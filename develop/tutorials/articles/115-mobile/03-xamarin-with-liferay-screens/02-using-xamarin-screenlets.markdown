# Using Screenlets in Xamarin Apps [](id=using-screenlets-in-xamarin-apps)

You can start using Screenlets once you've  [prepared](/develop/tutorials/-/knowledge_base/7-0/preparing-xamarin-projects-for-liferay-screens) 
your Xamarin project to use Liferay Screens. The Screenlet reference 
documentation describes the available Screenlets: 

-   [Screenlets in Liferay Screens for Android](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-android)
-   [Screenlets in Liferay Screens for iOS](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-ios)

Using Screenlets is very straightforward. This tutorial shows you how to insert 
and configure Screenlets in your Xamarin app. You'll be a Screenlet master in no 
time! 

## Xamarin.iOS [](id=xamarin-ios)

Follow these steps to insert Screenlets in your Xamarin.iOS app:

1.  Insert a view (`UIView`) in your storyboard (in Visual Studio's iOS Designer 
    or Xcode's Interface Builder). Note that if you're editing an XIB file, you 
    must insert the view inside the XIB's parent view. 

2.  Set the view's class to the class of the Screenlet you want to use. For 
    example, Login Screenlet's class is `LoginScreenlet`. If you're using 
    Xamarin Designer for iOS in Visual Studio, you must also give the view a 
    name so you can refer to it in your view controller's code. 

    For example, the following video shows the first two steps for inserting 
    Login Screenlet in a Xamarin Designer for iOS storyboard. 


    <iframe width="560" height="315" src="https://www.youtube.com/embed/y95XwdpCZVQ" frameborder="0" allowfullscreen></iframe>

3.  Configure the Screenlet's behavior in your app by implementing the 
    Screenlet's delegate in your view controller. To configure your app to 
    listen for events the Screenlet triggers, implement the Screenlet's delegate 
    methods and register the view controller as the delegate. Make sure to 
    annotate each delegate method with `[Export(...)]`. This ensures the method 
    can be called from Objective-C, which is required for it to work in Screens. 
    You should also set any Screenlet attributes you need. Each Liferay 
    Screenlet's 
    [reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-ios) 
    lists its available attributes and delegate methods. 

    +$$$

    **Note:** In Liferay Screens for Xamarin, Screenlet delegates are prefixed 
    with an `I`. For example, Login Screenlet's delegate in native code is 
    `LoginScreenletDelegate`, while in Xamarin it's `ILoginScreenletDelegate`. 

    $$$

    For example, here's a view controller that implements Login Screenlet's 
    delegate, `ILoginScreenletDelegate`. Note that the `ViewDidLoad()` method 
    sets the Screenlet's `ThemeName` attribute (`ThemeName` is available for all 
    Screenlets via `BaseScreenlet` inheritance) and registers the view 
    controller as the delegate. This view controller also implements the 
    `OnLoginResponseUserAttributes` method, which is called upon successful 
    login. Also note this method's `[Export(...)]` annotation: 

        public partial class ViewController : UIViewController, ILoginScreenletDelegate
        {
            protected ViewController(IntPtr handle) : base(handle) {}

            public override void ViewDidLoad()
            {
                base.ViewDidLoad();

                // Set the Screenlet's attributes
                this.loginScreenlet.ThemeName = "demo";

                // Registers this view controller as the delegate 
                this.loginScreenlet.Delegate = this;
            }

            ...

            // Delegate methods

            [Export("screenlet:onLoginResponseUserAttributes:")]
            public virtual void OnLoginResponseUserAttributes(BaseScreenlet screenlet, 
                NSDictionary<NSString, NSObject> attributes)
            {
                ...
            }
        }

See 
[the Showcase-iOS app](https://github.com/liferay/liferay-screens/tree/develop/xamarin/Samples/Showcase-iOS/ViewController)
for more examples of view controllers that use Liferay's Screenlets. 

## Xamarin.Android [](id=xamarin-android)

Follow these steps to insert Screenlets in your Xamarin.Android app: 

1.  Open your app's layout AXML file and insert the Screenlet's XML in your 
    activity or fragment layout. For example, here's Login Screenlet's XML in an 
    activity's `FrameLayout`: 

        <?xml version="1.0" encoding="utf-8"?>
        <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:app="http://schemas.android.com/apk/res-auto"
            android:orientation="vertical"
            android:layout_width="match_parent"
            android:layout_height="match_parent">
            <com.liferay.mobile.screens.auth.login.LoginScreenlet
                android:id="@+id/login_screenlet"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                app:basicAuthMethod="email"/>
        </FrameLayout>

2.  Set the Screenlet's attributes. If it's a Liferay Screenlet, refer to the 
    [Screenlet reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-android) 
    to learn the Screenlet's required and supported attributes. This screenshot 
    shows Login Screenlet's attributes being set: 

    ![Figure 1: You can set a Screenlet's attributes via the app's layout AXML file.](../../../images/screens-xamarin-android-screenlet-attributes.png)

3.  To configure your app to listen for events the Screenlet triggers, implement 
    the Screenlet's listener interface in your activity or fragment class. Refer 
    to the 
    [Screenlet's reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-android) 
    to learn its listener interface. Then register that activity or fragment as 
    the Screenlet's listener. 

    +$$$

    **Note:** In Liferay Screens for Xamarin, Screenlet listeners are prefixed 
    with an `I`. For example, Login Screenlet's listener in native code is 
    `LoginListener`, while in Xamarin it's `ILoginListener`. 

    $$$

    For example, the following activity class implements Login Screenlet's 
    `ILoginListener` interface, and registers itself as the Screenlet's 
    listener via `loginScreenlet.Listener = this`. Note that the listener
    methods `OnLoginSuccess` and `OnLoginFailure` are called when login succeeds 
    and fails, respectively. In this case, these methods print simple toast 
    messages: 

        [Activity]
        public class LoginActivity : Activity, ILoginListener
        {
            LoginScreenlet loginScreenlet;

            protected override void OnCreate(Bundle savedInstanceState)
            {
                base.OnCreate(savedInstanceState);
                SetContentView(Resource.Layout.LoginView);

                loginScreenlet = (LoginScreenlet) FindViewById(Resource.Id.login_screenlet);
                loginScreenlet.Listener = this;
            }

            // ILoginListener

            public void OnLoginSuccess(User p0)
            {
                Toast.MakeText(this, "Login success: " + p0.Id, ToastLength.Short).Show();
            }

            public void OnLoginFailure(Java.Lang.Exception p0)
            {
                Android.Util.Log.Debug("LoginScreenlet", $"Login failed: {p0.Message}");
            }

        }

See 
[the Showcase-Android app](https://github.com/liferay/liferay-screens/tree/develop/xamarin/Samples/Showcase-Android/Activities)
for more examples of activities that use Liferay's Screenlets. 

## Related Topics [](id=related-topics)

[Preparing Xamarin Projects for Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/preparing-xamarin-projects-for-liferay-screens)

[Using Views in Xamarin.Android](/develop/tutorials/-/knowledge_base/7-0/using-views-in-xamarin-android)

[Using Themes in Xamarin.iOS](/develop/tutorials/-/knowledge_base/7-0/using-themes-in-xamarin-ios)

[Creating Xamarin Views and Themes](/develop/tutorials/-/knowledge_base/7-0/creating-xamarin-views-and-themes)

[Liferay Screens for Xamarin Troubleshooting and FAQs](/develop/tutorials/-/knowledge_base/7-0/liferay-screens-for-xamarin-troubleshooting-and-faqs)
