# Migrate to Liferay Screens 1.4

## Android SDK

* We recommend using 23 as the `compileSdkVersion` and `targetSdkVersion`, that means using 23.1.1 or later for android support libraries (appcompat, design...) like this:

```xml

compile 'com.android.support:appcompat-v7:23.+'


```

## API changes

### Renamed methods

* `onScreenletAttachted` has been renamed to `onScreenletAttached`, fixing the previous typo.
* `SessionContext.getLoggedUser()` has been renamed to `SessionContext.getCurrentUser()`.
* `processJSONNotification()` from AbstractPushService now throws a JSONException (instead of a general Exception)
* `getServerValue()` in Record class now returns an Object instead of a String. Cast the values accordingly.
* `credentialsStore` attribute in the `LoginScreenlet` has been renamed to `credentialsStorage`.

### Feature changes

* `DefaultTheme` has been deleted, along with `LiferayCrouton`. Both classes has been replaced by a better theming system, based on the default color names in the material specification (like primaryColor, secondaryColor...). Liferay Screens will detect those values and apply them to all the screenlets.