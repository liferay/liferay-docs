# Initial Setup

Liferay Portal is one of the most flexible applications on the market today with regard to application server environments. You can install Liferay Portal on everything from a shared Tomcat installation to a multi-node cluster running a commercial application server, and on everything in between. In fact, Liferay is used successfully in all of these scenarios every day.

You will find that because Liferay is extremely flexible in its deployment options, it is easy to install as well. If you already have an application server, you can simply use the tools for deployment that came with your application server. If you do not have an application server, Liferay provides several application server bundles from which to choose. These are very easy to install and with a small amount of configuration can be made into production-ready systems.

## Editions of Liferay

Liferay ships in two different editions: Liferay Portal Community Edition (CE) and Liferay Portal Enterprise Edition (EE). CE is the same Liferay Portal that has been available for years: frequently updated and bursting with the latest features, the Community Edition of Liferay Portal is offered for free under the Lesser GNU public license, an open source license. This license gives you the flexibility to link Liferay with your own code in your portlet, theme, hook, layout, Ext, or web plugins, no matter what license you use for your code. If, however, you modify Liferay directly, those modifications need to be contributed back to the open source product. This is really the best of both worlds: you have the freedom to do what you want with your code, and Liferay receives the benefits of any enhancements that are made directly. Liferay Portal EE is a supported version of Liferay Portal for the enterprise. Hardened for security and designed to be rock solid stable, EE is offered with a subscription and support package, allowing organizations to build their portals on a stable version of the product that is offered over an extended period of time.

Because the release cycle for EE is longer than that for CE, each enterprise release is supported for 4 years. All bug fixes in Liferay Portal are backported to your version of Liferay for the duration of your subscription. This gives organizations the peace of mind that comes from knowing that their Liferay-powered web sites are stable and will run for years to come, enabling them to build their sites on a proven, stable platform. Additionally, Liferay's professional services team offers training and consulting on the Enterprise Edition to ensure long-term support and stability for our clients.

## Obtaining Liferay

The CE version of Liferay is freely downloadable from our web site at [http://www.liferay.com](http://www.liferay.com/). Click on the *Downloads* link at the top of the page, and you will be presented with multiple options for getting a copy of Liferay, including our convenient bundles or a `.war` package for installation on your application server of choice.

The EE version of Liferay is provided to you as a result of your support subscription. You will receive download links which will allow you to obtain a copy of a Liferay bundle or a `.war` package for installation on your application server of choice.

So what is a bundle anyway? A bundle is simply an open source application server with Liferay preinstalled. If you want to install a bundle, there is a list of bundles available. If you do not currently have an application server, it is best to download the Tomcat bundle, as Tomcat is one of the smallest and most straightforward bundles to configure. If you have an application server preference, you can also choose the server you prefer from the available Liferay Portal bundles. All of the bundles ship with a Java Runtime Environment for Windows; if you are using a different operating system, you will need to have a JDK (Java Development Kit) installed prior to launching Liferay.

Please note that Liferay is not able to provide application server bundles for proprietary application servers such as WebLogic or WebSphere, because the licenses for these servers do not allow for redistribution. Liferay Portal, however, runs just as well on these application servers as it does on open source application servers. You will need to use the `.war` package to install Liferay on these application servers.

For a manual install, you will need the Liferay `.war` file as well as Liferay's dependency `.jar`s. Later in this chapter are instructions for installing Liferay on many of the major application servers available today.

![image](../../images/02-bundle-directory-structure.png) *Illustration 1: Bundle directory structure*

## Installing a Bundle

Liferay bundles contain the same directory structure regardless of application server. The top-level folder is named for the release of Liferay. This folder is also sometimes called *Liferay Home*.

Inside this folder, you will find folders for various uses:

**Data:** This folder is used to store the embedded HSQL database which the bundles use, as well as the configuration and data for the Jackrabbit JSR-170 content repository and the Lucene search index.

**Deploy:** Plugins which you wish to deploy to Liferay can be copied into this folder. It is also used by Liferay's graphical plugin installer utility, which is available from the Control Panel.

**License:** Contains both Liferay's license and a file which describes the licenses for many of the other open source projects that are used internally by Liferay.

**[Application Server]:** There will also be an application server folder which is different depending on which bundle you have downloaded. This folder contains the application server in which Liferay has been installed.

In most cases, installing a bundle is as easy as uncompressing the archive and then starting the application server. For example, if you were to install Liferay Portal on Tomcat, you would simply unzip the bundle to a location of your choice.

Now you would start Tomcat in the same way as you would if you had downloaded it manually. Tomcat is launched by way of a script which is found in its *bin* folder. If you drop to a command prompt and go to this folder, you can launch Tomcat via the following command on Windows:

    startup

or the following command on Linux / Mac / Unix:

    ./startup.sh

The Liferay / Tomcat bundle will then launch. If you are on Windows, you will see another command prompt window appear with Tomcat's console in it. If you are on Linux, you can see the Tomcat console by issuing the following command:

    tail -f ../logs/catalina.out

Once Tomcat has completed its start up, it should automatically launch a web browser so you can see the home page. If it does not, launch your web browser and then go to the following address: [http://localhost:8080](http://localhost:8080/). The default Liferay home page will then appear in your web browser. It will be using an embedded database for its configuration, but it is fully functional. You can now begin exploring the various features of Liferay.

Liferay ships by default with a sample web site included, called 7 Cogs. You can access this site and log in as the various users to get familiar with Liferay and what it can do.

Installing a different bundle is done in exactly the same way: unzip the bundle into the folder of your choice, launch the application server, and then view the portal in your web browser.

![image](../../images/02-default-page-in-the-liferay-bundles.png) *Illustration 2: Default Page in the Liferay Bundles*

As you can see, bundles are the easiest way to get started with Liferay. They come pre-configured with a running Liferay that can be used immediately to explore all of the things that Liferay can do. And with minimal extra configuration (which we will see later), bundles can be converted into full production-ready systems.

## Installing Liferay for an Enterprise

When it comes time to install Liferay Portal on your server, you'll find it is easiest to do this by starting with a bundle and then reconfiguring that bundle so that it is enterprise-ready. Because this is by far the quickest and easiest method to get a production Liferay system running, we will look at this first. Often, however, enterprises will have an established Java EE infrastructure upon which they would like to install Liferay. In this situation, a bundle will not suffice. Most of the rest of this chapter, therefore, will focus on installing Liferay onto an already-established application server.

### Sample Data

Liferay CE ships with some sample data to help you see some of the things Liferay Portal can do. While the sample 7 Cogs data is a good example of how Liferay might be used, when you are ready to build your own site, you won't want that data cluttering up your database. So before you connect Liferay to your production database, you will want to make sure you have removed the sample 7 Cogs data from your Liferay installation. This is as simple as undeploying the application that installs the 7 Cogs data.

There is only one application included in the bundle that you will need to remove. It's a hook that copies the 7 Cogs data into the database when Liferay is started. Because we want to revert Liferay's behavior back to its defaults for a clean install, you will want to remove the *sevencogs-hook* application. The other two applications related to 7 Cogs are both themes, which you can leave installed if you wish.

If you forget to undeploy the *sevencogs-hook* application before you connect Liferay to your real database, the sample data will be created in your database and may cause issues, especially if you already have data in your database. So you want to make sure that you get *sevencogs-hook* undeployed before setting up your server. Use your application server's method for uninstalling applications in order to remove them.

### Liferay Home

Liferay Portal uses a special folder defined as *Liferay Home*. This folder is one folder higher than the location of the application server itself. This is why the bundles place the application server one folder in from the bundle's root folder.

If Liferay is unable to create the resources it needs in this folder, or if it finds itself running on certain application servers, it will fall back to defining the home folder in the home folder of the user ID that is running Liferay.

As described above in the *Bundles* section, the home folder is very important to the operation of Liferay. The aforementioned folders (*data*, *deploy*, and *license*) will be created there, and you can also put a special configuration file called `portal-ext.properties` there.

This file is fully documented in Chapter 6: *Advanced Liferay Configuration*, but we will use it in this chapter for some basic configuration, including setting up Liferay to talk to our database.

### Database Setup

#### Default Method: Automatic

If you create your database and grant a user ID full access to it, Liferay can use that user ID to create its indexes and tables automatically. This is the recommended way to set up Liferay, as it allows you to take advantage of Liferay's ability to automatically maintain its database during upgrades or through various plugin installs which may create tables of their own. It is by far the best way to set up your Liferay installation.

If you will be setting up Liferay's database with the recommended permissions, you can skip the next section.

#### Manual Method

![image](../../images/02-tip.png) **Note:** This is not the recommended set up for Liferay installations, but is documented here so that enterprises with more restrictive standards can install Liferay with more strict — but suboptimal — database settings. If it is at all possible, Liferay recommends that you use the automatic method as documented above instead of the procedure outlined below.

Even though Liferay can create its database automatically, some enterprises prefer *not* to allow the user ID configured in an application server to have the permissions over the database necessary for Liferay and its plugins to maintain their tables. For these organizations, Select, Insert, Update, and Delete are generally all the permissions that are granted, and so we will go over how to set up the database manually. If your organization *is* willing to grant the Liferay user ID permissions to create and drop tables in the database—and this is the recommended configuration—you can skip this section.

One other caveat is this: Liferay has an automatic database upgrade function which runs when the version of Liferay is upgraded to a new release. If the user ID that accesses the database does not have enough rights to create / modify / drop tables in the database, you will need to grant those rights to the ID before you start your upgraded Liferay for the first time. Once the upgrade is complete, you can remove those rights until the next upgrade. Additionally, many plugins provided by Liferay require that new tables be added to Liferay's database. These plugins cannot be installed if Liferay does not have permission to create these tables automatically. If you wish to install these plugins, you will need to grant rights to create tables in the database before you attempt to install them.

Liferay provides an SQL script archive download on the web site. For the CE version, it is in the *Additional Files* section of the Downloads page. For the EE version, you will be provided a link to this archive. Download this file and unzip it. You will find that it contains a folder structure that is broken down by the type of script (full, minimal, or upgrade), and then further by database vendor type.

It is best to use the `create-minimal` script if you are installing a fresh version of Liferay on a development, QA, or production server. This script creates the necessary Liferay tables in the database, with a minimum configuration. This is most appropriate for a new installation of Liferay.

The `create` script, by contrast, configures a Liferay database with a portion of the content from [http://www.liferay.com](http://www.liferay.com/) embedded in it. This can be useful from a development perspective, as it contains working examples of the use of many of Liferay's features, including the Content Management System.

Inside the `create` or `create-minimal` folders are scripts for every database that Liferay supports. A DBA can use the script provided to create the Liferay database, complete with the indexes necessary for optimal performance. Once this is done, be sure that the ID that the portal will use to connect to the database has at least Select, Insert, Update, and Delete permissions. Preferably, however, the ID should also have rights to create, modify, and drop tables and indexes, as this makes upgrading easier. This, however, is not necessary for the daily operation of Liferay.

Once your DBA has created the database and provided the credentials for accessing it, you are ready to begin 1) making a bundle enterprise-ready or 2) manually installing Liferay on your application server.

### Turning a Bundle into an Enterprise Portal

Liferay Portal is distributed with the following bundle options for servlet containers and full Java EE application servers:

-   Geronimo+Tomcat

-   Glassfish 3

-   JBoss

-   Jetty

-   JOnAS

-   Resin

-   Tomcat 6.0

Choose your preferred bundle and download it from the downloads page on Liferay's web site or via the EE links that were provided to you. A prerequisite for running any of the bundles is that you have the proper version of the Java Development Kit (1.5 or higher) installed on the machine to which you are installing Liferay. Make sure that you have also created the `JAVA_HOME` environment variable and have pointed it to your Java installation.

Unzip the bundle to the location from which you are going to run it. For example, you might use `D:apps` in Windows or `/opt` in Linux or UNIX variants. The default bundle installation of Liferay Portal uses an embedded database. While this is a good method to have it up and running fast for evaluation or development, it has several drawbacks:

-   Only one user can access it at a time. This is because the data is stored on a file on disk and HSQL locks it when doing changes.

-   The data is stored inside the bundle and might be lost on redeployment.

-   This configuration does not scale well and will have performance problems when multiple users are accessing the system.

Obviously, you do not want to be running Liferay against the embedded database. Fortunately, Liferay has great support for a good number of production-ready databases, and it is easy to configure Liferay to use them. The exact instructions will depend on the application server and database, but can be summarized as:

1.  Create the database in your DBMS of choice (see the above section labeled *Database Setup* for further information).

2.  [Optional] Create a Data Source called `jdbc/LiferayPool` in your application server which points to your database and has the proper credentials to access it.

3.  [Optional] Create a mail session called `mail/MailSession` in your application server which points to your mail server, so Liferay can send mail.

4.  Create a `portal-ext.properties` file in the Liferay Home folder which either points directly to the database and mail session or points to the application server's Data Source and mail session.

5.  Start Liferay. Liferay will create the tables automatically and start. Otherwise, you will have had to prepare the database first by running the appropriate `create` script.

Refer to the manual installation instructions below for further details on configuring the various application servers. There is no difference between the Liferay bundles and the regular distribution archives of the application servers as they are available from their own sites, with the exception that Liferay is pre-installed in them, and the JVM settings may have been optimized for use with Liferay.

#### The portal-ext.properties File

To point your Liferay bundle to your database, create a file called `portal-ext.properties` in your Liferay Home folder. This file overrides default properties that come with Liferay. You are going to override the default configuration which points Liferay to the embedded HSQL database.

There are two ways to set up the connection:

-   Use your application server's connection pool.

-   Use the built-in connection pool.

If you want to use your application server's connection pool, you will have to create one in your application server that points to your database. It should be called `jdbc/LiferayPool`. To cause Liferay to use this connection pool, add the following directive to your `portal-ext.properties` file:

jdbc.default.jndi.name=jdbc/LiferayPool

To use the built-in connection pool -- based on *C3P0* -- add the template which is provided in Chapter 6 for your particular database. The template for MySQL is provided as an example below.

    # 
    # MySQL 
    # 
    jdbc.default.driverClassName=com.mysql.jdbc.Driver     jdbc.default.url=jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false     jdbc.default.username=
    jdbc.default.password

You would provide the user name and password to the database as values for the *username* and *password* directives.

For mail, there is a similar procedure. Again, you have two ways to configure your server:

-   Use your application server's mail session.

-   Use the built-in mail session.

To use your application server's mail session, you will have to create one in your application server that points to your mail server. Once you have done that, add the following directive to your `portal-ext.properties` file:

	mail.session.jndi.name=mail/MailSession

Save the file. You can now start your application server.

To use Liferay's built-in mail session, go to *Control Panel &rarr; Server Administration &rarr; Mail* and enter your settings for your mail session settings.

### Installing Liferay on an Existing Application Server

This section contains detailed instructions for installing Liferay Portal using its WAR distribution. This allows system administrators to deploy Liferay in existing application server installations. It is recommended that you have a good understanding of how to deploy Java EE applications in your application server of choice.

#### Installing Liferay in 10 Easy Steps

There are 10 generic steps to installing Liferay on an existing application server:

1.  Obtain the Liferay `.war` file and the dependencies archive.

2.  Make sure you do not have an application listening at the root (`/`) of your server. If you do, move it to a different context or undeploy it.

3.  Decide whether you want to use your application server's data sources or if you want to use the one included with Liferay. If you want to use your application server's data source, create a data source for Liferay called `jdbc/LiferayPool`.

4.  Decide whether you want to use your application server's mail session or if you want to use the one included with Liferay. If you want to use your application server's mail session, create one called `mail/MailSession`.

5.  Shut your application server down.

6.  Extract the dependencies to a location on your server's global class path. This allows both Liferay and plugins to access these dependencies.

7.  Create a `portal-ext.properties` file and place it in the Liferay Home folder. See the notes on specific application servers below for the location of this folder for your application server.

8.  Add either the JNDI name of your data source or the JDBC parameters above to connect Liferay to your database.

9.  Add either the JNDI name of your mail session or the mail parameters above to connect Liferay to your mail server.

10. Start your application server, deploy the Liferay `.war` file, and start it.

The instructions below are specific for each application server that Liferay supports. Liferay supports a wide combination of application servers and databases. Because of this, for brevity this section assumes MySQL as the database, that the database has already been created, and that you are using your application server's mail session and data source. To use other databases, substitute the JDBC driver and URL construct for your database in place of the MySQL ones shown here.

We also assume your application server is already installed and running successfully. If you still need to install your application server, please follow your vendor's instructions first.

The following instructions assume an installation on a local machine. When installing to a remote server, substitute `localhost` with the host name or IP of the server.

![image](../../images/02-tip.png) **Tip:** Note that Liferay 5.x and above *requires* JDK 1.5 or greater. Do not attempt to install Liferay 6.x on an application server that runs under Java 1.4 or lower; it will not work. If you are running an application server that ships with a JDK and that JDK is 1.4 or lower, you will need to upgrade your application server in order to run current versions of Liferay Portal. Liferay 4.x, however, will run fine on these application servers.

Remember, for all of these application servers, create your `portal-ext.properties` file in the Liferay Home folder and make sure it points to your database connection pool and mail session.

#### GlassFish 3.1.x

*Liferay Home* is three folders above your GlassFish domain folder.

For example, if your domain location is `/glassfish-3.1-web/glassfish3/glassfish/domains/domain1`, then your Liferay Home will be `/glassfish-3.1-web/glassfish3/`.

These instructions assume that you have already configured a domain and server, and that you have access to the GlassFish administrative console.

##### Dependency Jars

1.	Navigate to the folder which corresponds to the domain in which you will be installing Liferay. Inside this folder is a `lib` sub-folder.

	Unzip the Liferay dependencies archive so that its `.jar` files are extrated into this `lib` folder. Copy your database's JDBC driver (e.g., mysql.jar) into `lib` as well.

2.	On GlassFish 3.0.1, you will need to extract `commons-codec.jar` from the Liferay Portal WAR file, rename it to `commons-codec-repackaged.jar`, and copy it into `[Liferay Home]/glassfish/modules/`, overwriting GlassFish's version of the file.

##### Domain Configuration

1.  Before starting GlassFish, you will need to modify the settings in your domain to do the following ...

	- Set the file encoding 
	- Set the user time-zone 
	- Set the preferred protocol stack 
	- Prevent the application server from setting static fields (final or non-final) to `null` 
	- Increase the default amount of memory available.

	In your domain's `config` folder, open the `domain.xml` file and merge the following JVM options into the current list of JVM options within your <java-config> element:

		<jvm-options>-Dfile.encoding=UTF8</jvm-options> 
		<jvm-options>-Djava.net.preferIPv4Stack=true</jvm-options>
		<jvm-options>-Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false</jvm-options>
		<jvm-options>-Duser.timezone=GMT</jvm-options>
		<jvm-options>-Xmx1024m</jvm-options>
		<jvm-options>-XX:MaxPermSize=256m</jvm-options>

	Be sure that any existing options with values such as `-Dfile.encoding`, `-Djava.net.preferIPv4Stack`, `-Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES`, `-Duser.timezone`, or `-XX:MaxPermSize` are replaced with the new values listed above.

	For example, replace: 

		<jvm-options>-Xmx256m</jvm-options>
	
	with this: 
	
		<jvm-options>-Xmx1024m</jvm-options>

2.  Delete, rename, or move the `[domain]/docroot/index.html` file to another location to allow your Liferay Portal default page to be displayed.

##### Database Configuration

If you want to use GlassFish to manage your domain's data source, follow the instructions found in this section. Otherwise, if you want to use Liferay Portal to manage your data source, you can skip this section.

1.  Startup your domain's application server, if it is not already running.

2.	Go to the GlassFish console URL: [http://localhost:4848](http://localhost:4848/).  

3.  Under *Common Tasks*, navigate to *Resources* &rarr; *JDBC* &rarr; *JDBC Connection Pools*

	![Figure 11.x: Navigate to JDBC Connection Pools](../../images/11-glassfish31-connection-pools.PNG)

4.	Click *New...*.

5.  In the first screen, give your connection pool the name `LiferayPool`, the resource type of `javax.sql.ConnectionPoolDataSource`, and select your database driver vendor (e.g., *MySQL*).

6.	Click *Next* to advance to the next step in creating your JDBC connection pool.

	![Figure 11.x: Glassfish JDBC Connection Pool](../../images/11-glassfish-31-jdbc-connection-pool.PNG)

7.  On the this screen (step 2 of 2), scroll down to the *Additional Properties* section.

	![Figure 11.x: Glassfish JDBC Connection Pool Properties](../../images/11-glassfish-31-jdbc-connection-pool-props.PNG)  

8.	Replace or add the following properties ...

	- **URL:** the URL of your connection pool.

		For example,

			jdbc:mysql://localhost/lportal?useUnicode=true&amp;characterEncoding=UTF-8&amp;emulateLocators=true                  Note, if you are using the above example, you should specify the name of your database in place of `lportal`. Likewise, if your database is not on the same server as GlassFish, specify your the database server's host name in place of `localhost`. Lastly, specify your database type in place of `jdbc:mysql`.

	- **user:** the name of your database user.

	- **password:** your database user's password.

10.  Click *Finish*.

	You should now see your `LiferayPool` connection pool listed under *Resources* &rarr; *JDBC* &rarr; *JDBC Connection Pools*

11. Test your connection by selecting your `LiferayPool` connection pool and clicking *Ping*.

	If you get a message stating  *Ping Succeeded*, you've set up the connection pool of your data source!

13. Now, you'll setup a JDBC resource to refer to the `LiferayPool` connection pool you just created.

14.	Navigate to *Resources* &rarr; *JDBC* &rarr; *JDBC Resources* to show the current JDBC resources listed by their JNDI names.

14. Click *New...*.

15. Set the JNDI name to `jdbc/LiferayPool` and select `LiferayPool` as the pool name.

16. Click *OK*.

Congratulations! You've now configured your domain's data source to manage on GlassFish!

##### Mail Configuration

If you want to use GlassFish to manage your mail session, follow GlassFish's documentation on configuring a JavaMail session. Note, for GlassFish 3.1, this involves using the `asadmin` commandline tool provided with GlassFish. If you want to use Liferay Portal to manage your mail session, you can disregard this section.

##### Domain Configuration - Continued

1.	Shutdown your domain's application server if it is currently running.

2.  Create a `portal-ext.properties` file in the *Liferay Home* folder mentioned at the beginning of this GlassFish installation section.

3.	If you are using *Liferay Portal* to manage your data source, add the following directives to the `portal-ext.properties` file:

		jdbc.default.driverClassName=com.mysql.jdbc.Driver 		jdbc.default.url=jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false 		jdbc.default.username=root 		jdbc.default.password=root

	Otherwise, if you are using *GlassFish* to manage your data source, add the following to refer to your data source:

		jdbc.default.jndi.name=jdbc/LiferayPool

4.	If you are using GlassFish to manage your mail session, add the following to your `portal-ext.properties` file to reference that mail session:

		mail.session.jndi.name=mail/MailSession

	Otherwise, if you are using Liferay Portal to manage your mail session, go to *Control Panel &rarr; Server Administration &rarr; Mail* and enter settings for your mail session.

##### Deploy Liferay

1.  Startup your domain's application server.

1.  Go to the GlassFish console URL: [http://localhost:4848](http://localhost:4848/)

2.  Click *Applications* in the tree on the left.

3.  Click *Deploy*.

4.  Under *Packaged File to Be Uploaded to the Server* click *Choose File*, and browse to the location of the Liferay Portal `.war` file.  5.	Enter *Context Root:* `/`

6.	Enter *Application Name:* `liferay-portal`

7.  Click *OK*.

    ![Figure 11.x Deploying Liferay in GlassFish 3.1.x](../../images/11-deploying-liferay-in-glassfish-31.png)

Liferay Portal will now be deployed and started automatically to your server's host and port (e.g., [http://localhost:0808](http://localhost:0808/)). Your installation of Liferay Portal on GlassFish is complete!

#### Jetty 6

**Liferay Home** is one folder above Jetty's install location.

1.  Download and install Jetty 6.

2.  Download the Liferay Portal `.war` file.

3.  Download Liferay Portal Dependencies.

4.  Copy the dependencies to* *`$JETTY_HOME/lib/ext`.

5.  Edit `$JETTY_HOME/extra/etc/start-plus.config`.

    $(jetty.home)/lib/ext/ $(jetty.home)/lib/ext/*

6.  Create a data source bound to `jdbc/LiferayPool` by editing `$JETTY_HOME/etc/jetty.xml`.

    <Call name="addService">
        <Arg>
            <New class="org.mortbay.jetty.plus.JotmService">
                <Set name="Name">TransactionMgr</Set>
                <Call name="addDataSource">
                    <Arg>jdbc/LiferayPool</Arg>
                    <Arg>
                        <New class="org.enhydra.jdbc.standard.StandardXADataSource">
                            <Set name="DriverName">com.mysql.jdbc.Driver</Set>
                            <Set
                            name="Url">jdbc:mysql://localhost/lportal?useUnicode=true&amp;characterEncoding=UTF-8</Set>
                            <Set name="User"></Set>
                            <Set name="Password"></Set>
                        </New>
                    </Arg>
                    <Arg>
                        <New class="org.enhydra.jdbc.pool.StandardXAPoolDataSource">
                            <Arg type="Integer">4</Arg>
                            <Set name="MinSize">4</Set>
                            <Set name="MaxSize">15</Set>
                        </New>
                    </Arg>
                </Call>
            </New>
        </Arg>
    </Call>

7.  Download `mysql-connector-java-{$version}-bin.jar` and copy to `$JETTY_HOME/lib/ext`. This is the JDBC driver for MySQL. If you are using a different database, copy the appropriate driver.

8.  Create a mail session bound to `mail/MailSession` by editing `$JETTY_HOME/etc/jetty.xml`:

    <Call name="addService">
        <Arg>
            <New class="org.mortbay.jetty.plus.MailService">
                <Set name="Name">MailService</Set>
                <Set name="JNDI">mail/MailSession</Set>
                <Put name="mail.smtp.host">localhost</Put>
            </New>
        </Arg>
    </Call>

9.  Create `$JETTY_HOME/etc/jaas.config`.

    PortalRealm {
        com.liferay.portal.kernel.security.jaas.PortalLoginModule required;
    };

10. Create directory `$JETTY_HOME/webapps/root` and *unpack* the Liferay `.war` file into it.

11. Go to `$JETTY_HOME/webapps/root/WEB-INF/lib` and delete `xercesImpl.jar` and `xml-apis.jar`.

15. Copy `$JETTY_HOME/webapps/root/WEB-INF/lib/commons-logging.jar` to `$JETTY_HOME/ext` (overwriting the existing one).

16. Create batch file: `run.bat`.

    @echo off

    if "" == "%JAVA_HOME%" goto errorJavaHome

    %JAVA_HOME%/bin/java -Xmx512m -Dfile.encoding=UTF8 -Duser.timezone=GMT         -Djava.security.auth.login.config=../etc/jaas.config         -DSTART=../extra/etc/start-plus.config -jar ../start.jar         ../etc/jetty.xml

    goto end

    :errorJavaHome         echo JAVA_HOME not defined.

    goto end

    :end

**Note:** If you get a `java.lang.OutOfMemoryError` exception while starting up Jetty, give your JVM more memory by setting `-Xmx1024m`.

Start Liferay by running `run.bat`. Open your browser to `http://localhost:8080`. You should see the default Liferay home page.

#### JBoss 5.x

**Liferay Home** is one folder above JBoss's install location.

1.  Download and install JBoss AS 5.0.1 GA into your preferred directory. This directory will be referred to below as `$JBOSS_HOME`.

2.  Download the latest version of the Liferay Portal `.war` file.

3.  Remove `hibernate-validator.jar` from `$JBOSS_HOME/common/lib.`

4.  Go to `$JBOSS_HOME/server/default/lib/`. Download `mysql-connector-java-{$version}-bin.jar` and copy to this directory. (This is the JDBC connector for MySQL. Use the appropriate driver for your database.)

5.  Download Liferay's Portal Dependencies. Unzip to `$JBOSS_HOME/server/default/lib`.

6.  Configure JAAS. Edit `$JBOSS_HOME/server/default/conf/login-config.xml`* *and comment out the entire XML for policy *other *in lines 115-131.

    <!--<application-policy name = "other">-->         
    ...         
    <!--<authentication> 
    <login-module code = "org.jboss.security.auth.spi.UsersRolesLoginModule" 
    flag = "required" /> 
    </authentication> 
    </application-policy>-->

##### Database Configuration

If you want JBoss to manage the data source, use the following instructions. If you want to use the built-in Liferay data source, you can skip this section.

Create `$JBOSS_HOME/server/default/deploy/liferay-ds.xml` with the following content:

    <datasources>
        <local-tx-datasource>
            <jndi-name>jdbc/LiferayPool</jndi-name>
            <connection-url>
                jdbc:mysql://localhost/lportal?useUnicode=true&amp;characterEncoding=UTF-8
            </connection-url>
            <driver-class>com.mysql.jdbc.Driver</driver-class>
            <user-name></user-name>
            <password></password>
            <min-pool-size>0</min-pool-size>
        </local-tx-datasource>
    </datasources>

##### Mail Configuration

If you want JBoss to manage the mail configuration, use the following instructions. If you want to use the built-in Liferay mail session, you can skip this section.

Set mail properties by replacing the contents of `$JBOSS_HOME/server/default/deploy/mail-service.xml` with:

    <?xml version="1.0"?>
    <server>
        <mbean code="org.jboss.mail.MailService"
        name="jboss:service=MailSession">
            <attribute name="JNDIName">mail/MailSession</attribute>
            <attribute name="User">nobody</attribute>
            <attribute name="Password">password</attribute>
            <attribute name="Configuration">
                <configuration>
                    <property name="mail.store.protocol" value="imap" />
                    <property name="mail.transport.protocol" value="smtp" />
                    <property name="mail.imap.host" value="localhost" />
                    <property name="mail.pop3.host" value="localhost" />
                    <property name="mail.smtp.host" value="localhost" />
                </configuration>
            </attribute>
        </mbean>
    </server>

##### Deploy Liferay

1.  Delete all the files and folders in     `$JBOSS_HOME/server/default/deploy/ROOT.war`

2.  Unzip the Liferay `.war` file to the `ROOT.war` directory.

3.  Remove `jaxrpc.jar`, `stax.jar`, `xercesImpl.jar`, `xml-apis.jar` from:

    $JBOSS_HOME/server/default/deploy/ROOT.war/WEB-INF/lib

4.  Navigate to the Liferay Home folder, which is one folder above JBoss's install location.

5.  Create a file called `portal-ext.properties`. Add the following directives to the file:

    jdbc.default.driverClassName=com.mysql.jdbc.Driver         jdbc.default.url=jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false         jdbc.default.username=root         jdbc.default.password=root

Start JBoss. Open your browser to `http://localhost:8080`. You should see the default Liferay home page.

#### Resin 3.1.x

**Liferay Home** is one folder above Resin's install location.

1.  Download and install Resin into your preferred directory. From now     on, the directory where you installed Resin will be referred to as     `$RESIN_HOME`.

2.  Edit `$RESIN_HOME/conf/resin.conf`. Replace lines 8-13     with:

    <class-loader>
        <tree-loader path="${resin.home}/lib"/>
        <tree-loader path="${server.root}/lib"/>
        <compiling-loader path="${server.rootDir}/common/classes"/>
        <library-loader path="${server.rootDir}/common/lib"/>
    </class-loader>

And add the following:

    <database>
        <jndi-name>jdbc/LiferayPool</jndi-name>
        <driver type="com.mysql.jdbc.Driver">
            <url>jdbc:mysql://localhost/lportal?useUnicode=true&amp;characterEncoding=UTF-8
            </url>
            <user></user>
            <password></password>
        </driver>
        <prepared-statement-cache-size>8</prepared-statement-cache-size>
        <max-connections>20</max-connections>
        <max-idle-time>30s</max-idle-time>
    </database>
    <resource jndi-name="mail/MailSession" type="javax.mail.Session">
        <init>
            <mail.store.protocol>imap</mail.store.protocol>
            <mail.transport.protocol>smtp</mail.transport.protocol>
            <mail.imap.host>localhost</mail.imap.host>
            <mail.pop3.host>localhost</mail.pop3.host>
            <mail.smtp.host>localhost</mail.smtp.host>
        </init>
    </resource>
    <system-property
        javax.xml.parsers.DocumentBuilderFactory="org.apache.xerces.jaxp.DocumentBuilderFactoryImpl"
    />
    <system-property
        javax.xml.parsers.SAXParserFactory="org.apache.xerces.jaxp.SAXParserFactoryImpl"
    />
    <system-property
        javax.xml.transform.TransformerFactory="org.apache.xalan.processor.TransformerFactoryImpl"
    />
    <system-property
        org.xml.sax.driver="org.apache.xerces.parsers.SAXParser" />

3.  Go to `$RESIN_HOME` and create a new directory called `common/lib`. Download `mysql-connector-java-{$version}-bin.jar` and copy to this directory. This is the JDBC connector for MySQL. If you are using another database, substitute this with the appropriate driver.

4.  Download the Liferay Portal Dependencies and unzip into `$RESIN_HOME/common/lib`.

5.  Delete contents of `$RESIN_HOME/webapps/ROOT`.

6.  Unzip `liferay-portal-x.x.x.war` to `$RESIN_HOME/webapps/ROOT`.

7.  If you are using Resin 3.1.9 or higher, remove `$RESIN_HOME/lib/portlet-01.jar`. This contains the old Portlet 1.0 classes. The Portlet 2.0 classes are backwards compatible, so this should not affect anything.

8.  Next, you will need several `.jar` files which are included as part of the Liferay source distribution. Many application servers ship with these already on the class path, but Resin does not. The best way to get the appropriate versions of these files is to download the Liferay source code and get them from there. Once you have downloaded the Liferay source, unzip it to a temporary folder.

1.  Go to `$LIFERAY_SOURCE/lib/development/` and copy `activation.jar` and `mail.jar` to `$RESIN_HOME/common/lib`. Copy `saxpath.jar` and `xalan.jar` to `$RESIN_HOME/lib`.
        
2.  Go to `$LIFERAY_SOURCE/lib/portal` and copy `xercesImpl.jar` and `xml-apis.jar` to `$RESIN_HOME/lib`.

9.  To start the server, open a command prompt, navigate to the     `$RESIN_HOME` and type:

    java -jar lib/resin.jar start

10. Open your browser to `http://localhost:8080`. You should see the default Liferay home page.

#### Resin 3.2.x

**Liferay Home** is one folder up from Resin's install location.

1.  Download and install Resin 3.2.1 into your preferred directory. From now on, the directory where you installed Resin will be referred to as `$RESIN_HOME`.

2.  Edit `$RESIN_HOME/conf/resin.conf`. Replace lines 9-13 with:

        <tree-loader path="${resin.home}/ext-lib"/>
        <tree-loader path="${resin.root}/ext-lib"/>
        <tree-loader path="${resin.home}/lib"/>
        <tree-loader path="${resin.root}/lib"/>
        <compiling-loader path="${server.rootDir}/common/classes"/>
        <library-loader path="${server.rootDir}/common/lib"/>

3.  Search `<jvm-arg>` tag in `resin.conf` and     replace what is there with the following:

        <jvm-arg>-Xmx256m</jvm-arg>
        <jvm-arg>-Xss1m</jvm-arg>
        <jvm-arg>-Dcom.sun.management.jmxremote</jvm-arg>
        <jvm-arg>-Xmx1024m</jvm-arg>
        <jvm-arg>-XX:MaxPermSize=256m</jvm-arg>
        <jvm-arg>-Dfile.encoding=UTF-8</jvm-arg>
        <jvm-arg>-Duser.timezone=GMT</jvm-arg>

4.  Go to `$RESIN_HOME` and create a new directory called `common/lib`. Download `mysqlconnector-java-{$version}-bin.jar`* *and copy to this directory. This is the JDBC connector for MySQL. If you are using another database, substitute this with the appropriate driver.

5.  Download the Liferay Portal Dependencies and unzip into `$RESIN_HOME/common/lib`.

6.  Delete the contents of `$RESIN_HOME/webapps/ROOT`.

7.  Unzip `liferay-portal-x.x.x.war` to `$RESIN_HOME/webapps/ROOT`.

8.  Next, you will need several `.jar` files which are included as part of the Liferay source distribution. Many application servers ship with these already on the class path, but Resin does not. The best way to get the appropriate versions of these files is to download the Liferay source code and get them from there. Once you have downloaded the Liferay source, unzip it to a temporary folder.

-   Go to `$LIFERAY_SOURCE/lib/development`. Copy `saxpath.jar` to `$RESIN_HOME/common/lib`

9.  To start the server, open a command prompt, navigate to the `$RESIN_HOME` and type:

    java -jar lib/resin.jar start

Open your browser to `http://localhost:8080`. You should see the default Liferay home page.

#### Tomcat 6.0.x-7.0.x

**Liferay Home** is one folder above Tomcat's install location.

For this section, we will refer to your Tomcat server's installation location as `$TOMCAT_HOME`. If you do not already have an existing Tomcat server, we recommend that you download a Liferay/Tomcat bundle from [http://www.liferay.com/downloads/liferay-portal/available-releases](http://www.liferay.com/downloads/liferay-portal/available-releases). If you have an existing Tomcat server or would like to install Liferay on Tomcat manually, please follow the steps below.

Before you begin, make sure you have downloaded the latest Liferay `.war` file and Liferay Portal dependencies from [http://www.liferay.com/downloads/liferay-portal/additional-files](http://www.liferay.com/downloads/liferay-portal/additional-files). The Liferay `.war` file should be called `liferay-portal-6.1.x-<date>.war` and the dependencies file should be called `liferay-portal-dependencies-6.1.x-<date>.zip`.

*Note:* If you are using JDK 5, move `$TOMCAT_HOME/webapps/ROOT/WEB-INF/lib/xercesImpl.jar` to `$TOMCAT_HOME/common/endorsed`. JDK 1.4 is no longer supported in Liferay 5.x and above.

**Installation Steps**:

1. If you are manually installing Liferay on a clean Tomcat server, delete the contents of the `$TOMCAT_HOME/webapps/ROOT` directory. This undeploys the default Tomcat home page. Then extract the Liferay `.war` file to `$TOMCAT_HOME/webapps/ROOT`.

2. Extract the Liferay dependencies file to `$TOMCAT_HOME/lib/ext`. If the files do not extract to this directory, you can copy the dependencies archive to this directory, extract them, and then delete the archive.

*Note:* Tomcat 6 users need to delete the `ccpp.jar` file from `$TOMCAT_HOME/lib/ext`. If you are using Tomcat 7, this file can stay.

3. Create a `setenv.bat` (Windows) or `setenv.sh` file (Unix, Linux, Mac OS) in the `$TOMCAT_HOME/bin` directory. When you start Tomcat, Catalina will call `setenv.bat` or `setenv.sh`. Edit the file and populate it with following contents:

	JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF8 -Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m"

This sets the character encoding to UTF-8, sets the time zone to Greenwich Mean Time, and allocates memory to the Java virtual machine.

4. Create the directory `$TOMCAT_HOME/conf/Catalina/localhost` and create a `ROOT.xml` file in it. Edit this file and populate it with the following contents:

	<Context path="" crossContext="true"> 	 	</Context> 	 Setting `crossContext="true"` allows multiple web apps to use the same class loader.

5. Open `$TOMCAT_HOME/conf/catalina.properties` and replace the line `common.loader=${catalina.base}/lib,${catalina.base}/lib/*.jar,${catalina.home}/lib,${catalina.home}/lib/*.jar` with `common.loader=${catalina.base}/lib,${catalina.base}/lib/*.jar,${catalina.home}/lib,${catalina.home}/lib/*.jar,${catalina.home}/lib/ext,${catalina.home}/lib/ext/*.jar`. This allows Catalina to access the dependency jars that you extracted to `$TOMCAT_HOME/lib/ext`.

6. Open `$TOMCAT_HOME/conf.server.xml` and do a search and replace of the line `redirectPort="8443"` with the line `redirectPort="8443" URIEncoding="UTF-8"`. This ensures that your server will consistently use UTF-8 encoding.

7. Make sure that there is no `support-catalina.jar` file in your `$TOMCAT_HOME/webapps` directory. If you find one, remove it.

8. Make sure that your database server is installed and working. If it's installed in a different machine, make sure that it's accessible from the one where Liferay is being installed.

9. Make sure the JDBC driver for your database is accessible by Tomcat. Obtain the JDBC driver for your version of the database server. In the case of MySQL, use `mysql-connector-java-{$version}-bin.jar`. You can download the latest MySQL JDBC driver from [http://www.mysql.com/products/connector/](http://www.mysql.com/products/connector/). Extract the JAR file and copy it to `$TOMCAT_HOME/lib/ext`.

11. Configure the data sources for your database by creating a `portal-ext.properties` file in your *Liferay Home* directory (the one your $TOMCAT_HOME directory is sitting in). Edit the file and populate it with the following contents:

	jdbc.default.url=jdbc:mysql://localhost/lportal?useUnicode=true&amp;characterEncoding=UTF-8&amp;useFastDateParsing=false 	jdbc.default.driverClassName=com.mysql.jdbc.Driver 	jdbc.default.username=root 	jdbc.default.password=root 	 Note that the above properties file assumes that your database name is *lportal* and your MySQL username and password are both *root*. You'll have to update these values with your own database name and credentials.

12. Start Tomcat. You can do this by executing `$TOMCAT_HOME/bin/startup.bat` or `$TOMCAT_HOME/bin/startup.sh`. Then point your browser to `http://localhost:8080`. You should see the default Liferay home page. Congratulations on successfully installing Liferay!

#### WebLogic 10

Note: WebLogic 10.0 supports JDK 1.5, but does *not* support JDK 1.6.

**Liferay Home** is one folder above the domain to which you will be installing Liferay.

For example, if your domain location is `/Oracle/Middleware/user_projects/domains/base_domain`, then your Liferay Home will be `/Oracle/Middleware/user_projects/domains`.

These instructions assume that you have already configured a domain and server, and that you have access to the WebLogic console.

##### Dependency Jars

1.  Navigate to the folder which corresponds to the domain to which you will be installing Liferay. Inside this folder is a `lib` folder. Unzip the Liferay dependencies archive to this folder so that the dependency `.jar` files are extracted into the `lib` folder.

2.  If WebLogic does not already have access to the JDBC driver for your database, copy the driver to your domain's `lib` folder as well.

3.  You will also need the `xercesImpl.jar` copied to your domain's `lib` folder or you will get SAX parsing errors after you deploy Liferay. You may download this  from [http://xerces.apache.org](http://xerces.apache.org/).

4.  Create a folder called *endorsed* in `$WEBLOGIC-HOME/jrockit_150_15/jre/lib`, then copy `commons-lang.jar, liferay-rhino.jar`, `serializer.jar`*,* and `xalan.jar` to the folder that you just created.

##### Database Configuration

If you want WebLogic to manage your data source, use the following procedure. If you want to use Liferay's built-in data source, you can skip this section.

![image](../../images/02-weblogic-data-sources.png) *Illustration 5: WebLogic: Data Sources*.

1.  Browse to your WebLogic Console. Click the *Lock & Edit* button above the Domain Structure tree on the left side of the page.

2.  From the Domain Structure tree on the left, select *Data Sources*. Then click the *New* button on the right side of the screen.

3.  Give the Data Source a name, such as `LiferayDataSource`.

4.  Define the JNDI name as `jdbc/LiferayPool`.

5.  Select your Database Type and the Driver class, and then click the *Next* button.

6.  Accept the defaults on the next screen by clicking *Next*.

7.  On the next screen, put in your *Database Name*, *Host Name*, *Database User Name*, and *Password*. If you have been following the defaults we have been using so far, you would use *lportal, localhost, root,* and no password as the values. Click *Next*.

8.  The next screen allows you to test your database configuration. Click the *Test Configuration* button. If the test succeeds, you have configured your database correctly. Select the check box of the server  to which you want to deploy this Data Source (`AdminServer` is the default). Click Finish.

9.  Click the *Activate Changes* button on the left, above the Domain Structure tree.

##### Mail Configuration

If you want WebLogic to manage your mail sessions, use the following procedure. If you want to use Liferay's built-in mail sessions, you can skip this section.

![image](../../images/02-weblogic-mail-sessions.png) *Illustration 6: WebLogic: Mail Sessions*

1.  In the Domain Structure tree, select *Mail Sessions.* Then click the *Lock & Edit* button again to enable modifying these settings.

2.  Click the *New* button which is now enabled on the right side of the screen.

3.  Give the Mail Session a name, such as *LiferayMail*.

4.  Select your new LiferayMail session from the list by clicking on it.

5.  On the screen that appears, define the JNDI name as `mail/MailSession` and set your JavaMail properties. Click the *Save* button.

6.  Click the *Targets* tab. Select the check box of the server to which you want deploy this Data Source to (AdminServer is the default).

7.  Click the *Activate Changes* button on the left side of the screen, above the Domain Structure tree.

##### Deploy Liferay

![image](../../images/02-weblogic-deployments.png) *Illustration 7: WebLogic: Deployments*  1.  In the Domain Structure tree, select *Mail Sessions.*  Then click the *Lock & Edit* button above the Domain Structure tree.

3.  Click the *Install* button on the right side of the screen.

4.  Click the *Upload your file(s)* link.

5.  Browse to where you have stored the Liferay `.war` file, select it, and then click *Next*.

6.  Select the Liferay `.war` file from the list and click *Next*.

7.  Leave *Install this deployment as an application* selected and click Next.

8.  Give the application a name (the default name is fine). Leave the other defaults selected and then click *Finish*.

9.  WebLogic will now deploy Liferay. When it is finished, a summary screen is displayed. Click the *Activate Changes* link on the left above the Domain Structure tree.

10. Create a `portal-ext.properties` file in the Liferay Home folder, which is one folder up from your domain's home folder. If you are using Liferay's built-in data source, add the database settings:

    jdbc.default.driverClassName=com.mysql.jdbc.Driver
    jdbc.default.url=jdbc:mysql://localhost/lportal?
    useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
    jdbc.default.username=root
    jdbc.default.password=
        
If you are using *WebLogic's* data source, add the JNDI name instead:  

	jdbc.default.jndi.name=jdbc/LiferayPool 

11. If you are using Liferay's built-in mail session, go to *Control Panel &rarr; Server Administration &rarr; Mail* and enter your settings for your mail session settings.

12. In the Deployments screen, select the Liferay application and click the *Start* button. Select *Servicing All Requests* in the pop up.

13. Click *Yes* to continue on the next screen.

Liferay will start. You will be able to get to it by browsing to `http://<server name>:7001`. If your browser is running on the same machine upon which you have installed Liferay, the URL is `http://localhost:7001`.

#### Oracle WebLogic 10.3

**Liferay Home** is one folder above the domain to which you will be installing Liferay.

For example, if your domain location is `/Oracle/Middleware/user_projects/domains/base_domain`, then your Liferay Home will be `/Oracle/Middleware/user_projects/domains`.

These instructions assume that you have already configured a domain and server, and that you have access to the WebLogic console.

If you still have the mainWebApp module installed, you will need to remove it first.

*Note:* There is a known issue with the Sun and JRockit JVMs bundled with WebLogic 10.3.2 (see [http://bugs.sun.com/view_bug.do?bug_id=6795561](issue)). To resolve, use Sun JVM 1.6.0_u24 or JRockit JVM 1.6.0_24.

##### Dependency Jars

1.  Navigate to the folder which corresponds to the domain to which you will be installing Liferay. Inside this folder is a `lib` folder. Unzip the Liferay dependencies archive to this folder so that the dependency `.jar` files reside in the `lib` folder.

2.  If WebLogic does not already have access to the JDBC driver for your database, copy the driver to your domain's `lib` folder as well.

##### Start Application Server

Start WebLogic.

##### Database Configuration

![image](../../images/02-weblogic-10-3-data-sources.png) *Illustration 8: WebLogic: Data Sources*.

If you want WebLogic to manage your data source, use the following procedure. If you want to use Liferay's built-in data source, you can skip this section.   ![image](../../images/02-creating-a-data-source-in-weblogic.png) *Illustration 9: Creating a data source in WebLogic 10.3*

1.  Select *Services &rarr; Data Sources.* Click *New &rarr; Generic Data Source*.

2.  Give your data source a name, such as *Liferay Data Source*. The JNDI name should be `jdbc/LiferayPool`.

3.  Choose the type of database and click *Next*. From the screen shot, you can see that we have chosen MySQL. The database driver class should be chosen for you automatically.

4.  Click *Next* three times. You should be on the *Connection Properties* screen. Enter the database name, the host name, the port, the database user name, and the password. WebLogic will use this information to construct the appropriate JDBC URL to connect to your database. Click *Next*.

5.  WebLogic will now confirm with you the information that it gathered. For MySQL, some additional parameters need to be added to the URL. Modify the JDBC URL so that it has the proper parameters. If you have been following the defaults we have been using so far, you would use *lportal, localhost, root,* and no password as the values. Click *Next*.

6.  Click *Test Configuration* to make sure WebLogic can connect to your database successfully. If it does, click *Finish*.

7.  You will be back to the list of data sources. Notice that your new data source has no value in the *Target* column. Click on your data source to edit it.

8.  Click the *Targets* tab and check off the server instance(s) to which you wish to deploy your data source. Then click *Save*.

##### Mail Configuration

![image](../../images/02-weblogic-10-3-mail-sessions.png) *Illustration 10: WebLogic: Mail Sessions*.

1.  Select *Mail Sessions* and create a new mail session which points to your mail server.

2.  Give it the name Liferay Mail and give it the JNDI name of `mail/MailSession` and click *Next*.

3.  Choose your server and then click *Finish*.

##### Deploy Liferay

![image](../../images/02-weblogic-10-3-deployments.png) *Illustration 11: WebLogic: Deployments*.

1.  Create a `portal-ext.properties` file in the Liferay Home folder, which is one folder up from your domain's home folder. If you are using Liferay's built-in data source, add the database settings:

    jdbc.default.driverClassName=com.mysql.jdbc.Driver
    jdbc.default.url=jdbc:mysql://localhost/lportal?
    useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
    jdbc.default.username=root
    jdbc.default.password=

If you are using WebLogic's data source, add the JNDI name instead:

    jdbc.default.jndi.name=jdbc/LiferayPool

2. If you are using Liferay's built-in mail session, go to *Control Panel &rarr; Server Administration &rarr; Mail* and enter your settings for your mail session settings.

If you are using WebLogic's mail session, add the JNDI name instead:

	mail.session.jndi.name=mail/MailSession

Save and close the file.

3.  Add the following to `server.xml`

    <library-ref>
        <library-name>jsf</library-name>
        <specification-version>1.2</specification-version>
        <implementation-version>1.2</implementation-version>
        <exact-match>false</exact-match>
    </library-ref>

4.  Select *Deployments* and click the  *Install* button. Upload `jsf-1.2.war` from WebLogic's common files directory, and select *Install this deployment as a library.*

5.  After installing the JSF libraries, go back to deployments and select the Liferay `.war` file from the file system or click the *Upload Your File(s)* link to upload it, and then click *Next*.

6.  Select *Install this deployment as an application *and click *Next*.

7.  If the default name is appropriate for your installation, keep it. Otherwise, give it a name of your choosing and click *Next*.

8.  Click *Finish*. After the deployment finishes, click *Save*.

![image](../../images/02-tip.png) **Tip:** After Liferay completes installing, you may see an error initializing the Web Proxy portlet. Because the XSL parser configured by default within WebLogic cannot compile a style sheet in this portlet, Liferay disables it by default. To re-enable this portlet, extract `xalan.jar` and `serializer.jar` from the Liferay `.war` archive and copy them to your JDK's endorsed folder for libraries. If you are using JRockit, you may find this folder in `[Bea Home]/jrockit_160_05/jre/lib/ext`.

#### WebSphere 6.1

![image](../../images/02-tip.png) **Tip:** Throughout this installation and configuration process, WebSphere will prompt you to Click Save to apply changes to Master Configuration. Do so intermittently to save your changes.

**Liferay Home** is in a folder called `liferay` in the home folder of the user ID that is running WebSphere.

##### Installation

1.  Download the Liferay Portal `.war` file.

2.  Download and extract these Liferay `.jar`s to `websphere/appserver/lib/ext`.

-   Dependency libraries (Liferay Portal Dependencies)

-   Your database JDBC driver `.jar`

-   Currently you also need to copy `portlet.jar` from the Liferay Dependencies archive into `WebSphere/AppServer/java/jre/lib/ext`, as WebSphere already contains older versions of the portlet .jar which must be overridden at the highest level of the class path. This issue may be fixed in future releases; check the Liferay Wiki for updates to this issue.

##### Database Configuration

1.  Start WebSphere.

2.  Open Administrative Console and log in.

3.  Click *Resources*, click *JDBC Providers*.

4.  Click *New*.

5.  For name, enter the name of the JDBC provider (e.g. *MySQL JDBC Provider*).

6.  For Implementation class name, enter the implementation class for your database driver's connection pool data source For MySQL, enter:

    com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource

7.  Click *Next*.

8.  Clear any text in class path. You already copied the necessary `.jar`s to a location on the server's class path.

9.  Click *Next*.

10. Click *Finish*.

11. Click *Data Sources* under *Additional Properties*.

12. Click *New*.

13. Enter a name: *liferaydatabasesource*.

14. Enter JNDI: `jdbc/LiferayPool`.

15. Everything else should stay at the default values.

16. Click *Next*.

17. Under *Additional Properties*, click *Custom Properties*.

18. Click *New*.

19. Create three custom properties by entering *Name*, *Value* and clicking *OK* for each row in the following table.

**Name**

**Value**

1. user

root

2. serverName

localhost

3. databaseName

lportal

 20. Click *Data Sources &rarr; Test Connection* to test.

##### Mail Configuration

1.  Click *Resources &rarr; Mail Providers*.

2.  Click *Built-in Mail Provider*.

3.  Click *Mail Sessions*.

4.  Click *New*.

5.  Click *OK*.

6.  Click *Security*.

7.  Click *Secure administration, applications, and infrastructure*.

8.  Select *Enable application security*.

9.  Deselect *Use Java 2 security to restrict application access to local resources*.

##### Install Liferay

1.  Click *Applications &rarr; Install new applications*.

2.  Browse for `liferay-portal-x.x.x.war`.

![image](../../02-installing-the-liferay-war-file-on-windows.png) *Illustration 8: Installing the Liferay .war file on WebSphere 6.1*

3.  Enter context root */*.

4.  Click *Next*. For Steps 1 to 3, click *Next* to apply defaults.

5.  Choose the *Mail Session* and *Data Source*, and then click *Next*.

6.  Specify the virtual host upon which you want Liferay to run.

7.  At the Summary Screen, click *Finish*.

8.  Wait for the installation process to complete.

9.  Save this configuration to master configuration by clicking on *System administration* and *Save Changes to Master Repository*.

10. Create a `portal-ext.properties` file in the Liferay Home folder. For WebSphere, this is a folder called `liferay` in the home folder of the user that is running WebSphere. If you are using Liferay's built-in data source, add the database settings:

##### Start Liferay Portal

1.  Applications.

2.  Click *Enterprise Applications*.

![image](../../images/02-starting-liferay-on-websphere.png) *Illustration 9: Starting Liferay on WebSphere 6.1*

3.  Uninstall *DefaultApplication*, *PlantsByWebSphere* and *SamplesGallery*.

4.  Select `liferay-portal.war`, click *Start.*

5.  Open up the browser and go to `http://localhost:9080`. The default Liferay home page will be displayed.

#### WebSphere 7.0

**Liferay Home** is in a folder called `liferay` in the home folder of the user ID that is running WebSphere.

1.  Download the Liferay Portal WAR file.

2.  Download and extract these Liferay jars to `websphere/appserver/lib/ext`.

-   Dependency libraries (Liferay Portal Dependencies)

-   JDBC Driver for your database

##### Database Configuration

If you want WebSphere to manage the database connections, follow the instructions below.

![image](../../images/02-websphere-jdbc-providers.png) *Illustration 10: WebSphere 7.0 JDBC Providers*

1.  Start WebSphere.

2.  Open the Administrative Console and log in.

3.  Click *Resources &rarr; JDBC Providers*.

4.  Click *New*.

5.  For name, enter the name of JDBC provider (e.g. *MySQL JDBC Provider*).

6.  For Implementation class name, enter:

    com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource

7.  Click *Next*.

8.  Clear any text in class path. You already copied the necessary `.jar`s to a location on the server's class path.

9.  Click *Next*.

10. Click *Finish*.

11. Click *Data Sources* under *Additional Properties*.

12. Click *New*.

13. Enter a name: *liferaydatabasesource*.

14. Enter JNDI: `jdbc/LiferayPool`.

15. Everything else should stay at the default values. Save the data source.

16. When finished, go back into the data source and click *Custom Properties*, and then click the *Show Filter Function* button. This is the second from last of the small icons under the *New* and *Delete* buttons.

17. Type *user* into the search terms and click *Go*.

![image](../../images/02-modifying-data-source-properties-in-websphere.png) *Illustration 11: Modifying data source properties in WebSphere 7*

18. Select the user property and give it the value of the user name to your database. Click *OK* and save to master configuration.

19. Do another filter search for the url property. Give it a value that points to your database. For example, the MySQL URL would be: `jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false`. Click *OK* and save to master configuration.

20. Do another filter search for the password property. Enter the password for the user ID you added earlier as the value for this property. Click *OK* and save to master configuration.

21. Go back to the data source page by clicking it in the breadcrumb trail. Click the *Test Connection* button. It should connect successfully.

##### Mail Configuration

1.  Click *Resources &rarr; Mail &rarr; Mail Providers*.

2.  Click the Built-In Mail Provider for your node and server.

3.  Click *Mail Sessions*, and then click the *New* button.

4.  Give it a name of *liferaymail* and a JNDI name of `mail/MailSession`. Click *OK* and save to master configuration.

5.  Click *Security &rarr; Global Security* and deselect *Use Java 2 security to restrict application access to local resources* if it is selected. Click *Apply*.

##### Install Liferay

1.  Click *Applications &rarr; New Application &rarr; New Enterprise Application*.

2.  Browse to the Liferay `.war` file and click *Next*.

3.  Leave *Fast Path* selected and click *Next*, and then click *Next* again.

4.  Make sure your server is selected and click *Next*.

5.  Keep the context root set to / and click *Next*.

6.  Click *Finish*. When Liferay has installed, click *Save to Master Configuration*.

##### Start Liferay

1.  Create a `portal-ext.properties` file in the Liferay Home folder. For WebSphere, this is a folder called `liferay` in the home folder of the user that is running WebSphere. If you are using Liferay's built-in data source, add the database settings:

    jdbc.default.driverClassName=com.mysql.jdbc.Driver
    jdbc.default.url=jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
    jdbc.default.username=root
    jdbc.default.password=root

2.  If you are using WebSphere's data source per the instructions above,     add the JNDI name instead:

    jdbc.default.jndi.name=jdbc/LiferayPool

3.  Save and close the file.

4.  Click *Application Types &rarr; WebSphere Enterprise Application*.

5.  Uninstall the default application.

6.  Select the Liferay application and click *Start*.

### Making Liferay Coexist with Other Java EE Applications

Liferay Portal by default is configured to sit at the root (i.e., `/`) of your application server. Dedicating your application server to running only Liferay Portal is a good practice, allowing for separation between your portal environment and your web application environment. This is generally a best practice for portals, which by definition are application development platforms in and of themselves. For that reason, your instance of Liferay is likely to be hosting many applications, and even integrating several of them together on a single page. For this reason, you are going to want to make sure your portal environment has all the resources it needs to do this, and configuring it so that it is the sole consumer of any other `.war` files that get deployed to the application server helps to make sure that your system performs optimally.

If, however, you want Liferay to share space on an application server with other applications, there is no reason why you cannot do that. In this instance, you may not want to make Liferay the default application in the root context of the server.

There are two steps to modifying this behavior:

1.  Deploy Liferay in a context other than root (for example `/portal`).

2.  Modify the `portal-ext.properties` file to tell Liferay the context to which it has been deployed.

To change the file, open it in a text editor. Place the `portal.ctx` property at the top of the file:

    portal.ctx=/

This default setting defines Liferay Portal as the application that sits at the root context. If you change it to something else, say */portal*, for example, you can then deploy Liferay in that context and it will live there instead of at the root context.

A full discussion of the `portal-ext.properties` file appears in Chapter 6.

**Note for WebLogic Users:** WebLogic also requires that you modify the `weblogic.xml` file which is included with Liferay. In this file are tags for the context root:

    <context-root>/</context-root>

Change this so that it matches the path you set in your `portal-ext.properties` file. You will have to modify the `weblogic.xml` file inside the Liferay `.war` itself. Extract the file from the `.war` file, modify it, and then put it back in the `.war` file. Then deploy the modified Liferay `.war` file to the server in the proper context.

## Summary

This chapter is a guide to everything about installing Liferay. Whether you choose a Liferay bundle or an existing application server, Liferay Portal integrates seamlessly with your enterprise Java environment. It is supported on more application servers than any other portal platform, allowing you to preserve your investment in your application server of choice, or giving you the freedom to move to a different application server platform. Liferay is committed to providing you this freedom: we have 100 test servers certifying our builds with roughly 10,000 tests per version of Liferay Portal. Each of those tests must be run on all of our different supported combinations of application servers, databases, and operating systems. So you can be sure that we are committed to supporting you on your environment of choice. Liferay Portal won't get in your way, and you can feel safe knowing that you have the freedom to use the software platform that is best for your organization, and Liferay Portal will run and perform well on it.
