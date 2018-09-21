# Extended Views [](id=extended-views)

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
