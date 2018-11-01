# Retrieving Portlet IDs with the Gogo Shell [](id=retrieving-portlet-ids-with-the-gogo-shell)

To 
[include a portlet in
a sitemap](/develop/tutorials/-/knowledge_base/7-1/defining-portlets-in-a-sitemap),
you must have its ID. For convenience, IDs for out-of-the-box portlets appear in
the [Fully Qualified Portlet IDs](/develop/reference/-/knowledge_base/7-1/fully-qualified-portlet-ids)
reference guide. If you've installed purchased or developed portlets, you can
retrieve their IDs using the Gogo Shell, as this tutorial instructs. 

Follow these steps to use the Gogo Shell to retrieve a portlet ID:

1.  Open the Control Panel and go to Configuration &rarr; Gogo Shell.

2.  Run the command `lb [app prefix]`, and locate the app's web bundle. For 
    example, run `lb blogs` to find the blogs web bundle.
    
        100|Active     |   10|Liferay Blogs Web (3.0.7)

3.  Run the command `scr:list [bundle ID]`, and locate the app's component ID. 
    The blogs portlet entry is shown below. The last number preceding the 
    bundle's state is the component ID:

        [ 100] com.liferay.blogs.web.internal.portlet.BlogsPortlet enabled 
        [ 196] [active] 

4.  Run the command `scr:info [component ID]` to list the portlet's information. 
    For example, to list the info for the blogs portlet component, run 
    `scr:info 196`. Note that the bundle and/or component ID may be different 
    for your instance.

5.  Search for `javax.portlet.name` in the results. `javax.portlet.name`'s value 
    is the portlet ID required for the sitemap. The blogs portlet's ID is shown 
    below:
    
        javax.portlet.name = com_liferay_blogs_web_portlet_BlogsPortlet

![Figure 1: Portlet IDs can be found via the Gogo Shell.](../../../../../images/resources-importer-gogo-shell.png)

## Related Topics [](id=related-topics)

[Defining Pages in a Sitemap](/develop/tutorials/-/knowledge_base/7-1/defining-pages-in-a-sitemap)

[Defining Portlets in a Sitemap](/develop/tutorials/-/knowledge_base/7-1/defining-portlets-in-a-sitemap)

[Preparing and Organizing Web Content for the Resources Importer](/develop/tutorials/-/knowledge_base/7-1/preparing-and-organizing-web-content-for-the-resources-importer)
