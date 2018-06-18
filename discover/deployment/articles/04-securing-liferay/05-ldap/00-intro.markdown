# LDAP [](id=ldap)

@product@ fully supports LDAP as a user store. Use the LDAP tab in Instance
Settings's Authentication page to connect to an LDAP directory. Users can be
imported from LDAP or exported to LDAP. If your organization already stores user
information on an LDAP server, it's convenient for both users and administrators
to import Users. In this article, you'll learn how to connect to an LDAP server
and how to configure import settings, export settings, and related LDAP
configuration settings. 

## Configuring LDAP Settings [](id=configuring-ldap-settings)

To access LDAP configuration settings, navigate to *Control Panel &rarr;
Configuration* &rarr; *Instance Settings*, then scroll down and expand the
form's *Authentication* section.  Go to the *LDAP* tab. Use this form to connect
to an LDAP directory.

You configure the global values from the LDAP tab of the Authentication page.

**Enabled:** Check this box to enable LDAP Authentication.

**Required:** Check this box if LDAP authentication is required. Users then
can't log in unless they can bind to the LDAP directory successfully. Uncheck
this box if users with @product@ accounts but no LDAP accounts can log in.

**Method:** Choose *Bind* (the default) or *Password Compare*. Bind does
a standard LDAP bind; Password Compare attempts to compare Liferay and LDAP
passwords using the encryption algorithm specified in the field below. Password
Compare is rarely used. 

**Password Encryption Algorithm:** Choose the password encryption algorithm your
LDAP server uses to encrypt passwords, so they can be compared, if using the
Password Compare bind method. This is rarely used. 

**LDAP Servers:** @product@ supports connections to multiple LDAP servers. Use
the *Add* button beneath this heading to add LDAP servers. Each LDAP server has
several configuration options that are explained in the next article. 

**Import/Export:** You can import and export user data from LDAP directories
using the following options:

**Enable Import:** Enable User import from LDAP directories. Users are imported
as they log in, rather than all at once. 

**Enable Import on Startup:** Checking this box causes a mass import from your
LDAP directories. Leave this unchecked to keep the default behavior, which
synchronizes Users only when they log in. Definitely leave this unchecked if you
are working in a clustered environment. Otherwise, all your nodes would try to
do a mass import when each of them starts up.

**Import Interval:** When mass importing users, enter the number of users batch
imported at a time. 

**Import Method:** Set either User or Group. If you set this to User, @product@
imports all users from the specified portion of the LDAP tree. If you set this
to Group, @product@ searches all the groups and imports the users in each group.
If you have users who do not belong to any groups, they are not imported. 

**Lock Expiration Time:** Set the account lock expiration time for LDAP User
import. The default is one day. 

**Import User Sync Strategy:** Set the strategy used to sync user accounts.
Options are Auth Type (i.e., the way the user authenticates, like with screen
name) and UUID (requires a UUID attribute in LDAP). 

**Enable User Password on Import:** Assign a default password (see below) when
users are imported, so they can be synced between the two systems. 

**Autogenerate User Password on Import:** Create a random password on user
import. 

**Default User Password:** Enter the default password users are assigned when
they first log in via LDAP. 

**Enable Group Cache on Import:** Cache the imported groups so import isn't
slowed by database access. 

**Create Role per Group on Import:** For every LDAP group, create
a corresponding Liferay Role. 

**Enable Export:** Check this box to export user accounts to LDAP. A listener
tracks changes made to the `User` object and pushes updates to the LDAP server
whenever a `User` object is modified. Note that by default on every login,
fields such as `lastLoginDate` are updated. When export is enabled, this causes
a user export every time the user logs in. You can prevent updates to users'
`lastLoginDate` fields from triggering LDAP user exports by setting the
following property in your `portal-ext.properties` file:

        users.update.last.login=false

**Enable Group Export:** Export groups to LDAP. 

**Use LDAP Password Policy:** @product@ uses its own password policy by default.
This can be configured on the Control Panel's Password Policies page. Check the
*Use LDAP Password Policy* box if you want to use the password policies defined
by your LDAP directory. Once this is enabled, the Password Policies tab states
that you are not using a local password policy. You must now use your LDAP
directory's mechanism for setting password policies. @product@ cannot enforce
these policies; the best it can do is pass through the messages returned by your
LDAP server. It does this by parsing the messages in the LDAP controls the
server returns. By default, @product@ is configured to parse the messages
returned by the Fedora Directory Server. If you use a different LDAP server, you
must customize the messages in *System Settings* &rarr; *Security* &rarr; *LDAP*
&rarr; *Connection*. 

Once you've finished configuring LDAP, click the *Save* button. 

### LDAP Options Available in System Settings [](id=ldap-options-available-in-system-settings)

Although most LDAP configuration can be done from Instance Settings, there are
several configuration parameters that are only available in System Settings. 

If you must change any of these options, navigate to *Control Panel* &rarr;
*Configuration* &rarr; *System Settings*. Go to the *Foundation* section and
find the entries with LDAP in the title.


Use the *Connection* entry to manage error properties
like *Error password age keywords* which lets you set a list of phrases from
error messages which can possibly be returned by the LDAP server. When a user
binds to LDAP, the server returns *controls* with its response of success or
failure. These controls contain a message describing the error or the
information that is returned with the response. Though the controls are the same
across LDAP servers, the messages can be different. The properties described
here contain snippets of words from those messages and work with Red Hat's
Fedora Directory Server. If you are not using that server, the word snippets may
not work with your LDAP server. If they don't, you can replace the values of
these properties with phrases from your server's error messages. This enables
@product@ to recognize them.

+$$$

**Note**: When you make a change in System Settings, it takes effect for the
virtual instance you're in. If after changing a setting you create a new
virtual instance, that virtual instance inherits the settings of the one it was
created from as defaults. For example, say you have virtual instances named A,
B, and C. From A, you modify *Error password history keywords*. This change
appears only in A, not in B or C. Then from A, you create virtual instance D.
The change to *Error password history keywords* appears in D (not B or C),
since D defaults to A's settings because you created it from A. 

$$$

In summary, if there's a configuration option you need with LDAP, and you don't
find it in Instance Settings, look in the LDAP System Settings entries.

Next, you'll learn how to add LDAP server connections. 
