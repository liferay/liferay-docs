# Web Content Display Screenlet for Android [](id=webcontentdisplayscreenlet-for-android)

<iframe width="560" height="315" src="https://www.youtube.com/embed/JVxfjAnCve8" frameborder="0" allowfullscreen></iframe>

## Requirements [](id=requirements)

- Android SDK 4.0 (API Level 15) or above
- Liferay Portal 6.2 CE or EE

## Compatibility [](id=compatibility)

- Android SDK 4.0 (API Level 15) and above

## Features [](id=features)

The `WebContentDisplayScreenlet` shows web content elements in your app, 
rendering the web content's inner HTML. The Screenlet also supports i18n, 
rendering contents differently depending on the device's locale.

## Module [](id=module)

- None

## Views [](id=views)

The Default View uses a standard `WebView` to render the HTML.

## Portal Configuration [](id=portal-configuration)

For the `WebContentDisplayScreenlet` to function properly, there should be web 
content in the Liferay instance your app connects to. For more details on web 
content, see the [Web Content Management](/portal/-/knowledge_base/6-2/web-content-management) 
section of the Liferay User Guide. 

## Required Attributes [](id=required-attributes)

- `layoutId`
- `articleId`

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `layoutId` | `@layout` | The layout used to show the View. |
| `groupId` | `number` | The site (group) identifier where the asset is stored. If this value is `0`, the `groupId` specified in `LiferayServerContext` is used. |
| `articleId` | `string` | The identifier of the web content to display. You can find the identifier by clicking *Edit* on the web content in the portal. |
| `classPK` | `number` | The class primary key of the corresponding asset. If got the web contents as an asset (from an AssetListScreenlet for instance), this is the asset's identifier. This attribute will be used only if `articleId` is empty.|
| `templateId` | `number` | The identifier of the template used to render the web content. This is applicable only with [structured web contents](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/advanced-content-with-structures-and-te-liferay-portal-6-2-user-guide-03-en).|
| `autoLoad` | `boolean` | If the content should be retrieved from the portal as soon as the screenlet is shown in the screen. Default value if `true`|
| `javascriptEnabled` | `boolean` | Enables support for JavaScript. Disabled by default. |

## Methods [](id=methods)

| Method | Return | Explanation |
|-----------|-----------|-------------| 
| `load()` | `void` | Starts the request to load the web content. The HTML is rendered when the response is received. |

## Listener [](id=listener)

The `WebContentDisplayScreenlet` delegates some events to an object that 
implements the `WebContentDisplayListener` interface. This interface lets you 
implement the following methods:

- `onWebContentReceived(WebContentDisplayScreenlet source, String html)`: Called 
  when the web content's HTML is received. To make some adaptations, the 
  listener may return a modified version of the HTML. The original HTML is 
  rendered if the listener returns `null`. 

- `onWebContentFailure(WebContentDisplayScreenlet source, Exception e)`: Called 
  when an error occurs in the process. 
