---
header-id: enabling-tags-categories-and-related-assets-for-guestbooks
---

# Enabling Tags, Categories, and Related Assets for Guestbooks

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Adding Asset Features to Your UI</p><p>Step 3 of 5</p>
</div>

Since you already asset-enabled guestbooks at the service layer, guestbook
entities can now support tags and categories. All that's left is to enable them
in the UI. In this step, you'll update the Guestbook Admin portlet's
`edit_guestbook.jsp` so administrators can add, edit, or remove tags and
categories when adding or updating a guestbook. 

## Enabling Asset Features

Follow these steps: 

1.  In the `guestbook-web` module's `/guestbook_admin/edit_guestbook.jsp`, 
    add the tags `<liferay-asset:asset-categories-error />` and 
    `<liferay-asset:asset-tags-error/>` to the `aui:form` below the closing 
    `</aui:button-row>` tag: 

    ```markup
    <liferay-asset:asset-categories-error />
    <liferay-asset:asset-tags-error />
    ```

    These tags display error messages if an error occurs with the tags or 
    categories submitted in the form. 

2.  Below the error tags, add a `<liferay-ui:panel>` tag surrounded by
    a `<c:if>` statement: 

    ```markup
    <c:if test="<%= guestbook != null %>">

        <liferay-ui:panel defaultState="closed" extended="<%= false %>"
          id="guestbookCategorizationPanel" persistState="<%= true %>"
          title="categorization">

        </liferay-ui:panel>

    </c:if>
    ```

    The `<liferay-ui:panel>` tag generates a collapsible section. The tags
    you'll add in the next step don't work if `guestbook` is `null`, so you only
    display the panel if the current Guestbook is being edited. 

3.  Add input fields for tags and categories inside the panel section you just 
    created. Specify the `assetCategories` and `assetTags` types for the 
    `<aui:input />` tags. These input tags represent asset categories and asset
    tags. You can group related input fields together with an `<aui:fieldset>`
    tag. The tags generate the appropriate selectors for tags and categories and
    displays those that have already been added to the guestbook: 

    ```markup
	<aui:fieldset>
        <liferay-asset:asset-categories-selector className="<%= Guestbook.class.getName() %>" classPK="<%= guestbook.getGuestbookId() %>" />
		<liferay-asset:asset-tags-selector className="<%= Guestbook.class.getName() %>" classPK="<%= guestbook.getGuestbookId() %>" />
	</aui:fieldset>
    ```

4.  Add a second `<liferay-ui:panel>` tag under the existing one. In this new 
    tag, add an `<aui:fieldset>` tag containing a `<liferay-ui:asset-links>` 
    tag. To display the correct asset links (the selected guestbook's related 
    assets), set the `className` and `classPK` attributes: 

    ```markup
    <liferay-ui:panel defaultState="closed" extended="<%= false %>"
      id="guestbookAssetLinksPanel" persistState="<%= true %>"
      title="related-assets">
      <aui:fieldset>
        <liferay-asset:input-asset-links
          className="<%= Guestbook.class.getName() %>"
          classPK="<%= guestbookId %>" />
      </aui:fieldset>
    </liferay-ui:panel>
    ```

Test the updated `edit_guestbook.jsp` page by navigating to the Guestbook Admin 
portlet in the Control Panel and clicking *Add Guestbook*. After adding the
Guestbook, edit it. You'll see a field for adding tags and a selector for
selecting related assets.

![Figure 1: Once you've updated your Guestbook Admin portlet's `edit_guestbook.jsp` page, you'll see forms for adding tags and selecting related assets.](../../../../images/guestbook-tags-related-assets.png)

Don't do anything with these fields yet, because you're not done implementing 
assets. Next, you'll enable tags and categories for guestbook entries. 
