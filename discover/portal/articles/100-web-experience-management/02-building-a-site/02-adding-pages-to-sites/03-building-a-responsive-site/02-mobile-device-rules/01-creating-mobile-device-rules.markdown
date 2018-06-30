# Creating Mobile Device Rules [](id=creating-mobile-device-rules)

To configure mobile device rules, you need a way to find out the characteristics
of the device. While some of the characteristics are provided by the device,
most are not. For this reason, there are databases that contain information
about thousands of devices. These databases make it possible to learn every
detail about a device from the device type. @product@'s Mobile Device Rules can
connect to device databases so that you can use their device characteristics in
your rules. 

+$$$

**Important:** For the features described in this article to work, you must 
install the Liferay Mobile Device Detection (LMDD) app from Liferay Marketplace.
This app provides the device detection database that's required to detect which
mobile devices are accessing it. Note that if you're running Liferay DXP, you
must install 
[the lite version of LMDD](https://web.liferay.com/marketplace/-/mp/application/92831494) 
before you can install 
[the enterprise version](https://web.liferay.com/marketplace/-/mp/application/35419014). 
[Click here](/discover/portal/-/knowledge_base/7-1/using-the-liferay-marketplace) 
for instructions on using Liferay Marketplace to find and install apps. 

$$$

You can develop plugins that integrate with other device databases. Even if you
don't have a device database, you can still set up mobile device rules. They
won't, however, be effective until a database is deployed, because the portal
won't have enough information about the devices being used to make page
requests. To learn how to tap into @product@'s Device API, see the 
[Using the Device Recognition API](/develop/tutorials/-/knowledge_base/7-1/using-the-device-recognition-api)
tutorial.

To access the Mobile Device Families administrative page,

1.  Open the *Product Menu*.

2.  Use the *Site Selector* (![Site Selector](../../../../../../images/icon-compass.png)) to choose the Site that you
    want to define Mobile Device Rules for.
 
3.  Select *Configuration* &rarr; *Mobile Device Families*.

You can also add families for all Sites by navigating to the Control Panel
&rarr; *Sites* &rarr; *Global*. The Mobile Device Families administrative page
displays a list of defined families and lets you add more. To add rules, you
must first add a family.

1.  Click *Add* button (![Add Family](../../../../../../images/icon-add.png)) to 
    add a *New Device Family*.

2.  Enter a *Name* and *Description*.

3.  Click *Save*.

4.  Click on the name of the Mobile Device Family to access the rules page.

![Figure 1: Create a Mobile Device Family so you can create rules.](../../../../../../images/mobile-device-families.png)

The rules defined for a family, along with the priorities of the families
selected for a particular Site or page, determine which family's actions are
applied to a given request. From the New Classification Rule page for a
specific rule set, you can add a rule by specifying an operating system, rule
type, physical screen size, and screen resolution. Remember that you can add as
many rules to a family as you need in order to classify the devices on which
you'd like to take actions.

1.  Click on the *Add* button (![Add Classification Rule](../../../../../../images/icon-add.png)) to add a new rule.

2.  Enter a *Name* and *Description*.

3.  Select the classifications you want for this rule from *Operating System and
    Type*, *Physical Screen Size*, and *Screen Resolution*.

4.  Click *Save*.

You'll notice after saving the classification rule that it's characterized as a 
*Simple Rule*. Only Simple Rules are included with @product@, but the rules 
are designed to be extensible, and additional rule types can be added by your 
developers.

![Figure 2: Select the operating system and device type for your rule.](../../../../../../images/mobile-device-editing-rule.png)

Once you've created some mobile device families and added some rules to them,
you're ready to create some actions. The actions defined for a family determine
what happens to a request when the device is detected and the family has been
found to apply.

+$$$

**Tip:** The Audience Targeting application offers a *Device* rule that
evaluates whether a User is accessing content using a particular device family.
This rule is integrated with the Mobile Device Families app. Visit the 
[Liferay Audience Targeting Rules](/discover/portal/-/knowledge_base/7-1/liferay-audience-targeting-rules#device)
section for more details.

$$$

You can add families to a Site, individual page, or page set from their
respective configuration pages. To do it for a Page Set:

1.  Go to *Build* &rarr; *Pages* in your Site.

2.  Click on *Configuration* 
    (![Configure](../../../../../../images/icon-page-gear.png)) for the Public 
    Pages.
 
3.  Select the *Advanced* tab and open the *Mobile Device Rules* option in the
    bottom menu.

4.  Click *Select* to open the list of families that can be applied.

From the same page, you can access the configuration for an individual page, or
you can configure Mobile Device Rules for an entire Site from *Configuration*
&rarr; *Site Settings*. You can select multiple families for a particular Site
or page and order them by priority. The families are checked in decreasing order
of priority: the actions defined by the first family that applies are executed.

![Figure 3: You can select a mobile device family to apply for a Site or page.](../../../../../../images/mobile-device-selection.png)

