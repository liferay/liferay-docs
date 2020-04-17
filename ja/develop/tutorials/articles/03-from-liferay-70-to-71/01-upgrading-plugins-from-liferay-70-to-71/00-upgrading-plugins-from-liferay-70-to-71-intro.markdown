---
header-id: upgrading-plugins-from-liferay-7-0-to-7-1
---

# プラグインを@product@ 7.0から7.1にアップグレードする

[TOC levels=1-4]

Liferay Workspaceのターゲットプラットフォーム機能とUpgrade Plannerは、@product-ver@へのアップグレードから、多くのマニュアルによる介入を必要とします。 ターゲットプラットフォームは、@product-ver@への依存関係のアップデートを容易にし、Upgrade Plannerは、プラグインを@product-ver@のAPIに適合させるのに役立ちます。 このチュートリアルでは、プラグインのアップグレード手順について説明します。

| **注: **Blade CLIの`convert`コマンドは | [従来のプラグインをLiferay Workspaceに移行](/docs/7-1/tutorials/-/knowledge_base/t/migrating-traditional-plugins-to-workspace-web-applications) | するため、Workspaceのアップグレード機能を活用できます。

プラグインのアップグレード手順は次のとおりです。

1.  その最新バージョンに[Liferay Workspaceをアップデート](/docs/7-1/tutorials/-/knowledge_base/t/updating-liferay-workspace)します。

2.  [ターゲットプラットフォーム](/docs/7-1/tutorials/-/knowledge_base/t/managing-the-target-platform-for-liferay-workspace) を@product-ver@の最新バージョンに設定して、 @product-ver@関連の依存関係をアップデートします。 （オプション）

3.  [プラグインの残りの依存関係をアップデート](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies)します。

4.  [Upgrade Plannerを使用して、コードを@product-ver@のAPIに適合](/docs/7-1/tutorials/-/knowledge_base/t/liferay-upgrade-planner)させます。 アップグレードツールは、[重大な変更](/docs/7-1/reference/-/knowledge_base/r/breaking-changes)がコードに影響を与える場所を示し、自動的にそれらの多くに対処します。

アップグレードされたプラグインは[@product-ver@にデプロイ](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#building-and-deploying-a-module)できるようになりました。

| **注: **プラグインがWorkspace外にある場合は | [ターゲットプラットフォームGradleプラグインをプロジェクトに適用](/docs/7-1/tutorials/-/knowledge_base/t/managing-the-target-platform-for-liferay-workspace#targeting-a-platform-outside-of-workspace)することで、 | そのプロジェクトのターゲットプラットフォームを設定できます。

## 関連トピック

[Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace)

[Workspace用のターゲットプラットフォームの管理](/docs/7-1/tutorials/-/knowledge_base/t/managing-the-target-platform-for-liferay-workspace)

[依存関係の設定方法](/docs/7-1/tutorials/-/knowledge_base/t/managing-the-target-platform-for-liferay-workspace)

[Liferay開発スタジオ](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ide)
