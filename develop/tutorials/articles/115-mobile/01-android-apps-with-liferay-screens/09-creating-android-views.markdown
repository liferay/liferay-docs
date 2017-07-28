# Creating Android Views [](id=creating-android-views)

By creating your own Views, you can customize your mobile app's layout, style,
and functionality. You can create them from scratch or use an existing View as a
foundation. Views include a View class for implementing Screenlet behavior, a
Screenlet class for notifying listeners and invoking Interactors, and an XML 
file for specifying the UI. The four Liferay Screens View types support
different levels of customization and parent View inheritance. Here's what each
View type offers: 

**Themed View:** presents the same structure as the current View, but alters the 
theme colors and tints of the View's resources. All existing Views can be themed 
with different styles. The View's colors reflect the current value of the 
Android 
[color palette](https://www.google.com/design/spec/style/color.html#color-color-palette). 
If you want to use one View Set with another View Set's colors, you can use 
those colors in your app's theme (e.g. `colorPrimary_default`, 
`colorPrimary_material`, `colorPrimary_westeros`). 

**Child View:** presents the same UI components as its parent View, but lets 
you change their appearance and position.

**Extended View:** inherits its parent View's functionality and appearance,
but lets you add to and modify both.

**Full View:** provides a complete standalone View for a Screenlet. A full
View is ideal for implementing completely different functionality and appearance
from a Screenlet's current theme.

This tutorial explains how to create all four types of Views. To understand
View concepts and components, you might want to examine the
[architecture](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-android)
of Liferay Screens for Android. And the tutorial
[Creating Android Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-android-screenlets)
can help you create or extend any Screenlet classes your View requires. Now get
ready to create some great Views! 

## Determining Your View's Location [](id=determining-your-views-location)

First, decide whether you'll reuse your view or if it's just for your current
app. If you don't plan to reuse it in another app or don't want to redistribute
it, create it in your app project. 

If you want to reuse your View in another app, create it in a new Android
application module; the tutorial
[Packaging Android Screenlets](/develop/tutorials/-/knowledge_base/7-0/packaging-your-android-screenlets)
explains how. When your View's project is in place, you can start creating it. 

First, you'll learn how to create a Themed View. 

## Themed View [](id=themed-view)

Screens provides several existing View Sets that you can reuse and customize in 
your app to create a Themed View. If you use or override the Android color 
palette's values (for example, `primaryColor`, `secondaryColor`, etc...), you'll 
reuse the View Set's general structure, but be able to use the new colors (also 
with tinted resources). Note that you must create Themed Views inside your app. 
This is because Themed Views depend on the app or activity theme. 

Each View Set has its own Android theme. These are listed here: 

- **Default View Set:** `default_theme`
- **Material View Set:** `material_theme`
- **Westeros View Set:** `westeros_theme`

You can easily style all your Screenlets by setting your app or activity theme 
to inherit a View Set's Android theme. For example, you can use the following 
code to reuse the styles (and layouts) from `material_theme` in your own theme: 

    <style name="AppTheme.NoActionBar" parent="material_theme">
        <item name="colorPrimary">#B91D6D</item>
        <item name="colorPrimaryDark">#670E3B</item>
        <item name="colorAccent">#BBBBBB</item>
    </style>

    <application android:theme="@style/AppTheme.NoActionBar"
        ...
    >

Note that this code overrides the `AppTheme.NoActionBar` theme's colors with 
your own color settings for `colorPrimary`, `colorPrimaryDark`, and 
`colorAccent`. Screenlets will also use these new colors, and tint images and 
other resources accordingly. Liferay Screens uses the default Android color 
palette names from the Support Library. 

You can also override only the parent View Set's theme colors. This way you can 
set a default color palette and override only the View Set colors you want. The 
color names for each View Set are the default Android names, followed by an 
underscore and the View Set's lowercase name (`_default`, `_material`, and 
`_westeros`). For example, the following code overrides `colorPrimary`, 
`colorPrimaryDark`, and `colorAccent` for only the `material_theme`: 

    <resources>
        <color name="colorPrimary_material">#B91D6D</color>
        <color name="colorPrimaryDark_material">#670E3B</color>
        <color name="colorAccent_material">#BBBBBB</color>
    </resources>

Liferay Screens also lets you use one View Set's layout with a Screenlet, and 
use another View Set's general style and colors. To do this, pass a `layoutId` 
attribute to a Screenlet that is already styled with another View Set's theme. 
The Screenlet uses the layout structure specified in `layoutId`, but inherits 
the general style and colors from the View Set's theme. For example, this code 
tells Login Screenlet to use the Default View Set's layout structure, but use 
the styles and colors defined earlier in `AppTheme.NoActionBar`:

    <com.liferay.mobile.screens.auth.login.LoginScreenlet
        android:id="@+id/login_screenlet"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:basicAuthMethod="email"
        app:layoutId="@layout/login_default"
        app:credentialsStorage="shared_preferences" />

    <application android:theme="@style/AppTheme.NoActionBar"
        ...
    >

Next, you'll learn how to create a Child View. 

## Child View [](id=child-view)

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

Stupendous! Now you know how to create Child Views. Next, you'll learn how to
create Extended Views. 

## Extended View [](id=extended-view)

An Extended View inherits the parent View's behavior and appearance, but lets
you change and add to both. You can do so by writing a custom View class and a
new layout XML file. An Extended View inherits all of the parent View's other
classes, including its Screenlet, listeners, and Interactors. An Extended View's
parent must be a Full View.

The example Extended View discussed here presents the same UI components as the
Login Screenlet's Default View, but adds functionality: computing password
strength. Of course, you're not restricted to password strength computations;
you can implement anything you want. 

1.  Create a new layout XML file named after the View's Screenlet and its
    intended use case. A good way to start building your UI is to duplicate the
    parent's layout XML file and use it as a template. The new layout file for
    the Login Screenlet's Extended View is called `login_password.xml`, because
    it's based on the Login Screenlet's Default View layout file
    [`login_default.xml`](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/res/layout/login_default.xml) and it adds a password strength computation.

2.  Create a new custom View class that extends the parent View class. Name it
    after the Screenlet and the functionality you'll add or override. The
    example View class `LoginCheckPasswordView` extends the Default View's
    [`LoginView`](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/java/com/liferay/mobile/screens/viewsets/defaultviews/auth/login/LoginView.java)
    class, overriding the `onClick` method to compute password strength:

        public class LoginCheckPasswordView extends LoginView {
	
            // parent's constructors go here...
        
            @Override
            public void onClick(View view) {
                // compute password strength
        
                if (passwordIsStrong) {
                    super.onClick(view);
                }
                else {
                    // Present user message
                }
            }
        
        }

3.  Rename the layout XML file's root element after your custom View's
    fully-qualified class name. For example, the root element in
    `login_password.xml` is `com.your.package.LoginCheckPasswordView`: 

        <com.your.package.LoginCheckPasswordView
            xmlns:android="http://schemas.android.com/apk/res/android"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">
            ...

4.  Insert your View's Screenlet in any of your activities or fragments, using
    your new layout's name as the `liferay:layoutId` attribute's value. For
    example, to use the new `login_password` layout, insert `LoginScreenlet` in
    an activity or fragment, and set
    `liferay:layoutId="@layout/login_password"`. 

The
[Bank of Westeros](https://github.com/liferay/liferay-screens/tree/master/android/samples/bankofwesteros) 
sample app's
[Westeros View Set](https://github.com/liferay/liferay-screens/tree/master/android/viewsets/westeros)
has a couple of Extended Views that you can examine. It has an Extended View that
adds a new button to show the password in the clear for the Login Screenlet. The
View uses custom layout file
[`login_westeros.xml`](https://github.com/liferay/liferay-screens/blob/master/android/viewsets/westeros/src/main/res/layout/login_westeros.xml) 
and custom View class
[`LoginView`](https://github.com/liferay/liferay-screens/blob/master/android/viewsets/westeros/src/main/java/com/liferay/mobile/screens/viewsets/westeros/auth/login/LoginView.java). 
The Westeros View Set also contains an Extended View for the User Portrait
Screenlet; it changes the border color and width of the user's portrait
picture and it uses the custom layout file
[`userportrait_westeros.xml`](https://github.com/liferay/liferay-screens/blob/master/android/viewsets/westeros/src/main/res/layout/userportrait_westeros.xml) 
and the custom View class
[`UserPortraitView`](https://github.com/liferay/liferay-screens/blob/master/android/viewsets/westeros/src/main/java/com/liferay/mobile/screens/viewsets/westeros/userportrait/UserPortraitView.java). 

Awesome! Now you know how to create Extended Views. Next, you can learn how to
create a Full View. 

## Full View [](id=full-view)

A Full View has a unique Screenlet class, a View class, and layout XML file.
It's standalone and doesn't inherit from any View. You should create a Full View
if there's no other View that you can extend to meet your needs or if your
Screenlet's behavior can only be augmented by customizing its listeners or
calling custom Interactors. To create a Full View, you must create its Screenlet
class, View class, and layout XML file. The example Full View here for the Login
Screenlet presents a single `EditText` component for the user name. For the
password, it uses
[`Secure.ANDROID_ID`](http://developer.android.com/reference/android/provider/Settings.Secure.html#ANDROID_ID).
The
[Screens Test App](https://github.com/liferay/liferay-screens/tree/master/android/samples/test-app)
uses
[this Full View](https://github.com/liferay/liferay-screens/tree/master/android/samples/test-app/src/main/java/com/liferay/mobile/screens/testapp/fullview). 

You can follow these steps to create a Full View:

1.  Create a new layout XML file and build your UI in it. A good way to start
    building your UI is to duplicate another View's layout XML file and use it
    as a template. Name your layout XML file after the View's Screenlet and
    intended use case. Name its root element after the fully-qualified class
    name of your custom View (you'll create this next).

	The Test App's Full View layout XML file for the Login Screenlet is called
    [`login_full.xml`](https://github.com/liferay/liferay-screens/blob/master/android/samples/test-app/src/main/res/layout/login_full.xml).
    It specifies `EditText` and `Button` elements copied from the
    LongScreenlet's Default View file
    [`login_default.xml`](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/res/layout/login_default.xml).

        <?xml version="1.0" encoding="utf-8"?>
        <com.your.package.LoginFullView
                xmlns:android="http://schemas.android.com/apk/res/android"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="vertical">
        
        <EditText
                android:id="@+id/liferay_login"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginBottom="20dp"
                android:hint="Email Address"
                android:inputType="textEmailAddress"/>
        
        <Button
                android:id="@+id/liferay_login_button"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="Sign In"/>
        
        </com.your.package.LoginFullView>

2.  Create a new custom View class named after the layout's root element. The
    tutorial on creating Android Screenlets explains how to
    [create a View class](/develop/tutorials/-/knowledge_base/7-0/creating-android-screenlets#creating-the-screenlets-view-class).
    Note that you don't have to extend a View class to implement a View Model
    interface, but you might want to for convenience. The custom View class
    [`LoginFullView`](https://github.com/liferay/liferay-screens/blob/master/android/samples/test-app/src/main/java/com/liferay/mobile/screens/testapp/fullview/LoginFullView.java),
    for example, implements the
    [`LoginViewModel`](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/java/com/liferay/mobile/screens/auth/login/view/LoginViewModel.java)
    interface by extending the Default
    [`LoginView`](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/java/com/liferay/mobile/screens/viewsets/defaultviews/auth/login/LoginView.java)
    class. To return the `ANDROID_ID`, the `LoginFullView` custom View class
    overrides the `getPassword()` method.

3. Create a new Screenlet class that inherits the base Screenlet class. This new 
    class is where you can add custom behavior to the listeners or call custom
    Interactors. The Screenlet class
    [`LoginFullScreenlet`](https://github.com/liferay/liferay-screens/blob/master/android/samples/test-app/src/main/java/com/liferay/mobile/screens/testapp/fullview/LoginFullScreenlet.java),
    for example, extends
    [`LoginScreenlet`](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/java/com/liferay/mobile/screens/auth/login/LoginScreenlet.java)
    and overrides the `onUserAction` method to log Interactor calls. 

4.  Insert your View's Screenlet in any of your activities or fragments, using
    your new layout's name as the `liferay:layoutId` attribute's value. For
    example, to use the new `login_password` layout, insert `LoginScreenlet` in
    an activity or fragment, and set
    `liferay:layoutId="@layout/login_password"`. 

The
[Westeros View Set's full view for the Sign Up Screenlet](https://github.com/liferay/liferay-screens/tree/master/android/viewsets/westeros/src/main/java/com/liferay/mobile/screens/viewsets/westeros/auth/signup)
uses a
[custom Screenlet class](https://github.com/liferay/liferay-screens/blob/master/android/viewsets/westeros/src/main/java/com/liferay/mobile/screens/viewsets/westeros/auth/signup/SignUpScreenlet.java) 
to add a
[new listener](https://github.com/liferay/liferay-screens/blob/master/android/viewsets/westeros/src/main/java/com/liferay/mobile/screens/viewsets/westeros/auth/signup/SignUpListener.java).
The custom Screenlet class also adds a new user action that calls the base
Interactor `SignUpInteractor`. 

Sweetness! Now you know how to create a Full View. Next, you'll learn how to 
package Views for distribution. 

## Packaging Your Views [](id=packaging-your-views)

If you want to distribute or reuse Views, you should package them in a module
that is then added as an app's project dependency. To do this, use the 
[material](https://github.com/liferay/liferay-screens/tree/master/android/viewsets/material) 
sub-project as a template for your new
[`build.gradle`](https://github.com/liferay/liferay-screens/blob/master/android/viewsets/material/build.gradle) 
file. 

To use a packaged View, you must import its module into your project by 
specifying its location in your
[`settings.gradle`](https://github.com/liferay/liferay-screens/blob/master/android/samples/bankofwesteros/settings.gradle)
file. The Bank of Westeros and test-app projects use custom Views `westeros` and
`material`, respectively. These projects exemplify using independent Views in a
project. 

If you want to redistribute your View and let others use it, you can upload it 
to jCenter or Maven Central. In the example
[`build.gradle`](https://github.com/liferay/liferay-screens/blob/master/android/samples/bankofwesteros/build.gradle) 
file, after entering your bintray api key you can execute `gradlew
bintrayupload` to upload your project to jCenter. When finished, anyone can use
the View as they would any Android dependency by adding the repository,
artifact, group ID, and version to their Gradle file. 

Super! Now you know how to create and package Views in Liferay Screens for
Android. This gives you extensive control over your app's visual design and
behavior and also lets you distribute and reuse your Views. 

## Related Topics [](id=related-topics)

[Using Views in Android Screenlets](/develop/tutorials/-/knowledge_base/7-0/using-views-in-android-screenlets)

[Architecture of Liferay Screens for Android](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-android)

[Creating Android Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-android-screenlets)
