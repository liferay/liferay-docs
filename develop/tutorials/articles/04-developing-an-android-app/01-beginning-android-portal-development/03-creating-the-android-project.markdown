# Creating the Android Project [](id=creating-the-android-project)

Now that you've built the Guestbook Mobile SDK, you're ready to create the 
Guestbook Android app. This article walks you through the steps required to 
create the app's project in Android Studio. After this, you'll be ready to 
install the Guestbook Mobile SDK and Liferay Screens. First though, you should 
make sure you've installed Android's development tools. 

## Installing Android Studio [](id=installing-android-studio)

This Learning Path uses Android Studio--Android's official IDE--to develop the 
Guestbook app. As an Android developer, you're likely very familiar with Android 
Studio. If you need help setting up and using Android Studio, see the following 
topics in Android Studio's documentation: 

- [Android Studio Installation Instructions](https://developer.android.com/studio/install.html?pkg=studio)
- [Meet Android Studio](https://developer.android.com/studio/intro/index.html)

Once Android Studio is up and running, you're ready to create the Guestbook app! 

## Creating the Guestbook App [](id=creating-the-guestbook-app)

When you start Android Studio, it presents a welcome screen containing a Quick 
Start menu. Click *Start a new Android Studio project* in this menu. This 
launches the *Create New Project* wizard, which asks you to enter the app name, 
company domain, and project location. Enter *Liferay Guestbook* as the app name 
and *docs.liferay.com* as the company domain. Android Studio uses these values 
to autofill your app's package name and project location. Accept the package 
name `com.liferay.docs.liferayguestbook`, and choose a project location that's 
convenient. Click *Next*. 

![Figure 1: The first screen of Android Studio's Create New Project wizard asks you to enter your app's name and company domain.](../../../images/android-studio-new-project-01.png)

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
activity to authenticate users to your @product@ instance, don't select Login 
Activity. Select *Empty Activity* instead. Later, you'll insert 
[Login Screenlet](/develop/reference/-/knowledge_base/7-0/loginscreenlet-for-android) 
in this activity. Login Screenlet contains everything your users need to 
authenticate to a @product@ instance, including the UI. Click *Next*. 

![Figure 3: The third screen of Android Studio's Create New Project wizard lets you specify an activity for your app.](../../../images/android-studio-new-project-03.png)

The final screen of the New Project Wizard asks you to enter the activity's name 
and the name of its layout file. Accept the defaults and click *Finish*. 

![Figure 4: In the final screen of Android Studio's Create New Project wizard, accept the default values for the activity and layout name.](../../../images/android-studio-new-project-04.png)

A progress indicator appears that indicates your project is building. Android 
Studio then opens the project with the activity's class and layout ready to 
edit. The project's structure appears on the left side of the screen. 

![Figure 5: Android Studio shows your project's structure.](../../../images/android-studio-project.png)

Well done! You successfully created the Guestbook app's project. Now it's time 
to put the Guestbook Mobile SDK and Liferay Screens to work! 
