# Managing Portal Settings [](id=managing-portal-settings)

After you have created users, user groups, organizations, roles, sites and teams
your portal will be ready to host content and applications. You can configure
Liferay's portal settings to fit your environment and your particular portal
project. Many configurations can be performed through Liferay's portlet-driven
user interface. This section covers how to configure portal settings such as
password policies, authentication settings, mail host names, email
notifications, display settings and monitoring.

Now that you have been navigating in the control panel, you should be pretty
familiar with how it works. All the options appear in the left navigation, their
interfaces appear in the middle and any sub-options appear on the right. We have
focused so far on the maintenance of users and portal security. The remaining
links in the *Portal* category focus on various portal settings which cover how
the portal operates and integrates with other systems you may have. Let's begin
our discussion of Liferay's portal settings by examining how to configure
password policies.

## Password Policies [](id=password-policies)

Password policies can enhance the security of your portal. You can set
requirements on password strength, frequency of password expiration and more.
Additionally, you can apply different password policies to different sets of
portal users.

If you are viewing a page other than the control panel, select *Control Panel*
from the *Go to* menu of the Dockbar. Next, click on the *Password Policies*
link on the left side of the screen under the *Portal* heading. You will see
there is already a default password policy in the system. You can edit this in
the same manner as you edit other resources in the portal: click *Actions* and
then click *Edit*.

The Password Policy settings form contains the following fields. Enabling
specific settings via the check boxes prompts setting-specific options to
appear.

**Name:** requires you to enter a name for the password policy.

**Description:** lets you describe the password policy so other administrators
will know what it's for.

**Changeable:** determines whether or not a user can change his or her password.

**Change Required:** determines whether or not a user must change his or her
password after logging into the portal for the first time.

**Minimum Age:** lets you choose how long a password must remain in effect
before it can be changed.

**Reset Ticket Max Age:** determines how long a password reset link remains
valid.

**Password Syntax Checking:** allows you to set a minimum password length and to
choose whether or not dictionary words can be in passwords. You can also specify
detailed requirements such as minimum numbers of alpha numeric characters, lower
case letters, upper case letters, numbers or symbols.

**Password History:** lets you keep a history (with a defined length) of
passwords and prevents users from changing their passwords to one that was
previously used.

**Password Expiration:** lets you choose how long passwords can remain active
before they expire. You can select the age, the warning time and a grace limit.

**Lockout:** allows you to set a number of failed log-in attempts that triggers
a user's account to lock. You can choose whether an administrator needs to
unlock the account or if it becomes unlocked after a specific duration.

From the list of password policies, you can perform several other actions.

**Edit:** brings you to the form above and allows you to modify the password
policy.

**Permissions:** allows you to define which users, user groups or roles have
permission to edit the password policy.

**Assign Members:** takes you to a screen where you can search and select users
in the portal to be assigned to this password policy. The password policy will
be enforced for any users who are added here.

**Delete:** shows up for any password policies you add beyond the default
policy. You cannot delete the default policy.

Next, let's examine Liferay's Portal Settings.

## Portal Settings [](id=portal-settings)

Most global portal settings can be configured from the Portal Settings section
of the control panel. The Configuration heading contains the following links:

**General:** lets you configure global settings, such as the company name,
domain, the virtual host, a global portal logo and more.

**Authentication:** allows you to configure log in IDs, connection to LDAP and
Single Sign-On.

**Users:** has three tabs, labeled Fields, Reserved Credentials and Default User
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

![Figure 15.14: Portal Settings Pages](../../images/server-configuration-portal-settings-pages.png)

Let's discuss these settings next.

## General [](id=general)

The General link takes you to a page with three headings: Main Configuration,
Navigation and Additional Information. Under the Main Configuration heading, you
can set the name of the company, organization or site which is running the
portal. This name also defines the name of your portal's default site. Its
default name is `liferay.com` so you will definitely want to set this to reflect
your own company. You can also set the mail domain, virtual host and content
delivery network address here. Under the Navigation heading, you can set a home
page for your portal here as well as default landing and logout pages. Under the
Additional Information heading, you can specify a Legal name, ID, company type,
SIC code, ticker symbol, industry and industry type.

## Authentication [](id=authentication)

The Authentication page has several tabs: General, LDAP, CAS, Facebook, NTLM,
OpenID, Open SSO and SiteMinder. You can use any of these authentication methods
to configure how users will authenticate to Liferay. Since Liferay supports
quite a few authentication methods, there are different settings for each.

The settings on the General tab of the Authentication page affect only Liferay
functionality and don't have anything to do with the integration options on the
other tabs. The General tab allows you to customize Liferay's standard
authentication behavior. Specifically, the General tab allows you to select from
several global authentication settings:

-   Authenticate via email address (default), screen name or user ID (a
    numerical ID auto-generated in the database not recommended).

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
page of Portal Settings). This is handy if you are using Liferay to host both
internal and external web sites. You can make sure all internal IDs have to be
created by administrators but external users can register for IDs themselves.

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

We'll examine how to set up LDAP authentication next.
