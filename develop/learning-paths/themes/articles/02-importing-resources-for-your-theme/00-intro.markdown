# Importing Resources for Your Theme

You've created the theme for the Lunar Resort, but it's looking pretty empty at 
the moment. There are a couple methods you can use to import resources with your 
theme. You can create an `archive.lar` file, which will import the LAR with the 
theme, or you can create a `sitemap.json` file to hold your layout and populate 
your content with a directory structure.

Both methods are good options, but there are some disadvantages to using the LAR 
method that you should be aware of. LARs are version dependent, so upgrading to 
a new version of Liferay means having to recreate the LAR. Also any changes made 
to the content would require the LAR to be recreated.

The `sitemap.json` on the other hand will work in any version of Liferay, and 
changes can be made and implemented with a simple redeploy.

In this section, you'll learn how to set up and configure the Resources Importer 
for the Lunar Resort theme using a `sitemap.json`. In order to import content 
with the Resources Importer, you'll need to setup the proper directory structure 
first.