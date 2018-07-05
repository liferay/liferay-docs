# Defining Assets for the Resources Importer [](id=defining-assets-for-the-resources-importer)

The `sitemap.json` file defines the pages of the site or site template to
import---along with the layout templates, portlets, and portlet preferences of
these pages. You may also want to provide details about the assets you include
with the theme. An `assets.json` file lets you provide this information. Create
the `assets.json` in your theme's
`[theme-name]/src/WEB-INF/src/resources-importer` folder. 

Tags can be applied to any asset. Abstract summaries and small images can be 
applied to web content articles. For example, the following `assets.json` file 
specifies two tags for the `company_logo.png` image, one tag for the 
`Custom Title.xml` web content article, and an abstract summary and small image 
for the `Child Web Content 1.json` article structure:

    {
        "assets": [
            {
                "name": "company_logo.png",
                "tags": [
                    "logo",
                    "company"
                ]
            },
            {
                "name": "Custom Title.xml",
                "tags": [
                    "web content"
                ]
            },
            {
                "abstractSummary": "This is an abstract summary.",
                "name": "Child Web Content 1.json",
                "smallImage": "company_logo.png"
            }
        ]
    }

Now you know how to configure assets for your web content!

## Related Topics [](id=related-topics)

[Preparing and Organizing Web Content for the Resources Importer](/develop/tutorials/-/knowledge_base/7-1/preparing-and-organizing-web-content-for-the-resources-importer)

[Creating a Sitemap for the Resources Importer](/develop/tutorials/-/knowledge_base/7-1/creating-a-sitemap-for-the-resources-importer)

[Specifying Where to Import Your Theme's Resources](/develop/tutorials/-/knowledge_base/7-1/specifying-where-to-import-your-themes-resources) 
