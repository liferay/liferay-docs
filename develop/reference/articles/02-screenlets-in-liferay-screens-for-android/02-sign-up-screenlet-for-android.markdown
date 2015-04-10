# SignUpScreenlet for Android [](id=signupscreenlet-for-android)

## Requirements [](id=requirements)

- Android SDK 4.0 (API Level 14) and above
- Liferay Portal 6.2 CE or EE
- Mobile Widgets plugin

## Compatibility [](id=compatibility)

- Android SDK 4.0 (API Level 14) and above

## Features [](id=features)

The main function of the `SignUpScreenlet` is to create a new user in your 
Liferay instance. For example, by using `SignUpScreenlet` a new user of your app 
can become a new user in your portal. You can also use this screenlet to save 
the new user's credentials on their device. This enables auto login for future 
sessions. The screenlet also supports navigation of form fields from the 
keyboard of the user's device.

## Module [](id=module)

- Auth

## Views [](id=views)

- Default

![The `SignUpScreenlet` with the Default and Material viewsets.](../../images/screens-android-signup.png)

## Portal Configuration [](id=portal-configuration)

The portal configuration related to `SignUpScreenlet` can be set in the Control 
Panel by clicking *Portal Settings* and then *Authentication*. These settings 
are shown in the following screenshot:

![The portal's authentication settings.](../../images/screens-portal-signup.png)

For more details, refer to the [Configuring Portal Settings](/portal/-/knowledge_base/6-2/configuring-portal-settings) 
section of the User Guide.

## Anonymous Requests [](id=anonymous-requests)

Anonymous requests can be done without the user being logged in. However, 
authentication is still required to call the API. To allow this operation, it's 
recommended that the portal administrator create a specific user with minimal 
permissions.

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `layoutId` | `@layout` | The layout used to show the view.|
| `anonymousApiUserName` | `string` | The user's name, email address, or ID to use for authenticating the request. Which of these is used depends on the portal's authentication method. |
| `anoymousApiPassword` | `string` | The password to authenticate the request with. |
| `companyId` | `number` | When set, a user in the specified company is authenticated. If set to `0`, the company specified in `LiferayServerContext` is used. |
| `autoLogin` | `boolean` | Sets whether the user is logged in automatically after a successful sign up. |
| `credentialsStore` | `enum` | Sets the mode for storing user credentials. The posible values are `none`, `auto`, and `shared_preferences`. If set to `shared_preferences`, the user credentials and attributes are stored using Android's `SharedPreferences` class. If set to `none`, user credentials and attributes aren't saved at all. If set to `auto`, the best of the available storage modes is used. Currently, this is equivalent to `shared_preferences`. The default value is `none`. |

## Listener [](id=listener)

The `LoginScreenlet` delegates some events to an object that implements the 
`LoginListener` interface. This interface lets you implement the following 
methods:

| Method | Explanation |
|-----------|-------------| 
|  <pre>onSignUpSuccess(User user)</pre> | Called when sign up successfully completes. The `user` parameter contains a set of the created user's attributes. The supported keys are the same as those in the [portal's User entity](https://github.com/liferay/liferay-portal/blob/6.2.x/portal-impl/src/com/liferay/portal/service.xml#L2227) |
|  <pre>onSignUpFailure(Exception e)</pre> | Called when an error occurs in the process. |
