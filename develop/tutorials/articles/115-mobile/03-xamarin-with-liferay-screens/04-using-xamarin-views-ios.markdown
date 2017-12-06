# Using Themes in Xamarin.iOS [](id=using-themes-in-xamarin-ios)

+$$$

**Note:** This documentation is currently in beta. 

$$$

Using a Liferay Screens *Theme*, you can set your Screenlet's UI components,
style, and behavior. Themes let you focus on a Screenlet's UI and UX, without
having to worry about its core functionality. Liferay's Screenlets come with
several Themes, and more are being developed by Liferay and the community. 
[The Screenlet reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-ios) 
lists the Themes available for each Screenlet included with Screens. This 
tutorial shows you how to use Themes in Xamarin.iOS. 

There are two ways to install a Theme: 

1.  If the Theme has been packaged as a NuGet dependency, you can install it 
    directly in your project via *Packages* &rarr; *Add packages...* &rarr; 
    *LiferayScreensThemeName* (make sure to replace *LiferayScreensThemeName* 
    with the Theme's NuGet package name). 

2.  If the Theme isn't available through NuGet, you can drag and drop the 
    Theme's folder into your project. 

To use the installed Theme, specify its name in the *Theme Name* property field 
of the *Base Screenlet*. Each Screenlet's available Themes are listed in the 
*Themes* section of the Screenlet's 
[reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-ios). 
If you leave the Theme Name property blank or enter an invalid or missing theme, 
the Screenlet's Default Theme is used. 

    loginScreenlet.ThemeName = "material"

That's all there is to it! Great! Now you know how to use Themes to dress up 
Screenlets in your iOS app. 

## Related Topics [](id=related-topics)

[Preparing Xamarin Projects for Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/preparing-xamarin-projects-for-liferay-screens)

[Using Screenlets in Xamarin Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-xamarin-apps)

[Using Views in Xamarin.Android](/develop/tutorials/-/knowledge_base/7-0/using-views-in-xamarin-android)

[Liferay Screens for Xamarin Troubleshooting and FAQs](/develop/tutorials/-/knowledge_base/7-0/liferay-screens-for-xamarin-troubleshooting-and-faqs)
