# Customizing Widgets

[TOC levels=1-4]

It would be nice to apply particular display changes to specific widget
instances without having to create a hook (e.g., HTML-related change) or change
a theme (e.g., CSS-related change). Ideally, you should be able to provide
authorized portal users the ability to apply custom display interfaces to
widgets.

Be of good cheer! That's precisely what
[Widget Templates](/docs/7-2/user/-/knowledge_base/u/styling-widgets-with-widget-templates)
provide--the ability to customize the way widgets appear on a page, removing
limitations to the way your site's content is displayed. With Widget Templates,
you can define custom display templates used to render asset-centric widgets.
Some default widgets already have templating capabilities (e.g., *Web Content*
and *Dynamic Data  Lists*), in which you can add as many display options (or
templates) as you want. You can also add them to your custom portlets, too.

Some portlets that already support Widget Templates are

- *Asset Publisher*
- *Blogs*
- *Breadcrumb*
- *Categories Navigation*
- *Language Selector*
- *Media Gallery*
- *Navigation Menu*
- *RSS Publisher*
- *Site Map*
- *Tags Navigation*
- *Wiki*

First, consider some useful tips when applying Widget Templates.

## Recommendations for Using Widget Templates

You can harness a lot of power by leveraging the Widget Template API. Be
careful, for with great power, comes great responsibility! To that end, you'll
learn about some practices you can use to optimize your portlet's performance
and security.
 
First let's talk about security. You may want to hide some classes or packages
from the template context, to limit the operations that Widget Templates can
perform on your portal. @product@ provides some portal system settings, which
can be accessed by navigating to *Control Panel* &rarr; *Configuration* &rarr;
*System Settings* &rarr; *Template Engines* &rarr; *FreeMarker Engine*, to
define the restricted classes, packages, and variables. In particular, you may
want to add `serviceLocator` to the list of default values assigned to the
FreeMarker Engine Restricted variables.

Widget Templates introduce additional processing tasks when your portlet is
rendered. To minimize negative effects on performance, make your templates as
minimal as possible by focusing on their presentation, while using the existing
API for complex operations. The best way to make Widget Templates efficient is
to know your template context well, and understand what you can use from it.
Fortunately, you don't need to memorize the context information, thanks to
@product@'s advanced template editor!

To navigate to the template editor for ADTs, go to the Site Admin menu and
select *Configuration* &rarr; *Widget Templates* and then click *Add* and select
the specific portlet on which you decide to create a custom template.

The template editor provides fields, general variables, and util variables
customized for the portlet you chose. These variable references can be found on
the left-side panel of the template editor. You can use them by simply placing
your cursor where you'd like the variable placed, and clicking the desired
variable to place it there. You can learn more about the template editor in the
[Styling Widgets with Widget Templates](/docs/7-2/user/-/knowledge_base/u/styling-widgets-with-widget-templates)
section.

Finally, don't forget to run performance tests and tune the template cache
options by modifying the *Resource modification check* field in *System
Settings* &rarr; *Template Engines* &rarr; *FreeMarker Engine*.

The cool thing about Widget Templates is the power they provide to your portlets,
providing infinite ways of editing your portlet to provide new interfaces for
your portal users. Be sure to configure your FreeMarker templates appropriately
for the most efficient customization process.

Continue on to add support for Widget Templates in your custom portlet.
