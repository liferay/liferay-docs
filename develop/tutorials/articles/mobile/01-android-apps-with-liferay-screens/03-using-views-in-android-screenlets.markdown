# Using Views in Android Screenlets [](id=using-views-in-android-screenlets)

Using a Liferay Screens *View*, you can set your Screenlet's UI components,
behavior, and style. They let you focus on a Screenlet's UI and UX, without
having to worry about its core functionality. Liferay's Screenlets come with
several Views, and more are being developed by Liferay and the community. A
Liferay Screenlet's Views are specified in its
[reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-android).
This tutorial shows you how to use Views in Android Screenlets. It's
straightforward; you'll master using Views in no time!

<iframe width="560" height="315" src="https://www.youtube.com/embed/ImAcH3JHYug" frameborder="0" allowfullscreen></iframe>

Here are some View layer components you should understand: 

- *View*: Implements the UI's behavior. Sometimes referred to as the *Custom
  View* or *Screenlet View*, it's a Java class that usually listens for the UI
  components' events.
- *Layout*: Defines the UI components in the View. It's an XML file whose root
  element is usually the same View type as the previous Screenlet View.
- *View Set*: It's a group of Views and layouts, used by several
  Screenlets. They typically has simple names, like *Default* or *Material*. 

Liferay Screens for Android comes with the Default View Set, but Liferay makes
additional View Sets, like Material and Westeros, available in jCenter. Anyone
can create View Sets and publish them in public repositories like Maven Central
and jCenter. 

To install View Sets for your Gradle or Maven projects, you add them as
dependencies. The `build.gradle` file code snippet below specifies Material and
Westeros View Sets as dependencies: 

    dependencies {
        ...
        compile 'com.liferay.mobile:liferay-material-viewset:+'
        compile 'com.liferay.mobile:liferay-westeros-viewset:+'	
        ...
    }

Here are some View Sets that Liferay created for Android Screenlets:

- *Default*: Comes standard with a Screenlet. It's used by a Screenlet if no
  layout ID is specified or if no View is found with the layout ID. The Default 
  Views can be used as parent Views for your custom Views. Refer to the 
  [architecture tutorial](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-android) 
  for more details. 
- *Material*: Demonstrates Views built from scratch. It follows Google's
  [Material Design](https://developer.android.com/design/material/index.html) 
  guidelines. Refer to the [View creation tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-android-views) 
  for instructions on creating your own Views.
- *Westeros*: Customizes the behavior and appearance of the
  [Westeros Bank](https://github.com/liferay/liferay-screens/tree/master/android/samples/bankofwesteros) 
  demo app. 

To use a View in your Screenlet, specify its name as the `liferay:layoutId` 
property value in your app's layout XML. This is shown in the following 
screenshot: 

![Figure 1: You can set a Screenlet's layout via its `liferay:layoutId` attribute.](../../../images/screens-android-layoutid-xml.png)

You can also use a View Set by having your app's theme inherit the View Set. For 
example, the following code in an app's `res/values/styles.xml` tells 
`AppTheme.NoActionBar` to use the Material View Set as its parent theme: 

    <resources>

        <style name="AppTheme.NoActionBar" parent="material_theme">
            <item name="colorPrimary">@color/colorPrimary</item>
            <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
            <item name="colorAccent">@color/colorAccent</item>

            <item name="windowActionBar">false</item>
            <item name="windowNoTitle">true</item>
        </style>
        ...
    </resources>

That's all there is to it! Great! Now you know how to use Views to spruce up 
your Android Screenlets. This opens up a world of possibilities, like 
[writing your own Views](/develop/tutorials/-/knowledge_base/7-0/creating-android-views). 

**Related Topics**

[Preparing Android Projects for Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/preparing-android-projects-for-liferay-screens)

[Using Screenlets in Android Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-android-apps)

[Creating Android Views](/develop/tutorials/-/knowledge_base/7-0/creating-android-views)

[Architecture of Liferay Screens for Android](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-android)

[Using Themes in iOS Screenlets](/develop/tutorials/-/knowledge_base/7-0/using-themes-in-ios-screenlets)
