---
header-id: sitemap-page-configuration-options
---

# Sitemap Page Configuration Options

[TOC levels=1-4]

If you're importing resources with your themes, you must define the pages for 
the site in the theme's `sitemap.json`. Below is the full list of available 
configuration options for pages in the theme's `sitemap.json`:

**colorSchemeId:** Specifies a different color scheme (by ID) than the default
color scheme to use for the page.

**columns:** Specifies the column contents for the page.

**friendlyURL:** Sets the page's friendly URL.

**hidden:** Sets whether the page is hidden.

**layoutCss:** Sets custom CSS for the page to load after the theme.

**layoutPrototypeLinkEnabled:** Sets whether the page inherits changes made to 
the page template (if the page has one).

**layoutPrototypeName:** Specifies the page template (by name) to use for the 
page. If this is defined, the page template's UUID is retrieved using the
name, and `layoutPrototypeUuid` is not required. 

**layoutPrototypeUuid:** Specifies the page template (by UUID) to use for the 
page. If `layoutPrototypeName` is defined, this is not required. 

**layouts:** Specifies child pages for a page set.

**name:** The page's name.

**nameMap:** Passes a name object with multiple name key/value pairs. You can 
use this to pass translations for a page's title, as shown in the example above. 

**privatePages:** Specifies private pages.

**publicPages:** Specifies public pages.

**themeId:** Specifies a different theme (by ID) than the default theme bundled
with the `sitemap.json` to use for the page.

**title:** The page's title.

**type:** Sets the page type. The default value is `portlet` (empty page). 
Possible values are `copy` (copy of a page of this site), `embedded`, 
`full_page_application`, `link_to_layout`, `node` (page set), `panel`, 
`portlet`, and `url` (link to URL).

**typeSettings:** Specifies settings (using key/value pairs) for the page 
`type`.
