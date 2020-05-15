---
header-id: creating-themes-with-liferay-ide
---

# Liferay Dev Studioを使ったテーマの作成

[TOC levels=1-4]

Liferay Dev Studioでは、Liferayテーマプロジェクトを作成および設定することができます。テーマは、スタンドアロンまたは[Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace)で作成できます。
GradleまたはMavenベースのテーマを作成することもできます。Dev Studioでのテーマ作成は、以下の手順で行います。

1. Dev Studioで、*[ファイル]* → *[新規]* → *[Liferay Module Project]*に移動します。

2. New Liferay Module Projectウィザードで、プロジェクトに名前を付け、プロジェクトテンプレートは*[theme]*を選択します。*Gradle*または*Maven*を選択して、テーマのビルドタイプも選択します。

   ![図1：テーマプロジェクトテンプレートを使用して、Dev StudioでLiferayテーマを作成します。](../../../images/theme-in-ide.png)

3. *[完了]*を選択します。

 これで、Dev Studioのテーマプロジェクトが作成されました。[このチュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/deploying-projects-with-liferay-ide)では、デプロイする方法を説明しています。

[Dev StudioインスタンスでLiferay Workspaceを設定した](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-liferay-workspace-with-liferay-ide)場合、デフォルトではワークスペースの`wars`フォルダからテーマが使用できます。Dev Studioでワークスペースを設定していない場合は、Dev StudioのProject Explorerのルートから使用できます。

Dev Studioで作成されたテーマは、WAR形式のレイアウトに従っていることに注意してください。ウィザードがテーマのことを新しいモジュールプロジェクトと呼ぶので紛らわしいですが、これはWARです。

Dev Studioで作成したテーマを変更するには、変更するファイルのフォルダ構造をミラーリングし、それらをテーマの`webapp`フォルダにコピーします。

**重要：**Dev Studioの内部では、[テーマプロジェクトテンプレート](/docs/7-1/reference/-/knowledge_base/r/theme-template)を使用しています。
Dev Studioによって作成されたWAR形式のテーマは、Gradle/Mavenベースです。これは 、[Liferay JS Theme Toolkit](https://github.com/liferay/liferay-themes-sdk/tree/master/packages)を使用する[Liferay Theme Generator](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes)で作成されたテーマとは異なります。
これら2つの開発方法を混同しないでください。WorkspaceとDev Studioでこれらの2つの方法がどのように使用されるかの詳細については、[Managing Themes in Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/managing-themes-in-liferay-workspace)のチュートリアルを参照してください。

Liferay Theme Generatorを使用したLiferayテーマの作成に興味がある場合は、専用の[チュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes)を参照してください。Liferayテーマの一般情報については、専用のチュートリアルセクションである[Themes and Layout Templates](/docs/7-1/tutorials/-/knowledge_base/t/themes-and-layout-templates)を参照してください。
