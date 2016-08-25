# Configuring Elasticsearch for Liferay [](id=configuring-elasticsearch-for-liferay-0)

Liferay is an open source project, so you won't be surprised to learn that the
default search engine that ships with Liferay is also an open source project.
Elasticsearch is a highly scalable, full-text search and analytics engine that
ships with @product@. 

By default, Liferay runs Elasticsearch as an embedded search engine, but you'll
want to configure it locally or remotely, as a separate server or cluster. This
guide walks you through that process.

If you'd rather use Solr, it's also supported. See [here](LINK) for information
on installing and configuring Solr.

If you just want to get up and running quickly with Elasticsearch, refer to the
[Configuring Search article](discover/deployment/-/knowledge_base/7-0/configuring-search). This
article gives more detail regarding the configuration of Elasticsearch for use
in @product@ production environments. It also assumes you're here to learn about
what's necessary for the installation and configuration of Elasticsearch, not
the basics of search engines in general, or the low level search infrastructure
of Liferay. For that information, refer to the developer tutorial [Introduction
to Liferay Search](/develop/tutorials/-/knowledge_base/7-0/introduction-to-liferay-search).

These terms will be useful to understand as you read this guide:

-  *Elasticsearch Home* refers to the root folder of your unzipped Elasticsearch installation (for example, `elasticsearch-2.2.0`). 
-  *Liferay Home* refers to the root folder of your Liferay installation. It will contain the `osgi`, `deploy`, `data`, and `license` folders, among others.

## Embedded vs. Remote Operation Mode [](id=embedded-vs-remote-operation-mode)

When you install Liferay, there's an embedded Elasticsearch already installed.
In embedded mode, Elasticsearch search is run with Liferay as a library in
the same JVM. Running Elasticsearch and Liferay in the same process has drawbacks:

-  Your Elasticsearch configuration will use the same JVM options as Liferay
-  Liferay and Elasticsearch will compete for resources. 

You wouldn't run an embedded database like HSQL in production, and you shouldn't
run Elasticsearch in embedded mode in production either. Instead, you want your
Liferay installation to run alongside Elasticsearch. In other words, run
Elasticsearch in *remote operation mode*, as a standalone server or cluster of
server nodes. The first step is to install Elasticsearch.

+$$$ 

**Note:** During development or demonstration, you might find it convenient to
run Elasticsearch in embedded mode, while clustering Liferay. In this scenario,
each Liferay node starts its own embedded Elasticsearch node. The Elasticsearch
nodes will automatically connect to each other and form an Elasticsearch cluster
named LiferayElasticsearchCluster. The Liferay indexes will be given one shard
each (number_of_shards=1), but the number of replicas (starting with
number_of_replicas=0) will vary with the number of Liferay instances in the
cluster.

Because every individual Liferay instance holds a full copy of the
entire index and runs all indexing commands for all documents, this would
constrain scalability in a production environment.

$$$

## Installing Elasticsearch [](id=installing-elasticsearch)

Install Elasticsearch, and then you can begin configuring it to use with
Liferay. Even if you already have Elasticsearch downloaded and unzipped, read
this section to make sure you have it properly set up to run with Liferay.

-  Download [Elasticsearch 2.2.0](https://download.elasticsearch.org/elasticsearch/release/org/elasticsearch/distribution/zip/elasticsearch/2.2.0/elasticsearch-2.2.0.zip)

-  Extract the contents of the compressed file you downloaded to your Liferay
   Home folder

-  Before continuing, make sure you have set the [`JAVA_HOME` environment
    variable](https://docs.oracle.com/cd/E19182-01/820-7851/inst_cli_jdk_javahome_t/)

-  Make sure Elasticsearch and Liferay are using the same version. You can
    specify this in `[Elasticsearch Home]/bin/elasticsearch.in.sh`:

        JAVA_HOME=/path/to/java

-  Install the following required Elasticsearch plugins:

    -  `analysis-icu`
    -  `analysis-kuromoji`
    -  `analysis-smartcn`
    -  `analysis-stempel`

    To install these plugins, navigate to Elasticsearch Home and enter
    
        ./bin/plugin install [plugin-name]

    Replace *[plugin-name]* with the Elasticsearch plugin's name.

-  Make the `[Elasticsearch_Home]/bin/elasticsearch.sh` file executable (if you're on Linux).

For more details refer to the [Elasticsearch installation guide](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/_installation.html)

Once you have Elasticsearch installed, you need to configure it for @product@.

## Configuring Elasticsearch [](id=configuring-elasticsearch)

For detailed Elasticsearch configuration information, refer to the
[Elasticsearch documentation](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/setup-configuration.html#settings).

The name of your Elasticsearch cluster is important. When you're running
Elasticsearch in remote mode, the cluster name is used by Liferay to recognize
the Elasticsearch cluster. To learn about setting the Elasticsearch cluster name
on the Liferay side, refer below to the section on <!--What???--> Configuring
Liferay for Elasticsearch.

Elasticsearch's configuration files are written in [YAML](http://www.yaml.org/)
and kept in the `[Elasticsearch Home]/config` folder:

-  `elasticsearch.yml`, for configuring Elasticsearch modules
-  `logging.yml`, for configuring Elasticsearch logging

To set the name of the Elasticsearch cluster, open `[Elasticsearch
Home]/config/elasticsearch.yml` and specify

    cluster.name: LiferayElasticsearchCluster

Since `LiferayElasticsearchCluster` is the default name given to the cluster in
Liferay, this would work just fine. Of course, you can name your cluster
whatever you'd like (we humbly submit the recommendation
`clustery_mcclusterface`). You can configure your node name using the same
syntax (setting the `node.name` property).

If you'd rather work from the command line than in the configuration file,
navigate to Elasticsearch Home and enter

    ./bin/elasticsearch --cluster.name clustery_mcclusterface --node.name nody_mcnodeface

Feel free to change the node name or the cluster name. Once you configure
Elasticsearch to your liking, start it up.

## Starting Elasticsearch [](id=starting-elasticsearch)

Start Elasticsearch by navigating to Elasticsearch Home and typing 

    ./bin/elasticsearch

if you run Linux, or 

    \bin\elasticsearch.bat

if you run Windows.

To run as a daemon, in the background, add the `-d` switch to either command:

    ./bin/elasticsearch -d

Now that you have Elasticsearch itself installed and running, and [Liferay installed](/discover/deployment/-/knowledge_base/6-2/liferay-installation-overview)
and running (do that if you haven't already) you need to introduce Liferay and
Elasticsearch to each other. Fortunately, Liferay provides an adapter that helps
it find and integrate your Elasticsearch cluster.

## Configuring the Liferay Elasticsearch Adapter [](id=configuring-the-liferay-elasticsearch-adapter)

Liferay has an Elasticsearch adapter that ships with @product@. It's a module
from the Liferay Foundation Suite that's deployed to the OSGi runtime, titled
*Liferay Portal Search Elasticsearch*. This adapter provides integration between
Elasticsearch and Liferay. Before you configure the adapter, make sure
Elasticsearch is running. 

There are two ways to configure the adapter: use the System Setting application
in Liferay's Control Panel, or manually create an OSGi configuration file. When
preparing a system for production deployment, you want to set up a repeatable
deployment process. Therefore, it's best to use the OSGi configuration file,
where your configuration is maintained in a controlled source.

It's convenient to configure the Elasticsearch adapter from System Settings, but
this is often only possible during development and testing. If you're not
familiar with System Settings, you can read about it
[here](/discover/portal/-/knowledge_base/7-0/system-settings). Even if you need
a configuration file so you can use the same configuration on another Liferay
system, you can still use System Settings. Just make the configuration edits you
need, then export the `.cfg` file with your configuration.

To configure the Elasticsearch adapter from the System Settings application:

1. Start Liferay.
2. Navigate to *Control Panel* &rarr; *Configuration* &rarr; *System Settings*
   &rarr; *Foundation*. 
3. Find the *Elasticsearch* entry (scroll down and browse to it or use the
   search box) and click the Actions icon
   (![Actions](../../../images/icon-actions.png)), then *Edit*.

    ![Figure 1: Use the System Settings application in Liferay's Control Panel to
    configure the Elasticsearch
    adapter.](../../../images/elasticsearch-system-settings.png)

4. Change *Operation Mode* to *Remote*, and then click *Save*.

    ![Figure 2: Set Operation Mode to *Remote* from System
    Settings.](../../../images/elasticsearch-configuration.png)

5. After you switch operation modes (`EMBEDDED` &rarr; `REMOTE`), you must
   trigger a reindex. Navigate to *Control Panel* &rarr; *Server
   Administration*, find the *Index Actions* section, and click *Execute* next
   to *Reindex all search indexes.* 

To configure the Elasticsearch adapter using an OSGi configuration file:

1. Create the following file:
    
        [Liferay_Home]/osgi/configs/com.liferay.portal.search.elasticsearch.configuration.ElasticsearchConfiguration.cfg

2. Add this to the `.cfg` file you just created:

        operationMode=REMOTE
        # If running Elasticsearch from a different computer:
        #transportAddresses=ip.of.elasticsearch.node:9300
        # Highly recommended for all non-prodcution usage (e.g., practice, tests, diagnostics):
        #logExceptionsOnly=false

3. Start Liferay, or reindex if Liferay is already running.

As you can see from the System Settings entry for Elasticsearch, there are a lot
more configuration options available. For a detailed accounting of these, refer
to the section of this article titled Available Elasticsearch Adapter
Configurations.

## Clustering Elasticsearch in Remote Operation Mode [](id=clustering-elasticsearch-in-remote-operation-mode)

Clustering Elasticsearch is easy. Each time you run the Elasticsearch start
script, a new node is added to the cluster. If you want four nodes, for example,
just run `./bin/elasticsearch` four times. If you only run the start script
once, you have a cluster with just one node.

Elasticsearch's default configuration works for a cluster of up to ten nodes,
since the default number of shards is `5`, while the default number of replica
shards is `1`:

    index.number_of_shards: 5
    index.number_of_replicas: 1

For more information on configuring an Elasticsearch cluster, see the
documentation on [Elasticsearch Index Settings](https://www.elastic.co/guide/en/elasticsearch/guide/current/_index_settings.html).

## Advanced Configuration of the Liferay Elasticsearch Adapter [](id=advanced-configuration-of-the-liferay-elasticsearch-adapter)

The default configurations for Liferay's Elasticsearch adapter module are set
in a Java class:

    com.liferay.portal.search.elasticsearch.configuration.ElasticsearchConfiguration

While the Elasticsearch adapter has a lot of configuration options out of the
box, you can also add configuration options. First learn about all the out of
the box options, then decide if you need to add more.

### Available Elasticsearch Adapter Configurations [](id=available-elasticsearch-adapter-configurations)

As mentioned above, there's a lot of configurability baked into Liferay's
Elasticsearch adapter module. The following is a list of all of the
configuration settings for Liferay's Elasticsearch adapter, in the order that
they appear in the System Settings application:

-  `clusterName=LiferayElasticsearchCluster`:
A String value that sets the name of the cluster to integrate with. This name
should match the remote cluster when Operation Mode is set to remote.  (See
also: remote operation mode)

-  `operationMode=EMBEDDED`
There are two operation modes you can choose from: EMBEDDED or REMOTE. Set to
REMOTE to connect to a remote standalone Elasticsearch cluster. Set to EMBEDDED
to start Liferay with an internal Elasticsearch instance. Embedded operation
mode is unsupported for production environments.

-  `indexNamePrefix=liferay-`
Set a String value to use as the prefix for the search index name. The default
value should not be changed under normal conditions. If you change it, you must
also perform a *reindex all* operation for the portal and then manually delete
the old index using the Elasticsearch administration console.

-  `bootstrapMlockAll=false`
A boolean setting that, when set to `true`, tries to lock the process address
space into RAM, preventing any Elasticsearch memory from being swapped out (see
[here](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/setup-configuration.html#setup-configuration-memory))
for more information)

-  `logExceptionsOnly=true`
A boolean setting that, when set to true, only logs exceptions from
Elasticsearch, and does not rethrow them.

-  `retryOnConflict=5`
Set an int value for the number of retries to attempt if a version conflict
occurs because the document was updated between getting it and updating it (see
[here](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/docs-update.html#_parameters_3)
for more information).

-  `discoveryZenPingUnicastHostsPort=9300-9400`
Set a String value for the range of ports to use when building the value for
discovery.zen.ping.unicast.hosts. Multiple Elasticsearch nodes on a range of
ports can act as gossip routers at the same computer (see
[here](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/modules-discovery-zen.html)
for more information).

-  `networkHost=`
Set this String value to instruct the node to bind to this hostname or IP
address and publish (advertise) this host to other nodes in the cluster. This is
a shortcut which sets the bind host and the publish host at the same time (see
[here](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/modules-network.html#common-network-settings)
for more information).

-  `networkBindHost=`
Set the String value of the network interface(s) a node should bind to in order
to listen for incoming requests (see
[here](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/modules-network.html#advanced-network-settings)
for more information).

-  `networkPublishHost=`
Set the String value of a single interface that the node advertises to other
nodes in the cluster, so that those nodes can connect to it (see
[here](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/modules-network.html#advanced-network-settings)
for more information).

-  `transportTcpPort=`
Set the String value for the port to bind for communication between nodes.
Accepts a single value or a range
(see [here](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/modules-transport.html#_tcp_transport)
for more information).

-  `transportAddresses=localhost:9300`
Set the String values for the addresses of the remote Elasticsearch nodes to
connect to. This value is required when Operation Mode is set to remote (see
[here](https://www.elastic.co/guide/en/elasticsearch/client/java-api/2.2/transport-client.html)
for more information). Specify as many or few nodes as you see fit.

-  `clientTransportSniff=true`
Set this booleant to true to enable cluster sniffing and dynamically discover
available data nodes in the cluster
(see [here](https://www.elastic.co/guide/en/elasticsearch/client/java-api/2.2/transport-client.html)
for more information).

-  `clientTransportIgnoreClusterName=false`
Set this boolean to true to ignore cluster name validation of connected nodes
(see [here](https://www.elastic.co/guide/en/elasticsearch/client/java-api/2.2/transport-client.html)
for more information).

-  `clientTransportNodesSamplerInterval=5s`
Set this String value to instruct the client node on how often to sample / ping
the nodes listed and connected (see
[here](https://www.elastic.co/guide/en/elasticsearch/client/java-api/2.2/transport-client.html)
for more information).

-  `httpEnabled=true`
Set this boolean to false to disable the http layer entirely on nodes which are
not meant to serve REST requests directly (see
[here](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/modules-http.html#_disable_http)
for more information).

-  `httpCORSEnabled=true`
Set this boolean to false to disable cross-origin resource sharing, i.e. whether
a browser on another origin can do requests to Elasticsearch. If disabled, web
front end tools like elasticsearch-head may be unable to connect (see
[here](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/modules-http.html#_settings_2)
for more information).

-  `httpCORSAllowOrigin=/https?:\\/\\/localhost(:[0-9]+)?/`
Set the String origins to allow when HTTP CORS is enabled (see
[here](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/modules-http.html#_settings_2)
for more information).

-  `httpCORSConfigurations=`
Set the String values for custom settings for HTTP CORS, in YML format
(elasticsearch.yml) (see
[here](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/modules-http.html#_settings_2)
for more information).

-  `additionalConfigurations=`
Set the String values for custom settings for embedded Elasticsearch, in YML
format. See: Adding Settings to the Liferay Elasticsearch Adapter

-  `additionalIndexConfigurations=`
Set the String values for custom settings for the Liferay index, in JSON or YML
format (refer to the Elasticsearch Create Index API for more information).
See: Adding Settings to the Liferay Elasticsearch Adapter

-  `additionalTypeMappings=`
Set the String values for custom mappings for the `LiferayDocumentType`, in JSON
format (refer to the Elasticsearch Put Mapping API for more information)
See: Adding Settings to the Liferay Elasticsearch Adapter

You can easily configure these settings in the System Setting application, or,
as mentioned above, you can specify them in a deployable OSGi `.cfg` file.

### Adding Settings to the Liferay Elasticsearch Adapter [](id=adding-settings-to-the-liferay-elasticsearch-adapter)

Even if the many available configuration options aren't enough, you can add
extra settings by using one or more of the `additionalConfigurations`,
`additionalIndexConfigurations`, or `additionalTypeMappings` settings. 

![Figure 3: You can add Elasticsearch configurations to the ones currently available
in System Settings.](../../../images/elasticsearch-additional-configs.png)

-  `additionalConfigurations` is used to define extra settings (defined in YAML)
   for the embedded Elasticsearch or the local Elasticsearch client, when
   running in remote mode.

    Here's an example: when Elasticsearch calculates a node's disk usage, it
    takes into account shards that are being relocated to the target node (see
    [here](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/disk-allocator.html)
    for more information). If the node is low on disk space, it prints an
    `INFO` log message like this:

        03:01:44,499 INFO
        [elasticsearch[Persuader][management][T#1]][decider:160] [Persuader] 
        low disk watermark [85%] exceeded on 
        [XecdPkdjQ5Cx5SS5JxlDzA][Persuader][/opt/liferay-portal-tomcat-master/
        data/elasticsearch/indices/LiferayElasticsearchCluster/nodes/0] free: 
        18.1gb[13%], replicas will not be assigned to this node

    There's currently no option to disable the disk allocation checking and its
    log message. However, you can add this to the `additionalConfigurations`
    field in System Settings:

        cluster.routing.allocation.disk.threshold_enabled: false

-  `additionalIndexConfigurations` is used to define extra settings (in JSON or
    YAML format) that are applied to the Liferay index when it's created.For
    example, you can create custom analyzers and filters using this setting. For
    a complete list of available settings, see the [Elasticsearch reference](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/index-modules.html)

    Here's an example that adds a custom analyzer:

        {
            "analysis": {
                "analyzer": {
                    "kuromoji_liferay_custom": {
                        "filter": [
                            "cjk_width",
                            "kuromoji_baseform",
                            "pos_filter"
                        ],
                        "tokenizer": "kuromoji_tokenizer"
                    }
                },
                "filter": {
                    "pos_filter": {
                        "type": "kuromoji_part_of_speech"
                    }
                }
            }
        }

-  `additionalTypeMappings` is used to define extra field mappings for the
    `LiferayDocumentType` type definition, which are applied when the index is
    created. Add these field mappings in using JSON syntax. For more information
    see
    [here](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/mapping.html)
    and
    [here](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/indices-put-mapping.html)

    +$$$

    **Note:** To look at the `LiferayDocumentType` definition, navigate to this
    file in Liferay's source:

        modules/apps/foundation/portal-search/portal-search-elasticsearch/src/main/resources/META-INF/mappings/liferay-type-mappings.json

    If you don't already have the Liferay source code, navigate to [Liferay's
    Downloads page](https://www.liferay.com/downloads), scroll to *Additional
    Files*, and find and click the download link next to *Liferay Source for
    [Your Version]*.

    $$$

    Here's an example that adds a dynamic template for Japanese content,
    configuring a new analyzer for String fields that end with `_ja*`: <!-- EXPLAIN-->

        {
            "dynamic_templates": [
                {
                    "template_ja": {
                        "mapping": {
                            "analyzer": "kuromoji_liferay_custom",
                            "index": "analyzed",
                            "store": "true",
                            "term_vector": "with_positions_offsets",
                            "type": "string"
                        },
                        "match": "\\w+_ja\\b|\\w+_ja_[A-Z]{2}\\b",
                        "match_mapping_type": "string",
                        "match_pattern": "regex"
                    }
                }
            ]
        }


+$$$

**Note:** There's actually a third way to add configuration options to the
Elasticsearch adapter. You or your favorite developer can publish a Settings
Contributor Component and deploy it to Liferay's OSGi runtime. See the developer
tutorial on Search Extension Points (not yet written) for more information. In
summary, the contributor module needs the following:

-  A class that implements either
`com.liferay.portal.search.elasticsearch.settings.SettingsContributor` or
`com.liferay.portal.search.elasticsearch.settings.IndexSettingsContributor`.
    -  If you're adding settings that would go into `additionalConfigurations`,
    override `SettingsContributor`. 
    -  If you want to add settings that would go into
    `additionalIndexConfigurations` or `additionalTypeMappings`, implement
    `IndexSettingsContributor`.
-  An OSGi `@Component` annotation for either of the implementations mentioned
    in the last step.

$$$

### Multi-line YAML Configurations [](id=multi-line-yaml-configurations)

If you configure the settings from the last section using a `.cfg` configuration
file, you might find yourself needing to write YML snippets that span multiple
lines. The syntax for that is straightforward, and just requires appending each
line with `\n\`, like this:

    additionalConfigurations=\
                        cluster.routing.allocation.disk.threshold_enabled: false\n\
                        cluster.service.slow_task_logging_threshold: 600s\n\
                        index.indexing.slowlog.threshold.index.warn: 600s\n\
                        index.search.slowlog.threshold.fetch.warn: 600s\n\
                        index.search.slowlog.threshold.query.warn: 600s\n\
                        monitor.jvm.gc.old.warn: 600s\n\
                        monitor.jvm.gc.young.warn: 600s

## Troubleshooting Elasticsearch [](id=troubleshooting-elasticsearch)

Sometimes things don't go as planned. If you've set up Liferay with
Elasticsearch in remote mode, but Liferay can't connect to Elasticsearch, check
these things:

-  Cluster name: The value of the `cluster.name` property in Elasticsearch must
match the `clusterName` property you configured for Liferay's Elasticsearch adapter.

-  Transport address: The value of the `transportAddress` property in the
Elasticsearch adapter must match the port where Elasticsearch is running. If
Liferay is running in embedded mode, and you start a standalone Elasticsearch
node or cluster, it will detect that port `9300` is taken, and will switch to port
`9301`. If you then set Liferay's Elasticsearch adapter to remote mode, it will
continue to look for Elasticsearch at the default port (`9300`).

Now that you have Elasticsearch configured for use with Liferay, read
[here](discover/deployment/-/knowledge_base/7-0/shield) to learn about
configuring Shield to secure your Elasticsearch data. 

## Related Topics [](id=related-topics)

[Introduction to Liferay Search](develop/tutorials/-/knowledge_base/7-0/introduction-to-liferay-search)

[Customizing Liferay Search](develop/tutorials/-/knowledge_base/7-0/customizing-liferay-search)
