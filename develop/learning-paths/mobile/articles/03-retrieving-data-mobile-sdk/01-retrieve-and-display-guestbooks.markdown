# Retrieve and Display Guestbooks [](id=retrieve-and-display-guestbooks)

The `GuestbooksActivity` you created in the first series of articles in this 
Learning Path contains a navigation drawer that slides out from the left side of 
the screen. You used Android Studio's *Navigation Drawer Activity* template to 
create this activity. Although this template created a functional navigation 
drawer, there are parts of the drawer you don't need in this app. For example, 
the drawer contains a list of menu items typically found in navigation drawers. 
It's up to you to decide which menu items, if any, you want to use. In this app, 
you don't need any of these menu items. You'll instead use the drawer to show 
the list of guestbooks your app retrieves from the portal. This is conceptually 
simple, but it's a bit more complex in practice. Not to worry! This article 
guides you though each step in the process.

![Figure 1: The placeholders currently in the navigation drawer.](../../images/android-guestbook-initial-drawer.png)

Before you can display the guestbooks, however, you need to retrieve them from 
your portal. You'll do this with the Guestbooks Mobile SDK and Liferay Screens. 
As you'll see, Screens includes some helper code that makes calling the Mobile 
SDK very straightforward. 

First, though, you need to be able to encapsulate the guestbooks that come back 
from the Guestbook portlet. 

## Encapsulating Guestbooks [](id=encapsulating-guestbooks)

To efficiently work with guestbooks in your app, you need a way of encapsulating 
them. This lets you refer to common guestbook objects throughout your code. 
First, create a new package called `model` inside the 
`com.liferay.docs.liferayguestbook` package. Inside this new `model` package, 
create a new class called `GuestbookModel`. Replace the `GuestbookModel` class's 
contents with the following code: 

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

            GuestbookModel guestbook = (GuestbookModel) obj;

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

        public String getName() {
            return _name;
        }
    }

This class creates `GuestbookModel` objects that effectively mirror `Guestbook` 
objects in the portlet. It does so by retrieving `Guestbook` parameters from 
the `JSONObject` returned by the Mobile SDK's remote service calls. This is done 
in the constructor by the `getLong` and `getString` methods. To see how the 
`Guestbook` parameters are defined in the portlet, see the 
[Liferay MVC Learning Path article on Service Builder](/develop/learning-paths/mvc/-/knowledge_base/6-2/using-service-builder-to-generate-a-persistence-fr). 
For now, the only parameters you really need in this class are `guestbookId` and 
`name`. However, you'll need the rest later. It's simpler to add support for all
of them now. 

You should also note the `toString` method in this class. Since all it does is
return a guestbook's name, it's very simple, but very important. To render
objects in the drawer, Android calls `toString` on them. If `toString` isn't
defined for the objects, strings with each object's full package path and
internal ID are shown. In other words, Android displays illegible text if you 
don't define `toString` here. By defining `toString` to return the name of each
`GuestbookModel`, you're telling Android to show each guestbook's name in the
drawer. 

Next, you'll write the code that retrieves guestbooks from the portlet. 

## Getting Guestbooks [](id=getting-guestbooks)

Before making the server call, you need to prepare the `GuestbooksActivity` 
class to handle `GuestbookModel` objects. Add the following `_guestbooks` 
variable for these objects: 

    private List<GuestbookModel> _guestbooks = new ArrayList<GuestbookModel>();

After adding this variable, add the following imports:

    import com.liferay.docs.liferayguestbook.model.GuestbookModel;
    import java.util.ArrayList;
    import java.util.List;

You also need a way to refresh `_guestbooks` with the latest `GuestbookModel` 
objects from the portlet. You'll do this with the `reloadGuestbooks` method. 
This method replaces any existing content in `_guestbooks` with the list of 
`GuestbookModel` objects supplied to it. Add it as shown here: 

    public void reloadGuestbooks(List<GuestbookModel> guestbooks) {
        _guestbooks.clear();
        _guestbooks.addAll(guestbooks);
    }

You'll use `reloadGuestbooks` when the call to retrive guestbooks from the 
portlet succeeds. You need a callback class to make this call. 

### Creating a Callback Class [](id=creating-a-callback-class)

Since Android doesn't allow network requests from its main UI thread, you have 
to make them from another thread by creating a callback class that extends the 
Mobile SDK's `GenericCallback` class. See
[Android's documentation](http://developer.android.com/guide/components/processes-and-threads.html#Threads) 
for more information on threading. At this point, you might be saying, "Oh no, 
threading in mobile apps? That sounds complicated!" Fear not! The Mobile SDK's 
`GenericCallback` class hides much of the added complexity. 

To create the callback class for retrieving guestbooks, first create a new 
package called `callback` in `com.liferay.docs.liferayguestbook`. Then create a 
new class in the `callback` package called `GetGuestbooksCallback`. Replace the 
code in the class with this code: 

    package com.liferay.docs.liferayguestbook.callback;

    import android.widget.Toast;

    import com.liferay.docs.liferayguestbook.GuestbooksActivity;
    import com.liferay.docs.liferayguestbook.model.GuestbookModel;
    import com.liferay.mobile.android.callback.typed.GenericCallback;

    import org.json.JSONArray;
    import org.json.JSONObject;

    import java.util.ArrayList;
    import java.util.List;

    public class GetGuestbooksCallback extends GenericCallback<List<GuestbookModel>> {

        private GuestbooksActivity _activity;

        public GetGuestbooksCallback(GuestbooksActivity activity) {
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
`GuestbooksActivity` instance as its only variable. This is so it can refer 
results back to `GuestbooksActivity`, which runs in Android's main UI thread.
`GetGuestbooksCallback`'s constructor thus does only one thing: it sets this
variable. Next, the `onFailure` and `onSuccess` methods are overridden. As you 
probably guessed, `onFailure` is called when the request fails, while `onSuccess` 
is called when it succeeds. In this example, `onFailure` displays a toast 
message with the error. The `onSuccess` method calls the main activity's 
`reloadGuestbooks` method. Last but not least is the overridden `transform` 
method. This method puts all the guestbooks it receives from the portlet into a 
`List` of `GuestbookModel` objects. It's this `List` that's fed to the 
`onSuccess` method. You're probably starting to see that `reloadGuestbooks` is 
an important method. It receives guestbooks for processing in the app's main UI 
thread. 

Now that you have everything you need to retrieve guestbooks from the Guestbook 
portlet, you're ready to make the server call.

### Making the Server Call [](id=making-the-server-call)

The Guestbook Mobile SDK contains the `GuestbookService` class you need to make 
the server call. You need to call this class's `getGuestbooks` method with the 
ID of the site that contains the Guestbook portlet. Do this now by adding the 
following `getGuestbooks()` method to `GuestbooksActivity`: 

    protected void getGuestbooks() {
        Session session = SessionContext.createSessionFromCurrentSession();
        GetGuestbooksCallback callback = new GetGuestbooksCallback(this);
        session.setCallback(callback);

        GuestbookService service = new GuestbookService(session);
        try {
            service.getGuestbooks(LiferayServerContext.getGroupId());
        }
        catch (Exception e) {
            String message = "Couldn't get guestbooks " + e.getMessage();
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }

Since the Mobile SDK requires a portal session to communicate with the portal, 
this method first uses `SessionContext.createSessionFromCurrentSession()` to 
create a session from the pre-existing session in the app. You might now be 
thinking, "Whoa there! I haven't created a session yet!" But actually, you have; 
you just didn't know it. Successful authentication with Login Screenlet creates 
a session that you can access with Screens's `SessionContext` class. Because 
you're counting on that session, however, you *must* use Login Screenlet. 

The `getGuestbooks()` method then creates a new `GetGuestbooksCallback` instance 
and sets it as the session's callback. Next, the session is used to create a 
`GuestbookService` instance. This lets you call the Guestbook portlet's remote 
services, as is done in the `try` block by 
`service.getGuestbooks(LiferayServerContext.getGroupId())`. The 
`LiferayServerContext` class, another Screens class, lets you retrieve the 
values you set in `server_context.xml`. It's used here to retrieve the group ID 
(site ID) needed to make the server call. As you can see, using Login Screenlet 
provides a great deal of flexibility, even when you don't need to use any other 
Screenlets.

Now you can call your new `getGuestbooks()` method. Place the `getGuestbooks()` 
call in the `onCreate` method of `GuestbooksActivity`, following the call to 
`setContentView(R.layout.activity_guestbooks)`. The first few lines of the 
`onCreate` method should now look like this: 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guestbooks);
        
        getGuestbooks();
        
        ...
    }

Android calls the activity's `onCreate` method to start the activity. This makes 
`onCreate` an ideal place to make the portlet call that retrieves guestbooks. 
Android's 
[activity lifecycle documentation](http://developer.android.com/training/basics/activity-lifecycle/starting.html) 
describes the `onCreate` method in more detail. 

Awesome! Your app can now retrieve the Guestbook portlet's guestbooks. Next, 
you'll display them in the app's UI. 

## Displaying Guestbooks in the Drawer [](id=displaying-guestbooks-in-the-drawer)

By default, the Action Bar displays the activity's name. You need it to display 
the selected guestbook's name instead. You'll do this by modifying the code that 
creates the app's Action Bar. Android Studio created this code for you in the 
`GuestbooksActivity` class's `onCreate` method:

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

First, you need the `ActionBar` and `Toolbar` to be instance variables that you 
can refer to anywhere in the activity. Add these variables to the 
`GuestbooksActivity` class now: 

    private ActionBar actionBar;
    private Toolbar toolbar;

Although you can modify the Action Bar initialization code in `onCreate`, you'll 
write it here in a separate method. Add the following `initActionBar()` method 
to `GuestbooksActivity`: 

    private void initActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setTitle("");
    }

Like the code in `onCreate`, this method also creates a `Toolbar` and sets it as 
the Action Bar. This code also sets the Action Bar's title to an empty string. 
This prevents the activity's title from showing in the Action Bar before the app
can retrieve guestbooks from the portal. Now you need to call `initActionBar()` 
in `onCreate`. Place the call immediately above the `getGuestbooks()` call. Then 
delete the original `Toolbar` initialization code in `onCreate`; it's no longer 
needed since you included it in `initActionBar`. The first few lines of the 
`onCreate` method should now look like this: 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guestbooks);
        
        initActionBar();
        getGuestbooks();

        ...
    }

Now you're ready to write the code that puts the guestbooks in the navigation 
drawer. First, you need to put a 
[`ListView`](http://developer.android.com/guide/topics/ui/layout/listview.html) 
in the drawer's layout. You'll use this `ListView` to display the guestbooks. In 
`activity_guestbooks.xml`, replace the existing 
[`NavigationView`](https://developer.android.com/reference/android/support/design/widget/NavigationView.html) 
with this one: 

    <android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/nav_header_guestbooks">

        <ListView
            android:id="@+id/drawer_list"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:choiceMode="singleChoice" />

    </android.support.design.widget.NavigationView>

When compared to the `NavigationView` it replaced, this new `NavigationView` 
contains the `ListView`, and lacks the `app:menu` attribute. This attribute 
points to the menu resource file used to create the drawer's items. Since you'll 
instead use the `ListView` to display the drawer's items (the guestbooks), you 
don't need to use `app:menu`. 

Now that you've specified the drawer's layout, you need to write the code that 
controls the drawer. You'll do this in `GuestbooksActivity`. First, add the 
following variables to this class:

    private DrawerLayout drawer;
    private ListView drawerListView;
    private ArrayAdapter _adapter;

As with the Action Bar, you'll initialize the drawer in its own method. Add 
this method now:

    private void initDrawer() {
        // drawer initialization
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, 
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        
        // ListView initialization
        drawerListView = (ListView) findViewById(R.id.drawer_list_view);
        _adapter = new ArrayAdapter<GuestbookModel>(
                this,
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                _guestbooks);
        drawerListView.setAdapter(_adapter);
    }

The `initDrawer` method's first few lines match the drawer initialization code 
in `onCreate`. You should therefore delete this code in `onCreate`. You don't 
need it there since you call it via `initDrawer` instead. The last part of 
`initDrawer` initializes the drawer's `ListView` and creates an 
[adapter](http://developer.android.com/guide/topics/ui/declaring-layout.html#AdapterViews) 
for it. Note that `_guestbooks` is the last argument to the adapter's 
constructor. This sets the list of guestbooks as the adapter's data source. The 
`setAdapter` method then sets the adapter to the `ListView`.

In the `onCreate` method, place the call to `initDrawer()` immediately below the 
`initActionBar` call. Also, because you want to use the same `DrawerLayout` 
instance throughout the class, delete the line of code that creates a new 
`DrawerLayout` in the `onBackPressed` method. 

Now you're ready to set what happens when the user selects a guestbook in the 
drawer. First, change the `GuestbooksActivity` class to implement 
`AdapterView.OnItemClickListener` instead of 
`NavigationView.OnNavigationItemSelectedListener`. Then delete the 
`onNavigationItemSelected` method. You should also delete the `NavigationView` 
code at the end of the `onCreate` method. Next, implement 
`AdapterView.OnItemClickListener` by adding the following `onItemClick` method:

    @Override
    public void onItemClick(final AdapterView<?> parent, final View view, 
        final int position, final long id) {
        
        drawerListView.setItemChecked(position, true);
        GuestbookModel selectedGuestbook = _guestbooks.get(position);
        actionBar.setTitle(selectedGuestbook.getName());

        drawer.closeDrawers();
    }

Android calls this method when a user selects a guestbook in the drawer. This 
method first marks the selected item in the UI. It then retrieves the selected 
guestbook and displays that guestbook's name in the Action Bar. The method 
finishes by closing the drawer.

Next, you need to set the drawer's `ListView` to listen for item clicks. Do this 
now by adding the following code to the end of the `initDrawer` method:

    drawerListView.setOnItemClickListener(this);

Great! Your app can now respond appropriately when a user selects a guestbook in 
the drawer. When `GuestbooksActivity` first loads, however, the drawer is closed 
until the user opens it. Recall that in `initActionBar()` you set the Action Bar 
to display an empty string by default. The activity therefore opens with no 
content and an empty Action Bar. This isn't very user friendly. You'll change 
this so that the activity selects the first guestbook by default. Recall that 
the Mobile SDK calls `reloadGuestbooks` when it successfully retrieves 
guestbooks from the portal. The existing code in this method replaces the 
current guestbook list with those retrieved from the portal. Add the following 
code to the end of the `reloadGuestbooks` method:

    _adapter.notifyDataSetChanged();
    
    drawerListView.performItemClick(drawerListView, 0, drawerListView.getItemIdAtPosition(0));

This code notifies the list adapter of any changed data, and then performs an 
item click on the first guestbook in the `ListView`. This item click triggers 
the `onItemClick` method. 

Awesome! You're almost ready to run the app. But you need to do some cleanup 
first. Because you're no longer using the menu resource file that defines the 
default drawer items, you can delete it. Delete the file 
`res/menu/activity_guestbooks_drawer.xml`.

There's one more thing you need to take care of before launching the app. Open 
the layout file `nav_header_guestbooks.xml`. The parent `LinearLayout` has a 
height of `160dp`. This value corresponds to `@dimen/nav_header_height`. To 
prevent the drawer header and guestbook list from overlapping, you must pad the 
top of the drawer's `ListView` by this value. Add the following to the 
`ListView` in `activity_guestbooks.xml`, below the `android:layout_height` 
setting:

    android:paddingTop="@dimen/nav_header_height"

That's it! Run the app. After you log in, the app retrieves the guestbooks from 
the portal and displays the first guestbook's name in the Action Bar.

![Figure 2: The app now displays the first guestbook's name in the Action Bar.](../../images/android-first-guestbook.png)

Open the drawer to reveal the list of guestbooks. Select a different guestbook 
from the drawer. The drawer then closes and the Action Bar displays the selected 
guestbook's name. Awesome! It works! There's one small issue, however, with the 
drawer (besides being hideous). Its header displays the text *Android Studio* 
and *android.studio@android.com*. You obviously don't want your users to see 
this. 

![Figure 3: The header displays generic text by default.](../../images/android-guestbook-drawer-01.png)

The two `TextView` elements in `nav_header_guestbooks.xml` use the 
`android:text` attribute to hardcode this text. To remove the text completely, 
you could delete the `TextView` elements. It's better though to show text that's 
relevant to your app. In the `TextView` elements, replace `"Android Studio"` 
with `"Liferay Guestbook"`, and `"android.studio@android.com"` with 
`"Welcome!"`.

Run the app again, and open the drawer after signing in. Although the drawer is 
still ugly, it's at least polite now. 

![Figure 4: The navigation drawer now contains the app's name and a greeting.](../../images/android-guestbook-drawer-02.png)

Great! Now you're ready to retrieve and display the guestbook entries. 
