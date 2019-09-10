---
header-id: updating-the-6-2-sitemap
---

# Updating the 6.2 Sitemap

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Updating 6.2 Resources Importer</p><p>Step 3 of 3</p>
</div>

In @product@ 6.2, portlet IDs were incremental numbers. In @product-ver@, 
they're explicit class names. Update the `sitemap.json` file with the new 
portlet IDs. Follow these steps to update the sitemap:

1.  Replace the portlet IDs with the updated class names. The 
    [Portlet ID Quick Reference Guide](/docs/7-2/reference/-/knowledge_base/r/fully-qualified-portlet-ids) 
    list the default portlet IDs. Check `liferay-portlet.xml` for the portlet ID 
    number in 6.2 and replace it with the updated ID in the quick reference 
    Guide.
    
    | **Note:** you can also retrieve a portlet's ID from the UI. Open the 
    | portlet's *Options* menu, select *Look and Feel Configuration*. 
    | 
    | ![Figure 1: You can find the portlet ID in the *Look and Feel Configuration* menu.](../../../../../images/upgrading-themes-look-and-feel-menu.png)
    | 
    | Select the *Advanced Styling* tab. The `Portlet ID` value appears in the 
    | blue box. 
    | 
    | ![Figure 2: The portlet ID appears within the blue box in the *Advanced Styling* tab.](../../../../../images/upgrading-themes-portlet-id.png)

    The original and updated versions of the Lunar Resort theme's `sitemap.json` 
    are shown below:

    Original:

    ```json
    {
    	"name": "Collaboration",
    	"title": "Collaboration",
    	"friendlyURL": "/collaboration",
    	"layoutTemplateId": "2_columns",
    	"columns": [
    		[
    			{
    			"portletId": "36"
    			}
    		],
    		[
    			{
    			"portletId": "115"
    			}
    		]
    	
    	]
    }
    ```

    Updated:

    ```json
    {
      "name": "Collaboration",
      "title": "Collaboration",
      "friendlyURL": "/collaboration",
      "layoutTemplateId": "2_columns",
      "columns": [
        [
          {
          "portletId": "com_liferay_wiki_web_portlet_WikiPortlet"
          }
        ],
        [
          {
          "portletId": "com_liferay_blogs_web_portlet_BlogsAgreggatorPortlet"
          }
        ]
      
      ]
    },
    ```

2.  Update references to the web content articles in the `sitemap.json` to use 
    the XML file extensions.
    
Great! The Resources Importer updates are complete. Next you'll apply Clay 
markup patterns to the theme's custom UI. 
