---
header-id: creating-a-message
---

# Creating a Message

[TOC levels=1-4]

Before you can 
[send a message](/docs/7-2/frameworks/-/knowledge_base/f/using-the-message-bus#sending-messages) 
via the Message Bus, you must first create it. Here's how to create a message: 

1.  Call the
    [`Message`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/Message.html) 
    constructor to create a new `Message`: 

    ```java
    Message message = new Message();
    ```

2.  Populate the message with a `String` or `Object` payload:

    -   String payload: `message.setPayload("Message Bus is great!")`

    -   Object payload: `message.put("firstName", "Joe")`

3.  To receive responses at a particular location, set both of these attributes: 

    -  Response destination name: `setResponseDestinationName(String)`

    -  Response ID: `setResponseId(String)`

## Related Topics

[Sending a Message](/docs/7-2/frameworks/-/knowledge_base/f/sending-a-message)

[Sending Messages Across a Cluster](/docs/7-2/frameworks/-/knowledge_base/f/sending-messages-across-a-cluster) 

[Using the Message Bus](/docs/7-2/frameworks/-/knowledge_base/f/using-the-message-bus) 
