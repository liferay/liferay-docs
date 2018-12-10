# Sign Up Screenlet for Android [](id=signupscreenlet-for-android)

## Requirements [](id=requirements)

- Android SDK 4.1 (API Level 16) or above
- Liferay Portal 6.2 CE/EE, Liferay CE Portal 7.0/7.1, Liferay DXP

## Compatibility [](id=compatibility)

- Android SDK 4.1 (API Level 16) or above

## Xamarin Requirements [](id=xamarin-requirements)

- Visual Studio 7.2
- Mono .NET framework 5.4.1.6

## Features [](id=features)

The Sign Up Screenlet creates a new user in your Liferay instance: a new user of
your app can become a new user in your portal. You can also use this Screenlet
to save new users' credentials on their devices. This enables auto login for
future sessions. The Screenlet also supports navigation of form fields from the
device's keyboard. 

## JSON Services Used [](id=json-services-used)

Screenlets in Liferay Screens call JSON web services in the portal. This 
Screenlet calls the following services and methods.

| Service | Method | Notes | 
| ------- | ------ | ----- |
| `UserService` | `addUser` |  |

## Module [](id=module)

- Auth

## Views [](id=views)

- Default
- Material

![The Sign Up Screenlet with the Default (left) and Material (right) Viewsets.](../../../images/screens-android-signup.png)

## Portal Configuration [](id=portal-configuration)

Sign Up Screenlet's corresponding configuration in the Liferay instance can be 
set in the Control Panel by selecting *Configuration* &rarr; *Instance 
Settings*, and then selecting the *Authentication* section. 

![The Liferay instance's authentication settings.](../../../images/screens-portal-signup.png)

For more details, see the 
[Authentication](/discover/portal/-/knowledge_base/7-1/authentication) 
section of the User Guide. 

## Anonymous Requests [](id=anonymous-requests)

Anonymous requests are unauthenticated requests. Authentication is
still required, however, to call the API. To allow this operation, the portal
administrator should create a user with minimal permissions. To use
Sign Up Screenlet, you need to use that user in your layout. You should add that
user's credentials to `server_context.xml`. 

## Offline [](id=offline)

This Screenlet doesn't support offline mode. It requires network connectivity. 

## Required Attributes [](id=required-attributes)

- `anonymousApiUserName`
- `anonymousApiPassword`

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `layoutId` | `@layout` | The layout used to show the View.|
| `anonymousApiUserName` | `string` | The user's name, email address, or ID to use for authenticating the request. The portal's authentication method defines which of these is used. |
| `anoymousApiPassword` | `string` | The password used to authenticate the request. |
| `companyId` | `number` | When set, a user in the specified company is authenticated. If not set, the company specified in `LiferayServerContext` is used. |
| `autoLogin` | `boolean` | Sets whether the user is logged in automatically after a successful sign up. |
| `credentialsStorage` | `enum` | Sets the mode for storing user credentials. The possible values are `none`, `auto`, and `shared_preferences`. If set to `shared_preferences`, the user credentials and attributes are stored using Android's `SharedPreferences` class. If set to `none`, user credentials and attributes aren't saved at all. If set to `auto`, the best of the available storage modes is used. Currently, this is equivalent to `shared_preferences`. The default value is `none`. |
|`basicAuthMethod`|`enum`| Specifies the authentication method to use after a successful sign up. This must match the authentication method configured on the server. You can set this attribute to `email`, `screenName` or `userId`. The default value is `email`. |

## Listener [](id=listener)

The Sign Up Screenlet delegates some events to an object that implements the 
`SignUpListener` interface. This interface lets you implement the following 
methods: 

- `onSignUpSuccess(User user)`: Called when sign up successfully completes. The 
  `user` parameter contains a set of the created user's attributes, as defined 
  in the 
  [portal's `User` entity](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/User.html). 

- `onSignUpFailure(Exception e)`: Called when an error occurs in the process.
