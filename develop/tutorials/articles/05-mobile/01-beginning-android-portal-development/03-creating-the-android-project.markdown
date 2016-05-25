# Creating the Android Project [](id=creating-the-android-project)

Now that you've built the Guestbook Mobile SDK, you're ready to create the 
Guestbook Android app. This article walks you through the steps required to 
create the app's project in Android Studio. After this, you'll be ready to 
install the Guestbook Mobile SDK and Liferay Screens. First though, you should 
make sure you've installed Android's development tools. 

## Installing Android Studio [](id=installing-android-studio)

This Learning Path uses Android Studio to develop the Guestbook app. While it's 
possible to use Eclipse, Android Studio is used here because it's the official 
Android IDE. Use the following links to download, install, and get acquainted 
with Android Studio: 

- [Download Android Studio](http://developer.android.com/sdk/index.html)
- [Android Studio Installation Instructions](https://developer.android.com/sdk/installing/index.html?pkg=studio)
- [Android Studio Overview](http://developer.android.com/tools/studio/index.html)
- [Android Studio Tips and Tricks](http://developer.android.com/sdk/installing/studio-tips.html)

Once you get Android Studio up and running, you're ready to create the Guestbook 
app! 

## Creating the Guestbook App [](id=creating-the-guestbook-app)

When you start Android Studio, a welcome screen with a Quick Start menu appears.
Click on *Start a new Android Studio project* in the Quick Start menu. Android
Studio's Create New Project wizard launches and asks you to fill in the
Application name, Company Domain, and Project location. Enter *Liferay
Guestbook* as the Application name and *docs.liferay.com* as the Company Domain.
These fields are used to fill in your app's *Package name* and
*Project location* automatically. For location, choose a location on your system
that's convenient for you. Click *Next*. 

![Figure 1: The first screen of Android Studio's Create New Project wizard asks you to enter your app's name and Company Domain.](../../../images/android-studio-new-project-01.png)

The next screen asks you to specify your app's supported form factors and
minimum Android SDK. Make sure that only the *Phone and Tablet* checkbox is
selected. In the *Minimum SDK* menu, select *API 15: Android 4.0.3
(IceCreamSandwich)*. Android Studio provides you with an estimate of the
percentage of devices active on the Google Play store that can run the selected
API level. You can view a graphical representation of these estimates by 
clicking the *Help me choose* link in the text below the Minimum SDK menu. 
Click *Next* when you're finished. 

![Figure 2: The second screen of Android Studio's Create New Project wizard lets you select your app's form factors and minimum Android API level.](../../../images/android-studio-new-project-02.png)

You now need to specify your app's first activity. Although you'll use this 
activity to authenticate users with the portal, don't select Login Activity in 
the wizard. At this point, you might be thinking, "Wait, why not? Why bother 
with writing all the authentication code from scratch, when Android Studio 
offers to do most of it for me?" Don't worry; you won't need to write any such 
code. You might now say, "Ha, you're funny! So I suppose I'll just create it 
with magic then?" In a manner of speaking, yes. You'll use Liferay Screens to 
insert 
[Login Screenlet](/develop/reference/-/knowledge_base/6-2/loginscreenlet-for-android) 
in an empty activity. Login Screenlet contains everything your users need to 
authenticate to a Liferay Portal installation, including the UI. Adding Login 
Screenlet to your app only takes about ten minutes. Although this technically 
isn't magic, you know all non-developers think you can wield magic, so we're
just helping you keep up the pretense. Now stop thinking about magic, and return
your attention to the wizard in Android Studio. Select *Empty Activity*, and
click *Next*.

![Figure 3: The third screen of Android Studio's Create New Project wizard lets you specify an activity for your app.](../../../images/android-studio-new-project-03.png)

The final screen of the New Project Wizard asks you to enter the activity's name 
and the name of its layout file. Accept the defaults and click *Finish*.

![Figure 4: The final screen of Android Studio's Create New Project wizard asks you to name your activity and its layout file.](../../../images/android-studio-new-project-04.png)

A progress indicator appears that indicates your project is building. Android 
Studio then opens the project with the activity's class and layout ready to edit. 
Click the `activity_main.xml` tab to view the layout file. This file is open in 
Design View. Design view gives you a preview of how Android renders the layout 
in the app. It also contains graphical tools for constructing a UI via drag and 
drop. Click the *Text* tab at the bottom of `activity_main.xml` to show its 
Text View. When you edit layout files in this Learning Path, you'll do so in 
Text View. 

By default, the Project View appears to the left of the open layout file. It 
lets you access your project's source files. Most of the files you'll work with
are in the `res/layout` and `java` directories. The `res/layout` directory
contains your app's layout files. The `java` directory contains your app's Java
source files. See the
[Android Studio Overview](http://developer.android.com/tools/studio/index.html) 
for more information on the project view.

![Figure 5: Project view in Android Studio.](../../../images/android-studio-project-view.png)

Well done! You've successfully created the initial Guestbook app and know how to 
access its files in Android Studio. Now it's time to put the Guestbook Mobile 
SDK and Liferay Screens to work! 
