---
header-id: migrating-plugins-sdk-projects-to-liferay-workspace
---

# Liferay WorkspaceへのプラグインSDKプロジェクトの移行

[TOC levels=1-4]

プラグインSDKは@product@ 7.0で廃止され、@product@ 7.1で削除されました。したがって、カスタムアプリケーションを@product-ver@にアップグレードするには、それらを新しい環境に移行する必要があります。 [Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace)は、コード移行向けに推奨された環境であり、このセクションで想定される選択肢です。

カスタムコードをワークスペースに移行するには、次の2つの手順に従う必要があります。

1.  プラグインSDKプロジェクトをUpgrade Plannerにインポートします。

2.  プラグインSDKプロジェクトを、サポートされているワークスペースビルドタイプに変換します。

最初に、プラグインSDKプロジェクトのインポートを実行します。

## 既存のプラグインSDKプロジェクトのインポート

Upgrade Plannerでこの手順を開始すると、プラグインSDKプロジェクトがUpgrade Plannerにインポートされます。 これらのプロジェクトは、Upgrade Plannerプロセスの開始時に設定したプラグインSDKから生じます。

コードをマニュアルでアップグレードする場合は、この手順をスキップできます。

これで、プラグインSDKプロジェクトを新しいワークスペースに移行する準備が整いました。

## 既存のプラグインをWorkspaceに移行する

Liferay WorkspaceはGradleまたはMaven環境として生成されますが、プラグインSDKのAntビルドはサポートしていません。 このため、プロジェクトをサポートされているビルドツールのいずれかに変換する必要があります。

  - Gradle
  - Maven

Gradleベースのワークスペースでこの手順を開始すると、AntベースのプラグインSDKプロジェクトは、プロジェクトタイプ（例: `wars`）に基づいて適切なワークスペースフォルダにコピーされ、Gradleプロジェクトに変換されます。 コマンドラインを介してこれを完了するBlade CLIコマンドもあります。 詳細については、[Converting Plugins SDK Projects with Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/converting-plugins-sdk-projects-with-blade-cli)をご覧ください。

AntプロジェクトをMavenワークスペースに移行する場合、プロジェクトの種類（例: `wars`）に基づいて、プロジェクトを適切なフォルダにマニュアルでコピーする必要があります。 プラグインSDKプロジェクトの大部分は、ワークスペースの`wars`フォルダに属します。 ワークスペースのフォルダ構造の完全な概要については、[Workspace Anatomy](/docs/7-1/tutorials/-/knowledge_base/t/configuring-a-liferay-workspace)セクションを参照して、カスタムアプリケーションを配置する場所を選択できます。 決定したら、カスタムアプリケーションを該当するワークスペースフォルダにコピーします。

次に、プロジェクトをAntからMavenに変換してください。 この変換はマニュアルで完了する必要があります。

完了したら、プロジェクトを適用可能なワークスペースフォルダにGradle/Mavenプロジェクトとして配置してください。
