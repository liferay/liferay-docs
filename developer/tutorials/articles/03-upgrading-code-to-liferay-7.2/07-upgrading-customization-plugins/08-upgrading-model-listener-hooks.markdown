# Upgrading Model Listener Hooks

Developers have been creating model listeners for several @product@
versions. Upgrading Model Listener Hooks from previous portal versions has never
been easier.

1.  Adapt your code to @product-ver@'s API using the Code Upgrade Planner. When
    you ran the planner's *Fix Upgrade Problems* step, many of the existing
    issues were autocorrected/flagged. For any remaining errors, consult the
    [Resolving a Project's Dependencies](/docs/tutorials/7-2/-/knowledge_base/t/resolving-a-projects-dependencies)
    article.

2.  Deploy your hook plugin.

@product@'s Plugin Compatibility Layer converts the plugin WAR to a Web
Application Bundle (WAB) and installs it to Liferay's OSGi Runtime.
