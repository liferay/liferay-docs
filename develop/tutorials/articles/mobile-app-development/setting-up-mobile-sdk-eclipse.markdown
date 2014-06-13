## Setting Up the Mobile SDK 

Liferay provides the *Liferay Mobile SDK Eclipse plugin* for you to use in
developing your mobile apps. Its powerful *Mobile SDK Builder* generates
libraries that enable your app to communicate with Liferay Portal and with the
custom portlet services deployed on your Liferay Portal instance. The plugin also
comes with a *Liferay Android Sample Project* that you can use as a reference
for building more Android apps of your own. Since Eclipse only supports Android
apps, you can't use the plugin to customize iOS apps. 

The Liferay Mobile SDK plugin depends on the *Android SDK Tools* and *Android
Development Tools (ADT)* Eclipse plugins. To satisfy these dependencies, you 
can install these plugins onto an existing Eclipse instance, or you can
install Google's *Android Developer Tools SDK* bundle which includes both of
the plugins. Here are the instructions for each of these options:

- **To install the *Android SDK Tools* and *ADT* plugins manually:** Follow the
[Setting Up an Existing
IDE](http://developer.android.com/sdk/installing/index.html) section on the 
Android developer site to download and install the [ADT
Eclipse](http://developer.android.com/tools/sdk/eclipse-adt.html) plugin to your
pre-existing Eclipse or Liferay Developer Studio/IDE installation. 

- **To install the *Android Developer Tools SDK* bundle:** Follow the
[Setting Up the ADT
Bundle](http://developer.android.com/sdk/installing/bundle.html) section on the 
Android developer site to download and install the ADT bundle, which is built on Eclipse
and which includes the required ADT plugins.

Once you've installed the required Android plugins, you can install the
Liferay Mobile SDK to your Eclipse instance by following these steps:

1. Go to the *Help* &rarr; *Install New Software* menu.

2. Copy the following URL into the *Work with* field:

        http://releases.liferay.com/tools/ide/latest/stable/

    In the *Liferay* drop-down menu that appears, click the drop-down arrow, 
    select *Liferay Mobile SDK*, and click *Next*.

    ![Figure 8.2: Download the *Liferay Mobile SDK* plugin for Eclipse by navigating to the *Install New Software* menu.](../../images/download-mobile-sdk-plugin.png)

3. Click *Finish* to complete installing the Liferay Mobile SDK plugin.

Terrific! You're ready to start developing Android apps that use Liferay! 

Next, let's create a *Liferay Android Sample Project* to learn how easy it is to
create a mobile app that uses Liferay. 
