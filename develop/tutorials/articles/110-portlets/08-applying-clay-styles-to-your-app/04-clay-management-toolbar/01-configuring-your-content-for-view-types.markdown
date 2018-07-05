# Implementing the View Types [](id=implementing-the-view-types)

The Management Toolbar has three predefined view types for your app's search 
container results. Each style offers a slightly different look and feel. To 
provide these view types in your app, you must make some updates to your search 
result columns. Start by defining the view types you want to provide. 

## Defining the View Types [](id=defining-the-view-types)

The Management Toolbar has three view types: 

- **Cards:** displays search result columns on a horizontal or vertical card 

- **List:** displays a detailed description along with summarized details for 
the search result columns  

- **Table:** the default view, which list the search result columns from left to 
right 

Follow these steps to define the view types for your management toolbar:

1.  Import the `ViewTypeItemList` utility class to create the action items 
    model:

        <%@ page import="com.liferay.frontend.taglib.clay.servlet.taglib.util.JSPViewTypeItemList" %>

2.  Add the `frontend.taglib.clay` and `frontend.taglib.soy` module dependencies 
    to your app's `build.gradle` file:

        compileOnly group: "com.liferay", name: "com.liferay.frontend.taglib.soy", 
        version: "1.0.10"
        
        compileOnly group: "com.liferay", name: "com.liferay.frontend.taglib.clay", 
        version: "1.0.0"

3.  In your app's main view, retrieve the `displayStyle` for reference. Each 
    view type corresponds to a display style. this is used to determine the 
    proper content configuration to display for the selected view type:

        <%
        String displayStyle = ParamUtil.getString(request, "displayStyle");
        %>

4.  Add the management toolbar to your app's main view and configure the display 
    buttons as shown below. Note that while this example implements all three 
    view types, only one view type is required. The default or active view type 
    is set by adding `viewTypeItem.setActive(true)` to the view type:

        <clay:management-toolbar
            disabled=<%= assetTagsDisplayContext.isDisabledTagsManagementBar() %>
            namespace="<%= renderResponse.getNamespace() %>"
            searchContainerId="assetTags"
            selectable="<%= true %>"
            viewTypes="<%=
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

        
    `viewTypes`: The available view types
    
    `portletURL`: The current URL, with the view type parameter included.

5.  Create a conditional block to check for the view types. If you only have 
    one view type, you can skip this step.

        <c:choose>
            <%-- view type configuration goes here --%>
        </c:choose>

Now that the view types are defined, you can configure them.

## Implementing the Card View [](id=implementing-the-card-view)

The card view displays the entry's information in a vertical or horizontal card 
with an image, user profile, or an icon representing the content's type, along 
with details about the content, such as its name, workflow status, and a 
condensed description.

See the 
[Liferay Frontend Cards](/develop/tutorials/-/knowledge_base/7-1/liferay-frontend-cards) 
tutorial for examples and use cases of each card. 

![Figure 1: The Management Toolbar's card view gives a quick summary of the content's description and status.](../../../../images/clay-taglib-management-toolbar-view-type-card.png)

Follow the steps below to create your card view:

1.  Inside the `<c:choose>` conditional block, add a condition for the icon 
    display style (Card view type):
    
        <c:when test='<%= Objects.equals(displayStyle, "icon") %>'>
            <%-- card view type configuration goes here --%>
        </c:when>

2.  Add the proper java scriplet to make the card view responsive to different 
    devices:

    Use the pattern below for vertical cards:

        <%
        row.setCssClass("col-md-2 col-sm-4 col-xs-6");
        %>

    For horizontal cards use the following pattern:
    
        <%
        row.setCssClass("col-md-3 col-sm-4 col-xs-12");
        %>

3.  Add the search container column text containing your card. The card should 
    include the actions for the entry(if applicable), as well as an image, icon 
    or user profile, and the entry's title. An example configuration is shown 
    below:

        <liferay-frontend:icon-vertical-card
          actionJsp='<%= dlPortletInstanceSettingsHelper.isShowActions() ? 
          "/image_gallery_display/image_action.jsp" : StringPool.BLANK %>'
          actionJspServletContext="<%= application %>"
          cssClass="entry-display-style"
          icon="documents-and-media"
          resultRow="<%= row %>"
          title="<%= dlPortletInstanceSettingsHelper.isShowActions() ? 
          fileEntry.getTitle() : StringPool.BLANK %>"
        />

## Implementing the List View [](id=implementing-the-list-view)

The list view displays the entry's complete description, along with a small icon 
for the content type, and its name. 

![Figure 2: The Management Toolbar's list view gives the content's full description.](../../../../images/clay-taglib-management-toolbar-view-type-list.png)

Inside the `<c:choose>` conditional block, add a condition for the descriptive 
display style (list view type):
    
    <c:when test='<%= Objects.equals(displayStyle, "descriptive") %>'>
        <%-- list view type configuration goes here --%>
    </c:when>

Your list view should have three columns with the content shown in the 
table below:

Column | Content Options | Example
------------- | ------------- | -------------
1 | icon | &lt;liferay-ui:search-container-column-icon/&gt;
&nbsp; | image | &lt;liferay-ui:search-container-column-image/&gt;
&nbsp;  | user portrait | &lt;liferay-ui:search-container-column-text&gt;<br/>&nbsp;&nbsp;&lt;liferay-ui:user-portrait/&gt;<br/>&lt;/liferay-ui:search-container-column-text&gt;
2 | description | <liferay-ui:search-container-column-text <br/>&nbsp;&nbsp;colspan="<%=2%>" <br/>><br/>&nbsp;&nbsp;&lt;h5&gt;<%= userGroup.getName() %>&lt;/h5&gt; <br/>&nbsp;&nbsp;&lt;h6 class="text-default"&gt; <br/>&nbsp;&nbsp;&nbsp;&nbsp;&lt;span&gt;<%= userGroup.getDescription() %>&lt;/span&gt; <br/>&nbsp;&nbsp;&lt;/h6&gt; <br/>&nbsp;&nbsp;&lt;h6 class="text-default"&gt; <br/>&nbsp;&nbsp;&nbsp;&nbsp;&lt;span&gt; <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<liferay-ui:message arguments="<%= usersCount%>" key="x-users"/> <br/>&nbsp;&nbsp;&nbsp;&nbsp;&lt;/span&gt; <br/>&nbsp;&nbsp;&lt;/h6&gt; <br/></liferay-ui:search-container-column-text> 
3 | actions | <liferay-ui:search-container-column-jsp<br/> &nbsp;&nbsp;path="/edit_team_assignments_user_groups_action.jsp"<br/>/>

## Implementing the Table View [](id=implementing-the-table-view)

The table view list the search container columns from left to right. 

![Figure 3: The Management Toolbar's table view list the content's information in individual columns.](../../../../images/clay-taglib-management-toolbar-view-type-table.png)

Inside the `<c:choose>` conditional block, add a condition for the list display 
style (table view type):
    
    <c:when test='<%= Objects.equals(displayStyle, "list") %>'>
        <%-- table view type configuration goes here --%>
    </c:when>


The columns should at least contain the information shown in the table below:

Column | Content Options | Example
------------- | ------------- | -------------
1 | name | <liferay-ui:search-container-column-text <br/>&nbsp;&nbsp;cssClass="content-column name-column title-column" <br/>&nbsp;&nbsp;name="name" <br/>&nbsp;&nbsp;truncate="<%= true %>" <br/>&nbsp;&nbsp;value="<%= rule.getName(locale) %>" <br/>/>
2 | description | <liferay-ui:search-container-column-text <br/>&nbsp;&nbsp;cssClass="content-column description-column" <br/>&nbsp;&nbsp;name="description" <br/>&nbsp;&nbsp;truncate="<%= true %>" <br/>&nbsp;&nbsp;value="<%= rule.getDescription(locale) %>" <br/>/>
3 | create date | <liferay-ui:search-container-column-date <br/>&nbsp;&nbsp;cssClass="create-date-column text-column" <br/>&nbsp;&nbsp;name="create-date" <br/>&nbsp;&nbsp;property="createDate" <br/>/>
4 | actions | <liferay-ui:search-container-column-jsp <br/>&nbsp;&nbsp; cssClass="entry-action-column" <br/>&nbsp;&nbsp;path="/rule_actions.jsp" <br/>/>

## Updating the Search Iterator [](id=updating-the-search-iterator)

Once the view type's display styles are defined, you must update the search 
iterator to show the selected view type. If your management toolbar only has one 
view type, you can set the `displayStyle` attribute to the style you defined, 
otherwise follow the pattern below:

    <liferay-ui:search-iterator
        displayStyle="<%= displayStyle %>"
        markupView="lexicon"
        searchContainer="<%= searchContainer %>"
    />

The `displayStyle`'s value is set to the 
[current view type](/develop/tutorials/-/knowledge_base/7-1/implementing-the-view-types#defining-the-view-types).

## Related Topics [](id=related-topics)

[Configuring the Clay Management Toolbar Taglib](/develop/tutorials/-/knowledge_base/7-1/clay-management-toolbar)

[Filtering and Sorting Items with the Management Toolbar](/develop/tutorials/-/knowledge_base/7-1/filtering-and-sorting-items-with-the-management-toolbar)
