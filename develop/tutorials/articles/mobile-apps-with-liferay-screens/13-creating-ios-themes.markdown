# Creating iOS Themes

By letting you create your own themes, Screens for iOS gives you nearly 
limitless ways to customize your app's look and feel. Themes can also introduce 
new functionality to screenlets. You can create themes from scratch, or use an 
existing theme as a foundation for your themes. All themes consist of a view 
class for screenlet behavior and an XIB file for the UI. Which of these 
components you need to create, however, depends on the *type* of theme you're 
creating. The type of theme you create depends on your needs. There are three 
types of themes you can create: 

- *Child theme*: Presents the same UI components as its parent theme, but lets 
  you change their appearance and position. Since the parent theme's behavior is 
  preserved, creating a Child theme is ideal when you only need to make visual 
  changes to the parent. Child themes reuse the parent's view class, but use a 
  new XIB. The Child theme's parent must be a Full theme. 

- *Extended theme*: Inherits the parent theme's behavior and appearance, but 
  lets you change and add to both. Creating an Extended theme is ideal when you 
  need to modify or add to an existing theme's behavior. Extended themes 
  subclass the parent theme's view class, and use a new XIB. An Extended theme's 
  parent must be a Full theme. 

- *Full theme*: A theme with no parent. Creating a Full theme is ideal when you 
  need a screenlet to have completely different behavior and appearance. Since a 
  Full theme doesn't have a parent theme, its view class must inherit Screens's 
  `BaseScreenletView` and conform to the screenlet's view model protocol. A Full 
  theme must also include an XIB file that specifies its UI. Any Full theme can 
  be inherited by a Child or Extended theme.

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

A Child theme presents the same behavior and UI components as another theme (its 
parent theme). You can can only change the UI components' appearance and 
position. The changes in a Child theme are therefore only visual. Due to this, 
you only need to create a new XIB file when creating a Child theme; the 
parent's view class is inherited. A Child theme's parent must be a Full theme. 
For example, the Child theme discussed here presents the same UI components as 
the Default theme for `LoginScreenlet`, but increases their size for use in 
larger screen sizes. The following steps show you how to create a Child theme. 

1.  Create a new XIB file and use Interface Builder to build your new UI in it. 
    However, you must use only the parent theme's UI components. You can paste 
    in the contents from 
    [the Default theme's XIB](https://github.com/liferay/liferay-screens/blob/1.0.0/ios/Framework/Themes/Default/Auth/LoginScreenlet/LoginView_default.xib) 
    to use as a template. Also, you should name your new XIB file after the 
    screenlet's view class and your theme's intended use case. The intended use 
    case in the XIB file name then serves as the theme's Xcode name. For 
    example, the theme in this example uses the `LoginScreenlet`'s Default 
    theme, which uses the view class `LoginView_default`. Since the new theme is 
    meant for use with larger screen sizes, its new XIB file is called 
    `LoginView_large.xib`. The use case `large` is this theme's Xcode name. 

    ![Figure 1: The UI components are enlarged in the the example Child theme's XIB file.](../../images/screens-ios-xcode-child-theme.png)

    In the XIB, you can change the position, size, or other visual properties 
    of the parent's UI components. However, you can't change the XIB file's 
    custom class (`LoginView` in this case), outlet connection, or 
    `restorationIdentifier`--these must match the parent XIB. 

2.  If you packaged your theme with CocoaPods, you must install the theme in
    your project before you can use it. Do so by adding the following line in 
    your Podfile: 

        pod 'LiferayScreens-YourThemeName'

    In the line above, make sure to replace `YourThemeName` with your theme's
    name as packaged in CocoaPods. Note that this may not match the theme's 
    Xcode name.

    To use the installed theme, insert its screenlet in any of your view 
    controllers. Then use your theme's Xcode name as the value for the 
    *Base Screenlet*'s *Theme Name* property in Interface Builder. 
    Alternatively, you can use the theme's Xcode name as the value for the 
    `themeName` property in your XIB file's view controller. In this example, 
    the theme's Xcode name is `large`. Your new UI then appears in Interface 
    Builder. 

Fantastic! You've created a Child theme and integrated it with your screenlet.
Next, you'll learn how to create an Extended theme. 

## Extended Theme [](id=extended-theme)

An Extended theme inherits another theme's UI components and behavior, letting 
you add to or alter both. Extended themes are therefore capable of augmenting a 
Screenlet's functionality in addition to making visual changes. This is done by 
subclassing the parent theme's view class and creating a new XIB. An Extended 
theme's parent must be a Full theme. See the 
[Flat7 theme](https://github.com/liferay/liferay-screens/tree/1.0.0/ios/Framework/Themes/Flat7) 
for an example of an Extended theme. As an example of creating an Extended 
theme, the Extended theme here for `LoginScreenlet` presents the same UI 
components as the Default theme (a Full theme), but sets new translated strings 
and contains a few new animations. A new UI component is also introduced to 
configure whether the password is shown when typed by the user. 

The following steps explain how to create an Extended theme.

1.  Create a new XIB file and use Interface Builder to build your new UI in it.
    You can use the XIB file of your parent theme as a template. Also, you 
    should name your new XIB file after the screenlet's view class and your 
    theme's intended use case. The intended use case in the XIB file name then 
    serves as the theme's Xcode name. For example, the theme in this example 
    uses the Login screenlet's Default theme, which uses the view class 
    `LoginView_default`. Since the new theme is an Extended theme, its new XIB 
    file is called `LoginView_ext.xib`. Therefore, `ext` is the theme's Xcode 
    name. The new XIB also contains a new `UISwitch` component to configure the 
    password presentation. 

    ![Figure 2: The example Extended theme's new XIB file has a switch next to the Password field.](../../images/screens-ios-xcode-ext-theme.png)

2.  Create a new view class for your theme, based on its parent class. You 
    should name this class after the XIB file you just created. This class is 
    where you should add or override functionality. As an example, the view 
    class for the `LoginView_ext` XIB from the first step is called 
    `LoginView_ext`, and uses `LoginView_default` as the parent class. The new 
    translations can be implemented by overriding the `onSetTranslations` 
    method. Also, the `IBSwitch` component can be wired up with `@IBOutlet` or 
    `@IBAction`. 

3.  Set your new view class as the custom class for your theme's XIB file. If 
    you added `@IBOutlet` or `@IBAction`, bind them to your class. In this 
    example, `LoginView_ext` is set as the `LoginView_ext.xib` file's custom 
    class. The `@IBOutlet` and `@IBAction` are also bound to the class. 

4.  If you packaged your theme with CocoaPods, you must install the theme in
    your project before you can use it. Do so by adding the following line in 
    your Podfile: 

        pod 'LiferayScreens-YourThemeName'

    In the line above, make sure to replace `YourThemeName` with your theme's
    name as packaged in CocoaPods. Note that this may not match the theme's 
    Xcode name. 

    To use the installed theme, insert its screenlet in any of your view 
    controllers. Then use your theme's Xcode name as the value for the 
    *Base Screenlet*'s *Theme Name* property in Interface Builder. 
    Alternatively, you can use the theme's Xcode name as the value for the 
    `themeName` property in your XIB file's view controller. In this example, 
    the theme's Xcode name is `ext`. Your new UI then appears in Interface 
    Builder. 

Well done! Now you know how to create an Extended theme. In the next section, 
you'll learn how to create a Full theme.

## Full Theme [](id=full-theme)

A Full theme has no parent theme and implements unique behavior and appearance 
for a screenlet. Its view class must inherit Screens's `BaseScreenletView` and 
conform to the screenlet's view model protocol. It must also specify a new UI 
in an XIB file. Full themes can also serve as the parent for Child and Extended 
themes. The example here creates a Full theme for `LoginScreenlet` that presents 
a single `UITextField` for the user name. It uses the 
[UDID](http://www.idownloadblog.com/2010/12/21/iphone-udid/) 
for the password. 

1.  Create a new XIB file and use Interface Builder to build your new UI in it. 
    You can use the XIB file from your theme's screenlet as a template. Also, 
    you should name your new XIB file after the screenlet's view class and your 
    theme's intended use case. The intended use case in the XIB file name then 
    serves as the theme's Xcode name. For example, the theme in this example 
    uses `LoginScreenlet`, which uses the view class `LoginView_default`. Since 
    the new theme is a Full theme, its XIB file is called `LoginView_full.xib`. 
    Therefore, `full` is the theme's Xcode name. Also, this example theme 
    includes a text field for the user name, and a *Sign In* button with the 
    same `restorationIdentifier` as the Default theme. 

    ![Figure 3: The new XIB file for this Full theme is very simple.](../../images/screens-ios-xcode-full-theme.png)

2.  Create a new view class for your theme, named after the XIB file you just 
    created. You can use the view class of your screenlet's Default theme 
    as a template. Your new view class must inherit `BaseScreenletView` and 
    conform to the screenlet's `*ScreenletViewModel` protocol, implementing the 
    corresponding getters and setters. You should also add all the `@IBOutlet` 
    properties or `@IBAction` methods you need to bind your UI components. 
    
    For example, the view class for the Full theme here is called 
    `LoginView_full`. It inherits `BaseScreenletView` and conforms the 
    `LoginViewModel` protocol. Its `password` getter returns the UDID. 

3.  Set your theme's new view class as your XIB file's custom class. Also, bind 
    the `@IBOutlet` and `@IBAction` to your class. In the example Full theme 
    here, `LoginView_full` is set as the `LoginView_full.xib` file's custom 
    class. The `@IBOutlet` and `@IBAction` is also bound to this class. 

4.  If you packaged your theme with CocoaPods, you must install the theme in
    your project before you can use it. Do so by adding the following line in 
    your Podfile: 

        pod 'LiferayScreens-YourThemeName'

    In the line above, make sure to replace `YourThemeName` with your theme's
    name as packaged in CocoaPods. Note that this may not match the theme's 
    Xcode name. 

    To use the installed theme, insert its screenlet in any of your view 
    controllers. Then use your theme's Xcode name as the value for the 
    *Base Screenlet*'s *Theme Name* property in Interface Builder. 
    Alternatively, you can use the theme's Xcode name as the value for the 
    `themeName` property in your XIB file's view controller. In this example, 
    the theme's Xcode name is `full`. Your new UI then appears in Interface 
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

1.  Create an empty *Cocoa Touch Framework* Xcode project.

    ![Figure 4: Choose *Cocoa Touch Framework* when creating a project for your theme.](../../images/screens-ios-cocoa-touch-framework.png)
    
2.  Name your project `LiferayScreensThemeName`. Change `Name` to your theme's 
    name, which becomes your theme's CocoaPods name. While you can use any name 
    you want, it's recommended that you use your theme's Xcode name. 

3.  Configure Liferay Screens for CocoaPods, using the steps described in 
    [Preparing iOS Projects for Liferay Screens](/develop/tutorials/-/knowledge_base/6-2/preparing-ios-projects-for-liferay-screens).

4.  Create your theme's classes and resources according to the instructions in 
    the above sections. Your classes must compile successfully in Xcode. Use 
    caution if you create your own XIB files from scratch; the custom class must 
    use your theme's module name. For example, the Custom Class setting in this 
    screenshot is incorrect. This is because the XIB file is bound to the custom 
    class name without specifying the module: 

    ![Figure 5: The XIB file is bound to the custom class name without specifying the module.](../../images/screens-ios-theme-custom-module-wrong.png)

    In the following screenshot, the setting for the custom class is correct:

    ![Figure 6: The XIB file is bound to the custom class name specifying the module.](../../images/screens-ios-theme-custom-module-right.png)

5.  In your project's root folder, add a file named 
    `LiferayScreensTheme-Name.podspec` (change `Name` to your theme's CocoaPods 
    name). It's very important that your project's name starts with 
    `LiferayScreens`. Add the following content to the file: 

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
your theme's CocoaPods name, so that it matches the name in step two above): 

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
