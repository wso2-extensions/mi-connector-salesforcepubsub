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
package org.wso2.carbon.sfpubsubconnector;

import io.grpc.ManagedChannel;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class GRPCConnectionPool {
    private static final Logger LOGGER = Logger.getLogger(GRPCConnectionPool.class.getName());
    private static volatile GRPCConnectionPool INSTANCE;
    private final ConcurrentHashMap<String, GRPCConnection> connectionPool;
    private static final ReentrantLock initLock = new ReentrantLock();

    private GRPCConnectionPool() {
        this.connectionPool = new ConcurrentHashMap<>();
    }

    /**
     * Singleton instance access with double-checked locking
     */
    public static GRPCConnectionPool getInstance() {
        if (INSTANCE == null) {
            initLock.lock();
            try {
                if (INSTANCE == null) {
                    INSTANCE = new GRPCConnectionPool();
                }
            } finally {
                initLock.unlock();
            }
        }
        return INSTANCE;
    }

    public GRPCConnection getConnection(String key) {
        if (connectionPool.containsKey(key)) {
            return connectionPool.get(key);
        }
        return null;
    }

    public void removeConnection(String key) {
        connectionPool.remove(key);
    }

    public void addConnection(String key, GRPCConnection connection) {
        connectionPool.put(key, connection);
    }

    public static class GRPCConnection {
        private final String connectionName;
        private final Object stub;
        private final ManagedChannel channel;

        private GRPCConnection(Builder builder) {
            this.connectionName = builder.connectionName;
            this.stub = builder.stub;
            this.channel = builder.channel;
        }

        public String getConnectionName() {
            return connectionName;
        }

        public Object getStub() {
            return stub;
        }

        public ManagedChannel getChannel() {
            return channel;
        }

        public static class Builder {
            private String connectionName;
            private Object stub;
            private ManagedChannel channel;

            public Builder() {
            }

            public Builder connectionName(String connectionName) {
                this.connectionName = connectionName;
                return this;
            }

            public Builder stub(Object stub) {
                this.stub = stub;
                return this;
            }

            public Builder channel(ManagedChannel channel) {
                this.channel = channel;
                return this;
            }

            public GRPCConnection build() {
                return new GRPCConnection(this);
            }
        }
    }
}
