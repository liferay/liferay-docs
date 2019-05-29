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

-   Minimize transaction logging.

| **Note:** These options worked well for us on specific versions of each 
| database. Please consult your database vendor's documentation for 
| information on how to optimize executing updates on your specific database 
| version. 

| **Important:** Test your database configuration to determine tuning that's 
| best for your system, and consult your DBA as appropriate. **Never** use
| database upgrade configurations in production. Always restore your production
| database settings before starting your @product@ server for production use 
| with the database. 

| **Warning:** Some database properties and configurations are global and affect
| schemas in the same database. 

These configurations were optimal for upgrading data in a Liferay 6.2 EE
installation that had these characteristics: 

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

Start with configuring the database upgrade tool's Java process. 

## Tuning the Database Upgrade Java Process

Make sure to provide adequate memory for the database upgrade tool's Java
process. 15GB was appropriate for the test scenario. Also make sure to set the
file encoding to UTF-8 and the time zone to GMT. Here are the Java process
settings:

-   Xmx 15 GB RAM 
-   File encoding UTF-8 
-   User time zone GMT

Here is the `db_upgrade.sh` command:

```bash
db_upgrade.sh -j "-Xmx15000m -Dfile.encoding=UTF-8 -Duser.timezone=GMT"
```

It's time to tune your database transaction engine. 

## Tuning the Database Transaction Engine for Executing Updates 

Many more update statements are executed during data upgrade than in production.
Here's how to optimize each database's transaction engine for the updates. 

### IBM DB2 

Please consult IBM's official DB2 documentation. 

### MariaDB

In addition to the default database configuration, turn off InnoDB double-write.

### Microsoft SQL Server 

In addition to the default database configuration, set
[transaction durability](https://docs.microsoft.com/en-us/sql/relational-databases/logs/control-transaction-durability)
to `FORCED`. 

### MySQL 

In addition to the default database configuration, turn off [InnoDB
double-write](https://dev.mysql.com/doc/refman/5.7/en/innodb-parameters.html#sysvar_innodb_doublewrite). 

### Oracle Database 

The default configuration works well. It configures [asynchronous I/O to
disk](https://docs.oracle.com/database/121/REFRN/GUID-FD8D1BD2-0F85-4844-ABE7-57B4F77D1608.htm#REFRN10048)
automatically. 

### PostgreSQL 

In addition to the default database configuration, turn off [synchronous
commits](https://www.postgresql.org/docs/10/wal-async-commit.html). 

## Tuning the Database Transaction Log

In production, transaction logs mark safe states to roll back to. In data
upgrades, however, the safe state is the original data backup. Since transaction
logging is insignificant for data upgrades, it should be disabled or minimized.
Here are log tuning instructions for each database. 

### IBM DB2 

Please consult IBM's official DB2 documentation. 

### MariaDB

In addition to the default database configuration, set the InnoDB flush log at
transaction commit to `0`. 

### Microsoft SQL Server 

Use the default database configuration. 

### MySQL 

In addition to the default database configuration, set the [InnoDB flush log at
transaction
commit](https://dev.mysql.com/doc/refman/5.7/en/innodb-parameters.html#sysvar_innodb_flush_log_at_trx_commit)
to `0`. 

### Oracle Database 

Use the default database configuration. 

### PostgreSQL 

In addition to the default database configuration, Set the [write ahead log
writer delay](https://www.postgresql.org/docs/10/wal-async-commit.html) to
`1000` milliseconds. 

Congratulations! You have a starting point to plan your own @product@ data
upgrade project. Remember, optimal tuning depends on your data, infrastructure
conditions, and database vendor. Analyze your data, tune for upgrade, and time
your test upgrades. Use this information to determine the best database and Java
process configuration for your @product@ data upgrade. 
