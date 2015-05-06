# Using Views in Liferay Screens for Android [](id=using-views-in-liferay-screens-for-android)

Liferay Screens for Android uses *views* to set your app's UI independently of 
its core functionality. This lets you focus on your app's UI and UX without also 
needing to adjust its feature implementation. Also, since views are fully 
pluggable, they give you a great deal of flexibility when implementing your 
app's look and feel. Screens provides a limited set of views, but new ones can 
be created and contributed to the community. This tutorial shows you how to use 
views in your Screens for Android apps. Using views is very straightforward. 
You'll be a UI master in no time!

## Using Views in Your App [](id=using-views-in-your-app)

Before starting, you should understand the different components in the view 
layer: 

- *View*: Sometimes referred to as the *Custom View* or *Screenlet View*. This
  is the Java class that implements the UI's behavior. This class is usually the
  listener for the UI component's events.
- *Layout*: The XML file that defines the UI components in the view. The root
  element is usually the same view type as the previous screenlet view.
- *View set*: The view group for several screenlets together with their layouts.
  A view set usually has a name that is easy to refer to, such as *Default* or
  *Material*. Anyone can create their own view set and release it to the
  community. 

When you set up your project it includes two view sets by default: Default and 
Material. However, anyone can create a new view set and publish it in a public 
repository like Maven Central or jCenter. In that event, include the artifact 
containing the view set by using the standard steps for Gradle or Maven 
described in the tutorial [Preparing Your Android Project for Liferay Screens](/tutorials/-/knowledge_base/6-2/preparing-your-android-project-for-liferay-screens). 

To use a view set, set the `liferay:layoutId` property in your layout XML. This 
is shown in the following screenshot: 

![Figure 1: The `liferay:layoutId` attribute is used to change the layout.](../../images/screens-android-layoutid-xml.png)

Currently, the view sets included with Liferay Screens for Android are:

- *Default*: Standard views used when you insert any screenlet and don't set the 
  `liferay:layoutId` attribute.
- *Material*: Sample views intended to demonstrate how to develop your own 
  view sets from scratch. It follows the [Material Design](https://developer.android.com/design/material/index.html) 
  guidelines published by Google. 

That's all there is to it! Great! Now you know how to use views to spruce up 
your Android app. This opens up a world of possibilities, like writing your own 
views. 

## Related Topics [](id=related-topics)

[Liferay Screens Overview](/tutorials/-/knowledge_base/6-2/liferay-screens-overview)

[Preparing Your Android Project for Liferay Screens](/tutorials/-/knowledge_base/6-2/preparing-your-android-project-for-liferay-screens)

[Creating Views in Liferay Screens for Android](/tutorials/-/knowledge_base/6-2/creating-views-in-liferay-screens-for-android)

[Using Screenlets in Your Android Project](/tutorials/-/knowledge_base/6-2/using-screenlets-in-your-android-project)
