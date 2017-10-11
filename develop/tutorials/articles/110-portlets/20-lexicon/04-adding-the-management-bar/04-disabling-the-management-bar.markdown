# Disabling the Management Bar

When there's no content in the app, the Management Bar disables all the buttons 
except the info button.

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

[Implementing a Management Bar Sort Filter](/develop/tutorials/-/knowledge_base/7-0/implementing-a-management-bar-sort-filter)

[Setting Search Container Animations](/develop/tutorials/-/knowledge_base/7-0//develop/tutorials/-/knowledge_base/7-0/setting-search-container-animations)