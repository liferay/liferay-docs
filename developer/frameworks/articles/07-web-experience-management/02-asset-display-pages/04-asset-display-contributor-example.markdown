# Asset Display Contributor Example [](id=asset-display-contributor-example)

Now create an Asset Display Contributor for the Blogs application. Remember the
two main steps:

1.  Create the Asset Display Contributor class for the asset type.

2.  Create the Asset Display Contributor Field classes for the fields you want 
    to be available for Asset Display Pages.
 
In this example, you'll create the Asset Display Contributor and then create 
one Asset Display Contributor Field for the Blogs Entry Title.
 

## Creating the Blogs Contributor [](id=creating-the-blogs-contributor)

First you must create the contributor.

1.  Inside the `blogs-web` module, create a package called 
    `com.liferay.blogs.web.internal.asset.display.contributor`.

2.  In that package, create a file named
    `BlogEntryAssetDisplayContributor.java`.

3.  Enter the following code into the class:

        @Component(immediate = true, service = AssetDisplayContributor.class)
        public class BlogsEntryAssetDisplayContributor
          extends BaseAssetDisplayContributor<BlogsEntry> {

          @Override
          public String getClassName() {
             return BlogsEntry.class.getName();
          }

          @Override
          protected Map<String, Object> getClassTypeValues(
             BlogsEntry assetEntryObject, Locale locale) {

             return null;
          }
        }

4.  Save your file and redeploy the module.

This adds Blogs Entries to the list of asset types eligible for use with Asset
Display Pages.

1.  Go to *Site Administration* &rarr; *Build* &rarr; *Pages*.

2.  Select the *Display Pages* tab.

3.  Click *Add* (![Add Display Page](../../../images/icon-add.png)) to create a new Display Page.

4.  In the Display Page editor, click *Mapping* from the menu on the right side.

5.  Select the Blogs Asset Type.

![Figure 1: You can now select the Blogs Asset Type.](../../../images/select-asset-type.png)

This isn't complete yet because while you can select the asset type, you don't 
have any fields that can be mapped.

## Creating the Blogs Entry Title Field Contributor [](id=creating-the-blogs-entry-title-field-contributor)

Now create a display contributor field for the Blogs Entry Title so that you can
map it to the page.

1.  In the `com.liferay.blogs.web.internal.asset.display.contributor` 
    package, create a file named `BlogEntryAssetDisplayContributor.java`.

2.  Enter the following code into the class:

        @Component(
          property = "model.class.name=com.liferay.blogs.model.BlogsEntry",
          service = AssetDisplayContributorField.class
        )
        public class BlogsEntryTitleAssetDisplayContributorField
          implements AssetDisplayContributorField<BlogsEntry> {

          @Override
          public String getKey() {
             return "subtitle";
          }

          @Override
          public String getLabel(Locale locale) {
             return LanguageUtil.get(locale, "subtitle");
          }

          @Override
          public String getType() {
             return "text";
          }

          @Override
          public String getValue(BlogsEntry blogsEntry, Locale locale) {
             return blogsEntry.getSubtitle();
          }
        }

Now you can select the Blogs Entry Title as a field to be used in an Asset 
Display Page. You can create additional display contributor field classes for
any fields that you need to map for Asset Display Pages.

![Figure 2: You can now select the Blogs Entry Title field in the mapping selector.](../../../images/mapping-fields.png)

