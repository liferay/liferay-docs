# Applying Lexicon Patterns to Your Forms, Navigation, and Search [](id=applying-lexicon-patterns-to-forms-navigation-and-search)

This tutorial covers how you can leverage Lexicon patterns in your app's forms, 
navigation, and search results, to make them more user-friendly.

You can learn how to update your navigation next.

## Applying Lexicon to the Navigation Bar [](id=applying-lexicon-to-the-navigation-bar)

All administration apps in @product-ver@ have a navigation bar. If your app is 
intended for administrative use, you can use a navigation bar as well. Applying 
Lexicon to your existing navigation bar takes only one additional attribute. 

If your app already has a navigation bar implemented with the [`aui:nav-bar`](@platform-ref@/7.0-latest/taglibs/util-taglib/aui/nav-bar.html)
tag, you can reuse it by adding the attribute `markupView="lexicon"`.

For example, [Liferay's Trash app](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/web-experience/trash/trash-web/src/main/resources/META-INF/resources/navigation.jsp)
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

Alternatively, you can use non-bordered tabs with the `liferay-ui:tabs` taglib as the [Lexicon Guidelines](https://lexicondesign.io/docs/patterns/nav%20tabs.html) state. 

Sweet! Now you know how to style a navigation bar with Lexicon. Next, you'll 
learn how to apply Lexicon to your forms. 

## Applying Lexicon Patterns to the Application Body

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

Next, you can learn how to apply Lexicon to your forms.

## Improving your Forms with Lexicon [](id=improving-your-forms-with-lexicon)

Follow these steps to apply Lexicon to your forms:

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

Your forms are now configured to use Lexicon! Next, you can learn how to apply 
Lexicon to the Actions menu for your entities. 

## Applying Lexicon to Your Entity's Actions Menus

Your Actions menus can also benefit from Lexicon patterns. You can learn how 
to apply Lexicon patterns to your Admin app's actions in the 
[Configuring Your Admin app's Actions Menu](/develop/tutorials/-/knowledge_base/7-0/configuring-your-admin-apps-actions-menu) 
tutorial. Follow these steps to update Actions menu for your entities 
(such as the ones listed next to search results):

1.  Open your module's actions JSP (`guestbook_actions.jsp` for example) and 
    update the `<liferay-ui:icon-menu>` to use Lexicon's markup with the 
    `markupView` attribute:

        <liferay-ui:icon-menu
            direction="left-side"
            icon="<%= StringPool.BLANK %>"
            markupView="lexicon"
            message="<%= StringPool.BLANK %>"
            showWhenSingleIcon="<%= true %>"
        >

2.  To follow the Lexicon guidelines, the Actions menu should only display an 
    icon if it is one action. If the Actions menu contains multiple actions, 
    remove the icon's `image` attribute and replace it with the `message` 
    attribute displaying the action's title. Below is an example configuration:

        <liferay-ui:icon
            message="Edit"
            url="<%= editURL.toString() %>"
        />

Next you can update your search iterator.

## Applying Lexicon to your Search iterator

To apply Lexicon to your search iterator, add the `markupView="lexicon"` 
attribute:

    <liferay-ui:search-iterator

        displayStyle="<%= displayStyle %>"
        markupView="lexicon"
        searchContainer="<%= searchContainer %>"
    />

The `displayStyle` attribute specifies which display style is set for the 
management bar. You can learn how to configure display styles in the 
[Implementing Management Bar Display Styles](/develop/tutorials/-/knowledge_base/7-0/implementing-the-management-bar-display-styles) 
tutorial.

If the results contain different sets of entries (folders and documents, 
categories and threads, etc.) you must use a `*ResultRowSplitter` to divide the 
results. This is covered next. <!-- Is this a Lexicon Design pattern? -->

### Creating a Results Row Splitter

The `*ResultRowSplitter` class is responsible for dividing and categorizing the 
results based on the different entry types. Follow these steps to create a 
result row splitter:

1.  Create a java class that implements the [`ResultRowSplitter` interface](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/search/ResultRowSplitter.html).
For example, the `com.liferay.bookmarks.web` module has the following 
`BookmarksResultRowSplitter` class to split its folder and bookmark results:

    public class BookmarksResultRowSplitter implements ResultRowSplitter {

2.  Override the `split()` method:

    	@Override
    	public List<ResultRowSplitterEntry> split(List<ResultRow> resultRows) {
    		List<ResultRowSplitterEntry> resultRowSplitterEntries =
    			new ArrayList<>();

3.  Create an `ArrayList` for each type of entity, as shown in the example below:

    		List<ResultRow> entryResultRows = new ArrayList<>();
    		List<ResultRow> folderResultRows = new ArrayList<>();

4.  Loop through the results and add your entities to the proper `ArrayList`:

        for (ResultRow resultRow : resultRows) {
        	Object object = resultRow.getObject();

        	if (object instanceof BookmarksFolder) {
        		folderResultRows.add(resultRow);
        	}
        	else {
        		entryResultRows.add(resultRow);
        	}
        }

5.  Create a new `ResultRowSplitterEntry` for each entity, passing the name of 
    the entity and the ArrayList:

        if (!folderResultRows.isEmpty()) {
        	resultRowSplitterEntries.add(
        		new ResultRowSplitterEntry("folders", folderResultRows));
        }

        if (!entryResultRows.isEmpty()) {
        	resultRowSplitterEntries.add(
        		new ResultRowSplitterEntry("bookmarks", entryResultRows));
        }

6.  Return the List of resultRowSplitter Entries.

7.  Use the `resultRowSplitter` attribute in your `liferay-ui:search-iterator` 
    taglib to create a new instance of your `*ResultRowSplitter` as shown in the 
    example below:

        <liferay-ui:search-iterator
            displayStyle="<%= displayStyle %>"
            markupView="lexicon"
            resultRowSplitter="<%= new BookmarksResultRowSplitter() %>"
            searchContainer="<%= bookmarksSearchContainer %>"
        />

Now you know how to apply Lexicon patterns to your app's forms, navigation, and 
search results!

## Related topics

[Configuring Your Application's Title and Back Link](/develop/tutorials/-/knowledge_base/7-0/configuring-your-applications-title-and-back-link)

[Using Lexicon Icons in Your App](/develop/tutorials/-/knowledge_base/7-0/using-lexicon-icons-in-your-app)