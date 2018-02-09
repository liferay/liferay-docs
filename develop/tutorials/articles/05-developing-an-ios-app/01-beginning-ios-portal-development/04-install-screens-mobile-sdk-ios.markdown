# Installing Liferay Screens and the Guestbook Mobile SDK [](id=installing-liferay-screens-and-the-guestbook-mobile-sdk)

For your iOS app to work with the Guestbook portlet, you must install the 
following in your iOS project: 

-   **Liferay's pre-built Mobile SDK:** This Mobile SDK contains the classes 
    that call @product@'s core remote services. It also contains the framework 
    necessary for any Mobile SDK to make server calls. 

-   **The Guestbook Mobile SDK:** This Mobile SDK contains only the classes that 
    call the Guestbook portlet's remote services. 

-   **Liferay Screens:** Screens contains the Screenlet framework and 
    [several built-in Screenlets](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-ios) 
    like Login Screenlet. Because these built-in Screenlets work with 
    @product@'s core apps, they make their server calls with Liferay's pre-built
    Mobile SDK. Note that all Screenlets, including those that make server calls
    with a custom-built Mobile SDK, must use the framework in Liferay's 
    pre-built Mobile SDK to issue their calls. 

Since Liferay's pre-built Mobile SDK is a dependency of Liferay Screens, 
installing Screens automatically installs this Mobile SDK. You must, however, 
install the Guestbook Mobile SDK manually. 

This article walks you through the installation of the Guestbook Mobile SDK and 
Liferay Screens. When you finish, you'll be ready to start developing the app. 

## Anatomy of the Liferay Guestbook iOS Project [](id=anatomy-of-the-liferay-guestbook-ios-project)

Before getting started, you should learn a couple of terms this Learning Path 
uses when referring to the project's structure. Knowing these terms ensures that 
you know where to add folders and files in the project. In Xcode's Project 
navigator, there are two *Liferay Guestbook* items: 

1.  **The root project:** This is the first item in the Project navigator. It 
    contains all other items in the project, and is labeled with a blue 
    application document icon. The root project corresponds with a folder in 
    your file system that this Learning Path refers to as the *root project 
    folder*. For example, the root project folder for the Liferay Guestbook 
    project is `Liferay Guestbook`. 

2.  **The Liferay Guestbook folder:** This is immediately under the root 
    project. It contains the app's files, and is labeled with a manila folder 
    icon. Even though this folder shares a name with the root project folder on 
    your file system, it **is not** the same thing. The root project folder 
    contains this `Liferay Guestbook` folder. 

![Figure 1: The root project and Liferay Guestbook folder are labeled in this screenshot.](../../../images/ios-lp-project-structure.png)

![Figure 2: On your file system, the `Liferay Guestbook` root project folder contains the app's `Liferay Guestbook` folder. The latter is selected in this screenshot.](../../../images/ios-lp-project-file-system.png)

It's important not to confuse these two items. If you're ever confused about 
where things should go, 
[click here](https://github.com/liferay/liferay-docs/tree/7.0.x/develop/tutorials/code/mobile/ios/screenlets-app/Liferay%20Guestbook) 
to see the finished app in GitHub.

Now you're ready to install Liferay Screens! 

## Installing Liferay Screens [](id=installing-liferay-screens)

You'll use 
[CocoaPods](https://cocoapods.org/) 
to install Liferay Screens. 
[Click here](https://guides.cocoapods.org/using/getting-started.html) 
for instructions on installing CocoaPods. After installing it, use these steps 
to install Screens: 

1.  In your root project's folder, create a file named `Podfile` that contains 
    the following: 

        source 'https://github.com/CocoaPods/Specs.git'

        platform :ios, '9.0'
        use_frameworks!

        target "Liferay Guestbook" do
            pod 'LiferayScreens', '3.0.2'
        end

        post_install do |installer|
          incompatiblePods = [
            'Cosmos',
            'CryptoSwift',
            'KeychainAccess',
            'Liferay-iOS-SDK',
            'Liferay-OAuth',
            'LiferayScreens',
            'Kingfisher'
          ]

          installer.pods_project.targets.each do |target|
            if incompatiblePods.include? target.name
              target.build_configurations.each do |config|
                config.build_settings['SWIFT_VERSION'] = '3.2'
              end
            end
            target.build_configurations.each do |config|
                config.build_settings['CONFIGURATION_BUILD_DIR'] = '$PODS_CONFIGURATION_BUILD_DIR'
            end
          end
        end

    This adds Liferay Screens 3.0.2 (the most recent version at the time this 
    Learning Path was published) as a dependency. Since Screens 3.0.2 is 
    incompatible with Swift 4, this `Podfile` also specifies that Screens and 
    several of its dependencies (`incompatiblePods`) should be compiled by Swift 
    3.2. This lets you develop the app in Swift 4, while Screens itself is 
    compiled in Swift 3.2.

    Also note the setting for `CONFIGURATION_BUILD_DIR`. This is a workaround 
    for a benign bug that causes Screenlet previews to fail in Interface 
    Builder.

2.  On the terminal, navigate to your root project's folder and run this 
    command: 

        pod repo update

    This ensures you have the latest version of the CocoaPods repository on your 
    machine. Note that this command can take a while to run. 

3.  Still in your root project's folder in the terminal, run this command: 

        pod install

    This installs the Liferay Screens as specified in your `Podfile`. Once this 
    completes, quit Xcode and reopen your project by using the 
    `LiferayGuestbook.xcworkspace` file in your root project's folder. From now 
    on, you must use this file to open your project. 

Great! You just installed Liferay Screens and the Liferay Mobile SDK! Next, 
you'll install the Guestbook Mobile SDK. 

## Installing the Guestbook Mobile SDK [](id=installing-the-guestbook-mobile-sdk)

To install the Guestbook Mobile SDK, you must add its service classes to your 
project. Recall that these service classes are Objective-C. 
[To use them from your project's Swift code](https://developer.apple.com/library/ios/documentation/Swift/Conceptual/BuildingCocoaApps/MixandMatch.html), 
you must also add and configure an Objective-C bridging header. You'll do these 
things now: 

1.  Recall that you created the following ZIP file containing the Guestbook 
    Mobile SDK: 

        modules/gb/build/liferay-gb-ios-sdk-1.0.zip

    Unzip this file to a location of your choosing on your machine. This creates 
    the following directory hierarchy: 

    ![Figure 3: The Guestbook Mobile SDK's Objective-C classes unzip to this folder structure.](../../../images/ios-lp-sdk-structure.png)

    This should look familiar. It's the same `Service` folder, contents and all, 
    from the Guestbook Mobile SDK you built earlier. 

2.  To install the service classes in your project, drag the `v7` folder from 
    your Finder into your Xcode project, directly under the root project. In the 
    dialog that appears, make sure you select the following items, and then 
    click *Finish*: 

    ![Figure 4: When adding the Guestbook Mobile SDK to your project, select these options and then click *Finish*.](../../../images/ios-lp-add-sdk.png)

    ![Figure 5: Your project structure should look like this after adding the Guestbook SDK.](../../../images/ios-lp-post-sdk-install.png)

3.  The `v7` folder and its contents are now inside your Xcode project. Now you 
    must change each Objective-C class header file in the Guestbook Mobile SDK 
    to always import the Liferay Mobile SDK framework. This is necessary because 
    you used `use_frameworks!` in your `Podfile`. 

    In `LREntryService_v7.h` and `LRGuestbookService_v7.h`, replace the 
    `#if ... #endif` statement with `@import LRMobileSDK;`. Don't worry if Xcode 
    doesn't recognize this import--you'll fix this shortly by adding and 
    configuring an Objective-C bridging header in your project. 

4.  In Xcode, for each `*.m` file in the Guestbook Mobile SDK 
    (`LREntryService_v7.m` and `LRGuestbookService_v7.m`), make sure the 
    checkbox for the *Liferay Guestbook* target is selected in the File 
    inspector's *Target Membership* section. 

    ![Figure 6: Each `*.m` file in the Guestbook Mobile SDK must be part of the *Liferay Guestbook* target.](../../../images/ios-lp-target-membership.png)

To use the Guestbook Mobile SDK's Objective-C classes from Swift, you must add 
and configure an Objective-C bridging header in your project. Follow these 
instructions to do so: 

1.  In Xcode's project navigator, right-click the root project and select *New 
    File*. In the window that appears, select *Header File* from the *Source* 
    section of the *iOS* tab, and click *Next*.

    ![Figure 7: Create a new iOS header file.](../../../images/ios-lp-header-file-01.png)

2.  Name the file `Liferay Guestbook-Bridging-Header.h` and make sure that 
    *Liferay Guestbook* with the blue icon is selected in the *Group* menu. To 
    finish creating the file, uncheck any items in *Targets* and click *Create*.

    ![Figure 8: Use these options to create the header file.](../../../images/ios-lp-header-file-02.png)

3.  Upon creating the header file, Xcode opens it in the editor. In this file, 
    you must import the Guestbook Mobile SDK's header files. Add these imports 
    immediately below the comments at the top of the file: 

        #import "LRGuestbookService_v7.h"
        #import "LREntryService_v7.h"

    Your bridging header file should now look like this: 

        #import "LRGuestbookService_v7.h"
        #import "LREntryService_v7.h"

        #ifndef Liferay_Guestbook_Bridging_Header_h
        #define Liferay_Guestbook_Bridging_Header_h


        #endif 

4.  Now you must configure your project to use this file. Select the root 
    project on the left and then click *Build Settings*. Enter for *Objective-C 
    Bridging Header* in the search box. The matching build setting appears under 
    the section *Swift Compiler - General*. In the two *Liferay Guestbook* 
    fields for this build setting, enter the bridging header's file name. 

    ![Figure 9: The red boxes highlight the two Liferay Guestbook fields configured to use the bridging header file.](../../../images/ios-lp-build-settings-header.png)

5.  Build the project. 

Awesome! You've successfully installed the Guestbook Mobile SDK. Next, you'll 
configure your app to communicate with your @product@ installation. 

## Configuring Communication with Liferay Portal [](id=configuring-communication-with-liferay-portal)

For Liferay Screens to work with your app, you must configure it to communicate 
with your @product@ installation. You'll do this by setting attributes in a 
`plist` file: 

1.  In Xcode's project navigator, right-click the *Liferay Guestbook* folder 
    (**not** the root project) and select *New File*. In the dialog that 
    appears, select the *iOS* tab then scroll down to the *Resource* section and 
    select *Property List*. Click *Next*. 

    ![Figure 10: Use the Property List template to create a new `plist` file.](../../../images/ios-lp-plist-01.png)

2.  Name the file `liferay-server-context.plist` and make sure you're creating 
    it in the *Liferay Guestbook* folder, which should be selected in the 
    *Group* menu. Also make sure that *Liferay Guestbook* is selected in the 
    *Targets* menu. Then click *Create*. 

    ![Figure 11: Create the `plist` file as shown here.](../../../images/ios-lp-plist-02.png)

3.  The `plist` file now opens in the editor. Right-click the file in the 
    Project navigator and select *Open As* &rarr; *Source Code*. Replace the 
    file's contents with this code: 

        <?xml version="1.0" encoding="UTF-8"?>
        <!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
        <plist version="1.0">
        <dict>
            <key>server</key>
            <string>http://localhost:8080</string>
            <key>version</key>
            <integer>70</integer>
            <key>companyId</key>
            <integer>20116</integer>
            <key>groupId</key>
            <integer>20143</integer>
        </dict>
        </plist>

    This `plist` file sets the server address (`http://localhost:8080`), 
    @product@ version (`70` specifies Liferay CE Portal 7.0 and Liferay DXP 
    7.0), `companyId` (@product@ instance ID), and `groupId` (site ID) the app 
    retrieves data from. 

4.  Change the `companyId` and `groupId` in the `plist` file to match those of 
    your @product@ instance. You can find your company ID in your portal at 
    *Control Panel* &rarr; *Configuration* &rarr; *Virtual Instances*. The 
    instance's ID is in the *Instance ID* column. You can find your site ID from 
    the site you put the Guestbook portlet on. Navigate to this site, and in the 
    *Site Administration* menu select *Configuration* &rarr; *Site Settings*. 
    The site ID is listed at the top of the *General* tab. 

Next, you'll configure iOS App Transport Security. 

## Disabling App Transport Security [](id=disabling-app-transport-security)

App Transport Security is an iOS security feature that restricts all network 
activity to HTTPS. It isn't necessary for use in development and testing. Since 
your local @product@ instance uses HTTP by default, App Transport Security 
prevents your app from communicating with the portal. You must therefore disable 
it: 

1.  Select your project in Xcode's Project navigator. With the *Liferay 
    Guestbook* target selected in the outline, click the *Info* tab. 

    ![Figure 12: You'll disable App Transport Security in the Info tab.](../../../images/ios-lp-ats-01.png)

2.  In *Custom iOS Target Properties*, right-click *Bundle OS Type Code* and 
    select *Add Row*. In the new row's text field, enter *App Transport Security 
    Settings*. 

3.  Even though it doesn't yet contain any items, ensure that the App Transport 
    Security Settings category is open (click the triangle icon to its left to 
    open/close it). Now click the *+* icon next to App Transport Security 
    Settings and select *Allow Arbitrary Loads*. Then select *YES* for this 
    field's value. 

    ![Figure 13: Your App Transport Security settings should look like this.](../../../images/ios-lp-ats-02.png)

    The Allow Arbitrary Loads setting should look like this when viewing your 
    app's `Info.plist` file as code: 

        ...
        <key>NSAppTransportSecurity</key>
        <dict>
            <key>NSAllowsArbitraryLoads</key>
            <true/>
        </dict>
        ...

Stupendous! You've successfully installed Liferay Screens and the Guestbook 
Mobile SDK, and configured your app to communicate with your @product@ instance. 
Before starting work on the app, however, you should learn the app's design. The 
next article walks you through this. 
