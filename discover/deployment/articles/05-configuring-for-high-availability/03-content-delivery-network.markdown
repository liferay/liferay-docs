# Content Delivery Network [](id=content-delivery-network)

A Content Delivery Network (CDN) is an interconnected system of servers deployed
in multiple data centers that use geographical proximity as a criteria to
deliver content across the Internet. For more information on CDNs and their
general use cases and technical details, visit the following
[site](http://en.wikipedia.org/wiki/Content_delivery_network).

First, you'll discover the perks of using a CDN in Liferay and learn about
general guidelines for using a CDN in your Liferay Portal instance. Then, you'll
learn the steps to configure a CDN for your portal. It's time to expand your
Liferay content around the world! 

## Using CDN for Performance Enhancements [](id=using-cdn-for-performance-enhancements)

A CDN serves static content to users in a Liferay Portal instance. These static
resources (images, CSS, JavaScript, etc.) from the portal are stored on multiple
servers around the world, and when requested, are retrieved by the server nearest
to the user. 

The CDN functions as a caching proxy, meaning that once static content
is copied to a local server, it is stored in a cache for quick and easy
retrieval. This drastically improves latency time, because browsers can
download static resources from a local server down the street instead of
halfway around the world. A user's request to the CDN for content is directed to
a server machine using an algorithm. That algorithm attempts to use a server
closest to the user. The figure below shows a visual representation of using
geographical proximity to improve latency. 

![Figure 5.8: The red lines on the map represent the required distances traveled by requests from a server to the user. Using CDN allows a user to request static resources from a much closer local server, improving download times.](../../images/cdn-map.png)

Because of the reduced wait time for requests and reduced load on your
application server, a CDN is a great option to improve your portal's performance.
Using a CDN with Liferay, however, has some restrictions. 

Liferay only works with CDNs that can dynamically retrieve requested resources
from Liferay. For this reason, you should check with your CDN provider to make
sure you don't have to upload anything to them manually in order for it to work.
The CDN must fetch the content itself. 

There are several properties in Liferay that enable you to configure your CDN
and tweak it to your portal's needs. You'll cover how to do this next.

## Configuring Liferay to Use a CDN [](id=configuring-liferay-to-use-a-cdn)

Now that you have a general understanding of what a CDN accomplishes and how
it's used in Liferay, it's time to set one up for yourself. You can set your CDN
and its properties using two different methods: editing your portal's properties
file or using the Control Panel. 

To configure your CDN by properties file, you'll need to create a
`portal-ext.properties` file in your Liferay Home directory and set the
appropriate CDN properties. You can view the CDN properties and their
descriptions by visiting the [Content Delivery Network](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Content%20Delivery%20Network)
section of the `portal.properties` HTML document. 

Once you configure your CDN host, static assets automatically get uploaded to
the CDN, and Liferay generates URLs to them that replace the old host with your
new CDN host.

To configure your CDN in the Control Panel, navigate to the Control Panel &rarr;
*Portal Settings*. In the main configuration, you'll notice three fields related
to CDNs: 

- *CDN Host HTTP* 
- *CDN Host HTTPS* 
- *CDN Dynamic Resources Enabled*

![Figure 5.9: The Control Panel lets you configure your portal's CDN.](../../images/cdn-control-panel.png)

These properties are exactly the same as the ones you can specify in your
`portal-ext.properties`. Make sure to visit the CDN section of the Properties
Document referenced previously if you don't know how to fill in the CDN fields.
Once you're finished, click *Save* and your old host is replaced with your new
CDN host for static content. 

As you can see, configuring a CDN is extremely easy, and can drastically reduce
latency time and improve your portal's performance. 
