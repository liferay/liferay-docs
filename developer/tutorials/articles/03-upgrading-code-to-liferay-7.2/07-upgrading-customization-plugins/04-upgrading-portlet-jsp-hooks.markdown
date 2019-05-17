# Upgrading Portlet JSP Hooks

Getting a portlet JSP hook running on @product-ver@ takes two steps:

1.  Adapt your code to @product-ver@'s API using the Code Upgrade Planner. When
    you ran the planner's *Fix Upgrade Problems* step, many of the existing
    issues were autocorrected/flagged. For any remaining errors, consult the
    [Resolving a Project's Dependencies](/docs/tutorials/7-2/-/knowledge_base/t/resolving-a-projects-dependencies)
    article.

2.  Deploy your hook plugin.

@product@'s Plugin Compatibility Layer converts the plugin WAR to a Web
Application Bundle (WAB) and installs it to Liferay's OSGi Runtime.

Although you can upgrade your portlet JSP hook to @product-ver@, there are
better ways to override a portlet JSP. The two recommended approaches are

- Dynamic includes
- Portlet filters

For more information on recommended ways of customizing JSPs in @product-ver@,
see the
[Customizing JSPs](/docs/customization/7-2/-/knowledge_base/c/customizing-jsps)
section.
