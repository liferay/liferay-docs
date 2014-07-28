# Manual Configuration [](id=manual-configuration-lp-6-2-use-useportal)

You don't have to use the setup wizard to configure Liferay. The setup wizard
behind the scenes creates a configuration file that you can create manually.
Create a text file called `portal-ext.properties` in your Liferay Home folder.
This file overrides default properties that come with Liferay. The first setting
you'll override is the default configuration that points Liferay to the embedded
HSQL database.

As stated above, there are two ways to set up the connection:

-   Use the built-in connection pool.

-   Use your application server's connection pool.

Use the setup wizard if you're using the built-in connection pool. If you want
to use your application server's pool, continue with this procedure.

If you want to use your application server's connection pool, you will have to
create one in your application server that points to your database. The
connection pool should be called `jdbc/LiferayPool`. In the application server
sections above, there are instructions for how to do this on each application
server that Liferay supports. To tell Liferay to use your `jdbc/LiferayPool`
connection pool, add the following directive to your `portal-ext.properties`
file:

    jdbc.default.jndi.name=jdbc/LiferayPool

Next, install Liferay according to the instructions for your application server.
Once it's installed, you can set up the mail configuration. 

For mail, you can use Liferay's control panel to create the configuration and
this is the recommended way. Go to *Control Panel &rarr; Server Administration
&rarr; Mail* and enter your settings for your mail session settings. If,
however, you're setting up a lot of Liferay machines and they're all going to
have similar mail configurations, it's easier to do the configuration once and
then copy the configuration file to multiple machines. In this case, you'll want
to use the `portal-ext.properties` file. To use the built-in mail session, use
the following properties and customize their values for your environment: 

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

All the instructions above assumed that you wanted to install Liferay Portal at
the root context of your server. But what if that isn't the case? Next, you'll
see how to use a different context for Liferay Portal.
