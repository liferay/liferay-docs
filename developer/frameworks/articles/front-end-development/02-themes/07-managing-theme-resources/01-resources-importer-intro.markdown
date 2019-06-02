---
header-id: importing-resources-with-a-theme
---

# Importing Resources with a Theme

[TOC levels=1-4]

To truly appreciate a theme, you must view it with content. Showcasing a theme 
in the proper context is key to communicating the true intentions of its design.
Who better to do this than the theme's designer? Designers can provide a sample
context that optimizes the design of their themes. The Resources Importer does
this for you. 

| **Important:** The Resources Importer is deprecated as of @product-ver@ 7.1.
| 
| 

The Resources Importer module lets theme developers import files and web content
with a theme. Administrators can use the site or site template created by the
Resources Importer to showcase the theme. In fact, all standalone themes that
are uploaded to Liferay Marketplace **must use** the Resources Importer. This
ensures a uniform experience for Marketplace users: a user can download a theme
from Marketplace, install it, go to Sites or Site Templates in the Control Panel
and immediately see their new theme in action. 

## Organizing Your Resources

Add your resources to the theme's `/src/WEB-INF/src/resources-importer` folder 
as outlined below:

- `[theme-name]/src/WEB-INF/src/resources-importer/`
    - `sitemap.json` - defines the pages, layout templates, and portlets
    - `assets.json` - (optional) specifies details on the assets
    - `document_library/`
        - `documents/` - contains documents and media files (assets) 
    - `journal/`
        - `articles/` - contains web content (HTML) and folders grouping web
          content articles (XML) by template. Each folder name must match the
          file name of the corresponding template. For example, create folder
          `Template 1/` to hold an article based on template file 
          `Template 1.ftl`.
        - `structures/` - contains structures (JSON) and folders of child
          structures. Each folder name must match the file name of the
          corresponding parent structure. For example, create folder
          `Structure 1/` to hold a child of structure file `Structure 1.json`.
        - `templates/` - groups templates (VM or FTL) into folders by structure.
          Each folder name must match the file name of the corresponding
          structure. For example, create folder `Structure 1/` to hold a
          template for structure file `Structure 1.json`. 

Using the Resources Importer involves the following steps:

- [Preparing and Organizing  Resources](/docs/7-2/frameworks/-/knowledge_base/f/preparing-and-organizing-web-content-for-the-resources-importer)
- [Creating a Sitemap for the Resources Importer](/docs/7-2/frameworks/-/knowledge_base/f/creating-a-sitemap-for-the-resources-importer)
- [Defining Assets for the Resources Importer](/docs/7-2/frameworks/-/knowledge_base/f/defining-assets-for-the-resources-importer) (optional)
- [Specifying Where to Import Your Theme's Resources](/docs/7-2/frameworks/-/knowledge_base/f/specifying-where-to-import-your-themes-resources) 

This section explains how to use the Resources Importer to import resources with 
your theme. 
