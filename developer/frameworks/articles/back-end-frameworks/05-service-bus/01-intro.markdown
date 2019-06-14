# Using the Service Bus

Here, you'll learn how to use the 
[Service Bus](/docs/7-2/frameworks/-/knowledge_base/f/back-end-frameworks#service-bus) 
to send and receive messages in the portal. The following topics are covered: 

-   [Messaging Destinations](#messaging-destinations)
-   Message Listeners
-   Sending Messages

## Messaging Destinations

In Service Bus, you send messages to destinations. A destination is a named 
logical (not physical) location. Sender classes send messages to destinations, 
while listener classes wait to receive messages at the destinations. In this 
way, the sender and recipient don't need to know each other---they're loosely
coupled. 

### Destination Configuration

Each destination has a name and type and can have several other attributes. The 
destination type determines the following: 

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
`DestinationNames.HOT_DEPLOY` (value is  `"liferay/hot_deploy"`) is for 
deployment event messages. Since destinations are tuned for specific purposes, 
don't modify them. 

Destinations are based on 
[`DestinationConfiguration`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/DestinationConfiguration.html) 
instances. The configuration specifies the destination type, name, and these 
destination-related attributes: 

**Maximum Queue Size**: Limits the number of the destination's queued messages. 

**Rejected Execution Handler**: A 
[`com.liferay.portal.kernel.concurrent.RejectedExecutionHandler`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/concurrent/RejectedExecutionHandler.html) 
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


