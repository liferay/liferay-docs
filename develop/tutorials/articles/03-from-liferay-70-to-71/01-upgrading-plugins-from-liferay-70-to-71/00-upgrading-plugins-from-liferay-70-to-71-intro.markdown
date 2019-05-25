---
header-id: upgrading-plugins-from-liferay-7-0-to-7-1
---

# Upgrading plugins from @product@ 7.0 to 7.1

[TOC levels=1-4]

Liferay Workspace's Target Platform feature and Code Upgrade Tool take a lot of
manual intervention out of upgrading to @product-ver@. Target Platform
facilitates updating dependencies to @product-ver@, and the Code Upgrade Tool
helps you adapt plugins to @product-ver@'s API. This tutorial explains the
plugin upgrade steps. 

| **Note:** Blade CLI's `convert` command
| [migrates traditional plugins to Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/migrating-traditional-plugins-to-workspace-web-applications),
| so you can leverage Workspace's upgrade features.

Here are the plugin upgrade steps:

1.  [Update your Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/updating-liferay-workspace)
    to that latest version. 

2.  Update your @product@-related dependencies by
    [setting your Target Platform](/docs/7-1/tutorials/-/knowledge_base/t/managing-the-target-platform-for-liferay-workspace)
    to the latest version of @product-ver@. (Optional)

3.  [Update your plugin's remaining dependencies](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies).

4.  [Adapt your code to @product-ver@'s API using the Upgrade Tool](/docs/7-1/tutorials/-/knowledge_base/t/adapting-to-liferay-7s-api-with-the-code-upgrade-tool).
    The Upgrade Tool shows you where
    [breaking changes](/docs/7-1/reference/-/knowledge_base/r/breaking-changes)
    affect your code and addresses many of them automatically.

Congratulations! Your upgraded plugin is ready to
[deploy to @product-ver@](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#building-and-deploying-a-module). 

| **Note:** If your plugin resides outside of a Workspace,
| [apply the Target Platform Gradle plugin to your project](/docs/7-1/tutorials/-/knowledge_base/t/managing-the-target-platform-for-liferay-workspace#targeting-a-platform-outside-of-workspace)
| so you can set that project's Target Platform.

## Related Topics

[Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace)

[Managing Target Platforms for Workspace](/docs/7-1/tutorials/-/knowledge_base/t/managing-the-target-platform-for-liferay-workspace)

[Configuring Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/managing-the-target-platform-for-liferay-workspace)

[Liferay Dev Studio](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ide)
