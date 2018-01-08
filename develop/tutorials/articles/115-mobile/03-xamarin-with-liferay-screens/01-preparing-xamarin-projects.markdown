# Preparing Xamarin Projects for Liferay Screens [](id=preparing-xamarin-projects-for-liferay-screens)

To use Liferay Screens with Xamarin, you must install Screens in your Xamarin 
project. You must then configure your project to communicate with your @product@ 
instance. Note that Liferay Screens for Xamarin is released as a 
[NuGet](https://docs.microsoft.com/en-us/nuget/what-is-nuget) 
package hosted in 
[NuGet.org](https://www.nuget.org/packages/LiferayScreens). 

+$$$

**Note:** After installation, you must configure Liferay Screens to communicate 
with your @product@ instance. The last section in this tutorial shows you how to 
do this. 

$$$

## Requirements and Example Projects [](id=requirements-and-example-projects)

Liferay Screens for Xamarin includes the bindings necessary to use all 
Screenlets included with Screens. The following software is required: 

- Visual Studio
- Android SDK 4.0 (API Level 15) or above
- [Liferay Portal CE 7.0, or Liferay DXP](http://www.liferay.com/downloads/liferay-portal/available-releases)
- [Liferay Screens NuGet package](https://www.nuget.org/packages/LiferayScreens)

Also note that if you get confused or stuck while using Screens for Xamarin, 
[the official Liferay Screens repository](https://github.com/liferay/liferay-screens) 
contains two sample Xamarin projects that you can reference: 

-   [**Showcase-Android:**](https://github.com/liferay/liferay-screens/tree/develop/xamarin/Samples/Showcase-Android) 
    An example app for Xamarin.Android containing all the currently available Screenlets. 

-   [**Showcase-iOS:**](https://github.com/liferay/liferay-screens/tree/develop/xamarin/Samples/Showcase-iOS) 
    An example app for Xamarin.iOS containing all the currently available Screenlets. 

## Install Liferay Screens in Xamarin Solutions [](id=install-liferay-screens-in-xamarin-solutions)

Follow these steps to install Liferay Screens in your Xamarin project: 

1.  Open your project in Visual Studio. 

2.  Right click your project's *Packages* folder and then select 
    *Add packages...*. 

3.  Look for *LiferayScreens* and install the latest version. 

4.  Accept the license agreements for any dependencies. These are necessary to 
    use Liferay Screens in Xamarin. 

5.  Check your configuration one of these ways: 

    -   Rebuild and execute your project, and import a Liferay Screens path 
        (e.g., `Com.Liferay.Mobile.Screens.Auth.Login`). 
    -   In your project, select *References* &rarr; *From Packages* and look for 
        the `LiferayScreens*` file. Open that file in the Assembly Browser. You 
        should then see all the available Liferay Screens files. 


<iframe width="560" height="315" src="https://www.youtube.com/embed/k59s0FYD9GY" frameborder="0" allowfullscreen></iframe>


Next, you'll set up communication with @product@. 

## Configuring Communication with @product@ [](id=configuring-communication-with-product)

Before using Liferay Screens, you must configure your project to communicate 
with your @product@ instance. To do this, you must provide your project with the 
following information: 

- Your @product@ instance's ID.
- The ID of the @product@ site your app needs to communicate with.
- Your @product@ instance's version.
- Any other information required by specific Screenlets. 


<iframe width="560" height="315" src="https://www.youtube.com/embed/kBojqzvtzDs" frameborder="0" allowfullscreen></iframe>


Fortunately, this is straightforward. Do the following in your Xamarin projects: 

-   For Xamarin.Android, create a new file called `server_context.xml` in the 
    `Resources/values` folder. Add the following code to this file: 

        <?xml version="1.0" encoding="utf-8"?>
        <resources>

            <!-- Change these values for your portal installation -->
            <string name="liferay_server">http://10.0.2.2:8080</string>

            <integer name="liferay_company_id">20116</integer>
            <integer name="liferay_group_id">20143</integer>

            <integer name="liferay_portal_version">70</integer>

        </resources>

-   For Xamarin.iOS, create a new file called `liferay-server-context.plist` in 
    the `Resources` folder. Add the following code to this file: 

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
     
Make sure to change these values to match those of your @product@ instance. The 
server address `http://10.0.2.2:8080` is suitable for testing with Android 
Studio's emulator, because it corresponds to `localhost:8080` through the 
emulator. If you're using the Genymotion emulator, you should, however, use 
`192.168.56.1` instead of `localhost`. 

The `liferay_company_id` and `companyId` values are your @product@ instance's 
ID. You can find this in your @product@ instance at *Control Panel* &rarr; 
*Configuration* &rarr; *Virtual Instances*. The instance's ID is in the 
*Instance ID* column. 

The `liferay_group_id` and `groupId` values are the ID of the site your app 
needs to communicate with. To find this value, first go to the site in your 
@product@ instance that you want your app to communicate with. In the *Site 
Administration* menu, select *Configuration* &rarr; *Site Settings*. The site 
ID is listed at the top of the *General* tab. 

The `liferay_portal_version` and `version` value `70` tells Screens that it's 
communicating with a @product-ver@ instance. Supported values are `62` for 
Liferay Portal 6.2, and `70` for @product-ver@. Note that if you omit 
`liferay_portal_version` or `version` from these files, they default to `62`. 

You can also configure Screenlet properties in `server_context.xml` and 
`liferay-server-context.plist`. The example `server_context.xml` properties 
listed below, `liferay_recordset_id` and `liferay_recordset_fields`, enable DDL 
Form Screenlet and DDL List Screenlet to interact with a @product@ instance's 
DDLs: 

    <!-- Change these values for your portal installation -->

    <integer name="liferay_recordset_id">20935</integer>
    <string name="liferay_recordset_fields">Title</string>

For additional examples of these files, see the 
[Showcase-Android](https://github.com/liferay/liferay-screens/blob/develop/xamarin/Samples/Showcase-Android/Resources/values/server_context.xml) 
and 
[Showcase-iOS](https://github.com/liferay/liferay-screens/blob/develop/xamarin/Samples/Showcase-iOS/Resources/liferay-server-context.plist) 
sample projects. 

Super! Your Xamarin projects are ready for Liferay Screens. 

## Related Topics [](id=related-topics)

[Using Screenlets in Xamarin Apps](/develop/tutorials/-/knowledge_base/7-0/using-screenlets-in-xamarin-apps)

[Using Views in Xamarin.Android](/develop/tutorials/-/knowledge_base/7-0/using-views-in-xamarin-android)

[Using Themes in Xamarin.iOS](/develop/tutorials/-/knowledge_base/7-0/using-themes-in-xamarin-ios)

[Creating Xamarin Views and Themes](/develop/tutorials/-/knowledge_base/7-0/creating-xamarin-views-and-themes)

[Liferay Screens for Xamarin Troubleshooting and FAQs](/develop/tutorials/-/knowledge_base/7-0/liferay-screens-for-xamarin-troubleshooting-and-faqs)
