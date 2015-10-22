# Creating the Screenlet Class [](id=creating-the-screenlet-class)

App developers primarily interact with the Screenlet class when using a 
Screenlet in their app. The Screenlet class can contain attributes for 
configuring the Screenlet's behavior, a reference to the Screenlet's View, 
methods for invoking interactor operations, and more. To app developers, the 
Screenlet class is like the driver's seat of a fine luxury automobile. All the 
controls are in just the right place, do exactly what they should, and make you 
feel like royalty on the road. Now it's your turn to craft this for the Get 
Guestbooks Screenlet. Don't worry, we promise you won't get a speeding ticket 
while you're eating your sandwich. And no, we're not done with ham-fisted puns 
yet. 

## Defining Screenlet Attributes [](id=defining-screenlet-attributes)

Before creating the Screenlet class, you should define its attributes. These are 
the `liferay` attributes the app developer can set when inserting the 
Screenlet's XML in an activity or fragment layout. For example, you inserted the 
following Login Screenlet XML in `activity_main.xml` when using Login Screenlet: 

    <com.liferay.mobile.screens.auth.login.LoginScreenlet
        android:id="@+id/login_screenlet"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        liferay:basicAuthMethod="email"
        liferay:layoutId="@layout/login_default"
        />

The app developer can set the `liferay` attributes `basicAuthMethod` and 
`layoutId` to set Login Screenlet's authentication method and View, 
respectively. The Screenlet class then receives these settings so it can enable 
the appropriate functionality. 

When creating a Screenlet, you can define the attributes you want to make 
available to app developers. You'll do this now for the Get Guestbooks 
Screenlet. Create the file `guestbooks_attrs.xml` in your app's `res/values` 
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

- `layoutId`: Sets the View used to display the Screenlet. This functions the 
same as the `layoutId` attribute in Liferay's existing Screenlets.
- `groupId`: Sets the portal site to communicate with, in the event the app 
developer doesn't want to use the `groupId` setting in `server_context.xml`.
- `autoLoad`: A boolean value that sets whether the Screenlet automatically 
retrieves guestbooks from the server, or waits for a user action to do so.

Now that you understand what these attributes do, you're ready to write the 
Screenlet class.

## Creating the Screenlet Class [](id=creating-the-screenlet-class)

Create a new class called `GetGuestbooksScreenlet` inside the 
`getguestbooksscreenlet` package. Change the class declaration to extend 
Screens's `BaseScreenlet`, with `GetGuestbooksViewModel` and 
`GetGuestbooksInteractor` as type parameters. The class should also implement 
`GetGuestbooksListener`. The class's declaration should now look like this: 

    public class GetGuestbooksScreenlet extends BaseScreenlet<GetGuestbooksViewModel, 
        GetGuestbooksInteractor> implements GetGuestbooksListener {...

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

Next, you need to implement the `onGetGuestbooksSuccess`, 
`onGetGuestbooksFailure`, and `onItemClicked` listener methods defined in 
`GetGuestbooksListener`. Each of these methods communicate one or more actions 
that occur in the Screenlet. Add these implementations now: 

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

The `onGetGuestbooksSuccess` implementation notifies the UI and the listener 
when the Screenlet successfully retrieves guestbooks from the Guestbook portlet. 
The method notifies the UI via the `GetGuestbooksViewModel` instance it obtains 
with `getViewModel()`. It then uses the listener's `onGetGuestbooksSuccess` 
method to propagate the list of guestbooks. The `onGetGuestbooksFailure` 
implementation uses the same mechanisms to notify the UI and listener of 
failure, instead propagating the `Exception` generated by a failed server call. 
The `onItemClicked` implementation uses the listener to propagate the Guestbook 
the user selects in the `ListView`. 

You should also provide public getters and setters for the `_listener` and 
`_groupId` variables. Having these for the listener lets classes register as 
listeners and send events. Likewise, app developers can retrieve and change the 
site ID (group ID) as needed. Add these methods as follows:

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

Next, you should implement `BaseScreenlet`'s abstract methods. Implement 
`createScreenletView` first. This method leverages the attributes you defined in 
`guestbooks_attrs.xml`. Add the method now as follows: 

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

In this method, you first obtain a 
[`TypedArray`](http://developer.android.com/reference/android/content/res/TypedArray.html) 
containing the attributes defined in `guestbooks_attrs.xml`. You then use the 
`TypedArray` method `getResourceId` to retrieve the app developer's `layoutId` 
setting. If the app developer hasn't set `layoutId`, `getDefaultLayoutId()` is 
used to return the Screenlet's default `layoutId`. You also use `TypedArray` 
methods to set the `_autoLoad` and `_groupId` attributes. As with 
`getResourceId`, the second argument of the `TypedArray` methods used to set 
these attributes is used to specify a default setting. Note that 
`(int) LiferayServerContext.getGroupId()`, the second argument to `getInt`, 
retrieves the `groupId` set in `server_context.xml`. This `groupId` serves as a 
reasonable default when the app developer hasn't specified one in the 
Screenlet's XML. You finish the `createScreenletView` method by inflating the 
layout specified in `layoutId` and returning its view. 

Now you need to implement the `createInteractor` method of `BaseScreenlet`. This 
is a factory method in which you must use the Screenlet's ID to create the 
Interactor. The `getScreenletId()` method returns this ID. Add 
`createInteractor` as follows:

    @Override
    protected GetGuestbooksInteractor createInteractor(String actionName) {
        return new GetGuestbooksInteractorImpl(getScreenletId());
    }

Next, implement the `onUserAction` method of `BaseScreenlet` This method uses 
the supplied interactor to start the operation to retrieve guestbooks from the 
server. It retrieves guestbooks from the site specified by `_groupId`. Add this 
method now: 

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

You now need to implement `BaseScreenlet`'s final abstract method: 
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
you can return to the `GetGuestbooksView` class to complete the 
`onItemClick` method. Open `GetGuestbooksView` and locate its `onItemClick` 
method. It should contain the following *TODO*: 

    // TODO: Call GetGuestbooksScreenlet's onItemClicked method

Recall that the `onItemClicked` method of `GetGuestbooksScreenlet` notifies the 
listener when a guestbook is selected in the UI. Replace the *TODO* with the 
following code: 

    ((GetGuestbooksScreenlet) getParent()).onItemClicked(_guestbooks.get(position));

Fantastic work! You finished the Get Guestbooks Screenlet! Now you need to 
create the Get Entries Screenlet. This Learning Path's next section shows you 
how to do this.
