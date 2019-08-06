---
header-id: asset-display-page-taglib
---

# Asset Display Page Taglib

[TOC levels=1-4]

Once you have created an Asset Display Page associated with your Asset type, 
you can add the option to select an Asset Display Page in the form where you
create the Asset. The `<liferay-asset:select-asset-display-page />` taglib
renders a form field for selecting an Asset Display Page for
your asset.

There are three options when selecting a display page:

- The default display page for the asset type if one has been configured.

- Any other selectable display page.

- None

If you select no default display page, a display page is not defined. 

## Display Page Attributes

When you use the display page selector taglib, you can define the following 
attributes:

`classNameId (long)` (required): a class name ID of the asset type to 
select an asset display page for.

`classPK (long)`: a primary key of the asset entry to select an asset 
display page for.

`classTypeId (long)`: a class type ID of the asset type to select an 
asset display page for.

`eventName (String)`: event name which fires when a user selects a display
page using the item selector.

`groupId (long)` (required): the entity's group ID to select an asset 
display page for.

`showPortletLayouts (boolean)`: allow selection of pages that have Asset
Publisher configured as a default Asset Publisher for the page.

The attribute `showPortletLayout` provides backwards compatibility for display 
pages created for Journal Articles in older versions. When `showPortletLayouts`
is set to true, you can select any public or private pages with an Asset
Publisher widget on it configured as the *Default Asset Publisher for the
page*.

When submitting a form with the taglib, it populates the request with the 
following parameters:

`displayPageType (int)`: 1 = Default, 2 = Specific, 3 = None.

`assetDisplayPageId (long)`: ID of selected Asset Display Page.

`layoutUuid`: Layout UUID in case of a portlet page with default Asset 
Publisher.
