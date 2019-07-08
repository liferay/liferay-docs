---
header-id: sending-messages-across-a-cluster
---

# Sending Messages Across a Cluster

[TOC levels=1-4]

To ensure a message sent to a destination is received by all cluster nodes, you 
must register a 
[`ClusterBridgeMessageListener`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/cluster/messaging/ClusterBridgeMessageListener.html) 
at that destination. This bridges the local destination to the cluster and 
ensures that messages sent to the destination are distributed across the 
cluster's JVMs. You should do this in a registrator class, like those shown in 
[Registering Message Listeners](/docs/7-2/frameworks/-/knowledge_base/f/registering-message-listeners). 

Follow these steps to create a registrator class that registers a 
`ClusterBridgeMessageListener` to a destination: 

1.  Create the registrator class as an OSGi component: 

    ```java
    @Component(
        immediate = true,
        service = MyMessageListenerRegistrator.class
    )
    public class MyMessageListenerRegistrator {
        ...
    }
    ```

2.  Create a 
    [`MessageListener`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/MessageListener.html) 
    variable: 

    ```java
    private MessageListener _clusterBridgeMessageListener;
    ```

3.  Create a 
    [`Destination`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/messaging/Destination.html) 
    reference and set its `destination.name` property to your destination. For 
    example, this reference is for the destination `liferay/live_users`: 

    ```java
    @Reference(target = "(destination.name=liferay/live_users)")
    private Destination _destination;
    ```

4.  In the registrator's `activate` method, create a new 
    `ClusterBridgeMessageListener` and set it to the `MessageListener` variable 
    you created earlier. Then set the `ClusterBridgeMessageListener`'s priority 
    and register the `ClusterBridgeMessageListener` to the destination: 

    ```java
    @Activate
    protected void activate() {

        _clusterBridgeMessageListener = new ClusterBridgeMessageListener();
        _clusterBridgeMessageListener.setPriority(Priority.LEVEL5)
        _destination.register(_clusterBridgeMessageListener);
    }
    ```

    The 
    [`Priority`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/cluster/Priority.html)
    enum has ten levels (`Level1` through `Level10`, with `Level10` being the 
    most important). Each level is a priority queue for sending messages through 
    the cluster. This is similar in concept to thread priorities: 
    `Thread.MIN_PRIORITY`, `Thread.MAX_PRIORITY`, and `Thread.NORM_PRIORITY`. 

5.  In the registrator's `deactivate` method, unregister the 
    `ClusterBridgeMessageListener` from the destination: 

    ```java
    @Deactivate
    protected void deactivate() {

        _destination.unregister(_clusterBridgeMessageListener);
    }
    ```

Here's the full registrator class for this example: 

```java
@Component(
    immediate = true,
    service = MyMessageListenerRegistrator.class
)
public class MyMessageListenerRegistrator {
    ...

    @Activate
    protected void activate() {

        _clusterBridgeMessageListener = new ClusterBridgeMessageListener();
        _clusterBridgeMessageListener.setPriority(Priority.LEVEL5)
        _destination.register(_clusterBridgeMessageListener);
    }

    @Deactivate
    protected void deactivate() {

        _destination.unregister(_clusterBridgeMessageListener);
    }

    @Reference(target = "(destination.name=liferay/live_users)")
    private Destination _destination;

    private MessageListener _clusterBridgeMessageListener;
}
```

## Related Topics

[Registering Message Listeners](/docs/7-2/frameworks/-/knowledge_base/f/registering-message-listeners)

[Sending a Message](/docs/7-2/frameworks/-/knowledge_base/f/sending-a-message)

[Using the Message Bus](/docs/7-2/frameworks/-/knowledge_base/f/using-the-message-bus)
