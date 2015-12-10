# Login Screenlet for iOS [](id=loginscreenlet-for-ios)

## Requirements [](id=requirements)

- XCode 6.4.x
- iOS 8 SDK
- Liferay Portal 6.2 CE or EE
- [OAuth Provider EE plugin](https://www.liferay.com/marketplace/-/mp/application/45261909) 
  (only for OAuth based authentication)

## Compatibility [](id=compatibility)

- iOS 7 and above

## Features [](id=features)

The `LoginScreenlet` authenticates portal users in your iOS app. The following
authentication methods are supported:

- Basic: uses user login and password according to 
  [HTTP Basic Access Authenication specification](http://tools.ietf.org/html/rfc2617). 
  Depending on the authentication method used by your Liferay instance, you need 
  to provide the user's email address, screen name, or user ID. You also need to 
  provide the user's password. 

- OAuth: implements the 
  [OAuth 1.0a specification](http://oauth.net/core/1.0a/).

When a user successfully authenticates, their attributes are retrieved for use 
in the app. You can use the `SessionContext` class to get the current user's 
attributes.

Note that user credentials and attributes can be stored securely in the keychain 
(see the `saveCredentials` attribute). Stored user credentials can be used to 
automatically log the user in to subsequent sessions. To do this, you can use 
the method `SessionContext.loadStoredCredentials()` method.

<iframe width="560" height="315" src="https://www.youtube.com/embed/XivMMLBqH9E" frameborder="0" allowfullscreen></iframe>

<iframe width="560" height="315" src="https://www.youtube.com/embed/JlQ8nWGFsyg" frameborder="0" allowfullscreen></iframe>

## Module [](id=module)

- Auth

## Themes [](id=themes)

- Default (`default`)
- Flat7 (`flat7`)

![The `LoginScreenlet` using the Default and Flat7 Themes.](../../images/screens-ios-login.png)

## Portal Configuration [](id=portal-configuration)

### Basic Authentication [](id=basic-authentication)

Before using `LoginScreenlet`, you should make sure your portal is configured 
with the authentication method you want to use. You can choose email address, 
screen name, or user ID. You can set this in the Control Panel by clicking 
*Portal Settings* &rarr; *Authentication*.

![Setting the authentication method in Liferay Portal.](../../images/screens-portal-auth.png)

For more details, please refer to the 
[Configuring Portal Settings](/portal/-/knowledge_base/6-2/configuring-portal-settings) 
section of the User Guide. 

### OAuth [](id=oauth)

<iframe width="560" height="315" src="https://www.youtube.com/embed/hg70gpUEUI0" frameborder="0" allowfullscreen></iframe>

<iframe width="560" height="315" src="https://www.youtube.com/embed/fo18U3SHhiI" frameborder="0" allowfullscreen></iframe>

If you want to use OAuth authentication, you first need to install the 
[OAuth Provider EE plugin](https://www.liferay.com/marketplace/-/mp/application/45261909) 
from Liferay's Marketplace. Once this plugin is installed, go to 
*Control Panel &rarr; Users &rarr; OAuth Admin*, and add a new application to be 
used from Liferay Screens. When the app is created, copy the *Consumer Key* and 
*Consumer Secret* values for later use in `LoginScreenlet`.

![Copy the Consumer Key and Consumer Secret from OAuth Admin in your portal.](../../images/screens-portal-oauth.png)

## Offline [](id=offline)

This Screenlet doesn't support offline mode. It requires network connectivity.
If you need to log in users automatically, even when there's no network 
connection, you can use the `saveCredentials` attribute together with the 
`SessionContext.loadStoredCredentials()` method. 

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `saveCredentials` | `boolean` | When set, the user credentials and attributes are stored securely in the keychain. This information can then be loaded in subsequent sessions by calling the `SessionContext.loadStoredCredentials()` method. |
| `companyId` | `number` | When set, authentication is done for a user in the specified company. If the value is `0`, the company specified in `LiferayServerContext` is used. |
| `basicAuthMethod` | `string` | Specifies the authentication method to use. This must match the authentication method configured on the server. You can set this attribute to `email`, `screenName` or `userId`. |
| `OAuthConsumerKey` | `string` | Specifies the *Consumer Key* to used in OAuth authentication. Leave this empty if you want to use Basic authentication. |
| `OAuthConsumerSecret` | `string` | Specifies the *Consumer Secret* to use in OAuth authentication. Leave this empty if you want to use Basic authentication. |

## Delegate [](id=delegate)

The `LoginScreenlet` delegates some events to an object that conforms to the 
`LoginScreenletDelegate` protocol. This protocol lets you implement the 
following methods:

- `- screenlet:onLoginResponseUserAttributes:`: Called when login successfully 
  completes. The user attributes are passed as a dictionary of keys (`String` or 
  `NSStrings`) and values (`AnyObject` or `NSObject`). The supported keys are 
  the same as the [portal's User entity](https://github.com/liferay/liferay-portal/blob/6.2.x/portal-impl/src/com/liferay/portal/service.xml#L2227).

- `- screenlet:onLoginResponseUserAttributes:`: Called when an error occurs 
  during login. The `NSError` object describes the error.

- `- onScreenletCredentialsSaved:`: Called when the user credentials are stored 
  after a successful login.

- `- onScreenletCredentialsLoaded:`: Called when the user credentials are 
  retrieved. Note that this only occurs when the Screenlet is used and stored 
  credentials are available.
