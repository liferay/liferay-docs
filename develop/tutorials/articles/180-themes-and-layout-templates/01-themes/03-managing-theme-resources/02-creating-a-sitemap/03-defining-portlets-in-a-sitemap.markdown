# Defining Portlets in a Sitemap [](id=defining-portlets-in-a-sitemap)

You can embed portlets in a sitemap for the pages you define. You can embed them 
with the default settings or provide portlet preferences for a more custom look 
and feel. This tutorial covers both these options. 

Follow these steps:

1.  Note the portlet's ID. This is the `javax.portlet.name` attribute of the 
    portlet spec. For convenience, The IDs for portlets available out-of-the-box 
    are listed in the 
    [Fully Qualified Portlet IDs](/develop/reference/-/knowledge_base/7-1/fully-qualified-portlet-ids) 
    reference guide. For custom portlets, this property is listed in the portlet 
    class as the `javax.portlet.name=` service property.
    
2.  List the portlet ID in the column of the layout you want to display the 
    portlet on. An example configuration is shown below:
    
        {
            "layoutTemplateId": "2_columns_ii",
            "publicPages": [
                {
                    "columns": [
                        [
                            {
                                "portletId": "com_liferay_login_web_portlet_LoginPortlet"
                            },
                            {
                                "portletId": 
                                "com_liferay_site_navigation_menu_web_portlet_SiteNavigationMenuPortlet"
                            }
                        ],
                        [
                            {
                                "portletId": "com_liferay_hello_world_web_portlet_HelloWorldPortlet"
                            }
                        ]
                    ],
                    "friendlyURL": "/home",
                    "nameMap": {
                        "en_US": "Welcome",
                        "fr_FR": "Bienvenue"
                    },
                    "title": "Welcome"
                }
            ]
        }

    This approach embeds the portlet with its default settings. To customize the 
    portlet, you must configure the portlet's preferences, as described in the 
    next step. 
    
3.  Optionally specify portlet preferences for a portlet with the 
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

    **portletSetupPortletDecoratorId:** Specifies the portlet decorator to use 
    for the portlet (`borderless` || `barebone` || `decorate`). See the 
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
