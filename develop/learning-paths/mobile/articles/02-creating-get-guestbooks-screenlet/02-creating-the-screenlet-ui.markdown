# Creating the Get Guestbook Screenlet's UI [](id=creating-the-get-guestbook-screenlets-ui)

In Liferay Screens for Android, Screenlet UIs are called Views. Every Screenlet 
must have at least one View. In this article, you'll create the Get Guestbook 
Screenlet's default View by following these steps:

1. Create the View Model interface. This interface defines the methods the View 
   class needs to control the UI.

2. Create the View's layout XML. This layout defines the Screenlet's UI.

3. Create the View class. This class renders the UI, handles user interactions, 
   and communicates with the Screenlet class.

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
Bookmarks portlet, its View class needs those methods so it can get and set the 
bookmark's URL and title in the UI. You'll define an analogous View Model 
interface for Get Guestbooks Screenlet. 

So what methods should the Get Guestbooks Screenlet's View Model interface 
define? The Get Guestbooks Screenlet's UI doesn't need *any* control elements, 
such as sliders, text boxes, buttons, or the like. It only needs to display a 
guestbook list. Its View Model interface therefore only needs to define a single 
method for this purpose. You'll handle list item selections separately, by 
implementing `AdapterView.OnItemClickListener` in the view class. 

Create the `GetGuestbooksViewModel` interface in the `view` package. When the 
interface opens in the editor, change its declaration to extend 
[Screens's `BaseViewModel` interface](https://github.com/liferay/liferay-screens/blob/1.3.0/android/library/core/src/main/java/com/liferay/mobile/screens/base/view/BaseViewModel.java). 
The interface's declaration should now look like this:

    public interface GetGuestbooksViewModel extends BaseViewModel {...

This also requires that you import 
`com.liferay.mobile.screens.base.view.BaseViewModel`. 

Now add the following method signature: 

    void showFinishOperation(String userAction, List<GuestbookModel> guestbooks);

This method requires that you import `com.liferay.docs.model.GuestbookModel` and 
`java.util.List`. You'll use `showFinishOperation` in the View class to refresh 
the list of guestbooks displayed by the Screenlet. The Screenlet calls this 
method with the list of guestbooks retrieved from the server when the given user 
action, represented by `userAction`, occurs. You might now be thinking, "Hold 
up! You just told me that this Screenlet's UI only displays a guestbook list, 
and that I'll handle list item selections separately. What other actions are 
there for the user to take?" This is an astute observation. The only other 
action a user can take, and the one you account for here, is loading the 
Screenlet. 

Awesome! Now you're ready to create the View's layout XML. 

## Creating the View's Layout XML [](id=creating-the-views-layout-xml)

Before creating the View class, you should create the View's layout XML. This 
defines the Screenlet's UI. Create the file `guestbooks_default.xml` in the 
`res/layout` directory. When this layout file opens, switch to text view and 
replace its contents with the following code: 

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
layout's content dynamically in the View class. 

## Creating the View Class [](id=creating-the-view-class)

The View class is the central hub of any Screenlet's UI. It renders the UI, 
handles user interactions, and communicates with the Screenlet class. Since you 
want to display guestbooks in a list, Get Guestbooks Screenlet's View class 
should extend [`ListView`](http://developer.android.com/reference/android/widget/ListView.html). 
This gives the View class an implicit `ListView` object to serve as content for 
the layout XML you just created. This is why you didn't need to define any other 
content in the layout. You'll use the View class's `ListView` to display the 
guestbooks programmatically in a list. 

Get Guestbooks Screenlet's View class must also implement 
`GetGuestbooksViewModel` and `AdapterView.OnItemClickListener`. Implementing 
these interfaces lets the View class handle user interactions. Implementing the 
View Model lets the View class load and refresh the list of guestbooks the 
Screenlet displays. Implementing the item click listener lets the View class 
handle selections of individual guestbooks in the list. 

Now it's time to get down to business! Create the `GetGuestbooksView` class 
inside the `view` package. Change the class declaration to extend Android's 
`ListView`, and implement `GetGuestbooksViewModel` and 
`AdapterView.OnItemClickListener`. The class declaration should look like this: 

    public class GetGuestbooksView extends ListView 
        implements GetGuestbooksViewModel, AdapterView.OnItemClickListener {...

This also requires that you import `android.widget.ListView` and 
`android.widget.AdapterView`. 

Before implementing the interfaces, you must declare a variable for the 
guestbooks retrieved from the Guestbook portlet. Since the Screenlet converts 
each guestbook from `JSONObject` to `GuestbookModel`, you must define the 
variable as a `GuestbookModel` list. You must also declare a `BaseScreenlet` 
variable to hold a reference to the Screenlet. Add these variables now:

    private List<GuestbookModel> _guestbooks = new ArrayList<>();
    private BaseScreenlet _screenlet;

After creating these variables, you must also add the following imports:

    import com.liferay.docs.liferayguestbook.model.GuestbookModel;
    import com.liferay.mobile.screens.base.BaseScreenlet;
    import java.util.ArrayList;
    import java.util.List;

Now you must create `GetGuestbooksView`'s constructors. You can leverage 
`ListView`'s constructors for this. In the `ListView` constructor 
[that takes a `Context`, `AttributeSet`, and `defaultStyle` parameter](http://developer.android.com/reference/android/widget/ListView.html#ListView(android.content.Context, android.util.AttributeSet, int)), 
you must create and set the `ListView`'s 
[adapter](http://developer.android.com/guide/topics/ui/declaring-layout.html#AdapterViews) 
and then set the item click listener. Add the following constructors to the 
`GetGuestbooksView` class: 

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

These constructors also require that you add the following imports. If you 
haven't already done so, add them now:

    import android.content.Context;
    import android.util.AttributeSet;
    import android.widget.ArrayAdapter;

The adapter in the last constructor is an 
[`ArrayAdapter`](http://developer.android.com/reference/android/widget/ArrayAdapter.html) 
that uses the built-in Android layout `simple_list_item_activated_1` to display 
a single line of text for each list item. Note that `_guestbooks` is the 
last argument to the adapter's constructor. This sets the `GuestbookModel` list 
as the adapter's data source. The `setAdapter` method then sets the adapter to 
the implicit `ListView`. The constructor finishes by using the `AdapterView` 
class's `setOnItemClickListener` method to set `GetGuestbooksView` as the 
`ListView`'s click listener. 

Next, you must implement the methods defined in the 
[`BaseViewModel`](https://github.com/liferay/liferay-screens/blob/1.3.0/android/library/core/src/main/java/com/liferay/mobile/screens/base/view/BaseViewModel.java) 
interface. This interface defines the following methods: 

- `public void showStartOperation(String actionName)`: Called by the Screenlet 
  when the server operation begins. 

- `public void showFinishOperation(String actionName)`: Called by the Screenlet 
  when the server operation finishes. 

- `public void showFailedOperation(String actionName, Exception e)`: Called by 
  the Screenlet if the server operation fails. 

- `BaseScreenlet getScreenlet()`: Gets the View's Screenlet reference. 

- `void setScreenlet(BaseScreenlet screenlet)`: Sets the View's Screenlet 
  reference. 

When you implement the `show*Operation` methods, you should include any code you 
want to run when the methods are called. In many cases, you can leave these 
methods empty. For example, in Get Guestbooks Screenlet you're only interested 
in what happens when the operation finishes with a list of guestbooks or an 
error. You can therefore leave `showStartOperation` and 
`showFinishOperation(String actionName)` blank. You can use the 
`showFailedOperation` method, however, to log the error and display it to the 
user. You can do this with Screens's `LiferayLogger` and `LiferayCrouton` helper 
classes. When implementing the get and set methods for `BaseScreenlet`, you must 
get and set `_screenlet`, respectively. This ensures that the View always has a 
reference to the Screenlet. 

Add these methods now:

    @Override
    public void showStartOperation(String actionName) {
    }

    @Override
    public void showFinishOperation(String actionName) {
    }
    
    @Override
    public void showFailedOperation(String actionName, Exception e) {
        LiferayLogger.e("Could not get Guestbooks", e);
        LiferayCrouton.error(getContext(), "Could not get Guestbooks", e);
    }
    
    @Override
    public BaseScreenlet getScreenlet() {
        return _screenlet;
    }

    @Override
    public void setScreenlet(BaseScreenlet screenlet) {
        _screenlet = screenlet;
    }

You must also add the `LiferayLogger` and `LiferayCrouton` imports: 

    import com.liferay.mobile.screens.util.LiferayLogger;
    import com.liferay.mobile.screens.viewsets.defaultviews.LiferayCrouton;

Now you must implement `GetGuestbooksViewModel`. Recall that this interface 
defines only a single method: the `showFinishOperation` method that takes a user 
action and `GuestbookModel` list as parameters. The Screenlet calls this method 
when the server call finishes successfully. In this method's implementation, you 
must refresh the guestbooks in `_guestbooks` and notify the `ListView`'s adapter 
of this change. To refresh the guestbooks, you'll clear `_guestbooks` of any 
content and then populate it with the most recent guestbooks from the server. To 
notify the adapter of this change, you'll use `getAdapter()` to retrieve the 
adapter, and then call `notifyDataSetChanged()`. Add this `showFinishOperation` 
method implementation now: 

    @Override
    public void showFinishOperation(final String userAction, final List<GuestbookModel> guestbooks) {
        LiferayLogger.i("Got the Guestbooks!");

        _guestbooks.clear();
        _guestbooks.addAll(guestbooks);

        ((ArrayAdapter) getAdapter()).notifyDataSetChanged();
    }

Now you must handle list item selections by implementing the 
`AdapterView.OnItemClickListener` interface's `onItemClick` method. In your 
app's UI, you want the navigation drawer containing the guestbooks to close when 
a guestbook is selected in the list, revealing that guestbook's entries. You 
shouldn't, however, implement this behavior in the Screenlet's UI. You should 
create Screenlets so they can be used in a wide variety of UIs. Unless you have 
a very good reason for doing so, you shouldn't force developers to use your 
Screenlet with a specific UI like a navigation drawer. So instead of 
implementing a navigation drawer and its behavior as part of Get Guestbooks 
Screenlet, you'll respond to list item selections by passing the selected 
guestbook to the activity or fragment the Screenlet is used from. This lets the 
app developer respond to a guestbook selection as they wish. You'll do this by 
calling the Screenlet class's `onItemClicked` method. Currently, however, 
there's a problem with this approach: the Screenlet class doesn't exist yet. You 
can ignore this for now; you'll create it later. Implement 
`AdapterView.OnItemClickListener` now by adding the following `onItemClick` 
method to the `GetGuestbooksView` class: 

    @Override
    public void onItemClick(final AdapterView<?> parent, final View view, 
        final int position, final long id) {
        
        setItemChecked(position, true);
        // TODO: Call GetGuestbooksScreenlet's onItemClicked method
    }

This method requires that you import `android.view.View`. In this method, you 
first use `setItemChecked` to highlight the selected item in the `ListView`. The 
*TODO* serves as a nice reminder to come back here after creating the Screenlet 
class. 

Great! With the exception of the *TODO* in `onItemClick`, you're done with Get 
Guestbooks Screenlet's View. You're almost ready to write the Screenlet's server 
call. Because Screenlets use the Mobile SDK to make server calls, you must first 
learn how Mobile SDK calls work. This is shown next. 
