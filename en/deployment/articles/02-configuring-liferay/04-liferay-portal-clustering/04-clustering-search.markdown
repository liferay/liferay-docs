---
header-id: clustering-search
---

# Clustering Search

[TOC levels=1-4]

Search should always run on a separate environment from your @product@ server.
@product@ supports 
[Elasticsearch](/docs/7-2/deploy/-/knowledge_base/d/installing-elasticsearch), 

<!--Uncomment when Solr adapters is available. 
or 
Solr 
and either of those environments
-->

which can also be clustered. 

For more information on how to cluster Elasticsearch, see 
[Elasticsearch's distributed cluster setup](https://www.elastic.co/guide/en/elasticsearch/guide/current/distributed-cluster.html). 

Once @product@ servers have been properly configured as a cluster and the same
for Elasticsearch, change @product@ from *embedded* mode to *remote* mode. On
the first connection, the two sets of clustered servers communicate with each
other the list of all IP addresses; in case of a node going down, the proper
failover protocols enable. Queries and indexes can continue to be sent for all
nodes.

<!-- Uncomment when Solr adapter is available. 
For more information on how to cluster Solr, see 
[Apache Solr Cloud](https://cwiki.apache.org/confluence/display/solr/SolrCloud)
documentation. 

Once @product@ servers have been properly configured as a cluster, deploy the
Liferay Solr 5 Adapter on all nodes. (This app is available for download from
Liferay Marketplace
[here](https://web.liferay.com/marketplace/-/mp/application/78803899).) Create a
Solr Cloud (cluster) managed by _Apache Solr Zookeeper_. Connect the @product@
cluster to Zookeeper and finish the final configurations to connect the two
clusters.
-->
