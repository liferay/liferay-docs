# Tuning and Scaling Elasticsearch [](id=tuning-and-scaling-elasticsearch)

Since search engines benefit heavily from caching, their JVM memory profiles are 
substantially different from those of a JVM focused on serving content and web 
views (e.g., a JVM running @product@). In production environments, search 
engines and @product@ should therefore always be on separate JVMs. 

The following sections provide a synopsis of Elasticsearch configurations. Prior 
to deployment, we strongly recommend reading Elastic’s documentation on 
production deployment. 
[Click here](https://www.elastic.co/guide/en/elasticsearch/guide/current/index.html) 
to find it. 

First, you'll learn how you should configure the JVM that runs Elasticsearch. 

## JVM [](id=jvm)

In general, you should allocate 45% of the available system memory to 
Elasticsearch, up to a maximum of 31 GB. You shouldn't need to adjust any other 
JVM settings for Elasticsearch. You should configure heap sizing by setting 
the `ES_HEAP_SIZE` environment variable. Also, note that the JVM vendor and 
version for the Elasticsearch server must be identical to the version used for 
@product@. 
<!-- 
If you don't need to adjust any other settings, then why does it go on to list 
other settings (e.g., ES_HEAP_SIZE, and the JVM vendor and version. Also, do 
both the JVM vendor and version have to match that of the JVM that runs DXP, or 
just the version?) 
-->

## File System [](id=file-system)

You should configure your OS for at least 64,000 file descriptors (the default 
Linux value is 1024). Elasticsearch also uses NioFS and MMapFS. You must 
therefore ensure there is sufficient virtual memory available for memory-mapped 
files. Consult your system administrator for information on how to configure 
these values. 

## Scaling Your Deployment [](id=scaling-your-deployment)

For deployments with less than 1,000 users, you may choose to deploy the 
Elasticsearch JVM and @product@ JVM on the same physical or virtual machines. 
With this configuration, the JVMs share the same CPU and memory resources. You 
should therefore allocate at least 8 vCPU/cores and 16 GB of memory. You must 
also properly monitor resources to avoid over-allocating between the @product@ 
and Elasticsearch JVMs. For most deployments, we recommend deploying 2 
Elasticsearch servers with at least 4 vCPU/cores. This is sufficient if the 
search index doesn't exceed 50 GB. The Elasticsearch JVMs should be configured 
to hold with 1 index shard and 1 index replica. 
<!-- Is vCPU/cores supposed to be vCPU/core, as in vCPU per core? -->

If you are planning a more search-intensive site (e.g., 50,000 users with 
250,000 documents), we recommend a 3-server cluster. In such a configuration, 
you should have 2 Elasticsearch JVMs on each server: a *master-eligible* JVM and 
a *data* JVM. A master-eligible JVM handles clustering operations. This includes 
determining which JVM holds which shard. A data JVM holds the shards that 
contain the indexed documents. 

A dedicated master-eligible Elasticsearch JVM requires only 1 GB of heap. You 
should size a dedicated data JVM according to previously provided guidelines. As 
the amount of indexed data and number of search requests grow, you may add 
additional data nodes according to these guidelines: 

1. Each shard should not exceed 50 GB.
2. Each data JVM or data and master JVM should not exceed 31 GB heap.

Keep in mind that you should only use these guidelines for initial planning. You 
must perform tests to ensure optimal configurations. 
