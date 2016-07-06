# Upgrading Themes

Introduction goes here.

## Upgrading a Theme

Explain the steps for upgrading a Liferay 6 theme (developed in the Plugins SDK)
to Liferay 7.

## Example: Upgrading a Theme

This tutorial demonstrates upgrading the Lunar Resort theme, that was developed
in the [Liferay 6.2 Themes Learning Path](/develop/learning-paths/themes/-/knowledge_base/6-2/developing-a-liferay-theme),
to Liferay 7. It shows how to use the [Themes Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator) 
to import and upgrade themes and how to resolve [breaking changes](/develop/reference/-/knowledge_base/7-0/what-are-the-breaking-changes-for-liferay-7-0) 
for FreeMarker theme templates. Finally, it covers upgrading resources 
importer files and directories to match the new pattern Liferay 7 uses.

<!-- As a Liferay 6 developer I'd like to know how this particular theme is
reprentative of 6.2 themes. Consider the reader who hasn't gone through that
learning path. Jim -->

<!-- In the previous section, the reader will have just read about upgrading an
Ant-based theme to Liferay 7. As a Liferay 6 developer,  I'd like to know why
we're first migrating to a Node.js based environment. Tell me why it's
advantageous to do that. Jim -->

<!-- TODO: This example covers a lot of upgrade topics. I think all of them
should be mentioned, perhaps even as bullets. As I read the example, here is the
breakdown I see ... 

- Migrating the theme to a Node.js-based project
- Upgrading with the Gulp theme upgrade task
- Updating the CSS
- Updating the Theme templates
- Updating the Theme's Resources Importer

Jim -->

<!--for now I have left the guestbook portlet and make no mention of it since it 
is not updated AFAIK. Once it has been updated, it can be added to this section 
as part of the Learning Path, if desired.-->

To get started, create a folder called *resources* and 
unzip the [6.2 Lunar Resort theme and resources]<!--URL needs updated and theme files in temp documents placeholder folder need to be zipped(/documents/10184/581742/lunar-resort-theme-migration-6.2.zip)-->
into it. Note that the example Lunar Resort theme has been slightly modfied for 
this tutorial.

<!-- TODO: What is the ZIP file's URL? Jim -->

<!-- TODO: What's the significance of the directory name "resources"? Or is it arbitrary? Jim -->

<!-- TODO: It would be great to add some pictures that show the theme applied to
the Lunar Resort. Jim -->

In addition to installing the resources, follow the [Themes Generator tutorial](/develop/tutorials/-/knowledge_base/7-0/themes-generator) 
to install the tools used in this tutorial.

Now you can go ahead and jump right into importing the Lunar Resort theme!

### Importing an Existing Theme

Now that you have the Lunar Resort theme resources downloaded and Themes 
Generator installed, you can update the theme to use the new node build tools. 
To help automate this process, the Themes Generator has an import sub-generator. 

<!-- Aren't these Node.js build tools? If so, we should replace "node" with
"Node.js" throughout this article. 

Also, note that Gulp should be capitalized. I've tried to update all references
to Gulp. Jim -->

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

The Lunar Resort theme is setup to use the node tools, but it's still a 6.2
theme at the moment. As part of the import process, a Gulp file was added that
gives you access to several Gulp theme tasks, one of which is `upgrade.` You'll
use the `gulp upgrade` task to start the upgrade process.

<!-- This should be a new section, perhaps named "Upgrading with the Gulp Theme Upgrade Task".

The start of each section, including this section, should explain not only what
the section is about but also why the steps described are necessary or
beneficial.

Jim -->

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

In this section you'll finish updating the Lunar Resort theme's CSS.

<!-- Explain not only what the section is about but also why the steps described
are necessary or beneficial. Jim -->

#### Updating the CSS Rules and Imports

At the end of the `gulp upgrade` task a list of changes, removals, and 
suggestions were printed in the command line, for the Bootstrap upgrade.
<!-- OK, so what do they mean to me? Jim -->

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
gulp `build` task.

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

<!-- The developer might not know what a breaking change is. Please explain what
is a breaking change and point out that we've documented them and in some cases
have created tools to identify and address them. Jim -->

The upgrade output should look like this:

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

In the last section, you continued the upgrade process for the Lunar Resort
theme and updated the theme templates. Next, you'll make the required changes for
the resources importer.

<!-- Describe the resources that this theme imports. Jim -->

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
        
    This class has been reogranized in Liferay 7.
    
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

The resources importer is fully upgraded! The Lunar Resort theme is ready for
Liferay 7!

<!-- TODO: This section needs a closing summary paragraph(s). Jim -->

Run `gulp deploy` to build and deploy the theme to the app server you defined at
the beginning.

<!-- TODO: The Upgrading Themes overall article needs a Summary section
and related articles. Jim -->
