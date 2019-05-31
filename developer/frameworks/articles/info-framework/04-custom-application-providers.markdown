---
header-id: using-providers-with-custom-applications
---

# Using Providers with Custom Applications

[TOC levels=1-4]

Imagine a widget that can display lists of orders. You can use the Info
Framework so that it shows any list of orders provided through
`InfoListProvider`. 

First you must obtain a list of all available providers for the desired type,
and then you would obtain a specific provider through that list.

1.  The list of all available providers for `MyOrder`, can be obtained done by 
    using the `InfoListProviderTracker`:

    ```java
    @Reference
    InfoListProviderTracker _infoListProviderTracker;
    ```

    Once a tracker is available, obtaining the list is as simple as invoking
    `getInfoListProviders()`:

    ```java
    _infoListProviderTracker.getInfoListProviders(MyOrder.class);
    ```

    When the user selects an item from this list, you can store the class's name.

2.  When a specific provider is desired it can be obtained through its class name as follows:

    ```java
    _infoListProviderTracker.getInfoListProvider(infoListProviderClassName);
    ```

## Leveraging renderers from a custom application

Using renderers from a custom application is almost identical to using 
providers. Here is the equivalent code to what you've seen previously:

```java
_infoItemRendererTracker.getInfoItemRenderers(MyOrder.class.getName());

String infoItemRendererClassName = MyOrderRenderer.class.getName();
_infoItemRendererTracker.getInfoItemRenderer(infoItemRendererClassName);
```

