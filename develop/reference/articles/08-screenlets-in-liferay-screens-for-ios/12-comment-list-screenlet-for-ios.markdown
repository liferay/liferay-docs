# Comment List Screenlet for iOS [](id=comment-list-screenlet-for-ios)

## Requirements [](id=requirements)

- Xcode 7.2
- iOS 9 SDK
- Liferay 7.0 CE, Liferay DXP 
- Liferay Screens Compatibility Plugin 
  ([CE](http://www.liferay.com/marketplace/-/mp/application/54365664) or 
  [DE](http://www.liferay.com/marketplace/-/mp/application/54369726), 
  depending on your Liferay edition). This app is preinstalled in Liferay 7.0 CE 
  and Liferay DXP instances. 

## Compatibility [](id=compatibility)

- iOS 8 and above

## Features [](id=features)

Comment List Screenlet can list all the comments of an asset in a Liferay 
instance. It also lets the user update or delete comments. 

## Module [](id=module)

- None

## Themes [](id=themes)

- Default

The Default Theme uses an 
[iOS `UITableView`](https://developer.apple.com/reference/uikit/uitableview) 
to show an asset's comments. Other Themes may use a different component, such as 
[iOS's `UICollectionView`](https://developer.apple.com/reference/uikit/uicollectionview) 
or others, to show the items. 

![Figure 1: Comment List Screenlet using the Default Theme.](../../images/screens-ios-commentlist.png)

## Offline [](id=offline)

This Screenlet supports offline mode so it can function without a network 
connection. For more information on how offline mode works, see the 
[tutorial on its architecture](/develop/tutorials/-/knowledge_base/7-0/architecture-of-offline-mode-in-liferay-screens). 
Here are the offline mode policies that you can use with this Screenlet: 

| Policy | What happens | When to use |
|--------|--------------|-------------|
| `remote-only` | The Screenlet loads the list from the Liferay instance. If a connection issue occurs, the Screenlet uses the delegate to notify the developer about the error. If the Screenlet successfully loads the list, it stores the data in the local cache for later use. | Use this policy when you always need to show updated data, and show nothing when there's no connection. |
| `cache-only` | The Screenlet loads the list from the local cache. If the list isn't there, the Screenlet uses the delegate to notify the developer about the error. | Use this policy when you always need to show local data, without retrieving remote information under any circumstance. |
| `remote-first` | The Screenlet loads the list from the Liferay instance. If this succeeds, the Screenlet shows the list to the user and stores it in the local cache for later use. If a connection issue occurs, the Screenlet retrieves the list from the local cache. If the list doesn't exist there, the Screenlet uses the delegate to notify the developer about the error. | Use this policy to show the most recent version of the data when connected, but show a possibly outdated version when there's no connection. |
| `cache-first` | The Screenlet loads the list from the local cache. If the list isn't there, the Screenlet requests it from the Liferay instance and notifies the developer about any errors that occur (including connectivity errors). | Use this policy to save bandwidth and loading time in case you have local (but possibly outdated) data. |

## Required Attributes [](id=required-attributes)

- `className`
- `classPK`

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------|
| `className` | `string` | The asset's fully qualified class name. For example, a blog entry's `className` is [`com.liferay.blogs.kernel.model.BlogsEntry`](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/blogs/kernel/model/BlogsEntry.html). The `className` and `classPK` attributes are required to instantiate the Screenlet. |
| `classPK` | `number` | The asset’s unique identifier. The `className` and `classPK` attributes are required to instantiate the Screenlet. |
| `offlinePolicy` | `string` | The offline mode setting. The default is `remote-first`. See [the Offline section](/develop/reference/-/knowledge_base/7-0/comment-list-screenlet-for-ios#offline) for details. |
| `editable` | `boolean` | Whether the user can edit the comment. |
| `autoLoad` | `boolean` | Whether the list should automatically load when the Screenlet appears in the app's UI. The default value is `true`. |
| `refreshControl` | `boolean` | Defines whether a standard [iOS `UIRefreshControl`](https://developer.apple.com/library/ios/documentation/UIKit/Reference/UIRefreshControl_class/) is shown when the user does the pull to refresh gesture. The default value is `true`. |
| `firstPageSize` | `number` | The number of items retrieved from the server for display on the first page. The default value is `50`. |
| `pageSize` | `number` | The number of items retrieved from the server for display on the second and subsequent pages. The default value is `25`. |
| `obcClassName` | `string` | The name of the [`OrderByComparator` class](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/OrderByComparator.html) to use to sort the results. You can only use classes that extend `OrderByComparator<MBMessage>`. If you don't want to sort the results, you can omit this property. |

## Methods [](id=methods)

| Method | Return | Explanation |
|-----------|-----------|-------------| 
| `loadList()` | `boolean` | Starts the request to load the list. This list is shown when the response is received. Returns `true` if the request is sent. | 

## Delegate [](id=delegate)

Comment List Screenlet delegates some events to an object that conforms to the 
`ComentListScreenletDelegate` protocol. This protocol lets you implement the 
following methods: 

- `- screenlet:onListResponseComments:`: Called when the Screenlet receives the 
  comments. 

- `- screenlet:onCommentListError:`: Called when an error occurs in the process. 
  The `NSError` object describes the error. 

- `- screenlet:onSelectedComment:`: Called when a comment is selected.

- `- screenlet:onDeletedComment:`: Called when a comment is deleted.

- `- screenlet:onCommentDelete:`: Called when the Screenlet prepares a comment 
  for deletion. 

- `- screenlet:onUpdatedComment:`: Called when a comment is updated. 

- `- screenlet:onCommentUpdate:`: Called when the Screenlet prepares a comment 
  for update. 
