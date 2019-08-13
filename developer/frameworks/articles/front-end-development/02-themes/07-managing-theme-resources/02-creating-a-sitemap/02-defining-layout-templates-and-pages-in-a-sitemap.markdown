---
header-id: defining-layout-templates-and-pages-in-a-sitemap
---

# Defining Layout Templates and Pages in a Sitemap

[TOC levels=1-4]

A sitemap defines the layouts---pages---and layout templates that your site or 
site template uses. Follow these steps to define pages and layout templates in 
your theme's `sitemap.json`:

1.  Define a default layout template ID so the target site or site template can 
    reference the layout template to use for its pages. When defined outside the 
    scope of a page, the `layoutTemplateId` sets the default layout template for 
    the theme's pages:

    ```json
    {
      "layoutTemplateId": "2_columns_ii",
      "publicPages": [
        {
          "friendlyURL": "/my-page",
          "name": "My Page",
          "title": "My Page"
        }
      ]  
    }
    ```

    You can override this by defining a layout template inside a page 
    configuration. In the example below, the Hidden Page and the Welcome page 
    both use the default `2_columns_ii` layout template, while the Custom Layout 
    Page overrides the default layout template and uses the `2_columns_i` layout 
    template instead:

    ```json
    {
      "layoutTemplateId":"2_columns_ii",
      "publicPages": [
          {
            "friendlyURL": "/welcome-page",
            "name": "Welcome",
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
    ```
    
    | **Note:** Pages are imported into a site template by default. Site templates
    | only support the importing of either public page sets or private page sets, not
    | both.
    | 
    | If you want to import both public and private page sets, as shown in the example
    | `sitemap.json` below, you must
    | [import your resources into a site](/docs/7-2/frameworks/-/knowledge_base/f/specifying-where-to-import-your-themes-resources).
  
2.  Follow the pattern below to specify the public and (optionally) private 
    pages for your theme. You can specify a name for a page, title, friendly 
    URL, whether it is hidden, and much more. The example below defines a 
    default layout template and both public and private page sets to import into 
    a site. See [Sitemap Page Configuration Options](/docs/7-2/reference/-/knowledge_base/r/sitemap-page-configuration-options) 
    for a full list of the available options.

    ```json
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
    ```

    You can create child pages by configuring the `layouts` element for a page 
    configuration:

    ```json
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
    ```

Great! Now you know how to configure pages for the Resources Importer. 

## Related Topics

- [Preparing and Organizing Web Content for the Resources Importer](/docs/7-2/frameworks/-/knowledge_base/f/preparing-and-organizing-web-content-for-the-resources-importer)
- [Defining Portlets in a Sitemap](/docs/7-2/frameworks/-/knowledge_base/f/defining-portlets-in-a-sitemap)
- [Specifying Where to Import Your Theme's Resources](/docs/7-2/frameworks/-/knowledge_base/f/specifying-where-to-import-your-themes-resources)
