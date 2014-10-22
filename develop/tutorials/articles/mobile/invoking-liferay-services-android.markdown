# Invoking Liferay Services in Your Android App [](id=invoking-liferay-services-in-your-android-app)

Now that you've downloaded the Liferay Mobile SDK for Android and placed it in
your Android project's classpath, you can consider how to access and invoke
Liferay services from within an Android application. Here are the steps to
follow: 

1. Create a session.
2. Import the Liferay services for your app to use.
3. Create a service object and call its service methods.

This tutorial shows how the
[Liferay Mobile SDK Sample Android App](https://github.com/brunofarache/liferay-android-sdk-sample)
demonstrates these steps.

## Tutorial: Invoking Liferay Services in Android [](id=tutorial-invoking-liferay-services-in-android)

In particular, this tutorial outlines the steps that the sample app's
[`UsersAsyncTask`](https://github.com/brunofarache/liferay-android-sdk-sample/blob/master/src/com/liferay/mobile/sample/task/UsersAsyncTask.java)
class takes in accessing and calling Liferay services. In your app, 
you can follow these steps too. If you followed the steps in the tutorial 
[Creating the Liferay Android Sample Project](/develop/tutorials/-/knowledge_base/6-2/creating-liferay-android-sample-project), 
then you can find the `UsersAsyncTask` class in the 
`src/com/liferay/mobile/sample/task` folder of your sample project. Now go ahead 
and get started learning how to invoke Liferay services in your Android app!

### Step 1: Create a session [](id=step-1-create-a-session)

The session is a conversion state between the client and server, that consists 
of multiple requests and responses between the two. You need a session to pass 
requests between your app and the Mobile SDK. 

The sample app establishes a session by means of user authentication. It creates
the session in the
[UsersAsyncTask](https://github.com/brunofarache/liferay-android-sdk-sample/blob/master/src/com/liferay/mobile/sample/task/UsersAsyncTask.java)
class as follows:

	Session session = SettingsUtil.getSession();
 
The `getSession()` method returns a `Session` instantiated like this: 

	SessionImpl("http://10.0.2.2:8080", "test@liferay.com", "test");

Here's an explanation of each of the session parameters:

**Server:** The URL of the Liferay instance you're connecting to. If you're
running your app on an Android Emulator, the URL should point to your local
Liferay instance. In this particular case, `http://10.0.2.2:8080` is equivalent
to `http://localhost:8080`, which means the emulator and Liferay are running on
the same machine.

**Username:** Can either be the user's email address, screen name, or user ID.
Your session login user name must be consistent with the authentication method
your Liferay instance is using. Liferay's default authentication method requires
the user's email address.

**Password:** The user's password.

+$$$

**Warning:** Take care when using 
administrator credentials on a production Liferay instance, as you'll have
permission to call any service. Make sure not to modify data accidentally. Of
course, the default administrator credentials should be disabled on a production
Liferay instance. 

$$$

### Step 2: Import the Liferay Services for Your App To Use [](id=step-2-import-the-liferay-services-for-your-app-to-use)

Being a contacts app, the sample app imports the Mobile SDK's `UserService`
class to connect to Liferay Portal's
[`UserService`](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/service/UserService.html):

	import com.liferay.mobile.android.v62.user.UserService; 

Note, the Liferay version (`.v62`) is used in the package namespace. Since the
SDK is built for a specific Liferay version, service classes are separated by
their package name. In this example, the Mobile SDK classes use the `.v62`
package, which means this SDK is compatible with Liferay 6.2. But, you can use
several SDKs in your classpath simultaneously to support different Liferay
versions. 

Your portal's JSON web services page (e.g.,
[http://localhost:8080/api/jsonws](http://localhost:8080/api/jsonws))
lists all available portal services and portlet services. 

### Step 3: Create a Service Object and Call its Service Methods [](id=step-3-create-a-service-object-and-call-its-service-methods)

The sample app creates a `UserService` object and calls its
`getGroupUsers(...)` method to fetch all of the `Guest` site's users:

	UserService userService = new UserService(session);

	...

	long groupId = getGuestGroupId(session);

	JSONArray jsonArray = userService.getGroupUsers(groupId);

	for (int i = 0; i < jsonArray.length(); i++) {
		JSONObject jsonObj = jsonArray.getJSONObject(i);

		users.add(new User(jsonObj));
	}

Since the `userService.getGroupUsers(...)` method requires a site group ID, you
invoke the method `getGuestGroupId(session)` of the sample app's
[`UsersAsyncTask`](https://github.com/brunofarache/liferay-android-sdk-sample/blob/master/src/com/liferay/mobile/sample/task/UsersAsyncTask.java)
class to get the `Guest` site's group ID, and then pass that group ID as
the `groupId` parameter in the call `userService.getGroupUsers(groupId)`. 

+$$$

**Note:** Many service methods
require a group ID as a parameter. The SDK's `GroupService` class, which uses
Liferay Portal's
[`GroupService`](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/service/GroupService.html),
provides methods for getting a site's `Group` and other scope groups. 

$$$

The call `JSONArray jsonArray = userService.getGroupUsers(groupId)`
demonstrates making a basic synchronous service call; the method only
returns after the request has finished. 

Service method return types can be `void`, `String`, `JSONArray`, and
`JSONObject`. Primitive type wrappers can be `Boolean`, `Integer`, `Long`,
and `Double`. 

Great! Now you're familiar with the basic process of accessing Liferay services 
through the Mobile SDK! 

## Related Topics [](id=related-topics)

[Creating iOS Apps that Use Liferay](/develop/tutorials/-/knowledge_base/6-2/creating-ios-apps-that-use-liferay)

[Liferay Mobile SDK Builder](/develop/tutorials/-/knowledge_base/6-2/liferay-mobile-sdk-builder)

[Service Builder and Services](/develop/tutorials/-/knowledge_base/6-2/service-builder)

[Developing Plugins with Liferay IDE](/develop/tutorials/-/knowledge_base/6-2/liferay-ide)
