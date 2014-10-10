# Leveraging Portal Predefined Settings in Your Theme [](id=leveraging-portal-predefined-settings-in-your-theme)

The portal defines some settings that allow the theme to determine certain
behaviors. As of this writing, predefined settings are only available for
portlet borders, bullet styles, and the site name, but more settings may be
added in the future. You can modify these settings from your theme's
`liferay-look-and-feel.xml` file. 

Time to get on with learning how to customize predefining settings using themes.
The first thing to cover is settings for portlet borders.

## Portlet Borders

By default, the theme turns on portlet borderst. But you can turn them off by
setting `portlet-setup-show-borders-default` to `false`, in your theme's
`liferay-look-and-feel.xml` file. For example, the following setting makes
border display configurable for the site administrator and disables the default
behavior of showing the borders:

    <settings>
        ...
        <setting
            configurable="true"
            key="portlet-setup-show-borders-default"
            type="checkbox"
            value="false"
        />
        ...
    </settings>

Now that you've learned how to configure portlet borders, you can learn how to 
configure bullet styles used in your sites, next. 

## Bullet Styles

Several of Liferay's core portlets use bullets, and you may have custom portlets
in your portal that use bullets too. Any portlet can be configured to use any
bullet styles inherited by your theme or implemented in your theme. For example,
if your theme uses Liferay's Classic theme as its base parent (see
[Setting a Base Theme](/develop/tutorials/-/knowledge_base/6-2/setting-a-base-theme)),
you can leverage the Classic theme's *arrows* bullet style.

The sample code below demonstrates the arrow bullet style class applied from the
Classic theme's `_diffs/css/custom.css` file to Liferay's Navigation portlet: 

    .nav-menu-style-arrows ul {
    list-style-image: url(@theme_image_path@/navigation/bullet_selected.png);
    }

You can make the above bullet style, for example, along with any bullet styles
of your theme available for site administrators to use in their site's
Navigation portlet. Just follow the naming convention as demonstrated below,
substituting `[bullet style name]`, with your bullet style's name:

    .nav-menu-style-[bullet style name] ul {
        ... CSS selectors ...
    }

Then, make the `bullet-style` setting configurable in your theme's
`liferay-look-and-feel.xml` file. From this setting, you can set a default
bullet style and list optional bullet styles you want to make available to site
administrators: 

    <settings>
        ...
        <setting
            configurable="true"
            key="bullet-style"
            options="arrows,dots,classic,modern
            value="dots"
        />
        ...
    </settings>

Your site administrators can now choose this example bullet style to apply to
Liferay's Navigation portlet. They select it from the site's *Look and Feel*
administration page. 

Using CSS and maybe some unobtrusive JavaScript, you can create a navigation
menu that looks just the way you want. Next, take a look at how to configure
displaying your site's name. 

## Site Names

The site name settings let site administrators decide whether to display a
site's name. If you're using a logo that mentions your company or site on each
site page, for example, you may find the default site name display distracting.

![Figure 1: By default, themes display the site's title on each page.](../../images/theme-site-name.png)

If you're using Liferay's *_unstyled* theme as your base theme, you have the
following settings available for configuring site name display:

- `show-site-name-default` configures site name display and lets you turn it
  on/off by default. 
- `show-site-name-supported` configures support for site name display and lets
  you turn it on/off by default. 

Here is how you might specify them in your `liferay-look-and-feel.xml` file:

    <settings>
        ...
        <setting
            configurable="true"
            key="show-site-name-default"
            type="checkbox"
            value="true"
        />
        <setting
            configurable="true"
            key="show-site-name-supported"
            type="checkbox"
            value="true"
        />
        ...
    </settings>

With these settings configurable, site administrators can control site name
display from the each site's *Look and Feel* screen. 

+$$$

**Note:** To override default behavior
for individual portlets, you can modify the portlet's `liferay-portlet.xml`
file.

$$$

Now that you know how to use the predefined settings available to you in Liferay
Portal, the sky's the limit!

