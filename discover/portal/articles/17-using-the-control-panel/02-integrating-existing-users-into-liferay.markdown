# Integrating Existing Users into Liferay [](id=integrating-existing-users-into-liferay)

Liferay provides a number of user authentication options. You can configure
Liferay to connect to LDAP or NTLM servers so users from those environments can
log in to your Liferay server. You can also configure Liferay to allow users to
log in via Facebook or OpenId accounts. Liferay's Control Panel provides
interfaces for setting up user authentication using the following services:

- LDAP
- SSO
- CAS
- Facebook
- NTLM
- OpenId
- Crowd
- Open SSO
- SiteMinder
- Shibboleth
- SAML

Let's start by learning how to connect Liferay to an LDAP server.

## LDAP [](id=ldap)

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
use the Add button beneath this heading to add LDAP servers. We explain how to
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

### Adding LDAP Servers [](id=adding-ldap-servers)

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

![Figure x: Testing LDAP Users](../../images/server-configuration-testing-ldap-users.jpg)

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

![Figure x: Mapping LDAP Groups](../../images/server-configuration-mapping-ldap-groups.jpg)

Once you've set all your options and tested your connection, click *Save*. From
here, you can add another LDAP server or set just a few more options that apply
to all of your LDAP server connections.

### LDAP Options Not Available in the GUI [](id=ldap-options-not-available-in-the-gui)

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

## SSO [](id=sso)

Single Sign-On solutions allow you to provide a single login credential for
multiple systems. This allows you to have people authenticate to the Single
Sign-On product and they will be automatically logged in to Liferay and to other
products as well.

Liferay supports several single sign-on solutions. Of course, if your product is
not yet supported, you may choose to implement support for it yourself by use of
the extension environment. Alternatively, your organization can choose to
sponsor support for it. Please contact `sales@liferay.com` for more information
about this.

## Authentication: Central Authentication Service (CAS) [](id=authentication-central-authentication-service-cas)

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

## Authentication: Facebook [](id=authentication-facebook)

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

## Authentication: NTLM [](id=authentication-ntlm)

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

## Authentication: OpenID [](id=authentication-openid)

OpenID is a new single sign-on standard which is implemented by multiple
vendors. The idea is multiple vendors can implement the standard and then users
can register for an ID with the vendor they trust. The credential issued by that
vendor can be used by all the web sites that support OpenID. Some high profile
OpenID vendors are [AOL](http://openid.aol.com/screenname),
[LiveDoor](http://profile.livedoor.com/username), and
[LiveJournal](http://username.livejournal.com/). Please see the
[OpenID site](http://www.openid.net/) for a more complete list.

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
[OpenID4Java](http://code.google.com/p/openid4java/) to implement the feature.

OpenID is enabled by default in Liferay but can be disabled here.

## Atlassian Crowd [](id=atlassian-crowd)

Atlassian Crowd is a web-based Single Sign-On product similar to CAS. Crowd can
be used to manage authentication to many different web applications and
directory servers.

Because Atlassian Crowd implements an OpenID producer, Liferay works and has
been tested with it. Simply use the OpenID authentication feature in Liferay to
log in using Crowd.

## Authentication: OpenSSO [](id=authentication-opensso)

OpenSSO is an open source single sign-on solution that comes from the code base
of Sun's System Access Manager product. Liferay integrates with OpenSSO,
allowing you to use OpenSSO to integrate Liferay into an infrastructure that
contains a multitude of different authentication schemes against different
repositories of identities.

You can set up OpenSSO on the same server as Liferay or a different box. Follow
the instructions at the [OpenSSO site](http://opensso.dev.java.net/) to install
OpenSSO. Once you have it installed, create the Liferay administrative user in
it. Users are mapped back and forth by screen names. By default, the Liferay
administrative user has a screen name of *test*, so in OpenSSO, you would
register the user with the ID of *test* and an email address of
*test\@liferay.com*. Once you have the user set up, log in to Open SSO using this
user.

In the same browser window, go to the URL for your server running Liferay and
log in as the same user, using the email address *test\@liferay.com*. Go to the
Control Panel and click *Settings &rarr; Authentication &rarr; OpenSSO*. Modify
the three URL fields (Login URL, Logout URL, and Service URL) so they point to
your OpenSSO server (i.e., only modify the host name portion of the URLs), click
the *Enabled* check box, and then click *Save*. Liferay then redirects users
to OpenSSO when they click the *Sign In* link.

## Authentication: SiteMinder [](id=authentication-siteminder)

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

## Shibboleth [](id=shibboleth)

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

![Figure x: You can enable/disable Shibboleth authentication for Liferay by navigating to the *Control Panel* &rarr; *Portal Settings* &rrar; *Authentication* &rarr; *Shibboleth*.](../../images/shibboleth.png)

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

## SAML [](id=saml)

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

### Setting up Liferay as a SAML Identity Provider [](id=setting-up-liferay-as-a-saml-identity-provider)

In order to set Liferay up to act as a SAML Identity Provider, follow these
steps:

1. Install the SAML 2.0 Provider EE app, either via the Control Panel's
   Marketplace interface or manually. To confirm that the plugin was
   successfully deployed, look for the *SAML Admin* entry in the Control Panel.

    ![Figure x: The SAML Admin entry appears as a sub-section of the Configuration section of the Control Panel.](../../images/saml-admin.png)

    To access the SAML Admin interface, click on *Admin* &rarr; *Control Panel*
    and then on *SAML Admin*. 

2. To begin configuring Liferay to use SAML, you need to select a SAML role
   for Liferay and you need to choose an entity ID.
   
    ![Figure x: Select a SAML role for Liferay and enter an entity ID.](../../images/saml-initial-config.png)
 
    The SAML role can be set to Identity Provider or Service Provider.
    Select the *Identity Provider* SAML role. A single Liferay instance can
    serve as an identity provider or as a service provider, but not both.
    Enter *liferaysamlidp* if you're setting up an example Liferay instance.
    Alternatively, choose your own entity ID. Then click *Save* and look for a
    new section to appear, entitled Certificate and Private Key.

3. The Certificate and Private Key section allows you to enter information to
   create a keystore for SAML. Enter the following information:
    
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
    However, this portlet property can be changed: 

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

    Leave the SAML role and Entity role as you configured them in step 3. In the
    Certificate and Private Key section, enter the same information that you
    entered when you generated your keystore, then click *Save*. After saving
    your certificate and private key information, you can view information about
    your certificate or download your certificate.

![Figure 17.2: After saving your certificate and private key information, you can view information about your certificate or download your certificate.](../../images/saml-admin-certificate-saved.png)

5. Finally, after you've saved your certificate and private key information,
   check the *Enabled* box at the top of the General tab and click *Save*.
   Great! You've successfully set Liferay up as a SAML Identity Provider!
 
To configure Liferay's SAML Identity Provider Settings, navigate to the Identity
Provider tab of the SAML Admin Control Panel portlet. Of course, setting up
Liferay as a SAML Identity Provider is only useful if you can connect to one or
more SAML Service Providers. Navigate to the Service Provider Connections tab of
the SAML Admin Control Panel portlet and click on the *Add Service Provider*
button to add a SAML Service Provider. Right now, we don't have one to add but
next, we'll learn how to set Liferay up as a SAML Service Provider.

After you've set up another Liferay instance as a Service Provider, you can come
back to this Liferay instance's Control Panel and add the Service Provider:
*Control Panel* &rarr; *SAML Admin* &rarr; *Service Provider Connections* &rarr;
*Add Service Provider*.

### Setting up Liferay as a SAML Service Provider [](id=setting-up-liferay-as-a-saml-service-provider)

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
   Identity Provider or Service Provider. Select the *Service Provider*
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
   followed the previous instructions and configured a separate Liferay instance
   as an Identify provider, you'd enter the following information:

    - Name: *Liferay IdP*
    - Entity ID: *liferaysamlidp*
    - Metadata URL: http://localhost:8080/c/portal/saml/metadata (test this URL
      first)

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
We recommend, however, using the Control Panel SAML interface because it
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

+$$$

**Limitation:** The Liferay SAML plugin can only be used with a single virtual
host. Technically, this means that in the SAML metadata for Liferay, only one
binding can be added in this form:

    <md:EntityDescriptor>
    ...
    <md:SPSSODescriptor>
    ...
    <md:AssertionConsumerService Binding="urn:oasis:names:tc:SAML:2.0:bindings:HTTP-POST" Location="https://portal.domain.com/c/portal/saml/acs" index="1" isDefault="true" />
    ...
    </md:SPSSODescriptor>
    </md:EntityDescriptor>

$$$

### Setting Up Liferay as a SAML Service Provider in a Clustered Environment

If you want to use Liferay's SAML 2.0 Provider EE plugin as a SSO solution for a
clustered Liferay environment, follow the steps in this section. Before
proceeding, make sure that the following assumptions apply to your scenario.

Suppose that your clustered Liferay environment consists of multiple Liferay
nodes that sit behind a load balancer. Your Liferay nodes could be Liferay
Tomcat bundles, for example. Your load balancer could be software like Apache
web server or hardware like F5 BIG-IP. Suppose further that you want want the
nodes of your Liferay cluster to serve as SAML Service Providers. And suppose
that you have a third-party participating as the SAML Identity Provider. (For
testing purposes, you could create a separate Liferay instance to serve as the
SAML IdP.)

If your situation fits the scenario described above, follow these steps:

1. Configure one node of your Liferay cluster as a SAML SP using the
   instructions of the previous section.

2. Ensure that this Liferay node is using the fully qualified name of the load
   balancer (e.g., `FQN.LB.HOST`) as the value of the `web.server.host` property
   in the node's `portal-ext.properties` file.

        #
        # Set the hostname that will be used when the portlet generates URLs.
        # Leaving this blank will mean the host is derived from the servlet
        # container.
        #
        web.server.host=FQN.LB.HOST


3. Repeat steps 1 and 2 for each other Liferay node.

4. Copy the `[Liferay Home]/data/keystore.jks` file from the first Liferay node
   to each other Liferay node. This file is the Java keystore that's created by
   the SAML 2.0 Provider EE plugin. The keystore contains the valid or
   self-signed certificate managed by the SAML 2.0 EE Provider plugin.

5. At this point, all of the Liferay nodes have the same SAML SP configuration
   and each of them can respond to web requests and handle the SAML protocol. To
   test your SSO solution, sign into Liferay via your load balancer, navigate to
   a few pages of a few different sites, and then log out.

Great! Now you know how to configure Liferay either as a SAML identity provider
or a service provider. You also know how to configure the Liferay SAML in a
clustered environment.
