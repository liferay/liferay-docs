### Sending Your iOS App's Requests Using Batch Processing [](id=batch-ios-app-requests-to-liferay-portal-6-2-dev-guide-en)

Another popular method of sending requests to the Mobile SDK is through batch
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

As you can see, it's very simple to make efficient service calls in batch using
the iOS SDK! 
