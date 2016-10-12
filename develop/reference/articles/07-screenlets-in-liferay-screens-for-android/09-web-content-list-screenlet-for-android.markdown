# Web Content List Screenlet for Android [](id=web-content-list-screenlet-for-android)

## Requirements [](id=requirements)

- Android SDK 4.0 (API Level 15) or above
- Liferay Portal 6.2 (CE or EE), Liferay 7.0 CE, Liferay DXP

## Compatibility [](id=compatibility)

- Android SDK 4.0 (API Level 15) and above

## Features [](id=features)

Web Content List Screenlet has the following features:

- Shows a scrollable collection of 
  [web content](/discover/portal/-/knowledge_base/7-0/creating-web-content) 
  articles. 
- Implements 
  [fluent pagination](http://www.iosnomad.com/blog/2014/4/21/fluent-pagination) 
  with configurable page size. 
- Supports i18n in web content values. 

## Module [](id=module)

- None

## Views [](id=views)

- Default

The Default View uses a standard `RecyclerView` to show the scrollable list. 
Other Views may use a different component, such as `ViewPager` or others, to 
show the items. 

![Figure 1: The Web Content List Screenlet using the Default View.](../../images/screens-android-webcontentlist.png)

## Offline [](id=offline)

This Screenlet supports offline mode so it can function without a network 
connection. For more information on how offline mode works, see the 
[tutorial on its architecture](/develop/tutorials/-/knowledge_base/7-0/architecture-of-offline-mode-in-liferay-screens). 
Here are the offline mode policies that you can use with this Screenlet: 

| Policy | What happens | When to use |
|--------|--------------|-------------|
| `REMOTE_ONLY` | The Screenlet loads the list from the Liferay instance. If a connection issue occurs, the Screenlet uses the listener to notify the developer about the error. If the Screenlet successfully loads the list, it stores the data in the local cache for later use. | Use this policy when you always need to show updated data, and show nothing when there's no connection. |
| `CACHE_ONLY` | The Screenlet loads the list from the local cache. If the list isn't there, the Screenlet uses the listener to notify the developer about the error. | Use this policy when you always need to show local data, without retrieving remote information under any circumstance. |
| `REMOTE_FIRST` | The Screenlet loads the list from the Liferay instance. If this succeeds, the Screenlet shows the list to the user and stores it in the local cache for later use. If a connection issue occurs, the Screenlet retrieves the list from the local cache. If the list doesn't exist there, the Screenlet uses the listener to notify the developer about the error. | Use this policy to show the most recent version of the data when connected, but show a possibly outdated version when there's no connection. |
| `CACHE_FIRST` | The Screenlet loads the list from the local cache. If the list isn't there, the Screenlet requests it from the Liferay instance and notifies the developer about any errors that occur (including connectivity errors). | Use this policy to save bandwidth and loading time in case you have local (but possibly outdated) data. |

## Required Attributes [](id=required-attributes)

- `folderId`
- `labelFields`

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `layoutId` | `@layout` | The ID of the layout to use to show the View. |
| `autoLoad` | `boolean` | Whether the list loads automatically when the Screenlet appears in the app's UI. The default value is `true`. |
| `folderId` | `number` | The ID of the web content folder to retrieve content from. |
| `groupId` | `number` | The ID of the site (group) where the asset is stored. If set to `0`, the `groupId` specified in `LiferayServerContext` is used. The default value is `0`. |
| `cachePolicy` | `string` | The offline mode setting. See the [Offline section](/develop/reference/-/knowledge_base/7-0/web-content-list-screenlet-for-android#offline) for details. |
| `firstPageSize` | `number` | The number of items to retrieve from the server for display on the first page. The default value is `50`. |
| `pageSize` | `number` | The number of items to retrieve from the server for display on the second and subsequent pages. The default value is `25`. |
| `labelFields` | `string` | The comma-separated names of the DDM fields to show. Refer to the list's data definition to find the field names. For more information on this, see [the article on structured web content](/discover/portal/-/knowledge_base/7-0/designing-uniform-content). Note that the appearance of data from a structure's fields depends on the `layoutId`. |
| `obcClassName` | `string` | The name of the `OrderByComparator` class to use to sort the results. Omit this property if you don't want to sort the results. [Click here](https://github.com/liferay/liferay-portal/tree/master/modules/apps/web-experience/journal/journal-api/src/main/java/com/liferay/journal/util/comparator) to see some comparator classes. Note, however, that not all of these classes can be used with `obcClassName`. You can only use comparator classes that extend `OrderByComparator<JournalArticle>`. You can also create your own comparator classes that extend `OrderByComparator<JournalArticle>`. |

## Methods [](id=methods)

| Method | Return | Explanation |
|--------|--------|-------------| 
| `loadPage(pageNumber)` | `void` | Starts the request to load the specified page of records. The page is shown when the response is received. |

## Listener [](id=listener)
Web Content List Screenlet delegates some events to an object or a class that 
implements 
[the `BaseListListener` interface](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListListener.java). 
This interface lets you implement the following methods: 

- `onListPageFailed(int startRow, Exception e)`: Called when the server call to 
  retrieve a page of items fails. This method's arguments include the 
  `Exception` generated when the server call fails. 

- `onListPageReceived(int startRow, int endRow, List<Record> records, int rowCount)`: 
  Called when the server call to retrieve a page of items succeeds. Note that 
  this method may be called more than once; once for each page received. Because 
  `startRow` and `endRow` change for each page, a `startRow` of `0` corresponds 
  to the first item on the first page. 

- `onListItemSelected(Record records, View view)`: Called when an item is 
  selected in the list. This method's arguments include the selected list item 
  (`Record`). 
