# Instance Settings [](id=instance-settings)

Most global instance settings can be configured from the Instance Settings section
of the Control Panel. 

Instance Settings is divided into *Configuration*, *Identification*, *Social*,
and *Miscellaneous*.

![Figure 1: The configuration options in Instance Settings are divided into four categories.](../../../images/instance-settings-navigation.png)

The Configuration section is much larger than the others, so it's presented last.

## Identification [](id=identification)

The Identification section has several links for addresses, phone numbers and
other information you can configure in your instance. This allows you to set up
contact information for the organization that owns the instance. Developers can
query for this information in their applications.

## Social [](id=social)

The Social section lets you configure Ratings <!--LINK--> and Mentions
<!--LINK--> throughout the instance. For each of Liferay's core portlets that
has ratings enabled, you can choose whether to use a simple *Like*, *Thumbs*,
or *Stars* for rating the content. You can also choose whether to allow users to
mention (*@username*) other users, and if so, whether all users have the
capability or whether you want to further refine the mentions configuration.

![Figure 2: You can configure the Mentions capability throughout a Liferay instance.](../../../images/instance-settings-mentions.png)

## Miscellaneous: Display Settings [](id=miscellaneous-display-settings)

This section allows you to set the default instance language and the time zone.
You can also set up an instance-wide logo which appears in the top left corners of
site pages. 

Liferay's default theme is configured to display Liferay's logo. For custom
themes, you can choose whether or not to display the logo. Be careful to choose
an image file that fits the space. If you pick something too big, it might
overlap with the navigation. 

<!-- Should we provide a range of recommended parameters for custom logos?  -->
## Configuration [](id=configuration)

Since there's a lot under this heading, here's a brief list, with a
longer description of each following:

<!-- Make these much shorter or just provide the list of headings or leave this
out? -->

**General:** lets you configure global settings, such as the company name,
domain, the virtual host, a global instance logo, and more.

**Authentication:** allows you to configure user authentication methods and
connections to LDAP and Single Sign-On servers.

**Users:** has three tabs labeled Fields, Reserved Credentials and Default User
Associations. The Fields tab enables or disables some user fields, such as
birthday or terms of use. The Reserved Credentials tab lets you reserve screen
names and email addresses so users cannot register using them. You might use
this to prevent users from registering on the instance with user names that
contain profanity or that sound official, such as *admin* or *president*. The
Default User Associations tab lets you configure default membership to roles,
user groups, sites for new users and provides a check box which allows you to
retroactively apply these to existing users.

**Terms of Use:** decide whether to require Terms of Use, and point to a Web
Content Article if you want to create your own. 

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

Let's discuss these settings in more detail.

### General [](id=general)

The General link takes you to a screen with three headings: Main Configuration,
Navigation, and Additional Information. Under the Main Configuration heading,
you can set the name of the company or organization that's responsible for
running the instance. This name also defines the name of your instance's default
site. Its default name is `liferay.com` so you will definitely want to change
this to reflect the name of your company or organization. You can also set the
mail domain, virtual host and content delivery network address here.

Under the Navigation heading, you can set a home page for your instance here as 
well as default landing and logout pages. For setting these pages, just use the 
part of the page's address that follows your domain. For example, if you want 
the default landing page to be `http://localhost:8080/web/guest/login`, use 
`/web/guest/login`. You can also use the variables `${liferay:screenName}` and 
`${liferay:userId}` as part of the address. This comes in handy if you want to 
redirect users to their personal pages upon login. Alternatively, you can set 
the default login or logout page in a `portal-ext.properties` file with the 
properties `default.landing.page.path` and `default.logout.page.path`, 
respectively. For more information, see the `portal.properties` documentation 
entries for the [Default Landing Page](http://docs.liferay.com/portal/7.0-b1/propertiesdoc/portal.properties.html#Default%20Landing%20Page) 
and [Default Logout Page](http://docs.liferay.com/portal/7.0-b1/propertiesdoc/portal.properties.html#Default%20Logout%20Page). 

Under the Additional Information heading, you can specify a Legal name, ID, 
company type, SIC code, ticker symbol, industry and industry type.

<!-- Information like the above is not really useful. Remove? -->

### Authentication [](id=authentication)

The Authentication section has several tabs: General, OpenSSO, LDAP, OpenID,
CAS, Google, NTLM, and Facebook. You can use any of these authentication methods
to configure how users will authenticate to Liferay. Since Liferay supports
quite a few authentication methods, there are different settings for each.

![Figure 3: After clicking on *Instance Settings* in Liferay's Control Panel, you
can configure any of the areas shown in this figure.](../../../images/instance-settings-auth-general.png)

The settings on the General tab of the Authentication page affect only Liferay
functionality and don't have anything to do with the integration options on the
other tabs. The General tab allows you to customize Liferay's standard
authentication behavior. Specifically, the General tab allows you to select from
several global authentication settings:

-   Authenticate by email address (default), screen name, or user ID (a
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
    page of Instance Settings). This is handy if you are using Liferay to host
    both internal and external web sites. You can make sure all internal IDs
    have to be created by administrators but external users can register for IDs
    themselves.

-   Enable / Disable email address verification. If you enable this, Liferay
    will send users a verification email with a link back to the instance to
    verify the email address they entered is a valid one they can access.

By default, all settings except for the last are enabled. User authentication by
email address is an important default for the following reasons:

1.  An email address is, by definition, unique to the user who owns it.

2.  People can generally remember their email addresses. If you have users who
    haven't logged into the instance for a while, it is possible they will forget
    their screen names, especially if they weren't allowed to use their screen
    names of choice (because they were already taken).

3.  If a user changes his or her email address, it is more likely the user will
    forget to update his or her email address in his or her profile, if the
    email address is not used to authenticate. If the user's email address is
    not updated, all notifications sent by the instance will fail to reach the
    user. So it is important to keep the email address at the forefront of a
    user's mind when he or she logs in to help the user keep it up to date. 

If you need to use a third party authentication service or set up Single Sign On
(SSO), see the documentation on [@product@
Security](/discover/portal/-/knowledge_base/7-0/liferay-portal-security) to
learn about the supported services and how to configure them.

### Users [](id=users)

If you click on *Users* from the Instance Settings screen, you'll find three tabs:
Fields, Reserved Credentials and Default User Associations.

The Fields tab allows you to enable/disable these fields on the Add/Edit User
Form:

- Autogeneration of screen names

- Birthday field

- Gender field

In the Reserved Credentials tab, enter any screen names and email addresses that
you don't want users to claim. Liferay prevents users from registering with
these screen names and email addresses. You might use this feature to prevent
users from creating IDs that look like administrative IDs or that have reserved
words in their names.

The Default User Associations tab has three fields allowing you to list (one per
line) sites, organization sites, roles, and user groups you want new users to
become members of automatically. By default, Liferay assigns new users to both
the Users role and the Power Users role. You can also choose whether to apply
these configurations to existing users.

User groups are handy tools for pre-populating your users' private sites with
a certain page set, defined by a site template. If you've done that and want all
users you add to immediately be added to the user group and inherit those pages,
add the user group here.

### Terms of Use [](id=terms-of-use)

Do you want to disable the requirement for all users to read the terms of use?
You can do that, or even specify the Group ID and Article ID for a Web Content
Article that you'd like to use as your own custom Terms of Use.

### Mail Host Names [](id=mail-host-names)

You can enter other mail host names (one per line) besides the one you
configured on the General tab. This lets the instance know which mail host names
are owned by your organization.

### Email Notifications [](id=email-notifications)

There are five tabs under the Email Notifications page of Instance Settings. The
Sender tab allows you to set the instance's administrative name and email address.
By default, these are `Joe Bloggs` and `test@liferay.com`. You can change them
to whatever you want. This name and email address will appear in the *From* field
in all email messages sent by the Liferay instance.

![Figure 4: You can customize the email template for the email messages sent to users who have just created their accounts.](../../../images/instance-settings-account-created-notification.png)

The other four tabs are Account Created Notification, Email Verification
Notification, Password Changed Notification and Password Reset Notification.
These tabs allow you to customize the email messages that are sent to users each
time any of those four events occur.

![Figure 5: There are some handy variables available for use in email templates.](../../../images/instance-settings-definition-of-terms.png)

A list of tokens, entitled "Definition of Terms," is provided so you can insert
certain values (such as the portal URL or the user ID) when you are setting up
the custom email messages.

### Content Sharing [](id=content-sharing)

The Content Sharing section of the Instance Settings area of the Control Panel
allows you to choose whether or or not site administrators can display content
in sites from other sites they administer. For example, suppose that a certain
user is a site administrator of two sites: *Engineering* and *Marketing*. The
checkbox in the Content Sharing section of Instance Settings determines whether or
not the site administrator can display content from the Marketing site in the
Engineering site and vice versa.

The Content Sharing section of Instance Settings also allows you to choose a
behavior for whether or not subsites can display content from parent sites and
for configuring the defaults. There are three options:

**Enabled by Default**: This means that subsites can display content from parent
sites by default but this can be disabled by a site administrator.

**Disabled by Default**: This means that subsites cannot display content from
parent sites by default but this can be enabled by a site administrator.

**Disabled**: This means that subsites cannot display content from parent sites
and this behavior cannot be changed by a site administrator.

Next, learn to integrate existing users from other environments, such as LDAP
servers, into Liferay.

