# Liferay Enterprise Search Monitoringのインストール
 [](id=installing-liferay-enterprise-search-monitoring)

X-Pack Monitoringを使ってElasticsearchを監視します。まず Elasticsearch上にX-Packをインストールし（詳細[install X-Pack onto Elasticsearch](discover/deployment/-/knowledge_base-7-1/installing-x-pack))、X-Packのセキュリティ機能を使用している場合は、セキュリティを設定します。上記のことが完了したら、X-Packで保護されているElasticsearchとKibana、および@product@が簡単かつ安全に通信できるように、以下のX-PackでKibana（監視サーバー）をインストール、および構成する手順に従ってください。この統合を行うには、(Premiumに含まれている）Liferay Enterprise Search Standardサブスクリプション契約が必要です。詳細はこちらまでお問い合わせください：[Liferay's Sales department for more information](https://www.liferay.com/contact-us#contact-sales)

1. データ収集を有効にするようにElasticsearchに指示します。



2. Kibanaをダウンロードしてインストールします。



3. 適切なセキュリティ設定でKibanaを設定してください。



4. [Liferay Connector to X-Pack Monitoring](https://web.liferay.com/marketplace/-/mp/application/106163750) [Elastic Stack 6.x]をインストールします。



5. Elasticsearchと通信できるようにコネクタを設定します。



このドキュメントでは、セキュリティ*および*監視を有効にしていることを前提としていますが、プロセスの違いについては必要に応じて注記されています。



X-Packのインストール手順については、[X-Pack security article](/discover/deployment/-/knowledge_base-7-1/installing-x-pack-security)を参照してください。



Elasticsearchでデータ収集を有効にすることから始めます。

## データ収集を有効にする
[](id=enable-data-collection)

Elasticsearchのモニタリングはデフォルトで有効になっていますが、データ収集は有効になっていません。
以下を`elasticsearch.yml`に追加してデータ収集を有効にします。


    xpack.monitoring.collection.enabled: true

それができたらKibanaをインストールしてください。



## Kibanaをインストール
[](id=install-kibana)

必ず正しいバージョンのKibanaをインストールしてください。詳細については、[Liferay Enterprise Search compatibility matrix](https://web.liferay.com/group/customer/dxp/support/compatibility-matrix/enterprise-search)を確認してください。



1. Kibanaをダウンロードして（詳細：[Download Kibana](https://www.elastic.co/downloads/kibana)）解凍します。ルートフォルダの名前は*Kibana Home*です。



2. ElasticsearchのURLを`kibana.yml`で設定して、Kibanaにモニタリングデータの送信先を指示します。



       elasticsearch.url: "http://localhost:9200"
   
   ElasticsearchでSSLが有効になっている場合は、`https`のURLです。



3. X-Packセキュリティを使用していない場合は、Kibana Homeへのコマンドプロンプトを開き、次のコマンドを入力してKibanaを起動します：



       ./bin/kibana
   
ElasticsearchサーバーでX-Packのセキュリティ機能を使用している場合は、Kibanaを起動する前に追加の設定が必要です。

### 認証付きのKibanaを設定する
[](id=configure-kibana-with-authentication)

X-PackがElasticsearchクラスターにアクセスするために認証を必要とする場合は、次の手順に従うか[Elastic's documentation](https://www.elastic.co/guide/en/kibana/6.5/monitoring-xpack-kibana.html)を参照してください。



1.  `[Kibana
Home]/config/kibana.yml`に組み込み`kibana`ユーザーのパスワードをに設定します。



       elasticsearch.username: "kibana"
       elasticsearch.password: "liferay"
   
   X-Packセットアップに`kibana`のユーザーパスワードを使用してください。Kibanaをインストールしたら、*Management*ユーザーインターフェイスから組み込みのユーザーパスワードを変更できます。



2. Elasticsearchクラスターとの通信を暗号化していない場合は、KibanaホームからKibanaを起動してください。

       ./bin/kibana
   
3. `localhost:5601`に移動して、`kibana_user`がある[user](https://www.elastic.co/guide/en/x-pack/6.5/native-realm.html#native-add)としてサインインできることを確認してください。

   [役割](https://www.elastic.co/guide/en/x-pack/6.5/built-in-roles.html)またはスーパーユーザー（`elasticユーザ`のような）。

### 暗号化を使用したKibanaの設定
 [](id=configuring-kibana-with-encryption)

X-PackがElasticsearchクラスターとの通信を暗号化する場合は、次の手順に従ってKibanaを設定してください。詳細については[Elastic's guide](https://www.elastic.co/guide/en/kibana/6.5/using-kibana-with-security.html#using-kibana-with-security)を参照してください。

以下の設定を`kibana.yml`に追加します：

    xpack.security.encryptionKey: "xsomethingxatxleastx32xcharactersx"
    xpack.security.sessionTimeout: 600000
    
    elasticsearch.ssl.verificationMode: certificate
    elasticsearch.url: "https://localhost:9200"
    elasticsearch.ssl.certificateAuthorities: [ "/path/to/ca.crt" ]
    
    server.ssl.enabled: true
    server.ssl.certificate: /path/to/[Elasticsearch Home]/config/localhost.crt
    server.ssl.key: /path/to/[Elasticsearch Home]/config/localhost.key

クラスター環境での監視およびセキュリティのベストプラクティスの詳細については、[Elastic's documentation](https://www.elastic.co/guide/en/x-pack/6.5/secure-monitoring.html)を参照してください 。

この手順の後、`https://localhost:5601`でKibanaにアクセスし、Kibanaのユーザーでサインインすることができます。最後の手順は、Kibanaを@product@に接続することです。

## Liferay ConnectorからX-Pack Monitoringへの設定方法 [](id=configuring-the-liferay-connector-to-x-pack-monitoring)

Liferay Enterprise Search（プレミアムまたはスタンダード）サブスクリプション契約をお持ちの場合は、Liferay Connector to X-Pack Monitoring[[Elastic Stack 6.x]]をダウンロードしてください。まずILPKGファイルを`Liferay Home/deploy`フォルダにコピーしてインストールします。



1. コネクタがインストールされ、KibanaとElasticsearchが安全に設定されたら、以下の名前の[configuration file](/discover/portal/-/knowledge_base/7-1/understanding-system-configuration-files) を作成します。<segment 0155>

       com.liferay.portal.search.elasticsearch6.xpack.monitoring.web.internal.configuration.XPackMonitoringConfiguration.config
   
2. 以下の設定を`.config`ファイルに配置します：

       kibanaPassword="liferay"
       kibanaUserName="elastic"
       kibanaURL="http://localhost:5601"
   
   値はKibanaの設定によって異なります。X-Pack Security機能を使用している場合、`kibanaURL="https://localhost:5601"`のような安全なURLを使ってください。



   または、[System Settings](/discover/portal/-/knowledge_base/7-1/system-settings)からモニタリングアダプタを設定します。
*[Control Panel]*>*[Configuration]*>*[System Settings]*へ移動し、検索カテゴリーを使ってX-Pack Monitoringの項目を見つけてください。モニタリングコネクタのすべての設定オプションがそこに表示されます。



3. この設定ファイルを`Liferay Home/osgi/configs`にデプロイすると、実行中のインスタンスが設定を適用します。サーバーを再起動する必要はありません。



4. Kibana自体に追加する設定がさらに2つあります。１つ目の設定では、接頭辞に`server.basePath`と付いた要求をKibanaが書き換えることを妨げます。２つ目の設定では、監視ポートレットのKibanaの基本パスを、Kibanaの監視UIのプロキシとして機能するように設定します。以下を`kibana.yml`に追加してください：

       server.rewriteBasePath: false
       server.basePath: "/o/portal-search-elasticsearch-xpack-monitoring/xpack-monitoring-proxy"
   
   しかし一度 `server.basePath`を設定すると、KibanaのURL（例えば`https://localhost:5601`）からKibanaのUIにアクセスできなくなります。Kibana UIへのアクセスはすべてMonitoringポートレットを介して行われます。これはログインした@product@ユーザーにのみアクセス可能です。以下のURLを使用してポートレットに直接移動します：

   [http://localhost:8080/o/portal-search-elasticsearch-xpack-monitoring/xpack-monitoring-proxy/app/monitoring](http://localhost:8080/o/portal-search-elasticsearch-xpack-monitoring/xpack-monitoring-proxy/app/monitoring)

5. @product@のMonitoringポートレットをKibanaのUIへのプロキシとして使用しているため、X-Pack Securityを使用している場合、有効な*truststore*と*password*を認識するようにアプリケーションサーバーの起動JVMパラメータを設定する必要が あります。

   まず、Elasticsearch Homeに移動し、X-Packセキュリティの設定時に作成したCAからPKSC＃12証明書を生成します。

       ./bin/elasticsearch-certutil cert --ca-cert /path/to/ca.crt --ca-key /path/to/ca.key --ip 127.0.0.1 --dns localhost --name localhost --out /path/to/Elasticsearch_Home/config/localhost.p12
   
   次に`keytool`コマンドを使用してトラストストアを生成します。

       keytool -importkeystore -deststorepass liferay -destkeystore /path/to/truststore.jks -srckeystore /path/to/Elasticsearch_Home/config/localhost.p12 -srcstoretype PKCS12 -srcstorepass liferay
   
   アプリケーションサーバーの立ち上げ時JVMパラメータにトラストストアパスとパスワードを追加します。以下はTomcatサーバーの`CATALINA_OPTS`に追加するためのトラストストアとパスのパラメーターの例をです：

       -Djavax.net.ssl.trustStore=/path/to/truststore.jks -Djavax.net.ssl.trustStorePassword=liferay
   
@product@とKibanaを再起動してください。

## @product@での監視[](id=monitoring-in-product)


KibanaとX-Packが正常にインストールされて設定され、すべてのサーバーが稼働したら、X-Pack Monitoringポートレットをページに追加します。



1. *[Add]*メニューを開き、*[Widgets]*を選択します。



2.  *monitoring*を検索し、*X-Pack Monitoring*ウィジェットを「検索」カテゴリからページにドラッグします。



[monitoring Elasticsearch](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/es-monitoring.html)については、Elasticのドキュメンテーションを参照してください 。
