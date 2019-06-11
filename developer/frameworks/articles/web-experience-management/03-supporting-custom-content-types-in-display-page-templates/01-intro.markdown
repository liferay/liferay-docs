# Supporting Custom Content Types in Display Page Templates

Display Page Templates can display several types of content out-of-the-box:

- Web Content Article
- Document
- Blogs Entry

You can publish these content types in highly customizable ways using 
[Page Fragments](/docs/7-2/frameworks/-/knowledge_base/f/page-fragments). 
You can use Display Page Templates to map fields of certain content (e.g., Web
Content) to fields defined in a Page Fragment. Then you can publish the content
on a page using the Page Fragment as a template. To see an example of how
Display Page Templates work, see the
[Display Page Template Example](/docs/7-2/user/-/knowledge_base/u/display-page-template-example).

If you want to extend the Display Page Templates framework to support other
content types, you must complete the following steps:

1.  Provide basic information about your custom content type.

2.  Provide your content type's fields so they're configurable in the Display
    Page Template Editor.

3.  Provide friendly URLs for your display page template, and handle the
    information that the user is requesting.

4.  Obtain a specific content type model instance by its primary key.

You'll step through how to provide this information to the Display Page Template
framework next.























<!--
## Asset Display Page Taglib

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

### Display Page Attributes

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

`displayPageType (int)`: 1 = Default, 2 = Specific, 3 = None

`assetDisplayPageId (long)`: ID of selected Asset Display Page

`layoutUuid`: Layout UUID in case of a portlet page with default Asset 
Publisher
-->
