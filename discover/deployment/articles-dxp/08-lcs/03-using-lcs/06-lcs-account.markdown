# Managing Your LCS Account [](id=managing-your-lcs-account)

To manage your LCS account, select *My Account* from the user menu in the
Dockbar. This takes you to a UI with three tabs: 

**Email Notifications:** Configure LCS to send you emails when specific 
events occur in your LCS projects by adding rules that define what events
trigger a notification. There are no rules by default. Click the *Add Rule*
button to define one. 

First specify the project, environment, and server for the notification. 
Note that you have the option of selecting all environments and servers in a
project. Then check the checkbox for each event that you want to trigger an 
email notification. For example, if you create a notification rule with *The 
server unexpectedly shuts down* selected for all servers and environments in
your project, then LCS sends you an email if any server in your project goes
offline without a normal shut down event. Click *Save* when you're done defining
the notification rule. It then appears in a table along with other existing
rules. Each has Edit and Delete Action buttons. 

![Figure 1: You can add rules to determine the events that trigger notifications.](../../../images-dxp/lcs-add-notification-rule.png)

**Notification History:** Displays your web notification history in a 
searchable table. You can also select the date range from which to display 
notifications. 

**Preferences:** Manage your LCS account's preferences. You can change your
account's language, time zone, and default LCS project. Your default LCS project
is the one shown each time you log in to LCS. 

![Figure 2: You can change your LCS account's general preferences.](../../../images-dxp/lcs-account-preferences.png)

## Using Web Notifications [](id=using-web-notifications)

LCS also displays web notifications under the bell icon in the Dockbar. A red
badge on this icon shows your unread notification count. LCS and Liferay Support
send these notifications. For example, LCS generates notifications when a server
shuts down or some other event requiring your attention occurs. To mark
a notification as read, click the small *x* icon next to it. To mark all
notifications as read, click the *Mark All as Read* button. To mark
notifications as unread again, click the *Undo* button that appears. To see your
notification history, click the *Notifications History* button. You can also
access your notification history by selecting *My Account* from the user menu in
the Dockbar. 

![Figure 3: Web notifications let you know what's happening in your LCS projects.](../../../images-dxp/lcs-user-web-notifications.png)
