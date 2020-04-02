---
header-id: development-lifecycle-for-a-liferay-workspace
---

# Liferay Workspaceの開発ライフサイクル

[TOC levels=1-4]

Liferay Workspacesは、Liferayモジュールの開発ライフサイクルのすべてのフェーズをサポートする環境を提供します。

- [プロジェクトの作成](#creating-projects)
- [プロジェクトのビルド](#building-projects)
- [プロジェクトのデプロイ](#deploying-projects)
- [プロジェクトのテスト](#testing-projects)
- [プロジェクトのリリース](#releasing-projects)

このチュートリアルでは、Liferay Workspaceが提供する開発ライフサイクルフェーズについて説明します。それから、特定のツール（例: Blade CLIやLiferay @ide@）用のワークスペースの特定のライフサイクルフェーズを活用するための詳細を説明している、他のチュートリアルへと進みます。

## プロジェクトの作成

Liferay Workspaceの開発フェーズの最初のステップは、プロジェクトの作成プロセスです。Workspaceには、さまざまな種類のLiferayプロジェクトを作成するのに使用できるテンプレートが多数用意されています。また、Workspaceは[Liferay JS Toolkit](/docs/7-1/reference/-/knowledge_base/r/js-generator)で作成されたフロントエンドポートレットの開発サポートも提供します。
これらは、デフォルトでは`modules`フォルダに保存されています。

ワークスペースがモジュールを作成する場所を設定するには、ワークスペースの`gradle.properties`ファイルの`liferay.workspace.modules.dir`プロパティを編集します。デフォルトでは、モジュールは`[ROOT]/modules`フォルダに作成されます。

また、`gradle.properties`ファイル内にある`liferay.workspace.themes.dir`プロパティを指定することにより、テーマの作成場所も制御できます。テーマは通常、[Liferay Theme Generator](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes)を使用して作成された後に`themes`フォルダに移行されます。

Workspaceは、WARプロジェクトを作成する方法も提供します。WARプロジェクトは、`gradle.properties`ファイルの`liferay.workspace.wars.dir`プロパティで設定されたフォルダに作成されます。WARスタイルのプロジェクトを作成する[プロジェクトテンプレート](/docs/7-1/reference/-/knowledge_base/r/project-templates)がいくつかあり 、`wars`フォルダに保存する必要があります。

Blade CLIまたはLiferay Dev Studioを使用して、ワークスペース内にプロジェクトを作成する方法の詳細については、[Creating Projects with Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/creating-projects-with-blade-cli)と[Creating Modules with Liferay Dev Studio](/docs/7-1/tutorials/-/knowledge_base/t/creating-modules-with-liferay-ide)のチュートリアルをそれぞれ参照してください。

## プロジェクトのビルド

Liferay Workspaceは多くのビルド要件を抽象化するので、ビルド方法を気にすることなくプロジェクトの開発に集中することができます。Liferay WorkspaceはGradleを使用してビルドされているため、プロジェクトはGradleビルドライフサイクルを活用します。

WorkspaceのROOTフォルダ（例: `gradlew`）にGradleラッパーが含まれており、Gradleコマンドを実行するために活用できます。つまり、Gradleをマシンにインストールすることなく、Liferay Workspaceから使い慣れたGradleビルドコマンド（例: `build`、`clean`、`compile`など）を実行できます。 

| **注：**`blade| gw`を実行してからGradleコマンドを実行することで、ワークスペースのGradleラッパーを使用することもできます。これは、ワークスペースの| Gradleラッパーをパスを指定せずに実行できる簡単な方法です。ワークスペースのGradleラッパー| はルートフォルダに常駐しているため、深く| ネストされたモジュール（例: `../../../../gradlew compileJava`）を実行するのが面倒な場合があります。Blade CLIからGradleラッパーを実行すると、| Gradleラッパーが自動的に検出され、| どこででも実行できます。

Liferay Workspaceを使用すると、ワークスペースのプラグインが自動的に適用されます。これにより、多数のサブプロジェクトが追加され、Gradleの複雑さが隠れます。たとえば、典型的なプロジェクトである`settings.gradle`ファイルには、以下のようなサブプロジェクトが多数含まれています。

    ...
    include images:base:oracle-jdk:oracle-jdk-6
    include images:base:oracle-jdk:oracle-jdk-7
    include images:base:oracle-jdk:oracle-jdk-8
    include images:base:liferay-portal:liferay-portal-ce-tomcat-7.1-ga1
    include images:source-bundles:glassfish
    include images:source-bundles:jboss-eap
    include images:source-bundles:tomcat
    include images:source-bundles:websphere
    include images:source-bundles:wildfly
    include compose:jboss-eap-mysql
    include compose:tomcat-mariadb
    include compose:tomcat-mysql
    include compose:tomcat-mysql-elastic
    include compose:tomcat-postgres
    include file-server
    ...

ワークスペースのプラグインが自動的に行うので、これらのサブプロジェクトの適用について心配する必要はありません。同様に、`/themes`フォルダ内にあるフォルダに`liferay-theme.json`ファイルが含まれる場合、`gulp`プラグインがそのファイルに適用されます。`/modules`フォルダ内にあるフォルダに`bnd.bnd`ファイルが含まれる場合は、[liferay-gradle](/docs/7-1/tutorials/-/knowledge_base/t/liferay-sample-projects)プラグインがそのファイルに適用されます。すべてのワークスペースアプリに自動的に提供されるLiferay Gradleプラグインのリストについては、[Gradle](/docs/7-1/reference/-/knowledge_base/r/gradle)のリファレンス記事を参照してください。このように、Liferay Workspaceは開発プロセスを便利にするために、多くのプラグインとビルド設定をバックグラウンドで提供しています。

Gradleビルドライフサイクルの抽象化の良い例として、ワークスペースでのプロジェクトのデプロイプロセスが挙げられます。Gradleコマンドを実行することなく、ワークスペースからモジュールをビルド/デプロイできるのです。次は、これを行う方法について説明します。

## プロジェクトのデプロイ

Liferay Workspaceでは、使いやすいデプロイメカニズムを提供しており、カスタム設定なしでLiferayサーバーにプロジェクトをデプロイすることができます。Blade CLIまたはLiferay @ide@を使用してワークスペースからプロジェクトをデプロイする方法の詳細については、[Deploying Projects with Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/deploying-projects-with-blade-cli)および[Deploying Modules with Liferay @ide@](/docs/7-1/tutorials/-/knowledge_base/t/deploying-projects-with-liferay-ide)のチュートリアルにそれぞれアクセスしてください。

## プロジェクトのテスト

Liferayでは、@product-ver@用の構成設定を多数提供しています。特定の動作をシミュレート/テストするために、いくつかの異なる@product@のインストールを設定するのは、面倒かつ時間がかかってしまうことがあります。しかし、Liferay Workspaceを使用すると、環境設定を簡単に整理し、それらの設定で環境インストールを作成することができます。

Liferay Workspaceには、同じワークスペースで異なる環境を設定することができる`configs`フォルダがあります。たとえば、一つのLiferay Workspaceで、開発、テスト、および実稼働用に個別の@product@環境設定を設定することができるのです。

この`configs`フォルダには、5つのサブフォルダがあります。

- `common`: すべての環境に適用する、共通の設定を保持します。
- `dev`: 開発設定を保持します。
- `local`: ローカルでテストするための設定を保持します。
- `prod`: 実稼働サイトの設定を保持します。
- `uat`: UATサイトの設定を保持します。

これらの環境だけではありません。あらゆる環境をシミュレートするために、任意のサブフォルダを`configs`フォルダに作成することができます（例: `aws`、`docker`、など）。
各環境のフォルダは、`portal-ext.properties`やElasticsearchなどの独自のデータベースを提供できます。各フォルダ内のファイルは、ワークスペース内から作成される@product@インストールをオーバーレイします。

![図1：`configs/common`および`configs/[environment]`は、作成されたときに@product@バンドルをオーバーレイします。](../../../images/workspace-configs.png)

ワークスペースが@product@バンドルを作成すると、以下のことが起こります。

1. `configs/common`フォルダにある設定ファイルが、バンドルに適用されます。

2. 設定されたワークスペース環境（`dev`、`local`、`prod`、`uat`、など）は、`common`フォルダの既存の設定に加えて適用されます。

ワークスペースの`/bundles`フォルダに特定の環境設定で@product@バンドルを作成するには、以下を実行します。

    ./gradlew initBundle -Pliferay.workspace.environment=[ENVIRONMENT]

<!-- `blade server init` is not able to pass the environment param in currently.
This new feature is requested in BLADE-343. -Cody -->

ワークスペースの`/build`フォルダにディストリビューションが可能な@product@インストールを作成するには、以下を実行します。

    ./gradlew distBundle[Zip|Tar] -Pliferay.workspace.environment=[ENVIRONMENT]

`ENVIRONMENT`変数は、適用したい設定フォルダ（`dev`、 `local`、`prod`、`uat`、など）と一致している必要があります。

| **注：**Gradleコマンド経由で渡すのではなく、| `gradle.properties`ファイルでワークスペース環境を設定することをお勧めします。もし、そうする場合は、| `[USER_HOME]/.gradle/gradle.properties`ファイル内に| ワークスペース環境の変数を設定することをお勧めします。
|
|     liferay.workspace.environment=local
|
| 変数は、デフォルトで`local`に設定されています。

`configs`フォルダの使用をシミュレートするために、典型的なシナリオを考えてみましょう。
テスト用のローカルの@product@インストールと、実稼働サイトのシミュレーション用のUATインストールが必要だとします。2つの環境に対して、以下の設定が必要だと仮定します。

**ローカル環境**

- ローカルホストを指す、MySQLデータベースを使用する。
- セットアップウィザードをスキップする。

**UAT環境**

- ライブサーバーを指す、MySQLデータベースを使用する。
- セットアップウィザードをスキップする。

ワークスペースでこれら2つの環境を設定するには、以下の手順に従います。

1. `configs/common`フォルダを開き、`setup.wizard.enabled=false`プロパティを持つ`portal-setup-wizard.properties`ファイルを追加します。
2. `configs/local`フォルダを開き、`portal-ext.properties`ファイル内のローカルホストのMySQLデータベース設定を設定します。
3. `configs/uat`フォルダを開き、`portal-ext.properties`ファイル内のライブサーバーのMySQLデータベース設定を設定方法します。

4. これで、2つの環境が設定されたので、以下を使ってそのうちの一つを作成します。

       ./gradlew distBundle[Zip|Tar] -Pliferay.workspace.environment=uat
   
   これで、2つの環境を正常に設定し、そのうちの1つが作成できました。

 これで、Liferay Workspaceを使用してさまざまな@product@バンドル環境をテストできるようになりました。

## プロジェクトのリリース

Liferay Workspaceは組み込みのリリースメカニズムを提供していませんが、ワークスペースで外部のリリースツールを使用する簡単な方法があります。最も一般的な選択肢は、プロジェクトをMaven Nexusリポジトリにアップロードすることです。[Artifactory](https://www.jfrog.com/artifactory/)のような他のリリースツールを使用することもできます。

プロジェクトをリモートリポジトリにアップロードすると、他のワークスペース以外のプロジェクトと共有する必要がある場合に便利です。また、プロジェクトを公開する準備ができている場合は、公開リモートリポジトリにプロジェクトをアップロードすると、他の開発者がプロジェクトを使用できるようになります。

ワークスペースのプロジェクト用に、Maven Nexusリポジトリを設定する方法の詳細については、[Creating a Maven Repository](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-maven-repository)および[Deploying Liferay Maven Artifacts to a Repository](/docs/7-1/tutorials/-/knowledge_base/t/deploying-liferay-maven-artifacts-to-a-repository)のチュートリアルを参照してください。
