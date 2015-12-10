# Sign Up Screenlet for Android [](id=signupscreenlet-for-android)

## Requirements [](id=requirements)

- Android SDK 4.0 (API Level 15) or above
- Liferay Portal 6.2 CE or EE

## Compatibility [](id=compatibility)

- Android SDK 4.0 (API Level 15) and above

## Features [](id=features)

The `SignUpScreenlet` creates a new user in your Liferay instance: a new user of
your app can become a new user in your portal. You can also use this Screenlet
to save new users' credentials on their devices. This enables auto login for
future sessions. The Screenlet also supports navigation of form fields from the
device's keyboard.

## Module [](id=module)

- Auth

## Views [](id=views)

- Default

![The `SignUpScreenlet` with the Default and Material Viewsets.](../../images/screens-android-signup.png)

## Portal Configuration [](id=portal-configuration)

`SignUpScreenlet`'s portal configuration can be set in the Control Panel by
clicking *Portal Settings* and then *Authentication*. 

![The portal's authentication settings.](../../images/screens-portal-signup.png)

For more details, refer to the [Configuring Portal Settings](/portal/-/knowledge_base/6-2/configuring-portal-settings) 
section of the User Guide.

## Anonymous Requests [](id=anonymous-requests)

Anonymous requests are unauthenticated requests. Authentication is
still required, however, to call the API. To allow this operation, the portal
administrator should create a user with minimal permissions. To use
`SignUpScreenlet`, you need to use that user in your layout. You should add that
user's credentials to `server_context.xml`. 

## Offline [](id=offline)

This Screenlet doesn't support offline mode. It requires network connectivity. 

## Required Attributes [](id=required-attributes)

- `layoutId`
- `anonymousApiUserName`
- `anonymousApiPassword`

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `layoutId` | `@layout` | The layout used to show the View.|
| `anonymousApiUserName` | `string` | The user's name, email address, or ID to
use for authenticating the request. The portal's authentication method defines which of these is used. |
| `anoymousApiPassword` | `string` | The password used to authenticate the request. |
| `companyId` | `number` | When set, a user in the specified company is authenticated. If set to `0`, the company specified in `LiferayServerContext` is used. |
| `autoLogin` | `boolean` | Sets whether the user is logged in automatically after a successful sign up. |
| `credentialsStorage` | `enum` | Sets the mode for storing user credentials. The possible values are `none`, `auto`, and `shared_preferences`. If set to `shared_preferences`, the user credentials and attributes are stored using Android's `SharedPreferences` class. If set to `none`, user credentials and attributes aren't saved at all. If set to `auto`, the best of the available storage modes is used. Currently, this is equivalent to `shared_preferences`. The default value is `none`. |

## Listener [](id=listener)

The `LoginScreenlet` delegates some events to an object that implements the 
`LoginListener` interface. This interface lets you implement the following 
methods:

- `onSignUpSuccess(User user)`: Called when sign up successfully completes. The 
  `user` parameter contains a set of the created user's attributes. The 
  supported keys are the same as those in the [portal's User entity](https://github.com/liferay/liferay-portal/blob/6.2.x/portal-impl/src/com/liferay/portal/service.xml#L2227).

- `onSignUpFailure(Exception e)`: Called when an error occurs in the process.
