# Using Views in Xamarin.iOS

+$$$

**Note:** This documentation is currently in beta. 

$$$

Using a Liferay Screens *Theme*, you can set your Screenlet's UI components,
style, and behavior. They let you focus on a Screenlet's UI and UX, without
having to worry about its core functionality. Liferay's Screenlets come with
several Themes, and more are being developed by Liferay and the community. 
[The Screenlet reference documentation for Android](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-android) and
[the Screenlet reference documentation for iOS](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-iOS) 
lists the Views/Themes available for each Screenlet included with Screens.
This tutorial shows you how to use Themes in Xamarin.iOS.

To install a Theme in your iOS app's Screenlet, you have two options:

1.  If the Theme has been packaged as a NuGet dependency, you can install 
    it directly in your project:

        Packages > Add packages... > LiferayScreensThemeName

    Make sure to replace `LiferayScreensThemeName` with the Theme's
    NuGet package name.

2. If the Theme isn't available through NuGet, you can drag and drop the 
   Theme's folder into your project.

To use the installed Theme, specify its name in the *Theme Name* property field
of the *Base Screenlet*. The names of each Screenlet's
Themes are listed in the *Themes* section of the Screenlet's
[reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-ios). 
If you leave the Theme name property blank or enter a name for a Theme that
can't be found, the Screenlet's Default Theme is used. 

    loginScreenlet.ThemeName = "material"

That's all there is to it! Great! Now you know how to use Themes to dress up 
Screenlets in your iOS app. This opens up a world of possibilities--like 
[writing your own Themes](/develop/tutorials/-/knowledge_base/7-0/creating-xamarin-views-for-the-screenlets).

## Related Topics


