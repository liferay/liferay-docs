# Archiving Site Resources [](id=archiving-your-sites-resources)

Although a `sitemap.json` is the recommended approach for including resources 
with a theme, you can also export your site's data in a LAR (Liferay Archive) 
file. A LAR file is version-specific; it won't work on any version of 
@product@ other than the one from which it was exported. This approach does, 
however, require less configuration, since it does not require a sitemap or 
other files. So, if you're using the exported resources in the same version of 
@product@ and it's not for a theme on Liferay Marketplace, you may prefer a LAR 
file. 

To create an `archive.lar`, export the contents of a site using the site scope. 
Then place the `archive.lar` file in your theme's 
`[theme-name]/src/WEB-INF/src/resources-importer` folder. A LAR archive does not 
require a `sitemap.json` file or any other files in your 
`[theme-name]/src/WEB-INF/src/resources-importer` folder. 

## Related Topics [](id=related-topics)
