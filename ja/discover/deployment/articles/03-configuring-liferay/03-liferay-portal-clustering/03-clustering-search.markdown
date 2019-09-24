---
header-id: clustering-search-ja
---

# クラスタリング検索

[TOC levels=1-4]

検索エンジンは、必ず@product@とは別のサーバーで起動してください。
@product@は [Elasticsearch ](/discover/deployment/-/knowledge_base/7-1/installing-elasticsearch-ja)をサポートしており、

<!--Uncomment when Solr adapters is available. 
or 
[Solr](discover/deployment/-/knowledge_base/7-1/using-solr), 
and either of those environments
-->

クラスタ環境で使用することが可能です。

Elasticsearchをクラスタ化する方法の詳細については、「[Elasticsearch’s distributed cluster setup](https://www.elastic.co/guide/en/elasticsearch/guide/current/distributed-cluster.html)」を参照してください。

@product@サーバーのクラスタ環境が正常に構築でき、Elasticsearchも同じようにクラスタ構成ができた時点で、@product@を*embedded*から*remote*モードに変更します。最初の接続では、2セットのクラスタリングされたサーバーが相互にリスト上の全IPアドレスに通信します。これは全ノードがダウンした時に、適切なフェールオーバーが有効になるためです。クエリとインデックスは全てのノードに対して引き続き送信されます。

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
