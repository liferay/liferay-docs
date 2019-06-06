---
header-id: upgrading-your-test-server-and-database
---

# Upgrading Your Test Server and Database

[TOC levels=1-4]

After you've [pruned your database and tested it
successfully](/docs/7-2/deploy/-/knowledge_base/d/pruning-the-database),
it's ready for upgrade. Here you'll install @product-ver@ and migrate your
current installation files to it and upgrade them. Then you'll optimize your
database for the upgrade and upgrade your data. Lastly, you'll test this
upgraded test environment. You may run into issues that require you to start
again with backup of your pruned database. After you're satisfied with the test
upgrade, you can prepare for upgrading production. Start with preparing
@product-ver@ on a test server. 

## Install Liferay on a Test Server and Configure It to Use the Pruned Database 

[Prepare a new test server with @product-ver@](/docs/7-2/deploy/-/knowledge_base/d/preparing-a-new-product-server-for-data-upgrade). 
Configure it to use the pruned database copy---keep the original backup in case
you want to restart test upgrades on a copy of it. You'll use the new test
server's Liferay upgrade tool next. 

## Tune Your Database for the Upgrade 

[Tune your database for the upgrade](/docs/7-2/deploy/-/knowledge_base/d/tuning-for-the-data-upgrade). 

## Upgrade the Database 

Upgrade the database to @product-ver@ (see
[Upgrade the Database](/docs/7-2/deploy/-/knowledge_base/d/upgrading-the-product-data));
then return here. 

If the upgrade took too long, search the upgrade log to identify more unused
objects. Then retry these steps with a fresh copy of the production database. 

## Test the Upgraded Portal and Resolve Any Issues 

Test this upgraded @product-ver@ instance and resolve any issues. If you can't
resolve an issue, retry these steps with a fresh copy of the production
database. 

## Checkpoint: You've Pruned and Upgraded a Production Database Copy 

By removing unused objects from @product@ in your test environment, you've made
upgrading feasible to do in production. You identified unused objects,
documented/scripted removing them, and successfully upgraded the @product@
database copy. 

It's time to prepare your production environment for upgrading. 
