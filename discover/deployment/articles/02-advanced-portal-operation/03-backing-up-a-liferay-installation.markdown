# Backing up a Liferay Installation [](id=backing-up-a-liferay-installation)

Once you have an installation of Liferay Portal running, you'll want to have
proper backup procedures in place in case of a catastrophic hardware failure of
some kind. Liferay isn't very different from any other application that may be
running on your application server. Nevertheless, there are some specific
components you should include in your backup plan.

## Backing up Source Code [](id=backing-up-source-code)

If you have extended Liferay or have written any plugins, they should be stored
in a source code repository such as Git, Subversion, or CVS, unless you're Linus
Torvalds, and then tarballs are okay too (that's a joke). Your source code
repository should be backed up on a regular basis to preserve your ongoing work.
This probably goes without saying in your organization, as nobody wants to lose
source code that's taken months to produce, but we thought we should mention it
anyway. 

If you're extending Liferay with an Ext plugin, you'll want to make sure you
also store the version of the Liferay source on which your extension environment
is based. This allows your developers convenient access to all the tools they
need to build your extension and deploy it to a server.

Let's look at the items that need to be backed up in your Liferay installation. 

## Backing up Liferay's File System [](id=backing-up-liferays-file-system)

Liferay's configuration file, `portal-ext.properties`, gets stored in the
*Liferay Home* folder, which is generally one folder up from where your
application server is installed (see the [Installation and
Setup](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/installation-and-setup-liferay-portal-6-2-user-guide-15-en)
chapter of this guide for specific details for your application server). At a
minimum, this file should be backed up, but it is generally best to back up your
whole application server.

If you've followed the non-plugin procedure (see chapter 19) to modify your
Ehcache configuration, you'll have cache configuration files in the deploy
location of Liferay. You'll need to back up this location. If you're using the
plugin procedure (i.e., the recommended procedure), your cache configuration
settings are stored in your source code repository, which is backed up
separately. 

Liferay stores configuration files, search indexes, and cache information in a
folder called `/data` in Liferay Home. If you're using the File System store or
the Advanced File System store, the media repository is stored here (by default)
too. You should always back up the contents of your Liferay Home folder.

If you've modified the location where the Document Library stores files, you
should also back up this location.

That covers the file system locations Liferay uses. Next, let's discuss how to
back up Liferay's database.

## Backing up Liferay's Database [](id=backing-up-liferays-database)

Liferay's database is the central repository for all of the Portal's information
and is the most important component that needs to be backed up. You can do this
by backing up the database live (if your database allows this) or by exporting
the database and then backing up the exported file. For example, MySQL ships
with a `mysqldump` utility which allows you to export the entire database and
data into a large SQL file. This file can then be backed up. In case of a
database failure, this file can be used to recreate the state of the database at
the time the dump was created.

If you're using Liferay's Documents and Media Library with the Jackrabbit
JSR-170 repository to store documents in a database, the Jackrabbit database
should be backed up also. If you've placed your search index into a database
(not recommended; see chapter 19 for information on using Cluster Link or Solr),
that database should be backed up as well. 

Search indexes can be backed up as well, if you wish to avoid reindexing your
entire portal after you do your restore. This is easiest to do if you have a
separate Solr environment upon which your index is stored. If you're in a
clustered configuration and you're replicating indexes, you'll need to back up
each index replica. 

Restoring your application server, your Liferay Home folder, the locations of
any file system-based media repositories, and your database from a backup system
should give you a functioning portal. Restoring search indexes should avoid the
need to reindex when you bring your site back up after a catastrophic failure.
Good, consistent backup procedures are key to successfully recovering from a
hardware failure. 

But what about maintenance while your server is running? Liferay lets you view a
lot of what is going on through its logging system.
