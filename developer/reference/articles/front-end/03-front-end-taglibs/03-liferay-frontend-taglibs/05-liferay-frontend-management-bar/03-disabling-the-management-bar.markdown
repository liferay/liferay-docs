---
header-id: disabling-all-or-portions-of-the-management-bar
---

# Disabling All or Portions of the Management Bar [](id=disabling-all-or-portions-of-the-management-bar)

[TOC levels=1-4]

When there are no search results to display, you should disable all the 
Management Bar's buttons, except the sidenav toggler button. 

You can disable the Management Bar by adding the `disabled` attribute to the 
`liferay-frontend:management-bar` tag:

```html
<liferay-frontend:management-bar
        disabled="<%= total == 0 %>"
        includeCheckBox="<%= true %>"
        searchContainerId="<%= searchContainerId %>"
>
```

You can also disable individual components by adding the `disabled` attribute to 
the corresponding tag. The example below disables the display buttons when the 
search container displays 0 results, since changing the display style has no 
effect when there aren't any results to view:

```html
<liferay-frontend:management-bar-display-buttons
        disabled="<%= total == 0 %>"
        displayViews='<%= new String[] {"descriptive", "icon", "list"} %>'
        portletURL="<%= changeDisplayStyleURL %>"
        selectedDisplayStyle="<%= displayStyle %>"
/>
```

![Figure 1: You can disable all or portions of the Management Bar.](../../../../../images/liferay-frontend-taglib-management-bar-disabled.png)

## Related Topics

- [Including Actions in the Management Bar](/docs/7-2/frameworks/-/knowledge_base/f/including-actions-in-the-management-bar)
- [Clay Management Toolbar](/docs/7-2/reference/-/knowledge_base/r/clay-management-toolbar)
