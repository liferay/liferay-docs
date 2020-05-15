---
header-id: creating-a-liferay-workspace-with-blade-cli
---

# Blade CLIを使用してLiferay Workspaceを作成する

[TOC levels=1-4]

このチュートリアルでは、Blade CLIを使用してLiferay Workspaceを作成する方法を説明します。[Installing Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/installing-blade-cli)のセクションでインストールしたBlade CLIツールには、Liferayプロジェクトの構築とカスタマイズに役立つさまざまなコマンドが用意されています。プロジェクトを構築してカスタマイズする前に、Liferay Workspaceの作成をまず最初に行います。Blade CLIを使用して、GradleまたはMavenベースのワークスペースを作成することができます。Mavenで構築されたLiferay Workspaceの管理の詳細については、[Maven Workspace](/docs/7-1/tutorials/-/knowledge_base/t/maven-workspace)のチュートリアルを参照してください。

ワークスペースは、すべてのカスタムLiferayプロジェクトのホームです。ワークスペースが必要なフォルダに移動し、以下のコマンドを実行してGradleベースのワークスペースを構築します。

    blade init -v 7.1 [WORKSPACE_NAME]

Mavenベースのワークスペースを作成するには、代わりに以下を実行します。

    blade init -v 7.1 -b maven [WORKSPACE_NAME]

| **注：**ワークスペースを最初に初期化するときに設定したバージョンは、| `liferay.version.default`プロパティと共にワークスペースの`.blade.properties`ファイルに保存されます。|このバージョンは、| 対応するプロジェクトのテンプレートバージョンに基づいてプロジェクトを作成するときに適用されます。
| | 別の@product@バージョン用のプロジェクトを開発する場合は、| Blade initコマンドで異なるバージョンを渡すことができます。たとえば、| | ```bash | blade init -v 7.0 [WORKSPACE_NAME] | ```

ワークスペースの初期化には、ダウンロードやインターネットへのアクセスは必要ありません。

Plugins SDKがあり、Blade CLIを使用してLiferay Workspaceに移行する場合は、Plugins SDKのルートフォルダに移動して、以下のコマンドを実行します。

    blade init -u

このコマンドは、ワークスペースを構築し、ワークスペース内で使用する現在のPlugins SDK環境を自動的に追加して、設定します。
`init -u`コマンドの詳細については、[Configuring a Plugins SDK in Your Workspace](/docs/7-0/tutorials/-/knowledge_base/t/creating-a-liferay-workspace-with-blade-cli#configuring-a-plugins-sdk-in-your-workspace)のセクションを参照してください。ワークスペース内からPlugins SDKを使用する方法の詳細については、[Using a Plugins SDK From Your Workspace](/docs/7-0/tutorials/-/knowledge_base/t/configuring-a-liferay-workspace#using-a-plugins-sdk-from-your-workspace)のセクションを参照してください。

ワークスペースが作成されたら、そのフォルダ構造を確認します。フォルダとビルド/プロパティファイルがいくつか自動生成されました。

- `configs`
- `gradle`
- `modules`
- `themes`
- `wars`
- `build.gradle`
- `gradle.properties`
- `gradle-local.properties`
- `gradlew`
- `settings.gradle`

ワークスペースのルートディレクトリに含まれるビルド/プロパティファイルは、ワークスペースのGradleプロパティを設定し、モジュールのビルドプロセスを促進します。[Configuring a Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/configuring-a-liferay-workspace)のチュートリアルでは、作成されたこれらのファイル/フォルダの詳細が説明されています。これ以降のチュートリアルでは、これらのフォルダとプロパティファイルの使用方法について説明します。

次は、ワークスペース内からインスタンスを作成および使用する方法について説明します。

## ワークスペースからLiferayインスタンスを実行する

[Configuring a Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/configuring-a-liferay-workspace#adding-a-liferay-bundle-to-a-workspace)のチュートリアルで説明したように 、Liferay WorkspacesはLiferayサーバーの作成および保持ができます。これにより、実行中のLiferayインスタンスに対してプラグインをビルド/テストすることができます。Liferayサーバーを適切に作成してワークスペースにインストールしたら、Blade CLIと共に使用を開始することができます。Liferayインスタンスを起動するには、以下を実行します。

    blade server start -b

このコマンドは、Liferayサーバーを別のウィンドウで起動します。サーバーをデバッグモード（`-d`）で実行するオプションもあります。

 これで、ワークスペースにLiferayサーバーが組み込まれて、Blade CLIを使用してサーバーを起動できるようになりました。
