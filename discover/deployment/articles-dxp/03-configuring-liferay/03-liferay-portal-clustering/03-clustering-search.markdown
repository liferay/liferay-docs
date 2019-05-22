# Clustering Search [](id=clustering-search)

Search should always run on a separate environment from your @product@ server.
@product@ supports 
[Elasticsearch](/discover/deployment/-/knowledge_base/7-1/installing-elasticsearch), 
and 
[Solr](/discover/deployment/-/knowledge_base/7-1/installing-solr). 
Both can also be clustered. 

For more information on how to cluster Elasticsearch, see 
[Elasticsearch's distributed cluster setup](https://www.elastic.co/guide/en/elasticsearch/guide/current/distributed-cluster.html). 

Once @product@ servers have been properly configured as a cluster and the same
for Elasticsearch, change @product@ from *embedded* mode to *remote* mode. On
the first connection, the two sets of clustered servers communicate with each
other the list of all IP addresses; in case of a node going down, the proper
failover protocols enable. Queries and indexes can continue to be sent for all
nodes.

For more information on how to cluster Solr, see 
[Apache Solr Cloud](https://cwiki.apache.org/confluence/display/solr/SolrCloud)
documentation. 

Once @product@ servers have been properly configured as a cluster, deploy the
Liferay Connector to Solr on all nodes. (This app is available for download
from Liferay Marketplace) Create a Solr Cloud (cluster) managed by _Apache Solr
Zookeeper_. Connect the @product@ cluster to Zookeeper and finish the final
configurations to connect the two clusters.
