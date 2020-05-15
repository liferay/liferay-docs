---
header-id: liferay-ui-icon-lists
---

# Liferay UIアイコンリスト

[TOC levels=1-4]

アイコンリストは、[アイコンメニュー](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ui-icon-menus)ようなポップアップナビゲーションメニューではなく、水平リストに アイコンを表示します。メッセージボードスレッドのアイコンリストメニューの例を以下に示します。スレッドのアクションは、管理者に対して常に表示されます。

![図1：アイコンリストには、アプリのアクションが常に表示されます。](../../../images/liferay-ui-taglib-icon-list.png)

以下に示すように、リスト項目ごとに`liferay-ui:icon-list`タグを使用してリストメニューを作成し、[アイコン](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ui-icons)をネストします。

    <div class="thread-actions">
    <liferay-ui:icon-list>
    
    <liferay-ui:icon
    iconCssClass="icon-lock"
    message="permissions"
    method="get"
    url="<%= permissionsURL %>"
    useDialog="<%= true %>"
    />
    
    <liferay-rss:rss
    delta="<%= rssDelta %>"
    displayStyle="<%= rssDisplayStyle %>"
    feedType="<%= rssFeedType %>"
    url="<%= MBRSSUtil.getRSSURL(plid, 0, message.getThreadId(), 0, themeDisplay) %>"
    />
    
    <liferay-ui:icon
    iconCssClass="icon-remove-sign"
    message="unsubscribe"
    url="<%= unsubscribeURL %>"
    />
    
    <liferay-ui:icon
    iconCssClass="icon-lock"
    message="lock"
    url="<%= lockThreadURL %>"
    />
    
    <liferay-ui:icon
    iconCssClass="icon-move"
    message="move"
    url="<%= editThreadURL %>"
    />
    
    <liferay-ui:icon-delete
    showIcon="<%= true %>"
    trash="<%= trashHelper.isTrashEnabled(themeDisplay.getScopeGroupId()) %>"
    url="<%= deleteURL %>"
    />
    </liferay-ui:icon-list>
    </div>

使用可能な属性の全リストについては、[Icon List taglibdocs](@platform-ref@/7.1-latest/taglibs/util-taglib/liferay-ui/icon-list.html)を参照してください。

## 関連トピック

[Clay Icons](/docs/7-1/tutorials/-/knowledge_base/t/clay-icons)

[Liferay UI Icon Menus](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ui-icon-menus)

[Liferay UI Icons](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ui-icons)
