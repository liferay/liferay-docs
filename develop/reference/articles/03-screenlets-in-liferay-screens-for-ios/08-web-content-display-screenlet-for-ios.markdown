# Web Content Display Screenlet for iOS [](id=webcontentdisplayscreenlet-for-ios)

<iframe width="560" height="315" src="https://www.youtube.com/embed/ODfb_4igvCs" frameborder="0" allowfullscreen></iframe>

## Requirements [](id=requirements)

- Xcode 7.2
- iOS 9 SDK
- Liferay Portal 6.2 CE or EE

## Compatibility [](id=compatibility)

- iOS 7 and above

## Features [](id=features)

The `WebContentDisplayScreenlet` shows web content elements in your app, 
rendering the inner HTML of the web content. The Screenlet also supports i18n, 
rendering contents differently depending on the device's current locale. 

## Module [](id=module)

- None

## Themes [](id=themes)

The Default Theme uses a standard `UIWebView` to render the HTML. Other Themes 
may use a different component, such as iOS 8's. 

![The `WebContentDisplayScreenlet` using the Default (`default`) Theme](../../images/screens-ios-webcontent.png)

## Portal Configuration [](id=portal-configuration)

For the `WebContentDisplayScreenlet` to function properly, there should be web 
content in the Liferay instance your app connects to. For more details on web 
content, please refer to the [Web Content Management](/portal/-/knowledge_base/6-2/web-content-management) 
section of the Liferay User Guide. 

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `groupId` | `number` | The site (group) identifier where the asset is stored. If this value is `0`, the `groupId` specified in `LiferayServerContext` is used. |
| `articleId` | `string` | The identifier of the web content to display. You can find the identifier by clicking *Edit* on the web content in the portal. |
| `classPK` | `number` | The class primary key of the corresponding asset. If got the web contents as an asset (from an AssetListScreenlet for instance), this is the asset's identifier. This attribute will be used only if `articleId` is empty.|
| `templateId` | `number` | The identifier of the template used to render the web content. This is applicable only with [structured web contents](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/advanced-content-with-structures-and-te-liferay-portal-6-2-user-guide-03-en).|
| `autoLoad` | `boolean` | If the content should be retrieved from the portal as soon as the screenlet is shown in the screen. Default value if `true`|

## Methods [](id=methods)

| Method | Return | Explanation |
|-----------|-----------|-------------| 
|  `loadWebContent()` | `boolean` | Starts the request to load the web content. The HTML is rendered when the response is received. Returns `true` if the request is sent. |

## Delegate [](id=delegate)

The `WebContentDisplayScreenlet` delegates some events to an object that 
conforms to the `WebContentDisplayScreenletDelegate` protocol. This protocol 
lets you implement the following methods:

- `- screenlet:onWebContentResponse:`: Called when the web content's HTML is 
  received. 

- `- screenlet:onWebContentDisplayError:`: Called when an error occurs in the 
  process. The `NSError` object describes the error. 
