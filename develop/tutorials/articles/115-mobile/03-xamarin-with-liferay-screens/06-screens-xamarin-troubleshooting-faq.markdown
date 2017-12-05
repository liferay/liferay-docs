# Liferay Screens for Xamarin Troubleshooting and FAQs

Even though Liferay developed Screens for Xamarin with great care, you may still
run into some common issues. Here are solutions and tips for solving these
issues. You'll also find answers to common questions about Screens for Xamarin.

## General Troubleshooting

Before delving into specific issues, you should first make sure that you've 
installed the correct versions of Visual Studio and the Mono .NET framework. 
Each Screenlet's reference documentation (
[Android](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-android) 
and 
[iOS](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-ios)
) lists these versions. 

It may also help to investigate the sample Xamarin.Android and Xamarin.iOS apps 
developed by Liferay. Both serve as good examples of how to use Screenlets and 
Views: 

- [Showcase-Android](https://github.com/liferay/liferay-screens/tree/develop/xamarin/Samples/Showcase-Android)
- [Showcase-iOS](https://github.com/liferay/liferay-screens/tree/develop/xamarin/Samples/Showcase-Android)

If you get stuck at any point, you can post your question on our 
[forum](https://www.liferay.com/community/forums/-/message_boards/category/42706063). 
We're happy to assist you! 

## Common Issues

This section contains information on common issues that can occur when using 
Liferay Screens for Xamarin.

1.  If you have build issues:

    Note that running *Clean* in Visual Studio might not be enough. Close Visual
    Studio, remove all the `bin` and `obj` folders that weren't removed by the 
    clean, then rebuild your project. 

2.  `NSUknownKeyException` error in Xamarin.iOS:

    This error occurs when Liferay Screens for iOS has a wrong module name in an 
    XIB file. You must use native code to solve this. Post your issue on our 
    [forum](https://www.liferay.com/community/forums/-/message_boards/category/42706063).

3.  `The selector is already registered` error in Xamarin.iOS:

    This error occurs because one or more methods share the same name. To fix 
    this, the binding file must be updated. Please file a ticket in our 
    [Jira](https://issues.liferay.com/browse/LMW/) 
    or post the issue on our 
    [forum](https://www.liferay.com/community/forums/-/message_boards/category/42706063).

4.  Xamarin.iOS crashes unexpectedly without any error messages in the console:

    Check the log file. You can do this from Mac OS via the Console. In the app, 
    you must click *User Reports* and then look for you app’s name. Note that 
    there may be more than one log file. On Windows, you can use the Event 
    Viewer.
    
5.  The app doesn't call delegate methods in Xamarin.iOS:

    Make sure to annotate your delegate method with `[Export(...)]`. Also, set 
    the `Delegate` property to the Screenlet and implement the delegate in your 
    class. For example:

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

## DataType Mapping

For a better understanding about Xamarin code and example apps, see this list to 
compare type mapping between platforms. You must write Xamarin apps in C#, which 
has some differences compared to native code: 

-   Delegate or Listener classes:

    These classes are important because they listen for a Screenlet's events. 
    For example, when using Login Screenlet in native code, you have 
    `LoginListener` for Android and `LoginScreenletDelegate` for iOS. In Liferay 
    Screens for Xamarin, you instead have `ILoginListener` and 
    `ILoginScreenletDelegate`, respectively (note the naming convention of 
    prefixing an `I` to the name). Use similar names when you define a 
    class/interface pair where the class is a standard implementation of the 
    interface.

-   Getter and setter methods:

    To get or set a value in native code, you use its getter and setter methods. 
    In Liferay Screens for Xamarin, you should convert such methods to 
    properties. If you have only one of these methods, you can call the method 
    itself. For example:

        //If you implemented a setter and a getter, call the property
        loginScreenlet.Listener = this;

        //Otherwise, call the method
        loginScreenlet.getListener();

-   Pascal case convention:

    C# code is usually written in Pascal case. However, you should use Camel 
    case for protected instance fields or parameters.
## Language Equivalents between Swift and C#

This section is to explain what equivalents exists between Swift and C#:

-   Protocols:
    
    | Swift | C# |
    |:-----:| --:|
    | protocol | interface |

    Swift:

        protocol PrintSelf {
            func ToString() -> String
        }

    C#:

        interface PrintSelf
        {
            string PrintString();
        }

-   Initializers:

    | Swift | C# |
    |:-----:| --:|
    | init | constructor |

    Swift:

        class Pet {
            var name : String = ""

            init(name : String) {
                self.name = name
            }
        }

        var spot = Pet(name: "Spot")

    C#:

        class Pet {
            protected string name = "";

            public Pet() {}

            public Pet(string name) {
                this.name = name;
            }
        }

        var spot = new Pet(name: "Spot");

If you want to learn more about language equivalents between Swift and C#, 
please see 
[this pdf](https://download.microsoft.com/download/4/6/9/469501F4-5F6B-4E51-897C-9A216CFB30A3/SwiftCSharpPoster.pdf).

## Language Equivalents between Java and C#

This section is to explain what equivalents exist between Java and C#:

-   Java `extends` and `implements` keywords:

    If you want to extend or implement a class from another, you have to do it 
    this way:
    
    Java: 

        class Car extends Vehicle implements Actions {
            ...
        }


    C#:

        class Car : Vehicle, Actions {
            ...
        }

If you want to learn more about language equivalents between Java and C#, please 
see 
[this pdf](https://www.google.es/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&cad=rja&uact=8&ved=0ahUKEwj9i4jp1OjXAhUQkRQKHaDtA0kQFggnMAA&url=http%3A%2F%2Fdownload.microsoft.com%2Fdownload%2FD%2FE%2FE%2FDEE91FC0-7AA9-4F6E-9FFA-8658AA0FA080%2FCSharp%2520for%2520Java%2520Developers%2520-%2520Cheat%2520Sheet.pdf&usg=AOvVaw1i0RzOcmyol7LhD59k9cUE).    

## FAQs

1.  Do I have to use Visual Studio?

    It's not necessary to use Visual Studio for Xamarin development. You can use 
    Xamarin Studio or Visual Studio Code instead. But we strongly recommend, 
    however, that you use Visual Studio. 
    
2.  What's the meaning of `[Export(...)]` below delegate method names?

    According to Xamarin documentation, this attribute is applied to properties 
    and methods in classes that derive from NSObject to export the value to the 
    Objective-C world. This can be used either to respond to messages or to 
    override an Objective-C method. 
