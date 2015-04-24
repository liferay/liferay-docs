# Using Screenlets in Your iOS Project [](id=using-screenlets-in-your-ios-project)

You can start using screenlets once your iOS project is configured to use 
Liferay Screens. Using screenlets is very straightforward. This tutorial shows 
you how to insert and configure screenlets in your iOS project. Steps for using 
screenlets from Objective-C code are also presented. You're also shown how to 
localize screenlets so your app can support multiple languages. You'll be a 
screenlet master in no time! 

## Inserting and Configuring Screenlets in Your iOS Project [](id=inserting-and-configuring-screenlets-in-your-ios-project)

To start using screenlets in your iOS project, first use Interface Builder to 
insert a new UIView in your Storyboard or XIB file. This is shown here: 

![Figure 1: Add a new UI View to your project.](../../images/screens-ios-add-uiwindow.png)

Next, change the Custom Class to the screenlet's class name. For example, if 
you're using `LoginScreenlet`, then enter `LoginScreenlet` as the Custom Class 
name. This is shown in the following screenshot:

![Figure 2: Change the Custom Class name to match the screenlet.](../../images/screens-ios-custom-class.png)

Now you need to conform the screenlet's delegate protocol in your 
`ViewController` class. For example, for the `LoginScreenlet` this is 
`LoginScreenletDelegate`. This is shown in the code that follows. Note that you 
need to implement the functionality of `onLoginResponse` and `onLoginError`. 
This is indicated by the comments in the code here:

    class ViewController: UIViewController, LoginScreenletDelegate {

        ...
        
        func onLoginResponse(attributes: [String:AnyObject]) {
            // handle succeeded login using passed user attributes
        }
    
        func onLoginError(error: NSError) {
            // handle failed login using passed error
        }
        
        ...

Now that the screenlet's delegate protocol is conformed in your `ViewController` 
class, go back to Interface Builder and connect the screenlet's delegate to your 
view controller. If the screenlet you're using has more outlets, you can assign 
them as well. Note that currently there are [some issues in Xcode](http://stackoverflow.com/questions/26180268/interface-builder-iboutlet-and-protocols-for-delegate-and-datasource-in-swift/26180481#26180481) 
with connecting an outlet to Swift source code. To get around this, you can 
change the delegate data type or assign the outlets in your code. For example, 
the following steps present a simple workaround for this limitation. In your 
view controller:

1. Declare an outlet to hold a reference to the screenlet. You can connect it in 
   Interface Builder without any issues.
   
2. Assign the screenlet's delegate by code in the `viewDidLoad` method. This is 
   the connection typically done in Interface Builder.

These steps are shown in the following code for the view controller of 
`LoginScreenlet`.

    class ViewController: UIViewController, LoginScreenletDelegate {
    
        @IBOutlet var screenlet: LoginScreenlet?

        override func viewDidLoad() {
            super.viewDidLoad()
            self.screenlet?.delegate = self
        }
        
        ...

![Figure 3: Connect the screenlet's delegate in Interface Builder.](../../images/screens-ios-xcode-delegate.png)

Awesome! Now you know how to use screenlets in your iOS projects. However, if 
you want to use screenlets from Objective-C code, then there are a few more 
things you need to take care of. These are presented in the next section. You 
can skip this if you don't need to use screenlets from Objective-C. 

## Using Screenlets from Objective-C [](id=using-screenlets-from-objective-c)

If you want to invoke screenlet classes from your Objective-C code, then there 
is an additional header file that you need to import in every Objective-C file: 

    #import "LiferayScreens-Swift.h"

To avoid adding the same imports over and over again, you can add a precompiler 
header file by using the following steps: 

1. Create the file `PrefixHeader.pch` and add it to your project.

2. Add the above import to that file.

3. Edit the following build settings of your target, using the indicated 
   settings. Remember to replace `path/to/your/file/` with the path to your 
   `PrefixHeader.pch` file:

    - Precompile Prefix Header: `Yes`
    - Prefix Header: `path/to/your/file/PrefixHeader.pch`

    ![Figure 4: The `PrefixHeader.pch` configuration in Xcode settings.](../../images/screens-ios-xcode-prefix.png)

You can use this [`PrefixHeader.pch`](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Showcase-objc/LiferayScreens-Showcase-Objc/PrefixHeader.pch) 
as a template. 

Super! Now you know how to call screenlets from the Objective-C code in your iOS 
project. 

## Localizing Screenlets [](id=localizing-screenlets)

You can localize screenlets to show their information in different languages. 
This uses [Apple's standard mechanism](https://developer.apple.com/library/ios/documentation/MacOSX/Conceptual/BPInternational/Introduction/Introduction.html) 
for localization. It's important to note that even though the screenlets support 
several languages, you must also support those languages in your app. In other 
words, if you don't support a given language in your app, the screenlet doesn't 
support that language either. To support a language, make sure that your 
project's settings list the language, as in the following screenshot:

![Figure 5: The Xcode localizations in the project's settings.](../../images/screens-ios-xcode-localizations.png)

## Related Topics [](id=related-topics)

[Creating Screenlets in Liferay Screens for iOS](/tutorials/-/knowledge_base/6-2/creating-screenlets-in-liferay-screens-for-ios)

[Creating Themes in Liferay Screens for iOS](/tutorials/-/knowledge_base/6-2/creating-themes-in-liferay-screens-for-ios)

[Preparing Your iOS Project for Liferay Screens](/tutorials/-/knowledge_base/6-2/preparing-your-ios-project-for-liferay-screens)

[Architecture of Liferay Screens for iOS](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-ios)
