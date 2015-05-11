# Making Themes Configurable with Settings [](id=making-themes-configurable-with-settings)

You can define settings to make your theme configurable for site administrators.
You can add a simple text field input setting, which is the default setting
type, or add other types of settings, such as text areas, checkboxes, etc.
And you can even introduce logic in your settings to leverage different theme
templates based on the settings selected by the user. In this tutorial, you'll
learn how to do all these things. 

First, it's best to learn how to add configurable settings to a theme. 

## Adding Configurable Settings to a Theme [](id=adding-configurable-settings-to-a-theme)

Settings must be defined in the theme plugin's `liferay-look-and-feel.xml` file found in the
`docroot/WEB-INF` folder. The
process is easier than you may think. Follow the steps below to get started.

1.  Open the `liferay-look-and-feel.xml` file.

    If your project doesn't have this file, create it in the `docroot/WEB-INF`
    folder and add the following XML content:

		<?xml version="1.0"?>
		<!DOCTYPE look-and-feel PUBLIC "-//Liferay//DTD Look and Feel 6.2.0//EN"
		"http://www.liferay.com/dtd/liferay-look-and-feel_6_2_0.dtd">

		<look-and-feel>
			<compatibility>
				<version>6.2.0+</version>
			</compatibility>
			<theme id="your-theme" name="Your Theme">
			</theme>
		</look-and-feel>

    Replace the `<theme>` element's `id` and `name` attribute values with the ID
    and display name of your theme. 

2.  Add `<settings></settings>` tags between the theme's `<theme></theme>` tags.

3.  Add a `<setting/>` element between the `<settings></settings>` tags for as
many settings as you want for the theme. For example, if you want to add a
simple input field setting, you can add a setting like this one: 

		<setting key="your-key" value="your-value" />

    If you stopped at this point, this setting would not be configurable for
    portal users. You'd have to manually change the setting in the theme
    plugin's `liferay-look-and-feel.xml` file and redeploy the plugin. 

    Note, that you can access your settings in your theme's templates by calling
    `$theme.getSetting("your-key")` method, where you'd specify your setting's
    key in place of *your-key*. 

4.  To make a setting configurable from the portal, you must add the attribute
`configurable="true"` to the `<setting>` element. 

		<setting configurable="true" key="your-key" value="your-value" />

Your assembled `liferay-look-and-feel.xml` content might look similar to the
code below, with your own `<theme>` and `<setting>` element attribute values in
place of the ones specified below. 

        <?xml version="1.0"?>
        <!DOCTYPE look-and-feel PUBLIC "-//Liferay//DTD Look and Feel 6.2.0//EN"
        "http://www.liferay.com/dtd/liferay-look-and-feel_6_2_0.dtd">

        <look-and-feel>
            <compatibility>
                <version>6.2.0+</version>
            </compatibility>
            <theme id="your-theme" name="Your Theme">
                <settings>
                    <setting configurable="true" key="your-key" value="your-value" />
                </settings>
            </theme>
        </look-and-feel>

To define additional settings, add more `<setting>` elements inside the
`<settings></settings>` tags of your `liferay-look-and-feel.xml` file. To learn
what other types of settings you can add to a theme, see the DTD file referenced
at the beginning of this file's contents. This DTD and all of Liferay's
definition files are available to view at 
[https://github.com/liferay/liferay-portal/tree/master/definitions](https://github.com/liferay/liferay-portal/tree/master/definitions). 

Any configurable settings you've defined are visible and ready for the site
administrator to modify. In the *Look and Feel* section of the *Site
Administration* &rarr; *Site Pages* panel, the configurable settings are visible
once the site administrator selects the theme and clicks *Save*.

![Figure 1: Here are examples of configurable settings for the site admin to enter a slogan and display the theme's footer. Themes and their settings are available in the *Look and Feel* of a site's page settings.](../../images/themes-custom-configurable-setting.png)

+$$$

**Note:** Use a language properties
hook to properly display configurable theme settings, like the slogan text area
and footer checkbox from the previous example. For details, see the 
 [Overriding a *Language.properties* File](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/override-a-language-properties-hook-liferay-portal-6-2-dev-guide-en)
section in the *Hooks* chapter of the DevGuide.

$$$

Now that you know how to add settings, you can move on to learning how to add
logic to enable different templates for a theme. 

## Enabling Different Templates for a Theme [](id=enabling-different-templates-for-a-theme)

Say you want to be able to choose from two different page headers (perhaps
one includes more details, while the other is smaller). Instead of creating two
themes that are identical except for some changes in the header, you can create
one and define a setting that lets you choose which header is displayed. 
You can follow the steps below to facilitate selecting different templates for
your theme. 

1.  Make sure you have a `docroot/_diffs/templates` folder created.

2.  Create a template for each of page style choices you want to make available
    to configure for the theme. 

    For example, if you want to provide a theme template option for a detailed
    header for the theme and a brief header option for the same theme, you can
    create separate template files for them and put them in the
    `docroot/_diffs/templates` folder. 

3.  Copy the `docroot/templates/portal_normal.[vm|ftl]` file into that
    directory. 

4.  Open your `docroot/_diffs/templates/portal_normal.[vm|ftl]` template file
    and add logic to use a particular template based on a theme setting.

    For example, if you have a Velocity template called `header_detailed.vm`
    that implements the theme using a detailed header and another template
    called `header_brief.vm` that implements the theme using a brief header, you
    could implement conditional logic that chooses between the templates based
    on the value of a theme setting. The example Velocity code below uses the
    value of a setting named `header-type` to select a theme template to apply. 

        #if ($theme.getSetting("header-type") == "detailed")
            #parse ("$full_templates_path/header_detailed.vm")
        #else
            #parse ("$full_templates_path/header_brief.vm")
        #end

5.  In your `liferay-look-and-feel.xml` file, you could add a `<theme>` element
for each variation of your theme, based on the templates you implemented. Make
sure to use a `<setting>` element with the same `key`, but different `value` in
each of the `<theme>` elements. 

    Here are example `<theme>` elements that both use a `<setting>` with `key`
    *heading-type* assigned to different values. 

        <theme id="deep-blue" name="Deep Blue">
            <settings>
                <setting key="header-type" value="detailed" />
            </settings>
        </theme>
        <theme id="deep-blue-mini" name="Deep Blue Mini">
            <settings>
                <setting key="header-type" value="brief" />
            </settings>
        </theme>

Following this strategy allows the plugin to leverage the same
`portal_normal.[vm|ftl]` template, but apply templates based on the *theme* that
the site administrator selects. 

Congrats! Now you know how to create configurable settings for your theme and
use multiple page templates from the same `portal_normal.[vm|ftl]` template
script! 

## Related Topics [](id=related-topics)

[Using Configurable Portlet Preferences](/develop/tutorials/-/knowledge_base/6-2/using-configurable-portlet-preferences)

[Leveraging Portal Predefined Settings in Your Theme](/develop/tutorials/-/knowledge_base/6-2/leveraging-portal-predefined-settings-in-your-theme)
