---
header-id: managing-projects-with-liferay-ide
---

# Liferay Dev Studioを使用したプロジェクトの管理

[TOC levels=1-4]

Liferay Dev Studioは、GUIからLiferayプロジェクトを管理する機能を提供します。
Liferay Dev Studioによるプロジェクトの管理方法を実践する前に、プロジェクトをデプロイおよび実行できるように、EclipseワークスペースでLiferayサーバーが設定されていることを確認しておく必要があります。Liferayバンドルを作成し、Liferay Workspaceにリンクする方法については、[Creating a Liferay Workspace with Liferay Dev Studio](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-liferay-workspace-with-liferay-ide)のチュートリアルを参照してください。

プロジェクトを作成したら、Dev Studioを使用してデプロイできます。まず、*[サーバー起動]*ボタン（![Start Server](../../../images/icon-start-server.png)）をクリックして、Liferayサーバーが起動していることを確認します。次に、Project Explorerからプロジェクトに移動し、*[サーバー]*メニューにある設定済みのLiferayバンドルにドラッグアンドドロップします。Liferayサーバーを停止する場合は、*[サーバー停止]*ボタン（![Stop Server](../../../images/icon-stop-server.png)）をクリックします。これで、実行中のLiferayインスタンスへのプロジェクトのデプロイが完了しました。

デプロイされたプロジェクトに[Gogoシェル](/docs/7-1/reference/-/knowledge_base/r/using-the-felix-gogo-shell)を使用して、プロジェクトが正常にデプロイされたかどうかを確認できます。
サーバービューで開始したポータルを右クリックし、*[Gogoシェルを開く]*を選択します。

![図1：Gogoシェルを使用してDev Studioでターミナルウィンドウを開くには、*[Gogoシェルを開く]*を選択します。](../../../images/open-gogo-shell.png)

Gogoシェルターミナルが表示され、Gogoコマンドを入力してLiferayインスタンスとデプロイされたプロジェクトを確認できます。`lb`コマンドを入力して、デプロイされたバンドルのリストを表示します。プロジェクトのステータスがアクティブの場合は、正常にデプロイされています。

![図2：Gogoシェルを使用して、プロジェクトがLiferayに正常にデプロイされたかどうかを確認できます。](../../../images/gogo-deploy-successful.png)

Dev StudioのGogoシェルを使用するには、[開発者モード](/docs/7-1/tutorials/-/knowledge_base/t/using-developer-mode-with-themes#setting-developer-mode-for-your-server-in-dev-studio)を有効にする必要があります。開発者モードは、 デフォルトでは[Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace)で有効になっています。

Dev StudioのLiferay WorkspaceパースペクティブはGradleベースであるため、利用できる追加のGradle機能がいくつかあります。Gradle Tasksツールバーには、マウスをクリックするだけで実行できるワークスペース用のGradleコマンドが表示されます。

![図3：Gradle Taskツールバーには、Gradleタスクとその説明があり、ダブルクリックすると実行できます。](../../../images/gradle-task-toolbar.png)

また、Liferay Module Project向けのさまざまなGradleビルド操作にアクセスすることもできます。モジュールプロジェクトを右クリックし、*[Liferay]*を選択して、実行するビルドコマンドを選択します。

![図4：Project ExplorerでGradleプロジェクトを右クリックすると、ビルド操作を実行できます。](../../../images/gradle-build-operations.png)

Liferay Dev StudioでのGradle開発の詳細については、[Using Gradle in Liferay Dev Studio](/docs/7-1/tutorials/-/knowledge_base/t/using-gradle-in-liferay-ide)のチュートリアルを参照してください。

 Dev Studioを使用して、GradleベースのLiferay Workspaceを管理する方法はこれで以上です。
