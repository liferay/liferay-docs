# Displaying Pages on Mobile Devices [](id=displaying-pages-on-mobile-devices)

Mobile device families allow you to configure sets of rules to alter the
behavior of the instance based on the device being used to access @product@. The
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
each request sent to Liferay. @product@'s Mobile Device Rules can connect to
device databases so that you can use their device characteristics in your rules. 

<!-- Liferay connects to the [DATABASE_NAME] database automatically behind the
scenes to provide device recognition access automatically. -->

+$$$

**Important:** At the time of this writing, Liferay 7.0 CE GA1 is not connected
to a device database by default. Currently, the only way to create Mobile Device
Families and Rules effectively is to integrate with other device databases using
Liferay APIs. Once you have access to a device database, you can proceed with
the instructions in this section.

$$$

You can access the Mobile Device Families administrative page from the
Configuration section of Site Administration. Make sure you're on the
appropriate site before adding mobile device families via Site Administration.
You can also add families for all sites by navigating to the Control Panel
&rarr; *Sites* &rarr; *Global*. The Mobile Device Families administrative page
displays a list of defined families and lets you add more. To add rules to a
family, select the device family name and select the *Add* button 
(![Add Classification Rule](../../../images/icon-add.png)).

![Figure 1: You can manage device rules by clicking on a Mobile Device Family.](../../../images/mobile-device-families.png)

The rules defined for a family, along with the priorities of the families
selected for a particular site or page, determine which family's actions are
applied to a given request. From the New Classification Rule page for a
specific rule set, you can add a rule by specifying an operating system, rule
type, physical screen size, and screen resolution. Remember that you can add as
many rules to a family as you need in order to classify the devices on which
you'd like to take actions. You'll notice after saving the classification rule
that it's characterized as a *Simple Rule*. By default, only the Simple Rule
type is available. The rules are designed to be extensible, and additional rule
types can be added by your developers.

![Figure 2: Select the operating system and device type for your rule.](../../../images/mobile-device-editing-rule.png)

Once you've created some mobile device families and added some rules to them,
you'll be ready to set up some actions. The actions defined for a family
determine what happens to a particular request when the device is detected and
the family has been found to apply.

+$$$

**Tip:** The Audience Targeting application offers a *Device* rule that
evaluates whether the user is accessing content using a particular device
family. This rule is integrated with the Mobile Device Families app. Visit
the [Liferay Audience Targeting Rules](/discover/portal/-/knowledge_base/6-2/liferay-audience-targeting-rules#device)
section for more details.

$$$

<!-- Update the link above for 7.0, when available. -Cody -->

You can add actions to a family from the Navigation menu of Site Administration.
Select a page set's (e.g., Public Pages) *Options*
(![Options](../../../images/icon-options.png)) &rarr; *Configure* button and
then select the *Advanced* tab and the *Mobile Device Rules* option in the
bottom menu. Use the *Select* button to select families to be applied either to
a page group or to a single page. If you select the page group itself from the
left-hand menu, the selected family applies to all the pages of the site by
default. If, however, you select an individual page and then click the *Select*
button, the families apply only to that page. You can select multiple families
for a particular site or page and order them by priority. The families are
checked in decreasing order of priority: the actions defined by the first family
that applies are executed.

![Figure 3: You can select a mobile device family to apply for a site or page from the Navigation section of Site Administration.](../../../images/mobile-device-selection.png)

To add actions to a selected rule group, use the *Actions*
(![Actions](../../../images/icon-actions.png)) &rarr; *Manage Actions* button
and then click *Add Action*. By default, there are four kinds of actions that
can be configured for mobile families: layout template modifications, theme
modifications, URL redirects, and site redirects. Layout template modifications
let you change the way portlets are arranged on pages delivered to mobile
devices, and themes modifications let you select a specific look and feel. If it
makes more sense for you to create separate mobile versions of certain sites or
pages, you can use a redirect to make sure mobile device users get to the right
page. To define a URL redirect, you need to specify a URL. To define a site
redirect, you need only specify the site name and page name of the page to which
you're redirecting. Like mobile device rules, mobile device actions are designed
to be extensible. Your developers can define custom actions in addition to the
four actions provided by default.

<!-- Couldn't check *Manage Actions* interface/functionality due to LPS-64354.
Need to confirm the instructions above still describe the same thing. -Cody -->

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

To see how this might work in practice, you'll study a few examples of how you
can use mobile device rules. First, suppose you have a separate version of a
site on your Liferay instance that's specifically designed for mobile phones
running Android or Bada. For this example, you'll make a site called
*Android/Bada Liferay* and you'll configure the default Liferay site to redirect
incoming requests from Android or Bada mobile phones to the Android/Bada Liferay
site. Yur first step is to create the Android/Bada Liferay site: go to the Sites
option of the Control Panel and click *Add*
(![Add](../../../images/icon-add.png)) &rarr; *Blank Site*. Enter the name
*Android/Bada Liferay* and click *Save*. Then add a page called *Welcome* to
that site. Now your Android/Bada Liferay site has a public Welcome page just
like the default Liferay site.

Next, select the default *Liferay* site in the site selector of the Menu,
navigate to Site Administration &rarr; *Configuration*, and click on *Mobile
Device Families*. Click on *Add Device Family*
(![Add Device Family](../../../images/icon-add.png)), enter the name *Android
and Bada Mobile Phones*, and click *Save*.

Click the device family link to configure the rule group to apply only to mobile
phones running Android or Bada. Enter *Rule 1* for the name. Under Operating
System, select *Android* and *Bada OS* (hold down Control to make multiple
selections), select *Other Devices* under Tablet since you want your family to
apply only to mobile phones, and click *Save*. Now you just need to define the
redirect action for your family. Navigate to *Navigation*, select *Options* next
to Public Pages and click on *Advanced* &rarr; *Mobile Device Rules* in the
bottom navigation menu.

![Figure 3.31: To apply a mobile device family to a page set of a site, click on *Mobile Device Rules*, click *Select*, and select the desired rule group.](../../../images/site-pages-mobile-device-rules.png)

Click *Select* and then click the *Android and Bada Mobile Phones* device family
that you configured. Once you've selected your device family, click on your
device family's *Actions* &rarr; *Manage Actions*. Then click *Add Action*,
enter the name *Android/Bada Liferay Redirect*, and select *Redirect to Site*
under Type. Under the Site dropdown menu that appears, select *Android/Bada
Liferay* and under the Page dropdown menu that appears, select the *Welcome*
page that you created earlier. Lastly, click *Save*. That's it! Now Android and
Bada mobile phone users are redirected to the Android/Bada Liferay site from the
Liferay site.

<!-- Couldn't check *Manage Actions* interface/functionality due to LPS-64354.
Need to confirm the instructions above still describe the same thing. -Cody -->

Now you'll look at one more example of using mobile device rules before you move
on. Suppose you'd like to create another rule so that when a site is accessed by
an Android or iOS tablet, a different layout is used. To set this up, you need
to follow the same four steps described above. First, make sure you're on the
Liferay site by checking in the site selector of the Menu. Then navigate to the
Mobile Device Families page of Site Administration. Add a new device family
called *Android and iOS Tablets*. Add a classification rule called *Rule 1*,
select *Android and iPhone OS* under the *Operating System* heading, select
*Tablets* under the *Device Type* heading, then click *Save*. As with the
previous example, you only need one rule to describe your device family.

Next, click on *Navigation* in Site Administration, select *Mobile Device
Rules*, and select the *Android and iOS Tablets* device family. Notice that
you've now selected two rule groups for the Liferay site's public pages and
they've been assigned priorities. If a device making a request belongs to both
of the device families represented by the rule groups, the priority of the rule
groups determines which rule group's actions are executed. Note that in this
example, the first rule group contains only mobile phones and the second rule
group contains only tablets, so no devices can belong to both rule groups. Now
you just need to define an action for your Android and iOS Tablets rule group to
use a different layout: On the *Edit* page of your page group, click on
*Mobile Device Rules*, and then on *Actions* &rarr; *Manage Actions* next to
Android and iOS Tablets. Click on *Add Action*, enter the name *Layout Template
Modification*, and select the *Layout Template Modification* action type.
Lastly, select the *1 Column* layout template (or whichever one you like) and
click *Save*. Good job! Now the Liferay site's pages are presented to Android
and iOS tablet users with the 1 Column layout template.

<!-- Couldn't check *Manage Actions* interface/functionality due to LPS-64354.
Need to confirm the instructions above still describe the same thing. -Cody -->
