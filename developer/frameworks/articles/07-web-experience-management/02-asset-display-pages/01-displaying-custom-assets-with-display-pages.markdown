# Asset Display Pages

Asset Display Pages display assets in highly customizable ways using [Page Fragments](/develop/tutorials/-/knowledge_base/7-1/developing-fragments). Out 
of the box, Liferay supports using Asset Display Pages to map the fields of 
certain assets, like Web Content, to fields defined in a Page Fragment. The 
asset can then be displayed on a page using the Page Fragment as a 
template.

For example, you could use this to have a specific design for displaying 
Web Content Articles. Web Content articles have three primary fields that an
author can enter content into, the *Title*, *Summary*, and *Body*.

![Figure X: Creating a Web Content Article with a title, summary, and body.](../../../images/display-pages-creating-default-web-content.png) 


The default display will only display the Title and Body, but you could use a 
Display Page to view the article with the Summary with the following steps:

![Figure X: The default display for a Web Content article with only the title and body.](../../../images/display-pages-default-web-content.png) 

1.  Create a Page Fragment which contains editable elements for each of the 
    entity fields that you want to display, like the title, abstract, 
    body, and images.

2.  Create a Display Page and map each of the Web Content article entity type 
    to the appropriate fields in the Page Fragment.

3.  Modify an existing article or create a new one and select your new Display 
    Page under the *Display Page* section.

4.  Links to the full content of your article will now display a Display Page 
    with the content from the article and the design and styling from the Page 
    Fragment.
    
![Figure X: The Display Page both changes the style, and displays additional fields.](../../../images/display-pages-web-content-with-display-page.png) 

In addition to doing this with the default assets, if you integrate your 
application's entities with the [Asset Framework](/developer/frameworks/-/knowledge_base/7-2/asset-framework) you can 
also integrate with Asset Display Pages to display them. To use Asset Display 
Pages with custom assets, you must:

1.  Create a Display Contributor class that extends 
    `BaseAssetDisplayContributor`.

2.  Define fields to contribute by implementing `AssetDisplayContributorFields`.

3.  Use the Asset Display Page tag library to integrate Asset Display Pages 
    into asset creation.

## Creating a Display Contributor

All the logic for Asset Display Contributors, such as reading fields and their
values, is in the `AssetDisplayContributor` class. Liferay provides a 
development pattern to use this. First create an 
`*AssetDisplayContributor` class that extends `BaseAssetDisplayContributor`.
In that class, provide the entity's class name and fields to be displayed.

The first method, `getClassName`, must return your asset type's class name. The
`getClassTypeValues` method returns a `Map` of the asset's field names and
values. Since you will use `AssetDisplayContributorFields` to
define the fields for the Display Page, the `getClassTypeValues` method can be
set to return null, and no further methods are necessary.

| Note: The `getClassTypeValues` method is still included because there is an
| alternate pattern for using Asset Display Pages that implements the 
| `AssetDisplayContributor` interface, rather than extending 
| `BaseAssetDisplayContributor`, but this is not the recommended practice.

The final class can be small and simple like this:

    @Component(immediate = true, service = AssetDisplayContributor.class)
    public class YourAppEntryAssetDisplayContributor
      extends BaseAssetDisplayContributor<BlogsEntry> {

      @Override
      public String getClassName() {
         return YourAppField.class.getName();
      }

      @Override
      protected Map<String, Object> getClassTypeValues(
         BlogsEntry assetEntryObject, Locale locale) {

         return null;
      }

    }
 
After you create the `*AssetDisplayContributor` class, you must create a Asset 
Display Contributor Field class for each asset field that you want to use with 
Display Pages.

## Defining Asset Display Contributor Fields 

Once you have created the Asset Display Contributor, the next step is to create 
the `AssetDisplayContributorFields`. To do this, you must implement the 
`AssetDisplayContributorFields` interface and reference 
`AssetDisplayContributorField.class` in your service declaration. Each field
requires its own class. You could do this for any or all of your asset's fields.

### AssetDisplayContributorFields 

The `AssetDisplayContributorFields` interface looks like this:

        public interface AssetDisplayContributorField<T> {

          public String getKey();

          public String getLabel(Locale locale);

          public String getType();

          public Object getValue(T model, Locale locale);

          }

The methods work as follows:

* `getkey()` returns the asset field name. 

* `getLabel(Locale locale)` passes in the `locale` parameter and returns the 
  language key for the field's display title.

* `getType()` returns the type for the asset field (i.e. text, image, html).

* `getValue(T model, Locale locale)` passes in the model type and a `locale` 
  and returns the field data.

Every field you want to contribute for use in an Asset Display Page must have
its own implementation of `AssetDisplayContributorFields`. For example, 
Liferay's Blogs application has contributor fields classes for Content, 
Subtitle, and Cover Image which means that if you create an Asset Display Page
for the Blogs, you would be able to customize the way that those three fields 
are displayed.

## Asset Display Page Taglib

Once you have created an Asset Display Page associated with your Asset type, 
you can add the option to select an Asset Display Page in the form where you
create the Asset. The `<liferay-asset:select-asset-display-page />` taglib
renders a form field which allows you to select an Asset Display Page for
the specific asset type.

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

