# Leveraging Portal Predefined Settings [](id=leveraging-portal-predefined-settings-in-your-theme)

The portal defines some settings that allow the theme to determine certain
behaviors. As of this writing, predefined settings are only available for
portlet borders, bullet styles, and the site name, but more settings may be
added in the future. Modify these settings from the `liferay-look-and-feel.xml`
file. Remember, your `liferay-look-and-feel.xml` file should have the 6.2.0
doctype for the following predefined settings to work correctly.

---

![note](../../images/tip-pen-paper.png) **Note:** To override default behavior
for individual portlets, you can modify the portlet's `liferay-portlet.xml`
file.

---

Time to get on with learning about predefining settings using themes. First,
take a look at settings for portlet borders.

## Portlet Borders

The theme turns on portlet borders, by default. But you can turn them off by
setting `portlet-setup-show-borders-default` to `false` in your theme's
`liferay-look-and-feel.xml` file. For example, the following setting, makes
border display configurable for the portal administrator, and disables showing
the borders as the default:

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
configure bullet styles used in your sites next. 

## Bullet Styles

Liferay's Navigation portlet can be configured to use any bullet styles
inherited by your theme or implemented in your theme. For example, if your theme 
uses Liferay's *classic* theme as its base parent(see [Setting a Base Theme](/tutorials/-/knowledge_base/setting-a-base-theme))
, you can leverage its *arrows* bullet style. Here is the arrow bullet style's 
class from the *classic* theme's `_diffs/css/custom.css` file:

    .nav-menu-style-arrows ul {
    list-style-image: url(@theme_image_path@/navigation/bullet_selected.png);
    }

You can make this bullet style, along with any bullet styles you implement,
available for site administrators to use in their site's Navigation portlet.
Just follow the naming convention as demonstrated below, substituting `[bullet
style name]`, with your bullet style's name:

    .nav-menu-style-[bullet style name] ul {
        ... CSS selectors ...
    }

Then, make the `bullet-style` setting configurable in your
`liferay-look-and-feel.xml` file. From this setting, list optional bullet styles
you want available to site administrators, and set a default bullet style as
well:

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

Your site administrators can now choose the bullet style to apply to the
Navigation portlet. They select it from the site's *Look and Feel* control
page. 

Using CSS, and maybe some unobtrusive JavaScript, you can create a navigation
menu that looks just the way you want it. Next, take a look at how to configure 
your site's name display. 

## Site Names

The site name settings let site administrators decide whether to display a
site's name (i.e., title). But, if you are using a logo that mentions your
company or site on each site page, you may find the default site name display
distracting.

![Figure 1: By default, themes display the site's title on each page.](../../images/theme-site-name.png)

Since the themes you create in the Plugins SDK use Liferay's *_unstyled* theme
as a base theme, you have the following settings available for configuring site
name display:

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
display from the each site's *Look and Feel* tab, which can be found by clicking
the *Edit* button from the left side menu of any page.

Now that you know how to use the predefined settings available to you in portal,
the sky's the limit!

## Next Steps
<!-- URL will probably need updated when added to the new devsite-->
 [Using Developer Mode with Themes](/tutorials/-/knowledge_base/using-developer-mode)
 
 [Making Themes Configurable with Settings](/tutorials/-/knowledge_base/making-themes-configurable)

 [Specifying Color Schemes for Your Themes](/tutorials/-/knowledge_base/specifying-color-schemes-for-your-theme)
