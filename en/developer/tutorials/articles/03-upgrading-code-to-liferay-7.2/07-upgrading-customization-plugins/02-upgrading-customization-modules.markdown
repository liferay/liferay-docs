---
header-id: upgrading-customization-modules
---

# Upgrading Customization Modules

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Upgrading Customization Plugins</p><p>Step 1 of 11</p>
</div>

Customization modules include any module extension or override used to
customize another module. For examples of these types of modules, visit the
[`extensions`](https://github.com/liferay/liferay-blade-samples/tree/master/liferay-workspace/extensions)
and
[`overrides`](https://github.com/liferay/liferay-blade-samples/tree/master/liferay-workspace/overrides)
sample projects.

Getting a customization module running on @product-ver@ takes two steps:

1.  Adapt your code to @product-ver@'s API using the Liferay Upgrade Planner. When
    you ran the planner's *Fix Upgrade Problems* step, many of the existing
    issues were autocorrected or flagged. For any remaining errors, consult the
    [Resolving a Project's Dependencies](/docs/7-2/tutorials/-/knowledge_base/t/resolving-a-projects-dependencies)
    article.

2.  Deploy your module.

| **Note:** A fragment was a common customization module in past versions of
| @product@. Fragments are no longer recommended; you should upgrade a fragment
| to a dynamic include or portlet filter. For more information on recommended
| ways of customizing JSPs in @product-ver@, see the
| [Customizing JSPs](/docs/7-2/customization/-/knowledge_base/c/customizing-jsps)
| section.

Great! Your customization module is upgraded for @product-ver@!
