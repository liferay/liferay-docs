# Applying Lexicon Styles to your App [](id=applying-lexicon-styles-to-your-app)

It’s important to have a consistent user experience across your apps. Liferay's 
built-in apps achieve this by using the 
[Lexicon Experience Language](/participate/liferaypedia/-/wiki/Main/Lexicon). 
Lexicon is optimized to provide you with a consistent, user-friendly UI for your 
Liferay apps. 

Liferay's default themes, Admin and Classic, include an implementation of the 
Lexicon Experience Language. Also, Lexicon's base styles are provided in the 
`aui.scss` file of the `_styled` base theme. This means that you can use Lexicon 
markup and components in your custom apps in @product@ 7. 

This tutorial shows you how to:

- leverage Liferay's taglibs to create a consistent UI
- use Lexicon to improve your app's forms
- use Lexicon icons in your app

First, you'll learn how to leverage Liferay's taglibs in your app. 

## Leveraging Liferay’s Frontend Taglibs to Create a Pleasant UI [](id=leveraging-liferays-frontend-taglibs-to-create-a-pleasant-ui)

Liferay's `liferay-frontend` taglibs streamline the process of writing Lexicon 
markup, letting you focus your efforts on creating a pleasant user experience. 
The Frontend taglibs give you access to several UI components that you can 
leverage in your app's design. The sections that follow outline some of the 
available components that you can use in your app's JSPs. 

### Applying the Add Button Pattern [](id=applying-the-add-button-pattern)
<!-- Should "add button pattern" be capitalized? -->

If your app requires an Actions menu, or perhaps a button that opens a different 
view (like an edit screen), you may wish to use the add button pattern. This 
pattern gives you a clean, minimal UI for such a button. You can use it in any 
of your app's screens. The add button pattern consists of an `add-menu` tag and 
at least one `add-menu-item` tag. 

![Figure x: The add button pattern consists of an `add-menu` tag and at least one `add-menu-item` tag.](../../images/add-button-diagram.png)

If there's only one item, the plus icon acts as a button that triggers the item. 
If there's more than one item, clicking the plus icon displays a menu containing 
them. 

Here's an example of the add button pattern with a single item: 

    <liferay-frontend:add-menu>
        <liferay-frontend:add-menu-item title='<%= LanguageUtil.get(request, 
        "titleName") %>' url="<%= nameURL.toString() %>" />
    <liferay-frontend:add-menu>

You can also find the add button pattern in Liferay's built-in apps. For 
example, the 
[Message Boards Admin portlet](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/collaboration/message-boards/message-boards-web/src/main/resources/META-INF/resources/message_boards_admin/add_button.jsp) 
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
intended for administrative use, you may want to use a navigation bar as well. 
Applying Lexicon to your existing navigation bar takes only one additional 
attribute.

If your app already has a navigation bar implemented with the `aui:nav-bar` 
tag, you can reuse it by adding the attribute `markupView=”lexicon”`.

+$$$

**Note:** The `markupView="lexicon"` attribute ensures that the Lexicon markup
is used for the UI components, rather than the standard markup. This attribute
tells the app to use the `lexicon` folder for the taglib to render the HTML, 
rather than the default pages. For example, using 
`<aui:fieldset markupView=“lexicon” />` tells your app to render the HTML with 
`/portal/portal-web/docroot/html/taglib/aui/fieldset/lexicon/`, instead of the 
`end.jsp` and `start.jsp` files found in 
`/portal/portal-web/docroot/html/taglib/aui/fieldset/`. 

$$$

For example, the 
[Liferay's Trash app](https://github.com/liferay/liferay-portal/blob/master/modules/apps/web-experience/trash/trash-web/src/main/resources/META-INF/resources/navigation.jsp) 
uses `markupView="lexicon"` with its `nav-bar`: 

    <aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">

Sweetness! Now you know how to style a navigation bar with Lexicon. Next, you'll 
learn about the Management Bar. 

### Adding the Management Bar [](id=adding-the-management-bar)

The Management Bar lets users change the view the app displays content with. For 
example, a user can use the Management Bar to display content in a list or a 
grid, or display only a specific type of content. You can also customize your 
app's Management Bar. The Management Bar in Liferay's Message Boards Admin 
portlet is shown here: 

![Figure x: The Management Bar lets the user customize how the app displays content.](../../images/message-boards-management-bar.png)

The Management Bar is divided into a few key sections. Each section is grouped 
and configured using different taglibs. For example, here's the Mangement Bar 
configuration in Liferay's Trash app: 

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

The `<liferay-frontend:management-bar-buttons>...<.../>` tag wraps the 
Management Bar button elements: 

![Figure x: The `management-bar-buttons` tag contains the Management Bar's main buttons.](../../images/management-bar-buttons.png) 

The `<liferay-frontend:management-bar-sidenav-toggler-button.../>` tag renders 
a slide out navigation, for the info button in this case. The `info-circle` icon 
is specified as the `icon` for the button. You can choose from a selection of 
Lexicon icons for your app's button. More on this later.

The `<liferay-frontend:management-bar-display-buttons.../>` tag renders the
display style options for the app:

![figure x: The management bar display buttons tag contains the display options for the content.](../../images/management-bar-display-buttons.png)

Finally, the `<liferay-frontend:management-bar-filters>...<.../>` tag wraps the
filter options for the app:

![figure x: The management bar filters tag contains the filter options for the content.](../../images/management-bar-filters.png)

When the app doesn't contain any content, all the buttons, except for 
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
app.

## Using Lexicon Icons in Your App [](id=using-lexicon-icons-in-your-app)

You may be updating your app to @product@ 7, or perhaps you are writing
a new app for @product@ 7. In either case, you can follow the process 
below to use Lexicon's icons in your app.

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
app:

    <liferay-ui:icon
    
        icon="icon-name"
        
        markupView="lexicon"
        
        message="message-goes-here"
    
    />

Note the addition of the `markupView="lexicon"` attribute, which ensures that 
Lexicon markup is used to render the HTML.

That's all you need to do to use Lexicon icons in your app.

As you can see, the Lexicon design Experience language has a lot to offer. If
you leverage Lexicon markup in your apps, you can ensure a consistent
user experience that your users will be pleased with.

## Related Topics [](id=related-topics)


