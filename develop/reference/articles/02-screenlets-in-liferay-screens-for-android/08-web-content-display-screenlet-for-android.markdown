# WebContentDisplayScreenlet for Android [](id=webcontentdisplayscreenlet-for-android)

## Requirements [](id=requirements)

- Android SDK 4.0 (API Level 14) and above
- Liferay Portal 6.2 CE or EE
- Mobile Widgets plugin

## Compatibility [](id=compatibility)

- Android SDK 4.0 (API Level 14) and above

## Features [](id=features)

The `WebContentDisplayScreenlet` shows web content elements in your app, 
rendering the web content's inner HTML. The screenlet also supports i18n, 
rendering contents differently depending on the device's locale.

## Module [](id=module)

- None

## Views [](id=views)

The Default view uses a standard `WebView` to render the HTML.

## Portal Configuration [](id=portal-configuration)

For the `WebContentDisplayScreenlet` to function properly, there should be web 
content in the Liferay instance your app connects to. For more details on web 
content, see the [Web Content Management](/portal/-/knowledge_base/6-2/web-content-management) 
section of the Liferay User Guide. 

## Required Attributes

- `layoutId`
- `articleId`

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `layoutId` | `@layout` | The layout used to show the view. |
| `groupId` | `number` | The ID of the site (group) where the asset is stored. If this value is `0`, the `groupId` specified in `LiferayServerContext` is used. |
| `articleId` | `string` | The ID of the web content to display. You can find this ID by clicking *Edit* on the web content in the portal. |

## Methods [](id=methods)

| Method | Return | Explanation |
|-----------|-----------|-------------| 
| `load()` | `void` | Starts the request to load the web content. The HTML is rendered when the response is received. |

## Listener [](id=listener)

The `WebContentDisplayScreenlet` delegates some events to an object that 
implements the `WebContentDisplayListener` interface. This interface lets you 
implement the following methods:

| Method | Explanation |
|-----------|-------------| 
|  <pre>onWebContentReceived(<br/>        WebContentDisplayScreenlet source, <br/>        String html)</pre> | Called when the web content's HTML is received. To make some adaptations, the listener may return a modified version of the HTML. The original HTML is rendered if the listener returns `null`. |
|  <pre>onWebContentFailure(<br/>        WebContentDisplayScreenlet source,<br/>        Exception e)</pre> | Called when an error occurs in the process. |
