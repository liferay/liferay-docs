# Using the Device Recognition API [](id=using-the-device-recognition-api)

As you know, Internet traffic has risen exponentially over the past decade, and
this trend shows no sign of stopping. In addition, the bulk of Internet traffic
now comes from mobile devices. The mobile boom presents new obstacles and
challenges for content management. How will content adapt to different devices
with different capabilities? How can your grandma's gnarly tablet and your
cousin's awesome new smart phone request the same information from your portal?

The Device API detects the capabilities of any device making a request to your
portal. It can also determine what mobile device or operating system was used to
make a request, and then follows rules to make @product@ render pages based on
the device. To use these features, you must first install a device detection
database that can detect which mobile devices are accessing the portal.
@product@  provides such a database in the Liferay Mobile Device Detection
(LMDD) app from  the Liferay Marketplace. 
[Click here](/discover/portal/-/knowledge_base/7-0/using-the-liferay-marketplace) 
for instructions on using Liferay Marketplace to find and install apps. 

You can create your own plugin that makes use of the device database. This 
tutorial shows you some of the methods in the Device API that you can use to 
retrieve device attributes and capabilities. Now go ahead and get started! 

## Getting Started with the Device API [](id=getting-started-with-the-device-api)

One important thing that you'll want to get using the Device API is the 
`Device` object. You can obtain it from the `themeDisplay` object like this: 

    Device device = themeDisplay.getDevice();

You can view the
[`Device` API](@platform-ref@/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/mobile/device/Device.html).
Using some of the methods from the Javadocs, here's an example that obtains a
device's dimensions:

    Dimensions dimensions = device.getScreenSize();
    float height = dimensions.getHeight();
    float width = dimensions.getWidth();

Now your code can get the `Device` object and the dimensions of a device.
Of course, this is just a simple example. You can acquire many other device
attributes that help you take care of the pesky problems that arise when sending
content to different devices. Refer to the Device Javadocs mentioned above for 
assistance. Next, you'll learn about retrieving some other device capabilities.

## Getting Device Properties [](id=getting-device-capabilities)

With the Device API, you can detect the *properties* of a device making a
request to your portal and then render content accordingly. Properties refer
to things that the requesting device can do. For example, you can determine the
device's operating system, browser, form factor, and much more. Properties
can be retrieved with the `getCapability` and `getCapabilities` methods of the
Device API. 

Most of the properties of the requesting device can be detected, depending on
the device detection implementation you're using. For example, you can obtain
the brand name of the device with this code:

    String brand = device.getCapability("OEM");

You can grab many other device properties, including `HardwareModel`,
`HardwareName`, `ReleaseYear`, and `ReleaseMonth`. You can also get boolean
values like `IsMobile`, `IsTablet`, and many more.

Keep in mind the Device API is an API.  The underlying implementation of the
Device API may  change.  You can also implement your own.  Thus, the device
property names are specific and proprietary to the underlying Device API
implementation.

Now that you know about the Device API, you can use it to make sure that your 
grandma's gnarly tablet and your cousin's awesome new smart phone can make 
requests to your portal and receive identical content. This will make everyone 
happy! 

## Related Topics [](id=related-topics)

[Using the Mobile SDK](/develop/tutorials/-/knowledge_base/7-0/mobile)

[Service Builder and Services](/develop/tutorials/-/knowledge_base/7-0/service-builder)
