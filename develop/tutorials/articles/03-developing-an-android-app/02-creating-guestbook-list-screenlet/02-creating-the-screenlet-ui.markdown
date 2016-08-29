# Creating Guestbook List Screenlet's UI

In Liferay Screens for Android, Screenlet UIs are called Views. Every Screenlet 
must have at least one View. In this article, you'll use the following steps to 
create a View for the Guestbook List Screenlet. Note that these are the same 
basic steps in 
[the list Screenlet tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-android-list-screenlets) 
for creating a list Screenlet's View: 

1. Create the row layout. This is the layout the Screenlet will use to display 
   each row in the list. 

2. Create the adapter class. The adapter fills in a row layout instance with the 
   data for each list item. 

3. Create the View class. This class renders the UI, handles user interactions, 
   and communicates with the Screenlet class. 

4. Create the View's layout. This layout defines the Screenlet's UI as a whole, 
   which in this case is a scrollable list. 

You'll create the Guestbook List Screenlet's View in its own package inside the 
`guestbooklistscreenlet` package. Get started by creating a new package named 
`view` inside the `guestbooklistscreenlet` package. Now you're ready to create 
the row layout. 

## Creating the Row Layout

First, you must create the Screenlet's layout for each list row. Since each row 
in Guestbook List Screenlet displays only a single guestbook's name, this layout 
is very simple. Create the following `guestbook_row.xml` file in the 
`res/layout` directory:

    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <TextView
            android:id="@+id/guestbook_name"
            android:textSize="25sp"
            android:padding="10dp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/>

    </LinearLayout>

You'll use the `TextView` to display a guestbook's name. Note that you don't 
have to use a `textSize` of `25sp` or a `padding` of `10dp`. These values serve 
as an example because they result in clean, readable text. 

## Creating the Adapter Class

[Android adapters](https://developer.android.com/guide/topics/ui/declaring-layout.html#AdapterViews) 
fill a layout with content. In this Screenlet, the layout is the row layout 
(`guestbook_row.xml`) and the content is each list item (a guestbook's name). To 
make list scrolling smooth, the adapter class should use an 
[Android view holder](https://developer.android.com/training/improving-layouts/smooth-scrolling.html#ViewHolder). 
To make this easier, the list Screenlet framework contains 
[the abstract class `BaseListAdapter`](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListAdapter.java) 
that you can extend to create your own adapter class with a view holder. By 
extending this class, your adapter only needs two methods: 

- `createViewHolder`: Instantiates your view holder. 

- `fillHolder`: Fills in each of your view holder's rows. 

Your view holder should also contain variables for any data each row needs to 
display. The view holder must assign these variables to the corresponding row 
layout elements, and set the appropriate data to them. In this case, you only 
need to do this for the guestbook's name. 

Inside the Screenlet's `view` package, create the following `GuestbookAdapter` 
class: 

    package com.liferay.docs.guestbooklistscreenlet.view;

    import android.support.annotation.NonNull;
    import android.view.View;
    import android.widget.TextView;

    import com.liferay.docs.liferayguestbook.R;
    import com.liferay.docs.model.GuestbookModel;
    import com.liferay.mobile.screens.base.list.BaseListAdapter;
    import com.liferay.mobile.screens.base.list.BaseListAdapterListener;

    public class GuestbookAdapter extends 
        BaseListAdapter<GuestbookModel, GuestbookAdapter.GuestbookViewHolder> {

        public GuestbookAdapter(int layoutId, int progressLayoutId, BaseListAdapterListener listener) {
            super(layoutId, progressLayoutId, listener);
        }

        @NonNull
        @Override
        public GuestbookViewHolder createViewHolder(View view, BaseListAdapterListener listener) {
            return new GuestbookAdapter.GuestbookViewHolder(view, listener);
        }

        @Override
        protected void fillHolder(GuestbookModel entry, GuestbookViewHolder holder) {
            holder.bind(entry);
        }

        public class GuestbookViewHolder extends BaseListAdapter.ViewHolder {

            private final TextView _name;

            public GuestbookViewHolder(View view, BaseListAdapterListener listener) {
                super(view, listener);

                _name = (TextView) view.findViewById(R.id.guestbook_name);
            }

            public void bind(GuestbookModel entry) {
                _name.setText(entry.getName());
            }

        }
    }

This class's view holder is an inner class that extends 
[`BaseListAdapter`'s view holder](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListAdapter.java#L120-L138). 
Since the Guestbook List Screenlet only needs to display a guestbook's name in 
each row, the view holder only needs one variable: `_name`. The view holder's 
constructor assigns the `TextView` from `guestbook_row.xml` to this variable. 
The `bind` method then sets the guestbook's name as the `TextView`'s text. 

The other methods in `GuestbookAdapter` leverage the view holder. The 
`createViewHolder` method calls the view holder's constructor to create new 
`GuestbookViewHolder` instances. The `fillHolder` method calls the view holder's 
`bind` method to set the guestbook's name as the `_name` variable's text. 

Next, you'll create the View class. 

## Creating the View Class

The View class is the central hub of any Screenlet's UI. It renders the UI, 
handles user interactions, and communicates with the Screenlet class. Screens 
provides most of this functionality for you via 
[the `BaseListScreenletView` class](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListScreenletView.java). 
Your View class, however, must extend this class to provide your row layout ID 
and an instance of your adapter. You'll do this by overriding 
`BaseListScreenletView`'s `getItemLayoutId` and `createListAdapter` methods, 
respectively. Note that this is the only custom functionality your View class 
needs. If it needed more, you could provide it by creating new methods or 
overriding other `BaseListScreenletView` methods. 

`BaseListScreenletView` displays the server call's results by implementing the 
[`BaseListViewModel` interface](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/view/BaseListViewModel.java). 
This interface defines two methods: 

- `showFinishOperation(int startRow, int endRow, List<E> entries, int rowCount)`: 
  Receives the list of objects (`List<E> entries`) that results from a 
  successful server call. 
  [This method's implementation in `BaseListScreenletView`](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListScreenletView.java#L81-L133) 
  fills the adapter with these objects. 

- `showFinishOperation(int startRow, int endRow, Exception e)`: Receives the 
  `Exception` that results from a failed server call. 
  [This method's implementation in `BaseListScreenletView`](https://github.com/liferay/liferay-screens/blob/1.4.1/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListScreenletView.java#L140-L145) 
  sets the visibility of the View's `ProgressBar` and `RecyclerView` (you'll 
  create these shortly), and logs the `Exception`. 
<!-- insert diagram -->

Later, when you create your Screenlet class, you'll see how the list Screenlet 
framework calls these methods. For now, just be aware that 
`BaseListScreenletView`'s `BaseListViewModel` implementation updates the UI. You 
should also note that every Screenlet's View requires a View Model interface to 
define the methods that update the UI. The list Screenlet framework is unique in 
that it provides this interface for you. If you were creating a non-list 
Screenlet, you'd need to create this interface manually. 

Now you're ready to create your View class. You'll do this by extending 
`BaseListScreenletView` with your model class, view holder, and adapter as type 
arguments. This is required for your View class to represent `GuestbookModel` 
objects in a `GuestbookViewHolder`, inside a `GuestbookAdapter`. 

Create the `GuestbookListView` class inside the `view` package, and replace its 
contents with the following: 

    package com.liferay.docs.guestbooklistscreenlet.view;

    import android.content.Context;
    import android.util.AttributeSet;

    import com.liferay.docs.liferayguestbook.R;
    import com.liferay.docs.model.GuestbookModel;
    import com.liferay.mobile.screens.base.list.BaseListScreenletView;

    public class GuestbookListView extends BaseListScreenletView<GuestbookModel, 
        GuestbookAdapter.GuestbookViewHolder, GuestbookAdapter> {

        // Superclass constructors
        public GuestbookListView(Context context) {
            super(context);
        }

        public GuestbookListView(Context context, AttributeSet attributes) {
            super(context, attributes);
        }

        public GuestbookListView(Context context, AttributeSet attributes, int defaultStyle) {
            super(context, attributes, defaultStyle);
        }

        // Overridden methods
        @Override
        protected GuestbookAdapter createListAdapter(int itemLayoutId, int itemProgressLayoutId) {
            return new GuestbookAdapter(itemLayoutId, itemProgressLayoutId, this);
        }

        @Override
        protected int getItemLayoutId() {
            return R.layout.guestbook_row;
        }
    }

Extending `BaseListScreenletView` with `GuestbookModel`, 
`GuestbookAdapter.GuestbookViewHolder`, and `GuestbookAdapter` as type arguments 
lets you override the `createListAdapter` method to return a new 
`GuestbookAdapter` instance. Also, you override the `getItemLayoutId` method to 
return the ID of the row layout you defined earlier (`guestbook_row`). 

Now you're ready to create your View's main layout. 

## Creating the View's Layout

Although you already created `guestbook_row.xml` to define each list row's 
layout, you must still create a layout to define the list as a whole. This 
layout should reference the View class and define the list's components. Since 
this Screenlet lets the user scroll through a multi-page list, its layout should 
contain 
[an Android `ProgressBar`](https://developer.android.com/reference/android/widget/ProgressBar.html) 
to show the user when it's loading more list items. The layout should also 
contain 
[an Android `RecyclerView`](https://developer.android.com/training/material/lists-cards.html) 
to ensure the list scrolls smoothly. 

Create the file `list_guestbooks.xml` in the `res/layout` directory, and replace 
its contents with the following: 

    <com.liferay.docs.guestbooklistscreenlet.view.GuestbookListView
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
    </com.liferay.docs.guestbooklistscreenlet.view.GuestbookListView>

Note that these `android:id` values are hardcoded into the Liferay Screens 
framework. Changing them will cause your app to crash. 

Great! You're done with Guestbook List Screenlet's View. You're almost ready to 
write the Screenlet's server call. Because Screenlets use the Mobile SDK to make 
server calls, you should first learn how Mobile SDK calls work. The next article 
shows you this. 
