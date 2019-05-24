---
header-id: device-detection
---

# Device Detection

As you know, Internet traffic has risen exponentially over the past decade and
shows no sign of stopping. With the latest and greatest devices, mobile Internet
access has become the norm and is predicted to pass PC based Internet access
soon. Because of the mobile boom, new obstacles and challenges are presented for
content management. How will content adapt to all devices with different
properties? How can your grandma's gnarly tablet and cousin's awesome new
mobile phone request the same information from your portal?

The Device Detection API detects the properties of any device making a request
to your portal. It can also determine what mobile device or operating system was
used to make a request, and then follows rules to make Liferay render pages
based on the device. To use these features, you must first install a  device
detection database that can detect which mobile devices are accessing the
portal. Liferay provides such a database in the Liferay Mobile Device Detection
(LMDD) app from the Liferay Marketplace. 
[Click here](/docs/6-1/user/-/knowledge_base/u/leveraging-the-liferay-marketplace) 
for instructions on using Liferay Marketplace to find and install apps. 

| **Note:** The Liferay Mobile Device Detection (LMDD) app is EE-only for Liferay
| Portal 6.1. Although you can use other device detection databases, doing so
| requires you to manually integrate the database with Liferay APIs.

You can create your own plugin to use your device's database. Let's look at some
simple uses of the Device Detection API and talk about its properties. 

## Using the Device API

Let's look at a couple of code snippets to get you started. You can obtain the
object `Device` from the `themeDisplay` object like this:

    Device device = themeDisplay.getDevice();

You can view the API in the
[Device Javadocs](http://docs.liferay.com/portal/6.1/javadocs/com/liferay/portal/kernel/mobile/device/Device.html).
Using some of the methods from the Javadocs, here's an example that obtains a
device's dimensions:

    Dimensions dimensions = device.getScreenSize();
    float height = dimensions.getHeight();
    float width = dimensions.getWidth();

Now your code can get the `Device` object and the dimensions of a device. Of
course this is just a simple example; you can acquire many other device
attributes that help you take care of the pesky problems that arise when sending
content to different devices. You can refer to the Device Javadocs mentioned
above for assistance.  Let's look at some device properties next.

## Device Properties

Most of the properties of a device can be detected, depending on the device
detection implementation you're using. For example, you can obtain the brand
name of a device with this code:

    String brand = device.getCapability("OEM");

You can grab many other device properties, including `HardwareModel`,
`HardwareName`, `ReleaseYear`, and `ReleaseMonth`. You can also get boolean
values like `IsMobile`, `IsTablet`, and many more. 

Keep in mind the Device API is an API. The underlying implementation may change.
You can use your own implementation also. Device property names are specific and
proprietary to the underlying Device API implementation. 

With the Device Detection API, you can detect the capabilities of a device
making request to your portal and render content accordingly; so your grandma's
gnarly tablet and your cousin's awesome new mobile phone can make requests to
your portal and receive identical content. This will make everyone happy! 

You're really getting the hang of Liferay's APIs. Way to go! 
