#  Creating Forums with Message Boards [](id=creating-forums-with-message-boards)

Liferay's Message Boards app is a state of the art forum application similar to
many forums in which you may have participated. The difference, of course, is
that Liferay's message boards can inherit the abilities of the Liferay
development platform to provide an integrated experience that others cannot
match. 

There are countless web sites out there where it is clearly evident that there
is no link whatsoever between the main site and the message boards. In some
cases, users are even required to register twice: once for the main site and
once for the message boards. Sometimes it is three times: for the site, for the
message boards, and for the shopping cart. By providing a message boards
application along with all of the other applications, Liferay provides a unique,
integrated approach to building sites. You can concentrate on building your site
while Liferay does the integration work for you. 

Message Boards offers many configuration options. They are straightforward to
use and are the reason why it's a full-featured forum application for your site.
Let's create a message board. 

## Creating Message Boards [](id=creating-message-boards)

You can create a message board instance for a Liferay instance, a site, or page.
An instance's message board can be used on any of its pages. A message board can
be scoped to a site, including a regular site, an organization's site, or a
user's profile site or dashboard site. Message Boards in Site Administration
lets you administer all of a message board's options. Each Liferay instance and
site has a message board instance built-in. If you want to work with a message
board scoped to a page, you must create the page scope from a Message Boards
application on that page. 

To scope a message board to a page, first add the Message Boards application to
the page. Then click the app's *Options* icon
(![Options](../../../images/icon-app-options.png)), select *Configuration*, and select the
*Scope* tab, to list the available scopes. Select the scope with page's name, or
*PageName (Create New)*, if the scope hasn't been created already. Your
message board is now scoped to the page and available to work with in Site
Administration. 

To administer a message board, open the *Menu*
(![Menu](../../../images/icon-menu.png)) and navigate to the *Content* section
for your site, page, or global scope. The Message Boards administration screen
appears. 

+$$$

**Note**: To administer a message board for a particular page scope, click the
*Compass* icon (![Compass](../../../images/icon-compass.png)) to list the available
scopes, and select the scope with the page's name. Finally, select *Message
Boards* from under the *Content* heading.

$$$

To administer a message board, open the *Product Menu* (![Product
Menu](../../../images/icon-menu.png)) and navigate the *Content* section for your
site, page, or global scope. Click on *Message Boards* and the Message Boards
administration screen appears. 

![Figure 1: A Message Board instance starts empty, ready for you to configure for your purposes.](../../../images/message-boards-administration.png)

Initially, a message board has no categories or threads; and that's good,
because you need to configure it for your purposes. To open the message board's
configuration screen, click the message board's *Options* icon
(![Options](../../../images/icon-options.png)) and select *Configuration*. From the
Configuration tabs, you can configure the message board for the behavior you
want. 

### General Setup [](id=general-setup)

From the *General* configuration tab, you can enable anonymous posting,
subscribe by default, flags, ratings, and thread as question by default. You can
also choose whether to use BBCode or HTML as your message format. You can also
choose how long posts display on the message board. Anonymous posting, subscribe
by default, flags, and ratings are selected by default and the default message
format is BBCode. 

Enabling *Allow Anonymous Posting* allows guest users to post messages to your
message board. Whether or not you should do this depends on the type of
community you are building. Allowing anonymous posting opens your site to anyone
who might want to spam your forums with unwanted or off topic advertising
messages. For this reason, most message boards administrators turn anonymous
posting off by unchecking this box. 

Enabling the *Subscribe by Default* option automatically subscribes users to
threads they participate in. Whenever a message in a thread is added or updated,
Liferay sends a notification email to all users subscribed to the thread. 

You can set the *Message Format* to either BBCode or HTML. This determines the
markup language of users' actual message board posts. The type of WYSIWYG editor
presented to users depends on which option is enabled. Both editors have a
*Source* button which lets users view a message's underlying BBCode or HTML.
Users can compose messages using either the WYSIWYG or Source view and can
switch between views during message composition by clicking on the *Source*
button. 

Enabling *Enable Flags* lets users flag content which they consider to be
objectionable. If you are allowing anonymous posting, you might use flags in
combination with it if you have someone administering your message boards on a
daily basis. That way, any unwanted messages can be flagged by your community,
and you can review those flagged messages and take whatever action is necessary.
Using flags is also a good practice even if you're not allowing anonymous
posting. 

Enabling *Enable Ratings* allows users to score posts. The scores are used by
Liferay's social activity system to rank site members by how helpful their
contributions are. You can read more about social activity in the [Measuring
Social Activity](/discover/portal/-/knowledge_base/7-0/measuring-social-activity)
article. 

Enabling the *Thread as Question by Default* option automatically checks the
mark as question box in the new thread window. Threads marked as questions
display the flag "waiting for an answer." Subsequent replies to the original
message can be marked as an answer. 

Lastly, you can set the amount of time a post is displayed on the message
board's *Recent Posts* tab until it is taken away. You have options of 24 hours,
7 days, 30 days, and 365 days. After the time has passed, the post is removed
from *Recent Posts*. However, the post itself isn't deleted. It remains
accessible everywhere else in the message board. 

### Email Setup [](id=email-setup)

Message Boards provides options for configuring the email sender and specifying
the mail message to use when someone posts a new message board thread or updates
a thread. 

**Email From:** This tab allows you to configure the name and email address from
which message board email notifications are sent. The default name and email
address are those of the default administrator account (e.g., `Test Test` and
the email address is `test@liferay.com`) you configured in the Basic
Configuration Wizard. Make sure to update this email address to a valid one that
can be dedicated to notifications. You can determine whether to support HTML in
your email messages. Lastly, you can expand the *Definition of Terms* section to
see variables available to use in your emails. 

**Message Added Email:** This tab allows you to customize the email message that
users receive when a message is added to a topic to which they are subscribed. 

-   **Enabled:** allows you to turn on the automatic emails to subscribed users.
    Uncheck the box to disable the message added emails. 

-   **Subject:** lets you choose a prefix to be prepended to the subject line of
    the email. This is usually done so that users can set up message filters to
    filter the notifications to a specific folder in their email clients. 

-   **Body:** allows you to write some content that should appear in the body
    of the email. 

-   **Signature:** lets you add some content to appear as the signature of the
    email. 

-   **Definition of Terms:** This section defines certain variables which you
    can use in the fields above to customize the email message. Some of these
    variables are for the message board category name, the site name, and more. 

**Message Updated Email:** The Message Updated Email tab is identical to the
Message Added Email tab, except it defines the email message that users receive
whenever a message is updated. Next, you can specify priority classifications
for message threads. 

### Thread Priorities [](id=thread-priorities)

You can define custom priorities for message threads on this tab. This allows
privileged roles to tag a thread with a certain priority in order to highlight it
for users. By default, three priorities are defined: Urgent, Sticky, and
Announcement. To define a thread priority, enter its name, a URL to the image
icon that represents it, and a priority number. Threads with a higher priority
are posted above threads with a lower priority. 

The localized language field lets you name the priorities in each locale. You
can select the locale, update the priority names for it, and save your updates. 

### User Ranks [](id=user-ranks)

On this tab, users can be ranked according to the number of messages they have
posted. You can set up custom ranks here. Defaults have been provided for you,
going from zero messages all the way up to one thousand. 

In addition to ranks, you can also choose labels for certain users to have
displayed in their profiles as shown by the Message Boards application. The
labels correspond to memberships the users have in Liferay. Below are examples
of using the label *Moderator*. The Moderator label in this configuration is
applied for anyone who is a part of any of the Message Boards Administrator
groups: the site role, the organization, the organization role, the regular
role, or the user group. Of course, you probably wouldn't want to create a role,
organization, organization role, site role, and user group all with the same
name in your Virtual Instance, but you get the idea. 

    Moderator=organization:Message Boards Administrator

    Moderator=organization-role:Message Boards Administrator

    Moderator=regular-role:Message Boards Administrator
    
    Moderator=site-role:Message Boards Administrator

    Moderator=user-group:Message Boards Administrator

As you can see, all you need to do is set the rank, the collection type, and the
name of the type. In the example above, anyone who has a site role, an
organization role, a regular role, or is in a user group called *Message Boards
Administrator*, or anyone who is the organization owner gets the Moderator rank.

As with thread priority names, the *Localized Language* field lets you localize
rank names. RSS feed configuration is next. 

### RSS [](id=rss)

Message board threads can be published as RSS feeds. This tab allows you to
enable/disable RSS subscriptions and define how the feeds are generated. 

**Maximum Items to Display:** lets you select the number of items to display in
the feed. 

**Display Style:** lets you select the style. You can publish the full content,
an abstract, or just the title of a thread. 

**Format:** allows you to choose the format: RSS 1.0, RSS 2.0, or Atom 1.0. 

Once you've finished configuring your message board, make sure to *Save* your
changes. 

From Message Boards in Site Administration, you can configure the message
board's permissions. 

### Message Board Permissions [](id=permissions)

To edit message board permissions, click the *Permissions* button. The
permissions screen allows you to grant and revoke a role's ability to access
parts of the application and perform particular actions. 

The permissions enable a role to perform the following actions. 

**Lock Thread:** Stop any further additions or modifications to a thread's
messages. 

**Subscribe:** Enlist yourself to receive notifications on new and modified
posts. 

**Reply to Message:** Respond to an existing message. 

**Add File:** The user can attach a file to his message. 

**Permissions:** View and modify permissions. 

**Add Message:** Post a new thread. 

**View:** View all the contents of message threads. 

**Add Category:** Add a new category to the message board. 

**Update Thread Priority:** Modify a thread's priority. 

**Ban User:** Forbid a user from participating in the message board. 

**Move Thread:** Move a thread to a different category or subcategory. 

Configure the roles with the permissions you want and *Save* your changes. 

### Export / Import

You can also import existing wiki content into your wiki application instance or
export wiki content to a file. To access this interface, click on *Options
&rarr; Export / Import*. Refer to [Importing/Exporting Pages and Content](/discover/portal/-/knowledge_base/7-0/importing-exporting-pages-and-content)
for details. 

### App Templates

The Message Boards application lets you store your current application setup or
apply an existing archived setup to this application instance. App templates are
especially useful for applying to multiple application instances. The
application templates interface is available by clicking on *Options &rarr; App
Templates.*

Now that you've configured your message board, you can focus on adding
categories for message board threads. 

## Adding Categories [](id=adding-categories)

You are now ready to add categories to your message boards. Click the *Add* icon
(![Add](../../../images/icon-add.png)) and select *Category* button. Enter a name for the
category and a description of the category. 

Categories can have different display styles. The available categories must be
set in [portal property](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Message%20Boards%20Portlet)
`message.boards.category.display.styles` and the default category in
`message.boards.category.display.styles.default`. When creating a new category,
you can select the display style you like. By default, Liferay provides two
predefined display styles, although many more can be easily added:

**Default:** classic display style for general purpose and discussions. 

**Question:** designed for discussions in a format of questions and answers. 

![Figure 2: You have several options to create a message board category for your needs.](../../../images/message-boards-add-category.png)

The Permissions section provides a selector for quickly choosing who can view
the category and a link to a table that presents the category's other permission
options. The *Viewable by* selector lets you choose from the following values to
determine who can view the category: Anyone (Guest Role), Site Members, or
Owner. 

To show more permission options, click *More Options*. A table appears with the
rest of the category's permissions: 

**Lock Thread:** Stop any further additions or modifications to a thread's
messages. 

**Add Subcategory:** Add a new category within this category. 

**Update:** Edit the category. 

**Subscribe:** Enlist yourself to receive notifications on new and modified
posts. 

**Reply to Message:** Respond to existing messages. 

**Add File:** Attach a file to any of your messages. 

**Permissions:** View and modify permissions. 

**Delete:** Remove the category. 

**Add Message:** Post a new thread. 

**Update Thread Priority:** Modify a thread's priority. 

**Move Thread:** Move a thread to a different category or subcategory. 

After you've created a category, you can revisit its permission options by
clicking the category's *Actions* icon
(![Actions](../../../images/icon-actions.png)) and selecting *Permissions*. 

The form also lets you enable the mailing list function. If don't want to add a
mailing list to the category you're creating, you can save your changes now. You
can always edit an existing category to add, edit, or remove a mailing list. The
next section explains user subscriptions and mailing lists. 

You can add as many categories to your message boards as you wish. And
categories can have subcategories. You can add any number of top-level
categories to a message board. You can also edit a category and add
subcategories to an unlimited level. For usability reasons, you don't want to
nest categories too deep, or your users will have trouble finding them. You can
always add more categories as your message boards grow. Finally, each category
can have any number of threads. 

Also, you may move a category or merge a category's threads with a different
Parent Category. To bring up the the Move screen, select the category's *Move*
option. You can select a different Parent Category, optionally select the *Merge
with Parent Category* check box, and click *Move*. 

As you add categories to a message board, they're listed on the message board's
home. The list displays the names of the categories and the numbers of
subcategories, threads, and posts in each one. To add a subcategory to category,
click on the category's name in the list and then click the *Add* icon and
select *Subcategory*. The form for adding a subcategory appears and is
populated, by default, with the properties of the parent category. This includes
the parent category's display style (Default or Question) and mailing list
configuration. Of course, you can change the display style or mailing list
configuration of a subcategory just as with any category. The *Add Subcategory*
form and the *Add Category* form are the same. 

![Figure 3: Categories help you organize threads so users can find topical threads that interest them.](../../../images/message-boards-home.png)

Liferay's Message Boards supports two different mechanisms for sending email
notifications: user subscriptions and mailing lists. Let's discuss user
subscriptions first and then mailing lists. 

## User Subscriptions and Mailing Lists [](id=user-subscriptions-and-mailing-lists)

The first mechanism Liferay uses for sending email notifications is user
subscriptions. Users can subscribe to particular categories and threads. Liferay
uses the message board's configured *Email From* address to send email
notifications to subscribed users, whenever a new post is created or an existing
post is updated. Liferay can import email replies to message board notifications
directly into the message board. This is a very useful feature since it allows
users to interact on the message board via email, without needing to log in to
Liferay and view the message board page directly. This feature is, however,
disabled by default. To enable it, add the following line to your
`portal-ext.properties` file: 

    pop.server.notifications.enabled=true

As this property suggests, Liferay's message boards user subscription mechanism
uses the POP mail protocol. When an email reply to a message board notification
is read by Liferay, the reply is posted to the message board and then deleted
from the mail server. Deleting the message from the mail server is the POP
protocol's default behavior and Liferay assumes that your POP mail server
behaves this way. Most POP clients offer an option to leave mail on the mail
server after it's been downloaded, but you shouldn't exercise this option. If
you configure mail to be left on the mail server, Liferay will repeatedly send
copies of each retained message along with each new email notification that's
sent to subscribed users. 

When enabling message boards to import replies to email notifications, you
should decide whether or not you want to use a mail server subdomain to handle
notifications. By default the following property setting is specified in the
portal properties: 

    pop.server.subdomain=events

This property creates a special MX (mail exchange) subdomain to receive all
virtual instance related email (e.g., events.liferay.com). If you don't want to
use the subdomain approach, you can unset this value to tell Liferay to use the
*Email From* address, specified in the Message Board's configuration, to receive
message board notification email replies. For example, the *Email From* address
could be set to *replies\@liferay.com*. 

If you don't want to use a mail server subdomain, add the following line to your
`portal-ext.properties` file: 

    pop.server.subdomain=

If you're not using a mail subdomain, Liferay parses the message headers of
emails from the *Email From* address to determine the message board category and
message ID. If you keep the `pop.server.subdomain=events` default, the email
notification address takes the following form:
*mb.[category_id][message_id]@events.liferay.com*. In this case, Liferay parses
the email address to find the category and message ID. Parsing the email address
is safer than parsing message headers, since different email clients treat
message headers differently. This is why the `events` subdomain is enabled by
default. 

Additionally, you can configure the interval on which the
`POPNotificationListener` runs. The value is set in one minute increments. The
default setting is to check for new mail every minute, but you can set it to
whatever you like: 

    pop.server.notifications.interval=1

The second mechanism Liferay uses for sending email notifications is mailing
lists. Any category in a Liferay message board can have its own mailing list.
Liferay's mailing list mechanism, unlike its user subscription mechanism,
supports both the POP and the IMAP protocols. POP is the default protocol, but
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
feature has been enabled, users can reply to a specific address and have their
replies copied to the message board. 

Note: Since any number of sites can use a globally scoped message board,
globally scoped message boards do not support user subscriptions or mailing
lists. Make sure to use a site-scoped or page-scoped message board if you need
user subscriptions or a mailing list with your message board. 

To enable the mailing list functionality for a category, you need a dedicated
email address for the category. Then, click on your category's *Edit* option.
Once you select the *Active* check box, a number of other options appear. When a
mailing list is activated, Liferay imports messages it receives from the mailing
list to the message board. Liferay looks for a Liferay user with the sender's
email address. If the sender isn't a Liferay user and the *Allow Anonymous
Emails* box is unchecked, the message is thrown away and not posted to the
message board. If the *Allow Anonymous Emails* box is checked, anyone can send
email to the message board category's dedicated email account and Liferay copies
the messages to the message board. 

**Email Address:** lets you enter the email address of the account that will
receive the messages. 

Next, there are two sections: *Incoming* and *Outgoing*. These define the mail
settings for receiving mail and for sending mail. The Incoming section has the
following options: 

**Protocol:** lets you select POP or IMAP. 

**Server Name:** lets you enter the host name of the mail server you are using.

**Server Port:** allows you to specify the port on which your mail service is
running. 

**Use a Secure Network Connection:** lets you use an encrypted connection if
your server supports it. 

**User Name:** lets you enter the login name on the mail server. 

**Password:** lets you enter the password for the account on the server. 

**Read Interval (Minutes):** allows you to specify how often Liferay polls the
server looking for new messages to post to the message board. 

The Outgoing section has the following options: 

**Email Address:** lets you enter the email address that messages from this
category should come from. If you want your users to be able to reply to the
categories using email, this should be the same address configured on the
*Incoming* section. 

**Use Custom Outgoing Server:** allows you to use a different mail server than
the one that is configured for the Liferay instance. If you check this box, more
options appear: 

-   **Server Name:** lets you enter the host name of the SMTP mail server you are
    using. 

-   **Server Port:** allows you to specify the port on which your mail service is
    running. 

-   **Use a Secure Network Connection:** allows you to use an encrypted
    connection if your server supports it. 

-   **User Name:** lets you enter the login name on the mail server. 

-   **Password:** lets you enter the password for the account on the mail
    server. 

When you're finished configuring the mailing list for your category, click
*Save*. 

Now that you've created message boards and message board categories, you can
explore posting messages to them and interacting with other user's threads. 

## Using the Message Boards [](id=using-the-message-boards)

You can add the Message Boards application to a page from the *Add*
(![Add](../../../images/icon-control-menu-add.png)) menu's *Collaboration*
section. Users will immediately recognize that the interface is similar to many
other implementations they've seen before. Message boards are nothing new to the
Internet, and many people have been using them for quite a long time. In any
case, it can't hurt to explore how to use Liferay Message Boards and discover
all of its features. 

![Figure 4: The Message Boards application lets you explore its categories, interact with message threads, and post new messages.](../../../images/message-boards-category-threads.png)

Threads can be viewed in many ways. At the top of the application is a set of
tabs: *Message Boards Home*, *Recent posts*, *My Posts*, *My Subscriptions*, and
for administrative users, *Statistics* and *Banned Users*. The Recent Posts tab
shows all posts from all categories by date, so you can keep up on all the most
recent discussions in the message boards. The My Posts tab shows all of the
user's posts. It provides a convenient interface for revisiting previous
conversations in order to retrieve pertinent information. The My Subscriptions
tab allows the user to manage thread subscriptions. If you lose interest in a
particular topic, you may want to visit this tab and unsubscribe from a category
or thread. 

For administrators, the Statistics tab shows the number of categories, the
number of posts, and the number of participants in your message boards. It also
has a list of your message board's top contributors. The Banned Users tab shows
all of the users who have been banned from posting on the message boards. 

### Posting New Threads [](id=posting-new-threads)

To post a new thread click the *Post New Thread* button in the app or
![Add](../../../images/icon-add.png) in Message Boards in Site Administration)
and select *Thread*. A message editing form appears. The body field on this form
is different from that of the other Liferay applications. The reason for this is
to support *BBCode*, which is a standard form of markup used in many message
board products. Before BBCode was invented, many message board products would
allow users to enter HTML to format their messages. This, however, enabled
attackers to insert malicious code into the message board. BBCode was invented
to provide users a way of formatting their messages without allowing them to
enter HTML. Similarly, Liferay supports BBCode in the message boards because the
other editors--which are used for the Content Management System, Blogs, and
other applications--produce HTML. This is appropriate for those other apps, as
they are only used by privileged users, but it is not appropriate for the
message boards. Besides this, many users of message boards are familiar with
BBCode and are used to it, and the editor that is provided for Liferay's Message
Boards application makes it very easy to use. 

Message Boards uses a rich-text editor. It supports bold, italicized,
underlined, and crossed-out text, links, images, colors, lists, tables,
alignments, quotation blocks, code blocks, different fonts and font sizes, and
more. The editor has keyboard shortcuts Ctrl+b for bold, Ctrl+i for italics, and
Ctrl+u for underline. You can [mention](/discover/portal/-/knowledge_base/7-0/creating-forums-with-message-boards#mentioning-other-users)
another other user by entering the "@" character followed by the user's user
name. There are even a bunch of smiley faces that you can use. 

After entering the message's *Subject*, enter your message *Body*. Below the
Body are sections that let you attach files (e.g., images) to your message,
categorize it, tag it, and relate it to other assets. expand the *More Settings*
section in the list of sections below the content editor. The user can specify
thread options that the message board permits. The options include marking the
message as a question, posting anonymously, subscribing to the message thread,
assigning the message a pre-defined priority, and allowing pingbacks to the
message. 

The user can also specify permissions. The message can be set to be viewable by
a particular role. And the following additional permissions can be set on the
message: 

**Update:** Edit the message. 

**Subscribe:** Receive notifications of updates to the message and its thread. 

**Permissions:** Grant/revoke permissions for the message. 

**Delete:** Remove the message. 

**View:** View the message. 

A permissions icon appears above each posted message. You can revisit the above
permissions by clicking the permissions icon. 

When you're done editing and configuring your message, you can preview it, and
save it as a draft or publish it. Once it's published, it's listed along with
the other threads in the category. 

### Participating in Message Board Threads [](id=participating-in-message-board-threads)

To find message board threads that interest you, browse a message board's
categories or Recent Posts. You can view a category's thread listing by clicking
on the category's name. From within a category screen, you can subscribe to an
RSS feed and/or emails that inform you about thread activities in that category.
Similar to a category's screen, Recent Posts lists threads too, except they're the
latest threads across all the categories. 

To view a message thread, click on it. Messages are shown by default in a
threaded view so that replies are attached to the proper parent message. This
makes it easy to follow along with conversations. Icons
(![Thread Views](../../../images/message-boards-thread-view-icons.png)) at the top
of the thread allow users to view threads in Combination View, Flat View, or
Tree View. A Flat View shows all of the messages in the order in which they are
posted. A Tree View indents indents reply messages with respect to the message
they're replying to. The Combination View shows a textual tree representation of
the thread messages using subjects only at the top, with the messages shown flat
underneath.

Message Boards is also highly integrated with Liferay's user management
features. Posts on a message board show a user's profile picture as well as the
date the user joined the site. 

![Figure 5: Combination View shows a textual representation of the thread tree above the messages, which are displayed flat.](../../../images/message-boards-participate-in-threads.png)

Subscribing to a thread causes Liferay to send the user an email whenever a new
message is posted to the thread. If you have enabled the mailing list feature
for the category in which the thread resides, users can simply reply to these
messages in order to post back to the thread, without having to visit your site.

Most threads get more interesting as users reply to them. You can start creating
a response by clicking one of the following buttons: *Reply*, *Reply with
Quote*, or *Quick Reply*. The *Reply* screen includes the same rich-text editor
and options that you use for posting new messages. If you want to include text
from the original message, click *Reply with Quote*. If you only need the
rich-text editor to generate your reponse and don't need any other message
options, click *Quick Reply*. The editor lets you insert emoticons, preformatted
text, and more. 

In addition to replying to a message, you can rate it or flag it as
objectionable. A message board moderator can evaluate flagged messages and
decide how to handle the messages and their authors. This provides an
appropriate segue into the next topic: managing message boards. 

## Managing Message Boards [](id=managing-message-boards)

Message boards are powerful, but can become unweildy if left unmanaged. The
Message Boards in Site Administration facilitates day to day thread
administration. You may wish to separate this function out by a role, and then
delegate that role to one or more users. That would free you up to concentrate
on other areas of your site. To do this, you can, for example, create a role
called *Message Board Administrator*. This role can be scoped to the Liferay
instance, an organization, or a site. If you create an instance-scoped role,
members of this role will be able to administer Message Boards throughout
Liferay. If it is an organization or site-scoped role, members of this role will
be able to administer a Message Boards application in only that organization or
site. 

You can create such a role from the Control Panel. To define the role's
permissions, click its *Define Permissions* action and navigate to *Site
Administration &rarr; Content &rarr; Message Boards*. A screen appears that
allows you to configure the various Message Boards permissions. 

Select the permissions you would like message board administrators to have and
save them. You can add users to this role. Message board administrators can
perform all the message board functions already presented, including creating
and deleting categories and posting threads. In addition to these, a number of
other functions are available. 

### Locking Threads [](id=locking-threads)

You may encounter threads that you think should be preserved, but stoppped. You
can halt activity on a thread by clicking *Lock Thread*. 

### Moving Threads [](id=moving-threads)

Many times a user will post a thread in the wrong category. Administrators may
in this case want to move a thread to the proper category. This is very easy to
do. You can select the *Actions* menu
(![Actions](../../../images/icon-actions.png)) to the right of the thread and
choose *Move*. Or, if you are already viewing the thread and you have
administrative access, there is a link at the top of the thread labeled *Move
Thread*. Click this link. You will be presented with a simple form which allows
you to select a category to which to move the thread and a check box which
allows you to post a message explaining why the thread was moved. This message
will be posted as a reply to the thread you are moving. When finished, click the
*Move Thread* button and the thread will be moved.

### Deleting Threads [](id=deleting-threads)

Users with administrative access to the message boards can delete threads.
Sometimes users begin discussing topics that are inappropriate or that reveal
confidential information. In this case, you can simply delete the thread from
the message boards. This is easy to do. First, view the list of threads. Click
*Actions* button and select *Move to the Recycle Bin* to delete the thread. This
does not prevent users from re-posting the information, so you may need to be
vigilant in deleting threads or consider the next option. 

### Banning Users [](id=banning-users)

Unfortunately, sometimes certain users become abusive. If you wind up with a
user like this, you can certainly make attempts to warn him or her that the
behavior he or she is displaying is unacceptable. If this does not work, you can
ban the user from posting on the message boards. 

Again, this is very easy to do. Find any post which was written by the abusive
user. Underneath the user's name/profile picture is a link called *Ban this
User*. Click this link to ban the user from the message boards. 

If after taking this action the user apologizes and agrees to stop his or her
abusive behavior, you can choose to reinstate the user. To do this, click the
*Banned Users* tab at the top of the message board. This will show a list of all
banned users. Find the user in the list and select *Unban this User*. 

### Splitting Threads [](id=splitting-threads)

Sometimes a thread will go on for a while and the discussion completely changes
into something else. In this case, you can split the thread where the discussion
diverges and create a whole new thread for the new topic. Administrative users
will see a *Split Thread* link on each post. To split the thread, click the
link. You will be brought to a form which allows you to add an explanation post
to the split thread. Click *OK* to split the thread. 

### Editing Posts [](id=editing-posts)

Administrative users can edit anyone's posts, not just their own. Sometimes
users will post links to copyrighted material or unsuitable pictures. You can
edit these posts, which allows you to redact information that should not be
posted or to censor profanity that is not allowed on your message boards. You
can also update the thread's priority or mark a reply as an answer to a thread's
question. 

### Permissions [](id=permissions-0)

Permissions can be set not only on threads, but also on individual posts. You
can choose to limit a particular conversation or a post to only a select group
of people. To do this, click the *Permissions* link on the post and then select
among the *Delete, Permissions, Subscribe, Update, and View* permissions for the
particular role to which you want to grant particular access. This function can
be used, for example, to allow some privileged users to post on a certain
thread, while others are only allowed to view it. Other combinations of the
above permissions are also possible. 

That wraps up message board management basics. Next, let's learn how users can
*mention* other users in their content and in comments and thread replies. 

## Mentioning Other Users

Have you ever wanted to include another user in a discussion on the Message
Boards? Have you ever wanted to give kudos to a colleague in content you're
writing? With the Mentions feature, you can [*mention*](/participate/liferaypedia/-/wiki/Main/Mentions)
(notify and/or draw attention to) other users, by entering the "@" character in
front of each user's user name. 

When you mention a user, the user receives a site notification next to the
user's profile icon and an email, alerting the user with a link to the content.
You can mention users in a blog entry, a message boards thread or thread reply,
or comments in any app that supports comments. What's more, a mention in app
content links to the user's home page, so readers can find out more about the
mentioned user. 

![Figure x: As you enter a user name after an "@" character, Mentions displays links to users that match the text you enter. Select the user you want to mention and publish your content.](../../../images/mentions-at-mention-menu.png)
                                                                     
The Mentions feature works as you'd expect. While editing comments or content,
enter an "@" character followed by the *user name* of the user you're mentioning
(e.g., *@joe.bloggs*). A selector appears, listing users that match the name
you're entering. In the selector, each user is represented by his/her profile
picture, name, and user name. Click on the user you want to mention and finish
editing your content.

+$$$

**Known Limitation**: In comments, homepage hyperlinks aren't displayed for
mentioned users. Refer to issue
[LPS-56020](https://issues.liferay.com/browse/LPS-56020) for details. 

$$$

On publishing the content, mentioned users receive a
notification next to their profile picture and an email, informing them that
they've been mentioned. The notification and email indicate the author's name
and content type, and it links to the content. 

![Figure x: Liferay's Notifications feature alerts users with the number of notifications (including mentions) waiting for them to read.](../../../images/mentions-count-near-profile-image.png)

![Figure x: When you click on the notifications number next to your profile picture, your Notifications List appears.](../../../images/mentions-notification-list.png)

Here's an example notification email message:

	Dear Joe Bloggs,

	Neil Armstrong mentioned you in the following comment:

	----------
	I bet @joe would like visiting the moon.
	----------

	View comment.(link)

	Sincerely,
	Kim Admin
	kim.admin@lunarresort.com 

The Mentions app is bundled with Liferay CE and is part of the Collaboration
Suite available on the [Marketplace](https://www.liferay.com/marketplace).
Mentions is enabled globally by default, but you can enable/disable it globally
or per site. For a site to use Mentions, it must be enabled for the site's
Virtual Instance. To access the global Mentions settings for your
Virtual Instance, open the *Menu* (![Menu](../../../images/icon-menu.png)), then
navigate to *Control Panel &rarr; Configuration &rarr; Instance Settings*, click
on the *Social* tab, and expand the *Mentions* section. 

![Figure x: From Instance Settings in the Control Panel, you can enable or disable the Mentions feature for all of the Virtual Instance's sites.](../../../images/mentions-global-instance-setting.png)

All users are allowed to mention fellow site members and friends, by default. To
fine tune these options, select the *Define Mentions Capability for Users*
option and specify the settings you want. 

Mentions must be enabled for a site's Virtual Instance in order for Mentions to
be available for the site. As a site administrator, you can enable or disable
Mentions for your site. A site's Mentions app configuration is accessible from
within the *Menu* (![Menu](../../../images/icon-menu.png)). Once in the menu,
navigate to *Site Name &rarr; Configuration &rarr; Site Settings*, click on the
*Social* tab, and expand the *Mentions* section. This section lets you enable or
disable Mentions for the site. 

As we've demonstrated, you can mention users to get their attention, praise them
with kudos, and link to their home pages. It helps you welcome specific users to
collaborate with you. Have fun collaborating and socializing with Mentions! 

## Summary [](id=summary)

As you've discovered, Liferay's Message Boards provides full-featured forums for
users to ask questions, discuss topics, and share small amounts of information.
Message boards help to build strong site communities. But as your site's
community gathers more information on topics or procedures relevant to users,
you'll want to provide a means for them to collaborate on that information.
Liferay's Wiki is a terrific application for users to jointly produce
hierarchies of information. 
