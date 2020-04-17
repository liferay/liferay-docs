---
header-id: converting-your-applications-portlet-classes-and-ui
---

# アプリケーションのポートレットクラスとUIの変換

[TOC levels=1-4]

まず最初に行うことは、アプリケーションのルートフォルダとその*web*クライアントモジュールのフォルダ構造を作成することです。 このモジュールは、ポートレットクラスとWeb UIを保持します。 モジュールのスケルトン構造の作成を開始する前に、このバージョンのアプリケーションを構成するモジュールを決定します。 アプリケーションがサービスAPIと実装クラスを提供する場合（すべてのLiferay Service Builderアプリケーションの場合）、それらに個別のモジュールを作成します。 このチュートリアルでは、Mavenプロジェクトモデルを想定していますが、ビルドツールまたはフォルダ構造は許可されます。

| **注:** 最新のOSGi | 機能をサポートするビルドプラグインバージョンを使用する必要があります。 次のGradleまたはMavenビルドプラグインバージョンは、 | それぞれのビルドフレームワークで使用する必要があります。: | | **Gradle** | | - biz.aQute.bnd:biz.aQute.bnd.gradle:3.2.0 **または** | - org.dm.gradle:gradle-bundle-plugin:0.9.0 | | **Maven** | | - biz.aQute.bnd:bnd-maven-plugin:3.2.0

フォルダ構造を作成する手順は次のとおりです。

1.  ルートフォルダを作成します。 これは、アプリケーションの独立したモジュールと設定ファイルの新しいホームです。 たとえば、アプリケーションの名前が*Tasks*である場合、ルートフォルダは*tasks*になります。

    アプリケーションがLiferay Service Builderを使用する場合、次の[Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli)コマンドを使用して、親フォルダとサービス実装およびサービスAPIモジュールをそこで生成します。 親フォルダが既に存在する場合、そこは空でなければなりません。 このコマンドは、親フォルダに`APPLICATION_NAME`と名前を付けます。
   
        blade create -t service-builder -p [ROOT_PACKAGE] [APPLICATION_NAME]

    The `*-service`および`*-api`モジュールフォルダについては、このチュートリアルの後半で説明します。

2.  Webクライアントモジュールのフォルダ構造を作成します。 [Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli)および[Maven](/docs/7-1/reference/-/knowledge_base/r/maven)は、[プロジェクトテンプレート](/docs/7-1/reference/-/knowledge_base/r/project-templates)に基づいてプロジェクトフォルダ構造を生成します。

    例として、ルートフォルダ（例: `tasks`）に移動し、次のBlade CLIコマンドを実行して、包括的なWebクライアントモジュール構造を生成します。
   
        blade create -t mvc-portlet [APPLICATION_NAME]-web

3.  `*-web`モジュールで、`/src/main/java/[APPLICATION_NAME]`フォルダをルートJavaパッケージに置き換えます。 たとえば、アプリケーションのルートパッケージ名が`com.liferay.tasks.web`である場合、クラスのフォルダは`/src/main/java/com/liferay/tasks/web`である必要があります。 さらに、`init.jsp`および`view.jsp`ファイルを、`src/main/resources/META-INF/resources`フォルダから削除します。 これらの生成されたデフォルトJSPの代わりに、既存のアプリケーションのJSPを使用します。

4.  `*-web`モジュールフォルダが（以下の`[APPLICATION_NAME]`でマークされた）アプリケーションのルートフォルダにあり、`*-web`モジュールのフォルダ構造が次のようになっていることを確認します。

      - `[APPLICATION_NAME]`
          - `[APPLICATION_NAME]-web`
              - `src`
                  - `main`
                      - `java`
                          - `[ROOT_PACKAGE]`
                      - `resources`
                          - `content`
                              - `Language.properties`
                          - `META-INF`
                              - `resources`
              - `bnd.bnd`
              - `build.gradle`

    残りの手順は、Webクライアントモジュール（`*-web`モジュール）に影響します。

5.  `bnd.bnd`ファイルはプロジェクトを構築する時に、モジュールの `MANIFEST.MF`ファイルを生成するために使用されます。 それを開き、アプリケーションに合わせて変更します。 お使いのモジュール`bnd.bnd`の設定方法について、[追加のドキュメンテーション](http://bnd.bndtools.org/)もあります。 例として、Liferay`dictionary-web`モジュールの`bnd.bnd`は次のとおりです。
   
       Bundle-Name: Liferay Dictionary Web
       Bundle-SymbolicName: com.liferay.dictionary.web
       Bundle-Version: 1.0.6

    より詳しい例については、`journal-web`モジュールの`bnd.bnd`を調べてください。
   
       Bundle-Name: Liferay Journal Web
       Bundle-SymbolicName: com.liferay.journal.web
       Bundle-Version: 2.0.0
       Export-Package:\
           com.liferay.journal.web.asset,\
           com.liferay.journal.web.dynamic.data.mapping.util,\
           com.liferay.journal.web.social,\
           com.liferay.journal.web.util
       Liferay-JS-Config: /META-INF/resources/js/config.js
       Liferay-Releng-Module-Group-Description:
       Liferay-Releng-Module-Group-Title: Web Content
       Web-ContextPath: /journal-web

6.  `build.gradle`ファイルを開きます。 ここで[モジュールのすべての依存関係を指定](/docs/7-0/tutorials/-/knowledge_base/t/configuring-dependencies)します。 生成されたものには、コンテンツとデフォルトの依存関係が事前に入力されています。 `dependencies {...}`ブロックにモジュールの依存関係を追加します。

    [Finding Liferay API Modules](/docs/7-0/reference/-/knowledge_base/r/finding-liferay-api-modules)では、一般的なLiferay APIモジュールのシンボル名をリストしています。 [@product-ver@](@platform-ref@/7.1-latest/javadocs/)および[Liferayアプリケーション](@app-ref@/7.1-latest/javadocs)向けのJavadoc概要では、各モジュールのシンボル名とバージョンをリストしています。 [Configuring Dependencies tutorial](/docs/7-0/tutorials/-/knowledge_base/t/configuring-dependencies)では、アーティファクト情報の検索と依存関係の指定について説明しています。 [@product@は、多くのJavaパッケージと全体のアーティファクト](/docs/7-0/reference/-/knowledge_base/r/third-party-packages-portal-exports)をOSGiコンテナのランタイムで提供します。 すべての依存関係が解決すると、インストール後にモジュールがアクティブになります。 ログには未解決の依存関係が表示されます。 メッセージの例は次のとおりです。
   
       ! could not resolve the bundles: ... Unresolved requirement: Import-Package: ... Unresolved requirement: Require-Capability ...

7.  従来のアプリケーションのJSPファイルを`/src/main/resources/META-INF/resources`フォルダの中にコピーします。 ほとんどの場合、アプリケーションのすべてのJSPファイルはWebクライアントモジュールに属します。

8.  [Service Builder](/docs/7-0/tutorials/-/knowledge_base/t/running-service-builder-and-understanding-the-generated-code)に関連しないポートレットクラスとサポートクラスを、Webクライアントモジュールのそれぞれのパッケージフォルダにコピーします。 クラスをサブパッケージに整理すると管理しやすくなります。

    例として、`journal-web`モジュールのJavaソースフォルダ構造は次のとおりです。

      - `journal-web`
          - ...
          - `src/main/java/com/liferay/journal/web/`
              - `asset`
                  - \[classes\]
              - `configuration`
                  - \[classes\]
              - `dynamic/data/mapping/util`
                  - \[classes\]
              - `internal`
                  - `application/list`
                      - \[classes\]
                  - `custom/attributes`
                      - \[classes\]
                  - `dao/search`
                      - \[classes\]
                  - ...
              - `social`
                  - \[classes\]
              - `util`
                  - \[classes\]
          - ...

    | **注: **多くのアプリケーションには、APIおよび実装クラスがあります。 これらの | クラスはAPIおよび実装モジュールに属します。 次のチュートリアル | では、これらのクラスをモジュールにコピーする方法を説明します。

9.  必要なクラスがクライアントモジュールにあるので、OSGiに準拠させる必要があります。 初心者の場合は@product@が使用するため、[宣言型サービス](https://osgi.org/specification/osgi.cmpn/7.0.0/service.component.html)コンポーネントフレームワークを使用することをお勧めします。 このチュートリアルでは、宣言型サービスの使用を前提としています。 ただし、他のOSGiコンポーネントフレームワークを使用することは可能です。

    従来のアプリケーションのXMLファイルを確認し、設定およびメタデータ情報をコンポーネントプロパティとしてポートレットクラスに移行します。 これを行うには、`@Component`注釈をポートレットクラスに追加し、その注釈に必要なプロパティを追加します。 [コンポーネントプロパティへのポートレット記述子のマッピング](/docs/7-0/reference/-/knowledge_base/r/portlet-descriptor-to-osgi-service-property-map)を調べます。 最終結果は、次の例のようになります。
   
       @Component(
           immediate = true,
           property = {
               "com.liferay.portlet.display-category=category.sample",
               "com.liferay.portlet.icon=/icon.png",
               "javax.portlet.name=1",
               "javax.portlet.display-name=Tasks Portlet",
               "javax.portlet.security-role-ref=administrator,guest,power-user",
               "javax.portlet.init-param.clear-request-parameters=true",
               "javax.portlet.init-param.view-template=/view.jsp",
               "javax.portlet.expiration-cache=0",
               "javax.portlet.supports.mime-type=text/html",
               "javax.portlet.resource-bundle=content.Language",
               "javax.portlet.info.title=Tasks Portlet",
               "javax.portlet.info.short-title=Tasks",
               "javax.portlet.info.keywords=Tasks",
           },
           service = Portlet.class
       )
       public class TasksPortlet extends MVCPortlet {
           ...
       }

10. `portletId`（例: `58_INSTANCE_4gtH`）のすべての参照をポートレットのクラス名に変換し、すべての期間をアンダースコア（例: `com_liferay_web_proxy_portlet_WebProxyPortlet`）に置き換えます。

11. 従来のアプリケーションのリソース操作（ある場合）をクライアントモジュールに移行します。 `/src/main/resources/resource-actions/default.xml`ファイルを作成し、そこへリソース操作をコピーします。 `src/portlet.properties`ファイルを作成し、次のプロパティを追加してください。
    
        resource.actions.configs=resource-actions/default.xml

    例として、ディレクトリアプリケーションの[`default.xml`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/directory/directory-web/src/main/resources/resource-actions/default.xml)を参照してください。

    [7.1では権限APIが変更 ](/docs/7-1/tutorials/-/knowledge_base/t/defining-application-permissions)されていることに注意してください。それに応じて権限ヘルパーを調整します。

12. `src/main/resources/content/Language.properties`ファイルに言語キーを追加します。 アプリケーションに固有の言語キーのみを含めます。 @product@の言語キーは、すべてのポートレットアプリケーションで使用できます。

アプリケーションのWebクライアントモジュールを作成し、ポートレットクラスとUIをモジュール化するための最も一般的なタスクのいくつかを完了しました。 このチュートリアルでは取り上げていない、アプリケーションの他の部分の変換に関しては、[@product@ developer tutorials](/docs/7-1/tutorials/-/knowledge_base/t/introduction-to-liferay-development)を参照し、それらの部分がどのようにアプリケーションモジュールに適合するかを確認してください。 チュートリアルは関心ごとの分野に分かれているため、必要なトピックの情報を簡単に見つけることができます。

最後に以下の表は、従来のポートレットプラグインから`tasks-portlet`という架空のアプリケーション用のモジュールに、ファイルとJavaパッケージをマップする、クイックリファレンスガイドです。

| 従来のプラグイン                                                      | モジュール                                                            |
| ------------------------------------------------------------- | ---------------------------------------------------------------- |
| `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.asset`   | `tasks-web/src/main/java/com.liferay.tasks.asset`                |
| `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.portlet` | `tasks-web/src/main/java/com.liferay.tasks.portlet`              |
| `tasks-portlet/docroot/WEB-INF/src/content`                   | `tasks-web/src/main/resources/content`                           |
| `tasks-portlet/docroot/WEB-INF/src/resource-actions`          | `tasks-web/src/main/resources/resource-actions`                  |
| `tasks-portlet/docroot/WEB-INF/src/portlet.properties`        | `tasks-web/src/main/resources/portlet.properties`                |
| `tasks-portlet/docroot/init.jsp`                              | `tasks-web/src/main/resources/META-INF/resources/init.jsp`       |
| `tasks-portlet/docroot/tasks`                                 | `tasks-web/src/main/resources/META-INF/resources/tasks`          |
| `tasks-portlet/docroot/upcoming_tasks`                        | `tasks-web/src/main/resources/META-INF/resources/upcoming_tasks` |

多くのアプリケーションにはWebクライアントモジュールしかありません。 Liferay Service Builderアプリケーションなどの、より大規模で複雑なアプリケーションでは、サービスAPIとサービス実装ロジックを保持するために追加のモジュールが必要です。 次に、これらのモジュールを作成する方法を学習します。
