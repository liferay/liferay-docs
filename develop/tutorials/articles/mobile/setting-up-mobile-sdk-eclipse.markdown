# Setting Up the Mobile SDK in Eclipse [](id=setting-up-the-mobile-sdk-in-eclipse)

Liferay provides the *Liferay Mobile SDK Eclipse plugin* for you to use in
developing your mobile apps. Its powerful *Mobile SDK Builder* generates
libraries that enable your app to communicate with Liferay Portal and with the
custom portlet services deployed on your Liferay Portal instance. The plugin 
also comes with a *Liferay Android Sample Project* that you can use as a 
reference for building more Android apps of your own. Since Eclipse only 
supports Android apps, you can't use the plugin to customize iOS apps. 

If you want to use the Mobile SDK within an existing Android project or if
you're using a different IDE, like *Android Studio*, you can download the latest
version of the Mobile SDK and add it to your project library. If you want to use
the Mobile SDK in a Maven project, you can configure it as a dependency. For
more information about these topics, read the
[Setting Up the Mobile SDK Manually](/develop/tutorials/-/knowledge_base/6-2/setting-up-the-mobile-sdk-manually). 

This tutorial starts by making sure that your environment has the software on
which the Mobile SDK depends. Then you'll learn how to install the SDK following
a few simple steps. 

## Satisfying Dependencies 

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

## Installing the Liferay Mobile SDK 

Once you've installed the required Android plugins, you can install the
Liferay Mobile SDK to your Eclipse instance by following these steps:

1. Go to the *Help* &rarr; *Install New Software* menu.

2. Click *Add* next to the *Work with* field. Enter `Liferay` in the *Name* 
   field, and copy the following URL into the *Location* field:

        http://releases.liferay.com/tools/ide/latest/stable/

    Click *Ok*.
    
3. In the *Liferay* drop-down menu that appears, click the drop-down 
   arrow, select *Liferay Mobile SDK*, and click *Next*.

    ![Figure 1: Download the *Liferay Mobile SDK* plugin for Eclipse by navigating to the *Install New Software* menu.](../../images/download-mobile-sdk-plugin.png)

4. Click *Next* again, accept the license agreement, and click *Finish* to 
   complete installing the Liferay Mobile SDK plugin.

Terrific! You're ready to start developing Android apps that use Liferay! 

## Related Topics

[Creating the Liferay Android Sample Project](add link once header id is generated)

[Making Liferay and Custom Portlet Services Available in Your Android App](add link once header id is generated)

[Creating iOS Apps that Use Liferay](/develop/tutorials/-/knowledge_base/6-2/creating-ios-apps-that-use-liferay)

[Liferay Mobile SDK Builder](/develop/tutorials/-/knowledge_base/6-2/liferay-mobile-sdk-builder)

[Service Builder and Services](/develop/tutorials/-/knowledge_base/6-2/service-builder)

[Developing Plugins with Liferay IDE](/develop/tutorials/-/knowledge_base/6-2/liferay-ide)
