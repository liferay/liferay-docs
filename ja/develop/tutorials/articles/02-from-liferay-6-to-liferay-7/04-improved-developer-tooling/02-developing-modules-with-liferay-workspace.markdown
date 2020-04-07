---
header-id: developing-modules-with-liferay-workspace
---

# Liferay Workspaceを使用したモジュールの開発

[TOC levels=1-4]

Workspaceは次の機能を備えた、Liferayの優れたモジュール開発環境です。

  - モジュール作成をブートストラップするテンプレート
  - GradleとMavenが、依存関係を管理し、モジュールを組み立てるためのシステムを構築
  - モジュールのデプロイとランタイム管理機能

Workspaceの一部である[Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli)（Blade）には、GradleおよびMavenベースのモジュールプロジェクトに向けた、追加され続けている30以上のテンプレートがあります。 テンプレートは、ビジネスロジックと重要な情報を入力するための、クラスとリソースファイルをスタブアウトします。

テンプレートの名前の一部を次に示します。

  - Activator
  - API
  - Content Targeting Report
  - Content Targeting Rule
  - Content Targeting Tracking Action
  - Control Menu Entry
  - MVC Portlet
  - Panel App
  - Portlet
  - Portlet Configuration Icon
  - Portlet Provider
  - Portlet Toolbar Contributor
  - Service
  - Service Builder
  - Service Wrapper
  - Simulation Panel Entry
  - Template Context Contributor
  - など..

これらのテンプレートに基づいて[Bladeはモジュールを作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-projects-with-blade-cli)します。 テンプレートの完全なリストについては、[Project Templates](/docs/7-1/reference/-/knowledge_base/r/project-templates)リファレンスセクションをご覧ください。

たとえば次のBladeコマンドは、`my-module`というLiferay MVCポートレットモジュールを作成します。

    blade create -t mvc-portlet -p com.liferay.docs.mymodule -c MyMvcPortlet my-module

Liferay @ide@の[モジュールプロジェクトウィザード](/docs/7-1/tutorials/-/knowledge_base/t/creating-modules-with-liferay-ide)は、テンプレートからWorkspaceモジュールも作成します。

![図1：Liferay @ide@を使用すると、開発者はテンプレートを選択してモジュールをスタブアウトできます。](../../../images/improved-tooling-module-wizard.png)

Liferay @ide@のコンポーネントウィザードは、 ポートレット、サービス、ラッパー、Struts操作などの[コンポーネントクラスの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-modules-with-liferay-ide#creating-component-classes)を容易にします。

![図2：Liferay @ide@のコンポーネントウィザードは、コンポーネントクラスの作成を容易にします。](../../../images/improved-tooling-component-wizard.png)

Workspaceでのモジュールの構築とデプロイは、[Liferay @ide@](/docs/7-1/tutorials/-/knowledge_base/t/deploying-projects-with-liferay-ide)および[Blade](/docs/7-1/tutorials/-/knowledge_base/t/deploying-projects-with-blade-cli)を使用して簡単に行えます。 WorkspaceはBndToolsを使用して、各モジュールのOSGiヘッダーを`META-INF/MANIFEST.MF`ファイルに生成します。 Workspaceは、Felix File Installコマンドを使用して、OSGiコンテナーにモジュールをデプロイします。

Liferay @ide@を使用すると、モジュールをドラッグしてPortalサーバーにデプロイできます。

![図3：Liferay @ide@では、ドラッグアンドドロップを使用してモジュールをデプロイできます。](../../../images/improved-tooling-drag-n-drop-onto-server.png)

Workspaceの詳細とLiferay @ide@での使用方法については、[このチュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-liferay-workspace-with-liferay-ide)を参照してください。

ターミナルでは、Bladeの`deploy`コマンドを使用してモジュールをデプロイできます。 たとえば次のコマンドは現在のモジュールをデプロイし、[自動的にリデプロイするためモジュールの変更を「監視」します](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#redeploying-module-changes-automatically)。

    blade deploy -w

モジュール開発の詳細については、チュートリアル[Starting Module Development](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development)を確認してください。
