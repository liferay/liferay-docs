# Making Liferay and Custom Portlet Services Available in Your Android App [](id=making-liferay-and-custom-portlet-services-available-in-your-android-app)

You must install the correct Mobile SDKs in your Android project to call the 
remote services you need in your app. You should first install Liferay's 
prebuilt Mobile SDK. This is required for any app that leverages Liferay. To 
call your custom portlet's services, you also need to install the Mobile SDK 
that you built for it. For instructions on building a Mobile SDK for your custom 
portlet, see the tutorial 
[Building Mobile SDKs](/develop/tutorials/-/knowledge_base/7-0/building-mobile-sdks). 

This tutorial shows you how to install Liferay's prebuilt Mobile SDK, and any 
custom built Mobile SDKs. First, you'll learn how to use Gradle or Maven to 
install Liferay's prebuilt Mobile SDK. You'll then learn how to install a Mobile 
SDK manually, which is required for installing any custom built Mobile SDKs. 
Now go forth and fear no remote service! 

## Adding the SDK to Your Gradle Project [](id=adding-the-sdk-to-your-gradle-project)

If your Android project is using Gradle as the build system, you can add 
Liferay's prebuilt Mobile SDK as a 
[dependency](/develop/tutorials/-/knowledge_base/7-0/configuring-dependencies)
to your project. All versions are available at the JCenter and Maven Central
repositories. Both repositories are listed here, but you only need to have one
in your app: 

    repositories {
      jcenter()
      mavenCentral()
    }

    dependencies {
      compile group: 'com.liferay.mobile', name: 'liferay-android-sdk', version: '7.0.+'
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

That's all there is to it! When your project syncs with your Gradle files, 
Liferay's prebuilt Mobile SDK downloads to your project. The instructions for 
doing this with Maven are shown next. 

## Adding the SDK to Your Maven Project [](id=adding-the-sdk-to-your-maven-project)

You can also add the Liferay's prebuilt Mobile SDK as a
[dependency](/develop/tutorials/-/knowledge_base/7-0/configuring-dependencies)
to your project using Maven. To do so, add the following code to your `pom.xml`
file: 

    <dependency>
        <groupId>com.liferay.mobile</groupId>
        <artifactId>liferay-android-sdk</artifactId>
        <version>LATEST</version>
    </dependency>

Awesome! However, what if you're not using Gradle or Maven? What if you want to 
install a custom built Mobile SDK? No problem! The next section shows you how to 
install a Mobile SDK manually. 

## Manually Adding the SDK to Your Android Project [](id=manually-adding-the-sdk-to-your-android-project)

Use the following steps to manually set up a Mobile SDK in your Android project: 

1. To install Liferay's prebuilt Mobile SDK, first 
   [download](https://github.com/liferay/liferay-mobile-sdk/releases/) 
   the latest version of `liferay-android-sdk-[version].jar`. If you built your 
   own Mobile SDK, find its JAR file on your machine. This is detailed in the 
   [Building Mobile SDKs](/develop/tutorials/-/knowledge_base/7-0/building-mobile-sdks) 
   tutorial. 

2. Copy the JAR into your Android project's `/libs` folder. 

3. If you're manually installing Liferay's prebuilt Mobile SDK, you also need to 
   download and copy these dependencies to your Android Project's `/libs` 
   folder: 
   [`httpclient-android-4.3.3.jar`](http://search.maven.org/remotecontent?filepath=org/apache/httpcomponents/httpclient-android/4.3.3/httpclient-android-4.3.3.jar)
   and 
   [`httpmime-4.3.3.jar`](http://search.maven.org/remotecontent?filepath=org/apache/httpcomponents/httpmime/4.3.3/httpmime-4.3.3.jar).

4. Start using it!

Great! Now you know how to manually install a Mobile SDK in your Android apps. 

## Making Custom Portlet Services Available in Your Android App [](id=making-custom-portlet-services-available-in-your-android-app)

If you want to invoke remote web services for your custom portlet, then you need 
to generate its client libraries by building an Android Mobile SDK yourself. 
Building an SDK is covered in the tutorial
[Building Mobile SDKs](/develop/tutorials/-/knowledge_base/7-0/building-mobile-sdks).
Once you build an SDK to a JAR file, you can install it using the manual 
installation steps above (make sure to use the JAR file you built instead of 
Liferay's prebuilt JAR file). Note that because your custom built SDKs contain 
*only* the client libraries for calling your custom portlet services, you must 
install them alongside Liferay's prebuilt SDK. Liferay's prebuilt SDK contains 
additional classes that are required to construct any remote service call. 

Super! Now that the remote services you need are available in your app, you're 
ready to call them. 

## Related Topics [](id=related-topics)

[Invoking Liferay Services in Your Android App](/develop/tutorials/-/knowledge_base/7-0/invoking-liferay-services-in-your-android-app)

[Creating iOS Apps that Use the Mobile SDK](/develop/tutorials/-/knowledge_base/7-0/creating-ios-apps-that-use-the-mobile-sdk)

[Building Mobile SDKs](/develop/tutorials/-/knowledge_base/7-0/building-mobile-sdks)
