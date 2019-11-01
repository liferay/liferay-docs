---
header-id: enabling-cluster-link
---

# Enabling Cluster Link

[TOC levels=1-4]

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

To enable Cluster Link, add this [portal
property](/docs/7-2/deploy/-/knowledge_base/d/portal-properties) to a
`portal-ext.properties` file: 

```properties
cluster.link.enabled=true
```

| **Note:** See the
| [Cluster Link portal property definitions](@platform-ref@/7.2-latest/propertiesdoc/portal.properties.html#Cluster%20Link)
| for details. 

Cluster Link depends on 
[JGroups](http://www.jgroups.org) 
and provides an API for nodes to communicate. It can

- Send messages to all nodes in a cluster
- Send messages to a specific node
- Invoke methods and retrieve values from all, some, or specific nodes
- Detect membership and notify when nodes join or leave

When you start @portal@ in a cluster, a log file message shows your cluster's 
name (e.g., `cluster=liferay-channel-control`): 

    ------------------------------------------------------------------- 
    GMS: address=oz-52865, cluster=liferay-channel-control, physical address=192.168.1.10:50643 
    -------------------------------------------------------------------

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

If your network configuration or the sheer distance between nodes prevents you
from using UDP Multicast clustering, you can configure TCP Unicast. You must use
this if you have a firewall separating any of your nodes or if your nodes are in
different geographical locations.

1.  Add a parameter to your app server's JVM:

        -Djgroups.bind_addr=[node_address]
 
    Use the node's IP address. 

2.  Now you must determine the discovery protocol the nodes should use to
    find each other. You have four choices: 

    - TCPPing
    - JDBCPing
    - S3_Ping
    - Rackspace_Ping

    If you aren't sure which one to choose, use `TCPPing`. This is used in the
    rest of these steps; the others are covered below. 

3.  Extract the `tcp.xml` file from
    `$LIFERAY.HOME/osgi/marketplace/Liferay Foundation - Liferay Portal -
    Impl.lpkg/com​.​liferay​.​portal​.​cluster​.​multiple​-​[version].​jar/lib​/​jgroups​-​[version].​Final​.​jar/tcp.xml`
    to a location accessible to @product@.

4.  Make a copy of the `tcp.xml` in the same location and rename both files, 
    designating one for the control channel and the other for the transport
    channel. For example,

    -   `tcp-control.xml`

    -   `tcp-transport.xml`

    Copy these two files to all your nodes. 

5.  If you're vertically clustering (i.e., you have multiple servers running on
    the same physical or virtual system), every channel must use a unique unused
    bind port for discovery communication. In each `tcp-*.xml` file, assign the
    TCP tag's `bind_port` attribute to a unique, unused port.  

    ```xml
    <TCP bind_port="[some unused port]"
        ... 
    />
    ```

    For example, your first two nodes might assign these bind ports:

    **Node 1**

    -   `tcp-control.xml` bind port: `7800`
    -   `tcp-transport.xml` bind port: `7801`

    **Node 2**

    -   `tcp-control.xml` bind port: `7802`
    -   `tcp-transport.xml` bind port: `7803`

    Usually, no further JGroups configuration is required. However, in a very
    specific case, if *(and only if)* cluster nodes are deployed across
    multiple networks, then the parameter `external_addr` must be set on each
    host to the external (public IP) address of the firewall. This kind of
    configuration is usually only necessary when nodes are geographically
    separated. By setting this, clustered nodes deployed to separate networks
    (e.g. separated by different firewalls) can communicate together. This
    configuration may be flagged in security audits of your system. See 
    [JGroups documentation](http://www.jgroups.org/manual4/index.html#_transport_protocols)
    for more information. 

    | **Note:** The `singleton_name` TCP attribute was deprecated in JGroups 
    | v4.0.0 and has therefore been removed since Liferay DXP 7.2 SP1 and 
    | Liferay Portal CE GA2 which use JGroups v 4.1.1-Final. 

6.  Modify the
    [cluster link channel portal properties](@platform-ref@/7.2-latest/propertiesdoc/portal.properties.html#Cluster%20Link)
    in that node's `portal-ext.properties` file to point to it: 

    ```properties
    cluster.link.channel.properties.control=[CONFIG_FILE_PATH]/tcp-control.xml
    cluster.link.channel.properties.transport.0=[CONFIG_FILE_PATH]/tcp-transport.xml
    ```

You're now set up for Unicast over TCP clustering! Repeat this process for each
node you want to add to the cluster. 

### JDBC Ping

Rather than use TCP Ping to discover cluster members, you can use a central
database accessible by all the nodes to help them find each other. Cluster
members write their own and read the other members' addresses from this
database. To enable this configuration, replace the `TCPPING` tag with the
corresponding `JDBCPING` tag: 

```xml
<JDBC_PING
    connection_url="jdbc:mysql://[DATABASE_IP]/[DATABASE_NAME]?useUnicode=true&amp;characterEncoding=UTF-8&amp;useFastDateParsing=false"
    connection_username="[DATABASE_USER]"
    connection_password="[DATABASE_PASSWORD]"
    connection_driver="com.mysql.jdbc.Driver"/>
```

The above example uses MySQL as the database. For further information about
JDBC Ping, please see the 
[JGroups Documentation](http://www.jgroups.org/manual4/index.html#DiscoveryProtocols). 

### S3 Ping

Amazon S3 Ping can be used for servers running on Amazon's EC2 cloud service.
Each node uploads a small file to an S3 bucket, and all the other nodes read the
files from this bucket to discover the other nodes. When a node leaves, its file
is deleted. 

To configure S3 Ping, replace the `TCPPING` tag with the corresponding `S3_PING`
tag: 

```xml
<S3_PING 
    secret_access_key="[SECRETKEY]" 
    access_key="[ACCESSKEY]"
    location="ControlBucket"/>
```

Supply your Amazon keys as values for the parameters above. For further
information about S3 Ping, please see the 
[JGroups Documentation](http://www.jgroups.org/manual4/index.html#_s3_ping). 

### Other Pings

JGroups supplies other means for cluster members to discover each other,
including Rackspace Ping, BPing, File Ping, and others. Please see the 
[JGroups Documentation](http://www.jgroups.org/manual4/index.html#DiscoveryProtocols)
for information about these discovery methods. 

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
