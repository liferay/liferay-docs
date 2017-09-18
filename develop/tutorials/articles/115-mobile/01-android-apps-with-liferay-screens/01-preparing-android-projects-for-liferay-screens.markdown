# Preparing Android Projects for Liferay Screens [](id=preparing-android-projects-for-liferay-screens)

To use Liferay Screens, you must install it in your Android project and then 
configure it to communicate with your @product@ instance. Note that Screens is 
released as an 
[AAR](http://tools.android.com/tech-docs/new-build-system/aar-format) file 
hosted in jCenter. 

There are three different ways to install Screens. This tutorial shows you each: 

1. With Gradle: Gradle is the build system Android Studio uses to build Android 
   projects. We therefore recommend that you use it to install Screens. 
2. With Maven
3. Manually

+$$$

**Note:** After installation, you must configure Liferay Screens to communicate 
with your @product@ instance. The last section in this tutorial shows you how to 
do this. 

$$$

<iframe width="560" height="315" src="https://www.youtube.com/embed/ABxCUUg7zhs" frameborder="0" allowfullscreen></iframe>

## Requirements [](id=requirements)

Liferay Screens for Android includes the Component Library (the Screenlets) and 
a sample project. It requires the following software:

- Android Studio 2.0 or above.
- Android SDK 4.0 (API Level 15) or above. Its [AppCompat library](https://developer.android.com/tools/support-library/features.html#v7-appcompat) 
  (v7:23.0.1) implements the
  [recycler view](https://developer.android.com/tools/support-library/features.html#v7-recyclerview) 
  and offers older devices a material look and feel.
- [Liferay Portal 6.2 CE/EE, 7.0 CE, Liferay DXP](http://www.liferay.com/downloads/liferay-portal/available-releases).
- Liferay Screens Compatibility Plugin (
  [CE](http://www.liferay.com/marketplace/-/mp/application/54365664) or 
  [EE](http://www.liferay.com/marketplace/-/mp/application/54369726), 
  depending on your portal edition). This app is preinstalled in Liferay 7.0 CE 
  and Liferay DXP bundles. 
- Liferay Screens source code. 

Liferay Screens for Android uses
[EventBus](https://github.com/greenrobot/EventBus) internally. 

## Using Gradle to Install Liferay Screens [](id=configuring-your-project-with-gradle)

To use Gradle to install Liferay Screens in your Android Studio project, you 
must edit your app's `build.gradle` file. Note that your project has two 
`build.gradle` files: one for the project and another for the app module. You 
can find them under Gradle Scripts in your Android Studio project. This 
screenshot highlights the app module's `build.gradle` file: 

![Figure 1: The app module's `build.gradle` file.](../../../images/screens-android-gradle-app-module.png)

In the app module's `build.gradle` file, add the following line of code inside 
the `dependencies` element: 

    compile 'com.liferay.mobile:liferay-screens:+'

Note that the `+` symbol tells Gradle to install the newest version of Screens.
If your app relies on a specific version of Screens, you can replace the `+`
symbol with that version. 

If you're not sure where to add the above lines, see the below screenshot. 

Once you edit `build.gradle`, a message appears at the top of the file that asks 
you to *sync* your app with its Gradle files. Syncing the Gradle files
incorporates the changes you make to them. Syncing also downloads and installs
any new dependencies, like the Liferay Screens dependency that you just added.
Sync the Gradle files now by clicking the *Sync Now* link in the message. The
following screenshot shows the top of an edited `build.gradle` file with the
Sync Now link highlighted by a red box: 

![Figure 2: After editing the app module's `build.gradle` file, click *Sync Now* to incorporate the changes in your app.](../../../images/screens-android-gradle-sync.png)

Although we strongly recommend that you use Gradle to install Screens, the 
following section shows you how to install Screens with Maven. 

## Using Maven to Install Liferay Screens [](id=configuring-your-project-with-maven)

Note that we strongly recommend that you use Gradle to install Screens. It's
possible though to use Maven to install Screens. Follow these steps to
configure Liferay Screens in a Maven project: 

1. Add the following dependency to your `pom.xml`:

        <dependency>
            <groupId>com.liferay.mobile</groupId>
            <artifactId>liferay-screens</artifactId>
            <version>LATEST</version>
        </dependency>

2. Force a Maven update to download all the dependencies.

If Maven doesn't automatically locate the artifact, you must add jCenter as a
new repository in your maven settings (e.g., `.m2/settings.xml` file):

    <profiles>
        <profile>
            <repositories>
                <repository>
                    <id>bintray-liferay-liferay-mobile</id>
                    <name>bintray</name>
                    <url>http://dl.bintray.com/liferay/liferay-mobile</url>
                </repository>
            </repositories>
            <pluginRepositories>
                <pluginRepository>
                    <id>bintray-liferay-liferay-mobile</id>
                    <name>bintray-plugins</name>
                    <url>http://dl.bintray.com/liferay/liferay-mobile</url>
                </pluginRepository>
            </pluginRepositories>
            <id>bintray</id>
        </profile>
    </profiles>
    <activeProfiles>
        <activeProfile>bintray</activeProfile>
    </activeProfiles>

Nice work! 

## Manual Configuration in Gradle [](id=manual-configuration-in-gradle)

Although we strongly recommend that you use Gradle to install Screens 
automatically, it's possible to use Gradle to install Screens manually. Follow 
these steps to use Gradle to install Screens and its dependencies manually in 
your Android project: 

1. [Download](https://github.com/liferay/liferay-screens/releases) the latest 
   version of Liferay Screens for Android. 

2. Copy the contents of `Android/library` into a folder outside your project. 

3. In your project, configure a `settings.gradle` file with the paths to the 
   library folders:

        include ':core'
        project(':core').projectDir = new File(settingsDir, '../../library/core')
        project(':core').name = 'liferay-screens'

4. Include the required dependencies in your `build.gradle` file: 

        compile project(':liferay-screens')

You can also configure the `.aar` binary files (in `Android/dist`) as local 
`.aar` file dependencies. You can download all necessary files from
[jCenter](https://bintray.com/liferay/liferay-mobile/liferay-screens/view).

To check your configuration, you can compile and execute a blank activity and
import a Liferay Screens class (like Login Screenlet). 

Next, you'll set up communication with @product@. 

## Configuring Communication with @product@ [](id=configuring-communication-with-liferay)

<iframe width="560" height="315" src="https://www.youtube.com/embed/QEJPEicgle8" frameborder="0" allowfullscreen></iframe>

Before using Liferay Screens, you must configure it to communicate with your 
@product@ instance. To do this, you must provide Screens the following
information:

- Your @product@ instance's ID

- The ID of the site your app needs to communicate with

- Your @product@ instance's version

- Any other information required by specific Screenlets

Fortunately, this is straightforward. In your Android project's `res/values`
folder, create a new file called `server_context.xml`. Add the following code to
the new file: 

    <?xml version="1.0" encoding="utf-8"?>
    <resources>

        <!-- Change these values for your @product@ installation -->

        <string name="liferay_server">http://10.0.2.2:8080</string>

        <integer name="liferay_company_id">10155</integer>
        <integer name="liferay_group_id">10182</integer>
        
        <integer name="liferay_portal_version">70</integer>

    </resources>
 
As the above comment indicates, make sure to change these values to match
your @product@ instance. The server address `http://10.0.2.2:8080` is suitable 
for testing with Android Studio's emulator, because it corresponds to 
`localhost:8080` through the emulator. If you're using the Genymotion emulator,
you should, however, use address `192.168.56.1` instead of `localhost`. 

The `liferay_company_id` value is your @product@ instance's ID. You can find it 
in your @product@ instance at *Control Panel* &rarr; *Configuration* &rarr; 
*Virtual Instances*. The instance's ID is in the *Instance ID* column. Copy and 
paste this value into the `liferay_company_id` value in `server_context.xml`. 

The `liferay_group_id` value is the ID of the site your app needs to communicate 
with. To find this value, first go to the site in your @product@ instance that 
you want your app to communicate with. In the *Site Administration* menu, select 
*Configuration* &rarr; *Site Settings*. The site ID is listed at the top of the 
*General* tab. Copy and paste this value into the `liferay_group_id` value in 
`server_context.xml`. 

The `liferay_portal_version` value `70` tells Screens that it's communicating 
with a @product-ver@ instance. Supported values for are `62` for Liferay Portal 
6.2, and `70` for @product-ver@. Note that if you leave `liferay_portal_version` 
out of your `server_context.xml`, it defaults to `62`. 

You can also configure Screenlet properties in your `server_context.xml` file. 
The example properties listed below, `liferay_recordset_id` and
`liferay_recordset_fields`, enable DDL Form Screenlet and DDL List Screenlet to
interact with a @product@ instance's DDLs. You can see an additional example
`server_context.xml` file
[here](https://github.com/liferay/liferay-screens/blob/master/android/samples/bankofwesteros/src/main/res/values/server_context.xml).

    <!-- Change these values for your @product@ installation -->
    
    <integer name="liferay_recordset_id">20935</integer>
    <string name="liferay_recordset_fields">Title</string>

Super! Your Android project's ready for Liferay Screens.

## Example Apps [](id=example-apps)

As you use Screens to develop your apps, you may want to refer to some example 
apps that also use it. There are two demo applications available:

- [test-app](https://github.com/liferay/liferay-screens/tree/master/android/samples/test-app): 
  A showcase app containing all the currently available Screenlets.
- [Westeros Bank](https://github.com/liferay/liferay-screens/tree/master/android/samples/bankofwesteros): 
  An example app that uses Screenlets to manage technical issues for the 
  *Westeros Bank*. It's also available in 
  [Google Play](https://play.google.com/store/apps/details?id=com.liferay.mobile.screens.bankofwesteros).

Great! Now you're ready to put Screens to use. The following tutorials show you 
how to do this.

## Related Topics [](id=related-topics)

[Using Screenlets in Android Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-android-apps)

[Using Views in Android Screenlets](/develop/tutorials/-/knowledge_base/7-0/using-views-in-android-screenlets)

[Preparing iOS Projects for Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/preparing-ios-projects-for-liferay-screens)
