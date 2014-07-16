## Using Message Bus [](id=using-message-bus-liferay-portal-6-2-dev-guide-06-en)

The *Message Bus* is a service level API used to exchange messages within
Liferay. The Message Bus is a mechanism for sending message payloads to
different components in Liferay, providing loose coupling between message
producers and consumers to prevent class loading issues. It's located in
the global class loader, making it accessible to every deployed web application.
Remote messaging isn't supported, but messages are sent across a cluster
when ClusterLink is enabled. 

Message Bus has several common uses, including sending search index write
events, sending subscription emails, handling messages at scheduler endpoints,
and running asynchronous processes. 

You can leverage Message Bus to send messages between and within your plugins. 

As we show you Message Bus we'll talk about things like *synchronous* and
*asynchronous* messaging , *serial* vs. *in-parallel* message dispatching, and
Java and JSON style messages formats. 

Before we get into those topics, let's first try to understand Message Bus
System's architecture. 
