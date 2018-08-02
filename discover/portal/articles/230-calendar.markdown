# Using the Calendar [](id=using-the-calendar) 

The Calendar widget is a tool for storing and sharing scheduled events. It can
be used as either a personal planner for individual users, as shared calendar
for an entire site, or both. It can be used to create multiple calendars for
a single site or user, to overlay the events stored in multiple calendars for
simultaneous view, to send email reminders to users, and more.

+$$$

**Note:** The calendar supports social activities. Whenever a calendar event
is added or updated, a corresponding social activity notification is created. If
the event was added or updated in a calendar that the current user has
permission to view, the social activity will be viewable in the Activities
widget. 

$$$

## Configuring the Calendar[](id=configuring-the-calendar-portlet)

Once you've added the Calendar widget to a page, go to
![Options](../images/icon-app.options.png) in the widget's header and click
*Configuration*.<!--deleted references to tabs because there isn't any material
here on the Display Settings and RSS tabs -NR-->

![Figure 1: The Setup &rarr; User Settings tab provides the options you need to get started quickly.](../images/new-calendar-configuration.png)
From the *User Settings* tab, you can customize your calendar's default
view and settings. 

**Time Format:** Choose *AM/PM* or to *24 Hour*. *AM/PM* is the default; with
this time format, times such as 8AM or 11PM are displayed. With the *24 Hour*
time format, these times are displayed as 08:00 and 23:00.

**Default Duration:** Choose an event duration. When you add a new event
to the calendar, the time you set here specifies how long events last by
default.

**Default View:** Choose *Day*, *Week*, *Month* or *Agenda*. This sets the
default for when the calendar is first displayed, but the view can be changed
by any user by clicking the appropriate button at the top-right of the widget.

**Week Starts On:** Choose *Sunday*, *Monday*, or *Saturday*. 

**Time Zone:** Either choose a particular time zone or check the *Use Global
Time Zone* box.

If you check *Use Global Time Zone*, the time displayed by the Calendar portlet
depends on whether it's being viewed by a logged-in user or a guest. If a user
is logged in to the portal, the Calendar portlet displays events using the time
zone set for the user in *User Menu* &rarr; *My Account* &rarr; *Account
Settings* &rarr; *Preferences* &rarr; *Display Settings* &rarr; *Time Zone*. If
the Calendar portlet is viewed by a guest, or by a user who is not logged in,
the Calendar portlet displays events using the time zone set by the portal
administrator in *Control Panel* &rarr; *Portal Settings* &rarr; *Display
Settings* &rarr; *Time Zone*.

Next, let's look at the widget itself.

## Using the Calendar Widget [](id=using-the-calendar-portlet)

The calendar widget displays a small monthly calendar which provides you with
an overview of upcoming events. A larger area shows a more detailed calendar
with a number of options: you can set it to to display a *Day*, *Week*, or
*Month*, or choose a more event-oriented *Agenda* setting.

![Figure 2: The default view is set in configuration, but a user can change it at any time.](../images/calendar-view.png)

Two calendars are included by default when the widget is added to a page:
a personal calendar for the current user, and a site calendar for the current
site. These are displayed in the widget's lower left. Next to each calendar is
a colored box: click on it to show/hide that calendars events in the main
viewing area.

### Adding New Calendars [](id=adding-new-calendars)

Follow these steps to create a new personal calendar:

1.  Click on the arrow to the right of the *My Calendars* header and select
    *Add Calendar* from the menu. 

2.  Fill in the *Add Calendar* form. Give the calendar a name and description,
    set a time zone, and determine if it will be your user's *default
    calendar*---the one that is shown automatically whenever the widget is
    displayed. You can also pick a color, which will color code events whenever
    multiple calendar's events are displayed at once. You can also decide
    whether to enable ratings or comments on the calendar's events, and
    configure permissions.

To edit an existing calendar instead of adding a new one, select *Manage
Calendars* from the menu in step 1.

To add or edit a site calendar, open the menu next to the header with the
site's name instead of *My Calendars*.

![Figure 3: Personal and site calendars are shown in the lower left. This image shows calendars belonging to user *Test Test* and site *Liferay DXP*.](../../images/new-calendar-manage-calendars.png)

### Adding Events to a Calendar [](id=adding-events-to-a-calendar)

Follow these steps:

1.  Click on any day in the main viewing area to open an event creation pop up.
    If you've selected the *Day* or *Week* view, you can click on the specific
    time when your event begins.

    ![Figure 3: When you click anywhere on the calendar, you'll see the event creation pop up appear. Click *Edit* to specify details for your event.](../../images/new-calendar-event-popup.png)

2.  Name your event and select and assign it to a calendar. Click *Save* to
    create the event immediately or *Edit* to enter additional information.

    ![Figure 4: You can specify event details such as the event title, start date, end date, description, location, and more.](../../images/new-calendar-event-details.png)

3.  If clicked *Edit*, fill in the *Edit Event* form. Enter a start and end
    time and enter a description. To schedule an event that will reoccur
    regularly, check the *Repeat* box and fill in the *Repeat* pop up.

    ![Figure 5: The *Repeat* box allows you to specify whether an events repeats daily, weekly, monthly, or yearly, how often it repeats, and when (or if) it ends.](../../images/new-calendar-event-repeat.png)

#### Additional Event Functions

At the bottom of the *Edit Event* form, there are several collapsed sections:
*Details*, *Invitations*, *Reminders*, *Categorization*, and related *Related
Assets*.

##### Details

In the Details collapsible section, you can change the calendar to which the
event belongs, enter a location, and configure the event's permissions.

Giving a user permission to add, delete, or update discussion allows them to
make, edit and remove comments on the event. The *Permissions* permission
allows a role to update permissions for the event.

##### Invitations

In the invitations section, you can invite users, groups, or resources (see
below for more on resources: in general, a resource is anything you might need
for an event--a conference room, a vehicle, etc.). Follow these steps:

1.  Enter the name of an invitee (user, group, or resource) in the
    *Invitations* field. Hit enter to add them to the *Pending* column.

2.  If you want, check their availability by clicking on the arrow next to the
    invitee's name and selecting *Check Availability*. This displays the
    invitee's calendar (assuming you have permission to view it).

An automated email is sent to invitees, who must navigate to the calendar
widget to respond. See below to customize the content of the invitation.

When invitees respond to the invitation, their names move to the *Accepted*,
*Declined*, or *Maybe* columns.

##### Reminders

Schedule up to two email reminders to send to attendees. Reminders translate
the time of the event into the recipients own time zone.

See below to customize the content of the reminder email.

##### Categorization

If you want, you can tag your event or assign it to a category. This causes it
to appear in appropriate search results, and allows it to be be published by an
asset publisher set to publish content assigned to its category.

##### Related Assets

You can list an asset---such as an agenda or supplementary material for
a meeting---as related to your event. Links to related assets are displayed in
the *Event Details* window.

### Customizing Email Notifications

To customize email notifications for event invitations and reminders, follow these steps:

1.  From the Calendar widget, click on the arrow next to a calendar and select
    *Calendar Settings*.

    ![Figure 6: Email templates apply to a single calendar and all its events.](../images/calendar-email-note.png)

2.  Click on the *Notification Templates* tab. Then select either the *Invite
    Email* or the *Reminder Email* sub-tab.

3.  Edit the email as desired. At the bottom of the screen is a glossary that
    specifies variables for terms that were set when you created the event. Use
    these variables to refer to event-specific information, such as then
    event's name, date or location. It's a good idea to include a link to the
    event (use the variable *[$EVENT_URL$]*) as users will need to navigate to
    the calendar widget in order to respond.

### Calendar Resources

A calendar resource can be anything you might need for an event other than the
actual attendees. For instance, a conference room, A/V equipment, or a table
reservation might all be designated as calendar resources.

Follow these steps to add a new calendar resource:

1.  Click on the *Resources* tab and click *Add Resource*.

    ![Figure 7: Resources are accessed from the tab menu at the top of the widget.](../images/calendar-resources.png)

2.  Fill in the *New Resource* form. Enter a name, give it a description and
    choose whether to set it as active. You can also tag it, assign it to
    categories, and configure its permissions. Click *Save*.

The resource has its own calendar that was generated automatically (this is how
users can check its availability when creating events). Just as with users,
however, it is possible for resources to have more than one calendar. Follow
these steps to assign a new calendar to the resource:

1.  Go to the widget's *Resources* tab, click the
    ![Options](../images/icon-actions.png) button next to the resource, and
    select *View Calendars*.

2.  Click *Add Calendar* and continue just as if you were creating a calendar
    for a user or a site.

### Exporting and Importing Calendar Data [](id=exporting-and-importing-calendar-data)

The calendar allows data to be exported or imported as LAR files. As with all
LAR files, data can only be ported between Liferay installations of the same
version. Follow these steps to export:

1.  From the calendar widget, click the
    ![Options](../images/icon-app-options.png) button  in the widget header and select
    *Export/Import*.

2.  Enter a name for the LAR file (or use the default).

    Under *Application*, choose whether to include the widget's configuration
    in the LAR.

    Under *Content*, choose how much historical data to export, and select what
    content types (calendars, resources, and events) will be included. You can
    also choose whether to include comments and ratings.

    Check the appropriate boxes to select whether to include deletions and
    permissions in the LAR.

3.  Click *Export*. When a success message displays (this may take a few
    moments) you can click on the LAR's filename to download it.

    ![Figure 8: This LAR is ready to be downloaded.](../images/calendar-lar.png)

Follow these steps to import a LAR:

1.  From the calendar widget, click the
    ![Options](../images/icon-app-options.png) button in the widget header and select
    *Export/Import*.

2.  Click the *Import* tab.

3.  Click *Choose File* or else drag-and-drop a LAR into the surrounded by
    a dotted line. Click *Continue*.

4.  Decide how much data you want to import:

    Under *Application*, check the box to import the configuration stored in the
    LAR or leave in unchecked to keep your current configuration.

    Under *Content*, decide which content types (calendars, resources, and
    events) to import, and whether to include comments and ratings.

    Choose whether to import permissions and deletions, and decide whether to
    delete your widget's existing data before the import.

5.  In the collapsible *Update Data* section, choose how data will be updated.

    **Mirroring:** The data will be imported along with a reference to its
    source. This allows data to be updated rather than duplicated if the same
    LAR is imported more than once.

    **Copy as New:** All data is imported as new entries. Repeat imports will
    produce duplicates.

6.  In the *Authorship of the Content* section, choose whether to keep the
    original author of the imported content (where available) or to list the
    current user as the author.

7. Click *Import*.
