# Creating Plugins to Share Structures, Templates, and More [](id=creating-a-plugin-to-share-templates-str-liferay-portal-6-2-dev-guide-03-en)

Have you ever wanted to share page templates with other users? Are colleagues
and clients banging at your door to get hold of the structures and templates you
use for your web content articles and wikis? If so, you can bundle these up in a
Liferay plugin to distribute to them. You can even put them in a Marketplace app
for them to purchase. When they install your plugin, its templates and
structures are automatically imported into their portal's global site. How is
this possible? The Templates Importer feature of the Resources Importer app
makes it happen! The Resources Importer is available from
[Liferay Marketplace](https://www.liferay.com/marketplace).

The Templates Importer is a part of the Resources Importer app. It lets you
import the following resources: 

- Page templates
- Web content structures and templates 
- Application Display Templates (ADTs) for any portlet that supports ADTs, such
as the Asset Publisher, Blogs, Tags Navigation, Categories Navigation, Documents
and Media, Site Map, and Wiki portlets. 
- DDL structures and templates, including display templates and form templates.

Although you can include the template importing capability in any Liferay
plugin, they're especially useful for Liferay themes. When developers
upload themes to [Liferay Marketplace](https://www.liferay.com/marketplace),
it's very convenient for them to be able to bundle sample content with the theme
so that the theme can be viewed in an appropriate context. Please refer to the
[Importing Resources with Your Themes](www.liferay.com) tutorial for
instructions on using the Resources Importer with theme plugins. Behind themes,
portlet plugins are the most common type of plugin used for importing templates.
Let's build a portlet plugin that imports some web content structures and
templates. 

1. If the Resources Importer is not already installed on your portal, visit
   [Liferay Marketplace](https://www.liferay.com/marketplace) to download and
   install it.

2. Create a portlet plugin project with the name of your choice. For example,
   create a portlet plugin project called `sample-templates-importer-portlet`.

3. Edit your `liferay-plugin-package.properties` file to include the following
   property settings:

		name=
	
		required-deployment-contexts=\
			resources-importer-web
	
		resources-importer-developer-mode-enabled=true
	
		module-incremental-version=1

    Here's a summary of what we're accomplishing with these settings:
        - We remove the plugin's `name` value to prevent the portal from
          displaying the plugin as an available app.
        - Since the Templates Importer feature resides in the Resources Importer
          web plugin, we include it as a required context.
        - By enabling developer mode, if the templates we're importing to the
          Global site already exist on it, the Templates Importer conveniently
          overwrites them. 
        - We set the module increment version to `1` since `1` is an appropriate
          version starting point for the plugin's version. 

4. Edit the portlet's `portlet.xml` file and delete the value of its
   `display-name` element to keep the portal from displaying the portlet as an
   available app. 

5. Create a folder named `templates-importer` in the plugin's `WEB-INF\src`
   folder. This folder will hold all of the structures and templates to import
   into the portal's Global site. 

Let's stop here for a moment and consider how to specify templates and
structures. The Templates Importer expects them to be specified in a directory
structure under the plugin project's `templates-importer` folder. You must
create folders to contain the template and structure files to apply to the
portal. 

Here's the directory structure to follow for specifying folders to contain your
structures and templates: 

- `templates-importer/`
    - `journal/`
        - `structures/` - contains structures (XML) and folders of child
          structures. Each folder name must match the file name of the
          corresponding parent structure. For example, to include a child
          structure of parent structure `Parent 1.xml`, create a folder named
          `Parent 1/` at the same level as the `Parent 1.xml` file, for holding
          a child structures. 
        - `templates/` - groups templates (FTL or VM) into folders by structure.
          Each folder name must match the file name of the corresponding
          structure. For example, create folder `Structure 1/` to hold a
          template for structure file `Structure 1.xml`. 
    - `templates/`
        - `application_display/` - contains application display template (ADT)
          script files written in either the FreeMarker Template Language (`.ftl`)
          or Velocity (`.vm`). The extension of the files, `.ftl` for FreeMarker or
          `.vm` for Velocity must reflect the language that the templates are
          written in. 
            - `asset_category/` - contains categories navigation templates
            - `asset_entry/` - contains asset publisher templates
            - `asset_tag/` - contains tags navigation templates
            - `blogs_entry/` - contains blogs templates
            - `document_library/` - contains documents and media templates
            - `site_map/` - contains site map templates
            - `wiki_page/` - contains wiki templates
        - `dynamic_data_list/` - contains dynamic data list templates and
          structures 
            - `display_template/` - groups templates (FTL or VM) into folders by
              structure. Each folder name must match the file name of the
              corresponding structure. For example, create folder `Structure 1/`
              to hold a template for structure file `Structure 1.xml`. 
            - `form_template/` - groups templates (FTL or VM) into folders by
              structure. Each folder name must match the file name of the
              corresponding structure. For example, create folder `Structure 1/`
              to hold a template for structure file `Structure 1.xml`. 
            - `structure/` - contains structures (XML)
        - `page/` - contains page templates (JSON)

For structures and templates in your custom plugins, you only need to create
folders to support the templates and/or structures you're adding. 

We've provided a ZIP file of the folders, templates, and structures for the
example `sample-templates-importer-portlet` plugin. To obtain this example
content, please take the following steps:

1. Download the file
   [sample-templates-importer-contents.zip](https://www.liferay.com/documents/14/21661387/sample-templates-importer-contents.zip/49f8ff21-ed1f-4154-95c5-fd410c418703).

2. Extract its contents into the `templates-importer` folder of the
   `sample-templates-importer-portlet` plugin. 

3. Deploy the `sample-templates-importer-portlet` plugin into a Liferay
   instance. 

    The console output should be similar to this:

        INFO  [localhost-startStop-8][PortletHotDeployListener:343] Registering portlets for sample-templates-importer-portlet
        INFO  [localhost-startStop-8][PortletHotDeployListener:490] 1 portlet for sample-templates-importer-portlet is available 
        for use
        INFO  [liferay/hot_deploy-1][ResourcesImporterHotDeployMessageListener:256] Importing resources from sample-templates-
        importer-portlet to group 10194 takes 1294 ms
        ...


4. View your resources from within Liferay. Log in to your portal as an
   administrator and check the Global site to make sure that your resources were
   deployed correctly. Here's how you can use the Control Panel to view your
   structures and templates: 
    1. Go to *Sites* in the Control Panel.
    2. Select the *Global* site.
    3. You can view the imported structures and templates here:
        - The Journal Article structures and templates can be viewed in the Web
          Content Control Panel portlet &rarr;  *Manage* &rarr; *Structures* or
          *Manage* &rarr; *Templates* .
        - The Dynamic Data List templates can be viewed in the Dynamic Data
          Lists Control Panel portlet &rarr; *Manage Data Definitions*. The
          templates can be viewed by going to the Actions menu of the Dynamic
          Data List structure and then clicking on *Manage Templates*. 
        - The Application Display templates can be viewed under the
          *Configuration* category &rarr; *Application Display Templates*.  
        - The page templates can be viewed in the *Page Templates* category from
          the Control Panel menu.

The figure below shows some of the ADTs that were imported.

![Figure x: The Templates Importer allows users to import all kinds of structures and templates, such as these application display templates.](../../images/templates-importer-adts.png)

As you take a look around the folders and files within the plugin's
`templates-importer` folder, notice the different kinds of templates and
structures. 

Page templates are specified in `.json` files in the
`templates-importer/templates/page` folder. Each one specifies the layout
template, web content, assets, and portlet configurations to be imported with
that page template. 

Here is the contents of the `page_3.json` file: 

    {
      "layoutTemplate": {
        "columns": [
          [
            {
              "portletId": "58"
            }
          ],
          [
            {
              "portletId": "47"
            },
            {
              "portletId": "118",
              "portletPreferences": {
                "columns": [
                  [
                    {
                      "portletId": "3"
                    }
                  ],
                  [
                    {
                      "portletId": "16"
                    }
                  ]
                ],
                "layoutTemplateId": "2_columns_i"
              }
            }
          ]
        ],
        "friendlyURL": "/page-3",
        "name": "Page 3",
        "title": "Page 3"
      },
      "layoutTemplateId": "2_columns_ii"
    }


At the bottom of the JSON file, there are several important specifications for
the page template. The `layoutTemplateId` references the layout template to use
for the page. You can specify different layout templates to use for individual
pages. You can find layout templates in your Liferay installation's `/layouttpl`
folder. You can specify a name, title, and friendly URL for the page using the
respective `name`, `title`, and `friendlyURL` fields. And, although it's not
demonstrated in this page template, you can even set a page to be hidden. 

Turning your attention to the columns of the JSON file, notice that you can
declare portlets by specifying their portlet IDs. To look up the IDs of
Liferay's core portlets, see the `WEB-INF/portlet-custom.xml` file deployed in
Liferay on your app server. If you're using the Web Content portlet, you can
declare articles to be displayed on a page, by specifying HTML files.
Interestingly, the `page_3.json` file demonstrates using the Nested Portlets
portlet to display other portlets: the Search and Currency Converter portlets.
Lastly, you can also specify portlet preferences for each portlet using the
`portletPreferences` field. 

---

 ![tip](../../images/tip.png) **Tip**: You can also import resources, such as
 web content articles, using the Resources Importer. For example, it's very
 useful to import web content articles along with a page template that
 references the articles, in a nested Web Content Display portlet. For more
 information on importing resources, see
 [Importing Resources with Your Themes](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/importing-resources-with-your-themes-liferay-portal-6-2-dev-guide-09-en).

---

The figure below, shows a page created using the Page 3 template.

![Figure x: Users can create pages, like this one, prepopulated with portlets and content that you've specified in your plugins, that leverage the Templates Importer.](../../images/templates-importer-page-3-template.png)

Now that you've learned about the directory structure for your templates and the
JSON file for the page templates, it's time to learn how to put template and
structure files into your plugin. You can create structures and templates from
scratch and/or leverage ones you've already created in Liferay. Let's go over
how to leverage bringing in XML (structures) and FTL or VM template files from
Liferay. 

The sections below explain how to create structure and template files to put
within the defined directory structure of the portlet's `templates-importer/`
folder. 

**Structure:** 

- **Dynamic Data Lists:** Edit the structure by clicking on *Manage Data
  Definitions*. Click on a structure that you want to export and then click on
  the *Source* tab. Copy and paste its contents into a new XML file for the
  structure in the `templates-importer/journal/dynamic_data_list/structure`
  folder. The structure XML sets a wireframe, or blueprint, for a dynamic data
  list's data. 
- **Web Content:** Edit the structure by clicking on *Manage* and then
  *Structures*. Click on a structure that you want to export and then click on
  the Source tab. Copy and paste its contents into a new XML file for the
  structure in the `templates-importer/journal/structures/` folder. The
  structure XML sets a wireframe, or blueprint, for an article's data. 

**Template:** 

- **Application Display:** Edit the template by clicking on the template you
  want to export. Copy and paste its contents into a new FTL or VM file and
  place it in
  `templates-importer/templates/application_display/[your application display template type]/`. 
- **Dynamic Data List:** Edit the template by clicking on Manage Data
  Definition. Click on *Manage Templates* from the Actions menu of the structure
  that your template is linked to. Choose the template that you want to export.
  Copy and paste its contents into a new FTL or VM file and place it in
  `templates-importer/templates/display_template/[structure name]/` or
  `templates-importer/templates/form_template/[structure name]/` 
- **Page:** You will have to create the page template from scratch based on the
  `.json` file example for the page template above. 

Importantly, you must name the files of all structures and templates, except
page templates, after their source structures and templates. You can go back to
any of the beginning steps in this section to make refinements to the sample
plugin to try importing different structures and templates. The final
`sample-templates-importer-portlet` project is available [here](https://github.com/liferay/liferay-docs/tree/master/devGuide/code/devGuide-sdk/portlets/sample-templates-importer-portlet). 

As you've seen for yourself, importing structures and templates with your plugin
isn't difficult at all. The Resource Importer app's Templates Importer feature
makes it easy. Have fun distributing your structures and templates!

## Next Steps

[Importing Resources with Your Themes](www.liferay.com)
