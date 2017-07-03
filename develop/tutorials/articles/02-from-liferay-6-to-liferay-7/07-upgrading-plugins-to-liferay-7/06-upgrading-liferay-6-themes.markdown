# Upgrading Your Theme from Liferay Portal 6.1 to 7.0 [](id=upgrading-your-theme-from-6-1-to-7-0)

This tutorial guides you through the process of upgrading your 6.1 theme to run 
on @product-ver@.

For a more in depth tutorial that covers upgrading 6.2 themes, please see the 
[Upgrading Themes](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/upgrading-themes) 
tutorial.

## Setting Up Your Theme With Liferay Theme Generator [](id=setting-up-your-theme-with-liferay-theme-generator)

You’ll use Liferay Theme Generator to get the upgrade process started. Liferay 
Theme Generator supplies your theme with the necessary tools to deploy and make 
quick modifications.

In this tutorial, you'll use Liferay Theme Generator's Import feature to help 
set up the upgrade process.

### Preparing Your Theme [](id=preparing-your-theme)

Before you import your theme, you need to make some modifications to your theme 
to ensure Liferay Theme Generator sets up the correct version and template 
language.

Make sure these settings are present in your theme:

1.  Open your `liferay-look-and-feel.xml` file and make sure the template language 
    (`vm`, `ftl`, `jsp`) is defined:

        <look-and-feel>
            ...
            <theme id="my-theme-name" name="My Theme Name">
                <template-extension>vm</template-extension>
                ...
 
2.  Also in `liferay-look-and-feel.xml`, set the theme version to 6.2+:

        <look-and-feel>
            <compatibility>
                <version>6.2+</version>
            </compatibility>
            ...
 
3.  Finally, open your `liferay-plugin-package.properties` file and update the 
    Liferay Portal version to 6.2+, as well:

        liferay-versions=6.2+

+$$$

**Note:** Because Liferay Theme Generator doesn't officially support importing 
6.1 themes, you must set the version of your theme to 6.2 to import it.

$$$

Now that your theme is prepped, you can start the import process next.

### Importing Your Theme [](id=importing-your-theme)

Follow these steps to import your theme:

1.  Install Liferay Theme Generator and its required dependencies. See 
    [project Readme](https://github.com/liferay/generator-liferay-theme#liferay-theme-generator--)
    for details.

2.  Navigate to the folder where you want your theme imported: 

        $ cd /where/i/want/my-theme/imported
 
3.  Import your theme by running this command:

        $ yo liferay-theme:import

4.  Follow the prompts to complete your theme import.

Your theme has been imported into a folder named after your original theme. For 
example, a theme named `my-awesome-theme-for-61` would be imported into a folder 
called `my-awesome-theme-for-61-theme`.

Next, you can learn how to update your theme for 7.0.

## Updating Your Theme For 7.0 [](id=updating-your-theme-for-7-0)

Now that you've successfully imported your theme, you have the tools necessary 
to develop your theme for 7.0. These tools are provided by 
[Liferay Theme Tasks](https://github.com/liferay/liferay-theme-tasks#liferay-theme-tasks-).

The first of these tools you'll use is the Upgrade task. This task does a few 
things:

- Backs up your files so you can revert them later if needed.

- Modifies and renames some files for compatibility.

- Creates a `css.diff` file under `/_backup`, so you can easily see what's been 
  changed.

- Updates NPM dependencies.

- Creates a "compatibility" file for Compass mixins, which may have been used 
  in your 6.1 theme.

+$$$

**Important**: Before you run the Update task, read the section below titled 
[An Important Note About Core Stylesheets](/develop/tutorials/-/knowledge_base/7-0/upgrading-your-theme-from-6-1-to-7-0#an-important-note-about-core-stylesheets) 
for advice on renaming (effectively overriding) "core" stylesheets.

$$$

Upgrade your theme, by running these commands:

    $ cd my-awesome-theme-for-62-theme

    $ gulp upgrade

If the Upgrade task didn't update your files like you expected, you can easily 
revert them back using the Upgrade Revert task:

    $ gulp upgrade:revert

The Upgrade task automates some of the steps for you, but some manual changes 
are still required. These are covered next. 

## Additional Manual Changes To Your Theme [](id=additional-manual-changes-to-your-theme)

When the Upgrade task finishes, the console may output additional manual changes 
you may need to make to your theme.

+$$$

**Note:** Because Liferay Portal 7.0 uses Bootstrap 3, the default 
[box model](https://css-tricks.com/box-sizing/) 
has been changed to box-sizing: border-box. If you were using width or height 
and padding together on an element, you may need to make changes, or those 
elements may have unexpected sizes.

$$$

There are likely changes that you still need to make, which these tools and this 
guide can't realistically cover. Therefore, it's recommended that you make 
changes gradually, rather than all at once. This allows you to more easily 
identify any issues your theme may have.

Please refer to the [Appendix](/develop/tutorials/-/knowledge_base/7-0/upgrading-your-theme-from-6-1-to-7-0#appendix) 
below for ways on how to update your theme manually.

Follow the instructions in the next section to build and deploy your theme.

## Building And Deploying Your Theme [](id=building-and-deploying-your-theme)

Follow these steps to build and deploy your theme:

1.  To check that everything is running smoothly, run this command:

        $ gulp build
 
2.  If all goes well, you can now deploy your theme by running this command:

        $ gulp deploy

If you're using Velocity as your template language, you'll notice this message 
on your server console when deploying your theme:

*Support of Velocity is deprecated. Update theme to use FreeMarker for forward 
compatibility.*

It's recommended that you migrate to using Freemarker for your theme templates. 
For more details, please see the 
[Upgrading Theme Templates](/develop/tutorials/-/knowledge_base/7-0/upgrading-your-theme-from-6-1-to-7-0#upgrading-theme-templates) 
section in the Appendix.

Additional tasks and info can be found on the 
[Liferay Theme Tasks project site](https://github.com/liferay/liferay-theme-tasks). 

Congratulations! You've now completed a major step in upgrading your theme.

## Appendix: Making Manual Compatibility Adjustments To Your Theme [](id=appendix)

This appendix covers the changes you need to make to your theme if you 
manually update your files, or if you need to make additional changes after 
using the Update task in Liferay Theme Tasks.

### Upgrading Theme Metadata [](id=upgrading-theme-metadata)

Your theme contains metadata about its capabilities and requirements. This 
metadata needs to be updated to ensure your theme is available for use in 
@product@.

#### Liferay Portal Version [](id=liferay-portal-version)

Your theme must target the proper @product@ version: in your case, 7.0.0+.
You must update the theme version in two places:
 
1.  In the file `liferay-plugin-package.properties`, add the following property 
    to ensure @product@ deploys your theme:

        liferay-versions=7.0.0+

2.  Add the following version information to your `liferay-look-and-feel.xml` 
    to ensure @product@ makes your theme available for use:

        <look-and-feel>
            <compatibility>
                <version>7.0.0+</version>
            </compatibility>
            ...

#### Template Extension [](id=template-extension)

If you're not using the default Theme Template language, Freemarker (.ftl), 
you must add a `template-extension` tag to your `liferay-look-and-feel.xml`
file:

    <look-and-feel>
        ...
        <theme id="my-theme-name" name="My Theme Name">
            <template-extension>vm</template-extension>
            ...

#### Required Deployment Contexts [](id=required-deployment-contexts)

If you have any Required Deployment Contexts listed in your 
`liferay-plugin-package.properties` file, these will likely need to be removed.

- `resources-importer-web`: Portal Compatibility Hook is for previous versions 
  of Liferay Portal.

- `portal-compat-hook`: Because this tutorial is focused on upgrading a theme 
  rather than importing content and assets, it does not cover the Resources 
  Importer. You can learn more about the Resources Importer in the 
  [Importing Resources with a Theme](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/importing-resources-with-a-theme) tutorial.

### Upgrading Stylesheets [](id=upgrading-stylesheets)

#### An Important Note About "Core" Stylesheets [](id=an-important-note-about-core-stylesheets)

If you've made customizations to "core" @product@ stylesheets, it's 
recommended that you not use these customizations right away. @product@'s 
UI has changed significantly since version 6.1, and any customizations you may 
have are either not likely to work, or will cause problems in the UI.

These "core" files would be:

- `application.css`

- `aui.css`

- `base.css`

- `dockbar.css`

- `extras.css`

- `layout.css`

- `main.css`

- `navigation.css`

- `portal.css`

- `portlet.css`

- `taglib.css`

+$$$

**Important:** Rename these files to something like `my_application.css`, 
`my_aui.css`, etc. If you don't rename these files, you may get an error when 
building your theme. This is because Sass is importing ambiguous names like 
application, aui, etc.

$$$

It's recommended that you get your theme working in @product-ver@ first, 
and then audit the styles in these "core" CSS files to see if they're still 
necessary and don't cause any issues.

#### Renaming CSS Files [](id=renaming-css-files)

@product-ver@ now uses the `.scss` extension for Sass stylesheets.

1.  Under `my-imported-theme/src/css`, rename your `custom.css` file to 
    `_custom.scss`.
 
2.  If you have any additional CSS files that need to take advantage of Sass and 
    [Bourbon](http://bourbon.io/), you must rename those as well.

    For example, `my-feature.css` becomes `_my-feature.scss`, and `fonts.css` 
    becomes `_fonts.scss`.

#### Imported Stylesheet References [](id=imported-stylesheet-references)

If you've imported any renamed stylesheets into another, you'll want to update 
those references.

For example `_my-fonts.scss` needs to be updated from:

    @import url(my-fonts.css);

to:

    @import "my-fonts";

#### Sass Framework Import [](id=sass-framework-import)

Beginning with version 7.0, Liferay Portal now uses 
[Bourbon](http://bourbon.io/) 
for mixins, rather than Compass. Bourbon is being imported in a core stylesheet,
[_imports.scss](https://github.com/liferay/liferay-portal/blob/7.0.2-ga3/modules/apps/foundation/frontend-theme/frontend-theme-styled/src/main/resources/META-INF/resources/_styled/css/_imports.scss),
so you shouldn't need to import it yourself.

Also, files with the `.scss` extension are processed by Sass automatically. 
But you must still import or link to these to use them.

Remove `@import "compass"` from all your Sass files, and don't worry about 
importing Bourbon.

#### Mixins [](id=mixins)

When Liferay Portal switched from using Compass to 
[Bourbon](http://bourbon.io/), 
some mixins changed syntax, became unsupported, or became unnecessary 
in modern browsers. These mixins should be updated or removed.

For example:

    .my-component {

        @include border-radius(8px);

        @include opaque;

        @include scale(0.9);

    }

    input[type="text"] {

        @include input-placeholder {

            color: #bfbfbf;

        }

    }

becomes:

    .my-component {

        border-radius: 8px;

        opacity: 1;

        @include transform(scale(0.9));

    input[type="text"] {

        @include placeholder {

            color: #bfbfbf;

        }

    }

Please refer to [Compass](http://compass-style.org/index/mixins/) and 
[Bourbon](http://bourbon.io/docs/#mixins) documentation for more details.

#### Bootstrap 3 [](id=bootstrap-3)

If you've used Bootstrap 2.3.2 in your theme, a [migration guide](http://getbootstrap.com/migration/) is available.

#### Remove `.aui` Class Used In Descendant Selectors [](id=remove-aui-class-used-in-descendant-selectors)

The class `.aui` is no longer in use. Selectors that contain `.aui` as a parent 
qualifier must be modified, or those selectors will not match the expected 
elements.

For example:

    .aui{

        my-component {

            // ...

        }

    }

    .aui my-other-component {

        // ...

    }

becomes:

    html {

        my-component {

            // ...

        }

    }

    html my-other-component {

        // ...

    }

or maybe:

    my-component {

        // ...

    }

    my-other-component {

        // ...
    }

### Upgrading JavaScript [](id=upgrading-javascript)

In August 2014 
[Yahoo stopped all new development of YUI](https://yahooeng.tumblr.com/post/96098168666/important-announcement-regarding-yui), 
which AlloyUI was built on. Because of this announcement, Liferay has decided to
sunset AlloyUI and deprecate it as of Liferay Portal 7.

Although AlloyUI is deprecated, it's still available by default, and existing 
AlloyUI 3 based code continues to work.

To further research changes to an AlloyUI module, you can find a `HISTORY.md` 
file under each component's directory.

Moving forward, it's recommended to migrate your AlloyUI/YUI code to either 
Metal.js or the framework of your choice. Below, you'll find some libraries used 
in @product@. Feel free to use them as well.

#### jQuery [](id=jquery)

[jQuery](https://jquery.com/) is a fast, small, and feature-rich JavaScript 
library. It makes things like HTML document traversal and manipulation, event 
handling, animation, and Ajax much simpler. It's also popular among front-end
developers, making it ideal for getting started quickly.

For more in depth coverage, see [jQuery's documentation](http://api.jquery.com/).

#### Lodash [](id=lodash)

[Lodash](https://lodash.com/) is a modern JavaScript utility library delivering 
modularity, performance & extras. It's used in @product@ to fill the void left 
by YUI’s utility modules.

For more in depth coverage, see [Lodash's documentation](http://lodash.com/docs/).

#### Metal.js [](id=metal-js)

Written by Liferay to specifically to meet the needs of @product@, 
[Metal.js](http://www.metaljs.com/) 
is a JavaScript library for building UI components in a solid, flexible way.

For more in depth coverage, see [Metal.js's documentation](http://metaljs.com/docs/)

### Upgrading Theme Templates [](id=upgrading-theme-templates)

In prior versions of Liferay Portal, Velocity templates (`.vm` files) were the 
default template language for writing theme markup. But Velocity had
limitations. In @product-ver@, Freemarker templates (`.ftl` files) are
the default template language, and Velocity templates are deprecated.

With that in mind, if your themes are written in `.vm` files, they should still 
work, but you're missing out on additional theme features. In a future release, support for Velocity templates will be removed.

#### Migrating to Freemarker Templates [](id=migrating-to-freemarker-templates)

For a typical theme, migrating to Freemarker should be simple. The syntaxes of 
both languages are relatively similar.

Here's a comparison: 

**Velocity:**

    $theme.include($my_template_include)

    #if ($show_site_name)

        <span title="#language_format ("go-to-x", [$site_name])">

            $site_name

        </span>

    #end

    #parse ("$full_templates_path/navigation.vm")

    #breadcrumbs()

    $theme.wrapPortlet("portlet.vm", $content_include)

    #foreach ($nav_item in $nav_items)

        $nav_item.getName()

    #end

**Freemarker:**

    <@liferay_util["include"] page=my_template_include />

    <#if show_site_name>

        <span title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">

            ${site_name}

        </span>

    </#if>

    <#include "${full_templates_path}/navigation.ftl" />

    <@liferay.breadcrumbs />

    <@liferay_theme["wrap-portlet"] page="portlet.ftl">

        <@liferay_util["include"] page=content_include />

    </@>

    <#list nav_items as nav_item>

        ${nav_item.getName()}

    </#list>

Whichever language you decide to use, there are several convenient macros
included with @product@: 

- [Freemarker macros on LDN](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/freemarker-macros)

- [Freemarker macros source](https://github.com/liferay/liferay-portal/blob/7.0.2-ga3/modules/apps/foundation/portal-template/portal-template-freemarker/src/main/resources/FTL_liferay.ftl)

- [Velocity macros source](https://github.com/liferay/liferay-portal/blob/7.0.2-ga3/modules/apps/foundation/portal-template/portal-template-velocity/src/main/resources/VM_liferay.vm)

### Noteworthy Breaking Changes [](id=noteworthy-breaking-changes)

There are many changes since Liferay Portal 6.1 which break in version 7.0. Some
of these changes are documented in 
[@product@'s Breaking Changes](/develop/reference/-/knowledge_base/7-0/breaking-changes).
Below are some notable ones. 

#### Dockbar Is Now Called Control Menu [](id=dockbar-is-now-called-control-menu)

In `portal_normal.vm`, admin controls are now called using `#control_menu()`.

Liferay Portal 6.1:

    #if ($is_signed_in)

        #dockbar()

    #end

@product-ver@:

    #control_menu()

#### Portlet Configuration Options [](id=portlet-configuration-options)

In `portlet.vm`, portlet menu icons have been condensed into a single call and 
are now controlled using the `PortletConfigurationIcon` classes.

Liferay Portal 6.1:

    $theme.iconOptions()

    $theme.iconMinimize()

    $theme.iconMaximize()

    $theme.iconClose()

@product-ver@:

    $theme.portletIconOptions()

See Liferay Portal's Breaking Changes for more details:

- [Removed the Tags that Start with portlet:icon-](https://github.com/liferay/liferay-portal/blob/master/readme/7.0/BREAKING_CHANGES.markdown#removed-the-tags-that-start-with-portleticon-)
- [Portlet Configuration Options May Not Always Be Displayed](https://github.com/liferay/liferay-portal/blob/master/readme/7.0/BREAKING_CHANGES.markdown#portlet-configuration-options-may-not-always-be-displayed)

#### Navigation Item Icons [](id=navigation-item-icons)

In `navigation.vm`, access to icons have been changed, to minimize dependency on 
taglibs.

Liferay Portal 6.1:

    nav_item.icon()

@product-ver@:

    $theme.layoutIcon($nav_item.getLayout())
 
See [Liferay Portal's Breaking Changes](https://github.com/liferay/liferay-portal/blob/master/readme/7.0/BREAKING_CHANGES.markdown#removed-the-icon-method-from-navitem) for more details.

### Upgrading Layout Templates [](id=upgrading-layout-templates)

The only significant change to Layout Templates has been the addition of 
[Bootstrap's Grid system](http://getbootstrap.com/css/#grid).

If you're using any custom Layout Templates, you'll want to integrate Bootstrap 
into them.

A comparison between Layout Templates in Liferay Portal 6.1 and Liferay Portal 
7.0 is shown in the example below:

Liferay Portal 6.1:

    <div class="portlet-layout">

        <div class="portlet-column portlet-column-only" id="column-1">

            $processor.processColumn("column-1", "portlet-column-content portlet-column-content-only")

        </div>

    </div>

Liferay Portal 7.0:

    <div class="portlet-layout row">

        <div class="col-md-12 portlet-column portlet-column-only" id="column-1">

            $processor.processColumn("column-1", "portlet-column-content portlet-column-content-only")

        </div>

    </div>
 
## Related Topics [](id=related-topics)

[Themes Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator)

[Themelets](/develop/tutorials/-/knowledge_base/7-0/themelets)

[Importing Resources with a Theme](/develop/tutorials/-/knowledge_base/7-0/importing-resources-with-a-theme)
