# ForgotPasswordScreenlet for Android [](id=forgotpasswordscreenlet-for-android)

## Requirements [](id=requirements)

- Android SDK 4.0 (API Level 14) and above
- Liferay Portal 6.2 CE or EE
- Mobile Widgets plugin

## Compatibility [](id=compatibility)

- Android SDK 4.0 (API Level 14) and above

## Features [](id=features)

The `ForgotPasswordScreenlet` can send an email to a registered user with their 
new password or a password reset link, depending on the server configuration. 
The available authentication methods are:

- Email address
- Screen name
- User id

## Module [](id=module)

- Auth

## Views [](id=views)

- Default

![The `ForgotPasswordScreenlet` with the Default and Material viewsets.](../../images/screens-android-forgotpwd.png)

## Portal Configuration [](id=portal-configuration)

To use `ForgotPasswordScreenlet`, the portal must be configured to allow users 
to request new passwords. The next sections show you how to do this.

### Authentication Method [](id=authentication-method)

The authentication method configured in the portal can be different than the one 
used by this screenlet. For example, it's *perfectly fine* to use `screenName` 
for sign in authentication, but allow users to recover their password using the 
`email` authentication method.

### Password Reset [](id=password-reset)

Password recovery depends on the authentication settings in the portal:

![Checkboxes for the password recovery features in Liferay Portal.](../../images/screens-password-reset.png)

If these options are both unchecked, then password recovery is disabled. If both 
options are checked, an email containing a password reset link is sent when a 
user requests it. If only the first option is checked, an email containing a new 
password is sent when a user requests it.

For more details on authentication in Liferay Portal, please refer to the 
[Configuring Portal Settings](/portal/-/knowledge_base/6-2/configuring-portal-settings) 
section of the User Guide.

### Anonymous Request [](id=anonymous-request)

An anonymous request can be made without the user being logged in. However, 
authentication is needed to call the API. To allow this operation, it's 
recommended that the portal administrator create a specific user with minimal 
permissions. 

## Required Attributes

- `layoutId`
- `anonymousApiUserName`
- `anonymousApiPassword`

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `layoutId` | `@layout` | The layout used to show the view. |
| `anonymousApiUserName` | `string` | The user name, email address, or `userId`  to use for authenticating the request. This depends on the portal's authentication settings. |
| `anonymousApiPassword` | `string` | The password to use to authenticate the request. |
| `companyId` | `number` | When set, a user within the specified company is authenticated. If the value is set to `0`, the company specified in `LiferayServerContext` is used. |
| `authMethod` | `string` | The authentication method presented to the user. This can be `email`, `screenName`, or `userId`. The default value is `email`. |

## Listener [](id=listener)

The `ForgotPasswordScreenlet` delegates some events to an object that implements 
the `ForgotPasswordListener` interface. This interface lets you implement the 
following methods:

| Method | Explanation |
|-----------|-------------| 
|  <pre>onForgotPasswordRequestSuccess(boolean passwordSent)</pre> | Called when a password reset email is successfully sent. The boolean parameter determines whether the email contains the new password or a password reset link. |
|  <pre>onForgotPasswordRequestFailure(Exception e)</pre> | Called when an error occurs in the process. |
