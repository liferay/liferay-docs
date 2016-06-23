# Preparing Android Projects for Liferay Screens [](id=preparing-android-projects-for-liferay-screens)

To use Liferay Screens in your Android project, you must install it, install its
dependencies, and configure it. We recommend you use Gradle or Maven to
configure your project automatically with Liferay Screens, but you can do it
manually too. Liferay Screens is released as an
[AAR](http://tools.android.com/tech-docs/new-build-system/aar-format) file
hosted in jCenter. This tutorial specifies Screens's requirements, shows you all
three configuration methods, and shows how to set up communication with Liferay.
Let the preparations begin! 

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
  and Liferay DXP instances. 
- Liferay Screens source code. 

Liferay Screens for Android uses
[EventBus](https://github.com/greenrobot/EventBus) internally. 

## Configuring Your Project with Gradle [](id=configuring-your-project-with-gradle)

Follow these steps to configure Liferay Screens in a Gradle project:

1. Add jCenter to your repositories:

        repositories {
            jcenter()
        }

2. Include Screens as a dependency:

        dependencies {
            compile 'com.liferay.mobile:liferay-screens:+'
        }

Gradle downloads all the necessary dependencies before building your project. If 
you get errors such as `Duplicate files copied in APK META-INF/NOTICE` when 
building with Gradle, add the following packaging options to your `build.gradle`
file: 

    android {
        ...
        packagingOptions {  
            exclude 'META-INF/LICENSE'
            exclude 'META-INF/NOTICE'
        }
        ...
    }

Next, steps for configuring your project with Maven are shown.

## Configuring Your Project with Maven [](id=configuring-your-project-with-maven)

Follow these steps to configure Liferay Screens in a Maven project:

1. Add the following dependency to your `pom.xml`:

        <dependency>
            <groupId>com.liferay.mobile</groupId>
            <artifactId>liferay-screens</artifactId>
            <version>LATEST</version>
        </dependency>

2. Force a Maven update to download all the dependencies.

If Maven doesn't automatically locate the artifact, you must add the jCenter as
a new repository in your maven settings (e.g., `.m2/settings.xml` file):

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

Even though we strongly recommend using Gradle or Maven to configure Liferay
Screens automatically in your project, you can configure it manually. 

## Manual Configuration in Gradle [](id=manual-configuration-in-gradle)

If you're using Gradle, you can follow these steps to manually install Screens
and its dependencies: 

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

Next, you'll set up communication with Liferay. 

## Configuring Communication with Liferay [](id=configuring-communication-with-liferay)

<iframe width="560" height="315" src="https://www.youtube.com/embed/QEJPEicgle8" frameborder="0" allowfullscreen></iframe>

Configuring communication between Screenlets and Liferay is easy. In your
project's `res/values` folder, create a new file called `server_context.xml`.
Add the following code to the new file: 

    <?xml version="1.0" encoding="utf-8"?>
    <resources>

        <!-- Change these values for your Liferay Portal installation -->

        <string name="liferay_server">http://10.0.2.2:8080</string>

        <integer name="liferay_company_id">10155</integer>
        <integer name="liferay_group_id">10182</integer>

    </resources>
 
As the above comment indicates, make sure to change these values to match
your Liferay instance. The server address `http://10.0.2.2:8080` is suitable 
for testing with Android Studio's emulator, because it corresponds to 
`localhost:8080` through the emulator. If you're using the Genymotion emulator,
you should, however, use address `192.168.56.1` instead of `localhost`. 

If you're using Liferay 7, you must also set the `liferay_portal_version` 
attribute in your `server_context.xml` to `70`. Supported values for this 
attribute are `62` for Liferay 6.2, and `70` for Liferay 7.0. If you don't set 
this attribute, it defaults to `62`. 

You can also configure Screenlet properties in your `server_context.xml` file. 
The example properties listed below, `liferay_recordset_id` and
`liferay_recordset_fields`, enable DDL Form Screenlet and DDL List Screenlet to
interact with a portal's DDLs. You can see an additional example
`server_context.xml` file
[here](https://github.com/liferay/liferay-screens/blob/master/android/samples/bankofwesteros/src/main/res/values/server_context.xml).

    <!-- Change these values for your Liferay Portal installation -->
    
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
  *Westeros Bank*. It's also available in [Google Play](https://play.google.com/store/apps/details?id=com.liferay.mobile.screens.bankofwesteros).

Great! Now you're ready to put Screens to use. The following tutorials show you 
how to do this.

## Related Topics [](id=related-topics)

[Using Screenlets in Android Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-android-apps)

[Using Views in Android Screenlets](/develop/tutorials/-/knowledge_base/7-0/using-views-in-android-screenlets)

[Preparing iOS Projects for Liferay Screens](develop/tutorials/-/knowledge_base/7-0/preparing-ios-projects-for-liferay-screens)
