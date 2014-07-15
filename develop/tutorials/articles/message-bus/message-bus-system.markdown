### The Message Bus System [](id=the-message-bus-system-liferay-portal-6-2-dev-guide-06-en)

The Message Bus system contains the following components: 

- **Message Bus**: Manages transfer of messages from message *senders* to
  message *listeners*. 
- **Destinations**: Addresses or endpoints to which *listeners* register to
  receive messages. 
- **Listeners**: Consume messages received at destinations. They receive all
  messages sent to their registered destinations. 
- **Senders**: Invoke the Message Bus to send messages to destinations. 

Your services can send messages to one or more destinations, and can listen
to one or more destinations. The figure below depicts this. An individual
service can be both a message sender and a message listener. For example, in the
figure below both *Plugin 2 - Service 3* and *Plugin 5 - Service 7* send and
listen for messages. 

![Figure 7.7: Example, Message Bus system](../../images/msg-bus-system.png)

The Message Bus supports *synchronous* and *asynchronous* messaging: 

- **Synchronous messaging**: After it sends a message, the sender blocks waiting
  for a response from a recipient. 
- **Asynchronous messaging**: After it sends a message, the sender is free to
  continue processing. The sender can be configured to receive a call-back or
  can simply send and forget. We'll show you how to implement both synchronous
  and asynchronous messaging in this section. 
    - **Call-back**: The sender can include a call-back destination key as the
      response destination for the message. The recipient (listener) can then
      send a response message back to the sender via this response
      destination. 
    - **Send-and-Forget**: The sender includes no call-back information in the
      message sent and continues with processing. 

Configuration of Message Bus is done using the following files:

- `WEB-INF/src/META-INF/messaging-spring.xml`: Specifies your destinations,
  listeners, and their mappings to each other. 
- `WEB-INF/web.xml`: Holds a listing of deployment descriptors for your plugin.
  Make sure you add `messaging-spring.xml` to your list of Spring configurations
  in this file. 

---

![note](../../images/tip-pen-paper.png) **Note:** The internal file
`META-INF/messaging-core-spring.xml` of `portal-impl.jar` specifies the default
Message Bus class, default asynchronous message sender class, and default
synchronous message sender class for Liferay. 

---

You can control your *Message Types* by using either the `Message` or
`JSONObject` class. Liferay core services are typically serialized and
deserialized in JSON. In our examples we'll demonstrate both types of message
classes. 

So far we've introduced the Message Bus System, including message types,
destinations, senders, listeners, and approaches to sending messages. Next we'll
show you how easy it is to create your destinations, register listeners, and
send your messages. To demonstrate, we'll implement a business use case.
