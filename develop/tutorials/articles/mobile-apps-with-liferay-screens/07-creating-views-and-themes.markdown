# Creating Views and Themes [](id=creating-views-and-themes)

Liferay Screens is highly customizable. In addition to letting you create your 
own screenlets, Screens lets you create your own views and themes. This gives 
you nearly limitless options for customizing your app's look and feel. Screens 
includes several views and themes that you can use to build your own. You can 
also write your view or theme completely from scratch. 

This tutorial shows you how to create views and themes, covering view creation 
first. Recall that views are exclusive to Screens for Android, while themes are 
exclusive to Screens for iOS. Although named differently, both serve the same 
function in their respective Screens implementation. Before proceeding, you may 
want to read the tutorial [Architecture of Liferay Screens](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens) 
to understand the concepts behind views and themes. It may also be useful to 
read the tutorial [Creating Screenlets](/tutorials/-/knowledge_base/6-2/creating-screenlets). 
Now get ready to create some great views and themes!

## Creating a View in Liferay Screens for Android [](id=creating-a-view-in-liferay-screens-for-android)

The first step in creating a new view is deciding what kind of view to create.
There are three basic view types:

- Full view
- Child view
- Extended view

For a detailed explanation of these view types, see the 
[section on views in the Screens architecture tutorial](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens#the-view-layer-of-screens-for-android). 
The following sections show you how to create each type of view, using 
`LoginScreenlet` as an example. You'll begin by creating a new Full view. 

### Full View [](id=full-view)

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

### Child View [](id=child-view)

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
        <com.liferay.mobile.screens.themes.default.auth.login.LoginView
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
        
        </com.liferay.mobile.screens.themes.auth.login.LoginScreenletView>

2. Insert the `LoginScreenlet` in any of your activities or fragments, using 
   your new layout as the `liferay:layoutId` attribute's value. In this case, 
   `@layout/login_horizontal` is used as the `liferay:layoutId` attribute's 
   value. 

Stupdendous! Now you know how to create Child views. Creating an extended view 
is shown next. 

### Extended View [](id=extended-view)

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

### Packaging Your Views [](id=packaging-your-views)

Views are typically created as source code inside your app's project. If you 
want to distribute or reuse your views, you should package them in a project 
that can then be added as a dependency in other projects. To do this, use the 
[viewsets](https://github.com/liferay/liferay-screens/tree/master/android/library/viewsets)
sub-project as a template and use a similar [`build.gradle`](https://github.com/liferay/liferay-screens/blob/master/android/library/viewsets/build.gradle)
file. 

Next, creating themes in Liferay Screens for iOS is discussed.

## Creating a Theme in Liferay Screens for iOS [](id=creating-a-theme-in-liferay-screens-for-ios)

The first step in creating a new theme is deciding what kind of theme to create. 
There are three theme types: 

- Full theme
- Child theme
- Extended theme

For a detailed explanation of these theme types, see the 
[section on themes in the Screens architecture tutorial](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens#the-theme-layer-of-screens-for-ios).
The following sections show you how to create each type of view, using 
`LoginScreenlet` as an example. You'll begin by creating a new Full view.

### Full Theme [](id=full-theme)

A Full theme can present a completely different layout, using different UI 
components and input data. The example here presents a single `UITextField` 
for the user name. The [UDID](http://www.idownloadblog.com/2010/12/21/iphone-udid/) 
is used for the password. 

1. Create a new `xib` file. Since the theme in this example is for 
   `LoginScreenlet`, its `xib` file is called `LoginScreenlet_full.xib`. This 
   file is where you'll use Interface Builder to build your new UI. A good way 
   to start is to duplicate the `xib` file for the screenlet your theme applies 
   to and then use it as a template. This example uses 
   `LoginScreenlet_default.xib` as a template. Next, insert your UI components 
   in your `xib` file. For this example, insert the text field for the user name 
   and add a *Sign In* button with the same `restorationIdentifier` as the 
   Default theme's button. 

    ![Figure 1: The new `xib` file for full theme.](../../images/screens-ios-xcode-full-theme.png)

2. Create a new view class for your theme. The view class in this example is 
   called `LoginScreenletView_full`. As with the `xib` file, you can duplicate 
   the `LoginScreenletView_default` class and use it as a template. In this 
   class, add all the `@IBOutlet` properties or `@IBAction` methods you need to 
   bind your UI components. This class must inherit the `BaseScreenletView` 
   class and conform to the `LoginScreenletData` protocol, implementing the 
   corresponding getters and setters. In this example, the `password` getter 
   returns the UDID. 

3. Set your theme's new view class as your `xib` file's custom class. Also, bind 
   the `@IBOutlet` and `@IBAction` to your class. In the example here, 
   `LoginScreenletView_full` is set as the `LoginScreenlet_full.xib` file's 
   custom class. The `@IBOutlet` and `@IBAction` is also bound to this class. 

4. Install the new theme and insert the screenlet it applies to in any of your 
   view controllers. Use `full` as the `themeName` property's value. Your new 
   look and feel is then shown in Interface Builder. This is done for 
   `LoginScreenlet` in this example. 

Super! Now that you know how to create a Full theme, you'll learn to create a 
Child theme.

### Child Theme [](id=child-theme)

A Child theme inherits only the look and feel of its parent. It doesn't inherit 
any code. The example theme here presents the same components as the Default 
theme, but is adjusted to a larger resolution. This makes it appropriate for 
iOS devices such as the iPad, iPhone 6, and iPhone 6 Plus. 

1. Create a new `xib` file. Since the theme in this example uses 
   `LoginScreenlet`, the new `xib` file is called `LoginScreenlet_large.xib`. 
   This file is where you'll use Interface Builder to build your new UI. A good 
   way to start is to duplicate the `xib` file for the screenlet your theme 
   applies to and then use it as a template. This example uses 
   `LoginScreenlet_default.xib` as a template. 

    ![Figure 2: The new `xib` file for the Child theme.](../../images/screens-ios-xcode-child-theme.png)

2. Change the position, size, or other properties of the parent components. 
   However, be sure not to change the custom class, outlet connection, or 
   `restorationIdentifier`. 

3. Install your new theme, insert the `LoginScreenlet` in any of your view 
   controllers, and use `large` as the value of the screenlet's `themeName` 
   property. Your new look and feel is shown in Interface Builder. 

Fantastic! Last but certainly not least, you'll learn how to create an Extended 
theme. 

### Extended Theme [](id=extended-theme)

An Extended theme inherits the functionality and UI of its parent. This lets you
add your own functionality and UI on top of it. For an example of an Extended 
theme, see the [Flat7 theme source code](https://github.com/liferay/liferay-screens/tree/master/ios/Library/Themes/Flat7). 
The Extended theme here presents the same components as the Default theme, but 
sets new translated strings and contains a few new animations. A new UI 
component is also introduced to configure whether the password is shown when 
typed by the user. 

1. Create a new `xib` file. Since the theme in this example uses 
   `LoginScreenlet`, the new `xib` file is called `LoginScreenlet_ext.xib`. This 
   file is where you'll use Interface Builder to build your new UI. A good way 
   to start is to duplicate the `xib` file for the screenlet your theme applies 
   to and then use it as a template. This example uses 
   `LoginScreenlet_default.xib` as a template.  Also, add a new `UISwitch` 
   component to configure the password presentation. 

    ![Figure 3: New `xib` file for the Extended theme.](../../images/screens-ios-xcode-ext-theme.png)

2. Create a new view class for your theme, based on the parent class. The 
   view class in this example is called `LoginScreenletView_ext` and uses 
   `LoginScreenletView_default` as the parent class. To set new translations, 
   you can override the `onSetTranslations` method. For other customizations, 
   consider overriding the parent class' other methods, or add new delegates 
   to the class. Also, you should add a new `@IBOutlet` or `@IBAction` for the 
   new `IBSwitch` component. 

3. Set your new view class as the custom class for your theme's `xib` file. In 
   this example, `LoginScreenletView_ext` is set as the `LoginScreenlet_ext.xib` 
   file's custom class. Also, bind your new `@IBOutlet` and `@IBAction` to your 
   class. 

4. Install the new theme and insert the screenlet it applies to in any of your 
   new controllers. The screenlet in this example is `LoginScreenlet`. Also, use 
   `ext` as the value for the screenlet's `themeName` property. Your new look 
   and feel is shown in Interface Builder. 

Well done! You now know how to create an Extended theme in Screens for iOS. 
You're the master of the themes! 

## Related Topics [](id=related-topics)

[Using Views and Themes](/tutorials/-/knowledge_base/6-2/using-views-and-themes)

[Architecture of Liferay Screens](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens)

[Creating Screenlets](/tutorials/-/knowledge_base/6-2/creating-screenlets)

[Liferay Screens Overview](/tutorials/-/knowledge_base/6-2/liferay-screens-overview)
