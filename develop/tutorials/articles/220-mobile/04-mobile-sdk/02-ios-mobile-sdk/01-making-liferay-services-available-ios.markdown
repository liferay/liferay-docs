# Making Liferay and Custom Portlet Services Available in Your iOS App [](id=making-liferay-and-custom-portlet-services-available-in-your-ios-app)

Your iOS app is no doubt pretty great, or at least off to a great start. Now you 
want it to access Liferay services. How do you accomplish this? Use Liferay's 
iOS Mobile SDK, of course! You must install the correct Mobile SDKs in your iOS 
project to call the remote services you need in your app. You should first 
install Liferay's prebuilt Mobile SDK. This is required for any app that 
leverages Liferay. To call your custom portlet's services, you also need to 
install the Mobile SDK that you built for it. For instructions on building a 
Mobile SDK for your custom portlet, see the tutorial 
[Building Mobile SDKs](/develop/tutorials/-/knowledge_base/7-0/building-mobile-sdks). 

This tutorial shows you how to install Liferay's prebuilt Mobile SDK, and any 
custom built Mobile SDKs. First, you'll learn how to use 
[CocoaPods](https://cocoapods.org/) 
to install Liferay's prebuilt Mobile SDK. You'll then learn how to install a 
Mobile SDK manually, which is required for installing any custom built Mobile 
SDKs. Now go forth and fear no remote service! 

## Installing the SDK Using CocoaPods [](id=installing-the-sdk-using-cocoapods)

Using CocoaPods is the simplest way to install Liferay's prebuilt Mobile SDK. 
The steps for doing so are shown here:

1. Make sure you have 
   [CocoaPods](https://cocoapods.org/) 
   installed.

2. Create a file called `Podfile` in your project. Add the following line in 
   this file:

        pod 'Liferay-iOS-SDK'

3. Run `pod install` from your project's directory. This downloads the latest 
   version of the Liferay iOS Mobile SDK and creates a `.xcworkspace` file. 
   CocoaPods also downloads all the necessary dependencies and configures your 
   workspace. Note that you may have to run `pod repo update` before running 
   `pod install`; this ensures you have the latest version of the CocoaPods 
   repository on your machine.

4. Use the `.xcworkspace` file to open your project in Xcode.

5. If you're importing dependencies as frameworks (`use_frameworks!` in your 
   Podfile), you need to import the `LRMobileSDK` module:
   
        @import LRMobileSDK; // (Objective-C)
        import LRMobileSDK // (Swift)

For more information on how CocoaPods works, see their 
[documentation](http://guides.cocoapods.org/using/index.html). 
Next, you'll learn how to install a Mobile SDK manually. 

## Installing an iOS SDK Manually [](id=installing-an-ios-sdk-manually)

You can also install Mobile SDKs manually. This is required if you built one 
for your custom portlet's services. You can also install install Liferay's 
prebuilt Mobile SDK manually if you don't want to use CocoaPods. 

1. To install Liferay's prebuilt Mobile SDK, first 
   [download](https://github.com/liferay/liferay-mobile-sdk/releases) 
   the latest version of the Liferay iOS Mobile SDK ZIP file. If you built your 
   own Mobile SDK, find its ZIP file on your machine. This is detailed in the 
   [Building Mobile SDKs](/develop/tutorials/-/knowledge_base/7-0/building-mobile-sdks) 
   tutorial. 

2. Unzip the file into your Xcode project. 

3. Within Xcode, right-click on your project and click 
   *Add Files to 'Project Name'*. 
   
4. Add the `core` and `v7` folders. Note the `v7` folder's name can change for 
   each Liferay version. In this example, the SDK is built for Liferay 7.0. 
   
5. If you're manually installing Liferay's prebuilt Mobile SDK, it also requires 
   [AFNetworking 2.6.3](https://github.com/AFNetworking/AFNetworking/releases/tag/2.6.3).
   Add its source code to your project. 

Great! Now you know how to manually install a Mobile SDK in your iOS apps. 

## Understanding Liferay and iOS Compatibility [](id=understanding-liferay-and-ios-compatibility)

Each Liferay Mobile SDK is designed to work with a specific Liferay version. The 
Liferay Mobile SDK version number reflects this. The first two digits of each 
Mobile SDK's version number correspond to the compatible Liferay version. For 
example, a Mobile SDK version `6.2.*` is compatible with Liferay 6.2, while a 
Mobile SDK version `7.0.*` is compatible with Liferay 7.0. Any digits after the 
first two correspond to the internal Liferay Mobile SDK build. 

The Mobile SDK's service class names are also suffixed with the Mobile SDK's 
version number. This lets your app support several Liferay versions. For 
example, you can add Mobile SDK versions `6.2.0.22` and `7.0.3` to the same 
project. The Mobile SDK service classes supporting Liferay versions 6.2 and 7.0 
end in `_v62.m` and `_v7.m`, respectively. To find out the Liferay versions your 
app connects to, use the 
`[LRPortalVersionUtil getPortalVersion:...]` method. 

The Liferay iOS Mobile SDK is compatible with iOS versions 7.0 and up. Older iOS 
versions may work, but compatibility is untested. 

## Making Custom Portlet Services Available in Your iOS App [](id=making-custom-portlet-services-available-in-your-ios-app)

If you want to invoke remote web services for your custom portlet, then you need 
to generate its client libraries by building an iOS Mobile SDK yourself. 
Building an SDK is covered in the tutorial
[Building Mobile SDKs](/develop/tutorials/-/knowledge_base/7-0/building-mobile-sdks).
Once you build an SDK to a ZIP file, you can install it using the manual 
installation steps above (make sure to use the ZIP file you built instead of 
Liferay's prebuilt ZIP file). Note that because your custom built SDKs contain 
*only* the client libraries for calling your custom portlet services, you must 
install them alongside Liferay's prebuilt SDK. Liferay's prebuilt SDK contains 
additional classes that are required to construct any remote service call. 

## Related Topics [](id=related-topics)

[Building Mobile SDKs](/develop/tutorials/-/knowledge_base/7-0/building-mobile-sdks)

[Creating Android Apps that Use the Mobile SDK](/develop/tutorials/-/knowledge_base/7-0/creating-android-apps-that-use-the-mobile-sdk)
