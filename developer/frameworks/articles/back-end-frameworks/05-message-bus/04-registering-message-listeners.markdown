---
header-id: registering-message-listeners
---

# Registering Message Listeners

[TOC levels=1-4]

There are three ways to register a 
[message listener](/docs/7-2/frameworks/-/knowledge_base/f/using-the-message-bus#message-listeners) 
with the Message Bus: 

1.  [Automatic Registration as a Component](#automatic-registration-as-a-component)
2.  [Registering via a MessageBus Reference](#registering-via-a-messagebus-reference)
3.  [Registering Directly to the Destination](#registering-directly-to-the-destination)

Automatic registration as a component is the preferred way to register message 
listeners to destinations. You might want to use the other two ways if, for 
example, you want to create some special proxy wrappers. 

| **Note**: The 
| [`DestinationNames`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/DestinationNames.html) 
| class defines `String` constants for @product@'s preconfigured destinations. 

## Automatic Registration as a Component

You can specify a message listener in the 
[Declarative Services](/docs/7-2/frameworks/-/knowledge_base/f/declarative-services) 
`@Component` annotation: 

```java
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
```

The Message Bus listens for 
[`MessageListener`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/MessageListener.html) 
service components like this one to publish themselves to the OSGi service 
registry. The attribute `immediate = true` tells the OSGi framework to activate 
the component as soon as its dependencies resolve. Message Bus wires each 
registered listener to the destination its `destination.name` property 
specifies. If the destination is not yet registered, Message Bus queues the 
listener until the destination registers. 

## Registering via a MessageBus Reference

You can use a 
[`MessageBus`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/MessageBus.html) 
reference to directly register message listeners to destinations. Here's a 
registrator that demonstrates this: 

```java
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
```

The `_messageBus` field's `@Reference` annotation binds it to the `MessageBus` 
instance. The `activate` method creates the listener and uses the Message Bus to 
register the listener to a destination named `"myDestination"`. When this 
registrator component is destroyed, the `deactivate` method unregisters the 
listener. 

## Registering Directly to the Destination

You can use a 
[`Destination`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/Destination.html) 
reference to register a listener to that destination. Here's a registrator that 
demonstrates this: 

```java
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
```

The `_destination` field's `@Reference` annotation binds it to a destination
named `someDestination`. The `activate` method creates the listener and
registers it to the destination. When this registrator component is destroyed,
the `deactivate` method unregisters the listener. 

## Related Topics

[Using the Message Bus](/docs/7-2/frameworks/-/knowledge_base/f/using-the-message-bus)
