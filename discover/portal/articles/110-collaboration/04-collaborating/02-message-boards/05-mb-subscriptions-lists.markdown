# User Subscriptions and Mailing Lists [](id=user-subscriptions-and-mailing-lists)

The Message Boards notifies users of message boards activity via email in two
ways:

-   User subscriptions
-   Mailing lists

+$$$

**Note:** Since multiple sites can use a globally scoped message board, such 
message boards don't support user subscriptions or mailing lists. Make sure to 
use a site-scoped or page-scoped message board if you need user subscriptions or 
a mailing list. 

$$$

## User Subscriptions [](id=user-subscriptions)

In the user subscriptions mechanism, the Message Boards app uses its configured 
*Email From* address to send email notifications to subscribed users. The app 
can also import email replies to message board notifications directly into the
message board. Then, users can interact on the message board via email without
logging in to view the message board directly. This is disabled by default. To
enable it, add the following line to your `portal-ext.properties` file: 

    pop.server.notifications.enabled=true

The user subscription mechanism uses the POP mail protocol. When the Message 
Boards app receives an email reply to a message board notification, it posts 
that reply to the message board and then deletes it from the mail server. 
Deleting the message from the mail server is the POP protocol's default behavior 
and the Message Boards app assumes that your POP mail server behaves this way. 
Most POP clients offer an option to leave mail on the mail server after it 
downloads, but you shouldn't exercise this option. If you configure mail to be 
left on the mail server, the Message Boards app sends copies of each retained 
message along with each new email notification it sends to subscribed users. 

When enabling Message Boards to import replies to email notifications, you must 
decide whether to handle notifications with a mail server subdomain. By default, 
the following property setting is specified in the portal properties: 

    pop.server.subdomain=events

This property creates a special MX (mail exchange) subdomain to receive all
virtual instance related email (e.g., events.liferay.com). If you don't want to
use this approach, unset this value in a `portal-ext.properties` file: 

    pop.server.subdomain= 

Doing so tells Message Boards to use the *Email From* address specified in the 
Message Board's configuration to receive message board notification email 
replies. For example, the *Email From* address could be set to 
*replies\@liferay.com*. 

If you're not using a mail subdomain, Message Boards parses the message headers 
of emails from the *Email From* address to determine the message board category 
and message ID. If you keep the `pop.server.subdomain=events` default, the email 
notification address takes the following form:

    mb.[category_id][message_id]@events.liferay.com

In this case, Message Boards parses the email address to find the category and 
message ID. Parsing the email address is safer than parsing message headers, 
since different email clients treat message headers differently. This is why the 
`events` subdomain is enabled by default. 

You can also configure the interval on which the `POPNotificationListener` runs. 
The value is set in one minute increments. The default setting is to check for 
new mail every minute, but you can set it to whatever you like: 

    pop.server.notifications.interval=1

+$$$

**Note**: Depending on your mail provider, if you use multiple devices to access
email through POP, you might need to configure in your POP settings something
like Gmail's *recent mode*. In Gmail, recent mode assures that emails go to all
your devices instead of only the first client that receives the email. To
enable recent mode in Gmail, for example, prefix the value of your POP client's
Username or Email field with `recent:`. 

$$$

## Mailing Lists [](id=mailing-lists)

Alternatively, the Message Boards app can use mailing lists to send email 
notifications. Any category in a message board can have its own mailing list. 
The mailing list mechanism, unlike the user subscription mechanism, supports 
both the POP and the IMAP protocols. POP is the default, but each message 
board's mailing list is configured independently. If you choose the IMAP 
protocol for a category's mailing list, make sure to configure the IMAP inbox to 
delete messages as they are pulled by the email client that sends messages to 
the users on the mailing list. Otherwise, each email message retained on the 
server is sent to the mailing list each time there's a new post or update in the 
category. 

When a mailing list is enabled for a message board category, Message Boards 
listens to the specific email inbox that's configured for the mailing list. 
Enabling the mailing list function lets users on the mailing list reply to the 
notification messages in their email clients. Message Boards pulls the messages 
from the email inbox it's configured to listen to and automatically copies those 
replies to the appropriate message board thread. 

To enable the mailing list functionality for a category, follow these steps:

1.  Set up a dedicated email address for the category. 

2.  Click the category's *Actions* icon
    (![Actions](../../../../images/icon-actions.png)) and select *Edit*. 

3.  In the *Mailing List* section of the form, set the *Active* slider to *YES*. 
    Several options then appear. Fill these out as follows: 

    **Email Address:** The email address of the account that receives the 
    messages. 

    **Protocol:** Select POP or IMAP.

    **Server Name:** Your mail server's host name. 

    **Server Port:** The port on which your mail service is running.

    **Use a Secure Network Connection:** Whether to use an encrypted 
    connection if your server supports it. 

    **User Name:** The login name on the mail server. 

    **Password:** The password for the account on the server. 

    **Read Interval (Minutes):** How often to poll the server looking for new
    messages to post. 

    **Email Address (Outgoing):** The email address originating messages from
    this category. If you want your users to be able to reply to the categories
    using email, this should be the same address as the incoming email address. 

    **Use Custom Outgoing Server:** Use a different mail server than global
    default. Fields appear for configuring the server's name, port, user name,
    password, and secure connection. 

4.  If you want to let emails from anonymous users post to the message board 
    category, set the *Allow Anonymous Emails* toggle to *YES*. 

5.  Click *Save* when you're finished. 
