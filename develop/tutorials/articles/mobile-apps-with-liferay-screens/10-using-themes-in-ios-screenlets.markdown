# Using Themes in iOS Screenlets [](id=using-themes-in-ios-screenlets)

Using a Liferay Screens *theme*, you can set your screenlet's UI components,
behavior, and look and feel. They let you focus on a screenlet's UI and UX,
without having to worry about its core functionality. Liferay's screenlets come
with several themes, and more are being developed by Liferay and the community.
A Liferay screenlet's themes are specified in its
[reference documentation](/develop/reference/-/knowledge_base/6-2/screenlets-in-liferay-screens-for-ios).
This tutorial shows you how to use themes in your iOS screenlets. 

To install a theme in your iOS app's screenlet, you have two options, depending 
on how the theme has been published:

1. If the theme has been packaged as a CocoaPods pod dependency, you can install 
   and use the theme by adding the following line in your Podfile:

        pod 'LiferayScreens-YourThemeName'

2. If the theme isn't available through CocoaPods, you can drag and drop the 
   theme's folder into your project. Liferay Screens detects the new classes and 
   then applies the new look and feel in the runtime and in Interface Builder. 

![Figure 1: To install a theme into an Xcode project, drag and drop the theme's folder into it.](../../images/screens-ios-xcode-install-theme.png)

To use the installed theme, specify its name in the *Theme Name* property field
of the *Base Screenlet* in Interface Builder. The names of each screenlet's
themes are listed in the *Themes* section of the screenlet's
[reference documentation](/develop/reference/-/knowledge_base/6-2/screenlets-in-liferay-screens-for-ios). 
If you leave the theme name property blank or enter a name for a theme that
can't be found, the screenlet's Default theme is used. 

![Figure 2: In Interface Builder, you specify a screenlet's theme by entering its name in the *Theme Name* field; this sets the screenlet's `themeName` property.](../../images/screens-ios-themes-property.png)

The initial release of Liferay Screens for iOS includes the following themes for
its screenlets: 

- *Default*: Comes standard with a screenlet. It's used by a screenlet if no
  theme name is specified or the named theme can't be found. The Default theme 
  can be used as the parent theme for your custom themes. Refer to the 
  [architecture tutorial](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-ios)
  for more details.
- *Flat7*: Demonstrates a theme made from scratch. Refer to the 
  [theme creation tutorial](/develop/tutorials/-/knowledge_base/6-2/creating-ios-themes) 
  for instructions on creating your own theme.
- *Westeros*: Customizes the behavior and appearance of the
  [Westeros Bank](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/WesterosBank) 
  demo app.

That's all there is to it! Great! Now you know how to use themes to dress up 
screenlets in your iOS app. This opens up a world of possibilities--like 
[writing your own themes](/develop/tutorials/-/knowledge_base/6-2/creating-ios-themes).

**Related Topics**

[Preparing iOS Projects for Liferay Screens](/develop/tutorials/-/knowledge_base/6-2/preparing-ios-projects-for-liferay-screens)

[Creating iOS Themes](/develop/tutorials/-/knowledge_base/6-2/creating-ios-themes)

[Using Screenlets in iOS Apps](/develop/tutorials/-/knowledge_base/6-2/using-screenlets-ios-apps)

[Architecture of Liferay Screens for iOS](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-ios)

[Using Views in Android Screenlets](/develop/tutorials/-/knowledge_base/6-2/using-views-in-android-screenlets)
