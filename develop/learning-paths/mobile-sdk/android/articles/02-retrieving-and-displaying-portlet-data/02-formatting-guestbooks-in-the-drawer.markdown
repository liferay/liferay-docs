# Formatting Guestbooks in the Drawer

In the previous article of this learning path, you retrieved guestbooks from the 
Guestbook portlet and displayed them in your app's navigation drawer. The only 
problem is that the guestbooks aren't recognizable. They're showing up as string 
representations of `GuestbookModel` objects.

![Figure 1: The guestbooks are being retrieved correctly from the portal, but their appearance isn't exactly user friendly.](../../images/android-guestbook-drawer-01.png)

To show the guestbook names instead, you need to write your own adapter class. 
This article shows you how to do this.

## Creating a Layout for the Adapter

The first thing you need to do is create a layout for the adapter to use when 
displaying each guestbook in the drawer. Currently, the `ArrayAdapter` is using 
one of Android's built-in layouts for list items, 
`simple_list_item_activated_1`. Since you're writing your own adapter, you also 
need to write your own layout. In your app's project view in Android Studio, 
right click the `res/layout` and select *New* &rarr; *Layout resource file*. In 
the dialog that appears, enter *item_guestbook.xml* into the *File name* field. 
Make sure that *LinearLayout* is specified in the *Root element* field. Accept 
the defaults for the remaining fields and click *OK*.

![Figure 2: Create the new layout file to use with each guestbook in the list.](../../images/android-guestbook-layout.png)

The new layout file then opens in Design view. Click the *Text* tab to switch to 
Text view. Replace the file's code with the following:

    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
      android:orientation="vertical"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:minHeight="?android:attr/listPreferredItemHeight"
      android:gravity="center_vertical" >
    
      <TextView
        android:id="@+id/guestbook_name"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textSize="20sp"
        android:textStyle="bold"
        android:text="Bring home the bacon" />
    
    </LinearLayout>

It's important to note that this layout controls only the display of each item 
in the drawer's `ListView`. It doesn't control the display for the `ListView` as 
a whole. In the `LinearLayout` attributes, the main thing to note is 
`android:minHeight="?android:attr/listPreferredItemHeight"`. This leverages 
Android's built-in value for the preferred height of a list item, to ensure the 
item is tall enough for a user to tap without also tapping adjacent list items. 
The `TextView` definition controls the guestbook name's formatting. The main 
things to take note of here are the settings for the `android:textSize` and 
`android:textStyle` attributes. The `bold` setting for text style specifies bold 
typeface. The text size setting of `20sp` specifies a text size of 20 
scale-independent pixels. Scale-independent pixels are effectively 
density-independent pixels (`dp`) that scale to the user's text size setting on 
the device. Since Android devices greatly differ in pixel density, using `dp` 
and `sp` ensures that your UI elements render in a consistent physical size 
across devices. If you were to instead specify a fixed pixel number, the text 
size would vary depending on the device's pixel density. For more information, 
see [Android's documentation on pixel density](http://developer.android.com/training/multiscreen/screendensities.html). 
Lastly, the default text of `"Bring home the bacon"` is specified. This might 
seem a bit unorthodox, but don't worry; users won't see it. It's just to let 
you preview the text settings in Android Studio's Design view. 

![Figure 3: Your text settings in the layout are previewed in Android Studio's Design view.](../../images/android-guestbook-text-formatting.png)

Awesome! Now that you have a layout for the guestbooks, it's time to write the 
adapter class. 

## Writing the Adapter Class

To begin, you'll create a package for the adapter class. In Android Studio's 
project view, right click the `com.liferay.docs.liferayguestbook` package and 
select *New* &rarr; *Package*. Name the package *adapter* and click *OK*. Next, 
right click the new `adapter` package and select *New* &rarr; *Java Class*. Name 
the class *GuestbooksAdapter* and click *OK*. Replace the code in the class with 
the following:

    package com.liferay.docs.liferayguestbook.adapter;
    
    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ArrayAdapter;
    import android.widget.TextView;

    import com.liferay.docs.liferayguestbook.R;
    import com.liferay.docs.liferayguestbook.model.GuestbookModel;

    import java.util.List;

    public class GuestbooksAdapter extends ArrayAdapter<GuestbookModel> {

      public GuestbooksAdapter(Context context, List<GuestbookModel> guestbooks) {
        super(context, 0, guestbooks);
      }
    
      @Override
      public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
    
        LayoutInflater inflater = LayoutInflater.from(getContext());
    
        if (view == null) {
            view = inflater.inflate(R.layout.item_guestbook, parent, false);
    
            holder = new ViewHolder();
    
            holder.guestbookName = (TextView)view.findViewById(R.id.guestbook_name);
    
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder)view.getTag();
        }
    
        GuestbookModel guestbook = getItem(position);
    
        holder.guestbookName.setText(guestbook.getGuestbookName());
    
        return view;
      }
    
      class ViewHolder {
    
        TextView guestbookName;
      }
    }

This class begins by extending the `ArrayAdapter` class. The superclass 
constructor is then leveraged to allow `GuestbookAdapter` objects to be created 
with a `Context` and a list of `GuestbookModel` objects. Next comes the `getView` 
method. This method renders the text for each `GuestbookModel` in the drawer's 
`ListView`, using the layout you created in the previous step. To inflate this 
layout, you first need to obtain a [`LayoutInflater`](http://developer.android.com/reference/android/view/LayoutInflater.html). 
The rest of this class renders the `GuestbookModel` by using the *View Holder* 
design pattern. In short, this is done to improve performance by preventing 
repeated calls to `findViewById` while a user scrolls through the list in the 
drawer. Android's documentation [explains this further](http://developer.android.com/training/improving-layouts/smooth-scrolling.html#ViewHolder). 
Also, you should note that `getView` retrieves the guestbook at the specified 
position in the list. The `getGuestbookName` method is then used to get the 
guestbook's name. This is the text necessary to display the guestbook's name in 
the drawer.

Super! Now you have a custom adapter that you can use to correctly display each 
guestbook in the drawer. The only thing left to do is put it to use.

## Using the Adapter Class

Using your new `GuestbookAdapter` class is easy. In the `onCreateView` method of 
`NavigationDrawerFragment`, replace the `_adapter` assignment with the following 
code:

    _adapter = new GuestbooksAdapter(getActionBar().getThemedContext(), MainActivity._guestbooks);
    
Remember to import the `GuestbookAdapter` class when prompted by Android Studio. 
That's it: Yes, seriously. Run your app and open the navigation drawer. The 
guestbooks from your portlet are now showing correctly! 

![Figure 4: Your guestbooks now appear by name in the drawer.](../../images/android-guestbooks-rendered.png)

However, the drawer doesn't look that great. The guestbooks are perilously close 
to the left edge of the screen. The white text on a light gray background 
doesn't look so hot either. Why doesn't the drawer have the same styling it did 
earlier? Recall that earlier you were using a simple `ArrayAdapter` with 
Android's built-in layout `simple_list_item_activated_1`. You're not using this 
anymore. Not to worry: simply replace the contents of 
`fragment_navigation_drawer.xml` with the following code:

    <ListView xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:tools="http://schemas.android.com/tools"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:paddingLeft="8dp"
      android:paddingRight="8dp"
      android:paddingTop="8dp"
      android:paddingBottom="8dp"
      android:background="@android:color/darker_gray"
      tools:context=".NavigationDrawerFragment" />

Run your app again and open the navigation drawer. The guestbooks look much 
better now! Using the `android:padding*` attributes helps by putting some space 
between each item and its surroundings. Likewise, the `android:background` 
attribute is used to darken the drawer's background color. 

![Figure 5: Your guestbooks look much better now.](../../images/android-guestbooks-rendered-nice.png)

Stupendous! Now the guestbooks are correctly showing in the drawer, and they 
don't look half bad either. Now you're ready to get their entries for display in 
the app. 
