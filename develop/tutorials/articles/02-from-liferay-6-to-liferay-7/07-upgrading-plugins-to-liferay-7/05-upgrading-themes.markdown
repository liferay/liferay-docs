# Migrating a Theme to Liferay 7

In this portion of the Learning Path, you'll migrate the lunar resort theme
from the [Liferay 6.2 Themes Learning Path](/develop/learning-paths/themes/-/knowledge_base/6-2/developing-a-liferay-theme) 
to Liferay 7. You'll learn how to use the [themes generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator) 
to import and upgrade themes. You'll apply the [breaking changes](/develop/reference/-/knowledge_base/7-0/what-are-the-breaking-changes-for-liferay-7-0) 
to the FreeMarker theme templates. Finally, you'll upgrade the resources 
importer files and directory structure to match the new new pattern.

<!--for now I have left the guestbook portlet and make no mention of it since it 
is not updated AFAIK. Once it has been updated, it can be added to this section 
as part of the Learning Path, if desired.-->

To get started, create a folder called *resources* and 
[download the 6.2 lunar resort theme and
resources]<!--URL needs updated and theme files in temp documents placeholder folder need to be zipped(/documents/10184/581742/lunar-resort-theme-migration-6.2.zip)-->
into it. Note that the example lunar resort theme has been slightly modfied for 
this Learning Path. Follow the [themes generator tutorial](/develop/tutorials/-/knowledge_base/7-0/themes-generator) 
to install the tools you'll need for this portion of the Learning Path.

Now you can go ahead and jump right into importing the theme!

## Importing a 6.2 Theme

Now that you have the lunar resort theme resources downloaded and themes 
generator installed, you can update the theme to use the new node build tools. 
To help automate this process, the themes generator has an import sub-generator. 

Follow the steps below to import the lunar resort theme.

1.  Create a new directory called *Theme*.

2.  Navigate to the *Theme* directory you just created and run the following 
    command from the command line:

        yo liferay-theme:import
        
    This starts the import sub-generator process.

3.  Enter a directory similar to the following to import the lunar resort theme:

        /Users/mike/Desktop/resources/lunar-resort-theme-migration-6.2/lunar-resort-ce-theme

+$$$

    **Note:** The themes generator does not support relative paths.
$$$

    The importer creates some configuration files and migrates the modified
    theme files over to a new `src` directory. Similar to the `_diffs` directory 
    in Plugins SDK developed themes, the `src` directory is where all of your
    theme modifications go.
        
4.  Enter the path to your app server directory. It should be similar to the one 
    below:

        /Users/mike/opt/bundles/tomcat-8.0.32
        
5.  Press *Enter* to accept the default *http://localhost:8080* for the
    development site URL.
    
Your settings are added to a `liferay-theme.json` file in the theme's root
directory:

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
    
To change these settings at any time, run `gulp init` from the theme's root 
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
imports a plugins SDk theme to use the node build tools. You'll upgrade the
theme to 7.0 in a moment.
    
Your updated theme directory structure should reflect the one below after
import:

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
                - `lib/`
            - `liferay-look-and-feel.xml`
            - `liferay-plugin-package.properties`
            - `src/`
                - `resources-importer/`
                    - (Many directories)

The lunar resort theme is setup to use the node tools, but it's still a 6.2
theme at the moment. As part of the import process, a gulp file was added that
gives you access to several gulp theme tasks, one of which is `upgrade.` You'll
use the `gulp upgrade` task to help with the upgrade process.

Follow these steps to upgrade the lunar resort theme to Liferay 7.

1.  Navigate to the `lunar-resort-ce-theme` in your `theme` directory if you are
    not already there and run the following command:

        gulp upgrade

    The existing lunar resort theme is placed in a `backup` folder. This can be
    used to restore your theme back to its original state. Running 
    `gulp upgrade:revert` will restore the lunar resort theme back to its 
    original state, after the import. All the core code needed to generate the 
    proper base files for Liferay 7 themes has also been created.
    
    If you open `package.json` in the theme's root directory, you'll note that
    the `version` is now `7.0`.

2.  Press Enter for each file rename to update them to the `.scss` Sass file 
    extension. All sass files now have the `.scss` extension and all sass 
    partials have an underscore at the start of their name, i.e. `_custom.scss`.
    
    Once the renames are complete, the upgrade task upgrades the themes 
    Bootstrap from 2 to 3, taking care of some of the migration for you, and 
    prints suggestions for further updates.
    
Your directory structure should reflect the one below after you run the 
`gulp upgrade` task:

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
                - `lib/`
            - `liferay-look-and-feel.xml`
            - `liferay-plugin-package.properties`
            - `src/`
                - `resources-importer/`
                    - (Many directories)

In the next section you'll continue the upgrade process with the lunar resort
theme's CSS.

## Updating the CSS

In the last section of the Learning Path you ran the `gulp upgrade` task. This
upgrades parts of the theme, but still requires manual steps on the part of the
developer to finish the upgrade.

In this section you'll finish updating the lunar resort theme's CSS.

### Updating the CSS Rules and Imports

At the end of the `gulp upgrade` task a list of changes, removals, and 
suggestions were printed in the command line, for the Bootstrap upgrade.

Follow these steps to make the remaining updates to the lunar resort's CSS files.

1.  Open the `_custom.scss` file in your `src` directory and find the 
    `#reserveBtnWrapper` selector and update the `width` property from `300px` 
    to the value below:

        height: 334px;

2.  Find the `div` selector, just above the `#facebook` selector for the 
    `.social-media-wrapper ul li div`, and change the `height` property's value 
    from `39px` to the value below:

        height: 46px;
        
    Go ahead and remove the `padding-top` rule from that selector as well. 
        
    This modification, accounts for the update to the *Padding* property in each 
    of these selectors, which no longer affects width or height.
    
3.  Add a `padding: 0;` rule to the `.social-media wrapper ul` selector:

        margin: auto;
        padding: 0;
        text-align: center;
        
    This counteracts inherited padding that was offsetting the unordered list 
    for the social media links.
    
    The next update that needs to be made is the file path for the Font Awesome
    icons. The social media links, as well as many other UI elements in Liferay,
    rely on the Font Awesome icons.

3.  Open `_aui_variables.scss` and add these imports to the top:

        // Icon paths
        $FontAwesomePath: "aui/lexicon/fonts/alloy-font-awesome/font";
        $font-awesome-path: "aui/lexicon/fonts/alloy-font-awesome/font";
        $icon-font-path: "aui/lexicon/fonts/";

In versions prior to Liferay 7, the Font Awesome icon path was indicated in the
`aui.css` file. If you are migrating your theme from 6.2 to 7 and have variables
in your `_aui_variables.scss`, make sure you add the Font Awesome imports listed
above, or the icons will not render properly in your theme. If you did not have
any custom variables in the `_aui_variables.scss` file, you could just remove
that file and it would automatically be updated when the gulp `build` task is
run.

The social media links are now updated!

### Updating Responsiveness

In Liferay 6.2, `respond-to` variables were used to respond to a specific aspect
ratio or device. The syntax has slightly changed for Liferay 7. Instead of using
`respond-to` variables, the explicit media query mixins are used now. Follow the
steps below to update the media queries:

1.  Open `_custom.scss` and find the `@include respond-to(phone, tablet)` mixin 
    and replace it with the following media query:
    
        @include media-query(null, $breakpoint_tablet - 1)
        
2.  Find the `@include respond-to(phone)` mixin and replace it with the
    following media query:
    
        @include media-query(null, $screen-xs-max)
        
    Now that the responsiveness is updated, there are a few additional styles 
    that need to be updated for the theme.

3.  Still inside `_custom.scss`, find the `html #wrapper #banner #heading`
    selector, inside of the `@include media-query(null, $breakpoint_tablet - 1)`
    media query and change the value for the `top` property to `0px`.
    
4.  In that same media query, change the `padding-top` property for the 
    `html #wrapper #content` selector to `130px`.
    
5.  Finally, inside of the `@include media-query(null, $screen-xs-max)` media
    query, remove the `margin` property for the `html #wrapper` selector.

That wraps up the changes needed for the theme's CSS files.

In the next section of the Learning Path you'll review the breaking changes for 
the theme templates and make the required updates.

## Updating Theme Templates

In the last section of the Learning Path you updated the lunar resort's CSS
files per the suggestions in the gulp upgrade log. In this section you'll review
the breaking changes listed in the log for the theme templates and make the 
required updates.

If you take a look at the upgrade output, you'll see these lines:

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
            
This points out some of the changes to themes that have taken place in Liferay 
7 for theme templates. Follow these steps to make the theme template updates:

1.  Open `portal_normal.ftl` in your `src/templates` directory and replace the
    `<@liferay.dockbar />` directive with `<@liferay.control_menu />`.

    The Dockbar has been removed in Liferay 7, and has been replaced with the
    Control Menu. Due to the deprecation of the dockbar, the `dockbar-split`
    class is no longer needed.
    
2.  Find the `<body class="${css_class} dockbar-split">` element and remove
    `dockbar-split`.
    
    As you can see from the log, the `${theme}` variable is no longer available
    in FreeMarker templates. You can view the breaking change in more detail
    here: [https://goo.gl/9fXzYt](https://goo.gl/9fXzYt). You'll need to update
    the templates to use the new syntax.
    
3.  Find the `${theme.include(top_head_include)}` directive and replace it with
    the `<@liferay_util["include"] page=top_head_include />` directive.

    Since the `${theme}` variable is no longer available to access the utlities 
    and tags in FreeMarker templates, this accesses the utility directly and
    defines what page to include.
    
4.  Find the `${theme.include(body_top_include)}` directive and replace it with
    `<@liferay_util["include"] page=body_top_include />`.

5.  Find `${theme.include(content_include)}` and replace it with 
    `<@liferay_util["include"] page=content_include />`.    

6.  Find the `${theme.wrapPortlet("portlet.ftl", content_include)}` directive in
    the `<div id="content">` div and replace it with the following one:
    
        <@liferay_theme["wrap-portlet"] page="portlet.ftl">
            <@liferay_util["include"] page=content_include />
        </@>

7. Find the `body_bottom_include` and `bottom_include` directives at the
   bottom of the file and replace theme with the following ones:
   
        <@liferay_util["include"] page=body_bottom_include />

        <@liferay_util["include"] page=bottom_include />

    That takes care of all the `${theme}` variable updates
        
8.  Find the link `<a href="#main-content" id="skip-to-content">
    <@liferay.language key="skip-to-content" /></a>` and replace it with 
    `<@liferay_ui["quick-access"] contentId="#main-content" />` to use the new
    syntax.
    
9.  Find the following line 
    `<#include "${full_templates_path}/navigation.ftl" />` and update it to
    match the pattern below:
    
        <#if has_navigation && is_setup_complete>
            <#include "${full_templates_path}/navigation.ftl" />
        </#if>
        
    The last update for portal normal is to update the content `<div>` to use 
    the HTML5 `<section>` element.

10. Find the `<div id="content">` element and update it and the matching closing
    `<div>` to use the `<section>` element instead:
    
        <section id="content">
            <h1 class="hide-accessible">${the_title}</h1>
            ...
        </section>
        
    A `<h1>` element was added as well to match the updated 
    [_unstyled] (https://github.com/liferay/liferay-portal/blob/master/modules/apps/foundation/frontend-theme/frontend-theme-unstyled/src/main/resources/META-INF/resources/_unstyled/templates/portal_normal.ftl)
    base theme.
    
That's all the updates you need to make to the theme templates for the lunar
resort theme. In the next section of the Learning Path you'll update the
resources importer for Liferay 7.

## Updating the Resources Importer

In the last section of the Learning Path you continued the upgrade process for
the lunar resort theme and updated the theme templates. In this portion of the
Learning Path, you'll make the required changes to the resources importer.

+$$$

**Note:** The resources importer articles have been slightly modfied due to a
known issue [LPS-64859](https://issues.liferay.com/browse/LPS-64859). The
articles in the 6.2 Learning Path have links to pages in the layout of the site.
Due to the order in which the pages and articles are imported, this causes a
null pointer exception. To avoid this issue, the links have been removed from
the articles.

$$$

Updates have been made to the importer, causing class names, the directory
structure, and web content structures to change.

Follow the steps below to make the updates to the resources importer for the 
lunar resort.

### Updating liferay-plugin-package.properties

First off you'll need to update the `liferay-plugin-package.properties` file to
have the proper settings:

1.  Open `liferay-plugin-package.properties` in your `src/WEB-INF` directory and
    remove the `required-deployment-contexts=\    resources-importer-web` line.
    
    The resources importer has been reconfigured as an osgi module that is
    available to you by default in Liferay, so this line is no longer needed.

2.  Update the `resources-importer-target-class-name` value to the following:

        com.liferay.portal.kernel.model.Group
        
    The class has been reogranized in Liferay 7, so this is now the required
    full class name for the model group.
    
Now that the resources importer is configured properly, you can move on to
updating the web content.

### Updating the Web Content

In Liferay 7, all web content articles require a structure and a template.
You'll need to update the current web content articles to use a structure and a
matching template. 

#### Updating the Directory Structure

In Liferay 7, basic web content articles have a Basic Web Content structure and
template that provides the essentials needed to create and render the article's 
content.

You'll create your own version of the Basic Web Content structure and template
for the lunar resort theme's articles.

Follow the steps below to reorganize the basic web content articles:

1.  Create a `Basic Web Content` folder in the 
    `src/resources-importer/journal/articles/` directory.
    
2.  Move all of the basic HTML articles into the `Basic Web Content` folder you
    just created.
    
3.  Add a matching `Basic Web Content` folder to the 
    `resources-importer/journal/templates/` directory.
    
    Now that you have the folders setup for the new structure and templates, you
    can create the structure and template next.

4.  Create a `Basic Web Content.json` file in the 
    `resources-importer/journal/structures/` directory and add the following 
    code to it:
    
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
    articles, and specifies a `name` to identify the content. In previous 
    versions of Liferay, article structures were XML, now they must be JSON. Now 
    that you have the structure created, you can create the matching template 
    next.

5.  Create a `Basic Web Content.ftl` template file in the 
    `resources-importer/journal/templates/Basic Web Content/`
    folder you just created and add the following code to it:

        ${content.getData()}

This accesses the article's content by the `name` defined in the structure, 
`content` in this case, and renders the HTML data. Now that you have the basic
web content structure and template created, all that's left to update is the
article type.

#### Updating the Web Content Articles to XML

In 6.2, HTML articles were an acceptable type for the resources importer. In
Liferay 7, all articles must be of type XML. This migration is easier then it
may sound for basic web content. You just need to wrap the HTML in 
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

You may have also noticed that the `<div>` classes have been changed. The 
`2 column description.xml` article shown above illustrates the updated Bootstrap 
grid system for  Bootstrap 3. Instead of using the `span[number]` class to 
designate a column width, the `col-md-[number]` class is now used to designate 
column width. The total amount for the column widths must still add up to 12.

That's all that is needed for most of the basic web content articles. To make 
the article migration run as smoothly as possible, you can replace the HTML web 
content articles with the updated XML ones in the 
`resources-importer/journal/articles/Basic Web Content/` directory of the files 
you downloaded at the beginning.

There is one last update you'll need to make to one of the web content articles
which you can read about more next.

#### Updating the Reservation Form's Bootstrap

Liferay 7's UI is built with a design language, known as [Lexicon](http://liferay.github.io/lexicon/).
When building UI, it is recommended that you use Lexicon to follow the design
patterns that Liferay has created.

The reservation form for the lunar resort theme used Bootstrap 2's design 
language for the 6.2 themes Learning path. Lexicon is an extension of Bootstrap 
3, and uses updated patterns and classes that you should be aware of. To 
follow best practices for Liferay 7, this form should be updated to follow 
Lexicon's design language.

Follow the step below to update the reservation form:

1.  Open the `Reservation Form.xml` article in the 
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

The `control-group` classes were updated to `form-group` classes. The 
The `control-label` classes were removed from the `<label>` elements. The 
`<div class=""controls>` elements were removed. A `form-control` class was added 
to each `<input>` element. Finally, the `btn-primary` class was added to the 
submit button for the form, to give it more emphasis and contrast.

Now that the basic web content is updated, all that is left to update is the
carousel article's structure and template.

#### Updating the Carousel's Structure and Template

The carousel article uses a more advanced structure and template than the basic 
web content. Luckily, the carousel article is already XML, so it doesn't need 
updated.

Follow these steps to migrate the 6.2 carousel structure and template to 
Liferay 7:

Liferay 6.2 used AlloyUI 2.0.x, Liferay 7 uses Alloy UI 3.0.x. There has been a
slight syntax change to the AlloyUI carousel between the two versions. Note that
support of HTML markup within the carousel images has been removed, causing the 
image links to no longer work. Make the changes below to update the carousel:

1.  Open the `carousel.vm` template in the `templates/carousel/` directory and
    replace all instances of `aui-carousel-item` with `image-viewer-base-image`
    and save the changes.
    
    Now that the template is updated, you can update the structure next. The
    carousel structure is XML and, as mentioned earlier, all structures must be
    JSON in Liferay 7.

2.  Open the `carousel.xml` structure in the `journal/structures/` directory and
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

The resources importer is fully upgraded! The lunar resort theme is ready for
Liferay 7!

Run `gulp deploy` to build and deploy the theme to the app server you defined at
the beginning.

