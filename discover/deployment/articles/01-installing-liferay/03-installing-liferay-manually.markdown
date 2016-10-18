# Installing Liferay Manually [](id=installing-liferay-manually)

The easiest way to install @product@ is to use a @product@ bundle. However, this is
not always possible. Some organizations have an existing infrastructure into
which @product@ must be installed. Other organizations have standardized on a
particular application server. @product@ has been designed to work well
with many leading application servers. Even if you have to manually install
@product@ on an existing application server, the procedure is straightforward.
Before you get started, note that there are two distinct approaches to managing
Liferay's data source and mail session. Let's review these options.

## Using Data Sources [](id=using-data-sources)

@product@ provides two ways to configure your data source:

- Use Liferay's built-in data source
- Use your application's server's JNDI data source

@product@ recommends that you use the built-in data source. Liferay's data source
is configured by a number of properties that are set in a properties file. By
default, you can enter database connection information on the Basic
Configuration page that appears when @product@ starts for the first time. The
setup wizard stores the information you entered in a configuration file called
`portal-setup-wizard.properties` in your Liferay Home folder. Liferay's
built-in data source uses this information to connect to the database.

Although @product@ recommends that you use the built-in data source, that's not
the only option. Some organizations prefer to use the data source provided by
their application server of choice. In this case, a JNDI lookup provides a
handle to the data source and the application server manages the connection
pools. So you can configure @product@ to use your application server's data
source, if that's desired.

To do this, you'll need to create your own configuration file and skip the
setup wizard. Since you'd be creating this file *after* the wizard anyway, this
isn't such a big deal. We show you how to configure a JNDI data source in the
Manual Configuration section below.

Since mail sessions are configured similarly to data sources, we'll look at
them next.

## Using Mail Sessions [](id=using-mail-sessions)

As with databases, you have two ways to configure your mail server:

- Use Liferay's built-in mail session
- Use your application server's mail session

@product@ recommends using the built-in mail session. After you've started
@product@, you can configure a mail server through Liferay's Control Panel.
Liferay's default configuration looks for a mail server on the same machine on
which Liferay is running and it tries to send mail via SMTP to this server. If
this is not your configuration, you'll need to modify Liferay's defaults. To do
this, you can use a `portal-ext.properties` file in your Liferay Home folder
(see below).

To use your application server's mail session, you must create it in your
application server. It should point to your mail server. Once you've created a
mail session in your application server, you're ready to point Liferay to it.
You can do this through your `portal-ext.properties` file or through
Liferay's Control Panel.

You now have all the background information you need to decide whether to use
Liferay's built-in data source or the one provided by your application server.
Similarly, you can now decide whether to use Liferay's mail session or your
application server's mail session. If you're planning to use @product@ to manage
both your database connection and mail session, great! When you start @product@,
simply enter your database connection information on the Basic Configuration
page and then enter your mail server information through the Control Panel.

If you're planning to let your application server manage your database
connection, you can't use Liferay's setup wizard. If you want to configure your
application server to manage either your database or mail server, you'll have
to follow the instructions in the Manual Configuration section below. The
Liferay Installation documentation for each specific application server also
includes instructions for configuring your application server to manage
Liferay's database connection and mail server.

## Manual Configuration [](id=manual-configuration)

If you want your application server to manage either your database connection
or mail server (or both), you'll need to manually create this configuration.
Create a text file called `portal-ext.properties` in your Liferay Home folder.
This file overrides default properties that come with @product@. The first
setting you'll override is the default configuration that points Liferay to the
embedded HSQL database.

As stated above, there are two ways to set up the connection:

- Use Liferay's built-in data source
- Use your application server's data source

Use the setup wizard if you're using Liferay's data source. If you want to use
your application server's pool, continue with this procedure.

If you want to use your application server's data source, you will have to
create a connection pool in your application server that points to your
database. The connection pool should be called `jdbc/LiferayPool`. You can find
instructions for how to do this in the installation documentation for each
application server that @product@ supports. To tell Liferay to use your
`jdbc/LiferayPool` connection pool, add the following directive to your
`portal-ext.properties` file:

    jdbc.default.jndi.name=jdbc/LiferayPool

Next, install @product@ according to the instructions for your application
server. Once it's installed, you can set up the mail configuration.

For mail, you should use Liferay's Control Panel to create the configuration.
Go to *Control Panel &rarr; Server Administration &rarr; Mail* and enter your
settings for your mail session settings. If, however, you're setting up a lot
of Liferay machines and they're all going to have similar mail configurations,
it's easier to do the configuration once and then copy the configuration file
to multiple machines. In this case, you'll want to use the
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

When you've finished, save the file.

All the instructions above assumed that you wanted to install @product@ at
the root context of your server. But what if that isn't the case? Next, you'll
see how to use a different context for @product@.

## Making Liferay Coexist with Other Java EE Applications [](id=making-liferay-coexist-with-other-java-ee-applications)

By default, @product@ is configured to sit at the root (i.e., `/`) of your
application server. Dedicating your application server to running only @product@
is a good practice. This allows your portal environment to be separated
from your web application environment. This is generally a best practice for
portals which, by definition, are application development platforms in and of
themselves. For this reason, your Liferay instance is likely to be hosting many
applications and even integrating several of them together on a single page.
For this reason, you should design your system so your portal environment has
all the resources it needs to do this. Configuring it so it is the sole
consumer of any other `.war` files that get deployed to the application server
helps to make sure your system performs optimally.

If, however, you want @product@ to share space on an application server with
other applications, you can. In this instance, you might not want to make
@product@ the default application in the root context of the server. If you want
to install @product@ in a context other than the root context, follow the
instructions from your app server vendor. No additional steps are necessary.
