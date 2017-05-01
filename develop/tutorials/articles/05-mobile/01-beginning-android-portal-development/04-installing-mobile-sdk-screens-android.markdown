# Installing the Guestbook Mobile SDK and Liferay Screens for Android [](id=installing-the-guestbook-mobile-sdk-and-liferay-screens-for-android)

For your Android app to interact with the Guestbook portlet, you must install 
the following in your Android project:

- **Liferay's pre-built Mobile SDK:** This Mobile SDK contains the classes that 
  call the remote services of Liferay Portal's core apps. It also contains the 
  framework necessary for any Mobile SDK to make server calls. 

- **The Guestbook Mobile SDK:** This Mobile SDK contains only the classes that 
  call the Guestbook portlet's remote services. 

- **Liferay Screens:** Screens contains the Screenlet framework and 
  [several built-in Screenlets](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-android) 
  like Login Screenlet. Because these built-in Screenlets interact with Liferay
  Portal's core apps, they make their server calls with Liferay's pre-built
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
`liferay-mobile-sdk-builder-6.2.0.18` folder: 

1. `modules/guestbook-portlet/build/libs/liferay-guestbook-portlet-android-sdk-1.0.jar`

2. `android/build/libs/liferay-android-sdk-2.0.3.jar`

The first JAR file is the Guestbook Mobile SDK. The second JAR file is a custom 
built version of Liferay's pre-built Mobile SDK. Because Screens includes the 
pre-built Mobile SDK, you don't need to install the second JAR file. You must, 
however, install the first JAR file. To do so, copy 
`liferay-guestbook-portlet-android-sdk-1.0.jar` into your app's `app/libs` 
folder (the default location for your Android app is 
`AndroidStudioProjects/LiferayGuestbook`). That's it! Next, you'll install 
Liferay Screens. 

## Installing Liferay Screens [](id=installing-liferay-screens)

To install Liferay Screens, you must edit your app's `build.gradle` file. Note 
that your project has two `build.gradle` files: one for the project, and another 
for the app module. You can find them under *Gradle Scripts* in your Android 
Studio project. This screenshot highlights the app module's `build.gradle` file: 

![Figure 1: The app module's `build.gradle` file.](../../../images/android-build-gradle-app-module.png)

In the app module's `build.gradle` file, add the following lines of code inside 
the `dependencies` element: 

    compile 'com.liferay.mobile:liferay-screens:2.1.1'
    compile 'com.liferay.mobile:liferay-material-viewset:2.1.1'

This adds the `liferay-screens` and `liferay-material-viewset` dependencies. 
Although only the `liferay-screens` dependency is necessary to install Screens, 
adding other View Sets, like the Material View Set, gives you flexibility when 
designing your app's look and feel. 
[Click here](/develop/tutorials/-/knowledge_base/7-0/using-views-in-android-screenlets) 
for more information on Views in Liferay Screens. 

Once you edit `build.gradle`, a message appears at the top of the file that asks 
you to sync your app with its Gradle files. Syncing with the Gradle files is 
required to incorporate any changes you make to them. Syncing also downloads and 
installs any new dependencies, like those you just added. Sync the Gradle files 
now by clicking the *Sync Now* link in the message. 

Note that after syncing, your `build.gradle` may show an error similar to this: 

    All com.android.support libraries must use the exact same version specification...

If this occurs, you must manually add the correct version of the 
`com.android.support` dependencies. For example, the app in this Learning Path 
currently uses version `25.3.1` of the `com.android.support` libraries. This 
requires that you manually add the following dependencies to the app's 
`build.gradle`: 

    compile 'com.android.support:support-v4:25.3.1'
    compile 'com.android.support:recyclerview-v7:25.3.1'
    compile 'com.android.support:transition:25.3.1'
    compile 'com.android.support:design:25.3.1'
    compile 'com.android.support:exifinterface:25.3.1'

After adding these inside the `dependencies` element, click *Sync Now* again. 
The error message should be gone once the sync completes.

![Figure 2: After editing the app module's `build.gradle` file, click *Sync Now* to incorporate the changes in your app.](../../../images/android-build-gradle-sync.png)

Great! Now you're ready to test your Screens and Mobile SDK installations. 

## Verifying the Installations [](id=verifying-the-installations)

To check your Screens and Mobile SDK installations, first open your project's 
`MainActivity` class in Android Studio. It's in the `java` folder's
`com.liferay.docs.liferayguestbook` package. Then add the following imports to 
this file: 

    import com.liferay.mobile.android.service.Session;
    import com.liferay.mobile.android.v62.entry.EntryService;
    import com.liferay.mobile.android.v62.guestbook.GuestbookService;
    import com.liferay.mobile.screens.auth.login.LoginScreenlet;

If Android Studio recognizes these imports, then you're good to go! Remove them 
once you've verified that they're valid. Next, there's one final small but 
important task to complete: point your app at the correct Liferay Portal 
instance. 

## Configuring Communication with Liferay Portal [](id=configuring-communication-with-liferay)

For Screens to work properly with your app, you must point it to your Liferay
Portal instance. You do this by adding a `server_context.xml` file in your
project's `res/values` directory. Create this file and add the following code
to it: 

    <?xml version="1.0" encoding="utf-8"?>
    <resources>

        <!-- Change these values for your portal instance -->

        <string name="liferay_server">http://10.0.2.2:8080</string>

        <integer name="liferay_company_id">20155</integer>
        <integer name="liferay_group_id">20182</integer>

        <integer name="liferay_portal_version">62</integer>

    </resources>

As the comment indicates, change the values to match those of your Liferay
Portal instance. The server address `http://10.0.2.2:8080` is suitable for
testing with Android Studio's emulator, because it corresponds to 
`localhost:8080` through the emulator. The Liferay Portal instance you set up 
earlier should be running on `localhost:8080`. 

The `liferay_company_id` value is your Liferay Portal instance's ID. You can 
find it in the *Control Panel* &rarr; *Configuration* &rarr; *Portal Instances* 
menu. Your portal instance's ID is in the *Instance ID* column. Copy and paste 
this value from your portal to the `liferay_company_id` value in 
`server_context.xml`. 

The `liferay_group_id` value is the ID of the site your app needs to communicate 
with. Since the app needs to communicate with the Guestbook portlet, navigate to 
the site you put the Guestbook portlet on. Then select 
*Admin* &rarr; *Site Administration* &rarr; *Configuration* from the Dockbar. 
The site ID is listed on the *Site Settings* tab. Copy and paste this value 
from your portal to the `liferay_group_id` value in `server_context.xml`. 

Awesome! Next, you'll learn the app's basic design. 
