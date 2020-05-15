---
header-id: creating-remote-services
---

# リモートサービスの作成

[TOC levels=1-4]

デフォルトの@product@サービスの多くは、JSONおよびSOAP Webサービスとして公開されています。ポータルを8080番ポートでローカルに実行する場合、以下のURLにアクセスしてデフォルトのJSON Webサービスを参照します。

    http://localhost:8080/api/jsonws/

デフォルトのSOAP Webサービスを参照するには、以下のURLにアクセスします。

    http://localhost:8080/api/axis

これらのWebサービスのAPIは、非ポートレットクライアントや非Javaクライアントを含む、様々なタイプのクライアントからアクセスできます。Service Builderを使用して、プロジェクトのエンティティに対して同様のリモートサービスを作成できます。エンティティの`remote-service`属性を`true`に設定して、Service Builderを実行すると、SOAPおよびJSON Webサービスの両方をサポートするために必要なすべてのクラス、インターフェース、およびファイルがそのエンティティに対して作成されます。Service Builderは既存のサービスを呼び出すメソッドを作成しますが、リモートで公開されるメソッドを実装するかは開発者次第です。このチュートリアルでは、アプリケーションのリモートサービスを作成する方法を説明します。リモートサービスの作成が完了すると、アプリケーションのリモートサービスメソッドをJSONおよびSOAP Webサービスを介してリモートで呼び出すことができるようになります。

## Service Builderを使用したリモートサービスの作成

アプリケーションのローカルサービスメソッドは、`*LocalServiceImpl`に実装する必要があります。そして、アプリケーションのリモートサービスメソッドは`*ServiceImpl`に実装する必要があります。

| **ベストプラクティス：**アプリケーションでローカルサービスとリモートサービスの両方が必要な場合、| エンティティモデルで| アプリケーションでの作業に必要なサービスメソッドを決定します。これらのサービスメソッドは、`*LocalServiceImpl`に追加します。それから、| `*ServiceImpl`に対応するリモートサービスメソッドを作成します。リモートサービスメソッドに| 権限チェックを追加し、リモートサービスメソッドが| ローカルサービスメソッドを呼び出すようにします。リモートサービスメソッドは、| 呼び出し対象のローカルサービスメソッドと同じ名前を持つことができます。アプリケーション内では、リモート| サービスのみを呼び出します。これにより、サービスメソッドが保護され、| パーミッションコードを複製する必要がなくなります。

ここでは、Webコンテンツの記事を例として考えてみます。Webコンテンツの記事は`JournalArticle`エンティティによって表されます。このエンティティは、`true`に設定された`remote-service`属性を使用して、`journal-service`モジュールの[`service.xml`
ファイル](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/journal/journal-service/service.xml)で宣言されます。したがって、Service Builderはリモートサービスメソッドの実装を保持するために、[リモートサービスクラスの`JournalArticleServiceImpl`](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/journal/journal-service/src/main/java/com/liferay/journal/service/impl/JournalArticleServiceImpl.java)を作成します。このアプリをゼロから開発している場合、このクラスは最初は空の状態です。したがって、これを使用して、エンティティのリモートサービスメソッドを実装する必要があります。また、`JournalArticleServiceImpl`のリモートサービスメソッドの実装では、権限を確認し、対応するローカルサービスメソッドを呼び出すというベストプラクティスに従っていることに注意してください。たとえば、以下から分かるように、`JournalArticleServiceImpl`の各`addArticle`メソッドは権限をチェックし、ローカルサービスの一致する`addArticle`メソッドを呼び出します。

    @Override
    public JournalArticle addArticle(...)
    throws PortalException {
    
    ModelResourcePermissionHelper.check(
    _journalFolderModelResourcePermission, getPermissionChecker(),
    groupId, folderId, ActionKeys.ADD_ARTICLE);
    
    return journalArticleLocalService.addArticle(...);
    }

`ModelResourcePermissionHelper.check(...)`を使用していることに注意してください。このヘルパークラスは、Liferay 7.1で導入されました。モデルリソースの権限チェックでは、カスタムの権限ヘルパークラスを使用する代わりに、このヘルパークラスを使用できます。
また、ローカルサービスは`journalArticleLocalService`フィールドを介して呼び出されることにも注意してください。これは、`JournalArticleLocalServiceImpl`型のSpring Beanであり、Service Builderによって`JournalArticleServiceImpl`に注入されます。Service Builderで作成されたクラスも同じことを行います。

`*ServiceImpl`クラスへのリモートサービスメソッドの追加が完了したら、それを保存し、Service Builderを再度実行します。Service Builderを実行した後、プロジェクトをデプロイしてJSON WebサービスのURL（[http://localhost:8080/api/jsonws/](http://localhost:8080/api/jsonws/)）を確認し、アプリケーションのコンテキストパスを選択したときにリモートサービスが表示されることを確認します。

 これで、Service Builderを使用して、アプリケーションのリモートサービスが作成できました。ただし、これらのサービスをSOAP経由で利用可能にするには、アプリのWebサービスデプロイメント記述子（WSDD）を構築して、デプロイする必要があります。次のセクションでは、その方法を解説します。SOAP Webサービスを作成する必要がない場合は、チュートリアルの[Invoking Remote Services](/docs/7-1/tutorials/-/knowledge_base/t/invoking-remote-services)に進んでください。

## アプリケーションのWSDDを作成する

@product@はApache Axisを使用しているので、SOAP Webサービスが利用可能です。AxisではSOAPを介してアプリケーションのリモートサービスを利用できるようにするためにWSDDが必要なので、アプリケーションのWSDDを構築してデプロイする必要があります。WSDDを作成するには、LiferayのWSDD Builder Gradleプラグインをアプリのプロジェクトにインストールする必要があります。ただし、インストール方法は、所有しているプロジェクトの種類によって異なります。Liferay WorkspaceのService Builderプロジェクトのようなマルチモジュールプロジェクトの場合、ワークスペースの`settings.gradle`ファイルを介してプラグインをインストールします。これにより、WSDD BuilderプラグインがService Builderを使用するワークスペース内のすべてのモジュール（通常、 `*-api`および`*-service`モジュール）に適用されます。ただし、Service Builderを使用するスタンドアロンの`*-service`モジュールがある場合は、モジュールの`build.gradle`ファイルにWSDD Builderプラグインをインストールします。

次のセクションでは、マルチモジュールプロジェクトにWSDDビルダーをインストールする方法を説明します。スタンドアロンのモジュールプロジェクトがある場合は、*Installing the WSDD Builder Plugin in a Standalone Module Project*セクションに進んでください。

### マルチモジュールプロジェクトへのWSDD Builderプラグインのインストール

Liferay WorkspaceのService BuilderプロジェクトのようなマルチモジュールプロジェクトにWSDD Builderプラグインをインストールするには、ワークスペースの`settings.gradle`ファイルを以下の手順で変更します。

1. `ServiceBuilderPlugin`と`WSDDBuilderPlugin`のインポートをファイルの先頭に追加します。

       import com.liferay.gradle.plugins.service.builder.ServiceBuilderPlugin
       import com.liferay.gradle.plugins.wsdd.builder.WSDDBuilderPlugin
   
2. `repositories`ブロックでは、Mavenを介して、LiferayのCDNリポジトリを追加します。

       repositories {
       maven {
       url "https://repository-cdn.liferay.com/nexus/content/groups/public"
       }
       }
   
   このリポジトリは、WSDD Builderライブラリとその推移的な依存関係、およびその他のLiferayライブラリをホストします。Blade CLIまたはLiferay @ide@で`service-builder`テンプレートを使用してService Builderプロジェクトを作成した場合は、`settings.gradle`ファイルにはすでにこれが含まれていることに注意してください。

3. 以下のコードをファイルの最後に追加します。

       gradle.beforeProject {
       project ->
       
       project.plugins.withType(ServiceBuilderPlugin) {
       project.apply plugin: WSDDBuilderPlugin
       }
       }
   
   これは、Service Builderを使用するLiferay WorkspaceのすべてのモジュールでWSDD Builderプラグインを適用するコードです。対象の`settings.gradle`ファイルは、以下のようになります。

       import com.liferay.gradle.plugins.service.builder.ServiceBuilderPlugin
       import com.liferay.gradle.plugins.wsdd.builder.WSDDBuilderPlugin
       
       buildscript {
       dependencies {
       classpath group: "com.liferay", name: "com.liferay.gradle.plugins.workspace", version: "1.2.0"
       }
       
       repositories {
       maven {
       url "https://repository-cdn.liferay.com/nexus/content/groups/public"
       }
       }
       }
       
       apply plugin: "com.liferay.workspace"
       
       gradle.beforeProject {
       project ->
       
       project.plugins.withType(ServiceBuilderPlugin) {
       project.apply plugin: WSDDBuilderPlugin
       }
       }
   
4. Liferay WorkspaceのGradleプロジェクトを更新します。Liferay @ide@を使用している場合は、閉じて再起動します。

WSDD Builderプラグインがインストールできたので、WSDDを構築してデプロイする準備が整いました。この手順については、*Building and Deploying the WSDD*のセクションを参照してください。

### スタンドアロンモジュールプロジェクトへのWSDD Builderプラグインのインストール

Service Builderを使用するスタンドアロンの`*-service`モジュールにWSDD Builderプラグインをインストールするために、以下の手順でモジュールの`build.gradle`ファイルを変更します。

1. プラグインを依存関係として`buildscript`に追加します。

2. Mavenを介してLiferay CDNリポジトリを追加します。

3. プラグインをプロジェクトに適用します。

たとえば、スタンドアロンの`*-service`モジュールにあるサンプルの`build.gradle`ファイルの以下の部分にはWSDD Builderプラグインが含まれており、プロジェクトに適用されます。

    buildscript {
    dependencies {
    classpath group: "com.liferay", name: "com.liferay.gradle.plugins.wsdd.builder", version: "1.0.9"
    }
    
    repositories {
    maven {
    url "https://repository-cdn.liferay.com/nexus/content/groups/public"
    }
    }
    }
    
    apply plugin: "com.liferay.portal.tools.wsdd.builder"

これで、WSDDを構築してデプロイする準備ができました。次のセクションでは、このデプロイ方法を説明します。

## WSDDの構築とデプロイ

WSDDを構築するには、`*-service`モジュールで`buildWSDD` Gradleタスクを実行する必要があります。開発ツールによって、適切な方法は異なります。

- **Liferay @ide@:** Liferay Workspaceパースペクティブの*Gradle Tasks*ペイン（通常は右側）から、[`*-service`]モジュールの[*build*]フォルダを開き、[*buildWSDD*]をダブルクリックします。
- **コマンドライン:** [`*-service`]モジュールに移動して、`../../../gradlew buildWSDD`を実行します。Gradleラッパー（`gradlew`）の正確な場所は、異なる場合があることに注意してください。Liferay Workspaceプロジェクトの場合、通常はルートワークスペースフォルダにあります。

もし、`buildWSDD`が失敗してしまった場合は、`buildWSDD`の失敗の一般的な原因として`*-service`モジュールに対してWSDD Builderが必要とする依存関係を満たしていないことが考えられます。これらの依存関係はプロジェクトのコードによって異なることに注意してください。標準セットはありません。しかし、ポートレットの開発には以下の依存関係が必要になることがよくあります。

    compileOnly group: "javax.portlet", name: "portlet-api", version: "3.0.0"
    compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
    compileOnly group: "com.liferay", name: "com.liferay.registry.api", version: "2.0.0"

アプリの依存関係の検索と設定方法の詳細については、[こちらをクリック](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies)してください。

`*-service`プロジェクトの`build/libs`フォルダで、`buildWSDD`タスクはWSDDを含む`*-service-wsdd-[version].jar`を作成しました。このJARを@product@インスタンスにデプロイします。これで、SOAP Webサービスは、以下のパターンを使用するURLで利用可能になります。

    yourportaladdress/o/your.apps.service.module.context/api/axis

たとえば、*Foo*というアプリケーションがモジュール`foo-api`、`foo-service`、および`foo-web`で構成されている場合、アプリのサービスモジュールコンテキストは`foo-service`です。このアプリが`http://localhost:8080`で実行されているローカルの@product@インスタンスにデプロイされている場合、以下で対象のSOAPサービスにアクセスすることができます。

    http://localhost:8080/o/foo-service/api/axis

アプリの`*-service`モジュールコンテキストがわからない場合は、アプリが実行されているアプリケーションマネージャでアプリを検索して、探し出すことができます。たとえば、以下のスクリーンショットはアプリケーションマネージャでのFooアプリのモジュールを示しています。アプリケーションマネージャの`*-service`モジュールの名前は、`foo-service`で、コンテキストでもあります。また、アプリケーションのWSDDモジュールがグレー表示され、アクティブではなく解決済みとしてリストされていますが、これは正常です。WSDDモジュールはOSGiフラグメントであり、アクティブ化できません。しかし、意図したとおりに機能します。

次に、デフォルトでWSDDを含まない組み込みアプリ向けにWSDDモジュールを作成する方法を紹介します。これを行う必要がない場合は、チュートリアルの[Invoking Remote Services](/docs/7-1/tutorials/-/knowledge_base/t/invoking-remote-services)に進んでください。

## 組み込みアプリ用のWSDDの構築

@product@では、ポータルコンテキストの外部に存在する組み込みアプリ用のWSDDモジュールは提供していません。つまり、デフォルトでは、WikiやBlogなどのアプリのSOAP Webサービスにアクセスできません。このようなアプリでSOAP Webサービスを利用できるようにするには、[`liferay-portal`のGitHubリポジトリ](https://github.com/liferay/liferay-portal)からWSDDを構築して、デプロイする必要があります。
アプリケーションは、`liferay-portal/modules/apps`フォルダ内にあります。これらのアプリのWSDDを構築するには、まず`liferay-portal`のソースコードをマシンにダウンロードする必要があります。ローカルの`liferay-portal`のコピーから、WSDDビルドを実行します。

アプリのWSDDを構築するときは、マシンの`gradle`ではなく、`liferay-portal`で`gradlew`を使用してください。構築が完了したら、ローカルの`liferay-portal`のコピーにある`tools/sdk/dist`フォルダでWSDD JARを見つけることができます。
それ以外の場合は、アプリのWSDDの構築は前のセクションで紹介した方法と同じです。

たとえば、ブックマークアプリ用のWSDDを構築するには、まずターミナルの`liferay-portal/modules/apps/bookmarks/bookmarks-service`フォルダに移動します。次に、以下のコマンドを実行します。

    ../../../../../gradlew buildWSDD

そして、`liferay-portal/tools/sdk/dist/com.liferay.bookmarks.service-wsdd-[version].jar`をデプロイします。
インスタンスが`localhost:8080`でローカルに実行されている場合、[http://localhost:8080/o/com.liferay.bookmarks.service/api/axis](http://localhost:8080/o/com.liferay.bookmarks.service/api/axis)でブックマークアプリのSOAPサービスを確認できるはずです。

 作成したリモートWebサービスを呼び出す方法については、[Invoking Remote Services](/docs/7-1/tutorials/-/knowledge_base/t/invoking-remote-services)のチュートリアルを参照してください。

## 関連トピック

[Invoking Remote Services](/docs/7-1/tutorials/-/knowledge_base/t/invoking-remote-services)

[Invoking JSON Web Services](/docs/7-1/tutorials/-/knowledge_base/t/invoking-json-web-services)

[JSON Web Services Invoker](/docs/7-1/tutorials/-/knowledge_base/t/json-web-services-invoker)

[What is Service Builder?](/docs/7-1/tutorials/-/knowledge_base/t/what-is-service-builder)
