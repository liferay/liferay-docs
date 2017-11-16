# Web Screenlet for iOS [](id=web-screenlet-for-ios)

## Requirements [](id=requirements)

-   Xcode 9
-   iOS 9 SDK
-   Liferay Portal CE 7.0, or Liferay DXP 
-   Liferay Screens Compatibility Plugin (for 
    [Liferay Portal CE 7.0](http://www.liferay.com/marketplace/-/mp/application/54365664) 
    or 
    [Liferay DXP](http://www.liferay.com/marketplace/-/mp/application/54369726)). 

## Compatibility [](id=compatibility)

- iOS 9 and above

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

Web Screenlet has a configuration object that lets you supply the parameters 
that the Screenlet needs to work. In the view controller that uses the 
Screenlet, you must create this object and set it to the Screenlet instance 
before calling the Screenlet's `load` method. You create the configuration 
object using the constructor `WebScreenletConfigurationBuilder("<url>")`, where 
`<url>` is the web page's URL. 

The resulting `WebScreenletConfigurationBuilder` object has these methods, which 
allow you to supply the corresponding configuration parameters: 

| Method | Returns | Explanation |
|-----------|-----------|-------------| 
| `addJs(localFile: String)` | `WebScreenletConfigurationBuilder` | Adds a local JavaScript file with the supplied filename. |
| `addCss(localFile: String)` | `WebScreenletConfigurationBuilder` | Adds a local CSS file with the supplied filename. |
| `addJs(url: String)` | `WebScreenletConfigurationBuilder ` | Adds a CSS file from the supplied URL. |
| `addCss(url: String)` | `WebScreenletConfigurationBuilder ` | Adds a JavaScript file from the supplied URL. |
| `set(webType: WebType)` | `WebScreenletConfigurationBuilder ` | Choose the [WebType](#webtype) (see options below) |
| `enableCordova()` | `WebScreenletConfigurationBuilder ` | Enable Cordova inside the Web Screenlet. |
| `load()` | `WebScreenletConfiguration` | Return a configuration object that you can set to the screenlet. |

+$$$

Note: If you want to add comments into the scripts use the `/**/` notation. 

$$$

After configure all of this you have to set the configuration like this 
`screenlet?.configuration = configuration` and then call the method `load()` on 
the screenlet. 

### WebType [](id=webtype)

**WebType.liferayAuthenticated** (default): Used if you want to display a 
Liferay page that requires authentication, you have to be logged with Screens 
(using a `LoginScreenlet` or with `SessionContext` method manually). For this 
webType the <_url_> you have to pass in the Builder constructor is a relative 
url. For example:

- Server: http://screens.liferay.org.es/web/guest/blog
- <_url_> = /web/guest/blog

**WebType.other**: Used if you want to display any other page. For this webType 
the <_url_> you have to pass in the Builder constructor is full url. For 
example:

- Server: http://screens.liferay.org.es/web/guest/blog 
- <_url_> = http://screens.liferay.org.es/web/guest/blog

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------|
| `autoLoad` | `boolean` | Whether the list automatically loads when the Screenlet appears in the app's UI. The default value is `true`. |
| `loggingEnabled` | `boolean` | If the logging is enabled or not. |
| `isScrollEnabled` | `boolean` | If the scroll inside Web Screenlet is enabled or not. |

## Delegate [](id=delegate)

Web Screenlet delegates some events to an object that conforms to the 
`WebScreenletDelegate` protocol. This protocol lets you implement the following 
methods:

-   `- onWebLoad:url:`: Called when the page is loaded.

-   `- screenlet:onScriptMessageNamespace:onScriptMessage:`: Called when we want 
    to notify a message from the WKWebView used in the view.

-   `- screenlet: onError:`: Called when an error occurs in the process. The 
    `NSError` object describes the error.
