# Applying Lexicon Styles to your Application [](id=applying-lexicon-styles-to-your-application)

The applications that come packaged with Liferay use the [Lexicon](/participate/liferaypedia/-/wiki/Main/Lexicon)
design language for their markup. When applying a new style of design it’s 
important to have a consistent user experience across your applications. 
Lexicon's markup is optimized to provide you with a consistent, user-friendly UI 
for your applications in Liferay.

The default themes, Admin and Classic, include an implementation of the Lexicon 
Experience Language. Likewise, the Lexicon base styles are provided in the 
`aui.scss` file of the `_styled` base theme. What this means for you as a 
developer is that you will be able to use Lexicon markup and components in your 
custom applications in @product@ 7.

This tutorial demonstrates the following:

- How to leverage Liferay's taglibs to create a consistent UI
- How to improve your Application's forms with Lexicon
- How to use Lexicon icons in your Application

Learn how to leverage Liferay's taglibs in your application next.

## Leveraging Liferay’s Frontend Taglibs to Create a Pleasant UI [](id=leveraging-liferays-frontend-taglibs-to-create-a-pleasant-ui)

Liferay's `liferay-frontend` taglibs streamline the process of writing Lexicon 
markup, allowing you to focus your efforts on creating a pleasant user 
experience.

The Frontend taglibs give you access to several UI components that you can
leverage in your application's design.

The sections that follow outline some of the available components that you can
use in your application's JSPs.

### Applying the Add Button Pattern [](id=applying-the-add-button-pattern)

If your application requires an actions menu, or perhaps a button to open a
different view of your application, like an edit screen for adding new content 
for example, you may wish to use the add button pattern.

The add button pattern provides you with a clean, minimal, UI, that caters to 
the needs of your application. It is intended for use in the main screen of your 
application, or in any inside screen where it makes sense.

The add button pattern consist of an `add-menu` and at least one `add-menu-item`.

![figure x: The add button consists of a add-menu and at least one menu item.](../../images/add-button-diagram.png)

If there is only one item, the plus icon will act as a button. If you add
more than one menu-item, then the user will be presented with a menu of options
to choose from.

Follow the pattern below to create a add menu for your application:

    <liferay-frontend:add-menu>
        <liferay-frontend:add-menu-item title='<%= LanguageUtil.get(request, 
        "titleName") %>' url="<%= nameURL.toString() %>" />
    <liferay-frontend:add-menu>

The [Message Boards Admin portlet](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/collaboration/message-boards/message-boards-web/src/main/resources/META-INF/resources/message_boards_admin/add_button.jsp) 
for example uses the following add button pattern:

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
    
There you have it. Your add button is ready to go. Learn how to update your 
application's navigation bar next.

### Applying Lexicon to the Navigation Bar [](id=applying-lexicon-to-the-navigation-bar)

All administration applications in @product@ 7 have a navigation bar. If your 
application is intended for administrative use, you may wish to follow the same 
design pattern.

Applying the Lexicon pattern to your existing navigation bar takes just one
additional attribute.

If your application already has a navigation bar implemented with the 
`aui:nav-bar` taglib, you can reuse it by adding the attribute 
`markupView=”lexicon”`.

+$$$

**Note:** The `markupView="lexicon"` attribute ensures that the Lexicon markup
is used for the UI components, rather than the standard markup. This attribute
tells the application to use the lexicon folder for the taglib to render the 
HTML rather than the default pages. For example using `
<aui:fieldset markupView=“lexicon” />` will use 
`/portal/portal-web/docroot/html/taglib/aui/fieldset/lexicon/` instead of the 
`end.jsp` and `start.jsp` found at 
`/portal/portal-web/docroot/html/taglib/aui/fieldset/`.

$$$

For example, the [trash application](https://github.com/liferay/liferay-portal/blob/2960360870ae69360861a720136e082a06c5548f/modules/apps/web-experience/trash/trash-web/src/main/resources/META-INF/resources/navigation.jsp) 
uses the following pattern:
    
    <aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
    
Your navigation bar is ready to go. You can learn about the User Management Bar
next.

### Adding the Management Bar [](id=adding-the-management-bar)
 
The management bar contains actions for the end user to aid in managing the 
application's content and can be customized to meet your needs. Take a look at 
the Message Boards Admin portlet for example:

![figure x: The management bar gives the user access to several tools to manage the application's content.](../../images/message-boards-management-bar.png)

The management bar is divided into a few key sections.

Each of these divisions are grouped and configured using different taglibs. Here
is how the Trash application's management bar is configured:

    <liferay-frontend:management-bar>
            <liferay-frontend:management-bar-buttons>
                    <liferay-frontend:management-bar-sidenav-toggler-button
                            disabled="<%= false %>"
                            href="javascript:;"
                            icon="info-circle"
                            label="info"
                            sidenavId='<%= liferayPortletResponse.getNamespace() 
                            + "infoPanelId" %>'
                    />
    
                    <liferay-portlet:actionURL name="changeDisplayStyle" 
                    varImpl="changeDisplayStyleURL">
                            <portlet:param name="redirect" value="<%= 
                            currentURL %>" />
                    </liferay-portlet:actionURL>
    
                    <liferay-frontend:management-bar-display-buttons
                            displayViews='<%= new String[] {"descriptive", 
                            "icon", "list"} %>'
                            portletURL="<%= changeDisplayStyleURL %>"
                            selectedDisplayStyle="<%= 
                            trashDisplayContext.getDisplayStyle() %>"
                    />
            </liferay-frontend:management-bar-buttons>
    
            <liferay-frontend:management-bar-filters>
                    <liferay-frontend:management-bar-navigation
                            navigationKeys='<%= new String[] {"all"} %>'
                            portletURL="<%= trashDisplayContext.getPortletURL() 
                            %>"
                    />
            </liferay-frontend:management-bar-filters>
    </liferay-frontend:management-bar>

Each taglib corresponds to a division of the management bar.

The `<liferay-frontend:management-bar-buttons>...<.../>` tag wraps the
management bar button elements:

![figure x: The management bar buttons tag contains the main buttons for the management bar.](../../images/management-bar-buttons.png)

The `<liferay-frontend:management-bar-sidenav-toggler-button.../>` tag renders
a slide out navigation, for the info button in this case. The `info-circle` icon 
is specified as the `icon` for the button. You can choose from a selection of 
Lexicon icons for your application's button. More on this later.

The `<liferay-frontend:management-bar-display-buttons.../>` tag renders the
display style options for the application:

![figure x: The management bar display buttons tag contains the display options for the content.](../../images/management-bar-display-buttons.png)

Finally, the `<liferay-frontend:management-bar-filters>...<.../>` tag wraps the
filter options for the application:

![figure x: The management bar filters tag contains the filter options for the content.](../../images/management-bar-filters.png)

When the application doesn't contain any content, all the buttons, except for 
the info button, are disabled in the management bar.

You can disable the management bar by adding the `disabled` attribute to the 
`liferay-frontend:management-bar` tag:

    <liferay-frontend:management-bar
            disabled="<%= total == 0 %>"
            includeCheckBox="<%= true %>"
            searchContainerId="<%= searchContainerId %>"
    >

You can also disable individual buttons by adding the `disabled` attribute to 
the tag:

    <liferay-frontend:management-bar-display-buttons
            disabled="<%= total == 0 %>"
            displayViews='<%= new String[] {"descriptive", "icon", "list"} %>'
            portletURL="<%= changeDisplayStyleURL %>"
            selectedDisplayStyle="<%= trashDisplayContext.getDisplayStyle() %>"
    />

Now that your management bar is managed, you can learn how to apply the Lexicon 
design pattern to your forms next.

## Improving your Forms with Lexicon [](id=improving-your-forms-with-lexicon)

To apply the Lexicon design patterns to your forms follow the steps below:

1.  Encapsulate your fieldsets with the following taglib:

        <aui:fieldset-group markupView="lexicon">
        
        </aui:fieldset-group>

    The fieldset inside `fieldset-group` should be collapsible.
    
2.  Add the `collapsed` and `collapsible` attributes to your `aui:fieldset` 
    taglib:

        <aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" 
        label="permissions">
              ...
        </aui:fieldset>

3.  Add the `btn-lg` CSS class to your form's buttons:

        <aui:button-row>
        
            <aui:button cssClass="btn-lg" type="submit" />
            
            <aui:button cssClass="btn-lg" href="<%= redirect %>"
            type="cancel" />
        
        </aui:button-row>
        
Your forms are now configured for Lexicon.

Earlier you saw how Lexicon's icons can be specified in the management bar. In 
the next section, you can learn how to leverage Lexicon's icons in your 
application.

## Using Lexicon Icons in Your Application [](id=using-lexicon-icons-in-your-application)

You may be updating your application to @product@ 7, or perhaps you are writing
a new application for @product@ 7. In either case, you can follow the process 
below to use Lexicon's icons in your application.

The list of available Lexicon icons can be found on the [Lexicon](http://liferay.github.io/lexicon/content/icons-lexicon/) 
site.

Lexicon icons are defined with the `icon` attribute of the taglib.

For example, you can define the icon in the management bar inside of the
`liferay-frontend:management-bar-sidenav-toggler-button` taglib:

    <liferay-frontend:management-bar-sidenav-toggler-button
            disabled="<%= false %>"
            href="javascript:;"
            icon="info-circle"
            label="info"
            sidenavId='<%= liferayPortletResponse.getNamespace() + "infoPanelId" %>'
    />

For standard use of Lexicon icons, outside of the management bar, you can use
the `liferay-ui:icon` taglib. Follow the pattern below to add the icon to your
application:

    <liferay-ui:icon
    
        icon="icon-name"
        
        markupView="lexicon"
        
        message="message-goes-here"
    
    />

Note the addition of the `markupView="lexicon"` attribute, which ensures that 
Lexicon markup is used to render the HTML.

That's all you need to do to use Lexicon icons in your application.

As you can see, the Lexicon design Experience language has a lot to offer. If
you leverage Lexicon markup in your applications, you can ensure a consistent
user experience that your users will be pleased with.

## Related Topics [](id=related-topics)


