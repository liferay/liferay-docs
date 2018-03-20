# Pre upgrade - Speed up the process [](id=pre-upgrade-speed-up-the-process)

The most complex step in upgrading @product@ is running the process
that upgrades the database from the old version to the new version. It
takes a long time to restructure the data to the new format.

You can shorten this process by performing a few steps before you upgrade your
production environment. Here's a summary of the steps:

1.  Copy your most
    [recent complete backup](/discover/deployment/-/knowledge_base/7-1/backing-up-a-liferay-installation)
    from production to a non-production environment in which you can analyze
    your database and test upgrading, as explained in the remaining steps. 
2.  [Examine your database](#analyzing-your-database-step-2).
3.  [Use Liferay's API to delete unused content](#remove-unused-data-from-the-database-using-liferays-api-step-3).
4.  [Run the upgrade process on your non-production environment](#execute-the-upgrade-process-step-4).
5.  Check the upgrade log for the processes that took the most time.
6.  Remove unused content from the upgrade processes that took the longest. If
    you see a potential issue, analyze it and contact the community if you need
    help. If you have an enterprise subscription, feel free to open a support
    ticket and have Liferay verify your analysis.
7.  Repeat steps 4, 5, and 6 as needed.
8.  [Remove unused content from production](remove-unused-objects-from-production-step-8). 

The sections that follow explain the more in-depth steps listed above.

## Examine Your Database (Step 2) [](id=analyzing-your-database-step-2)

Here are the most important things to examine in your non-production
environment's copy of the production database:

-   Records per table
-   Size per table (optional)

The greater these values are the longer upgrade processes take. 

The database engines show this information in different ways. Sometimes the
output from importing backup data into your non-production database shows  each
table's size and number of rows (records).

For example, output from a typical database import looks like this:

    Processing object type SCHEMA\_EXPORT/TABLE/TABLE\_DATA

    imported "LIFERAY"."JOURNALARTICLE" 13.33 GB 126687 rows

    imported "LIFERAY"."RESOURCEPERMISSION" 160.9 MB 1907698 rows

    imported "LIFERAY"."PORTLETPREFERENCES" 78.13 MB 432285 rows

    imported "LIFERAY"."LAYOUT" 52.05 MB 124507 rows

    imported "LIFERAY"."ASSETENTRY" 29.11 MB 198809 rows

    imported "LIFERAY"."MBMESSAGE" 24.80 MB 126185 rows

    imported "LIFERAY"."PORTALPREFERENCES" 4.091 MB 62202 rows

    imported "LIFERAY"."USER\_" 17.32 MB 62214 rows

    imported "LIFERAY"."USERS\_ROLES" 15.63 MB 1117225 rows

    imported "LIFERAY"."LAYOUTSET" 11.50 MB 124442 rows
     
    imported "LIFERAY"."MBTHREAD" 11.99 MB 126184 rows
     
    imported "LIFERAY"."COUNTER" 6.161 MB 123699 rows
     
    imported "LIFERAY"."CONTACT\_" 5.233 MB 62214 rows
     
    imported "LIFERAY"."GROUP\_" 3.772 MB 62221 rows

    imported "LIFERAY"."ASSETTAGPROPERTY" 1.460 MB 21417 rows
     
    imported "LIFERAY"."MBDISCUSSION" 3.138 MB 126179 rows
     
    imported "LIFERAY"."JOURNALARTICLEIMAGE" 1.015 MB 22523 rows
     
    imported "LIFERAY"."USERS\_GROUPS" 897.1 KB 62218 rows
     
    imported "LIFERAY"."IMAGE" 480.9 KB 13492 rows
     
    imported "LIFERAY"."JOURNALSTRUCTURE" 135.9 KB 12 rows
     
    imported "LIFERAY"."JOURNALTEMPLATE" 230.6 KB 44 rows
     
    imported "LIFERAY"."DLFILEENTRY" 195.3 KB 1028 rows
     
    imported "LIFERAY"."DLFILEVERSION" 196.8 KB 1350 rows
     
    imported "LIFERAY"."JOURNALARTICLERESOURCE" 272.6 KB 4665 rows
     
    imported "LIFERAY"."JOURNALCONTENTSEARCH" 107.1 KB 2038 rows
     
    imported "LIFERAY"."ASSETENTRIES\_ASSETTAGS" 90.89 KB 6118 rows
     
    imported "LIFERAY"."COUNTRY" 15.33 KB 227 rows
     
    imported "LIFERAY"."DLFILERANK" 12.83 KB 135 rows
     
    imported "LIFERAY"."QUARTZ\_JOB\_DETAILS" 19.70 KB 2 rows
     
    imported "LIFERAY"."QUARTZ\_TRIGGERS" 11.64 KB 1 rows
     
    imported "LIFERAY"."TICKET" 147.9 KB 1666 rows
     
    imported "LIFERAY"."ASSETTAG" 74.31 KB 908 rows

    imported "LIFERAY"."CLASSNAME\_" 13.36 KB 148 rows
     
    imported "LIFERAY"."COMPANY" 9.210 KB 1 rows
     
    imported "LIFERAY"."RATINGSSTATS" 71.67 KB 2566 rows
     
    imported "LIFERAY"."ACCOUNT\_" 11.35 KB 1 rows
     
    imported "LIFERAY"."ANNOUNCEMENTSDELIVERY" 13.85 KB 192 rows
     
    imported "LIFERAY"."ASSETCATEGORY" 11.17 KB 2 rows
     
    imported "LIFERAY"."ASSETENTRIES\_ASSETCATEGORIES" 5.539 KB 1 rows
     
    imported "LIFERAY"."ASSETTAGSTATS" 20.82 KB 666 rows

    imported "LIFERAY"."ASSETVOCABULARY" 11.03 KB 6 rows
     
    imported "LIFERAY"."DLFOLDER" 35.81 KB 195 rows

    imported "LIFERAY"."LAYOUTPROTOTYPE" 7.359 KB 1 rows
     
    imported "LIFERAY"."QUARTZ\_CRON\_TRIGGERS" 6.367 KB 1 rows
     
    imported "LIFERAY"."RESOURCEACTION" 42.84 KB 806 rows
     
    imported "LIFERAY"."SERVICECOMPONENT" 25.28 KB 1 rows
     
    imported "LIFERAY"."SOCIALACTIVITY" 45.42 KB 734 rows
     
    imported "LIFERAY"."WEBDAVPROPS" 7.820 KB 2 rows
     
    imported "LIFERAY"."WIKIPAGE" 14.68 KB 1 rows
     
    imported "LIFERAY"."EXPANDOTABLE" 6.656 KB 9 rows
     
    imported "LIFERAY"."IGFOLDER" 10.17 KB 8 rows
     
    imported "LIFERAY"."IGIMAGE" 18.61 KB 61 rows
     
    imported "LIFERAY"."JOURNALFEED" 24.22 KB 38 rows
     
    imported "LIFERAY"."LISTTYPE" 9.554 KB 63 rows
     
    imported "LIFERAY"."MBCATEGORY" 10.75 KB 1 rows
     
    imported "LIFERAY"."MBMAILINGLIST" 15.96 KB 1 rows
     
    imported "LIFERAY"."MBMESSAGEFLAG" 7.304 KB 1 rows
     
    imported "LIFERAY"."PASSWORDPOLICY" 19.06 KB 1 rows
     
    imported "LIFERAY"."PORTLET" 11.15 KB 136 rows
     
    imported "LIFERAY"."QUARTZ\_LOCKS" 5.117 KB 5 rows
     
    imported "LIFERAY"."QUARTZ\_SCHEDULER\_STATE" 6 KB 1 rows
     
    imported "LIFERAY"."REGION" 15.27 KB 316 rows
     
    imported "LIFERAY"."RELEASE\_" 8.281 KB 1 rows
     
    imported "LIFERAY"."ROLE\_" 17.17 KB 115 rows
     
    imported "LIFERAY"."SHARD" 6.382 KB 1 rows
     
    imported "LIFERAY"."SUBSCRIPTION" 8.648 KB 2 rows
     
    imported "LIFERAY"."USERGROUPROLE" 5.992 KB 2 rows
     
    imported "LIFERAY"."USERS\_ORGS" 5.531 KB 2 rows
     
    imported "LIFERAY"."VIRTUALHOST" 6.398 KB 1 rows
     
    imported "LIFERAY"."WIKINODE" 9.453 KB 2 rows
     
    imported "LIFERAY"."WIKIPAGERESOURCE" 6.382 KB 1 rows

Several items stand out in the example database import:

-   The *JOURNALARTICLE* table makes up 98% of the database size.
-   There are many *RESOURCEPERMISSION* records.
-   There are many *PORTLETPREFERENCES* records.

The table records reflect @product@ objects. Using the API to delete objects
that you no longer need not only deletes each object's data record but also
deletes related unneeded objects (and their data records). For example, deleting
an unneeded `Group` object also deletes related unneeded layouts, journal
articles, and more. This reduces the number of records your upgrade needs to
process, making your upgrade faster. 

## Use Liferay's API to remove unused objects (Step 3) [](id=remove-unused-data-from-the-database-using-liferays-api-step-3)

Your database analysis revealed tables that were large or contained lots of
records. It's recommended to find unneeded objects that can be deleted by
examining objects associated with these tables. Also there are some common areas
(listed below) to look for unneeded objects. 

+$$$

**Important**: You should only use
Liferay's API--[Core API](@platform-ref@/7.1-latest/javadocs/)
and [app APIs](@app-ref@)--
to delete objects because the API accounts for relationships between @product@
objects. You can invoke the API through the Control Panel's script console or a
portlet you create. 

Never run SQL directly on your database to remove records. Your SQL might miss
object relationships, resulting in orphaned objects and performance problems.

$$$

Here are some common areas to find unneeded objects:

-   **Sites**: Remove sites you don't need. When you remove a site,
    remove its related data:

    -   Layouts

    -   Portlet preferences

    -   File entries (document library objects)

    -   Asset Entries

    -   Tags

    -   Vocabularies and categories

    -   Expando fields and their values

    -   `ResourcePermission` objects

    -   (and everything else)

-   **Instances**: Unused instances are rare, but since
    they are the highest object in the hierarchy, removing their objects can
    optimize upgrades considerably:

    -   Sites (and all their related content)

    -   Users

    -   Roles

    -   Organizations

    -   Global `ResourcePermission` objects

-   **Intermediate web content versions:** @product@ generates a new web
    content version after any modification (including translations). Consider
    removing versions you don't need. Removing a Journal Article, for example,
    also removes related objects such as image files (`JournalArticleImage`)
    that are part of the content. Removing unneeded image files frees space in
    your database and file system. 

-   **Document versions**: As with Journal Articles, if you don't need
    intermediate document versions, delete them. This saves space both
    in the database and on the file system, space that no longer needs
    to be upgraded.

-   **Layouts:** Layouts are site pages, and they affect upgrade performance
    because they relate to other entities such as portlet preferences,
    permissions, assets, ratings, and more. Remove unneeded layouts. 

-   **Roles**: Remove any roles you don't need. Deleting them also deletes
    related `ResourceBlockPermission` and `ResourcePermission` objects.

-   **Users:** If you have users that aren't active anymore, remove them.

-   **Vocabularies**: Remove any unused vocabularies. Note that removing a
    vocabulary also removes its categories.

-   **Orphaned data**: Check for unused objects that are not connected to
    anything. Here are some examples:

    -   `DLFileEntries` with no file system data.

    -   `ResourcePermission` objects associated to a role, layout, user, portlet
        instances, etc. that no longer exists.

    -   `PortletPreference` objects associated with a portlet or layout that no
        no longer exists. This is common in environments with many embedded
        portlets. These portlet instances have a different lifecycle, and aren't
        deleted when the portlet is removed from a template.

After you've removed unneeded objects, test your changes. 

## Execute the upgrade process (Step 4) [](id=execute-the-upgrade-process-step-4)

It's time to upgrade your non-production environment and note what upgrade
processes take the longest. Each @product@ upgrade process logs how long it
takes. An upgrade log now looks like this:

    21:42:45,422 INFO \[main\]\[UpgradeProcess:83\] Upgrading com.liferay.portal.upgrade.v7\_0\_0.UpgradeRatings

    21:42:45,423 INFO \[main\]\[LoggingTimer:70\] Starting com.liferay.portal.upgrade.v7\_0\_0.UpgradeRatings\#upgradeRatingsEntry

    21:42:47,154 INFO \[main\]\[LoggingTimer:38\] Completed com.liferay.portal.upgrade.v7\_0\_0.UpgradeRatings\#upgradeRatingsEntry in 1731 ms

    21:42:47,154 INFO \[main\]\[LoggingTimer:70\] Starting com.liferay.portal.upgrade.v7\_0\_0.UpgradeRatings\#upgradeRatingsStats

    21:44:10,069 INFO \[main\]\[LoggingTimer:38\] Completed com.liferay.portal.upgrade.v7\_0\_0.UpgradeRatings\#upgradeRatingsStats in 82915 ms

    21:44:10,070 INFO \[main\]\[UpgradeProcess:98\] Completed upgrade process com.liferay.portal.upgrade.v7\_0\_0.UpgradeRatings in 84648ms

The duration times (in milliseconds) facilitate finding the most time consuming
processes. Consider searching for unneeded objects associated these longer
upgrade processes. Once again, make sure to delete them using Liferay's API and
test your changes. 

**Note**: Learning [how upgrade processes are created](/develop/tutorials/-/knowledge_base/7-1/creating-an-upgrade-process-for-your-app)
can help you understand their data better.

## Remove Unused Objects from Production (Step 8) [](id=remove-unused-objects-from-production-step-8)

Now that you have removed unused objects from your non-production environment
and tested your changes, you can use Liferay's API to remove the same objects
from your production environment. By removing the objects from production and
testing your changes before upgrading, you can more easily troubleshoot any
issues, knowing that they're not related to upgrade processes. Another benefit
of doing this even while you work through the upgrade is that your production
environment will perform better and be easier to maintain.

## Conclusion [](id=conclusion)

By removing unused objects from @product@, you can both reduce
upgrade time and improve your server's performance on the new version.

Taking the time to optimize your installation before upgrading can save
time and keep your installation running smoothly.
