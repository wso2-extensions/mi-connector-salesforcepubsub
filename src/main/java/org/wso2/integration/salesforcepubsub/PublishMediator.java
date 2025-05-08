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

import com.salesforce.eventbus.protobuf.PubSubProto;
import com.salesforce.eventbus.protobuf.PubSubGrpc;
import com.salesforce.eventbus.protobuf.PublishRequest;
import com.salesforce.eventbus.protobuf.PublishResponse;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import io.grpc.StatusRuntimeException;
import org.apache.axis2.AxisFault;
import org.apache.synapse.MessageContext;

import org.apache.synapse.commons.json.JsonUtil;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.wso2.carbon.connector.core.AbstractConnector;

import static java.lang.String.format;

public class PublishMediator extends AbstractConnector {

    @Override
    public void connect(MessageContext context) {
        String topic_name = (String) getParameter(context, "topic_name");
        String eventsString = (String) getParameter(context, "events");
        com.salesforce.eventbus.protobuf.ProducerEvent[] events = (com.salesforce.eventbus.protobuf.ProducerEvent[])
                TypeConverter.convert(eventsString, com.salesforce.eventbus.protobuf.ProducerEvent[].class);

        try {
            PublishRequest.Builder requestBuilder = PublishRequest.newBuilder()
                    .setTopicName(topic_name)
                    .addAllEvents(Arrays.asList(events));
            PublishRequest request = requestBuilder.build();

            com.salesforce.eventbus.protobuf.PubSubGrpc.PubSubBlockingStub stub = (com.salesforce.eventbus.protobuf.PubSubGrpc.PubSubBlockingStub) context.getProperty("stub");

            PublishResponse response = stub.publish(request);
            Map<String, Object> map = new HashMap<>();
            map.put("rpc_id", response.getRpcId());
            map.put("schema_id", response.getSchemaId());
            map.put("results", response.getResultsList());
            String jsonPayload = new Gson().toJson(map);
            org.apache.axis2.context.MessageContext axisMsgCtx = ((Axis2MessageContext) context).getAxis2MessageContext();
            JsonUtil.getNewJsonPayload(axisMsgCtx, jsonPayload, true, true);
            axisMsgCtx.setProperty(org.apache.axis2.Constants.Configuration.MESSAGE_TYPE, "application/json");
            axisMsgCtx.setProperty(org.apache.axis2.Constants.Configuration.CONTENT_TYPE, "application/json");
        } catch (StatusRuntimeException e) {
            handleException(format("Error in PublishMediator: code %s , cause: %s ", e.getStatus().getCode().name(), e.getStatus().getDescription()), context);
        } catch (AxisFault e) {
            handleException("Error in PublishMediator:", e, context);
        }
    }
}

