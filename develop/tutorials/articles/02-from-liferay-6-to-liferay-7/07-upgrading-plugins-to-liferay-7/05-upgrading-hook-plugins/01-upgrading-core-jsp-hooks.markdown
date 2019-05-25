---
header-id: upgrading-core-jsp-hooks
---

# Upgrading Core JSP Hooks

[TOC levels=1-4]

Getting a core JSP hook running on @product-ver@ takes two steps:

1.  Adapt your code to @product-ver@'s API using
    [Liferay @ide@'s Code Upgrade Tool](/docs/7-1/tutorials/-/knowledge_base/t/adapting-to-liferay-7s-api-with-the-code-upgrade-tool). 

2.  Deploy your hook plugin. 

@product@'s Plugin Compatibility Layer converts the plugin WAR to a Web
Application Bundle (WAB) and installs it to Liferay's OSGi Runtime. 

It's just that easy!

## Related Topics

[Customizing JSPs](/docs/7-1/tutorials/-/knowledge_base/t/customizing-jsps)

[Upgrading App JSP Hooks](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-app-jsp-hook-plugins)

[Resolving a Plugin's Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)

[Upgrading the Liferay Maven Build](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-the-liferay-maven-build)
