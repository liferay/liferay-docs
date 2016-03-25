# Invoking Services Asynchronously from Your iOS App [](id=invoking-services-asynchronously-from-your-ios-app)

The main drawback of using synchronous requests from your app is that each 
request must terminate before another can begin. If you're sending a large 
number of synchronous requests, performance suffers as a bottleneck forms while 
each one waits to be processed. Fortunately, Liferay's iOS SDK allows 
*asynchronous* HTTP requests. To do so, you need to set a callback to the 
session object. If you want to make synchronous requests again, you can set the 
callback to `nil`. 

With the following steps, this tutorial shows you how to implement asynchronous 
requests in your iOS app: 

1. Implement your callback class.
2. Instantiate your callback class and set it to the session.
3. Call Liferay services.

Objective-C is used in the code snippets that follow. Let the requesting begin! 

## Implementing Your Callback Class [](id=implementing-your-callback-class)

To configure asynchronous requests, first create a class that conforms to the 
`LRCallback` protocol. When implementing this callback class, you need to 
implement its `onFailure` and `onSuccess` methods. These methods respectivley 
determine what your app does when the request fails or succeeds. If a server 
side exception or a connection error occurs during the request, the `onFailure` 
method is called with an `NSError` instance that contains information about the 
error. Note that the `onSuccess` result parameter doesn't have a specific type. 
When deciding what to cast it to, you need to check the type in the service 
method signature. 

The example code here implements a callback class for an app that retrieves blog 
entries from a Blogs portlet. The service method for this call is 
`getGroupEntriesWithGroupId`, which returns an `NSArray` instance. The 
`onSuccess` method's result parameter is therefore cast to this type: 

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
        NSArray *entries = (NSArray *)result;
    }

    @end
    
Awesome! Now you have a callback class that you can use with the session.

## Set the Callback to the Session [](id=set-the-callback-to-the-session)

Next, create an instance of this callback and set it to the session. If you 
haven't created a session yet, do so now. The tutorial 
[Invoking Liferay Services in Your iOS App](/develop/tutorials/-/knowledge_base/7-0/invoking-liferay-services-in-your-ios-app) 
shows you how to create a session. Now you're ready to set the callback to the 
session. For example, this is done here for `BlogsEntriesCallback`: 

    BlogsEntriesCallback *callback = [[BlogsEntriesCallback alloc] init];

    [session setCallback:callback];

Pretty simple! Now you're ready to make the service call.

## Making the Service Call [](id=making-the-service-call)

Last but certainly not least, make the service call. This is done the same as 
calling any other service: create a service object from the session and use it 
to make the service call. This is also described in the tutorial 
[Invoking Liferay Services in Your iOS App](/develop/tutorials/-/knowledge_base/7-0/invoking-liferay-services-in-your-ios-app). 
Here, an example service call that gets all the blog entries from a site's Blogs 
portlet is shown: 

    [service getGroupEntriesWithGroupId:10184 status:0 start:-1 end:-1 error:&error];

Since the request is asynchronous, `getGroupEntriesWithGroupId` immediately 
returns `nil`. Once the request finishes successfully, the `onSuccess` method of 
your callback is invoked with the results on the main UI thread. 

Great! Now you know how to make asynchronous requests in your iOS apps. However, 
there's another way to accomplish the same thing. This is discussed next. 

## Using Blocks as Callbacks [](id=using-blocks-as-callbacks)

Instead of implementing a separate callback class, you can use an Objective-C 
block as a callback. An example of this is shown here for an asynchronous call 
that retrieves a user's sites. Note that this includes all the code required to 
make the call: 

    LRSession *session = [[LRSession alloc] 
        initWithServer:@"http://localhost:8080" username:@"test@liferay.com" password:@"test"];

    [session
        onSuccess:^(id result) {
            // Called after request has finished successfully
        }
        onFailure:^(NSError *e) {
            // Implement error handling code
        }
    ];

    LRGroupService_v62 *service = [[LRGroupService_v62 alloc] initWithSession:session];

    NSError *error;
    [service getUserSites:&error];

When using a block as a callback, take care not to also set an `LRCallback` 
instance to the session. If you do, it gets overriden. Otherwise, support for 
blocks works the same way as described in the previous sections. 

Super! Now you know two different ways to make asynchronous service requests in 
your iOS apps. 

## Related Topics [](id=related-topics)

[Invoking Liferay Services in Your iOS App](/develop/tutorials/-/knowledge_base/7-0/invoking-liferay-services-in-your-ios-app)

[Creating Android Apps that Use the Mobile SDK](/develop/tutorials/-/knowledge_base/7-0/creating-android-apps-that-use-the-mobile-sdk)
