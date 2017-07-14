# Creating Guestbook List Screenlet's UI [](id=creating-guestbook-list-screenlets-ui)

Recall that in Liferay Screens for Android, Screenlet UIs are called Views, and 
every Screenlet must have at least one View. In this article, you'll use the 
following steps to create a View for Guestbook List Screenlet: 

1. Create the row layout. This layout defines the UI for each row instance in 
   the list. 

2. Create the adapter class. This is an 
   [Android adapter](https://developer.android.com/guide/topics/ui/declaring-layout.html#AdapterViews) 
   that fills a row layout instance with the data for one list item. This 
   repeats for each list item until the list is full. 

3. Create the View class. This class renders the UI, handles user interactions, 
   and communicates with the Screenlet class. 

4. Create the View's layout. This layout defines the Screenlet's UI as a whole. 
   For a list Screenlet, this is a scrollable list. 

Note that these are the same steps for creating a View as those in 
[the list Screenlet tutorial](/develop/tutorials/-/knowledge_base/6-2/creating-android-list-screenlets#creating-the-screenlets-view). 

You'll create Guestbook List Screenlet's View in its own package inside the 
`guestbooklistscreenlet` package. Create a new package named `view` inside the 
`guestbooklistscreenlet` package. Now you're ready to create the row layout. 

## Creating the Row Layout [](id=creating-the-row-layout)

First, you must create the layout that defines the UI for each row instance in 
the list. Since each row in Guestbook List Screenlet displays only a single 
guestbook's name, the row layout only needs a single `TextView`. Create the 
layout file `res/layout/guestbook_row.xml` and paste in this content: 

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

[Android adapters](https://developer.android.com/guide/topics/ui/declaring-layout.html#AdapterViews) 
fill a layout with content. In Guestbook List Screenlet, the layout is 
`guestbook_row.xml` (the row layout) and the content is a guestbook's name. To 
make list scrolling smooth, the adapter class should use an 
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

This adapter class extends `BaseListAdapter` with `GuestbookModel` and 
`GuestbookAdapter.GuestbookViewHolder` as type arguments. The view holder is an 
inner class that extends `BaseListAdapter`'s view holder. Since Guestbook List 
Screenlet only needs to display a guestbook's name in each row, the view holder 
only needs one variable: `name`. The view holder's constructor assigns the 
`TextView` from `guestbook_row.xml` to this variable. The `bind` method then 
sets the guestbook's name as the `TextView`'s text. The other methods in 
`GuestbookAdapter` leverage the view holder. The `createViewHolder` method 
instantiates `GuestbookViewHolder`. The `fillHolder` method calls the view 
holder's `bind` method to set the guestbook's name as the `name` variable's 
text. 

Next, you'll create the View class. 

## Creating the View Class [](id=creating-the-view-class)

Recall that the View class controls a Screenlet's UI. It renders the UI, 
handles user interactions, and communicates with the Screenlet class. The list 
Screenlet framework provides most of this functionality for you via 
[the `BaseListScreenletView` class](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListScreenletView.java). 
Your View class must extend this class to provide your row layout ID and an 
instance of your adapter. You'll do this by overriding `BaseListScreenletView`'s 
`getItemLayoutId` and `createListAdapter` methods. Also, when you extend 
`BaseListScreenletView` you must do so with your model class, view holder, and 
adapter as type arguments. This is required for your View class to represent 
your model objects in a view holder, inside an adapter. 

Create the `GuestbookListView` class inside the `view` package, and replace its 
contents with this code: 

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
 
This View class represents `GuestbookModel` instances in a `GuestbookViewHolder` 
inside a `GuestbookAdapter`. This class therefore extends 
`BaseListScreenletView` parameterized with `GuestbookModel`, 
`GuestbookAdapter.GuestbookViewHolder`, and `GuestbookAdapter`. Besides 
overriding `createListAdapter` to return a `GuestbookAdapter` instance, the only 
other functionality that this View class needs to support is to get the layout 
for each row in the list. The overridden `getItemLayoutId` method does this by 
returning the row layout `guestbook_row`. 

Now you're ready to create your View's main layout. 

## Creating the View's Layout [](id=creating-the-views-layout)

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
list Screenlets. 

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

Note that the `android:id` values in this layout XML are hardcoded into the 
Screens framework and changing them will cause your app to crash. 

Great! You're done with Guestbook List Screenlet's View. Next, you'll create the 
Screenlet's Interactor. 
