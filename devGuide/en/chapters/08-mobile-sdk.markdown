
# Leveraging the Mobile SDK

The Liferay Mobile SDK is a framework for building native mobile apps that
integrate with your different Liferay Portal instances and their portlets. The
SDK provides the means for your mobile apps to easily consume Liferay Portal's
core web services and the web services of your custom portlets. It wraps Liferay
JSON web services, takes care of authentication, makes REST (Representational
State Transfer) requests (synchronously or asynchronously), parses JSON results,
and handles server side exceptions.

The Liferay Mobile SDK is compatible with Liferay Portal 6.2 and later, and it
comes with the Liferay Android SDK and Liferay iOS SDK ready for you to download
and use. You can view more about the Liferay Mobile SDK by navigating to its
[Official
Project](https://www.liferay.com/community/liferay-projects/liferay-mobile-sdk/overview)
page. For an illustration for how the mobile SDK works, view Figure 8.1.

![Figure 8.1: The mobile SDK allows you native app to access Liferay services.](../../images/mobile-sdk-diagram.png)

The illustration displays the general process that links the mobile SDK with
your native app. You can create and edit you app using Liferay Developer Studio.
Once the app is created, it can send and receive requests to/from the Liferay
Mobile SDK to enable access to Liferay services. The SDK provides a bridge to
your Liferay instance where you can access hidden complexities like
authentication, permissions, remote service calls, and JSON-wrapped results.

In this chapter, we'll provide information about this process for the
development of Android and iOS apps. We'll discuss the following topics as we
explore Liferay's Mobile SDK:

- Using the Android SDK
- Using the iOS SDK
- Building an SDK for Custom Portlet Services

Let's get started by learning how to access Liferay's core services from an
Android app.

## Using the Android SDK

Suppose you're creating an Android app and would like to access some of
Liferay's core services. All you'll need to do is download the SDK and you'll
immediately be able to access Liferay's core services. If you'd
like to invoke remote web services, you'll need to generate the client
libraries. You can learn more about the SDK builder and how to generate
libraries by visiting the *Building an SDK for Custom Portlet Services* section
of this chapter.

Let's get started by downloading the Android SDK!

### Downloading the Android SDK

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
    session to pass requests between the mobile SDK and your app. This code uses
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
		}

    This code fetches all the users from the site with the `groupId`, which is
    specified in the `getGroupUsers()` method above.

    This is a basic example of a synchronous service call; the method only
    returns after the request is finished. Service method return types can be
    `void`, `String`, `JSONArray`, and `JSONObject`. Primitive type wrappers can
    be `Boolean`, `Integer`, `Long`, and `Double`.
    
    ---
    
    ![Note](../../images/tip-pen-paper.png) **Note:** Many service methods
    require `groupId` as a parameter. You can get the user's groups by calling
    the `getUserSites()` method from `GroupService`. In our previous example,
    the `getGuestGroupId(session)` method does this by retrieving the user's
    group.
    
    ---
    
    As you'll notice when studying our sample app, there are several other
    moving parts to make this app work. However, our demonstration showed the
    basic process of accessing Liferay services through the mobile SDK. Now
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
Android app and the Liferay mobile SDK.

### Using Asynchronous HTTP Requests for Android

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

### Sending Android Requests Using Batch Processing

The mobile SDK also allows sending requests using batch processing, which can be
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

You've just created a custom iOS app and now want your app to access Liferay
services. How do you access Liferay services from an iOS mobile app? The answer
is the Liferay iOS SDK. All you need to do is download the SDK and you'll
immediately be able to access Liferay's core services. If you'd like to invoke
remote web services, you'll need to generate the client libraries. You can learn
more about the SDK builder and how to generate client libraries by reading the
*Building an SDK for Custom Portlet Services* section of this chapter.

Let's download the iOS SDK and get your app configured with Liferay services!

### Downloading the iOS SDK

To install the iOS SDK to your machine, you'll need to download the latest
version of `liferay-ios-sdk.zip`. You can download this file from the Liferay
Mobile SDK [Downloads](https://github.com/liferay/liferay-mobile-sdk/releases/)
page. We'll be providing installation instructions assuming you're using the
XCode developer tool provided by Apple, which can be downloaded from the [Mac
App Store](https://itunes.apple.com/us/app/xcode/id497799835?ls=1&mt=12).

<!-- Replace Downloads page with Official Downloads page on liferay.com when
available:
https://www.liferay.com/community/liferay-projects/liferay-mobile-sdk/ -->

After you've downloaded the ZIP file, unzip it into your XCode project. Within
XCode, right-click on your project and click *Add Files to <PROJECT_NAME>*.
Then, add both `core` and `v62` folders. The `v62` folder name can change for
each Liferay version. In this example, the mobile SDK is built for Liferay 6.2.

Awesome! Let's learn how to configure the SDK next.

### Configuring the iOS SDK

For your mobile app to access the mobile SDK, you'll need to complete several
steps. We'll demonstrate these steps by providing access to a sample Blogs app.
Note that the following code snippets are written in the *Objectve C*
programming language. Let's begin!

1. Create a `Session` with user credentials:

        #import "LRSession.h"

        LRSession *session = [[LRSession alloc] init:@"http://localhost:8080" username:@"test@liferay.com" password:@"test"];

    To learn more about the session and its three parameters, reference the
    *Configuring the Android SDK* section.

2. Check which Liferay services you need to build your app by navigating to
[http://localhost:8080/api/jsonws](http://localhost:8080/api/jsonws). This page
lists all available portal services and plugin services. For this example, we'll
import the `BlogsEntryService`.

        #import "LRBlogsEntryService_v62.h"

    Since the SDK is built for a specific Liferay version, service class names
    have the Liferay version they are built for. In this case, it's `_v62`,
    which means the SDK is built for Liferay 6.2. You can use several SDKs at
    the same time to support different Liferay versions.

3. Create an `LRBlogsEntryService_v62` object and make a service call.

        LRBlogsEntryService_v62 *service = [[LRBlogsEntryService_v62 alloc] init:session];

        NSError *error;
        NSArray *entries = [service getGroupEntriesWithGroupId:1084 status:0 start:-1 end:-1 error:&error];

    This fetches all blog entries from the *Guest* site. In this example, the
    `groupId` is equal to 10184.
    
    This is a basic example of a synchronous service call-- which means the
    method will only return after the request is finished.
    
    Service method return types can be `void`, `NSString`, `NSArray`,
    `NSDictionary`, `NSNumber`, and `BOOL`.
    
    ---
    
    ![Note](../../images/tip-pen-paper.png) **Note:** Many service methods
    require `groupId` as a parameter. You can get the user's groups by calling
    `[LRGroupService_v62 getUserSites:&error]`.
    
    ---

That's it! You've given the blogs app access to the `BlogsEntryService`. Next,
let's discuss asynchronous HTTP requests.

### Using Asynchronous HTTP Requests for iOS

The iOS SDK allows asynchronous HTTP requests, which allows your app to send
requests to the SDK and continue processing during the wait time for the return
request. The only configuring you'll need to do is set a callback to the session
object. You can set the callback to `nil` if you want to make synchronous
requests again.

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

Then, set this callbck to the session and call your service as usual:

    BlogsEntriesCallback *callback = [[BlogsEntriesCallback alloc] init];

    [session setCallback:callback];
    [service getGroupEntriesWithGroupId:1084 status:0 start:-1 end:-1 error:&error];

If a server side exception or a connection error occurs during the request, the
`onFailure` method is called with an `NSError` that contains information about
the error.

Since the request is asynchronous, the `getGroupEntriesWithGroupId` method
returns immediately with nil, and the `onSuccess` method of your callback is
invoked with the results once the request has finished successfully.

The `onSuccess` result parameter doesn't have a specific type. Therefore, you
need to check the service method signature in order to figure out which type you
can cast to safely. In this example, the `getGroupEntriesWithGroupId` method
retruns an `NSArray`, so you can cast to this type.

    - (void)onSuccess:(id)result {
        NSArray *entries = (NSArray *)result;
    }

The `onSuccess` method is called on the main UI thread after the request has
finished.

Let's talk about another popular way to send your app's requests: batch
processing.

### Sending iOS Requests Using Batch Processing

Another popular method of sending requests to the mobile SDK is batch
processing, which can be more efficient in some cases. For example, suppose you
want to delete 10 blog entries at the same time; instead of making one request
for each delete call, you can create a batch of delete calls and send them
together. Here's an example of how to do this:

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
calls as usual. With asynchronous calls, these methods will return `nil` right
away.

Finally, call `[bath invoke:&error]`; this returns an `NSArray` containing the
results for each service call. Since there are three `deleteEntryWithEntryId`
calls, the entries array contains three objects. The order of the results
matches the order of the service calls.

If you want to make batch calls asynchronously, set the callback to the session.

    [batch setCallback:callback];

The return type for batch calls is always an `NSArray`.

Next, let's learn how to build the SDK and generate client libraries.

## Building an SDK for Custom Portlet Services

What if your app needs to access Liferay's remote services? You'll need to build
the mobile SDK and generate the client libraries. You can accomplish this by
using the SDK Builder.

The Liferay Mobile SDK comes with an SDK Builder that generates the mobile SDK
for the Android and iOS platforms. Think of it as a Service Builder on the
client side; it generates code that allows your mobile app to access your
portlet's custom services. It is template-based, making it easy to extend to
various mobile platforms. The SDK Builder generates client libraries that allow
your native mobile apps to invoke remote web services of a portal instance and
any of its custom portlets built with Liferay's [Service
Builder](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/generating-your-service-layer-liferay-portal-6-2-dev-guide-04-en).

Let's configure your portlet's remote services so we can build the SDK!

### Configuring your Portlet's Remote Services

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

Now, you are ready to use the SDK Builder to generate a custom portlet SDK for
developing your mobile app. You'll be able to invoke your service's methods,
like `[Entity]Service.bar();`, from your mobile app. The Liferay Mobile SDK
takes care of making JSON Web Services requests to your portlet.

Next, let's configure the SDK builder for building your custom portlet SDK.

### Configuring the SDK Builder

In order to use the SDK Builder, you'll need to have the Liferay Mobile SDK
project on your local machine. If you haven't done so already, fork and clone
the [Liferay Mobile SDK](https://github.com/liferay/liferay-mobile-sdk) project
from Github. Follow the [Fork A
Repo](https://help.github.com/articles/fork-a-repo) instructions for details.

The SDK Builder is invoked using Ant, but before building an SDK, you must set
some properties so that the SDK Builder can communicate with your portlet
services and create an SDK specific to your mobile platform. Follow the steps
below to set these properties:

1. Create a file in the root folder called `build.${user.name}.properties`,
where `${user.name}` is your computer user name.

2. Here are the important properties to set:

	* `url` - The URL to your Liferay instance.

	* `context` - Your portlet's web context. For example, if you're generating
	an SDK for Liferay's Calendar portlet, which is generally deployed to the
	`calendar-portlet` context, then you should set your context value to
	`context=calendar-portlet`. Under the hood, the SDK Builder tries to
	access `http://localhost:8080/calendar-portlet/api/jsonws?discover` to find
	out which services are available for this portlet. Check in a browser if
	this URL is working before running the SDK. If it's not running, you may
	have forgotten to run `ant build-wsdd` on the portlet.

	* `filter` - Specifies your portlet's entities whose services to access; a
	blank value indicates the services of all of the portlet's entities. For
	example, the Calendar portlet has entities such as `CalendarBooking` and
	`CalendarResource`. To generate an SDK for only the `CalendarBooking`
	entity, set the filter's value to `calendarbooking`, all in lowercase. The
	SDK Builder will then make requests to the
	`http://localhost:8080/calendar-portlet/api/jsonws?discover=/calendarbooking/*`.
	If you set `filter=`, specifying no filter value, the remote services of
	*all* of the portlet's entities will be made available to your mobile SDK.

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

Here's an example of configuring the SDK Builder to generate a mobile SDK for
a portlet with the web context value `my-portlet`:

    url=http://localhost:8080
    context=my-portlet
    filter=
    package=com.mycompany.mobile.android
    destination=gen

Now that you've configured the SDK Builder, you're ready to build a platform
specific SDK for your portlet's remote services. 

### Building the Liferay Android SDK

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
[Using the Android SDK](http://www.liferay.com/documentation/liferay-portal/6.2/development).

<!-- Replace link once it's available in liferay.com Dev Guide -->

### Building the Liferay iOS SDK

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
[Using the iOS SDK](http://www.liferay.com/documentation/liferay-portal/6.2/development).

<!-- Replace link once it's available in liferay.com Dev Guide -->

## Summary

In this chapter, we showed you how easy it is to download and configure
Liferay's Mobile SDK. We explained the Android and iOS mobile platforms
separately, giving you step-by-step examples for accessing Liferay services. We
even dove into a working example with the sample *Contacts App* by configuring
Android Developer Tools. Then, we explored the SDK Builder and how to generate
client libraries to access your portlet's custom services. Of course, this was
also broken down into separate Android and iOS sections so that building the SDK
for either platform is easier than ever.

Next, we'll take a look at the OpenSocial framework and how OpenSocial gadgets
can increase your portals effectiveness.

