---
header-id: liferay-frontend-management-bar
---

# Liferayフロントエンド管理バー

[TOC levels=1-4]

管理バーを使用することで、管理者は検索コンテナの結果を管理できます。
検索結果の表示スタイルをフィルター処理、並べ替え、選択できるため、ドキュメント、Webコンテンツ、アセットエントリなど、アプリで探しているものをすばやく特定できます。管理バーは完全にカスタマイズ可能であるため、すべてのコントロールを実装することも、アプリに必要なコントロールのみを実装することもできます。

![図1：管理バーを使用して、ユーザーはアプリでコンテンツを表示する方法をカスタマイズできます。](../../../../images/liferay-frontend-taglib-management-bar-message-boards.png)

 **注：**Liferayフロントエンド管理バーは、@product-ver@では推奨されていません。
代わりに、[Clay Management Toolbar](/docs/7-1/tutorials/-/knowledge_base/t/clay-management-toolbar)を使用することを推奨しています。

管理バーにはいくつかの重要なセクションがあります。各セクションは、異なるtaglibを使用してグループ化されて構成されます。

[`<liferay-frontend:management-bar-buttons>`タグ](@app-ref@/foundation/latest/taglibdocs/liferay-frontend/management-bar-buttons.html)は、管理バーのボタン要素をラップします：

![図2：`management-bar-buttons`タグには、管理バーのメインボタンが含まれています。](../../../../images/liferay-frontend-taglib-management-bar-buttons.png)

[`<liferay-frontend:management-bar-sidenav-toggler-button>`タグ](@app-ref@/foundation/latest/taglibdocs/liferay-frontend/management-bar-sidenav-toggler-button.html)は、情報ボタンのスライドアウトナビゲーションを実装します。

[`<liferay-frontend:management-bar-display-buttons>`タグ](@app-ref@/foundation/latest/taglibdocs/liferay-frontend/management-bar-display-buttons.html)は、アプリの表示スタイルオプションをレンダリングします。

![図3：`management-bar-display-buttons`タグにはコンテンツの表示オプションが含まれています。](../../../../images/liferay-frontend-taglib-management-bar-display-buttons.png)

[`<liferay-frontend:management-bar-filters>`タグ](@app-ref@/foundation/latest/taglibdocs/liferay-frontend/management-bar-filters.html)は、アプリのフィルタリングオプションをラップします。このフィルターは、すべてのコントロールパネルアプリケーションに含める必要があります。フィルターオプションには、並べ替え条件、並べ替え順序などを含めることができます。

![図4：`management-bar-filters`タグにはコンテンツフィルタリングオプションが含まれています。](../../../../images/liferay-frontend-taglib-management-bar-filters.png)

最後に、[`<liferay-frontend:management-bar-action-buttons>`タグ](@app-ref@/foundation/latest/taglibdocs/liferay-frontend/management-bar-action-buttons.html)は、選択した項目に対して実行できるアクションをラップします。ページ間で複数の項目を選択できます。管理バーは、選択された項目の数を追跡します。

![図5：管理バーは、選択された項目を追跡し、それらに対して実行するアクションを表示します。](../../../../images/liferay-frontend-taglib-management-bar-action-buttons.png)

たとえば、ゴミ箱アプリの管理バーの構成は次のとおりです。

    <liferay-frontend:management-bar
    includeCheckBox="<%= true %>"
    searchContainerId="trash"
    >
    <liferay-frontend:management-bar-buttons>
    <liferay-frontend:management-bar-sidenav-toggler-button />
    
    <liferay-portlet:actionURL name="changeDisplayStyle"
    varImpl="changeDisplayStyleURL">
    <portlet:param name="redirect" value="<%= currentURL %>" />
    </liferay-portlet:actionURL>
    
    <liferay-frontend:management-bar-display-buttons
    displayViews='<%= new String[] {"descriptive", "icon",
    "list"} %>'
    portletURL="<%= changeDisplayStyleURL %>"
    selectedDisplayStyle="<%= trashDisplayContext.getDisplayStyle()
    %>"
    />
    </liferay-frontend:management-bar-buttons>
    
    <liferay-frontend:management-bar-filters>
    <liferay-frontend:management-bar-navigation
    navigationKeys='<%= new String[] {"all"} %>'
    portletURL="<%= trashDisplayContext.getPortletURL() %>"
    />
    
    <liferay-frontend:management-bar-sort
    orderByCol="<%= trashDisplayContext.getOrderByCol() %>"
    orderByType="<%= trashDisplayContext.getOrderByType() %>"
    orderColumns='<%= new String[] {"removed-date"} %>'
    portletURL="<%= trashDisplayContext.getPortletURL() %>"
    />
    </liferay-frontend:management-bar-filters>
    
    <liferay-frontend:management-bar-action-buttons>
    <liferay-frontend:management-bar-sidenav-toggler-button />
    
    <liferay-frontend:management-bar-button href="javascript:;"
    icon="trash" id="deleteSelectedEntries" label="delete" />
    </liferay-frontend:management-bar-action-buttons>
    </liferay-frontend:management-bar>

