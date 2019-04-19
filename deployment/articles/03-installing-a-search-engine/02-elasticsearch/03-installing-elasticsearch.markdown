---
header-id: installing-elasticsearch
---

# Installing Elasticsearch

[TOC levels=1-4]

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

Before installing Elasticsearch, refer to 
[Preparing to Install Elasticsearch](/docs/7-2/deploy/-/knowledge_base/deploy/preparing-to-install-elasticsearch) 
for guidance on configuring the servers to support an Elasticsearch deployment
properly. 

Installing Elasticsearch takes six steps: 

1. Find the version of Elasticsearch that's embedded in the version of @product@
   you have, and then download that version from [Elastic's](https://www.elastic.co) 
   website. 

2. Install Elasticsearch by extracting its archive to the system where you want
   it to run. 

3. Install some required Elasticsearch plugins.

4. Name your Elasticsearch cluster. 

5. Configure @product@ to connect to your Elasticsearch cluster. 

6. Restart @product@ and reindex your search indexes. 

Before continuing, make sure you have set the 
[`JAVA_HOME` environment variable](https://docs.oracle.com/cd/E19182-01/820-7851/inst_cli_jdk_javahome_t/).

If you have multiple JDKs installed, make sure Elasticsearch and @product@ are
using the same version and distribution (e.g., Oracle Open JDK 1.8.0_201). You
can specify this in `[Elasticsearch Home]/bin/elasticsearch.in.sh`:

        JAVA_HOME=/path/to/java

<!-- UNCOMMENT WHEN WE HAVE A COMPAT MATRIX 
Consult the 
[Elasticsearch compatibility matrix](https://www.elastic.co/support/matrix#matrix_jvm) 
and the 
[@product@ compatibility matrix](https://web.liferay.com/documents/14/21598941/Liferay+DXP+7.1+Compatibility+Matrix/9f9c917a-c620-427b-865d-5c4b4a00be85) 
to learn more about supported JDK distributions and versions.
-->

Once you perform these steps, you'll have a production-ready instance of
@product@ up and running.

After you're done following the installation guide, refer to the 
[Configuring Elasticsearch](/docs/7-2/deploy/-/knowledge_base/deploy/configuring-elasticsearch-for-liferay-0)
article for more details on configuring @product@ for Elasticsearch. For more
information on installing a search engine, see
[here](/docs/7-2/deploy/-/knowledge_base/deploy/installing-a-search-engine).

### Step One: Find the Right Version of Elasticsearch 

If @product@ isn't running, start it. 

Visit port 9200 on localhost to access the embedded Elasticsearch: 

    http://localhost:9200

A JSON document is returned that looks similar to this: 

```json
{
  "name" : "01BT8H4",
  "cluster_name" : "LiferayElasticsearchCluster",
  "cluster_uuid" : "ziPGEBeSToGHc7lVqaYHnA",
  "version" : {
    "number" : "6.5.0",
    "build_flavor" : "unknown",
    "build_type" : "unknown",
    "build_hash" : "816e6f6",
    "build_date" : "2018-11-09T18:58:36.352602Z",
    "build_snapshot" : false,
    "lucene_version" : "7.5.0",
    "minimum_wire_compatibility_version" : "5.6.0",
    "minimum_index_compatibility_version" : "5.0.0"
  },
  "tagline" : "You Know, for Search"
}
```

The version of Elasticsearch that's running is the value of the `"number"` field.
In this example, it's 6.5.0. 

Shut down the @product@ server. In a local, single-machine testing environment,
if you continue without shutting down, the Elasticsearch server you're about to
install and start throws errors in the log if its cluster name and HTTP port
match the already-running embedded Elasticsearch server. An alternative to
shutting down @product@ is to use a different cluster name (i.e., not
`LiferayElasticsearchCluster`) and HTTP port (i.e., not `9200`) in the remote
Elasticsearch server.

Now that you know the version of Elasticsearch you need, go to
[Elastic's](https://www.elastic.co) website and download that version. 

### Step Two: Install Elasticsearch 

Most of this step entails deciding where you want to run Elasticsearch. Do you
want to run it on the same machine as @product@, or do you want to run it on its
own hardware? The answer to this question comes down to a combination of the
resources you have available and the size of your installation. Regardless of
what you decide, either way you get the benefit of a separately tunable search
infrastructure. 

Once you have a copy of the right version of Elasticsearch, extract it to
a folder on the machine where you want it running. That's it! 

### Step Three: Install Elasticsearch Plugins 

Install the following required Elasticsearch plugins:

-  `analysis-icu`
-  `analysis-kuromoji`
-  `analysis-smartcn`
-  `analysis-stempel`

To install these plugins, navigate to Elasticsearch Home and enter

    ./bin/elasticsearch-plugin install [plugin-name]

Replace *[plugin-name]* with the Elasticsearch plugin's name.

### Step Four: Name Your Elasticsearch Cluster 

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

    [2019-04-01T16:55:50,127][INFO ][o.e.t.TransportService   ] [HfkqdKv] publish_address {127.0.0.1:9300}, bound_addresses {[::1]:9300}, {127.0.0.1:9300}

Take note of this address; you'll need to give it to your @product@ server so it
can find Elasticsearch on the network. 

### Step Five: Configure @product@ to Connect to your Elasticsearch Cluster 

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

### Step Six: Restart @product@ and Reindex 

Stop and restart @product@. When it's back up, log in as an administrative user
and click on *Control Panel* &rarr; *Configuration* &rarr; *Search* and
click the *Execute* button for *Reindex all search indexes*. When you do that,
you should see some messages scroll up in the Elasticsearch log. 

When restarting, `update_mappings` messages will appear in the Elasticsearch
logs:

    [2019-04-01T17:08:57,462][INFO ][o.e.c.m.MetaDataMappingService] [HfkqdKv] [liferay-0/m27eNsekTAyP27zDOjGojw] update_mapping [LiferayDocumentType]
    [2019-04-01T17:08:57,474][INFO ][o.e.c.m.MetaDataMappingService] [HfkqdKv] [liferay-0/m27eNsekTAyP27zDOjGojw] update_mapping [LiferayDocumentType]
    [2019-04-01T17:08:58,393][INFO ][o.e.c.m.MetaDataMappingService] [HfkqdKv] [liferay-0/m27eNsekTAyP27zDOjGojw] update_mapping [LiferayDocumentType]
    [2019-04-01T17:08:58,597][INFO ][o.e.c.m.MetaDataMappingService] [HfkqdKv] [liferay-0/m27eNsekTAyP27zDOjGojw] update_mapping [LiferayDocumentType]
    [2019-04-01T17:09:07,040][INFO ][o.e.c.m.MetaDataMappingService] [HfkqdKv] [liferay-20101/OJidpYkgR5OcCD5dgWB8Aw] update_mapping [LiferayDocumentType]

Once you reindex, more log messages appear in Elasticsearch:

    [2019-04-01T17:11:17,338][INFO ][o.e.c.m.MetaDataDeleteIndexService] [HfkqdKv] [liferay-20101/OJidpYkgR5OcCD5dgWB8Aw] deleting index
    [2019-04-01T17:11:17,389][INFO ][o.e.c.m.MetaDataCreateIndexService] [HfkqdKv] [liferay-20101] creating index, cause [api], templates [], shards [1]/[0], mappings [LiferayDocumentType]
    [2019-04-01T17:11:17,471][INFO ][o.e.c.r.a.AllocationService] [HfkqdKv] Cluster health status changed from [YELLOW] to [GREEN] (reason: [shards started [[liferay-20101][0]] ...]).
    [2019-04-01T17:11:17,520][INFO ][o.e.c.m.MetaDataMappingService] [HfkqdKv] [liferay-20101/Meacn_uxR06g0tCJonS4eA] update_mapping [LiferayDocumentType]
    [2019-04-01T17:11:19,047][INFO ][o.e.c.m.MetaDataMappingService] [HfkqdKv] [liferay-20101/Meacn_uxR06g0tCJonS4eA] update_mapping [LiferayDocumentType]
    [2019-04-01T17:11:19,133][INFO ][o.e.c.m.MetaDataMappingService] [HfkqdKv] [liferay-20101/Meacn_uxR06g0tCJonS4eA] update_mapping [LiferayDocumentType]
    [2019-04-01T17:11:19,204][INFO ][o.e.c.m.MetaDataMappingService] [HfkqdKv] [liferay-20101/Meacn_uxR06g0tCJonS4eA] update_mapping [LiferayDocumentType]
    [2019-04-01T17:11:19,249][INFO ][o.e.c.m.MetaDataMappingService] [HfkqdKv] [liferay-20101/Meacn_uxR06g0tCJonS4eA] update_mapping [LiferayDocumentType]
    [2019-04-01T17:11:21,215][INFO ][o.e.c.m.MetaDataMappingService] [HfkqdKv] [liferay-20101/Meacn_uxR06g0tCJonS4eA] update_mapping [LiferayDocumentType]
    [2019-04-01T17:11:21,262][INFO ][o.e.c.m.MetaDataMappingService] [HfkqdKv] [liferay-20101/Meacn_uxR06g0tCJonS4eA] update_mapping [LiferayDocumentType]
    [2019-04-01T17:11:21,268][INFO ][o.e.c.m.MetaDataMappingService] [HfkqdKv] [liferay-20101/Meacn_uxR06g0tCJonS4eA] update_mapping [LiferayDocumentType]
    [2019-04-01T17:11:21,275][INFO ][o.e.c.m.MetaDataMappingService] [HfkqdKv] [liferay-20101/Meacn_uxR06g0tCJonS4eA] update_mapping [LiferayDocumentType]
    [2019-04-01T17:11:21,282][INFO ][o.e.c.m.MetaDataMappingService] [HfkqdKv] [liferay-20101/Meacn_uxR06g0tCJonS4eA] update_mapping [LiferayDocumentType]
    [2019-04-01T17:11:21,373][INFO ][o.e.c.m.MetaDataMappingService] [HfkqdKv] [liferay-20101/Meacn_uxR06g0tCJonS4eA] update_mapping [LiferayDocumentType]

For additional confirmation that @product@ recognizes the remote search engine,
navigate to the Search Control Panel application and note the subtle change
there: the vendor name is now simply _Elasticsearch_, whereas prior to the
installation of the remote Elasticsearch server, it said _Elasticsearch
(Embedded)_.

![Figure 1: To see information about the currently connected search engine, go to _Control Panel &rarr; Configuration &rarr; Search_. ](../../../images/search-admin-engineinfo-remote.png)

For additional details refer to the [Elasticsearch installation guide](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/_installation.html).

