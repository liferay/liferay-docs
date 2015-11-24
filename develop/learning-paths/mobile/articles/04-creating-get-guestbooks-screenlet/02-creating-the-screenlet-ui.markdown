# Creating the Get Guestbook Screenlet's UI [](id=creating-the-get-guestbook-screenlets-ui)

In Liferay Screens for Android, Screenlet UIs are called Views. Every Screenlet 
must have a View. In this article, you'll create the Get Guestbook Screenlet's 
View by following these steps:

1. Create the View Model interface.

2. Create the View's layout XML.

3. Create the View class.

You'll create the Get Guestbook Screenlet's View in its own package inside the 
`getguestbooksscreenlet` package. Get started by creating a new package named 
*view* inside the `getguestbooksscreenlet` package. Now you're ready to create 
the View Model interface.

## Creating the View Model Interface [](id=creating-the-view-model-interface)

The first thing you should do when creating any Screenlet's View is create its 
View Model interface. This interface defines the methods the View class needs to 
control the UI. For example, the View Model interface created in the tutorial 
[Creating Android Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-android-screenlets) 
contains getter and setter methods for the Add Bookmark Screenlet's `url` and 
`title` attributes. Since that Screenlet needs to add a bookmark to Liferay's 
Bookmarks portlet, its View needs those methods so it can get and set the 
bookmark's URL and title from the UI. The Screenlet's View class then implements 
those methods. You need to define an analagous View Model interface for the Get 
Guestbooks Screenlet. 

So what methods should be defined by the Get Guestbooks Screenlet's View Model 
interface? The Get Guestbooks Screenlet's UI doesn't need *any* control 
elements, such as sliders, text boxes, buttons, or the like. It only needs to 
display a guestbook list. Its view model interface therefore only needs to 
define a single method for this purpose. You'll handle list item selections 
separately, by implementing `AdapterView.OnItemClickListener` in the view class. 

Create the `GetGuestbooksViewModel` interface in the `view` package. When the 
interface opens in the editor, change its declaration to extend 
[Screens's `BaseViewModel` interface](https://github.com/liferay/liferay-screens/blob/1.2.0/android/library/core/src/main/java/com/liferay/mobile/screens/base/view/BaseViewModel.java). 
The interface's declaration should now look like this:

    public interface GetGuestbooksViewModel extends BaseViewModel {...

This also requires that you add the following import for `BaseViewModel`:

    import com.liferay.mobile.screens.base.view.BaseViewModel;

Now add the following method signature: 

    void showFinishOperation(String userAction, List<GuestbookModel> guestbooks);

You'll use `showFinishOperation` in the view class to refresh the list of 
guestbooks displayed by the Screenlet. The Screenlet calls this method with the 
list of guestbooks retrieved from the server when the given user action, 
represented by `userAction`, occurs. You might now be thinking, "Hold up! You 
just told me that this Screenlet's UI only displays a guestbook list, and that 
I'll handle list item selections separately. What other actions are there for 
the user to take?" This is an astute observation. The only other action a user 
can take, and the one you're accounting for here, is loading the Screenlet. 

The `showFinishOperation` method requires that you add the following imports to 
the interface. If you haven't added them already, do so now:

    import com.liferay.docs.getguestbooksscreenlet.GuestbookModel;
    import java.util.List;

+$$$

**Note:** Make sure you import `GuestbookModel` from `getguestbooksscreenlet`, 
and not `liferayguestbook.model`.

$$$

Awesome! Now you're ready to create the View's layout XML.

## Creating the View's Layout XML

Before creating the View class, you should create the View's layout XML. This 
defines the Screenlet's UI. Create the file `guestbooks_default.xml` in the 
`res/layout` directory. When the layout file opens, switch to text view and 
replace the file's contents with the following code: 

    <?xml version="1.0" encoding="utf-8"?>
    <com.liferay.docs.getguestbooksscreenlet.view.GetGuestbooksView
        xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

    </com.liferay.docs.getguestbooksscreenlet.view.GetGuestbooksView>

The View class, `GetGuestbooksView`, doesn't exist yet. Don't worry; you'll 
create it next. You might also be wondering why the layout is otherwise empty. 
There's no need to worry about this either; you'll create and handle the 
layout's content in the View class. 

## Creating the View Class [](id=creating-the-view-class)

The View class is the heart of any Screenlet's UI. It renders the UI, handles 
user interactions, and communicates with the Screenlet class. This Screenlet's 
View class must extend `ListView` to get an implicit `ListView` object to serve 
as content for the layout XML you just created. This is why you didn't need to 
put any other content in the layout. You'll use the `ListView` in the View class 
to display the guestbooks programmatically. Note that this is similar to what 
you did in 
[`EntriesFragment` in this Learning Path's previous section](/develop/learning-paths/mobile/-/knowledge_base/6-2/retrieve-and-display-entries#creating-a-fragment-for-the-entries): 
by extending `ListFragment`, you gained an implicit `ListView` that you used to 
display entries programmatically in a list.

The Get Guestbooks Screenlet's View class must also implement 
`GetGuestbooksViewModel` and `AdapterView.OnItemClickListener`. Implementing 
these interfaces lets the View class handle user interactions. Implementing the 
View Model lets the View class refresh the list of guestbooks displayed by the 
Screenlet. Implementing the item click listener lets the View class handle user 
selections of individual guestbooks in the list.

Now it's time to get down to business! Create the `GetGuestbooksView` class 
inside the `view` package. Change the class declaration to extend Android's 
`ListView`, and implement `GetGuestbooksViewModel` and 
`AdapterView.OnItemClickListener`. The class declaration should look like this: 

    public class GetGuestbooksView extends ListView 
        implements GetGuestbooksViewModel, AdapterView.OnItemClickListener {...

This also requires that you add the following imports. If you haven't already 
done so, add them now:

    import android.widget.ListView;
    import android.widget.AdapterView;

Before implementing the interfaces, you need to declare a variable to hold the 
guestbooks retrieved from the Guestbook portlet. Since the Screenlet converts 
each guestbook from a `JSONObject` to a `GuestbookModel` object, define the 
variable as a list of `GuestbookModel` objects: 

    private List<GuestbookModel> _guestbooks = new ArrayList<>();

You must also add the following imports:

    import com.liferay.docs.getguestbooksscreenlet.GuestbookModel;
    import java.util.ArrayList;
    import java.util.List;

Now you need to create the `GetGuestbooksView` class's constructors. You can 
leverage `ListView`'s constructors for this. In the constructor 
[that takes a `Context`, `AttributeSet`, and `defaultStyle` parameter](http://developer.android.com/reference/android/widget/ListView.html#ListView(android.content.Context, android.util.AttributeSet, int)), 
you must create and set an adapter for the `ListView`, and then set the item 
click listener. Add the following constructors to the `GetGuestbooksView` class: 

    public GetGuestbooksView(Context context) {
        this(context, null);
    }

    public GetGuestbooksView(Context context, AttributeSet attributes) {
        this(context, attributes, 0);
    }

    public GetGuestbooksView(Context context, AttributeSet attributes, int defaultStyle) {
        super(context, attributes, defaultStyle);

        ArrayAdapter adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                _guestbooks
        );
        setAdapter(adapter);
        setOnItemClickListener(this);
    }

The adapter in the last constructor should look familiar: 
[it's effectively the same one you used earlier](/develop/learning-paths/mobile/-/knowledge_base/6-2/retrieve-and-display-guestbooks#displaying-guestbooks-in-the-drawer). 
It displays a single line of text for each list item by using the built-in 
Android layout `simple_list_item_activated_1`. After setting the adapter to the 
implicit `ListView`, the constructor finishes by using the `AdapterView` class's 
`setOnItemClickListener` method to set `GetGuestbooksView` as the `ListView`'s 
click listener. 

These constructors also require that you add the following imports. If you 
haven't already done so, add them now:

    import android.content.Context;
    import android.util.AttributeSet;
    import android.widget.ArrayAdapter;

You must now implement the methods defined in the interfaces  
[`BaseViewModel`](https://github.com/liferay/liferay-screens/blob/1.2.0/android/library/core/src/main/java/com/liferay/mobile/screens/base/view/BaseViewModel.java) 
and `GetGuestbooksViewModel`. Screens's `BaseViewModel` interface defines the 
following three methods. These methods exist so you can provide the 
functionality listed here: 

- `public void showStartOperation(String actionName)`: Called by the Screenlet 
  when the server operation begins.

- `public void showFinishOperation(String actionName)`: Called by the Screenlet 
  when the server operation finishes.

- `public void showFailedOperation(String actionName, Exception e)`: Called by 
  the Screenlet if the server operation fails.

In many cases, however, you don't need to put anything in these methods when you 
implement them. For example, in the Get Guestbooks Screenlet you're only 
interested in what happens when the operation finishes with a list of 
guestbooks, or finishes with an error. You can therefore leave 
`showStartOperation(String actionName)` and 
`showFinishOperation(String actionName)` blank. You defined the 
`showFinishOperation` method you need, the one that takes a list of guestbooks 
as a parameter, in the `GetGuestbooksViewModel` interface. In this method's 
implementation, you must refresh the list of guestbooks in `_guestbooks` and 
notify the `ListView`'s adapter of the change. You'll do this the same way you 
did in the `reloadGuestbooks` method when you 
[used the Mobile SDK to retrieve and display guestbooks](/develop/learning-paths/mobile/-/knowledge_base/6-2/retrieve-and-display-guestbooks). 
If the operation to retrieve guestbooks fails, you should use the 
`showFailedOperation` method to log and display the error to the user. You 
can do this with Screens's `LiferayLogger` and `LiferayCrouton` helper classes. 
Add these method implementations to `GetGuestbooksView` as follows: 

    @Override
    public void showStartOperation(String actionName) {
    }

    @Override
    public void showFinishOperation(String actionName) {
    }

    @Override
    public void showFinishOperation(final String userAction, final List<GuestbookModel> guestbooks) {
        LiferayLogger.i("Got the Guestbooks!");

        _guestbooks.clear();
        _guestbooks.addAll(guestbooks);

        ((ArrayAdapter) getAdapter()).notifyDataSetChanged();
    }

    @Override
    public void showFailedOperation(String actionName, Exception e) {
        LiferayLogger.e("Could not get Guestbooks", e);
        LiferayCrouton.error(getContext(), "Could not get Guestbooks", e);
    }

You must also add the following imports for `LiferayLogger` and 
`LiferayCrouton`: 

    import com.liferay.mobile.screens.util.LiferayLogger;
    import com.liferay.mobile.screens.viewsets.defaultviews.LiferayCrouton;

Next, you must handle list item selections by implementing the `onItemClick` 
method from `AdapterView.OnItemClickListener`. When you used the Mobile SDK 
directly, you set the navigation drawer containing the guestbooks to close when 
a guestbook is selected, revealing that guestbook's entries. You'll do the same 
thing here, but not in the Screenlet. You should create Screenlets so they can 
be used in a wide variety of UIs. Unless you have a very good reason for doing 
so, you shouldn't force developers to use your Screenlet with a specific UI like 
a navigation drawer. So instead of implementing a navigation drawer and its 
behavior as part of the Get Guestbooks Screenlet, you'll respond to list item 
selections by passing the selected guestbook to the activity or fragment the 
Screenlet is used from. This lets the app developer respond to a guestbook 
selection as they wish. You'll do this by calling the Screenlet class's 
`onItemClicked` method. Currently, however, there's a major problem with this 
approach: the Screenlet class doesn't exist yet. You can ignore this for now; 
you'll create it later. Implement `AdapterView.OnItemClickListener` now by 
adding the following `onItemClick` method to the `GetGuestbooksView` class: 

    @Override
    public void onItemClick(final AdapterView<?> parent, final View view, 
        final int position, final long id) {
        
        setItemChecked(position, true);
        // TODO: Call GetGuestbooksScreenlet's onItemClicked method
    }

In this method, you first use `setItemChecked` to highlight the selected item in 
the `ListView`. The *TODO* serves as a nice reminder to come back here after 
creating the Screenlet class. Also, make sure you add the following import: 

    import android.view.View;

Great! With the exception of the *TODO* in `onItemClick`, you're done with the 
Get Guestbooks Screenlet's View. Next, you need to write the classes that issue 
and process the server calls. 
