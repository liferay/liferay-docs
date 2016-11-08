# Image Gallery Screenlet for iOS [](id=image-gallery-screenlet-for-ios)

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

Image Gallery Screenlet shows a list of images from a Documents and Media folder 
in a Liferay instance. You can also use Image Gallery Screenlet to upload images 
to and delete images from the same folder. The Screenlet implements 
[fluent pagination](http://www.iosnomad.com/blog/2014/4/21/fluent-pagination) 
with configurable page size, and supports i18n in asset values. 

## Module [](id=module)

- None

## Themes [](id=themes)

The default Theme uses a standard iOS `UICollectionView` to show the scrollable 
list as a grid. Other Themes may use a different component, such as 
`UITableView` or others, to show the contents. 

This screenlet has three different Themes: 

1. Grid (default)
2. Slideshow
3. List

![Figure 1: Image Gallery Screenlet using the Grid, Slideshow, and List Themes.](../../images/screens-ios-imagegallery.png)

## Offline [](id=offline)

This Screenlet supports offline mode so it can function without a network 
connection when loading or uploading images (deleting images while offline is 
unsupported). For more information on how offline mode works, see the 
[tutorial on its architecture](/develop/tutorials/-/knowledge_base/7-0/architecture-of-offline-mode-in-liferay-screens). 
This Screenlet supports the `remote-only`, `cache-only`, `remote-first`, and 
`cache-first` offline mode policies. 

These policies take the following actions when loading images from a Liferay 
instance: 

| Policy | What happens | When to use |
|--------|--------------|-------------|
| `remote-only` | The Screenlet loads the data from the Liferay instance. If a connection issue occurs, the Screenlet uses the delegate to notify the developer about the error. If the Screenlet successfully loads the data, it stores it in the local cache for later use. | Use this policy when you always need to show updated data, and show nothing when there's no connection. |
| `cache-only` | The Screenlet loads the data from the local cache. If the data isn't there, the Screenlet uses the delegate to notify the developer about the error. | Use this policy when you always need to show local data, without retrieving remote information under any circumstance. |
| `remote-first` | The Screenlet loads the data from the Liferay instance. If this succeeds, the Screenlet shows the data to the user and stores it in the local cache for later use. If a connection issue occurs, the Screenlet retrieves the data from the local cache. If the data doesn't exist there, the Screenlet uses the delegate to notify the developer about the error. | Use this policy to show the most recent version of the data when connected, but show a possibly outdated version when there's no connection. |
| `cache-first` | The Screenlet loads the data from the local cache. If the data isn't there, the Screenlet requests it from the Liferay instance and notifies the developer about any errors that occur (including connectivity errors). | Use this policy to save bandwidth and loading time in case you have local (but possibly outdated) data. |

These policies take the following actions when uploading an image to a Liferay 
instance: 

| Policy | What happens | When to use |
|--------|-----------|---------------|
| `remote-only` | The Screenlet sends the image to the Liferay instance. If a connection issue occurs, the Screenlet uses the delegate to notify the developer about the error, but it also discards the image. | Use this policy to make sure the Liferay instance always has the most recent version of the image. |
| `cache-only` | The Screenlet stores the image in the local cache. | Use this policy when you need to save the image locally, but don't want to update it in the Liferay instance. |
| `remote-first` | The Screenlet sends the image to the Liferay instance. If this succeeds, it also stores the image in the local cache for later use. If a connection issue occurs, the Screenlet stores the image in the local cache and sends it to the Liferay instance when the connection is re-established. | Use this policy when you need to make sure the Screenlet sends the image to the Liferay instance as soon as the connection is restored. |
| `cache-first` | The Screenlet stores the image in the local cache and then attempts to send it to the Liferay instance. If a connection issue occurs, the Screenlet sends the image to the Liferay instance when the connection is re-established. | Use this policy when you need to make sure the Screenlet sends the image to the Liferay instance as soon as the connection is restored. Compared to `remote-first`, this policy always stores the image in the cache. The `remote-first` policy only stores the image in the event of a network error. |

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------|
| `repositoryId` | `number` | The ID of the Liferay instance's Documents and Media repository that contains the image gallery. If you're using a site's default Documents and Media repository, then the `repositoryId` matches the site ID (`groupId`). |
| `folderId` | `number` | The ID of the Documents and Media repository folder that contains the image gallery. When accessing the folder in your browser, the `folderId` is at the end of the URL. |
| `mimeTypes` | `string` | The comma-separated list of MIME types for the Screenlet to support. |
| `filePrefix` | `string` | The prefix to use on uploaded image file names. |
| `offlinePolicy` | `string` | The offline mode setting. The default value is `remote-first`. See the [Offline section](/develop/reference/-/knowledge_base/7-0/gallery-screenlet-for-ios#offline) for details. |
| `autoLoad` | `boolean` | Whether the list automatically loads when the Screenlet appears in the app's UI. The default value is `true`. |
| `refreshControl` | `boolean` | Whether a standard [iOS `UIRefreshControl`](https://developer.apple.com/library/ios/documentation/UIKit/Reference/UIRefreshControl_class/) appears when the user does the pull to refresh gesture. The default value is `true`. |
| `firstPageSize` | `number` | The number of items to display on the first page. The default value is `50`. |
| `pageSize` | `number` | The number of items to display on the second and subsequent pages. The default value is `25`. |
| `obcClassName` | `string` | The name of the `OrderByComparator` class to use to sort the results. Omit this property if you don't want to sort the results. Note that you can only use comparator classes that extend `OrderByComparator<DLFileEntry>`. Liferay contains no such comparator classes. You must therefore create your own by extending `OrderByComparator<DLFileEntry>`. To see examples of some comparator classes that extend other Document Library classes, [click here](https://github.com/liferay/liferay-portal/tree/master/portal-impl/src/com/liferay/portlet/documentlibrary/util/comparator). |

## Methods [](id=methods)

| Method | Return | Explanation |
|-----------|-----------|-------------| 
| `loadList()` | `boolean` | Starts the request to load the list of images. This list is shown when the response is received. Returns `true` if the request is sent successfully. |

## Delegate [](id=delegate)

Image Gallery Screenlet delegates some events to an object that conforms to 
the `ImageGalleryScreenletDelegate` protocol. This protocol lets you implement 
the following methods: 

- `- screenlet:onImageEntriesResponse:`: Called when a page of contents is 
  received. Note that this method may be called more than once: one call for 
  each page received. 

- `- screenlet:onImageEntriesError:`: Called when an error occurs in the 
  process. The `NSError` object describes the error. 

- `- screenlet:onImageEntrySelected:`: Called when an item in the list is 
  selected. 

- `- screenlet:onImageEntryDeleted:`: Called when an image in the list is 
  deleted. 

- `- screenlet:onImageEntryDeleteError:`: Called when an error occurs during 
  image file deletion. The `NSError` object describes the error. 

- `- screenlet:onImageUploadStart:`: Called when an image is prepared for 
  upload. 

- `- screenlet:onImageUploadProgress:`: Called when the image upload progress 
  changes.
  
- `- screenlet:onImageUploadError:`: Called when an error occurs in the image 
  upload process. The `NSError` object describes the error. 

- `- screenlet:onImageUploaded:`: Called when the image upload finishes. 

- `- screenlet:onImageUploadDetailViewCreated:`: Called when the image upload 
  View is instantiated. By default, the Screenlet uses a modal view controller 
  to present this View. You only need to implement this method if you want to 
  override this behavior. This method should present the View, passed as 
  parameter, and then return `true`. For example, the following example 
  implementation presents `ImageUploadDetailViewBase` as a parameter, and then 
  uses it to customize the View's appearance: 

        func screenlet(screenlet: ImageGalleryScreenlet, 
            onImageUploadDetailViewCreated uploadView: ImageUploadDetailViewBase) -> Bool {
                self.cardDeck?.cards[safe: 0]?.addPage(uploadView)
                self.cardDeck?.cards[safe: 0]?.moveRight()
                return true
        }
