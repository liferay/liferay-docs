# Integrating your email with Liferay Mail [](id=integrating-your-email-with-liferay-mail)

Liferay's Mail portlet enables your users to interact with their email using an
easy to use, ubiquitous web interface. If your mail system supports the IMAP
protocol, you can use the Mail portlet to integrate your users' mail with the
rest of your web site. You can also connect the Mail portlet to a mail account
provided by Google.

The Mail portlet is distributed with the Liferay bundles, but is not included
as part of the `.war` distribution, as it is a separate plugin. If you
installed the Liferay `.war` manually on your application server, you can
install the Mail portlet by accessing Liferay Marketplace. You can go to
Marketplace by navigating the the Control Panel and clicking *Store* under the
*Apps* heading. You can learn more about Marketplace and how to
purchase/download an app in the *Liferay Marketplace* chapter.

![Figure 8.35: Read, reply, and create messages using Liferay's Mail Portlet.](../../images/05-mail-portlet.png)

To connect the Mail portlet with an email account, click the *Add Mail Account*
link. From there, you are given a choice between a Custom email Account or a
Gmail Account. Choose the option that you wish, and fill out the form that
appears.

For a Gmail account, all you need to do is provide your email address and your
password, and the portlet will take care of the rest.

For a Custom Mail Account, the following fields are necessary:

**Address**: lets you enter the email address which receives mail for this
account.

**Login**: lets you choose a user name for logging into the account.

**Password**: lets you choose a password for logging into the account.

**Save Password**: allows Liferay to save your password so you won't have to
enter it next time you access your mail account.

**Incoming Host Name**: allows you to specify the host name for your IMAP
(Internet Mail Access Protocol) or POP server.

**Incoming Port**: allows you to specify the port upon which the IMAP or POP
service is running.

**Use Secure Incoming Connection**: allows you to use an encrypted connection
to the server provided that your server supports it.

**Outgoing SMTP Server**: lets you enter the host name of your SMTP (Simple
Mail Transfer Protocol) server.

**Outgoing Port**: allows you to specify the port upon which the SMTP service
is running.

**Use Secure Outgoing Connection**: allows you to use an encrypted connection
to the server provided that your server supports it.

When finished, click *Save*. Your new email account now appears as a tab at the
top of the page along with the button for adding a mail account. In this way,
you can add as many mail accounts as you want in order to view them in the
portlet.

Click the tab for the mail account you just configured to be brought to an
interface which allows you to read your mail and compose new messages. To read a
message, click on it. To compose a new message, click the *Compose* link on the
left side of the portlet. A form appears which allows you to compose an email
message using the same rich text editor that appears everywhere else in Liferay.
You can read, reply, and create messages, as well as manage all of your folders
in Liferay's Mail portlet.

The Mail portlet is a great way to integrate a familiar service with other the
collaboration features that Liferay provides. 
