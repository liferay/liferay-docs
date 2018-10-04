# Forgot Password Screenlet for iOS [](id=forgotpasswordscreenlet-for-ios)

## Requirements [](id=requirements)

- Xcode 9.3 or above
- iOS 11 SDK
- Liferay Portal 6.2 CE/EE, Liferay CE Portal 7.0/7.1, Liferay DXP
- Liferay Screens Compatibility app
  ([CE](http://www.liferay.com/marketplace/-/mp/application/54365664) or 
  [EE/DXP](http://www.liferay.com/marketplace/-/mp/application/54369726)). 
  This app is preinstalled in Liferay CE Portal 7.0/7.1 and Liferay DXP. 

## Compatibility [](id=compatibility)

- iOS 9 and above

## Xamarin Requirements [](id=xamarin-requirements)

- Visual Studio 7.2
- Mono .NET framework 5.4.1.6

## Features [](id=features)

The Forgot Password Screenlet sends emails to registered users with their new 
passwords or password reset links, depending on the server configuration. The 
available authentication methods are:

- Email address
- Screen name
- User id

## JSON Services Used [](id=json-services-used)

Screenlets in Liferay Screens call JSON web services in the portal. This 
Screenlet calls the following services and methods.

| Service | Method | Notes |
| ------- | ------ | ----- |
| `UserService` | `sendPasswordByEmailAddress` |  |
| `UserService` | `sendPasswordByUserId` |  |
| `UserService` | `sendPasswordByScreenName` |  |

## Module [](id=module)

- Auth

## Themes [](id=themes)

- Default (`default`)
- Flat7 (`flat7`)

![The Forgot Password Screenlet with the Default and Flat7 Themes.](../../../images/screens-ios-forgotpwd.png)

## Portal Configuration [](id=portal-configuration)

To use the Forgot Password Screenlet, you must allow users to request new 
passwords in the portal. The next sections show you how to do this.

### Authentication Method [](id=authentication-method)

Note that the authentication method configured in the portal can be different 
from the one used by this Screenlet. For example, it's *perfectly fine* to use 
`screenName` for sign in authentication, but allow users to recover their 
password using the `email` authentication method.

### Password Reset [](id=password-reset)

You can set the Liferay instance's corresponding password reset options in the 
Control Panel by selecting *Configuration* &rarr; *Instance Settings*, and then 
selecting the *Authentication* section. The Screenlet's password functionality 
depends on the authentication settings in the portal: 

![Checkboxes for the password recovery features in Liferay Portal.](../../../images/screens-password-reset.png)

If both of these options are unchecked, password recovery is disabled. If both
options are checked, an email containing a password reset link is sent when a
user requests it. If only the first option is checked, an email containing a new
password is sent when a user requests it.

For more details, see the 
[Authentication](/discover/portal/-/knowledge_base/7-1/authentication) 
section of the User Guide. 

### Anonymous Request [](id=anonymous-request)

An anonymous request can be made without the user being logged in. However, 
authentication is needed to call the API. To allow this operation, the portal
administrator should create a specific user with minimal permissions.

## Offline [](id=offline)

This Screenlet doesn't support offline mode. It requires network connectivity.

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `anonymousApiUserName` | `string` | The user name, email address, or userId (depending on the portal's authentication method) to use for authenticating the request. |
| `anonymousApiPassword` | `string` | The password to use to authenticate the request. |
| `companyId` | `number` | When set, the authentication is done for a user within the specified company. If the value is `0`, the company specified in `LiferayServerContext` is used. |
| `basicAuthMethod` | `string` | The authentication method that is presented to the user. This can be `email`, `screenName`, or `userId`. |

## Delegate [](id=delegate)

The Forgot Password Screenlet delegates some events to an object that conforms 
to the `ForgotPasswordScreenletDelegate` protocol. This protocol lets you 
implement the following methods: 

- `- screenlet:onForgotPasswordSent:`: Called when a password reset email is 
  successfully sent. The Boolean parameter indicates whether the email contains 
  the new password or a password reset link.

- `- screenlet:onForgotPasswordError:`: Called when an error occurs in the 
  process. The `NSError` object describes the error.
