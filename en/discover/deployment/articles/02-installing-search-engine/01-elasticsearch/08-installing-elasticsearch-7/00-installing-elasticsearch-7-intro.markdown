---
header-id: installing-elasticsearch-7
---

# Installing Elasticsearch 7

[TOC levels=1-4]

Elasticsearrch 7 can be installed with @product-ver@. This article presents a basic setup. For additional details on the general Elasticsearch Installation procedure, refer to
[Installing Elasticsearch](/docs/7-1/deploy/-/knowledge_base/d/installing-elasticsearch).

Once installed refere to the [configuration guide](/docs/7-1/deploy/-/knowledge_base/d/configuring-the-liferay-elasticsearch-connector).

| **Note:** Elasticsearch 6.x and 7.x are supported for @product-ver@. Refer to
| the [Search Engine Compatibility
| Matrix](https://help.liferay.com/hc/en-us/articles/360016511651#Liferay-DXP-7.1)
| for details.

To install Elasticsearch 7 on a new @product-ver@ installation:

1. Install and configure Elasticsearch. 

2. Install the Liferay Connector to Elasticsearch 7 and restart @product@.
 
3. Disable Elasticsearch 6.

4. Configure @product@ to connect to your Elasticsearch cluster. 

5. Restart @product@ and reindex your search indexes. 

## Install Elasticsearch

To install Elasticsearch,

1. Download an Elasticsearch archive (not the OSS version) from [Elastic's website](https://www.elastic.co).

   Download the latest Elasticsearch archive [compatible with your Liferay version](https://help.liferay.com/hc/en-us/articles/360016511651#Liferay-DXP-7.1).

2. Extract the archive contents to a local folder where you want to run
   Elasticsearch. This folder is your *Elasticsearch Home*.

3. Install the required Elasticsearch plugins by running these commands in your
   `[Elasticsearch Home]/bin` folder:

   ```bash
   ./elasticsearch-plugin install analysis-icu
   ```

   ```bash
   ./elasticsearch-plugin install analysis-kuromoji
   ```

   ```bash
   ./elasticsearch-plugin install analysis-smartcn
   ```

   ```bash
   ./elasticsearch-plugin install analysis-stempel
   ```

Each Elasticsearch server is configured by its `[Elasticsearch
Home]/config/elasticsearch.yml` file.

Here's an example `elasticsearch.yml` configuration for a single-node cluster:

```yaml
cluster.name: LiferayElasticsearchCluster

discovery.type: single-node
network.host: es-network
node.name: es-node1
transport.port: 9300

# Include additional settings (e.g., security settings) 
```

This cluster is named `LiferayElasticsearchCluster` and has one node named
`es-node1`.

If you are not configuring hosts for a production mode setup, use `localhost`
as the network host value. Elasticsearch can bind to loopback addresses for
HTTP and Transport communication. If you use a loopback network address and
single node discovery, this means the Elasticsearch server is running in
_development mode_.

Start Elasticsearch.

```bash
./bin/elasticsearch
```

## Install the Liferay Connector to Elasticsearch

Liferay requires installation of a connector application that can enable
communication with the corresponding Elasticsearch version:

1. Download the [appropriate
   connector](https://help.liferay.com/hc/en-us/articles/360016511651#Liferay-DXP-7.1)
   for your version of Liferay and Elasticsearch.

2. With Liferay running, place the LPKG file into `Liferay Home/deploy/`

3. Restart Liferay. The remaining steps can be completed with Liferay running.

## Disable Elasticsearch 6

Whether you're installing Elasticsearch 7 for a new @product-ver@ installation
or upgrading an existing stack to Elasticsearch 6, you must disable the
Elasticsearch 6 search modules.

1. Create a configuration file named

    com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration.config

2. Give it these contents:

    ```properties
    blacklistBundleSymbolicNames=[ \
        "com.liferay.portal.search.elasticsearch6.api", \
        "com.liferay.portal.search.elasticsearch6.impl", \
        "com.liferay.portal.search.elasticsearch6.spi", \
        "com.liferay.portal.search.elasticsearch6.xpack.security.impl", \
        "Liferay Connector to X-Pack Security [Elastic Stack 6.x] - Impl", \
        "Liferay Enterprise Search Security  - Impl" \
    ]
    ```

3. Place the file in `Liferay Home/osgi/configs`.

| **Note:** After disabling the Elasticsearch 6 connection, you'll see
| exceptions in the Liferay log, because there's now no search engine
| connection. This is fixed by the next step.

## Configure Liferay

Create a configuration file named

```
com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration.config
```

Give it these contents:

```properties
operationMode="REMOTE"
transportAddresses=["localhost:9300"]
```

This simple configuration leverages many default settings, but the
configuration is customizable. See the [Elasticsearch
documentation](https://www.elastic.co/guide/en/elasticsearch/reference/7.12/modules-network.html#transport-settings)
to learn about the available transport settings.

Learn more about the Liferay connector's corresponding configurations in the [configuration guide](/docs/7-1/deploy/-/knowledge_base/d/configuring-the-liferay-elasticsearch-connector).

## Restart and Re-Index

If both Liferay and Elasticsearch are running, once the connection is configured the Elasticsearch log shows that the Liferay indexes are created:

```bash
[2021-05-05T13:16:29,016][INFO ][o.e.c.m.MetadataCreateIndexService] [es-node1] [liferay-0] creating index, cause [api], templates [], shards [1]/[1]
[2021-05-05T13:16:29,032][INFO ][o.e.c.r.a.AllocationService] [es-node1] updating number_of_replicas to [0] for indices [liferay-0]
[2021-05-05T13:16:29,300][INFO ][o.e.c.r.a.AllocationService] [es-node1] Cluster health status changed from [YELLOW] to [GREEN] (reason: [shards started [[liferay-0][0]]]).
[2021-05-05T13:16:29,392][INFO ][o.e.c.m.MetadataMappingService] [es-node1] [liferay-0/rkLFWo4gT9mkL9Y2wrKkpA] update_mapping [LiferayDocumentType]
[2021-05-05T13:16:29,468][INFO ][o.e.c.m.MetadataMappingService] [es-node1] [liferay-0/rkLFWo4gT9mkL9Y2wrKkpA] update_mapping [LiferayDocumentType]
[2021-05-05T13:16:29,534][INFO ][o.e.c.m.MetadataCreateIndexService] [es-node1] [liferay-20099] creating index, cause [api], templates [], shards [1]/[1]
[2021-05-05T13:16:29,535][INFO ][o.e.c.r.a.AllocationService] [es-node1] updating number_of_replicas to [0] for indices [liferay-20099]
[2021-05-05T13:16:29,662][INFO ][o.e.c.r.a.AllocationService] [es-node1] Cluster health status changed from [YELLOW] to [GREEN] (reason: [shards started [[liferay-20099][0]]]).
[2021-05-05T13:16:29,722][INFO ][o.e.c.m.MetadataMappingService] [es-node1] [liferay-20099/214vtbkjTkmW_VCR_xOqyQ] update_mapping [LiferayDocumentType]
[2021-05-05T13:16:29,785][INFO ][o.e.c.m.MetadataMappingService] [es-node1] [liferay-20099/214vtbkjTkmW_VCR_xOqyQ] update_mapping [LiferayDocumentType]
```

In the Control Panel, navigate to Configuration &rarr; Search and verify the
Elasticsearch connection is active.

![Figure x: An active connection is displayed in the Search administrative panel.](../../../../images/elasticsearch-7-connection.png)

| **Important:** Re-index your search indexes and spell check indexes. Invoke
| both of these actions in the Index Actions tab of Control Panel &rarr;
| Configuration &rarr; Search.
