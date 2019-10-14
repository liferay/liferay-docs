---
header-id: enabling-cluster-link
---

# Cluster Linkを有効にする

[TOC levels=1-4]

Cluster Linkを有効にすると、分散キャッシュが自動的に有効になります。キャッシュは、同時に実行されている複数の@product@ノードに分散されます。Cluster Linkは[Ehcache](http://www.ehcache.org)の複製を行います。Ehcacheのグローバル設定は[`portal.properties`ファイル](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Ehcache)にあります。

デフォルトでは、Liferayはノード間でキャッシュされたエンティティをコピーしません。たとえば、１つのノード上でエンティティが削除または変更された場合、Cluster Link は他のノードに*削除*メッセージを送信して、ローカルキャッシュ内のこのエンティティを無効にします。別のノードでそのエンティティを要求すると、キャッシュ*ミス*が発生します。その後、エンティティはデータベースから取得され、ローカルキャッシュに置かれます。あるノードのローカルキャッシュに追加されたエンティティは、他のノードのローカルキャッシュにはコピーされません。
そのエンティティがキャッシュされていないノードで新しいエンティティを取得しようとすると、キャッシュ*ミス*が発生します。ミスにより、ノードはデータベースからエンティティを取得し、それをローカルキャッシュに格納します。

![図1：Liferay DXPのキャッシュアルゴリズムは効率的です。](../../../images/clustering-cache-efficient-algorithm.png)

Cluster Linkを有効にするには、以下のプロパティを`portal-ext.properties`に追加します:

    cluster.link.enabled=true

Cluster Linkは [JGroups](http://www.jgroups.org)に依存し、APIに通信するノードを提供します。Cluster Linkは、

- クラスタ内の全てのノードにメッセージ通信できます。

- 特定のノードへメッセージ通信できます。

- メソッドを呼び出し、全て、一部または特定のノードから値を所得します。

- 新しいノードを検知しノードの参加・離脱通知をします。

クラスタで@ portal @を起動すると、ログファイルのメッセージにクラスタの名前が表示されます（例:`cluster=liferay-channel-control`）

    -------------------------------------------------------------------
    GMS: address=oz-52865, cluster=liferay-channel-control, physical address=192.168.1.10:50643
    -------------------------------------------------------------------

Cluster Linkはノード間で1対多タイプの通信を提供をする拡張アルゴリズムが含まれています。JGroupsUDP マルチキャストではCluster Linkがデフォルトで実装されていますが、ユニキャストとTCPも利用可能です。

## UDPによるマルチキャスト

Cluster Linkを有効にすると、@product@のデフォルトのクラスタリング設定が有効になります。この設定はUDPを介したIPマルチキャストを定義します。@product@はこれを行うのに２つのグループの[JGroupsチャンネル](http://www.jgroups.org/manual/index.html#_channel)を使用します。一つはコントロールグループ、二つ目はトランスポートグループです。
チャンネルプロパティーをカスタマイズしたい場合は、`portal-ext.properties`内で設定します。

    cluster.link.channel.name.control=[your control channel name]
    cluster.link.channel.properties.control=[your control channel properties]

チャンネルプロパティーについては[JGroupsの資料](http://www.jgroups.org/manual/index.html#protlist)を参照ください。デフォルト設定は上記で説明したプロパティーを設定します。

マルチキャストはネットワーク上のすべてのデバイスにブロードキャストします。同じネットワーク上のクラスタ環境は、デフォルトで互いに通信します。それらの間で送信されたメッセージおよび情報（例えば、スケジュールされたタスク）は、意図しない結果につながる可能性があります。ネットワーク上で論理的または物理的に分離するか、[マルチキャストグループアドレスとポート値](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Multicast)の異なるセットを使用するように各クラスタの`portal-ext.properties`を構成することによって、そのようなクラスタ環境を分離します。

JGroupはバインドアドレスを自動で設定します。マニュアルアドレスを設定する場合は、下記を設定します。デフォルト値は`localhost`と設定されています:

    cluster.link.bind.addr["cluster-link-control"]=localhost
    cluster.link.bind.addr["cluster-link-udp"]=localhost

一部の設定にはlocalhostがホストの実際のアドレスではなく、内部ループバックネットワーク（127.0.0.1または::1)が使用されている場合もあります。何らかの理由でこれらの設定が必要な場合は、以下のプロパティで@product@に自動で検知させることも可能です。

    cluster.link.autodetect.address=www.google.com:80

サーバーから接続可能な他のホストに接続するように設定します。デフォルトではGoogleを指していますが、お使いのサーバーがファイアーウォールの内側にある場合は、この方法が利用できない可能があります。上記のプロパティーでアドレスを設定した場合は、自動検知アドレスを設定する必要はありません。

お使いのネットワークによって、マルチキャストTCPの使用が妨げられる可能性がありますので、下記の設定を使い、クラスターが通信できるようにします。これらの方法は全てJGroups から提供されていますのでご注意ください。

### チェックポイント

1. `localhost`使用せずにIPアドレスをバインドする場合は、これらのプロパティを使用して正しいIPアドレスが宣言されていることを確認してください。

       `cluster.link.bind.addr["cluster-link-control"]=localhost`
       `cluster.link.bind.addr["cluster-link-udp"]=localhost`
   
3. 負荷テストを実施して、必要に応じて設定を最適化します。

## TCPを利用したUNICAST

ネットワーク設定、もしくはノード間の物理距離といった理由から、UDP マルチキャストクラスタ構成を使用できない場合は、TCP ユニキャストが利用できます。ファイアーウォールでノードが分離されている構成や、ノードが地理的に異なる場所に配置されているケースなど特に、TCPユニキャストが必要です。

1. アプリケーションサーバーのJVMにパラメータを追加します。

       -Djgroups.bind_addr=[node_address]
   
   ノードのIPアドレスを使用します。

2. ノード間で相互に発見できるよう、ディスカバリー・プロトコルを決定します。4つの選択肢があります:

       - TCPPing
       - JDBCPing
       - S3_Ping
       - Rackspace_Ping
   
   どれを使用すればよいか定かでない時は`TCPPing`を使用します。ここで紹介する手順ではTCPPingを使っています。その他の設定は後半で説明しています。

3. [LiferayのNexusリポジトリ](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.portal.cluster.multiple)から最新の`com.liferay.portal.cluster.multiple-[version].jar`ファイルをダウンロードしてください。
このJARの`lib`フォルダには`jgroups-[version].Final.jar`というファイルがあります。
それを開いて`tcp.xml`見つけてください。このファイルを@product@にアクセス可能な場所に解凍します。このファイルをすべてのノードで使用してください。

4. 垂直クラスタ（例：複数のLiferay DXPサーバーが一つの物理または仮想システムで稼働している）を使用している場合は、TCPポートの衝突を防ぐためにディスカバリポートが最初のノード以外の全ノードに通信するポートを変更してください。この設定は、TCPタグ`bind_port` パラメータを調整します:

       <TCP bind_port="[some unused port]"
       ...
       />
   
   デフォルトポートが`7800`なので、未使用のポートをいくつか用意します。

5. 同じタグにパラメータ`singleton_name="liferay_cluster"`を追加します。このパラメータはトランスポートを統合し、チャンネルの制御しスレッドプールの数を減らします。詳しくは、 [JGroups](http://www.jgroups.org/manual-3.x/html/user-advanced.html)資料を参照ください。

   通常、上記以外のJGroups設定は必要ありません。しかし、*極めて特殊なケースで*クラスターノードが複数ネットワーク上に導入されている場合、ファイアーウォールの外部（公共IP）アドレスにパラメータ`external_addr`を各ホストに設定する必要があります。この種の設定は、ノードが物理的に離れている場合に必要です。これを設定することで、個別のネットワークに（ファイアーウォールで分離された）配備されたクラスタのノードが通信できるようになります。このような設定は、システムのセキュリティ監査でフラグされます。詳しくは、[JGroups](http://www.jgroups.org/manual-3.x/html/protlist.html#Transport)資料を参照ください。

6. ファイルを保存します。そのノードの`portal-ext.properties` が以下を指すように調節します:

       cluster.link.channel.properties.control=[CONFIG_FILE_PATH]/tcp.xml
       cluster.link.channel.properties.transport.0=[CONFIG_FILE_PATH]/tcp.xml
   
TCPを使ったユニキャストの設定ができました。上記の手順で、他のノードも設定します。

### JDBC Ping

TCP Pingを使ってクラスタのメンバーを発見する以外の方法は、セントラルデータベースを全ノードからアクセス可能にし、各ノードを発見させることも可能です。クラスタメンバーは自分自身を書き込み、このデータベースから他のメンバーのアドレスを読み取ります。クラスタ内のノードはこのデータベースに各自アドレスを書き込み、他ノードのアドレスを読み込みます。この設定を有効にするには、`TCPPING`タグを、対応する`JDBCPING`タグに置き換えます:

    <JDBC_PING
    connection_url="jdbc:mysql://[DATABASE_IP]/[DATABASE_NAME]?useUnicode=true&amp;characterEncoding=UTF-8&amp;useFastDateParsing=false"
    connection_username="[DATABASE_USER]"
    connection_password="[DATABASE_PASSWORD]"
    connection_driver="com.mysql.jdbc.Driver"/>

上記の例ではMySQLをデータベースとして使用しています。JDBC Pingの詳細は[JGroups](http://www.jgroups.org/manual-3.x/html/protlist.html#DiscoveryProtocols)資料を参照してください。

### S3 Ping

Amazon S3 Pingは、Amazon EC2 クラウドサービス上でサーバーを稼働させている場合に利用できます。
各ノードはS3 バケットに小規模のファイルをアップロードし、他のノードはこのバケットを読み込み他のノードを発見します。ノードが消えると、ファイルが削除されます。


S3 Pingを設定するには`TCPPING`タグを、対応する`S3_PING`タグに書き換えます: 

    <S3_PING
    secret_access_key="[SECRETKEY]"
    access_key="[ACCESSKEY]"
    location="ControlBucket"/>

上記のパラメータの値としてAmazonキーを指定します。 S3 Pingの詳細設定は、[JGroups](http://www.jgroups.org/manual-3.x/html/protlist.html#DiscoveryProtocols)資料を参照してください。


### その他のPING


JGroups は、この他にもRackspace Ping、BPing、File Ping等クラスターの発見方法を提供しています。これらのディスカバリーの利用方法の詳細は[JGroups](http://www.jgroups.org/manual-3.x/html/protlist.html#DiscoveryProtocols)の資料を参照してください。

## モジュールを使用したキャッシュ構成の変更

実運用でシステムが処理する必要があるトラフィックの種類の負荷を、最も再現する負荷テストを実施してください。たとえば掲示板上に多くのメッセージが作成されることが予想される場合、負荷テスト用のスクリプトはそれｃを反映するよう作成します。Webコンテンツを主に多く利用する場合は、同じく負荷テストスクリプトにそれが反映されている必要があります。

負荷テストの結果、デフォルトの分散キャッシュ設定がサイトに最適化されていないと分かることもあります。この場合は、設定を自分で調整する必要があります。Liferay DXPのインストールを直接変更することも、各ノードにモジュールをインストールし、クラスタを停止せずに設定を変更することもできます。

[プロジェクトを作成](https://dev.liferay.com/documents/10184/741415/portal-cache-override-config.zip)することによってこれをできるだけ簡単にしました。プロジェクトをダウンロードして、ワークスペースのし`modules`フォルダにある[Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace)に解凍します。キャッシュ設定を上書きするには、Ehcache設定ファイルを1つだけ変更する必要があります。これは、以下のフォルダ構造にあります。

- `src`
   - `main`
      - `java`
         - `resources`
            - `ehcache`
               - `override-liferay-multi-vm-clustered.xml`

サンプルプロジェクトでは、このファイルには`GroupImpl`サイトを処理するオブジェクトの設定が含まれています。他のオブジェクトをキャッシュに追加したいと思うかもしれません。実際、デフォルトファイルは他の多くのオブジェクトをキャッシュします。たとえば、活気のあるコミュニティがある場合は、前述のように、トラフィックの大部分がメッセージボードポートレットに向けられることがあります。メッセージボードにスレッドをキャッシュするには、次の`MBMessageImpl`クラスでブロックを設定します。

    <cache
    eternal="false"
    maxElementsInMemory="10000"
    name="com.liferay.portlet.messageboards.model.impl.MBMessageImpl"
    overflowToDisk="false"
    timeToIdleSeconds="600"
    >
    </cache>

Liferayのクラスタ設定ファイルを抽出して、モジュールプロジェクトに入れることで、デフォルト設定を自分でカスタマイズしながら保持することができます。`[Liferay Home]/osgi/portal`フォルダ内の`com.liferay.portal.cache.ehcache.impl.jar`ファイルにあります。 必要なファイルは`liferay-multi-vm-clustered.xml`で、`com.liferay.portal.cache.ehcache.impl.jar`ファイル内の`/ehcache`フォルダにあります。ファイルを入手したら、上記のファイルの内容を`override-liferay-multi-vm-clustered.xml`ファイルの内容で置き換えます。これで、このデフォルト設定を起点として使用します。

キャッシュに変更を加えたら、ファイルを保存し、モジュールを構築してデプロイすると、カスタマイズした設定でデフォルト設定を上書きします。このように、お客様のサイトで生成されるトラフィックの種類に最適なキャッシュ設定を微調整することができます。キャッシュ設定を変更するためにサーバーを再起動する必要はありません。これは大きな利点ですが、注意が必要です：Ehcacheはキャッシュが生きている間はキャッシュ設定の変更を許可しないので、サーバーの実行中にキャッシュを再設定するとキャッシュがフラッシュされます。
