# Image Gallery Screenlet for Android [](id=image-gallery-screenlet-for-android)

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

Image Gallery Screenlet shows a list of images from a Documents and Media folder in a 
Liferay instance. You can also use Image Gallery Screenlet to upload images to and 
delete images from the same folder. The Screenlet implements fluent pagination 
with configurable page size, and supports i18n in asset values. 

## Module [](id=module)

- None

## Views [](id=views)

The included Views use a standard Android `RecyclerView` to show the scrollable 
list. Other custom Views may use a different component, such as `ViewPager` or 
others, to show the items. 

This Screenlet has three different Views:

1. Grid (default)
2. Slideshow
3. List

![Figure 1: Image Gallery Screenlet using the Grid, Slideshow, and List Views.](../../images/screens-android-imagegallery.png)

## Offline [](id=offline)

This Screenlet supports offline mode so it can function without a network 
connection when loading or uploading images (deleting images while offline is 
unsupported). For more information on how offline mode works, see the 
[tutorial on its architecture](/develop/tutorials/-/knowledge_base/7-0/architecture-of-offline-mode-in-liferay-screens). 
This Screenlet supports the `REMOTE_ONLY`, `CACHE_ONLY`, `REMOTE_FIRST`, and 
`CACHE_FIRST` offline mode policies. 

These policies take the following actions when loading images from a Liferay 
instance: 

| Policy | What happens | When to use |
|--------|--------------|-------------|
| `REMOTE_ONLY` | The Screenlet loads the list from the Liferay instance. If a connection issue occurs, the Screenlet uses the listener to notify the developer about the error. If the Screenlet successfully loads the list, it stores the data in the local cache for later use. | Use this policy when you always need to show updated data, and show nothing when there's no connection. |
| `CACHE_ONLY` | The Screenlet loads the list from the local cache. If the list isn't there, the Screenlet uses the listener to notify the developer about the error. | Use this policy when you always need to show local data, without retrieving remote information under any circumstance. |
| `REMOTE_FIRST` | The Screenlet loads the list from the Liferay instance. If this succeeds, the Screenlet shows the list to the user and stores it in the local cache for later use. If a connection issue occurs, the Screenlet retrieves the list from the local cache. If the list doesn't exist there, the Screenlet uses the listener to notify the developer about the error. | Use this policy to show the most recent version of the data when connected, but show an outdated version when there's no connection. |
| `CACHE_FIRST` | The Screenlet loads the list from the local cache. If the list isn't there, the Screenlet requests it from the Liferay instance and notifies the developer about any errors that occur (including connectivity errors). | Use this policy to save bandwidth and loading time in case you have local (but probably outdated) data. |

These policies take the following actions when uploading an image to a Liferay 
instance: 

| Policy | What happens | When to use |
|--------|-----------|---------------|
| `REMOTE_ONLY` | The Screenlet sends the image to the Liferay instance. If a connection issue occurs, the Screenlet uses the delegate to notify the developer about the error, but it also discards the image. | Use this policy to make sure the Liferay instance always has the most recent version of the image. |
| `CACHE_ONLY` | The Screenlet stores the image in the local cache. | Use this policy when you need to save the image locally, but don't want to update the image in the Liferay instance (delete or add image). |
| `REMOTE_FIRST` | The Screenlet sends the image to the Liferay instance. If this succeeds, it also stores the image in the local cache for later use. If a connection issue occurs, the Screenlet stores the image in the local cache and sends it to the Liferay instance when the connection is re-established. | Use this policy when you need to make sure the Screenlet sends the image to the Liferay instance as soon as the connection is restored. |
| `CACHE_FIRST` | The Screenlet stores the image in the local cache and then attempts to send it to the Liferay instance. If a connection issue occurs, the Screenlet sends the image to the Liferay instance when the connection is re-established. | Use this policy when you need to make sure the Screenlet sends the image to the Liferay instance as soon as the connection is restored. Compared to `REMOTE_FIRST`, this policy always stores the image in the cache. The `REMOTE_FIRST` policy only stores the image in the event of a network error. |

## Required Attributes [](id=required-attributes)

- `folderId`
- `repositoryId`

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------|
| `repositoryId` | `number` | The ID of the Liferay instance's Documents and Media repository that contains the image gallery. If you're using a site's default Documents and Media repository, then the `repositoryId` matches the site ID (`groupId`). |
| `folderId` | `number` | The ID of the Documents and Media repository folder that contains the image gallery. When accessing the folder in your browser, the `folderId` is at the end of the URL. |
| `cachePolicy` | `string` | The offline mode setting. See the [Offline section](/develop/reference/-/knowledge_base/7-0/gallery-screenlet-for-android#offline) for details. |
| `firstPageSize` | `number` | The number of items to display on the first page. The default value is `50`. |
| `pageSize` | `number` | The number of items to display on second and subsequent pages. The default value is `25`. |
| `mimeTypes` | `string` | The comma-separated list of MIME types for the Screenlet to support. |
| `autoLoad` | `boolean` | Whether the list automatically loads when the Screenlet appears in the app's UI. The default value is `true`. |
| `layoutId` | `@layout` | The layout to use to show the View. |
| `obcClassName` | `string` | The name of the `OrderByComparator` class to use to sort the results. Omit this property if you don't want to sort the results. Note that you can only use comparator classes that extend `OrderByComparator<DLFileEntry>`. Liferay contains no such comparator classes. You must therefore create your own by extending `OrderByComparator<DLFileEntry>`. To see examples of some comparator classes that extend other Document Library classes, [click here](https://github.com/liferay/liferay-portal/tree/master/portal-impl/src/com/liferay/portlet/documentlibrary/util/comparator). |

## Methods [](id=methods)

| Method | Return | Explanation |
|-----------|-----------|-------------| 
| `loadPage(pageNumber)` | `void` | Starts the request to load the specified page of images. The page is shown when the response is received. |

## Listener [](id=listener)

Image Gallery Screenlet delegates some events to an object or class that implements 
[its `ImageGalleryListener` interface](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/imagegallery/ImageGalleryListener.java). 
This interface extends 
[the `BaseListListener` interface](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListListener.java). 
Therefore, Image Gallery Screenlet's listener methods are as follows: 

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
  
- `onImageEntryDeleted(long imageEntryId)`: Called when an item in the list is 
  deleted.

- `onImageUploadStarted(String picturePath, String title, String description, String changelog)`: 
  Called when an item is prepared for upload. 

- `onImageUploadProgress(int totalBytes, int totalBytesSent)`: Called when an 
  item is uploading. 

- `onImageUploadEnd(ImageEntry entry)`: Called when an item finishes uploading. 

- `showUploadImageView(String actionName, String picturePath, int screenletId)`: 
  Called when the View for uploading an image is instantiated. The default 
  behavior is to show the default View in a dialog. To retain this behavior, all 
  this method needs to do is return `false`. To change the default behavior, use 
  the `initializeUploadView` method to initialize a custom View that extends 
  `BaseDetailUploadView`. Then return `true` to prevent the Screenlet from 
  executing the default behavior. For example, the following sample 
  implementation uses `initializeUploadView` to initialize the custom View 
  instance `uploadDetailView`. It then performs a custom UI action 
  (`uploadImageCard.goRight()`) and returns `true`: 

        @Override
        public boolean showUploadImageView(String actionName, String picturePath, int screenletId) {
            uploadDetailView.initializeUploadView(actionName, picturePath, screenletId);
            uploadImageCard.goRight();

            return true;
        }

- `provideImageUploadDetailView()`: Called when the Screenlet provides the image 
  upload View. To inflate the default View, return `0` in this method. 
  Alternatively, display this View with a custom layout by returning its layout 
  ID. Such a layout must have `DefaultUploadDetailView` as its root class. 
