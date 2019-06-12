---
header-id: preparing-to-upgrade-the-product-database
---

# Preparing to Upgrade the @product@ Database

[TOC levels=1-4]

After testing the upgrade on a copy of your production database, you can apply
what you learned to your production database. 

| **Tip:** This step and
| [preparing a new @product@ server](/docs/7-2/deploy/-/knowledge_base/d/preparing-a-new-product-server-for-data-upgrade)
| can be done in parallel to save time. 

## Remove All Unused Objects You Identified Earlier

Previously you identified and removed unused objects from a copy of your
@product@ production database backup. In the same way (in the script console or
UI) you removed the unused objects from the backup, remove them from your
pre-upgrade production database. 

## Test Using the Pruned Database 

Find and resolve any issues related to the objects you removed. By removing the
objects from production and testing your changes before upgrading, you can more
easily troubleshoot issues, knowing that they're not related to upgrade
processes. 

## Upgrade Your Marketplace Apps 

Upgrade each Marketplace app (Kaleo, Calendar, Notifications, etc.) that you're
using to its latest version for your @product@ installation. Before proceeding
with the upgrade, troubleshoot any issues regarding these apps.

## Publish all Staged Changes to Production 

If you have
[local/remote staging enabled](/docs/7-2/user/-/knowledge_base/u/enabling-staging)
and have content or data saved on the staged site, 
[publish](/docs/7-2/user/-/knowledge_base/u/publishing-staged-content-efficiently)
it to the live site. If you skip this step, you must run a full publish (or
manually publish changes) after the upgrade, since the system won't know what
content changed since the last publishing date.

## Synchronize a Complete Backup 

[Completely back up your @product@ installation, pruned production database, and document repository](/docs/7-2/deploy/-/knowledge_base/d/backing-up-a-liferay-installation). 

It's time to prepare a new @product@ server. 
