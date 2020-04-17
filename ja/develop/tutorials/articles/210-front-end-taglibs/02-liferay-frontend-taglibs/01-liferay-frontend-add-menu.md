---
header-id: liferay-frontend-add-menu
---

# Liferayフロントエンドメニューの追加

[TOC levels=1-4]

メニューの追加タグは、1つまたは複数のアイテムにメニューの追加ボタンを作成します。これは、エンティティ（新しいブログエントリなど）を追加するアクションに使用され、管理バーの一部です。`<liferay-frontend:add-menu>`タグを使用してメニューの追加を作成し、アイテムごとに`<liferay-frontend:add-menu-item>`タグをネストします。

**注：**このパターンは、@product-ver@では推奨されていません。代わりに、Clay管理ツールバーの[作成メニューパターン](/docs/7-1/tutorials/-/knowledge_base/t/clay-management-toolbar#creation-menu)を使用することを推奨しています。

メニューに1つの項目がある場合、ブログ管理アプリの以下の例に示すように、ボタンは項目のアクションをトリガーします。

    <liferay-frontend:management-bar-buttons>
    ...
    <liferay-frontend:add-menu
    inline="<%= true %>"
    >
    <liferay-frontend:add-menu-item
    title='<%= LanguageUtil.get(request, "add-blog-entry") %>'
    url="<%= addEntryURL %>"
    />
    </liferay-frontend:add-menu>
    
    </liferay-frontend:management-bar-buttons>

![図1：追加ボタンのパターンは、`add-menu`タグと少なくとも1つの`add-menu-item` タグで構成されます。](../../../images/liferay-frontend-taglib-add-menu-one-item.png)

メニューに複数の項目がある場合、それらはポップアップメニューに表示されます。たとえば、Message Boards Adminアプリケーションの構成は次のとおりです。

    <liferay-frontend:add-menu>
    ...
    <liferay-frontend:add-menu-item title='<%= LanguageUtil.get(request,
    "thread") %>' url="<%= addMessageURL.toString() %>" />
    ...
    <liferay-frontend:add-menu-item title='<%= LanguageUtil.get(request,
    (categoryId == MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) ?
    "category[message-board]" : "subcategory[message-board]") %>'
    url="<%= addCategoryURL.toString() %>" />
    ...
    </liferay-frontend:add-menu>

![図2：追加ボタンのパターンは、`add-menu`タグと少なくとも1つの`add-menu-item` タグで構成されます。](../../../images/liferay-frontend-taglib-add-menu-items.png)

上記の例では、使用可能な属性の一部を使用しています。タグに使用可能な属性の全リストについては、[add menu](@app-ref@/foundation/latest/taglibdocs/liferay-frontend/add-menu.html)および[add menu item](@app-ref@/foundation/latest/taglibdocs/liferay-frontend/add-menu-item.html)taglibdocsを参照してください。

## 関連トピック

[Liferay Frontend Cards](/docs/7-1/tutorials/-/knowledge_base/t/liferay-frontend-cards)

[Liferay Frontend Info Bar](/docs/7-1/tutorials/-/knowledge_base/t/liferay-frontend-info-bar)

[Liferay Frontend Management Bar](/docs/7-1/tutorials/-/knowledge_base/t/liferay-frontend-management-bar)
