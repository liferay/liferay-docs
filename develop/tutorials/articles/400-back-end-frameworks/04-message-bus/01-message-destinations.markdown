# Messaging Destinations [](id=messaging-destinations)

In Message Bus, you send messages to *destinations*. A destination is a named
logical (not physical) location. Sender classes send messages to destinations,
while listener classes wait to receive messages at the destinations. In this
way, the sender and recipient don't need to know each other---they're loosely
coupled. Here are the messaging destination topics this tutorial covers:

- [Destination configuration](#destination-configuration)
- [Creating a destination](#creating-a-destination)
- [Messaging event listeners](#messaging-event-listeners)

It's time to configure a destination.

## Destination Configuration [](id=destination-configuration)

Each destination has a name and type and can have several other attributes. The
destination type determines whether there's a message queue, the kinds of
threads involved with a destination, and the message delivery behavior to expect
at the destination. 

Here are the primary destination types:

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

Liferay has preconfigured destinations for various purposes. The 
[`DestinationNames` class](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/DestinationNames.html)
defines `String` constants for each of them. For example,
`DestinationNames.HOT_DEPLOY` (value is  `"liferay/hot_deploy"`) is for
deployment event messages. Since destinations are tuned for specific purposes,
don't modify them. 

Destinations are based on 
[`DestinationConfiguration`
instances](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/DestinationConfiguration.html).
The configuration specifies the destination type, name, and these destination-
related attributes: 

**Maximum Queue Size**: limits the number of queued messages for the
destination. 

**Rejected Execution Handler**: A 
[`com.liferay.portal.kernel.concurrent.RejectedExecutionHandler` instance](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/concurrent/RejectedExecutionHandler.html)
can take action (e.g., log warnings) regarding rejected messages when the
destination queue is full. 

**Workers Core Size**: initial number of worker threads for processing
messages.

**Workers Max Size**: limits the number of worker threads for processing 
messages.

The `DestinationConfiguration` class provides these static methods for creating
the various types of configurations. 

- `createParallelDestinationConfiguration(String destinationName)`

- `createSerialDestinationConfiguration(String destinationName)`

- `createSynchronousDestinationConfiguration(String destinationName)`

You can also use the [`DestinationConfiguration` constructor](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/DestinationConfiguration.html#DestinationConfiguration-java.lang.String-java.lang.String-)
to create a configuration for any destination type, even your own.

## Creating a Destination [](id=creating-a-destination)

Message Bus destinations are based on destination configurations and
registered as OSGi services. Message Bus detects the destination services and
manages their associated destinations.

Here are the general steps for creating a destination. The example configurator
class that follows demonstrates these steps.

1.  Create a destination configuration using one of 
    `DestinationConfiguration`'s static `create*` methods or its constructor.
    Set any attributes that apply to the destinations you'll create with it. 

2.  Create a destination by invoking the 
    [`DestinationFactory`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/DestinationFactory.html)
    method `createDestination(DestinationConfiguration)`, passing in the
    destination configuration you created in the previous step. 

3.  Register the destination as an OSGi service by invoking the
    [`BundleContext` method `registerService`](https://osgi.org/javadoc/r4v43/core/org/osgi/framework/BundleContext.html), passing in the following parameters.
    -   Destination class `Destination.class`
    -   Your `Destination` object
    -   A `Dictionary` of properties defining the destination, including the 
        `destination.name` 

4.  Manage the destination object and service registration resources using a
    collection, such as a `Map<String, ServiceRegistration<Destination>>`.
    Keeping references to these resources is helpful for when you're ready to
    unregister and destroy them. The `deactivate` method in the example below
    demonstrates this.

Here's an example messaging configurator component that creates and registers a
parallel destination and manages its resources:

    @Component (
        immediate = true,
        service = MyMessagingConfigurator .class
    )
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

On activation, the example configurator above does these things:

1.  Creates a `DestinationConfiguration` for parallel destinations.

2.  Sets the `DestinationConfiguration`'s max queue size and a rejected
    execution handler. 

3.  Uses the `DestinationFactory` (the one bound to the `_destinationFactory`
    field) to create the destination. 

4.  Adds the destination to the OSGi service registry

5.  Adds the destination service registration to a map for managing them. 

Once the destination is registered, Message Bus detects its service and manages
the destination. On deactivating the example configurator, its `deactivate`
method unregisters the destination services and destroys the destinations. 

As an added bonus to creating destinations, you can create classes that listen
for new destinations and new message listeners. You might want to create such
listeners to log the deployment of new message bus endpoints.

## Messaging Event Listeners [](id=messaging-event-listeners)

There are Message Bus framework interfaces that let you listen for new
destinations and message listeners. 

### Listening for new Destinations [](id=listening-for-new-destinations)

The Message Bus notifies Message Bus Event Listeners when destinations are
added and removed. To register these listeners, publish a
[`MessageBusEventListener` instance](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/MessageBusEventListener.html)
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

Listening for new message listeners is easy too. 

### Listening for new Message Listeners [](id=listening-for-new-message-listeners)

The Message Bus notifies 
[`DestinationEventListener` instances](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/DestinationEventListener.html)
when message listeners are either registered or unregistered to destinations. To
register a listener to a destination, publish a 
[`DestinationEventListener` service](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/DestinationEventListener.html)
to the OSGi service registry, making sure to specify the destination's
`destination.name` property.

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

And that's how you listen for new destinations and message listeners. 

Now you understand the different destination types, how to create and register
destinations, and how to manage destination resources. Once you deploy your
destination, registered message listeners receive messages sent to it. 

## Related Topics [](id=related-topics)

[Message Listeners](/develop/tutorials/-/knowledge_base/7-1/message-listeners) 

[Sending Messages](/develop/tutorials/-/knowledge_base/7-1/sending-messages)
