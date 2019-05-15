# Updating the Sitemap

In Liferay Portal 6.2, portlet IDs were incremental numbers. In @product-ver@, 
they're explicit class names. Update your `sitemap.json` file with the new 
portlet IDs. 

Some common portlet IDs are specified in the `sitemap.json` example in the 
[Creating a Sitemap for the Resources Importer](/docs/7-2/tutorials/-/knowledge_base/t/creating-a-sitemap-for-the-resources-importer) 
tutorial.

You can also retrieve a portlet's ID from the UI:

1.  In the portlet's *Options* menu, select *Look and Feel Configuration*.
 
    ![Figure 1: You can find the portlet ID in the *Look and Feel Configuration* menu.](../../../../images/upgrading-themes-look-and-feel-menu.png)

2.  Select the *Advanced Styling* tab.

    The `Portlet ID` value appears in the blue box.

![Figure 2: The portlet ID appears within the blue box in the *Advanced Styling* tab.](../../../../images/upgrading-themes-portlet-id.png)

The [Portlet ID Quick Reference Guide](/docs/7-2/reference/-/knowledge_base/r/fully-qualified-portlet-ids)
shows all the default portlet IDs. Check `liferay-portlet.xml` for the portlet
ID number in 6.2 and replace it with the updated ID in the quick reference
Guide. 

| **Remember** to use the updated `.xml` extension for your web content articles
| in your sitemap.