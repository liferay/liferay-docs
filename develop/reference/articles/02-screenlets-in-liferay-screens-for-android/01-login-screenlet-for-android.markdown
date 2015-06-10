# LoginScreenlet for Android [](id=loginscreenlet-for-android)

## Requirements [](id=requirements)

- Android SDK 4.0 (API Level 14) or above
- Liferay Portal 6.2 CE or EE
- [OAuth Provider EE plugin](https://www.liferay.com/marketplace/-/mp/application/45261909) (only for OAuth based authentication)


## Compatibility [](id=compatibility)

- Android SDK 4.0 (API Level 14) and above

## Features [](id=features)

The `LoginScreenlet` authenticates portal users in your iOS app. The following
types of authentication are supported:

- Basic: uses user login and password according to [HTTP Basic Access Authenication specification](http://tools.ietf.org/html/rfc2617). For this type of authentication, you have to choose between three types of authentication methods, depending on your portal configuration:
	- Email address
	- Screen name
	- User id

- OAuth: implementing [OAuth 1.0a specification](http://oauth.net/core/1.0a/)

When a users successfully authenticate, their attributes are retrieved for use 
in the app.  You can use the `SessionContext` class to get current user 
attributes.

It's important to note that user credentials and attributes can be 
stored in an app's data store (see the `saveCredentials` attribute). Android's `SharedPreferences` is currently the 
only data store implemented. However, new and more secure data stores will be 
added in the future. 
Stored user credentials can be used to perform automatic 
login in subsequent sessions. To do that and auto-create a session based on stored user credentials, you can use `SessionContext.loadSessionFromStore()` method.


## Module [](id=module)

- Auth

## Views [](id=views)

- Default
- Material

![The `LoginScreenlet` using the Default and Material viewsets.](../../images/screens-android-login.png)

## Portal Configuration [](id=portal-configuration)

### Basic authentication

The authentication method (email address, screen name or user id) should be configured in your Liferay instance. You can set this in the Control 
Panel by clicking *Portal Settings* &rarr; *Authentication*.

![Setting the authentication method in Liferay Portal.](../../images/screens-portal-auth.png)

For more details, see the [Configuring Portal Settings](/portal/-/knowledge_base/6-2/configuring-portal-settings) 
section of the User Guide. 

### OAuth

If you want to use OAuth authentication type, you first need to install [OAuth Provider EE plugin](https://www.liferay.com/marketplace/-/mp/application/45261909) from the marketplace.

Once the plugin is installed in your Liferay Portal, go to *Control Panel &rarr; Users &rarr; OAuth Admin* and add a new application to be used from Liferay Screens.
When the app is created, copy *Consumer Key* and *Consumer Secret* values to be used later in the screenlet.

![Copy the Consumer Key and Consumer Secret from your OAuth app](../../images/screens-portal-oauth.png)


## Required Attributes [](id=required-attributes)

- `layoutId`

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `layoutId` | `@layout` | The layout to use to show the view. |
| `companyId` | `number` | When set, a user in the specified company is authenticated. If set to `0`, the company specified in `LiferayServerContext` is used. |
| `basicAuthMethod` | `string` | Specifies the authentication method to use. This must match the authentication method configured on the server. You can set this attribute to `email`, `screenName` or `userId`. The default value is `email`. |
| `credentialsStore ` | `enum` | Sets the mode for storing user credentials. The possible values are `none`, `auto`, and `shared_preferences`. If set to `shared_preferences`, the user credentials and attributes are stored using Android's `SharedPreferences` class. If set to `none`, user credentials and attributes aren't saved at all. If set to `auto`, the best of the available storage modes is used. Currently, this is equivalent to `shared_preferences`. The default value is `none`. |
|  `OAuthConsumerKey` | `string` | Specifies the *Consumer Key* to be used in OAuth authentication or leave it empty if you want to use basic authentication. |
|  `OAuthConsumerSecret` | `string` | Specifies the *Consumer Secret* to be used in OAuth authentication or leave it empty if you want to use basic authentication. |


## Listener [](id=listener)

The `LoginScreenlet` delegates some events to an object that implements the 
`LoginListener` interface. This interface let you implement the following 
methods:

- `onLoginSuccess(User user)`: Called when login successfully completes. The 
  `user` parameter contains a set of the logged in user's attributes. The 
  supported keys are the same as those in the [portal's User entity](https://github.com/liferay/liferay-portal/blob/6.2.x/portal-impl/src/com/liferay/portal/service.xml#L2227).

- `onLoginFailure(Exception e)`: Called when an error occurs in the process.
