# App Servers [](id=app-servers)

When it comes time to install Liferay Portal on your server, you'll find it's
easiest to do this by starting with a bundle. But many enterprises can't do
that. There may be an existing infrastructure into which you're installing
Liferay or you may have standardized on a particular application server. You'll
be happy to know that Liferay Portal has been designed to work well with all the
leading application servers and that it's easy and straightforward to install.
But before we get started, we need to go over a few concepts; namely, the
Liferay Home folder, databases and Liferay's main configuration file. These were
touched on in the section on bundles above but we'll look at them in more detail
now. 

## Liferay Home [](id=liferay-home)

Liferay Portal uses a special folder defined as *Liferay Home*. This folder is
one folder higher than the location of the application server itself. This is
why the bundles place the application server one folder in from the bundle's
root folder.

If Liferay is unable to create the resources it needs in this folder or if it
finds itself running on certain application servers, it creates a folder called
`liferay` in the home folder of the user ID that is running Liferay and that
becomes Liferay Home.

As described above in the *Bundles* section, the home folder is very important
to the operation of Liferay. The aforementioned folders (*data* and *deploy*)
are created there and you can also put a special configuration file called
`portal-ext.properties` there. This file is fully documented in chapter 20, a
reference for Liferay properties.

+$$$

**Note:** To use database properties from a `portal-ext.properties` file you 
must disable the Setup Wizard by specifying `setup.wizard.enabled=false` in that 
`portal-ext.properties`.  Also, note that property values in 
`portal-setup-wizard.properties` (the file created in Liferay Home by the Setup 
Wizard) override property values in `portal-ext.properties`. 

$$$

Let's move on to examining the database. 

## Liferay's Database [](id=liferays-database)

As stated above, if you create your database and grant a user ID full access to
it, Liferay can use that user ID to create its indexes and tables automatically.
This is the recommended way to set up Liferay, as it allows you to take
advantage of Liferay's ability to automatically maintain its database during
upgrades or through various plugin installs which may create tables of their
own. It is by far the best way to set up your Liferay installation.

If you'll be setting up Liferay's database with the recommended permissions, you
can skip to the next section.

+$$$

**Note:** This is not the recommended set up for Liferay installations but is 
documented here so enterprises with more restrictive standards can install 
Liferay with more strict -- but suboptimal -- database settings. If it's at all 
possible, Liferay recommends that you use the automatic method as documented 
above instead of the procedure outlined below.

$$$

Even though Liferay can create its database automatically, some enterprises
prefer *not* to allow the user ID configured in an application server to have
the permissions over the database necessary for Liferay and its plugins to
maintain their tables. For these organizations, Select, Insert, Update and
Delete are the only permissions allowed so we will go over how to set up the
database manually. If your organization *is* willing to grant the Liferay user
ID permissions to create and drop tables in the database -- and this is the
recommended configuration -- by all means, use the recommended configuration. 

Creating the database is simple: grant the ID Liferay uses to access the
database full rights to do anything to the database. Then install Liferay and
have it create the database. Once the database is created, remove the
permissions for creating tables and dropping tables from the user ID.

There are some caveats to running Liferay like this. Many Liferay plugins create
new tables when they're deployed. In addition to this, Liferay has an automatic
database upgrade function which runs when the version of Liferay is upgraded to
a new release. If the user ID that accesses the database doesn't have enough
rights to create/modify/drop tables in the database, you must grant those rights
to the ID before you deploy one of these plugins or start your upgraded Liferay
for the first time. Once the tables are created or the upgrade is complete, you
can remove those rights until the next deploy or upgrade. Additionally, your
developers may create plugins that need to create their own tables. These are
just like Liferay's plugins that do the same thing and they cannot be installed
if Liferay can't create these tables automatically. If you wish to install these
plugins, you will need to grant rights to create tables in the database before
you attempt to install them. 

Once you have your database ready, you can install Liferay on your server. 

## Liferay Installation Overview [](id=liferay-installation-overview)

Before we begin, it's important to go over the various facets of the
installation. They are: 

1. Create your database (see above). 

2. Determine whether you want to use the Liferay managed data source or a data
   source managed by your application server. The Liferay managed data source is
   recommended. 

3. Gather credentials for sending email notifications to users. Liferay supports
   a JNDI mail session as well as its built-in mail session. 

4. Install Liferay according to the instructions for your application server
   (see below). 

5. Create a `portal-ext.properties` file in the Liferay Home folder. This is a
   simple text properties file that you'll use to override Liferay's default
   properties (see below). This is where you'll place the mail session
   configuration credentials you collected in step 3. 

The easiest way to install Liferay is to set up your database and then follow
the instructions for your application server. This method uses the setup wizard
to create a working configuration. We'll go through the steps in order, so first
we'll look at the options for data sources. 

### Using Data Sources [](id=using-data-sources)

Liferay comes bundled with its own built-in data source. It's configured by a
number of properties which are set in a properties file. By default, the setup
wizard asks you for the necessary values and creates a configuration file that
uses the built-in data source to connect to the database. 

Liferay always recommends that you use the built-in data source. Sometimes,
however, organizations prefer to use the data source provided by their
application server of choice. In this instance, a JNDI lookup provides a handle
to the data source and the application server manages the connection pools.
Liferay supports using your application server's data source if you wish to do
that. 

To do this, you'll need to create your own configuration file and skip the setup
wizard. Since you'd be creating this file *after* the wizard anyway, this isn't
such a big deal. 

Since mail sessions are configured in a similar way to data sources, we'll look
at them next. 

### Using Mail Sessions [](id=using-mail-sessions)

Liferay's default configuration looks for a mail server on the same machine on
which Liferay's running and it tries to send mail via SMTP to this server. If
this is not your configuration, you'll need to modify Liferay's defaults. To do
this, you'll use a `portal-ext.properties` file (see below). 

In a similar fashion to databases, you have two ways to configure your mail
server:

-   Use your application server's mail session.

-   Use the built-in mail session.

To use your application server's mail session, you must create it in your
application server and it should point to your mail server. Once you've done
that, you're ready to point Liferay to it. You can do this through the
configuration file or through Liferay's control panel after it's been installed. 

Let's look next at this configuration file and, if you're choosing not to use
the setup wizard, show you how to get Liferay connected to your database and
your mail server. 

### The portal-ext.properties File [](id=the-portal-ext-properties-file)

Liferay's properties files differ from the configuration files of most other
products in that changing the default configuration file is discouraged. In
fact, the file that contains all the defaults is stored inside of a `.jar` file,
making it more difficult to customize. Why is it set up this way? Because
Liferay uses the concept of *overriding* the defaults in a separate file, rather
than going in and customizing the default configuration file. You put only the
settings you want to customize in your own configuration file and then you have
a file that contains only the settings you need. This makes it far easier to
determine whether a particular setting has been customized and it makes the
settings more portable across different installations of Liferay.

The default configuration file is called `portal.properties` and it resides
inside of the `portal-impl.jar` file. This `.jar` file is in Liferay Portal's
`WEB-INF/lib` folder. The file used to override the configuration manually is
`portal-ext.properties`. 

Complicating matters, the setup wizard creates a file called
`portal-setup-wizard.properties`. This file performs the same function as
`portal-ext.properties`, and you can use it in place of that file if you wish.
The `portal-ext.properties` file is read before the
`portal-setup-wizard.properties file`, so if you have both files in your
configuration, note that the settings in the setup wizard file override the ones
in `portal-ext.properties`. 

This file should be created in your Liferay Home folder. You'll use this file
throughout this book to change many of Liferay's settings. An exhaustive list of
the configurable properties is provided in chapter 20. 

+$$$

**Warning:** The configuration you choose in the setup wizard is saved in a 
`portal-setup-wizard.properties` file in your Liferay Home directory. In the 
setup wizard, however, if you specify a different Liferay Home directory than 
the default, the `portal-setup-wizard.properties` file that's saved there will 
not be read upon restarting your server. To have Liferay read your
`portal-setup-wizard.properties` file, create a `portal-ext.properties` file in
your new Liferay Home directory and add the following line to it, where
`${liferay.home}` is the new Liferay Home directory that you chose:

    include-and-override=${liferay.home}/portal-setup-wizard.properties

Without this workaround, Liferay will not read the
`portal-setup-wizard.properties` file with your saved configuration when you
restart your server and you'll see the setup wizard again. 

$$$

You now have all the background information you need. Next you need to make your
decision: will you use Liferay's built-in data source, or the one provided by
your application server? If you're planning to use the one provided by your
server, you can't use Liferay's installation wizard, and you'll have to follow
the instructions in the section below titled Manual Configuration. 

In either case, your next step is to install Liferay onto your application
server. Once this is done, if you're using the recommended built-in data source,
you can use the setup wizard, which we'll cover next. 
