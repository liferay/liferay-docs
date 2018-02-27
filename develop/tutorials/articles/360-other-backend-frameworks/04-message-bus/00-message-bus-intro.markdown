# Message Bus

Are you looking for a lightweight way for your apps and components to communicate? Message Bus is conceptually similar to Java Messaging
Service (JMS) Topics, but sacrifices transactional, reliable delivery capabilities. @product@ provides Message Bus out of the box. Here are some of its features:

-   publish/subscribe messaging 
-   request queuing and throttling
-   flow control
-   multi-thread message processing

And here are some of the ways @product@ uses Message Bus:

- Auditing
- Search engine integration
- Email subscriptions
- Monitoring
- Document Library processing
- Background tasks
- Cluster-wide request execution
- Clustered cache replication

This tutorial covers these Message Bus topics:

- Destinations 
- Receiving Messages 
- Sending Messages 
- Event Listeners
- Service Builder Service Conveniences

## Destinations

Messages are sent to and received at destinations. Destinations are foundational
to Message Bus. Here are the primary destination implementations:

-  **Parallel Destination**

    -   Messages sent here are queued.

    -   Multiple worker threads from a thread pool deliver each message to a 
        registered message listener. There's one worker thread per message per
        message listener.

-  **Serial Destination**

    -   Messages sent here are queued.

    -   Worker threads from a thread pool deliver the messages to each  
        registered message listener, one worker thread per message.

-  **Synchronous Destination**

    -   Messages sent here are directly delivered to message listeners.

    -   The thread sending the message here delivers the message to all message 
        listeners also.

Liferay has preconfigured destinations for various purposes. For example, the
`"liferay/hot_deploy"` destination is for  deployment event messages.  Since
Liferay's destinations are tuned for specific purposes, you should generally not
modify them. 

Tools such as the Java SE's JConsole help you monitor messages by destination. 

![Figure 1: JConsole shows you statistics on messages sent, messages pending, and more.](../../images/message-bus-jconsole.png)

### Creating Destinations

To create your own destination, you must first create a
[`DestinationConfiguration` instance](@platform-ref@/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/DestinationConfiguration.html). The configuration specifies the destination type and name and the following destination related attributes. 

-   **Maximum Queue Size**: limits the number of queued messages for the
    destination.

-   **Rejected Execution Handler**: A 
    `com.liferay.portal.kernel.concurrent.RejectedExecutionHandler` for managing
    rejected messages when the destination queue is full. 

-   **Workers Core Size**: initial number of worker threads for processing messages.

-   **Workers Max Size**: limits the number of worker threads for processing 
    messages.

These static factory methods create configurations for the destination types
mentioned previously. 

- `createParallelDestinationConfiguration(destinationName)`

- `createSerialDestinationConfiguration(destinationName)`

- `createSynchronousDestinationConfiguration(destinationName)`

The [`DestinationConfiguration` constructor](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/DestinationConfiguration.html#DestinationConfiguration-java.lang.String-java.lang.String-)
lets you create a configuration for any destination type, such as one of the previously mentioned types or your own custom type. 

    public DestinationConfiguration(String destinationType, String destinationName) {
        ...
    }

The [`DestinationFactory` class](@platform-ref@/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/DestinationFactory.html)
creates destinations based on a destination configuration. Once you create a
destination, add it to the OSGi service registry for Message Bus to manage.

For example, this messaging configurator component creates and registers a
parallel destination:

    @Component(immediate = true, service = MyMessagingConfigurator .class)
    public class MyMessagingConfigurator {

        @Activate
        protected void activate(BundleContext bundleContext) {

            _bundleContext = bundleContext;

            // Create a DestinationConfiguration for parallel destinations.

            DestinationConfiguration destinationConfiguration =
                new DestinationConfiguration(
                    DestinationConfiguration.DESTINATION_TYPE_PARALLEL,
                        "myDestinationName");

            // Set the DestinationConfiguration's max queue size and
            // rejected execution handler.

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

           // Create the destination

           Destination destination = _destinationFactory.createDestination(
                kaleoGraphWalkerDestinationConfiguration);

            // Add the destination to the OSGi service registry

            Dictionary<String, Object> properties = new HashMapDictionary<>();

            properties.put("destination.name", destination.getName());

            ServiceRegistration<Destination> serviceRegistration =
                _bundleContext.registerService(
                    Destination.class, destination, properties);

            // Track references to the destination service registrations 

            _serviceRegistrations.put(destination.getName(),    
                serviceRegistration);
        }

        @Deactivate
        protected void deactivate() {

            // Unregister and destroy destinations this component unregistered

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

        private final Map<String, ServiceRegistration<Destination>>
            _serviceRegistrations = new HashMap<>();
    }

When the configurator component activates, its `activate` method does these
things:

1.  Creates a `DestinationConfiguration` for parallel destinations.
2.  Sets the `DestinationConfiguration`'s max queue size and a rejected
    execution handler, to handle messages rejected after the queue is full. 
3.  Uses the `DestinationFactory` (the one bound to the `_destinationFactory`
    field) to create the destination. 
4.  Adds the destination to the OSGi service registry
5.  Adds the destination service registration to the configurator's map for tracking its registered destinations. 

Once registered, the destination is ready for registered messages listeners to
receive messages sent to it. 

On the component's deactivation, the `deactivate` method unregisters the destination services and destroys the destinations. 

Next you'll explore creating message listeners to receive messages sent to the
destinations. 

## Receiving Messages

Message listeners receive messages at associated destinations. Listeners
must implement the
[`MessageListener` interface](@platform-ref@/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/MessageListener.html)
and override the `receive(Message)` method to process messages. 

    public void receive(Message message) {
        // Handle the message
    }

Here are the ways to register listeners with Message Bus to receive messages at
destinations:

-   **Automatic Registration as a Component**: Publish the listener to the OSGi
    registry as a Declarative Services Component that specifies a destination.
    Message Bus automatically wires the listener to the proper destination.

-   **Registering via MessageBus**: Obtain a reference to the Message Bus and
    use it directly to register the listener to the proper destination.

-   **Registering directly to the Destination**: Obtain a reference to a specific 
    destination and use it directly to register the listener with that
    destination.

### Automatic Registration as a Component

The Declarative Services (DS) `@Component` annotation lets you
specify a message listener completely. 

    @Component(
        immediate = true,
        property = {"destination.name=myCustom/Destination"},
        service = MessageListener.class
    )
    public class MyMessageListener implements MessageListener {
       ...

       public void receive(Message message) {
           // Handle the message
       }
    }
    
The Message Bus listens for `MessageListener` services published to the OSGi
registry. Message Bus wires each listener to the destination its
`destination.name` property specifies. If the destination is not registered,
Message Bus queues the listener until it is registered. 

Registration as a component is the preferred way to register message listeners
to destinations.

### Registering via MessageBus

You can use the [`MessageBus` instance](@platform-ref@/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/MessageBus.html)
directly to register message listeners to destinations. You might want to do
this if, for example, you want to create some special proxy wrappers. Here's a
registrator that demonstrates registering a listener this way:

    @Component(
        immediate = true,
        service = MyMessageListenerRegistrator.class
    )
    public class MyMessageListenerRegistrator {
        ...

        @Activate
        protected void activate() {

            _messageListener = new MessageListener() {

                public void receive(Message message) {
                    // Handle the message
                }
            };

            _messageBus.registerMessageListener("myDestinationName",  
                _messageListener);
        }

        @Deactivate
        protected void deactivate() {
            _messageBus.unregisterMessageListener("myDestinationName",  
                _messageListener);
        }

        @Reference
        private MessageBus _messageBus;

        private MessageListener _messageListener;
    }

The `_messageBus` field's `@Reference` annotation binds it to the `MessageBus`
instance. The `activate` method creates the listener and uses the Message Bus to
register the listener to a destination named `"myDestination"`. When this
registrator component is destroyed, the `deactivate` method unregisters the
listener. 

### Registering directly to the Destination

You can use a `Destination` instance to register a listener to that destination.
You might want to do this if, for example, you want to create some special proxy
wrappers.  Here's a registrator that demonstrates registering a listener this
way:

    @Component(
        immediate = true,
        service = MyMessageListenerRegistrator.class
    )
    public class MyMessageListenerRegistrator {
       ...

        @Activate
        protected void activate() {

            _messageListener = new MessageListener() {

                public void receive(Message message) {
                    // Handle the message
                }
            };

            _destination.register(_messageListener);
        }

        @Deactivate
        protected void deactivate() {

            _destination.unregister(_messageListener);
        }

        @Reference(target = "(destination.name=someDestination)")
        private Destination _destination;

        private MessageListener _messageListener;
    }

The `_destination` field's `@Reference` annotation binds it to a destination
named `"someDestination"`. The `activate` method creates the listener and
registers it to the destination. When this registrator component is destroyed,
the `deactivate` method unregisters the listener. 

Now that you've registered destinations and message listeners for receiving
messages sent to the destinations, it's time to explore creating and sending
messages. 

## Sending Messages

Here you'll explore creating messages and sending them the way you want.

### Creating a Message

Here's how to create a message:

1.  Create a new [`Message` object](@platform-ref@/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/Message.html).

    `Message message = new Message();`

2.  Populate the message with a `String` or `Object` payload

    -   `String` payload: `message.setPayload("Message Bus is great!")`

    -   Object payload: `message.put("firstName", "Joe")`

3.  To receive responses at a particular location, set both of these attributes

    -  Response destination name: `setResponseDestinationName(String)`

    -  Response ID: `setResponseId(String)`

Your message is ready to send. 

### Sending a Message

Here are the ways to send a message: 

1.  Directly using the `MessageBus`

2.  Using a `SingleDestinationMessageSender` 

3.  Using a `SynchronousMessageSender`

#### Directly Using the Message Bus

This method involves obtaining a [`MessageBus` instance](@platform-ref@/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/MessageBus.html)
and invoking it to send messages. Here's an example of directly using Message
Bus to send a message.

    @Component(
        immediate = true,
        service = SomeServiceImpl.class
    )
    public class SomeServiceImpl {
        ...

        public void sendSomeMessage() {

            Message message = new Message();
            message.put("myId", 12345);
            message.put("someAttribute", “abcdef”);
            _messageBus.sendMessage("myDestinationName", message);
        }

        @Reference
        private MessageBus _messageBus;
    }

#### Using SingleDestinationMessageSender

The [`SingleDestinationMessageSender` class](@platform-ref@/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/sender/SingleDestinationMessageSender.html)
wraps the Message Bus to send messages asynchronously. This class demonstrates using a `SynchronousMessageSender`:

    @Component(
        immediate = true,
        service = SomeServiceImpl.class
    )
    public class SomeServiceImpl {
        ...

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

The `_messageSenderFactory` field's `@Reference` wires it to a
`SingleDestinationMessageSenderFactory` instance. The method
`sendSomeMessage` creates a message, uses the `_messageSenderFactory` to
create a `SingleDestinationMessageSender` for the specified destination, and
sends the message through the sender. 

#### SynchronousMessageSender

A [`SynchronousMessageSender` instance](@platform-ref@/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/sender/SynchronousMessageSender.html)
sends a message to the Message Bus and blocks until receiving a response or it
times out. A `SynchronousMessageSender` has these
[operating modes](@platform-ref@/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/sender/SynchronousMessageSender.Mode.html):

-   `DEFAULT`: Delivers the message in a separate thread and also provides
    timeouts, in case the message is not delivered properly.

-   `DIRECT`: Delivers the message in the same thread of execution and blocks 
    until it receives a response. 

Here's an example of using `SynchronousMessageSender` in `DEFAULT` mode.

    @Component(
        immediate = true,
        service = SomeServiceImpl.class
    )
    public class SomeServiceImpl {
        ...

        public void sendSomeMessage() {

            Message message = new Message();
            message.put("myId", 12345);
            message.put("someAttribute", “abcdef”);

            SingleDestinationSynchronousMessageSender messageSender = 
                _messageSenderFactory.createSingleDestinationSynchronousMessageSender(
                    "myDestinationName", SynchronousMessageSender.MODE.DEFAULT);

            messageSender.send(message);

        }

        @Reference
        private SingleDestinationMessageSenderFactory _messageSenderFactory;
    }

### Sending Messages Across the Cluster

To ensure a message sent to a destination is received by all cluster nodes, you
must use a
[`ClusterBridgeMessageListener`](@platform-ref@/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/cluster/messaging/ClusterBridgeMessageListener.html).
It bridges the local destination to the cluster.

Here's a message listener registrator that bridges a destination for
distributing messages to all the cluster nodes.

    @Component(
        immediate = true,
        service = MyMessageListenerRegistrator.class
    )
    public class MyMessageListenerRegistrator {
        ...

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

The destination named `"liferay/live_users"` is bound to the `_destination` field. The `activate` method creates a `ClusterBridgeMessageListener`, sets its priority queue, and registers it to the destination. Messages sent to the destination are distributed across the cluster's JVMs. 

The [`com.liferay.portal.kernel.cluster.Priority` class](@platform-ref@/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/cluster/Priority.html)
has ten levels (`Level1` through `Level10`, with `Level 10` being the most prioritized). Each level is a priority queue for sending messages through the
cluster. This is similar in concept to thread priorities: `Thread.MIN_PRIORITY`, `Thread.MAX_PRIORITY`, and
`Thread.NORM_PRIORITY`. 

## Event Listeners

The Message Bus framework has interfaces that let you listen for new destinations and message listeners.  

### Listening for new Destinations

The Message Bus notifies Message Bus Event Listeners when destinations are
either added or removed. To register these listeners, publish a
[`MessageBusEventListener` instance](@platform-ref@/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/MessageBusEventListener.html)
to the OSGi service registry (e.g., via an `@Component` annotation).

    @Component(
        immediate = true,
        service = MessageBusEventListener.class
    )
    public class MyMessageBusEventListener implements MessageBusEventListener {

        void destinationAdded(Destination destination) {
            ...
        }

        void destinationDestroyed(Destination destination) {
            ...
        }
    }

### Listening for new Message Listeners

The Message Bus notifies
[`DestinationEventListener` instances](@platform-ref@/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/DestinationEventListener.html)
when
[`MessageListener`](@platform-ref@/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/MessageListener.html)s
are either registered or unregistered to destinations. To register these
listeners to a destination, publish a
[`DestinationEventListener` object](@platform-ref@/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/DestinationEventListener.html)
to the OSGi service registry along with the service property
`destination.name` assigned to the destination.

    @Component(
        immediate = true,
        property = {"destination.name=myCustom/Destination"},
        service = DestinationEventListener.class
    )
    public class MyDestinationEventListener implements DestinationEventListener {

        void messageListenerRegistered(String destinationName,
                                       MessageListener messageListener) {
            ...
        }

        void messageListenerUnregistered(String destinationName,
                                       MessageListener messageListener) {
            ...
        }
    }

## Service Builder Service Conveniences

Liferay has integrated some convenience facilities in Service Builder that
leverage the Message Bus, specifically `@Async` and `@Clusterable`.

### @Async Service Method Annotation

All Service Builder generated local and remote services (`*Service` and
`*LocalService`) are wrapped with special AOP (aspect-oriented programming)
advice. If you annotate a public method in your local or remote service
implementation with `@Async`, calls to the method are converted to asynchronous method calls.

This lets you quickly implement fire and forget capabilities to your services,
which is especially useful for features such as notifications.

### @Clusterable Service Method Annotation

Service Builder service class methods annotated with `@Clusterable` are invoked across the cluster. They use Message Bus and Cluster Link. The annotation can be further qualified with these attributes:

-   `onMaster`: whether to only execute the request if the current 
    portal JVM is holding the cluster wide "master" token

-   `acceptor`: specifies a custom `ClusterInvokeAcceptor` to determine 
    whether the given portal JVM should accept and thus execute the request.

TODO summary or closing sentences
