---
header-id: creating-ios-apps-that-use-the-mobile-sdk
---

# Creating iOS Apps that Use the Mobile SDK

[TOC levels=1-4]

The Liferay Mobile SDK provides a way to streamline the consumption of 
@product@'s core web services and utilities, as well as those of custom apps. It 
wraps @product@'s JSON web services, making them easy to call in native mobile 
apps. It handles authentication, makes HTTP requests (synchronously or 
asynchronously), parses JSON results, and handles server-side exceptions so you 
can concentrate on *using* the services in your app. 

The Liferay Mobile SDK comes with the Liferay iOS SDK. The official 
[project page](https://www.liferay.com/community/liferay-projects/liferay-mobile-sdk/overview)
gives you access to the SDK releases, provides the latest SDK news, and has
forums for you to engage in mobile app development discussions. Once you 
configure the Mobile SDK in your app, you can invoke @product@ services in it. 

![Figure 1: Liferay's Mobile SDK enables your native app to communicate with Liferay.](../../../../images/mobile-sdk-diagram.png)

The iOS Mobile SDK app development tutorials cover these topics: 

- Making Liferay and Custom Portlet Services Available in Your iOS App
- Invoking Liferay Services in Your iOS App
- Invoking Services Asynchronously from Your iOS App
- Sending Your iOS App's Requests Using Batch Processing

A great way to start is by setting up the Mobile SDK your iOS project. This 
makes @product@'s services available in your app. 

## Related Topics

[Invoking Liferay Services in Your iOS App](/docs/7-1/tutorials/-/knowledge_base/t/invoking-liferay-services-in-your-ios-app)

[Building Mobile SDKs](/docs/7-1/tutorials/-/knowledge_base/t/building-mobile-sdks)

[Creating Android Apps that Use the Mobile SDK](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-apps-that-use-the-mobile-sdk)
