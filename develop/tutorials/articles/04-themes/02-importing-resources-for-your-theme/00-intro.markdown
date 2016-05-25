# Importing Resources for Your Theme [](id=importing-resources-for-your-theme)

You've created the theme for the Lunar Resort, but it's looking pretty empty at 
the moment. You need some default content to go with it, so it looks nice when
it's first installed. There are a couple methods you can use to import resources
with your theme. You can create an `archive.lar` file, which imports the LAR
with the theme, or you can create a `sitemap.json` file to hold your layout and
populate your content from a directory structure.

Both methods are good options, but there are some disadvantages to using the LAR
method. LARs are version dependent, so upgrading to a new version of Liferay
means recreating the LAR. Also any changes made to the content require the LAR
be recreated.

The `sitemap.json` on the other hand works in any version of Liferay. 
You can thus implement changes with a simple redeploy.

In this section, you'll learn how to use the Resources Importer with the Lunar
Resort theme using a `sitemap.json`. In order to import content with the
Resources Importer, you'll need to create the proper directory structure first.
