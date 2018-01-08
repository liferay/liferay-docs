# Using Views in Xamarin.Android [](id=using-views-in-xamarin-android)

You can use a Liferay Screens *View* to set a Screenlet's look and feel 
independent of the Screenlet's core functionality. Liferay's Screenlets come 
with several Views, and more are being developed by Liferay and the community. 
[The Screenlet reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-android) 
lists the Views available for each Screenlet included with Screens. This 
tutorial shows you how to use Views in Xamarin.Android. 

## Views and View Sets [](id=views-and-view-sets)

The concepts and components that comprise Views and View Sets in Liferay Screens 
for Xamarin are the same as they are in Liferay Screens for Android. For a brief 
description of these components, see 
[the section on Views and View Sets](/develop/tutorials/-/knowledge_base/7-0/using-views-in-android-screenlets#views-and-view-sets) 
in the general tutorial on using Views. For a detailed description of the View 
layer in Liferay Screens, see the tutorial 
[Architecture of Liferay Screens for Android](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-android). 

## Using Views [](id=using-views)

Follow these steps to use a View in Xamarin.Android: 

1.  Copy the layout of the View you want to use from the 
    [Liferay Screens repository](https://github.com/liferay/liferay-screens) to 
    your app's `res/layout` folder. Alternatively, you can create a new layout. 
    The following links list the View layouts available in each View Set: 

    - [Default](https://github.com/liferay/liferay-screens/tree/master/android/library/src/main/res/layout)
    - [Material](https://github.com/liferay/liferay-screens/tree/master/android/viewsets/material/src/main/res/layout)
    - [Westeros](https://github.com/liferay/liferay-screens/tree/master/android/viewsets/westeros/src/main/res/layout)

    For example, this is Login Screenlet's Material View, `login_material.xml`: 

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

2.  When you insert the Screenlet's XML in the layout of the activity or 
    fragment you want the Screenlet to appear in, set the `liferay:layoutId` 
    attribute to the View's layout. For example, here's Login Screenlet's XML 
    with `liferay:layoutId` set to `@layout/login_material`, which specifies 
    Login Screenlet's Material View from the previous step: 

        <com.liferay.mobile.screens.auth.login.LoginScreenlet
            android:id="@+id/login_screenlet"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            liferay:layoutId="@layout/login_material"
            />

3.  If the View you want to use is part of a View Set (e.g., the Material View 
    is part of the Material View Set), your app or activity's theme must also 
    inherit the theme that defines that View Set's styles. For example, the 
    following code in an app's `Resources/values/Styles.xml` tells 
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

Awesome! Now you know how to use Views to spruce up your Xamarin.Android 
Screenlets. 

## Related Topics [](id=related-topics)

[Preparing Xamarin Projects for Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/preparing-xamarin-projects-for-liferay-screens)

[Using Screenlets in Xamarin Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-xamarin-apps)

[Using Themes in Xamarin.iOS](/develop/tutorials/-/knowledge_base/7-0/using-themes-in-xamarin-ios)

[Creating Xamarin Views and Themes](/develop/tutorials/-/knowledge_base/7-0/creating-xamarin-views-and-themes)

[Liferay Screens for Xamarin Troubleshooting and FAQs](/develop/tutorials/-/knowledge_base/7-0/liferay-screens-for-xamarin-troubleshooting-and-faqs)
