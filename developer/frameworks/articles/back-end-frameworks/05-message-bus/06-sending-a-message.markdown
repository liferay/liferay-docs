---
header-id: sending-a-message
---

# Sending a Message

[TOC levels=1-4]

Once you've 
[created a message](/docs/7-2/frameworks/-/knowledge_base/f/creating-a-message), 
there are three ways to send it with the 
[Message Bus](/docs/7-2/frameworks/-/knowledge_base/f/using-the-message-bus): 

-   [Directly with `MessageBus`](#directly-with-messagebus)
-   [Asynchronously with `SingleDestinationMessageSender`](#asynchronously-with-singledestinationmessagesender) 
-   [Synchronously with `SynchronousMessageSender`](#synchronously-with-synchronousmessagesender)

## Directly with MessageBus

To send a message directly with 
[`MessageBus`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/MessageBus.html), 
follow these steps: 

1.  Get a `MessageBus` reference: 

    ```java
    @Reference
    private MessageBus _messageBus;
    ```

2.  [Create a message](/docs/7-2/frameworks/-/knowledge_base/f/creating-a-message). 
    For example: 

    ```java
    Message message = new Message();
    message.put("myId", 12345);
    message.put("someAttribute", "abcdef");
    ```

3.  Call the `MessageBus` reference's `sendMessage` method with the destination 
    and message: 

    ```java
    _messageBus.sendMessage("myDestinationName", message);
    ```

Here's a class that contains this example: 

```java
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
```

## Asynchronously with SingleDestinationMessageSender

The 
[`SingleDestinationMessageSender`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/sender/SingleDestinationMessageSender.html) 
interface wraps the Message Bus to send messages asynchronously. Follow these 
steps to use this interface to send asynchronous messages: 

1.  Create a 
    [`SingleDestinationMessageSenderFactory`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/sender/SingleDestinationMessageSenderFactory.html) 
    reference: 

    ```java
    @Reference
    private SingleDestinationMessageSenderFactory _messageSenderFactory;
    ```

2.  Create a `SingleDestinationMessageSender` by calling the 
    `SingleDestinationMessageSenderFactory` reference's 
    `createSingleDestinationMessageSender` method with the message's 
    destination: 

    ```java
    SingleDestinationMessageSender messageSender = 
       _messageSenderFactory.createSingleDestinationMessageSender("myDestinationName");
    ```

3.  [Create a message](/docs/7-2/frameworks/-/knowledge_base/f/creating-a-message). 
    For example: 

    ```java
    Message message = new Message();
    message.put("myId", 12345);
    message.put("someValue", "abcdef");
    ```

4.  Send the message by calling the `SingleDestinationMessageSender` instance's 
    `send` method with the message: 

    ```java
    messageSender.send(message);
    ```

Here's a class that contains this example: 

```java
@Component(
    immediate = true,
    service = SomeServiceImpl.class
)
public class SomeServiceImpl {
    ...

    public void sendSomeMessage() {

        SingleDestinationMessageSender messageSender = 
           _messageSenderFactory.createSingleDestinationMessageSender("myDestinationName");

        Message message = new Message();
        message.put("myId", 12345);
        message.put("someValue", "abcdef");

        messageSender.send(message);
    }

    @Reference
    private SingleDestinationMessageSenderFactory _messageSenderFactory;
}
```

### Synchronously with SynchronousMessageSender

[`SynchronousMessageSender`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/sender/SynchronousMessageSender.html) 
sends a message to the Message Bus and blocks until receiving a response or the 
response times out. A `SynchronousMessageSender` has these 
[operating modes](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/sender/SynchronousMessageSender.Mode.html):

`DEFAULT`: Delivers the message in a separate thread and also provides timeouts, 
in case the message is not delivered properly. 

`DIRECT`: Delivers the message in the same thread of execution and blocks until 
it receives a response. 

Follow these steps to send a synchronous message with 
`SynchronousMessageSender`: 

1.  Get a 
    [`SingleDestinationMessageSenderFactory`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/sender/SingleDestinationMessageSenderFactory.html) 
    reference: 

    ```java
    @Reference
    private SingleDestinationMessageSenderFactory _messageSenderFactory;
    ```

2.  Create a `SingleDestinationSynchronousMessageSender` by calling the 
    `SingleDestinationMessageSenderFactory` reference's 
    `createSingleDestinationSynchronousMessageSender` method with the 
    destination and operating mode. Note that this example uses the `DEFAULT` 
    mode: 

    ```java
    SingleDestinationSynchronousMessageSender messageSender = 
        _messageSenderFactory.createSingleDestinationSynchronousMessageSender(
            "myDestinationName", SynchronousMessageSender.Mode.DEFAULT);
    ```

3.  [Create a message](/docs/7-2/frameworks/-/knowledge_base/f/creating-a-message). 
    For example: 

    ```java
    Message message = new Message();
    message.put("myId", 12345);
    message.put("someValue", "abcdef");
    ```

4.  Send the message by calling the `SingleDestinationSynchronousMessageSender` 
    instance's `send` method with the message: 

    ```java
    messageSender.send(message);
    ```

Here's a class that contains this example: 

```java
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
```

## Related Topics

[Creating a Message](/docs/7-2/frameworks/-/knowledge_base/f/creating-a-message) 

[Sending Messages Across a Cluster](/docs/7-2/frameworks/-/knowledge_base/f/sending-messages-across-a-cluster) 

[Using the Message Bus](/docs/7-2/frameworks/-/knowledge_base/f/using-the-message-bus) 
