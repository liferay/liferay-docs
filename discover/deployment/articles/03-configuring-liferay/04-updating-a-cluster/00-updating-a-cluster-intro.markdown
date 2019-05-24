---
header-id: updating-a-cluster
---

# Updating a Cluster

Maintaining a
[cluster](/docs/7-0/deploy/-/knowledge_base/d/liferay-clustering)
is a big responsibility. It includes deploying new and updated modules and
plugins, applying fixes and improvements, making configuration changes, and
more. Maximizing server uptime and minimizing risks take priority when applying
changes. @product@ supports 
[standard cluster maintenance techniques](/docs/7-0/deploy/-/knowledge_base/d/other-cluster-update-techniques),
such as 
[rolling restarts](/docs/7-0/deploy/-/knowledge_base/d/using-rolling-restarts).

You should use rolling restarts if possible as it maximizes uptime and mitigates
risks of changes harming your deployment. 
[Other techniques](/docs/7-0/deploy/-/knowledge_base/d/other-cluster-update-techniques)
include

-   Cluster code changes.
-   Non-revertible
    [fix packs](/docs/7-0/deploy/-/knowledge_base/d/maintaining-liferay). 
-   Module/plugin data changes (modifying data in existing columns). 
-   Module/plugin data schema changes that break compatibility with the existing
    version. Breaking changes include but are not limited to dropping columns,
    changing column types, and changing data formats used in columns (such as
    changing from XML to JSON). 
-   Updating a data schema to a version outside of a Service Builder service 
    module's
    [required data schema range](/docs/7-0/tutorials/-/knowledge_base/t/creating-an-upgrade-process-for-your-app#specifying-the-schema-version). 

Since eligible changes should be done with rolling restarts, it's explained
first. 
