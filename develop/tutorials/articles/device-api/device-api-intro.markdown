# Device Detection

As you know, Internet traffic has risen exponentially over the past decade and
this trend shows no sign of stopping. In addition, the bulk of internet traffic 
now comes from mobile devices. The mobile boom presents new obstacles and 
challenges for content management. How will content adapt to different devices 
with different capabilities? How can your grandma's gnarly tablet and your 
cousin's awesome new smartphone request the same information from your portal?

The Device Detection API detects the capabilities of any device making a request
to your portal. It can also determine what mobile device or operating system 
made the request. Rules are then followed to make Liferay render pages based on 
the requesting device. To use this feature, you first need to install the 
*Device Recognition Provider* app from Liferay Marketplace. You can find more 
information on the [Device Recognition Provider CE](http://www.liferay.com/marketplace/-/mp/application/15193341) 
and [Device Recognition Provider EE](http://www.liferay.com/marketplace/-/mp/application/15186132) 
apps at Liferay Marketplace. 

The Device Recognition plugin comes bundled inside the Device Recognition
Provider app. It uses a device database called *WURFL* to determine the 
capabilities of your device. Visit the WURFL website for more information at
[http://wurfl.sourceforge.net](http://wurfl.sourceforge.net).

You can create your own plugin that makes use of your device's database. The 
tutorial in this section presents some simple uses of the Device Detection API 
and talks about its capabilities. 

## Related Topics

[Mobile](/tutorials/-/knowledge_base/6-2/mobile)

[Service Builder and Services](/tutorials/-/knowledge_base/6-2/service-builder)
