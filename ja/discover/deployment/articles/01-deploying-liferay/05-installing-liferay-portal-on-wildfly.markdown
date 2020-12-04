---
header-id: installing-product-on-wildfly
---

# Wildflyに@product@をインストールする<x1/><x2/>

[TOC levels=1-4]

Wildfly 11にバンドルされている@product-ver@は、[カスタマーポータル](https://web.liferay.com/group/customer/dxp/downloads/7-1)（DXP）または[Liferay Downloads](https://www.liferay.com/downloads)（ポータルCE）で入手できます。
@product-ver@は、Wildfly 10およびWildfly 11へのデプロイをサポートしています。既存のWildflyアプリケーションサーバーに手動で@product@をインストールする場合でも、依存関係を簡単に収集するために@product@ Wildflyバンドルをダウンロードすると便利です。先へ進む前に、[DXP](https://web.liferay.com/group/customer/dxp/downloads/7-1)と[Portal CE](https://www.liferay.com/downloads)用にこれらのファイルもダウンロードしてください。

- @product@ WARファイル
- 依存関係のZIPファイル

- OSGi JARのZIPファイル

@product@を手動でインストールするには、3つのステップがあります。

- [アプリケーションサーバーへ依存関係をインストールする](#installing-dependencies)

- [@product@用にアプリケーションサーバーを設定する](#configuring-wildfly)
- [@product@ WARファイルをアプリケーションサーバーにデプロイする](#deploying-product)

[*Liferay Home*](/docs/7-1/deploy/-/knowledge_base/d/installing-liferay#liferay-home)は、Wildflyサーバーのフォルダを含むフォルダです。@product@がインストールされ、デプロイされると、Liferay Homeフォルダーには、Wildflyサーバーフォルダー、`data`、`deploy`、 `logs`、そして`osgi`フォルダーが作成されます。`$WILDFLY_HOME`はWildflyサーバーフォルダを参照します。通常は`wildfly-[version]`という名前になっています。

## Liferay DXP の依存ファイルをインストールする

@product@は@product@ Wildflyバンドルに含まれる多くのJARに依存します。バンドル内のJARの中には必須ではないファイルもありますが、それでも有用です。@product@ Wildflyバンドルがない場合は、下記のようにサードパーティから必要なJARをダウンロードしてください。

1. `$WILDFLY_HOME/modules/com/liferay/portal/main`フォルダが存在しない場合は作成し、そのフォルダに依存関係のZIP JARを抽出します。

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

2. データベースドライバの`.jar`ファイルをダウンロードして、同じフォルダにコピーします。たとえば、[MySQLのドライバ](http://dev.mysql.com/downloads/connector/j/)を` $WILDFLY_HOME/modules/com/liferay/portal/main`フォルダに コピーします。`mariadb.jar`、`mysql.jar`、および`postgresql.jar`ドライバのJARがWildflyバンドルでも利用できます。

3. `$WILDFLY_HOME/modules/com/liferay/portal/main`フォルダに`module.xml`ファイルを作成し、この設定を挿入します。

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

   別のデータベースを使用する場合は、MySQL `.jar`をご使用のデータベース用のドライバJAR（HSQL、PostgreSQLなど）に置き換えてください。

4. Liferay Homeフォルダに`osgi`フォルダを作成します。ダウンロードしたOSGi ZIPファイルを`osgi`フォルダに解凍します。

   この`osgi`フォルダは@product@のOSGiランタイムに必要なモジュールを提供します。

**チェックポイント:**

1. この時点で、`$WILDFLY_HOME/modules/com/liferay/portal/main`フォルダ内に次のファイルがあるはずです 。

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
   - MySQL ConnectorなどのデータベースJAR。

2. `module.xml`には`<resource-root-path>`要素内のすべてのJARが列挙されています。

3. この`osgi`フォルダには、次のサブフォルダがあります。

   - `configs`
   - `core`
   - `marketplace`
   - `modules`
   - `portal`
   - `static`
   - `test`
   - `war`

 `.jar`ファイルの準備ができました。

## Wildflyで@product@を実行する場合のスタンドアロンモードとドメインモード<x1/><x2/>

Wildflyは、*スタンドアロンモード*または*ドメインモード*のどちらでも起動できます。ドメインモードでは、複数のアプリケーションサーバーインスタンスを単一の制御点から管理できます。このようなアプリケーションサーバーの集まりは*ドメイン*と呼ばれます。
スタンドアロンモードとドメインモードの詳細については、[Wildfly Admin Guide](https://docs.jboss.org/author/display/WFLY/Admin+Guide#AdminGuide-Operatingmodes)のこのトピックに関するセクションを参照してください 。
@product@は、スタンドアロンモードではWildflyを完全にサポートしていますが、ドメインモードではサポートしていません。

ドメインモードではWildflyで@product@を実行できますが、この方法は完全にはサポートされていません。特に、@product@のホットデプロイは管理されたデプロイでは機能しません。なぜなら、Wildflyはファイルをコピーすることによって管理されたデプロイメントの内容を管理するからです（展開または非展開）これにより、JSPフックとExtプラグインが意図したとおりに機能しなくなります。たとえば、@product@のJSPオーバーライドメカニズムはアプリケーションサーバーに依存しているため、JSPフックは、管理ドメインモードで実行されているWildflyでは機能しません。これらの機能はどちらも非推奨であるため、使用されない可能性があります。

コマンドラインインターフェイスは、ドメインモードのデプロイに推奨されます。


**注：**これは@product@が複数のWildflyサーバー上のクラスター環境で実行されるのを妨ぐわけではありません。スタンドアロンモードのWildflyサーバー上で実行されている@product@インスタンスのクラスターをセットアップできます。@product@クラスタの設定方法については、このガイドの[@product@ クラスタリング](/docs/7-1/deploy/-/knowledge_base/d/liferay-clustering)に関する章を参照してください 。

## Wildflyの設定<x1/><x2/>

次に、@product@を起動できるようにWildflyを設定します。

- 環境変数を設定する
- プロパティとディスクリプタを設定する
- 不要な設定を削除する

オプションとして、@product@のデータソースとメールセッションを管理するようにWildflyを設定することができます。


次に、@product@を起動できるようにWildflyを設定します。

`$WILDFLY_HOME/standalone/configuration/standalone.xml`へ以下の変更を行います。


1. `</extensions>`という終了タグを見つけます。そのタグのすぐ下に、次のシステムプロパティを挿入します。

       <system-properties>
       <property name="org.apache.catalina.connector.URI_ENCODING" value="UTF-8" />
       <property name="org.apache.catalina.connector.USE_BODY_ENCODING_FOR_QUERY_STRING" value="true" />
       </system-properties>

2. `<level name="INFO"/>`タグのすぐ下にある`<console-handler>`タグ内に、以下の`<filter-spec>`タグを追加します。

       <filter-spec value="not(any(match(&quot;WFLYSRV0059&quot;),match(&quot;WFLYEE0007&quot;)))" />

3. `deployment-timeout="360"`を以下の抜粋のように設定して、デプロイメントスキャナーのタイムアウトを追加し ます。


       <subsystem xmlns="urn:jboss:domain:deployment-scanner:2.0">
       <deployment-scanner deployment-timeout="360" path="deployments" relative-to="jboss.server.base.dir" scan-interval="5000" runtime-failure-causes-rollback="${jboss.deployment.scanner.rollback.on.failure:false}"/>
       </subsystem>

4. 次のJAASセキュリティドメインを`<subsystem xmlns="urn:jboss:domain:security:2.0">`要素で定義されたセキュリティサブシステムの`<security-domains>`に追加します 。

       <security-domain name="PortalRealm">
       <authentication>
       <login-module code="com.liferay.portal.kernel.security.jaas.PortalLoginModule" flag="required" />
       </authentication>
       </security-domain>

5. 以下のWeld関連のタグを削除します。

   - `<extension module="org.jboss.as.weld"/>`
   - `<subsystem xmlns="urn:jboss:domain:weld:4.0"/>`

6. ウェルカムコンテンツを提供する2つのコードスニペットを削除します。

       <location name="/" handler="welcome-content"/>

   そして

       <handlers>
       <file name="welcome-content" path="${jboss.home.dir}/welcome-content"/>
       </handlers>

7. ``<jsp-config/>タグを検索し、そのタグ内に`development`、`source-vm`、および`target-vm`の属性を設定します。終了すると、タグは次のようになります。

       <jsp-config development="true" source-vm="1.8" target-vm="1.8" />

**チェックポイント:**

続行する前に、`standalone.xml`ファイルに以下のプロパティが設定されていることを確認してください。

1. 新しい`<system-property>`が追加されました。

2. 新しい`<filter-spec>`が追加されました。

3. `<deployment-timeout>`は`360`に設定されています。

4. 新しい`<security-domain>`が作成されました。

5. Weldタグは削除されました。

6. ウェルカムコンテンツも削除されました。

7. `<jsp-config>`タグには、新しい属性が含まれています。

次に、JVMと起動スクリプトを設定する必要があります。

`$WILDFLY_HOME/bin/`フォルダ内にある、スタンドアロンドメインの設定スクリプトファイルの`standalone.conf`を変更する必要があります。（Windowsの場合は`standalone.conf.bat`）

- ファイルエンコーディングを設定します

- ユーザーのタイムゾーンを設定します

- 優先プロトコルスタックを設定します

- デフォルトの利用可能なメモリ容量を増やします。

使用しているオペレーティングシステムに応じて、以下の編集を行います。

**Windows:**

1. 最初の`JAVA_OPTS`の割り当てを以下のようにコメントアウトします。

       rem set "JAVA_OPTS=-Xms64M -Xmx512M -XX:MetaspaceSize=96M -XX:MaxMetaspaceSize=256m"

2. ファイルの末尾にある`JAVA_OPTS_SET`の行の1行上に、以下の`JAVA_OPTS`の割り当てを追加します。

       set "JAVA_OPTS=%JAVA_OPTS% -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Djboss.as.management.blocking.timeout=480 -Duser.timezone=GMT -Xmx2048m -XX:MaxMetaspaceSize=512m -XX:MetaspaceSize=200m"

**Unix:**

1. `if [ "x$JAVA_OPTS" = "x" ];`の文の下にある、以下の`JAVA_OPTS`の文を削除します。

       JAVA_OPTS="-Xms64m -Xmx512m -XX:MetaspaceSize=96M -XX:MaxMetaspaceSize=256m -Djava.net.preferIPv4Stack=true"

   そして、以下の文を挿入します。

       JAVA_OPTS="-Djava.net.preferIPv4Stack=true"

2. ファイルの末尾に以下の文を追加します。

       JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true  -Djboss.as.management.blocking.timeout=480 -Duser.timezone=GMT -Xmx2048m -XX:MaxMetaspaceSize=512m -XX:MetaspaceSize=200m"

| **重要:**：@product@が正常に動作するには、JVMは、GMTタイムゾーンとUTF-8を使用する必要があります。

**注：**WildflyサーバーでIBM JDKを使用する予定の場合は、いくつか追加のステップを実行する必要があります。まず、`$WILDFLY_HOME/modules/com/liferay/portal/main/module.xml`ファイルに移動して 、`<dependencies>`要素内に以下の依存関係を挿入します。<module name="ibm.jdk" />次に、`$WILDFLY_HOME/modules/system/layers/base/sun/jdk/main/module.xml`ファイルに移動して 、`<paths>...</paths>`要素内に以下のパス名を挿入します。<path name="com/sun/crypto" />|<path name="com/sun/crypto/provider" />|<path name="com/sun/org/apache/xml/internal/resolver" />|<path name="com/sun/org/apache/xml/internal/resolver/tools" />追加されたパスは、デプロイの例外およびイメージのアップロードの問題に関した事象を解決します。

**チェックポイント:**

これで、アプリケーションサーバーのJVM設定は完了しました。

1. ファイルのエンコーディング、ユーザーのタイムゾーン、優先プロトコルスタックは`standalone.conf.bat`ファイルの`JAVA_OPTS`に設定されています。

2. デフォルトの利用可能なメモリ容量が増えました。

Wildflyに@product@をインストールするための規定されたスクリプトの修正は、完了しました。次に、データベースを設定します。

### データベースの設定

データベース設定を処理する上で一番簡単な方法は @product@にデータソースを管理させることです。  [基本設定](/docs/7-1/deploy/-/knowledge_base/d/installing-liferay#using-the-setup-wizard)ページでは、@product@の組み込みデータソースを設定できます。組み込みデータソースを使用する場合は、このセクションは必要ではありません。

以下の例ではMySQLを使用しています。別のデータベースを使用している場合は、必要に応じてデータソースとドライバのスニペットを変更します。

Wildflyでデータソースを管理する場合は、次の手順で行います:

1. `$WILDFLY_HOME/standalone/configuration/standalone.xml`ファイルの `<datasources>`要素内にデータソースを追加します 。

       <datasource jndi-name="java:jboss/datasources/ExampleDS" pool-name="ExampleDS" enabled="true" jta="true" use-java-context="true" use-ccm="true">
       <connection-url>jdbc:mysql://localhost/lportal</connection-url>
       <driver>mysql</driver>
       <security>
       <user-name>root</user-name>
       <password>root</password>
       </security>
       </datasource>

   必ず、データベース名（つまり`lportal`）、ユーザー名、およびパスワードを適切な値に置き換えてください。

   **注：**データソースの`jndi-name`を別のものに変更する必要がある場合は、`<default-bindings>`タグの`datasource`要素も編集する必要があります。

2. ` standalone.xml `ファイルの` <drivers>`要素にもドライバを追加します。この要素は`<datasources>`要素内にもあります。

       <drivers>
       <driver name="mysql" module="com.liferay.portal">
       <driver-class>com.mysql.jdbc.Driver</driver-class>
       </driver>
       </drivers>

   最終的なデータソースのサブシステムは次のようになります。

       <subsystem xmlns="urn:jboss:domain:datasources:1.0">
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
       <driver name="mysql" module="com.liferay.portal">
       <driver-class>com.mysql.jdbc.Driver</driver-class>
       </driver>
       </drivers>
       </datasources>
       </subsystem>

3. Liferay Home内の`portal-ext.properties`ファイルで、データソースを指定します。

       jdbc.default.jndi.name=java:jboss/datasources/ExampleDS

データソースが設定できたので、次にメールセッションを設定します。

### メール設定

データベースと同様に、メール設定の処理も@product@に行わせるのが一番簡単な方法です。@product@の組み込みメールセッションを使用したい場合は、このセクションを飛ばしてコントロールパネルで[メールセッションを設定](/docs/7-1/deploy/-/knowledge_base/d/installing-liferay#configuring-mail)してください。

Wildflyでメールセッションを管理したい場合は、次の手順に従ってください。

1. `$WILDFLY_HOME/standalone/configuration/standalone.xml` ファイル内のメールサブシステムを以下のように指定します。

       <subsystem xmlns="urn:jboss:domain:mail:3.0">
       <mail-session jndi-name="java:jboss/mail/MailSession" name="mail-smtp">
       <smtp-server ssl="true" outbound-socket-binding-ref="mail-smtp" username="USERNAME" password="PASSWORD"/>
       </mail-session>
       </subsystem>
       ...
       <socket-binding-group name="standard-sockets" default-interface="public" port-offset="${jboss.socket.binding.port-offset:0}">
       ...
       <outbound-socket-binding name="mail-smtp">
       <remote-destination host="smtp.gmail.com" port="465"/>
       </outbound-socket-binding>
       </socket-binding-group>

2. Liferay Home内の`portal-ext.properties`ファイルで、メールセッションを参照します。

       mail.session.jndi.name=java:jboss/mail/MailSession

 次に、@product@をWildflyアプリサーバーにデプロイします。

## @product@をデプロイする <x1/><x2/>

これで、@product@ WARファイルを使用して@product@をデプロイする準備が整いました。

1. Wildflyインストールに`$WILDFLY_HOME/standalone/deployments/ROOT.war`フォルダがすでに存在する場合は、そのサブフォルダとファイルをすべて削除してください。
そうでない場合は、`$WILDFLY_HOME/standalone/deployments/ROOT.war`という名称の新しいフォルダを作成します 。

2. `ROOT.war`フォルダに@product@`.war`ファイルを解凍します。

3. `ROOT.war`のデプロイを実行するために、`$WILDFLY_HOME/standalone/deployments/`フォルダ内に`ROOT.war.dodeploy`という名前の空のファイルを作成します。
立ち上げ時に、Wildflyはこのファイルの存在を検出し、それをWebアプリケーションとしてデプロイします。

4. `$WILDFLY_HOME/bin`に移動し、`standalone.bat`または`standalone.sh`を実行して、Wildflyアプリケーションサーバーを起動します。

これで、Wildflyに@product@をデプロイできました。

@product@をデプロイした後、`PhaseOptimizer`を含む以下のような過剰な警告とログメッセージが表示される場合があります。これらは良性なので無視することができます。このようなログメッセージを回避するために、必ずアプリケーションサーバーのログレベルまたはログフィルターを調整してください。
|
|     May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
|     WARNING: Skipping pass gatherExternProperties
|     May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
|     WARNING: Skipping pass checkControlFlow
|     May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
|     INFO: pass supports: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, modules, exponent operator (**), async function, trailing comma in param list]
|     current AST contains: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, exponent operator (**), async function, trailing comma in param list, object literals with spread, object pattern rest]
