# Getting Started

Liferay Social Office is designed to be quick and easy to set up and get
running for your enterprise. We've endeavored to make the set up process
as simple and straightforward as possible. Whether you're installing
Liferay Social Office on your own machine for testing purposes or
installing it for production use, set up time is measured in minutes,
not hours.

This chapter will describe the procedure for installing Liferay Social
Office and connecting it to an enterprise database so that it can be
used in production environments. While this should be sufficient for
everyone who uses Social Office, note that since Social Office is based
on Liferay Portal technology, its performance can be optimized in
similar ways as Liferay Portal. If you decide that you need an advanced
configuration of Social Office (such as a cluster), please see the
*Liferay Portal Administrator's Guide*, specifically Chapter 6:
Enterprise Configuration for more information.

Once Social Office is installed, you'll want to become familiar with the
user interface. For this reason, we'll go over some of the first steps
you'll take as a Social Office administrator.. Before you know it,
you'll be up and running—ready to streamline your work and facilitating
collaboration between team members!

## Installing the software on your platform

The initial release of Social Office is available as a bundle from
Liferay. You should have received either a cross-platform .zip file or a
Windows executable file. In either case, you'll find that installing
Social Office is simple and straightforward.

### Using the Windows Installer

![image](../../images/02-getting-started_html_m15c2239.png)\
*Illustration 1: The Social Office Windows installer*

Simply double-click on the installer you have received and the
installation will begin. A standard installer will appear and ask you
where you want to install the software. The default is C:\\Liferay,**but
you can change this to whatever path you wish. Once the installer is
finished, Social Office is immediately available—there's no need to
reboot. After the installation, you'll find a new group in your Windows
Start Menu for Social Office. To start Social Office, simply click on
*Start*. That's it! To stop Social Office, click on *Stop*. (see
illustration 5). Note that this particular default install is useful
only for demo and evaluation purposes: it uses an embedded database
which is great for getting up and running quickly. If you want to
configure Social Office for production use, please see the section below
on connecting it to an enterprise database.

![image](../../images/02-getting-started_html_472817b.png)\
*Illustration 2: Starting and Stopping Social Office*

Because Social Office is a browser-based application, it starts up as a
web server process on your machine. For this reason, you'll see a window
pop up and display some messages before your browser launches to view
Social Office. This is normal and to be expected. Please do not close
this box! When it has finished starting, your browser will launch
automatically so that you can log into Social Office.

### Using the Cross-Platform Zip File

If you're running an operating system other than Windows, you can use
the cross-platform .zip file to install Social Office. The one
prerequisite is that Java is installed properly. Install Java and make
sure that your JAVA\_HOME environment variable is pointing to the
location of your JDK. Once this is done, simply extract the .zip file to
the location of your choice, and then Social Office can be launched via
scripts from the command line.

The same caveats apply to the .zip distribution as apply to the Windows
installer: the default install is useful *only for demo and evaluation
purposes* because it uses an embedded database. While this database is
great for getting up and running quickly, it doesn't perform well in
production environments where many users access the system. For this
reason, when you're ready to configure Social Office for production use,
please see the section below on connecting it to an enterprise database.

To start Social Office, launch the script that's found in the *[Social
Office Folder]/tomcat-[version]/bin* folder, where [Social Office
Folder] is the top level folder extracted from the .zip file.

You can use the following command on Linux / Mac / Unix to start Social
Office:

./startup.sh

Social Office will then launch.

Similarly, to stop Social Office use this command:

./shutdown.sh

Windows systems also have the same start and stop scripts, found in the
same folder. To start Social Office on Windows, use this command:

startup

And to stop it, use this command:

shutdown

If you're on Linux, Unix, or Mac, you can see the Tomcat console by
issuing the following command:

tail -f ../logs/catalina.out

Once Social Office has completed its startup, it will automatically
launch a web browser so you can see the home page. If this doesn't
happen, launch your web browser and then go to the following address:
http://localhost:8080. The default Social Office login page will appear
in your web browser. Now you're ready to explore the various features of
Social Office!

## Installing Social Office for Production Use

Eventually, you'll want to install Social Office onto a production
server. Unlike Liferay Portal, which can be downloaded both as a
standalone .war file or as a bundle, Social Office has been simplified
by distributing it as a bundle with an application server. This greatly
simplifies the setup and configuration for Social Office, as there's
significantly fewer steps to get a bundle up and running.

Unzip the bundle or install it to the location from which you're going
to run it using the instructions above. For example, you might use
D:\\apps in Windows or /opt in Linux or UNIX variants. As we mentioned
before, the default bundle installation of Social Office uses an
embedded database. While this works great for rapid evaluation or
development, it has several drawbacks:

-   Only one user can access it at a time since the data is stored on a
    file on disk and HSQL locks it when doing changes.

-   The data is stored inside the bundle and might be lost on
    redeployment.

-   This configuration doesn't scale well and will perform poorly once
    multiple users access the system.

For these reasons, you don't want to run Social Office against the
embedded database. Fortunately, Social Office has great support for most
production-ready databases, and it's easy to configure Social Office to
use them. The exact instructions will depend on the database, but here's
a summary:

1.  Create the database in your DBMS of choice (see the section below
    labeled *Database Setup* for further information).

2.  Create a *portal-ext.properties* file in the Social Office Home
    folder which points to the database and mail session.

3.  Start Social Office. Social Office will create the tables
    automatically and start.

As you can see, this is a simple, three step process. Using MySQL as an
example, we'll go over the exact settings in *portal-ext.properties*
below.

### Database Setup

First things first, let's get that database set up. Using your database
vendor's tools, create your database and grant a user ID full access to
it. You'll eventually configure Social Office to connect to the database
using this user ID. Once this is completed, the first time Social Office
starts it'll create the indexes and tables automatically.

### Social Office Home

In Social Office, there's a folder named *Home*. This folder is the top
level folder that was extracted from the .zip file or the location you
installed Social Office via the installer.

Social Office Home is primarily used for storing special configuration
files, like *portal-ext.properties*. Please take a moment to find this
folder on your system. We'll be creating a configuration file there.

### The *portal-ext.properties* File

Liferay Social Office, like Liferay Portal, makes use of a configuration
file to store its settings. These properties files differ from the
configuration files of most other products in that changing the default
configuration file is discouraged. In fact, the file that contains all
of the defaults is stored inside of a .jar file, making it more
difficult to customize. Why is Social Office set up this way? Because
Liferay's products use the concept of *overriding* the defaults in a
separate file rather than going in and customizing the default
configuration file. You only change the settings you want to customize
in your own configuration file, and then the configuration file for your
software remains uncluttered and contains only the settings you need.
This makes it far easier to determine whether a particular setting has
been customized— and it makes the settings more portable across
different installations of Liferay Portal and Liferay Social Office.

The default configuration file is named *portal.properties*, and the
default version resides inside the core of Social Office. To override
the settings in this file we use another, similarly named file called
*portal-ext.properties*. This file is stored in your Social Office Home
folder. By default, the file doesn't exist at all, so you'll need to
create it to get Social Office connected to your database.

So let's get started pointing your Social Office bundle to your
database. Create a file called *portal-ext.properties* in your Social
Office Home folder. Since this file overrides default properties that
come with Social Office, you're going to override the default
configuration which points Social Office to the embedded HSQL database.
Using the built in connection pool, you can easily connect Social Office
to most popular database servers. The template for MySQL is provided as
an example below.

\#

\# MySQL

\#

jdbc.default.driverClassName=com.mysql.jdbc.Driver

jdbc.default.url=jdbc:mysql://localhost/lportal?useUnicode=true&characterEn-
coding=UTF-8&useFastDateParsing=false

jdbc.default.username=mommy

jdbc.default.password=daddy

In the configuration above, we're assuming the user name is *mommy* and
the password is *daddy*. Obviously, you would instead use the user ID
and password you configured earlier in your database. You would also
point the JDBC URL to your database server, unless you're running the
database on the same machine as Social Office.

For email there's a similar procedure using the built-in mail session.
The mail session is a resource which enables communication between
Social Office and your mail server. Please note: configuring the mail
session properly is very important! Since several of Social Office's
applications rely heavily email notifications, the ability to
communicate with your mail server is essential. To configure the mail
session, please add the following directives to your
*portal-ext.properties*file (substituting your mail server information):

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

Save the file. You're all done! The next time you start Social Office,
it'll connect to your database server and create the tables it needs to
function.

We used MySQL above, just as an example, but of course Social Office
supports most common databases. Below we've provided the templates for
all of the supported databases. Use the template that corresponds to the
one you're using.

#### DB2

jdbc.default.driverClassName=com.ibm.db2.jcc.DB2Driver

jdbc.default.url=jdbc:db2://localhost:50000/lportal:deferPrepares=false;fullyMaterializeInputStreams=true;fullyMaterializeLobData=true;progresssiveLocators=2;progressiveStreaming=2;

jdbc.default.username=db2admin

jdbc.default.password=lportal

#### Derby

Derby is an embedded database and is not recommended for production
environments.

jdbc.default.driverClassName=org.apache.derby.jdbc.EmbeddedDriver

jdbc.default.url=jdbc:derby:lportal

jdbc.default.username=

jdbc.default.password=

#### Hypersonic

Hypersonic is an embedded database and is not recommended for production
environments.

jdbc.default.driverClassName=org.hsqldb.jdbcDriver

jdbc.default.url=jdbc:hsqldb:${liferay.home}/data/hsql/lportal

jdbc.default.username=sa

jdbc.default.password=

#### Ingres

jdbc.default.driverClassName=com.ingres.jdbc.IngresDriver

jdbc.default.url=jdbc:ingres://localhost:II7/lportal

jdbc.default.username=

jdbc.default.password=

#### MySQL

jdbc.default.driverClassName=com.mysql.jdbc.Driver

jdbc.default.url=jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false

jdbc.default.username=

jdbc.default.password=

#### Oracle

jdbc.default.driverClassName=oracle.jdbc.driver.OracleDriver

jdbc.default.url=jdbc:oracle:thin:@localhost:1521:xe

jdbc.default.username=lportal

jdbc.default.password=lportal

#### PostgreSQL

jdbc.default.driverClassName=org.postgresql.Driver

jdbc.default.url=jdbc:postgresql://localhost:5432/lportal

jdbc.default.username=sa

jdbc.default.password=

#### SQL Server

jdbc.default.driverClassName=net.sourceforge.jtds.jdbc.Driver

jdbc.default.url=jdbc:jtds:sqlserver://localhost/lportal

jdbc.default.username=sa

jdbc.default.password=

#### Sybase

jdbc.default.driverClassName=net.sourceforge.jtds.jdbc.Driver

jdbc.default.url=jdbc:jtds:sybase://localhost:5000/lportal

jdbc.default.username=sa

jdbc.default.password=

As you can see, Social Office supports a number of the leading databases
in the industry today.

Social Office will take about a minute to launch while it creates the
tables.. Once this is completed, a new browser window automatically
opens and you'rre presented with the Sign In screen.

## Signing In

When prompted, sign in using the default user id and password.

**Screen Name:**admin

**Password:** admin

Go ahead and sign in using these credentials. If this is the first time
you're logging in, you'll be prompted to choose a password reminder.
Once you've chosen a question and filled out the answer, click *Save.*

### 

![image](../../images/02-getting-started_html_m2b77f0d0.png)*Illustration 1: Sign In
Portlet*

\

Sign In Options

The Sign In screen has a three options at the bottom of it. These are
options you've probably seen on other web sites.

#### OpenID

OpenID is a new single sign-on standard which is implemented by multiple
vendors. The idea is that multiple vendors can implement the standard,
and then users can register for an ID with the vendor they trust. The
credential issued by that vendor can be used by all the web sites that
support OpenID. Some high profile OpenID vendors are AOL
([http://](http://openid.aol.com/screenname)[openid](http://openid.aol.com/screenname)[.aol.com/screenname](http://openid.aol.com/screenname)),
LiveDoor
([http://profile.livedoor.com/](http://profile.livedoor.com/username)[user](http://profile.livedoor.com/username)[name](http://profile.livedoor.com/username)),
and LiveJournal
([http://](http://username.livejournal.com/)[user](http://username.livejournal.com/)[name.live](http://username.livejournal.com/)[journal](http://username.livejournal.com/)[.com](http://username.livejournal.com/)).
Please see the OpenID site
([http://www.](http://www.openid.net/)[openid](http://www.openid.net/)[.net](http://www.openid.net/))
for a more complete list.

The obvious benefit of OpenID for the user is that he or she no longer
has to register for a new account on every site in which he or she wants
to participate. Users can register on *one*site (the OpenID provider's
site) and then use those credentials to authenticate to
anyOpenID-supported web site. Many web site owners struggle to build
communities because end users are reluctant to register for so many
different accounts. Supporting OpenID makes it easier for site owners to
build their communities because the barriers to participating (i.e., the
effort it takes to register for and keep track of many accounts) are
removed. All of the account information is kept with the OpenID
provider, making it much easier to manage this information and keep it
up to date.

The OpenID link is the first of three links available directly under the
*Sign In* button. Social Office's integration with OpenID allows users
to log in using their OpenID identifier from their preferred OpenID
provider instead of having to create a brand new login for Social
Office.

#### Create An Account

The second link is for account creation. If a new user wants to log in
and doesn't already have login credentials, he or she would use this
link.. Note that administrators can disable this function, thus
requiring administrators to create all accounts.

#### Forgot Password

The last is the Forgot Password link. This one does pretty much what it
says: if a user has login credentials but can't remember his or her
password, the user clicks this link to submit his or her user name and
password reminder and a new password is sent to their email address on
file. This is just one of the many email notifications that Social
Office sends to users, and is one of the reasons that setting up the
mail server properly is so important.

## Navigating Social Office

Upon first logging in to Social Office, you'll notice that you're
directed to your Home Page. Under your name at the top of the page
you'll see three options, *Home, Profile, and Mail.*

### Home

This is the user's home page. This is where users are directed each time
they log in to Social Office. It's here that users can find relevant
information on what's going on each day as well as track announcements,
activities, and tasks.

![image](../../images/02-getting-started_html_m4fb5e76.png)*Illustration 2: Personal
Home Page of the Admin user*

\

\
\

The left column contains the Sites portlet as well as the Contacts
portlet. The center column contains three aggregation portlets:
Announcements, Activities, and Tasks. The right column shows Today's
Events and Weather. You can remove each of these applications from your
home page by clicking the *x* in the upper right hand corner of the
application. To add an application back (or add a new application)
simply click the *Applications*tab at the top of the page. This is
located between *Control Panel*and *Layout.* You can also move the
applications to different locations on the page by selecting the title
of the application and dragging and dropping them onto a column of the
page.

You can also change the layout of the page if you don't like the default
three-column layout. To do this, click the *Layout* button at the top of
the page. Don't worry, we'll give you full details on layouts in the
next chapter.

That's the overall view of a user's personal home page. Let's dive
deeper and take a look at the functions of each application on this
page.

#### Sites

In Social Office, Sites are collections of Users who have a common
interest. Each site is pre-populated with collaboration pages allowing
you to quickly start collaborating with others. You can create a site
for any project or department that requires collaboration between users.
For example, a company could create a site specifically for the
marketing team, research team, or HR department. To drill down even
further, the marketing team could create sites for projects A, B, and C.
The great thing about Social Office is that as soon as a site is no
longer needed (say project A has been successfully launched) that site
can easily be deleted and a new site created for the next project.

This portlet is an aggregation of all of the sites to which a user
belongs. Initially, you will want to click *Add Site.*If you know a site
has already been added, you can click *Open Sites*to see a list of sites
that are available for you to join. To go back to our example, If a user
creates content in the site for project A, and is then re-assigned to
project B, the user simply leaves the project A site and joins the
project B site. All the content that this user created for project A
will remain in the project A site—and the user will now have access to
everything in project B. This allows him or her to immediately start
learning and contributing to the team.

#### Contacts

Here you'll find a list of all of your contacts within Social Office.
Contacts are people you added to your network within Social Office. To
add a contact, simply click the *Add Contact*button at the top right
corner of the portlet.

#### Announcements

The Announcements portlet lists all the announcements that are relevant
to you. An announcement is any message that a user wishes to share with
others in Social Office. Anyone can create an announcement. When
creating an announcement, you choose the scope, display, and expiration
dates. The scope is used to define who within social office receives the
announcement. This can be everyone, All Site Owners, or all users within
a specific site. As an example, this is great for project managers who
wish to share a message with every user within a specific site or for
Admins who have an important announcement for all users within Social
Office.

#### Activities

The Activities portlet aggregates all the recent activities that are
relevant to you. An activity is any change or update that a user made
while logged into Social Office. For example, if the admin of the
marketing site creates a wiki on how to interact with advertisers and
you're part of the Marketing site, then an activity is displayed
indicating that an event took place. In this application, activities are
scoped in three ways: by sites you belong to, by the activities of your
friends within Social Office, and by your own activities. The advantage
of this is that users can quickly see what's happened since they last
logged in to Social Office.

![image](../../images/02-getting-started_html_647e334e.png)*Illustration 3:
Activities Feed*

#### Tasks

Tasks are assignments that are in various stages of completion. They can
be assigned to specific people within Social Office. The Tasks portlet
allows you to view all of the tasks that others assigned to you, as well
as all the tasks that you assigned to others.

#### Today's Events

Today's Events will give you a quick overview of all of the public
events for each site that you belong to. These events are pulled from
the calendars of those sites. See the Calendar section in the *Sites*
chapter to learn about creating new events.

#### Weather

This lists the weather for cities that you choose. By default it lists
90210, Chicago, Frankfurt, and Rome. To change the default cities,
simply click the *Configuration* button and select *Preferences.*To
search for a city, type the desired city name or zip code into the
search box at the bottom of the portlet. Doing this will open up a new
window that displays the current weather for the city or zip code you're
searching.

### Profile

Clicking *Profile* will take you to your profile page. Your profile page
is basically a resume that lets other users in Social Office know your
contact information as well as different projects that you have worked
on. To edit this click *Edit Profile* and *Edit Projects* in the lower
left hand corner under your picture.

![image](../../images/02-getting-started_html_m3f09e75f.png)*Illustration 4: Profile*

\

\
\

#### Activities

This is the same as the Activities portlet on your home page. It
aggregates all of the recent activities for your sites, your friends,
and you. The benefit of having an activities feed on your profile page
is that when other users in Social Office navigate to your profile page,
they can see what you and your friends have been up to recently.

### Mail

Social Office offers a mail client. This is great because it means you
have the convenience of not having to log in to another server or use
another application to check and send your email. Currently, Social
Office can integrate with any mail server that uses the IMAP protocol,
as well as Google's GMail service.

![image](../../images/02-getting-started_html_m60ff14df.png)*Illustration 5: Default
view of Social Office's email client.*

\

In order to check and receive email in Social Office you'll need to
configure the mail portlet settings. This is where you'll configure your
email accounts. To start click *Add Mail Account*. Next choose *Gmail
Account*or *Custom Mail Account*and follow the directions. After you
have successfully added an email account, when navigating to the mail
page, you'll be presented with the option to *Check Your Email* or
C*onfigure Email Accounts.*

Social Office also provides a global navigation area at the top right of
the screen. This is one way to navigate away from your personal page to
other areas of Social Office.

## My Sites

This shows you a list of all of the Sites which you're currently a
member of. Should you click on one of the sites you'll be redirected to
the home page for that particular site. This is a quick way to navigate
to any site of which you're a member.

## The Control Panel

The Control Panel takes you to the administrative functions of Social
Office. Only Administrators will have access to the Control Panel.

### Navigating the Control Panel

The Control Panel is very easy to navigate. On the left side is a list
of headings with functions underneath them. The headings are in
alphabetical order, but the functions are in a logical order.

**User Name:** The first section is always the logged in user's personal
space. Here, you can change your account information.

**Portal:** The Portal section allows portal administrators to set up
and maintain the portal. This is where you can add and edit users,
roles, and configure the settings of the portal.

**Server:** The Server section contains administrative functions for
configuring portal instances and more.

![image](../../images/02-getting-started_html_75868b7c.png)*Illustration 6: Control
Panel*

\

All of the functions that you'll need to maintain social office can be
found in the control panel.

### Using the Control Panel

The Portal section of the Control Panel is used for most administrative
tasks. This is where you'll find the interface for the creation and
maintenance of

-   **Users:**These are individuals in Social Office who are
    representative of actual people. They are assigned a screen name and
    a password in order to login to Social Office.

-   **Roles:**Roles are groupings of users that share a particular
    function within Social Office according to a particular scope. In
    Social Office there are two scopes that roles can take. The first is
    a site role. This is a role that has permissions that are applicable
    only within the site that it's assigned to, such as the Marketing
    Site. The second is a regular role. The permissions associated with
    this role are effective throughout social office and not just within
    one particular site.

Additionally, it allows you to configure many server settings,
including:

-   **Password Policies:** If you (as an admin) have certain password
    requirements, a password policy is where you would enforce this.
    Some examples of password policy settings are allowing a user to
    change his own password, preventing reuse of old passwords, enabling
    password expiration after a specified time, and enabling account
    lockout after a certain number of failed logins. These are just some
    of the settings that can be set and managed with a password policy.

-   Authentication options, including Single Sign-On and LDAP
    integration. Social Office currently supports several single sign-on
    solutions including, LDAP, CAS, NTLM, OpenID, Open SSO, and
    SiteMinder. Social Office also supports any LDAP directory including
    but not limited to Apache Directory, Fedora Directory, Microsoft
    Active Directory, Novell eDirectory and OpenLDAP.

-   **Default User Associations:** There are two types of default user
    associations in Social Office: sites and roles. If you want every
    newly created user associated with a particular site you would list
    the name of that site under Sites. If you want every newly created
    user associated with a particular role you would list the name of
    that role under Roles.

-   **Reserved Screen names:** Allows Social Office admins to set aside
    certain screen names that they don't want users to use. This is
    beneficial if you want to restrict users from creating screen names
    that contain bad words for example.

-   **Mail Host Names:**You have already configured one mail host for
    sending notifications. You can optionally specify other mail hosts
    for sending mail in case the main one you configured becomes
    unavailable.

-   **Email Notifications:** There are two types of email notifications
    that you can configure. Account Created Notification will alert a
    user that an account was created in Social Office using their email
    address. You can customize the message that's sent to the newly
    created user. The Password Changed Notification lets a Social Office
    user know when their password is changed and what it's changed to.
    Just as with the Account Created Notification, you can customize the
    message that's sent to the user.

You'll use the Portal section of the Control Panel to create your
structure, implement security, and administer your users. Please see
Chapter 4 for an exhaustive view of the Control Panel.

## Summary

Liferay Social Office provides an easy to install platform that gets you
up and running very quickly. It requires minimal configuration, and in
fact needs only a connection to a database and a mail server to become
fully operational. Once you have it installed, it presents to the user a
very clear and consistent interface which is intuitive. Using this
interface, you can navigate to any area in Social Office you need to set
up the Sites and enable your users to get their work done.
