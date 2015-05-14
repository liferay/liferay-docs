# Creating the Android Project 

Now that you've built the Liferay Mobile SDK for the Guestbook portlet, you're 
ready to create the Guestbook Android app. This article walks you through the 
steps required to create the app and install the Mobile SDK in it. After this, 
you'll be ready to leverage the service classes the Mobile SDK Builder generated 
for the Guestbook portlet. First though, you should make sure you've installed 
Android's development tools. 

## Installing Android Studio 

This learning path uses Android Studio to develop the Guestbook app. While it's 
possible to use Eclipse, Android Studio is used here because it's the official 
Android IDE. Use the following links to download, install, and get acquainted 
with Android Studio: 

- [Download Android Studio](http://developer.android.com/sdk/index.html)
- [Android Studio Installation Instructions](https://developer.android.com/sdk/installing/index.html?pkg=studio)
- [Android Studio Overview](http://developer.android.com/tools/studio/index.html)
- [Android Studio Tips and Tricks](http://developer.android.com/sdk/installing/studio-tips.html)

Once you get Android Studio up and running, you're ready to create the Guestbook 
app! 

## Creating the Guestbook App

When you start Android Studio, a welcome screen with a Quick Start menu appears.
Click on *Start a new Android Studio project* in the Quick Start menu. Android
Studio's *Create New Project* wizard launches and asks you to fill in the
*Application name*, *Company Domain*, and *Project location*. Enter *Liferay
Guestbook* as the Application name and *docs.liferay.com* as the Company Domain.
These fields are used to automatically fill in your app's *Package name* and
*Project location*. Click *Next*. 

![Figure 1: The first screen of Android Studio's Create New Project wizard asks you to enter your app's name and Company Domain.](../../images/android-studio-new-project-01.png)

The next screen of the wizard asks you to specify your app's supported form 
factors and minimum Android SDK. Make sure that only the *Phone and Tablet* 
checkbox is selected. In the *Minimum SDK* menu, select 
*API 15: Android 4.0.3 (IceCreamSandwich)*. Android Studio provides you with an 
estimate of the percentage of devices active on the Google Play store that can 
run the selected API level. You can view a graphical representation of these 
estimates by clicking on the *Help me choose* link in the text below the Minimum 
SDK menu. Click *Next* when you're finished.

![Figure 2: The second screen of Android Studio's Create New Project wizard lets you select your app's form factors and minimum Android API level.](../../images/android-studio-new-project-02.png)

You now need to specify the *activity* to create for your app. An Android 
activity is essentially an on-screen view that serves as an app's entry point. 
It's important to note that an activity can be *any* on-screen view. It's not 
restricted to the app's launch screen. For example, an email app might contain 
several activities: one for reading messages, one for composing messages, and 
another for selecting between mailboxes. Other apps can start the email app in 
any of those activities. For example, if the user presses an email address in 
their web browser app, the browser can be configured to open the email app in 
the activity used to compose messages. Alternatively, the browser could open the 
Contacts app in an activity designed to create a new contact with an email 
address. 

The Guestbook app's design is to display the Guestbook portlet's guestbooks in a
navigation drawer that slides out from the screen's left side. When a guestbook
is pressed in the drawer, the drawer closes and the guestbook's entries are
displayed. While this makes for a smooth UX, it's somewhat complex to implement.
Fortunately, Android Studio has an activity template that does most of the work
for you! In the wizard, select *Navigation Drawer Activity* and click *Next*. 

![Figure 3: The third screen of Android Studio's Create New Project wizard lets you specify an activity for your app.](../../images/android-studio-new-project-03.png)

The final screen of the New Project Wizard asks you to enter the activity's name 
and the names of the activity's components. The fields in the wizard are 
summarized here:

- *Activity Name*: The activity's class name.
- *Layout Name*: The activity's layout name.
- *Fragment Layout Name*: The activity fragment's layout name.
- *Title*: The activity's name.
- *Navigation Drawer Fragment Name*: The drawer fragment's name.

At this point, you're probably wondering what *fragments* and *layouts* are.
Fragments are similar to activities. They are portions of on-screen views that
can be accessed in your app. Fragments exist *within* activities and there can
be multiple fragments per activity. In this case, the Guestbook app's drawer and
main views are fragments. Google describes fragments in detail
[here](http://developer.android.com/guide/components/fragments.html). Layouts
determine how UI components are positioned in a fragment or activity. For
example, an app may have a series of buttons that are positioned horizontally
across the bottom of the screen. To accomplish this, the buttons can be placed
in Android's `LinearLayout` with its `android:orientation` attribute set to
`horizontal`. Layouts are typically specified in XML files, with one file for
each activity or fragment. For more information on layouts, see Android's
[layout documentation](http://developer.android.com/guide/topics/ui/declaring-layout.html). 

![Figure 4: The final screen of Android Studio's Create New Project wizard asks you to name your activity's components.](../../images/android-studio-new-project-04.png)

Now that you know a bit about fragments and layouts, accept the defaults in the
wizard and click *Finish*. A progress indicator appears that indicates that your
project is building. Android Studio then opens the project, with the main
fragment's layout (`fragment_main.xml`) open in *design view*. Design view gives
you a preview of how a layout file is rendered in the app. It also contains
graphical tools for constructing a UI via drag and drop. Click the *Text* tab at
the bottom of `fragment_main.xml` to show its *text view*. When you edit layout
files in this learning path, you'll do so in text view. By default, the *project
view* appears to the left of the open layout file. It lets you access your
project's source files. Most of the files you'll be working with in this
learning path are in the `res/layout` and `java` directories. Your app's layout
files are contained in `res/layout`. The `java` directory contains your app's
Java source files. See the
[Android Studio Overview](http://developer.android.com/tools/studio/index.html) 
for more information on the project view.

![Figure 5: Project view in Android Studio.](../../images/android-studio-project-view.png)

Well done! You've successfully created the initial Guestbook app and know how to 
access its files in Android Studio. Now you're ready to run it.

## Running the Guestbook App

Even though your app doesn't do much yet, Android Studio created enough of its 
basic structure to let you run it. This learning path runs the app in Android 
Studio's default emulator. To run the app, click the green play button in the 
toolbar. This button is highlighted by a red box in the following screenshot:

![Figure 6: Click the green play button to run your app.](../../images/android-studio-run.png)

After a moment, a new window opens that asks you to select an emulator. Make
sure the radio button next to *Launch emulator* is selected, select the emulator
from the *Android virtual device* menu, and click *OK*. If you don't have an
Android virtual device, create one and then select it. The emulator starts up to
Android's default lock screen. Note that it may take some time for it to start,
especially if your machine is low on memory. Click the lock icon at the bottom
of the lock screen and drag up. The emulated device then opens to the Guestbook
app with the drawer open (it might take a few seconds for the app to launch
after unlocking the emulated device).

![Figure 7: When the Guestbook app first runs, it should look something like this.](../../images/android-guestbook-first-run.png)

The bar across the top contains a stack of three small horizontal lines, the
app's name, and a stack of three small dots. This bar is called the *action
bar*. Google provides extensive
[design](http://developer.android.com/design/patterns/actionbar.html) and
[developer](http://developer.android.com/guide/topics/ui/actionbar.html)
documentation for the action bar. In short, the action bar is where you should
put actions and navigation elements relevant to the displayed activity or
fragment. For example, clicking the dots opens a menu that lets you access the
app's settings (even though clicking settings doesn't do anything yet). Clicking
the horizontal lines serves as a toggle that opens and closes the drawer. When
the drawer is closed either via the toggle or by selecting an item in the
drawer, the text in the action bar changes to reflect the last item selected.
There's also an additional item in the action bar when the drawer is closed: an
*EXAMPLE ACTION* button. Click it now. A message temporarily appears at the
bottom of the emulator's screen that says *Example action*. This message type in
Android is called a *toast*. Toasts are useful for showing simple messages that
you want to appear and then disappear after a short time. They can also be handy
for debugging. Android's developer documentation for toasts can be found
[here](http://developer.android.com/guide/topics/ui/notifiers/toasts.html).

![Figure 8: The action bar when an item is selected from the drawer.](../../images/android-guestbook-action-bar.png)

As you can see, Android Studio does a significant amount of work for you in 
creating the basic structure of the app. With only a few steps and no coding, 
you created an app with a functional navigation drawer. Now it's time to put 
Liferay's Mobile SDK to work!
