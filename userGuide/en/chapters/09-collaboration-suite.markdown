# Collaboration Suite [](id=collaboration-suite-liferay-portal-6-2-user-guide-08-en)

Liferay Portal ships with a robust suite of collaboration applications which
you can use to build communities of users for your site. These applications
provide all the features that you would expect of standalone versions outside
of a portal setting. The difference with Liferay's collaboration suite,
however, is that all of the applications share a common look and feel, security
model, and architecture. They inherit the strengths of being part of Liferay's
development platform so you can use them in combination with Liferay's user
management and content management features to build a well-integrated,
feature-rich web site.

This chapter focuses on how to use Liferay's collaboration suite. We explain
how to set up and administer:

-   Blogs
-   Calendars
-   Message Boards
-   Wikis
-   Polls
-   Announcements
-   Chat
-   Mail

Let's jump right in and begin by exploring Blogs.

## Expressing Yourself Using Blogs [](id=expressing-yourself-using-blogs-liferay-portal-6-2-user-guide-08-en)

The word *Blog* is an apostrophe-less contraction of the two words *web* and
*log*. Blogs were first popularized by web sites such as Slashdot
([http://slashdot.org](http://slashdot.org)) which have the format of a running
list of entries to which users could attach comments. Over time, more and more
sites such as Digg, del.icio.us, and Newsvine adopted the format, empowering
users to share their opinions and generating lively discussions.

Over the course of time, blogging sites and applications began to appear, such
as blogger.com, blogspot.com. TypePad, WordPress, and Web Roller. These
applications allow *individuals* to run their own web sites in the same format:
a running list of short articles to which readers who are registered with the
site can attach threaded comments. People who run a blog are called *bloggers*,
and sometimes they build a whole community of readers who are interested in
their blog posts. Anyone can have a blog, in fact, there are several famous
people who run their own blogs. It gives people an outlet for self-expression
that they would not otherwise have, and the ubiquity and wide reach of the
Internet ensures that if you have something important and interesting to say,
somebody will read it.

![Figure 8.1: Slashdot was one of the first blogs on the Internet.](../../images/05-slashdot.jpg)

Liferay Portal has a Blogs portlet which allows you to provide a blogging
service to users of your web site. In fact, Liferay extensively uses the Blogs
portlet on
[http://www.liferay.com/community/blogs](http://www.liferay.com/community/blogs)
to provide community members and employees with blogs of their own. In addition
to the Blogs portlet, there's also a Blogs Aggregator portlet which can take
entries from multiple users' blogs and put them all in one larger list. We will
go over how to use both of these portlets to create a blogging site for your
users.

### The Blogs Portlet [](id=the-blogs-portlet-liferay-portal-6-2-user-guide-08-en)

The Blogs portlet is available from the *Collaboration* section of the *Add
&rarr; Applications* menu. Notice that it is an instanceable portlet, meaning
that it supports scopes. This allows you to use the Blogs portlet to create a
shared blog to build a site like Slashdot or to create multiple personal blogs
to build a site like [http://blogger.com](http://blogger.com). What's the
difference? Adding the Blogs portlet to a site page creates a shared blog for
members of the site that the page belongs to. Adding the Blogs portlet to a
user's personal site creates a blog just for that user. The Blogs portlet works
the same way in both cases. And of course, you can change the Blog portlet's
scope to have different blogs on different pages in the same site.

![Figure 8.2: The initial view of the Blogs portlet.](../../images/05-initial-view-blogs-portlet.png)

By default, the Blogs portlet displays the latest entry in its entirety. When
you first add the portlet to a page, it has no entries, so the portlet is
empty. There are several display options to let you configure it to look the
way you want it to look. Before we start adding entries, let's configure the
portlet so that it displays entries the way you want them.

#### Configuring the Blogs Portlet [](id=configuring-the-blogs-portlet-liferay-portal-6-2-user-guide-08-en)

The Blogs portlet is easy to configure. Click on the *Options* icon in the
portlet's title bar and select *Configuration*. Beneath the Setup tab, there is
another row of options.

**Display Settings:** changes various display options for the Blogs portlet. To
choose the right settings, you should think about the best way to display your
entries as well as how you want users to interact with bloggers.

- *Maximum Items to Display:* choose the total number of blog entries to display
on the initial page. You can select up to one hundred to be displayed.

- *Display Template:* choose between *Full Content*, *Abstract*, or *Title*.
Setting this to Abstract shows the abstract, or if there isn't one, only the
first 30 words of your blog entries, with a Read More link at the bottom of
each that expands to the whole entry. To learn how to customize your own display
templates visit the *Using application display templates* section of the
*Personalization and Customization* chapter.

- *Enable Flags:* flag content as inappropriate and send an email to the
administrators.

- *Enable Related Assets:* select related content from other portlets to pull
into their blog entry for readers to view.

- *Enable Ratings:* lets readers rate your blog entries from one to five stars.

- *Enable Comments:* lets readers comment on your blog entries.

- *Enable Comment Ratings:* lets readers rate the comments which are posted to
your blog entries.

- *Enable Social Bookmarks:* lets users tweet, Facebook like, or +1 (Google
Plus) about blog posts. You can edit which social bookmarks are available in the
*Social Bookmarks* section of the Configuration menu.

- *Display Style:* select a simple, vertical, or horizontal display style for
your blog posts.

- *Display Position:* choose a top or bottom position for your blog posts.

- *Social Bookmarks:* choose social bookmarks to enable for blog posts, which
includes Twitter, Facebook, and plusone (Google Plus).

![Figure 8.3: The Blogs portlet's *Configuration* menu offers a plethora of display settings.](../../images/05-blogs-configuration.png)

**Email From:** defines the *From* field in the email messages that users
receive from the Blogs portlet.

**Entry Added Email:** defines a subject and body for the emails sent out when
a new Blog entry has been added.

**Entry Updated Email:** defines a subject and body for the emails sent out
when a new Blog entry has been updated.

**RSS:** choose how blogs are displayed to RSS readers. Here, you can choose
how you want your blog entries to be published as feeds to readers and outside
web sites.

- *Maximum Items to Display:* choose the total number of RSS feeds to display on
the initial page. You can choose up to one hundred to be displayed.

- *Display Style:* choose between *Full Content*, *Abstract*, and *Title*. These
options work just like the ones above for blog entries.

- *Format:* choose which format you want to deliver your blogs: Atom 1.0, RSS
1.0, or RSS 2.0.

Now that you have the Blogs portlet looking the way you want it, you'll want to
review permissions for it--especially if you're working on a shared blog. 

#### Permissions [](id=permissions-liferay-portal-6-2-user-guide-08-en)

If you have a personal blog, the default permissions should work well for you.
If you have a shared blog, you may want to modify the permissions on the blog.
The default settings make it so only the owner of the site to which the portlet
has been added is able to add entries. This, of course, is great if the Blogs
portlet has been added to a user's personal pages, but doesn't work so well for
a shared blog. But don't worry: it's easy to share a blog with multiple users.

First, create a role for your bloggers and add them to the role (roles are
covered in chapter 13). Next, click the *Permissions* button on the
Blogs portlet. A list of both portal and site roles is displayed, and currently
only the owner is checked. Check off any other role or team that should have
the ability to add blog entries, and then click *Save*. Once this is done,
users in the roles or teams that you selected are able to post to the shared
blog. 

Now that everyone's able to post, let's look at how posts work. 

#### Adding Blog Entries [](id=adding-blog-entries-liferay-portal-6-2-user-guide-08-en)

Now you're ready to begin adding blog entries. Click the *Add Blog Entry*
button. The following data entry screen appears: 

![Figure 8.4: After clicking *Add Blog Entry*, a new window appears with configuration options for your new blog entry.](../../images/05-new-blog-entry.png)

There isn't much difference between this screen and any other data entry screen
within Liferay Portal. You get a title, a way of scheduling when the entry is
to appear, and a rich editor that allows you to format your entry the way you
want, complete with embedded images, videos, and the like. Note also that as
you type, the entry is automatically saved as a draft at periodic intervals.
This gives you peace of mind in using the portlet from within your browser,
since you won't lose your entry in the event of a browser crash or network
interruption. You can also tag your entries using the same tagging mechanism
found everywhere else in the portal.

The Blogs portlet also supports trackbacks and pingbacks. Trackbacks are
special links that let you notify another site that you explicitly linked to 
them in the *Content* field. For example, if you wanted to write an entry in 
your blog and reference some other site's entry, you might put the URL to the
other entry in the *Content* field and the trackback URL in the *Trackbacks 
Sent* field. If you have multiple URLs you want to send trackbacks to, 
separate them with spaces. 

If you want others who link to your blog to let you know about the link via
trackbacks, leave the *Allow Trackbacks* box checked. This generates a URL that
is displayed with your blog entry. Others who want to link to your entry can
use this URL for the link, to send trackbacks to your blog.

Note that trackbacks only work when the protocol is supported by both the
linker and the linkee. A newer way to support similar link notification
functionality is *pingbacks*. Pingbacks are XML-RPC requests that are similar
to trackbacks except they're automatically sent when you link to another site.
They're easier to use because you don't have to do anything extra: if you link
to another site in your blog entry, Liferay sends a pingback to the other site
to notify that site that you linked to it. Similarly, if someone links to your
blog entry, Liferay can receive a pingback from that person's site and record
the link.

You can enter a description of your post beneath the Abstract heading, and this
can be used by the Abstract display style. Below this is the Categorization
heading, where you can attach tags and/or categories to your blog entry. You
should definitely consider doing this: it improves search results for blog
entries, and it gives you more navigation options that you can pass on to your
users. For example, you can add the Tags Navigation portlet to another column
on your blogs page, allowing users to browse blog entries by tag. 

Below this is the Related Assets heading. If there's some other content in the
portal that's related to your blog, you can choose it here. For example, you
might want to write a blog entry talking about a particular discussion that
happened on the forums. To link those two assets together, select the forum
thread under Related Assets. 

Once you've finished your blog entry, click *Publish*. You'll go back to the
list of entries, and now your entry is displayed. Here is what it looks like
when the display template is set to *Full Content* and the maximum items to
display is set to ten:

![Figure 8.5: This sample blog entry gives you an idea of what new content and features are displayed on your page.](../../images/05-first-blog-entry-added.png)

You can see that in the summary view, you don't see the trackback/pingback
link, and you only see the number of comments that have been added. If you were
to click the *Read More* link, you would see the entirety of the article, all
the comments in a threaded view, and the trackback/pingback link which others
can use to link back to your blog entry.

The full view of the blog entry also contains links to share blog entries on
social networks, such as Twitter, Facebook, and Google Plus. This gives your
readers an easy way to share blog entries with friends, potentially driving
further traffic to your site. As you can see, the Blogs portlet is a
full-featured blogging application that gives you and your users the ability to
enter the blogosphere with an application that supports anything a blogger
needs.

Of course, Liferay is a portal, and as a portal, it excels at aggregating
information from multiple places. For that reason, it also includes the Blogs
Aggregator portlet so that you can "bubble up" blog entries from multiple users
and highlight them on your site. Let's look next at how that works. 

### Aggregating Blog Entries [](id=aggregating-blog-entries-liferay-portal-6-2-user-guide-08-en)

You can set up a whole web site devoted just to blogging if you wish. The Blogs
Aggregator portlet allows you to publish entries from multiple bloggers on one
page, giving further visibility to blog entries. This portlet is also very
straightforward to set up. You can add it to a page from the *Collaboration*
category in the *Add &rarr; Applications* menu.

If you click *Configuration* from the options button in the title bar of the
portlet, the Blogs Aggregator's configuration page appears. From here, you can
set several configuration options.

![Figure 8.6: You can navigate to the Blogs Aggregator Configuration menu by selecting the *Options* gear from the portlet's title bar and selecting *Configuration*.](../../images/05-blogs-aggregator-configuration.png)

**Selection Method:** select Users or Scope here. If you select Users, the
Blogs Aggregator aggregates the entries of every blogger on your system. If you
want to refine the aggregation, you can select an organization by which to
filter the users. If you select Scope, the Blogs Aggregator contains only
entries of users who are in the current scope. This limits the entries to
members of the site where the Blogs Aggregator portlet resides.

**Organization:** select which organization's blogs you want to aggregate.

**Display Style:** select from several different styles for displaying blog
entries: *Body and Image*, *Body*, *Abstract*, *Abstract without Title*,
*Quote*, *Quote without Title*, and *Title*.

**Maximum Items to Display:** select maximum number of entries the portlet
displays.

**Enable RSS Subscription:** creates an RSS feed out of the aggregated entries.
This lets users subscribe to an aggregate feed of all your bloggers. Below this
checkbox, you can configure how you want the RSS Feed displayed:

- **Maximum Items to Display:** select maximum number of RSS items to display.
- **Display Style:** select from several different styles for displaying RSS
feeds: *Abstract*, *Full Content*, and *Title*.
- **Format:** select which web feed language to use for your feed, which
includes *Atom 1.0*, *RSS 1.0*, or *RSS 2.0*.

**Show Tags:** for each entry, displays all the tags associated with the blogs.

When you've finished setting the options in the portlet, click *Save*. Then
close the dialog box. You'll notice the Blogs Aggregator looks very much like
the Blogs portlet, except that the entries come from more than one author. This
makes it nice and familiar for your users to navigate. 

### The Blogs Administrator Portlet [](id=the-blogs-administrator-portlet-liferay-portal-6-2-user-guide-08-en)

In the Control Panel there's a portlet for managing your site's blog entries.
Most of the time, the Blogs portlet is the only tool you'll need to manage your
blog entries. If, however, you need to massively delete blog entries, the blogs
administrator portlet is the perfect tool for you. 

![Figure 8.7: The Blogs Administrator portlet lets you delete large sets of blog entries.](../../images/04-blogs-administrator.png)

Note that it's only for batch processing of blog entries; for the full set of
tools for managing blog entries, your best bet is to use the Blogs portlet.
Next, let's look at Liferay's improved Calendar portlet available from Liferay
Marketplace.

## Managing Events and Calendar Resources with Liferay's Calendar Portlet [](id=managing-events-and-calendar-resources--liferay-portal-6-2-user-guide-08-en)

As of Liferay 6.2, Liferay no longer includes the core Calendar portlet that was
included in Liferay 6.1 and previous versions. Instead, Liferay provides a new
Calendar portlet on Liferay Marketplace. The new Calendar portlet upgrades
several features of the old Calendar portlet and adds additional features. The
features you're used to from the old Calendar portlet are still available. You
can add events and configure email notifications. You can use the Calendar
portlet as a shared calendar for an entire site or as a personal calendar. All
content created through the old Calendar is imported automatically by the
new Calendar. Some of the new features include an improved look and feel, more
configuration options, the ability to assign multiple calendars to a site or
user, the concept of calendar resources, and event invitations.

<!-- Add installation instructions for the new Calendar portlet here. Confirm
that it's available from Marketplace. Briefly describe the differences between
the CE and EE versions of the new Calendar portlet. -->

### Configuring the Calendar Portlet [](id=configuring-the-calendar-portlet-liferay-portal-6-2-user-guide-08-en)

Once you've added the new Calendar portlet to a page, open its configuration
dialog box by clicking on the gear icon in the portlet's title bar and
selecting *Configuration*. The Setup tab provides two sub-tabs of configurable
settings: *User Settings* and *RSS*.

![Figure 8.8: The Setup &rarr; User Settings tab provides several new configuration options that weren't available from the old Calendar portlet.](../../images/new-calendar-configuration.png)

**User Settings:** On this screen, you can customize your calendar's default
view and settings. You can set the *Time Format* to *AM/PM* or to *24 Hour*.
*AM/PM* is the default; with this time format, times such as 8AM or 11PM are
displayed. With the *24 Hour* time format, these times are displayed as 08:00
and 21:00. *Default Duration* refers to event duration. When you add a new event
to the calendar, the time you set here specifies how long events last by
default. You can set the *Default View* to *Day*, *Week*, or *Month*. You can
set *Week Starts On* to *Sunday*, *Monday*, or *Saturday*. For *Time Zone*, you
can either specify a particular time zone like *Pacific Standard Time* or *China
Standard Time* or you can check the *Use Global Time Zone* box. If you check
*Use Global Time Zone*, the time displayed by the Calendar portlet depends on
whether it's being viewed by a guest or a logged in user:

-If a user is logged in to the portal, the Calendar portlet displays events using the
time zone set for the user in *My Account* &rarr; *Display Settings* &rarr; *Time Zone*.
-If the Calendar portlet is viewed by a guest, or by a user who is not logged
in, the Calendar portlet displays events using the time zone set by the portal
administrator in *Control Panel* &rarr; *Portal Settings* &rarr; *Display
Settings* &rarr; *Time Zone*.

**RSS:** You can enable and customize your RSS Subscriptions for your calendar
from this screen. You have the following options: *Enable RSS Subscription*,
*Maximum Items to Display*, *Display Style*, *Format*, and *Time Interval*.
These options are explained in more detail in the Blogs section.

Next, let's look at how to use the new Calendar portlet.

### Using the Calendar Portlet [](id=using-the-calendar-portlet-liferay-portal-6-2-user-guide-08-en)

The first thing you'll notice about Liferay's new Calendar portlet is its
revamped look and feel. There's a monthly mini-calendar which provides you with
an overview of upcoming events. There's also a larger, more detailed calendar
area. You can set the large calendar area to display a *Day*, *Week*, or *Month*
view. The *Day* view displays a day as a single column, divided into hours and
half-hours. The *Week* view is similar but displays seven days as seven columns,
divided into hours and half-hours. The month view displays a traditional
calendar view with days represented as boxes. In the previous section, we saw
how to configure the default display view of the calendar.

#### Adding New Calendars [](id=adding-new-calendars-liferay-portal-6-2-user-guide-08-en)

You can add new personal or site calendars from the default view of the new
Calendar portlet and choose which calendar's events to display. To add a new
personal or site calendar, mouse over *My Calendars* or *Current Site
Calendars*, click on the arrow icon, and select *Add Calendar*. To manage your
personal or site calendars, navigate to the same drop-down menu and select
*Manage Calendars*. All users can manage their personal calendars. By default,
only site administrators can manage site calendars.

On the Manage Calendars screen, you can click *Add Calendar* to create a new
calendar. As usual, you can enter a name and description for the calendar and
configure its permissions. Flag the *Default Calendar* checkbox if you'd like
the new calendar to be the default calendar. All sites, including personal
sites, have a default calendar. When a calendar is first visited, the events
from the default calendar are displayed. You can customize the events that
appear in the main area of the calendar portlet by clicking on the colored boxes
corresponding to the calendars. When you click on a colored box, its color
disappears and the events of that calendar are no longer displayed. Click on an
uncolored box to view the events of the corresponding calendar.

![Figure 8.9: You have options to add or manage calendars. Also, you can toggle the colored boxes beside your calendars to show/hide your planned events.](../../images/new-calendar-manage-calendars.png)

When adding a calendar, you can also specify whether or not to enable comments
and ratings for your calendar's events. Comments and ratings can be
enabled/disabled on a per calendar basis. They are disabled by default.
Additionally, you can specify a color for your calendar. Events created in the
new calendar will default to the color you choose.

You can edit a calendar to change its name, description, or color. You can also
change the calendar's default calendar status and flag or unflag the *Enable
Comments* and *Enable Ratings* checkboxes to enable or disable comments and
ratings for a calendar's events.

#### Adding Events to a Calendar [](id=adding-events-to-a-calendar-liferay-portal-6-2-user-guide-08-en)

It's very easy to add events to a calendar: just click on any day of the
calendar and you'll see an event creation popup appear. If you've selected the
*Day* or *Month* view, you can click on the specific time when your event
begins.

![Figure 8.10: When you click anywhere on the calendar, you'll see the event creation popup appear. Click *Edit* to specify details for your event.](../../images/new-calendar-event-popup.png)

In the new event popup, you can select the calendar in which you'd like to
create the new event. This is useful since sites and users can have multiple
calendars. You can click *Save* to create the event right away or you can click
*Edit* to specify more event information.

![Figure 8.11: You can specify event details such as the event title, start date, end date, description, location, and more.](../../images/new-calendar-event-details.png)

The *Title* you enter determines the name of the event as it's displayed on the
calendar. The *Start Date* and *End Date* times determine when your event takes
place. You can click on the date text box to change the day and you can specify
times by selecting a particular hour and minute of the day. Note: Even though
the *Day* and *Week* views of the calendar break days into hours and half-hours
and display events in these time-slots, that's just for convenience. You're free
to specify whatever start times and end times you like, such as 11:37am and
12:08pm. Check the *All Day* box if your event lasts for an entire day. Check
the *Repeat* box if your event takes place over multiple days. Checking this box
opens another popup.

![Figure 8.12: The *Repeat* box allows you to specify whether an events repeats daily, weekly, monthly, or yearly, how often it repeats, and when (or if) it ends.](../../images/new-calendar-event-repeat.png)

In the Details collapsible section, you can specify four pieces of information.
Under *Calendar*, you can select the calendar to which you'd like to add your
event. Remember that sites and users can have multiple calendars. Under
*Description*, you can explain the purpose of your event and add any details
that you think might be useful. Use the *Location* field to specify where your
event takes place. Lastly, the *Viewable by* field lets you set the permissions
for your calendar.

For more advanced permissions options, select the *More Options* link. A list of
roles for which you can permission appears in the left column. The other columns
represent permissions which can be configured for the event:

- Add Discussion
- Delete Discussion
- Permissions
- Update Discussion

*Discussions* refers to comments on the event. So the Add Discussion, Delete
Discussion, and Update Discussion permissions determine whether a role can add,
delete, or update a comment on an event. The Permissions permission determines
whether a role can update an event's permissions.

In the Invitations collapsible section, you can invite users, organizations, or
other calendar resources to an event. To invite a user, group, or resource,
start typing the name of the entity you'd like to invite and a list of matches
will appear. Select the one you want or hit *Enter* if the entity you'd like to
invite is at the top of the list. All the entities you've invited to your event
appear as a list under the *Pending* heading, which shows how many pending
invitations there are. If you accidentally invited the wrong entity, mouse over
its name in the *Pending* list, click on the arrow icon that appears, and click
*Remove*. If you'd just like to check the status of a resource, click on *Check
Availability* instead. When you click on *Check Availability* for a calendar
resource, its schedule is displayed in this calendar view. If you like to
overview the availability of all the invited entities, just click on *Resources
Availability*.

![Figure 8.13: You can invite users, organizations, or other calendar resources to your event and can check their availability in a calendar view.](../../images/new-calendar-event-invitations.png)

The Reminders collapsible section lets you specify up to two
times when event reminder notifications will be sent via email. For example, you
might like event notifications to be send one day and one hour before your
event. Email is currently the only supported event notification type. To avoid
confusion, the event time in notification emails is shown in the user's
time zone, and the time zone is also displayed. Of course, Liferay can only
calculate this properly if both the portal's and the user's time zones are set
correctly. Users should set this for themselves when they create accounts. To
set the portal's time zone, see the section *Configuring Additional Portal
Settings* in chapter 17. 

The Categorization and Related Assets collapsible sections let you tag the event
and select related assets, respectively. By tagging your event, it will come up
in searches related to that specified tag. In addition, related assets including
blogs, message boards, web content, calendar, bookmarks, wikis, and documents
can be selected to include with your calendar event. When you're done specifying
event details, click *Save*.

Liferay's Calendar portlet supports social activities. Whenever a calendar event
is added or updated, a corresponding social activity notification is created. If
the event was added or updated in a calendar that the current user has
permission to view, the social activity will be viewable in the Activities
portlet. If the Social Networking portlets have been installed (they're
available as an app on Liferay Marketplace; search for *Social Networking CE* or
*Social Networking EE*), the social networking notifications will also appear in
all the appropriate portlets, such as the Friends' Activities or Members'
Activities portlets.

Calendar administrators can customize the email notification templates for event
invitation and event reminder emails. To customize a calendar's email templates,
open the calendar's Calendar Settings window by clicking on the small arrow next
to the calendar's name in the default view of the calendar portlet and selecting
*Calendar Settings*. By default, the General tab of the Calendar Settings
appears, where you can edit the calendar's name, description, color, default
calendar status, and whether or not calendar events and ratings are enabled or
disabled. Click on *Notification Templates* at the top of the screen to view a
new tab. Then click on either *Invite Email* or *Reminder Email* to customize
event invitation or event reminder emails. You can customize the name that
appears on the sent emails, the address from which to send the email, the
subject, and the body of the email. As with Liferay's other other email
notification templates (e.g., the Message Boards' notification email templates),
a definition of terms appears below the email body editor. This definition of
terms list specifies variables that you can you can use when customizing the
email template. For example, `[$EVENT_LOCATION$]` represents the event location,
`[$EVENT_START_DATE$]` represents the event start date, and `[$EVENT_TITLE$]`
specifies the event title.

![Figure 8.14: The *Calendar Settings* &rarr; *Notification Templates* tab lets you customize the email templates for emails that are automatically sent out to remind users of upcoming events or invite users to new events.](../../images/new-calendar-configuration2.png)

To respond to an event invitation, you can click *Accept*, *Maybe*, or *Decline*
when viewing the event in the Calendar portlet. The default event invitation
notification emails contain links to their corresponding events. This allows
users easy access to events in the Calendar portlet so they can respond to event
invitations.

#### Adding and Using Calendar Resources [](id=adding-and-using-calendar-resources-liferay-portal-6-2-user-guide-08-en)

You might be wondering, "It makes sense to invite users and organizations to an
event, but what about other calendar resources? What are they used for? How can
you add them?" Good questions. Calendar resources can represent just about
anything that you think might be important to an event. For example, your
department might have a limited number of rooms and projectors to use for
presentations. You can add the various rooms and projectors as calendar
resources and add them to events. This way, when new events are added, the event
organizer can check the availability of important resources against events that
have already been planned. So, how can you add new calendar resources? You might
have noticed that the new Calendar portlet has two tabs in the main portlet
window: Calendar and Resources. Click on the *Resources* tab of the portlet to
view, edit, add, or delete resources.

![Figure 8.15: Click on the *Resources* tab of the portlet to view, edit, add, or delete resources.](../../images/new-calendar-resources.png)

Click on the *Add Resource* button to add a new calendar resource. You'll be
able to enter a name and description for your resource, specify whether or not
it should be active, apply tags and categories, and configure its permissions.

Once you've created a calendar resource, you can click on the *Actions* button
next to it and select one of the following four options: *Edit*, *Permissions*,
*Delete*, or *View Calendars*. The *View Calendars* option exists since calendar
resources, like sites and users, can have more than one calendar associated with
them. Click *Actions* &rarr; *View Calendars* and then click on *Add Calendar*
to add a new calendar in which the selected calendar resource appears.

![Figure 8.16: Click on *Add Calendar* to add a new calendar in which the selected calendar resource appears.](../../images/new-calendar-resource-calendars2.png)

Liferay's new Calendar portlet provides an easy-to-use scheduling tool. Sites
and users can have multiple calendars, calendar events can include calendar
resources and calendar resources have their own schedules of availability. We
hope this flexible system meets the needs of your organization.

#### Exporting and Importing Calendar Data [](id=exporting-and-importing-calendar-data-liferay-portal-6-2-user-guide-08-en)

Liferay's Calendar portlet allows data to be exported or imported as LAR files.
Both calendar events and resources can be exported or imported. As with all LAR
files, data can only be imported into a version of Liferay that's the same as
the one from which it was exported. To access the interface for exporting or
importing calendar data, click on the *gear* icon in the portlet header and
select *Export / Import*. By default, the Export tab appears which allows you to
define a new export process or use an existing one to export calendar data. If
you'd like to import data, click on *Import*. You'll be able to define a new
import process by selecting an existing LAR file or by using an existing import
process.

To define a new export process, you first have to choose a name for the LAR file
to be generated. `Calendar-201308061558.portlet.lar` is an example of a typical
Calendar portlet LAR filename. Notice that by default, a timestamp is included
in the LAR filename. First you have the option of including the Configuration of
your calendar in the LAR file. Next, you have to define a data range. You can
select *All* to export calendar data regardless of when it was created or last
modified. Alternatively, you can specify a data range. In this case, only
calendar data which was created or modified at or after the start date and
before the end date will be exported. Next, you can choose which kinds of
calendar data to export: calendar events, resources, or both. For each selected
kind of calendar data, you can choose to export associated comments, ratings, or
both. Finally, you can choose whether or not to export the permissions defined
for the specific Calendar portlet window from which you're exporting data. Click
*Export* to export your data into a LAR file. You should see a message stating
*Your request completed successfully* and you'll be able to click on the LAR
file's name to download it.

To define a new import process, click on *Import* from the Export / Import
window. Then either drop a LAR file into the box outlined by the dashed lines or
click on the button to browse to and select the LAR file to import. Once
selected, you'll be able to choose whether to import calendar events, resources,
or both and whether to import the comments and ratings associated with these
calendar events and resources. You'll also be able to choose whether or not to
import the permissions for the Calendar portlet window from the LAR file. Once
you've made these selections, click *Continue* and you'll be able to choose a
strategy for updating data:

- The *Mirror* strategy means that all data and content inside the imported LAR
  will be created as new the first time while maintaining a reference to source.
  Subsequent imports from the same source will update the entries instead of
  creating new entries.
- The *Mirror with Overwriting* is the same as the Mirror strategy except that
  if an entry with the same name is found, it is overwritten.
- The *Copy as New* strategy means that all data and content inside the imported
  LAR will be created as new entries within the current site every time the LAR
  is imported.

You must also specify an option for assigning ownership of the imported data:

- *Use the Original Author* means that authorship of imported content will be
  preserved, if possible. If the original author of the imported content is not
  found, the current user will be assigned as the author.
- *Use the Current User as Author* means that the current user will be assigned
  as the author of the imported content regardless of the original author.

### Upgrading the Calendar Portlet [](id=upgrading-the-calendar-portlet-liferay-portal-6-2-user-guide-08-en)

In Liferay 6.1 and previous versions, an older version of the Calendar portlet
was included as one of the core portlets. If you're upgrading from Liferay 6.1
or a previous version to Liferay 6.2 or a later version, all the calendar events
that existed prior to 6.2 will still be available after your upgrade. After
following
[these](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/upgrading-liferay-liferay-portal-6-2-user-guide-18-en)
steps for upgrading Liferay, all you need to do to access previously added
events is deploy the new Calendar portlet. You can browse through the new
Calendar to confirm that the upgrade succeeded: you should be able to view and
edit calendar events that were added via the old calendar portlet.

The old Calendar portlet included certain calendar event types such as
appointments, birthdays, holidays, and meetings. The new Calendar portlet
doesn't include this explicit notion of event types. However, by using
categories, the upgrade process preserves the functionality provided by the old
Calendar portlet's notion of event types. When upgrading, the old calendar event
types are migrated to categories. The upgrade creates a vocabulary called
*Calendar Event Types* and adds the old event types as categories in this
vocabulary. Upon upgrading, any calendar events that had one of the old event
types receive the corresponding category from the Calendar Event Types
vocabulary instead.

Next, let's look at one of the most widely used applications provided by
Liferay: its message boards.

## Discuss, Ask, and Answer Using the Message Boards [](id=discuss-ask-and-answer-using-the-messag-liferay-portal-6-2-user-guide-08-en)

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

### General [](id=general-liferay-portal-6-2-user-guide-08-en)

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

Lastly, you can set the amount of time a post is displayed on the portlet's 
*Recent Posts* tab until it is taken away. You have options of 24 hours, 7 days, 
30 days, and 365 days. After the time has passed, the post is removed from 
*Recent Posts*. However, the post itself isn't deleted. It remains accessible 
elsewhere on the message board.

### Email From [](id=email-from-liferay-portal-6-2-user-guide-08-en)

This tab allows you to configure the name and email address from which message
board email notifications are sent. The default name and email address are those
of the default administrator account: The name is `Test Test` and the email
address is `test@liferay.com`. Make sure to update this email address to a valid
one that can be dedicated to notifications.

### Message Added Email [](id=message-added-email-liferay-portal-6-2-user-guide-08-en)

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

### Message Updated Email [](id=message-updated-email-liferay-portal-6-2-user-guide-08-en)

The Message Updated Email tab is identical to the Message Added Email tab,
except it defines the email message that users receive whenever a topic is
updated.

### Thread Priorities [](id=thread-priorities-liferay-portal-6-2-user-guide-08-en)

You can define custom priorities for message threads on this tab. These allow
administrators to tag certain threads with certain priorities in order to
highlight them for users. By default, three priorities are defined: Urgent,
Sticky, and Announcement. To define a thread priority, enter its name, a URL to
the image icon that represents it, and a priority number which denotes the
order in which that priority should appear.

There is also a field on this form that allows you to select a localized
language for your priorities. If you need to do this, you can select the
language from the selection box.

### User Ranks [](id=user-ranks-liferay-portal-6-2-user-guide-08-en)

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

### RSS [](id=rss-liferay-portal-6-2-user-guide-08-en)

Message board threads can be published as RSS feeds. This tab allows you to
enable/disale RSS subscriptions and define how the feeds are generated.

**Maximum Items to Display:** lets you select the number of items to display in
the feed.

**Display Style:** lets you select the style. You can publish the full content,
an abstract, or just the title of a thread.

**Format:** allows you to choose the format: RSS 1.0, RSS 2.0, or Atom 1.0.

### Permissions [](id=permissions-liferay-portal-6-2-user-guide-08-en-0)

The default page that the Message Boards portlet displays has three buttons on
it. Click the one labeled *Permissions*. This allows you to define which roles
have the ability to add a category of threads or to ban abusive users from the
message boards. Select the roles and permissions you want to configure and then
click *Save*.

### Adding Categories [](id=adding-categories-liferay-portal-6-2-user-guide-08-en)

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

### User Subscriptions and Mailing Lists [](id=user-subscriptions-and-mailing-lists-liferay-portal-6-2-user-guide-08-en)

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

### Managing User Subscriptions with the Message Boards Subscription Manager [](id=managing-user-subscriptions-with-the-me-liferay-portal-6-2-user-guide-08-en)

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

### Using the Message Boards [](id=using-the-message-boards-liferay-portal-6-2-user-guide-08-en)

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

### Posting New Threads [](id=posting-new-threads-liferay-portal-6-2-user-guide-08-en)

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

### Message Board Administrative Functions [](id=message-board-administrative-functions-liferay-portal-6-2-user-guide-08-en)

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

#### Moving Threads [](id=moving-threads-liferay-portal-6-2-user-guide-08-en)

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

#### Deleting Threads [](id=deleting-threads-liferay-portal-6-2-user-guide-08-en)

Users with administrative access to the message boards can delete threads.
Sometimes users begin discussing topics that are inappropriate or that reveal
confidential information. In this case, you can simply delete the thread from
the message boards. This is easy to do. First, view the list of threads. Next to
every thread is an *Actions* button. Click *Actions &rarr; Move to the Recycle
Bin* to delete the thread. This does not prevent users from re-posting the
information, so you may need to be vigilant in deleting threads or consider the
next option.

#### Banning Users [](id=banning-users-liferay-portal-6-2-user-guide-08-en)

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

#### Splitting Threads [](id=splitting-threads-liferay-portal-6-2-user-guide-08-en)

Sometimes a thread will go on for a while and the discussion completely changes
into something else. In this case, you can split the thread where the
discussion diverges and create a whole new thread for the new topic.
Administrative users will see a *Split Thread* link on each post. To split the
thread, click the link. You will be brought to a form which allows you to add
an explanation post to the split thread. Click *OK* to split the thread.

#### Editing Posts [](id=editing-posts-liferay-portal-6-2-user-guide-08-en)

Administrative users can edit anyone's posts, not just their own. Sometimes
users will post links to copyrighted material or unsuitable pictures. You can
edit these posts, which allows you to redact information that should not be
posted or to censor profanity that is not allowed on your message boards.

#### Permissions [](id=permissions-liferay-portal-6-2-user-guide-08-en-1)

Permissions can be set not only on threads, but also on individual posts. You
can choose to limit a particular conversation or a post to only a select group
of people. To do this, click the *Permissions* link on the post and then select
among the *Delete, Permissions, Subscribe, Update, and View* permissions for
the particular role to which you want to grant particular access. This function
can be used, for example, to allow some privileged users to post on a certain
thread, while others are only allowed to view it. Other combinations of the
above permissions are also possible. Next, let's discuss Liferay's Wiki
portlet.

## Working together with the Wiki [](id=working-together-with-the-wiki-liferay-portal-6-2-user-guide-08-en)

Liferay's Wiki portlet, like the Message Boards portlet, is a full-featured
wiki application which has all of the features you would expect in a state of
the art wiki. Again, though, it has the benefit of being able to take advantage
of all of the features of the Liferay platform. As such, it is completely
integrated with Liferay's user management, tagging, and security features.

So, what is a wiki? Basically, a wiki is an application which allows users to
collaboratively build a repository of information. There are, of course, many
implementations of this idea, the most famous of which is Wikipedia. Wikipedia
is a full online encyclopedia developed collaboratively by users from all over
the world, using a wiki. Another example would be Liferay's wiki, which is used
for collaborative documentation of the Community Edition of Liferay Portal.

A wiki application allows users to create and edit documents and link them to
each other. To accomplish this, a special form of markup is used which is
sometimes called wikitext. Unfortunately, the proliferation of many different
wiki applications resulted in slightly different syntax for wikitext in the
various products, as each new wiki tried to focus on new features that other
wikis did not have. For that reason, a project called WikiCreole was started.
This project resulted in the release of WikiCreole 1.0 in 2007, which is an
attempt to define a standard wiki markup that all wikis can support.

Rather than define another wikitext syntax, Liferay's Wiki portlet supports
WikiCreole as its syntax. This syntax is a best-of-breed wiki syntax and should
be familiar to users of other wikis. The portlet provides a handy cheat sheet
for the syntax on the page editing form, with a link to the full documentation
if you wish to use some of WikiCreole's advanced features.

### Getting Started with the Liferay Wiki [](id=getting-started-with-the-liferay-wiki-liferay-portal-6-2-user-guide-08-en)

The Wiki portlet works just like the other portlets developed by Liferay. Add
the portlet to a page using the *Add &rarr; Applications* menu and then click
*Configuration* in the portlet's *Options* menu in the Wiki portlet's title bar.
You'll see some options are likely to be familiar to you by now such as sharing
the application with websites, Facebook, Google Gadgets, etc. You will also
notice that the communication tab has some additional options not seen in the
other portlets.

![Figure 8.23: For each of the public parameters in this portlet, it is possible to ignore the values coming from other portlets or to read the value from another parameter.](../../images/05-wiki-configuration.png)

The communication tab of the configuration window allows you to configure
communication across portlets, using predefined public render parameters. From
here you can modify six public render parameters: categoryId, nodeId, nodeName,
resetCur, tag, and title. For each parameter you can:

-   Ignore the values for this parameter that come from other portlets. For
    example, the wiki portlet can be used along with the tags navigation
portlet. When a user clicks on a tag in the tags navigation portlet, the wiki
shows a list of pages with that tag. In some cases an administrator may want
the wiki portlet to always show the front page independently of any tag
navigation done through other portlets. This can be achieved by checking the
Ignore check box so that the values of the parameter coming from those other
portlets are ignored.

-   Read the value of a parameter from another portlet. This is an advanced but
    very powerful option that allows portlets to communicate without
configuring it beforehand. For example, imagine that the wiki portlet is used
to publish information about certain countries. Imagine further that a custom
portlet that allows browsing countries for administrative reasons was written
and placed on the same page. We could associate to this second portlet a public
render parameter called *country* to designate the name of the country. Using
this procedure, we can cause the wiki to show the information from the country
being browsed through in the other portlet. You can do this here for the wiki
by setting the value for the title parameter to be read from the country
parameter of the other portlet.

Once you have set the options the way you want them, click *Save*.

### Managing Wikis [](id=managing-wikis-liferay-portal-6-2-user-guide-08-en)

The Wiki portlet can contain many wikis. By default, it contains only one,
called *Main*. To manage Wikis, navigate to your site's *Site Administration
&rarr; Content* page and select *Wiki*. This page allows you to add, modify, and
delete wikis. The Main wiki has already been added for you.

At the top of this screen is a *Permissions* button. Clicking this allows you
to define which roles have access to create wikis. If you have created a
specific role for creating wikis, you can click the box in the *Add Node*
column and then click *Submit*, and that role will have access to create new
wikis in this portlet.

Clicking the *Add Wiki* button prompts you to enter a name and description for
the new wiki. You can also set up some default permissions. When you create a
new wiki, it appears in a list at the top of the main page of the Wiki portlet.

Next to each wiki in the list of wiki nodes is an *Actions* button. This button
contains several options:

**Edit:** lets you edit the name and description of the wiki.

**Permissions:** lets you define what roles can add attachments to wiki pages,
add pages to the wiki, delete pages, import pages to the wiki, set permissions
on the wiki, subscribe to the wiki, update existing pages, and view the wiki.

**Import Pages:** allows you to import data from other wikis. This lets you
migrate off of another wiki which you may be using and use the Liferay wiki
instead. You might wish to do this if you are migrating your site from a set of
disparate applications (i.e. a separate forum, a separate wiki, a separate
content management system) to Liferay, which provides all of these features.
Currently, MediaWiki is the only wiki that is supported, but others are likely
to be supported in the future.

**RSS:** opens a new page where you can subscribe to an RSS feed using Live
Bookmarks, Yahoo, or a chosen application from your machine.

**Subscribe:** allows you to subscribe to a wiki node, and any time a page is
added or updated Liferay will send you an email informing you what happened.

**Move to the Recycle Bin:** moves the wiki node to the recycle bin.

**View Removed Attachments:** displays attachments that have been removed from
the wiki node.

To go back to your wiki, navigate back to the Wiki portlet you added to your
page. Then click the *Options &rarr; Configuration* button, which contains
several other options which you may have seen on other portlets.

The *Display Settings* tab gives you several options for how the wiki should be
displayed. *Enable Related Assets*, *Enable Page Ratings*, *Enable Comments*,
and *Enable Comment Ratings* are similar to the same options in other portlets.
They give you the ability to set how you want users to interact with wiki
documents: a little, a lot, or not at all. The *Display Template* drop-down
option lets you choose the application display template for your portlet. Below
this, you can set which wikis are visible in the Wiki portlet by default and
which are hidden. You might host two wikis on a given site, exposing one to the
public and keeping the other private for site members.

The *Email From*, *Page Added Email*, and *Page Updated Email* tabs are similar
to the ones for notification email settings for other portlets, allowing you to
customize who wiki emails come from and the format and text of the email that
is sent when a page is added or updated.

Finally, the Wiki portlet also supports RSS feeds as the other collaboration
portlets do, and you can configure its options in the *RSS* tab.

### Adding and Editing Wiki Pages [](id=adding-and-editing-wiki-pages-liferay-portal-6-2-user-guide-08-en)

By default, there is one page added to your wiki, called *FrontPage*. To get
started adding data to your wiki, click the *Edit* link. You will be brought to
a blank editing page.

![Figure 8.24: By clicking *Edit*, you can create/modify your wiki content.](../../images/05-editing-wiki-page.png)

You can now begin to add content to the page. Notice that there is a very
convenient *Show Syntax Help* link which can help with the wiki syntax. You can
use this syntax to format your wiki pages. Consider, for example, the following
wiki document:

== Welcome to Our Wiki! ==

This is our new wiki, which should allow us to collaborate on documentation.
Feel free to add pages showing people how to do stuff.  Below are links to some
sections that have already been added.

[[Introduction]]

[[Getting Started]]

[[Configuration]]

[[Development]]

[[Support]]

[[Community]]

This would produce the following wiki page:

![Figure 8.25: By using the syntax help guide, you can format your wiki headings and text.](../../images/05-wiki-front-page.png)

This adds a simple heading, a paragraph of text, and several links to the page.
Since the pages behind these links have not been created yet, clicking one of
those links takes you to an editing screen to create the page. This editing
screen looks just like the one you used previously when you wrote the front
page. Liferay displays a notice at the top of the page stating that the page
does not exist yet, and that you are creating it right now. As you can see, it
is very easy to create wiki pages. All you have to do is create a link from an
existing page. Note that at the top of the screen you can select from the
Creole wiki format and the HTML editor that comes with Liferay. We recommend
that you stick with the Creole format, as it allows for a much cleaner
separation of content and code. If you want all of your users to use the Creole
format, you can disable the HTML format using the `portal-ext.properties` file.
See chapter 14 for details about how to configure this.

At the bottom of the page editing screen, you can select *Categorization* to add
tags. The tags link your wiki to categories. You can create categories using the
Site Administration page, in the *Content &rarr; Categories* section. Categories
are hierarchical lists of headings under which you can create wiki pages. This
allows you to organize your content in a more formal fashion.

### Page Details [](id=page-details-liferay-portal-6-2-user-guide-08-en)

When viewing a page, you can view its details by clicking the *Details* link
which appears in the top right of the page. This allows you to view many
properties of the page. There are several tabs which organize all of the
details into convenient categories.

#### Details [](id=details-liferay-portal-6-2-user-guide-08-en)

The Details tab shows various statistics about the page, and also allows you to
perform some actions on the page.

**Title:** displays the title of the page.

**Format:** displays the format for the page -- either Creole, HTML, or
MediaWiki.

**Latest Version:** displays the latest version of the page. The wiki portlet
automatically keeps track of page versions whenever a page has been edited.

**Created By:** displays the user who created the page.

**Last Changed By:** displays the user who last modified the page.

**Attachments:** displays the number of attachments to the page.

**Convert To:** offers different conversion formats for the wiki page: DOC, ODT,
PDF, RTF, SXW, and TXT.

**RSS Subscription:** displays links which allow you to subscribe to the page
as an RSS feed in three formats: RSS 1.0, RSS 2.0, and Atom 1.0.

**Email Subscription:** contains links allowing you to subscribe to the entire
wiki or just to this page.

**Advanced Actions:** contains links allowing you to modify the permissions on
the page, make a copy of the page, move (rename) the page, or move the page to
the recycle bin.

#### History [](id=history-liferay-portal-6-2-user-guide-08-en)

This tab shows a list of all of the versions of the wiki page since it was
created. You can revert a page back to a previous state and you can also
compare the differences between versions by selecting the versions and then
clicking the *Compare Versions* button.

#### Incoming/Outgoing Links [](id=incoming-outgoing-links-liferay-portal-6-2-user-guide-08-en)

The next two tabs are for incoming and outgoing links. These are wiki links to
and from the page. You can use this tab to examine how this page links to other
pages and how other pages link back to this page.

#### Attachments [](id=attachments-liferay-portal-6-2-user-guide-08-en)

The last tab is for attachments. You can attach any file to the wiki. This is
mostly used to attach images to wiki articles which can then be referenced in
the text. Referencing them using the proper WikiCreole syntax renders the image
inline, which is a nice way to include illustrations in your wiki documents.

### Navigating in the Wiki Portlet [](id=navigating-in-the-wiki-portlet-liferay-portal-6-2-user-guide-08-en)

At the top of the portlet is a list of links which allow you to navigate around
the wiki. Simply click on the wiki's name to begin browsing that wiki. After
this is a set of navigation links:

**Recent Changes:** takes you to a page which shows all of the recently updated
pages.

**All Pages:** takes you to a flat, alphabetical list of all pages currently
stored in the wiki.

**Orphan Pages:** takes you to a list of pages that have no links to them. This
can happen if you take a link out of a wiki page in an edit without realizing
it's the only link to a certain page. This area allows you to review wiki pages
that are orphaned in this way so that you can re-link to them or delete them
from the wiki if they are no longer relevant.

**Draft Pages:** takes you to a list of pages which have not yet been
published. Users can edit pages and save their changes as drafts. They can come
back later to finish their page changes and publish them once they have been
approved.

**Search:** allows you to a term here and click the *Search* button to search
for items in the wiki. If the search term is not found, a link will be
displayed which allows you to create a new wiki page on the topic for which you
searched. 

The Wiki portlet is another full-featured Liferay application with all of the
features you expect from a state of the art wiki. Next, we'll look at how
Liferay handles live chat.

## Find out what others think or do using Polls [](id=find-out-what-others-think-or-do-using--liferay-portal-6-2-user-guide-08-en)

How well do you know your users? Do you ever wonder what they're thinking? Is
using your site easy for them? How do they feel about the hot-button issues of
the day? Do they prefer dogs over cats? What about the new policy that
management wants to implement? What's their favorite ice cream flavor? When you
use Liferay's Polls feature you can find out the answer to these and other
questions that should help you better understand your users. 

There are two portlets involved in making and displaying a poll: the *Polls*
portlet, which is accessed through the Site Administration page, and the *Polls
Display* portlet, which can be added to any page in the portal.

The Polls portlet helps you set up the poll question and the possible answers
users can select. The Polls Display portlet is an instanceable portlet that
lets you select which poll to display, and is the portlet you put on the page
so users can vote.

The Polls portlet allows users and administrators to create multiple choice
polls that keep track of the votes and display results on the page. Many
separate polls can be managed; a separate portlet called Polls Display can be
configured to display a specific poll's questions and results.

The Polls Display Portlet allows users to vote for a specific poll's questions
and see the results. Questions must be created from the Polls portlet on the
Site Administration page. You can display one question at a time or you can
combine several questions inside a nested portlet to create a survey.

We'll begin by creating a poll.

### Creating a Poll [](id=creating-a-poll-liferay-portal-6-2-user-guide-08-en)

On your site's Site Administration page, navigate to the *Polls* link under
Content. Click the *Add Question* button. A form appears that allows you to fill
out all the information for your poll.

![Figure 8.26: Besides the Title and the Polls Question, you must enter data for each of the Choices fields when creating a new poll.](../../images/polls-add-new-question.png)

**Title:** Enter the name of the poll question. 

**Polls Question:** Enter the text of the poll question. 

**Expiration Date:** Enter the date and time you want the poll to expire. 

**Choices:** Enter at least two answer options for the poll question. 

**Add Choice:** Enter additional answer options for the poll question. 

**Permissions:** Manage who can view and edit the poll. 

When you have finished creating your poll, click *Save*, and it is added to the
Polls portlet. 

As more polls are created in the Control Panel, they become accessible through
the Polls Display portlet until they are either deleted or they expire. You can
set an expiration date for a poll by selecting the day and time in the Add Poll
form or in the New Question form. The default is set to *Never Expire*. 

When a poll expires, users can't enter votes any more, but if a Polls Display
portlet is still publishing it, the poll results are displayed on the page. To
remove an expired poll from a page, remove the Poll Display portlet or
configure it to show another poll question. See the section below for more
details about the Polls Display portlet. 

*Permissions* can be set on individual polls as they are set elsewhere in
Liferay Portal. Permissions can be used, for example, to allow some privileged
users to vote on a certain poll question, while others can only view it. For
further information about permissions, please see chapters 15 and 16. 

As you can see, creating a poll is fairly straightforward. Next, let's complete
the two-step process and put your poll on a page.

### Adding a Poll to a Page [](id=adding-a-poll-to-a-page-liferay-portal-6-2-user-guide-08-en)

Now that you have created your poll question, it's time to present it to your
users. Navigate to your site and add the Polls Display portlet to a page. It is
available from the *Add* &rarr; *Applications* &rarr; *Content Management* menu.

The Polls Display portlet may look strange when it first appears on your page.
That's because it's not configured. Before visitors to your site can use the
poll, they must be able to access it. Click on the link labeled *Please
configure this portlet to make it visible to all users, and a dialog box like
the one below appears.

![Figure 8.27: In the initial configuration of the Polls Display portlet, the Question field will remain blank until you select the appropriate poll question. ](../../images/polls-display-config.png)

Under the Setup tab is a menu option labeled *Question*. Selecting this option
displays the name of the poll you created. Choose it, click *Save*, and it is
displayed on the page. That, in a nutshell, is how you create a poll, but there
is another way to add a question to the Polls Display portlet.

Start by navigating to your site and placing the Polls Display portlet on a
page. Using the icons in the lower left of the portlet, choose the *Add
Question* button. A new form appears that lets you create another question.
When you are done filling out the form, click *Save* and you new poll appears
on the page.

Once the poll question has been successfully placed on the page, you can
perform other tasks by using the icons in the lower left corner of the portlet.
Besides adding questions, you can also edit the currently selected question or
select existing questions.

![Figure 8.28: These three buttons, highlighted in red, allow you to manage the configuration of the poll. Notice this poll has expired.](../../images/polls-config-buttons.png)

**Edit Question:** Displays a similar dialog box to the one used to create the
poll. 

**Select Question:** Displays the same dialog box as Configuration, allowing
you to choose different questions from the drop-down menu. 

**Add Question:** Allows you to create a new question. 

You can also manage the Polls Display portlet by clicking the gear symbol in
the upper right corner of the portlet's title bar. Now let's see the poll
results.

### Viewing the Poll Results [](id=viewing-the-poll-results-liferay-portal-6-2-user-guide-08-en)

When you create a poll question, it appears in a list in the *Content* section
of Site Administration. After users vote in the poll, the data is collected
here. If you select it, the name and the question, as well as a breakdown of the
poll results appears, including percentages and total number of votes per answer
and the total number of votes cast.

![Figure 8.29: Selecting a poll in the Polls portlet allows you to see all the information related to the poll results.](../../images/polls-results.png)

Below this is an item called *Charts*. This option shows the poll results
represented in various graphs. The graphs are *Area*, *Horizontal Bar*, *Line*,
*Pie*, and *Vertical Bar*.

![Figure 8.30: This is what the vertical bar graph for the Lunar Resort poll results looks like.](../../images/polls-results-vertical-bar.png)

There is also a listing of the users who voted in your poll, how they voted,
and a time/date stamp of when their votes were cast. Registered users are
represented by their screen name while Guest users are represented by a number.

With Liferay Polls you can do many things. You can ask users very specific
questions or you can use Polls to create a little fun for your community. As
with most things in Liferay, you are only limited by your imagination. Next,
let's learn how to use Liferay's Announcements portlet.

## Sending Alerts and Announcements in Your Portal [](id=sending-alerts-and-announcements-in-you-liferay-portal-6-2-user-guide-08-en)

Suppose you're running a portal with lots of users. Maybe you have thousands or
tens of thousands of users (or more!). Wouldn't it be nice to have an easy way
to periodically communicate important information to them? And wouldn't it be
even nicer to have easy ways to periodically send information to specific groups
of users? For example, maybe you'd like to send a reminder about a new policy to
all of the site administrators on your portal. Or maybe you'd like to send a
reminder to all the members of a certain site to submit an answer to this week's
poll question. Liferay provides two portlets that meet this need: the Alerts
portlet and the Announcements portlet.

![Figure 8.31: The Alerts portlet provides administrators with an easy way to communicate important information to appropriate groups of users.](../../images/alerts-portlet.png)

The Alerts and Announcements portlets let you broadcast important information to
different groups of users. Each alert or announcement can be created in a
specific scope so that you can manage which announcements are sent to which
users. Furthermore, each portal user can configure how they'd like to receive
announcements: click on *[User Name]* &rarr; *My Account* from the Dockbar and
then on *Announcements* in the Miscellaneous category. There's a menu for
customizing the delivery options for alerts and announcements. You can select a
different configuration for each type of alert or announcement: General, News,
or Test. Each kind of announcement that's broadcast to a user is viewable from
the Alerts or Announcements portlet, of course. But alerts and announcements can
also be sent via email or SMS (Short Message Service, i.e., text).

You can also create roles in Liferay to assign to users that should have access
to announcing general announcements. For instance, if you'd like an employee in
your site to have strict control over what is announced, you can assign them to
an Announcements role. To create a simple Announcement role, you'll need to
navigate to the *Control Panel* &rarr; *Roles* &rarr; *Add* &rarr; *Regular
Role*. Specify the name of your role as *Announcements*, click *Save*, and then
select the *Define Permissions* tab. For this particular role, you'll need to
grant two permissions:

- *Control Panel &rarr; General Permissions &rarr; Add General Announcements*
- *Site Administration &rarr; Applications &rarr; Announcements &rarr; Add
Entry*

You'll need to click *Save* after selecting each permission. Now you have a
simple Announcements role that can add an entry to the Announcements portlet and
add general announcements to your site! Of course, you can add additional
permissions to give the Announcements role more access, if you prefer.

![Figure 8.32: The Annoucements portlet displays a list of annoucements to regular users. It also provides administrators with an interface for managing and adding entries.](../../images/announcements-portlet.png)

Let's look at how to create announcements in the Announcements portlet. When you
view the Announcements portlet as an administrator, you'll see two tabs: Entries
and Manage Entries. Non-administrators only see the Entries tab which displays a
list of announcements. To add a new announcement, click on *Manage Entries* and
then select a *Distribution Scope*. The distribution scope determines the group
of users to which your announcement is sent. Announcements created in the
General distribution scope are sent to everyone. If the distribution scope is
set a particular site or role, only members of that site or role receive the
announcements.

Once you've selected a distribution scope, you'll see a list of all the
announcements that have already been created in that scope. You can click the
*Actions* button next to an announcement to edit or delete it. Click *Add* to
create a new announcement. In addition to the Distribution scope, you can
provide the following information:

**Title:** The title of the announcement. When the announcement is displayed in
the portlet, the full title always appears in bold. If a URL was entered, the
title serves as a link to the site specified in the URL field.

**URL:** The URL is optional. If entered, it must be a valid URL, starting with
*http://*. For example, an announcement about a news story could include a link
to the news article.

**Content:** The body of the announcement. You can use the familiar CK editor to
write the body of your announcement. Use the *Source* button to switch between
the editor view and the HTML view.

**Type:** Can be *General*, *News*, or *Test*. Each user can specify a different
delivery mechanism for each type of announcement. For example, a user can
specify that she'd like to receive general announcements via email and text,
news announcement via email only, and no special delivery mechanism for test
announcements. Each user can customize their delivery options by navigating to
*[User Name]* &rarr; *My Account* from the Dockbar and then clicking on
*Announcements* in the Miscellaneous category.

**Priority:** Can be either *Normal* or *Important*.

**Display Date:** This determines when the announcement will be sent to users
and will appear in the Announcements portlet. By default, the *Display
Immediately* box is flagged. This sets the display date to be equal to the
creation date. Unflag this box if you'd like to enter a custom display date. For
example, administrators can create announcements that won't be displayed until a
specified future date. This date can be days, weeks, months, or years in the
future. Once the *Display Immediately* box is unflagged, clicking on the Display
Date field opens the date-picker widget.

**Expiration Date:** This determines when the announcement expires. Once an
announcement expires, it's no longer displayed by the Announcements portlet to
any users. Clicking on the Expiration Date field opens the date-picker widget.

The Alerts portlet works the same way as the announcements portlet. It's a
different portlet because it's designed to be be used only for important
messages or message that may require users to take some action. The Alerts
portlet only displays alerts and the Announcements portlet only displays alerts.
The Alerts portlet displays alerts with a bold red box around each alert
message to draw attention to the displayed entries. Site administrators can
place the Alerts and Announcement portlets on different pages if they'd like to
separate important alerts from more mundane announcements.

Next, let's see what you can do with Liferay's Chat feature.

## Staying in touch with the Chat [](id=staying-in-touch-with-the-chat-liferay-portal-6-2-user-guide-08-en)

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

### Filtering Available Users [](id=filtering-available-users-liferay-portal-6-2-user-guide-08-en)

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

### Jabber Server Integration [](id=jabber-server-integration-liferay-portal-6-2-user-guide-08-en)

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

#### Installation Steps [](id=installation-steps-liferay-portal-6-2-user-guide-08-en)

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

#### Single Sign On [](id=single-sign-on-liferay-portal-6-2-user-guide-08-en)

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

## Integrating your email with Liferay Mail [](id=integrating-your-email-with-liferay-mai-liferay-portal-6-2-user-guide-08-en)

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

## Summary [](id=summary-liferay-portal-6-2-user-guide-08-en)

We have explored many of the portlets in Liferay's collaboration suite. The
Blogs and Blogs Aggregation portlets can be used to manage shared blogs or blogs
belonging to a group of people at once. These portlets have all the features you
would want in a blog, including rich text editing, links to news aggregators,
tags, RSS feeds, and more.

The Calendar portlet, likewise, can be used to manage a shared calendar or a
group calendar. It includes features for events, event notification, repeatable
events, and import and export to and from the standard iCalendar format.

Discussion becomes easy with Liferay's Message Boards portlet. This portlet can
be used to manage heavily trafficked discussion forums with ease. It inherits
all of the security features of the Liferay platform and includes
administrative functions for thread priorities, moving threads, nested
discussion categories, banning users, and more.

Liferay's Wiki portlet is a state of the art wiki application that users can
make use of to collaborate on web pages. Again, it inherits the strengths of
the Liferay platform in the form of security, interface, and search. You can
use the wiki portlet to manage several wiki nodes or use many wiki portlets to
manage one node each.

The Polls portlet is a fun way to interact with users of your site to get an
understanding of what they're thinking at any given time. It allows you to
create multiple choice polls that keep track of the votes and display results
on the page. You can view these results in a number of ways, including charts.

The Announcements portlet allows administrators to create and manage
announcements and allows users to view the announcements. The announcements can
be sent via email or text or they can simply be displayed on the portal. The
Announcements portlet provides a good way for administrators to communicate to
different groups of users since each announcement can be scoped to a specific
site or role.

Liferay provides a chat solution for your portal that's very easy to use. It
allows logged-in users to see who else is logged in to the portal and view
their status. Users can go invisible if they don't want others to know that
they're online. Users can chat with each other via instant messages. You can
also set up a Jabber chat server and configure Liferay to use it; this allows
users who have logged in to your portal via their browsers to chat with users
using traditional desktop clients.

Integrating mail with your portal is easy with the Mail portlet. You can add as
many custom or Gmail mail accounts as you wish, and this portlet can keep them
all organized in one place, together with the rest of the things Liferay is
aggregating for you.

Liferay's collaboration platform is a full suite of integrated applications
that empower users to work together. You can use them to great effect to
enhance your portal and to build a vibrant, active community. 
