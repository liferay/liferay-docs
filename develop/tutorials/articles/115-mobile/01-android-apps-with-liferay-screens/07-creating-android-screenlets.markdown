# Creating Android Screenlets [](id=creating-android-screenlets)

The
[Screenlets](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-android)
that come with Liferay Screens cover common use cases for mobile apps that use
Liferay. They authenticate users, interact with Dynamic Data Lists, view assets,
and more. However, what if there's no Screenlet for *your* specific use case? No
sweat! You can create your own. Extensibility is a key strength of Liferay
Screens. 

This tutorial explains how to create your own Screenlets. As an example, it
references code from the sample
[Add Bookmark Screenlet](https://github.com/liferay/liferay-screens/tree/master/android/samples/addbookmarkscreenlet), 
that saves bookmarks to Liferay's Bookmarks portlet. 

In general, you use the following steps to create Screenlets: 

1. Determine your Screenlet's location. Where you create your Screenlet depends 
   on how you'll use it. 

2. Create the Screenlet's UI (its View). Although this tutorial presents all 
   the information you need to create a View for your Screenlet, you may first 
   want to learn how to 
   [create a View](/develop/tutorials/-/knowledge_base/7-0/creating-android-views). 
   For more information on Views in general, see 
   [the tutorial on using Views with Screenlets](/develop/tutorials/-/knowledge_base/7-0/using-views-in-android-screenlets). 

3. Create the Screenlet's Interactor. Interactors are Screenlet components that 
   make server calls. 

4. Define the Screenlet’s attributes. These are the XML attributes the app 
   developer can set when inserting the Screenlet’s XML. These attributes 
   control aspects of the Screenlet’s behavior. You'll add functionality to 
   these attributes in the Screenlet class. 

5. Create the Screenlet class. The Screenlet class is the Screenlet's central 
   component. It controls the Screenlet's behavior and is the component the app 
   developer interacts with when inserting a Screenlet. 

To understand the components that make up a Screenlet, you should first 
learn the
[architecture of Liferay Screens for Android](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-android). 

Without further ado, let the Screenlet creation begin! 

## Determining Your Screenlet's Location [](id=determining-your-screenlets-location)

Where you should create your Screenlet depends on how you plan to use it. If you
don't plan to reuse your Screenlet in another app or don't want to redistribute
it, create it in a new package inside your Android app project. This lets you
reference and access Liferay Screens's core, in addition to all the View Sets 
you may have imported. 

If you want to reuse your Screenlet in another app, create it in a new Android 
application module. The tutorial 
[Packaging Android Screenlets](/develop/tutorials/-/knowledge_base/7-0/packaging-your-android-screenlets) 
explains how to do this. When your Screenlet's project is in place, you can 
start by creating the Screenlet's UI. 

## Creating the Screenlet's UI [](id=creating-the-screenlets-ui)

In Liferay Screens for Android, Screenlet UIs are called Views. Every Screenlet 
must have at least one View. A View consists of the following components: 

- The View Model interface: defines the methods the View needs to update the UI. 

- A layout XML file: defines the UI components that the View presents to the end 
  user.

- A View class: renders the UI, handles user interactions, and communicates with 
  the Screenlet class. The View class implements the View Model interface. 

- The Screenlet class: Although technically part of a View, the Screenlet class 
  depends on all the other Screenlet components. You therefore won't create the 
  Screenlet class until the end of this tutorial. 

The first items to create for a Screenlet's View are its View Model interface 
and layout. The following steps explain how: 

1.  To define the methods that every Screenlet's View class must implement, 
    Screens provides the 
    [`BaseViewModel` interface](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/view/BaseViewModel.java). 
    Your View Model interface should extend `BaseViewModel` to define any 
    additional methods needed by your Screenlet. This includes any getters and 
    setters for the attributes you want to use. 

    For example, Add Bookmark Screenlet needs attributes for each bookmark's URL 
    and title. Its View Model interface, `AddBookmarkViewModel`, therefore, 
    defines getters and setters for these attributes: 

        public interface AddBookmarkViewModel extends BaseViewModel {
            String getURL();

            void setURL(String value);

            String getTitle();

            void setTitle(String value);
        }

2.  Define your Screenlet's UI by writing a standard Android layout XML file. 
    The layout's root element should be the fully qualified class name of your 
    Screenlet's View class. You'll create that class in the next step, but 
    determine its name now and name the layout's root element after it. Finally, 
    add any UI elements your View needs. 

    For example, Add Bookmark Screenlet's layout needs two text fields: one for 
    entering a bookmark's URL and one for entering its title. The layout also 
    needs a button for saving the bookmark. The Screenlet defines this UI in its 
    [`bookmark_default.xml` layout file](https://github.com/liferay/liferay-screens/blob/master/android/samples/addbookmarkscreenlet/src/main/res/layout/bookmark_default.xml): 

        <?xml version="1.0" encoding="utf-8"?>
        <com.your.package.AddBookmarkView 
            xmlns:android="http://schemas.android.com/apk/res/android"
            style="@style/default_screenlet">

            <EditText
                android:id="@+id/url"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginBottom="15dp"
                android:hint="URL Address"
                android:inputType="textUri"/>

            <EditText
                android:id="@+id/title"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginBottom="15dp"
                android:hint="Title"/>

            <Button
                android:id="@+id/add_button"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="Add Bookmark"/>

        </com.your.package.AddBookmarkView>

Next, you'll create your Screenlet's View class. 

### Creating the Screenlet's View Class [](id=creating-the-screenlets-view-class)

Your Screenlet needs a View class to support the layout you just created. This 
class must extend an Android layout class (e.g. `LinearLayout`, `ListView`), 
implement your View Model interface, and implement a separate listener interface 
to handle user actions. Follow these steps to create this View class: 

1.  Create a View class that extends the Android layout class appropriate for 
    your Screenlet's UI. For example, Add Bookmark Screenlet renders its UI 
    components in a single column, so 
    [its View class (`AddBookmarkView`)](https://github.com/liferay/liferay-screens/blob/master/android/samples/addbookmarkscreenlet/src/main/java/com/liferay/mobile/screens/bookmark/view/AddBookmarkView.java) 
    extends 
    [Android's `LinearLayout`](https://developer.android.com/reference/android/widget/LinearLayout.html). 
    Your View class's constructors should call the parent layout class's 
    constructors. For example, 
    [`AddBookmarkView`'s constructors](https://github.com/liferay/liferay-screens/blob/master/android/samples/addbookmarkscreenlet/src/main/java/com/liferay/mobile/screens/bookmark/view/AddBookmarkView.java#L20-L30) 
    call those of `LinearLayout`: 

        public AddBookmarkView(Context context) {
            super(context);
        }

        public AddBookmarkView(Context context, AttributeSet attributes) {
            super(context, attributes);
        }

        public AddBookmarkView(Context context, AttributeSet attributes, int defaultStyle) {
            super(context, attributes, defaultStyle);
        }

2.  Add instance variables for your View Model's attributes and `BaseScreenlet`. 
    For example, Add Bookmark Screenlet needs instance variables for a 
    bookmark's URL and title. Because all Screenlet classes extend the 
    [`BaseScreenlet` class](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/BaseScreenlet.java), 
    a `BaseScreenlet` variable in your View class ensures that your View always 
    has a reference to the Screenlet. For example, here are `AddBookmarkView`'s 
    instance variables: 

        private EditText _urlText;
        private EditText _titleText;
        private BaseScreenlet _screenlet;

3.  Implement your View Model interface. Implement your View Model's getter and 
    setter methods to get and set the inner value of each component, 
    respectively. For example, here's `AddBookmarkView`'s implementation of 
    `AddBookmarkViewModel`: 

        public String getURL() {
            return _urlText.getText().toString();
        }

        public void setURL(String value) {
            _urlText.setText(value);
        }

        public String getTitle() {
            return _titleText.getText().toString();
        }

        public void setTitle(String value) {
            _titleText.setText(value);
        }

4.  Implement a listener interface to handle user actions in the Screenlet. For 
    example, Add Bookmark Screenlet must detect when the user presses the save 
    button. The `AddBookmarkView` class enables this by implementing 
    [Android's `View.OnClickListener` interface](https://developer.android.com/reference/android/view/View.OnClickListener.html), 
    which defines a single method: `onClick`. The Screenlet's `onClick` 
    implementation gets a reference to the Screenlet and calls its 
    `performUserAction()` method (you'll create `performUserAction()` in the 
    Screenlet class shortly): 
    
        public void onClick(View v) {
            AddBookmarkScreenlet screenlet = (AddBookmarkScreenlet) getParent();

            screenlet.performUserAction();
        }

    You can set the listener to the appropriate UI element by implementing an 
    `onFinishInflate()` method. This method should also retrieve and assign any 
    other UI elements from your layout. For example, the `onFinishInflate()` 
    implementation in `AddBookmarkView` retrieves the URL and title attributes 
    from the layout, and sets them to the `_urlText` and `_titleText` variables, 
    respectively. This method then retrieves the button from the layout and sets 
    this View class as the button's click listener:
    
        protected void onFinishInflate() {
            super.onFinishInflate();

            _urlText = (EditText) findViewById(R.id.url);
            _titleText = (EditText) findViewById(R.id.title_bookmark);

            Button addButton = (Button) findViewById(R.id.add_button);
            addButton.setOnClickListener(this);
        }

5.  Implement 
    [the `BaseViewModel` interface](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/view/BaseViewModel.java)'s 
    methods: `showStartOperation`, `showFinishOperation`, `showFailedOperation`, 
    `getScreenlet`, and `setScreenlet`. In the `show*Operation` methods, you can 
    log what happens in your Screenlet when the server operation starts, 
    finishes successfully, or fails, respectively. In the `getScreenlet` and 
    `setScreenlet` methods, you must get and set the `BaseScreenlet` variable, 
    respectively. This ensures that the View always has a Screenlet reference. 
    For example, Add Bookmark Screenlet implements these methods as follows: 

        @Override
        public void showStartOperation(String actionName) {

        }

	    @Override
        public void showFinishOperation(String actionName) {
            LiferayLogger.i("Add bookmark successful");
        }

        @Override
        public void showFailedOperation(String actionName, Exception e) {
            LiferayLogger.e("Could not add bookmark", e);
        }

        @Override
        public BaseScreenlet getScreenlet() {
            return _screenlet;
        }

        @Override
        public void setScreenlet(BaseScreenlet screenlet) {
            _screenlet = screenlet;
        }

    Note that although you must implement the `show[something]Operation`
    methods, you can leave their implementations empty if you don't need to take
    any specific action. 

[Click here](https://github.com/liferay/liferay-screens/blob/master/android/samples/addbookmarkscreenlet/src/main/java/com/liferay/mobile/screens/bookmark/view/AddBookmarkView.java) 
to see the complete example `AddBookmarkView` class. 

Great! Your View class is finished. Now you're ready to create your Screenlet's 
Interactor class. 

## Creating the Screenlet's Interactor [](id=creating-the-screenlets-Interactor-class)

A Screenlet's Interactor makes the service call to retrieve the data you need 
from a Liferay instance. An Interactor is made up of several components: 

1. The event class. This class lets you handle communication between the 
   Screenlet’s components via event objects that contain the server call’s 
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
   [Login Screenlet's listener](/develop/reference/-/knowledge_base/7-0/loginscreenlet-for-android#listener) 
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
   [use the Mobile SDK to make an asynchronous service call](/develop/tutorials/-/knowledge_base/7-0/invoking-services-asynchronously-from-your-android-app). 
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

## Defining Screenlet Attributes in Your App [](id=defining-screenlet-attributes-in-your-app)

Before creating the Screenlet class, you should define its attributes. These are 
the attributes the app developer can set when inserting the Screenlet's XML in 
an activity or fragment layout. For example, to use 
[Login Screenlet](/develop/reference/-/knowledge_base/7-0/loginscreenlet-for-android), 
the app developer could insert the following Login Screenlet XML in an activity 
or fragment layout: 

    <com.liferay.mobile.screens.auth.login.LoginScreenlet
        android:id="@+id/login_screenlet"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:basicAuthMethod="email"
        app:layoutId="@layout/login_default"
        />

The app developer can set the `liferay` attributes `basicAuthMethod` and 
`layoutId` to set Login Screenlet's authentication method and View, 
respectively. The Screenlet class reads these settings to enable the appropriate 
functionality. 

When creating a Screenlet, you can define the attributes you want to make 
available to app developers. You do this in an XML file inside your Android 
project's `res/values` directory. For example, Add Bookmark Screenlet's 
attributes are defined in the 
[Screenlet's `bookmark_attrs.xml` file](https://github.com/liferay/liferay-screens/blob/master/android/samples/addbookmarkscreenlet/src/main/res/values/bookmark_attrs.xml): 

    <?xml version="1.0" encoding="utf-8"?>
    <resources>
        <declare-styleable name="AddBookmarkScreenlet">
            <attr name="layoutId"/>
            <attr name="folderId"/>
            <attr name="defaultTitle" format="string"/>
        </declare-styleable>
    </resources>

This defines the attributes `layoutId`, `folderId`, and `defaultTitle`. Add 
Bookmark Screenlet's Screenlet class adds functionality to these attributes. 
Here's a brief description of what each does: 

- `layoutId`: Sets the View that displays the Screenlet. This functions the same 
as the `layoutId` attribute in 
[Liferay's existing Screenlets](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-android). 

- `folderId`: Sets the folder ID in the Bookmarks portlet where the Screenlet 
adds bookmarks. 

- `defaultTitle`: Sets each Bookmark's default title. 

Now that you've defined your Screenlet's attributes, you're ready to create the 
Screenlet class.

## Creating the Screenlet Class [](id=creating-the-screenlet-class)

The Screenlet class is the central hub of a Screenlet. It contains attributes 
for configuring the Screenlet's behavior, a reference to the Screenlet's View, 
methods for invoking Interactor operations, and more. When using a Screenlet, 
app developers primarily interact with its Screenlet class. In other words, if a 
Screenlet were to become self-aware, it would happen in its Screenlet class 
(though we're reasonably confident this won't happen). 

To make all this possible, your Screenlet class must implement the Interactor's 
listener interface and extend 
[Screens's `BaseScreenlet` class](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/BaseScreenlet.java) 
with the View Model interface and Interactor class as type arguments. Your 
Screenlet class should also contain instance variables and accompanying getters 
and setters for the listener and any other attributes that the app developer 
needs to access. For constructors, you can call 
[`BaseScreenlet`'s constructors](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/BaseScreenlet.java#L45-L61). 

For example, 
[Add Bookmark Screenlet's Screenlet class](https://github.com/liferay/liferay-screens/blob/master/android/samples/addbookmarkscreenlet/src/main/java/com/liferay/mobile/screens/bookmark/AddBookmarkScreenlet.java) 
extends `BaseScreenlet<AddBookmarkViewModel, AddBookmarkInteractor>` and 
implements `AddBookmarkListener`. It also contains instance variables for 
`AddBookmarkListener` and the bookmark's folder ID, and getters and setters for 
these variables. Also note the constructors call `BaseListScreenlet`'s 
constructors: 

    public class AddBookmarkScreenlet extends 
        BaseScreenlet<AddBookmarkViewModel, AddBookmarkInteractor>
        implements AddBookmarkListener {

        private long _folderId;
        private AddBookmarkListener _listener;

        public AddBookmarkScreenlet(Context context) {
            super(context);
        }

        public AddBookmarkScreenlet(Context context, AttributeSet attributes) {
            super(context, attributes);
        }

        public AddBookmarkScreenlet(Context context, AttributeSet attributes, int defaultStyle) {
            super(context, attributes, defaultStyle);
        }

        public long getFolderId() {
            return _folderId;
        }

        public void setFolderId(long folderId) {
            _folderId = folderId;
        }

        public AddBookmarkListener getListener() {
            return _listener;
        }

        public void setListener(AddBookmarkListener listener) {
            _listener = listener;
        }

        ...

Next, implement the Screenlet's listener methods. This lets the Screenlet class 
receive the server call's results and thus act as the listener. These methods 
should communicate the server call's results to the View (via the View Model) 
and any other listener instances (via the Screenlet class's listener instance). 
For example, here are Add Bookmark Screenlet's listener method implementations:

    public void onAddBookmarkSuccess() {
        getViewModel().showFinishOperation(null);

        if (_listener != null) {
            _listener.onAddBookmarkSuccess();
        }
    }

    public void onAddBookmarkFailure(Exception e) {
        getViewModel().showFailedOperation(null, e);

        if (_listener != null) {
            _listener.onAddBookmarkFailure(e);
        }
    }

These methods are called when the server call succeeds or fails, respectively. 
They first use `getViewModel()` to get a View Model instance and then call the 
`BaseViewModel` methods `showFinishOperation` and `showFailedOperation` to send 
the server call's results to the View. The `showFinishOperation` call sends 
`null` because a successful server call to add a bookmark doesn't return any 
objects. If a successful server call in your Screenlet returns any objects you 
need to display, then you should send them in this `showFinishOperation` call. 
The `showFailedOperation` call sends the `Exception` that results from a failed 
server call to the View. This lets you display an informative error to the user. 
The `onAddBookmarkSuccess` and `onAddBookmarkFailure` implementations then call 
the listener instance's method of the same name. This sends the server call's 
results to any other classes that implement the listener interface, such as the 
activity or fragment that uses the Screenlet. 

Next, you must implement `BaseScreenlet`'s abstract methods: 

- `createScreenletView`: Reads the app developer's Screenlet attribute settings, 
  and inflates the View. You'll use an 
  [Android `TypedArray`](https://developer.android.com/reference/android/content/res/TypedArray.html) 
  to retrieve the attribute settings. You should set the attribute values to the 
  appropriate variables, and set any default values you need to display via a 
  View Model reference. 

  For example, Add Bookmark Screenlet's `createScreenletView` method gets the 
  app developer's attribute settings via a `TypedArray`. This includes the 
  `layoutId`, `defaultTitle`, and `folderId` attributes. The `layoutId` is 
  used to inflate a View reference (`view`), which is then cast to a View 
  Model instance (`viewModel`). The View Model instance's `setTitle` method is 
  then called with `defaultTitle` to set the bookmark's default title. The 
  method concludes by returning the View reference. 

        @Override
        protected View createScreenletView(Context context, AttributeSet attributes) {
            TypedArray typedArray = context.getTheme()
                .obtainStyledAttributes(attributes, R.styleable.AddBookmarkScreenlet, 0, 0);

            int layoutId = typedArray.getResourceId(R.styleable.AddBookmarkScreenlet_layoutId, 0);

            View view = LayoutInflater.from(context).inflate(layoutId, null);

            String defaultTitle = typedArray.getString(R.styleable.AddBookmarkScreenlet_defaultTitle);

            _folderId = castToLong(typedArray.getString(R.styleable.AddBookmarkScreenlet_folderId));

            typedArray.recycle();

            AddBookmarkViewModel viewModel = (AddBookmarkViewModel) view;
            viewModel.setTitle(defaultTitle);

            return view;
        }

- `createInteractor`: Instantiates the Screenlet's Interactor. For example, Add 
  Bookmark Screenlet's `createInteractor` method calls the 
  `AddBookmarkInteractor` constructor to create a new instance of this 
  Interactor: 

        @Override
        protected AddBookmarkInteractor createInteractor(String actionName) {
            return new AddBookmarkInteractor(getScreenletId());
        }

- `onUserAction`: Retrieves any data the user has entered in the View, and 
  starts the Screenlet's server operation via an Interactor instance. If your 
  Screenlet doesn't take user input, this method only needs to do the 
  latter. 

  The example Add Bookmark Screenlet takes user input (the bookmark's URL and 
  title), so its `onUserAction` method must retrieve this data. This method 
  does so via a View Model instance it retrieves with the `getViewModel()` 
  method. The `onUserAction` method starts the server operation by calling the 
  Interactor's `start` method with the user input. Note that the Interactor 
  inherits the `start` method from the 
  [`BaseInteractor` class](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/interactor/BaseInteractor.java). 
  Invoking the `start` method causes the Interactor's `execute` method to run in 
  a background thread: 

        @Override
        protected void onUserAction(String userActionName, AddBookmarkInteractor interactor, Object... args) {
            AddBookmarkViewModel viewModel = getViewModel();
            String url = viewModel.getURL();
            String title = viewModel.getTitle();

            interactor.start(url, title, folderId);
        }

Nice! Your Screenlet is finished! You can now use it 
[the same way you would any other](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-android-apps). 
If you created your Screenlet in its own project, you can also 
[package and distribute](/develop/tutorials/-/knowledge_base/7-0/packaging-your-android-screenlets)
it via the Screens project, JCenter, or Maven Central. 

To finish the Add Bookmark Screenlet example, the following section shows you 
how to use this Screenlet. It also shows how you can set default attribute 
values in an app's `server_context.xml` file. Although you may not need to do 
this when using your Screenlets, it might come in handy on your way to becoming 
a master of Screenlets. 

## Using Your Screenlet [](id=using-your-screenlet)

To use any Screenlet, you must follow these 
[general steps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-android-apps): 

1. Insert the Screenlet's XML in the activity or fragment layout you want the 
   Screenlet to appear in. You can fine-tune the Screenlet's behavior by setting 
   the Screenlet XML's attributes. 

2. Implement the Screenlet's listener in the activity or fragment class. 

As an example of this, the Liferay Screens 
[Test App](https://github.com/liferay/liferay-screens/tree/master/android/samples/test-app) 
uses Add Bookmark Screenlet. You can find the following Add Bookmark Screenlet 
XML in the 
[Test App's `add_bookmark.xml` layout](https://github.com/liferay/liferay-screens/blob/master/android/samples/test-app/src/main/res/layout/add_bookmark.xml): 

    <com.liferay.mobile.screens.bookmark.AddBookmarkScreenlet
        android:id="@+id/bookmark_screenlet"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:folderId="@string/bookmark_folder"
        app:layoutId="@layout/bookmark_default" />

Note that the layout specified by `app:layoutId` (`bookmark_default`) 
matches the layout file of the Screenlet's View (`bookmark_default.xml`). This 
is how you specify the View that displays your Screenlet. For example, if Add 
Bookmark Screenlet had another View defined in a layout file named 
`bookmark_awesome.xml`, you could use that layout by specifying 
`@layout/bookmark_awesome` as the `app:layoutId` attribute's value. 

Also note that the `app:folderId` attribute specifies 
`@string/bookmark_folder` as the bookmark folder's ID. This is an alternative 
way of specifying an attribute's value. Instead of specifying the value 
directly, the Test App specifies the value in its 
[`server_context.xml` file](https://github.com/liferay/liferay-screens/blob/master/android/samples/test-app/src/main/res/values/server_context.xml#L23-L24): 

    ...
	<string name="bookmark_folder">20622</string>
	...

This `name` attribute's value, `bookmark_folder` is then used in the Screenlet 
XML to set the `app:folderId` attribute to `20622`.

Great! Now you know how to use the Screenlets you create. You also know a 
convenient way to specify default values for a Screenlet's attributes. 

## Related Topics [](id=related-topics)

[Using Screenlets in Android Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-android-apps)

[Architecture of Liferay Screens for Android](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-android)

[Creating Android Views](/develop/tutorials/-/knowledge_base/7-0/creating-android-views)

[Creating iOS Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-iOS-screenlets)
