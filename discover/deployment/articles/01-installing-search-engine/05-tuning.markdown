# Tuning Elasticsearch

Since search engines benefit heavily from caching, their JVM memory profiles are 
substantially different from those of a JVM focused on serving content and web 
views (e.g., a JVM running @product@). In production environments, search 
engines and @product@ should therefore always be on separate JVMs. 

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

Configure your Operating System for at least 64,000 file descriptors (the
default Linux value is 1024). Since Elasticsearch uses NioFS and MMapFS, ensure
there is sufficient virtual memory available for memory-mapped files. Consult
your system administrator for information on how to configure these values. 

## Scaling Your Deployment [](id=scaling-your-deployment)

For deployments with less than 1,000 users, you may choose to deploy the 
Elasticsearch JVM and @product@ JVM on the same physical or virtual machines. 
With this configuration, the JVMs share the same CPU and memory resources. You 
should therefore allocate at least 8 vCPUs/cores and 16 GB of memory. You must 
also properly monitor resources to avoid over-allocating between the @product@ 
and Elasticsearch JVMs. For most deployments, we recommend deploying two 
Elasticsearch servers with at least four vCPUs/core. This is sufficient if the 
search index doesn't exceed 50 GB. The Elasticsearch JVMs should be configured 
to hold one index shard and one index replica.

If you are planning a more search-intensive site (e.g., 50,000 users with 
250,000 documents), we recommend a three-server cluster. In such a configuration, 
you should have two Elasticsearch JVMs on each server: a *master-eligible* JVM and 
a *data* JVM. A master-eligible JVM handles clustering operations. This includes 
determining which JVM holds which shard. A data JVM holds the shards that 
contain the indexed documents. 

A dedicated master-eligible Elasticsearch JVM requires only 1 GB of heap. You 
should size a dedicated data JVM according to the 
[guidelines](https://www.elastic.co/guide/en/elasticsearch/reference/current/modules-node.html) 
in Elastic's documentation. 
 
As the amount of indexed data and number of search requests grow, you may add
additional data nodes as follows: 

1. Each shard should not exceed 50 GB.
2. Each data JVM or data and master JVM should not exceed 31 GB heap.

While these guidelines are useful for initial planning, you must perform tests
to ensure optimal configuration of your Elasticsearch and @product@ servers. 

