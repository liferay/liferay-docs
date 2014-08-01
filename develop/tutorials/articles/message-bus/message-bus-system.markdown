# Liferay's Message Bus System 

Using Liferay's Message Bus system is a great way to handle communication both 
between and within your applications. However, before you get started with 
Message Bus, you should know the basics of how it's structured. This tutorial 
presents information on the components of the Message Bus system, as well as the 
different types of messages that can be sent. Read on to fire up the wheels on 
the Message Bus! 

## Message Bus System Components 

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

![Figure 1: Example, Message Bus system](../../images/msg-bus-system.png)

## Message Types 

The Message Bus supports *synchronous* and *asynchronous* messaging: 

- **Synchronous messaging**: After it sends a message, the sender blocks the 
  thread, waiting for a response from a recipient. 
- **Asynchronous messaging**: After it sends a message, the sender is free to
  continue processing. The sender can be configured to receive a call-back or 
  simply send and forget. 
    - **Call-back**: The sender includes a call-back destination key to serve as 
      the destination for the response message. The recipient (listener) then
      send a response message back to the sender via this response destination. 
    - **Send-and-Forget**: The sender includes no call-back information in the
      message sent. 

Configuring Message Bus is done using the following files: 

- `WEB-INF/src/META-INF/messaging-spring.xml`: Specifies your destinations,
  listeners, and their mappings to each other. 
- `WEB-INF/web.xml`: Holds a listing of deployment descriptors for your plugin.
  You need to add `messaging-spring.xml` to your list of Spring configurations
  in this file. 

---

![note](../../images/tip-pen-paper.png) **Note:** The internal file
`META-INF/messaging-core-spring.xml` of `portal-impl.jar` specifies the default
Message Bus class, default asynchronous message sender class, and default 
synchronous message sender class for Liferay. 

---

You can control your *Message Types* by using either the `Message` or
`JSONObject` class. Liferay core services are typically serialized and
deserialized in JSON. Both types of message classes are used in the following 
tutorials to show you how to implement both synchronous and asynchronous 
messaging. 

## Next Steps 

[Service Builder and Services](/develop/tutorials/-/knowledge_base/service-builder-lp-6-2-develop-tutorial)

<!--
[Synchronous Messaging](http://www.liferay.com/)

[Asynchronous Messaging with Callbacks](http://www.liferay.com/)

[Asynchronous Send and Forget Messaging](http://www.liferay.com/)
-->
