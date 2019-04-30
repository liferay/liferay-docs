---
header-id: integrating-display-pages-into-asset-creation
---

# Integrating Display Pages into Asset Creation

[TOC levels=1-4]

After you add support for Asset Display Pages in your custom entities,
you can integrate display page configuration into your entity's creation form.

## Asset Display Page Taglib examples

To provide the Asset Display Page selector for Bookmarks after you 
[created fields for it](/docs/7-2/frameworks/-/knowledge_base/f/creating-an-asset-display-contributor),

1.  Open `.../META-INF/resources/bookmarks/edit_entry.jsp`.

2.  Add this code in the appropriate place in the layout to add the Asset 
    Display Page selector:

        <liferay-asset:select-asset-display-page
            	classNameId="<%= PortalUtil.getClassNameId(BookmarksEntry.class) %>"
            	classPK="<%= entryId %>"
            	groupId="<%= scopeGroupId %>"
         />

Now, a selector is available to define a default Asset Display Page when 
editing or creating a Bookmarks Entry.

![Figure 1: You need to add the Display Page selection to your asset's create/edit page to define the Display Page for each instance of that asset.](../../../images/display-pages-select-default-display-page.png)
