# Creating iOS Themes

By letting you create your own themes, Screens for iOS gives you nearly 
limitless ways to customize your app's look and feel. You can create your themes 
from scratch, or use the themes included with Screens as a foundation. There are 
three types of themes you can create:

- Child theme
- Extended theme
- Full theme

This tutorial explains how to create all three types of themes. Before creating
your first theme, you might want to learn the
[architecture](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-ios)
of Liferay Screens for iOS, to understand theme concepts and components. It
might also be helpful to read the tutorial
[Creating iOS Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-ios-screenlets). 
Now get ready to create some themes! 

## Determining Your Theme's Location

After determining the type of theme to create, you need to determine where to
create it. If you want to reuse or redistribute it, you should create it in an
empty Cocoa Touch Framework project in Xcode. The 
[publishing](/develop/tutorials/-/knowledge_base/6-2/creating-ios-themes#publish-your-themes-using-cocoapods)
section later in this tutorial explains how to publish with CocoaPods. If you're
not planning to reuse or redistribute your theme, you can create it directly
inside your app project. 

First, you'll learn how to create a Child theme.

## Child Theme [](id=child-theme)

A Child theme inherits the look and feel of its parent and allows you to
customize it. The example theme here uses the Login screenlet to present the
Default theme's components using a larger resolution. 

1.  Create a new XIB file and use Interface Builder to build your new UI in it. 
    Keep in mind, however, that you need to keep the same UI components as the
    parent theme. You can paste in the contents from the Default theme's XIB as
    a foundation. Also, you should name your new XIB file after the screenlet's
    view class, with an additional modifier that indicates some information
    about the theme. For example, the theme in this example uses the Login
    screenlet's default theme, which uses the view class `LoginView_default`.
    Since the new theme is meant for larger screen sizes, its new XIB file is
    called `LoginView_large.xib`. 

    ![Figure 2: Here's the starting XIB file for the example Child theme for the Login screenlet.](../../images/screens-ios-xcode-child-theme.png)

2.  In the XIB file, you can change the position, size, or other properties of
    the parent components, but don't change its custom class (`LoginView` in
    this case), outlet connection, or `restorationIdentifier`--the parent
    theme's UI components must remain untouched. 

3.  If you packaged your theme with CocoaPods, you must install the theme in
    your project before you can use it, by adding the following line in your
    Podfile: 

        pod 'LiferayScreens-YourThemeName'

    Make sure to replace `YourThemeName`, in the line above, with your theme's
    name. 

    <!-- TODO - replace the description below with the standard iOS theme file
    naming convention description. - Jim
    -->

    To use the installed theme, specify the modifier that you used in your XIB
    file's name, as the *Theme Name* property field of the *Base Screenlet* in
    Interface Builder or as the value for the `themeName` property for the view
    controller in your XIB file. The modifier value for XIB file
    `LoginView_large.xib`, for example, is `large`. 

    Your new UI appears in Interface Builder. 

Fantastic! You've created a Child theme and integrated it with your screenlet.
Next, you'll learn how to create an Extended theme. 

## Extended Theme [](id=extended-theme)

An Extended theme inherits the functionality and UI of its parent, but lets you
add your own functionality and UI on top of it. For an example of an Extended 
theme, refer to the 
[Flat7 theme](https://github.com/liferay/liferay-screens/tree/1.0.0/ios/Framework/Themes/Flat7).
As an example, the Extended theme here for the Login screenlet presents the same 
components as the Default theme, but sets new translated strings and contains a 
few new animations. A new UI component is also introduced to configure whether 
the password is shown when typed by the user. 

The following steps explain how to create an Extended theme.

1. Create a new XIB file and use Interface Builder to build your new UI in it.
   You can use the XIB file of your parent theme as a template. Also, you 
   should name your new XIB file after the screenlet's view class, with an 
   additional modifier that indicates some information about the theme. For 
   example, the theme in this example uses the Login screenlet's default theme, 
   which uses the view class `LoginView_default`. Since the new theme is an 
   Extended theme, its new XIB file is called `LoginView_ext.xib`. It also 
   contains a new `UISwitch` component to configure the password presentation. 

    ![Figure 3: The new XIB file for the example Extended theme.](../../images/screens-ios-xcode-ext-theme.png)

2. Create a new view class for your theme, based on its parent class. You should 
   name this class after the XIB file you just created. This class is where you 
   should add or override functionality. As an example, the view class for the 
   `LoginView_ext` XIB from the first step is called `LoginView_ext`, and uses 
   `LoginView_default` as the parent class. The new translations can be 
   implemented by overriding the `onSetTranslations` method. Also, the `IBSwitch` 
   component can be wired up with `@IBOutlet` or `@IBAction`. 

3. Set your new view class as the custom class for your theme's XIB file. If you 
   added `@IBOutlet` or `@IBAction`, bind them to your class. In this example, 
   `LoginView_ext` is set as the `LoginView_ext.xib` file's custom class. The 
   `@IBOutlet` and `@IBAction` are also bound to the class. 

4. If you packaged your theme with CocoaPods, you must install it in your 
   project before you can use it. To use your theme, insert its screenlet in any 
   of your view controllers. Use the additional modifier from your XIB file's 
   name as the value of the screenlet's `themeName` property. For example, for 
   `LoginView_ext.xib` this is `ext`. Your new UI then appears in Interface 
   Builder. 

Well done! Now you know how to create an Extended theme. In the next section, 
you'll learn how to create a Full theme.

## Full Theme [](id=full-theme)

A Full theme can present a completely different layout with different UI 
components and input data. The example here creates a Full theme for the Login
screenlet that presents a single `UITextField` for the user name. It uses 
the [UDID](http://www.idownloadblog.com/2010/12/21/iphone-udid/) for the 
password. 

1. Create a new XIB file and use Interface Builder to build your new UI in it. 
   You can use the XIB file from your theme's screenlet as a template. You 
   should name your XIB file after the screenlet's view class, with an 
   additional modifier that indicates some information about the theme. For 
   example, the theme in this example uses the Login screenlet's default theme, 
   which uses the view class `LoginView_default`. Since the new theme is a Full 
   theme, its XIB file is called `LoginView_full.xib`. Also, this example theme 
   includes a text field for the user name, and a *Sign In* button with the same 
   `restorationIdentifier` as the default theme. 

    ![Figure 1: The new XIB file for a Full theme.](../../images/screens-ios-xcode-full-theme.png)

2. Create a new view class for your theme, named after the XIB file you just 
   created. You can use the view class of your screenlet's default theme 
   as a template. Your new view class must inherit `BaseScreenletView` and 
   conform to the screenlet's `*ScreenletViewModel` protocol, implementing the 
   corresponding getters and setters. You should also add all the `@IBOutlet` 
   properties or `@IBAction` methods you need to bind your UI components. 
   
    For example, the view class for the Login screenlet is called 
    `LoginView_full`. It inherits `BaseScreenletView` and conforms the 
    `LoginViewModel` protocol. Its `password` getter returns the UDID. 

3. Set your theme's new view class as your XIB file's custom class. Also, bind 
   the `@IBOutlet` and `@IBAction` to your class. In the Login screenlet, 
   `LoginView_full` is set as the `LoginView_full.xib` file's 
   custom class. The `@IBOutlet` and `@IBAction` is also bound to this class. 

4. If you packaged your theme with CocoaPods, you must install it in your 
   project before you can use it. To use your theme, insert its screenlet in any 
   of your view controllers. Use the additional modifier from your XIB file's 
   name as the value of the screenlet's `themeName` property. For example, for 
   `LoginView_full.xib` this is `full`. Your new UI then appears in Interface 
   Builder. 

Super! Now you know how to create a Full theme. In the next section, you'll 
learn how to package and distribute your themes by using CocoaPods. 

## Publish Your Themes Using CocoaPods [](id=publish-your-themes-using-cocoapods)

Since your theme is a code library, you can package it by using CocoaPods. Doing 
so means that other developers can install your theme by adding the following 
line in their `Podfile`: 

    pod 'LiferayScreens-YourThemeName'

Use the following steps to package your theme for use with CocoaPods. It's 
important that you use the same names and identifiers described in these steps:

1. Create an empty *Cocoa Touch Framework* Xcode project.

    ![Figure 4: Choose *Cocoa Touch Framework* when creating a project for your theme.](../../images/screens-ios-cocoa-touch-framework.png)
    
2. Name your project `LiferayScreensThemeName` (change `Name` to your theme's 
   name).

3. Configure Liferay Screens for CocoaPods, using the steps described in 
   [Preparing iOS Projects for Liferay Screens](/develop/tutorials/-/knowledge_base/6-2/preparing-ios-projects-for-liferay-screens).

4. Create your theme's classes and resources according to the instructions in 
   the above sections. Your classes must compile successfully in Xcode. Use 
   caution if you create your own XIB files from scratch; the custom class must 
   use your theme's module name. For example, the Custom Class setting in this 
   screenshot is incorrect. This is because the XIB file is bound to the custom 
   class name without specifying the module: 

    ![Figure 5: The XIB file is bound to the custom class name without specifying the module.](../../images/screens-ios-theme-custom-module-wrong.png)

    In the following screenshot, the setting for the custom class is correct:

    ![Xib file binded to custom class name specifying the module.](../../images/screens-ios-theme-custom-module-right.png)

5. In your project's root folder, add a file named 
   `LiferayScreensTheme-Name.podspec` (change `Name` to your theme's name). It's 
   very important that your project's name starts with `LiferayScreens`. Add the 
   following content to the file:

        Pod::Spec.new do |s|
            s.name = 'LiferayScreensThemeName'
            s.version = '1.0'
            s.summary = 'Your theme description'
            s.source = {
                :git => 'https://your_repository_url.git',
                :tag => 'v1.0'
            }
        
            s.platform = :ios, '8.0'
            s.requires_arc = true
        
            s.source_files = 'Your/Relative/Folder/**/*.{h,m,swift}'
            s.resources = 'Your/Relative/Folder/**/*.{xib,png,plist,lproj}'
        
            s.dependency 'LiferayScreens'
        end

You can commit and push your project to your Git repository and then use the 
theme by adding the following line in your app's `Podfile` (change `Name` to 
your theme's name): 

    pod 'LiferayScreensThemeName', :git => 'https://your_repository_url.git'

If you want to simplify the process, you can publish your theme as a 
public Pod. For instructions on this, see the chapter *Deploying a library* in 
the [official CocoaPods guide](https://guides.cocoapods.org/making/getting-setup-with-trunk.html#deploying-a-library). 

Nice work! Now you know how to create themes in Liferay Screens for iOS. You 
even know how to package and distribute them with CocoaPods. You're the master 
of the themes! 

## Related Topics [](id=related-topics)

[Using Themes in iOS Screenlets](/develop/tutorials/-/knowledge_base/6-2/using-themes-in-ios-screenlets)

[Architecture of Liferay Screens for iOS](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-ios)

[Creating iOS Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-ios-screenlets)
