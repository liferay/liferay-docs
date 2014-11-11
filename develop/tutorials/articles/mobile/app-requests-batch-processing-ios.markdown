# Sending Your iOS App's Requests Using Batch Processing [](id=sending-your-ios-apps-requests-using-batch-processing)

If your app is making a large number of requests to your Liferay instance, 
performance can suffer if the requests are processed one at a time. Requests 
made like this are called *synchronous* requests. *Batch processing* is another 
popular way to send requests to the Mobile SDK. Batch processing of requests can 
be more efficient than synchronous requests.

This tutorial shows you how to implement batch processing of requests from your 
iOS app. The code used is an example of an app you might develop if you want to 
retrieve blogs from a Liferay instance. Now it's time to get started whipping up 
a fresh batch of requests!

## Implementing Batch Processing of Requests [](id=implementing-batch-processing-of-requests)

Suppose you want to delete 10 blog entries at the same time. Instead of making 
one request for each delete call, you can create a batch of delete calls and 
send them together. Here's an example of how to do this.

    #import "LRBatchSession.h"

    LRBatchSession *batch = [[LRBatchSession alloc] init:@"http://localhost:8080" username:@"test@liferay.com" password:@"test"];
    LRBlogsEntryService_v62 *service = [[LRBlogsEntryService_v62 alloc] init:batch];
    NSError *error;

    [service deleteEntryWithEntryId:1 error:&error];
    [service deleteEntryWithEntryId:2 error:&error];
    [service deleteEntryWithEntryId:3 error:&error];

    NSArray *entries = [batch invoke:&error];

So what's going on here? First, create an `LRBatchSession` session. You can 
either pass credentials or another `session` to the constructor. This is useful 
when you already have a `Session` object and want to reuse the same credentials. 
Next, make the service calls as usual. With asynchronous calls, these methods 
return `nil` right away.

Finally, call `[bath invoke:&error]`. This returns an `NSArray` containing the
results for each service call (the return type for batch calls is always 
`NSArray`). Since there are three `deleteEntryWithEntryId` calls, the `entries` 
array contains three objects. The order of the results matches the order of the 
service calls.

If you want to make batch calls asynchronously, set the callback to the session.

    [batch setCallback:callback];

Great! Now you know how to utilize batch processing to speed up your app's 
requests.

## Related Topics [](id=related-topics)

[Liferay Mobile SDK Builder](/develop/tutorials/-/knowledge_base/6-2/liferay-mobile-sdk-builder)

[Service Builder and Services](/develop/tutorials/-/knowledge_base/6-2/service-builder)

[Creating Android Apps that Use Liferay](/develop/tutorials/-/creating-android-apps-that-use-liferay))
