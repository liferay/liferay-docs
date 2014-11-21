# Using Liferay's setup wizard [](id=using-liferays-setup-wizard)

To make it easy to configure Liferay optimally for your use, the first thing you
see when browsing to your newly installed Liferay bundle is a setup wizard. This
gives you a convenient way to configure Liferay for your purposes. 

There are three sections of the wizard: the portal, the administrator and the
database. For the portal, you need to supply the following information: 

**Portal Name:** the name of the web site you're powering with Liferay. In this
book, we build a website for a lunar colony. This site is called Lunar Resort, 
so we've supplied `Lunar Resort` in the screenshot below. 

**Default Language:** choose the default locale where your site resides. 

For the administrator, you need to supply the following information: 

**First Name:** the first name of the user that has the administrator account. 

**Last Name:** the last name of the user that has the administrator account. 

**Email:** the email address of the user that has the administrator account. 

![Figure 1.15: Supply the information for your site and your site's administrative account in the setup wizard.](../../images/setup-wizard-1.png)

Liferay supports just about all the leading databases today: 

* DB2

* MySQL

* Oracle

* PostgreSQL

* SQL Server

* Sybase

In addition to these, Liferay also supports a few embedded databases that are
designed for development. We haven't listed these here because you're setting up
a production Liferay server, and you shouldn't use an embedded database with a
production box. 

Before you fill out the database section of Liferay's setup wizard, you should
already have created a database for Liferay to use. If you haven't, please see
the section above entitled *Liferay's Database*. 

Open the Database section of the wizard. From the select box, choose your
database. You'll see a form which lets you specify the URL to the database, the
driver class, and the user credentials (see below). Most of this is filled out
already; all you should need to do is supply the name of your database and the
server it's running on, as well as the user credentials. 

![Figure 1.16: Fill out the information for your database. We've chosen MySQL in this example and have created a database called *lunar_resort* to hold our Liferay data.](../../images/setup-wizard-2.png)

Once you've filled out the form, click *Finish Configuration*. You'll see a
message stating that Liferay is being installed as it creates the tables and
data it needs in its database. When it's finished, it tells you the location of
the configuration file (`portal-setup-wizard.properties`) where it saved all
your settings. From here, you can go to your home page. 

Congratulations! You've just installed Liferay Portal! The next thing you need
to do is set up your mail configuration, so Liferay can send email notifications
to users. This is covered in the Manual Configuration section below. 

+$$$

**Tip:** The wizard is an extremely helpful tool,
especially if you're setting up Liferay for the first time or creating a
completely fresh portal instance. If you're a Liferay veteran and you already
have your database information and various properties set up, you can skip the
wizard by adding this line to your *portal-ext.properties* file:

    setup.wizard.enabled=false

$$$

+$$$

**Tip:** In Liferay 6.2, the admin user test@liferay.com
is created by the setup wizard even when a different user is specified. This
means that two admin users are created: test@liferay.com and the specified user.
Unless you're just installing Liferay for testing purposes, you should
deactivate the test@liferay.com user after your database has been created.

$$$
