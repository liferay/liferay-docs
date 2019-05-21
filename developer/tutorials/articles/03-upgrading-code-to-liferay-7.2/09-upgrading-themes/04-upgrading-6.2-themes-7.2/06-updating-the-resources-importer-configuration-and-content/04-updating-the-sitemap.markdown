# Updating the 6.2 Sitemap

<div class="learn-path-step">
    <p>Updating 6.2 Resources Importer<br>Step 3 of 3</p>
</div>

In Liferay Portal 6.2, portlet IDs were incremental numbers. In @product-ver@, 
they're explicit class names. Update the `sitemap.json` file with the new 
portlet IDs. Follow these steps to update the `sitemap.json`:

1.  Replace the portlet IDs with the updated class names. Some common portlet 
    IDs are specified in the `sitemap.json` example in 
    [Creating a Sitemap for the Resources Importer](/docs/7-2/frameworks/-/knowledge_base/f/creating-a-sitemap-for-the-resources-importer). The [Portlet ID Quick Reference Guide](/docs/7-2/reference/-/knowledge_base/r/fully-qualified-portlet-ids)
    shows all the default portlet IDs. Check `liferay-portlet.xml` for the 
    portlet ID number in 6.2 and replace it with the updated ID in the quick 
    reference Guide.
    
    | **NOTE:** you can also retrieve a portlet's ID from the UI. Open the 
    | portlet's *Options* menu, select *Look and Feel Configuration*. 
    | 
    | ![Figure 1: You can find the portlet ID in the *Look and Feel Configuration* menu.](../../../../images/upgrading-themes-look-and-feel-menu.png)
    | 
    | Select the *Advanced Styling* tab. The `Portlet ID` value appears in the 
    | blue box. 
    | 
    | ![Figure 2: The portlet ID appears within the blue box in the *Advanced Styling* tab.](../../../../images/upgrading-themes-portlet-id.png)

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
    
Great! The Resources Importer updates are complete. Next you'll update the 
markup used 