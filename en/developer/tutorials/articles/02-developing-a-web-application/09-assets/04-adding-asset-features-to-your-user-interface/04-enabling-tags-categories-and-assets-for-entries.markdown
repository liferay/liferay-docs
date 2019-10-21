---
header-id: enabling-tags-categories-and-related-assets-for-guestbook-entries
---

# Enabling Tags, Categories, and Related Assets for Guestbook Entries

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Adding Asset Features to Your UI</p><p>Step 4 of 5</p>
</div>

Enabling tags, categories, and related assets for guestbook entries is similar 
to enabling them for guestbooks. Please refer back to the previous step for
a detailed explanation. 

Open your `guestbook-web` module's `guestbook/edit_entry.jsp` file. 
You'll add two pieces of code: a header for navigation and a panel for tags and
categories similar to the one you added to the `edit_guestbook.jsp` file. 

1.  Add the header after the `addEntry` action URL tag: 

    ```markup
    <liferay-ui:header
        backURL="<%= viewURL.toString() %>"
        title="<%= entry == null ? "Add Entry" : entry.getName() %>"
    />
    ```

2. Add the asset tags/categories/links in a collapsible panel after the closing
   `</aui:fieldset>`: 

   ```markup
<liferay-asset:asset-categories-error />
<liferay-asset:asset-tags-error />

<liferay-ui:panel defaultState="closed" 
                  extended="<%= false %>" id="entryCategorizationPanel" 
                  persistState="<%= true %>" title="categorization">

    <aui:fieldset>
       <liferay-asset:asset-categories-selector className="<%= GuestbookEntry.class.getName() %>" classPK="<%= entryId %>" />
       <liferay-asset:asset-tags-selector className="<%= GuestbookEntry.class.getName() %>" classPK="<%= entryId %>" />
    </aui:fieldset>

</liferay-ui:panel>

<liferay-ui:panel defaultState="closed" 
                  extended="<%= false %>" 
                  id="entryAssetLinksPanel" 
                  persistState="<%= true %>" 
                  title="related-assets">

    <aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" label="related-assets">
            
        <liferay-asset:input-asset-links
            className="<%= GuestbookEntry.class.getName() %>"
            classPK="<%= entryId %>"
        />
            
    </aui:fieldset>
</liferay-ui:panel>
```

Test your JSP by using the Guestbook portlet to add and update Guestbook 
entries. Add and remove tags, categories, and related assets. 

| **Note:** Setting your custom asset as the *Main Asset* of a page is
| required to display related assets in the Related Assets portlet. This is done
| when creating
| [Friendly URLs](/docs/7-2/tutorials/-/knowledge_base/t/making-urls-friendlier)
| in a later step.

Well done! Next, you'll enable comments and ratings for guestbook entries. 
