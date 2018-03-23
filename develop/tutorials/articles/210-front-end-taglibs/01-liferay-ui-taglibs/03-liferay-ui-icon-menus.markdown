# Liferay UI Icon Menus [](id=liferay-ui-icon-menus)

You can add a pop-up navigation menu to your app with the `liferay-ui:icon-menu`
tag. Icon menus display menu options when needed, storing them away in
a collapsed menu when they're not. This keeps the UI clean and uncluttered. Just
as with an icon list, you nest
[icons](/develop/tutorials/-/knowledge_base/7-1/liferay-ui-icons) for each
navigation item. You can see an example of a icon menu in a site's actions menu
in the My Sites portlet:

![Figure 1: Setting up an icon menu is a piece of cake.](../../../images/liferay-ui-taglib-icon-menu.png)

Example JSP configuration:

    <liferay-ui:icon-menu
        direction="left-side"
        icon="<%= StringPool.BLANK %>"
        markupView="lexicon"
        message="<%= StringPool.BLANK %>"
        showWhenSingleIcon="<%= true %>"
    >

    				<liferay-ui:icon
    					message="go-to-public-pages"
    					target="_blank"
    					url="<%= group.getDisplayURL(themeDisplay, false) %>"
    				/>

    				<liferay-ui:icon
    					message="leave"
    					url="<%= leaveURL %>"
    				/>

    </liferay-ui:icon-menu>

Note that the `url` attribute is required for icons to render properly. See the 
[Icon Menu taglibdocs](@platform-ref@/7.1-latest/taglibs/util-taglib/liferay-ui/icon-menu.html) 
for the full list of attributes. 

## Related Topics [](id=related-topics)

[Clay Icons](/develop/tutorials/-/knowledge_base/7-1/clay-icons)

[Liferay UI Icon Lists](/develop/tutorials/-/knowledge_base/7-1/liferay-ui-icon-lists)

[Liferay UI Icons](/develop/tutorials/-/knowledge_base/7-1/liferay-ui-icons)
