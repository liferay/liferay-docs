# Configuring Elasticsearch [](id=configuring-elasticsearch-for-liferay-0)

Elasticsearch is an open source, highly scalable, full-text search and analytics
engine.

By default, Elasticsearch runs as an embedded search engine, but it's only
supported in production as a separate server or cluster. This guide walks you
through the process of configuring Elasticsearch.

If you'd rather use Solr, it's also supported. See the documentation on
Installing Solr if you're interested.

<!-- See [here](/discover/deployment/-/knowledge_base/7-1/using-solr) for information
on installing and configuring Solr. -->

To get up and running quickly with Elasticsearch as a remote server, refer to
the [Installing Elasticsearch article](/discover/deployment/-/knowledge_base/7-1/installing-elasticsearch).
Those are basic instructions for installing and configuring Elasticsearch in
a single server environment. This article includes more details and information
on clustering and tuning Elasticsearch. Here, you'll learn to configure your
existing Elasticsearch installation for use in production environments. 

If you've come here looking for information on search engines in general, or the
low level search infrastructure of @product@, refer instead to the developer
tutorial 
Introduction to Liferay Search (not yet written).

These terms are useful to understand as you read this guide:

-  *Elasticsearch Home* refers to the root folder of your unzipped Elasticsearch
   installation (for example, `elasticsearch-6.1.3`). 

-  [*Liferay Home*](/discover/deployment/-/knowledge_base/7-1/installing-product#liferay-home)
   refers to the root folder of your @product@ installation. It contains the
   `osgi`, `deploy`, `data`, and `license` folders, among others.

## Embedded vs. Remote Operation Mode [](id=embedded-vs-remote-operation-mode)

When you install @product@, Elasticsearch is already embedded. In embedded mode,
Elasticsearch search runs in the same JVM to make it easy to test-drive with
minimal configuration. Running both servers in the same process has drawbacks:

-  Elasticsearch must use the same JVM options as @product@.
-  @product@ and Elasticsearch compete for resources. 

You wouldn't run an embedded database like HSQL in production, and you shouldn't
run Elasticsearch in embedded mode in production either. Instead, run
Elasticsearch in *remote operation mode*, as a standalone server or cluster of
server nodes.

## Troubleshooting Elasticsearch [](id=troubleshooting-elasticsearch)

Sometimes things don't go as planned. If you've set up @product@ with
Elasticsearch in remote mode, but @product@ can't connect to Elasticsearch, check
these things:

**Cluster name:** 
: The value of the `cluster.name` property in Elasticsearch must match the
`clusterName` property you configured for Liferay's Elasticsearch adapter.

**Transport address:** 
: The value of the `transportAddress` property in the Elasticsearch adapter must
match the port where Elasticsearch is running. If @product@ is running in
embedded mode, and you start a standalone Elasticsearch node or cluster, it
detects that port `9300` is taken and switches to port `9301`. If you then set
Liferay's Elasticsearch adapter to remote mode, it continues to look for
Elasticsearch at the default port (`9300`).

The next two articles cover the Liferay Connector to Elasticsearch's
configuration options in more detail.
