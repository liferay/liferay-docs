---
header-id: patching-liferay
---

# Patching @product@

[TOC levels=1-4]

While we strive for perfection with every @product@ release, the reality of the
human condition dictates that releases may not be as perfect as originally
intended. But we've planned for that. Included with every @product@ bundle is a
Patching Tool that handles installing two types of patches: fix packs and
hotfixes. 

| **Important:** Make sure to
| [back up your @product@ installation and database](/docs/7-2/deploy/-/knowledge_base/d/backing-up-a-liferay-installation)
| regularly, especially before patching. The patching tool installs code changes
| and some of these make data changes (if necessary) automatically on startup.
| 
| Certain fix packs (service packs) can include data/schema
| micro changes---they're optional and revertible. Module upgrades and any 
| micro changes they include are applied at server startup by default, or can be 
| applied manually by
| [disabling the `autoUpgrade` property](/docs/7-2/deploy/-/knowledge_base/d/configuring-the-data-upgrade#configuring-non-core-module-data-upgrades).
| Server startup skips all Core micro changes. Instead, you
| can apply them using the [upgrade
| tool](/docs/7-2/deploy/-/knowledge_base/d/upgrading-to-product-ver)
| before server startup.

| **Note:** [Patching a cluster](/docs/7-2/deploy/-/knowledge_base/d/updating-a-cluster)
| requires additional considerations.
