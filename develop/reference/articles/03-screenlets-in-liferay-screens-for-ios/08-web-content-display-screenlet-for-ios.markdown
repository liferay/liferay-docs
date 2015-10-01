# Web Content Display Screenlet for iOS [](id=webcontentdisplayscreenlet-for-ios)

<iframe width="560" height="315" src="https://www.youtube.com/embed/ODfb_4igvCs" frameborder="0" allowfullscreen></iframe>

## Requirements [](id=requirements)

- XCode 6.3.x
- iOS 8 SDK
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

## Offline [](id=offline)

This screenlet sopport offline mode in order to work under scenarios with bad connectivity.

| Policy | What happens | When to use |
|--------|--------------|-------------|
| `remote-only` | The web content will be loaded from the portal. If a connection issue happens, the screenlet will notify about the error through the delegate as usual. If the web content can be successfully loaded, the received asserts are stored in the local cache for later usage.| Use this policy when you need to show always updated web contents and show nothing when there's no connection.|
| `cache-only` | The web content will be loaded from the local cache. If they're not present, the screenlet will notify about the error through the delegate as usual.| Use this policy when you need to show always local web contents without retrieving remote information under any circumstance.|
| `remote-first` | The web content will be requested to the remote portal. If it's received, it will be shown to the user and stored in the local cache for later usage. If a connection issue happens, then it will be retrieved from the local cache. If it doesn't exist there, the screenlet will notify about the error through the delegate as usual. | When you need the most updated version if connected, but is accepted to show an outdated version when there's no connection.|
| `cache-first` | The web contents will be loaded from the local cache if exist. If they don't exist, then they will be requested to the portal as usual and will notify about the error in case of issue (even on connectivity errors).| When you want to save bandwidth and loading time in case you have a local (but probably outdated) version.|
