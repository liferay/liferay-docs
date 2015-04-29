# ForgotPasswordScreenlet for iOS [](id=forgotpasswordscreenlet-for-ios)

## Requirements [](id=requirements)

- XCode 6.3.x
- iOS 8 SDK
- Liferay Portal 6.2 CE or EE
- Mobile Widgets plugin installed

## Compatibility [](id=compatibility)

- iOS 7 and above

## Features [](id=features)

The `ForgotPasswordScreenlet` can send an email to a registered user with their 
new password or a password reset link, depending on the server configuration. 
The available authentication methods are:

- Email address
- Screen name
- User id

## Module [](id=module)

- Auth

## Themes [](id=themes)

- Default
- Flat7

![The `ForgotPasswordScreenlet` with the Default and Flat7 themes.](../../images/screens-ios-forgotpwd.png)

## Portal Configuration [](id=portal-configuration)

To use the `ForgotPasswordScreenlet`, you must allow users to request new 
passwords in the portal. The next sections show you how to do this.

### Authentication Method [](id=authentication-method)

Note that the authentication method configured in the portal can be different 
than the one used by this screenlet. For example, it's *perfectly fine* to use 
`screenName` for sign in authentication, but allow users to recover their 
password using the `email` authentication method.

### Password Reset [](id=password-reset)

Password recovery depends on the authentication settings in the portal:

![Checkboxes for the password recovery features in Liferay Portal.](../../images/screens-password-reset.png)

If both of these options are unchecked, then password recovery is disabled. If 
both options are checked, an email containing a password reset link is sent when 
a user requests it. If only the first option is checked, an email containing a 
new password is sent when a user requests it.

For more details on authentication in Liferay Portal, please refer to the 
[Configuring Portal Settings](/portal/-/knowledge_base/6-2/configuring-portal-settings) 
section of the User Guide.

### Anonymous Request [](id=anonymous-request)

An anonymous request can be done without the user being logged in. However, 
authentication is needed to call the API. To allow this operation, it's 
recommended that the portal administrator create a specific user with minimal 
permissions.

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `anonymousApiUserName` | `string` | The user name, email address, or userId (depending on the portal's authentication method) to use for authenticating the request. |
| `anonymousApiPassword` | `string` | The password to use to authenticate the request. |
| `companyId` | `number` | When set, the authentication is done for a user within the specified company. If the value is `0`, the company specified in `LiferayServerContext` is used. |
| `authMethod` | `string` | The authentication method that is presented to the user. This can be `email`, `screenName`, or `userId`. |

## Delegate [](id=delegate)

The `ForgotPasswordScreenlet` delegates some events to an object that conforms 
to the `ForgotPasswordScreenletDelegate` protocol. This protocol lets you 
implement the following methods: 

- `onForgotPasswordResponse(boolean)`: Called when a password reset email is 
  successfully sent. The Boolean parameter indicates whether the email contains 
  the new password or a password reset link.
- `onForgotPasswordError(error)`: Called when an error occurs in the process. 
  The `NSError` object describes the error.
