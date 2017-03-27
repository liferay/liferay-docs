# Optimizing Upgrade Processes [](id=optimizing-upgrade-processes)

When you make changes to your app that affect its database, you can use an 
*upgrade process* to upgrade any existing data to the new database schema. When 
migrating your app from Liferay Portal 6 to @product-ver@, you must also migrate 
your app's upgrade processes. How you do this depends on your app's development 
framework. If you're migrating your Liferay Portal 6 app to a traditional WAR, 
then you don't need to do anything special; you can copy your existing upgrade 
process into your new app and it will run without any changes required. If 
you're modularizing your app for @product-ver@, however, then you must migrate 
your upgrade processes to use @product-ver@'s new upgrade process framework. 
This tutorial shows you how to do this. 

Before beginning, make sure you know how to create an upgrade process that uses 
the new framework. 
[Click here](/develop/tutorials/-/knowledge_base/7-0/creating-an-upgrade-process-for-your-app) 
to read the tutorial on creating such upgrade processes. 

+$$$

**Note:** Your Liferay Portal 6 app may also include verify processes. Although 
you can migrate your verify processes to @product-ver@ without any changes, it's 
best practice to instead perform any verification in your upgrade processes. 

$$$

First, you'll review how Liferay Portal 6 upgrade processes work. 

## Understanding Liferay Portal 6 Upgrade Processes [](id=understanding-liferay-portal-6-upgrade-processes)

Before getting started, it's important to understand how Liferay Portal 6 
upgrade processes are structured. As an example, you'll use the Liferay Portal 
6.2 upgrade process for the Knowledgebase Portlet. 
[Click here](https://github.com/liferay/liferay-plugins/tree/6.2.x/portlets/knowledge-base-portlet/docroot/WEB-INF/src/com/liferay/knowledgebase/hook/upgrade) 
to access it in GitHub. 

![Figure 1: The Knowledgebase Portlet's Liferay Portal 6.2 upgrade process.](../../../../images/upgrade-process-6-2.png)

In Liferay Portal 6 upgrade processes, the upgrade step classes for each schema 
version are in folders named after the schema version they apply to. For 
example, the Knowledgebase Portlet's upgrade step classes are in folders named 
`v1_0_0`, `v1_1_0`, `v1_2_0`, and so on. Each upgrade step class extends 
`UpgradeProcess` and overrides the `doUpgrade` method. The code in `doUpgrade` 
performs the upgrade. For example, the Knowledgebase Portlet's 
[`v1_0_0/UpgradeRatingsEntry` upgrade step](https://github.com/liferay/liferay-plugins/blob/6.2.x/portlets/knowledge-base-portlet/docroot/WEB-INF/src/com/liferay/knowledgebase/hook/upgrade/v1_0_0/UpgradeRatingsEntry.java) 
extends `UpgradeProcess` and performs the upgrade via the 
`updateRatingsEntries()` call in its `doUpgrade` method: 

    public class UpgradeRatingsEntry extends UpgradeProcess {

        @Override
        protected void doUpgrade() throws Exception {
            updateRatingsEntries();
        }

        ...

        protected void updateRatingsEntries() throws Exception {
            // Upgrade code
        }

        ...

    }

The upgrade process classes are located on the same level as the folders 
containing the upgrade steps, and are also named after the schema version they 
apply to. For example, the Knowledgebase Portlet's upgrade process classes are 
named `UpgradeProcess_1_0_0`, `UpgradeProcess_1_1_0`, `UpgradeProcess_1_2_0`, 
and so on. Each upgrade process class also extends `UpgradeProcess` and runs the 
upgrade process in the `doUpgrade` method. It runs the upgrade process by 
passing the appropriate upgrade step to the `upgrade` method. For example, the 
`doUpgrade` method in the Knowledgebase Portlet's 
[`UpgradeProcess_1_0_0` class](https://github.com/liferay/liferay-plugins/blob/6.2.x/portlets/knowledge-base-portlet/docroot/WEB-INF/src/com/liferay/knowledgebase/hook/upgrade/UpgradeProcess_1_0_0.java) 
runs the upgrade steps `UpgradeRatingsEntry` and `UpgradeRatingsStats` via the 
`upgrade` method: 

    @Override
    protected void doUpgrade() throws Exception {
        upgrade(UpgradeRatingsEntry.class);
        upgrade(UpgradeRatingsStats.class);
    }

Now that you know how Liferay Portal 6 upgrade processes are defined, you'll 
learn how to convert them to the new upgrade process framework in @product-ver@. 

## Converting your Liferay Portal 6 Upgrade Process to @product-ver@ [](id=converting-your-liferay-portal-6-upgrade-process-to-product-ver)

So how do Liferay Portal 6 upgrade processes compare to those that use the new 
upgrade process framework in @product-ver@? First, the upgrade step classes are 
the same, so you can leave them unchanged. The big change in @product-ver@'s new 
upgrade processes is that upgrade process classes no longer exist. Instead, you 
must combine your upgrade process classes' functionality into a single 
registrator class. Recall from 
[the @product-ver@ upgrade process tutorial](/develop/tutorials/-/knowledge_base/7-0/creating-an-upgrade-process-for-your-app#writing-the-upgrade-step-registrator) 
that registrators define an upgrade process for the upgrade process framework to 
execute. Each `registry.register` call in the registrator registers the 
appropriate upgrade steps for each schema version. You must therefore transfer 
the functionality of your old upgrade process classes' `doUpgrade` methods into 
a registrator's `registry.register` calls. 

For example, 
[click here](https://github.com/liferay/liferay-portal/tree/7.0.x/modules/apps/knowledge-base/knowledge-base-service/src/main/java/com/liferay/knowledge/base/internal/upgrade) 
to see the Knowledgebase Portlet's new @product-ver@ upgrade process in GitHub. 

![Figure 2: The Knowledgebase Portlet's new @product-ver@ upgrade process.](../../../../images/upgrade-process-7-0.png)

Besides some additional upgrade step classes to handle changes made to the 
portlet for @product-ver@, the only difference in this upgrade process is that 
it contains a single registrator class, `KnowledgeBaseServiceUpgrade`, instead 
of multiple upgrade process classes. 
[The `KnowledgeBaseServiceUpgrade` class](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/knowledge-base/knowledge-base-service/src/main/java/com/liferay/knowledge/base/internal/upgrade/KnowledgeBaseServiceUpgrade.java), 
like all registrators, calls the appropriate upgrade steps for each schema 
version in its `registry.register` calls. For example, the first 
`registry.register` call registers the upgrade process for the `1.0.0` schema 
version: 

    registry.register(
            "com.liferay.knowledge.base.service", "0.0.1", "1.0.0",
            new com.liferay.knowledge.base.internal.upgrade.v1_0_0.
                UpgradeRatingsEntry(),
            new com.liferay.knowledge.base.internal.upgrade.v1_0_0.
                UpgradeRatingsStats());

Compare this to the above `doUpgrade` method from 
[the corresponding Liferay Portal 6 upgrade process class `UpgradeProcess_1_0_0`](https://github.com/liferay/liferay-plugins/blob/6.2.x/portlets/knowledge-base-portlet/docroot/WEB-INF/src/com/liferay/knowledgebase/hook/upgrade/UpgradeProcess_1_0_0.java). 
Both call the same upgrade steps. 
[Click here](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/knowledge-base/knowledge-base-service/src/main/java/com/liferay/knowledge/base/internal/upgrade/KnowledgeBaseServiceUpgrade.java) 
to see the complete `KnowledgeBaseServiceUpgrade` registrator class and all its 
`registry.register` calls. 

That's it! For instructions on creating new upgrade processes for @product-ver@, 
including complete steps on creating a registrator, 
[click here](/develop/tutorials/-/knowledge_base/7-0/creating-an-upgrade-process-for-your-app). 

## Related Topics [](id=related-topics)

[Creating an Upgrade Process for your App](/develop/tutorials/-/knowledge_base/7-0/creating-an-upgrade-process-for-your-app)

[Upgrading Plugins to @product-ver@](/develop/tutorials/-/knowledge_base/7-0/upgrading-plugins-to-liferay-7)

[From Liferay Portal 6 to 7](/develop/tutorials/-/knowledge_base/7-0/from-liferay-6-to-liferay-7)
