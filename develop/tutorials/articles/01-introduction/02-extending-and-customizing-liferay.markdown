# Extending and customizing Liferay [](id=extending-and-customizing-liferay)

Liferay provides many out-of-the-box features, including a fully featured
content management system, a social collaboration suite, and several
productivity tools. For most installations, these features are exactly what you
need; but sometimes you'll want to extend these features or customize their
behavior and appearance. 

Liferay is designed to be customized. Multiple plugins and plugin types can be
combined into a single WAR file. Let's take a look at these plugin types and how
they can be used. 

## Customizing the look and feel: Themes [](id=customizing-the-look-and-feel-themes)

Themes let you dictate your site's look and feel. You can specify color schemes
and commonly used images. You'll apply styling for UI elements such as fonts,
links, navigation elements, page headers, and page footers, using a combination
of CSS and Velocity or FreeMarker templates. With Liferay's Alloy UI API
framework, you use a consistent interface to common UI elements that make up
your page. This makes it easy to create sites that respond well to the window
widths of your users' desktop, tablet, and mobile devices. Most importantly,
themes let you focus on designing your site's UI, while leaving its
functionality to the portlets. 

## Adding new predefined page layouts: Layout Templates [](id=adding-new-predefined-page-layouts-layout-templates)

Layouts are similar to themes, except they specify the *arrangement* of portlets
on a page rather than their look and feel. You can create custom layout
templates to arrange portlets just the way you like them. And you can even embed
commonly used portlets. Like themes, layout templates are also written in
Velocity and are hot-deployable. 

## Customizing or extending the out-of-box functionality: Hook plugins [](id=customizing-or-extending-the-out-of-box-functionality-hook-plugins)

Hook plugins are how you customize the core functionality of Liferay at many
predefined extension points. Hook plugins are used to modify portal properties
or to perform custom actions on startup, shutdown, login, logout, session
creation, and session destruction. Using service wrappers, a hook plugin can
replace any of the core Liferay services with a custom implementation. Hook
plugins can also replace the JSP templates used by any of the default portlets.
Best of all, hooks are hot-deployable plugins just like portlets. 

## Advanced customization: Ext plugins [](id=advanced-customization-ext-plugins)

Ext plugins provide the largest degree of flexibility in modifying the Liferay
core, allowing you to replace essentially any class with a custom
implementation. However, it is highly unlikely that an Ext plugin written for
one version of Liferay will continue to work in the next version without
modification. For this reason, Ext plugins are only recommended for cases in
which an advanced customization is truly necessary, and there is no alternative.
Make sure you are familiar with the Liferay core so your Ext plugin doesn't
negatively effect existing funcitonality. Even though Ext plugins are deployed
as plugins, the server must be restarted for their customizations to take
effect. 

+$$$

**Note:** If you have developed for Liferay 5.2 or prior releases, you may be
familiar with what was known as the *Extension Environment*. Ext plugins were
introduced in Liferay 6.0 to replace the extension environment in order to
simplify development. For instructions on converting an existing Extension
Environment into a plugin, see the section on migrating old extension
environments in Chapter 8.

$$$

Now that you're familiar with the best options for developing applications on
Liferay and customizing Liferay, let's consider some of the tools you'll be
using.
