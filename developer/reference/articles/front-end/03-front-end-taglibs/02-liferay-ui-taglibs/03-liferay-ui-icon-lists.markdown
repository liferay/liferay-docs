---
header-id: liferay-ui-icon-lists
---

# Liferay UI Icon Lists

[TOC levels=1-4]

An icon list displays icons in a horizontal list, instead of in a pop-up 
navigation menu like an 
[icon menu](/docs/7-2/reference/-/knowledge_base/r/liferay-ui-icon-menus). You 
can see an example of an icon list menu in a message board thread. The thread's 
actions are visible at all times for administrators:

![Figure 1: Icon lists display an app's actions at all times.](../../../../images/liferay-ui-taglib-icon-list.png)

Create the list menu with the `liferay-ui:icon-list` tag and nest 
[icons](/docs/7-2/reference/-/knowledge_base/r/liferay-ui-icons) 
for each list item, as shown below:

```html
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
```

See the 
[Icon List taglibdocs](@platform-ref@/7.2-latest/taglibs/util-taglib/liferay-ui/icon-list.html) 
for the full list of available attributes.

## Related Topics

- [Clay Icons](/docs/7-2/reference/-/knowledge_base/r/clay-icons)
- [Liferay UI Icon Menus](/docs/7-2/reference/-/knowledge_base/r/liferay-ui-icon-menus)
- [Liferay UI Icons](/docs/7-2/reference/-/knowledge_base/r/liferay-ui-icons)
