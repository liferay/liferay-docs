# Login Screenlet for Android [](id=loginscreenlet-for-android)

## Requirements [](id=requirements)

- Android SDK 4.0 (API Level 15) or above
- Liferay Portal 6.2 CE or EE
- [OAuth Provider EE plugin](https://www.liferay.com/marketplace/-/mp/application/45261909) 
  (only when using OAuth for authentication)

## Compatibility [](id=compatibility)

- Android SDK 4.0 (API Level 15) and above

## Features [](id=features)

The `LoginScreenlet` lets you authenticate portal users in your Android app. The 
following types of authentication are supported:

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

Note that user credentials and attributes can be stored in an app's data store 
(see the `saveCredentials` attribute). Android's `SharedPreferences` is 
currently the only data store implemented. However, new and more secure data 
stores will be added in the future. Stored user credentials can be used to 
automatically log the user in to subsequent sessions. To do this, you can use 
the method `SessionContext.loadSessionFromStore()`.

<iframe width="560" height="315" src="https://www.youtube.com/embed/kEZEahTzuck" frameborder="0" allowfullscreen></iframe>

## Module [](id=module)

- Auth

## Views [](id=views)

- Default

![The `LoginScreenlet` using the Default and Material Viewsets.](../../images/screens-android-login.png)

## Portal Configuration [](id=portal-configuration)

### Basic Authentication [](id=basic-authentication)

Before using `LoginScreenlet`, you should make sure your portal is configured 
with the authentication method you want to use. You can choose email address, 
screen name, or user ID. You can set this in the Control Panel by clicking 
*Portal Settings* &rarr; *Authentication*.

![Setting the authentication method in Liferay Portal.](../../images/screens-portal-auth.png)

For more details, see the [Configuring Portal Settings](/portal/-/knowledge_base/6-2/configuring-portal-settings) 
section of the User Guide. 

### OAuth Authentication [](id=oauth-authentication)

<iframe width="560" height="315" src="https://www.youtube.com/embed/u5-_VVAyY-0" frameborder="0" allowfullscreen></iframe>

If you want to use OAuth authentication, you first need to install the 
[OAuth Provider EE plugin](https://www.liferay.com/marketplace/-/mp/application/45261909) 
from Liferay's Marketplace. Once this plugin is installed, go to 
*Control Panel &rarr; Users &rarr; OAuth Admin*, and add a new application to be 
used from Liferay Screens. When the app is created, copy the *Consumer Key* and 
*Consumer Secret* values for later use in `LoginScreenlet`.

![Copy the Consumer Key and Consumer Secret from OAuth Admin in your portal.](../../images/screens-portal-oauth.png)

## Required Attributes [](id=required-attributes)

- `layoutId`

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `layoutId` | `@layout` | The layout to use to show the View. |
| `companyId` | `number` | When set, a user in the specified company is authenticated. If set to `0`, the company specified in `LiferayServerContext` is used. |
| `basicAuthMethod` | `string` | Specifies the authentication method to use. This must match the authentication method configured on the server. You can set this attribute to `email`, `screenName` or `userId`. The default value is `email`. |
| `credentialsStore ` | `enum` | Sets the mode for storing user credentials. The possible values are `none`, `auto`, and `shared_preferences`. If set to `shared_preferences`, the user credentials and attributes are stored using Android's `SharedPreferences` class. If set to `none`, user credentials and attributes aren't saved at all. If set to `auto`, the best of the available storage modes is used. Currently, this is equivalent to `shared_preferences`. The default value is `none`. |
| `OAuthConsumerKey` | `string` | Specifies the *Consumer Key* to used in OAuth authentication. Leave this empty if you want to use Basic authentication. |
| `OAuthConsumerSecret` | `string` | Specifies the *Consumer Secret* to use in OAuth authentication. Leave this empty if you want to use Basic authentication. |

## Listener [](id=listener)

The `LoginScreenlet` delegates some events to an object that implements the 
`LoginListener` interface. This interface let you implement the following 
methods:

- `onLoginSuccess(User user)`: Called when login successfully completes. The 
  `user` parameter contains a set of the logged in user's attributes. The 
  supported keys are the same as those in the [portal's User entity](https://github.com/liferay/liferay-portal/blob/6.2.x/portal-impl/src/com/liferay/portal/service.xml#L2227).

- `onLoginFailure(Exception e)`: Called when an error occurs in the process.

## Offline [](id=offline)

This screenlet doesn't support offline mode. It needs proper network connectivity to work.
However, if you need to automatically log users in (even when there's no connection active), you can use the `credentialsStore` attribute together with `SessionContext.loadSessionFromStore` method.
