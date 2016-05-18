# Creating Get Entries Screenlet's Server Call [](id=creating-get-entries-screenlets-server-call)

To retrieve a guestbook's entries from the Guestbooks portlet, you must call the 
portlet's remote service that returns the entries. As with Get Guestbooks 
Screenlet, you'll do this in Get Entries Screenlet by creating an Interactor. 
Recall that the following components make up an Interactor: an event, a 
callback, a listener, and the Interactor interface and its implementation. This 
article shows you how to create each for the Get Entries Screenlet. You'll use 
the following steps to create the Interactor: 

1. Create the event class.

2. Create the callback class.

3. Create the listener.

4. Create and implement the Interactor interface.

This sequence of steps is identical to the one you followed when you created Get 
Guestbooks Screenlet's Interactor. Likewise, the components you create here are 
almost identical to the analogous components in Get Guestbooks Screenlet. 
Therefore, this article only explains the parts unique to Get Entries Screenlet. 
For a detailed explanation of each component, see 
[the article on creating Get Guestbooks Screenlet's server calls](/develop/tutorials/-/knowledge_base/6-2/creating-get-guestbook-screenlets-server-call). 

First, you'll create the event class. 

## Creating the Event Class [](id=creating-the-event-class)

Recall that Screens uses 
[EventBus](https://github.com/greenrobot/EventBus) 
to handle communication within Screenlets. Since the Screenlet needs to handle 
`EntryModel` objects, you must create an event class that can as well. Create 
a new package called `event` inside the `getentriesscreenlet` package. Then 
create a new class called `GetEntriesEvent` inside the `event` package. Replace 
the class's contents with the following code: 

    package com.liferay.docs.getentriesscreenlet.event;

    import com.liferay.docs.model.EntryModel;
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

Besides working with entries instead of guestbooks, this class is identical to 
`GetGuestbooksEvent`. Next, you'll create the callback class. 

## Creating the Callback Class [](id=creating-the-callback-class)

Recall that you must make server calls via a callback class because Android 
doesn't allow network requests on the main UI thread. The callback class must 
also transform the JSON it receives from the server call into `EntryModel` 
objects, and communicate the server call's results in a `GetEntriesEvent` 
object. You'll create this callback class now. 

First, create a new package called `interactor` in the 
`getentriesscreenlet` package. Then create a new class called 
`GetEntriesCallback` in the `interactor` package. Replace the class's contents 
with the following code: 

    package com.liferay.docs.getentriesscreenlet.interactor;

    import com.liferay.docs.model.EntryModel;
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

Besides working with entries instead of guestbooks, this class is identical to 
`GetGuestbooksCallback`. Now you need to create the Interactor's listener 
interface. 

## Creating the Listener [](id=creating-the-listener)

Recall that the listener interface defines the methods needed to control or 
respond to the Screenlet's behavior. Like the Get Guestbooks Screenlet's 
listener interface, the Get Entries Screenlet's listener interface must define 
the methods that respond to the server call's results. The listener interface 
must also define the method that responds to a list item click. 

Create the interface `GetEntriesListener` in the `getentriesscreenlet` package. 
Replace the interface's contents with the following code: 

    package com.liferay.docs.getentriesscreenlet;

    import com.liferay.docs.model.EntryModel;
    import java.util.List;

    public interface GetEntriesListener {

        void onGetEntriesFailure(Exception e);

        void onGetEntriesSuccess(List<EntryModel> entries);

        void onItemClicked(EntryModel entry);
    }

Besides working with entries instead of guestbooks, this interface is identical 
to `GetGuestbooksListener`. Next, you'll create and implement the Interactor 
interface. 

## Creating and Implementing the Interactor Interface [](id=creating-and-implementing-the-interactor-interface)

Recall that the Interactor interface and its implementation define and implement 
the methods used to make the server call. Like Get Guestbooks Screenlet, Get 
Entries Screenlet's Interactor interface only needs to define a single method 
for making the server call. Also like Get Guestbooks Screenlet, when you 
implement this interface you'll create an additional method that sends event 
objects to the listener. 

In the `interactor` package, create an interface called `GetEntriesInteractor`. 
Replace the interface's contents with the following code: 

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

    }

This implementation retrieves the entries by passing `groupId` and `guestbookId` 
to the remote service's `getEntries` method. Otherwise, besides using entries 
instead of guestbooks, `GetEntriesInteractorImpl` is identical to 
`GetGuestbooksInteractorImpl`. 

Nicely done! Now that Get Entries Screenlet has an Interactor, you must create 
the Screenlet class. The next article shows you how to do this. 
