# Rendering Web Pages in Your iOS App

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

## Inserting Web Screenlet in Your App

The process for inserting Web Screenlet in your app is the same as that of 
[any Screenlet](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-ios-apps#inserting-and-configuring-screenlets-in-ios-apps): 

1.  In Interface Builder, insert a new view (`UIView`) in a new view controller. 
    This new view should be nested under the view controller's existing view. 

2.  With the new view selected, open the Identity inspector and set the view's 
    Custom Class as `WebScreenlet`. 

3.  Set any constraints that you want for the Screenlet in the scene. 

The exact steps for configuring Web Screenlet, however, are unique to Web 
Screenlet. You'll do this next. 

## Configuring Web Screenlet

As with any Screenlet, you must conform the view controller's class to the 
Screenlet's delegate protocol. For Web Screenlet, this means you must conform 
the class of the view controller that contains it to the `WebScreenletDelegate` 
protocol. 

<!-- Explain delegate methods -->

    class ViewController: UIViewController, WebScreenletDelegate {

        ...

        func onWebLoad(_ screenlet: WebScreenlet, url: String) {
            // Called when the page is loaded
        }

        func screenlet(_ screenlet: WebScreenlet, onError error: NSError) {
            //Called when an error occurs in the process. The `NSError` object describes the error
        }

        func screenlet(_ screenlet: WebScreenlet, 
            onScriptMessageNamespace namespace: String, onScriptMessage message: String) {
                //Called when we want to notify a message from the WKWebView used in the view
        }

        ...
    }

<!-- Get a Web Screenlet reference, then do the configuration from the ref -->
