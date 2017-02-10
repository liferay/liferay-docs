# Preparing to Install Elasticsearch [](id=liferay-elasticsearch)

By default, @product-ver@ and its embedded Elasticsearch engine run in the same 
JVM. Although this enables out-of-the-box search in @product-ver@, it's only 
supported for development; production use isn't supported. For production use, 
Liferay only supports Elasticsearch when it runs in a separate JVM, because
search engines benefit heavily from caching. This makes their JVM memory
profiles differ substantially from those of a JVM running @product-ver@.
Therefore, the two applications should always be kept separate in production
environments. 

The following sections provide a synopsis of Elasticsearch configurations for 
@product-ver@. Prior to deployment, we strongly recommend reading 
[Elastic's documentation on production deployment](https://www.elastic.co/guide/en/elasticsearch/guide/current/index.html). 

## Sizing Your Deployment [](id=sizing-your-deployment)

When sizing your Elasticsearch deployment, you must carefully consider your CPU, 
memory, disk, and network capacity. Generally, you should deploy Elasticsearch
on medium to large machines. This lets you scale effectively and avoid large
numbers of machines. You should also avoid running multiple Elasticsearch JVMs
on the same operating system. 

## CPU [](id=cpu)

Liferay recommends that you allocate at least 4 CPU cores to the Elasticsearch 
engine. This assumes only 1 Elasticsearch JVM running on the machine. 

## Memory [](id=memory)

Liferay recommends at least 16 GB of memory, with 64 GB preferred. The memory
allocation depends upon the amount of index data. For index sizes 500 GB to 1
TB, 64 GB of memory should suffice. 

## Disk [](id=disk)

Search engines store their indexes on disk. Disk I/O capacity can therefore 
impact search performance. Liferay recommends deploying Elasticsearch on SSD 
when possible. If you are unable to do this, use high-performance traditional
hard disks (e.g., 15k RPM). Consider using RAID 0 for both SSD and traditional
hard disks. 

In general, avoid using NAS (network attached storage) for Elasticsearch as the
network overhead can be large. If you're using public cloud infrastructure like
Amazon Web Services, use instance local storage and avoid network storage like
Elastic Block Store (EBS). 

Also note that search index sizes vary based on the indexed content. Ensure you
have at least 25% more disk capacity than the total size of your indexes. For
example, if your index is 50 GB, you should have at least 75 GB of disk space
available. To estimate the disk space you need, Liferay recommends that you
index a representative sample of your production content and then multiply the
size of that sample index by the fraction of your production content that it
represents. For example, index 25% of your production content and then multiply
the resulting index size by 4. Keep in mind that indexing a 1 MB file doesn't
result in 1 MB of space in the search index. 

## Networking [](id=networking)

Elasticsearch relies on clustering and sharding to deliver fast, accurate search 
results. Therefore, it requires a fast and reliable network. Most modern data 
centers provide 1 GbE or 10 GbE between machines. Avoid spreading Elasticsearch
clusters across multiple data centers. Elasticsearch doesn't support multi-data
center deployments, especially data centers spread across large distances (e.g.,
cross-continent). To support multi-data center deployments, you must create a
custom solution that distributes index requests (update, delete, add document)
to each data center. 

## Shards and Replicas [](id=shards-and-replicas)

Elasticsearch uses shards and replicas to scale. Shards divide a search index 
into smaller, more manageable chunks. For example, if you have a 500 GB index 
you can split it into 10 shards of 50 GB each. For best results, each shard 
shouldn't exceed 50 GB. More shards generally mean faster indexing (write) 
performance but slower search (read) performance. In Elasticsearch, an index 
with multiple shards results in a distributed search and a subsequent result 
merge. Replicas provide resiliency and improve search performance. A replica 
helps to load balance search operations across the cluster. 
