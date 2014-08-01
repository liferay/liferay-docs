## Using Liferay's Message Bus 

The *Message Bus* is a service level API used to exchange messages within 
Liferay. The Message Bus is a mechanism for sending message payloads to 
different components in Liferay, providing loose coupling between message 
producers and consumers to prevent class loading issues. It's located in the 
global class loader, making it accessible to every deployed web application. 
Remote messaging isn't supported, but messages are sent across a cluster when 
ClusterLink is enabled. 

Message Bus has several common uses, including sending search index write
events, sending subscription emails, handling messages at scheduler endpoints,
and running asynchronous processes. You can leverage Message Bus to send 
messages between and within your plugins. 

As you read through the Message Bus tutorials, you'll learn about things like 
*synchronous* and *asynchronous* messaging , *serial* vs. *in-parallel* message 
dispatching, and Java and JSON style messages formats. 

Before you get into those topics, you should first learn about the Message Bus 
System's architecture. 

## Next Steps 

[Service Builder and Services](/develop/tutorials/-/knowledge_base/service-builder-lp-6-2-develop-tutorial)

<!--
[Liferay's Message Bus System](http://www.liferay.com/)

[Synchronous Messaging](http://www.liferay.com/)

[Asynchronous Messaging with Callbacks](http://www.liferay.com/)

[Asynchronous Send and Forget Messaging](http://www.liferay.com/)
-->
