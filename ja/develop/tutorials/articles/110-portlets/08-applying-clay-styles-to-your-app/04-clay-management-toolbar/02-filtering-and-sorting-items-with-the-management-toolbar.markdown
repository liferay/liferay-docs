---
header-id: filtering-and-sorting-items-with-the-management-toolbar
---

# 管理ツールバーを使用したアイテムのフィルタリングと並べ替え

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

管理ツールバーを使用すると、検索コンテナの結果をフィルタリングおよびソートできます。 JSPでツールバーのフィルターを構成できますが、これによりJSPがすぐに混雑する可能性があります。 代わりに、この機能を別のJavaクラスに移動することをお勧めします。このクラスは、このチュートリアル全体を通して表示コンテキストと呼ばれます。

フィルターには、主にナビゲーションと順序の2つのタイプがあります。 これらは両方とも同じドロップダウンメニューに含まれています。 以下の手順に従って、フィルターを作成します。

1.  ニーズに応じて、管理ツールバーの表示コンテキスト用に拡張できる2つのクラスがあります。 これらの基本クラスは、ナビゲーションおよび順序フィルターを作成するために必要なメソッドを提供します。

      - [`BaseManagementToolbarDisplayContext`](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/frontend-taglib/frontend-taglib-clay/src/main/java/com/liferay/frontend/taglib/clay/servlet/taglib/display/context/BaseManagementToolbarDisplayContext.java)：検索コンテナーのないアプリの場合
      - [`SearchContainerManagementToolbarDisplayContext`](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/frontend-taglib/frontend-taglib-clay/src/main/java/com/liferay/frontend/taglib/clay/servlet/taglib/display/context/SearchContainerManagementToolbarDisplayContext.java)：検索コンテナーを備えたアプリの場合（ `BaseManagementToolbarDisplayContext` を拡張し、検索コンテナーの追加ロジックを提供）

    それぞれの構成例を以下に示します。

    `BaseManagementToolbarDisplayContext` 例：

        パブリッククラスMyManagementToolbarDisplayContext はBaseManagementToolbarDisplayContextを拡張します{

          public MyManagementToolbarDisplayContext（ LiferayPortletRequest liferayPortletRequest、 LiferayPortletResponse liferayPortletResponse、 HttpServletRequest request）{
        
            super(liferayPortletRequest, liferayPortletResponse, request);
          } ... }

    `SearchContainerManagementToolbarDisplayContext` 例：

        パブリッククラスMyManagementToolbarDisplayContext はSearchContainerManagementToolbarDisplayContextを拡張します{

          public MyManagementToolbarDisplayContext（ LiferayPortletRequest liferayPortletRequest、 LiferayPortletResponse liferayPortletResponse、 HttpServletRequest request、SearchContainer searchContainer）{
        
            super(
              liferayPortletRequest, liferayPortletResponse, request,
              searchContainer);
          } }

2.  `getNavigationKeys（）` メソッドをオーバーライドして、ナビゲーションフィルタードロップダウンアイテムを返します。 アプリにナビゲーションフィルターが不要な場合は、 *all* フィルターを提供してすべてを表示できます。 構成例を以下に示します。

        @Override protected String [] getNavigationKeys（）{ new String [] {"all"、 "pending"、 "done"};を返します。 }

3.  `getOrderByKeys（）` メソッドをオーバーライドして、順序付けする列を返します。 構成例を以下に示します。

        @Override protected String [] getOrderByKeys（）{ 新しいString [] {"name"、 "items"、 "status"}を返します; }

4.  Clay Management Toolbarを含むJSPビューを開き、その `displayContext` 属性を作成したディスプレイコンテキストに設定します。 構成例を以下に示します。

        <clay:management-toolbar displayContext="<%= myManagementToolbarDisplayContext %>" />

これで、表示コンテキストを使用して管理ツールバーのフィルターを構成する方法がわかりました。

## 関連トピック

[管理ツールバー属性のフィルタリングとソートの構成](/docs/7-1/tutorials/-/knowledge_base/t/clay-management-toolbar#filtering-and-sorting-search-results)

[ビュータイプの実装](/docs/7-1/tutorials/-/knowledge_base/t/implementing-the-view-types)
