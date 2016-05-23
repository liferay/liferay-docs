# Invoking Liferay Services in Your iOS App [](id=invoking-liferay-services-in-your-ios-app)

Once the appropriate Mobile SDKs are set up in your iOS project, you can access 
and invoke Liferay services in your app. The basic steps for doing this are 
listed here: 

1. Create a session.
2. Import the Liferay services you need to call.
3. Create a service object and call the service methods.

This tutorial takes you through these steps. Also, since some Liferay service 
calls require special considerations, you're shown how to handle them. Note that 
the code snippets in this tutorial are written in *Objective-C*. Now go ahead 
and get started learning how to invoke Liferay services in your iOS app! 

## Step 1: Create a Session [](id=step-1-create-a-session)

A session is a conversion state between the client and server, consisting of 
multiple requests and responses between the two. You need a session to pass 
requests between your app and the Mobile SDK. In most cases, sessions need to be 
created with user authentication. The imports and code required to create a 
session are shown here: 

    #import "LRBasicAuthentication.h"
    #import "LRSession.h"

    LRSession *session = [[LRSession alloc] initWithServer:@"http://localhost:8080" 
        authentication:[[LRBasicAuthentication alloc] initWithUsername:@"test@liferay.com" password:@"test"]];

The `LRSession` object is created with initializers specifying the Liferay 
instance to connect to and the credentials of the user to authenticate. The 
`initWithServer` parameter sets the URL of the Liferay instance you're 
connecting to. In this case, the Liferay instance is running on 
`http://localhost:8080`. The iOS emulator is also running on the same machine. 
Next, the `authentication` parameter takes an `LRBasicAuthentication` instance 
with the credentials of the user to authenticate. Depending on the 
authentication method used by your Liferay instance, you need to provide the 
user's email address, screen name, or user ID to the `initWithUsername` 
parameter. You also need to provide the user's password to the `password` 
parameter. 

Using `LRBasicAuthentication` tells the session to authenticate each service 
call with Basic Authentication. The Mobile SDK also supports OAuth 
authentication, as long as the OAuth Provider portlet is deployed to your 
Liferay instance. To learn how to use OAuth authentication with the Mobile SDK, 
see the
[OAuth sample app](https://github.com/brunofarache/liferay-ios-sdk-oauth). 
Also, note that the OAuth Provider portlet is only available to customers with 
an active Liferay subscription. 

+$$$

**Warning:** Be careful when using administrator credentials on a production 
Liferay instance, as you'll have permission to call any service. Make sure not 
to modify data accidentally. Of course, the default administrator credentials 
should be disabled on a production Liferay instance. 

$$$

If you're building a sign in view for your app, you can use the `LRSignIn` 
utility class to check if the credentials given by the user are valid:

    #import "LRSignIn.h"

    [session
        onSuccess:^(id result) {
            user = result;
            [monitor signal];
        }
        onFailure:^(NSError *e) {
            error = e;
            [monitor signal];
        }
    ];

    [LRSignIn signInWithSession:session callback:session.callback error:&error];

The Mobile SDK doesn't keep a persistent connection or session with the server. 
Each request is sent with the user's credentials (except when using OAuth). 
However, the `SignIn` class provides a way to return user information after a 
successful sign-in. 

You can persist credentials with `LRCredentialStorage`. It safely saves the 
username and password in the keychain:

    [LRCredentialStorage storeCredentialForServer:@"http://localhost:8080" 
        username:@"test@liferay.com" password:@"test"];

After credentials are stored, you can retrieve them with:

    NSURLCredential *credential = [LRCredentialStorage getCredential];

Alternatively, you can create an `LRSession` instance directly with:

    LRSession *session = [LRCredentialStorage getSession];

For more examples of this, see 
[`CredentialStorageTest.m`](https://github.com/liferay/liferay-mobile-sdk/blob/ios-6.2.0.17/ios/Test/CredentialStorageTest.m).

Next, you're shown how to create an unauthenticated session in the limited cases 
where this is possible.

### Creating an Unauthenticated Session [](id=creating-an-unauthenticated-session)

In some cases, it's possible to create an `LRSession` instance without user 
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

    LRSession *session = [[LRSession alloc] initWithServer:@"http://localhost:8080"];

Fantastic! Now that you have a session, you can use it to call Liferay's 
services.

## Step 2: Import the Service [](id=step-2-import-the-service)

First, you should determine the Liferay services you need to call. You can find 
the available services at 
[`http://localhost:8080/api/jsonws`](http://localhost:8080/api/jsonws). 
Be sure to replace `http://localhost:8080` in this URL with your server's 
address. 

Once you determine the services you need to call, add their imports. For 
example, if you're building a blogs app, you can import `LRBlogsEntryService`:

    #import "LRBlogsEntryService_v62.h"

Note that the Liferay version (`_v62`) is used in the service class's name. This 
corresponds to the Liferay version it's compatible with. In this example, `_v62` 
is used, which means this Mobile SDK class is compatible with Liferay 6.2. 
Mobile SDK classes compatible with Liferay 7.0 use `_v7` instead. Because 
service class names contain the Liferay version they're compatible with, you can 
use several Mobile SDKs simultaneously to support different Liferay versions in 
the same app. 

## Step 3: Calling the Service [](id=step-3-calling-the-service)

Once you have a session and have imported the service class, you're ready to 
make the service call. This is done by creating a service object for the service 
you want to call, and then calling its service methods. For example, if you're 
creating a blogs app, you need to use `LRBlogsEntryService` to get all the blogs 
entries from a site. This is demonstrated by the following code. 

    LRBlogsEntryService_v62 *service = [[LRBlogsEntryService_v62 alloc] initWithSession:session];

    NSError *error;
    NSArray *entries = [service getGroupEntriesWithGroupId:10184 status:0 start:-1 end:-1 error:&error];

This fetches all blog entries from the *Guest* site. In this example, the 
*Guest* site's `groupId` is `10184`. Note that many service methods require 
`groupId` as a parameter. You can get the user's groups by calling 
`[LRGroupService_v62 getUserSites:&error]`. 

Service method return types can be `void`, `NSString`, `NSArray`, 
`NSDictionary`, `NSNumber`, and `BOOL`. 

This `LRBlogsEntryService` call is a basic example of a synchronous service 
call. The method in a synchronous service call returns only after the request is 
finished.

## Non-Primitive Arguments [](id=non-primitive-arguments)

There are some special cases in which service method arguments aren't 
primitives. In these cases, you should use `LRJSONObjectWrapper`. For example: 

    LRJSONObjectWrapper *wrapper = [[LRJSONObjectWrapper alloc] 
        initWithJSONObject:[NSDictionary dictionary]];

You must pass a dictionary containing the object's properties and their values. 
On the server side, your object is instantiated and setters for each property 
are called with the values from the dictionary. 

There are some other cases in which service methods require interfaces or 
abstract classes as arguments. Since it's impossible for the SDK to guess which
implementation you want to use, you must initialize `LRJSONObjectWrapper` with
the class name. For example: 

    LRJSONObjectWrapper *wrapper = [[LRJSONObjectWrapper alloc] 
        initWithClassName:@"com.example.MyClass" jsonObject:[NSDictionary dictionary]];

The server looks for the class name in its classpath and instantiates the object 
for you. It then calls setters, as in the previous example. The abstract class 
`OrderByComparator` is a good example of this. This is discussed next. 

### OrderByComparator [](id=orderbycomparator)

On the server side, `OrderByComparator` is an abstract class. You must therefore 
pass the name of a class that implements it. For example: 

    NSString *className = @"com.liferay.portlet.bookmarks.util.comparator.EntryNameComparator";

    LRJSONObjectWrapper *orderByComparator = [[LRJSONObjectWrapper alloc] initWithClassName:className jsonObject:[NSDictionary dictionary]];

If the service you're calling accepts `null` for a comparator argument, pass 
`nil` to the service call.

You may want to set the ascending property for a comparator. Unfortunately, as 
of Liferay 6.2, most Liferay `OrderByComparator` implementations don't have a 
setter for this property and it isn't possible to set from the Mobile SDK. 
Future Liferay versions may address this. However, you may have a custom 
`OrderByComparator` that has a setter for ascending. In this case, you can use 
the following code: 

    NSString *className = @"com.example.MyOrderByComparator";

    NSDictionary *jsonObject = @{
        @"ascending": @(YES)
    };

    LRJSONObjectWrapper *orderByComparator = [[LRJSONObjectWrapper alloc] 
        initWithClassName:className jsonObject:jsonObject];

For more examples, see the test case 
[`OrderByComparatorTest.m`](https://github.com/liferay/liferay-mobile-sdk/blob/ios-6.2.0.17/ios/Test/OrderByComparatorTest.m).

### ServiceContext [](id=servicecontext)

Another non-primitive argument is `ServiceContext`. It requires special 
attention because most Liferay service methods require it. However, you aren't 
required to pass it to the SDK; you can pass `nil` instead. The server then 
creates a `ServiceContext` instance for you, using default values.  

If you need to set properties for `ServiceContext`, you can do so by adding them 
to a new `NSDictionary` and then passing it as the `ServiceContext` argument:

    NSDictionary *jsonObject = @{
        @"addGroupPermissions": @(YES),
        @"addGuestPermissions": @(YES)
    };

    LRJSONObjectWrapper *serviceContext = [[LRJSONObjectWrapper alloc] initWithJSONObject:jsonObject];

For more examples, see the test case 
[`ServiceContextTest.m`](https://github.com/liferay/liferay-mobile-sdk/blob/ios-6.2.0.17/ios/Test/ServiceContextTest.m). 

### Binaries [](id=binaries)

Some Liferay services require binary argument types like `NSData` or 
`LRUploadData`. The Mobile SDK converts `NSData` instances to `NSString` before 
sending the POST request. For example, 
`[@"hello" dataUsingEncoding:NSUTF8StringEncoding]` becomes a JSON array such as 
`"[104,101,108,108,111]"`. The Mobile SDK does this for you, so you don't have 
worry about it; you only need to pass the `NSData` instance to the method.

However, you need to be careful when using such methods. This is because you're 
allocating memory for the whole `NSData`, which may cause memory issues if the 
content is large.

Other Liferay service methods require `java.io.File` as an argument. In these 
cases the Mobile SDK requires `LRUploadData` instead. Here are two examples of 
creating `LRUploadData` instances: 

    LRUploadData *upload = [[LRUploadData alloc] 
        initWithData:data fileName:@"file.png" mimeType:@"image/png"];

    LRUploadData *upload = [[LRUploadData alloc] 
        initWithInputStream:is length:length fileName:@"file.png" mimeType:@"image/png"];

The first constructor accepts an `NSData` argument, while the second accepts
`NSInputStream`. As you can see, you also need to pass the file's mime type and 
name. The `length` is the size in bytes of the content being sent. The SDK sends 
a multipart form request to the Liferay instance. On the server side, a `File` 
instance is created and sent to the service method you're calling.

It's also possible to monitor service calls that upload data to Liferay. If want 
to listen for upload progress to create a progress bar, you can create a 
`LRProgressDelegate` delegate and set it to an `LRUploadData` object. Its
`onProgressBytes` method is called for each byte chunk sent. It passes the 
bytes that were sent, the total number of bytes sent so far, and the total 
request size. For example: 

    @interface ProgressDelegate : NSObject <LRProgressDelegate>

    @end

    @implementation ProgressDelegate

    - (void)onProgressBytes:(NSUInteger)bytes sent:(long long)sent
        total:(long long)total {

            // bytes contains the byte values that were sent.
            // sent will contain the total number of bytes sent.
            // total will contain the total size of the request in bytes.

    }

    @end

For more examples of this, see the test case 
[`FileUploadTest.m`](https://github.com/liferay/liferay-mobile-sdk/blob/ios-6.2.0.17/ios/Test/FileUploadTest.m).

## Related Topics [](id=related-topics)

[Building Mobile SDKs](/develop/tutorials/-/knowledge_base/7-0/building-mobile-sdks)

[Creating Android Apps that Use the Mobile SDK](/develop/tutorials/-/knowledge_base/7-0/creating-android-apps-that-use-the-mobile-sdk)
