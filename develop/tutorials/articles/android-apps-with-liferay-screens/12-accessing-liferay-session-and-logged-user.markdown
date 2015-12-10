# Accessing the Liferay Session and the logged User attributes [](id=accessing-liferay-session)

The Liferay Session contains all the required information (usually server IP, username and password) to access and query the JSON web services provided by Liferay Portal. It is documented in the Liferay [Mobile SDK](https://github.com/liferay/liferay-mobile-sdk/tree/master/android).

When you log in using a Liferay Session, Liferay Portal returns the logged user information (name, email, userId...).

Liferay Screens stores the user information and the active Liferay Session in the class `SessionContext`. 

The `SessionContext` class is very powerful and allows using Liferay Screens in very different use cases, for example, to request information with the JSON WS API provided by Liferay. It also allows creating anonymous session or to log a user without showing a `LoginScreenlet`.

We explain several use cases and the most important methods below.

## Useful use cases with Liferay Session [](id=usecases)

### Check if the user if logged-in [](id=check-if-user-logged-in)

You can access the static method `isLoggedIn()` of `SessionContext` to check if there is an active logged user.

### Auto login and Save/Restore a user session [](id=save-restore-user-session)

If you don't want to enter your credentials every time you access the application we recommend you implementing auto login as documented in this [video](https://www.youtube.com/watch?v=kEZEahTzuck).

You need to pass a storage type to your `LoginScreenlet` and check in your application if you have an active Liferay Session with `SessionContext.isLoggedIn()` and if not, load the stored session from the CredentialsStorage with `loadStoredCredentials(StorageType storageType)`.

The code implementing this use case is like this:

```java

LiferayScreensContext.init(this); //If you haven't call to a screenlet before
SessionContext.loadStoredCredentials(SHARED_PREFERENCES);

if (SessionContext.hasSession()) {
	// logged in
} else {
	// send user to login form
}

```


### Creating a Liferay Session [](id=create-a-liferay-session)

A common operation working with Liferay Screens is calling to remote JSON web services provided by the [Liferay Mobile SDK](https://www.liferay.com/es/community/liferay-projects/liferay-mobile-sdk/overview). Every operation with the Liferay Mobile SDK needs a Liferay Session to provide the Server IP Address and other required parameters.

Liferay Screens automatically creates a Liferay Session and allows you to use it in JSON calls with `createSessionFromCurrentSession()`.

A common way of using it is showed below:

```java

Session sessionFromCurrentSession = 
	SessionContext.createSessionFromCurrentSession();

sessionFromCurrentSession.setCallback(callback);

new BookmarksEntryService(sessionFromCurrentSession).methodCall()

```


### Login without using a LoginScreenlet [](id=login-without-loginscreenlet)

A `LoginScreenlet` is not required for using all the screenlets but a `Liferay Session` is.

To allow using Liferay Screens or the Liferay Mobile SDK you need to create manually a Liferay Session. There is a method in SessionContext that allows you to do exactly that, SessionContext.createBasicSession that needs a valid username and password.

If you also need to access information of a user (existing or not), you'll have to manually call the User JSON web service or call SessionContext.setLoggedUser().

```java

LiferayScreensContext.init(this);

Session session = SessionContext.createBasicSession(USERNAME, PASSWORD);

SessionContext.setLoggedUser(USER);

```

You could also do the same, by calling the interactor directly:

```java

LoginBasicInteractor loginBasicInteractor = new LoginBasicInteractor(0);
loginBasicInteractor.onScreenletAttached(this);
loginBasicInteractor.setLogin(USERNAME);
loginBasicInteractor.setPassword(PASSWORD);
loginBasicInteractor.login();

``` 

## Methods [](id=methods)

| Method | Return Type | Explanation |
|--------|-------------|-------------| 
| `logout()` | `void` | Clears the stored user attributes and session. |
| `isLoggedIn()` | `boolean` | returns `true` if there is a stored Liferay Session in SessionContext |
| `createBasicSession(String username, String password)` | `Session` | Creates a Liferay Session using the default server and the supplied username and password |
| `createSessionFromCurrentSession()` | `Session` | Creates a Liferay Session based on the stored credentials and server |
| `getCurrentUser()` | `User` | returns an `User` object with the server attributes of the logged-in user, including the email, userId, name and portraitId of the user. |
| `storeCredentials(StorageType storageType)` | `void` | store the current session in the type of storage supplied as parameter |
| `removeStoredCredentials(StorageType storageType)` | `void` | clear the storage type of user information and session |
| `loadStoredCredentials(StorageType storageType)` | `void` | load session and user information from the storage supplied as parameter and use it as the current session and user |

There are more useful methods, check them out!