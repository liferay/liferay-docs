# Setting Up the Mobile SDK [](id=setting-up-the-mobile-sdk-manually)

Setting up the Mobile SDK in your Android project is the first step to 
developing apps that leverage Liferay. You can add the Mobile SDK by configuring 
it as a dependency in Gradle or Maven, or by installing it manually. Either way, 
you'll be up and running in no time! 

## Adding the SDK to Your Gradle Project [](id=adding-the-sdk-to-gradle-project)

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

If you get errors such as `Duplicate files copied in APK META-INF/NOTICE` when 
building with Gradle, add this to your `build.gradle` file:
    
    android {
        ...
        packagingOptions {
            exclude 'META-INF/LICENSE'
            exclude 'META-INF/NOTICE'
        }
        ...
    }

That's all there is to it! When your project syncs with your Gradle files, the 
Mobile SDK downloads to your project. The instructions for installing the Mobile 
SDK with Maven are presented next.
    
## Adding the SDK to Your Maven Project [](id=adding-the-sdk-to-your-maven-project)

You can also add the Liferay Mobile SDK as a dependency to your project if 
you're using Maven as your build system. Just add the following code to your 
`pom.xml` file:

    <dependency>
        <groupId>com.liferay.mobile</groupId>
        <artifactId>liferay-android-sdk</artifactId>
        <version>LATEST</version>
    </dependency>

Awesome! However, what if you're not using Gradle or Maven? No problem! The next 
section shows you how to install the Mobile SDK manually.

## Manually Adding the SDK to Your Android Project [](id=manually-adding-the-sdk-to-your-android-project)

Use the following steps to download and set up the Mobile SDK manually in your 
Android project:

1. [Download](https://github.com/liferay/liferay-mobile-sdk/releases/) the
latest version of `liferay-android-sdk.jar`.

2. Copy the JAR into your Android project's `/libs` folder. 

3. You also need to download and copy these dependencies to your Android 
   Project's `/libs` folder: 
   [httpclient-android-4.3.3.jar](http://search.maven.org/remotecontent?filepath=org/apache/httpcomponents/httpclient-android/4.3.3/httpclient-android-4.3.3.jar)
   and 
   [httpmime-4.3.3.jar](http://search.maven.org/remotecontent?filepath=org/apache/httpcomponents/httpmime/4.3.3/httpmime-4.3.3.jar).

4. Start using it!

Great! Now you know how to install the Mobile SDK in your Android apps.

## Related Topics [](id=related-topics)

[Invoking Liferay Services in Your Android App](/develop/tutorials/-/knowledge_base/6-2/invoking-liferay-services-in-your-android-app)

[Creating iOS Apps that Use Liferay](/develop/tutorials/-/knowledge_base/6-2/creating-ios-apps-that-use-liferay)

[Liferay Mobile SDK Builder](/develop/tutorials/-/knowledge_base/6-2/liferay-mobile-sdk-builder)

[Service Builder and Services](/develop/tutorials/-/knowledge_base/6-2/service-builder)

[Developing Plugins with Liferay IDE](/develop/tutorials/-/knowledge_base/6-2/liferay-ide)
