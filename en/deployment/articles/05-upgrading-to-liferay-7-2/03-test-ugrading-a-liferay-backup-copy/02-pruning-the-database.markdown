---
header-id: pruning-the-database
---

# Pruning the Database

[TOC levels=1-4]

Accumulating unneeded site data is common. For example, you may have many unused
versions of Web Content articles or Documents and Media files. If you're done
revising them and don't need the intermediate revisions, you can remove them.
This saves you space and upgrade time. Here you'll remove unneeded data and then
test your server. 

## Remove Duplicate Web Content Structure Field Names

If you've used Web Content Management extensively, you might have structures
without unique field names. Find and remove duplicate field names before
upgrading. If you upgraded to Liferay Portal 6.2 previously and skipped doing
this, you'll encounter this error: 

    19:29:35,298 ERROR [main][VerifyProcessTrackerOSGiCommands:221] com.liferay.portal.verify.VerifyException: com.liferay.dynamic.data.mapping.validator.DDMFormValidationException$MustNotDuplicateFieldName: The field name page cannot be defined more than once
    com.liferay.portal.verify.VerifyException: com.liferay.dynamic.data.mapping.validator.DDMFormValidationException$MustNotDuplicateFieldName: The field name page cannot be defined more than once
 
If this is the case, roll back to your previous backup of Liferay Portal 6.2 and
find and remove duplicate field names. 

## Find and Remove Unused Objects

In the UI or using database queries, identify unused objects. Then remove them
via Liferay's UI or using Liferay's API through the 
[script console](/docs/7-2/user/-/knowledge_base/u/running-scripts-from-the-script-console)
or a portlet you create. 

| **Important**: You should only use Liferay's UI or API because they account 
| for relationships between @product@ objects. 
|
| Never use SQL directly on your database to remove records. Your SQL might miss
| object relationships, orphaning objects and causing performance problems.

Here are some common places to check for unused objects.

### Objects From the Large/Populated Tables

Table rows are mapped to @product@ objects. Large tables with many records might
contain lots of unused objects. The greater the table size and the records per
table, the longer upgrading takes. 

Finding and removing unused objects associated with such tables reduces upgrade
times. Your data import log (from the previous step) can provide valuable table
information. Database engines show this information in different ways. Your
database import log might look like this:

    Processing object type SCHEMA\_EXPORT/TABLE/TABLE\_DATA

    imported "LIFERAY"."JOURNALARTICLE" 13.33 GB 126687 rows

    imported "LIFERAY"."RESOURCEPERMISSION" 160.9 MB 1907698 rows

    imported "LIFERAY"."PORTLETPREFERENCES" 78.13 MB 432285 rows

    imported "LIFERAY"."LAYOUT" 52.05 MB 124507 rows

    imported "LIFERAY"."ASSETENTRY" 29.11 MB 198809 rows

    imported "LIFERAY"."MBMESSAGE" 24.80 MB 126185 rows

    imported "LIFERAY"."PORTALPREFERENCES" 4.091 MB 62202 rows

    imported "LIFERAY"."USER\_" 17.32 MB 62214 rows
    
    ...

Several items stand out in the example database import:

-   The `JOURNALARTICLE` table makes up 98% of the database size.
-   There are many `RESOURCEPERMISSION` records.
-   There are many `PORTLETPREFERENCES` records.

Search for unused objects associated with the tables that stand out and use
Liferay's API (e.g., the UI or
[script console](/docs/7-2/user/-/knowledge_base/u/running-scripts-from-the-script-console))
to delete the objects. 

### Common Object Types Worth Checking 

Some object types should be checked for unused objects. Here are some reasons
for checking them:

-   Removing them frees related unused objects for removal
-   They're version objects that aren't worth keeping

Check these object types: 

-   **Sites**: Remove sites you don't need. When you remove a site,
    remove its related objects:

    -   Layouts

    -   Portlet preferences

    -   File entries (document library objects)

    -   Asset Entries

    -   Tags

    -   Vocabularies and categories

    -   Expando fields and their values

    -   `ResourcePermission` objects

    -   (and everything else)

-   **Instances**: Unused instances are rare, but since they are the highest
    object in the hierarchy, removing their objects can optimize upgrades
    considerably:

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
    your database and file system. For more details, see [Example: Removing
    Intermediate Journal Article
    Versions](/docs/7-2/deploy/-/knowledge_base/d/example-removing-intermediate-journal-article-versions).  

-   **Document versions**: As with Journal Articles, if you don't need 
    intermediate document versions, delete them. This saves space both in the
    database and on the file system, space that no longer needs to be upgraded. 

-   **Layouts:** Layouts are site pages, and they affect upgrade performance
    because they relate to other entities such as portlet preferences,
    permissions, assets, ratings, and more. Remove unneeded layouts. 

-   **Roles**: Remove any Roles you don't need. Deleting them also deletes
    related `ResourceBlockPermission` and `ResourcePermission` objects.

-   **Users:** If you have Users that aren't active anymore, remove them.

-   **Vocabularies**: Remove any unused vocabularies. Note that removing a
    vocabulary also removes its categories.

-   **Orphaned data**: Check for unused objects that are not connected to
    anything. Here are some examples:

    -   `DLFileEntries` with no file system data.

    -   `ResourcePermission` objects associated to a Role, Layout, User, portlet
        instance, etc. that no longer exists.

    -   `PortletPreference` objects associated with a portlet or layout that
        no longer exists. This is common in environments with many embedded
        portlets. These portlet instances have a different lifecycle and aren't
        deleted when the portlet is removed from a template.

If you want to see an example of removing intermediate object versions, read
[Example: Removing Intermediate Journal Article
Versions](/docs/7-2/deploy/-/knowledge_base/d/example-removing-intermediate-journal-article-versions)
and then return here. 

Next, you'll test @product@ with its pruned database. 

## Test with the Pruned Database Copy

Find and resolve any issues related to the objects you removed. You can always
restart pruning a new copy of your production database if you can't resolve an
issue. 

| **Warning:** the upgrade to @product@ 7.2 moves Web Content images to the 
| Document Library and then deletes their former table `JournalArticleImage`.
| Make sure the images show in the upgraded Web Content articles. 

Once you've successfully tested @product@ with its pruned database copy, you can
upgrade the database to @product-ver@. 
