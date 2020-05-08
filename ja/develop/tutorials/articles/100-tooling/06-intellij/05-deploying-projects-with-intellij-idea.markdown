---
header-id: deploying-projects-with-intellij-idea
---

# IntelliJ IDEAを使用したプロジェクトのデプロイ

[TOC levels=1-4]

[プロジェクトを作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-projects-with-intellij-idea)し、IntelliJに[Liferayサーバーをインストール](/docs/7-1/tutorials/-/knowledge_base/t/installing-a-server-in-intellij-idea)したら、プロジェクトをデプロイします。これを行うには、以下の手順に従ってください。

1. Liferay Workspaceのフォルダ構造内からプロジェクトを右クリックし、*[Liferay]* → *[Deploy]*を選択します。

   これにより、IntelliJインスタンスの下部に表示されるビルドの進行状況を示すウィンドウが自動的にロードされます。

   ![図1：プロジェクトが正常にビルドされたことを確認します。](../../../images/intellij-project-build.png)

2. ビルドの進行状況ウィンドウから、プロジェクトが正常にビルドできていることを確認します。
次に、サーバーのウィンドウに戻り、設定済みのインスタンスでプロジェクトが起動することを確認します。以下のようなメッセージが表示されるはずです。

       INFO  [fileinstall-C:/liferay-workspace/bundles/osgi/modules][BundleStartStopLogger:35] STARTED com.liferay.docs_1.0.0 [652]
   
Blade CLIの`watch`タスクがプロジェクトをデプロイします。これにより、ローカルプロジェクトが監視され、保存された変更がデプロイされたプロジェクトに伝播されます。また、プロジェクトのアップデートがLiferayサーバーからほぼ瞬時に表示できるようになります。この`watch`タスクの詳細については、[Deploying Projects with Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/deploying-projects-with-blade-cli)の記事を参照してください。

 これで、プロジェクトの@product@へのデプロイが正常に完了しました。
