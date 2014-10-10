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

## Related Topics

[Service Builder and Services](/tutorials/-/knowledge_base/6-2/service-builder)

[Developing with the Plugins SDK](/tutorials/-/knowledge_base/6-2/plugins-sdk)

[Developing Plugins with Liferay IDE](/tutorials/-/knowledge_base/6-2/liferay-ide)

[Developing with Maven](/tutorials/-/knowledge_base/6-2/maven)
