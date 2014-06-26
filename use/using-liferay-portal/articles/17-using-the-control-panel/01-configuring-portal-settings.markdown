# Configuring Portal Settings [](id=configuring-portal-settings-lp-6-2-use-useportal)

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

## General

The General link takes you to a screen with three headings: Main Configuration,
Navigation, and Additional Information. Under the Main Configuration heading,
you can set the name of the company or organization that's responsible for
running the portal. This name also defines the name of your portal's default
site. Its default name is `liferay.com` so you will definitely want to change
this to reflect the name of your company or organization. You can also set the
mail domain, virtual host and content delivery network address here. Under the
Navigation heading, you can set a home page for your portal here as well as
default landing and logout pages. Under the Additional Information heading, you
can specify a Legal name, ID, company type, SIC code, ticker symbol, industry
and industry type.

## Authentication

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
