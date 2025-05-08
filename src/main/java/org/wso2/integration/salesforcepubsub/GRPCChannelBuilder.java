/*
 *  Copyright (c) 2025, WSO2 LLC. (https://www.wso2.com).
 *
 *  WSO2 LLC. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.wso2.integration.salesforcepubsub;

import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import org.apache.synapse.MessageContext;
import org.apache.synapse.util.InlineExpressionUtil;
import org.json.JSONArray;
import org.wso2.carbon.connector.core.AbstractConnector;
import org.wso2.carbon.connector.core.ConnectException;
import org.wso2.integration.salesforcepubsub.BasicAuthLogin.LoginResponse;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is responsible for creating a gRPC channel with metadata.
 */
public class GRPCChannelBuilder extends AbstractConnector {
    private static final Logger LOGGER = Logger.getLogger(GRPCChannelBuilder.class.getName());

    private static final String GRPC_CHANNEL = "grpc_channel";

    @Override
    public void connect(MessageContext messageContext) {
        String server = (String) getParameter(messageContext, "server");
        String port = (String) getParameter(messageContext, "port");
        String headers = (String) getParameter(messageContext, "headers");
        String username = (String) getParameter(messageContext, "username");
        String password = (String) getParameter(messageContext, "password");
        String name = (String) getParameter(messageContext, "name");
        String tlsEnabledString = (String) getParameter(messageContext, "tlsEnabled");
        Boolean tlsEnabled = Boolean.parseBoolean(tlsEnabledString);
        String securityToken = (String) getParameter(messageContext, "securityToken");


        GRPCConnectionPool instance = GRPCConnectionPool.getInstance();
        if (instance.getConnection(name) != null) {
            GRPCConnectionPool.GRPCConnection connection = instance.getConnection(name);
            ConnectivityState state = connection.getChannel().getState(true);
            if (state != ConnectivityState.SHUTDOWN && state != ConnectivityState.TRANSIENT_FAILURE) {
                Object stubObject = connection.getStub();
                com.salesforce.eventbus.protobuf.PubSubGrpc.PubSubBlockingStub stub =
                        (com.salesforce.eventbus.protobuf.PubSubGrpc.PubSubBlockingStub) stubObject;
                messageContext.setProperty("stub", stub);
                return;
            } else {
                instance.removeConnection(name);
            }
        }
        try {
            if (server == null || port == null) {
                handleException("Server or port cannot be null", messageContext);
            }

            int portInt = Integer.parseInt(port);
            String target = server + ":" + portInt;
            ManagedChannel channel;
            com.salesforce.eventbus.protobuf.PubSubGrpc.PubSubBlockingStub stub;
            if (username != null && password != null) {
                BasicAuthLogin basicAuthLogin = new BasicAuthLogin();
                LoginResponse loginResponse = basicAuthLogin.login(username, password, securityToken,
                        "https://login.salesforce.com/services/Soap/u/61.0");
                Metadata metadata = new Metadata();
                metadata.put(Metadata.Key.of("accessToken", Metadata.ASCII_STRING_MARSHALLER), loginResponse.sessionId);
                metadata.put(Metadata.Key.of("instanceUrl", Metadata.ASCII_STRING_MARSHALLER), loginResponse.instanceUrl);
                metadata.put(Metadata.Key.of("tenantId", Metadata.ASCII_STRING_MARSHALLER), loginResponse.tenantId);

                channel = createChannel(target, tlsEnabled, metadata);
                stub = com.salesforce.eventbus.protobuf.PubSubGrpc.newBlockingStub(channel);
            } else {
                Metadata metadata = headers != null ? getHeaderMetadata(messageContext, headers) : null;

                channel = createChannel(target, tlsEnabled, metadata);
                messageContext.setProperty(GRPC_CHANNEL, channel);
                stub = com.salesforce.eventbus.protobuf.PubSubGrpc.newBlockingStub(channel);
            }

            messageContext.setProperty("stub", stub);
            GRPCConnectionPool.GRPCConnection connection = new GRPCConnectionPool.GRPCConnection.Builder()
                    .connectionName(name)
                    .stub(stub)
                    .channel(channel).build();
            instance.addConnection(name, connection);

            messageContext.setProperty(GRPC_CHANNEL, channel);
        } catch (NumberFormatException e) {
            handleException("Invalid port number: " + port, messageContext);
        } catch (Exception e) {
            handleException("Failed to create gRPC channel", messageContext);
        }
    }

    private static Metadata getHeaderMetadata(MessageContext messageContext, String headers) throws ConnectException {
        // Extract metadata from JSON
        Metadata metadata = new Metadata();
        JSONArray headerSet;

        try {
            headers = InlineExpressionUtil.processInLineSynapseExpressionTemplate(messageContext, headers);
            headerSet = new JSONArray(headers);
        } catch (Exception e) {
            throw new ConnectException("Error parsing JSON headers: " + e.getMessage());
        }
        for (int i = 0; i < headerSet.length(); i++) {
            JSONArray row = headerSet.getJSONArray(i);

            if (row.length() >= 2) {
                String key = row.getString(0);
                String value = row.getString(1);

                metadata.put(Metadata.Key.of(key, Metadata.ASCII_STRING_MARSHALLER), value);
            }
        }
        return metadata;
    }

    /**
     * Creates a gRPC channel based on the provided parameters.
     *
     * @param target    The target server address in the format "host:port"
     * @param useSecure Whether to use transport security (TLS)
     * @param metadata  Optional metadata for the channel (can be null)
     * @return A configured ManagedChannel
     */
    private ManagedChannel createChannel(String target, boolean useSecure, Metadata metadata) throws ConnectException {
        try {
            if (useSecure && metadata != null) {
                LOGGER.info("gRPC secure channel is created with metadata:" + target);
                return ManagedChannelBuilder.forTarget(target)
                        .useTransportSecurity()
                        .intercept(new MetadataInterceptor(metadata))
                        .keepAliveTime(30, TimeUnit.SECONDS)
                        .keepAliveTimeout(10, TimeUnit.SECONDS)
                        .keepAliveWithoutCalls(true)
                        .build();
            } else if (useSecure) {
                LOGGER.info("gRPC secure channel is created:" + target);
                return ManagedChannelBuilder.forTarget(target)
                        .useTransportSecurity()
                        .keepAliveTime(30, TimeUnit.SECONDS)
                        .keepAliveTimeout(10, TimeUnit.SECONDS)
                        .keepAliveWithoutCalls(true)
                        .build();
            } else if (metadata != null) {
                LOGGER.info("gRPC channel is created with metadata:" + target);
                return ManagedChannelBuilder.forTarget(target)
                        .usePlaintext()
                        .intercept(new MetadataInterceptor(metadata))
                        .keepAliveTime(30, TimeUnit.SECONDS)
                        .keepAliveTimeout(10, TimeUnit.SECONDS)
                        .keepAliveWithoutCalls(true)
                        .build();
            } else {
                LOGGER.info("gRPC channel is created:" + target);
                return ManagedChannelBuilder.forTarget(target)
                        .usePlaintext()
                        .keepAliveTime(30, TimeUnit.SECONDS)
                        .keepAliveTimeout(10, TimeUnit.SECONDS)
                        .keepAliveWithoutCalls(true)
                        .build();
            }
        } catch (Exception e) {
            throw new ConnectException("Failed to create channel: " + e.getMessage());
        }
    }

}
