---
header-id: upgrading-servlet-filter-hooks
---

# Upgrading Servlet Filter Hooks

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Upgrading Customization Plugins</p><p>Step 9 of 11</p>
</div>

If you have servlet filter hooks ready to be upgraded, this tutorial's for you.
The process is simple:

1.  Adapt your code to @product-ver@'s API using the Liferay Upgrade Planner. When
    you ran the planner's *Fix Upgrade Problems* step, many of the existing
    issues were autocorrected/flagged. For any remaining errors, consult the
    [Resolving a Project's Dependencies](/docs/7-2/tutorials/-/knowledge_base/t/resolving-a-projects-dependencies)
    article.

2.  Deploy your hook plugin.

@product@'s Plugin Compatibility Layer converts the plugin WAR to a Web
Application Bundle (WAB) and installs it to Liferay's OSGi Runtime.

Your Servlet Filter is running on @product-ver@!
