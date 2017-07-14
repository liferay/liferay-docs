# Creating Guestbook List Screenlet's Interactor [](id=creating-guestbook-list-screenlets-interactor)

*Interactors* are Screenlet components that make server calls and process the 
results. Interactors themselves are made up of several components: 

1. **The event class:** creates event objects that contain the server call's 
   results. Liferay Screens uses these event objects via the 
   [EventBus](https://greenrobot.github.io/EventBus/) 
   library to communicate the results between the Screenlet's components. 

2. **The listener interface:** defines the methods the app developer needs to 
   respond to the Screenlet's behavior. For example, 
   [Login Screenlet's listener](/develop/reference/-/knowledge_base/6-2/loginscreenlet-for-android#listener) 
   defines the `onLoginSuccess` and `onLoginFailure` methods. Screens calls 
   these methods when login succeeds or fails, respectively. By implementing 
   these methods in the activity or fragment class that contains the Screenlet, 
   the app developer can respond to login success and failure. 

3. **The Interactor class:** makes the server calls, processes the results in 
   the event objects, and notifies the listener of those results. 

Since the list Screenlet framework already contains two listener interfaces, you 
only need to create the event and Interactor classes. You'll create the event 
class first. 

## Creating the Event Class [](id=creating-the-event-class)

Follow these steps to create Guestbook List Screenlet's event class: 

1.  First, create a new package called `interactor` in the 
    `com.liferay.docs.guestbooklistscreenlet` package. You'll create your 
    Interactor's components in this new package. 

2.  A list Screenlet's event class must extend 
    [the `ListEvent` class](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/ListEvent.java) 
    with the Screenlet's model class as a type argument. This lets the event 
    class contain the server call's results as model objects. Guestbook List 
    Screenlet's event class, `GuestbookEvent`, must therefore extend `ListEvent` 
    with `GuestbookModel` as a type argument. Create this class now in the 
    `interactor` package. The class declaration should look like this: 

        public class GuestbookEvent extends ListEvent<GuestbookModel> {...

    This requires you to import `com.liferay.docs.model.GuestbookModel` and 
    `com.liferay.mobile.screens.base.list.interactor.ListEvent`.

3.  The event class should also contain a private instance variable for the 
    model class, a constructor that sets this variable, and a no-argument 
    constructor that calls the superclass constructor. Add this code now: 

        private GuestbookModel guestbook;

        public GuestbookEvent() {
            super();
        }

        public GuestbookEvent(GuestbookModel guestbook) {
            this.guestbook = guestbook;
        }

4.  You must also implement `ListEvent`'s abstract methods in your event class. 
    Note that these methods support 
    [offline mode](/develop/tutorials/-/knowledge_base/6-2/using-offline-mode-in-android). 
    Even though Guestbook List Screenlet doesn't support offline mode, you must 
    still implement these methods. Add these methods to `GuestbookEvent` now: 

    - `getListKey`: returns the ID for the cache. This ID is typically the data 
      each list row displays. For example, the `getListKey` method in 
      `GuestbookEvent` returns the guestbook's name: 

            @Override
            public String getListKey() {
                return guestbook.getName();
            }

    - `getModel`: unwraps the model entity to the cache by returning the model 
      class instance. For example, the `getModel` method in `GuestbookEvent` 
      method returns the guestbook: 

            @Override
            public GuestbookModel getModel() {
                return guestbook;
            }

Note that this code is almost identical to the example event class in 
[the list Screenlet tutorial](/develop/tutorials/-/knowledge_base/6-2/creating-android-list-screenlets#creating-the-screenlets-event). 
The only difference is that `GuestbookEvent` handles `GuestbookModel` objects. 

Nice work! Your event class is done. You're almost ready to write the 
Screenlet's server call. First, however, you should understand the basics of how 
server calls work in Interactors. 

## Understanding Screenlet Server Calls [](id=understanding-screenlet-server-calls)

Recall that Interactor classes use the Liferay Mobile SDK to make server calls 
and process the results. An Interactor class does this with the following 
sequence: 

1. Get the Mobile SDK session and use it to create the Mobile SDK service you 
   want to call.  

2. Invoke the Mobile SDK service method that makes the server call. 

3. Create an event object from the JSON that the server call returns. If your 
   Screenlet has a model class, create a model object from this JSON, then 
   use the model object to create the event object. 

![Figure 1: This diagram shows a typical server call made by a Screenlet's Interactor. The dashed line around the model class indicates that it's optional. Although list Screenlets require model classes, non-list Screenlets don't.](../../../images/android-screenlet-server-call.png)

To call the Guestbook portlet's remote services, you'll use the Guestbook Mobile 
SDK you built and installed earlier. This Mobile SDK contains the services 
required to call the Guestbook portlet's remote services. Next, you'll create 
Guestbook List Screenlet's Interactor class. 

## Creating the Interactor Class [](id=creating-the-interactor-class)

A Screenlet's Interactor class is the central component of the Interactor. It 
makes the server calls, processes the results in the event objects, and notifies 
the listener of those results. The list Screenlet framework's 
[`BaseListInteractor` class](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/BaseListInteractor.java) 
provides most of the functionality that Interactor classes in list Screenlets 
require. You must, however, extend `BaseListInteractor` to make your service 
calls and handle their results via your model and event classes. 

Follow these steps to create Guestbook List Screenlet's Interactor class, 
`GuestbookListInteractor`: 

1.  Create the `GuestbookListInteractor` class in the package 
    `com.liferay.docs.guestbooklistscreenlet.interactor`. A list Screenlet's 
    Interactor class must extend `BaseListInteractor` with 
    `BaseListInteractorListener<YourModelClass>` and your event class as type 
    arguments. You must therefore change `GuestbookListInteractor` to extend 
    `BaseListInteractor` with `BaseListInteractorListener<GuestbookModel>` and 
    `GuestbookEvent` as type arguments:

        public class GuestbookListInteractor extends 
            BaseListInteractor<BaseListInteractorListener<GuestbookModel>, GuestbookEvent> {...

    This requires that you add the following imports: 

        import com.liferay.docs.model.GuestbookModel;
        import com.liferay.mobile.screens.base.list.interactor.BaseListInteractor;
        import com.liferay.mobile.screens.base.list.interactor.BaseListInteractorListener;

2.  Override the `getPageRowsRequest` method to retrieve a page of entities. In 
    this method, you can use the `getSession()` method to retrieve the session 
    created by authentication with Login Screenlet. Then make the server call by 
    creating a service instance from the session and calling the service method 
    that retrieves the entities. Guestbook List Screenlet must retrieve a page 
    of guestbooks, so you must create a `GuestbookService` instance from the 
    session. Then call the service's `getGuestbooks` method with the `groupId`, 
    start row, and end row. The `groupId` specifies the site to retrieve 
    guestbooks from, while the start row and end row define the list rows that 
    mark the start and end of the page of guestbooks, respectively. Add this 
    `getPageRowsRequest` method to `GuestbookListInteractor`: 

        @Override
        protected JSONArray getPageRowsRequest(Query query, Object... args) throws Exception {

            return new GuestbookService(getSession()).getGuestbooks(groupId, query.getStartRow(), 
                query.getEndRow());
        }

    Note that the `groupId` variable isn't set anywhere. Interactors that extend    
    `BaseListInteractor`, like `GuestbookListInteractor`, inherit this variable 
    via the Screens framework. You'll set it when you create the Screenlet 
    class.

    This `getPageRowsRequest` method requires that you add the following 
    imports: 

        import com.liferay.mobile.android.v62.guestbook.GuestbookService;
        import com.liferay.mobile.screens.base.list.interactor.Query;
        import org.json.JSONArray;

3.  Override the `getPageRowCountRequest` method to retrieve the total number of 
    entities. This enables pagination. In `GuestbookListInteractor`, you 
    retrieve the total number of guestbooks from a site by creating a 
    `GuestbookService` instance from the session and then calling the service's 
    `getGuestbooksCount` method with the `groupId`. Add this 
    `getPageRowCountRequest` method to `GuestbookListInteractor`: 

        @Override
        protected Integer getPageRowCountRequest(Object... args) throws Exception {

            return new GuestbookService(getSession()).getGuestbooksCount(groupId);
        }

4.  Override the `createEntity` method to create and return a new event object 
    containing the server call's results. The `BaseListInteractor` class 
    converts the JSON that results from a successful server call into a 
    `Map<String, Object>`. The `createEntity` method's only argument is this 
    `Map`, which you use to create a `GuestbookModel` object. Then use the model 
    object to create and return a new `GuestbookEvent` object. Add this 
    `createEntity` method to `GuestbookListInteractor`: 

        @Override
        protected GuestbookEvent createEntity(Map<String, Object> stringObjectMap) {
            GuestbookModel guestbook = new GuestbookModel(stringObjectMap);
            return new GuestbookEvent(guestbook);
        }

    This requires you to import `java.util.Map`. 

5.  Override the `getIdFromArgs` method to return the value of the first object 
    argument as a string. Add this method to `GuestbookListInteractor`: 

        @Override
        protected String getIdFromArgs(Object... args) {
            return String.valueOf(args[0]);
        }

    This is a boilerplate method that returns a cache key for 
    [offline mode](/develop/tutorials/-/knowledge_base/6-2/using-offline-mode-in-android). 
    Even though you won't add offline mode support to Guestbook List Screenlet, 
    this method makes it easier if you decide to do so later. 

Nice work! Your Interactor class is finished. Note that this class is very 
similar to the Interactor class in 
[the list Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/6-2/creating-android-list-screenlets#creating-the-screenlets-interactor). 

Your Interactor is finished too. Next, you'll create the Screenlet class. 
