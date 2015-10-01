# Asset List Screenlet for Android [](id=assetlistscreenlet-for-android)

## Requirements [](id=requirements)

- Android SDK 4.0 (API Level 15) or above
- Liferay Portal 6.2 CE or EE
- Liferay Screens Compatibility Plugin (
  [CE](http://www.liferay.com/marketplace/-/mp/application/54365664) or 
  [EE](http://www.liferay.com/marketplace/-/mp/application/54369726), 
  depending on your portal edition). 

## Compatibility [](id=compatibility)

- Android SDK 4.0 (API Level 15) and above

## Features [](id=features)

The `AssetListScreenlet` can be used to show [asset](/tutorials/-/knowledge_base/6-2/asset-framework) 
lists from a Liferay instance. For example, you can use the Screenlet to show a 
scrollable list of assets. It also implements [fluent pagination](http://www.iosnomad.com/blog/2014/4/21/fluent-pagination) 
with configurable page size. The `AssetListScreenlet` can show assets belonging 
to the following classes:

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

## Views [](id=views)

The Default Views use a standard `RecyclerView` to show the scrollable list. 
Other Views may use a different component, such as `ViewPager` or others, to 
show the items.

![`AssetListScreenlet` using the Default and Material Viewsets.](../../images/screens-android-assetlist.png)

## Portal Configuration [](id=portal-configuration)

Dynamic Data Lists (DDL) and Data Types should be configured properly in the 
portal. Refer to the [Defining Data Types](/portal/-/knowledge_base/6-2/building-a-list-platform-in-liferay-and-defining-data-) 
and [Creating Data Lists](/portal/-/knowledge_base/6-2/creating-data-lists) 
sections of the User Guide for more details.

Also, [Liferay Screens' Compatibility Plugin](https://github.com/liferay/liferay-screens/tree/master/portal) 
must be installed to allow remote calls without the `userId`.

## Required Attributes [](id=required-attributes)

- `layoutId`
- `classNameId`

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `layoutId` | `@layout` | The layout to use to show the View.|
| `autoLoad` | `boolean` | Whether the list should be loaded when it's presented on the screen. The default value is `true`. |
|  `firstPageSize` | `number` | The number of items to retrieve from the server for display on the list's first page. The default value is `50`. |
| `pageSize` | `number` | The number of items to retrieve from the server for display on the second and subsequent pages. The default value is `25`. |
| `groupId` | `number` | The asset's group (site) ID. If this value is `0`, the `groupId` specified in `LiferayServerContext` is used. The default value is `0 `. |
| `classNameId` | `number` | The asset class name's ID. Use values from the `AssetClassNameId` enumeration or the `classname_` database table. |

## Methods [](id=methods)

| Method | Return | Explanation |
|-----------|-----------|-------------| 
| `loadPage(pageNumber)` | `void` | Starts the request to load the specified page of assets. The page is shown when the response is received. |

## Listener [](id=listener)

The `AssetListScreenlet` delegates some events to an object that implements the 
`AssetListListener` interface. This interface extends from `BaseListListener` 
and lets you implement the following methods:

- `onListPageReceived(BaseListScreenlet source, int page, 
  List<AssetEntry> entries, int rowCount)`: Called when a page of assets is 
  received. Note that this method may be called more than once; once for each 
  page received.

- `onListPageFailed(BaseListScreenlet source, int page, Exception e)`: Called 
  when an error occurs in the process.

- `onListItemSelected(BaseListScreenlet source, AssetEntry entry)`: Called when 
  an item in the list is selected.

## Offline [](id=offline)

This screenlet sopport offline mode in order to work under scenarios with bad connectivity.

| Policy | What happens | When to use |
|--------|--------------|-------------|
| `REMOTE_ONLY` | The list will be loaded from the portal. If a connection issue happens, the screenlet will notify about the error through the listener as usual. If the list can be successfully loaded, the received asserts are stored in the local cache for later usage.| Use this policy when you need to show always updated assets and show nothing when there's no connection.|
| `CACHE_ONLY` | The list will be loaded from the local cache. If they're not present, the screenlet will notify about the error through the listener as usual.| Use this policy when you need to show always local assets without retrieving remote information under any circumstance.|
| `REMOTE_FIRST` | The list will be requested to the remote portal. If it's received, it will be shown to the user and stored in the local cache for later usage. If a connection issue happens, then it will be retrieved from the local cache. If it doesn't exist there, the screenlet will notify about the error through the listener as usual. | When you need the most updated version if connected, but is accepted to show an outdated version when there's no connection.|
| `CACHE_FIRST` | The assets will be loaded from the local cache if exist. If they don't exist, then they will be requested to the portal as usual and will notify about the error in case of issue (even on connectivity errors).| When you want to save bandwidth and loading time in case you have a local (but probably outdated) version.|
