# Adapting Your Media Across Multiple Devices [](id=adapting-your-media-across-multiple-devices)

People consume content on different devices. They browse the web at school
on their tablets, check their favorite blogs on their phones in a coffee shop,
or view family photos at home on their laptops, desktops, game systems,
streaming devices, or something else. All these devices have different screen
sizes, bandwidths, and processing capabilities that must be considered when
delivering media content. @product@'s Adaptive Media app transforms uploaded
media dynamically to adjust content to the screen on which it's displayed.

This provides faster loading times for devices with a slower connection, saving 
bandwidth, and also gives administrative control over the image quality.

Adaptive Media is an application on [Liferay 
Marketplace](https://web.liferay.com/marketplace) that delivers your media 
content in the best format, based on the channel and device that consumes it. It 
runs on both Liferay Portal CE 7.0 and Liferay DXP. 

+$$$

**Note:** At this time, Adaptive Media only works for images included in blog 
entries and web content articles.

$$$

Adaptive Media integrates with Documents and Media, Blogs, and Web Content. 
It analyzes and processes images uploaded to these applications, generating
a set of images with the defined resolutions. When users read a blog entry that
contains an image on their mobile phones, Adaptive Media checks the phone's
screen size and delivers the copy of the image that best fits the screen. 

Adaptive Media also ensures that high resolution devices receive a high 
resolution image. 

+$$$

**Note:** Once Adaptive Media is installed, all content from that point onward 
uses adapted images, but content that existed previously still uses original 
images. To adapt these old content entries you must manually edit each of them. 
This can be time consuming. To automate this process, you can deploy the 
[`adaptive-media-image-content-transformer-backwards-compatibility` module](https://github.com/liferay/com-liferay-adaptive-media/tree/master/adaptive-media-image-content-transformer-backwards-compatibility).

Note that all transformations are performed both at startup and when a user
views it. This can affect the performance. Therefore we recommend that you run
some performance tests before using this module in production.

$$$

In this section, you'll learn how to manage and use Adaptive Media in @product@.
