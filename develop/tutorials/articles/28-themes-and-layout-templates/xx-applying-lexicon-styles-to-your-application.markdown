# Applying Lexicon Styles to your App [](id=applying-lexicon-styles-to-your-app)

It’s important to have a consistent user experience across your apps. Liferay's
built-in apps achieve this by using the
[Lexicon Experience Language](/participate/liferaypedia/-/wiki/Main/Lexicon).
Lexicon is optimized to provide you with a consistent, user-friendly UI for your
Liferay apps.

The Lexicon Experience Language is included in all themes based on the `_styled`
base theme. The `_styled` base theme uses Bootstrap with a Lexicon base, giving
you access to all the components documented on the [Lexicon](lexicon site url)
site. This means that you can use Lexicon markup and components in your custom
Liferay apps.

This tutorial shows you how to:

- leverage Liferay's taglibs to create a consistent UI
- set search container animations
- use Lexicon to improve your app's forms
- use Lexicon icons in your app

First, you'll learn how to leverage Liferay's taglibs in your app.

## Leveraging Liferay’s Taglibs to Create a Pleasant UI [](id=leveraging-liferays-taglibs-to-create-a-pleasant-ui)

Liferay's `liferay-frontend` taglibs streamline the process of writing Lexicon
markup, letting you focus your efforts on creating a pleasant user experience.
The frontend taglibs give you access to several UI components that you can
leverage in your app's design. The sections that follow outline some of the
available components that you can use in your app's JSPs.

### Applying the Add Button Pattern [](id=applying-the-add-button-pattern)

If your app requires an Actions menu, or perhaps a button that opens a different
view (like an edit screen), you may wish to use the add button pattern. This
pattern gives you a clean, minimal UI for such a button. You can use it in any
of your app's screens. The add button pattern consists of an `add-menu` tag and
at least one `add-menu-item` tag.

![Figure 1: The add button pattern consists of an `add-menu` tag and at least one `add-menu-item` tag.](../../images/add-button-diagram.png)

If there's only one item, the plus icon acts as a button that triggers the item.
If there's more than one item, clicking the plus icon displays a menu containing
them.

Here's an example of the add button pattern with a single item:

    <liferay-frontend:add-menu>
        <liferay-frontend:add-menu-item title='<%= LanguageUtil.get(request,
        "titleName") %>' url="<%= nameURL.toString() %>" />
    <liferay-frontend:add-menu>

You can also find the add button pattern in Liferay's built-in apps. For
example, the [Message Boards Admin portlet](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/collaboration/message-boards/message-boards-web/src/main/resources/META-INF/resources/message_boards_admin/add_button.jsp)
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

If your app already has a navigation bar implemented with the `aui:nav-bar`
tag, you can reuse it by adding the attribute `markupView=”lexicon”`.

For example, the
[Liferay's Trash app](https://github.com/liferay/liferay-portal/blob/master/modules/apps/web-experience/trash/trash-web/src/main/resources/META-INF/resources/navigation.jsp)
uses `markupView="lexicon"` with its `nav-bar`:

    <aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">

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

Sweetness! Now you know how to style a navigation bar with Lexicon. Next, you'll
learn about the Management Bar.

### Adding the Management Bar [](id=adding-the-management-bar)

The Management Bar lets users change the view the app displays content with. For
example, a user can use the Management Bar to display content in a list or a
grid, or display only a specific type of content. You can also customize your
app's Management Bar. The Management Bar in Liferay's Message Boards Admin
portlet is shown here:

![Figure 2: The Management Bar lets the user customize how the app displays content.](../../images/message-boards-management-bar.png)

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

The `<liferay-frontend:management-bar-buttons>` tag wraps the Management Bar's
button elements:

![Figure 3: The `management-bar-buttons` tag contains the Management Bar's main buttons.](../../images/management-bar-buttons.png)

The `<liferay-frontend:management-bar-sidenav-toggler-button>` tag implements
slide-out navigation for the info button.

The `<liferay-frontend:management-bar-display-buttons>` tag renders the app's
display style options:

![Figure 4: The `management-bar-display-buttons` tag contains the content's display options.](../../images/management-bar-display-buttons.png)

The `<liferay-frontend:management-bar-filters>` tag wraps the app's
filtering options. This filter should be included in all control panel
applications. Filtering options can include sort criteria, sort ordering, and
more:

![Figure 5: The `management-bar-filters` tag contains the content filtering options.](../../images/management-bar-filters.png)

Finally, the `<liferay-frontend:management-bar-action-buttons>` tag wraps the
actions that you can execute over selected items. In @product@ 7, you can select
multiple items between pages. The management bar keeps track of the number of
selected items for you:

![Figure X: The management bar keeps track of the items selected and displays the actions to execute on them.](../../images/management-bar-action-buttons.png)

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

## Setting Search Container Animations

If you've taken a tour around @product@ 7's UI, you've probably noticed some new
animations in the search containers.

These animations are used to let the user know that there is no available
content. To use these animations in your app you can use the following method:

    SearchContainer.setEmptyResultsMessageCssClass()

There are three built-in classes that you can choose from for the animation:

"taglib-empty-result-message-header" which is the default one

![Figure x: ](../../images/no-content-found-blog.png)

"taglib-empty-search-result-message-header" used when searching

![Figure x: ](../../images/no-web-content-found-search.png)

"taglib-empty-result-message-header-has-plus-btn" used when there is a plus
button:

![Figure x: ](../../images/no-tags-found-plus-button.png)

For example, the Roles Admin portlet uses the following code to set its
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

You can use the `liferay-ui:icon` taglib. For example:

    <liferay-ui:icon

        icon="icon-name"

        markupView="lexicon"

        message="message-goes-here"

    />

You can also use the `aui:icon` taglib. For example:

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

[Frontend Customizations](/develop/tutorials/-/knowledge_base/7-0/frontend-customizations)

[Themes and Layout Templates](/develop/tutorials/-/knowledge_base/7-0/themes-and-layout-templates)
