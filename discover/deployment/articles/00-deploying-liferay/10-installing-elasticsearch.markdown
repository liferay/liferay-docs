# Installing Elasticsearch [](id=installing-elasticsearch)

@product@ uses Elasticsearch to index its content. By default, it's installed as
an embedded service. It works, but it's not a supported configuration for
a production server. Feel free to use it while testing or developing, but when
you're ready to put your site in production, you must run Elasticsearch as
a standalone process. This is better anyway, because it frees you to design your
infrastructure the way you want it. If you've got hardware or a VM to spare, you
can separate your search infrastructure from @product@ and reap some performance
gains by putting search on a separate box. If you're more budget-conscious, you
can still increase performance by running Elasticsearch in a separate,
individually tunable JVM on the same box. 

Installing Elasticsearch is pretty easy and takes only six steps: 

1. Find the version of Elasticsearch that's embedded in the version of @product@
   you have, and then download that version from [Elastic's](https://www.elastic.co) 
   website. 

2. Install Elasticsearch by extracting its archive to the system where you want
   it to run. 

3. Install some required Elasticsearch plugins.

4. Name your Elasticsearch cluster. 

5. Configure @product@ to connect to your Elasticsearch cluster. 

6. Restart @product@ and reindex your search indexes. 

+$$$

**Note:** Before continuing, make sure you have set the [`JAVA_HOME` environment variable](https://docs.oracle.com/cd/E19182-01/820-7851/inst_cli_jdk_javahome_t/)

If you have multiple JDKs installed, make sure Elasticsearch and @product@ are
using the same version. You can specify this in `[Elasticsearch
Home]/bin/elasticsearch.in.sh`:

        JAVA_HOME=/path/to/java

$$$

Now you'll perform these steps, and when you're done, you'll have
a production-ready instance of @product@ up and running. After you're done
following the installation guide, refer to the 
[Configuring Elasticsearch](/discover/deployment/-/knowledge_base/7-1/configuring-elasticsearch-for-liferay-0)
article for more details on configuring @product@ for Elasticsearch. For more
information on installing a search engine, see
[here](/discover/deployment/-/knowledge_base/7-1/installing-a-search-engine).

### Step One: Find the Right Version of Elasticsearch [](id=step-one-find-the-right-version-of-elasticsearch)

If @product@ isn't running, start it. 

Visit port 9200 on localhost to access the embedded Elasticsearch: 

    http://localhost:9200

A JSON document is returned that looks similar to this: 

    {
      "name" : "g0m223N",
      "cluster_name" : "LiferayElasticsearchCluster",
      "cluster_uuid" : "Ii6STs04Tg-XzTVV5h7M2Q",
      "version" : {
        "number" : "6.1.3",
        "build_hash" : "af51318",
        "build_date" : "2018-01-26T18:22:55.523Z",
        "build_snapshot" : false,
        "lucene_version" : "7.1.0",
        "minimum_wire_compatibility_version" : "5.6.0",
        "minimum_index_compatibility_version" : "5.0.0"
      },
      "tagline" : "You Know, for Search"
    }

The version of Elasticsearch that's running is the value of the `"number"` field.
In this example, it's 6.1.3. 

Now that you know the version of Elasticsearch you need, go to
[Elastic's](https://www.elastic.co) website and download that version. 

### Step Two: Install Elasticsearch [](id=step-two-install-elasticsearch)

Most of this step entails deciding where you want to run Elasticsearch. Do you
want to run it on the same machine as @product@, or do you want to run it on its
own hardware? The answer to this question comes down to a combination of the
resources you have available and the size of your installation. Regardless of
what you decide, either way you get the benefit of a separately tunable search
infrastructure. 

Once you have a copy of the right version of Elasticsearch, extract it to
a folder on the machine where you want it running. That's it!. 

### Step Three: Install Elasticsearch Plugins [](id=step-three-install-elasticsearch-plugins)

Install the following required Elasticsearch plugins:

-  `analysis-icu`
-  `analysis-kuromoji`
-  `analysis-smartcn`
-  `analysis-stempel`

To install these plugins, navigate to Elasticsearch Home and enter

    ./bin/elasticsearch-plugin install [plugin-name]

Replace *[plugin-name]* with the Elasticsearch plugin's name.

### Step Four: Name Your Elasticsearch Cluster [](id=step-three-name-your-elastic-cluster)

A *cluster* in Elasticsearch is a collection of nodes (servers) identified as a
cluster by a shared cluster name. The nodes work together to share data and
workload. A one node cluster is discussed here; to create a multi-node cluster,
please refer to [Elastic's documentation](https://www.elastic.co/guide/index.html). 

Now that you've installed Elastic, it sits in a folder on your machine, which is
referred to here as `[Elasticsearch Home]`. To name your cluster, you'll define
the cluster name in both Elasticsearch and in @product@. First, define it in
Elasticsearch. Edit the following file: 

    [Elasticsearch Home]/config/elasticsearch.yml

Uncomment the line that begins with `cluster.name`. Set the cluster name to
whatever you want to name your cluster: 

    cluster.name: LiferayElasticsearchCluster

Of course, this isn't a very imaginative name; you may choose to name your
cluster `finders_keepers` or something else you can remember more easily. Save
the file. 

Now you can start Elasticsearch. Run the executable for your operating system
from the `[Elasticsearch Home]/bin` folder: 

    ./elasticsearch

Elasticsearch starts, and one of its status messages includes a transport address: 

    [2018-04-03T15:34:19,784][INFO ][o.e.t.TransportService   ] [g0m223N] publish_address {127.0.0.1:9300}, bound_addresses {[::1]:9300}, {127.0.0.1:9300}

Take note of this address; you'll need to give it to your @product@ server so it
can find Elasticsearch on the network. 

### Step Five: Configure @product@ to Connect to your Elasticsearch Cluster [](id=step-four-configure-liferay-to-connect-to-your-elastic-cluster)

Now that you're ready to configure @product@, start it if you haven't already,
log in, and then click on *Control Panel* &rarr; *Configuration* &rarr; *System
Settings* &rarr; *Search*. Enter the term *elasticsearch* in the search bar and
click the *Elasticsearch 6* entry from the list of settings. Now you can
configure it. Here are the configuration options to change: 

**Cluster Name:** Enter the name of the cluster as you defined it in
Elasticsearch. 

**Operation Mode:** Defaults to EMBEDDED. Change it to REMOTE to connect to a
standalone Elasticsearch. 

**Transport Addresses:** Enter a delimited list of transport addresses for
Elasticsearch nodes. Here, you'll enter the transport address from the
Elasticsearch server you started. The default value is `localhost:9300`, which
will work. 

When finished, click *Save*. You're almost done. 

### Step Six: Restart @product@ and Reindex [](id=step-five-restart-liferay-and-reindex)

Stop and restart @product@. When it's back up, log in as an administrative user
and click on *Control Panel* &rarr; *Configuration* &rarr; *Search* and
click the *Execute* button for *Reindex all search indexes*. When you do that,
you should see some messages scroll up in the Elasticsearch log. 

For more details refer to the [Elasticsearch installation guide](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/_installation.html).

