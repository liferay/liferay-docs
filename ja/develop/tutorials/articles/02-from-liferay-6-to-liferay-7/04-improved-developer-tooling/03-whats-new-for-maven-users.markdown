---
header-id: whats-new-for-maven-users
---

# Mavenユーザー向けの新機能

[TOC levels=1-4]

Liferay Portal 7.0+およびLiferay DXPは、Mavenの開発を完全にサポートし、いくつかの新機能と改善された機能を提供します。

  - [Maven向けLiferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/maven-workspace)
  - 新しいアーキタイプ
  - 新しいMavenプラグイン
  - より詳細な依存関係の管理

新しいアーキタイプ`com.liferay.project.templates.workspace`は、Mavenを使用してWorkspaceで開発するためのPOMファイルを含むLiferay Workspaceを生成します。 Workspaceサブフォルダではモジュールとテーマを開発できます。

@product-ver@は、さまざまなLiferayモジュールプロジェクト用の多くの新しいMavenアーキタイプを提供します。 @product-ver@向けの[Mavenアーキタイプ](/docs/7-1/reference/-/knowledge_base/r/project-templates)が30以上あり、今も多くが開発中です。 ここでは、よく使用されているものを次に示します。

  - Configuration Icons
  - フラグメント
  - Menu Buttons
  - Portlets
      - MVC
      - npm
      - Soy
      - Spring MVC
  - Service Builder
  - Themes
  - など...

LiferayのMavenアーキタイプは、さまざまなLiferayフレームワークとサービスタイプをカバーしています。 これらにより、MavenはLiferayモジュールおよびテーマを作成するための非常に優れたツールとなっています。 LiferayのMavenアーキタイプとその使用方法の詳細については、チュートリアル[Generating New Projects Using Archetypes](/docs/7-1/tutorials/-/knowledge_base/t/generating-new-projects-using-archetypes)をご覧ください。

Liferayは、ビルドプロセスを簡素化する、新規およびアップデートされた[Mavenプラグイン](/docs/7-1/reference/-/knowledge_base/r/maven)もいくつか提供します。 たとえば、次のプラグインはそれぞれスタイルシート、サービス、テーマを構築します。

  - [CSS Builder](/docs/7-1/tutorials/-/knowledge_base/t/compiling-sass-files-in-a-maven-project)
  - [Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/using-service-builder-in-a-maven-project)
  - [Theme Builder](/docs/7-1/tutorials/-/knowledge_base/t/building-themes-in-a-maven-project)

@product-ver@のモジュラリティは、よりきめ細かな依存関係の管理エクスペリエンスを提供します。 あらゆる面で`portal-impl`または`portal-service`（現在は `portal-kernel`）に依存する必要がなくなりました。 たとえば、@product@のWikiフレームワークを使用するには、Wikiモジュールのみに依存する必要があります。 追加の荷物を継承することなく、必要な機能を提供する簡潔なモジュールに依存関係を設定します。

Liferayの新しいWorkspace環境、Mavenアーキタイプ、Mavenプラグイン、および効率化されたモジュールにより、@product@での開発がこれまで以上に簡単になりました。 詳細については、[Maven tutorials](/docs/7-1/tutorials/-/knowledge_base/t/maven)を参照してください。
