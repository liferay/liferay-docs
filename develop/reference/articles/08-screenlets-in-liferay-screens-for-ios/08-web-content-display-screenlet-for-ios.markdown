# Web Content Display Screenlet for iOS [](id=webcontentdisplayscreenlet-for-ios)

<iframe width="560" height="315" src="https://www.youtube.com/embed/ODfb_4igvCs" frameborder="0" allowfullscreen></iframe>

## Requirements [](id=requirements)

- Xcode 7.3
- iOS 9 SDK
- Liferay Portal 6.2 (CE or EE), Liferay 7.0 CE, Liferay DXP 
- Liferay Screens Compatibility Plugin
  ([CE](http://www.liferay.com/marketplace/-/mp/application/54365664) or 
  [EE](http://www.liferay.com/marketplace/-/mp/application/54369726), 
  depending on your portal edition). This app is preinstalled in Liferay 7.0 CE 
  and Liferay DXP instances. 

## Compatibility [](id=compatibility)

- iOS 7 and above

## Features [](id=features)

The Web Content Display Screenlet shows web content elements in your app, 
rendering the inner HTML of the web content. The Screenlet also supports i18n, 
rendering contents differently depending on the device's current locale. 

## Module [](id=module)

- WebContent

## Themes [](id=themes)

- Default

The Default Theme uses a standard `UIWebView` to render the HTML. Other Themes 
may use a different component, such as iOS 8's. 

![The Web Content Display Screenlet using the Default (`default`) Theme](../../images/screens-ios-webcontent.png)

## Portal Configuration [](id=portal-configuration)

For the Web Content Display Screenlet to function properly, there should be web 
content in the Liferay instance your app connects to. For more details on web 
content, please refer to the 
[Creating Web Content](/discover/portal/-/knowledge_base/7-0/creating-web-content) 
section of the Liferay User Guide. 

## Offline [](id=offline)

This Screenlet supports offline mode so it can function without a network 
connection. For more information on how offline mode works, see the 
[tutorial on its architecture](/develop/tutorials/-/knowledge_base/7-0/architecture-of-offline-mode-in-liferay-screens). 
Here are the offline mode policies that you can use with this Screenlet: 

| Policy | What happens | When to use |
|--------|--------------|-------------|
| `remote-only` | The Screenlet loads the content from the portal. If a connection issue occurs, the Screenlet uses the delegate to notify the developer about the error. If the Screenlet successfully loads the content, it stores the data in the local cache for later use. | Use this policy when you always need to show updated content, and show nothing when there's no connection. |
| `cache-only` | The Screenlet loads the content from the local cache. If the content isn't there, the Screenlet uses the delegate to notify the developer about the error. | Use this policy when you always need to show local content, without retrieving remote content under any circumstance. |
| `remote-first` | The Screenlet loads the content from the portal. If this succeeds, the Screenlet shows the content to the user and stores it in the local cache for later use. If a connection issue occurs, the Screenlet retrieves the content from the local cache. If the content doesn't exist there, the Screenlet uses the delegate to notify the developer about the error. | Use this policy to show the most recent version of the content when connected, but show a possibly outdated version when there's no connection. |
| `cache-first` | The Screenlet loads the content from the local cache. If the content isn't there, the Screenlet requests it from the portal and notifies the developer about any errors that occur (including connectivity errors). | Use this policy to save bandwidth and loading time in case you have local (but probably outdated) content. |

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `groupId` | `number` | The site (group) identifier where the asset is stored. If this value is `0`, the `groupId` specified in `LiferayServerContext` is used. |
| `articleId` | `string` | The identifier of the web content to display. You can find the identifier by clicking *Edit* on the web content in the portal. |
| `templateId` | `number` | The identifier of the template used to render the web content. This is applicable only with [structured web content](/discover/portal/-/knowledge_base/7-0/designing-uniform-content). |
| `autoLoad` | `boolean` | Whether the content should be retrieved from the portal as soon as the Screenlet appears. The default value is `true`. |

## Methods [](id=methods)

| Method | Return | Explanation |
|-----------|-----------|-------------| 
|  `loadWebContent()` | `boolean` | Starts the request to load the web content. The HTML is rendered when the response is received. Returns `true` if the request is sent. |

## Delegate [](id=delegate)

The Web Content Display Screenlet delegates some events to an object that 
conforms to the `WebContentDisplayScreenletDelegate` protocol. This protocol 
lets you implement the following methods:

- `- screenlet:onWebContentResponse:`: Called when the web content's HTML is 
  received. 

- `- screenlet:onWebContentDisplayError:`: Called when an error occurs in the 
  process. The `NSError` object describes the error. 
