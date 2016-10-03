# Integrating Existing Users into Liferay [](id=integrating-existing-users-into-liferay)

Liferay provides a number of user authentication options. You can configure
Liferay to connect to LDAP or NTLM servers so users from those environments can
log in to your Liferay server. You can also configure Liferay to allow users to
log in via Facebook or OpenId accounts. Liferay's Control Panel provides
interfaces for setting up user authentication using the following services:

- Open SSO
- CAS
- NTLM
- LDAP
- OpenId
- Facebook
- Google
<!-- - Crowd -->
<!-- - SSO -->
<!-- - SiteMinder -->
<!-- - Shibboleth -->
<!-- - SAML -->

You probably already know which service you'll be using, but before skipping to the
relevant section consider what scope you want to affect when configuring
authentication.

## Authentication Configuration Scope

Many of the authentication configurations covered here can be made in two
places: [Instance
Settings](/discover/portal/-/knowledge_base/7-0/setting-up-a-liferay-instance)
and [System
Settings](/discover/portal/-/knowledge_base/7-0/system-wide-settings). So which
one should you use? It depends on what scope you'd like to affect. If you'd like
to affect the entire system (all Liferay instances configured for your Liferay
installation), use System Settings. With System Settings you'll be setting the
final value for the entire system (if the entry in System Settings lists the
scope as *System*) or a default value that can be overridden at a more granular
scope (if the entry has *Default Settings for All Instances* as its scope). If
you'd like to affect only one Liferay instance specifically, make your
configuration in Instance Settings.

Another consideration is whether you'd like to be able to maintain your settings
in a portable `.cfg` file that can be deployed to another Liferay installation.
If you use System Settings, you can simply make your configurations, save them,
and [export a configuration
file](discover/portal/-/knowledge_base/7-0/system-settings#exporting-and-importing-configurations).

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
Control Panel and click *Configuration &rarr; Instance Settings &rarr;
Authentication &rarr; OpenSSO*. Modify the three URL fields (Login URL, Logout
URL, and Service URL) so they point to your OpenSSO server (i.e., only modify
the host name portion of the URLs), click the *Enabled* check box, and then
click *Save*. Liferay then redirects users to OpenSSO when they click the *Sign
In* link.

## Authentication: Central Authentication Service (CAS) [](id=authentication-central-authentication-service-cas)

CAS is an authentication system originally created at Yale University. It is a
widely-used open source single sign-on solution and was the first SSO product to
be supported by Liferay.

Please follow the documentation for CAS to install it on your application server
of choice.

Your first step will be to copy the CAS client JAR file to Liferay's library
folder. On Tomcat, this is in `[Tomcat Home]/webapps/ROOT/WEB-INF/lib`. Once you
do this, the CAS client will be available to Liferay the next time you start it.

The CAS Server application requires a properly configured Secure Socket Layer
certificate on your server to work. If you wish to generate one yourself, you
will need to use the `keytool` utility that comes with the JDK. Your first step
is to generate the key. Next, you export the key into a file. Finally, you
import the key into your local Java key store. For public, Internet-based
production environments, you will need to either purchase a signed key from a
recognized certificate authority or have your key signed by a recognized
certificate authority. For Intranets, you should have your IT department
pre-configure users' browsers to accept the certificate so they don't get
warning messages about the certificate.

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
This is a simple matter of navigating to the CAS tab in *Control Panel &rarr;
Configuration &rarr; Instance Settings &rarr; Authentication*. Enable CAS
authentication and then modify the URL properties to point to your CAS server.

**Enabled:** Check this box to enable CAS single sign-on.

**Import from LDAP:** A user may be authenticated from CAS and not yet exist in
Liferay. Select this to automatically import users from LDAP if they do not
exist in Liferay.

The rest of the settings are various URLs, with defaults included. Change
*localhost* in the default values to point to your CAS server. When you are
finished, click *Save*. After this, when users click the *Sign In* link, they
will be directed to the CAS server to sign in to Liferay.

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

## LDAP [](id=ldap)

You can use the LDAP tab of the Authentication page to connect Liferay to an
LDAP directory. 

**Enabled:** Check this box to enable LDAP Authentication.

**Required:** Check this box if LDAP authentication is required. Liferay will
then not allow a user to log in unless he or she can successfully bind to the
LDAP directory first. Uncheck this box if you want to allow users with Liferay
accounts but no LDAP accounts to log in to Liferay.

**LDAP Servers:** Liferay supports connections to multiple LDAP servers. You can
use the Add button beneath this heading to add LDAP servers. We explain how to
configure new LDAP servers below.

**Import/Export:** You can import and export user data from LDAP directories
using the following options:

- *Enable Import:* Check this box to cause Liferay to do a mass import from
  your LDAP directories. If you want Liferay to only synchronize users when they
  log in, leave this box unchecked. Definitely leave this unchecked if you are
  working in a clustered environment. Otherwise, all of your nodes would try to
  do a mass import when each of them starts up.

- *Enable Export:* Check this box to enable Liferay to export user accounts
  from the database to LDAP. Liferay uses a listener to track any changes made
  to the `User` object and will push these changes out to the LDAP server
  whenever the `User` object is updated. Note that by default on every login,
  fields such as `LastLoginDate` are updated. When export is enabled, this has
  the effect of causing a user export every time the user logs in. You can
  disable this by setting the following property in your `portal-ext.properties`
  file:

        users.update.last.login=false

- *Enable Import on Startup:* Check this box to have Liferay run the import
  when it starts up. Note: This box only appears if you check the *Enable Import* 
  box described above.

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
messages in *System Settings &rarr; Foundation &rarr; System LDAP
Configuration*.

Once you finish configuring LDAP, click the *Save* button. Next, let's look
at how to add LDAP servers.

### Adding LDAP Servers [](id=adding-ldap-servers)

The Add button beneath the LDAP servers heading allows you to add LDAP servers.
You can add an LDAP server from the LDAP Servers entry in Systyem Settings, too.
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

- *Base Provider URL:* This tells Liferay where the LDAP server is located.
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

Once you set all your options and test your connection, click *Save*. From here,
you can add another LDAP server or set just a few more options that apply to all
of your LDAP server connections.

### LDAP Options Available in System Settings

Although most LDAP configuration can be done from Instance Settings, there are
several configuration parameters that are only available to be set System
Settings. In previous versions of Liferay system scoped settings for LDAP were
[set in the `portal.properties`
file](https://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#LDAP)
and modified using a `portal-ext.properties` file. Those settings are now all
available in System Settings.

If you need to change any of these options, navigate to *Control Panel* &rarr;
*Configuration* &rarr; *System Settings*, go to the Foundation section, and
find the entries with LDAP in the title.

In the LDAP Auth entry, you can set the authentication method and the password
encryption algorithm. The Bind authentication method is preferred by
most vendors so you don't have to worry about encryption strategies. Password
compare does exactly what it sounds like: it reads the user's password out of
LDAP, decrypts it and compares it with the user's password in Liferay, syncing
the two. If you use Password-compare, you can also choose which encryption
algorithm to use for the comparison.

You can use the LDAP Import entry in System Settings to configure import
settings from LDAP. One example is the import methos. If you set this to User,
Liferay will import all users from the specified portion of the LDAP tree. If
you set this to Group Liferay will search all the groups and import the users
in each group. If you have users who do not belong to any groups, they will not
be imported.

The error properties like *Error password age keywords* in the System LDAP
Configuration entry of System Settings let you set a list of phrases from error
messages which can possibly be returned by the LDAP server. When a user binds to
LDAP, the server can return *controls* with its response of success or failure.
These controls contain a message describing the error or the information that is
coming back with the response.  Though the controls are the same across LDAP
servers, the messages can be different. The properties described here contain
snippets of words from those messages and will work with Red Hat's Fedora
Directory Server. If you are not using that server, the word snippets may not
work with your LDAP server. If they don't, you can replace the values of these
properties with phrases from your server's error messages. This will enable
Liferay to recognize them. Next, let's look at the Single Sign-On solutions
Liferay supports.

Basically, if there's a configuration you need to set up Liferay with LDAP, and
you don't find it in Instance Settings, look in the LDAP System Settings
entries.

## Authentication: OpenID [](id=authentication-openid)

OpenID is a single sign-on standard implemented by multiple vendors. Users can
register for an ID with the vendor they trust. The credential issued by that
vendor can be used by all the web sites that support OpenID. Some high profile
OpenID vendors are [AOL](http://openid.aol.com/screenname),
[LiveDoor](http://profile.livedoor.com/username), and
[LiveJournal](http://username.livejournal.com/). Please see the [OpenID
site](http://www.openid.net/) for a more complete list.

With OpenID, a user no longer has to register for a new account on every site in
which he or she wants to participate. Users register on *one* site (the OpenID
provider's site) and then use those credentials to authenticate to many web
sites which support OpenID. Web site owners sometimes struggle to build
communities simply because users are reluctant to register for *another*
account. Supporting OpenID removes that barrier, making it easier for site
owners to build their communities. All of the account information is kept with
the OpenID provider, making it much easier to manage this information and keep
it up to date.

Liferay can act as an OpenID consumer, allowing users to automatically
register and sign in with their OpenID accounts. Internally, the product uses
[OpenID4Java](http://code.google.com/p/openid4java/) to implement the feature.

OpenID is enabled by default in Liferay but can be disabled in Control Panel
&rarr; Configuration &rarr; Instance Settings.

## Authentication: Facebook [](id=authentication-facebook)

Users can log in to Liferay using their Facebook accounts. To enable this
feature, you simply need to select the *Enable* box and enter the Application ID
and Application Secret which should have been provided to you by Facebook.
Facebook SSO works by taking the primary Facebook email address and searching
for the same email address in Liferay's `User_` table. If a match is found, the
user is automatically signed in (provided the user clicked *allow* from the
Facebook dialog). If there isn't a match, the user is prompted in Liferay to add
a user from Facebook. Once selected, a new user is created by retrieving four
fields from Facebook (first name, last name, email address and gender).

## Atlassian Crowd [](id=atlassian-crowd)

Atlassian Crowd is a web-based Single Sign-On product similar to CAS. Crowd can
be used to manage authentication to many different web applications and
directory servers.

Because Atlassian Crowd implements an OpenID producer, Liferay works and has
been tested with it. Simply use the OpenID authentication feature in Liferay to
log in using Crowd.

## Authentication: SiteMinder [](id=authentication-siteminder)

SiteMinder is a single sign-on implementation from Computer Associates. Liferay
5.2 introduced built-in integration with SiteMinder. SiteMinder uses a custom
HTTP header to implement its single sign-on solution.

To enable SiteMinder authentication in Liferay, check the *Enabled* box on the
*SiteMinder* tab. If you are also using LDAP with Liferay, you can check the
*Import from LDAP* box. If this box is checked, users authenticated from
SiteMinder who do not exist in Liferay will be imported from LDAP.

The last field defines the header SiteMinder is using to keep track of the user.
The default value is already populated. If you have customized the field for
your installation, enter the custom value here.

When you are finished, click *Save*. Now that we've looked at various options
for integrating existing users into Liferay, let's look at other Liferay portal
settings.

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

