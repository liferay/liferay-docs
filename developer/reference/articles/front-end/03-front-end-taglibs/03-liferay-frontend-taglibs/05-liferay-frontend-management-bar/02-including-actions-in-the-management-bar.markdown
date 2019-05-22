---
header-id: including-actions-in-the-management-bar
---

# Including Actions in the Management Bar

[TOC levels=1-4]

While an actions menu is typically included with each search container result, 
you can also include these actions in the management bar. This keeps everything 
organized within the same UI. This update adds a checkbox next to each search 
container result, as well as adds one in the management bar itself to select all 
results. The actions are displayed when a checkbox is checked---individual or 
select all---and hidden from view otherwise. 

![Figure 1: You can select individual results or all results at once.](../../../../../images/liferay-frontend-taglib-management-bar-include-checkbox.png)

Follow these steps to include actions in your management bar:

1.  Update the `<liferay-frontend:management-bar>` tag to include the checkbox 
    and provide the search container's ID:

    ```html
    <liferay-frontend:management-bar
    	includeCheckBox="<%= true %>"
    	searchContainerId="mySearchContainerId"
    >
    ```

2.  After the closing `</liferay-frontend:management-bar-filters>` tag, add the 
    `<liferay-frontend:management-bar-action-buttons>` tags:

    ```html    
    <liferay-frontend:management-bar-action-buttons>

    </liferay-frontend:management-bar-action-buttons>
    ```

3.  Use the available management bar button taglibs 
    (e.g. `management-bar-button`) to build the action buttons for your app's 
    management bar. A code snippet from the Site admin portlet is shown below: 

    ```html
    <liferay-frontend:management-bar-action-buttons>
    	<liferay-frontend:management-bar-sidenav-toggler-button
    		icon="info-circle"
    		label="info"
    	/>

    	<liferay-frontend:management-bar-button
    		href="javascript:deleteEntries();"
    		icon="trash"
    		id="deleteSites"
    		label="delete"
    	/>
    </liferay-frontend:management-bar-action-buttons>
    ```

![Figure 2: You can have as many actions as your app requires.](../../../../../images/liferay-frontend-taglib-management-bar-actions.png)

## Related Topics

- [Disabling All or Portions of the Management Bar](/docs/7-2/frameworks/-/knowledge_base/f/disabling-all-or-portions-of-the-management-bar)
- [Clay Management Toolbar](/docs/7-2/reference/-/knowledge_base/r/clay-management-toolbar)
