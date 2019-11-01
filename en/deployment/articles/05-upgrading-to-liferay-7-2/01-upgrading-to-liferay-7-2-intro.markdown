---
header-id: upgrading-to-product-ver
---

# Upgrading to @product-ver@

[TOC levels=1-4]

Upgrading to @product-ver@ involves migrating your installation and [code (your
theme and custom
apps)](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-code-to-product-ver) to
the new version. Here you'll learn how to upgrade your installation.

Here are the installation upgrade paths:

| Upgrade Path                                   | Description                |
| ---------------------------------------------- | -------------------------- |
| Liferay Portal 5.x and 6.0.x &rarr; Liferay Portal 6.2 &rarr; @product@ 7.2 | Support life ended for Liferay Portal 5.0, 5.1, 5.2, and 6.0 |
| Liferay Portal 6.1.x &rarr; @product@ 7.1 &rarr; @product@ 7.2      | Support life ended for Liferay Portal 6.1 |
| Liferay Portal 6.2+ &rarr; @product@ 7.2       |                            |
| @product@ 7.0+ &rarr; @product@ 7.2            |                            |

| **Note:** Themes and custom apps from Liferay Portal 6.0 through @product@ 
| 7.1 can be upgraded directly to @product@ 7.2. See the
| [code upgrade instructions](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-code-to-product-ver)
| for details. 

Here are the upgrade steps: 

| **Note:** You can 
| [prepare a new Liferay server for data upgrade](/docs/7-2/deploy/-/knowledge_base/d/preparing-to-upgrade-the-product-database)
| in parallel with the steps up to and including the step to
| [upgrading the @product@ data](/docs/7-2/deploy/-/knowledge_base/d/upgrading-the-product-data). 

1.  {.root}[If You're Upgrading to Liferay Portal 6.2, Follow the Liferay Portal 6.2 Upgrade Instructions First](/docs/6-2/deploy/-/knowledge_base/d/upgrading-liferay){.title}

2.  [If You're Upgrading a Sharded Environment, Follow the Instructions for Upgrading It](/docs/7-2/deploy/-/knowledge_base/d/upgrading-a-sharded-environment){.title}

    Upgrading a sharded installation to @product-ver@ requires migrating it to
    as many non-sharded @product@ installations (servers) as you have shards.{.summary}

3.  [If You're a Upgrading a Cluster, Read Those Instructions First](/docs/7-2/deploy/-/knowledge_base/d/updating-a-cluster){.title}

    If you're updating a cluster, read those instructions first and apply them
    to your upgrade.{.summary}

4.  [Plan for Handling the Deprecated Applications](/docs/7-2/deploy/-/knowledge_base/d/planning-for-deprecated-applications){.title}

    Every application deprecation has different ramifications. Learn how the
    deprecations might affect your site and decide how to replace the
    functionality you use from those applications.{.summary}

5.  [Test Upgrading a @product@ Backup Copy](/docs/7-2/deploy/-/knowledge_base/d/test-upgrading-a-product-backup-copy){.title}

    Here you'll prune a backup copy of your database and upgrade the data.
    You'll learn how to use the upgrade tool and resolve upgrade issues. The
    notes and scripts you assemble as you prune and upgrade the database copy
    are invaluable for correctly and efficiently upgrading the @product@
    database you'll use with @product-ver@.{.summary}

    1.  [Copy the Production Installation to a Test Server](/docs/7-2/deploy/-/knowledge_base/d/test-upgrading-a-product-backup-copy#copy-the-production-installation-to-a-test-server){.title}

        You'll use the installation copy to test data changes.{.summary}
 
    2. [Copy the Production Database Backup](/docs/7-2/deploy/-/knowledge_base/d/test-upgrading-a-product-backup-copy#copy-the-production-backup-to-the-test-database){.title}

        Copy the production backup to the test database and save the copy logs
        for analysis.{.summary}

    3.  [Remove Duplicate Web Content and Structure Field Names](/docs/7-2/deploy/-/knowledge_base/d/pruning-the-database){.title}

    4.  [Find and Remove Unused Objects](/docs/7-2/deploy/-/knowledge_base/d/pruning-the-database){.title}

        You may have intermediate versions of objects (e.g., `JournalArticle`
        objects) that you don't need. Remove them and objects that only
        reference them.{.summary}

    5.  [Test @product@ with its Pruned Database Copy](/docs/7-2/deploy/-/knowledge_base/d/pruning-the-database#test-with-the-pruned-database-copy){.title}

        Make sure @product@ continues to work successfully. If it's broken,
        start over with a fresh database backup and prune it more carefully.{.summary}

    6.  [Install the New  @product@ Version on a Test Server](/docs/7-2/deploy/-/knowledge_base/d/upgrading-your-test-server-and-database){.title}

        Install the @product@ version you're upgrading to, to use its upgrade
        tool.{.summary}
        
    7.  [Tune Your Database for the Upgrade](/docs/7-2/deploy/-/knowledge_base/d/tuning-for-the-data-upgrade){.title}

    8.  [Upgrade the Liferay Data, then Return Here](/docs/7-2/deploy/-/knowledge_base/d/upgrading-the-product-data){.title}

    9.  [If the Upgrade Took too Long, Prune a Fresh Database Backup More and Upgrade Its Data](/docs/7-2/deploy/-/knowledge_base/d/test-upgrading-a-product-backup-copy#copy-the-production-backup-to-the-test-database){.title}

    10. [Test the Upgraded Instance](/docs/7-2/deploy/-/knowledge_base/d/pruning-the-database){.title}

        Make sure @product@ continues to work successfully. If it's broken,
        start over with a fresh database backup and prune it more carefully.{.summary}

    11. Checkpoint: You've pruned and upgraded your production database copy. 
        You're ready to prepare for upgrading the production database. 

6.  [Prepare to Upgrade the @product@ Database](/docs/7-2/deploy/-/knowledge_base/d/preparing-to-upgrade-the-product-database){.title}

    Preparing for the production database upgrade involves pruning and testing
    it, upgrading your Marketplace apps, publishing staged changes, and
    synchronizing a complete data and configuration backup.{.summary}

    1.  [Remove All Noted Unused Objects](/docs/7-2/deploy/-/knowledge_base/d/preparing-to-upgrade-the-product-database#remove-all-unused-objects-you-identified-earlier){.title}

        Remove all unused objects you noted from pruning your test database.{.summary}

    2.  [Test @product@](/docs/7-2/deploy/-/knowledge_base/d/preparing-to-upgrade-the-product-database#test-using-the-pruned-database){.title}

    3.  [Upgrade Your Marketplace Apps](/docs/7-2/deploy/-/knowledge_base/d/preparing-to-upgrade-the-product-database#upgrade-your-marketplace-apps){.title}

    4.  [Publish All Staged Changes](/docs/7-2/deploy/-/knowledge_base/d/preparing-to-upgrade-the-product-database#publish-all-staged-changes-to-production){.title}

    5.  [Synchronize a Complete @product@ Backup](/docs/7-2/deploy/-/knowledge_base/d/preparing-to-upgrade-the-product-database#synchronize-a-complete-backup){.title}

        Synchronize a complete backup of your production @product@ server
        installation and pruned production database.{.summary}

    6.  Checkpoint: You're ready to prepare a @product-ver@ server for 
        upgrading a production database. 

7.  [Prepare a New @product@ Server for Data Upgrade](/docs/7-2/deploy/-/knowledge_base/d/preparing-a-new-product-server-for-data-upgrade){.title}

    Set up a production server with @product-ver@, configured to use your
    document repository and @product@ database. You'll migrate your portal and
    system properties too. (Note, this step can be done in parallel with any of
    the previous steps.){.summary}

    1.  [Request an Upgrade Patch From Liferay Support \(Liferay DXP Only\)](/docs/7-2/deploy/-/knowledge_base/d/preparing-to-upgrade-the-product-database#synchronize-a-complete-backup){.title}

    2.  [Install the @product@ Version You're Upgrading To](/docs/7-2/deploy/-/knowledge_base/d/preparing-a-new-product-server-for-data-upgrade#install-liferay){.title}

    3.  [Install the Latest Upgrade Patch or Fix Pack \(Liferay DXP Only\)](/docs/7-2/deploy/-/knowledge_base/d/preparing-a-new-product-server-for-data-upgrade#install-the-latest-upgrade-patch-or-fix-pack-liferay-dxp-only){.title}

    4.  [Migrate Your OSGi Configurations \(@product@ 7.0+\)](/docs/7-2/deploy/-/knowledge_base/d/preparing-a-new-product-server-for-data-upgrade#migrate-your-osgi-configurations-70){.title}

    5.  [Migrate Your Portal Properties](/docs/7-2/deploy/-/knowledge_base/d/preparing-a-new-product-server-for-data-upgrade#migrate-your-portal-properties){.title}

        Migrate your portal properties to your new @product-ver@ server.{.summary}

        1.  [Update Your Portal Properties](/docs/7-2/deploy/-/knowledge_base/d/preparing-a-new-product-server-for-data-upgrade#update-your-portal-properties){.title}

            Some of the portal properties have new values or have been removed
            or replaced. Update your properties for @product-ver@.{.summary}

        2.  [Convert Applicable Properties to OSGi Configurations](/docs/7-2/deploy/-/knowledge_base/d/preparing-a-new-product-server-for-data-upgrade#convert-applicable-properties-to-osgi-configurations){.title}

            Many applications are configured using OSGi Configuration (Config
            Admin) instead of portal properties. Convert your existing
            properties to their OSGi Configuration replacements.{.summary}

    6.  [Configure Your Documents and Media File Store](/docs/7-2/deploy/-/knowledge_base/d/preparing-a-new-product-server-for-data-upgrade#configure-your-documents-and-media-file-store){.title}

        The upgrade tool upgrades your Documents and Media file store too.
        Update your Documents and Media file store configuration and specify it
        for the upgrade tool.{.summary}

    7.  [Disable Indexing](/docs/7-2/deploy/-/knowledge_base/d/preparing-a-new-product-server-for-data-upgrade#disable-indexing){.title}

        Improve the data upgrade performance by disabling indexing.{.summary}

    8.  Checkpoint: You've prepared a new @product@ server for executing the 
        data upgrade 

8.  [Upgrade the @product@ data](/docs/7-2/deploy/-/knowledge_base/d/upgrading-the-product-data){.title}

    This section explains the data upgrade options, upgrade configuration,
    and the upgrade process.{.summary}

    1.  [Tune Your Database for the Upgrade](/docs/7-2/deploy/-/knowledge_base/d/tuning-for-the-data-upgrade){.title}

    2.  [Configure the Data Upgrade](/docs/7-2/deploy/-/knowledge_base/d/configuring-the-data-upgrade){.title}

        Configure the data upgrade, including the data store and whether to
        automatically upgrade the modules.{.summary}

    3.  [Upgrade the Core](/docs/7-2/deploy/-/knowledge_base/d/upgrading-the-core-using-the-upgrade-tool){.title}

        1.  [Run the Data Upgrade Tool](/docs/7-2/deploy/-/knowledge_base/d/upgrading-the-core-using-the-upgrade-tool#upgrade-tool-usage){.title}

            Run the data upgrade tool. Resolve any core upgrade issues.{.summary}

        2.  [Issues Upgrading to 7.0 or Lower? Restore the Database Backup](/docs/7-2/deploy/-/knowledge_base/d/preparing-to-upgrade-the-product-database#synchronize-a-complete-backup){.title}

            If the issues were with upgrades to Liferay 7.0 or lower, get a
            clean start by restoring the pruned production database backup.{.summary}

        3.  [Upgrade Your Resolved Issues](/docs/7-2/deploy/-/knowledge_base/d/upgrading-the-core-using-the-upgrade-tool){.title}

            If there were issues upgrading to 7.2, resolve them and restart the
            data upgrade tool; continue if there were no issues.{.summary}

    4.  [Upgrade the Liferay Modules](/docs/7-2/deploy/-/knowledge_base/d/upgrading-modules-using-gogo-shell){.title}

        Learn how to use Gogo Shell to upgrade the Liferay modules, if you 
        didn't upgrade them automatically with the core.{.summary}

        1.  [Upgrade Modules that are Ready for Upgrade](/docs/7-2/deploy/-/knowledge_base/d/upgrading-modules-using-gogo-shell#command-usage){.title}

            Discover which modules are ready for upgrade and upgrade them.{.summary}

        2.  [Check Module Upgrade Status and Resolve Any Module Upgrade Issues](/docs/7-2/deploy/-/knowledge_base/d/upgrading-modules-using-gogo-shell#checking-upgrade-status){.title}

        3.  Checkpoint: You've completed upgrading the Liferay data. It's time
            to get your server ready for production.{.summary}

9.  [Execute the Post-Upgrade Tasks](/docs/7-2/deploy/-/knowledge_base/d/executing-post-upgrade-tasks){.title}

    Now that your database is upgraded, clean up remnants of upgrading by
    restoring your database optimizations, enabling and regenerating your search
    indexes, and more.{.summary}

    1.  [Remove the Database Tuning](/docs/7-2/deploy/-/knowledge_base/d/executing-post-upgrade-tasks#tuning-your-database-for-production){.title}

    2.  [Re-enable and Re-Index the Search Indexes](/docs/7-2/deploy/-/knowledge_base/d/executing-post-upgrade-tasks#re-enabling-search-indexing-and-re-indexing-search-indexes){.title}

    3.  [Update Web Content Permissions \(7.0 and lower\)](/docs/7-2/deploy/-/knowledge_base/d/executing-post-upgrade-tasks#enabling-web-content-view-permissions){.title}

    4.  [Address Any Deprecated Apps That Still Need Handling](/docs/7-2/deploy/-/knowledge_base/d/planning-for-deprecated-applications){.title}

10.  Checkpoint: You've completed the upgrade and post-upgrade tasks

Follow the steps above to upgrade your @product@ installation to @product-ver@.
They link upgrade topic details to help complete a safe, successful upgrade. 
