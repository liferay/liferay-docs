# Installing Solr

Solr is a popular enterprise search platform build on Apache Lucene. It's
popular for its reliability, scalability, and fault tolerance. Read more about
it [here](http://lucene.apache.org/solr/).

Although 
[Elasticsearch](/discover/deployment/-/knowledge_base/7-1/configuring-elasticsearch-for-liferay-0)
is the default search engine that ships with @product@, it's perfectly valid to
use Solr instead. In particular, if you've already been using Solr with a
previous version of @product@, or your deployment system (for example, your OS or
JVM) [isn't supported by Elasticsearch](https://www.elastic.co/support/matrix),
you might choose to use Solr to search and index your @product@ data.

There are circumstances that force you to use Elasticsearch instead of Solr.
Read
[here](/discover/deployment/-/knowledge_base/7-1/installing-a-search-engine#choosing-a-search-engine)
for more information.

@product-ver supports Solr 7.x through the Liferay Connector to Solr.  There are
two ways to install the connector:

1. Navigate to the 
   [Liferay Marketplace](https://web.liferay.com/marketplace/)
   website and download the LPKG file for Liferay Connector to Solr 7. Once you
   do, copy the LPKG to your `Liferay_Home/osgi/marketplace` folder.

2. In your running portal instance, navigate to *Control Panel* &rarr; *Apps*
   &rarr; *Store*. Sign in using your credentials, search for Solr Search
   Engine, and purchase (it's free) the Liferay Connector to Solr 7 entry.

This guide leads you through installing and configuring Solr. As you proceed,
these terms will be useful to keep in mind:

*Solr Home*: The center of the Solr system (pun intended). This directory is
`solr-[version]/server/solr`.

*Liferay Home*: The root folder of your @product@ installation. It contains
the `osgi`, `deploy`, `data`, and `license` folders, among others.

# Installing Solr: Basic Steps

This article covers two basic installation steps:

1.  Installing and configuring Solr 7

2.  Installing and configuring the Solr 7 connector for @product@.

Before configuring @product@ for Solr, install and set up Solr.

## Installing and Configuring Solr 7 [](id=installing-and-configuring-solr-5)

To install and properly configure Solr for @product@:

1. Download 
    [Solr](http://www.apache.org/dyn/closer.lua/lucene/solr/7.4.0) 
    and unzip it.

2. Navigate to `solr-[version]/server/solr`. This is Solr Home.

3. Create a new folder called `liferay`.

4. In the `liferay` folder, create two new folders: `conf` and `data`.

5. Copy the contents of `Solr_Home/configsets/_default/conf`
   to `Solr_Home/liferay/conf`.

6. Open the Liferay Connector to Solr 7's LPKG file with an archive manager.

    Open the `com.liferay.portal.search.solr7.jar` file, and extract 

        META-INF/resources/solrconfig.xml

    and

        META-INF/resources/schema.xml

    to

        Solr_Home/liferay/conf
<!-- Might be best to just spell this dir out. It's not used so often that we
need this shorthand-->

    This replaces the current `solrconfig.xml` and `schema.xml` files with ones
    that tell Solr how to index data coming from @product@.

7. Create a `core.properties` file in `Solr_Home/liferay`, and add these
   contents:

        config=solrconfig.xml
        dataDir=data
        name=liferay
        schema=schema.xml

8. Checkpoint: your `Solr_Home/liferay` folder now has this structure:

        liferay
        ├── conf
        │   ├── currency.xml
        │   ├── elevate.xml
        │   ├── lang
        │   ├── managed-schema
        │   ├── params.json
        │   ├── protwords.txt
        │   ├── schema.xml
        │   ├── solrconfig.xml
        │   ├── stopwords.txt
        │   └── synonyms.txt
        ├── core.properties
        └── data

Solr 7 below (remove above when confirmed correct)

        liferay/
        ├── conf
        │   ├── lang
        │   │   ├── contractions_ca.txt
        │   │   └──  ...
        │   ├── managed-schema
        │   ├── params.json
        │   ├── protwords.txt
        │   ├── schema.xml
        │   ├── solrconfig.xml
        │   ├── stopwords.txt
        │   └── synonyms.txt
        ├── core.properties
        └── data

8. Start the Solr server by entering

        ./bin/solr start -f

    from the top-level folder of your Solr installation (`solr-[version]`).

9. The Solr server listens on port `8983` by default. Navigate to
   `http://localhost:8983/solr/#/~cores` (assuming you're testing locally with
   `localhost` as your host), and confirm that the `liferay` core is available.

Solr is now installed. Next install and configure the Solr connector.

## Installing and Configuring the Liferay Solr Adapter [](id=installing-and-configuring-the-liferay-solr-adapter)

Since Elasticsearch is the default search engine in @product@, the Elasticsearch
connector is already installed and running. Stop it before configuring the Solr
connector.

Stop the Elasticsearch connector bundle using the App Manager, the Felix Gogo
shell, or the bundle blacklist. If you're a Digital Enterprise customer, use the
blacklist feature as described below. The App Manager and Gogo shell rely on the
`osgi/state` folder to "remember" the state of the bundle. If you delete this
folder (recommended during patching) the Elasticsearch connector will be
reinstalled and started automatically. 

Navigate to Control Panel &rarr; Apps &rarr; App Manager.

Once in the App Manager, search for *elasticsearch*. Find the Liferay CE
Connector to Elasticsearch 6 module and click the edit
((![Edit](../../images/icon-edit.png))) button. Choose the Deactivate option.
This leaves the bundle installed, but stops it in the OSGi runtime.

Alternatively, use the 
[Felix Gogo shell](/developer/tutorials/-/knowledge_base/7-1/using-the-felix-gogo-shell) 
to stop the Elasticsearch connector. Enter

    lb elasticsearch

You'll see two active bundles for the Liferay CE Connector to Elasticsearch 6:
an API and an IMPL bundle. 

    ID|State      |Level|Name
    476|Active     |   10|Liferay CE Connector to Elasticsearch 6 - API (2.0.0)
    478|Active     |   10|Liferay Portal Search Elasticsearch 6 API (2.0.6)
    706|Active     |   10|Liferay CE Connector to Elasticsearch 6 - Impl (2.0.0)
    707|Active     |   10|Liferay Portal Search Elasticsearch 6 Implementation (2.0.5)

Stop the API bundle by entering 

    stop [bundle ID]

In the example above, the `[bundle ID]` is `476`. 

+$$$

**Liferay Digital Enterprise:** Digital Enterprise customers should 
[blacklist](/discover/portal/-/knowledge_base/7-1/blacklisting-osgi-modules) 
the Elasticsearch, Shield, and Marvel plugins. 

1.  Create a 

        com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration.config

    file with these contents:

        blacklistBundleSymbolicNames=["com.liferay.portal.search.elasticsearch6.api","com.liferay.portal.search.elasticsearch6.impl","Liferay Connector to X-Pack Monitoring [Elastic Stack 6.x]","Liferay Connector to X-Pack Security [Elastic Stack 6.x]"]

    If the X-Pack LPKG files are installed, you must blacklist these too.

2.  Place the file in `Liferay Home/osgi/configs`.

$$$

Install and configure the Solr connector:

1. Start @product@, then deploy the Solr connector by copying the LPKG you
   downloaded to `Liferay_Home/deploy`.

    You'll see a `STARTED` message in your @product@ log once the solr connector is
    installed. Here's what the log message looks like:

        2018-08-06 17:31:26.152 INFO  [fileinstall-/home/russell/liferay-bundles/solr-7-dxp/master/osgi/portal][BundleStartStopLogger:35] 
        STARTED com.liferay.portal.search.solr7_1.0.0 [866]

2. To reindex against Solr, navigate to *Control Panel* &rarr; *Configuration*
   &rarr; *Server Administration*, and click *Execute* next to the *Reindex all
   search indexes* option.

   ![Figure 1: Once the Solr connector is installed, you can reindex your @product@
   data against your Solr server.](../../../images/solr-reindex.png)

In production deployments, specify your edits to the Solr connector's default
configurations using a configuration file deployed to the `Liferay_Home/osgi/configs`
folder. Name the file 

    com.liferay.portal.search.solr7.configuration.SolrConfiguration.config

During testing and development, use the SOLR 7 System Settings entry Control
Panel &rarr; Configuration &rarr; System Settings for editing the default
configurations.

![Figure 2: You can configure Solr from @product@'s System Settings application.
This is most useful during development and testing.](../../../images/solr-system-settings.png)

# Installing Solr: High Availability with SolrCloud [](id=high-availability-with-solrcloud)

Use SolrCloud if you need a cluster of Solr servers featuring fault
tolerance and high availability. Note that to use SolrCloud in production, you
should set up an [external ZooKeeper
ensemble](https://cwiki.apache.org/confluence/display/solr/Setting+Up+an+External+ZooKeeper+Ensemble).
[ZooKeeper](http://zookeeper.apache.org/) is a centralized
coordination service for managing distributed systems, such as your SolrCloud
cluster.

The steps included here should be considered the bare minimum of what must be
done to configure SolrCloud with @product@. For example, these instructions cover
configuring SolrCloud on a single machine, whereas a production environment
would feature multiple physical or virtual machines. These instructions also
assume you've followed the earlier section on *Installing and Configuring Solr
7*. Refer to the [SolrCloud guide for more information](https://cwiki.apache.org/confluence/display/solr/SolrCloud).

1. Stop the Solr server if it's running.

2. Navigate to the `Solr_Home/configsets` folder and create a folder called 

        liferay_configs

3. Copy the `conf` folder from `Solr_Home/liferay` to the `liferay_configs`
   folder you just created.

    The `configset/liferay_configs` folder is used to configure the SolrCloud
    @product@ collection, and is uploaded to ZooKeeper. By copying the `conf`
    folder from the `liferay` server configured earlier, you're using the
    `schema.xml` and `solrconfig.xml` files provided with the Liferay Solr
    Adapter.

4. Next launch an interactive SolrCloud session to configure your SolrCloud
   cluster. Use this command:

        ./bin/solr -e cloud

5. Complete the setup wizard. These steps demonstrate creating a two-node
   cluster:

    -  Enter `2` for the number of nodes.
    -  Specify ports `8983` and `7574` (the defaults). Both nodes are
        started with the start commands printed in the log:

                Starting up SolrCloud node1 on port 18983 using command:

                solr start -cloud -s example/cloud/node1/solr -p [port#]  -m 512m

    -  Name the collection *liferay*.
    -  Split the collection into two shards.
    -  Specify two replicas per shard.
    -  When prompted to choose a configuration, enter *liferay_configs*. You
        should see a log message that concludes like this when the cluster has
        been started:

                SolrCloud example running, please visit http://localhost:8983/solr

Now you have a new collection called *liferay* in your local SolrCloud cluster.
Verify its status by running the *status* command:

    ./bin/solr status

You'll see log output like this:

    Found 2 Solr nodes: 

    Solr process 16989 running on port 8983
    INFO  - 2018-08-06 13:54:17.665; org.apache.solr.util.configuration.SSLCredentialProviderFactory; Processing SSL Credential Provider chain: env;sysprop
    {
      "solr_home":"/home/russell/liferay-bundles/solr-7-dxp/solr-7.4.0/example/cloud/node1/solr",
      "version":"7.4.0 9060ac689c270b02143f375de0348b7f626adebc - jpountz - 2018-06-18 16:55:13",
      "startTime":"2018-08-06T17:52:01.519Z",
      "uptime":"0 days, 0 hours, 2 minutes, 16 seconds",
      "memory":"68.5 MB (%14) of 490.7 MB",
      "cloud":{
        "ZooKeeper":"localhost:9983",
        "liveNodes":"2",
        "collections":"1"}}


    Solr process 17127 running on port 7574
    INFO  - 2018-08-06 13:54:18.507; org.apache.solr.util.configuration.SSLCredentialProviderFactory; Processing SSL Credential Provider chain: env;sysprop
    {
      "solr_home":"/home/russell/liferay-bundles/solr-7-dxp/solr-7.4.0/example/cloud/node2/solr",
      "version":"7.4.0 9060ac689c270b02143f375de0348b7f626adebc - jpountz - 2018-06-18 16:55:13",
      "startTime":"2018-08-06T17:52:11.987Z",
      "uptime":"0 days, 0 hours, 2 minutes, 6 seconds",
      "memory":"56.4 MB (%11.5) of 490.7 MB",
      "cloud":{
        "ZooKeeper":"localhost:9983",
        "liveNodes":"2",
        "collections":"1"}}

To stop Solr while running in SolrCloud mode, use the *stop* command, like this:

    bin/solr stop -all

## Configure the Solr Adapter for SolrCloud [](id=configure-the-solr-adapter-for-solrcloud)

There's only one thing left to do: specify the client type as *CLOUD* in
Liferay's Solr connector.

1. From System Settings or your OSGi configuration file, set the *Client Type*
   to *CLOUD*.

        clientType="CLOUD"

2. Start @product@ if it's not running already.

<!-- +$$$

**Note:** For a complete list of settings available in the Solr connector, see the
[Solr Settings reference
article](/discover/reference/-/knowledge_base/7-1/solr-settings).

$$$ -->

Now you're able to configure @product@ for Solr, and Solr for @product@.
Remember that Elasticsearch is the default search engine for @product@, so if
you're not constrained to use Solr or already a Solr expert, consider
Elasticsearch for you search engine requirements. If you do use Solr, tell all
your colleagues that your @product@ installation's search capability is Solr
powered (pun intended).
