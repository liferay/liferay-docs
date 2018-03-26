# Building the Guestbook Mobile SDK [](id=building-the-guestbook-mobile-sdk-ios)

Once you've deployed the Guestbook portlet, you're ready to build the Guestbook
Mobile SDK. You might be asking yourself, "Why do I have to build a separate
Mobile SDK? Can't I just use the pre-built Mobile SDK that Liferay already
provides?" Fantastic question! The reason is that Liferay's pre-built Mobile SDK
doesn't have the classes it needs to call the Guestbook portlet's remote
services. The pre-built Mobile SDK includes only the framework necessary to make
server calls to the remote services of @product@'s *core* apps. Core apps (also 
referred to as *out-of-the-box* apps) are those included with every @product@ 
instance. Since you're calling services of an app the default Mobile SDK doesn't 
know about (the Guestbook portlet), you must build a Mobile SDK that can call 
its services. Now put on your hard hat, because it's time to get building! 

## Building the Mobile SDK [](id=building-the-mobile-sdk)

In the Mobile SDK source code, Liferay provides a Mobile SDK Builder that you 
can use to build your own Mobile SDKs. For the builder to generate the classes 
that can call a non-core app's remote services, those services must be available 
and accompanied by a Web Service Deployment Descriptor (WSDD). To learn how the 
Guestbook portlet's remote services and WSDD were generated, see the section 
[Generating Web Services](/develop/tutorials/-/knowledge_base/7-0/generating-web-services) 
in the web application Learning Path. Since the Guestbook portlet's web services 
already exist, you don't need to generate them. Just remember that you must 
generate web services when developing your own portlets. 

1.  [Download the Mobile SDK's source code](https://github.com/liferay/liferay-mobile-sdk/archive/ios-7.0.13.zip) 
    and unzip the file to a location on your machine where you want the Mobile 
    SDK to reside. This location is purely personal preference; the builder 
    works the same no matter where you put the Mobile SDK's source code. Once 
    unzipped, the Mobile SDK's source code is in the folder 
    `liferay-mobile-sdk-ios-7.0.13`. 

2.  Now you're ready to build the Guestbook Mobile SDK. The builder contains a 
    convenient command line wizard to assist you in building Mobile SDKs. To 
    start it, navigate to the `liferay-mobile-sdk-ios-7.0.13` folder and run the 
    following command: 

        ./gradlew createModule

    The wizard launches and asks you to enter your project's properties. You'll 
    do this next.

3.  You must first provide the `Context` property. This is the context path of 
    the remote services the builder will generate classes and methods for. To 
    view your @product@ instance's remote service context paths, go to 
    [http://localhost:8080/api/jsonws](http://localhost:8080/api/jsonws). 
    On the page's upper left, there's a menu for selecting the context path. 
    Select *gb*, which is the Guestbook portlet's context path. The UI updates 
    to show only the remote services available in the selected context path. 
    Return to the terminal and enter `gb` for the `Context` property. 

    ![Figure 1: The Guestbook Portlet's context path (gb) on the server.](../../../images/remote-services-context.png)

4.  Next, the wizard needs the `Package Name` property. Because the builder also 
    builds an Android version of the Mobile SDK, this property defines the Java 
    package path for the classes the builder generates. Accept the default value 
    of `com.liferay.mobile.android`. 

5.  The wizard then asks for the `POM Description` property. This property also 
    applies to the Android version of the Mobile SDK. Since the builder requires 
    it, however, enter `Guestbook SDK`. The following screenshot shows these 
    properties entered in the wizard: 

    ![Figure 2: To build your Mobile SDK, you must enter values for the `Context`, `Package Name`, and `POM Description` properties. The blue values in square brackets are defaults.](../../../images/mobile-sdk-build-wizard.png)

    Once you enter the final property, the builder runs and generates a 
    `BUILD SUCCESSFUL` message.

6.  Now that the builder contains a `gb` module, you must generate that module's 
    remote services. To do this, first navigate to this folder: 

        liferay-mobile-sdk-ios-7.0.13/modules/gb

    Then run this command: 

        ../../gradlew generate

    As before, the builder runs and generates a `BUILD SUCCESSFUL` message. 
    Great! You're probably wondering what just happened, though. The builder 
    generated the source classes you'll use in your iOS app to interact with the 
    Guestbook portlet. You can find these source classes in the following folder 
    of the Mobile SDK's source code: 

        modules/gb/ios/Source/Service/v7

    The last folder in this path, `v7`, denotes the @product@ version the 
    classes work with. This folder has two subfolders that correspond to each 
    entity in the Guestbook portlet: `guestbook` and `entry`. Each subfolder 
    contains an Objective-C class header and implementation file for that 
    entity's class (`LRGuestbookService_v7` and `LREntryService_v7`, 
    respectively). 

7.  There's one last thing to do before you can use these classes in your iOS 
    app: put them in a JAR file. To do this, make sure you're still in the 
    `modules/gb` folder on the command line and run this command: 

        ../../gradlew zip

    This command generates this ZIP file: 

        modules/gb/build/liferay-gb-ios-sdk-1.0.zip

    This ZIP file is the Guestbook Mobile SDK. It contains the service classes 
    required to call the Guestbook portlet's remote services. 

Congratulations! You just built the Guestbook Mobile SDK. Now that's an 
accomplishment worth writing in a guestbook. All you need now is an iOS app to 
install this Mobile SDK in. The next article shows you how to create it. 
