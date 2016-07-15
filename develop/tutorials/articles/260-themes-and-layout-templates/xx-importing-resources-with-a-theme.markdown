# Importing Resources with a Theme [](id=importing-resources-with-a-theme)

A theme without content is like an empty house. If you're trying to sell an
empty house, it may be difficult for prospective buyers to see its full beauty.
However, staging the house with some furniture and decorations helps prospective
buyers imagine what the house might look like with their belongings. Liferay's
[resources importer](https://dev.liferay.com/participate/liferaypedia/-/wiki/Main/Resources+Importer) 
module is a tool that allows a theme developer to have files and web content 
automatically imported into @product@ when a theme is deployed. Usually, the 
resources are imported into a site template but they can also be imported 
directly into a site. Liferay Administrators can use the site or site template 
created by the resources importer to showcase the theme. This is a great way for 
theme developers to provide a sample context that optimizes the design of their 
theme. In fact, all standalone themes that are uploaded to Liferay Marketplace 
must use the resources importer. This ensures a uniform experience for 
Marketplace users: a user can download a theme from Marketplace, install it on 
@product@, go to Sites or Site Templates in the Control Panel and immediately 
see their new theme in action. In this tutorial, we explain how to include 
resources with your theme.

+$$$

**Note:** The resources importer has undergone some changes that affect the
properties, class names, and structures that were referred to in versions prior 
to Liferay 7.0.0. Please read through the steps below to see the updates. In 
previous versions of Liferay, you had to deploy the resources importer if you 
declared it as a dependency in your theme's `liferay-plugin-package.properties` 
file. In Liferay 7.0.0 and up, this is no longer a requirement. The resources 
importer is now an OSGI module, and is deployed to your instance by default.

$$$

<!--+$$$(Need to update once new article is written)

**Note:** The [resources importer](https://dev.liferay.com/participate/liferaypedia/-/wiki/Main/Resources+Importer) can be
used in any type of plugin project to import resources. Importing resources
within a theme is just one of the more common use cases. To learn how to
use the resources importer in other types of projects, please see the
[Creating Plugins to Share Structures, Templates, and More](/develop/tutorials/-/knowledge_base/6-2/creating-plugins-to-share-structures-templates-and-more)
tutorial.

$$$-->

<!-- (Need to find replacement example)

Liferay's welcome theme includes resources that the resources importer
automatically deploys to the default site. (Note: The welcome theme is only
applied out-of-the-box in Liferay CE.) The welcome theme and the pages and
content that it imports to the default site provide a good example of the
resources importer's functionality.
-->

When you create a new theme using the Liferay Theme Generator, check your
theme's `src/WEB-INF/liferay-plugin-package.properties` file for the developer
mode entry:

    resources-importer-developer-mode-enabled=true

This is a convenience feature for theme developers. With this setting enabled,
importing resources to a site or site template that already exists, recreates 
the site or site template. Importing resources into a site template reapplies 
the site template and its resources to the sites that are based on the site 
template. Without `resources-importer-developer-mode-enabled=true`, you have to 
manually delete the sites or site templates built by the resources importer, 
each time you want to apply changes from your theme's
`src/WEB-INF/src/resources-importer` folder. 

+$$$

**Warning:** the `resources-importer-developer-mode-enabled=true` setting can be
dangerous since it involves *deleting* (and re-creating) the affected site or
site template. It's only intended to be used during development. Never use it in
production. 

$$$

If you'd like to import your theme's resources directly into a site, instead of
into a site template, you can specify the following in your
`liferay-plugin-package.properties` file:

    resources-importer-target-class-name=com.liferay.portal.kernel.model.Group

    resources-importer-target-value=[site-name]

If you're using the `resources-importer-target-value=[site-name]` property,
double check the site name that you're specifying. If you specify the wrong
value, you could end up deleting (and re-creating) the wrong site!

+$$$

**Warning:** It's safer to import theme resources into a site template than into
an actual site. The
`resources-importer-target-class-name=com.liferay.portal.kernel.model.Group` 
setting can be handy for development and testing but should be used cautiously. 
Don't use this setting in a theme that will be deployed to a production Liferay
instance or a theme that will be submitted to Liferay Marketplace. To prepare a
theme for deployment to a production Liferay instance, use the default setting
so that the resources are imported into a site template. You can do this
explicitly by setting
`resources-importer-target-class-name=com.liferay.portal.kernel.model.LayoutSetPrototype`
or implicitly by commenting out or removing the
`resources-importer-target-class-name` property.

$$$

All of the resources a theme uses with the resources importer go in the
`[theme-name]/src/WEB-INF/src/resources-importer` folder. The assets to be
imported by your theme should be placed in the following directory structure:

- `[theme-name]/src/WEB-INF/src/resources-importer/`
    - `sitemap.json` - defines the pages, layout templates, and portlets
    - `assets.json` - (optional) specifies details on the assets
    - `document_library/`
        - `documents/` - contains documents and media files
    - `journal/`
        - `articles/` - contains web content (HTML) and folders grouping web
          content articles (XML) by template. Each folder name must match the
          file name of the corresponding template. For example, create folder
          `Template 1/` to hold an article based on template file
          `Template 1.ftl`.
        - `structures/` - contains structures (JSON) and folders of child
          structures. Each folder name must match the file name of the
          corresponding parent structure. For example, create folder
          `Structure 1/` to hold a child of structure file `Structure 1.json`.
        - `templates/` - groups templates (VM or FTL) into folders by structure.
          Each folder name must match the file name of the corresponding
          structure. For example, create folder `Structure 1/` to hold a
          template for structure file `Structure 1.json`.

The following is the XML file for a basic web content article:

    <?xml version="1.0"?>

    <root available-locales="en_US" default-locale="en_US">
	    <dynamic-element name="content" type="text_area" index-type="keyword" index="0">
		    <dynamic-content language-id="en_US">
			    <![CDATA[
				    <center>
				    <p><img alt="" src="[$FILE=space-program-history.jpg$]" /></p>
				    </center>

				    <p>In the mid-20th century, after two of the 
				    most violent wars in history, mankind turned 
				    its gaze upwards to the stars. Instead of 
				    continuing to strive against one another, 
				    man choose instead to strive against the 
				    limits that we had bound ourselves to. And 
				    so the Great Space Race began.</p>

				    <p>At first the race was to reach space--get 
				    outside the earth's atmosphere, and when 
				    that had been reached, we shot for the moon. 
				    After sending men to the moon, robots to 
				    Mars, and probes beyond the reaches of our 
				    solar system, it seemed that there was 
				    nowhere left to go.</p>

				    <p>The Space Program aims to change that. 
				    Beyond national boundaries, beyond what 
				    anyone can imagine that we can do. The sky 
				    is not the limit.</p>
			    ]]>
		    </dynamic-content>
	    </dynamic-element>
    </root>

You can view an article's XML by going to its source.
    
When you create a new theme using the Liferay Theme Generator, a default 
`sitemap.json` file is created and a default
`liferay-plugin-package.properties` file is created in the `WEB-INF` folder.

You have two options for specifying resources to be imported with your theme.
The recommended approach is to add resource files to the folders outlined above
and to specify the contents of the site or site template in a `sitemap.json`
file (described below). Alternatively, you can use an `archive.lar` file to
package the resources you'd like your theme to deploy. To create such an
`archive.lar`, just export the contents of a site from Liferay Portal using the
site scope. Then place the `archive.lar` file in your theme's
`[theme-name]/src/WEB-INF/src/resources-importer` folder. If you choose to
use an archive file to package all of your resources, you won't need a
`sitemap.json` file or any other files in your
`[theme-name]/src/WEB-INF/src/resources-importer` folder. Note, however, a
LAR file is version-specific; it won't work on any version of Liferay other than
the one from which it was exported. For this reason, using a `sitemap.json` file
to specify resources is the most flexible approach. If you're developing themes
for Liferay Marketplace, you should use the `sitemap.json` to specify resources
to be imported with your theme.

The `sitemap.json` in the `[theme-name]/src/WEB-INF/src/resources-importer`
folder specifies the site pages, layout templates, web content, assets, and
portlet configurations provided with the theme. This file describes the contents
and hierarchy of the site for Liferay to import as a site or site template. Even
if you're not familiar with JSON, the `sitemap.json` file is easy to understand.
Let's examine a sample `sitemap.json` file:

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
                            "portletId": "com_liferay_login_web_internal_portlet_LoginPortlet"
                        },
                        {
                            "portletId": "com_liferay_site_navigation_menu_web_internal_portlet_SiteNavigationMenuPortlet"
                        },
                        {
                            "portletId": "com_liferay_journal_content_web_internal_portlet_JournalContentPortlet",
                            "portletPreferences": {
                                "articleId": "Without Border.html",
                                "groupId": "${groupId}",
                                "portletSetupShowBorders": "false"
                            }
                        },
                        {
                            "portletId": "com_liferay_journal_content_web_internal_portlet_JournalContentPortlet",
                            "portletPreferences": {
                                "articleId": "Custom Title.html",
                                "groupId": "${groupId}",
                                "portletSetupShowBorders": "true",
                                "portletSetupTitle_en_US": "Web Content Display with Custom Title",
                                "portletSetupUseCustomTitle": "true"
                            }
                        }
                    ],
                    [
                        {
                            "portletId": "com_liferay_hello_world_web_internal_portlet_HelloWorldPortlet"
                        },
                        {
                            "portletId": "com_liferay_site_navigation_menu_web_internal_portlet_SiteNavigationMenuPortlet_INSTANCE_${groupId}",
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
                            "portletId": "com_liferay_nested_portlets_web_internal_portlet_NestedPortletsPortlet",
                            "portletPreferences": {
                                "columns": [
                                    [
                                        {
                                            "portletId": "com_liferay_journal_content_web_internal_portlet_JournalContentPortlet",
                                            "portletPreferences": {
                                            "articleId": "Child Web Content 1.xml",
                                            "groupId": "${groupId}",
                                            "portletSetupShowBorders": "true",
                                            "portletSetupTitle_en_US": "Web Content Display with Child Structure 1",
                                                "portletSetupUseCustomTitle": "true"
                                            }
                                        }
                                    ],
                                    [
                                        {
                                            "portletId": "com_liferay_journal_content_web_internal_portlet_JournalContentPortlet",
                                            "portletPreferences": {
                                            "articleId": "Child Web Content 2.xml",
                                            "groupId": "${groupId}",
                                            "portletSetupShowBorders": "true",
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
                            "portletId": "com_liferay_login_web_internal_portlet_LoginPortlet"
                        }
                    ],
                    [
                        {
                            "portletId": "com_liferay_hello_world_web_internal_portlet_HelloWorldPortlet"
                        }
                    ]
                ],
                "friendlyURL": "/layout-prototypes-parent-page", "layouts": [
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
                            "portletId": "com_liferay_login_web_internal_portlet_LoginPortlet"
                        }
                    ],
                    [
                        {
                            "portletId": "com_liferay_hello_world_web_internal_portlet_HelloWorldPortlet"
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
                "friendlyURL": "/hidden-page",
                "name": "Hidden Page",
                "title": "Hidden Page",
                "hidden": "true"
            }
        ]
    }

The first thing you should declare in your `sitemap.json` file is a layout
template ID so the target site or site template can reference the layout
template to use for its pages. You can also specify different layout templates
to use for individual pages. You can find layout templates in your Liferay
installation's `/layouttpl` folder. Next, you have to declare the layouts, or
pages, that your site template should use. Note that pages are called *layouts*
in Liferay's code. You can specify a name, title, and friendly URL for a page,
and you can set a page to be hidden. To declare that web content should be
displayed on a page, simply specify an HTML file. You can declare portlets by
specifying their portlet IDs, which can be found in the App Manager of the 
Control Panel. Select the suite that the App is located in, click the App, click
the App web link, and open the *Portlets* tab that appears. The portlet ID is 
displayed below the name of the App. You can find a full list of the default 
portlet IDs for Liferay in the [Portlet ID Quick Reference Guide](/participate/liferaypedia/-/wiki/Main/Portlet+ID+Quick+Reference+Guide). 
You can also specify portlet preferences for each portlet.

+$$$

**Tip:** You can specify an application display template (ADT) for a portlet in
the `sitemap.json` file by setting the `displayStyle` and `displayStyleGroupId`
portlet preferences. For example:

    "portletId": "com_liferay_asset_publisher_web_internal_portlet_AssetPublisherPortlet",
        "portletPreferences": {
            "displayStyleGroupId": "10197",
            "displayStyle": "ddmTemplate_6fe4851b-53bc-4ca7-868a-c836982836f4",
    }

To learn more about ADTs, visit the
[Styling Apps with Application Display Templates](/discover/portal/-/knowledge_base/7-0/styling-apps-with-application-display-templates)
chapter.

$$$

Optionally, you can create an `assets.json` file in your
`[theme-name]/src/WEB-INF/src/resources-importer` folder. While the
`sitemap.json` file defines the pages of the site or site template to be
imported, along with the layout templates, portlets, and portlet preferences of
these pages, the `assets.json` file specifies details about the assets to be
imported. Tags can be applied to any asset. Abstract summaries and small images
can be applied to web content articles. For example, the following `assets.json`
file specifies two tags for the `company_logo.png` image, one tag for the
`Custom Title.xml` web content article, and an abstract summary and small image
for the `Child Web Content 1.json` article structure:

    {
        "assets": [
            {
                "name": "company_logo.png",
                "tags": [
                    "logo",
                    "company"
                ]
            },
            {
                "name": "Custom Title.xml",
                "tags": [
                    "web content"
                ]
            },
            {
                "abstractSummary": "This is an abstract summary.",
                "name": "Child Web Content 1.xml",
                "smallImage": "company_logo.png"
            }
        ]
    }

Now that you've learned about the directory structure for your resources, the
`sitemap.json` file for referencing your resources, and the `assets.json` file
for describing the assets of your resources, it's time to put resources into
your theme. You can create resources from scratch and/or bring in resources that
you've already created in Liferay. Let's go over how to leverage your HTML
(basic web content), JSON (structures), or VM or FTL (templates) files from
Liferay:

+$$$

**Note:** In previous versions of Liferay, basic web content could be added
without the need of a structure or template. In Liferay 7.0 and above, all web
content articles require a structure and template.

$$$

- **web content:** Edit the article, and copy the content from the *Source* view. 
  Create a folder for the article under `resources-importer/journal/articles/`, 
  copy the contents into an XML file, named as desired, and place it into the 
  folder for the article. The web content article's XML fills in the data 
  required by the structure.

- **structure:** Edit the structure by clicking the link under 
  *Structure and Template*, and copy and paste its contents into a new JSON file 
  for the structure in the `resources-importer/journal/structures/` folder. The 
  structure JSON sets a wireframe, or blueprint, for an article's data.

- **template:** Create a folder for the template under 
  `resources-importer/journal/templates/`. Edit the template by clicking 
  the link under *Structure and Template*, and copy and paste its contents into 
  a new FTL file for the template, and place it into the folder for the template. 
  The template defines how the data should be displayed.

<!-- *Download* button is currently unavailable for Web Content (based on
structure and template). Contacted Juan for more info and following LPS-31355
-->

Here is an outline of steps you can use in developing your theme and its
resources:

1.  Create your theme.

2.  Add your resources under the
   `[theme-name]/src/WEB-INF/src/resources-importer` folder and its
   subfolders.

3.  Create a `sitemap.json` file in your `resources-importer/` folder. In this
    file, define the pages of the site or site template to be imported, along
    with the layout templates, portlets, and portlet preferences of these pages.

4.  Create an `assets.json` file in your `resources-importer/` folder.  In this
    file, specify details of your resource assets.

5.  In your `liferay-plugin-package.properties` file, set 
    `resources-importer-developer-mode-enabled=true`. For the
    `resources-importer-target-value` property, specify the name of the site or
    site template into which you are importing or comment it out to use the
    theme's name. For the `resources-importer-target-class-name` property,
    comment it out to import to a site template or set it to
    `com.liferay.portal.kernel.model.Group` to import directly into a site.

6.  Deploy your theme into your Liferay instance.

7.  View your theme, and its resources, from within Liferay. Log in to your
    portal as an administrator and check the Sites or Site Templates section of
    the Control Panel to make sure that your resources were deployed correctly.
    From the Control Panel you can easily view your theme and its resources:

    - If you imported into a site template, select its *Actions* &rarr; *View
      Pages* to see it.
    - If you imported directly into a site, select its
      *Actions* &rarr; *Go to Public Pages* to see it.

You can go back to any of the beginning steps in this outline to make
refinements. It's just that easy to develop a theme with resources intact!

<!-- (NEED TO UPDATE WORKING EXAMPLE)

To see a simple working example of the resources importer in action, visit
[https://github.com/liferay/liferay-docs/blob/6.2.x/devGuide/code/test-resources-importer-theme-6.2.0.1.war](https://github.com/liferay/liferay-docs/blob/6.2.x/devGuide/code/test-resources-importer-theme-6.2.0.1.war).
This is just the classic Liferay theme with some sample resources added. If
you're interested in extending the functionality of the resources-importer
application, you can use the test-resources-importer-portlet to check that you
aren't breaking existing functionality. The test-resources-importer-portlet is
available on Github here:
[https://github.com/liferay/liferay-plugins/tree/master/portlets/test-resources-importer-portlet](https://github.com/liferay/liferay-plugins/tree/master/portlets/test-resources-importer-portlet).
The sample resources included in the test-resources-importer-theme are the same
ones included in the test-resources-importer-portlet. If you'd like to examine
another example, check out the code for Liferay's welcome theme:
[https://github.com/liferay/liferay-plugins/tree/master/themes/welcome-theme](https://github.com/liferay/liferay-plugins/tree/master/themes/welcome-theme).
Note that this theme imports resources directly into the default site.
Typically, this won't be something you'll need to do; instead, you'll usually
have your theme's resources imported into a site template. For further examples,
please examine the Zoe themes which you can find on Github here
[https://github.com/liferay/liferay-plugins/tree/master/themes](https://github.com/liferay/liferay-plugins/tree/master/themes)
and which you can download from Liferay Marketplace.
-->

**Related Topics**

<!--[Creating Themes with the Theme Generator](URL goes here)-->

[Styling Apps with Application Display Templates](/discover/portal/-/knowledge_base/7-0/styling-apps-with-application-display-templates)


