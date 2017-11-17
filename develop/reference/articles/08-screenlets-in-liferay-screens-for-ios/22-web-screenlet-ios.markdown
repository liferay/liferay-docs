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

Web Screenlet has a `WebScreenletConfigurationBuilder` object that lets you 
supply the parameters that the Screenlet needs to work. In the view controller 
that uses the Screenlet, you must create this object and set it to the Screenlet 
instance before calling the Screenlet's `load` method. You create the 
configuration object using the constructor 
`WebScreenletConfigurationBuilder(<url>)`, where `<url>` is the web page's URL 
string. 

The resulting `WebScreenletConfigurationBuilder` object has the following 
methods, which let you supply the described configuration parameters: 

| Method | Returns | Explanation |
|-----------|-----------|-------------| 
| `addJs(localFile: String)` | `WebScreenletConfigurationBuilder` | Adds a local JavaScript file with the supplied filename. |
| `addCss(localFile: String)` | `WebScreenletConfigurationBuilder` | Adds a local CSS file with the supplied filename. |
| `addJs(url: String)` | `WebScreenletConfigurationBuilder` | Adds a CSS file from the supplied URL. |
| `addCss(url: String)` | `WebScreenletConfigurationBuilder` | Adds a JavaScript file from the supplied URL. |
| `set(webType: WebType)` | `WebScreenletConfigurationBuilder` | Sets the [`WebType`](/develop/reference/-/knowledge_base/7-0/web-screenlet-for-ios#webtype). |
| `enableCordova()` | `WebScreenletConfigurationBuilder` | Enables Cordova inside the Web Screenlet. |
| `load()` | `WebScreenletConfiguration` | Returns the `WebScreenletConfiguration` object that you can set to the Screenlet instance. |

+$$$

**Note:** If you want to add comments into the scripts use the `/**/` notation. 

$$$

After setting the configuration parameters, you must set the 
`WebScreenletConfiguration` object to the Screenlet's `configuration` property, 
then call the Screenlet's `load()` method. Here's an example snippet from a view 
controller in which the Web Screenlet instance is `webScreenlet`, and the 
`WebScreenletConfiguration` object is `webScreenletConfiguration`: 

    ...
    @IBOutlet weak var webScreenlet: WebScreenlet!
    ...
    let webScreenletConfiguration = 
            WebScreenletConfigurationBuilder(url: "/web/westeros-hybrid/companynews")
                .addCss(localFile: "blogs")
                .addJs(localFile: "blogs")
                .load()
    webScreenlet.configuration = webScreenletConfiguration
    webScreenlet.load()
    webScreenlet.delegate = self
    ...

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
| `autoLoad` | `boolean` | Whether the page automatically loads when the Screenlet appears in the app's UI. The default value is `true`. |
| `loggingEnabled` | `boolean` | Whether logging is enabled. |
| `isScrollEnabled` | `boolean` | Whether to enable scrolling on the page inside the Screenlet. |

## Delegate [](id=delegate)

Web Screenlet delegates some events to an object that conforms to the 
`WebScreenletDelegate` protocol. This protocol lets you implement the following 
methods:

-   `- onWebLoad:url:`: Called when the Screenlet loads the page. 

-   `- screenlet:onScriptMessageNamespace:onScriptMessage:`: Called when the 
    `WKWebView` sends a message. 

-   `- screenlet:onError:`: Called when an error occurs in the process. The 
    `NSError` object describes the error. 
