# Migrating to Liferay Screens 1.4 [](id=migrating-to-liferay-screens-1-4)

There are a few things you should be aware of when migrating your apps to 
Screens 1.4 from previous versions of Screens. This document provides a list of 
these items. 

## Android SDK [](id=android-sdk)

We recommend using 23 as your app's `compileSdkVersion` and `targetSdkVersion`. 
This means you should use 23.1.1 or later for any Android support libraries you 
require (AppCompat, Design, etc...). For example, the following `compile` 
statement in your app's `build.gradle` file adds the latest version of v7 
AppCompat 23 to your app: 

    compile 'com.android.support:appcompat-v7:23.+'

## Liferay Screens API Changes [](id=liferay-screens-api-changes)

### Renamed Methods [](id=renamed-methods)

- The `onScreenletAttachted` method has been renamed `onScreenletAttached`, 
  fixing the previous typo.
- The `SessionContext.getLoggedUser()` method has been renamed 
  `SessionContext.getCurrentUser()`. 
- The `processJSONNotification()` method from `AbstractPushService` now throws a 
  `JSONException` instead of a general `Exception`. 
- The `Record` class's `getServerValue()` method now returns an `Object` instead 
  of a `String`. Cast the values accordingly.
- The `credentialsStore` attribute in Login Screenlet has been renamed 
  `credentialsStorage`. 

### Feature Changes [](id=feature-changes)

- The classes `DefaultTheme` and `LiferayCrouton` have been deleted. Both have 
  been replaced by a better theming system, based on the default color names in 
  [Google's material design specification](https://www.google.com/design/spec/material-design/introduction.html#) 
  (like `primaryColor`, `secondaryColor`, and so on). Liferay Screens detects 
  these values and applies them to all the Screenlets. 
- Due to the new Web Content List Screenlet, Web Content Display Screenlet has 
  been moved from the package `com.liferay.mobile.screens.webcontentdisplay` to 
  `com.liferay.mobile.screens.webcontent.display`. 
