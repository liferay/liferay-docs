# Implementing the Management Bar Display Styles [](id=implementing-the-management-bar-display-styles)

The Management Bar offers a few display styles for your app's search container 
contents: descriptive, icon, and list. These views are standard in @product@'s 
control panel apps. While you are not required to implement all these display 
styles in your app's management bar, they provide some additional control over 
how your app's information is displayed.

![Figure 1: The `management-bar-display-buttons` tag contains the content's display options.](../../../../images/management-bar-display-buttons.png)

To provide these views in your app, you must make some updates to your search 
result columns. Follow the patterns covered in this tutorial to configure your 
app.

**Note:** You are not required to implement all the display views in your app. 
You must just at least have one display style implemented (`list` is the default). 
Views that are disabled in your app will render as greyed out buttons.

Start by configuring the Management Bar Display Buttons tag next.

## Configuring the Management Bar Display Buttons Tag [](id=configuring-the-management-bar-display-buttons-tag)

Follow these steps to configure the management bar display button tags:

1.  Add the `<liferay-frontend:management-bar>` taglib to your app's main view 
    (`view.jsp` for example). If the management bar has a checkbox that needs to 
    stay selected while the navigation is used, you can optionally provide a 
    search container ID with the `searchContainerId` attribute and set the 
    `includeCheckBox` attribute to `true`.

2.  Add the management bar buttons using the 
    `<liferay-frontend:management-bar-buttons>` and 
    `<liferay-frontend:management-bar-display-buttons>` tags. The 
    `<liferay-frontend:management-bar-display-buttons>` tag requires three 
    attributes: `displayViews`, the display style views that are available; 
    `portletURL`, the URL to redirect to after an option is chosen; and 
    `selectedDisplayStyle`, the view to display. Below is an example 
    configuration that implements all three display views:

        <liferay-frontend:management-bar>
            <liferay-frontend:management-bar-buttons>
              <liferay-frontend:management-bar-display-buttons
                displayViews='<%= new String[] {"icon", "descriptive", "list"} %>'
                portletURL="<%= myViewURL %>"
                selectedDisplayStyle="<%= displayStyle %>"
              />
            </liferay-frontend:management-bar-buttons>
        </liferay-frontend:management-bar>

Your taglibs are configured for your display styles, but at the moment they 
don't do anything. You'll configure the views next.

## Configuring the Display Views [](id=configuring-the-display-views)

Note that your management bar may not contain all three views. You only need to 
implement the views that you defined in your 
`<liferay-frontend:management-bar-display-buttons>` tag's `displayViews` 
attribute. Follow these steps to set the display views for the management bar:

1.  Define a default display style. For example, the configuration below sets 
    the default display style to list:

        <%
        String displayStyle = ParamUtil.getString(request, "displayStyle", "list");
        %>

2.  Wrap each display style configuration with the proper check:

        <c:choose>
        				<c:when test='<%= Objects.equals(displayStyle, "icon") %>'>
                    <%-- icon display style configuration goes here --%>
                </c:when>
                ...
                <c:when test='<%= Objects.equals(displayStyle, "descriptive") %>'>
                    <%-- descriptive display style configuration goes here --%>
                </c:when>
                ...
                <c:when test='<%= Objects.equals(displayStyle, "list") %>'>
                    <%-- list display style configuration goes here --%>
                </c:when>
        </c:choose>

Use [cards](https://lexicondesign.io/docs/patterns/cards.html) to display the 
information. Use a vertical card to display assets like files or web content. 
Use horizontal cards to display folders or directories. You can add the display 
style configurations for each view next. 

### Implementing the Icon View [](id=implementing-the-icon-view)

The icon view prominently displays an icon for the content, along with its name, 
status, and a condensed description.

![Figure 2: The Management Bar's icon display view gives a quick summary of the content's description and status.](../../../../images/lexicon-icon-view.png)

Follow the steps below to create your icon view:

1.  First, make your icon view responsive to different devices.

    For vertical cards use the following pattern:

        <%
        row.setCssClass("col-md-2 col-sm-4 col-xs-6");
        %>

    For horizontal cards use the following pattern:

        <%
        row.setCssClass("col-md-3 col-sm-4 col-xs-12");
        %>

2.  Once your cards are responsive, you must add search container column text 
    using the pattern below:

        <liferay-ui:search-container-column-text>
            <%-- include your vertical card or horizontal card here --%>
        </liferay-ui:search-container-column-text>

3.  Add the card to the `<liferay-ui:search-container-column-text>`:

    Use one of the following tags for your vertical card:

        <liferay-frontend:vertical-card/>

        <liferay-frontend:user-vertical-card/>

        <liferay-frontend:icon-vertical-card/>
        
    Below is an example from the `com.liferay.journal.web` module's [`view_comments.jsp`](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/web-experience/journal/journal-web/src/main/resources/META-INF/resources/view_comments.jsp):

        <liferay-ui:search-container-column-text>
          <liferay-frontend:vertical-card
            cssClass="entry-display-style"
            imageUrl="<%= (userDisplay != null) ? userDisplay.getPortraitURL(themeDisplay) : UserConstants.getPortraitURL(themeDisplay.getPathImage(), true, 0, null) %>"
            resultRow="<%= row %>"
          >
            <liferay-frontend:vertical-card-header>
              <liferay-ui:message arguments="<%= new String[] {LanguageUtil.getTimeDescription(locale, System.currentTimeMillis() - mbMessage.getModifiedDate().getTime(), true), HtmlUtil.escape(mbMessage.getUserName())} %>" key="x-ago-by-x" translateArguments="<%= false %>" />
            </liferay-frontend:vertical-card-header>

            <liferay-frontend:vertical-card-footer>
              <%= HtmlUtil.extractText(content) %>
            </liferay-frontend:vertical-card-footer>
          </liferay-frontend:vertical-card>
        </liferay-ui:search-container-column-text>

    For horizontal cards you can use the tag below:

        <liferay-frontend:horizontal-card/>

Now that your icon view is configured, you can move onto your descriptive view 
next.

### Implementing the Descriptive View [](id=implementing-the-descriptive-view)

The descriptive view displays the complete description, along with a small icon 
for the content, and its name. 

![Figure 3: The Management Bar's descriptive display view gives the content's full description.](../../../../images/lexicon-descriptive-view.png)

Your descriptive view should have three columns.

1.  The first column usually contains an icon, image, or user portrait:

    For an icon use the following tag:

        <liferay-ui:search-container-column-icon/>

    For an image use the following tag:

        <liferay-ui:search-container-column-image/>    

    For a user portrait use the following pattern:

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

### Implementing the List View [](id=implementing-the-list-view)

The list view is the default view that is shown for most applications. This view 
lists the content's information in individual columns. 

![Figure 4: The Management Bar's list display view list the content's information in individual columns.](../../../../images/lexicon-list-view.png)

For example, the [mobile device rules application](https://github.com/liferay/liferay-portal/blob/fe808e45473fc1491ac79b396b822629df5b052c/modules/apps/foundation/mobile-device-rules/mobile-device-rules-web/src/main/resources/META-INF/resources/rule_columns.jspf)
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

## Related Topics [](id=related-topics)

[Implementing a Management Bar Sort Filter](/develop/tutorials/-/knowledge_base/7-0/implementing-a-management-bar-sort-filter)

[Implementing a Management Bar Navigation Filter](/develop/tutorials/-/knowledge_base/7-0/implementing-a-management-bar-navigation-filter)
