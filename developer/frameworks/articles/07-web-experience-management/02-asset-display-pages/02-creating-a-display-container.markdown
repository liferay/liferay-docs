# Creating a Display Contributor [](id=creating-a-display-contributor)

All the logic for Asset Display Contributors, such as reading fields and their
values, goes in the `AssetDisplayContributor` class. To integrate it with your
application, create a class that references the `AssetDislayContributor`
service, extends `BaseAssetDisplayContributor` and implements its
`getClassName()` and `getClassTypeValue()` methods in your module.

You can also implement the `AssetDisplayContributor` interface instead of
extending `BaseAssetDisplayContributor`. However, the recommended method is to
use the pattern described here.

## AssetDisplayContributor [](id=assetdisplaycontributor)

Your `*AssetDisplayContributor` should extend `BaseAssetDisplayContributor` and
provide the entity's class name and fields to be displayed. The first method,
`getClassName`, must return your asset type's class name. The
`getClassTypeValues` method returns a `Map` of the asset's field names and
values. Since you will use `AssetDisplayContributorFields` to
define the fields for the Display Page, the `getClassTypeValues` method can be
set to return null, and no further methods are necessary.

<!--Why? How does this work? What are the components? -Rich -->

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
 
After you create the `*AssetDisplayContributor` class, you must create a class
for each asset field that you want to use with Display Pages.
