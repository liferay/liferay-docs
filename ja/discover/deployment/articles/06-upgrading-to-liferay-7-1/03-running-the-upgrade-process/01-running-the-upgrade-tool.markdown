---
header-id: running-the-upgrade-ja
---

# アップグレードツールの実行

[TOC levels=1-4]

アップグレードツールは、コアおよびインストールされているモジュールをアップグレードするに当たって、最も簡単な方法を提供します。ファイルまたはツールのコマンドラインインターフェイスから、アップグレードを設定できます。アップグレードツールを使用すると、コアとすべてのモジュールをまとめて、または個別にアップグレードできます。

@product-ver@バンドルにはアップグレードツールが含まれています。手動で@product-ver@をインストールした場合は、アップグレードツールを個別にダウンロードできます。

- *Liferay DXP 7.1*： [*Liferay DXP 7.1ダウンロード*ページ](https://web.liferay.com/group/customer/dxp/downloads/7-1)に移動し、*[管理ツール]*を選択して、*[ダウンロード]*をクリックします。

- *Liferay Portal CE 7.1*：[*ダウンロード*ページ](https://www.liferay.com/downloads-community)に行き、*Liferay Portalツール7.1*用の*ダウンロード*を選択します。

アップグレードツールを実行する前に、ツールの使用方法、およびコアアップグレードとコア以外のモジュールアップグレードの設定方法を確認してください。

- [アップグレードツールの使用法](#upgrade-tool-usage)
- [コア以外のモジュールアップグレードの設定](#configuring-module-upgrades)
- [コアアップグレードの設定](#configuring-the-core-upgrade)

まず最初に、ツールの使用方法を説明します。

## アップグレードツールの使用法

`db_upgrade.sh`スクリプト（Windows上では`db_upgrade.bat`）がアップグレードツールを起動します。これは、`[Liferay Home]/tools/portal-tools-db-upgrade-client`フォルダ内にあります。

このコマンドは、アップグレードツールの使用法を表示します。

    db_upgrade.sh --help

コアのみをアップグレードするには、`com.liferay.portal.upgrade.internal.configuration.ReleaseManagerConfiguration.config`というファイルと下記の内容と合わせて、`[Liferay Home]/osgi/configs`フォルダに追加します 。

    autoUpgrade="false"

この設定は自動的なモジュールアップグレードを防ぎますが、コアアップグレードを終えた後にアップグレードツールが[アップグレード中のモジュール](/discover/deployment/-/knowledge_base/7-1/gogo-shell-commands-for-module-upgrades-ja)のためにGogoシェルを開いてしまう原因になります。

ツールのデフォルトのJavaパラメータは、次のとおりです。

    -Dfile.encoding=UTF8 -Duser.country=US -Duser.language=en -Duser.timezone=GMT -Xmx2048m

この`-j`オプションを使用すると、JVMパラメータを上書きできます。たとえば、これらのオプションはJVMメモリを10GBに設定します。このメモリ量は、このプロセスタイプで最初に設定する量として適しています。

    db_upgrade.sh -j "-Dfile.encoding=UTF8 -Duser.country=US -Duser.language=en -Duser.timezone=GMT -Xmx10240m"

この`-l`オプションを使用すると、ツールのログファイル名を指定できます。

    db_upgrade.sh -l "output.log"

アップグレードツールのコマンドラインオプションは次のとおりです。

**--help**または**-h**：ツールのヘルプメッセージを表示します。

**--jvm-opts**または**-j** +**[arg]**：アップグレードプロセスのJVMオプションを設定します。

**--log-file**または**-l** + **[arg]**：ツールのログファイル名を指定します。デフォルトの名前は`upgrade.log`です。

**--shell**または**-s**：アップグレードプロセスが完了したら、自動的にGogoシェルに接続します。

| **注：**理想的なメモリ、CPU、およびデータベース接続設定を搭載したサーバーでのみ、アップグレードプロセスを実行してください。`ssh`を使用してリモートでアップグレードを実行する場合は、中断に注意してください。
| 
| - `ssh`を使用してアップグレードを実行している場合は、`nohup`などを使用してハングアップ（接続切断）を無視してください。
| - 接続しているコンピュータのシャットダウン、またはスリープ状態にする設定を無効にします。
| 
| データベースアップグレードツール2.0.1（Liferay Portal CE 7.1 GA 2に含まれており、エンタープライズのお客様向けのヘルプセンターからダウンロード可能）以降、アップグレードプロセスは、接続が切断されてもサーバー上で続行されます。接続が切断された場合は、ログを使用してアップグレードステータスを再接続して監視します（デフォルトのログファイルは`upgrade.log`です）。@product-ver@以前のバージョンを使用していてアップグレードの実行が中断された場合は、ログファイルで実行が停止した場所を確認してください。
| 
| - Core 7.1以降のアップグレードプロセスまたはモジュールのアップグレードプロセス中に実行が停止した場合は、アップグレードツールを再起動して、その時点からのアップグレードを続行してください。Gogoシェルを使用して[モジュールのアップグレードステータスを確認](/discover/deployment/-/knowledge_base/7-1/gogo-shell-commands-for-module-upgrades-ja#checking-the-upgrade-status)し、モジュールのアップグレードを続けることもできます。
| - Core 7.0以前のアップグレードプロセス中に実行が停止した場合は、[バックアップからデータを復元](/discover/deployment/-/knowledge_base/7-1/backing-up-a-liferay-installation-ja)してアップグレードを再度開始する必要があります。

| **警告**：ツールの展開されたコマンドがWindowsに対して大きくなりすぎないようにするには`[Liferay Home]/tools/portal-tools-db-upgrade-client`フォルダからアップグレードツールスクリプトを実行します。

[アップグレードを開始](#running-and-managing-the-core-upgrade)する前に、コア以外のモジュールのアップグレードを実行する方法を決定しておいてください。

## コア以外のモジュールアップグレードの設定

アップグレードツールを設定することで、インストールされているすべてのモジュールを自動的にアップグレードしたり、手動でモジュールのアップグレードを実行するために（コアアップグレードの完了後に）Gogoシェルを開くようにしたりできます。

アップグレードツールの`autoUpgrade`プロパティが`true`（デフォルト設定）に設定されている場合は、インストールされているすべてのモジュールのアップグレードプロセスも実行されます。

`com.liferay.portal.upgrade.internal.configuration.ReleaseManagerConfiguration.config`というファイルに`autoUpgrade="false"`を設定して、そのファイルを`[Liferay Home]/osgi/configs`フォルダにコピーすると、コアのアップグレード後にアップグレードツールによってGogoシェルが開きます。Gogoシェルでは、[モジュールのアップグレードを管理](/discover/deployment/-/knowledge_base/7-1/gogo-shell-commands-for-module-upgrades-ja)できます。

コア以外のモジュールをアップグレードする方法は以上です。次に、コアアップグレードの設定オプションについて説明します。

## コアアップグレードの設定

コアアップグレードでは設定が必要です。実行時にコマンドラインインターフェースを介して設定したり、`[Liferay
Home]/tools/portal-tools-db-upgrade-client/`にある以下のファイルで事前に設定することができます。

- `app-server.properties`：サーバーの場所とライブラリを指定します。
- `portal-upgrade-database.properties`：データベース接続を設定します。
- `portal-upgrade-ext.properties`：アップグレードに必要な残りのポータルプロパティを設定します。現在使用しているポータルプロパティ（データベースのプロパティを除く）をこのファイルにコピーすることをお勧めします。現在のプロパティをコピーする前に、@product-ver@のポータルプロパティを更新したことを確認してください 。

次に、各ファイルのプロパティについて説明します。

### app-server.propertiesの設定

以下の情報を指定して、@product-ver@がインストールされているアプリケーションサーバーを設定します。

**dir：** アプリケーションサーバーディレクトリの絶対パスです。*（必須）*

**extra.lib.dirs： **クラスパスに追加するバイナリまたはリソースを含む、追加ディレクトリのカンマ区切りのリストです。すべての絶対パス、またはすべての**dir**の相対パスを使用してください。*（必須）*

**global.lib.dir： **アプリケーションサーバーのグローバルライブラリディレクトリです。絶対パスまたは**dir**の相対パスを使用してください。*（必須）*

**portal.dir：** ご利用のアプリサーバにインストールされているポータルのディレクトリです。絶対パスまたは**dir**の相対パスを使用してください。*（必須）*

**server.detector.server.id：**サポートされているアプリケーションサーバーのIDです。
（*必須*）以下がIDです。

- `jboss`
- `jonas`
- `resin`
- `tomcat`
- `weblogic`
- `websphere`
- `wildfly`

相対パスはUnixスタイルのフォーマットを使用しなければなりません。たとえば、次のプロパティはWindows用で、相対パスを使用しています。

    dir=D:\
    extra.lib.dirs=Liferay/liferay-portal-master/tomcat-9.0.10/bin
    global.lib.dir=Liferay/liferay-portal-master/tomcat-9.0.10/lib
    portal.dir=Liferay/liferay-portal-master/tomcat-9.0.10/webapps/ROOT
    server.detector.server.id=tomcat

たとえば、これらのプロパティはUnix用であり、すべての絶対パスを使用しています。

    dir=/
    extra.lib.dirs=/home/user/liferay/liferay-portal-master/tomcat-9.0.10/bin
    global.lib.dir=/home/user/liferay/liferay-portal-master/tomcat-9.0.10/lib
    portal.dir=/home/user/liferay/liferay-portal-master/tomcat-9.0.10/webapps/ROOT
    server.detector.server.id=tomcat

### portal-upgrade-database.propertiesの設定

アップグレードするデータベースを設定するために、以下の情報を指定します。
これらのプロパティ は、`portal-ext.properties`ファイルで使用する[JDBCポータルのプロパティ](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#JDBC)と完全に対応しています。

**jdbc.default.driverClassName** *（必須）*

**jdbc.default.url** *（必須）*

**jdbc.default.username** *（必須）*

**jdbc.default.password** *（必須）*

### portal-upgrade-ext.propertiesの設定

アップグレードそのものを設定するには、以下の情報を指定します。

**liferay.home：**[Liferayのホームフォルダ](/discover/deployment/-/knowledge_base/7-1/installing-liferay-ja#liferay-home)*（必須）*

**hibernate.jdbc.batch_size：**パフォーマンスを向上させるために使用されるJDBCバッチサイズです。デフォルトでは、*250*に設定されています。*（オプション）*

### アップグレードの設定例

下記はアップグレードツールのコマンドラインインターフェイスとの対話の例です。

    Please enter your application server (tomcat):
    tomcat
    Please enter your application server directory (../../tomcat-8.0.32):
    
    Please enter your extra library directories (../../tomcat-8.0.32/bin):
    
    Please enter your global library directory (../../tomcat-8.0.32/lib):
    
    Please enter your portal directory (../../tomcat-8.0.32/webapps/ROOT):
    
    [ db2 mariadb mysql oracle postgresql sqlserver sybase ]
    Please enter your database (mysql):
    mariadb
    Please enter your database host (localhost):
    
    (etc.)

コマンドラインインタフェースは、入力に基づいて設定ファイルを作成します。
ただし、すべてを事前に設定したい場合は、この情報を設定ファイルに入れておくことをお勧めします。

以下は、カスタマイズして`[Liferay Home]/tools/portal-tools-db-upgrade-client/`にコピーできるアップグレード設定ファイルの例です。

- `app-server.properties`:

       dir=../../tomcat-8.0.32
       global.lib.dir=/lib
       portal.dir=/webapps/ROOT
       server.detector.server.id=tomcat
       extra.lib.dirs=/bin
   
- `portal-upgrade-database.properties`:

       jdbc.default.url=jdbc:mysql://lportal62?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
       jdbc.default.driverClassName=com.mysql.jdbc.Driver
       jdbc.default.username=root
       jdbc.default.password=
   
- `portal-upgrade-ext.properties`:

       liferay.home=/home/user/servers/liferay7
       module.framework.base.dir=/home/user/servers/liferay7/osgi
   
次に、コアアップグレードを開始する手順について説明します。

## コアアップグレードの実行と管理

[アップグレードツールの使用法](#upgrade-tool-usage)の説明に従って、アップグレードツールを起動します 。
コアアップグレードのステージは次のとおりです。

1. アップグレードパッチレベルを表示する

2. コアアップグレードプロセスを実行する

3. コアベリファイアを実行する

アップグレードツールのログファイル（デフォルトのファイルは`upgrade.log`）でアップグレードを監視します 。コアアップグレードプロセスが失敗した場合は、失敗を分析して解決します。@product@ 7.1（またはそれ以降）のコアアップグレード手順が失敗した場合は、アップグレードツールを実行すると、その手順から再開されます。

[コア以外のモジュールをアップグレードするようにアップグレードツールを設定](#configuring-module-upgrades)した場合 、ツールはGogoシェルを開き、該当するモジュールのアップグレードを開始します。Gogoシェルを使用すると、モジュールのアップグレード、モジュールのアップグレードステータスの確認、アップグレードの確認、およびモジュールのアップグレードの再開を行うことができます。次は、Gogoシェルコマンドを使用して、@product@アップグレードを完了する方法について説明します。
