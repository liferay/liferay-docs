# Full View [](id=full-view)

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
