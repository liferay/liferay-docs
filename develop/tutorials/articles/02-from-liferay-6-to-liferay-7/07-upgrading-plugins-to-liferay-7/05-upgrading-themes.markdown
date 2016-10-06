# Upgrading Themes [](id=upgrading-themes)

You've developed your theme in Liferay 6.2, and now you've upgraded to Liferay 7.
Upgrading your theme to Liferay 7 is easier than you may think. With just a few
adjustments, your theme will be running on Liferay 7 in no time!

Upgrading a theme involves these steps:

-  Updating the Liferay version
-  Updating the CSS
-  Updating the theme templates
-  Updating resources importer configuration and content
-  Updating the UIs to follow Lexicon guidelines

This tutorial demonstrates how to upgrade a Liferay 6.2 theme to Liferay 7,
using the Lunar Resort theme as an example. The Lunar Resort theme was developed
in the Liferay 6.2 Learning Path [Developing a Liferay Theme](/develop/learning-paths/themes/-/knowledge_base/6-2/developing-a-liferay-theme).
It's similar to many Liferay 6 themes. It extends the [_styled](https://github.com/liferay/liferay-portal/tree/6.2.x/portal-web/docroot/html/themes/_styled)
base theme, adding configurable theme settings and incorporating a responsive
design that leverages Font Awesome icons and Bootstrap. Its source code is
available in ZIP file [lunar-resort-theme-migration-6.2.zip](/develop/tutorials/code/upgrading-themes/lunar-resort-theme-migration-6.2.zip).

![Figure 1: The Lunar Resort theme offers a clean, minimal design.](../../../images/finished-theme.png)

Before upgrading your theme, consider whether to [migrate your theme](/develop/tutorials/-/knowledge_base/7-0/migrating-a-6-2-theme-to-liferay-7)
to use the [Themes Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator).
Migration isn't required to upgrade a theme for Liferay 7, but the Themes
Generator's upgrade Gulp task performs many upgrade steps automatically. In
addition, a Themes Generator theme can leverage new features, such as [Themelets](/develop/tutorials/-/knowledge_base/7-0/themelets).

Upgrading a migrated theme with the Gulp task is discussed first, because it's a
powerful and popular option. After that, all of the upgrade steps (including
those the Gulp task executes) are explained. You must understand all the upgrade
steps to completely upgrade your theme for Liferay 7.

## Running the Gulp Upgrade Task for Themes Generator Themes [](id=running-the-gulp-upgrade-task-for-themes-generator-themes)

A Liferay 6 theme can be upgraded to Liferay 7, regardless of its project
environment (Themes Generator, Plugins SDK, Maven, etc.). However, a theme
that's been migrated to the Themes Generator can leverage the theme upgrade Gulp
task. Here's what the Gulp Task does:

- Updates the theme's Liferay version
- Updates the CSS
- Suggests specific updates to the code

The Gulp task makes a best effort to upgrade CSS code that is easy to identify
and straightforward to update. It suggests modifications to parts of the
remaining CSS code. 

Here are the steps for using the theme upgrade Gulp task:

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

    The task proceeds with the CSS file upgrade process, prompting you to update
    CSS file names. 

2.  For Liferay 7, Sass files should use the `.scss` extension and filenames for
    Sass partials should start with an underscore (e.g., `_custom.scss`). The
    `gulp upgrade` task prompts you for each CSS file to rename.

The Gulp task makes a best effort to upgrade the theme's Bootstrap code from
version 2 to 3. For other areas of the code it suspects might need updating, it
logs suggestions (covered later). The task also points out breaking changes that
may affect theme templates. 

A *breaking change* is a code modification between versions of @product@ that 
might be incompatible with existing plugins, including themes. Liferay kept such 
changes to a minimum, but they were unavoidable in some cases. The 
[Breaking Changes](/develop/reference/-/knowledge_base/7-0/breaking-changes)
reference document explains all of them. The theme upgrade Gulp task and the 
[Code Upgrade Tool](/develop/tutorials/-/knowledge_base/7-0/adapting-to-liferay-7s-api-with-the-code-upgrade-tool),
in Liferay IDE and Liferay Developer Studio, identify and address these changes.

The `gulp upgrade` task jumpstarts the upgrade process, but it doesn't complete
it. Manual updates are required. 

The remainder of this tutorial explains all the theme upgrade steps, regardless
of whether the `gulp upgrade` task performs them. Steps the upgrade task
performs are noted in context. Even if you've already executed the Gulp task,
it's best to learn all the steps and make sure they're applied to your theme.

Updating the theme's Liferay version is a good first step. 

## Updating a Theme's Liferay Version [](id=updating-a-themes-liferay-version)

A theme's Liferay version must be updated to `7.0.0+`, for it to run on Liferay
7.

1.  Open your `liferay-plugin-package.properties` file and change the
    `liferay-versions` property value to `7.0.0+`:

        liferay-versions=7.0.0+

2.  Open your `liferay-look-and-feel.xml` file. Specify
    `liferay-look-and-feel_7_0_0.dtd` as its DTD and `7.0.0+` as its
    compatibility version. Your XML code should resemble this:

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

Your theme's Liferay version references are updated for Liferay 7. Next, you'll
update the CSS.

## Updating the CSS [](id=updating-the-css)

As a result of Liferay 7's UI improvements, some CSS files have been removed and
others added. The directory structure has changed, as well. Liferay 7's update
from Bootstrap 2 to Bootstrap 3 requires CSS classes variable changes. Lastly,
the CSS responsiveness tokens have changed. Conforming to all these changes is
part of the upgrade process.

In this section, you'll update your theme's CSS so you can enjoy the UI 
benefits. You can start with updating CSS file names for Sass.

### Updating CSS File Names for Sass [](id=updating-css-file-names-for-sass)

Although Sass was available in Liferay 6.2, only Sass partial files followed the 
Sass naming convention of `.scss`. Liferay 7 expects themes to use this naming 
convention for all Sass files. Therefore, some of the CSS files must be renamed.

+$$$

**Note:** The `gulp upgrade` task renames the files automatically.

$$$

For each CSS file you've modified from the base theme, except `main.scss` and
`aui.scss`, change its suffix from `.css` to `.scss`. 

Then, prepend an underscore (`_`) to the name of all Sass partial files. 

For example, rename `custom.css` to `_custom.scss`.

Refer to the [Theme Reference Guide](/develop/reference/-/knowledge_base/7-0/theme-reference-guide)
for a complete list of expected names for theme CSS files. 

Here are the Lunar Resort theme's modified CSS files with updated names:

    - `css/`
        - `_aui_variables.scss`
        - `_custom.scss`

Next, the CSS rules must be updated to use Bootstrap 3 syntax.

### Updating CSS Rules [](id=updating-css-rules)

Liferay 7 uses Bootstrap 3's syntax for CSS rules. This gives you access to all
of Bootstrap 3's great features and improvements.

If your theme does not use the Themes Generator, you can refer to the
[Migrating from 2.x to 3.0 guide](http://getbootstrap.com/migration/#migrating-from-2x-to-30)
for updating CSS rules to Bootstrap 3. 

If your theme uses the Themes Generator and you executed the `gulp upgrade`
task, its log reported CSS changes it made and changes for you to consider
making manually. For example, here is part of the `gulp upgrade` task log
for the Lunar Resort theme:

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

For each update performed and suggested, the Gulp task reports a file name and
line number range. 

Since Bootstrap 3 adopts the `box-sizing: border-box` property for all elements
and pseudo-elements (e.g., `:before` and `:after`), padding no longer affects
dimensions. [Bootstrap's documentation](http://getbootstrap.com/css/#less-mixins-box-sizing)
describes the details. The Gulp upgrade task points out CSS rules that use
padding, to suggest updates. Consider the updates it suggests. 

+$$$

**Note:** You can overwrite the `box-sizing: border-box` rule for
individual elements, by setting the rule to `box-sizing: content-box`.

$$$

Make sure to update the width and height in all CSS rules that use padding.

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


After you've updated the theme's CSS rules, you should update its CSS
responsiveness. 

### Updating the Responsiveness [](id=updating-the-responsiveness)

The Bootstrap 2 `respond-to` mixins that were used in Liferay 6.2 have been
replaced with explicit Bootstrap 3 media queries. Follow these steps to update
CSS responsiveness:

1.  Open your `_custom.scss` file.

2.  Locate all `respond-to` mixins and replace them with the matching media
    queries shown below:

    **Media Query Replacements**

      6.2 Mixin     |  &nbsp;7.0 Media Query |
    ---------- |:-------------- |
    `@include respond-to(phone)` | `@include media-query(null, $screen-xs-max)` |
    `@include respond-to(tablet)` | `@include media-query(sm, $screen-sm-max)` |
    `@include respond-to(phone, tablet)` | `@include media-query(null, $breakpoint_tablet - 1)` |
    `@include respond-to(desktop, tablet)` | `@include sm` |
    `@include respond-to(desktop)` | `@include media-query($breakpoint_tablet, null)` |

For example, here is an update to the Lunar Resort's `_custom.scss` file.

Old way:

    @include respond-to(phone, tablet) {
        html #wrapper #banner #navigation {
        ...
        }
        ...
    }

New way:

    @include media-query(null, $breakpoint_tablet - 1) {
        html #wrapper #banner #navigation {
        ...
        }
        ...
    }

The old mixin `@include respond-to(phone, tablet)` is replaced with the new
media query `@include media-query(null, $breakpoint_tablet - 1)`.

If your theme uses the Themes Generator and you executed the Gulp upgrade task,
the task generated a file `_deprecated_mixins.scss`. It provides deprecated
compass mixins that your migrated theme might be using. Consider upgrading your
use of these mixins. Keep the `_deprecated_mixins.scss` file if you're using any
of its mixins and delete from it all mixins you're not using. If you're not
using any of of its mixins, delete the `_deprecated_mixins.scss` file. 

Your theme's responsiveness is up to date. Next, you'll update your Font Awesome
settings. 

### Updating Font Awesome Icons [](id=updating-font-awesome-icons)

Font Awesome icons are widely used throughout @product@ and are easy to include 
in themes. For example, the Lunar Resort theme's design incorporates Font 
Awesome icons in its social media links. 

![Figure 2: Font Awesome icons facilitate creating social media links for your site.](../../../images/lunar-resort-social-media-links.png)

In Liferay 6.2, the Font Awesome icon paths were defined in the `aui.css`
file. In Liferay 7, they're defined in the `_aui_variables.scss` Sass CSS file.

+$$$

**Note:** In Liferay 7, the `aui.css` file holds the `lexicon-base` import for styling.
The [Theme Reference Guide](/develop/reference/-/knowledge_base/7-0/theme-reference-guide)
describes all the Liferay 7 theme files and their usage.

$$$

The top of the `_aui_variables.scss` file must start with the Font Awesome Icons
imports. 

If you modified the `_aui_variables.scss` file in your 6.2 theme, add these Font
Awesome imports to the top of it: 

	// Icon paths
	$FontAwesomePath: "aui/lexicon/fonts/alloy-font-awesome/font";
	$font-awesome-path: "aui/lexicon/fonts/alloy-font-awesome/font";
	$icon-font-path: "aui/lexicon/fonts/";

In the next section, you'll address breaking changes for theme templates.

## Updating Theme Templates [](id=updating-theme-templates)

Theme templates are essentially the same as they were in Liferay 6.2. The most
notable change is the deprecation of Velocity templates in favor of FreeMarker
templates. This was done for a few key reasons:

-  Velocity is no longer actively being developed, while FreeMarker is 
   maintained and kept up-to-date for users.
   
-  FreeMarker is faster and allows for the use of more sophisticated macros.

-  FreeMarker supports using taglibs directly rather than requiring a method 
   to represent them, meaning you can pass body content to them, pass parameters, 
   etc.

The Dockbar has been replaced and reorganized into a set of three distinct
menus, allowing for a more flexible and responsive design. This has resulted in 
some breaking changes that affect your theme templates. Although these changes 
have been made, the result is a well-designed user experience.

Since Velocity templates have been deprecated and are not compatible with 
Liferay 7, **you need to convert your Velocity theme templates into FreeMarker**.

If you're using the Themes Generator, the theme template breaking changes are
listed for you in the `gulp upgrade` log.

For example, here is the upgrade output from the `gulp upgrade` task for the
Lunar Resort theme:

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

This output points out some of the breaking changes in Liferay 7 that effect
theme templates. In this case, the changes only apply to `portal_normal.ftl`. If
you modified more theme templates for your theme, the corresponding breaking
changes would be listed in this log as well.

In this section, you'll update your theme templates to reflect the breaking
changes in Liferay 7. If you did not modify any of the theme templates and just
modified the CSS files for styling, you can skip this section.

### Updating Portal Normal FTL [](id=updating-portal-normal-ftl)

Follow these steps to update your `portal_normal.ftl` theme template. If you did
not modify `portal_normal.ftl` in your Liferay 6.2 theme, you can skip these
steps.

The syntax to include taglibs in theme templates has been updated. This does
mean however that taglibs are no longer accessible via the `${theme}` variable 
in FreeMarker templates, so these directives must be updated. Not to worry 
though, you can still use all the same taglibs in your theme templates. What's 
more, the new syntax is more intuitive, allowing you to use the taglib directly, 
rather than using a method to include content in your themes.

This was indicated in the example log output shown in the intro to this section. 
You can read more about this breaking change in the [Breaking Changes](/develop/reference/-/knowledge_base/7-0/breaking-changes#taglibs-are-no-longer-accessible-via-the-theme-variable-in-freemarker)
reference doc. You must update these directives with the new syntax.

1.  Open your modified `portal_normal.ftl` file and replace these directives
    with the matching ones shown below:

    **FreeMarker Theme Variable Replacements**

      6.2      |  &nbsp;7.0     |
    ---------- |:-------------- |
    `${theme.include(top_head_include)}` | `<@liferay_util["include"] page=top_head_include />` |
    `${theme.include(body_top_include)}` | `<@liferay_util["include"] page=body_top_include />` |
    `${theme.include(content_include)}` | `<@liferay_util["include"] page=content_include />` |
    `${theme.wrapPortlet("portlet.ftl", content_include)}` | `<@liferay_theme["wrap-portlet"] page="portlet.ftl"> <@liferay_util["include"] page=content_include /> </@>` |
    `${theme.include(body_bottom_include)}` | `<@liferay_util["include"] page=body_bottom_include />` |
    `${theme.include(bottom_include)}` | `<@liferay_util["include"] page=bottom_include />` |

2.  Find the link `<a href="#main-content" id="skip-to-content">
    <@liferay.language key="skip-to-content" /></a>` and replace it with
    `<@liferay_ui["quick-access"] contentId="#main-content" />`.
    
    This uses the new syntax, harnessing the `liferay-ui:quick-access` taglib to 
    provide a shortcut to allow keyboard users to jump straight to the main
    content of the page.

3.  You must update all references to the Dockbar in your template.

    Replace the `<@liferay.dockbar />` directive and wrapping `<#if>` statements
    with the `<@liferay.control_menu />` directive:

        Before:

        <#if is_signed_in>
                <@liferay.dockbar />
        </#if>

        After:

        <@liferay.control_menu />
       
    The Dockbar was an all-in-one component that contained the page 
    administration menus and the user/portal administration menus. This UI has
    since been split and reorganized into three menus:

    -  *The Product Menu* - Manage site and page navigation, content, settings 
       and pages for the current site, and navigate to user account settings, 
       etc..

    -  *The Control Menu* - Add content to the page, configure the page, and 
       view the page in the simulation window.
    
    -  *The User Personal Bar* - Displays the user's avatar, name, and 
       notifications.
    
    This new design provides clear and purposeful menus that create a good
    user-experience.

4.  If you used the split Dockbar in your 6.2 theme, you must update your `body`
    class as well, otherwise you can skip to step 5.

    As a reminder, here is what the split Dockbar looks like:

    ![Figure 3: The Dockbar was removed in Liferay 7 and needs removed from your code](../../../images/upgrading-themes-dockbar.png)

    Find the `<body class="... dockbar-split">` element and remove
    `dockbar-split` from its `class` value.

5.  Remove the page title code shown below:

        <h2 class="page-title">
                <span>${the_title}</span>
        </h2>
        
    Rather than include the page title on every page, it was decided that this
    decision should be left up to developers. With the introduction of
    modularization in themes, this feature can easily be implemented however you
    like.

6.  Wrap the `<#include "${full_templates_path}/navigation.ftl" />` include with
    these if statements:

        <#if has_navigation && is_setup_complete>
            <#include "${full_templates_path}/navigation.ftl" />
        </#if>

    This ensures that the navigation is not rendered unless there are pages.

7.  Finally, replace the content `<div>` with the HTML5 `<section>` syntax.

    Note that the `<div>` element would still work, however the `<section>`
    element is more accurate and will better assist accessibility for screen 
    readers.
    
    Find the `<div id="content">` element and replace it with the `<section>` 
    element shown below:

        <section id="content">
            <h1 class="hide-accessible">${the_title}</h1>
            ...
        </section>

    A `<h1>` element was added as well for accessibility, to match the updated
    portal normal template.

If you modified your navigation template for your theme, follow the steps in the
next section. As a reminder, if you're using the Themes Generator, any breaking
changes for your navigation template are printed in the `gulp upgrade` log.

### Updating Navigation FTL [](id=updating-navigation-ftl)

Follow these steps to make the required changes to your `navigation.ftl` file:

1.  Add this heading below the
    `<nav class="${nav_css_class}" id="navigation" role="navigation">` element:

        <h1 class="hide-accessible">
                <@liferay.language key="navigation" />
        </h1>

    This creates a hidden heading that is accessible to screen readers.

2.  Add this variable declaration below the
    `<#assign nav_item_css_class = "" />` variable declaration:

        <#assign nav_item_layout = nav_item.getLayout() />

    This defines a variable that grabs the layout for navigation. You can use
    this variable to retrieve an icon for the navigation menu next.

3.  Find the `<a aria-labelledby="layout_${nav_item.getLayoutId()}"...</a>`
    anchor and replace the `${nav_item.icon()}` variable with the new syntax
    below:

        <@liferay_theme["layout-icon"] layout=nav_item_layout />

Your navigation template is up to date.

That should cover most, if not all, of the changes needed for your theme
templates. If you modified any other theme templates that were not covered, you
can compare your template to the FreeMarker templates for the [_unstyled](https://github.com/liferay/liferay-portal/tree/7.0.x/modules/apps/foundation/frontend-theme/frontend-theme-unstyled/src/main/resources/META-INF/resources/_unstyled/templates)
theme, or refer to the suggested changes listed in your Gulp upgrade log output
if you're using the Themes Generator.

Now that your theme templates are updated, you can update your resources
importer next.

## Updating the Resources Importer [](id=updating-the-resources-importer)

The resources importer is now OSGi compatible and has been integrated into 
Liferay. This means that you will not need to install a separate application to
use the resources importer!

The APIs have changed some, so you must update the following components in the 
resources importer:

-  Class names
-  Directory structure
-  Web content files

In this section, you'll make these required changes.

+$$$

**Note:** If you're using the example Lunar Resort theme, the resources importer
articles have been slightly modified due to a known issue [LPS-64859](https://issues.liferay.com/browse/LPS-64859).
The articles in the 6.2 Learning Path have links to pages in the layout of the
site. Due to the order in which the pages and articles are imported, a null
pointer exception occurs. To avoid this issue, these links have been removed
from the articles formerly imported by this theme.

$$$

Get started by updating the properties for the resources importer next.

### Updating liferay-plugin-package.properties [](id=updating-liferay-plugin-package-properties)

First off, update the `liferay-plugin-package.properties` file with the proper
settings:

1.  The `required-deployment-contexts` property is no longer required since the
    resources importer is now an [OSGI module](https://github.com/liferay/liferay-portal/tree/master/modules/apps/web-experience/export-import/export-import-resources-importer)
    that is included in Liferay 7.

    Open `liferay-plugin-package.properties` and remove the
    `required-deployment-contexts` property.

2.  The class name has changed for the model group as well. Change the
    `resources-importer-target-class-name` property's value to the
    new class path below:

        com.liferay.portal.kernel.model.Group

Now that the resources importer is configured properly, you can move on to
updating the web content for the importer next.

### Updating the Web Content [](id=updating-the-web-content)

All articles must now have a structure and a template, and be written in XML.
The structure and template provides the essentials for creating and rendering
article content. Follow the steps below to update your web content for Liferay 7:

1.  Open the `/resources-importer/journal/articles/` directory and create a
    folder, for example `BASIC_WEB_CONTENT`, to hold your articles.

2.  Move all of the basic HTML articles into the folder you just created in step
    1.

3.  Create a folder that matches the folder name you created in step 1 and add
    it to the `/resources-importer/journal/templates/` directory.

    It's important that the folder names match the template and structure names
    to work properly.

4.  In previous versions of Liferay, article structures were written in XML.
    Article structures must now be written in JSON.

    Create a `[structure-name].json` file in the
    `/resources-importer/journal/structures/` directory that matches the name of 
    the folder you created in step 1, for example `BASIC_WEB_CONTENT.json`.

    For more complicated web content that goes beyond the scope of a basic 
    structure and template, as covered in this example, you need to recreate the 
    structure and template in @product@.

5.  Add the JSON for your structure to the file that you just created in step 4.
    For basic web content articles you can use the JSON structure below:

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

    This structure simply identifies the language and field settings for the
    articles and specifies a `name` to identify the content. Next, you can
    create the matching template.

5.  Create a `[template-folder-name].ftl` template file in the
    `/resources-importer/journal/templates/[template-folder-name]/` folder you 
    created in step 3 and add this method:

        ${content.getData()}

    This accesses the article's content by the `name` defined in the structure,
    `content` in this case, and renders the HTML data. You have created the
    basic web content structure and template.

6.  All articles must now be written in XML. You can use this pattern for basic
    web content articles:

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

    For example, the `2 column description.html` Lunar Resort article is written
    this way when converted to a `2 column description.xml` file:

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

7.  In this example, the `span[number]` `<div>` classes, used to define the
    column widths, need updated as well. This is due to the migration from
    Bootstrap 2 to Bootstrap 3. In Bootstrap 3, these classes are replaced with
    the syntax shown below:

        col-[device-size]-[number]

    Update any columns you have to use the new syntax.

    `device-size` can be `xs`, `sm`, `md`, or `lg`. In most cases, `md` works.
    You can read more about the Bootstrap grid system on their site at
    [http://getbootstrap.com/css/#grid](http://getbootstrap.com/css/#grid).
    Continuing with the `2 column description.xml` example, the article would be
    updated with these `<div>` classes:

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

That's all that is needed for most of the basic web content articles. If you are
following along with the Lunar Resort example, the updated XML articles are in
the `/resources-importer/journal/articles/Basic Web Content/` directory of the 
`lunar-resort-theme-migration-6.2.zip` file you downloaded at the beginning.

+$$$

**Note:** Liferay 6.2 used AlloyUI 2.0.x, and has since upgraded to Alloy UI
3.0.x for Liferay 7. Due to this upgrade, you may need to update your syntax in
order for your code to work. Please refer to Alloy UI's [examples](http://alloyui.com/examples/)
and [API docs](http://alloyui.com/api/) as a guide.

$$$

Your next step is to update your sitemap for the resources importer.

### Updating the Sitemap [](id=updating-the-sitemap)

Due to the modularization of the portlets in Liferay 7, the portlet IDs have
changed. In Liferay 6.2, the portlet ID's were numbered, however in Liferay 7
the portlet IDs are the explicit class names of the module. You must change
these in your `sitemap.json` so the portlets render properly.

Some of the common portlet IDs are specified in the `sitemap.json` example of
the [Importing Resources with a Theme](/develop/tutorials/-/knowledge_base/7-0/importing-resources-with-a-theme)
tutorial.

You can also find the updated portlet ID through the 
*Look and Feel Configuration* menu for the portlet.
Follow these steps to retrieve your portlet ID:

1.  Open the *Options* menu for the portlet and select the 
    *Look and Feel Configuration* menu.
    
    ![Figure 4: You can find the portlet ID in the the *Look and Feel Configuration* menu.](../../../images/upgrading-themes-look-and-feel-menu.png)

2.  Click the *Advanced Styling* tab.

    The portlet ID is listed within the blue box, next to the `Portlet ID` 
    label, as shown in the figure below:

![Figure 5: The portlet ID is listed for you within the blue box of the *Advanced Styling* tab.](../../../images/upgrading-themes-portlet-id.png)
    
A full list of the default portlet IDs can also be found in the
[Portlet ID Quick Reference Guide](https://dev.liferay.com/participate/liferaypedia/-/wiki/Main/Portlet+ID+Quick+Reference+Guide)

Next, you can learn how to update your theme's UIs to follow the Lexicon design
guidelines.

## Updating the UIs to Follow Lexicon Guidelines

Liferay 7 uses a design language known as [Lexicon](http://liferay.github.io/lexicon/).
When building a UI in Liferay 7, it is recommended that you follow the Lexicon 
design patterns. Lexicon is fully extensible, providing styling guidelines and 
best practices to ensure that your applications are well-designed.

Lexicon provides both HTML and JavaScript components for you to quickly and
effectively build fully realized UIs for your applications and web content.

For example, look at the reservation form's HTML for the Lunar Resort:

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

The Lunar Resort theme's reservation form uses Bootstrap 2's design
language. Since Lexicon extends Bootstrap 3, it uses updated patterns and
classes. As a best practice for Liferay 7, you should update your theme's
HTML to follow Lexicon's design language. You can find the Lexicon guidelines on
their site at [http://liferay.github.io/lexicon/](http://liferay.github.io/lexicon/).

For example, the updated Lunar Resort reservation form would be the HTML shown
below:

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

Here is a summary of the Lexicon updates to the example Lunar Resort form:

-   The `control-group` classes are updated to `form-group` classes.
-   The `control-label` classes have been removed from the `<label>` elements.
-   The `<div class=""controls>` elements have been removed.
-   A `form-control` class has been added to each `<input>` element.
-   The `btn-primary` class has been added to the submit button for the form, to
    give it more emphasis and contrast.

You can apply these same Lexicon design patterns to your theme's forms.

You've updated your theme to Liferay 7! Now your users can continue enjoying the
vision you've created.

Run `gulp deploy` to build and deploy the theme to the app server you defined at
the beginning if you're using the themes generator, or run `ant deploy` if 
you're using the standard Plugins SDK.

## Related Topics [](id=related-topics)

[Themes Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator)

[Migrating a theme to Liferay 7](/develop/tutorials/-/knowledge_base/7-0/migrating-a-6-2-theme-to-liferay-7)

[Upgrading to Liferay 7] (/discover/deployment/-/knowledge_base/7-0/upgrading-to-liferay-7)
