# AssetListScreenlet for Android [](id=assetlistscreenlet-for-android)

## Requirements [](id=requirements)

- Android SDK 4.0 (API Level 14) and above
- Liferay Portal 6.2 CE or EE
- Mobile Widgets plugin

## Compatibility [](id=compatibility)

- Android SDK 4.0 (API Level 14) and above

## Features [](id=features)

The `AssetListScreenlet` can be used to show [asset](/tutorials/-/knowledge_base/6-2/asset-framework) 
lists from a Liferay instance. For example, you can use the screenlet to show a 
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

The Default views use a standard `RecyclerView` to show the scrollable list. 
Other views may use a different component, such as `ViewPager` or others, to 
show the items.

![`AssetListScreenlet` using the Default and Material viewsets.](../../images/screens-android-assetlist.png)

## Portal Configuration [](id=portal-configuration)

Dynamic Data Lists (DDL) and Data Types should be configured properly in the 
portal. Refer to the [Defining Data Types](/portal/-/knowledge_base/6-2/building-a-list-platform-in-liferay-and-defining-data-) 
and [Creating Data Lists](/portal/-/knowledge_base/6-2/creating-data-lists) 
sections of the User Guide for more details.

## Required Attributes

- `layoutId`
- `classNameId`

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `layoutId` | `@layout` | The layout to use to show the view.|
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

| Method | Explanation |
|-----------|-------------| 
|  <pre>onListPageReceived(<br/>      BaseListScreenlet source, <br/>      int page,<br/>      List<AssetEntry> entries,<br/>      int rowCount)</pre> | Called when a page of assets is received. Note that this method may be called more than once; once for each page received. |
|  <pre>onListPageFailed(<br/>      BaseListScreenlet source, <br/>      int page,<br/>      Exception e)</pre> | Called when an error occurs in the process. |
|  <pre>onListItemSelected(<br/>      BaseListScreenlet source, <br/>      AssetEntry entry)</pre> | Called when an item in the list is selected. |
