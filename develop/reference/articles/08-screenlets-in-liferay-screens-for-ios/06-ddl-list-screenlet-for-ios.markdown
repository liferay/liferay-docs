# DDL List Screenlet for iOS [](id=ddllistscreenlet-for-ios)

<iframe width="560" height="315" src="https://www.youtube.com/embed/O28qAvrumqQ" frameborder="0" allowfullscreen></iframe>

## Requirements [](id=requirements)

- Xcode 7.3
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

The DDL List Screenlet enables the following features:

- Shows a scrollable collection of DDL records.
- Implements 
  [fluent pagination](http://www.iosnomad.com/blog/2014/4/21/fluent-pagination) 
  with configurable page size.
- Allows filtering of records by creator.
- Supports i18n in record values.

## Module [](id=module)

- DDL

## Themes [](id=themes)

- The Default Theme uses a standard `UITableView` to show the scrollable list. 
  Other Themes may use a different component, such as `UICollectionView` or 
  others, to show the items.

![The DDL List Screenlet using the Default (`default`) Theme.](../../images/screens-ios-ddllist.png)

## Portal Configuration [](id=portal-configuration)

Dynamic Data Lists (DDL) and Data Types should be configured in the portal. For
more details, please refer to the Liferay User Guide sections 
[Creating Data Definitions](/discover/portal/-/knowledge_base/7-0/creating-data-definitions) 
and 
[Creating Data Lists](/discover/portal/-/knowledge_base/7-0/creating-data-lists). 

## Offline [](id=offline)

This Screenlet supports offline mode so it can function without a network 
connection. For more information on how offline mode works, see the 
[tutorial on its architecture](/develop/tutorials/-/knowledge_base/7-0/architecture-of-offline-mode-in-liferay-screens). 
Here are the offline mode policies that you can use with this Screenlet: 

| Policy | What happens | When to use |
|--------|--------------|-------------|
| `remote-only` | The Screenlet loads the list from the portal. If a connection issue occurs, the Screenlet uses the delegate to notify the developer about the error. If the Screenlet successfully loads the list, it stores the data in the local cache for later use. | Use this policy when you always need to show updated data, and show nothing when there's no connection. |
| `cache-only` | The Screenlet loads the list from the local cache. If the list isn't there, the Screenlet uses the delegate to notify the developer about the error. | Use this policy when you always need to show local data, without retrieving remote information under any circumstance. |
| `remote-first` | The Screenlet loads the list from the portal. If this succeeds, the Screenlet shows the list to the user and stores it in the local cache for later use. If a connection issue occurs, the Screenlet retrieves the list from the local cache. If the list doesn't exist there, the Screenlet uses the delegate to notify the developer about the error. | Use this policy to show the most recent version of the data when connected, but show an outdated version when there's no connection. |
| `cache-first` | The Screenlet loads the list from the local cache. If the list isn't there, the Screenlet requests it from the portal and notifies the developer about any errors that occur (including connectivity errors). | Use this policy to save bandwidth and loading time in case you have local (but probably outdated) data. |

## Required Attributes [](id=required-attributes)

- `recordSetId`
- `labelFields`

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `recordSetId` | `number` | The ID of the DDL being called. To find the IDs for your DDLs, first open the Product Menu and select the site that contains your DDLs. Then click *Content* &rarr; *Dynamic Data Lists*. Each DDL's ID is in the table's ID column. |
| `userId` | `number` | The ID of the user to filter records on. Records aren't filtered if the `userId` is `0`. The default value is `0`. |
| `labelFields` | `string` | The comma-separated names of the DDL fields to show. Refer to the list's data definition to find the field names. To do so, first open the Product Menu and select the site that contains your DDLs. Then click *Content* &rarr; *Dynamic Data Lists*, and find the find the icon (![Configuration Menu](../../images/icon-options.png)) for the Dynamic Data List configuration menu at the upper right. Click this icon and select *Manage Data Definitions*. You can view the fields by clicking on any of the data definitions in the table that appears. Note that the appearance of these values in your app depends on the Theme selected by the user. |
| `offlinePolicy` | `string` | The offline mode setting. The default value is `remote-first`. See the [Offline section](/develop/reference/-/knowledge_base/7-0/ddllistscreenlet-for-ios#offline) for details. |
| `autoLoad` | `boolean` | Whether the list loads automatically when the Screenlet appears in the app's UI. The default value is `true`. |
| `refreshControl` | `boolean` | Whether a standard [iOS `UIRefreshControl`](https://developer.apple.com/library/ios/documentation/UIKit/Reference/UIRefreshControl_class/) appears when the user performs the pull to refresh gesture. The default value is `true`. |
| `firstPageSize` | `number` | The number of items retrieved from the server for display on the first page. The default value is `50`. |
| `pageSize` | `number` | The number of items retrieved from the server for display on the second and subsequent pages. The default value is `25`. |
| `obcClassName` | `string` | The name of the `OrderByComparator` class to use to sort the results. Omit this property if you don't want to sort the results. [Click here](https://github.com/liferay/liferay-portal/tree/master/modules/apps/forms-and-workflow/dynamic-data-lists/dynamic-data-lists-api/src/main/java/com/liferay/dynamic/data/lists/util/comparator) to see some comparator classes. Note, however, that not all of these classes can be used with `obcClassName`. You can only use comparator classes that extend `OrderByComparator<DDLRecord>`. You can also create your own comparator classes that extend `OrderByComparator<DDLRecord>`. |

## Methods [](id=methods)

| Method | Return | Explanation |
|-----------|-----------|-------------| 
|  `loadList()` | `boolean` | Starts the request to load the list of records. The list is shown when the response is received. This method returns `true` if the request is sent. |

## Delegate [](id=delegate)

The DDL List Screenlet delegates some events in an object that conforms to the 
`DDLListScreenletDelegate` protocol. This protocol lets you implement the 
following methods:

- `- screenlet:onDDLListResponseRecords:`: Called when a page of contents is 
  received. Note that this method may be called more than once; once for each 
  retrieved page.

- `- screenlet:onDDLListError:`: Called when an error occurs in the process. The 
  `NSError` object describes the error.

- `- screenlet:onDDLSelectedRecord:`: Called when an item in the list is 
  selected.
