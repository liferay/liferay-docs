---
header-id: disabling-the-management-bar
---

# Disabling the Management Bar

[TOC levels=1-4]

When there's no content in the app, you should disable all the Management Bar's 
buttons, except the info button. 

You can disable the Management Bar by adding the `disabled` attribute to the 
`liferay-frontend:management-bar` tag:

    <liferay-frontend:management-bar
            disabled="<%= total == 0 %>"
            includeCheckBox="<%= true %>"
            searchContainerId="<%= searchContainerId %>"
    >

You can also disable individual buttons by adding the `disabled` attribute to 
the corresponding tag. The example configuration below disables the display 
buttons when the search container displays 0 results:

    <liferay-frontend:management-bar-display-buttons
            disabled="<%= total == 0 %>"
            displayViews='<%= new String[] {"descriptive", "icon", "list"} %>'
            portletURL="<%= changeDisplayStyleURL %>"
            selectedDisplayStyle="<%= trashDisplayContext.getDisplayStyle() %>"
    />
    
Now you know how to disable the Management Bar!

## Related Topics

[Implementing a Management Bar Sort Filter](/docs/7-0/tutorials/-/knowledge_base/t/implementing-a-management-bar-sort-filter)

[Setting Search Container Animations](/docs/7-0/tutorials/-/knowledge_base/t/setting-search-container-animations)
