# Blogs Entry Display Screenlet for iOS [](id=blogs-entry-display-screenlet-for-ios)

## Requirements [](id=requirements)

- Xcode 7.2
- iOS 9 SDK
- Liferay 7.0 CE, Liferay DXP 
- Liferay Screens Compatibility Plugin 
  ([CE](http://www.liferay.com/marketplace/-/mp/application/54365664) or 
  [DE](http://www.liferay.com/marketplace/-/mp/application/54369726), 
  depending on your Liferay edition). This app is preinstalled in Liferay 7.0 CE 
  and Liferay DXP instances. 

## Compatibility [](id=compatibility)

- iOS 8 and above

## Features [](id=features)

Blogs Entry Display Screenlet displays a single blog entry. Image Display 
Screenlet renders any header image the blogs entry may have. 

## Module [](id=module)

- None

## Themes [](id=themes)

- Default

The Default Theme can use different components to show a blogs entry 
(`BlogsEntry`). For example, it uses `UILabel` to show a blog's text, and 
[User Portrait Screenlet](/develop/reference/-/knowledge_base/7-0/userportraitscreenlet-for-ios) 
to show the profile picture of the Liferay user who posted it. Note that other 
Themes may use different components. 

![Figure 1: Blogs Entry Display Screenlet using the Default Theme.](../../images/screens-ios-blogsentrydisplay.png)

## Offline [](id=offline)

This Screenlet supports offline mode so it can function without a network 
connection. For more information on how offline mode works, see the 
[tutorial on its architecture](/develop/tutorials/-/knowledge_base/7-0/architecture-of-offline-mode-in-liferay-screens). 
Here are the offline mode policies that you can use with this Screenlet: 

| Policy | What happens | When to use |
|--------|--------------|-------------|
| `remote-only` | The Screenlet loads the data from the Liferay instance. If a connection issue occurs, the Screenlet uses the listener to notify the developer about the error. If the Screenlet successfully loads the data, it stores it in the local cache for later use. | Use this policy when you always need to show updated data, and show nothing when there's no connection. |
| `cache-only` | The Screenlet loads the data from the local cache. If the data isn't there, the Screenlet uses the listener to notify the developer about the error. | Use this policy when you always need to show local data, without retrieving remote data under any circumstance. |
| `remote-first` | The Screenlet loads the data from the Liferay instance. If this succeeds, the Screenlet shows the data to the user and stores it in the local cache for later use. If a connection issue occurs, the Screenlet retrieves the data from the local cache. If the data doesn't exist there, the Screenlet uses the listener to notify the developer about the error. | Use this policy to show the most recent version of the data when connected, but show an outdated version when there's no connection. |
| `cache-first` | The Screenlet loads the data from the local cache. If the data isn't there, the Screenlet requests it from the Liferay instance and notifies the developer about any errors that occur (including connectivity errors). | Use this policy to save bandwidth and loading time in case you have local (but probably outdated) data. |

## Required Attributes [](id=attributes)

- `assetEntryId`

If you don't use `assetEntryId`, you must use both of the following attributes:

- `className`
- `classPK`

| Attribute | Data type | Explanation |
|-----------|-----------|-------------|
| `assetEntryId` | `number` | The primary key of the blog entry (`BlogsEntry`). |
| `className` | `string` | The `BlogsEntry` object's fully qualified class name. This is [`com.liferay.blogs.kernel.model.BlogsEntry`](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/blogs/kernel/model/BlogsEntry.html). If you don't use `assetEntryId`, the `className` and `classPK` attributes are required to instantiate the Screenlet. |
| `classPK` | `number` | The `BlogsEntry` object's unique identifier. If you don't use `assetEntryId`, the `className` and `classPK` attributes are required to instantiate the Screenlet. |
| `autoLoad` | `boolean` | Whether the blog entry automatically loads when the Screenlet appears in the app's UI. The default value is `true`. |
| `offlinePolicy` | `string` | The offline mode setting. The default value is `remote-first`. See [the Offline section](/develop/reference/-/knowledge_base/7-0/blogs-entry-display-screenlet-for-ios#offline) for details. |

## Delegate [](id=delegate)

Blogs Entry Display Screenlet delegates some events to an object that conforms 
to the `BlogsEntryDisplayScreenletDelegate` protocol. This protocol lets you 
implement the following methods: 

- `- screenlet:onBlogEntryResponse:`: Called when the Screenlet receives the 
  `BlogsEntry` object. 

- `- screenlet:onBlogEntryError:`: Called when an error occurs in the process. 
  The `NSError` object describes the error. 
