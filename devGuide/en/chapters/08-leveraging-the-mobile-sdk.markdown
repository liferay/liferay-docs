# Leveraging the Mobile SDK (Beta) [](id=mobile-sdk-to-call-services-liferay-portal-6-2-dev-guide-en)

The Liferay Mobile SDK is a framework that provides the means for your native
mobile apps to easily consume Liferay Portal's core web services and custom
portlet web services. It wraps Liferay JSON web services, making them accessible
for mobile apps. It takes care of authentication, makes
[RESTful](http://en.wikipedia.org/wiki/Representational_state_transfer) requests
(synchronously or asynchronously), parses JSON results, and handles server side
exceptions, so you can concentrate on using the services in your app. 

The Liferay Mobile SDK is compatible with Liferay Portal 6.2 and later, and it
comes with the Liferay Android SDK and Liferay iOS SDK ready for you to
download and use. The Liferay Mobile SDK is in Beta and is currently
unsupported. The mobile SDK's official [project
page](https://www.liferay.com/community/liferay-projects/liferay-mobile-sdk/overview)
gives you access to the SDK releases to download, provides the latest SDK news,
and has forums for you to engage in mobile app development discussions. 

For an illustration for how the mobile SDK works, see Figure 8.1. 

![Figure 8.1: Liferay's Mobile SDK acts as a bridge allowing your native app to communicate with Liferay services and utilities.](../../images/mobile-sdk-diagram.png)

The illustration displays the general process that links the mobile SDK with
your native app. You can create and edit you app using Liferay Developer Studio.
Once the app is created, it can send and receive requests to/from the Liferay
Mobile SDK to enable access to Liferay services. 

In this chapter, we'll demonstrate developing Android and iOS apps that
communicate with Liferay via the mobile SDK. We'll discuss the following topics
as we learn how to use Liferay's Mobile SDK:

- Using the Android SDK
- Using the iOS SDK
- Accessing the Mobile SDK Using Eclipse

<!--
- Building an SDK for Custom Portlet Services
-->

Let's get started by learning how to access Liferay's core services from an
Android app.

## Using the Android SDK [](id=use-the-android-sdk-to-call-services-liferay-portal-6-2-dev-guide-en)

Suppose you're creating an Android app and you'd like to access some of
Liferay's core services. All you need to do is download the SDK and put it in
your classpath, and you can access the services immediately. If you want to
invoke custom portlet web services, you'll need to generate client libraries
for them. You can learn more about generating these libraries by reading the
[Building an SDK for Custom Portlet Services](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/build-sdk-for-custom-portlet-services-liferay-portal-6-2-dev-guide-en)
section. 

Let's get started by downloading the Android SDK and configuring it in your
Android environment! 

### Setting Up the Android SDK [](id=setup-liferay-mobile-sdk-for-android-liferay-portal-6-2-dev-guide-en)

As we stated earlier, you'll need to download the latest version of
`liferay-android-sdk.jar`. In addition, if you want to debug the SDK source
code, you can download `liferay-android-sdk-sources.jar` and attach the source
code to your IDE project. You can download these JAR files from the Liferay
Mobile SDK [Downloads](https://github.com/liferay/liferay-mobile-sdk/releases/)
page.

<!-- Change link once Downloads page is available on liferay.com. Bruno informed
me that the Downloads page would be within its community project page, which
will be located at
https://www.liferay.com/community/liferay-projects/liferay-mobile-sdk/ -->

Once you've downloaded your JAR file, insert it into the `/libs` directory of
your Android project. Android Developer Tools should automatically add this JAR
to your classpath. If you're using a different IDE, make sure this JAR is added
to the project classpath. Now, you'll be able to import the SDK classes for your
app to use. 

Great! Now let's start accessing Liferay services from your app. 

### Accessing Services from Your Android App [](id=access-liferay-services-from-android-app-liferay-portal-6-2-dev-guide-en)

Now that you've downloaded the Liferay Mobile SDK for Android and placed it in
your Android project's classpath, let's consider how to access and invoke
Liferay services from within an Android application. Here are the steps to
follow: 

1. Create a session.
2. Import the Liferay services for your app to use.
3. Create a service object and call its service methods.

We'll show how the [Liferay Mobile SDK Sample Android
App](https://github.com/brunofarache/liferay-mobile-sdk-sample-android/tree/1.0.0)
demonstrates these steps. In particular, we'll outline the steps that its
[`UsersAsyncTask`](https://github.com/brunofarache/liferay-mobile-sdk-sample-android/blob/1.0.0/src/com/liferay/mobile/sample/task/UsersAsyncTask.java)
class takes in accessing and calling Liferay services. In your app, you can
follow these steps too. 

#### Step 1: Create a session [](id=create-a-session-android-liferay-portal-6-2-dev-guide-en)

The session is a conversion state between the client and server, which
consists of multiple requests and responses between the two. We need a
session to pass requests between your app and the mobile SDK. 

The sample app establishes a session by means of user authentication. It creates
the session in the
[UsersAsyncTask](https://github.com/brunofarache/liferay-mobile-sdk-sample-android/blob/1.0.0/src/com/liferay/mobile/sample/task/UsersAsyncTask.java)
class as follows:

	Session session = new SettingsUtil.getSession();
  
The `getSession()` method returns a `Session` instantiated like this: 

	SessionImpl("http://10.0.2.2:8080", "test@liferay.com", "test");

Here's an explanation of each of the session parameters:

  - *Server:* The URL of the Liferay instance you're connecting to. If
  you're running your app on an Android Emulator, the URL should point to your
  local Liferay instance. In this particular case, `http://10.0.2.2:8080` is
  equivalent to `http://localhost:8080`, which means the emulator and
  Liferay are running on the same machine.

  - *Username:* Can either be the user's email address, screen name, or
  user ID. Your session login user name must be consistent with the
  authentication method your Liferay instance is using. Liferay's default
  authentication method requires the user's email address.

  - *Password:* The user's password.

---

![Note](../../images/tip-pen-paper.png) **Warning:** Take care when using these
administrator credentials on a production Liferay instance, as you'll have
permission to call any service. Make sure not to modify data accidentally. 

---

#### Step 2: Import the Liferay services for your app to use [](id=step-2-import-the-liferay-services-for-y-liferay-portal-6-2-dev-guide-08-en)

Being a contacts app, the sample app imports the mobile SDK's `UserService`
class to connect to Liferay Portal's
[`UserService`](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/service/UserService.html):

	import com.liferay.mobile.android.v62.user.UserService; 

Note, the Liferay version (`.v62`) is used in the package namespace. Since the
SDK is built for a specific Liferay version, service classes are separated by
their package name. In this example, our mobile SDK classes use the `.v62`
package, which means this SDK is compatible with Liferay 6.2. But, you can use
several SDKs in your classpath simultaneously to support different Liferay
versions. 

Your portal's JSON web services page (e.g.,
[http://localhost:8080/api/jsonws](http://localhost:8080/api/jsonws))
lists all available portal services and portlet services. 

#### Step 3: Create a service object and call its service methods [](id=step-3-create-a-service-object-and-call--liferay-portal-6-2-dev-guide-08-en)

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

Since the `userService.getGroupUsers(...)` method requires a site group ID, we
invoke the method `getGuestGroupId(session)` of the sample app's
[`UsersAsyncTask`](https://github.com/brunofarache/liferay-mobile-sdk-sample-android/blob/1.0.0/src/com/liferay/mobile/sample/task/UsersAsyncTask.java)
class to get the `Guest` site's group ID, and then we pass that group ID as
the `groupId` parameter in the call `userService.getGroupUsers(groupId)`. 

---

![Note](../../images/tip-pen-paper.png) **Note:** Many service methods
require a group ID as a parameter. The SDK's `GroupService` class, which uses
Liferay Portal's
[`GroupService`](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/service/GroupService.html),
provides methods for getting a site's `Group` and other scope groups. 

---

The call `JSONArray jsonArray = userService.getGroupUsers(groupId)`
demonstrates making a basic synchronous service call; the method only
returns after the request has finished. 

Service method return types can be `void`, `String`, `JSONArray`, and
`JSONObject`. Primitive type wrappers can be `Boolean`, `Integer`, `Long`,
and `Double`. 

So far, we've explained the basic process of accessing Liferay services through
the mobile SDK. Now let's test the sample *Contacts App* from our development
environment. 

### Testing Your Android App in Eclipse [](id=test-android-app-with-liferay-portal-6-2-dev-guide-en)

To test the sample *Contacts App*, you'll need to do the following:

1. Install the [Android Developer Tools
   SDK](http://developer.android.com/sdk/index.html) plugin for Eclipse, Liferay
   Developer Studio, or [Liferay IDE](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/developing-apps-with-liferay-ide-liferay-portal-6-2-dev-guide-02-en). 

2. Fork and clone the [sample
   app](https://github.com/brunofarache/liferay-mobile-sdk-sample-android/tree/1.0.0)
   from GitHub. If you're unfamiliar with GitHub and need help forking and
   cloning the repository, follow the [Fork A
   Repo](https://help.github.com/articles/fork-a-repo) instructions. 

3. Import the sample app project into your Eclipse instance. You can complete
   this process by right-clicking in the *Package Explorer* and selecting
   *Import*. Then select *General* &rarr; *Existing Projects into Workspace* and
   browse and select the sample app project. Then click *Finish*. 

4. Configure an *Android Virtual Device* by following the
   [instructions](http://developer.android.com/tools/devices/index.html)
   provided by Android. 

5. Make sure to start a Liferay instance on [localhost:8080](localhost:8080).

6. Run the emulator by selecting the project, and clicking *Run* &rarr; *Run As*
   &rarr; *Android Application*.

7. Navigate through the emulator to the *Liferay Contacts* app, to see it list
   your Liferay site's users. You can select an individual user and view that
   user's personal information.

    ![Figure 8.2: The sample *Contacts App* lets you view the users of your local Liferay instance.](../../images/liferay-contacts-app.png)

Great work!

Next, we'll explore making asynchronous HTTP requests to your portal's services.

### Invoking Services Asynchronously from Your Android App [](id=invoke-service-asynchronously-android-liferay-portal-6-2-dev-guide-en)

Android doesn't allow making synchronous HTTP requests from the main UI thread.
Synchronous HTTP requests must be made from threads other than the main UI
thread. For example, they can be made from an `AsyncTask` instance. 

The SDK can help you make asynchronous HTTP requests if you don't
want to create an `AsyncTask` yourself. Simply implement a callback class,
instantiate it, and set the callback instance on the session. When the SDK makes
your service calls for that session, it makes them asynchronously. To make
synchronous calls again, set `null` as the session's callback. 

The following steps outline how to implement asynchronous requests in your app: 

1. Implement and instantiate your callback class.
2. Set the callback on the session.
5. Call Liferay services.

The following code is an implementation of these steps: 

    import com.liferay.mobile.android.task.callback.AsyncTaskCallback;
    import com.liferay.mobile.android.task.callback.typed.JSONArrayAsyncTaskCallback;

    ...

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

Let's consider how this code asynchronously invokes the Liferay service. 

It imports the `AsyncTaskCallback` callback class and the callback class
`JSONArrayAsyncTaskCallback`, which is related to the service's `JSONArray`
return type. Then it implements and instantiates the callback class. Lastly, it
sets the callback on the session and calls the Liferay service.

The `onFailure()` method is called if an exception occurs during the request.
This could be triggered by either a connection exception (e.g., a request
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
above, we used a `JSONArrayAsyncTaskCallback` instance since `getGroupUsers`
returns a `JSONArray`. 

It's also possible to use a generic `AsyncTaskCallback` implementation called
`GenericAsyncTaskCallback`. For this implementation, you must implement a
transform method and handle JSON parsing yourself.

---

![Note](../../images/tip-pen-paper.png) **Note:** If you still don't want to use
any of these callbacks, you can implement `AsyncTaskCallback` directly, but be
careful, you should always get the first element of the `JSONArray` passed as a
parameter to the `onPostExecute(JSONArray jsonArray)` method (i.e.,
`jsonArray.get(0)`).

---

After the request has finished, the `onSuccess()` method is called on the main
UI thread. Since the request is asynchronous, the service call immediately
returns a `null` object. The service delivers the service's real return value to
the callback's `onSuccess()` method, instead. 

Besides using synchronous and asynchronous requests, you can also send requests
using batch processing. Let's learn about this next.

### Sending Your Android App's Requests Using Batch Processing [](id=batch-android-app-requests-to-liferay-portal-6-2-dev-guide-en)

The mobile SDK also allows sending requests using batch processing, which can be
much more efficient than sending separate requests. For example, suppose
you want to delete 10 blog entries at the same time; instead of making one
request for each deletion, you can create a batch of calls and send them all
together.

Here is a code snippet from an app that deletes blog entries synchronously as a
batch: 

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
to reuse the same credentials. Then, it makes service calls as usual. With
asynchronous calls, these methods would return a `null` object immediately. 

Finally, it calls the `invoke()` method from the batch session object. It
returns a `JSONArray` containing the results for each service call. Since there
are three `deleteEntry` calls, the `jsonArray` contains three objects. The order
of the result matches the order of the service calls. 

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

It's just that easy to make efficient service calls in batch! 

Next, let's dive into using the iOS SDK to access Liferay services.

## Using the iOS SDK [](id=use-ios-sdk-to-call-services-liferay-portal-6-2-dev-guide-en)

You've just created a custom iOS app and now want your app to access Liferay
services. How do you access Liferay services from an iOS mobile app? Use
Liferay's iOS SDK, of course. All you'll need to do is download the SDK and
you'll immediately be able to access them. If you'd like to invoke remote web
services, you'll need to generate the client libraries. You can learn more about
the SDK builder and how to generate client libraries by reading the
[Building an SDK for Custom Portlet Services](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/build-sdk-for-custom-portlet-services-liferay-portal-6-2-dev-guide-en)
section. 

Let's get started by downloading the iOS SDK and configuring it in your iOS
environment! 

### Setting Up the iOS SDK [](id=setup-liferay-mobile-sdk-for-ios-liferay-portal-6-2-dev-guide-en)

To install the iOS SDK to your machine, you'll need to download the latest
version of `liferay-ios-sdk.zip`. You can download this file from the Liferay
Mobile SDK [Downloads](https://github.com/liferay/liferay-mobile-sdk/releases/)
page. We'll be providing installation instructions assuming you're using the
XCode developer tool provided by Apple, which can be downloaded from the [Mac
App Store](https://itunes.apple.com/us/app/xcode/id497799835?ls=1&mt=12).

<!-- Replace Downloads page with Official Downloads page on liferay.com when
available:
https://www.liferay.com/community/liferay-projects/liferay-mobile-sdk/ -->

After you've downloaded the Zip file, unzip it into your XCode project. Within
XCode, right-click on your project and click *Add Files to <PROJECT_NAME>*.
Then, add both `core` and `v62` folders. The `v62` folder name can change for
each Liferay version. In this example, the mobile SDK is built for Liferay 6.2.

Awesome! Let's learn how to configure the SDK next.

### Accessing Services from Your iOS App [](id=access-liferay-services-from-ios-app-liferay-portal-6-2-dev-guide-en)

For your mobile app to access the mobile SDK, you'll need to complete several
steps:

1. Create a session.
2. Import the Liferay services for your app to use.
3. Create a service object and call it's services.

We'll demonstrate these steps by providing access to a sample Blogs app. Note
that the following code snippets are written in the *Objective C* programming
language. Let's begin! 

#### Step 1: Create a session [](id=step-1-create-a-session-liferay-portal-6-2-dev-guide-08-en-0)

	#import "LRSession.h"

	LRSession *session = [[LRSession alloc] init:@"http://localhost:8080" username:@"test@liferay.com" password:@"test"];

To learn more about the session and its three parameters, refer to
[Setting Up the Android
SDK](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/setup-liferay-mobile-sdk-for-android-liferay-portal-6-2-dev-guide-en).

#### Step 2: Import the Liferay services for your app to use [](id=step-2-import-the-liferay-services-for-liferay-portal-6-2-dev-guide-08-en-0)

For this sample app, we'll import the [`BlogsEntryService`](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portlet/blogs/service/BlogsEntryService.html). 

	#import "LRBlogsEntryService_v62.h"

Note, the Liferay version (`.v62`) is used in the package namespacing.
Since the SDK is built for a specific Liferay version, service classes are
separated by their package name. Our mobile SDK classes use the `.v62`
package, which means this SDK is compatible with Liferay 6.2. However, you can
use several SDKs simultaneously to support different Liferay versions.

Your portal's JSON web services page (e.g.,
[http://localhost:8080/api/jsonws](http://localhost:8080/api/jsonws))
lists all available portal services and plugin services. 

#### Step 3: Create a service object and call its services [](id=step-3-create-a-service-object-and-cal-liferay-portal-6-2-dev-guide-08-en-0)

For this sample app, we'll create an `LRBlogsEntryService_v62` object and
make a service call that fetches all blog entries from the *Guest* site. In
this example, the `groupId` is `10184`. 

	LRBlogsEntryService_v62 *service = [[LRBlogsEntryService_v62 alloc] init:session];

	NSError *error;
	NSArray *entries = [service getGroupEntriesWithGroupId:1084 status:0 start:-1 end:-1 error:&error];


This is a basic example of a synchronous service call-- which means the
method will only returns after the request is finished.

Service method return types can be `void`, `NSString`, `NSArray`,
`NSDictionary`, `NSNumber`, and `BOOL`.

---

![Note](../../images/tip-pen-paper.png) **Note:** Many service methods
require a group ID as a parameter. 
You can call `[LRGroupService_v62 getUserSitesGroups:&error]`, which uses
Liferay Portal's
[`GroupService.getUserSitesGroups`](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/service/GroupService.html#getUserSitesGroups()),
to get a site group. 

---

That's it! You've given the blogs app access to the `BlogsEntryService`. Next,
let's discuss making asynchronous HTTP requests.

### Invoking Services Asynchronously from Your iOS App [](id=invoke-services-asynchronously-ios-liferay-portal-6-2-dev-guide-en)

The SDK allows asynchronous HTTP requests; all you need to do is set a
callback to the session object. You can set the callback to `nil` if you want to
make synchronous requests again. 

Let's continue our example with the blogs app. To configure asynchronous
requests, the first thing we'll need to do is create a class that conforms to
the `LRCallback` protocol.

    #import "LRCallback.h"

    @interface BlogsEntriesCallback : NSObject <LRCallback>

    @end


    #import "BlogsEntriesCallback.h"

    @implementation BlogsEntriesCallback

    - (void)onFailure:(NSError *)error {
        // Implement error handling code
    }

    - (void)onSuccess:(id)result {
        // Called after request has finished successfully
    }

    @end

Then, set this callback to the session and call your service as usual:

    BlogsEntriesCallback *callback = [[BlogsEntriesCallback alloc] init];

    [session setCallback:callback];
    [service getGroupEntriesWithGroupId:1084 status:0 start:-1 end:-1 error:&error];

If a server side exception or a connection error occurs during the request, the
`onFailure` method is called with an `NSError` that contains information about
the error.

Since the request is asynchronous, the `getGroupEntriesWithGroupId` method
returns immediately with `nil`, and the `onSuccess` method of your callback is
invoked with the results once the request has finished successfully.

The `onSuccess` result parameter doesn't have a specific type. Therefore, you
need to check the service method signature in order to figure out which type you
can cast to safely. In this example, the `getGroupEntriesWithGroupId` method
returns an `NSArray`; so you can cast to this type.

    - (void)onSuccess:(id)result {
        NSArray *entries = (NSArray *)result;
    }

The `onSuccess` method is called on the main UI thread after the request has
finished.

Let's talk about another popular way to send your app's requests: batch
processing.

### Sending Your iOS App's Requests Using Batch Processing [](id=batch-ios-app-requests-to-liferay-portal-6-2-dev-guide-en)

Another popular method of sending requests to the mobile SDK is through batch
processing, which can be more efficient then sending requests separately.

For example, suppose you want to delete 10 blog entries at the same time;
instead of making one request for each delete call, you can create a batch of
delete calls and send them together. Here's an example of how to do this:

    #import "LRBatchSession.h"

    LRBatchSession *batch = [[LRBatchSession alloc] init:@"http://localhost:8080" username:@"test@liferay.com" password:@"test"];
    LRBlogsEntryService_v62 *service = [[LRBlogsEntryService_v62 alloc] init:batch];
    NSError *error;

    [service deleteEntryWithEntryId:1 error:&error];
    [service deleteEntryWithEntryId:2 error:&error];
    [service deleteEntryWithEntryId:3 error:&error];

    NSArray *entries = [batch invoke:&error];

First, create an `LRBatchSession` session. You can either pass credentials or
pass another `session` to the constructor. This is useful when you already have
a `Session` object and want to reuse the same credentials. Then make the service
calls as usual. With asynchronous calls, these methods return `nil` right away.

Finally, call `[bath invoke:&error]`, which returns an `NSArray` containing the
results for each service call. Since there are three `deleteEntryWithEntryId`
calls, the `entries` array contains three objects. The order of the results
matches the order of the service calls.

If you want to make batch calls asynchronously, set the callback to the session.

    [batch setCallback:callback];

The return type for batch calls is always an `NSArray`.

Next, let's learn how to use Liferay Developer Studio to customize your Android
app by giving it access to the Liferay Mobile SDK.

## Accessing the Mobile SDK Using Eclipse

Liferay provides the *Liferay Mobile SDK* plugin for developers to use when
building Mobile SDK libraries and generating custom services for their app. By
using this plugin, you can simply import your app in Eclipse and inject it with
Mobile SDK JARs that your app can reference. Also, the Mobile SDK Eclipse plugin
can generate services for an app already deployed to Liferay Portal. Eclipse
only supports Android apps, so you're unable to customize iOS apps.

The Liferay Mobile SDK plugin can be downloaded to provide this useful
interface, but first, you need to install a few things to get up and running
with Eclipse. There are two ways to install the necessary Android capabilities:
install the *Android SDK Tools* and *ADT (Android Development Tools)* Eclipse
plugins to a pre-existing Eclipse instance, or install the *Android Developer
Tools SDK* bundle provided by Google, which includes these two plugins.

You can follow the [Setting Up an Existing
IDE](http://developer.android.com/sdk/installing/index.html) section on
Android's site for instructions on how to download and install the [ADT
Eclipse](http://developer.android.com/tools/sdk/eclipse-adt.html) plugin to a
pre-existing IDE. Furthermore, follow the [Setting Up the ADT
Bundle](http://developer.android.com/sdk/installing/bundle.html) section on
Android's site for instructions on downloading and installing the ADT bundle.

After you've installed the ADT plugin to your Eclipse instance, install the
*Liferay Mobile SDK* plugin. To do this, navigate to the *Help* &rarr; *Install
New Software* menu and copy the following link into the *Work with* field:

    http://releases.liferay.com/tools/ide/latest/milestone/

A *Liferay* drop-down menu appears; click the drop-down arrow and select
*Liferay Mobile SDK* and click *Next*.

![Figure 8.3: Download the *Liferay Mobile SDK* plugin for Eclipse by navigating to the *Install New Software* menu.](../../images/download-mobile-sdk-plugin.png)

Click *Finish* to complete the install.

Terrific! Eclipse is now ready to build the Liferay Mobile SDK and generate
custom services for your Android app! Let's show adding the Liferay Mobile SDK
libraries into your app next.

### Adding Mobile SDK Libraries

You've been developing an Android app for months and you're almost ready to
publish it. However, there are a few Liferay core web services you'd like to
access for your app. The Mobile SDK Eclipse plugin gives you the easy option of
importing your existing Android application project and adding the Liferay
Android Mobile SDK libraries to your project as JAR files. These JARs allow your
app to access Liferay core web services.

---

![Note](../../images/tip-pen-paper.png) **Note:** The action for injecting
Liferay Android Mobile SDK library JARs is only available for Android apps that
were created using the ADT plugin for Eclipse. Therefore, if you build your
Android app with Gradle or Maven, the action does not work.

---

To add the library JAR files, right-click your app and click *Configure* &rarr;
*Add Liferay Android SDK libraries*. Eclipse gives you a success message
indicating that the Liferay Android SDK libraries were added to the project.

Wasn't that easy? Your Android app now has the Liferay Android SDK libraries and
can call any of Liferay's core web services! Let's take a look behind the scenes
and discover what happened. In your project's `libs` directory, the Liferay
Android SDK `.jar` and `.properties` files were added. Furthermore, the `src`
folder containing the source JAR was included. Lastly, the project library JAR
is added to the classpath in the `Android Private Libraries` folder.

![Figure 8.4: The Mobile SDK Eclipse plugin makes it easy to inject Liferay Android SDK libraries accessible by your app.](../../images/mobile-sdk-jars.png)

Next, let's learn how to generate Liferay custom portlet services.

### Generating Custom Portlet Services

The Liferay Mobile SDK offers the generation of custom portlet services. If you
have your own custom portlets deployed to Liferay Portal and you'd like to
generate new Mobile SDK services for those remote JSON services, you'll need to
use the Mobile SDK Builder program.

You can specify a running Liferay Portal instance in Eclipse, and Eclipse
queries the server for all the remote APIs that are available to generate custom
services. Once Eclipse finds your custom services and you specify the services
you want to generate, a custom JAR is generated by the Mobile SDK Builder and
copies the resulting JAR and its sources to the user's Android project.

Now that you know the basic gist of how the Mobile SDK Eclipse plugin generates
custom portlet services, let's demonstrate how to do this.

Right-click your Android app project in the *Package Explorer* and navigate to
*Configure* &rarr; *Generate services for Liferay custom portlets*. This opens a
wizard where you'll specify your running portal server.

![Figure 8.5: You can select *Check Status* to verify that you've specified the correct running portal server.](../../images/service-generation-wizard.png)

Specify your server's *URL*, *Username*, and *Password*. Notice the *Status* and
*Summary* fields; these fields indicate your running portal instance's status
after clicking the *Check Status* button.

Eclipse saves all server instances you've specified in the bottom field. This
allows you to work with multiple servers. The servers are listed by *URL* and
*Last Available Summary*.

Once you've checked the status of your server, click *Next*. Eclipse queries the
server you specified for all the remote APIs that are available to generate
custom services.

In the next menu, specify the Java package to be used for the generated code.
You can select the *Browse* button to browse for available packages. You're also
presented with available custom APIs from your server that you can select to
generate the Java package for.

![Figure 8.6: Eclipse queries all remote APIs that are available for generating custom portlet services.](../../images/choosing-apis-and-packages-wizard.png)

For demonstration purposes, we'll specify the Java package as
`com.liferay.custom` and select the *opensocial-portlet* &rarr; *gadget* custom
API. You can view what this looks like in the figure below.

![Figure 8.7: Eclipse injects your Android project with the appropriate `.jar` and `.properties` files.](../../images/injected-jars-mobile-sdk.png)

Notice that in the figure, we have the standard and custom `.jar` and
`.properties` files. When generating custom services, Eclipse adds both the standard
JAR and the custom JAR with the specified generated Java package.

You've generated custom services for your Liferay portlets! Now your portlets
have access to the Liferay Mobile SDK services you specified. Next, let
demonstrate how to create a Liferay Android Sample Project using Eclipse.

### Creating a Liferay Android Sample Project

So far, we've demonstrated how to add Liferay Mobile SDK libraries to your
pre-existing Android app. The Mobile SDK Eclipse plugin also offers the ability
to create a new sample Android project from scratch that includes Liferay Mobile
SDK libraries and sample code. This gives you the ability to view what a Liferay
Android project looks like. Let's dive in and explain how to do this!

To start the Liferay Android Sample Project wizard, navigate to *File* &rarr;
*Project...* &rarr; *Liferay* &rarr; *Liferay Android Sample Project* and then
click *Next*. Specify the project name, directory location, and Android target
SDK. Notice that you also have the option to choose a sample project template
from the Mobile SDK preferences menu. Click *Finish* when you're ready to create
the sample Android app.

![Figure 8.8: The Liferay Android Sample Project wizard gives you several options to customize your sample app.](../../images/mobile-sdk-sample-project-wizard.png)

The sample Android app has the standard Liferay Mobile SDK JARs as well as
sample classes that characterize how the app integrates with your portal
instance. You can learn more about the Android application standard architecture
by visiting Android's *Getting Started* guide, where they'll demonstrate
standard architecture as well as activity classes, model classes, etc.

The Liferay Android Sample Project that is built by default displays details of
Liferay contacts. Just as evidence that this sample project is using the Liferay
Mobile SDK libraries, open the sample app's `MainActivity` class from the
`src/com/liferay/mobile/sample/activity` directory. In Eclipse, scroll your
mouse over the `Session` object instance within the `onListItemClick()` method.

![Figure 8.9: The `Session` interface is referenced from the sample app's Liferay Mobile SDK libraries.](../../images/sample-mobile-sdk-object.png)

You can see that the `Session` interface is being referenced from the
`com.liferay.mobile.android.service` package. This app is using the Liferay
Mobile SDK from the library JARs of this project.

You can run the sample app using Android's emulator. For instructions on how to
do this, visit Android's [Using the
Emulator](http://developer.android.com/tools/devices/emulator.html) section.

<!-- Commented out the SDK Builder content. Bruno advised that we've switched to
Gradle (instead of Ant) for building the Mobile SDK, so we'll either need to
overhaul this section, or remove it and point readers to Bruno's Github page.

Maven can also be used for building the SDK. Coordinating with Bruno and Greg on
this section and its value in the guide.
-Cody -->

<!--
Next, let's learn how to build your custom portlet SDK and generate client
libraries. 

## Building an SDK for Custom Portlet Services [](id=build-sdk-for-custom-portlet-services-liferay-portal-6-2-dev-guide-en)

What if your app needs to access Liferay's remote services? You'll need to
generate the client libraries to access them. You can accomplish this by 
using the SDK Builder.

The Liferay Mobile SDK comes with an SDK Builder that generates the mobile SDK
for the Android and iOS platforms. Think of it as a Service Builder on the
client side; it generates code that allows your mobile app to access your
portlet's custom services. It is template-based, making it easy to extend to
various mobile platforms. The SDK Builder generates client libraries that allow
your native mobile apps to invoke the remote web services of a portal instance
and any of its custom portlets built with Liferay's [Service
Builder](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/generating-your-service-layer-liferay-portal-6-2-dev-guide-04-en).

Let's configure your portlet's remote services so we can build the SDK!

### Configuring your Portlet's Remote Services [](id=configure-portlet-remote-services-liferay-portal-6-2-dev-guide-08-en)

In order for the SDK Builder to discover a portlet's remote services, the
portlet's services must be made available and be accompanied by a Web Service
Deployment Descriptor (WSDD).

If you're in the process of developing the portlet, see [Generating Your Service
Layer](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/generating-your-service-layer-liferay-portal-6-2-dev-guide-04-en)
for in-depth instructions on implementing remote services. For your convenience,
the following steps outline how to implement remote services for your portlet
and make them available for the SDK Builder to discover: 

1. Set your entity's `remote-service="true"` in your portlet's `service.xml`
file and run `ant build-service`.

2. Implement your remote services in the generated `[Entity]ServiceImpl.java`
source files and run `ant build-service` again. 

3. Run `ant build-wsdd` to build your Web Service Deployment Descriptor (WSDD).
The SDK Builder relies on the WSDD to discover your portlet's remote services;
so make sure to run `ant build-wsdd` before deploying your portlet.

4. Run `ant deploy` to deploy your portlet with its WSDD.

Now you're ready to use the SDK Builder to generate a custom portlet SDK for
developing your mobile app. You'll be able to invoke your service's methods,
like `[Entity]Service.bar();`, from your mobile app. The Liferay Mobile SDK
takes care of making JSON Web Service requests to your portlet.

Next, let's configure the SDK builder for building your custom portlet SDK.

### Configuring the SDK Builder [](id=configure-sdk-builder-liferay-portal-6-2-dev-guide-en)

In order to use the SDK Builder, you'll need to have the Liferay Mobile SDK
project on your local machine. If you haven't done so already, fork and clone
the [Liferay Mobile SDK](https://github.com/liferay/liferay-mobile-sdk) project
from GitHub. Follow the [Fork A
Repo](https://help.github.com/articles/fork-a-repo) instructions for details.

The SDK Builder is invoked using Ant, but before building an SDK, you must set
some properties so that the SDK Builder can communicate with your portlet
services and create an SDK specific to your mobile platform. Follow the steps
below to set these properties:

1. Create a file in the root folder called `build.${username}.properties`,
where `${username}` is your computer user name.

2. Here are the important properties to set:

	* `url` - The URL to your Liferay instance.

	* `context` - Your portlet's web context. For example, if you're generating
	an SDK for Liferay's Calendar portlet, which is generally deployed to the
	`calendar-portlet` context, then you should set your context value to
	`context=calendar-portlet`. Under the hood, the SDK Builder tries to access
	`http://localhost:8080/calendar-portlet/api/jsonws?discover` to find out
	which services are available for this portlet. Check in a browser to ensure
	this URL is working before running the SDK. If it's not running, you may
	have forgotten to run `ant build-wsdd` on the portlet.

	* `filter` - Specifies your portlet's entities whose services to access; a
	blank value indicates the services of all of the portlet's entities. For
	example, the Calendar portlet has entities such as `CalendarBooking` and
	`CalendarResource`. To generate an SDK for only the `CalendarBooking`
	entity, set the filter's value to `calendarbooking`, all in lowercase. The
	SDK Builder then makes requests to
	`http://localhost:8080/calendar-portlet/api/jsonws?discover=/calendarbooking/*`.
	If you set `filter=`, specifying no filter value, the remote services of
	*all* of the portlet's entities are made available to your mobile SDK.

	* `package` - On Android, this is the package to which your SDK's classes
	are written. The iOS platform does not use packages. Note that the Liferay
	Portal version is appended to the end of the package name. So, if you
	specified `com.liferay.mobile.android` as your package, the SDK
	Builder appends the Liferay Portal version (e.g., `v62`) to it, 
	like `com.liferay.mobile.android.v62`. Appending the Liferay Portal version
	prevents collisions between classes with the same names written for
	different versions of Liferay Portal.

	* `destination` - Specifies the root folder in which to save your generated
	files. The default destination root folder is `gen`. On Android, the files
	are saved to `[destination]/android/src`. On iOS, the files are saved to
	`[destination]/ios`.

Here's an example of configuring the SDK Builder to generate an Android mobile
SDK for a portlet with the web context value `my-portlet`:

    url=http://localhost:8080
    context=my-portlet
    filter=
    package=com.mycompany.mobile.android
    destination=gen

Now that you've configured the SDK Builder, you're ready to build a platform
specific SDK for your portlet's remote services. 

### Building the Liferay Android SDK [](id=building-the-liferay-android-sdk-liferay-portal-6-2-dev-guide-08-en)

To build the service related source files for your Liferay Android SDK, run the
following command from the `liferay-mobile-sdk` project's root folder:

    ant -f build-android.xml

The source files are written to your `[destination]/android/src` folder.

To build a `.jar` file containing the generated service and utility classes, run
the following command:

    ant -f build-android.xml jar

The `liferay-android-sdk.jar` file is written to your `dist/android/` folder.
After completing these commands, you're ready to use the
`liferay-android-sdk.jar` in your Android project and have no external
dependencies.

To learn how to use the Liferay Android SDK in your mobile app, visit the
[Using the Android SDK](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/use-the-android-sdk-to-call-services-liferay-portal-6-2-dev-guide-en).

### Building the Liferay iOS SDK [](id=building-the-liferay-ios-sdk-liferay-portal-6-2-dev-guide-08-en)

To build the service related source files for your Liferay iOS SDK, run the
following command from the `liferay-mobile-sdk` project's root folder:

    ant -f build-ios.xml

The source files are written to your `[destination]/ios` folder.

To build a `.zip` file containing the generated service and utility classes, run
the following command:

    ant -f build-ios.xml zip

The `liferay-ios-sdk.jar` file is written to your `dist/ios` folder. After
completing these commands, you're ready to use the contents of your
`liferay-ios-sdk.zip` file in your iOS project. Simply unzip its contents and
add all of the files to your XCode project. The SDK is free of any external
dependencies.

To learn how to use the Liferay iOS SDK in your mobile app, see the
[Using the iOS SDK](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/use-ios-sdk-to-call-services-liferay-portal-6-2-dev-guide-en).

-->

## Summary [](id=mobile-sdk-summary-liferay-portal-6-2-dev-guide-08-en)

In this chapter, we showed you how easy it is to download and configure
Liferay's Mobile SDK. We explained the Android and iOS mobile platforms
separately, giving you step-by-step examples for accessing Liferay services. We
even dove into a working example with the sample *Contacts App* by configuring
Android Developer Tools. Then, we explored Liferay Developer Studio's
integration with the Mobile SDK. LDS offers ways to build standard and custom
JARs for an Android app to reference. LDS also gives you the option to create a
sample Liferay Android project to familiarize yourself with a Liferay Android
app using the Mobile SDK.

<!--
Then, we explored the SDK Builder and how to generate client libraries to access
your portlet's custom services. Of course, this was also broken down into
separate Android and iOS sections so that building the SDK for either platform
is easier than ever.
-->

Did you know that there is a breed of social applications called OpenSocial
gadgets that lend themselves well to humans sharing information and application
functionality within defined networks? They are light-weight and easy to write
and distribute. We'll get into OpenSocial gadgets next. 

