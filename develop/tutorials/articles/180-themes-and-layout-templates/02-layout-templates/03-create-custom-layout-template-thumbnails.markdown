# Creating Custom Layout Template Thumbnail Previews [](id=creating-custom-layout-template-thumbnail-previews)

If you 
[created a layout template with the Theme Generator](/develop/tutorials/-/knowledge_base/7-1/creating-layout-templates-with-the-themes-generator), 
it generated a default thumbnail preview for your layout template. Follow these 
steps to create a custom preview thumbnail for a layout template:

1.  Open the Command-line and navigate to the `docroot/layouttpl/custom` folder 
    of your layout template. If you created the layout template in a theme 
    created with the Liferay Theme Generator, the thumbnail is located in your 
    theme's `src/layouttpl/custom` folder.

2.  Replace the thumbnail PNG file, if it exists, with a custom thumbnail PNG 
    with the same dimensions (120 x 120 px), or create a new one.

3.  If including the layout template with a theme, specify the thumbnail's 
    location in your theme's `liferay-look-and-feel.xml`, using the 
    `<thumbnail-path>` tag. Below is an example configuration for the Porygon 
    theme:
    
        <layout-template id="porygon_50_50_width_limited" 
        name="Porygon 2 Columns (50/50) width limited">
            <template-path>
                /layoutttpl/custom/porygon_50_50_width_limited.ftl
            </template-path>
            <thumbnail-path>
                /layoutttpl/custom/porygon_50_50_width_limited.png
            </thumbnail-path>
        </layout-template>

Deploy your layout template to your app server to use it. If your layout 
template is 
[bundled with a theme](/develop/tutorials/-/knowledge_base/7-1/including-layout-templates-with-a-theme), 
it deploys when the theme is deployed. Now you know how to create a custom 
thumbnail preview for your @product@ layout templates!

## Related topics [](id=related-topics)

[Layout Templates with the Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-1/creating-layout-templates-with-the-themes-generator-0)

[Creating Layout Templates Manually](/develop/tutorials/-/knowledge_base/7-1/creating-layout-templates-manually)

[Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-1/themes-generator)

