---
header-id: advanced-monitoring-apm-tools-dynatrace-id-ja
---

# 高度なモニタリング：APMツール；Dynatrace

[TOC levels=1-4]

[Dynatrace](https://www.dynatrace.com)のような高度なパフォーマンス監視ツール は、@product@サーバーとインスタンスに関する詳細なパフォーマンス情報を提供します。DynatraceのWebベースのダッシュボードには、メモリ使用量、ガベージコレクション、CPUレベル、およびヒープダンプに関するリアルタイムのシステム情報が表示されます。各ダッシュボードはそれぞれの詳細レポートです。@product@ サーバーのパフォーマンス測定基準に関する個々のユーザーの最新情報まで表示できます。

| |** 注： **Dynatraceは現在[*Dynatrace SaaS*](https://www.dynatrace.com/support/dynatrace/)も提供しています。|
| デフォルトの設定からたくさんの興味深いデータを検出できます。 Dynatrace SaaS製品では、[Liferay FastPack](#liferay-digital-enterprise-fastpack)ほどの深いダッシュボードは提供していませんが、Dynatrace SaasのUIはとても直感的です。インストールされているDynatrace アプリと比べたDynatrace Saasの大きな利点は、インストール、メンテナンス、またはアップグレードの必要がないところです。

Dynatraceのダッシュボードを@product@とともに使用するには、以下のものが必要です：



- User Interface（UI）を含むDynatraceクライアント

- Dynatraceライセンス

- Dynatraceサーバー

- インストール済みJVMエージェント


最後に、Dynatraceのサポートを受けるためにDynatraceアカウントにサインアップしてください。

## ダッシュボード

Dynatraceには、進行中のトランザクションとプロセスが表示されます。@product@スタック全体を分析できるため、各コンポーネントにダッシュボードがあります。



- Webサーバー

- ブラウザ

- アプリケーションサーバー

- データベース


以下の画像の一部は、MySQL 5.7サーバーに接続された1つのアプリケーションサーバー（Apache Tomcat 8.0.32）の単一インスタンス（クラスタ化されていない）からのものです。
インストール方法は異なりますが、デフォルトのダッシュボードは同じです。

![図1: 最も上のレベルのインターフェイスから、分析するコンポーネントを選択することができます。](../../images-dxp/dynatrace01.png)

アプリケーションサーバー（例：*Tomcat*）を選択します。



このダッシュボードは、アプリサーバーの内部状態を表示します。負荷テストの間のグラフはとてもおもしろいです。CPU使用率、メモリなどの内訳を取得できます。

![図2: 負荷中のアプリケーションサーバーを監視することによって、システムパフォーマンスに関する重要な情報を習得することができます。](../../images-dxp/dynatrace02.png)

ルートレベルは、環境の他の部分からのパフォーマンスメトリックを示します。


たとえば、データベースコンポーネント（例：*MySQL*など）にデータベーストランザクションが表示されます：エージェントをインストールせずに表示できます。Dynatrace Collectorにはデータベース管理機能が組み込まれているため、別のデータベースエージェントを配置することはオプションです（[Database Monitoring](https://community.dynatrace.com/community/display/DOCDT65/Database+Monitoring)を参照してください）。 

![図3: ライブデータトランザクションを見ることができます。](../../images-dxp/dynatrace04-db.png)

1つの利用可能なデフォルトダッシュボードはユーザーエクスペリエンスを追跡します；Dynatraceは、Webブラウザまたはモバイルアプリでユーザーの行動を監視できます。各ビジットのカテゴリは次のとおりです：



- 満足

- 我慢
- 不満足

*満足*いくビジットは、1）アクションが何も失敗していないもの、そして2）全行動の50％以上が満足していたものです。



*不満足*のビジットは、1）最後のアクションはが失敗したもの（ウェブサイトが動かない）、そして2）ユーザーの最後の操作がイライラしていたものです（例：Webサイトが遅すぎるなど）。詳細は[How Does UEM Work?](https://community.dynatrace.com/community/pages/viewpage.action?pageId=221381305#HowDoesUEMWork)を参照してください。

![図4: ブラウザーメトリクスはクライアンドサイドのパフォーマンスの問題を検出する手助けをしてくれます。](../../images-dxp/dynatrace05-visits.png)

作成したカスタムダッシュボードをインポートすることができます（例：XMLフォーマット上などで）：



1. *[Dashboard]*メニューから>*[Open*]を選択します



2. カスタムダッシュボードを参照して選択します。



3. *[Open Dashboard]*を選択します。



しかし、それだけではありません：@product@用に作成されたカスタムダッシュボードです。

## Liferay DXP FastPack


Liferay用Dynatrace FastPackは、@product@環境用にカスタマイズされた事前構成済みのDynatraceプロファイルを提供します。これには、センサー、メジャーとビジネストランザクションを含むテンプレートシステムプロファイル、および@product@のダッシュボードが含まれています。UEMを使用している場合は、コンバージョンと訪問者のタグ付けも行われます。



Liferay DXP FastPackは、[Dynatrace site](https://community.dynatrace.com/community/display/DL/Liferay+Digital+Enterprise+FastPack)からダウンロードすることができます 。
これらのダッシュボードは、Dynatraceに組み込まれているダッシュボードを超えたものです。



FastPackは`.dtp`ファイルとして配布されています。FastPackをインストールするには、次の手順に従います。

1. Dynatraceクライアントで、*[Tool]*> *[Manage Plugins]*を選択します。


2. *[Install Plugin]*を選択します。


3. ` .dtp`ファイルを参照して選択します。



4. *[OK]*を*[Import Resource Pack]*の確認ウィンドウから選択します。



5. *[OK]*を選択して*[Configure Plugins]*ウィンドウを閉じます。



6. これで*Liferay*はシステムプロファイルの左側のコントロールパネルに表示されるようになります。



7. Liferayプロファイルが唯一のアクティブプロファイルになるように、他のプロファイルを無効にします。

## Dynatraceクライアント構成


Java JDK 1.8（またはそれと同等のもの）は、Dynatraceクライアントと@product@の両方を実行するための前提条件です。JVMで@product@を実行するために十分なCPUコアを割り当てるようにしてください。クライアントを設定した*後*に Fastpackプラグインをインストールしてください。

1. Dynatrace環境をインストールしてください。[Dynatrace Installation Step 1](https://community.compuwareapm.com/community/display/EVAL/Step+1+-+Download+and+install+dynaTrace)の指示に従ってください。
インストールファイルは、Windows、Unix、およびLinuxシステムで利用可能です。



   Windowsユーザーの方は、`.msi `ファイル全体 （約770MB）をダウンロードしてインストールしてください。


   [file](https://files.dynatrace.com/downloads/appmon/freetrial/dynatrace-full-x86-64.msi)
(約770MB).

   - *[Immediately activate the Dynatrace .NET agent]*を選択します。

   - *Immediately activate the Dynatrace IIS agent*を選択します。
   - Dynatraceサーバー、コレクター、およびクライアントを必ず起動してください。
   Linuxユーザーの場合、インストールファイルは実行可能JARとしてパッケージされています。
完全な[JARファイル](https://files.dynatrace.com/downloads/appmon/freetrial/dynatrace-full-linux-x86-64.jar)をダウンロードしてください 。
`java -jar dynatrace-full-linux-x86-64.jar`のコマンドををJARのフォルダで実行します。

2. Dynatraceインスタンスを起動し、Dynatrace試用版ライセンスを展開します（下記参照）。



   - Dynatraceライセンスキーを要求してください；このキーは、Web認証情報またはウェルカムEメールのリンクから入手できます。ファイルを適切な場所に配置します。クライアントが初めて起動してライセンスの入力を求められたら、ファイルに移動して選択します。



   - ライセンスが正常にインポートされると、Dynatraceクライアントはサーバーの再起動を促します。サーバーを再起動してください。

3. サーバーが再起動したら、以下のサービスが開始されたことを確認してください：



   - Dynatraceサーバー

   - Dynatrace フロントエンド

   - Dynatrace コレクター

   これらは、プロセスが実行されているかどうか見ることによって確認できます：

       # ps -A | grep dt
   
   以下のような出力が見えるはずです：



       3954 ?00:00:43 dtcollector
       5924 ?00:01:54 dtserver
       5949 ?00:00:42 dtfrontendserver
   
   `netstat`を使ってクライアントが適切なポートで待機していることを確認してください：

       # netstat -an | grep 021
   
   以下のような出力が見えるはずです：



       tcp  0  0 :::2021 :::* LISTEN (port for dynaTrace client connections)
       tcp  0  0 :::8021 :::* LISTEN (the dynaTrace server's web interface)
       
       # netstat -an | grep 99
   
   以下のような出力が見えるはずです：



       tcp        0      0 :::6699    :::*     LISTEN    (port for dynaTrace collector connections)
       tcp        0      0 :::9998    :::*     LISTEN    (port for dynaTrace agent connections)
   
 これでDynatraceサーバーとコレクターをインストールが完了しました。

### JVMエージェントの設定方法


各@product@ サーバーにJVMエージェントをデプロイします。エージェントは既存のインフラストラクチャの上に置かれています（この例ではApache Tomcat）。

1. エージェント`.jar`をご使用のオペレーティングシステム用にダウンロードしてください。このエージェントは、アプリケーションサーバー上のDynatraceクライアントと@product@バンドルがリモートで接続されている場合にのみ使用してください。エージェントは各@product@インスタンスと同じマシンにインストールする必要があります。

2. エージェントの場所は、各アプリケーションサーバーで設定する必要があります。クライアントと@product@が同じマシン上にある場合は、他に何もインストールする必要はありませんが、（エージェントは既にフルインストールに含まれています）設定する必要があります。この設定はテストとデモンストレーションの目的のためだけのものです；DynatraceクライアントのJVM要件は、@product@の要件とは異なる場合があります。どちらにしても、`setenv.bat|sh`ファイルを編集してください。

   UNIX / Linuxの場合`setenv.sh`：

   `-agentpath:${location of the dtagent .dll}"=name=Tomcat_Monitoring,server=liferay-cfe3684:9998`

   Windowsのの場合`setenv.bat`：

   `-agentpath:"C:\Program Files\Dynatrace\Dynatrace 6.5\agent\lib64\dtagent.dll"=name=Tomcat_Monitoring,server=liferay-cfe3684:9998`

   他のアプリケーションサーバーの場合は、他のJVM設定が設定されているファイルにプロパティを配置します（例えば、JBoss EAPの場合`standalone.conf.bat`）。

3. @product@をJVMエージェントと共にDynatraceインスタンスに接続します。詳細は[Dynatrace's documentation](https://community.dynatrace.com/community/display/EVAL/Step+3+-+Connect+Agent+to+Dynatrace)を参照してください。右側の「アプリケーションサーバー」タブを選択します。以下の例ではApache Tomcatを使用していますが、Dynatraceによると、手順はJBossとWebSphereではほぼ同じです。

   - *[Client]>[Monitoring]*を選択します。

   - *Java*を選択します。

   - *アプリケーションサーバー*を選択します。

   - *Apache 5+*を選択します。

   - *Java 5以降（64ビット）*を選択します。

   - Tomcatバンドルが*ローカル*か*リモート*かを選択します。

   - コネクタがオンになっている限り、@product@インスタンスが見つかるはずです。テスト目的では、@product@バンドルはローカルです。

4. ファストパックをインポートしたら、以下のことを実行する必要があります：



   - Dynatraceで唯一のアクティブシステムプロファイルとしてLiferayプロファイルを選択します。

   - アプリケーションサーバーを再起動します。


Liferayエージェントが新しくインポートされたプロファイルへのデータ送信を開始します。

## 性能試験


システム管理者は、全体的な調整プロセスの一環として負荷テストを頻繁に実行する必要があります。Dynatraceクライアントは、システムのパフォーマンスを監視して分析するための優れたツールです。デモンストレーションの目的で、JMeterを使用して簡単な負荷テストを作成した結果、Dynatraceクライアントは以下の結果を取得しました。

![図5: Dynatraceはスレッドダンプの結果を作成することができます。](../../images-dxp/dynatrace10-thread-dump.png)

負荷テスト中にスレッド数が増加するにつれて、Dynatraceクライアントは時間の経過ごとのガベージコレクションを示します。

![図6: 負荷テスト開始直後のガベージコレクションの統計結果.](../../images-dxp/dynatrace07.png)

ここでも、Dynatraceクライアントはリアルタイムで負荷テスト中の高消費量を表示し続けます。

![図7: 負荷テスト中盤のカベージコレクションの統計結果。](../../images-dxp/dynatrace08.png)

パフォーマンステスト中に注意すべきもう1つのダッシュボードは、CPUサンプリングダッシュボードです。以下の図は、100秒間のCPUプロセスをキャプチャした生成レポートを示しています。これは、不要なプロセスが@product@インスタンスを遅くしていることを示しています。

![図8: DynatraceクライアントはCPUの見本のレポートを作成することができます。](../../images-dxp/dynatrace09-cpu-samplings.png)

これらのダッシュボードは全てすぐに使用できるようになっており、@product@用に開発されたDynatrace FastPackをデプロイした後、さらに多くのダッシュボードを使用することができます。Dynatraceはたくさんの便利なパフォーマンス監視機能を提供しています。

## 資料

[Dynatrace Community](https://community.compuwareapm.com)

[Dynatrace Installation Step 1](https://community.compuwareapm.com/community/display/EVAL/Step+1+-+Download+and+install+dynaTrace)

[Dynatrace Installation Step 2](https://community.compuwareapm.com/community/display/EVAL/Step+2+-+Activate+License+Key)

[Dynatrace Installation Step 3](https://community.dynatrace.com/community/display/EVAL/Step+3+-+Connect+Agent+to+Dynatrace)
