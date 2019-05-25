---
header-id: creating-android-apps-that-use-liferay
---

# Creating Android Apps that Use Liferay

[TOC levels=1-4]

The Liferay Mobile SDK provides a way to streamline consuming Liferay core web 
services, Liferay utilities, and custom portlet web services. It wraps Liferay
JSON web services, making them easy to call in native mobile apps. It takes care
of authentication, makes HTTP requests (synchronously or asynchronously), parses
JSON results, and handles server-side exceptions so you can concentrate on
*using* the services in your app. 

The Liferay Mobile SDK is compatible with Liferay Portal 6.2 and later. It comes
with the Liferay Android SDK and is ready for you to download and use. The
official project 
[page](https://www.liferay.com/community/liferay-projects/liferay-mobile-sdk/overview)
gives you access to the SDK releases, provides the latest SDK news, and has
forums for you to engage in mobile app development discussions. Once you 
configure the Mobile SDK in your app, you can invoke Liferay services in it. The 
Liferay Android SDK bridges your native app with Liferay services. 

![Figure 1: Liferay's Mobile SDK enables your native app to communicate with Liferay.](../../images/mobile-sdk-diagram.png)

The Android app development tutorials include the following topics: 

- Making Liferay and Custom Portlet Services Available in Your Android App
- Invoking Liferay Services in Your Android App
- Invoking Services Asynchronously from Your Android App
- Sending Your Android App's Requests Using Batch Processing

A great way to start is by setting up the Mobile SDK your Android project. This 
makes Liferay and custom portlet services available in your app.

## Related Topics

[Invoking Liferay Services in Your Android App](/docs/6-2/tutorials/-/knowledge_base/t/invoking-liferay-services-in-your-android-app)

[Creating iOS Apps that Use Liferay](/docs/6-2/tutorials/-/knowledge_base/t/creating-ios-apps-that-use-liferay)

[Building Mobile SDKs](/docs/6-2/tutorials/-/knowledge_base/t/building-mobile-sdks)

[Service Builder and Services](/docs/6-2/tutorials/-/knowledge_base/t/service-builder)
