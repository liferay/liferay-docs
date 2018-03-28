# Upgrading Servlet Filter Hooks [](id=upgrading-servlet-filter-hooks)

If you have Servlet Filter Hooks ready to be upgraded, this tutorial's for you.
The process is quite simple:

1.  Adapt your plugin to @product-ver@'s API using
    [Liferay @ide@'s Code Upgrade Tool](/develop/tutorials/-/knowledge_base/7-1/adapting-to-liferay-7s-api-with-the-code-upgrade-tool).

2.  Deploy the plugin.

@product@'s Plugin Compatibility Layer converts the plugin WAR to a Web
Application Bundle (WAB) and installs it to Liferay's OSGi Runtime. 

Your Servlet Filter is running on @product-ver@!

## Related Topics [](id=related-topics)

[Resolving a Plugin's Dependencies](/develop/tutorials/-/knowledge_base/7-1/resolving-a-plugins-dependencies)

[Configuring Dependencies](/develop/tutorials/-/knowledge_base/7-1/configuring-dependencies)

[Upgrading the Liferay Maven Build](/develop/tutorials/-/knowledge_base/7-1/upgrading-the-liferay-maven-build)
