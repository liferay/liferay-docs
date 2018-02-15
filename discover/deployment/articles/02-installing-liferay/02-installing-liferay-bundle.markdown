# Installing @product@ [](id=installing-product)

Now that you've performed the steps needed to prepare for your installation,
you're ready to install @product@! Since bundles are the easiest way to complete
an installation, all the installation steps below assume you're installing a
@product@ bundle. If you plan to install @product@ manually, please refer to the
article for your app server of choice, and then come back here to complete the
configuration steps. 

Now you're ready. You've created a blank database for @product@ and have
gathered the credentials you need for your mail server. The next step is to
install @product@. 

## Liferay Home [](id=liferay-home)

@product@ bundles contain the same folder structure regardless of application
server. The top-level folder is named for the @product@ release. This folder is
called *Liferay Home*. This folder is usually the  application server's parent
folder. This is why @product@ bundles place the application server inside the
bundle's root folder. On a manual installation, the location of this folder
varies by application server. In a bundle, it's part of the bundle. If you're
doing a manual installation, please refer to the article covering that app
server for its location. 

Liferay Home has folders for various purposes:

-   **[Liferay Home]**
    -   **[Application Server]**: The name of this folder varies depending on 
        the bundle you're using. This folder contains the application server in
        which @product@ has been installed.
    -   `data`: Stores an embedded HSQL database, @product@'s file repository,
        and @product@'s search indexes. @product@ is initially configured to use
        the embedded HSQL database but the HSQL database is primarily intended
        for demonstration and trial purposes.
        [Portal property `jdbc.default.url`](@platform-ref@/7.0/propertiesdoc/portal.properties.html#JDBC)
        sets the Hypersonic embedded HSQL database location.
    -   `deploy`: To auto-deploy @product@ plugins, copy them to this folder. 
        Legacy style `.war` files, @product-ver@ style `.jar` files, and `.lpkg`
        files from Liferay Marketplace are supported.
        [Portal property `auto.deploy.deploy.dir`](@platform-ref@/7.0/propertiesdoc/portal.properties.html#Auto%20Deploy)
        sets the auto-deploy location.
    -   `license`: @product@'s copyright and version files are here.
    -   `logs`: This folder contains @product@'s log files. The information in
        @product@'s log files can be quite valuable for system administrators,
        especially when trying to diagnose a problem. `portal-impl.jar`'s
        `portal-impl/src/META-INF/portal-log4j.xml` file sets the location for
        the log files. To override the log file location, you must
        [use an `ext-impl/src/META-INF/portal-log4j-ext.xml` file in an Ext plugin](/develop/tutorials/-/knowledge_base/7-0/advanced-customization-with-ext-plugins#using-advanced-configuration-files).
    -   `osgi`: All the JAR files and a few configuration files for @product@'s 
        OSGi runtime belong in this folder.
        [Portal property `module.framework.base.dir`](@platform-ref@/7.0/propertiesdoc/portal.properties.html#Module%20Framework)
        sets the OSGi folder location. Here are its subfolders:

        -   `configs`: Component configuration files go here
        -   `core`: @product@'s core modules
        -   `marketplace`: Marketplace applications and application suites
        -   `modules`: Modules you've deployed
        -   `portal`: @product@'s non-core modules
        -   `state`: Contains OSGi internal state files for such things as OSGi 
            bundle installation, bundle storage, and more
        -   `target-platform`: Target platform index
        -   `test`: Modules that support test integration
        -   `war`: WAR plugins you've deployed
    -   `patching-tool`: (Liferay Digital Enterprise 7.1 only) This folder 
        contains patches for @product@ and files for installing the patches.
    -   `tools`: For @product@ upgrade and target platform indexer.
    -   `work`: Module Jasper work files.

If @product@ is unable to create the resources it needs in the Liferay Home
folder or if it finds itself running on certain application servers, it creates
a folder called `liferay` in the home folder of the operating system user that
is running @product@. In this case, the `liferay` folder becomes Liferay Home.
For example, if the operating system user's name is jbloggs, the Liferay Home
folder is `/home/jbloggs/liferay` or `C:\Users\jbloggs\liferay`.

## Extracting a @product@ Bundle [](id=extracting-a-liferay-bundle)

Getting a @product@ bundle up and running involves uncompressing the archive,
possibly copying a JDBC driver, and then starting the application server. Let's
use the @product@ Tomcat bundle as an example.

1.  Unzip your @product@ bundle.

2.  If you're using a supported open source database or if you're setting up
    @product@ for demo purposes, you can skip this step. Otherwise, copy your
    database's JDBC driver `.jar` file to `[Tomcat]/lib/ext`. 

That's it! You've extracted @product@, and it's ready for use. This is much
easier than doing a manual installation on an app server. If, however, that's
what you need to do, please at this point click the link on the left and go
through the installation procedure for your app server of choice. When you're
finished with the installation (and before you've started @product@ for the
first time), come back to this spot, because you must hook it up to your
database. 

## Connecting @product@ to Your Database [](id=connecting-product-to-your-database)

You can connect to your database with JNDI on your app server or the data source
shipped with @product@ (recommended). Refer to the article on your app server
for instructions on using JNDI. For the internal data source, you can configure
it by specifying the configuration in a `portal-ext.properties` file or the
[Setup Wizard](/discover/deployment/-/knowledge_base/7-1/installing-product#using-liferays-setup-wizard):

1.  Create a `portal-ext.properties` file in your Liferay Home folder.

2.  Copy a relevant example data source configuration from
    one of the
    [data source configuration templates](/discover/reference/-/knowledge_base/7-0/database-templates)
    or
    [portal properties reference's JDBC section](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#JDBC)
    and paste it into the `portal-ext.properties` file.

3.  Customize the configuration with the proper host name and user and password
    credentials for your database, and save the file.

You're ready to start @product@. 

## Running @product@ for the First Time [](id=running-product-for-the-first-time)

Next, start your app server, or start the @product@ app in your app server. 
@product@ writes log files to folder `[Liferay Home]/logs`. 

The first time @product@ starts, it creates all of its database tables. Once it
has successfully started, it automatically launches a web browser that displays
the Basic Configuration page. If for some reason your browser doesn't load the
Basic Configuration page, launch it and navigate to your app server's address
and port (for example, http://localhost:8080). 

## Using @product@'s Setup Wizard [](id=using-liferays-setup-wizard)

The Setup Wizard runs when you start @product@ for the first time. The title of
the setup wizard page is Basic Configuration. This page provides a convenient
way to make an initial configuration.

There are two sections of the wizard: the portal and the administrator.  For the
portal, you need to supply the following information:

**Portal Name:** the name of the portal you're powering with @product@.

**Default Language:** choose the default locale of your portal.

For the administrator, you need to supply the following information:

**First Name:** the first name of the default administrator user

**Last Name:** the last name of the default administrator user

**Email:** the email address of the default administrator user

![Figure 1: Supply the information for your portal and your portal's default administrator user on the Basic Configuration page.](../../images/basic-configuration1.png)

The Basic Configuration page also includes a checkbox labeled *Add Sample Data*.
If you check this box, sample data is added to your database. This data includes
users, sites, and organizations. The sample data is for demo purposes. If you're
installing @product@ on your own machine to explore its features, the sample
data may be useful. If, however, you're installing @product@ on a real server,
start with a clean system.

Once you've filled out the form, click *Finish Configuration*. The setup wizard
creates a `portal-setup-wizard.properties` file which stores the settings that
you entered. When you begin customizing your portal's configuration, however,
use the `portal-ext.properties` file you created earlier. All the
possible properties that can be placed in this file are documented in 
[our reference documentation](http://docs.liferay.com/portal/7.0/propertiesdoc).

+$$$

**Tip:** The wizard is an extremely helpful tool, especially if you're setting
up @product@ for the first time. If you're a veteran and you already have
your various properties set up, you can disable the setup wizard. If you disable
the setup wizard, you must configure everything manually from the 
`portal-ext.properties` file. To disable the setup wizard, enter
`setup.wizard.enabled=false` in your `portal-ext.properties` file. Note that
property values in `portal-setup-wizard.properties` (the file created in Liferay
Home by the setup wizard) override property values in `portal-ext.properties`.

$$$

After you've entered the information requested by the Basic Configuration page,
the home page appears. You should set up your mail configuration next. 

## Configuring Mail [](id=configuring-mail)

Log in as the administrative user you created in the setup wizard. Click the
menu icon and then go to Control Panel &rarr; Server Administration &rarr; Mail,
and have your mail credentials ready. 

Fill out the form. You're asked for the following information: 

**Incoming POP Server:** The hostname for a server running the Post Office
Protocol. @product@ checks this mailbox for incoming messages, such as message
board replies. 

**Incoming Port:** The port on which the POP server is listening. 

**Use a Secure Network Connection:** Use an encrypted connection when connecting
to the POP server. 

**User Name:** The user ID @product@ should use to log into the POP server. 

**Password:** The password @product@ should use to log into the POP server. 

**Outgoing SMTP Server:** The hostname for a server running the Simple Mail
Transfer Protocol. @product@ uses this server to send emails, such as password
change emails and other notifications. 

**Outgoing Port:** The port on which the SMTP server is listening. 

**Use a Secure Network Connection:** Use an encrypted connection when connecting
to the SMTP server. 

**User Name:** The user ID @product@ should use to log into the SMTP server. 

**Password:** The password @product@ should use to log into the SMTP server. 

**Manually specify additional JavaMail properties to override the above
configuration:** If there are additional properties you need to specify, supply
them here. 

When you're finished setting up your mail configuration, click *Save*. 

Your next step is to convert the search implementation from its default demo
mode into a production-ready mode. 
