# Applying the Add Button Pattern [](id=applying-the-add-button-pattern)

If your app has any actions for adding entities (For example a new blog entry 
button), you may wish to use the add button pattern. This pattern gives you a 
clean, minimal UI for such a button. You can use it in any of your app's screens. 
The add button pattern consists of an [`add-menu` tag](@platform-ref@/7.0-latest/taglibs/modules/apps/foundation/com.liferay.frontend.taglib/com.liferay.frontend.taglib/liferay-frontend/add-menu.html) 
and at least one [`add-menu-item` tag](@platform-ref@/7.0-latest/taglibs/modules/apps/foundation/com.liferay.frontend.taglib/com.liferay.frontend.taglib/liferay-frontend/add-menu-item.html).

![Figure 1: The add button pattern consists of an `add-menu` tag and at least one `add-menu-item` tag.](../../../images/add-button-diagram.png)

If there's only one item, the plus icon acts as a button that triggers the item. 
If there's more than one item, clicking the plus icon displays a menu containing 
them.

Just add a `<liferay-frontend:add-menu-item>` tag for every menu item you have. 
Here's an example of the add button pattern with a single item:

    <liferay-frontend:add-menu>
        <liferay-frontend:add-menu-item title='<%= LanguageUtil.get(request,
        "titleName") %>' url="<%= nameURL.toString() %>" />
    </liferay-frontend:add-menu>

You can also find the add button pattern in Liferay's built-in apps. For 
example, the [Message Boards Admin application](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/collaboration/message-boards/message-boards-web/src/main/resources/META-INF/resources/message_boards_admin/add_button.jsp)
uses the following add button pattern:

    <liferay-frontend:add-menu>
        ...
        <liferay-frontend:add-menu-item title='<%= LanguageUtil.get(request,
        "thread") %>' url="<%= addMessageURL.toString() %>" />
        ...
        <liferay-frontend:add-menu-item title='<%= LanguageUtil.get(request,
        (categoryId == MBCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) ?
        "category[message-board]" : "subcategory[message-board]") %>'
        url="<%= addCategoryURL.toString() %>" />
        ...
    </liferay-frontend:add-menu>

There you have it! Now you know how to use the add button pattern

## Related Topics

[Setting Search Container Animations](/develop/tutorials/-/knowledge_base/7-0/setting-search-container-animations)

[Adding the Management Bar](/develop/tutorials/-/knowledge_base/7-0/adding-the-management-bar)