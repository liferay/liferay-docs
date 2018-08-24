# Tuning Elasticsearch [](id=tuning-elasticsearch)

Since search engines benefit heavily from caching, their JVM memory profiles are 
substantially different from those of a JVM focused on serving content and web 
views (e.g., a JVM running @product@). In production environments, search 
engines and @product@ should always be on separate JVMs. 

The following sections provide a synopsis of Elasticsearch configurations. Prior 
to deployment, we strongly recommend reading 
[Elastic's documentation](https://www.elastic.co/guide/en/elasticsearch/guide/current/index.html) 
on production deployment. 

You'll learn how to configure these settings: 

- JVM
- File System
- Scale

## JVM [](id=jvm)

The JVM vendor and version must be the same for the Elasticsearch server and the
@product@ server. In general, you should allocate 45 percent of the available
system memory to Elasticsearch, up to a maximum of 31 GB. Configure heap sizing
by setting the `ES_HEAP_SIZE` environment variable.

## File System [](id=file-system)

Configure your operating system for at least 64,000 file descriptors (the
default Linux value is 1024). Since Elasticsearch uses NioFS and MMapFS, ensure
there is sufficient virtual memory available for memory-mapped files. Consult
your system administrator for information on how to configure these values. 

## Tuning and Scaling an Elasticsearch Cluster [](id=tuning-and-scaling-an-elasticsearch-cluster)

Proper scaling and tuning of an Elasticsearch cluster primarily depends on the
type of indexes it holds and how they're intended to be used. Since @product@ is
a flexible development platform, no two applications index and search for data
in exactly the same way. Read the 
[definitive Elasticsearch guide](https://www.elastic.co/guide/en/elasticsearch/guide/master/distributed-cluster.html),
and understand the differences between 
[indexing-intensive applications](https://www.elastic.co/guide/en/elasticsearch/reference/master/tune-for-indexing-speed.html)
and 
[search-intensive applications](https://www.elastic.co/guide/en/elasticsearch/reference/master/tune-for-search-speed.html).
Then you'll be able to predict usage patterns for your @product@ indexes and
design the optimally scaled and tuned cluster.

Once you determine the appropriate number of shards and replicas, configure them
in the Liferay Connector to Elasticsearch module, using these settings:

- `indexNumberOfReplicas` corresponds to Elasticsearch's `number_of_replicas`
    property.
- `indexNumberOfShards` corresponds to Elasticsearch's `number_of_shards`
    property.

Tune, scale, and prosper.
