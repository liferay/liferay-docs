# Creating Entry List Screenlet's UI

Once you have the model class for entries, you can create the Screenlet's UI. 
Recall that in Liferay Screens for Android, you create a Screenlet's UI by 
implementing a View. In this article, you'll create Entry List Screenlet's View 
by using the same sequence of steps you used to create Guestbook List 
Screenlet's View: 

1. Create the row layout. The Screenlet uses this layout for each row in the 
   list. 

2. Create the adapter class. The adapter fills a row layout instance with the 
   data for one list item. This repeats for each list item. 

3. Create the View class. This class renders the UI, handles user interactions, 
   and communicates with the Screenlet class. 

4. Create the View's layout. This layout defines the Screenlet's UI as a whole, 
   which in this case is a scrollable list. 

As you follow these steps, you'll see that Entry List Screenlet's View shares a 
great deal of code with Guestbook List Screenlet's View. The biggest difference 
between these Views is that one displays guestbooks and the other displays 
entries. The mechanisms they use to display data, however, are almost identical. 

To get started, create a new package named `view` inside the 
`getentriesscreenlet` package. You'll create the the row layout first. 

## Creating the Row Layout

First, you must create the layout the Screenlet uses to display each row in the 
list. Recall that in Guestbook List Screenlet, `guestbook_row.xml` serves this 
purpose with a single `TextView` used to display a guestbook's name. You'll 
create a similar layout here for Entry List Screenlet, but you'll use two 
`TextView` elements: one for the entry, and one for the name of the person that 
left it. Create `entry_row.xml` in your app's `res/layout` directory, and 
replace its contents with the following: 

    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <TextView
            android:id="@+id/entry_message"
            android:textSize="25sp"
            android:textStyle="bold"
            android:paddingLeft="10dp"
            android:paddingRight="10dp"
            android:paddingBottom="1dp"
            android:paddingTop="10dp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/>

        <TextView
            android:id="@+id/entry_name"
            android:textSize="15sp"
            android:paddingLeft="10dp"
            android:paddingRight="10dp"
            android:paddingBottom="10dp"
            android:paddingTop="1dp"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/>

    </LinearLayout>

You'll use the first `TextView` (`entry_message`) to display the entry, and the 
second `TextView` (`entry_name`) to display the name of the person that left it. 
The `padding` settings in each `TextView` element group the text closer together 
and create extra space at the top and bottom of the row. This makes it clear 
that the text in each row belongs together as a single list item. Of course, 
this is only one of many possible representations. You're free to style each row 
as you wish. 

Next, you'll create your Screenlet's adapter class.

## Creating the Adapter Class

Recall that an adapter class is required to fill each row with data. 
Fortunately, Entry List Screenlet's adapter class is almost identical to that of 
Guestbook List Screenlet. The only difference, besides working with `EntryModel` 
instead of `GuestbookModel`, is that it needs two variables: one for the entry, 
and one for the name of the person who left it. Guestbook List Screenlet's 
adapter class only needs a variable for the guestbook's name. 

Inside the Entry List Screenlet's `view` package, create the following 
`EntryAdapter` class: 

    package com.liferay.docs.entrylistscreenlet.view;

    import android.support.annotation.NonNull;
    import android.view.View;
    import android.widget.TextView;

    import com.liferay.docs.liferayguestbook.R;
    import com.liferay.docs.model.EntryModel;
    import com.liferay.mobile.screens.base.list.BaseListAdapter;
    import com.liferay.mobile.screens.base.list.BaseListAdapterListener;


    public class EntryAdapter extends BaseListAdapter<EntryModel, EntryAdapter.EntryViewHolder> {

        public EntryAdapter(int layoutId, int progressLayoutId, BaseListAdapterListener listener) {
            super(layoutId, progressLayoutId, listener);
        }

        @NonNull
        @Override
        public EntryViewHolder createViewHolder(View view, BaseListAdapterListener listener) {
            return new EntryAdapter.EntryViewHolder(view, listener);
        }

        @Override
        protected void fillHolder(EntryModel entry, EntryViewHolder holder) {
            holder.bind(entry);
        }

        public class EntryViewHolder extends BaseListAdapter.ViewHolder {

            private final TextView _message;
            private final TextView _name;

            public EntryViewHolder(View view, BaseListAdapterListener listener) {
                super(view, listener);

                _message = (TextView) view.findViewById(R.id.entry_message);
                _name = (TextView) view.findViewById(R.id.entry_name);
            }

            public void bind(EntryModel entry) {
                _message.setText(entry.getMessage());
                _name.setText(entry.getName());
            }

        }
    }

For an explanation of how this code works, see the section on the adapter class 
[in the article on Creating Guestbook List Screenlet's UI](https://www.liferay.com/). 

Now you're ready to create the View class. 

## Creating the View Class

Recall that the View class controls a Screenlet's UI. Because Entry List 
Screenlet is so similar to Guestbook List Screenlet, their View classes are 
almost identical. The only difference is--you guessed it--one uses entries and 
the other uses guestbooks. For a full explanation of what the View class does, 
how it's structured, and why, see the section on the View class in 
[the article on Creating Guestbook List Screenlet's UI](https://www.liferay.com/). 

Create the `EntryListView` class inside Entry List Screenlet's `view` package, 
and replace its contents with the following: 

    package com.liferay.docs.entrylistscreenlet.view;

    import android.content.Context;
    import android.util.AttributeSet;

    import com.liferay.docs.liferayguestbook.R;
    import com.liferay.docs.model.EntryModel;
    import com.liferay.mobile.screens.base.list.BaseListScreenletView;


    public class EntryListView extends BaseListScreenletView<EntryModel, 
        EntryAdapter.EntryViewHolder, EntryAdapter> {

        public EntryListView(Context context) {
            super(context);
        }

        public EntryListView(Context context, AttributeSet attributes) {
            super(context, attributes);
        }

        public EntryListView(Context context, AttributeSet attributes, int defaultStyle) {
            super(context, attributes, defaultStyle);
        }

        @Override
        protected EntryAdapter createListAdapter(int itemLayoutId, int itemProgressLayoutId) {
            return new EntryAdapter(itemLayoutId, itemProgressLayoutId, this);
        }

        @Override
        protected int getItemLayoutId() {
            return R.layout.entry_row;
        }
    }

Fabulous work! Remember, if you need an explanation of how this code works, see 
the section on the View class in 
[the article on Creating Guestbook List Screenlet's UI](https://www.liferay.com/). 

Next, you'll create your View's main layout. 

## Creating the View's Layout

In the first step, you created a layout for each list row. Recall from 
[the article on Creating Guestbook List Screenlet's UI](https://www.liferay.com/) 
that you must also create a layout for the list as a whole. Although you may be 
getting tired of hearing this, it's saving you a great deal of work: Entry List 
Screenlet's layout is almost identical to that of Guestbook List Screenlet. The 
only difference is that Entry List Screenlet's layout uses `EntryListView` 
instead of `GuestbookListView`. 

Create the file `list_entries.xml` in the `res/layout` directory, and replace 
its contents with the following: 

    <com.liferay.docs.entrylistscreenlet.view.EntryListView
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
    </com.liferay.docs.entrylistscreenlet.view.EntryListView>

For a full explanation of this layout, see the section on creating the layout in 
[the article on Creating Guestbook List Screenlet's UI](https://www.liferay.com/). 
You should also be sure not to change the layout's `android:id` values. They're 
hardcoded into the Liferay Screens framework. Changing them will cause your app 
to crash. 

Nice job! You're done creating Entry List Screenlet's View. Next, you'll create 
the Screenlet's server call. 
