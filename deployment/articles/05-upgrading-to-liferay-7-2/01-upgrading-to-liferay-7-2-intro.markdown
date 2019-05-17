---
header-id: upgrading-to-product-ver
---

# Upgrading to @product-ver@

[TOC levels=1-4]

Upgrading to @product-ver@ involves migrating your installation and code (your
custom apps) to the new version. Here you'll learn how to upgrade your
installation.

Here are the installation upgrade paths:

| Upgrade Path                                   | Description                |
| ---------------------------------------------- | -------------------------- |
| Liferay Portal 5.x and 6.0.x &rarr; Liferay Portal 6.2 | Support life ended for Liferay Portal 5.0, 5.1, 5.2, and 6.0 |
| Liferay Portal 6.1.x &rarr; @product@ 7.1      | Support life ended for Liferay Portal 6.1 |
| Liferay Portal 6.2+ &rarr; @product@ 7.2       |                            |
| @product@ 7.0+ &rarr; @product@ 7.2            |                            |

| **Note:** Liferay Portal 6.0+ *code* can be upgraded directly to @product@ 
| 7.2.

Here are the upgrade steps: 

| **Note:** You can 
| [prepare a new Liferay server for data upgrade](/docs/7-2/deploy/-/knowledge_base/d/preparing-to-upgrade-the-product-database)
| in parallel with the steps up to and including the step to
| [upgrading the @product@ data](/docs/7-2/deploy/-/knowledge_base/d/upgrading-the-product-data). 

1.  [If You're Upgrading to Liferay Portal 6.2, Follow the Liferay Portal 6.2 Upgrade Instructions First](/docs/6-2/deploy/-/knowledge_base/d/upgrading-liferay)  

2.  [If You're Upgrading a Sharded Environment, Follow the Instructions for Upgrading It](/docs/7-2/deploy/-/knowledge_base/d/upgrading-a-sharded-environment)

    Upgrading a sharded installation to @product-ver@ requires migrating it to
    as many non-sharded @product@ installations (servers) as you have shards. 

3.  [If You're a Upgrading a Cluster, Read Those Instructions First](/docs/7-2/deploy/-/knowledge_base/d/updating-a-cluster)

    If you're updating a cluster, read those instructions first and apply them
    to your upgrade. 

4.  [Plan for Handling the Deprecated Applications](/docs/7-2/deploy/-/knowledge_base/d/planning-for-deprecated-applications)

    Every application deprecation has different ramifications. Learn how the
    deprecations might affect your site and decide how to replace the
    functionality you use from those applications. 

5.  [Test Upgrading a @product@ Backup Copy](/docs/7-2/deploy/-/knowledge_base/d/test-upgrading-a-product-backup-copy)

    Here you'll prune a backup copy of your database and upgrade the data.
    You'll learn how to use the upgrade tool and resolve upgrade issues. The
    notes and scripts you assemble as you prune and upgrade the database copy
    are invaluable for correctly and efficiently upgrading the @product@
    database you'll use with @product-ver@. 

    1.  [Copy the Production Installation to a Test Server](/docs/7-2/deploy/-/knowledge_base/d/test-upgrading-a-product-backup-copy#copy-the-production-installation-to-a-test-server)

        You'll use the installation copy to test data changes. 
 
    2. [Copy the Production Database Backup](/docs/7-2/deploy/-/knowledge_base/d/test-upgrading-a-product-backup-copy#copy-the-production-backup-to-the-test-database)

        Copy the production backup to the test database and save the copy logs
        for analysis. 

    3.  [Remove Duplicate Web Content and Structure Field Names](/docs/7-2/deploy/-/knowledge_base/d/pruning-the-database)

    4.  [Find and Remove Unused Objects](/docs/7-2/deploy/-/knowledge_base/d/pruning-the-database)

        You may have intermediate versions of objects (e.g., `JournalArticle`
        objects) that you don't need. Remove them and objects that only
        reference them. 

    5.  [Test @product@ with its Pruned Database Copy](/docs/7-2/deploy/-/knowledge_base/d/pruning-the-database#test-product-with-its-pruned-database-copy)

        Make sure @product@ continues to work successfully. If it's broken,
        start over with a fresh database backup and prune it more carefully. 

    6.  [Install the New  @product@ Version on a Test Server](/docs/7-2/deploy/-/knowledge_base/d/upgrading-your-test-server-and-database)

        Install the @product@ version you're upgrading to, to use its upgrade
        tool. 
        
    7.  [Tune Your Database for the Upgrade](/docs/7-2/deploy/-/knowledge_base/d/tune-your-database-for-the-upgrade)

    8. [Upgrade the Liferay Data, then Return Here](/docs/7-2/deploy/-/knowledge_base/d/upgrading-the-product-data)

    9.  [If the Upgrade Took too Long, Prune a Fresh Database Backup More and 
        Upgrade Its Data](/docs/7-2/deploy/-/knowledge_base/d/test-upgrading-a-product-backup-copy#copy-the-production-backup-to-the-test-database)

    10. [Test the Upgraded Instance](/docs/7-2/deploy/-/knowledge_base/d/test-upgrading-a-product-backup-copy#test-the-upgraded-portal-and-resolve-any-issues)

        Make sure @product@ continues to work successfully. If it's broken,
        start over with a fresh database backup and prune it more carefully. 

    11. Checkpoint: You've pruned and upgraded your production database copy. 
        You're ready to prepare for upgrading the production database. 

6.  [Prepare to Upgrade the @product@ Database](/docs/7-2/deploy/-/knowledge_base/d/preparing-to-upgrade-the-product-database)

    Preparing for the production database upgrade involves pruning and testing
    it, upgrading your Marketplace apps, publishing staged changes, and
    synchronizing a complete data and configuration backup. 

    1.  [Remove All Noted Unused Objects](/docs/7-2/deploy/-/knowledge_base/d/preparing-to-upgrade-the-product-database#remove-all-unused-objects-you-identified-earlier)

        Remove all unused objects you noted from pruning your test database.

    2.  [Test @product@](/docs/7-2/deploy/-/knowledge_base/d/preparing-to-upgrade-the-product-database#test-liferay-portal-with-its-pruned-database)

    3.  [Upgrade Your Marketplace Apps](/docs/7-2/deploy/-/knowledge_base/d/preparing-to-upgrade-the-product-database#upgrade-your-marketplace-apps)

    4.  [Publish All Staged Changes](/docs/7-2/deploy/-/knowledge_base/d/preparing-to-upgrade-the-product-database#publish-all-staged-changes-to-production)

    5.  [Synchronize a Complete @product@ Backup](/docs/7-2/deploy/-/knowledge_base/d/preparing-to-upgrade-the-product-database#synchronize-a-complete-liferay-portal-backup)

        Synchronize a complete backup of your production @product@ server
        installation and pruned production database.

    6.  Checkpoint: You're ready to prepare a @product-ver@ server for 
        upgrading a production database. 

7.  [Prepare a New @product@ Server for Data Upgrade](/docs/7-2/deploy/-/knowledge_base/d/preparing-a-new-product-server-for-data-upgrade)

    Set up a production server with @product-ver@, configured to use your
    document repository and @product@ database. You'll migrate your portal and
    system properties too. (Note, this step can be done in parallel with any of
    the previous steps.)

    1.  [Request an Upgrade Patch From Liferay Support \(Liferay DXP Only\)](/docs/7-2/deploy/-/knowledge_base/d/preparing-to-upgrade-the-product-database#synchronize-a-complete-liferay-portal-backup)

    2.  [Install the @product@ Version You're Upgrading To](/docs/7-2/deploy/-/knowledge_base/d/preparing-a-new-product-server-for-data-upgrade#install-liferay-portal-ce-72)

    3.  [Install the Latest Upgrade Patch or Fix Pack \(Liferay DXP Only\)](/docs/7-2/deploy/-/knowledge_base/d/preparing-a-new-product-server-for-data-upgrade#install-the-latest-upgrade-patch-or-fix-pack-liferay-dxp-only)

    4.  [Migrate Your OSGi Configurations \(@product@ 7.0+\)](/docs/7-2/deploy/-/knowledge_base/d/preparing-a-new-product-server-for-data-upgrade#migrate-your-osgi-configurations-liferay-portal-70)

    5.  [Migrate Your Portal Properties](/docs/7-2/deploy/-/knowledge_base/d/preparing-a-new-product-server-for-data-upgrade#migrate-your-portal-properties)

        Migrate your portal properties to your new @product-ver@ server. 

        1.  [Update Your Portal Properties](/docs/7-2/deploy/-/knowledge_base/d/preparing-a-new-product-server-for-data-upgrade#update-your-portal-properties)

            Some of the portal properties have new values or have been removed
            or replaced. Update your properties for @product-ver@. 

        2.  [Convert Applicable Properties to OSGi Configurations](/docs/7-2/deploy/-/knowledge_base/d/preparing-a-new-product-server-for-data-upgrade#convert-applicable-properties-to-osgi-configurations)

            Many applications are configured using OSGi Configuration (Config
            Admin) instead of portal properties. Convert your existing
            properties to their OSGi Configuration replacements. 

    6.  [Configure Your Documents and Media File Store](/docs/7-2/deploy/-/knowledge_base/d/preparing-a-new-product-server-for-data-upgrade#configure-your-documents-and-media-file-store)

        The upgrade tool upgrades your Documents and Media file store too.
        Update your Documents and Media file store configuration and specify it
        for the upgrade tool. 

    7.  [Disable Indexing](/docs/7-2/deploy/-/knowledge_base/d/preparing-a-new-product-server-for-data-upgrade#disable-indexing)

        Improve the data upgrade performance by disabling indexing.

    8.  Checkpoint: You've prepared a new @product@ server for executing the 
        data upgrade 

8.  [Upgrade the @product@ data](/docs/7-2/deploy/-/knowledge_base/d/upgrading-the-product-data)

    This section explains the data upgrade options, upgrade configuration,
    and the upgrade process.

    1.  [Tune Your Database for the Upgrade](/docs/7-2/deploy/-/knowledge_base/d/tuning-your-database-for-the-upgrade)

    2.  [Configure the Data Upgrade](/docs/7-2/deploy/-/knowledge_base/d/configuring-the-data-upgrade#configuring-the-data-upgrade)

        Configure the data upgrade, including the data store and whether to
        automatically upgrade the modules.

    3.  [Upgrade the Core](/docs/7-2/deploy/-/knowledge_base/d/upgrading-the-core-using-the-upgrade-tool#upgrading-the-core-using-the-upgrade-tool)

        1.  [Run the Data Upgrade Tool](/docs/7-2/deploy/-/knowledge_base/d/upgrading-the-core-using-the-upgrade-tool#upgrading-the-core-using-the-upgrade-tool#upgrade-tool-usage)

            Run the data upgrade tool. Resolve any core upgrade issues.

        2.  [Issues Upgrading to 7.0 or Lower? Restore the Database Backup](/docs/7-2/deploy/-/knowledge_base/d/preparing-to-upgrade-the-product-database#synchronize-a-complete-liferay-portal-backup)

            If the issues were with upgrades to Liferay 7.0 or lower, get a
            clean start by restoring the pruned production database backup.

        3.  [Upgrade Your Resolved Issues](/docs/7-2/deploy/-/knowledge_base/d/upgrading-the-core-using-the-upgrade-tool#upgrading-the-core-using-the-upgrade-tool)

            If there were issues upgrading to 7.2, resolve them and restart the
            data upgrade tool; continue if there were no issues.

    4.  [Upgrade the Liferay Modules](/docs/7-2/deploy/-/knowledge_base/d/upgrading-modules-using-gogo-shell#upgrading-modules-using-gogo-shell)

        Learn how to use Gogo Shell to upgrade the Liferay modules, if you 
        didn't upgrade them automatically with the core.

        1.  [Upgrade Modules that are Ready for Upgrade](/docs/7-2/deploy/-/knowledge_base/d/upgrading-modules-using-gogo-shell#command-usage)

            Discover which modules are ready for upgrade and upgrade them.

        2.  [Check Module Upgrade Status and Resolve Any Module Upgrade Issues](/docs/7-2/deploy/-/knowledge_base/d/upgrading-modules-using-gogo-shell#checking-upgrade-status)

        3.  Checkpoint: You've completed upgrading the Liferay data. It's time
            to get your server ready for production.

9.  [Execute the Post-Upgrade Tasks](/deployment/-/knowledge_base/executing-post-upgrade-tasks)

    Now that your database is upgraded, clean up remnants of upgrading by
    restoring your database optimizations, enabling and regenerating your search
    indexes, and more. 

    1.  [Remove the Database Tuning](/docs/7-2/deploy/-/knowledge_base/d/executing-post-upgrade-tasks#tuning-your-database-for-production)

    2.  [Re-enable and Re-Index the Search Indexes](/docs/7-2/deploy/-/knowledge_base/d/executing-post-upgrade-tasks#re-enabling-search-indexing-and-re-indexing-search-indexes)

    3.  [Update Web Content Permissions \(7.0 and lower\)](/docs/7-2/deploy/-/knowledge_base/d/executing-post-upgrade-tasks#enabling-web-content-view-permissions)

    4.  [Address Any Deprecated Apps That Still Need Handling](/docs/7-2/deploy/-/knowledge_base/d/planning-for-deprecated-applications#planning-for-deprecated-applications)

10.  Checkpoint: You've completed the upgrade and post-upgrade tasks

Follow the steps above to upgrade your @product@ installation to @product-ver@.
They link upgrade topic details to help complete a safe, successful upgrade. 
