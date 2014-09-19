# Content Delivery Network [](id=content-delivery-network)

A Content Delivery Network (CDN) is an interconnected system of servers deployed
in multiple data centers that use geographical proximity as a criteria to
deliver content across the internet. For more information on CDNs and their
general use cases and technical details, visit the following
[site](http://en.wikipedia.org/wiki/Content_delivery_network).

First, you'll discover the perks of using a CDN in Liferay and learn about
general guidelines for using a CDN in your Liferay site. Then, you'll learn the
steps to configure a CDN for your portal. It's time to expand your Liferay
content around the world! 

## Using CDN for Performance Enhancements

In Liferay, a configured CDN serves static content to users on a Liferay site.
These static resources (e.g., images, CSS, JavaScript, etc.) from the site
are stored on multiple servers around the world, and when requested, are
retrieved by a server nearest to the location of the user. 

The CDN basically functions as a caching proxy, meaning that once static content
is generated onto a local server, it is stored in a cache for quick and easy
retrieval, without having to communicate with another server that is not close
in proximity. This drastically improves request latencies for your users because
your site can download static resources from a local server down the street,
instead of halfway around the world. A user's request to the CDN for content is
directed to a server machine using an algorithm. That algorithm attempts to use
a server closest to the user. Take a look at the figure below for a visual
representation of using geographical proximity to improve request latencies. 

![Figure 1: The red lines on the map represent the required distances traveled by requests from a server to the user. Using a CDN allows a user to request static resources from a much closer local server, improving download times.](../../images/cdn-map.png)

Because of the reduced wait time for requests, CDN is a great option to improve
your portal's performance. However, using CDN in Liferay has some restrictions. 

Liferay only works with CDNs that can dynamically retrieve requested resources
from Liferay. Also, nothing that Liferay automatically refers is transferred to
the CDN. The CDN must fetch the content itself. 

There are several properties in Liferay that enable you to configure your CDN
and tweak it to your site's needs. You'll cover how to do this next.

## Configuring Liferay with CDN


