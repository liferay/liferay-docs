# Displaying Site Pages to Mobile Devices [](id=displaying-site-pages-to-mobile-devices-lp-6-2-use-useportal)

Mobile device families allow you to configure sets of rules to alter the
behavior of the portal based on the device being used to access Liferay. The
proportion of mobile device users browsing the web has been steadily increasing,
so it's important to be able to handle different kinds of devices appropriately.
For instance, you can configure the look and feel of Liferay pages accessed by
smartphone or tablet users differently from those accessed by PC users.

Both sites and individual pages can be configured with any number of mobile
device families. A family is designed to describe a group of devices. It can
contain one or more rules that describe a category of devices, such as all
Android devices or all iOS tablets. You can define as many rules in a family as
you need to classify all the devices for which you'd like to define actions.
Families can be prioritized to determine which one applies to a given page
request.

In order to configure mobile device rules, you need a way to find out the
characteristics of the device. While some of the characteristics are provided by
the device, most are not. For this reason, there are databases that contain
information about thousands of devices. These databases make it possible to
learn every detail about a device from the device type, which is included in
each request sent to the portal. Liferay's Mobile Device Rules can connect to
device databases so that you can use their device characteristics in your rules. 

Among the plugins available on Liferay Marketplace, you can find the Device
Recognition Provider plugin. This plugin provides out of the box integration
with WURFL, an open source database licensed with the AGPLv3 license. Commercial
licenses are also available. It's also possible to develop plugins that
integrate with other device databases. Even if you don't have a device database,
you can still set up mobile device rules. They won't, however, be effective
until a database is deployed, because the portal won't have enough information
about the devices being used to make page requests.

To configure mobile device rules, you must install the Device Recognition
Provider plugin. This plugin uses the WURFL database to enable Liferay to detect
which mobile device or operating system is being used for any given request. To
install the plugin, navigate to the Store section of the Control Panel, located
under the Marketplace heading. Click on the *Utility* section and then on *See
All*. Search for the appropriate Device Recognition Provider plugin (CE or EE)
and click on it. Finally, click on *Free* to acquire the plugin. Once you've
acquired the plugin, you need to download and install it. To do so, navigate to
the Purchased section of the Control Panel, find your Device Recognition
Provider plugin, and click on *Download* and then *Install*.

**Installation Note:** If your server doesn't have access to the outside
Internet, an error appears in your log: `SLF4J: Failed to load class
"org.slf4j.impl.StaticLoggerBinder`. This occurs because WURFL by default
downloads device information from the web. You can provide the same information
to WURFL manually by downloading the SLF4J distribution from
[http://www.slf4j.org/download.html](http://www.slf4j.org/download.html),
unzipping the resulting file, copying `slf4j-log4j12-<version>.jar` to
`[WEB_APP_HOME]/wurfl-web/WEB-INF/lib` folder, and restarting your Liferay
instance. On some application servers, you'll need to add this .jar file to the
`wurfl-web.war` file first (in the directory noted above) before deploying the
file to your server. 

You can access the Mobile Device Families administrative page from the
Configuration section of Site Administration. Make sure you're on the
appropriate site before adding mobile device families via Site Administration.
You can also add families for all sites by navigating to the Control Panel
&rarr; *Sites* &rarr; *Global*. The Mobile Device Families administrative page
displays a list of defined families and lets you add more. To add rules to a
family, select *Actions* &rarr; *Manage Classification Rules*, or click on a
family to edit it, and then click the *Manage Classification Rules* link.

![Figure 3.27: You can manage device rules from the Mobile Device Families administrative page.](../../images/mobile-device-families.png)

The rules defined for a family, along with the priorities of the families
selected for a particular site or page, determine which family's actions are
applied to a given request. From the Manage Classification Rules page for a
specific rule set, you can add a rule by specifying an operating system, rule
type, physical screen size, and screen resolution. Remember that you can add as
many rules to a family as you need in order to classify the devices on which
you'd like to take actions. You'll notice after saving the classification rule
that it's characterized as a *Simple Rule*. By default, only the Simple Rule
type is available. The rules are designed to be extensible, and additional rule
types can be added by your developers.

![Figure 3.28: You need to install the Device Recognition Provider plugin to populate the OS list.](../../images/mobile-device-editing-rule.png)

Once you've created some mobile device families and added some rules to them,
you'll be ready to set up some actions. The actions defined for a family
determine what happens to a particular request when the device is detected and
the family has been found to apply.

You can add actions to a family from the Site Pages page of Site Administration.
Select either the public or private pages and then look for the *Mobile Device
Rules* link in the right-hand menu. Use the *Select Device Family* button to
select families to be applied either to a site or to a single page. If you
select the page group itself from the left-hand menu, the selected family
applies to all the pages of the site by default. If, however, you select an
individual page and then click the *Select Device Family* button, the families
apply only to that page. You can select multiple families for a particular site
or page and order them by priority. The families are checked in decreasing order
of priority: the actions defined by the first family that applies are executed.

![Figure 3.29: You can select a mobile device family to apply for a site or page from the Site Pages section of Site Administration.](../../images/mobile-device-selection.png)

To add actions to a selected rule group, use the *Actions* &rarr; *Manage
Actions* button and then click *Add Action*. By default, there are four kinds of
actions that can be configured for mobile families: layout template
modifications, theme modifications, URL redirects, and site redirects. Layout
template modifications let you change the way portlets are arranged on pages
delivered to mobile devices, and themes modifications let you select a specific
look and feel. If it makes more sense for you to create separate mobile versions
of certain sites or pages, you can use a redirect to make sure mobile device
users get to the right page. To define a URL redirect, you need to specify a
URL. To define a site redirect, you need only specify the site name and page
name of the page to which you're redirecting. Like mobile device rules, mobile
device actions are designed to be extensible. Your developers can define custom
actions in addition to the four actions provided by default.

To review, if you'd like to configure an action or actions that take place when
mobile device requests are received, take the following steps:

1. Create a mobile device family to represent the group of devices for which to
   define an action or actions.

2. Define one or more rules for your family that describe the group of
   devices represented by your family.

3. Apply your family to an entire page set of a site (all the public pages of a
   site or all the private pages) or to a single page.

4. Define one or more actions for your family that describe how requests should
   be handled.

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
Page* button. Now our Android/Bada Liferay site has a public Welcome page just
like our default Liferay site.

Next, select *Liferay* in the *My Sites* tab of the Dockbar, navigate to Site
Administration &rarr; Configuration, and click on *Mobile Device Families*.
Click on *Add Device Family*, enter the name *Android and Bada Mobile Phones*, and
click *Save*. You'll see the message, *No rules are configured for this rule
group*.

Click the *Manage Classification Rules* link and we'll configure our rule group
to apply only to mobile phones running Android or Bada. Click *Add
Classification Rule*, enter *Rule 1* for the name. Under Operating System,
select *Android* and *Bada OS* (hold down Control to make multiple selections),
select *Other Devices* under Tablet since we want our family to apply only to
mobile phones, and click *Save*. Now we just need to define the redirect action
for our family. Navigate to *Pages* &rarr; *Site Pages* and click on *Mobile
Device Rules* in the navigation menu to the right.

![Figure 3.30: To apply a mobile device family to a page set of a site, click on *Mobile Device Rules*, click *Select Device Family*, and select the desired rule group.](../../images/site-pages-mobile-device-rules.png)

Click *Select Device Family* and then click the *Android and Bada Mobile Phones*
device family that you configured. Once you've selected your device family,
click on your device family or *Actions* &rarr; *Manage Actions* next to it.
Then click *Add Action*, enter the name *Android/Bada Liferay Redirect*, and
select *Redirect to Site* under Type. Under the Site dropdown menu that appears,
select *Android/Bada Liferay* and under the Page dropdown menu that appears,
select the *Welcome* page that you created earlier. Lastly, click *Save*. That's
it! Now Android and Bada mobile phone users are redirected to the Android/Bada
Liferay site from the Liferay site. 

Let's look at one more example of using mobile device rules before we move on.
Suppose you'd like to create another rule so that when a site is accessed by an
Android or iOS tablet, a different layout is used. To set this up, we need to
follow the same four steps described above. First, make sure you're on the
Liferay site by checking in the My Sites tab of the Dockbar. Then navigate to
the Mobile Device Families page of Site Administration. Add a new device family
called *Android and iOS Tablets*. Add a classification rule called *Rule 1*,
select *Android and iPhone OS* under the *Operating System heading*, select
*Tablets* under the *Device Type* heading, then click *Save*. As with the
previous example, we only need one rule to describe our device family.

Next, click on *Site Pages* in Site Administration, select *Mobile Device
Rules*, and select the *Android and iOS Tablets* device family. Notice that
you've now selected two rule groups for the Liferay site's public pages and
they've been assigned priorities. If a device making a request belongs to both
of the device families represented by the rule groups, the priority of the rule
groups determines which rule group's actions are executed. Note that in our
example, the first rule group contains only mobile phones and the second rule
group contains only tablets, so no devices can belong to both rule groups. Now
we just need to define an action for our Android and iOS Tablets rule group to
use a different layout: On the Site Pages page of Site Administration, click on
*Mobile Device Rules*, and then on *Actions* &rarr; *Manage Actions* next to
Android and iOS Tablets. Click on *Add Action*, enter the name *Layout Template
Modification*, and select the *Layout Template Modification* action type.
Lastly, select the *1 Column* layout template (or whichever one you like) and
click *Save*. Good job! Now the Liferay site's pages are presented to Android
and iOS tablet users with the 1 Column layout template.
