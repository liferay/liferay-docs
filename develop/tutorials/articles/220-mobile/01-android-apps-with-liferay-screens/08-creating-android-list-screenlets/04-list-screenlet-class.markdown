# Creating the Screenlet Class [](id=creating-the-screenlet-class-0)

Recall from 
[the basic Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/7-1/creating-the-screenlet-class) 
that the Screenlet class serves as your Screenlet's focal point. It governs the 
Screenlet's behavior and is the primary component the app developer interacts 
with. As with non-list Screenlets, you should first define any XML attributes 
that you want to make available to the app developer. For example, Bookmark List 
Screenlet defines the following attributes: 

- `groupId`: the ID of the site containing the Bookmarks portlet 
- `folderId`: the ID of the Bookmarks portlet folder to retrieve bookmarks from 
- `comparator`: the name of the comparator to use to sort the bookmarks 

The Screenlet defines these attributes in its `res/values/bookmark_attrs.xml` 
file: 

    <?xml version="1.0" encoding="utf-8"?>
    <resources>
        <declare-styleable name="BookmarkListScreenlet">
            <attr name="groupId"/>
            <attr name="folderId"/>
            <attr format="string" name="comparator"/>
        </declare-styleable>
    </resources>

Now you're ready to create your Screenlet class. Because 
[the `BaseListScreenlet` class](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListScreenlet.java) 
provides the basic functionality for all Screenlet classes in list Screenlets, 
including methods for pagination and other default behavior, your Screenlet 
class must extend `BaseListScreenlet` with your model class and Interactor as 
type arguments. 

For example, Bookmark List Screenlet's Screenlet 
class--`BookmarkListScreenlet`--extends `BaseListScreenlet` parameterized with 
`Bookmark` and `BookmarkListInteractor`: 

    public class BookmarkListScreenlet 
        extends BaseListScreenlet<Bookmark, BookmarkListInteractor> {...

You must also create instance variables for the XML attributes that you want to 
pass to your Interactor. For example, recall that the request methods in 
`BookmarkListInteractor` receive two `Object` arguments: the folder ID and the 
comparator. The `BookmarkListScreenlet` class must therefore contain variables 
for these parameters so it can pass them to the Interactor: 

    private long folderId;
    private String comparator;

For constructors, leverage the superclass constructors. For example, here are 
`BookmarkListScreenlet`'s constructors: 

    public BookmarkListScreenlet(Context context) {
        super(context);
    }

    public BookmarkListScreenlet(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BookmarkListScreenlet(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BookmarkListScreenlet(Context context, AttributeSet attrs, int defStyleAttr, 
        int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
    }

Now you must implement the `error` method. This is a boilerplate method that 
uses a listener in the Screenlet framework to propagate any exception, and the 
user action that produced it, that occurs during the service call. This method 
does this by checking for a listener and then calling its `error` method with 
the `Exception` and `userAction`: 

    @Override
    public void error(Exception e, String userAction) {
        if (getListener() != null) {
            getListener().error(e, userAction);
        }
    }

Next, override the `createScreenletView` method to read the values of the XML 
attributes you defined earlier and create the Screenlet's View. Recall from 
[the basic Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/7-1/creating-the-screenlet-class) 
that this method assigns the attribute values to their corresponding instance 
variables. For example, the `createScreenletView` method in 
`BookmarkListScreenlet` assigns the `folderId` and `comparator` attribute values 
to variables of the same name. This method also sets the local variable 
`groupId`. Recall that the Screens framework propagates this variable to your 
Interactor. Finish the `createScreenletView` method by calling the superclass's 
`createScreenletView` method. This instantiates the View for you: 

    @Override
    protected View createScreenletView(Context context, AttributeSet attributes) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attributes, 
            R.styleable.BookmarkListScreenlet, 0, 0);
        groupId = typedArray.getInt(R.styleable.BookmarkListScreenlet_groupId, 
            (int) LiferayServerContext.getGroupId());
        folderId = typedArray.getInt(R.styleable.BookmarkListScreenlet_folderId, 0);
        comparator = typedArray.getString(R.styleable.BookmarkListScreenlet_comparator);
        typedArray.recycle();

        return super.createScreenletView(context, attributes);
    }

Next, override the `loadRows` method to start your Interactor and thereby 
retrieve the list rows from the server. This method takes an instance of your 
Interactor as an argument, which you use to call the Interactor's `start` 
method. Note that the Interactor inherits `start` from `BaseListInteractor`. You 
can also use the `loadRows` method to execute any other code that you want to 
run when the Interactor starts. For example, the `loadRows` method in 
`BookmarkListScreenlet` first retrieves a listener instance so it can call the 
listener's `interactorCalled` method. It then starts the server operation to 
retrieve the list rows by calling the Interactor's `start` method with 
`folderId` and `comparator`: 

    @Override
    protected void loadRows(BookmarkListInteractor interactor) {

        ((BookmarkListListener) getListener()).interactorCalled();

        interactor.start(folderId, comparator);
    }

Note that if your Interactor doesn't require arguments, then you can pass the 
`start` method `0` or `null`. Calling `start` with no arguments, however, causes 
the server call to fail. 

Lastly, override the `createInteractor` method to instantiate your Interactor. 
Since that's all this method needs to do, call your Interactor's constructor and 
return the new instance. For example, `BookmarkListScreenlet`'s 
`createInteractor` method returns a new `BookmarkListInteractor`: 

    @Override
    protected BookmarkListInteractor createInteractor(String actionName) {
        return new BookmarkListInteractor();
    }

You're done! Your Screenlet is a ready-to-use component that you can use in your 
app. You can even 
[package your Screenlet](/develop/tutorials/-/knowledge_base/7-1/packaging-your-screenlets)
and contribute it to the Screens project, or distribute it in Maven Central or 
jCenter. 

## Related Topics [](id=related-topics)

[Creating the Model Class](/develop/tutorials/-/knowledge_base/7-1/creating-the-model-class)

[Creating the View](/develop/tutorials/-/knowledge_base/7-1/creating-the-view)

[Creating the Interactor](/develop/tutorials/-/knowledge_base/7-1/creating-the-interactor-0)
