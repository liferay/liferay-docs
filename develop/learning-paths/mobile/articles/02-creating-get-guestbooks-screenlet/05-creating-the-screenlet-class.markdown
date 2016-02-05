# Creating the Screenlet Class [](id=creating-the-screenlet-class)

When using a Screenlet, app developers primarily interact with its Screenlet 
class. The Screenlet class contains attributes for configuring the Screenlet's 
behavior, a reference to the Screenlet's View, methods for invoking Interactor 
operations, and more. To app developers, the Screenlet class is like the 
driver's seat of a fine luxury automobile. All the controls are in just the 
right place, do exactly what they should, and make you feel like royalty on the 
road. Now it's your turn to craft this for Get Guestbooks Screenlet. Don't 
worry, we promise you won't get a speeding ticket. 

You'll create the Screenlet class by using the following steps:

1. Define the Screenlet's attributes. These are the XML attributes the app 
   developer can set when inserting the Screenlet's XML. These attributes 
   control aspects of the Screenlet's behavior. 

2. Create the Screenlet class. This class implements the Screenlet's 
   functionality defined in the View, listener, and Interactor. It also reads 
   the attribute values and configures the Screenlet accordingly. 

First, you'll define Get Guestbooks Screenlet's attributes. 

## Defining Screenlet Attributes [](id=defining-screenlet-attributes)

Before creating the Screenlet class, you should define its attributes. These are 
the `liferay` attributes the app developer can set when inserting the 
Screenlet's XML in an activity or fragment layout. For example, you inserted the 
following Login Screenlet XML in `activity_main.xml` when you used Login 
Screenlet: 

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
available to app developers. You'll do this now for Get Guestbooks Screenlet. 
Create the file `guestbooks_attrs.xml` in your app's `res/values` 
directory. Replace the file's contents with the following code: 

    <?xml version="1.0" encoding="utf-8"?>
    <resources>
        <declare-styleable name="GetGuestbooksScreenlet">
            <attr name="layoutId"/>
            <attr name="groupId"/>
            <attr name="autoLoad"/>
        </declare-styleable>
    </resources>

This defines the attributes `layoutId`, `groupId`, and `autoLoad`. You'll add 
functionality to these attributes in the Screenlet class. Here's a brief 
description of what each needs to do: 

- `layoutId`: Sets the View that displays the Screenlet. This functions the same 
as the `layoutId` attribute in Liferay's existing Screenlets. 

- `groupId`: Sets the portal site to communicate with, if the app developer 
doesn't want to use the `groupId` setting in `server_context.xml`. 

- `autoLoad`: A boolean value that sets whether the Screenlet automatically 
retrieves guestbooks from the server, or waits for a specific user action to do 
so. 

Now that you've defined these attributes and know what they need to do, you're 
ready to write the Screenlet class. 

## Creating the Screenlet Class [](id=creating-the-screenlet-class)

Your Screenlet class must extend Screens's 
[`BaseScreenlet`](https://github.com/liferay/liferay-screens/blob/1.3.0/android/library/core/src/main/java/com/liferay/mobile/screens/base/BaseScreenlet.java), 
with your View Model and Interactor classes as type parameters. For the 
Screenlet to notify the app developer of its results, your Screenlet class must 
also implement the listener interface you created in this Learning Path's 
previous article. Now you'll create your Screenlet class. 

Create a new class called `GetGuestbooksScreenlet` inside the 
`getguestbooksscreenlet` package. Change the class declaration to extend 
`BaseScreenlet`, with `GetGuestbooksViewModel` and `GetGuestbooksInteractor` as 
type parameters. The class should also implement `GetGuestbooksListener`. The 
class's declaration should now look like this: 

    public class GetGuestbooksScreenlet extends BaseScreenlet<GetGuestbooksViewModel, 
        GetGuestbooksInteractor> implements GetGuestbooksListener {...

This also requires that you add the following imports:

    import com.liferay.docs.getguestbooksscreenlet.interactor.GetGuestbooksInteractor;
    import com.liferay.docs.getguestbooksscreenlet.view.GetGuestbooksViewModel;
    import com.liferay.mobile.screens.base.BaseScreenlet;

Now you need to add a few variables and constructors the Screenlet requires. One 
variable is for the `GetGuestbooksListener` instance, and the other two are for 
the `groupId` and `autoLoad` attributes. For constructors, you can use the 
superclass constructors. Add the following code now: 

    private GetGuestbooksListener _listener;
    private int _groupId;
    private boolean _autoLoad;

    public GetGuestbooksScreenlet(Context context) {
        super(context);
    }

    public GetGuestbooksScreenlet(Context context, AttributeSet attributes) {
        super(context, attributes);
    }

    public GetGuestbooksScreenlet(Context context, AttributeSet attributes, int defaultStyle) {
        super(context, attributes, defaultStyle);
    }

This requires you to import `android.content.Context` and 
`android.util.AttributeSet`. 

Next, you need to implement the `GetGuestbooksListener` interface. To do this, 
you'll implement the listener's `onGetGuestbooksSuccess`, 
`onGetGuestbooksFailure`, and `onItemClicked` methods. Each communicates an 
action that occurs in the Screenlet. Add their implementations now: 

    public void onGetGuestbooksSuccess(List<GuestbookModel> guestbooks) {
        getViewModel().showFinishOperation(null, guestbooks);

        if (_listener != null) {
            _listener.onGetGuestbooksSuccess(guestbooks);
        }
    }

    public void onGetGuestbooksFailure(Exception e) {
        getViewModel().showFailedOperation(null, e);

        if (_listener != null) {
            _listener.onGetGuestbooksFailure(e);
        }
    }

    @Override
    public void onItemClicked(final GuestbookModel guestbook) {
        if (_listener != null) {
            _listener.onItemClicked(guestbook);
        }
    }

This requires you to import `java.util.List` and 
`com.liferay.docs.model.GuestbookModel`. When the Screenlet successfully 
retrieves guestbooks, the `onGetGuestbooksSuccess` implementation sends them to 
the Screenlet's View and listener. This method first uses `getViewModel()` to 
obtain a `GetGuestbooksViewModel` instance. It then calls the View Model's 
`showFinishOperation` method to send the guestbooks to the View. The 
`onGetGuestbooksSuccess` implementation finishes by using the listener's 
`onGetGuestbooksSuccess` method to send the guestbooks to the listener. The 
`onGetGuestbooksFailure` implementation uses the same mechanisms to notify the 
View and listener of failure, instead propagating the `Exception` generated by a 
failed server call. The `onItemClicked` implementation uses the listener's 
`onItemClicked` method to send the guestbook the user selects in the `ListView` 
to the listener. 

You should also provide public getters and setters for the `_listener` and 
`_groupId` variables. Having these for the listener lets classes, like the 
activity or fragment class an app developer uses the Screenlet in, register as 
listeners and send events. Likewise, a public getter and setter for `_groupId` 
lets app developers retrieve and change the site ID (group ID) as needed. Add 
these methods as follows: 

    public GetGuestbooksListener getListener() {
        return _listener;
    }

    public void setListener(GetGuestbooksListener listener) {
        _listener = listener;
    }

    public int getGroupId() {
        return _groupId;
    }

    public void setGroupId(int groupId) {
        _groupId = groupId;
    }

Next, you must implement `BaseScreenlet`'s abstract methods. Implement 
`createScreenletView` first. This method retrieves any app developer settings 
for the attributes you defined in `guestbooks_attrs.xml`, then inflates and 
returns the view. Add this method now: 

    @Override
    protected View createScreenletView(Context context, AttributeSet attributes) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attributes, 
            R.styleable.GetGuestbooksScreenlet, 0, 0);

        int layoutId = typedArray.getResourceId(R.styleable.GetGuestbooksScreenlet_layoutId, 
            getDefaultLayoutId());
        _autoLoad = typedArray.getBoolean(R.styleable.GetGuestbooksScreenlet_autoLoad, true);

        _groupId = typedArray.getInt(R.styleable.GetGuestbooksScreenlet_groupId,
            (int) LiferayServerContext.getGroupId());

        LiferayLogger.i("The Group ID is: " + _groupId);

        View view = LayoutInflater.from(context).inflate(layoutId, null);

        typedArray.recycle();

        return view;
    }

This method requires you to add the following imports:

    import android.view.LayoutInflater;
    import android.view.View;
    import android.content.res.TypedArray;
    import com.liferay.docs.liferayguestbook.R;
    import com.liferay.mobile.screens.context.LiferayServerContext;
    import com.liferay.mobile.screens.util.LiferayLogger;

In this method, you first obtain a 
[`TypedArray`](http://developer.android.com/reference/android/content/res/TypedArray.html) 
containing any app developer settings for the attributes defined in 
`guestbooks_attrs.xml`. You then use the `TypedArray` method `getResourceId` to 
retrieve the app developer's `layoutId` setting. If the app developer hasn't set 
`layoutId`, `getDefaultLayoutId()` is used as `getResourceId`'s second argument 
to return the Screenlet's default `layoutId`. You also use the `TypedArray` 
methods `getBoolean` and `getInt` to set the `_autoLoad` and `_groupId` 
attribute variables, respectively. As with `getResourceId`, the second argument 
to these `TypedArray` methods specifies a default setting. Note that 
`(int) LiferayServerContext.getGroupId()`, the second argument to `getInt`, 
retrieves the `groupId` set in `server_context.xml`. This `groupId` serves as a 
reasonable default when the app developer hasn't specified the `groupId` in the 
Screenlet's XML. You finish the `createScreenletView` method by inflating the 
layout specified in `layoutId` and returning its view. 

Now you must implement `BaseScreenlet`'s `createInteractor` method. This factory 
method creates the Interactor with the Screenlet's ID. The `getScreenletId()` 
method returns this ID. Add the following `createInteractor` method:

    @Override
    protected GetGuestbooksInteractor createInteractor(String actionName) {
        return new GetGuestbooksInteractorImpl(getScreenletId());
    }

This requires you to import 
`com.liferay.docs.getguestbooksscreenlet.interactor.GetGuestbooksInteractorImpl`.

Next, implement `BaseScreenlet`'s `onUserAction` method. This method uses the 
Interactor's `getGuestbooks` method to retrieve guestbooks from the server. It 
retrieves guestbooks from the site specified by `_groupId`. Add this 
`onUserAction` method now: 

    @Override
    protected void onUserAction(String userActionName, 
        GetGuestbooksInteractor interactor, Object... args) {

        try {
            interactor.getGuestbooks(_groupId);
        }
        catch (Exception e) {
            onGetGuestbooksFailure(e);
        }
    }

You must now implement `BaseScreenlet`'s final abstract method: 
`onScreenletAttached`. When `_autoLoad` is `true`, this method uses 
`performUserAction()` to start the action to retrieve guestbooks from the 
server. Add `onScreenletAttached` now: 

    @Override
    protected void onScreenletAttached() {
        if (_autoLoad) {
            performUserAction();
        }
    }

Since there's no other UI button or gesture the user can take to load the 
guestbooks, it's important that you load them automatically in 
`onScreenletAttached()`. So why bother with the settable `_autoLoad` attribute 
at all? Why not simply call `performUserAction()` no matter what? Using 
`_autoLoad` provides the framework for adding additional UI controls later. 
Letting developers customize a Screenlet's UI is one of Screens's key strengths. 

Awesome! You finished the `GetGuestbooksScreenlet` class. Now that it exists, 
you can return to the `GetGuestbooksView` class to complete the `onItemClick` 
method. Open `GetGuestbooksView` and locate its `onItemClick` method. It 
contains the following *TODO*: 

    // TODO: Call GetGuestbooksScreenlet's onItemClicked method

Recall that the `onItemClicked` method in `GetGuestbooksScreenlet` sends the 
guestbook selected in the UI to the listener. Replace the *TODO* with the 
following code: 

    ((GetGuestbooksScreenlet) getParent()).onItemClicked(_guestbooks.get(position));

You must also import 
`com.liferay.docs.getguestbooksscreenlet.GetGuestbooksScreenlet` in 
`GetGuestbooksView`. 

This code uses `_guestbooks.get(position)` to retrieve the guestbook at the 
selected position. The `onItemClicked` call then passes this guestbook to the 
listener via the `GetGuestbooksScreenlet` instance retrieved with `getParent()`. 
The `onItemClicked` method should now look like this:

    @Override
    public void onItemClick(final AdapterView<?> parent, final View view, final int position, 
        final long id) {
        setItemChecked(position, true);

        ((GetGuestbooksScreenlet) getParent()).onItemClicked(_guestbooks.get(position));
    }

Fantastic work! You finished Get Guestbooks Screenlet! It only gets and displays 
guestbooks, though. To have an app that also gets and displays entries, you need 
to create Get Entries Screenlet. This Learning Path's next section shows you how 
to do this. 
