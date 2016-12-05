# Backing up a Liferay Installation [](id=backing-up-a-liferay-installation)

Once you have an installation of @product@ running, you should implement a
comprehensive backup plan. You'll be very thanking for your backup procedures
in case some kind of catastrophic hardware failure occurs. @product@ isn't very
different from any other Java web application that might be running on your
application server. Nevertheless, there are some specific components you should
include in your backup plan.

## Backing up Source Code [](id=backing-up-source-code)

If you have extended @product@ or have written any plugins, they should be stored
in a source code repository such as Git, Subversion, or CVS, unless you're
Linus Torvalds, and then tarballs are okay too (that's a joke). Your source
code repository should be backed up on a regular basis to preserve your ongoing
work. This probably goes without saying in your organization since nobody wants
to lose source code that's taken months to produce. Nevertheless, it's
important to include source code in a Liferay backup plan.

Next, let's examine the items that need to be backed up in your Liferay
installation.

## Backing up Liferay's File System [](id=backing-up-liferays-file-system)

Liferay's properties configuration files, such as
`portal-setup-wizard.properties` and `portal-ext.properties`, are stored in the
*Liferay Home* folder, which is generally one folder up from where your
application server is installed (see the [Installation and Setup (not yet
written)]() article for specific details for your application server). Your
Liferay installation's properties configuration files should absolutely be
backed up. In fact, it's best to back up your entire application server and
entire the contents of your Liferay Home folders.

If you've followed the non-plugin procedure (see the [(not yet written)]()
article) to modify your Ehcache configuration, you'll have cache configuration
files in the deploy location of @product@. You'll need to back up this location.
If you're using the plugin procedure (i.e., the recommended procedure), your
cache configuration settings are stored in your source code repository, which
should be backed up separately.

@product@ stores configuration files, search indexes, and cache information in a
folder called `/data` in Liferay Home. If you're using the File System store or
the Advanced File System store, the documents and media repository (a.k.a.,
document library) is also stored here by default. It's always import to back up
your `/data` folder.

The files that comprise @product@'s OSGi runtime are stored in a folder called
`/osgi` in Liferay Home. This folder contains the JAR files for all of the apps
and modules that you've deployed to Liferay. The `/osgi` folder also contains
other required JAR files, configuration files, and log files. It's also
important to back up your `/osgi` folder.

The `/logs` folder in Liferay Home contains Liferay's log files. Liferay's log
files are important to back up since if a problem occurs on Liferay, the
information in Liferay's log files often provides valuable information for
determining what went wrong. The `/data`, `/osgi`, and `/logs` folders are all
contained in the Liferay Home folder. Thus, if you're backing up both your
application server folder and your Liferay Home folder, you're in good shape.

Remember that if you've modified the location where the document library stores
files, you should also back up this location.

That covers the file system locations used by Liferay. Next, let's discuss how
to back up Liferay's database.

## Backing up Liferay's Database [](id=backing-up-liferays-database)

@product@'s database is the central repository for all of the portal's
information. It's the most important component that needs to be backed up. You
can do this by backing up the database live (if your database allows this) or
by exporting the database and then backing up the exported file. For example,
MySQL ships with a `mysqldump` utility which allows you to export the entire
database and data into a large SQL file. This file can then be backed up. In
case of a database failure, this file can be used to recreate the state of the
database at the time the dump was created.

If you're using Liferay's Documents and Media Library with the Jackrabbit
JSR-170 repository to store documents in a database, the Jackrabbit database
should also be backed up. If you've placed your search index into a database
(not recommended; see the [(not yet written)]() article for information on
using Cluster Link or Solr), that database should be backed up as well

Search indexes can be backed up as well, if you wish to avoid reindexing your
entire portal after restoring your database. This is easiest to do if you have
a separate Solr environment on which your index is stored. If you're in a
clustered configuration and you're replicating indexes, you'll need to back up
each index replica.

Restoring your application server, your Liferay Home folder, the locations of
any file system-based media repositories, and your database from a backup system
should give you a functioning portal. Restoring search indexes should avoid the
need to reindex when you bring your site back up after a catastrophic failure.
Good, consistent backup procedures are key to successfully recovering from a
hardware failure.

If you're a Liferay EE subscriber, you should apply the patches provided by
Liferay whenever they're available. Liferay provides tools to make this process
easy. See the [(not yet written)]() article to learn how to apply patches to
your Liferay installation.
