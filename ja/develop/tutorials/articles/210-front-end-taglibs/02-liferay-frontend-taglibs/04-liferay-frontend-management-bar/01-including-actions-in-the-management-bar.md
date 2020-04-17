---
header-id: including-actions-in-the-management-bar
---

# 管理バーにアクションを含める

[TOC levels=1-4]

通常、アクションメニューは各検索コンテナの結果に含まれていますが、これらのアクションを管理バーに含めることもできます。これにより、すべてが同じUI内に整理されます。このアップデートでは、各検索コンテナの結果の横にチェックボックスが追加され、すべての結果を選択するためのチェックボックスが管理バー自体に追加されます。アクションは、チェックボックスがオンになっている場合（個別またはすべて選択）に表示され、それ以外の場合は非表示になります。

![図1：個別の結果またはすべての結果を一度に選択できます。](../../../../images/liferay-frontend-taglib-management-bar-include-checkbox.png)

管理バーにアクションを含めるには、次の手順に従います。

1. `<liferay-frontend:management-bar>`タグを更新してチェックボックスを含め、検索コンテナのIDを指定します。

       <liferay-frontend:management-bar
       includeCheckBox="<%= true %>"
       searchContainerId="mySearchContainerId"
       >
   
2. 終了タグ`</liferay-frontend:management-bar-filters>` の後に、`<liferay-frontend:management-bar-action-buttons>`タグを追加します。

       <liferay-frontend:management-bar-action-buttons>
       
       </liferay-frontend:management-bar-action-buttons>
   
3. 使用可能な管理バーボタンのtaglib（例：`management-bar-button`）を使用して、アプリの管理バーのアクションボタンを作成します。サイト管理ポートレットのコードスニペットを以下に示します。

       <liferay-frontend:management-bar-action-buttons>
       <liferay-frontend:management-bar-sidenav-toggler-button
       icon="info-circle"
       label="info"
       />
       
       <liferay-frontend:management-bar-button
       href="javascript:deleteEntries();"
       icon="trash"
       id="deleteSites"
       label="delete"
       />
       </liferay-frontend:management-bar-action-buttons>
   
![図2：アプリで必要な数のアクションを含めることができます。](../../../../images/liferay-frontend-taglib-management-bar-actions.png)

## 関連トピック

[Disabling All or Portions of the Management Bar](/docs/7-1/tutorials/-/knowledge_base/t/disabling-all-or-portions-of-the-management-bar)

[Clay Management Toolbar](/docs/7-1/tutorials/-/knowledge_base/t/clay-management-toolbar)
