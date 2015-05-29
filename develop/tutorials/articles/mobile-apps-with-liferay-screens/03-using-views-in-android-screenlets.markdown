# Using Views in Android Screenlets [](id=using-views-in-android-screenlets)

Using a Liferay Screens *view*, you can set your screenlet's UI components,
behavior, and look and feel. They let you focus on a screenlet's UI and UX,
without having to worry about its core functionality. Liferay's screenlets come
with several views, and more are being developed by Liferay and the community. A
Liferay screenlet's views are specified in its
[reference documentation](/develop/reference/-/knowledge_base/6-2/screenlets-in-liferay-screens-for-android).
This tutorial shows you how to use views in Android screenlets. It's
straightforward; you'll master using views in no time!

Here are some view layer components you should understand: 

- *View*: Implements the UI's behavior. Sometimes referred to as the *Custom
  View* or *Screenlet View*, it's a Java class that usually listens for the UI
  components' events.
- *Layout*: Defines the UI components in the view. It's an XML file whose root
  element is usually the same view type as the previous screenlet view.
- *View set*: It's a group of views and layouts, used by several
  screenlets. They typically has simple names, like *Default* or *Material*. 

Liferay Screens for Android comes with the Default view set, but Liferay makes
additional view sets, like Material and Westeros, available in jCenter. Anyone
can create view sets and publish them in public repositories like Maven Central
and jCenter. 

To install view sets for your Gradle or Maven projects, you add them as
dependencies. The `build.gradle` file code snippet below specifies Material and
Westeros view sets as dependencies: 

    dependencies {
        ...
        compile 'com.liferay.mobile:liferay-material-viewset:1.0.+'
        compile 'com.liferay.mobile:liferay-westeros-viewset:1.0.+'	
        ...
    }

Here are some view sets that Liferay created for Android screenlets:

- *Default*: Comes standard with a screenlet. It's used by a screenlet if no
  layout ID is specified or if no view is found with the layout ID. The Default 
  views can be used as parent views for your custom views. Refer to the 
  [architecture tutorial](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android) 
  for more details. 
- *Material*: Demonstrates views built from scratch. It follows Google's
  [Material Design](https://developer.android.com/design/material/index.html) 
  guidelines. Refer to the [view creation tutorial](/develop/tutorials/-/knowledge_base/6-2/creating-android-views) 
  for instructions on creating your own views.
- *Westeros*: Customizes the behavior and appearance of the
  [Westeros Bank](https://github.com/liferay/liferay-screens/tree/master/android/samples/bankofwesteros) 
  demo app. 

To use a view set in your screenlet, specify its name as the `liferay:layoutId`
property value in your app's layout XML. This is shown in the following
screenshot: 

![Figure 1: You can set a screenlet's layout via its `liferay:layoutId` attribute.](../../images/screens-android-layoutid-xml.png)

That's all there is to it! Great! Now you know how to use views to spruce up 
your Android screenlets. This opens up a world of possibilities, like 
[writing your own views](/develop/tutorials/-/knowledge_base/6-2/creating-android-views). 

**Related Topics**

[Preparing Android Projects for Liferay Screens](/develop/tutorials/-/knowledge_base/6-2/preparing-android-projects-for-liferay-screens)

[Using Screenlets in Android Apps](/develop/tutorials/-/knowledge_base/6-2/using-screenlets-in-android-apps)

[Creating Android Views](/develop/tutorials/-/knowledge_base/6-2/creating-android-views)

[Architecture of Liferay Screens for Android](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android)

[Using Themes in iOS Screenlets](/develop/tutorials/-/knowledge_base/6-2/using-themes-in-ios-screenlets)
