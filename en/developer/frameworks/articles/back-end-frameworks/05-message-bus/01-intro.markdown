---
header-id: using-the-message-bus
---

# Using the Message Bus

[TOC levels=1-4]

Here, you'll learn how to use the 
[Message Bus](/docs/7-2/frameworks/-/knowledge_base/f/back-end-frameworks#message-bus) 
to send and receive messages in the portal. The following topics are covered: 

-   [Messaging Destinations](#messaging-destinations)
-   [Message Listeners](#message-listeners)
-   [Sending Messages](#sending-messages)

## Messaging Destinations

In Message Bus, you send messages to destinations. A destination is a named 
logical (not physical) location. Sender classes send messages to destinations, 
while listener classes wait to receive messages at the destinations. In this 
way, the sender and recipient don't need to know each other---they're loosely
coupled. 

### Destination Configuration

Each destination has a name and type and can have several other attributes. The 
destination type determines these things: 

-   Whether there's a message queue. 
-   The kinds of threads involved with a destination. 
-   The message delivery behavior to expect at the destination. 

Here are the primary destination types:

**Parallel Destination**

-   Messages sent here are queued.
-   Multiple worker threads from a thread pool deliver each message to a 
    registered message listener. There's one worker thread per message per 
    message listener. 

**Serial Destination**

-   Messages sent here are queued. 
-   Worker threads from a thread pool deliver the messages to each registered 
    message listener, one worker thread per message. 

**Synchronous Destination**

-   Messages sent here are directly delivered to message listeners. 
-   The thread sending the message here also delivers the message to all message 
    listeners. 

Preconfigured destinations exist for various purposes. The 
[`DestinationNames`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/DestinationNames.html)
class defines `String` constants for each. For example, 
`DestinationNames.HOT_DEPLOY` (value is `"liferay/hot_deploy"`) is for 
deployment event messages. Since destinations are tuned for specific purposes, 
don't modify them. 

Destinations are based on 
[`DestinationConfiguration`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/DestinationConfiguration.html) 
instances. The configuration specifies the destination type, name, and these 
destination-related attributes: 

**Maximum Queue Size**: Limits the number of the destination's queued messages. 

**Rejected Execution Handler**: A 
[`RejectedExecutionHandler`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/concurrent/RejectedExecutionHandler.html) 
instance can take action (e.g., log warnings) regarding rejected messages when 
the destination queue is full. 

**Workers Core Size**: Initial number of worker threads for processing messages. 

**Workers Max Size**: Limits the number of worker threads for processing 
messages. 

The `DestinationConfiguration` class provides these static methods for creating 
the various types of configurations. 

-   `createParallelDestinationConfiguration(String destinationName)`
-   `createSerialDestinationConfiguration(String destinationName)`
-   `createSynchronousDestinationConfiguration(String destinationName)`

You can also use the `DestinationConfiguration` 
[constructor](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/DestinationConfiguration.html#DestinationConfiguration-java.lang.String-java.lang.String-) 
to create a configuration for any destination type, even your own. 

For instructions on creating your own destination, see 
[Creating a Destination](/docs/7-2/frameworks/-/knowledge_base/f/creating-a-destination). 

## Message Listeners

If you're interested in messages sent to a destination, you need to *listen* for 
them. That is, you must create and register a message listener for the 
destination. 

To create a message listener, implement the
[`MessageListener`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/MessageListener.html) 
interface and override its `receive(Message)` method to process messages your 
way. 

```java
public void receive(Message message) {
    // Process messages your way
}
```

Here are the ways to register your listener with Message Bus: 

**Automatic Registration as a Component**: Publish the listener to the OSGi 
registry as a 
[Declarative Services](/docs/7-2/frameworks/-/knowledge_base/f/declarative-services) 
component that specifies a destination. Message Bus automatically wires the 
listener to the destination. 

**Registering via MessageBus**: Obtain and use a 
[`MessageBus`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/MessageBus.html) 
reference to directly register the listener to a destination. 

**Registering Directly to a Destination**: Obtain a reference to a specific 
destination and use it to directly register the listener with that destination. 

For instructions on these topics, see 
[Registering Message Listeners](/docs/7-2/frameworks/-/knowledge_base/f/registering-message-listeners). 

## Sending Messages

Message Bus lets you send messages to destinations that have any number of 
listening classes. As a message sender you don't need to know the message 
recipients. Instead, you focus on creating message content (payload) and sending 
messages to destinations. 

You can also send messages in a synchronous or asynchronous manner. The 
synchronous option waits for a response that the message was received or that it 
timed out. The asynchronous option gives you the "fire and forget" behavior; 
send the message and continue processing without waiting for a response. 

See these topics for instructions on creating and sending messages: 

- [Creating a Message](/docs/7-2/frameworks/-/knowledge_base/f/creating-a-message) 
- [Sending a Message](/docs/7-2/frameworks/-/knowledge_base/f/sending-a-message) 
- [Sending Messages Across a Cluster](/docs/7-2/frameworks/-/knowledge_base/f/sending-messages-across-a-cluster) 

