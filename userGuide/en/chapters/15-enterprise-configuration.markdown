# Enterprise Configuration

Liferay Portal is a robust, enterprise-ready portal solution. As such, it is fully ready to support mission-critical, enterprise applications in an environment configured for multiple redundancy and 24/7 up times. The product, however, like other products of its kind, doesn't come configured this way out of the box, and so there are some steps that need to be taken in order to tune it for your needs. 

This chapter covers these topics in detail. Because Liferay runs on so many different Java EE application servers, it's impossible to cover all the differences between these application servers. For this reason, we'll cover the configuration of Liferay only. As an example, you'll see how to configure Liferay to work in a clustered environment, but not how to create the cluster in your application server. The documentation for your particular application server is always a much better place to learn how to configure it as a cluster, and all application servers do this differently. 

This chapter shows how to configure Liferay for a number of advanced scenarios, such as

-   Clustering and Distributed Caching
-   Liferay Workflow
-   Deploying Customized versions of Liferay
-   Performance Testing and Tuning

During this discussion, we'll mention a number of other open source products upon which Liferay relies for much of this functionality. These products all have their own documentation which should be consulted for a fuller view of what these products can do. For example, Liferay uses Ehcache for its caching mechanism. We'll cover how to configure Ehcache to enable various caching functionality in Liferay, but will refer you to that product's documentation for further information about that product.

Sometimes Liferay supports multiple products which perform the same function. There are, for example, single sign-on  implementations. We'll leave it up to you to select which product best fits the needs of your project without recommending one product over another.

With all of that said, let's get started configuring Liferay for the enterprise.

## Liferay Clustering 

Liferay Portal is designed to serve everything from the smallest to the largest web sites. Out of the box, it's configured optimally for a single server environment. If one server isn't sufficient to serve the high traffic needs of your site, Liferay scales to the size you need.  

![Figure 15.x: Liferay is designed to scale to as large an installation as you need. ](../../images/enterprise-configuration.png) 

Liferay works well in clusters of multiple machines (horizontal clusters) or in clusters of multiple VMs on a single machine (vertical cluster), or any mixture of the two. Once you have Liferay installed in more than one application server node, there are several optimizations that need to be made. At a minimum, Liferay should be configured in the following way for a clustered environment:

- All nodes should be pointing to the same Liferay database or database cluster. 
- Document and Media repositories should be accessible to all nodes of the cluster. 
- Search should be configured for replication or should use a separate search server. 
- Hot deploy folders should be configured for each node if you're not using server farms. 

If you haven't configured your application server to use farms for deployment, the hot deploy folder should be a separate folder for all the nodes, and plugins will have to be deployed to all of the nodes individually. This can be done via a script. If you do have farms configured, you can deploy normally to any node's deploy folder, and your farm configuration should take care of syncing the deployment to all nodes. 

Many of these configuration changes can be made by adding or modifying properties in your `portal-ext.properties` file. Remember that this file overrides the defaults that are in the `portal.properties` file. The original version of this file can be found in the Liferay source code or can be extracted from the `portal-impl.jar` file in your Liferay installation. It is a best practice to copy the relevant section that you want to modify from `portal.properties`* *into your `portal-ext.properties` file, and then modify the values there.

---
![tip](../../images/01-tip.png)**Note:** This chapter documents a Liferay-specific cluster configuration, without getting into specific implementations of third party software, such as Java EE application servers, HTTP servers, and load balancers. Please consult your documentation for those components of your cluster for specific details of those components. Before configuring Liferay in a cluster configuration, make sure your OS is not defining the hostname of your box to the local network at 127.0.0.1.
---

We'll take each of the points above one by one in order to present a clear picture of how to cluster Liferay. 

### All Nodes Should Be Pointing to the Same Liferay Database

This is pretty self-explanatory. Each node should be configured with a data source that points to one Liferay database (or a database cluster) that all the nodes will share. This ensures that all the nodes operate from the same basic data set. This means, of course, that Liferay cannot (and should not) use the embedded HSQL database that is shipped with the bundles (but you already knew that, right?). And, of course, it goes without saying that the database server is a separate physical box from the server which is running Liferay. 

Beyond a database cluster, there are two more advanced options you can use to optimize your database configuration: a read-writer database configuration, and sharding. 

#### Read-Writer Database Configuration

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

Of course, if you wish to have your developers implement your own sharding algorithm, you can do that. You can select which algorithm is active via the `portal-ext.properties` file:

    shard.selector=com.liferay.portal.dao.shard.RoundRobinShardSelector
    #shard.selector=com.liferay.portal.dao.shard.ManualShardSelector
    #shard.selector=[your implementation here]

Enabling sharding is easy. You will need to make sure you are using Liferay's data source implementation instead of your application server's. Set your various database shards in your `portal-ext.properties` file this way:

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

Once you do this, you can set up your DNS so that several domain names point to your Liferay installation (e.g., abc1.com, abc2.com, abc3.com). Next, go to the Control Panel and click *Portal Instances* in the Server category. Create two to three instances bound to the DNS names you have configured.

If you are using the RoundRobinShardSelector class, Liferay will automatically enter data into each instance one by one, automatically. If you are using the ManualShardSelector class, you'll have to specify a shard for each instance using the UI.

![Figure 15.x: When creating a shard using the manual shard selector, specify the shard you want to use for that instance.](../../images/enterprise-sharding-portal-instance.png)

The last thing you will need to do is modify the `spring.configs` section of your `portal-ext.properties` file to enable the sharding configuration, which by default is commented out. To do this, your `spring.configs` should look like this (modified section is in bold):

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

That's all there is to it. Your system is now set up for sharding. Now that you've got your database set up and optimized for a large installation, let's turn to clustering the Document and Media Library. 

### Documents and Media Library clustering

Liferay 6.1 introduces a new Documents and Media Library which is capable of mounting several repositories at a time and presenting a unified interface to the user. By default, users can make use of the Liferay repository, which is already mounted. This repository is built into Liferay Portal and can use as its back-end one of several different store implementations. In addition to this, many different kinds of third party repositories can be mounted. If you have a separate repository that you've mounted, all nodes of the cluster will point to this repository. Your avenue for improving performance at that point is to cluster your third party repository, using the documentation for the repository you have chosen. If you don't have a third party repository, there are ways you can configure the Liferay repository to perform well in a clustered configuration. 

The main thing to keep in mind is that you need to make sure that every node of the cluster has the same access to the file store as every other node. For this reason, you'll need to take a look at your store configuration. 

There are several options available for configuring how Liferay's document and media library stores files. Each option is a *store* which can be configured through the `portal-ext.properties` file by setting the `dl.store.impl=` property. Let's consider the ramifications of the various store options. 

#### Using the File System store

This is the store that Liferay uses by default. It's a simple file storage implementation that uses a local folder to store files. You can use the file system for your clustered configuration, but you'd have to make sure that the folder to which you point the store can handle things like concurrent requests and file locking. For this reason, you need to use a Storage Area Network or a clustered file system.

The file system store was the first store created for Liferay and is heavily bound to the Liferay database. By default, documents are stored in a `document_library` subfolder of the `data` folder in a Liferay bundle. Of course, you can change this path to anything you want by using the `dl.store.file.system.root.dir=` property. 

This store creates a folder structure based on primary keys in the Liferay database. If, for example, you upload a presentation with the file name `workflow.odp` into a folder called *stuff*, the file system store creates a folder structure which looks like figure 15.x. 

![Figure 15.x: Liferay's file system store creates a folder structure based on primary keys in Liferay's database. ](../../images/enterprise-file-system-store.png)

The first folder is the company ID to which the site belongs. The second folder is the group ID of the site where the document resides. The third is the ID of the document itself, and finally the file name of the document is renamed to a version number for storing multiple versions of the document. 

As you can see, this binds your documents very closely to Liferay, and may not be exactly what you want. But if you've been using the default settings for a while and need to migrate your documents, Liferay provides a migration utility in the control panel in *Server Administration* &rarr; *Data Migration*. Using this utility, you can move your documents very easily from one store implementation to another. 

Speaking of other store implementations, let's look at some others that Liferay provides. 

#### Using the Advanced File System store

Liferay's advanced file system store is similar to the default file system store. Like that store, it saves files to the local file system--which, of course, could be a remote file system mount. It uses a slightly different folder structure to store files, which is pictured in figure 15.x. 

![Figure 15.x: The advanced file system store creates a more nested folder structure than the file system store. ](../../images/enterprise-adv-file-system-store.png)

So what makes the advanced file system store *advanced*? Several operating systems have limitations on the number of files which can be stored in a particular folder. The advanced file system store overcomes this limitation by programmatically creating a structure that can expand to millions of files, by alphabetically nesting the files in folders. This not only allows for more files to be stored, but also improves performance as there are less files stored per folder. 

The same rules apply for the advanced file system store as apply for the file system store. To cluster this, you'll need to point the store to a network mounted file system that all the nodes can access, and that networked file system needs to support concurrent requests and file locking. Otherwise, you may experience data corruption issues of two users attempt from two different nodes to write to the same file at the same time. 

You may decide that the advanced file system store for whatever reason doesn't serve your needs. If this is the case, you can of course mount other file systems into the documents and media library. In addition to this, you can also redefine the Liferay store to use one of three other supported protocols. We'll look at these next. 

#### Using the CMIS store

Though you can mount as many different CMIS (Content Management Interoperability Services) repositories as you like in the documents and media library, you may wish also to redefine the Liferay repository to point to a CMIS repository as well. Why? Because, as you know, users are users, and it's possible that they may find a way to create a folder or upload content to the Liferay repository. It would be nice if that Liferay repository was connected to a clustered CMIS repository by the administrator without having to mount it through the UI. The CMIS store allows you to do just that. 

If you wish to use the CMIS store, all you need to is set the following four directives in your `portal-ext.properties` file: 

    dl.store.cmis.credentials.username=yourusername
    dl.store.cmis.credentials.password=yourpassword
    dl.store.cmis.repository.url=http://localhost:8080/url/to/your/cmis/repository
    dl.store.cmis.system.root.dir=Liferay Home

Now the Liferay repository is connected to CMIS via the CMIS store. As long as all nodes are pointing to your CMIS repository, everything in your Liferay cluster should be fine, as the CMIS protocol prevents multiple simultaneous file access from causing data corruption. 

From here, we'll move on to the JCR store. 
    
#### Using the JCR store

Liferay Portal supports as a store the Java Content Repository standard. Under the hood, Liferay uses Jackrabbit—-which is a project from Apache-—as its JSR-170 compliant document repository. By default, Jackrabbit is configured to store the documents on the local file system upon which Liferay is installed, in the `$LIFERAY_HOME/liferay/jackrabbit` folder. Inside this folder is Jackrabbit's configuration file, called `repository.xml`. 

Using the default settings, the JCR store is not very different from the file system stores, except that you can use any JCR client to access the files. You can, however, modify Jackrabbit's configuration so that it stores files in a database that can be accessed by all nodes, and so that it operates as a cluster within Liferay's cluster. 

To simply move the default repository location to a shared folder, you do not need to edit Jackrabbit's configuration file. Instead, find the section in `portal.properties` labeled **JCR** and copy/paste that section into your `portal-ext.properties` file. One of the properties, by default, is the following:

    jcr.jackrabbit.repository.root=${liferay.home}/data/jackrabbit

Change this property to point to a shared folder that all of the nodes can see. A new Jackrabbit configuration file is then generated in that location, and you'll have to edit that file to modify Jackrabbit's configuration. 

Note that because of file locking issues, this isn't the best way to share Jackrabbit resources. If you have two people logged in at the same time uploading content, you could encounter data corruption using this method, and because of this, we don't recommend it for a production system. Instead, if you want to use the Java Content Repository in a cluster, you should redirect Jackrabbit into your database of choice. You can use the Liferay database or another database for this purpose. This requires editing Jackrabbit's configuration file.

The default Jackrabbit configuration file has sections commented out for moving the Jackrabbit configuration into the database. This has been done to make it as easy as possible to enable this configuration. To move the Jackrabbit configuration into the database, simply comment out the sections relating to the file system and comment in the sections relating to the database. These by default are configured for a MySQL database. If you are using another database, you will likely need to modify the configuration, as there are changes to the configuration file that are necessary for specific databases. For example, the default configuration uses Jackrabbit's `DbFileSystem` class to mimic a file system in the database. While this works well in MySQL, it doesn't work for all databases. For example, if you're using an Oracle database, you'll need to modify this to use `OracleFileSystem`. 

Modify the JDBC database URLs so that they point your database. Don't forget to create the database first, and grant the user ID you are specifying in the configuration file access to create, modify, and drop tables. After this, be sure to uncomment the `<Cluster/>` section at the bottom of the file. For further information, it's best to check out the Jackrabbit documentation. Please see the Jackrabbit documentation at `http://jackrabbit.apache.org` for further information. 

Once you've configured Jackrabbit to store its repository in a database, the next time you bring up Liferay, the necessary database tables are created automatically. Jackrabbit, however, does not create indexes on these tables, and so over time this can be a performance penalty. To fix this, you'll need to manually go into your database and index the primary key columns for all the Jackrabbit tables.

All your Liferay nodes should be configured to use the same Jackrabbit repository in the database. Once that is working, you can create a Jackrabbit cluster (please see the section below).

#### Other Storage Options

There are other options available to configure Liferay's Document Library. The default option has the best performance with large document libraries, because it simply uses the file system. If you require a JSR-170 compliant document store, you can use Jackrabbit, which can be configured to use the file system or database, depending on your needs. If, however, you have very specific configuration needs, or if you already have a content repository that you want to continue using, you might want to use one of the following options Liferay has available.

##### Advanced File System Hook

To use the Advanced File System Hook, set:

    dl.hook.impl=com.liferay.documentlibrary.util.AdvancedFileSystemHook

This is the preferred hook for clustered environments especially if you're using a SAN to store files.

From a performance standpoint, this method is superior to using Jackrabbit. The Advanced File System Hook distributes the files into multiple directories and thus circumvents file system limitations. Liferay does not implement any file level locking, so only use this if you’re using a SAN that supports file locking (most modern ones do but check your SAN documentation to be sure).

The path for storing your documents is set also set using the `dl.hook.file.system.root.dir` property in the `portal-ext.properties`.

##### Amazon Simple Storage

To use Amazon’s Simple Storage Service to store you documents for a Liferay Portal, set

    dl.hook.impl=com.liferay.documentlibrary.util.S3Hook

in `portal-ext.properties`. You will need to consult the Amazon Simple Storage documentation for additional details on setting it up.

##### Documentum

You can use this hook to store your documents with Documentum. Before configuring Documentum, you will need to install the `documentum-hook` plugin. Before installing, however, please note that the `documentum-hook` plugin is only supported on Liferay Enterprise Edition, and is currently in an experimental stage, and may not be ready for production use. To use this hook, set

    dl.hook.impl=liferay.documentum.hook.DocumentumHook

If you are using Documentum, there are additional settings that must be configured in the `${liferay_home}/documentum-hook/docroot/WEB-INF/src/dfc.properties` and `documentum-hook/docroot/WEB-INF/src/portlet.properties` files.

### Search Configuration

You can configure search in one of two ways: use pluggable enterprise search (recommended for a cluster configuration) or configure Lucene in such a way that either the index is stored on each node's file system or is shared in a database.

#### Pluggable Enterprise Search

As an alternative to using Lucene, Liferay supports pluggable search engines. The first implementation of this uses the open source search engine *Solr,* but in the future there will be many such plugins for your search engine of choice. This allows you to use a completely separate product for search, which can be installed on another application server in your environment. Your search engine then operates completely independently of your Liferay Portal nodes in a clustered environment, acting as a search service for all of the nodes simultaneously.

This solves the problem described below with sharing Lucene indexes. You can now have one search index for all of the nodes of your cluster without having to worry about putting it in a database (if you wish, you can still do this if you configure Solr or another search engine that way) or maintaining separate search indexes on all of your nodes. Each Liferay node will send requests to the search engine to update the search index when needed, and these updates are then queued and handled automatically by the search engine, independently.

##### Configuring the Solr Search Server

Since Solr is a standalone search engine, you will need to download it and install it first according to the instructions on the Solr web site (`http://lucene.apache.org/solr`). Of course, it is best to use a server that is separate from your Liferay installation, as your Solr server will be responsible for all indexing and searching for your entire cluster. Solr is distributed as a .war file with several .jar files which need to be available on your application server's class path. Once you have Solr up and running, integrating it with Liferay is easy, but it will require a restart of your application server.

The first thing you will need to define is the location of your search index. Assuming you are running a Linux server and you have mounted a file system for the index at `/solr`, create an environment variable that points to this folder. This environment variable needs to be called `$SOLR_HOME`. So for our example, we would define:

    $SOLR_HOME=/solr

This environment variable can be defined anywhere you need: in your operating system's start up sequence, in the environment for the user who is logged in, or in the start up script for your application server. If you are going to use Tomcat to host Solr, you would modify `setenv.sh` or `setenv.bat` and add the environment variable there.

Once you have created the environment variable, you then can use it in your application server's start up configuration as a parameter to your JVM. This is configured differently per application server, but again, if you are using Tomcat, you would edit `catalina.sh` or `catalina.bat` and append the following to the `$JAVA_OPTS` variable:

    -Dsolr.solr.home=$SOLR_HOME

This takes care of telling Solr where to store its search index. Go ahead and install Solr to this box according to the instructions on the Solr web site (`http://lucene.apache.org/solr`). Once it's installed, shut it down, as there is some more configuration to do.

##### Installing the Solr Liferay Plugin

Next, you have a choice. If you have installed Solr on the same system upon which Liferay is running, you can simply go to the Control Panel and install the *solr-web* plugin. This, however, defeats much of the purpose of using Solr, because the goal is to offload search indexing to another box in order to free up processing for your installation of Liferay. For this reason, you should not run Liferay and your search engine on the same box. Unfortunately, the configuration in the plugin defaults to having Solr and Liferay running on the same box, so to run them separately, you will have to make a change to a configuration file in the plugin before you install it so you can tell Liferay where to send indexing requests. In this case, go to the Liferay web site (`http://www.liferay.com`) and download the plugin manually.

Open or extract the plugin. Inside the plugin, you will find a file called `solr-spring.xml` in the `WEB-INF/classes/META-INF` folder. Open this file in a text editor and you will see the entry which defines where the Solr server can be found by Liferay:

    <bean     class="com.liferay.portal.spring.context.PortletBeanFactoryPostProcessor"     />

    <bean id="com.liferay.portal.search.solr.server.BasicAuthSolrServer"     class="com.liferay.portal.search.solr.server.BasicAuthSolrServer">          <constructor-arg     type="java.lang.String"value="http://localhost:8080/solr" />

Modify this value so that they point to the server upon which you are running Solr. Then save the file and put it back into the plugin archive in the same place it was before.

Next, extract the file `schema.xml` from the plugin. It should be in the `docroot/WEB-INF/conf` folder. This file tells Solr how to index the data coming from Liferay, and can be customized for your installation. Copy this file to `$SOLR_HOME/conf` (you may have to create the `conf` directory) on your Solr box. Now you can go ahead and start Solr.

You can now hot deploy the `solr-web` plugin to all of your nodes. See the next section for instructions on hot deploying to a cluster.

Once the plugin is hot deployed, your Liferay search is automatically upgraded to use Solr. It is likely, however, that initial searches will come up with nothing: this is because you will need to reindex everything using Solr.

Go to the Control Panel. In the *Server* section, click *Server Administration*. Click the *Execute* button next to *Reindex all search indexes *at the bottom of the page. It may take a while, but Liferay will begin sending indexing requests to Solr for execution. When the process is complete, Solr will have a complete search index of your site, and will be running independently of all of your Liferay nodes.

Installing the plugin to your nodes has the effect of overriding any calls to Lucene for searching. All of Liferay's search boxes will now use Solr as the search index. This is ideal for a clustered environment, as it allows all of your nodes to share one search server and one search index, and this search server operates independently of all of your nodes.

#### Lucene Configuration

Lucene, the search indexer which Liferay uses, can be in a shared configuration for a clustered environment, or an index can be created on each node of the cluster. The easiest configuration to implement is to have an index on each node of the cluster. Liferay provides a method called ClusterLink which can send indexing requests to all nodes in the cluster to keep them in sync. This configuration does not require any additional hardware, and it performs very well. It may increase network traffic when an individual server reboots, since in that case a full reindex will be needed. But since this should only rarely happen, it's a good tradeoff if you don't have the extra hardware to implement a Solr search server.

You can enable ClusterLink by setting one property in your `portal-ext.properties `file:

    cluster.link.enabled=true

Of course, this needs to be set on all the nodes.

If you wish to have a shared index, you will need to either share the index on the file system or in the database. This requires changing your Lucene configuration.

The Lucene configuration can be changed by modifying values in your `portal-ext.properties` file. Open your `portal.properties` file and search for the text *Lucene*. Copy that section and then paste it into your `portal-ext.properties` file.

If you wish to store the Lucene search index on a file system that is shared by all of the Liferay nodes, you can modify the location of the search index by changing the `lucene.dir` property. By default, this property points to the `lucene` folder inside the Liferay home folder:

    lucene.dir=${liferay.home}/data/lucene/

Change this to the folder of your choice. To make the change take effect, you will need to restart Liferay. You can point all of the nodes to this folder, and they will use the same index.

Like Jackrabbit, however, this is not the best way to share the search index, as it could result in file corruption if different nodes try reindexing at the same time. We do not recommend this for a production system. A better way is to share the index is via a database, where the database can enforce data integrity on the index. This is very easy to do; it is a simple change to your `portal-ext.properties` file.

There is a single property called `lucene.store.type`. By default this is set to go to the file system. You can change this so that the index is stored in the database by making it the following:

    lucene.store.type=jdbc

The next time Liferay is started, new tables will be created in the Liferay database, and the index will be stored there. If all the Liferay nodes point to the same database tables, they will be able to share the index. Performance on this is not always as good as it could be. Your DBAs may be able to tweak the database indexes a bit to improve performance. For better performance, you should consider using a separate search server (see the section on Solr above).

**Note:** MySQL users need to modify their JDBC connection string for this to work. Add the following parameter to your connection string:

    emulateLocators=true

Alternatively, you can leave the configuration alone, and each node will then have its own index. This ensures that there are no collisions when multiple nodes update the index, because they all will have separate indexes. This, however, creates duplicate indexes and may not be the best use of resources. Again, for a better configuration, you should consider using a separate search server (see the section on Solr above).

### Hot Deploy

Plugins which are hot deployed will need to be deployed separately to all of the Liferay nodes. Each node should, therefore, have its own hot deploy folder. This folder needs to be writable by the user under which Liferay is running, because plugins are moved from this folder to a temporary folder when they are deployed. This is to prevent the system from entering an endless loop, because the presence of a plugin in the folder is what triggers the hot deploy process.

When you want to deploy a plugin, copy that plugin to the hot deploy folders of all of the Liferay nodes. Depending on the number of nodes, it may be best to create a script to do this. Once the plugin has been deployed to all of the nodes, you can then make use of it (by adding the portlet to a page or choosing the theme as the look and feel for a page or page hierarchy).

Some containers contain a facility which allows the end user to deploy an application to one node, after which it will get copied to all of the other nodes. If you have configured your application server to support this, you won't need to hot deploy a plugin to all of the nodes—your application server will handle it transparently. Make sure, however, that you use Liferay's hot deploy mechanism to deploy plugins, as in many cases Liferay slightly modifies plugin .war files when hot deploying them.

All of the above will get basic Liferay clustering working; however, the configuration can be further optimized. We will see how to do this next.

## Distributed Caching

Liferay uses **Ehcache**, which has robust distributed caching support. This means that the cache can be distributed across multiple Liferay nodes running concurrently. Enabling this cache can increase performance dramatically. For example, say that two users are browsing the message boards. The first user clicks on a thread in order to read it. Liferay must look up that thread from the database and format it for display in the browser. With a distributed Ehcache running, this thread can be pulled from the database and stored in a cache for quick retrieval. Say then that the second user wants to read the same forum thread and clicks on it. This time, because the thread is in the local cache, no trip to the database is necessary, and so retrieving the data is much faster.

This could be done by simply having a cache running separately on each node, but the power of *distributed *caching allows for more functionality. The first user can post a message to the thread he or she was reading, and the cache will be updated across all of the nodes, making the new post available immediately from the local cache. Without that, the second user would need to wait until the cache was invalidated on the node he or she connected to before he or she could see the updated forum post.

Configuring distributed caching requires the modification of the `portal-ext.properties`* *file as well as one or more other files depending on what you want to cache. The first thing you will want to do is determine where on your server you will want to store your cache configuration files. This will have to be somewhere on Liferay's class path, so you will need to find where your application server has stored the deployed version of Liferay, and create a folder in Liferay's `WEB-INF/classes` folder to store the files. Because the original, default files are stored inside of a .jar file, you will need to extract them to this area and then tell Liferay (by use of the `portal-ext.properties`* *file) where they are.

For example, say you are running Liferay on Tomcat. Tomcat stores the deployed version of Liferay in `<Tomcat Home>/webapps/ROOT`. Inside of this folder is the folder structure `WEB-INF/classes`*. *You can create a new folder in here called `myehcache`* *to store the custom versions of the cache configuration files. Copy the files from the `/ehcache` folder—which is inside the `portal-impl.jar`* *file*—*into the `myehcache` folder you just created. You then need to modify the properties in `portal-ext.properties`* *that point to these files. Copy / paste the **Hibernate** section of `portal.properties`* *into your `portal-ext.properties`* *file and then modify the `net.sf.ehcache.configurationResourceName` property to point to the clustered version of the configuration file that is now in your custom folder:

    net.sf.ehcache.configurationResourceName=/myehcache/hibernate-clustered.xml

Now that Liferay is pointing to your custom file, you can modify the settings in this file to change the cache configuration for Hibernate.

Next, copy / paste the *Ehcache* section from the `portal.properties` file into your `portal-ext.properties` file. Modify the properties so that they point to the files that are in your custom folder. For example:

    ehcache.multi.vm.config.location=/**myehcache**/liferay-multi-vm.xml

If you are going to enable distributed clustering, uncomment the following line and point it to your custom version of the file:

    ehcache.multi.vm.config.location=/**myehcache**/liferay-multi-vm-clustered.xml

You can now take a look at the settings in these files and tune them to fit your environment and application.

Alternatively, if your Liferay project is using the Ext plugin to make customizations to Liferay, you can place your cache configuration in the extension environment. The settings there will override the default settings that ship with Liferay. If you wish to do this, you can create new versions of the files in `ext-impl/src/ehcache`. The files should be have with `-ext.xml`tacked onto the end of the file name. For example, the custom version of `hibernate.xml`* *should be called `hibernate-ext.xml`*, *and the custom version of` liferay-multi-vm-clustered.xml`* *should be called `liferay-multi-vm-clustered-ext.xml`*. *You can then modify the files and tune them to fit your environment / application, and they will be deployed along with the rest of your extension environment.

#### Hibernate Cache Settings

By default, Hibernate (Liferay's database persistence layer) is configured to use Ehcache as its cache provider. This is the recommended setting. The default configuration, however, points to a file that does not have clustering enabled. To enable clustering, copy the *Hibernate* section from `portal.properties` into your `portal-ext.properties` file. To enable a clustered cache, comment out the default file (`hibernate.xml`) and uncomment the clustered version of the file, making sure that you change the path so that it points to your custom version of the file:

    net.sf.ehcache.configurationResourceName=/**myehcache**/hibernate-clustered.xml

Next, open this file in a text editor. You will notice that the configuration is already set up to perform distributed caching through a multi-cast connection. It is likely, however, that the configuration is not set up optimally for your particular application. You will notice that by default, the only object cached in the Hibernate cache is the User object (`com.liferay.``portal``.model.impl.UserImpl`). This means that when a user logs in, his or her User object will go in the cache so that any portal operation that requires access to it (such as permission checking) can retrieve that object very quickly from the cache.

You may wish to add other objects to the cache. For example, a large part of your application may be document management using the Document Library portlet. In this case, you may want to cache Document Library objects, such as `DLFileEntryImpl` in order to improve performance as users access documents. To do that, add another block to the configuration file with the class you want to cache:

    <cache     name="**com.liferay.portlet.documentlibrary.model.impl.DLFileEntryImpl**"     maxElementsInMemory="10000"     eternal="false"     timeToIdleSeconds="600"     overflowToDisk="true"     > 	<cacheEventListenerFactory 	class="net.sf.ehcache.distribution.RMICacheReplicatorFactory" 	properties="replicatePuts=false,replicateUpdatesViaCopy=false" 	propertySeparator="," 	/> 	<bootstrapCacheLoaderFactory 	class="net.sf.ehcache.distribution.RMIBootstrapCacheLoaderFactory" />     </cache>

Your site may use the message boards portlet, and those message boards may get a lot of traffic. To cache the threads on the message boards, configure a block with the `MBMessageImpl` class:

    <cache     name="**com.liferay.portlet.messageboards.model.impl.MBMessageImpl**"     maxElementsInMemory="10000"     eternal="false"     timeToIdleSeconds="600"     overflowToDisk="true"     > 	<cacheEventListenerFactory 	class="net.sf.ehcache.distribution.RMICacheReplicatorFactory" 	properties="replicatePuts=false,replicateUpdatesViaCopy=false" 	propertySeparator="," 	/> 	<bootstrapCacheLoaderFactory 	class="net.sf.ehcache.distribution.RMIBootstrapCacheLoaderFactory" />     </cache>

Note that if your developers have overridden any of these classes, you will have to specify the overridden versions rather than the stock ones that come with Liferay Portal.

As you can see, it is easy to add specific data to be cached. Be careful, however, as too much caching can actually reduce performance if the JVM runs out of memory and starts garbage collecting too frequently. You will likely need to experiment with the memory settings on your JVM as well as the cache settings above. You can find the specifics about these settings in the documentation for Ehcache.

## Performance Tuning

Once you have your portal up and running, you may find a need to tune it for performance, especially if your site winds up generating more traffic than you'd anticipated. There are some definite steps you can take with regard to improving Liferay's performance.

### Memory

Memory is one of the first things to look at when you want to optimize performance. If you have any disk swapping, that will have a serious impact on performance. Make sure that your server has an optimal amount of memory and that your JVM is tuned to use it.

There are three basic JVM command switches that control the amount of memory in the Java heap.

    -Xms          -Xmx          -XX:MaxPermSize

These three settings control the amount of memory available to the JVM initially, the maximum amount of memory into which the JVM can grow, and the separate area of the heap called Permanent Generation space.

The first two settings should be set to the same value. This prevents the JVM from having to reallocate memory if the application needs more. Setting them to the same value causes the JVM to be created up front with the maximum amount of memory you want to give it.

    -Xms1024m -Xmx1024m -XX:MaxPermSize=128m

This is perfectly reasonable for a moderately sized machine or a developer machine. These settings give the JVM 1024MB for its regular heap size and have a PermGen space of 128MB. If, however, you have Liferay on a server with 4GB of RAM and you are having performance problems, the first thing you might want to look at is increasing the memory available to the JVM. You will be able to tell if memory is a problem by running a profiler (such as Jprobe, YourKit, or the NetBeans profiler) on the server. If you see Garbage Collection (GC) running frequently, you will definitely want to increase the amount of memory available to the JVM.

Note that there is a law of diminishing returns on memory, especially with 64 bit systems. These systems allow you to create very large JVMs, but the larger the JVM, the more time it takes for garbage collection to take place. For this reason, you probably won't want to create JVMs of more than 2 GB in size. To take advantage of higher amounts of memory on a single system, run multiple JVMs of Liferay instead.

Issues with PermGen space can also affect performance. PermGen space contains long-lived classes, anonymous classes and interned Strings. Hibernate, in particular—which Liferay uses extensively—has been known to make use of PermGen space. If you increase the amount of memory available to the JVM, you may want to increase the amount of PermGen space accordingly.

### Garbage Collection

As the system runs, various Java objects are created. Some of these objects are long-lived, and some are not. The ones that are not become *de-referenced*, which means that the JVM no longer has a link to them because they have ceased to be useful. These may be variables that were used for methods which have already returned their values, objects retrieved from the database for a user that is no longer logged on, or a host of other things. These objects sit in memory and fill up the heap space until the JVM decides it's time to clean them up.

Normally, when garbage collection (GC) runs, it stops all processing in the JVM while it goes through the heap looking for dead objects. Once it finds them, it frees up the memory they were taking up, and then processing can continue. If this happens in a server environment, it can slow down the processing of requests, as all processing comes to a halt while GC is happening.

There are some JVM switches that you can enable which can reduce the amount of time processing is halted while garbage collecting happens. These can improve the performance of your Liferay installation if applied properly. As always, you will need to use a profiler to monitor garbage collection during a load test to tune the numbers properly for your server hardware, operating system, and application server.

![image](../../images/portal-admin-ch7_html_518957a7.gif) *Illustration 10: Java memory*

The Java heap is divided into sections for the young generation, the old generation, and the permanent generation. The young generation is further divided into three sections: Eden, which is where new objects are created, and two “survivor spaces,” which we can call the *From* and *To* spaces. Garbage collection occurs in stages. Generally, it is more frequently done in the young generation, less frequently done in the old generation, and even less frequently done in the permanent generation, where long-lived objects reside. When garbage collection runs in the young generation, Eden is swept for objects which are no longer referenced. Those that are still around are moved to the “To” survivor space, and the “From” space is then swept. Any other objects in that space which still have references to them are moved to the “To” space, and the “From” space is then cleared out altogether. After this, the “From” and the “To” spaces swap roles, and processing is freed up again until the next time the JVM determines that garbage collection needs to run.

After a predetermined number of “generations” of garbage collection, surviving objects may be moved to the old generation. Similarly, after a predetermined number of “generations” of garbage collection in the old generation, surviving objects may be moved to the permanent generation.

By default, the JDK uses a serial garbage collector to achieve this. This works very well for a short-lived desktop Java application, but is not necessarily the best performer for a server-based application like Liferay. For this reason, you may wish to switch to the Concurrent Mark-Sweep (CMS) collector.

Rather than halting application processing altogether, this garbage collector makes one short pause in application execution to mark objects directly reachable from the application code. Then it allows the application to run while it marks all objects which are reachable from the set it marked. Finally, it adds another phase called the *remark* phase which finalizes marking by revisiting any objects modified while the application was running. It then sweeps through and garbage collects. This has the effect of greatly reducing the amount of time that execution needs to be halted in order to clean out dead objects.

Just about every aspect of the way memory management works in Java can be tuned. In your profiling, you may want to experiment with some of the following settings to see if any of them can increase your performance.

**NewSize, MaxNewSize:** The initial size and the maximum size of the New or Young Generation.

**+UseParNewGC:** Causes garbage collection to happen in parallel, using multiple CPUs. This decreases garbage collection overhead and increases application throughput.

**+UseConcMarkSweepGC:** Use the Concurrent Mark-Sweep Garbage Collector. This uses shorter garbage collection pauses, and is good for applications that have a relatively large set of long-lived data, and that run on machines with two or more processors, such as web servers.

**+CMSParallelRemarkEnabled:** For the CMS GC, enables the garbage collector to use multiple threads during the CMS remark phase. This decreases the pauses during this phase.

**SurvivorRatio:** Controls the size of the two survivor spaces. It's a ratio between the survivor space size and Eden. The default is 25. There's not much bang for the buck here, but it may need to be adjusted.

**ParallelGCThreads:** The number of threads to use for parallel garbage collection. Should be equal to the number of CPU cores in your server.

A sample configuration using the above parameters might look something like this:

    JAVA_OPTS="$JAVA_OPTS -XX:NewSize=700m -XX:MaxNewSize=700m -Xms2048m     -Xmx2048m -XX:MaxPermSize=128m -XX:+UseParNewGC -XX:+UseConcMarkSweepGC     -XX:+CMSParallelRemarkEnabled -XX:SurvivorRatio=20     -XX:ParallelGCThreads=8"

### Properties File Changes

There are also some changes you can make to your *portal-ext.properties* file once you are in a production environment.

Set the following to false to disable checking the last modified date on server side CSS and JavaScript.

    last.modified.check=false

Set this property to true to load the theme's merged CSS files for faster loading for production. By default it is set to false for easier debugging for development. You can also disable fast loading by setting the URL parameter *css_fast_load* to *0*.

    theme.css.fast.load=true

Set this property to true to load the combined JavaScript files from the property *javascript.files* into one compacted file for faster loading for production. By default it is set to false for easier debugging for development. You can also disable fast loading by setting the URL parameter *js_fast_load* to *0*.

    javascript.fast.load=true

### Servlet Filters

Liferay comes by default with 17 servlet filters enabled and running. It is likely that for your installation, you don't need them all.

To disable a servlet filter, simply comment it out of your `web.xml` file.

If there is a feature supported by a servlet filter that you know you are not using, you can comment it out as well to achieve some performance gains. For example, if you are not using CAS for single sign-on, comment out the CAS Filter. If you are not using NTLM for single sign-ons, comment out the Ntlm Filter. The fewer servlet filters you are running, the less processing power is needed for each request.

### Portlets

Liferay comes pre-bundled with many portlets which contain a lot of functionality, but not every web site that is running on Liferay needs to use them all. In *portlet.xml* and *liferay-portlet.xml*, comment out the ones you are not using. While having a loan calculator, analog clock, or game of hangman available for your users to add to pages is nice, those portlets may be taking up resources that are needed by custom portlets you have written for your site. If you are having performance problems, commenting out some of the unused portlets may give you the performance boost you need.

## Liferay Services Oriented Architecture

Liferay includes a utility called the *Service Builder* which is used to generate all of the low level code for accessing resources from the portal database. This utility is further explained in *Liferay in Action*, but it is mentioned here because of its feature which generates interfaces not only for Java code, but also for web services and JavaScript. This means that the method calls for storing and retrieving portal objects are all the same, and are generated in the same step.

*I![image](../../images/portal-admin-ch6_html_m1232b4ba.png)\ llustration 11: Liferay SOA's first layer of security. *

\ \ Because the actual method calls for retrieving data are the same regardless of how one gets access to those methods (i.e., locally or through web services), Liferay provides a consistent interface for accessing portal data that few other products can match. The actual interfaces for the various services are covered in *Liferay in Action*, but before they can be used there are steps that need to be taken to enable users to access those services remotely.

In the default `portal.properties` file, there is a section called **Main Servlet**. This section defines the security settings for all of the remote services provided by Liferay. Copy this section and paste it into your custom `portal-ext.properties` file, and you can configure security settings for the Axis Servlet, the Liferay Tunnel Servlet, the Spring Remoting Servlet, the JSON Tunnel Servlet, and the WebDAV servlet.

By default, a user connecting from the same machine Liferay is running on can access remote services so long as that user has the permission to use those services in Liferay's permissions system. Of course, you are not really “remote” unless you are accessing services from a different machine. Liferay has two layers of security when it comes to accessing its services remotely. Without explicit rights to both layers, a remote exception will be thrown and access to those services will not be granted.

The first layer of security that a user needs to get through in order to call a method from the service layer is servlet security. The *Main Servlet* section of the `portal-ext.properties` file is used to enable or disable access to Liferay's remote services. In that section of the properties file, there are properties for each of Liferay's remote services.

You can set each service individually with the security settings that you require. For example, you may have a batch job which runs on another machine in your network. This job looks in a particular shared folder on your network and uploads documents to your community's document library portlet on a regular basis, using Liferay's web services. To enable this batch job to get through the first layer of security, you would modify the `portal-ext.properties` file and put the IP address of the machine on which the batch job is running in the list for that particular service. For example, if the batch job uses the Axis web services to upload the documents, you would enter the IP address of the machine on which the batch job is running to the `axis.servlet.hosts.allowed` property. A typical entry might look like this:

axis.servlet.hosts.allowed=192.168.100.100, 127.0.0.1, SERVER\_IP

If the machine on which the batch job is running has the IP address `192.168.100.100`, this configuration will allow that machine to connect to Liferay's web services and pass in user credentials to be used to upload the documents.

![image](../../images/portal-admin-ch6_html_57e67878.png)\ *Illustration 12: Liferay SOA's second layer of security. *

The second layer of security is Liferay's security model that it uses for every object in the portal. The user ID that accesses the services remotely must have the proper permission to operate on the objects it will be accessing. Otherwise, a remote exception will be thrown. The Portal Administrator will need to make use of Liferay's usual means of granting access to these resources to the user ID that will be operating on them remotely. For example, say that a Document Library folder called *Documents* has been set up in a community. A role has been created called *Document Uploaders* which has the rights to add documents to this folder. Your batch job will be accessing Liferay's web services in order to upload documents into this folder. In order for this to work, you will have to call the web service using a user ID that is a member of this group (or that has individual rights to add documents to this folder). Otherwise, you will be prevented from using the Web Service.

To call the web service using credentials, you would use the following URL syntax:

http://" + userIdAsString + ":" + password + "@<server.com\>:<port\>/tunnel-web/secure/axis/" + serviceName

The user ID is the user's ID from the Liferay database. This may be obtained by logging in as the user and clicking *My Account* from the Dock. In the top left corner of the portlet that appears is the user ID.

For example, to get Organization data using a user that has the ID of *2* with a password of *test*, you would use the following URL:

http://2:test@localhost:8080/tunnel-web/secure/axis/Portal\_OrganizationService

It is important to note here how *Password Policies* (covered in Chapter 3) can be used in combination with this feature. If you are enforcing password policies on your users (requiring them to change their passwords on a periodic basis, etc.), any administrative ID which accesses Liferay's web services in a batch job will have its password expire too.

To prevent this from happening, you can add a new password policy which does not enforce the password expiration and add your administrative user ID to it. Then your batch job can run as many times as you need it to, and the administrative ID's password will never expire.

In summary, accessing Liferay remotely requires the successful passing of two security checks:

1.  The IP address must be pre-configured in the server's     `portal-ext.properties` file.

2.  The user ID being used must have permission to access the resources     it is attempting to access.

### Accessing Liferay's WSDL

After configuring the security settings properly, your first step in obtaining access to remote web services is to access the WSDL. If you are on a browser on the same machine Liferay is running on, you can do this by accessing the following URL:

http://localhost:<port number\>/tunnel-web/axis

If, for example, you are running on Tomcat on port 8080, you would specify this URL:

http://localhost:8080/tunnel-web/axis

If you are accessing a web service that was created as part of a portlet plugin, the URL is similar, but uses the context of your application rather than the tunnel-web servlet. You can get a list of your Service Builder-generated WSDL documents by using the URL pattern below:

http://localhost:8080/your-portlet/axis

If you are on a different machine from the Liferay server, you will need to pass in your user credentials on the URL to access the WSDL:

http://<user ID\>:<password\>@<server name\>:<port number\>/tunnel-web/axis

In any case, once you successfully browse to this URL, you will see the list of web services.

WSDL for each service is available by clicking on the *WSDL* link next to the name of the service. There are many services; one for each of the services available from the Liferay API.

Once you click on one of the *WSDL* links, the Web Service Definition Language document will be displayed. This document can be used to generate client code in any language that supports it. You can either save the document to your local machine and then generate the client code that way, or use your tool to trigger Liferay to generate the document dynamically by using one of the URLs above.

For further information about developing applications that take advantage of Liferay's remote services, please see *Liferay in Action*.

## Summary

We've seen how good a fit Liferay Portal is for the enterprise. It can be scaled linearly to grow to whatever size you need to serve your users. Clustering is a snap, and Liferay harmonizes very well with whatever environment you may have.

Kaleo workflow is a simple, yet robust workflow solution for your enterprise. With deep integration with Liferay's portlet applications and permissions system, it is an ideal choice for implementing your business processes.

Liferay Portal is also built for performance. You can tune it to support over 3300 concurrent users on a single server with mean log in times under half a second and maximum throughput of more than 79 log ins per second. We've seen some tips for tuning Liferay Portal, and we have to keep in mind the adage about tuning: load test and profile, tune, repeat.

You can also take advantage of read-writer database configurations, as well as database sharding. In all, Liferay Portal gives you all the options you need to build a high-performance, robust environment that supports your enterprise.
