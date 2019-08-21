---
header-id: upgrading-model-listener-hooks
---

# Upgrading Model Listener Hooks

[TOC levels=1-4]

Developers have been creating model listeners for several Liferay Portal
versions. Upgrading Model Listener Hooks from previous portal versions has never
been easier. 

1.  Adapt your code to @product-ver@'s API using the Liferay Upgrade Planner. When
    you ran the planner's *Fix Upgrade Problems* step, many of the existing
    issues were autocorrected or flagged. For any remaining errors, consult the
    [Resolving a Plugin's Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)
    article.

2.  Deploy the plugin.

@product@'s Plugin Compatibility Layer converts the plugin WAR to a Web
Application Bundle (WAB) and installs it to Liferay's OSGi Runtime. 

Your model listener hook is "all ears" and ready to act. 

## Related Topics

[Creating Model Listeners](/docs/7-1/tutorials/-/knowledge_base/t/model-listeners)

[Resolving a Plugin's Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)

[Configuring Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies)

[Upgrading the Liferay Maven Build](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-the-liferay-maven-build)
