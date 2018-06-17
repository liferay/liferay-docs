# Post-Upgrade Tasks [](id=post-upgrade-tasks)

After upgrading and running verify processes, you should reindex @product@'s
search indexes. Don't just do this blindly, however. By default, @product@ ships
with an embedded configuration for Elasticsearch. This configuration works great
for demo purposes, but is not supported in production. Make sure to
[install and configure a standalone Elasticsearch instance to run in production](/discover/deployment/-/knowledge_base/7-1/installing-elasticsearch).

Once you've configured search and reindexed your search index, your upgraded
system is ready for action! Congratulations! 
