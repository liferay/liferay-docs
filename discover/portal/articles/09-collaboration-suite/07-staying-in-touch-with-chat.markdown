# Staying in touch with Chat and Meetings [](id=staying-in-touch-with-the-chat)

Liferay's Chat and Meetings apps let you stay in contact and collaborate with 
other users. The Chat portlet lets you send instant messages to other logged-in 
users. The Meetings app integrates with the BigBlueButton and Zoom web 
conferencing services to let you schedule and conduct meetings from your Liferay 
instance. We'll cover both apps here. You're only a few clicks away from your 
fellow users! 

## Using Chat [](id=using-chat)

Liferay's Chat portlet provides a convenient way of allowing users to send each
other instant messages when they are logged into your web site. It appears as a
bar at the bottom of every page, showing who is logged on, their statuses, and
any chats the logged-in user has open.

![Figure 9.33: Liferay's Chat Portlet offers ways to update your status and notifications through the *Settings* menu.](../../images/05-liferay-chat-portlet.png)

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

### Filtering Available Users [](id=filtering-available-users)

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

### Jabber Server Integration [](id=jabber-server-integration)

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

#### Installation Steps [](id=installation-steps)

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

![Figure 9.34: View your server information using the Openfire Administration Web Tool.](../../images/jabber-service-name.png)

Additionally, make sure that you set `jabber.enabled` to `true` and have added
the correct values to `jabber.host` and `jabber.port`. If you installed your
chat server on a remote machine or chose to not use the default port, change
`jabber.host` and `jabber.port` accordingly.

3. Deploy your Chat portlet. Remember that this portlet must be of version 6.1
or higher.

#### Single Sign On [](id=single-sign-on)

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

## Using Meetings [](id=using-meetings)

The Meetings app, available in the Liferay Marketplace, integrates with the 
BigBlueButton and Zoom web conferencing services. It lets you schedule, manage, 
and launch meetings from within Liferay. This app includes two portlets: 
Meetings, and Meetings Admin. The Meetings portlet lets you schedule, manage, 
and launch meetings. The Meetings Admin portlet lets you add and configure 
BigBlueButton and Zoom servers to conduct meetings with. You must have a server 
configured in Meetings Admin before you can create a meeting in the Meetings 
portlet. 

Once you deploy the Meetings app, you can find Meetings Admin in the Control 
Panel's *Configuration* section. When you first access Meetings Admin, it 
presents you with an *Add Server* button and a message telling you there are no 
servers. To add a server, click *Add Server*. In the *New Server* form that 
appears, give your server a name and select *Zoom* or *BigBlueButton* in the 
*Provider Type* menu. If you select Zoom, then you must enter your Zoom API key 
and secret in the corresponding fields. If you select BigBlueButton, then you 
must enter your BigBlueButton API URL and secret. The following screenshot shows 
the *New Server* form. 

![Figure 9.35: Create a new meeting server by filling out the *New Server* form.](../../images/meetings-add-server.png)

When you finish entering your server's information, click *Save*. Meetings Admin 
then lists your server in a table. You can edit or delete this server by 
clicking its *Actions* button and selecting *Edit* or *Delete*. Once you have a 
server, you can create a meeting in the Meetings portlet. 

The Meetings portlet is in the Application menu's Social category. You can add 
it to any page you want to create meetings from. This portlet lists any 
scheduled meetings in the *Current* tab. The *Completed* tab lists any finished 
meetings. Click the *Add Meeting* button to schedule a meeting. In the *New 
Meeting* form that appears, enter your meeting's information. First, give your 
meeting a name and enter its time, date, and description. In the *Provider* 
field, select the server to use. Note that in this field *Preferred Solution* 
represents a Zoom server, and *Private Solution* represents a BigBlueButton 
server. To require attendees to use a password to join the meeting, check the 
*Require Password* checkbox and then create this password by typing it into the 
text field below the checkbox. To set the meeting to automatically begin the 
video conference when attendees join, check the *Automatically Start Video* 
checkbox. Lastly, add the participants that you want to join your meeting, and 
click *Save*. The Meetings portlet then lists your meeting in a table in the 
*Current* tab. Scheduling a meeting notifies the participants via the 
Notifications portlet and email. The email contains the organizer's name, the 
meeting's date and time, and a link and instructions for joining the meeting. 
The following screenshots show the *New Meeting* form, and the Meetings portlet 
with a currently scheduled meeting. 

![Figure 9.36: Create a new meeting by filling out the *New Meeting* form.](../../images/meetings-new-meeting.png)

![Figure 9.37: The Meetings portlet lists any currently scheduled meetings in its *Current* tab.](../../images/meetings-current.png)

To view a meeting's details, simply click it in the Meetings portlet's table. If 
you have the correct permissions, this also presents you with the options to 
edit, delete, start, or export the meeting. You can also view the meeting's 
invitation email template from here. Note that you don't have to go into the 
meeting's details to perform these actions. You can also do so from each 
meeting's *Actions* button in the table. Note that when a meeting is in 
progress, *Start Meeting* becomes *End Meeting*, and *Join Meeting* appears. 

It's important to note that your meeting doesn't actually run *inside* Liferay. 
When you start or join a meeting, the Meetings portlet hands you off to the 
meeting's web conferencing service (BigBlueButton or Zoom) via the server you 
specified in the Meetings Admin portlet. To start or join a meeting, you must 
therefore have any required BigBlueButton or Zoom software installed on your 
machine. 

Next, let's look at how you can integrate your email addresses with Liferay's
Mail portlet.
