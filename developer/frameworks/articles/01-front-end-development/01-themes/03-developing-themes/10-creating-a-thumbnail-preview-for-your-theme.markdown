---
header-id: creating-a-thumbnail-preview-for-your-theme
---

# Creating a Thumbnail Preview for Your Theme

[TOC levels=1-4]

When you apply a theme to your site pages, you have to choose from the list of 
available themes in the site selector. The only identification for each theme is 
the theme's name, along with a small thumbnail preview image that gives a brief 
impression of the theme. This is even more important when developing color 
schemes for a theme, since names are not displayed for color schemes. 
 
This article shows how to create a thumbnail preview for your theme so users can 
identify it. 

![Figure 1: Your theme thumbnail is displayed with the rest of the available themes.](../../../../images/theme-dev-theme-thumbnail-default.png)

Your first step in creating a thumbnail preview for your theme is taking a 
screenshot of your theme. Once you have a screenshot that you like, follow the 
steps below to create a thumbnail preview for your theme:

1.  Resize the screenshot to 480 pixels high by 270 pixels wide. Your thumbnail 
    *must be* these exact dimensions to display properly. 

2.  Save the image as a `.png` file named `thumbnail.png` and place it in the
    theme's `src/images` folder (create this folder if it doesn't already 
    exist).

    | **Note:** The 
    | [Theme Builder Gradle plugin](/docs/7-2/reference/-/knowledge_base/reference/theme-builder-gradle-plugin) 
    | doesn't recognize a `thumbnail.png` file. If you're using this plugin to 
    | build your theme instead, you must create a `screenshot.png` file in your 
    | theme's `images` folder that is 1080 pixels high by 864 pixels wide. The 
    | thumbnail is automatically generated from the screenshot for you when the 
    | theme is built.

3.  Redeploy the theme. The  file is displayed as the theme's
    thumbnail.

![Figure 2: Your theme thumbnail is displayed with the rest of the available themes.](../../../../images/theme-dev-theme-thumbnail-custom.png)

## Related Topics

- [Installing the Theme Generator and Creating a Theme](/docs/7-2/reference/-/knowledge_base/reference/installing-the-theme-generator-and-creating-a-theme)
- [Creating Reusable Pieces of Code for Your Themes](/docs/7-2/frameworks/-/knowledge_base/frameworks/creating-reusable-pieces-of-code-for-your-themes)
- [Creating Color Schemes for Your Theme](/docs/7-2/frameworks/-/knowledge_base/frameworks/creating-color-schemes-for-your-theme)
