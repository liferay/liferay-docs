# Packaging Your Android Screenlets [](id=packaging-your-android-screenlets)

If you want to distribute your screenlets for use in different projects, you 
should package them in a module (Android library). To use the screenlet, 
developers then add that module as a project dependency in their app. 

Use the following steps to package your screenlets in a module: 

1. Create a new Android module and configure the `build.gradle` file.
2. Configure dependencies between each module.
3. Optionally, you can distribute the module by uploading it to jCenter or Maven 
   Central.

The next sections detail these steps.

### Create a New Android Module [](id=create-a-new-android-module)

Fortunately, Android Studio has a menu option that automatically creates an 
Android module and adds it to your `settings.gradle` file. Go to 
*File* &rarr; *New* &rarr; *New Module* &rarr; *Android Library* (in 
*More Modules*) and enter a name for your new module. You don't need a new 
activity for the new module, so just use *Blank Activity*. Android Studio 
automatically creates a new `build.gradle` file (with an Android Library 
configuration) and adds the new module to the `settings.gradle` file. 

If you prefer to do this manually, you need to create a new Android Library. 
This is essentially an Android app project with the gradle import set to 
`apply plugin: 'com.android.library'`. Use the [gradle file](https://github.com/liferay/liferay-screens/blob/master/android/library/viewsets/build.gradle) 
from the material viewset or Westeros app as an example. 

After creating the module manually, you need to import it into your project by 
specifying its location in [`settings.gradle`](https://github.com/liferay/liferay-screens/tree/master/android/samples/settings.gradle). 
Here's an example of this configuration:

    // Change YOUR_MODULE_NAME and RELATIVE_ROUTE_TO_YOUR_MODULE to match your module
    
    include ':YOUR_MODULE_NAME'
    project(':YOUR_MODULE_NAME').projectDir = new File(settingsDir, 'RELATIVE_ROUTE_TO_YOUR_MODULE')

### Configure Dependencies Between Each Module [](id=configure-dependencies-between-each-module)

Next, you need to configure your app to use the new module. To do so, add the 
following `compile` statement to the `dependencies` in your `build.gradle` file:

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

### Upload the Module to jCenter or Maven Central [](id=upload-the-module-to-jcenter-or-maven-central)

If you want to distribute your screenlet so that others can use it, you can 
upload it to jCenter or Maven Central. Use the [`build.gradle`](https://github.com/liferay/liferay-screens/blob/LMW-230-Changes-In-Westeros-App/android/viewsets/westeros/build.gradle) 
file of the material or Westeros view set as an example. 

After entering your bintray api key, you can execute `gradlew bintrayupload` to 
upload your project to jCenter. When finished, your screenlet can be used as any 
other Android dependency. Developers just need to add the repository, artifact, 
groupId, and version to their gradle file. 
