# Importing Resources with Your Themes [](id=importing-resources-with-your-themes-liferay-portal-6-2-dev-guide-09-en)

A theme without content is like an empty house. If you're trying to sell an
empty house, it may be difficult for prospective buyers to see its full beauty.
However, staging the house with some furniture and decorations helps prospective
buyers imagine what the house might look like with their belongings. Liferay's
resources importer application is a tool that allows a theme developer to have
files and web content automatically imported into the portal when a theme is
deployed. Usually, the resources are imported into a site template but they can
also be imported directly into a site. Portal administrators can use the site or
site template created by the resources importer to showcase the theme. This is a
great way for theme developers to provide a sample context that optimizes the
design of their theme. In fact, all standalone themes that are uploaded to
Liferay Marketplace must use the resources importer. This ensures a uniform
experience for Marketplace users: a user can download a theme from Marketplace,
install it on their portal, go to Sites or Site Templates in the Control Panel
and immediately see their new theme in action. In this tutorial, we explain how
to include resources with your theme.

---

![Note](../../images/tip-pen-paper.png) **Note:** The resources importer can be
used in any type of plugin project to import resources. Importing resources
within a theme plugin is just one of the more common use cases. To learn how to
use the resources importer in other types of plugins, please see the
[Creating Plugins to Share Structures, Templates, and More](www.liferay.com)
tutorial.

---

Liferay's welcome theme includes resources that the resources importer
automatically deploys to the default site. (Note: The welcome theme is only
applied out-of-the-box in Liferay CE.) The welcome theme and the pages and
content that it imports to the default site provide a good example of the
resources importer's functionality.

![Figure 10.8: The welcome theme uses the resources importer to import pages and content to the default site of a fresh Liferay installation.](../../images/welcome-theme.png)

If it's not already installed, you can download the resources importer
application from Liferay Marketplace. Search for either *Resources Importer CE*
or *Resources Importer EE*, depending on your Liferay Portal platform, and
download the latest version. Install and deploy the resources importer to your
Liferay instance the same way you would deploy any other Liferay plugin or
Marketplace app.

---

![Tip](../../images/tip-pen-paper.png) **Tip:** If you deploy a theme to your
Liferay Portal instance and don't have the resources importer already deployed,
you might see a message like this:
 
    19:21:12,224 INFO  [pool-2-thread-2][HotDeployImpl:233] Queuing test-theme for deploy because it is missing resources-importer-web

Such a message appears if the resources importer is declared as a dependency in
your theme's `liferay-plugin-package.properties` file but is not deployed. You
can deploy the resources importer application to satisfy the dependency or you
can remove or comment out the dependency declaration if you're not going to use
the resources importer with your theme (see below).

---

When you create a new theme project using the Liferay Plugins SDK, check your
theme's `docroot/WEB-INF/liferay-plugin-package.properties` file for two entries
related the resources importer. One or both of these might be commented out or
missing, depending on the version of your Plugins SDK:

    required-deployment-contexts=\
        resources-importer-web

    resources-importer-developer-mode-enabled=true

The first entry, `required-deployment-contexts=resources-importer-web`, declares
your theme's dependency on the resources importer plugin. If you're not going to
use the resources importer with your theme and don't want to deploy the
resources importer, you can remove or comment out this entry. The second entry,
`resources-importer-developer-mode-enabled=true`, is a convenience feature for
theme developers. With this setting enabled, if the resources are to be imported
to a site template that already exists, the site template is recreated and
reapplied to sites using the site template. Otherwise, you have to manually
delete the sites built using the resource importer's site template each time you
change anything in your theme's `docroot/WEB-INF/src/resources-importer` folder.

If you'd like to import your theme's resources directly into a site, instead of
into a site template, you can specify the following in your
`liferay-plugin-package.properties` file:

    resources-importer-target-class-name=com.liferay.portal.model.Group

    resources-importer-target-value=<site-name>

---

![warning](../../images/tip-pen-paper.png) **Warning:** If you're developing
themes for Liferay Marketplace, don't configure your theme to import resources
directly into a site. Instead, use the default: import the resources into a
site template. Do this by commenting out the
`resources-importer-target-class-name` property. This way, it'll be much safer
to deploy your theme to a production Liferay instance.

---

All of the resources a theme uses with the resources importer go in the
`<theme-name>/docroot/WEB-INF/src/resources-importer` folder. The assets to be
imported by your theme should be placed in the following directory structure:

- `<theme-name>/docroot/WEB-INF/src/resources-importer/`
    - `sitemap.json` - defines the pages, layout templates, and portlets
    - `assets.json` - (optional) specifies details on the assets
    - `document_library/`
        - `documents/` - contains documents and media files
    - `journal/`
        - `articles/` - contains web content (HTML) and folders grouping web
          content articles (XML) by template. Each folder name must match the
          file name of the corresponding template. For example, create folder
          `Template 1/` to hold an article based on template file
          `Template 1.vm`. 
        - `structures/` - contains structures (XML) and folders of child
          structures. Each folder name must match the file name of the
          corresponding parent structure. For example, create folder
          `Structure 1/` to hold a child of structure file `Structure 1.xml`.
        - `templates/` - groups templates (VM or FTL) into folders by structure.
          Each folder name must match the file name of the corresponding
          structure. For example, create folder `Structure 1/` to hold a
          template for structure file `Structure 1.xml`.

When you create a new theme using the Liferay Plugins SDK
(liferay-plugins-sdk-6.1.1-ce-ga2-20121004092655026 or later), this folder
structure is created automatically. Also, a default `sitemap.json` file is
created and a default
`liferay-plugin-package.properties` file is created in the `WEB-INF` folder.

You have two options for specifying resources to be imported with your theme.
The recommended approach is to add resource files to the folders outlined above
and to specify the contents of the site or site template in a `sitemap.json`
file (described below). Alternatively, you can use an `archive.lar` file to
package the resources you'd like your theme to deploy. To create such an
`archive.lar`, just export the contents of a site from Liferay Portal using the
site scope. Then place the `archive.lar` file in your theme's
`<theme-name>/docroot/WEB-INF/src/resources-importer` folder. If you choose to
use an archive file to package all of your resources, you won't need a
`sitemap.json` file or any other files in your
`<theme-name>/docroot/WEB-INF/src/resources-importer` folder. Note, however, a
LAR file is version-specific; it won't work on any version of Liferay other than
the one from which it was exported. For this reason, using a `sitemap.json` file
to specify resources is the most flexible approach. If you're developing themes
for Liferay Marketplace, you should use the `sitemap.json` to specify resources
to be imported with your theme.

The `sitemap.json` in the `<theme-name>/docroot/WEB-INF/src/resources-importer`
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
                            "portletId": "58"
                        },
                        {
                            "portletId": "71"
                        },
                        {
                            "portletId": "56",
                            "portletPreferences": {
                                "articleId": "Without Border.html",
                                "groupId": "${groupId}",
                                "portletSetupShowBorders": "false"
                            }
                        },
                        {
                            "portletId": "56",
                            "portletPreferences": {
                                "articleId": "Custom Title.html",
                                "groupId": "${groupId}",
                                "portletSetupShowBorders": "true",
                                "portletSetupTitle_en_US":
                                    "Web Content Display with Custom Title",
                                "portletSetupUseCustomTitle": "true"
                            }
                        }
                    ],
                    [
                        {
                            "portletId": "47"
                        },
                        {
                            "portletId": "71_INSTANCE_${groupId}",
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
                            "portletId": "118",
                            "portletPreferences": {
                                "columns": [
                                    [
                                        {
                                            "portletId": "56",
                                            "portletPreferences": {
                                                "articleId":
                                                    "Child Web Content 1.xml",
                                                "groupId": "${groupId}",
                                                "portletSetupShowBorders": "true",
                                                "portletSetupTitle_en_US":
                                                    "Web Content Display with 
                                                        Child Structure 1",
                                                "portletSetupUseCustomTitle": "true"
                                            }
                                        }
                                    ],
                                    [
                                        {
                                            "portletId": "56",
                                            "portletPreferences": {
                                                "articleId":
                                                    "Child Web Content 2.xml",
                                                "groupId": "${groupId}",
                                                "portletSetupShowBorders": "true",
                                                "portletSetupTitle_en_US":
                                                    "Web Content Display with 
                                                        Child Structure 2",
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
                    "fr_CA": "Bienvenue"
                },
                "title": "Welcome"
            },
            {
                "columns": [
                    [
                        {
                            "portletId": "58"
                        }
                    ],
                    [
                        {
                            "portletId": "47"
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
specifying their portlet IDs which can be found in Liferay's
`WEB-INF/portlet-custom.xml` file. You can also specify portlet preferences for
each portlet.

Optionally, you can create an `assets.json` file in your
`<theme-name>/docroot/WEB-INF/src/resources-importer` folder. While the
`sitemap.json` file defines the pages of the site or site template to be
imported, along with the layout templates, portlets, and portlet preferences of
these pages, the `assets.json` file specifies details about the assets to be
imported. Tags can be applied to any asset. Abstract summaries and small images
can be applied to web content articles. For example, the following `assets.json`
file specifies two tags for the `company_logo.png` image, one tag for the
`Custom Title.html` web content article, and an abstract summary and small image
for the `Child Web Content 1.xml` article structure:

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
                "name": "Custom Title.html",
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
(basic web content), XML (structures), or VM or FTL (templates) files from
Liferay:

- **web content (basic):** Edit the article, click *Source*, and copy its
  contents into an HTML file in the `resources-importer/journal/articles/`
  folder.
- **web content (based on structure and template):** Edit the article, click
  *Download* to download it as a file `article.xml`. Create a folder for the
  template under `resources-importer/journal/articles/`, rename the downloaded
  `article.xml` file as desired, and copy it into the folder for the template.
  The web content article's XML fills in the data required by the structure.
- **structure:** Edit the structure by clicking *Source*, and copy and paste its
  contents into a new XML file for the structure in the
  `resources-importer/journal/structures/` folder. The structure XML sets a
  wireframe, or blueprint, for an article's data. 
- **template:** Edit the template by clicking *Source*, and copy and paste its
  contents into a new XML file for the template in the
  `resources-importer/journal/templates/` folder. The template defines how the
  data should be displayed.

<!-- *Download* button is currently unavailable for Web Content (based on
structure and template). Contacted Juan for more info and following LPS-31355
-->

Here is an outline of steps you can use in developing your theme and its
resources:

1.  Create your theme.

2.  Add your resources under the
   `<theme-name>/docroot/WEB-INF/src/resources-importer` folder and its
   subfolders.

3.  Create a `sitemap.json` file in your `resources-importer/` folder. In this
    file, define the pages of the site or site template to be imported, along
    with the layout templates, portlets, and portlet preferences of these pages.

4.  Create an `assets.json` file in your `resources-importer/` folder.  In this
    file, specify details of your resource assets.

5.  In your `liferay-plugin-package.properties` file, include
    `resources-importer-web` in your `required-deployment-contexts` property's
    list and set `resources-importer-developer-mode-enabled=true`. For the
    `resources-importer-target-value` property, specify the name of the site or
    site template into which you are importing or comment it out to use the
    theme's name. For the `resources-importer-target-class-name` property,
    comment it out to import to a site template or set it to
    `com.liferay.portal.model.Group` to import directly into a site.

6.  Deploy your plugin into your Liferay instance.

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

To see a simple working example of the resources importer in action, visit
[https://github.com/liferay/liferay-docs/tree/master/devGuide/code/test-resources-importer-theme-6.2.0.1.war](https://github.com/liferay/liferay-docs/tree/master/devGuide/code/test-resources-importer-theme-6.2.0.1.war).
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

## Next Steps

[Creating Plugins to Share Structures, Templates, and More](www.liferay.com)
