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

To understand the components that comprise a Screenlet, you might want to 
learn the
[architecture of Liferay Screens for Android](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-android) first.
You might also want to review how to
[create a View](/develop/tutorials/-/knowledge_base/7-0/creating-android-views)
to present your Screenlet. Without further ado, let the Screenlet creation
begin! 

## Determining Your Screenlet's Location [](id=determining-your-screenlets-location)

Where you should create your Screenlet depends on how you plan to use it. If you
don't plan to reuse your Screenlet in another app or don't want to redistribute
it, create it in a new package inside your Android app project. This lets you
reference and access Liferay Screens's core, in addition to all the View Sets
you've imported. 

If you want to reuse your Screenlet in another app, create it in a new Android
application module; the tutorial
[Packaging Android Screenlets](/develop/tutorials/-/knowledge_base/7-0/packaging-android-screenlets)
explains how. When your Screenlet's project is in place, you can start creating
its user interface. 

## Creating the Screenlet's UI [](id=creating-the-screenlets-ui)

The first items to create for a Screenlet's UI are its View Model interface and
layout. The following steps explain how: 

1.  Create a new View Model interface that extends
    [`BaseViewModel`](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/java/com/liferay/mobile/screens/base/view/BaseViewModel.java).
    In it, specify getters and setters for the attributes you want to use. Any
    View classes you write for your Screenlet must implement this interface. 

    The Add Bookmark Screenlet's View Model interface `AddBookmarkViewModel`
    specifies getters and setters for its `url` and `title` attributes: 

        public interface AddBookmarkViewModel extends BaseViewModel {
            String getURL();

            void setURL(String value);

            String getTitle();

            void setTitle(String value);
        }

2.  Build your Screenlet's UI by writing a layout XML file. The layout's root
    element should be the fully qualified class name of your Screenlet's View
    class. You'll create that class in the next step, but determine its name now
    and name the layout's root element after it. Lastly, add UI elements for
    displaying the attributes you alluded to in your View Model interface. 

    The Add Bookmark Screenlet's layout file `bookmark_default.xml`, for
    example, has two `EditText` tags for the bookmark's URL and title. It also
    specifies a button for saving the bookmark. 

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

As you layout your Screenlet, Android Studio's graphical layout viewer renders
the Screenlet's UI. 

![Figure 1: Android Studio's graphical layout viewer renders your Screenlet's layout as you create it.](../../../images/screens-android-add-bookmark-view.png)

Next, you'll create a View class for the Screenlet. 

## Creating the Screenlet's View Class [](id=creating-the-screenlets-view-class)

Your new Screenlet now needs a View class to support the UI you just created.
This class must extend an Android layout class, implement your Screenlet's View
Model interface, and implement a listener interface to handle user actions. To
do this, follow these steps: 

1.  Create a View class that extends an Android layout class. Implement
    constructors that call the parent layout class's constructors. 

2.  Implement the View Model interface you defined previously. Add fields to
    reference your UI's components. Implement the View Model interface getter
    methods to return the inner value of each component and setter methods to
    set their inner values. For example, here's
    [`AddBookmarkView`](https://github.com/liferay/liferay-screens/blob/master/android/samples/addbookmarkscreenlet/src/main/java/com/liferay/mobile/screens/bookmark/view/AddBookmarkView.java)'s
    implementation of View Model interface
    [`AddBookmarkViewModel`](https://github.com/liferay/liferay-screens/blob/master/android/samples/addbookmarkscreenlet/src/main/java/com/liferay/mobile/screens/bookmark/view/AddBookmarkViewModel.java):

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

        private EditText _urlText;
        private EditText _titleText;

3.  Implement a listener interface to handle the Screenlet user's actions. You
    can implement the `onFinishInflate` method to set references to your UI's
    components and add this View class to listen for specific user actions.
    Lastly, implement the listener interface methods (e.g.,
    `android.view.View.OnClickListener`'s method `onClick`) to invoke your
    Screenlet's `performUserAction` method--you'll implement the Screenlet class
    shortly.

    The Add Bookmark Screenlet's View class `AddBookmarkView`, for example,
    implements `android.view.View.OnClickListener` to set references to its
    Screenlet's UI components and invoke its Screenlet's `performUserAction`
    method: 

		protected void onFinishInflate() {
			super.onFinishInflate();

			_urlText = (EditText) findViewById(R.id.url);
			_titleText = (EditText) findViewById(R.id.title_bookmark);

			Button addButton = (Button) findViewById(R.id.add_button);
			addButton.setOnClickListener(this);
		}
	   
		public void onClick(View v) {
			AddBookmarkScreenlet screenlet = (AddBookmarkScreenlet) getParent();

			screenlet.performUserAction();
		}

4.  Override
    [`BaseViewModel`](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/java/com/liferay/mobile/screens/base/view/BaseViewModel.java)'s
    methods `showStartOperation`, `showFinishOperation`, `showFailedOperation`, 
    `getScreenlet`, and `setScreenlet`. In the `show*Operation` methods, you can 
    log what happens in your Screenlet when the server operation starts, 
    finishes successfully, or fails, respectively. In the `getScreenlet` and 
    `setScreenlet` methods, you must get and set the `BaseScreenlet` variable 
    `_screenlet`, respectively. This ensures that the View always has a 
    Screenlet reference. 

As an example View class, here's the Add Bookmark Screenlet's
[`AddBookmarkView`](https://github.com/liferay/liferay-screens/blob/master/android/samples/addbookmarkscreenlet/src/main/java/com/liferay/mobile/screens/bookmark/view/AddBookmarkView.java)
class:

    package com.liferay.mobile.screens.bookmark.view;

    import android.content.Context;
    import android.util.AttributeSet;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.LinearLayout;

    import com.liferay.mobile.screens.base.BaseScreenlet;
    import com.liferay.mobile.screens.bookmark.AddBookmarkScreenlet;
    import com.liferay.mobile.screens.bookmark.R;
    import com.liferay.mobile.screens.util.LiferayLogger;

    public class AddBookmarkView extends LinearLayout implements AddBookmarkViewModel, View.OnClickListener {

        public AddBookmarkView(Context context) {
            super(context);
        }

        public AddBookmarkView(Context context, AttributeSet attributes) {
            super(context, attributes);
        }

        public AddBookmarkView(Context context, AttributeSet attributes, int defaultStyle) {
            super(context, attributes, defaultStyle);
        }

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

        public void onClick(View v) {
            AddBookmarkScreenlet screenlet = (AddBookmarkScreenlet) getParent();

            screenlet.performUserAction();
        }

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

        protected void onFinishInflate() {
            super.onFinishInflate();

            _urlText = (EditText) findViewById(R.id.url);
            _titleText = (EditText) findViewById(R.id.title_bookmark);

            Button addButton = (Button) findViewById(R.id.add_button);
            addButton.setOnClickListener(this);
        }

        private EditText _urlText;
        private EditText _titleText;
        private BaseScreenlet _screenlet;
    }

Now you're ready to create your Screenlet's Interactor class.

## Creating the Screenlet's Interactor Class [](id=creating-the-screenlets-Interactor-class)

Your Screenlet's Interactor class is responsible for calling the Liferay remote
services you need. It's a good practice to use Inversion of Control
([IoC](http://en.wikipedia.org/wiki/Inversion_of_control)) in your Interactor
classes, so that anyone can insert different implementations without breaking
the Screenlet. Follow these steps to create an Interactor interface and
implementation: 

1.  Create an Interactor interface that extends
    `Interactor<YourInternalListener>`; replace `YourInternalListener` with
    the name of a listener class to notify your Screenlet class about operation
    status. The listener class is an *internal* listener class.
    You'll create the listener class later. For example, here's the Add Bookmark
    Screenlet's Interactor interface
    `AddBookmarkInteractor`: 

        public interface AddBookmarkInteractor extends Interactor<AddBookmarkListener> {

            void addBookmark(String url, String title, Integer folderId)
                throws Exception;
    
        }

2.  Create an Interactor implementation class that extends
    [`BaseRemoteInteractor<YourInternalListener>`](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/java/com/liferay/mobile/screens/base/interactor/BaseRemoteInteractor.java)
    and implements the Interactor interface you just created. In your Interactor
    methods, validate input and invoke the Liferay Mobile SDK to call the remote
    services. Also, when the service request ends, make sure to post an event
    into the [EventBus](https://github.com/greenrobot/EventBus) by invoking
    `EventBusUtil.post(event)`; `event` is a `JSONObjectEvent` object containing
    the `targetScreenletId` together with either the service result or an
    exception. Note that every Interactor must implement the
    `onEvent(JSONObjectEvent event)` method for `EventBus` to invoke. Implement
    this to notify the registered listener on the service's success or failure.
    Your constructor can invoke the superclass's constructor.

    <!-- I'm not sure how to "post an event into the `EventBus`", as mentioned
    above. I haven't found it implemented in the Add Bookmarks Screenlet. - Jim
    --> 

    A good example Interactor implementation is the Add Bookmark Screenlet's
    class `AddBookmarkInteractorImpl`: 

		public class AddBookmarkInteractorImpl
			extends BaseRemoteInteractor<AddBookmarkListener>
			implements AddBookmarkInteractor {

			public AddBookmarkInteractorImpl(int targetScreenletId) {
				super(targetScreenletId);
			}

			public void addBookmark(String url, String title, Integer folderId) throws Exception {
				// Validate input
				if (url == null || url.isEmpty()) {
					throw new IllegalArgumentException("Invalid url");
				}

				if (folderId == null || folderId == 0) {
					throw new IllegalArgumentException("folderId not set");
				}

				// Call the service asynchronously
				BookmarksEntryService bookmarksEntryService = getBookmarksEntryService();
				bookmarksEntryService.addEntry(
						LiferayServerContext.getGroupId(), folderId, title, url, "", null);
			}

			public void onEvent(JSONObjectEvent event) {
				if (!isValidEvent(event)) {
					return;
				}

				if (event.isFailed()) {
					getListener().onAddBookmarkFailure(event.getException());
				}
				else {
					getListener().onAddBookmarkSuccess();
				}
			}

			private BookmarksEntryService getBookmarksEntryService() {
				Session sessionFromCurrentSession = SessionContext.createSessionFromCurrentSession();
				sessionFromCurrentSession.setCallback(new JSONObjectCallback(getTargetScreenletId()));
				return new BookmarksEntryService(sessionFromCurrentSession);
			}

		}
 
3.  Now you can create the `YourInternalListener` interface that you referenced
    in the previous step. Define one method to handle operation failure and
    another method to handle success. You can follow this naming convention for
    the two methods: `onYourOperationFailure` and `onYourOperationSuccess`. The
    `AddBookmarkListener` interface serves as an example: 

		public interface AddBookmarkListener {

			void onAddBookmarkFailure(Exception exception);

			void onAddBookmarkSuccess();

		}

Next, you'll create the Screenlet class. 

## Creating the Screenlet Class [](id=creating-the-screenlet-class)

The Screenlet class is the Screenlet's cornerstone and the entry point for app
developers to use the Screenlet. 

Create the Screenlet class so that it implements your Interactor's internal
listener class and extends
[`BaseScreenlet`](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/java/com/liferay/mobile/screens/base/BaseScreenlet.java).
Specify your View Model interface and Interactor class as type parameters for
`BaseScreenlet`. Since the Screenlet is notified by its Interactor when an
asynchronous operation ends, the Screenlet class must implement the listener
interface that the Interactor uses. 

Screenlet classes can use an *external* listener class to notify classes outside
the Screenlet (i.e., classes in your app) about events. The 
[reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-android),
shows that every Liferay Screenlet specifies listener classes for registering
with your classes. You can use an internal listener class as an external
listener, or use a completely different listener class (for example, one that
uses different methods or signatures).

The `AddBookmarkScreenlet` class uses the internal listener class
`AddBookmarkListener` as an external listener class. It provides public methods
for the app's classes to register for its events. In the code below, the
Screenlet class has a public method for external classes to register as
listeners and methods to propagate events to them: 

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

	public AddBookmarkListener getListener() {
		return _listener;
	}

	public void setListener(AddBookmarkListener listener) {
		_listener = listener;
	}

	private Integer _folderId;
	private AddBookmarkListener _listener; 

You can even notify the app using a different mechanism such as the Event Bus or
Android's `BroadcastReceiver`. Note that the implemented interface methods call
the View to modify the UI and notify the app's listener. This lets your app 
perform any action. Next you must implement `BaseScreenlet`'s abstract methods.

The first `BaseScreenlet` method to implement is the `createScreenletView`
method. In this method, you get attributes from the XML definition and then
either store them as class attributes or otherwise use them. Next, inflate the
View using the layout specified in the `liferay:layoutId` attribute. You can
even use the read attributes to configure the initial state of the View.

The second abstract `BaseScreenlet` method to implement is `createInteractor`.
This is a factory method in which you must create the corresponding Interactor
for a specific action name, passing in the Screenlet's ID. `BaseScreenlet`'s
method `getScreenletId()` returns the ID. Note that a single Screenlet may have
several interactions (use cases). Each interaction should be implemented in a
separate Interactor. In the example Add Bookmark Screenlet, there is only one
Interactor, so the object is created in the `createInteractor` method.
Alternatively, you can retrieve the instance via your IoC framework.

The third and final abstract `BaseScreenlet` method to implement is
`onUserAction`. In this method, retrieve the data entered in the View and start
the operation by using the data and the supplied Interactor.

Here's the
[`AddBookmarkScreenlet`](https://github.com/liferay/liferay-screens/blob/master/android/samples/addbookmarkscreenlet/src/main/java/com/liferay/mobile/screens/bookmark/AddBookmarkScreenlet.java)
class from the Add Bookmark Screenlet:

    package com.liferay.mobile.screens.bookmark;

    import android.content.Context;
    import android.content.res.TypedArray;
    import android.util.AttributeSet;
    import android.view.LayoutInflater;
    import android.view.View;

    import com.liferay.mobile.screens.base.BaseScreenlet;
    import com.liferay.mobile.screens.bookmark.interactor.AddBookmarkInteractor;
    import com.liferay.mobile.screens.bookmark.interactor.AddBookmarkInteractorImpl;
    import com.liferay.mobile.screens.bookmark.interactor.AddBookmarkListener;
    import com.liferay.mobile.screens.bookmark.view.AddBookmarkViewModel;

    /**
     * @author Javier Gamarra
     */
    public class AddBookmarkScreenlet
        extends BaseScreenlet<AddBookmarkViewModel, AddBookmarkInteractor>
        implements AddBookmarkListener {

        public AddBookmarkScreenlet(Context context) {
            super(context);
        }

        public AddBookmarkScreenlet(Context context, AttributeSet attributes) {
            super(context, attributes);
        }

        public AddBookmarkScreenlet(Context context, AttributeSet attributes, int defaultStyle) {
            super(context, attributes, defaultStyle);
        }

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

        @Override
        protected View createScreenletView(Context context, AttributeSet attributes) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(attributes, R.styleable.AddBookmarkScreenlet, 0, 0);

            int layoutId = typedArray.getResourceId(R.styleable.AddBookmarkScreenlet_layoutId, 0);

            View view = LayoutInflater.from(context).inflate(layoutId, null);

            String defaultTitle = typedArray.getString(R.styleable.AddBookmarkScreenlet_defaultTitle);

            _folderId = typedArray.getInteger(R.styleable.AddBookmarkScreenlet_folderId, 0);

            typedArray.recycle();

            AddBookmarkViewModel viewModel = (AddBookmarkViewModel) view;
            viewModel.setTitle(defaultTitle);

            return view;
        }

        @Override
        protected AddBookmarkInteractor createInteractor(String actionName) {
            return new AddBookmarkInteractorImpl(getScreenletId());
        }

        @Override
        protected void onUserAction(String userActionName, AddBookmarkInteractor interactor, Object... args) {
            AddBookmarkViewModel viewModel = getViewModel();
            String url = viewModel.getURL();
            String title = viewModel.getTitle();

            try {
                interactor.addBookmark(url, title, _folderId);
            }
            catch (Exception e) {
                onAddBookmarkFailure(e);
            }
        }

        public Integer getFolderId() {
            return _folderId;
        }

        public void setFolderId(Integer folderId) {
            _folderId = folderId;
        }

        public AddBookmarkListener getListener() {
            return _listener;
        }

        public void setListener(AddBookmarkListener listener) {
            _listener = listener;
        }

        private Integer _folderId;
        private AddBookmarkListener _listener;

    }

If you copied the code above, you'll see a few errors regarding
`R.styleable.AddBookmarkScreenlet*` references. The next section explains how to
resolve such errors. 

Great! Now you know how to create a Screenlet class. 

## Defining Screenlet Attributes in Your App [](id=defining-screenlet-attributes-in-your-app)

To be able to read the Screenlet attributes, you need to add an XML file in
`res/values` that defines those attributes. Here's the `bookmark_attrs.xml`
example for the Add Bookmark Screenlet: 

    <?xml version="1.0" encoding="utf-8"?>
    <resources>
        <declare-styleable name="AddBookmarkScreenlet">
            <attr name="layoutId"/>
            <attr name="folderId"/>
            <attr name="defaultTitle" format="string"/>
        </declare-styleable>
    </resources>

Now you can use your Screenlet as you would any other. Just remember to use the
XML file in which you built your Screenlet's UI as the value of the
`liferay:layoutId` attribute (the value is `bookmark_default` in this example).
For example, here's the code to insert the Add Bookmark Screenlet in an Android
activity: 

    <com.your.package.AddBookmarkScreenlet
        android:id="@+id/addbookmarks_screenlet"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        liferay:folderId="@integer/bookmark_folder"
        liferay:layoutId="@layout/bookmark_default"
    />

Note that in your app's `server_context.xml` file, you can specify any
additional settings your app requires. You can set context variables for your
Screenlet to use by naming them in your Screenlet's layout XML file and then
setting their values in your app's `server_context.xml` file.

The Add Bookmark Screenlet's `server_context.xml` file sets the folder into
which the Screenlet adds bookmarks. The preceding Screenlet element code maps
the Screenlet's `liferay:folderId` setting to a bookmark folder context integer
`@integer/bookmark_folder`. In the `server_context.xml` file below, the context
integer element is set to the ID of a Bookmarks portlet folder. That value is
in turn assigned to the Screenlet's `folderId` variable, so the Screenlet can
add bookmarks to that folder. Notice that in addition to the value specified for
the `bookmark_folder`, values are also specified for `liferay_server`,
`liferay_company_id`, and the `liferay_group_id`: 

    <?xml version="1.0" encoding="utf-8"?>
    <resources>

        <!-- Change these values for your Liferay Portal installation -->

        <string name="liferay_server">http://10.0.2.2:8080</string>

        <integer name="liferay_company_id">20154</integer>
        <integer name="liferay_group_id">20181</integer>

        <!--
          Create a folder in a Bookmarks portlet on a page in the above portal instance and site.
          Get that folder's id from Liferay's database and use it here
        -->
        <integer name="bookmark_folder">21503</integer>

    </resources>

Congratulations! Your Screenlet is ready to
[use](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-android-apps)
in Android apps. You can even
[package and distribute](/develop/tutorials/-/knowledge_base/7-0/packaging-your-android-screenlets)
it via the Screens project, JCenter, or Maven Central. Now you know how to
create Screenlets in Liferay Screens for Android. 

## Related Topics [](id=related-topics)

[Using Screenlets in Android Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-android-apps)

[Architecture of Liferay Screens for Android](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-android)

[Creating Android Views](/develop/tutorials/-/knowledge_base/7-0/creating-android-views)

[Creating iOS Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-iOS-screenlets)
