# Packaging Your Android Screenlets [](id=packaging-your-android-screenlets)

You should package your screenlets in a module (Android library) if you want to 
distribute them for use in other apps. Developers can then use your screenlets 
by adding that module as a project dependency in their apps. With the following 
steps, this tutorial shows you how to package and distribute your screenlets in 
a module:  

1. Create a new Android module and configure the `build.gradle` file.
2. Configure dependencies between each module.
3. Distribute the module by uploading it to jCenter or Maven Central.

Now get ready to package and distribute your screenlets like a pro! 

## Create a New Android Module [](id=create-a-new-android-module)

Android Studio's *Create New Module* wizard automatically creates a module and 
adds it to your `settings.gradle` file. Go to *File* &rarr; *New Module...*, 
select *Android Library* in the *More Modules* section, and click *Next*. Then 
name your module and click *Next*. The wizard's final step lets you add a new 
activity. Since your module doesn't need one, select *Blank Activity* and click 
*Finish*. Android Studio then creates a new `build.gradle` file with an Android 
Library configuration and adds the new module to `settings.gradle`. 

If you prefer to do this manually, you can use the `build.gradle` file from the 
[Material view set](https://github.com/liferay/liferay-screens/blob/master/android/viewsets/material/build.gradle) 
or [Westeros app](https://github.com/liferay/liferay-screens/blob/master/android/viewsets/westeros/build.gradle) 
as an example. After creating the module manually, you need to import it into 
your project by specifying its location in [`settings.gradle`](https://github.com/liferay/liferay-screens/blob/master/android/samples/test-app/settings.gradle). 
Here's an example of this configuration: 

    // Change YOUR_MODULE_NAME and RELATIVE_ROUTE_TO_YOUR_MODULE to match your module
    
    include ':YOUR_MODULE_NAME'
    project(':YOUR_MODULE_NAME').projectDir = new File(settingsDir, 'RELATIVE_ROUTE_TO_YOUR_MODULE')

Now that you have a module, you're ready to configure its dependencies.

## Configure Dependencies Between Each Module [](id=configure-dependencies-between-each-module)

Next, you need to configure your app to use the new module. To do so, add the 
following `compile` statement to the `dependencies` in your `build.gradle` file:

    // Change YOUR_MODULE_NAME to match your module's name
    
    dependencies {
        ...
        compile project (':YOUR_MODULE_NAME')
        ...
    }

Your module also needs the dependencies required to override the existing 
screenlets or create new ones. This usually means that you need to add Liferay 
Screens and the view sets you currently use as dependencies. To do so, add the 
following `compile` statement to the `dependencies` in your `build.gradle` file: 

    dependencies {
        ...
        compile 'com.liferay.mobile:liferay-screens:0.3.+'
        ...
    }

Awesome! Now you're ready to show your screenlet to the world!

## Upload the Module to jCenter or Maven Central [](id=upload-the-module-to-jcenter-or-maven-central)

Now that your module is ready, you can upload it to jCenter or Maven Central so 
that others can use it. Before doing so, you need to configure your 
`build.gradle` file appropriately for those repositories. Use the material or 
Westeros view set's [`build.gradle`](https://github.com/liferay/liferay-screens/blob/master/android/viewsets/westeros/build.gradle) 
file as an example. After entering your bintray api key, you can execute 
`gradlew bintrayupload` to upload your project to jCenter. Developers can then 
use your screenlet as any other Android dependency by adding the repository, 
artifact, groupId, and version to their gradle file. 

## Related Topics

[Creating Android Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-android-screenlets)

[Preparing Your Android Project for Liferay Screens](/develop/tutorials/-/knowledge_base/6-2/preparing-your-android-project-for-liferay-screens)

[Using Screenlets in Android Apps](/develop/tutorials/-/knowledge_base/6-2/using-screenlets-in-android-apps)

[Creating Android Views](/develop/tutorials/-/knowledge_base/6-2/creating-android-views)
