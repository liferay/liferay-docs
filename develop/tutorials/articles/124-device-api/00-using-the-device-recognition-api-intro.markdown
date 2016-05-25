# Using the Device Recognition API [](id=using-the-device-recognition-api)

![EE Only Feature](../../images/ee-feature-web.png)

As you know, Internet traffic has risen exponentially over the past decade, and
this trend shows no sign of stopping. In addition, the bulk of Internet traffic 
now comes from mobile devices. The mobile boom presents new obstacles and 
challenges for content management. How will content adapt to different devices 
with different capabilities? How can your grandma's gnarly tablet and your 
cousin's awesome new smart phone request the same information from your portal?

The Device API detects the capabilities of any device making a request to your
portal. It can also determine what mobile device or operating system was used to
make a request, and then follows rules to make Liferay render pages based on the
device. To use this feature, you first need to install the *Device Recognition
Provider EE* app from the [Liferay Marketplace](http://www.liferay.com/marketplace). 

The Device Recognition plugin comes bundled inside the Device Recognition
Provider app. It uses a device database called *WURFL* to determine the 
capabilities of your device. Visit the WURFL website for more information at
[http://wurfl.sourceforge.net](http://wurfl.sourceforge.net).

You can create your own plugin that makes use of your device's database. 
This tutorial shows you some of the methods in the Device API that you 
can use to retrieve device attributes and capabilities. Now go ahead and get 
started! 

## Getting Started with the Device API [](id=getting-started-with-the-device-api)

One important thing that you'll want to get using the Device API is the 
`Device` object. You can obtain it from the `themeDisplay` object like this: 

    Device device = themeDisplay.getDevice();

You can view the `Device` API at [http://docs.liferay.com/portal/6.2/javadocs](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/mobile/device/Device.html).
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

## Getting Device Capabilities [](id=getting-device-capabilities)

With the Device API, you can detect the *capabilities* of a device making a
request to your portal and then render content accordingly. Capabilities refer
to things that the requesting device can do. For example, you can determine the
device's operating system, browser, form factor, and much more. Capabilities
can be retrieved with the `getCapability` and `getCapabilities` methods of the
Device API. 

Most of the capabilities of the requesting device can be detected, depending on 
the device detection implementation you're using. The Device Recognition 
plugin's device database (WURFL) has a list of capabilities, described at
[http://www.scientiamobile.com/wurflCapability/tree](http://www.scientiamobile.com/wurflCapability/tree).
For example, you can obtain the brand name of the device with this code:

    String brand = device.getCapability("brand_name");

You can grab many other device capabilities, including `model_name`,
`marketing_name`, and `release_date`. You can also get boolean values like
`is_wireless_device`, `is_tablet`, and many more. 

Now that you know about the Device API, you can use it to make sure that your 
grandma's gnarly tablet and your cousin's awesome new smart phone can make 
requests to your portal and receive identical content. This will make everyone 
happy! 

## Related Topics [](id=related-topics)

[Using the Mobile SDK](/develop/tutorials/-/knowledge_base/6-2/mobile)

[Service Builder and Services](/develop/tutorials/-/knowledge_base/6-2/service-builder)
