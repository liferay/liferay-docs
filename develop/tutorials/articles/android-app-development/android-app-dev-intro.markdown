# Creating Mobile Apps that Use Liferay [](id=mobile-sdk-to-call-services-liferay-portal-6-2-dev-guide-en)

The Liferay Mobile SDK is a way to streamline consuming Liferay core web
services, Liferay utilities, and custom portlet web services. It wraps Liferay
JSON web services, making them easy to call in native mobile apps. It takes care
of authentication, makes HTTP requests (synchronously or asynchronously), parses
JSON results, and handles server-side exceptions so you can concentrate on
*using* the services in your app. 

The Liferay Mobile SDK is compatible with Liferay Portal 6.2 and later. The
Liferay Android SDK and Liferay iOS SDK are ready for you to download and
use. The Mobile SDK's official project
[page](https://www.liferay.com/community/liferay-projects/liferay-mobile-sdk/overview)
gives you access to the SDK releases, provides the latest SDK news, and has
forums for you to engage in mobile app development discussions.

![Figure 8.1: Liferay's Mobile SDK enables your native app to communicate with Liferay. You can even use the SDK from Eclipse via the Liferay Mobile SDK plugin..](../../images/mobile-sdk-diagram.png)

The Liferay Mobile SDK plugin for Eclipse simplifies developing Android apps that
use Liferay. You can configure the Mobile SDKs manually to use with Android apps
and/or iOS apps. Once configured, you can invoke Liferay services from your app.
The Liferay Mobile SDK bridges your native app with Liferay services. 

In this chapter, we'll demonstrate developing Android and iOS apps that
communicate with Liferay via the Mobile SDK. Along the way, we'll discuss these
topics: 

- Setting Up the Mobile SDK
- Creating the Liferay Android Sample Project
- Calling Liferay Services in your Android App
- Using Custom Services in your Android App
- Using the Android SDK
- Using the iOS SDK

Let's get started by accessing Liferay from an Android app in Eclipse.
