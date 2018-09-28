# Creating the Interactor [](id=creating-the-interactor)

A Screenlet's Interactor makes the service call to retrieve the data you need 
from a Liferay instance. An Interactor is made up of several components: 

1. The event class. This class lets you handle communication between the 
   Screenlet's components via event objects that contain the server call's 
   results. Screens uses the 
   [EventBus](https://greenrobot.github.io/EventBus/) 
   library for this. Screens supplies the 
   [`BasicEvent` class](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/interactor/event/BasicEvent.java) 
   and 
   [`BaseListEvent` class](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/BaseListEvent.java) 
   for communicating `JSONObject` and `JSONArray` results within Screenlets, 
   respectively. You can create your own event classes by extending 
   `BasicEvent`. You should create your own event classes when you must 
   communicate objects other than `JSONObject` or `JSONArray`. The example Add 
   Bookmark Screenlet only needs to communicate `JSONObject` instances, so it 
   uses `BasicEvent`. 

2. The listener interface. This defines the methods the app developer needs to 
   respond to the Screenlet's behavior. For example, 
   [Login Screenlet's listener](/develop/reference/-/knowledge_base/7-1/loginscreenlet-for-android#listener) 
   defines the `onLoginSuccess` and `onLoginFailure` methods. Screens calls 
   these methods when login succeeds or fails, respectively. By implementing 
   these methods in the activity or fragment class that contains the Screenlet, 
   the app developer can respond to login success and failure. Similarly, the 
   [example Add Bookmark Screenlet's listener interface](https://github.com/liferay/liferay-screens/blob/master/android/samples/addbookmarkscreenlet/src/main/java/com/liferay/mobile/screens/bookmark/interactor/AddBookmarkListener.java) 
   defines two methods: one for responding to the Screenlet's failure to add a 
   bookmark and one for responding to its success to add a bookmark: 

        public interface AddBookmarkListener {

            void onAddBookmarkFailure(Exception exception);

            void onAddBookmarkSuccess();
        }

3. The Interactor class. This class must extend 
   [Screens's `BaseRemoteInteractor`](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/interactor/BaseRemoteInteractor.java) 
   with your listener and event as type arguments. The listener lets the 
   Interactor class send the server call's results to any classes that implement 
   the listener. In the implementation of the method that makes the server call, 
   the `execute` method, you must 
   [use the Mobile SDK to make an asynchronous service call](/develop/tutorials/-/knowledge_base/7-1/invoking-services-asynchronously-from-your-android-app). 
   This means you must get a session and then make the server call. You make the 
   server call by creating an instance of the Mobile SDK service (e.g., 
   `BookmarksEntryService`) that can call the Liferay service you need and then 
   making the call. The Interactor class must also process the event object that 
   contains the call's results and then notify the listener of those results. 
   You do this by implementing the `onSuccess` and `onFailure` methods to invoke 
   the corresponding `getListener()` methods. 

   For example, the 
   [`AddBookmarkInteractor` class](https://github.com/liferay/liferay-screens/blob/master/android/samples/addbookmarkscreenlet/src/main/java/com/liferay/mobile/screens/bookmark/interactor/AddBookmarkInteractor.java) 
   is Add Bookmark Screenlet's Interactor class. This class implements the 
   `execute` method, which adds a bookmark to a folder in a Liferay instance's 
   Bookmarks portlet. This method first validates the bookmark's URL and 
   folder. It then calls the `getJSONObject` method to add the bookmark, and 
   concludes by returning a new `BasicEvent` object created from the 
   `JSONObject`. The `if` statement in the `getJSONObject` method checks the 
   Liferay version so it can create the appropriate `BookmarksEntryService` 
   instance needed to make the server call. Regardless of the Liferay version, 
   the `getSession()` method retrieves the existing session created by Login
   Screenlet upon successful login. The session's `addEntry` method makes the
   server call. The Screenlet calls the `onSuccess` or `onFailure` method to
   notify the listener of the server call's success or failure, respectively. In
   either case, the `BasicEvent` object contains the server call's results.
   Since this Screenlet doesn't retrieve anything from the server, however,
   there's no need to process the `BasicEvent` object in the `onSuccess` method;
   calling the listener's `onAddBookmarkSuccess` method is sufficient. Here's
   the complete code for `AddBookmarkInteractor`: 

        public class AddBookmarkInteractor extends BaseRemoteInteractor<AddBookmarkListener, BasicEvent> {

            @Override
		    public BasicEvent execute(Object[] args) throws Exception {
                String url = (String) args[0];
                String title = (String) args[1];
                long folderId = (long) args[2];

                validate(url, folderId);

                JSONObject jsonObject = getJSONObject(url, title, folderId);
                return new BasicEvent(jsonObject);
            }

            @Override
            public void onSuccess(BasicEvent event) throws Exception {
                getListener().onAddBookmarkSuccess();
            }

            @Override
            public void onFailure(BasicEvent event) {
                getListener().onAddBookmarkFailure(event.getException());
            }

            private void validate(String url, long folderId) {
                if (url == null || url.isEmpty() || !URLUtil.isValidUrl(url)) {
                    throw new IllegalArgumentException("Invalid url");
                } else if (folderId == 0) {
                    throw new IllegalArgumentException("folderId not set");
                }
            }

            @NonNull
            private JSONObject getJSONObject(String url, String title, long folderId) throws Exception {
                if (LiferayServerContext.isLiferay7()) {
                    return new BookmarksEntryService(getSession()).addEntry(LiferayServerContext.getGroupId(), 
                        folderId, title, url, "", null);
                } else {
                    return new com.liferay.mobile.android.v62.bookmarksentry.BookmarksEntryService(
                        getSession()).addEntry(LiferayServerContext.getGroupId(), folderId, title, url, "", null);
                }
            }
        }

Sweetness! Your Screenlet's Interactor is done. Next, you'll create the 
Screenlet class. 

## Related Topics [](id=related-topics)

[Creating the UI](/develop/tutorials/-/knowledge_base/7-1/creating-the-ui)

[Defining the Attributes](/develop/tutorials/-/knowledge_base/7-1/defining-the-attributes)

[Creating the Screenlet Class](/develop/tutorials/-/knowledge_base/7-1/creating-the-screenlet-class)

[Packaging Your Screenlets](/develop/tutorials/-/knowledge_base/7-1/packaging-your-screenlets)
