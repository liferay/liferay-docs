---
header-id: creating-custom-layout-template-thumbnail-previews
---

# Creating Custom Layout Template Thumbnail Previews

[TOC levels=1-4]

To showcase your layout template properly, you must provide a thumbnail preview 
for it. Without this, no one will know the design of the layout. Follow these 
steps to provide a thumbnail preview for your layout template:

1.  Navigate to the layout template's `docroot/` folder. If you bundled the 
    layout template with a theme created with the Liferay Theme Generator, the 
    thumbnail is located in your theme's `src/layouttpl/custom/my-layoutttpl` 
    folder.

2.  Create a custom thumbnail PNG inside the folder specified in step 1 with the 
    same name as the layout template that is 120 px x 120 px . Delete the 
    temporary thumbnail PNG file if it exist.
    
    ![Figure 1: A thumbnail preview displays the layout's design to the user.](../../../images/porygon_50_50_width_limited.png)

3.  Deploy your layout template to your app server to use it. If your layout 
    template is [bundled with a theme](/docs/7-2/frameworks/-/knowledge_base/f/including-layout-templates-with-a-theme), 
    it deploys when the theme is deployed. Now you know how to create a custom 
    thumbnail preview for your @product@ layout templates! 

## Related topics

- [Layout Templates with the Liferay Theme Generator](/docs/7-2/reference/-/knowledge_base/r/creating-layout-templates-with-the-themes-generator)
- [Bundling Layout Templates with a Theme](/docs/7-2/frameworks/-/knowledge_base/f/including-layout-templates-with-a-theme)
- [Themes](/docs/7-2/frameworks/-/knowledge_base/f/themes-introduction)
