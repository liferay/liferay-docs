# Liferay UI Icon Lists [](id=liferay-ui-icon-lists)

An icon list displays icons in a horizontal list, instead of in a pop-up
navigation menu like an 
[icon menu](/develop/tutorials/-/knowledge_base/7-1/liferay-ui-icon-menu). You
can see an example of an icon list menu in a message board thread. The thread's
actions are visible at all times for administrators:

![Figure 1: Icon lists display an app's actions at all times.](../../../images/liferay-ui-taglib-icon-list.png)

Create the list menu with the `liferay-ui:icon-list` tag and nest 
[icons](/develop/tutorials/-/knowledge_base/7-1/liferay-ui-icons) 
for each list item, as shown below:

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

See the 
[Icon List taglibdocs](@platform-ref@/7.1-latest/taglibs/util-taglib/liferay-ui/icon-list.html) 
for the full list of available attributes.

## Related Topics [](id=related-topics)

[Clay Icons](/develop/tutorials/-/knowledge_base/7-1/clay-icons)

[Liferay UI Icon Menus](/develop/tutorials/-/knowledge_base/7-1/liferay-ui-icon-menus)

[Liferay UI Icons](/develop/tutorials/-/knowledge_base/7-1/liferay-ui-icons)
