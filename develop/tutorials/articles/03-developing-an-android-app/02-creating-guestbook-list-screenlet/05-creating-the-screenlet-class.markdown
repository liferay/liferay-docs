# Creating the Screenlet Class

When using a Screenlet, app developers primarily interact with its Screenlet 
class. The Screenlet class contains attributes for configuring the Screenlet's 
behavior, a reference to the Screenlet's View, methods for invoking Interactor 
operations, and more. To app developers, the Screenlet class is like the 
driver's seat of a fine luxury automobile. All the controls are in just the 
right place, do exactly what they should, and make you feel like royalty on the 
road. Now it's your turn to craft this for Guestbook List Screenlet. Don't 
worry, we promise you won't get a speeding ticket. 

You'll use these steps to create the Screenlet class: 

1. Define the Screenlet's attributes. These are the XML attributes the app 
   developer can set when inserting the Screenlet's XML. These attributes 
   control aspects of the Screenlet's behavior. 

2. Create the Screenlet class. This class implements the Screenlet's 
   functionality defined in the View, listener, and Interactor. It also reads 
   the attribute values and configures the Screenlet accordingly. 

First, you'll define Guestbook List Screenlet's attributes. 

## Defining Screenlet Attributes

Before creating the Screenlet class, you should define its attributes. These are 
the `liferay` attributes the app developer can set when inserting the 
Screenlet's XML in a layout. For example, you inserted the following Login 
Screenlet XML in `activity_main.xml` when you used Login Screenlet: 

    <com.liferay.mobile.screens.auth.login.LoginScreenlet
        android:id="@+id/login_screenlet"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        liferay:basicAuthMethod="email"
        liferay:layoutId="@layout/login_default"
        />

The app developer can set the `liferay` attributes `basicAuthMethod` and 
`layoutId` to set Login Screenlet's authentication method and View, 
respectively. The Screenlet class reads these settings to enable the appropriate 
functionality. 

When creating a Screenlet, you can define the attributes you want to make 
available to app developers. You'll do this now for Guestbook List Screenlet. 
Create the file `guestbook_attrs.xml` in your app's `res/values` directory. 
Replace the file's contents with the following code: 

    <?xml version="1.0" encoding="utf-8"?>
    <resources>
        <declare-styleable name="GuesbookListScreenlet">
            <attr name="groupId"/>
            <attr name="offlinePolicy"/>
            <attr name="layoutId"/>
        </declare-styleable>
    </resources>

This defines the attributes `groupId`, `offlinePolicy`, and `layoutId`. You'll 
add these attributes' functionality in the Screenlet class. Here's a brief 
description of what each will do: 

- `groupId`: Sets the portal site to communicate with, if the app developer 
  doesn't want to use the `groupId` setting in `server_context.xml`. 

- `offlinePolicy`: Sets the Screenlet's 
  [offline mode policy](/develop/tutorials/-/knowledge_base/7-0/architecture-of-offline-mode-in-liferay-screens#using-policies-with-offline-mode). 

- `layoutId`: Sets the Screenlet's View. This functions the same as the 
  `layoutId` attribute in Liferay's existing Screenlets. 

Now that you've defined these attributes and know what they need to do, you're 
ready to write the Screenlet class. 

## Creating the Screenlet Class

As you've seen, the list Screenlet framework provides basic implementations of 
many list Screenlet components. This is also true of the Screenlet class. The 
list Screenlet framework's 
[`BaseListScreenlet` class](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListScreenlet.java) 
provides much of your Screenlet class's code. This includes methods for 
pagination and other default behavior. Before extending this class to meet 
Guestbook List Screenlet's needs, you should understand how `BaseListScreenlet` 
handles server call results for you. Buckle up: you're about to go on another 
magical journey through the list Screenlet framework. 

### BaseListScreenlet and the Server Call's Results

<!-- Insert diagram in support of this section -->
Note that `BaseListScreenlet` implements the 
[`BaseListInteractorListener` interface](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/BaseListInteractorListener.java). 
by implementing the 
[`onListRowsFailure` and `onListRowsReceived` methods](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListScreenlet.java#L57-L73). 
Recall that the 
[`BaseListInteractor` class's](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/BaseListInteractor.java) 
`onEventMainThread` and `notifyError` methods call these methods to send the 
server call's results to any classes that implement 
`BaseListInteractorListener`. Therefore, by implementing 
`BaseListInteractorListener`, `BaseListScreenlet` receives the server call's 
results and serves as this listener. This happens in 
[its `onListRowsFailure` and `onListRowsReceived` implementations](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListScreenlet.java#L57-L73). 

These method implementations then send the server call's results to the View and 
the activity or fragment class that contains the Screenlet. They do this via 
`BaseListViewModel` and `BaseListListener`, respectively. Recall that a View 
Model updates the View with the server call's results. By sending the results to 
the View Model, the Screenlet's UI can display them. Also recall that 
`BaseListListener` sends the server call's results to the activity or fragment 
class that contains the Screenlet. This lets the app developer respond to the 
results. Here's how the `onListRowsFailure` and `onListRowsReceived` 
implementations in `BaseListScreenlet` work: 

- `onListRowsFailure`: Sends the results of a failed server call (an 
  `Exception`). The `BaseListViewModel` method 
  `showFinishOperation(int startRow, int endRow, Exception e)` sends the 
  `Exception` to the View. The `BaseListListener` method `onListPageFailed` 
  sends the same `Exception` to the activity or fragment class that contains the 
  Screenlet. 

- `onListRowsReceived`: Sends the results of a successful server call (the list 
  of objects retrieved from the server). The `BaseListViewModel` method 
  `showFinishOperation(int startRow, int endRow, List<E> entries, int rowCount)` 
  sends the objects to the View. The `BaseListListener` method 
  `onListPageReceived` sends the the same objects to the activity or fragment 
  class that contains the Screenlet. 

The best part about all this is that you can rely on these default 
implementations in `BaseListScreenlet` to handle the server call's results. All 
you have to do is extend `BaseListScreenlet` when creating your Screenlet class. 
You'll do this next. 

### Extending BaseListScreenlet

Your Screenlet class must extend `BaseListScreenlet` with your model and 
Interactor classes as type arguments, and implement any functionality specific 
to your Screenlet. 

In the `guestbooklistscreenlet` package, create a new class called 
`GuestbookListScreenlet`. This class must extend `BaseListScreenlet` with 
`GuestbookModel` and `GuestbookListInteractorImpl` as type arguments. Your class 
declaration should look like this: 

    public class GuestbookListScreenlet extends 
        BaseListScreenlet<GuestbookModel, GuestbookListInteractorImpl> {...

Next, you must add an instance variable for each Screenlet attribute you created 
in the preceding section, besides `layoutId` (you'll learn why in a moment). Add 
these variables now: 

    private long _groupId;
    private OfflinePolicy _offlinePolicy;

Now add your Screenlet class's constructors. You don't need anything special 
here: just leverage the superclass constructors: 

    public GuestbookListScreenlet(Context context) {
        super(context);
    }

    public GuestbookListScreenlet(Context context, AttributeSet attributes) {
        super(context, attributes);
    }

    public GuestbookListScreenlet(Context context, AttributeSet attributes, int defaultStyle) {
        super(context, attributes, defaultStyle);
    }

Next, add the methods that support 
[offline mode](/develop/tutorials/-/knowledge_base/7-0/architecture-of-offline-mode-in-liferay-screens) 
in your Screenlet. As with those in your Interactor class, these are boilerplate 
implementations. See the 
[list Screenlet tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-android-list-screenlets) 
for details on how they work: 

    @Override
    public void loadingFromCache(boolean success) {
        if (getListener() != null) {
            getListener().loadingFromCache(success);
        }
    }

    @Override
    public void retrievingOnline(boolean triedInCache, Exception e) {
        if (getListener() != null) {
            getListener().retrievingOnline(triedInCache, e);
        }
    }

    @Override
    public void storingToCache(Object object) {
        if (getListener() != null) {
            getListener().storingToCache(object);
        }
    }

Now you must override the method that reads the attribute settings and inflates 
the View: the aptly named `createScreenletView`. This method 
[is an abstract method of Screens's `BaseScreenlet` class](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/BaseScreenlet.java#L232), 
and takes a 
[`Context` object](https://developer.android.com/reference/android/content/Context.html) 
and an 
[`AttributeSet` object](https://developer.android.com/reference/android/util/AttributeSet.html) 
as arguments. 
Although 
[`BaseListScreenlet` implements `createScreenletView`](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListScreenlet.java#L152-L174), 
you must provide an implementation suitable for your Screenlet. For example, 
your `createScreenletView` implementation must account for the Guestbook List 
Screenlet's `groupId` and `offlinePolicy` attributes. Add this method to your 
`GuestbookListScreenlet` class now: 

    @Override
    protected View createScreenletView(Context context, AttributeSet attributes) {

    }

In this method, you'll use an 
[Android `TypedArray`](http://developer.android.com/reference/android/content/res/TypedArray.html) 
to retrieve any settings the app developer makes for the attributes you defined 
in `guestbook_attrs.xml`. Add the following to your `createScreenletView` 
method: 

    TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attributes, R.styleable.GuesbookListScreenlet, 0, 0);

First, you'll retrieve the app developer's `offlinePolicy` setting from the 
`TypedArray`, and use it to set the Screenlet class's `_offlinePolicy` variable. 
You'll retrieve the app developer's setting with the `TypedArray` method 
`getInteger`. Add the following to your `createScreenletView` method: 

    Integer offlinePolicy = typedArray.getInteger(
                R.styleable.GuesbookListScreenlet_offlinePolicy,
                OfflinePolicy.REMOTE_ONLY.ordinal());

With its first argument--`R.styleable.GuesbookListScreenlet_offlinePolicy`--the 
`getInteger` method retrieves the app developer's `offlinePolicy` setting. If 
the app developer hasn't set this attribute, `getInteger` defaults to the remote 
only policy setting via its second argument: 
`OfflinePolicy.REMOTE_ONLY.ordinal()`. Regardless of where the setting 
originates, the results are set to the local variable `offlinePolicy`. You must 
then use this variable to set the Screenlet class's `_offlinePolicy` variable. 
Do this now by adding the following code to your `createScreenletView` method: 

    _offlinePolicy = OfflinePolicy.values()[offlinePolicy];

[Screens's `OfflinePolicy`](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/cache/OfflinePolicy.java) 
is an enumeration that defines the four possible offline mode settings: 
`REMOTE_ONLY`, `CACHE_ONLY`, `CACHE_FIRST`, and `REMOTE_FIRST`. The `Integer` 
set in the local `offlinePolicy` variable is used to select the corresponding 
value from the enumeration, which is then set to the the Screenlet class's 
`_offlinePolicy` variable. 

Now you must retrieve the app developer's setting for the `groupId` attribute. 
You'll do this with the `TypedArray` method `getInt`, much as you used the 
`TypedArray` method `getInteger` to retrieve the app developer's `offlinePolicy` 
setting. Add the following code to your `createScreenletView` method: 

    _groupId = typedArray.getInt(R.styleable.GuesbookListScreenlet_groupId,
                (int) LiferayServerContext.getGroupId());

The first argument to `getInt` specifies the app developer's `groupId` setting, 
while the second argument specifies the `groupId` setting from the app's 
`server_context.xml`. The latter is provided as a default in case the app 
developer doesn't set `groupId`. The `getInt` method's results are set to the 
Screenlet class's `_groupId` variable.

Since you're now done with the `TypedArray` you created, you must recycle it. 
This is a best practice when working with `TypedArray` in Android. See 
[Android's `TypedArray` documentation](https://developer.android.com/reference/android/content/res/TypedArray.html) 
for more information. To recycle the `TypedArray`, add the following to your 
`createScreenletView` method: 

    typedArray.recycle();

Lastly, your `createScreenletView` method must inflate your Screenlet's layout 
and return its View. You'll do this by calling 
[`BaseListScreenlet`'s `createScreenletView` method](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListScreenlet.java#L152-L174). 
Note this method also uses a `TypedArray` to retrieve and set the Screenlet's 
attributes, including the `layoutId`. This is why your `createScreenletView` 
implementation doesn't need support for `layoutId`: calling 
`BaseListScreenlet`'s implementation supports it for you. Calling the 
`BaseListScreenlet` implementation also gets you other attributes for 
controlling pagination (`firstPageSize` and `pageSize`), loading the Screenlet 
automatically (`autoLoad`), and more. The method finishes by inflating and 
returning the View that matches the `layoutId`. Call this method now by adding 
the following code to your `createScreenletView` method: 

    return super.createScreenletView(context, attributes);

Nice job! You finished your `createScreenletView` method. Check to make sure it 
looks like this: 

    @Override
    protected View createScreenletView(Context context, AttributeSet attributes) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attributes, R.styleable.GuesbookListScreenlet, 0, 0);
        Integer offlinePolicy = typedArray.getInteger(
                R.styleable.GuesbookListScreenlet_offlinePolicy,
                OfflinePolicy.REMOTE_ONLY.ordinal());
        _offlinePolicy = OfflinePolicy.values()[offlinePolicy];
        _groupId = typedArray.getInt(R.styleable.GuesbookListScreenlet_groupId,
                (int) LiferayServerContext.getGroupId());
        typedArray.recycle();

        return super.createScreenletView(context, attributes);
    }

Next, to start the Interactor operation that retrieves the list rows for display 
in the Screenlet, you must implement 
[`BaseListScreenlet`'s abstract `loadRows` method](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListScreenlet.java#L149-L150). 
This method must call your Interactor class's `loadRows` method. Add the 
following `loadRows` method to your Screenlet class: 

    @Override
    protected void loadRows(GuestbookListInteractorImpl interactor, int startRow,
                            int endRow, Locale locale) throws Exception {

        interactor.loadRows(startRow, endRow, locale, _groupId);
    }

Note that the `interactor.loadRows` call includes the `_groupId`. This ensures 
that the Interactor has the correct group ID (site ID) to retrieve guestbooks 
from. 

Lastly, your Screenlet class needs a method to create an instance of your 
Interactor class. Add this method now: 

    @Override
    protected GuestbookListInteractorImpl createInteractor(String actionName) {
        return new GuestbookListInteractorImpl(getScreenletId(), _offlinePolicy);
    }

Awesome! Your Screenlet class is finished. Your Screenlet is finished, too! 
Before using Guestbook List Screenlet, however, you'll create Entry List 
Screenlet to show a list of each guestbook's entries. After all, viewing 
guestbooks without their entries doesn't make much sense. It isn't very exciting 
either. What's really exciting is that you can create Entry List Screenlet with 
the same set of steps you used to create Guestbook List Screenlet. The next 
series of articles in this Learning Path shows you this. 
