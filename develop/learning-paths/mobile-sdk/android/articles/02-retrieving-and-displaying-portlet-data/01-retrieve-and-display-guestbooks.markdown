# Retrieve and Display Guestbooks 

The first order of business is to retrieve the guestbooks from the portlet and 
display them in the app's navigation drawer. Recall that the app you created in 
the first segment of this learning path contains a navigation drawer that slides 
out from the left side of the screen. Currently, it contains simple 
placeholders that are in dire need of replacing.

![Figure 1: The placeholders currently in the navigation drawer.](../../images/android-guestbook-first-run.png)

In this article you'll use the Mobile SDK to call the Guestbook portlet's remote 
services and replace the placeholders with guestbook names. This is conceptually 
simple, but a bit more complex in practice. Not to worry! This article guides 
you through each step in the process. First, you'll do some simple refactoring 
in your project.

## Refactoring

Before you get started calling the Guestbook portlet, you should refactor your 
project's activity and fragment classes by moving them into a new `activity` 
package. While this isn't required for the app to function, it makes it simpler 
to find and work with these classes as you add new classes. In Android Studio's 
project view:

1. Right click the package `com.liferay.docs.liferayguestbook` and select 
   *New* &rarr; *Package*. In the dialog that appears, type *activity* and click 
   *OK*.
   
2. Right click `MainActivity` and select *Refactor* &rarr; *Move*. In the *Move* 
   dialog that appears, make sure the radio button for *To package* is selected, 
   and click the button with the ellipsis to the right. Another dialog appears 
   that lets you select the new packge. In this dialog, expand 
   *liferayguestbook*, click *activity*, and then click *OK*. Back in the Move 
   dialog, click *Refactor*.
   
    ![Figure 2: The *Refactor* &rarr; *Move* dialog in Android Studio. Clicking the button with the red box lets you choose a new package for the file.](../../images/android-studio-refactor.png)
   
3. Repeat step 2 for the `NavigationDrawerFragment` class. The `MainActivity` 
   and `NavigationDrawerFragment` classes should now be in the `activity` 
   package you created in step 1.
   
    ![Figure 3: The classes are now in the `activity` package.](../../images/android-studio-after-refactor.png)

Great! Now you'll be able to quickly find these classes as you add new files and 
functionality to your app. Now it's time to get some guestbooks!

## Encapsulating Guestbooks

To work with guestbooks efficiently in your app, you need a way of encapsulating 
them. This lets you refer to common guestbook objects throughout your code. 
First, create a new package called *model* inside of the 
`com.liferay.docs.liferayguestbook` package. Right click the new `model` package 
and select *New* &rarr; *Java Class*. In the dialog that appears, name the class 
*GuestbookModel* and click *OK*. The new class then opens in Android Studio. 
Replace its contents with the following code:

    package com.liferay.docs.liferayguestbook.model;

    import org.json.JSONException;
    import org.json.JSONObject;

    import java.io.Serializable;


    public class GuestbookModel implements Serializable {

      private long _guestbookId;
      private long _groupId;
      private long _companyId;
      private long _userId;
      private String _userName;
      private long _createDate;
      private long _modifiedDate;
      private String _name;

      public GuestbookModel(JSONObject json) throws JSONException {
        _guestbookId = json.getLong("guestbookId");
        _groupId = json.getLong("groupId");
        _companyId = json.getLong("companyId");
        _userId = json.getLong("userId");
        _userName = json.getString("userName");
        _createDate = json.getLong("createDate");
        _modifiedDate = json.getLong("modifiedDate");
        _name = json.getString("name");
      }

      @Override
      public boolean equals(Object obj) {
        if (!(obj instanceof GuestbookModel)) {
            return false;
        }

        GuestbookModel guestbook = (GuestbookModel)obj;

        return (_guestbookId == guestbook.getGuestbookId());
      }

      public long getGuestbookId() {
        return _guestbookId;
      }
      
      public long getGroupId() {
        return _groupId;
      }

      public long getCompanyId() {
        return _companyId;
      }

      public long getUserId() {
        return _userId;
      }

      public String getUserName() {
        return _userName;
      }

      public Date getCreateDate() {
        Date createDate = new Date(_createDate);
        return createDate;
      }

      public Date getModifiedDate() {
        Date modifiedDate = new Date(_modifiedDate);
        return modifiedDate;
      }

      public String getGuestbookName() {
        return _name;
      }
    }

This class creates `GuestbookModel` objects that effectively mirror `Guestbook` 
objects from the portlet. It does so by retrieving `Guestbook` parameters from 
the `JSONObject` returned by the Mobile SDK's remote service calls. This is done 
in the constructor by the `getLong` and `getString` methods. To see how the 
`Guestbook` fields are defined in the portlet, see the 
[Liferay MVC learning path article on Service Builder](/learning-paths/-/knowledge_base/6-2/using-service-builder-to-generate-a-persistence-fr). 
For now, the only fields you really need in this class are `guestbookId` and 
`name`. Later in this learning path, however, you'll need the rest when you 
implement functionality in the app to add, update, and delete guestbooks in the 
portlet. It's simpler to add support for all of them now. Next, you'll add the 
basic infrastructure for requesting guestbooks from the portal. 

## Creating a Callback Class

Since Android doesn't allow network requests from its main UI thread, you have 
to make them from another thread by creating a callback class that extends the 
Mobile SDK's `GenericAsyncTaskCallback` class. At this point you might be 
saying, "Oh no, threading in mobile apps? That sounds complicated!" Fear not! 
The Mobile SDK's `GenericAsyncTaskCallback` class obscures much of the added 
complexity.

To create the callback class for retrieving guestbooks, first create a new 
pacakge called *callback* in `com.liferay.docs.liferayguestbook`. Then create a 
new class in the `callback` package called `GetGuestbooksCallback`. Replace the 
code in the class with the following code:

    package com.liferay.docs.liferayguestbook.callback;

    import android.widget.Toast;

    import com.liferay.docs.liferayguestbook.activity.MainActivity;
    import com.liferay.mobile.android.task.callback.typed.GenericAsyncTaskCallback;
    import com.liferay.docs.liferayguestbook.model.GuestbookModel;

    import java.util.ArrayList;
    import java.util.List;

    import org.json.JSONArray;
    import org.json.JSONObject;

    public class GetGuestbooksCallback extends GenericAsyncTaskCallback<List<GuestbookModel>> {

      private MainActivity _activity;

      public GetGuestbooksCallback(MainActivity activity) {
        _activity = activity;
      }

      @Override
      public void onFailure(Exception e) {
        String message = "Couldn't get guestbooks " + e.getMessage();

        Toast.makeText(_activity, message, Toast.LENGTH_LONG).show();
      }

      @Override
      public void onSuccess(List<GuestbookModel> guestbooks) {
        _activity.reloadGuestbooks(guestbooks);
      }

      @Override
      public List<GuestbookModel> transform(Object obj) throws Exception {
        List<GuestbookModel> guestbooks = new ArrayList<GuestbookModel>();

        JSONArray jsonArray = (JSONArray)obj;

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);

            guestbooks.add(new GuestbookModel(json));
        }

        return guestbooks;
      }

    }
    
First, you should note that this class has an instance of `MainActivity` as its 
only variable. This is so it can refer it's results back to the main activity, 
which runs in Android's main UI thread. The constructor of 
`GetGuestbooksCallback` thus does only one thing: it sets this variable. Next, 
the `onFailure` and `onSuccess` methods are overridden. As you probably guessed, 
`onFailure` is called when the request fails, and `onSuccess` is called when the 
request succeeds. In this case, `onFailure` only does one thing: it displays a 
toast message with the error. The `onSuccess` method also only does one thing, 
but it's a very important thing: it calls the main activity's `reloadGuestbooks` 
method. Don't worry about the `reloadGuestbooks` method being marked as an error 
by Android Studio. This method doesn't yet exist but you'll create it in a 
moment. Last but certainly not least is the overridden `transform` method. This 
method puts all the guestbooks it receives from the Mobile SDK call into a 
`List` of `GuestbookModel` objects. It's this `List` that's fed to the 
`onSuccess` method. You're probably starting to see why `reloadGuestbooks` is 
such and important method; it's receiving the guestbooks for processing in the 
app's main UI thread. Now it's time to write that processing code!

## Displaying Guestbooks in the Drawer

To display guestbooks in the drawer, you need a variable for holding the `List` 
of `GuestbookModel` objects returned by the callback. This variable needs to be 
`public` and `static` so it can be used throughout the UI, independent of 
any activity or fragment instance. Create this variable in the `MainActivity` 
class as follows: 

    public static List<GuestbookModel> _guestbooks = new ArrayList<GuestbookModel>();

The app displays items in the drawer using a [`ListView`](http://developer.android.com/guide/topics/ui/layout/listview.html) 
with an [adapter](http://developer.android.com/guide/topics/ui/declaring-layout.html#AdapterViews). 
Specifically, an `ArrayAdapter` is used to hold the list of items for display in 
the `ListView`. This is done in the `onCreateView` method of the 
`NavigationDrawerFragment` class. Android Studio generated this method for you 
when you created the project:

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDrawerListView = (ListView) inflater.inflate(
                R.layout.fragment_navigation_drawer, container, false);
        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });
        mDrawerListView.setAdapter(new ArrayAdapter<String>(
                getActionBar().getThemedContext(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                new String[]{
                        "Section 1",
                        "Section 2",
                        "Section 3",
                }));
        mDrawerListView.setItemChecked(mCurrentSelectedPosition, true);
        return mDrawerListView;
    }

Before you change this method to display the list of guestbooks instead of the 
hardcoded `"Section *"` strings, you should understand how it works. The 
`onCreateView` method is called as part of the [fragment activity lifecycle](http://developer.android.com/guide/components/fragments.html#Creating). 
Specifically, `onCreateView` creates the fragment's UI. Here, it first inflates 
the `ListView` by calling `inflater.inflate` on the fragment's layout file 
`R.layout.fragment_navigation_drawer`. A click listener is then created and set 
for the list items. In this case, the click listener selects the item in the 
list pressed by the user. Next, the `ArrayAdapter` is created inside of the 
`setAdapter` method. The `setAdapter` method is used to set this `ArrayAdapter` 
to the `ListView`. Lastly, `onCreateView` finishes by setting the currently 
selected list item and returning the `ListView`. Now that you understand the 
basics of how `onCreateView` works here, you'll change it to display guestbooks 
from the Guestbook portlet. 

Begin by declaring the following variable in the `NavigationDrawerFragment` 
class:

    public ArrayAdapter _adapter;

Next, replace `onCreateView` with the following code:

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDrawerListView = (ListView) inflater.inflate(
                R.layout.fragment_navigation_drawer, container, false);
        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });
        
        _adapter = new ArrayAdapter<GuestbookModel>(
                getActionBar().getThemedContext(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                MainActivity._guestbooks);
        mDrawerListView.setAdapter(_adapter);
        
        mDrawerListView.setItemChecked(mCurrentSelectedPosition, true);
        return mDrawerListView;
    }

This creates an `ArrayAdapter` of `GuestbookModel` objects using the `_guestbook` 
variable you created in `MainActivity`. The new `ArrayAdapter` is then set as 
the adapter for the `ListView` in the drawer. The rest of `onCreateView` remains 
unchanged.

Now you need to add the `reloadGuestbooks` method that is called in the 
`onSuccess` method of the `GetGuestbooksCallback` class. Add it in `MainActivity` 
as shown here:

    public void reloadGuestbooks(List<GuestbookModel> guestbooks) {
        _guestbooks.clear();
        _guestbooks.addAll(guestbooks);
        mNavigationDrawerFragment._adapter.notifyDataSetChanged();
    }

This method first clears any existing guestbooks from the `_guestbooks` 
variable. It then populates `_guestbooks` with the guestbooks retrieved from the 
call to the portlet. For this change to be picked up by the adapter underlying 
the drawer's list, you must notify it. The `reloadGuestbooks` method does this 
by calling the `notifyDataSetChanged()` method on the adapter.

There's one last thing to do for the guestbooks to show up in the drawer: write 
and call the `getGuestbooks` method to initiate the call to the Liferay server. 
Add this method in the `MainActivity` class: 

    protected void getGuestbooks() {
        Session session = new SessionImpl("http://10.0.2.2:8080",
                new BasicAuthentication("joebloggs@liferay.com", "test"));

        GetGuestbooksCallback callback = new GetGuestbooksCallback(this);
        session.setCallback(callback);

        GuestbookService service = new GuestbookService(session);

        try {
            service.getGuestbooks(SITE_ID);
        }
        catch (Exception e) {
            String message = "Couldn't get guestbooks " + e.getMessage();

            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }

Since a portal *session* is required for the Mobile SDK to communicate with the 
portal, the `getGuestbooks` method first creates a session with the user's 
credentials. The session is created by using `SessionImpl` with the server's 
address (`10.0.2.2:8080` is the same as `localhost:8080`) and a 
`BasicAuthentication` object containing the user's credentials. Be sure to 
change the user credentials here to match those of the user you created when you 
set up your portal. A new instance of `GetGuestbooksCallback` is then created 
and then set as the callback for the session. Next, an instance of 
`GuestbookService` is created using the session. This enables you to call the 
remote services of the Guestbook portlet, as is done in the `try` block by 
`service.getGuestbooks(SITE_ID)`. This is the Guestbook portlet's 
`getGuestbooks(siteId)` remote service method that retrieves all the guestbooks 
that exist in the specified site. Note that `SITE_ID` doesn't exist yet. Create 
it now as the following varaible in the `MainActivity` class:

    public static final int SITE_ID = 10182;

This is the site ID of the default site in Liferay Portal. At this point, you're 
probably thinking, "Hang on a minute. What good is an Android app that has the 
portal, site, and user hardcoded in? It can only be used by one person, on one 
site, and in one portal!" You're right. This implementation is most untenable. 
However, you don't need to worry about this right now. It's done strictly for 
simplicity while you're developing the app's main features. Later in this 
learning path, you'll add a proper screen for portal selection, site selection, 
and authentication. If you add such a screen now, you'll have to enter this 
information *every time* your app redeploys to the emulator. Trust us; this 
becomes rather bothersome and slows down development.

All that's left now is calling your new `getGuestbooks` method. Place the call 
in the `onCreate` method of `MainActivity`, following the call to 
`setContentView(R.layout.activity_main)`. The `onCreate` method should now look 
like this:

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        getGuestbooks();

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

As its name implies, an activity's `onCreate` method is called to start the 
activity. This makes it a good place to initiate the portal call that retrieves 
guestbooks. The `onCreate` method is described further in Android's 
[activity lifecycle documentation](http://developer.android.com/training/basics/activity-lifecycle/starting.html). 
There's one final detail to address before running the app.

## Giving Your App Network Access

By default, Android apps can't access network connections. This means that even 
though your app is now capable of retrieving and displaying guestbooks from the 
portal, the Android system doesn't allow it yet. No worries! Adding this 
permission is simple. Open your app's `AndroidManifest.xml` and put the 
following line of code above the opening `application` tag:

    <uses-permission android:name="android.permission.INTERNET" />

In short, the Android manifest file is where your app's components are 
registered so they can be recognized by the Android system. This includes any 
permissions your app requires. You can find more information in Android's 
[documentation on the manifest](http://developer.android.com/guide/topics/manifest/manifest-intro.html). 
In most cases, Android Studio manages the manifest for you. For example, your 
app and its activity are already present in the manifest, even though you never 
put them there manually. However, defining your app's permissions is one case 
where you need to manually edit the manifest. Now it's time to run the app in 
the emulator!

## Running Your App

Run your app as you did before, by clicking the green play button in Android 
Studio's toolbar. If the emulator is already running, you're prompted to select 
it when your app deploys. With the app now running in the emulator, click the 
horizontal line stack on the action bar's left to open the drawer. Voila! Your 
guestbooks appear in the drawer! Wait, not so fast; the guestbook names aren't 
rendering properly.

![Figure 4: Strings representing `GuestbookModel` objects appear in the drawer instead of proper guestbook names.](../../images/android-guestbook-drawer-01.png)

Instead of each guestbook's name, a string representing each `GuestbookModel` 
object is showing. If you're a seasoned Java developer you probably have an idea 
of what's going on here. This is the sort of text you get when `toString` is 
called on a Java object. In fact, that's *exactly* what's happening here, 
because that's the default behavior of Android's built-in adapter classes that 
hold items for display in `ListView`. Recall that `ArrayAdapter` is being used 
to hold the guestbooks for display in the drawer's `ListView`. To show the 
proper guestbook names in the drawer, you need to implement your own adapter 
class. You'll do this next.
