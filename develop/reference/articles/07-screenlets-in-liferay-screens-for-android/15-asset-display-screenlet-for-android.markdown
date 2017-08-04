# Asset Display Screenlet for Android [](id=asset-display-screenlet-for-android)

## Requirements [](id=requirements)

- Android SDK 4.0 (API Level 15) or above
- Liferay 7.0 CE, Liferay DXP
- Liferay Screens Compatibility Plugin
  ([CE](http://www.liferay.com/marketplace/-/mp/application/54365664) or 
  [DE](http://www.liferay.com/marketplace/-/mp/application/54369726), 
  depending on your Liferay edition). This app is preinstalled in Liferay 7.0 CE 
  and Liferay DXP instances.

## Compatibility [](id=compatibility)

- Android SDK 4.0 (API Level 15) and above

## Features [](id=features)

Asset Display Screenlet can display an asset from a Liferay instance. The 
Screenlet can currently display Documents and Media files (`DLFileEntry` images, 
videos, audio files, and PDFs), blogs entries (`BlogsEntry`) and web content 
articles (`WebContent`). 

Asset Display Screenlet can also display your custom asset types. See 
[the Listener section of this document](/develop/reference/-/knowledge_base/7-0/asset-display-screenlet-for-android#listener) 
for details. 

## Module [](id=module)

- None

## Views [](id=views)

- Default

![Figure 1: Asset Display Screenlet using the Default View.](../../images/screens-android-assetdisplay.png)

The Default View uses different UI elements to show each asset type. For 
example, it displays images with `ImageView` and blogs with `TextView`. Note 
that other Views may use different UI elements. 

This Screenlet can also render other Screenlets as inner Screenlets: 

- Images: Image Display Screenlet
- Videos: Video Display Screenlet
- Audios: Audio Display Screenlet
- PDFs: PDF Display Screenlet
- Blogs entries: Blogs Entry Display Screenlet
- Web content: Web Content Display Screenlet

These Screenlets can also be used alone without Asset Display Screenlet. 

## Offline [](id=offline)

This Screenlet supports offline mode so it can function without a network 
connection. For more information on how offline mode works, see the 
[tutorial on its architecture](/develop/tutorials/-/knowledge_base/7-0/architecture-of-offline-mode-in-liferay-screens). 
Here are the offline mode policies that you can use with this Screenlet: 

| Policy | What happens | When to use |
|--------|--------------|-------------|
| `REMOTE_ONLY` | The Screenlet loads the data from the Liferay instance. If a connection issue occurs, the Screenlet uses the listener to notify the developer about the error. If the Screenlet successfully loads the data, it stores it in the local cache for later use. | Use this policy when you always need to show updated data, and show nothing when there's no connection. |
| `CACHE_ONLY` | The Screenlet loads the data from the local cache. If the data isn't there, the Screenlet uses the listener to notify the developer about the error. | Use this policy when you always need to show local data, without retrieving remote information under any circumstance. |
| `REMOTE_FIRST` | The Screenlet loads the data from the Liferay instance. If this succeeds, the Screenlet shows the data to the user and stores it in the local cache for later use. If a connection issue occurs, the Screenlet retrieves the data from the local cache. If the data doesn't exist there, the Screenlet uses the listener to notify the developer about the error. | Use this policy to show the most recent version of the data when connected, but show an outdated version when there's no connection. |
| `CACHE_FIRST` | The Screenlet loads the data from the local cache. If the data isn't there, the Screenlet requests it from the Liferay instance and notifies the developer about any errors that occur (including connectivity errors). | Use this policy to save bandwidth and loading time in case you have local (but probably outdated) data. |

## Required Attributes [](id=required-attributes)

- `entryId`

Instead of `entryId`, you can use both of the following attributes: 

- `className`
- `classPK`

If you don't use `entryId`, `className`, or `classPK`, you must use this 
attribute: 

- `portletItemName`

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------|
| `layoutId` | `@layout` | The layout to use to show the View. |
| `autoLoad` | `boolean` | Whether the asset automatically loads when the Screenlet appears in the app's UI. The default value is `true`. |
| `entryId` | `number` | The primary key of the asset. | 
| `className` | `string` | The asset's fully qualified class name. For example, a blog entry's `className` is [`com.liferay.blogs.kernel.model.BlogsEntry`](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/blogs/kernel/model/BlogsEntry.html). The `className` and `classPK` attributes are required to instantiate the Screenlet. |
| `classPK` | `number` | The asset’s unique identifier. The `className` and `classPK` attributes are required to instantiate the Screenlet. |
| `portletItemName` | `string` | The name of the [configuration template](/discover/portal/-/knowledge_base/7-0/configuration-templates) you used in the Asset Publisher. To use this feature, add an Asset Publisher to one of your site's pages (it may be a hidden page), configure the Asset Publisher's filter (in *Configuration* &rarr; *Setup* &rarr; *Asset Selection*), and then use the Asset Publisher's *Configuration Templates* option to save this configuration with a name. Use this name in this attribute. If there is more than one asset in the configuration, the Screenlet displays only the first one. |
| `cachePolicy` | `string` | The offline mode setting. See [the Offline section](/develop/reference/-/knowledge_base/7-0/asset-display-screenlet-for-android#offline) for details. |
| `imageLayoutId` | `@layout` | The layout to use to show an image (`DLFileEntry`). |
| `videoLayoutId` | `@layout` | The layout to use to show a video (`DLFileEntry`). |
| `audioLayoutId` | `@layout` | The layout to use to show an audio file (`DLFileEntry`). |
| `pdfLayoutId` | `@layout` | The layout to use to show a PDF (`DLFileEntry`). |
| `blogsLayoutId` | `@layout` | The layout to use to show a blog entry (`BlogsEntry`). |
| `webDisplayLayoutId` | `@layout` | The layout to use to show a web content article (`WebContent`). |

## Methods [](id=methods)

| Method | Return | Explanation |
|-----------|-----------|-------------| 
| `load(AssetEntry assetEntry)` | `void` | Loads the given `AssetEntry` in the Screenlet. If no inner Screenlet is instantiated, the method tries to load the asset with a custom asset listener method. If this returns `null`, a new `Intent` is called to display the asset. |
| `loadAsset()` | `void` | Searches for the `AssetEntry` defined by the required attributes and loads it in the Screenlet. |

## Listener [](id=listener)

Asset Display Screenlet delegates some events to a class that implements 
`AssetDisplayListener`. This interface contains the following methods: 

- `onRetrieveAssetSuccess(AssetEntry assetEntry)`: Called when the Screenlet 
  successfully loads the asset. 

A second listener, `AssetDisplayInnerScreenletListener`, also exists for 
configuring a child Screenlet (the Screenlet rendered inside Asset Display 
Screenlet) or rendering a custom asset. 

- `onConfigureChildScreenlet(AssetDisplayScreenlet screenlet, BaseScreenlet innerScreenlet, AssetEntry assetEntry)`: 
  Called when the child Screenlet has been successfully initialized. Use this 
  method to configure or customize the child Screenlet. The example 
  implementation here sets the child Screenlet's background color to light gray 
  if the asset is a blog entry entity (`BlogsEntry`): 

        @Override
        public void onConfigureChildScreenlet(AssetDisplayScreenlet screenlet,
            BaseScreenlet innerScreenlet, AssetEntry assetEntry) {
                if ("blogsEntry".equals(assetEntry.getObjectType())) {
                    innerScreenlet.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.light_gray));
                }
        }

- `onRenderCustomAsset(AssetEntry assetEntry)`: Called to render a custom asset. 
  The following example implementation inflates and returns the custom View 
  necessary to render a user from a Liferay instance (`User`): 

        @Override
        public View onRenderCustomAsset(AssetEntry assetEntry) {
            if (assetEntry instanceof User) {
                View view = getLayoutInflater().inflate(R.layout.user_display, null);
                User user = (User) assetEntry;

                TextView username = (TextView) view.findViewById(R.id.liferay_username);

                username(user.getUsername());

                return view;
            }
            return null;
        }
