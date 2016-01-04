# Understanding Mobile SDK Calls [](id=understanding-mobile-sdk-calls)

When you make SDK calls, you must understand how they work. This knowledge is
crucial to make these calls properly and handle their results in your app. To
call the Guestbook portlet's remote services, you must use the Guestbook Mobile
SDK you built and installed earlier. This Mobile SDK contains the services
required to call the Guestbook portlet's remote services. The following diagram
illustrates how you'll make and process calls with the Guestbook Mobile SDK. 

![Figure 1: A call made with the Guestbook Mobile SDK requires that you create a Mobile SDK service instance, use it to call the Liferay service, transform the resulting JSON into model objects, and pass those model objects back to the activity or fragment that issued the original call.](../../images/android-mobile-sdk.png)

This diagram is broken down into four basic steps:

1. In an activity or fragment, create an instance of the Guestbook Mobile SDK 
   service that you need to call the Guestbook portlet's remote services. For 
   example, to get guestbooks from the portlet you need to create a 
   `GuestbookService` instance. The service instance contains the methods needed 
   to call the Guestbook portlet's remote services. How you create the service 
   instance is also important. You must create it with an authenticated session 
   that contains a callback instance. The callback is required to route the 
   remote service call asynchronously through a background thread, since Android 
   doesn't allow network requests on its main UI thread. You also use the 
   callback class to process the service call's results. 

2. In the same activity or fragment, use the Guestbook Mobile SDK service 
   instance to call the Guestbook portlet's remote services. For example, to get 
   guestbooks from the portlet you need to call the `GuestbookService` 
   instance's `getGuestbooks` method. Even though you make this call from the 
   activity or fragment, the service routes the call through the callback 
   instance. 

3. In the callback, receive and process the JSON that results from a successful 
   server call. To efficiently work with these results in your app, you need to 
   transform the JSON into model objects that represent guestbooks or entries. 
   You do this by leveraging model classes you'll create for guestbooks and 
   entries. 

4. Return the model objects from the callback to the activity or fragment that 
   issued the original service call. You can then display the model objects in 
   your app. 

Great! Now you know the basics of making service calls with the Guestbook Mobile 
SDK. Next, you'll retrieve guestbooks from the portlet and display them in your 
app's navigation drawer.
