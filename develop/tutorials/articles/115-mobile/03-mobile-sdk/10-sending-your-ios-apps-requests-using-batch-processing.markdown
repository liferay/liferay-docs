# Sending Your iOS App's Requests Using Batch Processing [](id=sending-your-ios-apps-requests-using-batch-processing)

The Mobile SDK also allows sending requests in batch. This can be much more 
efficient than sending separate requests. For example, suppose you want to 
delete ten blog entries in a site's Blogs portlet at the same time. Instead of 
making a request for each deletion, you can create a batch of calls and send 
them all together. 

This tutorial shows you how to implement batch processing for your iOS app. It's 
assumed that you already know how to invoke Liferay services from your iOS app. 
If you don't, see the tutorial 
[Invoking Liferay Services in Your iOS App](/develop/tutorials/-/knowledge_base/7-0/invoking-liferay-services-in-your-ios-app). 
Objective-C is used in the code snippets that follow. Now it's time to whip up a 
fresh batch of requests! 

## Implementing Batch Processing [](id=implementing-batch-processing)

Making service calls in batch only requires two extra steps over making them one 
at a time: 

- Create a batch session with `LRBatchSession`.
- Make the batch service calls with the `invoke` method of `LRBatchSession`.

The rest of the steps are the same as making other service calls. You still need 
a service object, and you still need to call its service methods. As an example, 
here's a code snippet from an app that deletes a Blogs portlet's blog entries 
synchronously in batch: 

    #import "LRBatchSession.h"

    LRBatchSession *batch = [[LRBatchSession alloc] 
        initWithServer:@"http://localhost:8080" username:@"test@liferay.com" password:@"test"];
    LRBlogsEntryService_v62 *service = [[LRBlogsEntryService_v62 alloc] initWithSession:batch];
    NSError *error;

    [service deleteEntryWithEntryId:1 error:&error];
    [service deleteEntryWithEntryId:2 error:&error];
    [service deleteEntryWithEntryId:3 error:&error];

    NSArray *entries = [batch invoke:&error];

So what's going on here? After the import, `LRBatchSession` is used with a 
Liferay instance's URL and a user's credentials to create a batch session. You 
can alternatively pass a pre-existing `session` to the constructor. This is 
useful when you already have a session object and want to reuse the same 
credentials. Next, the service calls are made as usual (in this case, 
`deleteEntryWithEntryId`). With asynchronous calls, these methods return `nil` 
right away. Finally, call `[batch invoke:&error]`. This returns an `NSArray` 
containing the results for each service call (the return type for batch calls is 
always `NSArray`). Since there are three `deleteEntryWithEntryId` calls, the 
`entries` array contains three objects. The order of the results matches the 
order of the service calls. 

If you want to make batch calls asynchronously, set the callback to the session 
as usual.

    [batch setCallback:callback];

Great! Now you know how to utilize batch processing to speed up your app's 
requests. 

## Related Topics [](id=related-topics)

[Invoking Liferay Services in Your iOS App](/develop/tutorials/-/knowledge_base/7-0/invoking-liferay-services-in-your-ios-app)

[Creating Android Apps that Use the Mobile SDK](/develop/tutorials/-/knowledge_base/7-0/creating-android-apps-that-use-the-mobile-sdk)
