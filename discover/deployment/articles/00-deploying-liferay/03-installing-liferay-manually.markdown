# Installing @product@ Manually [](id=installing-liferay-manually)

The easiest way to install @product@ is to
[use a @product@ bundle](/discover/deployment/-/knowledge_base/7-0/installing-product).
However, this is not always possible. Some organizations have an existing
infrastructure into which @product@ must be installed. Other organizations have
standardized on a particular application server. @product@ works well with many
leading application servers. Even if you have to manually install it on an
existing application server, the procedure is straightforward. Before you get
started, note that there are two distinct approaches to managing @product@'s
data source and mail session. All these topics are covered:

- [Using data sources](#using-data-sources)
- [Using mail sessions](#using-mail-sessions)
- [Manual configuration for data sources and mail sessions](#manual-configuration)
- [Making @product@ coexist with other Java EE applications](#making-liferay-coexist-with-other-java-ee-applications)

Start with data sources. 

## Using Data Sources [](id=using-data-sources)

@product@ provides two ways to configure your data source:

- Use @product@'s built-in data source
- Use your application's server's JNDI data source

It's recommended to use the built-in data source. @product@'s data source is
configured by properties set in a properties file. By default, you can enter
database connection information on the
[Basic Configuration page](/discover/deployment/-/knowledge_base/7-1/installing-product#using-liferays-setup-wizard)
that appears when @product@ starts for the first time. The setup wizard stores
the information you entered in a configuration file called
`portal-setup-wizard.properties` in your
[Liferay Home](/discover/deployment/-/knowledge_base/7-1/installing-product#liferay-home)
folder. The built-in data source uses this information to connect to the
database.

Although using the built-in data source is recommended, that's not the only
option. You might prefer to use the data source your application server provides. In this case, a JNDI lookup provides a handle to the data
source and the application server manages the connection pools. So you can
configure @product@ to use your application server's data source, if you prefer.

To do this, you must your own configuration file and skip the setup wizard.
Since you'd be creating this file *after* the wizard anyway, this isn't a big
deal. 
[Manual Configuration](#manual-configuration)
section below demonstrates configuring a JNDI data source.

Since mail sessions are configured similarly to data sources, they're next.

## Using Mail Sessions [](id=using-mail-sessions)

As with databases, you have two ways to configure your mail server:

- Use @product@'s built-in mail session
- Use your application server's mail session

Using the built-in mail session is recommended. After you've started @product@,
you can configure a mail server through the Control Panel. The default
configuration looks for a mail server on the same machine running @product@ and
it tries to send mail via SMTP to this server. If this is not your
configuration, you must modify the defaults. To do this, use a
`portal-ext.properties` file in your Liferay Home folder (see below).

To use your application server's mail session, you must create it in your
application server. Point it to your mail server. Once you've created a mail
session, you can point @product@ to it through your `portal-ext.properties` file
or through the Control Panel.

You now have all the background information for deciding whether to use
@product@'s built-in data source or the one provided by your application server.
Similarly, you can now decide whether to use @product@'s mail session or your
application server's mail session. If you're planning to use @product@ to manage
both your database connection and mail session, great! When you start @product@,
simply enter your database connection information on the Basic Configuration
page and then enter your mail server information through the Control Panel.

If you're planning to let your application server manage your database
connection, you can't use @product@'s setup wizard. If you want to configure
your application server to manage either your database or mail server, you must
follow the instructions in the 
[Manual Configuration](#manual-configuration)
section below. The @product@ Installation articles for each specific application
server also include instructions for configuring your application server to
manage the database connection and mail server.

## Manual Configuration [](id=manual-configuration)

If you want your application server to manage either your database connection or
mail server (or both), you must manually create this configuration. Create a
text file called `portal-ext.properties` in your Liferay Home folder. This file
overrides the default properties. 

As stated above, there are two ways to set up the connection:

- Use @product@'s built-in data source
- Use your application server's data source

Use the setup wizard if you're using @product@'s data source. If you want to use
your application server's pool, continue with this procedure.

If you want to use your application server's data source, you will have to
create a connection pool in your application server that points to your
database. The connection pool should be called `jdbc/LiferayPool`. The @product@
installation articles for each application server explain how to do this. To
tell @product@ to use your `jdbc/LiferayPool` connection pool, add the following
directive to your `portal-ext.properties` file:

    jdbc.default.jndi.name=jdbc/LiferayPool

Next, install @product@ according to the article for your application server.
Once it's installed, you can set up the mail configuration.

You should use the Control Panel to create the mail configuration. Go to
*Control Panel &rarr; Configuration &rarr; Server Administration &rarr; Mail*
and enter your settings for your mail session settings. If, however, you're
setting up a lot of @product@ machines and they're all going to have similar
mail configurations, it's easier to do the configuration once and then copy the
configuration file to multiple machines. In this case, you'll want to use the
`portal-ext.properties` file. To use the built-in mail session, use the
following properties and customize their values for your environment:

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

All the instructions above assume that you want to install @product@ at the root
context of your server. But what if that isn't the case? Next, you'll see how to
use a different context for @product@.

## Making @product@ Coexist with Other Java EE Applications [](id=making-liferay-coexist-with-other-java-ee-applications)

By default, @product@ is configured to sit at the root (i.e., `/`) of your
application server. Dedicating your application server to running only @product@
is a good practice. This separates your portal environment from your web
application environment. This is generally a best practice for portals which, by
definition, are application development platforms in and of themselves. For this
reason, your @product@ instance is likely to be hosting many applications and
even integrating several of them together on a single page. In this case, you
should design your system so your portal environment has all the resources it
needs to do this. Configuring @product@ as the sole consumer of any other `.war`
files that get deployed to the application server helps your system perform
optimally.

If, however, you want @product@ to share application server space with other
applications, you can. In this instance, you might not want to make @product@
the default application in the root context of the server. To install @product@
in a context other than the root context, follow the instructions from your app
server vendor. No additional steps are necessary.
