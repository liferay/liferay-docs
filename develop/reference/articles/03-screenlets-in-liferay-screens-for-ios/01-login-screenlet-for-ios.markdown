# LoginScreenlet for iOS [](id=loginscreenlet-for-ios)

## Requirements [](id=requirements)

- XCode 6.3.x
- iOS 8 SDK
- Liferay Portal 6.2 CE or EE

## Compatibility [](id=compatibility)

- iOS 7 and above

## Features [](id=features)

The `LoginScreenlet` lets you authenticate portal users in your iOS app. The 
following authentication methods are supported:

- Email address
- Screen name
- User id

When the user is successfully authenticated, their attributes are retrieved for 
use in the app. You can use the `SessionContext` class to get current user 
attributes. It's important to note that user credentials and attributes can be 
stored securely in the keychain. This information can then be used to perform 
automatic login in subsequent sessions (see the `saveCredentials` attribute).

## Module [](id=module)

- Auth

## Themes [](id=themes)

- Default
- Flat7

![The `LoginScreenlet` using the Default and Flat7 themes.](../../images/screens-ios-login.png)

## Portal Configuration [](id=portal-configuration)

The authentication method should be configured in your Liferay instance. You can 
set this in the Control Panel by clicking *Portal Settings* and then 
*Authentication*.

![Setting the authentication method in Liferay Portal.](../../images/screens-portal-auth.png)

For more details, please refer to the [Configuring Portal Settings](/portal/-/knowledge_base/6-2/configuring-portal-settings) 
section of the User Guide. 

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
|  `saveCredentials` | `boolean` | When set, the user credentials and attributes are stored securely in the keychain. This information can then be loaded in subsequent sessions by calling the `SessionContext.loadSessionFromStore()` method. |
|  `companyId` | `number` | When set, authentication is done for a user in the specified company. If the value is `0`, the company specified in `LiferayServerContext` is used. |
|  `authMethod` | `string` | Specifies the authentication method to use. This must match the authentication method configured on the server. You can set this attribute to `email`, `screenName` or `userId`. |

## Delegate [](id=delegate)

The `LoginScreenlet` delegates some events to an object that conforms to the 
`LoginScreenletDelegate` protocol. This protocol lets you implement the 
following methods:

- `onLoginResponse(dictionary)`: Called when login successfully completes. The 
  user attributes are passed as a dictionary of keys (`NSStrings`) and values 
  (`NSObject`). The supported keys are the same as the [portal's User entity](https://github.com/liferay/liferay-portal/blob/6.2.x/portal-impl/src/com/liferay/portal/service.xml#L2227).
- `onLoginError(error)`: Called when an error occurs during login. The `NSError` 
  object describes the error.
- `onCredentialsSaved`: Called when the user credentials are stored after a 
  successful login.
- `onCredentialsLoaded`: Called when the user credentials are retrieved. Note 
  that this only occurs when the screenlet is used and stored credentials are 
  available.
