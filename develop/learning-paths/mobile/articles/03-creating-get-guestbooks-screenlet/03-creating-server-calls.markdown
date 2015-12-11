# Creating the Get Guestbook Screenlet's Server Call [](id=creating-the-get-guestbook-screenlets-server-call)

There are some similarities between server calls made by a Screenlet and those 
made by the Mobile SDK in an activity or fragment. This is because Screenlets 
also use the Mobile SDK to make server calls. For example, the Get Guestbooks 
Screenlet must use the Guestbook Mobile SDK to make the server call that gets 
guestbooks. When you 
[used the Guestbook Mobile SDK to make the server call from `GuestbooksActivity`](/develop/learning-paths/mobile/-/knowledge_base/6-2/retrieving-guestbooks), 
you made the call via a callback class. The Get Guestbooks Screenlet must also 
make the call via a callback class. That's where the similarities end, though. 

Screenlets use *interactors* to make server calls. A Screenlet needs an 
interactor for each server call that it makes. Since the Get Guestbooks 
Screenlet needs to make one server call to retrieve the guestbooks, it needs one 
interactor. The following components make up an interactor: an event, a 
callback, the interactor interface and its implementation, and a listener. This 
article shows you how to create each. 

You'll create the interactor by using the following steps:

1. Create the event class. This class handles communication between the 
   Screenlet's components.

2. Create the callback class. Because Android doesn't allow network requests on 
   its main UI thread, the callback class is required to route the server call 
   asynchronously through a background thread.

3. Create the listener. This defines the methods that the app developer needs to 
   respond to the Screenlet's behavior

4. Create and implement the interactor interface. This defines and implements 
   the method that makes the server call. The implementation must also process 
   the event object that contains the call's results, and then notify the 
   listener of those results. 

First, you'll create the event. 

## Creating the Event Class [](id=creating-the-event-class)

Screens uses the 
[EventBus](https://greenrobot.github.io/EventBus/) 
library to handle communication within Screenlets. Your Screenlet's components 
must therefore use *event classes* to communicate with each other. Screens comes 
with 
[`JSONObjectEvent`](https://github.com/liferay/liferay-screens/blob/1.2.0/android/library/core/src/main/java/com/liferay/mobile/screens/base/interactor/JSONObjectEvent.java) 
and 
[`JSONArrayEvent`](https://github.com/liferay/liferay-screens/blob/1.2.0/android/library/core/src/main/java/com/liferay/mobile/screens/base/interactor/JSONArrayEvent.java) 
for, respectively, communicating `JSONObject` and `JSONArray` results within 
Screenlets. These classes extend the abstract class 
[`BasicEvent`](https://github.com/liferay/liferay-screens/blob/1.2.0/android/library/core/src/main/java/com/liferay/mobile/screens/base/interactor/BasicEvent.java).
Likewise, you can create your own event classes by extending `BasicEvent`. You 
should create your own event classes when you must communicate objects other 
than `JSONObject` or `JSONArray`. Because the Get Guestbooks Screenlet must 
communicate `GuestbookModel` objects, you must create an event class capable of 
doing so. 

In Android Studio, create a new package called `event` inside the 
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

The first argument to both constructors is `targetScreenletId`. The 
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

When you used the Guestbook Mobile SDK directly in an activity or fragment, you 
[created a callback class](/develop/learning-paths/mobile/-/knowledge_base/6-2/retrieving-guestbooks#creating-a-callback-class) 
to route network requests asynchronously through another thread. You did this 
because Android doesn't allow network requests from the main UI thread. You also 
need to use a callback class in Screenlets.

First, create a new package called `interactor` in the `getguestbooksscreenlet` 
package. Then create a new class called `GetGuestbooksCallback` inside the 
`interactor` package. Replace its code with the following: 

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
the complexity of asynchronous calls in Android. This lets you focus on handling 
the call's results. Following the simple constructor, there are two 
`createEvent` methods for creating the respective `GetGuestbookEvent` objects 
you defined in the event class. The callback class finishes with the `transform` 
method. The `transform` method here is identical to the one you created when you 
used the Mobile SDK directly in an activity or fragment: it transforms the 
`JSONArray` of `JSONObject` objects returned from the server into an `ArrayList` 
of `GuestbookModel` objects. 

Now that you have the event and callback classes, you must create the 
Screenlet's listener interface. Later, you'll implement the listener so you can 
notify the app of the Screenlet's results. 

## Creating the Listener [](id=creating-the-listener)

The listener interface defines the methods that the app developer needs to 
respond to the Screenlet's behavior. The Screenlet class, and the activity or 
fragment the app developer uses the Screenlet in, must implement the listener 
interface. 

Create the `GetGuestbooksListener` interface in the `getguestbooksscreenlet` 
package. Replace the interface's contents with the following code: 

    package com.liferay.docs.getguestbooksscreenlet;

    import java.util.List;

    public interface GetGuestbooksListener {

        void onGetGuestbooksFailure(Exception e);

        void onGetGuestbooksSuccess(List<GuestbookModel> guestbooks);

        void onItemClicked(GuestbookModel guestbook);
    }

The methods `onGetGuestbooksSuccess` and `onGetGuestbooksFailure` let the 
implementing class respond, respectively, to the Screenlet's success or failure 
to retrieve Guestbooks. The `onItemClicked` method lets the implementing class 
respond when the user clicks a guestbook in the `ListView`. Now you can use this 
listener to create and implement the interactor interface. 

## Creating and Implementing the Interactor Interface [](id=creating-and-implementing-the-interactor-interface)

The interactor interface and its implementation must contain the method that the 
interactor uses to make the server call. The implementation must also process 
the event object that contains the call's results, and then notify the listener 
of those results. The Screenlet class, because it implements the listener, 
therefore receives the results from the event object. 

First, create the `GetGuestbooksInteractor` interface in the `interactor` 
package. Replace the interface's contents with the following code: 

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
[`getGuestbooks` method you created earlier in this Learning Path](/develop/learning-paths/mobile/-/knowledge_base/6-2/retrieving-guestbooks#making-the-server-call). 
Despite minor differences, this method does the same thing. 

The `GetGuestbooksInteractorImpl` class finishes with the `onEvent` method. This 
method processes the event that contains the service call's results. Any classes 
that implement the listener, such as the Screenlet class, are then notified of 
the results. 

Nice work! You now have the classes required to call the Guestbook portlet. 
Next, you'll create the Screenlet class. 
