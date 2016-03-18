# Creating Get Guestbooks Screenlet's Server Call [](id=creating-get-guestbook-screenlets-server-call)

In the previous article, you learned that Screenlets use a Mobile SDK to make 
server calls via Interactors. Since you must call the Guestbook portlet's remote 
services, you'll use the Guestbook Mobile SDK in the Get Guestbook Screenlet's 
Interactor. An Interactor consists of an event, a callback, the Interactor 
interface and its implementation, and a listener. This article shows you how to 
create each. 

You'll create the Interactor by using the following steps:

1. Create the event class. This class lets you handle communication between the 
   Screenlet's components via event objects that contain the server call's 
   results. 

2. Create the callback class. Because Android doesn't allow network requests on 
   its main UI thread, the callback class is required to route the server call 
   asynchronously through a background thread. The callback class also creates 
   model objects from the JSON returned by the server, and creates event objects 
   that contain the results. 

3. Create the listener. This interface defines the methods the app developer 
   needs to respond to the Screenlet's behavior. 

4. Create and implement the Interactor interface. This defines and implements 
   the method that makes the server call. The implementation must also process 
   the event object that contains the call's results, and then notify the 
   listener of those results. 

First, you'll create the event. 

## Creating the Event Class [](id=creating-the-event-class)

Screens uses the 
[EventBus](https://greenrobot.github.io/EventBus/) 
library to handle communication within Screenlets. Your Screenlet's components 
must therefore use event classes to communicate with each other. Screens comes 
with 
[`JSONObjectEvent`](https://github.com/liferay/liferay-screens/blob/1.3.0/android/library/core/src/main/java/com/liferay/mobile/screens/base/interactor/JSONObjectEvent.java) 
and 
[`JSONArrayEvent`](https://github.com/liferay/liferay-screens/blob/1.3.0/android/library/core/src/main/java/com/liferay/mobile/screens/base/interactor/JSONArrayEvent.java) 
for communicating `JSONObject` and `JSONArray` results within Screenlets, 
respectively. These classes extend the abstract class 
[`BasicEvent`](https://github.com/liferay/liferay-screens/blob/1.3.0/android/library/core/src/main/java/com/liferay/mobile/screens/base/interactor/BasicEvent.java).
Likewise, you can create your own event classes by extending `BasicEvent`. You 
should create your own event classes when you must communicate objects other 
than `JSONObject` or `JSONArray`. Because Get Guestbooks Screenlet must 
communicate `GuestbookModel` objects, you must create an event class capable of 
doing so. 

In Android Studio, create a new package called `event` inside the 
`getguestbooksscreenlet` package. Inside the `event` package, create a new class 
called `GetGuestbooksEvent`. Replace the class's code with the following: 

    package com.liferay.docs.getguestbooksscreenlet.event;

    import com.liferay.docs.model.GuestbookModel;
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

The first argument to both constructors is `targetScreenletId`. This makes sure 
events are aimed at the correct Screenlet. Each constructor's second argument 
indicates the constructor's purpose. The Screenlet creates the event object with 
`GetGuestbooksEvent(int targetScreenletId, Exception e)` when the server call 
fails, and 
`GetGuestbooksEvent(int targetScreenletId, List<GuestbookModel> guestbooks)` 
when the server call succeeds. In either case, the resulting 
`GetGuestbooksEvent` object communicates success or failure when used elsewhere 
in the Screenlet. The event class's `getGuestbooks` method lets you retrieve any 
guestbooks from an event object. 

Next, you'll create a callback class that uses this event class. 

## Creating the Callback Class [](id=creating-the-callback-class)

Recall that you must use a callback class to route server calls asynchronously 
through a background thread. This is required because Android doesn't allow 
network requests on its main UI thread. At this point, you might be saying, "Oh 
no, threading in mobile apps? That sounds complicated!" Fear not! Screens's 
`InteractorAsyncTaskCallback` class abstracts away this complexity. Your 
callback class should extend this class. The callback class must also process 
the call's results. Since the server returns the guestbooks as JSON, the 
callback class must transform them into `GuestbookModel` objects. The callback 
class then communicates the server call's results, either a `GuestbookModel` 
list or an exception, by creating a `GetGuestbooksEvent` object with these 
results. You'll create this callback class now. 

First, create a new package called `interactor` in the `getguestbooksscreenlet` 
package. Then create a new class called `GetGuestbooksCallback` inside the 
`interactor` package. Replace its code with the following: 

    package com.liferay.docs.getguestbooksscreenlet.interactor;

    import com.liferay.docs.model.GuestbookModel;
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

This class extends Screens's `InteractorAsyncTaskCallback` class, letting you 
focus on the call's results instead of threading. Following the simple 
constructor, there are two `createEvent` methods for creating the 
`GetGuestbookEvent` objects you defined in the event class. The callback class 
finishes with the `transform` method. The server returns the guestbooks in a 
`JSONArray` of `JSONObject`, where each `JSONObject` contains one guestbook's 
information. The transform method creates a `GuestbookModel` object from each 
`JSONObject`, and then adds it to an `ArrayList`. 

Well done! Now that you have the event and callback classes, you must create the 
Screenlet's listener interface. 

## Creating the Listener [](id=creating-the-listener)

The listener interface defines the methods needed to control or respond to the 
Screenlet's behavior. The Screenlet class (since it controls the Screenlet's 
behavior), and the activity or fragment the app developer uses the Screenlet in, 
must implement the listener interface. You saw an example of how this works when 
you used Login Screenlet. You implemented its listener interface 
(`LoginListener`) in `MainActivity`. This provided you with the listener methods 
`onLoginSuccess` and `onLoginFailure`, where you responded to login success or 
failure, respectively. You must create a similar listener interface for Get 
Guestbooks Screenlet. In addition to responding to success and failure, you must 
also respond to list item selections. 

Create the `GetGuestbooksListener` interface in the `getguestbooksscreenlet` 
package. Replace the interface's contents with the following code: 

    package com.liferay.docs.getguestbooksscreenlet;

    import com.liferay.docs.model.GuestbookModel;
    import java.util.List;

    public interface GetGuestbooksListener {

        void onGetGuestbooksFailure(Exception e);

        void onGetGuestbooksSuccess(List<GuestbookModel> guestbooks);

        void onItemClicked(GuestbookModel guestbook);
    }

The methods `onGetGuestbooksSuccess` and `onGetGuestbooksFailure` let the 
implementing class respond to the Screenlet's success or failure to retrieve 
Guestbooks, respectively. The `onItemClicked` method lets the implementing class 
respond when the user clicks a guestbook in the `ListView`. Next, you'll use 
this listener when creating and implementing the Interactor interface. 

## Creating and Implementing the Interactor Interface [](id=creating-and-implementing-the-interactor-interface)

The Interactor interface's implementation makes the server call and sends the 
results to the listener via an event object. The Interactor interface defines 
only a single method: the method that makes the server call. When you implement 
this interface, you'll also create an additional method that sends the event 
object's results to the listener. Since the event object contains the server 
call's results, this is how any classes that implement the listener receive 
those results. 

First, create the `GetGuestbooksInteractor` interface in the `interactor` 
package. Replace the interface's contents with the following code: 

    package com.liferay.docs.getguestbooksscreenlet.interactor;

    import com.liferay.docs.getguestbooksscreenlet.GetGuestbooksListener;
    import com.liferay.mobile.screens.base.interactor.Interactor;

    public interface GetGuestbooksInteractor extends Interactor<GetGuestbooksListener> {

        void getGuestbooks(long groupId);
    }

By extending `Interactor<GetGuestbooksListener>`, any classes that implement 
`GetGuestbooksInteractor` can use the listener to send the Interactor's results 
to any classes that implement the listener. The `GetGuestbooksInteractor` 
interface defines a single method: `getGuestbooks`. This method takes a site ID 
(`groupId`) as an argument so it can retrieve that site's guestbooks. 

Now you'll implement this Interactor interface. In addition to implementing the 
`getGuestbooks` method, you'll create an `onEvent` method that retrieves the 
server call's results from an event object and sends them to the listener. To 
make the server call in the `getGuestbooks` implementation, recall from the 
previous article that you must use an instance of the Guestbook Mobile SDK 
service that contains the service method you want to call. To get the 
guestbooks, you'll create a `GuestbookService` instance and then call its 
`getGuestbooks` method. You must create this service instance by using an 
authenticated session that has a callback set to it. 

Create the `GetGuestbooksInteractorImpl` class in the `interactor` package. 
Replace this class's contents with the following code: 

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

    }

In addition to implementing `GetGuestbooksInteractor`, this class extends 
Screens's `BaseRemoteInteractor` class with `GetGuestbooksListener` as a type 
parameter. This lets you use `getListener()` to retrieve the listener when 
processing the event in the `onEvent` method. The `onEvent` method first uses an 
`if` statement with `!isValidEvent(event)` to check for the presence of a 
listener. If a listener exists, `onEvent` proceeds to the next `if` statement. 
This `if` statement uses `event.isFailed()` to check the server call's results 
in the event. If the server call failed, then `onGetGuestbooksFailure` passes 
the exception, retrieved by `getException()`, to the listener. If the server 
call succeeded, then `onGetGuestbooksSuccess` passes the `GuestbookModel` list, 
retrieved by `getGuestbooks()`, to the listener. This lets any class that 
implements the listener interface, like the Screenlet class or the class the app 
developer uses the Screenlet in, receive the server call's results. 

As mentioned before, the `getGuestbooks` method in the Interactor interface's 
implementation retrieves a site's guestbooks. Since the Mobile SDK requires an 
authenticated session to communicate with the portal, you first use 
`SessionContext.createSessionFromCurrentSession()` to create a session from the 
pre-existing session in the app. You might now be thinking, "Whoa there! I 
haven't created a session yet!" But actually, you have; you just didn't know it. 
Successful authentication with Login Screenlet creates a session that you can 
access with Screens's `SessionContext` class. Because you're counting on that 
session, however, you *must* use Login Screenlet. 

You then create a new `GetGuestbooksCallback` instance and set it as the 
session's callback. This ensures that any service calls made with the session go 
through the callback. Next, you use the session to create a `GuestbookService` 
instance. In the `try` block, you then call the service's `getGuestbooks` method 
with the group ID (site ID) of the site to retrieve guestbooks from. You get the 
group ID from the `LiferayServerContext` class--another Screens class--that lets 
you retrieve the values you set in `server_context.xml`. 

Nice work! You now have the Interactor required to get guestbooks from the 
Guestbook portlet. Next, you'll create the Screenlet class. 
