# Creating Screenlets in Liferay Screens for Android [](id=creating-screenlets-in-liferay-screens-for-android)

Liferay Screens' built-in screenlets cover many common use cases in mobile 
apps that connect to Liferay. For example, you can use them to authenticate 
users, interact with Dynamic Data Lists, and view assets. However, what if 
there's not a screenlet for *your* specific use case? No sweat! You can write 
your own screenlet. Screenlets can also be written by others and contributed to 
the Screens project. This extensibility is one of Screens' key strengths. 

This tutorial shows you how to create your own screenlets in Liferay Screens for 
Android. You'll create a bookmarks screenlet that lets the user enter the 
bookmark's URL and title in text boxes. When the user touches the submit button, 
the URL and title are sent to the Liferay instance's Bookmark service to be 
saved. Before proceeding, you may want to read the tutorial 
[Architecture of Liferay Screens for Android](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android) 
to understand in detail the concepts underlying screenlets. You may also want to 
read the tutorial [Creating Views in Liferay Screens for Android](/tutorials/-/knowledge_base/6-2/creating-views-in-liferay-screens-for-android) 
to support your new screenlet from the view that you want to use. Without any 
further ado, let the screenlet creation begin! 

## Creating the Screenlet's UI [](id=creating-the-screenlets-ui)

You should start by creating the screenlet's UI. This is done by creating a new 
interface for the view and then creating a new layout file. Use the following 
steps to do this for the bookmarks screenlet: 

1. Create a new interface called `AddBookmarkViewModel`. This is for adding the 
   attributes to show in the view. In this case, the attributes are `url` and 
   `title`. Any screenlet view must implement this interface.

        public interface AddBookmarkViewModel {
	
            public String getURL();
            public void setURL(String value);
            
            public String getTitle();
            public void setTitle(String value);
	
        }

2. Build your UI using a layout XML file. Put in two `EditText` tags: one for 
   the URL and another for the title. Also, add a `Button` tag to let the user 
   save the bookmark. Note that the root element is a custom class. You'll 
   create this class in the next section.

        <?xml version="1.0" encoding="utf-8"?>
        <com.your.package.AddBookmarkDefaultView 
            xmlns:android="http://schemas.android.com/apk/res/android"
            style="@style/default_screenlet">
        
            <EditText
                android:id="@+id/url"
                android:hint="@string/url_address"
                style="@style/default_edit_text"
                android:inputType="textUri" />
        
            <EditText
                android:id="@+id/title"
                style="@style/default_edit_text"
                android:hint="@string/title"
                android:inputType="textAutoComplete" />
        
            <Button
                android:id="@+id/add_button"
                style="@style/default_submit_button"
                android:text="@string/add_bookmark" />
        
        </com.your.package.AddBookmarkDefaultView>

    At this point, the graphical layout viewer in Android Studio should look 
    like this:

    ![Figure 1: Android Studio's graphical layout viewer while creating your own screenlet.](../../images/screens-android-add-bookmark-view.png)

Next, you'll create a view class for the screenlet.

## Creating the Screenlet's View Class [](id=creating-the-screenlets-view-class)

Your new screenlet now needs a view class to implement the UI you just created. 
Create a new custom view class called `AddBookmarkDefaultView`. This class 
should extend `LinearLayout` and implement `AddBookmarkViewModel`:

    public class AddBookmarkDefaultView
        extends LinearLayout implements AddBookmarkViewModel {
    
        public AddBookmarkDefaultView(Context context) {
            super(context);
        }
    
        public AddBookmarkDefaultView(Context context, AttributeSet attributes) {
            super(context, attributes);
        }
    	
        public AddBookmarkDefaultView(Context context, AttributeSet attributes, int defaultStyle) {
            super(context, attributes, defaultStyle);
        }
    
        // TODO setters and getters have to set/get the data from/to the views
        public String getURL() ... 
        public void setURL(String value) ...
        
        public String getTitle() ...
        public void setTitle(String value) ...
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
represents the listener type to notify. This is defined here: 

    public interface AddBookmarkInteractor extends Interactor<AddBookmarkListener> {
    
        public void addBookmark(String url, String title);
    
    }
    
    public class AddBookmarkInteractorImpl
        extends BaseRemoteInteractor<AddBookmarkListener>
        implements AddBookmarkInteractor {
    
        public AddBookmarkInteractorImpl(int targetScreenletId) {
            super(targetScreenletId);
        }
    
        public void addBookmark(String url, String title) {
            // 1. Validate input
            if (url == null || url.isEmpty()) {
                throw new IllegalArgumentException("Invalid url");
            }
    
        // 2. Call the service asynchronously.
        // Notify when the request ends using the EventBus
        }
    
        public void onEvent(BasicEvent event) {
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
`EventBusUtil.post(event)`. Here, `event` is a `BasicEvent` object containing 
the `targetScreenletId` together with either the result or the exception. Every 
interactor should also implement the `onEvent` method. This method is invoked by 
the `EventBus` and calls the registered listener. 

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
        extends BaseScreenlet<LoginInteractor>
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
    
            AddBookmarkListener view = (AddBookmarkListener) getScreenletView();
            view.onAddBookmarkSuccess();
    
            if (_listener != null) {
                _listener.onAddBookmarkSuccess();
            }
        }
    
        public void onAddBookmarkFailure(Exception e) {
            AddBookmarkListener view = (AddBookmarkListener) getScreenletView();
            view.onAddBookmarkFailure(e);
    
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
attributes to configure the initial state of the view. 

    @Override
    protected View createScreenletView(Context context, AttributeSet attributes) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attributes, 
            R.styleable.AddBookmarkScreenlet, 0, 0);
    
        int layoutId = typedArray.getResourceId(R.styleable.AddBookmarkScreenlet_layoutId, 0);
    
        View view = LayoutInflater.from(context).inflate(layoutId, null);
    
        String defaultTitle = typedArray.getResourceString(
            R.styleable.AddBookmarkScreenlet_defaultTitle, 0);
    
        typedArray.recycle();
    
        AddBookmarkViewModel viewModel = (AddBookmarkViewModel) view;
        viewModel.setTitle(defaultTitle);
    
        return view;
    }

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
    
    protected void onUserAction(String userActionName, AddBookmarkInteractor interactor) {
        AddBookmarkViewModel viewModel = (AddBookmarkViewModel) getScreenletView();
        String url = viewModel.getURL();	
        String title = viewModel.getTitle();
    
        interactor.addBookmark(url, title);
    }

Great! Now you have a screenlet class for the bookmarks screenlet. The final 
step is hooking it up to the rest of the screenlet's code.

## Triggering the User Action [](id=triggering-the-user-action)

The only thing left to do is to trigger the user action when the button is 
pressed. To do this, go back to the view and add a listener to the button:

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

Celebrate! You're done! Now you can use the screenlet as you would any other. 
Congratulations! By creating an example bookmarks screenlet, you now know how to 
create screenlets in Screens for Android. 

## Related Topics [](id=related-topics)

[Using Screenlets in Your Android Project](/tutorials/-/knowledge_base/6-2/using-screenlets-in-your-android-project)

[Architecture of Liferay Screens for Android](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android)

[Creating Views in Liferay Screens for Android](/tutorials/-/knowledge_base/6-2/creating-views-in-liferay-screens-for-android)

[Liferay Screens Overview](/tutorials/-/knowledge_base/6-2/liferay-screens-overview)
