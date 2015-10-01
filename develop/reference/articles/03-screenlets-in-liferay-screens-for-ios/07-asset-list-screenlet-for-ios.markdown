# Asset List Screenlet for iOS [](id=assetlistscreenlet-for-ios)

## Requirements [](id=requirements)

- XCode 6.3.x
- iOS 8 SDK
- Liferay Portal 6.2 CE or EE
- Liferay Screens Compatibility Plugin (
  [CE](http://www.liferay.com/marketplace/-/mp/application/54365664) or 
  [EE](http://www.liferay.com/marketplace/-/mp/application/54369726), 
  depending on your portal edition). 

## Compatibility [](id=compatibility)

- iOS 7 and above

## Features [](id=features)

The `AssetListScreenlet` can be used to show lists of [assets](/tutorials/-/knowledge_base/6-2/asset-framework) 
from a Liferay instance. For example, you can use the Screenlet to show a 
scrollable collection of assets. It also implements [fluent pagination](http://www.iosnomad.com/blog/2014/4/21/fluent-pagination) 
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

![`AssetListScreenlet` using the Default (`default`) Theme.](../../images/screens-ios-assetlist.png)

## Portal Configuration [](id=portal-configuration)

Dynamic Data Lists and Data Types should be configured in the portal. 
Refer to the [Defining Data Types](/portal/-/knowledge_base/6-2/building-a-list-platform-in-liferay-and-defining-data-) 
and [Creating Data Lists](/portal/-/knowledge_base/6-2/creating-data-lists) 
sections of the User Guide for more details. 

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `autoLoad` | `boolean` | Defines whether the list should be loaded when it's presented on the screen. The default value is `true`. |
| `refreshControl` | `boolean` | Defines whether a standard [UIRefreshControl](https://developer.apple.com/library/ios/documentation/UIKit/Reference/UIRefreshControl_class/) is shown when the user does the pull to refresh gesture. The default value is `true`. |
| `firstPageSize` | `number` | The number of items retrieved from the server for display on the first page. The default value is `50`. |
| `pageSize` | `number` | The number of items retrieved from the server for display on the second and subsequent pages. The default value is `25`. |
| `groupId` | `number` | The ID of the site (group) where the asset is stored. If set to `0`, the `groupId` specified in `LiferayServerContext` is used. The default value is `0`. |
| `classNameId` | `number` | The ID of the asset's class name. Use values from the `AssetClassNameId` enumeration or the `classname_` database table. |

## Methods [](id=methods)

| Method | Return | Explanation |
|-----------|-----------|-------------| 
| `loadList()` | `boolean` | Starts the request to load the list of assets. This list is shown when the response is received. Returns `true` if the request is sent. |

## Delegate [](id=delegate)

The `AssetListScreenlet` delegates some events to an object that conforms to the 
`AssetListScreenletDelegate` protocol. This protocol lets you implement the 
following methods: 

- `- screenlet:onAssetListResponseEntries:`: Called when a page of assets is 
  received. Note that this method may be called more than once; one call for 
  each page received.

- `- screenlet:onAssetListError:`: Called when an error occurs in the process. 
  The `NSError` object describes the error.

- `- screenlet:onAssetSelectedEntry:`: Called when an item in the list is 
  selected.

## Offline [](id=offline)

This screenlet sopport offline mode in order to work under scenarios with bad connectivity.

| Policy | What happens | When to use |
|--------|--------------|-------------|
| `remote-only` | The list will be loaded from the portal. If a connection issue happens, the screenlet will notify about the error through the delegate as usual. If the list can be successfully loaded, the received asserts are stored in the local cache for later usage.| Use this policy when you need to show always updated assets and show nothing when there's no connection.|
| `cache-only` | The list will be loaded from the local cache. If they're not present, the screenlet will notify about the error through the delegate as usual.| Use this policy when you need to show always local assets without retrieving remote information under any circumstance.|
| `remote-first` | The list will be requested to the remote portal. If it's received, it will be shown to the user and stored in the local cache for later usage. If a connection issue happens, then it will be retrieved from the local cache. If it doesn't exist there, the screenlet will notify about the error through the delegate as usual. | When you need the most updated version if connected, but is accepted to show an outdated version when there's no connection.|
| `cache-first` | The assets will be loaded from the local cache if exist. If they don't exist, then they will be requested to the portal as usual and will notify about the error in case of issue (even on connectivity errors).| When you want to save bandwidth and loading time in case you have a local (but probably outdated) version.|
