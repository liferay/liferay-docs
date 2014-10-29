# Creating the Liferay Android Sample Project [](id=creating-the-liferay-android-sample-project)

The Mobile SDK Eclipse plugin offers the ability to create a new sample Android
project from scratch. The sample project includes Liferay Mobile SDK libraries
and sample app code. This project is called the *Liferay Android Sample Project*. 

This tutorial walks you through the creation of the *Liferay Android Sample
Project*. When you're finished, you'll have a simple but functional Android app
that you can refer to in your future projects. Alternatively, you can browse or
even download the *Liferay Android Sample Project* source code in
[GitHub](https://github.com/brunofarache/liferay-android-sdk-sample). But,
creating the app is fun and is the quickest way to get it into your environment.
Go ahead and get started! 

## Creating the Project [](id=creating-the-project)

1. First, go to *File* &rarr; *New* &rarr; *Project...*. Then select 
   *Liferay* &rarr; *Liferay Android Sample Project* in the window that appears. 
   Click *Next*.
   
    ![Figure 1: Creating the Liferay Android Sample Project.](../../images/liferay-android-sample-project.png)

2. Specify the *Project name*, *Location*, and *Target SDK*. Notice that you
   also have the option to choose a sample project template from the Mobile SDK
   preferences menu.

3. Click *Finish* to create the sample Android app.

+$$$

**Note:** If you get a console error 
  that says `Unable to resolve target android-18`, or some other number, then 
  you need to set your Android project's build target. Right click your project 
  and select *Properties*. Next, click *Android* in the left side of the window 
  that appears. Then check the box for your version of the Android SDK under 
  *Project Build Target*. 

+$$$

The sample Android app has the standard Liferay Mobile SDK JARs as well as
sample classes that characterize how the app integrates with your portal
instance. 

<!-- You can learn more about the Android application standard architecture
by visiting Android's *Getting Started* guide. It demonstrate standard
architecture as well as activity classes, model classes, etc. 

Removing this content as it's not necessary for the developer to continue with
the tutorial. Jim
-->

The Liferay Android Sample Project lists the portal's users as contacts, so that
you can view their detailed information, including their names, email addresses,
phone numbers, and birth dates. 

Just as evidence that this sample project is using the Liferay Mobile SDK
libraries, open the sample app's `MainActivity` class from the
`src/com/liferay/mobile/sample/activity` directory. In Eclipse, scroll your
mouse over the `Session` object instance within the `onListItemClick()` method. 

![Figure 2: The `Session` interface is referenced from the sample app's Liferay Mobile SDK libraries.](../../images/sample-mobile-sdk-object.png)

The `Session` interface is referenced from the
`com.liferay.mobile.android.service` package. This app uses the Liferay Mobile
SDK from the library JARs in this project.

## Running the App [](id=running-the-app)

You're now ready to run the sample app in Android's [emulator](http://developer.android.com/tools/devices/emulator.html).
To do so, you'll need to have an Android Virtual Device (AVD) set up in Eclipse. 
You can set one up before running the app, or at runtime. Instructions for 
setting up an AVD can be found in Android's [developer documentation](http://developer.android.com/tools/devices/managing-avds.html).

Now that you have an AVD set up, you can run the app!

1. Start a local portal instance on port `8080`, if one is not already running. 

2. In Eclipse, go to *Run* &rarr; *Run As* &rarr; *Android Application*. 

3. Navigate to the *Liferay Contacts* app in the emulator if it doesn't come up 
   automatically.
   
4. To go to the app's *Settings*, click the vertical bar in the upper right 
   corner of the emulator. Enter your login credentials for your portal. When 
   you go back to the app, your fellow Liferay site members should appear.
   
The *Liferay Contacts* app lists your site's users. You can select users' 
names to see their information. 

![Figure 3: The sample project lets you view your local Liferay instance's user information.](../../images/liferay-android-contacts.png)

Congratulations on creating a mobile app that uses Liferay! 

## Related Topics [](id=related-topics)

[Invoking Liferay Services in Your Android App](/develop/tutorials/-/knowledge_base/6-2/invoking-liferay-services-in-your-android-app)

[Creating iOS Apps that Use Liferay](/develop/tutorials/-/knowledge_base/6-2/creating-ios-apps-that-use-liferay)

[Liferay Mobile SDK Builder](/develop/tutorials/-/knowledge_base/6-2/liferay-mobile-sdk-builder)

[Service Builder and Services](/develop/tutorials/-/knowledge_base/6-2/service-builder)

[Developing Plugins with Liferay IDE](/develop/tutorials/-/knowledge_base/6-2/liferay-ide)
