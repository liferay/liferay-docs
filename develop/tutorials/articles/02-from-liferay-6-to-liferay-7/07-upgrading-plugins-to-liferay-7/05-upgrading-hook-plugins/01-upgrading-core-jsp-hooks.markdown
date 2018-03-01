# Upgrading Core JSP Hooks [](id=upgrading-core-jsp-hooks)

Getting a core JSP hook running on @product-ver@ takes two steps:

1.  Adapt your code to @product-ver@'s API using
    [Liferay @ide@'s Code Upgrade Tool](/develop/tutorials/-/knowledge_base/7-1/adapting-to-liferay-7s-api-with-the-code-upgrade-tool). 

2.  Deploy your hook plugin. 

@product@'s Plugin Compatibility Layer converts the plugin WAR to a Web
Application Bundle (WAB) and installs it to Liferay's OSGi Runtime. 

It's just that easy!

## Related Topics [](id=related-topics)

[Overriding Core JSPs](/develop/tutorials/-/knowledge_base/7-1/overriding-core-jsps)

[Upgrading App JSP Hooks](/develop/tutorials/-/knowledge_base/7-1/upgrading-app-jsp-hook-plugins)

[Resolving a Plugin's Dependencies](/develop/tutorials/-/knowledge_base/7-1/resolving-a-plugins-dependencies)

[Upgrading the Liferay Maven Build](/develop/tutorials/-/knowledge_base/7-1/upgrading-the-liferay-maven-build)
