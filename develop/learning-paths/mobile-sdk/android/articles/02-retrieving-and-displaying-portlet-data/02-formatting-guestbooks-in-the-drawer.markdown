# Formatting Guestbooks in the Drawer

In the previous article of this learning path, you retrieved guestbooks from the 
Guestbook portlet and displayed them in your app's navigation drawer. The only 
problem is that the guestbooks aren't recognizable. They're showing up as string 
representations of `GuestbookModel` objects.

![Figure 1: The guestbooks are retrieved from the portlet, but their appearance isn't exactly user friendly.](../../images/android-guestbook-drawer-01.png)

To show the guestbook names instead, you need to write your own adapter class. 
The adapter class first requires a layout.

## Creating a Layout for the Adapter

The first thing you need to do is create a layout for the adapter to use when 
displaying each guestbook in the drawer. The `ArrayAdapter` is currently using 
one of Android's built-in layouts for list items, 
`simple_list_item_activated_1`. Since you're writing your own adapter, you also 
need to write your own layout. In Android Studio's project view, right click the 
`res/layout` folder and select *New* &rarr; *Layout resource file*. In the 
dialog that appears, enter *item_guestbook.xml* into the *File name* field. 
Make sure that *LinearLayout* is specified in the *Root element* field. Accept 
the defaults for the remaining fields and click *OK*.

![Figure 2: Create the new layout file to use with each guestbook in the list.](../../images/android-guestbook-layout.png)

The new layout file then opens in design view. Click the *Text* tab to switch to 
text view. Replace the file's code with the following:

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
Android's built-in value for a list item's preferred height. It ensures the item 
is tall enough for a user to tap without also tapping adjacent list items. The 
`TextView` definition controls the guestbook name's formatting. Note the 
settings for the `android:textSize` and `android:textStyle` attributes. The 
`bold` setting for `android:textStyle` specifies bold typeface. The 
`android:textSize` setting of `20sp` specifies a text size of 20 
*scale-independent pixels* (`sp`). Scale-independent pixels are effectively 
*density-independent pixels* (`dp`) that scale to the user's text size setting 
on the device. Density independent pixels represent absolute pixels 
independently of the device's pixel density. Since Android devices vary greatly 
in pixel density, using `dp` and `sp` ensures that your UI elements render in a 
consistent physical size across devices. If you were to instead specify an 
absolute pixel number, the text size would vary with the device's pixel density. 
For more information, see [Android's documentation on pixel density](http://developer.android.com/training/multiscreen/screendensities.html). 
Also in the `TextView`, the default text `"Bring home the bacon"` is specified. 
This might seem a bit unorthodox, but don't worry; users won't see it. It's just 
to let you preview the text settings in Android Studio's design view. 

![Figure 3: Your text settings in the layout are previewed in Android Studio's design view.](../../images/android-guestbook-text-formatting.png)

Awesome! Now that you have a layout for the guestbooks, it's time to write the 
adapter class. 

## Writing the Adapter Class

To begin, you need to create a package for the adapter class. In Android 
Studio's project view, right click the `com.liferay.docs.liferayguestbook` 
package and select *New* &rarr; *Package*. Name the package *adapter* and click 
*OK*. Next, right click the new `adapter` package and select 
*New* &rarr; *Java Class*. Name the class *GuestbooksAdapter* and click *OK*. 
Replace the code in the class with the following: 

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
constructor is then leveraged to allow `GuestbooksAdapter` objects to be created 
with a `Context` and a list of `GuestbookModel` objects. Next comes the `getView` 
method. This method uses the layout you created in the previous step to render 
the text for each `GuestbookModel` in the drawer. To inflate this layout, you 
first need to obtain a [`LayoutInflater`](http://developer.android.com/reference/android/view/LayoutInflater.html). 
The rest of this class uses the *View Holder* design pattern to render the 
`GuestbookModel`. In short, this is done to improve performance by preventing 
repeated calls to `findViewById` while a user scrolls through the list in the 
drawer. Android's documentation [explains this further](http://developer.android.com/training/improving-layouts/smooth-scrolling.html#ViewHolder). 
Also, you should note that `getView` retrieves the guestbook at the specified 
position in the drawer. The `getGuestbookName` method is then used to get the 
guestbook's name. 

Super! Now you have a custom adapter that you can use to correctly display each 
guestbook's name in the drawer. The next section shows you how to use your 
adapter. 

## Using the Adapter Class

Using your new adapter is easy. In the `onCreateView` method of 
`NavigationDrawerFragment`, replace the `_adapter` assignment with the following 
code:

    _adapter = new GuestbooksAdapter(getActionBar().getThemedContext(), MainActivity._guestbooks);

Remember to import the `GuestbooksAdapter` class when prompted by Android 
Studio. That's it. Run your app and open the navigation drawer. The guestbooks 
from your portlet are now showing correctly! 

![Figure 4: Your guestbooks now appear by name in the drawer.](../../images/android-guestbooks-rendered.png)

However, the drawer doesn't look so hot. The guestbooks are perilously close to 
the left edge of the screen. Why doesn't the drawer have the same styling it did 
earlier? Recall that earlier you were using a simple `ArrayAdapter` with 
Android's built-in layout `simple_list_item_activated_1`. You're not using this 
layout anymore. Don't worry! Simply replace the contents of 
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
between each item and its surroundings. The `android:background` attribute is 
also used to set the drawer's background color similar to its previous color. 

![Figure 5: The drawer looks much better now.](../../images/android-guestbooks-rendered-nice.png)

Stupendous! Now the guestbook names display correctly in the drawer. They don't 
look half bad either. Now you're ready to retrieve and display the guestbook 
entries. 
