# Organizing and Sharing Events with the Calendar [](id=organizing-and-sharing-events-with-the-calendar)

Liferay's Calendar portlet is a complete calendaring solution. You can schedule
any number of events of different types, receive alarms via email or text
message, import and export your calendar, and much more. Additionally, you can
import and export the calendar to and from the popular iCalendar format for use
in other applications.

![Figure 7.22: The Liferay Calendar
Portlet](../../images/05-calendar-portlet.png)

In a similar way to the Blogs portlet, you can use the Calendar portlet as a
shared calendar on a web site or as a personal calendar -- or both. Let's look
at how to configure it. 

## Configuring the Calendar Portlet [](id=configuring-the-calendar-portlet)

Open the *Configuration* dialog box from the menu in the portlet's title bar.
The Setup tab allows you to configure three different options in subtabs:
*Email From*, *Event Reminder Email*, and *Display Settings*.

**Email From:** set the *Name* and *Email Address* system generated emails come
from. The address that you enter in the Email Address field must be properly
formatted, but it doesn't need to be an address that actually exists. 

**Event Reminder Email:** customize the email message the Calendar sends for
event reminders. It contains the same rich text editor that you see everywhere
else in Liferay, and this allows you to HTML format the message for easy
recognition. As with other Liferay email notifications, there are several
variables which allow you to insert runtime values into the message, and these
are listed underneath the text editor so that you can use them in the
appropriate place in your template. For example, you might want the event start
date and time and the event title included in the email reminder that you
receive. Use the variables to insert that information in the appropriate place. 

**Display Settings:** customize how the portlet shows itself to the user. There
are several settings here: 

- *Default Tab:* select which tab of the calendar portlet is displayed to the
  user by default. 

- *Summary Tab:* select whether it has a horizontal or vertical layout. You can
  also use checkboxes to choose whether or not the calendar portlet shows a
mini month, shows today's events, or enables related assets, comments, or
ratings. 

Now that you've successfully set up your calendar, let's look at how to use it. 

## Using the Calendar Portlet [](id=using-the-calendar-portlet)

The Calendar portlet inherits its interface from the rest of Liferay's portlet
library, so you should find shared features in the same place that you find
them in other Liferay portlets.

To get started, you may want to click the *Permissions* button. Here you'll
find an interface that should be becoming familiar: a list of roles with check
boxes. For the Calendar, these denote whether the role has the *Add Event* or
the *Export All Events* permission. By default, only the owner has permission
to do these things, which means that the Calendar portlet is set up for
personal use. The reason for this is that out of the box, Liferay puts a
Calendar portlet on all users' private pages. If you want to use the Calendar
as a shared calendar, you'll need to make an additional configuration. 

First, create a portal or site role. Then add the users responsible for
maintaining the calendar to this role. This process is described in chapter 12.
You can add multiple roles if you like. Once you have the role or roles set up,
come back to the Calendar portlet and click the *Permissions* button. Check the
boxes next to the roles that should have access to one or both of the functions
*Add Event* and *Export All Events*. Then click *Submit*.

Now you are ready to begin using your calendar. Click the *Add Event* button. A
form appears that allows you to fill out all the information for your event.

**Start Date/Time:** enter a date and time for the event.

**Duration:** specify how long the event will last.

**All Day Event:** disassociate time from the event and make it last all day.

**Time Zone Sensitive:** make sure that the portal keeps track of the event
regardless of time zone.

**Title:** provide a title for the event.

**Description:** describe your event.

**Location:** specify the physical location of the event.

**Type:** select a pre-configured event type. You can change these in the
`portal-ext.properties` file.

**Permissions:** manage who can view and edit the event.

**Categorizations:** tag and categorize the event.

**Related Assets:** attach a piece of content from another portlet to the
event.

**Repeat:** select the schedule (daily, weekly, monthly. etc.) for repeatable
events.

**End Date:** enter the end date for events that repeat on a schedule but have
a specified last meeting.

**Reminders:** select whether to send a reminder, how long before the event to
send it, and through what medium (email, SMS text message, or instant message)
to send it. Note that this feature is integrated with users' profiles on the
portal, so users need their information filled out and current in order to
receive notifications.

When you have finished adding your event, click *Save*. You can view calendar
events by day, week, month, year, or in a simple list. 

As you can see, the Calendar portlet is easy to use and convenient for users of
the portal. Next, let's look at one of the most widely used applications
provided by Liferay: its message boards.
