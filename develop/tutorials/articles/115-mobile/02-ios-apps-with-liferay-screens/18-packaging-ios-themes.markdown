# Packaging iOS Themes [](id=packaging-ios-themes)

Once you've 
[created a Theme](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes),
you can package it up to
[install and use](/develop/tutorials/-/knowledge_base/7-0/using-themes-in-ios-screenlets)
with its Screenlet. Your Theme is a code library that you can package using
CocoaPods. 

Follow the steps below to package your Theme for use with CocoaPods. (Note that
it's important that you use the same names and identifiers described in these
steps):

1.  Create an empty *Cocoa Touch Framework* Xcode project.

    ![Figure 1: Choose *Cocoa Touch Framework* when creating a project for your Theme.](../../../images/screens-ios-cocoa-touch-framework.png)

2.  Name your project `LiferayScreensThemeName`, replacing `Name` with your
    Theme's name. You can specify any name, but it's a best practice to use your
    Theme's Xcode name, capitalizing its first letter. The entire name becomes
    the Theme's CocoaPods name. 

3.  Configure Liferay Screens for CocoaPods, using the steps described in 
    [Preparing iOS Projects for Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/preparing-ios-projects-for-liferay-screens).

4.  Prepare your Theme's classes and resources by making sure your classes
    compile successfully in Xcode and by explicitly specifying a valid module
    for the custom class--the grayed-out *Current* default value only suggests a
    module.

    ![Figure 2: This XIB file's custom class's module is NOT specified.](../../../images/screens-ios-theme-custom-module-wrong.png)

    In the following screenshot, the setting for the custom class is correct:

    ![Figure 3: The XIB file is bound to the custom class name, with the specified module.](../../../images/screens-ios-theme-custom-module-right.png)

5.  In your project's root folder, add a file named
    `LiferayScreensTheme-Name.podspec` (change `Name` to your Theme's CocoaPods
    name--the value you used to replace `Name` in step 2). Note: you must start
    your the `.podspec` file's name and the project's name with `LiferayScreens`.

    Add the following content to the file: 

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
 
    Make the following substitutions in the `.podspec` file:

    * Replace `Name` in `LiferayScreensThemeName`, with your Theme's CocoaPods
      name--the value you used to replace `Name` in step 2. 
    * Replace `your_repository_url` with your repository's URL.
    * Replace `Your/Relative/Folder/` with the path to your source and resource
      files. 

6. Commit your changes and push your project's branch to your Git repository.

Your Theme is now available for other developers to pull from your Git
repository. You can, alternatively, publish your Theme as a public Pod. For
instructions, see the chapter *Deploying a library* in the
[official CocoaPods guide](https://guides.cocoapods.org/making/getting-setup-with-trunk.html#deploying-a-library). 

Developers can now use your Theme by adding the following line to their app's
`Podfile`; they must, of course, change `Name` to the Theme's CocoaPods name and
`your_repository_url` to your repository's URL: 

    pod 'LiferayScreensThemeName', :git => 'https://your_repository_url.git'

Nice work! Now you know how to package and distribute Screenlet Themes with
CocoaPods. 

**Related Topics**

[Using Themes in iOS Screenlets](/develop/tutorials/-/knowledge_base/7-0/using-themes-in-ios-screenlets)

[Architecture of Liferay Screens for iOS](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-ios)

[Creating iOS Themes](/develop/tutorials/-/knowledge_base/7-0/creating-ios-themes)

[Creating iOS Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-ios-screenlets)

[Preparing Android Projects for Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/preparing-android-projects-for-liferay-screens)

