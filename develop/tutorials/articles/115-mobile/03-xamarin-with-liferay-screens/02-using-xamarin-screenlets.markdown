# Using Screenlets in Xamarin Apps [](id=using-screenlets-in-xamarin-apps)

+$$$

**Note:** This documentation is currently in beta. 

$$$

You can start using Screenlets once you've  [prepared](/develop/tutorials/-/knowledge_base/7-0/preparing-xamarin-projects-for-liferay-screens) 
your Xamarin project to use Liferay Screens. There are plenty of Screenlets 
available and they're described in the Screenlet reference documentation: 

-   [Screenlets in Liferay Screens for Android](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-android)
-   [Screenlets in Liferay Screens for iOS](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-ios)

Using Screenlets is very straightforward. This tutorial shows you how to insert 
and configure Screenlets in your Xamarin app. You'll be a Screenlet master in no 
time! 

## Xamarin.iOS [](id=xamarin-ios)

First, insert a `UIView` in your storyboard (in Visual Studio's iOS Designer or 
Xcode's Interface Builder). Note that if you're editing an XIB file, you must 
insert a `UIView` inside the XIB's parent `UIView`. The following video, for 
example, shows Login Screenlet inserted in an storyboard. 


<iframe width="560" height="315" src="https://www.youtube.com/embed/y95XwdpCZVQ" frameborder="0" allowfullscreen></iframe>


Next, set the Screenlet's attributes. If it's a Liferay Screenlet, refer to the 
[Screenlet reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-ios) 
to learn the Screenlet's required and supported attributes. 

To configure your app to listen for events the Screenlet triggers, override the 
Screenlet's delegate methods in your view controller. Make sure to implement all 
the methods you need. Then register your view controller as the Screenlet's 
delegate. For Liferay's Screenlets, the delegate methods are listed in each 
Screenlet's 
[reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-ios). 

For example, here's a view controller that implements `LoginScreenletDelegate`: 

    public partial class ViewController : UIViewController, LoginScreenletDelegate
    {
        protected ViewController(IntPtr handle) : base(handle) {}

        public override void ViewDidLoad()
        {
            base.ViewDidLoad();

            /* Set the Screenlet's attributes */
            this.loginScreenlet.ThemeName = "demo";

            /* Listen for events the Screenlet triggers */
            this.loginScreenlet.Delegate = this;
        }

        ...

        /* Delegate methods */

        [Export("screenlet:onLoginResponseUserAttributes:")]
        public virtual void OnLoginResponseUserAttributes(BaseScreenlet screenlet, NSDictionary<NSString, NSObject> attributes)
        {
            ...
        }
    }

## Xamarin.Android [](id=xamarin-android)

First, open your app's layout AXML file and insert the Screenlet in your 
activity or fragment layout. For example, the following screenshot shows Login 
Screenlet in an activity's `FrameLayout`. 

![Figure 1: Here's Login Screenlet in an activity's layout in Visual Studio.](../../../images/screens-xamarin-android-insert-screenlet.png)

Next, set the Screenlet's attributes. If it's a Liferay Screenlet, refer to the 
[Screenlet reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-android) 
to learn the Screenlet's required and supported attributes. This screenshot 
shows Login Screenlet's attributes being set: 

![Figure 2: You can set a Screenlet's attributes via the app's layout AXML file.](../../../images/screens-xamarin-android-screenlet-attributes.png)

To configure your app to listen for events the Screenlet triggers, implement the 
Screenlet's listener interface in your activity or fragment class. Refer to the 
[Screenlet's reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-android) 
to learn its listener interface. Then register your activity or fragment as the 
Screenlet's listener. For example, the activity class in the following 
screenshot implements Login Screenlet's `LoginListener` interface, and registers 
itself to listen for the Screenlet's events. 

![Figure 3: Implement the Screenlet's listener in your activity or fragment class.](../../../images/screens-xamarin-android-screenlet-listener.png)

Make sure to implement all methods required by the Screenlet's listener 
interface. For Liferay's Screenlets, the listener methods are listed in each 
Screenlet's 
[reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-android). 

That's all there is to it! Awesome! Now you know how to use Screenlets in your 
Xamarin apps. 

## Related Topics [](id=related-topics)

[Preparing Xamarin Projects for Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/preparing-xamarin-projects-for-liferay-screens)

[Using Views in Xamarin.Android](/develop/tutorials/-/knowledge_base/7-0/using-views-in-xamarin-android)

[Using Themes in Xamarin.iOS](/develop/tutorials/-/knowledge_base/7-0/using-themes-in-xamarin-ios)

[Liferay Screens for Xamarin Troubleshooting and FAQs](/develop/tutorials/-/knowledge_base/7-0/liferay-screens-for-xamarin-troubleshooting-and-faqs)
