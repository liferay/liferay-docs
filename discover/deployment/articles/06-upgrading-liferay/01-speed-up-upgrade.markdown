# Pre upgrade - Speed up the process [](id=pre-upgrade-speed-up-the-process)

The most critical step in upgrading @product@ is running the process
that upgrades the database from the old version to the new version. It
takes a long time to restructure the data to the new format.

You can shorten this process by performing a few steps before you run
the upgrade.

1.  Use your most recent complete backup to create a non-production
    environment containing your production data.
2.  Analyze your database (explained more fully below).
3.  Use Liferay's API to delete unused content.
4.  Run the upgrade process.
5.  Check the upgrade log to find the processes that took the most time.
6.  For the slowest upgrade processes, try to remove unused data. This
    improves performance regardless of the upgrade. If you see a
    potential issue, feel free to open a support ticket and have Liferay
    verify your analysis.
7.  Repeat steps 4, 5, and 6 as needed.

Here are more in-depth details on several of the steps above.

## Analyzing Your Database (Step 2) [](id=analyzing-your-database-step-2)

You need two things from your database to analyze it properly:

-   Records per table
-   Size per table

This can help you find tables that could be reduced in size to improve
upgrade performance.

For example, a typical database import looks like this:

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

Several items stand out:

-   The *JournalArticle* table's size makes up 98% of the size of the
    whole database.
-   There are a lot of *resourcePermission* objects.
-   There are a lot of *portletPreferences* objects.

Your analysis of your database shows you the objects you need to
optimize, but be careful: objects have relationships, and some objects
you wouldn't expect to affect performance may do so indirectly because
of their relationships to other objects.

## Remove unused data from the database using Liferay's API (Step 4) [](id=remove-unused-data-from-the-database-using-liferays-api-step-4)

Never run SQL directly on your database because you can miss
relationships, creating orphaned objects and performance problems.
Instead, use Liferay's API or Control Panel UI. You can do this through
the Control Panel's script console or by creating a portlet.

Here are some common optimizations you can make:

-   **Sites**: Remove sites you don't need. When you remove the site,
    you also remove all related data:

    -   Layouts

    -   Portlet preferences

    -   File entries (document library objects)

    -   Asset Entries

    -   Tags

    -   Vocabularies and categories

    -   Expando fields and their values

    -   *ResourcePermission* objects

    -   (and everything else)

-   **Instances**: It's not common to have unneeded instances, but since
    they are the highest object in the hierarchy, you can save upgrade
    time by removing their objects:

    -   Sites (and all their related content)

    -   Users

    -   Roles

    -   Organizations

    -   Global *resourcePermission* objects

-   **Intermediate web content versions:** Liferay generates a new web
    content version after any modification (including translations). You
    may not want to keep all these versions. If so, remove those
    versions before the upgrade. Removing an article also removes
    related objects like images added as part of the content
    (*journalArticleImage*), which impacts the file system's size.

-   **Document versions**: As with Journal articles, if you don't need
    intermediate document versions, delete them. This saves space both
    in the database and on the file system, space that no longer needs
    to be upgraded.

-   **Layouts:** Layouts are page groups, and they affect upgrade
    performance because they relate to other entities like portlet
    preferences, perrmissions, assets, ratings, and more.

-   **Roles**: Remove any roles you don't need. This also deletes
    related *resourceBlockPermission* and *resourcePermission* objects.

-   **Users:** If you have users that aren't active anymore, remove
    them.

-   **Vocabularies**: Remove any unused vocabularies. Take into account
    that removing a vocabulary also removes its categories.

-   **Orphaned data**: You should also check for objects that are no
    longer connected to anything. For example,

    -   `DLFileEntries` with no file system data.

    -   `ResourcePermission` objects associated to a role, layout, user,
        portlet instances, etc. that don't exist anymore.

    -   `PortletPreference` objects associated with a portlet or layout
        that don't exist anymore. This is common in environments with
        many embedded portlets. These portlet instances have a
        different lifecycle, and aren't deleted when the portlet is
        removed from a template.

## Execute the upgrade process (Step 5) [](id=execute-the-upgrade-process-step-5)

One of the upgrade improvements made in DXP is improved upgrade time
logging. An upgrade process now looks like this::

    21:42:45,422 INFO \[main\]\[UpgradeProcess:83\] Upgrading com.liferay.portal.upgrade.v7\_0\_0.UpgradeRatings

    21:42:45,423 INFO \[main\]\[LoggingTimer:70\] Starting com.liferay.portal.upgrade.v7\_0\_0.UpgradeRatings\#upgradeRatingsEntry

    21:42:47,154 INFO \[main\]\[LoggingTimer:38\] Completed com.liferay.portal.upgrade.v7\_0\_0.UpgradeRatings\#upgradeRatingsEntry in 1731 ms

    21:42:47,154 INFO \[main\]\[LoggingTimer:70\] Starting com.liferay.portal.upgrade.v7\_0\_0.UpgradeRatings\#upgradeRatingsStats

    21:44:10,069 INFO \[main\]\[LoggingTimer:38\] Completed com.liferay.portal.upgrade.v7\_0\_0.UpgradeRatings\#upgradeRatingsStats in 82915 ms

    21:44:10,070 INFO \[main\]\[UpgradeProcess:98\] Completed upgrade process com.liferay.portal.upgrade.v7\_0\_0.UpgradeRatings in 84648ms

This helps you focus on optimizing the processes that take the most
time.

If you want to understand how the upgrade process works, check out our
[tutorial on the subject.](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/creating-an-upgrade-process-for-your-app)

## Conclusion [](id=conclusion)

By removing unused content from the database, you can both reduce
upgrade time and improve your server's performance on the new version.

Taking the time to optimize your installation before upgrading can save
time and keep your installation running smoothly.
