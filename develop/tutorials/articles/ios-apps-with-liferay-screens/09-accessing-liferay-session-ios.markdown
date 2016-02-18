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
information with the JSON WS API provided by Liferay or directly using the Liferay Mobile SDK.
 You can also use `SessionContext` to create anonymous sessions, or to log in a user without showing a Login Screenlet.

This tutorial explains some common `SessionContext` use cases, and and also 
describes the class's most important methods. 

## Getting the current session [](id=getting-the-current-session)

After the user successfully logs in using the Login Screenlet, the current session is established.
To get the current session is stored in `SessionContext.currentContext`, so you'll have a nil there if the user didn't sign in and a valid object if the user did.
You can also user the `SessionContext` property `isLoggedIn` which will return `false` when there's no current session.

## Creating a Liferay Session [](id=creating-a-liferay-session)

When working with Liferay Screens, you may wish to call the remote JSON web 
services provided by the Liferay Mobile SDK. Every operation with the Liferay 
Mobile SDK needs a Liferay Session to provide the server address, user 
credentials, and any other required parameters. Since the Login Screenlet 
creates a session when a user successfully logs in, you can retrieve this 
session with the `SessionContext` method `createRequestSession()`.
Typically, you call this through the `currentContext` object, like this: `SessionContext.currentContext?.createRequestSession()`.
You can then use that session to make the Mobile SDK service call. If you need 
to check first to see if a user has logged in, you can use the `SessionContext` 
property `isLoggedIn`. 

Great! Now you know how to retrieve an existing session in your app. But what if 
you're not using the Login Screenlet? There won't be an existing session to 
retrieve. No sweat! You can still use `SessionContext` to create one manually. 
The next section shows you how to do this. 

## Creating a Session Manually [](id=creating-a-session-manually)

If you don't use the Login Screenlet, then `SessionContext` doesn't have a 
session for you to retrieve. In this case, you must create one manually. You can 
do this with the `SessionContext` method `loginWithBasic`. The method takes 
a username, password and userAttributes as parameters, and creates a session with those 
credentials. The following code creates a session with `loginWithBasic`:

    Session session = SessionContext.loginWithBasic(username: USERNAME, password: PASSWORD, userAttributes: [:]);

For `userAttributes` parameter, you need to provide some attributes associated to the logged in user, such as the `userId`. For a complete list of attributes, check the [User model interface](https://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/model/User.html). No sweat! You don't need to provide all of them, but if you use the Login Screenlet, it does that for you.

Super! Now you know how to create a session manually. The next section shows you 
how to implement auto-login, and save or restore a session. 

## Implementing Auto-login and Saving or Restoring a Session [](id=implementing-auto-login-and-saving-or-restoring-a-session)

Although the Login Screenlet is awesome, your users may not want to enter their 
credentials every time they open your app. It's very common for apps to only 
require a single login. To implement this in your app, see 
[this video](https://www.youtube.com/watch?v=kEZEahTzuck). 

In short, you need to set `saveCredentials` to `true` in the Login Screenlet. The following login done with that screenlet will persist the credentials used.
In the next app startup, you need to retrieve the stored credentials. This is done using the `SessionContext` method `loadStoredCredentials`.
The following Swift code shows a typical implementation of this:

    if SessionContext.loadStoredCredentials() {
        // user auto-logged in
    }
    else {
        // send user to login screen with the login screenlet
    }

Awesome! Now you know how to implement auto-login in your Liferay Screens apps. 
You've also seen how handy `SessionContext` can be. It can do even more! The 
next section lists some additional `SessionContext` methods. 

## Methods [](id=methods)

| Method | Return Type | Explanation |
|--------|-------------|-------------| 
| `logout()` | `void` | Clears the stored user attributes and session. |
| `loginWithBasic(username, password, userAttributes)` | `LRSession` | Creates a Liferay Session using the default server, and the supplied username, password and user information. |
| `loginWithOAuth(authentication, userAttributes)` | `LRSession` | Creates a Liferay Session using the default server, and the supplied OAuth tokens. This is intended to be used together with [Liferay iOS OAuth library](https://github.com/brunofarache/liferay-ios-sdk-oauth). |
| `createRequestSession()` | `LRSession` | Creates a Liferay Session based on the current session's credentials and server. This Liferay Session is intended to be used just for a single request (don't reuse it).|
| `createEphemeralBasicSession(username, password)` | `LRSession` | Creates a Liferay Session based on provided username and password. Note this session won't be stored anywhere. This is the method used to create session for anoymous access, like SignUp and ForgotPassword screenlets. |
| `userAttribute(key: String)` | `AnyObject` | Returns a `User` object with the server attributes of the logged-in user. This includes the user's email, user ID, name, and portrait ID. |
| `storeCredentials()` | `Bool` | Store the current session. |
| `removeStoredCredentials()` | `Bool` | Clears the user information and session from storage. |
| `loadStoredCredentials()` | `Bool` | Loads the session and user information from storage. They're then used, respectively, as the current session and user. |


## Properties [](id=properties)

| Property | Type | Explanation |
|--------|-------------|-------------| 
| `currentContext` | `SessionContext` | The current session established through the Login Screenlet, or methods `loginWithBasic` and `loginWithOAuth`. |
| `isLoggedIn` | `Bool` | `true` if there is a stored Liferay Session in `SessionContext`. |
| `basicAuthUsername` | `String` | The username used to establish the current session (if any).|
| `basicAuthPassword` | `String` | The password used to establish the current session (if any).|
| `userId` | `Number` |  The user identifier used to establish the current session (if any). |


For more information, see the 
[`SessionContext` source code in GitHub](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Context/SessionContext.swift).

## Related Topics [](id=related-topics)

[Login Screenlet for iOS](/develop/reference/-/knowledge_base/6-2/loginscreenlet-for-ios)

[Using Screenlets in iOS Apps](/develop/tutorials/-/knowledge_base/6-2/using-screenlets-in-ios-apps)
