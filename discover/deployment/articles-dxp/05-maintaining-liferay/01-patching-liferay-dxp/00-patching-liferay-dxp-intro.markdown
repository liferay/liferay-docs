# Patching @product@ [](id=patching-liferay)

While we strive for perfection with every @product@ release, the reality of the
human condition dictates that releases may not be as perfect as originally
intended. But we've planned for that. Included with every @product@ bundle is a
Patching Tool that handles installing two types of patches: fix packs and
hotfixes. 

+$$$

**Important:** Make sure to
[back up your @product@ installation and database](/discover/deployment/-/knowledge_base/7-1/backing-up-a-liferay-installation)
regularly, especially before patching. The patching tool installs the code
changes and the @product@ server executes data changes (if any) automatically on
startup. 

Certain fix packs (service packs) can include data/schema
[micro changes](/develop/tutorials/-/knowledge_base/7-1/meaningful-schema-versioning#micro-change-examples)---they're
optional and revertible. Module upgrades, and any micro changes they include,
are applied at server startup by default, or can be applied manually by 
[disabling the `autoUpgrade` property](/discover/deployment/-/knowledge_base/7-1/running-the-upgrade#configuring-module-upgrades).
Server startup stays streamlined by skipping all Core micro changes. Instead,
before server startup, you can apply them using the
[upgrade tool](/discover/deployment/-/knowledge_base/7-1/upgrading-to-liferay-71).

$$$

+$$$

**Note:** [Patching a cluster](/discover/deployment/-/knowledge_base/7-1/updating-a-cluster)
requires additional considerations. 

$$$
