
# Leveraging the Mobile SDK

The Liferay Mobile SDK is a framework for building native mobile apps that
integrate with your different Liferay Portal instances and their portlets. The
SDK provides the means for your mobile apps to easily consume Liferay Portal's
core web services and the web services of your custom portlets. It wraps Liferay
JSON web services, takes care of authentication, makes REST (Representational
State Transfer) requests (synchronously or asynchronously), parses JSON results,
and handles server side exceptions. The Liferay Mobile SDK is compatible with
Liferay Portal 6.2 and later, and it comes with the Liferay Android SDK and
Liferay iOS SDK ready for you to download and use. For an illustration for how
the Mobile SDK works, view Figure 8.1. 

![Figure 8.1: There are four main steps involved with using the Mobile SDK.](../../images/mobile-sdk-diagram.png)

The illustration displays the general process that links the Mobile SDK with
your native app. In this chapter, we'll provide information about this process
for the development of Android and iOS apps.

We'll discuss the following topics as we explore Liferay's Mobile SDK:

- Using the Android SDK
- Using the iOS SDK
- Building an SDK for Custom Portlet Services

Let's get started by learning how to access Liferay's core services from an
Android app.

## Using the Android SDK

Suppose you're creating an Android app and would like to access some of
Liferay's core services. You'll need to download the SDK and generate libraries
from the SDK from which to choose the services you'll need for your native app.
Let's get started!

### Downloading the Android SDK

As we stated earlier, you'll need to download the latest version of
`liferay-android-sdk.jar`. In addition, if you want to debug the SDK source
code, you can download `liferay-android-sdk-sources.jar` and attach the source
code to your IDE project. You can download these JAR files from the Liferay
Mobile SDK [Downloads](https://github.com/liferay/liferay-mobile-sdk/releases/)
page.

<!-- Change link once Downloads page is available on liferay.com. Bruno informed
me that the Downloads page would be within its community project page, which
will be located at https://www.liferay.com/community/liferay-projects -->

Once you've downloaded your JAR file, insert it into the `/libs` directory of
your Android project. Android Developer Tools should automatically add this JAR
to your classpath. If you're using a different IDE, make sure this JAR is added
to the project classpath. Now import your classes and begin using the SDK!

Great! Now let's move on to configuring the SDK.

### Configuring the Android SDK

Now that you've downloaded the Liferay Mobile SDK for Android and placed it in
your Android project, let's configure your app to use the SDK. In the following
example, we'll reference the [Liferay Mobile SDK Sample
Android](https://github.com/brunofarache/liferay-mobile-sdk-sample-android)
app's configuration and then demonstrate how it accesses Liferay services.

1. Create a `Session` with the user credentials. For example, the sample app
   creates the session in the
   [UsersAsyncTask](https://github.com/brunofarache/liferay-mobile-sdk-sample-android/blob/master/src/com/liferay/mobile/sample/task/UsersAsyncTask.java)
   class as follows:

        Session session = new SettingsUtil.getSession();
        
    The `getSession()` method returns the following:
        
        SessionImpl("http://10.0.2.2:8080", "test@liferay.com", "test");

    The session is a conversional state between the client and server, which
    consists of multiple requests and responses between the two. We need a
    session to pass requests between the Mobile SDK and your app. This code uses
    user authentication to pass information between the Liferay instance and the
    user to maintain the session.

    The first parameter is the URL of the Liferay instance you're connecting to.
    If you're running your app on an Android Emulator, it points to your local
    Liferay instance. In this particular case, `http://10.0.2.2:8080` is
    equivalent to `http://localhost:8080`, which means the emulator and Liferay
    are running in the same machine.

    The second parameter can be the user's email address, screen name, or user
    ID. It depends on which authentication method your Liferay instance is
    using. The default authentication method requires the user's email address.

    Lastly, the third parameter is the user's password.
    
    ---
    
    ![Note](../../images/tip-pen-paper.png) **Warning:** Be careful to use these
    credentials on a production Liferay instance. If you're using the
    administrator credentials, you have permission to call any service and can
    change any data by accident.
    
    ---

2. Check which Liferay services you need to build your app by navigating to
   [http://localhost:8080/api/jsonws](http://localhost:8080/api/jsonws). This
   page lists all available portal services and plugin services. For our sample
   app example, we'll build a contacts app and import `UserService`.

        import com.liferay.mobile.android.v62.user.UserService;

    Since the SDK is built for a specific Liferay version, service classes are
    separated by their package name. In this case, it's `.v62`, which means this
    SDK is built for Liferay 6.2. You can use several SDKs at the same time to
    support different Liferay versions.

3. Create a `UserService` object and make a service call:

        UserService userService = new UserService(session);

        ...
        
        long groupId = getGuestGroupId(session);

		JSONArray jsonArray = userService.getGroupUsers(groupId);
			
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObj = jsonArray.getJSONObject(i);
				
			users.add(new User(jsonObj));

    This code fetches all the users from the site with the `groupId`, which is
    specified in the `getGroupUsers()` method above.

    This is a basic example of a synchronous service call; the method only
    returns after the request is finished. Service method return types can be
    `void`, `String`, `JSONArray`, and `JSONObject`. Primitive type wrappers can
    be `Boolean`, `Integer`, `Long`, and `Double`.
    
    ---
    
    ![Note](../../images/tip-pen-paper.png) **Note:** Many service methods
    require `groupId` as a parameter. You can get the user's groups by calling
    the `getUserSites()` method from `GroupService`.
    
    ---
    
    As you'll notice when studying our sample app, there are several other
    moving parts to make this app work. However, our demonstration showed the
    basic process of accessing Liferay services through the Mobile SDK. Now
    let's take a look at how this app works.

To test our sample *Contacts App*, you'll need to do the following:

1. Install the [Android Developer Tools
   SDK](http://developer.android.com/sdk/index.html) plugin for IDE.

2. Fork and clone the [sample
   app](https://github.com/brunofarache/liferay-mobile-sdk-sample-android) from
   Github. Follow the [Fork A
   Repo](https://help.github.com/articles/fork-a-repo) instructions for details.

3. Import the sample app project into IDE. You can complete this process by
   right-clicking in the *Package Explorer* and selecting *Import*. Then select
   *General* &rarr; *Existing Projects into Workspace* and browse and select the
   sample app project. Then click *Finish*.

4. Configure an *Android Virtual Device* by following the
   [instructions](http://developer.android.com/tools/devices/index.html)
   provided by Android.

5. Make sure to have a local Liferay instance running on
   [localhost:8080](localhost:8080).

6. Run the emulator by selecting the project, and clicking *Run* &rarr; *Run As*
   &rarr; *Android Application*.

7. Navigate through the emulator to the *Liferay Contacts* app. You should see a
   list of the users of your Liferay site.

![Figure 8.2: The sample *Contacts App* provides the users of your local Liferay instance.](../../images/liferay-contacts-app.png)

Notice you can also select an individual user and view their personal
information. Great work!

Next, we'll talk about different kinds of requests that are allowed between your
Android app and the Liferay Mobile SDK.

### Using Asynchronous HTTP Requests

Asynchronous requests allow your app to continue processing without
interruption, and is notified when its request to the SDK is returned. On the
other hand, synchronous requests must wait for a response before the app can
continue its processing. Android doesn't allow synchronous HTTP requests from
the main UI thread. You can only make synchronous requests from different
threads; for example, from within an
[AsyncTask](http://developer.android.com/reference/android/os/AsyncTask.html).

Therefore, the other option is to make asynchronous HTTP requests if you don't
want to create an `AsyncTask` yourself. It makes the service call from an
`AsyncTask` and you can pass a callback that is invoked after the request
finishes.

You can set a callback class implementation to the session for the following
service calls to be asynchronous. Set it back to null if you want to make
synchronous calls again.

    import com.liferay.mobile.android.task.callback.AsyncTaskCallback;
    import com.liferay.mobile.android.task.callback.typed.JSONArrayAsyncTaskCallback;

    AsyncTaskCallback callback = new JSONArrayAsyncTaskCallback() {

        public void onFailure(Exception exception) {
            // Implement exception handling code
        }

        public void onSuccess(JSONArray result) {
            // Called after request has finished successfully
        }

    };

    session.setCallback(callback);
    userService.getGroupUsers(groupId);

The `onFailure()` method is called if an exception occurs during the request.
This could be triggered by either a connection exception (e.g. a request
timeout) or a `ServerException`. If a `ServerException` occurs, it's because
something went wrong on the server side. For instance, if you pass a `groupId`
that doesn't exist, the portal complains about it and the SDK wraps the error
message with a `ServerException`.

There are multiple `AsyncTaskCallback` implementations, one for each method
return type: `JSONObjectAsyncTaskCallback`, `JSONArrayAsyncTackCallback`,
`StringAsyncTaskCallback`, `BooleanAsyncTaskCallback`,
`IntegerAsyncTaskCallback`, `LongAsyncTaskCallback`, and
`DoubleAsyncTaskCallback`. All you'll need to do is pick the appropriate
implementation for your service method return type. In the example code snippet
above since `getGroupUsers` returns a `JSONArray`, you must use the
`JSONArrayAsyncTaskCallback` instance.

It's also possible to use a generic `AsyncTaskCallback` implementation called
`GenericAsyncTaskCallback`. For this implementation, you must implement a
transform method and handle JSON parsing by yourself.

---

![Note](../../images/tip-pen-paper.png) **Note:** If you still don't want to use
any of these callbacks, you can implement `AsyncTaskCallback` directly, but be
careful, you should always get the first element of the `JSONArray` passed as a
parameter to the `onPostExecute(JSONArray jsonArray)` method (i.e.,
`jsonArray.get(0)`).

---

After the request has finished, the `onSuccess()` method is called on the main
UI thread. Since the request is asynchronous, `userService.getGroupUsers`
returns immediately with a null object. The result will be passed to the
callback `onSuccess()` method instead.

Besides using synchronous and asynchronous requests, you can also send requests
using batch processing. Let's learn about this next.

### Sending Requests Using Batch Processing

The Mobile SDK also allows sending requests using batch processing, which can be
much more efficient in some cases. In summary, batch processing executes a
series of program jobs without needing manual intervention. For example, suppose
you want to delete 10 blog entries at the same time; instead of making one
request for each delete call, you can create a batch of calls and send them all
together. For example, here is a code snippet from a Blogs app using synchronous
batch calls:

    import com.liferay.mobile.android.service.BatchSessionImpl;

    BatchSessionImpl batch = new BatchSessionImpl(session);
    BlogsEntryService service = new BlogsEntryService(batch);

    service.deleteEntry(1);
    service.deleteEntry(2);
    service.deleteEntry(3);

    JSONArray jsonArray = batch.invoke();
    
First, the `BatchSessionImpl` session is created. You can either pass
credentials or pass another session to the constructor. Passing another session
to the constructor is useful when you already have a `Session` object and want
to reuse the same credentials. Then, make the service calls as usual. With
asynchronous calls, these methods would return a null object immediately.

Finally, call the `invoke()` method from the `Session` object. It returns a
`JSONArray` containing the results for each service call. Since there are three
`deleteEntry` calls, the `jsonArray` contains three objects. The order of the
result matches the order of the service calls.

If you want to make batch calls asynchronously, set the callback as a
`BatchAsyncTaskCallback` instance:

    import com.liferay.mobile.android.task.callback.BatchAsyncTaskCallback;

    batch.setCallback(new BatchAsyncTaskCallback() {

        public void onFailure(Exception exception) {
        }

        public void onSuccess(JSONArray results) {
            // The result is always a JSONArray 
        }

    });

As you can see, the return type is always a `JSONArray`.

Next, let's dive into using the iOS SDK to access Liferay services!

## Using the iOS SDK


## Building an SDK for Custom Portlet Services

