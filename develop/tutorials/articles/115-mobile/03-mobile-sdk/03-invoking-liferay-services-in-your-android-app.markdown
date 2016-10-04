# Invoking Liferay Services in Your Android App [](id=invoking-liferay-services-in-your-android-app)

Once the appropriate Mobile SDKs are set up in your Android project, you can 
access and invoke Liferay services in your app. The basic steps for doing this 
are listed here: 

1. Create a session.
2. Import the Liferay services you need to call.
3. Create a service object and call the service methods.

This tutorial takes you through these steps. Also, since some Liferay service 
calls require special considerations, you're also shown how to handle them. Now 
go ahead and get started learning how to invoke Liferay services in your Android 
app! 

## Step 1: Create a Session [](id=step-1-create-a-session)

A session is a conversion state between the client and server, that consists of 
multiple requests and responses between the two. You need a session to pass 
requests between your app and the Mobile SDK. In most cases, sessions need to be 
created with user authentication. The imports and code required to create a 
session are shown here:

    import com.liferay.mobile.android.auth.basic.BasicAuthentication;
    import com.liferay.mobile.android.service.Session;
    import com.liferay.mobile.android.service.SessionImpl;
    ...
    Session session = new SessionImpl("http://10.0.2.2:8080", 
        new BasicAuthentication("test@liferay.com", "test"));

The arguments to `SessionImpl` are used to create the session. The first 
parameter is the URL of the Liferay instance you're connecting to. If you're 
running your app on Android Studio's emulator, `http://10.0.2.2:8080` is 
equivalent to `http://localhost:8080`. Be sure to replace this with the correct 
address for your server.

+$$$

**Warning:** Be careful when using administrator credentials on a production 
Liferay instance, as you'll have permission to call any service. Make sure not 
to modify data by accident. Of course, the default administrator credentials 
should be disabled on a production Liferay instance. 

$$$

The second parameter creates a new `BasicAuthentication` object containing the 
user's credentials. Depending on the authentication method used by your Liferay 
instance, you need to provide the user's email address, screen name, or user ID. 
You also need to provide the user's password. The `BasicAuthentication` object 
tells the session to use Basic Authentication to authenticate each service call. 
The Mobile SDK also supports OAuth authentication, as long as the OAuth Provider 
portlet is deployed to your Liferay instance. To learn how to use OAuth 
authentication with the Mobile SDK, see the
[OAuth sample app](https://github.com/brunofarache/liferay-android-sdk-oauth). 
Also, note that the OAuth Provider portlet is only available to customers with 
an active Liferay subscription. 

If you're building a sign in view for your app, you can use the `SignIn` utility
class to check if the credentials given by the user are valid.

    import com.liferay.mobile.android.auth.SignIn;
    ...
    SignIn.signIn(session, new JSONObjectAsyncTaskCallback() {

        @Override
        public void onSuccess(JSONObject userJSONObject) {
            System.out.println("Successful sign-in, user details: " + userJSONObject)
        }

        @Override
        public void onFailure(Exception e) {
            e.printStackTrace();
        }

    });

Note that the Mobile SDK doesn't keep a persistent connection or session with 
the server. Each request is sent with the user's credentials (except when using 
OAuth). However, the `SignIn` class provides a way to return user information 
after a successful sign-in. 

Next, you're shown how to create an unauthenticated session in the limited cases 
where this is possible. 

### Creating an Unauthenticated Session [](id=creating-an-unauthenticated-session)

In some cases, it's possible to create a `Session` instance without user 
credentials. However, most Liferay remote methods don't accept unauthenticated 
remote calls. Making a call with an unauthenticated session generates an 
`Authentication access required` exception in most cases.

Unauthenticated service calls only work if the remote method in the Liferay 
instance or your plugin has the `@AccessControlled` annotation. This is shown 
here for the hypothetical class `FooServiceImpl` and its method `bar`:

    import com.liferay.portal.security.ac.AccessControlled;
    ...
    public class FooServiceImpl extends FooServiceBaseImpl {
    ...
        @AccessControlled(guestAccessEnabled = true)
        public void bar() { ... }
    ...

To make such a call, you need to use the constructor that accepts the server 
URL only:

    Session session = new SessionImpl("http://10.0.2.2:8080");

Fantastic! Now that you have a session, you can use it to call Liferay's 
services.

## Step 2: Import the Liferay Services You Need [](id=step-2-import-the-liferay-services-you-need)

First, you should determine the Liferay services you need to call. You can find 
the available services at 
[`http://localhost:8080/api/jsonws`](http://localhost:8080/api/jsonws). 
Be sure to replace `http://localhost:8080` in this URL with your server's 
address. 

Add the imports for the services you need to call. For example, if you're 
building a blogs app, you can import `BlogsEntryService`: 

    import com.liferay.mobile.android.v62.blogsentry.BlogsEntryService;

Note that the Liferay version (`.v62`) is used in the package namespace. Since 
the Mobile SDK is built for a specific Liferay version, service classes for 
different Liferay versions are separated by their package names. In this 
example, the Mobile SDK classes use the `.v62` package, which means this Mobile 
SDK is compatible with Liferay 6.2. Mobile SDK classes compatible with Liferay 
7.0 use the `v7` package. This namespacing lets your app support multiple 
Liferay versions. 

## Step 3: Create a Service Object and Call its Service Methods [](id=step-3-create-a-service-object-and-call-its-service-methods)

Once you have a session and the required imports, you're ready to make the 
service call. This is done by creating a service object for the service you want 
to call, and then calling its service methods. For example, if you're creating a 
blogs app, you need to use `BlogsEntryService` to get all the blogs entries from 
a site. This is demonstrated by the following code: 

    BlogsEntryService service = new BlogsEntryService(session);

    JSONArray jsonArray = service.getGroupEntries(10184, 0, 0, -1, -1);

This fetches all blog entries from the *Guest* site. In this example, the 
*Guest* site's `groupId` is `10184`. Note that many service methods require 
`groupId` as a parameter. You can get the user's groups by calling the 
`getUserSites()` method from `GroupService`. 

Service method return types can be `void`, `String`, `JSONArray`, or 
`JSONObject`. Primitive type wrappers can be `Boolean`, `Integer`,
`Long`, or `Double`. 

This `BlogsEntryService` call is a basic example of a synchronous service call; 
the method only returns after the request finishes. However, Android doesn't 
allow network communication from an app's main UI thread. Service calls issued 
from the main UI thread need need to be asynchronous. For instructions on doing 
this, see the tutorial 
[Invoking Services Asynchronously from Your Android App](/develop/tutorials/-/knowledge_base/7-0/invoking-services-asynchronously-from-your-android-app).

Great! Now you're familiar with the basics of accessing Liferay services through 
the Mobile SDK. However, there are some special cases you may run into when 
making service calls from your app. These are discussed in the following 
sections. 

## Non-Primitive Arguments [](id=non-primitive-arguments)

There are some special cases in which a service method's arguments aren't 
primitives. In these cases, you should use `JSONObjectWrapper`. For example: 

    JSONObjectWrapper wrapper = new JSONObjectWrapper(new JSONObject());

You must pass a JSON containing the object properties and their values. On the 
server side, your object is instantiated and setters for each property are 
called with the values from the JSON you passed.

There are other cases in which service methods require interfaces or abstract 
classes as arguments. Since it's impossible for the SDK to guess which 
implementation you want to use, you must initialize `JSONObjectWrapper` with
the class name. For example: 

    JSONObjectWrapper wrapper = new JSONObjectWrapper(className, new JSONObject());

The server looks for the class name in its classpath and instantiates the object 
for you. It then calls setters, as in the previous example. The abstract class 
`OrderByComparator` is a good example of this. This is discussed next. 

### OrderByComparator [](id=orderbycomparator)

On the server side, `OrderByComparator` is an abstract class. You must therefore 
pass the name of a class that implements it. For example: 

    String className = "com.liferay.portlet.bookmarks.util.comparator.EntryNameComparator";

    JSONObjectWrapper orderByComparator = new JSONObjectWrapper(className, new JSONObject());

If the service you're calling accepts `null` for a comparator argument, pass 
`null` to the service call. 

You may want to set the ascending property for a comparator. Unfortunately, as 
of Liferay 6.2, most Liferay `OrderByComparator` implementations don't have a 
setter for this property and it isn't possible to set from the Mobile SDK. 
Future Liferay versions may address this. However, you may have a custom 
`OrderByComparator` that has a setter for ascending. In this case, you can use 
the following code: 

    String className = "com.example.MyOrderByComparator";

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("ascending", true);

    JSONObjectWrapper orderByComparator = new JSONObjectWrapper(className, jsonObject);

For more examples, see the test case 
[`OrderByComparatorTest.java`](https://github.com/liferay/liferay-mobile-sdk/blob/master/android/src/test/java/com/liferay/mobile/android/OrderByComparatorTest.java).

### ServiceContext [](id=servicecontext)

Another non-primitive argument is `ServiceContext`. It requires special 
attention because most Liferay service methods require it. However, you aren't 
required to pass it to the SDK; you can pass `null` instead. The server then 
creates a `ServiceContext` instance for you, using default values. 

If you need to set properties for `ServiceContext`, you can do so by adding them 
to a new `JSONObject` and then passing it as the `ServiceContext` argument:

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("addGroupPermissions", true);
    jsonObject.put("addGuestPermissions", true);

    JSONObjectWrapper serviceContext = new JSONObjectWrapper(jsonObject);

For more examples, see the test case 
[`ServiceContextTest.java`](https://github.com/liferay/liferay-mobile-sdk/blob/master/android/src/test/java/com/liferay/mobile/android/ServiceContextTest.java).

### Binaries [](id=binaries)

Some Liferay services require argument types such as byte arrays (`byte[]`) and
Files (`java.io.File`).

The Mobile SDK converts byte arrays to strings before sending the POST request. 
For example, `"hello".getBytes("UTF-8")` becomes a JSON array such as 
`"[104,101,108,108,111]"`. The Mobile SDK does this for you so you don't have 
worry about it; you only need to pass the byte array to the method.

However, you need to be careful when using such methods. This is because you're 
allocating memory for the whole byte array, which may cause memory issues if the 
content is large.

Other Liferay service methods require `java.io.File` as an argument. In these 
cases, the Mobile SDK requires `InputStreamBody` instead. To accomodate this, 
you need to create an `InputStream` and pass it to the `InputStreamBody` 
constructor, along with the file's mime type and name. For example:

    InputStream is = context.getAssets().open("file.png");
    InputStreamBody file = new InputStreamBody(is, "image/png", "file.png");

The Mobile SDK sends a multipart form request to the Liferay instance. On the 
server side, a `File` instance is created and sent to the service method you're 
calling. 

It's also possible to cancel or monitor service calls that upload data to 
Liferay. Every service that uploads data returns an `AsyncTask` instance. You 
can use it to cancel the upload if needed. If want to listen for upload progress 
to create a progress bar, you can create an `UploadProgressAsyncTaskCallback` 
callback and set it to the current `Session` object. Its `onProgress` method is 
called for each byte chunk sent. It passes the total number of uploaded bytes so 
far. For example: 

    session.setCallback(

        new UploadProgressAsyncTaskCallback<JSONObject>() {

            (...)

            public void onProgress(int totalBytes) {
                // This method will be called for each byte chunk sent.
                // The totalBytes argument will contain the total number
                // of uploaded bytes, from 0 to the total size of the
                // request.
            }

            (...)

        }
    );

For more examples on this subject, see the `addFileEntry*` methods in 
  [`DLAppServiceTest.java`](https://github.com/liferay/liferay-mobile-sdk/blob/master/android/src/test/java/com/liferay/mobile/android/DLAppServiceTest.java)

As you can see, the Mobile SDK does a great deal of work for you even when 
special service method arguments are required.

## Related Topics [](id=related-topics)

[Invoking Services Asynchronously from Your Android App](/develop/tutorials/-/knowledge_base/7-0/invoking-services-asynchronously-from-your-android-app)

[Building Mobile SDKs](/develop/tutorials/-/knowledge_base/7-0/building-mobile-sdks)

[Creating iOS Apps that Use the Mobile SDK](/develop/tutorials/-/knowledge_base/7-0/creating-ios-apps-that-use-the-mobile-sdk)
