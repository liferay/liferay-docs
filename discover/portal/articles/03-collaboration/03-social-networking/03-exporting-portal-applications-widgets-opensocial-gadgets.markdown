# Exporting Liferay Applications To Other Websites [](id=exporting-portal-applications-as-widgets-and-opensocia)

Liferay can publish its applications to other websites in a variety of formats.
These range from simple widgets to standards like OpenSocial. This lets you
provide your application or content in the context of another website, easily.
Read on to find out how this is done. 

## Sharing OpenSocial Gadgets [](id=sharing-opensocial-gadgets)

OpenSocial consists of a set of APIs for social networking. It may be beneficial
for you to share applications from your Liferay server with other sites, such as
[ighome.com](http://ighome.com) or
[igoogleportal.com](http://igoogleportal.com). These sites let users customize
their own pages with gadgets. Liferay Portal users can share their applications
on any OpenSocial-compatible site. Let's try this now.

For our example, we'll share Liferay's *Loan Calculator*.

1. Add the *Loan Calculator* application onto your Liferay page

2. Go to the Loan Calculator's configuration dialog. 

3. Select the *Sharing* tab and the *OpenSocial Gadget* section. 

4. Check the box labeled *Allow users to add Loan Calculator to an OpenSocial
   platform*. Also, replace "localhost:8080" with the name of your public domain
   and port.

5. Click *Save*. 

6. Close the dialog and click the actions icon. There is a new option named *Add
   to an OpenSocial Platform* available. Click on this button to add your
   application to a page on an OpenSocial platform.

Your application is now available!

By going through this process, Liferay shared the URL of your application to
the OpenSocial platform. The URL you provided is unique to your specific
instance of the application. This allows you to share multiple instances of the
same portlet as different Gadgets.

You could use this sharing capability to let users view what's happening on your
site at a glance. 

