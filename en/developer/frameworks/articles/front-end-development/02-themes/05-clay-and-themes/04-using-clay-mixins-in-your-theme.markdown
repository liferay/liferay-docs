---
header-id: using-clay-mixins-in-your-theme
---

# Using Clay Mixins in Your Theme

[TOC levels=1-4]

Bourbon mixins are deprecated as of @product-ver@ and will be removed in the 
next major release. We recommend you use Clay mixins instead. Follow these steps 
to use Clay mixins in your theme:

1.  Add the `clay-css` dependency to the theme's `package.json`:

    ```json
    "dependencies":{
      "clay-css": "^2.18.0",
    }
    ```

2.  Delete `_imports.scss` if you modified it.

3.  Import the library into the theme's `main.scss` file:

    ```scss
    @import 'node_modules/clay-css/src/scss/atlas-variables'
    ```

    or import the base-variables if you want to use Clay Base instead:

    ```scss
    @import 'node_modules/clay-css/src/scss/base-variables'
    ```
    
Great! Now you know how to use Clay mixins in your theme! 

## Related Topics

- [Customizing Atlas and Clay Base Theme](/docs/7-2/frameworks/-/knowledge_base/f/customizing-atlas-and-clay-base-themes)
