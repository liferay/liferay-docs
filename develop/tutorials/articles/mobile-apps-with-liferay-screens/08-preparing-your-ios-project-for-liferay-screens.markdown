# Preparing Your iOS Project for Liferay Screens [](id=preparing-your-ios-project-for-liferay-screens)

Your iOS project must be configured properly before you can use Liferay Screens. 
This consists of inserting Screens in your project and then adding its 
dependencies. Liferay Screens for iOS is released as a plain source code 
library. As soon as CocoaPods supports Swift libraries ([1](https://github.com/CocoaPods/CocoaPods/pull/2222), [2](https://github.com/CocoaPods/CocoaPods/issues/2272)), 
you'll be able to set up your project by simply adding a single line to your 
`Podfile`. In the meantime, there are a few things you need to manually set up 
in your app to prepare it for Liferay Screens. This tutorial shows you how to do 
this. Let the configuration begin! 

## Inserting and Configuring Screens [](id=inserting-and-configuring-screens)

First, you need to download the [Liferay Screens source code](https://github.com/liferay/liferay-screens/releases) 
and include it in your project. The steps for doing this are shown here: 

1. Create a folder at the root of your project called `Liferay-Screens`.

2. Copy the folders `Library/Core` and `Library/Themes` from the downloaded 
   source code into this new folder. After this, you'll have two subdirectories 
   in your project's `Liferay-Screens` directory: `Core` and `Themes`.
   
3. In the Finder, drag and drop `Liferay-Screens` into your Xcode project.

![Figure 1: This Xcode project includes Liferay Screens.](../../images/screens-ios-project-setup.png)

Next, set up [CocoaPods](http://cocoapods.org) for your project if you haven't 
done so already. Add the dependencies to your `Podfile` and then execute 
`pod install`. Use this [Podfile](https://github.com/liferay/liferay-screens/tree/master/ios/Library/Podfile) 
as a template. Note that you should consider using the 
[CocoaPods for Xcode plugin](https://github.com/kattrali/cocoapods-xcode-plugin). 
You can install it through the [Alcatraz package manager](http://alcatraz.io/) 
for Xcode. This way, you can perform these tasks from Xcode. 

![Figure 2: The CocoaPods for Xcode plugin.](../../images/screens-ios-xcode-cocoapods.png)

In your project's build settings, you also need to edit the 
*Objective-C bridging header* to include 
`${SRCROOT}/Liferay-Screens/Core/liferay-screens-bridge.h`. This is shown in 
the following screenshot:

![Figure 3: The Objective-C bridging header.](../../images/screens-ios-project-header.png)

There's just one more thing to take care of to ensure that your project is ready 
for Liferay Screens. You need to make sure that your project is configured to 
communicate with your Liferay Portal instance. Create a new property list file 
called `liferay-server-context.plist`. Use [`liferay-server-context-sample.plist`](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Resources/liferay-server-context-sample.plist) 
as a template. This screenshot shows such a file being browsed:

![Figure 4: The `liferay-context.plist` file.](../../images/screens-ios-liferay-context.png)

Great! Your iOS project should now be ready for Liferay Screens. 

## Related Topics [](id=related-topics)

[Using Screenlets in Your iOS Project](/tutorials/-/knowledge_base/6-2/using-screenlets-in-your-ios-project)

[Using Themes in Liferay Screens for iOS](/tutorials/-/knowledge_base/6-2/using-themes-in-liferay-screens-for-ios)

[Architecture of Liferay Screens for iOS](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-ios)
