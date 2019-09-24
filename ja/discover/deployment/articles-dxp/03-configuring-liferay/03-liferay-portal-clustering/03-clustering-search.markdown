---
header-id: clustering-search-ja
---

# クラスタリング検索

[TOC levels=1-4]

検索エンジンは必ず@product@とは別のサーバーで起動してください。
@product@は [Elasticsearch](/discover/deployment/-/knowledge_base/7-1/installing-elasticsearch-ja)と [Solr](/discover/deployment/-/knowledge_base/7-1/installing-solr-ja)をサポートしています。
両方ともクラスタ環境で使用することが可能です。

Elasticsearchをクラスター化する方法の詳細については、[Elasticsearch's distributed cluster setup](https://www.elastic.co/guide/en/elasticsearch/guide/current/distributed-cluster.html)を参照してください 。


@product@サーバーのクラスタ環境が正常に構築でき、Elasticsearchも同じようにクラスタ構成ができた時点で、@product@を*embedded*から*remote*モードに変更します。最初の接続では、2セットのクラスタリングされたサーバーが相互にリスト上の全IPアドレスに通信します。これは全ノードがダウンした時に、適切なフェールオーバーが有効になるためです。クエリとインデックスは全てのノードに対して引き続き送信されます。

Solrをクラスター化する方法について詳しくは、[Apache Solr Cloud](https://cwiki.apache.org/confluence/display/solr/SolrCloud)の資料を参照してください。



@product@サーバーがクラスターとして正しく構成されたら、Liferay ConnectorをすべてのノードのSolrにデプロイします。（このアプリはLiferay Marketplaceからダウンロードできます）_Apache Solr Zookeeper_が管理するSolrクラウド（クラスター）を作成します。@product@クラスターをZookeeperに接続し、2つのクラスタを接続するための最終設定を完了します。
