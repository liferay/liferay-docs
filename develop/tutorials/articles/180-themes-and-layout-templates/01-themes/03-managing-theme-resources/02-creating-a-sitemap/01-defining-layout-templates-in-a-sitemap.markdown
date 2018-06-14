# Defining Layout Templates in a Sitemap [](id=defining-layout-templates-in-a-sitemap)

The first thing you should declare in your `sitemap.json` file is a default 
layout template ID so the target site or site template can reference the layout 
template to use for its pages. When defined outside the scope of a page, the 
`layoutTemplateId` sets the default layout template for the theme's pages:

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

When placed inside a page configuration, the `layoutTemplateId` sets the layout 
template for the individual page. In the example below, the Hidden Page and the 
Welcome page both use the default `2_columns_ii` layout template, while the 
Custom Layout Page overrides the default layout template and uses the 
`2_columns_i` layout template instead:

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
 

## Related Topics [](id=related-topics)

[Preparing and organizing resources](/develop/tutorials/-/knowledge_base/7-1/preparing-resources-for-the-importer)

[Defining Portlets in a Sitemap](/develop/tutorials/-/knowledge_base/7-1/defining-portlets-in-a-sitemap)

[Specifying where to import the resources](/develop/tutorials/-/knowledge_base/7-1/specifying-where-to-import-resources)
