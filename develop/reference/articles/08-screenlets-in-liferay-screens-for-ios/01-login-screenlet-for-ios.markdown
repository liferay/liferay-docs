# Login Screenlet for iOS [](id=loginscreenlet-for-ios)

## Requirements [](id=requirements)

- Xcode 7.3
- iOS 9 SDK
- Liferay Portal 6.2 (CE or EE), Liferay 7.0 CE, Liferay DXP 
- OAuth Provider app (OAuth authentication is optional. Note that OAuth only 
  works with Liferay EE and DXP instances). 

## Compatibility [](id=compatibility)

- iOS 7 and above

## Features [](id=features)

The Login Screenlet authenticates portal users in your iOS app. The following
authentication methods are supported:

- **Basic:** uses user login and password according to 
  [HTTP Basic Access Authentication specification](http://tools.ietf.org/html/rfc2617). 
  Depending on the authentication method used by your Liferay instance, you need 
  to provide the user's email address, screen name, or user ID. You also need to 
  provide the user's password. 

- **OAuth:** implements the 
  [OAuth 1.0a specification](http://oauth.net/core/1.0a/).

- **Cookie:** uses a cookie to log in. This lets you access documents and images 
  in the portal's document library without the guest view permission in the 
  portal. The other authentication types require this permission to access such 
  files. 

+$$$

**Note:** Cookie authentication with Login Screenlet is broken in fix packs 14 
through 18 of Liferay Digital Enterprise 7.0. This issue is fixed in newer fix 
packs for Liferay Digital Enterprise 7.0. 

$$$

For instructions on configuring the Screenlet to use these authentication types, 
see the below 
[Portal Configuration](/develop/reference/-/knowledge_base/7-0/loginscreenlet-for-ios#portal-configuration) 
and 
[Screenlet Attributes](/develop/reference/-/knowledge_base/7-0/loginscreenlet-for-ios#attributes) 
sections. 

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

For instructions on using Themes, 
[click here](/develop/tutorials/-/knowledge_base/7-0/using-themes-in-ios-screenlets). 

![The Login Screenlet using the Default and Flat7 Themes.](../../images/screens-ios-login.png)

## Portal Configuration [](id=portal-configuration)

### Basic Authentication [](id=basic-authentication)

Before using Login Screenlet, you should make sure your portal is configured 
with the authentication option you want to use. You can choose email address, 
screen name, or user ID. You can set this in the Control Panel by selecting 
*Configuration* &rarr; *Instance Settings*, and then selecting the 
*Authentication* section. The authentication options are in the *How do users 
authenticate?* selector menu. 

![Setting the authentication method in your Liferay instance.](../../images/screens-portal-auth.png)

For more details, please refer to the 
[Setting up a Liferay Instance](/discover/portal/-/knowledge_base/7-0/setting-up-a-liferay-instance) 
section of the User Guide. 

### OAuth [](id=oauth)

+$$$

**Note:** OAuth authentication is only available in Liferay DXP instances. 

$$$

<iframe width="560" height="315" src="https://www.youtube.com/embed/hg70gpUEUI0" frameborder="0" allowfullscreen></iframe>

<iframe width="560" height="315" src="https://www.youtube.com/embed/fo18U3SHhiI" frameborder="0" allowfullscreen></iframe>

To use OAuth authentication, first install the OAuth provider app from the 
Liferay Marketplace. 
[Click here](https://web.liferay.com/marketplace/-/mp/application/45261909) 
to get this app. Once it's installed, go to *Control Panel* &rarr; *Users* 
&rarr; *OAuth Admin*, and add a new application to be used from Liferay Screens. 
Once the application exists, copy the *Consumer Key* and *Consumer Secret* 
values for later use in Login Screenlet. 

## Offline [](id=offline)

This Screenlet doesn't support offline mode. It requires network connectivity.
If you need to log in users automatically, even when there's no network 
connection, you can use the `saveCredentials` attribute together with the 
`SessionContext.loadStoredCredentials()` method. 

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `companyId` | `number` | The ID of the portal instance to authenticate to. If you don't set this attribute or set it to `0`, the Screenlet uses the `companyId` setting in `LiferayServerContext`. |
| `loginMode` | `string` | The Screenlet's authentication type. You can set this attribute to `basic`, `oauth`, or `cookie`. If you don't set this attribute, the Screenlet defaults to basic authentication. |
| `basicAuthMethod` | `string` | Specifies the basic authentication option to use. You can set this attribute to `email`, `screenName` or `userId`. This must match the server's authentication option. If you don't set this attribute, and don't set the `loginMode` attribute to `oauth` or `cookie`, the Screenlet defaults to basic authentication with the `email` option. |
| `OAuthConsumerKey` | `string` | Specifies the *Consumer Key* to use in OAuth authentication. |
| `OAuthConsumerSecret` | `string` | Specifies the *Consumer Secret* to use in OAuth authentication. |
| `saveCredentials` | `boolean` | When set, the user credentials and attributes are stored securely in the keychain. This information can then be loaded in subsequent sessions by calling the `SessionContext.loadStoredCredentials()` method. |

## Delegate [](id=delegate)

The Login Screenlet delegates some events to an object that conforms to the 
`LoginScreenletDelegate` protocol. This protocol lets you implement the 
following methods:

- `- screenlet:onLoginResponseUserAttributes:`: Called when login successfully 
  completes. The user attributes are passed as a dictionary of keys (`String` or 
  `NSStrings`) and values (`AnyObject` or `NSObject`). The supported keys are 
  the same as the [portal's User entity](https://github.com/liferay/liferay-portal/blob/master/portal-impl/src/com/liferay/portal/service.xml#L2575-L2737).

- `- screenlet:onLoginError:`: Called when an error occurs during login. The 
  `NSError` object describes the error.

- `- screenlet:onCredentialsSavedUserAttributes:`: Called when the user 
  credentials are stored after a successful login.

- `- screenlet:onCredentialsLoadedUserAttributes:`: Called when the user 
  credentials are retrieved. Note that this only occurs when the Screenlet is 
  used and stored credentials are available. 
