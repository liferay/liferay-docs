# Packaging Your Android Screenlets [](id=packaging-your-android-screenlets)

To reuse your Screenlet in another app or distribute it, you can package it in a
module (Android library). You can optionally share it with other developers via
jCenter or Maven Central. Developers can then use your Screenlet by adding its
module as a project dependency in their apps. This tutorial explains how to
package and distribute Screenlets by following these steps: 

1.  Create a new Android module.
2.  Configure dependencies between each module.
3.  Distribute the module by uploading it to jCenter or Maven Central.

Now get ready to package and distribute Screenlets like a pro! 

## Create a New Android Module [](id=create-a-new-android-module)

Android Studio's *Create New Module* wizard can automatically create a module
and add it to your `settings.gradle` file. Go to *File* &rarr; *New Module...*,
select *Android Library* in the More Modules section, and click *Next*. Then
name your module and click *Next*. The wizard's final step lets you add a new
activity. Since your module doesn't need one, select *Blank Activity* and click
*Finish*. Android Studio creates a new `build.gradle` file with an Android
Library configuration and adds the new module to your `settings.gradle` file.

If you prefer to create a new module manually, examine the `build.gradle` file
from the
[Material View set](https://github.com/liferay/liferay-screens/blob/master/android/viewsets/material/build.gradle)
or
[Westeros app](https://github.com/liferay/liferay-screens/blob/master/android/viewsets/westeros/build.gradle)
as an example. After creating the module, import it into your project by
specifying its location in
[`settings.gradle`](https://github.com/liferay/liferay-screens/blob/master/android/samples/test-app/settings.gradle).
Here's an example configuration: 

    // Change YOUR_MODULE_NAME and RELATIVE_ROUTE_TO_YOUR_MODULE to match your module

    include ':YOUR_MODULE_NAME'
    project(':YOUR_MODULE_NAME').projectDir = new File(settingsDir, 'RELATIVE_ROUTE_TO_YOUR_MODULE')

Now that you have a module, you're ready to configure its dependencies.

## Configure Dependencies Between Each Module [](id=configure-dependencies-between-each-module)

Next, you must configure your app to use the module. To do so, add a project
compile statement to your `build.gradle` file's dependencies: 

    // Change YOUR_MODULE_NAME to match your module's name

    dependencies {
        ...
        compile project (':YOUR_MODULE_NAME')
        ...
    }

Your module must also specify dependencies for overriding existing Screenlets
and creating new ones. This usually requires adding Liferay Screens and the View
Sets your Screenlet currently uses to your `build.gradle` file's dependencies.
To add Liferay Screens as a dependency, add to your `build.gradle` file's
dependencies the following project compile statement: 

    compile 'com.liferay.mobile:liferay-screens:+'

Awesome! Now you're ready to share your Screenlet with the world!

## Upload the Module to jCenter or Maven Central [](id=upload-the-module-to-jcenter-or-maven-central)

To make your module available to anyone, you can upload your module to jCenter
or Maven Central. Before doing so, you must configure your `build.gradle` file
appropriately for those repositories. Use the material or Westeros View Set's
[`build.gradle`](https://github.com/liferay/liferay-screens/blob/master/android/viewsets/westeros/build.gradle)
file as an example. After entering your bintray api key, execute `gradlew
bintrayupload` to upload your project to jCenter. Developers can then use your
Screenlet as any other Android dependency by specifying its repository,
artifact, group ID, and version in their Gradle files. Congratulations on
publishing your Screenlet! 

## Related Topics [](id=related-topics)

[Creating Android Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-android-screenlets)

[Preparing Android Projects for Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/preparing-android-projects-for-liferay-screens)

[Using Screenlets in Android Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-android-apps)

[Creating Android Views](/develop/tutorials/-/knowledge_base/7-0/creating-android-views)
