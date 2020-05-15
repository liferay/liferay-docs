---
header-id: disabling-all-or-portions-of-the-management-bar
---

# 管理バーのすべてまたは一部を無効にする

[TOC levels=1-4]

表示する検索結果がない場合は、sidenavトグラーボタンを除くすべての管理バーのボタンを無効にする必要があります。

`disabled`属性を`liferay-frontend:management-bar`タグに追加することで、管理バーを無効にできます。

    <liferay-frontend:management-bar
    disabled="<%= total == 0 %>"
    includeCheckBox="<%= true %>"
    searchContainerId="<%= searchContainerId %>"
    >

また、`disabled`属性を対応するタグに追加して、個々のコンポーネントを無効にすることもできます。次の例では、表示する結果がない場合は表示スタイルを変更しても効果がないので、検索コンテナに表示される結果が0個の場合に表示ボタンが無効になります。

    <liferay-frontend:management-bar-display-buttons
    disabled="<%= total == 0 %>"
    displayViews='<%= new String[] {"descriptive", "icon", "list"} %>'
    portletURL="<%= changeDisplayStyleURL %>"
    selectedDisplayStyle="<%= displayStyle %>"
    />

![図1：管理バーのすべてまたは一部を無効にできます。](../../../../images/liferay-frontend-taglib-management-bar-disabled.png)

## 関連トピック

[Including Actions in the Management Bar](/docs/7-1/tutorials/-/knowledge_base/t/including-actions-in-the-management-bar)

[Clay Management Toolbar](/docs/7-1/tutorials/-/knowledge_base/t/clay-management-toolbar)
