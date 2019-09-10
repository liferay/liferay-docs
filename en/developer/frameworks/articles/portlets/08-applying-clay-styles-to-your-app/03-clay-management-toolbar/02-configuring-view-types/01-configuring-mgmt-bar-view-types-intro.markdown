---
header-id: implementing-the-view-types
---

# Implementing the View Types

[TOC levels=1-4]

The Management Toolbar has three predefined view types for your app's search 
container results. Each style offers a slightly different look and feel. To 
provide these view types in your app, you must make some updates to your search 
result columns. Start by defining the view types you want to provide.

- **Cards:** displays search result columns on a horizontal or vertical card 

- **List:** displays a detailed description along with summarized details for 
the search result columns  

- **Table:** the default view, which list the search result columns from left to 
right 

Follow these steps to define the view types for your management toolbar:

1.  Import the `ViewTypeItemList` utility class to create the action items 
    model:

    ```markup
    <%@ page import="com.liferay.frontend.taglib.clay.servlet.taglib.util.JSPViewTypeItemList" %>
    ```

2.  Add the `frontend.taglib.clay` and `frontend.taglib.soy` module dependencies 
    to your app's `build.gradle` file:

    ```groovy
    compileOnly group: "com.liferay", name: "com.liferay.frontend.taglib.soy", 
    version: "1.0.10"

    compileOnly group: "com.liferay", name: "com.liferay.frontend.taglib.clay", 
    version: "1.0.0"
    ```

3.  In your app's main view, retrieve the `displayStyle` for reference. Each 
    view type corresponds to a display style. this is used to determine the 
    proper content configuration to display for the selected view type:

    ```java
    <%
    String displayStyle = ParamUtil.getString(request, "displayStyle");
    %>
    ```

4.  Add the management toolbar to your app's main view and configure the display 
    buttons as shown below. Note that while this example implements all three 
    view types, only one view type is required. The default or active view type 
    is set by adding `viewTypeItem.setActive(true)` to the view type:

    ```markup
    <clay:management-toolbar
        disabled=<%= assetTagsDisplayContext.isDisabledTagsManagementBar() %>
        namespace="<%= renderResponse.getNamespace() %>"
        searchContainerId="assetTags"
        selectable="<%= true %>"
        viewTypeItems="<%=
            new JSPViewTypeItemList(pageContext, baseURL, selectedType) {
                {
                	addCardViewTypeItem(
                		viewTypeItem -> {
                			viewTypeItem.setActive(true);
                			viewTypeItem.setLabel("Card");
                		});

                	addListViewTypeItem(
                		viewTypeItem -> {
                			viewTypeItem.setLabel("List");
                		});

                	addTableViewTypeItem(
                		viewTypeItem -> {
                			viewTypeItem.setLabel("Table");
                		});
                }
            }
        %>"
    />
    ```
        
    `viewTypeItems`: The available view types

5.  Create a conditional block to check for the view types. If you only have 
    one view type, you can skip this step.

    ```markup
    <c:choose>
        <%-- view type configuration goes here --%>
    </c:choose>
    ```

Now that the view types are defined, you can follow the instructions in the rest 
of this section to configure them. 
