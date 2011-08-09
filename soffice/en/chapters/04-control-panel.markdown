# Control Panel

Once Liferay Social Office is successfully installed, you can begin
configuring it to fit it to your environment and your particular
collaboration needs. You can perform many of these configuration tasks
through Social Office's control panel.

You'll want to customize Social Office by configuring various settings
such as email notifications, integration with services such as LDAP,
creating users, user groups, and roles, and readying your installation
to have its content and applications loaded by users. This chapter
covers the following activities:

-   *Social Office's User Interface:* How to navigate around Social
    Office and make use of the Control Panel.

-   *Social Office Administration:* How to administer a Social Office
    install.

-   *Global Settings:* Password policies, Settings, Monitoring, and
    more.

## Navigating the Control Panel

The control panel is very easy to navigate. On the left side is a list
of headings with functions underneath them. The headings are in
alphabetical order, but the functions are in a logical order.

**User Name:** The first section is always the logged in user's personal
space. Here, you can change your account information and manage your own
personal pages.

**Portal:** The Portal section allows administrators to set up and
maintain Social Office. This is where you can add and edit users, sites,
roles, and configure the server settings.

**Server:** The Server section contains administrative functions for
configuring portal instances, runtime settings, plugins, connecting
Social Office to OpenOffice.org for document conversion, and more.

All of the functions that you'll need to maintain Social Office or its
content is found in the Control Panel.

## Social Office Architecture

Before we dive into the user interface for adding and maintaining
various resources, it's best to go over the concepts Social Office uses
to organize itself.

### Users

Users are accounts on the system which are accessed by real people.
Users can be collected in multiple ways. They can be members of sites
which draw together common interests. They can have roles which describe
their functions in the system, and these roles can be scoped by Portal
or Site. They can also be collected into User Groups.

### Roles

There are two kinds of roles:

-   Portal Roles

-   Site Roles

These are called role *scopes*. Roles are used to define permissions
across their scope: across the portal or across a site. For example,
consider a role which grants access to create a Message Board category.
A Portal role would grant that access across the portal, wherever there
was a Message Board portlet. A Site role would grant that access only
within a single site.

Because Roles are used strictly for security, they also don't have
pages, like Sites.

Users or Sites can be members of a role.

### Sites

Sites are collections of Users who have a common interest. There are two
types of Sites:

-   Open

-   Private

An Open Site (the default) allows Social Office users to join and leave
the Site whenever they want to, using the Control Panel or a Sites
portlet added to a page to which they have access. A Private site
requires that users be added to the Site by a site administrator and
doesn't show up at all in the Sites portlet or the Control Panel.

## Using the Control Panel

The **Portal** section of the Control Panel is used for most
administrative tasks. You'll find there an interface for the creation
and maintenance of

-   Users

-   Roles

Additionally, it allows you to configure many server settings,
including:

-   Password Policies

-   Authentication options, including Single Sign-On and LDAP
    integration

-   Default User Associations

-   Reserved Screen Names

-   Mail Host Names

-   Email Notifications

You will use the Portal section of the Control Panel to create your
portal structure, implement security, and administer your users. Note
that only users with the Administrator role—a portal scoped role—have
permission to view this section of the Control Panel. You can, of
course, grant permissions to roles that you create yourself.

### Adding Users

Let's begin by adding a user account for yourself. We will then
configure this account so that it has the same administrative access as
the default administrator account. Go up to the global navigation area
and click the *Control Panel* link, if you aren't there already. Then
under the *Portal* category, click *Users*. Click the *Add*button.

You'll then be presented with the Add User form. Fill out the form using
your name and email address. When you're finished, click *Save.*

The page will then reappear with a message saying that the save was
successful, and there will now be an expanded form which allows you to
fill out a lot more information about the user. You don't have to fill
anything else out right now, but one thing is important to note: when
the user ID was created, a password was automatically generated and, if
Social Office has been correctly installed (see Chapter 2), an email
message with the password in it was sent to the user. This of course
requires that Social Office can properly communicate with your SMTP mail
server in your organization.

If you have not yet set up your mail server, you'll need to use this
screen to change the default password for the user ID to something you
can remember. You can do this by clicking on the *Password* link in the
box on the right, entering the new password in the two fields, and
clicking *Save*.

![image](../../images/04-control-panel_html_6fd46529.png)*Illustration 21: The Add
User screen.*

\

Next, you'll want to give your user account the same administrative
rights as the default administrator's account. This will allow you to
perform administrative tasks with your own ID instead of having to use
the default ID. This also allows you to make your portal more secure by
deleting or disabling the default ID.

Click the*Roles* link. You'll then be taken to a screen which shows the
roles to which your ID is currently assigned. By default, you should
have one role: *Power User*. By default, all users are also assigned the
Power User role. You can give this role certain permissions if you wish
or disable it altogether. You can also define the default roles a new
user receives; we will go over this later.

To make yourself an Administrator, click the *Select* link. A window
will pop up with a list of all the roles in the system. Select the
*Administrator* role from the list and the window will disappear and
you'll see that the role has been added to the list of roles to which
you're assigned. Next, click the *Save* button, which is at the bottom
of the blue bar of links on the right. You're now an administrator of
the portal. Log out of the portal and then log back in with your own
user ID.

### User Management

If you click the *Users* link on the left of the Control Panel, you'll
see that there are now two users in the list of users. If you wanted to
change something about a particular user, you can click the *Actions*
button next to that user.

**Edit User:** This takes you back to the Edit User page, where you can
modify anything about the user.

**Permissions:** This allows you to define which Roles have permissions
to edit the user.

**Impersonate User:** This opens another browser window which allows you
to browse the site as though you were the user.

**Deactivate:** Clicking this will deactivate the user's account.

Note that most users can't perform most of the above (in fact, they
won't even have access to this section of the Control Panel). Because
you have administrative access, you can perform all of the above
functions.

### Roles

Roles are groupings of users that share a particular function within
Social Office, according to a particular scope. Roles can be granted
permissions to various functions within portlet applications. Think of a
role as a description of a function, such as Message Board

Administrators. A role with that name is likely to have permissions to
functions of the Message Board portlet delegated to it. Users who are
placed in this role then inherit those permissions.

Roles are scoped by Portal or Site. The Control Panel makes it easy for
you to assign users to Roles and to assign permissions to Roles. You
only have to go to one place: the *Roles* link. From there, you can add
roles scoped by Portal or Site from one interface.

To create a Role, click the *Roles* link, and then click the
*Add*button. Type a name for your role and an optional description. The
drop down box at the bottom of the form lets you choose whether this is
a Regular or Site role. When you have finished, click *Save*.

You'll be back at the list of roles. To see what functions you can
perform on your new role, click the *Actions* button.

**Edit:** Click this action to edit the role. You can change its name or
description.

**Permissions:** This allows you to define which Users, User Groups, or
Roles have permissions to edit the Role.

**Define Permissions:** Click this to define what permissions this role
has. This is outlined in the next section.

**Assign Members:**Takes you to a screen where you can search and select
users in the portal to be assigned to this role. These users will
inherit any permissions given to the role.

**View Users:** Lets you view the users who are in the Role.

**Delete:** Deletes the Role.

![image](../../images/04-control-panel_html_37f4f880.png)*Illustration 22: Defining
Permissions on a Role.*

\

\
\

#### Defining Permissions on a Role

Roles exist as a bucket for granting permissions to the users who are
members of them. So one of the main tasks you'll be doing with a role is
granting it the permissions that you want members of the role to have.

When you click the *Define Permissions* action on a Portal scoped Role,
you're given a choice of two kinds of permissions that can be defined
for this role: *Portal Permissions* and *Portlet Permissions*. For other
Roles, you only see the portlet permissions.

Portal permissions cover portal-wide activities that are in several
categories, such as Site, Location, Password Policy, etc. This allows
you to create a Role that, for example, can create new Sites in the
portal. This would allow you to grant users that particular permission
without making them overall portal administrators.

Portlet permissions cover permissions that are defined within various
portlets. Since Social Office inherits from the design of Liferay
Portal, its applications are referred to in the Control Panel as
portlets. If you're familiar, therefore, with Liferay Portal,
administering Social Office is the same exact experience. Clicking the
*Portlet Permissions* button brings you to a page where you can browse
the names of the portlets that are currently installed in your portal.
Once you choose a portlet, you can then define the actions within this
portlet that the role will have permission to perform.

![image](../../images/04-control-panel_html_757b6245.png)*Illustration 23: Message
Boards permissions.*

\

\
\

If we stick with our example of a *Message Boards Admin* role, we would
then find the **Message Boards** portlet in the list and click on it. A
new page with configurable permissions would then be displayed (see
above).

Each possible action to which permissions can be granted is listed. To
grant a permission, choose the scope of the permission. You have two
choices: *Portal* and *Communities*. These labels are inherited from
Liferay Portal, but they correspond to global Social Office permissions
and Site permissions. Granting Portal permissions means that permission
to the action is granted across Social Office, in any site where there
is a Message Boards portlet.

If you choose Communities, a button appears next to the permission
allowing you to choose one or more sites in which the permission is
invalid. This lets you pick and choose specific sites (for a portal
scoped role) in which these permissions are valid for users in this
role.

Once you have chosen the permissions granted to this role, click *Save*.
For a Message Boards Admin role, you would likely grant Portal
permissions to every action listed. After you click *Save*, you'll see a
list of all permissions that are currently granted to this role. From
here, you can add more permissions (by clicking *Add Portlet
Permissions* or *Add Portal Permissions*), or go back by clicking a link
in the breadcrumb list or the *Return to Full Page* link.

Roles are very powerful, and allow portal administrators to define
various permissions in whatever combinations they like.

## Global Server Settings

Now that you've navigated in the Control Panel, you should be pretty
familiar with how it works, and hopefully now you're comfortable
exploring around the various options on the left. We've focused so far
on the maintenance of users and portal security. The remaining links in
the *Portal* category focus on various portal settings which cover how
Social Office operates and integrates with other systems you may have.

### Password Policies

Password policies can help to enhance the security of Social Office.
Using password policies, you can set password rules such as password
strength, frequency of password expiration, and more. Additionally, you
can apply different rule sets to different sets of portal users.

In the Control Panel, click on the *Password Policies* link on the left
side of the screen in the *Portal*category. You'll see that there is
already a default password policy in the system. You can edit this in
the same manner as you edit other resources in the portal: click
*Actions* and then click *Edit*.

You'll then see the Password Policy settings form:

**Changeable:** Selects whether a user can change his or her password.

**Change Required:** Selects whether a user must change his or her
password upon first log in.

**Minimum Age:** You can choose how long a password must remain in
effect before it can be changed.

**Syntax Checking:** Allows you to choose whether dictionary words can
be in passwords as well as the minimum password length.

**Password History:** Keeps a history (with a defined length) of
passwords and won't allow users to change their passwords to one that
was previously used.

**Password Expiration:** Lets you choose an interval where passwords can
be active before they expire. You can select the age, the warning time,
and a grace limit.

**Lockout:** Allows you to set the number of failed log in attempts
before a user's account becomes locked. You can choose whether an
administrator needs to unlock the account or if it becomes unlocked
after a specific duration.

From the list of password policies, you can perform several other
actions.

**Edit:** Brings you to the form above and allows you to modify the
password policy.

**Permissions:** This allows you to define which Users, User Groups, or
Roles have permissions to edit the Password Policy.

**Assign Members:** Takes you to a screen where you can search and
select users to be assigned to this password policy. The password policy
is enforced for any users who are added here.

**Delete:** This shows up for any password policies that you add beyond
the default policy. You cannot delete the default policy.

### Settings

The Settings link is where most of the global portal settings are.

**General:** This lets you configure global settings, such as the
company name, domain, the virtual host, a global portal logo, and more.

**Authentication:** Allows you to configure log in IDs, connection to
LDAP, and Single Sign-On.

**Users:** Has three tabs, labeled *Fields*, *Reserved Credentials*, and
*Default User Associations*. The Fields tab enables or disables some
user fields, such as birthday or terms of use. The Reserved Credentials
tab lets you reserve screen names and email addresses so that users
cannot register using them. You might use this to prevent users from
registering on the portal with user names that contain profanity or that
sound official, such as *admin* or *president*. The Default User
Associations tab lets you configure default membership to Roles, User
Groups, and Communities for new users, and provides a check box which
allows you to retroactively apply these to existing users.

**Mail** **Host Names:** You can add a list of other mail host names
that are associated with your organization. For example, your main
domain might be `mycompany.com`{.western}, but you might use
`mycompany-marketing.com`{.western} for your email newsletters. Any
domain names associated with your organization can go here.

**Email** **Notifications:** Social Office sends email notifications for
certain events, such as user registrations, password changes, etc. You
can customize those messages here.

We will go over these settings in detail below.

### General

The *General* link allows you to set the name of the company / site
which is running the portal.

### Authentication: General Settings

The *Authentication* link has several tabs under it. All of these are
used for configuring how users will authenticate to Social Office.
Because Social Office supports a number of authentication methods, there
are settings for each.

The general settings affect only Social Office functionality, and don't
have anything to do with any of the integration options on the other
tabs. This tab allows you to customize Social Office's out-of-box
behavior regarding authentication. Specifically, the *General* tab
allows you to select from several global authentication settings:

-   Authenticate via email address (default), screen name, or user ID (a
    numerical ID auto-generated in the database—not recommended).

-   Enable / Disable automatic login. If enabled, Social Office allows
    users to check a box which will cause the site to “remember” the
    user's log in by placing a cookie on his or her browser. If
    disabled, users will have to log in manually.

-   Enable / Disable Forgot Password functionality.

-   Enable / Disable account creation by strangers. If you're running an
    Internet site, you'll probably want to leave this on so that
    visitors can create accounts on your site. For internal sites, you
    may want to turn this off so that accounts will have to be created
    by administrators.

-   Enable / Disable account creation by those using an email address in
    the domain of the company running the site (which you just set on
    the General tab).

-   Enable / Disable email address verification. If you enable this,
    Social Office, will send users a verification email with a link back
    to the portal to verify the email address they entered is a valid
    one they can access.

By default, all settings except for the last are enabled by default. One
default that's important is that users will authenticate by their email
address. Social Office defaults to this for several reasons:

1.  An email address is, by definition, unique to the user who owns it.

2.  People can generally remember their email addresses. If you have a
    user who hasn't logged into the portal for a while, it's possible
    that he or she will forget his or her screen name, especially if the
    user was not allowed to use his or her screen name of choice
    (because somebody else already used it).

3.  If a user changes his or her email address, if it's not used to
    authenticate, it's more likely that the user will forget to update
    his or her email address in his or her profile. If the user's email
    address is not updated, all notifications sent by the portal will
    fail to reach the user. So it's important to keep the email address
    at the forefront of a user's mind when he or she logs in to help the
    user keep it up to date.

For these reasons, Social Office defaults to using the email address as
a user name.

### Authentication: LDAP

Connecting Social Office to an LDAP directory is a straightforward
process through the Control Panel. Configuring LDAP in the control panel
will allow you to setup multiple LDAP servers.

The LDAP settings screen is very detailed, so we will look at it in
chunks.

#### Global Values

There are two global LDAP settings.

**Enabled:**Check this box to enable LDAP Authentication.

**Required:** Check this box if LDAP authentication is required. Social
Office will then not allow a user to log in unless he or she can
successfully bind to the LDAP directory first. Uncheck this box if you
want to allow users that have Social Office accounts but no LDAP
accounts to log in to the portal.

Managing LDAP servers in Social Office is done by adding LDAP server
configurations. To add an LDAP server for Social Office to use, click on
the Add button. Here you’ll be presented with configuration options that
will allow Social Office to communicate with your LDAP server—these are
described in detail below. After you have configured some LDAP servers
for Social Office to use, you’ll notice that you can also edit, delete
and order your servers in the list. Be mindful of how you order them
because Social Office will attempt to authenticate to LDAP from your
list of servers in order from top to bottom.

Furthermore, the first LDAP server is considered your default LDAP
server; any interactions where a user cannot be matched to one of the
servers in the list—such as exporting a new user—will occur on the first
LDAP server.

##### Default Values

Several leading directory servers are listed here. If you're using one
of these, select it and the rest of the form is populated with the
proper default values for that directory.

##### Connection

These settings cover the basic connection to LDAP.

**Base Provider URL:** This tells the portal where the LDAP server is
located. Make sure that the machine on which Social Office is installed
can communicate with the LDAP server. If there is a firewall between the
two systems, check to make sure that the appropriate ports are opened.

**Base DN:**This is the Base Distinguished Name for your LDAP directory.
It's usually modeled after your organization. For a commercial
organization, it may look something like: *dc=companynamehere,dc=com.*

**Principal:** By default, the administrator ID is populated here. If
you have removed the default LDAP administrator, you'll need to use the
fully qualified name of the administrative credential you do use. You
need an administrative credential because Social Office is using this ID
to synchronize user accounts to and from LDAP.

**Credentials:** This is the password for the administrative user.

This is all you need in order to make a regular connection to an LDAP
directory. The rest of the configuration is optional: generally, the
default attribute mappings are sufficient data to synchronize back to
the Social Office database when a user attempts to log in. To test the
connection to your LDAP server, click the *Test LDAP Connection* button.

If you're running your LDAP directory in SSL mode to prevent credential
information from passing through the network unencrypted, you'll have to
perform extra steps to share the encryption key and certificate between
the two systems.

For example, assuming your LDAP directory is Microsoft Active Directory
on Windows Server 2003, you would take the following steps to share the
certificate:

On the Windows 2003 Domain Controller, open the *Certificates* MMC
snapin. Export the Root Certificate Authority certificate by selecting
*Certificates (Local Computer) mmc snapin -\> Trusted Root Certification
Authorities -\> MyRootCACertificateName.*Right click this certificate
and select *All Tasks* -\> *export* -\> *select DER encoded binary X.509
.CER*. Copy the exported *.cer* file to your Social Office server.

As with the CAS install (see the below section entitled **Single
Sign-On**), you'll need to import the certificate into the *cacerts
keystore*. The import is handled by a command like the following:

keytool -import -trustcacerts -keystore
/some/path/jdk1.5.0\_11/jre/lib/security/cacerts -storepass changeit
-noprompt

-alias MyRootCA -file /some/path/MyRootCA.cer

The *keytool* utility ships as part of the Java SDK.

Once this is done, go back to the LDAP page in the Control Panel. Modify
the LDAP URL in the **Base DN**field to the secure version by changing
the protocol to *https* and the port to 636 like the following:

ldaps://myLdapServerHostname:636

Save the changes. Your Social Office server will now use LDAP in secure
mode for authentication.

##### Users

This section contains settings for finding users in your LDAP directory.

**Authentication Search Filter:**The search filter box can be used to
determine the search criteria for user logins. By default, Social Office
uses the email address as a user login name. If you have changed this
setting—which can be done on the *General* tab that's next to the *LDAP*
tab in the *Settings-\>Authentication* section of the Control
Panel—you'll need to modify the search filter here, which has by default
been configured to use the email address attribute from LDAP as search
criteria. For example, if you changed Social Office's authentication
method to use the screen name instead of the email address, you would
modify the search filter so that it can match the entered login name:

(cn=@screen\_name@)

**Import Search Filter:**Depending on the LDAP server, there are
different ways to identify the user. Generally, the default setting
(*objectClass=inetOrgPerson*) is fine, but if you want to search for
only a subset of users or users that have different object classes, you
can change this.

**User Mapping:**The next series of fields allows you to define mappings
from LDAP attributes to Social Office fields. Though your LDAP user
attributes may be different from LDAP server to LDAP server, there are
five fields that Social Office requires to be mapped in order for the
user to be recognized. You must define a mapping to the corresponding
attributes in LDAP for the following Social Office fields:

-   Screen Name

-   Password

-   Email Address

-   First Name

-   Last Name

The Control Panel provides default mappings for commonly used LDAP
attributes. You can also add your own mappings if you wish.

**Test LDAP Users:** Once you have your attribute mappings set up (see
above), click the *Test LDAP Users* button, and Social Office will
attempt to pull LDAP users and match them up with their mappings as a
preview.

![image](../../images/04-control-panel_html_3312b71c.jpg)\
*Illustration 24: Testing LDAP Users*

##### **Groups**

This section contains settings for mapping LDAP groups to Social Office.

**Import Search Filter:** This is the filter for finding LDAP groups
that you want to map to Social Office. Enter the LDAP group attributes
that you want retrieved for this mapping. The following attributes can
be mapped:

-   Group Name

-   Description

-   User

**Test LDAP Groups:** Click the *Test LDAP Groups* to display a list of
the groups returned by your search filter.

![image](../../images/04-control-panel_html_e5994f7.jpg)\
*Illustration 25: Mapping LDAP Groups.*

##### Import/Export

**Users DN:** Enter the location in your LDAP tree where the users are
stored. When Social Office does an export, it will export the users to
this location.

**User Default Object Classes:** When a user is exported, the user is
created with the listed default object classes. To find out what your
default object classes are, use an LDAP browser tool such as *JXplorer*
to locate a user and view the Object Class attributes that are stored in
LDAP for that user.

**Groups DN:** Enter the location in your LDAP tree where the groups are
stored. When Social Office does an export, it will export the groups to
this location.

Once you have completed configuring LDAP, click the *Save* button. This
will take you back to the General tab under Settings. Click the *LDAP*
tab to get back to the LDAP settings, where you can define the global
LDAP settings as shown below.

##### Global LDAP Options

**Use LDAP Password Policy:** Social Office uses its own password policy
by default. This can be configured on the *Password Policies* link in
the *Portal* section on the left side of the Control Panel. If you want
to use the password policies defined by your LDAP directory, check this
box. Once this is enabled, the *Password Policies* tab will display a
message stating that you're not using a local password policy. You'll
now have to use your LDAP directory's mechanism for setting password
policies. Social Office does this by parsing the messages in the LDAP
controls that are returned by your LDAP server. By default, the messages
in the LDAP controls that Social Office is looking for are the messages
that are returned by the Fedora Directory Server. If you're using a
different LDAP server, you'll need to customize the messages in Social
Office's *portal-ext.properties* file, as there is not yet a GUI for
setting this. See below for instructions describing how to do this.

**Import Enabled:** Check this box to cause Social Office to do a mass
import from your LDAP directories. If you want Social Office to only
synchronize users when they log in, leave this box unchecked. Definitely
leave this unchecked if you're working in a clustered environment.
Otherwise, all of your nodes would try to do a mass import when each of
them starts up.

If you check the box, several other options become available.

**Import on Startup Enabled:** Check this box to have Social Office run
the import when it starts up. This box only appears if you check *Import
Enabled* above.

**Export Enabled:**Check this box to enable Social Office to export user
accounts from the database to LDAP. Social Office uses a listener to
track any changes made to the User object and will push these changes
out to the LDAP server whenever the User object is updated. Note that by
default on every login, fields such as `LastLoginDate`{.western} are
updated. When export is enabled, this has the effect of causing a user
export every time the user logs in. You can disable this by setting the
following property in your *portal-ext.properties* file:

users.update.last.login=false

##### LDAP Options Not Available in the GUI

Though most of the LDAP configuration can be done from the Control
Panel, there are several configuration parameters that are only
available by editing *portal-ext.properties*. These options are
available in the GUI in future versions of Social Office, but for now
they can only be configured by editing the properties file.

If you need to change any of these options, copy the LDAP section from
the *portal.properties* file into your *portal-ext.properties* file.
Note that since you have already configured LDAP from the GUI, any
settings from the properties file that match settings already configured
in the GUI is ignored. The GUI, which stores the settings in the
database, always takes precedence over the properties file.

ldap.auth.method=bind

\#ldap.auth.method=password-compare

Set either bind or password-compare for the LDAP authentication method.
Bind is preferred by most vendors so that you don't have to worry about
encryption strategies. Password compare does exactly what it sounds
like: it reads the user's password out of LDAP, decrypts it, and
compares it with the user's password in Social Office, syncing the two.

ldap.auth.password.encryption.algorithm=

ldap.auth.password.encryption.algorithm.types=MD5,SHA

Set the password encryption to used to compare passwords if the property
*ldap.auth.method* is set to password-compare.

ldap.import.method=[user,group]

If you set this to *user,*Social Office will import all users from the
specified portion of the LDAP tree. If you set this to *group*, Social
Office will search all the groups and import the users in each group. If
you have users who don't belong to any groups, they will not be
imported.

ldap.error.password.age=age

ldap.error.password.expired=expired

ldap.error.password.history=history

ldap.error.password.not.changeable=not allowed to change

ldap.error.password.syntax=syntax

ldap.error.password.trivial=trivial

ldap.error.user.lockout=retry limit

These properties are a list of phrases from error messages which can
possibly be returned by the LDAP server. When a user binds to LDAP, the
server can return *controls* with its response of success or failure.
These controls contain a message describing the error or the information
that's coming back with the response. Though the controls are the same
across LDAP servers, the messages can be different. The properties
described here contain snippets of words from those messages, and will
work with Red Hat's Fedora Directory Server. If you're not using that
server, the word snippets may not work with your LDAP server. If they
don't, you can replace the values of these properties with phrases from
your server's error messages. This will enable Social Office to
recognize them.

#### Single Sign-On

Single Sign-On solutions allow you to provide a single log in credential
for multiple systems. This allows you to have people authenticate to the
Single Sign-On product and they are automatically logged in to Social
Office and to other products as well.

Social Office at the time of this writing supports several single
sign-on solutions. Of course if your product is not yet supported, your
organization can choose to sponsor support for it. Please contact
[sales@](mailto:sales@liferay.com)[liferay](mailto:sales@liferay.com)[.com](mailto:sales@liferay.com)
for more information about this.

#### Authentication: Central Authentication Service (CAS)

CAS is an authentication system that was originally created at Yale
University. It's a widely-used open source single sign-on solution, and
was the first SSO product supported by Social Office.

Please follow the documentation for CAS to install it on your
application server of choice.

Your first step is to copy the CAS client .jar file to Social Office's
library folder. In the bundle, this is in [Social Office
Home]/tomcat-[version]*/webapps/ROOT/WEB-INF/lib*. Once you've done
this, the CAS client is available to Social Office the next time you
start it.

The CAS Server application requires a properly configured Secure Socket
Layer certificate on your server in order to work. If you wish to
generate one yourself, you'll need to use the *keytool* utility that
comes with the JDK. Your first step is to generate the key. Next, you
export the key into a file. Finally, you import the key into your local
Java key store. For public, Internet-based production environments,
you'll need to either purchase a signed key from a recognized
certificate authority (such as Thawte or Verisign) or have your key
signed by a recognized certificate authority. For Intranets, you should
have your IT department pre-configure users' browsers to accept the
certificate so that they don't get warning messages about the
certificate.

To generate a key, use the following command:

keytool -genkey -alias tomcat -keypass changeit -keyalg RSA

Instead of the password in the example (*changeit*), use a password that
you can remember. If you're not using Tomcat, you may want to use a
different alias as well. For First and Last name, enter *localhost,* or
the host name of your server. It cannot be an IP address.

To export the key to a file, use the following command:

keytool -export -alias tomcat -keypass changeit -file server.cert

Finally, to import the key into your Java key store, use the following
command:

keytool -import -alias tomcat -file %FILE\_NAME% -keypass changeit
-keystore $JAVA\_HOME/jre/lib/security/cacerts

If you're on a Windows system, replace *$JAVA\_HOME* above with
*%JAVA\_HOME%*. Of course, all of this needs to be done on the system on
which CAS is running.

Once your CAS server is up and running, you can configure Social Office
to use it. This is a simple matter of navigating to the *Settings* -\>
*Authentication* -\> *CAS* tab in the Control Panel. Enable CAS
authentication, and then modify the URL properties to point to your CAS
server.

**Enabled:** Set this to true to enable CAS single sign-on.

**Import****from LDAP:** A user may be authenticated from CAS and not
yet exist in the portal. Select this to automatically import users from
LDAP if they don't exist in the portal.

The rest of the settings are various URLs, with defaults included.
Change *localhost* in the default values to point to your CAS server.
When you're finished, click *Save*. After this, when users click the
*Sign In* link from the Dock, they are directed to the CAS server to
sign in to Social Office.

#### Authentication: Facebook

Social Office also enables users to log in using their Facebook
accounts. To enable this feature, you simply need to select the *Enable*
box and enter the Application ID and Application Secret which should
have been provided to you by Facebook. Facebook SSO works by taking the
primary Facebook email address and searching for the same email address
in Social Office's `User_`{.western} table. If a match is found, the
user is automatically signed on (provided that user clicked *allow* from
the Facebook dialog). If there isn't a match, the user is prompted in
Social Office to add a user from Facebook. Once selected, a new user is
created by retrieving four fields from Facebook (first name, last name,
email address, and gender).

#### Authentication: NTLM

NTLM is a Microsoft protocol that can be used for authentication through
Microsoft Internet Explorer. Though Microsoft has adopted Kerberos in
modern versions of Windows server, NTLM is still used when
authenticating to a workgroup.

**Enabled:** Check the box to enable NTLM authentication.

**Domain Controller:** Enter the IP address of your domain controller.
This is the server that contains the user accounts you want to use with
Social Office.

**Domain:** Enter the domain / workgroup name.

#### Authentication: OpenID

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

The obvious main benefit of OpenID for the user is that he or she no
longer has to register for a new account on every site in which he or
she wants to participate. Users can register on *one*site (the OpenID
provider's site) and then use those credentials to authenticate to many
web sites which support OpenID. Many web site owners often struggle to
build sites because end users are reluctant to register for so many
different accounts. Supporting OpenID makes it easier for site owners to
build their sites because the barriers to participating (i.e., the
effort it takes to register for and keep track of many accounts) are
removed. All of the account information is kept with the OpenID
provider, making it much easier to manage this information and keep it
up to date.

Social Office can act as an OpenID consumer, allowing users to
automatically register and sign in with their OpenID accounts.
Internally, the product uses OpenID4Java
([http://code.google.com/p/](http://code.google.com/p/openid4java/)[openid](http://code.google.com/p/openid4java/)[4java/](http://code.google.com/p/openid4java/))
to implement the feature.

OpenID is enabled by default in Social Office, but can be disabled on
this tab.

#### Authentication: OpenSSO

OpenSSO is an open source single sign-on solution that comes from the
code base of Sun's System Access Manager product. Social Office
integrates with OpenSSO, allowing you to use OpenSSO to integrate Social
Office into an infrastructure that contains a multitude of different
authentication schemes against different repositories of identities.

You can set up OpenSSO on the same server as Social Office or a
different box. Follow the instructions at the OpenSSO site
([http://](https://opensso.dev.java.net/)[opensso](https://opensso.dev.java.net/)[.dev.java.net](https://opensso.dev.java.net/))
to install OpenSSO. Once you have it installed, create the Social Office
administrative user in it. Users are mapped back and forth by screen
names. By default, the Social Office administrative user has a screen
name of *test*, so in OpenSSO, you would register the user with the ID
of *test* and an email address of
*[test@](mailto:test@liferay.com)[liferay](mailto:test@liferay.com)[.com](mailto:test@liferay.com)*.
Once you have the user set up, log in to Open SSO using this user.

In the same browser window, go to the URL for your server running Social
Office and log in as the same user, using the email address
*[test@](mailto:test@liferay.com)[liferay](mailto:test@liferay.com)[.com](mailto:test@liferay.com)*.
Go to the Control Panel and click *Settings -\> Authentication -\>
OpenSSO*. Modify the three URL fields (Login URL, Logout URL, and
Service URL) so that they point to your OpenSSO server (i.e., only
modify the host name portion of the URLs), click the *Enabled* check
box, and then click *Save.* Social Office will then redirect users to
OpenSSO when they click the *Sign In* link.

#### Authentication: SiteMinder

SiteMinder is a single sign-on implementation from Computer Associates.
Social Office now has built-in integration with SiteMinder. SiteMinder
uses a custom HTTP header to implement its single sign-on solution.

To enable SiteMinder authentication in Social Office, check the
*Enabled* box on the *SiteMinder* tab. If you're also using LDAP with
Social Office, you can check the *Import from LDAP* box. If this box is
checked, user authenticated from SiteMinder that don't exist in the
portal is imported from LDAP.

The last field defines the header SiteMinder is using to keep track of
the user. The default value is already populated. If you have customized
the field for your installation, enter the custom value here.

When you're finished, click *Save*.

#### Default User Associations

The *Default User Associations* link has three fields allowing you to
list (one per line) sites, roles, and user groups that you assign new
users by default. Social Office's default is to assign new users both
the Users role and the Power Users role.

The Power Users role allows used to allow users to have their own set of
pages. This is now provided to all users and can be modified using the
*portal-ext.properties* file. You can now use the Power Users role to
provide your own differentiation between regular users and users to whom
you wish to give more privileges in the portal, or you can remove it
altogether.

If you have defined other user groups, sites, or roles that you want
assigned to newly created users by default, enter them here. For
example, you may have defined page templates in certain user groups to
pre-populate end users' private pages. If there's a particular
configuration that you want everyone to have, you may want to enter
those user groups here.

#### Reserved Credentials

The next link is *Reserved Credentials*. You can enter screen names and
email addresses here that you don't want others to use. Social Office
will then prevent users from registering with these screen names and
email addresses. You might use this feature to prevent users from
creating IDs that look like administrative IDs or that have reserved
words in their names.

#### Mail Host Names

The next link is *Mail Host Names*. You can enter (one per line) other
mail host names besides the one you configured on the General tab.
Social Office will fail over to these host names in the event that the
connection to the main one fails.

#### Email Notifications

There are three tabs under the *Email Notifications* link. The *Sender*
tab allows you to set the portal administration name and email address.
By default, this is *Joe Bloggs* and
*[test@](mailto:test@liferay.com)[liferay](mailto:test@liferay.com)[.com](mailto:test@liferay.com)*.
You can change it to anything you want. This name and address will
appear in the From field in all email messages sent by the portal.

The other two tabs (*Account Created Notification* and *Password Changed
Notification*) allow you to customize the email messages that are sent
to users on those two events. A list of tokens for inserting certain
values (such as the portal URL) is given if you wish to use those.

#### Identification

The identification section has several links for addresses, phone
numbers, and other information you can configure in your portal. This
allows you to set up contact information for the organization that's
running the installation of Social Office.

#### Miscellaneous: Display Settings

This section allows you to set the default portal language and the time
zone. You can also set the site-wide logo which appears in the top left
corner of each page. Be careful when using this option to choose an
image file that fits the space. If you pick something that's too big, it
will mess up the navigation.

### Monitoring

The next link on the left side of the Control Panel is for monitoring.
Using this, you can see all of the live sessions in the portal. For
performance reasons, this setting is generally turned off in production,
but if you have it turned on, you can view the active sessions here.

### Server Administration

The Server Administration link lets you perform various tasks relating
to administration of the overall Social Office installation, as opposed
to administering resources in the site. Clicking the link makes this
abundantly clear: you're immediately presented with a graph showing the
resources available in the JVM.

#### Resources

The first tab is called *Resources*. This tab contains the
aforementioned graph plus several server wide actions that an
administrator can execute. These are:

-   **Garbage collection:** You can send in a request to the JVM to
    begin the garbage collection task.

-   **Clearing caches:** You can send in a request to the JVM to clear a
    single VM cache, the cluster cache, or the database cache.

-   **Reindex:** You can send in a request to regenerate all search
    indexes.

-   **Generate Thread Dump:** If you're performance testing, you can
    generate a thread dump which can be examined later to determine if
    there are any deadlocks and where they might be.

#### Log Levels

Here you can dynamically modify the log levels for any class hierarchy
in the portal. If you have custom code that you have deployed which
isn't in the list, you can use the *Add Category* tab to add it. If you
change the log level near the top of the class hierarchy (such as at
*com.liferay*), all the classes under that hierarchy will have their log
levels changed. If you're testing something specific, it's much better
to be as specific as you can when you change log levels, as by modifying
them too high in the hierarchy you can generate a lot more log messages
than you probably need.

#### System Properties

This tab shows an exhaustive list of system properties for the JVM, as
well as many Social Office system properties. This information can be
used for debugging purposes or to check the configuration of the
currently running portal.

#### Portal Properties

This tab shows an exhaustive list of the portal properties. These
properties are customizable, which you'll see in the next chapter. If
you need to check the current value of a particular property, it can be
viewed from this screen without having to shut down the portal or open
any properties files.

#### Shutdown

If you ever need to shut down your Social Office server while users are
logged in, you can use the Shutdown tab to inform your logged-in users
of the impending shutdown. You can define the number of minutes until
the shutdown and a custom message that is displayed.

Users will see your message at the top of their portal pages for the
duration of time you specified. When the time expires, all portal pages
will display a message saying the portal has been shut down. At this
point, restart the server to restore access.

#### OpenOffice

Social Office contains a Document Library application. This application
allows users to upload documents in many formats into a folder structure
that they define.

OpenOffice.org is an open source office suite which is normally run in
graphical mode to create documents, but it can be run in “server” mode.
When run in server mode, OpenOffice.org can be used to convert documents
to and from all of the file types it supports. Social Office's Document
Library application can make use of this feature to automatically
convert documents on the fly.

You would use this tab to tell Social Office how to connect to your
running instance of OpenOffice.org. You can install OpenOffice.org on
the same server upon which Social Office is running. Once you have it
installed, you can start OpenOffice.org in server mode with the
following command:

soffice -headless -accept="socket,host=127.0.0.1,port=8100;urp;"
-nofirststartwizard

As you can see, the command above specifies that OpenOffice.org will run
on port 8100, which is the default port in the Control Panel. If you can
use this port, all you need to do is check the *Enabled* box, and Social
Office is integrated with OpenOffice.org.

If you have something else running on this port, find a port that's open
and specify it both in the command above and on the Control Panel's
OpenOffice.org configuration page. When you're finished, click *Save*.

## Summary

We went in-depth through Social Office's Control Panel. Using the
Control Panel, we learned how to manage users and roles. We also learned
how to configure various server settings, such as authentication, LDAP
integration, and single sign-on. We also learned how to associate users
by default with different sites and roles, and we saw how to reserve
screen names and email addresses so that users cannot register in the
portal with them.

Next, we saw how to view and configure overall server settings. We saw
how to view the memory currently being used by the server, as well as
how to initiate garbage collection, a thread dump, search engine
re-indexing, and the clearing of various caches. We learned how to debug
parts of the portal by changing log levels, and by viewing the various
properties that are defined in the portal.

Finally, we learned how to properly notify users that the portal is
about to shut down and how to enable the OpenOffice.org integration.

All of this information should help to bring you well on your way to
becoming a seasoned Liferay Social Office Administrator.
