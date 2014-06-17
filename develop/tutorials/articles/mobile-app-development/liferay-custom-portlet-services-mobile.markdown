## Calling Liferay Services in your Android App [](id=calling-liferay-services-in-your-android-liferay-portal-6-2-dev-guide-08-en)

The Mobile SDK Eclipse plugin makes it easier than ever for you to call Liferay
core services and utilities from your Android app. All you need to do is add the 
Liferay Android Mobile SDK libraries to your project. The Liferay Mobile SDK
Builder that comes with our Eclipse plugin enables you to generate these
libraries. 

---

![Note](../../images/tip-pen-paper.png) **Note:** This configuration capability
is only available for Android apps that were created using the ADT plugin for
Eclipse. The action will not work for Android apps built with Gradle or Maven,
but you can still manually configure your project to use the Mobile SDK. For
more information on this, see the manual Liferay Mobile SDK setup instructions
for
[Android](https://github.com/liferay/liferay-mobile-sdk/tree/master/android#manually)
or
[iOS](https://github.com/liferay/liferay-mobile-sdk/blob/master/ios/README.md#manually).

---

Let's see how easy it is to set up the Liferay Android SDK in your Android app
project. 

1. In Eclipse, import your Android app into an Android application project. 

2. Right-click your app and select *Configure* &rarr;
*Add Liferay Android SDK libraries*. 

In the Eclipse console, the plugin prints a success message indicating that the
Liferay Android SDK libraries were added to the project.

Wasn't that easy? Your Android app now has the Liferay Android SDK libraries,
enabling it to call any of Liferay's core web services! Let's take a look behind
the scenes and discover what happened. 

In your project's `libs` folder, the Liferay Android SDK's `.jar` file and
`.properties` file were added. Furthermore, a JAR file containing the Liferay
Android SDK's source code was added to the project's `src` folder. Lastly, the
Liferay Android SDK library JAR was added as one of your project's *Android
Private Libraries*, making it available in your project's classpath. 

![Figure 8.6: The Mobile SDK Eclipse plugin makes it easy to inject Liferay Android SDK libraries into your app.](../../images/mobile-sdk-jars.png)

Great! Now you know how to set up your Android apps to use Liferay core web
services and Liferay utilities. But how do you access custom portlet services?
Does the Liferay Mobile SDK Eclipse plugin make that possible too? The answer is
an emphatic *YES*. We'll show you how to configure your app to use custom
portlet services next. 

## Using Custom Portlet Services in your Android App [](id=using-custom-portlet-services-in-your-an-liferay-portal-6-2-dev-guide-08-en)

The Liferay Mobile SDK makes it easier to call your custom portlet
services. The Liferay Mobile SDK Builder that comes with our Eclipse plugin
generates libraries that enable you to access your custom portlet's remote JSON
web services. 

You can specify a running Liferay Portal instance in Eclipse, and Eclipse
queries the server for all the remote APIs that are available to generate `.jar`
files to access your custom portlet services. The Liferay Mobile SDK Eclipse
plugin finds your custom services for you to select the ones you want to use in
your app. The Mobile SDK Builder generates a `.jar` file and resource files that
let you access the services in from your Android app. 

Now that you know the basic gist of how the Mobile SDK Eclipse plugin generates
custom portlet service access libraries, let's actually generate them for an
existing Android app. 

Right-click your Android app project in the Package Explorer and select
*Configure* &rarr; *Generate services for Liferay custom portlets*. This opens a
wizard where you'll specify your running portal server. 

![Figure 8.7: The Mobile SDK wizard lets you specify your Liferay portal server connection and conveniently provides the means to test that connection.](../../images/service-generation-wizard.png)

Specify your server's *URL*, *Username*, and *Password*. Notice the *Status* and
*Summary* fields; these fields indicate your running portal instance's status
after clicking the *Check Status* button.

The plugin saves previously configured server instance connection information in
a listing at the bottom so that you can configure your Android app to use any
previously configured portal server. Each server's URL and Last Available
Summary is displayed. 

Once you've checked the status of your server, click *Next*. The plugin queries
the server you specified for all the remote APIs that are available, and
generates for your custom portlet services. 

In the next menu, specify the Java package for the SDK source code that the
builder generates. Then select the custom portlet APIs that you want to access. 

![Figure 8.8: Liferay's Mobile SDK Builder queries all remote APIs that are available for generating SDK libraries to provide access to custom portlet services.](../../images/choosing-apis-and-packages-wizard.png)

For demonstration purposes, we'll specify the Java package as
`com.liferay.custom` and select an arbitrary custom API (such as the
*opensocial-portlet* &rarr; *gadget* custom API shown in the figure below). 

![Figure 8.9: The Mobile SDK Eclipse plugin injects the Android app with the appropriate `.jar` and `.properties` files to use in accessing the custom portlet APIs.](../../images/injected-jars-mobile-sdk.png)

Notice that you have the standard and custom `.jar` and `.properties` files. When
generating custom services, Eclipse adds both the standard JAR file used for
accessing Liferay core services and utilities and a custom JAR file for
accessing your custom portlet services. 

You've generated custom services for your Liferay portlets! Now your app can
access Liferay core services, Liferay utilities, *and* your custom portlet
services. 

Now that your Android app has the access it needs to use Liferay and your custom
portlets, let's take an in-depth look at using the Android SDK to invoke their
services. 

## Using the Android SDK [](id=using-the-android-sdk-liferay-portal-6-2-dev-guide-08-en)

Suppose you're creating an Android app, and you'd like to access some of
Liferay's core services. All you need to do is download the SDK and put it in
your classpath, and you can access the services immediately. If you want to
invoke custom portlet web services, you'll need to generate client libraries
for them. You can learn more about generating these libraries by reading the
[Building an SDK for Custom Portlet Services](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/build-sdk-for-custom-portlet-services-liferay-portal-6-2-dev-guide-en)
section. 

If you're developing in an Eclipse environment and are using the Liferay Mobile
SDK Eclipse plugin, you can skip the next section. Otherwise, continue onto the
next section where we'll get started by downloading the Android SDK and
configuring it in your Android environment! 

### Manually Setting Up the Android SDK [](id=manually-setting-up-the-android-sdk-liferay-portal-6-2-dev-guide-08-en)

As we stated earlier, you'll need to download the latest version of
`liferay-android-sdk.jar`. In addition, if you want to debug the SDK source
code, you can download `liferay-android-sdk-sources.jar` and attach the source
code to your IDE project. You can download these JAR files from the [Liferay
Mobile
SDK](https://www.liferay.com/community/liferay-projects/liferay-mobile-sdk)
project page.

Once you've downloaded your JAR file, copy it into the `/libs` directory of
your Android project. Android Developer Tools should add this JAR automatically 
to your classpath. If you're using a different IDE, make sure this JAR is added
to the project classpath. Now, you'll be able to import the SDK classes for your
app to use. 

Great! Now let's start accessing Liferay services from your app. 

## Using the iOS SDK [](id=use-ios-sdk-to-call-services-liferay-portal-6-2-dev-guide-en)

You've just created a custom iOS app and now want your app to access Liferay
services. How do you access Liferay services from an iOS mobile app? Use
Liferay's iOS SDK, of course. If you'd like to invoke remote web services,
you'll need to generate the client libraries. You can learn more about the SDK
builder and how to generate client libraries by reading the Custom Portlet
[Building an SDK for Custom Portlet Services](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/build-sdk-for-custom-portlet-services-liferay-portal-6-2-dev-guide-en)
section. 

Let's get started by downloading the iOS SDK and configuring it in your iOS
environment! 

### Setting Up the iOS SDK [](id=setup-liferay-mobile-sdk-for-ios-liferay-portal-6-2-dev-guide-en)

To install the iOS SDK to your machine, you'll need to download the latest
version of `liferay-ios-sdk.zip`. You can download this file from the [Liferay
Mobile
SDK](https://www.liferay.com/community/liferay-projects/liferay-mobile-sdk)
project page. These installation instructions assume you're using the XCode
developer tool provided by Apple, which can be downloaded from the [Mac App
Store](https://itunes.apple.com/us/app/xcode/id497799835?ls=1&mt=12).

After you've downloaded the Zip file, unzip it into your XCode project. Within
XCode, right-click on your project and click *Add Files to <PROJECT_NAME>*.
Then, add both `core` and `v62` folders. The `v62` folder name can change for
each Liferay version. In this example, the Mobile SDK is built for Liferay 6.2.

Awesome! Let's learn how to configure the SDK next.
