---
header-id: applying-the-add-button-pattern
---

# Applying the Add Button Pattern

[TOC levels=1-4]

Clay's add button pattern is for actions that add entities (for example 
a new blog entry button). It gives you a clean, minimal UI. You can use it in 
any of your app's screens. Follow these steps to add a plus button to your app:

1.  Add the `liferay-frontend` taglib declaration to your portlet's `init.jsp`:

    ```markup
    <%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>
    ```

2.  Add an [`add-menu` tag](@app-ref@/frontend-taglib/latest/taglibdocs/liferay-frontend/add-menu.html) 
    to your portlet's view:

    ```markup
    <liferay-frontend:add-menu>
    </liferay-frontend:add-menu>
    ```

3.  Nest a [`<liferay-frontend:add-menu-item>`](@app-ref@/frontend-taglib/latest/taglibdocs/liferay-frontend/add-menu-item.html) 
    tag for every menu item you have. Here's an example of the add button 
    pattern with a single item:

    ```markup
    <liferay-frontend:add-menu>
        <liferay-frontend:add-menu-item 
          title='<%= LanguageUtil.get(request,"titleName") %>' 
          url="<%= nameURL.toString() %>" 
        />
    </liferay-frontend:add-menu>
    ```

    If there's only one item, the plus icon acts as a button that triggers the 
    item. If there's multiple items, clicking the plus icon displays a menu 
    containing them. 

    ![Figure 1: The add button pattern consists of an `add-menu` tag and at least one `add-menu-item` tag.](../../../../images/add-button-diagram.png)

The `com.liferay.mobile.device.rules.web` module's add menu is shown below:

```markup
<liferay-frontend:add-menu
  inline="<%= true %>"
>
  <liferay-frontend:add-menu-item
    title='<%= LanguageUtil.get(resourceBundle, "add-device-family") %>'
    url="<%= addRuleGroupURL %>"
  />
</liferay-frontend:add-menu>
```

There you have it! Now you know how to use the add button pattern. 

## Related Topics

- [Setting Empty Results Messages](/docs/7-2/frameworks/-/knowledge_base/f/setting-empty-results-messages)
- [Implementing the Management Toolbar](/docs/7-2/frameworks/-/knowledge_base/f/implementing-the-management-toolbar)
