# Upgrading plugins from @product@ 7.0 to 7.1 [](id=upgrading-plugins-from-liferay-7-0-to-7-1)

Liferay Workspace's Target Platform feature and Code Upgrade Tool take a lot of
manual intervention out of upgrading to @product-ver@. Target Platform
facilitates updating dependencies to @product-ver@, and the Code Upgrade Tool
helps you adapt plugins to @product-ver@'s API. This tutorial explains the
plugin upgrade steps. 

+$$$

**Note:** Blade CLI's `convert` command
[migrates traditional plugins to Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/migrating-traditional-plugins-to-workspace-web-applications), 
so you can leverage Workspace's upgrade features. 

$$$

Here are the plugin upgrade steps:

1.  [Update your Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/updating-liferay-workspace)
    to that latest version. 

2.  Update your @product@-related dependencies by
    [setting your Target Platform](/develop/tutorials/-/knowledge_base/7-1/managing-the-target-platform-for-liferay-workspace)
    to the latest version of @product-ver@. (Optional)

3.  [Update your plugin's remaining dependencies](/develop/tutorials/-/knowledge_base/7-1/configuring-dependencies).

4.  [Adapt your code to @product-ver@'s API using the Upgrade Tool](/develop/tutorials/-/knowledge_base/7-1/adapting-to-liferay-7s-api-with-the-code-upgrade-tool).
    The Upgrade Tool shows you where
    [breaking changes](/develop/reference/-/knowledge_base/7-1/breaking-changes)
    affect your code and addresses many of them automatically.

Congratulations! Your upgraded plugin is ready to
[deploy to @product-ver@](/develop/tutorials/-/knowledge_base/7-1/starting-module-development#building-and-deploying-a-module). 

+$$$

**Note:** If your plugin resides outside of a Workspace, 
[apply the Target Platform Gradle plugin to your project](/develop/tutorials/-/knowledge_base/7-1/managing-the-target-platform-for-liferay-workspace#targeting-a-platform-outside-of-workspace)
so you can set that project's Target Platform. 

$$$

## Related Topics [](id=related-topics)

[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace)

[Managing Target Platforms for Workspace](/develop/tutorials/-/knowledge_base/7-1/managing-the-target-platform-for-liferay-workspace)

[Configuring Dependencies](/develop/tutorials/-/knowledge_base/7-1/managing-the-target-platform-for-liferay-workspace)

[Liferay Dev Studio](/develop/tutorials/-/knowledge_base/7-1/liferay-ide)
