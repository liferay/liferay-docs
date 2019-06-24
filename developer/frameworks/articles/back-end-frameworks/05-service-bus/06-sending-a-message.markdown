# Sending a Message

Once you've 
[created a message](/docs/7-2/frameworks/-/knowledge_base/f/creating-a-message), 
there are three ways to send it with the 
[Service Bus](/docs/7-2/frameworks/-/knowledge_base/f/using-the-service-bus): 

-   [Directly with `MessageBus`](#directly-with-messagebus)
-   [Asynchronously with `SingleDestinationMessageSender`](#asynchronously-with-singledestinationmessagesender) 
-   [Synchronously with `SynchronousMessageSender`](#synchronously-with-synchronousmessagesender)

## Directly with MessageBus

To send a message directly with 
[`MessageBus`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/MessageBus.html), 
you must get a `MessageBus` reference and call its `sendMessage` method with the 
destination and message. 

This example creates a message and then invokes the `MessageBus` reference's 
`sendMessage` method: 

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
interface wraps the Service Bus to send messages asynchronously. To use it, 
create a 
[`SingleDestinationMessageSenderFactory`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/sender/SingleDestinationMessageSenderFactory.html) 
reference and call its 

This class 
demonstrates using a `SingleDestinationMessageSender`: 

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

### Synchronously with SynchronousMessageSender

A [`SynchronousMessageSender` instance](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/sender/SynchronousMessageSender.html)
sends a message to the Message Bus and blocks until receiving a response or the
response times out. A `SynchronousMessageSender` has these
[operating modes](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/sender/SynchronousMessageSender.Mode.html):

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
