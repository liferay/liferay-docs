# Using Themes in Xamarin.iOS [](id=using-themes-in-xamarin-ios)

+$$$

**Note:** This documentation is currently in beta. 

$$$

Using a Liferay Screens *Theme*, you can set your Screenlet's UI components, 
style, and behavior. This lets you focus on a Screenlet's UI and UX, without 
worrying about the Screenlet's core functionality. Liferay's Screenlets come 
with several Themes, and more are being developed by Liferay and the community. 
[The Screenlet reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-ios) 
lists the Themes available for each Screenlet included with Screens. This 
tutorial shows you how to use Themes in Xamarin.iOS. 

## Installing and Using Themes [](id=installing-and-using-themes)

Follow these steps to install and use a Theme: 

1.  If the Theme has been packaged as a NuGet dependency, you can install it 
    directly in your project. To do so, right-click your project's *Packages* 
    folder and then select *Add packages...*. Then search for the Theme and 
    install it. If the Theme isn't available in NuGet, you can drag and drop the 
    Theme's folder directly into your project. 

2.  To use the installed Theme, set its name to the Screenlet instance's 
    `ThemeName` property, in your view controller that implements the 
    Screenlet's delegate. All Screenlets inherit this property from 
    `BaseScreenlet`. For example, this code sets Login Screenlet's `ThemeName` 
    property to the Material Theme: 

        loginScreenlet.ThemeName = "material"

    If you don't set this property or enter an invalid or missing Theme, the 
    Screenlet's Default Theme is used. Each Screenlet's available Themes are 
    listed in the *Themes* section of the Screenlet's 
    [reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-ios). 

Great, that's it! Now you know how to use Themes to dress up Screenlets in your 
Xamarin.iOS apps. 

## Related Topics [](id=related-topics)

[Preparing Xamarin Projects for Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/preparing-xamarin-projects-for-liferay-screens)

[Using Screenlets in Xamarin Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-xamarin-apps)

[Using Views in Xamarin.Android](/develop/tutorials/-/knowledge_base/7-0/using-views-in-xamarin-android)

[Liferay Screens for Xamarin Troubleshooting and FAQs](/develop/tutorials/-/knowledge_base/7-0/liferay-screens-for-xamarin-troubleshooting-and-faqs)
