# Creating Views in Liferay Screens for Android [](id=creating-views-in-liferay-screens-for-android)

Liferay Screens is highly customizable. In addition to letting you create your 
own screenlets, Screens for Android lets you create your own views. This gives 
you nearly limitless options for customizing your app's look and feel. Screens 
for Android includes several views that you can use to build your own. You can 
also write your view completely from scratch. 

This tutorial shows you how to create views in Liferay Screens for Android. 
Before proceeding, you may want to read the tutorial 
[Architecture of Liferay Screens for Android](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android) 
to understand the concepts underlying views. It may also be useful to 
read the tutorial [Creating Screenlets in Liferay Screens for Android](/tutorials/-/knowledge_base/6-2/creating-screenlets-in-liferay-screens-for-android). 
Now get ready to create some great views! 

## View Types [](id=view-types)

The first step in creating a new view is deciding what kind of view to create.
There are three basic view types:

- Full view
- Child view
- Extended view

For a detailed explanation of these view types, see the 
[section on views in the Screens architecture tutorial](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android#the-view-layer-of-screens-for-android). 
The following sections show you how to create each type of view, using 
`LoginScreenlet` as an example. You'll begin by creating a new Full view. 

## Full View [](id=full-view)

By using different UI components and input data, a Full view can present a 
completely different layout. The example here presents a single `EditText` 
component for the user name. For the password, [`Secure.ANDROID_ID`](http://developer.android.com/reference/android/provider/Settings.Secure.html#ANDROID_ID) 
is used. 

1. Create a new layout called `login_full.xml`. This is where the new UI is 
   built. Since the example here creates a new view for `LoginScreenlet`, a good 
   way to start is to duplicate `login_default.xml` and use it as a template. 
   Use a custom view class as the root view: 

        <?xml version="1.0" encoding="utf-8"?>
        <com.your.package.LoginFullView
            xmlns:android="http://schemas.android.com/apk/res/android"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">
        
            <EditText
                android:id="@+id/login"
                android:hint="@string/email_address"
                android:inputType="textEmailAddress" />
        
            <Button
                android:id="@+id/login_button"
                android:text="@string/sign_in" />
        
        </com.your.package.LoginFullView>

2. Create the new custom view class used in the layout XML. In this example, 
   this is `LoginFullView`. As before, you can use the default `LoginView` 
   class as a template. In the new class, first get a reference to the UI 
   components. The components in this example are the *Add* button and its 
   listener. The class must also implement the `LoginViewModel` interface and 
   include its getter and setter methods. In this case, the `getPassword()` 
   method returns the `ANDROID_ID`. 

3. Insert the `LoginScreenlet` in any of your activities or fragments, using 
   your new layout as the `liferay:layoutId` attribute's value. For example, to 
   use the layout created here you need to use `@layout/login_full` as the 
   `liferay:layoutId` attribute's value. 

Sweetness! Now you know how to create a Full view. Next, you're shown how to 
create a Child view. 

## Child View [](id=child-view)

Child views inherit another view's UI components, but use a different layout to 
present them. The changes in a new Child view are therefore only visual. For 
example, the Child view here presents the same components as the Default view, 
but uses a completely different layout; the view is oriented horizontally 
instead of vertically. The following steps show you how to create a Child view. 

1. Create a new layout file called `login_horizontal.xml`. You'll build the new 
   UI in this layout. A good way to start is to duplicate the parent layout's 
   XML and use it as a template. The child components such as the `EditText` 
   fields, `Button` elements, and so on must use the same IDs as the parent 
   view. You must also use the same custom view class as the parent view. The 
   example `login_horizontal.xml` is shown here: 

        <?xml version="1.0" encoding="utf-8"?>
        <com.liferay.mobile.screens.viewsets.defaultviews.auth.login.LoginView
            xmlns:android="http://schemas.android.com/apk/res/android"
            style="@style/default_screenlet"
            android:orientation="horizontal">
        
            <EditText
                android:id="@+id/login"
                style="@style/default_edit_text"
                android:drawableLeft="@drawable/default_mail_icon"
                android:hint="@string/email_address"
                android:inputType="text" />
        
            <EditText
                android:id="@+id/password"
                style="@style/default_edit_text"
                android:drawableLeft="@drawable/default_lock_icon"
                android:hint="@string/password"
                android:inputType="textPassword" />
        
            <Button
                android:id="@+id/login_button"
                style="@style/default_submit_button"
                android:text="@string/sign_in" />
        
        </com.liferay.mobile.screens.viewsets.defaultviews.auth.login.LoginView>

2. Insert the `LoginScreenlet` in any of your activities or fragments, using 
   your new layout as the `liferay:layoutId` attribute's value. In this case, 
   `@layout/login_horizontal` is used as the `liferay:layoutId` attribute's 
   value. 

Stupdendous! Now you know how to create Child views. Creating an extended view 
is shown next. 

## Extended View [](id=extended-view)

Extended views inherit another view's UI components and layout. This lets you 
add new functionality. The example Extended view here presents the same 
components as the Default view, but adds new functionality to the screenlet: the 
password strength is computed before sending the request. 

1. Create a new layout file called `login_password.xml`. However, there's no 
   need to actually build a new UI. In your layout XML, all you need to do is 
   change the parent's class to a new custom class. To use the parent's UI, use 
   the `<include>` tag to import its layout:

        <?xml version="1.0" encoding="utf-8"?>
        <com.your.package.LoginCheckPasswordView
            xmlns:android="http://schemas.android.com/apk/res/android"
            style="@style/default_screenlet">
            
        <include layout="@layout/login_default"/>
        
        </com.your.package.LoginCheckPasswordView>

2. Create the new view class, extending the parent view's class. The example 
   here creates `LoginCheckPasswordView` using the default `LoginView` parent 
   class. Next, override the `onClick` method and perform the password strength 
   computation in it. If the password is strong enough, call `super`. Otherwise, 
   show a message dialog to the user that tells them their password isn't strong 
   enough.

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

3. Insert the `LoginScreenlet` in any of your activities or fragments, using 
   your new layout as the `liferay:layoutId` attribute's value. In this case, 
   `@layout/login_password` is used as the `liferay:layoutId` attribute's value.

Awesome! Now you know how to create all three view types in Liferay Screens for 
Android. Next, you'll learn how to package views for distribution. 

## Packaging Your Views [](id=packaging-your-views)

Views are typically created as source code inside your app's project. If you 
want to distribute or reuse your views, you should package them in a project 
that can then be added as a dependency in other projects. To do this, use the 
[viewsets](https://github.com/liferay/liferay-screens/tree/master/android/library/viewsets)
sub-project as a template and use a similar [`build.gradle`](https://github.com/liferay/liferay-screens/blob/master/android/library/viewsets/build.gradle)
file. 

Super! Now you know how to create and package views in Liferay Screens for 
Android. This gives you extensive control over your app's look and feel, and 
also lets you distribute your views to others.

## Related Topics [](id=related-topics)

[Using Views in Liferay Screens for Android](/tutorials/-/knowledge_base/6-2/using-views-in-liferay-screens-for-android)

[Architecture of Liferay Screens for Android](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android)

[Creating Screenlets in Liferay Screens for Android](/tutorials/-/knowledge_base/6-2/creating-screenlets-in-liferay-screens-for-android)

[Liferay Screens Overview](/tutorials/-/knowledge_base/6-2/liferay-screens-overview)
