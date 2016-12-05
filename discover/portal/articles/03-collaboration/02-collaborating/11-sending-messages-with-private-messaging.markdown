# Sending Messages with Private Messaging [](id=sending-messages-with-private-messaging)

The Private Messaging app allows you to send email-like messages to other users 
within @product@. Its interface is similar to that of an email client. Messages 
are aggregated by thread, with the most recent message displayed. You can select 
messages to mark them as read or unread, or to delete them.

Note that the Private Messaging app is not available by default and must be
installed from the Marketplace. Once installed from the Marketplace, the 
app must be added to a User's private *Dashboard* page. Now that you know where 
you can get the app and where to place it, you're probably eager to write 
messages. This is covered next.

## Composing Messages [](id=composing-messages)

Once the app is added to a user's *Dashboard* page, click the *New Message* 
button to compose a new message. Just like email, you can enter a subject, 
a message body, and add attachments. Click in the *To* field and an autocomplete
recipient list of users appears that lets you select who to send your message to. 
You can select any number of users.

![Figure 1: The Private Messaging Interface functions just like an email client for use within a Portal instance.](../../../images/private-messages-inbox.png)

By default, all portal users are available in autocomplete recipient list of the 
Private Messaging portlet. You can filter the available list of users through 
custom configuration. This is covered next.

## Configuring System Settings [](id=configuring-system-settings)

You can filter the set of available users in the autocomplete recipient list of 
the *To* field by social connection, status, or site membership. However, this 
configuration must be done by an administrator since this configuration must be 
made to the Private Messaging portlet's system settings.

To access the Private Messaging portlet's system settings, open the Control 
Panel, navigate to *Configuration* &rarr; *System Settings*, and click 
*Private Messaging Service* under the *Other* tab.

The following settings allow you to filter the autocomplete recipient list of 
users for the Private Messaging portlet:

**Autocomplete recipient type:** Filters the type of users that appear in the 
autocomplete recipient list of the message's *To* field. Note that although this 
setting restricts the autocomplete recipient list of users, you can still send a 
private message to a user if you know their username. The default value is `all`. 
Possible values are listed below:

- `all`: Displays all users across your portal in the autocomplete recipient 
  list.
- `connection`: Displays only social connections in the autocomplete recipient 
  list.
- `site`: Displays only fellow site members in the autocomplete recipient list.

**Autocomplete recipient site excludes:** Sets specific sites to exclude from
the autocomplete recipient list when **Autocomplete recipient type** is set to
`site`. This is especially useful if you have a default site that all portal 
users belong to, but you still want to filter the autocomplete recipient list of 
users by site. That site is then ignored when determining the users that are 
available in the autocomplete recipient list.
