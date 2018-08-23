# Upgrading Model Listener Hooks [](id=upgrading-model-listener-hooks)

Developers have been creating model listeners for several Liferay Portal
versions. Upgrading Model Listener Hooks from previous portal versions has never
been easier. 

1.  Adapt your plugin to @product-ver@'s API using
    [Liferay @ide@'s Code Upgrade Tool](/develop/tutorials/-/knowledge_base/7-1/adapting-to-liferay-7s-api-with-the-code-upgrade-tool).

2.  Deploy the plugin.

@product@'s Plugin Compatibility Layer converts the plugin WAR to a Web
Application Bundle (WAB) and installs it to Liferay's OSGi Runtime. 

Your model listener hook is "all ears" and ready to act. 

## Related Topics [](id=related-topics)

[Creating Model Listeners](/develop/tutorials/-/knowledge_base/7-1/model-listeners)

[Resolving a Plugin's Dependencies](/develop/tutorials/-/knowledge_base/7-1/resolving-a-plugins-dependencies)

[Configuring Dependencies](/develop/tutorials/-/knowledge_base/7-1/configuring-dependencies)

[Upgrading the Liferay Maven Build](/develop/tutorials/-/knowledge_base/7-1/upgrading-the-liferay-maven-build)
