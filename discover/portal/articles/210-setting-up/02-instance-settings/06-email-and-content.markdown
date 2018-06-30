# Email and Content [](id=email-and-content)

There are a few sections of the Configuration tab dedicated to configuring
email and content sharing:

- Mail Host Names
- Email notifications
- Content Sharing

Configure these settings at Control Panel &rarr; Configuration &rarr; Instance
Settings. In the Configuration tab, scroll down to the bottom. These three
entries are last in the tab.

## Mail Host Names [](id=mail-host-names)

Enter one mail host name per line, besides the one you configured on the General
tab. This tells the virtual instance which mail host names are owned by your
organization.

## Email Notifications [](id=email-notifications)

The Sender tab sets the virtual instance's administrative name and email
address.  By default, these are `Test Test` and `test@liferay.com`. This name
and email address appear in the *From* field in all email messages sent by the
virtual instance.

![Figure 1: Customize the email template for the email messages sent to new Users.](../../../images/instance-settings-account-created.png)

The remaining tabs (Account Created Notification, Email Verification
Notification, Password Changed Notification and Password Reset Notification)
customize the email messages sent to Users each time any of those four events
occur.

![Figure 2: There are some handy variables available for use in email templates.](../../../images/instance-settings-definition-of-terms.png)

A list of tokens, entitled "Definition of Terms," is provided for insertion of
certain variables (such as the portal URL or the User ID) into custom email
messages.

## Content Sharing [](id=content-sharing)

Choose if Site administrators can display content in Sites from other Sites they
administer. For example, suppose that a certain User is a Site administrator of
two Sites: *Engineering* and *Marketing*. The checkbox in the Content Sharing
section of Instance Settings determines if the Site administrator can display
content from the Marketing Site in the Engineering Site and vice versa.

You can also choose if sub-sites can display content from parent Sites and
configure the defaults. There are three options:

**Enabled by Default**: Subsites can display content from parent Sites by
default, but this can be disabled by a Site administrator.

**Disabled by Default**: Subsites cannot display content from parent Sites by
default, but this can be enabled by a Site administrator.

**Disabled**: Subsites cannot display content from parent Sites, and this
behavior cannot be changed by a Site administrator.

That covers a lot of Instance Settings, but you're not finished yet. The next
article covers the identification and social settings.
