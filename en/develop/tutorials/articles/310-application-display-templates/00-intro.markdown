---
header-id: application-display-templates
---

# Application Display Templates

[TOC levels=1-4]

In the past, when you needed to modify the UI of a widget, you had to use a hook
(e.g., HTML-related change) or a theme (e.g., CSS-related change). It would be
nice to apply particular display changes to specific widget instances without
having to redeploy any plugins. Ideally, you should be able to provide
authorized portal users the ability to apply custom display interfaces to
widgets.

Be of good cheer! That's precisely what
[Application Display Templates](/docs/7-1/user/-/knowledge_base/u/styling-widgets-with-application-display-templates)
(ADTs) provide--the ability to customize the way widgets appear on a page,
removing limitations to the way your site's content is displayed. With ADTs, you
can define custom display templates used to render asset-centric widgets. This
isn't actually a new concept in @product@; some widgets already had templating
capabilities (e.g., *Web Content* and *Dynamic Data  Lists*), in which you can
already add as many display options (or templates) as you want. Now you can add
them to your custom portlets, too.

Some portlets that already support Application Display Templates in 7.1 are

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

Continue on to add support for ADTs in your custom portlet.
