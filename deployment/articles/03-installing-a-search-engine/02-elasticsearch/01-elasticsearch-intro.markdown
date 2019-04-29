---
header-id: elasticsearch
---

# Elasticsearch

[TOC levels=1-4]

Elasticsearch is an open source, highly scalable, full-text search and
analytics engine.

By default, Elasticsearch runs as an embedded search engine, but it's only
supported in production as a separate server or cluster. This guide walks you
through the process of configuring Elasticsearch in remote mode.

![Figure 1: To see information about the currently connected search engine, go to _Control Panel &rarr; Configuration &rarr; Search_. ](../../../images/search-admin-engineinfo.png)

<!-- Uncomment this when we release the Solr adapter
If you'd rather use Solr, it's also supported. See the documentation on
[Installing Solr](discover/deployment/-/knowledge_base/7-1/installing-solr) 
if you're interested.
-->

To get up and running quickly with Elasticsearch as a remote server, refer to
the 
[Installing Elasticsearch article](/7-2/deploy/-/knowledge_base/deploy/installing-elasticsearch).
Included there are basic instructions for installing and configuring
Elasticsearch in a single server environment. Additional articles include more
details and information on configuring and tuning Elasticsearch. 

If you've come here looking for information on search engines in general, or
the low level search infrastructure of @product@, refer instead to the
developer tutorial Introduction to Liferay Search (not yet written).

These terms are useful to understand as you read this guide:

-  *Elasticsearch Home* refers to the root folder of your unzipped Elasticsearch
   installation (for example, `elasticsearch-6.5.1`). 

-  [*Liferay Home*](/7-2/deploy/-/knowledge_base/deploy/liferay-home)
   refers to the root folder of your @product@ installation. It contains the
   `osgi`, `deploy`, `data`, and `license` folders, among others.

## Embedded vs. Remote Operation Mode

When you start @product@, this message is displayed in the log: 

    2019-04-29 09:59:02.276 WARN  [Elasticsearch initialization thread][EmbeddedElasticsearchConnection:288] Liferay is configured to use embedded Elasticsearch as its search engine. Do NOT use embedded Elasticsearch in production. Embedded Elasticsearch is useful for development and demonstration purposes. Refer to the documentation for details on the limitations of embedded Elasticsearch. Remote Elasticsearch connections can be configured in the Control Panel.

When you start @product@, Elasticsearch started in embedded mode, which means,
Liferay runs an Elasticsearch node in the same JVM to make it easy to test-drive with
minimal configuration. Running both servers in the same process has drawbacks:

-  Elasticsearch must use the same JVM options as @product@.
-  @product@ and Elasticsearch compete for the same system resources. 

+$$$

**Note:** While the embedded operation node is not supported in production environments, installing
Kibana to monitor the embedded Elasticsearch server is useful during
development and testing. Just be aware that you must install the 
[OSS only Kibana build](https://www.elastic.co/downloads/kibana-oss).

$$$

You wouldn't run an embedded database like HSQL in production, and you shouldn't
run Elasticsearch in embedded mode in production either. Instead, run
Elasticsearch in *remote operation mode*, as a standalone server or cluster of
server nodes.

## Troubleshooting Elasticsearch

Sometimes things don't go as planned. If you've set up @product@ with
Elasticsearch in remote mode, but @product@ can't connect to Elasticsearch, check
these things:

**Cluster name:** 
: The value of the `cluster.name` property in Elasticsearch must match the
`clusterName` property you configured for Liferay's Elasticsearch connector.

**Transport address:** 
: The value of the `transportAddresses` property in the Elasticsearch connector configuration must
contain at least one valid host and port where Elasticsearch is running. If @product@ is running in
embedded mode, and you start a standalone Elasticsearch node or cluster, it
detects that port `9300` is taken and switches to port `9301`. If you then set
Liferay's Elasticsearch connector to remote mode, it continues to look for
Elasticsearch at the default port (`9300`).

The following articles cover the Liferay Connector to Elasticsearch's
configuration options in more detail.

**Cluster Sniffing (Additional Configurations):**
: When integrating @product@ with an Elasticsearch cluster containing multiple and different "node" [types](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/modules-node.html#modules-node), Liferay's Elasticsearch connector may throw `NoNodeAvailableException` in the console log if no `data` node is available on the specified transport addresses. This may happen for example when only `master` type node addresses are configured. This is due to the ["cluster sniffing feature"](https://www.elastic.co/guide/en/elasticsearch/client/java-api/6.5/transport-client.html) of the Elasticsearch Transport Client that Liferay's connector is using internally and it is enabled by default:
>If, for instance, you initially connect to a master node, after sniffing, no further requests will go to that master node, but rather to any data nodes instead. The reason the transport client excludes non-data nodes is to avoid sending search traffic to master only nodes.
To disable this feature, add `client.transport.sniff: false` to the `additionalConfigurations` for Liferay's Elasticsearch connector configuration.
