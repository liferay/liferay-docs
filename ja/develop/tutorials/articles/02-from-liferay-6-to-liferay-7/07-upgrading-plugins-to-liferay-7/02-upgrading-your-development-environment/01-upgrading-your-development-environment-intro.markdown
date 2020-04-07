---
header-id: upgrading-your-development-environment
---

# 開発環境のアップグレード

[TOC levels=1-4]

[Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace)は、お使いのLiferayプロジェクトを保持し管理するために構築されている、生成された環境です。 さまざまなビルドスクリプトと設定されたプロパティを提供することにより、Liferayプロジェクトの管理を支援することを目的としています。

Liferay Workspaceは、コード移行向けに推奨された環境です。そのため、このセクションで想定される開発環境になります。

ワークスペースのセットアップに進みます。

## Liferay Workspaceのセットアップ

カスタムアプリケーションのアップグレードを開始する前に、ワークスペース開発環境をセットアップする必要があります。 既存のワークスペースがない場合は、作成する手順に従ってください。 既存のワークスペースがある場合は、Upgrade Plannerにインポートする手順に従ってください。

### 新しいLiferay Workspaceの作成

Upgrade Plannerでこのステップを開始すると、Liferay Workspaceプロジェクトウィザードがロードされます。

1.  新しいワークスペースに名前を付けます。

2.  ワークスペース環境と今後のLiferayプロジェクトに適したビルドタイプ（GradleまたはMaven）を選択します。

3.  完了をクリックします。

これで、Upgrade Plannerで新しいLiferay Workspaceが利用可能になりました。

Liferay Workspaceをプランナー以外で作成する方法の詳細については、[Creating a Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-liferay-workspace-with-blade-cli)セクションを参照してください。

### 既存のLiferay Workspaceのインポート

既存の7.x Liferay Workspaceが既にある場合は、プランナーにインポートする必要があります。 この手順を開始すると、既存のワークスペースを選択するためのFile Explorer/Managerが表示されます。 選択すると、ワークスペースがProject Explorerにインポートされます。

お使いのIDEにワークスペースをインポートする方法の詳細については、[こちらの記事](/docs/7-1/tutorials/-/knowledge_base/t/creating-modules-with-liferay-ide#importing-existing-module-projects)をご覧ください。

## Liferay Workspaceの設定方法

アップグレードする@product@バージョンでワークスペースを構成する必要があります。 ワークスペースに関する以下の項目を確認してください。

  - バンドルURL
  - ターゲットプラットフォームバージョン

バンドルURLバージョンとターゲットプラットフォームバージョンは一致する必要があります。

これらの手順に沿って実行してください。

### バンドルURLの設定方法

バンドルURLは、ワークスペースでダウンロードする@product@バージョンを指示します。 このステップを開始すると、ワークスペースのバンドルURLプロパティが@product-ver@の最新リリースにアップデートされます。

ワークスペースのバンドルURLの設定方法については、記事[Adding a Liferay Bundle to Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/configuring-a-liferay-workspace#adding-a-liferay-bundle-to-a-workspace)で詳細をご覧ください 。

### ターゲットプラットフォームバージョンの設定方法

ターゲットプラットフォームは、ワークスペースで開発する@product@バージョンです。 これは、特定のリリースに関連付けられた依存関係を指定するために使用されます。 ターゲットプラットフォームを設定して依存関係を定義すると、ワークスペースが設定された規定の@product@バージョンに基づいて、依存関係バージョンを自動的に割り当てます。 この手順を開始すると、ワークスペースのターゲットプラットフォームプロパティが@product-ver@の最新リリースにアップデートされます。

詳細については、記事[Managing the Target Platform](/docs/7-1/tutorials/-/knowledge_base/t/managing-the-target-platform-for-liferay-workspace)をご覧ください。

## サーバーバンドルの初期化

アップグレードする@product@バージョン用にワークスペースを構成したら、サーバーバンドルを初期化できます。 これには、バンドルのダウンロードとそのフォルダへ展開することが含まれます（例: `bundles`）。 既存のワークスペースに古いLiferayバンドルが既に装備されている場合、古いバンドルが削除され、新しいバンドルが初期化されます。

コードをマニュアルでアップグレードして開発スタジオで作業している場合は、ワークスペースプロジェクトを右クリックして*Liferay*→*Initialize Server Bundle*を選択することで、これを行うことができます。 IntelliJを使用している場合は、記事[Installing a Server in IntelliJ](/docs/7-1/tutorials/-/knowledge_base/t/installing-a-server-in-intellij-idea)をご覧ください。 コマンドラインを介してこれを行う方法については、記事[Managing Your Liferay Server with Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/managing-module-projects-with-blade-cli)を参照してください。
