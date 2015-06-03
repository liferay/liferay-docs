# Creating Android Screenlets [](id=creating-android-screenlets)

The
[screenlets](/develop/reference/-/knowledge_base/6-2/screenlets-in-liferay-screens-for-android)
that come with Liferay Screens cover common use cases for mobile apps that use
Liferay. They authenticate users, interact with Dynamic Data Lists, view assets,
and more. However, what if there's no screenlet for *your* specific use case? No
sweat! You can create your own screenlet--anyone can create them. Extensibility
is a key strength of Liferay Screens. 

This tutorial explains how to create your own screenlets. As you follow the
tutorial you'll examine code from the
[sample Add Bookmark screenlet](https://github.com/liferay/liferay-screens/tree/1.0.0/android/samples/addbookmarkscreenlet).
In that screenlet, a user specifies a URL and title for a bookmark, and clicks
on a button to submit it to Liferay's Bookmark service. 

To understand the components that comprise a screenlet, you might want to first
analyze the Liferay Screens
[architecture](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android).
You might also want to learn how to
[create a view](/develop/tutorials/-/knowledge_base/6-2/creating-android-views)
to present your screenlet. Without further ado, let the screenlet creation
begin! 

## Determining Your Screenlet's Location [](id=determining-your-screenlets-location)

Where you should create your screenlet depends on how you plan to use it. If you
don't plan to reuse your screenlet in another app or don't want to redistribute
it, create it in a new package inside your project. This lets you reference and
access Liferay Screens's core, in addition to all the view sets you've imported. 

If you want to reuse your screenlet in another app, create it in a new Android
application module. The tutorial
[Packaging Android Screenlets](/develop/tutorials/-/knowledge_base/6-2/packaging-android-screenlets)
explains how to create such a module. When your screenlet's project is in place,
you can start creating its user interface. 

## Creating the Screenlet's UI [](id=creating-the-screenlets-ui)

The first things to create for a screenlet's UI are its view model interface
and layout. The following steps explain how to create them: 

1.  Create a new view model interface that extends
    [`BaseViewModel`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/library/core/src/main/java/com/liferay/mobile/screens/base/view/BaseViewModel.java).
    In it, specify getters and setters for the attributes you want to use. Any
    view classes you write for your screenlet must implement this interface. 

    The Add Bookmark screenlet's view model interface `AddBookmarkViewModel`
    specifies getters and setters for its `url` and `title` attributes: 

        public interface AddBookmarkViewModel extends BaseViewModel {
            String getURL();

            void setURL(String value);

            String getTitle();

            void setTitle(String value);
        }

2.  Build your screenlet's UI by writing a layout XML file. The layout's root
    element should be the fully qualified class name of your screenlet's view
    class. You'll create that class in the next step, but decide its name now
    and name the layout's root element after it. Lastly, add UI elements for
    displaying the attributes you alluded to in your view model interface. 

    The Add Bookmark screenlet's layout file `bookmark_default.xml`, for
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

As you layout your screenlet, Android Studio's graphical layout viewer renders
the screenlet's UI. 

![Figure 1: Android Studio's graphical layout viewer renders your screenlet's layout as you create it.](../../images/screens-android-add-bookmark-view.png)

Next, you'll create a view class for the screenlet. 

## Creating the Screenlet's View Class [](id=creating-the-screenlets-view-class)

Your new screenlet now needs a view class to support the UI you just created.
This class must extend an Android layout class, implement your screenlet's view
model interface, and implement a listener interface to handle user actions. To
do so, follow these steps: 

1.  Create a view class that extends an Android layout class. Implement
    constructors that call the parent layout class's constructors. 

2.  Implement the view model interface you defined previously. Add fields to
    reference your UI's components. Implement the view model interface getter
    methods to return the inner value of each component, and setter methods to
    set their inner values. For example, here's
    [`AddBookmarkView`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/samples/addbookmarkscreenlet/src/main/java/com/liferay/mobile/screens/bookmark/view/AddBookmarkView.java)'s
    implementation of view model interface
    [`AddBookmarkViewModel`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/samples/addbookmarkscreenlet/src/main/java/com/liferay/mobile/screens/bookmark/view/AddBookmarkViewModel.java):

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

3.  Implement a listener interface to handle user actions. You can implement the
    `onFinishInflate` method to set references to your UI's components and add
    this view class to listen on specific user actions. Lastly, implement the
    listener interface methods (e.g., `android.view.View.OnClickListener`'s
    method `onClick`) to invoke your screenlet's `performUserAction`
    method--you'll implement the screenlet class shortly.

    For example, the Add Bookmark screenlet's view class `AddBookmarkView`
    implement's `android.view.View.OnClickListener` to set references to its
    screenlet's UI components and invoke its screenlet's `performUserAction`
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

4.  Override [`BaseViewModel`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/library/core/src/main/java/com/liferay/mobile/screens/base/view/BaseViewModel.java)'s
    methods `showStartOperation`, `showFinishOperation`, and
   `showFailedOperation`. In these methods, you can log what's happening in your
    screenlet. 

As an example view class, here's the Add Bookmark screenlet's
[`AddBookmarkView`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/samples/addbookmarkscreenlet/src/main/java/com/liferay/mobile/screens/bookmark/view/AddBookmarkView.java)
class:

	package com.liferay.mobile.screens.bookmark.view;

	import android.content.Context;
	import android.util.AttributeSet;
	import android.view.View;
	import android.widget.Button;
	import android.widget.EditText;
	import android.widget.LinearLayout;

	import com.liferay.mobile.screens.bookmark.AddBookmarkScreenlet;
	import com.liferay.mobile.screens.bookmark.R;
	import com.liferay.mobile.screens.util.LiferayLogger;
	import com.liferay.mobile.screens.viewsets.defaultviews.LiferayCrouton;

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
			LiferayCrouton.error(getContext(), "Could not add bookmark", e);
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
	}

Now you're ready to create your screenlet's interactor class.

## Creating the Screenlet's Interactor Class [](id=creating-the-screenlets-interactor-class)

Your screenlet's interactor class is responsible for calling the Liferay remote
services you need. It's a good practice to use Inversion of Control
([IoC](http://en.wikipedia.org/wiki/Inversion_of_control)) in your interactor
classes, so that anyone can insert different implementations without breaking
the screenlet. Follow these steps to create an interactor interface and
implementation: 

1.  Create an interactor interface that extends `Interactor<YourScreenletListener>`,
    replacing `YourScreenletListener` with the name of a listener class to use
    internally in your screenlet. You'll create the listener class at the end of
    this section. For example, here's the Add Bookmark screenlet's interactor
    interface `AddBookmarkInteractor`: 

        public interface AddBookmarkInteractor extends Interactor<AddBookmarkListener> {

            void addBookmark(String url, String title, Integer folderId)
                throws Exception;
    
        }

2.  Create an interactor implementation class that extends
    [`BaseRemoteInteractor<YourScreenletListener>`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/library/core/src/main/java/com/liferay/mobile/screens/base/interactor/BaseRemoteInteractor.java)
    and implements the interactor interface you just created. In your interactor
    methods, validate input and invoke the Liferay Mobile SDK to call the remote
    services. Also, when the service request ends, make sure to post an event
    into the `EventBus` by invoking `EventBusUtil.post(event)`, where `event` is
    a `JSONObjectEvent` object containing the `targetScreenletId` together with
    either the service result or an exception. Note that every interactor must
    implement the `onEvent(JSONObjectEvent event)` method. `EventBus` invokes
    this method. Implement this to notify the registered listener on the
    service's success or failure. Your constructor can invoke the superclass's
    constructor. 

    <!-- I'm not sure how to "post an event into the `EventBus`", as mentioned
    above. I haven't found it implemented in the Add Bookmarks screenlet. - Jim
    --> 

    A good example interactor implementation is the Add Bookmark screenlet's
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
    
3.  Now you can create the `YourScreenletListener` interface that you references
    in the previous step. Define one method to handle operation failure and
    another method to handle success. You can follow this naming convention for
    the two methods: `onYourScreenletFailure` and `onYourScreenletSuccess`. The
    `AddBookmarkListener` interface serves as an example: 

		public interface AddBookmarkListener {

			void onAddBookmarkFailure(Exception exception);

			void onAddBookmarkSuccess();

		}

Next, you'll create the screenlet class. 

## Creating the Screenlet Class [](id=creating-the-screenlet-class)

The screenlet class is the screenlet's cornerstone and the entry point for app
developers to use the screenlet. 

Create the screenlet class so that it implements your listener class and extends
[`BaseScreenlet`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/library/core/src/main/java/com/liferay/mobile/screens/base/BaseScreenlet.java);
specify your view model interface and interactor class as type parameters for
`BaseScreenlet`. Since the screenlet is notified by its interactor when an
asynchronous operation ends, the screenlet class must implement the listener
interface used by the interactor. Screenlet classes usually use a listener to
notify the app. That listener can be the same, or a completely different one
(for example, one that uses different methods or signatures). You can even
notify the app using a different mechanism such as the Event Bus or Android's
`BroadcastReceiver`. Note that the implemented interface methods call the view
to modify the UI and notify the app's listener. This allows your app to perform
any action. Next you must implement `BaseScreenlet`'s abstract methods. 

The first `BaseScreenlet` method to implement is the `createScreenletView`
method. In this method, you get attributes from the XML definition and then
either store them as class attributes or otherwise use them. Next, inflate the
view using the layout specified in the `liferay:layoutId` attribute. You can
even use the read attributes to configure the initial state of the view.

The second abstract `BaseScreenlet` method to implement is `createInteractor`.
This is a factory method in which you must create the corresponding interactor
for a specific action name, passing in the screenlet's ID--`BaseScreenlet`'s
method `getScreenletId()` returns the ID. Note that a single screenlet may have
several interactions (use cases). Each interaction should be implemented in a
separate interactor. In the example Add Bookmark screenlet, there is only one
interactor, so the object is created in the method. Alternatively, you can
retrieve the instance via your IoC framework.

The third and final abstract `BaseScreenlet` method to implement is
`onUserAction`. In this method, retrieve the data entered in the view and start
the operation by using the data and the supplied interactor.

Here's the
[`AddBookmarkScreenlet`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/samples/addbookmarkscreenlet/src/main/java/com/liferay/mobile/screens/bookmark/AddBookmarkScreenlet.java)
class from the Add Bookmark screenlet:

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
`R.styleable.AddBookmarkScreenlet*` references. See the next section to learn
how to create an XML file to resolve such errors.

Great! Now you know how to create a screenlet class. 

## Defining Screenlet Attributes in Your App

To be able to read the screenlet attributes, you need to add an XML file in
`res/values` that defines those attributes. Here's the `bookmark_attrs.xml`
example for the Add Bookmark screenlet: 

    <?xml version="1.0" encoding="utf-8"?>
    <resources>
        <declare-styleable name="AddBookmarkScreenlet">
            <attr name="layoutId"/>
            <attr name="folderId"/>
            <attr name="defaultTitle" format="string"/>
        </declare-styleable>
    </resources>

Celebrate! You're done! Now you can use your screenlet as you would any other.
Just remember to use the XML file in which you built your screenlet's UI as the
value of the `liferay:layoutId` attribute (the value is `bookmark_default` in
this example). For example, here's the code to insert the Add Bookmark screenlet
in an Android activity: 

    <com.your.package.AddBookmarkScreenlet
        android:id="@+id/addbookmarks_screenlet"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        liferay:folderId="@integer/bookmark_folder"
        liferay:layoutId="@layout/bookmark_default"
    />

Note, in your app's `server_context.xml` file, you can specify any additional
settings your app requires. You can set context variables for your screenlet to
use by naming them in your screenlet's layout XML file and then setting their
values in your app's `server_context.xml` file. This is done in the Add Bookmark
screenlet to set the folder into which the the screenlet adds bookmarks. The
preceding screenlet element code maps the screenlet's `liferay:folderId` setting
to a bookmark folder context integer `@integer/bookmark_folder`. In the
`server_context.xml` file below, the context integer element is set to the ID of
a Bookmarks portlet folder. That value is, in turn, assigned to the screenlet's
`folderId` variable, for the screenlet to add bookmarks to that folder. Notice
that in addition to the value specified for the `bookmark_folder`, values are
also specified for `liferay_server`, `liferay_company_id`, and the
`liferay_group_id`: 

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

Congratulations! Your screenlet is ready to
[use](/develop/tutorials/-/knowledge_base/6-2/using-screenlets-in-android-apps)
in Android apps. You can even contribute it to the Screens project, JCenter, or
Maven Central. Now you know how to create screenlets in Liferay Screens for
Android. 

## Related Topics [](id=related-topics)

[Using Screenlets in Android Apps](/develop/tutorials/-/knowledge_base/6-2/using-screenlets-in-android-apps)

[Architecture of Liferay Screens for Android](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android)

[Creating Android Views](/develop/tutorials/-/knowledge_base/6-2/creating-android-views)

[Creating iOS Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-iOS-screenlets)