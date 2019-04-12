# Tuning and Scaling Elasticsearch [](id=tuning-and-scaling-elasticsearch)

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

Read on to get Elasticsearch humming with @product@! 

## JVM [](id=jvm)

In general, you should allocate 45% of the available system memory to 
Elasticsearch, up to a maximum of 31 GB. You should configure heap sizing by 
setting the `ES_HEAP_SIZE` environment variable. Also, the JVM vendor and 
version for the Elasticsearch server must be identical to those of the 
@product@ server. 

## File System [](id=file-system)

You should configure your OS for at least 64,000 file descriptors (the default 
Linux value is 1024). Elasticsearch also uses NioFS and MMapFS. You must 
therefore ensure there is sufficient virtual memory available for memory-mapped 
files. Consult your system administrator for information on how to configure 
these values. 

## Scaling Your Deployment [](id=scaling-your-deployment)

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

Always must perform tests to ensure optimal configurations. 
