# Creating Screenlets in Liferay Screens for Android [](id=creating-screenlets-in-liferay-screens-for-android)

Liferay Screens' built-in screenlets cover many common use cases in mobile 
apps that connect to Liferay. For example, you can use them to authenticate 
users, interact with Dynamic Data Lists, and view assets. However, what if 
there's not a screenlet for *your* specific use case? No sweat! You can write 
your own screenlet. Screenlets can also be written by others and contributed to 
the Screens project. Screenlets can also be added to jCenter and Maven Central 
for distribution. This extensibility is one of Screens' key strengths. 

This tutorial shows you how to create your own screenlets in Liferay Screens for 
Android. You'll create a bookmarks screenlet that lets the user enter the 
bookmark's URL and title in text boxes. When the user touches the submit button, 
the URL and title are sent to the Liferay instance's Bookmark service to be 
saved. As you follow along here with this screenlet's development, you can refer 
to its finished code [here on GitHub](https://github.com/liferay/liferay-screens/tree/master/android/samples/addbookmarkscreenlet). 

Also, before proceeding you may want to read the tutorial 
[Architecture of Liferay Screens for Android](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android) 
to understand in detail the concepts underlying screenlets. You may also want to 
read the tutorial [Creating Views in Liferay Screens for Android](/tutorials/-/knowledge_base/6-2/creating-views-in-liferay-screens-for-android) 
to support your new screenlet from the view that you want to use. Without any 
further ado, let the screenlet creation begin! 

## Deciding Your Screenlet's Location [](id=deciding-your-screenlets-location)

You have a couple different options on where to create your screenlet, depending 
on how you plan on using it. If you don't plan to reuse your screenlet in 
another app, or if you don't want to redistribute it, the best place to create 
it is in a new package inside your project. This way you can reference and 
access Liferay Screens' core, in addition to all the view sets you've imported. 

If you want to reuse your screenlet in another app, you need to create it in a 
new Android application module. The steps for creating such a module are 
presented in the tutorial [Packaging Your Android Screenlets](http://www.liferay.com/).

## Creating the Screenlet's UI [](id=creating-the-screenlets-ui)

You should start by creating the screenlet's UI. This is done by creating a new 
interface for the view and then creating a new layout file. Use the following 
steps to do this for the bookmarks screenlet: 

1. Create a new interface called `AddBookmarkViewModel`. This is for adding the 
   attributes to show in the view. In this case, the attributes are `url` and 
   `title`. Any screenlet view must implement this interface.

        public interface AddBookmarkViewModel extends BaseViewModel {
            String getURL();
            
            void setURL(String value);
            
            String getTitle();
            
            void setTitle(String value);
        }

2. Build your UI using a layout XML file. Here, this file is called 
   `bookmark_default.xml`. Put in two `EditText` tags: one for the URL and 
   another for the title. Also, add a `Button` tag to let the user save the 
   bookmark. Note that the root element is a custom class. You'll create this 
   class in the next section.

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

At this point, the graphical layout viewer in Android Studio should look similar 
to this: 

![Figure 1: Android Studio's graphical layout viewer while creating your own screenlet.](../../images/screens-android-add-bookmark-view.png)

Next, you'll create a view class for the screenlet. 

## Creating the Screenlet's View Class [](id=creating-the-screenlets-view-class)

Your new screenlet now needs a view class to implement the UI you just created. 
Create a new custom view class called `AddBookmarkView`. This class 
should extend `LinearLayout` and implement `AddBookmarkViewModel`:

    public class AddBookmarkView extends LinearLayout 
        implements AddBookmarkViewModel {
    
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
        }
    
        @Override
        public void showFailedOperation(String actionName, Exception e) {
        }
    
        private EditText _urlText;
        private EditText _titleText;
    
    }

In the `onFinishInflate` method, get references to the components. Then complete 
the getters and setters by using the components' inner values:

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    
        _urlText = (EditText) findViewById(R.id.url);
        _titleText = (EditText) findViewById(R.id.title);
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

Now you're ready to create your screenlet's interactor class.

## Creating the Screenlet's Interactor Class [](id=creating-the-screenlets-interactor-class)

The screenlet's interactor class is responsible for sending the bookmark to the 
Liferay instance (or any other backend). Note that it's a good practice to use 
[IoC](http://en.wikipedia.org/wiki/Inversion_of_control) in your interactor 
classes. This way, anyone can provide a different implementation without 
breaking the code. The `Interactor` base class also needs a parameter that 
represents the listener type to notify. This is defined here (you'll implement 
the `AddBookmarkListener` in a moment): 

    public interface AddBookmarkInteractor extends Interactor<AddBookmarkListener> {
    
        void addBookmark(String url, String title, Integer folderId)
            throws Exception;
    
    }
    
    public class AddBookmarkInteractorImpl
        extends BaseRemoteInteractor<AddBookmarkListener>
        implements AddBookmarkInteractor {
    
        public AddBookmarkInteractorImpl(int targetScreenletId) {
            super(targetScreenletId);
        }
    
        public void addBookmark(String url, String title, Integer folderId) {
            // 1. Validate input
            if (url == null || url.isEmpty()) {
                throw new IllegalArgumentException("Invalid url");
            }
    
            // 2. Call the service asynchronously.
            // Notify when the request ends using the EventBus
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
    }

Pay special attention to the second step in the `addBookmark` method. When the 
request ends, make sure you post an event into the bus using 
`EventBusUtil.post(event)`, where `event` is a `JSONObjectEvent` object 
containing the `targetScreenletId` together with either the result or the 
exception. Every interactor should also implement the `onEvent` method. This 
method is invoked by the `EventBus` and calls the registered listener. A good 
example is the `AddBookmarkInteractorImpl`.

Now you can create the `AddBookmarkListener` interface. It's very simple, having 
only two methods:

    public interface AddBookmarkListener {
    
        void onAddBookmarkFailure(Exception exception);
    
        void onAddBookmarkSuccess();
    }

Next, you'll create the screenlet class. 

## Creating the Screenlet Class [](id=creating-the-screenlet-class)

Once your interactor is ready, you need to create the screenlet class. This is 
the cornerstone and entry point that the app developer sees and interacts with. 
In this example, this class is called `AddBookmarkScreenlet` and extends from 
`BaseScreenlet`. Again, this class needs to be parameterized with the interactor 
class. Since the screenlet is notified by the interactor when the asynchronous 
operation ends, you must implement the listener interface used by the 
interactor. In this case, this is `AddBookmarkListener`. Also, this class 
usually has another listener to notify the app. This listener can be the same 
one you used in the interactor, or a different one altogether (for example, if 
you want different methods or signatures). You can even notify the app using a 
different mechanism such as the Event Bus, Android's `BroadcastReceiver`, or 
others. Note that the implemented interface methods call the view to modify the 
UI and the app's listener. This allows the app to perform any action:

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
            // Invoked from the interactor:
            // Notify both the view and the app's listener
    
            getViewModel().showFinishOperation(null);
    
            if (_listener != null) {
                _listener.onAddBookmarkSuccess();
            }
        }
    
        public void onAddBookmarkFailure(Exception e) {
            getViewModel().showFinishOperation(null);
    
            if (_listener != null) {
                _listener.onAddBookmarkFailure(e);
            }
        }
    
        public void setListener(AddBookmarkListener listener) {
            _listener = listener;
        }
    
        private AddBookmarkListener _listener;
    
    }

You're almost finished! The next step is to implement the screenlet's abstract 
methods. First up is the `createScreenletView` method. In this method, you get 
attributes from the XML definition and then either store them as class 
attributes or use them otherwise. Next, inflate the view using the layout 
specified in the `liferay:layoutId` attribute. You can even use the read 
attributes to configure the initial state of the view. Don't worry about 
`R.styleable` showing as errors; you'll take care of this in the next section. 
Go ahead and add the `createScreenletView` method now: 

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
    
    private Integer _folderId;

The Second abstract method to implement is `createInteractor`. This is a factory 
method in which you must create the corresponding interactor for a specific 
action name. Note that a single screenlet may have several interactions (use 
cases). Each interaction should therefore be implemented in a separate 
interactor. In this example there is only one interactor, so the object is 
created in the method. Alternatively, you can retrieve the instance via your IoC 
framework. You also need to pass the `screenletId` (a number autogenerated by 
the `BaseScreenlet` class) to the constructor:

    protected AddBookmarkInteractor createInteractor(String actionName) {
        return new AddBookmarkInteractorImpl(getScreenletId());
    }

The third and final abstract method to implement is `onUserAction`. In this 
method, retrieve the data entered in the view and start the operation by using 
the data and the supplied interactor:
    
    @Override
    protected void onUserAction(String userActionName, 
        BookmarkInteractor interactor, Object... args) {
        
        AddBookmarkViewModel viewModel = (AddBookmarkViewModel) getScreenletView();
        String url = viewModel.getURL();	
        String title = viewModel.getTitle();
    
        try {
            interactor.addBookmark(url, title, _folderId);
        }
        catch (Exception e) {
            onAddBookmarkFailure(e);
        }
    }

Great! Now you have a screenlet class for the bookmarks screenlet. The final 
step is hooking it up to the rest of the screenlet's code.

## Triggering the User Action [](id=triggering-the-user-action)

To be able to read the screenlet attributes you have to add an `xml` file that 
defines those attributes. Here's an example for this `AddBookmarkScreenlet`: 

    <?xml version="1.0" encoding="utf-8"?>
    <resources>
        <declare-styleable name="AddBookmarkScreenlet">
            <attr name="layoutId"/>
            <attr name="folderId"/>
            <attr name="defaultTitle" format="string"/>
        </declare-styleable>
    </resources>

Next, you need to trigger the user action when the button is pressed. To do 
this, go back to the view class and add a listener to the button: 

    protected void onFinishInflate() {
        super.onFinishInflate();
        
        // same code as before
    
        Button addButton = (Button) findViewById(R.id.add_button);
        addButton.setOnClickListener(this);
    }
    
    public void onClick(View v) {
        AddBookmarkScreenlet screenlet = (AddBookmarkScreenlet) getParent();
    
        screenlet.performUserAction();
    }
    
To handle the button press, you also need to make `AddBookmarkView` implement 
`OnClickListener`.

Celebrate! You're done! Now you can use the screenlet as you would any other. 
Just remember to use the `xml` file you built your screenlet's UI in as the 
`liferay:layoutId` attribute's value (`bookmark_default` in this example). An 
example of the screenlet inserted in an Android activity is shown here: 

    <com.your.package.AddBookmarkScreenlet
        android:id="@+id/addbookmarks_screenlet"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        liferay:folderId="@integer/bookmark_folder"
        liferay:layoutId="@layout/bookmark_default"
    />

Another thing to keep in mind is additional settings in your app's 
`server_context.xml`, if your screenlet requires them. The `AddBookmarkScreenlet` 
in this example does in fact require such a setting: a folder's `folderId` in a 
Bookmarks portlet. This is the folder the `AddBookmarkScreenlet` saves bookmarks 
to. You can give the `folderId` property a name when you insert the screenlet in 
a fragment or activity, and then use that name in `server_context.xml` to assign 
it a value. In the above `xml`, `folderId` is named `bookmark_folder` in the 
`liferay:folderId` setting. You can then add the following line in 
`server_context.xml` to give `folderId` a value. Remember to change this value 
to match a bookmarks folder's `folderId` in your portal.

    <integer name="bookmark_folder">20676</integer>

Congratulations! By creating an example bookmarks screenlet, you now know how to 
create screenlets in Screens for Android. 

## Related Topics [](id=related-topics)

[Using Screenlets in Your Android Project](/tutorials/-/knowledge_base/6-2/using-screenlets-in-your-android-project)

[Architecture of Liferay Screens for Android](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android)

[Creating Views in Liferay Screens for Android](/tutorials/-/knowledge_base/6-2/creating-views-in-liferay-screens-for-android)

[Liferay Screens Overview](/tutorials/-/knowledge_base/6-2/liferay-screens-overview)
