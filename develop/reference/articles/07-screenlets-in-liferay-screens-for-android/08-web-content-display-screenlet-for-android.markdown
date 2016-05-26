# Web Content Display Screenlet for Android [](id=webcontentdisplayscreenlet-for-android)

<iframe width="560" height="315" src="https://www.youtube.com/embed/JVxfjAnCve8" frameborder="0" allowfullscreen></iframe>

## Requirements [](id=requirements)

- Android SDK 4.0 (API Level 15) or above
- Liferay Portal 6.2 (CE or EE), Liferay 7.0 CE, Liferay DXP
- Liferay Screens Compatibility Plugin
  ([CE](http://www.liferay.com/marketplace/-/mp/application/54365664) or 
  [EE](http://www.liferay.com/marketplace/-/mp/application/54369726), 
  depending on your portal edition). This app is preinstalled in Liferay 7.0 CE 
  and Liferay DXP instances. 

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
content, see the 
[Creating Web Content](/discover/portal/-/knowledge_base/7-0/creating-web-content) 
section of the Liferay User Guide. 

## Offline [](id=offline)

This Screenlet supports offline mode so it can function without a network 
connection. 

| Policy | What happens | When to use |
|--------|--------------|-------------|
| `REMOTE_ONLY` | The Screenlet loads the content from the portal. If a connection issue occurs, the Screenlet uses the listener to notify the developer about the error. If the Screenlet successfully loads the content, it stores the data in the local cache for later use. | Use this policy when you always need to show updated content, and show nothing when there's no connection. |
| `CACHE_ONLY` | The Screenlet loads the content from the local cache. If the content isn't there, the Screenlet uses the listener to notify the developer about the error. | Use this policy when you always need to show local content, without retrieving remote content under any circumstance. |
| `REMOTE_FIRST` | The Screenlet loads the content from the portal. If this succeeds, the Screenlet shows the content to the user and stores it in the local cache for later use. If a connection issue occurs, the Screenlet retrieves the content from the local cache. If the content doesn't exist there, the Screenlet uses the listener to notify the developer about the error. | Use this policy to show the most recent version of the content when connected, but show a possibly outdated version when there's no connection. |
| `CACHE_FIRST` | The Screenlet loads the content from the local cache. If the content isn't there, the Screenlet requests it from the portal and notifies the developer about any errors that occur (including connectivity errors). | Use this policy to save bandwidth and loading time in case you have local (but probably outdated) content. |

## Required Attributes [](id=required-attributes)

- `articleId`

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `layoutId` | `@layout` | The layout used to show the View. |
| `groupId` | `number` | The site (group) identifier where the asset is stored. If this value is `0`, the `groupId` specified in `LiferayServerContext` is used. |
| `articleId` | `string` | The identifier of the web content to display. You can find the identifier by clicking *Edit* on the web content in the portal. |
| `classPK` | `number` | The corresponding asset's class primary key. If the web content is an asset (from Asset List Screenlet, for example), this is the asset's identifier. This attribute is used only if `articleId` is empty. |
| `templateId` | `number` | The identifier of the template used to render the web content. This only applies to [structured web content](/discover/portal/-/knowledge_base/7-0/designing-uniform-content). |
| `structureId` | `number` | The identifier of the `DDMStructure` used to model the web content. This parameter lets the Screenlet retrieve and parse the structure. |
| `labelFields` | `string` | A comma-delimited list of `DDMStructure` fields to display in the Screenlet. |
| `autoLoad` | `boolean` | Whether the content should be retrieved from the portal as soon as the screenlet appears. Default value is `true`. |
| `javascriptEnabled` | `boolean` | Enables support for JavaScript. This is disabled by default. |

## Methods [](id=methods)

| Method | Return | Explanation |
|-----------|-----------|-------------| 
| `load()` | `void` | Starts the request to load the web content. The HTML is rendered when the response is received. |
| `getLocalized(String name)` | `String` | Returns the value, according to the device locale, of a field of the `DDMStructure` used to render the web content.

## Listener [](id=listener)

The `WebContentDisplayScreenlet` delegates some events to an object that 
implements the `WebContentDisplayListener` interface. This interface lets you 
implement the following methods:

- `onWebContentReceived(WebContentDisplayScreenlet source, WebContent webContent)`: 
  Called when the web content's HTML or `DDMStructure` is received. The HTML is 
  available by calling the `getHtml` method. To make some adaptations, the 
  listener may return a modified version of the HTML. The original HTML is 
  rendered if the listener returns `null`. 

- `onWebContentFailure(WebContentDisplayScreenlet source, Exception e)`: Called 
  when an error occurs in the process. 
