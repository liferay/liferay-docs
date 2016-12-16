# Installing @product@

Now that you've performed the steps needed to prepare for your installation,
you're ready to install @product@! Since bundles are the easiest way to complete
an installation, all the installation steps below assume a bundle has been
installed. If you plan to install @product@ manually, please refer to the
article for your app server of choice, and then come back here to complete the
configuration steps. 

Now you're ready. You've created a blank database for @product@ and have
gathered the credentials you need for your mail server. The next step is to
install @product@. 

## Liferay Home [](id=liferay-home)

@product@ bundles contain the same folder structure regardless of application
server. The top-level folder is named for the release of @product@. This folder
is called *Liferay Home*. This folder is usually the parent folder of the
application server's folder. This is why @product@ bundles place the application
server inside the bundle's root folder. On a manual installation, the location
of this folder varies by application server. In a bundle, it's part of the
bundle. If you're doing a manual installation, please refer to the article
covering that app server for its location. 

In the Liferay Home folder there are folders for various purposes:

    - [Liferay Home]
        - `data`
        - `deploy`
        - `logs`
        - `osgi`
        - [Application Server]

`data`: This folder is used to store an embedded HSQL database, @product@'s
file repository, and @product@'s search indexes. @product@ is initially configured
to use the embedded HSQL database but the HSQL database is primarily intended
for demonstration and trial purposes. 

`deploy`: To deploy @product@ plugins, copy them to this folder. Legacy style
`.war` files, @product-ver@ style `.jar` files, and `.lpkg` files from Liferay
Marketplace are supported.

`logs`: This folder contains @product@'s log files. The information in @product@'s
log files can be quite valuable for system administrators, especially when
trying to diagnose a problem.

`osgi`: All the JAR files and a few configuration files for @product@'s OSGi
runtime belong in this folder.

**[Application Server]:** The name of this folder is different depending on
the bundle you're using. This folder contains the application server in
which @product@ has been installed.

If @product@ is unable to create the resources it needs in the Liferay Home
folder or if it finds itself running on certain application servers, it creates
a folder called `liferay` in the home folder of the operating system user that
is running @product@. In this case, the `liferay` folder becomes Liferay Home.
For example, if the operating system user's name was jbloggs, the Liferay Home
folder could be `/home/bloggs/liferay` or `C:\Users\jbloggs\liferay`.

## Extracting a @product@ [](id=extracting-a-liferay-bundle)

Getting a @product@ bundle up and running is as easy as uncompressing the
archive, possibly copying a JDBC driver, and then starting the application
server. Let's use the @product@ Tomcat bundle as an example.

1. Unzip your @product@ bundle.

2. If you're setting up @product@ to be an actual server, copy your database's
   JDBC driver `.jar` file to `[Tomcat]/lib/ext`. If you're using a supported
   open source database or if you're setting up @product@ for demo purposes, you
   can skip this step.

That's it! You've extracted @product@, and it's ready for use. This is much
easier than doing a manual installation on an app server. If, however, that's
what you need to do, please at this point click the link on the left and go
through the installation procedure for your app server of choice. When you're
finished with the installation (and before you've started @product@ for the
first time), come back to this spot, because you need to hook it up to your
database. 

## Connecting @product@ to Your Database [](id=connecting-product-to-your-database)

You can connect @product@ through either your app server's data source or the one
that ships with @product@. Because of its suitability for tuning, it is
recommended that you use the data source that ships with @product@. To do this,
you'll create a configuration file called `portal-ext.properties`, and you'll
place that file in your Liferay Home folder. 

The configuration varies by database, of course, so templates for each one are
provided in the [reference section](/discover/reference/-/knowledge_base/7-0/database-templates). 
To connect your database, therefore, create a text file called
`portal-ext.properties` in your Liferay Home folder. Copy the relevant template
for your database and paste it into this file. 

Now all you have to do is customize it. Enter the proper host name and user and
password credentials for your database, and then save the file. 

## Running @product@ for the First Time [](id=running-product-for-the-first-time)

Next, start your app server, or start the @product@ app in your app server. For
example, if you're using the @product@-Tomcat bundle, start Tomcat as if you had
downloaded it manually. Tomcat is launched by invoking a script which is found
in its `bin` folder. If you open a command prompt or terminal and go to this
folder, you can launch Tomcat via the following command on Windows:

        startup

    or the following command on Linux/Mac/Unix:

        ./startup.sh

The @product@ Tomcat bundle then starts. If you are on Windows, another command
prompt window appears with Tomcat's console in it. If you are on Linux, you can
see the Tomcat console by issuing the following command:

    tail -f ../logs/catalina.out

The first time @product@ starts, it'll take a while to create all of its database
tables. Once it has successfully started, it automatically launches a web browser that
displays @product@'s Basic Configuration page. If for some reason your browser
doesn't load the Basic Configuration page, launch your web browser and navigate
to [http://localhost:8080](http://localhost:8080). 

# Using @product@'s Setup Wizard [](id=using-liferays-setup-wizard)

@product@'s Setup Wizard runs when you start @product@ for the first time. To make
it easy to configure @product@, the first thing you see when browsing to your
newly installed @product@ bundle is a setup wizard. The title of the setup wizard
page is Basic Configuration. This page provides a convenient way to make an
initial @product@ configuration.

There are two sections of the wizard: the portal and the administrator.  For the
portal, you need to supply the following information:

**Portal Name:** the name of the portal you're powering with @product@.

**Default Language:** choose the default locale of your portal.

For the administrator, you need to supply the following information:

**First Name:** the first name of the default administrator user

**Last Name:** the last name of the default administrator user

**Email:** the email address of the default administrator user

![Figure 1: Supply the information for your portal and your portal's default administrator user on the Basic Configuration page.](../../images/basic-configuration1.png)

The Basic Configuration page also includes a checkbox labeled *Add Sample
Data*. If you check this box, sample data is added to @product@'s database. This
data includes users, sites, and organizations. The sample data allows many
@product@ features to be showcased. If you're installing @product@ on your own
machine to explore its features, the sample data will probably be useful. If,
however, you're installing @product@ on a real server, you should start with a
clean system.

Once you've filled out the form, click *Finish Configuration*. The setup wizard
creates a `portal-setup-wizard.properties` file which stores the settings that
you entered. When you begin customizing your portal's configuration, however,
you should use the `portal-ext.properties` file you created earlier. All the
possible properties that can be placed in this file are documented in [our
reference documentation](http://docs.liferay.com/portal/7.0/propertiesdoc).

+$$$

**Tip:** The wizard is an extremely helpful tool, especially if you're setting
up @product@ for the first time. If you're a @product@ veteran and you already have
your various properties set up, you can disable the setup wizard. If you disable
the setup wizard, you must configure everything manually from the 
`portal-ext.properties` file. To disable the setup wizard, enter
`setup.wizard.enabled=false` in your `portal-ext.properties` file. Note that
property values in `portal-setup-wizard.properties` (the file created in Liferay
Home by the setup wizard) override property values in `portal-ext.properties`.

$$$

After you've entered the information requested by the Basic Configuration page,
@product@ should bring you to its home page. You should set up your mail
configuration next. 

## Configuring Mail [](id=configuring-mail)

Now that @product@ is up and running, log in as the administrative user you
created in the setup wizard. Click the menu icon and then go to Control Panel
&rarr; Server Administration &rarr; Mail, and have your mail credentials ready. 

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

Your next step for basic @product@ configuration is to convert the search
implementation from its default demo mode into a production-ready mode. 



