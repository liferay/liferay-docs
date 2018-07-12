# Notifications [](id=notifications)

Notifications are automated emails sent to buyers. By default, notifications can
only be sent to inform buyers when an order has been received, but an extension
point lets you develop other notifications: for a shipped order, a restocked
product, a carrier exception, or any other event.

Notifications require a *Notification Template*, which defines the
notification's sender, its trigger, and its content.

Follow these steps create a notification for a received order:

1.  Go to *Site Menu* &rarr; *Commerce* &rarr; *Settings*. Click on the
    *Notifications* tab and then the *Notification Template* sub-tab.

2.  Click ![Add](../../images/icon-add.png) and fill in the *Add
    Notification Template Form*.

    **Name:** Give the template a name.

    **User Segments:** Click **Select** and choose segments from the list to
    determine users that receive notifications. If no segment is selected, then
    all users receive notifications. See 
    [User Segments](/web/liferay-emporio/documentation/-/knowledge_base/1-0/user-segmentation)
    for more detail.

    **From Address:** Enter the email address that appears in the notification's
    *from* field.

    **From Name:** Enter a name to accompany the address.

    **Type:** Select *Order Placed* from the drop-down. This sets the
    notification to be sent to a user after he places an order. It is the only
    notification trigger available by default.

    **Body:** Enter the actual text of the message. You can include the name of
    the user who placed the order and the order number by including the text
    [%ORDER_CREATOR%] or [%ORDER_ID%] (including brackets) in the message.

    Notifications are not sent unless the *Enabled* box is checked. You can also
    add a description (for internal use), and addresses to CC or BCC. Click
    *Save*.

Once the template is created, a new notification is added to the notification
queue whenever an order is placed. You can view the queue by clicking on the
*Notification Queue* sub-tab in the *Notifications* tab. At specified
intervals---the default is every 15 minutes---@commerce@ checks the queue and
sends any unsent notifications.

To set the interval at which @commerce@ checks the queue, follow these steps:

1.  Go to *Control Menu* &rarr; *Configuration* &rarr; *System Settings*. In the
    *Commerce* section, click on the *Orders* link and then on the *Commerce
    Notification Queue* tab.

2.  In the *Notification Queue Entry Check Interval* field, enter the desired
    interval in minutes.

3.  Click Save.
