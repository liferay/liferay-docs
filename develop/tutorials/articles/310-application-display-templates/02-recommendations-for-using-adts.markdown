---
header-id: recommendations-for-using-adts
---

# Recommendations for Using ADTs

[TOC levels=1-4]

You've harnessed a lot of power by learning to leverage the ADT API. Be
careful, for with great power, comes great responsibility! To that end, you'll
learn about some practices you can use to optimize your portlet's performance
and security. 
 
First let's talk about security. You may want to hide some classes or packages
from the template context, to limit the operations that ADTs can perform on your
portal. Liferay provides some portal system settings, which can be accessed by
navigating to *Control Panel* &rarr; *Configuration* &rarr; *System Settings*
&rarr; *Template Engines* &rarr; *FreeMarker Engine*, to define the
restricted classes, packages, and variables. In particular, you may want to add
`serviceLocator` to the list of default values assigned to the FreeMarker Engine
Restricted variables.

Application Display Templates introduce additional processing tasks when your
portlet is rendered. To minimize negative effects on performance, make your
templates as minimal as possible by focusing on the presentation, while using
the existing API for complex operations. The best way to make Application
Display Templates efficient is to know your template context well, and
understand what you can use from it. Fortunately, you don't need to memorize
the context information, thanks to Liferay's advanced template editor!

To navigate to the template editor for ADTs, go to the Site Admin menu and
select *Configuration* &rarr; *Application Display Templates* and then click
*Add* and select the specific portlet on which you decide to create an ADT.

The template editor provides fields, general variables, and util variables
customized for the portlet you chose. These variable references can be found on
the left-side panel of the template editor. You can use them by simply placing
your cursor where you'd like the variable placed, and clicking the desired
variable to place it there. You can learn more about the template editor in the
[Styling Widgets with Application Display Templates](/docs/7-1/user/-/knowledge_base/u/styling-widgets-with-application-display-templates)
section.

Finally, don't forget to run performance tests and tune the template cache
options by modifying the *Resource modification check* field in *System
Settings* &rarr; *Template Engines* &rarr; *FreeMarker Engine*.

The cool thing about ADTs is the power they provide to your portlets,
providing infinite ways of editing your portlet to provide new interfaces for
your portal users. Be sure to configure your FreeMarker templates appropriately
for the most efficient customization process.
