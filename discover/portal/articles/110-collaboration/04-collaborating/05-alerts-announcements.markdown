# Sending Alerts and Announcements

You can use the Alerts and Announcements widgets on site pages to broadcast 
important information to users. The Alerts widget is designed for displaying 
high-priority information (e.g. planned downtime alerts, security alerts, etc.). 
Each alert is therefore labeled with a red *Important* tag. The Announcements 
widget is designed to display all other information you want to broadcast on 
your site. Each announcement therefore lacks the red tag. To separate important 
alerts from more mundane announcements, you can place the Alerts and 
Announcements widgets on different pages. However, you can use either widget to 
display any information you wish. Besides the red tag, they function the same. 
You can also scope your alerts and announcements to specific groups of users. 

![Figure 1: The Alerts widget provides administrators with an easy way to communicate important information to appropriate groups of users.](../../../../images/alerts-widget.png)

These widgets have 2 tabs: 

-   **Unread:** Non-expired alerts/announcements that you haven't read.
-   **Read:** Alerts/announcements that have expired, or that you've read. 

Click an alert/announcement's *Actions* button 
(![Actions](../../../../images/icon-actions.png)) to edit or delete it. 

## Creating Alerts and Announcements

You can add an Alerts or Announcements widget to your site page from the *Add* 
(![Add](../../../../images/icon-control-menu-add.png)) &rarr; *Widgets* &rarr; 
*News* menu. Once the widget is on your page, follow these steps to add an alert 
or announcement: 

1.  Click the *Add Alert* or *Add Announcement* button. 

2.  In the form that appears, use the *Title* field to give the alert or 
    announcement a title. Then create your content in the field 
    *Write your content here...*. For a detailed 
    explanation of the editor, see the 
    [Blogs documentation](/discover/portal/-/knowledge_base/7-1/using-the-blog-entry-editor). 

    ![Figure 2: Enter your alert or announcement's title and content.](../../../../images/alerts-widget.png)

3.  Open the *Configuration* section of the form and set the following options, 
    if desired: 

    -   **Distribution Scope:** The scope to display the alert/announcement in. 
        The *General* scope, which is the default, sends the alert/announcements 
        to everyone. Alternatively, you can select your site or specific roles 
        as the scope. 

    -   **URL:** A URL (optional) to include with the alert/announcement. For 
        example, an announcement about a news story could include a link to the 
        news article. The URL must be valid and begin with `http://` or 
        `https://`. 

    -   **Type:** The alert/announcement type. This can be *General*, *News*, or 
        *Test*. Note that each user can specify a different delivery mechanism 
        for each type of alert/announcement. See the 
        [User Configuration section](#user-configuration) 
        for details. 

    -   **Priority:** The announcement's priority. This can be *Normal* or 
        *Important*. Note that this is grayed out for alerts because alerts are 
        always high priority. 

    -   **Display Date:** The display date of the alert/announcement. This 
        determines when the alert/announcement is sent to users and appears in 
        the widget. By default, the *Display Immediately* box is checked. This 
        sets the display date equal to the creation date. Uncheck this box to 
        enter a custom display date. For example, administrators can create 
        alerts/announcements for display on a later date. This date can be days, 
        weeks, months, or years in the future. Once the *Display Immediately* 
        box is unchecked, clicking the Display Date field opens the date-picker. 

    -   **Expiration Date:** The date and time the alert/announcement expires. 
        Once an alert/announcement expires, the widget displays it in the Read
        tab. Clicking the Expiration Date field opens the date-picker. 

    ![Figure 3: Configure your new alert or announcement.](../../../../images/alerts-new-alert-config.png)

4.  Click *Save* when you're done. Your alert/announcement then appears in the 
    widget. 

## User Configuration

Each user can configure how they'd like to receive announcements. 

1.  From the Menu 
    (![Menu](../../../../images/icon-menu.png)), open your user menu and select 
    *My Account* &rarr; *Account Settings*. 

2.  On the *Preferences* tab, select *Alerts and Announcements Delivery*. This 
    brings up a menu for customizing the delivery options for alerts and 
    announcements. 

3.  Select a configuration for each type of alert/announcement (General, News, 
    or Test). For each type, you can enable delivery by email and SMS (text 
    message). Note that the *Website* delivery option is selected and grayed out 
    for each alert type. This means that each alert/announcement is always 
    viewable in its respective widget on a site. 

4.  Click *Save* when you're finished. 

    ![Figure 4: Each user can choose how they receive alerts and announcements.](../../../../images/alerts-delivery.png)

## Alert and Announcement Roles

You can also create roles for users to make general announcements. For instance, 
if you'd like an employee in your site to have strict control over what is 
announced, you can assign them to an Announcements role. Follow these steps to 
create a simple Announcements role: 

1.  Navigate to *Control Panel* &rarr; *Users* &rarr; *Roles*. 

2.  With the the *Regular Roles* tab selected, click the *Add* button 
    (![Add](../../../../images/icon-add.png)). This opens the *New Role* form. 

3.  Name your role *Announcements*, give it a title and description, and click 
    *Save*. 

4.  Select the role's *Define Permissions* tab then grant these permissions: 

    -   In *Control Panel* &rarr; *General Permissions*, select *Add General 
        Announcements*. 
    -   In *Site Administration* &rarr; *Applications* &rarr; *Announcements*, 
        select all the resource permissions.

    Click *Save* after selecting each permission. These permissions let the role 
    add alerts and announcements. 

Now you have a simple Announcements role that can manage your site's general 
announcements. Of course, you can adjust this role's permissions. 
