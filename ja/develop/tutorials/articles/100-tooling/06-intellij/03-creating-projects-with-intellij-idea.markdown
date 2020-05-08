---
header-id: creating-projects-with-intellij-idea
---

# IntelliJ IDEAを使用したプロジェクトの作成

[TOC levels=1-4]

IntelliJ IDEAは、さまざまなモジュールプロジェクトを作成するためのNew Liferay Modulesウィザードを提供します。同じウィザードを使用して、テーマプロジェクトやWARスタイルのプロジェクトなどを作成することもできます。作業を開始する前に、IntelliJ環境で[Liferay Workspaceが作成/インポートされている](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-liferay-workspace-with-intellij-idea)ことを確認してください。以下の手順に従って、@product@モジュールを作成します。

1. *[ファイル]* → *[新規]* → *[Liferay Module]*に移動します。

   ![図1：*[Liferay Module]*を選択すると、New Liferay Modulesウィザードが開きます。

2. 作成するプロジェクトを選択します。ウィザード自体は*モジュール*の性質を持ちますが、非OSGiベースのモジュールかつ利用可能なプロジェクトが多数あります（例: `テーマ`、`war-mvc-portlet`など）。利用可能なテンプレートの詳細については、 [Project Templates](/docs/7-1/reference/-/knowledge_base/r/project-templates)のリファレンスセクションを参照してください。

   ![図2：プロジェクトテンプレートを選択して、モジュールを作成します。](../../../images/intellij-modules.png)

3. 必要に応じて、プロジェクトのSDK（JDKなど）、パッケージ名、クラス名、およびサービス名を設定します。それから、*[次へ]*をクリックします。

4. プロジェクトに名前を付けます。そして、*[完了]*をクリックします。

 これで、プロジェクトがワークスペースのプロジェクトタイプフォルダで使用できます。x
