# Liferay UI Icon Menus [](id=liferay-ui-icon-menus)

You can add a pop-up navigation menu to your app with the `liferay-ui:icon-menu` 
tag. Icon menus are similar to icon lists, except icons are displayed in an icon 
menu, as opposed to a horizontal list. Nest 
[icons](/develop/tutorials/-/knowledge_base/7-1/liferay-ui-icons) 
for each navigation item. You can see an example of a icon menu in the actions 
menu of a site in the My Sites portlet:

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

Icons must have their `url` attribute configured to render properly. The list 
below gives some of the available attributes along with how to configure them. 
See the 
[Icon Menu taglibdocs](@platform-ref@/7.1-latest/taglibs/util-taglib/liferay-ui/icon-menu.html) 
for the full list of attributes. 

**direction:** A position from which to pop up the icon menu dialog in relation 
to the trigger. The default direction is `left`. Possible values are `up`, 
`down`, `left` or `right`.

**maxDisplayItems:** The number of items to show in the icon menu before 
scrolling. The default value is `15`.

**message:** Text to display next to the icon menu trigger. The default value is 
`actions`.

**showArrow:** Whether to show an arrow, indicating a popup dialog, next to the 
icon menu's trigger icon. The default value is `true`.

**useIconCaret:** Whether the icon menu drop-down caret points to the direction 
set by the `direction` attribute. The default value is `false`.

## Related Topics [](id=related-topics)

[Clay Icons](/develop/tutorials/-/knowledge_base/7-1/clay-icons)

[Liferay UI Icon Lists](/develop/tutorials/-/knowledge_base/7-1/liferay-ui-icon-lists)

[Liferay UI Icons](/develop/tutorials/-/knowledge_base/7-1/liferay-ui-icons)
