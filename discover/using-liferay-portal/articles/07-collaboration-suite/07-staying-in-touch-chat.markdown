# Staying in Touch With the Chat [](id=staying-in-touch-with-the-chat)

Liferay's Chat portlet provides a convenient way of allowing users to send each
other instant messages when they are logged into your web site. It appears as a
bar at the bottom of every page, showing who is logged on, their statuses, and
any chats the logged-in user has open.

![Figure 7.35: Liferay's Chat
Portlet](../../images/05-liferay-chat-portlet.jpg)

The Chat portlet is distributed with the Liferay bundles, but is not included
as part of the `.war` distribution, as it is a separate plugin. If you
installed the Liferay `.war` manually on your application server, you can
install the Chat portlet by going to the Control Panel, clicking *Plugins
Installation*, and then clicking the *Install More Portlets* button. Find the
Chat portlet in the list, click on it, and then click *Install*.

The Chat portlet is very simple to use. To change the settings, click
*Settings* (found near the lower right corner next to *Online Friends*). Here
you can set your status, choose whether or not to show that you are online, and
whether or not to play a sound if someone sends you a message while you have
the window or tab in the background. The Chat portlet displays the number of
your friends who are online. Click the *Online Friends* link and then click on
a friend's name to open a chat window. You can have multiple chats open at a
time, and can have one or more of them minimized.

## Jabber Server Integration [](id=jabber-server-integration)

Liferay 6.1 introduced Jabber server integration to Liferay's Chat portlet.
Jabber is the original name of the XMPP (Extensible Messaging and Presence
Protocol) protocol, an open-standard communications protocol based on XML.
Using a chat server helps Liferay's chat scale to very large installations and
allows for communication between different chat clients. For example, Jabber
server integration allows users using the chat portlet in their browser windows
to chat with other users using desktop clients like Empathy, Pidgin, or Kopete. 

Jabber server integration is not enabled by default since it requires a running
Jabber server. Once you have installed and started a Jabber server, you can
enable Jabber server integration by creating a `portlet-ext.properties` file to
override some properties of your Chat portlet's `portlet.properties` file. You
could modify your Chat portlet's `portlet.properties` file directly, but it's a
best practice to override it instead.

### Installation Steps [](id=installation-steps)

You can use any chat server that supports Jabber. The Chat portlet's Jabber
server integration feature was tested with versions 3.7.0 and 3.7.1 of
Openfire, a real time collaboration server distributed under the Open Source
Apache License. You can download Openfire from
[http://www.igniterealtime.org/projects/openfire/](http://www.igniterealtime.org/projects/openfire/).
To enable Jabber chat integration, follow these steps:

1. Start your chat server. If you are using Openfire on a Linux/Mac system, you
can start/stop the chat server by executing the openfire shell script in the
`openfire/bin` directory. Usage: `./openfire start` or `./openfire stop`

2. Override the `portlet.properties` file in your /chat-portlet/WEB-INF/src/
directory with a `portlet-ext.properties` file in the same directory. When you
deploy the portlet, the properties files should be copied to your
/chat-portlet/WEB-INF/classes/ directory. If you have already deployed the Chat
portlet, create the `portlet-ext.properties` file in your
/chat-portlet/WEB-INF/classes/ directory. The contents of your
`portlet-ext.properties` file should like this:

		jabber.enabled=true
		jabber.import.user.enabled=true
		jabber.host=localhost
		jabber.port=5222
		jabber.service.name=<Host Name>
		jabber.resource=Liferay
		jabber.sock5.proxy.enabled=false
		jabber.sock5.proxy.port=-1

Note that you must change `jabber.service.name` to the "Host Name". If you are
using Openfire, you can find the Host Name by using the Openfire administration
web tool. If you did not set up administrative credentials when you started
Openfire, the default credentials are username: admin, password: admin.

![Figure 7.36: Openfire Administration Web
Tool](../../images/jabber-service-name.png)

Additionally, make sure that you set `jabber.enabled` to `true` and have added
the correct values to `jabber.host` and `jabber.port`. If you installed your
chat server on a remote machine or chose to not use the default port, change
`jabber.host` and `jabber.port` accordingly.

3. Deploy your Chat portlet. Remember that this portlet must be of version 6.1
or higher.

### Single Sign On [](id=single-sign-on)

If the property `jabber.import.user.enabled` is set to `true`, the Chat portlet
will import the user automatically to Jabber after he logs in to the portal.
Once the user is imported, he can use any Jabber client using the same screen
name and password he uses to log in to the portal. His buddies will be also
imported as they become online in the Chat portlet.

Note that it's a "lazy import". Users are imported only after they log in to
the portal and their buddies will be added to his list only if they see each
other within the Chat portlet. They won't be able to use other Jabber chat
clients until they log in to the portal.

If `jabber.import.user.enabled` is set to `false`, users need to create their
Jabber account and add buddies manually. They have to create their accounts
using the same screen name and password they use in the portal. If they don't,
the Chat portlet won't be able to connect to their Jabber account.

Alternatively, since Openfire integrates with LDAP, if you are using Openfire
and your portal is also using LDAP for authentication, you can disable the
`jabber.import.user.enabled` property.

Next, let's look at how you can integrate your email addresses with Liferay's
Mail portlet.
