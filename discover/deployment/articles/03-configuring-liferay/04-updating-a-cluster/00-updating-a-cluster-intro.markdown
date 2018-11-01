# Updating a Cluster [](id=updating-a-cluster)

Maintaining a
[cluster](/discover/deployment/-/knowledge_base/7-1/liferay-clustering)
is a big responsibility. It includes deploying new and updated plugins and
modules, applying
[fix packs](/discover/deployment/-/knowledge_base/7-1/maintaining-liferay), 
making configuration changes, and more. Maximizing server uptime and minimizing
risks take priority when applying changes. @product@ supports using standard
cluster maintenance techniques. 

-   [Rolling restarts](/discover/deployment/-/knowledge_base/7-1/using-rolling-restarts): 
    Nodes are shut down and updated one at a time. 
-   [Blue-green deployment](/discover/deployment/-/knowledge_base/7-1/other-cluster-update-techniques):
    Blue-green involves duplicating the current environment (*blue*
    environment), updating the duplicate (*green* environment), and cutting over
    users to the updated environment (green). 

The techniques are compared below. 

**Cluster Update Techniques**

Update |  &nbsp;Rolling Restart|  &nbsp;Blue-green |
	--------- | :-------------- | :---------- | -------------- |
Plugin/module installation | Supported | Supported | 
Plugin/module update (backward-compatible data/schema changes) | Supported | Supported | 
Plugin/module update (non-backward-compatible data/schema changes) [1](#one) | Not supported | Supported | 
Fix pack installation and removal (revertable fix pack) | Supported | Supported | 
Fix pack installation (non-revertible fix pack) | Not supported | Supported | 
Cluster code changes [2](#two) | Not supported | Supported | 
Portal property changes | Supported | Supported | 
System Setting changes via configuration admin files | Supported | Supported | 
Application server updates | Supported | Supported | 
JVM setting changes | Supported | Supported | 
New Java version (minor) | Supported | Supported | 
New Java version (major) | Not supported | Supported | 

[<a name="one">1</a>] Data and data schema changes that are not 
backward-compatible include, but are not limited to these:

-   Modifying data in existing columns
-   Dropping columns
-   Changing column types
-   Changing data formats used in columns (such as changing from XML to JSON)
-   Updating a Service Builder service module's data schema to a version 
    outside of the module's
    [required data schema range](/develop/tutorials/-/knowledge_base/7-1/creating-an-upgrade-process-for-your-app#specifying-the-schema-version). 
    A module's `Liferay-Require-SchemaVersion` (specified in its `bnd.bnd`) must
    match the module's schema version value in the `Release_` table. Installing
    a module with a new schema version updates the `Release_` table with
    that schema version and triggers a data upgrade process. If you install such
    a module on one node, the schema version in the `Release_` table no longer
    matches the `Liferay-Require-SchemaVersion` of the modules on the other
    nodes, and the module's Service Builder services become unavailable until
    the module is installed on the other nodes. Such changes cannot be reverted:
    the database must be restored from a backup. These schema version changes
    must be applied while all nodes are shut down. 

[<a name="two">2</a>] Cluster communication must stay intact. For this reason, cluster code must not
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
    
Since eligible changes should be done with rolling restarts, it's explained
first. 
