# Creating the Screenlet Class [](id=creating-the-screenlet-class)

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
these variables. Also note the constructors call `BaseScreenlet`'s constructors: 

    public class AddBookmarkScreenlet extends 
        BaseScreenlet<AddBookmarkViewModel, AddBookmarkInteractor>
        implements AddBookmarkListener {

        private long folderId;
        private AddBookmarkListener listener;

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
            return folderId;
        }

        public void setFolderId(long folderId) {
            this.folderId = folderId;
        }

        public AddBookmarkListener getListener() {
            return listener;
        }

        public void setListener(AddBookmarkListener listener) {
            this.listener = listener;
        }

        ...

Next, implement the Screenlet's listener methods. This lets the Screenlet class 
receive the server call's results and thus act as the listener. These methods 
should communicate the server call's results to the View (via the View Model) 
and any other listener instances (via the Screenlet class's listener instance). 
For example, here are Add Bookmark Screenlet's listener method implementations:

    public void onAddBookmarkSuccess() {
        getViewModel().showFinishOperation(null);

        if (listener != null) {
            listener.onAddBookmarkSuccess();
        }
    }

    public void onAddBookmarkFailure(Exception e) {
        getViewModel().showFailedOperation(null, e);

        if (listener != null) {
            listener.onAddBookmarkFailure(e);
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

            folderId = castToLong(typedArray.getString(R.styleable.AddBookmarkScreenlet_folderId));

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
