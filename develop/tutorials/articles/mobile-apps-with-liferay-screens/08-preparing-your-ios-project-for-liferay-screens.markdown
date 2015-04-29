# Preparing Your iOS Project for Liferay Screens [](id=preparing-your-ios-project-for-liferay-screens)

Before you can use Liferay Screens, your iOS project must be configured 
properly. This consists of inserting Screens and its dependencies in your 
project. However, the steps for accomplishing this differ depending on the 
iOS versions your project supports. If your project only supports iOS versions 
8.0 or above, then you can prepare it for Screens by adding a single line to 
your project's `Podfile`. This is covered in this tutorial's first section. 
Adding Screens in this manner is possible because Liferay Screens is released 
as a standard [CocoaPods](https://cocoapods.org/) dependency. You should also 
note that since Screens for iOS is written in Swift, you need to use CocoaPods 
version 0.36 or above. Refer to [this article](http://blog.cocoapods.org/CocoaPods-0.36/) 
for more details about how CocoaPods works with Swift dependencies. 

If you need to support iOS 7, then you must manually add Screens to your 
project. The reasons for this are detailed in [this CocoaPods article](http://blog.cocoapods.org/Pod-Authors-Guide-to-CocoaPods-Frameworks/). 
As you've probably guessed, manually adding Screens to your project is more 
involved than adding a single line of code to a `Podfile`. Don't worry though! 
It's still not horribly complicated. This tutorial's second section walks you 
through the process. 

Regardless of the exact process needed to get Screens up and running in your iOS 
project, you also need to configure it to communicate with your Liferay 
installation. You could have the greatest app in the world, but if it can't 
communicate with your Liferay installation then it's all for naught. This 
tutorial's final section shows you how to do this. Onwards! 

## Using CocoaPods to Prepare Your Project [](id=using-cocoapods-to-prepare-your-project)

To prepare your iOS 8.0 (or above) project for Liferay Screens, simply add the 
following line to your project's `Podfile` (create this file if it doesn't yet 
exist): 

    `pod 'LiferayScreens'`

Your final `Podfile` should look something like this:

    source 'https://github.com/CocoaPods/Specs.git'
    
    platform :ios, '8.0'
    use_frameworks!
    
    pod 'LiferayScreens'
    
    # the rest of your Podfile

You can also use [this `Podfile`](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/Showcase-swift/Podfile) 
as a template. Next, navigate to your project's directory on the command line 
and execute `pod install`. This installs Liferay Screens into your project. Once 
this completes, quit Xcode (if you have it open) and open your project by using 
the `*.xcworkspace` file in your project's directory. From now on, you'll need 
to use this file every time you open your project. 

Great! Although your project now includes Liferay Screens, you still need to 
configure it to communicate with your Liferay installation. Proceed to this 
tutorial's final section for instructions on doing this.

## Manually Preparing Your Project [](id=manually-preparing-your-project)

There are a few things you need to manually setup in your iOS 7 app to prepare 
it for Liferay Screens. First, you need to download the 
[Liferay Screens source code](https://github.com/liferay/liferay-screens/releases) 
and include it in your project. The steps for doing this are shown here: 

1. Create a folder at the root of your project called `Liferay-Screens`.

2. Copy the folders `Framework/Core` and `Framework/Themes` from the downloaded 
   source code into this new folder. After this, you'll have two subdirectories 
   in your project's `Liferay-Screens` directory: `Core` and `Themes`.
   
3. In the Finder, drag and drop `Liferay-Screens` into your Xcode project. Make 
   sure the *Create groups* radio button is selected in the dialog that appears.

![Figure 1: This Xcode project includes Liferay Screens.](../../images/screens-ios-project-setup.png)

Next, set up [CocoaPods](http://cocoapods.org) for your project if you haven't 
done so already. Add the following dependencies to your `Podfile` (create this 
file if it doesn't yet exist):

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

Next, navigate to your project's directory on the command line and execute 
`pod install`. Once this completes, quit Xcode and open your project by using 
the `*.xcworkspace` file in your project's directory. From now on, you'll need 
to use this file every time you open your project. 

You should consider using the [CocoaPods for Xcode plugin](https://github.com/kattrali/cocoapods-xcode-plugin). 
You can install it through the [Alcatraz package manager](http://alcatraz.io/) 
for Xcode. This way, you can perform these tasks from Xcode. 

![Figure 2: The CocoaPods for Xcode plugin.](../../images/screens-ios-xcode-cocoapods.png)

If you want to add Liferay Screens to your test target (to be used under 
XCTest), then you need to use a `Podfile` similar to this one:

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

In your project's build settings, you also need to edit the 
*Objective-C bridging header* to include 
`${SRCROOT}/Liferay-Screens/Core/liferay-screens-bridge.h`. This is shown in 
the following screenshot:

![Figure 3: The Objective-C bridging header.](../../images/screens-ios-project-header.png)

### Troubleshooting a Manual Installation [](id=troubleshooting-a-manual-installation)

Since the preferred way to install Liferay Screens is using CocoaPods, you 
should be aware that there are some problems associated with the manual 
installation procedure. For example, when using a screenlet in your project, you 
may get an exception due to an unknown class in Interface Builder. This is shown 
in the following screenshot: 

![Figure 4: When using a screenlet with Screens installed manually, you may get an exception due to an unknown class in Interface Builder.](../../images/screens-ios-xcode-unknown-class.png)

This error occurs because the screenlet views are bound to the `LiferayScreens` 
Module. This module exists only if you installed Screens with CocoaPods. To 
solve this problem, you must open the failed `xib` file listed in the error 
trace (in the above screenshot this is `LoginView_default.xib`), select the root 
view, and then re-set the Custom Class. Note that the Module value should change 
from `LiferayScreens` to be blank, which results in the grayed out text 
`Current - your app name` being displayed as the Module value. Alternatively, 
you can click the dropdown next to the Module field and select an element from 
the list. For example, if your app's name is `ManualTestApp`, then you can 
select `ManualTestApp` from the Module dropdown. Before and after screenshots of 
this assignment are shown here: 

![Figure 5: By default, the Module is set to LiferayScreens.](../../images/screens-ios-xcode-custom-class-before.png)

![Figure 6: Here, the Module corresponds to `Current -` plus the app's name.](../../images/screens-ios-xcode-custom-class-after.png)

![Figure 7: In this case, only the app's name is set as the Module.](../../images/screens-ios-xcode-custom-class-after-2.png)

Great! Although your project now includes Liferay Screens, you still need to 
configure it to communicate with your Liferay installation. This is required 
regardless of how you installed Screens in your iOS project. Proceed to the next 
section for instructions on doing this.

## Configuring Your Project to Communicate with Your Liferay Installation [](id=configuring-your-project-to-communicate-with-your-liferay-installation)

There's just one more thing to take care of to ensure that your project is ready 
for Liferay Screens. You need to make sure that your project is configured to 
communicate with your Liferay Portal instance. Create a new property list file 
called `liferay-server-context.plist`. Use [`liferay-server-context-sample.plist`](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Resources/liferay-server-context-sample.plist) 
as a template. This screenshot shows such a file being browsed:

![Figure 8: The `liferay-context.plist` file.](../../images/screens-ios-liferay-context.png)

Great! Your iOS project should now be ready for Liferay Screens. 

## Related Topics [](id=related-topics)

[Using Screenlets in Your iOS Project](/tutorials/-/knowledge_base/6-2/using-screenlets-in-your-ios-project)

[Using Themes in Liferay Screens for iOS](/tutorials/-/knowledge_base/6-2/using-themes-in-liferay-screens-for-ios)

[Architecture of Liferay Screens for iOS](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-ios)
