# Making Liferay and Custom Portlet Services Available in Your iOS App [](id=making-liferay-and-custom-portlet-services-available-in-your-ios-app)

Your iOS app is no doubt pretty great, or at least off to a great start. Now you 
want it to access Liferay services. How do you accomplish this? Use Liferay's 
iOS SDK, of course! You can also make your custom portlet services available to
your app by building a Liferay iOS SDK that exposes them and by then installing
that SDK in your app project. 

This tutorial covers how to install a Liferay iOS SDK that you either download 
from Liferay or build yourself. Compatibility with versions of Liferay and iOS 
are also discussed. Let the installation begin! 

## Making Custom Portlet Services Available in Your iOS App 

If you want to invoke remote web services for your custom portlets, then you 
need to generate the client libraries by building the SDK yourself. Building the 
SDK is covered in the tutorial
[Building Mobile SDKs](/develop/tutorials/-/knowledge_base/6-2/building-mobile-sdks).
Once you build the SDK to a `zip` file, you can install it using the manual 
installation steps below. Just make sure to use your built `zip` file instead of
downloading Liferay's iOS Mobile SDK `zip` file. 

## Setting Up the iOS SDK 

These installation instructions assume you're using the XCode developer tool 
provided by Apple. You can dowload XCode from the [Mac App Store](https://itunes.apple.com/us/app/xcode/id497799835?ls=1&mt=12).
The iOS SDK can be installed either manually, or by using CocoaPods. The first 
section here covers manual installation. The section that follows covers 
installation using CocoaPods. 

### Installing the SDK Manually 

1. [Download](https://www.liferay.com/community/liferay-projects/liferay-mobile-sdk) the latest version of `liferay-ios-sdk.zip`. 

2. Unzip `liferay-ios-sdk.zip` into your XCode project. 

3. Within XCode, right-click on your project and click 
   *Add Files to 'Project Name'*. 
   
4. Add both `core` and `v62` folders. The `v62` folder name can change for each
   Liferay version. In this example, the SDK is built for Liferay 6.2. 
   
5. The iOS SDK requires [AFNetworking 2.2.4](https://github.com/AFNetworking/AFNetworking/releases/tag/2.2.4).
   Make sure its source code is added to your project.

### Installing the SDK Using CocoaPods 

1. Make sure you have CocoaPods installed.

2. Create a file called `Podfile` in your project and add the following line:

        ruby
        pod 'Liferay-iOS-SDK'

3. Run `$ pod install`. This will download the latest version of the SDK and 
   create a `.xcworkspace` file. CocoaPods will also download all the necessary 
   dependencies and configure your workspace.

4. Use the `.xcworkspace` file to open your project in Xcode.

For more information on how CocoaPods works, see their [documentation](http://guides.cocoapods.org/using/index.html).

## Understanding Liferay and iOS Compatibility 

Each Liferay Mobile SDK is designed to work with a specific Liferay Portal 
version. Because of this, the Liferay Mobile SDK version scheme reflects the 
compatible Liferay version. For example, Liferay Mobile SDK 6.2.0.1 is built to 
work with Liferay Portal 6.2.0. Similarly, Liferay Mobile SDK 7.0.0.1 works with 
Liferay Portal 7.0.0.

The fourth integer in the SDK version (6.2.0.x) is related to internal Liferay
Mobile SDK versions. For example, if a bug is found on 6.2.0.1, Liferay will
release a version called 6.2.0.2 with the bug fix.

This doesn't mean you can't support several Liferay versions in the same app 
though. You can add both versions 6.2.0.1 and 7.0.0.1 to your project. These 
don't conflict because service class names are suffixed with their version 
number. For example, service classes from versions 6.2.x and 7.0.x end in 
`_v62.m` and `_v7.m`, respectively. To find out which Liferay versions you are 
connecting to, use the `[LRPortalVersionUtil getPortalVersion:�]` method. 

The Liferay iOS SDK is compatible with iOS versions 6.1 and up. Older versions 
of iOS may work with the SDK, but compatibility untested. 

## Related Topics

[Liferay Mobile SDK Builder](/develop/tutorials/-/knowledge_base/6-2/liferay-mobile-sdk-builder)

[Service Builder and Services](/develop/tutorials/-/knowledge_base/6-2/service-builder)

[Creating Android Apps that Use Liferay](/develop/tutorials/-/creating-android-apps-that-use-liferay)

