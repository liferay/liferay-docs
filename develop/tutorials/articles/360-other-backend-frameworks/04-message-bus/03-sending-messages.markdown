# Sending Messages [](id=sending-messages)

Message Bus lets you send messages to destinations that have any number of
listening classes. As a message sender you don't need to know the message
recipients. Instead, you focus on creating message content (payload) and sending
messages to destinations.

You can also send messages in a synchronous or asynchronous manner. The
synchronous option waits for a response that the message was received or that it
timed out. The asynchronous option gives you the "fire and forget" behavior;
send the message and continue processing without waiting for a response. 

Here are the message sending topics:

- [Creating a message](#creating-a-message) 
- [Sending a message](#sending-a-message) (the way you want) 
- [Sending messages across a cluster](#sending-messages-across-the-cluster)

Start by creating a message. 

## Creating a Message [](id=creating-a-message)

Here's how to create a message:

1.  Call the [`Message` constructor](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/Message.html).

    `Message message = new Message();`

2.  Populate the message with a `String` or `Object` payload

    -   String payload: `message.setPayload("Message Bus is great!")`

    -   Object payload: `message.put("firstName", "Joe")`

3.  To receive responses at a particular location, set both of these attributes

    -  Response destination name: `setResponseDestinationName(String)`

    -  Response ID: `setResponseId(String)`

Your new message is ready to send. 

## Sending a Message [](id=sending-a-message)

Here are the ways to send a message: 

-  [Directly using the `MessageBus`](#directly-using-the-message-bus)
-  [Asynchronously using a `SingleDestinationMessageSender`](#using-singledestinationmessagesender) 
-  [Using a `SynchronousMessageSender`](#using-a-synchronousmessagesender)

First, let's consider using Message Bus directly. 

### Directly Using the Message Bus [](id=directly-using-the-message-bus)

This method involves obtaining a [`MessageBus` instance](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/MessageBus.html)
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
            message.put("someAttribute", "abcdef");
            _messageBus.sendMessage("myDestinationName", message);
        }

        @Reference
        private MessageBus _messageBus;
    }

To send messages asynchronously, consider using
`SingleDestinationMessageSender`.
 
### Using SingleDestinationMessageSender [](id=using-singledestinationmessagesender)

The [`SingleDestinationMessageSender` class](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/sender/SingleDestinationMessageSender.html)
wraps the Message Bus to send messages asynchronously. This class demonstrates
using a `SingleDestinationMessageSender`:

    @Component(
        immediate = true,
        service = SomeServiceImpl.class
    )
    public class SomeServiceImpl {
        ...

        public void sendSomeMessage() {

            Message message = new Message();
            message.put("myId", 12345);
            message.put("someValue", "abcdef");

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

### Using a SynchronousMessageSender [](id=using-a-synchronousmessagesender)

A [`SynchronousMessageSender` instance](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/sender/SynchronousMessageSender.html)
sends a message to the Message Bus and blocks until receiving a response or the
response times out. A `SynchronousMessageSender` has these
[operating modes](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/sender/SynchronousMessageSender.Mode.html):

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
            message.put("someAttribute", "abcdef");

            SingleDestinationSynchronousMessageSender messageSender = 
                _messageSenderFactory.createSingleDestinationSynchronousMessageSender(
                    "myDestinationName", SynchronousMessageSender.Mode.DEFAULT);

            messageSender.send(message);

        }

        @Reference
        private SingleDestinationMessageSenderFactory _messageSenderFactory;
    }

And those are the ways to send messages. Next, if you're in a cluster and want
messages sent to a destination across all nodes, you must register a bridge
message listener to that destination. 

## Sending Messages Across the Cluster [](id=sending-messages-across-the-cluster)

To ensure a message sent to a destination is received by all cluster nodes, you
must register a
[`ClusterBridgeMessageListener`](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/cluster/messaging/ClusterBridgeMessageListener.html)
at that destination. This bridges the local destination to the cluster.

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

The destination named `"liferay/live_users"` is bound to the `_destination`
field. The `activate` method creates a `ClusterBridgeMessageListener`, sets its
priority queue, and registers it to the destination. Messages sent to the
destination are distributed across the cluster's JVMs. 

The [`com.liferay.portal.kernel.cluster.Priority` class](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/cluster/Priority.html)
has ten levels (`Level_1` through `Level_10`, with `Level 10` being the most
important). Each level is a priority queue for sending messages through the
cluster. This is similar in concept to thread priorities: `Thread.MIN_PRIORITY`,
`Thread.MAX_PRIORITY`, and `Thread.NORM_PRIORITY`. 

That concludes the tour on sending messages. You've learned how to create
messages, send messages synchronously and asynchronously, and send messages to a
destination in a clustered environment. 

## Related Topics [](id=related-topics)

[Messaging Destinations](/develop/tutorials/-/knowledge_base/7-0/messaging-destinations) 

[Message Listeners](/develop/tutorials/-/knowledge_base/7-0/message-listeners)
