# Rating Screenlet for iOS [](id=rating-screenlet-for-ios)

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

Rating Screenlet shows an asset's rating. It also lets users update or delete 
the rating. This Screenlet comes with different Themes that display ratings as 
thumbs, stars, and emojis. 

## Module [](id=module)

- None

## Themes [](id=themes)

The default Theme uses 
[the `CosmosView` library](https://github.com/marketplacer/Cosmos) 
to show an asset's rating. Other custom Themes may use a different component, 
such as `UIButton` or others, to show the items.

This screenlet has four different Themes: 
 
1. Like 
2. Thumbs (default) 
3. Stars 
4. Emojis 

![Figure 1: Rating Screenlet's different Themes.](../../images/screens-ios-ratings.png)

## Offline [](id=offline)

This Screenlet supports offline mode so it can function without a network 
connection. For more information on how offline mode works, see the 
[tutorial on its architecture](/develop/tutorials/-/knowledge_base/7-0/architecture-of-offline-mode-in-liferay-screens). 
Here are the offline mode policies that you can use with this Screenlet: 

| Policy | What happens | When to use |
|--------|--------------|-------------|
| `remote-only` | The Screenlet loads the data from the Liferay instance. If a connection issue occurs, the Screenlet uses the delegate to notify the developer about the error. If the Screenlet successfully loads the data, it stores it in the local cache for later use. | Use this policy when you always need to show updated data, and show nothing when there's no connection. |
| `cache-only` | The Screenlet loads the data from the local cache. If the data isn't there, the Screenlet uses the delegate to notify the developer about the error. | Use this policy when you always need to show local data, without retrieving remote information under any circumstance. |
| `remote-first` | The Screenlet loads the data from the Liferay instance. If this succeeds, the Screenlet shows the data to the user and stores it in the local cache for later use. If a connection issue occurs, the Screenlet retrieves the data from the local cache. If the data doesn't exist there, the Screenlet uses the delegate to notify the developer about the error. | Use this policy to show the most recent version of the data when connected, but show a possibly outdated version when there's no connection. |
| `cache-first` | The Screenlet loads the data from the local cache. If the data isn't there, the Screenlet requests it from the Liferay instance and notifies the developer about any errors that occur (including connectivity errors). | Use this policy to save bandwidth and loading time in case you have local (but possibly outdated) data. |

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------|
| `layoutId` | `@layout` | The ID of the layout to use to show the Theme. |
| `autoLoad` | `boolean` | Whether the rating loads automatically when the Screenlet appears in the app's UI. The default value is `true`. |
| `editable` | `boolean` | Whether the user can change the rating. |
| `entryId` | `number` | The primary key of the asset with the rating to display. |
| `className` | `string` | The asset's fully qualified class name. For example, a blog entry's `className` is [`com.liferay.blogs.kernel.model.BlogsEntry`](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/blogs/kernel/model/BlogsEntry.html). The `className` attribute is required when using it with `classPK` to instantiate the Screenlet.. |
| `classPK` | `number` | The asset’s unique identifier. Only use this attribute when also using `className` to instantiate the Screenlet. |
| `groupId` | `number` | The ID of the site (group) containing the asset. |
| `offlinePolicy` | `string` | The offline mode setting. See the [Offline section](/develop/reference/-/knowledge_base/7-0/rating-screenlet-for-ios#offline) for details. |

## Methods [](id=methods)

| Method | Return | Explanation |
|-----------|-----------|-------------|
| `loadRatings()` | `boolean` | Starts the request to load the asset's ratings. |

## Delegate [](id=delegate)

Rating Screenlet delegates some events to an object that conforms to 
the `RatingScreenletDelegate` protocol. This protocol lets you implement 
the following methods: 

- `- screenlet:onRatingRetrieve:`: Called when the ratings are received. 

- `- screenlet:onRatingDeleted:`: Called when a rating is deleted. 

- `- screenlet:onRatingUpdated:`: Called when a rating is updated. 

- `- screenlet:onRatingError:`: Called when an error occurs in the process. The 
  `NSError` object describes the error. 
