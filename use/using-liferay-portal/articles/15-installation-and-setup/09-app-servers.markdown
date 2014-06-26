# App Servers [](id=app-servers-lp-6-2-use-useportal)

When it comes time to install Liferay Portal on your server, you'll find it's
easiest to do this by starting with a bundle. But many organizations can't do
that. There may be an existing infrastructure into which you're installing
Liferay, or you may have standardized on a particular application server. You'll
be happy to know that Liferay Portal has been designed to work well with all the
leading application servers and that it's easy and straightforward to install.
But before we get started, we need to go over a few concepts; namely, data
sources and mail sessions. These were touched on in the section on bundles above
but we'll look at them in more detail now. 

## Using data sources

Liferay comes bundled with its own built-in data source. It's configured by a
number of properties that are set in a properties file. By default, the setup
wizard asks you for the necessary values and creates a configuration file that
uses the built-in data source to connect to the database. 

Liferay always recommends that you use the built-in data source. Sometimes,
however, organizations prefer to use the data source provided by their
application server of choice. In this instance, a JNDI lookup provides a handle
to the data source and the application server manages the connection pools.
Liferay supports using your application server's data source if you wish to do
that. 

To do this, you'll need to create your own configuration file and skip the setup
wizard. Since you'd be creating this file *after* the wizard anyway, this isn't
such a big deal. We show you how to do that after all the sections on
application servers. 

Since mail sessions are configured in a similar way to data sources, we'll look
at them next. 

## Using mail sessions

Liferay's default configuration looks for a mail server on the same machine on
which Liferay's running and it tries to send mail via SMTP to this server. If
this is not your configuration, you'll need to modify Liferay's defaults. To do
this, you'll use a `portal-ext.properties` file (see below). 

In a similar fashion to databases, you have two ways to configure your mail
server:

-   Use your application server's mail session.

-   Use the built-in mail session.

To use your application server's mail session, you must create it in your
application server and it should point to your mail server. Once you've done
that, you're ready to point Liferay to it. You can do this through the
configuration file or through Liferay's control panel after it's been installed. 

You now have all the background information you need. Next you need to make your
decision: will you use Liferay's built-in data source, or the one provided by
your application server? If you're planning to use the one provided by your
server, you can't use Liferay's installation wizard, and you'll have to follow
the instructions in the section below titled Manual Configuration. 

In either case, your next step is to install Liferay onto your application
server. What follows are sections for every application server that Liferay
supports. Once you have Liferay Portal installed, the first thing you see is the
setup wizard. To continue, follow the instructions for setting up Liferay on
your application server, and then skip down to the section on the setup wizard. 

Next, follow the instructions for installing Liferay on your particular
application server in the section below.
