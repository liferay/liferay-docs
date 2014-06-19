# Invoking Services Asynchronously from Your iOS App 

The main drawback of using synchronous requests from your app is that each one 
must terminate before another can begin. If you're sending many synchronous 
requests, performance suffers as a bottleneck forms while each one is 
processed. Fortunately, Liferay's iOS SDK allows *asynchronous* HTTP requests. 
All you need to do is set a callback to the session object. If you want to make 
synchronous requests again, you can set the callback to `nil`. 

This tutorial shows you how to invoke services asynchronously from your iOS app. 
Go ahead and get started invoking!

## Implementing Asynchronous Service Requests

Let's continue our example with the blogs app. To configure asynchronous
requests, the first thing we need to do is create a class that conforms to
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

Then set this callback to the session and call your service as usual:

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
can cast it to safely. In this example, the `getGroupEntriesWithGroupId` method
returns an `NSArray`; so you can cast to this type.

    - (void)onSuccess:(id)result {
        NSArray *entries = (NSArray *)result;
    }

The `onSuccess` method is called on the main UI thread after the request has
finished.

## Next Steps

Let's talk about another way to send your app's requests: batch processing.
