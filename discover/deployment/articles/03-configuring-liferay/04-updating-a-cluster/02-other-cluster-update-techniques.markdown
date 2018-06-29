# Other Cluster Update Techniques [](id=other-cluster-update-techniques)

Several update scenarios cannot be done by
[rolling restart](/discover/deployment/-/knowledge_base/7-1/using-rolling-restarts)
because they affect cluster communication, break compatibility with existing
plugins/modules, or break Service Builder services. Also non-revertible updates
are disqualified because reversing their affects requires restoring data from a 
[backup](/discover/deployment/-/knowledge_base/7-1/backing-up-a-liferay-installation). 

Maintenance changes that are ineligible for rolling restart are typically done
on all nodes at once, when they're shut down. The following sections describe
techniques for applying these changes.

- [Custom Plugin/Module Data Schema Changes](#custom-plugin-module-data-schema-changes)
- [Custom Plugin/Module Data Changes](#custom-plugin-module-data-changes)
- [Non-revertible Fix Packs (DXP only)](#non-revertible-fix-packs-dxp-only)
- [Service Builder Service Schema Version Changes](#service-builder-service-schema-version-changes)
- [Cluster Code Changes](#cluster-code-changes)

Data schema changes are explained first. 

## Custom Module/Plugin Data Schema Changes [](id=custom-plugin-module-data-schema-changes)

Custom module/plugin data schema changes that break compatibility with existing
modules and plugins must be introduced over several releases in which the data
is transitioned and maintained in old and new columns until the old column is no
longer needed. 

## Custom Module/Plugin Data Changes [](id=custom-plugin-module-data-changes)

Data changes to modules or plugins you've developed require these steps:

1.  Create a new column.

2.  Copy the data to the new column.

3.  Maintain both columns until the old column is no longer used by any cluster 
    nodes. 

4.  Delete the column in the next release. 

## Non-revertible Fix Packs (DXP only) [](id=non-revertible-fix-packs-dxp-only)

The Customer Portal identifies fix packs that are not revertible. Non-revertible
fix packs must be applied to all nodes when they are all shut down. 

## Service Builder Service Schema Version Changes [](id=service-builder-service-schema-version-changes)

A module's `Liferay-Require-SchemaVersion` (specified in its `bnd.bnd`) must
match the module's schema version value in the `Release_` table. Installing a
new module that has a new schema version updates the `Release_` table with that
schema version and triggers a data upgrade process. If you install such a module
on one node, the schema version in the `Release_` table no longer matches the
`Liferay-Require-SchemaVersion` of the modules on the other nodes. The module's
Service Builder services become unavailable. Such changes cannot be
reverted: the database must be restored from a backup. These schema version
changes must be applied while all nodes are shut down. 

## Cluster Code Changes [](id=cluster-code-changes)

Cluster communication must stay intact. For this reason, cluster code must not
be updated in rolling restarts. The Customer Portal identifies DXP fix packs
that contain such changes as non-revertible. Here are packages you must not
change in rolling restarts:

- `com.liferay.portal.kernel.cluster`
- `com.liferay.portal.kernel.cluster.*`
- `com.liferay.portal.kernel.exception.NoSuchClusterGroupException`
- `com.liferay.portal.kernel.scheduler.multiple`
- `com.liferay.portal.kernel.scheduler.multiple.*`
- `com.liferay.portal.cache.multiple`
- `com.liferay.portal.cache.multiple.*`
- `com.liferay.portal.scheduler.multiple`
- `com.liferay.portal.scheduler.multiple.*`

Now you know how to update your cluster using ways other than rolling restart. 

## Related Topics [](id=related-topics)

[@product@ Clustering](/discover/deployment/-/knowledge_base/7-1/liferay-clustering)

[Maintaining @product@](/discover/deployment/-/knowledge_base/7-1/maintaining-liferay)

[Implementing Data Upgrades](/develop/tutorials/-/knowledge_base/7-1/data-upgrades)
