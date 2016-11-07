# Integrating Existing Users into Liferay [](id=integrating-existing-users-into-liferay)

Liferay provides a number of user authentication options. You can configure LDAP
or SSO servers so users from those environments can log in to your Liferay
server. You can also configure Liferay to allow users to log in via Facebook or
OpenId accounts. Liferay's Control Panel provides interfaces for setting up user
authentication using the following services:

- Open SSO
- CAS
- NTLM
- LDAP
- OpenId
- Facebook
- Google
- Atlassian Crowd

You probably already know which service you'll be using, but before skipping to the
relevant section, you must consider your authentication scope.

## Authentication Configuration Scope [](id=authentication-configuration-scope)

In the user interface, you can configure many authentication settings in two
places: 
[Instance Settings](/discover/portal/-/knowledge_base/7-0/setting-up-a-liferay-instance)
and [System Settings](/discover/portal/-/knowledge_base/7-0/system-wide-settings) 
(under the Foundation heading). So which one should you use? It depends on the
scope. 

To affect the entire system (all Liferay instances in your Liferay
installation), use System Settings. With System Settings you can set the final
value for the entire system if the entry in System Settings lists the scope as
*System*, or you can set a default value that can be overridden at a more
granular scope if the entry has *Default Settings for All Instances* as its
scope. 

To affect only one Liferay instance specifically, make your configuration in
Instance Settings.

Here's the list of System Settings entries that overlap with the Instance
Settings &rarr; Authentication entries:

- CAS Server
- Google Authorization
- LDAP Servers
- LDAP Auth
- LDAP Export
- LDAP Import
- NTLM
- OpenID
- OpenSSO
- Facebook Connect

You can also configure authentication in a portable OSGi configuration file that
can be deployed to another Liferay installation. If you use System Settings, you
can generate this file by making your configurations, saving them, and 
[exporting a configuration file](discover/portal/-/knowledge_base/7-0/system-settings#exporting-and-importing-configurations).

## SSO in General[](id=sso)

Single Sign-On (SSO) allow you to provide a single login credential for multiple
systems. With SSO, users authenticate to the SSO product and are logged in to
Liferay (and other products) automatically.

@product@ supports several SSO products. If your desired SSO solution isn't
supported, @product@ is [extensible](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/fundamentals#architecture),
and your developers can write a module to support it. Alternatively, your
organization can choose to sponsor support for it. Please contact
`sales@liferay.com` for more information about this.

## Authentication: OpenSSO [](id=authentication-opensso)

<!-- This needs thorough review. OpenSSO doesn't exist anymore; it's OpenAM.
-Rich --> 

OpenSSO is an open source SSO solution that comes from the code base
of Sun's System Access Manager product. Liferay integrates with OpenSSO,
allowing you to use OpenSSO to integrate Liferay into an infrastructure that
contains a multitude of different authentication schemes against different
repositories of identities.

You can set up OpenSSO on the same server as Liferay or a different box. Follow
the instructions at the [OpenSSO site](http://opensso.dev.java.net/) to install
OpenSSO. Once it's installed create the Liferay administrative user in OpenSSO.
Users are mapped back and forth by screen names. By default, the Liferay
administrative user has a screen name of *test*, so in OpenSSO, you would
register the user with the ID of *test* and an email address of
*test\@liferay.com*. Once you have the user set up, log in to Open SSO using
this user.

In the same browser window where you logged in, go to the URL for your server
running Liferay and log in as the same user, using the email address
*test\@liferay.com*. Go to the Control Panel and click *Configuration &rarr;
Instance Settings &rarr; Authentication &rarr; OpenSSO*. Modify the three URL
fields (Login URL, Logout URL, and Service URL) so they point to your OpenSSO
server (in other words, only modify the host name portion of the URLs), click
the *Enabled* check box, and then click *Save*. Liferay then redirects users to
OpenSSO when they click the *Sign In* link.

## Authentication: Central Authentication Service (CAS) [](id=authentication-central-authentication-service-cas)

CAS is an authentication system originally created at Yale University. It is a
widely-used open source single sign-on solution and was the first SSO product to
be supported by Liferay. Liferay's CAS module includes the CAS client, so
there's no need to install it separately.

The CAS Server application requires a properly configured Secure Socket Layer
certificate on your server to work. To generate one yourself, use the `keytool`
utility that comes with the JDK. First generate the key, then export the key
into a file. Finally, import the key into your local Java key store. For
public, Internet-based production environments, you will need to either purchase
a signed key from a recognized certificate authority or have your key signed by
a recognized certificate authority. For Intranets, you should have your IT
department pre-configure users' browsers to accept the certificate so they don't
get warning messages about the certificate.

To generate a key, use the following command:

    keytool -genkey -alias tomcat -keypass changeit -keyalg RSA

Instead of the password in the example (`changeit`), use a password you will
remember. If you are not using Tomcat, you may want to use a different alias as
well. For first and last names, enter `localhost` or the host name of your
server. It cannot be an IP address.

To export the key to a file, use the following command:

    keytool -export -alias tomcat -keypass changeit -file server.cert

Finally, to import the key into your Java key store, use the following command:

    keytool -import -alias tomcat -file server.cert -keypass changeit -keystore $JAVA_HOME/jre/lib/security/cacerts

If you are on a Windows system, replace `$JAVA_HOME` above with `%JAVA_HOME%`.
Of course, all of this needs to be done on the system where CAS is running.

Once your CAS server is up and running, configure Liferay to use it. Navigate to
the CAS tab in *Control Panel &rarr; Configuration &rarr; Instance Settings
&rarr; Authentication*. Enable CAS authentication and then modify the URL
properties to point to your CAS server.

**Enabled:** Check this box to enable CAS single sign-on.

**Import from LDAP:** A user may be authenticated from CAS and not yet exist in
Liferay. Select this to import users from LDAP automatically if they do not
exist in Liferay.

The rest of the settings are various URLs, with defaults included. Change
*localhost* in the default values to point to your CAS server. When you are
finished, click *Save*. After this, when users click the *Sign In* link, they
will be directed to the CAS server to sign in to Liferay.

## Authentication: NTLM [](id=authentication-ntlm)

<!-- This needs thorough review. Microsoft's new browser is Edge; this whole
section might need a complete rewrite. -Rich --> 

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

@product@ fully supports LDAP as a user store. Use the LDAP tab in Instance
Settings's Authentication page to connect Liferay to an LDAP directory. 

**Enabled:** Check this box to enable LDAP Authentication.

**Required:** Check this box if LDAP authentication is required. Liferay then
won't allow a user to log in unless he or she can successfully bind to the LDAP
directory first. Uncheck this box if users with Liferay accounts but no LDAP
accounts can log in to Liferay.

**LDAP Servers:** @product@ supports connections to multiple LDAP servers. Use the
Add button beneath this heading to add LDAP servers. Each LDAP server has the
following configuration options: 

**Import/Export:** These options define how @product@ imports and exports users:

- *Enable Import:* Check this box to cause @product@ to do a mass import from
  your LDAP directories. Leave this unchecked to keep the default behavior,
  which synchronizes users only when they log in. Definitely leave this
  unchecked if you are working in a clustered environment. Otherwise, all 
  your nodes would try to do a mass import when each of them starts.

- *Enable Export:* Enabling this exports users from Liferay's database to LDAP.
  Liferay uses a listener to track any changes made
  to the `User` object and pushes these changes to the LDAP server
  whenever the `User` object is updated. Note that by default on every login,
  fields such as `LastLoginDate` are updated. When export is enabled, this causes
  a user export every time the user logs in. You can
  disable this by setting the following property in your `portal-ext.properties`
  file:

        users.update.last.login=false

- *Enable Import on Startup:* Check this box to have Liferay run the import
  when it starts up. Note: This box only appears if you check the *Enable Import* 
  box described above.

**Use LDAP Password Policy:** Liferay uses its own password policy by default.
This can be configured on the Control Panel's Password Policies page. Check
the *Use LDAP Password Policy* box if you want to use the password policies
defined by your LDAP directory. Once this is enabled, the Password Policies tab
states that you are not using a local password policy. You must now use your
LDAP directory's mechanism for setting password policies. Liferay does this by
parsing the messages in the LDAP controls returned by your LDAP server. By
default, the messages in the LDAP controls that Liferay is looking for are the
messages returned by the Fedora Directory Server. If you are using a different
LDAP server, you will need to customize the messages in *System Settings &rarr;
Foundation &rarr; System LDAP Configuration*.

<!-- Is the bit about Fedora Directory still true? Where are these settings; the
above was a guess. -Rich -->

Once you finish configuring LDAP, click the *Save* button. Next, let's look
at how to add LDAP servers.

### Adding LDAP Servers [](id=adding-ldap-servers)

The Add button beneath the LDAP servers heading allows you to add LDAP servers.
You can add an LDAP server from the LDAP Servers entry in System Settings, too.
If you have more than one, you can arrange the servers by order of preference
using the up/down arrows. When you add an LDAP Server, you must provide
several pieces of data so Liferay can bind to that LDAP server and search for
user records. Regardless of how many LDAP servers you add, each server has the
same configuration options.

**Server Name:** Enter a name for your LDAP server.

**Default Values:** Several leading directory servers are listed here. If you
are using one of these, select it and click the *Reset Values* button. The rest
of the form will be populated with the proper default values for that directory.

<!-- If we have defaults above for Fedora Directory server, wouldn't the user be
able to switch that default here? -Rich -->

**Connection:** These settings cover the connection to LDAP.

- *Base Provider URL:* The link to the LDAP server. Make sure the Liferay server
  can communicate with the LDAP server. If there is a firewall between the two
  systems, check to make sure the appropriate ports are opened.

- *Base DN:* The Base Distinguished Name for your LDAP directory. It is
  usually modeled after your organization. For a commercial organization, it may
  look similar to this: `dc=companynamehere,dc=com`.

- *Principal:* By default, the administrator ID is populated here. If you have
  removed the default LDAP administrator, you will need to use the fully
  qualified name of the administrative credential you use instead. You need an
  administrative credential because Liferay uses this ID to synchronize
  user accounts to and from LDAP .

- *Credentials:* This is the password for the administrative user.

This is all you need to make a regular connection to an LDAP directory. The rest
of the configuration is optional. The default attribute mappings usually 
provide enough data to synchronize back to the Liferay database when a user
attempts to log in. To test the connection to your LDAP server, click the *Test
LDAP Connection* button.

If you are running your LDAP directory in SSL mode to prevent credential
information from passing through the network unencrypted, you must perform extra
steps to share the encryption key and certificate between the two systems.

For example, if your LDAP directory is Microsoft Active Directory on Windows
Server 2003, you'd share the certificate like this:

Click *Start* &rarr; *Administrative Tools* &rarr; *Certificate Authority*.
Highlight the machine that is the certificate authority, right-click on it, and
click *Properties*. From the General menu, click *View Certificate*. Select the
Details view, and click *Copy To File*. Use the resulting wizard to save the
certificate as a file. As with the CAS install (see the below section entitled
*Single Sign-On*), you will need to import the certificate into the *cacerts
keystore*. The import is handled by a command like the following:

    keytool -import -trustcacerts -keystore /some/path/jdk[version]/jre/lib/security/cacerts -storepass changeit -noprompt -alias MyRootCA -file /some/path/MyRootCA.cer

The *keytool* utility ships as part of the Java SDK.

Once this is done, go back to the LDAP page in the Control Panel. Modify the
LDAP URL in the Base DN field to the secure version by changing the protocol to
`https` and the port to `636` like the following:

    ldaps://myLdapServerHostname:636

Save the changes. Your Liferay Portal now encrypts its authentication to LDAP.

**Users:** This section contains settings for finding users in your LDAP
directory.

- *Authentication Search Filter:* Use the search filter box to determine
  the search criteria for user logins. By default, Liferay uses users' email
  addresses for their login names. If you have changed this setting, you must
  modify the search filter here, because the default uses the email address
  attribute from LDAP as a search criterion. For example, if you changed
  Liferay's authentication method to use screen names instead of the email
  addresses, you would modify the search filter so it can match the entered log
  in name:

        (cn=@screen_name@)

- *Import Search Filter:* Depending on the LDAP server, there are different
  ways to identify the user. The default setting is usually fine:

        (objectClass=inetOrgPerson)

    If you want to search for only a subset of users or users that have
    different LDAP object classes, you can change this.

- *User Mapping:* The next series of fields defines mappings from
  LDAP attributes to Liferay fields. Though your LDAP user attributes may be
  different from LDAP server to LDAP server, there are five fields Liferay
  requires to be mapped for the user to be recognized: 

    +   *Screen Name* (e.g., *uid*)

    +   *Password* (e.g., *userPassword*)

    +   *Email Address* (e.g., *mail* or *email*)

    +   *First Name* (e.g., *name* or *givenName*)

    +   *Last Name* (e.g., *sn*)

    If you want to import LDAP groups as Liferay user groups, make sure to
    define a mapping for the Liferay group field so that membership information
    is preserved:

    +   *Group* (e.g., *member*)

    The other LDAP user mapping fields are optional.

The Control Panel provides default mappings for commonly used LDAP attributes.
You can also add your own mappings.

- *Test LDAP Users:* Once you have your attribute mappings set up (see above),
  click the *Test LDAP Users* button and Liferay attempts to pull LDAP users
  and match them with their mappings as a preview.

**Groups:** This section contains settings for mapping LDAP groups to Liferay
user groups.

- *Import Search Filter:* This filter finds the LDAP groups that
  you want to map to Liferay user groups. E.g.,

        (objectClass=groupOfNames)

    Enter the LDAP group attributes you want retrieved for this mapping. The
    following attributes can be mapped. The *Group Name* and *User* fields are
    required; the *Description* is optional.

    + *Group Name* (e.g., *cn* or *o*)

    + *Description* (e.g., *description*)

    + *User* (e.g., *member*)

- *Test LDAP Groups:* Click the *Test LDAP Groups* button to display a list of
  the groups returned by your search filter.

**Export:** This section contains settings for exporting user data from LDAP.

- *Users DN:* Enter the location in your LDAP tree where the users should be
  stored. When Liferay does an export, it exports the users to this
  location.

- *User Default Object Classes:* When a user is exported, the user is created
  with the listed default object classes. To find out what your default object
  classes are, use an LDAP browser tool such as JXplorer to locate a user and
  view the Object Class attributes stored in LDAP for that user.

- *Groups DN:* Enter the location in your LDAP tree where the groups should be
  stored. When Liferay does an export, it exports the groups to this
  location.

- *Group Default Object Classes:* When a group is exported, the group is created
  with the listed default object classes. To find out what your default object
  classes are, use an LDAP browser tool such as *Jxplorer* to locate a group and
  view the Object Class attributes stored in LDAP for that group.

Once you set all your options and test your connection, click *Save*. From here,
you can add another LDAP server or set just a few more options that apply to all
of your LDAP server connections.

### LDAP Options Available in System Settings [](id=ldap-options-available-in-system-settings)

Although most LDAP configuration can be done from Instance Settings, there are
several configuration parameters that are only available in System Settings. In
previous versions of Liferay, system scoped settings for LDAP were 
[set in the `portal.properties` file](https://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#LDAP)
and modified using a `portal-ext.properties` file. Those settings must now be made
via System Settings.

If you need to change any of these options, navigate to *Control Panel* &rarr;
*Configuration* &rarr; *System Settings*. Go to the Foundation section and
find the entries with LDAP in the title.

In the LDAP Auth entry, you can set the authentication method and the password
encryption algorithm. The Bind authentication method is preferred by
most vendors so you don't have to worry about encryption strategies. Password
compare does exactly what it sounds like: it reads the user's password out of
LDAP, decrypts it and compares it with the user's password in Liferay, syncing
the two. If you use password compare, you can also choose the encryption
algorithm to use for the comparison.

You can use the LDAP Import entry in System Settings to configure import
settings from LDAP. One example is the import methods. If you set this to User,
Liferay imports all users from the specified portion of the LDAP tree. If
you set this to Group, Liferay searches all the groups and imports the users
in each group. If you have users who do not belong to any groups, they are not
imported.

Error properties like *Error password age keywords* in the System LDAP
Configuration entry of System Settings let you set a list of phrases from error
messages which can possibly be returned by the LDAP server. When a user binds to
LDAP, the server returns *controls* with its response of success or failure.
These controls contain a message describing the error or the information that is
returned with the response. Though the controls are the same across LDAP
servers, the messages can be different. The properties described here contain
snippets of words from those messages and work with Red Hat's Fedora
Directory Server. If you are not using that server, the word snippets may not
work with your LDAP server. If they don't, you can replace the values of these
properties with phrases from your server's error messages. This enables
Liferay to recognize them. 

In summary, if there's a configuration you need to set up Liferay with LDAP, and
you don't find it in Instance Settings, look in the LDAP System Settings
entries.

<!-- Recommend separating the LDAP section of this article into its own article
so it's more prominent in the ToC hierarchy. -Rich -->

## Authentication: OpenID [](id=authentication-openid)

OpenID is a single sign-on standard implemented by multiple vendors. Users can
register for an ID with the vendor they trust. The credential issued by that
vendor can be used by all the web sites that support OpenID. Some high profile
OpenID vendors are Google, Paypal, Amazon, and Microsoft. Please see the [OpenID
site](http://www.openid.net) for a more complete list.

With OpenID, users don't have to register for a new account on every site in
which they want to participate. Users register on *one* site (the OpenID
provider's site) and then use those credentials to authenticate to many web
sites that support OpenID. Web site owners sometimes struggle to build
communities because users are reluctant to register for *another* account.
Supporting OpenID removes that barrier, making it easier for site owners to
build their communities. All the account information is kept with the OpenID
provider, making it much easier to manage this information and keep it up to
date.

Liferay can act as an OpenID consumer, allowing users to register and sign in
automatically with their OpenID accounts. Internally, the product uses
[OpenID4Java](http://code.google.com/p/openid4java/) to implement the feature.

OpenID is enabled by default in Liferay but can be disabled in Control Panel
&rarr; Configuration &rarr; Instance Settings.

## Authentication: Facebook [](id=authentication-facebook)

Users can log in to Liferay using their Facebook accounts. To enable this
feature, you must select the *Enable* box in the Facebook tab and enter the
Application ID and Application Secret which should have been provided to you by
Facebook. Facebook SSO works by taking the primary Facebook email address and
searching for the same email address in Liferay's `User_` table. If a match is
found, the user is signed in automatically (provided the user clicked *allow*
from the Facebook dialog). If there isn't a match, the user is prompted in
Liferay to add a user from Facebook. Once selected, a new user is created by
retrieving four fields from Facebook (first name, last name, email address and
gender).

## Atlassian Crowd [](id=atlassian-crowd)

Atlassian Crowd is a web-based Single Sign-On product similar to CAS. Crowd can
be used to manage authentication to many different web applications and
directory servers.

Because Atlassian Crowd implements an OpenID producer, Liferay works and has
been tested with it. Simply use the OpenID authentication feature in Liferay to
log in using Crowd.

There are a lot of ways to configure authentication for Liferay. For even more
information, see the [security documentation](/discover/deployment/-/knowledge_base/7-0/liferay-portal-security).

