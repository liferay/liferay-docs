# LoginScreenlet for iOS [](id=loginscreenlet-for-ios)

## Requirements [](id=requirements)

- XCode 6.3.x
- iOS 8 SDK
- Liferay Portal 6.2 CE or EE
- [OAuth Provider EE plugin](https://www.liferay.com/marketplace/-/mp/application/45261909) (only for OAuth based authentication)

## Compatibility [](id=compatibility)

- iOS 7 and above

## Features [](id=features)

The `LoginScreenlet` authenticates portal users in your iOS app. The following
types of authentication are supported:

- Basic: uses user login and password according to [HTTP Basic Access Authenication specification](http://tools.ietf.org/html/rfc2617). For this type of authentication, you have to choose between three types of authentication methods, depending on your portal configuration:
	- Email address
	- Screen name
	- User id

- OAuth: implementing [OAuth 1.0a specification](http://oauth.net/core/1.0a/)
 
When users are successfully authenticated, their attributes are retrieved for 
use in the app. You can use the `SessionContext` class to get current user 
attributes.

It's important to note that user credentials and attributes can be 
stored securely in the keychain (see the `saveCredentials` attribute).
This information can then be used to perform  automatic login in subsequent sessions. To do that and auto-create a session based stored user credentials, you can use `SessionContext. loadSessionFromStore()` method.

## Module [](id=module)

- Auth

## Themes [](id=themes)

- Default
- Flat7

![The `LoginScreenlet` using the Default and Flat7 themes.](../../images/screens-ios-login.png)

## Portal Configuration [](id=portal-configuration)

### Basic authentication

The authentication method (email address, screen name or user id) should be configured in your Liferay instance. You can 
set this in the Control Panel by clicking *Portal Settings* &rarr; *Authentication*.

![Setting the authentication method in Liferay Portal.](../../images/screens-portal-auth.png)

For more details, please refer to the [Configuring Portal Settings](/portal/-/knowledge_base/6-2/configuring-portal-settings) 
section of the User Guide. 

### OAuth

If you want to use OAuth authentication type, you first need to install [OAuth Provider EE plugin](https://www.liferay.com/marketplace/-/mp/application/45261909) from the marketplace.

Once the plugin is installed in your Liferay Portal, go to *Control Panel &rarr; Users &rarr; OAuth Admin* and add a new application to be used from Liferay Screens.
When the app is created, copy *Consumer Key* and *Consumer Secret* values to be used later in the screenlet.

![Copy the Consumer Key and Consumer Secret from your OAuth app](../../images/screens-portal-oauth.png)

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
|  `saveCredentials` | `boolean` | When set, the user credentials and attributes are stored securely in the keychain. This information can then be loaded in subsequent sessions by calling the `SessionContext.loadSessionFromStore()` method. |
|  `companyId` | `number` | When set, authentication is done for a user in the specified company. If the value is `0` or empty, the company specified in `LiferayServerContext` is used. |
|  `basicAuthMethod` | `string` | Specifies the authentication method to use. This must match the authentication method configured on the server. You can set this attribute to `email`, `screenName` or `userId`. |
|  `OAuthConsumerKey` | `string` | Specifies the *Consumer Key* to be used in OAuth authentication or leave it empty if you want to use basic authentication. |
|  `OAuthConsumerSecret` | `string` | Specifies the *Consumer Secret* to be used in OAuth authentication or leave it empty if you want to use basic authentication. |

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
  retrieved. Note that this only occurs when the screenlet is used and stored 
  credentials are available.
