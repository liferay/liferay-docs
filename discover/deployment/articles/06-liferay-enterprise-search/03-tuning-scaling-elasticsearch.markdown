# Tuning and Scaling Elasticsearch [](id=tuning-and-scaling-elasticsearch)
Search engines benefit heavily from caching and their JVM memory profiles are substantially different from a JVM focused on serving content and web views (e.g., Liferay JVM). For these reasons, the two applications should always be kept separate in production environments.
The following sections provide a synopsis of Elasticsearch configurations. Prior to deployment, we strongly recommend reading Elastic’s documentation on production deployment in [Elasticsearch – the Definitive Guide](https://www.elastic.co/guide/en/elasticsearch/guide/current/index.html) .


## JVM [](id=jvm)
In general, 45% of the available system memory should be allocated to Elasticsearch, up to a maximum of 31GB. In general, no other JVM settings should be adjusted within Elasticsearch.
You should configure heap sizing by setting the environment variable: ES_HEAP_SIZE. The JVM vendor and version used for the Elasticsearch server must be identical to the version used for Liferay Digital Enterprise.


## File System [](id=file-system)
You should configure your OS for at least 64,000 file descriptors. The default Linux value is 1024.
Elasticsearch also uses NioFS and MMapFS. Consequently, you must ensure there is sufficient virtual memory available for memory-mapped files.
Please consult your system administrator on how to configure these values.

## Scaling Your Deployment [](id=scaling-your-deployment)
For deployments with less than 1000 users, you may choose to deploy the Elasticsearch JVM and the Digital Enterprise JVMs on the same physical or virtual machines. With this configuration, the Elasticsearch and Liferay Digital Enterprise JVMs will share the same CPU and memory resources.
Consequently, you should ensure there are at least 8 vCPU/cores and 16GB of memory allocated.
You must take care to properly monitor resources to avoid over-allocating between the Liferay Digital Enterprise and Elasticsearch JVMs.
For most deployments, Liferay recommends deploying 2 Elasticsearch server with at least 4 vCPU/cores.
This deployment is sufficient if you are not exceeding 50GB index size. The Elasticsearch JVMs should be configured to hold with one (1) index shard and one (1) index replica.

If you are planning a more search intensive site (e.g., 50000 users with 250000 documents), Liferay recommends moving to a three (3) server cluster. In this 3-server configuration, you should look to have 2 Elasticsearch JVMs on each server: a "master-eligible" JVM and a "data" JVM. A "master-eligible" JVM handles clustering operations, including determine which JVM holds which shard. A "data" JVM hold the shards containing the indexed documents.

A dedicated "master-eligible" Elasticsearch JVM would only require 1GB of heap. A dedicated "data" JVM should be sized according to previously provided guidelines.
As the amount of indexed data and number of search requests grow, you may add additional data nodes while using the following guidelines:
1. Each shard should not exceed 50GB
2. Each data JVM or data + master JVM should not exceed 31GB heap.
Please keep in mind that you should only use these guidelines for initial planning. You must perform tests to ensure you have the optimal configurations.
