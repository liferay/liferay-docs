# Web Screenlet for iOS [](id=web-screenlet-for-ios)

## Requirements [](id=requirements)

-   Xcode 9.0
-   iOS 11 SDK
-   Liferay Portal CE 7.0, or Liferay DXP 
-   Liferay Screens Compatibility Plugin (for 
    [Liferay Portal CE 7.0](http://www.liferay.com/marketplace/-/mp/application/54365664) 
    or 
    [Liferay DXP](http://www.liferay.com/marketplace/-/mp/application/54369726)). 

## Compatibility [](id=compatibility)

- iOS 9 and above

## Xamarin Requirements [](id=xamarin-requirements)

- Visual Studio 7.2
- Mono .NET framework 5.4.1.6

## Features [](id=features)

Web Screenlet lets you display any web page. It also lets you customize the web 
page through injection of local and remote JavaScript and CSS files. If you're 
using @product@ as backend, you can use 
[Application Display Templates](/discover/portal/-/knowledge_base/7-0/styling-apps-with-application-display-templates) 
in your page to customize its content from the server side. 

## Module [](id=module)

- None

## Themes [](id=themes)

- Default

The Default Theme uses an iOS `WKWebView` for displaying the web page. 

![Figure 1: Web Screenlet using the Default Theme.](../../images/screens-ios-webscreenlet.png)

## Configuration [](id=configuration)

To learn how to use Web Screenlet, follow the steps in the tutorial 
[Rendering Web Pages in Your iOS App](/develop/tutorials/-/knowledge_base/7-0/rendering-web-pages-in-your-ios-app). 
That tutorial gives detailed instructions for using the configuration items 
described here. 

Web Screenlet has `WebScreenletConfiguration` and 
`WebScreenletConfigurationBuilder` objects that you can use together to supply 
the parameters that the Screenlet needs to work. 
`WebScreenletConfigurationBuilder` has the following methods, which let you 
supply the described configuration parameters: 

| Method | Returns | Explanation |
|-----------|-----------|-------------| 
| `addJs(localFile: String)` | `WebScreenletConfigurationBuilder` | Adds a local JavaScript file with the supplied filename. |
| `addCss(localFile: String)` | `WebScreenletConfigurationBuilder` | Adds a local CSS file with the supplied filename. |
| `addJs(url: String)` | `WebScreenletConfigurationBuilder` | Adds a JavaScript file from the supplied URL. |
| `addCss(url: String)` | `WebScreenletConfigurationBuilder` | Adds a CSS file from the supplied URL. |
| `set(webType: WebType)` | `WebScreenletConfigurationBuilder` | Sets the [`WebType`](/develop/reference/-/knowledge_base/7-0/web-screenlet-for-ios#webtype). |
| `enableCordova()` | `WebScreenletConfigurationBuilder` | Enables Cordova inside the Web Screenlet. |
| `load()` | `WebScreenletConfiguration` | Returns the `WebScreenletConfiguration` object that you can set to the Screenlet instance. |

+$$$

**Note:** If you want to add comments in the scripts, use the `/**/` notation. 

$$$

### WebType [](id=webtype)

-   **WebType.liferayAuthenticated** (default): Displays a @product@ page that 
    requires authentication. The user must therefore be logged in with Screens 
    via Login Screenlet or a `SessionContext` method. For this `WebType`, the 
    URL you must pass to the `WebScreenletConfigurationBuilder` constructor is a 
    relative URL. For example, if the full URL is 
    `http://screens.liferay.org.es/web/guest/blog`, then the URL you must supply 
    to the constructor is `/web/guest/blog`. 

-   **WebType.other**: Displays any other page. For this `WebType`, the URL you 
    must pass to the `WebScreenletConfigurationBuilder` constructor is a full 
    URL. For example, if the full URL is 
    `http://screens.liferay.org.es/web/guest/blog`, then you must supply that 
    URL to the constructor. 

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------|
| `autoLoad` | `boolean` | Whether to load the page automatically when the Screenlet appears in the app's UI. The default value is `true`. |
| `loggingEnabled` | `boolean` | Whether logging is enabled. |
| `isScrollEnabled` | `boolean` | Whether to enable scrolling on the page inside the Screenlet. |

## Delegate [](id=delegate)

Web Screenlet delegates some events to an object that conforms to the 
`WebScreenletDelegate` protocol. This protocol lets you implement the following 
methods:

-   `onWebLoad(_:url:)`: Called when the Screenlet loads the page. 

        func onWebLoad(_ screenlet: WebScreenlet, url: String) {
            ...
        }

-   `screenlet(_:onScriptMessageNamespace:onScriptMessage:)`: Called when the 
    `WKWebView` sends a message. 

        func screenlet(_ screenlet: WebScreenlet,
                   onScriptMessageNamespace namespace: String,
                   onScriptMessage message: String) {
            ...
        }

-   `screenlet(_:onError:)`: Called when an error occurs in the process. The 
    `NSError` object describes the error. 

        func screenlet(_ screenlet: WebScreenlet, onError error: NSError) {
            ...
        }
