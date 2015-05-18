# DDLListScreenlet for Android [](id=ddllistscreenlet-for-android)

## Requirements [](id=requirements)

- Android SDK 4.0 (API Level 14) and above
- Liferay Portal 6.2 CE or EE
- Mobile Widgets plugin

## Compatibility [](id=compatibility)

- Android SDK 4.0 (API Level 14) and above

## Features [](id=features)

The `DDLListScreenlet` enables the following features:

- Shows a scrollable collection of Dynamic Data List (DDL) records.
- Implements [fluent pagination](http://www.iosnomad.com/blog/2014/4/21/fluent-pagination) 
  with configurable page size.
- Allows record filtering by creator.
- Supports i18n in record values.

## Module [](id=module)

- DDL

## Views [](id=views)

- The Default view uses a standard `RecyclerView` to show the scrollable list. 
  Other views may use a different component, such as `ViewPager` or others, to 
  show the items.

![The DDLList screenlet using the Default and Material viewsets.](../../images/screens-android-ddllist.png)

## Portal Configuration [](id=portal-configuration)

DDLs and Data Types should be properly configured in the portal before using 
`DDLListScreenlet`. For more details, see the Liferay User Guide sections 
[Defining Data Types](/portal/-/knowledge_base/6-2/building-a-list-platform-in-liferay-and-defining-data-) 
and [Creating Data Lists](/portal/-/knowledge_base/6-2/creating-data-lists).

## Required Attributes

- `layoutId`
- `recordSetId`
- `labelFields`

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `layoutId` | `@layout` | The layout to use to show the view. |
| `autoLoad` | `boolean` | Whether the list should be loaded when it's presented on the screen. The default value is `true`. |
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

| Method | Explanation |
|-----------|-------------| 
|  <pre>onListPageReceived(<br/>      BaseListScreenlet source, <br/>      int page,<br/>      List<DDLEntry> entries,<br/>      int rowCount)</pre> | Called when a page of records is received. Note that this method may be called more than once; once for each page received.|
|  <pre>onListPageFailed(<br/>      BaseListScreenlet source, <br/>      int page,<br/>      Exception e)</pre> | Called when an error occurs in the process. |
|  <pre>onListItemSelected(<br/>      BaseListScreenlet source, <br/>      DDLEntry entry)</pre> | Called when an item in the list is selected. |
