# Staying in touch with the Chat [](id=staying-in-touch-with-the-chat-lp-6-2-use-useportal)

Liferay's Chat portlet provides a convenient way of allowing users to send each
other instant messages when they are logged into your web site. It appears as a
bar at the bottom of every page, showing who is logged on, their statuses, and
any chats the logged-in user has open.

![Figure 8.33: Liferay's Chat Portlet offers ways to update your status and notifications through the *Settings* menu.](../../images/05-liferay-chat-portlet.png)

The Chat portlet is distributed with the Liferay bundles, but is not included
as part of the `.war` distribution, as it is a separate plugin. If you
installed the Liferay `.war` manually on your application server, you can
install the Chat portlet by accessing Liferay Marketplace. You can go to
Marketplace by navigating the Control Panel and clicking *Store* under the
*Apps* heading. You can learn more about Marketplace and how to
purchase/download an app in the *Liferay Marketplace* chapter.

The Chat portlet is very simple to use. To change the settings, click *Settings*
(found near the lower right corner next to *Online Friends*). Here you can set
your status, choose whether or not to show that you are online, whether or not
to play a sound if someone sends you a message while you have the window or tab
in the background, and whether to enable desktop notifications for new messages.
The Chat portlet displays the number of your friends who are online. Click the
*Online Friends* link and then click on a friend's name to open a chat window.
You can have multiple chats open at a time, and can have one or more of them
minimized.

## Filtering Available Users 

By default, all online portal users appear in the Chat portlet. If you want to
filter who appears in your contact list you can, but the configuration must be
done by someone who has administrative access to the server. The configuration
change must be made at the time the chat portlet is deployed. 

To filter users, create a `portlet-ext.properties` file to override some
properties of your Chat portlet's `portlet.properties` file. You could modify
your chat portlet's `portlet.properties` file directly, but it's a best practice
to override it instead. 

Before you deploy your Chat portlet, extract it to your file system. You can
create the `portlet-ext.properties` file in the `chat-portlet/WEB-INF/src`
directory. It gets copied over to your `chat-portlet/WEB-INF/classes` directory
upon deployment. When you're finished making changes to your
`portlet-ext.properties` file, you'll zip the directory structure back into a
.war file for deployment. Note that the Chat portlet must be redeployed for
settings in properties files to take effect. 

The property that refines the list of users that show up in the Chat portlet is
`buddy.list.strategy`. Some common values are listed here:

		buddy.list.strategy=all
		buddy.list.strategy=sites
		buddy.list.strategy=social
		buddy.list.strategy=sites,social

The default value is `all`. To show only other users of the sites a user belongs 
to, set `buddy.list.strategy` to `sites`. Setting `buddy.list.strategy` to 
`social` makes only a user's social connections available in the Chat portlet. 
Note that you can also combine values by separating them with a comma. Combined 
values behave like a logical AND statement. Thus, `sites,social` shows other 
users of the sites a user belongs to, and the user's social connections. 

You can also further refine the `sites` setting by using the property 
`buddy.list.site.excludes`. This property allows you to exclude specific sites 
in your portal from the `buddy.list.strategy=sites` setting. This is especially 
useful if you have a default site that all portal users belong to, but you still 
want to filter the users that appear in the Chat portlet by site. If this 
default site isn't excluded, then all portal users show up in the chat portlet 
when `buddy.list.strategy` is set to `sites`. The site name to give for 
`buddy.list.site.excludes` is the value of the `name` column for the `Group` 
table in your portal's database. It must be entered in the same case as it is in 
the database. For example, if you want to exclude a site called Default, enter 
`buddy.list.site.excludes=Default`. That site is then ignored when determining
the users to show in the Chat portlet. 

The `social` setting for `buddy.list.strategy` has further filtering options as 
well. You can set the allowed types of social relationships through the property 
`buddy.list.allowed.social.relation.types`. By default this property is set to
`2,12`. Those values correspond, respectively, to the *Friend* and *Connection*
social relationship types. The values for some additional social relationship
types are listed here along with those of *Friend* and *Connection*:

		TYPE_BI_CONNECTION = 12
		TYPE_BI_COWORKER = 1
		TYPE_BI_FRIEND = 2
		TYPE_BI_ROMANTIC_PARTNER = 3
		TYPE_BI_SIBLING = 4

These are bidirectional social relationship types as defined in Liferay's social 
API. It's important to note that these aren't available out-of-the-box. You must 
install apps that make use of them before you can leverage them in your portal. 
For example, Liferay's Social Networking app from the [Marketplace](http://www.liferay.com/marketplace) makes use of 
the "Friend" social relationship type. Similarly, Liferay Social Office uses the 
"Connection" social relationship type. Developers can make use of any of the 
social relationship types available in the API.

## Jabber Server Integration 

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

### Installation Steps 

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

![Figure 8.34: View your server information using the Openfire Administration Web Tool.](../../images/jabber-service-name.png)

Additionally, make sure that you set `jabber.enabled` to `true` and have added
the correct values to `jabber.host` and `jabber.port`. If you installed your
chat server on a remote machine or chose to not use the default port, change
`jabber.host` and `jabber.port` accordingly.

3. Deploy your Chat portlet. Remember that this portlet must be of version 6.1
or higher.

### Single Sign On 

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
