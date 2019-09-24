---
header-id: installing-product-on-jboss-eap-ja
---

# JBoss EAPに@product@をインストールする

[TOC levels=1-4]

JBoss EAP 7.1に@product@をインストールするには3つの手順があります
:

- [アプリケーションサーバーに@product@依存関係をインストールします](#installing-dependencies)

- [アプリケーションサーバーを@product@用に設定します](#configuring-jboss)

- [アプリケーションサーバーへ@product@ WARファイルをインストールします](#deploying-product)


続行する前に、[Customer Portal](https://web.liferay.com/group/customer/dxp/downloads/7-1)からこれらのファイルをダウンロードしてください :


- @product@ WARファイル
- 依存関係のZIPファイル

- OSGi JARsのZIPファイル

[*Liferay Home*](/discover/deployment/-/knowledge_base/7-0/installing-liferay#liferay-home)のフォルダの中に、JBossサーバーフォルダが入っています。@product@をインストールおよびデプロイしたら、Liferayのホームフォルダの中にJBossサーバーのフォルダ、`data`、`deploy`、`logs`、および`osgi`フォルダが含まれています。`$JBOSS_HOME`はJBossサーバーのフォルダのことです。 このフォルダには通常`jboss-eap-[version]`という名前が付けられています。

## 依存関係のインストール

@product@は、いくつかのLiferay固有の、およびサードパーティーのJARに依存しています。
以下の説明に従って、必要なJARをダウンロードしてインストールしてください。

1. `$JBOSS_HOME/modules/com/liferay/portal/main`というフォルダがない場合は新たに作成し、そのフォルダーに依存関係のZIPからJARを抽出します。

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
   
2. データベースドライバ`.jar`ファイルをダウンロードして、同じフォルダにコピーします。
例えば、[ copy MySQL's driver](http://dev.mysql.com/downloads/connector/j/) を`$JBOSS_HOME/modules/com/liferay/portal/main`フォルダにコピーします。

3. `$JBOSS_HOME/modules/com/liferay/portal/main`フォルダに`module.xml`ファイルを作成し、以下の設定を挿入します。

       <?xml version="1.0"?>
       
       <module xmlns="urn:jboss:module:1.0" name="com.liferay.portal">
       <resources>
       <resource-root path="com.liferay.petra.concurrent.jar" />
       <resource-root path="com.liferay.petra.executor.jar" />
       <resource-root path="com.liferay.petra.function.jar" />
       <resource-root path="com.liferay.petra.io.jar" />
       <resource-root path="com.liferay.petra.lang.jar" />
       <resource-root path="com.liferay.petra.memory.jar" />
       <resource-root path="com.liferay.petra.nio.jar" />
       <resource-root path="com.liferay.petra.process.jar" />
       <resource-root path="com.liferay.petra.reflect.jar" />
       <resource-root path="com.liferay.petra.string.jar" />
       <resource-root path="com.liferay.registry.api.jar" />
       <resource-root path="hsql.jar" />
       <resource-root path="mysql.jar" />
       <resource-root path="portal-kernel.jar" />
       <resource-root path="portlet.jar" />
       </resources>
       <dependencies>
       <module name="javax.api" />
       <module name="javax.mail.api" />
       <module name="javax.servlet.api" />
       <module name="javax.servlet.jsp.api" />
       <module name="javax.transaction.api" />
       </dependencies>
       </module>
   
   別のデータベースを使用している場合は、デートベース用にMySQL` .jar`をドライバJAR（例：HSQL、PostgreSQLなど）に置き換えてください。

4. Liferay Homeフォルダの中に`osgi`フォルダを作成します。`osgi`フォルダの中にダウンロードしたOSGi ZIPファイルを抽出します。

   この`osgi`フォルダは、@product@のOSGiランタイムに必要なモジュールを提供します。

**確認事項:**

1. `$JBOSS_HOME/modules/com/liferay/portal/main`フォルダに以下のファイルがあることを確認します：

   - `com.liferay.petra.concurrent`
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
   - `portal-kernel.jar`
   - `portlet.jar`
   - MySQL ConnectorなどのデータベースJAR

2. `module.xml`には`<resource-root-path>`内の全てのJARsがリストされています。

3. `osgi`フォルダの中には、以下のサブフォルダがあります。

   - `configs`
   - `core`
   - `marketplace`
   - `modules`
   - `portal`
   - `static`
   - `test`
   - `war`

 これで `.jar`ファイルの準備が整いました。 

## Standalone ModeとDomain Modeで@product@をJBoss EAPで実行する

JBoss EAPは、*standalone* modeまたは*domain* modeで起動できます。Domain modeでは、複数のアプリケーションサーバーインスタンスを単一のコントロールポイントから管理できます。このようなアプリケーションサーバーの集まりを*domain*と言います。
Standalone modeとDomain modeの詳細については、[JBoss EAP Product Documentation](https://access.redhat.com/documentation/en-us/red_hat_jboss_enterprise_application_platform/7.1/html/introduction_to_jboss_eap/overview_of_jboss_eap#operating_modes)のこのトピックに関するセクションを参照してください 。

@product@は、JBoss EAPをstandalone modeで実行している場合はサポートしますが、domain modeで実行している場合はサポートしません。JBossは、（展開または非展開の）ファイルをコピーすることによってデプロイの内容を管理するため、@product@のhot-deployは管理されたデプロイでは機能しません。これにより、JSP hooksおよびExtプラグインが意図したとおりに機能しなくなってしまいます。例えば、@product@のJSPオーバーライドメカニズムはアプリケーションサーバーに依存しているため、JSPフックは管理されているドメインモードで実行されているJBoss EAPでは機能しません。しかし、これらの機能はどちらも廃止予定であるため、使用しない可能性があります。

コマンドラインインターフェースは、domain modeのデプロイにお勧めします。

| **注：**これは@product@が複数のJBossサーバー上のクラスタ環境で実行されるのを妨げるものではありません。JBoss EAPサーバー上で実行している@product@インスタンスのクラスターを、Standalone modeで実行できるように設定できます。@product@クラスターの設定については、[@product@ Clustering](/discover/deployment/-/knowledge_base/7-1/liferay-clustering-ja)のセクションを参照してください 。
| 

## JBossの設定方法

@product@を実行するためにJBossを設定するには、以下のことをします：

- 環境変数を設定する
- プロパティと記述子の設定
- 不要な設定を削除する

必要に応じて、@product@のために以下のものを管理するようにJBossを設定できます:

- [Data source](#database-configuration)
- [Mail session](#mail-configuration)

@product@を実行できるようにするためにはまずJBossを設定することから始めます。

以下の変更を
`$JBOSS_HOME/standalone/configuration/standalone.xml`に加えます：

1. Closing`</extensions>`タグを見つけます。そのタグのすぐ下に、次のシステムプロパティを挿入します。

       <system-properties>
       <property name="org.apache.catalina.connector.URI_ENCODING" value="UTF-8" />
       <property name="org.apache.catalina.connector.USE_BODY_ENCODING_FOR_QUERY_STRING" value="true" />
       </system-properties>
   
2. `<level name="INFO"/>`タグのすぐ下に、以下の`<filter-spec>`タグを`<console-handler>`タグに追加してください。

       <filter-spec value="not(any(match(&quot;WFLYSRV0059&quot;),match(&quot;WFLYEE0007&quot;)))" />
   
3. 以下の抜粋のように、`deployment-timeout="360"`を設定して、デプロイメントスキャナーのタイムアウトを追加します。

       <subsystem xmlns="urn:jboss:domain:deployment-scanner:2.0">
       <deployment-scanner deployment-timeout="360" path="deployments" relative-to="jboss.server.base.dir" scan-interval="5000"/>
       </subsystem>
   
4.  `<subsystem xmlns="urn:jboss:domain:security:2.0">`で定義された構成部分に次のJAASセキュリティドメインをセキュリティサブシステム`<security-domains>`に追加します。

       <security-domain name="PortalRealm">
       <authentication>
       <login-module code="com.liferay.portal.security.jaas.PortalLoginModule" flag="required" />
       </authentication>
       </security-domain>
   
5. ウェルカムコンテンツを提供する2つのコードスニペットを削除します：

       <location name="/" handler="welcome-content"/>
   
   と

       <handlers>
       <file name="welcome-content" path="${jboss.home.dir}/welcome-content"/>
       </handlers>
   
6. `<jsp-config/>`タグを検索して、`development`、`source-vm`、および`target-vm`タグの属性を設定します。これが完了すると、タグは次のようになります：

       <jsp-config development="true" source-vm="1.8" target-vm="1.8"/>
   
**確認事項:**

続行する前に、`standalone.xml`ファイルに次のプロパティが設定されていることを確認してください:

1. 新しい`<system-property>`が追加されている。



2. 新しい`<filter-spec>`が追加されている。



3. `<deployment-timeout>`が`360`に設定されている。



4. 新しい`<security-domain>`が作成されている。




5. ウェルカムコンテンツが削除されている。



6. `<jsp-config>`タグに、新しい属性が含まれている。



以上のことが確認できたら、JVMと立ち上げ時のスクリプトを設定します。



`$JBOSS_HOME/bin`フォルダの中の、スタンドアロンドメインの設定スクリプトファイル`standalone.conf`を以下のように変更する必要があります（Windowsの場合`standalone.conf.bat`）：


- ファイルエンコーディングを設定する。 

- ユーザーのタイムゾーンを設定する。 

- 希望するプロトコルスタックを設定する。

- 利用可能なデフォルトのメモリ容量を増やす。



使用しているオペレーティングシステムに応じて、以下の編集を行います：

**Windows**

1. 以下に示されているように、最初の`JAVA_OPTS`の割り当てをコメントアウトします。



       rem set "JAVA_OPTS=-Xms1G -Xmx1G -XX:MetaspaceSize=96M -XX:MaxMetaspaceSize=256m"
   
2. ファイルの末尾にある`:JAVA_OPTS_SET`の一行上に`JAVA_OPTS`を追加します:


       set "JAVA_OPTS=%JAVA_OPTS% -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Djboss.as.management.blocking.timeout=480 -Duser.timezone=GMT -Xmx2048m -XX:MaxMetaspaceSize=384m"
   
**Unix**

1. `if [ "x$JAVA_OPTS" = "x" ];`の下を、この`JAVA_OPTS`に置き換えます： 

       JAVA_OPTS="-Xms1303m -Xmx1303m -XX:MetaspaceSize=96M -XX:MaxMetaspaceSize=256m -Djava.net.preferIPv4Stack=true
   
   以下も置き換えます：



       JAVA_OPTS="-Djava.net.preferIPv4Stack=true"
   
2. ファイルの末尾に以下を追加します：

       JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Djboss.as.management.blocking.timeout=480 -Duser.timezone=GMT -Xmx2048m -XX:MaxMetaspaceSize=512m"
   
| **注：** JBossサーバーでIBM JDKを使用する予定の場合は、いくつか追加の手順を実行する必要があります。
| まず、`$JBOSS_HOME/modules/com/liferay/portal/main/module.xml`ファイルに移動して 、`<dependencies>`要素内に次の依存関係を挿入します。
| 
| 
|     <module name="ibm.jdk" />
| 
| 次に`$JBOSS_HOME/modules/system/layers/base/sun/jdk/main/module.xml`ファイルに移動して 、`<paths>...</paths>`要素内に次のパス名を挿入します：
| 
|     <path name="com/sun/crypto" />
|     <path name="com/sun/crypto/provider" />
|     <path name="com/sun/image/codec/jpeg" />
|     <path name="com/sun/org/apache/xml/internal/resolver" />
|     <path name="com/sun/org/apache/xml/internal/resolver/tools" />
| 
| 追加されたパスは、ポータルデプロイの例外およびイメージアップロードに関する問題を解決します。

**確認事項:**

これで、アプリケーションサーバーのJVM設定は完了しました。

1. ファイルのエンコーディング、ユーザータイムゾーン、希望するプロトコルスタックは`standalone.conf.bat`内の`JAVA_OPTS`で設定されています。

2. 利用可能なデフォルトのメモリ量が増えています。

JBossに@product@をインストールするためのスクリプトの変更はこれで完了しました。次にデータベースとメールを設定します。



## データベースの設定方法

データベース設定を処理する上で一番簡単な方法は @product@にデータソースを管理させることです。[Basic Configuration](/discover/deployment/-/knowledge_base/7-1/installing-liferay-ja#using-liferays-setup-wizard-ja)のページでは、@product@の組み込みデータソースを設定できます。
組み込みのデータソースを使用したい場合は、このセクションを飛ばしてください。



このセクションではMySQLデータベースの設定方法について説明します。別のデータベースを使用している場合は、必要に応じてデータソースとドライバのスニペットを変更します。



JBossにデータソースを管理させたい場合は、次の手順に従ってください：

1. `<datasources>`要素内にデータソースを追加します。

       <datasource jndi-name="java:jboss/datasources/ExampleDS" pool-name="ExampleDS" enabled="true" jta="true" use-java-context="true" use-ccm="true">
       <connection-url>jdbc:mysql://localhost/lportal</connection-url>
       <driver>mysql</driver>
       <security>
       <user-name>root</user-name>
       <password>root</password>
       </security>
       </datasource>
   
   データベース名（例`lportal`）、ユーザー名、およびパスワードを適切なものに置き換えてください。



   | **注：**データソース`jndi-name`を別のものに変更する必要がある場合は、`datasource`の要素も`<default-bindings>`タグ内で編集する必要があります。

2. `<datasources>`要素内にある`standalone.xml`ファイルの`<drivers>`要素にドライバを追加します。

       <drivers>
       <driver name="mysql" module="com.liferay.portal">
       <driver-class>com.mysql.jdbc.Driver</driver-class>
       </driver>
       </drivers>
   
   最終的なデータソースサブシステムは次のようになります：

       <subsystem xmlns="urn:jboss:domain:datasources:5.0">
       <datasources>
       <datasource jndi-name="java:jboss/datasources/ExampleDS" pool-name="ExampleDS" enabled="true" jta="true" use-java-context="true" use-ccm="true">
       <connection-url>jdbc:mysql://localhost/lportal</connection-url>
       <driver>mysql</driver>
       <security>
       <user-name>root</user-name>
       <password>root</password>
       </security>
       </datasource>
       <drivers>
       <driver name="mysql" module="com.liferay.portal"/>
       </drivers>
       </datasources>
       </subsystem>
   
3. Liferayホーム内の`portal-ext.properties`ファイルに、データソースを指定します。

       jdbc.default.jndi.name=java:jboss/datasources/ExampleDS
   
データソースを設定したので、次はメールの設定方法について説明します。

## メール設定方法

データベースと同様、メール設定の処理も@product@に行わせるのが一番簡単な方法です。@product@の組み込みメールセッションを使用したい場合は、このセクションを飛ばしてControl Panelで[メールセッションを設定してください](/discover/deployment/-/knowledge_base/7-1/installing-liferay-ja#configuring-mail-ja)。



JBoss上でメールセッションを管理したい場合は、次の手順に従ってください：



1. `$WILDFLY_HOME/standalone/configuration/standalone.xml`のファイル上でメールサブシステムを以下のように指定します：

       <subsystem xmlns="urn:jboss:domain:mail:3.0">
       <mail-session jndi-name="java:jboss/mail/MailSession" >
       <smtp-server ssl="true" outbound-socket-binding-ref="mail-smtp">
       <login username="USERNAME" password="PASSWORD"/>
       </smtp-server>
       </mail-session>
       </subsystem>
       ...
       <socket-binding-group name="standard-sockets" default-interface="public" port-offset="${jboss.socket.binding.port-offset:0}">
       ...
       <outbound-socket-binding name="mail-smtp">
       <remote-destination host="smtp.gmail.com" port="465"/>
       </outbound-socket-binding>
       </socket-binding-group>
   
2. Liferay Homeの`portal-ext.properties`のファイルに、メールセッションを参照してください：

       mail.session.jndi.name=java:jboss/mail/MailSession
   
これでメールが届くようになります。次に、@product@をJBossアプリケーションサーバーにデプロイします。

##  @product@をデプロイする

1. `$JBOSS_HOME/standalone/deployments/ROOT.war`のフォルダがすでにJBossインストールに存在する場合は、そのサブフォルダとファイルをすべて削除します。
存在しない場合は、`$JBOSS_HOME/standalone/deployments/ROOT.war`という名前の新しいフォルダを作成します 。



2. @product@` .war`ファイルを`ROOT.war`フォルダに解凍します。



3. `ROOT.war`のデプロイを実行するために、名前なしのファイル`ROOT.war.dodeploy`を、`$JBOSS_HOME/standalone/deployments/`フォルダに作成します。
立ち上げ時に、JBossはこのファイルの存在を検出してWebアプリケーションとしてデプロイします。



4. `$JBOSS_HOME/bin `に移動して、実行されている`standalone.bat`または`standalone.sh`を使ってJBossアプリケーションを立ち上げます。



これでJBossに@product@をデプロイできました！


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
