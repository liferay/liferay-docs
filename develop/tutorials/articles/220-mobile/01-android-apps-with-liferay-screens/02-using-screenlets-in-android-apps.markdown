# Using Screenlets in Android Apps [](id=using-screenlets-in-android-apps)

You can start using Screenlets once you've
[prepared](/develop/tutorials/-/knowledge_base/7-0/preparing-android-projects-for-liferay-screens)
your project to use Liferay Screens. There are plenty of Liferay Screenlets
available and they're described in the Screenlet
[reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-android).
It is very straightforward to use Screenlets. This tutorial shows you how to
insert Screenlets into your android app and configure them. You'll be a
Screenlet master in no time! 

<iframe width="560" height="315" src="https://www.youtube.com/embed/TZ09fbV9UuU" frameborder="0" allowfullscreen></iframe>

First, in Android Studio's visual layout editor or your favorite editor, open
your app's layout XML file and insert the Screenlet in your activity or fragment
layout. The following screenshot, for example, shows the Login Screenlet
inserted in an activity's `FrameLayout`. 

![Figure 1: Here's the Login Screenlet in an activity's layout in Android Studio.](../../../images/screens-android-insert-screenlet.png)

Next, set the Screenlet's attributes. If it's a Liferay Screenlet, refer to the
Screenlet
[reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-android) 
to learn the Screenlet's required and supported attributes. This screenshot
shows the attributes of the Login Screenlet being set:

![Figure 2: You can set a Screenlet's attributes via the app's layout XML file.](../../../images/screens-android-screenlet-attributes.png)

To configure your app to listen for events the Screenlet triggers, implement the
Screenlet's listener interface in your activity or fragment class. Refer to the
Screenlet's documentation to learn its listener interface. Then register your
activity or fragment as the Screenlet's listener. The activity class, for
example, in the screenshot below, declares that it implements the
Login Screenlet's `LoginListener` interface, and it registers itself to listen
for the Screenlet's events.

![Figure 3: Implement the Screenlet's listener in your activity or fragment class.](../../../images/screens-android-screenlet-listener.png)

Make sure to implement all methods required by the Screenlet's listener
interface. For Liferay's Screenlets, the listener methods are listed in each
Screenlet's
[reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-android).
That's all there is to it! Awesome! Now you know how to use Screenlets in your 
Android apps. 

**Related Topics**

[Preparing Android Projects for Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/preparing-android-projects-for-liferay-screens)

[Using Views in Android Screenlets](/develop/tutorials/-/knowledge_base/7-0/using-views-in-android-screenlets)

[Creating Android Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-android-screenlets)

[Using Screenlets in iOS apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-ios-apps)
