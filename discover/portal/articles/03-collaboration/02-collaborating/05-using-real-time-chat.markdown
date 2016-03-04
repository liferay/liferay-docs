# Using Real-Time Chat [](id=using-real-time-chat)

Liferay's Chat app provides a convenient way for users to send each other 
instant messages when they are logged into your Liferay instance. It appears as 
a bar at the bottom of every page, showing who is logged on, their statuses, and 
any chats the logged-in user has open. 

![Figure 1: Liferay's Chat app lets you update your status and notifications through the *Settings* menu.](../../../images/chat-settings.png)

You can use Liferay Marketplace to install the Chat app. Go to Marketplace by 
navigating to Control Panel &rarr; *Apps* &rarr; *Store*. You can learn more 
about Marketplace and how to purchase/download an app in 
[the article on using Marketplace](/discover/portal/-/knowledge_base/7-0/leveraging-the-liferay-marketplace). 

+$$$

**Note:** There is currently an issue where the Chat app only works 
intermittently. See 
[the ticket LPS-63827](https://issues.liferay.com/browse/LPS-63827) 
for more details, and to follow progress on the fix. 

$$$

The Chat app is very simple to use. To change its settings, click its *Settings* 
button at the lower-right corner next to *Online Friends*. Here you can set your 
status and choose whether to show that you're online. You can also choose 
whether to play a sound if you receive a message while the window or tab is in 
the background, and enable desktop notifications for new messages. The Chat app 
also displays your online friends in the *Online Friends* tab. Click this tab 
and then click on a friend's name to open a chat window. You can have multiple 
chats open at a time. You can minimize any or all chat windows. 

## Customizing the Chat App [](id=customizing-the-chat-app)

You can also customize the Chat app to display only a specific subset of online 
users, or integrate with an existing Jabber server. You can do both by creating 
a `portlet-ext.properties` file that overrides some properties in the app's 
`portlet.properties` file. You could modify the app's `portlet.properties` file 
directly, but it's a best practice to override it instead. Before deploying the 
Chat app, extract it to your file system. Create the `portlet-ext.properties` 
file in the `chat-portlet/WEB-INF/src` directory. It gets copied over to your 
`chat-portlet/WEB-INF/classes` directory upon deployment. When you're finished 
making changes to your `portlet-ext.properties` file, you'll package the app 
back into a `.war` file (for example, by using the `jar` executable or other 
tool of your choice) for deployment. Note that this must be done by your Liferay 
instance's administrator. 

### Filtering Available Users [](id=filtering-available-users)

By default, all online users in your Liferay instance appear in the Chat app. 
You can filter these users so that the Chat app only shows a specific subset of 
them. For example, you may want the Chat app to only show fellow site members or 
social connections. 

There are a few properties you can set in a `portlet-ext.properties` file to 
filter users. The property `buddy.list.strategy` refines the list of users that 
show up in the Chat app. Some common values are listed here: 

    buddy.list.strategy=all
    buddy.list.strategy=sites
    buddy.list.strategy=social
    buddy.list.strategy=sites,social

The default value is `all`. To show only other users of the sites a user belongs 
to, set `buddy.list.strategy` to `sites`. Setting `buddy.list.strategy` to 
`social` makes only a user's social connections available in the Chat app. Note 
that you can also combine values by separating them with a comma. Combined 
values behave like a logical AND statement. Thus, `sites,social` shows a user's 
social connections and fellow site members. 

You can also further refine the `sites` setting by using the property 
`buddy.list.site.excludes`. This property allows you to exclude specific sites 
in your Liferay instance from the `buddy.list.strategy=sites` setting. This is 
especially useful if you have a default site that all users belong to, but you 
still want to filter the users that appear in the Chat app by site. If you don't 
exclude this default site, then all users show up in the Chat app when you set 
`buddy.list.strategy` to `sites`. The site name to specify for 
`buddy.list.site.excludes` is the value of the `name` column for the `Group` 
table in your Liferay instance's database. You must enter this in the same case 
as it is in the database. For example, if you want to exclude a site called 
Default, enter `buddy.list.site.excludes=Default`. Liferay then ignores that 
site when determining the users to show in the Chat app. 

The `social` setting for `buddy.list.strategy` also has further filtering 
options. You can set the allowed types of social relationships through the 
property `buddy.list.allowed.social.relation.types`. By default, this property 
is set to `2,12`. Those values correspond to the *Friend* and *Connection* 
social relationship types, respectively. The values for some additional social 
relationship types are listed here along with those of Friend and Connection:

		TYPE_BI_CONNECTION = 12
		TYPE_BI_COWORKER = 1
		TYPE_BI_FRIEND = 2
		TYPE_BI_ROMANTIC_PARTNER = 3
		TYPE_BI_SIBLING = 4

These are bidirectional social relationships as defined in Liferay's social API. 
It's important to note that these aren't available out-of-the-box. You must 
install apps that implement them before you can leverage them in your Liferay 
instance. For example, Liferay's Social Networking app in 
[Liferay Marketplace](http://www.liferay.com/marketplace) 
implements the Friend social relationship. Similarly, Liferay Social Office uses 
the Connection social relationship type. Developers can make use of any of the 
social relationship types available in the API. 

### Jabber Server Integration [](id=jabber-server-integration)

Liferay 6.1 introduced Jabber server integration to Liferay's Chat app. Jabber 
is the original name of the XMPP (Extensible Messaging and Presence Protocol), 
an open-standard communications protocol based on XML. Using a chat server helps 
Liferay's Chat scale to very large installations and allows for communication 
between different chat clients. For example, Jabber server integration allows 
users using the Chat app in their browsers to chat with other users using 
desktop clients like Empathy, Pidgin, or Kopete. 

Jabber server integration isn't enabled by default since it requires a running
Jabber server. Once you have installed and started a Jabber server, you can
enable Jabber server integration by creating a `portlet-ext.properties` file to
override some properties of your Chat app's `portlet.properties` file. 

#### Enabling Jabber Integration [](id=enabling-jabber-integration)

You can use any chat server that supports Jabber. The Chat app's Jabber server 
integration feature was tested with versions 3.7.0 and 3.7.1 of Openfire, a real 
time collaboration server distributed under the Open Source Apache License. You 
can download Openfire from
[http://www.igniterealtime.org/projects/openfire/](http://www.igniterealtime.org/projects/openfire/).
To enable Jabber chat integration, follow these steps:

1. Start your chat server. If you're using Openfire on a Linux/Mac system, you
can start/stop the chat server by executing the `openfire` shell script in the
`openfire/bin` directory. Usage: `./openfire start` or `./openfire stop`

2. Create a `portlet-ext.properties` file and add the following properties and 
settings:

        jabber.enabled=true
        jabber.import.user.enabled=true
        jabber.host=localhost
        jabber.port=5222
        jabber.service.name=<Host Name>
        jabber.resource=Liferay
        jabber.sock5.proxy.enabled=false
        jabber.sock5.proxy.port=-1

    Note that you must change `jabber.service.name` to the *Host Name*. If 
    you're using Openfire, you can find the Host Name by using the Openfire 
    administration web tool. If you didn't set up administrative credentials 
    when you started Openfire, the default credentials are username: admin, 
    password: admin. 

    ![Figure 2: View your server information using the Openfire Administration Web Tool.](../../../images/jabber-service-name.png)

    Additionally, make sure that you set `jabber.enabled` to `true` and add the 
    correct values to `jabber.host` and `jabber.port`. If you installed your 
    chat server on a remote machine or chose to not use the default port, change 
    `jabber.host` and `jabber.port` accordingly. 

3. Package and deploy your Chat app. Remember that this app must be of version 
6.1 or higher. 

#### Integrating Jabber with Single Sign On [](id=integrating-jabber-with-single-sign-on)

If the property `jabber.import.user.enabled` is set to `true`, the Chat app 
automatically imports the user to Jabber after they log in to the Liferay 
instance. Once a user is imported, they can use any Jabber client using the same 
screen name and password they use to log in to their Liferay instance. Their 
buddies are also imported as they come online in the Chat app. 

Note that this is a *lazy import*. Users are imported only after they log in to 
the Liferay instance, and their buddies are added to their list only if they see 
each other within the Chat app. They won't be able to use other Jabber chat
clients until they log in to the Liferay instance. 

If `jabber.import.user.enabled` is set to `false`, users must create their 
Jabber account and add buddies manually. They must create their accounts using 
the same screen name and password they use in their Liferay instance. If they 
don't, the Chat app won't be able to connect to their Jabber account. 

Alternatively, since Openfire integrates with LDAP, if you're using Openfire and 
your Liferay instance is also using LDAP for authentication, you can disable the 
`jabber.import.user.enabled` property. 
