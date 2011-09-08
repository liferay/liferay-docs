# Liferay Collaboration Suite

Liferay Portal ships with a robust suite of collaboration applications
which you can use to build communities of users for your site. These
applications are best-of-breed applications with all the features you
would expect of standalone versions that are outside a portal. The
difference with Liferay's collaboration suite, however, is that all of
the applications share a common look and feel, security model, and
architecture. And they inherit all of the strengths of being a part of
the Liferay development platform, so you can use them in combination
with Liferay's user management and Content Management features to build
a well-integrated, feature-rich web site.

This chapter will focus on the use of Liferay's collaboration suite. You
will learn how to set up and administer:

-   Blogs

-   Calendars

-   Chat

-   Mail

-   Message Boards

-   Wikis

You will see how all of these features can work together to provide an
enhanced experience for your users, as well as giving you the tools to
help build a community that keeps coming back.

## Scopes

As we learned earlier, roles can be scoped by the portal, by a
community, or by an organization. This means that the role only takes
effect for the scope in which it resides. For example, a Message Boards
Administrator role with complete access to the Message Boards portlet
would have different permissions based on the role's scope. If it's a
portal role, members have permission to administer message boards across
the portal. If it's a community role, members have permission to
administer message boards only within the community in which they are
members of the role. If it's an organization role, members have
permission to administer message boards only within the organization in
which they are members of the role.

We also use the word *scope* to refer to the data set of a portlet. When
a portlet is added to a page in a community or organization, it is
*scoped* for that community or organization. This means that its data
belongs to that community or organization. If the portlet is added to a
page in a different community or organization, it will have a completely
different data set. This is how the Message Boards portlet can be placed
in one community and have one set of categories and threads, and then be
placed in a different community and contain a completely different set
of categories and threads.

One limitation of this is that by default, you can have only one Message
Boards portlet per community or organization. If you have one Message
Boards portlet on one page in a community and you add the portlet to
another page in the same community, the second Message Boards portlet
will contain exactly the same data as the first, and this is because of
its scope. It is scoped for that community. Many of Liferay's portlets
work this way.

To get around this, some Liferay portlets can have scopes that go beyond
just the specific community or organization upon which the portlet has
been placed. You can now scope them by page. If you set the scope of a
portlet to be the page instead of the whole community or organization,
you can add any number of these portlets to different pages, and they
will have different sets of data. This allows you to have more than one
message board per community or organization if you wish. All portlets,
however, default to the “native” configuration, and have their scopes
set to the community or organization where they are placed.

Unless otherwise noted, all of the portlets in this chapter support
scoping by either the page or the community / organization to which they
belong. This gives you much more flexibility in how you want to set up
your portal. By default, however, the scope remains the same as it
always has, and is set to be by the community or organization. If you
want to change the scope, it only takes a few simple steps.

1.  Click the *Menu* icon in the portlet window (the wrench).

2.  Select *Configuration*.

3.  Select the *Scope* tab.

4.  Modify the Scope to be the current page.

5.  Click *Save*.

![image](../../images/portal-admin-ch5_html_2c05ad6b.jpg)*Illustration 1: Changing
the scope for a portlet *

That's all it takes to change the scope for a particular portlet
instance. By setting the scope to *page*, you can add as many of these
portlets to a particular community or organization as you want, provided
they all are added to different pages.

## Archived Setups

Another useful feature in Liferay's portlets is Archived Setups. This
means that once you configure a portlet, you can save those settings in
an “archive” that the portal will save for you. If someone goes in and
changes the settings of a particular portlet, it then becomes easy to
revert those changes back to the original, archived configuration.

To create an archived setup, click the *Configuration* option from the
menu in the portlet's title bar. If the current settings of the portlet
you are configuring are the ones you want to archive, click the
*Archived* tab. If not, change and save the settings until you have the
portlet configured the way you want it, and then click the *Archived*
tab.

There is only one field to fill out: a name for your archive. Create a
name for your archive and click *Save*. You will now see your archive in
the list. If for whatever reason you need to revert the portlet to these
archived settings, you can click *Actions → Restore* next to the
archived setup you want to restore.

Unless otherwise noted, all of the portlets in this chapter support this
feature. This is particularly useful for portlets that have a lot of
configuration options, such as the Message Boards portlet.

## Permissions

All of Liferay's portlets support Liferay's robust, fine-grained
permissions system. Some higher level permissions can be configured
here: whether a role can add the portlet to a page, can configure the
portlet, or whether the role can view the portlet. To set these
permissions, go to the *Configuration* menu and click on *Permissions*.
This will show you a table of roles defined in the portal. Select which
roles are allowed to see the portlet and which roles are allowed to
configure the portlet, and then click *Submit*.

## Sharing

The web was once thought of as a number of islands of applications in a
vast universe of “cyberspace.” Everybody tried to make their island the
biggest. Some succeeded and some failed. More recently, the concept of
the web as an application itself has taken hold, and so widgets have
become very popular nowadays. This concept is part of the “Web 2.0”
concept and is very much enabled by widgets. So what is a widget? A
widget is a small piece of code which provides a piece of functionality,
can be included on any web site, but does not necessarily have to be
hosted by that web site. If you have ever embedded a YouTube video on
your own web site so that users could watch a video without actually
having to visit [http://youtube.com](http://youtube.com/), then you have
already used a widget.

Liferay supports serving its portlets as widgets. You can embed a
particular instance of a portlet running on your site into another site,
such as Facebook. This opens up a whole new avenue of exposure to your
web site that you would not otherwise have had. In fact, this is how all
those Facebook games work.

To share one of your portlets as a widget, go to the *Configuration*
option in the menu in the portlet's title bar. Then click the *Sharing*
tab. There are five subtabs under sharing: Any Web Site, Facebook,
Google Gadget, Netvibes, and Friends.

### Any Web Site

Copy and paste the snippet of code into the web site to which you want
to add the portlet as a widget. That's all you need to do. When a user
loads the page on the other web site, the code will pull the relevant
portlet from your site and display it.

### Facebook

You can add any Liferay portlet as an application on Facebook. To do
this, you must first get a developer key. A link for doing this is
provided to you in the Facebook tab. You will have to create the
application on Facebook and get the key and canvas page URL from
Facebook. Once you have done this, you can copy and paste their values
into the Facebook tab. Your portlet will now be available on Facebook as
a Facebook application.

![image](../../images/portal-admin-ch5_html_m435d3961.png)
*Illustration 2: Liferay's forums in Facebook*

### Google Gadget

iGoogle is service provided by Google that lets users create a
customizable page and add *Gadgets* to that page. Liferay can serve up
portlets to be used as Google Gadgets on an iGoogle page.

Check the box labeled *Allow users to add [portlet-name] to iGoogle*.
Copy and paste the URL provided into Google's *Add a feed or gadget*
feature on the iGoogle configuration page, and Liferay will serve that
portlet directly onto your iGoogle page. The URL provided is unique to
the specific instance of the portlet, so you could serve multiple
instances of the same portlet as different Google Gadgets.

This feature could be useful to allow users to view what's happening on
your portal at a glance, with asset publishers or custom RSS feeds. You
could also use Liferay's API to build your own portlet and provide the
URL for users to place on their iGoogle pages.

### Netvibes

Netvibes offers a similar service to iGoogle – users can log in, create
their own personal portal, called a *dashboard,* and add customizable
widgets to the dashboard that they create. To set up Netvibes support
for a particular portlet, check the *Allow users to add [portlet-name]
to Netvibes pages* box. You can then use the provided URL to create a
custom Netvibes widget based on the instance of the portlet that you're
using.

### Friends

The final sub-tab in the *Sharing* tab is called *Friends*. This tab has
a single check box that allows you to give your friends permission to
add the application as a widget to another web site. This could be
particularly useful for your blog or your calendar if you wish to share
them.

## Blogs

The word *Blog* is an apostrophe-less contraction of the two words *web
log*. Blogs were first popularized by web sites such as Slashdot
([http://slashdot.org](http://slashdot.org/)) which have the format of a
running list of entries to which users could attach comments. Over time,
more and more sites such as Digg, del.icio.us, and Newsvine adopted the
format, empowering users to share their opinions and generating lively
discussions.

Over the course of time, blogging sites and applications began to
appear, such as blogger.com, blogspot.com. TypePad, WordPress, and Web
Roller. These applications allow *individuals* to run their own web
sites in the same format: a running list of short articles to which
readers who are registered with the site can attach threaded comments.
People who run a blog are called *bloggers*, and sometimes they build a
whole community of readers who are interested in their blog posts.
Additionally, there are several famous people who run their own blogs.
It gives people an outlet for self-expression which they would not
otherwise have, and the ubiquity and wide reach of the Internet ensures
that if you have something important and interesting to say, somebody
will read it.

![image](../../images/portal-admin-ch5_html_6847c073.jpg)*Illustration 3: Slashdot,
one of the first blogs on the Internet*

Liferay Portal has a portlet called the Blogs portlet which allows you
to provide a blogging service to users of your web site. In fact,
Liferay extensively uses the Blogs portlet on
[http://www.liferay.com](http://www.liferay.com/) to provide employees
with blogs of their own. In addition to the Blogs portlet, there is also
a** Blogs Aggregator** portlet which can take entries from multiple
users' blogs and put them all in one larger list. We will go over how to
use both of these portlets to create a blogging site for your users.
### The Blogs Portlet

![image](../../images/portal-admin-ch5_html_m5350d888.jpg)*Illustration 4: Initial
view of the Blogs portlet*

The Blogs portlet is available from the *Collaboration* section of the
*Add More* menu. You will notice that it is an Instanceable
portlet, meaning that it can only be added once to any community or
organization. This allows you to use the Blogs portlet to create a
shared blog to build a site like Slashdot or to create multiple personal
blogs to build a site like [http://blogger.com](http://blogger.com/).
What's the difference? Adding the Blogs portlet to a Community or
Organization page creates a shared blog for members of the Community or
Organization. Adding the Blogs portlet to a user's personal space
creates a blog for just that user. Either way, the Blogs portlet works
the same. And of course, you can change the Blog portlet's scope to have
different blogs on different pages in the same community.
By default, the Blogs portlet will display the latest entry in its
entirety. Since we have just added the portlet to a page, we have no
entries, so the portlet will be empty. Before we start adding entries,
we'll configure the portlet so that it displays entries according to our
specifications.

#### Configuring the Blogs Portlet

The Blogs portlet is easy to configure. Click on the *Menu* icon in the
portlet's title bar and select *Configuration*. Beneath the Setup tab,
you will see another row of options.

**Email From: **Selecting the *Email From tab* allows you to define the
*From:* field in the email messages that users receive from the Blogs
portlet.

**Entry Added Email: **This option allows you to enter a subject and
body of the emails sent out when a new Blog entry has been added.

**Entry Updated Email:** This option allows you to enter a subject and
body of the emails sent out when a new Blog entry has been updated.

**Display Settings: ** Here, you can configure the various display
options for the Blogs portlet.

![image](../../images/portal-admin-ch5_html_m1aaf3fd4.png)*Illustration 5: Blogs
Configuration*

The *Display Settings* tab lets you configure the formatting of the
Blogs portlet. You can choose the most optimal ways to display your
entries as well as configure how you want your users to interact with
you as you post those entries.

**Maximum Items to Display:** This allows you to choose the total number
of blog entries to display on the initial page. You can choose up to 100
to be displayed.

**Display Style:** Choose between Full Content, the Abstract, or just
the Title. Setting this to Abstract causes Liferay to display only the
first 30 words of your blog entries, with a Read More link at the bottom
of each to allow users to read the whole entry if they wish.

**Enable Flags: **Allow users to flag content as inappropriate and send
an email to the administrators.

**Enable Ratings:** Allows you to enable your users to rate your blog
entries from one to five stars.

**Enable Comments:** Allows readers to comment on your blog entries.

**Enable Comment Ratings:** Allows readers to rate the comments which
are posted to your blog entries.

Click on the *RSS* tab to configure the way in which blogs will display
to RSS readers. Here, you can choose how you want your blog entries to
be published to feed readers and outside web sites.

**Maximum Items to Display:** Allows you to determine how many blog
entries will be displayed at once. The default is set to twenty.

**Display Style**: You can select to display the full content,
abstracts, or title of your blogs.

**Format: **You can choose which format you want to deliver your blogs:
RSS 1.0, RSS 2.0, or Atom 1.0.

#### Permissions

Depending on whether this is a personal blog or a shared blog, you may
want to modify the permissions on the blog. By default, the permissions
are set up for a personal blog, so only the owner of the community to
which the portlet has been added will be able to add entries. If you
want to share a blog with multiple users, it is easy to do.

First, create a role for your bloggers and add them to the role. Next,
click the *Permissions* button on the Blogs portlet. You will now see a
list of both portal and Community / Organization roles, and currently
only the owner is checked. Check off any other role or team that should
have the ability to add blog entries, and then click *Save*.

#### Adding Blog Entries

Now you're ready to begin adding blog entries. Click the *Add Blog Entry* button. You will see the following data entry screen:

![image](../../images/portal-admin-ch5_html_m7930fe68.jpg)*Illustration 6: Adding a
Blog entry*

There isn't much difference between this screen and any other data entry
screen within Liferay Portal. You get a title, a way of scheduling when
the entry is to appear, and a rich editor that allows you to format your
entry the way you want, complete with embedded images, videos, and the
like.
Note also that as you type, the entry is automatically saved as a draft
at periodic intervals. This gives you peace of mind in using the portlet
from within your browser, since you won't lose your entry in the event
of a browser crash or network interruption. You can also tag your
entries using the same tagging mechanism found everywhere else in the
portal.

The Blogs portlet also supports trackbacks and pingbacks. Trackbacks are
special links that let you or another site know if you or if someone
else linked to a blog entry. For example, if you wanted to write an
entry in your blog and reference someone else's entry, you might put the
URL to the other entry in the **Trackbacks to Send** field. Similarly,
if you want others who link to your blog to let you know about the link
via trackbacks, leave the **Allow Trackbacks** box checked. This will
generate a URL that is displayed with your blog entry. Others who want
to link to your entry can use this URL for the link, and every time the
link is clicked on, your Liferay-powered site will know about it and
will be able to keep track of the clicks.

Note that trackbacks only work when the protocol is supported by both
the linker and the linkee. For this reason, the Blogs portlet also
supports pingbacks. Pingbacks are XML-RPC requests that are similar to
trackbacks except they are automatically sent when you link to another
site. So if you link to another site in your blog entry, Liferay will
send a pingback to that site to notify that site that you linked to it.
Similarly, if someone links to your blog entry, Liferay can receive a
pingback from that person's site and record the link.

Once you have finished your blog entry, click *Publish*. You'll go back
to the list of entries, and now your entry is displayed. Here is what it
looks like when the display style is set to *Abstract* and the number of
entries is set to 10:

![image](../../images/portal-admin-ch5_html_m27a1d62c.jpg)*Illustration 7: First blog
entry added*

You can see that in the summary view, you don't see the trackback /
pingback link, and you only see the number of comments which have been
added. If you were to click the *Read More* link, you would see the
entirety of the article, all of the comments in a threaded view, and the
trackback / pingback link which others can use to link back to your blog
entry.
Additionally, the full view of a blog contains convenient links to add
blog entries to popular aggregating sites such as Digg, del.icio.us, and
others. This gives your readers an easy way to submit your blog entries
to these aggregators, potentially driving further traffic to your site.

As you can see, the Blogs portlet is a full-featured blogging
application which gives you and your users the ability to enter the
blogosphere with an application that supports anything a blogger needs.

### Aggregating Blog Entries

You can set up a whole web site devoted just to blogging if you wish.
The Blogs Aggregator portlet allows you to publish entries from multiple
bloggers on one page, giving further visibility to blog entries. This
portlet is also very easy and straightforward to set up. You can add it
to a page from the Collaboration category in the *Add More* menu
in the dock.

If you click *Configuration* from the menu button in the title bar of
the portlet, you will see the Blogs Aggregator's configuration page.
From here, you can set several configuration options.

![image](../../images/portal-admin-ch5_html_bbaa84e.jpg)*Illustration 8: Blogs
Aggregator Configuration*

**Selection Method:** You can select Users or Scope here. If you select
Users, the Blogs Aggregator will aggregate the entries of every blogger
on your system. If you want to refine the aggregation, you can select an
Organization by which to filter the users. If you select Scope, the
Blogs Aggregator will contain only entries of users who are in the
current scope. This will, in essence, limit the entries to members of
the Community or Organization upon which the Blogs Aggregator portlet
resides.
**Organization:** Allows you to determine which organization's blogs you
want to aggregate.

**Display Style:** Just like the Blogs portlet, you can select from
several different styles for displaying blog entries.

**Maximum Items to Display:** Select maximum number of entries the
portlet will display.

**Enable RSS Subscription:** The aggregated entries can themselves be an
RSS feed. Leave this box selected if you want people to be able to
subscribe to your aggregated blog entries.

**Show Tags:** This option will display all the tags associated with the
blogs.

When you have finished setting the options in the portlet, click *Save*.
Then click *Return to Full Page*.

As you will see, the Blogs Aggregator looks very much like the Blogs
portlet, except that the entries come from more than one author.

## Calendar

Liferay's Calendar portlet is a complete calendaring solution. You can
schedule any number of events of different types, receive alarms via
email or text message, import and export your calendar, and much more.
Additionally, you can import and export the calendar to the popular
iCalendar format for use in other applications.

In a similar way to the Blogs portlet, you can use the Calendar portlet
as a shared calendar on a community or organization's web site, or you
can use the Calendar portlet as a personal calendar -- or both.

![image](../../images/portal-admin-ch5_html_m227004cd.jpg)*Illustration 9: The
Liferay Calendar Portlet*

Configuring the Calendar Portlet
Go to the *Configuration* option from the menu in the portlet's title
bar. You have three tabs there which allow you to configure three
different options: **Email From**, **Event Reminder Email,** and
**Display Settings**.

#### Email From

In this tab, you can set the **Name** and **Email** **Address** that
will display in system generated reminder emails. The address that you
enter in the Email Address field must be properly formatted, but it does
not need to be an address that actually exists. This can work well in
conjunction with mail rules in your email client that can operate on
these messages. By default, the name is set to Joe Bloggs and the email
address is set to test@liferay.com.

#### Event Reminder Email

This tab lets you customize the email message that you receive from the
portlet when you have an event for which you have configured a reminder.
It contains the same rich text editor that you see everywhere else in
Liferay, and this allows you to format your message so that you will be
able to easily recognize it. Additionally, there are several variables
which allow you to insert runtime values into the message, and these are
listed underneath the text editor so that you can use them in the
appropriate place in your template. For example, you might want the
event start date and time and the event title included in the email
reminder that you receive. Inserting the variables that correspond with
those values into your template will allow you to do that.

#### Display Settings

Display Settings for the calendar allows you to define which tab in the
calendar is the default when the portlet is first displayed. By default,
the summary tab is displayed, but you may want the daily, weekly, or
monthly view to be the default.

There are additional settings for the summary tab: you can select
whether it has a horizontal or vertical layout, select whether it shows
a mini month, select whether it shows today's events, or select to
enable comments.

### Using the Calendar Portlet

The Calendar portlet generally works the way you would expect a calendar
to work. It inherits its interface from the rest of Liferay's portlet
library, so you should find common tasks in the same place that you find
them in other Liferay portlets.

To get started, you may want to click the *Permissions* button. Here you
can find a list of roles with check boxes denoting whether the role has
the *Add Event* or the *Export All Events* permission. By default, only
the owner has permissions to do these things, which means that, by
default, the Calendar portlet is set up to be used in one's personal
space. Out of the box, Liferay defaults to putting a Calendar portlet on
all users' private pages, so this is expected. If you are going to be
using the Calendar as a shared calendar, you may want to modify some
things here.

First, create a portal, community, or organization role and add the
users to whom you wish to grant access to the calendar to the role. Then
come back to the Calendar portlet and click the *Permissions* button.
Check the boxes next to the role(s) that should have access to one or
both of the functions (*Add Event* or *Export All Events*). Then click
*Submit*.

Now you are ready to begin using your calendar. Click the *Add Event*
button. You will see a form that allows you to fill out all the
information for your event.

**Start Date/Time:** The date and time the event starts.

**Duration:** How long the event will last.

**All Day Event:** Check this box to disassociate time from the event
and make it last all day.

**Time Zone Sensitive:** Leave this box checked to make sure that the
portal keeps track of the event regardless of time zone.

**Title:** The title of the event.

**Description:** A description of the event.

**Type:** Select from a number of pre-configured event types. You can
change these in the `portal-`{.western}`ext.properties`{.western} file.

**Permissions:** Determine who can view and edit the event.

**Repeat:** If the event repeats on a schedule, select the schedule
(daily, weekly, monthly. etc.)

**End Date:** If the event repeats on a schedule but there is an end to
the set of meetings, enter the end date.

**Reminders:** Select whether to send a reminder, how long before the
event to send it, and through what medium (email, SMS text message, or
instant message) to send it. Note that this feature is integrated with
your profile on the portal, so you will need to fill out your mobile
phone number and / or instant messenger IDs in order to use those
features.

When you have finished adding your event, click *Save*.

You can view calendar events by day, week, month, year, or in a simple
list.

## Chat

Liferay's Chat portlet gives you a convenient way of allowing your users
to send each other instant messages when they are logged into your web
site. It appears as a bar at the bottom of every page, showing who is
logged on, their statuses, and any chats the logged-in user has had
open.

![image](../../images/portal-admin-ch5_html_6ede77e.jpg)*Illustration 10: Liferay's
Chat Portlet*

The Chat portlet is very simple to use. To change the settings, click
*Settings *(found near the lower right corner next to *Online Friends)*.
Here you can set your status, whether to show if you are online, and
whether to play a sound if someone sends you a message while you have
the window or tab in the background.

The portlet shows you the number of your friends who are online. To chat
with one of them, click the *Online Friends* link and then click the
friend's name. You can then begin chatting with him or her. You can have
multiple chats open at a time, and even have one or more of them
minimized.

The Chat portlet is distributed with the Liferay bundles, but is not
included as part of the .war distribution, as it is a separate plugin.
If you installed the Liferay .war manually on your application server,
you can install the Chat portlet by going to the Control Panel, clicking
*Plugins Installation*, and then clicking the *Install More Portlets*
button. Find the Chat portlet in the list, click on it, and then click
*Install*.

## Mail

Liferay's Mail portlet enables your users to interact with their email
using an easy to use, ubiquitous web interface. If your mail system
supports the IMAP protocol, you can use the Mail portlet to integrate
your users' mail with the rest of your web site. You can also connect
the Mail portlet to a mail account provided by Google.

The Mail portlet is distributed with the Liferay bundles, but is not
included as part of the .war distribution, as it is a separate plugin.
If you installed the Liferay .war manually on your application server,
you can install the Mail portlet by going to the Control Panel, clicking
*Plugins Installation*, and then clicking the *Install More Portlets*
button. Find the *Mail* portlet in the list, click on it, and then click
*Install*.

![image](../../images/portal-admin-ch5_html_4795fd88.png)
*Illustration 11: Liferay Mail portlet*

To connect the Mail portlet with an email account, click the *Add a New
Email Account* link. From there, you are given a choice between a Custom
email Account or a Gmail Account. Choose the option that you wish, and
fill out the form that appears.

For a Gmail account, all you need to do is provide your email address
and your password, and the portlet will take care of the rest.

For a Custom Mail Account, the following fields are necessary:

**Address:** The email address which receives mail for this account.

**Login:** The user name for logging into the account.

**Password:** The password for logging into the account.

**Incoming Settings:** The host name for your IMAP (Internet Mail Access
Protocol) or POP server.

**Incoming Port:** The port upon which the IMAP or POP service is
running.

**Use Secure Incoming Connection:** Check this box to use an encrypted
connection to the server, if your server supports it.

**Outgoing SMTP Server:** The host name of your SMTP (Simple Mail
Transfer Protocol) server.

**Outgoing Port:** The port upon which the SMTP service is running.

**Use Secure Outgoing Connection:** Check this box to use an encrypted
connection to the server, if your server supports it.

When finished, click *Save*. Your new email account now appears as a tab
at the top of the page along with the button for adding a mail account.
In this way, you can add as many mail accounts as you want in order to
view them in the portlet.

If you click the tab for the mail account you just configured, you will
be brought to an interface which allows you to read your mail and
compose new messages. To read a message, click on it. To compose a new
message, click the *Compose Email* link on the left side of the portlet.
You will be brought to a form which allows you to compose an email
message using the same rich text editor that appears everywhere else in
Liferay. You can read, reply, and create messages, as well as manage all
of your folders in Liferay's Mail portlet.

The Mail portlet is a great way to integrate a familiar service with
other collaboration features that Liferay provides.

## Message Boards

Liferay's message boards are one of the most widely used applications
provided by the product. The application is a state of the art forum
application similar to many forums in which you may have participated.
The difference, of course, is that Liferay's message boards can inherit
the abilities of the Liferay development platform to provide an
integrated experience that others cannot match.

There are countless web sites out there where it is clearly evident that
there is no link whatsoever between the main site and the message
boards. In some cases, users are even required to register twice: once
for the main site and once for the message boards. Sometimes it is three
times: for the site, for the message boards, and for the shopping cart.
By providing a message boards portlet along with all of the other
applications, Liferay can provide a unique, integrated approach to
building your web site. All of the integration work is done for you, and
you can concentrate on building out the site that you want to build.

The Message Boards portlet has a lot of configuration options, but they
are straightforward to use and are the reason why this portlet is a
full-featured forum application for your web site. To get started, add a
Message Boards portlet to your site. Once it is added, click the *Menu*
icon in the portlet's title bar and click *Configuration*. There are two
rows of tabs. The first tab in the top row is titled simply, *Setup*.
This is where you can configure the application the way you want it to
behave on your site.

### General

The first tab beneath *Setup* is labeled *General*. Here, you can enable
anonymous posting, flags and ratings. All three options are selected by
default.

Anonymous posting lets those without an account on the system post
messages to your message boards. You may or may not want to do this,
depending on the type of community you are building. Allowing anonymous
posting opens your site to anyone who might want to spam your forums
with unwanted or off topic advertising messages. For this reason, most
of those who implement message boards turn anonymous posting off by
unchecking this box.

Flags allow your users to flag content which they consider to be
objectionable. If you are allowing anonymous posting, you might use
flags in combination with it if you have someone administering your
message boards on a day-to-day basis. That way, any unwanted messages
can be flagged by your community, and you can review those flagged
messages and take whatever action is necessary. Using flags is also a
good practice even if you're not allowing anonymous posting.

Ratings enable your users to give certain posts a score. This score is
used by Liferay Portal's social equity system to rank your community
members by how helpful their contributions are. You can read more about
social equity later in this chapter.

### Email From

This tab allows you to configure the email address that messages from
the Message Boards portlet come from. By default, the name is Joe Bloggs
and the email address is [test@liferay.com](mailto:test@liferay.com).

### Message Added Email

This tab allows you to customize the email message that users receive
when a message is added to a topic to which they are subscribed.

**Enabled:** Uncheck the box to disable the message added email.

**Subject Prefix:** Enter a prefix that will be prepended to the subject
line of the email. This is usually done so that users can set up message
filters to filter the notifications to a specific folder in their email
clients.

**Body:** Enter anything here that should appear in the body of the
email.

**Signature:** Enter anything here that should appear as part of the
signature of the email.

Below the fields is a section called **Definition of Terms** which
defines certain variables which you can use in the fields above to
customize the email message. Some of these variables are for the message
board category name, the community name, and more.

### Message Updated Email

The Message Updated Email tab is identical to the Message Added Email
tab, except it defines the email message that users receive whenever a
topic is updated.

### Thread Priorities

You can define custom priorities for message threads. These allow
administrators to tag certain threads with certain priorities in order
to highlight them for users. By default, three priorities are defined:
Urgent, Sticky, and Announcement. To define a thread priority, enter its
name, a URL to the image icon that represents it, and a priority number
which denotes the order in which that priority should appear.

There is also a field on this form that allows you to select a localized
language for your priorities. If you need to do this, you can select the
language from the selection box.

### User Ranks

Users can be ranked according to the number of messages they have
posted. You can set up custom ranks here. Defaults have been provided
for you, going from zero messages all the way up to 1000.

In addition to ranks, you can also select who is a “moderator” by what
roles are held. Defaults are there for you which show you how to do
this.

Moderator=community-role:Message Boards Administrator

Moderator=organization:Message Boards Administrator

Moderator=organization-role:Message Boards Administrator

Moderator=regular-role:Message Boards Administrator

Moderator=user-group:Message Boards Administrator

As you can see, all you need to do is set the rank, the collection type,
and the name of the type. In the example above, anyone who has a
Community Role, an Organization Role, a regular Role, or is in a user
group called *Message Boards Administrator*, or anyone who is the
Organization Owner gets the Moderator rank.

As with thread priorities, on this tab you can define whether your ranks
are localized in a particular language.

### RSS

Message board threads can be published as RSS feeds. This tab allows you
to define how the feeds are generated.

**Maximum Items to Display:** Select the number of items to display in
the feed.

**Display Style:** Select the style. You can publish the full content,
an abstract, or just the title of a thread.

**Format:** Choose the format among RSS 1.0, RSS 2.0, or Atom 1.0
formats.

### Permissions

The default page that the Message Boards portlet displays has three
buttons on it. Click the one labeled *Permissions*. This allows you to
define which roles have the ability to add a category of threads or to
ban abusive users from the message boards. Select the roles and
permissions you want to configure and then click *Submit*.

### Adding Categories and Mailing Lists

You are now ready to add categories to your message boards. Click the
*Add Category* button. You may merge with a Parent Category by enabling
the Merge with Parent Category check box and clicking the *Select*
button. Enter a name for the category and a description of the category.
At the bottom of the form is a check box which allows you to enable the
mailing list function.

The mailing list function works in concert with the message notification
emails. If a user subscribes to a message board category, he or she will
get emails when someone posts messages to that category. Enabling the
mailing list function allows those users to simply reply to the
notification messages in their email clients, and those replies will be
posted to the thread automatically.

To enable this functionality, you will need a mail account for the
category. Once you click the check box, a number of other options will
appear.

**Email Address:** Enter the email address of the account that will
receive the messages.

Next, there are two sections: *Incoming* and *Outgoing*. These define
the mail settings for receiving mail and for sending mail. The Incoming
tab has the following options:

**Protocol:* ***Select POP or IMAP.

**Server Name: **Enter the host name of the mail server you are using.

**Server Port:** Enter the port on which your mail service is running.

**Use a Secure Network Connection:** Check this box to use an encrypted
connection if your server supports it.

**User Name:** The login name on the mail server.

**Password:** The password for the account on the server.

**Read Interval (Minutes):** Liferay will poll the server at this
interval looking for new messages to post.

The Outgoing section has the following options:

**Email Address:** Enter the email address that messages from this
category should come from. If you want your users to be able to reply to
the categories using email, this should be the same address configured
on the *Incoming* tab.

**Use Custom Outgoing Server:** If you need to use a different mail
server than the one that is configured for the portal, check this box.
If you check the box, a number of other options will appear.

**Server Name:** Enter the host name of the SMTP mail server you are
using.

**Server Port:** Enter the port on which your mail service is running.

**Use a Secure Network Connection:** Check this box to use an encrypted
connection if your server supports it.

**User Name:** Enter the login name on the mail server.

**Password:** Enter the password for the account on the mail server.

When finished adding your category, click *Save*. Add as many categories
to your message boards as you wish.

Note that categories can have subcategories. You can add a number of
top-level categories and then click on each one and add categories under
that, to an unlimited level. For usability reasons, you don't want to
nest your categories too deep, or your users will have trouble finding
them. You can always add more categories as your message boards grow.

### Using the Message Boards

Upon seeing Liferay's Message Boards portlet, your users will
immediately recognize that the interface is similar to many other
implementations they've seen before. Message boards are nothing new to
the Internet, and many people have been using them for quite a long
time. For that reason, Liferay's message boards will seem very familiar
to your users.

Threads can be viewed in many ways. At the top of the portlet is a set
of tabs: *Recent posts, My Posts, My Subscriptions, *and for
administrative users, *Statistics *and *Banned Users*. The Recent Posts
tab shows all posts from all categories by date, so you can keep up on
all the most recent discussions in the message boards. The My Posts tab
shows all of the posts for the user that is currently logged in. This is
a convenient way to get back to a previous conversation in order to
retrieve some pertinent information. The My Subscriptions tab allows a
user to manage thread subscriptions. If you lose interest in a
particular topic, you may want to visit this tab and unsubscribe from a
thread.

For administrators, the Statistics tab shows the number of categories,
the number of posts, and the number of participants in your message
boards. It also has a list of who the top posters to your message boards
are. The Banned Users tab shows all of the users who have been banned
from posting on the message boards.

### Posting New Threads

To post a new thread simply select the *Post New Thread* button. You
will see a message editing form. The body field on this form is
different from that of the other portlets in Liferay. The reason for
this is to support *BBCode*, which is a standard form of markup used in
many message board products. Before BBCode was invented, many message
board products would allow users to enter HTML to format their messages.
This, however, enabled attackers to insert malicious code into the
message board. BBCode was invented to provide users a way of formatting
their messages without allowing them to enter HTML. Similarly, Liferay
supports BBCode in the message boards portlet because the other
editor—which is used for the Content Management System, the Blogs
portlet, and other portlets—produces HTML. This is appropriate for those
other portlets, as they are only used by privileged users, but it is not
appropriate for the message boards. Besides this, many users of message
boards are familiar with BBCode and are used to it, and the editor that
is provided for Liferay's Message Boards portlet makes it very easy to
use.

![image](../../images/portal-admin-ch5_html_2b49b027.jpg)
*Illustration 12: Editing a message boards post. You can see the emoticons that are available in the editor.*

Users who have Moderator access to the board can modify the priority of
messages. You can also use the editor to quote from messages that you
are replying to, to insert emoticons, to add preformatted text, and
more.

Messages that are posted to the message boards are shown by default in a
threaded view so that replies are attached to the proper parent message.
This makes it easy to follow along with conversations.

When viewing a message board thread, users are given several options. At
the top right of the thread are three icons, allowing users to view
threads in a flat view, in a tree view, or in a combination view. A flat
view shows all of the messages in the order in which they are posted. A
tree view shows all of the messages in a threaded view, so that replies
are next to the messages they are replying to. A combination view shows
the threads at the top as subjects only, with the flat view underneath.

When viewing a thread, users can click links allowing them to post a new
thread, subscribe to the thread they are viewing, or if they have
administrative access, lock a thread or move a thread to another
category. Subscribing to a thread causes Liferay to send the user an
email whenever a new message is posted to the thread. If you have
enabled the mailing list feature for the category in which the thread
resides, users can simply reply to these messages in order to post back
to the thread, without having to visit your site.

The Message Boards portlet is also highly integrated with Liferay's user
management features. Posts on the message board show users' pictures if
they have uploaded one for themselves, as well as the dates that users
created an ID on your site.

### Message Board Administrative Functions

The Message Boards portlet provides for the day to day administration of
the message threads. You may wish to separate this function out by a
role, and then delegate that role to one or more of your users. That
would free you up to concentrate on other areas of your web site. To do
this, you can create a role called Message Board Administrator. This
role can be scoped by the portal, an organization, or a community. If
you have a portal scoped role, members of this role will be able to
administer any Message Boards portlet in the portal. If it is a
community or organization scoped role, members of this role will be able
to administer a Message Boards portlet in only the community or
organization in which they have the role.

Go to the Control Panel and create this role. Once it is created, click
*Actions -> Define Permissions*. Click the *Portlet Permissions* button.
Browse the list until you find the Message Boards portlet and then click
on it. You will then see a screen which allows you to configure the
various permissions on the portlet.

![image](../../images/portal-admin-ch5_html_m1dfc555f.png)
*Illustration 13: Configuring Message Board Administrators role*

Grant the permissions you wish message board administrators to have and
then click *Save*. You can then add users to this role and they will
inherit the permissions.

Message Board administrators can perform all of the functions we have
already presented, including creating and deleting categories and
posting threads. In addition to these, a number of other functions are
available.

#### Moving Threads

Many times a user will post a thread in the wrong category.
Administrators may in this case want to move a thread to the proper
category. This is very easy to do. You can select the *Action* menu to
the right of the thread and then select *Move Thread*. Or, if you are
already viewing the thread and you have administrative access, there is
a link at the top of the thread labeled *Move Thread*. Click this link.
You will be presented with a simple form which allows you to select a
category to which to move the thread and a check box which allows you to
post a message explaining why the thread was moved. This message will be
posted as a reply to the thread you are moving. When finished, click the
*Move Thread* button and the thread will be moved.

#### Deleting Threads

Users with administrative access to the message boards can delete
threads. Sometimes users begin discussing topics that are inappropriate
or which reveal information which should not be revealed. In this case,
you can simply delete the thread from the message boards. This is easy
to do. First, view the list of threads. Next to every thread is an
*Actions* button. Click *Actions ->  Delete* to delete the thread. This
does not prevent users from re-posting the information, so you may need
to be vigilant in deleting threads or consider the next option.

#### Banning Users

Unfortunately, sometimes certain users can become abusive. If you wind
up with a user like this, you can certainly make attempts to warn him or
her that the behavior he or she is displaying is unacceptable. If this
does not work, you can ban the user from posting on the message boards.

Again, this is very easy to do. Find any post which was written by the
abusive user. Underneath the user's name / profile picture is a link
called *Ban this User*. Click this link to ban the user from the message
boards.

If after taking this action the user apologizes and agrees to stop his
or her abusive behavior, you can choose to reinstate the user. To do
this, click the *Banned Users* tab at the top of the Message Boards
portlet. This will show a list of all banned users. Find the user in the
list and click the *Unban this User* link.

#### Splitting Threads

Sometimes a thread will go on for a while and the discussion completely
changes into something else. In this case, you can split the thread
where the discussion diverges and create a whole new thread for the new
topic. Administrative users will see a *Split Thread* link on each post.
To split the thread, click the link. You will be brought to a form which
allows you to add an explanation post to the split thread. Click *Ok* to
split the thread.

#### Editing Posts

Administrative users can edit not only their own posts, but also
everyone else's. Sometimes users will post links to copyrighted material
or unsuitable pictures. You can edit these posts, which allows you to
redact information that should not be posted or to censor profanity that
is not allowed on your message boards.

#### Permissions

Permissions can be set not only on threads, but also on individual
posts. You can choose to limit a particular conversation or a post to
only a select group of people. To do this, click the *Permissions* link
on the post and then select among the *Delete, Permissions, Subscribe,
Update, and View* permissions for the particular role to which you
want to grant particular access.

This function can be used to make it so some privileged users can post
on a certain thread, but others are allowed to view it, or any
combination of the above permissions.

## Wikis

Liferay's Wiki portlet, like the Message Boards portlet, is a
full-featured wiki application which has all of the features you would
expect in a state of the art wiki. Again, though, it has the benefit of
being able to take advantage of all of the features of the Liferay
platform. As such, it is completely integrated with Liferay's user
management, tagging, and security features.

So what is a wiki? Put simply, a wiki is an application which allows
users to collaborate on information. This, of course, has many
applications—the most famous of which is Wikipedia, which is a full
encyclopedia developed collaboratively by users from all over the world,
using a wiki. Another example would be Liferay's wiki, which is used for
collaborative documentation for the Standard Edition of the product.

A wiki application allows users to create and edit documents and link
them to each other. To accomplish this, a special form of markup is used
which is sometimes called wikitext. Unfortunately, the proliferation of
many different wiki applications resulted in slightly different syntax
for wikitext in the various products, as each new wiki tried to focus on
new features that other wikis did not have. For that reason, a project
called WikiCreole was started. This project resulted in the release of
WikiCreole 1.0 in 2007, which is an attempt to define a standard wiki
markup that all wikis can support.

Rather than define another wikitext syntax, Liferay's Wiki portlet
supports WikiCreole as its syntax. This syntax is a best-of-breed wiki
syntax and should be familiar to users of other wikis. The portlet
provides a handy cheat sheet for the syntax on the page editing form,
with a link to the full documentation if you wish to use some of
WikiCreole's advanced features.

### Getting Started with the Liferay Wiki

The Wiki portlet works just like the other portlets developed by
Liferay. Add the portlet to a page using the *Add More* menu and
then click *Configuration* in the portlet menu in the Wiki portlet's
title bar. You'll see some options are likely to be familiar to you by
now such as sharing the application with websites, Facebook, Google
Gadgets, etc.

You will also notice that the Communication tab has some additional
options not seen in the other portlets.

![image](../../images/portal-admin-ch5_html_19eab582.png)
*Illustration 14: Communication tab of Wiki portlet*

The Communication tab allows you to configure communication across
portlets, using predefined public render parameters. From here you can
modify four public render parameters: `nodeId`{.western},
`title`{.western}, `categoryId`{.western} and `tag`{.western}. For each
parameter you can select:

-   Ignore the values for this parameter that come from other portlets.
    For example, the wiki portlet can be used along with the Tags
    Navigation portlet. When a user clicks on a tag in the Tags
    Navigation portlet, the wiki it shows the list of pages with that
    tag. In some cases an administrator may want the wiki portlet to
    always show the front page independently of any tag navigation done
    through other portlets. This can be achieved by checking the ignore
    check box so that the values of the parameter coming from those
    other portlets are ignored.

-   Read the value of a parameter from another portlet. This is an
    advanced but very powerful option that allows portlets to
    communicate without configuring it beforehand. For example, imagine
    that the wiki portlet is used to publish information about certain
    countries. Another custom portlet that allows browsing countries for
    administrative reasons was written and placed on the same page. That
    second portlet has a public render parameter called *country* with
    the name of the country. Using this procedure, we can cause the wiki
    to show the information from the country being browsed through the
    other portlet. You can do this here for the wiki by setting the
    value for the title parameter to be read from the country parameter
    of the other portlet.

Once you have set the options the way you want them, click *Save*.

### Managing Wikis

The Wiki portlet can contain many wikis. By default, it contains only
one, called *Main*. To manage Wikis, navigate to the *Control Panel* and
select the *Wiki* tab. You will then be brought to a screen that allows
you to add, modify, and delete wikis. You will see that the Main wiki
has already been added for you.

At the top of this screen is a *Permissions* button. Clicking this
allows you to define what roles have access to create wikis. If you have
created a specific role for creating wikis, you can click the box in the
*Add Node* column and then click *Submit, *and that role will have
access to create new wikis in this portlet.

Clicking the *Add Wiki* button brings you to a screen which allows you
to give the wiki a name and a description. You can also set up some
default permissions. When you create a new wiki, it will appear in a
list at the top of the main page of the portlet.

Next to each wiki in the list of wiki nodes is an *Actions* button. This
button contains several options:

**Edit:** Lets you edit the name and description of the wiki.

**Permissions:** Lets you define what roles can add attachments to wiki
pages, add pages to the wiki, delete pages, import pages to the wiki,
set permissions on the wiki, subscribe to the wiki, update existing
pages, and view the wiki.

**Import Pages:** You can import your data from other wikis. This allows
you to migrate off of another wiki which you may be using and use the
Liferay wiki instead. You may wish to do this if you are migrating your
site from a set of disparate applications (i.e. a separate forum, a
separate wiki, a separate content management system) to Liferay, which
provides all of these features.

Currently, MediaWiki is the only wiki that is supported, but others are
likely to be supported in the future.

**Subscribe:** A user can subscribe to a wiki node, and any time a page
is added or updated Liferay will send an email to the user informing him
or her what happened.

**Delete:** Deletes the wiki node.

To go back to your wiki, click on its name in the list of wikis.

Note that there is also a wrench icon leading to a configuration menu on
this portlet in the control panel. This contains several other options
which you may have seen on other portlets.

**Email From,** **Page Added Email**, and **Page Updated Email** are
similar to the notification email settings for other portlets, allowing
you to customize who wiki emails come from and the format and text of
the email that is sent when a page is added or updated.

The* Display Settings* tab gives you several options for how the wiki
should be displayed. **Enable Page ****Ratings**, **Enable Comments**,
and **Enable Comment Ratings** are similar to the same options in other
portlets. They give you the ability to set how you want users to
interact with wiki documents: a little, a lot, or not at all.

Below this, you can set which wikis are visible in the Wiki portlet by
default and which are hidden. You might host two wikis in any given
community, exposing one to the public and keeping one of them private
for community members.

Finally, the Wiki portlet also supports RSS feeds as the other
collaboration portlets do, and you can configure its options here.

### Adding and Editing Wiki Pages

By default, there is one page added to your wiki, called *FrontPage*. To
get started adding data to your wiki, click the *Edit* link at the top
right of the portlet. You will be brought to a blank editing page.

![image](../../images/portal-admin-ch5_html_m98ecbcc.jpg)*Illustration 15: Editing
the default page in the wiki portlet*

You can now begin to add content to the page. Notice that there is a
very convenient “cheat sheet” which can help with the wiki syntax. You
can use this syntax to format your wiki pages. Consider for example the
following wiki document:
== Welcome to Our Wiki! ==


This is our new wiki, which should allow us to collaborate on
documentation. Feel free to add pages showing people how to do stuff.
Below are links to some sections that have already been added.


[[Introduction ]]


[[Getting Started]]


[[Configuration]]


[[Development]]


[[Support]]


[[Community]]

This would produce the following wiki page:

![image](../../images/portal-admin-ch5_html_78b47f5d.jpg)*Illustration 16: Wiki text
added to front page*

This adds a simple heading, a paragraph of text, and several links to
the page. Notice that the links are red, instead of the normal blue
color in the default Liferay theme. This indicates that the page behind
that link does not yet exist, and therefore needs to be created. If you
click one of those links, you will be brought immediately to the editing
screen you were on previously when you edited the front page, except
this time you will be creating the page behind the link you just
clicked. Liferay will display a notice at the top of the page stating
that the page does not exist yet, and that you are creating it right
now. As you can see, it is very easy to create wiki pages. All you have
to do is create a link from an existing page.
Note that at the top of the screen you can select from the Creole wiki
format and the HTML editor that comes with Liferay. We recommend that
you stick with the Creole format, as it allows for a much cleaner
separation of content and code. If you want all of your users to use the
Creole format, you can disable the HTML format using the
`portal-ext.properties`{.western} file. See the next chapter for further
information on how to configure this.

At the bottom of the page editing screen, you can select *Categories*
for the article. Categories are a hierarchical list of headings under
which you can create wiki pages. This allows you to organize your
content in a more formal fashion. You can create categories using the
Control Panel, in the *Tags and Categories* section.

### Page Details

When viewing a page, you can view its details by clicking the *Details*
link which appears in the top right of the page. This allows you to view
many properties of the page. There are several tabs which organize all
of the details into convenient categories.

#### Details

The Details tab shows various statistics about the page, and also
contains a few actions that you can perform on the page.

**Title:** Displays the title of the page.

**Format:** Displays the format for the page—either Creole or HTML.

**Latest Version:** Displays the latest version of the page. The wiki
portlet automatically keeps track of page versions whenever a page has
been edited.

**Created By:** Displays the user who created the page.

**Last Changed By:** Displays the user who last modified the page.

**Attachments:** Displays the number of attachments to the page.

**RSS Subscription:** Displays links which allow you to subscribe to the
page as an RSS feed in three formats: RSS 1.0, RSS 2.0, and Atom 1.0.

**Email Subscription:** Contains links allowing you to subscribe to the
entire wiki or just to this page.

**Advanced Actions: **Contains links allowing you to modify the
permissions on the page, make a copy of the page, move (rename) the
page, or delete the page.

#### History

This tab shows a list of all of the versions of the wiki page since it
was created. You can revert a page back to a previous state and you can
also compare the differences between versions by selecting the versions
and then clicking the *Compare Versions* button.

#### Incoming / Outgoing Links

The next two tabs are for incoming and outgoing links. These are wiki
links to and from the page. You can use this tab to examine how this
page links to other pages and how other pages link back to this page.

#### Attachments

The last tab is for attachments. You can attach any file to the wiki.
This is mostly used to attach images to wiki articles which can then be
referenced in the text. Referencing them using the proper WikiCreole
syntax renders the image inline, which is a nice way to include
illustrations in your wiki documents.

### Navigating in the Wiki Portlet

At the top of the portlet is a list of links which allow you to navigate
around the wiki. Next to the *Manage Wikis* button is a list of wikis
that are currently created in the portlet. Simply click on the wiki's
name to begin browsing that wiki. After this is a set of navigation
links:

**FrontPage:** Takes you to the main page of the main wiki.

**Recent Changes:** Takes you to a page which shows all of the recently
updated pages.

**All Pages:** Takes you to a flat, alphabetical list of all pages
currently stored in the wiki.

**Orphan Pages:** This link takes you to a list of pages that have no
links to them. This can happen if you take a link out of a wiki page in
an edit without realizing it's the only link to a certain page. This
area allows you to review wiki pages that are orphaned in this way so
that you can re-link to them or delete them from the wiki if they are no
longer relevant.

**Search:** Enter a term here and click the *Search* button to search
for items in the wiki. If the search term is not found, a link will be
displayed which allows you to create a new wiki page on the topic for
which you searched. In general, categories start of with broad terms and
eventually

## Tags

Tags are an important tool that you can use to help organize information
on your portal and make it easier for your users to find content that
they're looking for. Tags are words or phrases that you can attach to
any content on the website. Tagging content will make your search
results more accurate, and enable you to use tools like the Asset
Publisher to display content in an organized fashion on a web page.
There are two ways to create tags: you can do it through the
administrative console in the control panel, or on the fly as content is
created.

![image](../../images/portal-admin-ch5_html_73b7305.png)
*Illustration 17: The Add Tag dialog*

To create tags in the control panel, select the community that you want
to create tags for, and select *Tags. *From this screen, you will be
able to view any existing tags and make new ones. To create a new tag,
simply click *Add Tag*. You then be asked for the name of the tag, and
you'll also have the ability to set permissions for viewing or managing
the tag.

From the *Tags* screen on the control panel, you can also edit existing
tags. You can change the tag name, change the tag's permissions, delete
the tag, or add *Properties*. Properties are essentially tags for your
tags; you can use them to add additional information about your tags.

## Categories

Categories are similar in concept to tags, but are designed with the
administrator in mind, not the end user. Hierarchies of categories can
be created, and categories can be grouped together in *Vocabularies*.
Where tags represent an ad hoc method for users to group content
together, categories exist to allow administrators to organize content
in a more official, hierarchical structure. As has been said in Chapter
3, think of tags like the index to a book and categories like the table
of contents. Both serve the same purpose: to help the user find the
information he or she seeks.

Adding vocabularies and categories is similar to adding tags. Once
you've selected the community or organization you want to work on,
select *Categories *from the content section of the control panel, and
you will be presented with the categories administration screen.

![image](../../images/portal-admin-ch5_html_203f5177.png)
*Illustration 18: Categories administration*

Clicking on a vocabulary on the left will display any categories that
have been created under that vocabulary. You can create new vocabularies
simply by clicking *Add Vocabulary* and providing a name for it. You can
create categories in a similar fashion by choosing a vocabulary on the
left, and then selecting *Add Category*. Like tags, you can also provide
properties for categories.

Once you have created some vocabularies and categories, you can take
advantage of the full capabilities of categories by creating a nested
hierarchy of categories. To nest categories, select what you want to be
the parent category, then drag any category that you want to become a
child category onto it. You will see a plus sign appear next to the name
of the category you are dragging if you can add it to the selected
parent category; if you see a red *x* that means that you cannot add
that category as a subcategory of parent category that you have
selected.

Once you have created a hierarchy of categories, your content creators
will have them available to apply to content that they create. Under the
*Categorization* section of the content creation screen, click the
button labeled *Select* under the *Categories *heading. A dialog will
appear with your vocabularies and categories. Select any relevant
categories by checking the box next to them, and they will be applied to
the content.

## Social Equity

When you have a lot of user interaction on your web site, sometimes it
is helpful to try to separate the signal from the noise. Liferay
contains a lot of applications which end users can make use of to
communicate with each other and provide information. Some of this
information is good and helpful and some of it can be rather unhelpful.
In order to better show which users are making real, valuable
contributions, Liferay is introducing the new Social Equity system with
Liferay 6.

![image](../../images/portal-admin-ch5_html_m6daa4ee7.png)
*Illustration 20: The Social Equity control panel*

Social Equity enables you to assign values and weight for each
contribution that a user makes. Points are given to users for
*Information, *and *Participation. *This way, a user who writes several
blogs and wiki articles can be shown as having a higher rank than a user
who has only written comments and message board replies. The purpose of
this is to better indicate which users on a web site are knowledgeable
contributors, and to reward those users.
Currently Social Equity is available for the Blogs, Message Boards, and
Wiki applications. Each category contains a rating for *Information
Value, Information Lifespan, Daily Limit, Participation Value,
Participation Lifespan, *and *Daily Limit*.

**Information Value:** Indicates how many points a user receives for the
information content of a specific action.** **

**Information Lifespan: **Indicates, in number of days, how long it will
be before the information points expire. Setting it to 0 means that they
will never expire.** **

**Daily Limit: **This is the maximum number of actions of this type that
a user will receive information points for in a given day.** **

**Participation Value:** Indicates how many points a user receives for
the participating in the content creation of the website.** **

**Participation Lifespan: **Indicates, in number of days, how long it
will be before the participation points expire. Setting it to 0 means
that they will never expire.** **

**Daily Limit: **This is the maximum number of actions of this type that
a user will receive participation points for in a given day.** **

The actions available that you can provide credit for are wide ranging.
Users can receive credit for everything from writing wiki pages to
simply viewing content. You can also easily tweak these numbers in the
control panel if it becomes clear that certain activities are weighted
too high or too low.

If you have a portal that is heavily driven by community created
content, Social Equity is an invaluable tool to recognize users who are
major contributors, as well as to indicate to new users whose advice is
going to be the most sound and helpful. It's easy to set up, and can be
configured differently for each community and organization on your
portal, giving you increased flexibility across your web site as a
whole.

## Summary

We have together explored many of the portlets in Liferay's
collaboration suite. You have seen how you can configure all of the
portlets in a similar fashion using a unified user interface. After
this, we went over all of the portlets in succession.

The Blogs and Blogs Aggregation portlets can be used to manage shared
blogs or blogs belonging to a group of people at once. These portlets
have all the features you would want in a blog, including rich text
editing, links to news aggregators, tags, RSS feeds, and more.

The Calendar portlet likewise can be used to manage a shared calendar or
a group calendar. It includes features for events, event notification,
repeatable events, and import and export to and from the standard
iCalendar format.

Integrating mail with your portal is easy with the Mail portlet. You can
add as many custom or Gmail mail accounts as you wish, and this portlet
can keep them all organized in one place, together with the rest of the
things Liferay is aggregating for you.

Discussion becomes easy with Liferay's Message Boards portlet. This
portlet can be used to manage heavily trafficked discussion forums with
ease. It inherits all of the security features of the Liferay platform
and includes administrative functions for thread priorities, moving
threads, nested discussion categories, banning users, and more.

Liferay's Wiki portlet is a state of the art wiki application that users
can make use of to collaborate on web pages. Again, it inherits the
strengths of the Liferay platform in the form of security, interface,
and search. You can use the wiki portlet to manage several wiki nodes or
use many wiki portlets to manage one node each.

Tying all of these applications together are tags and categories. Tags
can be added to any content by end users, and hierarchies of categories
can be added to the system by administrators to be applied to content
anywhere in the portal. These help your users to find the content that
is most relevant to them, empowering the search to be as accurate as
possible.

Liferay's collaboration platform is a full suite of integrated
applications that empower users to work together. You can use them to
great effect to enhance your web site and to build a vibrant, active
community.
