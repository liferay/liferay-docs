# Invoking Liferay Services in Your iOS App [](id=invoking-liferay-services-in-your-ios-app)

If you have data or functionality in your portal that you want to use in your 
iOS app, you need to invoke Liferay services using Liferay's iOS SDK. Invoking 
Liferay services in your iOS app requires the following steps:

1. Create a session.
2. Import the Liferay services for your app to use.
3. Create a service object and call its services.

This tutorial demonstrates these steps by invoking Liferay's `BlogsEntryService` 
in an example of an app you might develop if you want to retrieve blogs from a 
Liferay instance. Note that the following code snippets are written in the 
*Objective C* programming language. Let the invoking begin! 

## Step 1: Create a Session [](id=step-1-create-a-session)

The session is a conversion state between the client and server, that consists 
of multiple requests and responses between the two. You need a session to pass 
requests between your app and the Mobile SDK. Here, you create a session with 
user credentials of the Liferay instance you are connecting to.

	#import "LRSession.h"

	LRSession *session = [[LRSession alloc] init:@"http://localhost:8080" username:@"test@liferay.com" password:@"test"];

Here's an explanation of each of the session parameters:

**Server:** The first parameter is the URL of the Liferay instance you are 
connecting to. In this case, the emulator and Liferay are running in the same 
machine.

**Username:** The second parameter can either be the user's email address, 
screen name, or user ID. Your session login user name must be consistent with 
the authentication method your Liferay instance is using. Liferay's default 
authentication method requires the user's email address.

**Password:** This is the user's password.

+$$$

**Warning:** Take care when using 
administrator credentials on a production Liferay instance, as you'll have
permission to call any service. Make sure not to modify data accidentally. Of
course, the default administrator credentials should be disabled on a production
Liferay instance. 

$$$

<!-- We should provide this information here, customized for Apple people.
They're a sensitive lot. :-) -Rich -->

## Step 2: Import the Liferay Services for Your App to Use [](id=step-2-import-the-liferay-services-for-your-app-to-use)

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

## Step 3: Create a Service Object and Call its Services [](id=step-3-create-a-service-object-and-call-its-services)

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

+$$$

**Note:** Many service methods
require a group ID as a parameter. 
You can call `[LRGroupService_v62 getUserSitesGroups:&error]`, which uses the
`getUserSitesGroups()` method from Liferay Portal's
[`GroupService`](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/service/GroupService.html)
class to get a site group. 

$$$

## Related Topics [](id=related-topics)

[Liferay Mobile SDK Builder](/develop/tutorials/-/knowledge_base/6-2/liferay-mobile-sdk-builder)

[Service Builder and Services](/develop/tutorials/-/knowledge_base/6-2/service-builder)

[Creating Android Apps that Use Liferay](/develop/tutorials/-/creating-android-apps-that-use-liferay)

