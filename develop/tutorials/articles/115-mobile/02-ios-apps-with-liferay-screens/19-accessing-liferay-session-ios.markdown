# Accessing the Liferay Session in iOS [](id=accessing-the-liferay-session-in-ios)

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
information with the JSON WS API provided by Liferay, or with the Liferay Mobile 
SDK. You can also use `SessionContext` to create anonymous sessions, or log in a 
user without showing Login Screenlet.

This tutorial explains some common `SessionContext` use cases, and also 
describes the class's most important methods. 

## Getting the current session [](id=getting-the-current-session)

The current session is established after the user successfully logs in with 
Login Screenlet. Use `SessionContext.currentContext` to retrieve the session. 
Note this will return `nil` if the user didn't sign in with Login Screenlet. You 
can also use the `SessionContext` property `isLoggedIn` to determine if a 
session exists. This returns `false` if there's no current session. 

## Creating a Liferay Session [](id=creating-a-liferay-session)

When working with Liferay Screens, you may wish to call the remote JSON web 
services provided by the Liferay Mobile SDK. Every operation with the Liferay 
Mobile SDK needs a Liferay Session to provide the server address, user 
credentials, and any other required parameters. Login Screenlet creates a 
session when a user successfully logs in. You can retrieve this session with the 
`SessionContext` method `createRequestSession()`. Typically, you call this 
method through the `currentContext` object. For example: 

    SessionContext.currentContext?.createRequestSession()

You can then use the session to make the Mobile SDK service call. If you need 
to check first to see if a user has logged in, you can use the `SessionContext` 
property `isLoggedIn`. 

Great! Now you know how to retrieve an existing session in your app. But what if 
you're not using Login Screenlet? There won't be an existing session to 
retrieve. No sweat! You can still use `SessionContext` to create one manually. 
The next section shows you how to do this. 

## Creating a Session Manually [](id=creating-a-session-manually)

If you don't use Login Screenlet, then `SessionContext` doesn't have a session 
for you to retrieve. In this case, you must create one manually. You can do this 
with the `SessionContext` method `loginWithBasic`. The method takes a username, 
password, and user attributes as parameters, and creates a session with those 
credentials. The following code uses `loginWithBasic` to create a session:

    Session session = SessionContext.loginWithBasic(username: USERNAME, password: PASSWORD, userAttributes: [:]);

For the `userAttributes` parameter, you must provide some attributes associated 
with the logged in user, such as their `userId`. For a complete list of 
attributes, see the 
[user model interface](https://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/model/User.html). 

Super! Now you know how to create a session manually. The next section shows you 
how to implement auto-login, and save or restore a session. 

## Implementing Auto-login and Saving or Restoring a Session [](id=implementing-auto-login-and-saving-or-restoring-a-session)

Although Login Screenlet is awesome, your users may not want to enter their 
credentials every time they open your app. It's very common for apps to only 
require a single login. To implement this in your app, see 
[this video](https://www.youtube.com/watch?v=kEZEahTzuck). 

In short, you need to set `saveCredentials` to `true` in Login Screenlet. The 
next login then uses the saved credentials. To make sure this also works when 
the app restarts, you must retrieve the stored credentials by using the 
`SessionContext` method `loadStoredCredentials`. The following Swift code shows 
a typical implementation of this: 

    if SessionContext.loadStoredCredentials() {
        // user auto-logged in
	    // consider doing a relogin here (see next section)
    }
    else {
        // send user to login screen with the login screenlet
    }

Awesome! Now you know how to implement auto-login in your Liferay Screens apps. 
For more information on available `SessionContext` methods, see the 
[Methods section](/develop/tutorials/-/knowledge_base/7-0/accessing-the-liferay-session-in-ios#methods) 
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
in a method that determines if the current session is still valid. If the 
session is still valid, the user's data is updated with the most recent data 
from the server. If the session isn't valid, the user is logged out and must 
then log in again to create a new session. 

To this feature, call the `SessionContext.currentContext` method `relogin`: 

    SessionContext.currentContext?.relogin(closure)

Note that this operation is done asynchronously in a background thread. The 
`closure` argument is a function that eventually receives the new user 
attributes. In case of error, the closure is called with `nil` attributes and 
the user is logged out of the session. The typical Swift code for a full relogin 
is as follows. Note a trailing closure is used: 

    SessionContext.currentContext?.relogin { userAttributes in
        if userAttributes == nil {
            // couldn't retrieve the user attributes: user invalidated or password changed?
        }
        else {
            // full re-login made. Everything is updated
        }
    }

Great! Now you know how to implement relogin in your app. You've also seen how 
handy `SessionContext` can be. It can do even more! The next section lists some 
additional `SessionContext` methods, and some more detail on the ones used in 
this tutorial. 

## Methods [](id=methods)

| Method | Return Type | Explanation |
|--------|-------------|-------------| 
| `logout()` | `void` | Clears the stored user attributes and session. |
| `relogin(closure)` | `void` | Refreshes user data from the server. This recreates `currentContext` if successful, or calls `logout()` on failure. When the server data is received, the closure is called with received user's attributes. If an error occurs, the closure is called with `nil`. |
| `loginWithBasic(username, password, userAttributes)` | `LRSession` | Creates a Liferay Session using the default server, and the supplied username, password, and user information. |
| `loginWithOAuth(authentication, userAttributes)` | `LRSession` | Creates a Liferay Session using the default server and the supplied OAuth tokens. This is intended to be used together with the [Liferay iOS OAuth library](https://github.com/brunofarache/liferay-ios-sdk-oauth). |
| `createRequestSession()` | `LRSession` | Creates a Liferay Session based on the current session's server and user credentials. This Liferay Session is intended to be used for only a single request (don't reuse it). |
| `createEphemeralBasicSession(username, password)` | `LRSession` | Creates a Liferay Session based on the provided username and password. Note that this session isn't stored anywhere. This is the method used to create a session for anonymous access. Anonymous access is used by the Sign Up and Forgot Password Screenlets. |
| `userAttribute(key: String)` | `AnyObject` | Returns a `User` object with the server attributes of the logged-in user. This includes the user's email, user ID, name, and portrait ID. |
| `storeCredentials()` | `Bool` | Stores the current session. |
| `removeStoredCredentials()` | `Bool` | Clears the session and user information from storage. |
| `loadStoredCredentials()` | `Bool` | Loads the session and user information from storage. They're then used, respectively, as the current session and user. |

## Properties [](id=properties)

| Property | Type | Explanation |
|--------|-------------|-------------| 
| `currentContext` | `SessionContext` | The current session established through Login Screenlet, or the `loginWithBasic` or `loginWithOAuth` methods. |
| `isLoggedIn` | `Bool` | Returns `true` if `SessionContext` contains a Liferay Session. |
| `basicAuthUsername` | `String` | The username used to establish the current session (if any).|
| `basicAuthPassword` | `String` | The password used to establish the current session (if any).|
| `userId` | `Number` |  The user identifier used to establish the current session (if any). |

For more information, see the 
[`SessionContext` source code in GitHub](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Context/SessionContext.swift).

## Related Topics [](id=related-topics)

[Login Screenlet for iOS](/develop/reference/-/knowledge_base/7-0/loginscreenlet-for-ios)

[Using Screenlets in iOS Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-ios-apps)
