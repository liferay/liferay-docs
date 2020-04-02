---
header-id: managing-themes-in-liferay-workspace
---

# Liferay Workspaceでテーマを管理する

[TOC levels=1-4]

@product@のテーマ作成は、2つの異なるツールを使用して実現できます。

- [Liferay Theme Generator](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes)（Liferay JS Theme Toolkitを使用した、Node.jsベースのテーマ）
- [Project template/archetype](/docs/7-1/reference/-/knowledge_base/r/theme-template)（Gradle/Mavenベース）

Liferay Workspaceは、開発者がLiferay Theme Generatorを使用してテーマを作成できる環境を提供し、その作業をDevOps戦略全体にシームレスに統合できます。Liferay Theme Generatorは、Workspace内にNode.jsベースのテーマを作成する場合や外部で活用してテーマをWorkspaceにコピーする場合に活用できます。

Workspaceでは、CI環境でLiferay JS Theme Toolkitのツールを使用できないユーザー向けに、従来のJavaベースのテーマアプローチ（Gradle/Mavenの活用）も提供しています。

以下では、Node.jsベースのテーマとGradle/Mavenベースのテーマの両方をWorkspaceで管理する方法を説明します。

## WorkspaceのNode.jsテーマ

Liferay Workspaceは、Themes Generatorで作成されたテーマに対してのみ、`themes`フォルダを用意します。テーマを作成するにあたって、Blade CLIが提供するコマンドやMavenアーキタイプはありません。それらを作成するには、`themes`フォルダ内から[Liferay Theme Generator](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes)を活用する必要があります。また、作成されたテーマをフォルダにコピーすることもできます。

次に、テーマ管理機能のデモンストレーションを行います。Liferay Theme Generatorに必要なツールがインストールされていることを確認してください。

1. ワークスペースの`themes`フォルダに移動して、以下のコマンドを実行します。

       yo liferay-theme
   
   プロンプトに従って、テーマを作成します。

2. 新しいテーマに移動して、`../gradlew build`を実行します。Liferay WorkspaceがGradleを使用して、フロントエンドテーマをビルドします。内部では、Liferayの[Node Gradle Plugin](/docs/7-1/reference/-/knowledge_base/r/node-gradle-plugin)が適用され、テーマのビルドに使用されます。

3. Liferay Workspaceでは、テーマタイプが区別されます。たとえば、Theme Generatorでビルドされたテーマを`wars`フォルダにコピーしてビルドすることはできません。Workspaceのルートフォルダから以下のコマンドを実行すると、プロジェクトがWorkspaceによって認識されているかどうかをテストできます。

       ../gradlew projects
   
   CLIでは、`themes`プロジェクトの下に新しく作成したテーマが表示されるはずです。

       Root project 'liferay-workspace'
       +--- Project ':themes'
       |    \--- Project ':themes:my-generated-theme'
   
   WARスタイルのテーマ（Gradle/Mavenベース）を`themes`フォルダに移動した場合、Gradleの`projects`コマンドでは認識されません。

   **注：**Workspaceは、`package.json`ファイルがあるかどうかを確認することにより、テーマがTheme Generatorによって作成されたものかどうかを識別します。このファイルがないテーマは、`themes`フォルダ内で互換性がありません。

 これで、作成されたテーマがWorkspaceでどのように認識され、どこに置かれるのかが分かりました。次は、WorkspaceによるWARスタイルのテーマの管理方法について説明します。

## WorkspaceでのGradle/Mavenテーマ

Liferay Workspaceでは、WARスタイルのプロジェクトには`wars`フォルダが提供されます。[`theme`](/docs/7-1/reference/-/knowledge_base/r/theme-template)プロジェクトテンプレートまたはアーキタイプを使用して[Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli)またはMavenで作成されたテーマは、Workspace内でプロジェクトを作成するときに自動的にこのフォルダに作成されます。

Liferayの`theme`プロジェクトテンプレートを使用してビルドされたテーマは、常にWARであり、Workspaceの`wars`フォルダ内に存在する必要があるので、絶対に`themes`フォルダに移動させないでください。このフォルダは、Theme Generatorによって作成されたテーマ専用です。

Workspaceで既存のWARスタイルのテーマをビルドするには、`../gradlew build`コマンドを実行します。Liferay Workspaceは、Gradleを使用してテーマをビルドします。内部では、Liferayの[Theme Builder Gradle Plugin](/docs/7-1/reference/-/knowledge_base/r/theme-builder-gradle-plugin)が適用され、テーマのビルドに使用されます。Mavenワークスペースでも同様に機能します。
詳細については、[Building Themes in a Maven Project](/docs/7-1/tutorials/-/knowledge_base/t/building-themes-in-a-maven-project)のチュートリアルを参照してください。

 これで、WARスタイルのテーマがWorkspaceでどのようにビルドされ、どこに存在するのかが分かりました。
