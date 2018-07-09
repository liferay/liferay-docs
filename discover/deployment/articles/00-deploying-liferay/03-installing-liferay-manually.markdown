# Installing @product@ Manually [](id=installing-liferay-manually)

The easiest way to install @product@ is to
[use a bundle](/discover/deployment/-/knowledge_base/7-1/installing-liferay).
However, this is not always possible. Some organizations have an existing
infrastructure into which @product@ must be installed. Other organizations have
standardized on a particular application server. @product@ works well with many
leading application servers. Before you get started, note that there are two distinct
approaches to managing @product@'s data source and mail session. All these
topics are covered:

- [Using data sources](#using-data-sources)
- [Using mail sessions](#using-mail-sessions)
- [Manual configuration for data sources and mail sessions](#manual-configuration)

Start with data sources. 

## Using Data Sources [](id=using-data-sources)

@product@ provides two ways to configure your data source:

- Use @product@'s built-in data source
- Use your application's server's JNDI data source

We recommend the built-in data source. @product@'s data source is configured
by properties set in a properties file. By default, you can enter database
connection information on the 
[Basic Configuration page](/discover/deployment/-/knowledge_base/7-1/installing-liferay#using-liferays-setup-wizard)
that appears when @product@ starts for the first time. The Setup Wizard stores
the information you entered in a configuration file called
`portal-setup-wizard.properties` in your 
[Liferay Home](/discover/deployment/-/knowledge_base/7-1/installing-liferay#liferay-home)
folder. The built-in data source uses this information to connect to the
database.

Although using the built-in data source is recommended, that's not the only
option. You might prefer to use the data source your application server
provides. In this case, a JNDI lookup provides a handle to the data
source and the application server manages the connection pools. 

To configure your application server's data source, you must create your own
configuration file and skip the setup wizard. Since you'd be creating this file
*after* the wizard anyway, this isn't a big deal. The 
[Manual Configuration](#manual-configuration) section below demonstrates configuring
a JNDI data source.

Since mail sessions are configured similarly to data sources, they're next.

## Using Mail Sessions [](id=using-mail-sessions)

@product@ uses SMTP to send mail. As with databases, you have two ways to
configure your mail server:

- Use @product@'s built-in mail session
- Use your application server's mail session

Using the built-in mail session is recommended. After you've started @product@,
you can configure a mail server through the Control Panel. The default
configuration is a mail server on the same machine running @product@. If this is
not your configuration, you must modify the defaults. To do this, use
a `portal-ext.properties` file in your Liferay Home folder (see below).

To use your application server's mail session, you must create it in your
application server. Once you've created a mail session, you can point @product@
to it through your `portal-ext.properties` file or through the Control Panel.

If you plan to use @product@ to manage both your database connection and mail
session, enter your database connection information on the Basic Configuration
page when @product@ first starts, and then enter your mail server information
through the Control Panel.

If you plan to let your application server manage your database connection or
your mail server, you can't use @product@'s setup wizard: you must follow the
instructions in the [Manual Configuration](#manual-configuration) section below.

The installation articles for each application server also include instructions
for configuring your application server to manage the database connection and
mail server.

## Manual Configuration [](id=manual-configuration)

To have your application server manage your database connection or mail server
(or both), you must manually create this configuration. Create a text file
called `portal-ext.properties` in your Liferay Home folder. This file overrides
the default properties. 

To use your application server's data source, create a connection pool in your
application server that points to your database. The connection pool should be
called `jdbc/LiferayPool`. This is spelled out for each application server in
its article. To tell @product@ to use your `jdbc/LiferayPool` connection pool,
add the following directive to your `portal-ext.properties` file:

    jdbc.default.jndi.name=jdbc/LiferayPool

Next, install @product@ according to the article for your application server.
Once it's installed, you can set up the mail configuration.

You should use the Control Panel to create the mail configuration. Go to
*Control Panel &rarr; Configuration &rarr; Server Administration &rarr; Mail*
and enter your settings for your mail session settings. 

You can also configure this with the `portal-ext.properties` file, which lets
you do the configuration once and then copy the configuration file to multiple
machines. To use the built-in mail session, use the following properties and
customize their values for your environment:

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

To use your application server's mail session, create it first. Then specify it
in the `portal-ext.properties` file:

    mail.session.jndi.name=mail/MailSession

When you're finished, save the file.

