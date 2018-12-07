# Preparing iOS Projects for Liferay Screens [](id=preparing-ios-projects-for-liferay-screens)

To develop iOS apps with Liferay Screens, you must first install and configure 
Screens in your iOS project. Screens is released as a standard 
[CocoaPods](https://cocoapods.org) 
dependency. You must therefore install Screens via CocoaPods. After completing 
the installation, you must configure your iOS project to communicate with your 
@product@ instance. This tutorial walks you through these processes. You'll be 
up and running in no time! 

First, you'll review the requirements for Liferay Screens. 

## Requirements [](id=requirements)

Liferay Screens for iOS includes the Component Library (the Screenlets) and 
some sample projects written in Swift. Screens is developed using Swift and 
development techniques that leverage functional Swift code and the 
[Model View Presenter](http://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter) 
architecture. You can use Swift or Objective-C with Screens, and you can run 
Screens apps on iOS 9 and above. 

Liferay Screens for iOS requires the following software: 

-   Xcode 9.3 or newer
-   iOS 11 SDK
-   [CocoaPods](http://cocoapods.org) 1 or newer
-   [Liferay Portal 6.2 CE/EE, Liferay CE Portal 7.0/7.1, or Liferay DXP](http://www.liferay.com/downloads/liferay-portal/available-releases)
-   Liferay Screens Compatibility Plugin 
    ([CE](http://www.liferay.com/marketplace/-/mp/application/54365664) or 
    [DXP/EE](http://www.liferay.com/marketplace/-/mp/application/54369726), 
    depending on your portal edition). This app is preinstalled in Liferay CE 
    Portal 7.0/7.1 CE and Liferay DXP. 

## Securing JSON Web Services [](id=securing-json-web-services)

Each Screenlet in Liferay Screens calls one or more of @product@'s JSON web 
services, which are enabled by default. The Screenlet 
[reference documentation](/develop/reference/-/knowledge_base/7-0/screenlets-in-liferay-screens-for-ios) 
lists the web services that each Screenlet calls. To use a Screenlet, its web 
services must be enabled in the portal. It's possible, however, to disable the 
web services needed by Screenlets you're not using. For instructions on this, 
see the tutorial 
[Configuring JSON Web Services](/develop/tutorials/-/knowledge_base/7-0/portal-configuration-of-json-web-services). 
You can also use 
[Service Access Policies](/develop/tutorials/-/knowledge_base/7-0/service-access-policies) 
for more fine-grained control over accessible services. 

## Configuring Your Project with CocoaPods [](id=configuring-your-project-with-cocoapods)

To use CocoaPods to prepare your iOS 9.0 (or above) project for Liferay Screens, 
follow these steps:

1.  In your project's root folder, add the following code to the file named 
    `Podfile`, or create this file if it doesn't exist. Be sure to replace 
    `Your Target` with your target's name: 

        source 'https://github.com/CocoaPods/Specs.git'

        platform :ios, '9.0'
        use_frameworks!

        target "Your Target" do
            pod 'LiferayScreens'
        end

        # the rest of your podfile

    Note that Liferay Screens and some of its dependencies aren't compatible 
    with Swift 3.2 or Swift 4.0. If your iOS project is compiled in Swift 3.2 or 
    Swift 4.0, then your `Podfile` must specify Screens and those dependencies 
    for compilation in Swift 4.2. The `post_install` code in the following 
    `Podfile` does this. You must therefore use this `Podfile` if you want to 
    use Screens in a Swift 3.2 or Swift 4.0 app: 

    	source 'https://github.com/CocoaPods/Specs.git'

        platform :ios, '9.0'
        use_frameworks!

        target "Your Target" do
            pod 'LiferayScreens'
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
                config.build_settings['SWIFT_VERSION'] = '4.2'
              end
            end
            target.build_configurations.each do |config|
                config.build_settings['CONFIGURATION_BUILD_DIR'] = '$PODS_CONFIGURATION_BUILD_DIR'
            end
          end
        end

        # the rest of your podfile 

2.  On the terminal, navigate to your project's root folder and run this 
    command: 

        pod repo update

    This ensures you have the latest version of the CocoaPods repository on your 
    machine. Note that this command can take a while to run. 

3.  Still in your project's root folder in the terminal, run this command: 

        pod install

    Once this completes, quit Xcode and reopen your project by using the 
    `*.xcworkspace` file in your project's root folder. From now on, you must 
    use this file to open your project. 

Great! To configure your project's communication with @product@, you can skip 
the next section and follow the instructions in the final section. 

## Configuring Communication with @product@ [](id=configuring-communication-with-liferay)

Configuring communication between Screenlets and @product@ is easy. Liferay
Screens uses a property list (`.plist`) file to access your @product@ instance.
It must include the server's URL, the portal's company ID, and the site's group
ID. Create a `liferay-server-context.plist` file and specify values required for 
communicating with your @product@ instance. As an example, refer to
[`liferay-server-context-sample.plist`](https://github.com/liferay/liferay-screens/blob/master/ios/Framework/Core/Resources/liferay-server-context-sample.plist). 

![Figure 1: Here's a property list file, called `liferay-context.plist`.](../../../images/screens-ios-liferay-context.png)

The values you need to specify in your `liferay-server-context.plist` are:

- `server`: Your @product@ instance's URL.
- `version`: Your @product@ instance's version. Supported values are `71` for
  Liferay CE Portal 7.1 or Liferay DXP 7.1, `70` for Liferay CE Portal 7.0 or 
  Liferay DXP 7.0, and `62` for Liferay Portal 6.2 CE/EE.
- `companyId`: Your @product@ instance's identifier. You can find this value in
  the *Instance ID* column of *Control Panel* &rarr; *Portal Instances*.
- `groupId`: The ID of the default site you want Screens to
  communicate with. You can find this value in the Site ID field of the site's
  *Site Administration* &rarr; *Configuration* &rarr; *Site Settings* menu.
- `connectorFactoryClassName`: Your Connector's factory class name.
  This is optional. If you don't include it, the `version` value is used to
  determine which factory is the most suitable for that version of @product@. 

Great! Your iOS project is ready for Liferay Screens.

## Related Topics [](id=related-topics)

[Using Screenlets in iOS Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-ios-apps)

[Using Themes in iOS Screenlets](/develop/tutorials/-/knowledge_base/7-0/using-themes-in-ios-screenlets)

[Preparing Android Projects for Liferay Screens](/develop/tutorials/-/knowledge_base/7-0/preparing-android-projects-for-liferay-screens)
