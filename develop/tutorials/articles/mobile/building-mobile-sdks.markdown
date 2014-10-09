# Building Mobile SDKs [](id=building-mobile-sdks)

The Liferay Mobile SDK project comes with an SDK Builder that generates the 
Liferay Mobile SDK for the Android and iOS platforms. Think of it as a Service
Builder on the client side. The SDK Builder generates client libraries that 
allow your native mobile apps to invoke remote web services of a portal instance 
and any of its custom portlets built with Liferay's Service Builder. 

This tutorial covers how to build a Liferay Mobile SDK for Android and iOS. 
You'll begin by making sure the remote services are configured for any custom 
portlets you have. Go ahead and get started! 

## Configuring your Portlet's Remote Services 

For the SDK Builder to discover a portlet's remote services, the services must 
be available and accompanied by a Web Service Deployment Descriptor (WSDD). 

If you're in the process of developing the portlet, see
[Writing a Data-Driven Application](/develop/learning-paths/-/knowledge_base/6-2/writing-a-data-driven-application)
for in-depth instructions on implementing remote services.

The following steps outline how to implement remote services for your portlet 
and make them available for the SDK Builder to discover. 

1. Set `remote-service="true"` in your portlet's `service.xml` file and run 
   `ant build-service`.

2. Implement your remote services in the generated `[Entity]ServiceImpl.java` 
   source files and run Service Builder again. 

3. Build your WSDD using Service Builder. The SDK Builder relies on the WSDD 
   to discover your portlet's remote services, so make sure to build the WSDD 
   before deploying your portlet. 

4. Deploy your portlet with its WSDD.

Now you're ready to use the SDK Builder to generate a custom portlet SDK for 
your mobile app! 

## Setting Up the SDK Builder  

The Liferay Mobile SDK takes care of making JSON Web Services 
requests to your portlet. In this section you'll configure the SDK builder for 
building your custom portlet SDK. 

### Download the Liferay Mobile SDK Project 

You need to have the [Liferay Mobile SDK project](https://github.com/liferay/liferay-mobile-sdk) on your local machine to use 
the SDK Builder. If you haven't done so already, download the Mobile SDK project 
using Git.

	git clone git@github.com:liferay/liferay-mobile-sdk.git

Alternatively, you can use the SDK Builder as a standalone library. Its `jar` is 
available on the JCenter and Maven Central repositories. 

	<dependency>
		<groupId>com.liferay.mobile</groupId>
		<artifactId>liferay-sdk-builder</artifactId>
		<version>6.2.0.1</version>
	</dependency>

Check the main method of `src/main/java/com/liferay/mobile/sdk/SDKBuilder.java` 
to see how this class can be called from the command line. 

Now you're ready to configure the SDK builder! 

### Configure the SDK Builder 

After you've downloaded the Liferay Mobile SDK project, you need to set a few
properties before actually running a build. The Liferay Mobile SDK uses a build
tool called Gradle. Since the Liferay Mobile SDK project already contains the
Gradle Wrapper (`gradlew`), you don't need to install Gradle's separately. You
must, however, tell the builder how to communicate with your portlet and how to
create an SDK specific to your mobile platform. You do this by setting the below
properties in the `gradle.properties` file.

+$$$

**Note:** If you don't want to change 
  `gradle.properties` directly, you can also copy it to `~/.gradle` and modify 
  it there. Alternatively, you can also edit the `gradle.properties` inside each 
  platform folder (`ios/` or `android/`).

$$$

Here are the important properties to set.

* `url` - The URL to your Liferay instance.

* `context` - Your portlet's web context. For example, you'd set the web context
to `context=calendar-portlet` if you wanted to generate an SDK for Liferay's
Calendar portlet. This is because the Calendar portlet is generally deployed to
the `calendar-portlet` context. Under the hood, the SDK Builder will try to
access `http://hostname:port/calendar-portlet/api/jsonws?discover` to find out
which services are available for this portlet. Within a browser make sure the
URL works before running the SDK. If the portlets services are not accessible at
the URL, you may have forgotten to build the WSDD for the portlet.

* `filter` - Specifies your portlet's entities whose services to access. A
blank value specifies the services of all the portlet's entities. For 
example, the Calendar portlet has entities such as `CalendarBooking` and
`CalendarResource`. To generate an SDK for only the `CalendarBooking`
entity, set the filter `calendarbooking`. The SDK Builder sends requests to 
`http://hostname:port/calendar-portlet/api/jsonws?discover=/calendarbooking/*`.
If you specify no filter value by setting `filter=`, the remote services of 
*all* the portlet's entities are made available to your mobile SDK. 

* `package` - On Android, this is the package to which your SDK's classes are
written. Note that iOS doesn't use packages. The Liferay Portal version is
appended to the end of the package name by the SDK Builder. For example, if you
specify `com.liferay.mobile.android` as your package,
`com.liferay.mobile.android.v62` is the resulting package name. Appending the
Liferay Portal version prevents collisions between classes with the same names
written for different versions of Liferay Portal. 

* `destination` - Specifies the root folder in which to save your generated
files. The default locations for Android and iOS are `android/src/gen/java` and
`ios/src/gen`, respectively. 
	
* `version` - The version number you specify here is appended to the `jar` and 
`zip` file names. 

Here's an example of these settings for a portlet with the web context value 
`my-portlet`: 

    url=http://localhost:8080
    context=my-portlet
    filter=
    package=com.mycompany.mobile.android
    destination=gen

Now that you've configured the SDK Builder, you're ready to build a
platform-specific SDK for your portlet's remote services. 

## Building Platform-Specific Mobile SDKs

With the SDK Builder, you can generate Mobile SDK `jar` files for Android and
iOS apps to use. You can provide the best of both worlds!  

### Building a Liferay Android SDK 

To build the service related source files for your Liferay Android SDK, run the
following command from the `android/` folder. 

    ../gradlew buildService

To build a `.jar` file containing the generated service and utility classes, run
the following command. 

    ../gradlew jar

The `liferay-android-sdk-[version].jar` file is written to your 
`android/build/libs` folder. The value of the `version` property you set in 
`gradle.properties` is used in place of `[version]` in the `jar` file name. 
You're now ready to use the `jar` file in your Android project! There are no 
external dependencies.

### Building a Liferay iOS SDK 

To build the service related source files for your Liferay iOS SDK, run the
following command from the `ios/` folder. 

	../gradlew buildService

To build a `.zip` file containing the generated service and utility classes, run
the following command.

    ../gradlew zip

The `liferay-ios-sdk-[version].zip` file is written to your `ios/build` folder. 
The value of the `version` property you set in `gradle.properties` is used in 
place of `[version]` in the `zip` file name. You're now ready to use the 
contents of your `liferay-ios-sdk-[version].zip` file in your iOS project! 
Simply unzip its contents and add all of the files to your XCode project. The 
SDK is free of any external dependencies.

You've just built a Liferay Android SDK and a Liferay iOS SDK for your mobile
apps to call your custom portlet services. You're a mobile app rock star! 

## Related Topics

[Creating Android Apps that Use Liferay](/develop/tutorials/-/creating-android-apps-that-use-liferay)

[Creating iOS Apps that Use Liferay](/develop/tutorials/-/knowledge_base/6-2/creating-ios-apps-that-use-liferay)

[Service Builder and Services](/develop/tutorials/-/knowledge_base/6-2/service-builder)

