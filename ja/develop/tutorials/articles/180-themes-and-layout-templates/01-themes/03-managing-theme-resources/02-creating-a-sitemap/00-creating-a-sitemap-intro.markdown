---
header-id: creating-a-sitemap-for-the-resources-importer
---

# Resources Importerのサイトマップを作成する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

テーマとともにインポートするリソースを指定するには、サイトマップまたは [アーカイブLARファイル](/docs/7-1/tutorials/-/knowledge_base/t/archiving-your-sites-resources)2つのオプションがあります。 `sitemap.json` ファイルを使用するのが最も柔軟なアプローチなので、お勧めします。 LARファイルとは異なり、 `sitemap.json` を@product@の1つのバージョンで作成し、別のバージョンで使用できます。 LARファイルはバージョン固有であり、作成された同じバージョンにのみインポートできます。

`sitemap.json` は、テーマで提供されるサイトページ、レイアウトテンプレート、Webコンテンツ、アセット、およびポートレット構成を指定します。 このファイルには、サイトまたはサイトテンプレートとしてインポートするサイトのコンテンツと階層が記述されています。 Liferay Marketplaceのテーマを開発している場合、 `sitemap.json` を使用して、テーマとともにインポートするリソースを指定する必要があります。 JSONに慣れていない場合でも、 `sitemap.json` ファイルは理解しやすいものです。 `sitemap.json` ファイルの例を以下に示します。

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
                "type": "link_to_layout",
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

この時点でサイトマップを理解していなくても心配する必要はありません。 チュートリアルのこのセクションでは、テーマのサイトマップを作成する方法について説明します [からページ](/docs/7-1/tutorials/-/knowledge_base/t/defining-pages-in-a-sitemap) を定義し、 [からポートレット](/docs/7-1/tutorials/-/knowledge_base/t/defining-portlets-in-a-sitemap)を定義します。
