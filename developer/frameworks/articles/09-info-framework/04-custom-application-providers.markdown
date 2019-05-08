# Using Providers with Custom Applications

To demonstrate a Custom Application, imagine a widget that can display lists of 
orders. Instead of hardcoding which lists it can provide, you can use the Info 
Framework so that it shows any list of orders provide through 
`InfoListProvider`. 

First you must obtain a list of all available providers for the desired type, 
then you would obtain a specific provider through that list. Once you have the 
provider.

1.  The list of all available providers for `MyOrder`, can be obtained done by 
    using the InfoListProviderTracker:
        
        @Reference
        InfoListProviderTracker _infoListProviderTracker;
        Once a tracker is available, obtaining the list is as simple as invoking getInfoListProviders() as follows:
        _infoListProviderTracker.getInfoListProviders(MyOrder.class);
        

    This list could be shown to the user in a list. When the user selects one, 
    the className of the class can be stored for retrieval later.

2.  When a specific provider is desired it can be obtained through its class name as follows:

        _infoListProviderTracker.getInfoListProvider(infoListProviderClassName);


## Leveraging renderers from a custom application

Using renderers from a custom application is almost identical to using 
providers. Here is the equivalent code to what we have seen in the previous 
section:

        _infoItemRendererTracker.getInfoItemRenderers(MyOrder.class.getName());

        String infoItemRendererClassName = MyOrderRenderer.class.getName();
        _infoItemRendererTracker.getInfoItemRenderer(infoItemRendererClassName);

