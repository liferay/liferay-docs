# Using Solr 

Solr is a popular enterprise search platform build on Apache Lucene. It's
popular for its reliability, scalability, and fault tolerance. Read more about
it [here](http://lucene.apache.org/solr/).

Although [Elasticsearch](/discover/deployment/-/knowledge_base/7-0/configuring-elasticsearch)
is the default search engine that ships with @product@, it's perfectly valid to
use Solr instead. In particular, if you've already been using Solr with a
previous version of Liferay, or your deployment system (for example, your OS or
JVM) [isn't supported by Elasticsearch](https://www.elastic.co/support/matrix),
you might choose to use Solr to search and index your Liferay data.

Liferay's support for Solr is compatible with Solr versions 5.2.x through 5.5.x.
To make Liferay and Solr talk to each other, you'll need to install the Liferay
Solr adapter. There are two ways to do this:

1. Navigate to the [Liferay Marketplace](https://web.liferay.com/marketplace/)
   website and download the LPKG file for Liferay (CE) Solr 5 Search
   Engine. Once you do, copy the LPKG to your `Liferay_Home/osgi/marketplace`
   folder.

2. In your running Liferay instance, navigate to *Control Panel* &rarr; *Apps*
   &rarr; *Store*. Sign in using your credentials, search for Solr Search
   Engine, and purchase (it's free) the Liferay (CE) Solr 5 Search
   Engine entry.

This guide leads you through the process of installing and configuring Solr.
As you proceed, these terms will be useful to keep in mind:

*Solr Home*: The center of the Solr system (pun intended). This directory is
`solr-[version]/server/solr`.

*Liferay Home*: The root folder of your Liferay installation. It contains
the `osgi`, `deploy`, `data`, and `license` folders, among others.

Before configuring Liferay for Solr, you need to install and set up Solr.

## Installing and Configuring Solr 5 [](id=installing-and-configuring-solr-5)

To install and properly configure Solr for Liferay:

1. Download [Solr](http://archive.apache.org/dist/lucene/solr/5.2.1/solr-5.2.1.zip) and unzip it.

2. Navigate to `solr-[version]/server/solr`. This is Solr Home.

3. Create a new folder called `liferay`.

4. In the `liferay` folder, create two new folders: `conf` and `data`.

5. Copy the contents of `Solr_Home/configsets/data_driven_schema_configs/conf`
   to `Solr_Home/liferay/conf`.

6. Open the Liferay Solr Adapter's LPKG file with an archive manager.

    Open the `com.liferay.portal.search.solr.jar` file, and extract 

        META-INF/resources/solrconfig.xml

    and

        META-INF/resources/schema.xml

    to

        Solr_Home/liferay/conf

    This replaces the current `solrconfig.xml` and `schema.xml` files with ones
    that tell Solr how to index data coming from Liferay.

7. Create a `core.properties` file in `Solr_Home/liferay`, and add these
   contents:

        config=solrconfig.xml
        dataDir=data
        name=liferay
        schema=schema.xml

8. Checkpoint: your `Solr_Home/liferay` folder should now have this structure:

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

8. Start the Solr server by entering

        ./bin/solr start -f

    from the top-level folder of your Solr installation (`solr-[version]`).

9. The Solr server listens on port `8983` by default. Navigate to
   `http://localhost:8983/solr/#/~cores` (assuming you're testing locally with
   `localhost` as your host), and confirm that the `liferay` core is available.

Solr is now installed. Next install and configure Liferay's Solr adapter.

## Installing and Configuring the Liferay Solr Adapter [](id=installing-and-configuring-the-liferay-solr-adapter)

Since Elasticsearch is the default search engine in Liferay, the Elasticsearch
adapter is already installed and running. Stop it before configuring the Solr
adapter.

First, open a Gogo shell and enter

    lb elasticsearch

You'll see an active bundle named Liferay Portal Search Elasticsearch (version)
listed in the Gogo shell.

    ID |State      |Level|Name
    239|Active     |   10|Liferay Portal Search Elasticsearch (2.0.4)

Stop the Elasticsearch adapter by entering 

    stop [bundle ID]

In the case above, the `[bundle ID]` is `239`. Now you can install and configure
the Solr adapter:

1. Start Liferay, then deploy the Solr adapter by copying the LPKG you
   downloaded to `Liferay_Home/deploy`.

    You'll see a `STARTED` message in your Liferay log once the solr adapter is
    installed. Here's what the log message looks like:

        08:48:24,165 INFO [localhost-startStop-1][BundleStartStopLogger:35] STARTED
        com.liferay.portal.search.solr_2.0.3 [47]

2. To reindex against Solr, navigate to *Control Panel* &rarr; *Configuration*
   &rarr; *Server Administration*, and click *Execute* next to the *Reindex all
   search indexes* option.

   ![Figure 1: Once the Solr adapter is installed, you can reindex your Liferay
   data against your Solr server.](../../../images/solr-reindex.png)

In production deployments, specify your edits to the Solr adapter's default
configurations using a `.cfg` file deployed to the `Liferay_Home/osgi/configs`
folder. During testing and development, the System Settings app in Liferay's
Control Panel &rarr; Configuration section is convenient for editing the default
configurations.

![Figure 2: You can configure Solr from Liferay's System Settings application.
This is most useful during development and testing.](../../../images/solr-system-settings.png)

## High Availability with SolrCloud [](id=high-availability-with-solrcloud)

You can use SolrCloud if you need a cluster of Solr servers featuring fault
tolerance and high availability. Note that to use SolrCloud in production, you
should set up an [external ZooKeeper
ensemble](https://cwiki.apache.org/confluence/display/solr/Setting+Up+an+External+ZooKeeper+Ensemble).
[ZooKeeper](http://zookeeper.apache.org/) is a centralized
coordination service for managing distributed systems, such as your SolrCloud
cluster.

The steps included here should be considered the bare minimum of what must be
done to configure SolrCloud with Liferay. For example, these instructions cover
configuring SolrCloud on a single machine, whereas a production environment
would feature multiple physical or virtual machines. These instructions also
assume you've followed the earlier section on *Installing and Configuring Solr
5*. Refer to the [SolrCloud guide for more information](https://cwiki.apache.org/confluence/display/solr/SolrCloud).

1. Stop the Solr server if it's running.

2. Navigate to the `Solr_Home/configsets` folder and create a folder called 

        liferay_configs

3. Copy the `conf` folder from `Solr_Home/liferay` to the `liferay_configs`
   folder you just created.

    The `configset/liferay_configs` folder is used to configure the SolrCloud
    liferay collection, and is uploaded to ZooKeeper. By copying the `conf`
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

    Solr process 12755 running on port 7574
    {
      "solr_home":"/home/russell/Documents/docs-projects/solr-docs/solr-5.2.1/example/cloud/node2/solr/",
      "version":"5.2.1 1684708 - shalin - 2015-06-10 23:20:13",
      "startTime":"2016-08-19T18:11:27.087Z",
      "uptime":"0 days, 0 hours, 13 minutes, 2 seconds",
      "memory":"50 MB (%10.2) of 490.7 MB",
      "cloud":{
        "ZooKeeper":"localhost:9983",
        "liveNodes":"2",
        "collections":"1"}}


    Solr process 12564 running on port 8983
    {
      "solr_home":"/home/russell/Documents/docs-projects/solr-docs/solr-5.2.1/example/cloud/node1/solr/",
      "version":"5.2.1 1684708 - shalin - 2015-06-10 23:20:13",
      "startTime":"2016-08-19T18:11:21.637Z",
      "uptime":"0 days, 0 hours, 13 minutes, 8 seconds",
      "memory":"44.9 MB (%9.2) of 490.7 MB",
      "cloud":{
        "ZooKeeper":"localhost:9983",
        "liveNodes":"2",
        "collections":"1"}}

To stop Solr while running in SolrCloud mode, use the *stop* command, like this:

    bin/solr stop -all

## Configure the Solr Adapter for SolrCloud [](id=configure-the-solr-adapter-for-solrcloud)

There's only one thing left to do: specify the client type as *CLOUD* in
Liferay's Solr adapter.

1. From System Settings or your OSGi `.cfg` file, set the *Client Type*
   to *CLOUD*.

        clientType=CLOUD

2. Start Liferay if it's not running already.

Now you're able to configure Liferay for Solr, and Solr for Liferay. Remember
that Elasticsearch is the default search engine for Liferay, so if you're not
constrained to use Solr or already a Solr expert, consider Elasticsearch for you
search engine requirements. If you do use Solr, then you can tell all your
colleagues that your Liferay installation's search capability is Solr powered
(pun intended).
