# Preparing and Organizing Web Content for the Resources Importer [](id=preparing-and-organizing-web-content-for-the-resources-importer)

You must create the resources to import with your theme. You can create 
resources from scratch and/or bring in resources that you've already created. 
This tutorial covers how to prepare and organize your resources for the 
Resources Importer. 

First, you must prepare your web content for the Resources Importer. 

## Preparing Your Web Content [](id=preparing-your-web-content)

You can leverage your HTML (basic web content), JSON (structures), or VM or FTL
(templates) files with the Resource Importer. All web content articles require
a structure and template. Note that some articles may share the same structure
and perhaps even the same template--this is the case for all basic web content
articles. Follow these steps to prepare your web content articles:

1.  Open the web content article and click the *Download* button to download the 
    XML file. Create a folder for the article under 
    `resources-importer/journal/articles/` and rename it as desired. The web 
    content article's XML fills in the data required by the structure. An 
    example web content article's XML is shown below:

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
 
2.  Download the web content article's structure. Open the structure and click 
    the *Source* tab to view the structure's file. Copy and paste its contents 
    into a new JSON file in the `resources-importer/journal/structures/` folder. 
    The structure JSON sets a wireframe, or blueprint, for an article's data. If 
    you're saving a basic web content article, you can copy the structure below 
    (replace `en_US` with your language):
    
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
                    "indexType": "text",
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

3.  Download the structure's matching template. Open the Actions menu for the 
    structure and select *Manage Templates* to view the templates that use it. 
    Create a folder for the template under 
    `resources-importer/journal/templates/` and copy and paste its contents into 
    a new FTL file. The template defines how the data should be displayed. If 
    you're saving a basic web content article, you can copy the FreeMarker 
    template below:
    
        ${content.getData()}
 
Repeat the steps above for each web content article you have. Note that some web 
content articles may share the same structure and template; In these cases, only 
one copy of the structure and template is required for all web content articles 
that use them. Once your web content articles are saved, you can place them in 
their proper folder structure.

## Organizing Your Resources [](id=organizing-your-resources)

Add your resources under the `[theme-name]/src/WEB-INF/src/resources-importer` 
folder and its subfolders. Place your resources in the folders outlined below:

- `[theme-name]/src/WEB-INF/src/resources-importer/`
    - `sitemap.json` - defines the pages, layout templates, and portlets
    - `assets.json` - (optional) specifies details on the assets
    - `document_library/`
        - `documents/` - contains documents and media files (assets) 
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

## Related Topics [](id=related-topics)

[Defining the Sitemap](/develop/tutorials/-/knowledge_base/7-1/defining-the-sitemap)

[Defining the Assets](/develop/tutorials/-/knowledge_base/7-1/defining-the-assets)

[Specifying Where to Import the Resources](/develop/tutorials/-/knowledge_base/7-1/specifying-where-to-import-resources)
