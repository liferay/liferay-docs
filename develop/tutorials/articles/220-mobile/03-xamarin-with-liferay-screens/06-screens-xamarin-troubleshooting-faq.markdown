# Liferay Screens for Xamarin Troubleshooting and FAQs [](id=liferay-screens-for-xamarin-troubleshooting-and-faqs)

Even though Liferay developed Liferay Screens for Xamarin with great care, you 
may still run into some common issues. This tutorial lists tips and solutions 
for these issues, as well as answers to common questions about Screens for 
Xamarin. 

## General Troubleshooting [](id=general-troubleshooting)

Before exploring specific issues, you should first make sure that you've 
installed the correct versions of Visual Studio and the Mono .NET framework. 
Each Screenlet's reference documentation (available for 
[Android](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-android) 
and 
[iOS](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-ios)) 
lists these versions. 

It may also help to investigate the sample Xamarin.Android and Xamarin.iOS apps 
developed by Liferay. Both are good examples of how to use Screenlets, Views 
(Android), and Themes (iOS): 

- [Showcase-Android](https://github.com/liferay/liferay-screens/tree/develop/xamarin/Samples/Showcase-Android)
- [Showcase-iOS](https://github.com/liferay/liferay-screens/tree/develop/xamarin/Samples/Showcase-Android)

If you get stuck at any point, you can post your question on our 
[forum](https://www.liferay.com/community/forums/-/message_boards/category/42706063). 
We're happy to assist you! 

## Common Issues [](id=common-issues)

1.  Build issues: 

    Running *Clean* in Visual Studio may not be enough. Close Visual Studio, 
    remove all the `bin` and `obj` folders that weren't removed by the clean, 
    then rebuild your project. 

2.  `NSUnknownKeyException` error in Xamarin.iOS:

    This error occurs when Liferay Screens for iOS has a wrong module name in an 
    XIB file. You must solve this in Xcode, removing the module name in the XIB 
    file's *Custom Class* assignment in Interface Builder. 

3.  `The selector is already registered` error in Xamarin.iOS:

    This error occurs because one or more methods share the same name. To fix 
    this, the binding file must be updated. Please file a ticket in our 
    [Jira](https://issues.liferay.com/browse/LMW/) 
    or post the issue on our 
    [forum](https://www.liferay.com/community/forums/-/message_boards/category/42706063).

4.  Xamarin.iOS crashes unexpectedly without any error messages in the console:

    Check the log file. On Mac OS, do this via the Console. On Windows, use the 
    Event Viewer. In the app, you must click *User Reports* and then look for 
    your app's name. Note that there may be more than one log file. 

5.  The app doesn't call delegate methods in Xamarin.iOS:

    When you implement the delegate methods in your view controller, make sure 
    to annotate them with `[Export(...)]`. You must also set the view controller 
    to the Screenlet instance's `Delegate` property. Here's an example of such a 
    view controller that implements Login Screenlet's delegate, 
    `ILoginScreenletDelegate`: 

        public partial class ViewController : UIViewController, ILoginScreenletDelegate
        {
            protected ViewController(IntPtr handle) : base(handle) {}

            public override void ViewDidLoad()
            {
                base.ViewDidLoad();

                this.loginScreenlet.Delegate = this;
            }

            [Export("screenlet:onLoginResponseUserAttributes:")]
            public virtual void OnLoginResponseUserAttributes(BaseScreenlet screenlet, 
                NSDictionary<NSString, NSObject> attributes)
            {
                ...
            }

            ...
        }

## DataType Mapping [](id=datatype-mapping)

For a better understanding of Xamarin code and example apps, see this list to 
compare type mapping between platforms. You must write Xamarin apps in C#, which 
has some differences compared to native code: 

-   Delegate (iOS) or listener (Android) classes:

    These classes are important because they listen for a Screenlet's events. In 
    Liferay Screens for Xamarin, Screenlet delegates and listeners are prefixed 
    with an `I`. For example, Login Screenlet's delegate in native code is 
    `LoginScreenletDelegate`, while in Xamarin it's `ILoginScreenletDelegate`. 
    Similarly, Login Screenlet's listener in native code is `LoginListener`, 
    while in Xamarin it's `ILoginListener`. Use a similar naming scheme when you 
    define a class/interface pair where the class is a standard implementation 
    of the interface. 

-   Getter and setter methods:

    To get or set a value in native code, you use its getter and setter methods. 
    In Liferay Screens for Xamarin, you should convert such methods to 
    properties. If you have only one of these methods, you can call the method 
    itself. For example:

        // If you implemented a setter and a getter, call the property
        loginScreenlet.Listener = this;

        // Otherwise, call the method
        loginScreenlet.getListener();

-   Pascal case convention:

    C# code is usually written in Pascal case. However, you should use Camel 
    case for protected instance fields or parameters. 

## Language Equivalents between Swift and C# [](id=language-equivalents-between-swift-and-c)

-   Protocols in Swift are analogous to interfaces in C#:

        // Swift
        protocol DoThings {
            func MyMethod() -> String
        }


        // C#
        interface DoThings
        {
            string MyMethod();
        }

-   Initializers in Swift are analogous to constructors in C#:

        // Swift
        class MyClass {
            var myVar : String = ""

            init(myVar : String) {
                self.myVar = myVar
            }
        }

        var testing = MyClass(myVar: "Test")


        // C#
        class MyClass {
            protected string myVar = "";

            public MyClass() {}

            public MyClass(string myVar) {
                this.myVar = myVar;
            }
        }

        var testing = new MyClass(myVar: "Test");

To learn more about language equivalents between Swift and C#, see 
[this quick reference](https://download.microsoft.com/download/4/6/9/469501F4-5F6B-4E51-897C-9A216CFB30A3/SwiftCSharpPoster.pdf). 

## Language Equivalents between Java and C# [](id=language-equivalents-between-java-and-c)

To extend or implement a class or interface, Java requires that you use the 
`extends` or `implements` keywords. C# doesn't require this:

    // Java
    class Bird extends Vertebrate implements Actions {
        ...
    }


    // C#
    class Bird : Vertebrate, Actions {
        ...
    }

To learn more about language equivalents between Java and C#, see 
[the C# for Java developers cheat sheet](https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&cad=rja&uact=8&ved=0ahUKEwjbr8bgz_XXAhWMMyYKHeUPA5wQFgg7MAA&url=http%3A%2F%2Fdownload.microsoft.com%2Fdownload%2FD%2FE%2FE%2FDEE91FC0-7AA9-4F6E-9FFA-8658AA0FA080%2FCSharp%2520for%2520Java%2520Developers%2520-%2520Cheat%2520Sheet.pdf&usg=AOvVaw1i0RzOcmyol7LhD59k9cUE). 

## FAQs [](id=faqs)

1.  Do I have to use Visual Studio?

    No, but we strongly recommend it. If you wish, however, you can use Xamarin 
    Studio or Visual Studio Code instead. 
    
2.  What's the meaning of `[Export(...)]` above delegate method names? 

    In short, this attribute makes properties and methods available in 
    Objective-C. 
    [Xamarin's documentation](https://developer.xamarin.com/api/type/MonoTouch.Foundation.ExportAttribute/) 
    explains this attribute in detail. 

## Related Topics [](id=related-topics)

[Preparing Xamarin Projects for Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/preparing-xamarin-projects-for-liferay-screens)

[Using Screenlets in Xamarin Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-xamarin-apps)

[Using Views in Xamarin.Android](/develop/tutorials/-/knowledge_base/7-0/using-views-in-xamarin-android)

[Creating Xamarin Views and Themes](/develop/tutorials/-/knowledge_base/7-0/creating-xamarin-views-and-themes)

[Using Themes in Xamarin.iOS](/develop/tutorials/-/knowledge_base/7-0/using-themes-in-xamarin-ios)
