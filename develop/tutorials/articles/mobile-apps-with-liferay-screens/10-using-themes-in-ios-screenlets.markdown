# Using Themes in iOS Screenlets [](id=using-themes-in-ios-screenlets)

Liferay Screens for iOS uses *themes* to set your app's UI independently of 
its core functionality. This lets you focus on your app's UI and UX without also 
needing to adjust its feature implementation. Also, since themes are fully 
pluggable, they give you a great deal of flexibility when implementing your 
app's look and feel. Screens provides a limited set of themes, but new ones can 
be created and contributed to the community. This tutorial shows you how to use 
themes in your Screens for iOS apps. Using themes is very straightforward. 
You'll be a UI master in no time! 

## Using Themes in Your Screenlet [](id=using-themes-in-your-screenlet)

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
  your screen. The Default theme can be used as the parent theme for any of your 
  custom themes. Please refer to the [Screens architecture tutorial](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-ios) 
  for more details on this.
- *Flat7*: A sample theme intended to demonstrate how to develop your own theme 
  from scratch. Please refer to the [theme creation tutorial](/develop/tutorials/-/knowledge_base/6-2/creating-ios-themes) 
  for instructions on creating your own theme. 
- *Westeros*: A custom theme created to customize the behaviour and appearance 
  of the [Westeros Bank](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/WesterosBank) 
  demo app.

That's all there is to it! Great! Now you know how to use themes to dress up 
your iOS app. This opens up a world of possibilities, like writing your own 
themes. 

## Related Topics [](id=related-topics)

[Preparing iOS Projects for Liferay Screens](/develop/tutorials/-/knowledge_base/6-2/preparing-ios-projects-for-liferay-screens)

[Creating iOS Themes](/develop/tutorials/-/knowledge_base/6-2/creating-ios-themes)

[Using Screenlets in iOS Apps](/develop/tutorials/-/knowledge_base/6-2/using-screenlets-in-ios-apps)
