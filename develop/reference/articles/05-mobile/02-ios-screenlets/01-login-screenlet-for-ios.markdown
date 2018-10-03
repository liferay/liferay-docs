# Login Screenlet for iOS [](id=loginscreenlet-for-ios)

## Requirements [](id=requirements)

- Xcode 9.0
- iOS 11 SDK
- Liferay Portal 6.2 (CE or EE), Liferay 7.0 CE, Liferay DXP 

## Compatibility [](id=compatibility)

- iOS 9 and above

## Xamarin Requirements [](id=xamarin-requirements)

- Visual Studio 7.2
- Mono .NET framework 5.4.1.6

## Features [](id=features)

The Login Screenlet authenticates portal users in your iOS app. The following
authentication methods are supported:

- **Basic:** uses user login and password according to 
  [HTTP Basic Access Authentication specification](http://tools.ietf.org/html/rfc2617). 
  Depending on the authentication method used by your Liferay instance, you need 
  to provide the user's email address, screen name, or user ID. You also need to 
  provide the user's password. 

- **OAuth:** implements 
  [OAuth 2](https://oauth.net/2/). 

- **Cookie:** uses a cookie to log in. This lets you access documents and images 
  in the portal's document library without the guest view permission in the 
  portal. The other authentication types require this permission to access such 
  files. 

For instructions on configuring the Screenlet to use these authentication types, 
see the below 
[Portal Configuration](#portal-configuration) 
and 
[Screenlet Attributes](#attributes) 
sections. 

When a user successfully authenticates, their attributes are retrieved for use 
in the app. You can use the `SessionContext` class to get the current user's 
attributes.

Note that user credentials and attributes can be stored securely in the keychain 
(see the `saveCredentials` attribute). Stored user credentials can be used to 
automatically log the user in to subsequent sessions. To do this, you can use 
the method `SessionContext.loadStoredCredentials()` method.

## JSON Services Used [](id=json-services-used)

Screenlets in Liferay Screens call the portal's JSON web services. This 
Screenlet calls the following services and methods. 

| Service | Method | Notes | 
| ------- | ------ | ----- |
| `UserService` | `getUserByEmailAddress` | Basic login |
| `UserService` | `getUserByScreenName` | Basic login |
| `UserService` | `getUserById` | Basic login |
| `UserService` | `getCurrentUser` | Cookie and OAuth login |

## Module [](id=module)

- Auth

## Themes [](id=themes)

- Default (`default`)
- Flat7 (`flat7`)

For instructions on using Themes, see the tutorial 
[Using Themes in iOS Screenlets](/develop/tutorials/-/knowledge_base/7-1/using-themes-in-ios-screenlets). 

![The Login Screenlet using the Default and Flat7 Themes.](../../../images/screens-ios-login.png)

## Portal Configuration [](id=portal-configuration)

### Basic Authentication [](id=basic-authentication)

Before using Login Screenlet, you should make sure your portal is configured 
with the authentication option you want to use. You can choose email address, 
screen name, or user ID. You can set this in the Control Panel by selecting 
*Configuration* &rarr; *Instance Settings*, and then selecting the 
*Authentication* section. The authentication options are in the *How do users 
authenticate?* selector menu. For more information, see the User Guide's 
[authentication section](/discover/portal/-/knowledge_base/7-1/authentication). 

![Setting the authentication method in your Liferay instance.](../../../images/screens-portal-auth.png)

### OAuth [](id=oauth)

For instructions on using OAuth with Login Screenlet, see the tutorial on 
[using OAuth 2 with Liferay Screens](/develop/tutorials/-/knowledge_base/7-1/using-oauth-2-in-liferay-screens-for-ios). 

## Offline [](id=offline)

This Screenlet doesn't support offline mode. It requires network connectivity.
If you need to log in users automatically, even when there's no network 
connection, you can use the `saveCredentials` attribute together with the 
`SessionContext.loadStoredCredentials()` method. 

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `companyId` | `number` | The ID of the portal instance to authenticate to. If you don't set this attribute or set it to `0`, the Screenlet uses the `companyId` setting in `LiferayServerContext`. |
| `loginMode` | `string` | The Screenlet's authentication type. You can set this attribute to `basic`, `cookie`, `oauth2Redirect`, or `oauth2UsernameAndPassword`. If you don't set this attribute, the Screenlet defaults to basic authentication. |
| `basicAuthMethod` | `string` | Specifies the basic authentication option to use. You can set this attribute to `email`, `screenName` or `userId`. This must match the server's authentication option. If you don't set this attribute, and don't set the `loginMode` attribute to one of the OAuth values or `cookie`, the Screenlet defaults to basic authentication with the `email` option. |
| `oauth2clientId` | `string` | The ID of the OAuth 2 application in the portal. You can find this value in the portal's OAuth 2 Admin portlet. |
| `oauth2redirectUrl` | `string` | The URL that the mobile browser will redirect the user to after successful login. You must configure this in the portal's OAuth 2 Admin portlet, and associate the URL with the iOS app. |
| `oauth2clientSecret` | `string` | The client secret of the OAuth 2 application in the portal. You can find this value in the portal's OAuth 2 Admin portlet. |
| `oauth2Scopes` | `string` | The portal permissions to request. You can define a set of permissions associated with an OAuth 2 application in the portal's OAuth 2 Admin portlet. Use this attribute to request a subset of those permissions. Separate multiple scopes with a space (e.g., `"scope1 scope2 scope3"`). |
| `saveCredentials` | `boolean` | When set, the user credentials and attributes are stored securely in the keychain. This information can then be loaded in subsequent sessions by calling the `SessionContext.loadStoredCredentials()` method. |
| `shouldHandleCookieExpiration` | `bool` | Whether to refresh the cookie automatically when using cookie login. When set to `true` (the default value), the cookie refreshes as it's about to expire.  |
| `cookieExpirationTime` | `int` | How long the cookie lasts, in seconds. This value depends on your portal instance's configuration. The default value is `900`. |

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

## Challenge-Response Authentication [](id=challenge-response-authentication)

To support 
[challenge-response authentication](https://en.wikipedia.org/wiki/Challenge%E2%80%93response_authentication) 
when using a cookie to log in to the portal, the `SessionContext` class has a 
`challengeResolver` attribute. For more information about how iOS handles 
challenge-response authentication, see the article 
[Authentication Challenges and TLS Chain Validation](https://developer.apple.com/library/content/documentation/Cocoa/Conceptual/URLLoadingSystem/Articles/AuthenticationChallenges.html). 

The challenge resolver type is a closure or block that receives two parameters: 

1.  `URLAuthenticationChallenge`
2.  Another closure or block. You must call this to resolve the challenge (e.g., 
    by passing credentials, canceling the challenge, etc.). You can do this by 
    passing a `URLSession.AuthChallengeDisposition`. 

Here's an example that sends a basic authorization in response to an 
authentication challenge: 

    SessionContext.challengeResolver = resolver

    func resolver(challenge: URLAuthenticationChallenge,
        decisionCallback: (URLSession.AuthChallengeDisposition, URLCredential) -> Void) {

        // Use the challenge variable to get information about the challenge itself
        if challenge.previousFailureCount == 0 {
            // To solve the challenge, call the decision callback with your decision
            // Pass the credentials to the server
            decisionCallback(.useCredential, URLCredential(user: "user", password: "password", 
                persistence: .forSession))
        }
        else {
            // Something went wrong, so let the system handle the challenge
            decisionCallback(.performDefaultHandling, URLCredential(user: "these credentials", 
                password: "are ignored", persistence: .none))
        }

    }
