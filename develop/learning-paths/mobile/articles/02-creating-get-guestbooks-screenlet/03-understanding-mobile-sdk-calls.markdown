# Understanding Mobile SDK Calls [](id=understanding-mobile-sdk-calls)

Liferay Screens uses the Mobile SDK to make server calls. You must therefore 
understand how Mobile SDK calls work. This knowledge is crucial to make these 
calls properly and handle their results in your app. To call the Guestbook 
portlet's remote services, you must use the Guestbook Mobile SDK you built and 
installed earlier. This Mobile SDK contains the services required to call the 
Guestbook portlet's remote services. 

Screenlets use *Interactors* to make server calls. Interactors are Screenlet 
components that use the Mobile SDK to make server calls. Although Interactors 
are also made up of several components, for now you only need to know two: the 
Interactor class, and the Interactor's callback. The Interactor class issues the 
Mobile SDK call, and the callback routes it asynchronously through a background 
thread. You'll learn these components in detail, and the others that comprise an 
Interactor, when you create Get Guestbooks Screenlet's Interactor. For now, 
though, you should focus on Mobile SDK calls. 

The following diagram illustrates how you'll make and process calls with the 
Guestbook Mobile SDK. 

![Figure 1: A call made with the Guestbook Mobile SDK requires that you create a Mobile SDK service instance, use it to call the Liferay service, transform the resulting JSON into model objects, and pass those model objects back to the Screenlet Interactor class that issued the call.](../../images/android-mobile-sdk.png)

This diagram is broken down into four basic steps:

1. In the Interactor class, create an instance of the Guestbook Mobile SDK 
   service that you need to call the Guestbook portlet's remote services. For 
   example, to get guestbooks from the portlet you must create a 
   `GuestbookService` instance. The service instance contains the methods needed 
   to call the Guestbook portlet's remote services. How you create the service 
   instance is also important. You must create it with an authenticated session 
   that contains a callback instance. The callback is required to route the 
   remote service call asynchronously through a background thread, since Android 
   doesn't allow network requests on its main UI thread. You also use the 
   callback class to process the service call's results. 

2. In the same Interactor class, use the Guestbook Mobile SDK service instance 
   to call the Guestbook portlet's remote services. For example, to get 
   guestbooks from the portlet you need to call the `GuestbookService` 
   instance's `getGuestbooks` method. The service routes the call through the 
   callback instance. 

3. In the Interactor's callback, receive and process the JSON that results from 
   a successful server call. To efficiently work with these results in your app, 
   you need to transform the JSON into model objects that represent guestbooks 
   or entries. You do this by using model classes, like the `GuestbookModel` 
   class you created earlier. 

4. Return the model objects from the callback to the Interactor class that 
   issued the service call. You can then send the model objects to the rest of 
   the Screenlet for display in your app. 

Great! Now you know the basics of making service calls with the Guestbook Mobile 
SDK. Next, you'll write Get Guestbooks Screenlet's server call. 
