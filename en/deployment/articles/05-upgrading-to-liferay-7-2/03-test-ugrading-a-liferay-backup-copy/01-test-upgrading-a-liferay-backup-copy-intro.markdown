---
header-id: test-upgrading-a-product-backup-copy
---

# Test Upgrading a @product@ Backup Copy

[TOC levels=1-4]

Before upgrading your production Liferay instance, you should do a trial run
(even multiple runs) to make sure that you upgrade successfully and efficiently.
Here's the process:

-   [Preparing a test server and
    database](#preparing-a-test-server-and-database): This involves copying your
    current production installation to a test server and copying your production
    data backup to a test database. After you prune data from the test database
    (next step) you'll test against it. 

-   [Pruning the
    database](/docs/7-2/deploy/-/knowledge_base/d/pruning-the-database):
    Free your database of duplicate and unused objects. By removing them you can
    reduce upgrade time and improve your server's performance. 

-   [Upgrading your test server and
    database](/docs/7-2/deploy/-/knowledge_base/d/upgrading-your-test-server-and-database):
    First you'll optimize your database for the data upgrade. Taking time to do
    this can save upgrade time. Then you'll do an upgrade test run (or several
    test runs) on a the pruned database copy. After going through the upgrade
    process, resolving any issues, and testing the upgraded server successfully,
    you can confidently upgrade your production database. 

| **Tip:** These steps and
| [preparing a new @product@ server](/docs/7-2/deploy/-/knowledge_base/d/preparing-a-new-product-server-for-data-upgrade)
| can be done in parallel to save time. 

Now prepare your test environment. 

## Preparing a Test Server and Database

Using a new separate server and database let's you safely test upgrading. 

### Copy the Production Installation to a Test Server

Prepare a test server to use a copy of your production installation. Your test
server must use the same Liferay version you're using on production. Configure
your server to use a new empty database for testing data upgrades. 

### Copy the Production Backup to the Test Database

Import data from your [production database
backup](/docs/7-2/deploy/-/knowledge_base/d/backing-up-a-liferay-installation)
to the new empty database. 

| **Important:** Make sure to save the data import log---you'll examine it in
| the next steps. 

Next you'll prune your database of unneeded data. 
