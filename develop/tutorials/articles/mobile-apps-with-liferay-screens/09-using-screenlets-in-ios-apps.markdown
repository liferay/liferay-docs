# Using Screenlets in iOS Apps [](id=using-screenlets-in-ios-apps)

Once you've
[prepared](/develop/tutorials/-/knowledge_base/6-2/preparing-ios-projects-for-liferay-screens)
your iOS project to use Liferay Screens, you can use screenlets in your app.
There are plenty of Liferay screenlets available, and they're described in the 
screenlet
[reference documentation](/develop/reference/-/knowledge_base/6-2/screenlets-in-liferay-screens-for-ios).
This tutorial shows you how to insert and configure screenlets in iOS apps
written in Swift and Objective-C. It also explains how to localize them. You'll
be a screenlet master in no time! 

## Inserting and Configuring Screenlets in iOS Apps [](id=inserting-and-configuring-screenlets-in-ios-apps)

The first step to using screenlets in your iOS project is to add a new UIView to
your project. In Interface Builder, insert a new UIView into your Storyboard or
XIB file. Figure 1 shows this. 

![Figure 1: Add a new UIView to your project.](../../images/screens-ios-add-uiwindow.png)

Next, enter the screenlet's name as the Custom Class. For example, if you're
using the `LoginScreenlet`, then enter `LoginScreenlet` as the class. 

![Figure 2: Change the Custom Class to match the screenlet.](../../images/screens-ios-custom-class.png)

Now you need to conform the screenlet's delegate protocol in your
`ViewController` class. For example, the `LoginScreenlet`'s delegate class is
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

If you're using CocoaPods, you need to import Liferay Screens in your view 
controller:

    import LiferayScreens

Now that the screenlet's delegate protocol conforms in your `ViewController` 
class, go back to Interface Builder and connect the screenlet's delegate to your 
view controller. If the screenlet you're using has more outlets, you can assign 
them as well.

Note that currently Xcode has some
[issues](http://stackoverflow.com/questions/26180268/interface-builder-iboutlet-and-protocols-for-delegate-and-datasource-in-swift/26180481#26180481) 
connecting outlets to Swift source code. To get around this, you can change the
delegate data type or assign the outlets in your code. In your view controller,
follow these steps: 

1. Declare an outlet to hold a reference to the screenlet. You can connect it in 
   Interface Builder without any issues.
   
    ![Figure 3: Connect the outlet with the screenlet reference.](../../images/screens-ios-xcode-add-screenlet-delegate.png)
 
2. Assign the screenlet's delegate the `viewDidLoad` method. This is 
   the connection typically done in Interface Builder.

These steps are shown in the following code for `LoginScreenlet`'s view
controller. 

    class ViewController: UIViewController, LoginScreenletDelegate {

        @IBOutlet var screenlet: LoginScreenlet?

        override func viewDidLoad() {
            super.viewDidLoad()
            self.screenlet?.delegate = self
        }

        ...

![Figure 4: Connect the screenlet's delegate in Interface Builder.](../../images/screens-ios-xcode-delegate.png)

Awesome! Now you know how to use screenlets in your apps. If you need to use
screenlets from Objective-C code, follow the instructions in the next section. 

## Using Screenlets from Objective-C [](id=using-screenlets-from-objective-c)

If you want to invoke screenlet classes from Objective-C code, there is an
additional header file that you must import. You can import the header file
`LiferayScreens-Swift.h` in all your Objective-C files or configure a
precompiler header file. 

The first option involves adding the following import line all of your
Objective-C files: 

    #import "LiferayScreens-Swift.h"

Alternatively, you can configure a precompiler header file by following these
steps: 

1. Create a precompiler header file (e.g., `PrefixHeader.pch`) and add it to
   your project. 

2. Import `LiferayScreens-Swift.h` in the precompiler header file you just
   created.

3. Edit the following build settings of your target. Remember to replace
   `path/to/your/file/` with the path to your `PrefixHeader.pch` file:

    - Precompile Prefix Header: `Yes`
    - Prefix Header: `path/to/your/file/PrefixHeader.pch`

    ![Figure 5: The `PrefixHeader.pch` configuration in Xcode settings.](../../images/screens-ios-xcode-prefix.png)

You can use the precompiler header file
[`PrefixHeader.pch`](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Showcase-objc/LiferayScreens-Showcase-Objc/PrefixHeader.pch) 
as a template. 

Super! Now you know how to use screenlets from Objective-C code in your apps. 

## Localizing Screenlets [](id=localizing-screenlets)

Follow Apple's
[standard mechanism](https://developer.apple.com/library/ios/documentation/MacOSX/Conceptual/BPInternational/Introduction/Introduction.html) 
to implement localization in your screenlet. Note: even though a screenlet may
support several languages, you must also support those languages in your app. In
other words, a screenlet's support for a language is only valid if your app
supports that language. To support a language, make sure to add it as a
localization in your project's settings. 

![Figure 6: The Xcode localizations in the project's settings.](../../images/screens-ios-xcode-localizations.png)

Way to go! You now know how to use screenlets in your iOS apps. 

## Related Topics [](id=related-topics)

[Preparing iOS Projects for Liferay Screens](/develop/tutorials/-/knowledge_base/6-2/preparing-ios-projects-for-liferay-screens)

[Using Themes in iOS Screenlets](/develop/tutorials/-/knowledge_base/6-2/using-themes-in-ios-screenlets)

[Creating iOS Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-ios-screenlets)

[Using Screenlets in Android apps](/develop/tutorials/-/knowledge_base/6-2/using-screenlets-in-android-apps)
