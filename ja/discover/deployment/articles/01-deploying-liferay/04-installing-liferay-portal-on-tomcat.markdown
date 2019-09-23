---
header-id: installing-product-on-tomcat
---

# Tomcatに@product@をインストールする方法

@product-ver@Tomcat 9にバンドルされているLiferay DXP 7.1は、[カスタマーポータル](https://web.liferay.com/group/customer/dxp/downloads/7-1)（DXP）または[Liferay Downloads](https://www.liferay.com/downloads)（ポータルCE）で入手できます。
Tomcatバンドルには、クリーンなTomcat 9アプリケーションサーバーに@product@ をインストールするために必要なJAR、スクリプト、および設定ファイルが含まれています。
@product@ Tomcatバンドルからこれらのファイルをコピーすると、Tomcatへの@product@のインストールが容易になります。

バンドルファイルをコピーする（推奨）または、ファイルをダウンロードして作成するかにかかわらず、[DXP](https://web.liferay.com/group/customer/dxp/downloads/7-1)または[Portal CE](https://www.liferay.com/downloads)用にこれらのファイルをダウンロードしてください。

- @product@ WARファイル
- 依存関係のZIPファイル

- OSGi JARファイルのZIPファイル

以下はTomcatに@product@をインストールするための基本的な手順です。

- [アプリケーションサーバーへの依存関係のインストール](#installing-liferay-dependencies)

- [@product@用にアプリケーションサーバーを設定する](#tomcat-configuration)
- [Liferay DXP WARファイルをアプリケーションサーバーにデプロイする](#deploying-liferay)

[*Liferay Home*](/discover/deployment/-/knowledge_base/7-1/installing-liferay#liferay-home)は、Tomcatサーバーのフォルダを含むフォルダです。Liferay DXPがTomcat上にインストールされ、デプロイされると、Liferay Homeには、Tomcatサーバーフォルダー`data`、`deploy`、 `license`、`osgi`フォルダーが作成されます。`$TOMCAT_HOME`はTomcatサーバーフォルダーのことを指しています。このフォルダーは、`tomcat-[version]`または、 `apache-tomcat-[version]`という名前になっています。

## Liferay DXP の依存ファイルをインストールする

@product@は@product@ Tomcatバンドルに含まれる多くのJARに依存します。バンドル内のJARの中には必須ではないファイルもありますが、有用なJARもあります。Liferay DXP Tomcat バンドルを使用していない場合は、必要なJARをサードパーティからダウンロードできます。

1. フォルダー`$TOMCAT_HOME/lib/ext`が存在しない場合は作成し、依存関係のZIPからそのフォルダーにJARを抽出します。JARのリストです:

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

2. 次のJARをダウンロードするか、@product@ Tomcatバンドルから`$TOMCAT_HOME/lib/ext`フォルダにコピーします:

   - [`activation.jar`](http://www.oracle.com/technetwork/java/javase/jaf-136260.html)
   - [`ccpp.jar`](http://mvnrepository.com/artifact/javax.ccpp/ccpp/1.0)
   - [`jms.jar`](http://www.oracle.com/technetwork/java/docs-136352.html)
   - [`jta.jar`](http://www.oracle.com/technetwork/java/javaee/jta/index.html)
   - [`jutf7.jar`](http://mvnrepository.com/artifact/com.beetstra.jutf7/jutf7)
   - [`mail.jar`](http://www.oracle.com/technetwork/java/index-138643.html)
   - [`persistence.jar`](http://mvnrepository.com/artifact/org.eclipse.persistence/javax.persistence/2.1.1)
   - [`support-tomcat.jar`](http://mvnrepository.com/artifact/com.liferay.portal/com.liferay.support.tomcat)

3. データベースのJDBCドライバを`$CATALINA_BASE/lib/ext`フォルダにコピーします。一般的なドライバは次のとおりです。

   - [`mariadb.jar`](https://downloads.mariadb.org/)
   - [`mysql.jar`](http://dev.mysql.com/downloads/connector/j)
   - [`postgresql.jar`](https://jdbc.postgresql.org/download/postgresql-42.0.0.jar)

4. Liferay Homeに`osgi`フォルダを作成します。OSGiのZIPファイルからフォルダー（`configs`、`core`など）を`osgi`フォルダーに抽出します。この`osgi`フォルダはLiferay DXPのOSGiランタイムに必要なモジュールを提供します。

チェックポイント:

1. `$CATALINA_BASE/lib/ext`フォルダにはこれらのJARファイルが含まれています。

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

2. `$CATALINA_BASE/lib/ext`フォルダには、これらのJARが含まれています：

   - `configs`
   - `core`
   - `marketplace`
   - `modules`
   - `portal`
   - `static`
   - `test`
   - `war`

## Tomcatの設定

次に、@product@を起動できるようにTomcatを設定します。

- 環境変数を設定する
- @product@用のWebアプリケーションコンテキストの指定
- プロパティと記述子の設定

オプションとして、@product@用にこれらを管理するようにTomcatを設定することができます。

- [データソース](#database-configuration)
- [メールセッション](#mail-configuration)

次に、@product@を起動できるようにTomcatを設定します。

1. バンドルを使用している場合は、バンドル内の`setenv.bat`と`setenv.sh`ファイルを、`$CATALINA_BASE/bin`フォルダーにコピーします。そうでない場合は、これらのスクリプトを作成します。

   これらのスクリプトはCatalinaの複数のJVM オプションを設定し、 TomcatのServletのコンテナーを含んでいます。これらのオプションの中にはJavaランタイム環境の場所が含まれます。ご使用のサーバー全体的でこの環境が利用できない場合は、ご利用の環境でのJavaランタイムへのパスをこのファイル内に設定してTomcatが動作できるようにします。これには、OSの`JAVA_HOME`環境変数が@product@がサポートしているJREを指すように設定します:

       export JAVA_HOME=/usr/lib/jvm/java-8-jdk
       export PATH=$JAVA_HOME/bin:$PATH

   次に、@product@をサポートするようにCatalinaのJVMオプションを設定します。

   Unix:

       CATALINA_OPTS="$CATALINA_OPTS -Dfile.encoding=UTF8 -Djava.net.preferIPv4Stack=true -Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false -Duser.timezone=GMT -Xmx2048m -XX:MaxMetaspaceSize=512m"

   Windows:

       set "CATALINA_OPTS=%CATALINA_OPTS% -Dfile.encoding=UTF8 -Djava.net.preferIPv4Stack=true -Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false -Duser.timezone=GMT -Xmx2048m -XX:MaxMetaspaceSize=512m"

   上記はファイルのエンコードを UTF-8（IPv6よりIPv4スタックが好ましい）に設定します。 これにより静的またはFinal変数に関するガーベジコレクションのバグを回避し、（これはLiferay DXP本体に存在するバグではなく、ログシステムに支障が出るのを回避します）タイムゾーンをGMTに設定し、JVMに2GBのRAMを提供し、Metaspaceを500MBに限定します。

   インストール後、パフォーマンスを向上させるためにシステム（これらのJVMオプションを含む）を調整してください。

2. @product@ Tomcatバンドルがある場合は、その`$CATALINA_BASE/conf/Catalina/localhost/ROOT.xml`ファイルをアプリケーションサーバーの対応する場所にコピーします。ファイルパスが存在しない場合は作成します。@product@ Tomcatバンドルがない場合は、`ROOT.xml`ファイルを作成してください。

   `ROOT.xml`ファイルには、LiferayのDXPのためのWebアプリケーションのコンテキストを指定します。
`ROOT.xml`このようになります：

       <Context crossContext="true" path="">
       
       <!-- JAAS -->
       
       <!--<Realm
       className="org.apache.catalina.realm.JAASRealm"
       appName="PortalRealm"
       userClassNames="com.liferay.portal.kernel.security.jaas.PortalPrincipal"
       roleClassNames="com.liferay.portal.kernel.security.jaas.PortalRole"
       />-->
       
       <!--
       Uncomment the following to disable persistent sessions across reboots.
       -->
       
       <!--<Manager pathname="" />-->
       
       <!--
       Uncomment the following to not use sessions. See the property
       "session.disabled" in portal.properties.
       -->
       
       <!--<Manager className="com.liferay.support.tomcat.session.SessionLessManagerBase" />-->
       
       <Resources>
       <PreResources
       base="${catalina.base}/lib/ext/portal"
       className="com.liferay.support.tomcat.webresources.ExtResourceSet"
       webAppMount="/WEB-INF/lib"
       />
       </Resources>
       </Context>

   設定`crossContext="true"`すると、複数のWebアプリケーションが同じクラスローダーを使用できます。この設定には、JAAS領域の設定、永続するセッションの無効化、セッションの完全な無効化のためのコメント付きの説明とタグが含まれています。

3. `$CATALINA_BASE/conf/catalina.properties`ファイルを開いて、`>$CATALINA_BASE/lib/ext`にある`common.loaderproperty`にこの値を追加することで、CatalinaによるJARへのアクセスを提供します。

       ,"${catalina.home}/lib/ext/global","${catalina.home}/lib/ext/global/*.jar","${catalina.home}/lib/ext","${catalina.home}/lib/ext/*.jar"

4. 必ずUTF-8 URIエンコードを使用してください。Liferay DXP Tomcatバンドルがある場合は、`$CATALINA_BASE/conf/server.xml`ファイルをサーバーにコピーしてください。そうでない場合は、`$CATALINA_BASE/conf/server.xml`ファイルを開き、`redirectPort=8443`を使用するHTTPおよびAJPコネクターに`URIEncoding="UTF-8"`属性を追加してください。以下は例です:

   旧:

       <Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" />

   新

       <Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" URIEncoding="UTF-8" />

   旧:

       <Connector port="8009" protocol="AJP/1.3" redirectPort="8443" />

   新

       <Connector port="8009" protocol="AJP/1.3" redirectPort="8443" URIEncoding="UTF-8" />

5. UNIX、Linux、またはMac OSを使っている場合は、それぞれのフォルダで下記のコマンドを実行し、`$CATALINA_HOME/bin`と`$CATALINA_BASE/bin`フォルダの中に実行可能なシェルスクリプトを作成します。

   `chmod a+x *.sh`

チェックポイント:

この時点で、アプリケーションサーバーが設定され@product@を起動できます。

1. ファイルのエンコーディング、ユーザーのタイムゾーン、および優先プロトコルスタックが`setenv.sh`に設定されています。

2. デフォルトの使用可能メモリとメタスペース制限が設定されています。

3. `$CATALINA_BASE/conf/Catalina/localhost/ROOT.xml`Webアプリケーションのコンテキストを宣言します。

4. `$CATALINA_BASE/conf/catalina.properties`の`common.loader`は、CatalinaがJARの`$CATALINA_BASE/lib/ext`へのアクセスできよう許可を出します。

5. `$CATALINA_BASE/conf/server.xml`は、UTF-8 encodingを設定します。

6. Tomcatの`bin`フォルダ内のスクリプトは実行可能です。

### データベースの設定

データベース設定を処理する上で一番簡単な方法は @product@にデータソースを管理させることです。 @product@の
[基本設定](/discover/deployment/-/knowledge_base/7-1/installing-liferay#using-liferays-setup-wizard)
ページは@product@にビルトインされたデータソースの設定ができます。@product@に備わっているデータソースを使用する場合は、このセクションは必要ではありません。

Tomcatでデータソースを管理する場合は、次の手順で行います:

1. データベースサーバーがインストールされ、動作していることを確かめます。別マシーンにインストールしている場合は、@product@からアクセスできることを確かめます。

2. `$CATALINA_BASE/conf/Catalina/localhost/ROOT.xml`を開いて、`Resource``Context`としてWebアプリケーションにデータソースを追加します。

       <Context...>
       ...
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

   上記のリソース定義は、`root`というユーザー名でパスワードが`root`というユーザーが含まれる`lportal`という名前のMySQLのものです。これらの値を自分のものに置き換えてください。

3. `portal-ext.properties`では、Liferay Home内のファイル、データソースを指定します。

       jdbc.default.jndi.name=jdbc/LiferayPool

Tomcatが管理するデータソースを作成し、それを使用するように@product@を設定しました。メールセッションの設定は次です。

### メール設定

データベースと同様、メール設定の処理もLiferay DXPに行わせるのが一番簡単な方法です。Liferay DXPの内蔵メールセッションを使用したい場合は、このセクションを飛ばしてコントロールパネルで[メールセッションを設定](/discover/deployment/-/knowledge_base/7-1/installing-liferay#configuring-mail)してください。

Tomcatとのメールセッションを管理したい場合は、次の手順に従ってください。

1. `$CATALINA_BASE/conf/Catalina/localhost/ROOT.xml`を開いて、`Resource``Context`としてWebアプリケーションにデータソースを追加します。サンプルのメールセッション値を必ず自分のものに置き換えてください。

       <Context...>
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

2. Liferayのホーム内の`portal-ext.properties`ファイル、メールセッションを参照します。

       mail.session.jndi.name=mail/MailSession

Tomcatが管理するためのメールセッションを作成し、それを使用するように@product@を設定しました。

## @product@をデプロイする

これで、@product@ DXP WARファイルを使用して@product@をデプロイする準備が整いました。

1. @product@をクリーンなTomcatサーバーに手動でインストールする場合は、`$CATALINA_BASE/webapps/ROOT`フォルダの内容を削除してください。これにより、デフォルトのTomcatホームページが削除されます。

2. @product@ `.war`ファイルを`$CATALINA_BASE/webapps/ROOT`に展開します。

   Tomcatで @product@を起動する準備ができました。

3. `$CATALINA_HOME/bin`へ行き、`./startup.sh`を実行し、Tomcatを実行します。代用として、`./catalina.sh run`を実行し、@product@のログファイルを使用し、Tomcatを開始することも可能です。ログは起動時のアクティビティを監査し、展開のデバッグに役立ちます。

これで、Tomcatに@product@を正常にインストールしデプロイできました。

| @product@を展開した後、`PhaseOptimizer`を含む以下のような過剰な警告とログメッセージが表示される場合があります。これらは良性なので無視することができます。このようなログメッセージを回避するために、必ずアプリケーションサーバーのログレベルまたはログフィルターを調整してください。
| 
|     May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
|     WARNING: Skipping pass gatherExternProperties
|     May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
|     WARNING: Skipping pass checkControlFlow
|     May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
|     INFO: pass supports: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, modules, exponent operator (**), async function, trailing comma in param list]
|     current AST contains: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, exponent operator (**), async function, trailing comma in param list, object literals with spread, object pattern rest]
