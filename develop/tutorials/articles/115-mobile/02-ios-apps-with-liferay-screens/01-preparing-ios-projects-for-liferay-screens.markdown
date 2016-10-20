# Preparing iOS Projects for Liferay Screens [](id=preparing-ios-projects-for-liferay-screens)

To use Liferay Screens in your iOS project, you must install it, install its
dependencies, and configure it. The steps differ depending on the iOS version
your project supports. If your app only needs to support iOS versions 
8.0 or above, you can prepare it for Screens by adding a single line to 
your project's `Podfile` and using CocoaPods to complete configuration. This is
possible because Liferay Screens is released as a standard
[CocoaPods](https://cocoapods.org) dependency. If you need to support iOS 8,
however, then you must manually add Screens to your project. This CocoaPods
[article](http://blog.cocoapods.org/Pod-Authors-Guide-to-CocoaPods-Frameworks)
explains why. As you've probably guessed, manually adding Screens to your
project is more challenging; don't worry, though! It's still not horribly 
complicated. 

Note: since Screens for iOS is written in Swift, you need to use CocoaPods
version 0.36 or higher. Refer to 
[this article](http://blog.cocoapods.org/CocoaPods-0.36/) 
for more details about how CocoaPods works with Swift dependencies. 

This tutorial specifies Screens's requirements, shows you both configuration
methods, and shows how to set up communication with Liferay. Onward! 

## Requirements [](id=requirements)

Liferay Screens for iOS includes the Component Library (the Screenlets) and
four sample projects written in Swift. Screens is developed using Swift and 
development techniques that leverage functional Swift code and the 
[Model View Presenter](http://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter) 
architecture. You can use Swift or Objective-C with Screens, and you can run 
Screens apps on iOS 8 and above--Screens doesn't use iOS 9 APIs. 

Liferay Screens for iOS requires the following software: 

- Xcode 7.3
- iOS 9 SDK
- [CocoaPods](http://cocoapods.org) 0.36 or newer
- [Liferay Portal 6.2 CE/EE, 7.0 CE, Liferay DXP](http://www.liferay.com/downloads/liferay-portal/available-releases)
- Liferay Screens Compatibility Plugin (
  [CE](http://www.liferay.com/marketplace/-/mp/application/54365664) or 
  [EE](http://www.liferay.com/marketplace/-/mp/application/54369726), 
  depending on your portal edition). This app is preinstalled in Liferay 7.0 CE 
  and Liferay DXP instances. 
- Liferay Screens source code

## Configuring Your Project with CocoaPods [](id=configuring-your-project-with-cocoapods)

<iframe width="560" height="315" src="https://www.youtube.com/embed/usRo6bCpR_U" frameborder="0" allowfullscreen></iframe>

To prepare your iOS 8.0 (or above) project for Liferay Screens, add the
following line to your project's `Podfile` (create this file if it doesn't yet
exist):

    pod 'LiferayScreens'

Your final `Podfile` should look like this:

    source 'https://github.com/CocoaPods/Specs.git'
    
    platform :ios, '8.0'
    use_frameworks!
    
    pod 'LiferayScreens'
    
    # the rest of your Podfile

You can also use 
[this `Podfile`](https://github.com/liferay/liferay-screens/blob/master/ios/Samples/Showcase-swift/Podfile) 
as a template.

Next, in your terminal, install Liferay Screens by executing `pod install` from
your project's folder. Once this completes, quit Xcode (if you have it open). To
open your project, use the `*.xcworkspace` file in your project's directory. 

Great! To configure your project's communication with Liferay, you can skip the
next section and follow the instructions in the final section. 

## Manual Configuration [](id=manual-configuration)

There are a few things you need to set up manually in your iOS 8 app to prepare 
it for Liferay Screens. First, you must download the Liferay Screens 
[source code](https://github.com/liferay/liferay-screens/releases) 
and include it in your project. Follow these steps to do this: 

1. Create a folder, (e.g., called `Liferay-Screens`) at the root of your
   project. 

2. Copy the folders `Framework/Core` and `Framework/Themes` from the downloaded 
   source code into the folder you created. 

3. Using the Finder, drag and drop `Liferay-Screens` into your Xcode project.
   Make sure the *Create groups* radio button is selected in the dialog that
   appears. 

![Figure 1: This Xcode project includes Liferay Screens.](../../../images/screens-ios-project-setup.png)

Add the following dependencies to your `Podfile` (create this file if it doesn't
yet exist): 

    source 'https://github.com/CocoaPods/Specs.git'

    platform :ios, '8.0'

    pod 'Liferay-iOS-SDK', '7.0.4'
    pod 'Liferay-OAuth', '1.2.0'
    pod 'MBProgressHUD', '0.9.1'
    pod 'SMXMLDocument', '1.1'
    pod 'ODRefreshControl', '1.2'
    pod 'YapDatabase/SQLCipher', '2.8.2'
    pod 'KeychainAccess', '2.3.1'
    pod 'CryptoSwift', '0.1.1'
    pod 'DTPickerPresenter', '0.2.0'
    pod 'TNRadioButtonGroup', '0.4'
    pod 'MDRadialProgress', '1.3.2'
    pod 'Kingfisher', '2.4'
    pod 'Cosmos', '1.2'

Quit Xcode if you have it open. In a terminal, navigate to your project's 
directory and execute `pod install`. To open your project, use the 
`*.xcworkspace` file in your project's directory. 

The
[CocoaPods for Xcode plugin](https://github.com/kattrali/cocoapods-xcode-plugin) 
that you can install with the [Alcatraz package manager](http://alcatraz.io/) 
can perform these tasks from Xcode. 

![Figure 2: You can perform Xcode tasks with the CocoaPods for Xcode plugin helps.](../../../images/screens-ios-xcode-cocoapods.png)

You can optionally add Liferay Screens to your test target (to be used under
XCTest). Here's a `Podfile` with a placeholder for a test target:

    source 'https://github.com/CocoaPods/Specs.git'

    platform :ios, '7.0'

    def import_pods
        pod 'Liferay-iOS-SDK', '7.0.4'
        pod 'Liferay-OAuth', '1.2.0'
        pod 'MBProgressHUD', '0.9.1'
        pod 'SMXMLDocument', '1.1'
        pod 'ODRefreshControl', '1.2'
        pod 'YapDatabase/SQLCipher', '2.8.2'
        pod 'KeychainAccess', '2.3.1'
        pod 'CryptoSwift', '0.1.1'
        pod 'DTPickerPresenter', '0.2.0'
        pod 'TNRadioButtonGroup', '0.4'
        pod 'MDRadialProgress', '1.3.2'
        pod 'Kingfisher', '2.4'
        pod 'Cosmos', '1.2'
    end

    import_pods

    target :<<Your Test Target Name Goes Here>> do
        import_pods
    end

In your project's build settings, you must edit its 
*Objective-C bridging header* to include 
`${SRCROOT}/Liferay-Screens/Core/liferay-screens-bridge.h`. 

![Figure 3: Here's the Objective-C bridging header.](../../../images/screens-ios-project-header.png)

### Troubleshooting Manual Installation [](id=troubleshooting-manual-installation)

There are some problems associated with the manual installation procedure. For
example, when using a Screenlet in your project, Interface Builder may complain 
about an unknown class. This error occurs because the Screenlet is bound to the 
`LiferayScreens` Module. This module exists only if you installed Screens with 
CocoaPods. To solve this problem, you must open the failed XIB file listed in 
the error trace (in Figure 4, this is `LoginView_default.xib`), select the root 
View, and then reset the Custom Class. Note that the Module value changes from 
`LiferayScreens` to blank; the Module value shows as the grayed-out text 
`Current - your app name`. 

![Figure 4: When using a Screenlet with Screens installed manually, you may get an exception due to an unknown class in Interface Builder.](../../../images/screens-ios-xcode-unknown-class.png)

Alternatively, you can click the selection box next to the Module field and
select an element from the list. For example, if your app's name is
`ManualTestApp`, you can select `ManualTestApp` from the Module selection box.
Before and after screenshots of this assignment are shown in Figure 5 through
Figure 7. 

![Figure 5: By default, the Module is set to LiferayScreens.](../../../images/screens-ios-xcode-custom-class-before.png)

![Figure 6: Here, the Module corresponds to `Current -` plus the app's name.](../../../images/screens-ios-xcode-custom-class-after.png)

![Figure 7: In this case, only the app's name is set as the Module.](../../../images/screens-ios-xcode-custom-class-after-2.png)

Great! You've completed installing Liferay Screens and its dependencies in your
project. It's time to configure its communication with your Liferay instance. 

## Configuring Communication with Liferay [](id=configuring-communication-with-liferay)

<iframe width="560" height="315" src="https://www.youtube.com/embed/5aCELB1Lq80" frameborder="0" allowfullscreen></iframe>

Configuring communication between Screenlets and Liferay is easy. Liferay
Screens uses a property list (`.plist`) file to access your Liferay instance.
It must include the server's URL, the portal's company ID, and the site's group
ID. Create a `liferay-server-context.plist` file and specify values required for 
communicating with your Liferay instance. As an example, refer to
[`liferay-server-context-sample.plist`](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Resources/liferay-server-context-sample.plist). 

![Figure 8: Here's a property list file, called `liferay-context.plist`.](../../../images/screens-ios-liferay-context.png)

The values you need to specify in your `liferay-server-context.plist` are:

- `server`: Your Liferay instance's URL.
- `version`: Your Liferay instance's version. Supported values are `70` for
  Liferay 7.0, and `62` for Liferay 6.2.
- `companyId`: Your Liferay instance's identifier. You can find this value in
  the *Instance ID* column of *Control Panel* &rarr; *Portal Instances*.
- `groupId`: The ID of the default site you want Screens to
  communicate with. You can find this value in the Site ID field of the site's
  *Site Administration* &rarr; *Configuration* &rarr; *Site Settings* menu.
- `connectorFactoryClassName`: Your Connector's factory class name.
  This is optional. If you don't include it, the `version` value is used to
  determine which factory is the most suitable for that version of Liferay.

Great! Your iOS project is ready for Liferay Screens.

## Related Topics [](id=related-topics)

[Using Screenlets in iOS Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-ios-apps)

[Using Themes in iOS Screenlets](/develop/tutorials/-/knowledge_base/7-0/using-themes-in-ios-screenlets)

[Preparing Android Projects for Liferay Screens](develop/tutorials/-/knowledge_base/7-0/preparing-android-projects-for-liferay-screens)
