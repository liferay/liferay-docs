---
header-id: using-clay-icons-in-a-theme
---

# Using Clay Icons in a Theme

[TOC levels=1-4]

To use Clay icons in your themes, you must use the [clay taglib macro](/docs/7-2/reference/-/knowledge_base/r/freemarker-taglib-macros). 
If you want to use Clay icons in your portlets, follow the steps in the 
[Clay taglib icons](/docs/7-2/reference/-/knowledge_base/r/clay-icons) article. 
To use Clay icons in your theme, follow these steps:

1.  Open the FreeMarker theme template you want to use the Clay icon in.

2.  Use the `@clay["icon"]` macro and specify the icon with the `symbol` 
    attribute, as shown in the pattern below:

    ```markup
    <@clay["icon"] symbol="icon-name" />
    ```
    
    The full list of icons can be found on [ClayUI's site](https://clayui.com/docs/components/icon.html) 
    (CSS/Markup tab). Here is an example configuration for a Facebook social 
    media icon:

    ```markup
        <a class="text-white"
        href="http://www.facebook.com/pages/Liferay/45119213107" 
        target="_blank">
          <span class="hide">Facebook</span>
          <@clay["icon"] symbol="social-facebook" />
        </a>
    ```
    
Great! Now you know how to use Clay icons in your theme. 

## Related Topics

- [FreeMarker Taglib Macros](/docs/7-2/reference/-/knowledge_base/r/freemarker-taglib-macros)
- [Clay Icons](/docs/7-2/reference/-/knowledge_base/r/clay-icons)
- [Customizing Atlas and Clay Base Themes](/docs/7-2/frameworks/-/knowledge_base/f/customizing-atlas-and-clay-base-themes)
