# Application Display Templates [](id=application-display-templates)

The application display template (ADT) framework allows portal administrators to
override the default display templates, removing limitations to the way your 
site's content is displayed. With ADTs, you can define custom display templates
used to render asset-centric applications.

Usually, when you need to modify the UI of a Liferay portlet, you can do so
using a hook (e.g., HTML-related change) or a theme (e.g., CSS-related change).
It'd be nice, however, if you could apply particular display changes to specific
portlet instances without having to redeploy any plugins. Ideally, you should be
able to provide authorized portal users the ability to apply custom display
interfaces to portlets.

Be of good cheer! That's precisely what
[Application Display Templates](/discover/portal/-/knowledge_base/7-0/styling-apps-with-application-display-templates)
(ADTs) provide--the ability to add custom display templates to your portlets from
the portal. This isn't actually a new concept in Liferay; some applications
already had templating capabilities (e.g., *Web Content* and *Dynamic Data 
Lists*), in which you can already add as many display options (or templates) as
you want. Now you can add them to your custom portlets, too.

Some portlets that already support Application Display Templates in 7.0 are 
*Asset Catgories Navigation*, *Asset Publisher*, *Asset Tags Navigation*, 
*Blogs*, *Media Gallery*, *RSS*, *Breadcrumb*, *Language*, *Navigation Menu*,
*SiteMap*, and *Wiki*.

