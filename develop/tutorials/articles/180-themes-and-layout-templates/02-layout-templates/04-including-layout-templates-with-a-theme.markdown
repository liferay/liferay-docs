# Including Layout Templates with a Theme [](id=including-layout-templates-with-a-theme)

Although you can deploy a layout template by itself, you can also bundle it with 
a theme. To include a layout template with a theme, follow these steps:

1.  Open your theme's `liferay-look-and-feel.xml` file, and nest 
    `<layout-templates>` tags in between the `<theme>...</theme>` tags so it 
    matches the configuration below:
    
        <theme id="my-theme-name" name="My Theme Name">
            ...
              <layout-templates>
                <custom>
                    //layout template code goes here
                </custom>
              </layout-templates>
            ...
        </theme>  
    
2.  Place the layout template in between the `<custom>...</custom>` tags, using 
    the `<layout-template>` tag. The `<layout-template>` tag's `id` attribute 
    **must match** the layout template's filename. Below is an example 
    configuration:
    
        <layout-template id="my_liferay_layout_template" 
        name="My Liferay Layout Template">
    
3.  Specify the layout template's path with a `<template-path>` tag, as shown 
    below:

        <template-path>
            /layoutttpl/custom/my_liferay_layout_template.ftl
        </template-path>
    
4.  Specify the 
    [layout template thumbnail's](/develop/tutorials/-/knowledge_base/7-1/creating-a-custom-thumbnail-for-your-layout-template) 
    path with a `<thumbnail-path>` tag, as shown below:

        <thumbnail-path>
            /layoutttpl/custom/my_liferay_layout_template.png
        </thumbnail-path>
    
5.  Place the completed layout in your theme's `src/layouttpl` folder if you 
    created your theme with the Liferay Theme Generator, or place it in your 
    theme's `docroot/layouttpl/custom` folder if using the Plugins SDK. Below is 
    an example `liferay-look-and-feel` configuration:

        <theme id="my-theme-name" name="My Theme Name">
            ...
              <layout-templates>
                <custom>
                  <layout-template id="my_liferay_layout_template" 
                  name="My Liferay Layout Template">
                    <template-path>
                        /layoutttpl/custom/my_liferay_layout_template.ftl
                    </template-path>
                    <thumbnail-path>
                        /layoutttpl/custom/my_liferay_layout_template.png
                    </thumbnail-path>
                  </layout-template>
                </custom>
              </layout-templates>
            ...
        </theme>

Now you know how to include layout templates with your @product@ themes!

## Related topics [](id=related-topics)

[Creating Custom Layout Template Thumbnail Previews](/develop/tutorials/-/knowledge_base/7-1/creating-custom-layout-template-thumbnail-previews)

[Layout Templates with the Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-1/creating-layout-templates-with-the-themes-generator)

[Creating Layout Templates Manually](/develop/tutorials/-/knowledge_base/7-1/creating-layout-templates-manually)
