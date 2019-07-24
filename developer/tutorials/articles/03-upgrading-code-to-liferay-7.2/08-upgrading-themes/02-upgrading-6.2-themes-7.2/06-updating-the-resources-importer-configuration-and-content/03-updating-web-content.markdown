---
header-id: updating-6-2-web-content
---

# Updating 6.2 Web Content

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Updating 6.2 Resources Importer</p><p>Step 2 of 3</p>
</div>

All web content articles must be written in XML and have a structure for article 
creation and a template for rendering. 

| **Note:** The example Lunar Resort theme's updated XML articles are in the ZIP
| file's `/resources-importer/journal/articles/Basic Web Content/` folder for
| reference.

Follow these steps to update the theme's web content:

1.  Create a subfolder called `BASIC_WEB_CONTENT` in the 
    `/resources-importer/journal/articles/` folder, and move all the basic HTML 
    articles (articles that did not require a structure or template previously) 
    into it. 

2.  Create a subfolder in the `/resources-importer/journal/templates/` folder 
    with the same name as the folder you just created (`BASIC_WEB_CONTENT`). The 
    articles and template folder names **must match** for the web content to 
    import properly.

3.  XML article structures are now written in JSON. Open the 
    `/resources-importer/journal/structures/` folder and create a new file 
    inside called `BASIC_WEB_CONTENT.json`. The structure name **must match** 
    the folder names created in the previous steps. To ensure the syntax is 
    correct for web content articles that used a structure and template before, 
    we recommend that you recreate the structure and template in @product@. 

4.  Add the JSON structure below to the `BASIC_WEB_CONTENT.json` file. This 
    provides the required metadata to render standard web content articles 
    (i.e. the language, fields, etc.):

    ```json
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
    ```

5.  Create a new FreeMarker template file for basic web content inside the 
    `/resources-importer/journal/templates/BASIC_WEB_CONTENT` folder called 
    `BASIC_WEB_CONTENT.ftl`, and add the method below to retrieve the article's 
    data:

    ```markup
    ${content.getData()}
    ```

6.  Convert the basic web content articles from HTML to XML to conform to the 
    new format. Replace the `.html` file extensions with `.xml`. wrap each basic 
    web content article's content with the XML shown below:

    ```xml
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
    ```

7.  @product-ver@'s updated Bootstrap requires that you replace all 
    `span[number]` classes with the updated `col-[device-size]-[number]` syntax.
    `[device-size]` can be `xs`, `sm`, `md`, or `lg`. See Bootstrap's 
    [documentation](https://getbootstrap.com/docs/4.0/layout/grid/) for more 
    information. The original and updated classes for the Lunar Resort's 
    `2 column description.xml` article are shown below:

    Original:

    ```xml
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
    ```

    Updated:

    ```xml
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
    ```

The web content is updated! Next, you must update the theme's sitemap file. 
