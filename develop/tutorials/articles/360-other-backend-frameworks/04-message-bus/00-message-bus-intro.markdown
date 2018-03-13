# Message Bus [](id=message-bus)

If you ever need to do some data processing outside the scope of the web's
request/response, look no further than the Message Bus. It's conceptually
similar to Java Messaging Service (JMS) Topics, but sacrifices transactional,
reliable delivery capabilities, making it much lighter-weight. @product@ uses
Message Bus all over the place: 

- Auditing
- Search engine integration
- Email subscriptions
- Monitoring
- Document Library processing
- Background tasks
- Cluster-wide request execution
- Clustered cache replication

You can use it too! Here are some of Message Bus's most important features:

-   publish/subscribe messaging 
-   request queuing and throttling
-   flow control
-   multi-thread message processing

There are also tools, such as the Java SE's JConsole, that can monitor Message
Bus activities. 

![Figure 1: JConsole shows statistics on Message Bus messages sent, messages pending, and more.](../../../images/message-bus-jconsole.png)

The Message Bus topics are covered in these tutorials:

- [Messaging Destinations](/develop/tutorials/-/knowledge_base/7-0/messaging-destinations) 
- [Message Listeners](/develop/tutorials/-/knowledge_base/7-0/message-listeners) 
- [Sending Messages](/develop/tutorials/-/knowledge_base/7-0/sending-messages) 

Since all messages are sent to and received at destinations, messaging
destinations is worth exploring first. 
