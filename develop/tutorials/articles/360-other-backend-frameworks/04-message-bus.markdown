# Introduction

Liferay ships with a lightweight message bus implementation to help facilitate
asynchronous communication between services.  It is similar in concept to Java
Messaging Service (JMS) Topics, but without transactional, reliable delivery
capabilities.

Liferay uses the MessageBus for publish/subscribe messaging, request queuing and
throttling, flow control, multi-threaded processing, and background task
processing.  Some examples where the MessageBus is used within Liferay:

1. Auditing

2. Search engine integration

3. Email subscriptions

4. Monitoring

5. Document Library processing

6. Background tasks

7. Cluster-wide request execution

8. Clustered cache replication

# Destinations

A Destination is the core foundation of the Liferay Message Bus.  There are
three primary Destination implementations:

1. Parallel Destination

    1. Messages sent to this destination are placed into queue

    2. Multiple worker threads from a thread pool will deliver each message to 
        a registered message listener, one worker thread per message per message
        listener.

2. Serial Destination

    1. Messages sent to this destination are placed into queue

    2. A single worker thread from a thread pool will deliver the message to  
        each registered message listener, one worker thread per message.

3. Synchronous Destination

    1. Messages sent to this destination are directly delivered to message 
        listeners

    2. The thread sending the message to the destination will also deliver the 
        message to all message listeners

Liferay comes preconfigured with a series of destinations for various purposes.
For instance, you can listen to "liferay/hot_deploy" for hot deploy event
messages.  These destinations have been tuned for specific purposes and you
should generally not modify them.  However, if you wish to see statistics like
messages sent, messages pending, etc, you can view them in the JMX Console.

![Figure x: JConsole shows you statistics on messages sent, messages pending, and more.](../../images/message-bus-jconsole.png)

## Creating Destinations

To create your own destination, you will first create a
`com.liferay.portal.kernel.messaging.DestinationConfiguration`.  You may choose
to use one of three factory methods:

- `createParallelDestinationConfiguration(destinationName)`

- `createSerialDestinationConfiguration(destinationName)`

- `createSynchronousDestinationConfiguration(destinationName)`

You may also choose to create your own custom `DestinationConfiguration` by
constructing one with the `destinationType` and `destinationName`.

    public DestinationConfiguration(String destinationType, String destinationName) {

    …

    }

Once you have created a `DestinationConfiguration`, you can set additional
configuration attributes like:

- `maximumQueueSize` - sets the maximum queued messages in the destination.

- `rejectedExecutionHandler` - configures a 
    `com.liferay.portal.kernel.concurrent.RejectedExecutionHandler` that handles
    messages that have been rejected when too many messages have been queued

- `workersCoreSize` - the default number of worker threads processing messages

- `workersMaxSize` - the maximum number of worker threads processing messages

With a `DestinationConfiguration`, you will then utilize a `DestinationFactory`
to create a Destination.

Finally, you will register the Destination to the OSGi service registry so that
it can be managed by the MessageBus.

Below is a completed example of a messaging configurator component that creates
and registers a destination.

    @Component(immediate = true, service = MyMessagingConfigurator .class)

    public class MyMessagingConfigurator {

    	@Activate

    	protected void activate(BundleContext bundleContext) {

    		_bundleContext = bundleContext;

    		DestinationConfiguration destinationConfiguration =

    	new DestinationConfiguration(

    				DestinationConfiguration.DESTINATION_TYPE_PARALLEL,

    "myDestinationName");

    		destinationConfiguration.setMaximumQueueSize(_MAXIMUM_QUEUE_SIZE);

    		RejectedExecutionHandler rejectedExecutionHandler =

    			new CallerRunsPolicy() {

    				@Override

    				public void rejectedExecution(

    					Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {

    					if (_log.isWarnEnabled()) {

    						_log.warn(

    							"The current thread will handle the request " +

    								"because the graph walker's task queue is at " +

    									"its maximum capacity");

    					}

    					super.rejectedExecution(runnable, threadPoolExecutor);

    				}

    			};

    		destinationConfiguration.setRejectedExecutionHandler(

    			rejectedExecutionHandler);

    Destination destination = _destinationFactory.createDestination(

    			kaleoGraphWalkerDestinationConfiguration);

    		Dictionary<String, Object> properties = new HashMapDictionary<>();

    		properties.put("destination.name", destination.getName());

    		ServiceRegistration<Destination> serviceRegistration =

    			_bundleContext.registerService(

    				Destination.class, destination, properties);

    		_serviceRegistrations.put(destination.getName(), serviceRegistration);

    	}

          @Deactivate

    	protected void deactivate() {

    for (ServiceRegistration<Destination> serviceRegistration : 

    _serviceRegistrations.values()) {

    	Destination destination = _bundleContext.getService(

    serviceRegistration.getReference());

    	serviceRegistration.unregister();

    	destination.destroy();

    }

    _serviceRegistrations.clear();

         }

    	@Reference

    	private DestinationFactory _destinationFactory;

    	@Reference

    	private MessageBus _messageBus;

    	private final Map<String, ServiceRegistration<Destination>>

    		_serviceRegistrations = new HashMap<>();

    }

## Destination Event Listeners

The Liferay Message Bus notifies `com.liferay.portal.kernel.messaging.DestinationEventListeners` when `MessageListeners` are either registered or unregistered from `Destinations`.  To register these listeners, publish a `DestinationEventListener` object to the OSGi service registry along with the service property `"destination.name"` pointing to the desired destination.

    @Component(

    	immediate = true,

    	property = {"destination.name=myCustom/Destination"},

    	service = DestinationEventListener.class

    )

    public class My DestinationEventListener implements DestinationEventListener{

    …

    }

## Message Bus Event Listeners

The Message Bus notifies
`com.liferay.portal.kernel.messaging.MessageBusEventListeners` when destinations
are either added or removed.  To register these listeners, simply publish a
`MessageBusEventListener` to the OSGi service registry (e.g. via `@Component`
Declarative Service).

# Receiving Messages

In any publish-subscribe framework, you have message listeners that receive
interested messages.  In the Message Bus, you implement a
`com.liferay.portal.kernel.messaging.MessageListener` and register it to a
destination to receive messages.

Once you have implemented your custom `MessageListener`, you can use two methods
to register it to a destination.  You may either:

-   Publish the `MessageListener` to the OSGi registry and the MessageBus will  
    automatically wire the listener to the proper destination (Automated
    Registration) or

-   Obtain a reference to the Message Bus and manually register the listener to 
    the proper destination (Manual Message Bus Registration) or

-   Obtain a reference to a specific Destination and manually register the 
    listener (Manual Destination Registration)

## Automated Registration

To automatically register, you would rely upon Declarative Services’ (DS) to
create and publish your `MessageListener` to the OSGi registry.

    @Component(

    	immediate = true,

    	property = {"destination.name=myCustom/Destination"},

    	service = MessageListener.class

    )

    public class MyMessageListener implements MessageListener {

    …

    }

The above will direct DS to instantiate a `MyMessageListener` component and
publish it into the OSGi registry as a `MessageListener` with the property
destination.name.

The Liferay Message Bus is listening for `MessageListener` references to be
published into the OSGi registry.  When it receives a `MessageListener`, it
looks to register it to the destination specified in the `destination.name`
property.

If the destination does not exist, the `MessageListener` is queued until the
destination is registered to the Message Bus.

This is the preferred way to register `MessageListeners` to destinations.

## Manual MessageBus Registration

There are some situations where you may wish to manually register message
listeners to the Message Bus.  For instance, you wanted to create some special
proxy wrappers, etc.  If you find yourself in this situation, you can simply do
something like:

    @Component(

    	immediate = true,

    	service = MyMessageListenerRegistrator.class

    )

    public class MyMessageListenerRegistrator {

    …

    	@Activate

    	protected void activate() {

    		_messageListener = new MessageListener() {

    			public void receive(Message message) {

    				…

    			}

    		};

    		

    		_messageBus.registerMessageListener("myDestinationName", _messageListener);

    	}

    	@Deactivate

    	protected void deactivate() {

    		_messageBus.unregisterMessageListener("myDestinationName", _messageListener);

    	}

    	@Reference

    	private MessageBus _messageBus;

    	private MessageListener _messageListener;

    }

Note, in the above example, we are using Declarative Services to obtain a
reference to a Message Bus.  Also, we need to make sure that when the component
is destroyed, we also unregister the message listener.

## Manual Destination Registration

The third and final avenue for registration is to directly register against a
destination that has been published to the OSGi registry.

There are some situations where you may wish to manually register message
listeners to the Message Bus.  For instance, you wanted to create some special
proxy wrappers, etc. If you find yourself in this situation, you can simply do
something like:

    @Component(

    	immediate = true,

    	service = MyMessageListenerRegistrator.class

    )

    public class MyMessageListenerRegistrator {

    …

    	@Activate

    	protected void activate() {

    		_messageListener = new MessageListener() {

    			public void receive(Message message) {

    				…

    			}

    		};

    		

    		_destination.register(_messageListener);

    	}

    	@Deactivate

    	protected void deactivate() {

    		_destination.unregister(_messageListener);

    	}

    	@Reference(target = "(destination.name=someDestinationName)")

    	private Destination _destination;

    	private MessageListener _messageListener;

    }

As in the previous example, we are using Declarative Services to obtain a
reference to a `Destination`.  Also, we need to make sure that when the
component is destroyed, we also unregister the message listener.

# Sending Messages

There are 3 ways to send messages via the Liferay Message Bus:

1.  Directly via the Liferay Message Bus

2.  Using a `SingleDestinationMessageSenderFactory` to obtain a 
    `SingleDestinationMessageSender`

3.  Using a `SingleDestinationMessageSenderFactory` to obtain a 
    `SynchronousMessageSender`

## Directly Using Message Bus

In the Liferay Message Bus, sending messages is quite simple:

1.  Create a `com.liferay.portal.kernel.messaging.Message` object

2.  Populate the `Message` with a payload

    1.  A String message payload: `message.setPayload(payload)` or

    2.  Object payloads: `message.put(key, value)`

3.  If expecting replies to the message to be sent to a certain location:

    1.  Set the `responseDestinationName`

    2.  Set the `responseId`

4.  Obtain a reference to the message bus and call 
    `messageBus.sendMessage(destinationName, message)`

Internally, the message bus will route the message to the appropriate
destination and deliver to the interested `MessageListeners`.

    @Component(

    	immediate = true,

    	service = SomeServiceImpl.class

    )

    public class SomeServiceImpl {

    …

    	public void sendSomeMessage() {

    		Message message = new Message();

    		message.put("myId", 12345);

    		message.put("someValue", “abcdef”);

    		_messageBus.sendMessage("myDestinationName", message);

    	}

    	@Reference

    	private MessageBus _messageBus;

    }

## SingleDestinationMessageSender

A `com.liferay.portal.kernel.messaging.sender.SingleDestinationMessageSender` is
a simplified wrapper around the Message Bus.  You obtain this sender via a
`SingleDestinationMessageSenderFactory.createSingleDestinationMessageSender()`.

    @Component(

    	immediate = true,

    	service = SomeServiceImpl.class

    )

    public class SomeServiceImpl {

    …

    	public void sendSomeMessage() {

    		Message message = new Message();

    		message.put("myId", 12345);

    		message.put("someValue", “abcdef”);

    		SingleDestinationMessageSender messageSender = 

    _messageSenderFactory.createSingleDestinationMessageSender("myDestinationName");

    messageSender.send(message);

    	}

    	@Reference

    	private SingleDestinationMessageSenderFactory _messageSenderFactory;

    }

## SynchronousMessageSender

A `SynchronousMessageSender` will send the message to the Message Bus and wait for a response prior to returning.  There are 2 operating modes for a `SynchronousMessageSender`:

1.  `DEFAULT` - this mode delivers the message in a separate thread and also provides timeouts in the event the message is not delivered properly.

2.  `DIRECT` - this mode delivers the message in the same thread of execution.  This mode does not provide the ability to 

        @Component(

        	immediate = true,

        	service = SomeServiceImpl.class

        )

        public class SomeServiceImpl {

        …

        	public void sendSomeMessage() {

        		Message message = new Message();

        		message.put("myId", 12345);

        		message.put("someValue", “abcdef”);

        		SingleDestinationSynchronousMessageSender messageSender = 

        _messageSenderFactory.createSingleDestinationSynchronousMessageSender(

        "myDestinationName", SynchronousMessageSender.MODE.DEFAULT);

        messageSender.send(message);

        	}

        	@Reference

        	private SingleDestinationMessageSenderFactory _messageSenderFactory;

        }

    ## Sending Messages Across the Cluster

    To ensure a message sent to a destination is received by all members of the
    cluster, you would utilize a
    `com.liferay.portal.kernel.cluster.messaging.ClusterBridgeMessageListener` to
    bridge the local destination to the cluster.

    In the following example, we will bridge the liferay/live_users destination so
    that a message send to the destination will be distributed across all JVMs
    within the cluster.

        @Component(

        	immediate = true,

        	service = MyMessageListenerRegistrator.class

        )

        public class MyMessageListenerRegistrator {

        …

        	@Activate

        	protected void activate() {

        		_clusterBridgeMessageListener = new ClusterBridgeMessageListener();

        		_clusterBridgeMessageListener.setPriority(Priority.LEVEL_5)

        		_destination.register(_clusterBridgeMessageListener);

        	}

        	@Deactivate

        	protected void deactivate() {

        		_destination.unregister(_clusterBridgeMessageListener );

        	}

        	@Reference(target = "(destination.name=liferay/live_users)")

        	private Destination _destination;

        	private MessageListener _clusterBridgeMessageListener;

        }

# Advanced Use Cases

Liferay has integrated some convenience facilities in Service Builder that
leverages the Message Bus, specifically `@Async` and `@Clusterable`.

## @Async

All Service Builder generated local and remote services (`*Service` and
`*LocalService`) are wrapped with a special AOP (aspect-oriented programming)
advice.  If you annotate a public method in your local or remote service
implementation with `@Async`, then any calls to the service method will result
in the method being converted to an asynchronous method call.

This allows us to quickly implement fire and forget capabilities to your
services.  This is especially useful for features like notifications.

## @Clusterable

The `Clusterable` annotation is another special convenience provided by Service
Builder that utilizes the Liferay Message Bus and Cluster Link.  Any service
method annotated with `Clusterable` will be invoked across the cluster.


The `Clusterable` annotation can be further qualified with two attributes:

-   `onMaster` - if set to true will only execute the request if the current 
    portal JVM is holding the cluster wide "master" token

-   `acceptor` - specifies a custom `ClusterInvokeAcceptor` to determine 
    whether the a given portal JVM should accept and thus execute the request.
