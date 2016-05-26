# Sign Up Screenlet for iOS [](id=signupscreenlet-for-ios)

## Requirements [](id=requirements)

- Xcode 7.3
- iOS 9 SDK
- Liferay Portal 6.2 (CE or EE), Liferay 7.0 CE, Liferay DXP 

## Compatibility [](id=compatibility)

- iOS 7 and above

## Features [](id=features)

The `SignUpScreenlet` creates a new user in your Liferay instance: a new user of
your app can become a new user in your portal. You can also use this Screenlet
to save the credentials of the new user in their keychain. This enables auto
login for future sessions. The Screenlet also supports navigation of form fields
from the keyboard of the user's device.

## Module [](id=module)

- Auth

## Themes [](id=themes)

- Default (`default`)
- Flat7 (`flat7`)

![The `SignUpScreenlet` with the Default and Flat7 Themes.](../../images/screens-ios-signup.png)

## Portal Configuration [](id=portal-configuration)

`SignUpScreenlet`'s corresponding configuration in the Liferay instance can be 
set in the Control Panel by selecting *Configuration* &rarr; *Instance 
Settings*, and then selecting the *Authentication* section. 

![The Liferay instance's authentication settings.](../../images/screens-portal-signup.png)

For more details, please refer to the 
[Setting up a Liferay Instance](/discover/portal/-/knowledge_base/7-0/setting-up-a-liferay-instance) 
section of the User Guide. 

## Anonymous Request [](id=anonymous-request)

Anonymous requests are unauthenticated requests. Authentication is needed,
however, to call the API. To allow this operation, the portal administrator
should create a specific user with minimal permissions.

## Offline [](id=offline)

This Screenlet doesn't support offline mode. It requires network connectivity.

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `anonymousApiUserName` | `string` | The user name, email address, or user ID (depending on the portal's authentication method) to use for authenticating the request. |
| `anoymousApiPassword` | `string` | The password for use in authenticating the request. |
| `companyId` | `number` | When set, authentication is done for a user in the specified company. If the value is `0`, the company specified in `LiferayServerContext` is used. |
| `autoLogin` | `boolean` | Whether the user is logged in automatically after a successful sign up. |
| `saveCredentials` | `boolean` | Sets whether or not the user's credentials and attributes are stored in the keychain after a successful log in. This attribute is ignored if `autologin` is disabled. |

## Delegate [](id=delegate)

The `SignUpScreenlet` delegates some events to an object that conforms to the 
`SignUpScreenletDelegate` protocol. If the `autologin` attribute is enabled, 
login events are delegated to an object conforming to the 
`LoginScreenletDelegate` protocol. Refer to the [`LoginScreenlet` documentation](LoginScreenlet.md) 
for more details.

The `SignUpScreenletDelegate` protocol lets you implement the following methods:

- `- screenlet:onSignUpResponseUserAttributes:`: Called when sign up 
  successfully completes. The user attributes are passed as a dictionary of keys 
  (`String` or `NSStrings`) and values (`AnyObject` or `NSObject`). The 
  supported keys are the same as [Liferay Portal's User entity](https://github.com/liferay/liferay-portal/blob/6.2.x/portal-impl/src/com/liferay/portal/service.xml#L2227).

- `- screenlet:onSignUpError:`: Called when an error occurs in the process. The 
  `NSError` object describes the error.
