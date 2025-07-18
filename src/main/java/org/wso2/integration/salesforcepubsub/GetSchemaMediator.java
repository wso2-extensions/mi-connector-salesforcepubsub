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
import com.salesforce.eventbus.protobuf.SchemaRequest;
import com.salesforce.eventbus.protobuf.SchemaInfo;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import io.grpc.StatusRuntimeException;
import org.apache.synapse.MessageContext;

import org.wso2.integration.connector.core.AbstractConnectorOperation;

import static java.lang.String.format;

public class GetSchemaMediator extends AbstractConnectorOperation {
    @Override
    public void execute(MessageContext context, String responseVariable, Boolean overwriteBody) {
        try {
            String schema_id = (String) getParameter(context, "schema_id");
            SchemaRequest request = SchemaRequest.newBuilder()
                    .setSchemaId(schema_id)
                    .build();

            com.salesforce.eventbus.protobuf.PubSubGrpc.PubSubBlockingStub stub = (com.salesforce.eventbus.protobuf.PubSubGrpc.PubSubBlockingStub) context.getProperty("stub");

            SchemaInfo response = stub.getSchema(request);
            Map<String, Object> map = new HashMap<>();
            map.put("rpc_id", response.getRpcId());
            map.put("schema_json", response.getSchemaJson());
            map.put("schema_id", response.getSchemaId());
            String jsonPayload = new Gson().toJson(map);
            handleConnectorResponse(context, responseVariable, overwriteBody, jsonPayload, null, null);
        } catch (StatusRuntimeException e) {
            handleException(format("Error in PublishMediator: code %s , cause: %s ", e.getStatus().getCode().name(), e.getStatus().getDescription()), context);
        }
    }
}

