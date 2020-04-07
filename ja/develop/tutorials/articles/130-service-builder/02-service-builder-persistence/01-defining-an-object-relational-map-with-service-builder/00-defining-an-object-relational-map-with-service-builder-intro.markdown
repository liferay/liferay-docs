---
header-id: defining-an-object-relational-map-with-service-builder
---

# Service Builderを使用したオブジェクトリレーショナルマップの定義

[TOC levels=1-4]

このチュートリアルでは、アプリケーションがデータを保持できるようにオブジェクトリレーショナルマップを定義する方法を学習します。 例として、Service Builderを使用する既存のLiferay Bookmarksアプリケーションを調べます。

ブックマークアプリケーションは、Liferayのアセットをブックマークします。 アプリケーションは、組織のブックマークとそのフォルダーを表す2つのエンティティ、またはモデルタイプを定義します。 これらのエンティティは、 *ブックマークエントリ* および *ブックマークフォルダ*と呼ばれます。 ブックマークにはフォルダーが必要であるため（ルートフォルダーであっても）、エントリエンティティはその属性の1つとしてフォルダーエンティティを参照します。

ブックマークアプリケーションのソースコードが存在 `ブックマーク-API`、 `ブックマークサービス`、及び `ブックマークウェブ` のモジュール。 `bookmarks-web` モジュールの `com.liferay.bookmarks.web.portlet` パッケージの `BookmarksAdminPortlet.java` および `BookmarksPortlet.java` ファイルに注目してください。 これらのポートレットクラスは、Liferayの [`MVCPortlet` クラス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html)拡張します。 MVCパターンのコントローラーとして機能します。 これらのクラスには、このセクションで作成する方法を学習するService Builderが生成したブックマークサービスを呼び出すビジネスロジックが含まれています。 アプリケーションのビューレイヤーは、 `bookmarks-web / src / main / resources / META-INF / resources` フォルダーのJSPに実装されています。

このチュートリアルでは、アプリケーションに次のタイプのモジュールがあることを前提としています。

  - `* -api`：サービスインターフェイス
  - `* -service`：サービスの実装
  - `* -web`：ポートレットとコントローラー

これらのモジュールの親フォルダーは、 *アプリケーションフォルダー*です。 [Service Builderプロジェクトテンプレート](/docs/7-1/reference/-/knowledge_base/r/using-the-service-builder-template) は、 `* -api` および `* -service` モジュールの作成に使用できます。 [MVCPortletテンプレート](/docs/7-1/reference/-/knowledge_base/r/using-the-mvc-portlet-template) などのクライアントUIプロジェクトテンプレートは、 `* -web` モジュールの作成に使用できます。 @ide@またはBladeを使用して、両方のテンプレートからプロジェクトを作成できます。

Service Builderを使用する最初の手順は、モデルクラスとその属性を `service.xml` ファイルで定義することです。 通常、このファイルは `* -service` モジュールのルートフォルダーにありますが、ビルドツールを構成して他のフォルダーで認識できるようにすることもできます。 Service Builderの用語では、モデルクラス *エンティティ*呼び出します。 たとえば、ブックマークアプリケーションには2つのエンティティがあります： `BookmarksEntry` および `BookmarksFolder`。 それぞれの要件は、 `bookmarks-service` モジュールの [service.xml](https://github.com/liferay/liferay-portal/blob/master/modules/apps/bookmarks/bookmarks-service/service.xml) で `<column />` 要素にリストされています。

Service Builderが `service.xml` ファイルを読み取ると、エンティティを定義できます。 [Liferay @IDE@](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ide) は、簡単にアプリケーションの中で実体を定義することを可能にする `service.xml` ファイルです。 次の手順を実行します：

1.  [`service.xml` ファイルを作成します。](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-service-xml-file)

2.  [サービスのグローバル情報を定義します。](/docs/7-1/tutorials/-/knowledge_base/t/defining-global-service-information)

3.  [サービスエンティティを定義します。](/docs/7-1/tutorials/-/knowledge_base/t/defining-service-entities)

4.  [各サービスエンティティの列（属性）を定義します。](/docs/7-1/tutorials/-/knowledge_base/t/defining-the-columns-attributes-for-each-service-entity)

5.  [エンティティ間の関係を定義します。](/docs/7-1/tutorials/-/knowledge_base/t/defining-relationships-between-service-entities)

6.  [データベースから取得するエンティティインスタンスのデフォルトの順序を定義します。](/docs/7-1/tutorials/-/knowledge_base/t/defining-ordering-of-service-entity-instances)

7.  [指定されたパラメーターに基づいてデータベースからオブジェクトを取得するファインダーメソッドを定義します。](/docs/7-1/tutorials/-/knowledge_base/t/defining-service-entity-finder-methods)

各ステップについて詳しく説明します。 `service.xml` ファイルの作成から始めます。
