# Comment Display Screenlet for iOS [](id=comment-display-screenlet-for-ios)

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

Comment Display Screenlet can show one comment of an asset in a Liferay 
instance. It also lets the user update or delete the comment. 

## Module [](id=module)

- None

## Themes [](id=themes)

- Default

The Default Theme uses 
[User Portrait Screenlet](/develop/reference/-/knowledge_base/7-0/userportraitscreenlet-for-ios) 
and iOS `UILabel` elements to show an asset's comment. Other Themes may use 
different components to show the comment. 

![Figure 1: Comment Display Screenlet using the Default Theme.](../../images/screens-ios-commentdisplay.png)

## Offline [](id=offline)

This Screenlet supports offline mode so it can function without a network 
connection. For more information on how offline mode works, see the 
[tutorial on its architecture](/develop/tutorials/-/knowledge_base/7-0/architecture-of-offline-mode-in-liferay-screens). 
This Screenlet supports the `remote-only`, `cache-only`, `remote-first`, and 
`cache-first` offline mode policies. 

These policies take the following actions when loading a comment from a Liferay 
instance: 

| Policy | What happens | When to use |
|--------|--------------|-------------|
| `remote-only` | The Screenlet loads the data from the Liferay instance. If a connection issue occurs, the Screenlet uses the listener to notify the developer about the error. If the Screenlet successfully loads the data, it stores it in the local cache for later use. | Use this policy when you always need to show updated data, and show nothing when there's no connection. |
| `cache-only` | The Screenlet loads the data from the local cache. If the data isn't there, the Screenlet uses the listener to notify the developer about the error. | Use this policy when you always need to show local data, without retrieving remote information under any circumstance. |
| `remote-first` | The Screenlet loads the data from the Liferay instance. If this succeeds, the Screenlet shows the data to the user and stores it in the local cache for later use. If a connection issue occurs, the Screenlet retrieves the data from the local cache. If the data doesn't exist there, the Screenlet uses the listener to notify the developer about the error. | Use this policy to show the most recent version of the data when connected, but show an outdated version when there's no connection. |
| `cache-first` | The Screenlet loads the data from the local cache. If the data isn't there, the Screenlet requests it from the Liferay instance and notifies the developer about any errors that occur (including connectivity errors). | Use this policy to save bandwidth and loading time in case you have local (but probably outdated) data. |

These policies take the following actions when updating or deleting a comment in 
a Liferay instance: 

| Policy | What happens | When to use |
|--------|-----------|---------------|
| `remote-only` | The Screenlet sends the data to the Liferay instance. If a connection issue occurs, the Screenlet uses the delegate to notify the developer about the error, but it also discards the data. | Use this policy to make sure the Liferay instance always has the most recent version of the data. |
| `cache-only` | The Screenlet stores the data in the local cache. | Use this policy when you need to save the data locally, but don't want to update it in the Liferay instance. |
| `remote-first` | The Screenlet sends the data to the Liferay instance. If this succeeds, it also stores the data in the local cache for later use. If a connection issue occurs, the Screenlet stores the data in the local cache and sends it to the Liferay instance when the connection is re-established. | Use this policy when you need to make sure the Screenlet sends the data to the Liferay instance as soon as the connection is restored. |
| `cache-first` | The Screenlet stores the data in the local cache and then attempts to send it to the Liferay instance. If a connection issue occurs, the Screenlet sends the data to the Liferay instance when the connection is re-established. | Use this policy when you need to make sure the Screenlet sends the data to the Liferay instance as soon as the connection is restored. Compared to `remote-first`, this policy always stores the data in the cache. The `remote-first` policy only stores the data in the event of a network error. |

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------|
| `commentId` | `number` | The primary key of the comment to display. |
| `autoLoad` | `boolean` | Whether the list should automatically load when the Screenlet appears in the app's UI. The default value is `true`. |
| `editable` | `boolean` | Whether the user can edit the comment. |
| `offlinePolicy` | `string` | The offline mode setting. The default is `remote-first`. See [the Offline section](/develop/reference/-/knowledge_base/7-0/comment-display-screenlet-for-ios#offline) for details. |

## Methods [](id=methods)

| Method | Return | Explanation |
|-----------|-----------|-------------| 
| `load()` | none | Starts the request to load the comment. |

## Delegate [](id=delegate)

Comment Display Screenlet delegates some events to an object that conforms to 
the `CommentDisplayScreenletDelegate` protocol. This protocol lets you implement 
the following methods: 

- `- screenlet:onCommentLoaded:`: Called when the Screenlet loads the comment. 

- `- screenlet:onLoadCommentError:`: Called when an error occurs in the process. 
  The `NSError` object describes the error.

- `- screenlet:onSelectedComment:`: Called when a comment is selected. 

- `- screenlet:onDeletedComment:`: Called when a comment is deleted. 

- `- screenlet:onCommentDelete:`: Called when the Screenlet prepares the comment 
  for deletion. 

- `- screenlet:onUpdatedComment:`: Called when a comment is updated. 

- `- screenlet:onCommentUpdate:`: Called when the Screenlet prepares the comment 
  for update. 
