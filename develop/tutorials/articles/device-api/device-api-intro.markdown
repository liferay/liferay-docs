## Device Detection [](id=device-detection-liferay-portal-6-2-dev-guide-06-en)

As you know, Internet traffic has risen exponentially over the past decade and
shows no sign of stopping. With the latest and greatest devices, mobile Internet
access has become the norm and is predicted to pass PC based Internet access
soon. Because of the mobile boom, new obstacles and challenges are presented for
content management. How will content adapt to all devices with different
capabilities? How can your grandma's gnarly tablet and cousin's awesome new
mobile phone request the same information from your portal?

The Device Detection API detects the capabilities of any device making a request
to your portal. It can also determine what mobile device or operating system was
used to make a request, and then follows rules to make Liferay render pages
based on the device. To use this feature, you first need to install the *Device
Recognition Provider* app from Liferay Marketplace. Find more information on
[Device Recognition Provider CE
app](http://www.liferay.com/marketplace/-/mp/application/15193341) and [Device
Recognition Provider
EE](http://www.liferay.com/marketplace/-/mp/application/15186132) at Liferay
Marketplace. 

The Device Recognition plugin comes bundled inside the Device Recognition
Provider app; it uses a device database called *WURFL* to determine the
capabilities of your device. Visit the WURFL website for more information at
[http://wurfl.sourceforge.net](http://wurfl.sourceforge.net).

You can create your own plugin to use your device's database. Let's look at
some simple uses of the Device Detection API and talk about its
capabilities. 
