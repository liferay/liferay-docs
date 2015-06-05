# Invoking Services Asynchronously from Your iOS App [](id=invoking-services-asynchronously-from-your-ios-app)

The main drawback of using synchronous requests from your app is that each 
request must terminate before another can begin. If you're sending a large  
number of synchronous requests, performance suffers as a bottleneck forms while 
each one waits to be processed. Fortunately, Liferay's iOS SDK allows 
*asynchronous* HTTP requests. All you need to do is set a callback to the 
session object. If you want to make synchronous requests again, you can set the 
callback to `nil`. 

This tutorial shows you how to make asynchronous requests from your iOS app. 
Objective-C is used in the code snippets that follow. Let the requesting begin! 

## Implementing Asynchronous Service Requests [](id=implementing-asynchronous-service-requests)

To configure asynchronous requests, first create a class that conforms to the 
`LRCallback` protocol. For example, this could appear as follows if you were 
creating a blogs app:

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

Next, set this callback to the session and call your service as usual.

    BlogsEntriesCallback *callback = [[BlogsEntriesCallback alloc] init];

    [session setCallback:callback];
    [service getGroupEntriesWithGroupId:10184 status:0 start:-1 end:-1 error:&error];

If a server side exception or a connection error occurs during the request, the
`onFailure` method is called with an `NSError` instance that contains 
information about the error.

Since the request is asynchronous, `getGroupEntriesWithGroupId` returns 
immediately with `nil`. Once the request has finished successfully, the 
`onSuccess` method of your callback is invoked with the results on the main UI 
thread. 

Note that the `onSuccess` result parameter doesn't have a specific type. When 
deciding what to cast it to, you need to check the type in the service method 
signature. In this example, the `getGroupEntriesWithGroupId` method returns an 
`NSArray` instance. The `onSuccess` result parameter is therefore cast to this 
type. 

    - (void)onSuccess:(id)result {
        NSArray *entries = (NSArray *)result;
    }
    
### Using Blocks as Callbacks

It's also possible to use Objective-C blocks as callbacks: 

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

Remember not to set a `LRCallback` to the session when doing this. If you do, it 
gets overriden. Otherwise, support for blocks works the same way as described in 
the previous section. 

Super! Now you know how to implement asynchronous service requests in your iOS 
apps. 

## Related Topics [](id=related-topics)

[Liferay Mobile SDK Builder](/develop/tutorials/-/knowledge_base/6-2/liferay-mobile-sdk-builder)

[Service Builder and Services](/develop/tutorials/-/knowledge_base/6-2/service-builder)

[Creating Android Apps that Use Liferay](/develop/tutorials/-/creating-android-apps-that-use-liferay)
