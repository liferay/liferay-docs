---
header-id: executing-post-upgrade-tasks
---

# Executing Post-Upgrade Tasks

[TOC levels=1-4]

After upgrading the database, you must prepare your @product@ database and
server for running in production again. 

Start with tuning your database. 

## Tuning Your Database for Production 

Prior to upgrading your @product@ database, you tuned it for upgrade. Now that
upgrade is complete, restore the production database tuning you used previously.

## Re-enabling Search Indexing and Reindexing Search Indexes

Make sure to re-enable search indexing by either removing the
`com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.config`
file from your `[Liferay Home]/osgi/configs` folder or setting this property in
it: 

```properties
indexReadOnly="false"
```

Then you should reindex @product@'s search indexes. Don't just do this blindly,
however. By default, @product@ ships with an embedded configuration for
Elasticsearch. This configuration works great for demo purposes, but is not
supported in production. Make sure to
[install and configure a standalone Elasticsearch instance to run in production](/docs/7-2/deploy/-/knowledge_base/deploy/installing-elasticsearch).

## Enabling Web Content View Permissions

Prior to @product@ 7.1, all users could view web content articles by default. Now view permissions are checked by default. Here are options for opening view permissions:

Option 1: Edit view permissions per web content article per role. 

Option 2: Open view permissions for all web content articles by going to
*System Settings &rarr; Web Experience &rarr; Web Content* and deselecting
*Article view permissions check enabled*. 

Once you've configured search, reindexed your search index, and set web content
view permissions, your upgraded system is ready for action! Congratulations! 
