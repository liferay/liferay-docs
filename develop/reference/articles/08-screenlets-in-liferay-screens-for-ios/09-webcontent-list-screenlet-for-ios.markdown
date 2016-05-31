# Web Content List Screenlet for iOS [](id=web-content-list-screenlet-for-ios)

## Requirements [](id=requirements)

- Xcode 7.2
- iOS 9 SDK
- Liferay Portal 6.2 (CE or EE), Liferay 7.0 CE, Liferay DXP 
- Liferay Screens Compatibility Plugin 
  ([CE](http://www.liferay.com/marketplace/-/mp/application/54365664) or 
  [EE](http://www.liferay.com/marketplace/-/mp/application/54369726), 
  depending on your portal edition). This app is preinstalled in Liferay 7.0 CE 
  and Liferay DXP instances. 

## Compatibility [](id=compatibility)

- iOS 7 and above

## Features [](id=features)

Web Content List Screenlet can show lists of 
[web content](/discover/portal/-/knowledge_base/7-0/creating-web-content) 
from a Liferay instance. It can show both basic and 
[structured web content](/discover/portal/-/knowledge_base/7-0/designing-uniform-content). 
The Screenlet also implements 
[fluent pagination](http://www.iosnomad.com/blog/2014/4/21/fluent-pagination) 
with configurable page size, and supports i18n in asset values. 

## Module [](id=module)

- WebContent

## Themes [](id=themes)

The Default Theme uses a standard `UITableView` to show the scrollable list. 
Other Themes may use a different component, such as `UICollectionView` or 
others, to show the contents. 

![Figure 1: Web Content List Screenlet using the Default (`default`) Theme.](../../images/screens-ios-webcontent-list.png)

## Offline [](id=offline)

This Screenlet supports offline mode so it can function without a network 
connection. 

| Policy | What happens | When to use |
|--------|--------------|-------------|
| `remote-only` | The Screenlet loads the list from the Liferay instance. If a connection issue occurs, the Screenlet uses the delegate to notify the developer about the error. If the Screenlet successfully loads the list, it stores the data in the local cache for later use. | Use this policy when you always need to show updated data, and show nothing when there's no connection. |
| `cache-only` | The Screenlet loads the list from the local cache. If the list isn't there, the Screenlet uses the delegate to notify the developer about the error. | Use this policy when you always need to show local data, without retrieving remote information under any circumstance. |
| `remote-first` | The Screenlet loads the list from the Liferay instance. If this succeeds, the Screenlet shows the list to the user and stores it in the local cache for later use. If a connection issue occurs, the Screenlet retrieves the list from the local cache. If the list doesn't exist there, the Screenlet uses the delegate to notify the developer about the error. | Use this policy to show the most recent version of the data when connected, but show a possibly outdated version when there's no connection. |
| `cache-first` | The Screenlet loads the list from the local cache. If the list isn't there, the Screenlet requests it from the Liferay instance and notifies the developer about any errors that occur (including connectivity errors). | Use this policy to save bandwidth and loading time in case you have local (but possibly outdated) data. |

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `autoLoad` | `boolean` | Whether the list should automatically load when the Screenlet appears in the app's UI. The default value is `true`. |
| `refreshControl` | `boolean` | Whether a standard [`UIRefreshControl`](https://developer.apple.com/library/ios/documentation/UIKit/Reference/UIRefreshControl_class/) is shown when the user does the pull to refresh gesture. The default value is `true`. |
| `firstPageSize` | `number` | The number of items to display on the first page. The default value is `50`. |
| `pageSize` | `number` | The number of items to display on second and subsequent pages. The default value is `25`. |
| `groupId` | `number` | The ID of the site (group) where the web content exists. If set to `0`, the `groupId` specified in `LiferayServerContext` is used. The default value is `0`. |
| `folderId` | `number` | The ID of the web content folder. If set to `0`, the root folder is used. The default value is `0`. |

## Methods [](id=methods)

| Method | Return | Explanation |
|-----------|-----------|-------------| 
| `loadList()` | `boolean` | Starts the request to load the web content list. This list is shown when the response is received. Returns `true` if the request is sent successfully. |

## Delegate [](id=delegate)

Web Content List Screenlet delegates some events to an object that conforms to 
the `WebContentListScreenletDelegate` protocol. This protocol lets you implement 
the following methods: 

- `- screenlet:onWebContentListResponse:`: Called when a page of contents is 
  received. Note that this method may be called more than once: one call for 
  each page received.

- `- screenlet:onWebContentListError:`: Called when an error occurs in the 
  process. The `NSError` object describes the error. 

- `- screenlet:onWebContentSelected:`: Called when an item in the list is 
  selected.
