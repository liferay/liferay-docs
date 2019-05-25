---
header-id: upgrading-portal-property-and-event-action-hooks
---

# Upgrading Portal Property and Event Action Hooks

[TOC levels=1-4]

All portal properties in Liferay Portal 6.2 that are also used in @product-ver@
can be overridden. Portal property and portal event action hooks that use these
properties can be upgraded by following these steps:

1.  Adapt your code to @product-ver@'s API using
    [Liferay @ide@'s Code Upgrade Tool](/docs/7-1/tutorials/-/knowledge_base/t/adapting-to-liferay-7s-api-with-the-code-upgrade-tool). 

2.  Deploy your hook plugin. 

@product@'s Plugin Compatibility Layer converts the plugin WAR to a Web
Application Bundle (WAB) and installs it to Liferay's OSGi Runtime. 

Your custom property values and actions are live.

## Related Topics

[Liferay @ide@](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ide)

[Resolving a Plugin's Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)

[Configuring Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies)

[Upgrading the Liferay Maven Build](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-the-liferay-maven-build)
