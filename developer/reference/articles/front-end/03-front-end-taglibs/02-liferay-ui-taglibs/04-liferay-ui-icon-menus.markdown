---
header-id: liferay-ui-icon-menus
---

# Liferay UI Icon Menus

[TOC levels=1-4]

You can add a pop-up navigation menu to your app with the `liferay-ui:icon-menu` 
tag. Icon menus display menu options when needed, storing them away in 
a collapsed menu when they're not. This keeps the UI clean and uncluttered. Just 
as with an icon list, you nest 
[icons](/docs/7-2/reference/-/knowledge_base/r/liferay-ui-icons) for each 
navigation item. You can see an example of a icon menu in a site's actions menu 
in the My Sites portlet:

![Figure 1: Setting up an icon menu is a piece of cake.](../../../../images/liferay-ui-taglib-icon-menu.png)

Example JSP configuration:

```html
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
```

Note that the `url` attribute is required for icons to render properly. See the 
[Icon Menu taglibdocs](@platform-ref@/7.2-latest/taglibs/util-taglib/liferay-ui/icon-menu.html) 
for the full list of attributes. 

## Related Topics

- [Clay Icons](/docs/7-2/reference/-/knowledge_base/r/clay-icons)
- [Liferay UI Icon Lists](/docs/7-2/reference/-/knowledge_base/r/liferay-ui-icon-lists)
- [Liferay UI Icons](/docs/7-2/reference/-/knowledge_base/r/liferay-ui-icons)
