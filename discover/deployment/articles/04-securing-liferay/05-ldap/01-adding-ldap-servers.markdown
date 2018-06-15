# Adding LDAP Servers [](id=adding-ldap-servers)

Click on the *Add* button beneath the LDAP Servers heading to add an LDAP
server connection. If you have more than one LDAP server, you can arrange the
servers by order of preference using the up/down arrows. When you add an LDAP
Server, you must provide several pieces of data so @product@ can bind to that
LDAP server and search it for user records. Regardless of how many LDAP servers
you add, each server has the same configuration options.

**Server Name:** Enter a name for your LDAP server.

**Default Values:** Several leading directory servers are listed here. If you
are using one of these, select it. The rest of the form is populated with the
proper default values for that directory.

**Connection:** These settings cover the basic connection to LDAP.

- *Base Provider URL:* The link to the LDAP server. Make sure the @product@ server
  can communicate with the LDAP server. If there is a firewall between the two
  systems, check to make sure the appropriate ports are opened.

- *Base DN:* The Base Distinguished Name for your LDAP directory. It is
  usually modeled after your organization. For a commercial organization, it may
  look similar to this: `dc=companynamehere,dc=com`.

- *Principal:* By default, the LDAP administrator user ID is populated here. If
  you have removed the default LDAP administrator, you must use the fully
  qualified name of the administrative credential that you use instead. You need
  an administrative credential because @product@ uses this ID to synchronize
  user accounts to and from LDAP.

- *Credentials:* This is the password for the LDAP administrative user.

This is all you need to make a regular connection to an LDAP directory. The rest
of the configuration, however, may need to be customized, as it represents "best
guesses" as to correct defaults. The default attribute mappings usually provide
enough data to synchronize back to the @product@ database when a user attempts
to log in. To test the connection to your LDAP server, click the *Test LDAP
Connection* button.

## Checkpoint [](id=checkpoint)

Before proceeding to fine tune @product@'s LDAP connections, ensure the
following steps have been taken:

1.  The LDAP connection has been enabled in the _Control Panel_. Depending on
    your needs, LDAP authentication may be required so that only users who have been
    bound may log in.

2.  *Export/Import*: for users in a clustered environment, Enable Import/Export
    on Startup should be disabled so that there are no massive imports on every
    node upon start up. 

3.  When adding the LDAP server, the *Server Name*, *Default Values*,
    *Connection* values are correct. It is always a good idea to click the *Test
    LDAP Connection* before saving.

## Security [](id=security)

If you run your LDAP directory in SSL mode to prevent credential information
from passing through the network unencrypted, you must perform extra steps to
share the encryption key and certificate between the two systems.

For example, if your LDAP directory is Microsoft Active Directory on
Windows Server 2003, you'd share the certificate like this:

Click *Start* &rarr; *Administrative Tools* &rarr; *Certificate Authority*.
Highlight the machine that is the certificate authority, right-click on it, and
click *Properties*. From the General menu, click *View Certificate*. Select the
Details view, and click *Copy To File*. Use the resulting wizard to save the
certificate as a file. You must also import the certificate into the *cacerts
keystore* like this: 

    keytool -import -trustcacerts -keystore /some/path/java-8-jdk/jre/lib/security/cacerts -storepass changeit -noprompt -alias MyRootCA -file /some/path/MyRootCA.cer

The *keytool* utility ships as part of the Java SDK.

Once this is done, go back to the LDAP page in the Control Panel. Modify the
LDAP URL in the Base DN field to the secure version by changing the protocol to
`ldaps` and the port to `636` like this:

    ldaps://myLdapServerHostname:636

Save the changes. Communication to LDAP is now encrypted. 

## Configuring LDAP Import/Export [](id=configuring-ldap-import-export)

The other settings configure mappings between LDAP and @product@ so users and
groups can be imported. 

### Users [](id=users)

This section contains settings for finding users in your LDAP directory.

**Authentication Search Filter:** The search filter box can be used to determine
the search criteria for user logins. By default, @product@ uses users' email
addresses for their login names. If you have changed this setting, you must
modify the search filter here, which defaults to use the email address attribute
from LDAP as a search criterion. For example, if you changed @product@'s
authentication method to use screen names instead of the email addresses, you
would modify the search filter so it can match the entered log in name:

        (cn=@screen_name@)

**Import Search Filter:** Depending on the **LDAP** server, there are different
ways to identify the user. The default setting is usually fine:

        (objectClass=inetOrgPerson)

If you want to search for only a subset of users or users that have
different LDAP object classes, you can change this.

**User Mapping:** Next, you can define mappings from LDAP attributes to Liferay
fields. Though LDAP user attributes may be different from LDAP server to LDAP
server, there are five fields @product@ requires to be mapped for the user
to be recognized:

   +   *Screen Name* (e.g., *uid*)

   +   *Password* (e.g., *userPassword*)

   +   *Email Address* (e.g., *mail* or *email*)

   +   *First Name* (e.g., *name* or *givenName*)

   +   *Last Name* (e.g., *sn*)

**Note:** If you intend to create or import users with no email addresses, then
you must set `users.email.address.required=false` in your
`portal-ext.properties`. With this set, Liferay auto-generates an email address
combining the user ID plus the suffix defined in the property
`users.email.address.auto.suffix=`. Finally, make sure to set Liferay and LDAP
authentication to something other than email address.

If you want to import LDAP groups as @product@ user groups, make sure define
a mapping for the @product@ group field so that membership information is
preserved:

  +   *Group* (e.g., *member*)

The other LDAP user mapping fields are optional.

The Control Panel provides default mappings for commonly used LDAP attributes.
You can also add your own mappings.

**Test LDAP Users:** Once you have your attribute mappings set up (see above),
click the *Test LDAP Users* button and @product@ attempts to pull LDAP users and
match them with their mappings as a preview.

![Figure 1: Testing LDAP Users](../../../images/server-configuration-testing-ldap-users.png)

### Groups [](id=groups)

This section contains settings for mapping LDAP groups to @product@ user groups.

**Import Search Filter:** This is the filter for finding the LDAP groups that
you want to map to @product@ user groups. For example, 

        (objectClass=groupOfNames)

Enter the LDAP group attributes you want retrieved for this mapping. The
following attributes can be mapped. The *Group Name* and *User* fields are
required, the *Description* is optional.

  + *Group Name* (e.g., *cn* or *o*)

  + *Description* (e.g., *description*)

  + *User* (e.g., *member*)

**Test LDAP Groups:** Click the *Test LDAP Groups* button to display a list of
the groups returned by your search filter.

### Export [](id=export)

This section contains settings for exporting user data from LDAP.

**Users DN:** Enter the location in your LDAP tree where the users are stored.
When @product@ does an export, it exports the users to this location.

**User Default Object Classes:** When a user is exported, the user is created
with the listed default object classes. To find out what your default object
classes are, use an LDAP browser tool such as Apache Directory Studio to locate
a user and view the Object Class attributes stored in LDAP for that user.

**Groups DN:** Enter the location in your LDAP tree where the groups are stored.
When @product@ does an export, it exports the groups to this location.

**Group Default Object Classes:** When a group is exported, the group is created
with the listed default object classes. To find out what your default object
classes are, use an LDAP browser tool such as Apache Directory Studio to locate
a group and view the Object Class attributes stored in LDAP for that group.

Once you set all your options and tested your connection, click *Save*. 

+$$$

**Note:** If a user changes a value like a password in @product@, that change is
passed to the LDAP server, provided @product@ has enough schema access to make
the change. 

$$$

Now you know how to connect an LDAP server to @product@ and how to
configure user import behavior, export behavior, and other LDAP settings.

## Related Topics [](id=related-topics)

[@product@ Security Overview](/discover/deployment/-/knowledge_base/7-0/liferay-portal-security-overview)
[Logging into @product@](/discover/deployment/-/knowledge_base/7-0/logging-in-to-liferay)

