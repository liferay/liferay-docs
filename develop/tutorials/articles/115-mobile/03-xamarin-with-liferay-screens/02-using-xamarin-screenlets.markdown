# Using Screenlets in Xamarin Apps

+$$$

**Note:** This documentation is currently in beta. 

$$$

You can start using Screenlets once you've [prepared](/develop/tutorials/-/knowledge_base/7-0/preparing-xamarin-projects-for-liferay-screens) your project to use Liferay Screens. There are plenty of Screenlets available and they're described in the Screenlet [reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-ios) for iOS and [reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-android) for Android.

It is very straightforward to use Screenlets. This tutorial shows you how to insert Screenlets into your Xamarin app and configure them. You'll be a Screenlet master in no time!

## Xamarin.iOS

If you're creating an app for Xamarin.iOS, first, in Visual Studio iOS Designer or if you prefer with Xcode Interface Builder, open your app's Storyboard and insert an UIView in your Storyboard. If we're editing a Xib file we have to insert an UIView inside the general UIView. The following video, for example, shows the Login Screenlet inserted in an Storyboard. 

<iframe width="560" height="315" src="https://www.youtube.com/embed/y95XwdpCZVQ" frameborder="0" allowfullscreen></iframe>

Next, set the Screenlet's attributes. If it's a Liferay Screenlet, refer to the Screenlet iOS [reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-ios) to learn the Screenlet's required and supported attributes.

To configure your app to listen for events the Screenlet triggers, override the Screenlet's delegate methods in your ViewController. Make sure to implement all the methods you need. Then register your ViewController as the Screenlet's delegate. For Liferay's Screenlets, the delegate methods are listed in each Screenlet's [reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-ios).

## Xamarin.Android

If you're creating an app for Xamarin.Android, first, in Visual Studio Layout Editor or in your favorite editor, open your app's layout AXML file and insert the Screenlet in your Activity or Fragment layout. The following screenshot, for example, shows the Login Screenlet inserted in an Activity's `FrameLayout`. 

![Figure 1: Here's the Login Screenlet in an Activity's layout in Visual Studio.](../../../images/screens-xamarin-android-insert-screenlet.png)

Next, set the Screenlet's attributes. If it's a Liferay Screenlet, refer to the Screenlet [reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-android) to learn the Screenlet's required and supported attributes. This screenshot shows the attributes of the Login Screenlet being set:

![Figure 2: You can set a Screenlet's attributes via the app's layout XML file.](../../../images/screens-xamarin-android-screenlet-attributes.png)

To configure your app to listen for events the Screenlet triggers, implement the Screenlet's listener interface in your Activity or Fragment class. Refer to the Screenlet's documentation to learn its listener interface. Then register your Activity or Fragment as the Screenlet's listener. The activity class, for example, in the screenshot below, declares that it implements the Login Screenlet's `LoginListener` interface, and it registers itself to listen for the Screenlet's events.

![Figure 3: Implement the Screenlet's listener in your activity or fragment class.](../../../images/screens-xamarin-android-screenlet-listener.png)

Make sure to implement all methods required by the Screenlet's listener interface. For Liferay's Screenlets, the listener methods are listed in each Screenlet's [reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-android). 

That's all there is to it! Awesome! Now you know how to use Screenlets in your 
Xamarin apps. 

## Related Topics

[Preparing Xamarin Projects for Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/preparing-xamarin-projects-for-liferay-screens)

[Screenlets in Liferay Screens for iOS](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-iOS)

[Screenlets in Liferay Screens for Android](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-android)

[Using Views in Xamarin Apps](/develop/tutorials/-/knowledge_base/7-0/using-views-in-xamarin-apps)

[Creating Xamarin Views for the Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-xamarin-views-for-the-screenlets)

[Using offline mode in Xamarin Apps](/develop/tutorials/-/knowledge_base/7-0/using-offline-mode-in-xamarin-apps)
