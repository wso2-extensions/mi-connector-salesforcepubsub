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
package org.wso2.carbon.pubsubconnector;

import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import org.apache.synapse.MessageContext;
import org.apache.synapse.util.InlineExpressionUtil;
import org.json.JSONArray;
import org.wso2.carbon.connector.core.AbstractConnector;
import org.wso2.carbon.connector.core.ConnectException;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is responsible for creating a gRPC channel with metadata.
 */
public class GRPCChannelBuilder extends AbstractConnector {
    private static final Logger LOGGER = Logger.getLogger(GRPCChannelBuilder.class.getName());

    private static String server;
    private static String port;
    private static String headers;
    private static String username;
    private static String password;
    private static String serverCrt;
    private static String bearerToken;
    private static final String GRPC_CHANNEL = "grpc_channel";
    private static String name;
    private static boolean tlsEnabled;

    public static String getServer() {
        return server;
    }

    public static void setServer(String server) {
        GRPCChannelBuilder.server = server;
    }

    public static String getPort() {
        return port;
    }

    public static void setPort(String port) {
        GRPCChannelBuilder.port = port;
    }


    public static void setHeaders(String headersJson) {
        GRPCChannelBuilder.headers = headersJson;
    }

    public static String getGRPCHeaders() {
        return headers;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        GRPCChannelBuilder.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        GRPCChannelBuilder.password = password;
    }

    public static String getServerCrt() {
        return serverCrt;
    }

    public static void setServerCrt(String serverCrt) {
        GRPCChannelBuilder.serverCrt = serverCrt;
    }

    public static String getBearerToken() {
        return bearerToken;
    }

    public static void setBearerToken(String bearerToken) {
        GRPCChannelBuilder.bearerToken = bearerToken;
    }

    public static void setName(String name) {
        GRPCChannelBuilder.name = name;
    }

    public static String getName() {
        return name;
    }


    public static void setTlsEnabled(String tlsEnabled) {
           boolean myBoolean = Boolean.parseBoolean(tlsEnabled);
           GRPCChannelBuilder.tlsEnabled = myBoolean;
    }
    public static boolean isTLS() {
        return tlsEnabled;
    }

    @Override
    public void connect(MessageContext messageContext) {
        GRPCConnectionPool instance = GRPCConnectionPool.getInstance();
        if(instance.getConnection(getName()) != null) {
            GRPCConnectionPool.GRPCConnection connection = instance.getConnection(getName());
            ConnectivityState state = connection.getChannel().getState(true);
            if (state != ConnectivityState.SHUTDOWN && state != ConnectivityState.TRANSIENT_FAILURE) {
                Object stubObject = connection.getStub();
                com.salesforce.eventbus.protobuf.PubSubGrpc.PubSubBlockingStub stub =
                        (com.salesforce.eventbus.protobuf.PubSubGrpc.PubSubBlockingStub) stubObject;
                messageContext.setProperty("stub", stub);
                return;
            } else {
                instance.removeConnection(getName());
            }
        }
        try {
            if (getServer() == null || getPort() == null) {
                handleException("Server or port cannot be null", messageContext);
            }

            int portInt = Integer.parseInt(getPort());
            String target = getServer() + ":" + portInt;
            Metadata metadata = getGRPCHeaders() != null ? getHeaderMetadata(messageContext): null;

            ManagedChannel channel = createChannel(target, isTLS(), metadata);
            messageContext.setProperty(GRPC_CHANNEL, channel);

            com.salesforce.eventbus.protobuf.PubSubGrpc.PubSubBlockingStub stub;
            if ( getUsername() != null && getPassword() != null) {
                BasicCallCredentials basicAuthCredential = new BasicCallCredentials(getUsername(), getPassword());
                stub = com.salesforce.eventbus.protobuf.PubSubGrpc.newBlockingStub(channel).withCallCredentials(basicAuthCredential);
            } else if (getBearerToken() != null) {
                TokenCallCredentials tokenCredential = new TokenCallCredentials(getBearerToken());
                stub = com.salesforce.eventbus.protobuf.PubSubGrpc.newBlockingStub(channel).withCallCredentials(tokenCredential);
            } else {
                stub = com.salesforce.eventbus.protobuf.PubSubGrpc.newBlockingStub(channel);
            }
            messageContext.setProperty("stub", stub);
            GRPCConnectionPool.GRPCConnection connection = new GRPCConnectionPool.GRPCConnection.Builder()
                    .connectionName(getName())
                            .stub(stub)
                                    .channel(channel).build();
            instance.addConnection(getName(), connection);

            messageContext.setProperty(GRPC_CHANNEL, channel);
        } catch (NumberFormatException e) {
            handleException("Invalid port number: " + getPort(), messageContext);
        } catch (Exception e) {
            handleException("Failed to create gRPC channel", messageContext);
        }
    }

    private static Metadata getHeaderMetadata(MessageContext messageContext) throws ConnectException {
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
     * @param target The target server address in the format "host:port"
     * @param useSecure Whether to use transport security (TLS)
     * @param metadata Optional metadata for the channel (can be null)
     * @return A configured ManagedChannel
     */
    private ManagedChannel createChannel(String target, boolean useSecure, Metadata metadata) throws ConnectException {
        try {
            if (useSecure && metadata != null) {
                LOGGER.info("gRPC secure channel is created with metadata:"+ target);
                return  ManagedChannelBuilder.forTarget(target)
                        .useTransportSecurity()
                        .intercept(new MetadataInterceptor(metadata))
                        .keepAliveTime(30, TimeUnit.SECONDS)
                        .keepAliveTimeout(10, TimeUnit.SECONDS)
                        .keepAliveWithoutCalls(true)
                        .build();
            } else if (useSecure){
                LOGGER.info("gRPC secure channel is created:"+ target);
                return ManagedChannelBuilder.forTarget(target)
                        .useTransportSecurity()
                        .keepAliveTime(30, TimeUnit.SECONDS)
                        .keepAliveTimeout(10, TimeUnit.SECONDS)
                        .keepAliveWithoutCalls(true)
                        .build();
            } else if (metadata != null) {
                LOGGER.info("gRPC channel is created with metadata:"+ target);
                return ManagedChannelBuilder.forTarget(target)
                        .usePlaintext()
                        .intercept(new MetadataInterceptor(metadata))
                        .keepAliveTime(30, TimeUnit.SECONDS)
                        .keepAliveTimeout(10, TimeUnit.SECONDS)
                        .keepAliveWithoutCalls(true)
                        .build();
            } else {
                LOGGER.info("gRPC channel is created:"+ target);
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
