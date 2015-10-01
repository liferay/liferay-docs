# DDL List Screenlet for iOS [](id=ddllistscreenlet-for-ios)

<iframe width="560" height="315" src="https://www.youtube.com/embed/O28qAvrumqQ" frameborder="0" allowfullscreen></iframe>

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

The `DDLListScreenlet` enables the following features:

- Shows a scrollable collection of DDL records.
- Implements [fluent pagination](http://www.iosnomad.com/blog/2014/4/21/fluent-pagination) 
  with configurable page size.
- Allows filtering of records by creator.
- Supports i18n in record values.

## Module [](id=module)

- DDL

## Themes [](id=themes)

- The Default Theme uses a standard `UITableView` to show the scrollable list. 
  Other Themes may use a different component, such as `UICollectionView` or 
  others, to show the items.

![The `DDLListScreenlet` using the Default (`default`) Theme.](../../images/screens-ios-ddllist.png)

## Portal Configuration [](id=portal-configuration)

Dynamic Data Lists (DDL) and Data Types should be configured in the portal. For
more details, please refer to the Liferay User Guide sections 
[Defining Data Types](/portal/-/knowledge_base/6-2/building-a-list-platform-in-liferay-and-defining-data-) 
and [Creating Data Lists](/portal/-/knowledge_base/6-2/creating-data-lists).

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `autoLoad` | `boolean` | Defines whether the list loads when it's presented on the screen. The default value is `true`. |
| `refreshControl` | `boolean` | Defines whether a standard [UIRefreshControl](https://developer.apple.com/library/ios/documentation/UIKit/Reference/UIRefreshControl_class/) is shown when the user performs the pull to refresh gesture. The default value is `true`. |
| `firstPageSize` | `number` | The number of items retrieved from the server for display on the first page. The default value is `50`. |
| `pageSize` | `number` | The number of items retrieved from the server for display on the second and subsequent pages. The default value is `25`. |
| `recordSetId` | `number` | The ID of the DDL being called. To find the IDs for your DDLs, click *Admin* &rarr; *Content* from the Dockbar. Then click *Dynamic Data Lists*. Each DDL's ID is in the table's ID column. |
| `userId` | `number` | The ID of the user to filter records on. Records aren't filtered if the `userId` is `0`. The default value is `0`. |
| `labelFields` | `string` | The comma-separated names of the DDL fields to show. Refer to the list's data definition to find the field names. To do so, click *Admin* &rarr; *Content* from the Dockbar. Then click *Dynamic Data Lists* and click the *Manage Data Definitions* button. You can view the fields by clicking on any of the data definitions in the table that appears. Note that the appearance of these values depends on the Theme selected by the user. |

## Methods [](id=methods)

| Method | Return | Explanation |
|-----------|-----------|-------------| 
|  `loadList()` | `boolean` | Starts the request to load the list of records. The list is shown when the response is received. This method returns `true` if the request is sent. |

## Delegate [](id=delegate)

The `DDLListScreenlet` delegates some events in an object that conforms to the 
`DDLListScreenletDelegate` protocol. This protocol lets you implement the 
following methods:

- `- screenlet:onDDLListResponseRecords:`: Called when a page of contents is 
  received. Note that this method may be called more than once; once for each 
  retrieved page.

- `- screenlet:onDDLListError:`: Called when an error occurs in the process. The 
  `NSError` object describes the error.

- `- screenlet:onDDLSelectedRecord:`: Called when an item in the list is 
  selected.

## Offline [](id=offline)

This screenlet sopport offline mode in order to work under scenarios with bad connectivity.

| Policy | What happens | When to use |
|--------|--------------|-------------|
| `remote-only` | The list will be loaded from the portal. If a connection issue happens, the screenlet will notify about the error through the delegate as usual. If the list can be successfully loaded, the received items are stored in the local cache for later usage.| Use this policy when you need to show always updated items and show nothing when there's no connection.|
| `cache-only` | The list will be loaded from the local cache. If they're not present, the screenlet will notify about the error through the delegate as usual.| Use this policy when you need to show always local items without retrieving remote information under any circumstance.|
| `remote-first` | The list will be requested to the remote portal. If it's received, it will be shown to the user and stored in the local cache for later usage. If a connection issue happens, then it will be retrieved from the local cache. If it doesn't exist there, the screenlet will notify about the error through the delegate as usual. | When you need the most updated version if connected, but is accepted to show an outdated version when there's no connection.|
| `cache-first` | The items will be loaded from the local cache if exist. If they don't exist, then they will be requested to the portal as usual and will notify about the error in case of issue (even on connectivity errors).| When you want to save bandwidth and loading time in case you have a local (but probably outdated) version.|
