# Applying the Add Button Pattern [](id=applying-the-add-button-pattern)

Clay's add button pattern is for actions that add entities (for example
a new blog entry button): it gives you a clean, minimal UI. You can use it in
any of your app's screens. It is primarily used in the 
[Management Bar](/develop/tutorials/-/knowledge_base/7-1/liferay-frontend-management-bar). 
The add button pattern consists of an 
[`add-menu` tag](@app-ref@/foundation/latest/taglibdocs/liferay-frontend/add-menu.html) 
and at least one [`add-menu-item` tag](@app-ref@/foundation/latest/taglibdocs/liferay-frontend/add-menu-item.html).

If there's only one item, the plus icon acts as a button that triggers the item. 
If there's more than one item, clicking the plus icon displays a menu containing 
them.

![Figure 1: The Add Menu lets you add entities from the Management Bar.](../../../images/clay-taglib-management-toolbar-creation-menu.png)

Add a `<liferay-frontend:add-menu-item>` tag for every menu item you have. 
Here's an example of the add button pattern with a single item:

    <liferay-frontend:add-menu>
        <liferay-frontend:add-menu-item title='<%= LanguageUtil.get(request,
        "titleName") %>' url="<%= nameURL.toString() %>" />
    </liferay-frontend:add-menu>

Following the Clay pattern, the add menu should be included in the Management 
Bar. This keeps the related UI inline with each other, keeping the app 
uncluttered. An example configuration is shown below:

        <liferay-frontend:management-bar-buttons>
            ...

            <c:if test="<%= fragmentDisplayContext.isShowAddButton(
              FragmentActionKeys.ADD_FRAGMENT_COLLECTION
              ) %>">
                <portlet:renderURL var="addFragmentCollectionURL">
                    <portlet:param name="mvcRenderCommandName" 
                    value="/fragment/edit_fragment_collection" />
                </portlet:renderURL>

                <liferay-frontend:add-menu inline="<%= true %>">
                    <liferay-frontend:add-menu-item 
                    title='<%= LanguageUtil.get(request, "add-collection") %>' 
                    url="<%= addFragmentCollectionURL.toString() %>" />
                </liferay-frontend:add-menu>
            </c:if>
        </liferay-frontend:management-bar-buttons>


There you have it! Now you know how to use the add button pattern. 

## Related Topics [](id=related-topics)

[Setting Search Container Animations](/develop/tutorials/-/knowledge_base/7-0/setting-search-container-animations)

[Adding the Management Bar](/develop/tutorials/-/knowledge_base/7-0/adding-the-management-bar)
