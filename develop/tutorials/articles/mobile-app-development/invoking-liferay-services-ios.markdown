### Invoking Liferay Services in Your iOS App [](id=access-liferay-services-from-ios-app-liferay-portal-6-2-dev-guide-en)

For your mobile app to access the Mobile SDK, you'll need to complete several
steps:

1. Create a session.
2. Import the Liferay services for your app to use.
3. Create a service object and call its services.

We'll demonstrate these steps by providing access to a sample Blogs app. Note
that the following code snippets are written in the *Objective C* programming
language. Let's begin! 

#### Step 1: Create a session [](id=step-1-create-a-session-liferay-portal-6-2-dev-guide-08-en-0)

	#import "LRSession.h"

	LRSession *session = [[LRSession alloc] init:@"http://localhost:8080" username:@"test@liferay.com" password:@"test"];

To learn more about the session and its three parameters, refer to
[Setting Up the Android
SDK](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/using-the-android-sdk-liferay-portal-6-2-dev-guide-08-en).

<!-- We should provide this information here, customized for Apple people.
They're a sensitive lot. :-) -Rich -->

#### Step 2: Import the Liferay services for your app to use [](id=step-2-import-the-liferay-services-for-liferay-portal-6-2-dev-guide-08-en-0)

For this sample app, we'll import the [`BlogsEntryService`](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portlet/blogs/service/BlogsEntryService.html). 

	#import "LRBlogsEntryService_v62.h"

Note the Liferay version (`.v62`) is used in the package namespacing.
Since the SDK is built for a specific Liferay version, service classes are
separated by their package name. Our Mobile SDK classes use the `.v62`
package, which means this SDK is compatible with Liferay 6.2. However, you can
use several SDKs simultaneously to support different Liferay versions.

Your portal's JSON web services page (e.g.,
[http://localhost:8080/api/jsonws](http://localhost:8080/api/jsonws))
lists all available portal services and plugin services. 

#### Step 3: Create a service object and call its services [](id=step-3-create-a-service-object-and-cal-liferay-portal-6-2-dev-guide-08-en-0)

For this sample app, we'll create an `LRBlogsEntryService_v62` object and
make a service call that fetches all blog entries from the *Guest* site. In
this example, the `groupId` is `10184`. 

	LRBlogsEntryService_v62 *service = [[LRBlogsEntryService_v62 alloc] init:session];

	NSError *error;
	NSArray *entries = [service getGroupEntriesWithGroupId:1084 status:0 start:-1 end:-1 error:&error];


This is a basic example of a synchronous service call, which means the
method returns only after the request is finished.

Service method return types can be `void`, `NSString`, `NSArray`,
`NSDictionary`, `NSNumber`, and `BOOL`.

---

![Note](../../images/tip-pen-paper.png) **Note:** Many service methods
require a group ID as a parameter. 
You can call `[LRGroupService_v62 getUserSitesGroups:&error]`, which uses
Liferay Portal's
[`GroupService.getUserSitesGroups`](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/service/GroupService.html#getUserSitesGroups()),
to get a site group. 

---

That's it! You've given the blogs app access to the `BlogsEntryService`. Next,
let's discuss making asynchronous HTTP requests.
