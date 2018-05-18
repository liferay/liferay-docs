# Making Configurable Theme Settings [](id=making-configurable-theme-settings)

Every time you want to make a change to a theme, you must make the change and
then deploy it to your server. For a Site Administrator, this process is
tedious, especially if it's a minor change to a theme, such as a banner color
change. What happens when it must be changed back? It must be deployed again.
For larger theme changes, this process is unavoidable, but for smaller changes,
there's a better way: configurable theme settings. The Theme Developer can
control the visibility and value of theme elements and provide variations of
theme elements for the Site Administrator to configure and choose in the Control
Panel. This tutorial covers how to create configurable theme settings for your
theme. 

Follow these steps:

1.  Open your theme's `WEB-INF/liferay-look-and-feel.xml` file, or create it if 
    it doesn't exist, and follow the pattern below to nest a `<setting/>` 
    element inside the parent `<settings>` element for each setting the theme 
    requires:

        <?xml version="1.0"?>
        <!DOCTYPE look-and-feel PUBLIC "-//Liferay//DTD Look and Feel 7.0.0//EN"
        "http://www.liferay.com/dtd/liferay-look-and-feel_7_0_0.dtd">

        <look-and-feel>
        	<compatibility>
        		<version>7.0.0+</version>
        	</compatibility>
        	<theme id="your-theme-name" name="Your Theme Name">
        		<template-extension>ftl</template-extension>
            <settings>
              <setting configurable="true" key="theme-setting-key"
              options="true,false" type="select" value="true" />
              <setting configurable="true" key="theme-setting-key"
              type="text" value="My placeholder text" />
            </settings>
            <portlet-decorator>
              portlet decorators...
        		</portlet-decorator>
        	</theme>
        </look-and-feel>

    The available theme `<setting>` attributes are shown below:

    `configurable`: whether the setting is configurable or static 

    `key`: the language key that identifies the theme setting 

    `options`: sets the options for the select menu if the `type` is `select` 

    `type`: the type of UI to render to control the theme setting. Possible 
    values are `checkbox`, `select`, `text`, or `textarea`. 

    `value`: sets the default value for the theme setting 

    You can find more information about setting attributes and all other 
    configurations for the `liferay-look-and-feel.xml` in its 
    [DTD docs](@platform-ref@/7.1-latest/definitions/liferay-look-and-feel_7_1_0.dtd.html#settings). 

2.  Add `init_custom.ftl` to your theme templates if it doesn't already exist, 
    and follow the patterns below to define your theme setting variables in it:

    Booleans (a select box with the options `true` and `false` or a checkbox 
    with values `yes` and `no`):

        <#assign my_variable_name =
        getterUtil.getBoolean(themeDisplay.getThemeSetting("theme-setting-key"))/>

    Strings (a text input or text area input):

        <#assign my_variable_name =
        getterUtil.getString(themeDisplay.getThemeSetting("theme-setting-key"))/>

3.  Apply your theme settings to the theme template. The example configuration 
    below prints the value for the `custom_text` variable in a `<p>` element:

    `portal_normal.ftl`:

        <p>${custom_text}</p>

4.  Deploy and install the theme, and open the *Control Menu* &rarr; *Site 
    Administration* &rarr; *Build* &rarr; *Site Pages*. From this point, you 
    can configure theme settings for two scopes:

    - **The entire site:** open the Options menu in the top right, and select 
      *Configure*. Then, click the *Look and Feel* tab and select the 
      *Define a Specific look and feel for this page* option. Choose your 
      settings and click *Save* to apply the changes to the site. 

    - **An individual page:** Open the Actions menu next to the page and select 
    *Configure*. Then, click the *Look and Feel* tab and select the 
    *Define a Specific look and feel for this page* option. Choose your settings 
    and click *Save* to apply the changes to the page. 

![Figure 1: Here are examples of configurable settings for the site Admin.](../../../../images/theme-dev-configurable-theme-settings.png)

Now you know how to make configurable theme settings for your themes!

## Related Topics [](id=related-topics)

[Creating Reusable Pieces of Code for Your Themes](/develop/tutorials/-/knowledge_base/7-1/creating-reusable-pieces-of-code-for-your-themes)

[Listing Your Theme's Extensions](/develop/tutorials/-/knowledge_base/7-1/listing-your-themes-extensions)

[Importing Resources with a Theme](/develop/tutorials/-/knowledge_base/7-1/importing-resources-with-a-theme)
