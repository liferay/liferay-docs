# Building the Guestbook Mobile SDK [](id=building-the-guestbook-mobile-sdk-ios)

Once you've deployed the Guestbook portlet, you're ready to build the Guestbook
Mobile SDK. You might be asking yourself, "Why do I have to build a separate
Mobile SDK? Can't I just use the pre-built Mobile SDK that Liferay already
provides?" Fantastic question! The reason is that Liferay's pre-built Mobile SDK
doesn't have the classes it needs to call the Guestbook portlet's remote
services. The pre-built Mobile SDK includes only the framework necessary to make
server calls to the remote services of Liferay Portal's *core* apps. Core apps
(also referred to as *out-of-the-box* apps) are those included with every
Liferay Portal instance. Since you're calling services of an app the default
Mobile SDK doesn't know about (the Guestbook portlet), you must build a Mobile 
SDK that can call its services. Now put on your hard hat, because it's time to 
get building! 

## Building the Mobile SDK [](id=building-the-mobile-sdk)

Liferay provides a Mobile SDK Builder that you can use to build your own Mobile 
SDKs. For the builder to generate the classes that can call a non-core app's 
remote services, those services must be available and accompanied by a Web 
Service Deployment Descriptor (WSDD). To learn how the Guestbook portlet's 
remote services and WSDD were generated, see the article 
[Creating Web Services for Your Application](/develop/tutorials/-/knowledge_base/6-2/creating-web-services-for-your-application). 
Since the Guestbook portlet's web services already exist, you don't need to 
generate them. Just remember that you must generate web services when developing 
your own portlets. 

To build the Guestbook Mobile SDK, first download the Mobile SDK Builder by 
[clicking here](https://github.com/liferay/liferay-mobile-sdk/archive/builder-6.2.0.18.zip). 
Unzip the file to a location on your machine where you want the Mobile SDK 
Builder to reside. This location is purely personal preference; the builder 
works the same no matter where you put it. Once unzipped, the Mobile SDK Builder 
is in the `liferay-mobile-sdk-builder-6.2.0.18` folder. 

Now you're ready to build the Guestbook Mobile SDK. First, you'll use the 
builder to generate a separate module for the Guestbook portlet. The builder 
contains a convenient command line wizard to assist you in this. To start it, 
navigate to the `liferay-mobile-sdk-builder-6.2.0.18` directory and run the 
following command: 

    ./gradlew createModule

The wizard launches and asks you to enter your project's properties. You must 
first provide the `Context` property. This is the context path of the remote 
services the builder will generate classes and methods for. To view your 
Liferay Portal instance's remote service context paths, go to 
[http://localhost:8080/api/jsonws](http://localhost:8080/api/jsonws). 
On the page's upper left, there's a menu for selecting the context path. Select 
*guestbook-portlet*, which is the Guestbook portlet's context path. The UI 
updates to show only the remote services available in the selected context path. 
Return to the terminal and enter `guestbook-portlet` for the `Context` property. 

![Figure 1: The Guestbook Portlet's context path (guestbook-portlet) on the server.](../../../images/remote-services-context.png)

Next, the wizard needs the `Package Name` property. Because the builder also 
builds an Android version of the Mobile SDK, this property defines the Java 
package path for the classes the builder generates. Accept the default value of 
`com.liferay.mobile.android`. The wizard then asks for the `POM Description` 
property. This property also applies to the Android version of the Mobile SDK. 
Since the builder requires it, however, enter `Guestbook SDK`. The following 
screenshot shows these properties entered in the wizard: 

![Figure 2: To build your Mobile SDK, you must enter values for the `Context`, `Package Name`, and `POM Description` properties. The blue values in square brackets are defaults.](../../../images/mobile-sdk-build-wizard.png)

Once you enter the final property, the builder runs and generates a 
`BUILD SUCCESSFUL` message. Now that the builder contains a `guestbook-portlet` 
module, you must generate that module's remote services. To do this, first 
navigate to the following folder: 

    liferay-mobile-sdk-builder-6.2.0.18/modules/guestbook-portlet

Then run the following command:

    ../../gradlew generate

As before, the builder runs and generates a `BUILD SUCCESSFUL` message. Great! 
You're probably wondering what just happened, though. The builder generated the 
classes you'll use in your iOS app to interact with the Guestbook portlet. You 
can find these classes in the builder's following folder: 

    modules/guestbook-portlet/ios/Source/Service/v62

The last folder in this path, `v62`, denotes the Liferay Portal version the 
classes work with. This folder has two subfolders that correspond to each entity 
in the Guestbook portlet: `guestbook` and `entry`. Each subfolder contains an 
Objective-C class header and implementation file for that entity's class 
(`LRGuestbookService_v62` and `LREntryService_v62`, respectively). 

There's one last thing to do before you can use these classes in your iOS app: 
put them in a JAR file. To do this, make sure you're still in the 
`modules/guestbook-portlet` folder on the command line and run the following 
command: 

    ../../gradlew zip

This command generates this ZIP file: 

    modules/guestbook-portlet/build/liferay-guestbook-portlet-ios-sdk-1.0.zip

This ZIP file is the Guestbook Mobile SDK. It contains the service classes 
required to call the Guestbook portlet's remote services. 

Congratulations! You just built the Guestbook Mobile SDK. Now that's an 
accomplishment worth writing in a guestbook. All you need now is an iOS app 
in which to install this Mobile SDK. The next article shows you how to create 
this. 
