# Preparing Your Android Project for Liferay Screens [](id=preparing-your-android-project-for-liferay-screens)

To use Liferay Screens, you must install it, install its dependencies, and
configure it in your Android project. We recommend you use Gradle or Maven to
automatically configure your project with Liferay Screens; but you can do it
manually too. Liferay Screens is released as an
[AAR](http://tools.android.com/tech-docs/new-build-system/aar-format) file
hosted in jCenter. This tutorial shows you all three configuration methods and
how to set up communication with Liferay. Let the configuration begin! 

## Configuring Your Project with Gradle

Follow these steps to configure Liferay Screens in a Gradle project:

1. Add jCenter to your repositories:

        repositories {
            jcenter()
        }

2. Include Screens as a dependency:

        dependencies {
            compile 'com.liferay.mobile:liferay-screens:0.3.0'
        }

Gradle downloads all the necessary dependencies before building your project. If 
you get errors, such as `Duplicate files copied in APK META-INF/NOTICE`, when 
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

## Configuring Your Project with Maven

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

Even though we strongly recommend using Gradle or Maven to automatically
configure Liferay Screens in your project, you can configure it manually. 

## Manual Configuration in Gradle

If you're using Gradle, you can follow these steps to manually install Screens
and its dependencies: 

1. [Download](https://github.com/liferay/liferay-screens/releases) the latest 
   version of Liferay Screens for Android.

2. Copy the contents of `Android/library` into a folder outside your project.

3. In your project, configure a `settings.gradle` file with the paths to the 
   library folders:

        include ':core'
        include ':viewsets'
        project(':core').projectDir = new File(settingsDir, '../../library/core')
        project(':viewsets').projectDir = new File(settingsDir, '../../library/viewsets')

4. Include the required dependencies in your `build.gradle` file: 

        compile project (':core')
        compile project (':viewsets')

You can also configure the `.aar` binary files (in `Android/dist`) as local 
`.aar` file dependencies. You can download all necessary files from
[jCenter](https://bintray.com/liferay/liferay-mobile/liferay-screens/view).

To check your configuration, you can compile and execute a blank activity and
import a Liferay Screens class (like `LoginScreenlet`). 

Next, you'll set up communication with Liferay. 

## Configuring Communication with Liferay

Regardless of how you install Screens in your project, you must configure
communication with Liferay. You could have the greatest app in existence, but if
it can't communicate with your Liferay installation then it's all for naught.
Fortunately, setup is easy. In your project's `res/values` folder, create a new
file called `server_context.xml`. Add the following code to the new file: 

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

In your `server_context.xml` file, you can configure screenlet properties. The
example properties listed below, `liferay_recordset_id` and
`liferay_recordset_fields`, enable `DDLFormScreenlet` and `DDLListScreenlet` to
interact with a portal's DDLs. You can see an additional example
`server_context.xml` file
[here](https://github.com/liferay/liferay-screens/blob/master/android/samples/bankofwesteros/src/main/res/values/server_context.xml).

    <!-- Change these values for your Liferay Portal installation -->
    
    <integer name="liferay_recordset_id">20935</integer>
    <string name="liferay_recordset_fields">Title</string>

Super! Your Android project's ready for Liferay Screens.

## Related Topics [](id=related-topics)

[Using Screenlets in Your Android Project](/tutorials/-/knowledge_base/6-2/using-screenlets-in-your-android-project)

[Using Views in Liferay Screens for Android](/tutorials/-/knowledge_base/6-2/using-views-in-liferay-screens-for-android)

[Architecture of Liferay Screens for Android](/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android)
