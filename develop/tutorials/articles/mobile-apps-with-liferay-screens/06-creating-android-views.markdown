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

- *Extended view*: Inherits the parent view’s behavior and appearance, but lets 
  you change and add to both. Creating an Extended view is ideal when you need 
  to modify or add to the parent view's behavior. Extended views reuse the 
  parent view's screenlet class, but let you change its view class and layout 
  XML. An Extended view's parent must be a Full view.

- *Full view*: A view with no parent. Creating a Full view is ideal when you 
  need a screenlet to have completely different behavior and appearance. Since a 
  Full view doesn't have a parent view, it has its own screenlet class, view 
  class, and layout XML. Any Full view can be inherited by a Child or Extended 
  view.

For a detailed explanation of these, see the 
[section on views in architecture tutorial](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android#the-view-layer).

This tutorial shows you how to create all three types of views. Before 
proceeding, you may want to read the tutorial 
[Architecture of Liferay Screens for Android](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android) 
to understand view concepts and components. It may also be useful to read the 
tutorial [Creating Android Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-android-screenlets). 
Now get ready to create some great views! 

## Determining Your View's Location

After deciding the type of view to create, you need to decide where to create
it. If you want to reuse or redistribute it, you should create it in a module.
You can then upload it to jCenter or Maven Central for redistribution. The
section at the end of this tutorial shows you how to do this. Otherwise, you can
create your view directly inside your app project. 

First, you'll learn how to create a Child view.

## Child View [](id=child-view)

A Child view presents the same behavior and UI components as another view (its 
parent view). You can can only change the UI components' appearance and 
position. The changes in a Child view are therefore only visual. Due to this, 
you only need to create a new layout XML file when creating a Child view; the 
parent's view class and screenlet class are inherited. A Child view's parent 
must be a Full view. For example, the Child view discussed here presents the 
same UI components as the Default view for `LoginScreenlet`, but displays them 
in a more compact layout. The following steps show you how to create a Child 
view. 

1. Create a new layout file for your view and build your UI in it. You should
   name this file after the view's screenlet and its intended use case. As you 
   build your UI, make sure that the UI components use the same IDs as those in 
   the parent view. You must also use the same custom view class as the parent 
   view. A good way to start building your UI is to duplicate the parent 
   layout's XML file and use it as a template. For example, the parent layout 
   for the Default view of `LoginScreenlet` is [`login_default.xml`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/library/core/src/main/res/layout/login_default.xml). 
   You can browse the other layouts for Screens's Default views [here on GitHub](https://github.com/liferay/liferay-screens/tree/1.0.0/android/library/core/src/main/res/layout). 
   
    In the example here, the Child view's layout file for `LoginScreenlet` is 
    called `login_compact.xml`. This is because it's designed to display the UI 
    components for `LoginScreenlet` in a more compact layout. Its `EditText` and 
    `Button` elements use the same IDs as its parent Default view. Its parent 
    view class, `LoginView`, is used as the custom view class: 

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

2. Insert your view's screenlet in any of your activities or fragments, using
   your new layout as the value of the `liferay:layoutId` attribute. For
   example, to use the new `login_compact` layout, insert `LoginScreenlet` in an
   activity or fragment and set the `liferay:layoutId` attribute to
   `@layout/login_compact`.

Another good example of this approach is the [`SignUpScreenlet` layout](https://github.com/liferay/liferay-screens/blob/1.0.0/android/viewsets/material/src/main/res/layout/sign_up_material.xml) 
in the Material view set. It uses `SignUpScreenlet`'s [Default view class](https://github.com/liferay/liferay-screens/blob/1.0.0/android/library/core/src/main/java/com/liferay/mobile/screens/viewsets/defaultviews/auth/signup/SignUpView.java), 
which in turn uses [`SignUpScreenlet`'s Default screenlet class](https://github.com/liferay/liferay-screens/blob/1.0.0/android/library/core/src/main/java/com/liferay/mobile/screens/auth/signup/SignUpScreenlet.java). 
This lets it present the same UI components and functionality as the Default 
`SignUpScreenlet`, but in [Android's Material design](http://www.google.com/design/spec/material-design/introduction.html). 

Stupdendous! Now you know how to create Child views. Next, learn how to create
Extended views. 

## Extended View [](id=extended-view)

An Extended view inherits another view's UI components and behavior, letting you 
add to or alter both. Extended views are therefore capable of augmenting a 
Screenlet's functionality in addition to making visual changes. This is done by 
creating a custom view class and a layout XML file. However, Extended views 
can't customize screenlet listeners or call custom interactors. This is because 
the screenlet class is inherited from the parent view. An Extended view's parent 
must be a Full view. The example Extended view discussed here presents the same 
UI components as the Default view for `LoginScreenlet`, but adds new 
functionality to the screenlet. The password strength is computed before sending 
the request. Of course, you're not restricted to password strength computations; 
you can implement other functionality if you wish. 

1. Create a new layout file for your view and build your UI in it. You should
   name this file after the view's screenlet and intended use case. A good way 
   to start building your UI is to duplicate the parent layout's XML file and 
   use it as a template. For example, new layout file for the Extended view here 
   is called `login_password.xml` because it's designed to add a password 
   strength computation to `LoginScreenlet`. It's also based on the 
   [Default view's layout for `LoginScreenlet`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/library/core/src/main/res/layout/login_default.xml). 

2. Create a new view class that extends the parent view's class. You should name
   this new class after the view's screenlet and the functionality you'll add or
   override in it. The example view class here is called
   `LoginCheckPasswordView` and extends the Default
   [`LoginView`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/library/core/src/main/java/com/liferay/mobile/screens/viewsets/defaultviews/auth/login/LoginView.java).
   The `onClick` method is overridden to perform a password strength 
   computation: 

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

3. Change the class reference in the layout you created in the first step to
   point to the class you just created. For example, in `login_password.xml`, 
   this needs to be `LoginCheckPasswordView`:

        <com.your.package.LoginCheckPasswordView
            xmlns:android="http://schemas.android.com/apk/res/android"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">
            ...

4. Insert your view's screenlet in any of your activities or fragments, using 
   your view's layout as the `liferay:layoutId` attribute's value. For example, 
   to use the example theme here, insert `LoginScreenlet` in an activity or 
   fragment and set the `liferay:layoutId` attribute to 
   `@layout/login_password`.

Several examples of this approach are found in the [Bank of Westeros](https://github.com/liferay/liferay-screens/tree/1.0.0/android/samples/bankofwesteros) 
sample app's [Westeros view set](https://github.com/liferay/liferay-screens/tree/1.0.0/android/viewsets/westeros). 
For example, the Westeros view set contains an Extended view for `LoginScreenlet` 
that adds a new button to show the password in the clear. This view is 
implemented by the layout file [`login_westeros.xml`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/viewsets/westeros/src/main/res/layout/login_westeros.xml) 
and custom view class [`LoginView`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/viewsets/westeros/src/main/java/com/liferay/mobile/screens/viewsets/westeros/auth/login/LoginView.java). 
The Westeros view set also contains an Extended view for `UserPortraitScreenlet` 
that changes the border color and width of the user's portrait picture. This is 
implemented by the [`userportrait_westeros.xml`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/viewsets/westeros/src/main/res/layout/userportrait_westeros.xml) 
layout file and custom view class [`UserPortraitView`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/viewsets/westeros/src/main/java/com/liferay/mobile/screens/viewsets/westeros/userportrait/UserPortraitView.java). 

Awesome! Now you know how to create Extended views. Next, learn how to create a
Full view. 

## Full View [](id=full-view)

A Full view has a unique screenlet class, view class, and layout XML file. It 
doesn't inherit the UI components or behavior of another view. You should create 
a Full view when you don't need to inherit another view, or when you need to 
alter the core behavior of a screenlet by customizing its listeners or calling 
custom interactors. When creating a Full view, you must therefore create its 
screenlet class, view class, and layout XML file. The example Full view here 
for `LoginScreenlet` presents a single `EditText` component for the user name. 
For the password, [`Secure.ANDROID_ID`](http://developer.android.com/reference/android/provider/Settings.Secure.html#ANDROID_ID) 
is used. A complete example of [this Full view](https://github.com/liferay/liferay-screens/tree/1.0.0/android/samples/test-app/src/main/java/com/liferay/mobile/screens/testapp/fullview) 
is used in the [Screens Test App](https://github.com/liferay/liferay-screens/tree/1.0.0/android/samples/test-app). 

1. Create a new layout file for your view and build your UI in it. You should
   name this file after the view's screenlet and intended use case. Also, use a 
   custom view class as the root view (you'll create this next). A good way to 
   start building your UI is to use the layout file from another view of the 
   same screenlet as a template. Since the example layout file here is intended 
   to create a new Full view for `LoginScreenlet`, its layout file is called 
   [`login_full.xml`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/samples/test-app/src/main/res/layout/login_full.xml). 
   It also includes the `EditText` and `Button` elements from the [Default view](https://github.com/liferay/liferay-screens/blob/1.0.0/android/library/core/src/main/res/layout/login_default.xml) 
   for `LoginScreenlet`:

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

2. Create the new custom view class used in the layout XML. This class must 
   implement the screenlet's view model interface and include its getter and 
   setter methods. You also need to get a reference to the UI components in your 
   view. In this example, [`LoginFullView`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/samples/test-app/src/main/java/com/liferay/mobile/screens/testapp/fullview/LoginFullView.java) 
   is the custom view class. It implements the [`LoginViewModel`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/library/core/src/main/java/com/liferay/mobile/screens/auth/login/view/LoginViewModel.java) 
   interface by extending the Default [`LoginView`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/library/core/src/main/java/com/liferay/mobile/screens/viewsets/defaultviews/auth/login/LoginView.java) 
   class. Note that you don't have to extend a view class to implement a view 
   model interface, but you may wish to for convenience. To return the 
   `ANDROID_ID`, the `LoginFullView` class overrides the `getPassword()` method. 

3. Create a new screenlet class that inherits the base screenlet class. This new 
   class is where you can add custom behavior to the listeners or call a custom
   interactor. In this example, this new class is [`LoginFullScreenlet`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/samples/test-app/src/main/java/com/liferay/mobile/screens/testapp/fullview/LoginFullScreenlet.java). 
   It inherits [`LoginScreenlet`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/library/core/src/main/java/com/liferay/mobile/screens/auth/login/LoginScreenlet.java) 
   and overrides `onUserAction` to log interactor calls. 

4. Insert your view's screenlet in any of your activities or fragments, using 
   your view's layout as the value of the `liferay:layoutId` attribute. For
   example, to use the example theme here, insert `LoginScreenlet` in an
   activity or fragment and set the `liferay:layoutId` attribute to
   `@layout/login_full`. 

Another good example of this approach is the 
[Westeros view set's Full view for `SignUpScreenlet`](https://github.com/liferay/liferay-screens/tree/1.0.0/android/viewsets/westeros/src/main/java/com/liferay/mobile/screens/viewsets/westeros/auth/signup). 
It uses a [custom screenlet class](https://github.com/liferay/liferay-screens/blob/1.0.0/android/viewsets/westeros/src/main/java/com/liferay/mobile/screens/viewsets/westeros/auth/signup/SignUpScreenlet.java) 
to add [a new listener](https://github.com/liferay/liferay-screens/blob/1.0.0/android/viewsets/westeros/src/main/java/com/liferay/mobile/screens/viewsets/westeros/auth/signup/SignUpListener.java) 
to the base `SignUpScreenlet`. The custom screenlet class also adds a new user 
action that calls the base interactor, `SignUpInteractor`. 

Sweetness! Now you know how to create a Full view. Next, you'll learn how to 
package views for distribution. 

## Packaging Your Views [](id=packaging-your-views)

If you want to distribute or reuse views, you should package them in a module 
that is then added as an app's project dependency. To do this, use the 
[material](https://github.com/liferay/liferay-screens/tree/1.0.0/android/viewsets/material) 
subproject as a template for your new [`build.gradle`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/viewsets/material/build.gradle) 
file. 

To use a packaged view, you need to import the new module into your project by 
specifying its location in your [`settings.gradle`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/samples/bankofwesteros/settings.gradle). 
The Bank of Westeros and test-app projects each use a custom theme (`westeros` 
and `material`, respectively). These projects are good examples of how to use an 
independent theme in your project. 

If you want to redistribute your theme and let others use it, you can upload it 
to jCenter or Maven Central. In the example [`build.gradle`](https://github.com/liferay/liferay-screens/blob/1.0.0/android/samples/bankofwesteros/build.gradle) 
file, after entering your bintray api key you can execute `gradlew bintrayupload` 
to upload your project to jCenter. When finished, you and others can use your 
theme as any other Android dependency: simply add the repository, artifact, 
groupId, and version to your gradle file. 

Super! Now you know how to create and package views in Liferay Screens for 
Android. This gives you extensive control over your app's look and feel, and 
also lets you distribute and reuse your views. 

## Related Topics [](id=related-topics)

[Using Views in Android Screenlets](/develop/tutorials/-/knowledge_base/6-2/using-views-in-android-screenlets)

[Architecture of Liferay Screens for Android](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android)

[Creating Android Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-android-screenlets)
