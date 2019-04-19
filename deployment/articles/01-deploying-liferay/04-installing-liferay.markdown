---
header-id: installing-product
---

# Installing @product@

[TOC levels=1-4]

Now that you've
[prepared for installing @product@](/docs/7-2/deploy/-/knowledge_base/deploy/preparing-for-install),
you can install and deploy it! This involves uncompressing the archive (the
7-Zip or gzip bundle file), possibly copying a JDBC driver to your application
server, and starting the application server. Lastly, @product@ provides a setup
wizard to configure portal essentials. 

| **Note:** Since bundles are the easiest way to complete an installation, the 
| installation steps here assume you're installing a @product@ bundle. If you 
| plan to install @product@ manually, please refer to the *Installing @product@ 
| on [Application Server]* article for the application server you're
| installing on. 

## Extracting a @product@ Bundle 

Extract your @product@ bundle to the appropriate location on your server.
This folder is the
[*Liferay Home*](/docs/7-2/deploy/-/knowledge_base/deploy/liferay-home). 

## Installing the JDBC Driver 

If you're using a supported open source database or if you're setting up
@product@ to use the embedded HSQL database for demo purposes, you can skip this
step. Otherwise, copy your database's JDBC driver `.jar` file to the folder your
application server documentation specifies. On Tomcat, for example, the driver
belongs in the `[Tomcat]/lib/ext` folder. 

## Running @product@ for the First Time 

Start your application server using the start script bundled with your
application server. For example, the Tomcat bundle provides the `startup.sh`
script in `$CATALINA_HOME/bin`.

| **Note:** @product@ writes log files to folder `[Liferay Home]/logs`. 

| **Important:** @product@ requires that the application server JVM use the GMT 
| time zone and UTF-8 file encoding. They're preset in your @product@ bundle. 

The first time @product@ starts, it creates all of its database tables. On
completing startup, it launches a web browser that displays the Basic
Configuration page. If for some reason your browser doesn't load the Basic
Configuration page automatically, open your browser and navigate to your app
server's address and port (for example, http://localhost:8080).

## Using the Setup Wizard 

The Basic Configuration page provides a convenient way to configure these
things:

-   Portal name and default locale
-   Administrator user
-   Database 

![Figure 1: Supply the information for your portal and your portal's default administrator user on the Basic Configuration page.](../../images/basic-configuration1.png)

### Portal 

Supply this basic portal information:

**Portal Name:** name the installation you're powering with @product@.

**Default Language:** choose your portal's default locale and click the *Change*
button. This immediately localizes your portal content, including the Basic
Configuration page. 

### Administrator User 

For the administrator, supply the following information:

**First Name:** the default administrator user's first name

**Last Name:** the default administrator user's last name

**Email:** the default administrator user's email address

### Database 

This section lets you connect to @product@'s built-in data source.

| **Important:** If you haven't created a database for @product@, create one 
| now following
| [database preparation instructions](/docs/7-2/deploy/-/knowledge_base/deploy/preparing-for-install#database-preparation)
| in the preceding article. 

HSQL is selected as the default database, but it's primarily for demonstration
or trial purposes. 

Click the *Change* link if you want to use @product@'s built-in data source and
configure it to use the
[database you created earlier](/docs/7-2/deploy/-/knowledge_base/deploy/preparing-for-install#database-preparation). 

The database configuration section also has an *Add Sample Data* checkbox for
adding sample data to your database. This data includes Users, Sites, and
Organizations for demo purposes. If you're installing @product@ on your own
machine to explore features, the sample data may be useful. If, however, you're
installing @product@ on a real server, start with a clean system by leaving this
checkbox unselected. 

| **Warning:** HSQL should not be used in production @product@ instances. 
| Configure @product@ to use a different database; specify that database via the
| Basic Configuration page here or using portal properties. See
| [Database Preparation](/docs/7-2/deploy/-/knowledge_base/deploy/preparing-for-install#database-preparation)
| for details.

Once you've filled out the Basic Configuration form, click *Finish
Configuration*. The setup wizard creates a
`[LIFERAY_HOME]/portal-setup-wizard.properties` file which stores the settings
that you entered. When you begin customizing your portal's configuration,
however, use a
[`portal-ext.properties` file](/docs/7-2/deploy/-/knowledge_base/deploy/portal-properties).
The
[Portal properties reference documentation](@platform-ref@/7.2-latest/propertiesdoc)
lists the default properties and describes all the properties you can set for
@product@. 

| **Tip:** The wizard is a helpful tool, especially if you're setting
| up @product@ for the first time. If you're a veteran and you already have your
| various properties set up, you can disable the setup wizard. If you disable 
| the setup wizard, you must configure everything manually from a portal
| properties file (e.g., `[LIFERAY_HOME]/portal-ext.properties`). To disable the
| setup wizard, set `setup.wizard.enabled=false` in your portal properties file.
| Note that property values in `portal-setup-wizard.properties` (the file the
| setup wizards creates in Liferay Home) override property values in
| `portal-ext.properties`.

On finishing basic configuration, @product@ prompts you to restart your server.
When you restart your application server, @product@ initializes the database you
specified. 

Now that @product@ is up and running, you can continue configuring it as
desired. Here are some suggestions:

1.  [Configure your mail session](/docs/7-2/deploy/-/knowledge_base/deploy/configuring-mail),
    if you haven't already configured it. 

2.  Install the Marketplace plugin, if it isn't already installed. If your 
    machine has  restricted access to the public network or if you restricted
    the @product@ database user's permissions after initializing the database
    (not recommended), you can still set up Marketplace by following the
    [Marketplace setup instructions](/docs/7-2/deploy/-/knowledge_base/deploy/setting-up-marketplace). 

3.  Read the
    [Configuring @product@](/docs/7-2/deploy/-/knowledge_base/deploy/configuring-product)
    articles for guidance in configuring @product@'s default time zone, locales,
    logging, search engine, document repository, and more.

You're on your way to providing your organization with terrific experiences on
@product@. 
