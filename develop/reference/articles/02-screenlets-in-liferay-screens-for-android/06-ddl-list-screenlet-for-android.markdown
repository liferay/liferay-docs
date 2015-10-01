# DDL List Screenlet for Android [](id=ddllistscreenlet-for-android)

<iframe width="560" height="315" src="https://www.youtube.com/embed/A_QEZzkuGHg" frameborder="0" allowfullscreen></iframe>

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

The `DDLListScreenlet` has the following features:

- Shows a scrollable collection of Dynamic Data List (DDL) records.
- Implements [fluent pagination](http://www.iosnomad.com/blog/2014/4/21/fluent-pagination) 
  with configurable page size.
- Allows record filtering by creator.
- Supports i18n in record values.

## Module [](id=module)

- DDL

## Views [](id=views)

- The Default View uses a standard `RecyclerView` to show the scrollable list. 
  Other Views may use a different component, such as `ViewPager` or others, to 
  show the items.

![The `DDLListScreenlet` using the Default and Material Viewsets.](../../images/screens-android-ddllist.png)

## Portal Configuration [](id=portal-configuration)

DDLs and Data Types should be configured in the portal before using
`DDLListScreenlet`. For more details, see the Liferay User Guide sections 
[Defining Data Types](/portal/-/knowledge_base/6-2/building-a-list-platform-in-liferay-and-defining-data-) 
and [Creating Data Lists](/portal/-/knowledge_base/6-2/creating-data-lists). 

Also, [Liferay Screens' Compatibility Plugin](https://github.com/liferay/liferay-screens/tree/master/portal) 
must be installed to allow remote calls without the `userId`. 

## Required Attributes [](id=required-attributes)

- `layoutId`
- `recordSetId`
- `labelFields`

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `layoutId` | `@layout` | The layout to use to show the View. |
| `autoLoad` | `boolean` | Defines whether the list should be loaded when it's presented on the screen. The default value is `true`. |
| `firstPageSize` | `number` | The number of items to retrieve from the server for display on the first page. The default value is `50`. |
| `pageSize` | `number` | The number of items to retrieve from the server for display on the second and subsequent pages. The default value is `25`. |
| `recordSetId` | `number` | The ID of the DDL being called. To find your DDLs' IDs, click *Admin* &rarr; *Content* from the Dockbar. Then click *Dynamic Data Lists* on the left. Each DDL's ID is in the ID column of the table. |
| `userId` | `number` | The ID of the user to filter records on. Records aren't filtered if the `userId` is `0`. The default value is `0`. |
| `labelFields` | `string` | The comma separated names of the DDL fields to show. Refer to the list's data definition to find the field names. To do so, click *Admin* &rarr; *Content* from the Dockbar. Then click *Dynamic Data Lists* on the left and click the *Manage Data Definitions* button. You can view the fields by clicking on any of the data definitions in the table. Note that the appearance of these values depends on the `layoutId` set. |

## Methods [](id=methods)

| Method | Return | Explanation |
|-----------|-----------|-------------| 
| `loadPage(pageNumber)` | `void` | Starts the request to load the specified page of records. The page is shown when the response is received. |

## Listener [](id=listener)

The `DDLListScreenlet` delegates some events to an object that implements the 
`DDLListListener` interface. This interface extends from `BaseListListener` and 
lets you implement the following methods: 

- `onListPageReceived(BaseListScreenlet source, int page, 
  List<DDLEntry> entries, int rowCount)`: Called when a page of records is 
  received. Note that this method may be called more than once; once for each 
  page received.

- `onListPageFailed(BaseListScreenlet source, int page, Exception e)`: Called 
  when an error occurs in the process.

- `onListItemSelected(BaseListScreenlet source, DDLEntry entry)`: Called when an 
  item in the list is selected.

## Offline [](id=offline)

This screenlet sopport offline mode in order to work under scenarios with bad connectivity.

| Policy | What happens | When to use |
|--------|--------------|-------------|
| `REMOTE_ONLY` | The list will be loaded from the portal. If a connection issue happens, the screenlet will notify about the error through the listener as usual. If the list can be successfully loaded, the received items are stored in the local cache for later usage.| Use this policy when you need to show always updated items and show nothing when there's no connection.|
| `CACHE_ONLY` | The list will be loaded from the local cache. If they're not present, the screenlet will notify about the error through the listener as usual.| Use this policy when you need to show always local items without retrieving remote information under any circumstance.|
| `REMOTE_FIRST` | The list will be requested to the remote portal. If it's received, it will be shown to the user and stored in the local cache for later usage. If a connection issue happens, then it will be retrieved from the local cache. If it doesn't exist there, the screenlet will notify about the error through the listener as usual. | When you need the most updated version if connected, but is accepted to show an outdated version when there's no connection.|
| `CACHE_FIRST` | The items will be loaded from the local cache if exist. If they don't exist, then they will be requested to the portal as usual and will notify about the error in case of issue (even on connectivity errors).| When you want to save bandwidth and loading time in case you have a local (but probably outdated) version.|
