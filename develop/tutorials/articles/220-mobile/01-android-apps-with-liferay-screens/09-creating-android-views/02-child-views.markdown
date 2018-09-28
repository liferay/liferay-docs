# Child Views [](id=child-views)

A Child View presents the same behavior and UI components as its parent, but can
change the UI components' appearance and position. It can't add or remove any UI
components. A Child View specifies visual changes in its own layout XML file; it
inherits the parent's View class and Screenlet class. The parent must be a Full
View. 

The Child View discussed here presents the same UI components as the
[Login Screenlet's](https://github.com/liferay/liferay-screens/tree/master/android/library/core/src/main/java/com/liferay/mobile/screens/auth/login)
Default View, but uses a more compact layout.

You can follow these steps to create a Child View:

1.  Create a new layout XML file named after the View's Screenlet and its
    intended use case. A good way to start building your UI is to duplicate the
    parent's layout XML file and use it as a template. However you start
    building your UI, name the root element after the parent View's
    fully-qualified class name and specify the parent's UI components with the
    same IDs. 

    In the example here, the Child View's layout file `login_compact.xml`
    resembles its parent's layout file
    [`login_default.xml`](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/res/layout/login_default.xml)--
    the layout of the Login Screenlet's Default View. The child View's name
    *compact* describes its use case: display the Screenlet's components in a
    more compact layout. The IDs of its `EditText` and `Button` components match
    those of the parent View. Its root element uses the parent View class's
    fully-qualified name: 

        <?xml version="1.0" encoding="utf-8"?>
        <com.liferay.mobile.screens.viewsets.defaultviews.auth.login.LoginView
            xmlns:android="http://schemas.android.com/apk/res/android"
            style="@style/default_screenlet">

            <EditText
                android:id="@+id/liferay_login"
                style="@style/default_edit_text"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginBottom="15dp"
                android:drawableLeft="@drawable/default_mail_icon"
                android:hint="@string/email_address"
                android:inputType="text" />

            <EditText
                android:id="@+id/liferay_password"
                style="@style/default_edit_text"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginBottom="15dp"
                android:drawableLeft="@drawable/default_lock_icon"
                android:hint="@string/password"
                android:inputType="textPassword" />

            <Button
                android:id="@+id/liferay_login_button"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                style="@style/default_button"
                android:text="@string/sign_in" />

        </com.liferay.mobile.screens.viewsets.defaultviews.auth.login.LoginView>

    You can browse other layouts for Screens's Default Views on
    [GitHub](https://github.com/liferay/liferay-screens/tree/master/android/library/core/src/main/res/layout). 

2.  Insert your View's Screenlet in any of your activities or fragments, using
    your new layout's name as the `liferay:layoutId` attribute's value. For
    example, to use the new `login_compact` layout, insert `LoginScreenlet` in
    an activity or fragment and set `liferay:layoutId="@layout/login_compact"`.

Another good Child View layout file to examine is
[`sign_up_material.xml`](https://github.com/liferay/liferay-screens/blob/master/android/viewsets/material/src/main/res/layout/sign_up_material.xml).
It presents the same UI components and functionality as the Sign Up Screenlet's
Default View, but using
[Android's Material design](http://www.google.com/design/spec/material-design/introduction.html). 

## Related Topics [](id=related-topics)

[Themed Views](/develop/tutorials/-/knowledge_base/7-1/themed-views)

[Extended Views](/develop/tutorials/-/knowledge_base/7-1/extended-views)

[Full Views](/develop/tutorials/-/knowledge_base/7-1/full-views)

[Packaging Your Views](/develop/tutorials/-/knowledge_base/7-1/packaging-your-views)
