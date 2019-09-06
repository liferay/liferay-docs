---
header-id: archiving-your-sites-resources
---

# Archiving Site Resources

[TOC levels=1-4]

Although a `sitemap.json` is the recommended approach for including resources 
with a theme, you can also export your site's data in a LAR (Liferay Archive) 
file. A LAR file is version-specific; it won't work on any version of 
@product@ other than the one from which it was exported. This approach does, 
however, require less configuration, since it does not require a sitemap or 
other files. So, if you're using the exported resources in the same version of 
@product@ and it's not for a theme on Liferay Marketplace, you may prefer a LAR 
file. 

Follow these steps to archive your site's resources:

1.  Export the contents of a site using the site scope. 

2.  Place the `archive.lar` file in your theme's 
    `/src/WEB-INF/src/resources-importer` folder. 
    
Great! Now you know how to archive your site's resources. 

## Related Topics

- [Preparing and Organizing Web Content for the Resources Importer](/docs/7-2/frameworks/-/knowledge_base/f/preparing-and-organizing-web-content-for-the-resources-importer)
- [Creating a Sitemap for the Resources Importer](/docs/7-2/frameworks/-/knowledge_base/f/creating-a-sitemap-for-the-resources-importer)
- [Defining Assets for the Resources Importer](/docs/7-2/frameworks/-/knowledge_base/f/defining-assets-for-the-resources-importer)
