# Enterprise Configuration

Liferay Portal is a robust, enterprise-ready portal solution. As such, it is fully ready to support mission-critical, enterprise applications in an environment configured for multiple redundancies and 24/7 uptimes. The product, however, like other products of its kind, doesn't come configured this way out of the box, so there are some steps that need to be taken in order to tune it for your needs. 

This chapter covers these topics in detail. Liferay runs on so many different Java EE application servers that we can't cover all the differences between them. For this reason, we'll discuss Liferay configurations only. For example, we'll look at how to configure Liferay to work in a clustered environment, but not how to create the cluster in your application server. The documentation for your particular application server is always a much better place to learn those kinds of things.  

This chapter explains how to configure Liferay for a number of advanced scenarios, such as

- Clustering 
- Distributed Caching
- Deploying Customized versions of Liferay
- Performance Testing and Tuning

During this discussion, we'll mention a number of other open source products upon which Liferay relies for much of this functionality. These products all have their own documentation which should be consulted for a fuller view of what these products can do. For example, Liferay uses Ehcache for its caching mechanism. We'll cover how to configure Ehcache to enable caches in Liferay, but will refer you to that product's documentation for further information about that product.

Sometimes Liferay supports multiple products which perform the same function. There are, for example, several single sign-on implementations you can use with Liferay. We'll leave it up to you to select which product best fits the needs of your project without recommending one product over another.

With all of that said, let's get started configuring Liferay for the enterprise.

## Liferay Clustering 

Liferay Portal is designed to serve everything from the smallest to the largest web sites. Out of the box, it's configured optimally for a single server environment. If one server isn't sufficient to serve the high traffic needs of your site, Liferay scales to the size you need.  

![Figure 15.1: Liferay is designed to scale to as large an installation as you need.](../../images/enterprise-configuration.png) 

Liferay works well in clusters of multiple machines (horizontal cluster) or in clusters of multiple VMs on a single machine (vertical cluster), or any mixture of the two. Once you have Liferay installed in more than one application server node, there are several optimizations that need to be made. At a minimum, Liferay should be configured in the following way for a clustered environment:

- The Quartz scheduler should be set for a clustered environment. 
- All nodes should be pointing to the same Liferay database or database cluster. 
- Documents and Media repositories should be accessible to all nodes of the cluster. 
- Search should be configured for replication or should use a separate search server. 
- Hot deploy folders should be configured for each node if you're not using server farms. 

If you haven't configured your application server to use farms for deployment, the hot deploy folder should be a separate folder for all the nodes, and plugins will have to be deployed to all of the nodes individually. This can be done via a script. If you do have farms configured, you can deploy normally to any node's deploy folder, and your farm configuration should take care of syncing the deployment to all nodes. 

Many of these configuration changes can be made by adding or modifying properties in your `portal-ext.properties` file. Remember that this file overrides the defaults that are in the `portal.properties` file. The original version of this file can be found in the Liferay source code or can be extracted from the `portal-impl.jar` file in your Liferay installation. It is a best practice to copy the relevant section that you want to modify from `portal.properties` into your `portal-ext.properties` file, and then modify the values there.

---

![tip](../../images/01-tip.png)**Note:** This chapter documents a Liferay-specific cluster configuration, without getting into specific implementations of third party software, such as Java EE application servers, HTTP servers, and load balancers. Please consult your documentation for those components of your cluster for specific details of those components. Before configuring Liferay in a cluster configuration, make sure your OS is not defining the hostname of your box to the local network at 127.0.0.1.

---

We'll take each of the points above one by one in order to present a clear picture of how to cluster Liferay. 

### The Quartz scheduler should be set for a clustered environment

Liferay uses Quartz to run jobs on a schedule. When you write an application for Liferay, one of the things you can do is set up jobs like these. An example of this is the calendar: periodically, a job runs to check to see whether it's time to trigger a calendar event. The scheduler needs to know that it's in a cluster, or events might get triggered multiple times by different nodes. 

This is very easy to configure. It takes only one property in your `portal-ext.properties` file: 

	org.quartz.jobStore.isClustered=true
	
If you've already started Liferay and had your database generated, you'll need to drop all the `QUARTZ_` tables to make this work. 
	
Your first clustering task is already done! Let's move on to the database. 

### All nodes should be pointing to the same Liferay database

This is pretty self-explanatory. Each node should be configured with a data source that points to one Liferay database (or a database cluster) that all the nodes will share. This ensures that all the nodes operate from the same basic data set. This means, of course, that Liferay cannot (and should not) use the embedded HSQL database that is shipped with the bundles (but you already knew that, right?). And, of course, it goes without saying that the database server is a separate physical box from the server which is running Liferay. 

Beyond a database cluster, there are two more advanced options you can use to optimize your database configuration: a read-writer database configuration, and sharding. 

#### Read-Writer database configuration

Liferay allows you to use two different data sources for reading and writing. This enables you to split your database infrastructure into two sets: one that is optimized for reading and one that is optimized for writing. Since all major databases support replication in one form or another, you can then use your database vendor's replication mechanism to keep the databases in sync in a much faster manner than if you had a single data source which handled everything.

Enabling a read-writer database is simple. In your `portal-ext.properties` file, configure two different data sources for Liferay to use, one for reading, and one for writing:

    jdbc.read.driverClassName=com.mysql.jdbc.Driver
    jdbc.read.url=jdbc:mysql://dbread.com/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false     jdbc.read.username=**your user name**
    jdbc.read.password=**your password**
    jdbc.write.driverClassName=com.mysql.jdbc.Driver
    jdbc.write.url=jdbc:mysql://dbwrite.com/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false     jdbc.write.username=**your user name**
    jdbc.write.password=**your password**

Of course, specify the user name and password to your database in the above configuration.

After this, enable the read-writer database configuration by uncommenting the Spring configuration file which enables it in your `spring.configs` property (line to uncomment is in bold):

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

The next time you restart Liferay, it will now use the two data sources you have defined. Be sure to make sure that you have correctly set up your two databases for replication before starting Liferay.

Next, we'll look at database sharding. 

#### Database Sharding

Liferay starting with version 5.2.3 supports database sharding for different portal instances. Sharding is a term used to describe an extremely high scalability configuration for systems with massive amounts of users. In diagrams, a database is normally pictured as a cylinder. Instead, picture it as a glass bottle full of data. Now take that bottle and smash it onto a concrete sidewalk. There will be shards of glass everywhere. If that bottle were a database, each shard now is a database, with a subset of the data in each shard.

This allows you to split up your database by various types of data that might be in it. For example, some implementations of sharding a database split up the users: those with last names beginning with A to D go in one database; E to I go in another; etc. When users log in, they are directed to the instance of the application that is connected to the database that corresponds to their last names. In this manner, processing is split up evenly, and the amount of data the application needs to sort through is reduced.

By default, Liferay allows you to support sharding through different portal instances, using the *round robin shard selector*. This is a class which serves as the default algorithm for sharding in Liferay. Using this algorithm, Liferay selects from several different portal instances and evenly distributes the data across them. Alternatively, you can use the manual shard selector. In this case, you'd need to use the UI provided in the control panel to configure your shards. 

Of course, if you wish to have your developers implement your own sharding algorithm, you can do that. This is a great use of the Ext plugin. You can select which algorithm is active via the `portal-ext.properties` file:

    shard.selector=com.liferay.portal.dao.shard.RoundRobinShardSelector
    #shard.selector=com.liferay.portal.dao.shard.ManualShardSelector
    #shard.selector=[your implementation here]

Enabling sharding is easy. You'll need to make sure you are using Liferay's data source implementation instead of your application server's. Set your various database shards in your `portal-ext.properties` file this way:

    jdbc.default.driverClassName=com.mysql.jdbc.Driver
    jdbc.default.url=jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
    jdbc.default.username=
    jdbc.default.password=
    jdbc.one.driverClassName=com.mysql.jdbc.Driver
    jdbc.one.url=jdbc:mysql://localhost/lportal1?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false     jdbc.one.username=
    jdbc.one.password=
    jdbc.two.driverClassName=com.mysql.jdbc.Driver
    jdbc.two.url=jdbc:mysql://localhost/lportal2?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
    jdbc.two.username=
    jdbc.two.password=
    shard.available.names=default,one,two

Once you do this, you can set up your DNS so that several domain names point to your Liferay installation (e.g., abc1.com, abc2.com, abc3.com). Next, go to the control panel and click *Portal Instances* in the Server category. Create two to three instances bound to the DNS names you have configured.

If you're using the RoundRobinShardSelector class, Liferay automatically enters data into each instance one by one. If you're using the `ManualShardSelector` class, you'll have to specify a shard for each instance using the UI.

![Figure 15.2: When creating a shard using the manual shard selector, specify the shard you want to use for that instance.](../../images/enterprise-sharding-portal-instance.png)

The last thing you need to do is modify the `spring.configs` section of your `portal-ext.properties` file to enable the sharding configuration, which by default is commented out. To do this, your `spring.configs` should look like this (modified section is in bold):

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
        META-INF/audit-spring.xml,\
        META-INF/cluster-spring.xml,\
        META-INF/editor-spring.xml,\
        META-INF/executor-spring.xml,\
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
        \
        #META-INF/dynamic-data-source-spring.xml,\
        **META-INF/shard-data-source-spring.xml,\**
        #META-INF/memcached-spring.xml,\
        #META-INF/monitoring-spring.xml,\
        \
        META-INF/ext-spring.xml

That's all there is to it. Your system is now set up for sharding. Now that you've got your database set up and optimized for a large installation, let's turn to clustering the Documents and Media Library. 

### Documents and Media Library clustering

Liferay 6.1 introduces a new Documents and Media Library which is capable of mounting several repositories at a time and presenting a unified interface to the user. By default, users can make use of the Liferay repository, which is already mounted. This repository is built into Liferay Portal and can use as its back-end one of several different store implementations. In addition to this, many different kinds of third party repositories can be mounted. If you have a separate repository that you've mounted, all nodes of the cluster will point to this repository. Your avenue for improving performance at that point is to cluster your third party repository, using the documentation for the repository you have chosen. If you don't have a third party repository, there are ways you can configure the Liferay repository to perform well in a clustered configuration. 

The main thing to keep in mind is that you need to make sure that every node of the cluster has the same access to the file store as every other node. For this reason, you'll need to take a look at your store configuration. 

There are several options available for configuring how Liferay's Documents and Media library stores files. Each option is a *store* which can be configured through the `portal-ext.properties` file by setting the `dl.store.impl=` property. Let's consider the ramifications of the various store options. 

#### Using the File System store

This is the default store. It's a simple file storage implementation that uses a local folder to store files. You can use the file system for your clustered configuration, but you'd have to make sure that the folder to which you point the store can handle things like concurrent requests and file locking. For this reason, you need to use a Storage Area Network or a clustered file system.

The file system store was the first store created for Liferay and is heavily bound to the Liferay database. By default, documents are stored in a `document_library` subfolder of the `data` folder in a Liferay bundle. Of course, you can change this path to anything you want by using the `dl.store.file.system.root.dir=` property. 

This store creates a folder structure based on primary keys in the Liferay database. If, for example, you upload a presentation with the file name `workflow.odp` into a folder called *stuff*, the file system store creates a folder structure which looks like figure 15.x. 

![Figure 15.3: Liferay's file system store creates a folder structure based on primary keys in Liferay's database. ](../../images/enterprise-file-system-store.png)

The first folder is the company ID to which the site belongs. The second folder is the group ID of the site where the document resides. The third is the ID of the document itself, and finally the file name of the document is renamed to a version number for storing multiple versions of the document. 

As you can see, this binds your documents very closely to Liferay, and may not be exactly what you want. But if you've been using the default settings for a while and need to migrate your documents, Liferay provides a migration utility in the control panel in *Server Administration* &rarr; *Data Migration*. Using this utility, you can move your documents very easily from one store implementation to another. 

Speaking of other store implementations, let's look at some others that Liferay provides. 

#### Using the Advanced File System store

Liferay's advanced file system store is similar to the default file system store. Like that store, it saves files to the local file system--which, of course, could be a remote file system mount. It uses a slightly different folder structure to store files, which is pictured below. 

![Figure 15.4: The advanced file system store creates a more nested folder structure than the file system store. ](../../images/enterprise-adv-file-system-store.png)

So what makes the advanced file system store *advanced*? Several operating systems have limitations on the number of files which can be stored in a particular folder. The advanced file system store overcomes this limitation by programmatically creating a structure that can expand to millions of files, by alphabetically nesting the files in folders. This not only allows for more files to be stored, but also improves performance as there are less files stored per folder. 

The same rules apply to the advanced file system store as apply to the default file system store. To cluster this, you'll need to point the store to a network mounted file system that all the nodes can access, and that networked file system needs to support concurrent requests and file locking. Otherwise, you may experience data corruption issues if two users attempt from two different nodes to write to the same file at the same time. 

You may decide that the advanced file system store for whatever reason doesn't serve your needs. If this is the case, you can of course mount other file systems into the documents and media library. In addition to this, you can also redefine the Liferay store to use one of three other supported protocols. We'll look at these next. 

#### Using the CMIS store

Though you can mount as many different CMIS (Content Management Interoperability Services) repositories as you like in the documents and media library, you may wish also to redefine the Liferay repository to point to a CMIS repository as well. Why? Because, as you know, users are users, and it's possible that they may find a way to create a folder or upload content to the Liferay repository. It would be nice if that Liferay repository was connected to a clustered CMIS repository by the administrator without having to mount it through the UI. The CMIS store allows you to do just that. 

If you wish to use the CMIS store, all you need to do is set the following four directives in your `portal-ext.properties` file: 

    dl.store.cmis.credentials.username=yourusername
    dl.store.cmis.credentials.password=yourpassword
    dl.store.cmis.repository.url=http://localhost:8080/url/to/your/cmis/repository
    dl.store.cmis.system.root.dir=Liferay Home

Now the Liferay repository is connected to CMIS via the CMIS store. As long as all nodes are pointing to your CMIS repository, everything in your Liferay cluster should be fine, as the CMIS protocol prevents multiple simultaneous file access from causing data corruption. 

From here, we'll move on to the JCR store. 
    
#### Using the JCR store

Liferay Portal supports as a store the Java Content Repository standard. Under the hood, Liferay uses Jackrabbit—-which is a project from Apache-—as its JSR-170 compliant document repository. By default, Jackrabbit is configured to store the documents on the local file system upon which Liferay is installed, in the `[Liferay Home]/liferay/jackrabbit` folder. Inside this folder is Jackrabbit's configuration file, called `repository.xml`. 

Using the default settings, the JCR store is not very different from the file system stores, except that you can use any JCR client to access the files. You can, however, modify Jackrabbit's configuration so that it stores files in a database that can be accessed by all nodes, and so that it operates as a cluster within Liferay's cluster. 

To move the default repository location to a shared folder, you do not need to edit Jackrabbit's configuration file. Instead, find the section in `portal.properties` labeled **JCR** and copy/paste that section into your `portal-ext.properties` file. One of the properties, by default, is the following:

    jcr.jackrabbit.repository.root=${liferay.home}/data/jackrabbit

Change this property to point to a shared folder that all the nodes can see. A new Jackrabbit configuration file is then generated in that location, and you'll have to edit that file to modify Jackrabbit's configuration. 

Note that because of file locking issues, this isn't the best way to share Jackrabbit resources, unless you're using a networked file system that can handle concurrency and file locking. If you have two people logged in at the same time uploading content, you could encounter data corruption using this method, and because of this, we don't recommend it for a production system. Instead, if you want to use the Java Content Repository in a cluster, you should redirect Jackrabbit into your database of choice. You can use the Liferay database or another database for this purpose. This requires editing Jackrabbit's configuration file.

The default Jackrabbit configuration file has sections commented out for moving the Jackrabbit configuration into the database. This has been done to make it as easy as possible to enable this configuration. To move the Jackrabbit configuration into the database, simply comment out the sections relating to the file system and comment in the sections relating to the database. These by default are configured for a MySQL database. If you are using another database, you will likely need to modify the configuration, as there are changes to the configuration file that are necessary for specific databases. For example, the default configuration uses Jackrabbit's `DbFileSystem` class to mimic a file system in the database. While this works well in MySQL, it doesn't work for all databases. For example, if you're using an Oracle database, you'll need to modify this to use `OracleFileSystem`. 

Modify the JDBC database URLs so that they point your database. This, of course, must be done on all nodes of the cluster. Don't forget to create the database first, and grant the user ID you are specifying in the configuration file access to create, modify, and drop tables. After this, be sure to uncomment the `<Cluster/>` section at the bottom of the file. For further information, it's best to check out the Jackrabbit documentation. Please see the Jackrabbit documentation at `http://jackrabbit.apache.org` for further information. 

Once you've configured Jackrabbit to store its repository in a database, the next time you bring up Liferay, the necessary database tables are created automatically. Jackrabbit, however, does not create indexes on these tables, and so over time this can be a performance penalty. To fix this, you'll need to manually go into your database and index the primary key columns for all the Jackrabbit tables.

Note that this configuration doesn't perform as well as the advanced file system store, because you're storing documents in a database instead of in the file system. But it does have the benefit of clustering well. Next, we'll look at Amazon's S3 store. 

##### Using Amazon Simple Storage Service

Amazon's simple storage service (S3) is a cloud-based storage solution which you can use with Liferay. All you need is an account, and you can store your documents to the cloud from all nodes, seamlessly. 

This is easy to set up. When you sign up for the service, Amazon assigns you some unique keys which link you to your account. In Amazon's interface, you can create "buckets" of data optimized by region. Once you've created these to your specifications, all you need to do is declare them in `portal-ext.properties`: 

    dl.store.s3.access.key=
    dl.store.s3.secret.key=
    dl.store.s3.bucket.name=

Once you have these configured, set your store implementation to the `S3Store`: 

    dl.store.impl=com.liferay.portlet.documentlibrary.store.S3Store

Consult the Amazon Simple Storage documentation for additional details on using Amazon's service. 

We have one more store to go over: the Documentum store. 

##### Using the Documentum store

![EE Only Feature](../../images/ee-only-image/ee-feature-web.png)

If you have a Liferay Portal EE license, you have access to the Documentum hook which adds support for Documentum to Liferay's Documents and Media library. Install this hook by using the Liferay Marketplace. 

This hook doesn't add an option to make the Liferay repository into a Documentum repository, as the other store implementations do. Instead, it gives you the ability to mount Documentum repositories via the Documents and Media library UI. 

There's not really a lot to this; it's incredibly easy. Click *Add* &rarr; *Repository*, and in the form that appears, choose *Documentum* as the repository type. After that, give it a name and specify the Documentum repository and cabinet, and Liferay mounts the repository for you. That's really all there is to it. If all your nodes are pointing to a Documentum repository, you can cluster Documentum to achieve higher performance. 

Now that we've covered the available ways you can configure documents and media for clustering, we can move on to configuring search. 

### Clustering search

You can configure search for clustering in one of two ways: use pluggable enterprise search (recommended), or configure Lucene so that indexes replicate across the individual file systems of the nodes in the cluster. We'll look at both ways to do this. 

#### Using Pluggable Enterprise Search

As an alternative to using Lucene, Liferay supports pluggable search engines. The first implementation of this uses the open source search engine *Solr*, but in the future there will be many such plugins for your search engine of choice. This allows you to use a completely separate product for search, and this product can be installed on another application server or cluster of servers. Your search engine then operates completely independently of your Liferay Portal nodes in a clustered environment, acting as a search service for all the nodes simultaneously.

This makes it much easier to deal with search indexes. You no longer have to maintain indexes on every node in your cluster, and you get to offload indexing activity to a separate server, so your nodes can concentrate their CPU power on serving pages. Each Liferay node sends requests to the search engine to update the search index when needed, and these updates are then queued and handled automatically by the search engine, independently. It's kind of like having an army of robots ready and willing to do your bidding. 

First, you'll need to configure your Solr server, and then you need to install Liferay's Solr plugin to redirect searches over to it. 

##### Configuring the Solr Search Server

Since Solr is a standalone search engine, you'll need to download it and install it first according to the instructions on the Solr web site (`http://lucene.apache.org/solr`). Of course, it's best to use a server that is separate from your Liferay installation, as your Solr server becomes responsible for all indexing and searching for your entire cluster. You definitely don't want both Solr and Liferay on the same box. Solr is distributed as a `.war` file with several `.jar` files which need to be available on your application server's classpath. Once you have Solr up and running, integrating it with Liferay is easy, but it requires a restart of your application server.

The first thing you need to define on the Solr box is the location of your search index. Assuming you're running a Linux server and you've mounted a file system for the index at `/solr`, create an environment variable that points to this folder. This environment variable needs to be called `$SOLR_HOME`. So for our example, we would define:

    $SOLR_HOME=/solr

This environment variable can be defined anywhere you need: in your operating system's start up sequence, in the environment for the user who is logged in, or in the start up script for your application server. If you're using Tomcat to host Solr, modify `setenv.sh` or `setenv.bat` and add the environment variable there.

Once you've created the environment variable, you then can use it in your application server's start up configuration as a parameter to your JVM. This is configured differently per application server, but again, if you're using Tomcat, edit `catalina.sh` or `catalina.bat` and append the following to the `$JAVA_OPTS` variable:

    -Dsolr.solr.home=$SOLR_HOME

This takes care of telling Solr where to store its search index. Go ahead and install Solr to this box according to the instructions on the Solr web site (`http://lucene.apache.org/solr`). Once it's installed, shut it down, as there is some more configuration to do.

##### Installing the Solr Liferay Plugin

Next, you have a choice. If you have installed Solr on the same system upon which Liferay is running (not recommended), you can simply go to the Liferay Marketplace and install the *solr-web* plugin. This, however, defeats much of the purpose of using Solr, because the goal is to offload search indexing to another box in order to free up processing for your installation of Liferay. For this reason, you really shouldn't run Liferay and your search engine on the same box. Unfortunately, the configuration in the plugin is set exactly that way, presumably to allow you to experiment with different search configurations. To run them separately--as you would in a production environment--, you'll have to make a change to a configuration file in the plugin before you install it so you can tell Liferay where to send indexing requests. In this case, go to the Liferay Marketplace and download the plugin to your system. 

Open or extract the plugin. Inside the plugin, you'll find a file called `solr-spring.xml` in the `WEB-INF/classes/META-INF` folder. Open this file in a text editor and you will see the entry which defines where the Solr server can be found by Liferay:

	<bean class="com.liferay.portal.spring.context.PortletBeanFactoryPostProcessor" />

	<!-- Solr search engine -->

	<bean id="com.liferay.portal.search.solr.server.BasicAuthSolrServer" class="com.liferay.portal.search.solr.server.BasicAuthSolrServer">
		<constructor-arg type="java.lang.String" value="http://localhost:8080/solr" />
	</bean>
	
Modify this value so it points to the server where Solr is running. Then save the file and put it back into the plugin archive in the same place it was before.

Next, extract the file `schema.xml` from the plugin. It should be in the `docroot/WEB-INF/conf` folder. This file tells Solr how to index the data coming from Liferay, and can be customized for your installation. Copy this file to `$SOLR_HOME/conf` on your Solr box (you may have to create the `conf` directory). Now you can go ahead and start Solr.

You can now hot deploy the `solr-web` plugin to all your nodes. See the next section for instructions on hot deploying to a cluster.

Once the plugin is hot deployed, your Liferay server's search is automatically upgraded to use Solr. It's likely, however, that initial searches will come up with nothing: this is because you need to reindex everything using Solr.

Go to the control panel. In the *Server* section, click *Server Administration*. Click the *Execute* button next to *Reindex all search indexes* at the bottom of the page. Liferay will begin sending indexing requests to Solr for execution. Once Solr has indexed all your data, you'll have a search server running independently of all your Liferay nodes.

Installing the plugin to your nodes has the effect of overriding any calls to Lucene for searching. All Liferay's search boxes will now use Solr as the search index. This is ideal for a clustered environment, as it allows all your nodes to share one search server and one search index, and this search server operates independently of all your nodes. If, however, you don't have the server hardware upon which to install a separate search server, you can sync the search indexes between all your nodes, as is described next. 

#### Clustering Lucene indexes on all nodes

Lucene, the search indexer which Liferay uses, can be configured to sync indexes across each cluster node. This is the easiest configuration to implement, though of course, it's not as "clean" a configuration as using pluggable enterprise search. Sometimes, however, you just don't have another server to use for search indexing, and so you need a way to keep all your nodes in sync. Liferay provides a method called Cluster Link which can send indexing requests to all nodes in the cluster to keep them in sync. This configuration doesn't require any additional hardware, and it performs very well. It may increase network traffic when an individual server reboots, since in that case a full reindex will be needed. But since this should only rarely happen, it's a good tradeoff if you don't have the extra hardware to implement a Solr search server.

You can enable Cluster Link by setting one property in your `portal-ext.properties `file:

    cluster.link.enabled=true

Of course, this needs to be set on all the nodes. That's all you need to do to sync your indexes. Pretty easy, right? Of course, if you have existing indexes, you'll want to do a reindex as described in the previous section once you have Cluster Link enabled on all your nodes.  

Next, we'll show how to share indexes in a database. This is actually not a recommended configuration, as it's slow (databases are always slower than file systems), but for completeness, we'll go ahead and tell you how to do it anyway. But you've been forewarned: it's far better to use one of the other methods of clustering your search index. 

#### Sharing a search index (not recommended unless you have a file locking-aware SAN) 

If you wish to have a shared index (and we really hope you don't), you'll need to either share the index on the file system or in the database. This requires changing your Lucene configuration.

The Lucene configuration can be changed by modifying values in your `portal-ext.properties` file. Open your `portal.properties` file and search for the text *Lucene*. Copy that section and then paste it into your `portal-ext.properties` file.

If you wish to store the Lucene search index on a file system that is shared by all of the Liferay nodes (not recommended: you've been warned), you can modify the location of the search index by changing the `lucene.dir` property. By default, this property points to the `lucene` folder inside the Liferay home folder:

    lucene.dir=${liferay.home}/data/lucene/

Change this to the folder of your choice. You'll need to restart Liferay for the changes to take effect. You can point all of the nodes to this folder and they will use the same index.

Like Jackrabbit, however, this is not the best way to share the search index, as it could result in file corruption if different nodes try reindexing at the same time. We do not recommend this for a production system. A better way (though still not great) is to share the index via a database, where the database can enforce data integrity on the index. This is very easy to do; it is a simple change to your `portal-ext.properties` file. Of course, we also don't recommend this for a production system, as accessing the index from a database will be slower than from a file system. If, however, you have no other option and want to do this anyway, keep reading. 

There is a single property called `lucene.store.type`. By default this is set to go to the file system. You can change this so that the index is stored in the database by making it the following:

    lucene.store.type=jdbc

The next time Liferay is started, new tables are created in the Liferay database, and the index is stored there. If all the Liferay nodes point to the same database tables, they will be able to share the index. Again, performance on this is not very good. Your DBAs may be able to tweak the database indexes a bit to improve performance. For better performance, you should consider using a separate search server or syncing the indexes on the nodes' file systems.

---

![tip](../../images/01-tip.png)**Note:** MySQL users need to modify their JDBC connection string for this to work. Add the following parameter to your connection string:

    emulateLocators=true

---

Alternatively, you can leave the configuration alone, and each node will have its own index. This ensures against collisions when multiple nodes update the index. However, the indices will quickly get out of sync since they don't replicate. For this reason, this is not a recommended configuration either. Again, for a better configuration, replicate the indexes with Cluster Link or use a separate search server (see the section on Solr above).

Now we can look at the last consideration when clustering Liferay: hot deploy. 

### Hot Deploy

Plugins which are hot deployed will need to be deployed separately to all the Liferay nodes. The best way to do this is to configure your application server to support *farms*. This is a feature that enables you to deploy an application on one node and then it replicates automatically to each of the other nodes. This, of course, is configured differently for each application server, so you'll need to consult your application server's documentation to learn how to do this. It's by far the best way to handle hot deploy, and is the recommended configuration. If you have this working, great! You can skip the rest of this section completely.  

If for some reason your application server doesn't support this feature or you can't use it, you'll need to come up with a way to deploy applications across your cluster. Each node needs to have its own hot deploy folder. This folder needs to be writable by the user under which Liferay is running, because plugins are moved from this folder to a temporary folder when they are deployed. This is to prevent the system from entering an endless loop, because the presence of a plugin in the folder is what triggers the hot deploy process.

When you want to deploy a plugin to the entire cluster, copy that plugin to the hot deploy folders of all of the Liferay nodes. Depending on the number of nodes, it may be best to create a script to do this. Once the plugin has been deployed to all of the nodes, you can then make use of it (by adding the portlet to a page or choosing the theme as the look and feel for a page or page hierarchy).

All of the above will get basic Liferay clustering working; however, the configuration can be further optimized. We will see how to do this next.

## Distributed Caching

Liferay uses **Ehcache**, which has robust distributed caching support. This means that the cache can be distributed across multiple Liferay nodes running concurrently. Enabling this cache can increase performance dramatically. For example, say that two users are browsing the message boards. The first user clicks a thread in order to read it. Liferay must look up that thread from the database and format it for display in the browser. With a distributed Ehcache running, this thread is stored in a cache for quick retrieval, and that cache is then replicated to the other nodes in the cluster. Say then that the second user who is being served by another node in the cluster wants to read the same forum thread and clicks on it. This time, the data is retrieved more quickly. Because the thread is in the cache, no trip to the database is necessary. 

This is much more powerful than having a cache running separately on each node. The power of *distributed* caching allows for more functionality. The first user can post a message to the thread he or she was reading, and the cache is updated across all the nodes, making the new post available immediately from the local cache. Without that, the second user would need to wait until the cache was invalidated on the node he or she connected to before he or she could see the updated forum post. 

There are two ways to enable distributed caching. If you use the default settings, it's very easy. If you need to tweak the cache for your site, there are a few more steps, but it's still pretty easy. 

### Enabling distributed caching

The super-easy way of enabling distributed caching is simply to enable Cluster Link. If you've already done this to enable distributed search engine indexes, then your job is already done. What this does is enable some RMI (Remote Method Invocation) cache listeners that are designed to replicate the cache across a cluster. 

Once you enable distributed caching, of course, you should do some due diligence and test your system under a load that best simulates the kind of traffic that your system needs to handle. If you'll be serving up a lot of message board messages, your script should reflect that. If web content is the core of your site, your script should reflect that too. 

As a result of a load test, you may find that the default distributed cache settings aren't optimized for your site. In this case, you'll need to tweak the settings yourself. You can modify the Liferay installation directly or you can use a plugin to do it. Either way, the settings you change are the same. Let's see how to do this with a plugin first. 

#### Modifying the cache settings with a plugin

A benefit of working with plugins is that you can quickly install a plugin on each node of your cluster without taking down the cluster. We'll cover this first. If you're not a developer, don't worry--even though you'll create a plugin, you won't have to write any code. 

Since we're assuming you're an administrator and not a developer, we'll take the easiest route, and use Liferay's graphical development tools, rather than the command line Plugins SDK by itself. If you're a Liferay EE customer, download Liferay Developer Studio from the Customer Portal. Set it up with all the defaults from the first start wizard, and you're good to go (skip the next paragraph). 

If you're not a Liferay EE customer, download Eclipse and install Liferay IDE from the Eclipse Marketplace. Download the Plugins SDK for your edition of Liferay from either the Customer Portal (EE) or the Downloads page on liferay.com. Connect Liferay IDE to your Plugins SDK using the instructions found in the *Liferay Developer's Guide*. 

Next, create a hook plugin by selecting *File* &rarr; *New* &rarr; *Liferay Project*. Select *Hook* as the project type and give your project a name. Click *Finish*, and your project is created. 

In your project, create a text file called `portlet.properties` in the `docroot/WEB-INF/src` folder. This file can override properties in your portal just like `portal-ext.properties`. Into this file place the following three properties: 

	net.sf.ehcache.configurationResourceName=
	ehcache.single.vm.config.location=
	ehcache.multi.vm.config.location=
	
Liferay's configuration files are, of course, used by default. If you're overriding these properties, it's because you want to customize the configuration for your own site. A good way to start with this is to extract Liferay's configuration files and then customize them. If you're running an application server (such as Tomcat) that allows you to browse to the running instance of Liferay, you can extract Liferay's configuration files from Liferay itself. If you're not, you can extract them from Liferay's `.war` file or Liferay's source code. In either place, you'll find the files in the `portal-impl.jar` file, which is in Liferay's `WEB-INF/lib` folder. The files you want are `hibernate-clustered.xml`, `liferay-single-vm.xml`, and `liferay-multi-vm-clustered.xml`, and they'll be in the `/ehcache` folder in this `.jar`. Once you have these, make a subfolder of the `docroot` folder in your project. Place the files you extracted into this folder, and then specify this folder in the properties above. 

For example, if you created a folder called `custom_cache` in your project's `docroot` folder, you'd copy the three XML configuration files (`hibernate-clustered.xml`, `liferay-single-vm.xml`, and `liferay-multi-vm-clustered.xml`) there. Then you'd edit your `portlet.properties` and specify your configuration files in the three properties above: 

	net.sf.ehcache.configurationResourceName=/custom_cache/hibernate-clustered.xml
	ehcache.single.vm.config.location=/custom_cache/liferay-single-vm.xml
	ehcache.multi.vm.config.location=/custom_cache/liferay-multi-vm-clustered.xml
	
Save the file and deploy the plugin (deploying plugins is covered in the *Liferay Developer's Guide*), and the settings you've placed in those files will override the default Liferay settings. In this way, you can tweak your cache settings so that your cache performs optimally for the type of traffic generated by your site. The strength of doing it this way is that you don't have restart your server to change the cache settings. This is a great benefit, but beware: since Ehcache doesn't allow for changes to cache settings while the cache is alive, reconfiguring a cache while the server is running will flush the cache. 

There is, of course, another way to do this if you don't want to create a plugin. It requires you to restart the server to enable the new cache settings, but you don't have to work with any developer tools to do it. 

#### Modifying the Ehcache settings directly

This method is pretty similar to the plugin method, except that you have to modify the Liferay installation directly. You'll still need to extract Liferay's configuration files as described in the previous section. Next, shut down your server and find the location in the server where Liferay is installed (this may not be possible on all application servers, and if this is the case, you'll need to use the plugin method described above). For example, say you're running Liferay on Tomcat. Tomcat stores the deployed version of Liferay in `[Tomcat Home]/webapps/ROOT`. Inside this folder is the folder structure `WEB-INF/classes`. You can create a new folder in here called `custom_cache` to store the custom versions of the cache configuration files. Copy the files you extracted from Liferay into this folder.  

You then need to modify the properties in `portal-ext.properties` that point to these files. Copy/paste the **Hibernate** section of `portal.properties` into your `portal-ext.properties` file and then modify the `net.sf.ehcache.configurationResourceName` property to point to the clustered version of the configuration file that is now in your custom folder:

    net.sf.ehcache.configurationResourceName=/custom_cache/hibernate-clustered.xml

Now that Liferay is pointing to your custom file, you can modify the settings in this file to change the cache configuration for Hibernate.

Next, copy/paste the *Ehcache* section from the `portal.properties` file into your `portal-ext.properties` file. Modify the properties so that they point to the files that are in your custom folder. For example:

    ehcache.multi.vm.config.location=/custom_cache/liferay-multi-vm-clustered.xml

You can now take a look at the settings in these files and tune them to fit your environment and application. Let's examine how to do that next. 

#### Customizing Hibernate cache settings

By default, Hibernate (Liferay's database persistence layer) is configured to use Ehcache as its cache provider. This is the recommended setting. If you're using the default settings using Cluster Link, you already have this enabled. If, however, you need to customize the settings, you'll have to customize it in one of the ways described above: either in a plugin or in the deployed instance of Liferay. The first thing, of course, is to start off with the clustered version of the file. Copy the `hibernate-clustered.xml` configuration file to your plugin or to a place in Liferay's classpath (as described above) where you can refer to it. Then change the following property to point to the file: 

    net.sf.ehcache.configurationResourceName=/path/to/hibernate-clustered.xml

Next, open this file in a text editor. You'll notice that the configuration is already set up to perform distributed caching through a multi-cast connection. The configuration, however, might not be set up optimally for your particular application. Notice that by default, the only object cached in the Hibernate cache is the User object (`com.liferay.``portal``.model.impl.UserImpl`). This means that when a user logs in, his or her User object will go in the cache so that any portal operation that requires access to it (such as permission checking) can retrieve that object very quickly from the cache.

You may wish to add other objects to the cache. For example, a large part of your application may be document management using the Documents and Media portlet. In this case, you may want to cache media objects, such as `DLFileEntryImpl` in order to improve performance as users access documents. To do that, add another block to the configuration file with the class you want to cache:

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

Your site may use the message boards portlet, and those message boards may get a lot of traffic. To cache the threads on the message boards, configure a block with the `MBMessageImpl` class:

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

Note that if your developers have overridden any of these classes in an Ext plugin, you'll have to specify the overridden versions rather than the stock ones that come with Liferay Portal. You can customize the other ehcache configuration files in exactly the same way. Refer to Ehcache's documentation for information on how to do this. 

As you can see, it's easy to add specific data to be cached. Be careful, however, as too much caching can actually reduce performance if the JVM runs out of memory and starts garbage collecting too frequently. You'll likely need to experiment with the memory settings on your JVM as well as the cache settings above. You can find the specifics about these settings in the documentation for Ehcache.

## Performance Tuning

Once you have your portal up and running, you may find a need to tune it for performance, especially if your site winds up generating more traffic than you'd anticipated. There are some definite steps you can take with regard to improving Liferay's performance.

### Memory

Memory is one of the first things to look at when you want to optimize performance. If you have any disk swapping, you want to avoid it at all costs: it has a serious impact on performance. Make sure that your server has an optimal amount of memory and that your JVM is tuned to use it.

There are three basic JVM command switches that control the amount of memory in the Java heap.

    -Xms
    -Xmx
    -XX:MaxPermSize

These three settings control the amount of memory available to the JVM initially, the maximum amount of memory into which the JVM can grow, and the separate area of the heap called Permanent Generation space.

The first two settings should be set to the same value. This prevents the JVM from having to reallocate memory if the application needs more. Setting them to the same value causes the JVM to be created up front with the maximum amount of memory you want to give it.

    -Xms1024m -Xmx1024m -XX:MaxPermSize=256m

This is perfectly reasonable for a moderately sized machine or a developer machine. These settings give the JVM 1024MB for its regular heap size and have a PermGen space of 256MB. If you're having performance problems, and your profiler is showing that there's a lot of garbage collection going on, the first thing you might want to look at is increasing the memory available to the JVM. You'll be able to tell if memory is a problem by running a profiler (such as Jprobe, YourKit, or the NetBeans profiler) on the server. If you see Garbage Collection (GC) running frequently, you definitely want to increase the amount of memory available to the JVM.

Note that there is a law of diminishing returns on memory, especially with 64 bit systems. These systems allow you to create very large JVMs, but the larger the JVM, the more time it takes for garbage collection to take place. For this reason, you probably won't want to create JVMs of more than 2 GB in size. To take advantage of higher amounts of memory on a single system, run multiple JVMs of Liferay instead.

Issues with PermGen space can also affect performance. PermGen space contains long-lived classes, anonymous classes and interned Strings (immutable String objects that are kept around for a long time to increase String processing performance). Hibernate--which Liferay uses extensively--has been known to make use of PermGen space. If you increase the amount of memory available to the JVM, you may want to increase the amount of PermGen space accordingly.

### Garbage Collection

As the system runs, various Java objects are created. Some of these objects are long-lived, and some are not. The ones that are not become *de-referenced*, which means that the JVM no longer has a link to them because they have ceased to be useful. These may be variables that were used for methods which have already returned their values, objects retrieved from the database for a user that is no longer logged on, or a host of other things. These objects sit in memory and fill up the heap space until the JVM decides it's time to clean them up.

Normally, when garbage collection (GC) runs, it stops all processing in the JVM while it goes through the heap looking for dead objects. Once it finds them, it frees the memory they were taking up, and then processing can continue. If this happens in a server environment, it can slow down the processing of requests, as all processing comes to a halt while GC is happening.

There are some JVM switches that you can enable which can reduce the amount of time processing is halted while garbage collecting happens. These can improve the performance of your Liferay installation if applied properly. As always, you will need to use a profiler to monitor garbage collection during a load test to tune the numbers properly for your server hardware, operating system, and application server.

![Figure 15.5: Java Memory](../../images/portal-admin-ch7_html_518957a7.gif)

The Java heap is divided into sections for the young generation, the old generation, and the permanent generation. The young generation is further divided into three sections: Eden, which is where new objects are created, and two “survivor spaces,” which we can call the *From* and *To* spaces. Garbage collection occurs in stages. Generally, it's more frequently done in the young generation, less frequently done in the old generation, and even less frequently done in the permanent generation, where long-lived objects reside. When garbage collection runs in the young generation, Eden is swept for objects which are no longer referenced. Those that are still around are moved to the “To” survivor space, and the “From” space is then swept. Any other objects in that space which still have references to them are moved to the “To” space, and the “From” space is then cleared out altogether. After this, the “From” and the “To” spaces swap roles, and processing is freed up again until the next time the JVM determines that garbage collection needs to run.

After a predetermined number of “generations” of garbage collection, surviving objects may be moved to the old generation. Similarly, after a predetermined number of “generations” of garbage collection in the old generation, surviving objects may be moved to the permanent generation.

By default, the JDK uses a serial garbage collector to achieve this. This works very well for a short-lived desktop Java application, but is not necessarily the best performer for a long-lived, server-based application like Liferay. For this reason, you may wish to switch to the Concurrent Mark-Sweep (CMS) collector.

In CMS garbage collection, rather than halting application processing altogether, this garbage collector makes one short pause in application execution to mark objects directly reachable from the application code. Then it allows the application to run while it marks all objects which are reachable from the set it marked. Finally, it adds another phase called the *remark* phase which finalizes marking by revisiting any objects modified while the application was running. It then sweeps through and garbage collects. Though it sounds more complicated, this has the effect of greatly reducing the amount of time that execution needs to be halted in order to clean out dead objects.

Just about every aspect of the way memory management works in Java can be tuned. In your profiling, you may want to experiment with some of the following settings to see if any of them can increase your performance.

**NewSize, MaxNewSize:** The initial size and the maximum size of the New or Young Generation.

**+UseParNewGC:** Causes garbage collection to happen in parallel, using multiple CPUs. This decreases garbage collection overhead and increases application throughput.

**+UseConcMarkSweepGC:** Use the Concurrent Mark-Sweep Garbage Collector. This uses shorter garbage collection pauses, and is good for applications that have a relatively large set of long-lived data, and that run on machines with two or more processors, such as web servers.

**+CMSParallelRemarkEnabled:** For the CMS GC, enables the garbage collector to use multiple threads during the CMS remark phase. This decreases the pauses during this phase.

**SurvivorRatio:** Controls the size of the two survivor spaces. It's a ratio between the survivor space size and Eden. The default is 25. There's not much bang for the buck here, but it may need to be adjusted.

**ParallelGCThreads:** The number of threads to use for parallel garbage collection. Should be equal to the number of CPU cores in your server.

A sample configuration using the above parameters might look something like this:

    JAVA_OPTS="$JAVA_OPTS -XX:NewSize=700m -XX:MaxNewSize=700m -Xms2048m
    -Xmx2048m -XX:MaxPermSize=128m -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
    -XX:+CMSParallelRemarkEnabled -XX:SurvivorRatio=20
    -XX:ParallelGCThreads=8"

Again, you should always follow the procedure of adjusting the settings, then testing under load, then adjusting again. Every system is different, and these are general guidelines to follow. Next, we'll see some modifications we can make to Liferay's properties to help increase performance. 
    
### Properties File Changes

There are also some changes you can make to your `portal-ext.properties` file once you are in a production environment.

Set the following to false to disable checking the last modified date on server side CSS and JavaScript.

    last.modified.check=false

Set this property to true to load the theme's merged CSS files for faster loading for production. By default it is set to false for easier debugging for development. You can also disable fast loading by setting the URL parameter `css_fast_load` to `0`.

    theme.css.fast.load=true

Set this property to true to load the combined JavaScript files from the property `javascript.files` into one compacted file for faster loading for production. 

    javascript.fast.load=true
    
These are various things the Liferay engineering team has done to increase performance generally. If your developers make use of Liferay's tools and platform, their JavaScript and themes can also take advantage of these properties. 

Let's look at one final, general way of increasing Liferay's performance: disabling unused servlet filters. 

### Disabling unused servlet filters

Liferay comes by default with a number of servlet filters enabled and running. It is likely that for your installation, you don't need them all. Since servlet filters intercept the HTTP request and do some processing on it before Liferay even has a chance to start building the page, you can increase performance by disabling the ones you're not using. 

You can disable servlet filters you're not using by using your `portal-ext.properties` file. Copy the *Servlet Filters* section from the original `portal.properties` file into your customized file, and then go through the list and see if there are any you can disable, by setting them to `false`. 

For example, if you are not using CAS for single sign-on, disable the CAS Filter. If you are not using NTLM for single sign-ons, disable the NTLM Filter. The fewer servlet filters you are running, the less processing power is needed for each request.

As you can see, there are many things you can do to increase Liferay's performance generally. But don't forget to load test your own applications! It may be that a performance issue comes from a custom-built application that's doing something it shouldn't do. Always load test your system before putting it into production: that's the best way of finding out potential performance problems, and that way, you'll find them during performance testing, and not when your system is in production.

## Plugin Management

One of the primary ways of extending the functionality of Liferay Portal is by the use of plugins. *Plugin* is an umbrella term for installable portlet, theme, layout template, hook, Ext, and web module Java EE `.war` files. Though Liferay comes bundled with a number of functional portlets, themes, layout templates, hooks, and web modules, plugins provide a means of extending Liferay to be able to do almost anything.

### Portlets

Portlets are small web applications that run in a portion of a web page. The heart of any portal implementation is its portlets, because all of the functionality of a portal resides in its portlets. Liferay's core is a portlet container. The container's job is to manage the portal's pages and to aggregate the set of portlets that are to appear on any particular page. This means that the core doesn't contain application code. Instead, all of the features and functionality of your portal application must reside in its portlets.

---

![tip](../../images/tip.png) **Tip:** Liferay 4.4.2 and below support the Portlet 1.0 standard: JSR-168. Liferay 5.0 and above support the Portlet 2.0 standard: JSR-286. You cannot run Portlet 2.0 portlets in Liferay 4.4.2, but because the Portlet 2.0 standard is backwards-compatible, portlets written to the 1.0 standard will run in Liferay 5.x and above.

---

Portlet applications, like servlet applications, have become a Java standard which various portal server vendors have implemented. The JSR-168 standard defines the portlet 1.0 specification, and the JSR-286 standard defines the portlet 2.0 specification. A Java standard portlet should be deployable on any portlet container which supports the standard. Portlets are placed on the page in a certain order by the end user and are served up dynamically by the portal server. This means that certain “givens” that apply to servlet-based projects, such as control over URLs or access to the `HttpServletRequest` object, don’t apply in portlet projects, because the portal server generates these objects dynamically. 

Portal applications come generally in two flavors: 1) portlets can be written to provide small amounts of functionality and then aggregated by the portal server into a larger application, or 2) whole applications can be written to reside in only one or a few portlet windows. The choice is up to those designing the application. The developer only has to worry about what happens inside of the portlet itself; the portal server handles building out the page as it is presented to the user.

Most developers nowadays like to use certain frameworks to develop their applications, because those frameworks provide both functionality and structure to a project. For example, Struts enforces the Model-View-Controller design pattern and provides lots of functionality, such as custom tags and form validation, that make it easier for a developer to implement certain standard features. With Liferay, developers are free to use all of the leading frameworks in the Java EE space, including Struts, Spring MVC, and Java Server Faces. This allows developers familiar with those frameworks to more easily implement portlets, and also facilitates the quick porting of an application using those frameworks over to a portlet implementation.

Additionally, Liferay allows for the consuming of PHP and Ruby applications as “portlets,” so you do not need to be a Java developer in order to take advantage of Liferay's built-in features (such as user management, sites, organizations, page building and content management). You can also use scripting languages such as Groovy if you wish. You can use the Plugins SDK to deploy your PHP or Ruby application as a portlet, and it will run seamlessly inside of Liferay. We have plenty of examples of this; to see them, check out the Plugins SDK from Liferay's public code repository.

Does your organization make use of any Enterprise Planning (ERP) software that exposes its data via web services? You could write a portlet plugin for Liferay that can consume that data and display it as part of a dashboard page for your users. Do you subscribe to a stock service? You could pull stock quotes from that service and display them on your page, instead of using Liferay's built-in Stocks portlet. Do you have a need to combine the functionality of two or more servlet-based applications on one page? You could make them into portlet plugins and have Liferay display them in whatever layout you want. Do you have existing Struts, Spring MVC, or JSF applications that you want to integrate with your portal? It is a straightforward task to migrate these applications into Liferay, and then they can take advantage of the layout, security, and administration infrastructure that Liferay provides.

### Themes

![Figure 9.14: Envision Theme from Liferay's Theme Repository](../../images/marketplace-envision-theme.png)

Themes are hot deployable plugins which can completely transform the look and feel of the portal. Most organizations have their own look and feel standards which go across all of the web sites and web applications in the infrastructure. Liferay makes it possible for a site designer to create a theme plugin which can be installed, allowing for the complete transformation of the portal to whatever look and feel is needed. There are lots of available theme plugins on Liferay's web site, and more are being added every day. This makes it easier for those who wish to develop themes for Liferay, as you can now choose a theme which most closely resembles what you want to do and then customize it. This is much easier than starting a theme from scratch. You can learn more about theme development in [*Liferay in Action*](http://manning.com/sezov).

![Figure 9.15: Murali Theme from Liferay's Theme Repository](../../images/marketplace-murali-theme.png)

### Layout Templates

Layout Templates are ways of choosing how your portlets will be arranged on a page. They make up the body of your page, the large area into which you can drag and drop portlets. Liferay Portal comes with several built-in layout templates, but if you have a complex page layout (especially for your home page), you may wish to create a custom layout template of your own. This is covered in [*Liferay in Action*](http://manning.com/sezov).

### Hook Plugins

Hook plugins were introduced with Liferay 5.2. As the name implies, they allow “hooking” into Liferay's core functionality. This means that they enable developers to override or replace functionality that is in the core of the system. You can hook into the eventing system, model listeners, and portal properties. You can also override Liferay's core JSPs with your own. Hooks are very powerful and have been designed to replace most of the reasons for using the extension environment with something that is easier to use and hot deployable.

### Web Plugins

Web plugins are regular Java EE web modules that are designed to work with Liferay. Liferay supports integration with various Enterprise Service Bus (ESB) implementations, as well as Single Sign-On implementations, workflow engines, and so on. These are implemented as web modules that are used by Liferay portlets to provide functionality.

### Installing Plugins from Repositories

Liferay Portal has a section of the control panel called Plugins Installation, which you can finder beneath the Server heading. This section not only allows you to see what plugins are installed in your portal, but also it enables you to run the search indexer on those portlets that support it and install new portlets.

Use the dockbar's *Go to* menu to select *Control Panel*. Under the Server heading, select *Plugins Installation*. You should now see the page which allows you to configure and install portlets.

The default view of the Plugins Installation page shows which plugins are already installed on the system and whether whether or not they are active. The Portlet Plugins tab allows you reindex certain portlets to improve their searchability. The Theme and Layout Template Plugins tabs display which portal roles can access them.

![Figure 9.16: Plugins Installation Portlet Tab Default View](../../images/marketplace-plugins-installation-portlet-tab.png)

![Figure 9.17: Plugins Installation Theme Tab Default View](../../images/marketplace-plugins-installation-theme-tab.png)

If you would like to see what plugins are available, you can do so by clicking the *Install More [Plugin Type]* button, where [Plugin Type] changes based on which tab you are viewing. Please note that the machine upon which Liferay is running must have access to the Internet in order to be able to read the Official and Community repositories. If the machine does not have Internet access, you will need to download the plugins from the site and install them manually. We will discuss how to do this later in this chapter.

It's easy to navigate from the initial page of the Plugin Installer to different pages since the plugins are listed alphabetically. You can also change the number of items per page and navigate to a specific page if you know where a particular plugin appears in the list. This is a standard feature of Liferay, and you will see it in most of Liferay's portlets.

![Figure 9.18: Installing Plugins](../../images/marketplace-plugins-installer.png)

After you click the *Install More [Plugin Type]* button, a new view appears. This view has multiple tabs, and by default, displays the *Portlet Plugins* tab. Note that the list displayed is a list of all of the plugins that are available across all of the repositories to which the server is subscribed. Above this is a search mechanism which allows you to search for plugins by their name, by whether or not they are installed, by tag, or by which repository they belong to. To install a plugin, choose the plugin by clicking on its name. For example, if you want to use online web forms on your web site, you might want to install the Web Form portlet. This portlet provides a handy interface which allows you to create forms for users to fill out. You can specify an address to which the results will be emailed.

Find the Web Form Portlet in the list by searching for it or browsing to it. Once you have found it, click on its name. Another page will be displayed which describes the portlet plugin in more detail. Below the description is an *Install* button. Click this button to install your plugin.

![Figure 9.19: Installing the Web Form Portlet](../../images/marketplace-installing-web-form-portlet.png)

Once you click *Install*, your chosen plugin will automatically download and be installed on your instance of Liferay. If you have the Liferay console open, you can view the deployment as it happens. When it is finished, you should be able to go back to the Add Application window and add your new plugin to a page in your portal.

The same procedure is used for installing new Liferay themes, layout templates, hooks, and web modules. Instead of the *Portlet Plugins* tab, you would use the appropriate tab for the type of plugin you wish to install to view the list of plugins of that type. For themes, convenient thumbnails (plus a larger version when you click on the details of a particular theme) are shown in the list.

After clicking on the *Install* button for a theme, the theme becomes available on the *Look and Feel* tab of any page.

### Installing Plugins Manually

Installing plugins manually is almost as easy as installing plugins via the Plugin Installer. There are several scenarios in which you would need to install plugins manually rather than from Liferay's repositories:

-   Your server is firewalled without access to the Internet. This makes it impossible for your instance of Liferay to connect to the plugin repositories.

-   You are installing portlets which you have either purchased from a vendor, downloaded separately, or developed yourself.

-   For security reasons, you do not want to allow portal administrators to install plugins from the Internet before they are evaluated.

You can still use the control panel to install plugins that are not available from the online repositories. This is by far the easiest way to install plugins.

If your server is firewalled, you will not see any plugins displayed in the Portlet Plugins or Theme Plugins tabs. Instead, you will need to click the *Upload File* tab. This gives you a simple interface for uploading a `.war` file containing a plugin to your Liferay Portal.

![Figure 9.20: Installing a Plugin Manually](../../images/marketplace-plugin-installer-upload-file.png)

Click the *Browse* button and navigate your file system to find the portlet or theme `.war` you have downloaded. The other field on the page is optional: you can specify your own context for deployment. If you leave this field blank, the default context defined in the plugin (or the `.war` file name itself) will be used.

That's all the information the Plugin Installer needs in order to deploy your portlet, theme, layout template, hook, or web module. Click the *Install* button, and your plugin will be uploaded to the server and deployed. If it is a portlet, you should see it in the *Add Content* window. If it is a theme, it will be available on the *Look and Feel* tab in the page definition.

If you do not wish to use the Update Manager or Plugin Installer to deploy plugins, you can also deploy them at the operating system level. The first time Liferay starts, it creates a *hot deploy* folder which is, by default, created inside the Liferay Home folder. This folder generally resides one directory up from where your application server is installed, though it may be elsewhere depending on which application server you are running. To find out where the Liferay Home folder is for your application server, please see the section on your server in chapter 1. The first time Liferay is launched, it will create a folder structure in Liferay Home to house various configuration and administrative data. One of the folders it creates is called *deploy*. If you copy a portlet or theme plugin into this folder, Liferay will deploy it and make it available for use just as though you'd installed it via the Plugin Installer in the control panel. In fact, this is what the Plugin Installer is doing behind the scenes.

You can change the defaults for this directory structure so that it is stored anywhere you like by modifying the appropriate properties in your `portal-ext.properties` file. Please see the above section on the `portal-ext.properties` file for more information.

To have Liferay hot deploy a portlet or theme plugin, copy the plugin into your hot deploy folder, which by default is in `[Liferay Home]/deploy`. If you are watching the Liferay console, you should see messages like the following:

	16:11:47,616 INFO [PortletAutoDeployListener:71] Copying portlets for
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/deploy/weather-portlet-6.0.4.1.war
	
	Expanding:
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/deploy/weather-portlet-6.0.4.1.war
	into
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/tomcat-6.0.26/temp/20100729161147694
	
	Copying 1 file to
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/tomcat-6.0.26/temp/20100729161147694/WEB-INF
	
	Copying 1 file to
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/tomcat-6.0.26/temp/20100729161147694/WEB-INF/classes
	
	Copying 1 file to
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/tomcat-6.0.26/temp/20100729161147694/WEB-INF/classes
	
	Copying 1 file to
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/tomcat-6.0.26/temp/20100729161147694/META-INF
	
	Copying 37 files to
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/tomcat-6.0.26/webapps/weather-portlet
	
	Copying 1 file to
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/tomcat-6.0.26/webapps/weather-portlet
	
	Deleting directory
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/tomcat-6.0.26/temp/20100729161147694
	
	16:11:48,072 INFO [PortletAutoDeployListener:81] Portlets for
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/deploy/weather-portlet-6.0.4.1.war
	copied successfully. Deployment will start in a few seconds.
	
	Jul 29, 2010 4:11:50 PM org.apache.catalina.startup.HostConfig
	deployDirectory
	
	INFO: Deploying web application directory weather-portlet
	
	16:11:50,585 INFO [PortletHotDeployListener:222] Registering portlets
	for weather-portlet
	
	16:11:50,784 INFO [PortletHotDeployListener:371] 1 portlet for
	weather-portlet is available for use

The *available for use* message means that your plugin was installed correctly and is available for use in the portal.

### Plugin Troubleshooting

Sometimes plugins fail to install. There can be different reasons for installation failure based on several factors, including

-   Liferay configuration

-   The container upon which Liferay is running

-   Changing the configuration options in multiple places

-   How Liferay is being launched

You can often tell whether or not you have a plugin deployment problem by looking at the Liferay server console. If the hot deploy listener recognizes the plugin, you'll see a *plugin copied successfully* message. If this message is not followed up by an *available for use* message then you have an issue with your plugin deployment configuration, probably due to one of the factors listed above.

Let's take a look at each of these factors.

#### Liferay Configuration Issues

---

![tip](../../images/tip.png) **Tip:** This applies to Liferay versions prior to version 4.3.5. Liferay versions above 4.3.5 are able to auto detect the type of server it is running on, which makes things a lot easier. If you are running a newer version of Liferay, you can skip this section. If you are upgrading from one of these versions, continue reading.

---

Liferay by default comes as a bundle or as a `.war` file. Though every effort has been made to make the `.war` file as generic as possible, sometimes the default settings are inappropriate for the container upon which Liferay is running. Most of these problems were resolved in Liferay 4.3.5 with the addition of code that allows Liferay to determine which application server it is running on and adjust the way it deploys plugins as a result. If you have upgraded from one of these older versions, you may still have settings in your `portal.ext.properties` file that are no longer needed. One of these settings is the manual override of the default value of `auto.deploy.dest.dir`.

In versions of Liferay prior to 4.3.5, there is a property called `auto.deploy.dest.dir` that defines the folder where plugins are deployed after the hot deploy utilities have finished preparing them. This folder maps to a folder that the container defines as an auto-deploy or a hot deploy folder. By default in older versions of Liferay, this property is set to `../webapps`. This default value works for Tomcat containers (if Tomcat has been launched from its `bin` folder), but will not work for other containers that define their hot deploy folders in a different place. In newer versions of Liferay, this value is automatically set to the default for the application server upon which Liferay is running.

For example, Glassfish defines the hot deploy folder as a folder called `autodeploy` inside of the domain folder in which your server is running. By default, this is in `<Glassfish Home>/domains/domain1/autodeploy`. JBoss defines the hot deploy folder as a root folder inside of the particular server configuration you are using. By default, this is in `<JBoss Home>/server/default/deploy`. WebLogic defines this folder inside of the domain directory. By default, this is in `<Bea Home>/user_projects/domains/<domain name>/autodeploy`.

The best thing to do when upgrading to newer versions of Liferay Portal is to remove this property altogether. It is not needed, as the autodetection of the container handles the hot deploy location. If, for whatever reason, you need to customize the location of the hot deploy folder, follow the instructions below.

You will first need to determine where the hot deploy folder is for the container you are running. Consult your product documentation for this. Once you have this value, there are two places in which you can set it: in the `portal-ext.properties` file and in the Plugin Installer portlet.

To change this setting in the `portal-ext.properties` file, browse to where Liferay was deployed in your application server. Inside of this folder should be a `WEB-INF/classes` folder. Here you will find the `portal-ext.properties` file. Open this file in a text editor and look for the property `auto.deploy.dest.dir`. If it does not appear in the file, you can add it. The safest way to set this property, as we will see later, is to define the property using an absolute path from the root of your file system to your application server's hot deploy folder. For example, if you are using Glassfish, and you have the server installed in `/java/glassfish`, your `auto.deploy.dest.dir` property would look like the following:

	auto.deploy.dest.dir=/java/glassfish/domains/domain1/autodeploy

Remember, if you are on a Windows system, use forward slashes instead of back slashes, like so:

	auto.deploy.dest.dir=C:/java/glassfish/domains/domain1/autodeploy

Save the file and then restart your container. Now plugins should install correctly.

Instead of changing the hot deploy destination directory in your `portal-ext.properties` file, you can do it via the Plugin Installer. To change change the setting this way, navigate to the Plugins Installation page of the control panel, click the *Install More [Plugins]* button. This will bring you to the Plugin Installer page. Next, click on the *Configuration* tab of the Plugin Installer page. There are a number of settings that you can change on this tab, including the default folders for hot deploy, where Liferay should look for plugin repositories, and so on.

![Figure 9.21: Changing the Hot Deploy Destination Directory](../../images/marketplace-plugin-installer-configuration.png)

The setting to change is the field labeled *Destination Directory*. Change this to the full path to your container's auto deploy folder from the root of your file system. When you are finished, click the *Save* button at the bottom of the form. The setting will now take effect without your having to restart your container. Note that the setting in the control panel overrides the setting in the properties file.

If you are having hot deploy trouble in Liferay versions 4.3.5 and greater, it is possible that the administrator of your application server has changed the default folder for auto deploy in your application server. In this case, you would want to set `auto.deploy.dest.dir` to the customized folder location as you would with older versions of Liferay. In Liferay 4.3.5 and greater, this setting still exists, but is blank. Add the property to your `portal-ext.properties` file and set its value to the fully qualified path to the auto deploy folder configured in your application server.

#### Deploy Issues for Specific Containers

Some containers, such as WebSphere®, don't have a hot deploy feature. Unfortunately, these containers do not work with Liferay's hot deploy system. But this does not mean that you cannot install plugins on these containers. You can deploy plugins manually using the application server's deployment tools. Liferay is able to pick up the portlet plugins once they get deployed to the container manually, especially if you add it to the same Enterprise Application project that was created for Liferay.

When Liferay hot deploys portlet and theme `.war` files, it sometimes makes modifications to those files right before deployment. In order to successfully deploy plugins using an application server vendor's tools, you will want to run your plugins through this process before you attempt to deploy them.

Navigate back to the *Configuration* tab of the Plugin Installer. Enter the location that you would like plugin `.war` files to be copied to after they are processed by Liferay's plugin installer process into the *Destination Directory* field. You will use this as a staging directory for your plugins before you install them manually with your server's deployment tools. When you are finished, click *Save*.

Now you can deploy plugins using the Plugin Installer portlet or by dropping `.war` files into your auto deploy directory. Liferay will pick up the files, modify them, and then copy the result into the destination directory you have configured. You may then deploy them from here to your application server.

##### Example: WebSphere® Application Server

1.  If you don't have one already, create a `portal-ext.properties` file in the Liferay Home folder of your Liferay installation. Add the following directive to it:

	auto.deploy.dest.dir=${liferay.home}/websphere-deploy

2.  Create a folder called `websphere-deploy` inside your `$LIFERAY_HOME` folder. This is the folder where the Lucene index, Jackrabbit config, and deploy folders are.

3.  Make sure the `web.xml` file inside the plugin you want to install has the following context parameter in it:

	<context-param\>
	
	<param-name\>com.ibm.websphere.portletcontainer.PortletDeploymentEnabled</param-name\>
	
	<param-value\>false</param-value\>
	
	</context-param\>

Liferay versions 5.2.2 and higher will automatically inject this into the `web.xml` file on WebSphere containers.

4.  The WebSphere deploy occurs in two steps. You will first use Liferay's tools to “pre-deploy” the file, and then use WebSphere's tools to do the actual deployment. This is because Liferay makes deployment-time modifications to the plugins right before they are actually deployed to the application server. For other application servers, this can usually be done in one step, because Liferay can make the modifications and then copy the resulting `.war` file into an autodeploy folder to have it actually deployed. Because WebSphere does not have an autodeploy feature, we need to separate these two steps.

5.  Deploy your .war file using Liferay's Plugin Installer or by copying it into `$LIFERAY_HOME/deploy`. Liferay will make its modifications and because we changed the `auto.deploy.dest.dir` in the first step, it will copy the resulting `.war` file into `$LIFERAY_HOME/websphere-deploy`. You will see a *copied successfully* message in the log.

6.  Use WebSphere's tools to deploy the `.war` file. Make the context root for the `.war` file equal to the file name (i.e., `/my-first-portlet`). Once the `.war` file is deployed, save it to the master configuration.

7.  Go back to the *Applications &rarr; Enterprise Applications* screen in the WebSphere Admin Console. You will see that your portlet is deployed, but not yet started. Start it.

8.  Liferay will immediately recognize that the portlet has been deployed and register it. The portlet will be automatically started and registered upon subsequent restarts of WebSphere.

Experienced WebSphere system administrators can further automate this by writing a script which watches the `websphere-deploy` directory and uses `wsadmin` commands to then deploy plugins automatically.

#### Changing the Configuration Options in Multiple Places

Sometimes, especially during development when several people have administrative access to the server at the same time, the auto deploy folder location may inadvertently be customized in both the `portal-ext.properties` file and in the control panel. If this happens, the value in the control panel takes precedence over the value in the properties file. If you go into the control panel and change the value to the correct setting, plugin deployment will start working again.

### Creating Your Own Plugin Repository

As your enterprise builds its own library of portlets for internal use, you can create your own plugin repository to make it easy to install and upgrade portlets. This will allow different departments who may be running different instances of Liferay to share portlets and install them as needed. If you are a software development house, you may wish to create a plugin repository for your own products. Liferay makes it easy for you to create your own plugin repository and make it available to others.

You can create your plugin repository in two ways:

1.  Use the Software Catalog in the control panel to create the repository by using its graphical interface and an HTTP server.

2.  Create an XML file using the Liferay Plugin Repository DTD (`http://www.liferay.com/dtd/liferay-plugin-repository_6_0_0.dtd`) and an HTTP server.

Both methods have their benefits. The first method allows users to upload their plugins to an HTTP server to which they have access. They can then register their plugins with the repository by adding a link to it via the control panel's graphical user interface. Liferay will then generate the XML necessary to connect the repository to a control panel running on another instance of Liferay. This XML file can then be placed on an HTTP server, and the URL to it can be added to the Plugin Installer, making the portlets in this repository available to the server running Liferay.

The second method does not require an instance of Liferay to be running. You can upload plugins to an HTTP server of your choice, and then create an XML file called `liferay-plugin-repository.xml` manually. If you make this file available on an HTTP server (it can be the same one which is storing the plugins or a different one), you can connect the repository to a Plugin Installer in the control panel running on an instance of Liferay.

We will first look at creating a plugin repository using the Software Catalog in the control panel.

#### The Software Catalog

You will want to use the Software Catalog if you will have multiple users submitting portlets into the repository, and if you don't want to worry about creating the `liferay-plugin-repository.xml` file yourself.

![Figure 9.22: The Software Catalog with Nothing Installed](../../images/marketplace-software-catalog.png)

Each site in your portal can have an instance of the Software Catalog. The control panel presents you with the software catalog for whichever site you are working on. This means that different sites can have different software repositories, so you can host several software repositories on the same instance of Liferay if you wish—they just have to be in different sites. Choose the site that will host the plugin repository and go to the control panel. You will see at the top of the screen a message that says “Content for [Site],” where [Site] is the site you were on when you selected the control panel from the dockbar. If you want to administer the software catalog for a different site, you can select it from the selection box.

![Figure 9.23: Populated Software Catalog from liferay.com](../../images/marketplace-populated-software-catalog.png)

The Software Catalog has several tabs. The first tab is labeled Products. The default view of the portlet, when populated with software, displays what plugins are available for install or download. This can be seen in the version on Liferay's home page.

We will use an example site in order to better illustrate how to use the Software Catalog portlet. Assume you, as the portal administrator, have created a site called *Old Computers*. This site will be for users to collaborate on setting up and using old computers with obsolete hardware and operating systems. Users who participate in the site will eventually get upgraded to a more privileged status and get their own blog page. To implement this, you have created a My Summary portlet which displays the user's name, picture, and description from his or her user profile. Because this portlet is generic enough that it could be useful to anyone using Liferay, you have decided to make it available in your own software catalog.

The first step in adding a plugin to your software repository is to add a license for your product. A license communicates to users the terms upon which you are allowing them to download and use your software. Click the *Licenses* tab and then click the *Add License* button that appears. You will then see a form which allows you to enter the title of your license, a URL pointing to the actual license document, and check boxes denoting whether the license is open source, active, or recommended.

When you have finished filling out the form, click the *Save* button. Your license will be saved. Once you have at least one license in the system, you can begin adding software products to your software catalog. Click the *Products* tab, and then click the *Add Product* button.

Your next step will be to create the product record in the software catalog. This will register the product in the software catalog and allow you to start adding versions of your software for users to download and/or install directly from their instances of Liferay. You will first need to put the `.war` file containing your software on a web server that is accessible without authentication to the users who will be installing your software. In the example above, the *Old Computers* site is on the Internet, so you would place the file on a web server that is accessible to anyone on the Internet. If you are creating a software catalog for an internal Intranet, you would place the file on a web server that is available to anyone inside of your organization's firewall.

To create the product record in the Software Catalog portlet, click the *Products* tab, and then click the *Add Product* button. Fill out the form with information about your product.

![Figure 9.24: Adding a Product to the Software Catalog](../../images/marketplace-adding-product-to-software-catalog.png)

**Name:** The name of your software product.

**Type:** Select whether this is a portlet, theme, layout template, hook, or web plugin.

**Licenses:** Select the license(s) under which you are releasing this software.

**Author:** Enter the name of the author of the software.

**Page URL:** If the software has a home page, enter its url here.

**Tags:** Enter any tags you would like added to this software.

**Short Description:** Enter a short description. This will be displayed in the summary table of your software catalog.

**Long Description:** Enter a longer description. This will be displayed on the details page for this software product.

**Permissions:** Click the *Configure* link to set permissions for this software product.

**Group ID:** Enter a group ID. A group ID is a name space which usually identifies the company or organization that made the software. For our example, we will use *old-computers*.

**Artifact ID:** Enter an Artifact ID. The artifact ID is a unique name within the name space for your product. For our example, we will use *my-summary-portlet*.

**Screenshot:** Click the *Add Screenshot* button to add a screen shot of your product for users to view.

When you have finished filling out the form, click the *Save* button. You will be brought back to the product summary page, and you will see that your product has been added to the repository.

Notice that in the version column, *N/A* is being displayed. This is because there are not yet any released *versions* of your product. To make your product downloadable, you need to create a version of your product and point it to the file you uploaded to your HTTP server earlier.

Before you do that, however, you need to add a *Framework Version* to your software catalog. A Framework version denotes what version of Liferay your plugin is designed for and works on. You cannot add a version of your product without linking it to a version of the framework for which it is designed.

Why is this so important? Because as Liferay gains more and more features, you may wish to take advantage of those features in future versions of your product, while still keeping older versions of your product available for those who are using older versions of Liferay. This is perfectly illustrated in the example My Summary portlet we are using. Liferay had a My Summary portlet of its own, which does exactly what we have described here. This portlet was added to the suite of portlets which Liferay provides in the Social Networking plugin. This plugin makes use of the many social networking features which have been added to Liferay. So rather than just displaying a summary of your information, the Social Networking portlet adds features such as status updates, a “wall” for each user in his or her profile that other users can “write” on, the ability to become “friends” with other users—thereby granting them access to their profiles—and more.

None of this would work in older versions of Liferay, because the core engine that enables developers to create features like this is not there. So in this case, you would want to keep the older My Summary portlet available for users who have not yet upgraded, and make the newer social portlets available to those using latest version of Liferay. This is what *Framework Versions* does for you. If you connect to Liferay's software repositories with an old version of Liferay Portal, you will see the My Summary portlet. If you connect to Liferay's software repositories with new version of Liferay, you will see the social portlets.

So click the *Framework Versions* tab and then click the *Add Framework Version* button.

Give the framework a name, a URL, and leave the *Active* check box checked. For our example, we have entered 6.0.3 for the name, because our portlet should work on that version and higher, and [http://www.liferay.com](http://www.liferay.com) for the URL. Click *Save*.

![Figure 9.25: Adding a Product Version to the Software Catalog](../../images/marketplace-adding-product-version-software-catalog.png)

Now go back to the *Products* tab and click on your product. You will notice that a message is displayed stating that the product does not have any released versions. Click the *Add Product Version* button.

**Version Name:** Enter the version of your product.

**Change Log:** Enter some comments regarding what changed between this version and any previous versions.

**Supported Framework Versions:** Select the framework version for which your software product is intended. Enter a `+` at the end of the version number if you want to specify a version plus any future versions.

**Download Page URL:** If your product has a descriptive web page, enter its URL here.

**Direct Download URL (Recommended):** Enter a direct download link to your software product here. The Plugin Installer portlet will follow this link in order to download your software product.

**Include Artifact in Repository:** To enable others to use the Plugin Installer portlet to connect to your repository and download your plugin, select *yes* here.

When you are finished filling out the form, click the *Save* button. Your product version will be saved, and your product will now be available in the software repository.

##### Generating The Software Catalog

The Software Catalog works by generating an XML document which the Plugin Installer reads. Using the data from this XML document, the Plugin Installer knows where it can download the plugins from, what version of Liferay the plugins are designed for, and all other data about the plugins that have been entered into the Software Catalog portlet.

In order to get your Software Catalog to generate this XML data, you will need to access a particular URL. If you have created a friendly URL for your site (for example, the default site, which is called *guest*, has a friendly URL of `/guest` already configured for it), you can use the friendly URL. If not, you will first need to know the Group ID of the site in which your Software Catalog portlet resides. You can do this by accessing the Manage Pages interface and looking at the URLs for any of the pages. The URL will look something like this: `http://localhost:8080/web/10148/1`.

Obviously, it is much easier if you are using Friendly URLs, which we highly recommend.

Next, go to your browser and go to the following URL:

[http://<server name\>:<port number\>/software\_catalog?<Friendly URL name or Group ID\>](http://<server name\>:<port number\>/software\_catalog?<Friendly URL name or Group ID\>)

For example, if you are on the same machine as your Liferay instance, and that instance is running on port 8080, and your group ID from the database is 10148, you would use the following URL:

[http://localhost:8080/software\_catalog?10148](http://localhost:8080/software\_catalog?10148)

If you have also created a friendly URL called *old-computers* for this site, you would use the following URL:

[http://localhost:8080/software\_catalog?old-computers](http://localhost:8080/software\_catalog?old-computers)

If you have configured everything properly, an XML document should be returned:

	<?xml version="1.0" encoding="UTF-8"?\>
	
	<plugin-repository\>
	
	<settings/\>
	
	<plugin-package\>
	
	<name\>My Summary</name\>
	
	<module-id\>old-computers/my-summary-portlet/1.0/war</module-id\>
	
	<modified-date\>Thu, 23 Apr 2009 20:40:16 +0000</modified-date\>
	
	<types\>
	
	<type\>portlet</type\>
	
	</types\>
	
	<tags\>
	
	<tag\>social</tag\>
	
	<tag\>profile</tag\>
	
	</tags\>
	
	<short-description\>My Summary</short-description\>
	
	<long-description\>My Summary</long-description\>
	
	<change-log\>Initial Version</change-log\>
	
	<download-url\>[http://www.liferay.com/portlets/my-summary-portlet-6.0.4.war](http://www.liferay.com/portlets/my-summary-portlet-5.2.2.war)
	
	</download-url\>
	
	<author\>Rich Sezov</author\>
	
	<screenshots/\>
	
	<licenses\>
	
	<license osi-approved="true"\>MIT License</license\>
	
	</licenses\>
	
	<liferay-versions/\>
	
	</plugin-package\>
	
	</plugin-repository\>

You can now give the URL to your software repository out on your web site, and other administrators of Liferay can enter it into the Plugins Installation module of their Liferay control panels to connect to your repository.

If you want to serve your repository off of a static web server, you can save this document to a file called `liferay-plugin-package.xml` and put this file on your HTTP server. You can then give out the URL to the directory which holds this file on your web site, and anyone with an instance of Liferay will be able to point their Plugin Installer portlets to it.

##### Benefits of the Software Catalog

As you can see, the Software Catalog makes it easy for you to create a repository of your software. Users of Liferay can configure their Plugin Installers to attach to your repository, and the proper versions of your software will be automatically made available to them by a single click. This is by far the easiest way for you to keep track of your software, and for your users to obtain your software.

Another benefit of the Software Catalog is that by using it, you make available to your users a standard interface for manually downloading your software. For those who prefer to manually download plugins, your Software Catalog gives them an interface to go in, find your software either by browsing or by searching, preview screen shots, and download your software—and you don't have to build any of those pages yourself. Simply configure your software in the portlet, and all of that is done for you.

How can you do this? The Software Catalog is also available as a portlet. You can add it to any page on your web site through the *Add Application* menu. You can find the portlet in the *Tools* category.

#### Manually Creating A Software Catalog

If you do not wish to use the control panel to create your software catalog, you can create it manually by manually typing out the XML file that the Software Catalog section of the control panel would normally generate. Note that if you do this, you will not be able to use the Software Catalog portlet as a graphical user interface to your software that end users can use to download your software manually: you will have to build this yourself. Keep in mind that many instances of Liferay Portal sit behind a firewall without access to the Internet. Because of this, if you are making your software available to Internet users, some of them will have to download it manually anyway, because their installations are firewalled. In this case, the Software Catalog portlet is the easiest way to provide a user interface for downloading your software.

If you still wish to use a text editor to create your software catalog, you can. To manually create a software catalog, obtain the DTD for the XML file from Liferay's source code. You will find this DTD in the *definitions* folder in the Liferay source. It is a file called `liferay-plugin-package_6_0_0.dtd`. Use this DTD with a validating XML editor (a good, free choice is jEdit with all the XML plugins) to create your software catalog manually.

#### Connecting to a Software Catalog

If there is a software catalog of plugins that you would like to point your instance of Liferay to, all you need is the URL to the catalog. Once you have the URL, go to the Plugin Installer in your control panel and click the *Configuration* tab. You will see that there are two fields in which you can enter URLs to plugin repositories: *Trusted Plugin Repositories* and *Untrusted Plugin Repositories*. Currently, the only difference between the two is to provide a visual cue for administrators as to which repositories are trusted and untrusted.

Enter the URL to the repository to which you wish to connect in one of the fields and click *Save*. The portlet will connect to the repository, and items from this repository will be shown in the list.

## Summary

We've seen how good a fit Liferay Portal is for the enterprise. It can be scaled linearly to grow to whatever size you need to serve your users. Clustering is a snap, and Liferay harmonizes very well with whatever environment you may have.

Liferay Portal is also built for performance. We discussed how to take advantage of read-writer database configurations, as well as database sharding. You can tune it to support over 3300 concurrent users on a single server with mean login times under half a second and maximum throughput of more than 79 logins per second. We've seen some tips for tuning Liferay Portal, and we have to keep in mind the adage about tuning: load test and profile, tune, repeat.

Finally, we discussed the Plugin Installer, which was the portal's mechanism for managing plugins in versions prior to Liferay 6.1. We also looked at different kinds of plugins, including portlets, themes, layout templates, hooks, and web plugins. We looked at how to install plugins either manually or from a repository and discussed some solutions to potential configuration issues. Finally, we showed how to create your own plugin repository using Liferay's Software Catalog. We hope this information helps you set up your portal with all the software you need to be productive.

In all, Liferay Portal gives you all the options you need to build a high-performance, robust environment that supports your enterprise.

