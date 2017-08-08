# Content Delivery Network [](id=content-delivery-network)

A Content Delivery Network (CDN) is an interconnected system of servers
deployed in multiple data centers that use geographical proximity as a criteria
to deliver content across the Internet. For more information on CDNs and their
general use cases and technical details, see
[http://en.wikipedia.org/wiki/Content_delivery_network](http://en.wikipedia.org/wiki/Content_delivery_network).

Here, you'll first discover the perks of using a CDN in @product@ and learn about
general guidelines for using a CDN in your @product@ instance. Then,
you'll learn the steps to configure a CDN for your @product@ instance. It's time to
distribute your @product@ content around the world!

## Using CDN for Performance Enhancements [](id=using-cdn-for-performance-enhancements)

A CDN serves web resources to users of a @product@ instance. These
resources (images, CSS files, JavaScript files, etc.) from the portal are stored
on multiple servers around the world. When requested, the resources are
retrieved from the server nearest to the user.

The CDN functions as a caching proxy. This means that once static content is
copied to a local server, it is stored in a cache for quick and easy retrieval.
This drastically improves latency time, because browsers can download static
resources from a local server down the street instead of halfway around the
world. A user's request to the CDN for content is directed to specific server
machine based on an algorithm. The algorithm attempts to find the server
closest to the user. The figure below shows a visual representation of using
geographical proximity to improve latency.

![Figure 1: The red lines on the map represent the required distances traveled by requests from a server to the user. Using CDN allows a user to request static resources from a much closer local server, improving download times.](../../images/cdn-map.png)

Because of the reduced wait time for requests and reduced load on your
application server, a CDN is a great option to improve your portal's
performance. Using a CDN with @product@, however, has some restrictions.

## Liferay CDN Requirements [](id=liferay-cdn-requirements)

@product@ only works with CDNs that can dynamically retrieve requested resources
from @product@. Dynamic resources are resources which change over time or via
interaction with end users and thus cannot be cached. For this reason, you
should check with your CDN provider to make sure you don't have to manually
upload anything in order for the CDN to work. The CDN must automatically fetch
the content from @product@.

A @product@-compatible CDN must work like a transparent proxy: A request first
goes to the CDN. If the CDN doesn't have the requested resource, the CDN makes
an identical request back to the origin (@product@), caches the resource, then
serves the resource.

Once you've configured @product@ to use a CDN (see below), the CDN not only serves
portal resources but also plugin resources (e.g., theme resources or JavaScript
files referenced from a plugin's `liferay-portlet.xml` file). The CDN only
serves resources that are actually included in a plugin. It does not serve
resources that are dynamically loaded from external sources.

To get the CDN URL for a resource, developers can simply replace the portal host
in the resource path with `themeDisplay.getCDNDynamicResourcesHost()`.
Developers should prefix resources with the CDN host name. They should not
manually upload any resources to the CDN or put anything on the CDN which
requires permission checking or complex policy access.

There are several properties in @product@ that enable you to configure your CDN
and tweak it to suite your portal's needs. You'll learn how to do this next.

## Configuring @product@ to Use a CDN [](id=configuring-liferay-to-use-a-cdn)

Now that you have a general understanding of what a CDN accomplishes and how
it's used in @product@, it's time to set one up for yourself. You can set your CDN
and its properties using two different methods:

1. By editing your portal properties file
2. By using the Control Panel

To configure your CDN via properties file, you need to create a
`portal-ext.properties` file in your Liferay Home directory and set the
appropriate CDN properties. You can view the CDN properties and their
descriptions by visiting the [Content Delivery Network](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Content%20Delivery%20Network)
section of the `portal.properties` HTML document.

Once you configure your CDN host, @product@ generates URLs to the static assets
that replace the old host with your new CDN host and so they are automatically
cached and served afterwards by the CDN.

To configure your CDN in the Control Panel, navigate to *Control Panel* &rarr;
*Configuration* &rarr; *Instance Settings*. In the main configuration, you'll
notice three fields related to CDNs:

- *CDN Host HTTP*
- *CDN Host HTTPS*
- *CDN Dynamic Resources Enabled*

![Figure 2: The Control Panel lets you configure your portal's CDN.](../../images/cdn-control-panel.png)

These properties are exactly the same as the ones you can specify in your
`portal-ext.properties`. Make sure to visit the CDN section of the Properties
Document referenced previously if you don't know how to fill in the CDN fields.
Once you're finished, click *Save* and your old host is replaced with your new
CDN host for static content.

As you can see, configuring a CDN is extremely easy, and can drastically reduce
latency time and improve your portal's performance.
