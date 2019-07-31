---
header-id: upgrading-service-wrapper-hooks
---

# Upgrading Service Wrapper Hooks

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Upgrading Customization Plugins</p><p>Step 4 of 11</p> 
</div>

Upgrading traditional
[service wrapper hook plugins](/docs/tutorials/6-2/-/knowledge_base/t/overriding-a-portal-service-using-a-hook)
to @product-ver@ is quick and easy.

1.  Adapt your code to @product-ver@'s API using the Liferay Upgrade Planner. When
    you ran the planner's *Fix Upgrade Problems* step, many of the existing
    issues were autocorrected/flagged. For any remaining errors, consult the
    [Resolving a Project's Dependencies](/docs/7-2/tutorials/-/knowledge_base/t/resolving-a-projects-dependencies)
    article.

2.  Deploy the plugin.

@product@'s Plugin Compatibility Layer converts the plugin WAR to a Web
Application Bundle (WAB) and installs it to Liferay's OSGi Runtime.

Your service wrapper hook is now available in @product@.
