---
header-id: customizing-atlas-and-clay-base-themes
---

# Customizing Atlas and Clay Base Themes in @product@

[TOC levels=1-4]

Whether you're customizing the Atlas or Clay base theme, the process is, for the 
most part, the same. Follow these steps. If you're customizing the Clay base 
theme, skip to step 3. 

1.  By default, Clay base is imported into the theme. If you're overwriting 
    Atlas, add a file named `clay.scss` to your theme's `/src/css/` folder and 
    import `clay/atlas` instead:

    ```scss
    @import "clay/atlas";
    ```

2.  By default, Clay base variables are imported into the theme. If you're 
    overwriting Atlas, add an `_imports.scss` file to your theme's `/src/css/` 
    folder and import Atlas variables instead:

    ```scss
    @import "bourbon";

    @import "mixins";

    @import "compat/mixins";

    @import "clay/atlas-variables";
    ```

    | **Note:** Bourbon mixins are deprecated as of @product-ver@ and will be 
    | removed in the next major release. We recommend you use Clay mixins 
    | instead. To use Clay mixins, follow the instructions in 
    | [Using Clay Mixins in Your Theme](/docs/7-2/frameworks/-/knowledge_base/f/using-clay-mixins-in-your-theme)

3.  Add a file named `_clay_variables.scss`. Place your Atlas, Bootstrap, and 
    Clay Base variable modifications in this file.

Great! Now you know how to customize the Atlas and Clay base themes. 

## Related Topics

- [Integrating Third Party Themes with Clay](/docs/7-2/frameworks/-/knowledge_base/f/integrating-third-party-themes-with-clay)
- [Using Clay Mixins in Your Theme](/docs/7-2/frameworks/-/knowledge_base/f/using-clay-mixins-in-your-theme)
