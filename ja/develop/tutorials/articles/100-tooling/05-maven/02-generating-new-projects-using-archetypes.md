---
header-id: generating-new-projects-using-archetypes
---

# アーキタイプを使用した新しいプロジェクトの作成

[TOC levels=1-4]

Mavenプロジェクトをゼロから作成するのは大変な作業です。Liferayポートレットプロジェクトにはどのような依存関係が必要になるのか、Liferay Maven Service Builderプロジェクトはどのようなものなのか、Liferay Mavenベースのコンテキストコントリビューターを作成するにはどうすればいいのか、
などの疑問は、「Liferay Mavenアーキタイプ」によって解決することができます。

LiferayはMavenアーキタイプを多数提供しており、Liferayプロジェクトが簡単に作成できます。
このチュートリアルでは、LiferayのMavenアーキタイプを使用してプロジェクトを作成する方法を説明します。

この記事の執筆時点で、Liferayでは約60種類のMavenアーキタイプを提供しています。この数は今後も増えていく予定です。これらのアーキタイプは、別のリモートリポジトリ（例: [Liferayリポジトリ](https://repository.liferay.com)）から作成されるように設定していない限りは、セントラルリポジトリから作成されます。Liferayが提供するMavenアーキタイプを表示するには、以下のコマンドを実行します。

    mvn archetype:generate -Dfilter=liferay

作成されたアーキタイプは、すべて最新の@product@リリース向けというわけではありません。
一部の製品は、Liferayポータルの以前のバージョン（例: 7.0、6.2）向けです。たとえば、接頭辞の`com.liferay.maven.archetypes`が付いているアーキタイプは、Liferay Portal 6.2を対象としたレガシーアーキタイプです。`com.liferay.project.templates.[TYPE]` または`com.liferay.faces.archetype:[TYPE]`が接頭辞になっているものは、@product-ver@と互換性があります。

<!-- TODO: Monitor the archetypes; updates may be required for the above text
for upcoming 7.1 Maven archetypes. -Cody -->

Liferayが提供するMavenアーキタイプで、よく使われているもののリストを以下に示します。

- [Activator](/docs/7-1/reference/-/knowledge_base/r/activator-template)
- [Fragment](/docs/7-1/reference/-/knowledge_base/r/using-the-fragment-template)
- [MVC Portlet](/docs/7-1/reference/-/knowledge_base/r/using-the-mvc-portlet-template)
- [npm Angular Portlet](/docs/7-1/reference/-/knowledge_base/r/npm-angular-portlet-template)
- [npm React Portlet](/docs/7-1/reference/-/knowledge_base/r/npm-react-portlet-template)
- [Panel App](/docs/7-1/reference/-/knowledge_base/r/panel-app-template)
- [Portlet Provider](/docs/7-1/reference/-/knowledge_base/r/portlet-provider-template)
- [Service Builder](/docs/7-1/reference/-/knowledge_base/r/using-the-service-builder-template)
- [Soy Portlet](/docs/7-1/reference/-/knowledge_base/r/soy-portlet-template)
- [Theme](/docs/7-1/reference/-/knowledge_base/r/theme-template)
- などです。

<!-- TODO: readd JSF archetype, when available -Cody.

- [Liferay Faces](develop/tutorials/-/knowledge_base/7-1/jsf-portlets-with-liferay-faces)
  portlets
  
-->

@product-ver@と互換性のあるアーキタイプ（プロジェクトテンプレート）のドキュメンテーションについては、[Project Templates](/docs/7-1/reference/-/knowledge_base/r/project-templates)のリファレンスセクションを参照してください。Mavenアーキタイプの作成プロセスを変更する方法の詳細については、Mavenの[Archetype Generation](http://maven.apache.org/archetype/maven-archetype-plugin/generate-mojo.html)のドキュメンテーションを参照してください。

| **注：**Liferay Facesを使用してJSFポートレットを作成している場合、| JSFコンポーネントスイートのアーキタイプ宣言の例は次のリンクにあります。| [http://www.liferayfaces.org](http://www.liferayfaces.org/)
|

Liferay Mavenアーキタイプを使用して、Liferay MVCポートレットを作成する例を以下に示します。

1. コマンドラインで、Mavenプロジェクトを配置する場所に移動します。Liferayアーキタイプのみに対してフィルター処理された、以下のMavenアーキタイプの作成コマンドを実行します。

       mvn archetype:generate -Dfilter=liferay
   
2. 対応する番号（例: `11`）を選択して、`com.liferay.project.templates.mvc.portlet`アーキタイプを選択します。

   ほとんどの場合、最新のアーキタイプバージョンを選択する必要があります。提供されるアーキタイプバージョンは、@product@のすべての7.xバージョンと互換性があります。

3. 選択したMavenアーキタイプに応じて、Mavenプロジェクトに入力するアーキタイプオプションのセットが提供されます。MVCポートレットアーキタイプの場合、以下のプロパティが使用できます。

   - `groupId`: `com.liferay`
   - `artifactId`: `com.liferay.project.templates.mvc.portlet`
   - `version`: `1.0.6`
   - `package`: `com.liferay.docs`
   - `className`: `SampleMVC`
   必要なプロパティ値を入力すると、定義したプロパティ設定の概要が表示されます。`Y`を入力して、プロジェクトの設定を確認します。

これで、Mavenプロジェクトが作成され、アーキタイプ作成コマンドを実行したフォルダから利用することができます。そのフォルダに既存の親`pom.xml`ファイルがある場合、モジュールプロジェクトはそこに自動的に処理され、以下のようになります。

    <modules>
    ...
    <module>com.liferay.project.templates.mvc.portlet</module>
    </modules>

Liferay Mavenのアーキタイプはデプロイ可能なLiferayプロジェクトを作成しますが、それらは必要最低限のものであり、さらにカスタマイズが必要になる可能性があります。

MavenでビルドされたLiferayプロジェクトに素早く基盤を作成する場合は、Liferay Mavenアーキタイプを使用するのが最適なオプションです。
