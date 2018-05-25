# Content Delivery Network [](id=content-delivery-network)

A Content Delivery Network (CDN) is an network of servers deployed in multiple
data centers that contain your static content. When users hit your site, that
static content is loaded from a server with geographical proximity to the user,
speeding up requests. 

Here, you'll first discover the perks of using a CDN and learn about general
guidelines for using a CDN with @product@. Then, you'll configure a CDN. It's
time to distribute your content around the world!

## Using CDN for Performance Enhancements [](id=using-cdn-for-performance-enhancements)

A CDN serves static web resources to users. These resources (images, CSS files,
JavaScript files, etc.) are stored on multiple servers around the world. When
requested, the resources are retrieved from the server nearest to the user.

The CDN functions as a caching proxy. This means that once static content is
copied to a local server, it is stored in a cache for quick and easy retrieval.
This drastically improves latency time, because browsers can download static
resources from a local server down the street instead of halfway around the
world. A user's request to the CDN for content is directed to specific server
machine based on an algorithm that finds the server closest to the user. The
figure below shows a visual representation of using geographical proximity to
improve latency.

![Figure 1: The red lines on the map represent the required distances traveled by requests from a server to the user. Using CDN allows a user to request static resources from a much closer local server, improving download times.](../../../images/cdn-map.png)

Because of the reduced wait time for requests and reduced load on your
application server, a CDN is a great option to improve performance. Using a CDN
with @product@, however, has some restrictions.

## Liferay CDN Requirements [](id=liferay-cdn-requirements)

@product@ only works with CDNs that can dynamically retrieve requested
resources. Dynamic resources change over time or via interaction with end users
and thus cannot be cached. For this reason, check with your CDN provider to make
sure you don't have to upload anything manually in order for the CDN to work.
The CDN must automatically fetch the content.

The CDN must work like a transparent proxy. A request first goes to the CDN. If
the CDN doesn't have the requested resource, the CDN makes an identical request
back to the origin (@product@), caches the resource, then serves the resource.

Once you're using a CDN (see below), it serves both portal resources and plugin
resources (e.g., theme resources or JavaScript files referenced from a plugin's
`liferay-portlet.xml` file). The CDN only serves resources that are included in
a plugin. It does not serve resources that are dynamically loaded from external
sources.

To get the CDN URL for a resource, developers should replace the portal host in
the resource path with `themeDisplay.getCDNDynamicResourcesHost()`. Prefix
resources with the CDN host name. Don't manually upload any resources to the CDN
or put anything on the CDN which requires permission checking or complex policy
access.

There are several portal properties for configuring your CDN to suit your
needs. You'll learn how to do this next.

## Configuring @product@ to Use a CDN [](id=configuring-liferay-to-use-a-cdn)

Now that you understand what a CDN accomplishes and how it's used, it's time to
set one up for yourself. You can set your CDN and its properties using two
different methods:

1. By editing your portal properties file

2. By using the Control Panel

To configure your CDN via a properties file, create a
`portal-ext.properties` file in your
[Liferay Home](/discover/deployment/-/knowledge_base/7-1/installing-product#liferay-home)
folder and set the appropriate
[Content Delivery Network properties](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Content%20Delivery%20Network).

Once you configure your CDN host, @product@ generates URLs to the static assets
that replace the old host with your new CDN host so they are automatically
cached and served afterwards by the CDN.

To configure your CDN in the Control Panel, navigate to *Control Panel* &rarr;
*Configuration* &rarr; *Instance Settings*. In the main configuration, there are
three fields related to CDNs:

- *CDN Host HTTP*
- *CDN Host HTTPS*
- *Enable CDN Dynamic Resources*

![Figure 2: The Control Panel lets you configure your portal's CDN.](../../../images/cdn-control-panel.png)

These properties are exactly the same as the ones you can specify in your
`portal-ext.properties`. Make sure to visit the Content Delivery Network section
of the
[portal.properties](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Content%20Delivery%20Network)
reference document if you don't know how to fill in the CDN fields. Once you're
finished, click *Save* and your old host is replaced with your new CDN host for
static content.

As you can see, configuring a CDN is easy and can drastically reduce latency
time and improve performance.
