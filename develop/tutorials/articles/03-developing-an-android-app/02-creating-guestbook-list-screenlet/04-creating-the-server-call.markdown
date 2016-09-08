# Creating Guestbook List Screenlet's Server Call 

In the previous article, you learned that Screenlets use a Mobile SDK to make 
server calls via Interactors. Now you'll create Guestbook List Screenlet's 
Interactor. In this Interactor, you'll use the Guestbook Mobile SDK to call the 
Guestbook portlet's remote services. Recall that this is necessary because the 
Guestbook Mobile SDK contains the services required to call the portlet's remote 
services. 

An Interactor consists of the following components: 

1. The event. Screens uses event objects via the 
   [EventBus](http://greenrobot.org/eventbus/) 
   library to communicate the server call's results within a Screenlet. A 
   Screens event class creates these event objects, which contain the server 
   call's results. The list Screenlet framework provides 
   [the event class `BaseListEvent`](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/BaseListEvent.java), 
   so you don't need to create an event class manually. 

2. The callback. Because Android doesn't allow network requests on its main UI 
   thread, a callback class is required to route the server call asynchronously 
   through a background thread. The callback also processes the JSON returned by 
   a successful server call, and uses the event class to create event objects 
   that contain these results. The list Screenlet framework provides 
   [the callback class `BaseListCallback`](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/BaseListCallback.java), 
   so you don't need to create one manually. 

3. The listener(s). Listener interfaces define the methods the Screenlet needs 
   to communicate the event object's results within the Screenlet, and to the 
   app using the Screenlet. Any object that implements a listener interface 
   receives the server call's results and thus serves as the listener. Non-list 
   Screenlets usually only need a single listener. Due to the added complexity 
   of the list Screenlet framework, however, it's a best practice that list 
   Screenlets have two listeners: one that communicates results within the 
   Screenlet, and one that communicates results to the app using the Screenlet. 
   This decouples the Screenlet's implementation from the part of the Screenlet 
   exposed to the app developer. To help you with this, the list Screenlet 
   framework provides these listeners: 

    - [`BaseListInteractorListener`](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/BaseListInteractorListener.java): 
      Communicates results within the Screenlet. This listener is part of the 
      Screenlet implementation and isn't exposed to the app developer. 

    - [`BaseListListener`](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListListener.java):
      Communicates results to the app activity or fragment that contains the 
      Screenlet. This lets the app developer respond to the Screenlet's actions. 

    You'll use these listeners as-is in Guestbook List Screenlet; there's no 
    need to extend them. This article describes some of 
    `BaseListInteractorListener`. You'll learn the rest, and learn how 
    `BaseListListener` works, when you create the Screenlet class later. 

4. The Interactor class. This class: 

    - implements the method that makes the server call
    - processes the event object that contains the call's results
    - notifies the listener of those results

    If you were creating a non-list Screenlet, you'd create the Interactor class 
    by creating and implementing an Interactor interface. Since you're creating 
    a list Screenlet, however, you can just extend 
    [the `BaseListInteractor` class](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/BaseListInteractor.java) 
    the list Screenlet framework provides. 

Since Guestbook List Screenlet is a list Screenlet, you only need to create its 
Interactor class! The list Screenlet framework provides default implementations 
for the event, callback, and listeners. 

Now it's time to get classy! 

## Creating the Interactor Class

To create Guestbook List Screenlet's Interactor class, you must extend the list 
Screenlet framework's 
[`BaseListInteractor` class](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/BaseListInteractor.java). 
Before doing this, however, you should know how `BaseListInteractor` processes 
the server call's results in its 
[`onEventMainThread` method](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/BaseListInteractor.java#L61-L75) 
and 
[`notifyError` method](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/BaseListInteractor.java#L87-L91). 
These methods take an event object (`BaseListEvent`), get the server call's 
results from the event, and then pass these results to a 
`BaseListInteractorListener` instance retrieved with `getListener`. Here's how 
each method works: 

- `onEventMainThread`: Called in response to a successful server call. This 
  method first uses an `if` statement with `isValidEvent` to make sure the event 
  is associated with the correct Screenlet and isn't `null`. For use with 
  [offline mode](/develop/tutorials/-/knowledge_base/7-0/architecture-of-offline-mode-in-liferay-screens), 
  `onEventMainThread` then calls `onEventWithCache` to store the event to the 
  cache. The `onEventMainThread` method finishes with an `if` statement that 
  calls the event's `isFailed` method to make sure the event doesn't contain an 
  exception. This verifies that the event succeeded and therefore contains the 
  objects the server call intended to retrieve. The `event.getEntries()` call 
  then gets these objects from the event. Similar calls to the event's 
  `getRowCount`, `getStartRow`, and `getEndRow` methods get the number of rows, 
  starting row number, and ending row number, respectively. This supports 
  pagination in the Screenlet's list. The `BaseListInteractorListener` method 
  `onListRowsReceived` then sends this data to any classes that implement it. 

- `notifyError`: Called in response to a failed server call. This method uses 
  the event's `getStartRow`, `getEndRow`, and `getException` methods to retrieve 
  the starting row number, ending row number, and exception, respectively. The 
  `BaseListInteractorListener` method `onListRowsFailure` then sends this data 
  to any classes that implement it. 

There's a key point here that warrants emphasis: objects of any class that 
implements `BaseListInteractorListener` receive the server call's results via 
these methods and thus serve as this listener. 

When you extend `BaseListInteractor` to create Guestbook List Screenlet's 
Interactor class, you can rely on the default `onEventMainThread` and 
`notifyError` implementations. You must, however, implement 
`BaseListInteractor`'s following abstract methods: 

- `getCallback`: Returns a new `BaseListCallback` instance to use when making 
  the service call. 

- `getPageRowsRequest`: Makes the server call to get a page of guestbooks. Note 
  that this is a page as represented in the Screenlet, not in the portlet. 

- `getPageRowCountRequest`: Makes the server call to get the total number of 
  guestbooks in the portlet. 

You must also implement the methods required to support 
[offline mode in Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/architecture-of-offline-mode-in-liferay-screens). 

First, create a new package called `interactor` in 
`com.liferay.docs.guestbooklistscreenlet`. Then create a new class called 
`GuestbookListInteractorImpl` in the `interactor` package. Change the class 
declaration to extend `BaseListInteractor` with `GuestbookModel` and 
`BaseListInteractorListener<GuestbookModel>` as type arguments. You should also 
add an instance variable for the group ID (site ID) of the site the Screenlet 
retrieves guestbooks from. The class declaration and first few lines of the 
class should now look like this: 

    public class GuestbookListInteractorImpl extends BaseListInteractor<GuestbookModel, 
        BaseListInteractorListener<GuestbookModel>> {

        private long _groupId;
        ...

Now you must implement `BaseListInteractor`'s abstract `getCallback` method to 
return a new `BaseListCallback<GuestbookModel>` instance. Before implementing 
this method, however, you should know how `BaseListCallback` processes a server 
call's results. Recall that the list Screenlet framework converts the JSON that 
results from a successful server call into `Map` objects, and that you must then 
convert the `Map` objects into model objects. This all happens in 
[a single line of `BaseListCallback`'s `transform` method](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/BaseListCallback.java#L52). 
This line converts each `JSONObject` into a `Map` object, and then uses 
`BaseListCallback`'s `createEntity` method to convert the `Map` into a model 
object. Since 
[`createEntity` is abstract](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/BaseListCallback.java#L79), 
you must implement it to create your model objects. 

You can do all this in your `getCallback` implementation by extending 
`BaseListCallback<GuestbookModel>` as an anonymous inner class that implements 
`createEntity`. All your `createEntity` implementation needs to do is call the 
`GuestbookModel` constructor with a `Map<String, Object>`. Add the following 
`getCallback` implementation to `GuestbookListInteractorImpl`: 

    @Override
    protected BaseListCallback<GuestbookModel> getCallback(Pair<Integer, Integer> rowsRange, Locale locale) {
        return new BaseListCallback<GuestbookModel>(getTargetScreenletId(), rowsRange, locale) {
            @Override
            public GuestbookModel createEntity(Map<String, Object> stringObjectMap) {
                return new GuestbookModel(stringObjectMap);
            }
        };
    }

Now that your Screenlet can create a callback instance, you're ready to 
implement the `BaseListInteractor` methods that make the server calls: 
`getPageRowsRequest` and `getPageRowCountRequest`. Recall that to make server 
calls, you must create a Mobile SDK service instance that contains the methods 
you'll use to call your server's remote services. You must also create this 
service instance with an authenticated session that contains a callback. You can 
do all this, and make the call, with a single line of code in 
`getPageRowsRequest` and `getPageRowCountRequest`. Add these methods to 
`GuestbookListInteractorImpl`: 

    @Override
    protected void getPageRowsRequest(Session session, int startRow, int endRow, Locale locale) 
        throws Exception {
            new GuestbookService(session).getGuestbooks(_groupId, startRow, endRow);
    }

    @Override
    protected void getPageRowCountRequest(Session session) throws Exception {
        new GuestbookService(session).getGuestbooksCount(_groupId);
    }

In both methods, you create the `GuestbookService` instance with a session and 
then call the service method. The `getGuestbooks` service method retrieves a 
page of guestbooks from the site matching `_groupId`. The `getGuestbooksCount` 
service method retrieves the total number of guestbooks from the same site. You 
might now be thinking, "Hey, wait a minute! There's no callback assignment here! 
You said a callback had to be set to the session! You're a phony!" Phony 
accusations aside, you're partially correct: there's no callback assignment 
*here*. 
[`BaseListInteractor`'s `getSession` method](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/BaseListInteractor.java#L160-L168) 
sets the callback to the session for you. This method retrieves the current 
session, which Login Screenlet creates when the user authenticates. A Mobile SDK 
batch session object--`BatchSessionImpl`--is then created from the session. This 
lets the Mobile SDK 
[make the calls in batch](/develop/tutorials/-/knowledge_base/7-0/sending-your-android-apps-requests-using-batch-processing), 
which is usually more efficient. The callback is created by your `getCallback` 
implementation, and then set to the batch session by `setCallback`. This last 
step occurs in 
[a single line of code](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/BaseListInteractor.java#L165). 

The rest of the code you'll add to `GuestbookListInteractorImpl`, including the 
constructor, is boilerplate code that 
[supports offline mode in Screens](/develop/tutorials/-/knowledge_base/7-0/architecture-of-offline-mode-in-liferay-screens). 
Add this code to `GuestbookListInteractorImpl` now: 

    private enum GUESTBOOK_LIST implements CachedType {GUESTBOOK, GUESTBOOK_COUNT}

    public GuestbookListInteractorImpl(int targetScreenletId, OfflinePolicy offlinePolicy) {
        super(targetScreenletId, offlinePolicy);
    }

    public void loadRows(int startRow, int endRow, Locale locale, long groupId)
            throws Exception {

        _groupId = groupId;

        processWithCache(startRow, endRow, locale);
    }

    @Override
    protected String getContent(GuestbookModel object) {
        return new JSONObject(object.getValues()).toString();
    }

    @Override
    protected GuestbookModel getElement(TableCache tableCache) throws JSONException {
        return new GuestbookModel(JSONUtil.toMap(new JSONObject(tableCache.getContent())));
    }

    @Override
    protected void storeToCache(BaseListEvent event, Object... args) {
        storeRows(String.valueOf(_groupId), GUESTBOOK_LIST.GUESTBOOK,
                GUESTBOOK_LIST.GUESTBOOK_COUNT, _groupId, null, event);
    }

    @Override
    protected boolean cached(Object... args) throws Exception {
        final int startRow = (int) args[0];
        final int endRow = (int) args[1];
        final Locale locale = (Locale) args[2];

        return recoverRows(String.valueOf(_groupId), GUESTBOOK_LIST.GUESTBOOK,
                GUESTBOOK_LIST.GUESTBOOK_COUNT, _groupId, null, locale, startRow, endRow);
    }

For an explanation of this code, see 
[the list Screenlet tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-android-list-screenlets). 
<!-- 
Add further loadRows detail to list Screenlet tutorial: 
processWithCache ends up calling super.loadRows (BaseListInteractor's loadRows method)
to make the server call
-->

Nice work! You now have the Interactor required to get guestbooks from the 
Guestbook portlet. Next, you'll create the Screenlet class. 
