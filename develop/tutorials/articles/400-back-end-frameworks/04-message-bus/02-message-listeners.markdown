# Message Listeners [](id=message-listeners)

If you're interested in messages sent to a destination, you need to "listen" for
them. That is, you must create and register a message listener for the
destination. 

To create a message listener, implement the
[`MessageListener` interface](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/MessageListener.html)
and override its `receive(Message)` method to process messages your way. 

    public void receive(Message message) {
        // Process messages your way
    }

Here are the ways to register your listener with Message Bus:

-   **Automatic Registration as a Component**: Publish the listener to the OSGi
    registry as a
    [Declarative Services Component](/develop/tutorials/-/knowledge_base/7-1/osgi-services-and-dependency-injection-with-declarative-services)
    that specifies a destination. Message Bus automatically wires the listener
    to the destination.

-   **Registering via MessageBus**: Obtain a reference to the Message Bus and
    use it directly to register the listener to a destination.

-   **Registering directly to a Destination**: Obtain a reference to a 
    specific destination and use it directly to register the listener with that
    destination.

+$$$

**Note**: The
[`DestinationNames` class](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/DestinationNames.html)
defines `String` constants for @product@'s preconfigured destinations. 

$$$

The Declarative Services component module provides the easiest way to register a message listener. 

## Automatic Registration as a Component [](id=automatic-registration-as-a-component)

You can specify a message listener in the Declarative Services (DS) `@Component`
annotation: 

    @Component (
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
 
The Message Bus listens for `MessageListener` service components like this one
to publish themselves to the OSGi service registry. The attribute `immediate =
true` tells the OSGi framework to activate the component as soon as its
dependencies resolve. Message Bus wires each registered listener to the
destination its `destination.name` property specifies. If the destination is not
yet registered, Message Bus queues the listener until the destination registers.

Registration as a component is the preferred way to register message listeners
to destinations.

## Registering via MessageBus [](id=registering-via-messagebus)

You can use the
[`MessageBus` instance](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/MessageBus.html)
directly to register message listeners to destinations. You might want to do
this if, for example, you want to create some special proxy wrappers. Here's a
registrator that demonstrates registering a listener this way:

    @Component (
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

## Registering directly to the Destination [](id=registering-directly-to-the-destination)

You can use a `Destination` instance to register a listener to that destination.
You might want to do this if, for example, you want to create some special proxy
wrappers. Here's a registrator that demonstrates registering a listener this
way:

    @Component (
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

Now you know how to create and register message listeners for receiving
messages sent to the destinations.

## Related Topics [](id=related-topics)

[Messaging Destinations](/develop/tutorials/-/knowledge_base/7-1/messaging-destinations) 

[Sending Messages](/develop/tutorials/-/knowledge_base/7-1/sending-messages)
