
# Using the Control Panel [](id=using-the-control-panel-liferay-portal-6-2-user-guide-17-en)

In the previous chapter, we learned how to use the Control Panel for user
management. We learned how to manage users, organizations, user group, roles,
and password policies. We also learned about user monitoring. In this chapter,
we'll look at portal and server configuration options for Liferay. Now that you
have been navigating in the Control Panel, you should be pretty familiar with
how it works. The Control Panel is organized in four main areas: Users, Sites,
Apps, and Configuration. The options in the *Configuration* section of the
Control Panel category include configuration options which control how the
portal operates and integrates with other systems you may have.  In this
chapter, we'll cover the following topics:

- Portal settings, such as user authentication options, mail host names, email
  notifications, identification settings, and display settings 

- Custom fields

- Server administration options, including options for resources, log levels,
  properties, CAPTCHA, data migration, file uploads, mail, external services,
  scripts, and shutdown settings

- Portal instances

After you have created users, user groups, organizations, roles, sites, and
teams your portal will be ready to host content and applications. You can
configure Liferay's portal settings to fit your environment and your particular
portal project. It's easy to adjust configuration settings using the
portlet-driven user interface of Liferay' Control Panel. Let's start by
examining Liferay's Portal Settings.

## Configuring Portal Settings [](id=portal-settings-liferay-portal-6-2-user-guide-16-en)

Most global portal settings can be configured from the Portal Settings section
of the Control Panel. The Configuration heading contains the following links:

**General:** lets you configure global settings, such as the company name,
domain, the virtual host, a global portal logo, and more.

**Authentication:** allows you to configure user authentication methods and
connections to LDAP and Single Sign-On servers.

**Users:** has three tabs labeled Fields, Reserved Credentials and Default User
Associations. The Fields tab enables or disables some user fields, such as
birthday or terms of use. The Reserved Credentials tab lets you reserve screen
names and email addresses so users cannot register using them. You might use
this to prevent users from registering on the portal with user names that
contain profanity or that sound official, such as *admin* or *president*. The
Default User Associations tab lets you configure default membership to roles,
user groups, sites for new users and provides a check box which allows you to
retroactively apply these to existing users.

**Mail Host Names:** lets you add a list of other mail host names to be
associated with your organization. For example, your main domain might be
`mycompany.com` but you might use `mycompany-marketing.com` for your email
newsletters. Any domain names associated with your organization can go here.

**Email Notifications:** allows you to configure Liferay to send email
notifications for certain events, such as user registrations, password changes,
etc. You can customize those messages here.

**Content Sharing**: contains options for enabling site administrators to
display content in one site from other sites they administer. You can also
configure rules for whether subsites should be able to display content from
their parent sites.

![Figure 16.14: After clicking on *Portal Settings* in Liferay's Control Panel, you can configure any of the areas shown in this figure.](../../images/server-configuration-portal-settings-pages.png)

Let's discuss these settings in more detail.

### General [](id=general-liferay-portal-6-2-user-guide-16-en)

The General link takes you to a screen with three headings: Main Configuration,
Navigation, and Additional Information. Under the Main Configuration heading,
you can set the name of the company or organization that's responsible for
running the portal. This name also defines the name of your portal's default
site. Its default name is `liferay.com` so you will definitely want to change
this to reflect the name of your company or organization. You can also set the
mail domain, virtual host and content delivery network address here.

Under the Navigation heading, you can set a home page for your portal here as 
well as default landing and logout pages. For setting these pages, just use the 
part of the page's address that follows your domain. For example, if you want 
the default landing page to be `http://localhost:8080/web/guest/login`, use 
`/web/guest/login`. You can also use the variables `${liferay:screenName}` and 
`${liferay:userId}` as part of the address. This comes in handy if you want to 
redirect users to their personal pages upon login. Alternatively, you can set 
the default login or logout page in a `portal-ext.properties` file with the 
properties `default.landing.page.path` and `default.logout.page.path`, 
respectively. For more information, see the `portal.properties` documentation 
entries for the [Default Landing Page](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Default%20Landing%20Page) 
and [Default Logout Page](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Default Logout Page). 

Under the Additional Information heading, you can specify a Legal name, ID, 
company type, SIC code, ticker symbol, industry and industry type.

### Authentication [](id=authentication-liferay-portal-6-2-user-guide-16-en)

The Authentication page has several tabs: General, LDAP, CAS, Facebook, NTLM,
OpenID, Open SSO and SiteMinder. You can use any of these authentication methods
to configure how users will authenticate to Liferay. Since Liferay supports
quite a few authentication methods, there are different settings for each.

The settings on the General tab of the Authentication page affect only Liferay
functionality and don't have anything to do with the integration options on the
other tabs. The General tab allows you to customize Liferay's standard
authentication behavior. Specifically, the General tab allows you to select from
several global authentication settings:

-   Authenticate via email address (default), screen name, or user ID (a
    numerical ID auto-generated in the database--not recommended).

-   Enable/Disable automatic log in. If enabled, Liferay allows a user to check
    a box which will cause the site to "remember" the user's log in by placing a
    cookie on his or her browser. If disabled, users will always have to log in
    manually.

-   Enable/Disable forgotten password functionality.

-   Enable/Disable request password reset links.

-   Enable/Disable account creation by strangers. If you are running an Internet
    site, you will probably want to leave this on so visitors can create
accounts on your site.

-   Enable/Disable account creation by those using an email address in the
    domain of the company running the site (which you just set on the General
    page of Portal Settings). This is handy if you are using Liferay to host
    both internal and external web sites. You can make sure all internal IDs
    have to be created by administrators but external users can register for IDs
    themselves.

-   Enable / Disable email address verification. If you enable this, Liferay
    will send users a verification email with a link back to the portal to
    verify the email address they entered is a valid one they can access.

By default, all settings except for the last are enabled. User authentication by
email address is an important default for the following reasons:

1.  An email address is, by definition, unique to the user who owns it.

2.  People can generally remember their email addresses. If you have users who
    haven't logged into the portal for a while, it is possible they will forget
    their screen names, especially if they weren't allowed to use their screen
    names of choice (because they were already taken).

3.  If a user changes his or her email address, it is more likely the user will
    forget to update his or her email address in his or her profile, if the
    email address is not used to authenticate. If the user's email address is
    not updated, all notifications sent by the portal will fail to reach the
    user. So it is important to keep the email address at the forefront of a
    user's mind when he or she logs in to help the user keep it up to date. 

Next, we'll examine how to integrate existing users from other environments,
such as LDAP servers, into Liferay.

## Integrating Existing Users into Liferay [](id=integrating-existing-users-into-liferay-liferay-portal-6-2-user-guide-17-en)

Liferay provides a number of user authentication options. You can configure
Liferay to connect to LDAP or NTLM servers so users from those environments can
log in to your Liferay server. You can also configure Liferay to allow users to
log in via Facebook or OpenId accounts. Liferay's Control Panel provides
interfaces for setting up user authentication using the following services:

- LDAP
- CAS
- Facebook
- NTLM
- OpenId
- Open SSO
- SiteMinder

Let's start by learning how to connect Liferay to an LDAP server.

### LDAP [](id=ldap-liferay-portal-6-2-user-guide-16-en)

You can use the LDAP tab of the Authentication page to connect Liferay to an
LDAP directory. There are two places for you to configure the LDAP settings:
here in the Control Panel or in your Liferay server's `portal-ext.properties`
file. You can browse an HTML version of Liferay's `portal.properties` file
online at
[http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html).
We recommend you use the Control Panel to configure LDAP server connection
settings since your settings will be stored in the database. Note that if you
use both, the settings in the database will be merged with the settings in
`portal-ext.properties`. If there's a conflict or overlapping data, the LDAP
server settings in the database take precedence over the servers set in
the `portal-ext.properties` file. Configuring the LDAP settings from the Control
Panel is easier and does not require a restart of Liferay. The only compelling
reason to use the `portal-ext.properties` file is if you have many Liferay nodes
which need be configured to run against the same LDAP directory. In that case,
for your initial deployment, it may be easier to copy the
`portal-ext.properties` file to all of the nodes so the first time they start
up, the settings are correct. Regardless of which method you use, the available
settings are the same.

You configure the global values from the LDAP tab of the Authentication page.

**Enabled:** Check this box to enable LDAP Authentication.

**Required:** Check this box if LDAP authentication is required. Liferay will
then not allow a user to log in unless he or she can successfully bind to the
LDAP directory first. Uncheck this box if you want to allow users with Liferay
accounts but no LDAP accounts to log in to the portal.

**LDAP Servers:** Liferay supports connections to multiple LDAP servers. You can
you the Add button beneath this heading to add LDAP servers. We explain how to
configure new LDAP servers below.

**Import/Export:** You can import and export user data from LDAP directories
using the following options:

- *Import Enabled:* Check this box to cause Liferay to do a mass import from
  your LDAP directories. If you want Liferay to only synchronize users when they
  log in, leave this box unchecked. Definitely leave this unchecked if you are
  working in a clustered environment. Otherwise, all of your nodes would try to
  do a mass import when each of them starts up.

- *Import on Startup Enabled:* Check this box to have Liferay run the import
  when it starts up. Note: This box only appears if you check the *Import
  Enabled* box above.

- *Export Enabled:* Check this box to enable Liferay to export user accounts
  from the database to LDAP. Liferay uses a listener to track any changes made
  to the `User` object and will push these changes out to the LDAP server
  whenever the `User` object is updated. Note that by default on every login,
  fields such as `LastLoginDate` are updated. When export is enabled, this has
  the effect of causing a user export every time the user logs in. You can
  disable this by setting the following property in your `portal-ext.properties`
  file:

        users.update.last.login=false

**Use LDAP Password Policy:** Liferay uses its own password policy by default.
This can be configured on the Password Policies page of the Control Panel. Check
the *Use LDAP Password Policy* box if you want to use the password policies
defined by your LDAP directory. Once this is enabled, the Password Policies tab
will display a message stating you are not using a local password policy. You
will now have to use your LDAP directory's mechanism for setting password
policies. Liferay does this by parsing the messages in the LDAP controls
returned by your LDAP server. By default, the messages in the LDAP controls that
Liferay is looking for are the messages returned by the Fedora Directory Server.
If you are using a different LDAP server, you will need to customize the
messages in Liferay's `portal-ext.properties` file, as there is not yet a GUI
for setting this. See below for instructions describing how to do this.

Once you've finished configuring LDAP, click the *Save* button. Next, let's look
at how to add LDAP servers.

#### Adding LDAP Servers [](id=adding-ldap-servers-liferay-portal-6-2-user-guide-16-en)

The Add button beneath the LDAP servers heading allows you to add LDAP servers.
If you have more than one, you can arrange the servers by order of preference
using the up/down arrows. When you add an LDAP Server, you will need to provide
several pieces of data so Liferay can bind to that LDAP server and search it for
user records. Regardless of how many LDAP servers you add, each server has the
same configuration options.

**Server Name:** Enter a name for your LDAP server.

**Default Values:** Several leading directory servers are listed here. If you
are using one of these, select it and click the *Reset Values* button. The rest
of the form will be populated with the proper default values for that directory.

**Connection:** These settings cover the basic connection to LDAP.

- *Base Provider URL:* This tells the portal where the LDAP server is located.
  Make sure the machine on which Liferay is installed can communicate with the
  LDAP server. If there is a firewall between the two systems, check to make
  sure the appropriate ports are opened.

- *Base DN:* This is the Base Distinguished Name for your LDAP directory. It is
  usually modeled after your organization. For a commercial organization, it may
  look similar to this: `dc=companynamehere,dc=com`.

- *Principal:* By default, the administrator ID is populated here. If you have
  removed the default LDAP administrator, you will need to use the fully
  qualified name of the administrative credential you use instead. You need an
  administrative credential because Liferay will be using this ID to synchronize
  user accounts to and from LDAP .

- *Credentials:* This is the password for the administrative user.

This is all you need to make a regular connection to an LDAP directory. The rest
of the configuration is optional. Generally, the default attribute mappings
provide enough data to synchronize back to the Liferay database when a user
attempts to log in. To test the connection to your LDAP server, click the *Test
LDAP Connection* button.

If you are running your LDAP directory in SSL mode to prevent credential
information from passing through the network unencrypted, you will have to
perform extra steps to share the encryption key and certificate between the two
systems.

For example, assuming your LDAP directory happens to be Microsoft Active
Directory on Windows Server 2003, you would take the following steps to share
the certificate:

Click *Start* &rarr; *Administrative Tools* &rarr; *Certificate Authority*.
Highlight the machine that is the certificate authority, right-click on it, and
click *Properties*. From the General menu, click *View Certificate*. Select the
Details view, and click *Copy To File*. Use the resulting wizard to save the
certificate as a file. As with the CAS install (see the below section entitled
*Single Sign-On*), you will need to import the certificate into the *cacerts
keystore*. The import is handled by a command like the following:

    keytool -import -trustcacerts -keystore /some/path/jdk1.5.0_11/jre/lib/security/cacerts -storepass changeit -noprompt -alias MyRootCA -file /some/path/MyRootCA.cer

The *keytool* utility ships as part of the Java SDK.

Once this is done, go back to the LDAP page in the Control Panel. Modify the
LDAP URL in the Base DN field to the secure version by changing the protocol to
`https` and the port to `636` like the following:

    ldaps://myLdapServerHostname:636

Save the changes. Your Liferay Portal will now use LDAP in secure mode for
authentication.

**Users:** This section contains settings for finding users in your LDAP
directory.

- *Authentication Search Filter:* The search filter box can be used to determine
  the search criteria for user logins. By default, Liferay uses users' email
  addresses for their login names. If you have changed this setting, you will
  need to modify the search filter here, which has been configured to use the
  email address attribute from LDAP as a search criterion. For example, if you
  changed Liferay's authentication method to use screen names instead of the
  email addresses, you would modify the search filter so it can match the
  entered log in name:

        (cn=@screen_name@)

- *Import Search Filter:* Depending on the **LDAP** server, there are different
  ways to identify the user. The default setting is usually fine:

        (objectClass=inetOrgPerson)

    If you want to search for only a subset of users or users that have
    different LDAP object classes, you can change this.

- *User Mapping:* The next series of fields allows you to define mappings from
  LDAP attributes to Liferay fields. Though your LDAP user attributes may be
  different from LDAP server to LDAP server, there are five fields Liferay
  requires to be mapped for the user to be recognized. You must define a mapping
  to the corresponding attributes in LDAP for the following Liferay fields:

    +   *Screen Name* (e.g., *uid*)

    +   *Password* (e.g., *userPassword*)

    +   *Email Address* (e.g., *mail* or *email*)

    +   *First Name* (e.g., *name* or *givenName*)

    +   *Last Name* (e.g., *sn*)

    If you'd like to import LDAP groups as Liferay user groups, make sure to
    define a mapping for the Liferay group field so that membership information
    is preserved:

    +   *Group* (e.g., *member*)

    The other LDAP user mapping fields are optional.

The Control Panel provides default mappings for commonly used LDAP attributes.
You can also add your own mappings if you wish.

- *Test LDAP Users:* Once you have your attribute mappings set up (see above),
  click the *Test LDAP Users* button and Liferay will attempt to pull LDAP users
  and match them with their mappings as a preview.

![Figure 16.15: Testing LDAP Users](../../images/server-configuration-testing-ldap-users.jpg)

**Groups:** This section contains settings for mapping LDAP groups to Liferay
user groups.

- *Import Search Filter:* This is the filter for finding the LDAP groups that
  you want to map to Liferay user groups. E.g.,

        (objectClass=groupOfNames)

    Enter the LDAP group attributes you want retrieved for this mapping. The
    following attributes can be mapped. The *Group Name* and *User* fields are
    required, the *Description* is optional.

    + *Group Name* (e.g., *cn* or *o*)

    + *Description* (e.g., *description*)

    + *User* (e.g., *member*)

- *Test LDAP Groups:* Click the *Test LDAP Groups* button to display a list of
  the groups returned by your search filter.

**Export:** This section contains settings for exporting user data from LDAP.

- *Users DN:* Enter the location in your LDAP tree where the users will be
  stored. When Liferay does an export, it will export the users to this
  location.

- *User Default Object Classes:* When a user is exported, the user is created
  with the listed default object classes. To find out what your default object
  classes are, use an LDAP browser tool such as JXplorer to locate a user and
  view the Object Class attributes stored in LDAP for that user.

- *Groups DN:* Enter the location in your LDAP tree where the groups will be
  stored. When Liferay does an export, it will export the groups to this
  location.

- *Group Default Object Classes:* When a group is exported, the group is created
  with the listed default object classes. To find out what your default object
  classes are, use an LDAP browser tool such as *Jxplorer* to locate a group and
  view the Object Class attributes stored in LDAP for that group.

![Figure 16.16: Mapping LDAP Groups](../../images/server-configuration-mapping-ldap-groups.jpg)

Once you've set all your options and tested your connection, click *Save*. From
here, you can add another LDAP server or set just a few more options that apply
to all of your LDAP server connections.

#### LDAP Options Not Available in the GUI [](id=ldap-options-not-available-in-the-gui-liferay-portal-6-2-user-guide-16-en)

Although most of the LDAP configuration can be done from the Control Panel,
there are several configuration parameters that are only available by adding to
your Liferay server's `portal-ext.properties` file. These options may be
available in the GUI in future versions of Liferay Portal but for now they can
only be configured by editing the properties file.

If you need to change any of these options, copy the LDAP section from Liferay's
`portal.properties` file into your `portal-ext.properties` file. Note that since
you have already configured LDAP from the GUI, any settings from the properties
file that match settings already configured in the GUI will be ignored. The GUI,
which stores the settings in the database, always takes precedence over the
properties file.

    ldap.auth.method=bind
    #ldap.auth.method=password-compare

Set either bind or password-compare for the LDAP authentication method. Bind is
preferred by most vendors so you don't have to worry about encryption
strategies. Password compare does exactly what it sounds like: it reads the
user's password out of LDAP, decrypts it and compares it with the user's
password in Liferay, syncing the two.

    ldap.auth.password.encryption.algorithm=
    ldap.auth.password.encryption.algorithm.types=MD5,SHA

Set the password encryption to used to compare passwords if the property
`ldap.auth.method` is set to `password-compare`.

    ldap.import.method=[user,group]

If you set this to `user`, Liferay will import all users from the specified
portion of the LDAP tree. If you set this to `group`, Liferay will search all
the groups and import the users in each group. If you have users who do not
belong to any groups, they will not be imported.

    ldap.error.password.age=age
    ldap.error.password.expired=expired
    ldap.error.password.history=history
    ldap.error.password.not.changeable=not allowed to change
    ldap.error.password.syntax=syntax
    ldap.error.password.trivial=trivial
    ldap.error.user.lockout=retry limit

These properties are a list of phrases from error messages which can possibly be
returned by the LDAP server. When a user binds to LDAP, the server can return
*controls* with its response of success or failure. These controls contain a
message describing the error or the information that is coming back with the
response. Though the controls are the same across LDAP servers, the messages can
be different. The properties described here contain snippets of words from those
messages and will work with Red Hat's Fedora Directory Server. If you are not
using that server, the word snippets may not work with your LDAP server. If they
don't, you can replace the values of these properties with phrases from your
server's error messages. This will enable Liferay to recognize them. Next, let's
look at the Single Sign-On solutions Liferay supports.

### SSO [](id=sso-liferay-portal-6-2-user-guide-16-en)

Single Sign-On solutions allow you to provide a single login credential for
multiple systems. This allows you to have people authenticate to the Single
Sign-On product and they will be automatically logged in to Liferay and to other
products as well.

Liferay supports several single sign-on solutions. Of course, if your product is
not yet supported, you may choose to implement support for it yourself by use of
the extension environment. Alternatively, your organization can choose to
sponsor support for it. Please contact `sales@liferay.com` for more information
about this.

### Authentication: Central Authentication Service (CAS) [](id=authentication-central-authentication-s-liferay-portal-6-2-user-guide-16-en)

CAS is an authentication system originally created at Yale University. It is a
widely-used open source single sign-on solution and was the first SSO product to
be supported by Liferay.

Please follow the documentation for CAS to install it on your application server
of choice.

Your first step will be to copy the CAS client .jar file to Liferay's library
folder. On Tomcat, this is in `[Tomcat Home]/webapps/ROOT/WEB-INF/lib`. Once
you've done this, the CAS client will be available to Liferay the next time you
start it.

The CAS Server application requires a properly configured Secure Socket Layer
certificate on your server to work. If you wish to generate one yourself, you
will need to use the `keytool` utility that comes with the JDK. Your first step
is to generate the key. Next, you export the key into a file. Finally, you
import the key into your local Java key store. For public, Internet-based
production environments, you will need to either purchase a signed key from a
recognized certificate authority (such as Thawte or Verisign) or have your key
signed by a recognized certificate authority. For Intranets, you should have
your IT department pre-configure users' browsers to accept the certificate so
they don't get warning messages about the certificate.

To generate a key, use the following command:

    keytool -genkey -alias tomcat -keypass changeit -keyalg RSA

Instead of the password in the example (`changeit`), use a password you will
remember. If you are not using Tomcat, you may want to use a different alias as
well. For First and Last names, enter `localhost` or the host name of your
server. It cannot be an IP address.

To export the key to a file, use the following command:

    keytool -export -alias tomcat -keypass changeit -file server.cert

Finally, to import the key into your Java key store, use the following command:

    keytool -import -alias tomcat -file %FILE_NAME% -keypass changeit -keystore $JAVA_HOME/jre/lib/security/cacerts

If you are on a Windows system, replace `$JAVA_HOME` above with `%JAVA_HOME%`.
Of course, all of this needs to be done on the system on which CAS will be
running.

Once your CAS server is up and running, you can configure Liferay to use it.
This is a simple matter of navigating to the *Settings &rarr;  Authentication
&rarr; CAS* tab in the Control Panel. Enable CAS authentication and then modify
the URL properties to point to your CAS server.

**Enabled:** Check this box to enable CAS single sign-on.

**Import from LDAP:** A user may be authenticated from CAS and not yet exist in
the portal. Select this to automatically import users from LDAP if they do not
exist in the portal.

The rest of the settings are various URLs, with defaults included. Change
*localhost* in the default values to point to your CAS server. When you are
finished, click *Save*. After this, when users click the *Sign In* link, they
will be directed to the CAS server to sign in to Liferay.

### Authentication: Facebook [](id=authentication-facebook-liferay-portal-6-2-user-guide-16-en)

Liferay Portal also enables users to log in using their Facebook accounts. To
enable this feature, you simply need to select the *Enable* box and enter the
Application ID and Application Secret which should have been provided to you by
Facebook. Facebook SSO works by taking the primary Facebook email address and
searching for the same email address in Liferay's `User_` table. If a match is
found, the user is automatically signed on (provided the user clicked *allow*
from the Facebook dialog). If there isn't a match, the user is prompted in
Liferay to add a user from Facebook. Once selected, a new user is created by
retrieving four fields from Facebook (first name, last name, email address and
gender).

### Authentication: NTLM [](id=authentication-ntlm-liferay-portal-6-2-user-guide-16-en)

NTLM is a Microsoft protocol that can be used for authentication through
Microsoft Internet Explorer. Though Microsoft has adopted Kerberos in modern
versions of Windows server, NTLM is still used when authenticating to a
workgroup. Liferay Portal now supports NTLM v2 authentication. NTLM v2 is more
secure and has a stronger authentication process than NTLMv1.

**Enabled:** Check this box to enable NTLM authentication.

**Domain Controller:** Enter the IP address of your domain controller. This is
the server that contains the user accounts you want to use with Liferay.

**Domain:** Enter the domain / workgroup name.

**Service Account:** You need to create a service account for NTLM. This account
will be a computer account, not a user account.

**Service Password:** Enter the password for the service account.

### Authentication: OpenID [](id=authentication-openid-liferay-portal-6-2-user-guide-16-en)

OpenID is a new single sign-on standard which is implemented by multiple
vendors. The idea is multiple vendors can implement the standard and then users
can register for an ID with the vendor they trust. The credential issued by that
vendor can be used by all the web sites that support OpenID. Some high profile
OpenID vendors are AOL
[http://openid.aol.com/screenname](http://openid.aol.com/screenname), LiveDoor
[http://profile.livedoor.com/username](http://profile.livedoor.com/username) and
LiveJournal [http://username.livejournal.com](http://username.livejournal.com/).
Please see the OpenID site [http://www.openid.net](http://www.openid.net/) for a
more complete list.

A main benefit of OpenID for the user is he or she no longer has to register for
a new account on every site in which he or she wants to participate. Users can
register on *one* site (the OpenID provider's site) and then use those
credentials to authenticate to many web sites which support OpenID. Many web
site owners often struggle to build communities because end users are reluctant
to register for so many different accounts. Supporting OpenID makes it easier
for site owners to build their communities because the barriers to participating
(i.e., the effort it takes to register for and keep track of many accounts) are
removed. All of the account information is kept with the OpenID provider, making
it much easier to manage this information and keep it up to date.

Liferay Portal can act as an OpenID consumer, allowing users to automatically
register and sign in with their OpenID accounts. Internally, the product uses
OpenID4Java
[http://code.google.com/p/openid4java/](http://code.google.com/p/openid4java/)
to implement the feature.

OpenID is enabled by default in Liferay but can be disabled here.

### Atlassian Crowd [](id=atlassian-crowd-liferay-portal-6-2-user-guide-16-en)

Atlassian Crowd is a web-based Single Sign-On product similar to CAS. Crowd can
be used to manage authentication to many different web applications and
directory servers.

Because Atlassian Crowd implements an OpenID producer, Liferay works and has
been tested with it. Simply use the OpenID authentication feature in Liferay to
log in using Crowd.

### Authentication: OpenSSO [](id=authentication-opensso-liferay-portal-6-2-user-guide-16-en)

OpenSSO is an open source single sign-on solution that comes from the code base
of Sun's System Access Manager product. Liferay integrates with OpenSSO,
allowing you to use OpenSSO to integrate Liferay into an infrastructure that
contains a multitude of different authentication schemes against different
repositories of identities.

You can set up OpenSSO on the same server as Liferay or a different box. Follow
the instructions at the OpenSSO site
[http://opensso.dev.java.net](http://opensso.dev.java.net/) to install OpenSSO.
Once you have it installed, create the Liferay administrative user in it. Users
are mapped back and forth by screen names. By default, the Liferay
administrative user has a screen name of *test*, so in OpenSSO, you would
register the user with the ID of *test* and an email address of
*test@liferay.com*. Once you have the user set up, log in to Open SSO using this
user.

In the same browser window, go to the URL for your server running Liferay and
log in as the same user, using the email address *test@liferay.com*. Go to the
Control Panel and click *Settings &rarr; Authentication &rarr; OpenSSO*. Modify
the three URL fields (Login URL, Logout URL and Service URL) so they point to
your OpenSSO server (i.e., only modify the host name portion of the URLs), click
the *Enabled* check box and then click *Save*. Liferay will then redirect users
to OpenSSO when they click the *Sign In* link.

### Authentication: SiteMinder [](id=authentication-siteminder-liferay-portal-6-2-user-guide-16-en)

SiteMinder is a single sign-on implementation from Computer Associates. Liferay
5.2 introduced built-in integration with SiteMinder. SiteMinder uses a custom
HTTP header to implement its single sign-on solution.

To enable SiteMinder authentication in Liferay, check the *Enabled* box on the
*SiteMinder* tab. If you are also using LDAP with Liferay, you can check the
*Import from LDAP* box. If this box is checked, users authenticated from
SiteMinder who do not exist in the portal will be imported from LDAP.

The last field defines the header SiteMinder is using to keep track of the user.
The default value is already populated. If you have customized the field for
your installation, enter the custom value here.

When you are finished, click *Save*. Now that we've looked at various options
for integrating existing users into Liferay, let's look at other Liferay portal
settings.

### Shibboleth [](id=shibboleth-liferay-portal-6-2-user-guide-17-en)

Shibboleth is a federated single sign-on and attribute exchange framework that
implements SAML (Security Assertion Markup Language). The Shibboleth plugin
available from Liferay Marketplace allows Liferay to integrate with Shibboleth
to provide SSO support. The Liferay Shibboleth plugin contains an autologin hook
that automatically logs users into Liferay when they are authenticated to a
Shibboleth identity provider and the HTTP request has a specific header
attribute. All of the Shibboleth service provider and identity provider
configuration is done outside of Liferay. All that the Liferay Shibboleth plugin
needs to do is accept the header attribute from Shibboleth and log the user in.

The Shibboleth plugin adds a Shibboleth tab to the Authentication page of the
Portal Settings section of the Control Panel.

![Figure 17.1: You can enable/disable Shibboleth authentication for Liferay by navigating to the *Control Panel* &rarr; *Portal Settings* &rrar; *Authentication* &rarr; *Shibboleth*.](../../images/shibboleth.png)

There are four configuration options:

- *Enabled*
- *Import from LDAP*
- *Logout URL*
- *User Header*

Check or uncheck the *Enabled* box to enable or disable Shibboleth authentication
for Liferay. If the *Enabled* box is not checked, the Shibboleth auto login hook
is disabled and the automated login process will not work even if the user
header is in the HTTP request. If you check the *Import from LDAP* box, then any
users authenticated from Shibboleth that do not exist in Liferay will be
imported into Liferay from LDAP. LDAP must already be enabled for this to work.
The path you enter into the *Logout URL* field sets the logout URL for
Shibboleth. The link is triggered when the user logs out of the portal.  If the
logout URL is incorrect or is not set, the user will not be logged out of
Shibboleth. The *User Header* is the attribute name of the user header that is
passed in from the Shibboleth service provider. It should contain either the
screen name or the email address of the user, which will be used to authenticate
the user into the portal.

It's possible to configure the above four Shibboleth options via portal
properties instead of via the Control Panel. To configure the Shibboleth plugin
this way, add the following properties to your Liferay server's
`portal-ext.properties` file and restart Liferay.

- `shibboleth.auth.enabled=true`: corresponds to the *Enable* box above
- `shibboleth.import.from.ldap=false`: corresponds to the *Import from LDAP* box
  above
- `shibboleth.logout.url=/Shibboleth.soo/Logout`: corresponds to the *Logout
  URL* box above
- `shibboleth.auth.enabled=true`: corresponds the *User Header* box above

Note that options selected via the Control Panel are saved to Liferay's database
and take precedence over any options configured via portal properties.

### SAML [](id=saml-liferay-portal-6-2-user-guide-17-en)

![EE-only](../../images/ee-feature-web.png)

SAML is an XML-based open standard data format for exchanging authentication and
authorization data between parties known as an identity provider and a service
provider. An identity provider is a trusted provider that enables users to use
single sign-on to access other websites. A service provider is a website that
hosts applications and grants access only to identified users with proper
credentials. SAML is maintained by the OASIS Security Services Technical
Committee. See
[https://www.oasis-open.org/committees/security/](https://www.oasis-open.org/committees/security/)
for more information. Liferay 6.1 EE and later versions support SAML 2.0
integration via the SAML 2.0 Provider EE plugin. This plugin is provided as an
app from Liferay Marketplace that allows Liferay to act as a SAML 2.0 identity
provider or as a service provider. First, we'll look at how to set Liferay up as
an Identity Provider and then we'll look at how to set it up as a Service
Provider.

#### Setting up Liferay as a SAML Identity Provider [](id=setting-up-liferay-as-a-saml-identity-p-liferay-portal-6-2-user-guide-17-en)

In order to set Liferay up to act as a SAML Identity Provider, please use the
following steps:

1. Install the SAML 2.0 Provider EE app, either via the Control Panel's
   Marketplace interface or manually. To confirm that the plugin was
   successfully deployed, look for the *SAML Admin* entry in the Control Panel.

    ![Figure x: The SAML Admin entry appears as a sub-section of the Configuration section of the Control Panel.](../../images/saml-admin.png)

    To access the SAML Admin interface, click on *Admin* &rarr; *Control Panel*
    and then on *SAML Admin*. 

2. To begin configuring the Liferay to use SAML, you need to select a SAML role
   for Liferay and you need to choose an entity ID.
   
    ![Figure x: Select a SAML role for Liferay and enter an entity ID.](../../images/saml-initial-config.png)
   
    The SAML role can be set to either Identity Provider or Service Provider.
    Select the *Identity Provider* SAML role. A single Liferay instance can
    serve either as an identity provider or as a service provider, but not both.
    Enter *liferaysamlidp* if you're setting up an example Liferay instance.
    Alternatively, choose your own entity ID. Then click *Save* and look for a
    new section entitled Certificate and Private Key to appear.

3. The Certificate and Private Key section allows you to enter information that
   will be used to create a keystore for SAML. Enter the following information:
    
    - Your common name (your first and last name)
    - The name of your organization
    - The name of your organizational unit
    - The name of your city or locality
    - The name of your state or province
    - The name of your country
    - The length in days that your keystore will remain valid (how long before
      the keystore expires)
    - The key algorithm (RSA is the default)
    - The key length in bits (2048 is the default)
    - The key password

    If you're configuring an example setup, use the password *liferay*. When
    you've entered all the required information, click *Save*.

    Note that the SAML keystore is created by the SAML plugin's keystore
    manager. By default, the following property is set in the SAML plugin:

        saml.keystore.manager.impl=com.liferay.saml.credential.FileSystemKeyStoreManagerImpl

    This portlet property means that the keystore will be created in the
    filesystem.  The default location is the `[Liferay Home]/data` directory.
    However, this portlet property can be changed to the following:

        saml.keystore.manager.impl=com.liferay.saml.credential.DLKeystoreManagerImpl

    With this setting, the keystore will be created in Liferay's document
    library.  Liferay's document library could be stored on the file system, in
    a database, in the cloud, etc. By default, documents are stored on the file
    system, as per the following portal property:

        dl.store.impl=com.liferay.portlet.documentlibrary.store.FileSystemStore

    See the
    [http://docs.liferay.com](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Document%20Library%20Portlet)
    section on Liferay's `dl.store.impl` portal property for details.

4. After you've clicked *Save*, information about the keystore you created is
   displayed. You can click *Replace Certificate* at any time to replace the
   current certificate with an updated one. You can do this, for example, if
   your keystore has expired or is about to expire. You can also do this if
   you'd like to change any of the information saved in your keystore, including
   the password.

    ![Figure x: The General tab of the SAML Admin portlet displays information about the current certificate and private key and allows administrators to download the certificate or replace the certificate.](../../images/saml-keystore-info.png)
   
    Also, notice that additional options appear in the SAML Admin Control Panel
    portlet. There are three tabs:

    - General
    - Identity Provider
    - Service Provider Connections

5. After you've saved your certificate and private key information, check the
   *Enabled* box at the top of the General tab and click *Save*.  Great! You've
   successfully set Liferay up as a SAML Identity Provider!
   
To configure Liferay's SAML Identity Provider Settings, navigate to the Identity
Provider tab of the SAML Admin Control Panel portlet. Of course, setting up
Liferay as a SAML Identity Provider is only useful if you can connect to one or
more SAML Service Providers. Navigate to the Service Provider Connections tab of
the SAML Admin Control Panel portlet and click on the *Add Service Provider*
button to add a SAML Service Provider. Right now, we don't have one to add but
next, we'll learn how to set Liferay up as a SAML Service Provider.

After you've set up another Liferay instance as a Service Provider, you can come
back to this Liferay instance's Control Panel and add the Service Provider:
*Control Panel* &rarr; *SAML Admin* &rrar; *Service Provider Connections* &rarr;
*Add Service Provider*.

#### Setting up Liferay as a SAML Service Provider [](id=setting-up-liferay-as-a-saml-service-pr-liferay-portal-6-2-user-guide-17-en)

In order to set Liferay up to act as a SAML Service Provider, use the following
steps. Many of the steps are similar to the ones for setting Liferay up to act
as a SAML Identity Provider. A single Liferay instance can be configured as a
SAML Identify Provider *or* as a SAML Service Provider but not as both. If
you've already set up one Liferay instance as a SAML Identity Provider, use a
*different* Liferay instance as a SAML Service Provider

1. Install the SAML 2.0 Provider EE app, either via the Control Panel's
   Marketplace interface or manually. To confirm that the plugin was
   successfully deployed, look for the *SAML Admin* entry in the Configuration
   section of the Control Panel.

2. To begin configuring the Liferay to use SAML, you need to select a SAML role
   for Liferay and you need to choose an entity ID. The SAML role can be set to
   either Identity Provider or Service Provider. Select the *Service Provider*
   SAML role. Enter *liferaysamlsp* if you're setting up an example Liferay
   instance. Alternatively, choose your own entity ID. Then click *Save* and a
   new section entitled Certificate and Private Key appears.

3. The Certificate and Private Key section allows you to enter information that
   will be used to create a keystore for SAML. Enter the following information:
    
    - Your common name (your first and last name)
    - The name of your organization
    - The name of your organizational unit
    - The name of your city or locality
    - The name of your state or province
    - The name of your country
    - The length in days that your keystore will remain valid (how long before
      the keystore expires)
    - The key algorithm (RSA is the default)
    - The key length in bits (2048 is the default)
    - The key password

    If you're configuring an example setup, use the password *liferay*. When
    you've entered all the required information, click *Save*.

4. After you've clicked *Save*, check that you can view information about your
   certificate or download your certificate. If you can, you've successfully
   created a keystore. After you've created a keystore, additional options
   appear in the SAML Admin Control Panel portlet. There are three tabs:

    - General
    - Service Provider (*not Identity Provider!*)
    - Identity Provider Connection (*not Service Provider Connections!*)

    Note that these options are different than if you were setting up Liferay as
    an Identity Provider.

5. Next, you need to configure an Identity Provider connection. Click on the
   *Identity Provider Connection* tab. Enter a name for the Identity Provider,
   enter its entity ID, and enter its metadata URL. If you have already
   configured a separate Liferay instance as an Identify provider by following
   the previous instructions, you'd enter the following information:

    - Name: *Liferay IdP*
    - Entity ID: *liferaysamlidp*
    - Metadata URL: *http://localhost:8080/c/portal/saml/metadata* (test this
      URL first)

6. Finally, after you've saved your certificate and private key information and
   configured an Identity Provider connection, check the *Enabled* box at the
   top of the General tab and click *Save*. Great! You've successfully set
   Liferay up as a SAML Service Provider!

If you'd like to configure Liferay's SAML Service Provider Settings, navigate to
the Service Provider tab of the SAML Admin Control Panel portlet.

+$$$

**Note:** The previous two sections explained how to use the SAML portlet's
Control Panel interface to configure Liferay as an Identity Provider and as a
Service Provider. It's possible to configure Liferay as an Identity Provider or
as a Service Provider entirely through the `portal-ext.properties` file.
However, we recommend using the Control Panel SAML interface because it
specifies required fields and validates some fields.

$$$

Suppose that you have two Liferay instances running on ports 8080 and 9080 of
your host. Suppose further that you've configured the Liferay running on port
8080 as a SAML Identity Provider and the Liferay running on port 9080 as a SAML
Service Provider, following the instructions above. If your Identity Provider
and Service Provider have been correctly configured, navigating to
[http://localhost:8080/c/portal/saml/sso?entityId=liferaysamlsp](http://localhost:8080/c/portal/saml/sso?entityId=liferaysamlsp)
initiates the SAML Identity Provider based login process. To initiate the SAML
Service Provider based login process, just navigate to the Liferay running on
port 9080 and click *Sign In*, navigate to
[http://localhost:9080/c/portal/login](http://localhost:9080/c/portal/login), or
try to access a protected resource URL such as a Control Panel URL.

## Configuring Additional Portal Settings [](id=configuring-additional-portal-settings-liferay-portal-6-2-user-guide-17-en)

Go back to the Control Panel, and click on *Portal Settings* under the
*Configuration* heading. We've already looked at the options available from the
*General* and *Authentication* links. Now let's examine the other portal
settings options.

### Users [](id=users-liferay-portal-6-2-user-guide-16-en)

If you click on *Users* from the Portal Settings screen, you'll find three tabs:
Fields, Reserved Credentials and Default User Associations.

The Fields tab allows you to enable/disable the following fields:

-   Enable/disable requiring the Terms of Use

-   Enable/disable user screen names autogeneration

-   Enable/disable requiring the last names

-   Enable/disable the birthday field

-   Enable/disable the gender field

The next tab is Reserved Credentials. You can enter screen names and email
addresses here that you don't want others to use. Liferay will then prevent
users from registering with these screen names and email addresses. You might
use this feature to prevent users from creating IDs that look like
administrative IDs or that have reserved words in their names.

The Default User Associations tab has three fields allowing you to list (one per
line) sites, roles, and user groups you want new users to become members of
automatically. By default, Liferay assigns new users to both the Users role and
the Power Users role.

If you have defined other user groups, sites or roles you want newly created
users to be members of by default, enter them here. For example, you may have
defined site templates in certain user groups to pre-populate end users' private
pages. If there is a particular configuration you want everyone to have, you may
want to enter those user groups here.

### Mail Host Names [](id=mail-host-names-liferay-portal-6-2-user-guide-17-en)

Mail Host Names appears after Authentication and Users on the Portal Settings
screen of the Control Panel. You can enter other mail host names (one per line)
besides the one you configured on the General tab. This lets the portal know
which mail host names are owned by your organization.

### Email Notifications [](id=email-notifications-liferay-portal-6-2-user-guide-17-en)

There are five tabs under the Email Notifications page of Portal Settings. The
Sender tab allows you to set the portal's administrative name and email address.
By default, these are `Joe Bloggs` and `test@liferay.com`. You can change them
to whatever you want. This name and email address will appear in the From field
in all email messages sent by the portal.

![Figure 17.2: You can customize the email template for the email messages sent to users who have just created their accounts.](../../images/server-configuration-account-created-notification.png)

The other four tabs are Account Created Notification, Email Verification
Notification, Password Changed Notification and Password Reset Notification.
These tabs allow you to customize the email messages that are sent to users each
time any of those four events occur.

![Figure 17.3: You can refer to this list of variables that's available for use in email templates.](../../images/server-configuration-definition-of-terms.png)

A list of tokens, entitled "Definition of Terms," is provided so you can insert
certain values (such as the portal URL or the user ID) when you are setting up
the custom email messages.

### Content Sharing [](id=content-sharing-liferay-portal-6-2-user-guide-17-en)

The Content Sharing section of the Portal Settings area of the Control Panel
allows you to choose whether or or not site administrators can display content
in sites from other sites they administer. For example, suppose that a certain
user is a site administrator of two sites: *Engineering* and *Marketing*. The
checkbox in the Content Sharing section of Portal Settings determines whether or
not the site administrator can display content from the Marketing site in the
Engineering site and vice versa.

The Content Sharing section of Portal Settings also allows you to choose a
behavior for whether or not subsites can display content from parent sites and
for configuring the defaults. There are three options:

**Enabled by Default**: This means that subsites can display content from parent
sites by default but this can be disabled by a site administrator.

**Disabled by Default**: This means that subsites cannot display content from
parent sites by default but this can be enabled by a site administrator.

**Disabled**: This means that subsites cannot display content from parent sites
and this behavior cannot be changed by a site administrator.

### Identification [](id=identification-liferay-portal-6-2-user-guide-17-en)

The identification section has several links for addresses, phone numbers and
other information you can configure in your portal. This allows you to set up
contact information for the organization that owns the portal. Developers can
query for this information in their applications.

### Miscellaneous: Display Settings [](id=miscellaneous-display-settings-liferay-portal-6-2-user-guide-17-en)

This section allows you to set the default portal language and the time zone.
You can also set up a portal-wide logo which appears in the top left corners of
portal pages. 

![Figure 17.4: You can specify various display settings for your portal including options for the default language, other available language, time zone, portal logo, and default themes for the portal and Control Panel.](../../images/server-configuration-miscellaneous-display-settings.png)

Liferay's default theme is configured to display the portal logo. For custom
themes, you can choose whether or not to display the logo. Be careful to choose
an image file that fits the space. If you pick something too big, it might
overlap with the navigation. Next, let's look at how to customize different
types of portal assets using custom fields.

<!-- Should we provide a range of recommended parameters for custom logos?  -->

## Custom Fields [](id=custom-fields-liferay-portal-6-2-user-guide-17-en)

Custom fields appear beneath Portal Settings under the Configuration heading of
the Control Panel. Custom fields are a way to add attributes to many types of
assets and resources in the portal. For example, if you're using Liferay Portal
to create a site for rating books, you might create a custom field called
*Favorite Books* for User resource. If you're using the Wiki for book reviews,
you might add fields called *Book Title* and *Book Author*.

It's possible to add custom fields to following kinds of portal resources:

- Blogs Entry
- Bookmarks Entry
- Bookmarks Folder
- Calendar Booking
- Document
- Documents Folder
- Message Boards Category
- Message Boards Message
- Organization
- Page
- Role
- Site
- User
- User Group
- Web Content Article
- Wiki Page

The ability to add custom fields to any of these resources affords flexibility
to portal developers. For example, suppose you'd like to define a limitation on
the number of users that can be assigned to certain roles. A portal
administrator can create a custom field called *max-users* for the Role
resource. Then a portal developer can create a hook plugin that checks this
field upon user assignment to roles to make sure that there aren't too many
users assigned to the role.

To add a custom field, click on the *Custom Fields* link in the Control Panel.
Then choose a resource, click on the *Edit* link next to it and select *Add
Custom Field*.

![Figure 17.5: You can add custom fields to these portal resources.](../../images/server-configuration-custom-fields-resource-list.png)

From here you need to add the custom field key. The key appears as the label for
the field on the form. For some portal resources (like the User), custom fields
are a separate section of the form. For others, as can be seen above, custom
fields are integrated with the default fields on the form. Additionally,
developers can access custom fields programatically through the
`<liferay-ui:custom-attribute />` tag.

![Figure 17.6: The `book-author` and `book-title` custom fields are integrated with the rest of the form for editing a Wiki page.](../../images/server-configuration-custom-fields-integrated.png)

You can create fields of many different types: text fields (indexed or secret),
integers, selection of multiple values and more. Once you've created a field,
you cannot change its type. However, you can delete custom fields and create new
ones. Next, let's look at how to apply server configurations.

## Server Administration [](id=server-administration-liferay-portal-6-2-user-guide-17-en)

The Server Administration page of the Control Panel lets you perform various
tasks related to the portal server itself that aren't directly related to the
resources in the portal. Clicking the link makes this clear: you're immediately
presented with a graph showing the resources available in the JVM.

### Resources [](id=resources-liferay-portal-6-2-user-guide-17-en)

The first tab is called *Resources*. This tab contains the aforementioned graph
plus several server wide actions that an administrator can execute. These
include the following:

**Run the garbage collector:** You can send in a request to the JVM to begin the
garbage collection task.

**Clear content cached by this VM:** You can send in a request to the JVM to
clear content stored in the local cache. Ehcache usage is split into two groups:
local JVM scope and cluster scope. This action only clears the content of the
local Ehcache.

**Clear content cached across the cluster:** You can send in a request to the
JVM to clear content cached across the entire cluster. This action clears the
content of the clustered Ehcache.

**Clear the database cache:** You can send in a request to the JVM to clear the
database cache. Liferay uses Ehcache mainly, but not only, at the persistence
layer for caching objects obtained from the database. This action only clears
the database result cache.

**Clear the direct servlet cache:** You can send in a request to the JVM to
clear the direct servlet cache. The direct servlet context is a feature that
optimizes JSP serving performance by caching and accessing the generated
servlets directly instead of accessing them over the application server's
dispatcher chain. This is only suitable for cases where no filter is required
for the JSPs. For production mode, this cache should be enabled to improve
performance. In case emergency fixes need to be applied, this action allows an
administrator to manually clear out the cache to force JSPs to reload. For
development mode, the direct servlet context should be disabled to allow JSP
servlets to be reloaded on the fly. See the Direct Servlet Context section of
the `portal.properties` file for details.

**Reindex all search indexes:** You can send in a request to regenerate all
search indexes. If you are not using a Solr search server this will impact
portal performance so try to do this at non-peak times.

**Reindex all spell check indexes:** You can send in a request to regenerate all
spell check indexes.

**Reset preview and thumbnail files for the Documents and Media portlet:** You
can send in a request to reset the preview and thumbnail files for each item in
your portal's Documents and Media libraries.

**Generate Thread Dump:** If you are performance testing, you can generate a
thread dump which can be examined later to determine if there are any deadlocks
and where they might be.

**Verify database tables of all plugins:** You can check all tables against
their indexes for accuracy of data retrieval.

**Verify Membership Policies**: You can check that existing site membership
policies have been correctly applied and automatically make updates if
necessary. For example, suppose that someone manually changed the Liferay
database or that the Liferay database was hacked, resulting in a user being
assigned to a site in violation of a site membership policy. When the *Verify
Membership Policies* button is clicked, the verify methods of all the site
membership policies that have been implemented are triggered. These methods
check that all site memberships are in accord with the site membership policies;
if they are not, the necessary changes (such as removing users from forbidden
sites) are made.

**Clean up Permissions:** This process removes the assignment of some
permissions on the Guest, User, and Power User roles to simplify the management
of "User Customizable Pages". Notably, the "Add To Page" permissions is removed
from the Guest and User roles for all portlets. Likewise, the same permission is
reduced in scope for Power Users from portal wide to scoped to "User Personal
Site."

### Log Levels [](id=log-levels-liferay-portal-6-2-user-guide-17-en)

The Log Levels tab of the Server Administration page allows you to dynamically
modify the log levels for any class hierarchy in the portal. If you have custom
code you have deployed which isn't in the list, you can use the *Add Category*
tab to add it. If you change the log level near the top of the class hierarchy
(such as at `com.liferay`), all the classes under that hierarchy will have their
log levels changed. If you are testing something specific, it is much better to
be as specific as you can when you change log levels. Modifying them too high in
the hierarchy generates a lot more log messages than you need.

### Properties [](id=properties-liferay-portal-6-2-user-guide-17-en)

Liferay and the JVM contain many settings which are defined as properties. There
are two subtabs of the properties tab of the Server Administration page: one
showing system properties and one showing portal properties.

The system properties tab shows an exhaustive list of system properties for the
JVM, as well as many Liferay system properties. This information can be used for
debugging purposes or to check the configuration of the currently running
portal.

The portal properties tab tab shows an exhaustive list of the portal properties.
These properties can be customized; you can peruse the full list of customizable
properties online at
[http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html).
If you need to check the current value of a particular property, it can be
viewed from this screen without having to shut down the portal or open any
properties files.

### CAPTCHA [](id=captcha-liferay-portal-6-2-user-guide-17-en)

By default, Liferay ships with its own simple CAPTCHA service which is designed
to thwart bots from registering for accounts on sites powered by Liferay. If you
want to instead use Google's reCAPTCHA service, you can enable this setting from
the CAPTCHA tab of the Server Administration page.

Simply check the *Enable ReCAPTCHA* box and enter your public and private keys
into the provided fields, then click *Save*. Liferay Portal will then use
reCAPTCHA instead of simple CAPTCHA.

### Data Migration [](id=data-migration-liferay-portal-6-2-user-guide-17-en)

If you are upgrading from a previous release of Liferay Portal or if you need to
migrate your data from one system to another, the Data Migration tab helps you
to do that without your developers having to write custom scripts.

The first section lets you copy your entire Liferay database from the current
database under which it is running to the database you specify in this set of
fields. You'll need to enter the driver class name (and the driver will need to
be on Liferay's classpath), the JDBC URL of the database to which you'll be
copying your data and the credentials of a user with access to that database.
Once you have all of this information entered, click *Execute* to copy the data.

The next section helps you migrate your documents. If you want to move off of
the Jackrabbit JSR-170 repository to the file system, or to the Jackrabbit
repository from the file system, or to any of the other repositories supported
by the Documents and Media library, you can do so very easily. Make sure you
have already set up your `portal-ext.properties` file so the hook is properly
configured before running this migration. Select the Document Library hook that
represents the location to which you want your documents to be migrated, then
click *Execute*. Your documents will be migrated to the new repository. You can
then shut down Liferay, make the new repository the default in the
`portal-ext.properties` file, and then restart.

You can migrate images from the Image Gallery in the same manner. (In Liferay
6.0 and previous versions, the Document Library and Image Gallery were distinct
repositories. In Liferay 6.1 and later versions, these have been combined into a
single Documents and Media repository.)

### File Uploads [](id=file-uploads-liferay-portal-6-2-user-guide-17-en)

Since Liferay allows users to upload files in various places, you may want to
lock down the type of files and the size of files users are allowed to upload.
The File Uploads tab of the Server Configuration tab lets you set the overall
maximum file size and then override that size for specific applications within
Liferay. You can limit the allowed file extensions generally or by application.
You have a lot of flexibility as to how you want files to be managed within your
portal.

### Mail [](id=mail-liferay-portal-6-2-user-guide-17-en)

Instead of using your Liferay server's `portal-ext.properties` file to configure
a mail server, you can configure a mail server from the Mail tab of the Server
Configuration section of the Control Panel. If your portal is to receive mail
(see, for example, our coverage of the Message Boards portlet in chapter 8), you
can connect a POP mail server. If your portal is to send mail, which is useful
for sending notifications to users, you can connect to an SMTP server. We highly
recommend setting up mail servers for your portal.

Note that if you configure mail server settings here in the Control Panel, these
settings will override any mail server settings in your `portal-ext.properties`
file.

### External Services [](id=external-services-liferay-portal-6-2-user-guide-17-en)

Liferay Portal enables users to upload and share content via the Documents and
Media library, a customizable and permissionable online repository. Users can
upload files of any type to the Documents and Media library. Liferay ships with
PDFBox and uses it to generate automatic previews for certain types of
documents, by default. You can also install three additional tools that offer
higher quality previews and document conversion functionality: OpenOffice or
LibreOffice, ImageMagick and Xuggler. With Liferay configured to use these
tools, you can generate automatic previews for many types of files including text
files, office suite files, PDFs, images, audio files and videos. Users will also
be able to use the conversion functionality to download documents in a variety
of formats. Please see chapter 4 on Documents and Media for more information.

LibreOffice is available here: [LibreOffice](http://www.libreoffice.org),
ImageMagick is available here: [ImageMagick](http://www.imagemagick.org) and
Xuggler is available here: [Xuggler](http://xuggle.com/xuggler/). Make sure to
choose the correct versions of these applications for your operating system. We
recommend that you install the latest stable versions. LibreOffice 3.6,
ImageMagick 6.7.7, and Xuggler 5.4 work with Liferay 6.2. You need to install
LibreOffice and ImageMagick manually but you can install Xuggler from Liferay's
Server Administration Control Panel interface.

---

 ![Tip](../../images/01-tip.png) **Tip:** If you're running Liferay on a Linux
 server and experience a problem enabling Xuggler, check your server's glibc
 version. You might have to update glibc to version 2.6 or later in order for
 Xuggler to work.

---

Once you've installed these tools, you can use the External Services tab of the
Control Panel to configure Liferay to use them.

#### OpenOffice/LibreOffice configuration [](id=openoffice-libreoffice-configuration-liferay-portal-6-2-user-guide-17-en)

OpenOffice and LibreOffice are open source office suites which are usually run
in graphical mode to create documents but they can also be run in "server" mode.
When run in server mode, OpenOffice and LibreOffice can be used to convert
documents to and from all of the file types it supports. Once configured,
Liferay makes use of this feature to automatically convert content on the fly.
You can install OpenOffice or LibreOffice on the same machine upon which Liferay
is running or you can connect to a separate host. 

If you've installed OpenOffice or LibreOffice on the same machine that's running
Liferay, you can start it in server mode with the following command:

    soffice --headless --accept="socket,host=127.0.0.1,port=8100;urp;"
    --nofirststartwizard

Once OpenOffice or LibreOffice has been installed and is running in server mode,
you can configure Liferay to use it either in your `portal-ext.properties` file
or from the Control Panel. To enable OpenOffice/LibreOffice in your
`portal-ext.properties` file, add the following line:

    openoffice.server.enabled=true
    
If OpenOffice or LibreOffice is running on another server or on a non-default
port, you must also specify these values. The default values are as follows:

    openoffice.server.host=127.0.0.1
    openoffice.server.port=8100

By default, when Liferay uses OpenOffice or LibreOffice to perform conversions,
it uses a cache. The first time a document is converted, a copy is saved in the
Liferay temp folder `/liferay/document_conversion/`. When Liferay receives a
conversion request, it checks this folder to see if the converted document
already exists. If the converted document is found, Liferay returns it to the
user. Otherwise, it performs a fresh conversion and saves a copy in the temp
folder. If the cache is turned off, Liferay will always regenerate the file
regardless of whether a previously existing conversion already exists in the
temp folder. You can turn the cache off by setting the following property:

    openoffice.cache.enabled=false

To configure Liferay to use OpenOffice/LibreOffice from the Control Panel,
navigate to the *Server Administration* &rarr; *External Services* page and
check the *Enabled* box for OpenOffice. If OpenOffice/LibreOffice is running on
a non-default port, you must also specify the port number. By default,
OpenOffice runs on port 8100, which is the default port in the Control Panel. If
you have something else running on this port, find an open port and specify it
both in the command to start OpenOffice/LibreOffice in server mode and on the
Control Panel's External Services configuration page. When you are finished,
click *Save*. Now Liferay can perform many types of document conversions.

#### ImageMagick configuration [](id=imagemagick-configuration-liferay-portal-6-2-user-guide-17-en)

Once you've installed the correct version of
[*ImageMagick*](http://www.imagemagick.org) for your operating system, which
should include the installation of Ghostscript, you need to configure Liferay to
use ImageMagick. You can do this either in your `portal-ext.properties` file or
from the Control Panel. To enable ImageMagick in your `portal-ext.properties`
file, add the following lines and make sure the search path points to the
directories for the ImageMagick and Ghostscript executables. You may also need
to configure the path for fonts used by Ghostscript when in Mac or Unix
environments.

    imagemagick.enabled=true
    imagemagick.global.search.path[apple]=/opt/local/bin:/opt/local/share/ghostscript/fonts:/opt/local/share/fonts/urw-fonts
    imagemagick.global.search.path[unix]=/usr/local/bin:/usr/local/share/ghostscript/fonts:/usr/local/share/fonts/urw-fonts
    imagemagick.global.search.path[windows]=C:\\Program Files\\ImageMagick

To enable ImageMagick from the Control Panel, navigate to the *Server
Administration* &rarr; *External Services* page, check the *Enabled* checkbox
for ImageMagick and verify the paths to the ImageMagick and Ghostscript
executables are correct.

Note that some older versions of ImageMagick are unable to properly run with
Liferay. If this is the case, update to the latest version (ImageMagick 6.7.9-6
2012-09-25 Q16 or later). To check for the latest ImageMagick versions, visit
[http://www.imagemagick.org/script/binary-releases.php](http://www.imagemagick.org/script/binary-releases.php).
See
[http://issues.liferay.com/browse/LPS-30291](http://issues.liferay.com/browse/LPS-30291)
for information on efforts to identify incompatible application versions with
Liferay.

#### Xuggler configuration [](id=xuggler-configuration-liferay-portal-6-2-user-guide-17-en)

Since Liferay 6.1.1+, you can install Xuggler completely from the Control Panel.
Navigate to the *Server Administration* &rarr; *External Services* page. From
the dropdown, select the Xuggler `.jar` file that matches your operating system.
Then click on *Install*. 

In order to use Xuggler, you must restart your application server and enable
Xuggler for your portal. You can enable Xuggler either from the Control Panel or
by specifying the portal property that enables Xuggler. 

To enable Xuggler from portal properties, add the following line to your
`portal-ext.properties` file and restart your application server. 

    xuggler.enabled=true

To enable Xuggler from the Control Panel, navigate to the *Server
Administration* &rarr; *External Services* page, check *Enabled*, and click
on *Save*.

That's it! You've successfully configured Documents and Media to use Xuggler for
audio and video files. 

### Script [](id=script-liferay-portal-6-2-user-guide-17-en)

Liferay includes a scripting console which lets administrators execute migration
or management code instantly. Several scripting languages are supported,
including JavaScript, Groovy, Python, Ruby and Beanshell. This guide contains a
[chapter](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/using-scripting-for-advanced-flexibilit-liferay-portal-6-2-user-guide-19-en)
that explains how to use Liferay's scripting console and provides some examples.
For further information about Liferay's APIs, please refer to the [Liferay
Development
Gudie](https://www.liferay.com/documentation/liferay-portal/6.2/development),
[*Liferay in Action*](http://manning.com/sezov), or Liferay's
[Javadocs](http://docs.liferay.com/portal/6.2/javadocs).

### Shutdown [](id=shutdown-liferay-portal-6-2-user-guide-17-en)

If you ever need to shut down your Liferay Portal server while users are logged
in, you can use the Shutdown tab to inform your logged-in users of the impending
shutdown. You can define the number of minutes until the shutdown and a custom
message that will be displayed.

Users will see your message at the top of their portal pages for the duration of
time you specified. When the time expires, all portal pages will display a
message saying the portal has been shut down. At this point, the server will
need to be restarted to restore access. Next, let's examine how to manage
multiple portal instances.

## Portal Instances [](id=portal-instances-liferay-portal-6-2-user-guide-17-en)

Liferay Portal allows you to run more than one portal instance on a single
server. The Portal Instances section of the Control Panel appears under the
Configuration heading. This section lets you manage multiple portal instances
from a single Liferay installation. Each portal instance's data is kept separate
from every other portal instance's data. All portal data, however, is kept in
the same database.

Each portal instance requires its own domain name. Liferay directs users to the
proper portal instance based on this domain name. So before you configure an
instance, configure its domain name in your network first. When you're ready to
add an instance, click the *Add* button here.

You'll be prompted for four fields and a check box:

**Web ID:** A general convention is to use the domain name for this. It's a
user-generated ID for the instance.

**Virtual Host:** Put the domain name you configured in your network here. When
**users** are directed to your Liferay server via this domain name, Liferay will
then be able to send them to the proper portal instance.

**Mail Domain:** Enter the domain name for the mail host for this instance.
Liferay will use this to send email notifications from the portal.

**Max Users:** Enter the maximum numbers of user accounts you would like your
portal instance to support.

**Active:** Use this check box to choose whether to create an active or an
inactive portal instance.

When you are finished filling out the form, click *Save*. Now navigate to the
portal using your new domain name. You will see you are brought to what looks
like a clean install of Liferay. This is your new portal instance which can now
be configured any way you like.

## Summary [](id=summary-liferay-portal-6-2-user-guide-17-en)

In this chapter, we examined how to configure Liferay's portal settings. We
looked at the different authentication options provided by Liferay. You can
configure Liferay so that users can authenticate via LDAP, CAS, Facebook, NTLM,
OpenID, OpenSSO, or SiteMinder. We also examined some general configuration
options for the portal users, such as reserved credentials and default user
associations. 

Next, we learned how to configure mail host names, email notifications, content
sharing, identification information, and portal display settings. We showed how
to add custom fields to various portal entities such as users, pages, documents,
wiki articles, message board posts, and more. 

We also saw how to view and configure overall server settings. We saw how to
view the memory currently being used by the server, as well as how to initiate
garbage collection, a thread dump, search engine re-indexing and the clearing of
various caches. We learned how to debug parts of the portal by changing log
levels and by viewing the various properties defined in the portal. We learned
how to properly notify users that the portal is about to shut down and how to
set up external services like OpenOffice integration.

Lastly, we looked at how to create multiple portal instances on a single
installation of Liferay and we showed how to view currently installed plugins.
We hope this information helps you become an effective Liferay Portal
Administrator.
