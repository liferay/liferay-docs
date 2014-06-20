# Building Mobile SDKs 

The Liferay Mobile SDK project comes with an SDK Builder that generates the 
Liferay Mobile SDK for the Android and iOS platforms. Think of it as a Service
Builder on the client side. The SDK Builder generates client libraries that 
allow your native mobile apps to invoke remote web services of a portal instance 
and any of its custom portlets built with Liferay's Service Builder. 

This tutorial covers how to build a Liferay Mobile SDK for Android and iOS. 
You'll begin by making sure the remote services are configured for any custom 
portlets you have. Go ahead and get started!

<!-- Add links to service builder tutorials once they exist. -Nick -->

## Configuring your Portlet's Remote Services 

For the SDK Builder to discover a portlet's remote services, the services must 
be available and accompanied by a Web Service Deployment Descriptor (WSDD).

<!--
If you're in the process of developing the portlet, see [Generating Your Service
Layer](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/generating-your-service-layer-liferay-portal-6-2-dev-guide-04-en)
for in-depth instructions on implementing remote services.
-->

The following steps outline how to implement remote services for your portlet
and make them available for the SDK Builder to discover. 

1. Set `remote-service="true"` in your portlet's `service.xml` file and run 
   `ant build-service`.

2. Implement your remote services in the generated `[Entity]ServiceImpl.java` 
   source files and run `ant build-service` again. 

3. Run `ant build-wsdd` to build your WSDD. The SDK Builder relies on the WSDD 
   to discover your portlet's remote services, so make sure to run 
   `ant build-wsdd` before deploying your portlet.

4. Run `ant deploy` to deploy your portlet with its WSDD.

Now you're ready to use the SDK Builder to generate a custom portlet SDK for 
your mobile app! The Liferay Mobile SDK takes care of making JSON Web Services 
requests to your portlet. Next, you'll configure the SDK builder for building 
your custom portlet SDK. 

## Setting Up the SDK Builder  

### Download the Liferay Mobile SDK Project

You need to have the [Liferay Mobile SDK project](https://github.com/liferay/liferay-mobile-sdk) on your local machine to use 
the SDK Builder. If you haven't done so already, download the Mobile SDK project 
using Git.

```sh
git clone git@github.com:liferay/liferay-mobile-sdk.git
```

Alternatively, you can use the SDK Builder as a standalone library, its `jar` is 
available on the JCenter and Maven Central repositories.

```xml
<dependency>
    <groupId>com.liferay.mobile</groupId>
    <artifactId>liferay-sdk-builder</artifactId>
    <version>6.2.0.1</version>
</dependency>
```

Check the main method of `src/main/java/com/liferay/mobile/sdk/SDKBuilder.java` 
to see how this class can be called from the command line. 

### Configure the SDK Builder 

After you have downloaded the Liferay Mobile SDK project source code, follow 
these steps to run it with Gradle. 

Since the Liferay Mobile SDK project already contains the Gradle Wrapper 
(`gradlew`), you don't need to install Gradle's separately. However, before you 
run the SDK builder you must set some properties. The builder needs to 
communicate with your portlet and create an SDK specific to your mobile 
platform. Follow these steps to set those properties. 

1. Edit `gradle.properties`, if you don't want to change that file directly,
you can also copy it to `~/.gradle` and modify it there. Alternatively, you can
also edit the `gradle.properties` inside each platform folder (ios/ or android/).

2. Here are the important properties to set:

	* `url` - The URL to your Liferay instance.

	* `context` - Your portlet's web context. Say for example you are generating
	an SDK for Liferay's Calendar portlet, which is generally deployed to the
	`calendar-portlet` context, then you should set your context value to
	`context=calendar-portlet`. Under the hood, the SDK Builder will try to
	access `http://localhost:8080/calendar-portlet/api/jsonws?discover` to find
	out which services are available for this portlet. Check in a browser if
	this URL is working before running the SDK. If it's not running, you may
	have forgotten to run `ant build-wsdd` on the portlet.

	* `filter` - Specifies your portlet's entities whose services to access; a
	blank value indicates the services of all of the portlet's entities. For
	example, the Calendar portlet has entities such as `CalendarBooking` and
	`CalendarResource`. To generate an SDK for only the `CalendarBooking`
	entity, set the filter's value to `calendarbooking`, all in lowercase. The
	SDK Builder will then make requests to the
	`http://localhost:8080/calendar-portlet/api/jsonws?discover=/calendarbooking/*`.
	If you set `filter=`, specifying no filter value, the remote services of
	*all* of the portlet's entities will be made available to your mobile SDK.

	* `package` - On Android, this is the package to which your SDK's classes
	are written. The iOS platform does not use packages. Note, that the Liferay
	Portal version is appended to the end of the package name. So, if you
	specified `com.liferay.mobile.android` as your package, the SDK
	Builder appends the Liferay Portal version (e.g., `v62`) to it, 
	like `com.liferay.mobile.android.v62`. Appending the Liferay Portal version
	prevents collisions between classes with the same names written for
	different versions of Liferay Portal.

	* `destination` - Specifies the root folder in which to save your generated
	files. On Android, by default, the files are saved to `android/src/gen/java`.
	On iOS, the files are saved by default to `ios/src/gen`.
	
	* `version` - The version number is appended to the jar and zip file names,
	see more about that in the following sections.

Here's an example of configuring the SDK Builder to generate a mobile SDK for
a portlet with a web context value `my-portlet`:

    url=http://localhost:8080
    context=my-portlet
    filter=
    package=com.mycompany.mobile.android
    destination=gen

Now that you've configured the SDK Builder, you're ready to build a platform
specific SDK for your portlet's remote services. 

## Building a Liferay Android SDK 

To build the service related source files for your Liferay Android SDK, run the
following command from the `android/` folder:

    ../gradlew buildService

The source files are written to the `android/src/gen/java` folder by default.

To build a `.jar` file containing the generated service and utility classes, run
the following command:

    ../gradlew jar

The `liferay-android-sdk-[version].jar` file is written to your `android/build/libs`
folder. You're ready to use the `liferay-android-sdk-[version].jar` in your
Android project, there are no external dependencies.

As you can see, the `version` property defined in `gradle.properties` is
appended to the jar file name.

To learn how to use the Liferay Android SDK in your mobile app, see the
[Liferay Android SDK documentation](../android/README.md).

## Building a Liferay iOS SDK 

To build the service related source files for your Liferay iOS SDK, run the
following command from the `ios/` folder:

	../gradlew buildService

The source files are written to your `ios/src/gen` folder by default.

To build a `.zip` file containing the generated service and utility classes, run
the following command:

    ../gradlew zip

The `liferay-ios-sdk-[version].zip` file is written to your `ios/build` folder.
You're ready to use the contents of your `liferay-ios-sdk-[version].zip` file in
your iOS project. Simply unzip its contents and add all of the files to your
XCode project. The SDK is free of any external dependencies.

As you can see, the `version` property defined in `gradle.properties` is
appended to the zip file name.

To learn how to use the Liferay iOS SDK in your mobile app, see the
[Liferay iOS SDK documentation](../ios/README.md).

## Next Steps


