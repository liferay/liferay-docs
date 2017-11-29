# Using Views in Xamarin.Android

+$$$

**Note:** This documentation is currently in beta. 

$$$

You can use a Liferay Screens *View* to set a Screenlet's look and feel 
independent of the Screenlet's core functionality. Liferay's Screenlets come 
with several Views, and more are being developed by Liferay and the community. 
[The Screenlet reference documentation for Android](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-android) and
[the Screenlet reference documentation for iOS](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-iOS) 
lists the Views/Themes available for each Screenlet included with Screens. This 
tutorial shows you how to use Views in Xamarin.Android.

## Views and View Sets [](id=views-and-view-sets)

Before using Views, you should know what components make them up. If you want to learn about Android views, please read [this]() documentation. And if you want to learn about iOS themes, please read [this]() one. For a detailed description of the View layer in Liferay Screens, see the 
tutorial 
[Architecture of Liferay Screens for Android](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-android).

## Using Views [](id=using-views)

To use a View in a Screenlet, specify the View's layout as the 
`liferay:layoutId` attribute's value when inserting the Screenlet AXML in an 
activity or fragment layout. For example, to use Login Screenlet with its 
Material View.

1. Copy the layout you want from the [Liferay Screens repository](https://github.com/liferay/liferay-screens) or create a new one. For example, this is `login_material.xml`:

        <com.liferay.mobile.screens.viewsets.material.auth.login.LoginView
            xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:liferay="http://schemas.android.com/apk/res-auto"
            android:paddingLeft="40dp"
            android:paddingRight="40dp"
            style="@style/default_screenlet">
    
        <LinearLayout
            android:id="@+id/basic_authentication_login"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">
    
            <LinearLayout style="@style/material_row">
    
                <ImageView
                    android:id="@+id/drawable_login"
                    android:contentDescription="@string/user_login_icon"
                    android:src="@drawable/material_email"
                    style="@style/material_icon"/>
    
                <EditText
                    android:id="@+id/liferay_login"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:layout_marginTop="8dp"
                    android:inputType="text"
                    android:labelFor="@+id/liferay_login"/>
    
            </LinearLayout>
    
            <LinearLayout style="@style/material_row">
    
                <ImageView
                    android:id="@+id/drawable_password"
                    android:contentDescription="@string/password_icon"
                    android:src="@drawable/material_https"
                    style="@style/material_icon"/>
    
                <EditText
                    android:id="@+id/liferay_password"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:layout_marginTop="8dp"
                    android:hint="@string/password"
                    android:inputType="textPassword"/>
    
            </LinearLayout>
    
            <FrameLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginTop="32dp">
    
                <Button
                    android:id="@+id/liferay_login_button"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:layout_margin="10dp"
                    android:text="@string/sign_in"/>
    
                <com.liferay.mobile.screens.base.ModalProgressBar
                    android:id="@+id/liferay_progress"
                    android:layout_width="wrap_content"
                    android:layout_height="match_parent"
                    android:layout_gravity="center_vertical|left"
                    android:layout_margin="10dp"
                    android:theme="@style/white_theme"
                    android:visibility="invisible"
                    liferay:actionViewId="@id/liferay_login_button"/>
            </FrameLayout>
        </LinearLayout>
    
        <Button
            android:id="@+id/oauth_authentication_login"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@string/authorize_application"
            android:visibility="gone"/>
            
        </com.liferay.mobile.screens.viewsets.material.auth.login.LoginView>

Note: You can install a NuGet with the theme in your App and then use it.

2. Insert the Screenlet's AXML with `liferay:layoutId` set to 
`@layout/login_material`: 

        <com.liferay.mobile.screens.auth.login.LoginScreenlet
            android:id="@+id/login_screenlet"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            liferay:layoutId="@layout/login_material"
            />

The following links list the View layouts available in each View Set: 

- [Default](https://github.com/liferay/liferay-screens/tree/master/android/library/src/main/res/layout)
- [Material](https://github.com/liferay/liferay-screens/tree/master/android/viewsets/material/src/main/res/layout)
- [Westeros](https://github.com/liferay/liferay-screens/tree/master/android/viewsets/westeros/src/main/res/layout)

If the View you want to use is part of a View Set, your app or activity's theme 
must also inherit the theme that defines that View Set's styles. For example, 
the following code in an app's `Resources/values/Styles.xml` tells 
`AppTheme.NoActionBar` to use the Material View Set as its parent theme: 

    <resources>
        <style name="AppTheme.NoActionBar" parent="material_theme">
            <item name="colorPrimary">@color/colorPrimary</item>
            <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
            <item name="colorAccent">@color/colorAccent</item>

            <item name="windowActionBar">false</item>
            <item name="windowNoTitle">true</item>
        </style>
        ...
    </resources>

To use the Default or Westeros View Set, inherit `default_theme` or 
`westeros_theme`, respectively. 

That's it! Great! Now you know how to use Views to spruce up your Android 
Screenlets. This opens up a world of possibilities, like 
[writing your own Themes](/develop/tutorials/-/knowledge_base/7-0/creating-xamarin-views-for-the-screenlets).

## Related Topics

