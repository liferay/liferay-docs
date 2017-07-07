# Sending Messages with Private Messaging [](id=sending-messages-with-private-messaging)

The Private Messaging app lets you send email-like messages to other users. Its
interface is similar to an email client. Messages are aggregated by thread, with
the most recent message displayed. You can mark messages as read or unread or
delete them.

+$$$

**Note:** There is a [known issue](https://issues.liferay.com/browse/LPS-69331) 
that causes the Private Messaging app to be treated as a brand-new module 
during the initial upgrade process. This issue is fixed in Liferay fixpack de-10.

$$$

The Private Messaging app must be installed from Marketplace. Once installed,
the app must be added to a user's private *Dashboard* page. Now that you know
where you can get the app and where to place it, once it's there, users can
write messages. This is covered next.

## Composing Messages [](id=composing-messages)

Once the app is added to a user's Dashboard page, click the *New Message* 
button to compose a new message. Just like email, you can enter a subject, 
a message body, and add attachments. The *To* field autocompletes so you can
select your recipient. You can select any number of users.

![Figure 1: The Private Messaging Interface functions just like an email client for use within a Portal instance.](../../../images/private-messages-inbox.png)

By default, all users are available in this list. You can filter the available
list of users by configuring the app. This is covered next.

## Configuring System Settings [](id=configuring-system-settings)

You can filter the set of autocomplete users by social connection, status, or
site membership. This configuration, however, must be done by an administrator
who has access to the Private Messaging portlet's system settings.

To access the Private Messaging portlet's system settings, open the Control 
Panel, navigate to *Configuration* &rarr; *System Settings*, and click 
*Private Messaging Service* under the *Other* tab.

The following settings filter the autocomplete users for the Private Messaging
portlet:

**Autocomplete recipient type:** Filters the scope of autocomplete users in the
message's *To* field. Note that although this filters the autocomplete list of
users, you can still send a private message to a user if you know his or her
user name. The default value is `all`. Possible values are listed below:

- `all`: displays all users across your installation in the autocomplete
  recipient list.
- `connection`: displays only social connections in the autocomplete recipient 
  list.
- `site`: displays only fellow site members in the autocomplete recipient list.

**Autocomplete recipient site excludes:** Sets specific sites to exclude from
the autocomplete recipient list when **Autocomplete recipient type** is set to
`site`. This is especially useful if you have a default site that all users
belong to, but you still want to filter the autocomplete recipient user list 
by site. That site is then ignored when determining the users that are available
in the autocomplete recipient list.

The Private Messaging app has a simple function: allow users to communicate with
each other privately. If this is something your users want, now you know how to
use it. 

