# Creating the Get Entries Screenlet's UI [](id=creating-the-get-entries-screenlets-ui)

Once you have the model class for entries, you can create the Screenlet's UI. 
Recall that in Liferay Screens for Android, you create a Screenlet's UI by 
implementing a View. A View Model interface, layout XML, and View class comprise 
a View. In this article, you'll create Get Entries Screenlet's View by using the 
same set of steps you used to create Get Guestbooks Screenlet's View:

1. Create the View Model interface.

2. Create the View's layout XML.

3. Create the View class.

As you create these components, you'll notice that they share a great deal of 
code with the corresponding components in Get Guestbooks Screenlet's View. The 
biggest difference between these Screenlets' Views is that one displays 
guestbooks and the other displays entries. The mechanisms they use to display 
their data, however, are almost identical. 

To get started, create a new package named `view` inside the 
`getentriesscreenlet` package. You'll first create the View Model interface 
inside this new `view` package. 

## Creating the View Model Interface [](id=creating-the-view-model-interface)

First, you need to create the Screenlet's View Model interface. You'll implement 
this interface when creating the View class. The View Model interface defines 
the methods the View class needs to control the UI. Create the interface 
`GetEntriesViewModel` inside the `view` package. Replace its contents with the 
following code:

    package com.liferay.docs.getentriesscreenlet.view;

    import com.liferay.docs.getentriesscreenlet.EntryModel;
    import com.liferay.mobile.screens.base.view.BaseViewModel;

    import java.util.List;

    public interface GetEntriesViewModel extends BaseViewModel {

        void showFinishOperation(String userAction, List<EntryModel> entries);
    }

Compare this interface to `GetGuestbooksViewModel`. It's almost identical! You 
could've created it by pasting in `GetGuestbooksViewModel`, and then running a 
find and replace for `Guestbook`, `Guestbooks`, and `guestbooks`. Keep this in 
mind when developing your own Screenlets. Don't be afraid to reuse existing code 
and then make some modifications. 

Now you're ready to create the View's layout XML. 

## Creating the View's Layout [](id=creating-the-views-layout)

Every View in Screens for Android requires a layout XML. In your app's 
`res/layout` directory, create the layout file `entries_default.xml`. Replace 
its contents with the following code:

    <?xml version="1.0" encoding="utf-8"?>
    <com.liferay.docs.getentriesscreenlet.view.GetEntriesView
        xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

    </com.liferay.docs.getentriesscreenlet.view.GetEntriesView>

This layout serves the same purpose in Get Entries Screenlet as 
`guestbooks_default.xml` serves in Get Guestbooks Screenlet. Also, note that the 
`GetEntriesView` class doesn't exist yet. You'll create it next. 

## Creating the View Class [](id=creating-the-view-class)

Recall that a Screenlet's View class forms the core of the Screenlet's UI. The 
View class renders the UI, handles user interactions, and communicates with the 
Screenlet class. Create the `GetEntriesView` class inside the `view` package. 
Replace the class's contents with the following code: 

    package com.liferay.docs.getentriesscreenlet.view;

    import android.content.Context;
    import android.util.AttributeSet;
    import android.view.View;
    import android.widget.AdapterView;
    import android.widget.ArrayAdapter;
    import android.widget.ListView;

    import com.liferay.docs.getentriesscreenlet.EntryModel;
    import com.liferay.mobile.screens.util.LiferayLogger;
    import com.liferay.mobile.screens.viewsets.defaultviews.LiferayCrouton;

    import java.util.ArrayList;
    import java.util.List;

    public class GetEntriesView extends ListView implements GetEntriesViewModel, 
        AdapterView.OnItemClickListener {

        private List<EntryModel> _entries = new ArrayList<>();

        public GetEntriesView(Context context) {
            this(context, null);
        }

        public GetEntriesView(Context context, AttributeSet attributes) {
            this(context, attributes, 0);
        }

        public GetEntriesView(Context context, AttributeSet attributes, int defaultStyle) {
            super(context, attributes, defaultStyle);

            ArrayAdapter adapter = new ArrayAdapter<EntryModel>(
                getContext(), 
                android.R.layout.simple_list_item_2, 
                android.R.id.text1, _entries) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(_entries.get(position).getMessage());
                    text2.setText(_entries.get(position).getName());

                    text1.setTypeface(Typeface.DEFAULT_BOLD);
                    text2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);

                    return view;
                }
            };
            setAdapter(adapter);
            setOnItemClickListener(this);
        }

        @Override
        public void showStartOperation(String actionName) {
        }

        @Override
        public void showFinishOperation(String actionName) {
        }

        @Override
        public void showFinishOperation(final String userAction, 
            final List<EntryModel> entries) {
                LiferayLogger.i("Got the Entries!");

                _entries.clear();
                _entries.addAll(entries);

                ((ArrayAdapter) getAdapter()).notifyDataSetChanged();
        }

        @Override
        public void showFailedOperation(String actionName, Exception e) {
            LiferayLogger.e("Could not get Entries", e);
            LiferayCrouton.error(getContext(), "Could not get Entries", e);
        }

        @Override
        public void onItemClick(final AdapterView<?> parent, final View view, 
            final int position, final long id) {
                setItemChecked(position, true);

                //((GetEntriesScreenlet) getParent()).onItemClicked(_entries.get(position));
        }
    }

This view class works almost exactly like 
[Get Guestbook Screenlet's View class](/develop/learning-paths/mobile/-/knowledge_base/6-2/creating-the-get-guestbook-screenlets-ui#creating-the-view-class). 
Besides using `EntryModel` instead of `GuestbookModel`, the `ListView` adapter 
is the only difference. The adapter in `GetEntriesView` displays the entry and 
the name of the person who left the entry. It's the same adapter you used to 
display entries when you used the Guestbook Mobile SDK directly in 
`EntriesFragment`. 

Great! You've successfully created the Get Entries Screenlet's View class. Next, 
you'll create the server calls the Screenlet needs to retrieve the entries. 
