# Using Real-Time Chat

Liferay's Chat app provides a convenient way for users to send each other 
instant messages when they are logged into your Liferay instance. It appears as 
a bar at the bottom of every page, showing who is logged on, their statuses, and 
any chats the logged-in user has open. 

![Figure 1: Liferay's Chat app lets you update your status and notifications through the *Settings* menu.](../../../images/chat-settings.png)

You can use Liferay Marketplace to install the Chat app. Go to Marketplace by 
navigating to Control Panel &rarr; *Apps* &rarr; *Store*. You can learn more 
about Marketplace and how to purchase/download an app in 
[the article on using Marketplace](/discover/portal/-/knowledge_base/7-0/leveraging-the-liferay-marketplace). 

The Chat app is very simple to use. To change its settings, click its *Settings* 
button at the lower-right corner next to *Online Friends*. Here you can set your 
status, whether to show that you're online, whether to play a sound if you 
receive a message while the window or tab is in the background, and whether to 
enable desktop notifications for new messages. The Chat app also displays your 
online friends in the *Online Friends* tab. Click this tab and then click on a 
friend's name to open a chat window. You can have multiple chats open at a time. 
You can minimize any or all chat windows. 

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
