# Using the Control Panel [](id=using-the-control-panel)

In this chapter, we continue our coverage of Liferay's control panel that we
began in chapter 15. We'll cover the following topics:

- Portal settings including mail host names, email notifications,
  identification, and display settings

- Custom fields

- Monitoring

- Plugins configuration

- Server administration, including resources, log levels, properties, captcha
  data migration, file uploads, mail, external services, scripts, and shutdown
  settings

- Portal instances

Let's begin with mail host names.

## Mail Host Names [](id=mail-host-names)

Mail Host Names appears after Authentication and Users on the Portal Settings
page of the control panel. You can enter other mail host names (one per line)
besides the one you configured on the General tab. This lets the portal know
which mail host names are owned by your organization.

## Email Notifications [](id=email-notifications)

There are five tabs under the Email Notifications page of Portal Settings. The
Sender tab allows you to set the portal's administrative name and email address.
By default, these are `Joe Bloggs` and `test@liferay.com`. You can change them
to whatever you want. This name and email address will appear in the From field
in all email messages sent by the portal.

![Figure 16.1: Automated Emails: Account Created Notification](../../images/server-configuration-account-created-notification.png)

The other four tabs are Account Created Notification, Email Verification
Notification, Password Changed Notification and Password Reset Notification.
These tabs allow you to customize the email messages that are sent to users each
time any of those four events occur.

![Figure 16.2: Definition of Terms for Automated Emails](../../images/server-configuration-definition-of-terms.png)

A list of tokens, entitled "Definition of Terms," is provided so you can insert
certain values (such as the portal URL or the user ID) when you are setting up
the custom email messages.

## Identification [](id=identification)

The identification section has several links for addresses, phone numbers and
other information you can configure in your portal. This allows you to set up
contact information for the organization that owns the portal. Developers can
query for this information in their applications.

## Miscellaneous: Display Settings [](id=miscellaneous-display-settings)

This section allows you to set the default portal language and the time zone.
You can also set up a portal-wide logo which appears in the top left corners of
portal pages. 

![Figure 16.3: Server Configuration Miscellaneous Display
Settings](../../images/server-configuration-miscellaneous-display-settings.png)

Liferay's default theme is configured to display the portal logo. For custom
themes, you can choose whether or not to display the logo. Be careful to choose
an image file that fits the space. If you pick something too big, it might
overlap with the navigation. Next, let's look at how to customize different
types of portal assets using custom fields.
