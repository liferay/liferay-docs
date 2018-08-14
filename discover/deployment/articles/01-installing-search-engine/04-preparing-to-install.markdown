# Preparing to Install Elasticsearch

By default, @product-ver@ and its embedded Elasticsearch engine run in the same
JVM. Although this enables out-of-the-box search, it's only supported for
development. For production use, Elasticsearch must run in a separate JVM.
Because search engines benefit heavily from caching, their JVM memory profiles
differ substantially from those of a JVM running @product@. Therefore, the two
applications should always be kept separate in production environments. 

The following sections provide a synopsis of Elasticsearch configurations for 
@product-ver@. Prior to deployment, we strongly recommend reading 
[Elastic's documentation on production deployment](https://www.elastic.co/guide/en/elasticsearch/guide/current/index.html). 

## Sizing Your Deployment [](id=sizing-your-deployment)

When sizing your Elasticsearch deployment, carefully consider CPU, memory, disk,
and network capacity. To scale effectively and avoid using lots of machines,
deploy Elasticsearch on medium to large machines (for example, machines with two
to eight CPUs). Avoid running multiple Elasticsearch JVMs on the same operating
system. 

## CPU [](id=cpu)

We recommend allocating at least eight total CPU cores to the Elasticsearch
engine, assuming only one Elasticsearch JVM is running on the machine. 

## Memory [](id=memory)

At least 16 GB of memory is recommended, with 64 GB preferred. The precise
memory allocation required depends on how much data is indexed. For index sizes
500 GB to 1 TB, 64 GB of memory will suffice. 

## Disk [](id=disk)

Search engines store their indexes on disk, so disk I/O capacity can impact
search performance. Deploy Elasticsearch on SSD whenever possible. Otherwise use
high-performance traditional hard disks (for example, 15k RPM). In either case,
consider using RAID 0.

Avoid using Network Attached Storage (NAS) whenever possible as the network
overhead can be large. If you're using public cloud infrastructure like Amazon
Web Services, use instance local storage instead of network storage, such as
Elastic Block Store (EBS). 

Maintain 25 percent more disk capacity than the total size of your indexes. If
your index is 60 GB, make sure you have at least 75 GB of disk space available.
To estimate the disk space you need, you can index a representative sample of
your production content, and multiply that size the fraction of your production
content that it represents. For example, index 25 percent of your production
content and then multiply the resulting index size by four. Just keep in mind
that indexing a 1 MB file doesn't result in 1 MB of disk space in the search
index. 

## Cluster Size

While @product@ can work with an Elasticsearch cluster comprised of one or two
nodes, the minimum cluster size recommended by Elastic for fault tolerance is
three nodes.

## Networking [](id=networking)

Elasticsearch relies on clustering and sharding to deliver fast, accurate search
results, and thus requires a fast and reliable network. Most modern data centers
provide 1 GbE or 10 GbE between machines. 

Elasticsearch doesn't support multi-data center deployments.

<!-- EXCLUDING FOR NOW: NOT IN 7.1 DEPLOYMENT CHECKLIST ## Shards and Replicas [](id=shards-and-replicas)

Elasticsearch uses shards and replicas to scale. Shards divide a search index 
into smaller, more manageable chunks. For example, if you have a 500 GB index 
you can split it into 10 shards of 50 GB each. For best results, each shard 
shouldn't exceed 50 GB. More shards generally mean faster indexing (write) 
performance but slower search (read) performance. In Elasticsearch, an index 
with multiple shards results in a distributed search and a subsequent result 
merge. Replicas provide resiliency and improve search performance. A replica 
helps to load balance search operations across the cluster.  -->
