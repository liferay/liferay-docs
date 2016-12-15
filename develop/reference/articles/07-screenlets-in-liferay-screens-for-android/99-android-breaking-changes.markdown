# Android Breaking Changes [](id=android-breaking-changes)

This document presents a list of changes in Liferay Screens for Android 2.0 that 
break preceding functionality. We try our best to minimize these disruptions, 
but sometimes they are unavoidable. 

## Breaking Changes List [](id=breaking-changes-list)

### Interactors Now Run in a Background Process [](id=interactors-now-run-in-a-background-process)

#### What changed? [](id=what-changed)

Interactors now run in a background process, so you don't need to create or set 
callback classes manually. This means you can write what appear to be 
synchronous server calls, and Liferay Screens handles the background threading 
for you. The Interactor’s `execute` method makes the server call. Invoking the 
`start` method in your Screenlet class causes `execute` to run in a background 
thread. 

Note that you no longer have to handle the exception when making the server 
call. The Screenlet framework does this for you and propagates any error via the 
listeners. Also note that the `screenletId` is no longer required. The Screenlet 
framework automatically decorates the event with a `screenletId` that it 
generates. 

#### Who is affected? [](id=who-is-affected)

This affects all Screenlet Interactors. 

#### How should I update my code? [](id=how-should-i-update-my-code)

You must rewrite your Interactors. See the tutorial 
[Creating Android Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-android-screenlets#creating-the-screenlets-Interactor-class) 
for the most recent instructions on creating an Interactor. 

#### Why was this change made? [](id=why-was-this-change-made)

Asynchronous calls can be difficult to develop and work with. By handling them 
for you, Liferay Screens removes this potential source of error and frees you to 
focus on other parts of your Screenlet. 

---------------------------------------

### Changes to View Set Inheritance [](id=changes-to-view-set-inheritance)

#### What changed? [](id=what-changed-0)

To use a View Set, your app or activity’s theme must also inherit that View 
Set’s styles. For example, to use the Default View Set, your app or activity’s 
theme must inherit `default_theme`. 

#### Who is affected? [](id=who-is-affected-0)

This affects any apps or activities that use a View Set without inheriting that 
View Set’s styles. For example, if you use the Default View for a Screenlet by 
setting the Screenlet XML’s `layoutId` attribute, your app or activity’s theme 
must now inherit `default_theme` as well. Likewise, your app or activity’s theme 
must inherit `westeros_theme` or `material_theme` to use the Westeros or 
Material View Set, respectively. 

#### How should I update my code? [](id=how-should-i-update-my-code-0)

Change your app or activity’s theme to inherit the styles of the View Set you 
want to use. 

**Example**

This code snippet from an app’s `res/values/styles.xml` tells 
`AppTheme.NoActionBar` to inherit the Default View Set’s styles: 

    <resources>

        <style name="AppTheme.NoActionBar" parent="default_theme">
            <item name="colorPrimary">@color/colorPrimary</item>
            <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
            <item name="colorAccent">@color/colorAccent</item>

            <item name="windowActionBar">false</item>
            <item name="windowNoTitle">true</item>
        </style>
        ...
    </resources>

#### Why was this change made? [](id=why-was-this-change-made-0)

This lets you change an Android theme’s colors and styles according to Android 
conventions. Before, the Android themes were hardcoded inside the Screenlets. 

---------------------------------------

### The Screenlet Attribute offlinePolicy is now cachePolicy [](id=the-screenlet-attribute-offlinepolicy-is-now-cachepolicy)

#### What changed? [](id=what-changed-1)

The Screenlet attribute `offlinePolicy` is now `cachePolicy`.

#### Who is affected? [](id=who-is-affected-1)

This affects any Screenlets that used the `offlinePolicy` attribute to set that 
Screenlet’s offline mode policy.

#### How should I update my code? [](id=how-should-i-update-my-code-1)

In the app layouts that contain the Screenlet, change the `offlinePolicy` 
attribute to `cachePolicy`.

**Example**

Old way:

    <com.liferay.mobile.screens.assetlist.AssetListScreenlet
		android:id="@+id/asset_list_screenlet"
		android:layout_width="match_parent"
		android:layout_height="match_parent"
		liferay:autoLoad="false"
		liferay:offlinePolicy="REMOTE_FIRST" />

New way:

    <com.liferay.mobile.screens.asset.list.AssetListScreenlet
		android:id="@+id/asset_list_screenlet"
		android:layout_width="match_parent"
		android:layout_height="match_parent"
		liferay:autoLoad="false"
		liferay:cachePolicy="REMOTE_FIRST"
		/>

#### Why was this change made? [](id=why-was-this-change-made-1)

This change was made for consistency throughout Liferay Screens. The method and 
class names in the offline mode APIs contain *cache*, as do the offline policies 
`CACHE_ONLY` and `CACHE_FIRST`. 

---------------------------------------

### Some Listener Methods in DDL Form Screenlet Have Changed [](id=some-listener-methods-in-ddl-form-screenlet-have-changed)

#### What changed? [](id=what-changed-2)

The following error listener methods in DDL Form Screenlet’s `DDLFormListener` 
have been removed: 

- `void onDDLFormLoadFailed(Exception e)`: Called when an error occurs in the 
  load form definition request. 
- `void onDDLFormRecordLoadFailed(Exception e)`: Called when an error occurs in 
  the load form record request. 
- `void onDDLFormRecordAddFailed(Exception e)`: Called when an error occurs in 
  the request to add a new record. 
- `void onDDLFormUpdateRecordFailed(Exception e)`: Called when an error occurs 
  in the request to update an existing record. 

Also in `DDLFormListener`, the method `onDDLFormRecordLoaded` now takes an 
additional parameter for the attribute map received from the server. 

#### Who is affected? [](id=who-is-affected-2)

This affects any classes that implement `DDLFormListener`. 

#### How should I update my code? [](id=how-should-i-update-my-code-2)

In place of the removed error listeners, use `BaseCacheListener`’s generic error 
listener: 

    void error(Exception e, String userAction)

You must also change any `onDDLFormRecordLoaded` implementations to account for 
the method’s new signature:

    public void onDDLFormRecordLoaded(Record record, Map<String, Object> valuesAndAttributes)

#### Why was this change made? [](id=why-was-this-change-made-2)

The old error listener methods were usually implemented the same way: by logging 
the exception. Multiple error listener methods aren’t needed for this. You can 
use the new error listener method to log the exception and take any other action 
that depends on the user action. 

---------------------------------------

### Cache Listener Methods Moved into Their Own Listener [](id=cache-listener-methods-moved-into-their-own-listener)

#### What changed? [](id=what-changed-3)

The cache listener methods `loadingFromCache`, `retrievingOnline`, and 
`storingToCache` have been moved to their own listener, `CacheListener`. Note, 
this change was introduced in Liferay Screens 1.4.0. 

#### Who is affected? [](id=who-is-affected-3)

All activity classes that implement a listener. 

#### How should I update my code? [](id=how-should-i-update-my-code-3)

If you don’t have special behavior in your old cache listener method 
implementations, you can remove them. Otherwise, you must implement the new 
`CacheListener`. When implementing `CacheListener` (in an activity or fragment, 
for example), you should also register a Screenlet instance as the cache 
listener: 

        screenlet.setCacheListener(this);

**Example**

In the Liferay Screens test app, 
[the activity `UserPortraitActivity`](https://github.com/liferay/liferay-screens/blob/2.0.1/android/samples/test-app/src/main/java/com/liferay/mobile/screens/testapp/UserPortraitActivity.java) 
implements `CacheListener`: 

    public class UserPortraitActivity extends ThemeActivity implements UserPortraitListener, 
        CacheListener {

        private UserPortraitScreenlet screenlet;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);

                setContentView(R.layout.user_portrait);

                screenlet = (UserPortraitScreenlet) findViewById(R.id.user_portrait_screenlet);
                screenlet.setListener(this);
                screenlet.setCacheListener(this);
        }

        …

        @Override
        public void loadingFromCache(boolean success) {
            View content = findViewById(android.R.id.content);
            Snackbar.make(content, "Trying to load from cache: " + success, 
                Snackbar.LENGTH_SHORT).show();
        }

        @Override
        public void retrievingOnline(boolean triedInCache, Exception e) {

        }

        @Override
        public void storingToCache(Object object) {
            View content = findViewById(android.R.id.content);
            Snackbar.make(content, "Storing to cache...", Snackbar.LENGTH_SHORT).show();
        }

        …

    }

#### Why was this change made? [](id=why-was-this-change-made-3)

Reacting to cache errors via the cache listener methods is a niche use case. 
Because the old cache listener methods were part of the normal listener, 
developers were forced to implement them whether they needed them or not. 
Putting them in their own listener makes their implementation optional. 

---------------------------------------

### Changed BaseListListener Methods [](id=changed-baselistlistener-methods)

#### What changed? [](id=what-changed-4)

The `BaseListListener` methods `onListPageFailed` and `onListPageReceived` no 
longer have the `BaseListScreenlet` argument `source`. These methods also now 
account for a page’s start and end row instead of the page number. 

#### Who is affected? [](id=who-is-affected-4)

This affects any classes or interfaces that extend or implement 
`BaseListListener`. 

#### How should I update my code? [](id=how-should-i-update-my-code-4)

Remove the `BaseListScreenlet` argument from your `onListPageFailed` and 
`onListPageReceived` implementations. You must also replace the `int page` 
argument in `onListPageFailed` with an `int` argument representing the page’s 
start row. Likewise, replace the `int page` argument in `onListPageReceived` 
with two `int` arguments that represent the page’s start row and end row, 
respectively.

**Example**

Old signatures: 

    void onListPageFailed(BaseListScreenlet source, int page, Exception e)
    void onListPageReceived(BaseListScreenlet source, int page, List<E> entries, int rowCount)

New signatures: 

    void onListPageFailed(int startRow, Exception e)
    void onListPageReceived(int startRow, int endRow, List<E> entries, int rowCount)

#### Why was this change made? [](id=why-was-this-change-made-4)

The `BaseListScreenlet` argument served to disambiguate two instances of the 
same Screenlet in a single activity. This is a very rare use case. Therefore, 
forcing the argument on all `BaseListListener` implementations was unnecessary. 
If you still need this use case, create a Screenlet instance and listener for 
each Screenlet instead of relying on the `BaseListScreenlet` argument in a 
single listener. The start row and end row change was made for consistency with 
other listeners that also use start row and end row arguments. 

---------------------------------------

### Changed Asset List Screenlet Package [](id=changed-asset-list-screenlet-package)

#### What changed? [](id=what-changed-5)

Asset List Screenlet’s package is now `com.liferay.mobile.screens.asset.list` 
instead of `com.liferay.mobile.screens.assetlist`. 

#### Who is affected? [](id=who-is-affected-5)

This affects any activities or fragments that use Asset List Screenlet.

#### How should I update my code? [](id=how-should-i-update-my-code-5)

Change your `com.liferay.mobile.screens.assetlist` imports to 
`com.liferay.mobile.screens.asset.list`. 

#### Why was this change made? [](id=why-was-this-change-made-5)

This allows for other Screenlets that work with assets, like Asset Display 
Screenlet. For example, the package `com.liferay.mobile.screens.asset` now 
contains Asset List Screenlet, Asset Display Screenlet, and classes common to 
both. 

---------------------------------------

### Changed Return Type for a DDL Record Method [](id=changed-return-type-for-a-ddl-record-method)

#### What changed? [](id=what-changed-6)

The `getModelValues()` method for 
[DDL records](https://github.com/liferay/liferay-screens/blob/2.0.1/android/library/src/main/java/com/liferay/mobile/screens/ddl/model/Record.java) 
now returns a `Map` instead of a `HashMap`. 

#### Who is affected? [](id=who-is-affected-6)

This affects any code that expects `getModelValues()` to return a `HashMap`. 

#### How should I update my code? [](id=how-should-i-update-my-code-6)

Change any code that uses `getModelValues()` to expect a `Map` instead of a 
`HashMap`. 

#### Why was this change made? [](id=why-was-this-change-made-6)

This follows general Java conventions. 

---------------------------------------

### Changed Code Conventions for Private and Protected Fields [](id=changed-code-conventions-for-private-and-protected-fields)

#### What changed? [](id=what-changed-7)

Private and protected fields in Screenlets are no longer prefixed by `_`. 

#### Who is affected? [](id=who-is-affected-7)

This affects any code that directly accesses protected fields. 

#### How should I update my code? [](id=how-should-i-update-my-code-7)

Change your code to use the new variable name. For example, if your code 
directly accesses a protected Screenlet variable named `_fields`, change it to 
use `fields` instead. 

#### Why was this change made? [](id=why-was-this-change-made-7)

This follows general Java naming conventions. 

---------------------------------------

### Changes to Using a Screenlet without a View [](id=changes-to-using-a-screenlet-without-a-view)

#### What changed? [](id=what-changed-8)

If you’re using a Screenlet without View (like you might be if you need to log a 
user in programmatically), you no longer have to call 
`LiferayScreensContext.init(this)` to initialise the library. This is now called 
automatically. 

#### Who is affected? [](id=who-is-affected-8)

This affects any apps that use a Screenlet without a View.

#### How should I update my code? [](id=how-should-i-update-my-code-8)

Remove your manual call to `LiferayScreensContext.init(this)`.

#### Why was this change made? [](id=why-was-this-change-made-8)

This removes the possibility of an error if you forget to call 
`LiferayScreensContext.init(this)` when using a Screenlet without a View. 
