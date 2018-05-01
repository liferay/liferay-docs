# Defining Pages in a Sitemap [](id=defining-pages-in-a-sitemap)

A sitemap defines the layouts---pages---that your site or site template uses.
This tutorial covers the configuration options that are available for pages in
a sitemap. 

+$$$

**Note:** Pages are imported into a site template by default. Site templates 
only support the importing of either public page sets or private page sets, not 
both. 

If you want to import both public and private page sets, as shown in the example 
`sitemap.json` below, you must 
[import your resources into a site](/develop/tutorials/-/knowledge_base/7-1/specifying-where-to-import-your-themes-resources#importing-resources-into-existing-site-templates-and-sites). 

$$$

You can specify a name for a page, title, friendly URL, whether it is hidden, 
and much more. The example below defines a 
[default layout template](/develop/tutorials/-/knowledge_base/7-1/defining-layout-templates-in-a-sitemap) 
and both public and private page sets to import into a site:

    {
      "layoutTemplateId": "2_columns_ii",
      "privatePages": [
          {
            "friendlyURL": "/private-page",
        		"name": "Private Page",
        		"title": "Private Page"
          }
      ],
      "publicPages": [
          {
            "friendlyURL": "/welcome-page",
            "nameMap": {
                "en_US": "Welcome",
                "fr_FR": "Bienvenue"
            },
            "title": "Welcome"
          },
          {
            "friendlyURL": "/custom-layout-page",
            "name": "Custom Layout Page",
            "title": "Custom Layout Page",
            "layoutTemplateId": "2_columns_i"
          },
          {
            "friendlyURL": "/hidden-page",
            "name": "Hidden Page",
            "title": "Hidden Page",
            "hidden": "true"
          }
      ]
    }

You can create child pages by configuring the `layouts` element for a page 
configuration:

    {      
    "friendlyURL": "/parent-page",
    "layouts": [
        {
            "friendlyURL": "/child-page-1",
            "name": "Child Page 1",
            "title": "Child Page 1"
        },
        {
            "friendlyURL": "/child-page-2",
            "name": "Child Page 2",
            "title": "Child Page 2"
        }
    ],
    "name": "Parent Page",
    "title": "Parent Page"
    }

These examples use some of the available page configuration attributes. The full 
list is shown below.

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

## Related Topics [](id=related-topics)

[Preparing and organizing resources](/develop/tutorials/-/knowledge_base/7-1/preparing-resources-for-the-importer)

[Defining Layout Templates in a Sitemap](/develop/tutorials/-/knowledge_base/7-1/defining-layout-templates-in-a-sitemap)

[Specifying where to import the resources](/develop/tutorials/-/knowledge_base/7-1/specifying-where-to-import-resources)
