### Using the Device API [](id=using-the-device-api-liferay-portal-6-2-dev-guide-06-en)

Let's look at a couple of code snippets to get you started. You can obtain the
object `Device` from the `themeDisplay` object like this:

    Device device = themeDisplay.getDevice();

You can view the `Device` API at
[http://docs.liferay.com/portal/6.2/javadocs](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/mobile/device/Device.html).
Using some of the methods from the Javadocs, here's an example that obtains a
device's dimensions:

    Dimensions dimensions = device.getScreenSize();
    float height = dimensions.getHeight();
    float width = dimensions.getWidth();

Now your code can get the `Device` object and the dimensions of a device.
Of course this is just a simple example; you can acquire many other device
attributes that help you take care of the pesky problems that arise when sending
content to different devices. You can refer to the Device Javadocs mentioned
above for assistance.  Let's look at some device capabilities next.

### Device Capabilities [](id=device-capabilities-liferay-portal-6-2-dev-guide-06-en)

Most of the capabilities of a device can be detected, depending on the device
detection implementation you're using. The Device Recognition plugin's device
database (WURFL) has a list of capabilities, described at
[http://www.scientiamobile.com/wurflCapability/tree](http://www.scientiamobile.com/wurflCapability/tree).
For example, you can obtain the capability of a brand name with this code:

    String brand = device.getCapability("brand_name");

You can grab many other device capabilities, including `model_name`,
`marketing_name`, and `release_date`. You can also get boolean values like
`is_wireless_device`, `is_tablet`, etc. 

With the Device Detection API, you can detect the capabilities of a device
making request to your portal and render content accordingly; so your grandma's
gnarly tablet and your cousin's awesome new mobile phone can make requests to
your portal and receive identical content. This will make everyone happy! 
