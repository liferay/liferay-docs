# Creating a Thumbnail Preview for Your Theme [](id=creating-a-thumbnail-preview-for-your-theme)

When you apply a theme to your site pages, you have to choose from the list of 
available themes in the site selector. The only identification for each theme is 
the theme's name, along with a small thumbnail preview image that gives a brief 
impression of the theme. By default, themes don't provide a thumbnail image, so 
you must create one. This is even more important when developing color schemes 
for a theme. Names are not displayed for color schemes, so a thumbnail preview 
is required to identify them. 

![Figure 1: Your theme thumbnail is displayed with the rest of the available themes.](../../../../images/theme-dev-theme-thumbnail-default.png)

Follow these steps to create a thumbnail preview for your theme:

1.  With your theme applied to your site and an optional color scheme chosen, 
    take a screenshot that best represents it.

2.  Crop any unwanted areas and re-size the screenshot to 150 pixels high by 120 
    pixels wide. Your thumbnail *must be* these exact dimensions to display 
    properly. 

3.  Save the image as a `.png` file named `thumbnail.png` and place it in the
    theme's `images` folder (create this folder if it doesn't already exist). On 
    redeployment, the `thumbnail.png` file automatically becomes the theme's
    thumbnail.

Now, when you apply the theme, its thumbnail displays along with the other
themes that are available to your site.

![Figure 2: Your theme thumbnail is displayed with the rest of the available themes.](../../../../images/theme-dev-theme-thumbnail-custom.png)

## Related Topics [](id=related-topics)

[Creating Themes](/develop/tutorials/-/knowledge_base/7-1/creating-themes)

[Creating Reusable Pieces of Code for Your Themes](/develop/tutorials/-/knowledge_base/7-1/creating-reusable-pieces-of-code-for-your-themes)

[Creating Color Schemes for Your Theme](/develop/tutorials/-/knowledge_base/7-1/creating-color-schemes-for-your-theme)
