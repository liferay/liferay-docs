# Asset List Screenlet for iOS [](id=assetlistscreenlet-for-ios)

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

The `AssetListScreenlet` can be used to show lists of 
[assets](/develop/tutorials/-/knowledge_base/7-0/asset-framework) 
from a Liferay instance. For example, you can use the Screenlet to show a 
scrollable collection of assets. It also implements 
[fluent pagination](http://www.iosnomad.com/blog/2014/4/21/fluent-pagination) 
with configurable page size. The `AssetListScreenlet` can show assets of the 
following classes: 

- `Group`
- `Layout`
- `Organization`
- `User`
- `UserGroup`
- `BlogsEntry`
- `BookmarksEntry`
- `BookmarksFolder`
- `CalendarEvent`
- `DLFileEntry`
- `DLFileEntryMetadata`
- `DLFileEntryType`
- `DLFileRank`
- `DLFileShortcut`
- `DLFileVersion`
- `DDLRecord`
- `DDLRecordSet`
- `JournalArticle` (Web Content)
- `JournalFolder`
- `MBMessage`
- `MBThread`
- `MBCategory`
- `MBDiscussion`
- `MBMailingList`
- `WikiPage`
- `WikiPageResource`
- `WikiNode`

The `AssetListScreenlet` also supports i18n in asset values.

## Module [](id=module)

- None

## Themes [](id=themes)

The Default Theme uses a standard `UITableView` to show the scrollable list. 
Other Themes may use a different component, such as `UICollectionView` or 
others, to show the items.

![Figure 1: Asset List Screenlet using the Default (`default`) Theme.](../../images/screens-ios-assetlist.png)

## Offline [](id=offline)

This Screenlet supports offline mode so it can function without a network 
connection. 

| Policy | What happens | When to use |
|--------|--------------|-------------|
| `remote-only` | The Screenlet loads the list from the portal. If a connection issue occurs, the Screenlet uses the delegate to notify the developer about the error. If the Screenlet successfully loads the list, it stores the data in the local cache for later use. | Use this policy when you always need to show updated data, and show nothing when there's no connection. |
| `cache-only` | The Screenlet loads the list from the local cache. If the list isn't there, the Screenlet uses the delegate to notify the developer about the error. | Use this policy when you always need to show local data, without retrieving remote information under any circumstance. |
| `remote-first` | The Screenlet loads the list from the portal. If this succeeds, the Screenlet shows the list to the user and stores it in the local cache for later use. If a connection issue occurs, the Screenlet retrieves the list from the local cache. If the list doesn't exist there, the Screenlet uses the delegate to notify the developer about the error. | Use this policy to show the most recent version of the data when connected, but show an outdated version when there's no connection. |
| `cache-first` | The Screenlet loads the list from the local cache. If the list isn't there, the Screenlet requests it from the portal and notifies the developer about any errors that occur (including connectivity errors). | Use this policy to save bandwidth and loading time in case you have local (but probably outdated) data. |

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `autoLoad` | `boolean` | Defines whether the list should be loaded when it's presented on the screen. The default value is `true`. |
| `refreshControl` | `boolean` | Defines whether a standard [UIRefreshControl](https://developer.apple.com/library/ios/documentation/UIKit/Reference/UIRefreshControl_class/) is shown when the user does the pull to refresh gesture. The default value is `true`. |
| `firstPageSize` | `number` | The number of items retrieved from the server for display on the first page. The default value is `50`. |
| `pageSize` | `number` | The number of items retrieved from the server for display on the second and subsequent pages. The default value is `25`. |
| `groupId` | `number` | The ID of the site (group) where the asset is stored. If set to `0`, the `groupId` specified in `LiferayServerContext` is used. The default value is `0`. |
| `classNameId` | `number` | The ID of the asset's class name. Use values from the `AssetClassNameId` class or the portal's `classname_` database table. |
| `customEntryQuery` | `Dictionary` | The set of keys (string) and values (string or number) to be used in the [`AssetEntryQuery` object](/portal/6.2/javadocs/com/liferay/portlet/asset/service/persistence/AssetEntryQuery.html). These values filter the assets returned by the portal. |
| `portletItemName` | `string` | The archive name you used in the Asset Publisher. To use this feature, add an Asset Publisher to one of your site's pages (it may be a hidden page), configure the Asset Publisher filter (Asset Selection on configuration page), and then use the *Archive Setup* option to save this configuration with a name. Use this name in this attribute. |

## Methods [](id=methods)

| Method | Return | Explanation |
|-----------|-----------|-------------| 
| `loadList()` | `boolean` | Starts the request to load the list of assets. This list is shown when the response is received. Returns `true` if the request is sent. |

## Delegate [](id=delegate)

The `AssetListScreenlet` delegates some events to an object that conforms to the 
`AssetListScreenletDelegate` protocol. This protocol lets you implement the 
following methods: 

- `- screenlet:onAssetListResponse:`: Called when a page of assets is 
  received. Note that this method may be called more than once; one call for 
  each page received.

- `- screenlet:onAssetListError:`: Called when an error occurs in the process. 
  The `NSError` object describes the error.

- `- screenlet:onAssetSelectedEntry:`: Called when an item in the list is 
  selected.
