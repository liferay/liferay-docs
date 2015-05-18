# LoginScreenlet for Android [](id=loginscreenlet-for-android)

## Requirements [](id=requirements)

- Android SDK 4.0 (API Level 14) and above
- Liferay Portal 6.2 CE or EE
- Mobile Widgets plugin

## Compatibility [](id=compatibility)

- Android SDK 4.0 (API Level 14) and above

## Features [](id=features)

The `LoginScreenlet` lets you authenticate portal users in your Android app. The 
following authentication methods are supported:

- Email address
- Screen name
- User ID

When a user successfully authenticates, their attributes are retrieved for use 
in the app. It's important to note that user credentials and attributes can be 
stored in an app's data store. Android's `SharedPreferences` is currently the 
only data store implemented. However, new and more secure data stores will be 
added in the future. Stored user credentials can be used to perform automatic 
login in subsequent sessions.

## Module [](id=module)

- Auth

## Views [](id=views)

- Default

![The `LoginScreenlet` using the Default and Material viewsets.](../../images/screens-android-login.png)

## Portal Configuration [](id=portal-configuration)

Before using `LoginScreenlet`, you should make sure your portal is configured 
with the authentication method you want to use. You can set this in the Control 
Panel by clicking *Portal Settings* &rarr; *Authentication*.

![Setting the authentication method in Liferay Portal.](../../images/screens-portal-auth.png)

For more details, see the [Configuring Portal Settings](/portal/-/knowledge_base/6-2/configuring-portal-settings) 
section of the User Guide. 

## Required Attributes

- `layoutId`

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `layoutId` | `@layout` | The layout to use to show the view. |
| `companyId` | `number` | When set, a user in the specified company is authenticated. If set to `0`, the company specified in `LiferayServerContext` is used. |
| `authMethod` | `string` | Specifies the authentication method to use. This must match the authentication method configured on the server. You can set this attribute to `email`, `screenName` or `userId`. The default value is `email`. |
| `credentialsStore ` | `enum` | Sets the mode for storing user credentials. The posible values are `none`, `auto`, and `shared_preferences`. If set to `shared_preferences`, the user credentials and attributes are stored using Android's `SharedPreferences` class. If set to `none`, user credentials and attributes aren't saved at all. If set to `auto`, the best of the available storage modes is used. Currently, this is equivalent to `shared_preferences`. The default value is `none`. |

## Listener [](id=listener)

The `LoginScreenlet` delegates some events to an object that implements the 
`LoginListener` interface. This interface let you implement the following 
methods:

| Method | Explanation |
|-----------|-------------| 
|  <pre>onLoginSuccess(User user)</pre> | Called when login successfully completes. The `user` parameter contains a set of the logged in user's attributes. The supported keys are the same as those in the [portal's User entity](https://github.com/liferay/liferay-portal/blob/6.2.x/portal-impl/src/com/liferay/portal/service.xml#L2227)|
|  <pre>onLoginFailure(Exception e)</pre> | Called when an error occurs in the process. |
