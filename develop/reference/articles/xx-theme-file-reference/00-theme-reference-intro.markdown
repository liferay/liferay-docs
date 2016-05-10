# Theme Reference Guide [](id=theme-reference-guide)

A theme is made up of several files. Although most of the files are named after 
their matching component, you may be unsure of the function of some of these 
files.

This document explains each file's usage in more detail, so that you will know
which files to modify and which files to leave untouched.

## Theme Anatomy [](id=theme-anatomy)

There are two main approaches to theme development for @product@ 7: themes built 
using the Node build tools with the [themes generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator) 
and themes built using the Plugins SDK.

For themes developed with the themes generator, your theme anatomy is organized
with the structure shown below. Although themes developed with the Plugins SDK 
have a slightly different anatomy, the core theme files are the same. Note that 
the `build` directory is shown for reference, and is not created by default.

- `theme-name/`
    - `build/`(not created automatically)
        - `css/`
            - [`_application.scss`](#application-scss)
            - [`_aui_custom.scss`](#auicustom-scss)
            - [`_aui_variables.scss`](#auivariables-scss)
            - [`_base.scss`](#base-scss)
            - [`_custom.scss`](#custom-scss)
            - [`_extras.scss`](#extras-scss)
            - [`_imports.scss`](#imports-scss)
            - [`_layout.scss`](#layout-scss)
            - [`_liferay_custom.scss`](#liferaycustom-scss)
            - [`_liferay_variables_custom.scss`](#liferayvariablescustom-scss)
            - [`_liferay_variables.scss`](#liferayvariables-scss)
            - [`_navigation.scss`](#navigation-scss)
            - [`_portal.scss`](#portal-scss)
            - [`_portlet.scss`](#portlet-scss)
            - [`_taglib.scss`](#taglib-scss)
            - `application/`
            - `aui/`
            - [`aui.scss`](#aui-scss)
            - `base/`
            - `layout/`
            - [`main.scss`](#main-scss)
            - `navigation/`
            - `portal/`
            - `portlet/`
            - `taglib/`
        - `templates/`
            - [`init_custom.ftl`](#initcustom-ftl)
            - [`init.ftl`](#init-ftl)
            - [`navigation.ftl`](#navigation-ftl)
            - [`portal_normal.ftl`](#portalnormal-ftl)
            - [`portal_pop_up.ftl`](#portalpopup-ftl)
            - [`portlet.ftl`](#portlet-ftl)
            - (other directories that have been copied from src)
    - `dist/` (not created automatically)
    - [`gulpfile.js`](#gulpfile-js)
    - [`liferay-theme.json`](#liferay-theme-json)
    - `node_modules/`
        - (many directories)
    - [`package.json`](#package-json)
    - `src/`
        - `css/`
            - (modified CSS files)
        - `images/`
            -   (many directories)
        - `js/`
            - [`main.js`](#main-js)
        - `templates/`
            - (Modified theme templates)
        - `WEB-INF/`
                - `lib/`
            - [`liferay-look-and-feel.xml`](#liferay-look-and-feel-xml)
            - [`liferay-plugin-package.properties`](#liferay-plugin-package-properties)
            - `src/`
                - `resources-importer/`
                    - (Many directories)
                    
Regarding CSS files, it is recommended that you only modify 
`_custom.scss`, `_aui_custom.scss`, `_aui_variables.scss`, and 
`_liferay_variables_custom.scss`.

You can of course overwrite any CSS file that you wish, but if you modify any 
other files, you will most likely be removing styling that @product@ 7 needs to 
work properly. It's recommended that you overwrite the individual files that 
aren't in the root `css` directory instead, i.e. 
`css/application/_cards.scss for example`, if you want to modify core styling.

## Theme Files [](id=theme-files)

### _application.scss [](id=application-scss)

Contains imports for application styles. Generally these files style components 
that aren't Liferay specific, i.e. Alloy or Bootstrap components.

### _aui_custom.scss [](id=auicustom-scss)

Used for AUI custom styles, i.e. styles for a third party Bootstrap theme.
Anything written in this file is compiled in the same scope as Bootstrap/Lexicon, 
so you can use their variables, mixins, etc. You can also implement any of the 
variables you define in `_aui_variables.scss`.

### _aui_variables.scss [](id=auivariables-scss)

Contains the Font Awesome icon imports for Liferay. Also, used to store custom
Sass variables. This file get's injected into the Bootstrap/Lexicon build, so 
you can overwrite variables and change how those libraries are compiled.

### _base.scss [](id=base-scss)

Contains imports for the base styles for Liferay.

### _custom.scss [](id=custom-scss)

Used for custom CSS styles. It is recommended that you place all of your custom 
CSS modfications in this file.

### _extras.scss [](id=extras-scss)

Contains styling that is considered non-essential and potentially dated in the 
near future i.e. box-shadows, rounded corners, etc. This allows for easy
maintenance.

### _imports.scss [](id=imports-scss)

Contains imports for third-party libraries required for the theme e.g. Bourbon, 
Liferay Mixins, Lexicon Base Variables, and Bootstrap Mixins.

### _layout.scss [](id=layout-scss)

Contains imports for layout styles and variables.

### _liferay_custom.scss [](id=liferaycustom-scss)

Contains Liferay styles that are compiled in the same scope as Bootstrap/Lexicon.

**It's recommended that you NOT overwrite this file.**

### _liferay_variables_custom.scss [](id=liferayvariablescustom-scss)

Used for overwriting variables defined in `_liferay_variables.scss` without 
wiping out the whole file.

### _liferay_variables.scss [](id=liferayvariables-scss)

Contains variables that are used in `_liferay_custom.scss`.

**It's recommended that you NOT overwrite this file.**

### _navigation.scss [](id=navigation-scss)

Contains imports for navigation styles.

### _portal.scss [](id=portal-scss)

Contains imports for Portal components.

### _taglib.scss [](id=taglib-scss)

Contains imports for taglib styles.

### aui.scss [](id=aui-scss)

Contains the Lexicon base CSS import. If you want to just use Bootstrap, or use 
Atlas, you can do so by adding one of the following imports:

    @import "aui/lexicon/bootstrap";

or

    @import "aui/lexicon/atlas"; 

### main.scss [](id=main-scss)

Contains imports for the core CSS files.

### init_custom.ftl [](id=initcustom-ftl)

Used for custom FreeMarker variables i.e. theme setting variables.

### init.ftl [](id=init-ftl)

Contains common FreeMarker variables that are available to use in your theme 
templates. Useful for reference if you need access to theme objects.

**It's recommended that you NOT overwrite this file.**

### navigation.ftl [](id=navigation-ftl)

The theme template for the theme's navigation.

### portal_normal.ftl [](id=portalnormal-ftl)

Similar to the `index.html` of a website, this file acts as a hub for all of the 
theme templates.

### portal_pop_up.ftl [](id=portalpopup-ftl)

The theme template for pop up dialogs for the theme's portlets.

### portlet.ftl [](id=portlet-ftl)

The theme template for the theme's portlets. If your theme uses Application 
Decorators, you can modify this file to create application decorator specific 
theme settings. See the [Application Decorators tutorial](/develop/tutorials/-/knowledge_base/7-0/application-decorators) 
for more info.

### gulpfile.js [](id=gulpfile-js)

Defines the required gulp tasks for node tool developed themes.

**It's recommended that you NOT overwrite this file.**

### main.js [](id=main-js)

Used for custom JavaScript.

### liferay-plugin-package.properties [](id=liferay-plugin-package-properties)

Contains general properties for the theme. [Resources Importer]{/develop/tutorials/-/knowledge_base/7-0/importing-resources-with-a-theme} 
configuration settings are also placed in this file. For a full explanation of 
the properties available for this file please see the [7.0 Propertiesdoc](https://docs.liferay.com/portal/7.0/propertiesdoc/liferay-plugin-package_7_0_0.properties.html).

### liferay-look-and-feel.xml [](id=liferay-look-and-feel-xml)

Contains basic information for the theme. If your theme has [theme settings](),
they are defined in this file. For a full explanation of this file please see
the [Definitions docs](https://docs.liferay.com/portal/7.0/definitions/liferay-look-and-feel_7_0_0.dtd.html).

### liferay-theme.json [](id=liferay-theme-json)

Contains the configuration settings for your app server, in node tool based 
themes. You can change this file manually at any time to update your server 
settings. The file can also be updated via the `gulp init` task. 

### package.json [](id=package-json)

contains theme setting information such as the theme template langauge, version, 
and base theme, for node tool developed themes. This file can be updated 
manually. The `gulp extend` task can also be used to change the base theme.
