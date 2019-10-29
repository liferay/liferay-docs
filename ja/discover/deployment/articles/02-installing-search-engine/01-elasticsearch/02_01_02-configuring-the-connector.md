---
header-id: configuring-the-liferay-elasticsearch-connector
---

# Liferay Elasticsearchコネクタの設定

[TOC levels=1-4]

Elasticsearchの設定情報の詳細については、[Elasticsearchのドキュメンテーション](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/settings.html)を参照してください 。

Elasticsearchのクラスター名は重要です。Elasticsearchをリモートモードで実行している場合、@product@はElasticsearchクラスターを認識するためにクラスター名を使用するからです。@product@側でElasticsearchクラスター名を設定する方法については、以下のLiferay Elasticsearchコネクタの設定というセクションを参照してください。

**注：**Elasticsearchの`http.enabled`設定は、Elasticsearch 6へのLiferayコネクタというアプリケーションの`httpEnabled`設定に対応します。
この設定は Elasticsearch 6.3で廃止されたため、コネクタの対応する設定も廃止予定です。この設定は、組み込みElasticsearchサーバーの設定にのみ使用されていたため、廃止による本番環境でのデプロイメントへの影響はわずかなはずです。

Elasticsearchの設定ファイルは[YAML](http://www.yaml.org)で書かれていて、`[Elasticsearch Home]/config`フォルダに保存されています。メイン設定ファイルは`elasticsearch.yml`で、Elasticsearchモジュールを設定するために使用されます。

Elasticsearchクラスター名を設定するには、`[Elasticsearch Home]/config/elasticsearch.yml`を開き、以下を指定します。

    cluster.name: LiferayElasticsearchCluster

`LiferayElasticsearchCluster`がクラスターに使われているデフォルトの名前なので、このままで起動が可能です。もちろん、クラスターには好きな名前を付けることができます（例：`clustery_mcclusterface`）。<sup>[1](#footnote1)</sup>ノード名を同じシンタックス（`node.name`プロパティ）を使って設定することも可能です。

設定ファイルよりもコマンドラインから作業する場合は、Elasticsearch Homeに行き、次のように入力します。

    ./bin/elasticsearch --cluster.name clustery_mcclusterface --node.name nody_mcnodeface

ノード名とクラスター名を変更することも可能です。Elasticsearchの設定が完了したら、次は起動です。 

## Elasticsearchを起動する

Elasticsearchを起動するために、Elasticsearch Homeへ行って、次のどちらかを入力します。

    ./bin/elasticsearch

Linuxをご使用の場合は上記を入力してください。

    \bin\elasticsearch.bat

Windowsの場合は上記を入力してください。

Daemonとしてバックグラウンドで実行する場合は、以下のように上記のお使いのコマンドに`-d`を追加します。

    ./bin/elasticsearch -d

Elasticsearchと@product@の両方がインストールされて実行されたら、統合します。

## Liferay Elasticsearchコネクタの設定

Elasticsearchコネクタは、Elasticsearchとポータル間の統合を提供します。コネクタを設定する前に、Elasticsearchが実行されていることを確認してください。

アダプターの設定には２通りあります。

1. [Control Panelのシステム設定アプリケーションを使用する](#configuring-the-adapter-in-the-control-panel)

2. [OSGi設定ファイルをマニュアルで作成する](#configuring-the-adapter-with-an-osgi-config-file)

Elasticsearchアダプターは[システム設定]から手早く設定できますが、それが可能なのは開発時と試験運用時のみです。システム設定に関する詳細は [こちら](/docs/7-1/user/-/knowledge_base/u/system-settings)の資料で説明していますので参照ください。[システム設定]を設定し、その`.config`ファイルを設定と一緒にエクスポートすることで、他のシステムをデプロイするための設定ファイルを生成できます。

### コントロールパネルでアダプターを設定 

システム設定アプリケーションからElasticsearchアダプターを設定するには、

1. @product@を起動します。

2. [コントロールパネル ] > [設定] > [システム設定] > [Foundation]へ移動します。

3. *「Elasticsearch」*を検索し(スクロールして検索するか、サーチボックスで検索) 、操作アイコン（![操作](../../../images/icon-actions.png)）をクリックして、*編集*をクリックします。 

   ![図1: @product@のコントロールパネルにあるシステム設定アプリケーションでElasticsearchアダプターを設定します。](../../../images/cfg-elasticsearch-sys-settings.png)

4. 設定を編集して、*保存*をクリックします。

   ![図2: 運用モードを*リモート*にするなどのElasticsearchコネクタの設定をします。](../../../images/cfg-elasticsearch-sys-settings2.png)

**注**：操作モードを切り替える（`EMBEDDED`→ `REMOTE`）場合は、再インデックスを起動する必要があります。*[コントロールパネル]*→*[設定]*→*[検索]*の順に移動し、*[検索で利用するインデックスを再構築する]*の横にある*[実行]*をクリックします。

### OSGi`.config`ファイルでアダプターを設定する

本番環境でのデプロイメントのためにシステムを準備するときには、繰り返しが可能なデプロイプロセスを使用します。したがって、設定が管理されたソースによって維持されている場合は、OSGi設定ファイルを使うのが最善です。

OSGi設定ファイルを使って Elasticsearchアダプターを以下の手順で設定します：

1. 以下のファイルを作成します：

       [Liferay_Home]/osgi/configs/com.liferay.portal.search.elasticsearch6.configuration.ElasticsearchConfiguration.config

2. `propertyName="Value"`形式でファイルに設定を追加します。例えば、

       operationMode="REMOTE"
       # If running Elasticsearch from a different computer:
       #transportAddresses="ip.of.elasticsearch.node:9300"
       # Highly recommended for all non-prodcution usage (e.g., practice, tests, diagnostics):
       #logExceptionsOnly="false"

3. @product@を起動します。既に実行している場合は、インデックスを再生成します。

Elasticsearchの[システム設定]エントリからわかるように、最適なパフォーマンスになるようにシステムを調整するのに役立つ、設定オプションが多数あります。
<!-- For a detailed accounting of these, refer to the reference article
on [Elasticsearch Settings](discover/reference/-/knowledge_base/7-1/elasticsearch-settings).
-->

このページで書かれている内容は、推奨されるElasticsearchのクラスタリングの設定方法です。しかしながら、これらは手動でのチューニング、負荷テスト、そして再度のチューニングのプロセスに代わるものではありません。
<!--
[settings](discover/reference/-/knowledge_base/7-1/elasticsearch-settings) 
as well as the -->
そのため、設定完了後に[Elasticsearchのドキュメンテーション](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/important-settings.html)を参照の上、設定を確認してからプロセスを実行することをお勧めします。 

## Elasticsearchリモートホストの設定

本番環境システムでは、Elasticsearchと@product@は別々のサーバーにインストールされています。別々のサーバにインストールされているElasticsearchを@product@側で検知させるためには、次のように設定します。


    transportAddresses=[IP address of Elasticsearch Node]:9300

以下はElasticsearchクラスターの2つのノードのIPアドレスを設定する例です。

    transportAddresses=["192.168.1.1:9300","192.168.1.2:9300"]

これをElasticsearchコネクタのOSGi設定ファイルに設定します。このプロパティにElasticsearchノードを必要な数だけ書き込みます。これにより、@product@に検索要求が送信されるべきIPアドレスまたはホスト名が伝えられます。システム設定を使用している場合は、*Transport Addresses*プロパティで値を設定します。

**注： **Elasticsearchクラスターでは、複数のElasticsearchノードのトランスポートアドレスを`transportAddresses`プロパティ内のコンマ区切りリストとして一覧表示できます。もしトランスポートアドレスが一つのみの場合、そのノードが故障した際に@product@はElasticsearchと通信できなくなります。 

Elasticsearch側では、`elaticsearch.yml`ファイル内の`network.host`プロパティを設定します。このプロパティは、*バインドホスト*（Elasticsearchが要求を待機するホスト）と*公開ホスト*（Elasticsearchが他のノードとの通信に使用するホスト名またはIPアドレス）の両方を同時に設定します。詳細は[こちら](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/modules-network.html)で確認できます。 

## リモートオペレーションモード時のElasticsearchのクラスタリング 

Elasticsearchのクラスタリングは簡単です。まず、`node.max_local_storage_nodes`を`1`より大きなものに設定します。Elasticsearchの起動スクリプトを実行すると、新しいローカルストレージノードがクラスターに追加されます。例えば、ローカルで４ノードを実行したい場合は`./bin/elasticsearch`を４回実行します。詳細は[こちら](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/modules-node.html#max-local-storage-nodes)をご覧ください。

Elasticsearch 6アダプターでシャードとレプリカの数を設定します。`indexNumberOfShards`と`indexNumberOfReplicas`プロパティーを使用して、プライマリシャードとレプリカシャードの数をそれぞれ指定します。
Elasticsearchのデフォルト設定では最高で10ノードのクラスターまで稼働します。デフォルトのシャード数は`5`で、レプリカシャードのデフォルト数は`1`です。 

**注：**Elasticsearchは[Zen Discovery Module](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/modules-discovery-zen.html)をデフォルトで使用します。このモジュールはユニキャストディスカバリを提供しています。加えて、クラスタコミュニケーション内のノードは、TCPを通して[Transportモジュール](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/modules-transport.html)を使用しています。`elasticsearch.yml`ファイルを設定するプロパティついてはElasticsearchの資料を参照してください。また、アダプターの使用可能な設定については@product@ Elasticsearchアダプターの設定を確認してください。
| <!--reference article](discover/reference/-/knowledge_base/7-1/elasticsearch-settings)-->
|
| 少なくとも、次の場所`host:port`で`elasticsearch.yml`のユニキャスト検出中にゴシップルーターとして機能するホストのリストを提供します:
|
|     discovery.zen.ping.unicast.hosts: ["node1.ip.address", "node2.ip.address"]
|
| 例えば,
|
|     discovery.zen.ping.unicast.hosts: ["10.10.10.5", "10.10.10,.5:9305"]

Elasticsearchクラスター設定の詳細は、[Elasticsearchのインデックス設定](https://www.elastic.co/guide/en/elasticsearch/guide/current/_index_settings.html)をご覧ください。

## Elasticsearchコネクタのシステム設定：運用モード

Elasticsearchコネクタで使用可能な設定の一部は、1つの操作モード（REMOTEまたはEMBEDDED）にのみ適用されます。以下の表を参照してください。

| アダプタ設定/動作モード | EMBEDDED | REMOTE |
------------------------------| :----: | :----: |
| `clusterName` | x | x |
| `operationMode` | x | x |
| `indexNamePrefix` | x | x |
| `indexNumberOfReplicas*` | x | x |
| `indexNumberOfShards*` | x | x |
| `bootstrapMlockAll` | x | \- |
| `logExceptionsOnly` | x | x |
| `retryOnConflict` | x | x |
| `discoveryZenPingUnicastHostsPort` | x | \- |
| `networkHost` | x | \- |
| `networkBindHost` | x | \- |
| `networkPublishHost` | x | \- |
| `transportTcpPort` | x | \- |
| `transportAddresses` | \- | x |
| `clientTransportSniff` | \- | x |
| `clientTransportIgnoreClusterName` | \- | x |
| `clientTransportPingTimeout*` | \- | x |
| `clientTransportNodesSamplerInterval` | \- | x |
| `httpEnabled` | x | \- |
| `httpCORSEnabled` | x | \- |
| `httpCORSAllowOrigin` | x | \- |
| `httpCORSConfigurations` | x | \- |
| `additionalConfigurations` | x | x |
| `additionalIndexConfigurations` | x | x |
| `additionalTypeMappings` | x | x |
| `overrideTypeMappings` | x | x |
| `syncSearch` | x | \- |

\*** 注：**Elasticsearch 6へのコネクタでのみ利用可能です。

<a name="footnote1">1</a> This is, of course, a nod to all those fans of [Boaty Mcboatface](http://www.theatlantic.com/international/archive/2016/05/boaty-mcboatface-parliament-lessons/482046).

