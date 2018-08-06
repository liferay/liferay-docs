# Installing Solr: Basic Installation

There are two ways to install the Liferay Connector to Solr 7:

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
