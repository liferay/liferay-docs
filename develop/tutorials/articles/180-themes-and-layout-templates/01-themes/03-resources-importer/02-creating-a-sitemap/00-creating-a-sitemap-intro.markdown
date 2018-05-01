# Creating a Sitemap for the Resources Importer [](id=creating-a-sitemap-for-the-resources-importer)

You have two options for specifying resources to be imported with your theme: a 
sitemap or an 
[archive LAR file](/develop/tutorials/-/knowledge_base/7-1/archiving-your-sites-resources). 
Using a `sitemap.json` file is the most flexible approach, so we recommend it; 
unlike LAR files, a `sitemap.json` can be created in one version of @product@
and used in another. LAR files are version-specific, and can only be imported in
the same version in which they were created. 

The `sitemap.json` specifies the site pages, layout templates, web content, 
assets, and portlet configurations provided with the theme. This file describes 
the contents and hierarchy of the site to import as a site or site template. If 
you're developing themes for Liferay Marketplace, you must use the 
`sitemap.json` to specify resources to be imported with your theme. Even if 
you're not familiar with JSON, the `sitemap.json` file is easy to understand. An 
example `sitemap.json` file is shown below:

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
                            "portletId": 
                            "com_liferay_site_navigation_menu_web_portlet_SiteNavigationMenuPortlet"
                        },
                        {
                            "portletId": 
                            "com_liferay_journal_content_web_portlet_JournalContentPortlet",
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
                            "portletId": 
                            "com_liferay_site_navigation_menu_web_portlet_SiteNavigationMenuPortlet_INSTANCE_${groupId}",
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
                                            "portletId": 
                                            "com_liferay_journal_content_web_portlet_JournalContentPortlet",
                                            "portletPreferences": {
                                                "articleId": "Child Web Content 1.xml",
                                                "groupId": "${groupId}",
                                                "portletSetupPortletDecoratorId": "decorate",
                                                "portletSetupTitle_en_US": 
                                                "Web Content Display with Child Structure 1",
                                                "portletSetupUseCustomTitle": "true"
                                            }
                                        }
                                    ],
                                    [
                                        {
                                            "portletId": 
                                            "com_liferay_journal_content_web_portlet_JournalContentPortlet",
                                            "portletPreferences": {
                                                "articleId": "Child Web Content 2.xml",
                                                "groupId": "${groupId}",
                                                "portletSetupPortletDecoratorId": "decorate",
                                                "portletSetupTitle_en_US": 
                                                "Web Content Display with Child Structure 2",
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

If you don't understand the sitemap at this point, don't worry. This section of 
tutorials covers how to create a sitemap for your theme, from 
[defining pages](/discover/portal/-/knowledge_base/7-1/defining-pages-in-a-sitemap) 
to 
[configuring portlets](/discover/portal/-/knowledge_base/7-1/configuring-portlets-in-a-sitemap). 
