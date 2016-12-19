# PDF Display Screenlet for iOS [](id=pdf-display-screenlet-for-ios)

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

PDF Display Screenlet displays a PDF file from a Liferay Instance's Documents 
and Media Library. 

## Module [](id=module)

- None

## Themes [](id=themes)

- Default

The Default Theme uses an iOS `UIWebView` for displaying the PDF file. 

![Figure 1: PDF Display Screenlet using the Default Theme.](../../images/screens-ios-pdfdisplay.png)

## Offline [](id=offline)

This Screenlet supports offline mode so it can function without a network 
connection. For more information on how offline mode works, see the 
[tutorial on its architecture](/develop/tutorials/-/knowledge_base/7-0/architecture-of-offline-mode-in-liferay-screens). 
Here are the offline mode policies that you can use with this Screenlet: 

| Policy | What happens | When to use |
|--------|--------------|-------------|
| `remote-only` | The Screenlet loads the data from the Liferay instance. If a connection issue occurs, the Screenlet uses the listener to notify the developer about the error. If the Screenlet successfully loads the data, it stores it in the local cache for later use. | Use this policy when you always need to show updated data, and show nothing when there's no connection. |
| `cache-only` | The Screenlet loads the data from the local cache. If the data isn't there, the Screenlet uses the listener to notify the developer about the error. | Use this policy when you always need to show local data, without retrieving remote information under any circumstance. |
| `remote-first` | The Screenlet loads the data from the Liferay instance. If this succeeds, the Screenlet shows the data to the user and stores it in the local cache for later use. If a connection issue occurs, the Screenlet retrieves the data from the local cache. If the data doesn't exist there, the Screenlet uses the listener to notify the developer about the error. | Use this policy to show the most recent version of the data when connected, but show an outdated version when there's no connection. |
| `cache-first` | The Screenlet loads the data from the local cache. If the data isn't there, the Screenlet requests it from the Liferay instance and notifies the developer about any errors that occur (including connectivity errors). | Use this policy to save bandwidth and loading time in case you have local (but probably outdated) data. |

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------|
| `assetEntryId` | `number` | The primary key of the PDF file. | 
| `className` | `string` | The PDF file's fully qualified class name. Since files in a Documents and Media Library are `DLFileEntry` objects, their `className` is [`com.liferay.document.library.kernel.model.DLFileEntry`](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/model/DLFileEntry.html). The `className` and `classPK` attributes are required to instantiate the Screenlet. |
| `classPK` | `number` | The PDF file’s unique identifier. The `className` and `classPK` attributes are required to instantiate the Screenlet. |
| `autoLoad` | `boolean` | Whether the PDF automatically loads when the Screenlet appears in the app's UI. The default value is `true`. |
| `offlinePolicy` | `string` | The offline mode setting. See [the Offline section](/develop/reference/-/knowledge_base/7-0/pdf-display-screenlet-for-ios#offline) for details. |

## Delegate [](id=delegate)

Because PDFs are files, PDF Display Screenlet delegates some events to an object 
that conforms to the `FileDisplayScreenletDelegate` protocol. This protocol lets 
you implement the following methods: 

- `- screenlet:onAssetResponse:`: Called when the Screenlet receives the PDF. 

- `- screenlet:onFileAssetError:`: Called when an error occurs in the process. 
  An `NSError` object describes the error. 
