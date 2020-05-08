---
header-id: maven-workspace
---

# Maven Workspace

[TOC levels=1-4]

Liferay Maven Workspaceは、MavenでビルドされたLiferayプロジェクトの保持および管理のために構築された環境です。このワークスペースは、さまざまなMavenプラグインと設定済みのプロパティを適用することにより、Liferayプロジェクトの管理を支援します。Liferay Maven Workspaceが提供するMavenプロジェクトの完全な開発ライフサイクルにより、@product@向けの開発はこれまでになく簡単になります。このチュートリアルでは、Liferay Maven Workspaceの開発ライフサイクルを活用する方法を説明します。

まず最初に、Maven Workspaceをインストールする方法を説明します。

## インストール

Maven Workspaceは、アーキタイプまたは[Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli)からワークスペースプロジェクトを作成することによりインストールされます。以下のコマンドを実行すると、アーキタイプを介してワークスペースが作成できます。

    mvn archetype:generate \
    -DarchetypeGroupId=com.liferay \
    -DarchetypeArtifactId=com.liferay.project.templates.workspace \
    -DgroupId=[GROUP_ID] \
    -DartifactId=[WORKSPACE_NAME] \
    -Dversion=[VERSION]

Blade CLIがインストールされていて、アーキタイプを作成する代わりにそれを使用したい場合は、以下のコマンドを実行します。

    blade init -b maven [WORKSPACE_NAME]

これで、Maven Workspaceが現在のフォルダに作成されます。Maven Workspaceには他のツールやCLIは必要ありません。

## 基本構造

デフォルトのMaven Workspaceには、以下のフォルダ/ファイルが含まれています。

- `[MAVEN_WORKSPACE]`
   - `configs`
      - `common`
      - `dev`
      - `local`
      - `prod`
      - `uat`
   - `modules`
      - `pom.xml`
   - `themes`
      - `pom.xml`
   - `wars`
      - `pom.xml`
   - `pom.xml`

`configs`フォルダの詳細については、[Testing Modules](/docs/7-1/tutorials/-/knowledge_base/t/development-lifecycle-for-a-liferay-workspace#testing-projects)のセクションを参照してください。`modules`、`themes`、および`wars`フォルダは、このタイプのプロジェクトを保持します。
親`pom.xml`は、ワークスペースをMavenプロジェクトとして設定し、Maven Workspaceが@product-ver@プロジェクトを処理するために必要な[Bundle Supportプラグイン](/docs/7-1/reference/-/knowledge_base/r/bundle-support-plugin)を適用します。POMで[ワークスペースプロパティ](#configuring-maven-workspace-properties)を設定することもできます。この点ついては、後半で説明します。

次は、ワークスペースを使用したバンドルの初期化およびパッケージ化の方法について説明します。

## LiferayバンドルをMaven Workspaceに追加する

Liferay Maven Workspaceは、Liferayサーバーの作成および保持ができます。これにより、実行中のLiferayインスタンスに対してプラグインをビルド/テストできます。Liferayインスタンスを作成する前に、ワークスペースのルートフォルダにある`pom.xml`ファイルを開き、`liferay.workspace.bundle.url`プロパティのダウンロードURLを設定して、作成およびインストールするLiferayバンドルのバージョンを設定します。例えば、

    <properties>
    <liferay.workspace.bundle.url>
    https://releases-cdn.liferay.com/portal/7.1.0-ga1/liferay-ce-portal-tomcat-7.1.0-ga1-20180703012531655.zip
    </liferay.workspace.bundle.url>
    ...
    </properties>

Liferayバンドルの場所を`liferay.workspace.home.dir`プロパティで設定することもできます。これは、デフォルトでは`bundles`に設定されています。

**重要：**POMの`com.liferay.portal.tools.bundle.support`プラグインがバージョン`3.2.0以降`を使用するように設定されていることを確認してください。`liferay.workspace.bundle.url`プロパティは、Bundle Supportプラグインの古いバージョンを使用するワークスペースでは機能しません。プラグインのアップデート方法については、[Updating a Maven Workspace](#updating-a-maven-workspace)のセクションを参照してください。

ワークスペースのプロパティを確定したら、ワークスペースのルートフォルダに移動して、以下を実行します。

    blade server init

これは、ワークスペースの事前にバンドルされた[Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli)ツールを使用して、POMファイルで指定した@product@のバージョンをダウンロードし、`bundles`フォルダに@product@インスタンスをインストールします。Blade CLIを使用しない場合、あるいはBlade CLIがインストールされていない場合、このコマンドに相当する純粋なMavenは`mvn bundle-support:init`です。

ダウンロードプロセスをスキップする場合は、ワークスペースのROOTフォルダに`bundles`手動でフォルダを作成し、Liferay Portalバンドルをそのフォルダに抽出できます。

また、ワークスペース内からディストリビューションが可能な@product@バンドル（Zip）を作成することもできます。これを行うには、ワークスペースのルートフォルダに移動し、以下のコマンドを実行します。

    mvn bundle-support:dist

ディストリビューションファイルは、ワークスペースの`/target`フォルダから入手できます。

## Maven Workspaceのプロパティの設定方法

ルートの`pom.xml`ファイルで設定できるワークスペースプロパティは、いくつかあります。

- `liferay.workspace.bundle.url`: @product@バンドルのダウンロードに使用されるURL。
詳細については、[Adding a Liferay Bundle to a Maven Workspace](#adding-a-liferay-bundle-to-a-maven-workspace)を参照してください。
- `liferay.workspace.environment`: 使用するサーバー設定を保持する`configs`のサブフォルダの名前。詳細については、[Testing Modules](/docs/7-1/tutorials/-/knowledge_base/t/development-lifecycle-for-a-liferay-workspace#testing-projects)を参照してください。

プロパティを設定するには、プロパティ名にタグを追加します。これらをPOMで設定する方法の例については、以下のプロパティ設定を参照してください。

    <properties>
    <liferay.workspace.bundle.url>https://releases-cdn.liferay.com/portal/7.1.0-ga1/liferay-ce-portal-tomcat-7.1.0-ga1-20180703012531655.zip</liferay.workspace.bundle.url>
    <liferay.workspace.environment>local</liferay.workspace.environment>
    </properties>

次は、Maven Workspaceにモジュール/プロジェクトを追加およびデプロイする方法を説明します。

## モジュールの管理

Maven Workspaceによって、Mavenプロジェクトの管理はこれまでになく簡単になります。プロジェクトを作成するには、このタイプのためのプロジェクト（例: `modules`、`wars`など）に適切なワークスペースのフォルダに移動します。次に、プロジェクトのアーキタイプを作成します。利用可能なアーキタイプの完全なリストは、[Project Templates](/docs/7-1/reference/-/knowledge_base/r/project-templates)セクションで表示できます。プロジェクトが作成されると、Maven Workspaceのすべての機能を活用できます。

Maven Workspaceでは、Mavenを使用してプロジェクトを@product@にデプロイすることもできます。詳細については 、[Deploying a Project Built with Maven to @product@](/docs/7-1/tutorials/-/knowledge_base/t/deploying-a-project-built-with-maven-to-product)のチュートリアルを参照してください。

Maven Workspaceのテストインフラストラクチャを活用して、特定の環境でMavenプロジェクトをシミュレートすることも可能です。詳細については、[Testing Modules](/docs/7-1/tutorials/-/knowledge_base/t/development-lifecycle-for-a-liferay-workspace#testing-projects)のセクションを参照してください。

Mavenプロジェクトが固まり、準備ができたら、プロジェクトを公開することをお勧めします。Maven Workspaceではリリース機能は提供されていませんが、ワークスペースで外部リリースツールを使用する簡単な方法があります。詳細については、[Releasing Modules](/docs/7-1/tutorials/-/knowledge_base/t/development-lifecycle-for-a-liferay-workspace#releasing-projects)セクションを参照してください。

次に、Maven Workspaceをアップデートする方法について説明します。

## Maven Workspaceのアップデート

Liferay Workspaceは新しい機能と共に定期的にアップデートされるため、それに応じてワークスペースのインスタンスをアップデートすることをお勧めします。Maven Workspaceをアップデートするには、ワークスペースのルート`pom.xml`ファイルで設定されたBundle Supportプラグインを以下のようにアップデートする必要があります。

    <plugin>
    <groupId>com.liferay</groupId>
    <artifactId>com.liferay.portal.tools.bundle.support</artifactId>
    <version>3.2.5</version>
    ...
    </plugin>

これで、バージョンが最新の利用可能なリリースにアップデートします。Bundle Supportプラグインの利用可能なリリースは、[こちら](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.portal.tools.bundle.support/)で参照できます。
