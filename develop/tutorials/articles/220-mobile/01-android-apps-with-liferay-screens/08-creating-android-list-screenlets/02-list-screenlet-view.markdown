# Creating the View [](id=creating-the-view)

Recall from 
[the basic Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/7-1/creating-the-ui) 
that a View defines a Screenlet's UI. To accommodate its list, a list 
Screenlet's View is constructed a bit differently than that of a non-list 
Screenlet. To create a List Screenlet's View, you'll create the following 
components: 

1. Row Layout: the layout for each list row. 
2. Adapter Class: an 
   [Android adapter class](http://developer.android.com/guide/topics/ui/declaring-layout.html#AdapterViews) 
   that populates each list row with data. 
3. View Class: the class that controls the View. This class serves the same 
   purpose in list Screenlets as it does in non-list Screenlets. 
4. Main Layout: the layout for the list as a whole. Note this is different from 
   the row layout, which defines the UI for individual rows. 

First, you'll create the row layout. 

## Creating the Row Layout [](id=creating-the-row-layout)

Before constructing the rest of the View, you should first define the layout to 
use for each row in the list. For example, Bookmark List Screenlet needs to 
display a bookmark in each row. Its row layout (`res/layout/bookmark_row.xml`) 
is therefore a `LinearLayout` containing a single `TextView` that displays the 
bookmark's URL: 

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

As you can see, this example is very simple. Row layouts, however, can be as 
simple or complex as you need them to be to display your content. 

Next, you'll create the adapter class. 

## Creating the Adapter Class [](id=creating-the-adapter-class)

[Android adapters](https://developer.android.com/guide/topics/ui/declaring-layout.html#AdapterViews) 
fill a layout with content. In the example Bookmark List Screenlet, the layout 
is the row layout 
(`bookmark_row.xml`) and the content is each list item (a URL). To make list 
scrolling smooth, the adapter class should use an 
[Android view holder](https://developer.android.com/training/improving-layouts/smooth-scrolling.html#ViewHolder). 
To make this easier, you can extend the list Screenlet framework's 
[`BaseListAdapter` class](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListAdapter.java) 
with your model class and view holder as type arguments. By extending 
`BaseListAdapter`, your adapter needs only two methods: 

- `createViewHolder`: instantiates the view holder 
- `fillHolder`: fills in the view holder for each row 

Your view holder should also contain variables for any data each row needs to 
display. The view holder must assign these variables to the corresponding row 
layout elements, and set the appropriate data to them. 

For example, Bookmark List Screenlet's adapter class (`BookmarkAdapter`) extends 
`BaseListAdapter` with `Bookmark` and `BookmarkAdapter.BookmarkViewHolder` as 
type arguments. This class's view holder is an inner class that extends 
`BaseListAdapter`'s view holder. Since Bookmark List Screenlet only needs to 
display a URL in each row, the view holder only needs one variable: `url`. The 
view holder's constructor assigns the `TextView` from `bookmark_row.xml` to this 
variable. The `bind` method then sets the bookmark's URL as the `TextView`'s 
text. The other methods in `BookmarkAdapter` leverage the view holder. The 
`createViewHolder` method instantiates `BookmarkViewHolder`. The `fillHolder` 
method calls the view holder's `bind` method to set the bookmark's URL as the 
`url` variable's text: 

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

Great! Your adapter class is finished. Next, you'll create the View class. 

## Creating the View Class [](id=creating-the-view-class)

Now that your adapter exists, you can create your list Screenlet's View class. 
Recall from 
[the basic Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/7-1/creating-the-ui) 
that the View class is the central hub of any Screenlet's UI. It renders the UI, 
handles user interactions, and communicates with the Screenlet class. The list 
Screenlet framework provides most of this functionality for you via 
[the `BaseListScreenletView` class](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListScreenletView.java). 
Your View class must extend this class to provide your row layout ID and an 
instance of your adapter. You'll do this by overriding `BaseListScreenletView`'s 
`getItemLayoutId` and `createListAdapter` methods. Note that in many cases this 
is the only custom functionality your View class needs. If it needs more, you 
can provide it by creating new methods or overriding other 
`BaseListScreenletView` methods. 

Create your View class by extending `BaseListScreenletView` with your model 
class, view holder, and adapter as type arguments. This is required for your 
View class to represent your model objects in a view holder, inside an adapter. 
For example, Bookmark List Screenlet's View class (`BookmarkListView`) must 
represent `Bookmark` instances in a `BookmarkViewHolder` inside a 
`BookmarkAdapter`. The `BookmarkListView` class must therefore extend 
`BaseListScreenletView` parameterized with `Bookmark`, 
`BookmarkAdapter.BookmarkViewHolder`, and `BookmarkAdapter`. Besides overriding 
`createListAdapter` to return a `BookmarkAdapter` instance, the only other 
functionality that this View class needs to support is to get the layout for 
each row in the list. The overridden `getItemLayoutId` method does this by 
returning the row layout `bookmark_row`: 

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

Next, you'll create your View's main layout. 

## Creating the View's Main Layout [](id=creating-the-views-main-layout)

Although you already created a layout for your list rows, you must still create 
a layout to define the list as a whole. This layout must contain: 

- The View class's fully qualified name as the layout's first element. 
- An 
  [Android `RecyclerView`](http://developer.android.com/training/material/lists-cards.html#RecyclerView) 
  to let your app efficiently scroll through a potentially large list of items.
- An 
  [Android `ProgressBar`](https://developer.android.com/reference/android/widget/ProgressBar.html) 
  to indicate progress when loading the list. 

Apart from the View class and styling, this layout's code is the same for all 
list Screenlets. For example, here's Bookmark List Screenlet's layout 
`res/layout/list_bookmarks.xml`: 

    <com.liferay.mobile.screens.listbookmark.BookmarkListView
        xmlns:android="http://schemas.android.com/apk/res/android"
        android:id="@+id/liferay_list_screenlet"
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

**Warning:** The `android:id` values in your View's layout XML must **exactly** 
match the ones shown here. These values are hardcoded into the Screens framework 
and changing them will cause your app to crash. 

$$$

Great job! Your View is finished. Next, you'll create your Screenlet's 
Interactor. 

## Related Topics [](id=related-topics)

[Creating the Model Class](/develop/tutorials/-/knowledge_base/7-1/creating-the-model-class)

[Creating the Interactor](/develop/tutorials/-/knowledge_base/7-1/creating-the-interactor-0)

[Creating the Screenlet Class](/develop/tutorials/-/knowledge_base/7-1/creating-the-screenlet-class-0)
