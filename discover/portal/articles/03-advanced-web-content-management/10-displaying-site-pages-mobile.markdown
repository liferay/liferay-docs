# Displaying Site Pages to Mobile Devices [](id=displaying-site-pages-to-mobile-devices)

Mobile device rules allow you to configure sets of rules to alter the behavior
of the portal based on the device being used to access Liferay. The proportion
of mobile device users browsing the web has been steadily increasing, so it's
important to be able to handle different kinds of devices appropriately. For
instance, you can configure the look and feel of Liferay pages accessed by
smartphone or tablet users differently from those accessed by PC users.

Both sites and individual pages can be configured with any number of rule
groups. A rule group is designed to describe a group of devices; think of a rule
group as a mobile device family. It can contain one or more rules that describe
a category of devices, such as all Android devices or all iOS tablets. You can
define as many rules in a rule group as you need to classify all the devices for
which you'd like to define actions. Rule groups can be prioritized to determine
which one applies to a given page request. 

In order to configure mobile device rules, you need a way to find out the
characteristics of the device. While some of the characteristics are provided by
the device, most are not. For this reason, there are databases that contain
information about thousands of devices. These databases make it possible to
learn every detail about a device from the device type, which is included in
each request sent to the portal. Liferay's Mobile Device Rules can connect to
device databases so that you can use their device characteristics in your rules. 

Liferay provides such a database in the Liferay Mobile Device Detection (LMDD) 
app from the Liferay Marketplace. 
[Click here](/discover/portal/-/knowledge_base/6-1/leveraging-the-liferay-marketplace) 
for instructions on using Liferay Marketplace to find and install apps. 

+$$$

**Note:** The Liferay Mobile Device Detection (LMDD) app is EE-only for Liferay 
Portal 6.1. Although you can use other device detection databases, doing so 
requires you to manually integrate the database with Liferay APIs. 

$$$

It's possible to develop plugins that integrate with other device databases.
Even if you don't have a device database, you can still set up mobile device
rules. They won't, however, be effective until a database is deployed, because
the portal won't have enough information about the devices being used to make
page requests. To learn how to tap into Liferay's Device API, see the
[Device Detection](/develop/tutorials/-/knowledge_base/6-1/device-detection).

You can access the Mobile Device Rules administrative page from the Content
section of the Control Panel. Select the appropriate scope using the context
menu selector so your rule groups are available where you expect them to be. The
Mobile Device Rules administrative page displays a list of defined rule groups
and lets you add more. To add rules to a rule group, select *Actions*
&rarr; *Manage Rules*, or click on a rule group to edit it, and then click
the *Manage Rules* link.

![Figure 3.28: You can manage device rules from the Mobile Device Rules
administrative page.](../../images/mobile-device-rules.png)

The rules defined for a rule group, along with the priorities of the rule groups
selected for a particular site or page, determine which rule group's actions are
applied to a given request. From the Manage Rules page for a specific rule set,
you can add a rule by specifying a rule type. Remember that you can add as many
rules to a rule group as you need in order to classify the devices on which
you'd like to take actions. Note that, by default, only the Simple Rule type is
available.  The rules are designed, however, to be extensible, and additional
rule types can be added by your developers. Once added, you can edit the rule to
specify a device type and operating system.

![Figure 3.29: You need to install a device recognition plugin to populate the
OS list.](../../images/mobile-device-editing-rule.png)

Once you've created some mobile device rule groups and added some rules to them,
you'll be ready to set up some actions. The actions defined for a rule group
determine what happens to a particular request when the device is detected
and the rule group has been found to apply.

You can add actions to a rule group from the Site Pages page of the Control
Panel. Select either the public or private pages and then look for the *Mobile
Rule Groups* link in the right-hand menu. Use the *Select Rule Group* button to
select rule groups to be applied either to a site or to a single page. If you
select the page group itself from the left-hand menu, the selected rule group
applies to all the pages of the site by default. If, however, you select an
individual page and then click the *Select Rule Group* button, the rule groups
apply only to that page. You can select multiple rule groups for a particular
site or page and order them by priority. The rule groups are checked in
decreasing order of priority: the actions defined by the first rule group that
applies are executed.

![Figure 3.30: You can select a mobile device rule group to apply for a site or
page from the Site Pages section of the Control
Panel.](../../images/mobile-device-selection.png)

To add actions to a selected rule group, use the *Actions* &rarr; *Manage
Actions* button and then click *Add Action*. By default, there are four kinds of
actions that can be configured for mobile rule groups: layout template
modifications, theme modifications, simple redirects, and site redirects. Layout
template modifications let you change the way portlets are arranged on pages
delivered to mobile devices, and themes modifications let you select a specific
look and feel. If it makes more sense for you to create separate mobile versions
of certain sites or pages, you can use a redirect to make sure mobile device
users get to the right page. To define a simple redirect, you need to specify a
URL. To define a site redirect, you need only specify the site name and page
name of the page to which you're redirecting. Like mobile device rules, mobile
device actions are designed to be extensible. Your developers can define custom
actions in addition to the four actions provided by default.

To review, if you'd like to configure an action or actions that take place when
mobile device requests are received, take the following steps:

1. Create a mobile device rule group to represent the family of devices for
   which to define an action or actions.

2. Define one or more rules for your rule group that describe the family of
   devices represented by your rule group.

3. Apply your rule group to an entire page set of a site (all the public pages
   of a site or all the private pages) or to a single page.

4. Define one or more actions for your rule group that describe how requests
   should be handled.

To see how this might work in practice, let's discuss a few examples of how you
can use mobile device rules. First, suppose you have a separate version of a
site on your portal that's specifically designed for mobile phones running
Android or Bada. For our example, we'll make a site called Android/Bada Liferay
and we'll configure the default Liferay site to redirect incoming requests from
Android or Bada mobile phones to the Android/Bada Liferay site. Our first step
is to create the Android/Bada Liferay site: go to the Sites page of the Control
Panel and click *Add* &rarr; *Blank Site*. Enter the name *Android/Bada Liferay*
and click *Save*. Then, with Android/Bada selected in the context menu selector,
click on *Site Pages*. By default, the newly created site doesn't have any
pages, so click on *Add Page*, enter the name *Welcome*, and click the *Add
Page* button.  Now our Android/Bada Liferay site has a public Welcome page just
like our default Liferay site.

Next, select *Liferay* in the context menu selector and go to the Mobile Device
Rules page of the Control Panel. Click on *Add Rule Group*, enter the name
*Android and Bada Mobile Phones*, and click *Save*. You'll see the message, *No
rules are configured for this rule group.*

![Figure 3.31: After adding a new rule, you'll see a message indicating that no
rules have been configured for the rule
group.](../../images/no-rule-groups-configured.png)

Click the *Manage Rules* link and we'll configure our rule group to apply only
to mobile phones running Android or Bada. Click *Add Rule*, enter *Rule 1* for
the name and select *Simple Rule* for the type, then click *Save*. Then click on
the rule to edit it or click *Actions* &rarr; *Edit*. Under OS, select *Android*
and *Bada OS* (hold down Control to make multiple selections), select *False*
under Tablet since we want our rule group to apply only to mobile phones, and
click *Save*. Now we just need to define the redirect action for our rule group.
Make sure Liferay is still selected in the context menu selector and click on
*Site Pages*. Click on *Mobile Rule Groups* in the navigation menu to the right.

![Figure 3.32: To apply a mobile device rule group to a page set of a site,
select the site in the context menu selector, click on *Mobile Rule Groups*,
click *Select Rule Group*, and select the desired rule
group.](../../images/site-pages-mobile-device-rules.png)

Click *Select Rule Group* and then click the *Android and Bada Mobile Phones*
rule group that you configured. Once you've selected your rule group, click 
*Mobile Rule Groups* again and click either on your rule group or *Actions*
&rarr; *Manage Actions* next to it. Then click *Add Action*, enter the name
*Android/Bada Liferay Redirect*, and select *Site Redirect* under Type.  Under
the Site dropdown menu that appears, select *Android/Bada Liferay* and under the
Page dropdown menu that appears, select the *Welcome* page that you created
earlier. Lastly, click *Save*. That's it! Now Android and Bada mobile phone
users are redirected to the Android/Bada Liferay site from the Liferay site. 

Let's look at one more example of using mobile device rules before we move on.
Suppose you'd like to create another rule so that when a site is accessed by an
Android or iOS tablet, a different layout is used. To set this up, we need to
follow the same four steps described above. First, make sure that the Liferay
site is selected in the Control Panel's context menu selector and navigate to
the Mobile Device Rules page of the Control Panel. Add a new rule group called
*Android and iOS Tablets*. Add a simple rule called *Rule 1* to this rule group.
As with the previous example, we only need one rule to describe our device
family. Edit *Rule 1* and select *Android and iPhone OS* under the OS heading
and *True* under the Tablet heading, then click *Save*.

Next, click on *Site Pages* in the Control Panel, select *Mobile Rule Groups*,
and select the *Android and iOS Tablets* rule group. Notice that you've now
selected two rule groups for the Liferay site's public pages and they've been
assigned priorities. If a device making a request belongs to both of the device
families represented by the rule groups, the priority of the rule groups
determines which rule group's actions are executed. Note that in our example,
the first rule group contains only mobile phones and the second rule group
contains only tablets, so no devices can belong to both rule groups. Now we just
need to define an action for our Android and iOS Tablets rule group to use a
different layout: On the Site Pages page of the Control Panel, click on *Mobile
Rule Groups*, and then on *Actions* &rarr; *Manage Actions* next to Android and
iOS Tablets. Click on *Add Action*, enter the name *Layout Template
Modification*, and select the *Layout Template Modification* action type.
Lastly, select the *1 Column* layout template (or whichever one you like) and
click *Save*. Good job!  Now the Liferay site's pages are presented to Android
and iOS tablet users with the 1 Column layout template.
