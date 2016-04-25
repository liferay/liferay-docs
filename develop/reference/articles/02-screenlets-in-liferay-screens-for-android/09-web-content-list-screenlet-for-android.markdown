# WebContentList Screenlet for Android [](id=webcontentlistscreenlet-for-android)

## Requirements [](id=requirements)

- Android SDK 4.0 (API Level 15) or above
- Liferay Portal 6.2 CE or EE

## Compatibility [](id=compatibility)

- Android SDK 4.0 (API Level 15) and above

## Features [](id=features)

The `WebContentList` has the following features:

- Shows a scrollable collection of WebContent records.
- Implements [fluent pagination](http://www.iosnomad.com/blog/2014/4/21/fluent-pagination) 
  with configurable page size.
- Supports i18n in WebContent values.

## Module [](id=module)

- None

## Views [](id=views)

- The Default View uses a standard `RecyclerView` to show the scrollable list. 
  Other Views may use a different component, such as `ViewPager` or others, to 
  show the items.

![The `WebContentListScreenlet` using the Default  viewset.](../../images/screens-android-webcontentlist.png)

## Offline [](id=offline)

This Screenlet supports offline mode so it can function without a network 
connection. 

| Policy | What happens | When to use |
|--------|--------------|-------------|
| `REMOTE_ONLY` | The Screenlet loads the list from the portal. If a connection issue occurs, the Screenlet uses the listener to notify the developer about the error. If the Screenlet successfully loads the list, it stores the data in the local cache for later use. | Use this policy when you always need to show updated data, and show nothing when there's no connection. |
| `CACHE_ONLY` | The Screenlet loads the list from the local cache. If the list isn't there, the Screenlet uses the listener to notify the developer about the error. | Use this policy when you always need to show local data, without retrieving remote information under any circumstance. |
| `REMOTE_FIRST` | The Screenlet loads the list from the portal. If this succeeds, the Screenlet shows the list to the user and stores it in the local cache for later use. If a connection issue occurs, the Screenlet retrieves the list from the local cache. If the list doesn't exist there, the Screenlet uses the listener to notify the developer about the error. | Use this policy to show the most recent version of the data when connected, but show an outdated version when there's no connection. |
| `CACHE_FIRST` | The Screenlet loads the list from the local cache. If the list isn't there, the Screenlet requests it from the portal and notifies the developer about any errors that occur (including connectivity errors). | Use this policy to save bandwidth and loading time in case you have local (but probably outdated) data. |

## Required Attributes [](id=required-attributes)

- `folderId`
- `labelFields`

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `layoutId` | `@layout` | The layout to use to show the View. |
| `autoLoad` | `boolean` | Defines whether the list should be loaded when it's presented on the screen. The default value is `true`. |
| `firstPageSize` | `number` | The number of items to retrieve from the server for display on the first page. The default value is `50`. |
| `pageSize` | `number` | The number of items to retrieve from the server for display on the second and subsequent pages. The default value is `25`. |
| `folderId` | `number` | The ID of the folder being rendered. |
| `labelFields` | `string` | The comma separated names of the DDM fields to show. Refer to the list's data definition to find the field names. To do so, click *Admin* &rarr; *Content* from the Dockbar. Then click *Web Content* on the left and click the *Manage*, *Structures* dropdown. You can view the fields by clicking on any of the data definitions in the table. Note that the appearance of these values depends on the `layoutId` set. |

## Methods [](id=methods)

| Method | Return | Explanation |
|-----------|-----------|-------------| 
| `loadPage(pageNumber)` | `void` | Starts the request to load the specified page of records. The page is shown when the response is received. |

## Listener [](id=listener)

The `WebContentListScreenlet` delegates some events to an object that implements the 
`WebContentListListener` interface. This interface extends from `BaseListListener` and 
lets you implement the following methods: 

- `onListPageReceived(BaseListScreenlet source, int page, 
  List<WebContent> entries, int rowCount)`: Called when a page of web contents is 
  received. Note that this method may be called more than once; once for each 
  page received.

- `onListPageFailed(BaseListScreenlet source, int page, Exception e)`: Called 
  when an error occurs in the process.

- `onListItemSelected(BaseListScreenlet source, WebContent webContent)`: Called when an 
  item in the list is selected.
