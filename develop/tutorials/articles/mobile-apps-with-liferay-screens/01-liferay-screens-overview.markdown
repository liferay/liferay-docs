# Liferay Screens Overview

Before using Screens, you should understand more about how it works. Screens has 
separate implementations for Android and iOS. Even so, care has been taken to 
ensure that the same features are availble on both platforms. This tutorial 
describes those features. The basic implementation differences between Screens 
for Android and iOS are also presented. 
<!-- Replace links with links to other Screens tutorials, once they exist -->

## Screenlets

In Liferay Screens, a *screenlet* is a visual component that is connected to 
Liferay Portal's functionality. Screenlets use a specific UI to handle input 
from the user and present information from the portal. This enables your app to 
receive user input, handle communication with the server, and present the user 
with the results. Each screenlet is tied to one or more of 
[Liferay's remote services](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/accessing-services-remotely-liferay-portal-6-2-dev-guide-05-en). 
Since [Screens' architecture](https://www.liferay.com/) is designed to isolate 
the screenlets, using Screens is a great way to construct your app in a modular 
fashion. When the screenlet needs to show information to the user, it relies on 
a *view* or a *theme*. Views are exclusive to Screens on Android, while themes 
are exclusive to Screens on iOS. Views and themes serve the same purpose on 
their respective platforms. They let you implement a different look and feel for 
your app, without changing its core functionality. What's more, themes and views 
are fully pluggable and can be contributed by third parties. This gives you a 
great deal of flexibility when styling your UI.

The same screenlets are available in Liferay Screens for Android and iOS. 
[Once your project is ready for Screens](https://www.liferay.com/), you can use 
the following screenlets in your app: 

- `LoginScreenlet`: Sign users in to a Liferay installation.
- `SignUpScreenlet`: Register new portal users with a Liferay installation.
- `ForgotPasswordScreenlet`: Send emails containing a new password or password 
  reset link to users.
- `UserPortraitScreenlet`: Show the user's portrait picture.
- `DDLFormScreenlet`: Present dynamic forms to be filled out by users and 
  submitted back to the portal.
- `DDLListScreenlet`: Show a list of records based on a pre-existing 
  [Dynamic Data List](/portal/-/knowledge_base/6-2/using-web-forms-and-dynamic-data-lists) 
  in a Liferay installation.
- `AssetListScreenlet`: Shows a list of assets managed by 
  [Liferay's Asset Framework](/tutorials/-/knowledge_base/6-2/asset-framework). 
  Assets include web content, blog entries, documents, users, and more.
- `WebContentDisplayScreenlet`: Shows web content's HTMLt. This screenlet uses 
  the features available in [Web Content Management](/portal/-/knowledge_base/6-2/web-content-management). 

## Liferay Screens for Android

Liferay Screens for Android includes the component library (the screenlets) and 
a sample project. Screens for Android also uses Android's standard set of 
development tools, including the Android SDK and Android Studio. Android app 
development using Screens requires the following:

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
- [Picasso](http://square.github.io/picasso/)

Liferay Screens for Android also contains the following view sets that you can 
use to change the screenlets' UI and UX: 

- **Default views**: The standard views used when you add a screenlet in your 
app's activities or fragments and don't set the `liferay:layoutId` attribute. 
The Default views can be used as parent views for any of your custom views. 
Refer to the [Architecture Guide](https://www.liferay.com/) 
for more details.
- **Material views**: The sample views intended to demonstrate how to develop 
your own full view set from scratch. Refer to the [Views Guide](https://www.liferay.com/) 
for instructions on creating your own view set. 

## Liferay Screens for iOS

Liferay Screens for iOS includes the component library (the screenlets) and two 
sample projects. One sample project uses Screens with Objective-C. The second 
sample project uses Screens with Swift. Your iOS app can be written in Swift or 
Objective-C. The implementation of Screens for iOS uses standard iOS development 
tools, such as Xcode, the iOS SDK, the iOS Simulator, and others. Screens for 
iOS is developed using the new Swift language and modern development techniques, 
such as functional Swift code and the [Model View Presenter architecture](http://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter). 
However, it doesn't use the new iOS 8 APIs, so it can be run only on devices 
with iOS 7 and above. Also, iOS app development using Screens requires the 
following: 

- Xcode 6.1 or above
- iOS 8 SDK
- [CocoaPods](http://cocoapods.org) installed
- [Liferay Portal 6.2 CE or EE](http://www.liferay.com/downloads/liferay-portal/available-releases)
- [Liferay Screens' compatiblity plugin](https://github.com/liferay/liferay-screens/tree/master/portal). 
- Liferay Screens' source code

Liferay Screens for iOS also contains the following themes that you can use to 
change the screenlets' UI and UX: 

- **Default**: The standard theme that is used when you insert any screenlet in 
your app's UI. The Default theme can be used as the parent theme for any of your 
custom themes. Please refer to the [Architecture Guide](ios/Documentation/architecture.md#theme-layer) 
for more details on this.
- **Flat7**: A sample theme intended to demonstrate how to develop your own full 
theme from scratch. Please refer to the [Theme Guide](ios/Documentation/themes.md) 
for instructions on creating your own theme.

You should note that themes provided in early versions of Screens for iOS 
support only vertical screen orientation and the iPhone 5, 5s, and 5c screen 
size. Support for the full range of screen orientations and sizes will be added 
in the future. 

## Related Topics

[Architecture of Liferay Screens](https://www.liferay.com/)

[Preparing Your Project for Liferay Screens](https://www.liferay.com/)
