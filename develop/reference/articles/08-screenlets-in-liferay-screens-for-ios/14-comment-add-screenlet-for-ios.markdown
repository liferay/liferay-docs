# Comment Add Screenlet for iOS [](id=comment-add-screenlet-for-ios)

## Requirements [](id=requirements)

- Xcode 7.2
- iOS 9 SDK
- Liferay 7.0 CE, Liferay DXP 
- Liferay Screens Compatibility Plugin 
  ([CE](http://www.liferay.com/marketplace/-/mp/application/54365664) or 
  [DE](http://www.liferay.com/marketplace/-/mp/application/54369726), 
  depending on your portal edition). This app is preinstalled in Liferay 7.0 CE 
  and Liferay DXP instances. 

## Compatibility [](id=compatibility)

- iOS 8 and above

## Features [](id=features)

Comment Add Screenlet can add a comment to an asset in a Liferay instance. 

## Module [](id=module)

- None

## Themes [](id=themes)

- Default

The Default Theme uses the iOS elements `UITextField` and `UIButton` to add a 
comment to an asset. Other Themes may use other components to show the comment.

![Figure 1: Comment Add Screenlet using the Default Theme.](../../images/screens-ios-commentadd.png)

## Offline [](id=offline)

This Screenlet supports offline mode so it can function without a network 
connection. For more information on how offline mode works, see the 
[tutorial on its architecture](/develop/tutorials/-/knowledge_base/7-0/architecture-of-offline-mode-in-liferay-screens). 
Here are the offline mode policies that you can use with this Screenlet: 

| Policy | What happens | When to use |
|--------|--------------|-------------|
| `remote-only` | The Screenlet sends the data to the Liferay instance. If a connection issue occurs, the Screenlet uses the listener to notify the developer about the error. If the Screenlet successfully sends the data, it also stores it in the local cache. | Use this policy when you always need to send updated data, and send nothing when there's no connection. |
| `cache-only` | The Screenlet sends the data to the local cache. If an error occurs, the Screenlet uses the listener to notify the developer. | Use this policy when you always need to store local data without sending remote information under any circumstance. |
| `remote-first` | The Screenlet sends the data to the Liferay instance. If this succeeds, the Screenlet also stores the data in the local cache. If a connection issue occurs, the Screenlet stores the data to the local cache and sends it to the Liferay instance when the connection is restored. If an error occurs, the Screenlet uses the listener to notify the developer. | Use this policy to send the most recent version of the data when connected, and store the data for later synchronization when there's no connection. |
| `cache-first` | The Screenlet sends the data to the local cache, then sends it to the Liferay instance. If sending the data to the Liferay instance fails, the Screenlet still stores the data locally and then notifies the developer about any errors that occur (including connectivity errors). | Use this policy to save bandwidth and store local (but possibly outdated) data. |

## Required Attributes [](id=required-attributes)

- `className`
- `classPK`

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------|
| `className` | `string` | The asset's fully qualified class name. For example, a blog entry's `className` is [`com.liferay.blogs.kernel.model.BlogsEntry`](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/blogs/kernel/model/BlogsEntry.html). The `className` and `classPK` attributes are required to instantiate the Screenlet. | 
| `classPK` | `number` | The asset’s unique identifier. The `className` and `classPK` attributes are required to instantiate the Screenlet. |
| `offlinePolicy` | `string` | The offline mode setting. The default value is `remote-first`. See [the Offline section](/develop/reference/-/knowledge_base/7-0/comment-add-screenlet-for-ios#offline) for details. |

## Delegate [](id=delegate)

Comment Add Screenlet delegates some events to an object that conforms to the 
`CommentAddScreenletDelegate` protocol. This protocol lets you implement 
the following methods: 

- `- screenlet:onCommentAdded:`: Called when the Screenlet adds a comment. 

- `- screenlet:onAddCommentError:`: Called when an error occurs while adding a 
  comment. The `NSError` object describes the error. 

- `- screenlet:onCommentUpdated:`: Called when the Screenlet prepares a comment 
  for update. 

- `- screenlet:onUpdateCommentError:`: Called when an error occurs while 
  updating a comment. The `NSError` object describes the error. 
