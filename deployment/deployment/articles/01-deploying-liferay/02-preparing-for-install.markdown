# Preparing for Install [](id=preparing-for-install)

@product@ doesn't require much to deploy. You need a Java Development Kit (JDK),
database, and mail server. Several configuration topics (e.g.,
[search engine integration]((/deployment/deployment/-/knowledge_base/7-2/installing-a-search-engine),
[document repository configuration](/deployment/deployment/-/knowledge_base/7-2/document-repository-configuration),
[security management](/deployment/deployment/-/knowledge_base/7-2/securing-liferay),
[clustering](/deployment/deployment/-/knowledge_base/7-2/liferay-clustering),
and more) can be addressed *after* deploying @product@. Start with familiarizing
yourself with the pre-installation topics here. 

- [JDK Requirements](#jdk-requirements)
- [Preparing a Database](#preparing-a-database) 
- [Configuring a Mail Session](#configuring-a-mail-session)

+$$$

**Note:** If you are installing @product@ to multiple machines (e.g., in a 
[cluster](/deployment/deployment/-/knowledge_base/7-2/liferay-clustering))
or prefer centralizing configuration in a file, using portal properties in a
[`[LIFERAY_HOME]/portal-ext.properties` file](/deployment/reference/-/knowledge_base/7-2/portal-properties)
is the recommended way to configure. The install preparation topics here and the
configuration topics throughout this guide demonstrate using applicable portal
properties. 

$$$

+$$$

**Note:** `LIFERAY_HOME` is the location from which @product@ launches 
applications, applies configurations, loads JAR files, and generates logs.
Liferay Home is customizable and can differ between application servers. The
[Liferay Home reference](/deployment/reference/-/knowledge_base/7-2/liferay-home)
describes its folder structure. 

$$$

Start preparing for @product@ install, by installing a supported Java
Development Kit. 

## JDK Requirements [](id=jdk-requirements)

@product@ deployment requires a JDK. The 
[Support page](https://help.liferay.com/hc/categories/360000894391-Product-Support)
lists the supported JDKs from various vendors. You must use one of these JDK
versions:

- JDK 8
- JDK 11

JDK 11 is backwards compatible with JDK 8 applications. Applications
and customizations developed on JDK 8 run on JDK 8 or JDK 11 runtimes. This makes JDK 8 best for developing on @product-ver@. 

## Preparing a Database [](id=preparing-a-database)

The recommended way to set up your @product@ database is also the simplest.
@product@ takes care of just about everything. It requires these two simple
steps:

1.  Create a blank database encoded with the character set UTF-8. @product@ is a
    multilingual application and needs UTF-8 encoding to display all of its
    supported character sets.

    +$$$

    **Note:** If you plan to migrate from one database vendor to another,
    [configure the database to use the default query result order you expect for entities @product@ lists](/develop/tutorials/-/knowledge_base/7-2/sort-order-changed-with-a-different-database). 

    $$$

2.  Create a database user for accessing this database. Grant this database user
    all rights, including the rights to create and drop tables, to the blank
    @product@ database.

@product@ uses this database user's credentials to connect to the database
either directly or through its application server. During @product@'s initial
startup, it creates the database tables it needs. It does this automatically,
complete with indexes.

This is the recommended way to set up @product@. It enables @product@ to
maintain its database automatically during upgrades or when various @product@
plugins that create database tables of their own are installed. This method is
by far the best way to set up your database. 

+$$$

**Warning:** If you're using an Oracle database, use the `ojdbc8.jar` driver 
library with at least Oracle 12.2.0.1.0 JDBC 4.2 versioning because
[data truncation issues](https://issues.liferay.com/browse/LPS-79229)
have been detected reading data from CLOB columns.

$$$

You can connect @product@ with your database using @product@'s built-in data
source (recommended) or using a data source you create on your app server. 

### Using the Built-in Data Source [](id=using-the-built-in-data-source)

You can configure the built-in data source from the
[Basic Configuration page](/deployment/deployment/-/knowledge_base/7-2/installing-liferay#basic-configuration-page)
(available when @product@ starts up the first time) or by specifying it using 
portal properties. 

Here's how set it using portal properties:

1.  Create a 
    [`portal-ext.properties` file](/deployment/reference/-/knowledge_base/7-2/portal-properties)
    if you haven't created one already. 

2.  Copy a set of `jdbc.*` properties from one of the
    [JDBC templates](/deployment/reference/-/knowledge_base/7-2/jdbc-templates)
    into your `portal-ext.properties` file.

3.  Modify the `jdbc.*` property values to specify your database and database 
    user credentials. 

3.  Put the `portal-ext.properties` file into your
    [LIFERAY_HOME](/deployment/reference/-/knowledge_base/7-2/liferay-home)
    once you've established it based on your installation. 

@product@ connects to the data source on startup. 

As an alternative to the build-in data source, you can use  a data source on
your application server. 

### Using a Data Source on Your Application Server [](id=using-a-data-source-on-your-application-server)

Although using @product@'s built-in data source is recommended, you can use a data source on your application server. Here are the steps:

1.  Create your data source based on the instructions in the *Installing 
    @product@ on \[Application Server\]* article (for your application server)
    and your application server's documentation.

2.  Create a
    [`portal-ext.properties` file](/deployment/reference/-/knowledge_base/7-2/portal-properties),
    if you haven't created one already. 

3.  Add the `jdbc.default.jndi.name` property set to the data source's JNDI 
    name. Here's an example:

        jdbc.default.jndi.name=jdbc/LiferayPool

4.  Put the `portal-ext.properties` file into your
    [LIFERAY_HOME](/deployment/reference/-/knowledge_base/7-2/liferay-home),
    once you've established your LIFERAY_HOME based on your installation. 

@product@ connects to your data source on startup.

Allowing the database user you're using to initialize the @product@ database to
continue with all database rights is recommended. If you're fine with that user
having the recommended permissions, skip the next section on limiting
database access and continue with
[Mail Session Management](#mail-session-management). 

### Limiting Database Access [](id=limiting-database-access)

+$$$

**Warning:** The instructions below are not ideal for @product@ installations
The following procedure is documented so that enterprises with more restrictive
standards can install @product@ with stricter (but sub-optimal) database
settings. If it's at all possible, allow the database user that initializes the
database to continue using the database with the same recommended permissions.
The start of this section
([Database Preparation](#database-prepartation)) 
describes the recommended procedure for initializing the @product@ database and
preserving that user's permissions for maintaining the @product@ database and
updating the database as plugin installations and plugin updates require. 

$$$ 

Even though it's recommended for @product@ to use the same database user to
create and maintain its database automatically, your organizations might insist
on revoking database initialization and maintenance permissions from that user
once the database is initialized. If permissions for Select, Insert, Update and
Delete operations are the only ones you will continue to allow for that user,
you must initialize and maintain the database manually (even though it's not
recommended). Here is the manual procedure: 

1.  Create a new, blank, database for @product@.

2.  Grant full rights for the @product@ database user to do anything to the 
    database. 

3.  Install @product@ and start it so that it automatically populates the
    database.

4.  Once the database has been populated with the @product@ tables, remove all
    permissions from that user except permissions to perform Select, Insert,
    Update and Delete operations. 

There are some caveats to running @product@ like this. Many plugins create new
tables when they're deployed. Additionally, @product@'s automatic database
upgrade function runs when @product@ is upgraded. If the @product@ database user
doesn't have adequate rights to create/modify/drop tables in the database, you
must grant those rights to that user before you deploy one of these plugins or
start upgrading @product@. Once the tables are created or the upgrade completes,
you can remove those rights until the next deploy or upgrade. Additionally, your
own developers might create plugins that need to create their own tables. These
are just like @product@ plugins that do the same thing, and they can only be
installed if @product@ can create database tables. Installing these plugins
requires granting the @product@ database user rights to create tables in the
database before you attempt to install the plugins. 

You've prepared your @product@ database for the @product@ installation. Next
consider how to manage your @product@ mail sessions. 

## Configuring a Mail Session [](id=configuring-a-mail-session)

@product@ uses a mail server to emails notifications using SMTP. You should
pre-configure a mail session before installing @product@ or configure one once
you've deployed @product@. You must provide the following information: 

-   Incoming POP Server and port
-   POP User Name
-   POP Password
-   Outgoing SMTP Server and port
-   SMTP User Name
-   SMTP Password
-   All JavaMail properties you want to use

Here are the two ways to configure your mail session:

-   [@product@'s built-in mail session (recommended)](#products-built-in-mail-session)

-   [A mail session on your application server](#application-server-mail-session)

Built-in mail session setup is easiest and explained first. 

### @product@'s Built-in Mail Session [](id=products-built-in-mail-session)

The built-in mail session setup can be done using either of these methods:

-   Control Panel

-   Portal properties

#### Built-in Mail Session in the Control Panel [](id=built-in-mail-session-in-the-control-panel)

After deploying @product@, you can configure the mail session from the Control Panel. 

1.  Sign in as the administrative user (the user you specified on the
    [Basic Configuration page](/deployment/deployment/-/knowledge_base/7-2/installing-liferay#basic-configuration)). 

2.  Navigate to *Control Panel &rarr; Configuration &rarr; Server Administration
    &rarr; Mail*.

3.  Fill out the form. You're asked for the following information: 

    **Incoming POP Server:** The hostname for a server running the Post Office
    Protocol. @product@ checks this mailbox for incoming messages, such as
    message board replies. 

    **Incoming Port:** The port on which the POP server is listening. 

    **Use a Secure Network Connection:** Use an encrypted connection when 
    connecting to the POP server. 

    **User Name:** The user ID @product@ should use to log into the POP server. 

    **Password:** The password @product@ should use to log into the POP server. 

    **Outgoing SMTP Server:** The hostname for a server running the Simple Mail
    Transfer Protocol. @product@ uses this server to send emails, such as 
    password change emails and other notifications. 

    **Outgoing Port:** The port on which the SMTP server is listening. 

    **Use a Secure Network Connection:** Use an encrypted connection when 
    connecting to the SMTP server. 

    **User Name:** The user ID @product@ should use to log into the SMTP server.

    **Password:** The password @product@ should use to log into the SMTP server.

    **Manually specify additional JavaMail properties to override the above
    configuration:** If there are additional properties you need to specify, 
    supply them here. 

4.  Click *Save*. 

@product@ connects to the mail session immediately.  

#### Built-in Mail Session Portal Properties [](id=built-in-mail-session-portal-properties)

If you prefer specifying your mail session offline or before deploying @product@, use portal properties. 

1.  Create a
    [`portal-ext.properties` file](/deployment/reference/-/knowledge_base/7-2/portal-properties),
    if you haven't already created one. 

2.  Copy these default property settings into your `portal-ext.properties` file:

        mail.session.mail=false
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
        mail.session.mail.transport.protocol=smtp.

3.  Replace the default mail session values with your own. 

4.  Put the `portal-ext.properties` file into your
    [LIFERAY_HOME](/deployment/reference/-/knowledge_base/7-2/liferay-home),
    once you've established it based on your installation. 

@product@ connects to the mail session on the next startup. 

### Application Server Mail Session [](id=application-server-mail-session)

You can manage a mail session for @product@ on your application server. Here's how:

1.  Create a mail session on your application server, following your application
    server documentation.  

2.  Point @product@ to that mail session using the Control Panel or 
    portal properties. Here are instructions for both:

    -   Configure the JNDI name in the *Mail* page at *Control Panel &rarr; 
        Configuration &rarr; Server Administration &rarr; Mail*. 
    -   Set a `mail.session.jndi.name` portal property in a
        `[LIFERAY_HOME]/portal-ext.properties` file. Here's an example property:

            mail.session.jndi.name=mail/MailSession

@product@ has many more configurable features; but they can wait
until *after* deployment. The
[Configuring @product@](/deployment/deployment/-/knowledge_base/7-2/configuring-liferay)
section explains them. 

You've prepared for deploying @product@. It's time to install @product@. 
