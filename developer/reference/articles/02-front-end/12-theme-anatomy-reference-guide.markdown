---
header-id: theme-reference-guide
---

# Theme Anatomy Reference Guide

[TOC levels=1-4]

A theme is made up of several files. Although most of the files are named after
their matching components, their functions may be unclear. This reference guide
explains each file's usage to make clear which files to modify.

Themes built with the [Liferay JS Theme Toolkit](https://github.com/liferay/liferay-js-themes-toolkit/tree/master/packages) 
have the anatomy shown below: 

- `theme-name/`
    - `src/`
        - `css/`
            - [`_clay_custom.scss`](/docs/7-2/reference/-/knowledge_base/r/theme-reference-guide#-clay-customscss)
            - [`_clay_variables.scss`](/docs/7-2/reference/-/knowledge_base/r/theme-reference-guide#-clay-variablesscss)
            - [`_custom.scss`](/docs/7-2/reference/-/knowledge_base/r/theme-reference-guide#-customscss)
            - [`_liferay_variables_custom.scss`](/docs/7-2/reference/-/knowledge_base/r/theme-reference-guide#-liferay-variables-customscss)
        - `images/`
            -   (custom images)
        - `js/`
            - [`main.js`](/docs/7-2/reference/-/knowledge_base/r/theme-reference-guide#mainjs)
        - `templates/`
            - [`init_custom.ftl`](/docs/7-2/reference/-/knowledge_base/r/theme-reference-guide#init-customftl)
            - [`navigation.ftl`](/docs/7-2/reference/-/knowledge_base/r/theme-reference-guide#navigationftl)
            - [`portal_normal.ftl`](/docs/7-2/reference/-/knowledge_base/r/theme-reference-guide#portal-normalftl)
            - [`portal_pop_up.ftl`](/docs/7-2/reference/-/knowledge_base/r/theme-reference-guide#portal-pop-upftl)
            - [`portlet.ftl`](/docs/7-2/reference/-/knowledge_base/r/theme-reference-guide#portletftl)
        - `WEB-INF/`
            - [`liferay-look-and-feel.xml`](/docs/7-2/reference/-/knowledge_base/r/theme-reference-guide#liferay-look-and-feelxml)
            - [`liferay-plugin-package.properties`](/docs/7-2/reference/-/knowledge_base/r/theme-reference-guide#liferay-plugin-packageproperties)
            - `src/`
                - `resources-importer/`
                    - (Many directories)
    - [`liferay-theme.json`](/docs/7-2/reference/-/knowledge_base/r/theme-reference-guide#liferay-themejson)
    - [`package.json`](/docs/7-2/reference/-/knowledge_base/r/theme-reference-guide#packagejson)
 
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
[theme setting](/docs/7-2/frameworks/-/knowledge_base/f/making-configurable-theme-settings) 
variables.

### navigation.ftl

The theme template for the theme's navigation.

### portal_normal.ftl

Similar to a static site's `index.html`, this file acts as a hub for all theme
templates.

### portal_pop_up.ftl

The theme template for pop up dialogs for the theme's portlets.

### portlet.ftl

The theme template for the theme's portlets. If your theme uses [Application 
Decorators](/docs/7-2/frameworks/-/knowledge_base/f/theming-portlets#portlet-decorators), 
you can modify this file to create application decorator-specific theme 
settings.

### liferay-theme.json

Contains the configuration settings for your app server, in Node.js tool-based 
themes. You can change this file manually at any time to update your server 
settings. The file can also be updated via the 
[`gulp init` task](/docs/7-2/frameworks/-/knowledge_base/f/updating-your-themes-app-server). 

### package.json

Contains theme setting information such as the theme template language, version,
and base theme, for Node.js tool developed themes. You can update this file
manually. The 
[`gulp extend` task](/docs/7-2/frameworks/-/knowledge_base/f/changing-your-base-theme) 
can also be used to change the base theme. 

### main.js

Used for custom JavaScript.

### liferay-look-and-feel.xml

Contains basic information for the theme. If your theme has 
[theme settings](/docs/7-2/frameworks/-/knowledge_base/f/making-configurable-theme-settings),
they are defined in this file. For a full explanation of this file, please see
the [Definitions docs](@platform-ref@/7.2-latest/definitions/liferay-look-and-feel_7_2_0.dtd.html). 

### liferay-plugin-package.properties

Contains general properties for the theme. 
[Resources Importer](/docs/7-2/frameworks/-/knowledge_base/f/importing-resources-with-a-theme) 
configuration settings are also placed in this file. For a full explanation of 
the properties available for this file please see the 
[7.2 Properties documentation](@platform-ref@/7.2-latest/propertiesdoc/liferay-plugin-package_7_2_0.properties.html). 
