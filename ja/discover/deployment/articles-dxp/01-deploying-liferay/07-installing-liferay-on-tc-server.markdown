---
header-id: installing-liferay-on-tc-server
---

# tcサーバーに@product@をインストールする

[TOC levels=1-4]

tcサーバーに@product@をインストールするには、以下のファイルが必要です：



- @product@ WARファイル



- 依存関係.zipファイル



- OSGi JARの.zipファイル



以下は、tc Serverに@product@をインストールするための基本的な手順です：



- アプリケーションサーバーに@product@依存関係をインストールする



- アプリケーションサーバーを@product@用に設定する



- アプリケーションサーバーに@product@ WARファイルをデプロイする

| **注:**このインストールに関する説明書では
| [*Liferay Home*](/discover/deployment/-/knowledge_base/7-1/installing-liferay-ja#liferay-home)
| という用語が使われています。*Liferay Home* とはtcサーバーと@product@特有のフォルダが含まれているフォルダのことです。`data`、`deploy`、
| `licenses`、および `osgi` フォルダのことです。

## tcサーバーを作成する

1. [ここ](https://network.pivotal.io/products/pivotal-tcserver)から入手できるtcサーバーをダウンロードし解凍してください。
これは`[TCSERVER_INSTANCE_HOME]`と呼ばれています。

2. `servers` という名のフォルダを `[TCSERVER_INSTANCE_HOME]`内に作成します。(例：
`/opt/pivotal-tc-server/servers`). このフォルダが*Liferay Home*になります。 (上の注意事項参照）上記の二つを混同しないように気をつけてください。

3. 次に、@product@をデプロイするための*dxp-server*というインスタンスを作成します。`[TCSERVER_INSTANCE_HOME]/developer-4.0.0.RELEASE`フォルダに移動して以下のコマンドを実行します：

       tcserver create -i ../servers dxp-server
   
   新しいインスタンスはデフォルトで`[TCSERVER_INSTANCE_HOME]/instances`フォルダに作成されます。`-i`は、どこにフォルダを作成すればいいか指示します。インスタンスのロケーションはプロパティファイルでも変更できます。詳しくは、[tc Server documentation](https://tcserver.docs.pivotal.io/4x/docs-tcserver/topics/tcserver-properties-reference.html)を参照してください。



確認事項:

1.  `servers` という新しいフォルダが作成されている。



2. `servers`のフォルダ内に`dxp-server`という名前の新しいフォルダが作成されている。 `dxp-server`フォルダ内に以下のフォルダが作成されている：

   - `bin`
   - `conf`
   - `lib`
   - `logs`
   - `temp`
   - `webapps`
   - `work`

これで、必要な依存関係をインストールできます。



## @product@依存関係のインストール

@product@は、デフォルトではtc Serverに含まれていない他のJARに依存しています。@product@バンドルに含まれているJARの中には、必須ではないけど便利なものもあります。@product@バンドルがない場合は、以下に説明されているように、必要なJARをサードパーティーからダウンロードできます。

| **注：**@product@をソースコードからビルドした時、あるいは[@product@ バンドルをダウンロードした](https://web.liferay.com/group/customer/dxp/downloads/7-1)時、必須かつ便利なJARsが事前インストールされます。
| @product@バンドルに同梱されているすべてのJARを早く取得したい場合は、以下のソースを使うことで早く習得できるかもしれません：

1. JARsを依存関係のZIPから`[TCSERVER_INSTANCE_HOME]/servers/dxp-server/lib`フォルダーに抽出します。JARは以下のとおりです:

   - `com.liferay.petra.concurrent.jar`
   - `com.liferay.petra.executor.jar`
   - `com.liferay.petra.function.jar`
   - `com.liferay.petra.io.jar`
   - `com.liferay.petra.lang.jar`
   - `com.liferay.petra.memory.jar`
   - `com.liferay.petra.nio.jar`
   - `com.liferay.petra.process.jar`
   - `com.liferay.petra.reflect.jar`
   - `com.liferay.petra.string.jar`
   - `com.liferay.registry.api.jar`
   - `hsql.jar`
   - `portal-kernel.jar`
   - `portlet.jar`

2. 以下のJARをダウンロードするか、@product@バンドルから`[TCSERVER_INSTANCE_HOME]/servers/dxp-server/lib`フォルダにコピーします：

   - [`activation.jar`](http://www.oracle.com/technetwork/java/javase/jaf-136260.html)
   - [`ccpp.jar`](http://mvnrepository.com/artifact/javax.ccpp/ccpp/1.0)
   - [`jms.jar`](http://www.oracle.com/technetwork/java/docs-136352.html)
   - [`jta.jar`](http://www.oracle.com/technetwork/java/javaee/jta/index.html)
   - [`jutf7.jar`](http://mvnrepository.com/artifact/com.beetstra.jutf7/jutf7)
   - [`mail.jar`](http://www.oracle.com/technetwork/java/index-138643.html)
   - [`persistence.jar`](http://mvnrepository.com/artifact/org.eclipse.persistence/javax.persistence/2.1.1)
   - [`support-tomcat.jar`](http://mvnrepository.com/artifact/com.liferay.portal/com.liferay.support.tomcat)

3. データベースのJDBCドライバを`[TCSERVER_INSTANCE_HOME]/servers/dxp-server/lib`フォルダにコピーします。よく使われるドライバは次のとおりです：

   - [`mariadb.jar`](https://downloads.mariadb.org/)
   - [`mysql.jar`](http://dev.mysql.com/downloads/connector/j)
   - [`postgresql.jar`](https://jdbc.postgresql.org/download/postgresql-42.0.0.jar)

4. *Liferay Home*に`osgi`フォルダを作成します。OSGiのZIPファイルからフォルダ（例：`configs`, `core`、など多数）を`osgi`フォルダに抽出します。
`osgi`フォルダは、@product@のOSGiランタイムに必要なモジュールを提供します。

**確認事項:**

1. `[TCSERVER_INSTANCE_HOME]/servers/dxp-server/lib`フォルダに以下のJARが含まれている：

   - `activation.jar`
   - `ccpp.jar`
   - `com.liferay.petra.concurrent.jar`
   - `com.liferay.petra.executor.jar`
   - `com.liferay.petra.function.jar`
   - `com.liferay.petra.io.jar`
   - `com.liferay.petra.lang.jar`
   - `com.liferay.petra.memory.jar`
   - `com.liferay.petra.nio.jar`
   - `com.liferay.petra.process.jar`
   - `com.liferay.petra.reflect.jar`
   - `com.liferay.petra.string.jar`
   - `com.liferay.registry.api.jar`
   - `hsql.jar`
   - `jms.jar`
   - `jta.jar`
   - `jutf7.jar`
   - `mail.jar`
   - `mariadb.jar`
   - `mysql.jar`
   - `persistence.jar`
   - `portal-kernel.jar`
   - `portlet.jar`
   - `postgresql.jar`
   - `support-tomcat.jar`

2. `[Liferay Home]/osgi`フォルダに以下のサブフォルダが含まれている：

   - `configs`
   - `core`
   - `marketplace`
   - `modules`
   - `portal`
   - `static`
   - `test`
   - `war`

## tcサーバーの設定方法

@product@がtcサーバー上でうまく動作するようにするための設定の編集がいくつかあります。
これらの設定変更はすべて、tcサーバーランタイムインスタンスで行う必要があります。



1. `[TCSERVER_INSTANCE_HOME]/servers/dxp-server/bin`フォルダに移動してください。`setenv.sh`で、この行を：

       JVM_OPTS="-Xmx512M -Xss256K"
   
   以下の行に置き換えてください。

       JVM_OPTS="-Xmx2048M -Xss512K -XX:MaxMetaspaceSize=512m"
   
   `setenv.bat` では、この行を

       set JVM_OPTS=-Xmx512M -Xss256K
   
   上を以下のものに置き換えてください。

       set JVM_OPTS=-Xmx2048M -Xss512K -XX:MaxMetaspaceSize=512m
   

2. 次に、UTF-8 URIエンコードが一貫して使用されていることを確認する必要があります。
`[TCSERVER_INSTANCE_HOME]/servers/dxp-server/conf/server.xml`を開いて、`Connector`タグに`URIEncodingto`を`UTF-8`に設定することが含まれていることを確認してください。

       <Connector acceptCount="100"
       connectionTimeout="20000"
       executor="tomcatThreadPool"
       maxKeepAliveRequests="15"
       port="${bio.http.port}"
       protocol="org.apache.coyote.http11.Http11Protocol"
       redirectPort="${bio.https.port}"
       URIEncoding="UTF-8" />
   

3. Windowsに@product@とtcサーバーをインストールする場合は、`[TCSERVER_INSTANCE_HOME]/servers/dxp-server/conf/wrapper.conf`を開いて、

       wrapper.java.additional.8=-Xss256K
   
   上を以下のものに置き換えてください。

       wrapper.java.additional.8=-Xmx2048M
   
   そして以下のプロパティを追加します：

       wrapper.java.additional.9=-Xss512K
       wrapper.java.additional.10=-XX:MaxMetaspaceSize=256M
       wrapper.java.additional.11=-Dfile.encoding=UTF-8
   
4. 最後に、`[TCSERVER_INSTANCE_HOME]/servers/dxp-server/conf/web.xml`を開いて、` <load-on-startup>3</load-on-startup>`の後に以下の設定を足してください。



       <init-param>
       <param-name>compilerSourceVM</param-name>
       <param-value>1.8</param-value>
       </init-param>
       <init-param>
       <param-name>compilerTargetVM</param-name>
       <param-value>1.8</param-value>
       </init-param>
   
### データベースの設定方法

データベース設定を処理する上で一番簡単な方法は @product@にデータソースを管理させることです。@product@の組み込みデータソースを使いたい場合は、このセクションは飛ばして大丈夫です。

tcサーバーにデータソースを管理させたい場合は、次の手順に従ってください：



1. データベースサーバーがインストールされ、動作していることを確かめます。別のマシーンにインストールされている場合は、@product@からアクセスできることを確認してください。



2. `[TCSERVER_INSTANCE_HOME]/servers/dxp-server/conf/Catalina/localhost/ROOT.xml` で指定されているウェブアプリケーションにデータソースを追加します。（このファイルがなければい作成してください。）設定例は以下のとおりです：

       <Context crossContext="true" path="">
       <Resources>
       <PreResources
       base="${catalina.base}/lib/ext/portal"
       className="com.liferay.support.tomcat.webresources.ExtResourceSet"
       webAppMount="/WEB-INF/lib"
       />
       </Resources>
       <Resource
       name="jdbc/LiferayPool"
       auth="Container"
       type="javax.sql.DataSource"
       driverClassName="com.mysql.jdbc.Driver"
       url="jdbc:mysql://localhost/lportal?useUnicode=true&amp;characterEncoding=UTF-8"
       username="root"
       password="root"
       maxActive="100"
       maxIdle="30"
       maxWait="10000"
       />
       </Context>
   
   上記のリソースはMySQL databaseのもので、名前は`lportal`、ユーザー名が`root`、そしてパスワードも`root`となっています。これらの値を独自のものに置き換えてください。



3. Liferay Homeの`portal-ext.properties`ファイルに、自分のデータソースを指定してください：



       jdbc.default.jndi.name=jdbc/LiferayPool
   
これでデータソースが設定されました。次にメールセッションを設定します。

### メール設定方法

データベースと同様、メール設定の処理も@product@に行わせるのが一番簡単な方法です。@product@の組み込みメールセッションを使用したい場合は、このセクションを飛ばしてControl Panelで[メールセッションを設定してください](/discover/deployment/-/knowledge_base/7-1/installing-liferay-ja#configuring-mail)。



tcサーバーを使ってメールセッションを管理したい場合は、次の手順に従ってください：

1. `[TCSERVER_INSTANCE_HOME]/servers/dxp-server/conf/Catalina/localhost/ROOT.xml`を編集して、ウェブアプリケーションの`Context`に`Resource`としてメールセッションを足します。 
以下のサンプルのメールセッション値を必ず自分のものに置き換えてください。

       <Context crossContext="true" path="">
       <Resources>
       <PreResources
       base="${catalina.base}/lib/ext/portal"
       className="com.liferay.support.tomcat.webresources.ExtResourceSet"
       webAppMount="/WEB-INF/lib"
       />
       </Resources>
       ...
       <Resource
       name="mail/MailSession"
       auth="Container"
       type="javax.mail.Session"
       mail.pop3.host="pop.gmail.com"
       mail.pop3.port="110"
       mail.smtp.host="smtp.gmail.com"
       mail.smtp.port="465"
       mail.smtp.user="user"
       mail.smtp.password="password"
       mail.smtp.auth="true"
       mail.smtp.starttls.enable="true"
       mail.smtp.socketFactory.class="javax.net.ssl.SSLSocketFactory"
       mail.imap.host="imap.gmail.com"
       mail.imap.port="993"
       mail.transport.protocol="smtp"
       mail.store.protocol="imap"
       />
       </Context>
   
2. Liferay Homeの`portal-ext.properties`のファイルに、メールセッションを参照してください：

       mail.session.jndi.name=mail/MailSession
   
tcサーバーがメールセッションを管理できるよう設定し、@product@が使えるように設定しました。



##  @product@をデプロイする

これで、@product@ WARファイルを使用して@product@をデプロイする準備が整いました。

1. @product@をマニュアルで、何も記録されていない状態のtcサーバーインスタンスにインストールする場合は、`[TCSERVER_INSTANCE_HOME]/servers/dxp-server/webapps/ROOT`ディレクトリに含まれている内容を削除してください 。これにより、デフォルトのホームページが削除されます。



2. @product@ の`.war`ファイルを`[TCSERVER_INSTANCE_HOME]/servers/dxp-server/webapps/ROOT`に抽出してください。



   これができたら、@product@を立ち上げてみてください。



3. ランタイムインスタンスの`[TCSERVER_INSTANCE_HOME]/developer-4.0.0.RELEASE`フォルダに移動してtcサーバーを起動し 、次のコマンドを実行します：

       tcserver run -i ../servers dxp-server
   
これでtcサーバーに@product@を正常にインストールかつデプロイできました！



| @product@をデプロイした後、`PhaseOptimizer`を含む以下のような警告とログメッセージが表示される場合があります。これらの警告は無視してください。
| 必要ないログメッセージが頻繁に表示されないように、必ずアプリケーションサーバーのログレベルまたはログフィルターを調整してください。
| 
|     May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
|     WARNING: Skipping pass gatherExternProperties
|     May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
|     WARNING: Skipping pass checkControlFlow
|     May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
|     INFO: pass supports: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, modules, exponent operator (**), async function, trailing comma in param list]
|     current AST contains: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, exponent operator (**), async function, trailing comma in param list, object literals with spread, object pattern rest]
