# Retrieve and Display Guestbooks 

Your first order of business is to retrieve the guestbooks from the portlet and
display them in the app's navigation drawer. Recall that the app you created in
the first series of articles in this learning path contains a navigation drawer
that slides out from the left side of the screen. Currently, it contains simple
placeholders that are in dire need of replacement. 

![Figure 1: The placeholders currently in the navigation drawer.](../../images/android-guestbook-first-run.png)

In this article, you'll use the Liferay Mobile SDK to call the Guestbook
portlet's remote services and replace the placeholders with guestbooks. This is
conceptually simple, but it's a bit more complex in practice. Not to worry! This
article guides you through each step in the process. First, you'll do some
simple refactoring of your project. 

## Refactoring

Before you call the Guestbook portlet's remote services, you should move your 
project's activity and fragment classes into a new `activity` package. While 
this isn't required for the app to function, it makes it simpler to find and 
work with these classes as you add new ones. In Android Studio's project view: 

1. Right-click on the package `com.liferay.docs.liferayguestbook` and select
   *New* &rarr; *Package* in the context menu. In the dialog that appears, type
   *activity* and click *OK*. 
   
2. Right-click on the `MainActivity` class and select *Refactor* &rarr; *Move*
   in the context menu. In the *Move* dialog that appears, make sure the radio
   button for *To package* is selected, and then click the button to the right
   that contains the ellipsis. Another dialog then appears that lets you select
   the new package. In this dialog, expand *liferayguestbook*, click *activity*,
   and then click *OK*. Back in the Move dialog, click *Refactor*.
   
    ![Figure 2: The *Refactor* &rarr; *Move* dialog in Android Studio. Clicking the button highlighted by the red box lets you choose the file's new package.](../../images/android-studio-refactor.png)
   
3. Repeat step 2 for the `NavigationDrawerFragment` class. The `MainActivity` 
   and `NavigationDrawerFragment` classes should now be in the `activity` 
   package you created in step 1.
   
    ![Figure 3: The classes are now in the `activity` package.](../../images/android-studio-after-refactor.png)

Great! Now you'll be able to quickly find these classes as you add new files and 
functionality to your app. Now it's time to get some guestbooks!

## Encapsulating Guestbooks

To efficiently work with guestbooks in your app, you need a way of encapsulating 
them. This lets you refer to common guestbook objects throughout your code. 
First, create a new package called *model* inside the 
`com.liferay.docs.liferayguestbook` package. Right-click on the new `model`
package and select *New* &rarr; *Java Class* in the context menu. In the dialog
that appears, name the class *GuestbookModel* and click *OK*. The new class then
opens in Android Studio. Replace its contents with the following code:

    package com.liferay.docs.liferayguestbook.model;

    import org.json.JSONException;
    import org.json.JSONObject;

    import java.io.Serializable;
    import java.util.Date;

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
          
        @Override
        public String toString() {
            return _name;
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
objects in the portlet. It does so by retrieving `Guestbook` parameters from 
the `JSONObject` returned by the Mobile SDK's remote service calls. This is done 
in the constructor by the `getLong` and `getString` methods. To see how the 
`Guestbook` parameters are defined in the portlet, see the 
[Liferay MVC learning path article on Service Builder](/develop/learning-paths/-/knowledge_base/6-2/using-service-builder-to-generate-a-persistence-fr). 
For now, the only parameters you really need in this class are `guestbookId` and 
`name`. However, you'll need the rest later in this learning path. It's simpler 
to add support for all of them now.

You should also note the `toString` method in this class. By returning a 
guestbook's name, it's very simple, but very important. To render objects in the 
drawer, Android calls `toString` on them. If `toString` isn't defined for the 
objects, then strings with each object's full package path and internal ID are 
shown. In other words, illegible text is displayed if you don't define `toString` 
here. By defining `toString` to return the name of each `GuestbookModel`, you're 
telling Android to show each guestbook's name in the drawer. 

Next, you'll add the basic infrastructure for requesting guestbooks from the 
portlet. 

## Creating a Callback Class

Since Android doesn't allow network requests from its main UI thread, you have 
to make them from another thread by creating a callback class that extends the 
Mobile SDK's `GenericAsyncTaskCallback` class. See
[Android's documentation](http://developer.android.com/guide/components/processes-and-threads.html#Threads) 
for more information on threading. At this point, you might be saying, "Oh no, 
threading in mobile apps? That sounds complicated!" Fear not! The Mobile SDK's 
`GenericAsyncTaskCallback` class hides much of the added complexity. 

To create the callback class for retrieving guestbooks, first create a new 
package called *callback* in `com.liferay.docs.liferayguestbook`. Then create a 
new class in the `callback` package called `GetGuestbooksCallback`. Replace the 
code in the class with the following: 

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

So what's going on here? First, you should note that this class has a 
`MainActivity` instance as its only variable. This is so it can refer results 
back to the main activity, which runs in Android's main UI thread. The 
constructor of `GetGuestbooksCallback` thus does only one thing: it sets this 
variable. Next, the `onFailure` and `onSuccess` methods are overridden. As you 
probably guessed, `onFailure` is called when the request fails, while `onSuccess` 
is called when the it succeeds. In this case, `onFailure` displays a toast 
message with the error. The `onSuccess` method calls the main activity's 
`reloadGuestbooks` method. Don't worry about the error Android Studio marks for 
`reloadGuestbooks`. This method doesn't exist yet, but you'll create it in a 
moment. Last but not least is the overridden `transform` method. This method
puts all the guestbooks it receives from the portlet into a `List` of
`GuestbookModel` objects. It's this `List` that's fed to the `onSuccess` method.
You're probably starting to see that `reloadGuestbooks` is an important method.
It receives guestbooks for processing in the app's main UI thread. Now it's time
to write that processing code! 

## Displaying Guestbooks in the Drawer

To display guestbooks in the drawer, you first need a variable for the 
`GuestbookModel` objects returned by the callback. Create this variable in the 
`MainActivity` class as follows: 

    public List<GuestbookModel> _guestbooks = new ArrayList<GuestbookModel>();
    
Android Studio doesn't recognize `GuestbookModel` and marks it as an error 
because you haven't imported it yet. To do so, first place your cursor in the 
line of code containing `GuestbookModel`. Android Studio then displays a 
notification telling you the keyboard combination to press to add the import. 
Press it now. On Windows it's *Alt* + *Enter*, and on Mac it's 
*Option* + *Return*. You need to remember this going forward. The rest of this 
learning path assumes that you'll use this to organize any required imports.

In the `NavigationDrawerFragment` class, the app displays a list of items in the 
drawer by using a [`ListView`](http://developer.android.com/guide/topics/ui/layout/listview.html) 
with an [adapter](http://developer.android.com/guide/topics/ui/declaring-layout.html#AdapterViews). 
Specifically, an `ArrayAdapter` is used as the data source for the drawer's 
`ListView`. Even though the `ListView` and `ArrayAdapter` are closely linked, 
it's important to note that they each serve a different purpose. The `ListView` 
is used to render the adapter's contents as a scrollable list. The `ArrayAdapter` 
contains and renders each individual item for that list. This is all done in the 
`onCreateView` method of the `NavigationDrawerFragment` class. Android Studio 
generated this method for you when you created the project: 

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
hardcoded `"Section *"` strings, read the existing code to understand how it
works. The `onCreateView` method is part of the
[fragment activity lifecycle](http://developer.android.com/guide/components/fragments.html#Creating).
Specifically, `onCreateView` creates the fragment's UI. Here, it first
*inflates* the `ListView` by calling `inflater.inflate` on the fragment's layout
file `fragment_navigation_drawer.xml`. This file is represented by
`R.layout.fragment_navigation_drawer`. You can think of this representation as
an address the app uses to find the layout file. That's all fine and well, but
why do XML files need air pumped into them? Fortunately, we're not talking about
that kind of inflation; Liferay is fresh out of air pumps and pressure gauges.
When an Android layout XML file is inflated, its XML is converted into Java code
that the system uses to draw the UI. In this case,
`fragment_navigation_drawer.xml` only contains a single `ListView`, so it can be
cast to a `ListView` object following inflation. 

After this, a click listener for the list's items is created and set to the 
`ListView`. This lets the app respond when the user taps an item in the drawer. 
The click listener does this by calling the `selectItem` method with the 
selected item's position in the list (for now, you don't need to worry about 
what this method does). 

Next, the `ArrayAdapter` is created inside the `setAdapter` method. There are 
two main things you should take note of in the `ArrayAdapter`. First is that it 
uses the layout `simple_list_item_activated_1` to render each of its items. This 
is one of Android's default layouts and is suitable in cases where each list 
item is only a single string. Using a default layout also means that you don't 
have to write your own layout file. The second thing of note in the 
`ArrayAdapter` is the array of hardcoded `"Section *"` strings. You'll replace 
this shortly. The `setAdapter` method sets the adapter to the `ListView`.

The `onCreateView` method then finishes by setting the currently selected list 
item in the UI and returning the `ListView`. Now that you understand the basics 
of how `onCreateView` works here, you'll change it to display guestbooks from 
the Guestbook portlet. 

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
                ((MainActivity)getActivity())._guestbooks);
        mDrawerListView.setAdapter(_adapter);
        
        mDrawerListView.setItemChecked(mCurrentSelectedPosition, true);
        return mDrawerListView;
    }

This method uses the `_guestbooks` variable that you added in `MainActivity` to
create an `ArrayAdapter` of `GuestbookModel` objects. This adapter is then set
to the `ListView` in the drawer. The rest of `onCreateView` remains unchanged.

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
portlet call. You must also notify the list's adapter of this change. This is 
done with `notifyDataSetChanged()`. 

You're now ready to make the portlet call! This is done in the `MainActivity` 
class. First, add the following `getGuestbooks` method to `MainActivity`: 

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

This method makes the portlet call to get the guestbooks. Since a portal 
*session* is required for the Mobile SDK to communicate with the portal, the 
`getGuestbooks` method first creates a session with the user's credentials. The 
session is created by using `SessionImpl` with the server's address 
(for the Android emulator, `10.0.2.2:8080` is the same as `localhost:8080`) and 
a `BasicAuthentication` object containing the user's credentials. Be sure to 
change the user credentials here to match those of the user you created when you 
set up your portal. A new instance of `GetGuestbooksCallback` is then created 
and set as the session's callback. Next, the session is used to create a 
`GuestbookService` instance. This lets you call the Guestbook portlet's remote 
services, as is done in the `try` block by `service.getGuestbooks(SITE_ID)`. The 
`getGuestbooks(siteId)` method is the Guestbook portlet's remote service method 
that retrieves a site's guestbooks. Note that `SITE_ID` doesn't exist yet. 
Create it now as the following variable in the `MainActivity` class: 

    public static final int SITE_ID = 10182;

The value `10182` is the ID of the site the Guestbook portlet was placed on in 
this example. In your code, you should make sure it matches the ID of the site 
your Guestbook portlet is on. It's easy to look up in your portal. Log in to 
your Liferay installation and navigate to the site with the Guestbook portlet. 
In the Dockbar (the bar at the top of the screen), click 
*Admin* &rarr; *Site Administration* &rarr; *Configuration*. The site ID is in 
the *Site Settings* section. It's highlighted by the red box in the following 
screenshot. 

![Figure 4: The site ID is listed in the *Site Settings* section of the *Site Administration*'s *Configuration* menu.](../../images/site-id.png)

At this point, you're probably thinking, "Hang on a minute. What good is an 
Android app that has the portal, site, and user hardcoded in? It can only be 
used by one person, on one site, and in one portal!" You're right. This 
implementation is most untenable. However, you don't need to worry about it 
right now. It's done strictly for simplicity while you're developing the app's 
main features. Later in this learning path, you'll add a proper UI for portal 
selection, site selection, and authentication. If you add it right now, you'll 
have to enter this information *every time* your app redeploys to the emulator. 
Trust us; this becomes rather bothersome and significantly slows down 
development. 

Now you can call your new `getGuestbooks` method. Place the call in the 
`onCreate` method of `MainActivity`, following the call to 
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
activity. This makes it a good place to make the portlet call that retrieves 
guestbooks. The `onCreate` method is described further in Android's 
[activity lifecycle documentation](http://developer.android.com/training/basics/activity-lifecycle/starting.html). 
Awesome! You're almost done! There's one final detail to address before running 
the app. 

## Giving Your App Network Access

By default, Android apps can't access network connections. This means that even 
though your app is now capable of retrieving and displaying guestbooks from the 
portlet, the Android system doesn't yet allow it to. No worries! Adding this 
permission is simple. Open your app's `AndroidManifest.xml` and put the 
following line of code above the opening `application` tag: 

    <uses-permission android:name="android.permission.INTERNET" />

In short, the Android manifest file (`AndroidManifest.xml`) is where your app's 
components are registered so they're recognized by the Android system. App 
permissions are also placed in the manifest. You can find more information in 
Android's [documentation on the manifest](http://developer.android.com/guide/topics/manifest/manifest-intro.html). 
In most cases, Android Studio manages the manifest for you. For example,
declarations for your app and its activity already appear in the manifest, even
though you never put them there manually. However, defining your app's
permissions is one case where you need to manually edit the manifest. Now that
your app has network access, it's time to run it! 

## Running Your App

Make sure that your portal is running and then run your app as you did before, 
by clicking the green play button in Android Studio's toolbar. If the emulator 
is already running, you're prompted to select it when your app deploys. With the 
app now running in the emulator, open the drawer. Your guestbooks now appear in 
the drawer! 

![Figure 5: The guestbooks from the Guestbook portlet now appear in the drawer.](../../images/android-guestbook-drawer-01.png)

Great! Now you're ready to retrieve and display the guestbook entries. 
