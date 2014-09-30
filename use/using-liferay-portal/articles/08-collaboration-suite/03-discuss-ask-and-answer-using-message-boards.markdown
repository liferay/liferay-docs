# Discuss, Ask, and Answer Using the Message Boards [](id=discuss-ask-and-answer-using-the-message-boards)

Liferay's Message Boards portlet is a state of the art forum application
similar to many forums in which you may have participated. The difference, of
course, is that Liferay's message boards can inherit the abilities of the
Liferay development platform to provide an integrated experience that others
cannot match.

There are countless web sites out there where it is clearly evident that there
is no link whatsoever between the main site and the message boards. In some
cases, users are even required to register twice: once for the main site and
once for the message boards. Sometimes it is three times: for the site, for the
message boards, and for the shopping cart. By providing a message boards
portlet along with all of the other applications, Liferay provides a unique,
integrated approach to building web sites. You can concentrate on building your
site while Liferay does the integration work for you.

The Message Boards portlet offers many configuration options. They are
straightforward to use and are the reason why this portlet is a full-featured
forum application for your web site. To get started, add a Message Boards
portlet to your site. Once it is added, click the *Options* icon in the
portlet's title bar and click *Configuration*. There are two rows of tabs. The
first tab in the top row is titled *Setup*. This is where you can configure the
application the way you want it to behave on your site.

## General 

The first tab beneath *Setup* is labeled *General*. Here, you can enable
anonymous posting, subscribe by default, flags, ratings, and thread as question
by default. You can also choose whether you want the message format to be BBcode
or HTML and choose how long posts display on the message board. Anonymous
posting, subscribe by default, flags, and ratings are selected by default and
the default message format is BBcode.

Enabling *Allow Anonymous Posting* allows users without an account on the system
to post messages to your message boards. Whether or not you you'll want to do
this depends on the type of community you are building. Allowing anonymous
posting opens your site to anyone who might want to spam your forums with
unwanted or off topic advertising messages. For this reason, most of those who
implement message boards turn anonymous posting off by unchecking this box.

Enabling the *Subscribe by Default* option automatically subscribes users to
threads they participate in. Whenever a message in a thread is added or updated,
Liferay sends a notification email to each user who is subscribed to the thread. 

You can set the *Message Format* to either BBCode or HTML. This determines the
markup language of users' actual message board posts. Different WYSIWYG editors
are presented to users depending on which option is enabled. Both editors have a
*Source* button which allows users to view the underlying BBCode or HTML of a
message. Users can compose messages using either the WYSIWYG or Source view and
can switch between views during message composition by clicking on the *Source*
button.

Enabling *Enable Flags* allows your users to flag content which they consider to
be objectionable. If you are allowing anonymous posting, you might use flags in
combination with it if you have someone administering your message boards on a
day-to-day basis. That way, any unwanted messages can be flagged by your
community, and you can review those flagged messages and take whatever action is
necessary. Using flags is also a good practice even if you're not allowing
anonymous posting.

Enabling *Enable Ratings* enables your users to give certain posts a score. This
score is used by Liferay Portal's social activity system to rank your site
members by how helpful their contributions are. You can read more about social
activity later in this chapter and in chapter 9.

Enabling the *Thread as Question by Default* option automatically checks the
mark as question box in the new thread window. Threads marked as questions
display the flag "waiting for an answer." Subsequent replies to the original
message can be marked as an answer.

Lastly, you can set the amount of time a post is displayed until it is taken
away. You have options of 24 hours, 7 days, 30 days, and 365 days. After the
time has passed, the post is removed from the message board.

## Email From 

This tab allows you to configure the name and email address from which message
board email notifications are sent. The default name and email address are those
of the default administrator account: The name is `Test Test` and the email
address is `test@liferay.com`. Make sure to update this email address to a valid
one that can be dedicated to notifications.

## Message Added Email 

This tab allows you to customize the email message that users receive when a
message is added to a topic to which they are subscribed.

**Enabled:** allows you to turn on the automatic emails to subscribed users.
Uncheck the box to disable the message added emails.

**Subject:** lets you choose a prefix to be prepended to the subject
line of the email. This is usually done so that users can set up message
filters to filter the notifications to a specific folder in their email
clients.

**Body:** allows you to write some content that should appear in the body of
the email.

**Signature:** lets you add some content to appear as the signature of the
email.

Below the fields is a section called *Definition of Terms* which defines
certain variables which you can use in the fields above to customize the email
message. Some of these variables are for the message board category name, the
site name, and more.

## Message Updated Email 

The Message Updated Email tab is identical to the Message Added Email tab,
except it defines the email message that users receive whenever a topic is
updated.

## Thread Priorities 

You can define custom priorities for message threads on this tab. These allow
administrators to tag certain threads with certain priorities in order to
highlight them for users. By default, three priorities are defined: Urgent,
Sticky, and Announcement. To define a thread priority, enter its name, a URL to
the image icon that represents it, and a priority number which denotes the
order in which that priority should appear.

There is also a field on this form that allows you to select a localized
language for your priorities. If you need to do this, you can select the
language from the selection box.

## User Ranks 

On this tab, users can be ranked according to the number of messages they have
posted. You can set up custom ranks here. Defaults have been provided for you,
going from zero messages all the way up to one thousand.

In addition to ranks, you can also choose labels for certain users to have
displayed in their profiles as shown by the Message Boards application. These
labels correspond to memberships these users have in your portal. Below are
examples of using the label *Moderator*. The Moderator label in this
configuration is applied for anyone who is a part of any of the Message Boards
Administrator groups: the site role, the organization, the organization role,
the regular role, or the user group. Of course, you probably wouldn't want to
create a role, organization, organization role, site role, and user group all
with the same name in your portal, but you get the idea. 

    Moderator=organization:Message Boards Administrator

    Moderator=organization-role:Message Boards Administrator

    Moderator=regular-role:Message Boards Administrator
    
    Moderator=site-role:Message Boards Administrator

    Moderator=user-group:Message Boards Administrator

As you can see, all you need to do is set the rank, the collection type, and
the name of the type. In the example above, anyone who has a site role, an
organization role, a regular role, or is in a user group called *Message Boards
Administrator*, or anyone who is the organization owner gets the moderator
rank.

As with thread priorities, on this tab you can define whether your ranks are
localized in a particular language.

## RSS 

Message board threads can be published as RSS feeds. This tab allows you to
enable/disale RSS subscriptions and define how the feeds are generated.

**Maximum Items to Display:** lets you select the number of items to display in
the feed.

**Display Style:** lets you select the style. You can publish the full content,
an abstract, or just the title of a thread.

**Format:** allows you to choose the format: RSS 1.0, RSS 2.0, or Atom 1.0.

## Permissions 

The default page that the Message Boards portlet displays has three buttons on
it. Click the one labeled *Permissions*. This allows you to define which roles
have the ability to add a category of threads or to ban abusive users from the
message boards. Select the roles and permissions you want to configure and then
click *Save*.

## Adding Categories 

You are now ready to add categories to your message boards. Click the *Add
Category* button. Enter a name
for the category and a description of the category.

Categories can have different display styles. The available categories must be
set in portal property `message.boards.category.display.styles` and the default
category in `message.boards.category.display.styles.default`. When creating a
new category, you can select the display style you like for that category. By
default, Liferay provides two predefined display styles, although many more can
be easily added:

**Default:** classic display style for general purpose and discussions.

**Question:** designed for discussions in a format of questions and answers.

![Figure 8.17: You have several options when creating a Message Boards Category.](../../images/05-editing-message-board-category.png)

You can add as many categories to your message boards as you wish. As we saw
above, categories can have subcategories. You can add any number of top-level
categories to a message board. You can also edit any category and add
subcategories to an unlimited level. For usability reasons, you don't want to
nest your categories too deep, or your users will have trouble finding them. You
can always add more categories as your message boards grow. Finally, each
category can have any number of threads.

At the bottom of the form for creating or editing a message board category is a
check box for enabling the mailing list function. If don't want to add a mailing
list to the category you're creating, click *Save* now. You can always edit an
existing category to add, edit, or remove a mailing list.

Also, you may merge with a Parent Category by navigating to a category's
*Actions* &rarr; *Move* button. From this window, you can enable the *Merge with
Parent Category* check box and click the *Select* button to choose the parent
category.

Once one or more categories have been added to a message board, they appear in a
list on the message board's home. The list displays the names of the categories
and the numbers of subcategories, threads, and posts in each one. To add a
subcategory to category, click on the category's name in the list and then click
the *Add Subcategory* button. By default, when you click the *Add Subcategory*
button, the form for adding a subcategory is populated with the properties of
the parent category. This includes the parent category's display style (Default
or Question) and mailing list configuration. Of course, you can change the
display style or mailing list configuration of a subcategory just as with a new
category.

Liferay's Message Boards portlet supports two different mechanisms for sending
email notifications: user subscriptions and mailing lists. Let's discuss user
subscriptions first and then mailing lists.

## User Subscriptions and Mailing Lists 

The first mechanism Liferay uses for sending email notifications is user
subscriptions. Users can subscribe to particular categories and threads. Liferay
uses the message board's configured *Email From* address to send email
notifications to subscribed users whenever a new post is created or an existing
post is updated. Liferay can import email replies to message board notifications
directly into the message board. This is a very useful features since it
allows users to interact on the message board via email without needing to log
in to the portal and view the message board page directly. However, this feature
is not enabled by default. To enable this feature, add the following line to
your `portal-ext.properties` file:

    pop.server.notifications.enabled=true

As this property suggests, Liferay's message boards user subscription mechanism
uses the POP mail protocol. When an email reply to a message board notification
is read by Liferay, the reply is posted to the message board and then deleted
from the mail server. Deleting the message from the mail server is the POP
protocol's default behavior and Liferay assumes that your POP mail server
behaves this way. Most POP clients offer an option to leave mail on the mail
server after it's been downloaded but you shouldn't exercise this option. If you
configure mail to be left on the mail server, Liferay will repeatedly send
copies of each retained message along with each new email notification that's
sent to subscribed users.

When enabling message boards to import replies to email notifications, you
should decide whether or not you want to you a mail server subdomain to handle
notifications. By default the following line is set in your portal properties:

    pop.server.subdomain=events

This property creates a special MX (mail exchange) subdomain to receive all
portal-related email (e.g., events.liferay.com). If you don't want to use the
subdomain approach, you can unset this value to tell Liferay to use the *Email
From* address specified in the portlet preferences to receive message board
notification email replies. For example, the *Email From* address could be set
to *replies@liferay.com*.

If you don't want to use a mail server subdomain, add the following line to your
`portal-ext.properties` file:

    pop.server.subdomain=

If you're not using a mail subdomain, Liferay parses the message headers of
emails from the *Email From* address to determine the message board category and
message ID. If you keep the `pop.server.subdomain=events` default, the email
notification address takes the following form:
*mb.[category_id][message_id]@events.liferay.com*. In this case, Liferay parses
the email address to find the category and message ID. Parsing the email address
is safer than parsing message headers since different email clients treat
message headers differently. This is why the `events` subdomain is enabled by
default.

Additionally, you can configure the interval on which the
POPNotificationListener runs. The value is set in one minute increments. The
default setting is to check for new mail every minute, but you can set it to
whatever you like:

    pop.server.notifications.interval=1

The second mechanism Liferay uses for sending email notifications is mailing
lists. Any category in a Liferay message board can have its own mailing list.
Liferay's mailing list mechanism, unlike its user subscription mechanism,
supports both the POP and the IMAP protocols. POP is the default protocol but
each message board's mailing list is configured independently. If you choose the
IMAP protocol for a category's mailing list, make sure to configure the IMAP
inbox to delete messages as they are pulled by the email client that sends
messages to the users on the mailing list. Otherwise, each email message that's
retained on the server will be sent to the mailing list each time there's a new
post or an update in the category.

When a mailing list is enabled for a message board category, Liferay listens to
the specific email inbox that's configured for the mailing list. Enabling the
mailing list function allows users on the mailing list to simply reply to the
notification messages in their email clients. Liferay pulls the messages from
the email inbox it's configured to listen to and automatically copies those
replies to the appropriate message board thread.

With both user subscriptions and mailing lists, users can reply to message board
notification emails and Liferay imports their replies to the message board.
However, with mailing lists, users reply to the mailing list and Liferay listens
to the specific inbox configured for the mailing list and copies messages to the
appropriate message board category. With user subscriptions, by default, email
replies to message board notifications are not imported to the message boards.
This feature has to be enabled in your `portal-ext.properties` file. Once this
feature has been enabled, users can reply to a specific address and have
their replies copied to the message board.

Note: Since any number of sites can use a globally scoped message board,
globally scoped message boards do not support user subscriptions or mailing
lists. Make sure to use a site-scoped or page-scoped message board if you need
user subscriptions or a mailing list with your message board.

To enable the mailing list functionality for a category, you need a dedicated
email address for the category. Once you click the *Active* check box, a number
of other options appear. When a mailing list is activated, Liferay imports
messages it receives from the mailing list to the message board. Liferay looks
for a Liferay user with the sender's email address. If the sender isn't a
Liferay user and the *Allow Anonymous Emails* box is unchecked, the message is
thrown away and not posted to the message board. If the *Allow Anonymous Emails*
box is checked, anyone can send email to the message board category's dedicated
email account and Liferay copies the messages to the message board.

**Email Address:** lets you enter the email address of the account that will
receive the messages.

Next, there are two sections: *Incoming* and *Outgoing*. These define the mail
settings for receiving mail and for sending mail. The Incoming tab has the
following options:

**Protocol:** lets you select POP or IMAP.

**Server Name:** lets you enter the host name of the mail server you are using.

**Server Port:** allows you to specify the port on which your mail service is
running.

**Use a Secure Network Connection:** lets you use an encrypted connection if
your server supports it.

**User Name:** lets you enter the login name on the mail server.

**Password:** lets you enter the password for the account on the server.

**Read Interval (Minutes):** allows you to specify how often Liferay will poll
the server looking for new messages to post to the message board.

The Outgoing section has the following options:

**Email Address:** lets you enter the email address that messages from this
category should come from. If you want your users to be able to reply to the
categories using email, this should be the same address configured on the
*Incoming* tab.

**Use Custom Outgoing Server:** allows you to use a different mail server than
the one that is configured for the portal. If you check this box, more options
appear.

**Server Name:** lets you enter the host name of the SMTP mail server you are
using.

**Server Port:** allows you to specify the port on which your mail service is
running.

**Use a Secure Network Connection:** allows you to use an encrypted connection
if your server supports it.

**User Name:** lets you enter the login name on the mail server.

**Password:** lets you enter the password for the account on the mail server.

When you're finished configuring the mailing list for your category, click
*Save*. 

## Managing User Subscriptions with the Message Boards Subscription Manager 

The Message Boards Subscription Manager is available as an app from Liferay
Marketplace. It allows site administrators to manage the subscriptions of their
site's message board. Without the Message Boards Subscription Manager, users
must manage their own message board subscriptions. The Message Boards
Subscription Manager is available for both Liferay CE and EE--make sure to
select the correct version when downloading and installing the app. Once
installed, the Message Boards Subscription Manager appears in the Configuration
section of Site Administration.

![Figure 8.18: To make sure that the Message Boards Subscription Manager was successfully installed, look for the *Message Boards Subscription Manager* entry in the Configuration section of Site Administration.](../../images/message-boards-subscription-manager-control-panel.png)

The subscription manager allows managing the subscriptions of both the
site-scoped message board and the subscriptions of any page-scoped message
boards that may exist within the site. To access the Message Boards Subscription
Manager, navigate to your site's Site Administration page, select
*Configuration*, and click on *Message Boards Subscription Manager*. You'll see
a list of the categories that have been created within your site's site-scoped
message board. Click *Actions* &rarr; *View* next to a category to view the
category's threads in a new browser tab. Click *Actions* &rarr; *Manage
Subscriptions* to view a list of users which can be subscribed or unsubscribed
from the message board category.

![Figure 8.19: The Message Board Subscription Manager allows site administrators to subscribe or unsubscribe selected users from any category within their site's message board.](../../images/message-boards-subscription-manager.png)

Click on the *Subscribe* or *Unsubscribe* links next to a user's name to
subscribe or unsubscribe the user from the selected message board category.
Alternatively, you can use the checkboxes to select a group of users and use the
*Subscribe* or *Unsubscribe* buttons above the list of users to subscribe or
unsubscribe a number of users at once. The Message Boards Subscription Manager
is easy to use but it's a great tool for site administrators who need to make
sure that certain users receive emails from important message board categories.

## Using the Message Boards 

Upon seeing Liferay's Message Boards portlet, your users will immediately
recognize that the interface is similar to many other implementations they've
seen before. Message boards are nothing new to the Internet, and many people
have been using them for quite a long time. For that reason, Liferay's message
boards will seem very familiar to your users.

Threads can be viewed in many ways. At the top of the portlet is a set of tabs:
*Recent posts*, *My Posts*, *My Subscriptions*, and for administrative users,
*Statistics* and *Banned Users*. The Recent Posts tab shows all posts from all
categories by date, so you can keep up on all the most recent discussions in
the message boards. The My Posts tab shows all of the posts for the user that
is currently logged in. This is a convenient way to get back to a previous
conversation in order to retrieve some pertinent information. The My
Subscriptions tab allows a user to manage thread subscriptions. If you lose
interest in a particular topic, you may want to visit this tab and unsubscribe
from a thread.

For administrators, the Statistics tab shows the number of categories, the
number of posts, and the number of participants in your message boards. It also
has a list of who the top posters to your message boards are. The Banned Users
tab shows all of the users who have been banned from posting on the message
boards.

## Posting New Threads 

To post a new thread simply select the *Post New Thread* button. You will see a
message editing form. The body field on this form is different from that of the
other portlets in Liferay. The reason for this is to support *BBCode*, which is
a standard form of markup used in many message board products. Before BBCode
was invented, many message board products would allow users to enter HTML to
format their messages. This, however, enabled attackers to insert malicious
code into the message board. BBCode was invented to provide users a way of
formatting their messages without allowing them to enter HTML. Similarly,
Liferay supports BBCode in the message boards portlet because the other
editor--which is used for the Content Management System, the Blogs portlet, and
other portlets--produces HTML. This is appropriate for those other portlets, as
they are only used by privileged users, but it is not appropriate for the
message boards. Besides this, many users of message boards are familiar with
BBCode and are used to it, and the editor that is provided for Liferay's
Message Boards portlet makes it very easy to use.

![Figure 8.20: The *Follow the moon* message board post gives you an idea of what it takes to add a message board message.](../../images/05-editing-message-board-post.png)

The message boards editor is quite rich. It supports bold, italicized,
underlined, and crossed-out text, links, images, colors, lists, tables,
alignments, quotation blocks, code blocks, different fonts and font sizes, and
more. There are even a bunch of smiley faces that you can use.

![Figure 8.21: Liferay's dynamic editor even includes a wide range of smiley faces!](../../images/05-emoticons.png)

Users who have Moderator access to the board can modify the priority of
messages. You can also use the editor to quote from messages that you are
replying to, to insert emoticons, to add preformatted text, and more. Messages
that are posted to the message boards are shown by default in a threaded view
so that replies are attached to the proper parent message. This makes it easy
to follow along with conversations.

When viewing a message board thread, users are given several options. At the top
right of the thread are three icons, allowing users to view threads in a
combination view, flat view, or tree view. A combination view shows the threads
at the top as subjects only, with the flat view underneath. A flat view shows
all of the messages in the order in which they are posted. A tree view shows all
of the messages in a threaded view, so that replies are next to the messages
they are replying to.

When viewing a thread, users can click links allowing them to post a new
thread, subscribe to the thread they are viewing, or if they have
administrative access, lock a thread or move a thread to another category.
Subscribing to a thread causes Liferay to send the user an email whenever a new
message is posted to the thread. If you have enabled the mailing list feature
for the category in which the thread resides, users can simply reply to these
messages in order to post back to the thread, without having to visit your
site.

The Message Boards portlet is also highly integrated with Liferay's user
management features. Posts on the message board show users' pictures if they
have uploaded one for themselves, as well as the dates that users created an ID
on your site.

## Message Board Administrative Functions 

The Message Boards portlet provides for the day to day administration of the
message threads. You may wish to separate this function out by a role, and then
delegate that role to one or more of your users. That would free you up to
concentrate on other areas of your web site. To do this, you can create a role
called Message Board Administrator. This role can be scoped by the portal, an
organization, or a site. If you have a portal scoped role, members of this role
will be able to administer any Message Boards portlet in the portal. If it is
an organization or site scoped role, members of this role will be able to
administer a Message Boards portlet in only the organization or site which
assigned the role to them.

Go to the Control Panel and create this role. Once it is created, click *Actions
&rarr; Define Permissions*. Click the *Content* drop-down list. Browse the list
until you find the Message Boards portlet and then click on it. You will then
see a screen which allows you to configure the various permissions on the
portlet.

![Figure 8.22: Defining Permissions for the Message Board Administrators can be done by navigating to *Actions &rarr; Define Permissions*.](../../images/05-defining-permissions-message-board-admin-role.png)

Select the permissions you would like message board administrators to have and
then click *Save*. You can add users to this role and they will inherit the
permissions. Message Board administrators can perform all of the functions we
have already presented, including creating and deleting categories and posting
threads. In addition to these, a number of other functions are available.

### Moving Threads 

Many times a user will post a thread in the wrong category. Administrators may
in this case want to move a thread to the proper category. This is very easy to
do. You can select the *Actions* menu to the right of the thread and choose
*Move*. Or, if you are already viewing the thread and you have
administrative access, there is a link at the top of the thread labeled *Move
Thread*. Click this link. You will be presented with a simple form which allows
you to select a category to which to move the thread and a check box which
allows you to post a message explaining why the thread was moved. This message
will be posted as a reply to the thread you are moving. When finished, click
the *Move Thread* button and the thread will be moved.

### Deleting Threads 

Users with administrative access to the message boards can delete threads.
Sometimes users begin discussing topics that are inappropriate or that reveal
confidential information. In this case, you can simply delete the thread from
the message boards. This is easy to do. First, view the list of threads. Next to
every thread is an *Actions* button. Click *Actions &rarr; Move to the Recycle
Bin* to delete the thread. This does not prevent users from re-posting the
information, so you may need to be vigilant in deleting threads or consider the
next option.

### Banning Users 

Unfortunately, sometimes certain users become abusive. If you wind up with a
user like this, you can certainly make attempts to warn him or her that the
behavior he or she is displaying is unacceptable. If this does not work, you
can ban the user from posting on the message boards.

Again, this is very easy to do. Find any post which was written by the abusive
user. Underneath the user's name/profile picture is a link called *Ban this
User*. Click this link to ban the user from the message boards.

If after taking this action the user apologizes and agrees to stop his or her
abusive behavior, you can choose to reinstate the user. To do this, click the
*Banned Users* tab at the top of the Message Boards portlet. This will show a
list of all banned users. Find the user in the list and select *Unban this
User*.

### Splitting Threads 

Sometimes a thread will go on for a while and the discussion completely changes
into something else. In this case, you can split the thread where the
discussion diverges and create a whole new thread for the new topic.
Administrative users will see a *Split Thread* link on each post. To split the
thread, click the link. You will be brought to a form which allows you to add
an explanation post to the split thread. Click *OK* to split the thread.

### Editing Posts 

Administrative users can edit anyone's posts, not just their own. Sometimes
users will post links to copyrighted material or unsuitable pictures. You can
edit these posts, which allows you to redact information that should not be
posted or to censor profanity that is not allowed on your message boards.

### Permissions 

Permissions can be set not only on threads, but also on individual posts. You
can choose to limit a particular conversation or a post to only a select group
of people. To do this, click the *Permissions* link on the post and then select
among the *Delete, Permissions, Subscribe, Update, and View* permissions for
the particular role to which you want to grant particular access. This function
can be used, for example, to allow some privileged users to post on a certain
thread, while others are only allowed to view it. Other combinations of the
above permissions are also possible. Next, let's discuss Liferay's Wiki
portlet.
