# Accessing the Liferay Session in Android [](id=accessing-the-liferay-session-in-android)

A session is a conversation state between the client and server. It typically 
consists of multiple requests and responses between the two. To facilitate this 
communication, the session must have the server IP address, and a user's login 
credentials. Liferay Screens uses a Liferay Session to access and query the 
JSON web services provided by Liferay Portal. When you log in using a Liferay 
Session, the portal returns the user's information (name, email, user ID, 
etc...). Screens stores this information and the active Liferay Session in 
Screens's `SessionContext` class. 

The `SessionContext` class is very powerful and lets you use Screens in many 
different scenarios. For example, you can use `SessionContext` to request 
information with the JSON WS API provided by Liferay. You can also use 
`SessionContext` to create anonymous sessions, or to log in a user without 
showing a Login Screenlet.

This tutorial explains some common `SessionContext` use cases, and and also 
describes the class's most important methods. 

## Creating a Session from an Existing Session [](id=creating-a-session-from-an-existing-session)

When working with Liferay Screens, you may wish to call the remote JSON web 
services provided by the Liferay Mobile SDK. Every operation with the Liferay 
Mobile SDK needs a Liferay Session to provide the server address, user 
credentials, and any other required parameters. Since the Login Screenlet 
creates a session when a user successfully logs in, you can retrieve this 
session with the `SessionContext` method `createSessionFromCurrentSession()`. 
You can then use that session to make the Mobile SDK service call. The following 
example shows this for calling the Mobile SDK's `BookmarksEntryService`: 

    Session sessionFromCurrentSession = SessionContext.createSessionFromCurrentSession();
    sessionFromCurrentSession.setCallback(callback);

    new BookmarksEntryService(sessionFromCurrentSession).methodCall()

If you need to check first to see if a user has logged in, you can use 
`SessionContext.isLoggedIn()`. 

Great! Now you know how to retrieve an existing session in your app. But what if 
you're not using the Login Screenlet? There won't be an existing session to 
retrieve. No sweat! You can still use `SessionContext` to create one manually. 
The next section shows you how to do this. 

## Creating a Session Manually [](id=creating-a-session-manually)

If you don't use the Login Screenlet, then `SessionContext` doesn't have a 
session for you to retrieve. In this case, you must create one manually. You can 
do this with the `SessionContext` method `createBasicSession`. The method takes 
a username and password as parameters, and creates a session with those 
credentials. If you also need to access a user's information, you must manually 
call the `User` JSON web service, or call `SessionContext.setLoggedUser()`. The 
following code creates a session with `createBasicSession` and then uses 
`setLoggedUser` to set the user in `SessionContext`:

    LiferayScreensContext.init(this);

    Session session = SessionContext.createBasicSession(USERNAME, PASSWORD);
    SessionContext.setLoggedUser(USER);

Note that you can achieve the same thing by calling the interactor directly: 

    LoginBasicInteractor loginBasicInteractor = new LoginBasicInteractor(0);
    loginBasicInteractor.onScreenletAttached(this);
    loginBasicInteractor.setLogin(USERNAME);
    loginBasicInteractor.setPassword(PASSWORD);
    loginBasicInteractor.login();

Super! Now you know how to create a session manually. The next section shows you 
how to implement auto-login, and save or restore a session. 

## Implementing Auto-login and Saving or Restoring a Session [](id=implementing-auto-login-and-saving-or-restoring-a-session)

Although the Login Screenlet is awesome, your users may not want to enter their 
credentials every time they open your app. It's very common for apps to only 
require a single login. To implement this in your app, see 
[this video](https://www.youtube.com/watch?v=kEZEahTzuck). 

In short, you need to pass a storage type to the Login Screenlet, and then use 
`SessionContext.isLoggedIn()` to check for a session. If a session doesn't 
exist, load the stored session from `CredentialsStorage` with 
`loadStoredCredentials(StorageType storageType)`. The following code shows a 
typical implementation of this:

    LiferayScreensContext.init(this); // If you haven't called a screenlet yet
    SessionContext.loadStoredCredentials(SHARED_PREFERENCES);

    if (SessionContext.hasSession()) {
        // logged in
        // consider doing a relogin here (see next section)
    } else {
        // send user to login form
    }

Awesome! Now you know how to implement auto-login in your Liferay Screens apps. 
For more information on available `SessionContext` methods, see the 
[Methods section](/develop/tutorials/-/knowledge_base/7-0/accessing-the-liferay-session-in-android#methods) 
at the end of this tutorial. Next, you'll learn how to implement relogin for 
cases where a user's credentials change on the server while they're logged in. 

## Implementing Relogin [](id=implementing-relogin)

A session, whether created via Login Screenlet or auto-login, contains basic 
user data that verifies the user in the Liferay instance. If that data changes 
in the server, then your session is outdated, which may cause your app to behave 
inconsistently. Also, if a user is deleted, deactivated, or otherwise changes 
their credentials in the server, the auto-login feature won't deny access 
because it doesn't perform server transactions: it only retrieves an existing 
session from local storage. This isn't an optimal situation! 

For such scenarios, you can use the relogin feature. This feature is implemented 
in a simple method that determines if the current session is still valid. If the 
session is still valid, the user's data is updated with the most recent data 
from the server. If the session isn't valid, the user is logged out and must 
then log in again to create a new session. 

To use this feature, call the `SessionContext` method `relogin`, with an object 
that implements the `LoginListener` interface as an argument: 

    SessionContext.relogin(listener);

This method handles success or failure via the listener's `onLoginSuccess` and 
`onLoginFailure` methods, respectively. Note that this operation is done 
asynchronously in a background thread, so the listener is called in that thread. 
If you also want to perform any UI operations, you must do so in your UI thread. 
For example: 

    @Override
    public void onLoginSuccess(final User user) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // do any UI operation here
            }
        });
    }

Great! Now you know how to implement relogin in your app. You've also seen how 
handy `SessionContext` can be. It can do even more! The next section lists some 
additional `SessionContext` methods, and some more detail on the ones used in 
this tutorial. 

## Methods [](id=methods)

| Method | Return Type | Explanation |
|--------|-------------|-------------| 
| `logout()` | `void` | Clears the stored user attributes and session. |
| `relogin(LoginListener)` | `void` | Refreshes user data from the server. This recreates the `currentUser` object if successful, or calls `logout()` on failure. When the server data is received, the listener method `onLoginSuccess` is called with received user's attributes. If an error occurs, the listener method `onLoginFailure` is called. |
| `isLoggedIn()` | `boolean` | Returns `true` if there is a stored Liferay Session in `SessionContext`. |
| `createBasicSession(String username, String password)` | `Session` | Creates a Liferay Session using the default server and the supplied username and password. |
| `createSessionFromCurrentSession()` | `Session` | Creates a Liferay Session based on the stored credentials and server. |
| `getCurrentUser()` | `User` | Returns a `User` object containing the server attributes of the logged-in user. This includes the user's email, user ID, name, and portrait ID. |
| `storeCredentials(StorageType storageType)` | `void` | Stores the current session in the `StorageType` supplied as a parameter. |
| `removeStoredCredentials(StorageType storageType)` | `void` | Clears the `StorageType` of any user information and session. |
| `loadStoredCredentials(StorageType storageType)` | `void` | Loads the session and user information from the `StorageType` parameter, and uses it as the current session and user. |

For more information, see the 
[`SessionContext` source code in GitHub](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/java/com/liferay/mobile/screens/context/SessionContext.java).

## Related Topics [](id=related-topics)

[Login Screenlet for Android](/develop/reference/-/knowledge_base/7-0/loginscreenlet-for-android)

[Using Screenlets in Android Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-android-apps)
