# Using the Calendar Widget [](id=using-the-calendar-widget)

The calendar widget displays a small monthly calendar which provides you with
an overview of upcoming events. A larger area shows a more detailed calendar
with a number of options: you can set it to to display a *Day*, *Week*, or
*Month*, or choose a more event-oriented *Agenda* setting.

![Figure 2: The default view is set in configuration, but a user can change it at any time.](../../images/calendar-view.png)

Two calendars are included by default when the widget is added to a page:
a personal calendar for the current user, and a site calendar for the current
site. These are displayed in the widget's lower left. Next to each calendar is
a colored box: click on it to show/hide that calendars events in the main
viewing area.

## Adding New Calendars [](id=adding-new-calendars)

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

## Adding Events to a Calendar [](id=adding-events-to-a-calendar)

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

### Additional Event Functions

At the bottom of the *Edit Event* form, there are several collapsed sections:
*Details*, *Invitations*, *Reminders*, *Categorization*, and related *Related
Assets*.

#### Details

In the Details collapsible section, you can change the calendar to which the
event belongs, enter a location, and configure the event's permissions.

Giving a user permission to add, delete, or update discussion allows them to
make, edit and remove comments on the event. The *Permissions* permission
allows a role to update permissions for the event.

#### Invitations

In the invitations section, you can invite users, groups, or resources (see
the next tutorial for more on resources: in brief, a resource is anything you might need
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

#### Reminders

Schedule up to two email reminders to send to attendees. Reminders translate
the time of the event into the recipients own time zone.

See below to customize the content of the reminder email.

#### Categorization

If you want, you can tag your event or assign it to a category. This causes it
to appear in appropriate search results, and allows it to be be published by an
asset publisher set to publish content assigned to its category.

#### Related Assets

You can list an asset---such as an agenda or supplementary material for
a meeting---as related to your event. Links to related assets are displayed in
the *Event Details* window.

## Customizing Email Notifications

To customize email notifications for event invitations and reminders, follow these steps:

1.  From the Calendar widget, click on the arrow next to a calendar and select
    *Calendar Settings*.

    ![Figure 6: Email templates apply to a single calendar and all its events.](../../images/calendar-email-note.png)

2.  Click on the *Notification Templates* tab. Then select either the *Invite
    Email* or the *Reminder Email* sub-tab.

3.  Edit the email as desired. At the bottom of the screen is a glossary that
    specifies variables for terms that were set when you created the event. Use
    these variables to refer to event-specific information, such as then
    event's name, date or location. It's a good idea to include a link to the
    event (use the variable *[$EVENT_URL$]*) as users will need to navigate to
    the calendar widget in order to respond.

Click *Save*. Now your notifications will contain the proper text. The next
section covers setting up calendar resources and porting data from one
installation to another.
