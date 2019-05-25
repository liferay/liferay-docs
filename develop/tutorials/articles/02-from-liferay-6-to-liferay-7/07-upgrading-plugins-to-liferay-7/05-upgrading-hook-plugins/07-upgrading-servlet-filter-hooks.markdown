---
header-id: upgrading-servlet-filter-hooks
---

# Upgrading Servlet Filter Hooks

[TOC levels=1-4]

If you have Servlet Filter Hooks ready to be upgraded, this tutorial's for you.
The process is quite simple:

1.  Adapt your plugin to @product-ver@'s API using
    [Liferay @ide@'s Code Upgrade Tool](/docs/7-1/tutorials/-/knowledge_base/t/adapting-to-liferay-7s-api-with-the-code-upgrade-tool).

2.  Deploy the plugin.

@product@'s Plugin Compatibility Layer converts the plugin WAR to a Web
Application Bundle (WAB) and installs it to Liferay's OSGi Runtime. 

Your Servlet Filter is running on @product-ver@!

## Related Topics

[Resolving a Plugin's Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)

[Configuring Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies)

[Upgrading the Liferay Maven Build](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-the-liferay-maven-build)
