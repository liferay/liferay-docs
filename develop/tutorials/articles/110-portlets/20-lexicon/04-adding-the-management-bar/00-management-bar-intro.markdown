# Adding the Management Bar [](id=adding-the-management-bar)

The Management Bar lets users configure display options for search container 
results. The Management Bar lets you display content in a list or a grid, or 
display only a specific type of content. You can also customize your app's 
Management Bar. 

![Figure 1: The Management Bar lets the user customize how the app displays content.](../../../images/message-boards-management-bar.png)

The Management Bar is divided into a few key sections. Each section is grouped 
and configured using different taglibs:

The [`<liferay-frontend:management-bar-buttons>` tag](@platform-ref@/7.0-latest/taglibs/modules/apps/foundation/com.liferay.frontend.taglib/com.liferay.frontend.taglib/liferay-frontend/management-bar-buttons.html) 
wraps the Management Bar's button elements:

![Figure 2: The `management-bar-buttons` tag contains the Management Bar's main buttons.](../../../images/management-bar-buttons.png)

The [`<liferay-frontend:management-bar-sidenav-toggler-button>` tag](@platform-ref@/7.0-latest/taglibs/modules/apps/foundation/com.liferay.frontend.taglib/com.liferay.frontend.taglib/liferay-frontend/management-bar-sidenav-toggler-button.html) 
implements slide-out navigation for the info button.

The [`<liferay-frontend:management-bar-display-buttons>` tag](@platform-ref@/7.0-latest/taglibs/modules/apps/foundation/com.liferay.frontend.taglib/com.liferay.frontend.taglib/liferay-frontend/management-bar-display-buttons.html) 
renders the app's display style options:

![Figure 3: The `management-bar-display-buttons` tag contains the content's display options.](../../../images/management-bar-display-buttons.png)

The [`<liferay-frontend:management-bar-filters>` tag](@platform-ref@/7.0-latest/taglibs/modules/apps/foundation/com.liferay.frontend.taglib/com.liferay.frontend.taglib/liferay-frontend/management-bar-filters.html) 
wraps the app's filtering options. This filter should be included in all control 
panel applications. Filtering options can include sort criteria, sort ordering, 
and more:

![Figure 4: The `management-bar-filters` tag contains the content filtering options.](../../../images/management-bar-filters.png)

Finally, the [`<liferay-frontend:management-bar-action-buttons>` tag](@platform-ref@/7.0-latest/taglibs/modules/apps/foundation/com.liferay.frontend.taglib/com.liferay.frontend.taglib/liferay-frontend/management-bar-action-buttons.html) 
wraps the actions that you can execute over selected items. In @product-ver@, 
you can select multiple items between pages. The management bar keeps track of 
the number of selected items for you:

![Figure 5: The management bar keeps track of the items selected and displays the actions to execute on them.](../../../images/management-bar-action-buttons.png)

For example, here's the Management Bar configuration in Liferay's Trash app:

   <liferay-frontend:management-bar
       includeCheckBox="<%= true %>"
       searchContainerId="trash"
   >
       <liferay-frontend:management-bar-buttons>
           <liferay-frontend:management-bar-sidenav-toggler-button />

           <liferay-portlet:actionURL name="changeDisplayStyle"
           varImpl="changeDisplayStyleURL">
               <portlet:param name="redirect" value="<%= currentURL %>" />
           </liferay-portlet:actionURL>

           <liferay-frontend:management-bar-display-buttons
               displayViews='<%= new String[] {"descriptive", "icon",
               "list"} %>'
               portletURL="<%= changeDisplayStyleURL %>"
               selectedDisplayStyle="<%= trashDisplayContext.getDisplayStyle()
               %>"
           />
       </liferay-frontend:management-bar-buttons>

       <liferay-frontend:management-bar-filters>
           <liferay-frontend:management-bar-navigation
               navigationKeys='<%= new String[] {"all"} %>'
               portletURL="<%= trashDisplayContext.getPortletURL() %>"
           />

           <liferay-frontend:management-bar-sort
               orderByCol="<%= trashDisplayContext.getOrderByCol() %>"
               orderByType="<%= trashDisplayContext.getOrderByType() %>"
               orderColumns='<%= new String[] {"removed-date"} %>'
               portletURL="<%= trashDisplayContext.getPortletURL() %>"
           />
       </liferay-frontend:management-bar-filters>

       <liferay-frontend:management-bar-action-buttons>
           <liferay-frontend:management-bar-sidenav-toggler-button />

           <liferay-frontend:management-bar-button href="javascript:;"
           icon="trash" id="deleteSelectedEntries" label="delete" />
       </liferay-frontend:management-bar-action-buttons>
   </liferay-frontend:management-bar>

In this section of tutorials, you'll learn how to add a management bar to your 
application. 