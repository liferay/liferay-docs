# Invoking Liferay Services in Your iOS App 

If you have data or functionality in your portal that you want to use in your 
iOS app, you need to invoke Liferay services using Liferay's iOS SDK. Invoking 
Liferay services in your iOS app requires the following steps:

1. Create a session.
2. Import the Liferay services for your app to use.
3. Create a service object and call its services.

This tutorial demonstrates these steps by providing access to a sample Blogs 
app that invokes Liferay's `BlogsEntryService`. Note that the following code 
snippets are written in the *Objective C* programming language. Let the 
invoking begin! 

<!-- Where is this sample bloggs app? -Nick -->

## Step 1: Create a Session 

	#import "LRSession.h"

	LRSession *session = [[LRSession alloc] init:@"http://localhost:8080" username:@"test@liferay.com" password:@"test"];

To learn more about the session and its three parameters, refer to the tutorial 
[Invoking Liferay Services in Your Android App](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/invoking-liferay-services-android-lp-6-2-develop-tutorial).

<!-- We should provide this information here, customized for Apple people.
They're a sensitive lot. :-) -Rich -->

## Step 2: Import the Liferay Services for Your App to Use 

For this sample app, import the [`BlogsEntryService`](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portlet/blogs/service/BlogsEntryService.html). 

	#import "LRBlogsEntryService_v62.h"

Note the Liferay version (`.v62`) is used in the package namespacing. Since the 
SDK is built for a specific Liferay version, service classes are separated by 
their package names. The Mobile SDK classes use the `.v62` package, which means 
this SDK is compatible with Liferay 6.2. However, you can use several SDKs 
simultaneously to support different Liferay versions.

Your portal's JSON web services page (e.g.,
[http://localhost:8080/api/jsonws](http://localhost:8080/api/jsonws))
lists all available portal and plugin services. 

## Step 3: Create a Service Object and Call its Services 

This sample app creates an `LRBlogsEntryService_v62` object and makes a service 
call that fetches all blog entries from the *Guest* site. In this example, the 
`groupId` is `10184`. 

	LRBlogsEntryService_v62 *service = [[LRBlogsEntryService_v62 alloc] init:session];

	NSError *error;
	NSArray *entries = [service getGroupEntriesWithGroupId:1084 status:0 start:-1 end:-1 error:&error];

This is a basic example of a synchronous service call. The method in a 
synchronous service call returns only after the request is finished.

Service method return types can be `void`, `NSString`, `NSArray`,
`NSDictionary`, `NSNumber`, or `BOOL`.

---

![Note](../../images/tip-pen-paper.png) **Note:** Many service methods
require a group ID as a parameter. 
You can call `[LRGroupService_v62 getUserSitesGroups:&error]`, which uses
Liferay Portal's
[`GroupService.getUserSitesGroups`](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/service/GroupService.html#getUserSitesGroups()),
to get a site group. 

---

## Next Steps 

That's it! If you implemented these steps in your iOS app, it now has access to 
the Liferay service you invoked. However, synchronous service calls can be a 
bottleneck if your app is making a lot of them. Making service calls 
*asynchronously* is described in the tutorial 
[Invoking Services Asynchronously from Your iOS App](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/invoking-services-asynchronously-ios-lp-6-2-develop-tutorial). 
You can also speed things up by sending service calls in batches instead of 
separately. This is discussed in the tutorial 
[Sending Your iOS App's Requests Using Batch Processing](https://www-ldn.liferay.com/develop/tutorials/-/knowledge_base/app-requests-batch-processing-ios-lp-6-2-develop-tutorial).
