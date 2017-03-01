# Understanding Screenlet Server Calls

Screenlets use *Interactors* to make server calls. Interactors are Screenlet 
components that use the Mobile SDK to make server calls. Although Interactors 
are also made up of several components, for now you only need to know two: the 
Interactor class, and the Interactor's callback. The Interactor class issues the 
Mobile SDK call, and the callback routes it asynchronously through a background 
thread. You'll learn these components in more detail, and the others that 
comprise an Interactor, when you create Guestbook List Screenlet's Interactor. 
For now, though, you'll focus on Mobile SDK calls. 

To call the Guestbook portlet's remote services, you'll use the Guestbook Mobile 
SDK you built and installed earlier. This Mobile SDK contains the services 
required to call the Guestbook portlet's remote services. The following diagram 
illustrates a typical Mobile SDK call in a Screenlet: 

![Figure 1: This diagram shows a typical Mobile SDK call made by a Screenlet's Interactor.](../../../images/android-mobile-sdk.png)

This diagram is broken down into four basic steps: 

1. In the Interactor class, create an instance of the Guestbook Mobile SDK 
   service required to call the Guestbook portlet's remote services. For 
   example, to get guestbooks from the portlet you must create a 
   `GuestbookService` instance. The service instance contains the methods that 
   call the Guestbook portlet's remote services. How you create this instance is 
   also important. You must create it with an authenticated session that 
   contains a callback instance. The callback is required to route the remote 
   service call asynchronously through a background thread, since Android 
   doesn't allow network requests on its main UI thread. Note that when creating 
   a list Screenlet, you don't have to set the callback to the session manually; 
   the list Screenlet framework does it for you. 

2. In the same Interactor class, use the Guestbook Mobile SDK service instance 
   to call the Guestbook portlet's remote services. For example, to get 
   guestbooks from the portlet you must call the `GuestbookService` instance's 
   `getGuestbooks` method. The service routes the call through the callback 
   instance. 

3. Receive and process the JSON that results from a successful server call. To 
   efficiently work with these results in your app, you must transform the JSON 
   into model objects that represent guestbooks or entries. You use model 
   classes to do this, like the `GuestbookModel` class you created earlier. Note 
   that in list Screenlets, 
   [the `BaseListCallback` class](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/BaseListCallback.java) 
   automatically transforms the JSON into `Map` objects for you. You must still, 
   however, use your model class to convert these `Map` objects into proper 
   guestbook objects. 

4. Receive the model objects in the Interactor class. You can then send these 
   objects to the rest of the Screenlet for display in the app. Note that in 
   list Screenlets, the list Screenlet framework's 
   [`BaseListInteractor` class](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/BaseListInteractor.java) 
   receives the model objects for you. 

You've probably noticed that in list Screenlets, the list Screenlet framework 
handles a great deal of the work for you. You'll see this in greater detail as 
you progress through this Learning Path. Next, you'll create Guestbook List 
Screenlet's Interactor. 
