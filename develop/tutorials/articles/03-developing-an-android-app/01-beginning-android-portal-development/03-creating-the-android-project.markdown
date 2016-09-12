# Creating the Android Project [](id=creating-the-android-project)

Now that you've built the Guestbook Mobile SDK, you're ready to create the 
Guestbook Android app. This article walks you through the steps required to 
create the app's project in Android Studio. After this, you'll be ready to 
install the Guestbook Mobile SDK and Liferay Screens. First though, you should 
make sure you've installed Android's development tools. 

## Installing Android Studio [](id=installing-android-studio)

This Learning Path uses Android Studio--Android's official IDE--to develop the 
Guestbook app. As an Android developer, you're likely very familiar with Android 
Studio. The following links to Android Studio's documentation are provided as a 
quick reference in case you need additional information about setting up and 
using Android Studio: 

- [Android Studio Installation Instructions](https://developer.android.com/studio/install.html?pkg=studio)
- [Meet Android Studio](https://developer.android.com/studio/intro/index.html)

Once Android Studio is up and running, you're ready to create the Guestbook app! 

## Creating the Guestbook App [](id=creating-the-guestbook-app)

When you start Android Studio, a welcome screen with a Quick Start menu appears.
Click on *Start a new Android Studio project* in the Quick Start menu. Android
Studio's Create New Project wizard launches and asks you to fill in the
Application name, Company Domain, and Project location. Enter *Liferay
Guestbook* as the Application name and *docs.liferay.com* as the Company Domain. 
Android Studio uses these fields to fill in your app's *Package name* and 
*Project location* automatically. For location, choose a location on your system 
that's convenient for you. Click *Next*. 

![Figure 1: The first screen of Android Studio's Create New Project wizard asks you to enter your app's name and Company Domain.](../../../images/android-studio-new-project-01.png)

The next screen asks you to specify your app's supported form factors and
minimum Android SDK. Make sure that only the *Phone and Tablet* checkbox is
selected. In the *Minimum SDK* menu, select *API 15: Android 4.0.3
(IceCreamSandwich)*. Android Studio gives you an estimate of the percentage of 
devices active on the Google Play store that can run the selected API level. You 
can view a graphical representation of these estimates by clicking the *Help me 
choose* link in the text below the Minimum SDK menu. Click *Next* when you're 
finished. 

![Figure 2: The second screen of Android Studio's Create New Project wizard lets you select your app's form factors and minimum Android API level.](../../../images/android-studio-new-project-02.png)

You must now specify your app's first activity. Although you'll use this 
activity to authenticate users to your Liferay instance, don't select Login 
Activity in the wizard. Select *Empty Activity* instead. Later, you'll insert 
[Login Screenlet](/develop/reference/-/knowledge_base/6-2/loginscreenlet-for-android) 
in this activity. Login Screenlet contains everything your users need to 
authenticate to a Liferay instance, including the UI. Click *Next*. 

![Figure 3: The third screen of Android Studio's Create New Project wizard lets you specify an activity for your app.](../../../images/android-studio-new-project-03.png)

The final screen of the New Project Wizard asks you to enter the activity's name 
and the name of its layout file. Accept the defaults and click *Finish*.

![Figure 4: The final screen of Android Studio's Create New Project wizard asks you to name your activity and its layout file.](../../../images/android-studio-new-project-04.png)

A progress indicator appears that indicates your project is building. Android 
Studio then opens the project with the activity's class and layout ready to 
edit. The project's structure appears on the left side of the screen. 

![Figure 5: Android Studio shows your projects structure.](../../../images/android-studio-project.png)

Well done! You've successfully created the initial Guestbook app. Now it's time 
to put the Guestbook Mobile SDK and Liferay Screens to work! 
