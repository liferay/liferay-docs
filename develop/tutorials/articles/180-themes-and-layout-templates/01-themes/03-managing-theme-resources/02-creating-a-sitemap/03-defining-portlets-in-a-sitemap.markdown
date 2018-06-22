# Defining Portlets in a Sitemap [](id=defining-portlets-in-a-sitemap)

You can embed portlets in a sitemap for the pages you define. You can embed them 
with the default settings or provide portlet preferences for a more custom look 
and feel. This tutorial covers both these options. 

## Defining Portlets on a Page [](id=defining-portlets-on-a-page)

To add a portlet to a sitemap, provide its portlet ID. You can find this in the
App Manager of the Control Panel. Select the suite containing the App, click the
App, click the App's web module link, and open the *Portlets* tab that appears.
The portlet ID is displayed below the name of the App:

    {
        "portletId": "com_liferay_journal_content_web_portlet_JournalContentPortlet"
    }

![Figure 1: Portlet IDs are listed in the App Manager.](../../../../../images/resources-importer-app-manager-configuration.png)

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
[Portlet Decorators](/develop/tutorials/-/knowledge_base/7-1/portlet-decorators) 
tutorial for more info. 

+$$$

**Tip:** You can specify an 
[application display template](/discover/portal/-/knowledge_base/7-1/styling-apps-with-application-display-templates) 
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

[Preparing and organizing resources](/develop/tutorials/-/knowledge_base/7-1/preparing-resources-for-the-importer)

[Defining Pages in a Sitemap](/develop/tutorials/-/knowledge_base/7-1/defining-pages-in-a-sitemap)

[Specifying where to import the resources](/develop/tutorials/-/knowledge_base/7-1/specifying-where-to-import-resources)
