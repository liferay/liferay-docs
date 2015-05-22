# Creating Themes in Liferay Screens for iOS [](id=creating-themes-in-liferay-screens-for-ios)

Liferay Screens is highly customizable. In addition to letting you create your 
own screenlets, Screens for iOS lets you create your own themes. This gives you 
nearly limitless options for customizing your app's look and feel. Screens for 
iOS includes several themes that you can use to build your own. You can also 
write your theme completely from scratch. 

This tutorial shows you how to create themes in Liferay Screens for iOS. Before 
proceeding, you may want to read the tutorial 
[Architecture of Liferay Screens for iOS](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-ios) 
to understand the concepts underlying themes. It may also be useful to read the 
tutorial [Creating Screenlets in Liferay Screens for iOS](/tutorials/-/knowledge_base/6-2/creating-screenlets-in-liferay-screens-for-ios). 
Now get ready to create some themes! 

## Theme Types [](id=theme-types)

The first step in creating a new theme is deciding what kind of theme to create. 
There are three theme types: 

- Full theme
- Child theme
- Extended theme

For a detailed explanation of these theme types, see the 
[section on themes in the Screens for iOS architecture tutorial](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-ios#the-theme-layer-of-screens-for-ios).
You can create your theme's classes and resources directly inside your app 
project. If you want to use the theme in more than one app, follow the 
instructions at the end of this tutorial to publish your theme as a CocoaPods 
library. 

The following sections show you how to create each type of theme, using 
`LoginScreenlet` as an example. You'll begin by creating a new Full theme.

## Full Theme [](id=full-theme)

A Full theme can present a completely different layout, using different UI 
components and input data. The example here presents a single `UITextField` 
for the user name. The [UDID](http://www.idownloadblog.com/2010/12/21/iphone-udid/) 
is used for the password. 

1. Create a new `xib` file. Since the theme in this example is for 
   `LoginScreenlet`, its `xib` file is called `LoginScreenlet_full.xib`. This 
   file is where you'll use Interface Builder to build your new UI. A good way 
   to start is to duplicate the `xib` file for the screenlet your theme applies 
   to and then use it as a template. This example uses 
   `LoginScreenlet_default.xib` as a template. Next, insert your UI components 
   in your `xib` file. For this example, insert the text field for the user name 
   and add a *Sign In* button with the same `restorationIdentifier` as the 
   Default theme's button. 

    ![Figure 1: The new `xib` file for full theme.](../../images/screens-ios-xcode-full-theme.png)

2. Create a new view class for your theme. The view class in this example is 
   called `LoginScreenletView_full`. As with the `xib` file, you can duplicate 
   the `LoginScreenletView_default` class and use it as a template. In this 
   class, add all the `@IBOutlet` properties or `@IBAction` methods you need to 
   bind your UI components. This class must inherit the `BaseScreenletView` 
   class and conform to the `LoginScreenletData` protocol, implementing the 
   corresponding getters and setters. In this example, the `password` getter 
   returns the UDID. 

3. Set your theme's new view class as your `xib` file's custom class. Also, bind 
   the `@IBOutlet` and `@IBAction` to your class. In the example here, 
   `LoginScreenletView_full` is set as the `LoginScreenlet_full.xib` file's 
   custom class. The `@IBOutlet` and `@IBAction` is also bound to this class. 

4. Install the new theme and insert the screenlet it applies to in any of your 
   view controllers. Use `full` as the `themeName` property's value. Your new 
   look and feel is then shown in Interface Builder. This is done for 
   `LoginScreenlet` in this example. 

Super! Now that you know how to create a Full theme, you'll learn to create a 
Child theme.

## Child Theme [](id=child-theme)

A Child theme inherits only the look and feel of its parent. It doesn't inherit 
any code. The example theme here presents the same components as the Default 
theme, but is adjusted to a larger resolution. This makes it appropriate for 
iOS devices such as the iPad, iPhone 6, and iPhone 6 Plus. 

1. Create a new `xib` file. Since the theme in this example uses 
   `LoginScreenlet`, the new `xib` file is called `LoginScreenlet_large.xib`. 
   This file is where you'll use Interface Builder to build your new UI. A good 
   way to start is to duplicate the `xib` file for the screenlet your theme 
   applies to and then use it as a template. This example uses 
   `LoginScreenlet_default.xib` as a template. 

    ![Figure 2: The new `xib` file for the Child theme.](../../images/screens-ios-xcode-child-theme.png)

2. Change the position, size, or other properties of the parent components. 
   However, be sure not to change the custom class, outlet connection, or 
   `restorationIdentifier`. 

3. Install your new theme, insert the `LoginScreenlet` in any of your view 
   controllers, and use `large` as the value of the screenlet's `themeName` 
   property. Your new look and feel is shown in Interface Builder. 

Fantastic! Last but certainly not least, you'll learn how to create an Extended 
theme. 

## Extended Theme [](id=extended-theme)

An Extended theme inherits the functionality and UI of its parent. This lets you
add your own functionality and UI on top of it. For an example of an Extended 
theme, see the [Flat7 theme source code](https://github.com/liferay/liferay-screens/tree/master/ios/Framework/Themes/Flat7). 
The Extended theme here presents the same components as the Default theme, but 
sets new translated strings and contains a few new animations. A new UI 
component is also introduced to configure whether the password is shown when 
typed by the user. 

1. Create a new `xib` file. Since the theme in this example uses 
   `LoginScreenlet`, the new `xib` file is called `LoginScreenlet_ext.xib`. This 
   file is where you'll use Interface Builder to build your new UI. A good way 
   to start is to duplicate the `xib` file for the screenlet your theme applies 
   to and then use it as a template. This example uses 
   `LoginScreenlet_default.xib` as a template.  Also, add a new `UISwitch` 
   component to configure the password presentation. 

    ![Figure 3: New `xib` file for the Extended theme.](../../images/screens-ios-xcode-ext-theme.png)

2. Create a new view class for your theme, based on the parent class. The 
   view class in this example is called `LoginScreenletView_ext` and uses 
   `LoginScreenletView_default` as the parent class. To set new translations, 
   you can override the `onSetTranslations` method. For other customizations, 
   consider overriding the parent class' other methods, or add new delegates 
   to the class. Also, you should add a new `@IBOutlet` or `@IBAction` for the 
   new `IBSwitch` component. 

3. Set your new view class as the custom class for your theme's `xib` file. In 
   this example, `LoginScreenletView_ext` is set as the `LoginScreenlet_ext.xib` 
   file's custom class. Also, bind your new `@IBOutlet` and `@IBAction` to your 
   class. 

4. Install the new theme (drag and drop the classes and resources) and insert 
   the screenlet it applies to in any of your new controllers. The screenlet in 
   this example is `LoginScreenlet`. Also, use `ext` as the value for the 
   screenlet's `themeName` property. Your new look and feel is shown in 
   Interface Builder. 

Well done! You now know how to create an Extended theme in Screens for iOS. In 
the next section, you'll learn how to package and distribute your themes using 
CocoaPods.

## Publish Your Themes Using CocoaPods [](id=publish-your-themes-using-cocoapods)

Since your theme is a code library, you can package it using CocoaPods. Doing so 
means that other developers are able to install and use your theme by simply 
adding the following line in their `Podfile`: 

    pod 'LiferayScreens-YourThemeName'
	
Use the following steps to package your theme for use with CocoaPods. *It's 
important that you use the same names and identifiers described in these steps*:

1. Create an empty Xcode project choosing *Cocoa Touch Framework*.

    ![Choose *Cocoa Touch Framework* when creating a project for your theme.](../../images/screens-ios-cocoa-touch-framework.png)
    
2. Call your project `LiferayScreensThemeName` (change `Name` to your theme's 
   name).

3. Configure Liferay Screens for CocoaPods. To do this, follow the steps 
   described in [Preparing Android Projects for Liferay Screens](/develop/tutorials/-/knowledge_base/6-2/preparing-ios-projects-for-liferay-screens).

4. Create your theme's classes and resources according to the instructions in 
   the above sections. Your classes must compile successfully in Xcode. Use 
   caution if you create your own `xib` files. The custom class must use your 
   theme's module name. For example, the Custom Class setting in this screenshot 
   is incorrect. This is because the `xib` file is bound to the custom class 
   name without specifying the module:

    ![The `xib` file is bound to the custom class name without specifying the module.](../../images/screens-ios-theme-custom-module-wrong.png)

    In the following screenshot, the setting for the custom class is correct:

    ![Xib file binded to custom class name specifying the module.](../../images/screens-ios-theme-custom-module-right.png)

5. In your project's root folder, add a file called 
   `LiferayScreensTheme-Name.podspec` (change `Name` to your theme's name) with 
   the following content:

        Pod::Spec.new do |s|
            s.name = 'LiferayScreensTheme-Name'
            s.module_name = 'LiferayScreensThemeName'
            s.version = '1.0'
            s.summary = 'Your theme description'
            s.source = {
                :git => 'https://your_repository_url.git',
                :tag => 'v1.0'
            }
        
            s.platform = :ios
            s.ios.deployment_target = '8.0'
            s.requires_arc = true
        
            s.source_files = 'Your/Relative/Folder/**/*.{h,m,swift}'
        
            s.resource_bundle = {
                'LiferayScreens-name' => 'Your/Relative/Folder/**/*.{xib,png,plist,lproj}'
            }
        
            s.dependency 'LiferayScreens'
        end

6. Doublecheck this `podspec` file to make sure you changed the occurences of 
   `Name` and `name` to your theme's name in the following lines: 

        s.name = 'LiferayScreensTheme-Name'
        s.module_name = LiferayScreensThemeName'
        'LiferayScreens-name => Your/Folder/**/*'

You can commit and push your project to your Git repository and then use the 
theme by adding the following line in your app's `Podfile`: 

    pod 'LiferayScreens-YourThemeName', :git => 'https://your_repository_url.git'

If you want to simplify the process even more, you can publish your theme as a 
public Pod. For instructions on this, see the chapter *Deploying a library* in 
the [official CocoaPods guide](https://guides.cocoapods.org/making/getting-setup-with-trunk.html#deploying-a-library). 

Nice work! If you followed along with every section in this tutorial, now you 
know how to create a theme of each type in Liferay Screens for iOS. You even 
know how to package and distribute them with CocoaPods. You're the master of the 
themes! 

## Related Topics [](id=related-topics)

[Using Themes in iOS Screenlets](/develop/tutorials/-/knowledge_base/6-2/using-themes-in-ios-screenlets)

[Architecture of Liferay Screens for iOS](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-ios)

[Creating Screenlets in Liferay Screens for iOS](/develop/tutorials/-/knowledge_base/6-2/creating-screenlets-in-liferay-screens-for-ios)

[Liferay Screens Overview](/develop/tutorials/-/knowledge_base/6-2/liferay-screens-overview)
