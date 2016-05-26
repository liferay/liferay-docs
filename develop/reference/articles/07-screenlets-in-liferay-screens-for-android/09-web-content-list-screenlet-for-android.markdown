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

- The Default View uses a standard `RecyclerView` to show the scrollable list. 
  Other Views may use a different component, such as `ViewPager` or others, to 
  show the items. 

![Figure 1: The Web Content List Screenlet using the Default View Set.](../../images/screens-android-webcontentlist.png)

## Offline [](id=offline)

This Screenlet supports offline mode so it can function without a network 
connection. 

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
| `firstPageSize` | `number` | The number of items to retrieve from the server for display on the first page. The default value is `50`. |
| `pageSize` | `number` | The number of items to retrieve from the server for display on the second and subsequent pages. The default value is `25`. |
| `folderId` | `number` | The ID of the folder to render. |
| `labelFields` | `string` | The comma-separated names of the DDM fields to show. Refer to the list's data definition to find the field names. For more information on this, see [the article on structured web content](/discover/portal/-/knowledge_base/7-0/designing-uniform-content). Note that the appearance of data from a structure's fields depends on the `layoutId`. |

## Methods [](id=methods)

| Method | Return | Explanation |
|--------|--------|-------------| 
| `loadPage(pageNumber)` | `void` | Starts the request to load the specified page of records. The page is shown when the response is received. |

## Listener [](id=listener)

Web Content List Screenlet delegates some events to an object that implements 
the `WebContentListListener` interface. This interface extends from 
`BaseListListener` and lets you implement the following methods: 

- `onListPageReceived(BaseListScreenlet source, int page, List<WebContent> entries, int rowCount)`: 
  Called when a page of web content is received. Note that this method may be 
  called more than once: once for each page received. 

- `onListPageFailed(BaseListScreenlet source, int page, Exception e)`: Called 
  when an error occurs in the process. 

- `onListItemSelected(BaseListScreenlet source, WebContent webContent)`: Called 
  when an item in the list is selected. 
