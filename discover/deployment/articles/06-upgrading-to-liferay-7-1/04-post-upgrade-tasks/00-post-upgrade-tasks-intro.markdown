# Post-Upgrade Tasks [](id=post-upgrade-tasks)

After upgrading and running verify processes, there are post-upgrade tasks:

1. Re-enable search indexing and reindex search indexes. 
2. Enable web content view permissions.

Start with search indexes. 

## Re-enabling Search Indexing and Reindexing Search Indexes [](id=re-enabling-search-indexing-and-reindexing-search-indexes)

Make sure to re-enable search indexing by either removing the
`com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.config`
file from your `[Liferay Home]/osgi/configs` folder or setting this property in
it: 

    indexReadOnly="false"

Then you should reindex @product@'s search indexes. Don't just do this blindly,
however. By default, @product@ ships with an embedded configuration for
Elasticsearch. This configuration works great for demo purposes, but is not
supported in production. Make sure to
[install and configure a standalone Elasticsearch instance to run in production](/discover/deployment/-/knowledge_base/7-1/installing-elasticsearch).

## Enabling Web Content View Permissions [](id=enabling-web-content-view-permissions)

Prior to @product@ 7.1, all users could view web content articles by default. Now view permissions are checked by default. Here are options for opening view permissions:

Option 1: Edit view permissions per web content article per role. 

Option 2: Open view permissions for all web content articles by going to
*System Settings &rarr; Web Experience &rarr; Web Content* and deselecting
*Article view permissions check enabled*. 

Once you've configured search, reindexed your search index, and set web content
view permissions, your upgraded system is ready for action! Congratulations! 
