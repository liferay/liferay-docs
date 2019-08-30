---
header-id: customizing-widgets
---

# Customizing Widgets

[TOC levels=1-4]

It would be nice to apply display changes to specific widget instances without
having to create a hook (e.g., HTML-related change) or change a theme (e.g.,
CSS-related change). Ideally, you should be able to enable authorized users to
apply custom display interfaces to widgets.

Be of good cheer! That's precisely what
[Widget Templates](/docs/7-2/user/-/knowledge_base/u/styling-widgets-with-widget-templates)
provide. Now you can customize the way widgets appear on a page, removing
limitations to the way content is displayed. With Widget Templates, you can
define display templates to render asset-centric widgets. Some default widgets
already have templating capabilities (e.g., *Web Content* and *Dynamic Data
Lists*), in which you can add as many display options (or templates) as you
want. You can also add them to your own applications.

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

To leverage the Widget Template API, follow these steps: 

- register your portlet to use Widget Templates
- define your display template definitions
- define permissions
- expose the Widget Template functionality to users

The detailed steps are in the
[Implementing Widget Templates](/docs/7-2/customization/-/knowledge_base/c/implementing-widget-templates)
article. Here's a high level overview of what you'll do. 

## Implementing the TemplateHandler Interface

To register your portlet to use Widget Templates, you must implement the
[`TemplateHandler`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/template/TemplateHandler.html)
interface. Read the interface's Javadoc for more information on each method
provided by the interface.

Each of the methods in this class have a significant role in defining and
implementing Widget Templates for your custom portlet. The list below highlights
some of the methods defined specifically for Widget Templates:

`getClassName()`: Defines the type of entry your portlet is rendering.

`getName()`: Declares the name of your Widget Template type (typically,
the name of the portlet).

`getResourceName()`: Specifies which resource is using the Widget
Template (e.g., a portlet) for permission checking. This method must return
the portlet's fully qualified portlet ID (e.g.,
`com_liferay_wiki_web_portlet_WikiPortlet`).

`getTemplateVariableGroups()`: Defines the variables exposed in the
template editor.

`getTemplatesConfigPath()`: Defines the configuration file containing the
display template definition.

Next, you must define your display template definition(s).

## Defining Display Template Definitions

Once you've registered your portlet to use Widget Templates, you should create
the display template definitions. These are used to style the content displayed
in the widget.

You must create a `portlet-display-templates.xml` configuration file to define
the definitions and point to their styled templated (e.g., FreeMarker). Then
you must create the templates. These template definitions are available to apply
from a widget's Configuration menu.

Next, you define permissions for your portlet's Widget Templates.

## Defining Permissions

You must define permissions for your Widget Templates; without permissions,
anyone in the Site could access and change your widget's display templates.
Configuring permissions lets administrative users grant permissions only to the
Roles that should create and manage display templates.

This is done by creating a `default.xml` file in your portlet defining the
permissions you want to enforce, wiring it up with your portlet, and
configuring them for use in @product@. You can visit
[this article](/docs/7-2/customization/-/knowledge_base/c/implementing-widget-templates)
for step-by-step instructions on how to complete this.

Next, you'll learn how to expose Widget Template selection for users.

## Exposing the Widget Template Selection

To expose the Widget Template option to your users, use the
`<liferay-ui:ddm-template-selector>` tag in the JSP file that controls
your portlet's configuration. This tag requires the following parameters:

`className`: your entity's class name.

`contextObjects`: accepts a `Map<String, Object>` with any object you want
to the template context.

`displayStyle`: your portlet's display style.

`displayStyleGroupId`: your portlet's display style group ID.

`entries`: accepts a list of your entities (e.g., `List<YourEntity>`).

The variables `displayStyle` and `displayStyleGroupId` are preferences that your
portlet stores when you use this taglib and your portlet uses the
[`BaseJSPSettingsConfigurationAction`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/BaseJSPSettingsConfigurationAction.html)
or
[`DefaultConfigurationAction`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/DefaultConfigurationAction.html).
Otherwise, you must obtain the value of those parameters and store them
manually in your configuration class.

## Recommendations for Using Widget Templates

You can harness a lot of power by leveraging the Widget Template API. Be
careful, for with great power, comes great responsibility! Here are some
practices you can use to optimize your portlet's performance and security.
 
First let's talk about security. You may want to hide some classes or packages
from the template context to limit the operations that Widget Templates can
perform. @product@ provides some system settings, which can be accessed by
navigating to *Control Panel* &rarr; *Configuration* &rarr; *System Settings*
&rarr; *Template Engines* &rarr; *FreeMarker Engine*, to define the restricted
classes, packages, and variables. In particular, you may want to add
`serviceLocator` to the list of default values assigned to the FreeMarker Engine
Restricted variables.

Widget Templates introduce additional processing tasks when your portlet is
rendered. To minimize negative effects on performance, make your templates as
minimal as possible by focusing on their presentation, while using the existing
API for complex operations. The best way to make Widget Templates efficient is
to know your template context well, and understand what you can use from it.
Fortunately, you don't need to memorize the context information, thanks to
@product@'s advanced template editor!

To navigate to the template editor for Widget Templates, go to the Site Admin
menu and select *Configuration* &rarr; *Widget Templates* and then click *Add*
and select the specific portlet on which you decide to create a custom template.

The template editor provides fields, general variables, and utility variables
customized for the portlet you chose. These variable references are on the
left-side panel of the template editor. Place your cursor where you want the
variable placed and click the desired variable to insert it. You can learn more
about the template editor in 
[Styling Widgets with Widget Templates](/docs/7-2/user/-/knowledge_base/u/styling-widgets-with-widget-templates).

Finally, don't forget to run performance tests and tune the template cache
options by modifying the *Resource modification check* field in *System
Settings* &rarr; *Template Engines* &rarr; *FreeMarker Engine*.

Widget Templates provide power to your portlets by providing infinite ways of
editing your portlet to create new interfaces for your users. Be sure to
configure your FreeMarker templates appropriately for the most efficient
customization process.

Continue on to add support for Widget Templates in your portlet.
