# Creating Xamarin Views and Themes [](id=creating-xamarin-views-and-themes)

Recall that Views in Xamarin.Android and Themes in Xamarin.iOS are analogous 
components that let you customize a Screenlet's look and feel. You can use the 
Views and Themes provided by Liferay Screens, or write your own. Writing your 
own lets you tailor a Screenlet's UI to your exact specifications. This tutorial 
shows you how to do this. 

You can create Views and Themes from scratch, or use an existing one as a 
foundation. Views and Themes include a View class for implementing the Screenlet 
UI's behavior, a Screenlet class for notifying listeners/delegates and invoking 
Interactors, and an AXML or XIB file for defining the UI. 

There are also different types of Views and Themes. These are discussed in the 
tutorials on creating 
[Views](/develop/tutorials/-/knowledge_base/7-0/creating-android-views) 
and 
[Themes](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes) 
in native code. You should read those tutorials before creating Views in 
Xamarin.Android or Themes in Xamarin.iOS. 

First, you'll determine where to create your View or Theme. 

## Determining the Location of Your View or Theme [](id=determining-the-location-of-your-view-or-theme)

If you plan to reuse or redistribute your View or Theme, create it in a new 
Xamarin project as a 
[multiplatform library for code sharing](https://developer.xamarin.com/guides/cross-platform/application_fundamentals/nuget-multiplatform-libraries/). 
Otherwise, create it in your app's project. 

## Creating a Xamarin.Android View [](id=creating-a-xamarin-android-view)

Creating Views for Xamarin.Android is very similar to doing so in native code. 
You can create the following View types: 

-   **Themed View:** Creating a Themed View in Xamarin.Android is identical 
    [to doing so in native code](/develop/tutorials/-/knowledge_base/7-0/creating-android-views#themed-view). 
    In Xamarin.Android, however, only the Default View Set is available to 
    extend.

-   **Child View:** Creating a Child View in Xamarin.Android is identical 
    [to doing so in native code](/develop/tutorials/-/knowledge_base/7-0/creating-android-views#child-view). 

-   **Extended View:** Creating an Extended View in Xamarin.Android differs from 
    doing so in native code. The next section shows you how. 

### Extended View [](id=extended-view)

To create an Extended View in Xamarin.Android, follow 
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

Awesome! Now you know how to create Extended Views in Xamarin.Android. 

## Creating a Xamarin.iOS Theme [](id=creating-a-xamarin-ios-theme)

Creating Themes for Xamarin.iOS is very similar 
[to doing so in native code](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes). 
You can create the following Theme types in Xamarin.iOS: 

-   **Child Theme:** presents the same UI components as its parent Theme, but 
    lets you change their appearance and position. 
-   **Extended Theme:** inherits its parent Theme's functionality and 
    appearance, but lets you add to and modify both. 

First, you'll learn how to create a Child Theme in Xamarin.iOS. 

### Child Theme [](id=child-theme)

Child Themes leverage a parent Theme's behavior and UI components, letting you 
modify the appearance and position of those components. Note that you can't add 
or remove components, and the parent Theme must be a Full Theme. The Child Theme 
presents visual changes with its own XIB file and inherits the parent's View 
class. 

Follow these steps to create a Child Theme in Xamarin.iOS: 

1.  In Visual Studio, create a new XIB file named after the Screenlet's View 
    class and your Theme. By convention, an XIB file for a Screenlet with a View 
    class named `LoginView` and a Theme named `demo` should be named 
    `LoginView_demo`. You can use content from the parent Theme's XIB file as a 
    foundation for your new XIB file. In your new XIB, you can change the UI 
    components' visual properties (e.g., their position and size). You mustn't 
    change, however, the XIB file's custom class, outlet connection, or 
    `restorationIdentifier`. These must match those of the parent XIB file. 

2.  In the View Controller, set the Screenlet's `ThemeName` property to the 
    Theme's name. For example, this sets Login Screenlet's `ThemeName` property 
    to the `demo` Theme from the first step: 

        this.loginScreenlet.ThemeName = "demo";

    This causes Liferay Screens to look for the file `LoginView_demo` in all 
    apps' bundles. If that file doesn't exist, Screens uses the Default Theme 
    instead (`LoginView_default`). 

You can see an example of `LoginView_demo` in 
[the Showcase-iOS demo app](https://github.com/liferay/liferay-screens/tree/develop/xamarin/Samples/Showcase-iOS/CustomViews). 
Fantastic! Next, you'll learn how to create an Extended Theme. 

### Extended Theme [](id=extended-theme)

An Extended Theme inherits another Theme's UI components and behavior, but lets 
you add to or alter both. For example, you can extend the parent Theme's View 
class to change the parent Theme's behavior. You can also create a new XIB file 
that contains new or modified UI components. An Extended Theme's parent must be 
[a Full Theme](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes#full-theme). 

Follow these steps to create an Extended Theme: 

1.  In Visual Studio, create a new XIB file named after the Screenlet's View 
    class and your Theme. By convention, an XIB file for a Screenlet with a 
    View class named `LoginView` and a Theme named `demo` should be named 
    `LoginView_demo`. You can use the parent Theme's XIB file as a template. 
    Make your Theme's UI changes by editing your XIB file in Visual Studio's iOS 
    Designer or Xcode's Interface Builder. 

2.  Create a new View class that extends the parent Theme's View class. You 
    should name this class after the XIB file you just created. You can add to 
    or override functionality of the parent Theme's View class. Here's an 
    example that extends the View class of Login Screenlet's default Theme 
    (`LoginView_default`). Note that it changes the login button's background 
    color and disables the progress presenter: 

        using LiferayScreens;
        using System;

        namespace ShowcaseiOS
        {
            public partial class LoginView_demo : LoginView_default
            {
                public LoginView_demo (IntPtr handle) : base (handle) { }
        
                public override void OnCreated()
                {
                    // You can change the login button color from code
                    this.LoginButton.BackgroundColor = UIKit.UIColor.DarkGray;
                }
        
                // If you don't want a progress presenter, create an empty one
                public override IProgressPresenter CreateProgressPresenter()
                {
                    return new NoneProgressPresenter();
                }
            }
        }

3.  Set your new View class as the custom class for your Theme's XIB file:

    ![Figure 1: Set new View class in XIB Theme file.](../../../images/screens-ios-extended-theme.png)

Well done! Now you know how to create an Extended Theme. 

## Related Topics [](id=related-topics)

[Creating Android Views (native code)](/develop/tutorials/-/knowledge_base/7-0/creating-android-views)

[Creating iOS Themes (native code)](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes)

[Preparing Xamarin Projects for Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/preparing-xamarin-projects-for-liferay-screens)

[Using Screenlets in Xamarin Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-xamarin-apps)

[Using Views in Xamarin.Android](/develop/tutorials/-/knowledge_base/7-0/using-views-in-xamarin-android)

[Using Themes in Xamarin.iOS](/develop/tutorials/-/knowledge_base/7-0/using-themes-in-xamarin-ios)

[Liferay Screens for Xamarin Troubleshooting and FAQs](/develop/tutorials/-/knowledge_base/7-0/liferay-screens-for-xamarin-troubleshooting-and-faqs)
