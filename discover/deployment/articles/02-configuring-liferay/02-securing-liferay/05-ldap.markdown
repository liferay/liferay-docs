# LDAP [](id=ldap)

@product@ can connect to external LDAP servers. Users can be imported into
Liferay from LDAP or exported to LDAP from Liferay. If your organization
already stores user information on an LDAP server, it can be very convenient
for both users and administrators to simply have the LDAP user information
imported into Liferay. Importing LDAP user information to Liferay means that
users don't have to remember an extra set of credentials for Liferay. Importing
LDAP user information to Liferay also means that administrators don't have to
create a whole new set of user accounts for Liferay. In this article, you'll
learn how to connect @product@ to an LDAP server and how to configure import
settings, export settings, and related LDAP configuration settings. <!-- For a
technical overview of Liferay's LDAP authentication module, please see the (not
yet written) tutorial. -->

## Configuring Liferay's LDAP Settings [](id=configuring-liferays-ldap-settings)

To access @product@'s LDAP configuration settings, navigate to the Control Panel
and click on *Configuration* &rarr; *Instance Settings*. Then click on
*Authentication* on the right and then on the *LDAP* tab at the top. You can
use this page to connect Liferay to an LDAP directory. There are two places for
you to configure the LDAP settings: here in the Control Panel or in your
Liferay server's `portal-ext.properties` file. You can browse an HTML version
of Liferay's `portal.properties` file online at
[http://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html](http://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html).
We recommend that you use the Control Panel to configure LDAP server
configuration settings since settings configured through the Control Panel are
stored in Liferay's database. Note that if you use both, the settings in the
database will be merged with the settings in `portal-ext.properties`. If
there's a conflict or overlapping data, the LDAP settings in the database take
precedence over the settings specified in the `portal-ext.properties` file.
Configuring the LDAP settings from the Control Panel is easier and does not
require a restart of @product@. The only compelling reason to use the
`portal-ext.properties` file is if you have many Liferay nodes which need to be
configured to run against the same LDAP directory. In that case, for your
initial deployment, it may be easier to copy the `portal-ext.properties` file
to all of the nodes so the first time they start up, the settings are correct.
Regardless of which method you use, the available settings are the same.

You configure the global values from the LDAP tab of the Authentication page.

**Enabled:** Check this box to enable LDAP Authentication.

**Required:** Check this box if LDAP authentication is required. Liferay will
then not allow a user to log in unless he or she can successfully bind to the
LDAP directory first. Uncheck this box if you want to allow users with Liferay
accounts but no LDAP accounts to log in to the portal.

**LDAP Servers:** @product@ supports connections to multiple LDAP servers. You can
use the Add button beneath this heading to add LDAP servers. We explain how to
configure new LDAP servers below.

**Import/Export:** You can import and export user data from LDAP directories
using the following options:

- *Import Enabled:* Checking this box instructs @product@ to do a mass import
  from your LDAP directories. If you want Liferay to only synchronize users
  when they log in, leave this box unchecked. <!-- (Check if the following two
  sentences can be removed due to the fixes for LPS-58816 and LPP-19372.)
  Definitely leave this unchecked if you are working in a clustered
  environment. Otherwise, all of your nodes would try to do a mass import when
  each of them starts up. -->

- *Import on Startup Enabled:* Checking this box instructs @product@ to run the
  LDAP user import when it starts up. Note: This box only appears if you check
  the *Import Enabled* box above.

- *Export Enabled:* Check this box to enable @product@ to export user accounts
  from its database to LDAP. @product@ uses a listener to track any changes made
  to the `User` object. Liferay pushes updates out to the LDAP server whenever
  a `User` object is modified. Note that by default on every login, fields such
  as `lastLoginDate` are updated. When export is enabled, this has the effect
  of causing a user export every time the user logs in. You can prevent updates
  to users' `lastLoginDate` fields from triggering LDAP user exports by setting
  the following property in your `portal-ext.properties` file:

        users.update.last.login=false

**Use LDAP Password Policy:** Liferay uses its own password policy by default.
This can be configured on the Users &rarr; Password Policies page of the
Control Panel. Check the *Use LDAP Password Policy* box if you want to use the
password policies defined by your LDAP directory. Once this is enabled, the
Password Policies tab displays a message stating you are not using a local
password policy. You now have to use your LDAP directory's mechanism for
setting password policies. Liferay does this by parsing the messages in the
LDAP controls returned by your LDAP server. By default, the messages in the
LDAP controls that Liferay is looking for are the messages returned by the
Fedora Directory Server. If you are using a different LDAP server, you will
need to customize the messages in Liferay's `portal-ext.properties` file, as
there is not yet a GUI for configuring this. See below for instructions about
how to do this.

Once you've finished configuring LDAP, click the *Save* button. Next, let's look
at how to add LDAP servers.

### Adding LDAP Servers [](id=adding-ldap-servers)

Click on the *Add* button beneath the LDAP Servers heading to add an LDAP
server connection. If you have more than one LDAP server, you can arrange the
servers by order of preference using the up/down arrows. When you add an LDAP
Server, you need to provide several pieces of data so Liferay can bind to that
LDAP server and search it for user records. Regardless of how many LDAP servers
you add, each server has the same configuration options.

**Server Name:** Enter a name for your LDAP server.

**Default Values:** Several leading directory servers are listed here. If you
are using one of these, select it and click the *Reset Values* button. The rest
of the form will be populated with the proper default values for that
directory.

**Connection:** These settings cover the basic connection to LDAP.

- *Base Provider URL:* This tells the portal where the LDAP server is located.
  Make sure the machine on which Liferay is installed can communicate with the
  LDAP server. If there is a firewall between the two systems, check to make
  sure the appropriate ports are opened.

- *Base DN:* This is the Base Distinguished Name for your LDAP directory. It is
  usually modeled after your organization. For a commercial organization, it may
  look similar to this: `dc=companynamehere,dc=com`.

- *Principal:* By default, the LDAP administrator user ID is populated here. If
  you have removed the default LDAP administrator, you will need to use the
  fully qualified name of the administrative credential that you use instead.
  You need an administrative credential because Liferay uses this ID to
  synchronize user accounts to and from LDAP.

- *Credentials:* This is the password for the LDAP administrative user.

This is all you need to make a regular connection to an LDAP directory. The rest
of the configuration is optional. Generally, the default attribute mappings
provide enough data to synchronize back to the Liferay database when a user
attempts to log in. To test the connection to your LDAP server, click the *Test
LDAP Connection* button.

If you are running your LDAP directory in SSL mode to prevent credential
information from passing through the network unencrypted, you will have to
perform extra steps to share the encryption key and certificate between the two
systems.

For example, suppose that your LDAP directory is Microsoft Active Directory on
Windows Server 2003. In this case, you would take the following steps to share
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

Save the changes. Your @product@ will now use LDAP in secure mode for
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

![Figure 1: Testing LDAP Users](../../../images/server-configuration-testing-ldap-users.jpg)

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

![Figure 2: Mapping LDAP Groups](../../../images/server-configuration-mapping-ldap-groups.jpg)

Once you've set all your options and tested your connection, click *Save*. From
here, you can add another LDAP server or set just a few more options that apply
to all of your LDAP server connections.

### LDAP Options Not Available in the GUI [](id=ldap-options-not-available-in-the-gui)

Although most of the LDAP configuration can be done from the Control Panel,
there are several configuration parameters that are only available by adding to
your Liferay server's `portal-ext.properties` file. These options may be
available in the GUI in future versions of @product@ but for now they can
only be configured by editing the properties file.

If you need to change any of these options, copy the LDAP section from Liferay's
`portal.properties` file into your `portal-ext.properties` file. Note that if
you have already configured LDAP from the Control Panel GUI, any settings from
the properties file that match settings already configured in the GUI will be
ignored. The GUI, which stores the settings in the database, always takes
precedence over the properties file.

    ldap.auth.method=bind
    #ldap.auth.method=password-compare

For the property above, set either `bind` or `password-compare` for the LDAP
authentication method. Bind is preferred by most vendors so you don't have to
worry about encryption strategies. Password compare does exactly what it sounds
like: it reads the user's password out of LDAP, decrypts it and compares it
with the user's password in Liferay, syncing the two.

    ldap.auth.password.encryption.algorithm=
    ldap.auth.password.encryption.algorithm.types=MD5,SHA

For the property above, set the password encryption to be used to compare
passwords if the property `ldap.auth.method` is set to `password-compare`.

    ldap.import.method=[user,group]

If you set the property above to `user`, @product@ will import all users from the
specified portion of the LDAP tree. If you set this to `group`, Liferay will
search all the groups and import the users in each group. If you have users who
do not belong to any groups, they will not be imported.

    ldap.error.password.age=age
    ldap.error.password.expired=expired
    ldap.error.password.history=history
    ldap.error.password.not.changeable=not allowed to change
    ldap.error.password.syntax=syntax
    ldap.error.password.trivial=trivial
    ldap.error.user.lockout=retry limit

These properties above are a list of phrases from error messages which can
possibly be returned by the LDAP server. When a user binds to LDAP, the server
can return *controls* with its response of success or failure. These controls
contain a message describing the error or the information that is coming back
with the response. Though the controls are the same across LDAP servers, the
messages can be different. The properties described here contain snippets of
words from those messages and will work with Red Hat's Fedora Directory Server.
If you are not using that server, the word snippets may not work with your LDAP
server. If they don't, you can replace the values of these properties with
phrases from your server's error messages. This will enable @product@ to
recognize them.

Great! Now you know how to an LDAP server connection to @product@ and how to
configure user import behavior, export behavior, and other LDAP settings.

<!--
## Related Topics [](id=related-topics)

- Liferay Security Overview
- Logging into Liferay

For a technical overview of Liferay's LDAP authentication module, please
see the (not yet written) tutorial.

-->


