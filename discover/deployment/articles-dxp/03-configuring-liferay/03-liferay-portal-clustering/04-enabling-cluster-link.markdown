# Enabling Cluster Link [](id=enabling-cluster-link)

Enabling Cluster Link automatically activates distributed caching. Distributed
caching enables some RMI (Remote Method Invocation) cache listeners that
replicate the cache across a cluster. Cluster Link uses
[Ehcache](http://www.ehcache.org), 
which has robust distributed caching support. The cache is distributed across 
multiple @product@ nodes running concurrently. The Ehcache global settings are in the
[`portal.properties` file](@platform-ref@/7.0-latest/propertiesdoc/portal.properties.html#Ehcache). 

By default Liferay does not copy cached entities between nodes. If an entity is
deleted or changed, for example, Cluster Link sends a *remove* message to the
other nodes to invalidate this entity in their local caches. Requesting that
entity on another node results in a cache *miss*; the entity is then retrieved
from the database and put into the local cache. Entities added to one node's
local cache are not copied to local caches of the other nodes. An attempt to
retrieve a new entity on a node which doesn't have that entity cached results in
a cache *miss*. The miss triggers the node to retrieve the entity from the
database and store it in its local cache. 

![Figure 2: @product@'s cache algorithm is extremely efficient. ](../../../images-dxp/clustering-cache-efficient-algorithm.png)

To enable Cluster Link, add this property to `portal-ext.properties`: 

    cluster.link.enabled=true

Cluster Link depends on [JGroups](http://www.jgroups.org) and provides an API
for nodes to communicate. It can

- Send messages to all nodes in a cluster
- Send messages to a specific node
- Invoke methods and retrieve values from all, some, or specific nodes
- Detect membership and notify when nodes join or leave

Cluster Link contains an enhanced algorithm that provides one-to-many type
communication between the nodes. This is implemented by default with JGroups's
UDP multicast, but unicast and TCP are also available. 

## Multicast Over UDP [](id=multicast-over-udp)

When you enable Cluster Link, @product@'s default clustering configuration is
enabled. This configuration defines IP multicast over UDP. @product@ uses two
groups of [channels from JGroups](http://www.jgroups.org/manual/index.html#_channel) 
to implement this: a control group and a transport group. If you want to
customize the channel properties, you can do so in `portal-ext.properties`: 

    cluster.link.channel.name.control=[your control channel name]
    cluster.link.channel.properties.control=[your control channel properties]

Please see [JGroups's documentation](http://www.jgroups.org/manual/index.html#protlist) 
for channel properties. The default configuration sets many properties whose
settings are discussed there. 

JGroups sets a bind address automatically. If you want to set a manual address,
you can do this. By default, these are set to `localhost`: 

    cluster.link.bind.addr["cluster-link-control"]=localhost
    cluster.link.bind.addr["cluster-link-udp"]=localhost

In some configurations, however, `localhost` is bound to the internal loopback
network (`127.0.0.1` or `::1`), rather than the host's real address. If for some
reason you need this configuration, you can make @product@ auto detect its real
address with this property: 

    cluster.link.autodetect.address=www.google.com:80

Set it to connect to some other host that's contactable by your server. By
default, it points to Google, but this may not work if your server is behind a
firewall. If you set the address manually using the properties above, you don't
need to set the auto-detect address. 

Your network configuration may preclude the use of multicast over TCP, so below
are some other ways you can get your cluster communicating. Note that these
methods are all provided by JGroups. 

### Checkpoint: 

1.  If you are using multicast to handle your cluster, add this property to
    `portal-ext.properties`:

        `cluster.link.enabled=true`

2.  If you are binding the IP address instead of using `localhost`, make sure
    the right IP addresses are declared using these properties: 

        `cluster.link.bind.addr["cluster-link-control"]=localhost` 
        `cluster.link.bind.addr["cluster-link-udp"]=localhost`

3.  Test your load and then optimize your settings if necessary.

## Unicast over TCP [](id=unicast-over-tcp)

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

3.  Download the OSGi Dependencies from the Customer Portal or access them from
    your existing installation. In the dependencies' `portal` folder is a .jar
    file called `com.liferay.portal.cluster.multiple-[version].jar`. In this
    .jar's `lib` folder is a file called `jgroups-[version].Final.jar`. Open it
    and find `tcp.xml`. Extract this file to a location accessible to @product@.
    Use this file on all your nodes.

    <!-- TODO Check for the com.liferay.portal.cluster.multiple JAR in the downloadable OSGi Dependencies. - Jim -->

4.  If you're vertically clustering (i.e., you have multiple servers running on
    the same physical or virtual system), you must change the port on which
    discovery communicates for all nodes other than the first one, to avoid TCP
    port collision. To do this, modify the TCP tag's `bind_port` parameter: 

        <TCP bind_port="[some unused port]"
            ... 
        />

    Since the default port is `7800`, provide some other unused port. 

5.  Add to the same tag the parameter `singleton_name="liferay_cluster"`. This
    merges the transport and control channels to reduce the number of thread
    pools. See 
    [JGroups documentation](http://www.jgroups.org/manual-3.x/html/user-advanced.html) 
    for further information. 

    Usually, no further JGroups configuration is required. However, in a very
    specific case, if *(and only if)* cluster nodes are deployed across
    multiple networks, then the parameter `external_addr` must be set on each
    host to the external (public IP) address of the firewall. This kind of
    configuration is usually only necessary when nodes are geographically
    separated. By setting this, clustered nodes deployed to separate networks
    (e.g. separated by different firewalls) can communicate together. This
    configuration may be flagged in security audits of your system. See 
    [JGroups documentation](http://www.jgroups.org/manual-3.x/html/protlist.html#Transport)
    for more information. 

6.  Save the file. Modify that node's `portal-ext.properties` file to point to
    it: 

        cluster.link.channel.properties.control=[CONFIG_FILE_PATH]/tcp.xml
        cluster.link.channel.properties.transport.0=[CONFIG_FILE_PATH]/tcp.xml

You're now set up for Unicast over TCP clustering! Repeat this process for each
node you want to add to the cluster. 

### JDBC Ping [](id=jdbc-ping)

Rather than use TCP Ping to discover cluster members, you can use a central
database accessible by all the nodes to help them find each other. Cluster
members write their own and read the other members' addresses from this
database. To enable this configuration, replace the `TCPPING` tag with the
corresponding `JDBCPING` tag: 

    <JDBC_PING
        connection_url="jdbc:mysql://[DATABASE_IP]/[DATABASE_NAME]?useUnicode=true&amp;characterEncoding=UTF-8&amp;useFastDateParsing=false"
        connection_username="[DATABASE_USER]"
        connection_password="[DATABASE_PASSWORD]"
        connection_driver="com.mysql.jdbc.Driver"/>

The above example uses MySQL as the database. For further information about
JDBC Ping, please see the [JGroups Documentation](http://www.jgroups.org/manual-3.x/html/protlist.html#DiscoveryProtocols). 

### S3 Ping [](id=s3-ping)

Amazon S3 Ping can be used for servers running on Amazon's EC2 cloud service.
Each node uploads a small file to an S3 bucket, and all the other nodes read the
files from this bucket to discover the other nodes. When a node leaves, its file
is deleted. 

To configure S3 Ping, replace the `TCPPING` tag with the corresponding `S3_PING`
tag: 

    <S3_PING 
        secret_access_key="[SECRETKEY]" 
        access_key="[ACCESSKEY]"
        location="ControlBucket"/>

Supply your Amazon keys as values for the parameters above. For further
information about S3 Ping, please see the [JGroups Documentation](http://www.jgroups.org/manual-3.x/html/protlist.html#DiscoveryProtocols). 

### Other Pings [](id=other-pings)

JGroups supplies other means for cluster members to discover each other,
including Rackspace Ping, BPing, File Ping, and others. Please see the 
[JGroups Documentation](http://www.jgroups.org/manual-3.x/html/protlist.html#DiscoveryProtocols)
for information about these discovery methods. 

## Modifying the Cache Configuration with a Module [](id=modifying-the-cache-configuration-with-a-module)

It's recommended to test your system under a load that best simulates the kind
of traffic your system must handle. If you serve a lot of message
board messages, your script should reflect that. If web content is the core of
your site, your script should reflect that too. 

As a result of a load test, you may find that the default distributed cache
settings aren't optimized for your site. In this case, tweak the settings using
a module. You can install the module on each node and change the settings
without taking down the cluster. 

We've made this as easy as possible by
[creating the project](https://dev.liferay.com/documents/10184/741415/portal-cache-override-config.zip) 
for you. Download the project and unzip it into a 
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace),
in the workspace's `modules` folder. To override your cache settings, you must
only modify one Ehcache configuration file, which you'll find in this folder
structure: 

- `src`
    - `main`
        - `java`
            - `resources`
                - `ehcache`
                    - `override-liferay-multi-vm-clustered.xml`

In the sample project, this file contains a configuration for the `GroupImpl`
object which handles sites. You may wish to add other objects to the cache; in
fact, the default file caches many other objects. For example, if you have
a vibrant community, a large portion of your traffic may be directed at the
message boards portlet, as mentioned above. To cache the threads on the message
boards, configure a block with the `MBMessageImpl` class:

    <cache
        eternal="false"
        maxElementsInMemory="10000"
        name="com.liferay.portlet.messageboards.model.impl.MBMessageImpl"
        overflowToDisk="false"
        timeToIdleSeconds="600"
    >
    </cache>

You can preserve the default settings while customizing them with your own
by extracting Liferay's cluster configuration file and putting it into
your module project. You'll find it in
the `com.liferay.portal.cache.ehcache.impl.jar` file the `[Liferay
Home]/osgi/portal` folder. The file you want is
`liferay-multi-vm-clustered.xml`, in the `/ehcache` folder inside the
`com.liferay.portal.cache.ehcache.impl.jar` file. Once you have the file,
replace the contents of the `override-liferay-multi-vm-clustered.xml` file above
with the contents of this file. Now you'll be using the default configuration as
a starting point. 

Once you've made your changes to the cache, save the file, build, and deploy the
module, and your settings override the default settings. In this way, you can
tweak your cache settings so that your cache performs optimally for the type of
traffic generated by your site. You don't have restart your server to change the
cache settings. This is a great benefit, but beware: since Ehcache doesn't allow
for changes to cache settings while the cache is alive, reconfiguring a cache
while the server is running flushes the cache. 
