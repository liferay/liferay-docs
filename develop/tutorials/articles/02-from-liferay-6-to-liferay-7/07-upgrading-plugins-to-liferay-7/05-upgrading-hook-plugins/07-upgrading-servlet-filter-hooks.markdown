# Upgrading Servlet Filter Hooks [](id=upgrading-servlet-filter-hooks)

If you have Servlet Filter Hooks ready to be upgraded, this tutorial's for you.
Although the process is probably simpler than you'd expect.

1.  Adapt your plugin to @product-ver@'s API using @liferay-ide@'s Code
    Upgrade Tool.

2.  Deploy the plugin.

@product@'s Plugin Compatibility Layer converts the plugin WAR to a Web
Application Bundle (WAB) and installs it to Liferay's OSGi Runtime. 

Your Servlet Filter is running on @product-ver@!
