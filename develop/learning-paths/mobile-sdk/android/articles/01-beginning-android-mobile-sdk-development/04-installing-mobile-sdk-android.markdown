# Installing the Liferay Mobile SDK for Android

You must install the Liferay Mobile SDK in your Android app so that your app can
interact with the Guestbook portlet. The Mobile SDK you built for the Guestbook
portlet contains only the classes and methods required to call the portlet's
remote services. You also need to install the Mobile SDK that contains the
classes and methods required to call Liferay's built-in remote services. This
article shows you how to do both. When you finish, you'll be ready to retrieve
guestbooks and their entries from the portal.

## Installing the Guestbook Mobile SDK

The Mobile SDK Builder generated two separate JAR files in your
`liferay-mobile-sdk-android-6.2.0.15` directory: 

1. `modules/guestbook-portlet/build/libs/liferay-guestbook-portlet-android-sdk-1.0.jar`

2. `android/build/libs/liferay-android-sdk-6.2.0.15.jar`

The first JAR file contains the classes and methods for calling the Guestbook
portlet's remote services. The second contains the rest of the Mobile SDK. You
don't actually need to manually install the second JAR file. You can instead add
the Mobile SDK as a dependency in your project's build system, which then
downloads and installs the Mobile SDK for you. However, you need to install the
first JAR file manually. To do so, copy the
`liferay-guestbook-portlet-android-sdk-1.0.jar` file into your app's `app/libs`
directory. (The default location for your Android app's directory is
`AndroidStudioProjects/LiferayGuestbook`.) That's it! Next, you'll install the
rest of the Mobile SDK. 

## Installing the Liferay Mobile SDK

To add the rest of the Mobile SDK, you need to edit your app's `build.gradle` 
file. Gradle is the build system used by Android Studio. Note that your project 
has two `build.gradle` files: one for the project, and another for the app 
module. You can find them under *Gradle Scripts* in Android Studio's project 
view. The app module's `build.gradle` file is highlighted in this screenshot: 

![Figure 1: The app module's `build.gradle` file.](../../images/android-build-gradle-app-module.png)

In the app module's `build.gradle` file, add the following code on the first 
level (on the same level as the `android` element): 

    repositories {
      jcenter()
      mavenCentral()
    }

Next, add the following line of code inside the `dependencies` element: 

    compile group: 'com.liferay.mobile', name: 'liferay-android-sdk', version: '6.2.0.15'

Once you edit `build.gradle`, a message appears at the top of the file that asks 
you to *sync* your app with its Gradle files. Syncing with the Gradle files is 
required to incorporate any changes you make to them. Syncing also downloads and 
installs any new dependencies, like the Mobile SDK dependency that you just
added. Sync the Gradle files now by clicking the *Sync Now* link in the message.
The following screenshot shows the edited `build.gradle` file, with the Sync Now
link highlighted in a red box: 

![Figure 2: After editing the `build.gradle` file, click *Sync Now* to incorporate the changes in your app.](../../images/android-build-gradle-sync.png)

If you get errors such as `Duplicate files copied in APK META-INF/NOTICE` during 
this process, then you need to add the following code to the same `build.gradle` 
file. You should place it inside the `android` element, after the `buildTypes` 
element. After adding this code, click the *Sync Now* link when prompted:

    packagingOptions {
      exclude 'META-INF/LICENSE'
      exclude 'META-INF/NOTICE'
    }

Great! Now you're ready to test your Mobile SDK installation. 

## Verifying the Mobile SDK Installation

To check your Mobile SDK installation, first open your project's `MainActivity` 
class in Android Studio. It's located in the `java` folder's 
`com.liferay.docs.liferayguestbook` package, in the project view. Add the 
following imports at the top of the file:

    import com.liferay.mobile.android.service.Session;
    import com.liferay.mobile.android.v62.entry.EntryService;
    import com.liferay.mobile.android.v62.guestbook.GuestbookService;

If Android Studio recognizes these imports, then you installed the Mobile SDK
correctly. Congratulations! You now have a functional Liferay Mobile SDK in the
Guestbook app! Now you can put it to use to retrieve the guestbooks and their
entries from the Guestbook portlet. The next series of articles in this learning
path explains how to accomplish this. 
