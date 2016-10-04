# Using Themes in iOS Screenlets [](id=using-themes-in-ios-screenlets)

Using a Liferay Screens *Theme*, you can set your Screenlet's UI components,
style, and behavior. They let you focus on a Screenlet's UI and UX, without
having to worry about its core functionality. Liferay's Screenlets come with
several Themes, and more are being developed by Liferay and the community. A
Liferay Screenlet's Themes are specified in its
[reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-ios).
This tutorial shows you how to use Themes in your iOS Screenlets. 

<iframe width="560" height="315" src="https://www.youtube.com/embed/vgwDZJcs3-Y" frameborder="0" allowfullscreen></iframe>

To install a Theme in your iOS app's Screenlet, you have two options, depending 
on how the Theme has been published:

1.  If the Theme has been packaged as a CocoaPods pod dependency, you can install 
    it by adding a line to your Podfile:

        pod 'LiferayScreensThemeName'

    Make sure to replace `LiferayScreensThemeName` with the Theme's
    CocoaPods project name. 

2. If the Theme isn't available through CocoaPods, you can drag and drop the 
   Theme's folder into your project. Liferay Screens detects the new classes and 
   then applies the new design in the runtime and in Interface Builder. 

![Figure 1: To install a Theme into an Xcode project, drag and drop the Theme's folder into it.](../../../images/screens-ios-xcode-install-theme.png)

To use the installed Theme, specify its name in the *Theme Name* property field
of the *Base Screenlet* in Interface Builder. The names of each Screenlet's
Themes are listed in the *Themes* section of the Screenlet's
[reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-ios). 
If you leave the Theme name property blank or enter a name for a Theme that
can't be found, the Screenlet's Default Theme is used. 

![Figure 2: In Interface Builder, you specify a Screenlet's Theme by entering its name in the *Theme Name* field; this sets the Screenlet's `themeName` property.](../../../images/screens-ios-themes-property.png)

The initial release of Liferay Screens for iOS includes the following Themes for
its Screenlets: 

- *Default*: Comes standard with a Screenlet. It's used by a Screenlet if no
  Theme name is specified or the named Theme can't be found. The Default Theme 
  can be used as the parent Theme for your custom Themes. Refer to the 
  [architecture tutorial](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-ios)
  for more details.
- *Flat7*: Demonstrates a Theme made from scratch. Refer to the 
  [Theme creation tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes) 
  for instructions on creating your own Theme.
- *Westeros*: Customizes the behavior and appearance of the
  [Westeros Bank](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/WesterosBank) 
  demo app.

That's all there is to it! Great! Now you know how to use Themes to dress up 
Screenlets in your iOS app. This opens up a world of possibilities--like 
[writing your own Themes](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes).

**Related Topics**

[Preparing iOS Projects for Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/preparing-ios-projects-for-liferay-screens)

[Creating iOS Themes](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes)

[Using Screenlets in iOS Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-ios-apps)

[Architecture of Liferay Screens for iOS](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-ios)

[Using Views in Android Screenlets](/develop/tutorials/-/knowledge_base/7-0/using-views-in-android-screenlets)
