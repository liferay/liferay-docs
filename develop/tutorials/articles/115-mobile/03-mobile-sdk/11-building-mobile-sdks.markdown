# Building Mobile SDKs [](id=building-mobile-sdks)

The Liferay Mobile SDK lets you connect your Android and iOS apps to a @product@ 
instance. By accessing built-in portal services through Liferay's prebuilt 
Mobile SDK, your apps can access the out-of-the-box functionality in a @product@ 
instance. But what if you want to call custom services that belong to a custom 
portlet? No problem! In this case, you need to build your own Mobile SDK that 
can call these custom portlet services. 

Note that when you build a Mobile SDK for a portlet, it contains *only* the 
classes needed to call that portlet's remote services. You still need to install 
Liferay's prebuilt Mobile SDK in your app. It contains the framework required to 
construct remote service calls in general. 

The Liferay Mobile SDK project contains a Mobile SDK Builder that generates a 
custom Mobile SDK for the Android and iOS platforms. The Mobile SDK Builder 
does this by generating client libraries that let your native mobile apps invoke 
a custom portlet's remote web services. Think of the Mobile SDK Builder as a 
Service Builder on the client side 
([click here](/develop/tutorials/-/knowledge_base/7-0/service-builder) 
to learn about Service Builder). 

This tutorial covers how to build a custom Mobile SDK for Android and iOS. 
You'll begin by making sure the remote services are configured for any custom 
portlets you have. 

## Configuring Your Portlet's Remote Services [](id=configuring-your-portlets-remote-services)

For the Mobile SDK Builder to discover a portlet's remote services, the services 
must be available and accompanied by a Web Service Deployment Descriptor (WSDD). 
For instructions on creating a portlet's remote services and building its WSDD, 
[click here](/develop/tutorials/-/knowledge_base/7-0/creating-remote-services). 

Next, you'll download the Liferay Mobile SDK's source code. 

## Downloading the Liferay Mobile SDK [](id=downloading-the-liferay-mobile-sdk)

To build a Mobile SDK for your custom portlet's services, you need to have the 
[Liferay Mobile SDK's source code](https://github.com/liferay/liferay-mobile-sdk) 
on your local machine. This code also contains the Mobile SDK Builder. You can 
get this code by cloning the Mobile SDK project via Git, or by downloading it 
from GitHub. To clone the Mobile SDK project with Git, open a terminal and 
navigate to the directory on your machine in which you want to put the Mobile 
SDK. Then run this command: 

    git clone git@github.com:liferay/liferay-mobile-sdk.git

Since the Mobile SDK changes frequently, you should check out the latest stable 
release for your chosen mobile platform (Android or iOS). 
[Click here](https://github.com/liferay/liferay-mobile-sdk/releases) 
to see the list of available stable releases. Stable releases correspond to tags 
in GitHub that begin with the mobile platform and end with the Liferay Mobile 
SDK version. For example, the `android-7.0.6` tag corresponds to version 7.0.6 
of the Liferay Mobile SDK for Android. To check out this tag in a new branch of 
the same name, you can use this command: 

    git checkout tags/android-7.0.6 -b android-7.0.6

Alternatively, you can download the ZIP or TAR.GZ file listed under each tag on 
GitHub. 

Now you're ready to create your own module with the Mobile SDK! 

## Building a Liferay Mobile SDK [](id=building-a-liferay-mobile-sdk)

After you've downloaded the Mobile SDK's source code, you need to build a module 
for your portlet's services. The Mobile SDK Builder comes with a command line 
wizard that helps you build your module. From the Mobile SDK's root folder, run 
the following command to start the wizard:

    ./gradlew createModule

This starts the wizard with the most commonly required properties it needs to 
generate code for your portlet. If you need more control over these properties, 
run the same command with the `all` argument:

    ./gradlew createModule -P=all

The wizard should look similar to this screenshot. Note that default values are 
in square brackets with blue text:

![Figure 1: The Mobile SDK Builder's wizard lets you specify property values for building your module.](../../../images/mobile-sdk-create-module.png)

So what properties are available, and what do they do? Fantastic question! The 
following properties can be set while running `createModule`, or after you 
create your module. After creating your module, you can set these properties in 
your module's `gradle.properties` file. The values in parentheses are the keys 
used in `gradle.properties`: 

- `Context (context)`: Your portlet's web context. For example, if you're 
generating a Mobile SDK for @product@'s Calendar portlet, which is generally 
deployed to the `calendar` context, then you should set your context value to 
`calendar`. Under the hood, the SDK Builder tries to access 
`http://localhost:8080/calendar-portlet/api/jsonws?discover` to find out which 
services are available for this portlet. Check in a browser to make sure the URL 
for your service context is working before you run the Mobile SDK builder. If 
there are no services available at the specified context, you may have forgotten 
to generate your portlet's WSDD.

- `Platforms (platforms)`: The platforms to build the Mobile SDK for. By 
default, you can generate code for Android and iOS (`android,ios`). 

- `Server URL (url)`: The URL to your @product@ instance. The Mobile SDK Builder 
tries to connect to this instance at the specified context to discover your 
services.

- `Filter (filter)`: Specifies your portlet's entities to access their 
services. A blank value specifies all portlet entity services. For example, the 
Calendar portlet's entities include `CalendarBooking` and `CalendarResource`. To 
generate a Mobile SDK for only the `CalendarBooking` entity, set the filter's 
value to `calendarbooking` (all lowercase). The SDK Builder then makes requests 
to 
`http://localhost:8080/calendar-portlet/api/jsonws?discover=/calendarbooking/*`. 
If you don't specify a filter value, the remote services of *all* the portlet's 
entities are made available to your Mobile SDK.

- `Module Version (version)`: The version number appended to your Mobile SDK's 
JAR and ZIP files. This is discussed further in the following sections.

- `Package Name (packageName)`: On Android, this is the package your Mobile 
SDK's classes are written to (iOS doesn't use packages). Note that the @product@ 
version is appended to the end of the package name. For example, if you're using 
Liferay Portal 7.0 or Liferay DXP Digital Enterprise 7.0, and specify 
`com.liferay.mobile.android` as your package name, the Mobile SDK Builder 
appends `v7` to the package name, yielding `com.liferay.mobile.android.v7`. This 
prevents collisions between classes with the same names, allowing Mobile SDKs 
for more than one portal version to be used in the same app. You can use the 
`Portal Version` property to change the portal version. 

- `POM Description (description)`: The description for your POM file. This is 
only needed if you are building for Android and want to publish your JAR file to 
Maven.

Note that there's also a `destination` property that can only be set in the
`gradle.properties` file. This property specifies the destination for the 
generated source files. Generally, you don't need to change this.

After you set the properties you need, the Mobile SDK Builder generates your 
module in the folder `modules/${your_portlet_context}`. 

Now you must generate your Mobile SDK. To do this, navigate to your module and 
run `../../gradlew generate`. By default, the builder writes the source files to 
`android/src/gen/java` and `ios/Source` in your module's folder. 

If you update your portlet's remote services on the server side and need to 
update your Mobile SDK, simply run `../../gradlew generate` again. 

Awesome! Now you know how to create and regenerate a Mobile SDK for your custom 
portlet's remote services. Next, you'll finish by packaging your Mobile SDK for 
the Android and iOS. 

### Packaging Your Mobile SDK for Android [](id=packaging-your-mobile-sdk-for-android)

To package your Mobile SDK in a JAR file for Android, run the following command 
from your module's folder: 

    ../../gradlew jar

This packages your Mobile SDK in the following file: 

- `modules/${your_portlet_context}/build/libs/liferay-${your_portlet_context}-android-sdk-${version}.jar`

To call your portlet's remote services, you must first install this file in your 
Android project. To do so, copy the file into your Android app's `app/libs` 
folder. Note that you must also install Liferay's prebuilt Mobile SDK in your 
app. 
[Click here](/develop/tutorials/-/knowledge_base/7-0/making-liferay-and-custom-portlet-services-available-in-your-android-app#adding-the-sdk-to-your-gradle-project) 
for instructions on doing this. 

Also note that if you regenerate your Mobile SDK to include new functionality, 
you can update your module's version in its `gradle.properties` file. For 
example, if you added or changed a service method in the Mobile SDK you 
initially built, you could update it's version by setting `version=1.1` in your 
module's `gradle.properties` file. 

To learn how to use the Mobile SDK in your Android app, 
[click here](/develop/tutorials/-/knowledge_base/7-0/creating-android-apps-that-use-the-mobile-sdk) 
to see the rest of the Android Mobile SDK documentation. You can also use your 
Mobile SDK to create custom Screenlets in Liferay Screens. 
[Click here](/develop/tutorials/-/knowledge_base/7-0/android-apps-with-liferay-screens) 
to see the Liferay Screens documentation for Android.

### Packaging Your Mobile SDK for iOS [](id=packaging-your-mobile-sdk-for-ios)

To package your Mobile SDK in a ZIP file for iOS, run the following command from 
your module's folder: 

    ../../gradlew zip

This packages your Mobile SDK in the following file: 

- `modules/${your_portlet_context}/build/liferay-${your_portlet_context}-ios-sdk-${version}.zip`

To call your portlet's remote services, you must first install this file in your 
Xcode project. To do so, simply unzip it and add its files to your Xcode 
project. 

To learn how to use the Mobile SDK in your iOS app, 
[click here](/develop/tutorials/-/knowledge_base/7-0/creating-ios-apps-that-use-the-mobile-sdk) 
to see the rest of the iOS Mobile SDK documentation. You can also use your 
Mobile SDK to create custom Screenlets in Liferay Screens. 
[Click here](/develop/tutorials/-/knowledge_base/7-0/ios-apps-with-liferay-screens) 
to see the Liferay Screens documentation for iOS. 

## Related Topics [](id=related-topics)

[Creating Android Apps that Use the Mobile SDK](/develop/tutorials/-/knowledge_base/7-0/creating-android-apps-that-use-the-mobile-sdk)

[Creating iOS Apps that Use the Mobile SDK](/develop/tutorials/-/knowledge_base/7-0/creating-ios-apps-that-use-the-mobile-sdk)

[Android Apps with Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/android-apps-with-liferay-screens)

[iOS Apps with Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/ios-apps-with-liferay-screens)
