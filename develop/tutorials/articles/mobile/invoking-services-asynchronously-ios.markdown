# Invoking Services Asynchronously from Your iOS App [](id=invoking-services-asynchronously-from-your-ios-app)

The main drawback of using synchronous requests from your app is that each 
request must terminate before another can begin. If you're sending a large  
number of synchronous requests, performance suffers as a bottleneck forms while 
each one waits to be processed. Fortunately, Liferay's iOS SDK allows 
*asynchronous* HTTP requests. All you need to do is set a callback to the 
session object. If you want to make synchronous requests again, you can set the 
callback to `nil`. 

This tutorial shows you how to make asynchronous requests from your iOS app. The 
code used is an example of an app you might develop if you want to retrieve 
blogs from a Liferay instance. Let the requesting begin! 

## Implementing Asynchronous Service Requests [](id=implementing-asynchronous-service-requests)

To configure asynchronous requests, first create a class that conforms to the 
`LRCallback` protocol.

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
    [service getGroupEntriesWithGroupId:1084 status:0 start:-1 end:-1 error:&error];

If a server side exception or a connection error occurs during the request, the
`onFailure` method is called with an `NSError` that contains information about
the error.

Since the request is asynchronous, the `getGroupEntriesWithGroupId` method
returns immediately with `nil`. Once the request has finished successfully, the 
`onSuccess` method of your callback is invoked with the results on the main UI 
thread. 

Note that the `onSuccess` result parameter doesn't have a specific type. When 
deciding what to cast it to, you need to check the type in the service method 
signature. In this example, the `getGroupEntriesWithGroupId` method returns an 
`NSArray`. The `onSuccess` result parameter is therefore cast to this type. 

    - (void)onSuccess:(id)result {
        NSArray *entries = (NSArray *)result;
    }

Super! Now you know how to implement asynchronous service requests in your iOS 
apps. 

## Related Topics [](id=related-topics)

[Liferay Mobile SDK Builder](/develop/tutorials/-/knowledge_base/6-2/liferay-mobile-sdk-builder)

[Service Builder and Services](/develop/tutorials/-/knowledge_base/6-2/service-builder)

[Creating Android Apps that Use Liferay](/develop/tutorials/-/creating-android-apps-that-use-liferay)

