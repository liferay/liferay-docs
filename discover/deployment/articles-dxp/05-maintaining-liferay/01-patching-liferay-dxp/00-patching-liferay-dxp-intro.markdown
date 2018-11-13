# Patching @product@ [](id=patching-liferay)

While we strive for perfection with every @product@ release, the reality of the
human condition dictates that releases may not be as perfect as originally
intended. But we've planned for that. Included with every @product@ bundle is a
Patching Tool that handles installing two types of patches: fix packs and
hotfixes. 

+$$$

**Important:** Make sure to
[back up your @product@ installation and database](/discover/deployment/-/knowledge_base/7-1/backing-up-a-liferay-installation)
regularly, especially before patching. Certain fix packs (service packs) can
contain data changes in @product@ modules (micro changes in their schema
version which are revertibles) which are applied by the patching tool
automatically. From 7.1 also micro data changes in @product@ Core can be
included on fix packs and they are able to be executed on demand using the db
upgrade tool. Please, review the release notes and the documentation for the
fix pack and previous fix packs to be installed to know if data changes are
applied.

$$$

+$$$

**Note:** [Patching a cluster](/discover/deployment/-/knowledge_base/7-1/updating-a-cluster)
requires additional considerations. 

$$$
