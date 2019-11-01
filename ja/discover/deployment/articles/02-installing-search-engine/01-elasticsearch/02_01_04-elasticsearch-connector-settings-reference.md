---
header-id: elasticsearch-connector-settings-reference
---

# Elasticsearch コネクタの設定：リファレンス

[TOC levels=1-4]

Elasticsearchは@product-ver@のデフォルトの検索エンジンです。*Liferay Foundation*スイートは*Elasticsearch 6へのLiferay コネクタ
*と呼ばれるElasticsearch用のアダプターを含んでいます。アダプターは、[システム設定]または`com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration.cfg`と呼ばれる`[Liferay_Home]/osgi/configs`へデプロイされたOSGi設定ファイルを介して設定することができます。

以下のリストは、LiferayのデフォルトのElasticsearchアダプターのすべての設定を、_[システム設定]_のアプリケーション（_[検索]_カテゴリーの下にあるElasticsearch 6エントリー）に表示される順序で示しています。


`clusterName=LiferayElasticsearchCluster`：統合するクラスターの名前を設定するString値です。運用モードがリモートに設定されている場合、この名前はリモートクラスターと一致する必要があります。（参照：リモートオペレーションモード）

`operationMode=EMBEDDED`：EMBEDDEDまたはREMOTEの二つの運用モードから選ぶことができます。リモートのスタンドアロンElasticsearchクラスターに接続するには、REMOTEに設定します。内部のElasticsearchインスタンスでLiferayを起動するには、EMBEDDEDに設定します。Embedded運用モードは、本番環境ではサポートされていません。

`indexNamePrefix=liferay-`：サーチインデックス名の接頭辞として使用するString値を設定してください。デフォルト値は、通常の状態では変更しないでください。これを変更した場合は、ポータルに対して*すべてのインデックスの再構築*操作を実行してから、Elasticsearch管理コンソールを使用して古いインデックスを手動で削除する必要があります。


`indexNumberOfReplicas=` 各インデックスのレプリカ数を設定します。設定しない場合、レプリカは使用されません。
変更を有効にするには、完全なインデックスの再構築が必要です。

`indexNumberOfShards=` Liferayインデックスを作成するときに使用するインデックスシャードの数を設定します。設定しない場合は、単一のシャードが使用されます。変更を有効にするには、完全なインデックスの再構築が必要です。

`bootstrapMlockAll=false`：`true`に設定されている場合に、プロセスアドレス空間をRAMにロックしようとしするブール値の設定です。Elasticsearchメモリがスワップアウトされるのを防ぎます（詳細は[こちら](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/setup-configuration-memory.html#bootstrap-memory_lock)を参照 ）。

`logExceptionsOnly=true`： trueに設定されている場合に、Elasticsearchからの例外のみをログに記録し、それらを再スローしないブール値の設定です。

`retryOnConflict=5`：ドキュメントの取得と更新の間にドキュメントが更新されたことでバージョンの競合が発生した場合に、試行する再試行回数のint値を設定します（詳細は[こちら](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/docs-update.html#_parameters_3)を参照 ）。

`discoveryZenPingUnicastHostsPort=9300-9400`：discovery.zen.ping.unicast.hostsの値を構築するときに使用するポートの範囲にString値を設定します。ある範囲のポートにある複数のElasticsearchノードは、同じコンピューターでゴシップルーターとして機能することができます（詳細は[こちら](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/modules-discovery-zen.html)を参照）。

`networkHost=`：このString値を設定して、このホスト名またはIPアドレスにバインドし、このホストをクラスター内の他のノードに公開（アドバタイズ）するようにノードに指示します。
これは、バインドホストと公開ホストを同時に設定するショートカットです（詳細については、[こちら](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/modules-network.html#common-network-settings)を参照）。

`networkBindHost=`：着信要求を待機するために、ノードがバインドする必要があるネットワークインタフェースのString値を設定します（詳細は[こちら](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/modules-network.html#advanced-network-settings)を参照 ）。

`networkPublishHost=`：ノードがクラスター内の他のノードにアドバタイズする単一のインタフェースのString値を設定して、それらのノードが接続できるようにします（詳細は[こちら](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/modules-network.html#advanced-network-settings)を参照）。

`transportTcpPort=`：ノード間の通信用にバインドするポートのString値を設定してください。
単一の値または範囲を受け入れます（詳細は[こちら](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/modules-transport.html#_tcp_transport)を参照）。

`transportAddresses=localhost:9300`：接続先のリモートElasticsearchノードのアドレスのためのString値を設定してください。運用モードがリモートに設定されている場合、この値は必須です（詳細については[こちら](https://www.elastic.co/guide/en/elasticsearch/client/java-api/6.5/transport-client.html) を参照）。適切と思われる数のノードを指定します。

`clientTransportSniff=true`：クラスターのスニッフィングを有効にし、クラスター内の使用可能なデータノードを動的に見つけるには、このブール値をtrueに設定します（詳細は[こちら](https://www.elastic.co/guide/en/elasticsearch/client/java-api/6.5/transport-client.html)を参照）。

`clientTransportIgnoreClusterName=false`：接続されているノードのクラスター名の検証を無視するには、このブール値をtrueに設定します（詳細は[こちら](https://www.elastic.co/guide/en/elasticsearch/client/java-api/6.5/transport-client.html)を参照）。

`clientTransportPingTimeout=`クライアントノードがノードからのping応答を待つ時間（秒）。設定しない場合は、デフォルトのElasticsearch `client.transport.ping_timeout`が使用されます。

`clientTransportNodesSamplerInterval=`：このString値を設定して、リストされており、かつ接続されているノードをサンプリング/ pingする頻度をクライアントノードに指示します（詳細は[こちら](https://www.elastic.co/guide/en/elasticsearch/client/java-api/6.5/transport-client.html)を参照）。

`httpEnabled=true`：REST要求を直接処理することを意図していないノード上でhttpレイヤーを完全に無効にするには、このブール値をfalseに設定します。この設定は Elasticsearch 6.3で廃止されたため、コネクタの対応する設定も廃止予定です。この設定は、組み込みElasticsearchサーバーの設定にのみ使用されていたため、廃止による本番環境でのデプロイメントへの影響はわずかなはずです。

`httpCORSEnabled=true`：オリジン間のリソース共有（つまり、別のオリジンのブラウザがElasticsearchへのリクエストを実行できるかどうか）を無効にするには、このブール値をfalseに設定します。無効にした場合、elasticsearch-headのようなWebフロントエンドツールは接続できないかもしれません（詳細は[こちら](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/modules-http.html#_settings_2)を参照）。

`httpCORSAllowOrigin=/https?:\\/\\/localhost(:[0-9]+)?/`：HTTP CORSが有効になっている場合に許可するように、文字列の起点を設定します（詳細は[こちら](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/modules-http.html#_settings_2)を参照）。

`httpCORSConfigurations=`：HTTP CORSのカスタム設定のString値をYMLフォーマット（elasticsearch.yml）で設定してください（詳細は[こちら](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/modules-http.html#_settings_2)を参照）。

`additionalConfigurations=`：埋め込みElasticsearchのカスタム設定のString値をYML形式で設定します。
参照先：Liferay Elasticsearchアダプターへの設定の追加

`additionalIndexConfigurations=`：Liferayインデックスのカスタム設定のString値をJSONまたはYML形式で設定します（詳細については、Elasticsearch Create Index APIを参照してください）。
参照先：Liferay Elasticsearchアダプターへの設定の追加

`additionalTypeMappings=`：`LiferayDocumentType`のカスタムマッピングのString値をJSON形式で設定します（詳細については、Elasticsearch Put Mapping APIを参照してください）。参照先：Liferay Elasticsearchアダプターへの設定の追加

`overrideTypeMappings=`ここでの設定は@product@のデフォルトの型マッピングを上書きします。
これは高度な機能であり、どうしても必要な場合にのみ使用します。この値を設定すると、@product@のソースコードでLiferayドキュメントタイプを定義するために使用されるデフォルトのマッピング（例：`liferay-type-mappings.json`）は完全に無視されるため、変更するセグメントだけではなく、マッピングの定義全体をこのプロパティに含めます。

`syncSearch=true`これを有効にした場合、検索はElasticsearchの検索スレッドプールではなく呼び出し元のスレッドで実行されます。

以下の設定はElasticsearch 6アダプターでのみ使用可能です。

## 組み込みElasticsearchサーバーにのみ影響を与える設定方法

これらの設定（上記で定義）は、組み込みElasticsearchサーバーを設定するときにのみ使用することを目的としています。これらを設定しても、リモートのElasticsearchインストールに影響はありません。

- `bootstrapMlockAll`
- `discoveryZenPingUnicastHostsPort`
- `networkHost`
- `networkBindHost`
- `networkPublishHost`
- `transportTcpPort`
- `httpEnabled`
- `httpCORSEnabled`
- `httpCORSAllowOrigin`
- `httpCORSConfigurations`
- `syncSearch`

システム設定アプリケーションでこれらの設定を簡単に設定できます。または、前述のように、デプロイ可能なOSGi `.config`ファイルでそれらを指定できます。
