# Upgrading Themes

@product@ brings with it several updates in UI, design, build tools, development
processes and much more. These updates are designed with the user in mind.
They're designed for you, the developer, to create the vision that you see,
without the tools getting in your way.

In this section of tutorials, you will learn how to upgrade your Ant based theme
project from Liferay 6.2 to @product@, using two methods. The first tutorial
covers how to upgrade your Ant based theme project to @product@ manually, using
the tools and editors of your choice. The second approach shows you how to
upgrade your Ant based theme to @product@ using the Themes Generator, a powerful
tool that brings with it some handy features.

If you want to upgrade your theme manually, you can go ahead and get started. If
you want to learn how to upgrade your theme using the Themes Generator, you can
jump to the [Example: Upgrading a Theme](#example-upgrading-a-theme) section.

## Upgrading Your Ant-Based Theme Project to Liferay 7

You've developed your theme in Liferay 6.2 and now you've upgraded to @product@.
Upgrading your theme to @product@ is easier than you may think. With just a few
adjustments, your theme will be running on @product@ in no time.

This tutorial demonstrates how to upgrade your Ant based theme project from 
Liferay 6.2 to @product@ and covers the following topics:

- Updating the theme version
- Updating CSS files
- Updating theme templates
- Updating the resources importer

Go ahead and get started by updating your theme's version next.

### Updating Your Theme Version

In order for your theme to run in Liferay 7, you'll need to update the Liferay
version to `7.0.0+`. The theme's version information is defined in two files.
You can start with your `liferay-plugin-package.properties` first.

1.  Open your `liferay-plugin-package.properties` file and change the 
    `liferay-versions` property from `6.2.[version]` to `7.0.0+`:
    
        liferay-versions=7.0.0+

    The resources importer configuration settings, found in this file, are 
    covered in a later section. Now that your version is updated in your 
    `liferay-plugin-package.properties`, you'll need to update the version in 
    your `liferay-look-and-feel.xml`.

2.  Open your `liferay-look-and-feel.xml` and update the `compatibility` and 
    `DTD` versions to match the following configuration:
    
    <?xml version="1.0"?>
    <!DOCTYPE look-and-feel PUBLIC "-//Liferay//DTD Look and Feel 7.0.0//EN" 
    "http://www.liferay.com/dtd/liferay-look-and-feel_7_0_0.dtd">
    
    <look-and-feel>
            <compatibility>
                    <version>7.0.0+</version>
            </compatibility>
            ...
    </look-and-feel>

Your theme can now run on Liferay 7! Not so fast though, your theme can run on
Liferay 7, but it still needs some updates in order to render properly. Now that
your theme version is updated you can update your theme's CSS next.

### Updating Your CSS

The UI has changed somewhat between Liferay 6.2 and @product@, which has caused
some CSS files to be removed and others to be added. The directory structure has
changed somewhat as well. @product@ has updated from Bootstrap 2 to Bootstrap 3, 
which has caused some changes in CSS classes. The CSS responsiveness tokens have 
changed, and will need to be updated. Finally, some variables have migrated, 
which could cause your theme to potentially break if not updated.

In this section you'll update your theme's CSS to reflect these changes. Go
ahead and get started by renaming your CSS files.

1.  Rename the CSS files in your `_diffs/css` folder, following the SASS naming 
    convention. For example, `custom.css` becomes `_custom.scss`. The updated 
    `css/` directory should look similar to the following structure at this 
    stage:

        - `_diffs/`
            - `css/`
                - `_aui_variables.scss`
                - `_custom.scss`
                - `dockbar.css`(This file has been removed)
                - `_liferay_custom.scss`
                - `_navigation.scss`
                - `_portlet.scss`
                - `aui.scss`
                - `main.scss`

<!-- Do I need to add a complete build directory structure example as well? or 
will those files be updated properly after the upgraded theme has been deployed?
    As stated earlier, the directory structure has been updated. Once your theme
    has been built, your `build/css` directory should look similar to the
    following:
-->

    The rest of the updated CSS files and directories can be found in your `css` 
    directory, which should look similar to the following structure:

        - `css/`
            - `_application.scss`
            - `_aui_custom.scss`
            - `_aui_variables.scss`
            - `_base.scss`
            - `_custom.scss`
            - `_extras.scss`
            - `_imports.scss`
            - `_layout.scss`
            - `_liferay_custom.scss`
            - `_liferay_variables_custom.scss`
            - `_liferay_variables.scss`
            - `_navigation.scss`
            - `_portal.scss`
            - `_portlet.scss`
            - `_taglib.scss`
            - `application/`
            - `aui/`
            - `aui.scss`
            - `base/`
            - `layout/`
            - `main.scss`
            - `navigation/`
            - `portal/`
            - `portlet/`
            - `taglib/`

    Now that your files are renamed, you can update your CSS rules to match
    the CSS3 syntax next. <!-- is this the right version? -->
    
2.  `Padding` no longer effects `width` or `height` properties, so you will need
    to update your CSS rules accordingly. For example the following CSS rule:
    
        .container{
            height: 30px;
            padding-top: 10 px;
        }
        
        would need to be updated to the following rule:
        
        .container{
            height: 40px;
        }
        
    The `padding-top` rule has been removed, so the `10px` difference has been
    added to the total `height` instead. Follow this same method to update your
    effected CSS rules.
    
<!-- What other rules are there that need updated? -->

    Now that your CSS rules are updated, you can update your theme's CSS
    responsiveness next. In Liferay 6.2, `respond-to` mixins were used to
    specify aspect ratios for CSS responsiveness.
    
The `respond-to` media queries that were used in Liferay 6.2 have been
replaced with the explicit media queries. Follow the step below to update your
responsiveness.

1.  Update your 6.2 `respond-to` media queries with the matching ones below:

    **Media Query Replacements**

      6.2      |  &nbsp;7.0     |
    ---------- |:-------------- |
    `@include respond-to(phone)` | `@include media-query(null, $screen-xs-max)` |
    `@include respond-to(tablet)` | `@include media-query(sm, $screen-sm-max)` |
    `@include respond-to(phone, tablet)` | `@include media-query(null, $breakpoint_tablet - 1)` |
    `@include respond-to(desktop, tablet)` | `@include sm` |
    `@include respond-to(desktop)` | `@include media-query($breakpoint_tablet, null)` |

Your responsiveness is back in order. You can update your variables and Font 
Awesome settings next. The Font Awesome icons are responsible for rendering 
several icons and UI in @product@. In Liferay 6.2, the file path for these icons 
was defined in the previously named `aui.css` file. In @product@ the Font 
Awesome icon imports have been relocated to the  `_aui_variables.scss` file. You 
will need to add the imports for the Font Awesome Icons to the top of this file. 
Don't worry, if you had previously modified that file and currently 
have variables in it, they can remain there.

1.  Add the following Font Awesome imports to the top of your 
    `_aui_variables.scss` file to ensure that the icons are rendered properly:

        // Icon paths    
        $FontAwesomePath: "aui/lexicon/fonts/alloy-font-awesome/font";
        $font-awesome-path: "aui/lexicon/fonts/alloy-font-awesome/font";
        $icon-font-path: "aui/lexicon/fonts/";
        
Some variables and classes have been added, others removed, in Bootstrap 3, that 
were available in Bootstrap 2. Please refer to the [Migrating from 2.x to 3.0](http://getbootstrap.com/migration/#migrating-from-2x-to-30) 
guide for the changes.

Next you can update your theme templates.

### Updating Your Theme Templates

Since Liferay 6.2, there have been a few breaking changes that you should be
aware of in @product@ that affect your theme templates. Breaking changes refer
to any changes that have been made to @product@ that can potentially break your
Liferay 6.2 theme. You can view all the breaking changes for @product@ in the 
[Breaking Changes reference doc](/develop/reference/-/knowledge_base/7-0/breaking-changes).

It's also important to note that Velocity theme templates have been deprecated
in @product@. In Liferay 6.2, Velocity and FreeMarker theme templates were
allowed, but it is recommended that you use FreeMarker theme templates in
@product@, as Velocity theme templates are not compatible with @product@.

In this section, you'll update your theme templates to reflect the breaking
changes in @product@. If you did not modify 

#### Updating Portal Normal FTL

Follow these steps to update your `portal_normal.ftl` theme template. If you did
not modify `portal_normal.ftl` in your Liferay 6.2 theme, you can skip these 
steps.

Starting in @product@, taglibs are no longer accessible via the theme variable 
in FreeMarker templates. You will need to update these directives with the new
syntax.

1.  Open your `portal_normal.ftl` file and replace the following directives with
    the matching ones shown below:
    
    **FreeMarker Theme Variable Replacements**

      6.2      |  &nbsp;7.0     |
    ---------- |:-------------- |
    `${theme.include(top_head_include)}` | `<@liferay_util["include"] page=top_head_include />` |
    `${theme.include(body_top_include)}` | `<@liferay_util["include"] page=body_top_include />` |
    `${theme.include(content_include)}` | `<@liferay_util["include"] page=content_include />` |
    `${theme.wrapPortlet("portlet.ftl", content_include)}` | `<@liferay_theme["wrap-portlet"] page="portlet.ftl"> <@liferay_util["include"] page=content_include /> </@>` |
    `${theme.include(body_bottom_include)}` | `<@liferay_util["include"] page=body_bottom_include />` |
    `${theme.include(bottom_include)}` | `<@liferay_util["include"] page=bottom_include />` |

    This takes care of the `${theme}` variable replacements, but there is still 
    more work to be done in your `portal_normal.ftl` file.

4.  Find the link `<a href="#main-content" id="skip-to-content">
    <@liferay.language key="skip-to-content" /></a>` and replace it with 
    `<@liferay_ui["quick-access"] contentId="#main-content" />`, which uses the 
    new syntax
    
    The Dockbar has been removed in @product@ and replaced with the Control
    Menu. You'll need to update all references to the dockbar in your template.

2.  Replace the `<@liferay.dockbar />` directive and wrapping `<#if>` statements 
    with the `<@liferay.control_menu />` directive:
    
        Before:
        
        <#if is_signed_in>
                <@liferay.dockbar />
        </#if>
        
        After:
        
        <@liferay.control_menu />
        
    If you used the dockbar-split setup in your 6.2 theme, you will need to
    update your `body` class as well, otherwise, you can skip to step 4. As a
    reminder, here is what the dockbar-split UI looks like:
    
    ![Figure x: The dockbar was removed in Liferay 7 and will need to be removed from your code](../../../images/upgrading-themes-dockbar.png)
    
    Go ahead and remove the `dockbar-split` code from your template.
    
3.  Find the `<body class="${css_class} dockbar-split">` element and remove
    `dockbar-split` from its `class` value.
    
    There are just a few more updates left to make in this file.

4.  Remove the following page title code:

        <h2 class="page-title">		
                <span>${the_title}</span>		
        </h2>

5.  Find the following line 
    `<#include "${full_templates_path}/navigation.ftl" />` and wrap it with
    conditional elements to match the pattern below:

        <#if has_navigation && is_setup_complete>
            <#include "${full_templates_path}/navigation.ftl" />
        </#if>

    The last update for `portal_normal.ftl` is to update the content `<div>` to 
    use the HTML5 `<section>` element.

6. Find the `<div id="content">` element and update it and convert it to a 
   `<section>` element, as demonstrated below:

        <section id="content">
            <h1 class="hide-accessible">${the_title}</h1>
            ...
        </section>

    A `<h1>` element was added as well to match the updated 
    [_unstyled] (https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/foundation/frontend-theme/frontend-theme-unstyled/src/main/resources/META-INF/resources/_unstyled/templates/portal_normal.ftl)
    base theme.  
    
Next you can update your theme's navigation template.
    
#### Updating Navigation FTL

Since Liferay 6.2 there have been a few updates to the navigation template for
the `_unstyled` theme. You'll need to make the following changes in your 
`navigation.ftl` theme template, if you modified your `navigation.ftl` template
in your 6.2 theme.

1.  Add the following heading below the 
    `<nav class="${nav_css_class}" id="navigation" role="navigation">` line:

        <h1 class="hide-accessible">
                <@liferay.language key="navigation" />
        </h1>
        
    This creates a hidden heading that is accessible to screen readers.

2.  Add the following variable assignment below the 
    `<#assign nav_item_css_class = "" />` variable:

        <#assign nav_item_layout = nav_item.getLayout() />
        
    This defines a variable to access the layout for navigation. You'll use this
    variable to retrieve an icon for the navigation menu next.

3.  Find the `<a aria-labelledby="layout_${nav_item.getLayoutId()}"...</a>`
    anchor and replace the `${nav_item.icon()}` variable with the following code:

        <@liferay_theme["layout-icon"] layout=nav_item_layout />
        
Your navigation template is up to date.

That should cover most, if not all, of the changes needed for your theme
templates. If you modifed any other theme templates that were not covered, you
can compare your template to the FreeMarker templates for the [_unstyled](https://github.com/liferay/liferay-portal/tree/7.0.x/modules/apps/foundation/frontend-theme/frontend-theme-unstyled/src/main/resources/META-INF/resources/_unstyled/templates)
theme.

Now that your theme templates are updated, you can update your resources
importer next.

### Updating the Resources Importer

The resources importer has undergone several changes that affect the
configuration files and directory structure. Follow the steps in the sections 
below to make the required changes.

#### Updating liferay-plugin-package.properties

1.  Open the `liferay-plugin-package.properties` file and remove the 
    `required-deployment-contexts` line. 
    
    This property is no longer required, as the resources importer is now an 
    [OSGI module](https://github.com/liferay/liferay-portal/tree/master/modules/apps/web-experience/export-import/export-import-resources-importer) 
    that is included in @product@.
    
    The class name has changed slightly, so you'll need to update it.

2.  Update the value of the `resources-importer-target-class-name` property to 
    the value below:
    
        com.liferay.portal.kernel.model.Group
        
With your configuration files updated, you can move onto the directory structure 
and web content article changes next.
    
#### Updating the Web Content Articles

In versions prior to @product@, basic web content articles did not require a
structure and template, and could be of type *HTML*. In @product@ all articles
must have a structure and template and be of type *XML*. Follow the steps below 
to update your directory structure for @product@:

1.  Open the `src/resources-importer/journal/articles/` directory and create a
    folder, for example `Basic Web Content`, to hold your articles.

2.  Rename the extension for any `.html` articles you have to `.xml`.

3.  Follow the pattern below to update your HTML articles to XML:

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

    Once your articles are updated, you can create the template and structures
    next.
    
    Before @product@ web content structures were of type *XML*. Now web content
    structures must be of type *JSON*. You can manually recreate your structure 
    in @product@ to ensure that it is correct. For basic web content articles 
    you can use the structure covered in the next step.

4.  Create a `[structure-name].json` file in the 
    `resources-importer/journal/structures/` directory that matches the name of 
    the folder you created in step 1. For basic web content articles you can use
    the structure below:

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

    The structure above defines some basic attributes for the web content, sets 
    the input field data as `html`, and identifies the web content by the `name`
    `content`. You can use this `name` as reference in your template next.

5.  Open the folder you created in step 1 in the 
    `resources-importer/journal/templates/` directory and create a 
    `[template-name].ftl` file with the same name as your structure. For basic 
    web content you can add the following line to the template:

        ${content.getData()}
        
This simply renders the html from the the matching web content structure with 
the `name` `content`.

Your SDK theme is ready to be deployed to @product@!

In the next tutorial, you can learn how to upgrade your SDK theme to @product@, 
using the Themes Generator.

## Example: Upgrading a Theme

This tutorial demonstrates upgrading the Lunar Resort theme, an Ant based theme 
project that was developed in the [Liferay 6.2 Themes Learning Path](/develop/learning-paths/themes/-/knowledge_base/6-2/developing-a-liferay-theme),
to a Liferay 7 Node.js based environment. It shows how to use the 
[Themes Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator) 
to import and upgrade themes, and how to resolve [breaking changes](/develop/reference/-/knowledge_base/7-0/what-are-the-breaking-changes-for-liferay-7-0) 
for FreeMarker theme templates. Finally, it covers upgrading resources importer 
files and directories to match the new pattern Liferay 7 uses.

Migrating your theme to a Node.js based theme project offers you a lot as a
developer. It gives you access to various extension points, such as [Themelets]()
, and helps streamline the development process via Gulp tasks.

As a review, the Lunar Resort theme is based off of the `_unstyled` base theme
and makes a few key modifications to the Portal's UI. It incorporates a 
responsive design that leverages Bootstrap and Font Awesome's icons, along
with configurable theme settings.

![Figure x: The Lunar Resort theme offers a clean, minimal design.](../../../images/finished-theme.png)

This tutorial covers the following upgrade topics:

- Migrating a theme to a Node.js-based project
- Upgrading with the Gulp theme upgrade task
- Updating the CSS
- Updating the Theme templates
- Updating the Theme's Resources Importer

<!--for now I have left the guestbook portlet and make no mention of it since it 
is not updated AFAIK. Once it has been updated, it can be added to this section 
as part of the Learning Path, if desired.-->

To get started, unzip the [6.2 Lunar Resort theme and resources](/develop/tutorials/code/upgrading-themes/lunar-resort-theme-migration-6.2.zip)
into a folder of your choice. Note that the example Lunar Resort theme has been 
slightly modfied for this tutorial.

In addition to installing the resources, follow the [Themes Generator tutorial](/develop/tutorials/-/knowledge_base/7-0/themes-generator) 
to install the tools used in this tutorial.

Now you can go ahead and jump right into importing the Lunar Resort theme!

### Importing an Existing Theme

Now that you have the Lunar Resort theme resources downloaded and Themes 
Generator installed, you can update the theme to use the new Node.js build tools. 
To help automate this process, the Themes Generator has an import sub-generator.

Follow the steps below to import the Lunar Resort theme.

1.  Create a new directory called *Theme*.

2.  Navigate to the `Theme` directory you just created and run the following 
    command from the command line:

        yo liferay-theme:import

    This starts the import sub-generator process.

3.  Enter the absolute path of the Lunar Resort theme:

    +$$$

    **Note:** you must specify an absolute path, as the themes import
    sub-generator does not support relative paths.

    $$$

    The importer creates some configuration files and migrates the modified
    theme files over to a new `src` directory. Similar to the `_diffs` directory 
    in Plugins SDK developed themes, the `src` directory is where all of your
    theme modifications go.
        
4.  Enter the path to your app server directory.
        
5.  Enter the URL of your site (this can be your production site, development
    site, etc.), or press *Enter* to accept the default URL
    `http://localhost:8080`.
    
Your settings are added to a `liferay-theme.json` file in the theme's root
directory. Here's an example set of theme settings:

    {
      "LiferayTheme": {
        "appServerPath": "/Users/mike/opt/bundles/tomcat-8.0.32",
        "deployPath": "/Users/mike/opt/bundles/deploy",
        "url": "http://localhost:8080",
        "appServerPathTheme": "/Users/mike/opt/Liferay/bundles/tomcat-8.0.30/webapps/lunar-resort-ce-theme",
        "deployed": false,
        "themeName": "lunar-resort-ce-theme"
      }
    }
    
To change your settings at any time, run `gulp init` from the theme's root 
directory.

Notice also that a `package.json` was added to the theme. This defines some
basic info for the theme:

    {
            "name": "lunar-resort-ce-theme",
            "version": "0.0.0",
            "keywords": [
                    "liferay-theme"
            ],
            "liferayTheme": {
                    "baseTheme": "styled",
                    "screenshot": "",
                    "rubySass": true,
                    "templateLanguage": "ftl",
                    "version": "6.2"
            },
            "devDependencies": {
                    "gulp": "^3.8.10",
                    "liferay-theme-tasks": "*",
                    "liferay-theme-deps-6.2": "*"
            },
            "publishConfig": {
                    "tag": "6_2_x"
            }
    }
    
As you may have noticed, the `version` is still `6.2`. The import process 
imports a Plugins SDK theme to use the node build tools. You'll upgrade the
theme to 7.0 in a moment.
    
Your updated theme directory structure should reflect the one below:

- `lunar-resort-theme/`
    - `gulpfile.js`
    - `liferay-theme.json`
    - `node_modules/`
        - (many directories)
    - `package.json`
    - `src/`
        - `css/`
            - `_aui_variables.scss`
            - `custom.css`
        - `images/`
            -   (many directories)
        - `js/`
            - `main.js`
        - `templates/`
            - `init_custom.ftl`
            - `navigation.ftl`
            - `portal_normal.ftl`
        - `WEB-INF/`
            - `liferay-look-and-feel.xml`
            - `liferay-plugin-package.properties`
            - `lib/`
            - `src/`
                - `resources-importer/`
                    - (Many directories)

Now that your theme is imported into the new build structure, you can upgrade
your theme's version next.

<!-- This should be a new section, perhaps named "Upgrading with the Gulp Theme Upgrade Task".

The start of each section, including this section, should explain not only what
the section is about but also why the steps described are necessary or
beneficial.

Jim -->

### Upgrading with the Gulp Theme Upgrade Task

The Lunar Resort theme is setup to use the Node.js tools, but it's still a 6.2
theme at the moment. As part of the import process, a Gulp file was added that
gives you access to several Gulp theme tasks, one of which is `upgrade.` You'll
use the `gulp upgrade` task to start the upgrade process next.

Follow these steps to upgrade the Lunar Resort theme to Liferay 7.

1.  Navigate to the `lunar-resort-ce-theme` in your `theme` directory if you are
    not already there and run the following command:

        gulp upgrade

    The existing Lunar Resort theme is placed in a `backup` folder. This can be
    used to restore your theme back to its original state. Running 
    `gulp upgrade:revert` will restore the Lunar Resort theme back to its 
    original (post-import) state. All the core code needed to generate the 
    proper base files for Liferay 7 themes has also been created.
    
    If you open `package.json` in the theme's root directory, you'll note that
    the `version` is now `7.0`.

2.  Press `Enter` for each file rename to update them to the `.scss` Sass file 
    extension. All sass files now have the `.scss` extension and all sass 
    partials have an underscore at the start of their name (e.g.,
    `_custom.scss`). 
    
    Once the renames are complete, the Gulp task makes a best effort to upgrade
    the theme's Bootstrap from 2 to 3. It takes care of some of the upgrade for
    you and prints suggestions for further updates to consider.
    
On completion of the `gulp upgrade` task, your directory structure should
reflect the one below: 

- `lunar-resort-theme/`
    - `gulpfile.js`
    - `liferay-theme.json`
    - `node_modules/`
        - (many directories)
    - `package.json`
    - `src/`
        - `css/`
            - `_aui_variables.scss`
            - `_custom.scss`
        - `images/`
            -   (many directories)
        - `js/`
            - `main.js`
        - `templates/`
            - `init_custom.ftl`
            - `navigation.ftl`
            - `portal_normal.ftl`
        - `WEB-INF/`
            - `liferay-look-and-feel.xml`
            - `liferay-plugin-package.properties`
            - `lib/`
            - `src/`
                - `resources-importer/`
                    - (Many directories)

Next, you'll upgrade the Lunar Resort theme's CSS.

### Updating the CSS

At the end of the `gulp upgrade` task a list of changes, removals, and 
suggestions were printed in the command line, for the Bootstrap upgrade. These
changes reflect the updates needed, due to migration from Bootstrap 2 to
Bootstrap 3(the version Liferay 7 uses).

In this section you'll finish updating the Lunar Resort theme's CSS.

<!-- Explain not only what the section is about but also why the steps described
are necessary or beneficial. Jim -->

#### Updating the CSS Rules and Imports

Follow these steps to make the remaining updates to the Lunar Resort's CSS files.

1.  In the `src` directory, open the `_custom.scss` file.

2.  Find the `#reserveBtnWrapper` selector and update its `width` property from `300px` 
     to the value below:

        height: 334px;

2.  Find the `div` selector, just above the `#facebook` selector for the 
    `.social-media-wrapper ul li div`. Change the `height` property's value 
    from `39px` to the value below:

        height: 46px;
 
    Go ahead and remove the `padding-top` rule from that selector as well. 
        
    This modification, accounts for the update to each selector's *Padding*
    property--it no longer affects width or height.
    
3.  Add a `padding: 0;` rule to the `.social-media wrapper ul` selector:

        margin: auto;
        padding: 0;
        text-align: center;

    This counteracts any inherited padding that was offsetting the unordered
    list for the social media links.

    The next update that needs to be made is the file path for the Font Awesome
    icons. The social media links, as well as many other UI elements in Liferay,
    rely on the Font Awesome icons.

3.  The file path for the Font Awesome icons needs to be updated. The social
    media links, as well as many other UI elements in Liferay, rely on the Font
    Awesome icons.

    Open the `_aui_variables.scss` file and add these imports to the top of it:

        // Icon paths
        $FontAwesomePath: "aui/lexicon/fonts/alloy-font-awesome/font";
        $font-awesome-path: "aui/lexicon/fonts/alloy-font-awesome/font";
        $icon-font-path: "aui/lexicon/fonts/";

In versions prior to Liferay 7, the Font Awesome icon path was specified in the
`aui.css` file. If you are migrating your theme from 6.2 to 7 and have variables
in your `_aui_variables.scss`, make sure to add the Font Awesome imports listed
above, or the icons won't render properly in your theme.

If you don't have any custom variables in your `_aui_variables.scss` file, you
can remove that file, as it will be generated automatically when you run the
Gulp `build` task.

The social media links are now updated!

#### Updating Responsiveness

In Liferay 6.2, `respond-to` variables were used to respond to specific aspect
ratios or devices. The syntax has slightly changed for Liferay 7. Explicit media
query mixins are used in place of these variables. Follow the steps below to
update the media queries:

1.  Open `_custom.scss` file.

2.  Find the `@include respond-to(phone, tablet)` mixin 
    and replace it with the following media query:

        @include media-query(null, $breakpoint_tablet - 1)

2.  Find the `@include respond-to(phone)` mixin and replace it with the
    following media query:

        @include media-query(null, $screen-xs-max)

    Now that the responsiveness is updated, there are a few additional styles 
    that need updating.

3.  Still inside `_custom.scss`, find the `html #wrapper #banner #heading`
    selector, inside of the `@include media-query(null, $breakpoint_tablet - 1)`
    media query. Change the selector's value for the `top` property to `0px`.

4.  In that same media query, change the `padding-top` property for the 
    `html #wrapper #content` selector to `130px`.

5.  Finally, inside of the `@include media-query(null, $screen-xs-max)` media
    query, remove the `margin` property from the `html #wrapper` selector.

That wraps up the theme's CSS changes.

In the next section, you'll address the breaking changes that apply to the
theme templates.

### Updating Theme Templates

In the last section, you updated the Lunar Resort's CSS files per the
suggestions in the Gulp upgrade log. In this section you'll review the log's
breaking changes listed for the theme templates and make the required updates.

A breaking change refers to any changes that have been made between Liferay 6.2
and Liferay 7 that can potentially break something in your Portal installation.
These are kept to a minimum, but in some cases, they are unavoidable. You can
find a full list of the breaking changes for Liferay 7 in our [Breaking Changes](/develop/reference/-/knowledge_base/7-0/breaking-changes)
reference doc. In some cases, you can use tools, such as the Code Upgrade Tool
in Liferay IDE or Liferay Developer Studio, to identify and address these
changes.

<!-- Link to Code Upgrade Tool tutorial once it is published. -->

The upgrade output from the `gulp upgrade` task should look like this:

        ----------------------------------------------------------------
        Liferay Upgrade (6.2 to 7)
        ----------------------------------------------------------------

        File: portal_normal.ftl
            Warning: <@liferay.dockbar /> is deprecated, replace with 
            <@liferay.control_menu /> for new admin controls.
            Warning: not all admin controls will be visible without 
            <@liferay.control_menu />
            Warning: ${theme} variable is no longer available in Freemarker 
            templates, see https://goo.gl/9fXzYt for more information.
            
This output points out some of the changes in Liferay 7 that affect theme
templates. Follow these steps to update the theme templates:

1.  Open the `portal_normal.ftl` file in your `src/templates` directory.

2.  Replace the `<@liferay.dockbar />` directive with `<@liferay.control_menu />`.

    In Liferay 7, the Dockbar has been replaced by the Control Menu. Due to the
    dockbar's deprecation, the `dockbar-split` class is no longer needed.

2.  Find the `<body class="${css_class} dockbar-split">` element and remove
    `dockbar-split` from its class value.
    
    As you can see from the log, the `${theme}` variable is no longer available
    in FreeMarker templates. You can view the breaking change in more detail
    here: [https://goo.gl/9fXzYt](https://goo.gl/9fXzYt). You'll need to update
    the templates to use the new syntax.

3.  Find the `${theme.include(top_head_include)}` directive and replace it with
    the `<@liferay_util["include"] page=top_head_include />` directive.

    Since the `${theme}` variable is no longer available to access the utilities 
    and tags in FreeMarker templates, this accesses the utility directly and
    defines the page to include.

4.  Find the `${theme.include(body_top_include)}` directive and replace it with
    `<@liferay_util["include"] page=body_top_include />`.

5.  Find `${theme.include(content_include)}` and replace it with 
    `<@liferay_util["include"] page=content_include />`.    

6.  Find the `${theme.wrapPortlet("portlet.ftl", content_include)}` directive in
    the `<div id="content">` div and replace it with the following one:

        <@liferay_theme["wrap-portlet"] page="portlet.ftl">
            <@liferay_util["include"] page=content_include />
        </@>

7.  Find the `body_bottom_include` and `bottom_include` directives at the
    bottom of the file, and replace them with the following ones:
   
        <@liferay_util["include"] page=body_bottom_include />

        <@liferay_util["include"] page=bottom_include />

    That takes care of all the `${theme}` variable updates

8.  Find the link `<a href="#main-content" id="skip-to-content">
    <@liferay.language key="skip-to-content" /></a>` and replace it with 
    `<@liferay_ui["quick-access"] contentId="#main-content" />`, which uses the new
    syntax.
    
9.  Find the following line 
    `<#include "${full_templates_path}/navigation.ftl" />` and wrap it with
    conditional elements to match the pattern below:

        <#if has_navigation && is_setup_complete>
            <#include "${full_templates_path}/navigation.ftl" />
        </#if>

    The last update for portal normal is to update the content `<div>` to use 
    the HTML5 `<section>` element.

10. Find the `<div id="content">` element and update it and convert it to a
    `<section>` element, as demonstrated below: 

        <section id="content">
            <h1 class="hide-accessible">${the_title}</h1>
            ...
        </section>

    A `<h1>` element was added as well to match the updated 
    [_unstyled] (https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/foundation/frontend-theme/frontend-theme-unstyled/src/main/resources/META-INF/resources/_unstyled/templates/portal_normal.ftl)
    base theme.

That's all the updates you need to make to the theme templates for the Lunar
Resort theme. In the next section, you'll update the theme's resources importer.

### Updating the Resources Importer

The Lunar Resort's resources include web content articles and the matching
images. In the last section, you continued the upgrade process for the Lunar 
Resort theme and updated the theme templates. In this section, you'll make the 
required changes for the resources importer.

+$$$

**Note:** The resources importer articles have been slightly modified due to a
known issue [LPS-64859](https://issues.liferay.com/browse/LPS-64859). The
articles in the 6.2 Learning Path have links to pages in the layout of the site.
Due to the order in which the pages and articles are imported, a null pointer
exception occurs. To avoid this issue, these links have been removed from the
articles formerly imported by this theme.

$$$

Updates have been made to the importer, causing class names, the directory
structure, and web content structures to change.

Follow the steps below to update the resources importer for the Lunar Resort.

#### Updating liferay-plugin-package.properties

First off, you'll need to update the `liferay-plugin-package.properties` file
with the proper settings:

1.  Open `liferay-plugin-package.properties` and
    remove the `required-deployment-contexts` property. 

    The resources importer has been reconfigured as an OSGi module that is
    available to you in Liferay by default; so the context is no longer needed.

2.  Update the `resources-importer-target-class-name` property's value to this:

        com.liferay.portal.kernel.model.Group
        
    This class has been reorganized in Liferay 7.
    
Now that the resources importer is configured properly, you can move on to
updating the web content.

#### Updating the Web Content

In Liferay 7, all web content articles require a structure and a template.
You'll need to update each of the theme's current web content articles to use a
structure and matching template. 

##### Updating the Directory Structure

In Liferay 7, basic web content articles have a Basic Web Content structure and
template that provides the essentials for creating and rendering article
content.

For the Lunar Resort theme's articles, you'll create your own version of the
Basic Web Content structure and template.

Follow the steps below to reorganize the Basic Web Content articles:

1.  Create a `Basic Web Content` folder in the 
    `src/resources-importer/journal/articles/` directory.

2.  Move all of the basic HTML articles into the `Basic Web Content` folder you
    just created.

3.  Add a matching `Basic Web Content` folder to the 
    `resources-importer/journal/templates/` directory.

    The folders are ready for the new structure and template that you'll create.

4.  In the `resources-importer/journal/structures/` directory, create a `Basic
    Web Content.json` file with following code:

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
    articles and specifies a `name` to identify the content. In previous
    versions of Liferay, article structures were written in XML; they must now
    be written in JSON. Next, you can create the matching template next.

5.  Create a `Basic Web Content.ftl` template file in the 
    `resources-importer/journal/templates/Basic Web Content/`
    folder you just created and add the following code to it:

        ${content.getData()}

This accesses the article's content by the `name` defined in the structure,
`content` in this case, and renders the HTML data. You have created the basic
web content structure and template. All that's left is to update the article
type.

##### Updating the Web Content Articles to XML

In Liferay 6.2, HTML articles were an acceptable type for the Resources Importer. In
Liferay 7, all articles must be in XML. This migration is easier then it
may sound for Basic Web Content. You just need to wrap the HTML in 
`<![CDATA[...]]>` tags.

For example, to update the `2 column description.html` article to XML, you would
write the following in a `2 column description.xml` file:

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
        stay in our fabulous Lunar accomodations. Enjoy a round of Lunar 
        Golf on our one of a kind course. Have a blast on our Rover Racing 
        track. Make your reservation now. The rest of your life starts 
        today!</div>
    </div>
    ]]>
            </dynamic-content>
        </dynamic-element>
    </root>

You may have also noticed that the `<div>` classes have changed. The `2 column
description.xml` article shown above illustrates the updated Bootstrap grid
system for Bootstrap 3. Instead of using the `span[number]` class to designate a
column width, the `col-md-[number]` class designates column width. The total
combined width of the columns must still add up to `12`.

That's all that is needed for most of the Basic Web Content articles. To make 
the article migration run as smoothly as possible, you can replace the HTML Web 
Content articles with the updated XML ones in the 
`resources-importer/journal/articles/Basic Web Content/` directory of the files 
you downloaded at the beginning.

The last Web Content article update is described next.

##### Updating the Reservation Form's Bootstrap

Liferay 7's UI is built using a design language, known as [Lexicon](http://liferay.github.io/lexicon/).
When building a UI in Liferay 7, it is recommended that you use Lexicon to
follow the design patterns that Liferay has created.

The original Lunar Resort theme's reservation form used Bootstrap 2's design
language. Since Lexicon is an extends Bootstrap 3, it uses updated patterns and
classes. As a best practice for Liferay 7, you should update the theme's
reservation form to follow Lexicon's design language.

To update the reservation form, open the `Reservation Form.xml` article in the
`/src/resources-importer/journal/articles/Basic Web Content/` directory and
replace the contents with the markup below:
    
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

Here's what's been changed:

-   The `control-group` classes are updated to `form-group` classes.
-   The `control-label` classes have been removed from the `<label>` elements.
-   The `<div class=""controls>` elements have been removed.
-   A `form-control` class has been added 
    to each `<input>` element. 
-   The `btn-primary` class has been added to the submit button for the form, to
    give it more emphasis and contrast. 

Now that the Basic Web Content is updated, all that is left to update is the
carousel article's structure and template.

##### Updating the Carousel's Structure and Template

The carousel article uses a more advanced structure and template than the Basic 
Web Content. Luckily, the carousel article is already XML.

Follow these steps to migrate the Liferay 6.2 carousel structure and template to 
Liferay 7:

Liferay 6.2 used AlloyUI 2.0.x; Liferay 7 uses Alloy UI 3.0.x. There has been a
slight syntax change to the AlloyUI carousel between the two versions. Note that
support of HTML markup within the carousel images has been removed, breaking the
image links. Make the changes below to update the carousel:

1.  Open the `carousel.vm` template file in the `templates/carousel/` directory and
    replace all instances of `aui-carousel-item` with `image-viewer-base-image`.
    
    Now that the template is updated, you can update the structure. The carousel
    structure is in XML and, as mentioned earlier, all Liferay 7 structures must
    be in JSON.

2.  Open the `carousel.xml` structure file in the `journal/structures/` directory and
    replace the contents with the JSON structure below:

        {
            "availableLanguageIds": [
                "en_US"
            ],
            "defaultLanguageId": "en_US",
            "fields": [
                {
                    "label": {
                        "en_US": "image"
                    },
                    "predefinedValue": {
                        "en_US": ""
                    },
                    "style": {
                        "en_US": ""
                    },
                    "tip": {
                        "en_US": "Image to use in the carousel."
                    },
                    "dataType": "image",
                    "fieldNamespace": "ddm",
                    "indexType": "keyword",
                    "localizable": true,
                    "name": "image",
                    "readOnly": false,
                    "repeatable": true,
                    "required": false,
                    "showLabel": true,
                    "type": "ddm-image",
                    "nestedFields": [
                        {
                            "label": {
                                "en_US": "content"
                            },
                            "predefinedValue": {
                                "en_US": ""
                            },
                            "style": {
                                "en_US": ""
                            },
                            "tip": {
                                "en_US": "This is text made availability to 
                                users with accessibility issues. Screen readers 
                                will be able to read this text."
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
                        },
                        {
                            "label": {
                                "en_US": "link"
                            },
                            "predefinedValue": {
                                "en_US": ""
                            },
                            "style": {
                                "en_US": ""
                            },
                            "tip": {
                                "en_US": "A URL to use for this image. Be sure 
                                to include the \"http://\" at the beginning."
                            },
                            "dataType": "string",
                            "indexType": "keyword",
                            "localizable": true,
                            "name": "link",
                            "readOnly": false,
                            "repeatable": false,
                            "required": false,
                            "showLabel": true,
                            "type": "text"
                        }
                    ]
                }
            ]
        }
        
The JSON structure above was created by manually recreating the structure in
Liferay 7. You can use the same process to convert your deprecated XML 
structures to JSON.

<!--Note that Rob said he would try to work on a automated process for this 
conversion, but for now this is the best approach-->

The resources importer is fully upgraded! You've migrated the Lunar Resort theme
from an Ant based project to a Node.js based project. You used the Themes
Generator to run the upgrade process and point out the breaking changes. You 
updated the CSS and Bootstrap. Finally, you updated the resources importer.

The Lunar Resort theme is ready for
Liferay 7!



<!-- TODO: This section needs a closing summary paragraph(s). Jim -->

Run `gulp deploy` to build and deploy the theme to the app server you defined at
the beginning.

<!-- TODO: The Upgrading Themes overall article needs a Summary section
and related articles. Jim -->
