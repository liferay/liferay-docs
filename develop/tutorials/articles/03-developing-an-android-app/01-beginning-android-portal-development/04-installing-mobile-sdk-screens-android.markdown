# Installing the Guestbook Mobile SDK and Liferay Screens for Android [](id=installing-the-guestbook-mobile-sdk-and-liferay-screens-for-android)

For your Android app to interact with the Guestbook portlet, you must install 
the following in your Android project:

- **Liferay's pre-built Mobile SDK:** This Mobile SDK contains the classes that 
  call the remote services of @product@'s core apps. It also contains the 
  framework necessary for any Mobile SDK to make server calls. 

- **The Guestbook Mobile SDK:** This Mobile SDK contains only the classes that 
  call the Guestbook portlet's remote services. 

- **Liferay Screens:** Screens contains the Screenlet framework and 
  [several built-in Screenlets](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-android) 
  like Login Screenlet. Because these built-in Screenlets interact with 
  @product@'s core apps, they make their server calls with Liferay's pre-built 
  Mobile SDK. Note that all Screenlets, including those that make server calls 
  with a custom built Mobile SDK, must use the framework in Liferay's pre-built 
  Mobile SDK to issue their calls. 

Since Liferay's pre-built Mobile SDK is a dependency of Liferay Screens, 
installing Screens automatically installs this Mobile SDK. You must, however, 
install the Guestbook Mobile SDK manually. 

This article walks you through the installation of the Guestbook Mobile SDK and 
Liferay Screens. When you finish, you'll be ready to start developing the app. 

## Installing the Guestbook Mobile SDK [](id=installing-the-guestbook-mobile-sdk)

The Mobile SDK Builder generated two separate JAR files in your
`liferay-mobile-sdk-android-7.0.4` folder: 

1. `modules/gb/build/libs/liferay-gb-android-sdk-1.0.jar`

2. `android/build/libs/liferay-android-sdk-7.0.4.jar`

The first JAR file is the Guestbook Mobile SDK. The second JAR file is a custom 
built version of Liferay's pre-built Mobile SDK. Because Screens includes the 
pre-built Mobile SDK, you don't need to install the second JAR file. You must, 
however, install the first JAR file. To do so, copy 
`liferay-gb-android-sdk-1.0.jar` into your app's `app/libs` folder (the default 
location for your Android app is 
`AndroidStudioProjects/LiferayGuestbook`). That's it! Next, you'll install 
Liferay Screens. 

## Installing Liferay Screens [](id=installing-liferay-screens)

To install Liferay Screens, you must edit your app's `build.gradle` file. Note 
that your project has two `build.gradle` files: one for the project, and another 
for the app module. You can find them under *Gradle Scripts* in your Android 
Studio project. This screenshot highlights the app module's `build.gradle` file: 

![Figure 1: The app module's `build.gradle` file.](../../../images/android-build-gradle-app-module.png)

In the app module's `build.gradle` file, add the following line of code inside 
the `dependencies` element: 

    compile 'com.liferay.mobile:liferay-screens:2.1.0'

Once you edit `build.gradle`, a message appears at the top of the file that asks 
you to sync your app with its Gradle files. Syncing with the Gradle files is 
required to incorporate any changes you make to them. Syncing also downloads and 
installs any new dependencies, like the Liferay Screens dependency that you just
added. Sync the Gradle files now by clicking the *Sync Now* link in the message.
The following screenshot shows the edited `build.gradle` file with the Sync Now
link highlighted by a red box: 

![Figure 2: After editing the app module's `build.gradle` file, click *Sync Now* to incorporate the changes in your app.](../../../images/android-build-gradle-sync.png)

Great! Now you're ready to test your Screens and Mobile SDK installations. 

## Verifying the Installations [](id=verifying-the-installations)

To check your Screens and Mobile SDK installations, first open your project's 
`MainActivity` class in Android Studio. It's in the `java` folder's
`com.liferay.docs.liferayguestbook` package. Then add the following imports at 
the top of the file: 

    import com.liferay.mobile.android.service.Session;
    import com.liferay.mobile.android.v7.entry.EntryService;
    import com.liferay.mobile.android.v7.guestbook.GuestbookService;
    import com.liferay.mobile.screens.auth.login.LoginScreenlet;

If Android Studio recognizes these imports, then you're good to go! Remove them 
once you've verified that they're valid. Next, there's one final small but 
important task to complete: point your app at the correct @product@ instance. 

## Configuring Communication with @product@ [](id=configuring-communication-with-liferay)

For Screens to work properly with your app, you must point it to your @product@ 
instance. You do this by adding a `server_context.xml` file in your project's 
`res/values` directory. Create this file and add the following code to it: 

    <?xml version="1.0" encoding="utf-8"?>
    <resources>

        <!-- Change these values for your @product@ instance -->

        <string name="liferay_server">http://10.0.2.2:8080</string>

        <integer name="liferay_company_id">20116</integer>
        <integer name="liferay_group_id">20147</integer>

        <integer name="liferay_portal_version">70</integer>

    </resources>

As the comment indicates, change the values to match those of your @product@ 
instance. The server address `http://10.0.2.2:8080` is suitable for testing 
with Android Studio's emulator, because it corresponds to `localhost:8080` 
through the emulator. The @product@ instance you set up earlier should be 
running on `localhost:8080`. 

The `liferay_company_id` value is your @product@ instance's ID. You can find it 
in your @product@ instance at *Control Panel* &rarr; *Configuration* &rarr; 
*Virtual Instances*. The instance's ID is in the *Instance ID* column. Copy and 
paste this value into the `liferay_company_id` value in `server_context.xml`. 

The `liferay_group_id` value is the ID of the site your app needs to communicate 
with. Since the app needs to communicate with the Guestbook portlet, navigate to 
the site you put the Guestbook portlet on. In the *Site Administration* menu, 
select *Configuration* &rarr; *Site Settings*. The site ID is listed at the top 
of the *General* tab. Copy and paste this value into the `liferay_group_id` 
value in `server_context.xml`. 

Awesome! Next, you'll learn the app's basic design. 
