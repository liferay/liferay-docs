# Liferay's database [](id=liferays-database)

The recommended way of setting up your Liferay database also happens to be the
simplest. Liferay Portal takes care of just about everything. The only thing you
need to do is create a blank database encoded for character set UTF-8. The
reason for this is that Liferay is a multilingual application, and needs UTF-8
encoding to display all the character sets it supports. 

Next, create an ID for accessing this database and grant it all
rights--including the rights to create and drop tables--to the blank Liferay
database. This is the ID you'll use to connect to the Liferay database, and
you'll configure it later either in your application server or in Liferay's
properties file so that Liferay can connect to it. 

One of the first things Liferay Portal does when you bring it up for the first
time is create the tables it needs in the database you just created. It does
this automatically, complete with indexes. 

If you create your database and grant a user ID full access to it, Liferay can
use that user ID to create its indexes and tables automatically.  This is the
recommended way to set up Liferay, as it allows you to take advantage of
Liferay's ability to maintain its database automatically during upgrades or
through various plugin installs that create tables of their own. It is by far
the best way to set up your Liferay installation.

If you'll be setting up Liferay's database with the recommended permissions, you
can skip to the next section.

+$$$

**Note:** The below instructions are not the
recommended set up for Liferay installations, but the procedure is documented
here so enterprises with more restrictive standards can install Liferay with
more strict--but suboptimal--database settings. If it's at all possible,
Liferay recommends that you use the automatic method as documented above instead
of the procedure outlined below.

$$$

Even though Liferay can create its database automatically, some enterprises
prefer *not* to allow the user ID configured in an application server to have
the permissions over the database necessary for Liferay and its plugins to
maintain their tables. For these organizations, Select, Insert, Update and
Delete are the only permissions allowed so we will go over how to set up the
database manually. If your organization *is* willing to grant the Liferay user
ID permissions to create and drop tables in the database--and this is the
recommended configuration--by all means, use the recommended configuration. 

Creating the database is simple: grant the ID Liferay uses to access the
database full rights to do anything to the database. Then install Liferay and
have it create the database. Once the database is created, remove the
permissions for creating tables and dropping tables from the user ID.

There are some caveats to running Liferay like this. Many Liferay plugins create
new tables when they're deployed. In addition to this, Liferay has an automatic
database upgrade function that runs when Liferay is upgraded. If the user ID
that accesses the database doesn't have enough rights to create/modify/drop
tables in the database, you must grant those rights to the ID before you deploy
one of these plugins or start your upgraded Liferay for the first time. Once the
tables are created or the upgrade is complete, you can remove those rights until
the next deploy or upgrade. Additionally, your developers may create plugins
that need to create their own tables. These are just like Liferay's plugins that
do the same thing, and they cannot be installed if Liferay can't create these
tables automatically. If you wish to install these plugins, you will need to
grant rights to create tables in the database before you attempt to install
them. 

Once you have your database ready, you can install Liferay on your server.
