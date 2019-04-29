---
header-id: configuring-the-liferay-elasticsearch-connector
---

# Configuring the Liferay Elasticsearch Connector

[TOC levels=1-4]

For detailed Elasticsearch configuration information, refer to the
[Elasticsearch documentation](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/settings.html).

The name of your Elasticsearch cluster is important. When you're running
Elasticsearch in remote mode, the cluster name is used by @product@ to recognize
the Elasticsearch cluster. To learn about setting the Elasticsearch cluster name
on the @product@ side, refer below to the section called Configuring the Liferay
Elasticsearch Connector.

+$$$

**Note:** The `http.enabled` setting in Elasticsearch corresponds to the `httpEnabled` setting in the Liferay Connector to Elasticsearch 6 application. As this setting was [deprecated in Elasticsearch 6.3](https://www.elastic.co/guide/en/elasticsearch/reference/current/release-notes-6.3.0.html#deprecation-6.3.0), the connector's corresponding setting is now also deprecated. This setting was only used for configuring the embedded Elasticsearch server, so its deprecation should have minimal impact to production deployments.

$$$

Elasticsearch's configuration files are written in [YAML](http://www.yaml.org)
and kept in the `[Elasticsearch Home]/config` folder. The main configuration
file is `elasticsearch.yml`, used for configuring Elasticsearch modules.

To set the name of the Elasticsearch cluster, open `[Elasticsearch
Home]/config/elasticsearch.yml` and specify

    cluster.name: LiferayElasticsearchCluster

Since `LiferayElasticsearchCluster` is the default name given to the cluster in
the @product@ Elasticsearch connector, this works just fine. Of course, you can
name your cluster whatever you want (we humbly submit the recommendation
`clustery_mcclusterface`).<sup>[1](#footnote1)</sup> Configure your node name
using the same syntax (setting the `node.name` property). There's no client
setting for this, it exists only in each Elasticsearch node's
`elasticsearch.yml` file.

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

To run as a daemon in the background, add the `-d` switch to either command:

    ./bin/elasticsearch -d

Once both Elasticsearch and @product@ are installed and running, introduce them
to each other. 

## Configuring the Liferay Elasticsearch Connector 

The Elasticsearch connector provides integration between Elasticsearch and the
portal. Before configuring the connector, make sure Elasticsearch is running.

There are two ways to configure the connector: 

1. [Use the System Settings application in the Control Panel.](#configuring-the-connector-in-the-control-panel) 

2. [Manually create an OSGi configuration file.](#configuring-the-connector-with-an-osgi-config-file) 

It's convenient to configure the Elasticsearch connector from System Settings, but
this is often only possible during development and testing. If you're not
familiar with System Settings, read about it
[here](/docs/7-2/user/-/knowledge_base/user/system-settings).
Remember that you can generate configuration files for deployment to other
systems by configuring System Settings, and then exporting the `.config` file
with your configuration.

### Configuring the Connector in the Control Panel 

To configure the Elasticsearch connector from the System Settings application,

1.  Start @product@.

2.  Navigate to *Control Panel* &rarr; *Configuration* &rarr; *System Settings*
    &rarr; *Platform*. 

3.  Find the *Elasticsearch* entry (scroll down and browse to it or use the
    search box) and click the Actions icon
    (![Actions](../../../images/icon-actions.png)), then *Edit*.

    Figure 1: Use the System Settings application in @product@'s Control Panel to configure the Elasticsearch connector.](../../../images/cfg-elasticsearch-sys-settings.png)

4.  Make any edits to the configuration and click *Save*.

    Figure 2: Set configurations for the Elasticsearch connector, like settings the Operation Mode to *Remote*.](../../../images/cfg-elasticsearch-sys-settings2.png)

+$$$

**Note:** If you switch operation modes (`EMBEDDED` &rarr; `REMOTE`), you must trigger a re-index. Navigate to *Control Panel* &rarr; *Configuration* &rarr; *Search*, and click *Execute* next to *Reindex all search indexes.*

$$$

### Configuring the Connector with an OSGi `.config` File 

When preparing a system for production deployment, you want to use a repeatable
deployment process. Therefore, it's best to use the OSGi configuration file,
where your configuration is maintained in a controlled source.

Follow these steps to configure the Elasticsearch connector using a configuration
file:

1. Create the following file:
    
        [Liferay_Home]/osgi/configs/com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration.config

2. Add configurations to the file, in the format `propertyName="Value"`. For
   example,

        operationMode="REMOTE"
        # If running Elasticsearch from a different computer:
        #transportAddresses="ip.of.elasticsearch.node:9300"
        # Highly recommended for all non-prodcution usage (e.g., practice, tests, diagnostics):
        #logExceptionsOnly="false"

3. Start @product@ or re-index if already running.

As you can see from the System Settings entry for Elasticsearch, there are a lot
more configuration options available that help you tune your system for optimal
performance. 
<!-- For a detailed accounting of these, refer to the reference article
on [Elasticsearch Settings](discover/reference/-/knowledge_base/7-1/elasticsearch-settings).
-->

What follows here are some known good configurations for clustering
Elasticsearch. These, however, can't replace the manual process of tuning,
testing under load, and tuning again, so we encourage you to examine the
<!--
[settings](discover/reference/-/knowledge_base/7-1/elasticsearch-settings) 
as well as the -->
[Elasticsearch documentation](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/important-settings.html) 
and go through that process once you have a working configuration. 

## Configuring a Remote Elasticsearch Host 

In production systems Elasticsearch and @product@ are installed on different
servers. To make @product@ aware of the Elasticsearch cluster, set

    transportAddresses=[IP address of Elasticsearch Node]:9300

Here's an example that sets the IP address of two nodes in the Elasticsearch
cluster:

    transportAddresses=["192.168.1.1:9300","192.168.1.2:9300"]

Set this in the Elasticsearch connector's OSGi configuration file. List as many
or as few Elasticsearch nodes in this property as you want. This tells
@product@ the IP address or host name where search requests should be sent. If
using System Settings, set the value in the *Transport Addresses* property.

+$$$

**Note:** In an Elasticsearch cluster you can list the transport addresses for multiple Elasticsearch nodes as a comma-separated list in the `transportAddresses` property. If you set only one transport address, @product@ loses contact with Elasticsearch if that node goes down.

$$$

On the Elasticsearch side, set the `network.host` property in your
`elaticsearch.yml` file. This property simultaneously sets both the *bind host*
(the host where Elasticsearch listens for requests) and the *publish host*
(the host name or IP address Elasticsearch uses to communicate with other
nodes). See
[here](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/modules-network.html)
for more information.

## Clustering Elasticsearch in Remote Operation Mode 

To cluster Elasticsearch, first set `node.max_local_storage_nodes` to
be something greater than `1`. When you run the Elasticsearch start script,
a new local storage node is added to the cluster. If you want four nodes running
locally, for example, run `./bin/elasticsearch` four times. See
[here](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/modules-node.html#max-local-storage-nodes)
for more information.

Configure the number of shards and replicas in the Elasticsearch 6 connector,
using the `indexNumberOfShards` and `indexNumberOfReplicas` properties to
specify the number of primary shards and number of replica shards, respectively.
Elasticsearch's default configuration works for a cluster of up to ten nodes,
since the default number of shards is `5` and the default number of replica
shards is `1`.

+$$$

**Note:** Elasticsearch uses the [Zen Discovery Module](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/modules-discovery-zen.html) by default, which provides unicast discovery. Additionally, nodes in the cluster communicate using the [Transport Module](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/modules-transport.html), through TCP. See the Elasticsearch documentation for the available properties (to be set in the `elasticsearch.yml` file), and the @product@ Elasticsearch connector's settings for the connector's available settings.
<!--reference article](discover/reference/-/knowledge_base/7-1/elasticsearch-settings)-->

At a minimum, provide the list of hosts (as `host:port`)  to act as gossip routers during unicast discovery in the `elasticsearch.yml`:

    discovery.zen.ping.unicast.hosts: ["node1.ip.address", "node2.ip.address"]

For example,

    discovery.zen.ping.unicast.hosts: ["10.10.10.5", "10.10.10,.5:9305"]

For more information on configuring an Elasticsearch cluster, see the
documentation on 
[Elasticsearch Index Settings](https://www.elastic.co/guide/en/elasticsearch/guide/6.5/_index_settings.html).

+$$$

## Elasticsearch Connector System Settings, By Operation Mode 

Some of the settings available for the Elasticsearch connector are applicable
for only one operation mode (REMOTE or EMBEDDED). Refer to the table below:

Connector Setting/Operation Mode | EMBEDDED | REMOTE |
------------------------------| :----: | :----: |
`clusterName` | x | x
`operationMode` | x | x
`indexNamePrefix` | x | x
`indexNumberOfReplicas*` | x | x
`indexNumberOfShards*` | x | x
`bootstrapMlockAll` | x | \-
`logExceptionsOnly` | x | x
`retryOnConflict` | x | x
`discoveryZenPingUnicastHostsPort` | x | \-
`networkHost` | x | \-
`networkBindHost` | x | \-
`networkPublishHost` | x | \-
`transportTcpPort` | x | \-
`transportAddresses` | \- | x
`clientTransportSniff` | \- | x
`clientTransportIgnoreClusterName` | \- | x
`clientTransportPingTimeout*` | \- | x
`clientTransportNodesSamplerInterval` | \- | x
`httpEnabled` | x | \-
`httpCORSEnabled` | x | \-
`httpCORSAllowOrigin` | x | \-
`httpCORSConfigurations` | x | \-
`additionalConfigurations` | x | x
`additionalIndexConfigurations` | x | x
`additionalTypeMappings` | x | x
`overrideTypeMappings` | x | x

<a name="footnote1">1</a> This is, of course, a nod to all those fans of [Boaty Mcboatface](http://www.theatlantic.com/international/archive/2016/05/boaty-mcboatface-parliament-lessons/482046). 

