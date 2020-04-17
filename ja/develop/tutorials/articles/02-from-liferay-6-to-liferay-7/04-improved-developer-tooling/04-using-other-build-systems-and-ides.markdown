---
header-id: using-other-build-systems-and-ides
---

# 他のビルドシステムとIDEの使用

[TOC levels=1-4]

@product@はツールに依存しないため、好きなツールを使用して開発できます。 Workspaceを使用しない場合は任意のIDEを使用でき、さらにGradle、Bnd、またはBndToolsを使用することもできます。 欠点は、BladeとWorkspaceで取得したLiferay固有のプロジェクトテンプレートが失われることです。

Bladeを使用するとLiferay Workspaceだけでなく、どこでも開発できるモジュールを作成できます。

Workspaceに依存しない、Liferayが提供する新しいGradle機能を次に示します。

  - Liferayの[Gradleプラグイン](/docs/7-1/reference/-/knowledge_base/r/gradle)
  - Liferay @ide@のビルドシッププラグイン
  - モジュール開発とGradleタスク操作用の、Liferay @ide@の[新しいGradleビュー](/docs/7-1/tutorials/-/knowledge_base/t/using-gradle-in-liferay-ide)

Liferayは、@product@ IDEへ依存しないことに対して懸命に取り組んできました。 Liferayモジュール開発者にはIntelliJを使用する開発者もいれば、なかにはNetBeansの使用を好む開発者もいます。

最後に、[Liferayサンプルプロジェクト](/docs/7-1/tutorials/-/knowledge_base/t/liferay-sample-projects)をコピーおよび変更して、Bladeテンプレートの代わりにテンプレートとして使用できます。 それらは以下のビルドシステムで利用可能です。

  - Gradle
  - Liferay Workspace
  - Maven

Liferayのツールへの取り組みによって、@product-ver@は大幅に改善されました。 当社のツールは、@product-ver@へのアップグレード、従来のプラグインの開発をこれまでのように継続すること、また最適な開発環境への移行を支援します。 Liferay Workspaceと改善されたMavenサポートにより、モジュール開発が容易になります。 また、他のツールを使用して@product@で開発するのがこれまで以上に簡単になりました。 ツールのオプションは幅広く用意されています。
