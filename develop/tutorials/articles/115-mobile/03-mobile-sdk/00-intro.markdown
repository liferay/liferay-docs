# Mobile SDK [](id=mobile-sdk)

Want to wield Liferay's power in your mobile apps? Thanks to Liferay's Mobile 
SDK, you can do just that. The Liferay Mobile SDK provides a way to streamline 
consuming Liferay core web services, Liferay utilities, and custom app web 
services. It's a low-level layer that wraps Liferay JSON web services, making 
them easy to call in native mobile apps. It takes care of authentication, makes 
HTTP requests (synchronously or asynchronously), parses JSON results, and 
handles server-side exceptions so you can concentrate on *using* the services in 
your app. The Liferay Mobile SDK bridges the gap between your native app and 
Liferay services. The official project 
[page](https://www.liferay.com/community/liferay-projects/liferay-mobile-sdk/overview) 
gives you access to the SDK releases, provides the latest SDK news, and has 
forums for you to engage in mobile app development discussions. The Liferay 
Mobile SDK is available as separate downloads for Android and iOS. 

![Figure 1: Liferay's Mobile SDK enables your native app to communicate with Liferay.](../../../images/mobile-sdk-diagram.png)

There are two different types of Mobile SDKs that you need to add to your app's 
project, depending on the remote services you need to call. Liferay's prebuilt 
Mobile SDK includes the classes required to construct remote service calls in 
general. It also contains the classes required to call the specific remote 
services of Liferay's *core* portlets. Core portlets are included with every 
Liferay installation (these are also referred to as out-of-the-box or built-in 
portlets). However, you need to build an additional Mobile SDK if you want to 
leverage your custom portlet's remote services. Once built, this Mobile SDK 
contains *only* the classes required to call those services. Therefore, you must 
install it in your app alongside Liferay's prebuilt Mobile SDK to leverage your 
custom portlet's remote services. 

Note that Liferay also provides 
[Liferay Screens](https://www.liferay.com/products/liferay-screens) 
for constructing mobile apps that connect to Liferay. Screens uses components 
called *screenlets* to leverage and abstract the Mobile SDK's low-level service 
calls. However, if there's not a screenlet for your use case, or you need more 
control over the service call, then you may want to use the Mobile SDK directly. 
You should read the Screens tutorials in addition to the Mobile SDK tutorials 
here to decide which better fits your needs. 

This section's tutorials cover using the Mobile SDK in Android and iOS app 
development. The following tutorials introduce these topics and are followed by 
in-depth tutorials on each: 

- [Creating Android Apps that Use the Mobile SDK](/develop/tutorials/-/knowledge_base/7-0/creating-android-apps-that-use-the-mobile-sdk)
- [Creating iOS Apps that Use the Mobile SDK](/develop/tutorials/-/knowledge_base/7-0/creating-ios-apps-that-use-the-mobile-sdk)

In addition, the following tutorial covers building Mobile SDKs to support your 
custom portlet services: 

- [Building Mobile SDKs](/develop/tutorials/-/knowledge_base/7-0/building-mobile-sdks)

Fasten your seatbelt--it's time to go mobile with Liferay's Mobile SDK! 

**Related Topics:**

[Android Apps with Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/android-apps-with-liferay-screens)

[iOS Apps with Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/ios-apps-with-liferay-screens)

[Building Mobile SDKs](/develop/tutorials/-/knowledge_base/7-0/building-mobile-sdks)
