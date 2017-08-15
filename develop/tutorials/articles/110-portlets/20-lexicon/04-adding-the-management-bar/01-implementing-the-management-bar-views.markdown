# Implementing the Management Bar Display Styles [](id=implementing-the-management-bar-display-styles)

The Management Bar offers a few options for display styles: descriptive, icon, 
and list. These views are standard in control panel apps. In order to provide 
these views in your app, you'll need to configure some settings for your search 
result columns. Follow the patterns covered in this tutorial to configure your 
app.

Start by configuring the Management Bar Display Buttons tag next.

## Configuring the Management Bar Display Buttons tag

Follow these steps to configure the management bar display buttons:

1.  Start by adding the `<liferay-frontend:management-bar>` taglib. You can 
    optionally provide a search container ID with the `searchContainerId` 
    attribute and a checkbox with the `includeCheckBox` attribute.
    <!-- Why would I include either one of these optional attributes? Since the 
    ID is not required, what extra benefit does it provide? -->

2.  Define the management bar buttons using the 
    `<liferay-frontend:management-bar-buttons>` and 
    `<liferay-frontend:management-bar-display-buttons>` tags. The 
    `<liferay-frontend:management-bar-display-buttons>` tag requires three 
    attributes: `displayViews`, the display style views that are available; 
    `portletURL`, the URL to redirect to after an option is chosen; 
    and `selectedDisplayStyle`, the view to display:

        <liferay-frontend:management-bar-buttons>
          <liferay-frontend:management-bar-display-buttons
            displayViews='<%= new String[] {"icon", "descriptive", "list"} %>'
            portletURL="<%= viewPageURL %>"
            selectedDisplayStyle="<%= displayStyle %>"
          />
        </liferay-frontend:management-bar-buttons>
Follow these steps to lay the foundation for the Management Bar views:

## Configuring the Display Views

1.  As a best practice you should define a default display style. For example, 
    the configuration below sets the default display style to list:

        <%
        String displayStyle = ParamUtil.getString(request, "displayStyle", "list");
        %>

    The display style is defined from within the 
    `liferay-frontend:management-bar-display-buttons` tag, as shown below:

        <liferay-frontend:management-bar>
        	<liferay-frontend:management-bar-buttons>
        		<liferay-frontend:management-bar-display-buttons
        			displayViews='<%= new String[] {"icon", "descriptive", "list"} %>'
        			portletURL="<%= viewPageURL %>"
        			selectedDisplayStyle="<%= displayStyle %>"
        		/>
        	</liferay-frontend:management-bar-buttons>
          
    The display views are passed in as the value of the `displayViews` attribute, 
    and the display style is set using a variable `displayStyle`. You'll check 
    the value of the `selectedDisplayStyle` attribute to specify which view to 
    display.

2.  Separate each display style with the proper check:

        <c:choose>
        				<c:when test='<%= Objects.equals(displayStyle, "icon") %>'>
                    <%-- include your display style configuration here --%>
                </c:when>
                ...
                <c:when test='<%= Objects.equals(displayStyle, "descriptive") %>'>
                    <%-- include your display style configuration here --%>
                </c:when>
                ...
                <c:when test='<%= Objects.equals(displayStyle, "list") %>'>
                    <%-- include your display style configuration here --%>
                </c:when>
        </c:choose>

You can start with the icon view next.


## Implementing the Icon View

Follow the patterns below to create your icon view. Your icon view should be 
responsive to different devices.

1.  Use [cards]() to display the information:

    For vertical cards use the following pattern:

        <%
        row.setCssClass("col-md-2 col-sm-4 col-xs-6");
        %>
        
    For horizontal cards use the following pattern:

        <%
        row.setCssClass("col-md-3 col-sm-4 col-xs-12");
        %>

2.  Once your cards are responsive, you'll need to add search container column 
    text, using the pattern below: <!-- include more relevant example code -->

        <liferay-ui:search-container-column-text>
            <%-- include your vertical card or horizontal card here --%>
        </liferay-ui:search-container-column-text>
    
3.  Use one of the following tags for your vertical card:

        <liferay-frontend:vertical-card/>
        
        <liferay-frontend:user-vertical-card/>
        
        <liferay-frontend:icon-vertical-card/>
        
    For horizontal cards you can use the tag below:

        <liferay-frontend:horizontal-card/>
    
Now that your icon view is configured, you can move onto your descriptive view
next.

## Implementing the Descriptive View

Your descriptive view should have three columns. 

1.  The first column usually contains an icon, image, or user portrait:

    For an icon use the following tag:

        <liferay-ui:search-container-column-icon/>
        
    For an image use the following tag:

        <liferay-ui:search-container-column-image/>    

    For a user use the following pattern:

        <liferay-ui:search-container-column-text>
            <liferay-ui:user-portrait/>
        </liferay-ui:search-container-column-text>

2.  The second column should contain the descriptions. For example, the 
    [site teams application](https://github.com/liferay/liferay-portal/blob/98d332c8fa884ab229c848e7eabd5b9a8da514d6/modules/apps/web-experience/site/site-teams-web/src/main/resources/META-INF/resources/user_group_columns.jspf)
    is configured with the settings below: 

        <liferay-ui:search-container-column-text
            colspan="<%=2%>"
        >
            <h5><%= userGroup.getName() %></h5>
            
            <h6 class="text-default">
                <span><%= userGroup.getDescription() %></span>
            </h6>
            
            <h6 class="text-default">
                <span><liferay-ui:message arguments="<%= usersCount%>" key="x-users" 
                /></span>
            </h6>

        </liferay-ui:search-container-column-text>
    
3.  Finally, the third column contains the actions. For example, the site teams 
    application uses the configuration below:

        <liferay-ui:search-container-column-jsp
            path="/edit_team_assignments_user_groups_action.jsp"
        />
    
Now that your descriptive view is configured you can implement your list view 
next.

## Implementing the List View

The list view is the default view that is shown for most applications. For
example, the [mobile device rules application](https://github.com/liferay/liferay-portal/blob/fe808e45473fc1491ac79b396b822629df5b052c/modules/apps/foundation/mobile-device-rules/mobile-device-rules-web/src/main/resources/META-INF/resources/rule_columns.jspf)
configures its list view using the pattern below:

    <liferay-ui:search-container-column-text
            cssClass="content-column name-column title-column"
            name="name"
            truncate="<%= true %>"
            value="<%= rule.getName(locale) %>"
    />
    
    <liferay-ui:search-container-column-text
            cssClass="content-column description-column"
            name="description"
            truncate="<%= true %>"
            value="<%= rule.getDescription(locale) %>"
    />
    
    <liferay-ui:search-container-column-date
            cssClass="create-date-column text-column"
            name="create-date"
            property="createDate"
    />
    
    <liferay-ui:search-container-column-text
            cssClass="text-column type-column"
            name="type"
            translate="<%= true %>"
            value="<%= rule.getType() %>"
    />
    
    <liferay-ui:search-container-column-jsp
            cssClass="entry-action-column"
            path="/rule_actions.jsp"
    />
    
Finally, set the display style in your `liferay-ui:search-iterator` tag with the 
`displayStyle` attribute:

    <liferay-ui:search-iterator

        displayStyle="<%= displayStyle %>"
        markupView="lexicon"
        searchContainer="<%= searchContainer %>"
    />

The `displayStyle` attribute is set to the `displayStyle` var which is set by 
the management bar display style buttons.
    
Your display views are configured!

## Related Topics