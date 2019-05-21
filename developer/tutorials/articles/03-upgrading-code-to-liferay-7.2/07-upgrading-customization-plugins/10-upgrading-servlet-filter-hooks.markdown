# Upgrading Servlet Filter Hooks

<div class="learn-path-step">
    <p>Upgrading Customization Plugins<br>Step 9 of 11</p>
</div>

If you have Servlet Filter hooks ready to be upgraded, this tutorial's for you.
The process is simple:

1.  Adapt your code to @product-ver@'s API using the Code Upgrade Planner. When
    you ran the planner's *Fix Upgrade Problems* step, many of the existing
    issues were autocorrected/flagged. For any remaining errors, consult the
    [Resolving a Project's Dependencies](/docs/tutorials/7-2/-/knowledge_base/t/resolving-a-projects-dependencies)
    article.

2.  Deploy your hook plugin.

@product@'s Plugin Compatibility Layer converts the plugin WAR to a Web
Application Bundle (WAB) and installs it to Liferay's OSGi Runtime.

Your Servlet Filter is running on @product-ver@!