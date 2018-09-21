# Creating the UI [](id=creating-the-ui)

In Liferay Screens for Android, Screenlet UIs are called Views. Every Screenlet 
must have at least one View. A View consists of the following components: 

-   **The View Model interface:** Defines the methods the View needs to update 
    the UI. 

-   **A layout XML file:** Defines the UI components that the View presents to 
    the end user.

-   **A View class:** Renders the UI, handles user interactions, and 
    communicates with the Screenlet class. The View class implements the View 
    Model interface. 

-   **The Screenlet class:** Although technically part of a View, the Screenlet 
    class depends on all the other Screenlet components. You therefore won't 
    create the Screenlet class until the end of this tutorial. 

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

![Figure 1: Add Bookmark Screenlet's layout contains two text fields and a button.](../../../../images/screens-android-add-bookmark-view.png)

Next, you'll create your Screenlet's View class. 

## Creating the Screenlet's View Class [](id=creating-the-screenlets-view-class)

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

        private EditText urlText;
        private EditText titleText;
        private BaseScreenlet screenlet;

3.  Implement your View Model interface. Implement your View Model's getter and 
    setter methods to get and set the inner value of each component, 
    respectively. For example, here's `AddBookmarkView`'s implementation of 
    `AddBookmarkViewModel`: 

        public String getURL() {
            return urlText.getText().toString();
        }

        public void setURL(String value) {
            urlText.setText(value);
        }

        public String getTitle() {
            return titleText.getText().toString();
        }

        public void setTitle(String value) {
            titleText.setText(value);
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
    from the layout, and sets them to the `urlText` and `titleText` variables, 
    respectively. This method then retrieves the button from the layout and sets 
    this View class as the button's click listener:
    
        protected void onFinishInflate() {
            super.onFinishInflate();

            urlText = (EditText) findViewById(R.id.url);
            titleText = (EditText) findViewById(R.id.title_bookmark);

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
            return screenlet;
        }

        @Override
        public void setScreenlet(BaseScreenlet screenlet) {
            this.screenlet = screenlet;
        }

    Note that although you must implement the `show[something]Operation`
    methods, you can leave their implementations empty if you don't need to take
    any specific action. 

[Click here](https://github.com/liferay/liferay-screens/blob/master/android/samples/addbookmarkscreenlet/src/main/java/com/liferay/mobile/screens/bookmark/view/AddBookmarkView.java) 
to see the complete example `AddBookmarkView` class. 

Great! Your View class is finished. Now you're ready to create your Screenlet's 
Interactor class. 
