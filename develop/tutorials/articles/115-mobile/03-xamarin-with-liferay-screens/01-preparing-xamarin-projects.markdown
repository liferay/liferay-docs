# Preparing Xamarin Projects for Liferay Screens

+$$$

**Note:** This documentation is currently in beta. 

$$$

To use Liferay Screens, you must install it in your Xamarin project and then 
configure it to communicate with your Liferay instance. Note that Liferay Screens is 
released as an 
[NuGet](https://docs.microsoft.com/en-us/nuget/what-is-nuget) package 
hosted in [NuGet.org](https://www.nuget.org/packages/LiferayScreens).

+$$$

**Note:** After installation, you must configure Liferay Screens to communicate 
with your Liferay Portal instance. The last section in this tutorial shows you 
how to do this. 

$$$

## Requirements [](id=requirements)

Liferay Screens for Xamarin includes the necessary bindings to use all the screenlets since Liferay Screens 3.0. Also, in the official repository there are two sample projects: one for [Android](https://github.com/liferay/liferay-screens/tree/develop/xamarin/Samples/Showcase-Android) and one for [iOS](https://github.com/liferay/liferay-screens/tree/develop/xamarin/Samples/Showcase-iOS). It requires the following software:

- Visual Studio
- Android SDK 4.0 (API Level 15) or above
- [Liferay Portal 7.0 CE, Liferay DXP](http://www.liferay.com/downloads/liferay-portal/available-releases)
- [Liferay Screens NuGet package](https://www.nuget.org/packages/LiferayScreens)

## Install Liferay Screens in Xamarin solutions [](id=install-liferay-screens-in-xamarin-solutions)

The easiest way to install Liferay Screens and its dependencies in Xamarin is following these steps to use NuGet.org from our solution: 

1. Open your project in Visual Studio

2. Right click on `Packages` folder an then in `Add packages...`

3. Look for `LiferayScreens` and install the lastest version

4. Accept all its dependencies: those are necessaries to use the library in Xamarin

To check your configuration, you can:

* Rebuild and execute the sample project and import a Liferay Screens class (like Login Screenlet)
* In your project, go to `References > From Packages` and look for `LiferayScreens` file. Open that file in the Assembly Browser and there you can see all the available files from Liferay Screens for Xamarin

<iframe width="560" height="315" src="https://www.youtube.com/embed/k59s0FYD9GY" frameborder="0" allowfullscreen></iframe>

Next, you'll set up communication with Liferay. 

## Configuring Communication with Liferay [](id=configuring-communication-with-liferay)

Before using Liferay Screens, you must configure it to communicate with your Liferay instance. To do this, you must provide Screens the following information:

- Your Liferay instance's ID

- The ID of the site your app needs to communicate with

- Your Liferay instance's version

- Any other information required by specific Screenlets

<iframe width="560" height="315" src="https://www.youtube.com/embed/kBojqzvtzDs" frameborder="0" allowfullscreen></iframe>

Fortunately, this is straightforward. In your Xamarin projects: 

* For **Xamarin.Android**: in `Resources/values` folder, create a new file called `server_context.xml` and add the following code to the new file: 

    ```
    <?xml version="1.0" encoding="utf-8"?>
    <resources>

        <!-- Change these values for your Liferay Portal installation -->
        <string name="liferay_server">http://10.0.2.2:8080</string>

        <integer name="liferay_company_id">20116</integer>
        <integer name="liferay_group_id">20143</integer>
        
        <integer name="liferay_portal_version">70</integer>

    </resources>
    ```
    
* For **Xamarin.iOS**: in `Resources` folder, create a new file called `liferay-server-context.plist` and add the following code to the new file:

    ```
    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
    <plist version="1.0">
    <dict>
        <key>server</key>
        <string>http://localhost:8080</string>
        <key>version</key>
        <integer>70</integer>
        <key>companyId</key>
        <real>20116</real>
        <key>groupId</key>
        <real>20143</real>
    </dict>
    </plist>
    ```
     
As the above comment indicates, make sure to change these values to match
your Liferay instance. The server address `http://10.0.2.2:8080` is suitable 
for testing with Android Studio's emulator, because it corresponds to 
`localhost:8080` through the emulator. If you're using the Genymotion emulator,
you should, however, use address `192.168.56.1` instead of `localhost`. 

The `liferay_company_id` value is your Liferay instance's ID. You can find it in 
the *Control Panel* &rarr; *Configuration* &rarr; *Portal Instances* menu. Your 
Liferay instance's ID is in the *Instance ID* column. Copy and paste this value 
from your portal to the `liferay_company_id` value in `server_context.xml`.

The `liferay_group_id` value is the ID of the site your app needs to communicate 
with. Since the app needs to communicate with the Guestbook portlet, navigate to 
the site you put the Guestbook portlet on. Then select 
*Admin* &rarr; *Site Administration* &rarr; *Configuration* from the Dockbar. 
The site ID is listed on the *Site Settings* tab. Copy and paste this value 
from your portal to the `liferay_group_id` value in `server_context.xml`.

If you're using version 7.0 of Liferay Portal CE or Liferay DXP, you must also 
set the `liferay_portal_version` attribute in your `server_context.xml` to `70`. 
Supported values for this attribute are `62` for Liferay Portal 6.2, and `70` 
for Liferay Portal CE 7.0 or Liferay DXP 7.0. If you don't set this attribute, 
it defaults to `62`.

You can also configure Screenlet properties in your `server_context.xml` file. 
The example properties listed below, `liferay_recordset_id` and
`liferay_recordset_fields`, enable DDL Form Screenlet and DDL List Screenlet to
interact with a Liferay instance's DDLs. You can see an additional example
`server_context.xml` file
[here](https://github.com/liferay/liferay-screens/blob/develop/xamarin/Samples/Showcase-Android/Resources/values/server_context.xml).

    <!-- Change these values for your Liferay Portal installation -->
    
    <integer name="liferay_recordset_id">20935</integer>
    <string name="liferay_recordset_fields">Title</string>

Super! Your Xamarin projects are ready for Liferay Screens.

## Example Apps [](id=example-apps)

As you use Screens to develop your apps, you may want to refer to some example 
apps that also use it. There are two demo applications available:

- [Showcase-Android](https://github.com/liferay/liferay-screens/tree/develop/xamarin/Samples/Showcase-Android): An example app for Android containing all the currently available Screenlets.
- [Showcase-iOS](https://github.com/liferay/liferay-screens/tree/develop/xamarin/Samples/Showcase-iOS): A showcase app for iOS containing all the currently available Screenlets.

Great! Now you're ready to put Screens to use. The following tutorials show you 
how to do this.

## Related Topics [](id=related-topics)

[Using Screenlets in Xamarin Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-xamarin-apps)

[Using Views in Xamarin Apps](/develop/tutorials/-/knowledge_base/7-0/using-views-in-xamarin-apps)

[Creating Xamarin Views for the Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-xamarin-views-for-the-screenlets)
