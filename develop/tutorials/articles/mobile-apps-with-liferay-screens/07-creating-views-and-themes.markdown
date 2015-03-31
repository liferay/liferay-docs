# Creating Views and Themes

Liferay Screens includes several built-in views and themes that let you 
customize your app's look and feel. You can extend this even further by creating 
your own views and themes. Recall that views are exclusive to Screens for 
Android, while themes are exclusive to Screens for iOS. Although named 
differently, both serve the same function in their respective Screens 
implementation.

This tutorial shows you how to create views and themes, covering view creation 
first. Before proceeding, you may want to read the tutorial 
[Architecture of Liferay Screens](http://www.liferay.com/) to understand the 
concepts behind views and themes. It may also be useful to read the tutorial 
[Creating Screenlets](http://www.liferay.com/). 

## Creating a View in Liferay Screens for Android

The first step in creating a new view is deciding what kind of view to create.
There are three basic view types:

- Full view
- Child view
- Extended view

For a detailed explanation of these view types, see the 
[section on views in the Screens architecture tutorial](architecture.md#view-layer).
The following sections show you how to create each type of view, using 
`LoginScreenlet` as an example. You'll begin by creating a new Full view.

### Full View

By using different components and input data, a Full view can present a 
completely different layout. The example here presents a single `EditText` 
component for the user name. For the password, [`Secure.ANDROID_ID`](http://developer.android.com/reference/android/provider/Settings.Secure.html#ANDROID_ID) 
is used. 

1. Create a new layout called `login_full.xml`. This is where the new UI is 
    built. A good way to start is to duplicate `login_default.xml` and use it as 
    a template. Use a custom view class as the root view: 

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
    class as a template. In the new class, first get a reference to the 
    components. The components in this example are the Add button and its 
    listener. The class must also implement the `LoginViewModel` interface, with 
    its getter and setter methods. In this case, the `getPassword()` method 
    returns the `ANDROID_ID`.

3. Insert the `LoginScreenlet` in any of your activities or fragments, using 
    `@layout/login_full` as the `liferay:layoutId` attribute's value.

Sweetness! Now you know how to create a Full view. Next, you're shown how to 
create a Child view. 

### Child View

Child views inherit another view's components, but use a different layout to 
present them. As such, the changes in a Child view are only visual. For example, 
the Child view here presents the same components as the Default view, but uses a 
completely different layout. The view is oriented horizontally instead of 
vertically. The following steps show how to create a Child view.

1. Create a new layout file called `login_horizontal.xml`. This is where the new 
    UI is built. A good way to start is to duplicate the parent layout's XML and 
    use it as a template. The child components such as the `EditText` fields, 
    `Button` elements, and so on must use the same identifiers as the parent 
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

2. Insert the `LoginScreenlet` in any of your activities or fragments, and use 
    `@layout/login_horizontal` as the `liferay:layoutId` attribute's value.

Stupdendous! Now you know how to create Child views. Creating an extended view 
is shown next. 

### Extended View

Extended views inherit another view's components and layout, letting you add new 
functionality. The example Extended view here presents the same components as 
the Default view, but adds new functionality to the screenlet: the password 
strength is computed before sending the request.

1. Create a new layout file called `login_password.xml`. However, there's no 
    need to actually build a new UI. All you need to do is change the parent's 
    class to a new custom class. To use the same UI, use the `<include>` tag to 
    import the parent's layout:

        <?xml version="1.0" encoding="utf-8"?>
        <com.your.package.LoginCheckPasswordView
            xmlns:android="http://schemas.android.com/apk/res/android"
            style="@style/default_screenlet">
            
        <include layout="@layout/login_default"/>
        
        </com.your.package.LoginCheckPasswordView>

2. Create the new view class `LoginCheckPasswordView` using the default 
    `LoginView` parent class. Override the `onClick` method and perform the 
    password strength computation in it. If the password is strong enough, call 
    `super`. Otherwise, show a message dialog to the user that tells them their 
    password isn't strong enough.

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

3. Insert the `LoginScreenlet` in any of your activities or fragments and use 
    `@layout/login_password` as the `liferay:layoutId` attribute's value.

Awesome! Now you know how to create all three view types in Liferay Screens for 
Android. Next, you'll learn how to package views for distribution.

### Packaging Your Views

Views are typically created as source code inside your app's project. If you
want to distribute your views or reuse them in different projects, however, you
should package them in a project that is then added as an app's project
dependency. To do this, use the [viewsets](https://github.com/liferay/liferay-screens/tree/master/android/library/viewsets)
subproject as a template using a similar [`build.gradle`](https://github.com/liferay/liferay-screens/blob/master/android/library/viewsets/build.gradle)
file.

## Creating a Theme in Liferay Screens for iOS

The first step in creating a new theme is deciding what kind of theme to create. 
There are three theme types:

- Full theme
- Child theme
- Extended theme

For a detailed explanation of these theme types, see the 
[section on themes in the Screens architecture tutorial](architecture.md#view-layer).
The following sections show you how to create each type of view, using 
`LoginScreenlet` as an example. You'll begin by creating a new Full view.

### Full Theme

A Full theme can present a completely different layout, using different 
components and input data. The example here presents just a single `UITextField` 
for the user name. The [UDID](http://www.idownloadblog.com/2010/12/21/iphone-udid/) 
is used for the password. 

1. Create a new `xib` called `LoginScreenlet_full.xib`. You'll build your new UI 
    here as usual with Interface Builder. A good way to start is to duplicate 
    `LoginScreenlet_default.xib` and use it as a template. Insert the text field 
    for the user name and add a *Sign In* button with the same 
    `restorationIdentifier` as the Default theme's button. 

    ![New xib for full theme](../../images/screens-ios-xcode-full-theme.png)

2. Create a new view class called `LoginScreenletView_full`. As before, you can 
    duplicate the `LoginScreenletView_default` class and use it as a template. 
    In this class, add all `@IBOutlet` properties or `@IBAction` methods you 
    need to bind your UI components. This class must inherit the 
    `BaseScreenletView` class and conform to the `LoginScreenletData` protocol, 
    implementing the corresponding getters and setters. In this case, the 
    `password` getter returns the UDID.

3. Set `LoginScreenletView_full` as the custom class of your 
    `LoginScreenlet_full.xib` file and bind your `@IBOutlet` and `@IBAction` to 
    your class.

4. Install the new theme, insert the `LoginScreenlet` in any of your view 
    controllers, and use the `full` value as the `themeName` screenlet's 
    property. Your new look and feel is shown in Interface Builder.

Super! Now that you know how to create a Full theme, you'll learn to create a 
Child theme.

### Child Theme

A Child theme inherits only the look and feel of its parent. It doesn't inherit 
any code. The example theme here presents the same components as the Default 
theme, but is adjusted to a larger resolution. This makes it appropriate for 
iOS devices such as the iPad, iPhone 6, and iPhone 6 Plus.

1. Create a new `xib` called `LoginScreenlet_large.xib`. You'll build your new 
    UI here as usual with Interface Builder. A good place to start is to 
    duplicate `LoginScreenlet_default.xib` and use it as a template for your new 
    `xib`.

    ![New xib for child theme](../../images/screens-ios-xcode-child-theme.png)

2. Change the position, size, or other properties of the parent components. 
    However, be sure not to change the custom class, outlet connection, or 
    `restorationIdentifier`.

3. Install your new theme, insert the `LoginScreenlet` in any of your view 
    controllers, and use `large` as the value of the screenlet's `themeName` 
    property. Your new look and feel is shown in Interface Builder.
    
Fantastic! Last but certainly not least, you'll learn how to create an Extended 
theme.

### Extended Theme

An Extended theme inherits the functionality and UI of its parent. This lets you
add your own functionality and UI on top of it. For an example of an Extended 
theme, see the [Flat7 theme source code](https://github.com/liferay/liferay-screens/tree/master/ios/Library/Themes/Flat7). 
The Extended theme here presents the same components as the Default theme, but 
sets new translated strings and contains a few new animations. A new UI 
component is also introduced to configure whether or not the password is shown 
when typed by the user.

1. Create a new `xib` called `LoginScreenlet_ext.xib`. You'll build your new UI 
    here as usual with Interface Builder. A good place to start is to duplicate 
    `LoginScreenlet_default.xib` and use it as a template for your new `xib`. 
    Add a new `UISwitch` component to configure the password presentation.

    ![New xib for extended theme](../../images/screens-ios-xcode-ext-theme.png)

2. Create a new view class called `LoginScreenletView_ext` using 
    `LoginScreenletView_default` as the parent class. To set new translations, 
    you can override the `onSetTranslations` method. For other customizations, 
    consider overriding other methods of the parent class, or add new delegates 
    to the class. Add new `@IBOutlet` or `@IBAction` for the new `IBSwitch` 
    component.

3. Set `LoginScreenletView_full` as the custom class of your 
    `LoginScreenlet_ext.xib` file and bind your new `@IBOutlet` and `@IBAction` 
    to your class.

4. Install the new theme, insert `LoginScreenlet` in any of your view 
    controllers, and use `ext` as the value for the screenlet's `themeName` 
    property. Your new look and feel is shown in Interface Builder.
    
Well done! You now know how to create an Extended theme in Screens for iOS. 
You're the master of the themes!

## Related Topics

[Architecture of Liferay Screens](http://www.liferay.com/)

[Using Views and Themes](http://www.liferay.com/)

[Creating Screenlets](http://www.liferay.com/)
