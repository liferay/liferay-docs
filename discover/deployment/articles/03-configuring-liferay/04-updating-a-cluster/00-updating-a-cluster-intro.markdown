# Updating a Cluster [](id=updating-a-cluster)

Maintaining a
[cluster](/discover/deployment/-/knowledge_base/7-1/liferay-clustering)
is a big responsibility. It includes deploying new and updated modules and
plugins, applying fixes and improvements, making configuration changes, and
more. Maximizing server uptime and minimizing risks take priority when applying
changes. @product@ supports using standard cluster maintenance techniques. They
fall into these categories:

- [Rolling restarts](/discover/deployment/-/knowledge_base/7-1/using-rolling-restarts)
- [Other cluster maintenance techniques](/discover/deployment/-/knowledge_base/7-1/other-cluster-update-techniques)

You should use rolling restarts if possible as it maximizes uptime and mitigates
risks of changes harming your deployment. But the following types of updates
must be done using
[other techniques](/discover/deployment/-/knowledge_base/7-1/other-cluster-update-techniques). 

-   Cluster code changes.
-   Non-revertible
    [fix packs](/discover/deployment/-/knowledge_base/7-1/maintaining-liferay). 
-   Module/plugin data changes (modifying data in existing columns). 
-   Module/plugin data schema changes that break compatibility with the existing
    version. Breaking changes include but are not limited to dropping columns,
    changing column types, and changing data formats used in columns (such as
    changing from XML to JSON). 
-   Updating a data schema to a version outside of a Service Builder service 
    module's
    [required data schema range](/develop/tutorials/-/knowledge_base/7-1/creating-an-upgrade-process-for-your-app#specifying-the-schema-version). 

Since eligible changes should be done with rolling restarts it's explained
first. 
