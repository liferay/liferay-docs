# Using Screenlets in Android Apps [](id=using-screenlets-in-android-apps)

You can start using screenlets once you've
[prepared](/develop/tutorials/-/knowledge_base/6-2/preparing-android-projects-for-liferay-screens)
your project to use Liferay Screens. There are plenty of Liferay screenlets
available and they're described in the screenlet
[reference documentation](/develop/reference/-/knowledge_base/6-2/screenlets-in-liferay-screens-for-android).
It is very straightforward to use screenlets. This tutorial shows you how to
insert screenlets into your android app and configure them. You'll be a
screenlet master in no time! 

First, in Android Studio's visual layout editor or your favorite editor, open
your app's layout XML file and insert the screenlet in your activity or fragment
layout. The following screenshot, for example, shows the `LoginScreenlet`
inserted in an activity's `FrameLayout`. 

![Figure 1: Here's the `LoginScreenlet` in an activity's layout in Android Studio.](../../images/screens-android-insert-screenlet.png)

Next, set the screenlet's attributes. If it's a Liferay screenlet, refer to the
screenlet
[reference documentation](/develop/reference/-/knowledge_base/6-2/screenlets-in-liferay-screens-for-android) 
to learn the screenlet's required and supported attributes. This screenshot
shows the attributes of the `LoginScreenlet` being set:

![Figure 2: You can set a screenlet's attributes via the app's layout XML file.](../../images/screens-android-screenlet-attributes.png)

To configure your app to listen for events the screenlet triggers, implement the
screenlet's listener interface in your activity or fragment class. Refer to the
screenlet's documentation to learn its listener interface. Then register your
activity or fragment as the screenlet's listener. The activity class, for
example, in the screenshot below, declares that it implements the
`LoginScreenlet`'s `LoginListener` interface, and it registers itself to listen
for the screenlet's events.

![Figure 3: Implement the screenlet's listener in your activity or fragment class.](../../images/screens-android-screenlet-listener.png)

Make sure to implement all methods required by the screenlet's listener
interface. For Liferay's screenlets, the listener methods are listed in each
screenlet's
[reference documentation](/develop/reference/-/knowledge_base/6-2/screenlets-in-liferay-screens-for-android).
That's all there is to it! Awesome! Now you know how to use screenlets in your 
Android apps. 

**Related Topics**

[Preparing Android Projects for Liferay Screens](/develop/tutorials/-/knowledge_base/6-2/preparing-android-projects-for-liferay-screens)

[Using Views in Android Screenlets](/develop/tutorials/-/knowledge_base/6-2/using-views-in-android-screenlets)

[Creating Android Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-android-screenlets)

[Using Screenlets in iOS apps](/develop/tutorials/-/knowledge_base/6-2/using-screenlets-in-ios-apps)
