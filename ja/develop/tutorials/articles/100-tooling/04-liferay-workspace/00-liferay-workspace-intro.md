---
header-id: liferay-workspace
---

# Liferay Workspace

[TOC levels=1-4]

*Liferay Workspace*は、Liferayプロジェクトの保持と管理ができるように構築された環境です。このワークスペースは、さまざまなGradleのビルドスクリプトと設定済みのプロパティを提供することにより、Liferayプロジェクトの管理を支援することを目的としており、Gradleを使用して@product-ver@モジュールを作成するための公式な方法です。Gradleではなく、Mavenを使用したい場合の対処方法もあります。MavenでLiferay Workspaceを使用する方法については、[Maven Workspace](/docs/7-1/tutorials/-/knowledge_base/t/maven-workspace)のチュートリアルを参照してください。

Liferay Workspaceは多くの異なる開発環境で使用できるため、様々な開発者に対して柔軟に適用することができます。[Liferay Project SDKインストーラー](https://sourceforge.net/projects/lportal/files/Liferay%20Workspace)をダウンロードして実行することで、[Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli)（ワークスペースのデフォルトCLI）のインストールや新しいLiferay Workspaceの初期化、そして@ide@のダウンロードができます。

また、他のIDEと使用することもできます。たとえば、Liferay WorkspaceはLiferay @ide@と簡単に統合でき、シームレスな開発エクスペリエンスを提供します。Liferay @ide@とワークスペースの使用の詳細については、[Creating a Liferay Workspace with Liferay @ide@](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-liferay-workspace-with-liferay-ide)のチュートリアルを参照してください。

ワークスペースには、作成されたフォルダの管理に役立つように変更できるGradleプロパティもあります。デフォルトでは作成されないけれど、手動で作成および設定できるフォルダもあります。これにより、ワークスペースのフォルダ構造が自由にカスタマイズできます。ワークスペースのフォルダ構造とワークスペースの設定方法の詳細については、[Configuring a Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/configuring-a-liferay-workspace)のチュートリアルを参照してください。

Liferay Workspacesではモジュールの開発ライフサイクル全般が提供できるため、Liferay開発がこれまで以上に簡単にできます。開発ライフサイクルには、モジュールの作成、構築、デプロイ、テスト、およびリリースが含まれます。Liferay Workspaceの開発ライフサイクルの詳細については、[Development Lifecycle for a Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/development-lifecycle-for-a-liferay-workspace)のチュートリアルを参照してください。
