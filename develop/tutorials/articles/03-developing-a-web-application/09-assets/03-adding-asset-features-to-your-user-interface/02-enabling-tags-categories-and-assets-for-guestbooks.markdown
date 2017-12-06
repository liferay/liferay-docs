# Enabling Tags, Categories, and Related Assets for Guestbooks [](id=enabling-tags-categories-and-related-assets-for-guestbooks)

<div class="learn-path-step">
    <p>Adding Asset Features to Your UI<br>Step 3 of 5</p>
</div>

Since you already asset-enabled guestbooks at the service layer, guestbook 
entities can now use @product@'s back-end support for tags and categories. All
that's left is to enable tags and categories in the UI. In this step, you'll 
update the Guestbook Admin portlet's `edit_guestbook.jsp` so admins can add,
edit, or remove tags and categories when adding or updating a guestbook. 

## Enabling Asset Features [](id=enabling-asset-features)

Follow these steps: 

1.  In the `guestbook-web` module's `/guestbookadminportlet/edit_guestbook.jsp`, 
    add the tags `<liferay-ui:asset-categories-error />` and 
    `<liferay-ui:asset-tags-error/>` to the `aui:form` below the closing 
    `</aui:fieldset>` tag: 

        <liferay-ui:asset-categories-error />
        <liferay-ui:asset-tags-error />

    These tags display error messages if an error occurs with the tags or 
    categories submitted in the form. 

2.  Below the error tags, add a `<liferay-ui:panel>` tag with the following
    attributes: 

        <liferay-ui:panel defaultState="closed" extended="<%= false %>"
          id="guestbookCategorizationPanel" persistState="<%= true %>"
          title="categorization">

        </liferay-ui:panel>

    The `<liferay-ui:panel>` tag generates a collapsible section. 

3.  Add input fields for tags and categories inside the panel section you just 
    created. Specify the `assetCategories` and `assetTags` types for the 
    `<aui:input />` tags to tell @product@ that these input tags represent asset
    categories and asset tags. You can group related input fields together with 
    an `<aui:fieldset>` tag. @product@ shows the appropriate selectors for tags
    and categories and displays the tags and categories that have already been 
    added to the guestbook: 

        <aui:fieldset>
          <aui:input name="categories" type="assetCategories" />

          <aui:input name="tags" type="assetTags" />
        </aui:fieldset>

4.  Add a second `<liferay-ui:panel>` tag under the existing one. In this new 
    tag, add an `<aui:fieldset>` tag containing a `<liferay-ui:asset-links>` 
    tag. To display the correct asset links (the selected guestbook's related 
    assets), set the `className` and `classPK` attributes: 

        <liferay-ui:panel defaultState="closed" extended="<%= false %>"
          id="guestbookAssetLinksPanel" persistState="<%= true %>"
          title="related-assets">
          <aui:fieldset>
            <liferay-ui:input-asset-links
              className="<%= Guestbook.class.getName() %>"
              classPK="<%= guestbookId %>" />
          </aui:fieldset>
        </liferay-ui:panel>

Test the updated `edit_guestbook.jsp` page by navigating to the Guestbook Admin 
portlet in the Control Panel and clicking *Add Guestbook*. You'll see a field 
for adding tags and a selector for selecting related assets.

![Figure 1: Once you've updated your Guestbook Admin portlet's `edit_guestbook.jsp` page, you'll see forms for adding tags and selecting related assets.](../../../../images/guestbook-tags-related-assets.png)

Don't do anything with these fields yet, because you're not done implementing 
assets. Next, you'll enable tags and categories for guestbook entries. 
