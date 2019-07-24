---
header-id: message-bus-event-listeners
---

# Message Bus Event Listeners

[TOC levels=1-4]

When 
[using Message Bus](/docs/7-2/frameworks/-/knowledge_base/f/using-the-message-bus), 
you may wish to listen for events that take place within the Message Bus 
framework itself, independent of messages. For example, you can listen for when 
[destinations](/docs/7-2/frameworks/-/knowledge_base/f/using-the-message-bus#messaging-destinations) 
and 
[message listeners](/docs/7-2/frameworks/-/knowledge_base/f/using-the-message-bus#message-listeners) 
are added or removed. Here, you'll learn how. 

## Listening for Destinations

Message Bus notifies event listeners when destinations are added and removed. To 
register these listeners, publish a 
[`MessageBusEventListener`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/MessageBusEventListener.html) 
instance to the OSGi service registry (e.g., via an `@Component` annotation).

Here's an example implementation of `MessageBusEventListener`. Use the 
`destinationAdded` and `destinationDestroyed` methods to implement any logic 
that you want to run when a destination is added or removed, respectively: 

```java
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
```

## Listening for Message Listeners

Message Bus notifies 
[`DestinationEventListener`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/DestinationEventListener.html) 
instances when message listeners for destinations are either registered or 
unregistered. To register an event listener to a destination, publish a 
`DestinationEventListener` service to the OSGi service registry, making sure to 
specify the destination's `destination.name` property. 

```java
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
```

## Related Topics

[Using the Message Bus](/docs/7-2/frameworks/-/knowledge_base/f/using-the-message-bus)
