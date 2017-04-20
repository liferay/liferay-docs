# Creating Guestbook List Screenlet's UI [](id=creating-guestbook-list-screenlets-ui)

Recall that in Liferay Screens for Android, Screenlet UIs are called Views, and 
every Screenlet must have at least one View. In this article, you'll use the 
following steps to create a View for Guestbook List Screenlet: 

1. Create the row layout. This layout defines the UI for each row in the list. 

2. Create the adapter class. The adapter fills a row layout instance with the 
   data for one list item. This repeats for each list item. 

3. Create the View class. This class renders the UI, handles user interactions, 
   and communicates with the Screenlet class. 

4. Create the View's layout. This layout defines the Screenlet's UI as a whole. 
   For a list Screenlet, this is a scrollable list. 

Note that these are the same steps for creating a View as those in the list 
Screenlet tutorial. Therefore, this article doesn't explain the concepts or code 
in detail. For such an explanation, 
[click here](/develop/tutorials/-/knowledge_base/7-0/creating-android-list-screenlets#creating-the-screenlets-view) 
to see the tutorial. 

You'll create Guestbook List Screenlet's View in its own package inside the 
`guestbooklistscreenlet` package. Create a new package named `view` inside the 
`guestbooklistscreenlet` package. Now you're ready to create the row layout. 

## Creating the Row Layout [](id=creating-the-row-layout)

First, you must create the layout that defines the Screenlet's UI for each list 
row. Since each row in Guestbook List Screenlet displays only a single 
guestbook's name, its layout needs to contain only a single `TextView`. Create 
the following layout file `res/layout/guestbook_row.xml`: 

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

Note that the `textSize` and `padding` values result in clean, readable text for 
this example. When you develop your own list Screenlets, you can style your row 
layouts however you like. 

## Creating the Adapter Class [](id=creating-the-adapter-class)

Recall that an adapter class is required to fill each row with data. Guestbook 
List Screenlet's adapter class, `GuestbookAdapter`, is almost identical to 
[the adapter class in the list Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-android-list-screenlets#creating-the-adapter-class). 
The only difference is that `GuestbookAdapter` handles a guestbook's name. 

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

            private final TextView name;

            public GuestbookViewHolder(View view, BaseListAdapterListener listener) {
                super(view, listener);

                name = (TextView) view.findViewById(R.id.guestbook_name);
            }

            public void bind(GuestbookModel entry) {
                name.setText(entry.getName());
            }

        }
    }

In the view holder, the `name` variable accounts for the guestbook's name. The 
constructor assigns the `TextView` from `guestbook_row.xml` to this variable. 
The `bind` method then sets the guestbook's name as the `TextView`'s text. 

Next, you'll create the View class. 

## Creating the View Class [](id=creating-the-view-class)

Recall that the View class controls a Screenlet's UI. Guestbook List Screenlet's 
View class, `GuestbookListView`, is almost identical to 
[the View class in the list Screenlet creation tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-android-list-screenlets#creating-the-view-class). 
The only difference is that `GuestbookListView` displays guestbooks. 

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

        public GuestbookListView(Context context) {
            super(context);
        }

        public GuestbookListView(Context context, AttributeSet attributes) {
            super(context, attributes);
        }

        public GuestbookListView(Context context, AttributeSet attributes, int defaultStyle) {
            super(context, attributes, defaultStyle);
        }

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
lets you override the `createListAdapter` method to instantiate and return a 
`GuestbookAdapter` instance. You also override the `getItemLayoutId` method to 
return the ID of the row layout you defined earlier (`guestbook_row`). 

Now you're ready to create your View's main layout. 

## Creating the View's Layout [](id=creating-the-views-layout)

Even though you already have a row layout (`guestbook_row.xml`), your View needs 
a layout for the list as a whole. Recall from 
[the list Screenlet tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-android-list-screenlets#creating-the-views-main-layout) 
that the View layout for all list Screenlets is identical apart from the styling 
and the referenced View class. The layout for Guestbook List Screenlet's View, 
`list_guestbooks.xml`, must reference `GuestbookListView` and contain the same 
`ProgressBar` and `RecyclerView` as all list Screenlet View layouts. Even the 
`android:id` values are the same; they're hardcoded into the list Screenlet 
framework and changing them will cause your app to crash. 

Create the layout file `res/layout/list_guestbooks.xml` and replace its contents 
with this code: 

    <com.liferay.docs.guestbooklistscreenlet.view.GuestbookListView
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
    </com.liferay.docs.guestbooklistscreenlet.view.GuestbookListView>

Great! You're done with Guestbook List Screenlet's View. Next, you'll create the 
Screenlet's Interactor. 
