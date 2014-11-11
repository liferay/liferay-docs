# Setting Up the Mobile SDK Manually [](id=setting-up-the-mobile-sdk-manually)

If you want to use the Liferay Mobile SDK with an existing Android project, or 
you're using a different IDE like *Android Studio*, you can download the latest 
version of the Mobile SDK and manually add it to your project library. You can 
also configure the Mobile SDK as a dependency to your project if you're using 
Gradle or Maven. Gradle and Maven download all the necessary dependencies 
before building your project. 

## Manually Adding the SDK to Your Android Project [](id=manually-adding-the-sdk-to-your-android-project)

Use the following steps to download and set up the Mobile SDK manually in your 
Android project:

1. [Download](https://github.com/liferay/liferay-mobile-sdk/releases/) the
latest version of `liferay-android-sdk.jar`.

2. Within your Android project, copy the JAR into your project's `/libs` folder.
Android Developer Tools should automatically add this JAR to your classpath.

3. You will also need to download and copy these dependencies to the `/libs`
folder: [httpclient-android-4.3.3.jar](http://search.maven.org/remotecontent?filepath=org/apache/httpcomponents/httpclient-android/4.3.3/httpclient-android-4.3.3.jar)
and [httpmime-4.3.3.jar](http://search.maven.org/remotecontent?filepath=org/apache/httpcomponents/httpmime/4.3.3/httpmime-4.3.3.jar).

4. Start using it!

## Adding the SDK to Gradle Project [](id=adding-the-sdk-to-gradle-project)

If your Android project is using Gradle as the build system, you can add the 
Liferay Android SDK as a dependency to your project. All versions are available 
at the JCenter and Maven Central repositories. Both repositories are listed 
here, but you only need to have one in your app:

    repositories {
      jcenter()
      mavenCentral()
    }

    dependencies {
      compile group: 'com.liferay.mobile', name: 'liferay-android-sdk', version: '6.2.0.+'
    }

If you get errors such as `Duplicate files copied in APK META-INF/NOTICE`
when building with Gradle, add this to your `build.gradle` file:
    
    android {

        packagingOptions {
            exclude 'META-INF/LICENSE'
            exclude 'META-INF/NOTICE'
        }

    }

    
## Adding the SDK to Your Maven Project [](id=adding-the-sdk-to-your-maven-project)

You can also add the Liferay Android SDK as a dependency to your project if 
you're using Maven as your build system. Just add the following code to your 
`pom.xml` file:

    <dependency>
        <groupId>com.liferay.mobile</groupId>
        <artifactId>liferay-android-sdk</artifactId>
        <version>LATEST</version>
    </dependency>
	
## Related Topics [](id=related-topics)

[Invoking Liferay Services in Your Android App](/develop/tutorials/-/knowledge_base/6-2/invoking-liferay-services-in-your-android-app)

[Creating iOS Apps that Use Liferay](/develop/tutorials/-/knowledge_base/6-2/creating-ios-apps-that-use-liferay)

[Liferay Mobile SDK Builder](/develop/tutorials/-/knowledge_base/6-2/liferay-mobile-sdk-builder)

[Service Builder and Services](/develop/tutorials/-/knowledge_base/6-2/service-builder)

[Developing Plugins with Liferay IDE](/develop/tutorials/-/knowledge_base/6-2/liferay-ide)
