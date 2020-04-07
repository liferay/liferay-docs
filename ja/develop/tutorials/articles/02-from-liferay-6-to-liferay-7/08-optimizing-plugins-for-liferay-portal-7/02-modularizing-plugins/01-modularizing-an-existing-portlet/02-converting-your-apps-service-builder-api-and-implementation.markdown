---
header-id: converting-your-applications-service-builder-api-and-implementation
---

# アプリケーションのService Builde APIと実装を変換する

[TOC levels=1-4]

このチュートリアルでは、Liferay Portal 6のService Buildeアプリケーションを、@product-ver@スタイルのアプリケーションに変換する方法を学びます。 [前回のチュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/converting-your-applications-portlet-classes-and-ui)では、実装とAPIモジュールを生成する方法を学びました。 前回のチュートリアルのステップ2で概説した、`service-builder`Blade CLIコマンドをまだ実行していない場合は、今すぐ実行します。 APIモジュールは、アプリケーションのService Builderで生成されたAPIを保持し、実装モジュールは、アプリケーションのService Builderの実装を保持します。

APIおよび実装モジュールの編集を開始する前に、ルートプロジェクト（例: `tasks`）を設定して、そこにある複数のモジュールを認識する必要があります。 マルチモジュールGradleプロジェクトでは、構築目的向けにルートプロジェクトで`settings.gradle`ファイルが必要です。 Blade CLIを使用してService Builderプロジェクトのモジュールを生成すると、 `settings.gradle`ファイルが挿入され、`api`および`service`モジュール用に事前設定されました。 `Web`モジュールをService Builderプロジェクトの生成親フォルダに追加し、 `settings.gradle`ファイルでも定義する必要があります。 Gradle設定を使用して`Web`モジュールを後で設定しますが、現時点では、モジュールを `service-builder`テンプレートによって生成されたプロジェクトにコピーします。 `tasks`プロジェクトのルートフォルダの例は、次のようになります。

  - `tasks`
      - `gradle`
      - `tasks-api`
      - `tasks-service`
      - `tasks-web`
      - `build.gradle`
      - `gradlew`
      - `settings.gradle`

これでルートプロジェクトフォルダは正常な状態になります。 次に、Service Builderを使用して、アプリケーションのサービスAPIとサービス実装コードを生成します。

1.  従来のアプリケーションの`service.xml`ファイルを、実装モジュールのルートフォルダにコピーします（例: `tasks/tasks-service`）。

2.  Blade CLIは、サービス実装モジュール用に`bnd.bnd`ファイルを生成しました。 この`bnd.bnd`ファイルを編集して、アプリケーションに合わせます。 サービス実装モジュールのbndファイルの例については、以下の`export-import-service`モジュールのbndを参照してください。
   
        Bundle-Name: Liferay Export Import Service
        Bundle-SymbolicName: com.liferay.exportimport.service
        Bundle-Version: 4.0.0
        Export-Package:\
            com.liferay.exportimport.content.processor.base,\
            com.liferay.exportimport.controller,\
            com.liferay.exportimport.data.handler.base,\
            com.liferay.exportimport.lar,\
            com.liferay.exportimport.lifecycle,\
            com.liferay.exportimport.messaging,\
            com.liferay.exportimport.portlet.preferences.processor.base,\
            com.liferay.exportimport.portlet.preferences.processor.capability,\
            com.liferay.exportimport.search,\
            com.liferay.exportimport.staged.model.repository.base,\
            com.liferay.exportimport.staging,\
            com.liferay.exportimport.xstream
        Liferay-Releng-Module-Group-Description:
        Liferay-Releng-Module-Group-Title: Data Management
        Liferay-Require-SchemaVersion: 1.0.0
        -includeresource: content=../../staging/staging-lang/src/main/resources/content

3.  Blade CLIは、サービス実装モジュールの`build.gradle`ファイルも生成しました。 このファイルでは、Service Builderは、このモジュールとサービスAPIモジュールの両方でコードを生成するように既に設定されています。 Service Builderを実行すると、お使いの `*api`と`*service`モジュールで、[Javaクラス、インタフェース、および関連ファイルを生成](/docs/7-1/tutorials/-/knowledge_base/t/running-service-builder)します。 サービス実装モジュールの`build.gradle`ファイルを開き、デフォルトの設定を表示します。

    既に学んだように、生成されたビルドファイルのデフォルトを承認する必要はありません。 Blade CLIは、いくつかの標準OSGiおよびLiferay設定を単純に生成しました。

    たとえば、Service Builderはデフォルトですでに利用可能です。 プロジェクトに`service.xml`ファイルが含まれている場合、Blade CLIはService Builderプラグインを自動的に適用します。 Service Builderプラグインがすでに利用可能になっているので、プロジェクトでのプラグインの設定を気に掛ける必要はありません。

4.  サービス実装モジュールの`build.gradle`ファイルにおけるもう1つの重要な部分は、 `buildService{...}`ブロックです。 このブロックは、プロジェクトでService Builderを実行する方法を設定します。 現在の設定ではAPIモジュールが正常に生成されますが、特定のケースで追加の設定が必要になる場合があります。

5.  ルートプロジェクトフォルダに移動します。 次に、`gradlew buildService`を実行します。

    サービスAPI、実装クラス、および設定（SQL、Hibernate、Springなど）は、それぞれのモジュールの`service.xml`ファイルから生成されます。 [Service Builder Gradleプラグイン](/docs/7-1/reference/-/knowledge_base/r/service-builder-gradle-plugin)には複数のオプションがあります。

6.  Service Builderを実行したので、ビジネスロジッククラスを実装モジュールにコピーします。 以下の表は、よく使用されているLiferay Portal 6のクラスとパッケージ、およびアプリケーション内での配置場所を示しています。 またこの表は、クラスと設定ファイルを整理する方法を示しています。ただし、アプリケーションにとって最も意味をなす組織的な方法論に従うということを覚えておいてください。 1つのサイズは、モジュールのフォルダスキームにすべて適合しません。

    | プラグインパッケージ                                                                     | モジュールパッケージ                                                               |
    | ------------------------------------------------------------------------------ | ------------------------------------------------------------------------ |
    | `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.model.impl`               | `tasks-service/src/main/java/com.liferay.tasks.model.impl`               |
    | `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.service.impl`             | `tasks-service/src/main/java/com.liferay.tasks.service.impl`             |
    | `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.service.permission`       | `tasks-service/src/main/java/com.liferay.tasks.service.permission`       |
    | `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.service.persistence.impl` | `tasks-service/src/main/java/com.liferay.tasks.service.persistence.impl` |
    | `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.social`                   | `tasks-service/src/main/java/com.liferay.tasks.social`                   |
    | `tasks-portlet/docroot/WEB-INF/src/com.liferay.tasks.util`                     | `tasks-service/src/main/java/com.liferay.tasks.util`                     |
    | `tasks-portlet/docroot/WEB-INF/src/custom-sql`                                 | `tasks-service/src/main/resources/META-INF/custom-sql`                   |


7.  ビジネスロジックをコピーしたら、`gradlew buildService`を再度実行して、残りのサービスを生成します。

サービスが生成されたので、LiferayのOSGiコンテナーにデプロイされたとき相互に参照できるように、モジュールを接続する必要があります。 Blade CLIはすでにこのタスクを部分的に完了しています。 たとえば、これはサービス実装モジュールがサービスAPIモジュールに依存すると想定しています。

クライアントモジュールを`api`および`service`モジュールに関連付ける必要があるのは、これらが個別に生成されたためです。 これを行うには、次の手順に従います。

1.  ルートプロジェクトの`settings.gradle`ファイルで、`web`モジュールと `api`および`service`モジュールを追加し、Gradleビルドライフサイクルに含まれるようにします。
   
        include "tasks-api", "tasks-service", "tasks-web"

2.  クライアントモジュールの依存関係として、`api`および`service`モジュールを追加します。
   
        dependencies {
            compileOnly  project(':tasks-api')
            compileOnly  project(':tasks-service')
        }

Service Builderを使用して、アプリケーションのサービスを正常に生成しました。 これらはモジュールに存在し、@product-ver@にデプロイできます。
