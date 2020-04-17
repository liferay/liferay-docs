---
header-id: upgrading-a-spring-mvc-portlet
---

# Spring MVCポートレットのアップグレード

[TOC levels=1-4]

[SpringポートレットMVCフレームワーク](https://docs.spring.io/spring/docs/current/spring-framework-reference/html/portlet.html)は、ポートレットへの依存関係の導入と、モデルビューコントローラーパターンの実装を容易にします。 Liferay Portal 6.xのポートレットでこのフレームワークを使用する場合は、@product-ver@へアップグレードできます。

このチュートリアルでは、My Spring MVC（プロジェクト`my-spring-mvc-portlet`）と呼ばれる、Spring MVCポートレットのアップグレードについて説明します。 これはプラグインSDKの`spring_mvc`テンプレートから作成された、必要最小限のポートレットです。

![図1：My Spring MVCポートレットには、名前と@product@の情報が表示されます。](../../../../images/upgraded-spring-mvc-portlet.png)

詳細は[オリジナルのソースコード](https://portal.liferay.dev/documents/113763090/114000186/my-spring-mvc-portlet-pre-7-0-upgrade.zip)および[アップグレードされたソースコード](https://portal.liferay.dev/documents/113763090/114000653/my-spring-mvc-portlet-post-7-1-upgrade.zip)をダウンロードして参照してください。

以下の図は、`my-spring-mvc-portlet`プロジェクトを示しています。

![図2：<code>my-spring-mvc-portlet</code>プロジェクトには、従来のLiferayプラグインファイル、Spring Portlet MVCアプリケーションコンテキスト（<code>spring-context /</code>）、およびコントローラークラス<code>MySpringMVCPortletviewController</code>があります。 ](../../../../images/upgrading-spring-mvc-portlets-folder-structure.png)

これらのファイルには、Spring関連のコンテンツがあります。

  - `view.jsp` → ポートレットの名前と@product@のリリース情報を表示します。
  - `my-spring-mvc-portlet.xml` → @product@はこのコンテキストファイルをポートレットに使用します。
  - `portlet-applications-context.xml` → Springの`SpringContextLoaderListener`クラスはこのコンテキストファイルを使用します。
  - `MySpringMVCPortletviewController` → マップ`VIEW`が`view.jsp`にリクエストし、@product@リリース情報をモデル属性に割り当てます。
  - `portlet.xml` → コンテキスト設定ファイル`my-spring-mvc-portlet.xml`を参照し、登録済みポートレット要求ハンドラーのディスパッチャーを指定します。
  - `web.xml` → コンテキスト設定ファイル`portlet-application-context.xml`を参照し、`ViewRendererServlet` を指定して、ポートレットの要求と応答およびHTTPサーブレットの要求と応答を変換します。

Spring MVCポートレットのアップグレード手順は次のとおりです。

1.  [@product-ver@のAPIにコードを適合させる](#adapt-the-code-to-liferays-api)

2.  [依存関係を解決する](#resolve-dependencies)

## liferayのAPIにコードを適合させる

[Liferay Upgrade Planner](/docs/7-1/tutorials/-/knowledge_base/t/liferay-upgrade-planner)は、コードのアップデートとコンパイルの事象の迅速な解決を促進します。

Upgrade Plannerは、プラグインの`liferay-plugin-package.properties`ファイルの`liferay-versions`プロパティの値をアップデートする必要があるかどうかを検出し、それを自動的に修正するオプションを提供します。 これは`my-spring-mvc-portlet`が必要とする、唯一のコード適応です。

## 依存関係を解決する

Liferay Portal 6.2では、`my-spring-mvc-portlet`は`liferay-plugin-package.properties`ファイルの`portal-dependency-jars`プロパティでそれらを指定することにより、ポータルのJARを活用しました。 プロパティは@product-ver@で廃止されているため、Gradle、MavenまたはApache Ant/Ivyなどの依存関係管理フレームワークを使用して、依存関係を取得する必要があります。

[サンプルポートレットプラグインを従来のプラグインからLiferay Workspace Webアプリケーション](/docs/7-1/tutorials/-/knowledge_base/t/migrating-traditional-plugins-to-workspace-web-applications)に変換することで、依存関係の解決が容易になりました。

アップデートされた`my-spring-mvc-portlet`の`build.gradle`ファイルは次のとおりです。

    dependencies {
        compileOnly group: 'aopalliance', name: 'aopalliance', version: '1.0'
        compileOnly group: 'commons-logging', name: 'commons-logging', version: '1.2'
        compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "2.0.0"
        compileOnly group: "javax.portlet", name: "portlet-api", version: "2.0"
        compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
        compile group: 'org.jboss.arquillian.junit', name: 'arquillian-junit-container', version: '1.1.3.Final'
        compile group: 'org.jboss.arquillian.container', name: 'arquillian-tomcat-remote-7', version: '1.0.0.CR6'
        compile group: 'com.liferay', name: 'com.liferay.ant.arquillian', version: '1.0.0-SNAPSHOT'
        compile group: 'org.springframework', name: 'spring-aop', version: '4.1.9.RELEASE'
        compile group: 'org.springframework', name: 'spring-beans', version: '4.1.9.RELEASE'
        compile group: 'org.springframework', name: 'spring-context', version: '4.1.9.RELEASE'
        compile group: 'org.springframework', name: 'spring-core', version: '4.1.9.RELEASE'
        compile group: 'org.springframework', name: 'spring-expression', version: '4.1.9.RELEASE'
        compile group: 'org.springframework', name: 'spring-web', version: '4.1.9.RELEASE'
        compile group: 'org.springframework', name: 'spring-webmvc', version: '4.1.9.RELEASE'
        compile group: 'org.springframework', name: 'spring-webmvc-portlet', version: '4.1.9.RELEASE'
    }

`my-spring-mvc-portlet`の依存アーティファクトの一部には、新しい名前が付けられています。

| 古い名前                 | 新しい名前                   |
| -------------------- | ----------------------- |
| `spring-web-portlet` | `spring-webmvc-portlet` |
| `spring-web-servlet` | `spring-webmvc`         |

[Maven Central](https://search.maven.org/)は、アーティファクト依存関係情報を提供します。

| **注**: 使用しているSpring Frameworkバージョンが、@product@が使用するバージョン | と異なる場合は、Spring Framework JARに | @product@のSpring Framework JARとは異なる名前を付ける必要があります。 JARの名前を変更しない場合は、@product@ | はSpring Framework JARを使用していると想定し、それらを | 生成されたWAB（Webアプリケーションバンドル）から除外します。 | [ポータルプロパティ `module.framework.web.generator.excluded.paths`](https://docs.liferay.com/ce/portal/7.1-latest/propertiesdoc/portal.properties.html#Module%20Framework) | は@product@のSpring Framework JARをリストします。 | [Understanding Excluded JARs](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies#understanding-excluded-jars) | では、@product@が使用するSpring Frameworkバージョンを検出する方法を説明しています。

| **注**: 依存関係がOSGiモジュールJARで、@product@がプラグインに必要なパッケージをすでにエクスポート | している場合、プラグインのWARファイルからJARを*除外*します。 | これにより、プラグインがLiferayが | すでにエクスポートしているパッケージと同じパッケージをエクスポートできなくなります。 またクラスローダーの衝突も防止されます。 デプロイから | JARを除外するには、その名前をプロジェクトの | `liferay-plugin-package.properties`ファイルの`deploy-excludes`プロパティに追加します。 | | deploy-excludes= | \*\*/WEB-INF/lib/module-a.jar, | \*\*/WEB-INF/lib/module-b.jar | | `my-spring-mvc-portlet`の依存関係はOSGiモジュールではないため、JARは | 除外する必要がありません。

ポートレットの記述子ファイルによって参照されるクラスパッケージをインポートするには、`liferay-plugin-package.properties`ファイルの`Import-Package`ヘッダーにパッケージを追加します。 詳細については、[Deploying a Spring MVC Portlet](/docs/7-1/tutorials/-/knowledge_base/t/deploying-a-spring-mvc-portlet)を参照してください。

`java.*`パッケージ以外でJavaの`rt.jar`からのパッケージに依存している場合、[ポータルプロパティ`org.osgi.framework.bootdelegation`](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Module%20Framework)をオーバーライドし、プロパティのリストにそれを追加します。 詳細については[こちら](/docs/7-1/tutorials/-/knowledge_base/t/resolving-classnotfoundexception-and-noclassdeffounderror-in-osgi-bundles#case-4-the-missing-class-belongs-to-a-java-runtime-package)をご覧ください。

| **注**: 組み込みJARにプロパティファイルが含まれるSpring MVCポートレット | （例: `spring.handlers`、 `spring.schemas`、 `spring.tooling`）は、 | 事象ごと[LPS-75212](https://issues.liferay.com/browse/LPS-75212)に影響を受ける可能性があります。 | プロパティファイルを持つ最後のJARは、プロパティが結果として生じるWABのクラスパスに | 追加されている唯一のJARです。 他のJARのプロパティは追加されません。 | | [Depploying a Spring MVC Portlet](/docs/7-1/tutorials/-/knowledge_base/t/deploying-a-spring-mvc-portlet) | では、すべての組み込みJARプロパティを追加する方法を説明しています。

ポートレットをデプロイする準備ができました。 通常通りデプロイします。

@product@の[WAB ジェネレーター](/docs/7-1/tutorials/-/knowledge_base/t/using-the-wab-generator)は、ポートレットWARをWebアプリケーションバンドル（WAB）に変換し、LiferayのOSGiランタイムフレームワークにWABをインストールします。

    2018-04-12 19:28:36.810 INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:262] Processing my-spring-mvc-portlet.war
    2018-04-12 19:28:42.182 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][BaseAutoDeployListener:43] Copying portlets for C:\portals\liferay-ce-portal-7.1-m1\tomcat-8.0.32\temp\20180412192842100ZSINUETA\my-spring-mvc-portlet.war
    2018-04-12 19:28:42.706 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][BaseDeployer:876] Deploying my-spring-mvc-portlet.war
    2018-04-12 19:28:47.708 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][BaseAutoDeployListener:50] Portlets for C:\portals\liferay-ce-portal-7.1-m1\tomcat-8.0.32\temp\20180412192842100ZSINUETA\my-spring-mvc-portlet.war copied successfully
    2018-04-12 19:28:56.600 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][HotDeployImpl:226] Deploying my-spring-mvc-portlet from queue
    2018-04-12 19:28:56.601 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][PluginPackageUtil:1003] Reading plugin package for my-spring-mvc-portlet
    2018-04-12 19:28:56.700 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][PortletHotDeployListener:186] Registering portlets for my-spring-mvc-portlet
    2018-04-12 19:28:56.955 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][PortletHotDeployListener:298] 1 portlet for my-spring-mvc-portlet is available for use
    2018-04-12 19:28:57.114 INFO  [fileinstall-C:/portals/liferay-ce-portal-7.1-m1/osgi/war][BundleStartStopLogger:35] STARTED my-spring-mvc-portlet_7.1.0.1 [658]

これでSpring MVCポートレットを@product-ver@にアップグレードできました。 　

## 関連トピック

[Spring MVC](/docs/7-1/tutorials/-/knowledge_base/t/spring-mvc)

[プラグインSDKプロジェクトをWorkspaceとGradleに移行する](/docs/7-1/tutorials/-/knowledge_base/t/migrating-traditional-plugins-to-workspace-web-applications)

[依存関係管理ツールの使用](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies#using-a-dependency-management-tool)

[WABジェネレーターの使用](/docs/7-1/tutorials/-/knowledge_base/t/using-the-wab-generator)
