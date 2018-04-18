# Creating Display Options for Search Results [](id=creating-display-options-for-search-results)

The Management Bar has a few display styles for your app's search container 
results. Each style offers a slightly different look and feel. At least one 
display style must be implemented. Styles that are not implemented render as 
disabled options. 

![Figure 1: The `management-bar-display-buttons` tag contains the content's display options.](../../../../images/liferay-frontend-taglib-management-bar-display-buttons.png)

To provide these display styles in your app, you must make some updates to your 
search result columns. Start by defining the display styles you want to provide. 

## Defining the Display Styles [](id=defining-the-display-styles)

The Management Bar has three display style options: descriptive--displays a 
detailed description along with summarized details for the search result columns; 
icon--displays search result columns on a horizontal or vertical card; 
list--the default view, which list the search result columns from left to right. 
Follow these steps to define the display styles for your management bar:

1.  In your app's main view, retrieve the `displayStyle` for reference:

        <%
        String displayStyle = ParamUtil.getString(request, "displayStyle");
        %>

    Alternatively, you can set the default display style by explicitly setting 
    the style when you retrieve it. By default, the display style is set to 
    `list`. For example, this sets the default display style to `icon` instead:
    
        <%
        String displayStyle = ParamUtil.getString(request, "displayStyle", "icon")
        %>

2.  Add the management bar to your app's main view and configure the display 
    buttons as shown below. Note that while this example implements all three 
    display styles, only one style is required:

        <liferay-frontend:management-bar>
            <liferay-frontend:management-bar-buttons>
              <liferay-frontend:management-bar-display-buttons
                displayViews='<%= new String[] {"icon", "descriptive", "list"} %>'
                portletURL="<%= myViewURL %>"
                selectedDisplayStyle="<%= displayStyle %>"
              />
            </liferay-frontend:management-bar-buttons>
        </liferay-frontend:management-bar>
        
    `displayViews`: The available display styles.
    
    `portletURL`: The current URL, with the display style parameter included.
    
    `selectedDisplayStyle`: The active display style.
        

3.  Create a conditional block to check for the display styles. If you only have 
    one display style, you can skip this step.

        <c:choose>
            <%-- display style configuration goes here --%>
        </c:choose>

Now that the display styles are defined, you can configure them.

## Implementing the Icon View [](id=implementing-the-icon-view)

The icon view displays the entry's information in a vertical or horizontal card 
with an image, user profile, or an icon representing the content's type, along 
with details about the content, such as its name, workflow status, and a 
condensed description.

See the 
[Liferay Frontend Cards](/develop/tutorials/-/knowledge_base/7-1/liferay-frontend-cards) 
tutorial for examples and use cases of each card. 

![Figure 2: The Management Bar's icon display view gives a quick summary of the content's description and status.](../../../../images/liferay-frontend-taglib-management-bar-display-style-icon.png)

Follow the steps below to create your icon view:

1.  Inside the `<c:choose>` conditional block, add a condition for the icon 
    display style:
    
        <c:when test='<%= Objects.equals(displayStyle, "icon") %>'>
            <%-- display style configuration goes here --%>
        </c:when>

2.  Add the proper java scriplet to make your icon view responsive to different 
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
    include the actions for the entry(if applicable), as well as an image, icon, 
    or user profile, and the entry's title. An example configuration is shown 
    below:

        <liferay-frontend:icon-vertical-card
          actionJsp='<%= dlPortletInstanceSettingsHelper.isShowActions() ? "/image_gallery_display/image_action.jsp" : StringPool.BLANK %>'
          actionJspServletContext="<%= application %>"
          cssClass="entry-display-style"
          icon="documents-and-media"
          resultRow="<%= row %>"
          title="<%= dlPortletInstanceSettingsHelper.isShowActions() ? fileEntry.getTitle() : StringPool.BLANK %>"
        />

## Implementing the Descriptive View [](id=implementing-the-descriptive-view)

The descriptive view displays the entry's complete description, along with a 
small icon for the content type, and its name. 

![Figure 3: The Management Bar's descriptive display view gives the content's full description.](../../../../images/liferay-frontend-taglib-management-bar-display-style-descriptive.png)

Inside the `<c:choose>` conditional block, add a condition for the descriptive 
display style:
    
    <c:when test='<%= Objects.equals(displayStyle, "descriptive") %>'>
        <%-- display style configuration goes here --%>
    </c:when>

Your descriptive view should have three columns with the content shown in the 
table below:

Column | Content Options | Example
------------- | ------------- | -------------
1 | icon | &lt;liferay-ui:search-container-column-icon/&gt;
&nbsp; | image | &lt;liferay-ui:search-container-column-image/&gt;
&nbsp;  | user portrait | &lt;liferay-ui:search-container-column-text&gt;<br/>&nbsp;&nbsp;&lt;liferay-ui:user-portrait/&gt;<br/>&lt;/liferay-ui:search-container-column-text&gt;
2 | description | <liferay-ui:search-container-column-text <br/>&nbsp;&nbsp;colspan="<%=2%>" <br/>><br/>&nbsp;&nbsp;&lt;h5&gt;<%= userGroup.getName() %>&lt;/h5&gt; <br/>&nbsp;&nbsp;&lt;h6 class="text-default"&gt; <br/>&nbsp;&nbsp;&nbsp;&nbsp;&lt;span&gt;<%= userGroup.getDescription() %>&lt;/span&gt; <br/>&nbsp;&nbsp;&lt;/h6&gt; <br/>&nbsp;&nbsp;&lt;h6 class="text-default"&gt; <br/>&nbsp;&nbsp;&nbsp;&nbsp;&lt;span&gt; <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<liferay-ui:message arguments="<%= usersCount%>" key="x-users"/> <br/>&nbsp;&nbsp;&nbsp;&nbsp;&lt;/span&gt; <br/>&nbsp;&nbsp;&lt;/h6&gt; <br/></liferay-ui:search-container-column-text> 
3 | actions | <liferay-ui:search-container-column-jsp<br/> &nbsp;&nbsp;path="/edit_team_assignments_user_groups_action.jsp"<br/>/>

## Implementing the List View [](id=implementing-the-list-view)

The list view is the default view that is shown for most applications. This view 
list the content's information in individual columns. 

![Figure 4: The Management Bar's list display view list the content's information in individual columns.](../../../../images/liferay-frontend-taglib-management-bar-display-style-list.png)

Inside the `<c:choose>` conditional block, add a condition for the list display 
style:
    
    <c:when test='<%= Objects.equals(displayStyle, "list") %>'>
        <%-- display style configuration goes here --%>
    </c:when>


The columns should at least contain the information shown in the table below:

Column | Content Options | Example
------------- | ------------- | -------------
1 | name | <liferay-ui:search-container-column-text <br/>&nbsp;&nbsp;cssClass="content-column name-column title-column" <br/>&nbsp;&nbsp;name="name" <br/>&nbsp;&nbsp;truncate="<%= true %>" <br/>&nbsp;&nbsp;value="<%= rule.getName(locale) %>" <br/>/>
2 | description | <liferay-ui:search-container-column-text <br/>&nbsp;&nbsp;cssClass="content-column description-column" <br/>&nbsp;&nbsp;name="description" <br/>&nbsp;&nbsp;truncate="<%= true %>" <br/>&nbsp;&nbsp;value="<%= rule.getDescription(locale) %>" <br/>/>
3 | create date | <liferay-ui:search-container-column-date <br/>&nbsp;&nbsp;cssClass="create-date-column text-column" <br/>&nbsp;&nbsp;name="create-date" <br/>&nbsp;&nbsp;property="createDate" <br/>/>
4 | actions | <liferay-ui:search-container-column-jsp <br/>&nbsp;&nbsp; cssClass="entry-action-column" <br/>&nbsp;&nbsp;path="/rule_actions.jsp" <br/>/>

## Updating the Search Iterator [](id=updating-the-search-iterator)

Once the display styles are defined, you must update the search iterator to show 
the selected display style. If your management bar only has one display style, 
you can set the `displayStyle` attribute to the style you defined, otherwise 
follow the pattern below:

    <liferay-ui:search-iterator
        displayStyle="<%= displayStyle %>"
        markupView="lexicon"
        searchContainer="<%= searchContainer %>"
    />

The `displayStyle`'s value is set to the 
[current display style](#defining-the-display-styles).

## Related Topics [](id=related-topics)

[Disabling All or Portions of the Management Bar](/develop/tutorials/-/knowledge_base/7-1/disabling-all-or-portions-of-the-management-bar)

[Filtering Search Results with the Management Bar](/develop/tutorials/-/knowledge_base/7-1/filtering-search-results-with-the-management-bar)

[Including Actions in the Management Bar](/develop/tutorials/-/knowledge_base/7-1/including-actions-in-the-management-bar)
