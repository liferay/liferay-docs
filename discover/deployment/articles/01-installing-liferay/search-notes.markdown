First Article
# Configuring Elasticsearch in Production Environments

Liferay is an open source project, so you might not be surprised to learn that
the default search engine adapter that ships with Liferay is also an open source
project. Elasticsearch is a popular search engine that ships with @product@ and
configuring can be done easily. 

## Running Elasticsearch in Remote Operation Mode

It's a best practice to disable Elasticsearch from running in embedded
mode in production environments. Instead, you want your Liferay installation to
run alongside your Elasticsearch operation. In other words, run Elasticsearch in
*remote operation mode*. The first step is to install Elasticsearch:

<!-- Confirm this crazy list actually renders properly when converted to html,
and make sure we want to link to that specific version. We could provide
instructions on figuring out which version is supported, if this is likely to
change-->

-  Download [Elasticsearch 2.2.0](https://download.elasticsearch.org/elasticsearch/release/org/elasticsearch/distribution/zip/elasticsearch/2.2.0/elasticsearch-2.2.0.zip)

-  Extract the contents of the compressed file you downloaded to your Liferay
   Home folder

-  Before continuing, make sure you have set the [`JAVA_HOME` environment
    variable](https://docs.oracle.com/cd/E19182-01/820-7851/inst_cli_jdk_javahome_t/)

-  Make sure Elasticsearch and Liferay are using the same version. You can
    specify this in `ES_HOME/bin/elasticsearch.in.sh`:

        JAVA_HOME=/path/to/java

-  Install some necessary Elasticsearch plugins by navigating to Elasticsearch
    Home and entering
    
        ./bin/plugin install [plugin-name]

    Replace *[plugin-name]* with the Elasticsearch plugin's name. You'll need
    these plugins:

    -  `analysis-icu`
    -  `analysis-kuromoji`
    -  `analysis-smartcn`
    -  `analysis-stempel`

<!-- These are required?-->

-  Make the `elasticsearch.sh` file executable (if you're on Linux).


For more details refer to the [Elasticsearch installation guide](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/_installation.html)

Once you have Elasticsearch installed, you need to configure it for @product@.

The architectural overview is Liferay (with Liferay Portal Search Elasticsearch
OSGi module installed), alongside the Elasticsearch installation, any connectors you
need (Shield, Marve) installed into Elasticsearch and Liferay (if necessary?),
and your Liferay Database.
<!-- We need a diagram of this boondoggle -->

## Configuring Elasticsearch

For detailed Elasticsearch configuration information, refer to the
[Elasticsearch documentation](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/setup-configuration.html#settings)

The name of your Elasticsearch cluster is important. When you're not in embedded
mode, the cluster name is used by Liferay to recognize the Elasticsearch
cluster. To learn about setting the Elasticsearch cluster name on the Liferay
side, refer below to the section on <!--What???--> Configuring Liferay for
Elasticsearch.

Elasticsearch's configuration files are written in [YAML](http://www.yaml.org/)
and kept in the `[Elasticsearch Home]/config` folder. There are two that you
should be aware of:

-  `elasticsearch.yml`, for configuring Elasticsearch modules
-  `logging.yml`, for configuring Elasticsearch logging

To set the name of the Elasticsearch cluster, open `[Elasticsearch
Home]/config/elasticsearch.yml` and specify

    cluster.name: LiferayElasticsearchCluster

Since `LiferayElasticsearchCluster` is the default name given to the cluster in
Liferay, this would work just fine. Of course, you can name your cluster
whatever you'd like, though we humbly submit the recommendation
`clustery_mc_clusterface`. You can configure your node name using the same
syntax (setting the `node.name` property).

If you'd rather work from the command line than in the configuration file,
navigate to Elasticsearch Home and enter

    ./bin/elasticsearch --cluster.name clustery_mcclusterface --node.name nody_mcnodeface

Feel free to change the node name or the cluster name. Once you configure
Elasticsearch to your liking, start it up.

## Starting Elasticsearch

Start Elasticsearch by navigating to Elasticsearch Home and typing 

    ./bin/elasticsearch

if you run Linux, or 

    \bin\elasticsearch.bat

if you run Windows.


To run as a daemon, in the background, add the `-d` switch to the command:

    ./bin/elasticsearch -d

Now that you have Elasticsearch itself installed and running, and [Liferay installed](/discover/deployment/-/knowledge_base/6-2/liferay-installation-overview)
and running (do that if you haven't already) you need to introduce Liferay and
Elasticsearch to each other. Fortunately, Liferay provides an adapter that helps
it find and integrate your Elasticsearch cluster.

## Configure the Liferay Elasticsearch Adapter

Liferay has developed an Elasticsearch adapter that ships with @product@. It's a
module from the Liferay Foundation Suite that's deployed to the OSGi runtime,
titled *Liferay Portal Search Elasticsearch*. This adapter provides integration
between Elasticsearch and Liferay. 

Before you configure the adapter, make sure Elasticsearch is running.  There are
two ways to configure the adapter: use the System Setting application in
Liferay's Control Panel, or manually create an OSGi configuration file.

To configure the Elasticsearch adapter from the System Settings application:

1. Start Liferay.
2. Navigate to *Control Panel* &rarr; *Configuration* &rarr; *System Settings*
   &rarr; *Foundation*. 
3. Find the *Elasticsearch* entry (scroll down and browse to it or use the search box) and click the Actions icon (![Actions](../../images/icon-actions.png)), then *Edit*.

    ![Figure x: Use the System Setting application in Lfieray's Control Panel to
    configure the Elasticsearch
    adapter.](../../images/elasticsearch-system-settings.png)

4. Change *Operation Mode* to *Remote*, and then click *Save*.

    ![Figure x: Set Operation Mode to *Remote* from System
    Settings.](../../images/elasticsearch-configuration.png)

To configure the Elasticsearch adapter using an OSGi configuration file:

1. Create the following file:
    
        [Liferay_Home]/osgi/configs/com.liferay.portal.search.elasticsearch.configuration.ElasticsearchConfiguration.cfg

2. Add this to the `.cfg` file you just created:

        operationMode=REMOTE
        # If running Elasticsearch from a different computer:
        #transportAddresses=ip.of.elasticsearch.node:9300
        # Highly recommended for practice, tests, diagnostics and any non-production usage in general:
        #logExceptionsOnly=false

3. Start Liferay.

As you can see from the System Settings Elasticsearch configuration page, there
are a lot more configuration options available. For a detailed accounting of
these, refer to the end of this article in the section titled <!--?????-->.

## Clustering Elasticsearch in Remote Operation Mode

<!-- Find a place to cover what happens when you cluster Liferay with
Elasticsearch running in Embedded mode-->

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

## Advanced Configuration of the Liferay Elasticsearch Adapter

The default configurations for Liferay's Elasticsearch adapter module are set
in a Java class:

    com.liferay.portal.search.elasticsearch.configuration.ElasticsearchConfiguration

<!-- Describe the beauty of this class with the OSGi stuff? -->

While the Elasticsearch adapter has a lot of configuration options out of the box, you can also add configuration options. First learn abotu all the out of the box options, then descide if you need to add more.

### Available Elasticsearch Adapter Configurations

As mentioned above, there's a lot of configurability baked into Liferay's
Elasticsearch adapter module. 
The following is a list of all of the configuration settings for Liferay's
Elasticsearch adapter, in the order that they appear in the System Settings
application:

-  `clusterName=LiferayElasticsearchCluster`:
A String value that sets the name of the cluster to integrate with. This name should match the remote cluster when Operation Mode is set to remote.
(See also: remote operation mode)

-  `operationMode=EMBEDDED`
There are two operation modes you can choose from: EMBEDDED or REMOTE. Set to
REMOTE to connect to a remote standalone Elasticsearch cluster. Set to EMBEDDED
to start Liferay with an internal Elasticsearch instance. Embedded operation mode is unsupported for production environments.

-  `indexNamePrefix=liferay-`
Set a String value to use as the prefix for the search index name. The default
value should not be changed under normal conditions. If you change it,
you must also perform a *reindex all* operation for the portal and then manually
delete the old index using the Elasticsearch administration console.

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
ports can act as gossip routers at the same computer (see [here](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/modules-discovery-zen.html) for more information).

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
nodes in the cluster, so that those nodes can connect to it (see [here](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/modules-network.html#advanced-network-settings) for more
information).

-  `transportTcpPort=`
Set the String value for the port to bind for communication between nodes.
Accepts a single value or a range (see[here](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/modules-transport.html#_tcp_transport) for more information).


-  `transportAddresses=localhost:9300`
Set the String values for the addresses of the remote Elasticsearch nodes to
connect to. This value is required when Operation Mode is set to remote (see
[here](https://www.elastic.co/guide/en/elasticsearch/client/java-api/2.2/transport-client.html)
for more information).


-  `clientTransportSniff=true`
Set this booleant to true to enable cluster sniffing and dynamically discover
available data nodes in the cluster
(see[here](https://www.elastic.co/guide/en/elasticsearch/client/java-api/2.2/transport-client.html)
for more information).

-  `clientTransportIgnoreClusterName=false`
Set this boolean to true to ignore cluster name validation of connected nodes
(see[here](https://www.elastic.co/guide/en/elasticsearch/client/java-api/2.2/transport-client.html)
for more information).

-  `clientTransportNodesSamplerInterval=5s`
Set this String value to instruct the client node on how often to sample / ping the nodes listed and connected (see [here](https://www.elastic.co/guide/en/elasticsearch/client/java-api/2.2/transport-client.html) for more information).

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
format.  See: Contributing Additional Settings<!-- Change to final section
title-->

-  `additionalIndexConfigurations=`
Set the String values for custom settings for the Liferay index, in JSON or YML
format (Elasticsearch Create Index API).  For a complete list of available
settings, see the [Elasticsearch
reference](https://www.elastic.co/guide/en/elasticsearch/reference/2.2/index-modules.html)
See: Contributing Additional Settings

-  `additionalTypeMappings=`
Set the String values for custom mappings for the LiferayDocumentType, in JSON
format (Elasticsearch Put Mapping API).  In addition to the available
configurations is the possibility of adding more.

You can easily configure these settings in the System Setting application, or,
as mentioned above, you can specify them in a deployable OSGi `.cfg` file.

### Adding Configuration Options to the Liferay Elasticsearch Adapter

Even if the many available configuration options aren't enough, you can add
extra settings by using one or more of the `additionalConfigurations`,
`additionalIndexConfigurations`, or `additionalTypeMappings` settings. 

-  `additionalConfigurations` is used to define extra settings (defined in YAML)
   for the embedded Elasticsearch or the local Elasticsearch client, when
   running in remote mode.

Not only can you
configure are there lots of options, but several configuration methods to
consider using:

- Create a configuration file, like 

        LIFERAY_HOME/osgi/configs/com.liferay.portal.search.elasticsearch.configuration.ElasticsearchConfiguration.cfg

-  Use the System Setting application in Liferay's Control Panel.
-  
-



![Figure x: You can add Elasticsearch configurations to the ones currently available
in System Settings.](../../images/elasticsearch-additional-configs.png)

























When a @product@ user searches for something in Liferay, they're lookin for
*data*, and data is stored in the @product@ database. Search functionality can
rely on database queries or on a search engine. 

Why would you need a search engine at all?

When your users search for something in @product@, they're sending a search
query, and obtaining results from a search engine. By default, the search
engine you'll use with @product@ is Elasticsearch, a CONCISELY DEFINE HERE.

When you install Liferay, Elasticsearch is operating in *embedded* mode by
default. In embedded mode, Elasticsearch search is run alongside Liferay as a
library in the same JVM. Running both in the same process confines your
Elasticsearch configuration to using the same JVM options as Liferay, and forces
Liferay and Elasticsearch to compete for resources. You wouldn't run an embedded
database like HSQL in production, and you shouldn't run Elasticsearch in
embedded mode in production either. 

Consider what happens when you have a clustered environment.

## Portal Clustering and Embedded Elasticsearch

When you run Portal with the Elasticsearch adapter in embedded mode, an
Elasticsearch node is automatically started in-process. In a clustered Portal
environment, each separate Portal instance in the cluster starts its own
embedded in-process Elasticsearch node. The embedded Elasticsearch nodes connect
to each other and form an Elasticsearch cluster (named
`LiferayElasticsearchCluster` by default). In this mode, the Liferay indexes
will be created with a fixed `number_of_shards` of `1` and a variable
`number_of_replicas` starting from `0`; the number of replicas increases and
decreases with the number of extra @product@ virtual instances joining or
leaving the cluster.

Since you're now convinced that you *should not* run Elasticsearch in embedded mode
in your clustered production environment, let's turn to how you *should*
configure Elasticsearch for Liferay.

## Related Topics

[Introduction to Liferay Search](develop/tutorials/-/knowledge_base/7-0/introduction-to-liferay-search)

[Customizing Liferay Search](develop/tutorials/-/knowledge_base/7-0/customizing-liferay-search)




-  
-  
-
-





Shield and Marvel are in DE only!

Marvel is for monitoring. You have a Marvel Agent on each cluster node, and a
Marvel Web Application which provides a dashboard on a Kibana server, which is
used with Elasticsearch to explore and analyze data stored in the Elasticsearch
indices

Shield is the security component to making the search indices explorable via Marvel. 
Deploy shield JAR to Liferay nodes with Marvel agent?

Neither is pre-installed. For my testing I can build from the portal-src, but link to the products from our doc?

Deploy these things to the OSGI runtime?

Marvel module won't build.
Shield module builds, deploys (the Jar is put in the -fragment module) but I
can't find it with lb in gogo

Dave Truong, Andre Oliveira have a couple of the marvel tickets.

Who's the SME? Mike Han or one of those guys?

So these are plugins for elasticsearch, but we've made OSGi modules out of them? Are they on Marketplace? I could not find anything with a search for "Marvel"

Update: Kibana can't run in a background process right now, needs to run in the
foreground. 4.4 has some app that can do it, not office supported but we're
looking into it.

Tibor is doc'ing some solr security stuff, leaving some internal to make
customers ask if they really want it. mike wants to get rid of solr support in
the future.

most important qa tix
26402
26404

shield steps work for linux and mac but running into excel handshake exceptions
in windows.

qa does not have windows automation environments

3 docs in; search infra is done; status of elastic and solr? securing solr?
annotated part shouldn't go in. solr property stuff is almost compete (few
properties missing--read and write urls).

SME; Andre or Tibor?
