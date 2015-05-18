# Liferay Screens Overview [](id=liferay-screens-overview)

Before using Screens, you should understand its components and platform-specific
requirements, and what it brings to Android and iOS apps. Screens has separate
implementations for Android and iOS, but their features are largely the same.
Read on to find out what Screens brings to your apps!

## Screenlets [](id=screenlets)

Liferay Screens's power lies in its *screenlets*. A screenlet is a visual
component connected to Liferay Portal. Each screenlet uses one or more Liferay
[remote services](/tutorials/-/knowledge_base/6-2/invoking-remote-services).
Liferay Screens *views* or *themes* provide the UI and look and feel for
screenlets. They're completely separate from the app's core functionality.
Although views and themes serve the same purpose, views are exclusive to Screens
on Android and themes are exclusive to Screens on iOS. What's more, they're
fully pluggable to screenlets; a screenlet can leverage any view or theme. 

Screens lets you construct apps in modular fashion. Screenlets are independent,
but they're, in some cases, aggregated in modules based on common dependencies.
The Liferay Screens Component Library is comprised of these modules. Screenlets
that aren't packaged in modules require no import. 

Here are the Component Library's modules and screenlets:

- *Auth module*: Authenticates users via Liferay Portal
  [User Management](/portal/-/knowledge_base/6-2/user-management). It includes
  the following screenlets: 

	- `LoginScreenlet`: Logs in users.
	- `SignUpScreenlet`: Creates new user accounts.
	- `ForgotPasswordScreenlet`: Lets users request a new password. 
	- `UserPortraitScreenlet`: Show the user's profile picture.

- *Dynamic Data Lists (DDLs) module*: Interacts with the portal's
  [Dynamic Data Lists](/portal/-/knowledge_base/6-2/using-web-forms-and-dynamic-data-lists). 
  It includes the following screenlets:

	- `DDLFormScreenlet`: Displays dynamic forms for users to submit. 
	- `DDLListScreenlet`: Displays the portal's Dynamic Data Lists. 

- `AssetListScreenlet`: Lists portal assets, including web content, blogs
  entries, documents, users, etc., managed by Liferay's 
  [Asset Framework](/tutorials/-/knowledge_base/6-2/asset-framework). 

- `WebContentDisplayScreenlet`: Displays portal
  [web content](/portal/-/knowledge_base/6-2/web-content-management). 

Now that you're familiar with the Component Library's modules and screenlets,
you can focus on each Screens implementation's requirements and features.
Liferay Screens for Android is next. 

## Liferay Screens for Android [](id=liferay-screens-for-android)

Liferay Screens for Android includes the Component Library (the screenlets) and 
a sample project. It requires the following software:

- Android Studio 1.0.2 or above.
- Android SDK 4.0 (API Level 14) or above. Its [AppCompat library](https://developer.android.com/tools/support-library/features.html#v7-appcompat) 
  (v7:21.0.3) implements the
  [recycler view](https://developer.android.com/tools/support-library/features.html#v7-recyclerview) 
  and offers older devices a material look and feel.
- [Liferay Portal 6.2 CE or EE](http://www.liferay.com/downloads/liferay-portal/available-releases).
- [Liferay Screens Compatiblity Plugin](https://github.com/liferay/liferay-screens/tree/master/portal). 
- Liferay Screens source code. 

Liferay Screens for Android uses
[EventBus](https://github.com/greenrobot/EventBus) internally and 
`UserPortraitScreenlet` uses [Picasso](http://square.github.io/picasso/).

Screens for Android comes with the following view sets: 

- *Default views*: Each standard view adds a screenlet in your app's activities
  or fragments. Custom views can extend the standard views. See
  [Architecture of Liferay Screens for Android](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android) 
  for details. 
- *Material views*: They're sample views built from scratch. You can refer to
  them as you [create views](/tutorials/-/knowledge_base/6-2/creating-views-in-liferay-screens-for-android). 

Check out
[Related Topics](/tutorials/-/knowledge_base/6-2/liferay-screens-overview#related-topics)
for tutorials on developing Android apps with Screens. 

## Liferay Screens for iOS [](id=liferay-screens-for-ios)

Liferay Screens for iOS includes the Component Library (the screenlets) and two
sample projects: one uses Objective-C and the other Swift. It was developed
using Swift and development techniques that leverage functional Swift code and
the
[Model View Presenter](http://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter)
architecture. You can use Swift or Objective-C with Screens, and you can run
Screens apps on iOS 7 and above--Screens doesn't use iOS 8 APIs. 

Liferay Screens for iOS requires the following software: 

- Xcode 6.3.x
- iOS 8 SDK
- [CocoaPods](http://cocoapods.org) (installed)
- [Liferay Portal 6.2 CE or EE](http://www.liferay.com/downloads/liferay-portal/available-releases)
- [Liferay Screens Compatiblity Plugin](https://github.com/liferay/liferay-screens/tree/master/portal)
- Liferay Screens source code

Liferay Screens for iOS comes with the following themes: 

- *Default*: The standard theme inserts a screenlet in your app's UI. Custom
  themes can extend the theme. See 
  [Architecture of Liferay Screens for iOS](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-ios)
  for details. 
- *Flat7*:  This sample theme was built from scratch. You can refer to it as you
  [create](/tutorials/-/knowledge_base/6-2/creating-themes-in-liferay-screens-for-ios)
  themes. 

Now that you understand the basic concepts of screens, screenlets, views, and
themes, you dig into Screens architecture or integrate Liferay Screens into your
project. 

## Related Topics [](id=related-topics)

[Architecture of Liferay Screens for Android](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android)

[Architecture of Liferay Screens for iOS](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-ios)

[Preparing Your Android Project for Liferay Screens](/tutorials/-/knowledge_base/6-2/preparing-your-android-project-for-liferay-screens)

[Preparing Your iOS Project for Liferay Screens](/tutorials/-/knowledge_base/6-2/preparing-your-ios-project-for-liferay-screens)

[Using Screenlets in Your Android Project](/tutorials/-/knowledge_base/6-2/using-screenlets-in-your-android-project)

[Using Screenlets in Your iOS Project](/tutorials/-/knowledge_base/6-2/using-screenlets-in-your-ios-project)

[Using Views in Liferay Screens for Android](/tutorials/-/knowledge_base/6-2/using-views-in-liferay-screens-for-android)

[Using Themes in Liferay Screens for iOS](/tutorials/-/knowledge_base/6-2/using-themes-in-liferay-screens-for-ios)

[Creating Screenlets in Liferay Screens for Android](/tutorials/-/knowledge_base/6-2/creating-screenlets-in-liferay-screens-for-android)

[Creating Screenlets in Liferay Screens for iOS](/tutorials/-/knowledge_base/6-2/creating-screenlets-in-liferay-screens-for-ios)

[Creating Views in Liferay Screens for Android](/tutorials/-/knowledge_base/6-2/creating-views-in-liferay-screens-for-android)

[Creating Themes in Liferay Screens for iOS](/tutorials/-/knowledge_base/6-2/creating-themes-in-liferay-screens-for-ios)
