# Liferay Screens for Android Troubleshooting and FAQs [](id=liferay-screens-for-android-troubleshooting-and-faqs)

Even though Liferay developed Screens for Android with great care, you may still
run into some common issues. Here are solutions and tips for solving these
issues. You'll also find answers to common questions about Screens for Android. 

## General Troubleshooting [](id=general-troubleshooting)

Before delving into specific issues, you should first make sure that you have 
the latest tools installed and know where to get additional help if you need it. 
You should use the latest Android API level, with the latest version of Android 
Studio. Although Screens *can* work with Eclipse ADT or manual Gradle builds, 
Android Studio is the preferred IDE. 

If you're having trouble using Liferay Screens, it may help to investigate the 
sample apps developed by Liferay. Both serve as good examples of how to use 
Screenlets and Views:

- [Westeros Bank](https://github.com/liferay/liferay-screens/tree/master/android/samples/bankofwesteros) 
- [Test App](https://github.com/liferay/liferay-screens/tree/master/android/samples/test-app) 

When updating an app or Screenlet to a new version of Liferay Screens, make sure 
to see the 
[Android breaking changes reference article](/develop/reference/-/knowledge_base/7-0/android-breaking-changes). 
This article lists changes to Screens that break functionality in prior 
versions. In most cases, updating your code is relatively straightforward. 

If you get stuck at any point, you can post your question on our 
[forum](https://www.liferay.com/community/forums/-/message_boards/category/42706063).
We're happy to assist you! If you found a bug or want to suggest an improvement, 
file a ticket in our 
[Jira](https://issues.liferay.com/browse/LMW/). 
Note that you must 
[log in](https://issues.liferay.com/login.jsp?os_destination=%2Fbrowse%2F) 
first to be able to see the project.

## Common Issues [](id=troubleshooting)

This section contains information on common issues that can occur when using 
Liferay Screens. 

1.  Could not find `com.liferay.mobile:liferay-screens`

    This error occurs when Gradle isn't able to find Liferay Screens or the 
    repository. First, check that the Screens version number you're trying to 
    use exists in jCenter. You can use this
    [link](https://bintray.com/liferay/liferay-mobile/liferay-screens/view) 
    to see all uploaded versions.

    It's also possible that you're using an old Gradle plugin that doesn't use 
    jCenter as the default repository. Screens uses version 1.2.3 and later. You 
    can add jCenter as a new repository by placing this code in your project's 
    `build.gradle` file: 

        repositories {
            jcenter()
        }

2.  Failed to resolve: `com.android.support:appcompat-v7`

    Liferay Screens uses the appcompat library from Google, as do all new 
    Android projects created with the latest Android Studio. The appcompat 
    library uses a custom repository maintained by Google, so it must be updated 
    manually using the Android SDK Manager. 

    In the Android SDK Manager (located at *Tools* &rarr; *Android* &rarr; *SDK
    Manager* in Android Studio), you must install at least version 14 of the 
    Android Support Repository (in the *Extras* menu), and version 22.1.1 of the
    Android Support Library.

3.  Duplicate files copied in `APK META-INF`...

    This is a common Android error when using libraries. It occurs because 
    Gradle can't merge duplicated files such as license or notice files. To 
    prevent this error, add the following code to your `build.gradle` file:

        android {
            ...
            packagingOptions {
                exclude 'META-INF/LICENSE'
                exclude 'META-INF/NOTICE'
            }
            ...
        }

    This error may not happen right away, but may only appear later on in the
    development process. For this reason, it's recommended that you put the
    above code in your `build.gradle` file after creating your project. 

4.  Connect failed: ECONNREFUSED (Connection refused), or 
   `org.apache.http.conn.HttpHostConnectException`

    This error occurs when Liferay Screens and the underlying Liferay Mobile 
    SDK can't connect to the Liferay Portal instance. If you get this error, 
    you should first check the IP address of the server to make sure it's 
    available. If you've 
    [overridden the default IP address in `server_context.xml`](/develop/tutorials/-/knowledge_base/7-0/preparing-android-projects-for-liferay-screens#configuring-communication-with-liferay), 
    you should check to make sure that you've set it to the correct IP. Also, if 
    you're using the Genymotion emulator, you must use `192.168.56.1` instead of 
    localhost for your app to communicate with a local Liferay instance. 

5.  `java.io.IOException`: open failed: EACCES (Permission denied)

    Some Screenlets use temporary files to store information, such as when the
    User Portrait Screenlet uploads a new portrait, or the DDL Form Screenlet
    uploads new files to the portal. Your app needs to have the necessary
    permissions to use a specific Screenlet's functionality. In this case, add
    the following line to your `AndroidManifest.xml`: 

        <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>

    If you're using the device's camera, you also need to add the following 
    permission:

        <uses-permission android:name="android.permission.CAMERA"/>

6.  No JSON web service action with path ...

    This error most commonly occurs if you haven't installed the 
    [Liferay Screens Compatibility Plugin](https://github.com/liferay/liferay-screens/tree/master/portal). 
    This plugin adds new API calls to Liferay Portal.

## FAQs [](id=faqs)

1.  Do I have to use Android Studio?

    No, Liferay Screens also works with Eclipse ADT. You can also compile your 
    project manually with Gradle or another build system. Just make sure you use 
    the compiled `aar` in your project's `lib` folder.

    We *strongly* recommend, however, that you use Android Studio. Android 
    Studio is the official IDE for developing Android apps, and Eclipse ADT is 
    no longer supported. Using Eclipse ADT or compiling manually may cause 
    unexpected issues that are difficult to overcome. 

2.  How does Screens handle orientation changes?

    Liferay Screens uses an event bus, 
    [the `EventBus` library](http://greenrobot.github.io/EventBus/), 
    to notify activities when the interactor has finished its work. 

3.  How can I use a Liferay feature not available in Screens?

    There are several ways you can use Liferay features not currently available 
    in Screens. The 
    [Liferay Mobile SDK](https://github.com/liferay/liferay-mobile-sdk) 
    gives you access to all of Liferay's remote APIs. You can also create a 
    custom Screenlet to support any features not included in Screens by default. 

4.  How do I create a new Screenlet?

    Screenlet creation is explained in detail 
    [here](/develop/tutorials/-/knowledge_base/7-0/creating-android-screenlets).

5.  How can I customize a Screenlet?

    You can customize Screenlets by creating new Views. Fortunately, there's a 
    [tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-android-views) 
    for this! 

6.  Does Screens have offline support?

    Yes, since Liferay Screens 1.3!

## Related Topics [](id=related-topics)

[Preparing Android Projects for Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/preparing-android-projects-for-liferay-screens)

[Creating Android Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-android-screenlets)

[Creating Android Views](/develop/tutorials/-/knowledge_base/7-0/creating-android-views)

[Mobile SDK](/develop/tutorials/-/knowledge_base/7-0/mobile-sdk)

[Android Breaking Changes](/develop/reference/-/knowledge_base/7-0/android-breaking-changes)
