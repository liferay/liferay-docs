# Creating Android List Screenlets [](id=creating-android-list-screenlets)

It's very common for mobile apps to display lists of entities. Liferay Screens 
lets you display asset lists and DDL lists in your Android app by using 
[Asset List Screenlet](/develop/reference/-/knowledge_base/7-0/assetlistscreenlet-for-android) 
and 
[DDL List Screenlet](/develop/reference/-/knowledge_base/7-0/ddllistscreenlet-for-android), 
respectively. For your app to display a list of other entities from a Liferay 
instance, however, you must create your own list Screenlet. You can create this 
Screenlet to display standard Liferay entities such as `User`, or custom 
entities that belong to custom Liferay plugins. 

This tutorial shows you how to create your own list Screenlet. As an example, 
you'll create a Screenlet that displays a list of bookmarks from Liferay's 
Bookmarks portlet--Bookmark List Screenlet. You can find the finished 
Screenlet's code 
[here in GitHub](https://github.com/liferay/liferay-screens/tree/master/android/samples/listbookmarkscreenlet). 

Note that this tutorial doesn't explain the general Screenlet concepts and 
components in detail. Focus is instead placed on creating a Screenlet that 
displays lists of entities. Before beginning, you should therefore read the 
[Screens architecture tutorial](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-android), 
and the general 
[Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-android-screenlets). 

You'll create the list Screenlet by following these steps: 

1. Creating the Model Class

2. Creating the Screenlet's View

3. Creating the Screenlet's Interactor

4. Creating the Screenlet's Listener

5. Creating the Screenlet Class

First though, you should understand how pagination works with list Screenlets. 

## Pagination [](id=pagination)

To ensure that users can scroll smoothly through large lists of items, list 
Screenlets should support fluent pagination. In cases where you only have a 
small list of items, however, you can skip this. For example, if you want to 
list the days of the week, you don't need to implement fluent pagination. 

Liferay Screens gives you some tools to implement fluent pagination with 
configurable page size. Note that Asset List Screenlet and DDL List Screenlet 
also use this approach. 

Now you're ready to start creating your list Screenlet! 

## Creating the Model Class [](id=creating-the-model-class)

Entities come back from Liferay in JSON. To work with these results efficiently 
in your app, you must convert them to model objects that represent the entity in 
Liferay. Although Screens's 
[`BaseListCallback`](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/BaseListCallback.java) 
transforms the JSON entities into `Map` objects for you, you still must 
convert these into proper entity objects for use in your app. You'll do this via 
a model class. 

For example, Bookmark List Screenlet needs `Bookmark` objects. Create the 
following `Bookmark` class that you'll use to create these objects. This class 
creates `Bookmark` objects that contain the bookmark's URL and other data. Since 
you always need quick access to the bookmark's URL, it's extracted from the 
`Map` and stored in the `url` variable. The entire `Map` is stored in the 
`values` variable so you can extract any other data you need from it later: 

    import android.os.Parcel;
    import android.os.Parcelable;

    import java.util.Map;

    public class Bookmark implements Parcelable {

        private String url;
        private Map values;

        public static final Creator<Bookmark> CREATOR = new Creator<Bookmark>() {
            @Override
            public Bookmark createFromParcel(Parcel in) {
                return new Bookmark(in);
            }

            @Override
            public Bookmark[] newArray(int size) {
                return new Bookmark[size];
            }
        };

        public Bookmark() {
            super();
        }

        protected Bookmark(Parcel in) {
            url = in.readString();
        }

        public Bookmark(Map<String, Object> stringObjectMap) {
            url = (String) stringObjectMap.get("url");
            values = stringObjectMap;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(url);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public String getUrl() {
            return url;
        }

        public Map getValues() {
            return values;
        }

        public void setValues(Map values) {
            this.values = values;
        }

    }

Now that you have your model class, you can create your Screenlet's View.

## Creating the Screenlet's View [](id=creating-the-screenlets-view)

Recall that a View defines a Screenlet's UI. Since you're creating a list 
Screenlet, you'll first define the layout to use for each row in the list. For 
example, Bookmark List Screenlet needs to display a bookmark in each row. You 
can therefore define its row layout as a simple `LinearLayout` that contains a 
single `TextView` you'll use to display the bookmark's URL. Create the following 
`bookmark_row.xml` in the `res/layout` directory:

    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <TextView
            android:id="@+id/bookmark_url"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/>

    </LinearLayout>

A list Screenlet's View must also contain an 
[adapter class](http://developer.android.com/guide/topics/ui/declaring-layout.html#AdapterViews). 
Your adapter class should contain a standard Android 
[view holder](http://developer.android.com/training/improving-layouts/smooth-scrolling.html#ViewHolder) 
to make scrolling through the list smooth. You can extend Screens's 
[`BaseListAdapter`](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListAdapter.java) 
to make things simpler. If you do so, you only need to implement two methods: 
one that instantiates your view holder, and one that fills it in for each row. 
Create this class now for Bookmark List Screenlet. Note that the view holder 
renders the model's `url` attribute in the bookmark layout: 

    import android.support.annotation.NonNull;
    import android.view.View;
    import android.widget.TextView;

    import com.liferay.mobile.screens.base.list.BaseListAdapter;
    import com.liferay.mobile.screens.base.list.BaseListAdapterListener;

    public class BookmarkAdapter extends BaseListAdapter<Bookmark, BookmarkAdapter.BookmarkViewHolder> {

        public BookmarkAdapter(int layoutId, int progressLayoutId, BaseListAdapterListener listener) {
            super(layoutId, progressLayoutId, listener);
        }

        @NonNull
        @Override
        public BookmarkViewHolder createViewHolder(View view, BaseListAdapterListener listener) {
            return new BookmarkAdapter.BookmarkViewHolder(view, listener);
        }

        @Override
        protected void fillHolder(Bookmark entry, BookmarkViewHolder holder) {
            holder.bind(entry);
        }

        public class BookmarkViewHolder extends BaseListAdapter.ViewHolder {

            private final TextView url;

            public BookmarkViewHolder(View view, BaseListAdapterListener listener) {
                super(view, listener);

                url = (TextView) view.findViewById(R.id.bookmark_url);
            }

            public void bind(Bookmark entry) {
                url.setText(entry.getUrl());
            }

        }

    }

Now that your adapter exists, you can create your list Screenlet's View class. 
This class should extend `BaseListScreenletView`, parameterized with your model 
class, view holder, and adapter. Your View class must also override the 
`createListAdapter` method to return an instance of your adapter. You'll also 
create any other methods required to support your View's functionality. 

For example, Bookmark List Screenlet's View class (`BookmarkListView`) must 
represent `Bookmark` instances in a `BookmarkViewHolder` inside a 
`BookmarkAdapter`. The `BookmarkListView` class must therefore extend 
`BaseListScreenletView` with `Bookmark`, `BookmarkAdapter.BookmarkViewHolder`, 
and `BookmarkAdapter` as type parameters. Besides overriding `createListAdapter` 
to return a `BookmarkAdapter` instance, the only other functionality that this 
View class needs to support is to get the layout for each row in the list. You 
can accomplish this by overriding the `getItemLayoutId` method to return the row 
layout you created earlier (`bookmark_row`). Create `BookmarkListView` now: 

    import android.content.Context;
    import android.util.AttributeSet;

    import com.liferay.mobile.screens.base.list.BaseListScreenletView;

    public class BookmarkListView
        extends BaseListScreenletView<Bookmark, BookmarkAdapter.BookmarkViewHolder, BookmarkAdapter> {

        public BookmarkListView(Context context) {
            super(context);
        }

        public BookmarkListView(Context context, AttributeSet attributes) {
            super(context, attributes);
        }

        public BookmarkListView(Context context, AttributeSet attributes, int defaultStyle) {
            super(context, attributes, defaultStyle);
        }

        @Override
        protected BookmarkAdapter createListAdapter(int itemLayoutId, int itemProgressLayoutId) {
            return new BookmarkAdapter(itemLayoutId, itemProgressLayoutId, this);
        }

        @Override
        protected int getItemLayoutId() {
            return R.layout.bookmark_row;
        }
    }

Lastly, you must define your View's layout XML. To let your app efficiently 
scroll through a potentially large list of items, your layout should use 
Android's 
[`RecyclerView`](http://developer.android.com/training/material/lists-cards.html#RecyclerView). 
Apart from the styling, this layout's code is the same for all list Screenlets. 
Create Bookmark List Screenlet's layout in `res/layout/list_bookmarks.xml` as 
follows: 

    <com.liferay.mobile.screens.listbookmark.BookmarkListView
        android:id="@+id/liferay_list_screenlet"
        xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <ProgressBar
            android:id="@+id/liferay_progress"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:visibility="gone"/>

        <android.support.v7.widget.RecyclerView
            android:id="@+id/liferay_recycler_list"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:visibility="gone"/>
    </com.liferay.mobile.screens.listbookmark.BookmarkListView>

+$$$

**Warning:** The `android:id` values in your View's layout XML must exactly 
match the ones shown here. These values are hardcoded into the Screens framework 
and changing them will cause your app to crash. 

$$$

Nice work! Now you can create your list Screenlet's Interactor. 

## Creating the Screenlet's Interactor [](id=creating-the-screenlets-interactor)

Recall that Screenlets use Interactors to retrieve entities from Liferay. Your 
list Screenlet's Interactor should extend `BaseListInteractor`, parameterized 
with your model class and `BaseListInteractorListener<YourModelClass>`. Your 
Interactor must also define the variables it needs to make the server call. 
Create Bookmark List Screenlet's Interactor class as follows. 

Your Interactor class must contain the methods needed to make the server 
call and process the results. You'll do this by overriding the following 
methods: 

- `createEntity`: Creates a new `Bookmark` instance.

- `getPageRowsRequest`: Retrieves the specified page of entities. 

- `getPageRowCountRequest`: Retrieves the number of entities. 

Create `BookmarkListInteractor` now: 

    import com.liferay.mobile.android.v7.bookmarksentry.BookmarksEntryService;
    import com.liferay.mobile.screens.base.list.interactor.BaseListInteractor;
    import com.liferay.mobile.screens.base.list.interactor.BaseListInteractorListener;
    import com.liferay.mobile.screens.base.list.interactor.Query;
    import com.liferay.mobile.screens.context.LiferayServerContext;
    import java.util.Map;
    import org.json.JSONArray;

    public class BookmarkListInteractor extends BaseListInteractor<BaseListInteractorListener<Bookmark>, BookmarkEvent> {

        @Override
        protected JSONArray getPageRowsRequest(Query query, Object... args) throws Exception {
            long folderId = (long) args[0];

            if (args[1] != null) {
                query.setComparator((String) args[1]);
            }

            if (LiferayServerContext.isLiferay7()) {
                return new BookmarksEntryService(getSession()).getEntries(groupId, folderId, query.getStartRow(),
                    query.getEndRow(), query.getComparatorJSONWrapper());
            } else {
                return new com.liferay.mobile.android.v62.bookmarksentry.BookmarksEntryService(getSession()).getEntries(
                    groupId, folderId, query.getStartRow(), query.getEndRow(), query.getComparatorJSONWrapper());
            }
        }

        @Override
        protected Integer getPageRowCountRequest(Object... args) throws Exception {
            long folderId = (long) args[0];

            if (LiferayServerContext.isLiferay7()) {
                return new BookmarksEntryService(getSession()).getEntriesCount(groupId, folderId);
            } else {
                return new com.liferay.mobile.android.v62.bookmarksentry.BookmarksEntryService(
                    getSession()).getEntriesCount(groupId, folderId);
            }
        }

        @Override
        protected BookmarkEvent createEntity(Map<String, Object> stringObjectMap) {
            Bookmark bookmark = new Bookmark(stringObjectMap);
            return new BookmarkEvent(bookmark);
        }
    }

Great! Your Interactor is finished. Next, you'll create your list Screenlet's 
event. 

## Creating the Screenlet's Event

    import com.liferay.mobile.screens.base.list.interactor.ListEvent;

    public class BookmarkEvent extends ListEvent<Bookmark> {

	      private Bookmark bookmark;

	      public BookmarkEvent() {
		        super();
	      }

	      public BookmarkEvent(Bookmark bookmark) {
      		  this.bookmark = bookmark;
	      }

	      @Override
	      public String getListKey() {
		        return bookmark.getUrl();
	      }

	      @Override
	      public Bookmark getModel() {
		        return bookmark;
	      }
    }

Next, you'll create a listener for your list Screenlet. 

## Creating the Screenlet's Listener [](id=creating-the-screenlets-listener)

Recall that listeners let the app developer respond to events that occur in your 
Screenlet. For example, an app developer using Login Screenlet in an activity 
must implement `LoginListener` in that activity to respond to login success or 
failure. When creating a list Screenlet, however, you don't have to create a 
separate listener. Developers can use your list Screenlet in an activity or 
fragment by implementing 
[the `BaseListListener` interface](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListListener.java) 
parameterized with your model entity. For example, to use Bookmark List 
Screenlet in an activity, an app developer's activity declaration could look 
like this: 

    public class BookmarkListActivity extends AppCompatActivity 
        implements BaseListListener<Bookmark> {...

The `BaseListListener` interface defines the following methods that the app 
developer can implement in their activity or fragment:

- `void onListPageFailed(int startRow, int endRow, Exception e);`: 
  Respond to the Screenlet's failure to retrieve entities from the server. 

- `void onListPageReceived(int startRow, int endRow, List<E> entries, int rowCount);`: 
  Respond to the Screenlet's success to retrieve entities from the server. 

- `void onListItemSelected(E element, View view)`: Respond to a user selection 
  in the list. 

If these methods meet your list Screenlet's needs, then you can move on to the 
next section in this tutorial. If you want to let app developers respond to more 
actions, however, you must create your own listener that extends 
`BaseListListener` with your model entity as a type parameter. For example, you 
could create a `BookmarkListListener` listener for Bookmark List Screenlet that 
notifies the app developer when the Interactor is called: 

    public interface BookmarkListListener extends BaseListListener<Bookmark> {
        void interactorCalled();
    }

Next, you'll create the Screenlet class. 

## Creating the Screenlet Class [](id=creating-the-screenlet-class)

Now you're ready to create your Screenlet class. Recall that the Screenlet class 
serves as your Screenlet's focal point. It governs the Screenlet's behavior and 
is the primary component the app developer interacts with. As with non-list 
Screenlets, you should first define any XML attributes that you want to make 
available to the app developer. For example, Bookmark List Screenlet needs to 
make its `groupId` and `folderId` available as attributes the developer can set 
in the Screenlet's XML. You should also add an attribute for setting the 
Screenlet's 
[cache policy](/develop/tutorials/-/knowledge_base/7-0/architecture-of-offline-mode-in-liferay-screens#using-policies-with-offline-mode). Do this by creating the following 
`bookmark_attrs.xml` file in the `res/values` folder: 

    <?xml version="1.0" encoding="utf-8"?>
    <resources>
	      <declare-styleable name="BookmarkListScreenlet">
		        <attr name="groupId"/>
            <attr name="folderId"/>
            <attr format="string" name="comparator"/>
            <attr name="cachePolicy"/>
        </declare-styleable>
    </resources>

Your Screenlet class must extend `BaseListScreenlet` with your model entity and 
Interactor implementation as type parameters. You must also create variables 
for each of the XML attributes you just defined. For constructors, you can 
leverage the superclass constructors. For example, here is the first part of 
`BookmarkListScreenlet`, the Screenlet class of Bookmark List Screenlet: 

    public class BookmarkListScreenlet 
        extends BaseListScreenlet<Bookmark, BookmarkListInteractor> {

        private long folderId;
	      private String comparator;

	      public BookmarkListScreenlet(Context context) {
		        super(context);
	      }

	      public BookmarkListScreenlet(Context context, AttributeSet attrs) {
		        super(context, attrs);
	      }

	      public BookmarkListScreenlet(Context context, AttributeSet attrs, int defStyleAttr) {
		        super(context, attrs, defStyleAttr);
	      }

	      public BookmarkListScreenlet(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		        super(context, attrs, defStyleAttr, defStyleRes);
	      }

        ...

Next, add the method that reads the values of the XML attributes you defined 
earlier and creates the Screenlet's view: the `createScreenletView` method. 
Recall that this method assigns the attribute values to their corresponding 
instance variables. For example, the `createScreenletView` method in 
`BookmarkListScreenlet` assigns the `groupId`, `folderId`, and `comparator` 
attribute values to variables of the same name: 

    @Override
    protected View createScreenletView(Context context, AttributeSet attributes) {
	      TypedArray typedArray =
		        context.getTheme().obtainStyledAttributes(attributes, R.styleable.BookmarkListScreenlet, 0, 0);
	      groupId = typedArray.getInt(R.styleable.BookmarkListScreenlet_groupId, (int) LiferayServerContext.getGroupId());
	      folderId = typedArray.getInt(R.styleable.BookmarkListScreenlet_folderId, 0);
	      comparator = typedArray.getString(R.styleable.BookmarkListScreenlet_comparator);
	      typedArray.recycle();

	      return super.createScreenletView(context, attributes);
    }

Lastly, you must add the `createInteractor` and `loadRows` methods. These 
methods create an Interactor instance and load the list rows, respectively. The 
`loadRows` method in `BookmarkListScreenlet` also retrieves a listener instance 
so it can call the listener's `interactorCalled` method:

    @Override
    protected void loadRows(BookmarkListInteractor interactor) {

		    ((BookmarkListListener) getListener()).interactorCalled();

		    interactor.start(folderId, comparator);
	  }

    @Override
    protected BookmarkListInteractor createInteractor(String actionName) {
		    return new BookmarkListInteractor();
	  }

You're done! Your Screenlet is a ready-to-use component that you can use in your 
app. You can even
[package your Screenlet](/develop/tutorials/-/knowledge_base/7-0/packaging-your-android-screenlets)
and contribute it to the Screens project, or distribute it in Maven Central or 
jCenter. 

## Using the Screenlet [](id=using-the-screenlet)

You can now use the new Screenlet 
[the same way you use any other Screenlet](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-android-apps): 

1. Insert the Screenlet’s XML in the layout of the activity or fragment you want 
   to use the Screenlet in. For example, here's Bookmark List Screenlet's XML: 

        <com.liferay.mobile.screens.listbookmark.BookmarkListScreenlet
            android:id="@+id/bookmarklist_screenlet"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:comparator=”PATH_TO_COMPARATOR"
            app:folderId="YOUR_FOLDER_ID"
            app:groupId="YOUR_GROUP_ID"
            app:layoutId="@layout/list_bookmarks"/>

2. Implement the Screenlet’s listener in the activity or fragment class. If your 
   list Screenlet doesn't have a custom listener, then you can do this by 
   implementing `BaseListListener` parameterized by your model class. For 
   example:

        public class YourListActivity extends AppCompatActivity 
            implements BaseListListener<YourEntity> {...

    If you created a custom listener for your list Screenlet, however, then your 
    activity or fragment must implement it instead. For example, recall that the 
    example Bookmark List Screenlet's listener is `BookmarkListListener`. To use 
    this Screenlet, you must therefore implement this listener in the class of 
    the activity or fragment that you want to use the Screenlet in. For example: 

        public class ListBookmarksActivity extends AppCompatActivity 
            implements BookmarkListListener {...

    See the full example of this 
    [here in GitHub](https://github.com/liferay/liferay-screens/blob/master/android/samples/test-app/src/main/java/com/liferay/mobile/screens/testapp/ListBookmarksActivity.java). 

## Related Topics [](id=related-topics)

[Creating Android Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-android-screenlets)

[Architecture of Liferay Screens for Android](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-android)

[Packaging Your Android Screenlets](/develop/tutorials/-/knowledge_base/7-0/packaging-your-android-screenlets)

[Using Views in Android Screenlets](/develop/tutorials/-/knowledge_base/7-0/using-views-in-android-screenlets)

[Using Screenlets in Android Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-android-apps)
