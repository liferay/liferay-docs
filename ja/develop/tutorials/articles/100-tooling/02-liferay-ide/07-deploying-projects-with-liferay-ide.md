---
header-id: deploying-projects-with-liferay-ide
---

# Liferay Dev Studioを使用したプロジェクトのデプロイ

[TOC levels=1-4]

Liferay Dev Studioを使ったプロジェクトのデプロイメントは簡単です。プロジェクトをデプロイする前に、Dev StudioでLiferayサーバーが設定されていることを確認してください。確認方法については、[Installing a Server in Liferay Dev Studio](/docs/7-1/tutorials/-/knowledge_base/t/installing-a-server-in-liferay-ide)を参照してください。

Liferayサーバーにプロジェクトをデプロイするには、2つの方法があります。デプロイする前に、Liferayサーバーを起動する必要があります。

1. Package Explorerウィンドウからプロジェクトを選択し、サーバーウィンドウでLiferayサーバーにドラッグします。

   ![図1：ドラッグアンドドロップを使用して、プロジェクトを@product@にデプロイできます。](../../../images/starting-module-dev-drag-module.png)

2. サーバーウィンドウでサーバーを右クリックし、*[追加と削除...]*を選択します。デプロイするプロジェクトをAvailableウィンドウからConfiguredウィンドウに追加します。そして、*[完了]*をクリックします。

   ![図2：このデプロイメントの方法を使用すると、複数のプロジェクトをデプロイする時に便利です。](../../../images/add-and-remove-ide.png)

| **注：**Mavenのレガシーアプリケーションの場合は、次の方法でデプロイができていました。Package Explorerで右クリックし、*[Liferay]* → *[Maven]* | → *[liferay:deploy]*。LiferayのMaven| アーキタイプは、レガシーの`liferay-maven-plugin`にもはや依存しません。Maven| プロジェクトをDev Studioにデプロイするには、上記の方法に従ってください。

[Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace)を使用してプロジェクトをデプロイする場合、`watch`というBlade CLIタスクを使用してプロジェクトをデプロイします。これにより、ローカルプロジェクトが監視され、保存された変更がデプロイされたプロジェクトに迅速に伝播され、
プロジェクトのアップデートをLiferayサーバーからほぼ瞬時に表示することができます。`watch`タスクの詳細については、[Deploying Projects with Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/deploying-projects-with-blade-cli)の記事を参照してください。

| **注：**プロジェクトを右クリックして、*[Liferay]* | → *[watch]*を選択すると、`watch`タスクを使用してLiferay Workspaceに存在しないスタンドアロンプロジェクトをデプロイすることができます。

 Liferayサーバーにプロジェクトをデプロイしたら、Dev Studioのコンソールウィンドウでインストールを確認できます。
