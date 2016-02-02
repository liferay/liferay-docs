# Installing the Guestbook Mobile SDK and Liferay Screens for Android [](id=installing-the-guestbook-mobile-sdk-and-liferay-screens-for-android)

You must install the Guestbook Mobile SDK in your Android app so it can interact 
with the Guestbook portlet. Note that this Mobile SDK contains only the classes 
and methods required to call the portlet's remote services. To use Screenlets,
you also need to install Liferay Screens. What's nice is that installing Screens
also installs the related Liferay Mobile SDK, so you can do it all in one shot. 

Next you'll learn how to install the Guestbook Mobile SDK and Liferay Screens.
When you finish, you'll be ready to start developing the app. 

## Installing the Guestbook Mobile SDK [](id=installing-the-guestbook-mobile-sdk)

The Mobile SDK Builder generated two separate JAR files in your
`liferay-mobile-sdk-android-6.2.0.22` folder: 

1. `modules/guestbook-portlet/build/libs/liferay-guestbook-portlet-android-sdk-1.0.jar`

2. `android/build/libs/liferay-android-sdk-6.2.0.22.jar`

The first JAR file contains the Guestbook Mobile SDK. The second JAR file 
contains the rest of the Liferay Mobile SDK. Because Screens also includes the 
rest of the Liferay Mobile SDK, you don't need to install the second JAR file. 
You do, however, need to install the first JAR file. To do so, copy
`liferay-guestbook-portlet-android-sdk-1.0.jar` into your app's `app/libs`
folder (the default location for your Android app's directory is
`AndroidStudioProjects/LiferayGuestbook`). That's it! Next, you'll install 
Liferay Screens. 

## Installing Liferay Screens [](id=installing-liferay-screens)

To install Liferay Screens (which also includes the rest of the Liferay Mobile 
SDK), you must edit your app's `build.gradle` file. Note that your project has
two `build.gradle` files: one for the project, and another for the app module.
You can find them under *Gradle Scripts* in Android Studio's project view. The
app module's `build.gradle` file is highlighted in this screenshot: 

![Figure 1: The app module's `build.gradle` file.](../../images/android-build-gradle-app-module.png)

In the app module's `build.gradle` file, add these lines just after the closing `buildTypes` brace: 

    packagingOptions {
      exclude 'META-INF/LICENSE'
      exclude 'META-INF/NOTICE'
    }

Next, add the following code on the first level (on the same level as the
`android` element): 

    repositories {
      jcenter()
    }

Finally, add the following line of code inside the `dependencies` element: 

    compile 'com.liferay.mobile:liferay-screens:1.2.4'

If you're unsure of where to add the above lines, please see the figure below. 

Once you edit `build.gradle`, a message appears at the top of the file that asks 
you to *sync* your app with its Gradle files. Syncing with the Gradle files is 
required to incorporate any changes you make to them. Syncing also downloads and 
installs any new dependencies, like the Liferay Screens dependency that you just
added. Sync the Gradle files now by clicking the *Sync Now* link in the message.
The following screenshot shows the edited `build.gradle` file with the Sync Now
link highlighted in a red box: 

![Figure 2: After editing the `build.gradle` file, click *Sync Now* to incorporate the changes in your app.](../../images/android-build-gradle-sync.png)
 
Great! Now you're ready to test your Screens and Mobile SDK installations. 

## Verifying the Installations [](id=verifying-the-installations)

To check your Screens and Mobile SDK installations, first open your project's 
`MainActivity` class in Android Studio. It's in the `java` folder's
`com.liferay.docs.liferayguestbook` package, in the project view. Add the
following imports at the top of the file:

    import com.liferay.mobile.android.service.Session;
    import com.liferay.mobile.android.v62.entry.EntryService;
    import com.liferay.mobile.android.v62.guestbook.GuestbookService;
    import com.liferay.mobile.screens.auth.login.LoginScreenlet;

If Android Studio recognizes these imports, then you're good to go! Remove them 
once you've verified that they're recognized. Next, there's one final small but 
important detail to complete: pointing your app atthe correct Liferay 
installation. 

## Configuring Communication with Liferay [](id=configuring-communication-with-liferay)

For Screens to work properly with your app, you must point it to your Liferay 
Portal installation. You do this by adding a `server_context.xml` file in your 
project's `res/values` directory. Create this file and add the following code 
to it:

    <?xml version="1.0" encoding="utf-8"?>
    <resources>

        <!-- Change these values for your Liferay Portal installation -->

        <string name="liferay_server">http://10.0.2.2:8080</string>

        <integer name="liferay_company_id">10155</integer>
        <integer name="liferay_group_id">10182</integer>

    </resources>

As the comment indicates, change the values to match those of your portal 
installation. The server address `http://10.0.2.2:8080` is suitable for testing 
with Android Studio's emulator, because it corresponds to `localhost:8080` 
through the emulator. The portal you set up earlier should be running on 
`localhost:8080`.

The `liferay_company_id` value is your portal instance's ID. You can find it in 
the *Control Panel* &rarr; *Configuration* &rarr; *Portal Instances* menu. Your 
portal's instance ID is in the *Instance ID* column. Copy and paste this value 
from your portal to the `liferay_company_id` value in `server_context.xml`.

The `liferay_group_id` value is the ID of the site your app needs to communicate 
with. Since the app needs to communicate with the Guestbook portlet, navigate to 
the site you put the Guestbook portlet on. Then select 
*Admin* &rarr; *Site Administration* &rarr; *Configuration* from the Dockbar. 
The site ID is listed on the *Site Settings* tab. Copy and paste this value 
from your portal to the `liferay_group_id` value in `server_context.xml`.

Awesome! Next, you'll learn the app's basic design. 
