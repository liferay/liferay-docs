# Instance Settings [](id=instance-settings)

Virtual instance settings are configured in the Instance Settings section of the
Control Panel. 

Instance Settings is divided into *Configuration*, *Identification*, *Social*,
and *Miscellaneous*. This article covers the first and largest section,
Configuration. The remaining sections are covered in the next article.

![Figure 1: The configuration options in Instance Settings are divided into four categories.](../../../images/instance-settings-navigation.png)

The Configuration section of In stance Settings is divided into seven
sub-sections. The first is General.

## General [](id=general)

The General section has three headings: Main Configuration, Navigation, and
Additional Information. Under Main Configuration, set the name of the entity
responsible for running the virtual instance. Additionally, define the name of
your virtual instance's default site, set the mail domain, virtual host and
content delivery network address.

Under the Navigation heading, set a home page for your virtual instance as well
as default landing and logout pages. To set these pages, use the part
of the page's address that follows your domain. For example, to set the
default landing page to be 

`http://localhost:8080/web/guest/login`

use `/web/guest/login`. Use the variable `${liferay:screenName}` as part of the
address. This comes in handy if you want to redirect Users to their personal
pages upon login. For example, the User `marvin` has this default llURL to his
personal page: 

    http://localhost:8080/User/marvin

To make sure he's directed there on login, place this in the Default Landing
Page field:

    /User/${liferay:screenName}

Alternatively, set these URLs in a `portal-ext.properties` file:

    default.landing.page.path=
    default.logout.page.path=
    company.default.home.url=

For more information, see the [Portal Properties documentation](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html)

Under the Additional Information heading, you can specify a Legal name, ID, 
company type, SIC code, ticker symbol, industry and industry type.

## Authentication [](id=authentication)

Authentication has several tabs: General, OpenSSO, CAS, NTLM, LDAP, OpenID,
Facebook, Google, and OpenId Connect. Use any of these to configure how Users
authenticate.

![Figure 3: After clicking on *Instance Settings* in Liferay's Control Panel, you
can configure any of the areas shown in this figure.](../../../images/instance-settings-auth-general.png)

The General tab's settings affect only @product@'s standard authentication
functionality and don't have anything to do with the integration options on the
other tabs. There are several general authentication settings:

-   Authenticate by email address (default), screen name, or User ID (a
    numerical ID auto-generated in the database--not recommended).

-   Enable/Disable automatic log in. If enabled, a User can check
    a box which will cause the site to "remember" the login information by
    placing a cookie on the browser. If disabled, Users must always log in
    manually.

-   Enable/Disable forgotten password functionality.

-   Enable/Disable request password reset links.

-   Enable/Disable account creation by strangers. If running an Internet
    site, leave this enabled so visitors can create accounts on your site.

-   Enable/Disable account creation by those using an email address in the
    domain of the company running the site (which is set on the General page of
    Instance Settings). This is handy if you're using Liferay to host both
    internal and external web sites. Make sure all internal IDs are created by
    administrators but external Users can register for IDs themselves.

-   Enable/Disable email address verification. If enabled,
    Users receive a verification email with a link back to the virtual instance,
    verifying that the email address they entered is valid.

By default, all settings except for the last are enabled. User authentication by
email address is an important default for the following reasons:

1.  An email address is unique to the User who owns it.

2.  People remember their email addresses. A Users who hasn't logged in for a
    while could forget their screen name.

3.  If email address isn't used to authenticate, a User might change her email
    address then forget to update the email address in her profile. If this
    occurs, no notifications sent by the virtual instance will reach the User.
    Keeping the email address at the forefront of a User's mind when she logs in
    helps ensure the User keeps it current. 

To use a third party authentication service or set up Single Sign On (SSO), see
the documentation on 
[@product@ Security](/discover/deployment/-/knowledge_base/7-1/liferay-portal-security) 
to learn about the supported services and how to configure them.

## Users [](id=Users)

The *Users* section has three tabs: Fields, Reserved Credentials and Default
User Associations.

The Fields tab allows you to enable/disable these fields on the Add/Edit User
Form:

- Autogeneration of screen names

- Birthday field

- Gender field

In the Reserved Credentials tab, specify the screen names and email addresses
that you don't want Users to claim. Users are prevented from registering with
these screen names and email addresses. You might use this feature to prevent
Users from creating IDs that look like administrative IDs or that have reserved
words in their names.

The Default User Associations tab has four fields allowing you to list (one per
line) Sites, Organization Sites, Soles, and User Groups you want new Users to
become members of automatically. By default, new Users are assigned to only the
Users role. You can also choose whether to apply these configurations to
existing Users.

User groups are handy tools for pre-populating your Users' private sites with
a certain page set, defined by a site template. If you've done that and want all
Users you add to immediately be added to the User group and inherit those pages,
add the User group here.

## Terms of Use [](id=terms-of-use)

Terms of Use are important when you need them, but not all sites do. This is
where you disable the requirement for all Users to read the terms of use, and
where you set the Group ID and Article ID for the Web Content Article you're
using for your Terms of Use.

To find a Web Content Article's ID:

1.  Go to the Site Menu &rarr; Content &rarr;
Web Content. 

2.  Click on the Terms of Use article. Its ID is displayed at the top of the
    screen, with the Version and Workflow Status.

![Figure x: ](../../images/instance-settings-wc-id.png)

The Group ID is the ID of the site the Web Content is associated with. To find
the Group/Site ID:

1.  Go to the Site Menu &rarr; Configuration &rarr; Site Settings.

2.  Find the Site ID field in the General tab. Enter it into the Group ID
    field.

![Figure x: ](../../images/instance-settings-group-id.png)

Save the configuration and now your Terms of Use article is seen by all Users
signing in for the first time. Users must agree to the Terms of Use to register
their User Account.

![Figure x: ](../../images/instance-settings-terms-of-use.png)

## Mail Host Names [](id=mail-host-names)

Enter one mail host name per line, besides the one you configured on the General
tab. This tells the virtual instance which mail host names are owned by your
organization.

## Email Notifications [](id=email-notifications)

The Sender tab allows you to set the virtual instance's administrative name and
email address.  By default, these are `Test Test` and `test@liferay.com`. This
name and email address appear in the *From* field in all email messages sent by
the virtual instance.

![Figure 4: Customize the email template for the email messages sent to new Users.](../../../images/instance-settings-account-created-notification.png)

The remaining tabs (Account Created Notification, Email Verification
Notification, Password Changed Notification and Password Reset Notification) are
used to customize the email messages sent to Users each time any of those four
events occur.

![Figure 5: There are some handy variables available for use in email templates.](../../../images/instance-settings-definition-of-terms.png)

A list of tokens, entitled "Definition of Terms," is provided for insertion of
certain values (such as the portal URL or the User ID) into custom email
messages.

## Content Sharing [](id=content-sharing)

Choose whether or or not site administrators can display content in sites from
other sites they administer. For example, suppose that a certain User is a site
administrator of two sites: *Engineering* and *Marketing*. The checkbox in the
Content Sharing section of Instance Settings determines whether or not the site
administrator can display content from the Marketing site in the Engineering
site and vice versa.

The Content Sharing section of Instance Settings also allows you to choose a
behavior for whether or not sub-sites can display content from parent sites and
for configuring the defaults. There are three options:

**Enabled by Default**: This means that subsites can display content from parent
sites by default but this can be disabled by a site administrator.

**Disabled by Default**: This means that subsites cannot display content from
parent sites by default but this can be enabled by a site administrator.

**Disabled**: This means that subsites cannot display content from parent sites
and this behavior cannot be changed by a site administrator.

That covers a lot of Instance Settings, but you're not finished yet. The next
article contains even more virtual instance configuration settings.
