---
header-id: elasticsearch-logging
---

# Elasticsearchロギング

[TOC levels=1-4]

@product@を初めて起動すると、組み込みのElasticsearchサーバーが起動し、すぐに検索が機能します。組み込み検索エンジンは本番環境には適していませんが、テストと開発には役立ちます。

Fix Pack 3およびCE GA2では、組み込みElasticsearchのINFOレベルのElasticsearchログを削除することにより、Liferayログがスリム化されました。WARNおよびERRORログは引き続き表示されます。起動時にElasticsearchからの応答がない場合は、組み込みのElasticsearchサーバーのINFOログレベルを有効にできます。

**注：**これらの手順は、組み込みのElasticsearchサーバーのログを調整する方法を示しています。@product@の検索機能のロギングは、[コントロールパネル] &rarr; [設定] &rarr; [Server Administration] &rarr; [Log Levels]ツールの[[Log Levels]](/docs/7-1/user/-/knowledge_base/u/server-administration)画面で設定できます。_com.liferay.portal.search_を検索して、検索クラスとパッケージのみを含めるようにリストを絞り込みます。
リモートのElasticsearchサーバーのロギングを調整するには、[Elasticのドキュメント](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/logging.html)を参照してください。

デフォルトで表示_される_ログメッセージの例を次に示します。

    2018-09-13 16:49:24.442 WARN  [Elasticsearch initialization thread][EmbeddedElasticsearchConnection:315]

Server Administrationで`EmbeddedElasticsearchConnection`のログレベルを管理します。

デフォルトでは表示_されない_Elasticsearchログメッセージの例を次に示します。

    [2018-09-05T17:25:30,107][WARN ][o.e.d.i.m.MapperService  ] [unmapped_type:string] should be replaced with [unmapped_type:keyword]

`o.e.d.i.m.MapperService`およびその他のElasticsearchクラスのロギングを調整するには、

1. `Liferay Home/data/elasticsearch6/`に`config`フォルダを作成します。

2. 新しいフォルダに`log4j2.properties`ファイルを作成します。

3. INFOレベルのロギングを有効にするには、`log4j2.properties`ファイルに次のコンテンツを入力します。

       appender.console.layout.pattern=[%d{ISO8601}][%-5p][%-25c{1.}] %marker%m%n
       appender.console.layout.type=PatternLayout
       appender.console.name=console
       appender.console.type=Console
       logger.action.level=info
       logger.action.name=org.elasticsearch.action
       rootLogger.appenderRef.console.ref=console
       rootLogger.level=info
       status=error
   
   詳細については、Elasticsearchの[ロギングドキュメント](https://www.elastic.co/guide/en/elasticsearch/reference/6.x/logging.html)を参照してください。

4. @product@サーバーまたは組み込みのElasticsearchのみを再起動します。

5. 組み込みのElasticsearchサーバーのみを再起動するには、以下のファイルを作成します。

       com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration.config
   
   `Liferay Home/osgi/configs`ディレクトリに以下の値を入力します。

       blacklistBundleSymbolicNames="com.liferay.portal.search.elasticsearch6.impl"
   
6. `.config`ファイルを保存すると、バンドルが停止します。ログに`[STOPPED]`というメッセージが表示されたら、行をコメントアウトしてファイルを再保存し、バンドルを再起動します。

       #blacklistBundleSymbolicNames="com.liferay.portal.search.elasticsearch6.impl"
   
これが完了すると、組み込みのElasticsearchサーバーは、INFOレベル以上のElasticsearchログを表示します。

スローログとJVMのガベージコレクションログの2つの一般的なElasticsearchログをさらに設定できます。

## スローログの構成

Elasticsearchのスローログについては[こちら](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/index-modules-slowlog.html)をご覧ください。

システム設定のElasticsearch 6エントリで、組み込みのElasticsearchサーバーにスローログを構成します。次の設定を[Additional Index Configurations]プロパティに追加します。

    index.indexing.slowlog.threshold.index.debug: 2s
    index.indexing.slowlog.threshold.index.info: 5s
    index.indexing.slowlog.threshold.index.trace: 500ms
    index.indexing.slowlog.threshold.index.warn: 10s
    
    index.search.slowlog.threshold.fetch.debug: 500ms
    index.search.slowlog.threshold.fetch.info: 800ms
    index.search.slowlog.threshold.fetch.trace: 200ms
    index.search.slowlog.threshold.fetch.warn: 1s
    
    index.search.slowlog.threshold.query.debug: 2s
    index.search.slowlog.threshold.query.info: 5s
    index.search.slowlog.threshold.query.trace: 500ms
    index.search.slowlog.threshold.query.warn: 10s

これらはサンプル値です。必要に応じて調整してください。

## JVMガベージコレクションのロギングの構成

スローログの構成と同様に、ElasticsearchのJVMガベージコレクションのロギングは、システム設定のElasticsearch 6エントリで調整可能です。次の設定を[Additional Configurations]プロパティに追加します。

    monitor.jvm.gc.enabled: true
    monitor.jvm.gc.overhead.debug: 40
    monitor.jvm.gc.overhead.info: 70
    monitor.jvm.gc.overhead.warn: 90

これらはサンプル値です。必要に応じて調整してください。

