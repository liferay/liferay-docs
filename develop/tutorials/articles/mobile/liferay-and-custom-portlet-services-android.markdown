# Making Liferay and Custom Portlet Services Available in Your Android App [](id=making-liferay-and-custom-portlet-services-available-in-your-android-app)

Suppose you'd like your Android app to access some of Liferay's core services. 
After all, you undoubtedly have lots of cool stuff in your portal. This is where 
the Liferay Mobile SDK comes in. The Liferay Mobile SDK lets you access 
Liferay's core services so your Android app can get what it needs from your 
portal. You can also use the Liferay Mobile SDK to invoke the web services of 
your custom portlets. Read on to find out how!

## Calling Liferay Services in your Android App [](id=calling-liferay-services-in-your-android-app)

The Mobile SDK Eclipse plugin makes it easier than ever for you to call Liferay
core services and utilities from your Android app. All you need to do is add the 
Liferay Android Mobile SDK libraries to your project. The Liferay Mobile SDK
Builder that comes with our Eclipse plugin enables you to generate these
libraries. 

+$$$

**Note:** This section assumes that you
created your Android project using the ADT plugin for Eclipse. If you didn't
create the project using the ADT plugin or if it uses Gradle or Maven, see the
instructions for
[Setting Up the Mobile SDK Manually](/develop/tutorials/-/knowledge_base/6-2/setting-up-the-mobile-sdk-manually).

$$$

Use these steps to set up the Liferay Android SDK in your Android app project. 

1. In Eclipse, import your Android app into an Android application project. 

2. Right-click your app and select *Configure* &rarr;
*Add Liferay Android SDK libraries*. 

In the Eclipse console, the plugin prints a success message indicating that the
Liferay Android SDK libraries were added to the project.

Wasn't that easy? Your Android app now has the Liferay Android SDK libraries,
enabling it to call any of Liferay's core web services! Next, take a look behind
the scenes and discover what happened. 

In your project's `libs` folder, the Liferay Android SDK's `.jar` file and
`.properties` file were added. Furthermore, a JAR file containing the Liferay
Android SDK's source code was added to the project's `src` folder. Lastly, the
Liferay Android SDK library JAR was added as one of your project's *Android
Private Libraries*, making it available in your project's classpath. 

![Figure 1: The Mobile SDK Eclipse plugin makes it easy to inject Liferay Android SDK libraries into your app.](../../images/mobile-sdk-jars.png)

Great! Now you know how to set up your Android apps to use Liferay core web
services and Liferay utilities. But how do you access custom portlet services?
Does the Liferay Mobile SDK Eclipse plugin make that possible too? The answer is
an emphatic *YES*. You'll learn how to configure your app to use custom portlet 
services next. 

## Using Custom Portlet Services in your Android App [](id=using-custom-portlet-services-in-your-android-app)

The Liferay Mobile SDK makes it easier to call your custom portlet
services. The Liferay Mobile SDK Builder that comes with your Eclipse plugin
generates libraries that enable you to access your custom portlet's remote JSON
web services. 
<!-- 
Change the link in this Note to the tutorial: "Building Mobile SDKs" once the 
header id's are generated.
-->

+$$$

**Note:** You can also use the Liferay
Mobile SDK Builder via command line. Read
[Building Mobile SDKs](/develop/tutorials/-/knowledge_base/6-2/building-mobile-sdks)
for more details.

$$$

You can specify a running Liferay Portal instance in Eclipse, and Eclipse
queries the server for all the remote APIs that are available to generate `.jar`
files to access your custom portlet services. The Liferay Mobile SDK Eclipse
plugin finds your custom services for you to select the ones you want to use in
your app. The Mobile SDK Builder generates a `.jar` file and resource files that
let you access the services from your Android app. 

Now that you know the basic gist of how the Mobile SDK Eclipse plugin generates
custom portlet service access libraries, you can go ahead and actually generate 
them for an existing Android app. 

Right-click your Android app project in the Package Explorer and select
*Configure* &rarr; *Generate services for Liferay custom portlets*. This opens a
wizard where you'll specify your running portal server. 

![Figure 2: The Mobile SDK wizard lets you specify your Liferay portal server connection and conveniently provides the means to test that connection.](../../images/service-generation-wizard.png)

Specify your server's *URL*, *Username*, and *Password*. Notice the *Status* and
*Summary* fields; these fields indicate your running portal instance's status
after clicking the *Check Status* button.

The plugin saves previously configured server instance connection information in
a listing at the bottom so that you can configure your Android app to use any
previously configured portal server. Each server's URL and Last Available
Summary is displayed. 

Once you've checked the status of your server, click *Next*. The plugin queries
the server you specified for all the remote APIs that are available, and
generates your custom portlet services. 

In the next menu, specify the Java package for the SDK source code that the
builder generates. Then select the custom portlet APIs that you want to access. 

![Figure 3: Liferay's Mobile SDK Builder queries all remote APIs that are available for generating SDK libraries to provide access to custom portlet services.](../../images/choosing-apis-and-packages-wizard.png)

For demonstration purposes, you can specify the Java package as 
`com.liferay.custom` and select an arbitrary custom API (such as the
*opensocial-portlet* &rarr; *gadget* custom API shown in the figure below). 

![Figure 4: The Mobile SDK Eclipse plugin injects the Android app with the appropriate `.jar` and `.properties` files to use in accessing the custom portlet APIs.](../../images/injected-jars-mobile-sdk.png)

Notice that you have the standard and custom `.jar` and `.properties` files. 
When generating custom services, Eclipse adds both the standard JAR file used 
for accessing Liferay core services and utilities and a custom JAR file for 
accessing your custom portlet services. 

Congratulations! You've generated custom services for your Liferay portlets! Now 
your app can access Liferay core services, Liferay utilities, *and* your custom 
portlet services. 

## Related Topics [](id=related-topics)

[Creating iOS Apps that Use Liferay](/develop/tutorials/-/knowledge_base/6-2/creating-ios-apps-that-use-liferay)

[Liferay Mobile SDK Builder](/develop/tutorials/-/knowledge_base/6-2/liferay-mobile-sdk-builder)

[Service Builder and Services](/develop/tutorials/-/knowledge_base/6-2/service-builder)

[Developing Plugins with Liferay IDE](/develop/tutorials/-/knowledge_base/6-2/liferay-ide)
