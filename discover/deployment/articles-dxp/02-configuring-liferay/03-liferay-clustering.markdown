# Liferay Clustering [](id=liferay-clustering)

@product@ can serve everything from the smallest to the largest web sites. Out
of the box, it's configured optimally for a single server environment. If one
server isn't sufficient to serve the high traffic needs of your site, Liferay
scales to the size you need. 

![Figure 5.1: Liferay is designed to scale to as large an installation as you need.](../../images/enterprise-configuration.png) 

@product@ works well in clusters of multiple machines (horizontal cluster) or in
clusters of multiple VMs on a single machine (vertical cluster), or any mixture
of the two. Once you have @product@ installed in more than one application
server node, there are several optimizations that need to be made. At a minimum,
@product@ should be configured in the following way for a clustered environment:

1.  All nodes should be pointing to the same Liferay database or database cluster. 

2.  Documents and Media repositories should be accessible to all nodes of the
    cluster. 

3.  Search should be on a separate search server that is optionally clustered. 

4.  Cluster Link must be enabled so the cache replicates across all nodes of the
    cluster. 

5.  Hot deploy folders should be configured for each node if you're not using
    server farms. 

If you haven't configured your application server to use farms for deployment,
the hot deploy folder should be a separate folder for all the nodes, and plugins
will have to be deployed to all of the nodes individually. This can be done via
a script. If you do have farms configured, you can deploy normally to any node's
deploy folder, and your farm configuration should take care of syncing the
deployment to all nodes. 

Many of these configuration changes can be made by adding or modifying
properties in your `portal-ext.properties` file. Remember that this file
overrides the defaults in the `portal.properties` file. You can also browse its
contents [here](https://docs.liferay.com/digital-enterprise/7.0-latest/propertiesdoc/portal.properties.html). 
It's a best practice to copy the relevant section you want to modify from
`portal.properties` into your `portal-ext.properties` file, and then modify the
values there.

<!-- Is the above still true? Possibly not; may be OSGi config files now. -->

+$$$

**Note:** This article documents a Liferay-specific cluster configuration
without getting into specific implementations of third party software, such as
Java EE application servers, HTTP servers, and load balancers. Please consult
your documentation for those components of your cluster to configure those
components. Before creating a @product@ cluster, make sure your OS is not
defining the hostname of your box to the local network at 127.0.0.1.

$$$

Each step defined above is covered below to give you a step by step process for
creating your cluster.

## 1. All Nodes Should Point to the Same Liferay Database [](id=all-nodes-should-point-to-the-same-liferay-database)

Each node should have a data source that points to one @product@ database (or a
database cluster) that all the nodes will share. This means, of course,
@product@ cannot (and should not) use the embedded HSQL database that is shipped
with the bundles (but you already knew that, right?). And, of course, the
database server should be on a separate system from the server which is running
@product@. 

You can also use a read-writer database configuration to optimize your database
configuration.

### Read-Writer Database Configuration [](id=read-writer-database-configuration)

Liferay allows you to use two different data sources for reading and writing.
This enables you to split your database infrastructure into two sets: one
optimized for reading and one optimized for writing. Since all Liferay's
supported databases support replication, you can use your database vendor's
replication mechanism to keep the database nodes in sync.

Enabling a read-writer database is simple. In your `portal-ext.properties` file,
configure two different data sources for Liferay to use, one for reading, and
one for writing:

    jdbc.read.driverClassName=com.mysql.jdbc.Driver
    jdbc.read.url=jdbc:mysql://dbread.com/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
    jdbc.read.username=**your user name**
    jdbc.read.password=**your password**
    
    jdbc.write.driverClassName=com.mysql.jdbc.Driver
    jdbc.write.url=jdbc:mysql://dbwrite.com/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
    jdbc.write.username=**your user name**
    jdbc.write.password=**your password**

Of course, specify the user name and password to your database in the above
configuration.

After this, enable the read-writer database configuration by uncommenting the
Spring configuration file which enables it in your `spring.configs` property:

    spring.configs=\
    [..]
    META-INF/dynamic-data-source-spring.xml,\
    [..]

You can find the full `spring.config` list in the `portal.properties`
[documentation](https://docs.liferay.com/digital-enterprise/7.0-latest/propertiesdoc/portal.properties.html#Spring).

The next time you start Liferay, it uses the two data sources you have
defined. Be sure you have correctly set up your two databases for replication
before starting Liferay.

## 2. Documents and Media Library Clustering [](id=documents-and-media-library-clustering)

@product@'s Documents and Media Library can mount several repositories at a time
while presenting a unified interface to the user. By default, users can use the
Liferay repository, which is already mounted. This repository is built into
@product@ and can use one of [several different store implementations](/discover/deployment/-/knowledge_base/7-0/document-repository-configuration) 
as its back-end. In addition to this, users can mount many different kinds of
third party repositories. If you have a separate repository you've mounted, all
nodes of the cluster point to this repository. Your avenue for improving
performance at this point is to cluster your third party repository, using the
documentation for the repository you have chosen. If you don't have a third
party repository, you can configure the Liferay repository to perform well in a
clustered configuration. 

The main thing to keep in mind is you need to make sure that every node of the
cluster has the same access to the file store as every other node. For this
reason, you must look at your store configuration. 

Note that the file systems used by the `File System` or `Advanced File System`
stores must support concurrent requests and file locking.

+$$$

**Checkpoint**: To test if the sharing works well, execute the following steps:

1. On Node 1 upload a document to the Documents and Media.
2. On Node 2 download the document. The download should be successful.
3. Repeat the test with reversed roles.

$$$

## 3. Clustering Search [](id=clustering-search)

Search should always run on a separate environment from your Liferay server.
@product@ supports [Elasticsearch](/discover/deployment/-/knowledge_base/7-0/configuring-elasticsearch-for-liferay-0) 
or [Solr](/discover/deployment/-/knowledge_base/7-0/using-solr), 
and either of those environments can also be clustered. 

<!-- Have a Google doc for this: https://docs.google.com/document/d/19oaISXylCyKueuMkIjCKtnrNuyhbvrkMJZvyGQLPIpk/edit#heading=h.ayvbwz8pwsz0 -->

## 4. Enable Cluster Link

Enabling Cluster Link automatically activates distributed caching. Distributed
caching enables some RMI (Remote Method Invocation) cache listeners that are
designed to replicate the cache across a cluster. 

Liferay uses [Ehcache](www.ehcache.org), which has robust distributed caching
support. This means that the cache can be distributed across multiple Liferay
nodes running concurrently. Enabling this cache can increase performance
dramatically. For example, if two users are browsing the message
boards, and the first user clicks a thread, @product@ must grab that
thread from the database, cache it, and format it for display in the browser.
When Cluster Link is enabled, the cache is replicated to the other nodes in the
cluster. If another user wants to read that thread, it's retrieved from the
cache, no matter what node serves that user, because the cache is replicated.
Because the thread is in the cache, no trip to the database is necessary. 

This is much more powerful than having a cache running separately on each node.
The power of *distributed* caching lets common destinations be cached for
multiple users. The first user can post a message to the thread he or she was
reading, and the cache is updated across all the nodes, making the new post
available immediately from the local cache. Without that, the second user would
need to wait until the cache was invalidated on the node he or she connected to
before he or she could see the updated forum post. 

To enable Cluster Link, add this property to `portal-ext.properties`: 

    cluster.link.enabled=true

Cluster Link depends on [JGroups](http://www.jgroups.org), and provides an API
for nodes to communicate. It can

- Send messages to all nodes in a cluster
- Send messages to a specific node
- Invoke methods and retrieve values from all, some, or specific nodes
- Detect membership and notify when nodes join or leave

Cluster Link contains an enhanced algorithm that provides one-to-many type
communication between the nodes. This is implemented by default with JGroups's
UDP multicast, but unicast and TCP are also available. 

### Multicast over UDP 

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

Next, you need to set an IP address for the JGroups channel to use. This is
called a Bind Address. By default, these are set to `localhost`: 

    cluster.link.bind.addr["cluster-link-control"]=localhost
    cluster.link.bind.addr["cluster-link-udp"]=localhost

In some configurations, however, `localhost` is bound to the internal network
(`127.0.0.1` or `::1`), rather than the host's real address. If for some reason
you need this configuration, you can make @product@ auto detect its real address
with this property: 

    cluster.link.autodetect.address=www.google.com:80

Set it to connect to some other host that's contactable by your server. By
default, it points to Google, but this may not work if your server is
firewalled. 

Once you enable distributed caching, of course, you should do some due diligence
and test your system under a load that best simulates the kind of traffic your
system needs to handle. If you'll be serving up a lot of message board messages,
your script should reflect that. If web content is the core of your site, your
script should reflect that too. 

As a result of a load test, you may find that the default distributed cache
settings aren't optimized for your site. In this case, you'll need to tweak the
settings yourself. You can modify the Liferay installation directly or you can
use a plugin to do it. Either way, the settings you change are the same. Next,
we'll discuss a special EE-only optimization that can be made to the cache.
After that, we'll explain how to configure Liferay's caching settings.

+$$$

**Checkpoint**: To test if the separation is working well, execute the following steps:

1. Start at least two nodes (Nodes 1 and 2) sequentially.
2. Log in and add a portlet (e.g. Hello Velocity) to Node 1.
3. On Node 2, refresh the page. Portlet appears!
4. Repeat test with reversed roles!

$$$

### Modifying the Ehcache Settings With a Plugin [](id=modifying-the-ehcache-settings-with-a-plugin)

A benefit of working with plugins is that you can quickly install a plugin on
each node of your cluster without taking down the cluster. Modifying the Ehcache
settings with a plugin is recommended over modifying Liferay's Ehcache settings
directly. We'll discuss this method first. If you're not a developer, don't
worry--even though you'll create a plugin, you won't have to write any code. 

Since we're assuming you're an administrator and not a developer, we'll take the
easiest route, and use Liferay's graphical development tools, rather than the
command line Plugins SDK by itself. If you're a Liferay EE customer, download
Liferay Developer Studio from the Customer Portal. Set it up with all the
defaults from the first start wizard, and you're good to go (skip the next
paragraph). 

If you're not a Liferay EE customer, download Eclipse and install Liferay IDE
from the Eclipse Marketplace. Download the Plugins SDK for your edition of
Liferay from either the Customer Portal (EE) or the Downloads page on
liferay.com. Connect Liferay IDE to your Plugins SDK using the instructions
found in the *Liferay Developer's Guide*. 

Next, create a hook plugin by selecting *File* &rarr; *New* &rarr; *Liferay
Project*. Select *Hook* as the project type and give your project a name. Click
*Finish* and your project is created. 

In your project, create a text file called `portlet.properties` in the
`docroot/WEB-INF/src` folder. This file can override properties in your portal
just like `portal-ext.properties`. Into this file, add the following three
properties: 

    net.sf.ehcache.configurationResourceName=
    ehcache.single.vm.config.location=
    ehcache.multi.vm.config.location=
    
Liferay's configuration files are, of course, used by default. If you're
overriding these properties, it's because you want to customize the
configuration for your own site. A good way to start with this is to extract
Liferay's configuration files and then customize them. If you're running an
application server (such as Tomcat) that allows you to browse to the running
instance of Liferay, you can extract Liferay's configuration files from Liferay
itself. If you're not, you can extract them from Liferay's `.war` file or
Liferay's source code. In either place, you'll find the files in the
`portal-impl.jar` file, which is in Liferay's `WEB-INF/lib` folder. The files
you want are `hibernate-clustered.xml`, `liferay-single-vm.xml`, and
`liferay-multi-vm-clustered.xml`, and they'll be in the `/ehcache` folder in
this `.jar`. Once you have these, make a subfolder of the `docroot/WEB-INF/src`
folder in your project. Place the files you extracted into this folder and then
specify this folder in the properties above. 

For example, if you created a folder called `custom_cache` in your project's
`docroot/WEB-INF/src` folder, you'd copy the three XML configuration files
(`hibernate-clustered.xml`, `liferay-single-vm.xml`, and
`liferay-multi-vm-clustered.xml`) there. Then you'd edit your
`portlet.properties` and specify your configuration files in the three
properties above: 

    net.sf.ehcache.configurationResourceName=/custom_cache/hibernate-clustered.xml
    ehcache.single.vm.config.location=/custom_cache/liferay-single-vm.xml
    ehcache.multi.vm.config.location=/custom_cache/liferay-multi-vm-clustered.xml
    
Save the file and deploy the plugin (deploying plugins is covered in the
[Liferay Development Guide](https://www.liferay.com/documentation/liferay-portal/6.2/development)),
and the settings you've placed in those files will override the default Liferay
settings. In this way, you can tweak your cache settings so that your cache
performs optimally for the type of traffic generated by your site. The strength
of doing it this way is that you don't have restart your server to change the
cache settings. This is a great benefit, but beware: since Ehcache doesn't allow
for changes to cache settings while the cache is alive, reconfiguring a cache
while the server is running will flush the cache. 

There is, of course, another way to do this if you don't want to create a
plugin. It requires you to restart the server to enable the new cache settings,
but you don't have to work with any developer tools to do it. 

### Modifying the Ehcache Settings Directly [](id=modifying-the-ehcache-settings-directly)

This method is pretty similar to the plugin method, except that you have to
modify the Liferay installation directly. You'll still need to extract Liferay's
configuration files as described in the previous section. Next, shut down your
server and find the location in the server where Liferay is installed (this may
not be possible on all application servers, and if this is the case, you'll need
to use the plugin method described above). For example, suppose you're running
Liferay on Tomcat. Tomcat stores the deployed version of Liferay in `[Tomcat
Home]/webapps/ROOT`. Inside this folder is the folder structure
`WEB-INF/classes`. You can create a new folder in here called `custom_cache` to
store the custom versions of the cache configuration files. Copy the files you
extracted from Liferay into this folder. 

You then need to modify the properties in `portal-ext.properties` that point to
these files. Copy/paste the *Hibernate* section of `portal.properties` into your
`portal-ext.properties` file and then modify the
`net.sf.ehcache.configurationResourceName` property to point to the clustered
version of the configuration file that is now in your custom folder:

    net.sf.ehcache.configurationResourceName=/custom_cache/hibernate-clustered.xml

Now that Liferay is pointing to your custom file, you can modify the settings in
this file to change the cache configuration for Hibernate.

Next, copy/paste the *Ehcache* section from the `portal.properties` file into
your `portal-ext.properties` file. Modify the properties so they point to the
files in your custom folder. For example:

    ehcache.multi.vm.config.location=/custom_cache/liferay-multi-vm-clustered.xml

You can now take a look at the settings in these files and tune them to fit your
environment and application. Let's examine how to do that next. 

### Customizing Hibernate Cache Settings [](id=customizing-hibernate-cache-settings)

By default, Hibernate (Liferay's database persistence layer) is configured to
use Ehcache as its cache provider. This is the recommended setting. If you're
using the default settings using Cluster Link, you already have this enabled.
If, however, you need to customize the settings, you'll have to customize it in
one of the ways described above: either in a plugin or in the deployed instance
of Liferay. The first thing, of course, is to start off with the clustered
version of the file. Copy the `hibernate-clustered.xml` configuration file to
your plugin or to a place in Liferay's classpath (as described above) where you
can refer to it. Then change the following property to point to the file: 

    net.sf.ehcache.configurationResourceName=/path/to/hibernate-clustered.xml

Next, open this file in a text editor. You'll notice that the configuration is
already set up to perform distributed caching through a multi-cast connection.
The configuration, however, might not be set up optimally for your particular
application. Notice that by default, the only object cached in the Hibernate
cache is the User object (`com.liferay.``portal``.model.impl.UserImpl`). This
means that when a user logs in, his or her User object will go in the cache so
that any portal operation that requires access to it (such as permission
checking) can retrieve that object very quickly from the cache.

You may wish to add other objects to the cache. For example, a large part of
your application may be document management using the Documents and Media
portlet. In this case, you may want to cache media objects, such as
`DLFileEntryImpl` to improve performance as users access documents. To do that,
add another block to the configuration file with the class you want to cache:

    <cache
        eternal="false"
        maxElementsInMemory="10000"
        name="com.liferay.portlet.documentlibrary.model.impl.DLFileEntryImpl"
        overflowToDisk="false"
        timeToIdleSeconds="600"
    >
        <cacheEventListenerFactory
            class="com.liferay.portal.cache.ehcache.LiferayCacheEventListenerFactory"
            properties="replicatePuts=false,replicateUpdatesViaCopy=false"
            propertySeparator=","
        />
        <bootstrapCacheLoaderFactory class="com.liferay.portal.cache.ehcache.LiferayBootstrapCacheLoaderFactory" />
    </cache>

Your site may use the message boards portlet, and those message boards may get a
lot of traffic. To cache the threads on the message boards, configure a block
with the `MBMessageImpl` class:

    <cache
        eternal="false"
        maxElementsInMemory="10000"
        name="com.liferay.portlet.messageboards.model.impl.MBMessageImpl"
        overflowToDisk="false"
        timeToIdleSeconds="600"
    >
        <cacheEventListenerFactory
            class="com.liferay.portal.cache.ehcache.LiferayCacheEventListenerFactory"
            properties="replicatePuts=false,replicateUpdatesViaCopy=false"
            propertySeparator=","
        />
        <bootstrapCacheLoaderFactory class="com.liferay.portal.cache.ehcache.LiferayBootstrapCacheLoaderFactory" />
    </cache>

Note that if your developers have overridden any of these classes in an Ext
plugin, you'll have to specify the overridden versions rather than the stock
ones that come with Liferay Portal. You can customize the other ehcache
configuration files in exactly the same way. Refer to Ehcache's
[documentation](www.ehcache.org/documentation) for information on how to do
this. 

As you can see, it's easy to add specific data to be cached. Be careful,
however, as too much caching can actually reduce performance if the JVM runs out
of memory and starts garbage collecting too frequently. You'll likely need to
experiment with the memory settings on your JVM as well as the cache settings
above. You can find the specifics about these settings in the documentation for
Ehcache.

### Configuring Liferay's Caching Settings [](id=configuring-liferays-caching-settings)

To understand how Liferay behaves with various cache configurations, let's
consider five different scenarios.

- Scenario 1: The portal administrator does not override the default cache
  configuration files (i.e., does not override
  `ehcache.single.vm.config.location`, `ehcache.multi.vm.config.location`, or
  `net.sf.ehcache.configurationResourceName`) but does set
  `cluster.link.enabled=true` and
  `ehcache.cluster.link.replication.enabled=true`. This is the recommended
  configuration for a Liferay cluster, as long as the Ehcache Cluster EE app,
  available from Liferay Marketplace, has been installed. **Important**: The
  Ehcache Cluster EE app must be installed or cache replication will *not* work
  with this configuration. In this scenario, Liferay automatically resets cache
  peer and cache event listers and replaces them with Cluster Link based
  listeners. This basically activates Cluster Link based cache replication. The
  configured LiferayCacheManagerPeerProviderFactory,
  RMICacheManagerPeerListenerFactory, and LiferayCacheEventListenerFactory
  classes are replaced with Cluster Link based implementations.

- Scenario 2: The portal administrator does not override the default cache
  configuration files, does set `cluster.link.enabled=true`, but does not set
  `ehcache.cluster.link.replication.enabled=true`. In this case, Liferay
  utilizes Ehcache's out-of-the-box replication mechanisms (Multicast for
  discovery and RMI for replication). Lots of replicator threads appear in the
  log with this configuration.

- Scenario 3: The portal administrator does not override the default cache
  configuration files and does not set `cluster.link.enabled=true`. In this
  case, Liferay does not activate any replication and operates with the
  assumption that there's no cluster.

- Scenario 4: The portal administrator overrides the default cache configuration
  files and sets `cluster.link.enabled=true` and
  `ehcache.cluster.link.replication.enabled=true`. In this case, Liferay uses
  Cluster Link based replication for any caches configured with the
  LiferayCacheEventListenerFactory class. If the portal administrator
  configured a different CacheEventListener class for a specific cache (e.g.,
  JGroups), then Liferay uses that listener class. This a basically a hybrid
  mode that was useful prior to Liferay 6.1 before Liferay supported cached
  object replication (and only supported cached event replication). This is
  *not* a recommended configuration for Liferay 6.1 or later.

- Scenario 5: The portal administrator overrides the default cache configuration
  files and sets `cluster.link.enabled=true` but does not set
  `ehcache.cluster.link.replication.enabled=true`. In this case, Liferay uses
  the cache configurations specified in the custom cache configuration files.
  This is the recommended configuration when overriding the default cache
  configuration files.

As a general rule, we recommend that portal administrators *not* set custom
cache configuration files but to set `cluster.link.enabled=true` and
`ehcache.cluster.link.replication.enabled=true` (i.e., we recommend that portal
administrators set up the configuration in scenario 1). If it's necessary to
tune the cache configurations, it's better to do it via a plugin than to do it
directly. We explained how to do this in the section above called "Modifying the
Ehcache Settings With a Plugin."

Next, we'll show how to share indexes in a database. This is actually not a
recommended configuration, as it's slow (databases are always slower than file
systems), but for completeness, we'll go ahead and tell you how to do it anyway.
But you've been forewarned: it's far better to use one of the other methods of
clustering your search index. 

## Hot Deploy [](id=hot-deploy)

Plugins which are hot deployed will need to be deployed separately to all the
Liferay nodes. The best way to do this is to configure your application server
to support *farms*. This is a feature that enables you to deploy an application
on one node and then it replicates automatically to each of the other nodes.
This, of course, is configured differently for each application server, so
you'll need to consult your application server's documentation to learn how to
do this. It's by far the best way to handle hot deploy, and is the recommended
configuration. If you have this working, great! You can skip the rest of this
section completely. 

If for some reason your application server doesn't support this feature or you
can't use it, you'll need to come up with a way to deploy applications across
your cluster. Each node needs to have its own hot deploy folder. This folder
needs to be writable by the user under which Liferay is running, because plugins
are moved from this folder to a temporary folder when they are deployed. This is
to prevent the system from entering an endless loop, because the presence of a
plugin in the folder is what triggers the hot deploy process.

When you want to deploy a plugin to the entire cluster, copy that plugin to the
hot deploy folders of all of the Liferay nodes. Depending on the number of
nodes, it may be best to create a script to do this. Once the plugin has been
deployed to all of the nodes, you can then make use of it (by adding the portlet
to a page or choosing the theme as the look and feel for a page or page
hierarchy).

All of the above will get basic Liferay clustering working; however, the
configuration can be further optimized. We will see how to do this next.
