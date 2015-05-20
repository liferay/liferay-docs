# Preparing Your iOS Project for Liferay Screens [](id=preparing-your-ios-project-for-liferay-screens)

To use Liferay Screens in your iOS project, you must install it, install its
dependencies, and configure it. The steps differ depending on the iOS version
you're project supports. If your app only needs to support iOS versions 
8.0 or above, then you can prepare it for Screens by adding a single line to 
your project's `Podfile` and using CocoaPods to complete configuration. This is
possible because Liferay Screens is released as a standard
[CocoaPods](https://cocoapods.org/) dependency. If you need to support iOS 7,
however, then you must manually add Screens to your project. This CocoaPods
[article](http://blog.cocoapods.org/Pod-Authors-Guide-to-CocoaPods-Frameworks/)
explains why. As you've probably guessed, manually adding Screens to your
project is more challenging; don't worry, though! It's still not horribly complicated. 

Note: since Screens for iOS is written in Swift, you need to use CocoaPods
version 0.36 or higher. Refer to [this
article](http://blog.cocoapods.org/CocoaPods-0.36/) for more details about how
CocoaPods works with Swift dependencies. 

This tutorial specifies Screens's requirements, shows you both configuration
methods, and shows how to set up communication with Liferay. Onwards! 

## Requirements

<!-- TODO list requirements -->

## Configuring Your Project with CocoaPods

To prepare your iOS 8.0 (or above) project for Liferay Screens, add the
following line to your project's `Podfile` (create this file if it doesn't yet
exist):

    pod 'LiferayScreens'

Your final `Podfile` should look something like this:

    source 'https://github.com/CocoaPods/Specs.git'
    
    platform :ios, '8.0'
    use_frameworks!
    
    pod 'LiferayScreens'
    
    # the rest of your Podfile

You can also use [this `Podfile`](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Showcase-swift/Podfile) 
as a template.

Next, in your terminal, install Liferay Screns by executing `pod install` from
your project's folder. Once this completes, quit Xcode (if you have it open). To
open your project use the `*.xcworkspace` file in your project's directory. 

Great! To configure your project's communication with Liferay, you can skip the
next section and follow the instuctions in the final section. 

## Manual Configuration

There are a few things you need to manually setup in your iOS 7 app to prepare 
it for Liferay Screens. First, you must download the 
Liferay Screens [source code](https://github.com/liferay/liferay-screens/releases) 
and include it in your project. Follow these steps: 

1. Create a folder, (e.g., called `Liferay-Screens`) at the root of your
   project. 

2. Copy the folders `Framework/Core` and `Framework/Themes` from the downloaded 
   source code into the folder you created. 

3. Using the Finder, drag and drop `Liferay-Screens` into your Xcode project.
   Make sure the *Create groups* radio button is selected in the dialog that
   appears. 

![Figure 1: This Xcode project includes Liferay Screens.](../../images/screens-ios-project-setup.png)

Next, set up [CocoaPods](http://cocoapods.org) version 0.36 (or above) for your
project if you haven't done so already. Add the following dependencies to your
`Podfile` (create this file if it doesn't yet exist):

    source 'https://github.com/CocoaPods/Specs.git'

    platform :ios, '7.0'

    pod 'MBProgressHUD'
    pod 'SMXMLDocument'
    pod 'UICKeyChainStore'
    pod 'DTPickerPresenter'
    pod 'TNRadioButtonGroup'
    pod 'MDRadialProgress'
    pod 'ODRefreshControl'
    pod 'Liferay-iOS-SDK'

In a terminal, navigate to your project's directory and execute 
`pod install`. Once this completes, quit Xcode (if you have it open). To
open your project use the `*.xcworkspace` file in your project's directory. 

The
[CocoaPods for Xcode plugin](https://github.com/kattrali/cocoapods-xcode-plugin),  
that you can install with the [Alcatraz package manager](http://alcatraz.io/), 
can perform these tasks from Xcode. 

![Figure 2: You can perform Xcode tasks with the CocoaPods for Xcode plugin helps.](../../images/screens-ios-xcode-cocoapods.png)

You can optionally add Liferay Screens to your test target (to be used under
XCTest). Here's a `Podfile` with a placeholder for a test target:

    source 'https://github.com/CocoaPods/Specs.git'

    platform :ios, '7.0'

    def import_pods
        pod 'MBProgressHUD'
        pod 'SMXMLDocument'
        pod 'UICKeyChainStore'
        pod 'DTPickerPresenter'
        pod 'TNRadioButtonGroup'
        pod 'MDRadialProgress'
        pod 'ODRefreshControl'
        pod 'Liferay-iOS-SDK'
    end

    import_pods

    target :<<You Test Target Name Goes Here>> do
        import_pods
    end

In your project's build settings, you must edit its 
*Objective-C bridging header* to include 
`${SRCROOT}/Liferay-Screens/Core/liferay-screens-bridge.h`. This is shown in 
the following screenshot.

![Figure 3: Here's the Objective-C bridging header.](../../images/screens-ios-project-header.png)

### Troubleshooting Manual Installation

There are some problems associated with the manual installation procedure. For
example, when using a screenlet in your project, the Interface Builder may
complain about a unknown class. This error occurs because the screenlet is bound
to the `LiferayScreens` Module. This module exists only if you installed Screens
with CocoaPods (for iOS 8 and above). To solve this problem, you must open the
failed `xib` file listed in the error trace (in Figure 4, this is
`LoginView_default.xib`), select the root view, and then re-set the Custom
Class. Note that the Module value changes from `LiferayScreens` to blank; the
Module values shows as the grayed-out text `Current - your app name`. 

![Figure 4: When using a screenlet with Screens installed manually, you may get an exception due to an unknown class in Interface Builder.](../../images/screens-ios-xcode-unknown-class.png)

Alternatively, you can click the dropdown next to the Module field and select an
element from the list. For example, if your app's name is `ManualTestApp`, then
you can select `ManualTestApp` from the Module dropdown. Before and after
screenshots of this assignment are shown in Figure 5 through Figure 7. 

![Figure 5: By default, the Module is set to LiferayScreens.](../../images/screens-ios-xcode-custom-class-before.png)

![Figure 6: Here, the Module corresponds to `Current -` plus the app's name.](../../images/screens-ios-xcode-custom-class-after.png)

![Figure 7: In this case, only the app's name is set as the Module.](../../images/screens-ios-xcode-custom-class-after-2.png)

Great! You've completed installing Liferay Screens and its dependencies in your
project. It's time to configure its communication with your Liferay instance. 

## Configuring Communication with Liferay

Configuring communication between screenlets and Liferay is easy. Liferay
Screens uses a property list (`.plist`) file, to access your Liferay instance.
It must include the server's URL, the portal's company ID, and the site's group
ID. Create a `.plist` file and specify values required for communicating with
your Liferay instance. As an example, refer to
[`liferay-server-context-sample.plist`](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Resources/liferay-server-context-sample.plist). 

![Figure 8: Here's a property list file, called `liferay-context.plist`.](../../images/screens-ios-liferay-context.png)

Great! Your iOS project is ready for Liferay Screens. 

## Related Topics [](id=related-topics)

[Using Screenlets in Your iOS Project](/tutorials/-/knowledge_base/6-2/using-screenlets-in-your-ios-project)

[Using Themes in Liferay Screens for iOS](/tutorials/-/knowledge_base/6-2/using-themes-in-liferay-screens-for-ios)

[Architecture of Liferay Screens for iOS](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-ios)
