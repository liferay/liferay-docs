# Building Mobile SDKs [](id=building-mobile-sdks)

The Liferay Mobile SDK is great for letting you connect your mobile apps to a 
Liferay instance. By accessing Liferay services through Liferay's prebuilt 
Mobile SDK, your apps can access the data and functionality in a Liferay 
instance. But what happens when the services you need to use aren't built-in 
Liferay services? What if they're custom portlet services? No problem! In this 
case, you need to build your own Mobile SDK to access the services of the custom 
portlets. 

Note that when you build a Mobile SDK for your portlet, it contains *only* the 
classes needed to call your portlet's remote services. You still need to install 
Liferay's prebuilt Mobile SDK in your app; it also contains the classes required 
to construct remote service calls in general.

The Liferay Mobile SDK project comes with an SDK Builder that generates the 
Liferay Mobile SDK for the Android and iOS platforms. Think of it as a Service
Builder on the client side. The SDK Builder generates client libraries that 
allow your native mobile apps to invoke remote web services of a Liferay 
instance and any of its custom portlets. 

This tutorial covers how to build a Liferay Mobile SDK for Android and iOS. 
You'll begin by making sure the remote services are configured for any custom 
portlets you have. Go ahead and get started! 

## Configuring Your Portlet's Remote Services [](id=configuring-your-portlets-remote-services)

For the SDK Builder to discover a portlet's remote services, the services must 
be available and accompanied by a Web Service Deployment Descriptor (WSDD). For 
instructions on creating a portlet's remote services and building its WSDD, see 
[this tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-remote-services).

For convenience, the following steps show how to implement remote services for 
your portlet and and make them available for the SDK Builder to discover.

1. Install Liferay's WSDD Builder Gradle plugin. To do this, add 
   `com.liferay.gradle.plugins.wsdd.builder` as a dependency in your `*-service` 
   project's `build.gradle` file, and apply the plugin 
   `com.liferay.portal.tools.wsdd.builder` in the same file. 

2. Set `remote-service="true"` in your `*-service` project's `service.xml` file 
   and run `gradle buildService`. 

3. Implement your remote services in the generated `[YourEntity]ServiceImpl.java` 
   source files and run `gradle buildService` again. 

4. Run `gradle buildWSDD` to build your WSDD. The Mobile SDK Builder relies on 
   the WSDD to discover your portlet's remote services, so make sure to build 
   the WSDD before deploying your portlet's modules. In your `*-service` 
   project's `build/libs` folder, the `buildWSDD` task generates a 
   `*-service-wsdd-[version].jar` file that contains your WSDD. 

5. Deploy your portlet's modules, including the `*-service-wsdd-[version].jar` 
   file. 

Now you're ready to use the SDK Builder to generate a custom portlet SDK for 
your mobile app! 

## Setting Up the SDK Builder [](id=setting-up-the-sdk-builder)

To build a Mobile SDK for your custom portlet's services, you need to have the 
[Liferay Mobile SDK project](https://github.com/liferay/liferay-mobile-sdk) 
on your local machine. If you haven't done so already, download the Mobile SDK 
project using Git.

    git clone git@github.com:liferay/liferay-mobile-sdk.git

Since the SDK Builder changes frequently, it's safer to checkout the latest 
stable release. Stable SDK Builder releases correspond to tags with the 
`builder-` prefix in GitHub. For example, you can use the following command to 
check out the `builder-6.2.0.17` tag in a new branch of the same name: 

    git checkout tags/builder-6.2.0.17 -b builder-6.2.0.17

Check for the latest stable SDK Builder version 
[here in GitHub](https://github.com/liferay/liferay-mobile-sdk/tags). 
 
Alternatively, you can use the SDK Builder as a standalone library. Its `jar` is 
available on the JCenter and Maven Central repositories. 

	<dependency>
		<groupId>com.liferay.mobile</groupId>
		<artifactId>liferay-sdk-builder</artifactId>
		<version>6.2.0.1</version>
	</dependency>

Check the main method of `src/main/java/com/liferay/mobile/sdk/SDKBuilder.java` 
to see how this class can be called from the command line. 

If you don't want to use either of those methods, you can download 
[the Mobile SDK's source code](https://github.com/liferay/liferay-mobile-sdk/releases).

Now you're ready to create your own module with the Mobile SDK! 

## Creating Your Module [](id=creating-your-module)

After you've downloaded the Mobile SDK's source code, you need to build a module 
for your portlet's services. The Mobile SDK Builder comes with a command line 
wizard that helps you build your module. From the Mobile SDK's root folder, run 
the following command to start the wizard:

    ./gradlew createModule

This starts the wizard with the most commonly required properties it needs to 
generate code for your portlet. If for some reason you need more control over 
these properties, run the same command with the `all` argument:

    ./gradlew createModule -P=all

The wizard should look similar to this screenshot. Note that default values are 
in square brackets with blue text:

![Figure 1: The Mobile SDK Builder's wizard lets you specify property values for building your module.](../../../images/mobile-sdk-create-module.png)

So what properties are available, and what do they do? Fantastic question! 
They're described here. These properties can be set while running 
`createModule`, or after your module is created in the `gradle.properties` file 
inside your module's folder. The values in parentheses are the keys used in 
`gradle.properties`:

* `Context (context)` - Your portlet's web context. For example, if you're 
generating a Mobile SDK for Liferay's Calendar portlet, which is generally 
deployed to the `calendar-portlet` context, then you should set your context 
value to `calendar-portlet`. Under the hood, the SDK Builder tries to access 
`http://localhost:8080/calendar-portlet/api/jsonws?discover` to find out which 
services are available for this portlet. Check in a browser to make sure this 
URL is working before you run the Mobile SDK builder. If there are no services 
available at the specified context, you may have forgotten to generate your 
portlet's WSDD.

* `Platforms (platforms)` - The platforms to build the Mobile SDK for. By 
default, you can generate code for Android and iOS (`android,ios`). 

* `Server URL (url)` - The URL to your Liferay instance. The Mobile SDK Builder 
tries to connect to this instance at the specified context to discover your 
services.

* `Filter (filter)` - Specifies your portlet's entities to access their 
services. A blank value specifies all portlet entity services. For example, the 
Calendar portlet's entities include `CalendarBooking` and `CalendarResource`. To 
generate a Mobile SDK for only the `CalendarBooking` entity, set the filter's 
value to `calendarbooking` (all lowercase). The SDK Builder then makes requests 
to 
`http://localhost:8080/calendar-portlet/api/jsonws?discover=/calendarbooking/*`. 
If you don't specify a filter value, the remote services of *all* the portlet's 
entities are made available to your Mobile SDK.

* `Module Version (version)` - The version number appended to your Mobile SDK's 
JAR and ZIP files. This is discussed further in the following sections.

* `Package Name (packageName)` - On Android, this is the package your Mobile 
SDK's classes are written to (iOS doesn't use packages). Note that the Liferay 
version is appended to the end of the package name. For example, if you're using 
Liferay 6.2 and specify `com.liferay.mobile.android` as your package name, the 
Mobile SDK Builder appends `v62` to it, yielding 
`com.liferay.mobile.android.v62`. This prevents collisions between classes with 
the same names, allowing Mobile SDKs for several Liferay versions to be used in 
the same app. The Liferay version can be changed with the `Portal Version` 
property.

* `POM Description (description)` - The description for your POM file. This is 
only needed if you are building for Android and want to publish your JAR file to 
Maven.

Note that there is also a `destination` property that can only be set in the
`gradle.properties` file. This property specifies the destination for the 
generated source files. Generally, you don't need to change this.

After you specify the properties you need, the Mobile SDK Builder generates your 
portlet client code in the folder `modules/${your_portlet_context}`. In this 
folder, the source files are by default written to `android/src/gen/java` and 
`ios/Source`.

If you update your portlet's remote services on the server side and need to 
update your Mobile SDK, run the following command from your module's folder:

    ../../gradlew generate

This runs builders for each platform and regenerates the code in the same 
default folders.

Awesome! Now you know how to create and regenerate a module for your custom 
portlet's remote services. Next, you'll finish by packaging your Mobile SDK for 
the platforms you need to use it on.

## Building a Liferay Android SDK [](id=building-a-liferay-android-sdk)

To build a JAR file containing your module's service and utility classes, run
the following command from your module's folder:

    ../../gradlew jar

This writes a `liferay-${your_portlet_context}-android-sdk-${version}.jar` 
file to your module's `android/build/libs` folder. This is your portlet's Mobile 
SDK! You can use it in your Android project to call your portlet's remote 
services.

Note that if you regenerate your Mobile SDK to include new functionality, you 
can update your module's version in its `gradle.properties` file. For example, 
if you added or changed a service method in the Mobile SDK you initially built, 
you could update it's version by setting `version=1.1` in your module's 
`gradle.properties` file.

To learn how to use the Mobile SDK in your Android app, see the
[Android Mobile SDK documentation](/develop/tutorials/-/knowledge_base/7-0/creating-android-apps-that-use-the-mobile-sdk).

## Building a Liferay iOS SDK [](id=building-a-liferay-ios-sdk)

To build a ZIP file containing your module's service and utility classes, run
the following command from your module's folder:

    ../../gradlew zip

This writes a `liferay-${your_portlet_context}-ios-sdk-${version}.zip` file to 
your module's `ios/build` folder. This contains your portlet's Mobile SDK! You 
can use this file's contents in your iOS project to call your portlet's remote 
services. Simply unzip it and add its files to your Xcode project. 

To learn how to use the Mobile SDK in your iOS app, see the
[iOS Mobile SDK documentation](/develop/tutorials/-/knowledge_base/7-0/creating-ios-apps-that-use-the-mobile-sdk).

Super! You've just built Liferay Mobile SDKs for Android and iOS. Now you can 
use them to call your custom portlet services in your mobile apps. You're a 
mobile app rock star! 

## Related Topics [](id=related-topics)

[Creating Android Apps that Use the Mobile SDK](/develop/tutorials/-/knowledge_base/7-0/creating-android-apps-that-use-the-mobile-sdk)

[Creating iOS Apps that Use the Mobile SDK](/develop/tutorials/-/knowledge_base/7-0/creating-ios-apps-that-use-the-mobile-sdk)
