# Building the Guestbook Mobile SDK [](id=building-the-guestbook-mobile-sdk)

Once you've deployed the Guestbook portlet to your @product@ instance, you're 
ready to build the Guestbook Mobile SDK. You might be asking yourself, "Why do I 
have to build a separate Mobile SDK? Can't I just use the pre-built Mobile SDK 
that Liferay already provides?" Fantastic question! The reason is that Liferay's 
pre-built Mobile SDK doesn't have the classes it needs to call the Guestbook 
portlet's remote services. The pre-built Mobile SDK includes only the framework 
necessary to make server calls, and the classes required to call the remote 
services of @product@'s *core* apps. Core apps (also referred to as 
*out-of-the-box* apps) are those included with every @product@ instance. You 
must build a separate Mobile SDK to call the remote services of any non-core 
app. Since the Guestbook portlet isn't a core app, you must build a separate 
Mobile SDK that can call its remote services. Now put on your hard hat, because 
it's time to get building! 

## Building the Mobile SDK [](id=building-the-mobile-sdk)

In the Mobile SDK source code, Liferay provides a Mobile SDK Builder that you 
can use to build your own Mobile SDKs. For the builder to generate the classes 
that can call a non-core app's remote services, those services must be available 
and accompanied by a Web Service Deployment Descriptor (WSDD). To learn how the 
Guestbook portlet's remote services and WSDD were generated, see the article 
[Creating Web Services for Your Application](/develop/tutorials/-/knowledge_base/6-2/creating-web-services-for-your-application). 
Since the Guestbook portlet's web services already exist, you don't need to 
generate them. Just remember that you must generate web services when developing 
your own @product@ apps. 

To build the Guestbook Mobile SDK, first download the Mobile SDK's source code. 
You can do so by clicking 
[this link](https://github.com/liferay/liferay-mobile-sdk/archive/android-7.0.5.zip). 
Unzip the file to a location on your machine where you want the Mobile SDK to 
reside. This location is purely personal preference; the builder works the same 
no matter where you put the Mobile SDK's source code. Once unzipped, the Mobile 
SDK's source code is in the `liferay-mobile-sdk-android-7.0.5` folder. 

Now you're ready to build the Guestbook Mobile SDK. The builder contains a 
convenient command line wizard to assist you in building Mobile SDKs. To start 
it, navigate to the `liferay-mobile-sdk-android-7.0.5` directory and run the 
following command: 

    ./gradlew createModule

The wizard launches and asks you to enter your project's properties. You must 
first provide the `Context` property. This is the context path of the remote 
services the builder will generate classes and methods for. To view your 
@product@ instance's remote service context paths, go to 
[http://localhost:8080/api/jsonws](http://localhost:8080/api/jsonws). 
On the page's upper left, there's a menu for selecting the context path. Select 
*gb*, which is the Guestbook portlet's context path. The UI updates to show only 
the remote services available in the selected context path. Return to the 
terminal and enter `gb` for the `Context` property. 

![Figure 1: The Guestbook Portlet's context path (gb) on the server.](../../../images/remote-services-context.png)

Next, the builder needs the `Package Name` property. This is the package path 
for the classes the builder generates. Accept the default value of 
`com.liferay.mobile.android`. The builder then asks for the `POM Description` 
property. Technically, you only need this if you want to publish your Mobile SDK 
to Maven. Since the builder requires it, however, enter `Guestbook SDK`. The 
following screenshot shows these properties entered in the builder: 

![Figure 2: To build your Mobile SDK, you must enter values for the `Context`, `Package Name`, and `POM Description` properties. The blue values in square brackets are defaults.](../../../images/mobile-sdk-build-wizard.png)

Once you enter the final property, the builder runs and generates a 
`BUILD SUCCESSFUL` message. Great! You're probably wondering what just happened, 
though. Obviously the build succeeded, but it doesn't tell you much else. What 
happened is this: the builder generated the source classes you'll use in your 
Android app to interact with the Guestbook portlet. You can find these source 
classes in the following folder of the Mobile SDK's source code: 

    modules/gb/android/src/gen/java

Also note that the source classes are in the package path you specified during 
the build, with an additional folder that denotes the @product@ version they 
work with. The full path to the generated source classes is therefore: 

    modules/gb/android/src/gen/java/com/liferay/mobile/android/v7

This folder has two subfolders that correspond to each entity in the Guestbook 
portlet: `guestbook` and `entry`. Each subfolder contains that entity's source 
class, `GuestbookService` and `EntryService`, respectively. 

There's one last thing to do before you can use these classes in your Android 
app: put them in a JAR file. To do this, navigate to the `modules/gb` folder on 
the command line and run `../../gradlew jar`. This command does two things: 

1.  Generates a JAR file in `modules/gb/build/libs` that contains the Guestbook 
    portlet's service classes. This JAR file is the Guestbook Mobile SDK. 

2.  Generates a custom-built version of Liferay's pre-built Mobile SDK in 
    `liferay-mobile-sdk-android-7.0.5/android/build/libs`. 

Congratulations! You just built the Guestbook Mobile SDK. Now that's an 
accomplishment worth writing in a guestbook. All you need now is an Android app 
in which to install this Mobile SDK. The next article shows you how to create 
this. 
