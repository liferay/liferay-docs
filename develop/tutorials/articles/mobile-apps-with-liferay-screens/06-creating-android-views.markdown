# Creating Android Views [](id=creating-android-views)

By letting you create your own views, Screens for Android gives you nearly 
limitless ways to customize your app's look and feel. You can create your views 
from scratch, or use the views included with Screens as a foundation for your 
views. There are three types of views you can create:

- *Child view*: Reuses Java the parent view's Java code and changes the `xml` 
  layout.
- *Extended view*: Inherits the parent view's Java code.
- *Full view*: Overrides *all* Java code, including the screenlet code.

For a detailed explanation of these, see the 
[section on themes in architecture tutorial](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android#the-view-layer).

This tutorial shows you how to create all three types of views. Before 
proceeding, you may want to read the tutorial 
[Architecture of Liferay Screens for Android](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android) 
to understand view concepts and components. It may also be useful to read the 
tutorial [Creating Android Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-android-screenlets). 
Now get ready to create some great views! 

## Deciding Your View's Location

After deciding the type of view to create, you need to decide where to create 
it. If you want to reuse or redistribute it, you should create it in a module. 
You can then upload it to jCenter or Maven Central for redistribution. The 
section at the end of this tutorial shows you how to do this. Otherwise, you can 
create your view directly inside your app project. 

First, you'll learn how to create a Child view. 

## Child View [](id=child-view)

Child views reuse another view's UI components, but present them in a different 
layout. The changes in a Child view are therefore only visual. For example, the 
example Child view here presents the same components as the Default view, but 
displays them in a more compact layout. The following steps show you how to 
create a Child view. A Child view for `LoginScreenlet` is used as an example. 

1. Create a new layout file for your view and build your UI in it. You should 
   name this file after the view's screenlet and what you intend the view to do. 
   As you build your UI, make sure that the UI components use the same IDs as 
   those in the parent view. You must also use the same custom view class as the 
   parent view. A good way to start building your UI is to duplicate the parent 
   layout's XML and use it as a template.
   
    For example, the layout file for the Child view here is called 
    `login_compact.xml`. Its `EditText` and `Button` elements use the same IDs 
    as its parent default view. Its parent view class, `LoginView`, is used as 
    the custom view class: 

        <?xml version="1.0" encoding="utf-8"?>
        <com.liferay.mobile.screens.viewsets.defaultviews.auth.login.LoginView
            xmlns:android="http://schemas.android.com/apk/res/android"
            style="@style/default_screenlet">
        
            <EditText
                android:id="@+id/login"
                style="@style/default_edit_text"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginBottom="15dp"
                android:drawableLeft="@drawable/default_mail_icon"
                android:hint="@string/email_address"
                android:inputType="text" />
        
            <EditText
                android:id="@+id/password"
                style="@style/default_edit_text"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginBottom="15dp"
                android:drawableLeft="@drawable/default_lock_icon"
                android:hint="@string/password"
                android:inputType="textPassword" />
        
            <Button
                android:id="@+id/login_button"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                style="@style/default_button"
                android:text="@string/sign_in" />
        
        </com.liferay.mobile.screens.viewsets.defaultviews.auth.login.LoginView>

2. Insert your view's screenlet in any of your activities or fragments, using 
   your new layout as the `liferay:layoutId` attribute's value. For example, to 
   use the new `login_compact` layout, insert `LoginScreenlet` in an activity or 
   fragment and set the `liferay:layoutId` attribute to `@layout/login_compact`.

Another good example of this approach is the [`SignUpScreenlet`](https://github.com/liferay/liferay-screens/blob/master/android/library/viewsets/src/main/res/layout/sign_up_material.xml) 
in the Material viewset. It uses the default base view class and a custom layout 
with a different theme, colors, and spacing. 

Stupdendous! Now you know how to create Child views. Creating an extended view 
is shown next. 

## Extended View [](id=extended-view)

Extended views inherit another view's UI components and layout, letting you add 
or alter functionality. The example Extended view here presents the same 
components as the Default view, but adds new functionality to the screenlet: the 
password strength is computed before sending the request. Of course, you're not 
restricted to password strength computations; you can implement other 
functionality if you wish. 

1. Create a new layout file for your view and build your UI in it. You should 
   name this file after the view's screenlet and what you intend the view to do. 
   Use the parent's layout as a template. For example, the example Extended 
   view's new layout file is called `login_password.xml`, and is based on the 
   Default view's layout for `LoginScreenlet`.

2. Create a new view class, extending the parent view's class. You should name 
   this new class after the view's screenlet and the functionality you'll add or 
   override in it. For example, the example view class here is called 
   `LoginCheckPasswordView` and extends the default [`LoginView`](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/java/com/liferay/mobile/screens/viewsets/defaultviews/auth/login/LoginView.java). 
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
   point to class you just created. This is shown here for the example 
   `login_password.xml`:

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

Several examples of this approach are found in the [Bank of Westeros](https://github.com/liferay/liferay-screens/tree/master/android/samples/bankofwesteros) 
sample app. For example, the app's `LoginView` uses a custom view class to add a 
new button that shows the password in clear. The `UserPortraitView` also changes 
the border color and width of the user's portrait picture. The 
`DDLFieldSelectView` is used to change the behavior and positioning of the 
label. 

Awesome! Now you know how to create Extended views. Creating a Full view is 
shown next. 

## Full View [](id=full-view)

A Full view presents a different layout by using different UI components and 
input data. The example Full view here for `LoginScreenlet` presents a single 
`EditText` component for the user name. For the password, [`Secure.ANDROID_ID`](http://developer.android.com/reference/android/provider/Settings.Secure.html#ANDROID_ID) 
is used. A complete example of this Full view is the `LoginFullScreenlet` used 
in the [Demo App](https://github.com/liferay/liferay-screens/tree/master/android/samples/test-app). 

1. Create a new layout file for your view and build your UI in it. You should 
   name this file after the view's screenlet and what you intend the view to do. 
   Also, use a custom view class as the root view (you'll create this next). A 
   good way to start building your UI is to use the layout file from another 
   view of the same screenlet as a template. Since the example layout file here 
   is intended to create a new Full view for `LoginScreenlet`, its layout file 
   is called `login_full.xml`. It also includes the `EditText` and `Button` 
   elements from the default view for `LoginScreenlet`:

        <?xml version="1.0" encoding="utf-8"?>
        <com.your.package.LoginFullView
                xmlns:android="http://schemas.android.com/apk/res/android"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="vertical">
        
        <EditText
                android:id="@+id/login"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginBottom="20dp"
                android:hint="Email Address"
                android:inputType="textEmailAddress"/>
        
        <Button
                android:id="@+id/login_button"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="Sign In"/>
        
        </com.your.package.LoginFullView>

2. Create the new custom view class used in the layout XML. This class must 
   implement the screenlet's view model interface and include its getter and 
   setter methods. You also need to get a reference to the UI components in your 
   view. In this example, `LoginFullView` is the custom view class. It 
   implements the `LoginViewModel` interface by extending the default `LoginView` 
   class. It overrides the `getPassword()` method to return the `ANDROID_ID`. 

3. Create a new class that inherits the base screenlet class. This new class is 
   where you can add custom behavior to the listeners, or call a custom 
   interactor. In this example, this new class is `LoginFullScreenlet`. It 
   inherits `LoginScreenlet` and overrides `onUserAction` to log interactor 
   calls.

4. Insert your view's screenlet in any of your activities or fragments, using 
   your view's layout as the `liferay:layoutId` attribute's value. For example, 
   to use the example theme here, insert `LoginScreenlet` in an activity or 
   fragment and set the `liferay:layoutId` attribute to 
   `@layout/login_full`. 

Another good example of this approach is the `SignUpScreenlet` found in the 
[Bank of Westeros](https://github.com/liferay/liferay-screens/tree/master/android/samples/bankofwesteros) 
app. It uses a custom screenlet class to add a new listener to the base 
`SignUpScreenlet`, and a new user action (this can be used to call a custom 
interactor). 

Sweetness! Now you know how to create a Full view. Next, you'll learn how to 
package views for distribution. 

## Packaging Your Views [](id=packaging-your-views)

If you want to distribute or reuse views, you should package them in a module 
that is then added as an app's project dependency. To do this, use the 
[material](https://github.com/liferay/liferay-screens/tree/master/android/viewsets/material) 
subproject as a template for your new [`build.gradle`](https://github.com/liferay/liferay-screens/blob/master/android/viewsets/material/build.gradle) 
file. 

To use a packaged view, you need to import the new module into your project by 
specifying its location in your [`settings.gradle`](https://github.com/liferay/liferay-screens/blob/master/android/samples/bankofwesteros/settings.gradle). 
The Bank of Westeros and test-app projects each use a custom theme (`westeros` 
and `material`, respectively). These projects are good examples of how to use an 
independent theme in your project. 

If you want to redistribute your theme and let others use it, you can upload it 
to jCenter or Maven Central. In the example [`build.gradle`](https://github.com/liferay/liferay-screens/blob/master/android/samples/bankofwesteros/build.gradle) 
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
