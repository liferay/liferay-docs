# Invoking Services Asynchronously from Your iOS App 

The main drawback of using synchronous requests from your app is that each 
request must terminate before another can begin. If you're sending a large  
number of synchronous requests, performance suffers as a bottleneck forms while 
each one is processed. Fortunately, Liferay's iOS SDK allows *asynchronous* HTTP 
requests. All you need to do is set a callback to the session object. If you 
want to make synchronous requests again, you can set the callback to `nil`. 

This tutorial shows you how to make asynchronous requests from your iOS app. The 
code used is an example of an app you might develop if you want to retrieve 
blogs from a Liferay instance. Let the requesting begin! 

## Implementing Asynchronous Service Requests 

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
returns immediately with `nil`. The `onSuccess` method of your callback is 
invoked with the results once the request has finished successfully. 

Note that the `onSuccess` result parameter doesn't have a specific type. When 
deciding what to cast it to, you need to check the type in the service method 
signature. In this example, the `getGroupEntriesWithGroupId` method returns an 
`NSArray`. The `onSuccess` result parameter is therefore cast to this type. 

    - (void)onSuccess:(id)result {
        NSArray *entries = (NSArray *)result;
    }

The `onSuccess` method is called on the main UI thread after the request has
finished. 

## Next Steps 

Super! Now you know how to implement asynchronous service requests in your iOS 
apps. Another way you can speed up your service requests is to send them in 
batches. This is discussed in the tutorial 
[Sending Your iOS App's Requests Using Batch Processing](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/app-requests-batch-processing-ios-lp-6-2-develop-tutorial).
