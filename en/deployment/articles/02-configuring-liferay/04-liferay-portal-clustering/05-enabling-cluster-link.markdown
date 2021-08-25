---
header-id: enabling-cluster-link
---

# Enabling Cluster Link

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/setting-up-liferay/clustering-for-high-availability/configuring-cluster-link.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

Enabling Cluster Link automatically activates distributed caching.  The cache is
distributed across multiple @product@ nodes running concurrently. Cluster Link
does
[Ehcache](http://www.ehcache.org)
replication. The Ehcache global settings are in the
[`portal.properties` file](@platform-ref@/7.2-latest/propertiesdoc/portal.properties.html#Ehcache).

By default Liferay does not copy cached entities between nodes. If an entity is
deleted or changed, for example, Cluster Link sends a *remove* message to the
other nodes to invalidate this entity in their local caches. Requesting that
entity on another node results in a cache *miss*; the entity is then retrieved
from the database and put into the local cache. Entities added to one node's
local cache are not copied to local caches of the other nodes. An attempt to
retrieve a new entity on a node which doesn't have that entity cached results in
a cache *miss*. The miss triggers the node to retrieve the entity from the
database and store it in its local cache.

![Figure 1: @product@'s cache algorithm is extremely efficient. ](../../../images/clustering-cache-efficient-algorithm.png)

Here are the Cluster Link topics:

- [Enabling Cluster Link](#enabling-cluster-link)
- [Multicast Over UDP](#multicast-over-udp)
- [Unicast Over TCP](#unicast-over-tcp)
- [Using Different Control and Transport Channel Ports](#using-different-control-and-transport-channel-ports)
- [Modifying the Cache Configuration with a Module](#modifying-the-cache-configuration-with-a-module)
- [Conclusion](#conclusion)


## Enabling Cluster Link

To enable Cluster Link, add this [portal
property](/docs/7-2/deploy/-/knowledge_base/d/portal-properties) to a
`portal-ext.properties` file:

```properties
cluster.link.enabled=true
```

The
[Cluster Link portal properties](@platform-ref@/7.2-latest/propertiesdoc/portal.properties.html#Cluster%20Link)
provide a default configuration that you can override to fit your needs.

Many of the defaults use `localhost`, instead of a real address. In some
configurations, however, `localhost` is bound to the internal loopback network
(`127.0.0.1` or `::1`), rather than the host's real address. If for some reason
you need this configuration, you can make DXP auto detect the real address with
this property:

```properties
cluster.link.autodetect.address=www.google.com:80
```

Set it to connect to some other host that's contactable by your server. By
default, it points to Google, but this may not work if your server is behind a
firewall. If you use each host's real address, you don't need to set the
auto-detect address.

Cluster Link depends on
[JGroups](http://www.jgroups.org)
and provides an API for nodes to communicate. It can

- Send messages to all nodes in a cluster
- Send messages to a specific node
- Invoke methods and retrieve values from all, some, or specific nodes
- Detect membership and notify when nodes join or leave

Cluster Link contains an enhanced algorithm that provides one-to-many type
communication between the nodes. This is implemented by default with JGroups's
UDP multicast, but unicast and TCP are also available.

## Multicast Over UDP

When you enable Cluster Link, @product@'s default clustering configuration is
enabled. This configuration defines IP multicast over UDP. @product@ uses two
groups of [channels from
JGroups](http://www.jgroups.org/manual4/index.html#_channel) to implement this:
a control group and a transport group. If you want to customize the [channel
properties](@platform-ref@/7.2-latest/propertiesdoc/portal.properties.html#Cluster%20Link),
you can do so in `portal-ext.properties`:

```properties
cluster.link.channel.name.control=[your control channel name]
cluster.link.channel.properties.control=[your control channel properties]
```

Please see
[JGroups's documentation](http://www.jgroups.org/manual4/index.html#protlist)
for channel properties. The default configuration sets many properties whose
settings are discussed there.

Multicast broadcasts to all devices on the network. Clustered environments on
the same network communicate with each other by default. Messages and
information (e.g., scheduled tasks) sent between them can lead to unintended
consequences. Isolate such cluster environments by either separating them
logically or physically on the network, or by configuring each cluster's
`portal-ext.properties` to use different sets of
[multicast group address and port values](@platform-ref@/7.2-latest/propertiesdoc/portal.properties.html#Multicast).

JGroups sets a bind address automatically. If you want to set a manual address,
you can do this. By default, these are set to `localhost`:

```properties
cluster.link.bind.addr["cluster-link-control"]=localhost
cluster.link.bind.addr["cluster-link-udp"]=localhost
```

In some configurations, however, `localhost` is bound to the internal loopback
network (`127.0.0.1` or `::1`), rather than the host's real address. If for some
reason you need this configuration, you can make @product@ auto detect its real
address with this property:

```properties
cluster.link.autodetect.address=www.google.com:80
```

Set it to connect to some other host that's contactable by your server. By
default, it points to Google, but this may not work if your server is behind a
firewall. If you set the address manually using the properties above, you don't
need to set the auto-detect address.

Your network configuration may preclude the use of multicast over TCP, so below
are some other ways you can get your cluster communicating. Note that these
methods are all provided by JGroups.

### Checkpoint:

1.  If you are binding the IP address instead of using `localhost`, make sure
    the right IP addresses are declared using these properties:

    ```properties
    cluster.link.bind.addr["cluster-link-control"]=localhost
    cluster.link.bind.addr["cluster-link-udp"]=localhost
    ```

3.  Test your load and then optimize your settings if necessary.

## Unicast over TCP

If your network configuration or the geographical distance between nodes prevents you from using UDP Multicast clustering, you can configure TCP Unicast. You must use this if you have a firewall separating any of your nodes or if your nodes are in different geographical locations.

1.  Add a parameter to your app server's JVM on each node:

    ```bash
    -Djgroups.bind_addr=[node_ip_address]
    ```

    Use the node's IP address.

2.  Select a discovery protocol for the nodes to use to find each other. Here are the protocol choices:

    - TCPPing
    - JDBCPing
    - S3_Ping
    - Rackspace_Ping

    If you aren't sure which one to choose, use TCPPing. The rest of these steps use TCPPing

3.  Extract the `tcp.xml` file from `$LIFERAY.HOME/osgi/marketplace/Liferay Foundation - Liferay Portal - Impl.lpkg/com​.​liferay​.​portal​.​cluster​.​multiple​-​[version].​jar/lib​/​jgroups​-​[version].​Final​.​jar/tcp.xml` to a location accessible to DXP, such as a folder called `jgroups` in the DXP web application's `WEB-INF/classes` folder.

    ```
    WEB-INF/classes/jgroups/tcp.xml
    ```

4.  In the `tcp.xml` file, set the TCP bind port to an unused port on your node. Here's an example:

    ```xml
    <TCP bind_port="7800"/>
    ```

5.  In the `tcp.xml` file, make each node discoverable to TCPPing by specifying its IP address and an unused port on that node. Building off of the previous step, here's an example `<TCPPing>` element:

    ```xml
    <TCP bind_port="7800"/>
    <TCPPING async_discovery="true"
        initial_hosts="192.168.224.154[7800],192.168.224.155[7800]"
        port_range="0"/>
    ```

    **Regarding Initial Hosts:**

    - An alternative to specifying initial hosts in a TCP XML file is to specify them to your app server using a JVM argument like this: `-Djgroups.tcpping.initial_hosts=192.168.224.154[7800],192.168.224.155[7800]`.
    - Make sure the initial hosts value accounts for all your nodes. If `initial_hosts` is not specified in a TCP XML file or in a JVM argument, `localhost` is the initial host.

6.  Copy your `tcp.xml` file to each node, making sure to set the TCP bind port to the node's bind port. On the node with IP address `192.168.224.155`, for example, configure TCPPing like this:

    ```xml
    <TCP bind_port="7800"/>
    <TCPPING async_discovery="true"
        initial_hosts="192.168.224.154[7800],192.168.224.155[7800]"
        port_range="0"/>
    ```

7. Modify the [Cluster Link properties](https://docs.liferay.com/portal/7.2-latest/propertiesdoc/portal.properties.html#Cluster%20Link) in the node's `portal-ext.properties` file to enable Cluster Link and point to the TCP XML file for each Cluster Link channel:

```properties
cluster.link.enabled=true
cluster.link.channel.properties.control=/jgroups/tcp.xml
cluster.link.channel.properties.transport.0=/jgroups/tcp.xml
```

The JGroups configuration demonstrated above is typically all that Unicast over TCP requires. However, in a very specific case, if *(and only if)* cluster nodes are deployed across multiple networks, then the parameter `external_addr` must be set on each host to the external (public IP) address of the firewall. This kind of configuration is usually only necessary when nodes are geographically separated. By setting this, clustered nodes deployed to separate networks (e.g. separated by different firewalls) can communicate together. This configuration may be flagged in security audits of your system. See [JGroups documentation](http://www.jgroups.org/manual4/index.html#_transport_protocols) for more information.

> **Note:** The `singleton_name` TCP attribute was deprecated in JGroups v4.0.0 and has therefore been removed since Liferay DXP 7.2 SP1 and Liferay Portal CE GA2 which use JGroups v 4.1.1-Final.

You're now set up for Unicast over TCP clustering! Repeat either TCPPING process for each node you want to add to the cluster.

### JDBC Ping

Rather than use TCP Ping to discover cluster members, you can use a central database accessible by all the nodes to help them find each other. Cluster members write their own and read the other members' addresses from this database. To enable this configuration, replace the `TCPPING` tag with the corresponding `JDBC_PING` tag:

```xml
<JDBC_PING
    connection_url="[place the URL to your database here]"
    connection_username="[place your user name here]"
    connection_password="[place your password here]"
    connection_driver="[place your driver name here]"/>
```

For example JDBC connection values, please see [Database Templates](/docs/7-2/deploy/-/knowledge_base/d/database-templates). For further information about JDBC Ping, please see the [JGroups Documentation](http://www.jgroups.org/manual4/index.html#DiscoveryProtocols).

### S3 Ping

Amazon S3 Ping can be used for servers running on Amazon's EC2 cloud service. Each node uploads a small file to an S3 bucket, and all the other nodes read the files from this bucket to discover the other nodes. When a node leaves, its file is deleted.

To configure S3 Ping, replace the `TCPPING` tag with the corresponding `S3_PING` tag:

```xml
<S3_PING
    secret_access_key="[SECRETKEY]"
    access_key="[ACCESSKEY]"
    location="ControlBucket"/>
```

Supply your Amazon keys as values for the parameters above. For further information about S3 Ping, please see the [JGroups Documentation](http://www.jgroups.org/manual4/index.html#_s3_ping).

### Other Pings

JGroups supplies other means for cluster members to discover each other, including Rackspace Ping, BPing, File Ping, and others. Please see the [JGroups Documentation](http://www.jgroups.org/manual4/index.html#DiscoveryProtocols) for information about these discovery methods.

The control and transport channels can be configured to use different ports.

## Using Different Control and Transport Channel Ports

Using separate control and transport channel ports lets you monitor control and transport traffic and helps you isolate information to diagnose problems. These steps use Unicast over TCPPing to demonstrate the approach.

1.  Add a parameter to your app server's JVM on each node:

    ```bash
    -Djgroups.bind_addr=[node_ip_address]
    ```

2.  Extract the `tcp.xml` file from `$LIFERAY.HOME/osgi/marketplace/Liferay Foundation - Liferay Portal - Impl.lpkg/com​.​liferay​.​portal​.​cluster​.​multiple​-​[version].​jar/lib​/​jgroups​-​[version].​Final​.​jar/tcp.xml` to a location accessible to DXP, such as a folder called `jgroups` in the DXP web application's `WEB-INF/classes` folder.  

3.  Make a copy of the `tcp.xml` in the same location and rename both files, designating one for the control channel and the other for the transport channel. For example, you could use these file names:

    -   `tcp-control.xml`

    -   `tcp-transport.xml`

5.  Modify the [Cluster Link properties](https://docs.liferay.com/portal/7.2-latest/propertiesdoc/portal.properties.html#Cluster%20Link) in the node's `portal-ext.properties` file to enable Cluster Link and point to the TCP XML file for each Cluster Link channel:

    ```properties
    cluster.link.enabled=true
    cluster.link.channel.properties.control=/jgroups/tcp-control.xml
    cluster.link.channel.properties.transport.0=/jgroups/tcp-transport.xml
    ```

6.  Modify each `tcp-*.xml` file's the TCP and TCPPing elements to account for each node's IP address and bind port.

    If you're vertically clustering (i.e., you have multiple servers running on the same physical or virtual system), every channel must use a unique unused bind port for discovery communication. In each `tcp-*.xml` file, assign the TCP tag's `bind_port` attribute to a unique, unused port.

    For example, your first two nodes might assign these bind ports:

    | Node   | Properties File     | Port   |
    | :----- | :------------------ | :----- |
    | Node 1 | `tcp-control.xml`   | `7800` |
    | Node 1 | `tcp-transport.xml` | `7801` |
    | Node 2 | `tcp-control.xml`   | `7802` |
    | Node 2 | `tcp-transport.xml` | `7803` |

    Here are example TCP and TCPPing elements using the bind ports on nodes running on the same system (i.e., same IP address):

    **Node 1 `tcp-control.xml`**

    ```xml
    <TCP bind_port="7800"/>
    <TCPPING async_discovery="true"
        initial_hosts="192.168.224.154[7800],192.168.224.154[7802]"
        port_range="0"/>
    ```

    **Node 1 `tcp-transport.xml`**

    ```xml
    <TCP bind_port="7801"/>
    <TCPPING async_discovery="true"
        initial_hosts="192.168.224.154[7801],192.168.224.154[7803]"
        port_range="0"/>
    ```

    **Node 2 `tcp-control.xml`**

    ```xml
    <TCP bind_port="7802"/>
    <TCPPING async_discovery="true"
        initial_hosts="192.168.224.154[7800],192.168.224.154[7802]"
        port_range="0"/>
    ```

    **Node 2 `tcp-transport.xml`**

    ```xml
    <TCP bind_port="7803"/>
    <TCPPING async_discovery="true"
        initial_hosts="192.168.224.154[7801],192.168.224.154[7803]"
        port_range="0"/>
    ```

If you have added entities that can be cached or you want to tune the cache configuration for your system, you can do so using a module.

## Modifying the Cache Configuration with a Module

It's recommended to test your system under a load that best simulates the kind
of traffic your system must handle. If you serve a lot of message
board messages, your script should reflect that. If web content is the core of
your site, your script should reflect that too.

As a result of a load test, you may find that the default distributed cache
settings aren't optimized for your site. In this case, tweak the settings using
a module. Follow instructions for
[Overriding Cache](/docs/7-2/frameworks/-/knowledge_base/f/overriding-cache).

You can install the module on each node and change the settings without taking
down the cluster. This is a great benefit, but beware: since Ehcache doesn't
allow for changes to cache settings while the cache is alive, reconfiguring a
cache while the server is running flushes the cache.

## Conclusion

Once you've configured your cluster, you can start it. A log file message shows
your cluster's  name (e.g., `cluster=liferay-channel-control`):

```bash
-------------------------------------------------------------------
GMS: address=oz-52865, cluster=liferay-channel-control, physical address=192.168.1.10:50643
-------------------------------------------------------------------
```

Congratulations! Your cluster is using Cluster Link.
