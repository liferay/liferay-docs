# Applying Lexicon Styles to your App [](id=applying-lexicon-styles-to-your-app)

It's important to have a consistent user experience across your apps. Liferay's
built-in apps achieve this by using the
[Lexicon Experience Language](https://dev.liferay.com/participate/liferaypedia/-/wiki/Main/Lexicon).
Lexicon is optimized to provide you with a consistent, user-friendly UI for your
Liferay apps.

The Lexicon Experience Language is included in all themes based on the `_styled`
base theme. The `_styled` base theme uses Bootstrap with a Lexicon base, giving
you access to all the components documented on the [Lexicon](http://liferay.github.io/lexicon/)
site. This means that you can use Lexicon markup and components in your custom
Liferay apps.

This tutorial shows you how to:

- configure your portlet title and back link
- leverage Liferay's taglibs to create a consistent UI
- set search container animations
- use Lexicon to improve your app's forms
- configure your app's actions menu
- use Lexicon icons in your app

First, you'll learn how to configure your application's title and back link.

## Configuring Your Application's Title and Back Link [](id=configuring-your-applications-title-and-back-link)

For @product@ 7 administration applications, the title should be moved to the
inner views, and the associated back link should be moved to the portlet titles.

Follow the pattern below for the title:

    renderResponse.setTitle();
    
Follow this pattern for the Back link:

    portletDisplay.setShowBackIcon(true);
    
    portletDisplay.setURLBack(redirect);
    
For an example of how this is implemented, take a look at the Blogs Admin
application's [`edit_entry.jsp`](https://github.com/liferay/liferay-portal/blob/b74496b5c450c134957347e7ebabd25dec1c763d/modules/apps/collaboration/blogs/blogs-web/src/main/resources/META-INF/resources/blogs/edit_entry.jsp)
:

    portletDisplay.setShowBackIcon(true);
    portletDisplay.setURLBack(redirect);
    
    renderResponse.setTitle((entry != null) ? entry.getTitle() : 
    LanguageUtil.get(request, "new-blog-entry"));

If you open the Blogs Admin application in the control panel and add a new
blog entry, you'll see this behavior in action:

![Figure 1: Adding a new blog entry displays the portlet title at the top, along with a back link.](../../../images/new-blog-entry-title.png)

Now that you know how to configure your app's title and back URL, you'll
learn how to leverage Liferay's taglibs in your app next.

## Leveraging Liferay's Taglibs to Create a Pleasant UI [](id=leveraging-liferays-taglibs-to-create-a-pleasant-ui)

Liferay's [`liferay-frontend` taglibs](@platform-ref@/7.0-latest/taglibs/modules/apps/foundation/com.liferay.frontend.taglib/com.liferay.frontend.taglib/) 
streamline the process of writing Lexicon markup, letting you focus your efforts 
on creating a pleasant user experience. The frontend taglibs give you access to 
several UI components that you can leverage in your app's design. The sections 
that follow outline some of the available components that you can use in your 
app's JSPs. 

### Applying the Add Button Pattern [](id=applying-the-add-button-pattern)

If your app requires an Actions menu, or perhaps a button that opens a different
view (like an edit screen), you may wish to use the add button pattern. This
pattern gives you a clean, minimal UI for such a button. You can use it in any
of your app's screens. The add button pattern consists of an [`add-menu` tag](@platform-ref@/7.0-latest/taglibs/modules/apps/foundation/com.liferay.frontend.taglib/com.liferay.frontend.taglib/liferay-frontend/add-menu.html) 
and at least one [`add-menu-item` tag](@platform-ref@/7.0-latest/taglibs/modules/apps/foundation/com.liferay.frontend.taglib/com.liferay.frontend.taglib/liferay-frontend/add-menu-item.html).

![Figure 2: The add button pattern consists of an `add-menu` tag and at least one `add-menu-item` tag.](../../../images/add-button-diagram.png)

If there's only one item, the plus icon acts as a button that triggers the item.
If there's more than one item, clicking the plus icon displays a menu containing
them.

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

There you have it! Now you know how to use the add button pattern. Next, you'll
learn how to update your app's navigation bar.

### Applying Lexicon to the Navigation Bar [](id=applying-lexicon-to-the-navigation-bar)

All administration apps in @product@ 7 have a navigation bar. If your app is
intended for administrative use, your app should use a navigation bar as well.
Applying Lexicon to your existing navigation bar takes only one additional
attribute.

If your app already has a navigation bar implemented with the [`aui:nav-bar`](@platform-ref@/7.0-latest/taglibs/util-taglib/aui/nav-bar.html)
tag, you can reuse it by adding the attribute `markupView="lexicon"`.

For example, the
[Liferay's Trash app](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/web-experience/trash/trash-web/src/main/resources/META-INF/resources/navigation.jsp)
uses `markupView="lexicon"` with its `nav-bar`:

    <aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">

+$$$

**Note:** The `markupView="lexicon"` attribute ensures that the Lexicon markup
is used for the UI components, rather than the standard markup. This attribute
tells the app to use the `lexicon` folder for the taglib to render the HTML,
rather than the default pages. For example, using
`<aui:fieldset markupView="lexicon" />` tells your app to render the HTML with
`/portal/portal-web/docroot/html/taglib/aui/fieldset/lexicon/`, instead of the
`end.jsp` and `start.jsp` files found in
`/portal/portal-web/docroot/html/taglib/aui/fieldset/`.

$$$

Sweetness! Now you know how to style a navigation bar with Lexicon. Next, you'll
learn about the Management Bar.

### Adding the Management Bar [](id=adding-the-management-bar)

The Management Bar lets users change the view the app displays content with. For
example, a user can use the Management Bar to display content in a list or a
grid, or display only a specific type of content. You can also customize your
app's Management Bar. The Management Bar in Liferay's Message Boards Admin
application is shown here:

![Figure 3: The Management Bar lets the user customize how the app displays content.](../../../images/message-boards-management-bar.png)

The Management Bar is divided into a few key sections. Each section is grouped
and configured using different taglibs. For example, here's the Management Bar
configuration in Liferay's Trash app:

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

The [`<liferay-frontend:management-bar-buttons>` tag](@platform-ref@/7.0-latest/taglibs/modules/apps/foundation/com.liferay.frontend.taglib/com.liferay.frontend.taglib/liferay-frontend/management-bar-buttons.html) 
wraps the Management Bar's button elements:

![Figure 4: The `management-bar-buttons` tag contains the Management Bar's main buttons.](../../../images/management-bar-buttons.png)

The [`<liferay-frontend:management-bar-sidenav-toggler-button>` tag](@platform-ref@/7.0-latest/taglibs/modules/apps/foundation/com.liferay.frontend.taglib/com.liferay.frontend.taglib/liferay-frontend/management-bar-sidenav-toggler-button.html) 
implements slide-out navigation for the info button.

The [`<liferay-frontend:management-bar-display-buttons>` tag](@platform-ref@/7.0-latest/taglibs/modules/apps/foundation/com.liferay.frontend.taglib/com.liferay.frontend.taglib/liferay-frontend/management-bar-display-buttons.html) 
renders the app's display style options:

![Figure 5: The `management-bar-display-buttons` tag contains the content's display options.](../../../images/management-bar-display-buttons.png)

The [`<liferay-frontend:management-bar-filters>` tag](@platform-ref@/7.0-latest/taglibs/modules/apps/foundation/com.liferay.frontend.taglib/com.liferay.frontend.taglib/liferay-frontend/management-bar-filters.html) 
wraps the app's filtering options. This filter should be included in all control 
panel applications. Filtering options can include sort criteria, sort ordering, 
and more:

![Figure 6: The `management-bar-filters` tag contains the content filtering options.](../../../images/management-bar-filters.png)

Finally, the [`<liferay-frontend:management-bar-action-buttons>` tag](@platform-ref@/7.0-latest/taglibs/modules/apps/foundation/com.liferay.frontend.taglib/com.liferay.frontend.taglib/liferay-frontend/management-bar-action-buttons.html) 
wraps the actions that you can execute over selected items. In @product@ 7, you 
can select multiple items between pages. The management bar keeps track of the 
number of selected items for you:

![Figure 7: The management bar keeps track of the items selected and displays the actions to execute on them.](../../../images/management-bar-action-buttons.png)

When there's no content in the app, the management bar disables all the buttons
except the info button.

You can disable the management bar by adding the `disabled` attribute to the
`liferay-frontend:management-bar` tag:

    <liferay-frontend:management-bar
            disabled="<%= total == 0 %>"
            includeCheckBox="<%= true %>"
            searchContainerId="<%= searchContainerId %>"
    >

You can also disable individual buttons by adding the `disabled` attribute to
the corresponding tag:

    <liferay-frontend:management-bar-display-buttons
            disabled="<%= total == 0 %>"
            displayViews='<%= new String[] {"descriptive", "icon", "list"} %>'
            portletURL="<%= changeDisplayStyleURL %>"
            selectedDisplayStyle="<%= trashDisplayContext.getDisplayStyle() %>"
    />

Now that you've managed your app's management bar, you can learn how to apply
Lexicon to the search iterator next.

### Implementing the Different Management Bar Views [](id=implementing-the-different-management-bar-views)

As you saw in the last section, the management bar offers a few options for
display styles: icon, descriptive, and list. These views are standard in 
control panel apps. In order to provide these views in your app, you'll need to 
configure some settings for your search result columns. Follow the patterns
below to configure your app.

Follow the patterns below to create your icon view. Your icon view should be 
responsive to different devices.

For vertical cards use the following pattern:

    <%
    row.setCssClass("col-md-2 col-sm-4 col-xs-6");
    %>
    
For horizontal cards use the following pattern:

    <%
    row.setCssClass("col-md-3 col-sm-4 col-xs-12");
    %>

Once your cards are responsive, you'll need to add search container column text,
using the pattern below:

    <liferay-ui:search-container-column-text>
        <%-- include your vertical card or horizontal card here --%>
    </liferay-ui:search-container-column-text>
    
Use one of the following tags for your vertical card:

    <liferay-frontend:vertical-card/>
    
    <liferay-frontend:user-vertical-card/>
    
    <liferay-frontend:icon-vertical-card/>
    
For horizontal cards you can use the tag below:

    <liferay-frontend:horizontal-card/>
    
Now that your icon view is configured, you can move onto your descriptive view
next.

Your descriptive view should have three columns. The first column usually
contains an icon, image, or user portrait.

For an icon use the following tag:

    <liferay-ui:search-container-column-icon/>
    
For an image use the following tag:

    <liferay-ui:search-container-column-image/>    

For a user use the following pattern:

    <liferay-ui:search-container-column-text>
        <liferay-ui:user-portrait/>
    </liferay-ui:search-container-column-text>

The second column should contain the descriptions. For example, the 
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
    
Finally, the third column contains the actions. For example, the site teams 
application uses the configuration below:

    <liferay-ui:search-container-column-jsp
        path="/edit_team_assignments_user_groups_action.jsp"
    />
    
Now that your descriptive view is configured you can setup your list view next.
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
    
Your display views are configured! As best practice, you should set your 
`liferay-ui:search-iterator />`'s `displayStyle` attribute to the current style
being used, and make sure the `markupView` attribute is set to use `lexicon`.
You'll take a closer look at the search iterator next.

### Applying Lexicon to the Search Iterator [](id=applying-lexicon-to-the-search-iterator)

To apply Lexicon to your search iterator you will just need to add one
attribute. Using the existing `liferay-ui:search-iterator` tag, you can add the
`markupView="lexicon"` attribute:

    <liferay-ui:search-iterator

        displayStyle="<%= displayStyle %>"
        markupView="lexicon"
        searchContainer="<%= searchContainer %>"
    />

If the results contain different set of entries (folders and documents,
categories and threads, etc.) you will need to use a ResultRowSplitter to
divide the results. The result row splitter is an attribute that is
provided to the liferay-ui:search-iterator taglib as shown below:

    <liferay-ui:search-iterator

        displayStyle="<%= displayStyle %>"
        markupView="lexicon"

        markupView="<%= new DLResultRowSplitter() %>"
        searchContainer="<%= searchContainer %>"
    />

You will need to create a java class that implements the ResultRowSplitter
interface. The ResultRowSplitter class is responsible for dividing and
categorizing the results based on the different entry types. See the Bookmarks
application's [BookmarksResultRowSplitter.java](@platform-ref@/7.0-latest/javadocs/modules/apps/collaboration/bookmarks/com.liferay.bookmarks.web/com/liferay/bookmarks/web/internal/dao/search/BookmarksResultRowSplitter.html) 
class for example.

Finally, the action menu style should be changed to use the vertical ellipsis
icon (icon-ellipsis-vertical). The taglib should look like this:

    <liferay-ui:icon-menu
           direction="left-side"
           icon="<%= StringPool.BLANK %>"
           markupView="lexicon"
           message="<%= StringPool.BLANK %>"
           showWhenSingleIcon="<%= true %>"
    >
    
Setting the `icon` and `message` values to `StringPool.BLANK` defaults the value 
to the vertical ellipsis icon.

Now that your search iterator is configured, you can learn how to set search
container animations next.

## Setting Search Container Animations [](id=setting-search-container-animations)

If you've taken a tour around @product@ 7's UI, you've probably noticed some new
animations in the search containers.

These animations are used to let the user know that there is no available
content. To use these animations in your app you can use the following method:

    SearchContainer.setEmptyResultsMessageCssClass()

There are three built-in classes that you can choose from for the animation:

"taglib-empty-result-message-header" which is the default one

![Figure 8: ](../../../images/no-content-found-blog.png)

"taglib-empty-search-result-message-header" used when searching

![Figure 9: ](../../../images/no-web-content-found-search.png)

"taglib-empty-result-message-header-has-plus-btn" used when there is a plus
button:

![Figure 10: ](../../../images/no-tags-found-plus-button.png)

For example, the Roles Admin application uses the following code to set its
animation:

    if (!searchTerms.isSearch()) {
            searchContainer.setEmptyResultsMessageCssClass(
            "taglib-empty-result-message-header-has-plus-btn"
            );
    }

You can of course create your own animation to use for the messages. Use
@product@'s existing CSS styles in [`_empty_result_message.scss`](https://github.com/liferay/liferay-portal/blob/fe808e45473fc1491ac79b396b822629df5b052c/modules/apps/foundation/frontend-css/frontend-css-web/src/main/resources/META-INF/resources/taglib/_empty_result_message.scss)
as a guide.

Now that you know how to configure your search container messages, you can learn
how to apply Lexicon to your forms next.

## Improving your Forms with Lexicon [](id=improving-your-forms-with-lexicon)

Follow these steps to apply Lexicon to your forms:

1. Encapsulate your fieldsets with the following taglib:

        <aui:fieldset-group markupView="lexicon">

        </aui:fieldset-group>

    The fieldset inside `fieldset-group` should be collapsible.

2. Add the `collapsed` and `collapsible` attributes to your `aui:fieldset`
   taglib:

        <aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>"
        label="permissions">
              ...
        </aui:fieldset>

3. Add the `btn-lg` CSS class to your form's buttons:

        <aui:button-row>

            <aui:button cssClass="btn-lg" type="submit" />

            <aui:button cssClass="btn-lg" href="<%= redirect %>"
            type="cancel" />

        </aui:button-row>

Your forms are now configured for Lexicon!

You'll learn how to configure the actions for your app next.

## Configuring Your Apps Actions Menu [](id=configuring-your-apps-actions-menu)

In previous versions of Liferay it was common to have a series of buttons or
menus with actions in the different views of the app. In @product-ver@ the 
proposed pattern is to move all of these actions to the upper right menu, 
leaving the primary action(often an "Add" operation) visible in the add menu. 
For example, the web content application has the actions menu shown below:

![Figure 11: The upper right ellipsis menu contains most of the actions for the app.](../../../images/actions-menu.png)

To add an action to the upper right menu you will need to first create a 
[`PortletConfigurationIcon` component](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/configuration/icon/PortletConfigurationIcon.html). 
This class specifies the portlet where the action will be added, the screen in 
which it will be shown, and the order (by specifying a weight).

In this example, the action will be shown in the home page of the System
Settings portlet. To make it appear in a secondary screen you can use the `path`
property as shown below:

    @Component(
      immediate = true,
      property = {
         "javax.portlet.name=" +
            ConfigurationAdminPortletKeys.SYSTEM_SETTINGS,
         "path=/view_factory_instances"
      },
      service = PortletConfigurationIconFactory.class
    )
    public class ExportFactoryInstancesIconFactory
      extends BasePortletConfigurationIconFactory {
    
      @Override
      public PortletConfigurationIcon create(PortletRequest portletRequest) {
         return new ExportFactoryInstancesIcon(portletRequest);
      }
    
      @Override
      public double getWeight() {
         return 1;
      }
    
    }

The value of the `path` property depends on the MVC framework being used to
develop the app.

For the MVCPortlet framework you should provide the path(often a JSP) that is 
used in the mvcPath parameter.

For MVCPortlet with MVCCommands the path should contain the mvcRenderCommandName
where the actions should be displayed
(such as /document_library/edit_folder for example).

The second class that you need to write specifies the label of the action,
whether it should be invoked with a GET or POST method and the URL
(or onClick JavaScript method) that should be invoked when the action is
clicked. It can also implement some custom code to determine whether the action
should be shown for the current request. For example the class below creates a
`export-all-settings` label and specfies the `GET` method for the action:

    public class ExportAllConfigurationIcon extends BasePortletConfigurationIcon {
    
      public ExportAllConfigurationIcon(PortletRequest portletRequest) {
         super(portletRequest);
      }
    
      @Override
      public String getMessage() {
         return "export-all-settings";
      }
    
      @Override
      public String getMethod() {
         return "GET";
      }
    
      @Override
      public String getURL() {
         LiferayPortletURL liferayPortletURL =
            (LiferayPortletURL)PortalUtil.getControlPanelPortletURL(
               portletRequest, ConfigurationAdminPortletKeys.SYSTEM_SETTINGS,
               PortletRequest.RESOURCE_PHASE);
    
         liferayPortletURL.setResourceID("export");
    
         return liferayPortletURL.toString();
      }
    
      @Override
      public boolean isShow() {
         return true;
      }
    
    }

By default, if the portlet uses mvcPath, the global actions 
(such as configuration, export/import, maximized, etc) will be displayed for the 
jsp indicated in the init param of the portlet 
`javax.portlet.init-param.view-template=/view.jsp`. The value indicates the jsp 
where the global actions should be displayed.

However, if the portlet uses MVCCommand, the views for the global actions need 
to be indicated with the init-parameter 
`javax.portlet.init-param.mvc-command-names-default-views=/wiki_admin/view` and 
the value must contain the mvcRenderCommandName where the global actions should 
be displayed.

For portlets that can be added to a page, if the desired behaviour is to always 
include the configuration options, the following init-parameter should be added 
to the portlet:

    javax.portlet.init-param.always-display-default-configuration-icons=true
    
Your actions are configured for your menu!
    
In the next section, you'll learn to leverage Lexicon's icons throughout your
app.

## Using Lexicon Icons in Your App [](id=using-lexicon-icons-in-your-app)

Whether you're updating your app to @product@ 7, or writing a new @product@ 7
app, follow the process here to use Lexicon's icons in your app. You can find
the list of available Lexicon icons on the
[Lexicon site](http://liferay.github.io/lexicon/content/icons-lexicon/).

Lexicon icons are defined with the `icon` attribute. For example, you can use
this attribute to define the icon in the management bar, inside of the
`liferay-frontend:management-bar-sidenav-toggler-button` taglib:

    <liferay-frontend:management-bar-sidenav-toggler-button
            disabled="<%= false %>"
            href="javascript:;"
            icon="info-circle"
            label="info"
            sidenavId='<%= liferayPortletResponse.getNamespace() + "infoPanelId" %>'
    />

To use Lexicon icons outside of the management bar, you have two options:

You can use the [`liferay-ui:icon` taglib](@platform-ref@/7.0-latest/taglibs/util-taglib/liferay-ui/icon.html). 
For example:

    <liferay-ui:icon

        icon="icon-name"

        markupView="lexicon"

        message="message-goes-here"

    />

You can also use the [`aui:icon` taglib](@platform-ref@/7.0-latest/taglibs/util-taglib/aui/icon.html). 
For example:

    <aui:icon

         cssClass="icon-monospaced"

         image="times"

         markupView="lexicon"

    />

Note the addition of the `markupView="lexicon"` attribute. This ensures that
Lexicon markup renders the HTML.

That's it! As you can see, Lexicon offers a great deal. If you leverage Lexicon
in your apps, you can ensure a consistent user experience that your users will
be pleased with.

## Related Topics [](id=related-topics)

[Front-End Taglibs](/develop/tutorials/-/knowledge_base/7-0/front-end-taglibs)

[Themes and Layout Templates](/develop/tutorials/-/knowledge_base/7-0/themes-and-layout-templates)
