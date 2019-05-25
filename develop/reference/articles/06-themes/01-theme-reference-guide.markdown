---
header-id: theme-reference-guide
---

# Theme Reference Guide

[TOC levels=1-4]

A theme is made up of several files. Although most of the files are named after
their matching components, their functions may be unclear. This reference guide
explains each file's usage to make clear which files to modify.

## Theme Anatomy

There are two main approaches to theme development for @product-ver@: themes 
built using the Node.js build tools with the [theme generator](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes) 
and [themes built using @ide@](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes-with-liferay-ide). 

Themes developed with the theme generator have the anatomy shown below. Although 
themes developed with @ide@ have a slightly different anatomy built with the
[theme project template](/docs/7-1/reference/-/knowledge_base/r/theme-template), 
the core theme files are the same. 

- `theme-name/`
    - `src/`
        - `css/`
            - [`_clay_custom.scss`](/docs/7-1/reference/-/knowledge_base/r/theme-reference-guide#claycustom-scss)
            - [`_clay_variables.scss`](/docs/7-1/reference/-/knowledge_base/r/theme-reference-guide#auivariables-scss)
            - [`_custom.scss`](/docs/7-1/reference/-/knowledge_base/r/theme-reference-guide#custom-scss)
            - [`_liferay_variables_custom.scss`](/docs/7-1/reference/-/knowledge_base/r/theme-reference-guide#liferayvariablescustom-scss)
        - `images/`
            -   (custom images)
        - `js/`
            - [`main.js`](/docs/7-1/reference/-/knowledge_base/r/theme-reference-guide#main-js)
        - `templates/`
            - [`init_custom.ftl`](/docs/7-1/reference/-/knowledge_base/r/theme-reference-guide#initcustom-ftl)
            - [`navigation.ftl`](/docs/7-1/reference/-/knowledge_base/r/theme-reference-guide#navigation-ftl)
            - [`portal_normal.ftl`](/docs/7-1/reference/-/knowledge_base/r/theme-reference-guide#portalnormal-ftl)
            - [`portal_pop_up.ftl`](/docs/7-1/reference/-/knowledge_base/r/theme-reference-guide#portalpopup-ftl)
            - [`portlet.ftl`](/docs/7-1/reference/-/knowledge_base/r/theme-reference-guide#portlet-ftl)
        - `WEB-INF/`
            - [`liferay-look-and-feel.xml`](/docs/7-1/reference/-/knowledge_base/r/theme-reference-guide#liferay-look-and-feel-xml)
            - [`liferay-plugin-package.properties`](/docs/7-1/reference/-/knowledge_base/r/theme-reference-guide#liferay-plugin-package-properties)
            - `src/`
                - `resources-importer/`
                    - (Many directories)
    - [`liferay-theme.json`](/docs/7-1/reference/-/knowledge_base/r/theme-reference-guide#liferay-theme-json)
    - [`package.json`](/docs/7-1/reference/-/knowledge_base/r/theme-reference-guide#package-json)
 
Regarding CSS files, you should only modify `_clay_custom.scss`,
`_clay_variables.scss`, `_custom.scss`, and `_liferay_variables_custom.scss`.

You can of course overwrite any CSS file you want, but if you modify any other
files, you're removing styling that @product-ver@ needs to work properly.

## Theme Files

### _clay_custom.scss

Used for Clay custom styles, i.e. styles for a third party Bootstrap theme. 
Anything written in this file is compiled in the same scope as Bootstrap/Lexicon, 
so you can use their variables, mixins, etc. You can also implement any of the 
variables you define in `_clay_variables.scss`.

### _clay_variables.scss

Used to store custom Sass variables. This file gets injected into the 
Bootstrap/Lexicon build, so you can overwrite variables and change how those 
libraries are compiled.

### _custom.scss

Used for custom CSS styles. You should place all of your custom CSS
modifications in this file.

### _liferay_variables_custom.scss

Used for overwriting variables defined in `_liferay_variables.scss` without 
wiping out the whole file.

### init_custom.ftl

Used for custom FreeMarker variables i.e. 
[theme setting](/docs/7-1/tutorials/-/knowledge_base/t/making-configurable-theme-settings) 
variables.

### navigation.ftl

The theme template for the theme's navigation.

### portal_normal.ftl

Similar to a static site's `index.html`, this file acts as a hub for all theme
templates.

### portal_pop_up.ftl

The theme template for pop up dialogs for the theme's portlets.

### portlet.ftl

The theme template for the theme's portlets. If your theme uses Application 
Decorators, you can modify this file to create application decorator-specific 
theme settings. See the 
[Portlet Decorators tutorial](/docs/7-1/tutorials/-/knowledge_base/t/creating-configurable-styles-for-portlet-wrappers) 
for more info.

### liferay-theme.json

Contains the configuration settings for your app server, in Node.js tool-based 
themes. You can change this file manually at any time to update your server 
settings. The file can also be updated via the 
[`gulp init` task](/docs/7-1/tutorials/-/knowledge_base/t/configuring-your-themes-app-server). 

### package.json

Contains theme setting information such as the theme template language, version,
and base theme, for Node.js tool developed themes. You can update this file
manually. The 
[`gulp extend` task](/docs/7-1/tutorials/-/knowledge_base/t/changing-your-base-theme) can also
be used to change the base theme. 

### main.js

Used for custom JavaScript.

### liferay-look-and-feel.xml

Contains basic information for the theme. If your theme has 
[theme settings](/docs/7-1/tutorials/-/knowledge_base/t/making-configurable-theme-settings),
they are defined in this file. For a full explanation of this file, please see
the [Definitions docs](@platform-ref@/7.1-latest/definitions/liferay-look-and-feel_7_1_0.dtd.html). 

### liferay-plugin-package.properties

Contains general properties for the theme. 
[Resources Importer](/docs/7-1/tutorials/-/knowledge_base/t/importing-resources-with-a-theme) 
configuration settings are also placed in this file. For a full explanation of 
the properties available for this file please see the 
[7.1 Properties documentation](@platform-ref@/7.1-latest/propertiesdoc/liferay-plugin-package_7_1_0.properties.html). 
