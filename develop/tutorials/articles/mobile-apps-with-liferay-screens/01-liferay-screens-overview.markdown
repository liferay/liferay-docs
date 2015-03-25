# Liferay Screens Overview

Before using Screens, you should understand more about how it works. Screens has 
separate implementations for Android and iOS. Even so, care has been taken to 
ensure that the same features are availble on both platforms. This tutorial 
describes these features. The basic differences in implementation between 
Screens for Android and iOS are also presented. 
<!-- Replace links with links to other Screens tutorials, once they exist -->

## Available Screenlets

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

Screens for Android uses Android's standard set of development tools, including 
the Android SDK (starting from Android 4.0, API Level 14) and Android Studio. 
Since [Screens' architecture](https://www.liferay.com/) is designed 
to isolate the screenlets, using Screens is a great way to construct your app in 
a modular fashion. 

Liferay Screens for Android also contains a set of *views* that you can use to 
change the UI and UX of the screenlets: 

- **Default views**: The standard views used when you add a screenlet in your 
app's activities or fragments and don't set the `liferay:layoutId` attribute. 
The Default views can be used as parent views for any of your custom views. 
Refer to the [Architecture Guide](https://www.liferay.com/) 
for more details.
- **Material views**: The sample views intended to demonstrate how to develop 
your own full view set from scratch. Refer to the [Views Guide](https://www.liferay.com/) 
for instructions on creating your own view set. 

## Liferay Screens for iOS

The implementation of Screens for iOS devices uses standard iOS development 
tools, such as Xcode, the iOS SDK, the iOS Simulator, and others. Screens is developed 
using the new Swift language and modern development techniques, such as 
functional Swift code and the [Model View Presenter architecture](http://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter). 
This makes Screens a great way to construct your app in a modular way.

You can also check out the [Showcase app](ios/Samples/README.md) to learn how to 
configure and use the above screenlets.

Liferay Screens for iOS also contains a set of *themes* that you can use to 
change the UI and UX of the screenlets: 

- **Default**: The standard theme that is used when you insert any screenlet in 
your app's UI. The Default theme can be used as the parent theme for any of your 
custom themes. Please refer to the [Architecture Guide](ios/Documentation/architecture.md#theme-layer) 
for more details on this.
- **Flat7**: A sample theme intended to demonstrate how to develop your own full 
theme from scratch. Please refer to the [Theme Guide](ios/Documentation/themes.md) 
for instructions on creating your own theme.

## Related Topics

[Architecture of Liferay Screens](https://www.liferay.com/)

[Preparing Your Project for Liferay Screens](https://www.liferay.com/)
