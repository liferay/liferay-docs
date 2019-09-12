---
header-id: upgrading-servlet-filter-hooks
---

# Upgrading Servlet Filter Hooks

[TOC levels=1-4]

If you have Servlet Filter Hooks ready to be upgraded, this tutorial's for you.
The process is quite simple:

1.  Adapt your code to @product-ver@'s API using the Liferay Upgrade Planner. When
    you ran the planner's *Fix Upgrade Problems* step, many of the existing
    issues were autocorrected or flagged. For any remaining errors, consult the
    [Resolving a Plugin's Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)
    article.

2.  Deploy the plugin.

@product@'s Plugin Compatibility Layer converts the plugin WAR to a Web
Application Bundle (WAB) and installs it to Liferay's OSGi Runtime. 

Your Servlet Filter is running on @product-ver@!

## Related Topics

[Resolving a Plugin's Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)

[Configuring Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies)

[Upgrading the Liferay Maven Build](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-the-liferay-maven-build)
