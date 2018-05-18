# Backing up a @product@ Installation [](id=backing-up-a-liferay-installation)

Once you have an installation of @product@ running, you should implement a
comprehensive backup plan. In case some kind of catastrophic hardware failure
occurs, you'll be thankful to have backups and procedures for restoring
@product@ from one of them. @product@ isn't very different from other Java web
application that might be running on your application server. Nevertheless,
there are some specific components you should include in your backup plan.

The recommended backup plan includes backing up these things:

-   Source code
-   @product@'s file System
-   @product@'s database

## Backing up Source Code [](id=backing-up-source-code)

If you have extended @product@ or have written any plugins, they should be
stored in a source code repository such as Git, Subversion, or CVS, unless
you're Linus Torvalds, and then tarballs are okay too (that's a joke). You
should back up your source code repository on a regular basis to preserve your
ongoing work. This probably goes without saying in your organization since
nobody wants to lose source code that's taken months to produce. Thus you should
include source code in your @product@ backup plan.

Next, let's examine the @product@ installation items you should back up.

## Backing up @product@'s File System [](id=backing-up-liferays-file-system)

The
[Liferay Home folder](/discover/deployment/-/knowledge_base/7-1/installing-product#liferay-home)
stores @product@'s properties configuration files, such as `portal-setup-
wizard.properties` and `portal-ext.properties`. You should absolutely back them
up. In fact, it's best to back up your entire application server and  Liferay
Home folder contents.

@product@ stores configuration files, search indexes, and cache information in
Liferay Home's `/data` folder. If you're using the File System store or the
Advanced File System store, the documents and media repository is also stored
here by default. It's always important to back up your `/data` folder.

The files that comprise @product@'s OSGi runtime are stored in Liferay Home's
`/osgi` folder. It contains all of the app and module JAR files deployed to
@product@. The `/osgi` folder also contains other required JAR files,
configuration files, and log files. It's also important to back up your `/osgi`
folder.

Liferay Home's `/logs` folder contains @product@'s log files. If a problem
occurs on @product@, the @product@ log files often provide valuable information
for determining what went wrong. The `/data`, `/osgi`, and `/logs` folders are
all contained in the Liferay Home folder. Thus, if you're backing up both your
application server folder and your Liferay Home folder, you're in good shape.

Remember that if you've configured the document library to store files to a
location other than the default location, you should also back up that location.

That covers the @product@ file system locations you should back up. Next, let's
discuss how to back up @product@'s database.

## Backing up @product@'s Database [](id=backing-up-liferays-database)

@product@'s database is the central repository for all of the portal's
information. It's the most important component to back up. You can back up the
database live (if your database allows this) or by exporting (dumping) the
database into a file and then backing up the exported file. For example, MySQL
ships with a `mysqldump` utility which lets you export the entire database and
data into a large SQL file. This file can then be backed up. On restoring the
database you can import this file into the database to recreate the database
state to that of the time you exported the database.

If you're storing @product@'s Documents and Media Library files to a Jackrabbit
JSR-170 repository database, you should back it up. If you've placed your search
index into a database (not recommended; see the
[@product@ Clustering](/discover/deployment/-/knowledge_base/7-1/liferay-clustering) 
article for information on using Cluster Link or Solr), you should back up that
database too. 

If you wish to avoid re-indexing your content after restoring your database,
back up your search indexes. This is easiest to do if you have a separate
Elastic or Solr environment on which your index is stored. If you're in a
clustered configuration and you're replicating indexes, you'll need to back up
each index replica.

Restoring your application server, your Liferay Home folder, the locations of
any file system-based media repositories, and your database from a backup system
should give you a functioning portal. Restoring search indexes should avoid the
need to re-index when you bring your site back up after a catastrophic failure.
Good, consistent backup procedures are key to recovering successfully from a
hardware failure.
