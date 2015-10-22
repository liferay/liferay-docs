# Creating the Get Guestbook Screenlet's Server Calls [](id=creating-the-get-guestbook-screenlets-server-calls)

For the Get Guestbooks Screenlet to work, it needs to call the Guestbook 
portlet's remote service that returns the guestbooks. In Screenlets, server 
calls are handled by *interactors*. A Screenlet needs an interactor for each 
server call that it makes. Since the Get Guestbooks Screenlet needs to make one 
server call to retrieve the guestbooks, it needs one interactor. The following 
components make up an interactor: an event, a callback, the interactor interface 
and its implementation, and a listener. This article shows you how to create 
each. First, you'll create the event. 

## Creating the Event Class [](id=creating-the-event-class)

Screens uses the 
[EventBus](https://github.com/greenrobot/EventBus)
library to handle communication within Screenlets. You can often use Screens's 
`JSONObjectEvent` to handle events in your Screenlets. The Get Guestbooks 
Screenlet, however, needs to handle `GuestbookModel` objects. You therefore 
need to create an event class that is capable of this.

In Android Studio, create a new package called *event* inside the 
`getguestbooksscreenlet` package. Inside the `event` package, create a new class 
called `GetGuestbooksEvent`. Replace the class's code with the following:

    package com.liferay.docs.getguestbooksscreenlet.event;

    import com.liferay.docs.getguestbooksscreenlet.GuestbookModel;
    import com.liferay.mobile.screens.base.interactor.BasicEvent;

    import java.util.List;

    public class GetGuestbooksEvent extends BasicEvent {
    
        private List<GuestbookModel> _guestbooks;

        public GetGuestbooksEvent(int targetScreenletId, Exception e) {
            super(targetScreenletId, e);
        }

        public GetGuestbooksEvent(int targetScreenletId, List<GuestbookModel> guestbooks) {
            super(targetScreenletId);

            _guestbooks = guestbooks;
        }

        public List<GuestbookModel> getGuestbooks() {
            return _guestbooks;
        }

    }

The first argument to the constructors is `targetScreenletId`. The 
`targetScreenletId` makes sure events are aimed at the correct Screenlet. The 
second argument to each constructor indicates the constructor's purpose. The 
Screenlet uses `GetGuestbooksEvent(int targetScreenletId, Exception e)` when the 
server call fails, and 
`GetGuestbooksEvent(int targetScreenletId, List<GuestbookModel> guestbooks)` 
when the server call succeeds. In either case, the resulting 
`GetGuestbooksEvent` object communicates success or failure when used elsewhere 
in the Screenlet. The event class's `getGuestbooks` method lets you retrieve the 
guestbooks, if any, from the event object. 

Next, you'll create a callback class that uses this event class. 

## Creating the Callback Class [](id=creating-the-callback-class)

When you used the Guestbook Mobile SDK directly, you 
[created a callback class](/develop/learning-paths/mobile/-/knowledge_base/6-2/retrieve-and-display-guestbooks#creating-a-callback-class) 
to make network requests asynchronously from another thread. You need to do the 
same thing in Screenlets. First, create a new package called 
`interactor` in the `getguestbooksscreenlet` package. Then create a new class 
called `GetGuestbooksCallback` inside the `interactor` package. Replace 
its code with the following: 

    package com.liferay.docs.getguestbooksscreenlet.interactor;

    import com.liferay.docs.getguestbooksscreenlet.GuestbookModel;
    import com.liferay.docs.getguestbooksscreenlet.event.GetGuestbooksEvent;
    import com.liferay.mobile.screens.base.interactor.BasicEvent;
    import com.liferay.mobile.screens.base.interactor.InteractorAsyncTaskCallback;

    import org.json.JSONArray;
    import org.json.JSONObject;

    import java.util.ArrayList;
    import java.util.List;

    public class GetGuestbooksCallback extends InteractorAsyncTaskCallback<List<GuestbookModel>> {

        public GetGuestbooksCallback(int targetScreenletId) {
            super(targetScreenletId);
        }

        @Override
        protected BasicEvent createEvent(int targetScreenletId, List<GuestbookModel> guestbooks) {
            return new GetGuestbooksEvent(targetScreenletId, guestbooks);
        }

        @Override
        protected BasicEvent createEvent(int targetScreenletId, Exception e) {
            return new GetGuestbooksEvent(targetScreenletId, e);
        }

        @Override
        public List<GuestbookModel> transform(Object obj) throws Exception {
            List<GuestbookModel> guestbooks = new ArrayList<GuestbookModel>();

            JSONArray jsonArray = (JSONArray)obj;

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);

                guestbooks.add(new GuestbookModel(json));
            }

            return guestbooks;
        }

    }

Extending Screens's `InteractorAsyncTaskCallback` class abstracts away much of 
the complexity of asynchronous calls in Android. Following the simple 
constructor, there are two `createEvent` methods for creating the respective 
`GetGuestbookEvent` objects you defined in the event class. The callback class 
finishes with the `transform` method. The `transform` method here is identical 
to the one you created when you used the Mobile SDK directly: it transforms the 
`JSONArray` of `JSONObject` objects returned from the server into an `ArrayList` 
of `GuestbookModel` objects. 

Next, you need to create the listener interface you'll use to notify the app of 
the Screenlet's actions. 

## Creating the Listener [](id=creating-the-listener)

The listener interface defines the methods needed by the Screenlet class to 
notify the app of the Screenlet's behavior. This lets the app developer respond 
to the Screenlet's actions. Create the `GetGuestbooksListener` interface now in 
the `getguestbooksscreenlet` package. Replace the interface's contents with the 
following code: 

    package com.liferay.docs.getguestbooksscreenlet;

    import java.util.List;

    public interface GetGuestbooksListener {

        void onGetGuestbooksFailure(Exception e);

        void onGetGuestbooksSuccess(List<GuestbookModel> guestbooks);

        void onItemClicked(GuestbookModel guestbook);
    }

The methods `onGetGuestbooksSuccess` and `onGetGuestbooksFailure` let the 
implementing class respond to the Screenlet's success or failure to retrieve 
Guestbooks, respectively. The `onItemClicked` method lets the implementing class 
respond when the user clicks a guestbook in the `ListView`. Now you can use this 
listener to create and implement the interactor interface. 

## Creating and Implementing the Interactor Interface [](id=creating-and-implementing-the-interactor-interface)

Since you've implemented the framework needed to call the server, you now need 
to create and implement the interactor interface that makes the server call. In 
the `interactor` package, create the `GetGuestbooksInteractor` interface. 
Replace the interface's contents with the following code: 

    package com.liferay.docs.getguestbooksscreenlet.interactor;

    import com.liferay.docs.getguestbooksscreenlet.GetGuestbooksListener;
    import com.liferay.mobile.screens.base.interactor.Interactor;

    public interface GetGuestbooksInteractor extends Interactor<GetGuestbooksListener> {

        void getGuestbooks(long groupId);
    }

Pretty simple, right? By extending `Interactor<GetGuestbooksListener>`, any 
classes that implement `GetGuestbooksInteractor` can notify the Screenlet class 
of the interactor's status. The Screenlet handles this communication via the 
`GetGuestbooksListener` interface's methods. The `GetGuestbooksInteractor` 
interface only has a single method: `getGuestbooks`. This method takes a site 
ID (`groupId`) as an argument so it can retrieve all the guestbooks from a 
Guestbook portlet on that site. 

Now you'll implement this interactor interface. Create the 
`GetGuestbooksInteractorImpl` class in the `interactor` package. Replace this 
class's contents with the following code:

    package com.liferay.docs.getguestbooksscreenlet.interactor;

    import com.liferay.docs.getguestbooksscreenlet.GetGuestbooksListener;
    import com.liferay.docs.getguestbooksscreenlet.event.GetGuestbooksEvent;
    import com.liferay.mobile.android.service.Session;
    import com.liferay.mobile.android.v62.guestbook.GuestbookService;
    import com.liferay.mobile.screens.base.interactor.BaseRemoteInteractor;
    import com.liferay.mobile.screens.context.SessionContext;
    import com.liferay.mobile.screens.util.LiferayLogger;

    public class GetGuestbooksInteractorImpl extends BaseRemoteInteractor<GetGuestbooksListener>
        implements GetGuestbooksInteractor {

        public GetGuestbooksInteractorImpl(int targetScreenletId) {
            super(targetScreenletId);
        }

        public void getGuestbooks(long groupId) {

            Session session = SessionContext.createSessionFromCurrentSession();
            session.setCallback(new GetGuestbooksCallback(getTargetScreenletId()));
            GuestbookService service = new GuestbookService(session);

            try {
                service.getGuestbooks(groupId);
            } catch (Exception e) {
                LiferayLogger.e("Could not get Guestbooks", e);
            }
        }

        public void onEvent(GetGuestbooksEvent event) {
            if (!isValidEvent(event)) {
                return;
            }

            if (event.isFailed()) {
                getListener().onGetGuestbooksFailure(event.getException());
            }
            else {
                getListener().onGetGuestbooksSuccess(event.getGuestbooks());
            }
        }
    }

In addition to implementing `GetGuestbooksInteractor`, this class extends 
Screens's `BaseRemoteInteractor` class with `GetGuestbooksListener` as a type 
parameter. The `GetGuestbooksInteractorImpl` class uses the superclass 
constructor as its only constructor. 

As mentioned before, the `getGuestbooks` method retrieves all the guestbooks 
from a site. This method is almost identical to the 
[`getGuestbooks` method you created when using the Guestbook Mobile SDK directly](/develop/learning-paths/mobile/-/knowledge_base/6-2/retrieve-and-display-guestbooks#making-the-server-call). 
Despite minor differences, it does the same thing. 

The `GetGuestbooksInteractorImpl` class finishes with the `onEvent` method. This 
method processes the event that contains the service call's results. The 
listener then notifies the Screenlet class of the results. 

Nice work! You now have the classes required to call the Guestbook portlet. 
Next, you'll create the Screenlet class. 
