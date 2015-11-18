# Understanding Mobile SDK Calls

Before making Mobile SDK calls, whether they're calls made with the Liferay 
Mobile SDK or a custom Mobile SDK that you built, you must understand how the 
calls work. This is crucial for making these calls and handling their results 
properly in your app. Since you'll call the Guestbook portlet's remote services, 
you must use the Guestbook Mobile SDK you built and installed earlier. This 
Mobile SDK contains the services required to call the Guestbook portlet's remote 
services. The following diagram illustrates how you'll make and process calls 
with the Guestbook Mobile SDK. 

![Figure 1: A call made with the Guestbook Mobile SDK requires that you create a Mobile SDK service instance, use it to call the Liferay service, transform the resulting JSON into model objects, and pass those model objects back to the activity or fragment that issued the original call.](../../images/android-mobile-sdk.png)

This diagram is broken down into four basic steps:

1. Create an instance of the Guestbook Mobile SDK service that you need to call 
   the Guestbook portlet service.

2. Call the Guestbook portlet service.

3. Receive the JSON that results from a successful server call. Transform the 
   JSON into *model* objects that reflect guestbooks or entries from the 
   Guestbook portlet.

4. Return the model objects to the activity or fragment that issued the original 
   call.