# Configuring Additional Portal Settings [](id=configuring-additional-portal-settings)

Go back to the Control Panel, and click on *Portal Settings* under the
*Configuration* heading. We've already looked at the options available from the
*General* and *Authentication* links. Now let's examine the other portal
settings options.

## Users

If you click on *Users* from the Portal Settings screen, you'll find three tabs:
Fields, Reserved Credentials and Default User Associations.

The Fields tab allows you to enable/disable the following fields:

-   Enable/disable requiring the Terms of Use

-   Enable/disable user screen names autogeneration

-   Enable/disable requiring the last names

-   Enable/disable the birthday field

-   Enable/disable the gender field

The next tab is Reserved Credentials. You can enter screen names and email
addresses here that you don't want others to use. Liferay will then prevent
users from registering with these screen names and email addresses. You might
use this feature to prevent users from creating IDs that look like
administrative IDs or that have reserved words in their names.

The Default User Associations tab has three fields allowing you to list (one per
line) sites, roles, and user groups you want new users to become members of
automatically. By default, Liferay assigns new users to both the Users role and
the Power Users role.

If you have defined other user groups, sites or roles you want newly created
users to be members of by default, enter them here. For example, you may have
defined site templates in certain user groups to pre-populate end users' private
pages. If there is a particular configuration you want everyone to have, you may
want to enter those user groups here.

## Mail Host Names

Mail Host Names appears after Authentication and Users on the Portal Settings
screen of the Control Panel. You can enter other mail host names (one per line)
besides the one you configured on the General tab. This lets the portal know
which mail host names are owned by your organization.

## Email Notifications

There are five tabs under the Email Notifications page of Portal Settings. The
Sender tab allows you to set the portal's administrative name and email address.
By default, these are `Joe Bloggs` and `test@liferay.com`. You can change them
to whatever you want. This name and email address will appear in the From field
in all email messages sent by the portal.

![Figure 3.5: You can customize the email template for the email messages sent to users who have just created their accounts.](../../images/server-configuration-account-created-notification.png)

The other four tabs are Account Created Notification, Email Verification
Notification, Password Changed Notification and Password Reset Notification.
These tabs allow you to customize the email messages that are sent to users each
time any of those four events occur.

![Figure 3.6: You can refer to this list of variables that's available for use in email templates.](../../images/server-configuration-definition-of-terms.png)

A list of tokens, entitled "Definition of Terms," is provided so you can insert
certain values (such as the portal URL or the user ID) when you are setting up
the custom email messages.

## Content Sharing

The Content Sharing section of the Portal Settings area of the Control Panel
allows you to choose whether or or not site administrators can display content
in sites from other sites they administer. For example, suppose that a certain
user is a site administrator of two sites: *Engineering* and *Marketing*. The
checkbox in the Content Sharing section of Portal Settings determines whether or
not the site administrator can display content from the Marketing site in the
Engineering site and vice versa.

The Content Sharing section of Portal Settings also allows you to choose a
behavior for whether or not subsites can display content from parent sites and
for configuring the defaults. There are three options:

**Enabled by Default**: This means that subsites can display content from parent
sites by default but this can be disabled by a site administrator.

**Disabled by Default**: This means that subsites cannot display content from
parent sites by default but this can be enabled by a site administrator.

**Disabled**: This means that subsites cannot display content from parent sites
and this behavior cannot be changed by a site administrator.

## Identification

The identification section has several links for addresses, phone numbers and
other information you can configure in your portal. This allows you to set up
contact information for the organization that owns the portal. Developers can
query for this information in their applications.

## Miscellaneous: Display Settings

This section allows you to set the default portal language and the time zone.
You can also set up a portal-wide logo which appears in the top left corners of
portal pages. 

![Figure 3.7: You can specify various display settings for your portal including options for the default language, other available language, time zone, portal logo, and default themes for the portal and Control Panel.](../../images/server-configuration-miscellaneous-display-settings.png)

Liferay's default theme is configured to display the portal logo. For custom
themes, you can choose whether or not to display the logo. Be careful to choose
an image file that fits the space. If you pick something too big, it might
overlap with the navigation. Next, let's look at how to customize different
types of portal assets using custom fields.

<!-- Should we provide a range of recommended parameters for custom logos?  -->
