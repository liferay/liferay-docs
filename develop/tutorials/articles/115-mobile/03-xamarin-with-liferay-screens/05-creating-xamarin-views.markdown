# Creating Xamarin Views and Themes [](id=creating-xamarin-views-and-themes)

Recall that Views in Xamarin.Android and Themes in Xamarin.iOS are analogous 
components that let you customize a Screenlet's look and feel. You can use the 
Views and Themes provided by Liferay Screens, or write your own. Writing your 
own lets you tailor a Screenlet's UI to your exact specifications. In this 
tutorial, you'll learn how to do this. 

You can create Views and Themes from scratch, or use an existing one as a 
foundation. Views and Themes include a View class for implementing the Screenlet 
UI's behavior, a Screenlet class for notifying listeners or delegates and 
invoking Interactors, and an AXML or XIB file for defining the UI. 

There are also different types of Views and Themes. These are discussed in the 
tutorials on creating 
[Views](/develop/tutorials/-/knowledge_base/7-0/creating-android-views) 
and 
[Themes](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes) 
in native code. You should read those tutorials before creating Views in 
Xamarin.Android, or Themes in Xamarin.iOS. 

First, you'll determine where to create your View or Theme. 

## Determining the Location of Your View or Theme [](id=determining-the-location-of-your-view-or-theme)

If you plan to use your View or Theme in only a single app, and don't intend to 
redistribute it, then create it in your app's project. If you want to reuse your 
View or Theme in another app, create it in a new Xamarin project as a 
[multiplatform library for code sharing](https://developer.xamarin.com/guides/cross-platform/application_fundamentals/nuget-multiplatform-libraries/). 

## Creating a Xamarin.Android View [](id=creating-a-xamarin-android-view)

Creating Views for Xamarin.Android is very similar to doing so in native code. 
You can create the following View types: 

-   Themed View: Creating a Themed View in Xamarin.Android is almost identical 
    [to doing so in native code](/develop/tutorials/-/knowledge_base/7-0/creating-android-views#themed-view). 
    The only difference is that only the Default View Set is available to 
    extend.
 
-   Child View: Follow the same steps for 
    [creating a Child View in native code](/develop/tutorials/-/knowledge_base/7-0/creating-android-views#child-view). 

-   Extended View: See the following section for an example. 

### Extended View [](id=extended-view)

To creating an Extended View in Xamarin.Android, follow 
[the steps for creating an Extended View in native code](/develop/tutorials/-/knowledge_base/7-0/creating-android-views#extended-view), 
but make sure your custom View class in the second step is the appropriate C# 
class. For example, here's the View class from the native code tutorial, 
converted to C#: 

    using System;
    using Android.Content;
    using Android.Util;
    using Com.Liferay.Mobile.Screens.Viewsets.Defaultviews.Auth.Login;

    namespace ShowcaseAndroid.CustomViews
    {
        public class LoginCheckPasswordView : LoginView
        {
            public LoginCheckPasswordView(Context context) : base(context) { }

            public LoginCheckPasswordView(Context context, IAttributeSet attributes) : base(context, attributes) {}

            public LoginCheckPasswordView(Context context, IAttributeSet attributes, int defaultStyle) : base(context, attributes, defaultStyle) {}

            public override void OnClick(Android.Views.View view)
            {
                // compute password strength
                if (PasswordIsStrong) {
                    base.OnClick(view);
                }
                else {
                    // Present user message
                }
            }
        }
    }

Awesome! Now you know how to create Extended Views.

## Creating a Xamarin.iOS Theme [](id=creating-a-xamarin-ios-theme)

Creating Themes for Xamarin.iOS is very similar to doing so in native code. You 
can create the following Theme types: 

-   Child Theme
-   Extended Theme

For a description of these Theme types, see 
[the tutorial on creating Themes in native code](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes). 

First, you'll learn how to create a Child Theme in Xamarin.iOS. 

### Child Theme [](id=child-theme)

In a Child Theme, you leverage a parent Theme's behavior and UI components, but 
you can modify the appearance and position of the UI components. Note that you 
can't add or remove any components, and the parent Theme must be a Full Theme. 
The Child Theme presents visual changes with its own XIB file and inherits the 
parent's View class.

1.  In Visual Studio, create a new XIB file that's named after the Screenlet's 
    View class and your Theme. By convention, an XIB file for a Screenlet with a 
    View class named `LoginView` and a Theme named `demo` must be named 
    `LoginView_demo`. You can use content from the parent Theme's XIB file as a 
    foundation for your new XIB file. In your new XIB, you can change the UI 
    components' visual properties (e.g., their position and size). You mustn't 
    change, however, the XIB file's custom class, outlet connection, or 
    `restorationIdentifier`. These must match those of the parent's XIB file.

2.  In the View Controller, set the Screenlet's `ThemeName` property to Theme. 
    For example:

        this.loginScreenlet.ThemeName = "demo";

    This causes Liferay Screens to look for the file `LoginView_demo` in all 
    apps' bundles. If that file doesn't exist, it uses the Default Theme instead 
    (`LoginView_default`). 

You can see an example of `LoginView_demo` in 
[the Showcase-iOS demo app](https://github.com/liferay/liferay-screens/tree/develop/xamarin/Samples/Showcase-iOS/CustomViews). 
Fantastic! Next, you'll learn how to create an Extended Theme.

### Extended Theme [](id=extended-theme)

An Extended Theme inherits another Theme's UI components and behavior, but lets 
you add to or alter it by extending the parent Theme's View class and creating a 
new XIB file. An Extended Theme's parent must be a Full Theme. 

These steps explain how to create an Extended Theme:

1.  In Visual Studio, create a new XIB file that's named after the Screenlet's 
    View class and your Theme. By convention, an XIB file for a Screenlet with a 
    View class named `LoginView` and a Theme named `demo` must be named 
    `LoginView_demo`. You can use the XIB file of your parent Theme as a 
    template. Build your UI changes in your new XIB file with iOS Designer in 
    Visual Studio or Xcode's Interface Builder.

2.  Create a new View class that extends the parent Theme's View class. You 
    should name this class after the XIB file you just created. You can add or 
    override functionality of the parent Theme's View class.

        using LiferayScreens;
        using System;

        namespace ShowcaseiOS
        {
            public partial class LoginView_demo : LoginView_default
            {
                public LoginView_demo (IntPtr handle) : base (handle) { }
        
                public override void OnCreated()
                {
                    //You can change the login button color from code
                    //this.LoginButton.BackgroundColor = UIKit.UIColor.DarkGray;
                }
        
                //If we don't wont progress presenter, we have to create an empty one
                public override IProgressPresenter CreateProgressPresenter()
                {
                    return new NoneProgressPresenter();
                }
            }
        }

3.  Set your new View class as the custom class for your Theme's XIB file:

    ![Figure 1: Set new View class in XIB Theme file.](../../../images/screens-ios-extended-theme.png)

Well done! Now you know how to create and use an Extended Theme.

## Related Topics [](id=related-topics)

[Creating Android Views (native code)](/develop/tutorials/-/knowledge_base/7-0/creating-android-views)

[Creating iOS Themes (native code)](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes)

[Preparing Xamarin Projects for Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/preparing-xamarin-projects-for-liferay-screens)

[Using Screenlets in Xamarin Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-xamarin-apps)

[Using Views in Xamarin.Android](/develop/tutorials/-/knowledge_base/7-0/using-views-in-xamarin-android)

[Using Themes in Xamarin.iOS](/develop/tutorials/-/knowledge_base/7-0/using-themes-in-xamarin-ios)

[Liferay Screens for Xamarin Troubleshooting and FAQs](/develop/tutorials/-/knowledge_base/7-0/liferay-screens-for-xamarin-troubleshooting-and-faqs)
