# Email and Content [](id=email-and-content)

There are a few sections of the Configuration tab dedicated to configuring
email and content sharing:

- Mail Host Names
- Email notifications
- Content Sharing

Configure these settings at Control Panel &rarr; Configuration &rarr; Instance
Settings. In the Configuration tab, scroll down to the bottom. These three
entries are the last in the tab.

## Mail Host Names [](id=mail-host-names)

Enter one mail host name per line, besides the one you configured on the General
tab. This tells the virtual instance which mail host names are owned by your
organization.

## Email Notifications [](id=email-notifications)

The Sender tab allows you to set the virtual instance's administrative name and
email address.  By default, these are `Test Test` and `test@liferay.com`. This
name and email address appear in the *From* field in all email messages sent by
the virtual instance.

![Figure 1: Customize the email template for the email messages sent to new Users.](../../../images/instance-settings-account-created.png)

The remaining tabs (Account Created Notification, Email Verification
Notification, Password Changed Notification and Password Reset Notification) are
used to customize the email messages sent to Users each time any of those four
events occur.

![Figure 2: There are some handy variables available for use in email templates.](../../../images/instance-settings-definition-of-terms.png)

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
