# Message Bus [](id=using-liferays-message-bus)

Liferay's *Message Bus* is a service level API that components can use to send
and receive messages. It provides loose coupling between message producers and
consumers. The Message Bus is located in Liferay's global class loader, making
it accessible to every deployed web application. Remote messaging isn't
supported, but messages are sent across a cluster when ClusterLink is enabled. 

Here are a few common uses of the Message Bus:

- Sending search index write events
- Sending subscription emails
- Handling messages at scheduler endpoints
- Running asynchronous processes

You can leverage the Message Bus to send messages between and within your
plugins. 

As you read through the Message Bus tutorials, you'll learn about *synchronous*
and *asynchronous* messaging, *serial* vs. *in-parallel* message dispatching,
and how to style message formats. 

Before you get into those topics, you should first learn about the Message Bus 
system's architecture. 

## Related Topics [](id=related-topics)

[Service Builder and Services](/tutorials/-/knowledge_base/6-2/service-builder)

[Developing with the Plugins SDK](/tutorials/-/knowledge_base/6-2/plugins-sdk)

[Developing Plugins with Liferay IDE](/tutorials/-/knowledge_base/6-2/liferay-ide)

[Developing with Maven](/tutorials/-/knowledge_base/6-2/maven)
