# Liferay Clustering [](id=liferay-clustering)

Liferay Portal is designed to serve everything from the smallest to the largest
web sites. Out of the box, it's configured optimally for a single server
environment. If one server isn't sufficient to serve the high traffic needs of
your site, Liferay scales to the size you need. 

![Figure 5.1: Liferay is designed to scale to as large an installation as you need.](../../images/enterprise-configuration.png) 

Liferay works well in clusters of multiple machines (horizontal cluster) or in
clusters of multiple VMs on a single machine (vertical cluster), or any mixture
of the two. Once you have Liferay installed in more than one application server
node, there are several optimizations that need to be made. At a minimum,
Liferay should be configured in the following way for a clustered environment:

- All nodes should be pointing to the same Liferay database or database cluster. 

- Documents and Media repositories should be accessible to all nodes of the
  cluster. 

- Search should be configured for replication or should use a separate search
  server. 

- The cache should be replicating across all nodes of the cluster. 

- Hot deploy folders should be configured for each node if you're not using
  server farms. 

If you haven't configured your application server to use farms for deployment,
the hot deploy folder should be a separate folder for all the nodes, and plugins
will have to be deployed to all of the nodes individually. This can be done via
a script. If you do have farms configured, you can deploy normally to any node's
deploy folder, and your farm configuration should take care of syncing the
deployment to all nodes. 

Many of these configuration changes can be made by adding or modifying
properties in your `portal-ext.properties` file. Remember that this file
overrides the defaults in the `portal.properties` file. The original version of
this file can be found in the Liferay source code or can be extracted from the
`portal-impl.jar` file in your Liferay installation. You can also browse an
online version of Liferay's properties file:
[http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html).
It's a best practice to copy the relevant section you want to modify from
`portal.properties` into your `portal-ext.properties` file, and then modify the
values there.

+$$$

**Note:** This chapter documents a
 Liferay-specific cluster configuration, without getting into specific
 implementations of third party software, such as Java EE application servers,
 HTTP servers, and load balancers. Please consult your documentation for those
 components of your cluster for specific details of those components. Before
 configuring Liferay in a cluster configuration, make sure your OS is not
 defining the hostname of your box to the local network at 127.0.0.1.

$$$

We'll discuss each of the points above one by one to present a clear picture of
how to cluster Liferay. 

## All Nodes Should Point to the Same Liferay Database [](id=all-nodes-should-point-to-the-same-liferay-database)

This is pretty self-explanatory. Each node should be configured with a data
source that points to one Liferay database (or a database cluster) that all the
nodes will share. This ensures all the nodes operate from the same basic data
set. This means, of course, Liferay cannot (and should not) use the embedded
HSQL database that is shipped with the bundles (but you already knew that,
right?). And, of course, it goes without saying that the database server is a
separate physical box from the server which is running Liferay. 

Beyond a database cluster, there are two more advanced options you can use to
optimize your database configuration: a read-writer database configuration, and
sharding. 

### Read-Writer Database Configuration [](id=read-writer-database-configuration)

Liferay allows you to use two different data sources for reading and writing.
This enables you to split your database infrastructure into two sets: one that
is optimized for reading and one that is optimized for writing. Since all major
databases support replication in one form or another, you can then use your
database vendor's replication mechanism to keep the databases in sync in a much
faster manner than if you had a single data source which handled everything.

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
Spring configuration file which enables it in your `spring.configs` property
(line to uncomment is in bold):

    spring.configs=\
    META-INF/base-spring.xml,\
    META-INF/hibernate-spring.xml,\
    META-INF/infrastructure-spring.xml,\
    META-INF/management-spring.xml,\
    META-INF/util-spring.xml,\
    META-INF/editor-spring.xml,\
    META-INF/jcr-spring.xml,\
    META-INF/messaging-spring.xml,\
    META-INF/scheduler-spring.xml,\
    META-INF/search-spring.xml,\
    META-INF/counter-spring.xml,\
    META-INF/document-library-spring.xml,\
    META-INF/lock-spring.xml,\
    META-INF/mail-spring.xml,\
    META-INF/portal-spring.xml,\
    META-INF/portlet-container-spring.xml,\
    META-INF/wsrp-spring.xml,\
    META-INF/mirage-spring.xml,\
    **META-INF/dynamic-data-source-spring.xml,\**
    #META-INF/shard-data-source-spring.xml,\
    META-INF/ext-spring.xml

The next time you restart Liferay, it will now use the two data sources you have
defined. Be sure you have correctly set up your two databases for replication
before starting Liferay.

Next, we'll look at database sharding. 

### Database Sharding [](id=database-sharding)

Liferay, starting with version 5.2.3, supports database sharding for different
portal instances. Sharding is a term used to describe an extremely high
scalability configuration for systems with massive amounts of users. In
diagrams, a database is normally pictured as a cylinder. Instead, picture it as
a glass bottle full of data. Now take that bottle and smash it onto a concrete
sidewalk. There will be shards of glass everywhere. If that bottle were a
database, each shard now is a database, with a subset of the data in each shard.

This allows you to split up your database by various types of data that might be
in it. For example, some implementations of sharding a database split up the
users: those with last names beginning with A to D go in one database; E to I go
in another; etc. When users log in, they are directed to the instance of the
application that is connected to the database that corresponds to their last
names. In this manner, processing is split up evenly, and the amount of data the
application needs to sort through is reduced.

By default, Liferay allows you to support sharding through different portal
instances, using the *round robin shard selector*. This is a class which serves
as the default algorithm for sharding in Liferay. Using this algorithm, Liferay
selects from several different portal instances and evenly distributes the data
across them. Alternatively, you can use the manual shard selector. In this case,
you'd need to use the UI provided in the Control Panel to configure your shards. 

Of course, if you wish to have your developers implement your own sharding
algorithm, you can do that. This is a great use of the Ext plugin. You can
select which algorithm is active via the `portal-ext.properties` file:

    shard.selector=com.liferay.portal.dao.shard.RoundRobinShardSelector
    #shard.selector=com.liferay.portal.dao.shard.ManualShardSelector
    #shard.selector=[your implementation here]

Enabling sharding is easy. You'll need to make sure you are using Liferay's data
source implementation instead of your application server's. Set your various
database shards in your `portal-ext.properties` file this way:

    jdbc.default.driverClassName=com.mysql.jdbc.Driver
    jdbc.default.url=jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
    jdbc.default.username=
    jdbc.default.password=
    jdbc.one.driverClassName=com.mysql.jdbc.Driver
    jdbc.one.url=jdbc:mysql://localhost/lportal1?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
    jdbc.one.username=
    jdbc.one.password=
    jdbc.two.driverClassName=com.mysql.jdbc.Driver
    jdbc.two.url=jdbc:mysql://localhost/lportal2?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
    jdbc.two.username=
    jdbc.two.password=
    shard.available.names=default,one,two

Once you do this, you can set up your DNS so several domain names point to your
Liferay installation (e.g., abc1.com, abc2.com, abc3.com). Next, go to the
Control Panel and click on *Portal Instances* under the Configuration heading.
Create two to three instances bound to the DNS names you have configured.

If you're using the RoundRobinShardSelector class, Liferay automatically enters
data into each instance one by one. If you're using the `ManualShardSelector`
class, you'll have to specify a shard for each instance using the UI.

![Figure 5.2: When creating a shard using the manual shard selector, specify the shard you want to use for that instance.](../../images/enterprise-sharding-portal-instance.png)

The last thing you need to do is modify the `spring.configs` section of your
`portal-ext.properties` file to enable the sharding configuration, which by
default is commented out. To do this, your `spring.configs` should look like
this (modified section is in bold):

    spring.configs=\
        META-INF/base-spring.xml,\
        \
        META-INF/hibernate-spring.xml,\
        META-INF/infrastructure-spring.xml,\
        META-INF/management-spring.xml,\
        \
        META-INF/util-spring.xml,\
        \
        META-INF/jpa-spring.xml,\
        \
        META-INF/executor-spring.xml,\
        \
        META-INF/audit-spring.xml,\
        META-INF/cluster-spring.xml,\
        META-INF/editor-spring.xml,\
        META-INF/jcr-spring.xml,\
        META-INF/ldap-spring.xml,\
        META-INF/messaging-core-spring.xml,\
        META-INF/messaging-misc-spring.xml,\
        META-INF/mobile-device-spring.xml,\
        META-INF/notifications-spring.xml,\
        META-INF/poller-spring.xml,\
        META-INF/rules-spring.xml,\
        META-INF/scheduler-spring.xml,\
        META-INF/scripting-spring.xml,\
        META-INF/search-spring.xml,\
        META-INF/workflow-spring.xml,\
        \
        META-INF/counter-spring.xml,\
        META-INF/mail-spring.xml,\
        META-INF/portal-spring.xml,\
        META-INF/portlet-container-spring.xml,\
        META-INF/staging-spring.xml,\
        META-INF/virtual-layouts-spring.xml,\
        \
        #META-INF/dynamic-data-source-spring.xml,\
        *META-INF/shard-data-source-spring.xml,\*
        #META-INF/memcached-spring.xml,\
        #META-INF/monitoring-spring.xml,\
        \
        classpath*:META-INF/ext-spring.xml

That's all there is to it. Your system is now set up for sharding. Now that
you've got your database set up and optimized for a large installation, let's
turn to clustering the Documents and Media Library. 

## Documents and Media Library Clustering [](id=documents-and-media-library-clustering)

Beginning with Liferay 6.1, Liferay's Documents and Media Library is capable of
mounting several repositories at a time while presenting a unified interface to
the user. By default, users can use the Liferay repository, which is already
mounted. This repository is built into Liferay Portal and can use one of several
different store implementations as its back-end. In addition to this, many
different kinds of third party repositories can be mounted. If you have a
separate repository you've mounted, all nodes of the cluster will point to this
repository. Your avenue for improving performance at this point is to cluster
your third party repository, using the documentation for the repository you have
chosen. If you don't have a third party repository, you can configure the
Liferay repository to perform well in a clustered configuration. 

The main thing to keep in mind is you need to make sure that every node of the
cluster has the same access to the file store as every other node. For this
reason, you'll need to take a look at your store configuration. 

There are several options available for configuring how Liferay's Documents and
Media library stores files. Each option is a *store* which can be configured
through the `portal-ext.properties` file by setting the `dl.store.impl=`
property. Let's consider the ramifications of the various store options. 

### Using the File System Store [](id=using-the-file-system-store)

This is the default store. It's a simple file storage implementation that uses a
local folder to store files. You can use the file system for your clustered
configuration, but you'd have to make sure the folder to which you point the
store can handle things like concurrent requests and file locking. For this
reason, you need to use a Storage Area Network or a clustered file system.

The file system store was the first store created for Liferay and is heavily
bound to the Liferay database. By default, documents are stored in a
`document_library` subfolder of the `data` folder in a Liferay bundle. Of
course, you can change this path to anything you want by using the
`dl.store.file.system.root.dir=` property. 

This store creates a folder structure based on primary keys in the Liferay
database. If, for example, you upload a presentation with the file name
`workflow.odp` into a folder called *stuff*, the file system store creates a
folder structure which looks like the figure below. 

![Figure 5.3: Liferay's file system store creates a folder structure based on primary keys in Liferay's database.](../../images/enterprise-file-system-store.png)

The actual folder path that is used by Liferay for storing documents is this:

    /companyId/folderId/numericFileEntryName/versionNumber

The first folder name is the company ID to which the site belongs. The second
folder name is the ID of the Documents and Media folder within which the
document resides. The third folder name is the numeric file entry name of the
document itself. Finally, the fourth name is a version number which is used for
storing multiple versions of the document.

+$$$

**Note:** The numeric file entry name of a
 document is distinct from the document ID; be careful not to confuse the two!
 Each has an independent counter. The numeric file entry name of a document is
 used in the folder path for storing the document but the document ID is not.
 The numeric file entry name of document can be found in the `name` column of
 the `DLFileEntry` table in Liferay's database; the document ID can be found in
 the `fileEntryId` column of the same table.

$$$

As you can see, the File System Store binds your documents very closely to
Liferay, and may not be exactly what you want. But if you've been using the
default settings for a while and need to migrate your documents, Liferay
provides a migration utility in the Control Panel in *Server Administration*
&rarr; *Data Migration*. Using this utility, you can move your documents very
easily from one store implementation to another. 

Speaking of other store implementations, let's look at some others Liferay
provides. 

### Using the Advanced File System Store [](id=using-the-advanced-file-system-store)

Liferay's advanced file system store is similar to the default file system
store. Like that store, it saves files to the local file system--which, of
course, could be a remote file system mount. It uses a slightly different folder
structure to store files, which is pictured below. 

![Figure 5.4: The advanced file system store creates a more nested folder structure than the file system store.](../../images/enterprise-adv-file-system-store.png)

So what makes the advanced file system store *advanced*? Several operating
systems have limitations on the number of files which can be stored in a
particular folder. The advanced file system store overcomes this limitation by
programmatically creating a structure that can expand to millions of files, by
alphabetically nesting the files in folders. This not only allows for more files
to be stored, but also improves performance as there are less files stored per
folder. 

The same rules apply to the advanced file system store as apply to the default
file system store. To cluster this, you'll need to point the store to a network
mounted file system that all the nodes can access, and that networked file
system needs to support concurrent requests and file locking. Otherwise, you may
experience data corruption issues if two users attempt to write to the same file
at the same time from two different nodes. 

You may decide the advanced file system store for whatever reason doesn't serve
your needs. If this is the case, you can of course mount other file systems into
the documents and media library. In addition to this, you can also redefine the
Liferay store to use one of three other supported protocols. We'll look at these
next. 

### Using the CMIS Store [](id=using-the-cmis-store)

Though you can mount as many different CMIS (Content Management Interoperability
Services) repositories as you like in the Documents and Media library, you may
wish also to redefine the Liferay repository to point to a CMIS repository as
well. Why? Users might want to create a folder or upload content to the Liferay
repository. It would be nice if that Liferay repository was connected to a
clustered CMIS repository by the administrator without having to mount it
through the UI. The CMIS store allows you to do just that. 

If you wish to use the CMIS store, all you need to do is set the following four
directives in your `portal-ext.properties` file: 

    dl.store.cmis.credentials.username=yourusername
    dl.store.cmis.credentials.password=yourpassword
    dl.store.cmis.repository.url=http://localhost:8080/url/to/your/cmis/repository
    dl.store.cmis.system.root.dir=Liferay Home

With the configuration above, the Liferay repository is connected to CMIS via
the CMIS store. As long as all nodes are pointing to your CMIS repository,
everything in your Liferay cluster should be fine, as the CMIS protocol prevents
multiple simultaneous file access from causing data corruption. 

From here, we'll move on to the JCR store. 

### Using the JCR Store [](id=using-the-jcr-store)

Liferay Portal supports as a store the Java Content Repository standard. Under
the hood, Liferay uses Jackrabbit, a project from Apache, as its JSR-170
compliant document repository. By default, Jackrabbit is configured to store the
documents on the local file system upon which Liferay is installed, in the
`[Liferay Home]/liferay/jackrabbit` folder. Inside this folder is Jackrabbit's
configuration file, called `repository.xml`. 

Using the default settings, the JCR store is not very different from the file
system stores, except you can use any JCR client to access the files. You can,
however, modify Jackrabbit's configuration so it stores files in a database that
can be accessed by all nodes, and so that it operates as a cluster within
Liferay's cluster. 

To move the default repository location to a shared folder, you do not need to
edit Jackrabbit's configuration file. Instead, find the section in
`portal.properties` labeled **JCR** and copy/paste that section into your
`portal-ext.properties` file. One of the properties, by default, is the
following:

    jcr.jackrabbit.repository.root=${liferay.home}/data/jackrabbit

Change this property to point to a shared folder that all the nodes can see. A
new Jackrabbit configuration file is then generated in that location, and you'll
have to edit that file to modify Jackrabbit's configuration. 

Note that because of file locking issues, this isn't the best way to share
Jackrabbit resources, unless you're using a networked file system that can
handle concurrency and file locking. If you have two people logged in at the
same time uploading content, you could encounter data corruption using this
method, and because of this, we don't recommend it for a production system.
Instead, if you want to use the Java Content Repository in a cluster, you should
redirect Jackrabbit into your database of choice. You can use the Liferay
database or another database for this purpose. This requires editing
Jackrabbit's configuration file.

The default Jackrabbit configuration file has sections commented out for moving
the Jackrabbit configuration into the database. This has been done to make it as
easy as possible to enable this configuration. To move the Jackrabbit
configuration into the database, simply comment out the sections relating to the
file system and comment in the sections relating to the database. These by
default are configured for a MySQL database. If you are using another database,
you will likely need to modify the configuration, as there are changes to the
configuration file that are necessary for specific databases. For example, the
default configuration uses Jackrabbit's `DbFileSystem` class to mimic a file
system in the database. While this works well in MySQL, it doesn't work for all
databases. For example, if you're using an Oracle database, you'll need to
modify this to use `OracleFileSystem`. 

Modify the JDBC database URLs so they point to your database. This, of course,
must be done on all nodes of the cluster. Don't forget to create the database
first, and grant the user ID you are specifying in the configuration file access
to create, modify, and drop tables. After this, be sure to uncomment the
`<Cluster/>` section at the bottom of the file. For further information, it's
best to check out the Jackrabbit documentation. Please see the Jackrabbit
documentation at `http://jackrabbit.apache.org` for further information. 

Once you've configured Jackrabbit to store its repository in a database, the
next time you bring up Liferay, the necessary database tables are created
automatically. Jackrabbit, however, does not create indexes on these tables, and
so over time this can be a performance penalty. To fix this, you'll need to
manually go into your database and index the primary key columns for all the
Jackrabbit tables.

Note that this configuration doesn't perform as well as the advanced file system
store, because you're storing documents in a database instead of on the file
system. But it does have the benefit of clustering well. Next, we'll look at
Amazon's S3 store. 

#### Using Amazon Simple Storage Service [](id=using-amazon-simple-storage-service)

Amazon's simple storage service (S3) is a cloud-based storage solution which you
can use with Liferay. All you need is an account, and you can store your
documents to the cloud from all nodes, seamlessly. 

This is easy to set up. When you sign up for the service, Amazon assigns you
some unique keys which link you to your account. In Amazon's interface, you can
create "buckets" of data optimized by region. Once you've created these to your
specifications, all you need to do is declare them in `portal-ext.properties`: 

    dl.store.s3.access.key=
    dl.store.s3.secret.key=
    dl.store.s3.bucket.name=

Once you have these configured, set your store implementation to the `S3Store`: 

    dl.store.impl=com.liferay.portlet.documentlibrary.store.S3Store

Consult the Amazon Simple Storage documentation for additional details on using
Amazon's service. 

We have one more store to go over: the Documentum store. 

#### Using the Documentum Store [](id=using-the-documentum-store)


![EE Only Feature](../../images/ee-feature-web.png)

If you have a Liferay Portal EE activation key, you have access to the
Documentum hook which adds support for Documentum to Liferay's Documents and
Media library. The Documentum hook is included in the Documentum Connector EE
app which you can download and install from Liferay Marketplace. 

This hook doesn't add an option to make the Liferay repository into a Documentum
repository, as the other store implementations do. Instead, it gives you the
ability to mount Documentum repositories via the Documents and Media library UI. 

There's not really a lot to this; it's incredibly easy. Click *Add* &rarr;
*Repository*, and in the form that appears, choose *Documentum* as the
repository type. After that, enter a name for the repository, specify the
location of the Documentum repository and cabinet, and Liferay mounts the
repository for you. That's really all there is to it. If all your nodes are
pointing to a Documentum repository, you can cluster Documentum to achieve
higher performance. 

Now that we've covered the available ways you can configure Documents and Media
for clustering, let's move on to configuring search. 

## Clustering Search [](id=clustering-search)

You can configure search for clustering in one of two ways: you can use
pluggable enterprise search (recommended) or you can configure Lucene so indexes
replicate across the individual file systems of the nodes in the cluster. We'll
look at both ways to do this. 

### Using Pluggable Enterprise Search [](id=using-pluggable-enterprise-search)

As an alternative to using Lucene, Liferay supports pluggable search engines.
The first implementation of this uses the open source search engine *Solr*, but
in the future there will be many such plugins for your search engine of choice.
This allows you to use a completely separate product for search, and this
product can be installed on another application server or cluster of servers.
Your search engine then operates completely independently of your Liferay Portal
nodes in a clustered environment, acting as a search service for all the nodes
simultaneously.

This makes it much easier to deal with search indexes. You no longer have to
maintain indexes on every node in your cluster, and you get to offload indexing
activity to a separate server, so your nodes can concentrate their CPU power on
serving pages. Each Liferay node sends requests to the search engine to update
the search index when needed, and these updates are then queued and handled
automatically by the search engine, independently. It's kind of like having an
army of robots ready and willing to do your bidding. 

First, you'll need to configure your Solr server, and then you need to install
Liferay's Solr plugin to redirect searches over to it. 

#### Configuring the Solr Search Server [](id=configuring-the-solr-search-server)

Since Solr is a standalone search engine, you'll need to download it and install
it first according to the instructions on the Solr web site
(`http://lucene.apache.org/solr`). Of course, it's best to use a server that is
separate from your Liferay installation, as your Solr server becomes responsible
for all indexing and searching for your entire cluster. You definitely don't
want both Solr and Liferay on the same box. Solr is distributed as a `.war` file
with several `.jar` files which need to be available on your application
server's classpath. Once you have Solr up and running, integrating it with
Liferay is easy, but it requires a restart of your application server.

The first thing you need to define on the Solr box is the location of your
search index. Assuming you're running a Linux server and you've mounted a file
system for the index at `/solr`, create an environment variable that points to
this folder. This environment variable needs to be called `$SOLR_HOME`. So for
our example, we would define:

    $SOLR_HOME=/solr

This environment variable can be defined anywhere you need: in your operating
system's start up sequence, in the environment for the user who is logged in, or
in the start up script for your application server. If you're using Tomcat to
host Solr, modify `setenv.sh` or `setenv.bat` and add the environment variable
there.

Once you've created the environment variable, you then can use it in your
application server's start up configuration as a parameter to your JVM. This is
configured differently per application server, but again, if you're using
Tomcat, edit `catalina.sh` or `catalina.bat` and append the following to the
`$JAVA_OPTS` variable:

    -Dsolr.solr.home=$SOLR_HOME

This takes care of telling Solr where to store its search index. After you have
Solr installed and the configuration finished, shut it down, as there is some
more configuration to do.

#### Installing the Solr Liferay Plugin [](id=installing-the-solr-liferay-plugin)

Next, you have a choice. If you have installed Solr on the same system upon
which Liferay is running (not recommended), you can simply go to the Liferay
Marketplace and install the *solr-web* plugin. This, however, defeats much of
the purpose of using Solr, because the goal is to offload search indexing to
another box to free up processing for your installation of Liferay. For this
reason, you really shouldn't run Liferay and your search engine on the same box.
Unfortunately, the configuration in the plugin is set exactly that way,
presumably to allow you to experiment with different search configurations. To
run them separately--as you would in a production environment--, you'll have to
make a change to a configuration file in the plugin before you install it so you
can tell Liferay where to send indexing requests. In this case, go to the
Liferay Marketplace and download the plugin to your system. 

Open or extract the plugin. Inside the plugin, you'll find a file called
`solr-spring.xml` in the `WEB-INF/classes/META-INF` folder. Open this file in a
text editor and you will see the entry which defines where the Solr server can
be found by Liferay:

    <bean class="com.liferay.portal.spring.context.PortletBeanFactoryPostProcessor" />

    <!-- Solr search engine -->

    <bean id="com.liferay.portal.search.solr.server.BasicAuthSolrServer" class="com.liferay.portal.search.solr.server.BasicAuthSolrServer">
        <constructor-arg type="java.lang.String" value="http://localhost:8080/solr" />
        
    </bean>
	
Modify this value so it points to the server where Solr is running. Then save
the file and put it back into the plugin archive in the same place it was
before.

Next, extract the file `schema.xml` from the plugin. It should be in the
`docroot/WEB-INF/conf` folder. This file tells Solr how to index the data coming
from Liferay, and can be customized for your installation. Copy this file to
`$SOLR_HOME/conf` on your Solr box (you may have to create the `conf`
directory).

Before you start Solr, you should provide Solr with a list of **synonyms** and
**stop words**. Synonyms are words that should be equivalent in search. For
example, if a user searches for *important information*, you may want to
show results for *required* *information* or *critical information*. You
can define these in `synonyms.txt`. Stop words are defined in `stopwords.txt`
and are words that should not be indexed: articles, pronouns, and other words
that have little value in a search. Place these files in your
`$SOLR_HOME/conf` folder. Examples for both of these files are found in the
Solr archive in the `solr-4.1.0/example/solr/collection1/conf` folder.
Additional Solr configuration options, most importantly `solrconfig.xml` and
`elevate.xml`, are in the `$SOLR_HOME/conf` folder. Now you can start Solr.
After Solr has started, hot deploy the `solr-web` plugin to all your nodes. See
the next section for instructions on hot deploying to a cluster.

Once the plugin is hot deployed, your Liferay server's search is automatically
upgraded to use Solr. It's likely, however, that initial searches will come up
with nothing: this is because you need to reindex everything using Solr.

Navigate to the Control Panel. Under the Configuration heading, click on *Server
Administration*. On the Resources tab, click the *Execute* button next to
*Reindex all search indexes*. When you click this button, Liferay begins sending
indexing requests to Solr for execution. Once Solr has indexed all your data,
you'll have a search server running independently of all your Liferay nodes.

Installing the plugin to your nodes has the effect of overriding any calls to
Lucene for searching. All Liferay's search boxes will now use Solr as the search
index. This is ideal for a clustered environment, as it allows all your nodes to
share one search server and one search index, and this search server operates
independently of all your nodes. If, however, you don't have the server hardware
upon which to install a separate search server, you can sync the search indexes
between all your nodes, as is described next. 

### Clustering Lucene Indexes on All Nodes [](id=clustering-lucene-indexes-on-all-nodes)

Lucene, the search indexer which Liferay uses, can be configured to sync indexes
across each cluster node. This is the easiest configuration to implement, though
of course, it's not as "clean" a configuration as using pluggable enterprise
search. Sometimes, however, you just don't have another server to use for search
indexing, so you need a way to keep all your nodes in sync. By default, Liferay
provides a method called Cluster Link which can send indexing requests to all
nodes in the cluster to keep them in sync. This configuration doesn't require
any additional hardware, and it performs very well. It may increase network
traffic when an individual server reboots, since a full reindex will be needed.
But this should rarely happen, making it a good tradeoff if you don't have the
extra hardware to implement a Solr search server.

You can enable Cluster Link by setting the following property in your
`portal-ext.properties` file:

    cluster.link.enabled=true
 
To cluster your search indexes, you also need to set the following property:

    lucene.replicate.write=true
 
If you have `cluster.link.enabled=true` but `lucene.replicate.write=false`,
you'll enable cache replication but not index replication.

Of course, `cluster.link.enabled=true` and `lucene.replicate.write=true` need to
be set on all your nodes. That's all you need to do to sync your indexes. Pretty
easy, right? Of course, if you have existing indexes, you'll want to reindex as
described in the previous section once you have Cluster Link enabled on all your
nodes. 

## Distributed Caching [](id=distributed-caching)

Enabling Cluster Link automatically activates distributed caching. Distributed
caching enables some RMI (Remote Method Invocation) cache listeners that are
designed to replicate the cache across a cluster. 

Liferay uses [Ehcache](www.ehcache.org), which has robust distributed caching
support. This means that the cache can be distributed across multiple Liferay
nodes running concurrently. Enabling this cache can increase performance
dramatically. For example, suppose that two users are browsing the message
boards. The first user clicks a thread to read it. Liferay must look up that
thread from the database and format it for display in the browser. With a
distributed Ehcache running, this thread is stored in a cache for quick
retrieval, and that cache is then replicated to the other nodes in the cluster.
Suppose then that the second user who is being served by another node in the
cluster wants to read the same forum thread and clicks on it. This time, the
data is retrieved more quickly. Because the thread is in the cache, no trip to
the database is necessary. 

This is much more powerful than having a cache running separately on each node.
The power of *distributed* caching allows for common portal destinations to be
cached for multiple users. The first user can post a message to the thread he or
she was reading, and the cache is updated across all the nodes, making the new
post available immediately from the local cache. Without that, the second user
would need to wait until the cache was invalidated on the node he or she
connected to before he or she could see the updated forum post. 

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

### Enhanced Distributed Cache Algorithm [](id=enhanced-distributed-cache-algorithm)

![EE Only Feature](../../images/ee-feature-web.png)

By default, Liferay's distributed cache uses the RMI replication mechanism,
which uses a point to point communication topology. As you can guess, this kind
of structure doesn't scale well for a large cluster with many nodes. Because
each node has to send the same event to other nodes `N - 1` times, network
traffic becomes a bottleneck when `N` is too large. Ehcache also has a
performance issue of its own, in that it creates a replication thread for each
cache entity. In a large system like Liferay Portal, it's very common to have
more than 100 cached entities. This translates to 100+ cache replication
threads. Threads are expensive, because they take resources (memory and CPU
power). Most of the time, these threads are sleeping, because they only need to
work when a cached entity has to talk to remote peers. 

![Figure 5.5: The default algorithm requires each node to create massive amounts of dispatch threads to update the cache for each node in the cluster.](../../images/19-ehcache-inefficient-algorithm.png)

Putting heap memory aside (because the amount of memory on the heap depends on
the application(s) running), consider the stack memory footprint of those 100+
threads. By default on most platforms, the thread stack size is 2 MB; for 100
threads, that's more than 200 MB. If you include the heap memory size, this
number can become as high as 500 MB for just one node. And that massive amount
of threads can also cause frequent context switch overhead, which translates to
increased CPU cycles.

For large installations containing many nodes, Liferay has developed an enhanced
algorithm for handling cache replication that can can fix both the `1` to `N -
1` network communication bottleneck, as well as the massive threads bottleneck.
The default implementation uses JGroups' UDP multicast to communicate. 

![Figure 5.6: Liferay's algorithm uses a single UDP multicast channel, so that nodes don't have to create a thread for each other node in the cluster.](../../images/19-ehcache-efficient-algorithm.png)

To reduce the number of replication threads, we provide a small pool of
dispatching threads. These deliver cache cluster events to remote peers. Since
all cache entities' cluster events must go through our pool of dispatching
threads to communicate, this gives us a chance to coalesce events: if two
modifications to the same cache object happen at almost the same time, we can
combine the changes into one, and then we only need to notify remote peers once.
This reduces traffic on the network. We should also note that newer versions of
Ehcache support the JGroups replicator and can also fix the `1` to `N - 1`
network communication; however, they cannot fix the massive threads issue and
they cannot coalesce cache events.

For EE customers who are interested in this feature, all you have to do to
enable the enhanced algorithm is to install a plugin from the Liferay
Marketplace and set the following property in the `portal-ext.properties` files
of each of your nodes:

    ehcache.cluster.link.replication.enabled=true

Search Liferay Marketplace for the *Ehcache Cluster EE* plugin, which is free to
all EE customers, and install it on each of your nodes. The new algorithm is
immediately activated and you can reap the benefits right away.

Next, let's discuss how to modify your Ehache settings. As we've seen, it's easy
to use the default Ehcache settings just by enabling Cluster Link. If you need
to tweak the cache for your site, you have two options: you can modify Ehcache
settings with a plugin or you can modify them directly.

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

### Sharing a Search Index (not recommended unless you have a file locking-aware SAN) [](id=sharing-a-search-index-not-recommended-unless-you-have-a-file-locking-aware)

If you wish to have a shared index (and we really hope you don't), you'll need
to either share the index on the file system or in the database. This requires
changing your Lucene configuration.

The Lucene configuration can be changed by modifying values in your
`portal-ext.properties` file. Open your `portal.properties` file and search for
the text *Lucene*. Copy that section and then paste it into your
`portal-ext.properties` file.

If you wish to store the Lucene search index on a file system that is shared by
all of the Liferay nodes (not recommended: you've been warned), you can modify
the location of the search index by changing the `lucene.dir` property. By
default, this property points to the `lucene` folder inside the Liferay home
folder:

    lucene.dir=${liferay.home}/data/lucene/

Change this to the folder of your choice. You'll need to restart Liferay for the
changes to take effect. You can point all of the nodes to this folder and they
will use the same index.

Like Jackrabbit, however, this is not the best way to share the search index, as
it could result in file corruption if different nodes try reindexing at the same
time. We do not recommend this for a production system. A better way (though
still not great) is to share the index via a database, where the database can
enforce data integrity on the index. This is very easy to do; it is a simple
change to your `portal-ext.properties` file. Of course, we also don't recommend
this for a production system, as accessing the index from a database will be
slower than from a file system. If, however, you have no other option and want
to do this anyway, keep reading. 

There is a single property called `lucene.store.type`. By default this is set to
go to the file system. You can change this so that the index is stored in the
database by making it the following:

    lucene.store.type=jdbc

The next time Liferay is started, new tables are created in the Liferay
database, and the index is stored there. If all the Liferay nodes point to the
same database tables, they will be able to share the index. Again, performance
on this is not very good. Your DBAs may be able to tweak the database indexes a
bit to improve performance. For better performance, you should consider using a
separate search server or syncing the indexes on the nodes' file systems.

+$$$

**Note:** MySQL users need to modify their JDBC connection string for this to
work. Add the following parameter to your connection string:

`emulateLocators=true`

$$$

Alternatively, you can leave the configuration alone, and each node will have
its own index. This ensures against collisions when multiple nodes update the
index. However, the indices will quickly get out of sync since they don't
replicate. For this reason, this is not a recommended configuration either.
Again, for a better configuration, replicate the indexes with Cluster Link or
use a separate search server (see the section on Solr above).

Now we can look at the last consideration when clustering Liferay: hot deploy. 

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
