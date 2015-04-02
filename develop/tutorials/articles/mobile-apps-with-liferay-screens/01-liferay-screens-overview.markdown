# Liferay Screens Overview [](id=liferay-screens-overview)

Before using Screens, you should understand more about how it works. Screens has 
separate implementations for Android and iOS. Even so, care has been taken to 
ensure that the features on both platforms are largely the same. This tutorial 
describes those features. The basic implementation differences between Screens 
for Android and iOS are also presented. Read on to find out what Screens can 
bring to your apps!

## Screenlets [](id=screenlets)

Liferay Screens' power is based on *screenlets*. In Screens, a screenlet is a 
visual component that is connected to Liferay Portal's functionality. Screenlets 
use a specific UI to handle user input and present portal data. Behind the 
scenes, each screenlet uses one or more of [Liferay's remote services](/tutorials/-/knowledge_base/6-2/invoking-remote-services). 
Since [Screens' architecture](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens) 
is designed to isolate the screenlets, using Screens is a great way to construct 
your app in a modular fashion. When the screenlet needs to show information to 
the user, it relies on a *view* or a *theme*. Views are exclusive to Screens on 
Android, while themes are exclusive to Screens on iOS. Although named 
differently, views and themes serve the same purpose on their respective 
platforms. They let you implement a different look and feel for your app without 
changing its core functionality. What's more, views and themes are fully 
pluggable and can be contributed by third parties. This gives you a great deal 
of flexibility when implementing your app's UI and UX. 

Currently, the same screenlets are available in Liferay Screens for Android and 
iOS. Screenlets are grouped in modules based on internal dependencies. Each 
module is isolated, so you can use only the modules that are necessary for your 
app. However, it's important to note that you can't use a screenlet from a 
single module without using the entire module. The modules are listed here with 
their screenlets.

- *Auth*: The module for user authentication. It uses Liferay Portal's 
  [user management](/portal/-/knowledge_base/6-2/user-management) features. It 
  includes the following screenlets: 

	- `LoginScreenlet`: Gives your app the ability to log users in to a 
	  Liferay instance.
	- `SignUpScreenlet`: Gives your app the ability to create new user 
	  accounts in a Liferay instance.
	- `ForgotPasswordScreenlet`: Gives your app the ability to let users 
	  request a new password from a Liferay instance. 
	- `UserPortraitScreenlet`: Gives your app the ability to show the user's 
	  portrait picture from a Liferay instance.

- *Dynamic Data Lists (DDL)*: Module for interacting with [Dynamic Data Lists](/portal/-/knowledge_base/6-2/using-web-forms-and-dynamic-data-lists) 
  in a Liferay instance. It includes the following screenlets:

	- `DDLFormScreenlet`: Gives your app the ability to present dynamic 
	  forms to be filled out by users and submitted back to the server.
	- `DDLListScreenlet`: Gives your app the ability to show a list of 
	  records based on a pre-existing DDL in a Liferay instance.

Also, some screenlets can be used individually without the need to import an 
entire module. These include: 

- `AssetListScreenlet`: Shows a list of assets managed by 
  [Liferay's Asset Framework](/tutorials/-/knowledge_base/6-2/asset-framework). 
  This includes web content, blog entries, documents, users, and more. 
- `WebContentDisplayScreenlet`: Shows web content's HTML. This screenlet uses 
  the features avaiable in [Web Content Management](/portal/-/knowledge_base/6-2/web-content-management). 

Now that you know the screenlets shared by Screens for Android and iOS, you 
should learn some of the differences between the two implementations. 

## Liferay Screens for Android [](id=liferay-screens-for-android)

Liferay Screens for Android includes the component library (the screenlets) and 
a sample project. Screens for Android also uses Android's standard development 
tools, including the Android SDK and Android Studio. Android app development 
using Screens requires the following:

- Android Studio 1.0.2 or above.
- Android SDK 4.0 (API Level 14) or above. The SDK uses the [AppCompat library](https://developer.android.com/tools/support-library/features.html#v7-appcompat) 
  (v7:21.0.3) to offer a material look on older devices and the new 
  [recycler view](https://developer.android.com/tools/support-library/features.html#v7-recyclerview) 
  implementation.
- [Liferay Portal 6.2 CE or EE](http://www.liferay.com/downloads/liferay-portal/available-releases).
- [Liferay Screens' compatiblity plugin](https://github.com/liferay/liferay-screens/tree/master/portal). 
- Liferay Screens' source code. 

Other internal dependencies are: 

- [EventBus](https://github.com/greenrobot/EventBus) 
- [Picasso](http://square.github.io/picasso/) (used only for `UserPortraitScreenlet`)

Liferay Screens for Android also contains the following view sets that you can 
use to change the screenlets' UI and UX: 

- *Default views*: The standard views used when you add a screenlet in your 
app's activities or fragments and don't set the `liferay:layoutId` attribute. 
The Default views can be used as parent views for any of your custom views. 
Refer to the tutorial [Architecture of Liferay Screens](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens) 
for more information. 
- *Material views*: The sample views intended to demonstrate how to develop 
your own full view set from scratch. Refer to the tutorial 
[Creating Views and Themes](/tutorials/-/knowledge_base/6-2/creating-views-and-themes) 
for instructions on creating your own view set. 

## Liferay Screens for iOS [](id=liferay-screens-for-ios)

Liferay Screens for iOS includes the component library (the screenlets) and two 
sample projects. Your iOS app can be written in Swift or Objective-C. One sample 
project uses Screens with Objective-C. The second sample project uses Screens 
with Swift. The implementation of Screens for iOS uses standard iOS development 
tools, such as Xcode, the iOS SDK, the iOS Simulator, and others. Screens for 
iOS is developed using the new Swift language and modern development techniques, 
such as functional Swift code and the [Model View Presenter architecture](http://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter). 
However, it doesn't use the new iOS 8 APIs, so iOS apps developed with Screens 
can be run on devices with iOS 7 and above. Using Screens to develop iOS apps 
requires the following: 

- Xcode 6.1 or above
- iOS 8 SDK
- [CocoaPods](http://cocoapods.org) installed
- [Liferay Portal 6.2 CE or EE](http://www.liferay.com/downloads/liferay-portal/available-releases)
- [Liferay Screens' compatiblity plugin](https://github.com/liferay/liferay-screens/tree/master/portal)
- Liferay Screens' source code

Liferay Screens for iOS also contains the following themes that you can use to 
change the screenlets' UI and UX: 

- *Default*: The standard theme that is used when you insert any screenlet in 
your app's UI. The Default theme can be used as the parent for any of your 
custom themes. Refer to the tutorial [Architecture of Liferay Screens](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens) 
for more information. 
- *Flat7*: A sample theme intended to demonstrate how to develop your own full 
theme from scratch. Refer to the tutorial [Creating Views and Themes](/tutorials/-/knowledge_base/6-2/creating-views-and-themes) 
for instructions on creating your own themes. 

## Related Topics [](id=related-topics)

[Architecture of Liferay Screens](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens)

[Preparing Your Project for Liferay Screens](/tutorials/-/knowledge_base/6-2/preparing-your-project-for-liferay-screens)

[Using Screenlets](/tutorials/-/knowledge_base/6-2/using-screenlets)

[Using Views and Themes](/tutorials/-/knowledge_base/6-2/using-views-and-themes)

[Creating Screenlets](/tutorials/-/knowledge_base/6-2/creating-screenlets)

[Creating Views and Themes](/tutorials/-/knowledge_base/6-2/creating-views-and-themes)
