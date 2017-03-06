# Creating Guestbook List Screenlet's Interactor

Recall from 
[the basic Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-android-screenlets) 
that *Interactors* are Screenlet components that make server calls and 
process the results. Also recall that Interactors themselves are made up of 
several components: 

1. The event class
2. The listener
3. The Interactor class

Since the list Screenlet framework already contains two listeners, you only need 
to create the event and Interactor classes. You'll create the event class first. 

## Creating the Event Class

Recall that Screens uses the EventBus library to communicate the server call's 
results between Screenlet components. Screens does this via event objects that 
contain the server call's results. For more information and instructions on 
creating an event class, see 
[the section of the list Screenlet tutorial on event classes](/develop/tutorials/-/knowledge_base/7-0/creating-android-list-screenlets#creating-the-screenlets-event). 
You'll follow those instructions here to create Guestbook List Screenlet's event 
class. 

First, create a new package called `interactor` in the 
`com.liferay.docs.guestbooklistscreenlet` package. Then create the 
`GuestbookEvent` class in the `interactor` package. Replace this class's 
contents with the following code: 

    package com.liferay.docs.guestbooklistscreenlet.interactor;

    import com.liferay.docs.model.GuestbookModel;
    import com.liferay.mobile.screens.base.list.interactor.ListEvent;

    public class GuestbookEvent extends ListEvent<GuestbookModel> {

        private GuestbookModel guestbook;

        public GuestbookEvent() {
            super();
        }

        public GuestbookEvent(GuestbookModel guestbook) {
            this.guestbook = guestbook;
        }

        @Override
        public String getListKey() {
            return guestbook.getName();
        }

        @Override
        public GuestbookModel getModel() {
            return guestbook;
        }
    }

Note that this code is almost identical to that of the example `BookmarkEvent` 
in the list Screenlet tutorial. The only difference is that `GuestbookEvent` is 
adapted for guestbooks. 

Nice work! Your event class is done. You're almost ready to write the 
Screenlet's server call. First, however, you should understand the basics of how 
server calls work in Interactors. 

## Understanding Screenlet Server Calls

Interactor classes use the Liferay Mobile SDK to make server calls and process 
the results. An Interactor class does this in the following sequence: 

1. Get the Mobile SDK session and use it to create the Mobile SDK service 
   you want to call. 

2. Call the Mobile SDK service method that makes the server call. 

3. Create an event object from the JSON that the server call returns. If your 
   Screenlet has a model class, create a model object from the JSON, then create 
   the event object from the model object. 

![Figure 1: This diagram shows a typical Mobile SDK call made by a Screenlet's Interactor.](../../../images/android-mobile-sdk.png)

To call the Guestbook portlet's remote services, you'll use the Guestbook Mobile 
SDK you built and installed earlier. This Mobile SDK contains the services 
required to call the Guestbook portlet's remote services. Next, you'll create 
Guestbook List Screenlet's Interactor class. 

## Creating the Interactor Class

You'll create your Interactor class, `GuestbookListInteractor`, with the same 
[steps the list Screenlet creation tutorial uses to create the Interactor class](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/creating-android-list-screenlets#creating-the-screenlets-interactor). 
Guestbook List Screenlet's Interactor class must create `GuestbookService` 
instances for calling the Guestbook portlet's remote services. This Interactor 
class must also create `GuestbookModel` and `GuestbookEvent` objects from the 
service call's results. Follow these steps to create `GuestbookListInteractor`: 

1. Create the `GuestbookListInteractor` class in the package 
   `com.liferay.docs.guestbooklistscreenlet.interactor`. This class must extend 
   `BaseListInteractor` with `BaseListInteractorListener<GuestbookModel>` and 
   `GuestbookEvent` as type arguments. 

        public class GuestbookListInteractor extends 
            BaseListInteractor<BaseListInteractorListener<GuestbookModel>, GuestbookEvent> {...

    This requires that you add the following imports: 

        import com.liferay.docs.model.GuestbookModel;
        import com.liferay.mobile.screens.base.list.interactor.BaseListInteractor;
        import com.liferay.mobile.screens.base.list.interactor.BaseListInteractorListener;

2. Override the `getPageRowsRequest` method to retrieve a page of Guestbooks. In 
   `GuestbookListInteractor`, you do this by creating a `GuestbookService` 
   instance from the session and then calling the service's `getGuestbooks` 
   method with the `groupId`, start row, and end row: 

        @Override
        protected JSONArray getPageRowsRequest(Query query, Object... args) throws Exception {

            return new GuestbookService(getSession()).getGuestbooks(groupId, query.getStartRow(), 
                query.getEndRow());
        }

    This requires that you add the following imports: 

        import com.liferay.mobile.android.v7.guestbook.GuestbookService;
        import com.liferay.mobile.screens.base.list.interactor.Query;
        import org.json.JSONArray;

3. Override the `getPageRowCountRequest` method to retrieve the number of 
   guestbooks. Recall that this enables pagination. In 
   `GuestbookListInteractor`, you do this by creating a `GuestbookService` 
   instance from the session and then calling the service's `getGuestbooksCount` 
   method with the `groupId`: 

        @Override
	    protected Integer getPageRowCountRequest(Object... args) throws Exception {

            return new GuestbookService(getSession()).getGuestbooksCount(groupId);
        }

4. Override the `createEntity` method to create and return a new 
   `GuestbookEvent` object that contains the server call's results. Recall that 
   `BaseListInteractor` converts the JSON that results from a successful server 
   call into a `Map<String, Object>`. This happens in `BaseListInteractor`'s 
   [`execute(Query query, Object... args)` method](https://github.com/liferay/liferay-screens/blob/2.1.0/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/BaseListInteractor.java#L27-L49). 
   The `createEntity` method's only argument is this `Map`, which you use to 
   create a `GuestbookModel` object. Then create and return a new 
   `GuestbookEvent` object from the model object: 

        @Override
        protected GuestbookEvent createEntity(Map<String, Object> stringObjectMap) {
            GuestbookModel guestbook = new GuestbookModel(stringObjectMap);
            return new GuestbookEvent(guestbook);
        }

    This requires that you import `java.util.Map;`. 

5. Override the `getIdFromArgs` method to return the value of the first object 
   argument as a string: 

        @Override
        protected String getIdFromArgs(Object... args) {
            return String.valueOf(args[0]);
        }

    This serves as a cache key for 
    [offline mode](/develop/tutorials/-/knowledge_base/7-0/using-offline-mode-in-android). 
    Even though you won't add offline mode support to Guestbook List Screenlet, 
    this method makes it easier if you decide to do so later. 

Nice work! You now have the Interactor required to get guestbooks from the 
Guestbook portlet. Next, you'll create the Screenlet class. 
