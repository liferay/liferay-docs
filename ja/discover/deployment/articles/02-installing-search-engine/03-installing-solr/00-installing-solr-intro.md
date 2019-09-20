# Solrのインストール[](id=installing-solr)

SolrはApache Luceneをベースにしたエンタープライズ検索プラットフォームです。信頼性、拡張性、耐障害性にすぐれた検索エンジンとして広く使われています。詳細は[こちら](http://lucene.apache.org/solr/)を参照してください。

[Elasticsearch](/discover/deployment/-/knowledge_base/7-1/configuring-elasticsearch-for-liferay-0)は@product@に組み込まれている検索エンジンですが、Solrを使用することも可能です。以前のバージョンの@product@でSolrを使用している場合、またはデプロイメントシステム(例：ご使用のOSまたはJVM)で[Elasticsearchがサポートされていない場合](https://www.elastic.co/support/matrix)、@product@の検索エンジンとしてSolrも利用できます。

場合によっては、SolrではなくElasticsearchの使用が好ましい場合もあります。
詳細は[こちら](/discover/deployment/-/knowledge_base/7-1/installing-a-search-engine#choosing-a-search-engine)を参照してください。

@product-ver@は、Liferay Connector to Solr 7コネクタを介してSolr 7.5.xをサポートします。
