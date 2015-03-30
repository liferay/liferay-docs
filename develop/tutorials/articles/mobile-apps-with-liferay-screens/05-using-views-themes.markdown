# Using Views and Themes

Liferay Screens uses *views* and *themes* to set your app's UI independently of 
its core functionality. This lets you focus on your app's UI and UX without also 
needing to adjust its feature implementation. Also, since views and themes are 
fully pluggable, they give you a great deal of flexibility when implementing 
your app's look and feel. You should note that views are exclusive to Screens on 
Android, while themes are exclusive to Screens on iOS. Even though they have 
different names and implementations, views and themes serve the same purpose on 
each of their respective mobile platforms. Liferay provides a limited set of 
views and themes, but new ones can be created and contributed to the community. 
This tutorial shows you how to use views and themes in your Screens apps. Using 
views in Liferay Screens for Android is shown first. 

## Using Views in Liferay Screens for Android

Before starting, you should understand the different components in the view 
layer: 

- *View*: Sometimes refered to as the *Custom View* or *Screenlet View*. This is 
  the Java class that implements the UI's behavior. This class is usually the 
  listener for the UI component's events.
- *Layout*: The XML file that defines the UI components in the view. The root 
  element is usually the same view type as the previous screenlet view.
- *View set*: The group of views for several screenlets together with their 
  layouts. A view set usually has a name that is easy to refer to, such as 
  *Default* or *Material*. Anyone can create their own view set and release it 
  to the community. 

When you set up your project it includes two view sets by default: Default and 
Material. However, anyone can create a new view set and publish it in a public 
repository like Maven Central or jCenter. In that event, include the artifact 
containing the view set by using the standard steps for Gradle or Maven 
described in the tutorial [Preparing Your Project for Liferay Screens](http://www.liferay.com/). 

To use a view set, set the `liferay:layoutId` property in your layout XML. This 
is shown in the following screenshot: 

![The `liferay:layoutId` attribute is used to change the layout.](../../images/screens-android-layoutid-xml.png)

Currently, the view sets included with Liferay Screens for Android are:

- *Default*: Standard views used when you insert any screenlet and don't set the 
  `liferay:layoutId` attribute.
- *Material*: Sample views intended to demonstrate how to develop your own 
  viewsets from scratch. It follows the [Material Design](https://developer.android.com/design/material/index.html) 
  guidelines published by Google.

## Using Themes in Liferay Screens for iOS

To install a theme in your app, drag and drop the theme's folder into your 
project. Liferay Screens detects the new classes and then applies the new look 
and feel, in both the Interface Builder and runtime.

![Installing the Flat7 theme in an XCode project.](../../images/screens-ios-xcode-install-theme.png)

As soon as CocoaPods is ready to work with Swift, it will be possible to simply 
publish a new recipe for your theme. Users will then be able to install new 
themes by just adding a new line in their `Podfile`.

To use themes, it's as simple as setting the `themeName` property in your 
Interface Builder. If you leave this property empty or type a wrong name, the 
Default theme will be used.

![The `themeName` property in Interface Builder.](../../images/screens-ios-themes-property.png)

Currently, the themes included with Liferay Screens for iOS are:

- *Default*: The standard theme that is used when you insert any screenlet on 
  your screen.
- *Flat7*: A sample theme intended to demonstrate how to develop your own theme 
  from scratch.

## Related Topics

[Liferay Screens Overview](http://www.liferay.com/)

[Creating Views and Themes](http://www.liferay.com/)

[Using Screenlets](http://www.liferay.com/)
