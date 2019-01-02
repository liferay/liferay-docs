# Updating the Resources Importer [](id=updating-the-resources-importer)

The Resources Importer is now an OSGi module in Liferay's Web Experience
application suite. Since the suite is bundled with @product@, you don't have to
download the Resources Importer separately. The following components have been
updated and are the focus of this tutorial:

- Plugin properties
- Web content article files and folder structure
- Sitemap

+$$$

**Note:** Due to the page and article import order, articles that link to pages 
in the Site's layout cause a null pointer exception 
[issue](https://issues.liferay.com/browse/LPS-64859). 
These links have been removed from the example Lunar Resort theme's web content 
articles to avoid this issue. 

$$$

Start updating the plugin properties for the Resources Importer.

## Updating liferay-plugin-package.properties [](id=updating-liferay-plugin-package-properties)

Follow the steps in this section to upgrade Plugins SDK themes. Skip to the next 
section for all other themes. 

1.  Open the `src\WEB-INF\liferay-plugin-package.properties` file and remove the 
    `required-deployment-contexts` property. This is no longer needed since the 
    Resources Importer is bundled with @product@.

2.  The group model class's fully-qualified class name has changed. Replace the 
    `resources-importer-target-class-name` property's value with the updated one 
    below:

        com.liferay.portal.kernel.model.Group

Now that your `liferay-plugin-package.properties` is updated, you can update 
your theme's web content.

## Updating Web Content [](id=updating-web-content)

All web content articles must be written in XML and have a structure for article 
creation and a template for rendering. 

+$$$

**Note:** The example Lunar Resort theme's updated XML articles are in the ZIP 
file's `/resources-importer/journal/articles/Basic Web Content/` folder for 
reference.

$$$

Follow these steps to update your web content:

1.  Create a subfolder, for example `BASIC_WEB_CONTENT`, in the 
    `/resources-importer/journal/articles/` folder, and move all the basic HTML 
    articles (articles that did not require a structure or template previously) 
    into it. 

2.  Create a subfolder in the `/resources-importer/journal/templates/` folder 
    with the same name as the folder you created in step 1. The articles and 
    template folder names must match for the web content to import properly.

3.  XML article structures are now written in JSON. Create a file, for example 
    `BASIC_WEB_CONTENT.json`, in the `/resources-importer/journal/structures/` 
    folder. The structure name **must match** the folder names you created in 
    the previous steps. To ensure the syntax is correct for web content articles 
    that used a structure and template before, we recommend that you recreate 
    the structure and template in @product@. 

4.  In the JSON file you just created, add a JSON structure for the basic web 
    content that follows the pattern below:

        {
            "availableLanguageIds": [
                "en_US"
            ],
            "defaultLanguageId": "en_US",
            "fields": [
                {
                    "label": {
                        "en_US": "Content"
                    },
                    "predefinedValue": {
                        "en_US": ""
                    },
                    "style": {
                        "en_US": ""
                    },
                    "tip": {
                        "en_US": ""
                    },
                    "dataType": "html",
                    "fieldNamespace": "ddm",
                    "indexType": "keyword",
                    "localizable": true,
                    "name": "content",
                    "readOnly": false,
                    "repeatable": false,
                    "required": false,
                    "showLabel": true,
                    "type": "ddm-text-html"
                }
            ]
        }

5.  For basic web content, create a FreeMarker template file 
    (e.g., `[template-folder-name].ftl`) in the template subfolder you created 
    in step 2, and add the method below to retrieve the article's data:

        ${content.getData()}

6.  Use this syntax to migrate basic web content articles from HTML to XML. 
    **Remember** to change your `.html` file extension to `.xml`: 

        <?xml version="1.0"?>

        <root available-locales="en_US" default-locale="en_US">
                <dynamic-element name="content" type="text_area"
                index-type="keyword" index="0">
                        <dynamic-content language-id="en_US">
                                <![CDATA[
                                ORIGINAL HTML CONTENT GOES HERE
                                ]]>
                        </dynamic-content>
                </dynamic-element>
        </root>

7.  @product-ver@'s updated Bootstrap requires that you replace all 
    `span[number]` classes with the updated syntax below:

        col-[device-size]-[number]

    `[device-size]` can be `xs`, `sm`, `md`, or `lg`. `md` works for most cases. 
    The original and updated classes for the Lunar Resort's 
    `2 column description.xml` article are shown below for reference:

    Original:

        <?xml version="1.0"?>

        <root available-locales="en_US" default-locale="en_US">
            <dynamic-element name="content" type="text_area"
            index-type="keyword" index="0">
                <dynamic-content language-id="en_US">
                    <![CDATA[
                        <div class="container-fluid">
                            <div class="span4" id="columnLeft">
                                Out of This World
                            </div>
                            <div class="span8" id="columnRight">
                                Come to the Lunar Resort...
                            </div>
                        </div>
                    ]]>
                </dynamic-content>
            </dynamic-element>
        </root>
 
    Updated:

        <?xml version="1.0"?>

        <root available-locales="en_US" default-locale="en_US">
            <dynamic-element name="content" type="text_area"
            index-type="keyword" index="0">
                <dynamic-content language-id="en_US">
                    <![CDATA[
                        <div class="container-fluid">
                            <div class="col-md-4" id="columnLeft">
                                Out of This World
                            </div>
                            <div class="col-md-8" id="columnRight">
                                Come to the Lunar Resort...
                            </div>
                        </div>
                    ]]>
                </dynamic-content>
            </dynamic-element>
        </root>

    Bootstrap's 
    [documentation](https://getbootstrap.com/docs/4.0/layout/grid/) explains the 
    updated grid system. 

Next, you must update your theme's sitemap file. 

## Updating the Sitemap [](id=updating-the-sitemap)

In Liferay Portal 6.2, portlet IDs were incremental numbers. In @product-ver@, 
they're explicit class names. Update your `sitemap.json` file with the new 
portlet IDs. 

Some common portlet IDs are specified in the `sitemap.json` example in the 
[Creating a Sitemap for the Resources Importer](/develop/tutorials/-/knowledge_base/7-1/creating-a-sitemap-for-the-resources-importer) 
tutorial.

You can also retrieve a portlet's ID from the UI:

1.  In the portlet's *Options* menu, select *Look and Feel Configuration*.
 
    ![Figure 1: You can find the portlet ID in the *Look and Feel Configuration* menu.](../../../../images/upgrading-themes-look-and-feel-menu.png)

2.  Select the *Advanced Styling* tab.

    The `Portlet ID` value appears in the blue box.

![Figure 2: The portlet ID appears within the blue box in the *Advanced Styling* tab.](../../../../images/upgrading-themes-portlet-id.png)

The [Portlet ID Quick Reference Guide](/develop/reference/-/knowledge_base/7-1/fully-qualified-portlet-ids)
shows all the default portlet IDs. Check `liferay-portlet.xml` for the portlet
ID number in 6.2 and replace it with the updated ID in the quick reference
Guide. 

+$$$

**Remember** to use the updated `.xml` extension for your web content articles 
in your sitemap.

$$$

## Related Topics [](id=related-topics)

[Updating 6.2 CSS Code](/develop/tutorials/-/knowledge_base/7-1/updating-6-2-css-code)

[Applying Clay Design Patterns to 6.2 Themes](/develop/tutorials/-/knowledge_base/7-1/applying-clay-design-patterns-to-6-2-themes)
