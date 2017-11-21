# Rendering Web Pages in Your iOS App [](id=rendering-web-pages-in-your-ios-app)

The 
[Rendering Web Content](/develop/tutorials/-/knowledge_base/7-0/rendering-web-content-in-your-ios-app) 
tutorial shows you how to display 
[web content](/discover/portal/-/knowledge_base/7-0/creating-web-content) 
from a @product@ site in your iOS app. But what if you don't want to display 
only content? What if you want to display an entire page? And what if you want 
to customize that page? No problem! 
[Web Screenlet](/develop/reference/-/knowledge_base/7-0/web-screenlet-for-ios) 
lets you display any web page. What's more, Web Screenlet lets you customize the 
web page through injection of local and remote JavaScript and CSS files. When 
combined with @product@'s server-side customization features (like 
[Application Display Templates](/discover/portal/-/knowledge_base/7-0/styling-apps-with-application-display-templates), 
for example), Web Screenlet gives you almost limitless possibilities for 
displaying web pages in your iOS apps. 

In this tutorial, you'll learn how to use Web Screenlet to display web pages in 
your iOS app. 

## Inserting Web Screenlet in Your App [](id=inserting-web-screenlet-in-your-app)

The process for inserting Web Screenlet in your app is the same as 
[that of any Screenlet](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-ios-apps#inserting-and-configuring-screenlets-in-ios-apps): 

1.  In Interface Builder, insert a new view (`UIView`) in a new view controller. 
    This new view should be nested under the view controller's existing view. 

2.  With the new view selected, open the Identity inspector and set the view's 
    Custom Class as `WebScreenlet`. 

3.  Set any constraints that you want for the Screenlet in the scene. 

The exact steps for configuring Web Screenlet, however, are unique to Web 
Screenlet. First, you'll conform your view controller to Web Screenlet's 
delegate protocol. 

## Conforming to Web Screenlet's Delegate Protocol [](id=conforming-to-web-screenlets-delegate-protocol)

As with any Screenlet, you must conform the view controller's class to the 
Screenlet's delegate protocol. For Web Screenlet, this means you must conform 
the class of the view controller that contains it to the `WebScreenletDelegate` 
protocol. Follow these steps to do so: 

1.  Import `LiferayScreens` and set your view controller to adopt the 
    `WebScreenletDelegate` protocol: 

        import UIKit
        import LiferayScreens

        class ViewController: UIViewController, WebScreenletDelegate {...

2.  Implement the `WebScreenletDelegate` method `onWebLoad(_:url:)`. Since this 
    method is called when the Screenlet loads the page, how you implement it 
    depends on what (if anything) you want to happen at that time. Its arguments 
    are the `WebScreenlet` instance and the page URL. This example simply prints 
    a message to the console indicating that the page was loaded: 

        func onWebLoad(_ screenlet: WebScreenlet, url: String) {
            // Called when the page is loaded
            print("\(url) was just loaded")
        }

3.  Implement the `WebScreenletDelegate` method `screenlet(_:onError:)`. This 
    method is called when an error occurs loading the page, and therefore 
    includes the `NSError` object. This lets you log or print the error, as well 
    as perform any other processing you want to happen when an error occurs. For 
    example, this implementation prints a message containing the error's 
    description: 

        func screenlet(_ screenlet: WebScreenlet, onError error: NSError) {
            print("Failed to load the page: \(error.localizedDescription)")
        }

4.  Implement the `WebScreenletDelegate` method 
    `screenlet(_:onScriptMessageNamespace:onScriptMessage:)`. This method is 
    called when 
    [the `WKWebView`](https://developer.apple.com/documentation/webkit/wkwebview) 
    in the Screenlet sends a message. This method's arguments include the 
    message's namespace and the message. How you implement this method depends 
    on what you want to happen when the message is sent. For example, you could 
    perform a segue and include the message as the segue's `sender`: 

        func screenlet(_ screenlet: WebScreenlet,
            onScriptMessageNamespace namespace: String,
            onScriptMessage message: String) {

            performSegue(withIdentifier: "detail", sender: message)
        }

5.  Get a reference to the Web Screenlet on your storyboard by using Interface 
    Builder to create an outlet to it in your view controller. It's a best 
    practice to name a Screenlet outlet after the Screenlet it references, or 
    simply `screenlet`. Here's an example Web Screenlet outlet: 

        @IBOutlet weak var webScreenlet: WebScreenlet!

6.  In the view controller's `viewDidLoad()` method, use the Web Screenlet 
    reference you just created to set the view controller as the Screenlet's 
    delegate. To do this, add the following line of code just below the 
    `super.viewDidLoad()` call: 

        self.webScreenlet?.delegate = self

Next, you'll use the same Web Screenlet reference to set the Screenlet's 
parameters. 

## Setting Web Screenlet's Parameters [](id=setting-web-screenlets-parameters)

Web Screenlet has `WebScreenletConfiguration` and 
`WebScreenletConfigurationBuilder` objects that you can use together to supply 
the parameters that the Screenlet needs to work. These parameters include the 
URL of the page to load, and the location of any JavaScript or CSS files that 
you want to use to customize the page. You'll set most of these parameters via 
`WebScreenletConfigurationBuilder`'s methods. 

+$$$

**Note:** For a full list of `WebScreenletConfigurationBuilder`'s methods, and a 
description of each, see the table in 
[the Configuration section](/develop/reference/-/knowledge_base/7-0/web-screenlet-for-ios#configuration) 
of Web Screenlet's reference doc. 

$$$

To use `WebScreenletConfiguration` and `WebScreenletConfigurationBuilder` to set 
the parameters you need for Web Screenlet, follow these steps in the 
`viewDidLoad()` method of a view controller that uses Web Screenlet: 

1.  Use the constructor `WebScreenletConfigurationBuilder(<url>)`, where `<url>` 
    is the web page's URL string, to create a `WebScreenletConfigurationBuilder` 
    object. If the page is a @product@ page that requires authentication, then 
    the user must be logged in via 
    [Login Screenlet](/develop/reference/-/knowledge_base/7-0/loginscreenlet-for-ios) 
    or a `SessionContext` method, and you must provide a relative URL to the 
    `WebScreenletConfigurationBuilder` constructor. For example, if the full 
    URL to such a page is `http://your.liferay.instance/web/guest/blog`, then 
    the URL you must supply to the constructor is `/web/guest/blog`. For any 
    other page that doesn't require @product@ authentication, you must supply 
    the full URL to the constructor. 

2.  Call the `WebScreenletConfigurationBuilder` methods to set the parameters 
    that you need. 

    +$$$

    **Note:** If the URL you supplied to the `WebScreenletConfigurationBuilder` 
    constructor is to a page that doesn't require @product@ authentication, then 
    you must call the `WebScreenletConfigurationBuilder` method 
    `set(webType: .other)`. This is because the default `WebType` setting is 
    `.liferayAuthenticated`, which is required to load @product@ pages that need 
    authentication to access. If you need to set `.liferayAuthenticated` 
    manually, call `set(webType: .liferayAuthenticated)`. 

    $$$

3.  Call the `WebScreenletConfigurationBuilder` instance's `load()` method, 
    which returns a `WebScreenletConfiguration` object. 

4.  Set the `WebScreenletConfiguration` object to the Web Screenlet instance's 
    `configuration` property. 

5.  Call the Web Screenlet instance's `load()` method. 

Here's an example snippet of these steps in the `viewDidLoad()` method of a view 
controller in which the Web Screenlet instance is `webScreenlet`, and the 
`WebScreenletConfiguration` object is `webScreenletConfiguration`: 

    override func viewDidLoad() {
        super.viewDidLoad()

        self.webScreenlet?.delegate = self

        let webScreenletConfiguration = 
            WebScreenletConfigurationBuilder(url: "/web/westeros-hybrid/companynews")
                .addCss(localFile: "blogs")
                .addJs(localFile: "blogs")
                .load()
        webScreenlet.configuration = webScreenletConfiguration
        webScreenlet.load()
    }

The relative URL `/web/westeros-hybrid/companynews` supplied to the 
`WebScreenletConfigurationBuilder` constructor, and the lack of a 
`set(webType: .other)` call, indicates that this Web Screenlet instance loads a 
@product@ page that requires authentication. The `addCss` and `addJs` calls add 
local CSS and JavaScript files, respectively. Both files are named `blogs`. 

Great! Now you know how to use Web Screenlet in your iOS apps. 

## Related Topics [](id=related-topics)

[Web Screenlet for iOS](/develop/reference/-/knowledge_base/7-0/web-screenlet-for-ios)

[Using Screenlets in iOS Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-ios-apps)

[Rendering Web Content in Your iOS App](/develop/tutorials/-/knowledge_base/7-0/rendering-web-content-in-your-ios-app)
