---
header-id: installing-elasticsearch-6-1
---

# Elasticsearch 6.1のインストール

[TOC levels=1-4]

**注**： Elasticsearch 6.1.xは@product-ver@でサポートされている検索エンジンですが、サポートされている最新バージョンではありません。初めてElasticsearchをインストールする場合は、[デプロイメントガイドのはじめ](/docs/7-1/deploy/-/knowledge_base/d/installing-elasticsearch)にあるElasticsearch 6.5.xのインストール手順に従うことを検討してください。


@product@はElasticsearchを使用してそのコンテンツを索引付けします。デフォルトでは、埋め込みサービスとしてインストールされています。本番環境の運用ではサポートしていません。 テスト環境、開発環境での使用は問題ありませんが、本番環境に移行する際には、Liferayとは別の仮想/物理サーバー上にElasticsearchを設定する必要があります。 インフラ設計において拡張性など様々な利点があります。Elasticsearch用に物理/仮想サーバーを専有で割り当てられる場合は、検索エンジンのサーバリソースを @product@から独立させることで、パフォーマンスの向上に寄与します。予算上の制約がある場合は、 Liferayとは別JVMを利用することでパフォーマンスを向上できます。

Liferay DXPへのElasticsearchインストールは、以下の６つのステップで完了します。

1. @product@に付属しているElasticsearchのバージョンを確認して、Elasticのウェブサイトから該当のバージョンをダウンロードします。

2. アーカイブを稼働させるフォルダ内にElasticsearchを解凍、インストールします。

3. 必要なElasticsearch プラグインをインストールします。

4. Elasticsearchクラスタ名を決めます。

5. @product@をElasticseachクラスターに接続する設定をします。

6. @product@を再起動して、インデックスを作成します。

**注**：続行する前に、[`JAVA_HOME`環境変数](https://docs.oracle.com/cd/E19182-01/820-7851/inst_cli_jdk_javahome_t/)を設定したことを確認してください 。
複数のJDKがインストールされている場合は、Elasticsearchと@product@が同じバージョンとディストリビューションを使用していることを確認してください（例：Oracle Open JDK 1.8.0_201）。
これは`[Elasticsearch Home]/bin/elasticsearch.in.sh`で指定できます。:
|
|         JAVA_HOME=/path/to/java
|
| サポートされているJDKディストリビューションとバージョンの詳細については、 [Elasticsearch互換性マトリックス](https://www.elastic.co/support/matrix#matrix_jvm) と [Liferay DXP互換性マトリックス](https://web.liferay.com/documents/14/21598941/Liferay+DXP+7.1+Compatibility+Matrix/9f9c917a-c620-427b-865d-5c4b4a00be85)を参照してください。

以下の手順で設定後、本番環境用の@product@インスタンスが作成されます。

### ステップ１: ELASTICSEARCHの使用バージョンを探す

@product@が起動していない場合は起動してください。

ローカルホストのポート 9200から Embedded モードのElasticsearchにアクセスします。

    http://localhost:9200

JSONドキュメントは下記のようなJSONレスポンスが表示されます。

    {
    "name" : "g0m223N",
    "cluster_name" : "LiferayElasticsearchCluster",
    "cluster_uuid" : "Ii6STs04Tg-XzTVV5h7M2Q",
    "version" : {
    "number" : "6.1.3",
    "build_hash" : "af51318",
    "build_date" : "2018-01-26T18:22:55.523Z",
    "build_snapshot" : false,
    "lucene_version" : "7.1.0",
    "minimum_wire_compatibility_version" : "5.6.0",
    "minimum_index_compatibility_version" : "5.0.0"
    },
    "tagline" : "You Know, for Search"
    }

`"number"`フィールドにある数値がElasticsearchのバージョンです。
上記の例では 6.1.3です。

必要な Elasticsearchが分かったら、[Elastic](https://www.elastic.co)のウェブサイトに行って該当するバージョンをダウンロードします。

### ステップ２: ELASTICSEARCHのインストール

ここの設定によって、Elasticsearchの起動場所が決定されます。 @product@を同じサーバー上で起動させるか、独立したサーバーで起動させるかは、利用可能なリソースとインストールのサイズが重要になります。いずれの方法でも個別に調整可能な検索システムを構築できます。

正しいバージョンのElasticsearchを取得したら、起動させるサーバ上で圧縮ファイルを解凍します。これで設定が完了しました。

### ステップ２はこれで完了です。

必要なElasticsearch プラグインをインストールします。

- `analysis-icu`
- `analysis-kuromoji`
- `analysis-smartcn`
- `analysis-stempel`

このプラグインをインストールするにはElasticsearch Homeへ行き、以下を入力します。

    ./bin/elasticsearch-plugin install [plugin-name]

*[plugin-name]*にElasticsearch プラグイン名を入力します。

### ステップ４: ELASTICSEARCHクラスタの名前指定

Elasticsearch内の*クラスタ*は、シャードのクラスタ名で認識されるノード（サーバー）の集合体です。これらのノードがデータ及びワークロードを分散します。ここでは１ノードクラスターでの設定を説明していますが、複数ノードのクラスター構築に関しては [Elasticの書類](https://www.elastic.co/guide/index.html)を参照ください。

Elasticsearchのインストールが完了すると、[Elasticsearch Home]という名前のフォルダに配置されます。クラスタに名前を付けるには、Elasticsearchと@product@の両方で指定します。まずは Elasticで指定する為に、下記のファイルを編集します。

    [Elasticsearch Home]/config/elasticsearch.yml

cluster.nameの部分をアンコメントします。
お好きな名前を入力します。

    cluster.name: LiferayElasticsearchCluster

この時指定する名前は`覚えやすい名前`に指定することを推奨します。ファイルを保存します。

Elasticsearchを起動します。ご使用のオペレーティングシステムの`[Elasticsearch Home]/bin `フォルダーから実行するファイルを起動します

    ./elasticsearch

Elasticが起動したら、ステータスメッセージに下記のようなトランスポートアドレスが記載されます。

    [2018-04-03T15:34:19,784][INFO ][o.e.t.TransportService   ] [g0m223N] publish_address {127.0.0.1:9300}, bound_addresses {[::1]:9300}, {127.0.0.1:9300}

@product@サーバーがネットワーク内でElasticを検知できるようにこのアドレスは必ず記録をしておきます。

### ステップ５: @product@をELASTICクラスターに接続

次のステップは@product@の設定です。Liferay DXPを起動し、ログインをし、 [Control Panel] > [Configuration] > [System Settings] > [Search]とページを辿り、*Elasticsearch ６*を検索バーで探し、設定のリストから*Elasticsearch 6*をクリックします。設定をします。下記のオプションを変更します。

**Cluster Name**: Elasticsearchで指定した通りにクラスター名を入力します。

**Operation Mode**: デフォルトではEMBEDDEDになっています。 REMOTEに設定して独立した Elasticsearchに接続します。

**Transport Addresses:** Elasticノード用にトランスポートアドレスの制限リストを入力します。ここで起動した Elasticサーバーのトランスポートアドレスを入力します。デフォルト値は`localhost:9300`です。このデフォルト値でも稼働します。

完了したら、*保存*をクリックします。

### ステップ６: @product@の再起動、インデックスの再構築

@product@を一度停止して、再起動します。再起動できたら、管理ユーザーとしてログインして、[Control Panel] > [Configuration] > [Search] へ行き、[Reindex all search indexes(インデックスの再構築)]の *Execute*をクリックします。その後、Elasticseachログにメッセージが表示されるはずです。

詳細については、[Elasticsearchインストールガイド](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/_installation.html)を参照してください。

