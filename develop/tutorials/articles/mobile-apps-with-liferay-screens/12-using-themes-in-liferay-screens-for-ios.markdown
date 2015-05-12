# Using Themes in Liferay Screens for iOS [](id=using-themes-in-liferay-screens-for-ios)

Liferay Screens for iOS uses *themes* to set your app's UI independently of 
its core functionality. This lets you focus on your app's UI and UX without also 
needing to adjust its feature implementation. Also, since themes are fully 
pluggable, they give you a great deal of flexibility when implementing your 
app's look and feel. Screens provides a limited set of themes, but new ones can 
be created and contributed to the community. This tutorial shows you how to use 
themes in your Screens for iOS apps. Using themes is very straightforward. 
You'll be a UI master in no time! 

## Using Themes in Your App [](id=using-themes-in-your-app)

To install a theme in your iOS app, drag and drop the theme's folder into your 
project. Liferay Screens detects the new classes and then applies the new look 
and feel in the runtime and in Interface Builder. 

![Figure !: Installing the Flat7 theme in an XCode project.](../../images/screens-ios-xcode-install-theme.png)

As soon as CocoaPods is ready to work with Swift, it will be possible to simply 
publish a new recipe for your theme. Users will then be able to install new 
themes by adding a new line in their `Podfile`. 

To use themes, it's as simple as setting the `themeName` property in your 
Interface Builder. If you leave this property empty or type the wrong name, the 
Default theme is used. 

![Figure 1: The `themeName` property in Interface Builder.](../../images/screens-ios-themes-property.png)

Currently, the themes included with Liferay Screens for iOS are:

- *Default*: The standard theme that is used when you insert any screenlet on 
  your screen.
- *Flat7*: A sample theme intended to demonstrate how to develop your own theme 
  from scratch.

That's all there is to it! Great! Now you know how to use themes to dress up 
your iOS app. This opens up a world of possibilities, like writing your own 
themes. 

## Related Topics [](id=related-topics)

[Liferay Screens Overview](/tutorials/-/knowledge_base/6-2/liferay-screens-overview)

[Preparing Your iOS Project for Liferay Screens](/tutorials/-/knowledge_base/6-2/preparing-your-ios-project-for-liferay-screens)

[Creating Themes in Liferay Screens for iOS](/tutorials/-/knowledge_base/6-2/creating-themes-in-liferay-screens-for-ios)

[Using Screenlets in Your iOS Project](/tutorials/-/knowledge_base/6-2/using-screenlets-in-your-ios-project)
