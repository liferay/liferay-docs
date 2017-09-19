# Managing Events and Calendar Resources with Liferay's Calendar Portlet [](id=managing-events-and-calendar-resources-with-liferays-c)

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

## Configuring the Calendar Portlet [](id=configuring-the-calendar-portlet)

Once you've added the new Calendar portlet to a page, open its configuration
dialog box by clicking on the gear icon in the portlet's title bar and
selecting *Configuration*. The Setup tab provides two sub-tabs of configurable
settings: *User Settings* and *RSS*.

![Figure 9.8: The Setup &rarr; User Settings tab provides several new configuration options that weren't available from the old Calendar portlet.](../../images/new-calendar-configuration.png)

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

Next, let's look at how to use the new Calendar portlet.

## Using the Calendar Portlet [](id=using-the-calendar-portlet)

The first thing you'll notice about Liferay's new Calendar portlet is its
revamped look and feel. There's a monthly mini-calendar which provides you with
an overview of upcoming events. There's also a larger, more detailed calendar
area. You can set the large calendar area to display a *Day*, *Week*, or *Month*
view. The *Day* view displays a day as a single column, divided into hours and
half-hours. The *Week* view is similar but displays seven days as seven columns,
divided into hours and half-hours. The month view displays a traditional
calendar view with days represented as boxes. In the previous section, we saw
how to configure the default display view of the calendar.

### Adding New Calendars [](id=adding-new-calendars)

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

![Figure 9.9: You have options to add or manage calendars. Also, you can toggle the colored boxes beside your calendars to show/hide your planned events.](../../images/new-calendar-manage-calendars.png)

When adding a calendar, you can also specify whether or not to enable comments
and ratings for your calendar's events. Comments and ratings can be
enabled/disabled on a per calendar basis. They are disabled by default.
Additionally, you can specify a color for your calendar. Events created in the
new calendar will default to the color you choose.

You can edit a calendar to change its name, description, or color. You can also
change the calendar's default calendar status and flag or unflag the *Enable
Comments* and *Enable Ratings* checkboxes to enable or disable comments and
ratings for a calendar's events.

### Adding Events to a Calendar [](id=adding-events-to-a-calendar)

It's very easy to add events to a calendar: just click on any day of the
calendar and you'll see an event creation popup appear. If you've selected the
*Day* or *Month* view, you can click on the specific time when your event
begins.

![Figure 9.10: When you click anywhere on the calendar, you'll see the event creation popup appear. Click *Edit* to specify details for your event.](../../images/new-calendar-event-popup.png)

In the new event popup, you can select the calendar in which you'd like to
create the new event. This is useful since sites and users can have multiple
calendars. You can click *Save* to create the event right away or you can click
*Edit* to specify more event information.

![Figure 9.11: You can specify event details such as the event title, start date, end date, description, location, and more.](../../images/new-calendar-event-details.png)

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

![Figure 9.12: The *Repeat* box allows you to specify whether an events repeats daily, weekly, monthly, or yearly, how often it repeats, and when (or if) it ends.](../../images/new-calendar-event-repeat.png)

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

In the Invitations collapsible section, you can invite users, sites, or
other calendar resources to an event. To invite a user, site, or resource,
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

![Figure 9.13: You can invite users, organizations, or other calendar resources to your event and can check their availability in a calendar view.](../../images/new-calendar-event-invitations.png)

The Reminders collapsible section lets you specify up to two
times when event reminder notifications will be sent via email. For example, you
might like event notifications to be send one day and one hour before your
event. Email is currently the only supported event notification type. To avoid
confusion, the event time in notification emails is shown in the user's
time zone, and the time zone is also displayed. Of course, Liferay can only
calculate this properly if both the portal's and the user's time zones are set
correctly. Users should set this for themselves when they create accounts. To
set the portal's time zone, see the section
[Configuring Additional Portal Settings](/discover/portal/-/knowledge_base/6-2/configuring-additional-portal-settings). 

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

![Figure 9.14: The *Calendar Settings* &rarr; *Notification Templates* tab lets you customize the email templates for emails that are automatically sent out to remind users of upcoming events or invite users to new events.](../../images/new-calendar-configuration2.png)

To respond to an event invitation, you can click *Accept*, *Maybe*, or *Decline*
when viewing the event in the Calendar portlet. The default event invitation
notification emails contain links to their corresponding events. This allows
users easy access to events in the Calendar portlet so they can respond to event
invitations.

### Adding and Using Calendar Resources [](id=adding-and-using-calendar-resources)

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

![Figure 9.15: Click on the *Resources* tab of the portlet to view, edit, add, or delete resources.](../../images/new-calendar-resources.png)

Click on the *Add Resource* button to add a new calendar resource. You'll be
able to enter a name and description for your resource, specify whether or not
it should be active, apply tags and categories, and configure its permissions.

Once you've created a calendar resource, you can click on the *Actions* button
next to it and select one of the following four options: *Edit*, *Permissions*,
*Delete*, or *View Calendars*. The *View Calendars* option exists since calendar
resources, like sites and users, can have more than one calendar associated with
them. Click *Actions* &rarr; *View Calendars* and then click on *Add Calendar*
to add a new calendar in which the selected calendar resource appears.

![Figure 9.16: Click on *Add Calendar* to add a new calendar in which the selected calendar resource appears.](../../images/new-calendar-resource-calendars2.png)

Liferay's new Calendar portlet provides an easy-to-use scheduling tool. Sites
and users can have multiple calendars, calendar events can include calendar
resources and calendar resources have their own schedules of availability. We
hope this flexible system meets the needs of your organization.

### Exporting and Importing Calendar Data [](id=exporting-and-importing-calendar-data)

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

## Upgrading the Calendar Portlet [](id=upgrading-the-calendar-portlet)

In Liferay 6.1 and previous versions, an older version of the Calendar portlet
was included as one of the core portlets. If you're upgrading from Liferay 6.1
or a previous version to Liferay 6.2 or a later version, all the calendar events
that existed prior to 6.2 will still be available after your upgrade. After
following [these](/discover/deployment/-/knowledge_base/6-2/upgrading-liferay)
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
