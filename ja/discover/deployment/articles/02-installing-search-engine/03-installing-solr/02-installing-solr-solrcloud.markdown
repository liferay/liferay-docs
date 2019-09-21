---
header-id: high-availability-with-solrcloud
---

# Sorlのインストール：高可用性クラスターでSolrCloudを使用する

Solrサーバーにクラスタリングが必要な場合、SolrCloudを使用します。
SolrCloudを本番環境で使用する場合は、[外部 ZooKeeper](https://cwiki.apache.org/confluence/display/solr/Setting+Up+an+External+ZooKeeper+Ensemble)アンサンブル構成をセットアップすることを推奨します。
[ZooKeeper](http://zookeeper.apache.org/)はSolrCloudクラスターのような分散システムの管理をする集中型コーディネーションサービスです。

この記事で紹介するSolrCloud設定手順は必要最小限にしています。具体的に、この記事は１つのマシンでSolrCloudを設定する方法で説明していますが、本番環境においては複数の物理/仮想マシンを使います。また、ここに記載してある手順は*Solr 7のインストールと設定*ドキュメントの前半部分を既に確認していることを前提にしています。[SolrCloudガイド](https://cwiki.apache.org/confluence/display/solr/SolrCloud)を参考にしてください。

1. Solrサーバーが起動している場合は停止します。


2. Solr_Home/configsetsへ移動し、下記のファイルを作成します。

       liferay_configs
   
3. `Solr_Home/liferay`にある`conf`フォルダを、手順２で作成した`liferay_configs`にコピーします。

   `configset/liferay_configs`にはSolrCloud @product@ コレクションの構成が含まれており、ZooKeeperにアップロードされます。先程設定した`liferay`サーバーから`conf`フォルダをコピーすることで、Liferay Solr Adapterから配布される`schema.xml`、`solrconfig.xml`ファイルを使用します。

4. インタラクティブ SolrCloudセッションを開始して、下記のコマンドを使用してSolrCloudクラスタリングを起動します。

       ./bin/solr -e cloud

5. セットアップウィザードを完了します。下記の手順は２ノードクラスタリングの作成を説明しています。

   - ノード数として`2`を入力します。

   - `8983`、`7574`(デフォルト)ポートを指定します。どちらのノードもログに記録されたコマンドで開始されます。

          Starting up Solr on port 8983 using command:
          "bin/solr" start -cloud -p 8983 -s "example/cloud/node1/solr"
      
   - コレクションに*liferay*と名前をつけます。

   - コレクションを２つシャードに分割します。

   - 各シャードに２つのレプリカを指定します。

   - 設定を選択するように指示されたら、*liferay_configs*を入力します. クラスターが起動したら、以下のようなログが表示されます。

          SolrCloud example running, please visit http://localhost:8983/solr
      
これでliferayという新しいコレクションが、ローカルSolrCloudクラスター内に作成されました。

これまでの*ステータス*の確認をするのに以下のコマンドを入力します。

    ./bin/solr status

ログ出力は次のようになります。

    Found 2 Solr nodes:
    
    Solr process 16989 running on port 8983
    INFO  - 2018-08-06 13:54:17.665; org.apache.solr.util.configuration.SSLCredentialProviderFactory; Processing SSL Credential Provider chain: env;sysprop
    {
    "solr_home":"/home/russell/liferay-bundles/solr-7-dxp/solr-7.4.0/example/cloud/node1/solr",
    "version":"7.4.0 9060ac689c270b02143f375de0348b7f626adebc - jpountz - 2018-06-18 16:55:13",
    "startTime":"2018-08-06T17:52:01.519Z",
    "uptime":"0 days, 0 hours, 2 minutes, 16 seconds",
    "memory":"68.5 MB (%14) of 490.7 MB",
    "cloud":{
    "ZooKeeper":"localhost:9983",
    "liveNodes":"2",
    "collections":"1"}}
    
    
    Solr process 17127 running on port 7574
    INFO  - 2018-08-06 13:54:18.507; org.apache.solr.util.configuration.SSLCredentialProviderFactory; Processing SSL Credential Provider chain: env;sysprop
    {
    "solr_home":"/home/russell/liferay-bundles/solr-7-dxp/solr-7.4.0/example/cloud/node2/solr",
    "version":"7.4.0 9060ac689c270b02143f375de0348b7f626adebc - jpountz - 2018-06-18 16:55:13",
    "startTime":"2018-08-06T17:52:11.987Z",
    "uptime":"0 days, 0 hours, 2 minutes, 6 seconds",
    "memory":"56.4 MB (%11.5) of 490.7 MB",
    "cloud":{
    "ZooKeeper":"localhost:9983",
    "liveNodes":"2",
    "collections":"1"}}

SolrCloudモードで起動中のSolrを停止するには、下記の「*終了*」コマンドを使用します。

    bin/solr stop -all

## SolrCloudのSolrコネクタ設定

残りの設定は、LiferayのSolrコネクタでClient Typeを*CLOUD*として指定するのみです。

1. LiferayのSolrコネクタで*Client Type*を*CLOUD*として指定します。

       clientType="CLOUD"
   
2. 起動していない場合は、@product@を起動します。

![図1：Solr 7のSystem Settingsエントリから、_Client Type_を_Cloud_に設定します


<!-- +$$$
UNCOMMENT WHEN WRITTEN
**Note:** For a complete list of settings available in the Solr connector, see the
Solr Settings reference
article(/discover/reference/-/knowledge_base/7-1/solr-settings).

$$$ -->

これで@product@にSolrを設定する準備が整いました。DXPのデフォルトのサーチエンジンはElasticsearchであるため、Solrの使用が必須な場合やSolrを熟知してるという場合以外は、検索エンジンとしてElasticsearchの使用をご検討ください。
