# Creating the Get Entries Screenlet's Server Calls

To retrieve a guestbook's entries from the Guestbooks portlet, you need to call 
the portlet's remote service that returns the entries. As with Get Guestbooks 
Screenlet, you'll do this in Get Entries Screenlet by creating an interactor. 
Recall that the following components make up an interactor: an event, a 
callback, the interactor interface and its implementation, and a listener. This 
article shows you how to create each for the Get Entries Screenlet. The 
components you create here are almost identical to the analogous components you 
created for Get Guestbooks Screenlet. Therefore, this article doesn't explain 
these components in detail. For such an explanation, see 
[the article on creating the Get Guestbooks Screenlet's server calls](http://www.liferay.com/).

First, you'll create the event class. 

## Creating the Event Class

Recall that Screens uses 
[EventBus](https://github.com/greenrobot/EventBus) 
to handle communication within Screenlets. Since the Screenlet needs to handle 
`EntryModel` objects, you need to create an event class that can as well. Create 
a new package called `event` inside the `getentriesscreenlet` package. Then 
create a new class called `GetEntriesEvent` inside the `event` package. Replace 
the class's contents with the following code: 

    package com.liferay.docs.getentriesscreenlet.event;

    import com.liferay.docs.getentriesscreenlet.EntryModel;
    import com.liferay.mobile.screens.base.interactor.BasicEvent;

    import java.util.List;

    public class GetEntriesEvent extends BasicEvent {

        private List<EntryModel> _entries;

        public GetEntriesEvent(int targetScreenletId, Exception e) {
            super(targetScreenletId, e);
        }

        public GetEntriesEvent(int targetScreenletId, List<EntryModel> entries) {
            super(targetScreenletId);

            _entries = entries;
        }

        public List<EntryModel> getEntries() {
            return _entries;
        }
    }

Next, you'll create the callback class. 

## Creating the Callback Class

Recall that you need to make server calls via a callback class because Android 
doesn't allow network requests on the main UI thread. First, create a new 
package called `interactor` in the `getentriesscreenlet` package. Then create a 
new class called `GetEntriesCallback` in the `interactor` package. Replace the 
class's contents with the following code: 

    package com.liferay.docs.getentriesscreenlet.interactor;

    import com.liferay.docs.getentriesscreenlet.EntryModel;
    import com.liferay.docs.getentriesscreenlet.event.GetEntriesEvent;
    import com.liferay.mobile.screens.base.interactor.BasicEvent;
    import com.liferay.mobile.screens.base.interactor.InteractorAsyncTaskCallback;

    import org.json.JSONArray;
    import org.json.JSONObject;

    import java.util.ArrayList;
    import java.util.List;

    public class GetEntriesCallback extends InteractorAsyncTaskCallback<List<EntryModel>> {

        public GetEntriesCallback(int targetScreenletId) {
            super(targetScreenletId);
        }

        @Override
        protected BasicEvent createEvent(int targetScreenletId, List<EntryModel> entries) {
            return new GetEntriesEvent(targetScreenletId, entries);
        }

        @Override
        protected BasicEvent createEvent(int targetScreenletId, Exception e) {
            return new GetEntriesEvent(targetScreenletId, e);
        }

        @Override
        public List<EntryModel> transform(Object obj) throws Exception {
            List<EntryModel> entries = new ArrayList<EntryModel>();

            JSONArray jsonArray = (JSONArray)obj;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);

                entries.add(new EntryModel(json));
            }

            return entries;
        }
    }

Now you need to create the interactor's listener interface.

## Creating the Listener

Listeners communicate the Screenlet's behavior to the app. The listener 
interface therefore needs to define the methods that do this. Create the 
interface `GetEntriesListener` in the `getentriesscreenlet` package. Replace the 
interface's contents with the following code: 

    package com.liferay.docs.getentriesscreenlet;

    import java.util.List;

    public interface GetEntriesListener {

        void onGetEntriesFailure(Exception e);

        void onGetEntriesSuccess(List<EntryModel> entries);

        void onItemClicked(EntryModel entry);
    }

Next, you need to create and implement the interactor interface.

## Creating and Implementing the Interactor Interface

The interactor interface and its implementation define and implement the methods 
used to make the server call. In the `interactor` package, create an interface 
called `GetEntriesInteractor`. Replace the interface's contents with the 
following code:

    package com.liferay.docs.getentriesscreenlet.interactor;

    import com.liferay.docs.getentriesscreenlet.GetEntriesListener;
    import com.liferay.mobile.screens.base.interactor.Interactor;

    public interface GetEntriesInteractor extends Interactor<GetEntriesListener> {

        void getEntries(long groupId, long guestbookId);
    }

This interface is very similar to `GetGuestbooksInteractor`. Both define a 
`get*` method that needs a `groupId` to retrieve entities from the portlet. The 
`getEntries` method, however, also needs a `guestbookId` so it can retrieve that 
guestbook's entries. Now you're ready to implement `GetEntriesInteractor`. 
Create a class called `GetEntriesInteractorImpl` in the `interactor` package. 
Replace its contents with the following code:

    package com.liferay.docs.getentriesscreenlet.interactor;

    import com.liferay.docs.getentriesscreenlet.GetEntriesListener;
    import com.liferay.docs.getentriesscreenlet.event.GetEntriesEvent;
    import com.liferay.mobile.android.service.Session;
    import com.liferay.mobile.android.v62.entry.EntryService;
    import com.liferay.mobile.screens.base.interactor.BaseRemoteInteractor;
    import com.liferay.mobile.screens.context.SessionContext;
    import com.liferay.mobile.screens.util.LiferayLogger;

    public class GetEntriesInteractorImpl extends BaseRemoteInteractor<GetEntriesListener> 
        implements GetEntriesInteractor {

        public GetEntriesInteractorImpl(int targetScreenletId) {
            super(targetScreenletId);
        }

        public void getEntries(long groupId, long guestbookId) {

            Session session = SessionContext.createSessionFromCurrentSession();
            session.setCallback(new GetEntriesCallback(getTargetScreenletId()));
            EntryService service = new EntryService(session);

            try {
                service.getEntries(groupId, guestbookId);
            } catch (Exception e) {
                LiferayLogger.e("Could not get Entries", e);
            }
        }

        public void onEvent(GetEntriesEvent event) {
            if (!isValidEvent(event)) {
                return;
            }

            if (event.isFailed()) {
                getListener().onGetEntriesFailure(event.getException());
            }
            else {
                getListener().onGetEntriesSuccess(event.getEntries());
            }
        }
    }

This implementation retrieves the entries by passing `groupId` and `guestbookId` 
to the remote service's `getEntries` method. Otherwise, 
`GetEntriesInteractorImpl` is almost identical to `GetGuestbooksInteractorImpl`. 

Nicely done! Now that Get Entries Screenlet has an interactor, you need to 
create the Screenlet class. The next article shows you how to do this. 
