---
header-id: creating-an-asset-display-contributor
---

# Creating an Asset Display Contributor 

[TOC levels=1-4]

Now create an Asset Display Contributor for the Bookmarks application. Remember 
the two main steps:

1.  Create the Asset Display Contributor class for the asset type.

2.  Create the Asset Display Contributor Field classes for the fields you want 
    to be available for Asset Display Pages.
 
In this example, you'll create an Asset Display Contributor for Liferay's
Bookmarks application, and then you'll create one Asset Display Contributor
Field for the Bookmarks Entry Title.

## Creating the Bookmarks Contributor 

First you must create the contributor.

1.  Inside the `bookmarks-web` module, create a package called 
    `com.liferay.bookmarks.web.internal.asset.display.contributor`.

2.  In that package, create a file named
    `BookmarksEntryAssetDisplayContributor.java`.

3.  Enter the following code into the class:

        @Component(immediate = true, service = AssetDisplayContributor.class)
        public class BookmarksEntryAssetDisplayContributor
          extends BaseAssetDisplayContributor<BookmarksEntry> {

          @Override
          public String getClassName() {
             return BookmarksEntry.class.getName();
          }

          @Override
          protected Map<String, Object> getClassTypeValues(
             BookmarksEntry assetEntryObject, Locale locale) {

             return null;
          }
        }

4.  Save your file and redeploy the module.

This adds Bookmarks Entries to the list of asset types eligible for use with 
Asset Display Pages.

1.  Go to *Site Administration* &rarr; *Build* &rarr; *Pages*.

2.  Select the *Display Pages* tab.

3.  Click *Add* (![Add Display Page](../../../images/icon-add.png)) to create a new Display Page.

4.  In the Display Page editor, click *Mapping* from the menu on the right side.

5.  Select the Bookmarks Asset Type.

![Figure 1: You can now select the Bookmarks Asset Type.](../../../images/select-asset-type.png)

This isn't complete yet because while you can select the asset type, you don't 
have any fields that can be mapped.

## Creating the Bookmarks Entry Name Field Contributor 

Now create a display contributor field for the Bookmarks Entry Title so that 
you can map it to the page.

1.  In the `com.liferay.bookmarks.web.internal.asset.display.contributor` 
    package, create a file named `BookmarksEntryNameAssetDisplayContributor.java`.

2.  Enter the following code into the class:

        @Component(
          property = "model.class.name=com.liferay.bookmarks.model.BookmarksEntry",
          service = AssetDisplayContributorField.class
        )
        public class BookmarksEntryNameAssetDisplayContributorField
          implements AssetDisplayContributorField<BookmarksEntry> {

          @Override
          public String getKey() {
             return "name";
          }

          @Override
          public String getLabel(Locale locale) {
             return LanguageUtil.get(locale, "name");
          }

          @Override
          public String getType() {
             return "text";
          }

          @Override
          public String getValue(BookmarksEntry bookmarksEntry, Locale locale) {
             return bookmarksEntry.getName();
          }
        }

Now you can select the Bookmarks Entry Name as a field to be used in an Asset 
Display Page. You can create additional display contributor field classes for
any fields that you need to map for Asset Display Pages.
