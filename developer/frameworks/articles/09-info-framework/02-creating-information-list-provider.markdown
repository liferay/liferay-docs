# Creating an Information List Provider

To demonstrate Information List Providers, follow the instructions below to 
implement an `InfoListProvider` for the most viewed asset entries. In this case 
the list will a list of `AssetEntry` instances, which already have their own 
renderer, so you can display in the Asset Publisher with no additional changes. 
If you create a provider for a custom class, but you will also need to render 
it.

1.  Create a [module](link-create-module) name asset-entry-info-list-provider.

2.  Create a package inside the module named  
    `com.liferay.docs.info.provider`.

3.  Inside the package, create a class named `AssetEntryInfoListProvider` that implements `InfoListProvider` and register it as a component:

        @Component(service = InfoListProvider.class)
          public class AssetEntryInfoListProvider implements InfoListProvider<AssetEntry> {
        
        }
        
4.  Next, add the necessary `@Reference` that you need for the logic of 
    retrieving assets to the bottom of the class.

        @Reference
        AssetEntryLocalService _assetEntryLocalService;

5.  Then create the basic `getInfoList` which will return just the list.
      
           @Override
           public List<AssetEntry> getInfoList(
             InfoListProviderContext infoListProviderContext) {

             return _assetEntryLocalService.getTopViewedEntries(
                new String[0], false, 0, 20);
           }

    In this case, we have hardcoded descending order and a maximum of 20 items 
    to return. 
           
6.  Now implement the second method, which provides greater control over how 
    the items are returned to the provider.           

         @Override
         public List<AssetEntry> getInfoList(
           InfoListProviderContext infoListProviderContext, Pagination pagination,
           Sort sort) {

           return _assetEntryLocalService.getTopViewedEntries(
              new String[0], !sort.isReverse(), pagination.getStart(),
              pagination.getEnd());
         }
         
7.  Provide the method to get a full count of info list items.           

         @Override
         public int getInfoListCount(
         InfoListProviderContext infoListProviderContext) {

             Company company = infoListProviderContext.getCompany();

             return _assetEntryLocalService.getCompanyEntriesCount(
             company.getCompanyId());
         }

8.  And finally, add the method for providing a display label for the list.
   
           @Override
           public String getLabel(Locale locale) {
             return “Most Viewed Content”;
             }

The completed class should look like this:

        @Component(service = InfoListProvider.class)
          public class AssetEntryInfoListProvider implements InfoListProvider<AssetEntry> {
        
                   @Override
                   public List<AssetEntry> getInfoList(
                     InfoListProviderContext infoListProviderContext) {

                     return _assetEntryLocalService.getTopViewedEntries(
                        new String[0], false, 0, 20);
                   }

                 @Override
                 public List<AssetEntry> getInfoList(
                   InfoListProviderContext infoListProviderContext, Pagination pagination,
                   Sort sort) {

                   return _assetEntryLocalService.getTopViewedEntries(
                      new String[0], !sort.isReverse(), pagination.getStart(),
                      pagination.getEnd());
                 }

                 @Override
                 public int getInfoListCount(
                 InfoListProviderContext infoListProviderContext) {

                     Company company = infoListProviderContext.getCompany();

                     return _assetEntryLocalService.getCompanyEntriesCount(
                     company.getCompanyId());
                 }
   
                   @Override
                   public String getLabel(Locale locale) {
                     return “Most Viewed Content”;
                     }
        
        @Reference
        AssetEntryLocalService _assetEntryLocalService;
        }
        

This class is now ready to go! If you deploy it, it will display the "Most 
Viewed Content" in any Asset Publisher.

## Next steps

This example is pretty simplistic and probably not useful in real world cases. 
To begin with, you may want to scope the search to the current site. You can 
also add any more advanced filter criteria or provide a configuration for the 
provider using Liferay’s configuration framework. 

As mentioned, it is also possible to implement providers for custom types. The 
following code shows a partial example of how a provider for a custom MyOrder 
class would look like:

    @Component(service = InfoListProvider.class)
    public class MyOrderProvider implements InfoListProvider<MyOrder> {

        @Override
        public List<MyOrder> getInfoList(
            InfoListProviderContext infoListProviderContext, Pagination pagination,
            Sort sort) {

            return _myOrderLocalService.getOrders(
                [...], !sort.isReverse(), pagination.getStart(),
                pagination.getEnd());
            }

        [..]

        @Reference
        MyOrderLocalService _myOrderLocalService;

