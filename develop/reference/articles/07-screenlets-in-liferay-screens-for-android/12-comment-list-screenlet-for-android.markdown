# Comment List Screenlet for Android [](id=comment-list-screenlet-for-android)

## Requirements [](id=requirements)

- Android SDK 4.0 (API Level 15) or above
- Liferay 7.0 CE, Liferay DXP
- Liferay Screens Compatibility Plugin
  ([CE](http://www.liferay.com/marketplace/-/mp/application/54365664) or 
  [DE](http://www.liferay.com/marketplace/-/mp/application/54369726), 
  depending on your Liferay edition). This app is preinstalled in Liferay 7.0 CE 
  and Liferay DXP instances. 

## Compatibility [](id=compatibility)

- Android SDK 4.0 (API Level 15) and above

## Features [](id=features)

Comment List Screenlet can list all the comments of an asset in a Liferay 
instance. It also lets the user update or delete comments. 

## Module [](id=module)

- None

## Views [](id=views)

- Default

The Default View uses an 
[Android `RecyclerView`](https://developer.android.com/training/material/lists-cards.html) 
to show an asset's comments. Other Views may use a different component, such as 
`TableView` or others, to show the items. 

![Figure 1: Comment List Screenlet using the Default View.](../../images/screens-android-commentlist.png)

## Offline [](id=offline)

This Screenlet supports offline mode so it can function without a network 
connection. For more information on how offline mode works, see the 
[tutorial on its architecture](/develop/tutorials/-/knowledge_base/7-0/architecture-of-offline-mode-in-liferay-screens). 
Here are the offline mode policies that you can use with this Screenlet: 

| Policy | What happens | When to use |
|--------|--------------|-------------|
| `REMOTE_ONLY` | The Screenlet loads the comments from the Liferay instance. If a connection issue occurs, the Screenlet uses the listener to notify the developer about the error. If the Screenlet successfully loads the comments, it stores the data in the local cache for later use. | Use this policy when you always need to show updated data, and show nothing when there's no connection. |
| `CACHE_ONLY` | The Screenlet loads the comments from the local cache. If the data isn't there, the Screenlet uses the listener to notify the developer about the error. | Use this policy when you always need to show local data, without retrieving remote information under any circumstance. |
| `REMOTE_FIRST` | The Screenlet loads the comments from the Liferay instance. If this succeeds, the Screenlet shows the data to the user and stores it in the local cache for later use. If a connection issue occurs, the Screenlet retrieves the data from the local cache. If the data doesn't exist there, the Screenlet uses the listener to notify the developer about the error. | Use this policy to show the most recent version of the data when connected, but show an outdated version when there's no connection. |
| `CACHE_FIRST` | The Screenlet loads the comments from the local cache. If the data isn't there, the Screenlet requests it from the Liferay instance and notifies the developer about any errors that occur (including connectivity errors). | Use this policy to save bandwidth and loading time in case you have local (but probably outdated) data. |

## Required Attributes [](id=required-attributes)

- `className`
- `classPK`

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------|
| `layoutId` | `@layout` | The layout to use to show the View. |
| `autoLoad` | `boolean` | Whether the list should automatically load when the Screenlet appears in the app's UI. The default value is `true`. |
| `cachePolicy` | `string` | The offline mode setting. See [the Offline section](/develop/reference/-/knowledge_base/7-0/comment-list-screenlet-for-android#offline) for details. |
| `className` | `string` | The asset's fully qualified class name. For example, a blog entry's `className` is [`com.liferay.blogs.kernel.model.BlogsEntry`](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/blogs/kernel/model/BlogsEntry.html). The `className` and `classPK` attributes are required to instantiate the Screenlet. |
| `classPK` | `number` | The asset’s unique identifier. The `className` and `classPK` attributes are required to instantiate the Screenlet. |
| `firstPageSize` | `number` | The number of items to retrieve from the server for display on the first page. The default value is `50`. |
| `pageSize` | `number` | The number of items to retrieve from the server for display on the second and subsequent pages. The default value is `25`. |
| `labelFields` | `string` | The comma-separated names of the DDL fields to show. Refer to the list's data definition to find the field names. For more information on this, see [the article on structured web content](/discover/portal/-/knowledge_base/7-0/designing-uniform-content). Note that the appearance of data from a structure's fields depends on the `layoutId`. |
| `editable` | `boolean` | Whether the user can edit the comment. |

## Methods [](id=methods)

| Method | Return | Explanation |
|-----------|-----------|-------------| 
| `loadPage(pageNumber)` | `void` | Starts the request to load the specified page of records. The page is shown when the response is received. |

## Listener [](id=listener)

Comment List Screenlet delegates some events to a class that implements 
`CommentDisplayListener`. This interface lets you implement the following 
methods: 

- `onLoadCommentSuccess(CommentEntry commentEntry)`: Called when the Screenlet 
  successfully loads the comment. 

- `onDeleteCommentSuccess(CommentEntry commentEntry)`: Called when the Screenlet 
  successfully deletes the comment. 

- `onUpdateCommentSuccess(CommentEntry commentEntry)`: Called when the Screenlet 
  successfully updates the comment. 
