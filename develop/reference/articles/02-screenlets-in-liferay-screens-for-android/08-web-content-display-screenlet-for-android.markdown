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
| `groupId` | `number` | The ID of the site (group) where the asset is stored. If this value is `0`, the `groupId` specified in `LiferayServerContext` is used. |
| `articleId` | `string` | The ID of the web content to display. You can find this ID by clicking *Edit* on the web content in the portal. |
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

## Offline [](id=offline)

This screenlet sopport offline mode in order to work under scenarios with bad connectivity.

| Policy | What happens | When to use |
|--------|--------------|-------------|
| `REMOTE_ONLY` | The web content will be loaded from the portal. If a connection issue happens, the screenlet will notify about the error through the listener as usual. If the web content can be successfully loaded, the received asserts are stored in the local cache for later usage.| Use this policy when you need to show always updated web contents and show nothing when there's no connection.|
| `CACHE_ONLY` | The web content will be loaded from the local cache. If they're not present, the screenlet will notify about the error through the listener as usual.| Use this policy when you need to show always local web contents without retrieving remote information under any circumstance.|
| `REMOTE_FIRST` | The web content will be requested to the remote portal. If it's received, it will be shown to the user and stored in the local cache for later usage. If a connection issue happens, then it will be retrieved from the local cache. If it doesn't exist there, the screenlet will notify about the error through the listener as usual. | When you need the most updated version if connected, but is accepted to show an outdated version when there's no connection.|
| `CACHE_FIRST` | The web contents will be loaded from the local cache if exist. If they don't exist, then they will be requested to the portal as usual and will notify about the error in case of issue (even on connectivity errors).| When you want to save bandwidth and loading time in case you have a local (but probably outdated) version.|
