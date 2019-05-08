---
header-id: tuning-for-the-data-upgrade
---

# Tuning for the Data Upgrade

[TOC levels=1-4]

Upgrading impacts the database differently from daily running in production.
Because of this, you should tune your database for the upgrade process before
you run it, and then re-apply your production settings after the upgrade
completes. 

-   Data upgrades execute many more update statements (`INSERT`, `UPDATE`, and
    `DELETE`) and less `SELECT` statements than production instances. When
    upgrading, tune your database for executing updates. 

-   Data upgrades should be done in safe environments completely separate from 
    production servers and should use database backup copies. If upgrade errors
    occur or you make mistakes, they don't impact production, and you can always
    restart using your database backup copy. 

The data upgrade tuning instructions given here are a starting point for tuning
your @product@ data upgrade. They account for data upgrade activities and a safe
data upgrade environment: 

-   Deactivate data integrity measures that impact performance. Restore the 
    backup if failures occur. 

-   Make commit-related transaction I/O operations asynchronous. 

-   Increase the interval to flush commits to disk. 

-   Increase the log file size accordingly. 

| **Important:** Test your database configuration to determine tuning that's 
| best for your system, and consult your DBA as appropriate. **Never** use
| database upgrade configurations in production. Always restore your production
| database settings before starting your @product@ server for production use 
| with the database. 

The configurations you'll see here were optimal for upgrading data in a Liferay
6.2 EE installation that had these characteristics: 

-   3.2 GB database 

-   15 GB Document Library 

-   Content translated in 3 languages

-   Record count for most populated entities:

    -   1,694,000 rating entries
    -   1,605,000 permissions (`ResourcePermission` objects)
    -   871,000 assets (`AssetEntry` objects)
    -   400,000 users 
    -   400,000 sites (`Group` objects)
    -   402,000 images 
    -   259,000 message forum threads and posts 
    -   200,000 documents 
    -   193,000 portlet preferences 
    -   103,000 web content pieces (`JournalArticle` objects) 
    -   50,600 pages 
    -   3,276 journal article images 
    -   3,100 document folders 

Start with configuring the database upgrade tool. 

## Tuning the Database Upgrade Java Process

Make sure to provide adequate memory for the database upgrade tool's Java
process. 15GB was appropriate for the test scenario. Also make sure to set the
file encoding to UTF-8 and the time zone to GMT. Here are the Java process
settings:

-   Xmx  15 GB RAM 
-   File encoding UTF-8 
-   User time zone GMT

Here is the `db_upgrade.sh` command:

```bash
db_upgrade.sh -j "-Xmx15000m -Dfile.encoding=UTF-8 -Duser.timezone=GMT"
```

It's time to tune your database for the data upgrade. 

## Database Tuning 

Database vendors provide different ways to tune their database servers. The
following sections describe vendor-specific settings that were optimal for
upgrading the Liferay Portal installation described earlier. 

| **Important:** The following database configurations are only valid for 
| @product@ *data upgrades*. **Never** use these configurations to *run* 
| @product@. 

| **Warning:** Some database properties and configurations are global and affect
| schemas in the same database. 

### Microsoft SQL Server 

In addition to the default database configuration, set
[transaction durability](https://docs.microsoft.com/en-us/sql/relational-databases/logs/control-transaction-durability)
to `FORCED`. 

### PostgreSQL 

In addition to the default database configuration, apply these settings: 

-   Set
    [synchronous commit](https://www.postgresql.org/docs/10/wal-async-commit.html)
    to off. 

-   Set the
    [write ahead log writer delay](https://www.postgresql.org/docs/10/wal-async-commit.html)
    to 1000 ms. 

### Oracle Database 

The default configuration works well. It configures
[asynchronous I/O to disk](https://docs.oracle.com/database/121/REFRN/GUID-FD8D1BD2-0F85-4844-ABE7-57B4F77D1608.htm#REFRN10048)
automatically. 

### MySQL 

In addition to the default database configuration, apply these settings: 

-   Set the
    [InnoDB flush log at transaction commit property](https://docs.oracle.com/database/121/REFRN/GUID-FD8D1BD2-0F85-4844-ABE7-57B4F77D1608.htm#REFRN10048)
    to 0. 

-   Set the
    [InnoDB double-write property](https://dev.mysql.com/doc/refman/5.7/en/innodb-parameters.html#sysvar_innodb_doublewrite)
    to off. 

Congratulations! You have a starting point to plan your own @product@ data
upgrade project. Remember, optimal tuning depends on your data, infrastructure
conditions, and database vendor. Analyze your data, tune for upgrade, and time
your test upgrades. Use this information to determine the best database and Java
process configuration for your @product@ data upgrade. 
