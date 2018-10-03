# Login Screenlet for Android [](id=loginscreenlet-for-android)

## Requirements [](id=requirements)

- Android SDK 4.0 (API Level 15) or above
- Liferay Portal 6.2 (CE or EE), Liferay 7.0 CE, Liferay DXP

## Compatibility [](id=compatibility)

- Android SDK 4.0 (API Level 15) and above

## Xamarin Requirements [](id=xamarin-requirements)

- Visual Studio 7.2
- Mono .NET framework 5.4.1.6

## Features [](id=features)

Login Screenlet lets you authenticate portal users in your Android app. The 
following types of authentication are supported:

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

When a user successfully authenticates, their user attributes are retrieved for 
use in the app. You can use the `SessionContext` class to get the current user's 
attributes. 

Note that user credentials and attributes can be stored in an app's data store 
(see the `saveCredentials` attribute). Android's `SharedPreferences` is 
currently the only data store implemented. However, new and more secure data 
stores will be added in the future. Stored user credentials can be used to 
automatically log the user in to subsequent sessions. To do this, you can use 
the method `SessionContext.loadStoredCredentials()`. 

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

## Views [](id=views)

- Default 
- Material 

For instructions on using these Views, see the `layoutId` attribute in the 
[Attributes section below](#attributes). 

![The Login Screenlet using the Default (left) and Material (right) Viewsets.](../../../images/screens-android-login.png)

## Portal Configuration [](id=portal-configuration)

### Basic Authentication [](id=basic-authentication)

Before using Login Screenlet, you should make sure your portal is configured 
with the authentication option you want to use. You can choose email address, 
screen name, or user ID. You can set this in the Control Panel by selecting 
*Configuration* &rarr; *Instance Settings*, and then selecting the 
*Authentication* section. The authentication options are in the *How do users 
authenticate?* selector menu. For more information, see the User Guide's 
[authentication section](/discover/portal/-/knowledge_base/7-1/authentication). 

![Set the authentication method in your @product@ instance.](../../../images/screens-portal-auth.png)

### OAuth Authentication [](id=oauth-authentication)

For instructions on using OAuth with Login Screenlet, see the tutorial on 
[using OAuth 2 with Liferay Screens](/develop/tutorials/-/knowledge_base/7-1/using-oauth-2-in-liferay-screens-for-android). 

## Offline [](id=offline)

This Screenlet doesn't support offline mode. It requires network connectivity.
If you need to log in users automatically, even when there's no network 
connection, you can use the `credentialsStorage` attribute together with the 
`SessionContext.loadStoredCredentials` method. 

## Required Attributes [](id=required-attributes)

- None

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `layoutId` | `@layout` | The ID of the View's layout. You can set this attribute to `@layout/login_default` (Default View) or `@layout/login_material` (Material View). To use the Material View, you must first install the Material View Set. [Click here](/develop/tutorials/-/knowledge_base/7-1/using-views-in-android-screenlets) for instructions on installing and using Views and View Sets, including the Material View Set. |
| `companyId` | `number` | The ID of the portal instance to authenticate to. If you don't set this attribute or set it to `0`, the Screenlet uses the `companyId` setting in `LiferayServerContext`. |
| `loginMode` | `enum` | The Screenlet's authentication type. You can set this attribute to `basic`, `cookie`, `oauth2Redirect`, or `oauth2UsernameAndPassword`. If you don't set this attribute, the Screenlet defaults to basic authentication. |
| `basicAuthMethod` | `string` | Specifies the authentication option to use with basic or cookie authentication. You can set this attribute to `email`, `screenName` or `userId`. This must match the server's authentication option. If you don't set this attribute, and don't set the `loginMode` attribute to one of the OAuth values or `cookie`, the Screenlet defaults to basic authentication with the `email` option. |
| `oauth2Redirect` | `string` | The URL that the mobile browser will redirect the user to after successful login. You must configure this in the portal's OAuth 2 Admin portlet, and associate the URL with the Android app. |
| `oauth2ClientId` | `string` | The ID of the OAuth 2 application in the portal. You can find this value in the portal's OAuth 2 Admin portlet. |
| `oauth2ClientSecret` | `string` | The client secret of the OAuth 2 application in the portal. You can find this value in the portal's OAuth 2 Admin portlet. |
| `oauth2Scopes` | `string` | The portal permissions to request. You can define a set of permissions associated with an OAuth 2 application in the portal's OAuth 2 Admin portlet. Use this attribute to request a subset of those permissions. Separate multiple scopes with a space (e.g., `"scope1 scope2 scope3"`). |
| `credentialsStorage ` | `enum` | Sets the mode for storing user credentials. The possible values are `none`, `auto`, and `shared_preferences`. If set to `shared_preferences`, the user credentials and attributes are stored using Android's `SharedPreferences` class. If set to `none`, user credentials and attributes aren't saved at all. If set to `auto`, the best of the available storage modes is used. Currently, this is equivalent to `shared_preferences`. The default value is `none`. |
| `shouldHandleCookieExpiration` | `bool` | Whether to refresh the cookie automatically when using cookie login. When set to `true` (the default value), the cookie refreshes as it's about to expire.  |
| `cookieExpirationTime` | `int` | How long the cookie lasts, in seconds. This value depends on your portal instance's configuration. The default value is `900`. |
| `authenticator` | `Authenticator` | An instance of a class that implements the `Authenticator` interface. The *Challenge-Response Authentication* section below discusses this further. |

## Listener [](id=listener)

The Login Screenlet delegates some events to an object that implements the 
`LoginListener` interface. This interface let you implement the following 
methods:

- `onLoginSuccess(User user)`: Called when login successfully completes. The 
  `user` parameter contains a set of the logged in user's attributes. The 
  supported keys are the same as those in the 
  [portal's User entity](https://github.com/liferay/liferay-portal/blob/master/portal-impl/src/com/liferay/portal/service.xml#L2575-L2737).

- `onLoginFailure(Exception e)`: Called when an error occurs in the process.

## Challenge-Response Authentication [](id=challenge-response-authentication)

To support 
[challenge-response authentication](https://en.wikipedia.org/wiki/Challenge%E2%80%93response_authentication) 
when using a cookie to log in to the portal, Login Screenlet has an 
`authenticator` attribute. As mentioned in the above *Attributes* table, this 
attribute's value is a class that implements the 
[`Authenticator` interface](https://square.github.io/okhttp/3.x/okhttp/okhttp3/Authenticator.html). 

Here's an example of such a class. It sends a basic authorization in response to 
an authentication challenge: 

    public class BasicAuthAutenticator extends BasicAuthentication implements Authenticator {

        public BasicAuthAutenticator(String username, String password) {
            super(username, password);
        }

        @Override
        public Request authenticate(Proxy proxy, Response response) throws IOException {
            String credential = Credentials.basic(username, password);
            return response.request().newBuilder().header(Headers.AUTHORIZATION, credential).build();
        }

        @Override
        public Request authenticateProxy(Proxy proxy, Response response) throws IOException {
            return null;
        }
    }
