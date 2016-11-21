# Upgrading Themes [](id=upgrading-themes)

If you've developed themes in Liferay Portal 6.2, as part of your upgrade you'll want
to use them in @product-ver@. While you're at it, you should leverage theme
improvements, including support for Sass, Bootstrap 3, and Lexicon (Liferay's UI
design language). This tutorial demonstrates upgrading a Liferay Portal 6.2 theme to
@product-ver@. 

Theme upgrades involve these steps:

-  Updating project metadata
-  Updating CSS
-  Updating theme templates
-  Updating resources importer configuration and content
-  Applying Lexicon UI design patterns

As an example, this tutorial applies the steps to a Liferay Portal 6.2 theme called the
Lunar Resort theme--developed in the Liferay Portal 6.2 Learning Path [Developing a Liferay Theme](/develop/learning-paths/themes/-/knowledge_base/6-2/developing-a-liferay-theme).
It's similar to many Liferay Portal 6.2 themes as it extends the [`_styled` theme](https://github.com/liferay/liferay-portal/tree/6.2.x/portal-web/docroot/html/themes/_styled),
adding configurable settings and incorporating a responsive design that
leverages Font Awesome icons and Bootstrap. The theme [ZIP file](/documents/10184/656312/lunar-resort-theme-migration-6.2.zip)
contains its original source code.

![Figure 1: The Lunar Resort example theme upgraded in this tutorial uses a clean, minimal design.](../../../images/finished-theme.png)

Before upgrading a theme, consider [migrating the theme](/develop/tutorials/-/knowledge_base/7-0/migrating-a-6-2-theme-to-liferay-7)
to use the [Themes Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator).
@product-ver@ doesn't require this migration, but the Themes Generator's `upgrade`
Gulp task automates many upgrade steps. Themes Generator themes can also
leverage exclusive new features, such as
[Themelets](/develop/tutorials/-/knowledge_base/7-0/themelets).
If you migrate your theme, return here afterward to upgrade it.

No matter the environment in which you're developing your theme, this tutorial
explains everything required to upgrade it. The easiest option is to use the
Themes Generator's `upgrade` Gulp task, so you'll see that first. Then you'll
see *all* upgrade steps, in case you want to run them manually. 

## Running the Upgrade Task for Themes Generator Themes [](id=running-the-upgrade-task-for-themes-generator-themes)

A Liferay Portal 6.2 theme can be upgraded to @product-ver@, regardless of its project
environment (Themes Generator, Plugins SDK, Maven, etc.). But a theme that's
been migrated to the Themes Generator can leverage the theme `upgrade` Gulp task.
If you're developing your theme in an environment other than the Themes
Generator, skip to the *Updating Project Metadata* section. 

Here's what the Gulp Task does:

- Updates the theme's Liferay version
- Updates the CSS
- Suggests specific code updates

The Gulp task automatically upgrades CSS code that it can identify. For
everything else, it suggests upgrades. 

Here are the steps for using the theme `upgrade` Gulp task:

1.  In your theme's root directory, run this command:

        gulp upgrade

    Here's what it does initially:
    
    - Copies the existing theme to a folder called `backup`
    - Creates core code for generating theme base files
    - Updates Liferay version references

    +$$$

    **Note**: An upgraded theme can be restored to its original state by
    executing `gulp upgrade:revert`. 

    $$$

    The task continues upgrading CSS files, prompting you to update CSS file
    names.

2.  For @product-ver@, Sass files should use the `.scss` extension and file names for
    Sass partials should start with an underscore (e.g., `_custom.scss`). The
    `upgrade` task prompts you for each CSS file to rename.

The Gulp task makes a best effort to upgrade the theme's Bootstrap code from
version 2 to 3. For other areas of the code it suspects might need updates, it
logs suggestions (covered later). The task also reports changes that may affect
theme templates. 

A *breaking change* is a code modification between versions of @product@ that 
might be incompatible with existing plugins, including themes. Liferay minimized
the number of breaking changes, but couldn't avoid some. The [Breaking Changes](/develop/reference/-/knowledge_base/7-0/breaking-changes)
reference document describes them. The theme `gulp upgrade` command and the 
[Code Upgrade Tool](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool),
in Liferay IDE and Liferay Developer Studio identify and address these changes.

The `upgrade` Gulp task jump-starts the upgrade process, but it doesn't complete
it. Manual updates are required. 

The rest of this tutorial explains all the theme upgrade steps, regardless of
whether the `upgrade` Gulp task performs them. Steps the upgrade task performs
are noted in context. Even if you've already executed the upgrade task, it's
best to learn all the steps and make sure they're applied to your theme.

The next step is to update the theme's metadata. 

## Updating Project Metadata [](id=updating-project-metadata)

If you're developing your theme in an environment other than the Plugins SDK,
skip this section. 

A theme's Liferay version must be updated to `7.0.0+` for the theme to run on
@product-ver@. 

If you're using the Plugins SDK, open the `liferay-plugin-package.properties`
file and change the `liferay-versions` property value to `7.0.0+`: 

        liferay-versions=7.0.0+

If you're using the Themes Generator, open the `liferay-look-and-feel.xml` file
and specify `liferay-look-and-feel_7_0_0.dtd` as the DTD and `7.0.0+` as the
compatibility version: 

        <?xml version="1.0"?>
        <!DOCTYPE look-and-feel PUBLIC "-//Liferay//DTD Look and Feel 7.0.0//EN"
        "http://www.liferay.com/dtd/liferay-look-and-feel_7_0_0.dtd">

        <look-and-feel>
                <compatibility>
                        <version>7.0.0+</version>
                </compatibility>

                ...

        </look-and-feel>

3.  If your theme uses the Themes Generator and has a `package.json` file,
    update the file's Liferay version references to `7.0`.

Your theme's Liferay version references are updated for @product-ver@. Next, you'll
update the CSS.

## Updating CSS Code [](id=updating-css-code)

@product-ver@'s UI improvements required these CSS-related changes:

- Adding new CSS files
- Removing unneeded CSS files
- Class variable changes (required by Bootstrap 3)
- Modifications to CSS responsiveness tokens

The theme upgrade process involves conforming to these changes.

In this section, you'll update your theme's CSS to leverage the styling
improvements. Start with updating CSS file names for Sass. 

### Updating CSS File Names for Sass [](id=updating-css-file-names-for-sass)

Although Sass was available in Liferay Portal 6.2, only Sass partial files followed the
Sass naming convention (using file suffix `.scss`). In @product-ver@ themes, all
Sass files must end in `scss`. 

+$$$

**Note**: The `upgrade` Gulp task renames Sass files automatically.

$$$

For each CSS file you've modified in your theme, except `main.scss` and
`aui.scss`, change its suffix from `.css` to `.scss`. 

Then prepend an underscore (`_`) to all Sass partial file names. 

For example, rename `custom.css` to `_custom.scss`.

Here are the Lunar Resort theme's renamed CSS files:

- `css/`
	- `_aui_variables.scss`
	- `_custom.scss`

Refer to the [Theme Reference Guide](/develop/reference/-/knowledge_base/7-0/theme-reference-guide)
for a complete list of expected theme CSS files. 

Next, the CSS rules must be updated to use Bootstrap 3 syntax.

### Updating CSS Rules [](id=updating-css-rules)

@product-ver@ uses Bootstrap 3's CSS rule syntax. The new syntax lets developers
leverage Bootstrap 3 features and improvements.

If your theme does not use the Themes Generator, you can refer to the
[Migrating from 2.x to 3.0 guide](http://getbootstrap.com/migration/#migrating-from-2x-to-30)
for updating CSS rules to Bootstrap 3. 

If your theme uses the Themes Generator, the `upgrade` Gulp task reports
automatic CSS updates and suggested manual updates. For example, here is part of
the task log for the Lunar Resort theme:

    ----------------------------------------------------------------
     Bootstrap Upgrade (2 to 3)
    ----------------------------------------------------------------

    File: src/css/_aui_variables.scss
        Line 5: "$white" has been removed
        Line 11: "$baseBorderRadius" has changed to "$border-radius-base"
        Line 15: "$btnBackground" has changed to "$btn-default-bg"
        Line 16: "$btnBackgroundHighlight" has been removed
        Line 17: "$btnBorder" has changed to "$btn-default-border"
        Line 18: "$btnDangerBackground" has changed to "$btn-danger-bg"
        Line 19: "$btnDangerBackgroundHighlight" has been removed
        Line 21: "$btnInfoBackgroundHighlight" has been removed
        Line 21: "$btnInfoBackground" has changed to "$btn-info-bg"
        Line 22: "$btnPrimaryBackground" has changed to "$btn-primary-bg"
        Line 23: "$btnPrimaryBackgroundHighlight" has been removed
        Line 24: "$btnSuccessBackground" has changed to "$btn-success-bg"
        Line 25: "$btnSuccessBackgroundHighlight" has been removed
        Line 26: "$btnWarningBackground" has changed to "$btn-warning-bg"
        Line 27: "$btnWarningBackgroundHighlight" has been removed
        Line 29: "$dropdownLinkBackgroundActive" has changed to
        "$dropdown-link-active-bg"
        Line 30: "$dropdownLinkBackgroundHover" has changed to
        "$dropdown-link-hover-bg"
        Line 31: "$dropdownLinkColorActive" has changed to
        "$dropdown-link-active-color"
        Line 31: "$white" has been removed
        Line 34: "$navbarBackgroundHighlight" has been removed
        Line 35: "$navbarBorder" has changed to "$navbar-default-border"
        Line 36: "$navbarBackground" has changed to "$navbar-default-bg"
        Line 36: "$navbarLinkBackgroundActive" has changed to
        "$navbar-default-link-active-bg"
        Line 38: "$linkColorHover" has changed to "$link-hover-color"
        Line 38: "$navbarLinkColorHover" has changed to
        "$navbar-default-link-hover-color"
        Line 39: "$navbarLinkColor" has changed to
        "$navbar-default-link-color"
        Line 39: "$navbarText" has changed to "$navbar-default-color"
        Line 41: "$errorBackground" has changed to "$error-bg"
        Line 45: "$infoBackground" has changed to "$info-bg"
        Line 47: "$successBackground" has changed to "$success-bg"
        Line 50: "$warningBackground" has changed to "$warning-bg"
    File: src/css/custom.css
        Line 201: Padding no longer affects width or height, you may need to
        change your rule (lines 201-227)
        Line 207: Padding no longer affects width or height, you may need to
        change your rule (lines 207-226)
        Line 212: You would change height from "62px" to "82px"
        Line 305: Padding no longer affects width or height, you may need to
        change your rule (lines 305-314)
        Line 308: You would change height from "39px" to "46px"
        Line 403: "nav-collapse" has changed to "navbar-collapse"
        Line 409: Padding no longer affects width or height, you may need to
        change your rule (lines 409-418)
        Line 490: "btn-navbar" has changed to "navbar-btn"
        Line 490: "btn" has changed to "btn btn-default"
        Line 586: "nav-collapse" has changed to "navbar-collapse"

For each update performed and suggested, the task reports a file name and line
number range.

Since Bootstrap 3 adopts the `box-sizing: border-box` property for all elements
and pseudo-elements (e.g., `:before` and `:after`), padding no longer affects
dimensions. [Bootstrap's documentation](http://getbootstrap.com/css/#less-mixins-box-sizing)
describes the box sizing changes. Consider the padding updates the `upgrade`
task reports for CSS rules. 

+$$$

**Note:** For individual elements, you can overwrite the `box-sizing:
border-box` rule with `box-sizing: content-box`. 

$$$

In all CSS rules that use padding, make sure to update the width and height.

For example, examine the `height` value change in this CSS rule from the Lunar
Resort theme's `_custom.scss` file.

Old way:

	#reserveBtn {
		background-color: #00C4FB;
		border-radius: 10px;
		color: #FFF;
		font-size: 1.5em;
		height: 62px;
		margin: 30px;
		padding: 10px 0;
		...
	}

New way:

	#reserveBtn {
		background-color: #00C4FB;
		border-radius: 10px;
		color: #FFF;
		font-size: 1.5em;
		height: 82px;
		margin: 30px;
		padding: 10px 0;
		...
	}


After updating your theme's CSS rules, you should update its CSS responsiveness. 

### Updating the Responsiveness [](id=updating-the-responsiveness)

In @product-ver@, Bootstrap 3 explicit media queries replace Bootstrap 2
`respond-to` mixins for CSS responsiveness. Follow these steps to update CSS
responsiveness:

1.  Open your `_custom.scss` file.

2.  Replace all `respond-to` mixins with corresponding media
    queries shown below:

    **Media Query Replacements**

    | Liferay Portal 6.2 Mixin                            |  &nbsp;@product-ver@ Media Query                                     |
    -------------------------------------- |:---------------------------------------------------------- |
    `@include respond-to(phone)`           | `@include media-query(null, $screen-xs-max)`               |
    `@include respond-to(tablet)`          | `@include media-query(sm, $screen-sm-max)`                 |
    `@include respond-to(phone, tablet)`   | `@include media-query(null, $breakpoint_tablet - 1)`       |
    `@include respond-to(desktop, tablet)` | `@include sm`                                              |
    `@include respond-to(desktop)`         | `@include media-query($breakpoint_tablet, null)`           |

For example, here is a responsiveness update to the Lunar Resort's
`_custom.scss` file:

Old:

    @include respond-to(phone, tablet) {
        html #wrapper #banner #navigation {
        ...
        }
        ...
    }

New:

    @include media-query(null, $breakpoint_tablet - 1) {
        html #wrapper #banner #navigation {
        ...
        }
        ...
    }

The new media query `@include media-query(null, $breakpoint_tablet -
1)` replaces the old mixin `@include respond-to(phone, tablet)`.

The Themes Generator's `upgrade` Gulp task generates a file
`_deprecated_mixins.scss`. The file provides deprecated compass mixins that your
migrated theme might be using. Consider upgrading your use of these mixins. Keep
the `_deprecated_mixins.scss` file if you're using any of its mixins, but delete
all unused mixins. If you're not using any of the mixins, delete the
`_deprecated_mixins.scss` file. 

You've updated the theme's responsiveness. Next, you'll update its Font Awesome
settings. 

### Updating Font Awesome Icons [](id=updating-font-awesome-icons)

@product@ uses Font Awesome icons extensively. For example, the Lunar Resort
theme's design incorporates Font Awesome icons in its social media links. 

![Figure 2: Font Awesome icons facilitate creating social media links.](../../../images/lunar-resort-social-media-links.png)

The icons are easy to use in themes too.

In Liferay Portal 6.2, the CSS file `aui.css`  defined the Font Awesome icon paths. In
@product-ver@, the Sass file `_aui_variables.scss` defines them.

+$$$

**Note:** In @product-ver@, the `aui.css` file holds the `lexicon-base` style
import. The [Theme Reference Guide](/develop/reference/-/knowledge_base/7-0/theme-reference-guide)
describes all the @product@ theme files.

$$$

The top of the `_aui_variables.scss` file must start with the Font Awesome Icons
imports. If you modified the `_aui_variables.scss` file in your Liferay Portal 6.2 theme, add
these Font Awesome imports to the top of it:

	// Icon paths
	$FontAwesomePath: "aui/lexicon/fonts/alloy-font-awesome/font";
	$font-awesome-path: "aui/lexicon/fonts/alloy-font-awesome/font";
	$icon-font-path: "aui/lexicon/fonts/";

Next, you'll update the theme templates.

## Updating Theme Templates [](id=updating-theme-templates)

@product-ver@ theme templates are essentially the same as Liferay Portal 6.2 theme
templates. Here are the main changes:

-   Velocity templates are now deprecated in favor of FreeMarker templates

-   The Dockbar has been replaced and reorganized into a set of three distinct
    menus.

Key reasons for using FreeMarker templates and deprecating Velocity templates
are these: 

-   FreeMarker is developed and maintained regularly, while Velocity is no longer
    actively being developed.
 
-   FreeMarker is faster and supports more sophisticated macros.

-   FreeMarker supports using taglibs directly rather than requiring a method 
    to represent them. You can pass body content to them, parameters, etc.

The menus that replace the Dockbar supports a more flexible and responsive
design for creating better user experiences. 

You should start by addressing the Velocity templates. Since Velocity
templates have been deprecated and aren't compatible with @product-ver@, **you
must convert your Velocity theme templates to FreeMarker**.

If you're using the Themes Generator, the `gulp upgrade` command reports the
required theme template changes in the log. 

For example, here is the command's output for the Lunar Resort theme:

    ----------------------------------------------------------------
    Liferay Upgrade (6.2 to 7)
    ----------------------------------------------------------------

    File: portal_normal.ftl
        Warning: <@liferay.dockbar /> is deprecated, replace with
        <@liferay.control_menu /> for new admin controls.
        Warning: not all admin controls will be visible without
        <@liferay.control_menu />
        Warning: ${theme} variable is no longer available in FreeMarker
        templates, see https://goo.gl/9fXzYt for more information.

For all the theme's templates, it suggests replacement code for deprecated code. 

Next, you'll learn how to update various theme templates to @product-ver@. If you
didn't modify any theme templates, you can skip these sections.

### Updating Portal Normal FTL [](id=updating-portal-normal-ftl)

The first one to update is the `portal_normal.ftl` theme template. If you didn't
customize `portal_normal.ftl`, you can skip this section.

In FreeMarker templates, the new syntax for including taglibs lets you use them
directly rather than accessing them via the `theme` variable. The change is
described in the [Breaking Changes](/develop/reference/-/knowledge_base/7-0/breaking-changes#taglibs-are-no-longer-accessible-via-the-theme-variable-in-freemarker)
reference document. All modified `portal_normal.ftl` theme templates must be
updated to use the new syntax. 

1.  Open your modified `portal_normal.ftl` file and replace the following 6.2
    directives with the corresponding 7.0 directives:

    **FreeMarker Theme Variable Replacements**

      6.2                                |  &nbsp;7.0                                                                                                                     |
    ------------------------------------ |:------------------------------------------------------------------------------------------------------------------------------ |
    `${theme.include(top_head_include)}`                   | `<@liferay_util["include"] page=top_head_include />`                                                         |
    `${theme.include(body_top_include)}`                   | `<@liferay_util["include"] page=body_top_include />`                                                         |
    `${theme.include(content_include)}`                    | `<@liferay_util["include"] page=content_include />`                                                          |
    `${theme.wrapPortlet("portlet.ftl", content_include)}` | `<@liferay_theme["wrap-portlet"] page="portlet.ftl"> <@liferay_util["include"] page=content_include /> </@>` |
    `${theme.include(body_bottom_include)}`                | `<@liferay_util["include"] page=body_bottom_include />`                                                      |
    `${theme.include(bottom_include)}`                     | `<@liferay_util["include"] page=bottom_include />`                                                           |

2.  Replace the following link type.

    Old:

    `<a href="#main-content" id="skip-to-content"><@liferay.language key="skip-to-content" /></a>`
 
    New:

    `<@liferay_ui["quick-access"] contentId="#main-content" />`.

    The `liferay-ui:quick-access` tag provides a keyboard shortcut to the page's
    main content.

3.  Replace all Dockbar references with Control Menu references.

    Old:

        <#if is_signed_in>
                <@liferay.dockbar />
        </#if>

    New:

        <@liferay.control_menu />

    The Dockbar was an all-in-one component that contained the page 
    administration menus and the user/portal administration menus. This UI has
    since been split and reorganized into three menus:

    -  *The Product Menu*: Manage site and page navigation, content, settings 
       and pages for the current site, and navigate to user account settings, 
       etc.

    -  *The Control Menu*: Configure and add content to the page and view the
        page in a simulation window. 
    
    -  *The User Personal Bar*: Display notifications and the user's avatar and
        name. 

    ![Figure 3: The Dockbar was removed in @product-ver@ and must be replaced with the new Control Menu.](../../../images/upgrading-themes-dockbar.png)

    The new design enhances the user experience by providing clear and
    purposeful menus. 

4.  If you used the split Dockbar in your Liferay Portal 6.2 theme, remove `dockbar-split` from
    the `body` element's `class` value.

    For example, remove `dockbar-split` from `<body class="... dockbar-split">`.
    .

5.  Remove the page title code shown below:

        <h2 class="page-title">
            <span>${the_title}</span>
        </h2>
 
    Rather than include the page title on every page, it was decided that this
    decision should be left up to developers. With the introduction of
    modularization in themes, this feature can easily be implemented however you
    like.

6.  To ensure navigation is only rendered when there are pages, wrap the
    `<#include "${full_templates_path}/navigation.ftl" />` include with an `if`
    statement as demonstrated below:

        <#if has_navigation && is_setup_complete>
            <#include "${full_templates_path}/navigation.ftl" />
        </#if>

7.  Finally, replace content `div` elements (e.g., `<div
    id="content">...<div>`)  with HTML 5 `section` elements. 

    The `div` element works but the `section` element is more accurate and
    provides better accessibility for screen readers.
    
    For example, here's a new content `section` element:

        <section id="content">
            <h1 class="hide-accessible">${the_title}</h1>
            ...
        </section>

    To support accessibility, consider adding an `h1` element like the one
    above. 

+$$$

**Note**: The `gulp upgrade` command for Themes Generator themes reports suggested
theme template changes. 

$$$

If you modified the navigation template for your theme, follow the steps in the
next section. 

### Updating Navigation FTL [](id=updating-navigation-ftl)

Follow these steps to update your modified `navigation.ftl` file:

1.  Below the `<nav class="${nav_css_class}" id="navigation" role="navigation">`
    element, add the following hidden heading for accessibility screen readers:

        <h1 class="hide-accessible">
            <@liferay.language key="navigation" />
        </h1>

2.  To access the layout, add the following variable declaration below the
    `<#assign nav_item_css_class = "" />` variable declaration:

        <#assign nav_item_layout = nav_item.getLayout() />

    This variable grabs the layout for navigation. You can use
    this variable to retrieve an icon for the navigation menu next.

3.  To retrieve an icon for the navigation menu, replace the `${nav_item.icon()}`
    variable in the
    `<a aria-labelledby="layout_${nav_item.getLayoutId()}"...</a>` anchor with
    the following element: 

        <@liferay_theme["layout-icon"] layout=nav_item_layout />

The navigation template is updated.

That covers most, if not all, of the required theme template changes.
If you modified any other  FreeMarker theme templates, you
can compare them with templates in the [`_unstyled` theme](https://github.com/liferay/liferay-portal/tree/7.0.x/modules/apps/foundation/frontend-theme/frontend-theme-unstyled/src/main/resources/META-INF/resources/_unstyled/templates).
And if your theme uses the Themes Generator, refer to the suggested
changes that the `gulp upgrade` command reports. 

After updating the theme templates, you can update your theme's resources
importer code.

## Updating the Resources Importer [](id=updating-the-resources-importer)

Liferay's resources importer is now an OSGi module in Liferay's Web Experience
application suite. Since the suite is bundled with @product@, developers no
longer need to download the resources importer separately.

API changes and upgrades to Bootstrap 3 affect the following resources importer
components:

- Plugin properties
- Web content article files and directory structure
- Sitemap

This section shows you how to update these components.

+$$$

**Note:** The example Lunar Resort theme's resources importer web content
articles have been modified to avoid known issue [LPS-64859](https://issues.liferay.com/browse/LPS-64859).
Articles in the Liferay Portal 6.2 theme link to pages in the site's layout. Due to the page
and article import order, the links cause a null pointer exception. To avoid
this issue with the example theme, the offending links have been removed from
its articles.

$$$

Start updating the plugin properties for the resources importer.

### Updating liferay-plugin-package.properties [](id=updating-liferay-plugin-package-properties)

If you're upgrading a Plugins SDK theme, follow these instructions to update
resources importer properties. Otherwise, skip this section.

Make the following updates to the theme's `liferay-plugin-package.properties`
file:

1.  Remove the `required-deployment-contexts` property.

    The plugin no longer needs this property as the resources importer is now an
    [OSGi module](https://github.com/liferay/liferay-portal/tree/master/modules/apps/web-experience/export-import/export-import-resources-importer)
    built-in and deployed with @product-ver@.

2.  Since the group model class's fully-qualified class name has changed, replace the
    `resources-importer-target-class-name` property's value with the
    following class name:

        com.liferay.portal.kernel.model.Group

Now that the resources importer's properties are configured properly, you can
update your theme's web content.

### Updating Web Content [](id=updating-web-content)

All @product-ver@ web content articles must be written in XML and have a structure
and template. Article creation requires a structure and article content
rendering requires a template. Follow these steps to update your web content:

1.  In the `/resources-importer/journal/articles/` folder, create a
    subfolder, for example `BASIC_WEB_CONTENT`, to hold the basic HTML articles.

2.  Move all basic HTML articles into the folder you just created in step 1.

3.  In the `/resources-importer/journal/templates/` and
    `/resources-importer/journal/structures/` folders, create a subfolder with
    the same name as the folder you created in step 1.

    For the web content to work properly, the articles, structure, and template
    folder names must match.

4.  In previous Liferay versions, article structures were written in XML. Now
    they're written in JSON. 

    Create a file `[structure-name].json`, for example `BASIC_WEB_CONTENT.json`,
    in the structure subfolder you created in the previous step.

    For web content articles that use complicated structures and templates,
    create the structures and templates in @product@. 

5.  In the JSON file you just created, add a JSON structure for the web content.
    For example, you can use a JSON structure like the one below for basic web
    content articles.

        {
            "availableLanguageIds": [
                "en_US"
            ],
            "defaultLanguageId": "en_US",
            "fields": [
                {
                    "label": {
                        "en_US": "Content"
                    },
                    "predefinedValue": {
                        "en_US": ""
                    },
                    "style": {
                        "en_US": ""
                    },
                    "tip": {
                        "en_US": ""
                    },
                    "dataType": "html",
                    "fieldNamespace": "ddm",
                    "indexType": "keyword",
                    "localizable": true,
                    "name": "content",
                    "readOnly": false,
                    "repeatable": false,
                    "required": false,
                    "showLabel": true,
                    "type": "ddm-text-html"
                }
            ]
        }

    This structure identifies the articles' language and field settings and
    specifies a `name` value to identify the content. This example structure's
    content is named `content`.

5.  In the template subfolder you created in step 3 (e.g.,
    `/resources-importer/journal/templates/[template-folder-name]/`), create a
    FreeMarker template file (e.g., `[template-folder-name].ftl`) and add a
    method in it to get the article's data.

    For example, this method accesses content from the variable named `content`
    and renders it as HTML:

        ${content.getData()}

    You've created the basic web content structure and template.

6.  Follow this pattern for basic web content articles you convert from HTML to
    XML: 

        <?xml version="1.0"?>

        <root available-locales="en_US" default-locale="en_US">
                <dynamic-element name="content" type="text_area"
                index-type="keyword" index="0">
                        <dynamic-content language-id="en_US">
                                <![CDATA[
                                HTML CONTENT GOES HERE
                                ]]>
                        </dynamic-content>
                </dynamic-element>
        </root>

    For example, the `2 column description.html` Lunar Resort article's HTML
    content should be converted to an XML file (e.g., `2 column
    description.xml`) whose content looks like this:

        <?xml version="1.0"?>

        <root available-locales="en_US" default-locale="en_US">
            <dynamic-element name="content" type="text_area"
            index-type="keyword" index="0">
                <dynamic-content language-id="en_US">
                    <![CDATA[
                        <div class="container-fluid">
            <div class="span4" id="columnLeft">Out of This World</div>
            <div class="span8" id="columnRight">Come to the Lunar Resort and
            live out your childhood dream of being an astronaut on the Moon. If
            that's not enough incentive, you'll enjoy a luxurious 3 day 2 night
            stay in our fabulous Lunar accommodations. Enjoy a round of Lunar
            Golf on our one of a kind course. Have a blast on our Rover Racing
            track. Make your reservation now. The rest of your life starts
            today!</div>
        </div>
        ]]>
                </dynamic-content>
            </dynamic-element>
        </root>

7.  @product-ver@'s migration from Bootstrap 2 to Bootstrap 3 requires that you
    replace all `div` element `class` attribute values of Bootstrap 2 format 
    `span[number]` with values that use the Bootstrap 3 format:

        col-[device-size]-[number]

    `device-size` can be `xs`, `sm`, `md`, or `lg`. `md` works for most cases.
    Bootstrap's site at
    [http://getbootstrap.com/css/#grid](http://getbootstrap.com/css/#grid)
    explains the Bootstrap 3 grid system

    Continuing with the `2 column description.xml` article example, here is its
    updated content:

        <?xml version="1.0"?>

        <root available-locales="en_US" default-locale="en_US">
            <dynamic-element name="content" type="text_area"
            index-type="keyword" index="0">
                <dynamic-content language-id="en_US">
                    <![CDATA[
                        <div class="container-fluid">
            <div class="col-md-4" id="columnLeft">Out of This World</div>
            <div class="col-md-8" id="columnRight">Come to the Lunar Resort and
            live out your childhood dream of being an astronaut on the Moon. If
            that's not enough incentive, you'll enjoy a luxurious 3 day 2 night
            stay in our fabulous Lunar accommodations. Enjoy a round of Lunar
            Golf on our one of a kind course. Have a blast on our Rover Racing
            track. Make your reservation now. The rest of your life starts
            today!</div>
        </div>
        ]]>
                </dynamic-content>
            </dynamic-element>
        </root>

That's all that is needed for most basic web content articles. If you're
following along with the Lunar Resort example, the updated XML articles are in
the ZIP file's `/resources-importer/journal/articles/Basic Web Content/` folder.

+$$$

**Note:** Although Liferay Portal 6.2 used AlloyUI 2.0.x, @product-ver@ uses AlloyUI 3.0.x.
As a result, you may need to update your code that uses AlloyUI. Refer to
AlloyUI's [examples](http://alloyui.com/examples/) and [API docs](http://alloyui.com/api/)
for details. 

$$$

Next, you must update your resources importer's sitemap file.

### Updating the Sitemap [](id=updating-the-sitemap)

In Liferay Portal 6.2, portlet IDs were incremental numbers. In @product-ver@, they're
explicit class names. The new IDs are intuitive and unique. But you must update
your `sitemap.json` file with the new portlet IDs.

Some of common portlet IDs are specified in the `sitemap.json` example in the [Importing Resources with a Theme](/develop/tutorials/-/knowledge_base/7-0/importing-resources-with-a-theme)
tutorial.

You can also retrieve a portlet's ID from the UI:

1.  In the portlet's *Options* menu, select *Look and Feel Configuration*.
 
    ![Figure 4: You can find the portlet ID in the the *Look and Feel Configuration* menu.](../../../images/upgrading-themes-look-and-feel-menu.png)

2.  Select the *Advanced Styling* tab.

    The `Portlet ID` value is listed in the blue box.

![Figure 5: The portlet ID is listed within the blue box in the *Advanced Styling* tab.](../../../images/upgrading-themes-portlet-id.png)

The [Portlet ID Quick Reference Guide](https://dev.liferay.com/participate/liferaypedia/-/wiki/Main/Portlet+ID+Quick+Reference+Guide)
lists all the default portlet IDs.

Next, you can learn how to update your theme's UI to follow Lexicon design
patterns.

## Applying Lexicon UI Design Patterns [](id=applying-lexicon-ui-design-patterns)

@product-ver@ uses a design language called [Lexicon](http://liferay.github.io/lexicon/).
It provides styling guidelines and best practices for application UIs. Lexicon's
HTML and JavaScript components enable developers to build fully-realized UIs
quickly and effectively. This section demonstrates how to apply Lexicon to a
form. 

For example, this is the Liferay Portal 6.2 Lunar Resort's reservation form:

    <p>
    Thanks for choosing to stay at the Liferay Lunar Resort! Please fill out the
    form below to book your stay. We know you have a choice in where to stay on
    the
    Moon... oh wait no you don't. Thanks for picking us anyways. We'll see you
    soon on the Moon!
    </p>

    <form class="form-horizontal">
            <fieldset>
              <legend>Reservation Form</legend>
              <div class="control-group">
                  <label class="control-label" for="inputName">Name</label>
                  <div class="controls">
                          <input type="text" id="inputName"
                          placeholder="Enter your Name here" required="required">
                      </div>
              </div>
              <div class="control-group">
                  <label class="control-label" for="inputEmail">Email</label>
                  <div class="controls">
                      <input type="email" id="inputEmail"
                      placeholder="Enter your E-Mail here" required="required">
                  </div>
              </div>
              <div class="control-group">
                  <div class="controls">
                      <button type="submit" class="btn">Submit</button>
                  </div>
              </div>
            </fieldset>
    </form>

    <p style="padding-bottom:25px;">
    Thanks again for booking with Liferay. When you book with Liferay, you
    remember your stay. Please take a moment to fill out our guestbook below.
    </p>

The HTML code above uses Bootstrap 2's design language.

Lexicon extends Bootstrap 3. Here's the Lunar Resort form updated to Lexicon:

    <?xml version="1.0"?>

    <root available-locales="en_US" default-locale="en_US">
            <dynamic-element name="content" type="text_area"
            index-type="keyword" index="0">
                    <dynamic-content language-id="en_US">
                            <![CDATA[
                                    <p>Thanks for choosing to stay at the
                                    Liferay Lunar Resort! Please fill out the
                                    form below to book your stay. We know you
                                    have a choice in where to stay on the Moon...
                                    oh wait no you don't. Thanks for picking us
                                    anyways. We'll see you soon on the Moon!</p>
    <form role="form-horizontal">
            <fieldset>
              <legend>Reservation Form</legend>
              <div class="form-group">
                  <label for="inputName">Name</label>
                  <input type="text" id="inputName" class="form-control"
                  placeholder="Enter your Name here" required="required">
              </div>
              <div class="form-group">
                  <label for="inputEmail">Email</label>
                  <input type="email" id="inputEmail" class="form-control"
                  placeholder="Enter your E-Mail here" required="required">
              </div>
              <div class="form-group">
                      <button type="submit" class="btn btn-primary">Submit
                      </button>
              </div>
            </fieldset>
    </form>

    <p style="padding-bottom:25px;">Thanks again for booking with Liferay. When
    you book with Liferay, you remember your stay. Please take a moment to fill
    out our guestbook below.</p>
                            ]]>
                    </dynamic-content>
            </dynamic-element>
    </root>

The Lexicon updates applied to the form are as follows:

-   The `control-group` classes were updated to `form-group` classes.
-   The `control-label` classes were removed from the `label` elements.
-   The `<div class=""controls>` elements were removed.
-   The `form-control` class was added to each `input` element.
-   To emphasize the form's submit button, the `btn-primary` class was added to
    it. 

You can apply similar Lexicon design patterns to your theme's HTML files.

You've updated your theme to @product-ver@! You can deploy it from your theme
project.

Themes Generator-based project:

    gulp deploy

Plugins SDK project:

    ant deploy

Now your users can continue enjoying the visual styles you've created in your
upgraded themes. 

## Related Topics [](id=related-topics)

[Themes Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator)

[Migrating a theme to @product-ver@](/develop/tutorials/-/knowledge_base/7-0/migrating-a-6-2-theme-to-liferay-7)

[Upgrading to @product-ver@] (/discover/deployment/-/knowledge_base/7-0/upgrading-to-liferay-7)
