# Message Bus [](id=message-bus)

Are you looking for a lightweight way for your apps and components to
communicate? Message Bus offers just that and comes with @product@ out of the
box. It is conceptually similar to Java Messaging Service (JMS) Topics, but
sacrifices transactional, reliable delivery capabilities. Here are some of
Message Bus's most popular features:

-   publish/subscribe messaging 
-   request queuing and throttling
-   flow control
-   multi-thread message processing

And here are some of the ways @product@ uses Message Bus:

- Auditing
- Search engine integration
- Email subscriptions
- Monitoring
- Document Library processing
- Background tasks
- Cluster-wide request execution
- Clustered cache replication

There are also tools, such as the Java SE's JConsole, that can monitor Message
Bus activities. 

![Figure 1: JConsole shows statistics on Message Bus messages sent, messages pending, and more.](../../../images/message-bus-jconsole.png)

The Message Bus topics are covered in these tutorials:

- [Messaging Destinations](/develop/tutorials/-/knowledge_base/7-0/messaging-destinations) 
- [Message Listeners](/develop/tutorials/-/knowledge_base/7-0/message-listeners) 
- [Sending Messages](/develop/tutorials/-/knowledge_base/7-0/sending-messages) 

Since all messages are sent to and received at destinations, messaging
destinations is worth exploring first. 
