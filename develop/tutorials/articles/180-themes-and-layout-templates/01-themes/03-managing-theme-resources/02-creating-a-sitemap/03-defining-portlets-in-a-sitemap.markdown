# Defining Portlets in a Sitemap [](id=defining-portlets-in-a-sitemap)

You can embed portlets in a sitemap for the pages you define. You can embed them 
with the default settings or provide portlet preferences for a more custom look 
and feel. This tutorial covers both these options. 

## Defining Portlets on a Page [](id=defining-portlets-on-a-page)

To add a portlet to a sitemap, provide its portlet ID. You can find this through 
the 
[Gogo Shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell). 
Follow these steps:

1.  Open the Control Panel and go to Configuration &rarr; Gogo Shell.

2.  Run the command `lb [app prefix]`, and locate the app's web bundle. For 
    example, run `lb blogs` to find the blogs web bundle.
    
        100|Active     |   10|Liferay Blogs Web (3.0.7)

3.  Run the command `scr:list [bundle ID]`, and locate the app's component ID. 
    The blogs portlet entry is shown below. The last number preceding the 
    bundle's state is the component ID:

        [ 100] com.liferay.blogs.web.internal.portlet.BlogsPortlet enabled 
        [ 196] [active] 

4.  Run the command `scr:info [component ID]`. For example, to list the info for 
    the blogs portlet component, run `scr:info 196`. Note that the bundle and/or 
    component ID may be different for your instance.

5.  Search for `javax.portlet.name` in the results. `javax.portlet.name`'s value 
    is the portlet ID required for the sitemap. The blogs portlet's ID is shown 
    below:
    
        javax.portlet.name = com_liferay_blogs_web_portlet_BlogsPortlet

![Figure 1: Portlet IDs can be found via the Gogo Shell.](../../../../../images/resources-importer-gogo-shell.png)

This approach embeds the portlet with its default settings. To customize the 
portlet, you must configure the portlet's preferences. 

## Configuring Portlet Preferences [](id=configuring-portlet-preferences)

You can specify portlet preferences for each portlet with the 
`portletPreferences` key. Below is an example for the Web Content Display 
portlet:

    {
        "portletId": "com_liferay_journal_content_web_portlet_JournalContentPortlet",
        "portletPreferences": {
            "articleId": "Custom Title.xml",
            "groupId": "${groupId}",
            "portletSetupPortletDecoratorId": "decorate",
            "portletSetupTitle_en_US": "Web Content Display with Custom Title",
            "portletSetupUseCustomTitle": "true"
        }
    }

**portletSetupPortletDecoratorId:** Specifies the portlet decorator to use for 
the portlet (`borderless` || `barebone` || `decorate`). See the 
[Applying Portlet Decorators to Embedded Portlets](/develop/tutorials/-/knowledge_base/7-1/applying-portlet-decorators-to-embedded-portlets) 
tutorial for more info. 

+$$$

**Tip:** You can specify an 
[application display template](/discover/portal/-/knowledge_base/7-1/styling-apps-and-assets) 
(ADT) for a portlet in the `sitemap.json` file by setting the `displayStyle` and 
`displayStyleGroupId` portlet preferences, as shown in the example below:

    "portletId": "com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet",
        "portletPreferences": {
            "displayStyleGroupId": "10197",
            "displayStyle": "ddmTemplate_6fe4851b-53bc-4ca7-868a-c836982836f4"
    }

$$$

Portlet preferences are unique to each portlet, so first you must determine
which preferences you want to configure. There are two ways to determine the
proper key/value pair for a portlet preference. The first is to set the
portlet preference manually, and then check the values in the
`portletPreferences.preferences` column of the database as a hint for what to
configure in your `sitemap.json`. For example, you can configure the Asset
Publisher to display assets that match the specified asset tags, using the
following configuration:

    "queryName0": "assetTags",
    "queryValues0": "MyAssetTagName"

Another approach is to search each app in your bundle for the keyword 
`preferences--`. This returns some of the app's JSPs that have the portlet 
preferences defined for the portlet.

+$$$

**Note:** Portlet preferences that require an existing configuration, such as a 
tag or category, may require you to create the configuration on the Global site 
first, so that the Resources Importer finds a match when deployed with the 
theme. 

$$$

## Related Topics [](id=related-topics)

[Preparing and Organizing Web Content for the Resources Importer](/develop/tutorials/-/knowledge_base/7-1/preparing-and-organizing-web-content-for-the-resources-importer)

[Defining Pages in a Sitemap](/develop/tutorials/-/knowledge_base/7-1/defining-pages-in-a-sitemap)

[Specifying Where to Import Your Theme's Resources](/develop/tutorials/-/knowledge_base/7-1/specifying-where-to-import-your-themes-resources)
