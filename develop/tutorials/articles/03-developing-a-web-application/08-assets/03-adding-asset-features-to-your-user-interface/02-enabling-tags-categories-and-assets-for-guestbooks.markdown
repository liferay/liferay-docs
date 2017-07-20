# Enabling Tags, Categories, and Related Assets for Guestbooks [](id=enabling-tags-categories-and-related-assets-for-guestbooks)

Since you've already asset-enabled guestbooks at the service layer, your
guestbook entities are all set to take advantage of Liferay's back-end support
for tags and categories. Your only remaining task is to update your user
interface to allow access to these features. Recall that you've designed your
application to allow users to add guestbooks from two different portlets: the
Guestbook portlet and the Guestbook Admin portlet. In this section, you'll
update the form on the Guestbook Admin portlet's `edit_guestbook.jsp` page to
allow admins to add, edit, or remove tags and categories when adding or updating
a guestbook. You'll use Liferay-UI and AUI JSP tags to quickly create the UI. 
For simplicity's sake, you'll leave the Guestbook portlet's `edit_guestbook.jsp` 
page alone. (Of course, nothing is preventing you from adding tags and 
categories functionality to the Guestbook portlet's `edit_guestbook.jsp` except 
a design decision.) You'll use Liferay-UI and AUI JSP tags to add tags, 
categories, and related assets to the form for adding or updating a guestbook.

## Enabling Asset Features

Follow these steps to enable these features: 

1.  Open the `guestbook-web` module's 
    `/guestbookadminmvcportlet/edit_guestbook.jsp` file and add the
    `<liferay-ui:asset-categories-error />` and `<liferay-ui:asset-tags-error/>` 
    tags to the form. These tags are responsible for displaying custom error 
    messages that appear if an error occurs with the categories or tags that are
    submitted on the form. Add the following code to the `aui:form` below 
    the closing `</aui:fieldset>` tag:

        <liferay-ui:asset-categories-error />
        <liferay-ui:asset-tags-error />
                        
2.  Next add a `<liferay-ui:panel>` tag with the following attributes set. The 
    `<liferay-ui:panel>` tag generates a collapsible section:

        <liferay-ui:panel defaultState="closed" extended="<%= false %>"
          id="guestbookCategorizationPanel" persistState="<%= true %>"
          title="categorization">

        </liferay-ui:panel>

3.  Add input fields for tags and categories inside the panel section you just 
    created. Specify the `assetCategories` and `assetTags` types for the 
    `<aui:input />` tags to tell Liferay that these input tags represent asset 
    categories and asset tags. You can group related input fields together with 
    an `<aui:fieldset>` tag. Liferay shows the appropriate selectors for tags 
    and categories and displays the tags and categories that have already been 
    added to the guestbook:

        <aui:fieldset>
          <aui:input name="categories" type="assetCategories" />

          <aui:input name="tags" type="assetTags" />
        </aui:fieldset>

4.  Add a second `<liferay-ui:panel>` tag under the existing one. Inside of it 
    add an `<aui:fieldset>` tag containing a `<liferay-ui:asset-links>` tag. You 
    have to specify values for the `className` and `classPK` attributes in order 
    for the correct asset links (the related assets corresponding to the 
    selected guestbook) to be displayed:

        <liferay-ui:panel defaultState="closed" extended="<%= false %>"
          id="guestbookAssetLinksPanel" persistState="<%= true %>"
          title="related-assets">
          <aui:fieldset>
            <liferay-ui:input-asset-links
              className="<%= Guestbook.class.getName() %>"
              classPK="<%= guestbookId %>" />
          </aui:fieldset>
        </liferay-ui:panel>

Test your updated `edit_guestbook.jsp` page by navigating to your Guestbook
Admin portlet in the Control Panel and clicking on *Add Guestbook*. You'll see a
field for adding tags and a selector for selecting related assets.

![Figure 2: Once you've updated your Guestbook Admin portlet's `edit_guestbook.jsp` page, you'll see forms for adding tags and selecting related assets.](../../../../images/guestbook-tags-related-assets.png)

## Testing Asset Features

Now that you've enabled some asset features for guestbooks. You can try them out.
When you create or edit a guestbook in the admin portlet, where is the field for 
selecting categories? It's been enabled but it won't appear until you create a 
vocabulary and add at least one category to it.

Follow these steps to create a vocabulary:

1.  Open the *Control Menu* and select *Content* &rarr; *Categories* under the 
    site menu.

2.  Click the Add button to create a new vocabulary.

3.  Once you've created a new vocabulary, it appears in the table on the main 
    page. Click the Actions Menu next to the vocabulary and select 
    *Add Category*.
    
4.  Fill in the form and click *Save*.

5.  Now go back to the Guestbook Admin portlet, click on *Add Guestbook* or 
    *Actions* &rarr; *Edit* next to a guestbook and confirm that categories are 
    selectable.

Follow these steps to further test your entities' integration with Liferay's 
asset framework:

1.  Add the Tags Navigation, Tag Cloud, and Categories Navigation portlets to 
    the page with the Asset Publisher portlet. All of tags that you've created 
    appear in the Tags Navigation and Tag Cloud portlet. All of the categories 
    that you've created appear in the Categories Navigation portlet.
    
2.  Click on a tag name or category name in any of the portlets that you added. 

3.  Check that the Asset Publisher dynamically displays only assets with the 
    selected tag or category.

This mechanism works by means of public render parameters. The Tags Navigation,
Tag Cloud, and Categories Navigation portlets publish a `tag` or a `categoryId`
render parameter and the Asset Publisher reads the parameter and uses it to
dynamically determine which assets to display.

You should also test the Related Assets feature. To do so, follow these steps:

1.  Create a guestbook and, say, a web content article.

2.  Then select one asset as a related asset of the other and click *Save*.
    
3.  Alternatively, you can create two guestbooks and add one as a related asset 
    of the other.

    +$$$

    **Warning:** When you click on *Related Assets* from the Guestbook Admin
    portlet's Add/Update Guestbook form, the fully qualified Java class name of your
    entity appears in the list instead of just *Guestbook*. This is easy to fix by
    adding a language key to your guestbook-portlet project. See the [Overriding Language Keys](/develop/tutorials/-/knowledge_base/7-0/overriding-language-keys)
    tutorial for details about modifying and creating language keys.

    $$$

4.  Asset links represent a reciprocal relationship. If one asset is a related 
    asset of a second, the second is a related asset of the first. Check this 
    for the assets that you linked together.
    
Next you can enable these features for guestbook entries.