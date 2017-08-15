# Applying Clay Patterns to Your Forms and Navigation [](id=applying-clay-patterns-to-your-forms-and-navigation)

This tutorial covers how you can leverage Clay patterns in your app's forms and 
navigation to make them more user-friendly.

You can learn how to update your navigation next.

## Applying Clay to the Navigation Bar [](id=applying-lexicon-to-the-navigation-bar)

<!-- I'm guessing this is not a requirement, and something that is only intended 
for internal use. A 3rd party dev could of course follow this pattern for their 
admin apps, right? Would this break any functionality if they didn't? -->

All administration apps in @product-ver@ have a navigation bar. If your app is
intended for administrative use, your app should use a navigation bar as well.
Applying Clay to your existing navigation bar takes only one additional
attribute.

If your app already has a navigation bar implemented with the [`aui:nav-bar`](@platform-ref@/7.0-latest/taglibs/util-taglib/aui/nav-bar.html)
tag, you can reuse it by adding the attribute `markupView="lexicon"`.

For example, [Liferay's Trash app](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/web-experience/trash/trash-web/src/main/resources/META-INF/resources/navigation.jsp)
uses `markupView="lexicon"` with its `nav-bar`:

    <aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">

+$$$

**Note:** The `markupView="lexicon"` attribute ensures that the Clay markup
is used for the UI components, rather than the standard markup. This attribute
tells the app to use the `lexicon` folder for the taglib to render the HTML,
rather than the default pages. For example, using
`<aui:fieldset markupView="lexicon" />` tells your app to render the HTML with
`/portal/portal-web/docroot/html/taglib/aui/fieldset/lexicon/`, instead of the
`end.jsp` and `start.jsp` files found in
`/portal/portal-web/docroot/html/taglib/aui/fieldset/`.

$$$

Sweet! Now you know how to style a navigation bar with Clay. Next, you'll learn 
how to apply Clay to your forms.

## Applying Clay Patterns to the Application Body

To ensure that your application is using all the available screen real state, 
from left to right, you should make the application body fluid in all portlet 
views. This helps provide a consistent user-experience across all views of your 
app.

To make your app's content fluid add the `container-fluid-1280` class in a 
`<div>` (or equivalent) element that contains all the content of the portlet 
(excluding the nav bar and management bar).

If your app's view (or views) are already contained within a `<div>` element 
just add the `container-fluid-1280` class to it. Otherwise add an uppermost 
`<div>` element for this purpose:

    <div class="container-fluid-1280">
    ...
    </div>
    
Next, you can learn how to apply Clay to your forms.

## Improving your Forms with Clay [](id=improving-your-forms-with-lexicon)

Follow these steps to apply Clay to your forms:

1.  Encapsulate your fieldsets with the following taglib:

        <aui:fieldset-group markupView="lexicon">

        </aui:fieldset-group>

2.  The fieldset inside `fieldset-group` should be collapsible, so you can hide 
    it when it's not being used. Add the `collapsed` and `collapsible` 
    attributes to your `aui:fieldset` taglib:

        <aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>"
        label="permissions">
              ...
        </aui:fieldset>

3.  Finally, add the `btn-lg` CSS class to your form's buttons to increase the 
    click area:

        <aui:button-row>

            <aui:button cssClass="btn-lg" type="submit" />

            <aui:button cssClass="btn-lg" href="<%= redirect %>"
            type="cancel" />

        </aui:button-row>

Your forms are now configured to use Clay!

## Applying Clay to Your Actions Menu

Your Actions menus can also benefit from Clay patterns. You can learn how to 
apply Clay patterns to your Admin app's actions in the 
[Configuring Your Admin app's Actions Menu](/develop/tutorials/-/knowledge_base/7-0/configuring-your-admin-apps-actions-menu)
Follow these steps to update the other Actions menu:

1.  Open your module's actions JSP (`guestbook_actions.jsp` for example) and 
    update the `<liferay-ui:icon-menu>` to use Lexicon's Clay markup with the 
    `markupView` attribute:

        <liferay-ui:icon-menu
            direction="left-side"
            icon="<%= StringPool.BLANK %>"
            markupView="lexicon"
            message="<%= StringPool.BLANK %>"
            showWhenSingleIcon="<%= true %>"
        >

2.  To follow the Clay guidelines, the Actions menu should only display an icon
    if it is one action. If the Actions menu contains multiple actions, the 
    icons (`image` attribute) should be removed, leaving a `message` attribute 
    that displays the action's title, as shown in the example below:
    
        <liferay-ui:icon
            message="Edit"
            url="<%= editURL.toString() %>"
        />
        
## Applying Clay to your Search iterator

To apply Clay to your search iterator you will just need to add one
attribute. Using the existing `liferay-ui:search-iterator` tag, you can add the
`markupView="lexicon"` attribute:

    <liferay-ui:search-iterator

        displayStyle="<%= displayStyle %>"
        markupView="lexicon"
        searchContainer="<%= searchContainer %>"
    />

The `displayStyle` attribute is set to the `displayStyle` var which is set by 
the management bar display style buttons.

If the results contain different set of entries (folders and documents,
categories and threads, etc.) you will need to use a ResultRowSplitter to
divide the results. The result row splitter is an attribute that is
provided to the `liferay-ui:search-iterator` taglib as shown below:

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
<!-- provide a full example -->

## Related topics

