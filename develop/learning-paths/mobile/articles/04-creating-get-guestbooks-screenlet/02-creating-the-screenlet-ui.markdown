# Creating the Get Guestbook Screenlet's UI [](id=creating-the-get-guestbook-screenlets-ui)

In Liferay Screens for Android, Screenlet UIs are called Views. You'll create 
the Get Guestbook Screenlet's View in its own package inside the 
`getguestbooksscreenlet` package. Get started by creating a new package named 
*view* inside the `getguestbooksscreenlet` package. Now you're ready to create 
the *View Model* interface.

## Creating the View Model Interface [](id=creating-the-view-model-interface)

The first thing you should do when creating a Screenlet's View is create its 
View Model interface. This interface defines the methods the View class needs to 
control the UI. You'll implement this interface when creating the View class. 

Create the `GetGuestbooksViewModel` interface in the `view` package. When the 
interface opens in the editor, change its declaration to extend 
[Screens's `BaseViewModel` interface](https://github.com/liferay/liferay-screens/blob/1.2.0/android/library/core/src/main/java/com/liferay/mobile/screens/base/view/BaseViewModel.java). 
The interface's declaration should now look like this:

    public interface GetGuestbooksViewModel extends BaseViewModel {...

Now add the signatures of any methods needed by the Screenlet's view class. The 
Get Guestbooks Screenlet only needs one. Add it to the View Model interface now: 

    void showFinishOperation(String userAction, List<GuestbookModel> guestbooks);

You'll use this method to refresh the list of guestbooks displayed by the 
Screenlet. Also, check to make sure that you have the following imports in the 
interface. If you don't, add them now: 

    import com.liferay.docs.getguestbooksscreenlet.GuestbookModel;
    import com.liferay.mobile.screens.base.view.BaseViewModel;
    import java.util.List;

+$$$

**Note:** Make sure you import `GuestbookModel` from `getguestbooksscreenlet`, 
and not `liferayguestbook.model`.

$$$

Awesome! Now you're ready to create the View's layout XML.

## Creating the View's Layout [](id=creating-the-views-layout)

Before creating the View class, you should create the View's layout XML. Create 
the file `guestbooks_default.xml` in the `res/layout` directory. When the layout 
file opens, switch to text view and replace the file's contents with the 
following code: 

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
layout's content in the `GetGuestbooksView` class. 

## Creating the View Class [](id=creating-the-view-class)

Create the `GetGuestbooksView` class inside the `view` package. Change the class 
declaration to extend Android's `ListView`, and implement 
`GetGuestbooksViewModel` and `AdapterView.OnItemClickListener`. The class 
declaration should now look like this: 

    public class GetGuestbooksView extends ListView 
        implements GetGuestbooksViewModel, AdapterView.OnItemClickListener {...

Extending `ListView` gives `GetGuestbooksView` an implicit `ListView` object 
that serves as the content for the layout XML you just created. This is why you 
didn't need to put any other content in the layout. 

Implementing the interfaces `GetGuestbooksViewModel` and 
`AdapterView.OnItemClickListener` lets `GetGuestbooksView` control the 
Screenlet's UI. Recall that `GetGuestbooksViewModel` defines the signature of 
the `showFinishOperation` method that refreshes the Screenlet's list of 
guestbooks (you'll implement this method shortly). Implementing 
`AdapterView.OnItemClickListener` lets the app developer respond to the 
Screenlet's list item clicks. 

Before implementing these interfaces, you need to declare a variable to hold the 
guestbooks retrieved from the Guestbook portlet. Since the Screenlet converts 
each guestbook from a `JSONObject` to a `GuestbookModel` object, define the 
variable as a list of `GuestbookModel` objects: 

    private List<GuestbookModel> _guestbooks = new ArrayList<>();

Next, add the `GetGuestbooksView` class's constructors as follows:

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
After setting the adapter to the implicit `ListView`, the constructor finishes 
by using the `AdapterView` class's `setOnItemClickListener` method to set 
`GetGuestbooksView` as the `ListView`'s click listener. 

Now you need to implement the methods defined in the interfaces 
`GetGuestbooksViewModel` and 
[`BaseViewModel`](https://github.com/liferay/liferay-screens/blob/1.2.0/android/library/core/src/main/java/com/liferay/mobile/screens/base/view/BaseViewModel.java). 
Add these methods as follows: 

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

These methods run when the operation to interact with the portal starts, 
finishes, or fails, respectively. In many cases you don't need to put anything 
in these methods. For example, in this app you're only interested in what 
happens when the operation finishes with a list of guestbooks, or finishes with 
an error. You can therefore leave `showStartOperation(String actionName)` and 
`showFinishOperation(String actionName)` blank. The `showFinishOperation` method 
containing the guestbooks populates the `_guestbooks` variable with the 
guestbooks returned from the portlet. The `notifyDataSetChanged` method then 
notifies the adapter of this change. If the operation fails, the Screenlet calls 
the `showFailedOperation` method to add an error message to the log and notify 
the user. 

Next, implement `AdapterView.OnItemClickListener` by adding the following 
`onItemClick` method to the `GetGuestbooksView` class: 

    @Override
    public void onItemClick(final AdapterView<?> parent, final View view, 
        final int position, final long id) {
        
        setItemChecked(position, true);
        // TODO: Call GetGuestbooksScreenlet's onItemClicked method
    }

This method runs when the user selects a guestbook in the `ListView`. It first 
uses `setItemChecked` to highlight the selected item. It then needs to call the 
`onItemClicked` method of `GetGuestbooksScreenlet`. There's just one (big) 
problem: the `GetGuestbooksScreenlet` class doesn't exist yet. You can ignore 
this for now; you'll create the Screenlet class later. The *TODO* serves as a 
nice reminder to come back here after creating the Screenlet class. 

Before moving on, make sure you have the following imports in your 
`GetGuestbooksView` class. If you don't, add them now: 

    import android.content.Context;
    import android.util.AttributeSet;
    import android.view.View;
    import android.widget.AdapterView;
    import android.widget.ArrayAdapter;
    import android.widget.ListView;

    import com.liferay.docs.getguestbooksscreenlet.GuestbookModel;
    import com.liferay.mobile.screens.util.LiferayLogger;
    import com.liferay.mobile.screens.viewsets.defaultviews.LiferayCrouton;

    import java.util.ArrayList;
    import java.util.List;

Great! With the exception of the *TODO* in `onItemClick`, you're done with the 
Get Guestbooks Screenlet's View. Next, you need to write the classes that issue 
and process the server calls. 
