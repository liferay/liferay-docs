## Creating the Liferay Android Sample Project 

The Mobile SDK Eclipse plugin offers the ability to create a new sample Android
project from scratch. The sample project includes Liferay Mobile SDK libraries
and sample app code. We call this project the *Liferay Android Sample Project*.
You can refer to it to see how it connects with Liferay and invokes Liferay
services. Let's create the sample project now! 

1. Go to *File* &rarr; *Project...* &rarr; *Liferay* &rarr; *Liferay Android
Sample Project*.

2. Click *Next*. <!-- ... please describe the defaults we're accepting at this
screen. -->

3. Specify the *Project name*, *Location*, and *Target SDK*. Notice that you
also have the option to choose a sample project template from the Mobile SDK
preferences menu.

4. Click *Finish* to create the sample Android app.

![Figure 8.3: The Liferay Android Sample Project wizard gives you several options to customize your sample app.](../../images/mobile-sdk-sample-project-wizard.png)

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

![Figure 8.4: The `Session` interface is referenced from the sample app's Liferay Mobile SDK libraries.](../../images/sample-mobile-sdk-object.png)

The `Session` interface is referenced from the
`com.liferay.mobile.android.service` package. This app uses the Liferay Mobile
SDK from the library JARs in this project.

Let's run the sample app in Android's
[emulator](http://developer.android.com/tools/devices/emulator.html).

1. Start a local portal instance on port `8080`, if one is not already running. 

2. In Eclipse, go to *Run* &rarr; *Run As* &rarr; *Android Application*. 

3. Go to the *Liferay Contacts* app. 

The *Liferay Contacts* app lists your site's users. You can select users' 
names to see their information. 

![Figure 8.5: The sample project lets you view your local Liferay instance's user information.](../../images/liferay-contacts-app.png)

Congratulations on creating a mobile app that uses Liferay! 

Now let's consider the Android apps you've been developing. Is there data or
functionality that you'd like to leverage from your Liferay Portal instances?
The Mobile SDK enables you to use Liferay core services to get what you
need from your portal. This is easy to do with Liferay's Mobile SDK Eclipse
plugin. We'll show you how to use it next. 
