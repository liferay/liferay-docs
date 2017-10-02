# @product@ Clustering [](id=liferay-clustering)

@product@ can serve everything from the smallest to the largest web sites. Out
of the box, it's configured optimally for a single server environment. If one
server isn't sufficient to serve the high traffic needs of your site, @product@
scales to the size you need. 

+$$$

**Note:** Clustering is available in @product-ver@ GA5+. 

$$$

![Figure 1: @product@ is designed to scale to as large an installation as you need.](../../images/clustering-enterprise-configuration.png) 

@product@ works well in clusters of multiple machines (horizontal cluster) or in
clusters of multiple VMs on a single machine (vertical cluster), or any mixture
of the two. Once you have @product@ installed in more than one application
server node, there are several optimizations that need to be made. At a minimum,
@product@ should be configured in the following way for a clustered environment:

1.  All nodes should be pointing to the same @product@ database or database 
    cluster. 

2.  Documents and Media repositories must have the same configuration and be
    accessible to all nodes of the cluster. 

3.  Search should be on a separate search server that is optionally clustered. 

4.  Cluster Link must be enabled so the cache replicates across all nodes of the
    cluster. 

5.  Hot deploy applications to each node individually. 

If you haven't configured your application server to use farms for deployment,
the hot deploy folder should be a separate folder for all the nodes, and plugins
will have to be deployed to all of the nodes individually. This can be done via
a script. If you do have farms configured, you can deploy normally to any node's
deploy folder, and your farm configuration should take care of syncing the
deployment to all nodes. 

Many of these configuration changes can be made by adding or modifying
properties in your `portal-ext.properties` file. Remember that this file
overrides the defaults in the `portal.properties` file. You can also browse its
contents 
[here](https://docs.liferay.com/ce/portal/7.0-latest/propertiesdoc/portal.properties.html). 
It's a best practice to copy the relevant section you want to modify from
`portal.properties` into your `portal-ext.properties` file, and then modify the
values there.

+$$$

**Note:** This article documents a @product@-specific cluster configuration
without getting into specific implementations of third party software, such as
Java EE application servers, HTTP servers, and load balancers. Please consult
your documentation for those components of your cluster to configure those
components. Before creating a @product@ cluster, make sure your OS is not
defining the hostname of your box to the local network at 127.0.0.1.

$$$

Each step defined above is covered below to give you a step by step process for
creating your cluster. 

## 1. All Nodes Should Point to the Same @product@ Database [](id=all-nodes-should-point-to-the-same-liferay-database)

Each node should have a data source that points to one @product@ database (or a
database cluster) that all the nodes will share. This means, of course,
@product@ cannot (and should not) use the embedded HSQL database that is shipped
with the bundles (but you already knew that, right?). And, of course, the
database server should be on a separate system from the server which is running
@product@. 

You can also use a read-writer database configuration to optimize your database
configuration.

### Read-Writer Database Configuration [](id=read-writer-database-configuration)

@product@ allows you to use two different data sources for reading and writing.
This enables you to split your database infrastructure into two sets: one
optimized for reading and one optimized for writing. Since all @product@'s
supported databases support replication, you can use your database vendor's
replication mechanism to keep the database nodes in sync.

Enabling a read-writer database is simple. In your `portal-ext.properties` file,
configure two different data sources for @product@ to use, one for reading, and
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
[documentation](https://docs.liferay.com/ce/portal/7.0-latest/propertiesdoc/portal.properties.html#Spring).

The next time you start @product@, it uses the two data sources you have
defined. Be sure you have correctly set up your two databases for replication
before starting @product@.

## 2. Documents and Media Library Clustering [](id=documents-and-media-library-clustering)

@product@'s Documents and Media Library can mount several repositories at a time
while presenting a unified interface to the user. By default, users can use the
@product@ repository, which is already mounted. This repository is built into
@product@ and can use one of 
[several different store implementations](/discover/deployment/-/knowledge_base/7-0/document-repository-configuration) 
as its back-end. In addition to this, users can mount many different kinds of
third party repositories. In a cluster, Documents and Media must have the exact
same configuration on all nodes. If you have a separate repository you've
mounted, all nodes of the cluster must point to this repository. Your avenue for
improving performance at this point is to cluster your third party repository,
using the documentation for the repository you have chosen. If you don't have a
third party repository, you can configure the @product@ repository to perform
well in a clustered configuration. 

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

Search should always run on a separate environment from your @product@ server.
@product@ supports [Elasticsearch](/discover/deployment/-/knowledge_base/7-0/configuring-elasticsearch-for-liferay-0) 
or 
[Solr](/discover/deployment/-/knowledge_base/7-0/using-solr), 
and either of those environments can also be clustered. 

<!-- Have a Google doc for this: https://docs.google.com/document/d/19oaISXylCyKueuMkIjCKtnrNuyhbvrkMJZvyGQLPIpk/edit#heading=h.ayvbwz8pwsz0 -->

For more information on how to cluster ElasticSearch, see 
[ElasticSearch's distributed cluster setup](https://www.elastic.co/guide/en/elasticsearch/guide/current/distributed-cluster.html). 

Once @product@ servers have been properly configured as a cluster and the same
for ElasticSearch, change @product@ from *embedded* mode to *remote* mode. On
the first connection, the two sets of clustered servers communicate with
each other the list of all IP addresses; in case of a node going down, the
proper failover protocols will enable. Queries and indices can continue to be
sent for all nodes.

For more information on how to cluster Solr, see 
[Apache Solr Cloud](https://cwiki.apache.org/confluence/display/solr/SolrCloud)
documentation. 

Once @product@ servers have been properly configured as a cluster, deploy the
Liferay Solr 5 Adapter on all nodes. (This app is available for download from
Liferay Marketplace
[here](https://web.liferay.com/marketplace/-/mp/application/78803899).) Create a
Solr Cloud (cluster) managed by _Apache Solr Zookeeper_. Connect the @product@
cluster to Zookeeper and finish the final configurations to connect the two
clusters.

## 4. Enable Cluster Link [](id=4-enable-cluster-link)

Enabling Cluster Link automatically activates distributed caching. Distributed
caching enables some RMI (Remote Method Invocation) cache listeners that are
designed to replicate the cache across a cluster. 

The cache is distributed across multiple @product@ nodes running concurrently.
Enabling Cluster Link can increase performance dramatically. For example, if two
users are browsing the message boards, and the first user clicks a thread,
@product@ must grab that thread from the database, cache it, and format it for
display in the browser. When Cluster Link is enabled, the cache is replicated to 
the other nodes in the cluster. If another user wants to read that thread, it's 
retrieved from the cache, no matter what node serves that user, because the 
cache is replicated. Because the thread is in the cache, no trip to the database 
is necessary. 

This is much more powerful than having a cache running separately on each node.
The power of *distributed* caching lets common destinations be cached for
multiple users. The first user can post a message to the thread he or she was
reading, and the cache is updated across all the nodes, making the new post
available immediately from the local cache. Without that, the second user would
need to wait until the cache was invalidated on the node he or she connected to
before he or she could see the updated forum post. 

To enable Cluster Link, add this property to `portal-ext.properties`: 

    cluster.link.enabled=true

### Modifying the Cache Configuration with a Module [](id=modifying-the-cache-configuration-with-a-module)

Enabling Cluster Link automatically activates distributed caching. Distributed
caching enables some RMI (Remote Method Invocation) cache listeners that are
designed to replicate the cache across a cluster. 

@product@ uses 
[Ehcache](http://www.ehcache.org), 
which has robust distributed caching support. The cache is distributed across 
multiple @product@ nodes running concurrently. This increases performance 
dramatically. For example, say that two users are browsing the message boards. 
When the first user clicks a thread, @product@ must retrieve that thread from 
the database and format it for display in the browser. With a distributed 
Ehcache running, this thread is cached for quick retrieval, and the cache is 
replicated to the other nodes. If a second user who's served by a different node 
clicks on the same thread, the data is retrieved faster because the thread is in 
the cache: no trip to the database was necessary. 

This is much more powerful than having a cache running separately on each node.
The power of *distributed* caching allows for common destinations to be cached
for multiple users. The first user can post a message to the thread he or she
was reading, and the cache is updated across all the nodes, making the new post
available immediately from the local cache. Without that, the second user must
wait until the cache is invalidated on his or her own node and the post is
retrieved and cached separately. 

For this reason, once you enable distributed caching, you should do some due
diligence and test your system under a load that best simulates the kind of
traffic your system needs to handle. If you'll be serving up a lot of message
board messages, your script should reflect that. If web content is the core of
your site, your script should reflect that too. 

As a result of a load test, you may find that the default distributed cache
settings aren't optimized for your site. In this case, you should tweak the
settings yourself. You can modify the @product@ installation directly or you can
use a module to do it. Either way, the settings you change are the same. 
A benefit of working with modules is that you can install a module on
each node and change the settings without taking down the cluster. Modifying the
Ehcache settings with a module is recommended over modifying the Ehcache
settings directly. 

We've made this as easy as possible by [creating the project](https://dev.liferay.com/documents/10184/741415/portal-cache-override-config.zip) 
for you. Download the project and unzip it into a 
[Liferay Workspace](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/liferay-workspace), 
in the workspace's `modules` folder. To override your cache settings, you only
have to modify one Ehcache configuration file, which you'll find in this folder
structure: 

- src
    - main
        - java
            - resources
                - ehcache
                    - override-liferay-multi-vm-clustered.xml

In the sample project, this file contains a configuration for @product@'s
`GroupImpl` object which handles sites. You may wish to add other objects to
the cache; in fact, the default file caches many other objects. For example, if
you have a vibrant community, a large portion of your traffic may be directed at
the message boards portlet, as in the example above. To cache the threads on the 
message boards, configure a block with the `MBMessageImpl` class:

    <cache
		eternal="false"
		maxElementsInMemory="10000"
		name="com.liferay.portlet.messageboards.model.impl.MBMessageImpl"
		overflowToDisk="false"
		timeToIdleSeconds="600"
	>
	</cache>

If you're overriding these properties, it's because you want to customize the
configuration for your own site. A good way to start with this is to extract
@product@'s configuration file and then customize it. You'll find it in the
`com.liferay.portal.ehcache-[version].jar` file. You can get this from
@product@'s `Liferay Foundation.lpkg` file in the `osgi/marketplace` folder. The
file you want is `liferay-multi-vm-clustered.xml`, in the `/ehcache` folder
inside the `.jar`. Once you have the file, replace the contents of the
`override-liferay-multi-vm-clustered.xml` file above with the contents of this
file. Now you'll be using the default configuration as a starting point. 

Once you've made your changes to the cache, save the file, build, and deploy the
module, and your settings override the default settings. In this way, you can
tweak your cache settings so that your cache performs optimally for the type of
traffic generated by your site. You don't have restart your server to change the
cache settings. This is a great benefit, but beware: since Ehcache doesn't allow
for changes to cache settings while the cache is alive, reconfiguring a cache
while the server is running flushes the cache. 

## 5. Hot Deploy to All Nodes [](id=hot-deploy)

If you want to deploy any module or WAR file onto the cluster, it must be 
deployed to all nodes of the cluster. Because @product@ now 
[installs applications as OSGi bundles](/develop/tutorials/-/knowledge_base/7-0/using-the-wab-generator), 
this means you cannot rely on your application server's means of installing WAR 
files (even if you only intend to install WAR files) to deploy an application to 
the entire cluster. Instead, the application must be placed in @product@'s 
`deploy` folder on each node. 

This, as you might imagine, can be done with a script. Write a shell script that
uploads applications to each node using sftp or some other service. This way,
when you deploy an application, it is uploaded to each node's `deploy` folder
and installed by each running @product@ installation. 

## Summary [](id=summary)

Setting up @product@ on a cluster takes five steps: 

1. Point all nodes at the same database or database cluster. 

2. Make sure the Documents and Media repository is accessible to all nodes. 

3. Install Elasticsearch or Solr on a separate system or cluster. 

4. Enable Cluster Link for cache replication. 

5. Hot deploy applications to each node individually. 
