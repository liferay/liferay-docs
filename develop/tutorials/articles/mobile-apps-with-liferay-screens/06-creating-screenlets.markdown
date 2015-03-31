# Creating Screenlets

Liferay Screens' built-in screenlets cover many common use cases in mobile 
apps that connect to Liferay. For example, you can use them to authenticate 
users, interact with Dynamic Data Lists, and view assets. However, what if 
there's not a screenlet for *your* specific use case? No sweat! You can write 
your own screenlet. Screenlets can also be written by others and contributed to 
the Screens project. This extensibility is one of Screens' key strengths. 

By walking you through the creation of an example bookmarks screenlet, this 
tutorial shows you how to create your own screenlets in Liferay Screens for 
Android and iOS. Before proceeding, you may want to read the tutorial 
[Architecture of Liferay Screens](https://www.liferay.com/) to understand in 
detail the concepts underlying screenlets. You may also want to read the 
tutorial [Creating Views and Themes](https://www.liferay.com/) to support your 
new screenlet from the view or theme that you want to use. Without any further 
ado, let the screenlet creation begin!

## Creating Screenlets in Liferay Screens for Android

In this section, you'll create an example bookmarks screenlet for Android. This 
screenlet needs to let the user enter a URL and title for the bookmark in a text 
box. When the user touches the submit button, the URL and title are sent to the 
Liferay instance's Bookmark service to be saved. Implement this by using the 
following steps:

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
    create this class in the next step.

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

    ![An app based on Liferay Screens.](../../images/screens-android-add-bookmark-view.png)

3. Create a new custom view class called `AddBookmarkDefaultView`, that extends 
    `LinearLayout` and implements `AddBookmarkViewModel`. This new class is 
    where you implement the UI using the layout XML file from the previous step.

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

4. In the `onFinishInflate` method, get the reference to the components. Then 
    complete the getters and setters using the inner value of the components:

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

5. Create the interactor class. It's responsible for sending the bookmark to the 
    Liferay instance (or any other backend). Note that it's a good practice to 
    use [IoC](http://en.wikipedia.org/wiki/Inversion_of_control) in your 
    interactor classes. This way, anyone can provide a different implementation 
    without breaking the code. The `Interactor` base class also needs a 
    parameter that represents the type of listener to notify. This is defined 
    here:

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

    Pay special attention to the second step in the `addBookmark` method. When 
    the request ends, make sure you post an event into the bus using 
    `EventBusUtil.post(event)`, where `event` is a `BasicEvent` object 
    containing the `targetScreenletId` together with either the result or the 
    exception. Every interactor should also implement the `onEvent` method. This 
    method is invoked by the `EventBus` and calls the registered listener.

6. Once your interactor is ready, you need to create the screenlet class. This 
    is the cornerstone and entry point that your app developer sees and 
    interacts with. In this example, this class is called `AddBookmarkScreenlet` 
    and extends from `BaseScreenlet`. Again, this class needs to be 
    parameterized with the interactor class. Since the screenlet is notified by 
    the interactor when the asynchronous operation ends, you must implement the 
    listener interface used by the interactor (`AddBookmarkListener`, in this 
    case). Also, to notify the app, this class usually has another listener. 
    This listener can be the same one you used in the interactor or a different 
    one altogether (if you want different methods or signatures). You could even 
    notify the app using a different mechanism such as the Event Bus, Android's 
    `BroadcastReceiver`, or others.  Note that the implemented interface methods 
    call the view to modify the UI and the app's listener to allow the app to 
    perform any action:

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

7. You're almost finished! The next step is to implement the screenlet's 
    abstract methods. First is the `createScreenletView` method. In this method 
    you get attributes from the XML definition and either store them as class 
    attributes or otherwise make use of them. Then inflate the view using the 
    layout specified in the `liferay:layoutId` attribute. You can even configure 
    the initial state of the view, using the attributes read.

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

    The Second abstract method to implement is `createInteractor`. This is a 
    factory method in which you have to create the corresponding interactor for 
    a specific action name. Note that a single screenlet may have several 
    interactions (use cases). Each interaction should therefore be implemented 
    in a separate interactor. In this example there is only one interactor, so 
    the object is created in the method. Alternatively, you can retrieve the 
    instance by using your IoC framework. Also, you need to pass the 
    `screenletId` (a number autogenerated by the `BaseScreenlet` class) to the 
    constructor:

        protected AddBookmarkInteractor createInteractor(String actionName) {
            return new AddBookmarkInteractorImpl(getScreenletId());
        }

    The third and final abstract method to implement is `onUserAction`. In this 
    method, retrieve the data entered in the view and start the operation by using the supplied interactor and the data:
    
        protected void onUserAction(String userActionName, AddBookmarkInteractor interactor) {
            AddBookmarkViewModel viewModel = (AddBookmarkViewModel) getScreenletView();
            String url = viewModel.getURL();	
            String title = viewModel.getTitle();
        
            interactor.addBookmark(url, title);
        }

8. The only thing left to do is to trigger the user action when the button is 
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

9. Celebrate! You're done! Now you can use the screenlet as you would any other.

Congratulations! By creating an example bookmarks screenlet, you now know how to 
create screenlets in Screens for Android. The next section covers screenlet 
creation in Screens for iOS.

## Creating Screenlets in Liferay Screens for iOS

In this section, you'll create an example bookmarks screenlet for iOS that has 
the following features: 

- Allows entry of a URL in a text box. 
- Checks if the URL is valid and extract its title value. 
- Shows a preview image and title for user confirmation. 
- Allows the user to modify the title. 
- Upon user confirmation, the URL and title is sent back to the Liferay 
  instance's Bookmark services to be saved.

Use these steps to implement this screenlet:

1. Create a new `xib` called `BookmarkView_default.xib`. You'll build your UI 
    here using Interface Builder. Put in two text box fields (`UITextField`) for 
    the URL and title. Also, add a couple of buttons to let the user retrieve 
    the title and save the bookmark. To differentiate between these two user 
    actions, assign a value for the `restorationIdentifier` property in each 
    button.

    ![The new `xib` file for the new screenlet.](../../images/screens-ios-xcode-add-bookmark.png)

2. Create a new interface (protocol) called `BookmarkViewModel`. The associated 
    attributes are `url` and `title`.

    ![The new view model for the new screenlet.](../../images/screens-ios-xcode-bookmark-viewmodel.png)

3. Create a new class called `BookmarkView_default` that extends 
    `BaseScreenletView` and conforms `BookmarkViewModel`. It must wire all UI 
    components and events from the `xib` using the standard `@IBOutlet` and 
    `@IBAction`. Getters and setters from `BookmarkViewModel` should, 
    respectively, get and set the data from UI components. Also be sure to write 
    any animations or front end code here.

4. Set `BookmarkView_default` as the custom class of your 
    `BookmarkView_default.xib` file.

5. Create a class called `BookmarkScreenlet` that extends `BaseScreenlet`.

6. Optionally, you can add any `@IBInspectable` property to configure the 
    behavior. For example, you could use a boolean property to configure whether 
    the user can save broken URLs.

7. Override the `onUserAction` method so that it receives both button actions. 
    Use the `name` parameter to differentiate between the following actions. 
    These strings should be set in the `Restoration ID` attribute of the trigger 
    component (for example, `UIButton` or others):

    - `"check"`: Occurs when the "Add bookmark" button is touched. 
    - `"save"`: Occurs when the "Confirm" button is touched.

    ![The restoration ID for trigger components.](../../images/screens-ios-xcode-restoration-id.png)

8. Write two operation classes that extend `ServerOperation`. Override the 
    `doRun` method to perform the operation. Also override the `validateData` 
    method to check if the data stored in `BookmarkViewModel` is valid. These 
    two operation classes are described here:

    - `GetSiteTitleOperation`: Retrieves content from start of the HTML to the 
      `<title>` tag. This results in the title being extracted from the HTML.
    - `LiferaySaveBookmarkOperation`: Sends the URL and title to the Liferay 
      instance's Bookmark services.

9. In the screenlet's `onUserAction` method, create and start these operations:

    - When `name` argument is `"check"`: Create and start 
      `GetSiteTitleOperation`. The closure specified should get the retrieved 
      title and set it to the associated `BookmarkViewModel` using the 
      `screenletView`. If the operation fails, show the error to the user.
    - When `name` argument is `"save"`: Get the URL and title from 
      `BookmarkViewModel` and create a `LiferaySaveBookmarkOperation` object 
      with these values. Start the operation and set the closure to show the 
      success or failure to the user.

10. You're done! Now you can add your new screenlet to your storyboard as usual 
    and use it as a ready-to-use component.

Great! By creating this example bookmarks screenlet, now you know how to create 
screenlets for use in Screens for iOS. 

## Related Topics

[Using Screenlets](https://www.liferay.com/)

[Architecture of Liferay Screens](https://www.liferay.com/)

[Creating Views and Themes](https://www.liferay.com/)

[Liferay Screens Overview](https://www.liferay.com/)
