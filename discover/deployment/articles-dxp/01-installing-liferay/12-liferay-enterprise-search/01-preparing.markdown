# Liferay ElasticSearch [](id=liferay-elasticsearch)
In Liferay Digital Enterprise, Liferay ships with an embedded Elasticsearch search engine (i.e., the Elasticsearch engine runs in the same JVM as Liferay Digital Enterprise). Although this solution is great for having out-of-the-box search in Liferay, it will not officially be supported by Liferay for production use, only for development. For production usage, Liferay will only support use of the Elasticsearch search engine running outside of the Digital Enterprise JVM (i.e. 1 JVM for Liferay Digital Enterprise and a separate JVM for the Elasticsearch search engine).

Search engines benefit heavily from caching and their JVM memory profiles are substantially different from a JVM focused on serving content and web views (e.g., Liferay JVM). For these reasons, the two applications should always be kept separate in production environments. 
The following sections provide a synopsis of Elasticsearch configurations. Prior to deployment,
we strongly recommend reading Elastic’s documentation on production deployment in ["Elasticsearch – the Definitive Guide."](https://www.elastic.co/guide/en/elasticsearch/guide/current/index.html)

## Sizing Your Deployment [](id=sizing-your-deployment)
When sizing your Elasticsearch deployment, you must carefully consider your CPU, memory, disk, and network capacity. As a general rule of thumb, you should strive to deploy Elasticsearch on medium to large machines  to scale effectively while avoiding large quantities of machines.
Also, you should avoid running multiple Elasticsearch JVMs on the same operating system.

## CPU [](id=cpu)
Liferay recommends at least 4 CPU cores allocated to the Elasticsearch engine. This assumes only 1 Elasticsearch JVM running on the machine.

## Memory [](id=memory)
Liferay recommends at least at least 16GB of memory. The preference is for 64GB of memory. The memory allocation depends upon the amount of index data. 64GB of memory should be sufficient for index sizes of 500GB to 1TB.

## Disk [](id=disk)
Search engines store their indices on disk and thus disk IO capacity can greatly impact search performance. Liferay recommends deploying Elasticsearch on SSD when possible. If you are unable to use SSD, Liferay recommends high performance disks (15k RPM drives). You should also consider using RAID 0 for both SSD and traditional hard disks.

In general, you should avoid using NAS (network attached storage) for Elasticsearch as the network overhead can be quite large. If you are using public cloud infrastructure like Amazon Web Services, this means you should rely upon instance local storage and avoid network storage like Elastic
Block Store (EBS). 

You must ensure you have at least 25% more disk capacity than the total size of your indices. For instance, if you have 50G of data to be indexed, you should plan for having at least 75GB disk space available. Index sizes will vary based on the indexed content.

To ensure you have sufficient disk space, Liferay recommends indexing a representative sample of your production content. For instance, indexing 25% of production content to determine index size and then multiplying index size by 4. It is important to keep in mind that indexing a 1MB PDF document does not translate into a 1MB document in the search index.

## Networking [](id=networking)
Elasticsearch relies upon clustering and sharding to deliver fast, accurate search results. Consequently, it relies upon a fast and reliable network. Most modern data centers provide 1GbE or 10GbE between machines. You should avoid spreading Elasticsearch clusters across multiple data centers. Elasticsearch does not support multi-data center deployments, especially data centers spread across large distances (e.g., cross continents). To support multi-data center deployments, you will need to create a custom solution that distributes index requests (update, delete, add document) to each data center.

## Shards and Replicas [](id=shards-and-replicas)
Elasticsearch uses shards and replicas to scale.
Shards are used to divide an index into more manageable chunks. For instance, if you have 500GB index, you may wish to split this into 10 shards. For best results, each shard should not exceed 50GB. More shards generally mean faster indexing (write) performance but slower search (read) performance. In Elasticsearch, an index with multiple shards will result in a distributed search and a subsequent result merge.
Replicas are used to provide resiliency and improve search performance. A replica will help load balance search operations across the cluster.
