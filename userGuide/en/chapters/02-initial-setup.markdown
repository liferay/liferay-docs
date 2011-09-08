# Initial Setup

Liferay Portal is one of the most flexible applications on the market today with regard to application server environments. You can install
Liferay Portal on everything from a shared Tomcat installation to a
multi-node cluster running a commercial application server, and on
everything in between. In fact, Liferay is used successfully in all of
these scenarios every day.

You will find that because Liferay is extremely flexible in its
deployment options, it is easy to install as well. If you already have
an application server, you can simply use the tools for deployment that
came with your application server. If you do not have an application
server, Liferay provides several application server bundles from which
to choose. These are very easy to install and with a small amount of
configuration can be made into production-ready systems.

## Editions of Liferay

Liferay ships in two different editions: Liferay Portal Community
Edition (CE) and Liferay Portal Enterprise Edition (EE). CE is the same
Liferay Portal that has been available for years: frequently updated and
bursting with the latest features, the Community Edition of Liferay
Portal is offered for free under the Lesser GNU public license, an open
source license. This license gives you the flexibility to link Liferay
with your own code in your portlet, theme, hook, layout, Ext, or web
plugins, no matter what license you use for your code. If, however, you
modify Liferay directly, those modifications need to be contributed back
to the open source product. This is really the best of both worlds: you
have the freedom to do what you want with your code, and Liferay
receives the benefits of any enhancements that are made directly.
Liferay Portal EE is a supported version of Liferay Portal for the
enterprise. Hardened for security and designed to be rock solid stable,
EE is offered with a subscription and support package, allowing
organizations to build their portals on a stable version of the product
that is offered over an extended period of time.

Because the release cycle for EE is longer than that for CE, each
enterprise release is supported for 4 years. All bug fixes in Liferay
Portal are backported to your version of Liferay for the duration of
your subscription. This gives organizations the peace of mind that comes
from knowing that their Liferay-powered web sites are stable and will
run for years to come, enabling them to build their sites on a proven,
stable platform. Additionally, Liferay's professional services team
offers training and consulting on the Enterprise Edition to ensure
long-term support and stability for our clients.

## Obtaining Liferay

The CE version of Liferay is freely downloadable from our web site at
[http://www.liferay.com](http://www.liferay.com/). Click on the
*Downloads* link at the top of the page, and you will be presented with
multiple options for getting a copy of Liferay, including our convenient
bundles or a .war package for installation on your application server of
choice.

The EE version of Liferay is provided to you as a result of your support
subscription. You will receive download links which will allow you to
obtain a copy of a Liferay bundle or a .war package for installation on
your application server of choice.

So what is a bundle anyway? A bundle is simply an open source
application server with Liferay preinstalled. If you want to install a
bundle, there is a list of bundles available. If you do not currently
have an application server, it is best to download the Tomcat bundle, as
Tomcat is one of the smallest and most straightforward bundles to
configure. If you have an application server preference, you can also
choose the server you prefer from the available Liferay Portal bundles.
All of the bundles ship with a Java Runtime Environment for Windows; if
you are using a different operating system, you will need to have a JDK
(Java Development Kit) installed prior to launching Liferay.

Please note that Liferay is not able to provide application server
bundles for proprietary application servers such as WebLogic or
WebSphere, because the licenses for these servers do not allow for
redistribution. Liferay Portal, however, runs just as well on these
application servers as it does on open source application servers. You
will need to use the .war package to install Liferay on these
application servers.

For a manual install, you will need the Liferay .war file as well as
Liferay's dependency .jars. Later in this chapter are instructions for
installing Liferay on many of the major application servers available
today.

![image](../../images/02-bundle-directory-structure.png)
*Illustration 1: Bundle directory structure*

## Installing a Bundle

Liferay bundles contain the same directory structure regardless of
application server. The top-level folder is named for the release of
Liferay. This folder is also sometimes called *Liferay Home*.

Inside this folder, you will find folders for various uses:

**Data:** This folder is used to store the embedded HSQL database which
the bundles use, as well as the configuration and data for the
Jackrabbit JSR-170 content repository and the Lucene search index.

**Deploy:** Plugins which you wish to deploy to Liferay can be copied
into this folder. It is also used by Liferay's graphical plugin
installer utility, which is available from the Control Panel.

**License:** Contains both Liferay's license and a file which describes
the licenses for many of the other open source projects that are used
internally by Liferay.

**[Application Server]:** There will also be an application server
folder which is different depending on which bundle you have downloaded.
This folder contains the application server in which Liferay has been
installed.

In most cases, installing a bundle is as easy as uncompressing the
archive and then starting the application server. For example, if you
were to install Liferay Portal on Tomcat, you would simply unzip the
bundle to a location of your choice.

Now you would start Tomcat in the same way as you would if you had
downloaded it manually. Tomcat is launched by way of a script which is
found in its *bin* folder. If you drop to a command prompt and go to
this folder, you can launch Tomcat via the following command on Windows:

startup

or the following command on Linux / Mac / Unix:

./startup.sh

The Liferay / Tomcat bundle will then launch. If you are on Windows, you
will see another command prompt window appear with Tomcat's console in
it. If you are on Linux, you can see the Tomcat console by issuing the
following command:

tail -f ../logs/catalina.out

Once Tomcat has completed its start up, it should automatically launch a
web browser so you can see the home page. If it does not, launch your
web browser and then go to the following address:
[http://localhost:8080](http://localhost:8080/). The default Liferay
home page will then appear in your web browser. It will be using an
embedded database for its configuration, but it is fully functional. You
can now begin exploring the various features of Liferay.

Liferay ships by default with a sample web site included, called 7 Cogs.
You can access this site and log in as the various users to get familiar
with Liferay and what it can do.

Installing a different bundle is done in exactly the same way: unzip the
bundle into the folder of your choice, launch the application server,
and then view the portal in your web browser.

![image](../../images/02-default-page-in-the-liferay-bundles.png)
*Illustration 2: Default Page in the Liferay Bundles*

As you can see, bundles are the easiest way to get started with Liferay.
They come pre-configured with a running Liferay that can be used
immediately to explore all of the things that Liferay can do. And with
minimal extra configuration (which we will see later), bundles can be
converted into full production-ready systems.

## Installing Liferay for an Enterprise

When it comes time to install Liferay Portal on your server, you'll find
it is easiest to do this by starting with a bundle and then
reconfiguring that bundle so that it is enterprise-ready. Because this
is by far the quickest and easiest method to get a production Liferay
system running, we will look at this first. Often, however, enterprises
will have an established Java EE infrastructure upon which they would
like to install Liferay. In this situation, a bundle will not suffice.
Most of the rest of this chapter, therefore, will focus on installing
Liferay onto an already-established application server.

### Sample Data

Liferay CE ships with some sample data to help you see some of the
things Liferay Portal can do. While the sample 7 Cogs data is a good
example of how Liferay might be used, when you are ready to build your
own site, you won't want that data cluttering up your database. So
before you connect Liferay to your production database, you will want to
make sure you have removed the sample 7 Cogs data from your Liferay
installation. This is as simple as undeploying the application that
installs the 7 Cogs data.

There is only one application included in the bundle that you will need
to remove. It's a hook that copies the 7 Cogs data into the database
when Liferay is started. Because we want to revert Liferay's behavior
back to its defaults for a clean install, you will want to remove the
*sevencogs-hook* application. The other two applications related to 7
Cogs are both themes, which you can leave installed if you wish.

If you forget to undeploy the *sevencogs-hook* application before you
connect Liferay to your real database, the sample data will be created
in your database and may cause issues, especially if you already have
data in your database. So you want to make sure that you get
*sevencogs-hook* undeployed before setting up your server. Use your
application server's method for uninstalling applications in order to
remove them.

### Liferay Home

Liferay Portal uses a special folder defined as *Liferay Home*. This
folder is one folder higher than the location of the application server
itself. This is why the bundles place the application server one folder
in from the bundle's root folder.

If Liferay is unable to create the resources it needs in this folder, or
if it finds itself running on certain application servers, it will fall
back to defining the home folder in the home folder of the user ID that
is running Liferay.

As described above in the *Bundles* section, the home folder is very
important to the operation of Liferay. The aforementioned folders
(*data*, *deploy*, and *license*) will be created there, and you can also
put a special configuration file called
`portal-ext.properties`{.western} there.

This file is fully documented in Chapter 6: *Advanced Liferay
Configuration*, but we will use it in this chapter for some basic
configuration, including setting up Liferay to talk to our database.

### Database Setup

#### Default Method: Automatic

If you create your database and grant a user ID full access to it,
Liferay can use that user ID to create its indexes and tables
automatically. This is the recommended way to set up Liferay, as it
allows you to take advantage of Liferay's ability to automatically
maintain its database during upgrades or through various plugin installs
which may create tables of their own. It is by far the best way to set
up your Liferay installation.

If you will be setting up Liferay's database with the recommended
permissions, you can skip the next section.

#### Manual Method

![image](../../images/02-tip.png)![image](../../images/02-tip.png)
**Note:** This is not the recommended set up for Liferay installations,
but is documented here so that enterprises with more restrictive
standards can install Liferay with more strict — but suboptimal —
database settings. If it is at all possible, Liferay recommends that you
use the automatic method as documented above instead of the procedure
outlined below.

Even though Liferay can create its database automatically, some
enterprises prefer *not* to allow the user ID configured in an
application server to have the permissions over the database necessary
for Liferay and its plugins to maintain their tables. For these
organizations, Select, Insert, Update, and Delete are generally all the
permissions that are granted, and so we will go over how to set up the
database manually. If your organization* is* willing to grant the
Liferay user ID permissions to create and drop tables in the
database—and this is the recommended configuration—you can skip this
section.

One other caveat is this: Liferay has an automatic database upgrade
function which runs when the version of Liferay is upgraded to a new
release. If the user ID that accesses the database does not have enough
rights to create / modify / drop tables in the database, you will need
to grant those rights to the ID before you start your upgraded Liferay
for the first time. Once the upgrade is complete, you can remove those
rights until the next upgrade. Additionally, many plugins provided by
Liferay require that new tables be added to Liferay's database. These
plugins cannot be installed if Liferay does not have permission to
create these tables automatically. If you wish to install these plugins,
you will need to grant rights to create tables in the database before
you attempt to install them.

Liferay provides an SQL script archive download on the web site. For the
CE version, it is in the *Additional Files* section of the Downloads
page. For the EE version, you will be provided a link to this archive.
Download this file and unzip it. You will find that it contains a folder
structure that is broken down by the type of script (full, minimal, or
upgrade), and then further by database vendor type.

It is best to use the `create-minimal`{.western} script if you are
installing a fresh version of Liferay on a development, QA, or
production server. This script creates the necessary Liferay tables in
the database, with a minimum configuration. This is most appropriate for
a new installation of Liferay.

The `create`{.western} script, by contrast, configures a Liferay
database with a portion of the content from
[http://www.liferay.com](http://www.liferay.com/) embedded in it. This
can be useful from a development perspective, as it contains working
examples of the use of many of Liferay's features, including the Content
Management System.

Inside the `create`{.western} or `create-minimal`{.western} folders are
scripts for every database that Liferay supports. A DBA can use the
script provided to create the Liferay database, complete with the
indexes necessary for optimal performance. Once this is done, be sure
that the ID that the portal will use to connect to the database has at
least Select, Insert, Update, and Delete permissions. Preferably,
however, the ID should also have rights to create, modify, and drop
tables and indexes, as this makes upgrading easier. This, however, is
not necessary for the daily operation of Liferay.

Once your DBA has created the database and provided the credentials for
accessing it, you are ready to begin 1) making a bundle enterprise-ready
or 2) manually installing Liferay on your application server.

### Turning a Bundle into an Enterprise Portal

Liferay Portal is distributed with the following bundle options for
servlet containers and full Java EE application servers:

-   Geronimo+Tomcat

-   Glassfish 3

-   JBoss

-   Jetty

-   JOnAS

-   Resin

-   Tomcat 6.0

Choose your preferred bundle and download it from the downloads page on
Liferay's web site or via the EE links that were provided to you. A
prerequisite for running any of the bundles is that you have the proper
version of the Java Development Kit (1.5 or higher) installed on the
machine to which you are installing Liferay. Make sure that you have
also created the `JAVA_HOME`{.western} environment variable and have
pointed it to your Java installation.

Unzip the bundle to the location from which you are going to run it. For
example, you might use `D:\apps`{.western} in Windows or
`/opt`{.western} in Linux or UNIX variants. The default bundle
installation of Liferay Portal uses an embedded database. While this is
a good method to have it up and running fast for evaluation or
development, it has several drawbacks:

-   Only one user can access it at a time. This is because the data is
    stored on a file on disk and HSQL locks it when doing changes.

-   The data is stored inside the bundle and might be lost on
    redeployment.

-   This configuration does not scale well and will have performance
    problems when multiple users are accessing the system.

Obviously, you do not want to be running Liferay against the embedded
database. Fortunately, Liferay has great support for a good number of
production-ready databases, and it is easy to configure Liferay to use
them. The exact instructions will depend on the application server and
database, but can be summarized as:

1.  Create the database in your DBMS of choice (see the above section
    labeled *Database Setup* for further information).

2.  [Optional] Create a Data Source called `jdbc/LiferayPool`{.western}
    in your application server which points to your database and has the
    proper credentials to access it.

3.  [Optional] Create a mail session called `mail/MailSession`{.western}
    in your application server which points to your mail server, so
    Liferay can send mail.

4.  Create a `portal-ext.properties`{.western} file in the Liferay Home
    folder which either points directly to the database and mail session
    or points to the application server's Data Source and mail session.

5.  Start Liferay. Liferay will create the tables automatically and
    start. Otherwise, you will have had to prepare the database first by
    running the appropriate `create`{.western} script.

Refer to the manual installation instructions below for further details
on configuring the various application servers. There is no difference
between the Liferay bundles and the regular distribution archives of the
application servers as they are available from their own sites, with the
exception that Liferay is pre-installed in them, and the JVM settings
may have been optimized for use with Liferay.

#### The portal-ext.properties File

To point your Liferay bundle to your database, create a file called
`portal-ext.properties`{.western} in your Liferay Home folder. This file
overrides default properties that come with Liferay. You are going to
override the default configuration which points Liferay to the embedded
HSQL database.

There are two ways to set up the connection:

-   Use your application server's connection pool.

-   Use the built-in connection pool.

If you want to use your application server's connection pool, you will
have to create one in your application server that points to your
database. It should be called `jdbc/LiferayPool`{.western}. To cause
Liferay to use this connection pool, add the following directive to your
`portal-ext.properties`{.western} file:

jdbc.default.jndi.name=jdbc/LiferayPool

To use the built-in connection pool -- based on *C3P0* -- add the template
which is provided in Chapter 6 for your particular database. The
template for MySQL is provided as an example below.

#

# MySQL

#

jdbc.default.driverClassName=com.mysql.jdbc.Driver

jdbc.default.url=jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false

jdbc.default.username=

jdbc.default.password=

You would provide the user name and password to the database as values
for the *username* and *password* directives.

For mail, there is a similar procedure. Again, you have two ways to
configure your server:

-   Use your application server's mail session.

-   Use the built-in mail session.

To use your application server's mail session, you will have to create
one in your application server that points to your mail server. Once you
have done that, add the following directive to your
`portal-ext.properties`{.western} file:

mail.session.jndi.name=mail/MailSession

To use the built-in mail session, add the following directives to your
`portal-ext.properties`{.western} file, substituting your mail server
information:

mail.session.mail.pop3.host=localhost

mail.session.mail.pop3.password=

mail.session.mail.pop3.port=110

mail.session.mail.pop3.user=

mail.session.mail.smtp.auth=false

mail.session.mail.smtp.host=localhost

mail.session.mail.smtp.password=

mail.session.mail.smtp.port=25

mail.session.mail.smtp.user=

mail.session.mail.store.protocol=pop3

mail.session.mail.transport.protocol=smtp

Save the file. You can now start your application server.

### Installing Liferay on an Existing Application Server

This section contains detailed instructions for installing Liferay
Portal using its WAR distribution. This allows system administrators to
deploy Liferay in existing application server installations. It is
recommended that you have a good understanding of how to deploy Java EE
applications in your application server of choice.

#### Installing Liferay in 10 Easy Steps

There are 10 generic steps to installing Liferay on an existing
application server:

1.  Obtain the Liferay .war file and the dependencies archive.

2.  Make sure you do not have an application listening at the root
    (`/`{.western}) of your server. If you do, move it to a different
    context or undeploy it.

3.  Decide whether you want to use your application server's data
    sources or if you want to use the one included with Liferay. If you
    want to use your application server's data source, create a data
    source for Liferay called `jdbc/LiferayPool`{.western}.

4.  Decide whether you want to use your application server's mail
    session or if you want to use the one included with Liferay. If you
    want to use your application server's mail session, create one
    called `mail/MailSession`{.western}.

5.  Shut your application server down.

6.  Extract the dependencies to a location on your server's global class
    path. This allows both Liferay and plugins to access these
    dependencies.

7.  Create a `portal-ext.properties`{.western} file and place it in the
    Liferay Home folder. See the notes on specific application servers
    below for the location of this folder for your application server.

8.  Add either the JNDI name of your data source or the JDBC parameters
    above to connect Liferay to your database.

9.  Add either the JNDI name of your mail session or the mail parameters
    above to connect Liferay to your mail server.

10. Start your application server, deploy the Liferay .war file, and
    start it.

The instructions below are specific for each application server that
Liferay supports. Liferay supports a wide combination of application
servers and databases. Because of this, for brevity this section assumes
MySQL as the database, that the database has already been created, and
that you are using your application server's mail session and data
source. To use other databases, substitute the JDBC driver and URL
construct for your database in place of the MySQL ones shown here.

We also assume your application server is already installed and running
successfully. If you still need to install your application server,
please follow your vendor's instructions first.

The following instructions assume an installation on a local machine.
When installing to a remote server, substitute `localhost`{.western}
with the host name or IP of the server.

![image](../../images/02-tip.png)![image](../../images/02-tip.png)
**Tip:** Note that Liferay 5.x and above *requires* JDK 1.5 or greater.
Do not attempt to install Liferay 6.x on an application server that runs
under Java 1.4 or lower; it will not work. If you are running an
application server that ships with a JDK and that JDK is 1.4 or lower,
you will need to upgrade your application server in order to run current
versions of Liferay Portal. Liferay 4.x, however, will run fine on these
application servers.

Remember, for all of these application servers, create your
`portal-ext.properties`{.western} file in the Liferay Home folder and
make sure it points to your database connection pool and mail session.

#### GlassFish 3.x

**Liferay Home** is in the Glassfish root folder. We will assume for
these instructions that you are using the default domain stored in
`[GlassFish Root]/glassfish/domains/domain1`{.western}.

1.  Before starting GlassFish, you will need to modify some settings in
    the domain you will be using to increase the default amount of
    memory available. In your domain folder is a `config`{.western}
    folder. Open the file called `domain.xml`{.western} in this folder.

2.  At approximately line 166 of this file, you will find the following
    JVM option being set:

    <jvm-options\>-Xmx512m</jvm-options\>

    Change this to:

    <jvm-options\>-Xmx1024m</jvm-options\>

3.  Add another line after this line with the following JVM option:

    <jvm-options\>-XX:MaxPermSize=256m</jvm-options\>

    Save and close the file.

4.  In your domain folder is a folder called `docroot`{.western}. This
    folder contains a default page for the domain in a file called
    `index.html`{.western}. Delete or move this file to another
    location.

5.  Extract the Liferay dependencies archive into your domain's
    `lib`{.western} folder. Extract your database's JDBC driver here as
    well.

6.  On Glassfish 3.0.1, you will need to extract commons-codec.jar from
    the Liferay WAR file, rename it to commons-codec-repackaged.jar, and
    copy it to [Glassfish Root]/glassfish/modules/, overwriting
    Glassfish's version of the file

##### Database Configuration

If you want GlassFish to manage the data source, use the following
instructions. If you want to use the built-in Liferay data source, you
can skip this section.

1.  

![image](../../images/02-glassfish-jdbc-connection-pool.png)
![image](../../images/02-glassfish-jdbc-connection-pool.png)
    *Illustration 3: Glassfish JDBC Connection Pool*

    Go to the GlassFish console URL:
    [http://localhost:4848](http://localhost:4848/).

2.  Under *Other Tasks*, select *Create New JDBC Connection Pool*.

3.  In the first screen, give it a name of `LiferayPool`{.western}, a
    Resource Type of `javax.sql.ConnectionPoolDataSource`{.western}, and
    select *MySQL* as the Database Vendor. Click *Next*.

4.  On the next page, scroll down to the *Additional Properties*
    section. Find the property called `URL`{.western}, and set its value
    to:

    jdbc:mysql://localhost/lportal?useUnicode=true&amp;characterEncoding=UTF-8&amp;emulateLocators=true

5.  If your database is not on the same server as GlassFish, substitute
    your database server's host name for `localhost`{.western} above.

6.  Click *Add Property*, and add a property called `user`{.western}
    with a value of the user name to connect to the database.

7.  Click *Add Property* again, and add a property called
    `password`{.western} with a value of the password to connect to the
    database.

8.  Click *Finish*.

9.  You will now see a list of Connection Pools. To test your
    connection, click the `LiferayPool`{.western} and click the *Ping*
    button. If you get a **Ping Succeeded** message, everything has been
    set up correctly.

10. Click *JDBC Resources*. You will see a list of JDBC Resources by
    JNDI Name.

11. Click *New*.

12. Make the JNDI Name `jdbc/LiferayPool`{.western} and select the
    LiferayPool you created earlier.

13. Click *OK*.

##### Mail Configuration

If you want GlassFish to manage your mail session, follow the
instructions below. If you want Liferay to manage your mail session, you
can skip this section.

1.  Under *Resources*, click *JavaMail Sessions*.

2.  Click *New*.

3.  Give the JavaMail Session a JNDI name of
    `mail/MailSession`{.western}, and fill out the rest of the form with
    the appropriate information for your mail server.

Click *OK*.

##### Deploy Liferay

1.  Create a file called `portal-ext.properties`{.western}. Add the
    following directives to the file:

jdbc.default.driverClassName=com.mysql.jdbc.Driver

jdbc.default.url=jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false

jdbc.default.username=root

jdbc.default.password=root


2.  Go to the GlassFish console URL: `http://localhost:4848`{.western}

3.  Click *Applications* in the tree on the left.

4.  Click the *Deploy* button.

5.  Under *Packaged File to Be Uploaded to the Server* click *Choose
    File*, and browse to the location of the Liferay .war file.

6.  Leave the rest of the defaults and click *OK*.

![image](../../images/02-deploying-liferay-in-glassfish-3.png)
![image](../../images/02-deploying-liferay-in-glassfish-3.png)
    *Illustration 4: Deploying Liferay in GlassFish 3*

Liferay will be deployed and started automatically.

#### Jetty 6

**Liferay Home** is one folder above Jetty's install location.

1.  Download and install Jetty 6.

2.  Download the Liferay Portal .war file.

3.  Download Liferay Portal Dependencies.

4.  Copy the dependencies to* *`$JETTY_HOME/lib/ext`{.western}.

5.  Edit `$JETTY_HOME/extra/etc/start-plus.config`{.western}.

    $(jetty.home)/lib/ext/

    $(jetty.home)/lib/ext/\*

6.  Create a data source bound to `jdbc/LiferayPool`{.western} by
    editing `$JETTY_HOME/etc/jetty.xml`{.western}.

    <Call name="addService"\>

    <Arg\>

    <New class="org.mortbay.jetty.plus.JotmService"\>

    <Set name="Name"\>TransactionMgr</Set\>

    <Call name="addDataSource"\>

    <Arg\>jdbc/LiferayPool</Arg\>

    <Arg\>

    <New class="org.enhydra.jdbc.standard.StandardXADataSource"\>

    <Set name="DriverName"\>com.mysql.jdbc.Driver</Set\>

    <Set
    name="Url"\>jdbc:mysql://localhost/lportal?useUnicode=true&amp;characterEncoding=UTF-8</Set\>

    <Set name="User"\></Set\>

    <Set name="Password"\></Set\>

    </New\>

    </Arg\>

    <Arg\>

    <New class="org.enhydra.jdbc.pool.StandardXAPoolDataSource"\>

    <Arg type="Integer"\>4</Arg\>

    <Set name="MinSize"\>4</Set\>

    <Set name="MaxSize"\>15</Set\>

    </New\>

    </Arg\>

    </Call\>

    </New\>

    </Arg\>

    </Call\>

7.  Download `mysql-connector-java-{$version}-bin.jar`{.western} and
    copy to `$JETTY_HOME/lib/ext`{.western}. This is the JDBC driver for
    MySQL. If you are using a different database, copy the appropriate
    driver.

8.  Create a mail session bound to `mail/MailSession`{.western} by
    editing `$JETTY_HOME/etc/jetty.xml`{.western}:

    <Call name="addService"\>

    <Arg\>

    <New class="org.mortbay.jetty.plus.MailService"\>

    <Set name="Name"\>MailService</Set\>

    <Set name="JNDI"\>mail/MailSession</Set\>

    <Put name="mail.smtp.host"\>localhost</Put\>

    </New\>

    </Arg\>

    </Call\>

9.  Create `$JETTY_HOME/etc/jaas.config`{.western}.

    PortalRealm {

    com.liferay.portal.kernel.security.jaas.PortalLoginModule required;

    };

10. Create directory `$JETTY_HOME/webapps/root`{.western} and *unpack*
    the Liferay .war file into it.

11. Go to `$JETTY_HOME/webapps/root/WEB-INF/lib`{.western} and delete
    `xercesImpl.jar`{.western} and `xml-apis.jar`{.western}.

15. Copy
    `$JETTY_HOME/webapps/root/WEB-INF/lib/commons-logging.jar`{.western}
    to `$JETTY_HOME/ext`{.western} (overwriting the existing one).

16. Create batch file: `run.bat`{.western}.

@echo off

if "" == "%JAVA\_HOME%" goto errorJavaHome


%JAVA\_HOME%/bin/java -Xmx512m -Dfile.encoding=UTF8 -Duser.timezone=GMT
-Djava.security.auth.login.config=../etc/jaas.config
-DSTART=../extra/etc/start-plus.config -jar ../start.jar
../etc/jetty.xml


goto end


:errorJavaHome

echo JAVA\_HOME not defined.


goto end


:end

**Note:** If you get a `java.lang.OutOfMemoryError`{.western} exception
while starting up Jetty, give your JVM more memory by setting
`-Xmx1024m`{.western}.

Start Liferay by running `run.bat`{.western}. Open your browser to
`http://localhost:8080`{.western}. You should see the default Liferay
home page.

#### JBoss 5.x

**Liferay Home** is one folder above JBoss's install location.

1.  Download and install JBoss AS 5.0.1 GA into your preferred
    directory. This directory will be referred to below as
    `$JBOSS_HOME`{.western}.

2.  Download the latest version of the Liferay Portal .war file.

3.  Remove `hibernate-validator.jar`{.western} from
    `$JBOSS_HOME/common/lib.`{.western}

4.  Go to `$JBOSS_HOME/server/default/lib/`{.western}. Download
    `mysql-connector-java-{$version}-bin.jar`{.western} and copy to this
    directory. (This is the JDBC connector for MySQL. Use the
    appropriate driver for your database.)

5.  Download Liferay's Portal Dependencies. Unzip to
    `$JBOSS_HOME/server/default/lib`{.western}.

6.  Configure JAAS. Edit
    `$JBOSS_HOME/server/default/conf/login-config.xml`{.western} and
    comment out the entire XML for policy *other *in lines 115-131.

<!--<application-policy name = "other"\>--\>

...

<!--<authentication\>

<login-module code = "org.jboss.security.

auth.spi.UsersRolesLoginModule"

flag = "required" /\>

</authentication\>

</application-policy\>--\>

##### Database Configuration

If you want JBoss to manage the data source, use the following
instructions. If you want to use the built-in Liferay data source, you
can skip this section.

Create `$JBOSS_HOME/server/default/deploy/liferay-ds.xml`{.western} with
the following content:

<datasources\>

<local-tx-datasource\>

<jndi-name\>jdbc/LiferayPool</jndi-name\>

<connection-url\>

jdbc:mysql://localhost/lportal?useUnicode=true&amp;characterEncoding=UTF-8

</connection-url\>

<driver-class\>com.mysql.jdbc.Driver</driver-class\>

<user-name\></user-name\>

<password\></password\>

<min-pool-size\>0</min-pool-size\>

</local-tx-datasource\>

</datasources\>

##### Mail Configuration

If you want JBoss to manage the mail configuration, use the following
instructions. If you want to use the built-in Liferay mail session, you
can skip this section.

Set mail properties by replacing the contents of
`$JBOSS_HOME/server/default/deploy/mail-service.xml`{.western} with:

<?xml version="1.0"?\>

<server\>

<mbean code="org.jboss.mail.MailService"
name="jboss:service=MailSession"\>

<attribute name="JNDIName"\>mail/MailSession</attribute\>

<attribute name="User"\>nobody</attribute\>

<attribute name="Password"\>password</attribute\>

<attribute name="Configuration"\>

<configuration\>

<property name="mail.store.protocol" value="imap" /\>

<property name="mail.transport.protocol" value="smtp" /\>

<property name="mail.imap.host" value="localhost" /\>

<property name="mail.pop3.host" value="localhost" /\>

<property name="mail.smtp.host" value="localhost" /\>

</configuration\>

</attribute\>

</mbean\>

</server\>

##### Deploy Liferay

1.  Delete all the files and folders in
    `$JBOSS_HOME/server/default/deploy/ROOT.war`{.western}

2.  Unzip the Liferay .war file to the ROOT.war directory.

3.  Remove `jaxrpc.jar`{.western}, `stax.jar`{.western},
    `xercesImpl.jar`{.western}, `xml-apis.jar`{.western} from:

    `$JBOSS_HOME/server/default/deploy/ROOT.war/WEB-INF/lib`{.western}

4.  Navigate to the Liferay Home folder, which is one folder above
    JBoss's install location.

5.  Create a file called `portal-ext.properties`{.western}. Add the
    following directives to the file:

jdbc.default.driverClassName=com.mysql.jdbc.Driver

jdbc.default.url=jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false

jdbc.default.username=root

jdbc.default.password=root

Start JBoss. Open your browser to `http://localhost:8080`{.western}. You
should see the default Liferay home page.

#### Resin 3.1.x

**Liferay Home** is one folder above Resin's install location.

1.  Download and install Resin into your preferred directory. From now
    on, the directory where you installed Resin will be referred to as
    `$RESIN_HOME`{.western}.

2.  Edit `$RESIN_HOME/conf/resin.conf`{.western}. Replace lines 8-13
    with:

    <class-loader\>

    <tree-loader path="${resin.home}/lib"/\>

    <tree-loader path="${server.root}/lib"/\>

    <compiling-loader path="${server.rootDir}/common/classes"/\>

    <library-loader path="${server.rootDir}/common/lib"/\>

    </class-loader\>

    And add the following:

    <database\>

    <jndi-name\>jdbc/LiferayPool</jndi-name\>

    <driver type="com.mysql.jdbc.Driver"\>

    <url\>jdbc:mysql://localhost/lportal?useUnicode=true&amp;characterEncoding=UTF-8</url\>

    <user\></user\>

    <password\></password\>

    </driver\>

    <prepared-statement-cache-size\>8</prepared-statement-cache-size\>

    <max-connections\>20</max-connections\>

    <max-idle-time\>30s</max-idle-time\>

    </database\>

    <resource jndi-name="mail/MailSession" type="javax.mail.Session"\>

    <init\>

    <mail.store.protocol\>imap</mail.store.protocol\>

    <mail.transport.protocol\>smtp</mail.transport.protocol\>

    <mail.imap.host\>localhost</mail.imap.host\>

    <mail.pop3.host\>localhost</mail.pop3.host\>

    <mail.smtp.host\>localhost</mail.smtp.host\>

    </init\>

    </resource\>

    <system-property

    javax.xml.parsers.DocumentBuilderFactory="org.apache.xerces.jaxp.DocumentBuilderFactoryImpl"

    /\>

    <system-property
    javax.xml.parsers.SAXParserFactory="org.apache.xerces.jaxp.SAXParserFactoryImpl"
    /\>

    <system-property
    javax.xml.transform.TransformerFactory="org.apache.xalan.processor.TransformerFactoryImpl"
    /\>

    <system-property
    org.xml.sax.driver="org.apache.xerces.parsers.SAXParser" /\>

3.  Go to `$RESIN_HOME`{.western} and create a new directory called
    `common/lib`{.western}. Download
    `mysql-connector-java-{$version}-bin.jar`{.western} and copy to this
    directory. This is the JDBC connector for MySQL. If you are using
    another database, substitute this with the appropriate driver.

4.  Download the Liferay Portal Dependencies and unzip into
    `$RESIN_HOME/common/lib`{.western}.

5.  Delete contents of `$RESIN_HOME/webapps/ROOT`{.western}.

6.  Unzip `liferay-portal-x.x.x.war`{.western} to
    `$RESIN_HOME/webapps/ROOT`{.western}.

7.  If you are using Resin 3.1.9 or higher, remove
    `$RESIN_HOME/lib/portlet-01.jar`{.western}. This contains the old
    Portlet 1.0 classes. The Portlet 2.0 classes are backwards
    compatible, so this should not affect anything.

8.  Next, you will need several .jar files which are included as part of
    the Liferay source distribution. Many application servers ship with
    these already on the class path, but Resin does not. The best way to
    get the appropriate versions of these files is to download the
    Liferay source code and get them from there. Once you have
    downloaded the Liferay source, unzip it to a temporary folder.

    1.  Go to `$LIFERAY_SOURCE/lib/development/`{.western} and copy
        `activation.jar`{.western} and `mail.jar`{.western} to
        `$RESIN_HOME/common/lib`{.western}. Copy
        `saxpath.jar`{.western} and `xalan.jar`{.western} to
        `$RESIN_HOME/lib`{.western}.

    2.  Go to `$LIFERAY_SOURCE/lib/portal`{.western} and copy
        `xercesImpl.jar`{.western} and `xml-apis.jar`{.western} to
        `$RESIN_HOME/lib`{.western}.

9.  To start the server, open a command prompt, navigate to the
    `$RESIN_HOME`{.western} and type:

    java -jar lib/resin.jar start

10. Open your browser to `http://localhost:8080`{.western}. You should
    see the default Liferay home page.

#### Resin 3.2.x

**Liferay Home** is one folder up from Resin's install location.

1.  Download and install Resin 3.2.1 into your preferred directory. From
    now on, the directory where you installed Resin will be referred to
    as `$RESIN_HOME`{.western}.

2.  Edit `$RESIN_HOME/conf/resin.conf`{.western}. Replace lines line
    9-13 with:

    <tree-loader path="${resin.home}/ext-lib"/\>

    <tree-loader path="${resin.root}/ext-lib"/\>

    <tree-loader path="${resin.home}/lib"/\>

    <tree-loader path="${resin.root}/lib"/\>

    <compiling-loader path="${server.rootDir}/common/classes"/\>

    <library-loader path="${server.rootDir}/common/lib"/\>

3.  Search `<jvm-arg>`{.western} tag in `resin.conf`{.western} and
    replace what is there with the following:

    <jvm-arg\>-Xmx256m</jvm-arg\>

    <jvm-arg\>-Xss1m</jvm-arg\>

    <jvm-arg\>-Dcom.sun.management.jmxremote</jvm-arg\>

    <jvm-arg\>-Xmx1024m</jvm-arg\>

    <jvm-arg\>-XX:MaxPermSize=256m</jvm-arg\>

    <jvm-arg\>-Dfile.encoding=UTF-8</jvm-arg\>

    <jvm-arg\>-Duser.timezone=GMT</jvm-arg\>

4.  Go to `$RESIN_HOME`{.western}* *and create a new directory called
    `common/lib`{.western}. Download
    `mysqlconnector-java-{$version}-bin.jar`{.western}* *and copy to
    this directory. This is the JDBC connector for MySQL. If you are
    using another database, substitute this with the appropriate driver.

5.  Download the Liferay Portal Dependencies and unzip into
    `$RESIN_HOME/common/lib`{.western}.

6.  Delete the contents of `$RESIN_HOME/webapps/ROOT`{.western}.

7.  Unzip `liferay-portal-x.x.x.war`{.western} to
    `$RESIN_HOME/webapps/ROOT`{.western}.

8.  Next, you will need several .jar files which are included as part of
    the Liferay source distribution. Many application servers ship with
    these already on the class path, but Resin does not. The best way to
    get the appropriate versions of these files is to download the
    Liferay source code and get them from there. Once you have
    downloaded the Liferay source, unzip it to a temporary folder.

-   Go to `$LIFERAY_SOURCE/lib/development`{.western}.  Copy
    `saxpath.jar`{.western} to `$RESIN_HOME/common/lib`{.western}

9.  To start the server, open a command prompt, navigate to the
    `$RESIN_HOME`{.western} and type:

    `java -jar     lib/resin.jar start`{.western}

Open your browser to `http://localhost:8080`{.western}. You should see
the default Liferay home page.

#### Tomcat 6.0.x

**Liferay Home** is one folder above Tomcat's install location.

1.  Download and install Tomcat 6.0.X into your preferred directory.
    From now on, the directory where you installed Tomcat will be
    referred to as `$TOMCAT_HOME`{.western}.

    **Note:** For JDK 5 users: move
    `$TOMCAT_HOME/webapps/ROOT/WEB-INF/lib/xercesImpl.jar`{.western} to
    `$TOMCAT_HOME/common/endorsed`{.western}. JDK 1.4 is no longer
    supported in Liferay 5.x and above.

2.  Create and edit
    `$TOMCAT_HOME/conf/Catalina/localhost/ROOT.xml`{.western} to set up
    the portal web application.

    <Context path=""\>

    </Context\>

3.  Download `liferay-portal-x.x.x.war`{.western}.

4.  Download Liferay's Portal Dependencies. Create a
    `$TOMCAT_HOME/lib/ext`{.western} directory and unzip the
    dependencies ZIP in there. If the files do not extract to this
    directory, make sure they are in the correct directory by moving
    them there.

5.  Edit `$TOMCAT_HOME/conf/catalina.properties`{.western}:

    common.loader=

    ${catalina.home}/classes,\\

    ...\\

    ${catalina.home}/lib/ext/\*.jar

6.  Make sure your database server is installed and is working. If it's
    installed in a different machine, make sure that it's accessible
    from the one where Liferay is being installed.

7.  Configure data sources for your database. Make sure the JDBC driver
    for your database is accessible by Tomcat. Obtain the JDBC driver
    for your version of the database server. In the case of MySQL use
    `mysql-connector-java-{$version}-bin.jar`{.western}. Next, copy the
    JAR file to `$TOMCAT_HOME/common/lib/ext`{.western}.

8.  Edit `$TOMCAT_HOME/conf/Catalina/localhost/ROOT.xml`{.western}.

    <Context...\>

    <Resource

    name="jdbc/LiferayPool"

    auth="Container"

    type="javax.sql.DataSource"

    driverClassName="com.mysql.jdbc.Driver"

    url="jdbc:mysql://localhost/lportal?useUnicode=true&amp;characterEncoding=UTF-8"

    username=""

    password=""

    maxActive="100"

    maxIdle="30"

    maxWait="10000"

    /\>

    </Context\>

9.  Be sure to enter the user name and password to your database in the
    appropriate fields above.

10. Create a mail session bound to `mail/MailSession`{.western}. Edit
    `$TOMCAT_HOME/conf/Catalina/localhost/ROOT.xml`{.western} and
    configure a mail session.

    <Context...\>

    <Resource

    name="mail/MailSession"

    auth="Container"

    type="javax.mail.Session"

    mail.transport.protocol="smtp"

    mail.smtp.host="localhost"

    mail.store.protocol="imap"

    mail.imap.host="localhost"

    /\>

    </Context\>

11. Configure JAAS.

    Edit `$TOMCAT_HOME/conf/Catalina/localhost/ROOT.xml`{.western} and
    configure a security realm.

    <Context...\>

    <Realm

    className="org.apache.catalina.realm.JAASRealm"

    appName="PortalRealm"

    userClassNames="com.liferay.portal.security.jaas.PortalPrincipal"

    roleClassNames="com.liferay.portal.security.jaas.PortalRole"

    debug="99"

    useContextClassLoader="false"

    /\>

    </Context\>

14. Create `$TOMCAT_HOME/conf/jaas.config`{.western}.

    PortalRealm {

    com.liferay.portal.kernel.security.jaas.PortalLoginModule required;

    };

15. Edit `$TOMCAT_HOME/bin/catalina.bat`{.western} (on Windows) or
    `$TOMCAT_HOME/bin/catalina.sh`{.western} (on Linux / Mac / Unix) so
    that Tomcat can reference the login module.

    rem ----- Execute...

    set JAVA\_OPTS=-Xms128m -Xmx512m -Dfile.encoding=UTF8
    -Duser.timezone=GMT
    -Djava.security.auth.login.config=%CATALINA\_HOME%/conf/jaas.config

16. Delete contents `$TOMCAT_HOME/webapps/ROOT`{.western} directory.
    This undeploys the default Tomcat home page.

17. Unpack `liferay-portal-x.x.x.war`{.western} to
    `$TOMCAT_HOME/webapps/ROOT`{.western}.

18. For supporting UTF-8 URI Encoding, edit
    `$TOMCAT_HOME/conf/server.xml`{.western}:

    <!-- Define a non-SSL HTTP/1.1 Connector on port 8080 --\>

    <Connector port="8080" maxHttpHeaderSize="8192"

    maxThreads="150" minSpareThreads="25" maxSpareThreads="75"

    enableLookups="false" redirectPort="8443" acceptCount="100"

    connectionTimeout="20000" disableUploadTimeout="true"

    URIEncoding="UTF-8"

    /\>

19. Run Tomcat, point browser to `http://localhost:8080`{.western}. You
    should see the default Liferay home page.

#### WebLogic 10

**Liferay Home** is one folder above the home folder of the domain in
which Liferay is installed.

These instructions assume that you have already configured a domain and
server, and that you have access to the WebLogic console.

##### Dependency Jars

1.  Navigate to the folder which corresponds to the domain to which you
    will be installing Liferay. Inside this folder is a `lib`{.western}
    folder. Unzip the Liferay dependencies archive to this folder.

2.  Copy the JDBC driver for your database to this folder as well.

3.  You will also need the `xercesImpl.jar`{.western} or you will get
    SAX parsing errors after you deploy Liferay. You may download this
    from [http://xerces.apache.org](http://xerces.apache.org/). Copy the
    `xercesImpl.jar`{.western} file into this directory.

4.  

![image](../../images/02-weblogic-data-sources.png)
![image](../../images/02-weblogic-data-sources.png)
    *Illustration 5: WebLogic: Data Sources*

    Create a folder called *endorsed* in
    `$WEBLOGIC-HOME/jrockit90_150_04/jre/lib`{.western}, then copy
    `commons-lang.jar, rhino.jar`{.western},
    `serializer.jar`{.western}, and `xalan.jar`{.western} to the
    folder that you just created.

##### Database Configuration

If you want WebLogic to manage your data source, use the following
procedure. If you want to use Liferay's built-in data source, you can
skip this section.

1.  Browse to your WebLogic Console. Click the *Lock & Edit* button
    above the Domain Structure tree on the left side of the page.

2.  From the Domain Structure tree on the left, select *Data Sources*.
    Then click the *New* button on the right side of the screen.

3.  Give the Data Source a name, such as `LiferayDataSource`{.western}.

4.  Define the JNDI name as `jdbc/LiferayPool`{.western}.

5.  Select your Database Type and the Driver class, and then click the
    *Next* button.

6.  Accept the defaults on the next screen by clicking *Next*.

7.  On the next screen, put in your *Database Name*, *Host Name*, *Database
    User Name*, and *Password*. If you have been following the defaults
    we have been using so far, you would use *lportal, localhost, root,* 
    and no password as the values. Click *Next*.

8.  The next screen allows you to test your database configuration.
    Click the *Test Connection* button. If the test succeeds, you have
    configured your database correctly. Check off the server you want to
    deploy this Data Source to (AdminServer is the default). Click
    Finish.

9.  Click the *Activate Changes* button on the left, above the Domain
    Structure tree.

##### Mail Configuration

If you want WebLogic to manage your mail sessions, use the following
procedure. If you want to use Liferay's built-in mail sessions, you can
skip this section.

1.  In the Domain Structure tree, select *Mail Sessions*. Then click the
    *Lock & Edit* button again to enable modifying these settings.

2.  Click the *New* button which is now enabled on the right side of the
    screen.

3.  Give the Mail Session a name, such as *LiferayMail*.

4.  Select your new LiferayMail session from the list by clicking on it.

5.  On the screen that appears, define the JNDI name as
    `mail/MailSession`{.western}. Click the *Save* button.

6.  Click the *Targets* tab. Check off the server you want to deploy
    this Data Source to (AdminServer is the default).

7.  Click the *Activate Changes *button on the left side of the screen,
    above the Domain Structure tree.

##### Deploy Liferay

1.  

![image](../../images/02-weblogic-mail-sessions.png)
    *Illustration 6: WebLogic: Mail Sessions*

    Click the *Deployments* option in the Domain Structure tree on the
    left side of the screen.
    
2.  Click the *Lock & Edit* button above the Domain Structure tree.

3.  Click the *Install* button on the right side of the screen.

4.  Click the *Upload your file(s)* link.

5.  Browse to where you have stored the Liferay .war file, select it,
    and then click *Next*.

6.  Select the Liferay .war file from the list and click *Next*.

7.  Leave *Install this deployment as an application* selected and click
    Next.

8.  Give the application a name (the default name is fine). Leave the
    other defaults selected and then click *Finish*.

9.  WebLogic will now deploy Liferay. When it is finished, a summary
    screen is displayed. Click the *Activate Changes* link on the left
    above the Domain Structure tree.

10. Create a `portal-ext.properties`{.western} file in the Liferay Home
    folder, which is one folder up from your domain's home folder. If
    you are using Liferay's built-in data source, add the database
    settings:

11. In the Deployments screen, select the Liferay application and click
    the *Start* button. Select *Servicing All Requests* in the pop up.

12. Click *Yes* to continue on the next screen.

Liferay will start. You will be able to get to it by browsing to
`http://<server name>:7001`{.western}. If your browser is running on the
same machine upon which you have installed Liferay, the URL is
`http://localhost:7001`{.western}.

#### Oracle WebLogic 10.3

If you still have the mainWebApp module installed, you will need to
remove it first.

##### Dependency Jars

1.  Navigate to the folder which corresponds to the domain to which you
    will be installing Liferay. Inside this folder is a `lib`{.western}
    folder. Unzip the Liferay dependencies archive to this folder.

2.  Copy the JDBC driver for your database to this folder as well.

3.  Create a folder called *endorsed* in
    `$WEBLOGIC-HOME/jrockit90_150_04/jre/lib`{.western}, then copy
    `commons-lang.jar,     rhino.jar`{.western},
    `serializer.jar`{.western}, and `xalan.jar`{.western} to the
    folder that you just created.

##### Start Application Server

Start WebLogic.

##### Database Configuration

![image](../../images/portal-admin-ch2_html_02-creating-a-data-source-in-weblogic.png)
*Illustration 7: Creating a data source in WebLogic 10.3*

If you want WebLogic to manage your data source, use the following
procedure. If you want to use Liferay's built-in data source, you can
skip this section.

1.  Select *JDBC ->  Data Source..* Click *New*.

2.  Give your data source a name, such as *Liferay Data Source*. The
    JNDI name should be `jdbc/LiferayPool`{.western}.

3.  Choose the type of database. From the screen shot, you can see that
    we have chosen MySQL. The database driver class should be chosen for
    you automatically.

4.  Click *Next* twice. You should be on the *Connection Properties*
    screen. Enter the database name, the host name, the port, the
    database user name, and the password. WebLogic will use this
    information to construct the appropriate JDBC URL to connect to your
    database. Click *Next*.

5.  WebLogic will now confirm with you the information that it gathered.
    For MySQL, some additional parameters need to be added to the URL.
    Modify the JDBC URL so that it has the proper parameters:

    jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false

6.  Click *Test Configuration* to make sure WebLogic can connect to your
    database successfully. If it does, click *Finish*.

7.  You will be back to the list of data sources. Notice that your new
    data source has no value in the *Target* column. Click on your data
    source to edit it.

8.  Click the *Targets* tab and check off the server instance(s) to
    which you wish to deploy your data source. Then click *Save*.

##### Mail Configuration

1.  Select *Mail Sessions* and create a new mail session which points to
    your mail server.

2.  Give it the name Liferay Mail and give it the JNDI name of
    `mail/MailSession`{.western} and click *Next*.

3.  Choose your server and then click *Finish*.

##### Deploy Liferay

1.  Create a `portal-ext.properties`{.western} file in the Liferay Home
    folder, which is one folder up from your domain's home folder. If
    you are using Liferay's built-in data source, add the database
    settings:

    jdbc.default.driverClassName=com.mysql.jdbc.Driver

    jdbc.default.url=jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false

    jdbc.default.username=root

    jdbc.default.password=root

    If you are using WebLogic's data source, add the JNDI name instead:

    jdbc.default.jndi.name=jdbc/LiferayPool

    Do the same thing for the Mail Session. If you are using the
    built-in configuration, set the following properties for your
    system:

    mail.session.mail.pop3.host=localhost

    mail.session.mail.pop3.password=

    mail.session.mail.pop3.port=110

    mail.session.mail.pop3.user=

    mail.session.mail.smtp.auth=false

    mail.session.mail.smtp.host=localhost

    mail.session.mail.smtp.password=

    mail.session.mail.smtp.port=25

    mail.session.mail.smtp.user=

    mail.session.mail.store.protocol=pop3

    mail.session.mail.transport.protocol=smtp

    If you are using WebLogic's mail session, add the JNDI name instead:

    mail.session.jndi.name=mail/MailSession

    Save and close the file.

2.  Add the following to `server.xml`{.western}

    <library-ref\>\
     <library-name\>jsf</library-name\>\
     <specification-version\>1.2</specification-version\>\
     <implementation-version\>1.2</implementation-version\>\
     <exact-match\>false</exact-match\>\
    </library-ref\>

3.  Select *Deployments* and click the *Install* button. Upload
    `jsf-1.2.war`{.western} from WebLogic's common files directory, and
    select *Install this deployment as a library*.

4.  After installing the JSF libraries, go back to deployments and
    select the Liferay .war file from the file system or click the
    *Upload Your File(s)* link to upload it, and then click *Next*.

5.  Select *Install this deployment as an application* and click *Next*.

6.  If the default name is appropriate for your installation, keep it.
    Otherwise, give it a name of your choosing and click *Next*.

7.  Click *Finish*. After the deployment finishes, click *Save*.

![image](../../images/02-tip.png) **Tip:** After Liferay
completes installing, you may see an error initializing the Web Proxy
portlet. Because the XSL parser configured by default within WebLogic
cannot compile a style sheet in this portlet, Liferay disables it by
default. To re-enable this portlet, extract xalan.jar and serializer.jar
from the Liferay .war archive and copy them to your JDK's endorsed
folder for libraries. If you are using JRockit, you may find this folder
in

`[Bea Home]/jrockit_160_05/jre/lib/ext`{.western}.

#### WebSphere 6.1

![image](../../images/02-tip.png) **Tip:** Throughout this
installation and configuration process, WebSphere will prompt you to
Click Save to apply changes to Master Configuration. Do so
intermittently to save your changes.


**Liferay Home** is in a folder called `liferay`{.western} in the home
folder of the user ID that is running WebSphere.

##### Installation

1.  Download the Liferay Portal WAR file.

2.  Download and extract these Liferay .jars to
    `websphere/appserver/lib/ext`{.western}.

-   Dependency libraries (Liferay Portal Dependencies)

-   Your database JDBC driver .jar

-   Currently you also need to copy `portlet.jar`{.western} from the
    Liferay Dependencies archive into
    `WebSphere/AppServer/java/jre/lib/ext`{.western}, as WebSphere
    already contains older versions of the portlet .jar which must be
    overridden at the highest level of the class path. This issue may be
    fixed in future releases; check the Liferay Wiki for updates to this
    issue.

##### Database Configuration

1.  Start WebSphere.

2.  Open Administrative Console and log in.

3.  Click *Resources*, click *JDBC Providers*.

4.  Click *New*.

5.  For name, enter the name of the JDBC provider (e.g. *MySQL JDBC
    Provider*).

6.  For Implementation class name, enter the implementation class for
    your database driver's connection pool data source For MySQL, enter:

    com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource

7.  Click *Next*.

8.  Clear any text in class path. You already copied the necessary .jars
    to a location on the server's class path.

9.  Click *Next*.

10. Click *Finish*.

11. Click *Data Sources* under *Additional Properties*.

12. Click *New*.

13. Enter a name: *liferaydatabasesource*.

14. Enter JNDI: `jdbc/LiferayPool`{.western}.

15. Everything else should stay at the default values.

16. Click *Next*.

17. Under *Additional Properties*, click *Custom Properties*.

18. Click *New*.

19. Create three custom properties by entering *Name*, *Value* and
    clicking *OK* for each row in the following table.

**Name**

**Value**

\1. user

root

\2. serverName

localhost

\3. databaseName

lportal


20. Click *Data Sources -> Test Connection* to test.

##### Mail Configuration

1.  Click *Resources -> Mail Providers*.

2.  Click *Built-in Mail Provider*.

3.  Click *Mail Sessions*.

4.  Click *New*.

5.  Click *OK*.

6.  Click *Security*.

7.  Click *Secure administration, applications, and infrastructure*.

8.  Select *Enable application security*.

9.  Deselect *Use Java 2 security to restrict application access to
    local resources*.

##### Install Liferay

1.  Click *Applications -> Install new applications*.

2.  Browse for `liferay-portal-x.x.x.war`{.western}.

3.  

![image](../../02-installing-the-liferay-war-file-on-windows.png)
![image](../../02-installing-the-liferay-war-file-on-windows.png)
    *Illustration 8: Installing the Liferay .war file on WebSphere 6.1*

    Enter context root */*.
    
4.  Click *Next*. For Steps 1 to 3, click *Next* to apply defaults.

5.  Choose the *Mail Session* and *Data Source*, and then click *Next*.

6.  Specify the virtual host upon which you want Liferay to run.

7.  At the Summary Screen, click *Finish*.

8.  Wait for the installation process to complete.

9.  Save this configuration to master configuration by clicking on
    *System administration* and *Save Changes to Master Repository*.

10. Create a `portal-ext.properties`{.western} file in the Liferay Home
    folder. For WebSphere, this is a folder called `liferay`{.western}
    in the home folder of the user that is running WebSphere. If you are
    using Liferay's built-in data source, add the database settings:

##### Start Liferay Portal

1.  Applications.

2.  Click *Enterprise Applications*.

![image](../../images/02-starting-liferay-on-websphere.png)
![image](../../images/02-starting-liferay-on-websphere.png)
*Illustration 9: Starting Liferay on WebSphere 6.1*

3.  Uninstall *DefaultApplication*, *PlantsByWebSphere* and
    *SamplesGallery*.

4.  Select `liferay-portal.war`{.western}, click *Start*.

5.  Open up the browser and go to `http://localhost:9080`{.western}. The
    default Liferay home page will be displayed.

#### WebSphere 7.0

**Liferay Home** is in a folder called `liferay`{.western} in the home
folder of the user ID that is running WebSphere.

1.  Download the Liferay Portal WAR file.

2.  Download and extract these Liferay jars to
    `websphere/appserver/lib/ext`{.western}.

-   Dependency libraries (Liferay Portal Dependencies)

-   JDBC Driver for your database

##### Database Configuration

If you want WebSphere to manage the database connections, follow the
instructions below.

1.  

![image](../../images/02-websphere-jdbc-providers.png)
    *Illustration 10: WebSphere 7.0 JDBC Providers*

    Start WebSphere.

2.  Open the Administrative Console and log in.

3.  Click *Resources -> JDBC Providers*.

4.  Click *New*.

5.  For name, enter the name of JDBC provider (e.g. *MySQL JDBC
    Provider*).

6.  For Implementation class name, enter:

    com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource

7.  Click *Next*.

8.  Clear any text in class path. You already copied the necessary .jars
    to a location on the server's class path.

9.  Click *Next*.

10. Click *Finish*.

11. Click *Data Sources* under *Additional Properties*.

12. Click *New*.

13. Enter a name: *liferaydatabasesource*.

14. Enter JNDI: `jdbc/LiferayPool`{.western}.

15. Everything else should stay at the default values. Save the data
    source.

16. When finished, go back into the data source and click *Custom
    Properties*, and then click the *Show Filter Function* button. This
    is the second from last of the small icons under the *New* and
    *Delete* buttons.

17. Type *user* into the search terms and click *Go*.

18. 

![image](../../images/02-modifying-data-source-properties-in-websphere.png)
![image](../../images/02-modifying-data-source-properties-in-websphere.png)
    *Illustration 11: Modifying data source properties in WebSphere 7*

    Select the user property and give it the value of the user name to
    your database. Click *OK* and save to master configuration.

19. Do another filter search for the url property. Give it a value that
    points to your database. For example, the MySQL URL would be:
    `jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false`{.western}.
    Click *OK* and save to master configuration.

20. Do another filter search for the password property. Enter the
    password for the user ID you added earlier as the value for this
    property. Click *OK* and save to master configuration.

21. Go back to the data source page by clicking it in the breadcrumb
    trail. Click the *Test Connection* button. It should connect
    successfully.

##### Mail Configuration

1.  Click *Resources -> Mail -> Mail Providers*.

2.  Click the Built-In Mail Provider for your node and server.

3.  Click *Mail Sessions*, and then click the *New* button.

4.  Give it a name of *liferaymail* and a JNDI name of
    `mail/MailSession`{.western}. Click *OK* and save to master
    configuration.

5.  Click *Security ->  Global Security* and deselect *Use Java 2 security
    to restrict application access to local resources* if it is
    selected. Click *Apply*.

##### Install Liferay

1.  Click *Applications -> New Application -> New Enterprise
    Application*.

2.  Browse to the Liferay .war file and click *Next*.

3.  Leave *Fast Path* selected and click *Next*, and then click *Next*
    again.

4.  Make sure your server is selected and click *Next*.

5.  Keep the context root set to / and click *Next*.

6.  Click *Finish*. When Liferay has installed, click *Save to Master
    Configuration*.

##### Start Liferay

1.  Create a `portal-ext.properties`{.western} file in the Liferay Home
    folder. For WebSphere, this is a folder called `liferay`{.western}
    in the home folder of the user that is running WebSphere. If you are
    using Liferay's built-in data source, add the database settings:

    jdbc.default.driverClassName=com.mysql.jdbc.Driver

    jdbc.default.url=jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false

    jdbc.default.username=root

    jdbc.default.password=root

2.  If you are using WebSphere's data source per the instructions above,
    add the JNDI name instead:

    jdbc.default.jndi.name=jdbc/LiferayPool

3.  Save and close the file.

4.  Click *Application Types -> WebSphere Enterprise Application*.

5.  Uninstall the default application.

6.  Select the Liferay application and click *Start*.

### Making Liferay Coexist with Other Java EE Applications

Liferay Portal by default is configured to sit at the root (i.e.,
`/`{.western}) of your application server. Dedicating your application
server to running only Liferay Portal is a good practice, allowing for
separation between your portal environment and your web application
environment. This is generally a best practice for portals, which by
definition are application development platforms in and of themselves.
For that reason, your instance of Liferay is likely to be hosting many
applications, and even integrating several of them together on a single
page. For this reason, you are going to want to make sure your portal
environment has all the resources it needs to do this, and configuring
it so that it is the sole consumer of any other .war files that get
deployed to the application server helps to make sure that your system
performs optimally.

If, however, you want Liferay to share space on an application server
with other applications, there is no reason why you cannot do that. In
this instance, you may not want to make Liferay the default application
in the root context of the server.

There are two steps to modifying this behavior:

1.  Deploy Liferay in a context other than root (for example
    `/portal`{.western}).

2.  Modify the `portal-ext.properties`{.western} file to tell Liferay
    the context to which it has been deployed.

To change the file, open it in a text editor. Place the
`portal.ctx`{.western}* *property at the top of the file:

portal.ctx=/

This default setting defines Liferay Portal as the application that sits
at the root context. If you change it to something else, say */portal*,
for example, you can then deploy Liferay in that context and it will
live there instead of at the root context.

A full discussion of the `portal-ext.properties`{.western} file appears
in Chapter 6.

**Note for WebLogic Users:** WebLogic also requires that you modify the
`weblogic.xml`{.western} file which is included with Liferay. In this
file are tags for the context root:

<context-root\>/</context-root\>

Change this so that it matches the path you set in your
`portal-ext.properties`{.western} file. You will have to modify the
`weblogic.xml`{.western} file inside the Liferay .war itself. Extract
the file from the .war file, modify it, and then put it back in the .war
file. Then deploy the modified Liferay .war file to the server in the
proper context.

## Summary

This chapter is a guide to everything about installing Liferay. Whether
you choose a Liferay bundle or an existing application server, Liferay
Portal integrates seamlessly with your enterprise Java environment. It
is supported on more application servers than any other portal platform,
allowing you to preserve your investment in your application server of
choice, or giving you the freedom to move to a different application
server platform. Liferay is committed to providing you this freedom: we
have 100 test servers certifying our builds with roughly 10,000 tests
per version of Liferay Portal. Each of those tests must be run on all of
our different supported combinations of application servers, databases,
and operating systems. So you can be sure that we are committed to
supporting you on your environment of choice. Liferay Portal won't get
in your way, and you can feel safe knowing that you have the freedom to
use the software platform that is best for your organization, and
Liferay Portal will run and perform well on it.
