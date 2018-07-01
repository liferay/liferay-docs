# Mobile Device Actions [](id=mobile-device-actions)

After you've created families and applied rules to define those families, you 
can associate specific actions that occur when a user visits that Site on
a device.

To add actions to a selected rule group:

1.  Go to the *Configuration* page for the page, page set, or Site where you 
    have configured a device family.

2.  In the *Mobile Device Rules* section, click *Actions* (![Actions](../../../../../../images/icon-actions.png)) &rarr; *Manage 
    Actions* next to the device family that you wish to add an action for.
 
3.  Click *Add Action*.

![Figure 1: Getting to the Manage Actions page.](../../../../../../images/manage-mobile-actions.png)

By default, there are four kinds of actions that can be configured for mobile
families:

**Layout Template Modification:** Changes the way portlets are arranged on pages
delivered to mobile devices. For example, you could have pages with more complex
layouts automatically switch to a simpler template if it detects a mobile
device---even if the resolution is theoretically high enough to support the
standard layout.

**Theme Modification:** Selects a specific theme for different mobile device
families. You'd have to have a mobile version of your Site's theme that is
automatically applied when a device hits your page.

**URL Redirect:** Sends mobile users to any URL. This can be used to direct
mobile users to a mobile app download or a mobile version of the page.

**Site Redirect:** Sends mobile users to a different Site on your portal. In
some cases, mobile content could be created on a mirror of your Site.

+$$$

**Tip:** @product-ver@ was designed from the ground up to be responsive and 
adapt to any device that might be accessing it. Before creating new themes or 
forcing a layout template change, you should test how the Site behaves using 
@product@ out of the box. Certain features, like URL Redirects, can be 
disruptive and frustrating for users if used improperly.

$$$

Like mobile device rules, mobile device actions are extensible. Your developers
can define custom actions in addition to the four actions provided by default.

To review, if you want to configure an action or actions that take place when
mobile device requests are received, take the following steps:

1.  Create a mobile device family to represent the group of devices for which to
    define an action or actions.

2.  Define one or more rules for your family that describe the group of devices
    represented by your family.

3.  Apply your family to an entire page set of a Site (all the public pages of a
    Site or all the private pages) or to a single page.

4.  Define one or more actions for your family that describe how requests should
    be handled.

## Mobile Device Rules Example [](id=mobile-device-rules-example)

Now you'll look at an example of using mobile device rules. Suppose you want to
create a rule so that when a Site is accessed by an Android or iOS tablet,
a different layout is used. To set this up, you must follow the same four steps
described above.

First create the Mobile Device Family:

1.  Navigate to the *Mobile Device Families* page of *Site Administration*.

2.  Click *Add Device Family* (![Add Device Family](../../../../../../images/icon-add.png)).

3.  Enter *Android and iOS Tablets* for the *Name*.

4.  Click *Save*.

Next create a rule for the family:

1.  From the *Mobile Device Families* page, click on *Android and iOS Tablets*.

2.  Click *Add Classification Rule* (![Add Classification Rule](../../../../../../images/icon-add.png)).

3.  Name the rule *Rule 1*.

4.  Under *Operating System* select *Android* and *iPhone OS* (you can hold
    <CTRL> or <CMD> to select multiple items).

5.  Under *Device Type* select *Tablets*,

6.  Click *Save*.

    ![Figure 2: Create the Classification rule.](../../../../../../images/example-classification-rule.png)

As with the previous example, you only need one rule to describe your device
family. Now you must apply the rule to some pages.

1.  Go to *Build* &rarr; *Pages* in Site Administration.

2.  Click on the *Configuration* icon for the *Public Pages*

3.  Go to the *Advanced* tab.

3.  Under *Mobile Device Rules*, select the *Android and iOS Tablets* device
    family.

Now you must define an action for your Android and iOS Tablets rule group to use
a different layout.

1.  Click *Actions* &rarr; *Manage Actions* for the *Android and iOS Tablets* 
    rule.
 
2.  Click *Add Action*.

3.  Enter the name *Layout Template Modification*, and select the *Layout
    Template Modification* action type.

4.  Select the *1 Column* layout template. 

5.  Click *Save*.

![Figure 3: Create the Actions for Android and iOS Tablets.](../../../../../../images/example-mobile-action.png)

Now the Liferay Site's pages are presented to Android and iOS tablet users with 
the 1 Column layout template.

Mobile Device Rules are a powerful way to manage the way pages and content
appear on the various devices that access your Site. But remember to consider
the power of modern devices and the experience of your users, and use this great
power responsibly---to help users have a great experience on your website and to
not interrupt or negatively impact that experience on whatever device they're
using.
