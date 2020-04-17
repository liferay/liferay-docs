---
header-id: improved-developer-tooling-liferay-workspace-maven-plugins-and-more
---

# 開発者向けツールの改善：Liferay Workspace、Mavenプラグインなど

[TOC levels=1-4]

適切なツールがあれば、アプリケーションの作成は快適になります。 主な構成要素は次のとおりです。

  - プロジェクトをスタブアウトするための豊富なテンプレート
  - 最先端のプラグインを提供する拡張可能なビルド環境
  - デプロイおよびランタイム管理ツール
  - アプリケーションアップグレードの自動化

[Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace)（ワークスペース）には、これらのすべてがあります。 これは[Liferay @ide@](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ide)と統合するGradleベースの開発環境で、「バニラ」Eclipse、IntelliJおよびNetBeansなどの、他のIDEと組み合わせた使用が可能です。 テスト、コードカバレッジ分析などのために[Gradleプラグイン](/docs/7-1/reference/-/knowledge_base/r/gradle)を追加して、WorkspaceのGradle環境を拡張できます。

Gradleよりも[Maven](/docs/7-1/tutorials/-/knowledge_base/t/maven)が好ましい場合は、代わりに[MavenベースのWorkspace](/docs/7-1/tutorials/-/knowledge_base/t/maven-workspace)を使用できます。 @product-ver@の効率的なアーティファクトと、新しいプロジェクト[アーキタイプ](/docs/7-1/tutorials/-/knowledge_base/t/generating-new-projects-using-archetypes)および[Mavenプラグイン](/docs/7-1/reference/-/knowledge_base/r/maven)は、Mavenによる@product@開発をこれまで以上に簡単にします。

Workspaceには、[Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli)が付属しています。これはプロジェクトの作成とデプロイ、ランタイム環境の管理などのためのコマンドラインツールです。 さまざまな種類の[プロジェクトテンプレート](/docs/7-1/tutorials/-/knowledge_base/t/creating-projects-with-blade-cli#project-templates)を提供し、GradleまたはMaven環境で開発するためのモジュールを作成します。

Liferayのツールは、アプリケーションのアップグレードプロセスも効率化します。 Liferay@ide@の[Upgrade Planner](/docs/7-1/tutorials/-/knowledge_base/t/liferay-upgrade-planner)は、従来のプラグインを@product-ver@APIに適合させます。 [Liferay Theme Generator](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes)は、新しいLiferayのJSテーマツールキットを使用するためにテーマとレイアウトテンプレートを[移行](/docs/7-0/tutorials/-/knowledge_base/t/migrating-a-6-2-theme-to-liferay-7)させ、それらを＠product-ver@に[適合](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-6-2-themes-intro)させます。

ツール改善のトピックは次のとおりです。

  - [プラグインSDKからLiferay Workspaceへの移行](/docs/7-1/tutorials/-/knowledge_base/t/from-the-plugins-sdk-to-liferay-workspace)
  - [Liferay Workspaceを使用したプロジェクトの開発](/docs/7-1/tutorials/-/knowledge_base/t/developing-modules-with-liferay-workspace)
  - [Mavenユーザー向けの新機能](/docs/7-1/tutorials/-/knowledge_base/t/whats-new-for-maven-users)
  - [他のビルドシステムとIDEの使用](/docs/7-1/tutorials/-/knowledge_base/t/using-other-build-systems-and-ides)
