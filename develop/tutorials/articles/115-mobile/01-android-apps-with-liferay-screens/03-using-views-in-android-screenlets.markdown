# Using Views in Android Screenlets [](id=using-views-in-android-screenlets)

You can use a Liferay Screens *View* to set a Screenlet's look and feel 
independent of the Screenlet's core functionality. Liferay's Screenlets come 
with several Views, and more are being developed by Liferay and the community. 
[The Screenlet reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-android) 
lists the Views available for each Screenlet included with Screens. This 
tutorial shows you how to use Views in Android Screenlets. It's straightforward; 
you'll master using Views in no time! 
<!-- 
Video is outdated:

<iframe width="560" height="315" src="https://www.youtube.com/embed/ImAcH3JHYug" frameborder="0" allowfullscreen></iframe>
-->

## Views and View Sets [](id=views-and-view-sets)

Before using Views, you should know what components make them up. Note that what 
follows is a simple description, sufficient for learning how to use different 
Views. For a detailed description of the View layer in Liferay Screens, see the 
tutorial 
[Architecture of Liferay Screens for Android](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-android). 

A View consists of the following items: 

**Screenlet class:** A Java class that coordinates and implements the
Screenlet's functionality. The Screenlet class contains attributes for
configuring the Screenlet's behavior, a reference to the Screenlet's View class,
methods for invoking server operations, and more. 

**View class:** A Java class that implements a View's behavior. This class
usually listens for the UI components' events. 

**Layout:** An XML file that defines a View's UI components. The View class is
usually this file's root element. To use a View, you must specify its layout in
the Screenlet XML (you'll see an example of this shortly). 

Note that because it contains a Screenlet class and a specific set of UI 
components, a View can only be used with one particular Screenlet. For example, 
the Default View for Login Screenlet can only be used with Login Screenlet. 
Multiple Views for several Screenlets can be combined into a *View Set*. A View 
Set typically implements a similar look and feel for several Screenlets. This 
lets an app use a View Set to present a cohesive look and feel. For example, 
[the Bank of Westeros sample app](https://github.com/liferay/liferay-screens/tree/master/android/samples/bankofwesteros) 
uses the 
[Westeros View Set's](https://github.com/liferay/liferay-screens/tree/master/android/viewsets/westeros/src/main) 
Views with several Screenlets to present the red and white motif you can see 
[here on Google Play](https://play.google.com/store/apps/details?id=com.liferay.mobile.screens.bankofwesteros). 
Liferay Screens for Android comes with the Default View Set, but Liferay makes
additional View Sets, like Material and Westeros, available in jCenter. Anyone
can create View Sets and publish them in public repositories like Maven Central
and jCenter. 

To install View Sets besides Default, add them as dependencies in your project. 
The `build.gradle` file code snippet below specifies the Material and Westeros 
View Sets as dependencies: 

    dependencies {
        ...
        compile 'com.liferay.mobile:liferay-material-viewset:+'
        compile 'com.liferay.mobile:liferay-westeros-viewset:+'	
        ...
    }

Here are the View Sets that Liferay currently provides for Screens: 

**Default:** Comes standard with a Screenlet. It's used by a Screenlet if no
layout ID is specified or if no View is found with the layout ID. The Default
Views can be used as parent Views for your custom Views. Refer to the
[architecture tutorial](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-android)
for more details. 

**Material:** Demonstrates Views built from scratch. It follows Google's
[Material Design](https://developer.android.com/design/material/index.html)
guidelines. Refer to the 
[View creation tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-android-views) for
instructions on creating your own Views. 

**Westeros:** Customizes the behavior and appearance of the 
[Westeros Bank](https://github.com/liferay/liferay-screens/tree/master/android/samples/bankofwesteros)
demo app. 

Now that you know about Views and View Sets, you're ready to put them to use! 

## Using Views [](id=using-views)

To use a View in a Screenlet, specify the View's layout as the 
`liferay:layoutId` attribute's value when inserting the Screenlet XML in an 
activity or fragment layout. For example, to use Login Screenlet with its 
Material View, insert the Screenlet's XML with `liferay:layoutId` set to 
`@layout/login_material`: 

    <com.liferay.mobile.screens.auth.login.LoginScreenlet
        android:id="@+id/login_screenlet"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        liferay:layoutId="@layout/login_material"
        />

The following links list the View layouts available in each View Set: 

- [Default](https://github.com/liferay/liferay-screens/tree/master/android/library/src/main/res/layout)
- [Material](https://github.com/liferay/liferay-screens/tree/master/android/viewsets/material/src/main/res/layout)
- [Westeros](https://github.com/liferay/liferay-screens/tree/master/android/viewsets/westeros/src/main/res/layout)

If the View you want to use is part of a View Set, your app or activity's theme 
must also inherit the theme that defines that View Set's styles. For example, 
the following code in an app's `res/values/styles.xml` tells 
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

To use the Default or Westeros View Set, inherit `default_theme` or 
`westeros_theme`, respectively. 

That's it! Great! Now you know how to use Views to spruce up your Android 
Screenlets. This opens up a world of possibilities, like 
[writing your own Views](/develop/tutorials/-/knowledge_base/7-0/creating-android-views). 

## Related Topics [](id=related-topics)

[Preparing Android Projects for Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/preparing-android-projects-for-liferay-screens)

[Using Screenlets in Android Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-android-apps)

[Creating Android Views](/develop/tutorials/-/knowledge_base/7-0/creating-android-views)

[Architecture of Liferay Screens for Android](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-android)

[Using Themes in iOS Screenlets](/develop/tutorials/-/knowledge_base/7-0/using-themes-in-ios-screenlets)
