---
header-id: sending-alerts-and-announcements
---

# Sending Alerts and Announcements

[TOC levels=1-4]

You can use the Alerts and Announcements widgets on Site pages to broadcast 
important information to users. The Alerts widget is designed for displaying 
high-priority information (e.g. planned downtime alerts, security alerts, etc.). 
Each alert is therefore labeled with a red *Important* tag. The Announcements
widget displays all other information you want to broadcast on your site. Each
announcement therefore lacks the red tag. To separate important alerts from
more mundane announcements, you can place the Alerts and Announcements widgets
on different pages. However, you can use either widget to display any
information you wish. Besides the red tag, they function the same. You can also
scope your alerts and announcements to specific groups of users. 

![Figure 1: The Alerts widget provides administrators with an easy way to communicate important information to appropriate groups of users.](../../../images/alerts-widget.png)

These widgets have two tabs: 

**Unread:** Non-expired alerts/announcements that you haven't read.

**Read:** Alerts/announcements that have expired, or that you've read. 

Click an alert/announcement's *Actions* button 
(![Actions](../../../images/icon-actions.png)) to edit or delete it. 

## Creating Alerts and Announcements

There are two places where you can create alerts and announcements: 

1.  The *Announcements and Alerts* app. Access this app at *Control Panel* 
    &rarr; *Configuration* &rarr; *Announcements and Alerts*. Announcements and 
    alerts are in separate tabs in this app. To begin creating an announcement 
    or alert, select the appropriate tab and then click the *Add* button 
    (![Add](../../../images/icon-add.png)). 
    This app gives administrators a central location to create announcements and 
    alerts that are then displayed on Site pages by the Announcements and Alerts 
    widgets. 

2.  The Announcements and Alerts widgets, after adding them to a Site page from 
    the *Add* 
    (![Add](../../../images/icon-add-app.png)) 
    &rarr; *Widgets* &rarr; *News* menu. To begin creating an announcement or 
    alert, click the widget's *Add Alert* or *Add Announcement* button. 

Regardless of where you create the alert or announcement, the form for creating 
it is the same. Follow these steps to complete the form: 

1.  Use the *Title* field to give the alert or announcement a title. Then create 
    your content in the field *Write your content here...*. For a detailed 
    explanation of the editor, see the 
    [Blogs documentation](/docs/7-2/user/-/knowledge_base/u/using-the-blog-entry-editor). 

    ![Figure 2: Enter your alert or announcement's title and content.](../../../images/alerts-new-alert.png)

2.  Open the *Configuration* section of the form and set the following options, 
    if desired: 

    **Distribution Scope:** The scope where the alert/announcement is displayed. 
    The default *General* scope sends the alert/announcements to everyone. 
    Alternatively, you can select your site or specific roles as the scope. 

    **URL:** A URL (optional) to include with the alert/announcement. For 
    example, an announcement about a news story could include a link to the news 
    article. The URL must be valid and begin with `http://` or `https://`. 

    **Type:** The alert/announcement type. This can be *General*, *News*, or 
    *Test*. Note that each user can specify a different delivery mechanism for 
    each type of alert/announcement. See 
    [User Configuration](#user-configuration) 
    for details. 

    **Priority:** The announcement's priority. This can be *Normal* or 
    *Important*. Note that this is disabled for alerts because alerts are always 
    high priority. 

    **Display Date:** The display date of the alert/announcement. This 
    determines when the alert/announcement is sent to users and appears in the 
    widget. By default, the *Display Immediately* box is checked. This sets the 
    display date equal to the creation date. Uncheck this box to enter a custom 
    display date. For example, administrators can create alerts/announcements 
    for display on a later date. This date can be days, weeks, months, or years 
    in the future. Once the *Display Immediately* box is unchecked, clicking the 
    Display Date field opens the date-picker. 

    **Expiration Date:** The date and time the alert/announcement expires. Once 
    an alert/announcement expires, the widget displays it in the Read tab. 
    Clicking the Expiration Date field opens the date-picker. 

    ![Figure 3: Configure your new alert or announcement.](../../../images/alerts-new-alert-config.png)

3.  Click *Save* when you're done. Your alert/announcement then appears in the 
    widget. 

## User Configuration

Users can configure how they'd like to receive announcements. 

1.  Open your user menu and select *Account Settings*. 

2.  On the *Preferences* tab, select *Alerts and Announcements Delivery*. This 
    shows options for customizing the delivery of alerts and announcements. 

3.  Select a configuration for each type of alert/announcement (General, News, 
    or Test). For each type, you can enable delivery by email and SMS (text 
    message). Note that the *Website* delivery option is selected and grayed out 
    for each alert type. This means that each alert/announcement is always 
    viewable in its respective widget on a site. 

4.  Click *Save* when you're finished. 

    ![Figure 4: Each user can choose how they receive alerts and announcements.](../../../images/alerts-delivery.png)

## Alert and Announcement Roles

You can also create roles for users to make general announcements. For instance, 
if you want someone specific to have strict control over announcements, give
that person an Announcements Role. Follow these steps to create a simple
Announcements Role: 

1.  Navigate to *Control Panel* &rarr; *Users* &rarr; *Roles*. 

2.  With the *Regular Roles* tab selected, click the *Add* button 
    (![Add](../../../images/icon-add.png)). This opens the *New Role* form. 

3.  Name your Role *Announcements*, give it a description, and click *Save*. 

4.  Select the Role's *Define Permissions* tab then grant these permissions: 

    -   In *Control Panel* &rarr; *General Permissions*, select *Add General 
        Announcements*. 
    -   In *Control Panel* &rarr; *General Permissions* &rarr; *Announcements and Alerts*, select all the resource permissions.
    -   In *Site Administration* &rarr; *Applications* &rarr; *Announcements*, 
        select all the resource permissions.

    Click *Save* after selecting each permission. These permissions let the Role 
    add alerts and announcements. 

Now you have a simple Announcements Role that can manage your site's general 
announcements. Of course, you can adjust this Role's permissions as needed. 
