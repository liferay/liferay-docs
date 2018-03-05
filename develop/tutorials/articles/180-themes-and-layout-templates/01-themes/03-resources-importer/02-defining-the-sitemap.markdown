# Defining the Sitemap [](id=defining-the-sitemap)

You have two options for specifying resources to be imported with your theme.
The recommended approach is to add resource files to the 
[folder structure](/develop/tutorials/-/knowledge_base/7-1/preparing-resources-for-the-importer#organizing-your-resources) 
and to specify the contents of the site or site template in a `sitemap.json`
file (described below). When you create a new theme using the Liferay Theme 
Generator, a default `sitemap.json` file and a 
`liferay-plugin-package.properties` file are created in the `WEB-INF` folder. 
Alternatively, you can use an `archive.lar` file to package the resources you'd 
like your theme to deploy. Both these methods are explained in this tutorial. 

## Creating a Sitemap JSON [](id=creating-a-sitemap-json)

Using a `sitemap.json` file to specify resources is the most flexible approach; 
unlike LAR files, a `sitemap.json` can be created in one version of @product@ 
and used in another--LAR files are version specific, only working in the 
@product@ version they were created in. The `sitemap.json`  specifies the site 
pages, layout templates, web content, assets, and portlet configurations 
provided with the theme. This file describes the contents and hierarchy of the 
site for Liferay to import as a site or site template. If you're developing 
themes for Liferay Marketplace, you should use the `sitemap.json` to specify 
resources to be imported with your theme. 

+$$$

**Note:** Site templates only support the importing of either public page sets 
or private page sets. 

If you want to import both public and private page sets, as shown in the example 
`sitemap.json` below, you must import your resources into a site. 

$$$

Even if you're not familiar with JSON, the `sitemap.json` file is easy to 
understand. Let's examine a sample `sitemap.json` file. 

### Defining a Default Layout Template for Pages [](id=defining-a-default-layout-template-for-pages)

The first thing you should declare in your `sitemap.json` file is a default 
layout template ID so the target site or site template can reference the layout 
template to use for its pages. You can also specify different layout templates 
to use for individual pages. You can find layout templates in your @product@ 
installation's `/layouttpl` folder:

    {
    "layoutTemplateId": "2_columns_ii",  
    ...

Next you can learn how to define pagesets and pages in your sitemap.

### Adding Pagesets and Pages [](id=adding-pagesets-and-pages)

A sitemap defines the layouts--pages--that your site or site template should 
use. Note that pages are called *layouts* in @product@'s code. You can specify a 
name, title, and friendly URL for a page, and you can set a page to be hidden. 
Both pagesets--public and private--can be defined within a sitemap. Only public 
pages are required:<!-- Is this true? -->

    "privatePages": [
        {
          "friendlyURL": "/private-page",
      		"name": "Private Page",
      		"title": "Private Page"
        }
    ],
    "publicPages": [
        {
          "friendlyURL": "/welcome-page",
          "name": "Welcome",
          "title": "Welcome"
        },
        {
          "friendlyURL": "/custom-layout-page",
          "name": "Custom Layout Page",
          "title": "Custom Layout Page",
          "layoutTemplateId": "2_columns_i"
        },
        {
          "friendlyURL": "/hidden-page",
          "name": "Hidden Page",
          "title": "Hidden Page",
          "hidden": "true"
        }
    ]
        
You can create child pages by defining the `layouts` element for a page:

    {      
    "friendlyURL": "/parent-page",
    "layouts": [
        {
            "friendlyURL": "/child-page-1",
            "name": "Child Page 1",
            "title": "Child Page 1"
        },
        {
            "friendlyURL": "/child-page-2",
            "name": "Child Page 2",
            "title": "Child Page 2"
        }
    ],
    "name": "Parent Page",
    "title": "Parent Page"
    }
    
Next you can learn how to define portlets in your sitemap.

### Adding Portlets [](id=adding-portlets)

You can define portlets by specifying their portlet IDs, which can be found in 
the App Manager of the Control Panel. Select the suite that the App is located 
in, click the App, click the App web link, and open the *Portlets* tab that 
appears. The portlet ID is displayed below the name of the App. You can also 
specify portlet preferences for each portlet:

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

+$$$

**Note:** Portlet preferences set in `sitemap.json` are saved in the database to 
the column: `portletPreferences.preferences`. To determine the proper key:value 
pair for a portlet preference, there are a couple approaches you can take.

You can manually set the portlet preference in @product@, and then check the 
values in this column of the database as a hint for what to configure in your 
`sitemap.json`. For example, you can configure the Asset Publisher to display 
assets that match the specified asset tags, using the following configuration:

    "queryName0": "assetTags",
    "queryValues0": "MyAssetTagName"

You can also search each app in your @product@ bundle for the keyword 
`preferences--`. This returns some of the app's JSPs that have the portlet 
preferences defined for the portlet.

Note that portlet preferences that require an existing configuration, such as a 
tag or category, may require you to create the configuration on the Global site 
first, so that the Resources Importer finds a match when deployed with the theme.  

$$$

+$$$

**Tip:** You can specify an application display template (ADT) for a portlet in
the `sitemap.json` file by setting the `displayStyle` and `displayStyleGroupId`
portlet preferences. For example:

    "portletId": "com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet",
        "portletPreferences": {
            "displayStyleGroupId": "10197",
            "displayStyle": "ddmTemplate_6fe4851b-53bc-4ca7-868a-c836982836f4",
    }

To learn more about ADTs, visit the 
[Styling Apps with Application Display Templates](/discover/portal/-/knowledge_base/7-1/styling-apps-with-application-display-templates) 
chapter. 

$$$

The available sitemap properties are shown next.

### Sitemap Properties [](id=sitemap-properties)

So far, you've seen a few examples of the different types of information you can 
add to your sitemap. Below is a full list of the properties that are available 
in the `sitemap.json`: <!-- Have these updated for 7.1? -->

**colorSchemeId:** Specifies a different color scheme (by ID) than the default
color scheme to use for the layout.

**columns:** Specifies the column contents for the layout.

**friendlyURL:** Sets the layout's friendly URL.

**hidden:** Sets whether the layout is hidden.

**layoutCss:** Sets custom CSS for the layout to load after the theme.

**layoutPrototypeLinkEnabled:** Sets whether the layout inherits changes made to 
the page template (if the layout has one).

**layoutPrototypeName:** Specifies the page template (by name) to use for the 
layout. If this is defined, the page template's UUID is retrieved using the
name, and `layoutPrototypeUuid` is not required. 

**layoutPrototypeUuid:** Specifies the page template (by UUID) to use for the 
layout. If `layoutPrototypeName` is defined, this is not required.

**layoutTemplateId:** When defined outside the scope of a portlet, sets the 
default layout template for the theme's layouts. When placed inside a layout, 
sets the layout template for the layout. 

**layouts:** Specifies child pages for a layout set (`publicPages` || 
`privatePages`).

**name:** The layout's name.

**nameMap:** Passes a name object with multiple name key/value pairs. As shown 
in the example sitemap above, you can use this to pass language keys for layout 
names.

**portletPreferences:** Specifies the portlet's preferences. See the 
[Adding Portlets](/develop/tutorials/-/knowledge_base/7-1/defining-the-sitemap#adding-portlets) 
section above for more information.

**portletSetupPortletDecoratorId:** Specifies the portlet decorator to use 
for the portlet (`borderless` || `barebone` || `decorate`). See the 
[Portlet Decorators](/develop/tutorials/-/knowledge_base/7-1/portlet-decorators) 
tutorial for more info.

**portlets:** specifies the portlets to display in the layout's column. To nest 
portlets, recursively use columns as shown in the example `sitemap.json` above 
for the `com_liferay_nested_portlets_web_portlet_NestedPortletsPortlet` portlet. 

**privatePages:** Specifies private layouts.

**publicPages:** Specifies public layouts.

**themeId:** Specifies a different theme (by ID) than the default theme bundled
with the `sitemap.json` to use for the layout.

**title:** The layout's title.

**type:** Sets the layout type. The default value is `portlet` (empty page). 
Possible values are `copy` (copy of a page of this site), 
`embedded`, `full_page_application`, `link_to_layout`, `node` (page set), 
`panel`, `portlet`, and `url` (link to URL).

**typeSettings:** Specifies settings (using key/value pairs) for the layout 
`type`.

### Sitemap Example [](id=sitemap-example)

Below is a full example `sitemap.json` file:

    {
        "layoutTemplateId": "2_columns_ii",
        "privatePages": [
            {
                "friendlyURL": "/private-page",
                "name": "Private Page",
                "title": "Private Page"
            }
        ],
        "publicPages": [
            {
                "columns": [
                    [
                        {
                            "portletId": "com_liferay_login_web_portlet_LoginPortlet"
                        },
                        {
                            "portletId": "com_liferay_site_navigation_menu_web_portlet_SiteNavigationMenuPortlet"
                        },
                        {
                            "portletId": "com_liferay_journal_content_web_portlet_JournalContentPortlet",
                            "portletPreferences": {
                                "articleId": "Without Border.html",
                                "groupId": "${groupId}",
                                "portletSetupPortletDecoratorId": "borderless"
                            }
                        },
                        {
                            "portletId": "com_liferay_journal_content_web_portlet_JournalContentPortlet",
                            "portletPreferences": {
                                "articleId": "Custom Title.html",
                                "groupId": "${groupId}",
                                "portletSetupPortletDecoratorId": "decorate",
                                "portletSetupTitle_en_US": "Web Content Display with Custom Title",
                                "portletSetupUseCustomTitle": "true"
                            }
                        }
                    ],
                    [
                        {
                            "portletId": "com_liferay_hello_world_web_portlet_HelloWorldPortlet"
                        },
                        {
                            "portletId": "com_liferay_site_navigation_menu_web_portlet_SiteNavigationMenuPortlet_INSTANCE_${groupId}",
                            "portletPreferences": {
                                "displayStyle": "[custom]",
                                "headerType": "root-layout",
                                "includedLayouts": "all",
                                "nestedChildren": "1",
                                "rootLayoutLevel": "3",
                                "rootLayoutType": "relative"
                            }
                        },
                            "Web Content with Image.html",
                        {
                            "portletId": "com_liferay_nested_portlets_web_portlet_NestedPortletsPortlet",
                            "portletPreferences": {
                                "columns": [
                                    [
                                        {
                                            "portletId": "com_liferay_journal_content_web_portlet_JournalContentPortlet",
                                            "portletPreferences": {
                                                "articleId": "Child Web Content 1.xml",
                                                "groupId": "${groupId}",
                                                "portletSetupPortletDecoratorId": "decorate",
                                                "portletSetupTitle_en_US": "Web Content Display with Child Structure 1",
                                                "portletSetupUseCustomTitle": "true"
                                            }
                                        }
                                    ],
                                    [
                                        {
                                            "portletId": "com_liferay_journal_content_web_portlet_JournalContentPortlet",
                                            "portletPreferences": {
                                                "articleId": "Child Web Content 2.xml",
                                                "groupId": "${groupId}",
                                                "portletSetupPortletDecoratorId": "decorate",
                                                "portletSetupTitle_en_US": "Web Content Display with Child Structure 2",
                                                "portletSetupUseCustomTitle": "true"
                                            }
                                        }
                                    ]
                                ],
                                "layoutTemplateId": "2_columns_i"
                            }
                        }
                    ]
                ],
                "friendlyURL": "/home",
                "nameMap": {
                    "en_US": "Welcome",
                    "fr_FR": "Bienvenue"
                },
                "title": "Welcome"
            },
            {
                "columns": [
                    [
                        {
                            "portletId": "com_liferay_login_web_portlet_LoginPortlet"
                        }
                    ],
                    [
                        {
                            "portletId": "com_liferay_hello_world_web_portlet_HelloWorldPortlet"
                        }
                    ]
                ],
                "friendlyURL": "/layout-prototypes-parent-page", 
                "layouts": [
                    {
                        "friendlyURL": "/layout-prototypes-page-1",
                        "layoutPrototypeLinkEnabled": "true",
                        "layoutPrototypeUuid": "371647ba-3649-4039-bfe6-ae32cf404737",
                        "name": "Layout Prototypes Page 1",
                        "title": "Layout Prototypes Page 1"
                    },
                    {
                        "friendlyURL": "/layout-prototypes-page-2",
                        "layoutPrototypeUuid": "c98067d0-fc10-9556-7364-238d39693bc4",
                        "name": "Layout Prototypes Page 2",
                        "title": "Layout Prototypes Page 2"
                    }
                ],
                "name": "Layout Prototypes",
                "title": "Layout Prototypes"
            },
            {
                "columns": [
                    [
                        {
                            "portletId": "com_liferay_login_web_portlet_LoginPortlet"
                        }
                    ],
                    [
                        {
                            "portletId": "com_liferay_hello_world_web_portlet_HelloWorldPortlet"
                        }
                    ]
                ],
                "friendlyURL": "/parent-page",
                "layouts": [
                    {
                        "friendlyURL": "/child-page-1",
                        "name": "Child Page 1",
                        "title": "Child Page 1"
                    },
                    {
                        "friendlyURL": "/child-page-2",
                        "name": "Child Page 2",
                        "title": "Child Page 2"
                    }
                ],
                "name": "Parent Page",
                "title": "Parent Page"
            },
            {
                "friendlyURL": "/url-page",
                "name": "URL Page",
                "title": "URL Page",
                "type": "url"
            },
            {
                "friendlyURL": "/link-page",
                "name": "Link to another Page",
                "title": "Link to another Page",
                "type": "link_to_layout"
                "typeSettings": "linkToLayoutId=1"
            },
            {
                "friendlyURL": "/hidden-page",
                "name": "Hidden Page",
                "title": "Hidden Page",
                "hidden": "true"
            }
        ]
    }

Now that you understand how to configure a `sitemap.json`, you can learn how to 
create an `archive.lar` file if you prefer.

## Creating Archive LAR Files [](id=creating-archive-lar-files)

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

[Preparing and organizing resources](/develop/tutorials/-/knowledge_base/7-1/preparing-resources-for-the-importer)

[Defining the Assets](/develop/tutorials/-/knowledge_base/7-1/defining-the-assets)

[Specifying where to import the resources](/develop/tutorials/-/knowledge_base/7-1/specifying-where-to-import-resources) 
