---
header-id: upgrading-to-product-ver
---

# Upgrading to @product-ver@

[TOC levels=1-4]

Your sites are critical to you and Liferay wants to make sure your upgrade to
@product-ver@ is safe, complete, and efficient. Upgrading involves upgrading
your data and code (your custom apps). Here you'll learn how to upgrade your
data. 

Upgrading data comprises these main steps:

1.  *Plan for handling the deprecated applications:* Every application 
    deprecation has different ramifications. You'll learn how the deprecations
    might affect your site and decide how to replace the functionality you use
    from those application. 

2.  *Test upgrading a @product@ backup copy:* Here you'll prune a backup copy of
    your database and upgrade the data. You'll learn how to use the upgrade tool
    and resolve upgrade issues. The notes and scripts you assemble as you prune
    and upgrade the database copy are invaluable for correctly and efficiently
    upgrading @product@ database you'll use with @product-ver@. 

3.  *Prepare to upgrade the @product@ database:* Next you'll prune your 
    @product@ database, upgrade your marketplace apps, publish staged data, and
    make final backups.  

4.  *Prepare a new @product@ server:* Involves setting up a production server 
    with @product-ver@, configured to use your document repository and @product@ database. You'll migrate your portal and system properties too. (Note, this step can be done in parallel with any of the previous steps.)

5.  *Upgrade the @product@ database:* Upgrade your latest @product@ database, 
    based on what you learned from upgrading the database backup copy in step 2.  

7.  *Post-upgrade tasks:* Now that your database is upgraded, clean up remnants 
    of upgrading by restoring your database optimizations, enabling and
    regenerating your search indexes, and more. 

Here are the data upgrade paths: 

| Data Upgrade Path                              | Description                |
| ---------------------------------------------- | -------------------------- |
| Liferay Portal 5.x and 6.0.x &rarr; Liferay Portal 6.2 | Support life ended for Liferay Portal 5.0, 5.1, 5.2, and 6.0 |
| Liferay Portal 6.1.x &rarr; @product@ 7.1      | Support life ended for Liferay Portal 6.1 |
| Liferay Portal 6.2+ &rarr; @product@ 7.2       |                            |
| @product@ 7.0+ &rarr; @product@ 7.2            |                            |

| **Note:** Liferay Portal 6.0+ *code* can be upgraded directly to @product@ 
| 7.2.

Liferay guides you through each of the upgrade steps, so you can start running
on @product-ver@ as soon as possible. 
