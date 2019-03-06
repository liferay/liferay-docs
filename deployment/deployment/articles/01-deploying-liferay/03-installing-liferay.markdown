# Installing @product@ [](id=installing-liferay)

Now that you've
[prepared for installing @product@](/deployment/deployment/-/knowledge_base/7-2/preparing-for-install),
you can install and deploy it! This involves uncompressing the archive (the
7-Zip or gzip bundle file), possibly copying a JDBC driver to your application
server, and starting the application server. Lastly, @product@ provides a setup
wizard to configure portal essentials. Here are sections that demonstrate these
steps:

-   [Extracting a @product@ Bundle](#extracting-a-liferay-bundle)

-   [Installing the JDBC Driver](#installing-the-jdbc-driver)

-   [Running @product@ for the first time](#running-liferay-for-the-first-time)

-   [Using the Setup Wizard](#using-the-setup-wizard)

+$$$

**Note:** Since bundles are the easiest way to complete an installation, the installation
steps here assume you're installing a @product@ bundle. If you plan to install
@product@ manually, please refer to these articles:

-   *[Installing @product@ manually](/deployment/deployment/-/knowledge_base/7-2/installing-liferay-manually)*: Explains the common manual installation
    steps.

-   *Installing @product@ on \[Application Server\]*: Demonstrates installing on
    the particular application server. 

$$$

## Extracting a @product@ Bundle [](id=extracting-a-liferay-bundle)

Extract your @product@ bundle to the folder you want @product@ to reside in.
This folder is the
[*Liferay Home*](/deployment/reference/-/knowledge_base/7-2/liferay-home). 

## Installing the JDBC Driver [](id=installing-the-jdbc-driver)

If you're using a supported open source database or if you're setting up
@product@ to use the embedded HSQL database for demo purposes, you can skip this
step. Otherwise, copy your database's JDBC driver `.jar` file to the folder your
application server documentation specifies. On Tomcat, for example, the driver
belongs in the `[Tomcat]/lib/ext` folder. 

## Running @product@ for the First Time [](id=running-product-for-the-first-time)

Start your application server using the `startup.sh` or `startup.bat` script
@product@ bundled with your application server's startup scripts. For example,
the Tomcat bundle provides the `startup.sh` script in `$CATALINA_HOME/bin`.

+$$$

**Note:** @product@ writes log files to folder `[Liferay Home]/logs`. 

$$$

+$$$

**Important:** @product@ requires that the application server JVM use the GMT 
time zone and UTF-8 file encoding. They're preset in your @product@ bundle. 

$$$

The first time @product@ starts, it creates all of its database tables. On
completing startup, it automatically launches a web browser that displays the
Basic Configuration page. If for some reason your browser doesn't load the Basic
Configuration page automatically, open your browser and navigate to your app
server's address and port (for example, http://localhost:8080).

## Using the Setup Wizard [](id=using-the-setup-wizard)

The Basic Configuration page opens on @product@'s first startup. It provides a convenient way to configure these things for your portal:

-   Portal name and default locale
-   Administrator user
-   Database 

![Figure 1: Supply the information for your portal and your portal's default administrator user on the Basic Configuration page.](../../images/basic-configuration1.png)

### Portal [](id=portal)

Supply this basic portal information:

**Portal Name:** name the portal you're powering with @product@.

**Default Language:** choose your portal's default locale and click the *Change*
button. This immediately localizes your portal content, including the Basic
Configuration page. 

**Add Sample Data** Selecting this checkbox, adds sample data to your 
database. This data includes users, sites, and organizations for demo purposes.
If you're installing @product@ on your own machine to explore features, the
sample data may be useful. If, however, you're installing @product@ on a real
server, start with a clean system by unselecting this checkbox. 

### Administrator User [](id=administrator-user)

For the administrator, supply the following information:

**First Name:** the default administrator user's first name

**Last Name:** the default administrator user's last name

**Email:** the default administrator user's email address

### Database [](id=database)

This section lets you connect to @product@'s built-in data source.

+$$$

**Important:** If you haven't created a database for @product@, create one now 
following
[database preparation instructions](/deployment/deployment/-/knowledge_base/7-2/preparing-for-install#database-preparation)
in the preceding article. 

$$$

HSQL is selected as the default database, but it's primarily for demonstration
or trial purposes. 

Click the *Change* link, if you want to use @product@'s built-in data source and
configure it to use the
[database you created earlier](/deployment/deployment/-/knowledge_base/7-2/preparing-for-install#database-preparation). 

+$$$

**Warning:** HSQL should not be used in production @product@ instances. 
Configure @product@ to use a different database; specify that database via the
Basic Configuration page here or using portal properties. See
[Database Preparation](/deployment/deployment/-/knowledge_base/7-2/preparing-for-install#database-preparation)
for details.  

$$$

Once you've filled out the Basic Configuration form, click *Finish
Configuration*. The setup wizard creates a
`[LIFERAY_HOME]/portal-setup-wizard.properties` file which stores the settings
that you entered. When you begin customizing your portal's configuration,
however, use a
[`portal-ext.properties` file](/deployment/reference/-/knowledge_base/7-2/portal-properties).
The
[Portal properties reference documentation](http://docs.liferay.com/portal/7.0/propertiesdoc)
lists the default properties and describes all the properties you can set for
@product@. 

+$$$

**Tip:** The wizard is an extremely helpful tool, especially if you're setting
up @product@ for the first time. If you're a veteran and you already have your
various properties set up, you can disable the setup wizard. If you disable the
setup wizard, you must configure everything manually from a portal properties
file (e.g., `[LIFERAY_HOME]/portal-ext.properties`). To disable the setup
wizard, set `setup.wizard.enabled=false` in your portal properties file. Note
that property values in `portal-setup-wizard.properties` (the file the setup
wizards creates in Liferay Home) override property values in
`portal-ext.properties`.

$$$

On finishing basic configuration, @product@ prompts you to restart your server.
When you restart your application server, @product@ initializes the database you
specified. 

Now that @product@ is up and running, you can continue configuring it as
desired. Here are some suggestions:

1.  [Configure your mail session](/deployment/deployment/-/knowledge_base/7-2/configuring-a-mail-session),
    if you haven't already configured it. 

2.  Install the Marketplace plugin, if it isn't already installed. If your 
    machine has  restricted access to the public network or if you restricted
    the @product@ database user's permissions after initializing the database
    (not recommended), you can still set up Marketplace by following the
    [Marketplace setup instructions](/deployment/deployment/-/knowledge_base/7-2/setting-up-marketplace). 

3.  Read the
    [Configuring @product@](/deployment/deployment/-/knowledge_base/7-2/configuring-liferay)
    articles for guidance in configuring @product@'s default time zone, locales,
    logging, search engine, document repository, and more.

You're on your way to providing your organization with terrific experiences on
@product@. 
