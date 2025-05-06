package com.salesforce.eventbus.protobuf;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * The Pub/Sub API provides a single interface for publishing and subscribing to platform events, including real-time
 * event monitoring events, and change data capture events. The Pub/Sub API is a gRPC API that is based on HTTP/2.
 * A session token is needed to authenticate. Any of the Salesforce supported
 * OAuth flows can be used to obtain a session token:
 * https://help.salesforce.com/articleView?id=sf.remoteaccess_oauth_flows.htm&amp;type=5
 * For each RPC, a client needs to pass authentication information
 * as metadata headers (https://www.grpc.io/docs/guides/concepts/#metadata) with their method call.
 * For Salesforce session token authentication, use:
 *   accesstoken : access token
 *   instanceurl : Salesforce instance URL
 *   tenantid : tenant/org id of the client
 * StatusException is thrown in case of response failure for any request.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.70.0)",
    comments = "Source: pubsub_api.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class PubSubGrpc {

  private PubSubGrpc() {}

  public static final java.lang.String SERVICE_NAME = "eventbus.v1.PubSub";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.salesforce.eventbus.protobuf.FetchRequest,
      com.salesforce.eventbus.protobuf.FetchResponse> getSubscribeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Subscribe",
      requestType = com.salesforce.eventbus.protobuf.FetchRequest.class,
      responseType = com.salesforce.eventbus.protobuf.FetchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.salesforce.eventbus.protobuf.FetchRequest,
      com.salesforce.eventbus.protobuf.FetchResponse> getSubscribeMethod() {
    io.grpc.MethodDescriptor<com.salesforce.eventbus.protobuf.FetchRequest, com.salesforce.eventbus.protobuf.FetchResponse> getSubscribeMethod;
    if ((getSubscribeMethod = PubSubGrpc.getSubscribeMethod) == null) {
      synchronized (PubSubGrpc.class) {
        if ((getSubscribeMethod = PubSubGrpc.getSubscribeMethod) == null) {
          PubSubGrpc.getSubscribeMethod = getSubscribeMethod =
              io.grpc.MethodDescriptor.<com.salesforce.eventbus.protobuf.FetchRequest, com.salesforce.eventbus.protobuf.FetchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Subscribe"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.salesforce.eventbus.protobuf.FetchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.salesforce.eventbus.protobuf.FetchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new PubSubMethodDescriptorSupplier("Subscribe"))
              .build();
        }
      }
    }
    return getSubscribeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.salesforce.eventbus.protobuf.SchemaRequest,
      com.salesforce.eventbus.protobuf.SchemaInfo> getGetSchemaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetSchema",
      requestType = com.salesforce.eventbus.protobuf.SchemaRequest.class,
      responseType = com.salesforce.eventbus.protobuf.SchemaInfo.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.salesforce.eventbus.protobuf.SchemaRequest,
      com.salesforce.eventbus.protobuf.SchemaInfo> getGetSchemaMethod() {
    io.grpc.MethodDescriptor<com.salesforce.eventbus.protobuf.SchemaRequest, com.salesforce.eventbus.protobuf.SchemaInfo> getGetSchemaMethod;
    if ((getGetSchemaMethod = PubSubGrpc.getGetSchemaMethod) == null) {
      synchronized (PubSubGrpc.class) {
        if ((getGetSchemaMethod = PubSubGrpc.getGetSchemaMethod) == null) {
          PubSubGrpc.getGetSchemaMethod = getGetSchemaMethod =
              io.grpc.MethodDescriptor.<com.salesforce.eventbus.protobuf.SchemaRequest, com.salesforce.eventbus.protobuf.SchemaInfo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetSchema"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.salesforce.eventbus.protobuf.SchemaRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.salesforce.eventbus.protobuf.SchemaInfo.getDefaultInstance()))
              .setSchemaDescriptor(new PubSubMethodDescriptorSupplier("GetSchema"))
              .build();
        }
      }
    }
    return getGetSchemaMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.salesforce.eventbus.protobuf.TopicRequest,
      com.salesforce.eventbus.protobuf.TopicInfo> getGetTopicMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTopic",
      requestType = com.salesforce.eventbus.protobuf.TopicRequest.class,
      responseType = com.salesforce.eventbus.protobuf.TopicInfo.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.salesforce.eventbus.protobuf.TopicRequest,
      com.salesforce.eventbus.protobuf.TopicInfo> getGetTopicMethod() {
    io.grpc.MethodDescriptor<com.salesforce.eventbus.protobuf.TopicRequest, com.salesforce.eventbus.protobuf.TopicInfo> getGetTopicMethod;
    if ((getGetTopicMethod = PubSubGrpc.getGetTopicMethod) == null) {
      synchronized (PubSubGrpc.class) {
        if ((getGetTopicMethod = PubSubGrpc.getGetTopicMethod) == null) {
          PubSubGrpc.getGetTopicMethod = getGetTopicMethod =
              io.grpc.MethodDescriptor.<com.salesforce.eventbus.protobuf.TopicRequest, com.salesforce.eventbus.protobuf.TopicInfo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetTopic"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.salesforce.eventbus.protobuf.TopicRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.salesforce.eventbus.protobuf.TopicInfo.getDefaultInstance()))
              .setSchemaDescriptor(new PubSubMethodDescriptorSupplier("GetTopic"))
              .build();
        }
      }
    }
    return getGetTopicMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.salesforce.eventbus.protobuf.PublishRequest,
      com.salesforce.eventbus.protobuf.PublishResponse> getPublishMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Publish",
      requestType = com.salesforce.eventbus.protobuf.PublishRequest.class,
      responseType = com.salesforce.eventbus.protobuf.PublishResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.salesforce.eventbus.protobuf.PublishRequest,
      com.salesforce.eventbus.protobuf.PublishResponse> getPublishMethod() {
    io.grpc.MethodDescriptor<com.salesforce.eventbus.protobuf.PublishRequest, com.salesforce.eventbus.protobuf.PublishResponse> getPublishMethod;
    if ((getPublishMethod = PubSubGrpc.getPublishMethod) == null) {
      synchronized (PubSubGrpc.class) {
        if ((getPublishMethod = PubSubGrpc.getPublishMethod) == null) {
          PubSubGrpc.getPublishMethod = getPublishMethod =
              io.grpc.MethodDescriptor.<com.salesforce.eventbus.protobuf.PublishRequest, com.salesforce.eventbus.protobuf.PublishResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Publish"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.salesforce.eventbus.protobuf.PublishRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.salesforce.eventbus.protobuf.PublishResponse.getDefaultInstance()))
              .setSchemaDescriptor(new PubSubMethodDescriptorSupplier("Publish"))
              .build();
        }
      }
    }
    return getPublishMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.salesforce.eventbus.protobuf.PublishRequest,
      com.salesforce.eventbus.protobuf.PublishResponse> getPublishStreamMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PublishStream",
      requestType = com.salesforce.eventbus.protobuf.PublishRequest.class,
      responseType = com.salesforce.eventbus.protobuf.PublishResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.salesforce.eventbus.protobuf.PublishRequest,
      com.salesforce.eventbus.protobuf.PublishResponse> getPublishStreamMethod() {
    io.grpc.MethodDescriptor<com.salesforce.eventbus.protobuf.PublishRequest, com.salesforce.eventbus.protobuf.PublishResponse> getPublishStreamMethod;
    if ((getPublishStreamMethod = PubSubGrpc.getPublishStreamMethod) == null) {
      synchronized (PubSubGrpc.class) {
        if ((getPublishStreamMethod = PubSubGrpc.getPublishStreamMethod) == null) {
          PubSubGrpc.getPublishStreamMethod = getPublishStreamMethod =
              io.grpc.MethodDescriptor.<com.salesforce.eventbus.protobuf.PublishRequest, com.salesforce.eventbus.protobuf.PublishResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PublishStream"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.salesforce.eventbus.protobuf.PublishRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.salesforce.eventbus.protobuf.PublishResponse.getDefaultInstance()))
              .setSchemaDescriptor(new PubSubMethodDescriptorSupplier("PublishStream"))
              .build();
        }
      }
    }
    return getPublishStreamMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.salesforce.eventbus.protobuf.ManagedFetchRequest,
      com.salesforce.eventbus.protobuf.ManagedFetchResponse> getManagedSubscribeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ManagedSubscribe",
      requestType = com.salesforce.eventbus.protobuf.ManagedFetchRequest.class,
      responseType = com.salesforce.eventbus.protobuf.ManagedFetchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.salesforce.eventbus.protobuf.ManagedFetchRequest,
      com.salesforce.eventbus.protobuf.ManagedFetchResponse> getManagedSubscribeMethod() {
    io.grpc.MethodDescriptor<com.salesforce.eventbus.protobuf.ManagedFetchRequest, com.salesforce.eventbus.protobuf.ManagedFetchResponse> getManagedSubscribeMethod;
    if ((getManagedSubscribeMethod = PubSubGrpc.getManagedSubscribeMethod) == null) {
      synchronized (PubSubGrpc.class) {
        if ((getManagedSubscribeMethod = PubSubGrpc.getManagedSubscribeMethod) == null) {
          PubSubGrpc.getManagedSubscribeMethod = getManagedSubscribeMethod =
              io.grpc.MethodDescriptor.<com.salesforce.eventbus.protobuf.ManagedFetchRequest, com.salesforce.eventbus.protobuf.ManagedFetchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ManagedSubscribe"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.salesforce.eventbus.protobuf.ManagedFetchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.salesforce.eventbus.protobuf.ManagedFetchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new PubSubMethodDescriptorSupplier("ManagedSubscribe"))
              .build();
        }
      }
    }
    return getManagedSubscribeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PubSubStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PubSubStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PubSubStub>() {
        @java.lang.Override
        public PubSubStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PubSubStub(channel, callOptions);
        }
      };
    return PubSubStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static PubSubBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PubSubBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PubSubBlockingV2Stub>() {
        @java.lang.Override
        public PubSubBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PubSubBlockingV2Stub(channel, callOptions);
        }
      };
    return PubSubBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PubSubBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PubSubBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PubSubBlockingStub>() {
        @java.lang.Override
        public PubSubBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PubSubBlockingStub(channel, callOptions);
        }
      };
    return PubSubBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PubSubFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PubSubFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PubSubFutureStub>() {
        @java.lang.Override
        public PubSubFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PubSubFutureStub(channel, callOptions);
        }
      };
    return PubSubFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * The Pub/Sub API provides a single interface for publishing and subscribing to platform events, including real-time
   * event monitoring events, and change data capture events. The Pub/Sub API is a gRPC API that is based on HTTP/2.
   * A session token is needed to authenticate. Any of the Salesforce supported
   * OAuth flows can be used to obtain a session token:
   * https://help.salesforce.com/articleView?id=sf.remoteaccess_oauth_flows.htm&amp;type=5
   * For each RPC, a client needs to pass authentication information
   * as metadata headers (https://www.grpc.io/docs/guides/concepts/#metadata) with their method call.
   * For Salesforce session token authentication, use:
   *   accesstoken : access token
   *   instanceurl : Salesforce instance URL
   *   tenantid : tenant/org id of the client
   * StatusException is thrown in case of response failure for any request.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Bidirectional streaming RPC to subscribe to a Topic. The subscription is pull-based. A client can request
     * for more events as it consumes events. This enables a client to handle flow control based on the client's processing speed.
     * Typical flow:
     * 1. Client requests for X number of events via FetchRequest.
     * 2. Server receives request and delivers events until X events are delivered to the client via one or more FetchResponse messages.
     * 3. Client consumes the FetchResponse messages as they come.
     * 4. Client issues new FetchRequest for Y more number of events. This request can
     *    come before the server has delivered the earlier requested X number of events
     *    so the client gets a continuous stream of events if any.
     * If a client requests more events before the server finishes the last
     * requested amount, the server appends the new amount to the current amount of
     * events it still needs to fetch and deliver.
     * A client can subscribe at any point in the stream by providing a replay option in the first FetchRequest.
     * The replay option is honored for the first FetchRequest received from a client. Any subsequent FetchRequests with a
     * new replay option are ignored. A client needs to call the Subscribe RPC again to restart the subscription
     * at a new point in the stream.
     * The first FetchRequest of the stream identifies the topic to subscribe to.
     * If any subsequent FetchRequest provides topic_name, it must match what
     * was provided in the first FetchRequest; otherwise, the RPC returns an error
     * with INVALID_ARGUMENT status.
     * </pre>
     */
    default io.grpc.stub.StreamObserver<com.salesforce.eventbus.protobuf.FetchRequest> subscribe(
        io.grpc.stub.StreamObserver<com.salesforce.eventbus.protobuf.FetchResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getSubscribeMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get the event schema for a topic based on a schema ID.
     * </pre>
     */
    default void getSchema(com.salesforce.eventbus.protobuf.SchemaRequest request,
        io.grpc.stub.StreamObserver<com.salesforce.eventbus.protobuf.SchemaInfo> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetSchemaMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get the topic Information related to the specified topic.
     * </pre>
     */
    default void getTopic(com.salesforce.eventbus.protobuf.TopicRequest request,
        io.grpc.stub.StreamObserver<com.salesforce.eventbus.protobuf.TopicInfo> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetTopicMethod(), responseObserver);
    }

    /**
     * <pre>
     * Send a publish request to synchronously publish events to a topic.
     * </pre>
     */
    default void publish(com.salesforce.eventbus.protobuf.PublishRequest request,
        io.grpc.stub.StreamObserver<com.salesforce.eventbus.protobuf.PublishResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPublishMethod(), responseObserver);
    }

    /**
     * <pre>
     * Bidirectional Streaming RPC to publish events to the event bus.
     * PublishRequest contains the batch of events to publish.
     * The first PublishRequest of the stream identifies the topic to publish on.
     * If any subsequent PublishRequest provides topic_name, it must match what
     * was provided in the first PublishRequest; otherwise, the RPC returns an error
     * with INVALID_ARGUMENT status.
     * The server returns a PublishResponse for each PublishRequest when publish is
     * complete for the batch. A client does not have to wait for a PublishResponse
     * before sending a new PublishRequest, i.e. multiple publish batches can be queued
     * up, which allows for higher publish rate as a client can asynchronously
     * publish more events while publishes are still in flight on the server side.
     * PublishResponse holds a PublishResult for each event published that indicates success
     * or failure of the publish. A client can then retry the publish as needed before sending
     * more PublishRequests for new events to publish.
     * A client must send a valid publish request with one or more events every 70 seconds to hold on to the stream.
     * Otherwise, the server closes the stream and notifies the client. Once the client is notified of the stream closure,
     * it must make a new PublishStream call to resume publishing.
     * </pre>
     */
    default io.grpc.stub.StreamObserver<com.salesforce.eventbus.protobuf.PublishRequest> publishStream(
        io.grpc.stub.StreamObserver<com.salesforce.eventbus.protobuf.PublishResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getPublishStreamMethod(), responseObserver);
    }

    /**
     * <pre>
     * This feature is part of an open beta release and is subject to the applicable
     * Beta Services Terms provided at Agreements and Terms
     * (https://www.salesforce.com/company/legal/agreements/).
     * Same as Subscribe, but for Managed Subscription clients.
     * This feature is part of an open beta release.
     * </pre>
     */
    default io.grpc.stub.StreamObserver<com.salesforce.eventbus.protobuf.ManagedFetchRequest> managedSubscribe(
        io.grpc.stub.StreamObserver<com.salesforce.eventbus.protobuf.ManagedFetchResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getManagedSubscribeMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service PubSub.
   * <pre>
   * The Pub/Sub API provides a single interface for publishing and subscribing to platform events, including real-time
   * event monitoring events, and change data capture events. The Pub/Sub API is a gRPC API that is based on HTTP/2.
   * A session token is needed to authenticate. Any of the Salesforce supported
   * OAuth flows can be used to obtain a session token:
   * https://help.salesforce.com/articleView?id=sf.remoteaccess_oauth_flows.htm&amp;type=5
   * For each RPC, a client needs to pass authentication information
   * as metadata headers (https://www.grpc.io/docs/guides/concepts/#metadata) with their method call.
   * For Salesforce session token authentication, use:
   *   accesstoken : access token
   *   instanceurl : Salesforce instance URL
   *   tenantid : tenant/org id of the client
   * StatusException is thrown in case of response failure for any request.
   * </pre>
   */
  public static abstract class PubSubImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return PubSubGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service PubSub.
   * <pre>
   * The Pub/Sub API provides a single interface for publishing and subscribing to platform events, including real-time
   * event monitoring events, and change data capture events. The Pub/Sub API is a gRPC API that is based on HTTP/2.
   * A session token is needed to authenticate. Any of the Salesforce supported
   * OAuth flows can be used to obtain a session token:
   * https://help.salesforce.com/articleView?id=sf.remoteaccess_oauth_flows.htm&amp;type=5
   * For each RPC, a client needs to pass authentication information
   * as metadata headers (https://www.grpc.io/docs/guides/concepts/#metadata) with their method call.
   * For Salesforce session token authentication, use:
   *   accesstoken : access token
   *   instanceurl : Salesforce instance URL
   *   tenantid : tenant/org id of the client
   * StatusException is thrown in case of response failure for any request.
   * </pre>
   */
  public static final class PubSubStub
      extends io.grpc.stub.AbstractAsyncStub<PubSubStub> {
    private PubSubStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PubSubStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PubSubStub(channel, callOptions);
    }

    /**
     * <pre>
     * Bidirectional streaming RPC to subscribe to a Topic. The subscription is pull-based. A client can request
     * for more events as it consumes events. This enables a client to handle flow control based on the client's processing speed.
     * Typical flow:
     * 1. Client requests for X number of events via FetchRequest.
     * 2. Server receives request and delivers events until X events are delivered to the client via one or more FetchResponse messages.
     * 3. Client consumes the FetchResponse messages as they come.
     * 4. Client issues new FetchRequest for Y more number of events. This request can
     *    come before the server has delivered the earlier requested X number of events
     *    so the client gets a continuous stream of events if any.
     * If a client requests more events before the server finishes the last
     * requested amount, the server appends the new amount to the current amount of
     * events it still needs to fetch and deliver.
     * A client can subscribe at any point in the stream by providing a replay option in the first FetchRequest.
     * The replay option is honored for the first FetchRequest received from a client. Any subsequent FetchRequests with a
     * new replay option are ignored. A client needs to call the Subscribe RPC again to restart the subscription
     * at a new point in the stream.
     * The first FetchRequest of the stream identifies the topic to subscribe to.
     * If any subsequent FetchRequest provides topic_name, it must match what
     * was provided in the first FetchRequest; otherwise, the RPC returns an error
     * with INVALID_ARGUMENT status.
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.salesforce.eventbus.protobuf.FetchRequest> subscribe(
        io.grpc.stub.StreamObserver<com.salesforce.eventbus.protobuf.FetchResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getSubscribeMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * Get the event schema for a topic based on a schema ID.
     * </pre>
     */
    public void getSchema(com.salesforce.eventbus.protobuf.SchemaRequest request,
        io.grpc.stub.StreamObserver<com.salesforce.eventbus.protobuf.SchemaInfo> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetSchemaMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get the topic Information related to the specified topic.
     * </pre>
     */
    public void getTopic(com.salesforce.eventbus.protobuf.TopicRequest request,
        io.grpc.stub.StreamObserver<com.salesforce.eventbus.protobuf.TopicInfo> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetTopicMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Send a publish request to synchronously publish events to a topic.
     * </pre>
     */
    public void publish(com.salesforce.eventbus.protobuf.PublishRequest request,
        io.grpc.stub.StreamObserver<com.salesforce.eventbus.protobuf.PublishResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPublishMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Bidirectional Streaming RPC to publish events to the event bus.
     * PublishRequest contains the batch of events to publish.
     * The first PublishRequest of the stream identifies the topic to publish on.
     * If any subsequent PublishRequest provides topic_name, it must match what
     * was provided in the first PublishRequest; otherwise, the RPC returns an error
     * with INVALID_ARGUMENT status.
     * The server returns a PublishResponse for each PublishRequest when publish is
     * complete for the batch. A client does not have to wait for a PublishResponse
     * before sending a new PublishRequest, i.e. multiple publish batches can be queued
     * up, which allows for higher publish rate as a client can asynchronously
     * publish more events while publishes are still in flight on the server side.
     * PublishResponse holds a PublishResult for each event published that indicates success
     * or failure of the publish. A client can then retry the publish as needed before sending
     * more PublishRequests for new events to publish.
     * A client must send a valid publish request with one or more events every 70 seconds to hold on to the stream.
     * Otherwise, the server closes the stream and notifies the client. Once the client is notified of the stream closure,
     * it must make a new PublishStream call to resume publishing.
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.salesforce.eventbus.protobuf.PublishRequest> publishStream(
        io.grpc.stub.StreamObserver<com.salesforce.eventbus.protobuf.PublishResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getPublishStreamMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * This feature is part of an open beta release and is subject to the applicable
     * Beta Services Terms provided at Agreements and Terms
     * (https://www.salesforce.com/company/legal/agreements/).
     * Same as Subscribe, but for Managed Subscription clients.
     * This feature is part of an open beta release.
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.salesforce.eventbus.protobuf.ManagedFetchRequest> managedSubscribe(
        io.grpc.stub.StreamObserver<com.salesforce.eventbus.protobuf.ManagedFetchResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getManagedSubscribeMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service PubSub.
   * <pre>
   * The Pub/Sub API provides a single interface for publishing and subscribing to platform events, including real-time
   * event monitoring events, and change data capture events. The Pub/Sub API is a gRPC API that is based on HTTP/2.
   * A session token is needed to authenticate. Any of the Salesforce supported
   * OAuth flows can be used to obtain a session token:
   * https://help.salesforce.com/articleView?id=sf.remoteaccess_oauth_flows.htm&amp;type=5
   * For each RPC, a client needs to pass authentication information
   * as metadata headers (https://www.grpc.io/docs/guides/concepts/#metadata) with their method call.
   * For Salesforce session token authentication, use:
   *   accesstoken : access token
   *   instanceurl : Salesforce instance URL
   *   tenantid : tenant/org id of the client
   * StatusException is thrown in case of response failure for any request.
   * </pre>
   */
  public static final class PubSubBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<PubSubBlockingV2Stub> {
    private PubSubBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PubSubBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PubSubBlockingV2Stub(channel, callOptions);
    }

    /**
     * <pre>
     * Bidirectional streaming RPC to subscribe to a Topic. The subscription is pull-based. A client can request
     * for more events as it consumes events. This enables a client to handle flow control based on the client's processing speed.
     * Typical flow:
     * 1. Client requests for X number of events via FetchRequest.
     * 2. Server receives request and delivers events until X events are delivered to the client via one or more FetchResponse messages.
     * 3. Client consumes the FetchResponse messages as they come.
     * 4. Client issues new FetchRequest for Y more number of events. This request can
     *    come before the server has delivered the earlier requested X number of events
     *    so the client gets a continuous stream of events if any.
     * If a client requests more events before the server finishes the last
     * requested amount, the server appends the new amount to the current amount of
     * events it still needs to fetch and deliver.
     * A client can subscribe at any point in the stream by providing a replay option in the first FetchRequest.
     * The replay option is honored for the first FetchRequest received from a client. Any subsequent FetchRequests with a
     * new replay option are ignored. A client needs to call the Subscribe RPC again to restart the subscription
     * at a new point in the stream.
     * The first FetchRequest of the stream identifies the topic to subscribe to.
     * If any subsequent FetchRequest provides topic_name, it must match what
     * was provided in the first FetchRequest; otherwise, the RPC returns an error
     * with INVALID_ARGUMENT status.
     * </pre>
     */
    @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/10918")
    public io.grpc.stub.BlockingClientCall<com.salesforce.eventbus.protobuf.FetchRequest, com.salesforce.eventbus.protobuf.FetchResponse>
        subscribe() {
      return io.grpc.stub.ClientCalls.blockingBidiStreamingCall(
          getChannel(), getSubscribeMethod(), getCallOptions());
    }

    /**
     * <pre>
     * Get the event schema for a topic based on a schema ID.
     * </pre>
     */
    public com.salesforce.eventbus.protobuf.SchemaInfo getSchema(com.salesforce.eventbus.protobuf.SchemaRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetSchemaMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get the topic Information related to the specified topic.
     * </pre>
     */
    public com.salesforce.eventbus.protobuf.TopicInfo getTopic(com.salesforce.eventbus.protobuf.TopicRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetTopicMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Send a publish request to synchronously publish events to a topic.
     * </pre>
     */
    public com.salesforce.eventbus.protobuf.PublishResponse publish(com.salesforce.eventbus.protobuf.PublishRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPublishMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Bidirectional Streaming RPC to publish events to the event bus.
     * PublishRequest contains the batch of events to publish.
     * The first PublishRequest of the stream identifies the topic to publish on.
     * If any subsequent PublishRequest provides topic_name, it must match what
     * was provided in the first PublishRequest; otherwise, the RPC returns an error
     * with INVALID_ARGUMENT status.
     * The server returns a PublishResponse for each PublishRequest when publish is
     * complete for the batch. A client does not have to wait for a PublishResponse
     * before sending a new PublishRequest, i.e. multiple publish batches can be queued
     * up, which allows for higher publish rate as a client can asynchronously
     * publish more events while publishes are still in flight on the server side.
     * PublishResponse holds a PublishResult for each event published that indicates success
     * or failure of the publish. A client can then retry the publish as needed before sending
     * more PublishRequests for new events to publish.
     * A client must send a valid publish request with one or more events every 70 seconds to hold on to the stream.
     * Otherwise, the server closes the stream and notifies the client. Once the client is notified of the stream closure,
     * it must make a new PublishStream call to resume publishing.
     * </pre>
     */
    @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/10918")
    public io.grpc.stub.BlockingClientCall<com.salesforce.eventbus.protobuf.PublishRequest, com.salesforce.eventbus.protobuf.PublishResponse>
        publishStream() {
      return io.grpc.stub.ClientCalls.blockingBidiStreamingCall(
          getChannel(), getPublishStreamMethod(), getCallOptions());
    }

    /**
     * <pre>
     * This feature is part of an open beta release and is subject to the applicable
     * Beta Services Terms provided at Agreements and Terms
     * (https://www.salesforce.com/company/legal/agreements/).
     * Same as Subscribe, but for Managed Subscription clients.
     * This feature is part of an open beta release.
     * </pre>
     */
    @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/10918")
    public io.grpc.stub.BlockingClientCall<com.salesforce.eventbus.protobuf.ManagedFetchRequest, com.salesforce.eventbus.protobuf.ManagedFetchResponse>
        managedSubscribe() {
      return io.grpc.stub.ClientCalls.blockingBidiStreamingCall(
          getChannel(), getManagedSubscribeMethod(), getCallOptions());
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service PubSub.
   * <pre>
   * The Pub/Sub API provides a single interface for publishing and subscribing to platform events, including real-time
   * event monitoring events, and change data capture events. The Pub/Sub API is a gRPC API that is based on HTTP/2.
   * A session token is needed to authenticate. Any of the Salesforce supported
   * OAuth flows can be used to obtain a session token:
   * https://help.salesforce.com/articleView?id=sf.remoteaccess_oauth_flows.htm&amp;type=5
   * For each RPC, a client needs to pass authentication information
   * as metadata headers (https://www.grpc.io/docs/guides/concepts/#metadata) with their method call.
   * For Salesforce session token authentication, use:
   *   accesstoken : access token
   *   instanceurl : Salesforce instance URL
   *   tenantid : tenant/org id of the client
   * StatusException is thrown in case of response failure for any request.
   * </pre>
   */
  public static final class PubSubBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<PubSubBlockingStub> {
    private PubSubBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PubSubBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PubSubBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Get the event schema for a topic based on a schema ID.
     * </pre>
     */
    public com.salesforce.eventbus.protobuf.SchemaInfo getSchema(com.salesforce.eventbus.protobuf.SchemaRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetSchemaMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get the topic Information related to the specified topic.
     * </pre>
     */
    public com.salesforce.eventbus.protobuf.TopicInfo getTopic(com.salesforce.eventbus.protobuf.TopicRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetTopicMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Send a publish request to synchronously publish events to a topic.
     * </pre>
     */
    public com.salesforce.eventbus.protobuf.PublishResponse publish(com.salesforce.eventbus.protobuf.PublishRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPublishMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service PubSub.
   * <pre>
   * The Pub/Sub API provides a single interface for publishing and subscribing to platform events, including real-time
   * event monitoring events, and change data capture events. The Pub/Sub API is a gRPC API that is based on HTTP/2.
   * A session token is needed to authenticate. Any of the Salesforce supported
   * OAuth flows can be used to obtain a session token:
   * https://help.salesforce.com/articleView?id=sf.remoteaccess_oauth_flows.htm&amp;type=5
   * For each RPC, a client needs to pass authentication information
   * as metadata headers (https://www.grpc.io/docs/guides/concepts/#metadata) with their method call.
   * For Salesforce session token authentication, use:
   *   accesstoken : access token
   *   instanceurl : Salesforce instance URL
   *   tenantid : tenant/org id of the client
   * StatusException is thrown in case of response failure for any request.
   * </pre>
   */
  public static final class PubSubFutureStub
      extends io.grpc.stub.AbstractFutureStub<PubSubFutureStub> {
    private PubSubFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PubSubFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PubSubFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Get the event schema for a topic based on a schema ID.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.salesforce.eventbus.protobuf.SchemaInfo> getSchema(
        com.salesforce.eventbus.protobuf.SchemaRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetSchemaMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get the topic Information related to the specified topic.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.salesforce.eventbus.protobuf.TopicInfo> getTopic(
        com.salesforce.eventbus.protobuf.TopicRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetTopicMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Send a publish request to synchronously publish events to a topic.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.salesforce.eventbus.protobuf.PublishResponse> publish(
        com.salesforce.eventbus.protobuf.PublishRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPublishMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_SCHEMA = 0;
  private static final int METHODID_GET_TOPIC = 1;
  private static final int METHODID_PUBLISH = 2;
  private static final int METHODID_SUBSCRIBE = 3;
  private static final int METHODID_PUBLISH_STREAM = 4;
  private static final int METHODID_MANAGED_SUBSCRIBE = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_SCHEMA:
          serviceImpl.getSchema((com.salesforce.eventbus.protobuf.SchemaRequest) request,
              (io.grpc.stub.StreamObserver<com.salesforce.eventbus.protobuf.SchemaInfo>) responseObserver);
          break;
        case METHODID_GET_TOPIC:
          serviceImpl.getTopic((com.salesforce.eventbus.protobuf.TopicRequest) request,
              (io.grpc.stub.StreamObserver<com.salesforce.eventbus.protobuf.TopicInfo>) responseObserver);
          break;
        case METHODID_PUBLISH:
          serviceImpl.publish((com.salesforce.eventbus.protobuf.PublishRequest) request,
              (io.grpc.stub.StreamObserver<com.salesforce.eventbus.protobuf.PublishResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SUBSCRIBE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.subscribe(
              (io.grpc.stub.StreamObserver<com.salesforce.eventbus.protobuf.FetchResponse>) responseObserver);
        case METHODID_PUBLISH_STREAM:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.publishStream(
              (io.grpc.stub.StreamObserver<com.salesforce.eventbus.protobuf.PublishResponse>) responseObserver);
        case METHODID_MANAGED_SUBSCRIBE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.managedSubscribe(
              (io.grpc.stub.StreamObserver<com.salesforce.eventbus.protobuf.ManagedFetchResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getSubscribeMethod(),
          io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
            new MethodHandlers<
              com.salesforce.eventbus.protobuf.FetchRequest,
              com.salesforce.eventbus.protobuf.FetchResponse>(
                service, METHODID_SUBSCRIBE)))
        .addMethod(
          getGetSchemaMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.salesforce.eventbus.protobuf.SchemaRequest,
              com.salesforce.eventbus.protobuf.SchemaInfo>(
                service, METHODID_GET_SCHEMA)))
        .addMethod(
          getGetTopicMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.salesforce.eventbus.protobuf.TopicRequest,
              com.salesforce.eventbus.protobuf.TopicInfo>(
                service, METHODID_GET_TOPIC)))
        .addMethod(
          getPublishMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.salesforce.eventbus.protobuf.PublishRequest,
              com.salesforce.eventbus.protobuf.PublishResponse>(
                service, METHODID_PUBLISH)))
        .addMethod(
          getPublishStreamMethod(),
          io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
            new MethodHandlers<
              com.salesforce.eventbus.protobuf.PublishRequest,
              com.salesforce.eventbus.protobuf.PublishResponse>(
                service, METHODID_PUBLISH_STREAM)))
        .addMethod(
          getManagedSubscribeMethod(),
          io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
            new MethodHandlers<
              com.salesforce.eventbus.protobuf.ManagedFetchRequest,
              com.salesforce.eventbus.protobuf.ManagedFetchResponse>(
                service, METHODID_MANAGED_SUBSCRIBE)))
        .build();
  }

  private static abstract class PubSubBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PubSubBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.salesforce.eventbus.protobuf.PubSubProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PubSub");
    }
  }

  private static final class PubSubFileDescriptorSupplier
      extends PubSubBaseDescriptorSupplier {
    PubSubFileDescriptorSupplier() {}
  }

  private static final class PubSubMethodDescriptorSupplier
      extends PubSubBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    PubSubMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (PubSubGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PubSubFileDescriptorSupplier())
              .addMethod(getSubscribeMethod())
              .addMethod(getGetSchemaMethod())
              .addMethod(getGetTopicMethod())
              .addMethod(getPublishMethod())
              .addMethod(getPublishStreamMethod())
              .addMethod(getManagedSubscribeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
