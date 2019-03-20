# Integrating Display Pages into Asset Creation

After you add support for 
[Asset Display Pages in your custom entities](/develop/tutorials/-/knowledge_base/7-1/creating-a-display-contributor), 
you can integrate display page configuration into your entity's creation form.

## Asset Display Page Taglib

Once you have created an Asset Display Page associated with your Asset type, 
you can add the option to select an Asset Display Page in the form where you
create the Asset. The `<liferay-asset:select-asset-display-page />` taglib
renders a form field which allows you to select an Asset Display Page for
the specific asset type.

![Figure 1: You need to add the Display Page selection to your asset's create/edit page to define the Display Page for each instance of that asset.](../../../images/display-pages-select-default-display-page.png)

There are three options when selecting a display page:

* The default display page for the asset type if one has been configured.

* Any other selectable display page.

* None

If you select no default display page, when the default is selected, a display
page is not defined. 

### Display Page Attributes

When you use the display page selector taglib, you can define the following 
attributes:

* `classNameId (long)` (required): a class name ID of the asset type to 
  select an asset display page for.

* `classPK (long)`: a primary key of the asset entry to select an asset 
  display page for.

* `classTypeId (long)`: a class type ID of the asset type to select an 
  asset display page for.

* `eventName (String)`: event name which fires when user selects a display 
    page using item selector.

* `groupId (long)` (required): a group ID of the entity to select an asset 
    display page for.

* `showPortletLayouts (boolean)`: allow selecting pages with Asset 
  Publisher configured as a default Asset Publisher for the page.

The attribute `showPortletLayout` provides backwards compatibility for display 
pages created for Journal Articles in older versions. When `showPortletLayouts`
is set to true, you can select any public or private pages with an Asset
Publisher widget on it configured as the *Default Asset Publisher for the
page*.

When submitting a form with the taglib, it populates the request with the 
following parameters:

* `displayPageType (int)`: 1 = Default, 2 = Specific, 3 = None

* `assetDisplayPageId (long)`: ID of selected Asset Display Page

* `layoutUuid`: Layout UUID in case of a portlet page with default Asset 
  Publisher

### Asset Display Page Taglib examples

If you wanted to provide the Asset Display Page selector for Blogs after you 
[created fields for it](/develop/tutorials/-/knowledge_base/7-1/creating-a-display-contributor),
you could add this code in the Blogs `edit_entry.jsp` to add the Asset Display
Page selector:

      <liferay-asset:select-asset-display-page
            	classNameId="<%= PortalUtil.getClassNameId(BlogsEntry.class) %>"
            	classPK="<%= entryId %>"
            	groupId="<%= scopeGroupId %>"
         />

If you wanted to provide a selector which could use any page with a properly 
configured Asset Publisher as a display page, you would create it like this:

    <%
    EditArticleDisplayPageDisplayContext editArticleDisplayPageDisplayContext = 
    new EditArticleDisplayPageDisplayContext(request);
    %>

    <liferay-asset:select-asset-display-page
      	classNameId="<%= editArticleDisplayPageDisplayContext.getClassNameId() %>"
      	classPK="<%= editArticleDisplayPageDisplayContext.getClassPK() %>"
      	classTypeId="<%= editArticleDisplayPageDisplayContext.getDDMStructureId() %>"
      	groupId="<%= editArticleDisplayPageDisplayContext.getGroupId() %>"
      	showPortletLayouts="<%= true %>"
    />
 
<!-- Show what you just did. Make it make sense. As is, I can't even picture what this is doing. -Rich --> 

