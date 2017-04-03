# Migrating the Add Guestbook Action [](id=migrating-the-add-guestbook-action)

The action to add a guestbook is currently found through the add menu in the 
Management Bar. To comply with the Lexicon design guidelines, and to improve the 
overall user-experience for the portlet, you'll move this action to an Add menu.

Go ahead and Get started.

## Adding the Add Menu [](id=adding-the-add-menu)

Follow these steps to create the Add menu:

1.  Open the `view.jsp` and replace the `<aui:button-row>` tags with 
    `<liferay-frontend:add-menu>` tags to match the configuration below:

        <liferay-frontend:add-menu>
            <c:if test='<%= GuestbookModelPermission.contains(permissionChecker,
                     scopeGroupId, "ADD_GUESTBOOK") %>'>
                        <portlet:renderURL var="addGuestbookURL">
                                <portlet:param name="mvcPath"
                                        value="/html/guestbookadminmvcportlet/edit_guestbook.jsp" />
                                <portlet:param name="redirect" value="${currentURL}" />
                        </portlet:renderURL>

                        <aui:button onClick="<%= addGuestbookURL.toString() %>" 
                        value="Add Guestbook" />
            </c:if>
        </liferay-frontend:add-menu>
        
2.  Replace the `<aui:button>` tag and contents with the 
    `<liferay-frontend:add-menu-item>` tag below, since this functionality 
    is now in the Add Menu:
  
        <liferay-frontend:add-menu-item title="Add Guestbook" 
        url="<%= addGuestbookURL.toString() %>" />
        
The Add Menu is complete! Your updated Admin portlet should match the figure 
below:

![Figure 1: The Add Menu keeps the UI clean and clear.](../../../images/admin-app-completed.png)

The Guestbook portlet and Guestbook Admin portlet UIs are updated! You can 
compare your code to the finished Guestbook Admin app code found [here](https://github.com/liferay/liferay-docs/tree/master/develop/learning-paths/mvc/code/guestbook-ui-complete/guestbook).
