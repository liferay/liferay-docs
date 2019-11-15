---
header-id: installing-a-search-engine
---

# 検索エンジンのインストール

[TOC levels=1-4]

検索エンジンは、@product@インストールの重要な構成要素です。ここにいる場合は、おそらく基本をすでに知っているので、Liferay DXP展開用の検索エンジンを構成する必要があります。

@product@には、高度なスケーラビリティーを持つ、全文検索エンジンであるElasticsearchが同梱されています。
Elasticsearchは十分にサポートされており、あらゆる検索や索引作業のニーズをほぼ確実に満たすことができます。デプロイ設定については、スタンドアロンまたはリモートのElasticsearchサーバー、あるいはクラスターを設定する方法を[以下にて](/docs/7-1/deploy/-/knowledge_base/d/installing-elasticsearch)紹介します。

[Solr](http://lucene.apache.org/solr)は@product@でもサポートされています。

## 検索エンジンの選択

ElasticsearchとSolrはどちらもサポートされていますが、@product@との動作方法にはいくつかの違いがあります。場合によっては、Elasticsearchを選択する必要があります。

以下の質問で両方とも_「はい」_と答えた場合は、Elasticsearchを選択する必要があります。

1. Liferayの[コマースソリューション](/web/commerce/documentation/-/knowledge_base/1-0/getting-started)、@commerce@を使用している。

2. カスタム検索コードのために、ElasticsearchへのLiferayコネクタに実装されている`TermsSetFilter`APIまたはGeolocation APIを使用する必要がある。

@commerce@は`TermsSetFilter`がElasticsearchコネクタで起動可能である必要があるため、@commerce@を使用している場合は、Elasticsearchを使用する必要があります。

これらのElasticsearch専用の開発者向け機能は、現在SolrへのLiferayコネクタには実装されていませんが、将来追加される可能性があります。
検索ソリューションのコードでこれらの機能のいずれかを使用する必要がある場合は、Elasticsearchを使用してください。@commerce@を使用している場合は、Elasticsearchを使用してください。
それ以外の場合は、ポータルコンテンツの索引付けにElasticsearchまたはSolrのどちらかを使用してください。

サーチエンジンの選択で考慮すべきもう一つの要因はJDKのバージョンです。検索エンジンと@product@は、同じJDKのバージョンとディストリビューションを使用する必要があります（たとえば、Oracle Open JDK 1.8.0_201）。サポートされているJDKディストリビューションとバージョンの詳細については、[Elasticsearch互換性マトリックス](https://www.elastic.co/support/matrix#matrix_jvm)と[@product@互換性マトリックス](https://web.liferay.com/documents/14/21598941/Liferay+DXP+7.1+Compatibility+Matrix/9f9c917a-c620-427b-865d-5c4b4a00be85)を参照してください。サーバー間でJVMレベルのシリアライゼーションが行われないため、この考慮事項はSolrには必要ありません。すべての通信はHTTPレベルで行われます。

