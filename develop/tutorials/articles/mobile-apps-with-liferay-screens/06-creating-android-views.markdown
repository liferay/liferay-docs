# Creating Android Views [](id=creating-android-views)

By letting you create your own views, Screens for Android gives you nearly 
limitless ways to customize your app's look and feel. Views can also introduce 
new functionality to screenlets. You can create views from scratch, or use an 
existing view as a foundation for your views. All views consist of a screenlet 
class, a view class, and a layout XML. Which of these components you need to 
create, however, depends on the *type* of view you're creating. The type of view 
you create depends on your needs. There are three types of views you can create: 

- *Child view*: Presents the same UI components as its parent view, but lets you 
  change their appearance and position. Since the parent view's behavior is 
  preserved, creating a Child view is ideal when you only need to make visual 
  changes to the parent. Child views reuse the parent's screenlet class and view 
  class, but change the layout XML. The Child view's parent must be a Full view.

- *Extended view*: Inherits the parent view's behavior and appearance, but lets 
  you change and add to both. Extended views reuse the parent view's screenlet
  class, but let you change its view class and layout XML. An Extended view's
  parent must be a Full view.

- *Full view*: Provides a complete standalone view. Creating a Full view is
  ideal when you need a screenlet to have completely different behavior and
  appearance. Since a Full view doesn't have a parent view, it has its own
  screenlet class, view class, and layout XML. Any Full view can be inherited by
  a Child or Extended view.

This tutorial shows you how to create all three types of views. Before 
proceeding, you might want to examine the
[architecture of Liferay Screens for Android](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android) 
to understand view concepts and components. It might also find it useful to
learn how to
[create Android screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-android-screenlets). 
Now get ready to create some great views! 

## Determining Your View's Location

Where you should create your view depends on how you plan to use it. If you
don't plan to reuse it in another app or don't want to redistribute it, create
it in your app project. 

If you want to reuse your view in another app, create it in a new Android
application module; the tutorial
[Packaging Android Screenlets](/develop/tutorials/-/knowledge_base/6-2/packaging-android-screenlets)
explains how. When your view's project is in place, you can start creating it. 

First, you'll learn how to create a Child view.

## Child View [](id=child-view)

A Child view presents the same behavior and UI components as its parent, but can
change the UI components' appearance and position. A Child view specifies visual
changes in its own layout XML file; it inherits the parent's view class and
screenlet class. The parent must be a Full view. For example, the Child view
discussed here presents the same UI components as the Login screenlet's Default
view, but displays them in a more compact layout. You can follow these steps to
create a Child view. 

1.  Create a new layout XML file named after the view's screenlet and its
    intended use case. A good way to start building your UI is to duplicate the
    parent's layout XML file and use it as a template. However you start
    building your UI, name the root element after the parent view's
    fully-qualified class name and specify the parent's UI components with the
    same IDs. 

    In the example here, the Child view's layout file `login_compact.xml`
    resembles its parent's layout file
    [`login_default.xml`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/library/core/src/main/res/layout/login_default.xml)--
    the layout of the Login screenlet's Default view. The child view's name
    *compact* describes its use case: display the screenlet's components in a
    more compact layout. The IDs of its `EditText` and `Button` components match
    those of the parent view. Its root element uses the parent view class's
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

    You can browse other layouts for Screens's Default views on
    [GitHub](https://github.com/liferay/liferay-screens/tree/1.0.0/android/library/core/src/main/res/layout). 

2.  Insert your view's screenlet in any of your activities or fragments, using
    your new layout's name as the `liferay:layoutId` attribute's value. For
    example, to use the new `login_compact` layout, insert `LoginScreenlet` in
    an activity or fragment, and set `liferay:layoutId="@layout/login_compact"`.

Another good Child view layout file to examine is
[`sign_up_material.xml`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/viewsets/material/src/main/res/layout/sign_up_material.xml).
It presents the same UI components and functionality as `SignUpScreenlet`'s
Default view, but using
[Android's Material design](http://www.google.com/design/spec/material-design/introduction.html). 

Stupendous! Now you know how to create Child views. Next, you'll learn how to
create Extended views. 

## Extended View [](id=extended-view)

An Extended view inherits the parent view's behavior and appearance, but lets
you change and add to both. You can do so by writing a custom view class and a
new layout XML file. An Extended view inherits all of the parent view's other
classes, including its screenlet, listeners, and interactors; create a Full view
to customize them for a screenlet. An Extended view's parent must be a Full
view. 

The example Extended view discussed here presents the same UI components as the
Default view for the Login screenlet, but adds new functionality: computing
password strength. Of course, you're not restricted to password strength
computations; you can implement the functionality you want. 

1.  Create a new layout XML file named after the view's screenlet and its
    intended use case. A good way to start building your UI is to duplicate the
    parent's layout XML file and use it as a template. The new layout file for
    the Login screenlet's Extended view is called `login_password.xml`, because
    it adds a password strength computation. It's also based on the Login
    screenlet's Default view layout file
    [`login_default.xml`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/library/core/src/main/res/layout/login_default.xml). 

2.  Create a new custom view class that extends the parent view class. Name
    it after the screenlet and the functionality you'll add or override. The
    example view class `LoginCheckPasswordView` extends the Default view's
    [`LoginView`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/library/core/src/main/java/com/liferay/mobile/screens/viewsets/defaultviews/auth/login/LoginView.java)
    class. It overrides the `onClick` method to compute password strength:

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

3.  Rename the layout XML file's root element after your custom view's
    fully-qualified class name. For example, the root element in
    `login_password.xml` is `com.your.package.LoginCheckPasswordView`: 

        <com.your.package.LoginCheckPasswordView
            xmlns:android="http://schemas.android.com/apk/res/android"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">
            ...

4.  Insert your view's screenlet in any of your activities or fragments, using
    your new layout's name as the `liferay:layoutId` attribute's value. For
    example, to use the new `login_password` layout, insert `LoginScreenlet` in
    an activity or fragment, and set
    `liferay:layoutId="@layout/login_password"`. 

The
[Bank of Westeros](https://github.com/liferay/liferay-screens/tree/1.0.0/android/samples/bankofwesteros) 
sample app's
[Westeros view set](https://github.com/liferay/liferay-screens/tree/1.0.0/android/viewsets/westeros)
has a couple Extended views that you can examine. It has an Extended view for
the Login screenlet that adds a new button to show the password in the clear.
The view uses custom layout file
[`login_westeros.xml`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/viewsets/westeros/src/main/res/layout/login_westeros.xml) 
and custom view class
[`LoginView`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/viewsets/westeros/src/main/java/com/liferay/mobile/screens/viewsets/westeros/auth/login/LoginView.java). 
The Westeros view set also contains an Extended view for the User Portrait
screenlet that changes the border color and width of the user's portrait
picture. It uses custom layout file
[`userportrait_westeros.xml`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/viewsets/westeros/src/main/res/layout/userportrait_westeros.xml) 
and custom view class
[`UserPortraitView`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/viewsets/westeros/src/main/java/com/liferay/mobile/screens/viewsets/westeros/userportrait/UserPortraitView.java). 

Awesome! Now you know how to create Extended views. Next, learn how to create a
Full view. 

## Full View [](id=full-view)

A Full view has a unique screenlet class, view class, and layout XML file. It's
standalone and doesn't inherit from any view. You should create a Full view if
there's no other view that you can extend to meet your needs or if your
screenlet's behavior can only be augmented by customizing its listeners or
calling custom interactors. To create a Full view, you must create its screenlet
class, view class, and layout XML file. The example Full view here for the Login
screenlet presents a single `EditText` component for the user name. For the
password, it uses
[`Secure.ANDROID_ID`](http://developer.android.com/reference/android/provider/Settings.Secure.html#ANDROID_ID).
The
[Screens Test App](https://github.com/liferay/liferay-screens/tree/1.0.0/android/samples/test-app)
uses
[this Full view](https://github.com/liferay/liferay-screens/tree/1.0.0/android/samples/test-app/src/main/java/com/liferay/mobile/screens/testapp/fullview). 

1.  Create a new layout XML file and build your UI in it. A good way to start
    building your UI is to duplicate another view's layout XML file and use it
    as a template. Name your layout XML file after the view's screenlet and
    intended use case. Name its root element after the fully-qualified class
    name of your custom view (you'll create this next). The Test App's Full view
    layout XML file for the Login screenlet is called
    [`login_full.xml`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/samples/test-app/src/main/res/layout/login_full.xml).
    It specifies `EditText` and `Button` elements copied from the
    LongScreenlet's Default view file
    [`login_default.xml`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/library/core/src/main/res/layout/login_default.xml).

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

2.  Create a new custom view class named after the layout's root element.
    The tutorial on creating Android screenlets explains how to
    [create a view class](/develop/tutorials/-/knowledge_base/6-2/creating-android-screenlets#creating-the-screenlets-view-class).
    The custom view class
    [`LoginFullView`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/samples/test-app/src/main/java/com/liferay/mobile/screens/testapp/fullview/LoginFullView.java),
    for example, implements the
    [`LoginViewModel`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/library/core/src/main/java/com/liferay/mobile/screens/auth/login/view/LoginViewModel.java)
    interface by extending the Default
    [`LoginView`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/library/core/src/main/java/com/liferay/mobile/screens/viewsets/defaultviews/auth/login/LoginView.java)
    class. Note that you don't have to extend a view class to implement a view
    model interface, but you might want to for convenience. To return the
    `ANDROID_ID`, the `LoginFullView` custom view class overrides the
    `getPassword()` method. 

3. Create a new screenlet class that inherits the base screenlet class. This new 
    class is where you can add custom behavior to the listeners or call custom
    interactors. The screenlet class
    [`LoginFullScreenlet`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/samples/test-app/src/main/java/com/liferay/mobile/screens/testapp/fullview/LoginFullScreenlet.java),
    for example, extends
    [`LoginScreenlet`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/library/core/src/main/java/com/liferay/mobile/screens/auth/login/LoginScreenlet.java)
    and overrides the `onUserAction` method to log interactor calls. 

4.  Insert your view's screenlet in any of your activities or fragments, using
    your new layout's name as the `liferay:layoutId` attribute's value. For
    example, to use the new `login_password` layout, insert `LoginScreenlet` in
    an activity or fragment, and set
    `liferay:layoutId="@layout/login_password"`.  

The 
[Westeros view set's Full view for SignUpScreenlet](https://github.com/liferay/liferay-screens/tree/1.0.0/android/viewsets/westeros/src/main/java/com/liferay/mobile/screens/viewsets/westeros/auth/signup) 
uses a
[custom screenlet class](https://github.com/liferay/liferay-screens/blob/1.0.0/android/viewsets/westeros/src/main/java/com/liferay/mobile/screens/viewsets/westeros/auth/signup/SignUpScreenlet.java) 
to add a
[new listener](https://github.com/liferay/liferay-screens/blob/1.0.0/android/viewsets/westeros/src/main/java/com/liferay/mobile/screens/viewsets/westeros/auth/signup/SignUpListener.java).
The custom screenlet class also adds a new user action that calls the base
interactor `SignUpInteractor`. 

Sweetness! Now you know how to create a Full view. Next, you'll learn how to 
package views for distribution. 

## Packaging Your Views [](id=packaging-your-views)

If you want to distribute or reuse views, you should package them in a module
that is then added as an app's project dependency. To do this, use the 
[material](https://github.com/liferay/liferay-screens/tree/1.0.0/android/viewsets/material) 
sub-project as a template for your new
[`build.gradle`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/viewsets/material/build.gradle) 
file. 

To use a packaged view, you must import its module into your project by 
specifying its location in your
[`settings.gradle`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/samples/bankofwesteros/settings.gradle)
file. The Bank of Westeros and test-app projects use custom views `westeros` and
`material`, respectively. These projects exemplify using independent views in a
project. 

If you want to redistribute your view and let others use it, you can upload it 
to jCenter or Maven Central. In the example
[`build.gradle`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/samples/bankofwesteros/build.gradle) 
file, after entering your bintray api key you can execute `gradlew
bintrayupload` to upload your project to jCenter. When finished, anyone can use
the view, as they would any Android dependency, by adding the repository,
artifact, group ID, and version to their gradle file. 

Super! Now you know how to create and package views in Liferay Screens for 
Android. This gives you extensive control over your app's look and feel, and 
also lets you distribute and reuse your views. 

## Related Topics [](id=related-topics)

[Using Views in Android Screenlets](/develop/tutorials/-/knowledge_base/6-2/using-views-in-android-screenlets)

[Architecture of Liferay Screens for Android](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android)

[Creating Android Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-android-screenlets)
