# Rating Screenlet for Android [](id=rating-screenlet-for-android)

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

Rating Screenlet shows an asset's rating. It also lets users update or delete 
the rating. This Screenlet comes with different Views that display ratings as 
thumbs, stars, and emojis. 

## Module [](id=module)

- None

## Views [](id=views)

The default View uses an 
[Android `RatingBar`](https://developer.android.com/reference/android/widget/RatingBar.html) 
to show an asset's rating. Other custom Views may show the rating with a 
different Android component such as `Button`, `ImageButton`, or others. 

This Screenlet has five different Views: 

1. Like
2. Thumbs (default)
3. Stars
4. Reactions
5. Emojis

![Figure 1: Rating Screenlet's different Views.](../../images/screens-android-ratings.png)

## Offline [](id=offline)

This Screenlet supports offline mode so it can function without a network 
connection. For more information on how offline mode works, see the 
[tutorial on its architecture](/develop/tutorials/-/knowledge_base/7-0/architecture-of-offline-mode-in-liferay-screens). 
Here are the offline mode policies that you can use with this Screenlet: 

| Policy | What happens | When to use |
|--------|--------------|-------------|
| `REMOTE_ONLY` | The Screenlet loads the data from the Liferay instance. If a connection issue occurs, the Screenlet uses the listener to notify the developer about the error. If the Screenlet successfully loads the data, it stores it in the local cache for later use. | Use this policy when you always need to show updated data, and show nothing when there's no connection. |
| `CACHE_ONLY` | The Screenlet loads the data from the local cache. If the data isn't there, the Screenlet uses the listener to notify the developer about the error. | Use this policy when you always need to show local data, without retrieving remote information under any circumstance. |
| `REMOTE_FIRST` | The Screenlet loads the data from the Liferay instance. If this succeeds, the Screenlet shows the data to the user and stores it in the local cache for later use. If a connection issue occurs, the Screenlet retrieves the data from the local cache. If the data doesn't exist there, the Screenlet uses the listener to notify the developer about the error. | Use this policy to show the most recent version of the data when connected, but show an outdated version when there's no connection. |
| `CACHE_FIRST` | The Screenlet loads the data from the local cache. If the data isn't there, the Screenlet requests it from the Liferay instance and notifies the developer about any errors that occur (including connectivity errors). | Use this policy to save bandwidth and loading time in case you have local (but probably outdated) data. |

## Required Attributes [](id=required-attributes)

- `entryId`

If you don't use `entryId`, you must use both of the following attributes: 

- `className`
- `classPK`

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------|
| `layoutId` | `@layout` | The ID of the layout to use to show the View. |
| `autoLoad` | `boolean` | Whether the rating loads automatically when the Screenlet appears in the app's UI. The default value is `true`. |
| `editable` | `boolean` | Whether the user can change the rating. |
| `entryId` | `number` | The primary key of the asset with the rating to display. |
| `className` | `string` | The asset's fully qualified class name. For example, a blog entry's `className` is [`com.liferay.blogs.kernel.model.BlogsEntry`](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/blogs/kernel/model/BlogsEntry.html). The `className` attribute is required when using it with `classPK` to instantiate the Screenlet. |
| `classPK` | `number` | The asset’s unique identifier. Only use this attribute when also using `className` to instantiate the Screenlet. |
| `groupId` | `number` | The ID of the site (group) containing the asset. |
| `cachePolicy` | `string` | The offline mode setting. See the [Offline section](/develop/reference/-/knowledge_base/7-0/rating-screenlet-for-android#offline) for details. |

## Methods [](id=methods)

| Method | Return | Explanation |
|-----------|-----------|-------------|
| `load()` | `void` | Starts the request to load the asset's ratings. |

## Listener [](id=listener)

Rating Screenlet delegates some events to an object or class that implements 
[its `RatingListener` interface](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/rating/RatingListener.java). 
Therefore, Rating Screenlet's listener methods are as follows: 

- `onRatingOperationSuccess(AssetRating assetRating)`: Called when the operation 
  finishes successfully and the rating is loaded. 
