---
header-id: upgrading-model-listener-hooks
---

# Upgrading Model Listener Hooks

[TOC levels=1-4]

Developers have been creating model listeners for several Liferay Portal
versions. Upgrading Model Listener Hooks from previous portal versions has never
been easier. 

1.  Adapt your plugin to @product-ver@'s API using
    [Liferay @ide@'s Code Upgrade Tool](/docs/7-1/tutorials/-/knowledge_base/t/adapting-to-liferay-7s-api-with-the-code-upgrade-tool).

2.  Deploy the plugin.

@product@'s Plugin Compatibility Layer converts the plugin WAR to a Web
Application Bundle (WAB) and installs it to Liferay's OSGi Runtime. 

Your model listener hook is "all ears" and ready to act. 

## Related Topics

[Creating Model Listeners](/docs/7-1/tutorials/-/knowledge_base/t/model-listeners)

[Resolving a Plugin's Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)

[Configuring Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies)

[Upgrading the Liferay Maven Build](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-the-liferay-maven-build)
