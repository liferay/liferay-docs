# WebContentDisplayScreenlet for iOS

## Requirements

- XCode 6.0 or above
- iOS 8 SDK
- Liferay Portal 6.2 CE or EE
- Mobile Widgets plugin installed

## Compatibility

- iOS 7 and above

## Features

The `WebContentDisplayScreenlet` shows web content elements in your app, 
rendering the inner HTML of the web content. The screenlet also supports i18n, 
rendering contents differently depending on the device's current locale. 

## Module

- None

## Themes

The Default theme uses a standard `UIWebView` to render the HTML. Other themes 
may use a different component, such as iOS 8's. 

![The `WebContentDisplayScreenlet` using the Default theme](../../images/screens-ios-webcontent.png)

## Portal Configuration

For the `WebContentDisplayScreenlet` to function properly, there should be web 
content in the Liferay instance your app connects to. For more details on web 
content, please refer to the [Web Content Management](/portal/-/knowledge_base/6-2/web-content-management) 
section of the Liferay User Guide. 

## Attributes

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `groupId` | `number` | The site (group) identifier where the asset is stored. If this value is 0, the `groupId` specified in `LiferayServerContext` is used. |
|  `articleId` | `string` | The identifier of the web content to display. You can find the identifier by clicking *Edit* on the web content in the portal. |

## Methods

| Method | Return | Explanation |
|-----------|-----------|-------------| 
|  `loadWebContent()` | `boolean` | Starts the request to load the web content. The HTML is rendered when the response is received. Returns `true` if the request is sent. |

## Delegate

The `WebContentDisplayScreenlet` delegates some events to an object that 
conforms to the `WebContentDisplayScreenletDelegate` protocol. This protocol 
lets you implement the following methods:

- `onWebContentDisplayResponse(html)`: Called when the web content's HTML is 
  received. 
- `onWebContentDisplayError(error)`: Called when an error occurs in the process. 
  The `NSError` object describes the error. 
